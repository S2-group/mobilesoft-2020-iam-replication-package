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
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Process;
import android.os.SystemClock;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.config.FeatureFlags;
import com.android.launcher3.graphics.LauncherIcons;
import com.android.launcher3.model.CacheDataUpdatedTask;
import com.android.launcher3.model.PackageItemInfo;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.InstantAppResolver;
import com.android.launcher3.util.Provider;
import com.android.launcher3.util.SQLiteCacheHelper;
import com.android.launcher3.util.SQLiteCacheHelper.MySQLiteOpenHelper;
import com.google.android.apps.nexuslauncher.b;
import com.hdeva.launcher.LeanSettings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public final class IconCache
{
  static final Object ICON_UPDATE_TOKEN = new Object();
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
    this.mIconProvider = ((IconProvider)Utilities.getOverrideObject(IconProvider.class, paramContext, 2131427645));
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
    String str = LeanSettings.getCustomAppName(this.mContext, paramItemInfoWithIcon.getTargetComponent());
    Object localObject = str;
    if (TextUtils.isEmpty(str)) {
      localObject = paramCacheEntry.title;
    }
    paramItemInfoWithIcon.title = Utilities.trim((CharSequence)localObject);
    paramItemInfoWithIcon.contentDescription = paramCacheEntry.contentDescription;
    if (paramCacheEntry.icon == null) {}
    for (localObject = getDefaultIcon(paramItemInfoWithIcon.user);; localObject = paramCacheEntry.icon)
    {
      paramItemInfoWithIcon.iconBitmap = ((Bitmap)localObject);
      paramItemInfoWithIcon.usingLowResIcon = paramCacheEntry.isLowResIcon;
      return;
    }
  }
  
  private CacheEntry cacheLocked(ComponentName paramComponentName, Provider<LauncherActivityInfo> paramProvider, UserHandle paramUserHandle, boolean paramBoolean1, boolean paramBoolean2)
  {
    ComponentKey localComponentKey = new ComponentKey(paramComponentName, paramUserHandle);
    CacheEntry localCacheEntry = (CacheEntry)this.mCache.get(localComponentKey);
    Object localObject;
    int i;
    if (localCacheEntry != null)
    {
      localObject = localCacheEntry;
      if (localCacheEntry.isLowResIcon)
      {
        localObject = localCacheEntry;
        if (paramBoolean2) {}
      }
    }
    else
    {
      localCacheEntry = new CacheEntry();
      this.mCache.put(localComponentKey, localCacheEntry);
      if (getEntryFromDB(localComponentKey, localCacheEntry, paramBoolean2)) {
        break label291;
      }
      localObject = (LauncherActivityInfo)paramProvider.get();
      if (localObject == null) {
        break label217;
      }
      localCacheEntry.icon = LauncherIcons.createBadgedIconBitmap(getFullResIcon((LauncherActivityInfo)localObject, true), ((LauncherActivityInfo)localObject).getUser(), this.mContext, ((LauncherActivityInfo)paramProvider.get()).getApplicationInfo().targetSdkVersion);
      paramComponentName = (ComponentName)localObject;
      i = 1;
    }
    for (;;)
    {
      if (TextUtils.isEmpty(localCacheEntry.title))
      {
        localObject = paramComponentName;
        if (paramComponentName == null)
        {
          localObject = paramComponentName;
          if (i == 0) {
            localObject = (LauncherActivityInfo)paramProvider.get();
          }
        }
        if (localObject != null)
        {
          localCacheEntry.title = ((LauncherActivityInfo)localObject).getLabel();
          localCacheEntry.contentDescription = this.mUserManager.getBadgedLabelForUser(localCacheEntry.title, paramUserHandle);
        }
      }
      localObject = localCacheEntry;
      return localObject;
      label217:
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
      if (localCacheEntry.icon == null) {
        localCacheEntry.icon = getDefaultIcon(paramUserHandle);
      }
      paramComponentName = (ComponentName)localObject;
      i = 1;
      continue;
      label291:
      paramComponentName = null;
      i = 0;
    }
  }
  
  private static Bitmap generateLowResIcon(Bitmap paramBitmap)
  {
    return Bitmap.createScaledBitmap(paramBitmap, paramBitmap.getWidth() / 5, paramBitmap.getHeight() / 5, true);
  }
  
  private CacheEntry getEntryForPackageLocked(String paramString, UserHandle paramUserHandle, boolean paramBoolean)
  {
    ComponentKey localComponentKey = getPackageKey(paramString, paramUserHandle);
    CacheEntry localCacheEntry = (CacheEntry)this.mCache.get(localComponentKey);
    Object localObject;
    int i;
    PackageInfo localPackageInfo;
    if (localCacheEntry != null)
    {
      localObject = localCacheEntry;
      if (localCacheEntry.isLowResIcon)
      {
        localObject = localCacheEntry;
        if (paramBoolean) {}
      }
    }
    else
    {
      localCacheEntry = new CacheEntry();
      if (getEntryFromDB(localComponentKey, localCacheEntry, paramBoolean)) {
        break label286;
      }
      try
      {
        if (Process.myUserHandle().equals(paramUserHandle))
        {
          i = 0;
          localPackageInfo = this.mPackageManager.getPackageInfo(paramString, i);
          localObject = localPackageInfo.applicationInfo;
          if (localObject != null) {
            break label151;
          }
          throw new PackageManager.NameNotFoundException("ApplicationInfo is null");
        }
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        i = 0;
      }
    }
    for (;;)
    {
      if (i != 0) {
        this.mCache.put(localComponentKey, localCacheEntry);
      }
      localObject = localCacheEntry;
      return localObject;
      i = 8192;
      break;
      label151:
      Bitmap localBitmap1 = LauncherIcons.createBadgedIconBitmap(((ApplicationInfo)localObject).loadIcon(this.mPackageManager), paramUserHandle, this.mContext, ((ApplicationInfo)localObject).targetSdkVersion);
      Bitmap localBitmap2 = generateLowResIcon(localBitmap1);
      localCacheEntry.title = ((ApplicationInfo)localObject).loadLabel(this.mPackageManager);
      localCacheEntry.contentDescription = this.mUserManager.getBadgedLabelForUser(localCacheEntry.title, paramUserHandle);
      if (paramBoolean) {}
      for (localObject = localBitmap2;; localObject = localBitmap1)
      {
        localCacheEntry.icon = ((Bitmap)localObject);
        localCacheEntry.isLowResIcon = paramBoolean;
        addIconToDB(newContentValues(localBitmap1, localBitmap2, localCacheEntry.title.toString(), paramString), localComponentKey.componentName, localPackageInfo, this.mUserManager.getSerialNumberForUser(paramUserHandle));
        i = 1;
        break;
      }
      label286:
      i = 1;
    }
  }
  
  /* Error */
  private boolean getEntryFromDB(ComponentKey paramComponentKey, CacheEntry paramCacheEntry, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 8
    //   6: aconst_null
    //   7: astore 7
    //   9: aload 8
    //   11: astore 4
    //   13: aload_0
    //   14: getfield 127	com/android/launcher3/IconCache:mIconDb	Lcom/android/launcher3/IconCache$IconDB;
    //   17: astore 9
    //   19: iload_3
    //   20: ifeq +171 -> 191
    //   23: ldc_w 421
    //   26: astore 5
    //   28: aload 8
    //   30: astore 4
    //   32: aload_1
    //   33: getfield 411	com/android/launcher3/util/ComponentKey:componentName	Landroid/content/ComponentName;
    //   36: invokevirtual 179	android/content/ComponentName:flattenToString	()Ljava/lang/String;
    //   39: astore 10
    //   41: aload 8
    //   43: astore 4
    //   45: aload_0
    //   46: getfield 97	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   49: aload_1
    //   50: getfield 422	com/android/launcher3/util/ComponentKey:user	Landroid/os/UserHandle;
    //   53: invokevirtual 415	com/android/launcher3/compat/UserManagerCompat:getSerialNumberForUser	(Landroid/os/UserHandle;)J
    //   56: invokestatic 425	java/lang/Long:toString	(J)Ljava/lang/String;
    //   59: astore 11
    //   61: aload 8
    //   63: astore 4
    //   65: aload 9
    //   67: iconst_2
    //   68: anewarray 427	java/lang/String
    //   71: dup
    //   72: iconst_0
    //   73: aload 5
    //   75: aastore
    //   76: dup
    //   77: iconst_1
    //   78: ldc_w 429
    //   81: aastore
    //   82: ldc_w 431
    //   85: iconst_2
    //   86: anewarray 427	java/lang/String
    //   89: dup
    //   90: iconst_0
    //   91: aload 10
    //   93: aastore
    //   94: dup
    //   95: iconst_1
    //   96: aload 11
    //   98: aastore
    //   99: invokevirtual 435	com/android/launcher3/IconCache$IconDB:query	([Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   102: astore 5
    //   104: aload 5
    //   106: invokeinterface 441 1 0
    //   111: ifeq +140 -> 251
    //   114: aload 7
    //   116: astore 4
    //   118: iload_3
    //   119: ifeq +9 -> 128
    //   122: aload_0
    //   123: getfield 156	com/android/launcher3/IconCache:mLowResOptions	Landroid/graphics/BitmapFactory$Options;
    //   126: astore 4
    //   128: aload_2
    //   129: aload 5
    //   131: aload 4
    //   133: invokestatic 445	com/android/launcher3/IconCache:loadIconNoResize$48051843	(Landroid/database/Cursor;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   136: putfield 258	com/android/launcher3/IconCache$CacheEntry:icon	Landroid/graphics/Bitmap;
    //   139: aload_2
    //   140: iload_3
    //   141: putfield 273	com/android/launcher3/IconCache$CacheEntry:isLowResIcon	Z
    //   144: aload_2
    //   145: aload 5
    //   147: iconst_1
    //   148: invokeinterface 449 2 0
    //   153: putfield 245	com/android/launcher3/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   156: aload_2
    //   157: getfield 245	com/android/launcher3/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   160: ifnonnull +39 -> 199
    //   163: aload_2
    //   164: ldc_w 451
    //   167: putfield 245	com/android/launcher3/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   170: aload_2
    //   171: ldc_w 451
    //   174: putfield 253	com/android/launcher3/IconCache$CacheEntry:contentDescription	Ljava/lang/CharSequence;
    //   177: aload 5
    //   179: ifnull +10 -> 189
    //   182: aload 5
    //   184: invokeinterface 454 1 0
    //   189: iconst_1
    //   190: ireturn
    //   191: ldc_w 455
    //   194: astore 5
    //   196: goto -168 -> 28
    //   199: aload_2
    //   200: aload_0
    //   201: getfield 97	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   204: aload_2
    //   205: getfield 245	com/android/launcher3/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   208: aload_1
    //   209: getfield 422	com/android/launcher3/util/ComponentKey:user	Landroid/os/UserHandle;
    //   212: invokevirtual 333	com/android/launcher3/compat/UserManagerCompat:getBadgedLabelForUser	(Ljava/lang/CharSequence;Landroid/os/UserHandle;)Ljava/lang/CharSequence;
    //   215: putfield 253	com/android/launcher3/IconCache$CacheEntry:contentDescription	Ljava/lang/CharSequence;
    //   218: goto -41 -> 177
    //   221: astore_2
    //   222: aload 5
    //   224: astore_1
    //   225: aload_1
    //   226: astore 4
    //   228: ldc_w 457
    //   231: ldc_w 459
    //   234: aload_2
    //   235: invokestatic 465	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   238: pop
    //   239: aload_1
    //   240: ifnull +9 -> 249
    //   243: aload_1
    //   244: invokeinterface 454 1 0
    //   249: iconst_0
    //   250: ireturn
    //   251: aload 5
    //   253: ifnull -4 -> 249
    //   256: aload 5
    //   258: invokeinterface 454 1 0
    //   263: goto -14 -> 249
    //   266: astore_1
    //   267: aload 4
    //   269: ifnull +10 -> 279
    //   272: aload 4
    //   274: invokeinterface 454 1 0
    //   279: aload_1
    //   280: athrow
    //   281: astore_1
    //   282: aload 5
    //   284: astore 4
    //   286: goto -19 -> 267
    //   289: astore_2
    //   290: aload 6
    //   292: astore_1
    //   293: goto -68 -> 225
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	296	0	this	IconCache
    //   0	296	1	paramComponentKey	ComponentKey
    //   0	296	2	paramCacheEntry	CacheEntry
    //   0	296	3	paramBoolean	boolean
    //   11	274	4	localObject1	Object
    //   26	257	5	localObject2	Object
    //   1	290	6	localObject3	Object
    //   7	108	7	localObject4	Object
    //   4	58	8	localObject5	Object
    //   17	49	9	localIconDB	IconDB
    //   39	53	10	str1	String
    //   59	38	11	str2	String
    // Exception table:
    //   from	to	target	type
    //   104	114	221	android/database/sqlite/SQLiteException
    //   122	128	221	android/database/sqlite/SQLiteException
    //   128	177	221	android/database/sqlite/SQLiteException
    //   199	218	221	android/database/sqlite/SQLiteException
    //   13	19	266	finally
    //   32	41	266	finally
    //   45	61	266	finally
    //   65	104	266	finally
    //   228	239	266	finally
    //   104	114	281	finally
    //   122	128	281	finally
    //   128	177	281	finally
    //   199	218	281	finally
    //   13	19	289	android/database/sqlite/SQLiteException
    //   32	41	289	android/database/sqlite/SQLiteException
    //   45	61	289	android/database/sqlite/SQLiteException
    //   65	104	289	android/database/sqlite/SQLiteException
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
  
  private void getTitleAndIcon(ItemInfoWithIcon paramItemInfoWithIcon, Provider<LauncherActivityInfo> paramProvider, boolean paramBoolean1, boolean paramBoolean2)
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
  
  private static Bitmap loadIconNoResize$48051843(Cursor paramCursor, BitmapFactory.Options paramOptions)
  {
    paramCursor = paramCursor.getBlob(0);
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
  
  final void addIconToDBAndMemCache(LauncherActivityInfo paramLauncherActivityInfo, PackageInfo paramPackageInfo, long paramLong, boolean paramBoolean)
  {
    for (;;)
    {
      try
      {
        ComponentKey localComponentKey = new ComponentKey(paramLauncherActivityInfo.getComponentName(), paramLauncherActivityInfo.getUser());
        if (!paramBoolean)
        {
          Object localObject2 = (CacheEntry)this.mCache.get(localComponentKey);
          if ((localObject2 == null) || (((CacheEntry)localObject2).isLowResIcon)) {
            break label217;
          }
          localObject1 = localObject2;
          if (((CacheEntry)localObject2).icon == null) {
            break label217;
          }
          localObject2 = localObject1;
          if (localObject1 == null)
          {
            localObject2 = new CacheEntry();
            ((CacheEntry)localObject2).icon = LauncherIcons.createBadgedIconBitmap(getFullResIcon(paramLauncherActivityInfo, true), paramLauncherActivityInfo.getUser(), this.mContext, paramLauncherActivityInfo.getApplicationInfo().targetSdkVersion);
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
      continue;
      label217:
      localObject1 = null;
    }
  }
  
  public final void cachePackageInstallInfo(String paramString, UserHandle paramUserHandle, Bitmap paramBitmap, CharSequence paramCharSequence)
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
  
  public final void clear()
  {
    try
    {
      IconDB localIconDB = this.mIconDb;
      SQLiteCacheHelper.MySQLiteOpenHelper.access$000(localIconDB.mOpenHelper, localIconDB.mOpenHelper.getWritableDatabase());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public final Bitmap getDefaultIcon(UserHandle paramUserHandle)
  {
    try
    {
      if (!this.mDefaultIcons.containsKey(paramUserHandle)) {
        this.mDefaultIcons.put(paramUserHandle, LauncherIcons.createBadgedIconBitmap(getFullResDefaultActivityIcon(), paramUserHandle, this.mContext, 26));
      }
      paramUserHandle = (Bitmap)this.mDefaultIcons.get(paramUserHandle);
      return paramUserHandle;
    }
    finally {}
  }
  
  public final Drawable getFullResIcon(ActivityInfo paramActivityInfo)
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
  
  public final Drawable getFullResIcon(LauncherActivityInfo paramLauncherActivityInfo, boolean paramBoolean)
  {
    if (b.b(this.mContext).equals(""))
    {
      String str = String.valueOf(paramLauncherActivityInfo.getLabel()).toLowerCase().replaceAll(" ", "");
      int i = this.mContext.getResources().getIdentifier(str, "drawable", this.mContext.getPackageName());
      if (i != 0) {}
      try
      {
        if (Build.VERSION.SDK_INT >= 21) {
          return this.mContext.getResources().getDrawable(i, this.mContext.getTheme());
        }
        return this.mContext.getResources().getDrawable(i);
      }
      catch (Exception paramLauncherActivityInfo)
      {
        Log.d("Resource Not Found", paramLauncherActivityInfo.getMessage());
        return null;
      }
      paramLauncherActivityInfo = this.mIconProvider.getIcon(paramLauncherActivityInfo, this.mIconDpi, paramBoolean);
      return paramLauncherActivityInfo;
    }
    return this.mIconProvider.getIcon(paramLauncherActivityInfo, this.mIconDpi, paramBoolean);
  }
  
  public final Drawable getFullResIcon(String paramString, int paramInt)
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
  
  public final void getTitleAndIcon(ItemInfoWithIcon paramItemInfoWithIcon, LauncherActivityInfo paramLauncherActivityInfo, boolean paramBoolean)
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
  public final void getTitleAndIcon(ItemInfoWithIcon paramItemInfoWithIcon, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual 229	com/android/launcher3/ItemInfoWithIcon:getTargetComponent	()Landroid/content/ComponentName;
    //   6: ifnonnull +37 -> 43
    //   9: aload_1
    //   10: aload_0
    //   11: aload_1
    //   12: getfield 262	com/android/launcher3/ItemInfoWithIcon:user	Landroid/os/UserHandle;
    //   15: invokevirtual 266	com/android/launcher3/IconCache:getDefaultIcon	(Landroid/os/UserHandle;)Landroid/graphics/Bitmap;
    //   18: putfield 269	com/android/launcher3/ItemInfoWithIcon:iconBitmap	Landroid/graphics/Bitmap;
    //   21: aload_1
    //   22: ldc_w 451
    //   25: putfield 250	com/android/launcher3/ItemInfoWithIcon:title	Ljava/lang/CharSequence;
    //   28: aload_1
    //   29: ldc_w 451
    //   32: putfield 254	com/android/launcher3/ItemInfoWithIcon:contentDescription	Ljava/lang/CharSequence;
    //   35: aload_1
    //   36: iconst_0
    //   37: putfield 276	com/android/launcher3/ItemInfoWithIcon:usingLowResIcon	Z
    //   40: aload_0
    //   41: monitorexit
    //   42: return
    //   43: aload_0
    //   44: aload_1
    //   45: new 10	com/android/launcher3/IconCache$ActivityInfoProvider
    //   48: dup
    //   49: aload_0
    //   50: aload_1
    //   51: invokevirtual 684	com/android/launcher3/ItemInfoWithIcon:getIntent	()Landroid/content/Intent;
    //   54: aload_1
    //   55: getfield 262	com/android/launcher3/ItemInfoWithIcon:user	Landroid/os/UserHandle;
    //   58: invokespecial 687	com/android/launcher3/IconCache$ActivityInfoProvider:<init>	(Lcom/android/launcher3/IconCache;Landroid/content/Intent;Landroid/os/UserHandle;)V
    //   61: iconst_1
    //   62: iload_2
    //   63: invokespecial 679	com/android/launcher3/IconCache:getTitleAndIcon	(Lcom/android/launcher3/ItemInfoWithIcon;Lcom/android/launcher3/util/Provider;ZZ)V
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
  
  public final void getTitleAndIconForApp(PackageItemInfo paramPackageItemInfo, boolean paramBoolean)
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
  
  public final boolean isDefaultIcon(Bitmap paramBitmap, UserHandle paramUserHandle)
  {
    return this.mDefaultIcons.get(paramUserHandle) == paramBitmap;
  }
  
  public final void remove(ComponentName paramComponentName, UserHandle paramUserHandle)
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
  
  public final void removeIconsForPkg(String paramString, UserHandle paramUserHandle)
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
  
  /* Error */
  public final void updateDbIcons(Set<String> paramSet)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 151	com/android/launcher3/IconCache:mWorkerHandler	Landroid/os/Handler;
    //   4: getstatic 62	com/android/launcher3/IconCache:ICON_UPDATE_TOKEN	Ljava/lang/Object;
    //   7: invokevirtual 710	android/os/Handler:removeCallbacksAndMessages	(Ljava/lang/Object;)V
    //   10: aload_0
    //   11: getfield 138	com/android/launcher3/IconCache:mIconProvider	Lcom/android/launcher3/IconProvider;
    //   14: invokevirtual 713	com/android/launcher3/IconProvider:updateSystemStateString	()V
    //   17: aload_0
    //   18: getfield 97	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   21: invokevirtual 717	com/android/launcher3/compat/UserManagerCompat:getUserProfiles	()Ljava/util/List;
    //   24: invokeinterface 720 1 0
    //   29: astore 15
    //   31: aload 15
    //   33: invokeinterface 554 1 0
    //   38: ifeq +42 -> 80
    //   41: aload 15
    //   43: invokeinterface 557 1 0
    //   48: checkcast 370	android/os/UserHandle
    //   51: astore 18
    //   53: aload_0
    //   54: getfield 104	com/android/launcher3/IconCache:mLauncherApps	Lcom/android/launcher3/compat/LauncherAppsCompat;
    //   57: aconst_null
    //   58: aload 18
    //   60: invokevirtual 724	com/android/launcher3/compat/LauncherAppsCompat:getActivityList	(Ljava/lang/String;Landroid/os/UserHandle;)Ljava/util/List;
    //   63: astore 12
    //   65: aload 12
    //   67: ifnull +13 -> 80
    //   70: aload 12
    //   72: invokeinterface 726 1 0
    //   77: ifeq +4 -> 81
    //   80: return
    //   81: invokestatic 368	android/os/Process:myUserHandle	()Landroid/os/UserHandle;
    //   84: aload 18
    //   86: invokevirtual 374	android/os/UserHandle:equals	(Ljava/lang/Object;)Z
    //   89: ifeq +88 -> 177
    //   92: aload_1
    //   93: astore 13
    //   95: aload_0
    //   96: getfield 97	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   99: aload 18
    //   101: invokevirtual 415	com/android/launcher3/compat/UserManagerCompat:getSerialNumberForUser	(Landroid/os/UserHandle;)J
    //   104: lstore 8
    //   106: aload_0
    //   107: getfield 81	com/android/launcher3/IconCache:mContext	Landroid/content/Context;
    //   110: invokevirtual 87	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   113: astore 14
    //   115: new 66	java/util/HashMap
    //   118: dup
    //   119: invokespecial 67	java/util/HashMap:<init>	()V
    //   122: astore 16
    //   124: aload 14
    //   126: sipush 8192
    //   129: invokevirtual 730	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   132: invokeinterface 720 1 0
    //   137: astore 14
    //   139: aload 14
    //   141: invokeinterface 554 1 0
    //   146: ifeq +39 -> 185
    //   149: aload 14
    //   151: invokeinterface 557 1 0
    //   156: checkcast 200	android/content/pm/PackageInfo
    //   159: astore 17
    //   161: aload 16
    //   163: aload 17
    //   165: getfield 731	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   168: aload 17
    //   170: invokevirtual 291	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   173: pop
    //   174: goto -35 -> 139
    //   177: invokestatic 736	java/util/Collections:emptySet	()Ljava/util/Set;
    //   180: astore 13
    //   182: goto -87 -> 95
    //   185: new 66	java/util/HashMap
    //   188: dup
    //   189: invokespecial 67	java/util/HashMap:<init>	()V
    //   192: astore 17
    //   194: aload 12
    //   196: invokeinterface 720 1 0
    //   201: astore 12
    //   203: aload 12
    //   205: invokeinterface 554 1 0
    //   210: ifeq +31 -> 241
    //   213: aload 12
    //   215: invokeinterface 557 1 0
    //   220: checkcast 302	android/content/pm/LauncherActivityInfo
    //   223: astore 14
    //   225: aload 17
    //   227: aload 14
    //   229: invokevirtual 570	android/content/pm/LauncherActivityInfo:getComponentName	()Landroid/content/ComponentName;
    //   232: aload 14
    //   234: invokevirtual 291	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   237: pop
    //   238: goto -35 -> 203
    //   241: new 538	java/util/HashSet
    //   244: dup
    //   245: invokespecial 539	java/util/HashSet:<init>	()V
    //   248: astore 20
    //   250: new 738	java/util/Stack
    //   253: dup
    //   254: invokespecial 739	java/util/Stack:<init>	()V
    //   257: astore 19
    //   259: aconst_null
    //   260: astore 12
    //   262: aload_0
    //   263: getfield 127	com/android/launcher3/IconCache:mIconDb	Lcom/android/launcher3/IconCache$IconDB;
    //   266: astore 14
    //   268: lload 8
    //   270: invokestatic 425	java/lang/Long:toString	(J)Ljava/lang/String;
    //   273: astore 21
    //   275: aload 14
    //   277: iconst_5
    //   278: anewarray 427	java/lang/String
    //   281: dup
    //   282: iconst_0
    //   283: ldc_w 741
    //   286: aastore
    //   287: dup
    //   288: iconst_1
    //   289: ldc -83
    //   291: aastore
    //   292: dup
    //   293: iconst_2
    //   294: ldc -58
    //   296: aastore
    //   297: dup
    //   298: iconst_3
    //   299: ldc -50
    //   301: aastore
    //   302: dup
    //   303: iconst_4
    //   304: ldc_w 530
    //   307: aastore
    //   308: ldc_w 743
    //   311: iconst_1
    //   312: anewarray 427	java/lang/String
    //   315: dup
    //   316: iconst_0
    //   317: aload 21
    //   319: aastore
    //   320: invokevirtual 435	com/android/launcher3/IconCache$IconDB:query	([Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   323: astore 14
    //   325: aload 14
    //   327: astore 12
    //   329: aload 12
    //   331: ldc -83
    //   333: invokeinterface 747 2 0
    //   338: istore_2
    //   339: aload 12
    //   341: ldc -58
    //   343: invokeinterface 747 2 0
    //   348: istore_3
    //   349: aload 12
    //   351: ldc -50
    //   353: invokeinterface 747 2 0
    //   358: istore 4
    //   360: aload 12
    //   362: ldc_w 741
    //   365: invokeinterface 747 2 0
    //   370: istore 5
    //   372: aload 12
    //   374: ldc_w 530
    //   377: invokeinterface 747 2 0
    //   382: istore 6
    //   384: aload 12
    //   386: invokeinterface 441 1 0
    //   391: ifeq +344 -> 735
    //   394: aload 12
    //   396: iload_2
    //   397: invokeinterface 449 2 0
    //   402: invokestatic 751	android/content/ComponentName:unflattenFromString	(Ljava/lang/String;)Landroid/content/ComponentName;
    //   405: astore 14
    //   407: aload 16
    //   409: aload 14
    //   411: invokevirtual 336	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   414: invokevirtual 287	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   417: checkcast 200	android/content/pm/PackageInfo
    //   420: astore 21
    //   422: aload 21
    //   424: ifnonnull +155 -> 579
    //   427: aload 13
    //   429: aload 14
    //   431: invokevirtual 336	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   434: invokeinterface 754 2 0
    //   439: ifne -55 -> 384
    //   442: aload_0
    //   443: aload 14
    //   445: aload 18
    //   447: invokevirtual 756	com/android/launcher3/IconCache:remove	(Landroid/content/ComponentName;Landroid/os/UserHandle;)V
    //   450: aload 20
    //   452: aload 12
    //   454: iload 5
    //   456: invokeinterface 760 2 0
    //   461: invokestatic 214	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   464: invokevirtual 561	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   467: pop
    //   468: goto -84 -> 384
    //   471: astore 13
    //   473: ldc_w 457
    //   476: ldc_w 459
    //   479: aload 13
    //   481: invokestatic 465	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   484: pop
    //   485: aload 12
    //   487: ifnull +10 -> 497
    //   490: aload 12
    //   492: invokeinterface 454 1 0
    //   497: aload 20
    //   499: invokevirtual 761	java/util/HashSet:isEmpty	()Z
    //   502: ifne +19 -> 521
    //   505: aload_0
    //   506: getfield 127	com/android/launcher3/IconCache:mIconDb	Lcom/android/launcher3/IconCache$IconDB;
    //   509: ldc_w 741
    //   512: aload 20
    //   514: invokestatic 765	com/android/launcher3/Utilities:createDbSelectionQuery	(Ljava/lang/String;Ljava/lang/Iterable;)Ljava/lang/String;
    //   517: aconst_null
    //   518: invokevirtual 704	com/android/launcher3/IconCache$IconDB:delete	(Ljava/lang/String;[Ljava/lang/String;)V
    //   521: aload 17
    //   523: invokevirtual 766	java/util/HashMap:isEmpty	()Z
    //   526: ifeq +11 -> 537
    //   529: aload 19
    //   531: invokevirtual 767	java/util/Stack:isEmpty	()Z
    //   534: ifne -503 -> 31
    //   537: new 738	java/util/Stack
    //   540: dup
    //   541: invokespecial 739	java/util/Stack:<init>	()V
    //   544: astore 12
    //   546: aload 12
    //   548: aload 17
    //   550: invokevirtual 771	java/util/HashMap:values	()Ljava/util/Collection;
    //   553: invokevirtual 775	java/util/Stack:addAll	(Ljava/util/Collection;)Z
    //   556: pop
    //   557: new 25	com/android/launcher3/IconCache$SerializedIconUpdateTask
    //   560: dup
    //   561: aload_0
    //   562: lload 8
    //   564: aload 16
    //   566: aload 12
    //   568: aload 19
    //   570: invokespecial 778	com/android/launcher3/IconCache$SerializedIconUpdateTask:<init>	(Lcom/android/launcher3/IconCache;JLjava/util/HashMap;Ljava/util/Stack;Ljava/util/Stack;)V
    //   573: invokevirtual 781	com/android/launcher3/IconCache$SerializedIconUpdateTask:scheduleNext	()V
    //   576: goto -545 -> 31
    //   579: aload 21
    //   581: getfield 384	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   584: getfield 784	android/content/pm/ApplicationInfo:flags	I
    //   587: ldc_w 785
    //   590: iand
    //   591: ifne -207 -> 384
    //   594: aload 12
    //   596: iload_3
    //   597: invokeinterface 789 2 0
    //   602: lstore 10
    //   604: aload 12
    //   606: iload 4
    //   608: invokeinterface 760 2 0
    //   613: istore 7
    //   615: aload 17
    //   617: aload 14
    //   619: invokevirtual 565	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   622: checkcast 302	android/content/pm/LauncherActivityInfo
    //   625: astore 22
    //   627: iload 7
    //   629: aload 21
    //   631: getfield 209	android/content/pm/PackageInfo:versionCode	I
    //   634: if_icmpne +41 -> 675
    //   637: lload 10
    //   639: aload 21
    //   641: getfield 204	android/content/pm/PackageInfo:lastUpdateTime	J
    //   644: lcmp
    //   645: ifne +30 -> 675
    //   648: aload 12
    //   650: iload 6
    //   652: invokeinterface 449 2 0
    //   657: aload_0
    //   658: getfield 138	com/android/launcher3/IconCache:mIconProvider	Lcom/android/launcher3/IconProvider;
    //   661: aload 21
    //   663: getfield 731	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   666: invokevirtual 534	com/android/launcher3/IconProvider:getIconSystemState	(Ljava/lang/String;)Ljava/lang/String;
    //   669: invokestatic 792	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   672: ifne -288 -> 384
    //   675: aload 22
    //   677: ifnonnull +47 -> 724
    //   680: aload_0
    //   681: aload 14
    //   683: aload 18
    //   685: invokevirtual 756	com/android/launcher3/IconCache:remove	(Landroid/content/ComponentName;Landroid/os/UserHandle;)V
    //   688: aload 20
    //   690: aload 12
    //   692: iload 5
    //   694: invokeinterface 760 2 0
    //   699: invokestatic 214	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   702: invokevirtual 561	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   705: pop
    //   706: goto -322 -> 384
    //   709: astore_1
    //   710: aload 12
    //   712: ifnull +10 -> 722
    //   715: aload 12
    //   717: invokeinterface 454 1 0
    //   722: aload_1
    //   723: athrow
    //   724: aload 19
    //   726: aload 22
    //   728: invokevirtual 793	java/util/Stack:add	(Ljava/lang/Object;)Z
    //   731: pop
    //   732: goto -348 -> 384
    //   735: aload 12
    //   737: ifnull -240 -> 497
    //   740: aload 12
    //   742: invokeinterface 454 1 0
    //   747: goto -250 -> 497
    //   750: astore_1
    //   751: aconst_null
    //   752: astore 12
    //   754: goto -44 -> 710
    //   757: astore_1
    //   758: goto -48 -> 710
    //   761: astore 13
    //   763: goto -290 -> 473
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	766	0	this	IconCache
    //   0	766	1	paramSet	Set<String>
    //   338	59	2	i	int
    //   348	249	3	j	int
    //   358	249	4	k	int
    //   370	323	5	m	int
    //   382	269	6	n	int
    //   613	22	7	i1	int
    //   104	459	8	l1	long
    //   602	36	10	l2	long
    //   63	690	12	localObject1	Object
    //   93	335	13	localObject2	Object
    //   471	9	13	localSQLiteException1	android.database.sqlite.SQLiteException
    //   761	1	13	localSQLiteException2	android.database.sqlite.SQLiteException
    //   113	569	14	localObject3	Object
    //   29	13	15	localIterator	Iterator
    //   122	443	16	localHashMap	HashMap
    //   159	457	17	localObject4	Object
    //   51	633	18	localUserHandle	UserHandle
    //   257	468	19	localStack	Stack
    //   248	441	20	localHashSet	HashSet
    //   273	389	21	localObject5	Object
    //   625	102	22	localLauncherActivityInfo	LauncherActivityInfo
    // Exception table:
    //   from	to	target	type
    //   329	384	471	android/database/sqlite/SQLiteException
    //   384	422	471	android/database/sqlite/SQLiteException
    //   427	468	471	android/database/sqlite/SQLiteException
    //   579	675	471	android/database/sqlite/SQLiteException
    //   680	706	471	android/database/sqlite/SQLiteException
    //   724	732	471	android/database/sqlite/SQLiteException
    //   329	384	709	finally
    //   384	422	709	finally
    //   427	468	709	finally
    //   579	675	709	finally
    //   680	706	709	finally
    //   724	732	709	finally
    //   262	325	750	finally
    //   473	485	757	finally
    //   262	325	761	android/database/sqlite/SQLiteException
  }
  
  public final IconLoadRequest updateIconInBackground(final ItemInfoUpdateReceiver paramItemInfoUpdateReceiver, final ItemInfoWithIcon paramItemInfoWithIcon)
  {
    paramItemInfoUpdateReceiver = new Runnable()
    {
      public final void run()
      {
        if (((paramItemInfoWithIcon instanceof AppInfo)) || ((paramItemInfoWithIcon instanceof ShortcutInfo))) {
          IconCache.this.getTitleAndIcon(paramItemInfoWithIcon, false);
        }
        for (;;)
        {
          IconCache.this.mMainThreadExecutor.execute(new Runnable()
          {
            public final void run()
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
  public final void updateIconsForPkg(String paramString, UserHandle paramUserHandle)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_2
    //   5: invokevirtual 809	com/android/launcher3/IconCache:removeIconsForPkg	(Ljava/lang/String;Landroid/os/UserHandle;)V
    //   8: aload_0
    //   9: getfield 89	com/android/launcher3/IconCache:mPackageManager	Landroid/content/pm/PackageManager;
    //   12: aload_1
    //   13: sipush 8192
    //   16: invokevirtual 380	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   19: astore 5
    //   21: aload_0
    //   22: getfield 97	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   25: aload_2
    //   26: invokevirtual 415	com/android/launcher3/compat/UserManagerCompat:getSerialNumberForUser	(Landroid/os/UserHandle;)J
    //   29: lstore_3
    //   30: aload_0
    //   31: getfield 104	com/android/launcher3/IconCache:mLauncherApps	Lcom/android/launcher3/compat/LauncherAppsCompat;
    //   34: aload_1
    //   35: aload_2
    //   36: invokevirtual 724	com/android/launcher3/compat/LauncherAppsCompat:getActivityList	(Ljava/lang/String;Landroid/os/UserHandle;)Ljava/util/List;
    //   39: invokeinterface 720 1 0
    //   44: astore_1
    //   45: aload_1
    //   46: invokeinterface 554 1 0
    //   51: ifeq +35 -> 86
    //   54: aload_0
    //   55: aload_1
    //   56: invokeinterface 557 1 0
    //   61: checkcast 302	android/content/pm/LauncherActivityInfo
    //   64: aload 5
    //   66: lload_3
    //   67: iconst_0
    //   68: invokevirtual 811	com/android/launcher3/IconCache:addIconToDBAndMemCache	(Landroid/content/pm/LauncherActivityInfo;Landroid/content/pm/PackageInfo;JZ)V
    //   71: goto -26 -> 45
    //   74: astore_1
    //   75: ldc_w 457
    //   78: ldc_w 813
    //   81: aload_1
    //   82: invokestatic 465	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   85: pop
    //   86: aload_0
    //   87: monitorexit
    //   88: return
    //   89: astore_1
    //   90: aload_0
    //   91: monitorexit
    //   92: aload_1
    //   93: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	94	0	this	IconCache
    //   0	94	1	paramString	String
    //   0	94	2	paramUserHandle	UserHandle
    //   29	38	3	l	long
    //   19	46	5	localPackageInfo	PackageInfo
    // Exception table:
    //   from	to	target	type
    //   8	45	74	android/content/pm/PackageManager$NameNotFoundException
    //   45	71	74	android/content/pm/PackageManager$NameNotFoundException
    //   2	8	89	finally
    //   8	45	89	finally
    //   45	71	89	finally
    //   75	86	89	finally
  }
  
  public final void updateTitleAndIcon(AppInfo paramAppInfo)
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
  
  private final class ActivityInfoProvider
    extends Provider<LauncherActivityInfo>
  {
    private final Intent mIntent;
    private final UserHandle mUser;
    
    public ActivityInfoProvider(Intent paramIntent, UserHandle paramUserHandle)
    {
      this.mIntent = paramIntent;
      this.mUser = paramUserHandle;
    }
  }
  
  public static final class CacheEntry
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
    private static final int RELEASE_VERSION;
    
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
    
    protected final void onCreateTable(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS icons (componentName TEXT NOT NULL, profileId INTEGER NOT NULL, lastUpdated INTEGER NOT NULL DEFAULT 0, version INTEGER NOT NULL DEFAULT 0, icon BLOB, icon_low_res BLOB, label TEXT, system_state TEXT, PRIMARY KEY (componentName, profileId) );");
    }
  }
  
  public static final class IconLoadRequest
  {
    final Handler mHandler;
    final Runnable mRunnable;
    
    IconLoadRequest(Runnable paramRunnable, Handler paramHandler)
    {
      this.mRunnable = paramRunnable;
      this.mHandler = paramHandler;
    }
  }
  
  public static abstract interface ItemInfoUpdateReceiver
  {
    public abstract void reapplyItemInfo(ItemInfoWithIcon paramItemInfoWithIcon);
  }
  
  final class SerializedIconUpdateTask
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
    
    public final void run()
    {
      Object localObject1;
      Object localObject2;
      if (!this.mAppsToUpdate.isEmpty())
      {
        localObject1 = (LauncherActivityInfo)this.mAppsToUpdate.pop();
        localObject2 = ((LauncherActivityInfo)localObject1).getComponentName().getPackageName();
        PackageInfo localPackageInfo = (PackageInfo)this.mPkgInfoMap.get(localObject2);
        IconCache.this.addIconToDBAndMemCache((LauncherActivityInfo)localObject1, localPackageInfo, this.mUserSerial, true);
        this.mUpdatedPackages.add(localObject2);
        if ((this.mAppsToUpdate.isEmpty()) && (!this.mUpdatedPackages.isEmpty()))
        {
          localObject1 = LauncherAppState.getInstance(IconCache.this.mContext).mModel;
          localObject2 = this.mUpdatedPackages;
          ((LauncherModel)localObject1).enqueueModelUpdateTask(new CacheDataUpdatedTask(1, IconCache.this.mUserManager.getUserForSerialNumber(this.mUserSerial), (HashSet)localObject2));
        }
        scheduleNext();
      }
      do
      {
        do
        {
          return;
        } while (this.mAppsToAdd.isEmpty());
        localObject1 = (LauncherActivityInfo)this.mAppsToAdd.pop();
        localObject2 = (PackageInfo)this.mPkgInfoMap.get(((LauncherActivityInfo)localObject1).getComponentName().getPackageName());
        if (localObject2 != null) {
          IconCache.this.addIconToDBAndMemCache((LauncherActivityInfo)localObject1, (PackageInfo)localObject2, this.mUserSerial, false);
        }
      } while (this.mAppsToAdd.isEmpty());
      scheduleNext();
    }
    
    public final void scheduleNext()
    {
      IconCache.this.mWorkerHandler.postAtTime(this, IconCache.ICON_UPDATE_TOKEN, SystemClock.uptimeMillis() + 1L);
    }
  }
}
