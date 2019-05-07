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
import co.madseven.launcher.settings.preferences.Preferences;
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
  private static final boolean DEBUG = true;
  public static boolean ENABLED = ;
  private static final String KEY_MIGRATION_SRC_HOTSEAT_COUNT = "migration_src_hotseat_count";
  private static final String KEY_MIGRATION_SRC_WORKSPACE_SIZE = "migration_src_workspace_size";
  private static final String TAG = "GridSizeMigrationTask";
  private static final float WT_APPLICATION = 0.8F;
  private static final float WT_FOLDER_FACTOR = 0.5F;
  private static final float WT_SHORTCUT = 1.0F;
  private static final float WT_WIDGET_FACTOR = 0.6F;
  private static final float WT_WIDGET_MIN = 2.0F;
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
  private final HashMap<String, Point> mWidgetMinSize = new HashMap();
  
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
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((localPackageInfo != null) && (localPackageInfo.packageName != null)) {
        localHashSet.add(localPackageInfo.packageName);
      }
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
        break label395;
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
            localDbEntry.weight = (n * 0.5F);
          } else {
            throw new Exception("Folder is empty");
          }
        case 0: 
        case 1: 
          verifyIntent(localCursor.getString(k));
          if (localDbEntry.itemType != 0) {
            break label408;
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
      label395:
      localCursor.close();
      return localArrayList;
      continue;
      label408:
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
    //   1: invokestatic 395	com/android/launcher3/Utilities:getPrefs	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   4: astore 5
    //   6: aload_0
    //   7: invokestatic 423	com/android/launcher3/LauncherAppState:getInstance	(Landroid/content/Context;)Lcom/android/launcher3/LauncherAppState;
    //   10: invokevirtual 427	com/android/launcher3/LauncherAppState:getInvariantDeviceProfile	()Lcom/android/launcher3/InvariantDeviceProfile;
    //   13: astore 6
    //   15: aload 6
    //   17: getfield 432	com/android/launcher3/InvariantDeviceProfile:numColumns	I
    //   20: aload 6
    //   22: getfield 435	com/android/launcher3/InvariantDeviceProfile:numRows	I
    //   25: invokestatic 403	com/android/launcher3/model/GridSizeMigrationTask:getPointString	(II)Ljava/lang/String;
    //   28: astore 7
    //   30: aload 7
    //   32: aload 5
    //   34: ldc 25
    //   36: ldc_w 437
    //   39: invokeinterface 440 3 0
    //   44: invokevirtual 443	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   47: ifeq +27 -> 74
    //   50: aload 6
    //   52: getfield 446	com/android/launcher3/InvariantDeviceProfile:numHotseatIcons	I
    //   55: aload 5
    //   57: ldc 22
    //   59: aload 6
    //   61: getfield 446	com/android/launcher3/InvariantDeviceProfile:numHotseatIcons	I
    //   64: invokeinterface 449 3 0
    //   69: if_icmpne +5 -> 74
    //   72: iconst_1
    //   73: ireturn
    //   74: invokestatic 455	java/lang/System:currentTimeMillis	()J
    //   77: lstore_2
    //   78: aload_0
    //   79: invokestatic 457	com/android/launcher3/model/GridSizeMigrationTask:getValidPackages	(Landroid/content/Context;)Ljava/util/HashSet;
    //   82: astore 8
    //   84: aload 5
    //   86: ldc 22
    //   88: aload 6
    //   90: getfield 446	com/android/launcher3/InvariantDeviceProfile:numHotseatIcons	I
    //   93: invokeinterface 449 3 0
    //   98: istore_1
    //   99: iload_1
    //   100: aload 6
    //   102: getfield 446	com/android/launcher3/InvariantDeviceProfile:numHotseatIcons	I
    //   105: if_icmpeq +377 -> 482
    //   108: new 2	com/android/launcher3/model/GridSizeMigrationTask
    //   111: dup
    //   112: aload_0
    //   113: aload_0
    //   114: invokestatic 423	com/android/launcher3/LauncherAppState:getInstance	(Landroid/content/Context;)Lcom/android/launcher3/LauncherAppState;
    //   117: invokevirtual 427	com/android/launcher3/LauncherAppState:getInvariantDeviceProfile	()Lcom/android/launcher3/InvariantDeviceProfile;
    //   120: aload 8
    //   122: iload_1
    //   123: aload 6
    //   125: getfield 446	com/android/launcher3/InvariantDeviceProfile:numHotseatIcons	I
    //   128: invokespecial 459	com/android/launcher3/model/GridSizeMigrationTask:<init>	(Landroid/content/Context;Lcom/android/launcher3/InvariantDeviceProfile;Ljava/util/HashSet;II)V
    //   131: invokevirtual 462	com/android/launcher3/model/GridSizeMigrationTask:migrateHotseat	()Z
    //   134: istore 4
    //   136: goto +3 -> 139
    //   139: new 128	android/graphics/Point
    //   142: dup
    //   143: aload 6
    //   145: getfield 432	com/android/launcher3/InvariantDeviceProfile:numColumns	I
    //   148: aload 6
    //   150: getfield 435	com/android/launcher3/InvariantDeviceProfile:numRows	I
    //   153: invokespecial 465	android/graphics/Point:<init>	(II)V
    //   156: astore 9
    //   158: aload 5
    //   160: ldc 25
    //   162: aload 7
    //   164: invokeinterface 440 3 0
    //   169: invokestatic 469	com/android/launcher3/model/GridSizeMigrationTask:parsePoint	(Ljava/lang/String;)Landroid/graphics/Point;
    //   172: astore 10
    //   174: new 9	com/android/launcher3/model/GridSizeMigrationTask$MultiStepMigrationTask
    //   177: dup
    //   178: aload 8
    //   180: aload_0
    //   181: invokespecial 472	com/android/launcher3/model/GridSizeMigrationTask$MultiStepMigrationTask:<init>	(Ljava/util/HashSet;Landroid/content/Context;)V
    //   184: aload 10
    //   186: aload 9
    //   188: invokevirtual 476	com/android/launcher3/model/GridSizeMigrationTask$MultiStepMigrationTask:migrate	(Landroid/graphics/Point;Landroid/graphics/Point;)Z
    //   191: ifeq +6 -> 197
    //   194: iconst_1
    //   195: istore 4
    //   197: iload 4
    //   199: ifeq +51 -> 250
    //   202: aload_0
    //   203: invokevirtual 155	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   206: getstatic 199	com/android/launcher3/LauncherSettings$Favorites:CONTENT_URI	Landroid/net/Uri;
    //   209: aconst_null
    //   210: aconst_null
    //   211: aconst_null
    //   212: aconst_null
    //   213: invokevirtual 479	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   216: astore_0
    //   217: aload_0
    //   218: invokeinterface 259 1 0
    //   223: istore 4
    //   225: aload_0
    //   226: invokeinterface 280 1 0
    //   231: iload 4
    //   233: ifeq +6 -> 239
    //   236: goto +14 -> 250
    //   239: new 146	java/lang/Exception
    //   242: dup
    //   243: ldc_w 481
    //   246: invokespecial 376	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   249: athrow
    //   250: new 168	java/lang/StringBuilder
    //   253: dup
    //   254: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   257: astore_0
    //   258: aload_0
    //   259: ldc_w 483
    //   262: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   265: pop
    //   266: aload_0
    //   267: invokestatic 455	java/lang/System:currentTimeMillis	()J
    //   270: lload_2
    //   271: lsub
    //   272: invokevirtual 246	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   275: pop
    //   276: ldc 28
    //   278: aload_0
    //   279: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   282: invokestatic 486	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   285: pop
    //   286: aload 5
    //   288: invokeinterface 401 1 0
    //   293: ldc 25
    //   295: aload 7
    //   297: invokeinterface 409 3 0
    //   302: ldc 22
    //   304: aload 6
    //   306: getfield 446	com/android/launcher3/InvariantDeviceProfile:numHotseatIcons	I
    //   309: invokeinterface 413 3 0
    //   314: invokeinterface 416 1 0
    //   319: iconst_1
    //   320: ireturn
    //   321: astore_0
    //   322: goto +85 -> 407
    //   325: astore_0
    //   326: ldc 28
    //   328: ldc_w 488
    //   331: aload_0
    //   332: invokestatic 491	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   335: pop
    //   336: new 168	java/lang/StringBuilder
    //   339: dup
    //   340: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   343: astore_0
    //   344: aload_0
    //   345: ldc_w 483
    //   348: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   351: pop
    //   352: aload_0
    //   353: invokestatic 455	java/lang/System:currentTimeMillis	()J
    //   356: lload_2
    //   357: lsub
    //   358: invokevirtual 246	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   361: pop
    //   362: ldc 28
    //   364: aload_0
    //   365: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   368: invokestatic 486	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   371: pop
    //   372: aload 5
    //   374: invokeinterface 401 1 0
    //   379: ldc 25
    //   381: aload 7
    //   383: invokeinterface 409 3 0
    //   388: ldc 22
    //   390: aload 6
    //   392: getfield 446	com/android/launcher3/InvariantDeviceProfile:numHotseatIcons	I
    //   395: invokeinterface 413 3 0
    //   400: invokeinterface 416 1 0
    //   405: iconst_0
    //   406: ireturn
    //   407: new 168	java/lang/StringBuilder
    //   410: dup
    //   411: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   414: astore 8
    //   416: aload 8
    //   418: ldc_w 483
    //   421: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   424: pop
    //   425: aload 8
    //   427: invokestatic 455	java/lang/System:currentTimeMillis	()J
    //   430: lload_2
    //   431: lsub
    //   432: invokevirtual 246	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   435: pop
    //   436: ldc 28
    //   438: aload 8
    //   440: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   443: invokestatic 486	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   446: pop
    //   447: aload 5
    //   449: invokeinterface 401 1 0
    //   454: ldc 25
    //   456: aload 7
    //   458: invokeinterface 409 3 0
    //   463: ldc 22
    //   465: aload 6
    //   467: getfield 446	com/android/launcher3/InvariantDeviceProfile:numHotseatIcons	I
    //   470: invokeinterface 413 3 0
    //   475: invokeinterface 416 1 0
    //   480: aload_0
    //   481: athrow
    //   482: iconst_0
    //   483: istore 4
    //   485: goto -346 -> 139
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	488	0	paramContext	Context
    //   98	25	1	i	int
    //   77	354	2	l	long
    //   134	350	4	bool	boolean
    //   4	444	5	localSharedPreferences	SharedPreferences
    //   13	453	6	localInvariantDeviceProfile	InvariantDeviceProfile
    //   28	429	7	str	String
    //   82	357	8	localObject	Object
    //   156	31	9	localPoint1	Point
    //   172	13	10	localPoint2	Point
    // Exception table:
    //   from	to	target	type
    //   78	136	321	finally
    //   139	174	321	finally
    //   174	194	321	finally
    //   202	231	321	finally
    //   239	250	321	finally
    //   326	336	321	finally
    //   78	136	325	java/lang/Exception
    //   139	174	325	java/lang/Exception
    //   174	194	325	java/lang/Exception
    //   202	231	325	java/lang/Exception
    //   239	250	325	java/lang/Exception
  }
  
  private static Point parsePoint(String paramString)
  {
    paramString = paramString.split(",");
    return new Point(Integer.parseInt(paramString[0]), Integer.parseInt(paramString[1]));
  }
  
  public static LongArrayMap<Object> removeBrokenHotseatItems(Context paramContext)
    throws Exception
  {
    paramContext = new GridSizeMigrationTask(paramContext, LauncherAppState.getInstance(paramContext).getInvariantDeviceProfile(), getValidPackages(paramContext), Integer.MAX_VALUE, Integer.MAX_VALUE);
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
    //   0: new 168	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   7: astore 15
    //   9: aload 15
    //   11: ldc_w 586
    //   14: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: pop
    //   18: aload 15
    //   20: lload_1
    //   21: invokevirtual 246	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   24: pop
    //   25: aload 15
    //   27: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   30: astore 15
    //   32: aload_0
    //   33: bipush 9
    //   35: anewarray 248	java/lang/String
    //   38: dup
    //   39: iconst_0
    //   40: ldc -55
    //   42: aastore
    //   43: dup
    //   44: iconst_1
    //   45: ldc_w 346
    //   48: aastore
    //   49: dup
    //   50: iconst_2
    //   51: ldc_w 587
    //   54: aastore
    //   55: dup
    //   56: iconst_3
    //   57: ldc_w 588
    //   60: aastore
    //   61: dup
    //   62: iconst_4
    //   63: ldc_w 589
    //   66: aastore
    //   67: dup
    //   68: iconst_5
    //   69: ldc_w 590
    //   72: aastore
    //   73: dup
    //   74: bipush 6
    //   76: ldc -6
    //   78: aastore
    //   79: dup
    //   80: bipush 7
    //   82: ldc_w 592
    //   85: aastore
    //   86: dup
    //   87: bipush 8
    //   89: ldc_w 594
    //   92: aastore
    //   93: aload 15
    //   95: invokevirtual 254	com/android/launcher3/model/GridSizeMigrationTask:queryWorkspace	([Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   98: astore 17
    //   100: aload 17
    //   102: ldc -55
    //   104: invokeinterface 358 2 0
    //   109: istore 5
    //   111: aload 17
    //   113: ldc_w 346
    //   116: invokeinterface 358 2 0
    //   121: istore 4
    //   123: aload 17
    //   125: ldc_w 587
    //   128: invokeinterface 358 2 0
    //   133: istore 8
    //   135: aload 17
    //   137: ldc_w 588
    //   140: invokeinterface 358 2 0
    //   145: istore 9
    //   147: aload 17
    //   149: ldc_w 589
    //   152: invokeinterface 358 2 0
    //   157: istore 10
    //   159: aload 17
    //   161: ldc_w 590
    //   164: invokeinterface 358 2 0
    //   169: istore 11
    //   171: aload 17
    //   173: ldc -6
    //   175: invokeinterface 358 2 0
    //   180: istore 12
    //   182: aload 17
    //   184: ldc_w 592
    //   187: invokeinterface 358 2 0
    //   192: istore 13
    //   194: aload 17
    //   196: ldc_w 594
    //   199: invokeinterface 358 2 0
    //   204: istore 6
    //   206: new 94	java/util/ArrayList
    //   209: dup
    //   210: invokespecial 95	java/util/ArrayList:<init>	()V
    //   213: astore 15
    //   215: aload 17
    //   217: invokeinterface 259 1 0
    //   222: ifeq +559 -> 781
    //   225: new 6	com/android/launcher3/model/GridSizeMigrationTask$DbEntry
    //   228: dup
    //   229: invokespecial 359	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:<init>	()V
    //   232: astore 18
    //   234: aload 18
    //   236: aload 17
    //   238: iload 5
    //   240: invokeinterface 271 2 0
    //   245: putfield 363	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:id	J
    //   248: aload 18
    //   250: aload 17
    //   252: iload 4
    //   254: invokeinterface 367 2 0
    //   259: putfield 369	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:itemType	I
    //   262: aload 18
    //   264: aload 17
    //   266: iload 8
    //   268: invokeinterface 367 2 0
    //   273: putfield 527	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:cellX	I
    //   276: aload 18
    //   278: aload 17
    //   280: iload 9
    //   282: invokeinterface 367 2 0
    //   287: putfield 533	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:cellY	I
    //   290: aload 18
    //   292: aload 17
    //   294: iload 10
    //   296: invokeinterface 367 2 0
    //   301: putfield 530	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:spanX	I
    //   304: aload 18
    //   306: aload 17
    //   308: iload 11
    //   310: invokeinterface 367 2 0
    //   315: putfield 536	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:spanY	I
    //   318: aload 18
    //   320: lload_1
    //   321: putfield 372	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:screenId	J
    //   324: aload 18
    //   326: getfield 369	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:itemType	I
    //   329: istore 7
    //   331: iload 7
    //   333: iconst_4
    //   334: if_icmpeq +126 -> 460
    //   337: iload 7
    //   339: bipush 6
    //   341: if_icmpeq +83 -> 424
    //   344: iload 7
    //   346: tableswitch	default:+445->791, 0:+78->424, 1:+78->424, 2:+37->383
    //   372: new 146	java/lang/Exception
    //   375: dup
    //   376: ldc_w 374
    //   379: invokespecial 376	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   382: athrow
    //   383: aload_0
    //   384: aload 18
    //   386: getfield 363	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:id	J
    //   389: invokespecial 378	com/android/launcher3/model/GridSizeMigrationTask:getFolderItemsCount	(J)I
    //   392: istore 7
    //   394: iload 7
    //   396: ifeq +17 -> 413
    //   399: aload 18
    //   401: iload 7
    //   403: i2f
    //   404: ldc 33
    //   406: fmul
    //   407: putfield 381	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:weight	F
    //   410: goto +384 -> 794
    //   413: new 146	java/lang/Exception
    //   416: dup
    //   417: ldc_w 383
    //   420: invokespecial 376	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   423: athrow
    //   424: aload_0
    //   425: aload 17
    //   427: iload 12
    //   429: invokeinterface 263 2 0
    //   434: invokespecial 267	com/android/launcher3/model/GridSizeMigrationTask:verifyIntent	(Ljava/lang/String;)V
    //   437: aload 18
    //   439: getfield 369	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:itemType	I
    //   442: ifne +355 -> 797
    //   445: ldc 31
    //   447: fstore_3
    //   448: goto +3 -> 451
    //   451: aload 18
    //   453: fload_3
    //   454: putfield 381	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:weight	F
    //   457: goto +337 -> 794
    //   460: aload 17
    //   462: iload 13
    //   464: invokeinterface 263 2 0
    //   469: astore 16
    //   471: aload_0
    //   472: aload 16
    //   474: invokestatic 598	android/content/ComponentName:unflattenFromString	(Ljava/lang/String;)Landroid/content/ComponentName;
    //   477: invokevirtual 571	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   480: invokespecial 574	com/android/launcher3/model/GridSizeMigrationTask:verifyPackage	(Ljava/lang/String;)V
    //   483: aload 18
    //   485: fconst_2
    //   486: aload 18
    //   488: getfield 530	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:spanX	I
    //   491: i2f
    //   492: ldc 37
    //   494: fmul
    //   495: aload 18
    //   497: getfield 536	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:spanY	I
    //   500: i2f
    //   501: fmul
    //   502: invokestatic 604	java/lang/Math:max	(FF)F
    //   505: putfield 381	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:weight	F
    //   508: aload 17
    //   510: iload 6
    //   512: invokeinterface 367 2 0
    //   517: istore 7
    //   519: aload_0
    //   520: getfield 103	com/android/launcher3/model/GridSizeMigrationTask:mContext	Landroid/content/Context;
    //   523: invokestatic 609	com/android/launcher3/compat/AppWidgetManagerCompat:getInstance	(Landroid/content/Context;)Lcom/android/launcher3/compat/AppWidgetManagerCompat;
    //   526: iload 7
    //   528: invokevirtual 613	com/android/launcher3/compat/AppWidgetManagerCompat:getLauncherAppWidgetInfo	(I)Lcom/android/launcher3/LauncherAppWidgetProviderInfo;
    //   531: astore 19
    //   533: aload 19
    //   535: ifnonnull +20 -> 555
    //   538: aload_0
    //   539: getfield 87	com/android/launcher3/model/GridSizeMigrationTask:mWidgetMinSize	Ljava/util/HashMap;
    //   542: aload 16
    //   544: invokevirtual 617	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   547: checkcast 128	android/graphics/Point
    //   550: astore 16
    //   552: goto +18 -> 570
    //   555: aload 19
    //   557: aload_0
    //   558: getfield 105	com/android/launcher3/model/GridSizeMigrationTask:mIdp	Lcom/android/launcher3/InvariantDeviceProfile;
    //   561: aload_0
    //   562: getfield 103	com/android/launcher3/model/GridSizeMigrationTask:mContext	Landroid/content/Context;
    //   565: invokevirtual 623	com/android/launcher3/LauncherAppWidgetProviderInfo:getMinSpans	(Lcom/android/launcher3/InvariantDeviceProfile;Landroid/content/Context;)Landroid/graphics/Point;
    //   568: astore 16
    //   570: aload 16
    //   572: ifnull +70 -> 642
    //   575: aload 16
    //   577: getfield 131	android/graphics/Point:x	I
    //   580: ifle +13 -> 593
    //   583: aload 16
    //   585: getfield 131	android/graphics/Point:x	I
    //   588: istore 7
    //   590: goto +10 -> 600
    //   593: aload 18
    //   595: getfield 530	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:spanX	I
    //   598: istore 7
    //   600: aload 18
    //   602: iload 7
    //   604: putfield 626	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:minSpanX	I
    //   607: aload 16
    //   609: getfield 134	android/graphics/Point:y	I
    //   612: ifle +13 -> 625
    //   615: aload 16
    //   617: getfield 134	android/graphics/Point:y	I
    //   620: istore 7
    //   622: goto +10 -> 632
    //   625: aload 18
    //   627: getfield 536	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:spanY	I
    //   630: istore 7
    //   632: aload 18
    //   634: iload 7
    //   636: putfield 629	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:minSpanY	I
    //   639: goto +15 -> 654
    //   642: aload 18
    //   644: iconst_2
    //   645: putfield 629	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:minSpanY	I
    //   648: aload 18
    //   650: iconst_2
    //   651: putfield 626	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:minSpanX	I
    //   654: aload 18
    //   656: getfield 626	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:minSpanX	I
    //   659: aload_0
    //   660: getfield 115	com/android/launcher3/model/GridSizeMigrationTask:mTrgX	I
    //   663: if_icmpgt +34 -> 697
    //   666: aload 18
    //   668: getfield 629	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:minSpanY	I
    //   671: istore 7
    //   673: aload_0
    //   674: getfield 113	com/android/launcher3/model/GridSizeMigrationTask:mTrgY	I
    //   677: istore 14
    //   679: iload 7
    //   681: iload 14
    //   683: if_icmpgt +14 -> 697
    //   686: aload 15
    //   688: aload 18
    //   690: invokevirtual 238	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   693: pop
    //   694: goto +84 -> 778
    //   697: new 146	java/lang/Exception
    //   700: dup
    //   701: ldc_w 631
    //   704: invokespecial 376	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   707: athrow
    //   708: astore 16
    //   710: goto +10 -> 720
    //   713: astore 16
    //   715: goto +5 -> 720
    //   718: astore 16
    //   720: new 168	java/lang/StringBuilder
    //   723: dup
    //   724: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   727: astore 19
    //   729: aload 19
    //   731: ldc_w 385
    //   734: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   737: pop
    //   738: aload 19
    //   740: aload 18
    //   742: getfield 363	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:id	J
    //   745: invokevirtual 246	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   748: pop
    //   749: ldc 28
    //   751: aload 19
    //   753: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   756: aload 16
    //   758: invokestatic 388	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   761: pop
    //   762: aload_0
    //   763: getfield 97	com/android/launcher3/model/GridSizeMigrationTask:mEntryToRemove	Ljava/util/ArrayList;
    //   766: aload 18
    //   768: getfield 363	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:id	J
    //   771: invokestatic 277	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   774: invokevirtual 238	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   777: pop
    //   778: goto -563 -> 215
    //   781: aload 17
    //   783: invokeinterface 280 1 0
    //   788: aload 15
    //   790: areturn
    //   791: goto -419 -> 372
    //   794: goto -108 -> 686
    //   797: fconst_1
    //   798: fstore_3
    //   799: goto -348 -> 451
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	802	0	this	GridSizeMigrationTask
    //   0	802	1	paramLong	long
    //   447	352	3	f	float
    //   121	132	4	i	int
    //   109	130	5	j	int
    //   204	307	6	k	int
    //   329	355	7	m	int
    //   133	134	8	n	int
    //   145	136	9	i1	int
    //   157	138	10	i2	int
    //   169	140	11	i3	int
    //   180	248	12	i4	int
    //   192	271	13	i5	int
    //   677	7	14	i6	int
    //   7	782	15	localObject1	Object
    //   469	147	16	localObject2	Object
    //   708	1	16	localException1	Exception
    //   713	1	16	localException2	Exception
    //   718	39	16	localException3	Exception
    //   98	684	17	localCursor	Cursor
    //   232	535	18	localDbEntry	DbEntry
    //   531	221	19	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   697	708	708	java/lang/Exception
    //   508	533	713	java/lang/Exception
    //   538	552	713	java/lang/Exception
    //   555	570	713	java/lang/Exception
    //   575	590	713	java/lang/Exception
    //   593	600	713	java/lang/Exception
    //   600	622	713	java/lang/Exception
    //   625	632	713	java/lang/Exception
    //   632	639	713	java/lang/Exception
    //   642	654	713	java/lang/Exception
    //   654	679	713	java/lang/Exception
    //   324	331	718	java/lang/Exception
    //   372	383	718	java/lang/Exception
    //   383	394	718	java/lang/Exception
    //   399	410	718	java/lang/Exception
    //   413	424	718	java/lang/Exception
    //   424	445	718	java/lang/Exception
    //   451	457	718	java/lang/Exception
    //   460	508	718	java/lang/Exception
  }
  
  protected boolean migrateHotseat()
    throws Exception
  {
    ArrayList localArrayList = loadHotseatEntries();
    if (!Preferences.getInstance().getDockDisplayAllAppsIcon(this.mContext)) {
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
      if (Preferences.getInstance().getDockDisplayAllAppsIcon(this.mContext))
      {
        i = j;
        if (this.mIdp.isAllAppsButtonRank(j, this.mContext)) {
          i = j + 1;
        }
      }
    }
    return applyOperations();
  }
  
  protected void migrateScreen(long paramLong)
  {
    int i2;
    if (paramLong == 0L) {
      i2 = 1;
    } else {
      i2 = 0;
    }
    Object localObject4 = loadWorkspaceEntries(paramLong);
    Object localObject5 = new float[2];
    int i = Integer.MAX_VALUE;
    Object localObject1 = null;
    int j = Integer.MAX_VALUE;
    float f1 = Float.MAX_VALUE;
    float f2 = Float.MAX_VALUE;
    int k = 0;
    int m;
    Object localObject2;
    int n;
    float f3;
    for (;;)
    {
      m = j;
      localObject2 = localObject1;
      n = i;
      f3 = f1;
      if (k >= this.mSrcX) {
        break;
      }
      n = this.mSrcY - 1;
      m = i;
      f3 = f1;
      f1 = f2;
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
        if (m < i2) {
          break;
        }
        localObject3 = tryRemove(k, m, i2, deepCopy((ArrayList)localObject4), (float[])localObject5);
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
        localObject2 = localObject1;
        n = i;
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
      ((GridOccupancy)localObject1).markCells(0, 0, this.mTrgX, i2, true);
      localObject2 = ((ArrayList)localObject2).iterator();
      while (((Iterator)localObject2).hasNext()) {
        ((GridOccupancy)localObject1).markCells((DbEntry)((Iterator)localObject2).next(), true);
      }
      localObject1 = new OptimalPlacementSolution((GridOccupancy)localObject1, deepCopy(this.mCarryOver), i2, true);
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
      return new GridSizeMigrationTask(this.mContext, LauncherAppState.getInstance(this.mContext).getInvariantDeviceProfile(), this.mValidPackages, paramPoint1, paramPoint2).migrateWorkspace();
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
          int i1 = Integer.MAX_VALUE;
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
          paramFloat1 = localDbEntry.weight;
        }
      }
      try
      {
        find(paramInt + 1, f2 + paramFloat1, paramFloat2, paramArrayList);
        return;
      }
      catch (Throwable paramArrayList)
      {
        throw paramArrayList;
      }
    }
  }
}
