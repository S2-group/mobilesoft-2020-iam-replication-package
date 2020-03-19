package com.android.launcher3.model;

import android.content.ComponentName;
import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.android.launcher3.InvariantDeviceProfile;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.LauncherProvider;
import com.android.launcher3.LauncherSettings.Favorites;
import com.android.launcher3.LauncherSettings.Settings;
import com.android.launcher3.LauncherSettings.WorkspaceScreens;
import com.android.launcher3.Utilities;
import com.android.launcher3.compat.PackageInstallerCompat;
import com.android.launcher3.util.GridOccupancy;
import com.android.launcher3.util.LongArrayMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class GridSizeMigrationTask
{
  public static boolean ENABLED = Utilities.ATLEAST_NOUGAT;
  protected final ArrayList<DbEntry> mCarryOver = new ArrayList();
  private final Context mContext;
  private final int mDestHotseatSize;
  protected final ArrayList<Long> mEntryToRemove = new ArrayList();
  private final InvariantDeviceProfile mIdp;
  private final boolean mShouldRemoveX;
  private final boolean mShouldRemoveY;
  private final int mSrcHotseatSize;
  private final int mSrcX;
  private final int mSrcY;
  private final ContentValues mTempValues = new ContentValues();
  private final int mTrgX;
  private final int mTrgY;
  private final ArrayList<ContentProviderOperation> mUpdateOperations = new ArrayList();
  private final HashSet<String> mValidPackages;
  
  protected GridSizeMigrationTask(Context paramContext, InvariantDeviceProfile paramInvariantDeviceProfile, HashSet<String> paramHashSet, int paramInt1, int paramInt2)
  {
    this.mContext = paramContext;
    this.mIdp = paramInvariantDeviceProfile;
    this.mValidPackages = paramHashSet;
    this.mSrcHotseatSize = paramInt1;
    this.mDestHotseatSize = paramInt2;
    this.mTrgY = -1;
    this.mTrgX = -1;
    this.mSrcY = -1;
    this.mSrcX = -1;
    this.mShouldRemoveY = false;
    this.mShouldRemoveX = false;
  }
  
  protected GridSizeMigrationTask(Context paramContext, InvariantDeviceProfile paramInvariantDeviceProfile, HashSet<String> paramHashSet, Point paramPoint1, Point paramPoint2)
  {
    this.mContext = paramContext;
    this.mValidPackages = paramHashSet;
    this.mIdp = paramInvariantDeviceProfile;
    this.mSrcX = paramPoint1.x;
    this.mSrcY = paramPoint1.y;
    this.mTrgX = paramPoint2.x;
    this.mTrgY = paramPoint2.y;
    int i = this.mTrgX;
    int j = this.mSrcX;
    boolean bool2 = false;
    if (i < j) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    this.mShouldRemoveX = bool1;
    boolean bool1 = bool2;
    if (this.mTrgY < this.mSrcY) {
      bool1 = true;
    }
    this.mShouldRemoveY = bool1;
    this.mDestHotseatSize = -1;
    this.mSrcHotseatSize = -1;
  }
  
  private boolean applyOperations()
    throws Exception
  {
    if (!this.mUpdateOperations.isEmpty()) {
      this.mContext.getContentResolver().applyBatch(LauncherProvider.AUTHORITY, this.mUpdateOperations);
    }
    if (!this.mEntryToRemove.isEmpty())
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Removing items: ");
      localStringBuilder.append(TextUtils.join(", ", this.mEntryToRemove));
      Log.d("GridSizeMigrationTask", localStringBuilder.toString());
      this.mContext.getContentResolver().delete(LauncherSettings.Favorites.CONTENT_URI, Utilities.createDbSelectionQuery("_id", this.mEntryToRemove), null);
    }
    return (!this.mUpdateOperations.isEmpty()) || (!this.mEntryToRemove.isEmpty());
  }
  
  private static ArrayList<DbEntry> deepCopy(ArrayList<DbEntry> paramArrayList)
  {
    ArrayList localArrayList = new ArrayList(paramArrayList.size());
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext()) {
      localArrayList.add(((DbEntry)paramArrayList.next()).copy());
    }
    return localArrayList;
  }
  
  private int getFolderItemsCount(long paramLong)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("container = ");
    ((StringBuilder)localObject).append(paramLong);
    localObject = ((StringBuilder)localObject).toString();
    localObject = queryWorkspace(new String[] { "_id", "intent" }, (String)localObject);
    int i = 0;
    while (((Cursor)localObject).moveToNext())
    {
      try
      {
        verifyIntent(((Cursor)localObject).getString(1));
        i += 1;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
      this.mEntryToRemove.add(Long.valueOf(((Cursor)localObject).getLong(0)));
    }
    ((Cursor)localObject).close();
    return i;
  }
  
  private static String getPointString(int paramInt1, int paramInt2)
  {
    return String.format(Locale.ENGLISH, "%d,%d", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
  }
  
  protected static HashSet<String> getValidPackages(Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = paramContext.getPackageManager().getInstalledPackages(8192).iterator();
    while (localIterator.hasNext()) {
      localHashSet.add(((PackageInfo)localIterator.next()).packageName);
    }
    localHashSet.addAll(PackageInstallerCompat.getInstance(paramContext).updateAndGetActiveSessionCache().keySet());
    return localHashSet;
  }
  
  private ArrayList<DbEntry> loadHotseatEntries()
  {
    Cursor localCursor = this.mContext.getContentResolver().query(LauncherSettings.Favorites.CONTENT_URI, new String[] { "_id", "itemType", "intent", "screen" }, "container = -101", null, null, null);
    int i = localCursor.getColumnIndexOrThrow("_id");
    int j = localCursor.getColumnIndexOrThrow("itemType");
    int k = localCursor.getColumnIndexOrThrow("intent");
    int m = localCursor.getColumnIndexOrThrow("screen");
    ArrayList localArrayList = new ArrayList();
    DbEntry localDbEntry;
    for (;;)
    {
      if (!localCursor.moveToNext()) {
        break label397;
      }
      localDbEntry = new DbEntry();
      localDbEntry.id = localCursor.getLong(i);
      localDbEntry.itemType = localCursor.getInt(j);
      localDbEntry.screenId = localCursor.getLong(m);
      if (localDbEntry.screenId < this.mSrcHotseatSize) {
        break;
      }
      this.mEntryToRemove.add(Long.valueOf(localDbEntry.id));
    }
    for (;;)
    {
      try
      {
        int n = localDbEntry.itemType;
        if (n != 6) {}
        switch (n)
        {
        case 2: 
          throw new Exception("Invalid item type");
          n = getFolderItemsCount(localDbEntry.id);
          if (n != 0) {
            localDbEntry.weight = (0.5F * n);
          } else {
            throw new Exception("Folder is empty");
          }
        case 0: 
        case 1: 
          verifyIntent(localCursor.getString(k));
          if (localDbEntry.itemType != 0) {
            break label410;
          }
          f = 0.8F;
          localDbEntry.weight = f;
          localArrayList.add(localDbEntry);
        }
      }
      catch (Exception localException)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Removing item ");
        localStringBuilder.append(localDbEntry.id);
        Log.d("GridSizeMigrationTask", localStringBuilder.toString(), localException);
        this.mEntryToRemove.add(Long.valueOf(localDbEntry.id));
      }
      break;
      label397:
      localCursor.close();
      return localArrayList;
      continue;
      label410:
      float f = 1.0F;
    }
  }
  
  public static void markForMigration(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    Utilities.getPrefs(paramContext).edit().putString("migration_src_workspace_size", getPointString(paramInt1, paramInt2)).putInt("migration_src_hotseat_count", paramInt3).apply();
  }
  
  /* Error */
  public static boolean migrateGridIfNeeded(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 371	com/android/launcher3/Utilities:getPrefs	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   4: astore 5
    //   6: aload_0
    //   7: invokestatic 404	com/android/launcher3/LauncherAppState:getIDP	(Landroid/content/Context;)Lcom/android/launcher3/InvariantDeviceProfile;
    //   10: astore 6
    //   12: aload 6
    //   14: getfield 409	com/android/launcher3/InvariantDeviceProfile:numColumns	I
    //   17: aload 6
    //   19: getfield 412	com/android/launcher3/InvariantDeviceProfile:numRows	I
    //   22: invokestatic 381	com/android/launcher3/model/GridSizeMigrationTask:getPointString	(II)Ljava/lang/String;
    //   25: astore 7
    //   27: aload 7
    //   29: aload 5
    //   31: ldc_w 379
    //   34: ldc_w 414
    //   37: invokeinterface 417 3 0
    //   42: invokevirtual 420	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   45: ifeq +28 -> 73
    //   48: aload 6
    //   50: getfield 423	com/android/launcher3/InvariantDeviceProfile:numHotseatIcons	I
    //   53: aload 5
    //   55: ldc_w 389
    //   58: aload 6
    //   60: getfield 423	com/android/launcher3/InvariantDeviceProfile:numHotseatIcons	I
    //   63: invokeinterface 426 3 0
    //   68: if_icmpne +5 -> 73
    //   71: iconst_1
    //   72: ireturn
    //   73: invokestatic 432	java/lang/System:currentTimeMillis	()J
    //   76: lstore_2
    //   77: aload_0
    //   78: invokestatic 434	com/android/launcher3/model/GridSizeMigrationTask:getValidPackages	(Landroid/content/Context;)Ljava/util/HashSet;
    //   81: astore 8
    //   83: aload 5
    //   85: ldc_w 389
    //   88: aload 6
    //   90: getfield 423	com/android/launcher3/InvariantDeviceProfile:numHotseatIcons	I
    //   93: invokeinterface 426 3 0
    //   98: istore_1
    //   99: iload_1
    //   100: aload 6
    //   102: getfield 423	com/android/launcher3/InvariantDeviceProfile:numHotseatIcons	I
    //   105: if_icmpeq +381 -> 486
    //   108: new 2	com/android/launcher3/model/GridSizeMigrationTask
    //   111: dup
    //   112: aload_0
    //   113: aload_0
    //   114: invokestatic 404	com/android/launcher3/LauncherAppState:getIDP	(Landroid/content/Context;)Lcom/android/launcher3/InvariantDeviceProfile;
    //   117: aload 8
    //   119: iload_1
    //   120: aload 6
    //   122: getfield 423	com/android/launcher3/InvariantDeviceProfile:numHotseatIcons	I
    //   125: invokespecial 436	com/android/launcher3/model/GridSizeMigrationTask:<init>	(Landroid/content/Context;Lcom/android/launcher3/InvariantDeviceProfile;Ljava/util/HashSet;II)V
    //   128: invokevirtual 439	com/android/launcher3/model/GridSizeMigrationTask:migrateHotseat	()Z
    //   131: istore 4
    //   133: goto +3 -> 136
    //   136: new 95	android/graphics/Point
    //   139: dup
    //   140: aload 6
    //   142: getfield 409	com/android/launcher3/InvariantDeviceProfile:numColumns	I
    //   145: aload 6
    //   147: getfield 412	com/android/launcher3/InvariantDeviceProfile:numRows	I
    //   150: invokespecial 442	android/graphics/Point:<init>	(II)V
    //   153: astore 9
    //   155: aload 5
    //   157: ldc_w 379
    //   160: aload 7
    //   162: invokeinterface 417 3 0
    //   167: invokestatic 446	com/android/launcher3/model/GridSizeMigrationTask:parsePoint	(Ljava/lang/String;)Landroid/graphics/Point;
    //   170: astore 10
    //   172: new 9	com/android/launcher3/model/GridSizeMigrationTask$MultiStepMigrationTask
    //   175: dup
    //   176: aload 8
    //   178: aload_0
    //   179: invokespecial 449	com/android/launcher3/model/GridSizeMigrationTask$MultiStepMigrationTask:<init>	(Ljava/util/HashSet;Landroid/content/Context;)V
    //   182: aload 10
    //   184: aload 9
    //   186: invokevirtual 453	com/android/launcher3/model/GridSizeMigrationTask$MultiStepMigrationTask:migrate	(Landroid/graphics/Point;Landroid/graphics/Point;)Z
    //   189: ifeq +6 -> 195
    //   192: iconst_1
    //   193: istore 4
    //   195: iload 4
    //   197: ifeq +51 -> 248
    //   200: aload_0
    //   201: invokevirtual 123	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   204: getstatic 170	com/android/launcher3/LauncherSettings$Favorites:CONTENT_URI	Landroid/net/Uri;
    //   207: aconst_null
    //   208: aconst_null
    //   209: aconst_null
    //   210: aconst_null
    //   211: invokevirtual 456	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   214: astore_0
    //   215: aload_0
    //   216: invokeinterface 230 1 0
    //   221: istore 4
    //   223: aload_0
    //   224: invokeinterface 251 1 0
    //   229: iload 4
    //   231: ifeq +6 -> 237
    //   234: goto +14 -> 248
    //   237: new 114	java/lang/Exception
    //   240: dup
    //   241: ldc_w 458
    //   244: invokespecial 349	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   247: athrow
    //   248: new 137	java/lang/StringBuilder
    //   251: dup
    //   252: invokespecial 138	java/lang/StringBuilder:<init>	()V
    //   255: astore_0
    //   256: aload_0
    //   257: ldc_w 460
    //   260: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   263: pop
    //   264: aload_0
    //   265: invokestatic 432	java/lang/System:currentTimeMillis	()J
    //   268: lload_2
    //   269: lsub
    //   270: invokevirtual 217	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   273: pop
    //   274: ldc -102
    //   276: aload_0
    //   277: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   280: invokestatic 463	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   283: pop
    //   284: aload 5
    //   286: invokeinterface 377 1 0
    //   291: ldc_w 379
    //   294: aload 7
    //   296: invokeinterface 387 3 0
    //   301: ldc_w 389
    //   304: aload 6
    //   306: getfield 423	com/android/launcher3/InvariantDeviceProfile:numHotseatIcons	I
    //   309: invokeinterface 393 3 0
    //   314: invokeinterface 396 1 0
    //   319: iconst_1
    //   320: ireturn
    //   321: astore_0
    //   322: goto +87 -> 409
    //   325: astore_0
    //   326: ldc -102
    //   328: ldc_w 465
    //   331: aload_0
    //   332: invokestatic 468	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   335: pop
    //   336: new 137	java/lang/StringBuilder
    //   339: dup
    //   340: invokespecial 138	java/lang/StringBuilder:<init>	()V
    //   343: astore_0
    //   344: aload_0
    //   345: ldc_w 460
    //   348: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   351: pop
    //   352: aload_0
    //   353: invokestatic 432	java/lang/System:currentTimeMillis	()J
    //   356: lload_2
    //   357: lsub
    //   358: invokevirtual 217	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   361: pop
    //   362: ldc -102
    //   364: aload_0
    //   365: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   368: invokestatic 463	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   371: pop
    //   372: aload 5
    //   374: invokeinterface 377 1 0
    //   379: ldc_w 379
    //   382: aload 7
    //   384: invokeinterface 387 3 0
    //   389: ldc_w 389
    //   392: aload 6
    //   394: getfield 423	com/android/launcher3/InvariantDeviceProfile:numHotseatIcons	I
    //   397: invokeinterface 393 3 0
    //   402: invokeinterface 396 1 0
    //   407: iconst_0
    //   408: ireturn
    //   409: new 137	java/lang/StringBuilder
    //   412: dup
    //   413: invokespecial 138	java/lang/StringBuilder:<init>	()V
    //   416: astore 8
    //   418: aload 8
    //   420: ldc_w 460
    //   423: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   426: pop
    //   427: aload 8
    //   429: invokestatic 432	java/lang/System:currentTimeMillis	()J
    //   432: lload_2
    //   433: lsub
    //   434: invokevirtual 217	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   437: pop
    //   438: ldc -102
    //   440: aload 8
    //   442: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   445: invokestatic 463	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   448: pop
    //   449: aload 5
    //   451: invokeinterface 377 1 0
    //   456: ldc_w 379
    //   459: aload 7
    //   461: invokeinterface 387 3 0
    //   466: ldc_w 389
    //   469: aload 6
    //   471: getfield 423	com/android/launcher3/InvariantDeviceProfile:numHotseatIcons	I
    //   474: invokeinterface 393 3 0
    //   479: invokeinterface 396 1 0
    //   484: aload_0
    //   485: athrow
    //   486: iconst_0
    //   487: istore 4
    //   489: goto -353 -> 136
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	492	0	paramContext	Context
    //   98	22	1	i	int
    //   76	357	2	l	long
    //   131	357	4	bool	boolean
    //   4	446	5	localSharedPreferences	SharedPreferences
    //   10	460	6	localInvariantDeviceProfile	InvariantDeviceProfile
    //   25	435	7	str	String
    //   81	360	8	localObject	Object
    //   153	32	9	localPoint1	Point
    //   170	13	10	localPoint2	Point
    // Exception table:
    //   from	to	target	type
    //   77	133	321	finally
    //   136	172	321	finally
    //   172	192	321	finally
    //   200	229	321	finally
    //   237	248	321	finally
    //   326	336	321	finally
    //   77	133	325	java/lang/Exception
    //   136	172	325	java/lang/Exception
    //   172	192	325	java/lang/Exception
    //   200	229	325	java/lang/Exception
    //   237	248	325	java/lang/Exception
  }
  
  private static Point parsePoint(String paramString)
  {
    paramString = paramString.split(",");
    return new Point(Integer.parseInt(paramString[0]), Integer.parseInt(paramString[1]));
  }
  
  public static LongArrayMap<Object> removeBrokenHotseatItems(Context paramContext)
    throws Exception
  {
    paramContext = new GridSizeMigrationTask(paramContext, LauncherAppState.getIDP(paramContext), getValidPackages(paramContext), Integer.MAX_VALUE, Integer.MAX_VALUE);
    Object localObject = paramContext.loadHotseatEntries();
    paramContext.applyOperations();
    paramContext = new LongArrayMap();
    localObject = ((ArrayList)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      DbEntry localDbEntry = (DbEntry)((Iterator)localObject).next();
      paramContext.put(localDbEntry.screenId, localDbEntry);
    }
    return paramContext;
  }
  
  private ArrayList<DbEntry> tryRemove(int paramInt1, int paramInt2, int paramInt3, ArrayList<DbEntry> paramArrayList, float[] paramArrayOfFloat)
  {
    GridOccupancy localGridOccupancy = new GridOccupancy(this.mTrgX, this.mTrgY);
    localGridOccupancy.markCells(0, 0, this.mTrgX, paramInt3, true);
    if (!this.mShouldRemoveX) {
      paramInt1 = Integer.MAX_VALUE;
    }
    if (!this.mShouldRemoveY) {
      paramInt2 = Integer.MAX_VALUE;
    }
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      DbEntry localDbEntry = (DbEntry)paramArrayList.next();
      if (((localDbEntry.cellX <= paramInt1) && (localDbEntry.spanX + localDbEntry.cellX > paramInt1)) || ((localDbEntry.cellY <= paramInt2) && (localDbEntry.spanY + localDbEntry.cellY > paramInt2)))
      {
        localArrayList2.add(localDbEntry);
        if (localDbEntry.cellX >= paramInt1) {
          localDbEntry.cellX -= 1;
        }
        if (localDbEntry.cellY >= paramInt2) {
          localDbEntry.cellY -= 1;
        }
      }
      else
      {
        if (localDbEntry.cellX > paramInt1) {
          localDbEntry.cellX -= 1;
        }
        if (localDbEntry.cellY > paramInt2) {
          localDbEntry.cellY -= 1;
        }
        localArrayList1.add(localDbEntry);
        localGridOccupancy.markCells(localDbEntry, true);
      }
    }
    paramArrayList = new OptimalPlacementSolution(localGridOccupancy, localArrayList2, paramInt3);
    paramArrayList.find();
    localArrayList1.addAll(paramArrayList.finalPlacedItems);
    paramArrayOfFloat[0] = paramArrayList.lowestWeightLoss;
    paramArrayOfFloat[1] = paramArrayList.lowestMoveCost;
    return localArrayList1;
  }
  
  private void verifyIntent(String paramString)
    throws Exception
  {
    paramString = Intent.parseUri(paramString, 0);
    if (paramString.getComponent() != null)
    {
      verifyPackage(paramString.getComponent().getPackageName());
      return;
    }
    if (paramString.getPackage() != null) {
      verifyPackage(paramString.getPackage());
    }
  }
  
  private void verifyPackage(String paramString)
    throws Exception
  {
    if (this.mValidPackages.contains(paramString)) {
      return;
    }
    throw new Exception("Package not available");
  }
  
  /* Error */
  protected ArrayList<DbEntry> loadWorkspaceEntries(long paramLong)
  {
    // Byte code:
    //   0: new 137	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 138	java/lang/StringBuilder:<init>	()V
    //   7: astore 15
    //   9: aload 15
    //   11: ldc_w 563
    //   14: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: pop
    //   18: aload 15
    //   20: lload_1
    //   21: invokevirtual 217	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   24: pop
    //   25: aload 15
    //   27: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   30: astore 15
    //   32: aload_0
    //   33: bipush 9
    //   35: anewarray 219	java/lang/String
    //   38: dup
    //   39: iconst_0
    //   40: ldc -84
    //   42: aastore
    //   43: dup
    //   44: iconst_1
    //   45: ldc_w 319
    //   48: aastore
    //   49: dup
    //   50: iconst_2
    //   51: ldc_w 564
    //   54: aastore
    //   55: dup
    //   56: iconst_3
    //   57: ldc_w 565
    //   60: aastore
    //   61: dup
    //   62: iconst_4
    //   63: ldc_w 566
    //   66: aastore
    //   67: dup
    //   68: iconst_5
    //   69: ldc_w 567
    //   72: aastore
    //   73: dup
    //   74: bipush 6
    //   76: ldc -35
    //   78: aastore
    //   79: dup
    //   80: bipush 7
    //   82: ldc_w 569
    //   85: aastore
    //   86: dup
    //   87: bipush 8
    //   89: ldc_w 571
    //   92: aastore
    //   93: aload 15
    //   95: invokevirtual 225	com/android/launcher3/model/GridSizeMigrationTask:queryWorkspace	([Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   98: astore 17
    //   100: aload 17
    //   102: ldc -84
    //   104: invokeinterface 331 2 0
    //   109: istore 5
    //   111: aload 17
    //   113: ldc_w 319
    //   116: invokeinterface 331 2 0
    //   121: istore 4
    //   123: aload 17
    //   125: ldc_w 564
    //   128: invokeinterface 331 2 0
    //   133: istore 8
    //   135: aload 17
    //   137: ldc_w 565
    //   140: invokeinterface 331 2 0
    //   145: istore 9
    //   147: aload 17
    //   149: ldc_w 566
    //   152: invokeinterface 331 2 0
    //   157: istore 10
    //   159: aload 17
    //   161: ldc_w 567
    //   164: invokeinterface 331 2 0
    //   169: istore 11
    //   171: aload 17
    //   173: ldc -35
    //   175: invokeinterface 331 2 0
    //   180: istore 12
    //   182: aload 17
    //   184: ldc_w 569
    //   187: invokeinterface 331 2 0
    //   192: istore 13
    //   194: aload 17
    //   196: ldc_w 571
    //   199: invokeinterface 331 2 0
    //   204: istore 6
    //   206: new 61	java/util/ArrayList
    //   209: dup
    //   210: invokespecial 62	java/util/ArrayList:<init>	()V
    //   213: astore 15
    //   215: aload 17
    //   217: invokeinterface 230 1 0
    //   222: ifeq +544 -> 766
    //   225: new 6	com/android/launcher3/model/GridSizeMigrationTask$DbEntry
    //   228: dup
    //   229: invokespecial 332	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:<init>	()V
    //   232: astore 18
    //   234: aload 18
    //   236: aload 17
    //   238: iload 5
    //   240: invokeinterface 242 2 0
    //   245: putfield 336	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:id	J
    //   248: aload 18
    //   250: aload 17
    //   252: iload 4
    //   254: invokeinterface 340 2 0
    //   259: putfield 342	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:itemType	I
    //   262: aload 18
    //   264: aload 17
    //   266: iload 8
    //   268: invokeinterface 340 2 0
    //   273: putfield 504	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:cellX	I
    //   276: aload 18
    //   278: aload 17
    //   280: iload 9
    //   282: invokeinterface 340 2 0
    //   287: putfield 510	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:cellY	I
    //   290: aload 18
    //   292: aload 17
    //   294: iload 10
    //   296: invokeinterface 340 2 0
    //   301: putfield 507	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:spanX	I
    //   304: aload 18
    //   306: aload 17
    //   308: iload 11
    //   310: invokeinterface 340 2 0
    //   315: putfield 513	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:spanY	I
    //   318: aload 18
    //   320: lload_1
    //   321: putfield 345	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:screenId	J
    //   324: aload 18
    //   326: getfield 342	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:itemType	I
    //   329: istore 7
    //   331: iload 7
    //   333: iconst_4
    //   334: if_icmpeq +128 -> 462
    //   337: iload 7
    //   339: bipush 6
    //   341: if_icmpeq +84 -> 425
    //   344: iload 7
    //   346: tableswitch	default:+430->776, 0:+79->425, 1:+79->425, 2:+37->383
    //   372: new 114	java/lang/Exception
    //   375: dup
    //   376: ldc_w 347
    //   379: invokespecial 349	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   382: athrow
    //   383: aload_0
    //   384: aload 18
    //   386: getfield 336	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:id	J
    //   389: invokespecial 351	com/android/launcher3/model/GridSizeMigrationTask:getFolderItemsCount	(J)I
    //   392: istore 7
    //   394: iload 7
    //   396: ifeq +18 -> 414
    //   399: aload 18
    //   401: ldc_w 352
    //   404: iload 7
    //   406: i2f
    //   407: fmul
    //   408: putfield 356	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:weight	F
    //   411: goto +368 -> 779
    //   414: new 114	java/lang/Exception
    //   417: dup
    //   418: ldc_w 358
    //   421: invokespecial 349	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   424: athrow
    //   425: aload_0
    //   426: aload 17
    //   428: iload 12
    //   430: invokeinterface 234 2 0
    //   435: invokespecial 238	com/android/launcher3/model/GridSizeMigrationTask:verifyIntent	(Ljava/lang/String;)V
    //   438: aload 18
    //   440: getfield 342	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:itemType	I
    //   443: ifne +339 -> 782
    //   446: ldc_w 359
    //   449: fstore_3
    //   450: goto +3 -> 453
    //   453: aload 18
    //   455: fload_3
    //   456: putfield 356	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:weight	F
    //   459: goto +320 -> 779
    //   462: aload_0
    //   463: aload 17
    //   465: iload 13
    //   467: invokeinterface 234 2 0
    //   472: invokestatic 575	android/content/ComponentName:unflattenFromString	(Ljava/lang/String;)Landroid/content/ComponentName;
    //   475: invokevirtual 548	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   478: invokespecial 551	com/android/launcher3/model/GridSizeMigrationTask:verifyPackage	(Ljava/lang/String;)V
    //   481: aload 18
    //   483: fconst_2
    //   484: ldc_w 576
    //   487: aload 18
    //   489: getfield 507	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:spanX	I
    //   492: i2f
    //   493: fmul
    //   494: aload 18
    //   496: getfield 513	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:spanY	I
    //   499: i2f
    //   500: fmul
    //   501: invokestatic 582	java/lang/Math:max	(FF)F
    //   504: putfield 356	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:weight	F
    //   507: aload 17
    //   509: iload 6
    //   511: invokeinterface 340 2 0
    //   516: istore 7
    //   518: aload_0
    //   519: getfield 70	com/android/launcher3/model/GridSizeMigrationTask:mContext	Landroid/content/Context;
    //   522: invokestatic 587	com/android/launcher3/compat/AppWidgetManagerCompat:getInstance	(Landroid/content/Context;)Lcom/android/launcher3/compat/AppWidgetManagerCompat;
    //   525: iload 7
    //   527: invokevirtual 591	com/android/launcher3/compat/AppWidgetManagerCompat:getLauncherAppWidgetInfo	(I)Lcom/android/launcher3/LauncherAppWidgetProviderInfo;
    //   530: astore 19
    //   532: aconst_null
    //   533: astore 16
    //   535: aload 19
    //   537: ifnull +18 -> 555
    //   540: aload 19
    //   542: aload_0
    //   543: getfield 72	com/android/launcher3/model/GridSizeMigrationTask:mIdp	Lcom/android/launcher3/InvariantDeviceProfile;
    //   546: aload_0
    //   547: getfield 70	com/android/launcher3/model/GridSizeMigrationTask:mContext	Landroid/content/Context;
    //   550: invokevirtual 597	com/android/launcher3/LauncherAppWidgetProviderInfo:getMinSpans	(Lcom/android/launcher3/InvariantDeviceProfile;Landroid/content/Context;)Landroid/graphics/Point;
    //   553: astore 16
    //   555: aload 16
    //   557: ifnull +70 -> 627
    //   560: aload 16
    //   562: getfield 98	android/graphics/Point:x	I
    //   565: ifle +13 -> 578
    //   568: aload 16
    //   570: getfield 98	android/graphics/Point:x	I
    //   573: istore 7
    //   575: goto +10 -> 585
    //   578: aload 18
    //   580: getfield 507	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:spanX	I
    //   583: istore 7
    //   585: aload 18
    //   587: iload 7
    //   589: putfield 600	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:minSpanX	I
    //   592: aload 16
    //   594: getfield 101	android/graphics/Point:y	I
    //   597: ifle +13 -> 610
    //   600: aload 16
    //   602: getfield 101	android/graphics/Point:y	I
    //   605: istore 7
    //   607: goto +10 -> 617
    //   610: aload 18
    //   612: getfield 513	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:spanY	I
    //   615: istore 7
    //   617: aload 18
    //   619: iload 7
    //   621: putfield 603	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:minSpanY	I
    //   624: goto +15 -> 639
    //   627: aload 18
    //   629: iconst_2
    //   630: putfield 603	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:minSpanY	I
    //   633: aload 18
    //   635: iconst_2
    //   636: putfield 600	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:minSpanX	I
    //   639: aload 18
    //   641: getfield 600	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:minSpanX	I
    //   644: aload_0
    //   645: getfield 82	com/android/launcher3/model/GridSizeMigrationTask:mTrgX	I
    //   648: if_icmpgt +34 -> 682
    //   651: aload 18
    //   653: getfield 603	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:minSpanY	I
    //   656: istore 7
    //   658: aload_0
    //   659: getfield 80	com/android/launcher3/model/GridSizeMigrationTask:mTrgY	I
    //   662: istore 14
    //   664: iload 7
    //   666: iload 14
    //   668: if_icmpgt +14 -> 682
    //   671: aload 15
    //   673: aload 18
    //   675: invokevirtual 209	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   678: pop
    //   679: goto +84 -> 763
    //   682: new 114	java/lang/Exception
    //   685: dup
    //   686: ldc_w 605
    //   689: invokespecial 349	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   692: athrow
    //   693: astore 16
    //   695: goto +10 -> 705
    //   698: astore 16
    //   700: goto +5 -> 705
    //   703: astore 16
    //   705: new 137	java/lang/StringBuilder
    //   708: dup
    //   709: invokespecial 138	java/lang/StringBuilder:<init>	()V
    //   712: astore 19
    //   714: aload 19
    //   716: ldc_w 361
    //   719: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   722: pop
    //   723: aload 19
    //   725: aload 18
    //   727: getfield 336	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:id	J
    //   730: invokevirtual 217	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   733: pop
    //   734: ldc -102
    //   736: aload 19
    //   738: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   741: aload 16
    //   743: invokestatic 364	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   746: pop
    //   747: aload_0
    //   748: getfield 64	com/android/launcher3/model/GridSizeMigrationTask:mEntryToRemove	Ljava/util/ArrayList;
    //   751: aload 18
    //   753: getfield 336	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:id	J
    //   756: invokestatic 248	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   759: invokevirtual 209	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   762: pop
    //   763: goto -548 -> 215
    //   766: aload 17
    //   768: invokeinterface 251 1 0
    //   773: aload 15
    //   775: areturn
    //   776: goto -404 -> 372
    //   779: goto -108 -> 671
    //   782: fconst_1
    //   783: fstore_3
    //   784: goto -331 -> 453
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	787	0	this	GridSizeMigrationTask
    //   0	787	1	paramLong	long
    //   449	335	3	f	float
    //   121	132	4	i	int
    //   109	130	5	j	int
    //   204	306	6	k	int
    //   329	340	7	m	int
    //   133	134	8	n	int
    //   145	136	9	i1	int
    //   157	138	10	i2	int
    //   169	140	11	i3	int
    //   180	249	12	i4	int
    //   192	274	13	i5	int
    //   662	7	14	i6	int
    //   7	767	15	localObject1	Object
    //   533	68	16	localPoint	Point
    //   693	1	16	localException1	Exception
    //   698	1	16	localException2	Exception
    //   703	39	16	localException3	Exception
    //   98	669	17	localCursor	Cursor
    //   232	520	18	localDbEntry	DbEntry
    //   530	207	19	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   682	693	693	java/lang/Exception
    //   507	532	698	java/lang/Exception
    //   540	555	698	java/lang/Exception
    //   560	575	698	java/lang/Exception
    //   578	585	698	java/lang/Exception
    //   585	607	698	java/lang/Exception
    //   610	617	698	java/lang/Exception
    //   617	624	698	java/lang/Exception
    //   627	639	698	java/lang/Exception
    //   639	664	698	java/lang/Exception
    //   324	331	703	java/lang/Exception
    //   372	383	703	java/lang/Exception
    //   383	394	703	java/lang/Exception
    //   399	411	703	java/lang/Exception
    //   414	425	703	java/lang/Exception
    //   425	446	703	java/lang/Exception
    //   453	459	703	java/lang/Exception
    //   462	507	703	java/lang/Exception
  }
  
  protected boolean migrateHotseat()
    throws Exception
  {
    ArrayList localArrayList = loadHotseatEntries();
    boolean bool = Utilities.isShowAllAppsPrefEnabled(this.mContext);
    if (!bool) {
      i = this.mDestHotseatSize;
    } else {
      i = this.mDestHotseatSize - 1;
    }
    DbEntry localDbEntry;
    while (localArrayList.size() > i)
    {
      localObject = (DbEntry)localArrayList.get(localArrayList.size() / 2);
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
      {
        localDbEntry = (DbEntry)localIterator.next();
        if (localDbEntry.weight < ((DbEntry)localObject).weight) {
          localObject = localDbEntry;
        }
      }
      this.mEntryToRemove.add(Long.valueOf(((DbEntry)localObject).id));
      localArrayList.remove(localObject);
    }
    Object localObject = localArrayList.iterator();
    int i = 0;
    while (((Iterator)localObject).hasNext())
    {
      localDbEntry = (DbEntry)((Iterator)localObject).next();
      long l1 = localDbEntry.screenId;
      long l2 = i;
      if (l1 != l2)
      {
        localDbEntry.screenId = l2;
        localDbEntry.cellX = i;
        localDbEntry.cellY = 0;
        update(localDbEntry);
      }
      int j = i + 1;
      i = j;
      if (bool)
      {
        i = j;
        if (this.mIdp.isAllAppsButtonRank(j)) {
          i = j + 1;
        }
      }
    }
    return applyOperations();
  }
  
  protected void migrateScreen(long paramLong)
  {
    Object localObject4 = loadWorkspaceEntries(paramLong);
    Object localObject5 = new float[2];
    int i = Integer.MAX_VALUE;
    Object localObject1 = null;
    float f1 = Float.MAX_VALUE;
    float f2 = f1;
    int j = Integer.MAX_VALUE;
    int k = 0;
    int m;
    int n;
    Object localObject2;
    float f3;
    for (;;)
    {
      m = j;
      n = i;
      localObject2 = localObject1;
      f3 = f1;
      if (k >= this.mSrcX) {
        break;
      }
      n = this.mSrcY;
      m = i;
      f3 = f1;
      f1 = f2;
      n -= 1;
      i = j;
      j = m;
      f2 = f3;
      m = n;
      float f4;
      int i1;
      for (;;)
      {
        f4 = f2;
        f3 = f1;
        i1 = j;
        n = i;
        localObject2 = localObject1;
        if (m < 0) {
          break;
        }
        localObject3 = tryRemove(k, m, 0, deepCopy((ArrayList)localObject4), (float[])localObject5);
        if (localObject5[0] >= f2)
        {
          f4 = f2;
          f3 = f1;
          i1 = j;
          n = i;
          localObject2 = localObject1;
          if (localObject5[0] == f2)
          {
            f4 = f2;
            f3 = f1;
            i1 = j;
            n = i;
            localObject2 = localObject1;
            if (localObject5[1] >= f1) {}
          }
        }
        else
        {
          f4 = localObject5[0];
          f3 = localObject5[1];
          if (this.mShouldRemoveX) {
            j = k;
          }
          if (this.mShouldRemoveY) {
            i = m;
          }
          localObject2 = localObject3;
          n = i;
          i1 = j;
        }
        if (!this.mShouldRemoveY) {
          break;
        }
        m -= 1;
        f2 = f4;
        f1 = f3;
        j = i1;
        i = n;
        localObject1 = localObject2;
      }
      f1 = f4;
      f2 = f3;
      i = i1;
      j = n;
      localObject1 = localObject2;
      if (!this.mShouldRemoveX)
      {
        m = j;
        n = i;
        localObject2 = localObject1;
        f3 = f1;
        break;
      }
      k += 1;
    }
    Log.d("GridSizeMigrationTask", String.format("Removing row %d, column %d on screen %d", new Object[] { Integer.valueOf(m), Integer.valueOf(n), Long.valueOf(paramLong) }));
    localObject1 = new LongArrayMap();
    Object localObject3 = deepCopy((ArrayList)localObject4).iterator();
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (DbEntry)((Iterator)localObject3).next();
      ((LongArrayMap)localObject1).put(((DbEntry)localObject4).id, localObject4);
    }
    localObject3 = ((ArrayList)localObject2).iterator();
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (DbEntry)((Iterator)localObject3).next();
      localObject5 = (DbEntry)((LongArrayMap)localObject1).get(((DbEntry)localObject4).id);
      ((LongArrayMap)localObject1).remove(((DbEntry)localObject4).id);
      if (!((DbEntry)localObject4).columnsSame((DbEntry)localObject5)) {
        update((DbEntry)localObject4);
      }
    }
    localObject1 = ((LongArrayMap)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject3 = (DbEntry)((Iterator)localObject1).next();
      this.mCarryOver.add(localObject3);
    }
    if ((!this.mCarryOver.isEmpty()) && (f3 == 0.0F))
    {
      localObject1 = new GridOccupancy(this.mTrgX, this.mTrgY);
      ((GridOccupancy)localObject1).markCells(0, 0, this.mTrgX, 0, true);
      localObject2 = ((ArrayList)localObject2).iterator();
      while (((Iterator)localObject2).hasNext()) {
        ((GridOccupancy)localObject1).markCells((DbEntry)((Iterator)localObject2).next(), true);
      }
      localObject1 = new OptimalPlacementSolution((GridOccupancy)localObject1, deepCopy(this.mCarryOver), 0, true);
      ((OptimalPlacementSolution)localObject1).find();
      if (((OptimalPlacementSolution)localObject1).lowestWeightLoss == 0.0F)
      {
        localObject1 = ((OptimalPlacementSolution)localObject1).finalPlacedItems.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (DbEntry)((Iterator)localObject1).next();
          ((DbEntry)localObject2).screenId = paramLong;
          update((DbEntry)localObject2);
        }
        this.mCarryOver.clear();
      }
    }
  }
  
  protected boolean migrateWorkspace()
    throws Exception
  {
    ArrayList localArrayList = LauncherModel.loadWorkspaceScreensDb(this.mContext);
    if (!localArrayList.isEmpty())
    {
      Object localObject1 = localArrayList.iterator();
      long l;
      Object localObject2;
      while (((Iterator)localObject1).hasNext())
      {
        l = ((Long)((Iterator)localObject1).next()).longValue();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Migrating ");
        ((StringBuilder)localObject2).append(l);
        Log.d("GridSizeMigrationTask", ((StringBuilder)localObject2).toString());
        migrateScreen(l);
      }
      if (!this.mCarryOver.isEmpty())
      {
        localObject1 = new LongArrayMap();
        localObject2 = this.mCarryOver.iterator();
        DbEntry localDbEntry;
        while (((Iterator)localObject2).hasNext())
        {
          localDbEntry = (DbEntry)((Iterator)localObject2).next();
          ((LongArrayMap)localObject1).put(localDbEntry.id, localDbEntry);
        }
        do
        {
          localObject2 = new OptimalPlacementSolution(new GridOccupancy(this.mTrgX, this.mTrgY), deepCopy(this.mCarryOver), 0, true);
          ((OptimalPlacementSolution)localObject2).find();
          if (((OptimalPlacementSolution)localObject2).finalPlacedItems.size() <= 0) {
            break;
          }
          l = LauncherSettings.Settings.call(this.mContext.getContentResolver(), "generate_new_screen_id").getLong("value");
          localArrayList.add(Long.valueOf(l));
          localObject2 = ((OptimalPlacementSolution)localObject2).finalPlacedItems.iterator();
          while (((Iterator)localObject2).hasNext())
          {
            localDbEntry = (DbEntry)((Iterator)localObject2).next();
            if (this.mCarryOver.remove(((LongArrayMap)localObject1).get(localDbEntry.id)))
            {
              localDbEntry.screenId = l;
              update(localDbEntry);
            }
            else
            {
              throw new Exception("Unable to find matching items");
            }
          }
        } while (!this.mCarryOver.isEmpty());
        localObject1 = LauncherSettings.WorkspaceScreens.CONTENT_URI;
        this.mUpdateOperations.add(ContentProviderOperation.newDelete((Uri)localObject1).build());
        int j = localArrayList.size();
        int i = 0;
        while (i < j)
        {
          localObject2 = new ContentValues();
          ((ContentValues)localObject2).put("_id", Long.valueOf(((Long)localArrayList.get(i)).longValue()));
          ((ContentValues)localObject2).put("screenRank", Integer.valueOf(i));
          this.mUpdateOperations.add(ContentProviderOperation.newInsert((Uri)localObject1).withValues((ContentValues)localObject2).build());
          i += 1;
        }
        throw new Exception("None of the items can be placed on an empty screen");
      }
      return applyOperations();
    }
    throw new Exception("Unable to get workspace screens");
  }
  
  protected Cursor queryWorkspace(String[] paramArrayOfString, String paramString)
  {
    return this.mContext.getContentResolver().query(LauncherSettings.Favorites.CONTENT_URI, paramArrayOfString, paramString, null, null, null);
  }
  
  protected void update(DbEntry paramDbEntry)
  {
    this.mTempValues.clear();
    paramDbEntry.addToContentValues(this.mTempValues);
    this.mUpdateOperations.add(ContentProviderOperation.newUpdate(LauncherSettings.Favorites.getContentUri(paramDbEntry.id)).withValues(this.mTempValues).build());
  }
  
  protected static class DbEntry
    extends ItemInfo
    implements Comparable<DbEntry>
  {
    public float weight;
    
    public DbEntry() {}
    
    public void addToContentValues(ContentValues paramContentValues)
    {
      paramContentValues.put("screen", Long.valueOf(this.screenId));
      paramContentValues.put("cellX", Integer.valueOf(this.cellX));
      paramContentValues.put("cellY", Integer.valueOf(this.cellY));
      paramContentValues.put("spanX", Integer.valueOf(this.spanX));
      paramContentValues.put("spanY", Integer.valueOf(this.spanY));
    }
    
    public boolean columnsSame(DbEntry paramDbEntry)
    {
      return (paramDbEntry.cellX == this.cellX) && (paramDbEntry.cellY == this.cellY) && (paramDbEntry.spanX == this.spanX) && (paramDbEntry.spanY == this.spanY) && (paramDbEntry.screenId == this.screenId);
    }
    
    public int compareTo(DbEntry paramDbEntry)
    {
      if (this.itemType == 4)
      {
        if (paramDbEntry.itemType == 4) {
          return paramDbEntry.spanY * paramDbEntry.spanX - this.spanX * this.spanY;
        }
        return -1;
      }
      if (paramDbEntry.itemType == 4) {
        return 1;
      }
      return Float.compare(paramDbEntry.weight, this.weight);
    }
    
    public DbEntry copy()
    {
      DbEntry localDbEntry = new DbEntry();
      localDbEntry.copyFrom(this);
      localDbEntry.weight = this.weight;
      localDbEntry.minSpanX = this.minSpanX;
      localDbEntry.minSpanY = this.minSpanY;
      return localDbEntry;
    }
  }
  
  protected static class MultiStepMigrationTask
  {
    private final Context mContext;
    private final HashSet<String> mValidPackages;
    
    public MultiStepMigrationTask(HashSet<String> paramHashSet, Context paramContext)
    {
      this.mValidPackages = paramHashSet;
      this.mContext = paramContext;
    }
    
    public boolean migrate(Point paramPoint1, Point paramPoint2)
      throws Exception
    {
      boolean bool1 = paramPoint2.equals(paramPoint1);
      boolean bool2 = false;
      boolean bool3 = false;
      if (!bool1)
      {
        if (paramPoint1.x < paramPoint2.x) {
          paramPoint1.x = paramPoint2.x;
        }
        bool1 = bool3;
        if (paramPoint1.y < paramPoint2.y)
        {
          paramPoint1.y = paramPoint2.y;
          bool1 = bool3;
        }
        for (;;)
        {
          bool2 = bool1;
          if (paramPoint2.equals(paramPoint1)) {
            break;
          }
          Point localPoint = new Point(paramPoint1);
          if (paramPoint2.x < localPoint.x) {
            localPoint.x -= 1;
          }
          if (paramPoint2.y < localPoint.y) {
            localPoint.y -= 1;
          }
          if (runStepTask(paramPoint1, localPoint)) {
            bool1 = true;
          }
          paramPoint1.set(localPoint.x, localPoint.y);
        }
      }
      return bool2;
    }
    
    protected boolean runStepTask(Point paramPoint1, Point paramPoint2)
      throws Exception
    {
      return new GridSizeMigrationTask(this.mContext, LauncherAppState.getIDP(this.mContext), this.mValidPackages, paramPoint1, paramPoint2).migrateWorkspace();
    }
  }
  
  private class OptimalPlacementSolution
  {
    ArrayList<GridSizeMigrationTask.DbEntry> finalPlacedItems;
    private final boolean ignoreMove;
    private final ArrayList<GridSizeMigrationTask.DbEntry> itemsToPlace;
    float lowestMoveCost = Float.MAX_VALUE;
    float lowestWeightLoss = Float.MAX_VALUE;
    private final GridOccupancy occupied;
    private final int startY;
    
    public OptimalPlacementSolution(ArrayList<GridSizeMigrationTask.DbEntry> paramArrayList, int paramInt)
    {
      this(paramArrayList, paramInt, i, false);
    }
    
    public OptimalPlacementSolution(ArrayList<GridSizeMigrationTask.DbEntry> paramArrayList, int paramInt, boolean paramBoolean)
    {
      this.occupied = paramArrayList;
      this.itemsToPlace = paramInt;
      boolean bool;
      this.ignoreMove = bool;
      this.startY = paramBoolean;
      Collections.sort(this.itemsToPlace);
    }
    
    public void find()
    {
      find(0, 0.0F, 0.0F, new ArrayList());
    }
    
    public void find(int paramInt, float paramFloat1, float paramFloat2, ArrayList<GridSizeMigrationTask.DbEntry> paramArrayList)
    {
      float f2 = paramFloat1;
      if (f2 < this.lowestWeightLoss)
      {
        if ((f2 == this.lowestWeightLoss) && (paramFloat2 >= this.lowestMoveCost)) {
          return;
        }
        if (paramInt >= this.itemsToPlace.size())
        {
          this.lowestWeightLoss = f2;
          this.lowestMoveCost = paramFloat2;
          this.finalPlacedItems = GridSizeMigrationTask.deepCopy(paramArrayList);
          return;
        }
        GridSizeMigrationTask.DbEntry localDbEntry = (GridSizeMigrationTask.DbEntry)this.itemsToPlace.get(paramInt);
        int i2 = localDbEntry.cellX;
        int i3 = localDbEntry.cellY;
        ArrayList localArrayList = new ArrayList(paramArrayList.size() + 1);
        localArrayList.addAll(paramArrayList);
        localArrayList.add(localDbEntry);
        int i;
        int k;
        int j;
        int m;
        float f1;
        if ((localDbEntry.spanX <= 1) && (localDbEntry.spanY <= 1))
        {
          i = this.startY;
          k = Integer.MAX_VALUE;
          int n = Integer.MAX_VALUE;
          int i1 = n;
          while (i < GridSizeMigrationTask.this.mTrgY)
          {
            j = 0;
            while (j < GridSizeMigrationTask.this.mTrgX)
            {
              if (this.occupied.cells[j][i] == 0)
              {
                if (this.ignoreMove) {
                  m = 0;
                } else {
                  m = (localDbEntry.cellX - j) * (localDbEntry.cellX - j) + (localDbEntry.cellY - i) * (localDbEntry.cellY - i);
                }
                if (m < i1)
                {
                  n = i;
                  k = j;
                  i1 = m;
                }
              }
              j += 1;
            }
            i += 1;
          }
          if ((k < GridSizeMigrationTask.this.mTrgX) && (n < GridSizeMigrationTask.this.mTrgY))
          {
            if (k != i2)
            {
              localDbEntry.cellX = k;
              paramFloat1 = paramFloat2 + 1.0F;
            }
            else
            {
              paramFloat1 = paramFloat2;
            }
            f1 = paramFloat1;
            if (n != i3)
            {
              localDbEntry.cellY = n;
              f1 = paramFloat1 + 1.0F;
            }
            paramFloat1 = f1;
            if (this.ignoreMove) {
              paramFloat1 = paramFloat2;
            }
            this.occupied.markCells(localDbEntry, true);
            paramInt += 1;
            find(paramInt, f2, paramFloat1, localArrayList);
            this.occupied.markCells(localDbEntry, false);
            localDbEntry.cellX = i2;
            localDbEntry.cellY = i3;
            if ((paramInt < this.itemsToPlace.size()) && (((GridSizeMigrationTask.DbEntry)this.itemsToPlace.get(paramInt)).weight >= localDbEntry.weight) && (!this.ignoreMove)) {
              find(paramInt, f2 + localDbEntry.weight, paramFloat2, paramArrayList);
            }
          }
          else
          {
            paramInt += 1;
            while (paramInt < this.itemsToPlace.size())
            {
              f2 += ((GridSizeMigrationTask.DbEntry)this.itemsToPlace.get(paramInt)).weight;
              paramInt += 1;
            }
            find(this.itemsToPlace.size(), f2 + localDbEntry.weight, paramFloat2, paramArrayList);
          }
        }
        else
        {
          j = localDbEntry.spanX;
          m = localDbEntry.spanY;
          i = this.startY;
          while (i < GridSizeMigrationTask.this.mTrgY)
          {
            k = 0;
            while (k < GridSizeMigrationTask.this.mTrgX)
            {
              if (k != i2)
              {
                localDbEntry.cellX = k;
                paramFloat1 = paramFloat2 + 1.0F;
              }
              else
              {
                paramFloat1 = paramFloat2;
              }
              f1 = paramFloat1;
              if (i != i3)
              {
                localDbEntry.cellY = i;
                f1 = paramFloat1 + 1.0F;
              }
              paramFloat1 = f1;
              if (this.ignoreMove) {
                paramFloat1 = paramFloat2;
              }
              if (this.occupied.isRegionVacant(k, i, j, m))
              {
                this.occupied.markCells(localDbEntry, true);
                find(paramInt + 1, f2, paramFloat1, localArrayList);
                this.occupied.markCells(localDbEntry, false);
              }
              if ((j > localDbEntry.minSpanX) && (this.occupied.isRegionVacant(k, i, j - 1, m)))
              {
                localDbEntry.spanX -= 1;
                this.occupied.markCells(localDbEntry, true);
                find(paramInt + 1, f2, paramFloat1 + 1.0F, localArrayList);
                this.occupied.markCells(localDbEntry, false);
                localDbEntry.spanX += 1;
              }
              if ((m > localDbEntry.minSpanY) && (this.occupied.isRegionVacant(k, i, j, m - 1)))
              {
                localDbEntry.spanY -= 1;
                this.occupied.markCells(localDbEntry, true);
                find(paramInt + 1, f2, paramFloat1 + 1.0F, localArrayList);
                this.occupied.markCells(localDbEntry, false);
                localDbEntry.spanY += 1;
              }
              if ((m > localDbEntry.minSpanY) && (j > localDbEntry.minSpanX) && (this.occupied.isRegionVacant(k, i, j - 1, m - 1)))
              {
                localDbEntry.spanX -= 1;
                localDbEntry.spanY -= 1;
                this.occupied.markCells(localDbEntry, true);
                find(paramInt + 1, f2, paramFloat1 + 2.0F, localArrayList);
                this.occupied.markCells(localDbEntry, false);
                localDbEntry.spanX += 1;
                localDbEntry.spanY += 1;
              }
              localDbEntry.cellX = i2;
              localDbEntry.cellY = i3;
              k += 1;
            }
            i += 1;
          }
          find(paramInt + 1, f2 + localDbEntry.weight, paramFloat2, paramArrayList);
        }
        return;
      }
    }
  }
}
