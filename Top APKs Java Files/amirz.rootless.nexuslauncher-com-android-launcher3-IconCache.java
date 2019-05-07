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
import android.os.Handler;
import android.os.Process;
import android.os.SystemClock;
import android.os.UserHandle;
import android.text.TextUtils;
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
    this.mIconProvider = ((IconProvider)Utilities.getOverrideObject(IconProvider.class, paramContext, 2131820624));
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
    Bitmap localBitmap;
    if (paramCacheEntry.icon == null) {
      localBitmap = getDefaultIcon(paramItemInfoWithIcon.user);
    } else {
      localBitmap = paramCacheEntry.icon;
    }
    paramItemInfoWithIcon.iconBitmap = localBitmap;
    paramItemInfoWithIcon.usingLowResIcon = paramCacheEntry.isLowResIcon;
  }
  
  private CacheEntry cacheLocked(ComponentName paramComponentName, Provider<LauncherActivityInfo> paramProvider, UserHandle paramUserHandle, boolean paramBoolean1, boolean paramBoolean2)
  {
    ComponentKey localComponentKey = new ComponentKey(paramComponentName, paramUserHandle);
    Object localObject2 = (CacheEntry)this.mCache.get(localComponentKey);
    Object localObject1;
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
      CacheEntry localCacheEntry = new CacheEntry();
      this.mCache.put(localComponentKey, localCacheEntry);
      localObject1 = null;
      paramBoolean2 = getEntryFromDB(localComponentKey, localCacheEntry, paramBoolean2);
      int j = 1;
      if (!paramBoolean2)
      {
        localObject1 = (LauncherActivityInfo)paramProvider.get();
        if (localObject1 != null) {}
        for (paramComponentName = LauncherIcons.createBadgedIconBitmap(getFullResIcon((LauncherActivityInfo)localObject1, true), ((LauncherActivityInfo)localObject1).getUser(), this.mContext, ((LauncherActivityInfo)paramProvider.get()).getApplicationInfo().targetSdkVersion);; paramComponentName = getDefaultIcon(paramUserHandle))
        {
          localCacheEntry.icon = paramComponentName;
          i = j;
          paramComponentName = (ComponentName)localObject1;
          break;
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
          i = j;
          paramComponentName = (ComponentName)localObject1;
          if (localCacheEntry.icon != null) {
            break;
          }
        }
      }
      int i = 0;
      paramComponentName = (ComponentName)localObject1;
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
    }
    return localObject1;
  }
  
  private static Bitmap generateLowResIcon(Bitmap paramBitmap)
  {
    return Bitmap.createScaledBitmap(paramBitmap, paramBitmap.getWidth() / 5, paramBitmap.getHeight() / 5, true);
  }
  
  private CacheEntry getEntryForPackageLocked(String paramString, UserHandle paramUserHandle, boolean paramBoolean)
  {
    ComponentKey localComponentKey = getPackageKey(paramString, paramUserHandle);
    CacheEntry localCacheEntry = (CacheEntry)this.mCache.get(localComponentKey);
    int j;
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
      j = 1;
      i = j;
      if (getEntryFromDB(localComponentKey, localCacheEntry, paramBoolean)) {}
    }
    try
    {
      if (!Process.myUserHandle().equals(paramUserHandle)) {
        break label292;
      }
      i = 0;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;)
      {
        PackageInfo localPackageInfo;
        Bitmap localBitmap1;
        Bitmap localBitmap2;
        continue;
        i = 8192;
        continue;
        localObject = localBitmap1;
      }
    }
    localPackageInfo = this.mPackageManager.getPackageInfo(paramString, i);
    localObject = localPackageInfo.applicationInfo;
    if (localObject == null) {
      throw new PackageManager.NameNotFoundException("ApplicationInfo is null");
    }
    localBitmap1 = LauncherIcons.createBadgedIconBitmap(((ApplicationInfo)localObject).loadIcon(this.mPackageManager), paramUserHandle, this.mContext, ((ApplicationInfo)localObject).targetSdkVersion);
    localBitmap2 = generateLowResIcon(localBitmap1);
    localCacheEntry.title = ((ApplicationInfo)localObject).loadLabel(this.mPackageManager);
    localCacheEntry.contentDescription = this.mUserManager.getBadgedLabelForUser(localCacheEntry.title, paramUserHandle);
    if (paramBoolean)
    {
      localObject = localBitmap2;
      localCacheEntry.icon = ((Bitmap)localObject);
      localCacheEntry.isLowResIcon = paramBoolean;
      addIconToDB(newContentValues(localBitmap1, localBitmap2, localCacheEntry.title.toString(), paramString), localComponentKey.componentName, localPackageInfo, this.mUserManager.getSerialNumberForUser(paramUserHandle));
      i = j;
      break label260;
      i = 0;
      label260:
      localObject = localCacheEntry;
      if (i != 0)
      {
        this.mCache.put(localComponentKey, localCacheEntry);
        localObject = localCacheEntry;
      }
      return localObject;
    }
  }
  
  /* Error */
  private boolean getEntryFromDB(ComponentKey paramComponentKey, CacheEntry paramCacheEntry, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 8
    //   3: aconst_null
    //   4: astore 6
    //   6: aconst_null
    //   7: astore 7
    //   9: aload 8
    //   11: astore 4
    //   13: aload_0
    //   14: getfield 127	com/android/launcher3/IconCache:mIconDb	Lcom/android/launcher3/IconCache$IconDB;
    //   17: astore 9
    //   19: iload_3
    //   20: ifeq +275 -> 295
    //   23: ldc_w 411
    //   26: astore 5
    //   28: goto +3 -> 31
    //   31: aload 8
    //   33: astore 4
    //   35: aload_1
    //   36: getfield 401	com/android/launcher3/util/ComponentKey:componentName	Landroid/content/ComponentName;
    //   39: invokevirtual 179	android/content/ComponentName:flattenToString	()Ljava/lang/String;
    //   42: astore 10
    //   44: aload 8
    //   46: astore 4
    //   48: aload_0
    //   49: getfield 97	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   52: aload_1
    //   53: getfield 412	com/android/launcher3/util/ComponentKey:user	Landroid/os/UserHandle;
    //   56: invokevirtual 405	com/android/launcher3/compat/UserManagerCompat:getSerialNumberForUser	(Landroid/os/UserHandle;)J
    //   59: invokestatic 415	java/lang/Long:toString	(J)Ljava/lang/String;
    //   62: astore 11
    //   64: aload 8
    //   66: astore 4
    //   68: aload 9
    //   70: iconst_2
    //   71: anewarray 417	java/lang/String
    //   74: dup
    //   75: iconst_0
    //   76: aload 5
    //   78: aastore
    //   79: dup
    //   80: iconst_1
    //   81: ldc_w 419
    //   84: aastore
    //   85: ldc_w 421
    //   88: iconst_2
    //   89: anewarray 417	java/lang/String
    //   92: dup
    //   93: iconst_0
    //   94: aload 10
    //   96: aastore
    //   97: dup
    //   98: iconst_1
    //   99: aload 11
    //   101: aastore
    //   102: invokevirtual 425	com/android/launcher3/IconCache$IconDB:query	([Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   105: astore 5
    //   107: aload 5
    //   109: invokeinterface 431 1 0
    //   114: ifeq +104 -> 218
    //   117: aload 7
    //   119: astore 4
    //   121: iload_3
    //   122: ifeq +9 -> 131
    //   125: aload_0
    //   126: getfield 156	com/android/launcher3/IconCache:mLowResOptions	Landroid/graphics/BitmapFactory$Options;
    //   129: astore 4
    //   131: aload_2
    //   132: aload 5
    //   134: aload 4
    //   136: invokestatic 435	com/android/launcher3/IconCache:loadIconNoResize$48051843	(Landroid/database/Cursor;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   139: putfield 242	com/android/launcher3/IconCache$CacheEntry:icon	Landroid/graphics/Bitmap;
    //   142: aload_2
    //   143: iload_3
    //   144: putfield 257	com/android/launcher3/IconCache$CacheEntry:isLowResIcon	Z
    //   147: aload_2
    //   148: aload 5
    //   150: iconst_1
    //   151: invokeinterface 439 2 0
    //   156: putfield 227	com/android/launcher3/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   159: aload_2
    //   160: getfield 227	com/android/launcher3/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   163: ifnonnull +22 -> 185
    //   166: aload_2
    //   167: ldc_w 441
    //   170: putfield 227	com/android/launcher3/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   173: ldc_w 441
    //   176: astore_1
    //   177: aload_2
    //   178: aload_1
    //   179: putfield 237	com/android/launcher3/IconCache$CacheEntry:contentDescription	Ljava/lang/CharSequence;
    //   182: goto +22 -> 204
    //   185: aload_0
    //   186: getfield 97	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   189: aload_2
    //   190: getfield 227	com/android/launcher3/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   193: aload_1
    //   194: getfield 412	com/android/launcher3/util/ComponentKey:user	Landroid/os/UserHandle;
    //   197: invokevirtual 330	com/android/launcher3/compat/UserManagerCompat:getBadgedLabelForUser	(Ljava/lang/CharSequence;Landroid/os/UserHandle;)Ljava/lang/CharSequence;
    //   200: astore_1
    //   201: goto -24 -> 177
    //   204: aload 5
    //   206: ifnull +10 -> 216
    //   209: aload 5
    //   211: invokeinterface 444 1 0
    //   216: iconst_1
    //   217: ireturn
    //   218: aload 5
    //   220: ifnull +59 -> 279
    //   223: aload 5
    //   225: invokeinterface 444 1 0
    //   230: iconst_0
    //   231: ireturn
    //   232: astore_1
    //   233: aload 5
    //   235: astore 4
    //   237: goto +44 -> 281
    //   240: astore_2
    //   241: aload 5
    //   243: astore_1
    //   244: goto +11 -> 255
    //   247: astore_1
    //   248: goto +33 -> 281
    //   251: astore_2
    //   252: aload 6
    //   254: astore_1
    //   255: aload_1
    //   256: astore 4
    //   258: ldc_w 446
    //   261: ldc_w 448
    //   264: aload_2
    //   265: invokestatic 454	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   268: pop
    //   269: aload_1
    //   270: ifnull +9 -> 279
    //   273: aload_1
    //   274: invokeinterface 444 1 0
    //   279: iconst_0
    //   280: ireturn
    //   281: aload 4
    //   283: ifnull +10 -> 293
    //   286: aload 4
    //   288: invokeinterface 444 1 0
    //   293: aload_1
    //   294: athrow
    //   295: ldc_w 455
    //   298: astore 5
    //   300: goto -269 -> 31
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	303	0	this	IconCache
    //   0	303	1	paramComponentKey	ComponentKey
    //   0	303	2	paramCacheEntry	CacheEntry
    //   0	303	3	paramBoolean	boolean
    //   11	276	4	localObject1	Object
    //   26	273	5	localObject2	Object
    //   4	249	6	localObject3	Object
    //   7	111	7	localObject4	Object
    //   1	64	8	localObject5	Object
    //   17	52	9	localIconDB	IconDB
    //   42	53	10	str1	String
    //   62	38	11	str2	String
    // Exception table:
    //   from	to	target	type
    //   107	117	232	finally
    //   125	131	232	finally
    //   131	173	232	finally
    //   177	182	232	finally
    //   185	201	232	finally
    //   107	117	240	android/database/sqlite/SQLiteException
    //   125	131	240	android/database/sqlite/SQLiteException
    //   131	173	240	android/database/sqlite/SQLiteException
    //   177	182	240	android/database/sqlite/SQLiteException
    //   185	201	240	android/database/sqlite/SQLiteException
    //   13	19	247	finally
    //   35	44	247	finally
    //   48	64	247	finally
    //   68	107	247	finally
    //   258	269	247	finally
    //   13	19	251	android/database/sqlite/SQLiteException
    //   35	44	251	android/database/sqlite/SQLiteException
    //   48	64	251	android/database/sqlite/SQLiteException
    //   68	107	251	android/database/sqlite/SQLiteException
  }
  
  private Drawable getFullResDefaultActivityIcon()
  {
    Resources localResources = Resources.getSystem();
    int i;
    if (Utilities.ATLEAST_OREO) {
      i = 17301651;
    } else {
      i = 17629184;
    }
    return getFullResIcon(localResources, i);
  }
  
  private Drawable getFullResIcon(Resources paramResources, int paramInt)
  {
    try
    {
      paramResources = paramResources.getDrawableForDensity(paramInt, this.mIconDpi);
    }
    catch (Resources.NotFoundException paramResources)
    {
      for (;;) {}
    }
    paramResources = null;
    if (paramResources != null) {
      return paramResources;
    }
    return getFullResDefaultActivityIcon();
  }
  
  private static ComponentKey getPackageKey(String paramString, UserHandle paramUserHandle)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(".");
    return new ComponentKey(new ComponentName(paramString, localStringBuilder.toString()), paramUserHandle);
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
    catch (Exception paramCursor)
    {
      for (;;) {}
    }
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
      CacheEntry localCacheEntry;
      try
      {
        ComponentKey localComponentKey = new ComponentKey(paramLauncherActivityInfo.getComponentName(), paramLauncherActivityInfo.getUser());
        Object localObject2 = null;
        localObject1 = localObject2;
        if (!paramBoolean)
        {
          localCacheEntry = (CacheEntry)this.mCache.get(localComponentKey);
          localObject1 = localObject2;
          if (localCacheEntry != null)
          {
            localObject1 = localObject2;
            if (!localCacheEntry.isLowResIcon)
            {
              if (localCacheEntry.icon != null) {
                break label226;
              }
              localObject1 = localObject2;
            }
          }
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
      finally {}
      label226:
      Object localObject1 = localCacheEntry;
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
      localResources = this.mPackageManager.getResourcesForApplication(paramActivityInfo.applicationInfo);
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Resources localResources;
      int i;
      for (;;) {}
    }
    localResources = null;
    if (localResources != null)
    {
      i = paramActivityInfo.getIconResource();
      if (i != 0) {
        return getFullResIcon(localResources, i);
      }
    }
    return getFullResDefaultActivityIcon();
  }
  
  public final Drawable getFullResIcon(LauncherActivityInfo paramLauncherActivityInfo, boolean paramBoolean)
  {
    return this.mIconProvider.getIcon(paramLauncherActivityInfo, this.mIconDpi, paramBoolean);
  }
  
  public final Drawable getFullResIcon(String paramString, int paramInt)
  {
    try
    {
      paramString = this.mPackageManager.getResourcesForApplication(paramString);
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;) {}
    }
    paramString = null;
    if ((paramString != null) && (paramInt != 0)) {
      return getFullResIcon(paramString, paramInt);
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
  
  public final void getTitleAndIcon(ItemInfoWithIcon paramItemInfoWithIcon, boolean paramBoolean)
  {
    try
    {
      if (paramItemInfoWithIcon.getTargetComponent() == null)
      {
        paramItemInfoWithIcon.iconBitmap = getDefaultIcon(paramItemInfoWithIcon.user);
        paramItemInfoWithIcon.title = "";
        paramItemInfoWithIcon.contentDescription = "";
        paramItemInfoWithIcon.usingLowResIcon = false;
        return;
      }
      getTitleAndIcon(paramItemInfoWithIcon, new ActivityInfoProvider(paramItemInfoWithIcon.getIntent(), paramItemInfoWithIcon.user), true, paramBoolean);
      return;
    }
    finally {}
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
      paramUserHandle = this.mIconDb;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("/%");
      paramUserHandle.delete("componentName LIKE ? AND profileId = ?", new String[] { localStringBuilder.toString(), Long.toString(l) });
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
    //   7: invokevirtual 652	android/os/Handler:removeCallbacksAndMessages	(Ljava/lang/Object;)V
    //   10: aload_0
    //   11: getfield 138	com/android/launcher3/IconCache:mIconProvider	Lcom/android/launcher3/IconProvider;
    //   14: invokevirtual 655	com/android/launcher3/IconProvider:updateSystemStateString	()V
    //   17: aload_0
    //   18: getfield 97	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   21: invokevirtual 659	com/android/launcher3/compat/UserManagerCompat:getUserProfiles	()Ljava/util/List;
    //   24: invokeinterface 662 1 0
    //   29: astore 16
    //   31: aload 16
    //   33: invokeinterface 548 1 0
    //   38: ifeq +873 -> 911
    //   41: aload 16
    //   43: invokeinterface 551 1 0
    //   48: checkcast 360	android/os/UserHandle
    //   51: astore 21
    //   53: aload_0
    //   54: getfield 104	com/android/launcher3/IconCache:mLauncherApps	Lcom/android/launcher3/compat/LauncherAppsCompat;
    //   57: aconst_null
    //   58: aload 21
    //   60: invokevirtual 666	com/android/launcher3/compat/LauncherAppsCompat:getActivityList	(Ljava/lang/String;Landroid/os/UserHandle;)Ljava/util/List;
    //   63: astore 13
    //   65: aload 13
    //   67: ifnull +844 -> 911
    //   70: aload 13
    //   72: invokeinterface 668 1 0
    //   77: ifeq +4 -> 81
    //   80: return
    //   81: invokestatic 358	android/os/Process:myUserHandle	()Landroid/os/UserHandle;
    //   84: aload 21
    //   86: invokevirtual 364	android/os/UserHandle:equals	(Ljava/lang/Object;)Z
    //   89: ifeq +9 -> 98
    //   92: aload_1
    //   93: astore 14
    //   95: goto +8 -> 103
    //   98: invokestatic 673	java/util/Collections:emptySet	()Ljava/util/Set;
    //   101: astore 14
    //   103: aload_0
    //   104: getfield 97	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   107: aload 21
    //   109: invokevirtual 405	com/android/launcher3/compat/UserManagerCompat:getSerialNumberForUser	(Landroid/os/UserHandle;)J
    //   112: lstore 10
    //   114: lload 10
    //   116: lstore 8
    //   118: aload_0
    //   119: getfield 81	com/android/launcher3/IconCache:mContext	Landroid/content/Context;
    //   122: invokevirtual 87	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   125: astore 15
    //   127: new 66	java/util/HashMap
    //   130: dup
    //   131: invokespecial 67	java/util/HashMap:<init>	()V
    //   134: astore 17
    //   136: aload 15
    //   138: sipush 8192
    //   141: invokevirtual 677	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   144: invokeinterface 662 1 0
    //   149: astore 15
    //   151: aload 15
    //   153: invokeinterface 548 1 0
    //   158: ifeq +31 -> 189
    //   161: aload 15
    //   163: invokeinterface 551 1 0
    //   168: checkcast 200	android/content/pm/PackageInfo
    //   171: astore 18
    //   173: aload 17
    //   175: aload 18
    //   177: getfield 678	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   180: aload 18
    //   182: invokevirtual 275	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   185: pop
    //   186: goto -35 -> 151
    //   189: new 66	java/util/HashMap
    //   192: dup
    //   193: invokespecial 67	java/util/HashMap:<init>	()V
    //   196: astore 18
    //   198: aload 13
    //   200: invokeinterface 662 1 0
    //   205: astore 13
    //   207: aload 13
    //   209: invokeinterface 548 1 0
    //   214: ifeq +31 -> 245
    //   217: aload 13
    //   219: invokeinterface 551 1 0
    //   224: checkcast 286	android/content/pm/LauncherActivityInfo
    //   227: astore 15
    //   229: aload 18
    //   231: aload 15
    //   233: invokevirtual 564	android/content/pm/LauncherActivityInfo:getComponentName	()Landroid/content/ComponentName;
    //   236: aload 15
    //   238: invokevirtual 275	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   241: pop
    //   242: goto -35 -> 207
    //   245: new 532	java/util/HashSet
    //   248: dup
    //   249: invokespecial 533	java/util/HashSet:<init>	()V
    //   252: astore 20
    //   254: new 680	java/util/Stack
    //   257: dup
    //   258: invokespecial 681	java/util/Stack:<init>	()V
    //   261: astore 19
    //   263: aload_0
    //   264: getfield 127	com/android/launcher3/IconCache:mIconDb	Lcom/android/launcher3/IconCache$IconDB;
    //   267: astore 13
    //   269: lload 8
    //   271: invokestatic 415	java/lang/Long:toString	(J)Ljava/lang/String;
    //   274: astore 15
    //   276: aload 13
    //   278: iconst_5
    //   279: anewarray 417	java/lang/String
    //   282: dup
    //   283: iconst_0
    //   284: ldc_w 683
    //   287: aastore
    //   288: dup
    //   289: iconst_1
    //   290: ldc -83
    //   292: aastore
    //   293: dup
    //   294: iconst_2
    //   295: ldc -58
    //   297: aastore
    //   298: dup
    //   299: iconst_3
    //   300: ldc -50
    //   302: aastore
    //   303: dup
    //   304: iconst_4
    //   305: ldc_w 524
    //   308: aastore
    //   309: ldc_w 685
    //   312: iconst_1
    //   313: anewarray 417	java/lang/String
    //   316: dup
    //   317: iconst_0
    //   318: aload 15
    //   320: aastore
    //   321: invokevirtual 425	com/android/launcher3/IconCache$IconDB:query	([Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   324: astore 13
    //   326: lload 8
    //   328: lstore 10
    //   330: aload 13
    //   332: astore 15
    //   334: aload 13
    //   336: ldc -83
    //   338: invokeinterface 689 2 0
    //   343: istore 4
    //   345: lload 8
    //   347: lstore 10
    //   349: aload 13
    //   351: astore 15
    //   353: aload 13
    //   355: ldc -58
    //   357: invokeinterface 689 2 0
    //   362: istore_3
    //   363: lload 8
    //   365: lstore 10
    //   367: aload 13
    //   369: astore 15
    //   371: aload 13
    //   373: ldc -50
    //   375: invokeinterface 689 2 0
    //   380: istore_2
    //   381: lload 8
    //   383: lstore 10
    //   385: aload 13
    //   387: astore 15
    //   389: aload 13
    //   391: ldc_w 683
    //   394: invokeinterface 689 2 0
    //   399: istore 5
    //   401: lload 8
    //   403: lstore 10
    //   405: aload 13
    //   407: astore 15
    //   409: aload 13
    //   411: ldc_w 524
    //   414: invokeinterface 689 2 0
    //   419: istore 6
    //   421: lload 8
    //   423: lstore 10
    //   425: aload 13
    //   427: astore 15
    //   429: aload 13
    //   431: invokeinterface 431 1 0
    //   436: istore 12
    //   438: iload 12
    //   440: ifeq +292 -> 732
    //   443: aload 13
    //   445: astore 15
    //   447: aload 13
    //   449: iload 4
    //   451: invokeinterface 439 2 0
    //   456: invokestatic 693	android/content/ComponentName:unflattenFromString	(Ljava/lang/String;)Landroid/content/ComponentName;
    //   459: astore 22
    //   461: aload 13
    //   463: astore 15
    //   465: aload 17
    //   467: aload 22
    //   469: invokevirtual 312	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   472: invokevirtual 271	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   475: checkcast 200	android/content/pm/PackageInfo
    //   478: astore 23
    //   480: aload 23
    //   482: ifnonnull +59 -> 541
    //   485: aload 13
    //   487: astore 15
    //   489: aload 14
    //   491: aload 22
    //   493: invokevirtual 312	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   496: invokeinterface 696 2 0
    //   501: ifne +411 -> 912
    //   504: aload 13
    //   506: astore 15
    //   508: aload_0
    //   509: aload 22
    //   511: aload 21
    //   513: invokevirtual 698	com/android/launcher3/IconCache:remove	(Landroid/content/ComponentName;Landroid/os/UserHandle;)V
    //   516: aload 13
    //   518: astore 15
    //   520: aload 20
    //   522: aload 13
    //   524: iload 5
    //   526: invokeinterface 702 2 0
    //   531: invokestatic 214	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   534: invokevirtual 555	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   537: pop
    //   538: goto +374 -> 912
    //   541: aload 13
    //   543: astore 15
    //   545: aload 23
    //   547: getfield 374	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   550: getfield 705	android/content/pm/ApplicationInfo:flags	I
    //   553: ldc_w 706
    //   556: iand
    //   557: ifne +167 -> 724
    //   560: aload 13
    //   562: astore 15
    //   564: aload 13
    //   566: iload_3
    //   567: invokeinterface 710 2 0
    //   572: lstore 10
    //   574: aload 13
    //   576: astore 15
    //   578: aload 13
    //   580: iload_2
    //   581: invokeinterface 702 2 0
    //   586: istore 7
    //   588: aload 13
    //   590: astore 15
    //   592: aload 18
    //   594: aload 22
    //   596: invokevirtual 559	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   599: checkcast 286	android/content/pm/LauncherActivityInfo
    //   602: astore 24
    //   604: aload 13
    //   606: astore 15
    //   608: iload 7
    //   610: aload 23
    //   612: getfield 209	android/content/pm/PackageInfo:versionCode	I
    //   615: if_icmpne +300 -> 915
    //   618: aload 13
    //   620: astore 15
    //   622: lload 10
    //   624: aload 23
    //   626: getfield 204	android/content/pm/PackageInfo:lastUpdateTime	J
    //   629: lcmp
    //   630: ifne +37 -> 667
    //   633: aload 13
    //   635: astore 15
    //   637: aload 13
    //   639: iload 6
    //   641: invokeinterface 439 2 0
    //   646: aload_0
    //   647: getfield 138	com/android/launcher3/IconCache:mIconProvider	Lcom/android/launcher3/IconProvider;
    //   650: aload 23
    //   652: getfield 678	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   655: invokevirtual 528	com/android/launcher3/IconProvider:getIconSystemState	(Ljava/lang/String;)Ljava/lang/String;
    //   658: invokestatic 713	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   661: ifne +60 -> 721
    //   664: goto +3 -> 667
    //   667: aload 24
    //   669: ifnonnull +40 -> 709
    //   672: aload 13
    //   674: astore 15
    //   676: aload_0
    //   677: aload 22
    //   679: aload 21
    //   681: invokevirtual 698	com/android/launcher3/IconCache:remove	(Landroid/content/ComponentName;Landroid/os/UserHandle;)V
    //   684: aload 13
    //   686: astore 15
    //   688: aload 20
    //   690: aload 13
    //   692: iload 5
    //   694: invokeinterface 702 2 0
    //   699: invokestatic 214	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   702: invokevirtual 555	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   705: pop
    //   706: goto +15 -> 721
    //   709: aload 13
    //   711: astore 15
    //   713: aload 19
    //   715: aload 24
    //   717: invokevirtual 714	java/util/Stack:add	(Ljava/lang/Object;)Z
    //   720: pop
    //   721: goto -300 -> 421
    //   724: goto -303 -> 421
    //   727: astore 14
    //   729: goto +21 -> 750
    //   732: lload 8
    //   734: lstore 10
    //   736: aload 13
    //   738: ifnull +76 -> 814
    //   741: goto +62 -> 803
    //   744: astore 14
    //   746: lload 10
    //   748: lstore 8
    //   750: goto +28 -> 778
    //   753: astore 13
    //   755: goto +12 -> 767
    //   758: astore_1
    //   759: aconst_null
    //   760: astore 15
    //   762: goto +135 -> 897
    //   765: astore 13
    //   767: lload 10
    //   769: lstore 8
    //   771: aload 13
    //   773: astore 14
    //   775: aconst_null
    //   776: astore 13
    //   778: aload 13
    //   780: astore 15
    //   782: ldc_w 446
    //   785: ldc_w 448
    //   788: aload 14
    //   790: invokestatic 454	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   793: pop
    //   794: lload 8
    //   796: lstore 10
    //   798: aload 13
    //   800: ifnull +14 -> 814
    //   803: aload 13
    //   805: invokeinterface 444 1 0
    //   810: lload 8
    //   812: lstore 10
    //   814: aload 20
    //   816: invokevirtual 715	java/util/HashSet:isEmpty	()Z
    //   819: ifne +19 -> 838
    //   822: aload_0
    //   823: getfield 127	com/android/launcher3/IconCache:mIconDb	Lcom/android/launcher3/IconCache$IconDB;
    //   826: ldc_w 683
    //   829: aload 20
    //   831: invokestatic 719	com/android/launcher3/Utilities:createDbSelectionQuery	(Ljava/lang/String;Ljava/lang/Iterable;)Ljava/lang/String;
    //   834: aconst_null
    //   835: invokevirtual 646	com/android/launcher3/IconCache$IconDB:delete	(Ljava/lang/String;[Ljava/lang/String;)V
    //   838: aload 18
    //   840: invokevirtual 720	java/util/HashMap:isEmpty	()Z
    //   843: ifeq +11 -> 854
    //   846: aload 19
    //   848: invokevirtual 721	java/util/Stack:isEmpty	()Z
    //   851: ifne +42 -> 893
    //   854: new 680	java/util/Stack
    //   857: dup
    //   858: invokespecial 681	java/util/Stack:<init>	()V
    //   861: astore 13
    //   863: aload 13
    //   865: aload 18
    //   867: invokevirtual 725	java/util/HashMap:values	()Ljava/util/Collection;
    //   870: invokevirtual 729	java/util/Stack:addAll	(Ljava/util/Collection;)Z
    //   873: pop
    //   874: new 25	com/android/launcher3/IconCache$SerializedIconUpdateTask
    //   877: dup
    //   878: aload_0
    //   879: lload 10
    //   881: aload 17
    //   883: aload 13
    //   885: aload 19
    //   887: invokespecial 732	com/android/launcher3/IconCache$SerializedIconUpdateTask:<init>	(Lcom/android/launcher3/IconCache;JLjava/util/HashMap;Ljava/util/Stack;Ljava/util/Stack;)V
    //   890: invokevirtual 735	com/android/launcher3/IconCache$SerializedIconUpdateTask:scheduleNext	()V
    //   893: goto -862 -> 31
    //   896: astore_1
    //   897: aload 15
    //   899: ifnull +10 -> 909
    //   902: aload 15
    //   904: invokeinterface 444 1 0
    //   909: aload_1
    //   910: athrow
    //   911: return
    //   912: goto -491 -> 421
    //   915: goto -248 -> 667
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	918	0	this	IconCache
    //   0	918	1	paramSet	Set<String>
    //   380	201	2	i	int
    //   362	205	3	j	int
    //   343	107	4	k	int
    //   399	294	5	m	int
    //   419	221	6	n	int
    //   586	30	7	i1	int
    //   116	695	8	l1	long
    //   112	768	10	l2	long
    //   436	3	12	bool	boolean
    //   63	674	13	localObject1	Object
    //   753	1	13	localSQLiteException1	android.database.sqlite.SQLiteException
    //   765	7	13	localSQLiteException2	android.database.sqlite.SQLiteException
    //   776	108	13	localStack1	Stack
    //   93	397	14	localObject2	Object
    //   727	1	14	localSQLiteException3	android.database.sqlite.SQLiteException
    //   744	1	14	localSQLiteException4	android.database.sqlite.SQLiteException
    //   773	16	14	localSQLiteException5	android.database.sqlite.SQLiteException
    //   125	778	15	localObject3	Object
    //   29	13	16	localIterator	Iterator
    //   134	748	17	localHashMap	HashMap
    //   171	695	18	localObject4	Object
    //   261	625	19	localStack2	Stack
    //   252	578	20	localHashSet	HashSet
    //   51	629	21	localUserHandle	UserHandle
    //   459	219	22	localComponentName	ComponentName
    //   478	173	23	localPackageInfo	PackageInfo
    //   602	114	24	localLauncherActivityInfo	LauncherActivityInfo
    // Exception table:
    //   from	to	target	type
    //   447	461	727	android/database/sqlite/SQLiteException
    //   465	480	727	android/database/sqlite/SQLiteException
    //   489	504	727	android/database/sqlite/SQLiteException
    //   508	516	727	android/database/sqlite/SQLiteException
    //   520	538	727	android/database/sqlite/SQLiteException
    //   545	560	727	android/database/sqlite/SQLiteException
    //   564	574	727	android/database/sqlite/SQLiteException
    //   578	588	727	android/database/sqlite/SQLiteException
    //   592	604	727	android/database/sqlite/SQLiteException
    //   608	618	727	android/database/sqlite/SQLiteException
    //   622	633	727	android/database/sqlite/SQLiteException
    //   637	664	727	android/database/sqlite/SQLiteException
    //   676	684	727	android/database/sqlite/SQLiteException
    //   688	706	727	android/database/sqlite/SQLiteException
    //   713	721	727	android/database/sqlite/SQLiteException
    //   334	345	744	android/database/sqlite/SQLiteException
    //   353	363	744	android/database/sqlite/SQLiteException
    //   371	381	744	android/database/sqlite/SQLiteException
    //   389	401	744	android/database/sqlite/SQLiteException
    //   409	421	744	android/database/sqlite/SQLiteException
    //   429	438	744	android/database/sqlite/SQLiteException
    //   269	326	753	android/database/sqlite/SQLiteException
    //   263	269	758	finally
    //   269	326	758	finally
    //   263	269	765	android/database/sqlite/SQLiteException
    //   334	345	896	finally
    //   353	363	896	finally
    //   371	381	896	finally
    //   389	401	896	finally
    //   409	421	896	finally
    //   429	438	896	finally
    //   447	461	896	finally
    //   465	480	896	finally
    //   489	504	896	finally
    //   508	516	896	finally
    //   520	538	896	finally
    //   545	560	896	finally
    //   564	574	896	finally
    //   578	588	896	finally
    //   592	604	896	finally
    //   608	618	896	finally
    //   622	633	896	finally
    //   637	664	896	finally
    //   676	684	896	finally
    //   688	706	896	finally
    //   713	721	896	finally
    //   782	794	896	finally
  }
  
  public final IconLoadRequest updateIconInBackground(final ItemInfoUpdateReceiver paramItemInfoUpdateReceiver, final ItemInfoWithIcon paramItemInfoWithIcon)
  {
    paramItemInfoUpdateReceiver = new Runnable()
    {
      public final void run()
      {
        if ((!(paramItemInfoWithIcon instanceof AppInfo)) && (!(paramItemInfoWithIcon instanceof ShortcutInfo)))
        {
          if ((paramItemInfoWithIcon instanceof PackageItemInfo)) {
            IconCache.this.getTitleAndIconForApp((PackageItemInfo)paramItemInfoWithIcon, false);
          }
        }
        else {
          IconCache.this.getTitleAndIcon(paramItemInfoWithIcon, false);
        }
        IconCache.this.mMainThreadExecutor.execute(new Runnable()
        {
          public final void run()
          {
            IconCache.1.this.val$caller.reapplyItemInfo(IconCache.1.this.val$info);
          }
        });
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
    //   5: invokevirtual 751	com/android/launcher3/IconCache:removeIconsForPkg	(Ljava/lang/String;Landroid/os/UserHandle;)V
    //   8: aload_0
    //   9: getfield 89	com/android/launcher3/IconCache:mPackageManager	Landroid/content/pm/PackageManager;
    //   12: aload_1
    //   13: sipush 8192
    //   16: invokevirtual 370	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   19: astore 5
    //   21: aload_0
    //   22: getfield 97	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   25: aload_2
    //   26: invokevirtual 405	com/android/launcher3/compat/UserManagerCompat:getSerialNumberForUser	(Landroid/os/UserHandle;)J
    //   29: lstore_3
    //   30: aload_0
    //   31: getfield 104	com/android/launcher3/IconCache:mLauncherApps	Lcom/android/launcher3/compat/LauncherAppsCompat;
    //   34: aload_1
    //   35: aload_2
    //   36: invokevirtual 666	com/android/launcher3/compat/LauncherAppsCompat:getActivityList	(Ljava/lang/String;Landroid/os/UserHandle;)Ljava/util/List;
    //   39: invokeinterface 662 1 0
    //   44: astore_1
    //   45: aload_1
    //   46: invokeinterface 548 1 0
    //   51: ifeq +23 -> 74
    //   54: aload_0
    //   55: aload_1
    //   56: invokeinterface 551 1 0
    //   61: checkcast 286	android/content/pm/LauncherActivityInfo
    //   64: aload 5
    //   66: lload_3
    //   67: iconst_0
    //   68: invokevirtual 753	com/android/launcher3/IconCache:addIconToDBAndMemCache	(Landroid/content/pm/LauncherActivityInfo;Landroid/content/pm/PackageInfo;JZ)V
    //   71: goto -26 -> 45
    //   74: aload_0
    //   75: monitorexit
    //   76: return
    //   77: astore_1
    //   78: ldc_w 446
    //   81: ldc_w 755
    //   84: aload_1
    //   85: invokestatic 454	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   88: pop
    //   89: aload_0
    //   90: monitorexit
    //   91: return
    //   92: astore_1
    //   93: aload_0
    //   94: monitorexit
    //   95: aload_1
    //   96: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	97	0	this	IconCache
    //   0	97	1	paramString	String
    //   0	97	2	paramUserHandle	UserHandle
    //   29	38	3	l	long
    //   19	46	5	localPackageInfo	PackageInfo
    // Exception table:
    //   from	to	target	type
    //   8	45	77	android/content/pm/PackageManager$NameNotFoundException
    //   45	71	77	android/content/pm/PackageManager$NameNotFoundException
    //   2	8	92	finally
    //   8	45	92	finally
    //   45	71	92	finally
    //   78	89	92	finally
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
    private static final int RELEASE_VERSION = (FeatureFlags.LAUNCHER3_DISABLE_ICON_NORMALIZATION ^ true) + true;
    
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
        return;
      }
      if (!this.mAppsToAdd.isEmpty())
      {
        localObject1 = (LauncherActivityInfo)this.mAppsToAdd.pop();
        localObject2 = (PackageInfo)this.mPkgInfoMap.get(((LauncherActivityInfo)localObject1).getComponentName().getPackageName());
        if (localObject2 != null) {
          IconCache.this.addIconToDBAndMemCache((LauncherActivityInfo)localObject1, (PackageInfo)localObject2, this.mUserSerial, false);
        }
        if (!this.mAppsToAdd.isEmpty()) {
          scheduleNext();
        }
      }
    }
    
    public final void scheduleNext()
    {
      IconCache.this.mWorkerHandler.postAtTime(this, IconCache.ICON_UPDATE_TOKEN, SystemClock.uptimeMillis() + 1L);
    }
  }
}
