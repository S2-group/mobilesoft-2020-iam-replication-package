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
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.android.launcher3.LauncherProvider;
import com.crashlytics.android.Crashlytics;
import com.minti.lib.hv;
import com.minti.lib.hx;
import com.minti.lib.ik;
import com.minti.lib.in.c;
import com.minti.lib.in.f;
import com.minti.lib.in.g;
import com.minti.lib.jp;
import com.minti.lib.lg.b;
import com.minti.lib.xl;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

public class GridSizeMigrationTask
{
  private static final boolean DEBUG = true;
  public static boolean ENABLED = ;
  private static final String KEY_MIGRATION_SRC_HOTSEAT_SIZE = "migration_src_hotseat_size";
  private static final String KEY_MIGRATION_SRC_WORKSPACE_SIZE = "migration_src_workspace_size";
  private static final String KEY_MIGRATION_WIDGET_MINSIZE = "migration_widget_min_size";
  private static final String TAG = "GridSizeMigrationTask";
  private static final float WT_APPLICATION = 0.8F;
  private static final float WT_FOLDER_FACTOR = 0.5F;
  private static final float WT_SHORTCUT = 1.0F;
  private static final float WT_WIDGET_FACTOR = 0.6F;
  private static final float WT_WIDGET_MIN = 2.0F;
  private final ArrayList<DbEntry> mCarryOver = new ArrayList();
  private final Context mContext;
  private final int mDestAllAppsRank;
  private final int mDestHotseatSize;
  private final ArrayList<Long> mEntryToRemove = new ArrayList();
  private final hv mIdp;
  private final boolean mShouldRemoveX;
  private final boolean mShouldRemoveY;
  private final int mSrcAllAppsRank;
  private final int mSrcHotseatSize;
  private final int mSrcX;
  private final int mSrcY;
  private final ContentValues mTempValues = new ContentValues();
  private final int mTrgX;
  private final int mTrgY;
  private final ArrayList<ContentProviderOperation> mUpdateOperations = new ArrayList();
  private final HashSet<String> mValidPackages;
  private final HashMap<String, Point> mWidgetMinSize = new HashMap();
  
  protected GridSizeMigrationTask(Context paramContext, hv paramHv, HashSet<String> paramHashSet, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mContext = paramContext;
    this.mIdp = paramHv;
    this.mValidPackages = paramHashSet;
    this.mSrcHotseatSize = paramInt1;
    this.mSrcAllAppsRank = paramInt2;
    this.mDestHotseatSize = paramInt3;
    this.mDestAllAppsRank = paramInt4;
    this.mTrgY = -1;
    this.mTrgX = -1;
    this.mSrcY = -1;
    this.mSrcX = -1;
    this.mShouldRemoveY = false;
    this.mShouldRemoveX = false;
  }
  
  protected GridSizeMigrationTask(Context paramContext, hv paramHv, HashSet<String> paramHashSet, HashMap<String, Point> paramHashMap, Point paramPoint1, Point paramPoint2)
  {
    this.mContext = paramContext;
    this.mValidPackages = paramHashSet;
    this.mWidgetMinSize.putAll(paramHashMap);
    this.mIdp = paramHv;
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
    this.mDestAllAppsRank = -1;
    this.mDestHotseatSize = -1;
    this.mSrcAllAppsRank = -1;
    this.mSrcHotseatSize = -1;
  }
  
  private boolean applyOperations()
    throws Exception
  {
    if (!this.mUpdateOperations.isEmpty()) {
      this.mContext.getContentResolver().applyBatch(LauncherProvider.a, this.mUpdateOperations);
    }
    if (!this.mEntryToRemove.isEmpty())
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Removing items: ");
      localStringBuilder.append(TextUtils.join(", ", this.mEntryToRemove));
      Log.d("GridSizeMigrationTask", localStringBuilder.toString());
      this.mContext.getContentResolver().delete(in.c.n, jp.a("_id", this.mEntryToRemove), null);
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
    Object localObject1 = this.mContext.getContentResolver();
    Uri localUri = in.c.n;
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("container = ");
    ((StringBuilder)localObject2).append(paramLong);
    localObject2 = ((StringBuilder)localObject2).toString();
    localObject1 = ((ContentResolver)localObject1).query(localUri, new String[] { "_id", "intent" }, (String)localObject2, null, null, null);
    int i = 0;
    while (((Cursor)localObject1).moveToNext()) {
      try
      {
        verifyIntent(((Cursor)localObject1).getString(1));
        i += 1;
      }
      catch (Exception localException)
      {
        Crashlytics.logException(localException);
        this.mEntryToRemove.add(Long.valueOf(((Cursor)localObject1).getLong(0)));
      }
    }
    ((Cursor)localObject1).close();
    return i;
  }
  
  private static String getPointString(int paramInt1, int paramInt2)
  {
    return String.format(Locale.ENGLISH, "%d,%d", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
  }
  
  private boolean isVacant(boolean[][] paramArrayOfBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt1 + paramInt3 > this.mTrgX) {
      return false;
    }
    if (paramInt2 + paramInt4 > this.mTrgY) {
      return false;
    }
    int i = 0;
    while (i < paramInt3)
    {
      int j = 0;
      while (j < paramInt4)
      {
        if (paramArrayOfBoolean[(i + paramInt1)][(j + paramInt2)] != 0) {
          return false;
        }
        j += 1;
      }
      i += 1;
    }
    return true;
  }
  
