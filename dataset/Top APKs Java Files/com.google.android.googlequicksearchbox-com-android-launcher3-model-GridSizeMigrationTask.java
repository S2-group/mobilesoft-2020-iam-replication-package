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
import android.text.TextUtils;
import android.util.Log;
import com.android.launcher3.InvariantDeviceProfile;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherAppWidgetProviderInfo;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.LauncherProvider;
import com.android.launcher3.LauncherProvider.DatabaseHelper;
import com.android.launcher3.LauncherSettings.Favorites;
import com.android.launcher3.LauncherSettings.WorkspaceScreens;
import com.android.launcher3.Utilities;
import com.android.launcher3.backup.nano.BackupProtos.DeviceProfieData;
import com.android.launcher3.compat.AppWidgetManagerCompat;
import com.android.launcher3.compat.PackageInstallerCompat;
import com.android.launcher3.util.LongArrayMap;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class GridSizeMigrationTask
{
  public static boolean ENABLED = Utilities.ATLEAST_NOUGAT;
  public final ArrayList<GridSizeMigrationTask.DbEntry> mCarryOver = new ArrayList();
  public final Context mContext;
  public final int mDestAllAppsRank;
  public final int mDestHotseatSize;
  public final ArrayList<Long> mEntryToRemove = new ArrayList();
  public final InvariantDeviceProfile mIdp;
  public final boolean mShouldRemoveX;
  public final boolean mShouldRemoveY;
  public final int mSrcAllAppsRank;
  public final int mSrcHotseatSize;
  public final int mSrcX;
  public final int mSrcY;
  public final ContentValues mTempValues = new ContentValues();
  public final int mTrgX;
  public final int mTrgY;
  public final ArrayList<ContentProviderOperation> mUpdateOperations = new ArrayList();
  public final HashSet<String> mValidPackages;
  public final HashMap<String, Point> mWidgetMinSize = new HashMap();
  
  protected GridSizeMigrationTask(Context paramContext, InvariantDeviceProfile paramInvariantDeviceProfile, HashSet<String> paramHashSet, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mContext = paramContext;
    this.mIdp = paramInvariantDeviceProfile;
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
  
  protected GridSizeMigrationTask(Context paramContext, InvariantDeviceProfile paramInvariantDeviceProfile, HashSet<String> paramHashSet, HashMap<String, Point> paramHashMap, Point paramPoint1, Point paramPoint2)
  {
    this.mContext = paramContext;
    this.mValidPackages = paramHashSet;
    this.mWidgetMinSize.putAll(paramHashMap);
    this.mIdp = paramInvariantDeviceProfile;
    this.mSrcX = paramPoint1.x;
    this.mSrcY = paramPoint1.y;
    this.mTrgX = paramPoint2.x;
    this.mTrgY = paramPoint2.y;
    if (this.mTrgX < this.mSrcX)
    {
      bool1 = true;
      this.mShouldRemoveX = bool1;
      if (this.mTrgY >= this.mSrcY) {
        break label190;
      }
    }
    label190:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      this.mShouldRemoveY = bool1;
      this.mDestAllAppsRank = -1;
      this.mDestHotseatSize = -1;
      this.mSrcAllAppsRank = -1;
      this.mSrcHotseatSize = -1;
      return;
      bool1 = false;
      break;
    }
  }
  
  private final boolean applyOperations()
  {
    if (!this.mUpdateOperations.isEmpty()) {
      this.mContext.getContentResolver().applyBatch("com.google.android.launcher.settings", this.mUpdateOperations);
    }
    if (!this.mEntryToRemove.isEmpty())
    {
      str = String.valueOf(TextUtils.join(", ", this.mEntryToRemove));
      if (str.length() == 0) {
        break label117;
      }
    }
    label117:
    for (String str = "Removing items: ".concat(str);; str = new String("Removing items: "))
    {
      Log.d("GridSizeMigrationTask", str);
      this.mContext.getContentResolver().delete(LauncherSettings.Favorites.CONTENT_URI, Utilities.createDbSelectionQuery("_id", this.mEntryToRemove), null);
      if ((this.mUpdateOperations.isEmpty()) && (this.mEntryToRemove.isEmpty())) {
        break;
      }
      return true;
    }
    return false;
  }
  
  static ArrayList<GridSizeMigrationTask.DbEntry> deepCopy(ArrayList<GridSizeMigrationTask.DbEntry> paramArrayList)
  {
    ArrayList localArrayList = new ArrayList(paramArrayList.size());
    paramArrayList = (ArrayList)paramArrayList;
    int j = paramArrayList.size();
    int i = 0;
    while (i < j)
    {
      Object localObject = paramArrayList.get(i);
      i += 1;
      localObject = (GridSizeMigrationTask.DbEntry)localObject;
      GridSizeMigrationTask.DbEntry localDbEntry = new GridSizeMigrationTask.DbEntry();
      localDbEntry.copyFrom((ItemInfo)localObject);
      localDbEntry.weight = ((GridSizeMigrationTask.DbEntry)localObject).weight;
      localDbEntry.minSpanX = ((GridSizeMigrationTask.DbEntry)localObject).minSpanX;
      localDbEntry.minSpanY = ((GridSizeMigrationTask.DbEntry)localObject).minSpanY;
      localArrayList.add(localDbEntry);
    }
    return localArrayList;
  }
  
  private final int getFolderItemsCount(long paramLong)
  {
    Object localObject = this.mContext.getContentResolver();
    Uri localUri = LauncherSettings.Favorites.CONTENT_URI;
    String str = String.valueOf("container = ");
    str = String.valueOf(str).length() + 20 + str + paramLong;
    localObject = ((ContentResolver)localObject).query(localUri, new String[] { "_id", "intent" }, str, null, null, null);
    int i = 0;
    while (((Cursor)localObject).moveToNext()) {
      try
      {
        verifyIntent(((Cursor)localObject).getString(1));
        i += 1;
      }
      catch (Exception localException)
      {
        this.mEntryToRemove.add(Long.valueOf(((Cursor)localObject).getLong(0)));
      }
    }
    ((Cursor)localObject).close();
    return i;
  }
  
  private static String getPointString(int paramInt1, int paramInt2)
  {
    return String.format(Locale.ENGLISH, "%d,%d", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
  }
  
  private final boolean isVacant(boolean[][] paramArrayOfBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt1 + paramInt3 > this.mTrgX) {}
    while (paramInt2 + paramInt4 > this.mTrgY) {
      return false;
    }
    int i = 0;
    for (;;)
    {
      if (i >= paramInt3) {
        break label76;
      }
      int j = 0;
      for (;;)
      {
        if (j >= paramInt4) {
          break label67;
        }
        if (paramArrayOfBoolean[(i + paramInt1)][(j + paramInt2)] != 0) {
          break;
        }
        j += 1;
      }
      label67:
      i += 1;
    }
    label76:
    return true;
  }
  
  private final ArrayList<GridSizeMigrationTask.DbEntry> loadHotseatEntries()
  {
    Cursor localCursor = this.mContext.getContentResolver().query(LauncherSettings.Favorites.CONTENT_URI, new String[] { "_id", "itemType", "intent", "screen" }, "container = -101", null, null, null);
    int i = localCursor.getColumnIndexOrThrow("_id");
    int j = localCursor.getColumnIndexOrThrow("itemType");
    int k = localCursor.getColumnIndexOrThrow("intent");
    int m = localCursor.getColumnIndexOrThrow("screen");
    ArrayList localArrayList = new ArrayList();
    GridSizeMigrationTask.DbEntry localDbEntry;
    for (;;)
    {
      if (!localCursor.moveToNext()) {
        break label399;
      }
      localDbEntry = new GridSizeMigrationTask.DbEntry();
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
        case 3: 
        case 4: 
        case 5: 
          throw new Exception("Invalid item type");
        }
      }
      catch (Exception localException)
      {
        long l = localDbEntry.id;
        Log.d("GridSizeMigrationTask", 34 + "Removing item " + l, localException);
        this.mEntryToRemove.add(Long.valueOf(localDbEntry.id));
      }
      break;
      verifyIntent(localCursor.getString(k));
      float f;
      if (localDbEntry.itemType == 0) {
        f = 0.8F;
      }
      label335:
      int n;
      for (localDbEntry.weight = f;; localDbEntry.weight = (n * 0.5F))
      {
        localArrayList.add(localDbEntry);
        break;
        f = 1.0F;
        break label335;
        n = getFolderItemsCount(localDbEntry.id);
        if (n == 0) {
          throw new Exception("Folder is empty");
        }
      }
      label399:
      localCursor.close();
      return localArrayList;
    }
  }
  
  private final ArrayList<GridSizeMigrationTask.DbEntry> loadWorkspaceEntries(long paramLong)
  {
    ContentResolver localContentResolver = this.mContext.getContentResolver();
    Object localObject2 = LauncherSettings.Favorites.CONTENT_URI;
    Object localObject3 = String.valueOf("container = -100 AND screen = ");
    localObject3 = String.valueOf(localObject3).length() + 20 + (String)localObject3 + paramLong;
    localObject2 = localContentResolver.query((Uri)localObject2, new String[] { "_id", "itemType", "cellX", "cellY", "spanX", "spanY", "intent", "appWidgetProvider", "appWidgetId" }, (String)localObject3, null, null, null);
    int j = ((Cursor)localObject2).getColumnIndexOrThrow("_id");
    int k = ((Cursor)localObject2).getColumnIndexOrThrow("itemType");
    int m = ((Cursor)localObject2).getColumnIndexOrThrow("cellX");
    int n = ((Cursor)localObject2).getColumnIndexOrThrow("cellY");
    int i1 = ((Cursor)localObject2).getColumnIndexOrThrow("spanX");
    int i2 = ((Cursor)localObject2).getColumnIndexOrThrow("spanY");
    int i3 = ((Cursor)localObject2).getColumnIndexOrThrow("intent");
    int i4 = ((Cursor)localObject2).getColumnIndexOrThrow("appWidgetProvider");
    int i5 = ((Cursor)localObject2).getColumnIndexOrThrow("appWidgetId");
    localObject3 = new ArrayList();
    GridSizeMigrationTask.DbEntry localDbEntry;
    if (((Cursor)localObject2).moveToNext())
    {
      localDbEntry = new GridSizeMigrationTask.DbEntry();
      localDbEntry.id = ((Cursor)localObject2).getLong(j);
      localDbEntry.itemType = ((Cursor)localObject2).getInt(k);
      localDbEntry.cellX = ((Cursor)localObject2).getInt(m);
      localDbEntry.cellY = ((Cursor)localObject2).getInt(n);
      localDbEntry.spanX = ((Cursor)localObject2).getInt(i1);
      localDbEntry.spanY = ((Cursor)localObject2).getInt(i2);
      localDbEntry.screenId = paramLong;
    }
    for (;;)
    {
      try
      {
        switch (localDbEntry.itemType)
        {
        case 3: 
        case 5: 
          throw new Exception("Invalid item type");
        }
      }
      catch (Exception localException)
      {
        long l = localDbEntry.id;
        Log.d("GridSizeMigrationTask", 34 + "Removing item " + l, localException);
        this.mEntryToRemove.add(Long.valueOf(localDbEntry.id));
      }
      break;
      verifyIntent(((Cursor)localObject2).getString(i3));
      float f;
      if (localDbEntry.itemType == 0)
      {
        f = 0.8F;
        label495:
        localDbEntry.weight = f;
      }
      for (;;)
      {
        ((ArrayList)localObject3).add(localDbEntry);
        break;
        f = 1.0F;
        break label495;
        Object localObject1 = ((Cursor)localObject2).getString(i4);
        verifyPackage(ComponentName.unflattenFromString((String)localObject1).getPackageName());
        localDbEntry.weight = Math.max(2.0F, 0.6F * localDbEntry.spanX * localDbEntry.spanY);
        int i = ((Cursor)localObject2).getInt(i5);
        LauncherAppWidgetProviderInfo localLauncherAppWidgetProviderInfo = AppWidgetManagerCompat.getInstance(this.mContext).getLauncherAppWidgetInfo(i);
        if (localLauncherAppWidgetProviderInfo == null)
        {
          localObject1 = (Point)this.mWidgetMinSize.get(localObject1);
          if (localObject1 == null) {
            break label724;
          }
          if (((Point)localObject1).x <= 0) {
            break label704;
          }
          i = ((Point)localObject1).x;
          label630:
          localDbEntry.minSpanX = i;
          if (((Point)localObject1).y <= 0) {
            break label714;
          }
          i = ((Point)localObject1).y;
          label652:
          localDbEntry.minSpanY = i;
        }
        for (;;)
        {
          if ((localDbEntry.minSpanX <= this.mTrgX) && (localDbEntry.minSpanY <= this.mTrgY)) {
            break label737;
          }
          throw new Exception("Widget can't be resized down to fit the grid");
          localObject1 = localLauncherAppWidgetProviderInfo.getMinSpans$51666RRD5TGMSP3IDTKM8BRCC5QMSOR8CLP36BQ9DPR62SJ9C5N78H35EPKM6PAGE9NMCQBCCKTKOOBECHP6UQB45THMURJKCLN78BQ3DTN78PBOEGTIIJ31DPI74RR9CGNMESJ1E1K6IORJ5T86UQBEEGTG____0();
          break;
          label704:
          i = localDbEntry.spanX;
          break label630;
          label714:
          i = localDbEntry.spanY;
          break label652;
          label724:
          localDbEntry.minSpanY = 2;
          localDbEntry.minSpanX = 2;
        }
        label737:
        continue;
        i = getFolderItemsCount(localDbEntry.id);
        if (i == 0) {
          throw new Exception("Folder is empty");
        }
        localDbEntry.weight = (i * 0.5F);
      }
      ((Cursor)localObject2).close();
      return localObject3;
    }
  }
  
  public static void markForMigration(Context paramContext, HashSet<String> paramHashSet, BackupProtos.DeviceProfieData paramDeviceProfieData)
  {
    Utilities.getPrefs(paramContext).edit().putString("migration_src_workspace_size", getPointString((int)paramDeviceProfieData.desktopCols, (int)paramDeviceProfieData.desktopRows)).putString("migration_src_hotseat_size", getPointString((int)paramDeviceProfieData.hotseatCount, paramDeviceProfieData.allappsRank)).putStringSet("migration_widget_min_size", paramHashSet).apply();
  }
  
  public static boolean migrateGridIfNeeded(Context paramContext)
  {
    SharedPreferences localSharedPreferences = Utilities.getPrefs(paramContext);
    Object localObject2 = LauncherAppState.getInstance().mInvariantDeviceProfile;
    String str1 = getPointString(((InvariantDeviceProfile)localObject2).numColumns, ((InvariantDeviceProfile)localObject2).numRows);
    String str2 = getPointString(((InvariantDeviceProfile)localObject2).numHotseatIcons, ((InvariantDeviceProfile)localObject2).hotseatAllAppsRank);
    if ((str1.equals(localSharedPreferences.getString("migration_src_workspace_size", ""))) && (str2.equals(localSharedPreferences.getString("migration_src_hotseat_size", "")))) {
      return true;
    }
    long l1 = System.currentTimeMillis();
    boolean bool1 = false;
    Object localObject1;
    Object localObject3;
    long l2;
    boolean bool2;
    int i;
    int j;
    try
    {
      localObject1 = new HashSet();
      localObject3 = paramContext.getPackageManager().getInstalledPackages(0).iterator();
      while (((Iterator)localObject3).hasNext()) {
        ((HashSet)localObject1).add(((PackageInfo)((Iterator)localObject3).next()).packageName);
      }
      localObject3 = new HashMap();
    }
    catch (Exception paramContext)
    {
      Log.e("GridSizeMigrationTask", "Error during grid migration", paramContext);
      return false;
      ((HashSet)localObject1).addAll(PackageInstallerCompat.getInstance(paramContext).updateAndGetActiveSessionCache().keySet());
      localObject3 = parsePoint(localSharedPreferences.getString("migration_src_hotseat_size", str2));
      if ((((Point)localObject3).x != ((InvariantDeviceProfile)localObject2).numHotseatIcons) || (((Point)localObject3).y != ((InvariantDeviceProfile)localObject2).hotseatAllAppsRank)) {
        bool1 = new GridSizeMigrationTask(paramContext, LauncherAppState.getInstance().mInvariantDeviceProfile, (HashSet)localObject1, ((Point)localObject3).x, ((Point)localObject3).y, ((InvariantDeviceProfile)localObject2).numHotseatIcons, ((InvariantDeviceProfile)localObject2).hotseatAllAppsRank).migrateHotseat();
      }
      localObject3 = new Point(((InvariantDeviceProfile)localObject2).numColumns, ((InvariantDeviceProfile)localObject2).numRows);
      localObject4 = parsePoint(localSharedPreferences.getString("migration_src_workspace_size", str1));
      bool2 = bool1;
      if (((Point)localObject3).equals(localObject4)) {
        break label844;
      }
      localObject2 = new ArrayList();
      ((ArrayList)localObject2).add(new Point(3, 2));
      ((ArrayList)localObject2).add(new Point(3, 3));
      ((ArrayList)localObject2).add(new Point(4, 3));
      ((ArrayList)localObject2).add(new Point(4, 4));
      ((ArrayList)localObject2).add(new Point(5, 5));
      ((ArrayList)localObject2).add(new Point(6, 5));
      ((ArrayList)localObject2).add(new Point(6, 6));
      ((ArrayList)localObject2).add(new Point(7, 7));
      i = ((ArrayList)localObject2).indexOf(localObject4);
      j = ((ArrayList)localObject2).indexOf(localObject3);
      if ((i < 0) || (j < 0))
      {
        paramContext = String.valueOf(localObject4);
        localObject1 = String.valueOf(localObject3);
        throw new Exception(String.valueOf(paramContext).length() + 37 + String.valueOf(localObject1).length() + "Unable to migrate grid size from " + paramContext + " to " + (String)localObject1);
      }
    }
    finally
    {
      l2 = System.currentTimeMillis();
      Log.v("GridSizeMigrationTask", 53 + "Workspace migration completed in " + (l2 - l1));
      localSharedPreferences.edit().putString("migration_src_workspace_size", str1).putString("migration_src_hotseat_size", str2).remove("migration_widget_min_size").apply();
    }
    Object localObject4 = Utilities.getPrefs(paramContext).getStringSet("migration_widget_min_size", Collections.emptySet()).iterator();
    Object localObject5;
    while (((Iterator)localObject4).hasNext())
    {
      localObject5 = ((String)((Iterator)localObject4).next()).split("#");
      ((HashMap)localObject3).put(localObject5[0], parsePoint(localObject5[1]));
    }
    for (;;)
    {
      bool2 = bool1;
      if (j < i)
      {
        localObject4 = (Point)((ArrayList)localObject2).get(i - 1);
        localObject5 = (Point)((ArrayList)localObject2).get(i);
        if (new GridSizeMigrationTask(paramContext, LauncherAppState.getInstance().mInvariantDeviceProfile, (HashSet)localObject1, (HashMap)localObject3, (Point)localObject5, (Point)localObject4).migrateWorkspace())
        {
          bool1 = true;
          break label978;
        }
      }
      else
      {
        label844:
        if (bool2)
        {
          paramContext = paramContext.getContentResolver().query(LauncherSettings.Favorites.CONTENT_URI, null, null, null, null);
          bool1 = paramContext.moveToNext();
          paramContext.close();
          if (!bool1) {
            throw new Exception("Removed every thing during grid resize");
          }
        }
        l2 = System.currentTimeMillis();
        Log.v("GridSizeMigrationTask", 53 + "Workspace migration completed in " + (l2 - l1));
        localSharedPreferences.edit().putString("migration_src_workspace_size", str1).putString("migration_src_hotseat_size", str2).remove("migration_widget_min_size").apply();
        return true;
      }
      break label978;
      continue;
      label978:
      i -= 1;
    }
  }
  
  private final void migrateScreen(long paramLong)
  {
    Object localObject3 = loadWorkspaceEntries(paramLong);
    int j = Integer.MAX_VALUE;
    int i = Integer.MAX_VALUE;
    float f1 = Float.MAX_VALUE;
    float f2 = Float.MAX_VALUE;
    Object localObject4 = new float[2];
    Object localObject1 = null;
    int k = 0;
    int m;
    label49:
    int i1;
    Object localObject5;
    label123:
    Object localObject2;
    label310:
    label386:
    float f3;
    if (k < this.mSrcX)
    {
      m = 0;
      if (m < this.mSrcY)
      {
        ArrayList localArrayList2 = deepCopy((ArrayList)localObject3);
        n = this.mTrgX;
        i1 = this.mTrgY;
        localObject5 = (boolean[][])Array.newInstance(Boolean.TYPE, new int[] { n, i1 });
        ArrayList localArrayList1;
        int i4;
        int i2;
        if (this.mShouldRemoveX)
        {
          n = k;
          if (!this.mShouldRemoveY) {
            break label310;
          }
          i1 = m;
          localObject2 = new ArrayList();
          localArrayList1 = new ArrayList();
          localArrayList2 = (ArrayList)localArrayList2;
          i4 = localArrayList2.size();
          i2 = 0;
        }
        for (;;)
        {
          if (i2 >= i4) {
            break label386;
          }
          Object localObject6 = localArrayList2.get(i2);
          int i3 = i2 + 1;
          localObject6 = (GridSizeMigrationTask.DbEntry)localObject6;
          if (((((GridSizeMigrationTask.DbEntry)localObject6).cellX <= n) && (((GridSizeMigrationTask.DbEntry)localObject6).spanX + ((GridSizeMigrationTask.DbEntry)localObject6).cellX > n)) || ((((GridSizeMigrationTask.DbEntry)localObject6).cellY <= i1) && (((GridSizeMigrationTask.DbEntry)localObject6).spanY + ((GridSizeMigrationTask.DbEntry)localObject6).cellY > i1)))
          {
            localArrayList1.add(localObject6);
            if (((GridSizeMigrationTask.DbEntry)localObject6).cellX >= n) {
              ((GridSizeMigrationTask.DbEntry)localObject6).cellX -= 1;
            }
            i2 = i3;
            if (((GridSizeMigrationTask.DbEntry)localObject6).cellY < i1) {
              continue;
            }
            ((GridSizeMigrationTask.DbEntry)localObject6).cellY -= 1;
            i2 = i3;
            continue;
            n = Integer.MAX_VALUE;
            break;
            i1 = Integer.MAX_VALUE;
            break label123;
          }
          if (((GridSizeMigrationTask.DbEntry)localObject6).cellX > n) {
            ((GridSizeMigrationTask.DbEntry)localObject6).cellX -= 1;
          }
          if (((GridSizeMigrationTask.DbEntry)localObject6).cellY > i1) {
            ((GridSizeMigrationTask.DbEntry)localObject6).cellY -= 1;
          }
          ((ArrayList)localObject2).add(localObject6);
          markCells((boolean[][])localObject5, (GridSizeMigrationTask.DbEntry)localObject6, true);
          i2 = i3;
        }
        localObject5 = new GridSizeMigrationTask.OptimalPlacementSolution(this, (boolean[][])localObject5, localArrayList1);
        ((GridSizeMigrationTask.OptimalPlacementSolution)localObject5).find();
        ((ArrayList)localObject2).addAll(((GridSizeMigrationTask.OptimalPlacementSolution)localObject5).finalPlacedItems);
        localObject4[0] = ((GridSizeMigrationTask.OptimalPlacementSolution)localObject5).lowestWeightLoss;
        localObject4[1] = ((GridSizeMigrationTask.OptimalPlacementSolution)localObject5).lowestMoveCost;
        if ((localObject4[0] >= f1) && ((localObject4[0] != f1) || (localObject4[1] >= f2))) {
          break label1149;
        }
        f3 = localObject4[0];
        f1 = localObject4[1];
        if (this.mShouldRemoveX)
        {
          j = k;
          label484:
          if (!this.mShouldRemoveY) {
            break label551;
          }
          i = m;
          label495:
          localObject1 = localObject2;
        }
      }
    }
    for (int n = i;; n = i)
    {
      localObject2 = localObject1;
      float f4 = f1;
      f2 = f3;
      i1 = n;
      i = j;
      if (this.mShouldRemoveY)
      {
        m += 1;
        i = n;
        f2 = f1;
        f1 = f3;
        break label49;
        break label484;
        label551:
        break label495;
        f4 = f2;
        i1 = i;
        i = j;
        f2 = f1;
        localObject2 = localObject1;
      }
      if (this.mShouldRemoveX)
      {
        k += 1;
        f1 = f2;
        j = i;
        localObject1 = localObject2;
        f2 = f4;
        i = i1;
        break;
        i1 = i;
      }
      for (;;)
      {
        Log.d("GridSizeMigrationTask", String.format("Removing row %d, column %d on screen %d", new Object[] { Integer.valueOf(i1), Integer.valueOf(j), Long.valueOf(paramLong) }));
        localObject2 = new LongArrayMap();
        localObject3 = (ArrayList)deepCopy((ArrayList)localObject3);
        j = ((ArrayList)localObject3).size();
        i = 0;
        while (i < j)
        {
          localObject4 = ((ArrayList)localObject3).get(i);
          i += 1;
          localObject4 = (GridSizeMigrationTask.DbEntry)localObject4;
          ((LongArrayMap)localObject2).put(((GridSizeMigrationTask.DbEntry)localObject4).id, localObject4);
        }
        localObject3 = (ArrayList)localObject1;
        k = ((ArrayList)localObject3).size();
        i = 0;
        if (i < k)
        {
          localObject4 = (GridSizeMigrationTask.DbEntry)((ArrayList)localObject3).get(i);
          localObject5 = (GridSizeMigrationTask.DbEntry)((LongArrayMap)localObject2).get(((GridSizeMigrationTask.DbEntry)localObject4).id);
          ((LongArrayMap)localObject2).remove(((GridSizeMigrationTask.DbEntry)localObject4).id);
          if ((((GridSizeMigrationTask.DbEntry)localObject5).cellX == ((GridSizeMigrationTask.DbEntry)localObject4).cellX) && (((GridSizeMigrationTask.DbEntry)localObject5).cellY == ((GridSizeMigrationTask.DbEntry)localObject4).cellY) && (((GridSizeMigrationTask.DbEntry)localObject5).spanX == ((GridSizeMigrationTask.DbEntry)localObject4).spanX) && (((GridSizeMigrationTask.DbEntry)localObject5).spanY == ((GridSizeMigrationTask.DbEntry)localObject4).spanY) && (((GridSizeMigrationTask.DbEntry)localObject5).screenId == ((GridSizeMigrationTask.DbEntry)localObject4).screenId)) {}
          for (j = 1;; j = 0)
          {
            if (j == 0) {
              update((GridSizeMigrationTask.DbEntry)localObject4);
            }
            i += 1;
            break;
          }
        }
        localObject2 = ((LongArrayMap)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (GridSizeMigrationTask.DbEntry)((Iterator)localObject2).next();
          this.mCarryOver.add(localObject3);
        }
        if ((!this.mCarryOver.isEmpty()) && (f1 == 0.0F))
        {
          i = this.mTrgX;
          j = this.mTrgY;
          localObject2 = (boolean[][])Array.newInstance(Boolean.TYPE, new int[] { i, j });
          localObject1 = (ArrayList)localObject1;
          j = ((ArrayList)localObject1).size();
          i = 0;
          while (i < j)
          {
            localObject3 = ((ArrayList)localObject1).get(i);
            i += 1;
            markCells((boolean[][])localObject2, (GridSizeMigrationTask.DbEntry)localObject3, true);
          }
          localObject1 = new GridSizeMigrationTask.OptimalPlacementSolution(this, (boolean[][])localObject2, deepCopy(this.mCarryOver), true);
          ((GridSizeMigrationTask.OptimalPlacementSolution)localObject1).find();
          if (((GridSizeMigrationTask.OptimalPlacementSolution)localObject1).lowestWeightLoss == 0.0F)
          {
            localObject1 = (ArrayList)((GridSizeMigrationTask.OptimalPlacementSolution)localObject1).finalPlacedItems;
            j = ((ArrayList)localObject1).size();
            i = 0;
            while (i < j)
            {
              localObject2 = ((ArrayList)localObject1).get(i);
              i += 1;
              localObject2 = (GridSizeMigrationTask.DbEntry)localObject2;
              ((GridSizeMigrationTask.DbEntry)localObject2).screenId = paramLong;
              update((GridSizeMigrationTask.DbEntry)localObject2);
            }
            this.mCarryOver.clear();
          }
        }
        return;
        f1 = f2;
        localObject1 = localObject2;
        j = i;
      }
      label1149:
      f3 = f1;
      f1 = f2;
    }
  }
  
  private static Point parsePoint(String paramString)
  {
    paramString = paramString.split(",");
    return new Point(Integer.parseInt(paramString[0]), Integer.parseInt(paramString[1]));
  }
  
  private final void update(GridSizeMigrationTask.DbEntry paramDbEntry)
  {
    this.mTempValues.clear();
    ContentValues localContentValues = this.mTempValues;
    localContentValues.put("screen", Long.valueOf(paramDbEntry.screenId));
    localContentValues.put("cellX", Integer.valueOf(paramDbEntry.cellX));
    localContentValues.put("cellY", Integer.valueOf(paramDbEntry.cellY));
    localContentValues.put("spanX", Integer.valueOf(paramDbEntry.spanX));
    localContentValues.put("spanY", Integer.valueOf(paramDbEntry.spanY));
    this.mUpdateOperations.add(ContentProviderOperation.newUpdate(LauncherSettings.Favorites.getContentUri(paramDbEntry.id)).withValues(this.mTempValues).build());
  }
  
  private final void verifyIntent(String paramString)
  {
    paramString = Intent.parseUri(paramString, 0);
    if (paramString.getComponent() != null) {
      verifyPackage(paramString.getComponent().getPackageName());
    }
    while (paramString.getPackage() == null) {
      return;
    }
    verifyPackage(paramString.getPackage());
  }
  
  private final void verifyPackage(String paramString)
  {
    if (!this.mValidPackages.contains(paramString)) {
      throw new Exception("Package not available");
    }
  }
  
  final void markCells(boolean[][] paramArrayOfBoolean, GridSizeMigrationTask.DbEntry paramDbEntry, boolean paramBoolean)
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
  
  protected final boolean migrateHotseat()
  {
    ArrayList localArrayList1 = loadHotseatEntries();
    int j = this.mDestHotseatSize;
    Object localObject1;
    int k;
    int i;
    label54:
    Object localObject2;
    if (localArrayList1.size() > j - 1)
    {
      localObject1 = (GridSizeMigrationTask.DbEntry)localArrayList1.get(localArrayList1.size() / 2);
      ArrayList localArrayList2 = (ArrayList)localArrayList1;
      k = localArrayList2.size();
      i = 0;
      if (i < k)
      {
        localObject2 = localArrayList2.get(i);
        i += 1;
        localObject2 = (GridSizeMigrationTask.DbEntry)localObject2;
        if (((GridSizeMigrationTask.DbEntry)localObject2).weight >= ((GridSizeMigrationTask.DbEntry)localObject1).weight) {
          break label231;
        }
        localObject1 = localObject2;
      }
    }
    label231:
    for (;;)
    {
      break label54;
      this.mEntryToRemove.add(Long.valueOf(((GridSizeMigrationTask.DbEntry)localObject1).id));
      localArrayList1.remove(localObject1);
      break;
      localObject1 = (ArrayList)localArrayList1;
      int m = ((ArrayList)localObject1).size();
      j = 0;
      i = 0;
      while (j < m)
      {
        localObject2 = ((ArrayList)localObject1).get(j);
        j += 1;
        localObject2 = (GridSizeMigrationTask.DbEntry)localObject2;
        if (((GridSizeMigrationTask.DbEntry)localObject2).screenId != i)
        {
          ((GridSizeMigrationTask.DbEntry)localObject2).screenId = i;
          ((GridSizeMigrationTask.DbEntry)localObject2).cellX = i;
          ((GridSizeMigrationTask.DbEntry)localObject2).cellY = 0;
          update((GridSizeMigrationTask.DbEntry)localObject2);
        }
        k = i + 1;
        i = k;
        if (k == this.mDestAllAppsRank) {
          i = k + 1;
        }
      }
      return applyOperations();
    }
  }
  
  protected final boolean migrateWorkspace()
  {
    ArrayList localArrayList = LauncherModel.loadWorkspaceScreensDb(this.mContext);
    if (localArrayList.isEmpty()) {
      throw new Exception("Unable to get workspace screens");
    }
    Object localObject1 = (ArrayList)localArrayList;
    int j = ((ArrayList)localObject1).size();
    int i = 0;
    Object localObject2;
    long l;
    while (i < j)
    {
      localObject2 = ((ArrayList)localObject1).get(i);
      i += 1;
      l = ((Long)localObject2).longValue();
      Log.d("GridSizeMigrationTask", 30 + "Migrating " + l);
      migrateScreen(l);
    }
    if (!this.mCarryOver.isEmpty())
    {
      localObject1 = new LongArrayMap();
      localObject2 = (ArrayList)this.mCarryOver;
      j = ((ArrayList)localObject2).size();
      i = 0;
      Object localObject3;
      while (i < j)
      {
        localObject3 = ((ArrayList)localObject2).get(i);
        i += 1;
        localObject3 = (GridSizeMigrationTask.DbEntry)localObject3;
        ((LongArrayMap)localObject1).put(((GridSizeMigrationTask.DbEntry)localObject3).id, localObject3);
      }
      do
      {
        i = this.mTrgX;
        j = this.mTrgY;
        localObject2 = new GridSizeMigrationTask.OptimalPlacementSolution(this, (boolean[][])Array.newInstance(Boolean.TYPE, new int[] { i, j }), deepCopy(this.mCarryOver), true);
        ((GridSizeMigrationTask.OptimalPlacementSolution)localObject2).find();
        if (((GridSizeMigrationTask.OptimalPlacementSolution)localObject2).finalPlacedItems.size() > 0)
        {
          l = LauncherAppState.getLauncherProvider().mOpenHelper.generateNewScreenId();
          localArrayList.add(Long.valueOf(l));
          localObject2 = (ArrayList)((GridSizeMigrationTask.OptimalPlacementSolution)localObject2).finalPlacedItems;
          j = ((ArrayList)localObject2).size();
          i = 0;
          while (i < j)
          {
            localObject3 = ((ArrayList)localObject2).get(i);
            i += 1;
            localObject3 = (GridSizeMigrationTask.DbEntry)localObject3;
            if (!this.mCarryOver.remove(((LongArrayMap)localObject1).get(((GridSizeMigrationTask.DbEntry)localObject3).id))) {
              throw new Exception("Unable to find matching items");
            }
            ((GridSizeMigrationTask.DbEntry)localObject3).screenId = l;
            update((GridSizeMigrationTask.DbEntry)localObject3);
          }
        }
        throw new Exception("None of the items can be placed on an empty screen");
      } while (!this.mCarryOver.isEmpty());
      localObject1 = LauncherSettings.WorkspaceScreens.CONTENT_URI;
      this.mUpdateOperations.add(ContentProviderOperation.newDelete((Uri)localObject1).build());
      j = localArrayList.size();
      i = 0;
      while (i < j)
      {
        localObject2 = new ContentValues();
        ((ContentValues)localObject2).put("_id", Long.valueOf(((Long)localArrayList.get(i)).longValue()));
        ((ContentValues)localObject2).put("screenRank", Integer.valueOf(i));
        this.mUpdateOperations.add(ContentProviderOperation.newInsert((Uri)localObject1).withValues((ContentValues)localObject2).build());
        i += 1;
      }
    }
    return applyOperations();
  }
}
