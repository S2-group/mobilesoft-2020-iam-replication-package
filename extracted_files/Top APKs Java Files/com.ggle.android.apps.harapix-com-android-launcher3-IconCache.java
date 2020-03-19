package com.android.launcher3;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Process;
import android.os.SystemClock;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.config.FeatureFlags;
import com.android.launcher3.graphics.LauncherIcons;
import com.android.launcher3.model.PackageItemInfo;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.InstantAppResolver;
import com.android.launcher3.util.Preconditions;
import com.android.launcher3.util.Provider;
import com.android.launcher3.util.SQLiteCacheHelper;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class IconCache
{
  private static final boolean DEBUG = false;
  private static final boolean DEBUG_IGNORE_CACHE = false;
  public static final String EMPTY_CLASS_NAME = ".";
  static final Object ICON_UPDATE_TOKEN = new Object();
  private static final int INITIAL_ICON_CACHE_CAPACITY = 50;
  private static final int LOW_RES_SCALE_FACTOR = 5;
  private static final String TAG = "Launcher.IconCache";
  private final HashMap<ComponentKey, CacheEntry> mCache = new HashMap(50);
  private final Context mContext;
  private final HashMap<UserHandle, Bitmap> mDefaultIcons = new HashMap();
  final IconDB mIconDb;
  private final int mIconDpi;
  private final IconProvider mIconProvider;
  private final InstantAppResolver mInstantAppResolver;
  private final LauncherAppsCompat mLauncherApps;
  private final BitmapFactory.Options mLowResOptions;
  final MainThreadExecutor mMainThreadExecutor = new MainThreadExecutor();
  private final PackageManager mPackageManager;
  final UserManagerCompat mUserManager;
  final Handler mWorkerHandler;
  
  public IconCache(Context paramContext, InvariantDeviceProfile paramInvariantDeviceProfile)
  {
    this.mContext = paramContext;
    this.mPackageManager = paramContext.getPackageManager();
    this.mUserManager = UserManagerCompat.getInstance(this.mContext);
    this.mLauncherApps = LauncherAppsCompat.getInstance(this.mContext);
    this.mInstantAppResolver = InstantAppResolver.newInstance(this.mContext);
    this.mIconDpi = paramInvariantDeviceProfile.fillResIconDpi;
    this.mIconDb = new IconDB(paramContext, paramInvariantDeviceProfile.iconBitmapSize);
    this.mIconProvider = ((IconProvider)Utilities.getOverrideObject(IconProvider.class, paramContext, 2131427478));
    this.mWorkerHandler = new Handler(LauncherModel.getWorkerLooper());
    this.mLowResOptions = new BitmapFactory.Options();
    this.mLowResOptions.inPreferredConfig = Bitmap.Config.RGB_565;
  }
  
  private void addIconToDB(ContentValues paramContentValues, ComponentName paramComponentName, PackageInfo paramPackageInfo, long paramLong)
  {
    paramContentValues.put("componentName", paramComponentName.flattenToString());
    paramContentValues.put("profileId", Long.valueOf(paramLong));
    paramContentValues.put("lastUpdated", Long.valueOf(paramPackageInfo.lastUpdateTime));
    paramContentValues.put("version", Integer.valueOf(paramPackageInfo.versionCode));
    this.mIconDb.insertOrReplace(paramContentValues);
  }
  
  private void applyCacheEntry(CacheEntry paramCacheEntry, ItemInfoWithIcon paramItemInfoWithIcon)
  {
    paramItemInfoWithIcon.title = Utilities.trim(paramCacheEntry.title);
    paramItemInfoWithIcon.contentDescription = paramCacheEntry.contentDescription;
    if (paramCacheEntry.icon == null) {}
    for (Bitmap localBitmap = getDefaultIcon(paramItemInfoWithIcon.user);; localBitmap = paramCacheEntry.icon)
    {
      paramItemInfoWithIcon.iconBitmap = localBitmap;
      paramItemInfoWithIcon.usingLowResIcon = paramCacheEntry.isLowResIcon;
      return;
    }
  }
  
  private Bitmap generateLowResIcon(Bitmap paramBitmap)
  {
    return Bitmap.createScaledBitmap(paramBitmap, paramBitmap.getWidth() / 5, paramBitmap.getHeight() / 5, true);
  }
  
  private CacheEntry getEntryForPackageLocked(String paramString, UserHandle paramUserHandle, boolean paramBoolean)
  {
    Preconditions.assertWorkerThread();
    ComponentKey localComponentKey = getPackageKey(paramString, paramUserHandle);
    Object localObject2 = (CacheEntry)this.mCache.get(localComponentKey);
    CacheEntry localCacheEntry;
    int j;
    int i;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (((CacheEntry)localObject2).isLowResIcon)
      {
        localObject1 = localObject2;
        if (paramBoolean) {}
      }
    }
    else
    {
      localCacheEntry = new CacheEntry();
      j = 1;
      i = j;
      if (getEntryFromDB(localComponentKey, localCacheEntry, paramBoolean)) {}
    }
    PackageInfo localPackageInfo;
    ApplicationInfo localApplicationInfo;
    for (;;)
    {
      try
      {
        if (!Process.myUserHandle().equals(paramUserHandle)) {
          break label157;
        }
        i = 0;
        localPackageInfo = this.mPackageManager.getPackageInfo(paramString, i);
        localApplicationInfo = localPackageInfo.applicationInfo;
        if (localApplicationInfo != null) {
          break;
        }
        throw new PackageManager.NameNotFoundException("ApplicationInfo is null");
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        i = 0;
      }
      localObject1 = localCacheEntry;
      if (i != 0)
      {
        this.mCache.put(localComponentKey, localCacheEntry);
        localObject1 = localCacheEntry;
      }
      return localObject1;
      label157:
      i = 8192;
    }
    localObject2 = LauncherIcons.createBadgedIconBitmap(localApplicationInfo.loadIcon(this.mPackageManager), paramUserHandle, this.mContext, localApplicationInfo.targetSdkVersion);
    Object localObject1 = localObject2;
    if (this.mInstantAppResolver.isInstantApp(localApplicationInfo)) {
      localObject1 = LauncherIcons.badgeWithDrawable((Bitmap)localObject2, this.mContext.getDrawable(2130837527), this.mContext);
    }
    Bitmap localBitmap = generateLowResIcon((Bitmap)localObject1);
    localCacheEntry.title = localApplicationInfo.loadLabel(this.mPackageManager);
    localCacheEntry.contentDescription = this.mUserManager.getBadgedLabelForUser(localCacheEntry.title, paramUserHandle);
    if (paramBoolean) {}
    for (localObject2 = localBitmap;; localObject2 = localObject1)
    {
      localCacheEntry.icon = ((Bitmap)localObject2);
      localCacheEntry.isLowResIcon = paramBoolean;
      addIconToDB(newContentValues((Bitmap)localObject1, localBitmap, localCacheEntry.title.toString(), paramString), localComponentKey.componentName, localPackageInfo, this.mUserManager.getSerialNumberForUser(paramUserHandle));
      i = j;
      break;
    }
  }
  
  private boolean getEntryFromDB(ComponentKey paramComponentKey, CacheEntry paramCacheEntry, boolean paramBoolean)
  {
    Object localObject4 = null;
    BitmapFactory.Options localOptions = null;
    Object localObject2 = localOptions;
    Object localObject1 = localObject4;
    label362:
    for (;;)
    {
      try
      {
        IconDB localIconDB = this.mIconDb;
        Object localObject3;
        if (paramBoolean)
        {
          localObject3 = "icon_low_res";
          localObject2 = localOptions;
          localObject1 = localObject4;
          String str1 = paramComponentKey.componentName.flattenToString();
          localObject2 = localOptions;
          localObject1 = localObject4;
          String str2 = Long.toString(this.mUserManager.getSerialNumberForUser(paramComponentKey.user));
          localObject2 = localOptions;
          localObject1 = localObject4;
          localObject3 = localIconDB.query(new String[] { localObject3, "label" }, "componentName = ? AND profileId = ?", new String[] { str1, str2 });
          localObject2 = localObject3;
          localObject1 = localObject3;
          if (!((Cursor)localObject3).moveToNext()) {
            continue;
          }
          if (!paramBoolean) {
            break label362;
          }
          localObject2 = localObject3;
          localObject1 = localObject3;
          localOptions = this.mLowResOptions;
          localObject2 = localObject3;
          localObject1 = localObject3;
          paramCacheEntry.icon = loadIconNoResize((Cursor)localObject3, 0, localOptions);
          localObject2 = localObject3;
          localObject1 = localObject3;
          paramCacheEntry.isLowResIcon = paramBoolean;
          localObject2 = localObject3;
          localObject1 = localObject3;
          paramCacheEntry.title = ((Cursor)localObject3).getString(1);
          localObject2 = localObject3;
          localObject1 = localObject3;
          if (paramCacheEntry.title == null)
          {
            localObject2 = localObject3;
            localObject1 = localObject3;
            paramCacheEntry.title = "";
            localObject2 = localObject3;
            localObject1 = localObject3;
            paramCacheEntry.contentDescription = "";
            if (localObject3 != null) {
              ((Cursor)localObject3).close();
            }
            return true;
          }
        }
        else
        {
          localObject3 = "icon";
          continue;
        }
        localObject2 = localObject3;
        localObject1 = localObject3;
        paramCacheEntry.contentDescription = this.mUserManager.getBadgedLabelForUser(paramCacheEntry.title, paramComponentKey.user);
        continue;
        localOptions = null;
      }
      catch (SQLiteException paramComponentKey)
      {
        localObject1 = localObject2;
        Log.d("Launcher.IconCache", "Error reading icon cache", paramComponentKey);
        if (localObject2 != null) {
          ((Cursor)localObject2).close();
        }
        return false;
        if (localObject3 != null) {
          ((Cursor)localObject3).close();
        }
      }
      finally
      {
        if (localObject1 != null) {
          localObject1.close();
        }
      }
    }
  }
  
  private Drawable getFullResDefaultActivityIcon()
  {
    Resources localResources = Resources.getSystem();
    if (Utilities.ATLEAST_OREO) {}
    for (int i = 17301651;; i = 17629184) {
      return getFullResIcon(localResources, i);
    }
  }
  
  private Drawable getFullResIcon(Resources paramResources, int paramInt)
  {
    try
    {
      paramResources = paramResources.getDrawableForDensity(paramInt, this.mIconDpi);
      if (paramResources != null) {
        return paramResources;
      }
    }
    catch (Resources.NotFoundException paramResources)
    {
      for (;;)
      {
        paramResources = null;
      }
    }
    return getFullResDefaultActivityIcon();
  }
  
  private static ComponentKey getPackageKey(String paramString, UserHandle paramUserHandle)
  {
    return new ComponentKey(new ComponentName(paramString, paramString + "."), paramUserHandle);
  }
  
  private void getTitleAndIcon(@NonNull ItemInfoWithIcon paramItemInfoWithIcon, @NonNull Provider<LauncherActivityInfo> paramProvider, boolean paramBoolean1, boolean paramBoolean2)
  {
    try
    {
      applyCacheEntry(cacheLocked(paramItemInfoWithIcon.getTargetComponent(), paramProvider, paramItemInfoWithIcon.user, paramBoolean1, paramBoolean2), paramItemInfoWithIcon);
      return;
    }
    finally
    {
      paramItemInfoWithIcon = finally;
      throw paramItemInfoWithIcon;
    }
  }
  
  private static Bitmap loadIconNoResize(Cursor paramCursor, int paramInt, BitmapFactory.Options paramOptions)
  {
    paramCursor = paramCursor.getBlob(paramInt);
    try
    {
      paramCursor = BitmapFactory.decodeByteArray(paramCursor, 0, paramCursor.length, paramOptions);
      return paramCursor;
    }
    catch (Exception paramCursor) {}
    return null;
  }
  
  private ContentValues newContentValues(Bitmap paramBitmap1, Bitmap paramBitmap2, String paramString1, String paramString2)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("icon", Utilities.flattenBitmap(paramBitmap1));
    localContentValues.put("icon_low_res", Utilities.flattenBitmap(paramBitmap2));
    localContentValues.put("label", paramString1);
    localContentValues.put("system_state", this.mIconProvider.getIconSystemState(paramString2));
    return localContentValues;
  }
  
  private void removeFromMemCacheLocked(String paramString, UserHandle paramUserHandle)
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.mCache.keySet().iterator();
    while (localIterator.hasNext())
    {
      ComponentKey localComponentKey = (ComponentKey)localIterator.next();
      if ((localComponentKey.componentName.getPackageName().equals(paramString)) && (localComponentKey.user.equals(paramUserHandle))) {
        localHashSet.add(localComponentKey);
      }
    }
    paramString = localHashSet.iterator();
    while (paramString.hasNext())
    {
      paramUserHandle = (ComponentKey)paramString.next();
      this.mCache.remove(paramUserHandle);
    }
  }
  
  private void updateDBIcons(UserHandle paramUserHandle, List<LauncherActivityInfo> paramList, Set<String> paramSet)
  {
    long l1 = this.mUserManager.getSerialNumberForUser(paramUserHandle);
    Object localObject1 = this.mContext.getPackageManager();
    HashMap localHashMap1 = new HashMap();
    localObject1 = ((PackageManager)localObject1).getInstalledPackages(8192).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (PackageInfo)((Iterator)localObject1).next();
      localHashMap1.put(((PackageInfo)localObject2).packageName, localObject2);
    }
    HashMap localHashMap2 = new HashMap();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      localObject1 = (LauncherActivityInfo)paramList.next();
      localHashMap2.put(((LauncherActivityInfo)localObject1).getComponentName(), localObject1);
    }
    HashSet localHashSet = new HashSet();
    Stack localStack = new Stack();
    ComponentName localComponentName = null;
    Object localObject2 = null;
    localObject1 = localObject2;
    paramList = localComponentName;
    Object localObject4;
    for (;;)
    {
      try
      {
        localObject3 = this.mIconDb;
        localObject1 = localObject2;
        paramList = localComponentName;
        localObject4 = Long.toString(l1);
        localObject1 = localObject2;
        paramList = localComponentName;
        localObject2 = ((IconDB)localObject3).query(new String[] { "rowid", "componentName", "lastUpdated", "version", "system_state" }, "profileId = ? ", new String[] { localObject4 });
        localObject1 = localObject2;
        paramList = (List<LauncherActivityInfo>)localObject2;
        i = ((Cursor)localObject2).getColumnIndex("componentName");
        localObject1 = localObject2;
        paramList = (List<LauncherActivityInfo>)localObject2;
        j = ((Cursor)localObject2).getColumnIndex("lastUpdated");
        localObject1 = localObject2;
        paramList = (List<LauncherActivityInfo>)localObject2;
        k = ((Cursor)localObject2).getColumnIndex("version");
        localObject1 = localObject2;
        paramList = (List<LauncherActivityInfo>)localObject2;
        m = ((Cursor)localObject2).getColumnIndex("rowid");
        localObject1 = localObject2;
        paramList = (List<LauncherActivityInfo>)localObject2;
        n = ((Cursor)localObject2).getColumnIndex("system_state");
      }
      catch (SQLiteException paramUserHandle)
      {
        Object localObject3;
        int i;
        int j;
        int k;
        int m;
        int n;
        paramList = (List<LauncherActivityInfo>)localObject1;
        Log.d("Launcher.IconCache", "Error reading icon cache", paramUserHandle);
        if (localObject1 == null) {
          continue;
        }
        ((Cursor)localObject1).close();
        if (localHashSet.isEmpty()) {
          continue;
        }
        this.mIconDb.delete(Utilities.createDbSelectionQuery("rowid", localHashSet), null);
        if ((localHashMap2.isEmpty()) && (localStack.isEmpty())) {
          continue;
        }
        paramUserHandle = new Stack();
        paramUserHandle.addAll(localHashMap2.values());
        new SerializedIconUpdateTask(l1, localHashMap1, paramUserHandle, localStack).scheduleNext();
        return;
        localObject1 = localObject2;
        paramList = (List<LauncherActivityInfo>)localObject2;
        if ((((PackageInfo)localObject3).applicationInfo.flags & 0x1000000) != 0) {
          continue;
        }
        localObject1 = localObject2;
        paramList = (List<LauncherActivityInfo>)localObject2;
        l2 = ((Cursor)localObject2).getLong(j);
        localObject1 = localObject2;
        paramList = (List<LauncherActivityInfo>)localObject2;
        i1 = ((Cursor)localObject2).getInt(k);
        localObject1 = localObject2;
        paramList = (List<LauncherActivityInfo>)localObject2;
        localObject4 = (LauncherActivityInfo)localHashMap2.remove(localComponentName);
        localObject1 = localObject2;
        paramList = (List<LauncherActivityInfo>)localObject2;
        if (i1 != ((PackageInfo)localObject3).versionCode) {
          continue;
        }
        localObject1 = localObject2;
        paramList = (List<LauncherActivityInfo>)localObject2;
        if (l2 != ((PackageInfo)localObject3).lastUpdateTime) {
          continue;
        }
        localObject1 = localObject2;
        paramList = (List<LauncherActivityInfo>)localObject2;
        if (TextUtils.equals(((Cursor)localObject2).getString(n), this.mIconProvider.getIconSystemState(((PackageInfo)localObject3).packageName))) {
          continue;
        }
        if (localObject4 != null) {
          break label770;
        }
        localObject1 = localObject2;
        paramList = (List<LauncherActivityInfo>)localObject2;
        remove(localComponentName, paramUserHandle);
        localObject1 = localObject2;
        paramList = (List<LauncherActivityInfo>)localObject2;
        localHashSet.add(Integer.valueOf(((Cursor)localObject2).getInt(m)));
        continue;
      }
      finally
      {
        if (paramList == null) {
          continue;
        }
        paramList.close();
      }
      localObject1 = localObject2;
      paramList = (List<LauncherActivityInfo>)localObject2;
      if (!((Cursor)localObject2).moveToNext()) {
        break label788;
      }
      localObject1 = localObject2;
      paramList = (List<LauncherActivityInfo>)localObject2;
      localComponentName = ComponentName.unflattenFromString(((Cursor)localObject2).getString(i));
      localObject1 = localObject2;
      paramList = (List<LauncherActivityInfo>)localObject2;
      localObject3 = (PackageInfo)localHashMap1.get(localComponentName.getPackageName());
      if (localObject3 != null) {
        continue;
      }
      localObject1 = localObject2;
      paramList = (List<LauncherActivityInfo>)localObject2;
      if (!paramSet.contains(localComponentName.getPackageName()))
      {
        localObject1 = localObject2;
        paramList = (List<LauncherActivityInfo>)localObject2;
        remove(localComponentName, paramUserHandle);
        localObject1 = localObject2;
        paramList = (List<LauncherActivityInfo>)localObject2;
        localHashSet.add(Integer.valueOf(((Cursor)localObject2).getInt(m)));
      }
    }
    for (;;)
    {
      long l2;
      int i1;
      label770:
      localObject1 = localObject2;
      paramList = (List<LauncherActivityInfo>)localObject2;
      localStack.add(localObject4);
      break;
      label788:
      if (localObject2 != null) {
        ((Cursor)localObject2).close();
      }
    }
  }
  
  void addIconToDBAndMemCache(LauncherActivityInfo paramLauncherActivityInfo, PackageInfo paramPackageInfo, long paramLong, boolean paramBoolean)
  {
    for (;;)
    {
      try
      {
        ComponentKey localComponentKey = new ComponentKey(paramLauncherActivityInfo.getComponentName(), paramLauncherActivityInfo.getUser());
        localObject1 = null;
        Object localObject2;
        if (!paramBoolean)
        {
          localObject2 = (CacheEntry)this.mCache.get(localComponentKey);
          if ((localObject2 != null) && (!((CacheEntry)localObject2).isLowResIcon))
          {
            localObject1 = localObject2;
            if (((CacheEntry)localObject2).icon == null) {}
          }
        }
        else
        {
          localObject2 = localObject1;
          if (localObject1 == null)
          {
            localObject2 = new CacheEntry();
            ((CacheEntry)localObject2).icon = LauncherIcons.createBadgedIconBitmap(getFullResIcon(paramLauncherActivityInfo), paramLauncherActivityInfo.getUser(), this.mContext, paramLauncherActivityInfo.getApplicationInfo().targetSdkVersion);
          }
          ((CacheEntry)localObject2).title = paramLauncherActivityInfo.getLabel();
          ((CacheEntry)localObject2).contentDescription = this.mUserManager.getBadgedLabelForUser(((CacheEntry)localObject2).title, paramLauncherActivityInfo.getUser());
          this.mCache.put(localComponentKey, localObject2);
          localObject1 = generateLowResIcon(((CacheEntry)localObject2).icon);
          addIconToDB(newContentValues(((CacheEntry)localObject2).icon, (Bitmap)localObject1, ((CacheEntry)localObject2).title.toString(), paramLauncherActivityInfo.getApplicationInfo().packageName), paramLauncherActivityInfo.getComponentName(), paramPackageInfo, paramLong);
          return;
        }
      }
      finally {}
      Object localObject1 = null;
    }
  }
  
  protected CacheEntry cacheLocked(@NonNull ComponentName paramComponentName, @NonNull Provider<LauncherActivityInfo> paramProvider, UserHandle paramUserHandle, boolean paramBoolean1, boolean paramBoolean2)
  {
    Preconditions.assertWorkerThread();
    ComponentKey localComponentKey = new ComponentKey(paramComponentName, paramUserHandle);
    Object localObject2 = (CacheEntry)this.mCache.get(localComponentKey);
    Object localObject1;
    CacheEntry localCacheEntry;
    int i;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (((CacheEntry)localObject2).isLowResIcon)
      {
        localObject1 = localObject2;
        if (paramBoolean2) {}
      }
    }
    else
    {
      localCacheEntry = new CacheEntry();
      this.mCache.put(localComponentKey, localCacheEntry);
      localObject1 = null;
      i = 0;
      if (!getEntryFromDB(localComponentKey, localCacheEntry, paramBoolean2)) {
        break label180;
      }
      paramComponentName = (ComponentName)localObject1;
    }
    for (;;)
    {
      localObject1 = localCacheEntry;
      if (TextUtils.isEmpty(localCacheEntry.title))
      {
        localObject2 = paramComponentName;
        if (paramComponentName == null)
        {
          localObject2 = paramComponentName;
          if (i == 0) {
            localObject2 = (LauncherActivityInfo)paramProvider.get();
          }
        }
        localObject1 = localCacheEntry;
        if (localObject2 != null)
        {
          localCacheEntry.title = ((LauncherActivityInfo)localObject2).getLabel();
          localCacheEntry.contentDescription = this.mUserManager.getBadgedLabelForUser(localCacheEntry.title, paramUserHandle);
          localObject1 = localCacheEntry;
        }
      }
      return localObject1;
      label180:
      localObject1 = (LauncherActivityInfo)paramProvider.get();
      int j = 1;
      if (localObject1 != null)
      {
        localCacheEntry.icon = LauncherIcons.createBadgedIconBitmap(getFullResIcon((LauncherActivityInfo)localObject1), ((LauncherActivityInfo)localObject1).getUser(), this.mContext, ((LauncherActivityInfo)paramProvider.get()).getApplicationInfo().targetSdkVersion);
        paramComponentName = (ComponentName)localObject1;
        i = j;
      }
      else
      {
        if (paramBoolean1)
        {
          paramComponentName = getEntryForPackageLocked(paramComponentName.getPackageName(), paramUserHandle, false);
          if (paramComponentName != null)
          {
            localCacheEntry.icon = paramComponentName.icon;
            localCacheEntry.title = paramComponentName.title;
            localCacheEntry.contentDescription = paramComponentName.contentDescription;
          }
        }
        paramComponentName = (ComponentName)localObject1;
        i = j;
        if (localCacheEntry.icon == null)
        {
          localCacheEntry.icon = getDefaultIcon(paramUserHandle);
          paramComponentName = (ComponentName)localObject1;
          i = j;
        }
      }
    }
  }
  
  public void cachePackageInstallInfo(String paramString, UserHandle paramUserHandle, Bitmap paramBitmap, CharSequence paramCharSequence)
  {
    try
    {
      removeFromMemCacheLocked(paramString, paramUserHandle);
      ComponentKey localComponentKey = getPackageKey(paramString, paramUserHandle);
      paramUserHandle = (CacheEntry)this.mCache.get(localComponentKey);
      paramString = paramUserHandle;
      if (paramUserHandle == null) {
        paramString = new CacheEntry();
      }
      if (!TextUtils.isEmpty(paramCharSequence)) {
        paramString.title = paramCharSequence;
      }
      if (paramBitmap != null) {
        paramString.icon = LauncherIcons.createIconBitmap(paramBitmap, this.mContext);
      }
      if ((!TextUtils.isEmpty(paramCharSequence)) && (paramString.icon != null)) {
        this.mCache.put(localComponentKey, paramString);
      }
      return;
    }
    finally {}
  }
  
  public void clear()
  {
    try
    {
      Preconditions.assertWorkerThread();
      this.mIconDb.clear();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public Bitmap getDefaultIcon(UserHandle paramUserHandle)
  {
    try
    {
      if (!this.mDefaultIcons.containsKey(paramUserHandle)) {
        this.mDefaultIcons.put(paramUserHandle, makeDefaultIcon(paramUserHandle));
      }
      paramUserHandle = (Bitmap)this.mDefaultIcons.get(paramUserHandle);
      return paramUserHandle;
    }
    finally {}
  }
  
  public Drawable getFullResIcon(ActivityInfo paramActivityInfo)
  {
    try
    {
      Resources localResources = this.mPackageManager.getResourcesForApplication(paramActivityInfo.applicationInfo);
      if (localResources != null)
      {
        int i = paramActivityInfo.getIconResource();
        if (i != 0) {
          return getFullResIcon(localResources, i);
        }
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        Object localObject = null;
      }
    }
    return getFullResDefaultActivityIcon();
  }
  
  public Drawable getFullResIcon(LauncherActivityInfo paramLauncherActivityInfo)
  {
    return getFullResIcon(paramLauncherActivityInfo, true);
  }
  
  public Drawable getFullResIcon(LauncherActivityInfo paramLauncherActivityInfo, boolean paramBoolean)
  {
    return this.mIconProvider.getIcon(paramLauncherActivityInfo, this.mIconDpi, paramBoolean);
  }
  
  public Drawable getFullResIcon(String paramString, int paramInt)
  {
    try
    {
      paramString = this.mPackageManager.getResourcesForApplication(paramString);
      if ((paramString != null) && (paramInt != 0)) {
        return getFullResIcon(paramString, paramInt);
      }
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;)
      {
        paramString = null;
      }
    }
    return getFullResDefaultActivityIcon();
  }
  
  public void getTitleAndIcon(ItemInfoWithIcon paramItemInfoWithIcon, LauncherActivityInfo paramLauncherActivityInfo, boolean paramBoolean)
  {
    try
    {
      getTitleAndIcon(paramItemInfoWithIcon, Provider.of(paramLauncherActivityInfo), false, paramBoolean);
      return;
    }
    finally
    {
      paramItemInfoWithIcon = finally;
      throw paramItemInfoWithIcon;
    }
  }
  
  /* Error */
  public void getTitleAndIcon(ItemInfoWithIcon paramItemInfoWithIcon, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual 490	com/android/launcher3/ItemInfoWithIcon:getTargetComponent	()Landroid/content/ComponentName;
    //   6: ifnonnull +37 -> 43
    //   9: aload_1
    //   10: aload_0
    //   11: aload_1
    //   12: getfield 261	com/android/launcher3/ItemInfoWithIcon:user	Landroid/os/UserHandle;
    //   15: invokevirtual 265	com/android/launcher3/IconCache:getDefaultIcon	(Landroid/os/UserHandle;)Landroid/graphics/Bitmap;
    //   18: putfield 268	com/android/launcher3/ItemInfoWithIcon:iconBitmap	Landroid/graphics/Bitmap;
    //   21: aload_1
    //   22: ldc_w 433
    //   25: putfield 249	com/android/launcher3/ItemInfoWithIcon:title	Ljava/lang/CharSequence;
    //   28: aload_1
    //   29: ldc_w 433
    //   32: putfield 253	com/android/launcher3/ItemInfoWithIcon:contentDescription	Ljava/lang/CharSequence;
    //   35: aload_1
    //   36: iconst_0
    //   37: putfield 274	com/android/launcher3/ItemInfoWithIcon:usingLowResIcon	Z
    //   40: aload_0
    //   41: monitorexit
    //   42: return
    //   43: aload_0
    //   44: aload_1
    //   45: new 10	com/android/launcher3/IconCache$ActivityInfoProvider
    //   48: dup
    //   49: aload_0
    //   50: aload_1
    //   51: invokevirtual 721	com/android/launcher3/ItemInfoWithIcon:getIntent	()Landroid/content/Intent;
    //   54: aload_1
    //   55: getfield 261	com/android/launcher3/ItemInfoWithIcon:user	Landroid/os/UserHandle;
    //   58: invokespecial 724	com/android/launcher3/IconCache$ActivityInfoProvider:<init>	(Lcom/android/launcher3/IconCache;Landroid/content/Intent;Landroid/os/UserHandle;)V
    //   61: iconst_1
    //   62: iload_2
    //   63: invokespecial 716	com/android/launcher3/IconCache:getTitleAndIcon	(Lcom/android/launcher3/ItemInfoWithIcon;Lcom/android/launcher3/util/Provider;ZZ)V
    //   66: goto -26 -> 40
    //   69: astore_1
    //   70: aload_0
    //   71: monitorexit
    //   72: aload_1
    //   73: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	74	0	this	IconCache
    //   0	74	1	paramItemInfoWithIcon	ItemInfoWithIcon
    //   0	74	2	paramBoolean	boolean
    // Exception table:
    //   from	to	target	type
    //   2	40	69	finally
    //   43	66	69	finally
  }
  
  public void getTitleAndIconForApp(PackageItemInfo paramPackageItemInfo, boolean paramBoolean)
  {
    try
    {
      applyCacheEntry(getEntryForPackageLocked(paramPackageItemInfo.packageName, paramPackageItemInfo.user, paramBoolean), paramPackageItemInfo);
      return;
    }
    finally
    {
      paramPackageItemInfo = finally;
      throw paramPackageItemInfo;
    }
  }
  
  public boolean isDefaultIcon(Bitmap paramBitmap, UserHandle paramUserHandle)
  {
    return this.mDefaultIcons.get(paramUserHandle) == paramBitmap;
  }
  
  protected Bitmap makeDefaultIcon(UserHandle paramUserHandle)
  {
    return LauncherIcons.createBadgedIconBitmap(getFullResDefaultActivityIcon(), paramUserHandle, this.mContext, 26);
  }
  
  public void remove(ComponentName paramComponentName, UserHandle paramUserHandle)
  {
    try
    {
      this.mCache.remove(new ComponentKey(paramComponentName, paramUserHandle));
      return;
    }
    finally
    {
      paramComponentName = finally;
      throw paramComponentName;
    }
  }
  
  public void removeIconsForPkg(String paramString, UserHandle paramUserHandle)
  {
    try
    {
      removeFromMemCacheLocked(paramString, paramUserHandle);
      long l = this.mUserManager.getSerialNumberForUser(paramUserHandle);
      this.mIconDb.delete("componentName LIKE ? AND profileId = ?", new String[] { paramString + "/%", Long.toString(l) });
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void updateDbIcons(Set<String> paramSet)
  {
    this.mWorkerHandler.removeCallbacksAndMessages(ICON_UPDATE_TOKEN);
    this.mIconProvider.updateSystemStateString();
    Iterator localIterator = this.mUserManager.getUserProfiles().iterator();
    UserHandle localUserHandle;
    List localList;
    if (localIterator.hasNext())
    {
      localUserHandle = (UserHandle)localIterator.next();
      localList = this.mLauncherApps.getActivityList(null, localUserHandle);
      if ((localList != null) && (!localList.isEmpty())) {}
    }
    else
    {
      return;
    }
    if (Process.myUserHandle().equals(localUserHandle)) {}
    for (Object localObject = paramSet;; localObject = Collections.emptySet())
    {
      updateDBIcons(localUserHandle, localList, (Set)localObject);
      break;
    }
  }
  
  public IconLoadRequest updateIconInBackground(final ItemInfoUpdateReceiver paramItemInfoUpdateReceiver, final ItemInfoWithIcon paramItemInfoWithIcon)
  {
    paramItemInfoUpdateReceiver = new Runnable()
    {
      public void run()
      {
        if (((paramItemInfoWithIcon instanceof AppInfo)) || ((paramItemInfoWithIcon instanceof ShortcutInfo))) {
          IconCache.this.getTitleAndIcon(paramItemInfoWithIcon, false);
        }
        for (;;)
        {
          IconCache.this.mMainThreadExecutor.execute(new Runnable()
          {
            public void run()
            {
              IconCache.1.this.val$caller.reapplyItemInfo(IconCache.1.this.val$info);
            }
          });
          return;
          if ((paramItemInfoWithIcon instanceof PackageItemInfo)) {
            IconCache.this.getTitleAndIconForApp((PackageItemInfo)paramItemInfoWithIcon, false);
          }
        }
      }
    };
    this.mWorkerHandler.post(paramItemInfoUpdateReceiver);
    return new IconLoadRequest(paramItemInfoUpdateReceiver, this.mWorkerHandler);
  }
  
  /* Error */
  public void updateIconsForPkg(String paramString, UserHandle paramUserHandle)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_2
    //   5: invokevirtual 778	com/android/launcher3/IconCache:removeIconsForPkg	(Ljava/lang/String;Landroid/os/UserHandle;)V
    //   8: aload_0
    //   9: getfield 104	com/android/launcher3/IconCache:mPackageManager	Landroid/content/pm/PackageManager;
    //   12: aload_1
    //   13: sipush 8192
    //   16: invokevirtual 329	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   19: astore 5
    //   21: aload_0
    //   22: getfield 112	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   25: aload_2
    //   26: invokevirtual 397	com/android/launcher3/compat/UserManagerCompat:getSerialNumberForUser	(Landroid/os/UserHandle;)J
    //   29: lstore_3
    //   30: aload_0
    //   31: getfield 119	com/android/launcher3/IconCache:mLauncherApps	Lcom/android/launcher3/compat/LauncherAppsCompat;
    //   34: aload_1
    //   35: aload_2
    //   36: invokevirtual 754	com/android/launcher3/compat/LauncherAppsCompat:getActivityList	(Ljava/lang/String;Landroid/os/UserHandle;)Ljava/util/List;
    //   39: invokeinterface 569 1 0
    //   44: astore_1
    //   45: aload_1
    //   46: invokeinterface 545 1 0
    //   51: ifeq +34 -> 85
    //   54: aload_0
    //   55: aload_1
    //   56: invokeinterface 549 1 0
    //   61: checkcast 574	android/content/pm/LauncherActivityInfo
    //   64: aload 5
    //   66: lload_3
    //   67: iconst_0
    //   68: invokevirtual 780	com/android/launcher3/IconCache:addIconToDBAndMemCache	(Landroid/content/pm/LauncherActivityInfo;Landroid/content/pm/PackageInfo;JZ)V
    //   71: goto -26 -> 45
    //   74: astore_1
    //   75: ldc 44
    //   77: ldc_w 782
    //   80: aload_1
    //   81: invokestatic 445	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   84: pop
    //   85: aload_0
    //   86: monitorexit
    //   87: return
    //   88: astore_1
    //   89: aload_0
    //   90: monitorexit
    //   91: aload_1
    //   92: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	93	0	this	IconCache
    //   0	93	1	paramString	String
    //   0	93	2	paramUserHandle	UserHandle
    //   29	38	3	l	long
    //   19	46	5	localPackageInfo	PackageInfo
    // Exception table:
    //   from	to	target	type
    //   8	45	74	android/content/pm/PackageManager$NameNotFoundException
    //   45	71	74	android/content/pm/PackageManager$NameNotFoundException
    //   2	8	88	finally
    //   8	45	88	finally
    //   45	71	88	finally
    //   75	85	88	finally
  }
  
  public void updateTitleAndIcon(AppInfo paramAppInfo)
  {
    try
    {
      CacheEntry localCacheEntry = cacheLocked(paramAppInfo.componentName, Provider.of(null), paramAppInfo.user, false, paramAppInfo.usingLowResIcon);
      if ((localCacheEntry.icon != null) && (!isDefaultIcon(localCacheEntry.icon, paramAppInfo.user))) {
        applyCacheEntry(localCacheEntry, paramAppInfo);
      }
      return;
    }
    finally
    {
      paramAppInfo = finally;
      throw paramAppInfo;
    }
  }
  
  private class ActivityInfoProvider
    extends Provider<LauncherActivityInfo>
  {
    private final Intent mIntent;
    private final UserHandle mUser;
    
    public ActivityInfoProvider(Intent paramIntent, UserHandle paramUserHandle)
    {
      this.mIntent = paramIntent;
      this.mUser = paramUserHandle;
    }
    
    public LauncherActivityInfo get()
    {
      return IconCache.this.mLauncherApps.resolveActivity(this.mIntent, this.mUser);
    }
  }
  
  public static class CacheEntry
  {
    public CharSequence contentDescription = "";
    public Bitmap icon;
    public boolean isLowResIcon;
    public CharSequence title = "";
    
    public CacheEntry() {}
  }
  
  private static final class IconDB
    extends SQLiteCacheHelper
  {
    private static final String COLUMN_COMPONENT = "componentName";
    private static final String COLUMN_ICON = "icon";
    private static final String COLUMN_ICON_LOW_RES = "icon_low_res";
    private static final String COLUMN_LABEL = "label";
    private static final String COLUMN_LAST_UPDATED = "lastUpdated";
    private static final String COLUMN_ROWID = "rowid";
    private static final String COLUMN_SYSTEM_STATE = "system_state";
    private static final String COLUMN_USER = "profileId";
    private static final String COLUMN_VERSION = "version";
    private static final int DB_VERSION = 17;
    private static final int RELEASE_VERSION;
    private static final String TABLE_NAME = "icons";
    
    static
    {
      if (FeatureFlags.LAUNCHER3_DISABLE_ICON_NORMALIZATION) {}
      for (int i = 0;; i = 1)
      {
        RELEASE_VERSION = i + 17;
        return;
      }
    }
    
    public IconDB(Context paramContext, int paramInt)
    {
      super("app_icons.db", (RELEASE_VERSION << 16) + paramInt, "icons");
    }
    
    protected void onCreateTable(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS icons (componentName TEXT NOT NULL, profileId INTEGER NOT NULL, lastUpdated INTEGER NOT NULL DEFAULT 0, version INTEGER NOT NULL DEFAULT 0, icon BLOB, icon_low_res BLOB, label TEXT, system_state TEXT, PRIMARY KEY (componentName, profileId) );");
    }
  }
  
  public static class IconLoadRequest
  {
    private final Handler mHandler;
    private final Runnable mRunnable;
    
    IconLoadRequest(Runnable paramRunnable, Handler paramHandler)
    {
      this.mRunnable = paramRunnable;
      this.mHandler = paramHandler;
    }
    
    public void cancel()
    {
      this.mHandler.removeCallbacks(this.mRunnable);
    }
  }
  
  public static abstract interface ItemInfoUpdateReceiver
  {
    public abstract void reapplyItemInfo(ItemInfoWithIcon paramItemInfoWithIcon);
  }
  
  class SerializedIconUpdateTask
    implements Runnable
  {
    private final Stack<LauncherActivityInfo> mAppsToAdd;
    private final Stack<LauncherActivityInfo> mAppsToUpdate;
    private final HashMap<String, PackageInfo> mPkgInfoMap;
    private final HashSet<String> mUpdatedPackages = new HashSet();
    private final long mUserSerial;
    
    SerializedIconUpdateTask(HashMap<String, PackageInfo> paramHashMap, Stack<LauncherActivityInfo> paramStack1, Stack<LauncherActivityInfo> paramStack2)
    {
      this.mUserSerial = ???;
      this.mPkgInfoMap = paramStack1;
      this.mAppsToAdd = paramStack2;
      Object localObject;
      this.mAppsToUpdate = localObject;
    }
    
    public void run()
    {
      LauncherActivityInfo localLauncherActivityInfo;
      Object localObject;
      if (!this.mAppsToUpdate.isEmpty())
      {
        localLauncherActivityInfo = (LauncherActivityInfo)this.mAppsToUpdate.pop();
        localObject = localLauncherActivityInfo.getComponentName().getPackageName();
        PackageInfo localPackageInfo = (PackageInfo)this.mPkgInfoMap.get(localObject);
        IconCache.this.addIconToDBAndMemCache(localLauncherActivityInfo, localPackageInfo, this.mUserSerial, true);
        this.mUpdatedPackages.add(localObject);
        if ((this.mAppsToUpdate.isEmpty()) && (!this.mUpdatedPackages.isEmpty())) {
          LauncherAppState.getInstance(IconCache.this.mContext).getModel().onPackageIconsUpdated(this.mUpdatedPackages, IconCache.this.mUserManager.getUserForSerialNumber(this.mUserSerial));
        }
        scheduleNext();
      }
      do
      {
        do
        {
          return;
        } while (this.mAppsToAdd.isEmpty());
        localLauncherActivityInfo = (LauncherActivityInfo)this.mAppsToAdd.pop();
        localObject = (PackageInfo)this.mPkgInfoMap.get(localLauncherActivityInfo.getComponentName().getPackageName());
        if (localObject != null) {
          IconCache.this.addIconToDBAndMemCache(localLauncherActivityInfo, (PackageInfo)localObject, this.mUserSerial, false);
        }
      } while (this.mAppsToAdd.isEmpty());
      scheduleNext();
    }
    
    public void scheduleNext()
    {
      IconCache.this.mWorkerHandler.postAtTime(this, IconCache.ICON_UPDATE_TOKEN, SystemClock.uptimeMillis() + 1L);
    }
  }
}