  private ArrayList<DbEntry> loadHotseatEntries()
  {
    Cursor localCursor = this.mContext.getContentResolver().query(in.c.n, new String[] { "_id", "itemType", "intent", "screen" }, "container = -101", null, null, null);
    int i = localCursor.getColumnIndexOrThrow("_id");
    int j = localCursor.getColumnIndexOrThrow("itemType");
    int k = localCursor.getColumnIndexOrThrow("intent");
    int m = localCursor.getColumnIndexOrThrow("screen");
    ArrayList localArrayList = new ArrayList();
    label102:
    DbEntry localDbEntry;
    for (;;)
    {
      if (!localCursor.moveToNext()) {
        break label386;
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
        switch (localDbEntry.itemType)
        {
        case 2: 
          continue;
          int n = getFolderItemsCount(localDbEntry.id);
          if (n == 0) {
            throw new Exception("Folder is empty");
          }
          localDbEntry.weight = (n * 0.5F);
          break;
        case 0: 
        case 1: 
          verifyIntent(localCursor.getString(k));
          if (localDbEntry.itemType != 1) {
            break label399;
          }
          f = 1.0F;
          localDbEntry.weight = f;
          localArrayList.add(localDbEntry);
          break label102;
          throw new Exception("Invalid item type");
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
      label386:
      localCursor.close();
      return localArrayList;
      continue;
      label399:
      float f = 0.8F;
    }
  }
  
  /* Error */
  private ArrayList<DbEntry> loadWorkspaceEntries(long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 105	com/android/launcher3/model/GridSizeMigrationTask:mContext	Landroid/content/Context;
    //   4: invokevirtual 177	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   7: astore 15
    //   9: getstatic 220	com/minti/lib/in$c:n	Landroid/net/Uri;
    //   12: astore 16
    //   14: new 189	java/lang/StringBuilder
    //   17: dup
    //   18: invokespecial 190	java/lang/StringBuilder:<init>	()V
    //   21: astore 17
    //   23: aload 17
    //   25: ldc_w 372
    //   28: invokevirtual 196	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: pop
    //   32: aload 17
    //   34: lload_1
    //   35: invokevirtual 266	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   38: pop
    //   39: aload 17
    //   41: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   44: astore 17
    //   46: aload 15
    //   48: aload 16
    //   50: bipush 9
    //   52: anewarray 268	java/lang/String
    //   55: dup
    //   56: iconst_0
    //   57: ldc -34
    //   59: aastore
    //   60: dup
    //   61: iconst_1
    //   62: ldc_w 329
    //   65: aastore
    //   66: dup
    //   67: iconst_2
    //   68: ldc_w 374
    //   71: aastore
    //   72: dup
    //   73: iconst_3
    //   74: ldc_w 376
    //   77: aastore
    //   78: dup
    //   79: iconst_4
    //   80: ldc_w 378
    //   83: aastore
    //   84: dup
    //   85: iconst_5
    //   86: ldc_w 380
    //   89: aastore
    //   90: dup
    //   91: bipush 6
    //   93: ldc_w 270
    //   96: aastore
    //   97: dup
    //   98: bipush 7
    //   100: ldc_w 382
    //   103: aastore
    //   104: dup
    //   105: bipush 8
    //   107: ldc_w 384
    //   110: aastore
    //   111: aload 17
    //   113: aconst_null
    //   114: aconst_null
    //   115: aconst_null
    //   116: invokevirtual 274	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Landroid/os/CancellationSignal;)Landroid/database/Cursor;
    //   119: astore 17
    //   121: aload 17
    //   123: ldc -34
    //   125: invokeinterface 337 2 0
    //   130: istore 6
    //   132: aload 17
    //   134: ldc_w 329
    //   137: invokeinterface 337 2 0
    //   142: istore 5
    //   144: aload 17
    //   146: ldc_w 374
    //   149: invokeinterface 337 2 0
    //   154: istore 8
    //   156: aload 17
    //   158: ldc_w 376
    //   161: invokeinterface 337 2 0
    //   166: istore 9
    //   168: aload 17
    //   170: ldc_w 378
    //   173: invokeinterface 337 2 0
    //   178: istore 10
    //   180: aload 17
    //   182: ldc_w 380
    //   185: invokeinterface 337 2 0
    //   190: istore 11
    //   192: aload 17
    //   194: ldc_w 270
    //   197: invokeinterface 337 2 0
    //   202: istore 12
    //   204: aload 17
    //   206: ldc_w 382
    //   209: invokeinterface 337 2 0
    //   214: istore 13
    //   216: aload 17
    //   218: ldc_w 384
    //   221: invokeinterface 337 2 0
    //   226: istore 4
    //   228: new 96	java/util/ArrayList
    //   231: dup
    //   232: invokespecial 97	java/util/ArrayList:<init>	()V
    //   235: astore 16
    //   237: aload 17
    //   239: invokeinterface 279 1 0
    //   244: ifeq +566 -> 810
    //   247: new 6	com/android/launcher3/model/GridSizeMigrationTask$DbEntry
    //   250: dup
    //   251: invokespecial 338	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:<init>	()V
    //   254: astore 18
    //   256: aload 18
    //   258: aload 17
    //   260: iload 6
    //   262: invokeinterface 297 2 0
    //   267: putfield 342	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:id	J
    //   270: aload 18
    //   272: aload 17
    //   274: iload 5
    //   276: invokeinterface 346 2 0
    //   281: putfield 348	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:itemType	I
    //   284: aload 18
    //   286: aload 17
    //   288: iload 8
    //   290: invokeinterface 346 2 0
    //   295: putfield 386	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:cellX	I
    //   298: aload 18
    //   300: aload 17
    //   302: iload 9
    //   304: invokeinterface 346 2 0
    //   309: putfield 388	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:cellY	I
    //   312: aload 18
    //   314: aload 17
    //   316: iload 10
    //   318: invokeinterface 346 2 0
    //   323: putfield 390	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:spanX	I
    //   326: aload 18
    //   328: aload 17
    //   330: iload 11
    //   332: invokeinterface 346 2 0
    //   337: putfield 392	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:spanY	I
    //   340: aload 18
    //   342: lload_1
    //   343: putfield 351	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:screenId	J
    //   346: aload 18
    //   348: getfield 348	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:itemType	I
    //   351: istore 7
    //   353: iload 7
    //   355: iconst_4
    //   356: if_icmpeq +120 -> 476
    //   359: iload 7
    //   361: tableswitch	default:+27->388, 0:+79->440, 1:+79->440, 2:+38->399
    //   388: new 168	java/lang/Exception
    //   391: dup
    //   392: ldc_w 362
    //   395: invokespecial 357	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   398: athrow
    //   399: aload_0
    //   400: aload 18
    //   402: getfield 342	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:id	J
    //   405: invokespecial 353	com/android/launcher3/model/GridSizeMigrationTask:getFolderItemsCount	(J)I
    //   408: istore 7
    //   410: iload 7
    //   412: ifne +14 -> 426
    //   415: new 168	java/lang/Exception
    //   418: dup
    //   419: ldc_w 355
    //   422: invokespecial 357	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   425: athrow
    //   426: aload 18
    //   428: iload 7
    //   430: i2f
    //   431: ldc 33
    //   433: fmul
    //   434: putfield 360	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:weight	F
    //   437: goto +388 -> 825
    //   440: aload_0
    //   441: aload 17
    //   443: iload 12
    //   445: invokeinterface 283 2 0
    //   450: invokespecial 287	com/android/launcher3/model/GridSizeMigrationTask:verifyIntent	(Ljava/lang/String;)V
    //   453: aload 18
    //   455: getfield 348	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:itemType	I
    //   458: iconst_1
    //   459: if_icmpne +369 -> 828
    //   462: fconst_1
    //   463: fstore_3
    //   464: goto +3 -> 467
    //   467: aload 18
    //   469: fload_3
    //   470: putfield 360	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:weight	F
    //   473: goto +352 -> 825
    //   476: aload 17
    //   478: iload 13
    //   480: invokeinterface 283 2 0
    //   485: astore 15
    //   487: aload_0
    //   488: aload 15
    //   490: invokestatic 398	android/content/ComponentName:unflattenFromString	(Ljava/lang/String;)Landroid/content/ComponentName;
    //   493: invokevirtual 401	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   496: invokespecial 404	com/android/launcher3/model/GridSizeMigrationTask:verifyPackage	(Ljava/lang/String;)V
    //   499: aload 18
    //   501: fconst_2
    //   502: aload 18
    //   504: getfield 390	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:spanX	I
    //   507: i2f
    //   508: ldc 37
    //   510: fmul
    //   511: aload 18
    //   513: getfield 392	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:spanY	I
    //   516: i2f
    //   517: fmul
    //   518: invokestatic 410	java/lang/Math:max	(FF)F
    //   521: putfield 360	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:weight	F
    //   524: aload 17
    //   526: iload 4
    //   528: invokeinterface 346 2 0
    //   533: istore 7
    //   535: aload_0
    //   536: getfield 105	com/android/launcher3/model/GridSizeMigrationTask:mContext	Landroid/content/Context;
    //   539: invokestatic 415	com/minti/lib/me:a	(Landroid/content/Context;)Lcom/minti/lib/me;
    //   542: iload 7
    //   544: invokevirtual 419	com/minti/lib/me:b	(I)Lcom/android/launcher3/LauncherAppWidgetProviderInfo;
    //   547: astore 19
    //   549: aload 19
    //   551: ifnonnull +25 -> 576
    //   554: aload_0
    //   555: getfield 89	com/android/launcher3/model/GridSizeMigrationTask:mWidgetMinSize	Ljava/util/HashMap;
    //   558: aload 15
    //   560: invokevirtual 423	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   563: checkcast 138	android/graphics/Point
    //   566: astore 15
    //   568: goto +23 -> 591
    //   571: astore 15
    //   573: goto +249 -> 822
    //   576: aload 19
    //   578: aload_0
    //   579: getfield 107	com/android/launcher3/model/GridSizeMigrationTask:mIdp	Lcom/minti/lib/hv;
    //   582: aload_0
    //   583: getfield 105	com/android/launcher3/model/GridSizeMigrationTask:mContext	Landroid/content/Context;
    //   586: invokevirtual 428	com/android/launcher3/LauncherAppWidgetProviderInfo:a	(Lcom/minti/lib/hv;Landroid/content/Context;)Landroid/graphics/Point;
    //   589: astore 15
    //   591: aload 15
    //   593: ifnull +70 -> 663
    //   596: aload 15
    //   598: getfield 141	android/graphics/Point:x	I
    //   601: ifle +13 -> 614
    //   604: aload 15
    //   606: getfield 141	android/graphics/Point:x	I
    //   609: istore 7
    //   611: goto +10 -> 621
    //   614: aload 18
    //   616: getfield 390	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:spanX	I
    //   619: istore 7
    //   621: aload 18
    //   623: iload 7
    //   625: putfield 431	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:minSpanX	I
    //   628: aload 15
    //   630: getfield 144	android/graphics/Point:y	I
    //   633: ifle +13 -> 646
    //   636: aload 15
    //   638: getfield 144	android/graphics/Point:y	I
    //   641: istore 7
    //   643: goto +10 -> 653
    //   646: aload 18
    //   648: getfield 392	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:spanY	I
    //   651: istore 7
    //   653: aload 18
    //   655: iload 7
    //   657: putfield 434	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:minSpanY	I
    //   660: goto +15 -> 675
    //   663: aload 18
    //   665: iconst_2
    //   666: putfield 434	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:minSpanY	I
    //   669: aload 18
    //   671: iconst_2
    //   672: putfield 431	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:minSpanX	I
    //   675: aload 18
    //   677: getfield 431	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:minSpanX	I
    //   680: aload_0
    //   681: getfield 121	com/android/launcher3/model/GridSizeMigrationTask:mTrgX	I
    //   684: if_icmpgt +37 -> 721
    //   687: aload 18
    //   689: getfield 434	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:minSpanY	I
    //   692: istore 7
    //   694: aload_0
    //   695: getfield 119	com/android/launcher3/model/GridSizeMigrationTask:mTrgY	I
    //   698: istore 14
    //   700: iload 7
    //   702: iload 14
    //   704: if_icmple +6 -> 710
    //   707: goto +14 -> 721
    //   710: aload 16
    //   712: aload 18
    //   714: invokevirtual 258	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   717: pop
    //   718: goto +89 -> 807
    //   721: new 168	java/lang/Exception
    //   724: dup
    //   725: ldc_w 436
    //   728: invokespecial 357	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   731: athrow
    //   732: astore 15
    //   734: goto +10 -> 744
    //   737: astore 15
    //   739: goto +5 -> 744
    //   742: astore 15
    //   744: new 189	java/lang/StringBuilder
    //   747: dup
    //   748: invokespecial 190	java/lang/StringBuilder:<init>	()V
    //   751: astore 19
    //   753: aload 19
    //   755: ldc_w 364
    //   758: invokevirtual 196	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   761: pop
    //   762: aload 19
    //   764: aload 18
    //   766: getfield 342	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:id	J
    //   769: invokevirtual 266	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   772: pop
    //   773: ldc 28
    //   775: aload 19
    //   777: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   780: aload 15
    //   782: invokestatic 367	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   785: pop
    //   786: aload 15
    //   788: invokestatic 293	com/crashlytics/android/Crashlytics:logException	(Ljava/lang/Throwable;)V
    //   791: aload_0
    //   792: getfield 99	com/android/launcher3/model/GridSizeMigrationTask:mEntryToRemove	Ljava/util/ArrayList;
    //   795: aload 18
    //   797: getfield 342	com/android/launcher3/model/GridSizeMigrationTask$DbEntry:id	J
    //   800: invokestatic 303	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   803: invokevirtual 258	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   806: pop
    //   807: goto -570 -> 237
    //   810: aload 17
    //   812: invokeinterface 306 1 0
    //   817: aload 16
    //   819: areturn
    //   820: astore 15
    //   822: goto -78 -> 744
    //   825: goto -115 -> 710
    //   828: ldc 31
    //   830: fstore_3
    //   831: goto -364 -> 467
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	834	0	this	GridSizeMigrationTask
    //   0	834	1	paramLong	long
    //   463	368	3	f	float
    //   226	301	4	i	int
    //   142	133	5	j	int
    //   130	131	6	k	int
    //   351	354	7	m	int
    //   154	135	8	n	int
    //   166	137	9	i1	int
    //   178	139	10	i2	int
    //   190	141	11	i3	int
    //   202	242	12	i4	int
    //   214	265	13	i5	int
    //   698	7	14	i6	int
    //   7	560	15	localObject1	Object
    //   571	1	15	localException1	Exception
    //   589	48	15	localPoint	Point
    //   732	1	15	localException2	Exception
    //   737	1	15	localException3	Exception
    //   742	45	15	localException4	Exception
    //   820	1	15	localException5	Exception
    //   12	806	16	localObject2	Object
    //   21	790	17	localObject3	Object
    //   254	542	18	localDbEntry	DbEntry
    //   547	229	19	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   554	568	571	java/lang/Exception
    //   596	611	571	java/lang/Exception
    //   614	621	571	java/lang/Exception
    //   621	643	571	java/lang/Exception
    //   646	653	571	java/lang/Exception
    //   653	660	571	java/lang/Exception
    //   721	732	732	java/lang/Exception
    //   524	549	737	java/lang/Exception
    //   576	591	737	java/lang/Exception
    //   663	675	737	java/lang/Exception
    //   675	700	737	java/lang/Exception
    //   346	353	742	java/lang/Exception
    //   476	524	742	java/lang/Exception
    //   388	399	820	java/lang/Exception
    //   399	410	820	java/lang/Exception
    //   415	426	820	java/lang/Exception
    //   426	437	820	java/lang/Exception
    //   440	462	820	java/lang/Exception
    //   467	473	820	java/lang/Exception
  }
  
  private void markCells(boolean[][] paramArrayOfBoolean, DbEntry paramDbEntry, boolean paramBoolean)
  {
    int i = paramDbEntry.cellX;
    while (i < paramDbEntry.cellX + paramDbEntry.spanX)
    {
      int j = paramDbEntry.cellY;
      while (j < paramDbEntry.cellY + paramDbEntry.spanY)
      {
        paramArrayOfBoolean[i][j] = paramBoolean;
        j += 1;
      }
      i += 1;
    }
  }
  
  public static void markForMigration(Context paramContext, HashSet<String> paramHashSet, lg.b paramB)
  {
    jp.c(paramContext).edit().putString("migration_src_workspace_size", getPointString((int)paramB.b, (int)paramB.a)).putString("migration_src_hotseat_size", getPointString((int)paramB.c, paramB.d)).putStringSet("migration_widget_min_size", paramHashSet).apply();
  }
  
  /* Error */
  public static boolean migrateGridIfNeeded(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 443	com/minti/lib/jp:c	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   4: astore 8
    //   6: invokestatic 482	com/minti/lib/ib:a	()Lcom/minti/lib/ib;
    //   9: invokevirtual 486	com/minti/lib/ib:l	()Lcom/minti/lib/hv;
    //   12: astore 7
    //   14: aload 7
    //   16: invokevirtual 490	com/minti/lib/hv:a	()I
    //   19: aload 7
    //   21: invokevirtual 492	com/minti/lib/hv:b	()I
    //   24: invokestatic 457	com/android/launcher3/model/GridSizeMigrationTask:getPointString	(II)Ljava/lang/String;
    //   27: astore 9
    //   29: aload 7
    //   31: getfield 494	com/minti/lib/hv:n	I
    //   34: aload 7
    //   36: getfield 497	com/minti/lib/hv:r	I
    //   39: invokestatic 457	com/android/launcher3/model/GridSizeMigrationTask:getPointString	(II)Ljava/lang/String;
    //   42: astore 10
    //   44: aload 9
    //   46: aload 8
    //   48: ldc 22
    //   50: ldc_w 499
    //   53: invokeinterface 502 3 0
    //   58: invokevirtual 505	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   61: ifeq +25 -> 86
    //   64: aload 10
    //   66: aload 8
    //   68: ldc 19
    //   70: ldc_w 499
    //   73: invokeinterface 502 3 0
    //   78: invokevirtual 505	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   81: ifeq +5 -> 86
    //   84: iconst_1
    //   85: ireturn
    //   86: invokestatic 511	java/lang/System:currentTimeMillis	()J
    //   89: lstore_3
    //   90: new 513	java/util/HashSet
    //   93: dup
    //   94: invokespecial 514	java/util/HashSet:<init>	()V
    //   97: astore 11
    //   99: aload_0
    //   100: invokevirtual 518	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   103: iconst_0
    //   104: invokevirtual 524	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   107: invokeinterface 527 1 0
    //   112: astore 12
    //   114: aload 12
    //   116: invokeinterface 246 1 0
    //   121: ifeq +25 -> 146
    //   124: aload 11
    //   126: aload 12
    //   128: invokeinterface 250 1 0
    //   133: checkcast 529	android/content/pm/PackageInfo
    //   136: getfield 532	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   139: invokevirtual 533	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   142: pop
    //   143: goto -29 -> 114
    //   146: aload 11
    //   148: aload_0
    //   149: invokestatic 538	com/minti/lib/mo:a	(Landroid/content/Context;)Lcom/minti/lib/mo;
    //   152: invokevirtual 541	com/minti/lib/mo:a	()Ljava/util/HashMap;
    //   155: invokevirtual 545	java/util/HashMap:keySet	()Ljava/util/Set;
    //   158: invokevirtual 549	java/util/HashSet:addAll	(Ljava/util/Collection;)Z
    //   161: pop
    //   162: aload 8
    //   164: ldc 19
    //   166: aload 10
    //   168: invokeinterface 502 3 0
    //   173: invokestatic 553	com/android/launcher3/model/GridSizeMigrationTask:parsePoint	(Ljava/lang/String;)Landroid/graphics/Point;
    //   176: astore 12
    //   178: aload 12
    //   180: getfield 141	android/graphics/Point:x	I
    //   183: aload 7
    //   185: getfield 494	com/minti/lib/hv:n	I
    //   188: if_icmpne +19 -> 207
    //   191: aload 12
    //   193: getfield 144	android/graphics/Point:y	I
    //   196: aload 7
    //   198: getfield 497	com/minti/lib/hv:r	I
    //   201: if_icmpeq +748 -> 949
    //   204: goto +3 -> 207
    //   207: new 2	com/android/launcher3/model/GridSizeMigrationTask
    //   210: dup
    //   211: aload_0
    //   212: invokestatic 482	com/minti/lib/ib:a	()Lcom/minti/lib/ib;
    //   215: invokevirtual 486	com/minti/lib/ib:l	()Lcom/minti/lib/hv;
    //   218: aload 11
    //   220: aload 12
    //   222: getfield 141	android/graphics/Point:x	I
    //   225: aload 12
    //   227: getfield 144	android/graphics/Point:y	I
    //   230: aload 7
    //   232: getfield 494	com/minti/lib/hv:n	I
    //   235: aload 7
    //   237: getfield 497	com/minti/lib/hv:r	I
    //   240: invokespecial 555	com/android/launcher3/model/GridSizeMigrationTask:<init>	(Landroid/content/Context;Lcom/minti/lib/hv;Ljava/util/HashSet;IIII)V
    //   243: invokevirtual 558	com/android/launcher3/model/GridSizeMigrationTask:migrateHotseat	()Z
    //   246: istore 5
    //   248: new 138	android/graphics/Point
    //   251: dup
    //   252: aload 7
    //   254: invokevirtual 490	com/minti/lib/hv:a	()I
    //   257: aload 7
    //   259: invokevirtual 492	com/minti/lib/hv:b	()I
    //   262: invokespecial 561	android/graphics/Point:<init>	(II)V
    //   265: astore 12
    //   267: aload 8
    //   269: ldc 22
    //   271: aload 9
    //   273: invokeinterface 502 3 0
    //   278: invokestatic 553	com/android/launcher3/model/GridSizeMigrationTask:parsePoint	(Ljava/lang/String;)Landroid/graphics/Point;
    //   281: astore 13
    //   283: aload 12
    //   285: aload 13
    //   287: invokevirtual 562	android/graphics/Point:equals	(Ljava/lang/Object;)Z
    //   290: ifne +675 -> 965
    //   293: new 96	java/util/ArrayList
    //   296: dup
    //   297: invokespecial 97	java/util/ArrayList:<init>	()V
    //   300: astore 7
    //   302: aload 7
    //   304: new 138	android/graphics/Point
    //   307: dup
    //   308: iconst_3
    //   309: iconst_2
    //   310: invokespecial 561	android/graphics/Point:<init>	(II)V
    //   313: invokevirtual 258	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   316: pop
    //   317: aload 7
    //   319: new 138	android/graphics/Point
    //   322: dup
    //   323: iconst_3
    //   324: iconst_3
    //   325: invokespecial 561	android/graphics/Point:<init>	(II)V
    //   328: invokevirtual 258	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   331: pop
    //   332: aload 7
    //   334: new 138	android/graphics/Point
    //   337: dup
    //   338: iconst_4
    //   339: iconst_3
    //   340: invokespecial 561	android/graphics/Point:<init>	(II)V
    //   343: invokevirtual 258	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   346: pop
    //   347: aload 7
    //   349: new 138	android/graphics/Point
    //   352: dup
    //   353: iconst_4
    //   354: iconst_4
    //   355: invokespecial 561	android/graphics/Point:<init>	(II)V
    //   358: invokevirtual 258	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   361: pop
    //   362: aload 7
    //   364: new 138	android/graphics/Point
    //   367: dup
    //   368: iconst_5
    //   369: iconst_5
    //   370: invokespecial 561	android/graphics/Point:<init>	(II)V
    //   373: invokevirtual 258	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   376: pop
    //   377: aload 7
    //   379: new 138	android/graphics/Point
    //   382: dup
    //   383: bipush 6
    //   385: iconst_5
    //   386: invokespecial 561	android/graphics/Point:<init>	(II)V
    //   389: invokevirtual 258	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   392: pop
    //   393: aload 7
    //   395: new 138	android/graphics/Point
    //   398: dup
    //   399: bipush 6
    //   401: bipush 6
    //   403: invokespecial 561	android/graphics/Point:<init>	(II)V
    //   406: invokevirtual 258	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   409: pop
    //   410: aload 7
    //   412: new 138	android/graphics/Point
    //   415: dup
    //   416: bipush 7
    //   418: bipush 7
    //   420: invokespecial 561	android/graphics/Point:<init>	(II)V
    //   423: invokevirtual 258	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   426: pop
    //   427: aload 7
    //   429: aload 13
    //   431: invokevirtual 566	java/util/ArrayList:indexOf	(Ljava/lang/Object;)I
    //   434: istore_1
    //   435: aload 7
    //   437: aload 12
    //   439: invokevirtual 566	java/util/ArrayList:indexOf	(Ljava/lang/Object;)I
    //   442: istore_2
    //   443: iload_1
    //   444: iconst_m1
    //   445: if_icmple +156 -> 601
    //   448: iload_2
    //   449: iconst_m1
    //   450: if_icmpgt +6 -> 456
    //   453: goto +148 -> 601
    //   456: new 86	java/util/HashMap
    //   459: dup
    //   460: invokespecial 87	java/util/HashMap:<init>	()V
    //   463: astore 12
    //   465: aload_0
    //   466: invokestatic 443	com/minti/lib/jp:c	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   469: ldc 25
    //   471: invokestatic 571	java/util/Collections:emptySet	()Ljava/util/Set;
    //   474: invokeinterface 575 3 0
    //   479: invokeinterface 578 1 0
    //   484: astore 13
    //   486: aload 13
    //   488: invokeinterface 246 1 0
    //   493: ifeq +462 -> 955
    //   496: aload 13
    //   498: invokeinterface 250 1 0
    //   503: checkcast 268	java/lang/String
    //   506: ldc_w 580
    //   509: invokevirtual 584	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   512: astore 14
    //   514: aload 12
    //   516: aload 14
    //   518: iconst_0
    //   519: aaload
    //   520: aload 14
    //   522: iconst_1
    //   523: aaload
    //   524: invokestatic 553	com/android/launcher3/model/GridSizeMigrationTask:parsePoint	(Ljava/lang/String;)Landroid/graphics/Point;
    //   527: invokevirtual 588	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   530: pop
    //   531: goto -45 -> 486
    //   534: iload 5
    //   536: istore 6
    //   538: iload_2
    //   539: iload_1
    //   540: if_icmpge +111 -> 651
    //   543: aload 7
    //   545: iload_1
    //   546: iconst_1
    //   547: isub
    //   548: invokevirtual 591	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   551: checkcast 138	android/graphics/Point
    //   554: astore 13
    //   556: aload 7
    //   558: iload_1
    //   559: invokevirtual 591	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   562: checkcast 138	android/graphics/Point
    //   565: astore 14
    //   567: new 2	com/android/launcher3/model/GridSizeMigrationTask
    //   570: dup
    //   571: aload_0
    //   572: invokestatic 482	com/minti/lib/ib:a	()Lcom/minti/lib/ib;
    //   575: invokevirtual 486	com/minti/lib/ib:l	()Lcom/minti/lib/hv;
    //   578: aload 11
    //   580: aload 12
    //   582: aload 14
    //   584: aload 13
    //   586: invokespecial 593	com/android/launcher3/model/GridSizeMigrationTask:<init>	(Landroid/content/Context;Lcom/minti/lib/hv;Ljava/util/HashSet;Ljava/util/HashMap;Landroid/graphics/Point;Landroid/graphics/Point;)V
    //   589: invokevirtual 596	com/android/launcher3/model/GridSizeMigrationTask:migrateWorkspace	()Z
    //   592: ifeq +366 -> 958
    //   595: iconst_1
    //   596: istore 5
    //   598: goto +360 -> 958
    //   601: new 189	java/lang/StringBuilder
    //   604: dup
    //   605: invokespecial 190	java/lang/StringBuilder:<init>	()V
    //   608: astore_0
    //   609: aload_0
    //   610: ldc_w 598
    //   613: invokevirtual 196	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   616: pop
    //   617: aload_0
    //   618: aload 13
    //   620: invokevirtual 601	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   623: pop
    //   624: aload_0
    //   625: ldc_w 603
    //   628: invokevirtual 196	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   631: pop
    //   632: aload_0
    //   633: aload 12
    //   635: invokevirtual 601	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   638: pop
    //   639: new 168	java/lang/Exception
    //   642: dup
    //   643: aload_0
    //   644: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   647: invokespecial 357	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   650: athrow
    //   651: iload 6
    //   653: ifeq +48 -> 701
    //   656: aload_0
    //   657: invokevirtual 177	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   660: getstatic 220	com/minti/lib/in$c:n	Landroid/net/Uri;
    //   663: aconst_null
    //   664: aconst_null
    //   665: aconst_null
    //   666: aconst_null
    //   667: invokevirtual 606	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   670: astore_0
    //   671: aload_0
    //   672: invokeinterface 279 1 0
    //   677: istore 5
    //   679: aload_0
    //   680: invokeinterface 306 1 0
    //   685: iload 5
    //   687: ifne +14 -> 701
    //   690: new 168	java/lang/Exception
    //   693: dup
    //   694: ldc_w 608
    //   697: invokespecial 357	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   700: athrow
    //   701: new 189	java/lang/StringBuilder
    //   704: dup
    //   705: invokespecial 190	java/lang/StringBuilder:<init>	()V
    //   708: astore_0
    //   709: aload_0
    //   710: ldc_w 610
    //   713: invokevirtual 196	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   716: pop
    //   717: aload_0
    //   718: invokestatic 511	java/lang/System:currentTimeMillis	()J
    //   721: lload_3
    //   722: lsub
    //   723: invokevirtual 266	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   726: pop
    //   727: ldc 28
    //   729: aload_0
    //   730: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   733: invokestatic 613	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   736: pop
    //   737: aload 8
    //   739: invokeinterface 449 1 0
    //   744: ldc 22
    //   746: aload 9
    //   748: invokeinterface 463 3 0
    //   753: ldc 19
    //   755: aload 10
    //   757: invokeinterface 463 3 0
    //   762: ldc 25
    //   764: invokeinterface 617 2 0
    //   769: invokeinterface 474 1 0
    //   774: iconst_1
    //   775: ireturn
    //   776: astore_0
    //   777: goto +93 -> 870
    //   780: astore_0
    //   781: ldc 28
    //   783: ldc_w 619
    //   786: aload_0
    //   787: invokestatic 622	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   790: pop
    //   791: aload_0
    //   792: invokestatic 293	com/crashlytics/android/Crashlytics:logException	(Ljava/lang/Throwable;)V
    //   795: new 189	java/lang/StringBuilder
    //   798: dup
    //   799: invokespecial 190	java/lang/StringBuilder:<init>	()V
    //   802: astore_0
    //   803: aload_0
    //   804: ldc_w 610
    //   807: invokevirtual 196	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   810: pop
    //   811: aload_0
    //   812: invokestatic 511	java/lang/System:currentTimeMillis	()J
    //   815: lload_3
    //   816: lsub
    //   817: invokevirtual 266	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   820: pop
    //   821: ldc 28
    //   823: aload_0
    //   824: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   827: invokestatic 613	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   830: pop
    //   831: aload 8
    //   833: invokeinterface 449 1 0
    //   838: ldc 22
    //   840: aload 9
    //   842: invokeinterface 463 3 0
    //   847: ldc 19
    //   849: aload 10
    //   851: invokeinterface 463 3 0
    //   856: ldc 25
    //   858: invokeinterface 617 2 0
    //   863: invokeinterface 474 1 0
    //   868: iconst_0
    //   869: ireturn
    //   870: new 189	java/lang/StringBuilder
    //   873: dup
    //   874: invokespecial 190	java/lang/StringBuilder:<init>	()V
    //   877: astore 7
    //   879: aload 7
    //   881: ldc_w 610
    //   884: invokevirtual 196	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   887: pop
    //   888: aload 7
    //   890: invokestatic 511	java/lang/System:currentTimeMillis	()J
    //   893: lload_3
    //   894: lsub
    //   895: invokevirtual 266	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   898: pop
    //   899: ldc 28
    //   901: aload 7
    //   903: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   906: invokestatic 613	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   909: pop
    //   910: aload 8
    //   912: invokeinterface 449 1 0
    //   917: ldc 22
    //   919: aload 9
    //   921: invokeinterface 463 3 0
    //   926: ldc 19
    //   928: aload 10
    //   930: invokeinterface 463 3 0
    //   935: ldc 25
    //   937: invokeinterface 617 2 0
    //   942: invokeinterface 474 1 0
    //   947: aload_0
    //   948: athrow
    //   949: iconst_0
    //   950: istore 5
    //   952: goto -704 -> 248
    //   955: goto -421 -> 534
    //   958: iload_1
    //   959: iconst_1
    //   960: isub
    //   961: istore_1
    //   962: goto -428 -> 534
    //   965: iload 5
    //   967: istore 6
    //   969: goto -318 -> 651
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	972	0	paramContext	Context
    //   434	528	1	i	int
    //   442	99	2	j	int
    //   89	805	3	l	long
    //   246	720	5	bool1	boolean
    //   536	432	6	bool2	boolean
    //   12	890	7	localObject1	Object
    //   4	907	8	localSharedPreferences	SharedPreferences
    //   27	893	9	str1	String
    //   42	887	10	str2	String
    //   97	482	11	localHashSet	HashSet
    //   112	522	12	localObject2	Object
    //   281	338	13	localObject3	Object
    //   512	71	14	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   90	114	776	finally
    //   114	143	776	finally
    //   146	204	776	finally
    //   207	248	776	finally
    //   248	443	776	finally
    //   456	486	776	finally
    //   486	531	776	finally
    //   543	567	776	finally
    //   567	595	776	finally
    //   601	651	776	finally
    //   656	685	776	finally
    //   690	701	776	finally
    //   781	795	776	finally
    //   90	114	780	java/lang/Exception
    //   114	143	780	java/lang/Exception
    //   146	204	780	java/lang/Exception
    //   207	248	780	java/lang/Exception
    //   248	443	780	java/lang/Exception
    //   456	486	780	java/lang/Exception
    //   486	531	780	java/lang/Exception
    //   543	567	780	java/lang/Exception
    //   567	595	780	java/lang/Exception
    //   601	651	780	java/lang/Exception
    //   656	685	780	java/lang/Exception
    //   690	701	780	java/lang/Exception
  }
  
  private void migrateScreen(long paramLong)
  {
    Object localObject4 = loadWorkspaceEntries(paramLong);
    Object localObject5 = new float[2];
    int i = Integer.MAX_VALUE;
    Object localObject1 = null;
    int k = 0;
    int j = Integer.MAX_VALUE;
    float f1 = Float.MAX_VALUE;
    int m;
    int n;
    Object localObject2;
    float f3;
    float f4;
    for (float f2 = Float.MAX_VALUE;; f2 = f4)
    {
      m = i;
      n = j;
      localObject2 = localObject1;
      f3 = f1;
      if (k >= this.mSrcX) {
        break;
      }
      m = i;
      n = 0;
      f3 = f1;
      i = j;
      j = m;
      m = n;
      int i1;
      for (;;)
      {
        i1 = j;
        n = i;
        f1 = f3;
        f4 = f2;
        localObject2 = localObject1;
        if (m >= this.mSrcY) {
          break;
        }
        localObject3 = tryRemove(k, m, deepCopy((ArrayList)localObject4), (float[])localObject5);
        if (localObject5[0] >= f3)
        {
          i1 = j;
          n = i;
          f1 = f3;
          f4 = f2;
          localObject2 = localObject1;
          if (localObject5[0] == f3)
          {
            i1 = j;
            n = i;
            f1 = f3;
            f4 = f2;
            localObject2 = localObject1;
            if (localObject5[1] >= f2) {}
          }
        }
        else
        {
          f1 = localObject5[0];
          f4 = localObject5[1];
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
        m += 1;
        j = i1;
        i = n;
        f3 = f1;
        f2 = f4;
        localObject1 = localObject2;
      }
      i = i1;
      j = n;
      localObject1 = localObject2;
      if (!this.mShouldRemoveX)
      {
        m = i;
        n = j;
        localObject2 = localObject1;
        f3 = f1;
        break;
      }
      k += 1;
    }
    Log.d("GridSizeMigrationTask", String.format("Removing row %d, column %d on screen %d", new Object[] { Integer.valueOf(n), Integer.valueOf(m), Long.valueOf(paramLong) }));
    localObject1 = new xl();
    Object localObject3 = deepCopy((ArrayList)localObject4).iterator();
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (DbEntry)((Iterator)localObject3).next();
      ((xl)localObject1).put(((DbEntry)localObject4).id, localObject4);
    }
    localObject3 = ((ArrayList)localObject2).iterator();
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (DbEntry)((Iterator)localObject3).next();
      localObject5 = (DbEntry)((xl)localObject1).get(((DbEntry)localObject4).id);
      ((xl)localObject1).remove(((DbEntry)localObject4).id);
      if (!((DbEntry)localObject4).columnsSame((DbEntry)localObject5)) {
        update((DbEntry)localObject4);
      }
    }
    localObject1 = ((xl)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject3 = (DbEntry)((Iterator)localObject1).next();
      this.mCarryOver.add(localObject3);
    }
    if ((!this.mCarryOver.isEmpty()) && (f3 == 0.0F))
    {
      localObject1 = (boolean[][])Array.newInstance(Boolean.TYPE, new int[] { this.mTrgX, this.mTrgY });
      localObject2 = ((ArrayList)localObject2).iterator();
      while (((Iterator)localObject2).hasNext()) {
        markCells((boolean[][])localObject1, (DbEntry)((Iterator)localObject2).next(), true);
      }
      localObject1 = new OptimalPlacementSolution((boolean[][])localObject1, deepCopy(this.mCarryOver), true);
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
  
  private static Point parsePoint(String paramString)
  {
    paramString = paramString.split(",");
    return new Point(Integer.parseInt(paramString[0]), Integer.parseInt(paramString[1]));
  }
  
  private ArrayList<DbEntry> tryRemove(int paramInt1, int paramInt2, ArrayList<DbEntry> paramArrayList, float[] paramArrayOfFloat)
  {
    boolean[][] arrayOfBoolean = (boolean[][])Array.newInstance(Boolean.TYPE, new int[] { this.mTrgX, this.mTrgY });
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
        markCells(arrayOfBoolean, localDbEntry, true);
      }
    }
    paramArrayList = new OptimalPlacementSolution(arrayOfBoolean, localArrayList2);
    paramArrayList.find();
    localArrayList1.addAll(paramArrayList.finalPlacedItems);
    paramArrayOfFloat[0] = paramArrayList.lowestWeightLoss;
    paramArrayOfFloat[1] = paramArrayList.lowestMoveCost;
    return localArrayList1;
  }
  
  private void update(DbEntry paramDbEntry)
  {
    this.mTempValues.clear();
    paramDbEntry.addToContentValues(this.mTempValues);
    this.mUpdateOperations.add(ContentProviderOperation.newUpdate(in.c.a(paramDbEntry.id)).withValues(this.mTempValues).build());
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
    if (!this.mValidPackages.contains(paramString)) {
      throw new Exception("Package not available");
    }
  }
  
  protected boolean migrateHotseat()
    throws Exception
  {
    ArrayList localArrayList = loadHotseatEntries();
    int i = this.mDestHotseatSize;
    DbEntry localDbEntry;
    while (localArrayList.size() > i - 1)
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
    i = 0;
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
      if (j == this.mDestAllAppsRank) {
        i = j + 1;
      }
    }
    return applyOperations();
  }
  
