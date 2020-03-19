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
  private static final boolean DEBUG = true;
  public static boolean ENABLED = Utilities.ATLEAST_NOUGAT;
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
    throws Exception
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
        break label391;
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
      label328:
      int n;
      for (localDbEntry.weight = f;; localDbEntry.weight = (0.5F * n))
      {
        localArrayList.add(localDbEntry);
        break;
        f = 1.0F;
        break label328;
        n = getFolderItemsCount(localDbEntry.id);
        if (n == 0) {
          throw new Exception("Folder is empty");
        }
      }
      label391:
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
    InvariantDeviceProfile localInvariantDeviceProfile = LauncherAppState.getIDP(paramContext);
    String str = getPointString(localInvariantDeviceProfile.numColumns, localInvariantDeviceProfile.numRows);
    if ((str.equals(localSharedPreferences.getString("migration_src_workspace_size", ""))) && (localInvariantDeviceProfile.numHotseatIcons == localSharedPreferences.getInt("migration_src_hotseat_count", localInvariantDeviceProfile.numHotseatIcons))) {
      return true;
    }
    long l = System.currentTimeMillis();
    boolean bool = false;
    try
    {
      HashSet localHashSet = getValidPackages(paramContext);
      int i = localSharedPreferences.getInt("migration_src_hotseat_count", localInvariantDeviceProfile.numHotseatIcons);
      if (i != localInvariantDeviceProfile.numHotseatIcons) {
        bool = new GridSizeMigrationTask(paramContext, LauncherAppState.getIDP(paramContext), localHashSet, i, localInvariantDeviceProfile.numHotseatIcons).migrateHotseat();
      }
      Point localPoint1 = new Point(localInvariantDeviceProfile.numColumns, localInvariantDeviceProfile.numRows);
      Point localPoint2 = parsePoint(localSharedPreferences.getString("migration_src_workspace_size", str));
      if (new MultiStepMigrationTask(localHashSet, paramContext).migrate(localPoint2, localPoint1)) {
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
    catch (Exception paramContext)
    {
      Log.e("GridSizeMigrationTask", "Error during grid migration", paramContext);
      return false;
      return true;
    }
    finally
    {
      Log.v("GridSizeMigrationTask", "Workspace migration completed in " + (System.currentTimeMillis() - l));
      localSharedPreferences.edit().putString("migration_src_workspace_size", str).putInt("migration_src_hotseat_count", localInvariantDeviceProfile.numHotseatIcons).apply();
    }
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
    label44:
    ArrayList localArrayList1;
    ArrayList localArrayList2;
    if (this.mShouldRemoveX)
    {
      if (!this.mShouldRemoveY) {
        break label199;
      }
      localArrayList1 = new ArrayList();
      localArrayList2 = new ArrayList();
      paramArrayList = paramArrayList.iterator();
    }
    for (;;)
    {
      if (!paramArrayList.hasNext()) {
        break label267;
      }
      DbEntry localDbEntry = (DbEntry)paramArrayList.next();
      if (((localDbEntry.cellX <= paramInt1) && (localDbEntry.spanX + localDbEntry.cellX > paramInt1)) || ((localDbEntry.cellY <= paramInt2) && (localDbEntry.spanY + localDbEntry.cellY > paramInt2)))
      {
        localArrayList2.add(localDbEntry);
        if (localDbEntry.cellX >= paramInt1) {
          localDbEntry.cellX -= 1;
        }
        if (localDbEntry.cellY < paramInt2) {
          continue;
        }
        localDbEntry.cellY -= 1;
        continue;
        paramInt1 = Integer.MAX_VALUE;
        break;
        label199:
        paramInt2 = Integer.MAX_VALUE;
        break label44;
      }
      if (localDbEntry.cellX > paramInt1) {
        localDbEntry.cellX -= 1;
      }
      if (localDbEntry.cellY > paramInt2) {
        localDbEntry.cellY -= 1;
      }
      localArrayList1.add(localDbEntry);
      localGridOccupancy.markCells(localDbEntry, true);
    }
    label267:
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
    if (paramString.getComponent() != null) {
      verifyPackage(paramString.getComponent().getPackageName());
    }
    while (paramString.getPackage() == null) {
      return;
    }
    verifyPackage(paramString.getPackage());
  }
  
  private void verifyPackage(String paramString)
    throws Exception
  {
    if (!this.mValidPackages.contains(paramString)) {
      throw new Exception("Package not available");
    }
  }
  
  protected ArrayList<DbEntry> loadWorkspaceEntries(long paramLong)
  {
    String str = "container = -100 AND screen = " + paramLong;
    Cursor localCursor = queryWorkspace(new String[] { "_id", "itemType", "cellX", "cellY", "spanX", "spanY", "intent", "appWidgetProvider", "appWidgetId" }, str);
    int j = localCursor.getColumnIndexOrThrow("_id");
    int k = localCursor.getColumnIndexOrThrow("itemType");
    int m = localCursor.getColumnIndexOrThrow("cellX");
    int n = localCursor.getColumnIndexOrThrow("cellY");
    int i1 = localCursor.getColumnIndexOrThrow("spanX");
    int i2 = localCursor.getColumnIndexOrThrow("spanY");
    int i3 = localCursor.getColumnIndexOrThrow("intent");
    int i4 = localCursor.getColumnIndexOrThrow("appWidgetProvider");
    int i5 = localCursor.getColumnIndexOrThrow("appWidgetId");
    ArrayList localArrayList = new ArrayList();
    DbEntry localDbEntry;
    if (localCursor.moveToNext())
    {
      localDbEntry = new DbEntry();
      localDbEntry.id = localCursor.getLong(j);
      localDbEntry.itemType = localCursor.getInt(k);
      localDbEntry.cellX = localCursor.getInt(m);
      localDbEntry.cellY = localCursor.getInt(n);
      localDbEntry.spanX = localCursor.getInt(i1);
      localDbEntry.spanY = localCursor.getInt(i2);
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
        Log.d("GridSizeMigrationTask", "Removing item " + localDbEntry.id, localException);
        this.mEntryToRemove.add(Long.valueOf(localDbEntry.id));
      }
      break;
      verifyIntent(localCursor.getString(i3));
      float f;
      if (localDbEntry.itemType == 0)
      {
        f = 0.8F;
        label448:
        localDbEntry.weight = f;
      }
      for (;;)
      {
        localArrayList.add(localDbEntry);
        break;
        f = 1.0F;
        break label448;
        verifyPackage(ComponentName.unflattenFromString(localCursor.getString(i4)).getPackageName());
        localDbEntry.weight = Math.max(2.0F, 0.6F * localDbEntry.spanX * localDbEntry.spanY);
        int i = localCursor.getInt(i5);
        LauncherAppWidgetProviderInfo localLauncherAppWidgetProviderInfo = AppWidgetManagerCompat.getInstance(this.mContext).getLauncherAppWidgetInfo(i);
        Point localPoint = null;
        if (localLauncherAppWidgetProviderInfo != null) {
          localPoint = localLauncherAppWidgetProviderInfo.getMinSpans(this.mIdp, this.mContext);
        }
        if (localPoint != null) {
          if (localPoint.x > 0)
          {
            i = localPoint.x;
            localDbEntry.minSpanX = i;
            if (localPoint.y <= 0) {
              break label656;
            }
            i = localPoint.y;
            label604:
            localDbEntry.minSpanY = i;
          }
        }
        for (;;)
        {
          if ((localDbEntry.minSpanX <= this.mTrgX) && (localDbEntry.minSpanY <= this.mTrgY)) {
            break label679;
          }
          throw new Exception("Widget can't be resized down to fit the grid");
          i = localDbEntry.spanX;
          break;
          label656:
          i = localDbEntry.spanY;
          break label604;
          localDbEntry.minSpanY = 2;
          localDbEntry.minSpanX = 2;
        }
        label679:
        continue;
        i = getFolderItemsCount(localDbEntry.id);
        if (i == 0) {
          throw new Exception("Folder is empty");
        }
        localDbEntry.weight = (0.5F * i);
      }
      localCursor.close();
      return localArrayList;
    }
  }
  
  protected boolean migrateHotseat()
    throws Exception
  {
    ArrayList localArrayList = loadHotseatEntries();
    int i = this.mDestHotseatSize;
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
    i = 0;
    Object localObject = localArrayList.iterator();
    while (((Iterator)localObject).hasNext())
    {
      localDbEntry = (DbEntry)((Iterator)localObject).next();
      if (localDbEntry.screenId != i)
      {
        localDbEntry.screenId = i;
        localDbEntry.cellX = i;
        localDbEntry.cellY = 0;
        update(localDbEntry);
      }
      i += 1;
    }
    return applyOperations();
  }
  
  protected void migrateScreen(long paramLong)
  {
    int i2;
    Object localObject4;
    int j;
    int i;
    float f1;
    float f3;
    Object localObject5;
    int n;
    if ((FeatureFlags.QSB_ON_FIRST_SCREEN) && (paramLong == 0L))
    {
      i2 = 1;
      localObject4 = loadWorkspaceEntries(paramLong);
      j = Integer.MAX_VALUE;
      i = Integer.MAX_VALUE;
      f1 = Float.MAX_VALUE;
      f3 = Float.MAX_VALUE;
      localObject5 = new float[2];
      localObject1 = null;
      n = 0;
    }
    Object localObject2;
    float f2;
    for (;;)
    {
      localObject2 = localObject1;
      f2 = f1;
      int m = j;
      int k = i;
      int i1;
      if (n < this.mSrcX)
      {
        i1 = this.mSrcY - 1;
        f2 = f1;
        localObject2 = localObject1;
      }
      float f4;
      for (;;)
      {
        localObject1 = localObject2;
        f4 = f3;
        f1 = f2;
        m = j;
        k = i;
        if (i1 >= i2)
        {
          localObject3 = tryRemove(n, i1, i2, deepCopy((ArrayList)localObject4), (float[])localObject5);
          if (localObject5[0] >= f2)
          {
            localObject1 = localObject2;
            f4 = f3;
            f1 = f2;
            m = j;
            k = i;
            if (localObject5[0] == f2)
            {
              localObject1 = localObject2;
              f4 = f3;
              f1 = f2;
              m = j;
              k = i;
              if (localObject5[1] >= f3) {}
            }
          }
          else
          {
            f1 = localObject5[0];
            f4 = localObject5[1];
            if (this.mShouldRemoveX) {
              j = n;
            }
            if (this.mShouldRemoveY) {
              i = i1;
            }
            localObject1 = localObject3;
            k = i;
            m = j;
          }
          if (this.mShouldRemoveY) {}
        }
        else
        {
          if (this.mShouldRemoveX) {
            break label399;
          }
          f2 = f1;
          localObject2 = localObject1;
          Log.d("GridSizeMigrationTask", String.format("Removing row %d, column %d on screen %d", new Object[] { Integer.valueOf(k), Integer.valueOf(m), Long.valueOf(paramLong) }));
          localObject1 = new LongArrayMap();
          localObject3 = deepCopy((ArrayList)localObject4).iterator();
          while (((Iterator)localObject3).hasNext())
          {
            localObject4 = (DbEntry)((Iterator)localObject3).next();
            ((LongArrayMap)localObject1).put(((DbEntry)localObject4).id, localObject4);
          }
          i2 = 0;
          break;
        }
        i1 -= 1;
        localObject2 = localObject1;
        f3 = f4;
        f2 = f1;
        j = m;
        i = k;
      }
      label399:
      n += 1;
      f3 = f4;
      j = m;
      i = k;
    }
    Object localObject3 = ((ArrayList)localObject2).iterator();
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (DbEntry)((Iterator)localObject3).next();
      localObject5 = (DbEntry)((LongArrayMap)localObject1).get(((DbEntry)localObject4).id);
      ((LongArrayMap)localObject1).remove(((DbEntry)localObject4).id);
      if (!((DbEntry)localObject4).columnsSame((DbEntry)localObject5)) {
        update((DbEntry)localObject4);
      }
    }
    Object localObject1 = ((LongArrayMap)localObject1).iterator();
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
  }
  
  protected boolean migrateWorkspace()
    throws Exception
  {
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
      int i = 0;
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
      boolean bool2 = false;
      boolean bool3 = false;
      if (!paramPoint2.equals(paramPoint1))
      {
        if (paramPoint1.x < paramPoint2.x) {
          paramPoint1.x = paramPoint2.x;
        }
        boolean bool1 = bool3;
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
      if ((paramFloat1 >= this.lowestWeightLoss) || ((paramFloat1 == this.lowestWeightLoss) && (paramFloat2 >= this.lowestMoveCost))) {}
      GridSizeMigrationTask.DbEntry localDbEntry;
      label783:
      do
      {
        return;
        if (paramInt >= this.itemsToPlace.size())
        {
          this.lowestWeightLoss = paramFloat1;
          this.lowestMoveCost = paramFloat2;
          this.finalPlacedItems = GridSizeMigrationTask.deepCopy(paramArrayList);
          return;
        }
        localDbEntry = (GridSizeMigrationTask.DbEntry)this.itemsToPlace.get(paramInt);
        int i5 = localDbEntry.cellX;
        int i6 = localDbEntry.cellY;
        ArrayList localArrayList = new ArrayList(paramArrayList.size() + 1);
        localArrayList.addAll(paramArrayList);
        localArrayList.add(localDbEntry);
        int k;
        int j;
        if ((localDbEntry.spanX > 1) || (localDbEntry.spanY > 1))
        {
          k = localDbEntry.spanX;
          m = localDbEntry.spanY;
          i = this.startY;
          while (i < GridSizeMigrationTask.this.mTrgY)
          {
            j = 0;
            while (j < GridSizeMigrationTask.this.mTrgX)
            {
              f2 = paramFloat2;
              f1 = f2;
              if (j != i5)
              {
                localDbEntry.cellX = j;
                f1 = f2 + 1.0F;
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
                find(paramInt + 1, paramFloat1, 2.0F + f1, localArrayList);
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
          find(paramInt + 1, localDbEntry.weight + paramFloat1, paramFloat2, paramArrayList);
          return;
        }
        int i1 = Integer.MAX_VALUE;
        int n = Integer.MAX_VALUE;
        int m = Integer.MAX_VALUE;
        int i = this.startY;
        while (i < GridSizeMigrationTask.this.mTrgY)
        {
          j = 0;
          if (j < GridSizeMigrationTask.this.mTrgX)
          {
            int i4 = i1;
            int i3 = n;
            int i2 = m;
            if (this.occupied.cells[j][i] == 0) {
              if (!this.ignoreMove) {
                break label783;
              }
            }
            for (k = 0;; k = (localDbEntry.cellX - j) * (localDbEntry.cellX - j) + (localDbEntry.cellY - i) * (localDbEntry.cellY - i))
            {
              i4 = i1;
              i3 = n;
              i2 = m;
              if (k < i1)
              {
                i3 = j;
                i2 = i;
                i4 = k;
              }
              j += 1;
              i1 = i4;
              n = i3;
              m = i2;
              break;
            }
          }
          i += 1;
        }
        if ((n >= GridSizeMigrationTask.this.mTrgX) || (m >= GridSizeMigrationTask.this.mTrgY)) {
          break;
        }
        float f2 = paramFloat2;
        float f1 = f2;
        if (n != i5)
        {
          localDbEntry.cellX = n;
          f1 = f2 + 1.0F;
        }
        f2 = f1;
        if (m != i6)
        {
          localDbEntry.cellY = m;
          f2 = f1 + 1.0F;
        }
        f1 = f2;
        if (this.ignoreMove) {
          f1 = paramFloat2;
        }
        this.occupied.markCells(localDbEntry, true);
        find(paramInt + 1, paramFloat1, f1, localArrayList);
        this.occupied.markCells(localDbEntry, false);
        localDbEntry.cellX = i5;
        localDbEntry.cellY = i6;
      } while ((paramInt + 1 >= this.itemsToPlace.size()) || (((GridSizeMigrationTask.DbEntry)this.itemsToPlace.get(paramInt + 1)).weight < localDbEntry.weight) || (this.ignoreMove));
      find(paramInt + 1, localDbEntry.weight + paramFloat1, paramFloat2, paramArrayList);
      return;
      paramInt += 1;
      while (paramInt < this.itemsToPlace.size())
      {
        paramFloat1 += ((GridSizeMigrationTask.DbEntry)this.itemsToPlace.get(paramInt)).weight;
        paramInt += 1;
      }
      find(this.itemsToPlace.size(), localDbEntry.weight + paramFloat1, paramFloat2, paramArrayList);
    }
  }
}
