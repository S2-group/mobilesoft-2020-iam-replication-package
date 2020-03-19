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
import com.android.launcher3.config.FeatureFlags;
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
  
  private GridSizeMigrationTask(Context paramContext, InvariantDeviceProfile paramInvariantDeviceProfile, HashSet<String> paramHashSet, int paramInt1, int paramInt2)
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
  {
    if (!this.mUpdateOperations.isEmpty()) {
      this.mContext.getContentResolver().applyBatch(LauncherProvider.AUTHORITY, this.mUpdateOperations);
    }
    if (!this.mEntryToRemove.isEmpty())
    {
      StringBuilder localStringBuilder = new StringBuilder("Removing items: ");
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
    Object localObject = new StringBuilder("container = ");
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
        break label375;
      }
      localDbEntry = new DbEntry();
      localDbEntry.id = localCursor.getLong(i);
      localDbEntry.itemType = localCursor.getInt(j);
      localDbEntry.screenId = localCursor.getLong(m);
      if (localDbEntry.screenId < this.mSrcHotseatSize) {
        break;
      }
      label173:
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
          if (n == 0) {
            throw new Exception("Folder is empty");
          }
          localDbEntry.weight = (n * 0.5F);
          break;
        case 0: 
        case 1: 
          verifyIntent(localCursor.getString(k));
          if (localDbEntry.itemType != 0) {
            break label388;
          }
          f = 0.8F;
          localDbEntry.weight = f;
          localArrayList.add(localDbEntry);
        }
      }
      catch (Exception localException)
      {
        StringBuilder localStringBuilder = new StringBuilder("Removing item ");
        localStringBuilder.append(localDbEntry.id);
        Log.d("GridSizeMigrationTask", localStringBuilder.toString(), localException);
      }
      break label173;
      label375:
      localCursor.close();
      return localArrayList;
      continue;
      label388:
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
    //   1: invokestatic 370	com/android/launcher3/Utilities:getPrefs	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   4: astore 12
    //   6: aload_0
    //   7: invokestatic 402	com/android/launcher3/LauncherAppState:getInstance	(Landroid/content/Context;)Lcom/android/launcher3/LauncherAppState;
    //   10: getfield 405	com/android/launcher3/LauncherAppState:mInvariantDeviceProfile	Lcom/android/launcher3/InvariantDeviceProfile;
    //   13: astore 13
    //   15: aload 13
    //   17: getfield 410	com/android/launcher3/InvariantDeviceProfile:numColumns	I
    //   20: aload 13
    //   22: getfield 413	com/android/launcher3/InvariantDeviceProfile:numRows	I
    //   25: invokestatic 380	com/android/launcher3/model/GridSizeMigrationTask:getPointString	(II)Ljava/lang/String;
    //   28: astore 14
    //   30: aload 14
    //   32: aload 12
    //   34: ldc_w 378
    //   37: ldc_w 415
    //   40: invokeinterface 418 3 0
    //   45: invokevirtual 421	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   48: ifeq +28 -> 76
    //   51: aload 13
    //   53: getfield 424	com/android/launcher3/InvariantDeviceProfile:numHotseatIcons	I
    //   56: aload 12
    //   58: ldc_w 388
    //   61: aload 13
    //   63: getfield 424	com/android/launcher3/InvariantDeviceProfile:numHotseatIcons	I
    //   66: invokeinterface 427 3 0
    //   71: if_icmpne +5 -> 76
    //   74: iconst_1
    //   75: ireturn
    //   76: invokestatic 433	java/lang/System:currentTimeMillis	()J
    //   79: lstore_2
    //   80: aload_0
    //   81: invokestatic 435	com/android/launcher3/model/GridSizeMigrationTask:getValidPackages	(Landroid/content/Context;)Ljava/util/HashSet;
    //   84: astore 15
    //   86: aload 12
    //   88: ldc_w 388
    //   91: aload 13
    //   93: getfield 424	com/android/launcher3/InvariantDeviceProfile:numHotseatIcons	I
    //   96: invokeinterface 427 3 0
    //   101: istore_1
    //   102: iload_1
    //   103: aload 13
    //   105: getfield 424	com/android/launcher3/InvariantDeviceProfile:numHotseatIcons	I
    //   108: if_icmpeq +609 -> 717
    //   111: new 2	com/android/launcher3/model/GridSizeMigrationTask
    //   114: dup
    //   115: aload_0
    //   116: aload_0
    //   117: invokestatic 402	com/android/launcher3/LauncherAppState:getInstance	(Landroid/content/Context;)Lcom/android/launcher3/LauncherAppState;
    //   120: getfield 405	com/android/launcher3/LauncherAppState:mInvariantDeviceProfile	Lcom/android/launcher3/InvariantDeviceProfile;
    //   123: aload 15
    //   125: iload_1
    //   126: aload 13
    //   128: getfield 424	com/android/launcher3/InvariantDeviceProfile:numHotseatIcons	I
    //   131: invokespecial 437	com/android/launcher3/model/GridSizeMigrationTask:<init>	(Landroid/content/Context;Lcom/android/launcher3/InvariantDeviceProfile;Ljava/util/HashSet;II)V
    //   134: astore 16
    //   136: aload 16
    //   138: invokespecial 439	com/android/launcher3/model/GridSizeMigrationTask:loadHotseatEntries	()Ljava/util/ArrayList;
    //   141: astore 17
    //   143: aload 16
    //   145: getfield 78	com/android/launcher3/model/GridSizeMigrationTask:mDestHotseatSize	I
    //   148: istore_1
    //   149: aload 17
    //   151: invokevirtual 184	java/util/ArrayList:size	()I
    //   154: iload_1
    //   155: if_icmple +102 -> 257
    //   158: aload 17
    //   160: aload 17
    //   162: invokevirtual 184	java/util/ArrayList:size	()I
    //   165: iconst_2
    //   166: idiv
    //   167: invokevirtual 443	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   170: checkcast 6	com/android/launcher3/model/GridSizeMigrationTask$DbEntry
    //   173: astore 9
    //   175: aload 17
    //   177: invokevirtual 191	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   180: astore 18
    //   182: aload 18
    //   184: invokeinterface 196 1 0
    //   189: ifeq +40 -> 229
    //   192: aload 18
    //   194: invokeinterface 200 1 0
    //   199: checkcast 6	com/android/launcher3/model/GridSizeMigrationTask$DbEntry
    //   202: astore 11
    //   204: aload 9
    //   206: astore 10
    //   208: aload 11
    //   210: getfield 357	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:weight	F
    //   213: aload 9
    //   215: getfield 357	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:weight	F
    //   218: fcmpg
    //   219: ifge +484 -> 703
    //   222: aload 11
    //   224: astore 10
    //   226: goto +477 -> 703
    //   229: aload 16
    //   231: getfield 64	com/android/launcher3/model/GridSizeMigrationTask:mEntryToRemove	Ljava/util/ArrayList;
    //   234: aload 9
    //   236: getfield 336	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:id	J
    //   239: invokestatic 248	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   242: invokevirtual 208	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   245: pop
    //   246: aload 17
    //   248: aload 9
    //   250: invokevirtual 446	java/util/ArrayList:remove	(Ljava/lang/Object;)Z
    //   253: pop
    //   254: goto -105 -> 149
    //   257: aload 17
    //   259: invokevirtual 191	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   262: astore 9
    //   264: iconst_0
    //   265: istore_1
    //   266: aload 9
    //   268: invokeinterface 196 1 0
    //   273: ifeq +63 -> 336
    //   276: aload 9
    //   278: invokeinterface 200 1 0
    //   283: checkcast 6	com/android/launcher3/model/GridSizeMigrationTask$DbEntry
    //   286: astore 10
    //   288: aload 10
    //   290: getfield 345	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:screenId	J
    //   293: lstore 4
    //   295: iload_1
    //   296: i2l
    //   297: lstore 6
    //   299: lload 4
    //   301: lload 6
    //   303: lcmp
    //   304: ifeq +406 -> 710
    //   307: aload 10
    //   309: lload 6
    //   311: putfield 345	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:screenId	J
    //   314: aload 10
    //   316: iload_1
    //   317: putfield 449	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:cellX	I
    //   320: aload 10
    //   322: iconst_0
    //   323: putfield 452	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:cellY	I
    //   326: aload 16
    //   328: aload 10
    //   330: invokevirtual 456	com/android/launcher3/model/GridSizeMigrationTask:update	(Lcom/android/launcher3/model/GridSizeMigrationTask$DbEntry;)V
    //   333: goto +377 -> 710
    //   336: aload 16
    //   338: invokespecial 458	com/android/launcher3/model/GridSizeMigrationTask:applyOperations	()Z
    //   341: istore 8
    //   343: goto +3 -> 346
    //   346: new 95	android/graphics/Point
    //   349: dup
    //   350: aload 13
    //   352: getfield 410	com/android/launcher3/InvariantDeviceProfile:numColumns	I
    //   355: aload 13
    //   357: getfield 413	com/android/launcher3/InvariantDeviceProfile:numRows	I
    //   360: invokespecial 461	android/graphics/Point:<init>	(II)V
    //   363: astore 9
    //   365: aload 12
    //   367: ldc_w 378
    //   370: aload 14
    //   372: invokeinterface 418 3 0
    //   377: ldc_w 463
    //   380: invokevirtual 467	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   383: astore 10
    //   385: new 95	android/graphics/Point
    //   388: dup
    //   389: aload 10
    //   391: iconst_0
    //   392: aaload
    //   393: invokestatic 470	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   396: aload 10
    //   398: iconst_1
    //   399: aaload
    //   400: invokestatic 470	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   403: invokespecial 461	android/graphics/Point:<init>	(II)V
    //   406: astore 10
    //   408: new 9	com/android/launcher3/model/GridSizeMigrationTask$MultiStepMigrationTask
    //   411: dup
    //   412: aload 15
    //   414: aload_0
    //   415: invokespecial 473	com/android/launcher3/model/GridSizeMigrationTask$MultiStepMigrationTask:<init>	(Ljava/util/HashSet;Landroid/content/Context;)V
    //   418: aload 10
    //   420: aload 9
    //   422: invokevirtual 477	com/android/launcher3/model/GridSizeMigrationTask$MultiStepMigrationTask:migrate	(Landroid/graphics/Point;Landroid/graphics/Point;)Z
    //   425: ifeq +6 -> 431
    //   428: iconst_1
    //   429: istore 8
    //   431: iload 8
    //   433: ifeq +48 -> 481
    //   436: aload_0
    //   437: invokevirtual 121	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   440: getstatic 170	com/android/launcher3/LauncherSettings$Favorites:CONTENT_URI	Landroid/net/Uri;
    //   443: aconst_null
    //   444: aconst_null
    //   445: aconst_null
    //   446: aconst_null
    //   447: invokevirtual 480	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   450: astore_0
    //   451: aload_0
    //   452: invokeinterface 231 1 0
    //   457: istore 8
    //   459: aload_0
    //   460: invokeinterface 251 1 0
    //   465: iload 8
    //   467: ifne +14 -> 481
    //   470: new 213	java/lang/Exception
    //   473: dup
    //   474: ldc_w 482
    //   477: invokespecial 348	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   480: athrow
    //   481: new 135	java/lang/StringBuilder
    //   484: dup
    //   485: ldc_w 484
    //   488: invokespecial 140	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   491: astore_0
    //   492: aload_0
    //   493: invokestatic 433	java/lang/System:currentTimeMillis	()J
    //   496: lload_2
    //   497: lsub
    //   498: invokevirtual 218	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   501: pop
    //   502: ldc -102
    //   504: aload_0
    //   505: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   508: invokestatic 487	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   511: pop
    //   512: aload 12
    //   514: invokeinterface 376 1 0
    //   519: ldc_w 378
    //   522: aload 14
    //   524: invokeinterface 386 3 0
    //   529: ldc_w 388
    //   532: aload 13
    //   534: getfield 424	com/android/launcher3/InvariantDeviceProfile:numHotseatIcons	I
    //   537: invokeinterface 392 3 0
    //   542: invokeinterface 395 1 0
    //   547: iconst_1
    //   548: ireturn
    //   549: astore_0
    //   550: goto +82 -> 632
    //   553: astore_0
    //   554: ldc -102
    //   556: ldc_w 489
    //   559: aload_0
    //   560: invokestatic 492	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   563: pop
    //   564: new 135	java/lang/StringBuilder
    //   567: dup
    //   568: ldc_w 484
    //   571: invokespecial 140	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   574: astore_0
    //   575: aload_0
    //   576: invokestatic 433	java/lang/System:currentTimeMillis	()J
    //   579: lload_2
    //   580: lsub
    //   581: invokevirtual 218	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   584: pop
    //   585: ldc -102
    //   587: aload_0
    //   588: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   591: invokestatic 487	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   594: pop
    //   595: aload 12
    //   597: invokeinterface 376 1 0
    //   602: ldc_w 378
    //   605: aload 14
    //   607: invokeinterface 386 3 0
    //   612: ldc_w 388
    //   615: aload 13
    //   617: getfield 424	com/android/launcher3/InvariantDeviceProfile:numHotseatIcons	I
    //   620: invokeinterface 392 3 0
    //   625: invokeinterface 395 1 0
    //   630: iconst_0
    //   631: ireturn
    //   632: new 135	java/lang/StringBuilder
    //   635: dup
    //   636: ldc_w 484
    //   639: invokespecial 140	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   642: astore 9
    //   644: aload 9
    //   646: invokestatic 433	java/lang/System:currentTimeMillis	()J
    //   649: lload_2
    //   650: lsub
    //   651: invokevirtual 218	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   654: pop
    //   655: ldc -102
    //   657: aload 9
    //   659: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   662: invokestatic 487	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   665: pop
    //   666: aload 12
    //   668: invokeinterface 376 1 0
    //   673: ldc_w 378
    //   676: aload 14
    //   678: invokeinterface 386 3 0
    //   683: ldc_w 388
    //   686: aload 13
    //   688: getfield 424	com/android/launcher3/InvariantDeviceProfile:numHotseatIcons	I
    //   691: invokeinterface 392 3 0
    //   696: invokeinterface 395 1 0
    //   701: aload_0
    //   702: athrow
    //   703: aload 10
    //   705: astore 9
    //   707: goto -525 -> 182
    //   710: iload_1
    //   711: iconst_1
    //   712: iadd
    //   713: istore_1
    //   714: goto -448 -> 266
    //   717: iconst_0
    //   718: istore 8
    //   720: goto -374 -> 346
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	723	0	paramContext	Context
    //   101	613	1	i	int
    //   79	571	2	l1	long
    //   293	7	4	l2	long
    //   297	13	6	l3	long
    //   341	378	8	bool	boolean
    //   173	533	9	localObject1	Object
    //   206	498	10	localObject2	Object
    //   202	21	11	localDbEntry	DbEntry
    //   4	663	12	localSharedPreferences	SharedPreferences
    //   13	674	13	localInvariantDeviceProfile	InvariantDeviceProfile
    //   28	649	14	str	String
    //   84	329	15	localHashSet	HashSet
    //   134	203	16	localGridSizeMigrationTask	GridSizeMigrationTask
    //   141	117	17	localArrayList	ArrayList
    //   180	13	18	localIterator	Iterator
    // Exception table:
    //   from	to	target	type
    //   80	149	549	finally
    //   149	182	549	finally
    //   182	204	549	finally
    //   208	222	549	finally
    //   229	254	549	finally
    //   257	264	549	finally
    //   266	295	549	finally
    //   307	333	549	finally
    //   336	343	549	finally
    //   346	408	549	finally
    //   408	428	549	finally
    //   436	465	549	finally
    //   470	481	549	finally
    //   554	564	549	finally
    //   80	149	553	java/lang/Exception
    //   149	182	553	java/lang/Exception
    //   182	204	553	java/lang/Exception
    //   208	222	553	java/lang/Exception
    //   229	254	553	java/lang/Exception
    //   257	264	553	java/lang/Exception
    //   266	295	553	java/lang/Exception
    //   307	333	553	java/lang/Exception
    //   336	343	553	java/lang/Exception
    //   346	408	553	java/lang/Exception
    //   408	428	553	java/lang/Exception
    //   436	465	553	java/lang/Exception
    //   470	481	553	java/lang/Exception
  }
  
  public static LongArrayMap<Object> removeBrokenHotseatItems(Context paramContext)
  {
    paramContext = new GridSizeMigrationTask(paramContext, LauncherAppState.getInstance(paramContext).mInvariantDeviceProfile, getValidPackages(paramContext), Integer.MAX_VALUE, Integer.MAX_VALUE);
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
  
  private void verifyIntent(String paramString)
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
  {
    if (!this.mValidPackages.contains(paramString)) {
      throw new Exception("Package not available");
    }
  }
  
  /* Error */
  public ArrayList<DbEntry> loadWorkspaceEntries(long paramLong)
  {
    // Byte code:
    //   0: new 135	java/lang/StringBuilder
    //   3: dup
    //   4: ldc_w 533
    //   7: invokespecial 140	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   10: astore 15
    //   12: aload 15
    //   14: lload_1
    //   15: invokevirtual 218	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   18: pop
    //   19: aload 15
    //   21: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   24: astore 15
    //   26: aload_0
    //   27: bipush 9
    //   29: anewarray 220	java/lang/String
    //   32: dup
    //   33: iconst_0
    //   34: ldc -84
    //   36: aastore
    //   37: dup
    //   38: iconst_1
    //   39: ldc_w 319
    //   42: aastore
    //   43: dup
    //   44: iconst_2
    //   45: ldc_w 534
    //   48: aastore
    //   49: dup
    //   50: iconst_3
    //   51: ldc_w 535
    //   54: aastore
    //   55: dup
    //   56: iconst_4
    //   57: ldc_w 537
    //   60: aastore
    //   61: dup
    //   62: iconst_5
    //   63: ldc_w 539
    //   66: aastore
    //   67: dup
    //   68: bipush 6
    //   70: ldc -34
    //   72: aastore
    //   73: dup
    //   74: bipush 7
    //   76: ldc_w 541
    //   79: aastore
    //   80: dup
    //   81: bipush 8
    //   83: ldc_w 543
    //   86: aastore
    //   87: aload 15
    //   89: invokevirtual 226	com/android/launcher3/model/GridSizeMigrationTask:queryWorkspace	([Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   92: astore 17
    //   94: aload 17
    //   96: ldc -84
    //   98: invokeinterface 331 2 0
    //   103: istore 4
    //   105: aload 17
    //   107: ldc_w 319
    //   110: invokeinterface 331 2 0
    //   115: istore 6
    //   117: aload 17
    //   119: ldc_w 534
    //   122: invokeinterface 331 2 0
    //   127: istore 5
    //   129: aload 17
    //   131: ldc_w 535
    //   134: invokeinterface 331 2 0
    //   139: istore 10
    //   141: aload 17
    //   143: ldc_w 537
    //   146: invokeinterface 331 2 0
    //   151: istore 11
    //   153: aload 17
    //   155: ldc_w 539
    //   158: invokeinterface 331 2 0
    //   163: istore 12
    //   165: aload 17
    //   167: ldc -34
    //   169: invokeinterface 331 2 0
    //   174: istore 13
    //   176: aload 17
    //   178: ldc_w 541
    //   181: invokeinterface 331 2 0
    //   186: istore 14
    //   188: aload 17
    //   190: ldc_w 543
    //   193: invokeinterface 331 2 0
    //   198: istore 7
    //   200: new 61	java/util/ArrayList
    //   203: dup
    //   204: invokespecial 62	java/util/ArrayList:<init>	()V
    //   207: astore 15
    //   209: aload 17
    //   211: invokeinterface 231 1 0
    //   216: ifeq +606 -> 822
    //   219: new 6	com/android/launcher3/model/GridSizeMigrationTask$DbEntry
    //   222: dup
    //   223: invokespecial 332	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:<init>	()V
    //   226: astore 18
    //   228: aload 18
    //   230: aload 17
    //   232: iload 4
    //   234: invokeinterface 242 2 0
    //   239: putfield 336	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:id	J
    //   242: aload 18
    //   244: aload 17
    //   246: iload 6
    //   248: invokeinterface 340 2 0
    //   253: putfield 342	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:itemType	I
    //   256: aload 18
    //   258: aload 17
    //   260: iload 5
    //   262: invokeinterface 340 2 0
    //   267: putfield 449	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:cellX	I
    //   270: aload 18
    //   272: aload 17
    //   274: iload 10
    //   276: invokeinterface 340 2 0
    //   281: putfield 452	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:cellY	I
    //   284: aload 18
    //   286: aload 17
    //   288: iload 11
    //   290: invokeinterface 340 2 0
    //   295: putfield 545	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:spanX	I
    //   298: aload 18
    //   300: aload 17
    //   302: iload 12
    //   304: invokeinterface 340 2 0
    //   309: putfield 547	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:spanY	I
    //   312: aload 18
    //   314: lload_1
    //   315: putfield 345	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:screenId	J
    //   318: aload 18
    //   320: getfield 342	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:itemType	I
    //   323: istore 8
    //   325: iload 8
    //   327: iconst_4
    //   328: if_icmpeq +130 -> 458
    //   331: iload 8
    //   333: bipush 6
    //   335: if_icmpeq +86 -> 421
    //   338: iload 8
    //   340: tableswitch	default:+28->368, 0:+81->421, 1:+81->421, 2:+39->379
    //   368: new 213	java/lang/Exception
    //   371: dup
    //   372: ldc_w 347
    //   375: invokespecial 348	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   378: athrow
    //   379: aload_0
    //   380: aload 18
    //   382: getfield 336	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:id	J
    //   385: invokespecial 350	com/android/launcher3/model/GridSizeMigrationTask:getFolderItemsCount	(J)I
    //   388: istore 8
    //   390: iload 8
    //   392: ifne +14 -> 406
    //   395: new 213	java/lang/Exception
    //   398: dup
    //   399: ldc_w 352
    //   402: invokespecial 348	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   405: athrow
    //   406: aload 18
    //   408: iload 8
    //   410: i2f
    //   411: ldc_w 353
    //   414: fmul
    //   415: putfield 357	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:weight	F
    //   418: goto +419 -> 837
    //   421: aload_0
    //   422: aload 17
    //   424: iload 13
    //   426: invokeinterface 235 2 0
    //   431: invokespecial 238	com/android/launcher3/model/GridSizeMigrationTask:verifyIntent	(Ljava/lang/String;)V
    //   434: aload 18
    //   436: getfield 342	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:itemType	I
    //   439: ifne +401 -> 840
    //   442: ldc_w 358
    //   445: fstore_3
    //   446: goto +3 -> 449
    //   449: aload 18
    //   451: fload_3
    //   452: putfield 357	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:weight	F
    //   455: goto +382 -> 837
    //   458: aload_0
    //   459: aload 17
    //   461: iload 14
    //   463: invokeinterface 235 2 0
    //   468: invokestatic 551	android/content/ComponentName:unflattenFromString	(Ljava/lang/String;)Landroid/content/ComponentName;
    //   471: invokevirtual 518	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   474: invokespecial 521	com/android/launcher3/model/GridSizeMigrationTask:verifyPackage	(Ljava/lang/String;)V
    //   477: aload 18
    //   479: fconst_2
    //   480: aload 18
    //   482: getfield 545	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:spanX	I
    //   485: i2f
    //   486: ldc_w 552
    //   489: fmul
    //   490: aload 18
    //   492: getfield 547	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:spanY	I
    //   495: i2f
    //   496: fmul
    //   497: invokestatic 558	java/lang/Math:max	(FF)F
    //   500: putfield 357	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:weight	F
    //   503: aload 17
    //   505: iload 7
    //   507: invokeinterface 340 2 0
    //   512: istore 8
    //   514: aload_0
    //   515: getfield 70	com/android/launcher3/model/GridSizeMigrationTask:mContext	Landroid/content/Context;
    //   518: invokestatic 563	com/android/launcher3/compat/AppWidgetManagerCompat:getInstance	(Landroid/content/Context;)Lcom/android/launcher3/compat/AppWidgetManagerCompat;
    //   521: iload 8
    //   523: invokevirtual 567	com/android/launcher3/compat/AppWidgetManagerCompat:getLauncherAppWidgetInfo	(I)Lcom/android/launcher3/LauncherAppWidgetProviderInfo;
    //   526: astore 19
    //   528: aconst_null
    //   529: astore 16
    //   531: aload 19
    //   533: ifnull +323 -> 856
    //   536: aload 19
    //   538: getfield 572	com/android/launcher3/LauncherAppWidgetProviderInfo:resizeMode	I
    //   541: istore 8
    //   543: iload 8
    //   545: iconst_1
    //   546: iand
    //   547: ifeq +18 -> 565
    //   550: aload 19
    //   552: getfield 575	com/android/launcher3/LauncherAppWidgetProviderInfo:minSpanX	I
    //   555: istore 8
    //   557: goto +11 -> 568
    //   560: astore 16
    //   562: goto +272 -> 834
    //   565: iconst_m1
    //   566: istore 8
    //   568: aload 19
    //   570: getfield 572	com/android/launcher3/LauncherAppWidgetProviderInfo:resizeMode	I
    //   573: iconst_2
    //   574: iand
    //   575: ifeq +270 -> 845
    //   578: aload 19
    //   580: getfield 578	com/android/launcher3/LauncherAppWidgetProviderInfo:minSpanY	I
    //   583: istore 9
    //   585: goto +3 -> 588
    //   588: new 95	android/graphics/Point
    //   591: dup
    //   592: iload 8
    //   594: iload 9
    //   596: invokespecial 461	android/graphics/Point:<init>	(II)V
    //   599: astore 16
    //   601: goto +3 -> 604
    //   604: aload 16
    //   606: ifnull +75 -> 681
    //   609: aload 16
    //   611: getfield 98	android/graphics/Point:x	I
    //   614: ifle +13 -> 627
    //   617: aload 16
    //   619: getfield 98	android/graphics/Point:x	I
    //   622: istore 8
    //   624: goto +10 -> 634
    //   627: aload 18
    //   629: getfield 545	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:spanX	I
    //   632: istore 8
    //   634: aload 18
    //   636: iload 8
    //   638: putfield 579	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:minSpanX	I
    //   641: aload 16
    //   643: getfield 101	android/graphics/Point:y	I
    //   646: ifle +13 -> 659
    //   649: aload 16
    //   651: getfield 101	android/graphics/Point:y	I
    //   654: istore 8
    //   656: goto +10 -> 666
    //   659: aload 18
    //   661: getfield 547	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:spanY	I
    //   664: istore 8
    //   666: aload 18
    //   668: iload 8
    //   670: putfield 580	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:minSpanY	I
    //   673: goto +20 -> 693
    //   676: astore 16
    //   678: goto +175 -> 853
    //   681: aload 18
    //   683: iconst_2
    //   684: putfield 580	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:minSpanY	I
    //   687: aload 18
    //   689: iconst_2
    //   690: putfield 579	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:minSpanX	I
    //   693: aload 18
    //   695: getfield 579	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:minSpanX	I
    //   698: aload_0
    //   699: getfield 82	com/android/launcher3/model/GridSizeMigrationTask:mTrgX	I
    //   702: if_icmpgt +37 -> 739
    //   705: aload 18
    //   707: getfield 580	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:minSpanY	I
    //   710: istore 8
    //   712: aload_0
    //   713: getfield 80	com/android/launcher3/model/GridSizeMigrationTask:mTrgY	I
    //   716: istore 9
    //   718: iload 8
    //   720: iload 9
    //   722: if_icmple +115 -> 837
    //   725: goto +14 -> 739
    //   728: aload 15
    //   730: aload 18
    //   732: invokevirtual 208	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   735: pop
    //   736: goto +83 -> 819
    //   739: new 213	java/lang/Exception
    //   742: dup
    //   743: ldc_w 582
    //   746: invokespecial 348	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   749: athrow
    //   750: astore 16
    //   752: goto +15 -> 767
    //   755: astore 16
    //   757: goto +10 -> 767
    //   760: astore 16
    //   762: goto +5 -> 767
    //   765: astore 16
    //   767: new 135	java/lang/StringBuilder
    //   770: dup
    //   771: ldc_w 360
    //   774: invokespecial 140	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   777: astore 19
    //   779: aload 19
    //   781: aload 18
    //   783: getfield 336	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:id	J
    //   786: invokevirtual 218	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   789: pop
    //   790: ldc -102
    //   792: aload 19
    //   794: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   797: aload 16
    //   799: invokestatic 363	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   802: pop
    //   803: aload_0
    //   804: getfield 64	com/android/launcher3/model/GridSizeMigrationTask:mEntryToRemove	Ljava/util/ArrayList;
    //   807: aload 18
    //   809: getfield 336	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:id	J
    //   812: invokestatic 248	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   815: invokevirtual 208	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   818: pop
    //   819: goto -610 -> 209
    //   822: aload 17
    //   824: invokeinterface 251 1 0
    //   829: aload 15
    //   831: areturn
    //   832: astore 16
    //   834: goto -67 -> 767
    //   837: goto -109 -> 728
    //   840: fconst_1
    //   841: fstore_3
    //   842: goto -393 -> 449
    //   845: iconst_m1
    //   846: istore 9
    //   848: goto -260 -> 588
    //   851: astore 16
    //   853: goto -19 -> 834
    //   856: goto -252 -> 604
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	859	0	this	GridSizeMigrationTask
    //   0	859	1	paramLong	long
    //   445	397	3	f	float
    //   103	130	4	i	int
    //   127	134	5	j	int
    //   115	132	6	k	int
    //   198	308	7	m	int
    //   323	400	8	n	int
    //   583	264	9	i1	int
    //   139	136	10	i2	int
    //   151	138	11	i3	int
    //   163	140	12	i4	int
    //   174	251	13	i5	int
    //   186	276	14	i6	int
    //   10	820	15	localObject1	Object
    //   529	1	16	localObject2	Object
    //   560	1	16	localException1	Exception
    //   599	51	16	localPoint	Point
    //   676	1	16	localException2	Exception
    //   750	1	16	localException3	Exception
    //   755	1	16	localException4	Exception
    //   760	1	16	localException5	Exception
    //   765	33	16	localException6	Exception
    //   832	1	16	localException7	Exception
    //   851	1	16	localException8	Exception
    //   92	731	17	localCursor	Cursor
    //   226	582	18	localDbEntry	DbEntry
    //   526	267	19	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   550	557	560	java/lang/Exception
    //   568	585	676	java/lang/Exception
    //   588	601	676	java/lang/Exception
    //   609	624	676	java/lang/Exception
    //   627	634	676	java/lang/Exception
    //   634	656	676	java/lang/Exception
    //   659	666	676	java/lang/Exception
    //   666	673	676	java/lang/Exception
    //   739	750	750	java/lang/Exception
    //   681	693	755	java/lang/Exception
    //   693	718	755	java/lang/Exception
    //   503	528	760	java/lang/Exception
    //   318	325	765	java/lang/Exception
    //   458	503	765	java/lang/Exception
    //   368	379	832	java/lang/Exception
    //   379	390	832	java/lang/Exception
    //   395	406	832	java/lang/Exception
    //   406	418	832	java/lang/Exception
    //   421	442	832	java/lang/Exception
    //   449	455	832	java/lang/Exception
    //   536	543	851	java/lang/Exception
  }
  
  protected final void migrateScreen(long paramLong)
  {
    int i3;
    if ((FeatureFlags.QSB_ON_FIRST_SCREEN) && (paramLong == 0L)) {
      i3 = 1;
    } else {
      i3 = 0;
    }
    Object localObject4 = loadWorkspaceEntries(paramLong);
    Object localObject5 = new float[2];
    Object localObject1 = null;
    int j = Integer.MAX_VALUE;
    int i = Integer.MAX_VALUE;
    float f1 = Float.MAX_VALUE;
    float f2 = Float.MAX_VALUE;
    int n = 0;
    int k;
    int m;
    Object localObject2;
    float f3;
    for (;;)
    {
      k = j;
      m = i;
      localObject2 = localObject1;
      f3 = f1;
      if (n >= this.mSrcX) {
        break;
      }
      int i1 = this.mSrcY - 1;
      f3 = f2;
      float f4 = f1;
      k = i;
      m = j;
      i = i1;
      while (i >= i3)
      {
        Object localObject6 = deepCopy((ArrayList)localObject4);
        localObject2 = new GridOccupancy(this.mTrgX, this.mTrgY);
        i1 = this.mTrgX;
        j = n;
        ((GridOccupancy)localObject2).markCells(0, 0, i1, i3, true);
        if (this.mShouldRemoveX) {
          i1 = j;
        } else {
          i1 = Integer.MAX_VALUE;
        }
        int i2;
        if (this.mShouldRemoveY) {
          i2 = i;
        } else {
          i2 = Integer.MAX_VALUE;
        }
        localObject3 = new ArrayList();
        ArrayList localArrayList = new ArrayList();
        localObject6 = ((ArrayList)localObject6).iterator();
        if (((Iterator)localObject6).hasNext())
        {
          DbEntry localDbEntry = (DbEntry)((Iterator)localObject6).next();
          if (((localDbEntry.cellX <= i1) && (localDbEntry.spanX + localDbEntry.cellX > i1)) || ((localDbEntry.cellY <= i2) && (localDbEntry.spanY + localDbEntry.cellY > i2)))
          {
            localArrayList.add(localDbEntry);
            if (localDbEntry.cellX >= i1) {
              localDbEntry.cellX -= 1;
            }
            if (localDbEntry.cellY >= i2) {
              localDbEntry.cellY -= 1;
            }
          }
          for (;;)
          {
            break;
            if (localDbEntry.cellX > i1) {
              localDbEntry.cellX -= 1;
            }
            if (localDbEntry.cellY > i2) {
              localDbEntry.cellY -= 1;
            }
            ((ArrayList)localObject3).add(localDbEntry);
            ((GridOccupancy)localObject2).markCells(localDbEntry, true);
          }
        }
        localObject2 = new OptimalPlacementSolution((GridOccupancy)localObject2, localArrayList, i3);
        ((OptimalPlacementSolution)localObject2).find();
        ((ArrayList)localObject3).addAll(((OptimalPlacementSolution)localObject2).finalPlacedItems);
        localObject5[0] = ((OptimalPlacementSolution)localObject2).lowestWeightLoss;
        localObject5[1] = ((OptimalPlacementSolution)localObject2).lowestMoveCost;
        if (localObject5[0] >= f4)
        {
          localObject2 = localObject1;
          i2 = m;
          i1 = k;
          f2 = f4;
          f1 = f3;
          if (localObject5[0] == f4)
          {
            localObject2 = localObject1;
            i2 = m;
            i1 = k;
            f2 = f4;
            f1 = f3;
            if (localObject5[1] >= f3) {}
          }
        }
        else
        {
          f2 = localObject5[0];
          f1 = localObject5[1];
          if (this.mShouldRemoveX) {
            m = j;
          }
          if (this.mShouldRemoveY) {
            k = i;
          }
          localObject2 = localObject3;
          i1 = k;
          i2 = m;
        }
        localObject1 = localObject2;
        m = i2;
        k = i1;
        f4 = f2;
        f3 = f1;
        if (!this.mShouldRemoveY) {
          break;
        }
        i -= 1;
        n = j;
        localObject1 = localObject2;
        m = i2;
        k = i1;
        f4 = f2;
        f3 = f1;
      }
      j = m;
      i = k;
      f1 = f4;
      f2 = f3;
      k = j;
      m = i;
      localObject2 = localObject1;
      f3 = f1;
      if (!this.mShouldRemoveX) {
        break;
      }
      n += 1;
    }
    Log.d("GridSizeMigrationTask", String.format("Removing row %d, column %d on screen %d", new Object[] { Integer.valueOf(m), Integer.valueOf(k), Long.valueOf(paramLong) }));
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
      if ((((DbEntry)localObject5).cellX == ((DbEntry)localObject4).cellX) && (((DbEntry)localObject5).cellY == ((DbEntry)localObject4).cellY) && (((DbEntry)localObject5).spanX == ((DbEntry)localObject4).spanX) && (((DbEntry)localObject5).spanY == ((DbEntry)localObject4).spanY) && (((DbEntry)localObject5).screenId == ((DbEntry)localObject4).screenId)) {
        i = 1;
      } else {
        i = 0;
      }
      if (i == 0) {
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
      ((GridOccupancy)localObject1).markCells(0, 0, this.mTrgX, i3, true);
      localObject2 = ((ArrayList)localObject2).iterator();
      while (((Iterator)localObject2).hasNext()) {
        ((GridOccupancy)localObject1).markCells((DbEntry)((Iterator)localObject2).next(), true);
      }
      localObject1 = new OptimalPlacementSolution((GridOccupancy)localObject1, deepCopy(this.mCarryOver), i3, true);
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
  
  protected final boolean migrateWorkspace()
  {
    ArrayList localArrayList = LauncherModel.loadWorkspaceScreensDb(this.mContext);
    if (localArrayList.isEmpty()) {
      throw new Exception("Unable to get workspace screens");
    }
    Object localObject1 = localArrayList.iterator();
    long l;
    Object localObject2;
    while (((Iterator)localObject1).hasNext())
    {
      l = ((Long)((Iterator)localObject1).next()).longValue();
      localObject2 = new StringBuilder("Migrating ");
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
          if (!this.mCarryOver.remove(((LongArrayMap)localObject1).get(localDbEntry.id))) {
            throw new Exception("Unable to find matching items");
          }
          localDbEntry.screenId = l;
          update(localDbEntry);
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
  
  public Cursor queryWorkspace(String[] paramArrayOfString, String paramString)
  {
    return this.mContext.getContentResolver().query(LauncherSettings.Favorites.CONTENT_URI, paramArrayOfString, paramString, null, null, null);
  }
  
  public void update(DbEntry paramDbEntry)
  {
    this.mTempValues.clear();
    paramDbEntry.addToContentValues(this.mTempValues);
    this.mUpdateOperations.add(ContentProviderOperation.newUpdate(LauncherSettings.Favorites.getContentUri(paramDbEntry.id)).withValues(this.mTempValues).build());
  }
  
  protected static final class DbEntry
    extends ItemInfo
    implements Comparable<DbEntry>
  {
    public float weight;
    
    public DbEntry() {}
    
    public final void addToContentValues(ContentValues paramContentValues)
    {
      paramContentValues.put("screen", Long.valueOf(this.screenId));
      paramContentValues.put("cellX", Integer.valueOf(this.cellX));
      paramContentValues.put("cellY", Integer.valueOf(this.cellY));
      paramContentValues.put("spanX", Integer.valueOf(this.spanX));
      paramContentValues.put("spanY", Integer.valueOf(this.spanY));
    }
    
    public final DbEntry copy()
    {
      DbEntry localDbEntry = new DbEntry();
      localDbEntry.copyFrom(this);
      localDbEntry.weight = this.weight;
      localDbEntry.minSpanX = this.minSpanX;
      localDbEntry.minSpanY = this.minSpanY;
      return localDbEntry;
    }
  }
  
  protected static final class MultiStepMigrationTask
  {
    private final Context mContext;
    private final HashSet<String> mValidPackages;
    
    public MultiStepMigrationTask(HashSet<String> paramHashSet, Context paramContext)
    {
      this.mValidPackages = paramHashSet;
      this.mContext = paramContext;
    }
    
    public final boolean migrate(Point paramPoint1, Point paramPoint2)
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
          if (new GridSizeMigrationTask(this.mContext, LauncherAppState.getInstance(this.mContext).mInvariantDeviceProfile, this.mValidPackages, paramPoint1, localPoint).migrateWorkspace()) {
            bool1 = true;
          }
          paramPoint1.set(localPoint.x, localPoint.y);
        }
      }
      return bool2;
    }
  }
  
  private final class OptimalPlacementSolution
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
    
    private void find(int paramInt, float paramFloat1, float paramFloat2, ArrayList<GridSizeMigrationTask.DbEntry> paramArrayList)
    {
      while (paramFloat1 < this.lowestWeightLoss)
      {
        if ((paramFloat1 == this.lowestWeightLoss) && (paramFloat2 >= this.lowestMoveCost)) {
          return;
        }
        if (paramInt >= this.itemsToPlace.size())
        {
          this.lowestWeightLoss = paramFloat1;
          this.lowestMoveCost = paramFloat2;
          this.finalPlacedItems = GridSizeMigrationTask.deepCopy(paramArrayList);
          return;
        }
        GridSizeMigrationTask.DbEntry localDbEntry = (GridSizeMigrationTask.DbEntry)this.itemsToPlace.get(paramInt);
        int i5 = localDbEntry.cellX;
        int i6 = localDbEntry.cellY;
        ArrayList localArrayList = new ArrayList(paramArrayList.size() + 1);
        localArrayList.addAll(paramArrayList);
        localArrayList.add(localDbEntry);
        int i;
        int m;
        int j;
        int k;
        float f1;
        float f2;
        if ((localDbEntry.spanX <= 1) && (localDbEntry.spanY <= 1))
        {
          i = this.startY;
          int i1 = Integer.MAX_VALUE;
          int n = Integer.MAX_VALUE;
          m = Integer.MAX_VALUE;
          while (i < GridSizeMigrationTask.this.mTrgY)
          {
            j = 0;
            while (j < GridSizeMigrationTask.this.mTrgX)
            {
              int i4 = i1;
              int i3 = n;
              int i2 = m;
              if (this.occupied.cells[j][i] == 0)
              {
                if (this.ignoreMove) {
                  k = 0;
                } else {
                  k = (localDbEntry.cellX - j) * (localDbEntry.cellX - j) + (localDbEntry.cellY - i) * (localDbEntry.cellY - i);
                }
                i4 = i1;
                i3 = n;
                i2 = m;
                if (k < m)
                {
                  i3 = i;
                  i4 = j;
                  i2 = k;
                }
              }
              j += 1;
              i1 = i4;
              n = i3;
              m = i2;
            }
            i += 1;
          }
          f1 = paramFloat1;
          i = paramInt;
          if (i1 < GridSizeMigrationTask.this.mTrgX)
          {
            f1 = paramFloat1;
            i = paramInt;
            if (n < GridSizeMigrationTask.this.mTrgY)
            {
              if (i1 != i5)
              {
                localDbEntry.cellX = i1;
                f1 = paramFloat2 + 1.0F;
              }
              else
              {
                f1 = paramFloat2;
              }
              f2 = f1;
              if (n != i6)
              {
                localDbEntry.cellY = n;
                f2 = f1 + 1.0F;
              }
              f1 = f2;
              if (this.ignoreMove) {
                f1 = paramFloat2;
              }
              this.occupied.markCells(localDbEntry, true);
              paramInt += 1;
              find(paramInt, paramFloat1, f1, localArrayList);
              this.occupied.markCells(localDbEntry, false);
              localDbEntry.cellX = i5;
              localDbEntry.cellY = i6;
              if ((paramInt < this.itemsToPlace.size()) && (((GridSizeMigrationTask.DbEntry)this.itemsToPlace.get(paramInt)).weight >= localDbEntry.weight) && (!this.ignoreMove)) {
                break label1072;
              }
              return;
            }
          }
          for (;;)
          {
            i += 1;
            if (i >= this.itemsToPlace.size()) {
              break;
            }
            f1 += ((GridSizeMigrationTask.DbEntry)this.itemsToPlace.get(i)).weight;
          }
          paramInt = this.itemsToPlace.size();
          paramFloat1 = f1;
        }
        else
        {
          k = localDbEntry.spanX;
          m = localDbEntry.spanY;
          i = this.startY;
          while (i < GridSizeMigrationTask.this.mTrgY)
          {
            j = 0;
            while (j < GridSizeMigrationTask.this.mTrgX)
            {
              if (j != i5)
              {
                localDbEntry.cellX = j;
                f1 = paramFloat2 + 1.0F;
              }
              else
              {
                f1 = paramFloat2;
              }
              f2 = f1;
              if (i != i6)
              {
                localDbEntry.cellY = i;
                f2 = f1 + 1.0F;
              }
              f1 = f2;
              if (this.ignoreMove) {
                f1 = paramFloat2;
              }
              if (this.occupied.isRegionVacant(j, i, k, m))
              {
                this.occupied.markCells(localDbEntry, true);
                find(paramInt + 1, paramFloat1, f1, localArrayList);
                this.occupied.markCells(localDbEntry, false);
              }
              if ((k > localDbEntry.minSpanX) && (this.occupied.isRegionVacant(j, i, k - 1, m)))
              {
                localDbEntry.spanX -= 1;
                this.occupied.markCells(localDbEntry, true);
                find(paramInt + 1, paramFloat1, f1 + 1.0F, localArrayList);
                this.occupied.markCells(localDbEntry, false);
                localDbEntry.spanX += 1;
              }
              if ((m > localDbEntry.minSpanY) && (this.occupied.isRegionVacant(j, i, k, m - 1)))
              {
                localDbEntry.spanY -= 1;
                this.occupied.markCells(localDbEntry, true);
                find(paramInt + 1, paramFloat1, f1 + 1.0F, localArrayList);
                this.occupied.markCells(localDbEntry, false);
                localDbEntry.spanY += 1;
              }
              if ((m > localDbEntry.minSpanY) && (k > localDbEntry.minSpanX) && (this.occupied.isRegionVacant(j, i, k - 1, m - 1)))
              {
                localDbEntry.spanX -= 1;
                localDbEntry.spanY -= 1;
                this.occupied.markCells(localDbEntry, true);
                find(paramInt + 1, paramFloat1, f1 + 2.0F, localArrayList);
                this.occupied.markCells(localDbEntry, false);
                localDbEntry.spanX += 1;
                localDbEntry.spanY += 1;
              }
              localDbEntry.cellX = i5;
              localDbEntry.cellY = i6;
              j += 1;
            }
            i += 1;
          }
          paramInt += 1;
        }
        label1072:
        paramFloat1 += localDbEntry.weight;
      }
    }
    
    public final void find()
    {
      find(0, 0.0F, 0.0F, new ArrayList());
    }
  }
}