  protected boolean migrateWorkspace()
    throws Exception
  {
    ArrayList localArrayList = ik.a(this.mContext);
    if (localArrayList.isEmpty()) {
      throw new Exception("Unable to get workspace screens");
    }
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
      localObject1 = new xl();
      localObject2 = this.mCarryOver.iterator();
      DbEntry localDbEntry;
      while (((Iterator)localObject2).hasNext())
      {
        localDbEntry = (DbEntry)((Iterator)localObject2).next();
        ((xl)localObject1).put(localDbEntry.id, localDbEntry);
      }
      do
      {
        localObject2 = new OptimalPlacementSolution((boolean[][])Array.newInstance(Boolean.TYPE, new int[] { this.mTrgX, this.mTrgY }), deepCopy(this.mCarryOver), true);
        ((OptimalPlacementSolution)localObject2).find();
        if (((OptimalPlacementSolution)localObject2).finalPlacedItems.size() <= 0) {
          break;
        }
        l = in.f.a(this.mContext.getContentResolver(), "generate_new_screen_id").getLong("value");
        localArrayList.add(Long.valueOf(l));
        localObject2 = ((OptimalPlacementSolution)localObject2).finalPlacedItems.iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localDbEntry = (DbEntry)((Iterator)localObject2).next();
          if (!this.mCarryOver.remove(((xl)localObject1).get(localDbEntry.id))) {
            throw new Exception("Unable to find matching items");
          }
          localDbEntry.screenId = l;
          update(localDbEntry);
        }
      } while (!this.mCarryOver.isEmpty());
      localObject1 = in.g.b;
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
  
