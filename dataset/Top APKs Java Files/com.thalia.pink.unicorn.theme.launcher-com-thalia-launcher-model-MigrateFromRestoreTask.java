package com.thalia.launcher.model;

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
import android.text.TextUtils;
import android.util.Log;
import com.thalia.launcher.InvariantDeviceProfile;
import com.thalia.launcher.ItemInfo;
import com.thalia.launcher.LauncherAppState;
import com.thalia.launcher.LauncherModel;
import com.thalia.launcher.LauncherProvider;
import com.thalia.launcher.LauncherSettings.Favorites;
import com.thalia.launcher.Utilities;
import com.thalia.launcher.compat.PackageInstallerCompat;
import com.thalia.launcher.util.LongArrayMap;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MigrateFromRestoreTask
{
  private static final boolean DEBUG = true;
  public static boolean ENABLED = false;
  private static final String KEY_MIGRATION_SOURCE_SIZE = "migration_restore_src_size";
  private static final String KEY_MIGRATION_WIDGET_MINSIZE = "migration_widget_min_size";
  private static final String TAG = "MigrateFromRestoreTask";
  private static final float WT_APPLICATION = 0.8F;
  private static final float WT_FOLDER_FACTOR = 0.5F;
  private static final float WT_SHORTCUT = 1.0F;
  private static final float WT_WIDGET_FACTOR = 0.6F;
  private static final float WT_WIDGET_MIN = 2.0F;
  private ArrayList<DbEntry> mCarryOver;
  private final Context mContext;
  public ArrayList<Long> mEntryToRemove;
  private final InvariantDeviceProfile mIdp;
  private final boolean mShouldRemoveX;
  private final boolean mShouldRemoveY;
  private final int mSrcX;
  private final int mSrcY;
  private final ContentValues mTempValues = new ContentValues();
  final int mTrgX;
  final int mTrgY;
  private ArrayList<ContentProviderOperation> mUpdateOperations;
  private HashSet<String> mValidPackages;
  private final HashMap<String, Point> mWidgetMinSize;
  
  public MigrateFromRestoreTask(Context paramContext)
  {
    this.mContext = paramContext;
    paramContext = prefs(paramContext);
    Object localObject = parsePoint(paramContext.getString("migration_restore_src_size", ""));
    this.mSrcX = ((Point)localObject).x;
    this.mSrcY = ((Point)localObject).y;
    this.mWidgetMinSize = new HashMap();
    paramContext = paramContext.getStringSet("migration_widget_min_size", Collections.emptySet()).iterator();
    boolean bool1;
    boolean bool2;
    for (;;)
    {
      bool1 = paramContext.hasNext();
      bool2 = true;
      if (!bool1) {
        break;
      }
      localObject = ((String)paramContext.next()).split("#");
      this.mWidgetMinSize.put(localObject[0], parsePoint(localObject[1]));
    }
    this.mIdp = LauncherAppState.getInstance().getInvariantDeviceProfile();
    this.mTrgX = this.mIdp.numColumns;
    this.mTrgY = this.mIdp.numRows;
    if (this.mTrgX < this.mSrcX) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    this.mShouldRemoveX = bool1;
    if (this.mTrgY < this.mSrcY) {
      bool1 = bool2;
    } else {
      bool1 = false;
    }
    this.mShouldRemoveY = bool1;
  }
  
  public static void clearFlags(Context paramContext)
  {
    prefs(paramContext).edit().remove("migration_restore_src_size").remove("migration_widget_min_size").commit();
  }
  
  static ArrayList<DbEntry> deepCopy(ArrayList<DbEntry> paramArrayList)
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
    Uri localUri = LauncherSettings.Favorites.CONTENT_URI;
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("container = ");
    ((StringBuilder)localObject2).append(paramLong);
    localObject2 = ((StringBuilder)localObject2).toString();
    localObject1 = ((ContentResolver)localObject1).query(localUri, new String[] { "_id", "intent" }, (String)localObject2, null, null, null);
    int i = 0;
    while (((Cursor)localObject1).moveToNext())
    {
      try
      {
        verifyIntent(((Cursor)localObject1).getString(1));
        i += 1;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
      this.mEntryToRemove.add(Long.valueOf(((Cursor)localObject1).getLong(0)));
    }
    return i;
  }
  
  public static void markForMigration(Context paramContext, int paramInt1, int paramInt2, HashSet<String> paramHashSet)
  {
    paramContext = prefs(paramContext).edit();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramInt1);
    localStringBuilder.append(",");
    localStringBuilder.append(paramInt2);
    paramContext.putString("migration_restore_src_size", localStringBuilder.toString()).putStringSet("migration_widget_min_size", paramHashSet).apply();
  }
  
  private void migrateScreen(long paramLong)
  {
    Object localObject4 = loadEntries(paramLong);
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
    Log.d("MigrateFromRestoreTask", String.format("Removing row %d, column %d on screen %d", new Object[] { Integer.valueOf(n), Integer.valueOf(m), Long.valueOf(paramLong) }));
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
  
  private static SharedPreferences prefs(Context paramContext)
  {
    return paramContext.getSharedPreferences(LauncherAppState.getSharedPreferencesKey(), 0);
  }
  
  public static boolean shouldRunTask(Context paramContext)
  {
    return TextUtils.isEmpty(prefs(paramContext).getString("migration_restore_src_size", "")) ^ true;
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
    this.mUpdateOperations.add(ContentProviderOperation.newUpdate(LauncherSettings.Favorites.getContentUri(paramDbEntry.id)).withValues(this.mTempValues).build());
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
  
  public void execute()
    throws Exception
  {
    this.mEntryToRemove = new ArrayList();
    this.mCarryOver = new ArrayList();
    this.mUpdateOperations = new ArrayList();
    this.mValidPackages = new HashSet();
    Object localObject1 = this.mContext.getPackageManager().getInstalledPackages(0).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (PackageInfo)((Iterator)localObject1).next();
      this.mValidPackages.add(((PackageInfo)localObject2).packageName);
    }
    this.mValidPackages.addAll(PackageInstallerCompat.getInstance(this.mContext).updateAndGetActiveSessionCache().keySet());
    localObject1 = LauncherModel.loadWorkspaceScreensDb(this.mContext);
    if (((ArrayList)localObject1).isEmpty()) {
      throw new Exception("Unable to get workspace screens");
    }
    Object localObject2 = ((ArrayList)localObject1).iterator();
    long l;
    Object localObject3;
    while (((Iterator)localObject2).hasNext())
    {
      l = ((Long)((Iterator)localObject2).next()).longValue();
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("Migrating ");
      ((StringBuilder)localObject3).append(l);
      Log.d("MigrateFromRestoreTask", ((StringBuilder)localObject3).toString());
      migrateScreen(l);
    }
    if (!this.mCarryOver.isEmpty())
    {
      localObject2 = new LongArrayMap();
      localObject3 = this.mCarryOver.iterator();
      DbEntry localDbEntry;
      while (((Iterator)localObject3).hasNext())
      {
        localDbEntry = (DbEntry)((Iterator)localObject3).next();
        ((LongArrayMap)localObject2).put(localDbEntry.id, localDbEntry);
      }
      do
      {
        localObject3 = new OptimalPlacementSolution((boolean[][])Array.newInstance(Boolean.TYPE, new int[] { this.mTrgX, this.mTrgY }), deepCopy(this.mCarryOver), true);
        ((OptimalPlacementSolution)localObject3).find();
        if (((OptimalPlacementSolution)localObject3).finalPlacedItems.size() <= 0) {
          break;
        }
        l = LauncherAppState.getLauncherProvider().generateNewScreenId();
        ((ArrayList)localObject1).add(Long.valueOf(l));
        localObject3 = ((OptimalPlacementSolution)localObject3).finalPlacedItems.iterator();
        while (((Iterator)localObject3).hasNext())
        {
          localDbEntry = (DbEntry)((Iterator)localObject3).next();
          if (!this.mCarryOver.remove(((LongArrayMap)localObject2).get(localDbEntry.id))) {
            throw new Exception("Unable to find matching items");
          }
          localDbEntry.screenId = l;
          update(localDbEntry);
        }
      } while (!this.mCarryOver.isEmpty());
      LauncherAppState.getInstance().getModel().updateWorkspaceScreenOrder(this.mContext, (ArrayList)localObject1);
      break label483;
      throw new Exception("None of the items can be placed on an empty screen");
    }
    label483:
    this.mContext.getContentResolver().applyBatch(LauncherProvider.AUTHORITY, this.mUpdateOperations);
    if (!this.mEntryToRemove.isEmpty())
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Removing items: ");
      ((StringBuilder)localObject1).append(TextUtils.join(", ", this.mEntryToRemove));
      Log.d("MigrateFromRestoreTask", ((StringBuilder)localObject1).toString());
      this.mContext.getContentResolver().delete(LauncherSettings.Favorites.CONTENT_URI, Utilities.createDbSelectionQuery("_id", this.mEntryToRemove), null);
    }
    localObject1 = this.mContext.getContentResolver().query(LauncherSettings.Favorites.CONTENT_URI, null, null, null, null);
    boolean bool = ((Cursor)localObject1).moveToNext();
    ((Cursor)localObject1).close();
    if (!bool) {
      throw new Exception("Removed every thing during grid resize");
    }
  }
  
  boolean isVacant(boolean[][] paramArrayOfBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
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
  
  /* Error */
  public ArrayList<DbEntry> loadEntries(long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 76	com/thalia/launcher/model/MigrateFromRestoreTask:mContext	Landroid/content/Context;
    //   4: invokevirtual 221	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   7: astore 14
    //   9: getstatic 227	com/thalia/launcher/LauncherSettings$Favorites:CONTENT_URI	Landroid/net/Uri;
    //   12: astore 15
    //   14: new 229	java/lang/StringBuilder
    //   17: dup
    //   18: invokespecial 230	java/lang/StringBuilder:<init>	()V
    //   21: astore 16
    //   23: aload 16
    //   25: ldc_w 608
    //   28: invokevirtual 236	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: pop
    //   32: aload 16
    //   34: lload_1
    //   35: invokevirtual 239	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   38: pop
    //   39: aload 16
    //   41: invokevirtual 243	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   44: astore 16
    //   46: aload 14
    //   48: aload 15
    //   50: bipush 8
    //   52: anewarray 137	java/lang/String
    //   55: dup
    //   56: iconst_0
    //   57: ldc -11
    //   59: aastore
    //   60: dup
    //   61: iconst_1
    //   62: ldc_w 610
    //   65: aastore
    //   66: dup
    //   67: iconst_2
    //   68: ldc_w 611
    //   71: aastore
    //   72: dup
    //   73: iconst_3
    //   74: ldc_w 612
    //   77: aastore
    //   78: dup
    //   79: iconst_4
    //   80: ldc_w 613
    //   83: aastore
    //   84: dup
    //   85: iconst_5
    //   86: ldc_w 614
    //   89: aastore
    //   90: dup
    //   91: bipush 6
    //   93: ldc -9
    //   95: aastore
    //   96: dup
    //   97: bipush 7
    //   99: ldc_w 616
    //   102: aastore
    //   103: aload 16
    //   105: aconst_null
    //   106: aconst_null
    //   107: aconst_null
    //   108: invokevirtual 253	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Landroid/os/CancellationSignal;)Landroid/database/Cursor;
    //   111: astore 14
    //   113: aload 14
    //   115: ldc -11
    //   117: invokeinterface 619 2 0
    //   122: istore 6
    //   124: aload 14
    //   126: ldc_w 610
    //   129: invokeinterface 619 2 0
    //   134: istore 5
    //   136: aload 14
    //   138: ldc_w 611
    //   141: invokeinterface 619 2 0
    //   146: istore 8
    //   148: aload 14
    //   150: ldc_w 612
    //   153: invokeinterface 619 2 0
    //   158: istore 9
    //   160: aload 14
    //   162: ldc_w 613
    //   165: invokeinterface 619 2 0
    //   170: istore 10
    //   172: aload 14
    //   174: ldc_w 614
    //   177: invokeinterface 619 2 0
    //   182: istore 11
    //   184: aload 14
    //   186: ldc -9
    //   188: invokeinterface 619 2 0
    //   193: istore 12
    //   195: aload 14
    //   197: ldc_w 616
    //   200: invokeinterface 619 2 0
    //   205: istore 4
    //   207: new 193	java/util/ArrayList
    //   210: dup
    //   211: invokespecial 415	java/util/ArrayList:<init>	()V
    //   214: astore 16
    //   216: aload 14
    //   218: invokeinterface 258 1 0
    //   223: ifeq +566 -> 789
    //   226: new 6	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry
    //   229: dup
    //   230: invokespecial 620	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:<init>	()V
    //   233: astore 17
    //   235: aload 17
    //   237: aload 14
    //   239: iload 6
    //   241: invokeinterface 271 2 0
    //   246: putfield 334	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:id	J
    //   249: aload 17
    //   251: aload 14
    //   253: iload 5
    //   255: invokeinterface 624 2 0
    //   260: putfield 626	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:itemType	I
    //   263: aload 17
    //   265: aload 14
    //   267: iload 8
    //   269: invokeinterface 624 2 0
    //   274: putfield 418	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:cellX	I
    //   277: aload 17
    //   279: aload 14
    //   281: iload 9
    //   283: invokeinterface 624 2 0
    //   288: putfield 424	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:cellY	I
    //   291: aload 17
    //   293: aload 14
    //   295: iload 10
    //   297: invokeinterface 624 2 0
    //   302: putfield 421	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:spanX	I
    //   305: aload 17
    //   307: aload 14
    //   309: iload 11
    //   311: invokeinterface 624 2 0
    //   316: putfield 427	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:spanY	I
    //   319: aload 17
    //   321: lload_1
    //   322: putfield 390	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:screenId	J
    //   325: aload 17
    //   327: getfield 626	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:itemType	I
    //   330: istore 7
    //   332: iload 7
    //   334: iconst_4
    //   335: if_icmpeq +121 -> 456
    //   338: iload 7
    //   340: tableswitch	default:+28->368, 0:+80->420, 1:+80->420, 2:+39->379
    //   368: new 215	java/lang/Exception
    //   371: dup
    //   372: ldc_w 628
    //   375: invokespecial 498	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   378: athrow
    //   379: aload_0
    //   380: aload 17
    //   382: getfield 334	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:id	J
    //   385: invokespecial 630	com/thalia/launcher/model/MigrateFromRestoreTask:getFolderItemsCount	(J)I
    //   388: istore 7
    //   390: iload 7
    //   392: ifne +14 -> 406
    //   395: new 215	java/lang/Exception
    //   398: dup
    //   399: ldc_w 632
    //   402: invokespecial 498	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   405: athrow
    //   406: aload 17
    //   408: iload 7
    //   410: i2f
    //   411: ldc 30
    //   413: fmul
    //   414: putfield 635	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:weight	F
    //   417: goto +380 -> 797
    //   420: aload_0
    //   421: aload 14
    //   423: iload 12
    //   425: invokeinterface 261 2 0
    //   430: invokespecial 265	com/thalia/launcher/model/MigrateFromRestoreTask:verifyIntent	(Ljava/lang/String;)V
    //   433: aload 17
    //   435: getfield 626	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:itemType	I
    //   438: iconst_1
    //   439: if_icmpne +361 -> 800
    //   442: fconst_1
    //   443: fstore_3
    //   444: goto +3 -> 447
    //   447: aload 17
    //   449: fload_3
    //   450: putfield 635	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:weight	F
    //   453: goto +344 -> 797
    //   456: aload 14
    //   458: iload 4
    //   460: invokeinterface 261 2 0
    //   465: astore 15
    //   467: aload 15
    //   469: invokestatic 639	android/content/ComponentName:unflattenFromString	(Ljava/lang/String;)Landroid/content/ComponentName;
    //   472: astore 18
    //   474: aload_0
    //   475: aload 18
    //   477: invokevirtual 480	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   480: invokespecial 483	com/thalia/launcher/model/MigrateFromRestoreTask:verifyPackage	(Ljava/lang/String;)V
    //   483: aload 17
    //   485: getfield 421	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:spanX	I
    //   488: istore 7
    //   490: iload 7
    //   492: i2f
    //   493: fstore_3
    //   494: aload 17
    //   496: fconst_2
    //   497: fload_3
    //   498: ldc 34
    //   500: fmul
    //   501: aload 17
    //   503: getfield 427	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:spanY	I
    //   506: i2f
    //   507: fmul
    //   508: invokestatic 645	java/lang/Math:max	(FF)F
    //   511: putfield 635	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:weight	F
    //   514: aload_0
    //   515: getfield 76	com/thalia/launcher/model/MigrateFromRestoreTask:mContext	Landroid/content/Context;
    //   518: aload 18
    //   520: invokestatic 651	com/thalia/launcher/compat/UserHandleCompat:myUserHandle	()Lcom/thalia/launcher/compat/UserHandleCompat;
    //   523: invokestatic 655	com/thalia/launcher/LauncherModel:getProviderInfo	(Landroid/content/Context;Landroid/content/ComponentName;Lcom/thalia/launcher/compat/UserHandleCompat;)Lcom/thalia/launcher/LauncherAppWidgetProviderInfo;
    //   526: astore 18
    //   528: aload 18
    //   530: ifnonnull +25 -> 555
    //   533: aload_0
    //   534: getfield 109	com/thalia/launcher/model/MigrateFromRestoreTask:mWidgetMinSize	Ljava/util/HashMap;
    //   537: aload 15
    //   539: invokevirtual 658	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   542: checkcast 94	android/graphics/Point
    //   545: astore 15
    //   547: goto +23 -> 570
    //   550: astore 15
    //   552: goto +242 -> 794
    //   555: aload 18
    //   557: aload_0
    //   558: getfield 159	com/thalia/launcher/model/MigrateFromRestoreTask:mIdp	Lcom/thalia/launcher/InvariantDeviceProfile;
    //   561: aload_0
    //   562: getfield 76	com/thalia/launcher/model/MigrateFromRestoreTask:mContext	Landroid/content/Context;
    //   565: invokevirtual 664	com/thalia/launcher/LauncherAppWidgetProviderInfo:getMinSpans	(Lcom/thalia/launcher/InvariantDeviceProfile;Landroid/content/Context;)Landroid/graphics/Point;
    //   568: astore 15
    //   570: aload 15
    //   572: ifnull +70 -> 642
    //   575: aload 15
    //   577: getfield 97	android/graphics/Point:x	I
    //   580: ifle +13 -> 593
    //   583: aload 15
    //   585: getfield 97	android/graphics/Point:x	I
    //   588: istore 7
    //   590: goto +10 -> 600
    //   593: aload 17
    //   595: getfield 421	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:spanX	I
    //   598: istore 7
    //   600: aload 17
    //   602: iload 7
    //   604: putfield 667	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:minSpanX	I
    //   607: aload 15
    //   609: getfield 102	android/graphics/Point:y	I
    //   612: ifle +13 -> 625
    //   615: aload 15
    //   617: getfield 102	android/graphics/Point:y	I
    //   620: istore 7
    //   622: goto +10 -> 632
    //   625: aload 17
    //   627: getfield 427	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:spanY	I
    //   630: istore 7
    //   632: aload 17
    //   634: iload 7
    //   636: putfield 670	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:minSpanY	I
    //   639: goto +15 -> 654
    //   642: aload 17
    //   644: iconst_2
    //   645: putfield 670	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:minSpanY	I
    //   648: aload 17
    //   650: iconst_2
    //   651: putfield 667	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:minSpanX	I
    //   654: aload 17
    //   656: getfield 667	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:minSpanX	I
    //   659: aload_0
    //   660: getfield 166	com/thalia/launcher/model/MigrateFromRestoreTask:mTrgX	I
    //   663: if_icmpgt +37 -> 700
    //   666: aload 17
    //   668: getfield 670	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:minSpanY	I
    //   671: istore 7
    //   673: aload_0
    //   674: getfield 171	com/thalia/launcher/model/MigrateFromRestoreTask:mTrgY	I
    //   677: istore 13
    //   679: iload 7
    //   681: iload 13
    //   683: if_icmple +6 -> 689
    //   686: goto +14 -> 700
    //   689: aload 16
    //   691: aload 17
    //   693: invokevirtual 209	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   696: pop
    //   697: goto +89 -> 786
    //   700: new 215	java/lang/Exception
    //   703: dup
    //   704: ldc_w 672
    //   707: invokespecial 498	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   710: athrow
    //   711: astore 15
    //   713: goto +15 -> 728
    //   716: astore 15
    //   718: goto +10 -> 728
    //   721: astore 15
    //   723: goto +5 -> 728
    //   726: astore 15
    //   728: new 229	java/lang/StringBuilder
    //   731: dup
    //   732: invokespecial 230	java/lang/StringBuilder:<init>	()V
    //   735: astore 18
    //   737: aload 18
    //   739: ldc_w 674
    //   742: invokevirtual 236	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   745: pop
    //   746: aload 18
    //   748: aload 17
    //   750: getfield 334	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:id	J
    //   753: invokevirtual 239	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   756: pop
    //   757: ldc 25
    //   759: aload 18
    //   761: invokevirtual 243	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   764: aload 15
    //   766: invokestatic 677	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   769: pop
    //   770: aload_0
    //   771: getfield 267	com/thalia/launcher/model/MigrateFromRestoreTask:mEntryToRemove	Ljava/util/ArrayList;
    //   774: aload 17
    //   776: getfield 334	com/thalia/launcher/model/MigrateFromRestoreTask$DbEntry:id	J
    //   779: invokestatic 277	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   782: invokevirtual 209	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   785: pop
    //   786: goto -570 -> 216
    //   789: aload 16
    //   791: areturn
    //   792: astore 15
    //   794: goto -66 -> 728
    //   797: goto -108 -> 689
    //   800: ldc 28
    //   802: fstore_3
    //   803: goto -356 -> 447
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	806	0	this	MigrateFromRestoreTask
    //   0	806	1	paramLong	long
    //   443	360	3	f	float
    //   205	254	4	i	int
    //   134	120	5	j	int
    //   122	118	6	k	int
    //   330	354	7	m	int
    //   146	122	8	n	int
    //   158	124	9	i1	int
    //   170	126	10	i2	int
    //   182	128	11	i3	int
    //   193	231	12	i4	int
    //   677	7	13	i5	int
    //   7	450	14	localObject1	Object
    //   12	534	15	localObject2	Object
    //   550	1	15	localException1	Exception
    //   568	48	15	localPoint	Point
    //   711	1	15	localException2	Exception
    //   716	1	15	localException3	Exception
    //   721	1	15	localException4	Exception
    //   726	39	15	localException5	Exception
    //   792	1	15	localException6	Exception
    //   21	769	16	localObject3	Object
    //   233	542	17	localDbEntry	DbEntry
    //   472	288	18	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   533	547	550	java/lang/Exception
    //   575	590	550	java/lang/Exception
    //   593	600	550	java/lang/Exception
    //   600	622	550	java/lang/Exception
    //   625	632	550	java/lang/Exception
    //   632	639	550	java/lang/Exception
    //   642	654	711	java/lang/Exception
    //   654	679	711	java/lang/Exception
    //   700	711	711	java/lang/Exception
    //   494	528	716	java/lang/Exception
    //   555	570	716	java/lang/Exception
    //   456	490	721	java/lang/Exception
    //   325	332	726	java/lang/Exception
    //   368	379	792	java/lang/Exception
    //   379	390	792	java/lang/Exception
    //   395	406	792	java/lang/Exception
    //   406	417	792	java/lang/Exception
    //   420	442	792	java/lang/Exception
    //   447	453	792	java/lang/Exception
  }
  
  void markCells(boolean[][] paramArrayOfBoolean, DbEntry paramDbEntry, boolean paramBoolean)
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
  
  private static class DbEntry
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
  
  private class OptimalPlacementSolution
  {
    ArrayList<MigrateFromRestoreTask.DbEntry> finalPlacedItems;
    private final boolean ignoreMove;
    private final ArrayList<MigrateFromRestoreTask.DbEntry> itemsToPlace;
    float lowestMoveCost = Float.MAX_VALUE;
    float lowestWeightLoss = Float.MAX_VALUE;
    private final boolean[][] occupied;
    
    public OptimalPlacementSolution(ArrayList<MigrateFromRestoreTask.DbEntry> paramArrayList)
    {
      this(paramArrayList, localArrayList, false);
    }
    
    public OptimalPlacementSolution(ArrayList<MigrateFromRestoreTask.DbEntry> paramArrayList, boolean paramBoolean)
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
    
    public void find(int paramInt, float paramFloat1, float paramFloat2, ArrayList<MigrateFromRestoreTask.DbEntry> paramArrayList)
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
          this.finalPlacedItems = MigrateFromRestoreTask.deepCopy(paramArrayList);
          return;
        }
        MigrateFromRestoreTask.DbEntry localDbEntry = (MigrateFromRestoreTask.DbEntry)this.itemsToPlace.get(paramInt);
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
          while (i < MigrateFromRestoreTask.this.mTrgY)
          {
            j = 0;
            while (j < MigrateFromRestoreTask.this.mTrgX)
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
          if ((k < MigrateFromRestoreTask.this.mTrgX) && (n < MigrateFromRestoreTask.this.mTrgY))
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
            MigrateFromRestoreTask.this.markCells(this.occupied, localDbEntry, true);
            paramInt += 1;
            find(paramInt, f3, paramFloat2, localArrayList);
            MigrateFromRestoreTask.this.markCells(this.occupied, localDbEntry, false);
            localDbEntry.cellX = i2;
            localDbEntry.cellY = i3;
            if ((paramInt < this.itemsToPlace.size()) && (((MigrateFromRestoreTask.DbEntry)this.itemsToPlace.get(paramInt)).weight >= localDbEntry.weight) && (!this.ignoreMove)) {
              find(paramInt, f3 + localDbEntry.weight, paramFloat1, paramArrayList);
            }
          }
          else
          {
            paramInt += 1;
            while (paramInt < this.itemsToPlace.size())
            {
              f3 += ((MigrateFromRestoreTask.DbEntry)this.itemsToPlace.get(paramInt)).weight;
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
          while (j < MigrateFromRestoreTask.this.mTrgY)
          {
            k = 0;
            for (;;)
            {
              paramFloat1 = paramFloat2;
              if (k >= MigrateFromRestoreTask.this.mTrgX) {
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
              if (MigrateFromRestoreTask.this.isVacant(this.occupied, k, j, m, i))
              {
                MigrateFromRestoreTask.this.markCells(this.occupied, localDbEntry, true);
                find(paramInt + 1, f3, paramFloat1, localArrayList);
                MigrateFromRestoreTask.this.markCells(this.occupied, localDbEntry, false);
              }
              if ((m > localDbEntry.minSpanX) && (MigrateFromRestoreTask.this.isVacant(this.occupied, k, j, m - 1, i)))
              {
                localDbEntry.spanX -= 1;
                MigrateFromRestoreTask.this.markCells(this.occupied, localDbEntry, true);
                find(paramInt + 1, f3, paramFloat1 + 1.0F, localArrayList);
                MigrateFromRestoreTask.this.markCells(this.occupied, localDbEntry, false);
                localDbEntry.spanX += 1;
              }
              if ((i > localDbEntry.minSpanY) && (MigrateFromRestoreTask.this.isVacant(this.occupied, k, j, m, i - 1)))
              {
                localDbEntry.spanY -= 1;
                MigrateFromRestoreTask.this.markCells(this.occupied, localDbEntry, true);
                find(paramInt + 1, f3, paramFloat1 + 1.0F, localArrayList);
                MigrateFromRestoreTask.this.markCells(this.occupied, localDbEntry, false);
                localDbEntry.spanY += 1;
              }
              if ((i > localDbEntry.minSpanY) && (m > localDbEntry.minSpanX) && (MigrateFromRestoreTask.this.isVacant(this.occupied, k, j, m - 1, i - 1)))
              {
                localDbEntry.spanX -= 1;
                localDbEntry.spanY -= 1;
                MigrateFromRestoreTask.this.markCells(this.occupied, localDbEntry, true);
                find(paramInt + 1, f3, paramFloat1 + 2.0F, localArrayList);
                MigrateFromRestoreTask.this.markCells(this.occupied, localDbEntry, false);
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
