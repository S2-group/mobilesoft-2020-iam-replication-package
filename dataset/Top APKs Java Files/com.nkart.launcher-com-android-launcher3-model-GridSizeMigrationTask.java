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
import com.android.launcher3.LauncherAppWidgetProviderInfo;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.LauncherProvider;
import com.android.launcher3.LauncherSettings.Favorites;
import com.android.launcher3.LauncherSettings.Settings;
import com.android.launcher3.LauncherSettings.WorkspaceScreens;
import com.android.launcher3.Utilities;
import com.android.launcher3.compat.AppWidgetManagerCompat;
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
    if (this.mTrgX < this.mSrcX)
    {
      bool1 = true;
      this.mShouldRemoveX = bool1;
      if (this.mTrgY >= this.mSrcY) {
        break label160;
      }
    }
    label160:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      this.mShouldRemoveY = bool1;
      this.mDestHotseatSize = -1;
      this.mSrcHotseatSize = -1;
      return;
      bool1 = false;
      break;
    }
  }
  
  private boolean applyOperations()
  {
    if (!this.mUpdateOperations.isEmpty()) {
      this.mContext.getContentResolver().applyBatch(LauncherProvider.AUTHORITY, this.mUpdateOperations);
    }
    if (!this.mEntryToRemove.isEmpty())
    {
      Log.d("GridSizeMigrationTask", "Removing items: " + TextUtils.join(", ", this.mEntryToRemove));
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
    Object localObject = "container = " + paramLong;
    localObject = queryWorkspace(new String[] { "_id", "intent" }, (String)localObject);
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
        break label390;
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
        case 3: 
        case 4: 
        case 5: 
          throw new Exception("Invalid item type");
        }
      }
      catch (Exception localException)
      {
        Log.d("GridSizeMigrationTask", "Removing item " + localDbEntry.id, localException);
        this.mEntryToRemove.add(Long.valueOf(localDbEntry.id));
      }
      break;
      verifyIntent(localCursor.getString(k));
      float f;
      if (localDbEntry.itemType == 0) {
        f = 0.8F;
      }
      label326:
      int n;
      for (localDbEntry.weight = f;; localDbEntry.weight = (n * 0.5F))
      {
        localArrayList.add(localDbEntry);
        break;
        f = 1.0F;
        break label326;
        n = getFolderItemsCount(localDbEntry.id);
        if (n == 0) {
          throw new Exception("Folder is empty");
        }
      }
      label390:
      localCursor.close();
      return localArrayList;
    }
  }
  
  public static void markForMigration(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    Utilities.getPrefs(paramContext).edit().putString("migration_src_workspace_size", getPointString(paramInt1, paramInt2)).putInt("migration_src_hotseat_count", paramInt3).apply();
  }
  
  public static boolean migrateGridIfNeeded(Context paramContext)
  {
    SharedPreferences localSharedPreferences = Utilities.getPrefs(paramContext);
    InvariantDeviceProfile localInvariantDeviceProfile = LauncherAppState.getInstance(paramContext).mInvariantDeviceProfile;
    String str = getPointString(localInvariantDeviceProfile.numColumns, localInvariantDeviceProfile.numRows);
    if ((str.equals(localSharedPreferences.getString("migration_src_workspace_size", ""))) && (localInvariantDeviceProfile.numHotseatIcons == localSharedPreferences.getInt("migration_src_hotseat_count", localInvariantDeviceProfile.numHotseatIcons))) {
      return true;
    }
    long l = System.currentTimeMillis();
    boolean bool = false;
    for (;;)
    {
      int i;
      try
      {
        HashSet localHashSet = getValidPackages(paramContext);
        i = localSharedPreferences.getInt("migration_src_hotseat_count", localInvariantDeviceProfile.numHotseatIcons);
        GridSizeMigrationTask localGridSizeMigrationTask;
        ArrayList localArrayList;
        Object localObject1;
        Object localObject2;
        if (i != localInvariantDeviceProfile.numHotseatIcons)
        {
          localGridSizeMigrationTask = new GridSizeMigrationTask(paramContext, LauncherAppState.getInstance(paramContext).mInvariantDeviceProfile, localHashSet, i, localInvariantDeviceProfile.numHotseatIcons);
          localArrayList = localGridSizeMigrationTask.loadHotseatEntries();
          i = localGridSizeMigrationTask.mDestHotseatSize;
          if (localArrayList.size() > i)
          {
            localObject1 = (DbEntry)localArrayList.get(localArrayList.size() / 2);
            Iterator localIterator = localArrayList.iterator();
            if (localIterator.hasNext())
            {
              localObject2 = (DbEntry)localIterator.next();
              if (((DbEntry)localObject2).weight >= ((DbEntry)localObject1).weight) {
                break label673;
              }
              localObject1 = localObject2;
              break label673;
            }
            localGridSizeMigrationTask.mEntryToRemove.add(Long.valueOf(((DbEntry)localObject1).id));
            localArrayList.remove(localObject1);
            continue;
          }
        }
        Log.v("GridSizeMigrationTask", "Workspace migration completed in " + (System.currentTimeMillis() - l));
      }
      catch (Exception paramContext)
      {
        Log.e("GridSizeMigrationTask", "Error during grid migration", paramContext);
        return false;
        localObject1 = localArrayList.iterator();
        i = 0;
        if (((Iterator)localObject1).hasNext())
        {
          localObject2 = (DbEntry)((Iterator)localObject1).next();
          if (((DbEntry)localObject2).screenId == i) {
            break label676;
          }
          ((DbEntry)localObject2).screenId = i;
          ((DbEntry)localObject2).cellX = i;
          ((DbEntry)localObject2).cellY = 0;
          localGridSizeMigrationTask.update((DbEntry)localObject2);
          break label676;
        }
        bool = localGridSizeMigrationTask.applyOperations();
        localObject1 = new Point(localInvariantDeviceProfile.numColumns, localInvariantDeviceProfile.numRows);
        localObject2 = localSharedPreferences.getString("migration_src_workspace_size", str).split(",");
        localObject2 = new Point(Integer.parseInt(localObject2[0]), Integer.parseInt(localObject2[1]));
        if (new MultiStepMigrationTask(localHashSet, paramContext).migrate((Point)localObject2, (Point)localObject1)) {
          bool = true;
        }
        if (bool)
        {
          paramContext = paramContext.getContentResolver().query(LauncherSettings.Favorites.CONTENT_URI, null, null, null, null);
          bool = paramContext.moveToNext();
          paramContext.close();
          if (!bool) {
            throw new Exception("Removed every thing during grid resize");
          }
        }
      }
      finally
      {
        Log.v("GridSizeMigrationTask", "Workspace migration completed in " + (System.currentTimeMillis() - l));
        localSharedPreferences.edit().putString("migration_src_workspace_size", str).putInt("migration_src_hotseat_count", localInvariantDeviceProfile.numHotseatIcons).apply();
      }
      localSharedPreferences.edit().putString("migration_src_workspace_size", str).putInt("migration_src_hotseat_count", localInvariantDeviceProfile.numHotseatIcons).apply();
      return true;
      label673:
      continue;
      label676:
      i += 1;
    }
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
    if (paramString.getComponent() != null) {
      verifyPackage(paramString.getComponent().getPackageName());
    }
    while (paramString.getPackage() == null) {
      return;
    }
    verifyPackage(paramString.getPackage());
  }
  
  private void verifyPackage(String paramString)
  {
    if (!this.mValidPackages.contains(paramString)) {
      throw new Exception("Package not available");
    }
  }
  
  public ArrayList<DbEntry> loadWorkspaceEntries(long paramLong)
  {
    String str = "container = -100 AND screen = " + paramLong;
    Cursor localCursor = queryWorkspace(new String[] { "_id", "itemType", "cellX", "cellY", "spanX", "spanY", "intent", "appWidgetProvider", "appWidgetId" }, str);
    int k = localCursor.getColumnIndexOrThrow("_id");
    int m = localCursor.getColumnIndexOrThrow("itemType");
    int n = localCursor.getColumnIndexOrThrow("cellX");
    int i1 = localCursor.getColumnIndexOrThrow("cellY");
    int i2 = localCursor.getColumnIndexOrThrow("spanX");
    int i3 = localCursor.getColumnIndexOrThrow("spanY");
    int i4 = localCursor.getColumnIndexOrThrow("intent");
    int i5 = localCursor.getColumnIndexOrThrow("appWidgetProvider");
    int i6 = localCursor.getColumnIndexOrThrow("appWidgetId");
    ArrayList localArrayList = new ArrayList();
    DbEntry localDbEntry;
    int i;
    Object localObject;
    while (localCursor.moveToNext())
    {
      localDbEntry = new DbEntry();
      localDbEntry.id = localCursor.getLong(k);
      localDbEntry.itemType = localCursor.getInt(m);
      localDbEntry.cellX = localCursor.getInt(n);
      localDbEntry.cellY = localCursor.getInt(i1);
      localDbEntry.spanX = localCursor.getInt(i2);
      localDbEntry.spanY = localCursor.getInt(i3);
      localDbEntry.screenId = paramLong;
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
        Log.d("GridSizeMigrationTask", "Removing item " + localDbEntry.id, localException);
        this.mEntryToRemove.add(Long.valueOf(localDbEntry.id));
      }
      continue;
      verifyIntent(localCursor.getString(i4));
      if (localDbEntry.itemType == 0) {}
      for (float f = 0.8F;; f = 1.0F)
      {
        localDbEntry.weight = f;
        localArrayList.add(localDbEntry);
        break;
      }
      verifyPackage(ComponentName.unflattenFromString(localCursor.getString(i5)).getPackageName());
      localDbEntry.weight = Math.max(2.0F, 0.6F * localDbEntry.spanX * localDbEntry.spanY);
      i = localCursor.getInt(i6);
      localObject = AppWidgetManagerCompat.getInstance(this.mContext).getLauncherAppWidgetInfo(i);
      if (localObject == null) {
        break label761;
      }
      if ((((LauncherAppWidgetProviderInfo)localObject).resizeMode & 0x1) == 0) {
        break label770;
      }
      i = ((LauncherAppWidgetProviderInfo)localObject).minSpanX;
      label560:
      if ((((LauncherAppWidgetProviderInfo)localObject).resizeMode & 0x2) == 0) {
        break label776;
      }
    }
    label632:
    label684:
    label707:
    label761:
    label770:
    label776:
    for (int j = ((LauncherAppWidgetProviderInfo)localObject).minSpanY;; j = -1)
    {
      for (localObject = new Point(i, j);; localObject = null)
      {
        if (localObject != null) {
          if (((Point)localObject).x > 0)
          {
            i = ((Point)localObject).x;
            localDbEntry.minSpanX = i;
            if (((Point)localObject).y <= 0) {
              break label684;
            }
            i = ((Point)localObject).y;
            localDbEntry.minSpanY = i;
          }
        }
        for (;;)
        {
          if ((localDbEntry.minSpanX <= this.mTrgX) && (localDbEntry.minSpanY <= this.mTrgY)) {
            break label707;
          }
          throw new Exception("Widget can't be resized down to fit the grid");
          i = localDbEntry.spanX;
          break;
          i = localDbEntry.spanY;
          break label632;
          localDbEntry.minSpanY = 2;
          localDbEntry.minSpanX = 2;
        }
        break;
        i = getFolderItemsCount(localDbEntry.id);
        if (i == 0) {
          throw new Exception("Folder is empty");
        }
        localDbEntry.weight = (i * 0.5F);
        break;
        localCursor.close();
        return localArrayList;
      }
      break;
      i = -1;
      break label560;
    }
  }
  
  protected final void migrateScreen(long paramLong)
  {
    int i2;
    Object localObject3;
    int i;
    int n;
    float f1;
    float f4;
    Object localObject4;
    Object localObject1;
    int k;
    Object localObject2;
    float f2;
    int j;
    int m;
    label96:
    Object localObject6;
    label152:
    int i1;
    label163:
    ArrayList localArrayList;
    if ((FeatureFlags.QSB_ON_FIRST_SCREEN) && (paramLong == 0L))
    {
      i2 = 1;
      localObject3 = loadWorkspaceEntries(paramLong);
      i = Integer.MAX_VALUE;
      n = Integer.MAX_VALUE;
      f1 = Float.MAX_VALUE;
      f4 = Float.MAX_VALUE;
      localObject4 = new float[2];
      localObject1 = null;
      k = 0;
      localObject2 = localObject1;
      f2 = f1;
      j = n;
      m = i;
      if (k >= this.mSrcX) {
        break label617;
      }
      m = this.mSrcY - 1;
      f2 = f4;
      j = i;
      i = n;
      if (m < i2) {
        break label574;
      }
      localObject6 = deepCopy((ArrayList)localObject3);
      localObject5 = new GridOccupancy(this.mTrgX, this.mTrgY);
      ((GridOccupancy)localObject5).markCells(0, 0, this.mTrgX, i2, true);
      if (!this.mShouldRemoveX) {
        break label323;
      }
      n = k;
      if (!this.mShouldRemoveY) {
        break label331;
      }
      i1 = m;
      localObject2 = new ArrayList();
      localArrayList = new ArrayList();
      localObject6 = ((ArrayList)localObject6).iterator();
    }
    for (;;)
    {
      if (!((Iterator)localObject6).hasNext()) {
        break label402;
      }
      DbEntry localDbEntry = (DbEntry)((Iterator)localObject6).next();
      if (((localDbEntry.cellX <= n) && (localDbEntry.spanX + localDbEntry.cellX > n)) || ((localDbEntry.cellY <= i1) && (localDbEntry.spanY + localDbEntry.cellY > i1)))
      {
        localArrayList.add(localDbEntry);
        if (localDbEntry.cellX >= n) {
          localDbEntry.cellX -= 1;
        }
        if (localDbEntry.cellY < i1) {
          continue;
        }
        localDbEntry.cellY -= 1;
        continue;
        i2 = 0;
        break;
        label323:
        n = Integer.MAX_VALUE;
        break label152;
        label331:
        i1 = Integer.MAX_VALUE;
        break label163;
      }
      if (localDbEntry.cellX > n) {
        localDbEntry.cellX -= 1;
      }
      if (localDbEntry.cellY > i1) {
        localDbEntry.cellY -= 1;
      }
      ((ArrayList)localObject2).add(localDbEntry);
      ((GridOccupancy)localObject5).markCells(localDbEntry, true);
    }
    label402:
    Object localObject5 = new OptimalPlacementSolution((GridOccupancy)localObject5, localArrayList, i2);
    ((OptimalPlacementSolution)localObject5).find();
    ((ArrayList)localObject2).addAll(((OptimalPlacementSolution)localObject5).finalPlacedItems);
    localObject4[0] = ((OptimalPlacementSolution)localObject5).lowestWeightLoss;
    localObject4[1] = ((OptimalPlacementSolution)localObject5).lowestMoveCost;
    float f3;
    if ((localObject4[0] < f1) || ((localObject4[0] == f1) && (localObject4[1] < f2)))
    {
      f2 = localObject4[0];
      f3 = localObject4[1];
      if (this.mShouldRemoveX)
      {
        j = k;
        label503:
        if (!this.mShouldRemoveY) {
          break label567;
        }
        i1 = m;
      }
    }
    for (;;)
    {
      localObject1 = localObject2;
      f4 = f3;
      f1 = f2;
      n = i1;
      i = j;
      if (this.mShouldRemoveY)
      {
        m -= 1;
        localObject1 = localObject2;
        f1 = f2;
        i = i1;
        f2 = f3;
        break label96;
        break label503;
        label567:
        i1 = i;
        continue;
        label574:
        n = i;
        i = j;
        f4 = f2;
      }
      localObject2 = localObject1;
      f2 = f1;
      j = n;
      m = i;
      if (this.mShouldRemoveX)
      {
        k += 1;
        break;
      }
      label617:
      Log.d("GridSizeMigrationTask", String.format("Removing row %d, column %d on screen %d", new Object[] { Integer.valueOf(j), Integer.valueOf(m), Long.valueOf(paramLong) }));
      localObject1 = new LongArrayMap();
      localObject3 = deepCopy((ArrayList)localObject3).iterator();
      while (((Iterator)localObject3).hasNext())
      {
        localObject4 = (DbEntry)((Iterator)localObject3).next();
        ((LongArrayMap)localObject1).put(((DbEntry)localObject4).id, localObject4);
      }
      localObject3 = ((ArrayList)localObject2).iterator();
      label853:
      while (((Iterator)localObject3).hasNext())
      {
        localObject4 = (DbEntry)((Iterator)localObject3).next();
        localObject5 = (DbEntry)((LongArrayMap)localObject1).get(((DbEntry)localObject4).id);
        ((LongArrayMap)localObject1).remove(((DbEntry)localObject4).id);
        if ((((DbEntry)localObject5).cellX == ((DbEntry)localObject4).cellX) && (((DbEntry)localObject5).cellY == ((DbEntry)localObject4).cellY) && (((DbEntry)localObject5).spanX == ((DbEntry)localObject4).spanX) && (((DbEntry)localObject5).spanY == ((DbEntry)localObject4).spanY) && (((DbEntry)localObject5).screenId == ((DbEntry)localObject4).screenId)) {}
        for (i = 1;; i = 0)
        {
          if (i != 0) {
            break label853;
          }
          update((DbEntry)localObject4);
          break;
        }
      }
      localObject1 = ((LongArrayMap)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject3 = (DbEntry)((Iterator)localObject1).next();
        this.mCarryOver.add(localObject3);
      }
      if ((!this.mCarryOver.isEmpty()) && (f2 == 0.0F))
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
      return;
      localObject2 = localObject1;
      f3 = f2;
      f2 = f1;
      i1 = i;
    }
  }
  
  protected final boolean migrateWorkspace()
  {
    int i = 0;
    ArrayList localArrayList = LauncherModel.loadWorkspaceScreensDb(this.mContext);
    if (localArrayList.isEmpty()) {
      throw new Exception("Unable to get workspace screens");
    }
    Object localObject1 = localArrayList.iterator();
    long l;
    while (((Iterator)localObject1).hasNext())
    {
      l = ((Long)((Iterator)localObject1).next()).longValue();
      Log.d("GridSizeMigrationTask", "Migrating " + l);
      migrateScreen(l);
    }
    if (!this.mCarryOver.isEmpty())
    {
      localObject1 = new LongArrayMap();
      Object localObject2 = this.mCarryOver.iterator();
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
        if (((OptimalPlacementSolution)localObject2).finalPlacedItems.size() > 0)
        {
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
        }
        throw new Exception("None of the items can be placed on an empty screen");
      } while (!this.mCarryOver.isEmpty());
      localObject1 = LauncherSettings.WorkspaceScreens.CONTENT_URI;
      this.mUpdateOperations.add(ContentProviderOperation.newDelete((Uri)localObject1).build());
      int j = localArrayList.size();
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
      boolean bool2;
      Point localPoint;
      if (!paramPoint2.equals(paramPoint1))
      {
        if (paramPoint1.x < paramPoint2.x) {
          paramPoint1.x = paramPoint2.x;
        }
        if (paramPoint1.y < paramPoint2.y) {
          paramPoint1.y = paramPoint2.y;
        }
        boolean bool1 = false;
        bool2 = bool1;
        if (paramPoint2.equals(paramPoint1)) {
          break label173;
        }
        localPoint = new Point(paramPoint1);
        if (paramPoint2.x < localPoint.x) {
          localPoint.x -= 1;
        }
        if (paramPoint2.y < localPoint.y) {
          localPoint.y -= 1;
        }
        if (!new GridSizeMigrationTask(this.mContext, LauncherAppState.getInstance(this.mContext).mInvariantDeviceProfile, this.mValidPackages, paramPoint1, localPoint).migrateWorkspace()) {
          break label176;
        }
        bool1 = true;
      }
      label173:
      label176:
      for (;;)
      {
        paramPoint1.set(localPoint.x, localPoint.y);
        break;
        bool2 = false;
        return bool2;
      }
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
      if ((paramFloat1 >= this.lowestWeightLoss) || ((paramFloat1 == this.lowestWeightLoss) && (paramFloat2 >= this.lowestMoveCost))) {
        label27:
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
      int i2 = localDbEntry.cellX;
      int i3 = localDbEntry.cellY;
      ArrayList localArrayList = new ArrayList(paramArrayList.size() + 1);
      localArrayList.addAll(paramArrayList);
      localArrayList.add(localDbEntry);
      int k;
      int m;
      int i;
      label156:
      int j;
      if ((localDbEntry.spanX > 1) || (localDbEntry.spanY > 1))
      {
        k = localDbEntry.spanX;
        m = localDbEntry.spanY;
        i = this.startY;
        if (i < GridSizeMigrationTask.this.mTrgY)
        {
          j = 0;
          label171:
          if (j < GridSizeMigrationTask.this.mTrgX)
          {
            if (j == i2) {
              break label1067;
            }
            localDbEntry.cellX = j;
          }
        }
      }
      label655:
      label670:
      label708:
      label1046:
      label1052:
      label1067:
      for (float f1 = 1.0F + paramFloat2;; f1 = paramFloat2)
      {
        float f2 = f1;
        if (i != i3)
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
          find(paramInt + 1, paramFloat1, 1.0F + f1, localArrayList);
          this.occupied.markCells(localDbEntry, false);
          localDbEntry.spanX += 1;
        }
        if ((m > localDbEntry.minSpanY) && (this.occupied.isRegionVacant(j, i, k, m - 1)))
        {
          localDbEntry.spanY -= 1;
          this.occupied.markCells(localDbEntry, true);
          find(paramInt + 1, paramFloat1, 1.0F + f1, localArrayList);
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
        localDbEntry.cellX = i2;
        localDbEntry.cellY = i3;
        j += 1;
        break label171;
        i += 1;
        break label156;
        paramInt += 1;
        paramFloat1 += localDbEntry.weight;
        break;
        int n = Integer.MAX_VALUE;
        m = Integer.MAX_VALUE;
        k = Integer.MAX_VALUE;
        i = this.startY;
        int i1;
        if (i < GridSizeMigrationTask.this.mTrgY)
        {
          j = 0;
          if (j < GridSizeMigrationTask.this.mTrgX)
          {
            if (this.occupied.cells[j][i] != 0) {
              break label1052;
            }
            if (this.ignoreMove)
            {
              i1 = 0;
              if (i1 >= n) {
                break label1052;
              }
              n = i;
              m = j;
            }
          }
        }
        for (k = i1;; k = i1)
        {
          i1 = j + 1;
          j = k;
          k = n;
          n = j;
          j = i1;
          break label670;
          i1 = (localDbEntry.cellX - j) * (localDbEntry.cellX - j) + (localDbEntry.cellY - i) * (localDbEntry.cellY - i);
          break label708;
          i += 1;
          break label655;
          if ((m < GridSizeMigrationTask.this.mTrgX) && (k < GridSizeMigrationTask.this.mTrgY))
          {
            if (m == i2) {
              break label1046;
            }
            localDbEntry.cellX = m;
          }
          for (f1 = 1.0F + paramFloat2;; f1 = paramFloat2)
          {
            f2 = f1;
            if (k != i3)
            {
              localDbEntry.cellY = k;
              f2 = f1 + 1.0F;
            }
            f1 = f2;
            if (this.ignoreMove) {
              f1 = paramFloat2;
            }
            this.occupied.markCells(localDbEntry, true);
            find(paramInt + 1, paramFloat1, f1, localArrayList);
            this.occupied.markCells(localDbEntry, false);
            localDbEntry.cellX = i2;
            localDbEntry.cellY = i3;
            if ((paramInt + 1 >= this.itemsToPlace.size()) || (((GridSizeMigrationTask.DbEntry)this.itemsToPlace.get(paramInt + 1)).weight < localDbEntry.weight) || (this.ignoreMove)) {
              break label27;
            }
            paramInt += 1;
            paramFloat1 += localDbEntry.weight;
            break;
            paramInt += 1;
            while (paramInt < this.itemsToPlace.size())
            {
              paramFloat1 += ((GridSizeMigrationTask.DbEntry)this.itemsToPlace.get(paramInt)).weight;
              paramInt += 1;
            }
            paramInt = this.itemsToPlace.size();
            paramFloat1 += localDbEntry.weight;
            break;
          }
          i1 = n;
          n = k;
        }
      }
    }
    
    public final void find()
    {
      find(0, 0.0F, 0.0F, new ArrayList());
    }
  }
}