  static class DbEntry
    extends hx
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
  
  class OptimalPlacementSolution
  {
    ArrayList<GridSizeMigrationTask.DbEntry> finalPlacedItems;
    private final boolean ignoreMove;
    private final ArrayList<GridSizeMigrationTask.DbEntry> itemsToPlace;
    float lowestMoveCost = Float.MAX_VALUE;
    float lowestWeightLoss = Float.MAX_VALUE;
    private final boolean[][] occupied;
    
    public OptimalPlacementSolution(ArrayList<GridSizeMigrationTask.DbEntry> paramArrayList)
    {
      this(paramArrayList, localArrayList, false);
    }
    
    public OptimalPlacementSolution(ArrayList<GridSizeMigrationTask.DbEntry> paramArrayList, boolean paramBoolean)
    {
      this.occupied = paramArrayList;
      this.itemsToPlace = paramBoolean;
      boolean bool;
      this.ignoreMove = bool;
      Collections.sort(this.itemsToPlace);
    }
    
    public void find()
    {
      find(0, 0.0F, 0.0F, new ArrayList());
    }
    
    public void find(int paramInt, float paramFloat1, float paramFloat2, ArrayList<GridSizeMigrationTask.DbEntry> paramArrayList)
    {
      float f3 = paramFloat1;
      paramFloat1 = paramFloat2;
      if (f3 < this.lowestWeightLoss)
      {
        if ((f3 == this.lowestWeightLoss) && (paramFloat1 >= this.lowestMoveCost)) {
          return;
        }
        if (paramInt >= this.itemsToPlace.size())
        {
          this.lowestWeightLoss = f3;
          this.lowestMoveCost = paramFloat1;
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
          i = 0;
          k = Integer.MAX_VALUE;
          int n = Integer.MAX_VALUE;
          int i1 = Integer.MAX_VALUE;
          while (i < GridSizeMigrationTask.this.mTrgY)
          {
            j = 0;
            while (j < GridSizeMigrationTask.this.mTrgX)
            {
              if (this.occupied[j][i] == 0)
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
              paramFloat2 = paramFloat1 + 1.0F;
            }
            else
            {
              paramFloat2 = paramFloat1;
            }
            f1 = paramFloat2;
            if (n != i3)
            {
              localDbEntry.cellY = n;
              f1 = paramFloat2 + 1.0F;
            }
            paramFloat2 = f1;
            if (this.ignoreMove) {
              paramFloat2 = paramFloat1;
            }
            GridSizeMigrationTask.this.markCells(this.occupied, localDbEntry, true);
            paramInt += 1;
            find(paramInt, f3, paramFloat2, localArrayList);
            GridSizeMigrationTask.this.markCells(this.occupied, localDbEntry, false);
            localDbEntry.cellX = i2;
            localDbEntry.cellY = i3;
            if ((paramInt < this.itemsToPlace.size()) && (((GridSizeMigrationTask.DbEntry)this.itemsToPlace.get(paramInt)).weight >= localDbEntry.weight) && (!this.ignoreMove)) {
              find(paramInt, f3 + localDbEntry.weight, paramFloat1, paramArrayList);
            }
          }
          else
          {
            paramInt += 1;
            while (paramInt < this.itemsToPlace.size())
            {
              f3 += ((GridSizeMigrationTask.DbEntry)this.itemsToPlace.get(paramInt)).weight;
              paramInt += 1;
            }
            find(this.itemsToPlace.size(), f3 + localDbEntry.weight, paramFloat1, paramArrayList);
          }
        }
        else
        {
          m = localDbEntry.spanX;
          i = localDbEntry.spanY;
          j = 0;
          while (j < GridSizeMigrationTask.this.mTrgY)
          {
            k = 0;
            for (;;)
            {
              paramFloat1 = paramFloat2;
              if (k >= GridSizeMigrationTask.this.mTrgX) {
                break;
              }
              if (k != i2)
              {
                localDbEntry.cellX = k;
                f1 = paramFloat1 + 1.0F;
              }
              else
              {
                f1 = paramFloat1;
              }
              float f2 = f1;
              if (j != i3)
              {
                localDbEntry.cellY = j;
                f2 = f1 + 1.0F;
              }
              if (!this.ignoreMove) {
                paramFloat1 = f2;
              }
              if (GridSizeMigrationTask.this.isVacant(this.occupied, k, j, m, i))
              {
                GridSizeMigrationTask.this.markCells(this.occupied, localDbEntry, true);
                find(paramInt + 1, f3, paramFloat1, localArrayList);
                GridSizeMigrationTask.this.markCells(this.occupied, localDbEntry, false);
              }
              if ((m > localDbEntry.minSpanX) && (GridSizeMigrationTask.this.isVacant(this.occupied, k, j, m - 1, i)))
              {
                localDbEntry.spanX -= 1;
                GridSizeMigrationTask.this.markCells(this.occupied, localDbEntry, true);
                find(paramInt + 1, f3, paramFloat1 + 1.0F, localArrayList);
                GridSizeMigrationTask.this.markCells(this.occupied, localDbEntry, false);
                localDbEntry.spanX += 1;
              }
              if ((i > localDbEntry.minSpanY) && (GridSizeMigrationTask.this.isVacant(this.occupied, k, j, m, i - 1)))
              {
                localDbEntry.spanY -= 1;
                GridSizeMigrationTask.this.markCells(this.occupied, localDbEntry, true);
                find(paramInt + 1, f3, paramFloat1 + 1.0F, localArrayList);
                GridSizeMigrationTask.this.markCells(this.occupied, localDbEntry, false);
                localDbEntry.spanY += 1;
              }
              if ((i > localDbEntry.minSpanY) && (m > localDbEntry.minSpanX) && (GridSizeMigrationTask.this.isVacant(this.occupied, k, j, m - 1, i - 1)))
              {
                localDbEntry.spanX -= 1;
                localDbEntry.spanY -= 1;
                GridSizeMigrationTask.this.markCells(this.occupied, localDbEntry, true);
                find(paramInt + 1, f3, paramFloat1 + 2.0F, localArrayList);
                GridSizeMigrationTask.this.markCells(this.occupied, localDbEntry, false);
                localDbEntry.spanX += 1;
                localDbEntry.spanY += 1;
              }
              localDbEntry.cellX = i2;
              localDbEntry.cellY = i3;
              k += 1;
            }
            j += 1;
          }
          find(paramInt + 1, f3 + localDbEntry.weight, paramFloat2, paramArrayList);
        }
        return;
      }
    }
  }
}
