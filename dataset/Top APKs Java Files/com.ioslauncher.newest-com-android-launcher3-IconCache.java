package com.android.launcher3;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Process;
import android.os.UserHandle;
import android.text.TextUtils;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.graphics.LauncherIcons;
import com.android.launcher3.model.PackageItemInfo;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.InstantAppResolver;
import com.android.launcher3.util.Provider;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class IconCache
{
  static final Object ICON_UPDATE_TOKEN = new Object();
  private final HashMap<ComponentKey, IconCache.CacheEntry> mCache = new HashMap(50);
  private final Context mContext;
  private final HashMap<UserHandle, Bitmap> mDefaultIcons = new HashMap();
  final IconCache.IconDB mIconDb;
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
    this.mIconDb = new IconCache.IconDB(paramContext, paramInvariantDeviceProfile.iconBitmapSize);
    this.mIconProvider = ((IconProvider)Utilities.getOverrideObject(IconProvider.class, paramContext, 2131296955));
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
  
  private void applyCacheEntry(IconCache.CacheEntry paramCacheEntry, ItemInfoWithIcon paramItemInfoWithIcon)
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
  
  private IconCache.CacheEntry cacheLocked(ComponentName paramComponentName, Provider<LauncherActivityInfo> paramProvider, UserHandle paramUserHandle, boolean paramBoolean1, boolean paramBoolean2)
  {
    ComponentKey localComponentKey = new ComponentKey(paramComponentName, paramUserHandle);
    IconCache.CacheEntry localCacheEntry = (IconCache.CacheEntry)this.mCache.get(localComponentKey);
    Object localObject;
    int i;
    if ((localCacheEntry != null) && ((!localCacheEntry.isLowResIcon) || (paramBoolean2)))
    {
      localObject = localCacheEntry;
      if (!localCacheEntry.isDirtyForTheme) {}
    }
    else
    {
      localCacheEntry = new IconCache.CacheEntry();
      this.mCache.put(localComponentKey, localCacheEntry);
      if (getEntryFromDB(localComponentKey, localCacheEntry, paramBoolean2)) {
        break label295;
      }
      localObject = (LauncherActivityInfo)paramProvider.get();
      if (localObject == null) {
        break label221;
      }
      localCacheEntry.icon = LauncherIcons.createBadgedIconBitmapWithTheme(getFullResIcon((LauncherActivityInfo)localObject), ((LauncherActivityInfo)localObject).getUser(), this.mContext, ((LauncherActivityInfo)paramProvider.get()).getApplicationInfo().targetSdkVersion, paramComponentName);
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
      label221:
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
      label295:
      paramComponentName = null;
      i = 0;
    }
  }
  
  private static Bitmap generateLowResIcon(Bitmap paramBitmap)
  {
    return Bitmap.createScaledBitmap(paramBitmap, paramBitmap.getWidth() / 5, paramBitmap.getHeight() / 5, true);
  }
  
  private IconCache.CacheEntry getEntryForPackageLocked(String paramString, UserHandle paramUserHandle, boolean paramBoolean)
  {
    ComponentKey localComponentKey = getPackageKey(paramString, paramUserHandle);
    Object localObject2 = (IconCache.CacheEntry)this.mCache.get(localComponentKey);
    Object localObject1;
    int i;
    PackageInfo localPackageInfo;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (((IconCache.CacheEntry)localObject2).isLowResIcon)
      {
        localObject1 = localObject2;
        if (paramBoolean) {}
      }
    }
    else
    {
      localObject1 = new IconCache.CacheEntry();
      if (getEntryFromDB(localComponentKey, (IconCache.CacheEntry)localObject1, paramBoolean)) {
        break label274;
      }
      try
      {
        if (Process.myUserHandle().equals(paramUserHandle))
        {
          i = 0;
          localPackageInfo = this.mPackageManager.getPackageInfo(paramString, i);
          paramString = localPackageInfo.applicationInfo;
          if (paramString != null) {
            break label145;
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
        this.mCache.put(localComponentKey, localObject1);
      }
      return localObject1;
      i = 8192;
      break;
      label145:
      localObject2 = LauncherIcons.createBadgedIconBitmapWithTheme(paramString.loadIcon(this.mPackageManager), paramUserHandle, this.mContext, paramString.targetSdkVersion, null);
      Bitmap localBitmap = generateLowResIcon((Bitmap)localObject2);
      ((IconCache.CacheEntry)localObject1).title = paramString.loadLabel(this.mPackageManager);
      ((IconCache.CacheEntry)localObject1).contentDescription = this.mUserManager.getBadgedLabelForUser(((IconCache.CacheEntry)localObject1).title, paramUserHandle);
      if (paramBoolean) {}
      for (paramString = localBitmap;; paramString = (String)localObject2)
      {
        ((IconCache.CacheEntry)localObject1).icon = paramString;
        ((IconCache.CacheEntry)localObject1).isLowResIcon = paramBoolean;
        addIconToDB(newContentValues$7048ad0b((Bitmap)localObject2, localBitmap, ((IconCache.CacheEntry)localObject1).title.toString()), localComponentKey.componentName, localPackageInfo, this.mUserManager.getSerialNumberForUser(paramUserHandle));
        i = 1;
        break;
      }
      label274:
      i = 1;
    }
  }
  
  /* Error */
  private boolean getEntryFromDB(ComponentKey paramComponentKey, IconCache.CacheEntry paramCacheEntry, boolean paramBoolean)
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
    //   14: getfield 107	com/android/launcher3/IconCache:mIconDb	Lcom/android/launcher3/IconCache$IconDB;
    //   17: astore 9
    //   19: iload_3
    //   20: ifeq +171 -> 191
    //   23: ldc_w 396
    //   26: astore 5
    //   28: aload 8
    //   30: astore 4
    //   32: aload_1
    //   33: getfield 386	com/android/launcher3/util/ComponentKey:componentName	Landroid/content/ComponentName;
    //   36: invokevirtual 159	android/content/ComponentName:flattenToString	()Ljava/lang/String;
    //   39: astore 10
    //   41: aload 8
    //   43: astore 4
    //   45: aload_0
    //   46: getfield 75	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   49: aload_1
    //   50: getfield 397	com/android/launcher3/util/ComponentKey:user	Landroid/os/UserHandle;
    //   53: invokevirtual 390	com/android/launcher3/compat/UserManagerCompat:getSerialNumberForUser	(Landroid/os/UserHandle;)J
    //   56: invokestatic 400	java/lang/Long:toString	(J)Ljava/lang/String;
    //   59: astore 11
    //   61: aload 8
    //   63: astore 4
    //   65: aload 9
    //   67: iconst_2
    //   68: anewarray 402	java/lang/String
    //   71: dup
    //   72: iconst_0
    //   73: aload 5
    //   75: aastore
    //   76: dup
    //   77: iconst_1
    //   78: ldc_w 404
    //   81: aastore
    //   82: ldc_w 406
    //   85: iconst_2
    //   86: anewarray 402	java/lang/String
    //   89: dup
    //   90: iconst_0
    //   91: aload 10
    //   93: aastore
    //   94: dup
    //   95: iconst_1
    //   96: aload 11
    //   98: aastore
    //   99: invokevirtual 410	com/android/launcher3/IconCache$IconDB:query	([Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   102: astore 5
    //   104: aload 5
    //   106: invokeinterface 416 1 0
    //   111: ifeq +140 -> 251
    //   114: aload 7
    //   116: astore 4
    //   118: iload_3
    //   119: ifeq +9 -> 128
    //   122: aload_0
    //   123: getfield 136	com/android/launcher3/IconCache:mLowResOptions	Landroid/graphics/BitmapFactory$Options;
    //   126: astore 4
    //   128: aload_2
    //   129: aload 5
    //   131: aload 4
    //   133: invokestatic 420	com/android/launcher3/IconCache:loadIconNoResize$48051843	(Landroid/database/Cursor;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   136: putfield 224	com/android/launcher3/IconCache$CacheEntry:icon	Landroid/graphics/Bitmap;
    //   139: aload_2
    //   140: iload_3
    //   141: putfield 239	com/android/launcher3/IconCache$CacheEntry:isLowResIcon	Z
    //   144: aload_2
    //   145: aload 5
    //   147: iconst_1
    //   148: invokeinterface 424 2 0
    //   153: putfield 209	com/android/launcher3/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   156: aload_2
    //   157: getfield 209	com/android/launcher3/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   160: ifnonnull +39 -> 199
    //   163: aload_2
    //   164: ldc_w 426
    //   167: putfield 209	com/android/launcher3/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   170: aload_2
    //   171: ldc_w 426
    //   174: putfield 219	com/android/launcher3/IconCache$CacheEntry:contentDescription	Ljava/lang/CharSequence;
    //   177: aload 5
    //   179: ifnull +10 -> 189
    //   182: aload 5
    //   184: invokeinterface 429 1 0
    //   189: iconst_1
    //   190: ireturn
    //   191: ldc_w 430
    //   194: astore 5
    //   196: goto -168 -> 28
    //   199: aload_2
    //   200: aload_0
    //   201: getfield 75	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   204: aload_2
    //   205: getfield 209	com/android/launcher3/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   208: aload_1
    //   209: getfield 397	com/android/launcher3/util/ComponentKey:user	Landroid/os/UserHandle;
    //   212: invokevirtual 308	com/android/launcher3/compat/UserManagerCompat:getBadgedLabelForUser	(Ljava/lang/CharSequence;Landroid/os/UserHandle;)Ljava/lang/CharSequence;
    //   215: putfield 219	com/android/launcher3/IconCache$CacheEntry:contentDescription	Ljava/lang/CharSequence;
    //   218: goto -41 -> 177
    //   221: astore_2
    //   222: aload 5
    //   224: astore_1
    //   225: aload_1
    //   226: astore 4
    //   228: ldc_w 432
    //   231: ldc_w 434
    //   234: aload_2
    //   235: invokestatic 440	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   238: pop
    //   239: aload_1
    //   240: ifnull +9 -> 249
    //   243: aload_1
    //   244: invokeinterface 429 1 0
    //   249: iconst_0
    //   250: ireturn
    //   251: aload 5
    //   253: ifnull -4 -> 249
    //   256: aload 5
    //   258: invokeinterface 429 1 0
    //   263: goto -14 -> 249
    //   266: astore_1
    //   267: aload 4
    //   269: ifnull +10 -> 279
    //   272: aload 4
    //   274: invokeinterface 429 1 0
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
    //   0	296	2	paramCacheEntry	IconCache.CacheEntry
    //   0	296	3	paramBoolean	boolean
    //   11	274	4	localObject1	Object
    //   26	257	5	localObject2	Object
    //   1	290	6	localObject3	Object
    //   7	108	7	localObject4	Object
    //   4	58	8	localObject5	Object
    //   17	49	9	localIconDB	IconCache.IconDB
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
  
  private ContentValues newContentValues$7048ad0b(Bitmap paramBitmap1, Bitmap paramBitmap2, String paramString)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("icon", Utilities.flattenBitmap(paramBitmap1));
    localContentValues.put("icon_low_res", Utilities.flattenBitmap(paramBitmap2));
    localContentValues.put("label", paramString);
    localContentValues.put("system_state", this.mIconProvider.getIconSystemState$16915f7f());
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
        Object localObject3 = new ComponentKey(paramLauncherActivityInfo.getComponentName(), paramLauncherActivityInfo.getUser());
        if (!paramBoolean)
        {
          Object localObject2 = (IconCache.CacheEntry)this.mCache.get(localObject3);
          if ((localObject2 == null) || (((IconCache.CacheEntry)localObject2).isLowResIcon)) {
            break label230;
          }
          localObject1 = localObject2;
          if (((IconCache.CacheEntry)localObject2).icon == null) {
            break label230;
          }
          localObject2 = localObject1;
          if (localObject1 == null)
          {
            localObject2 = new IconCache.CacheEntry();
            ((IconCache.CacheEntry)localObject2).icon = LauncherIcons.createBadgedIconBitmapWithTheme(getFullResIcon(paramLauncherActivityInfo), paramLauncherActivityInfo.getUser(), this.mContext, paramLauncherActivityInfo.getApplicationInfo().targetSdkVersion, paramLauncherActivityInfo.getComponentName());
          }
          ((IconCache.CacheEntry)localObject2).title = paramLauncherActivityInfo.getLabel();
          ((IconCache.CacheEntry)localObject2).contentDescription = this.mUserManager.getBadgedLabelForUser(((IconCache.CacheEntry)localObject2).title, paramLauncherActivityInfo.getUser());
          this.mCache.put(localObject3, localObject2);
          localObject1 = generateLowResIcon(((IconCache.CacheEntry)localObject2).icon);
          localObject3 = ((IconCache.CacheEntry)localObject2).icon;
          localObject2 = ((IconCache.CacheEntry)localObject2).title.toString();
          String str = paramLauncherActivityInfo.getApplicationInfo().packageName;
          addIconToDB(newContentValues$7048ad0b((Bitmap)localObject3, (Bitmap)localObject1, (String)localObject2), paramLauncherActivityInfo.getComponentName(), paramPackageInfo, paramLong);
          return;
        }
      }
      finally {}
      Object localObject1 = null;
      continue;
      label230:
      localObject1 = null;
    }
  }
  
  public final void cachePackageInstallInfo(String paramString, UserHandle paramUserHandle, Bitmap paramBitmap, CharSequence paramCharSequence)
  {
    try
    {
      removeFromMemCacheLocked(paramString, paramUserHandle);
      ComponentKey localComponentKey = getPackageKey(paramString, paramUserHandle);
      paramUserHandle = (IconCache.CacheEntry)this.mCache.get(localComponentKey);
      paramString = paramUserHandle;
      if (paramUserHandle == null) {
        paramString = new IconCache.CacheEntry();
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
      this.mIconDb.clear();
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
        this.mDefaultIcons.put(paramUserHandle, LauncherIcons.createBadgedIconBitmapWithTheme(getFullResDefaultActivityIcon(), paramUserHandle, this.mContext, 26, null));
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
  
  public final Drawable getFullResIcon(LauncherActivityInfo paramLauncherActivityInfo)
  {
    return IconProvider.getIcon$6768e156(paramLauncherActivityInfo, this.mIconDpi);
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
  
  public final int getIconDpi()
  {
    return this.mIconDpi;
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
    //   3: invokevirtual 482	com/android/launcher3/ItemInfoWithIcon:getTargetComponent	()Landroid/content/ComponentName;
    //   6: ifnonnull +37 -> 43
    //   9: aload_1
    //   10: aload_0
    //   11: aload_1
    //   12: getfield 228	com/android/launcher3/ItemInfoWithIcon:user	Landroid/os/UserHandle;
    //   15: invokevirtual 232	com/android/launcher3/IconCache:getDefaultIcon	(Landroid/os/UserHandle;)Landroid/graphics/Bitmap;
    //   18: putfield 235	com/android/launcher3/ItemInfoWithIcon:iconBitmap	Landroid/graphics/Bitmap;
    //   21: aload_1
    //   22: ldc_w 426
    //   25: putfield 216	com/android/launcher3/ItemInfoWithIcon:title	Ljava/lang/CharSequence;
    //   28: aload_1
    //   29: ldc_w 426
    //   32: putfield 220	com/android/launcher3/ItemInfoWithIcon:contentDescription	Ljava/lang/CharSequence;
    //   35: aload_1
    //   36: iconst_0
    //   37: putfield 242	com/android/launcher3/ItemInfoWithIcon:usingLowResIcon	Z
    //   40: aload_0
    //   41: monitorexit
    //   42: return
    //   43: aload_0
    //   44: aload_1
    //   45: new 596	com/android/launcher3/IconCache$ActivityInfoProvider
    //   48: dup
    //   49: aload_0
    //   50: aload_1
    //   51: invokevirtual 600	com/android/launcher3/ItemInfoWithIcon:getIntent	()Landroid/content/Intent;
    //   54: aload_1
    //   55: getfield 228	com/android/launcher3/ItemInfoWithIcon:user	Landroid/os/UserHandle;
    //   58: invokespecial 603	com/android/launcher3/IconCache$ActivityInfoProvider:<init>	(Lcom/android/launcher3/IconCache;Landroid/content/Intent;Landroid/os/UserHandle;)V
    //   61: iconst_1
    //   62: iload_2
    //   63: invokespecial 593	com/android/launcher3/IconCache:getTitleAndIcon	(Lcom/android/launcher3/ItemInfoWithIcon;Lcom/android/launcher3/util/Provider;ZZ)V
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
  
  public final void setAllIconsDirtyFlagForTheme()
  {
    Iterator localIterator = this.mCache.keySet().iterator();
    while (localIterator.hasNext())
    {
      ComponentKey localComponentKey = (ComponentKey)localIterator.next();
      ((IconCache.CacheEntry)this.mCache.get(localComponentKey)).isDirtyForTheme = true;
    }
    this.mIconDb.clear();
  }
  
  /* Error */
  public final void updateDbIcons(Set<String> paramSet)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 131	com/android/launcher3/IconCache:mWorkerHandler	Landroid/os/Handler;
    //   4: getstatic 40	com/android/launcher3/IconCache:ICON_UPDATE_TOKEN	Ljava/lang/Object;
    //   7: invokevirtual 627	android/os/Handler:removeCallbacksAndMessages	(Ljava/lang/Object;)V
    //   10: aload_0
    //   11: getfield 118	com/android/launcher3/IconCache:mIconProvider	Lcom/android/launcher3/IconProvider;
    //   14: invokevirtual 630	com/android/launcher3/IconProvider:updateSystemStateString	()V
    //   17: aload_0
    //   18: getfield 75	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   21: invokevirtual 634	com/android/launcher3/compat/UserManagerCompat:getUserProfiles	()Ljava/util/List;
    //   24: invokeinterface 637 1 0
    //   29: astore 15
    //   31: aload 15
    //   33: invokeinterface 532 1 0
    //   38: ifeq +42 -> 80
    //   41: aload 15
    //   43: invokeinterface 535 1 0
    //   48: checkcast 345	android/os/UserHandle
    //   51: astore 18
    //   53: aload_0
    //   54: getfield 82	com/android/launcher3/IconCache:mLauncherApps	Lcom/android/launcher3/compat/LauncherAppsCompat;
    //   57: aconst_null
    //   58: aload 18
    //   60: invokevirtual 641	com/android/launcher3/compat/LauncherAppsCompat:getActivityList	(Ljava/lang/String;Landroid/os/UserHandle;)Ljava/util/List;
    //   63: astore 12
    //   65: aload 12
    //   67: ifnull +13 -> 80
    //   70: aload 12
    //   72: invokeinterface 643 1 0
    //   77: ifeq +4 -> 81
    //   80: return
    //   81: invokestatic 343	android/os/Process:myUserHandle	()Landroid/os/UserHandle;
    //   84: aload 18
    //   86: invokevirtual 349	android/os/UserHandle:equals	(Ljava/lang/Object;)Z
    //   89: ifeq +88 -> 177
    //   92: aload_1
    //   93: astore 13
    //   95: aload_0
    //   96: getfield 75	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   99: aload 18
    //   101: invokevirtual 390	com/android/launcher3/compat/UserManagerCompat:getSerialNumberForUser	(Landroid/os/UserHandle;)J
    //   104: lstore 8
    //   106: aload_0
    //   107: getfield 59	com/android/launcher3/IconCache:mContext	Landroid/content/Context;
    //   110: invokevirtual 65	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   113: astore 14
    //   115: new 44	java/util/HashMap
    //   118: dup
    //   119: invokespecial 45	java/util/HashMap:<init>	()V
    //   122: astore 16
    //   124: aload 14
    //   126: sipush 8192
    //   129: invokevirtual 647	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   132: invokeinterface 637 1 0
    //   137: astore 14
    //   139: aload 14
    //   141: invokeinterface 532 1 0
    //   146: ifeq +39 -> 185
    //   149: aload 14
    //   151: invokeinterface 535 1 0
    //   156: checkcast 180	android/content/pm/PackageInfo
    //   159: astore 17
    //   161: aload 16
    //   163: aload 17
    //   165: getfield 648	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   168: aload 17
    //   170: invokevirtual 260	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   173: pop
    //   174: goto -35 -> 139
    //   177: invokestatic 653	java/util/Collections:emptySet	()Ljava/util/Set;
    //   180: astore 13
    //   182: goto -87 -> 95
    //   185: new 44	java/util/HashMap
    //   188: dup
    //   189: invokespecial 45	java/util/HashMap:<init>	()V
    //   192: astore 17
    //   194: aload 12
    //   196: invokeinterface 637 1 0
    //   201: astore 12
    //   203: aload 12
    //   205: invokeinterface 532 1 0
    //   210: ifeq +31 -> 241
    //   213: aload 12
    //   215: invokeinterface 535 1 0
    //   220: checkcast 271	android/content/pm/LauncherActivityInfo
    //   223: astore 14
    //   225: aload 17
    //   227: aload 14
    //   229: invokevirtual 548	android/content/pm/LauncherActivityInfo:getComponentName	()Landroid/content/ComponentName;
    //   232: aload 14
    //   234: invokevirtual 260	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   237: pop
    //   238: goto -35 -> 203
    //   241: new 516	java/util/HashSet
    //   244: dup
    //   245: invokespecial 517	java/util/HashSet:<init>	()V
    //   248: astore 20
    //   250: new 655	java/util/Stack
    //   253: dup
    //   254: invokespecial 656	java/util/Stack:<init>	()V
    //   257: astore 19
    //   259: aconst_null
    //   260: astore 12
    //   262: aload_0
    //   263: getfield 107	com/android/launcher3/IconCache:mIconDb	Lcom/android/launcher3/IconCache$IconDB;
    //   266: astore 14
    //   268: lload 8
    //   270: invokestatic 400	java/lang/Long:toString	(J)Ljava/lang/String;
    //   273: astore 21
    //   275: aload 14
    //   277: iconst_5
    //   278: anewarray 402	java/lang/String
    //   281: dup
    //   282: iconst_0
    //   283: ldc_w 658
    //   286: aastore
    //   287: dup
    //   288: iconst_1
    //   289: ldc -103
    //   291: aastore
    //   292: dup
    //   293: iconst_2
    //   294: ldc -78
    //   296: aastore
    //   297: dup
    //   298: iconst_3
    //   299: ldc -70
    //   301: aastore
    //   302: dup
    //   303: iconst_4
    //   304: ldc_w 509
    //   307: aastore
    //   308: ldc_w 660
    //   311: iconst_1
    //   312: anewarray 402	java/lang/String
    //   315: dup
    //   316: iconst_0
    //   317: aload 21
    //   319: aastore
    //   320: invokevirtual 410	com/android/launcher3/IconCache$IconDB:query	([Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   323: astore 14
    //   325: aload 14
    //   327: astore 12
    //   329: aload 12
    //   331: ldc -103
    //   333: invokeinterface 664 2 0
    //   338: istore_2
    //   339: aload 12
    //   341: ldc -78
    //   343: invokeinterface 664 2 0
    //   348: istore_3
    //   349: aload 12
    //   351: ldc -70
    //   353: invokeinterface 664 2 0
    //   358: istore 4
    //   360: aload 12
    //   362: ldc_w 658
    //   365: invokeinterface 664 2 0
    //   370: istore 5
    //   372: aload 12
    //   374: ldc_w 509
    //   377: invokeinterface 664 2 0
    //   382: istore 6
    //   384: aload 12
    //   386: invokeinterface 416 1 0
    //   391: ifeq +354 -> 745
    //   394: aload 12
    //   396: iload_2
    //   397: invokeinterface 424 2 0
    //   402: invokestatic 668	android/content/ComponentName:unflattenFromString	(Ljava/lang/String;)Landroid/content/ComponentName;
    //   405: astore 14
    //   407: aload 16
    //   409: aload 14
    //   411: invokevirtual 311	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   414: invokevirtual 253	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   417: checkcast 180	android/content/pm/PackageInfo
    //   420: astore 24
    //   422: aload 24
    //   424: ifnonnull +155 -> 579
    //   427: aload 13
    //   429: aload 14
    //   431: invokevirtual 311	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   434: invokeinterface 671 2 0
    //   439: ifne -55 -> 384
    //   442: aload_0
    //   443: aload 14
    //   445: aload 18
    //   447: invokevirtual 673	com/android/launcher3/IconCache:remove	(Landroid/content/ComponentName;Landroid/os/UserHandle;)V
    //   450: aload 20
    //   452: aload 12
    //   454: iload 5
    //   456: invokeinterface 677 2 0
    //   461: invokestatic 194	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   464: invokevirtual 539	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   467: pop
    //   468: goto -84 -> 384
    //   471: astore 13
    //   473: ldc_w 432
    //   476: ldc_w 434
    //   479: aload 13
    //   481: invokestatic 440	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   484: pop
    //   485: aload 12
    //   487: ifnull +10 -> 497
    //   490: aload 12
    //   492: invokeinterface 429 1 0
    //   497: aload 20
    //   499: invokevirtual 678	java/util/HashSet:isEmpty	()Z
    //   502: ifne +19 -> 521
    //   505: aload_0
    //   506: getfield 107	com/android/launcher3/IconCache:mIconDb	Lcom/android/launcher3/IconCache$IconDB;
    //   509: ldc_w 658
    //   512: aload 20
    //   514: invokestatic 682	com/android/launcher3/Utilities:createDbSelectionQuery	(Ljava/lang/String;Ljava/lang/Iterable;)Ljava/lang/String;
    //   517: aconst_null
    //   518: invokevirtual 620	com/android/launcher3/IconCache$IconDB:delete	(Ljava/lang/String;[Ljava/lang/String;)V
    //   521: aload 17
    //   523: invokevirtual 683	java/util/HashMap:isEmpty	()Z
    //   526: ifeq +11 -> 537
    //   529: aload 19
    //   531: invokevirtual 684	java/util/Stack:isEmpty	()Z
    //   534: ifne -503 -> 31
    //   537: new 655	java/util/Stack
    //   540: dup
    //   541: invokespecial 656	java/util/Stack:<init>	()V
    //   544: astore 12
    //   546: aload 12
    //   548: aload 17
    //   550: invokevirtual 688	java/util/HashMap:values	()Ljava/util/Collection;
    //   553: invokevirtual 692	java/util/Stack:addAll	(Ljava/util/Collection;)Z
    //   556: pop
    //   557: new 694	com/android/launcher3/IconCache$SerializedIconUpdateTask
    //   560: dup
    //   561: aload_0
    //   562: lload 8
    //   564: aload 16
    //   566: aload 12
    //   568: aload 19
    //   570: invokespecial 697	com/android/launcher3/IconCache$SerializedIconUpdateTask:<init>	(Lcom/android/launcher3/IconCache;JLjava/util/HashMap;Ljava/util/Stack;Ljava/util/Stack;)V
    //   573: invokevirtual 700	com/android/launcher3/IconCache$SerializedIconUpdateTask:scheduleNext	()V
    //   576: goto -545 -> 31
    //   579: aload 24
    //   581: getfield 359	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   584: getfield 703	android/content/pm/ApplicationInfo:flags	I
    //   587: ldc_w 704
    //   590: iand
    //   591: ifne -207 -> 384
    //   594: aload 12
    //   596: iload_3
    //   597: invokeinterface 708 2 0
    //   602: lstore 10
    //   604: aload 12
    //   606: iload 4
    //   608: invokeinterface 677 2 0
    //   613: istore 7
    //   615: aload 17
    //   617: aload 14
    //   619: invokevirtual 543	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   622: checkcast 271	android/content/pm/LauncherActivityInfo
    //   625: astore 21
    //   627: iload 7
    //   629: aload 24
    //   631: getfield 189	android/content/pm/PackageInfo:versionCode	I
    //   634: if_icmpne +51 -> 685
    //   637: lload 10
    //   639: aload 24
    //   641: getfield 184	android/content/pm/PackageInfo:lastUpdateTime	J
    //   644: lcmp
    //   645: ifne +40 -> 685
    //   648: aload 12
    //   650: iload 6
    //   652: invokeinterface 424 2 0
    //   657: astore 22
    //   659: aload_0
    //   660: getfield 118	com/android/launcher3/IconCache:mIconProvider	Lcom/android/launcher3/IconProvider;
    //   663: astore 23
    //   665: aload 24
    //   667: getfield 648	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   670: astore 24
    //   672: aload 22
    //   674: aload 23
    //   676: invokevirtual 512	com/android/launcher3/IconProvider:getIconSystemState$16915f7f	()Ljava/lang/String;
    //   679: invokestatic 711	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   682: ifne -298 -> 384
    //   685: aload 21
    //   687: ifnonnull +47 -> 734
    //   690: aload_0
    //   691: aload 14
    //   693: aload 18
    //   695: invokevirtual 673	com/android/launcher3/IconCache:remove	(Landroid/content/ComponentName;Landroid/os/UserHandle;)V
    //   698: aload 20
    //   700: aload 12
    //   702: iload 5
    //   704: invokeinterface 677 2 0
    //   709: invokestatic 194	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   712: invokevirtual 539	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   715: pop
    //   716: goto -332 -> 384
    //   719: astore_1
    //   720: aload 12
    //   722: ifnull +10 -> 732
    //   725: aload 12
    //   727: invokeinterface 429 1 0
    //   732: aload_1
    //   733: athrow
    //   734: aload 19
    //   736: aload 21
    //   738: invokevirtual 712	java/util/Stack:add	(Ljava/lang/Object;)Z
    //   741: pop
    //   742: goto -358 -> 384
    //   745: aload 12
    //   747: ifnull -250 -> 497
    //   750: aload 12
    //   752: invokeinterface 429 1 0
    //   757: goto -260 -> 497
    //   760: astore_1
    //   761: aconst_null
    //   762: astore 12
    //   764: goto -44 -> 720
    //   767: astore_1
    //   768: goto -48 -> 720
    //   771: astore 13
    //   773: goto -300 -> 473
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	776	0	this	IconCache
    //   0	776	1	paramSet	Set<String>
    //   338	59	2	i	int
    //   348	249	3	j	int
    //   358	249	4	k	int
    //   370	333	5	m	int
    //   382	269	6	n	int
    //   613	22	7	i1	int
    //   104	459	8	l1	long
    //   602	36	10	l2	long
    //   63	700	12	localObject1	Object
    //   93	335	13	localObject2	Object
    //   471	9	13	localSQLiteException1	android.database.sqlite.SQLiteException
    //   771	1	13	localSQLiteException2	android.database.sqlite.SQLiteException
    //   113	579	14	localObject3	Object
    //   29	13	15	localIterator	Iterator
    //   122	443	16	localHashMap	HashMap
    //   159	457	17	localObject4	Object
    //   51	643	18	localUserHandle	UserHandle
    //   257	478	19	localStack	java.util.Stack
    //   248	451	20	localHashSet	HashSet
    //   273	464	21	localObject5	Object
    //   657	16	22	str	String
    //   663	12	23	localIconProvider	IconProvider
    //   420	251	24	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   329	384	471	android/database/sqlite/SQLiteException
    //   384	422	471	android/database/sqlite/SQLiteException
    //   427	468	471	android/database/sqlite/SQLiteException
    //   579	685	471	android/database/sqlite/SQLiteException
    //   690	716	471	android/database/sqlite/SQLiteException
    //   734	742	471	android/database/sqlite/SQLiteException
    //   329	384	719	finally
    //   384	422	719	finally
    //   427	468	719	finally
    //   579	685	719	finally
    //   690	716	719	finally
    //   734	742	719	finally
    //   262	325	760	finally
    //   473	485	767	finally
    //   262	325	771	android/database/sqlite/SQLiteException
  }
  
  public final IconCache.IconLoadRequest updateIconInBackground(IconCache.ItemInfoUpdateReceiver paramItemInfoUpdateReceiver, ItemInfoWithIcon paramItemInfoWithIcon)
  {
    paramItemInfoUpdateReceiver = new IconCache.1(this, paramItemInfoWithIcon, paramItemInfoUpdateReceiver);
    this.mWorkerHandler.post(paramItemInfoUpdateReceiver);
    return new IconCache.IconLoadRequest(paramItemInfoUpdateReceiver, this.mWorkerHandler);
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
    //   5: invokevirtual 732	com/android/launcher3/IconCache:removeIconsForPkg	(Ljava/lang/String;Landroid/os/UserHandle;)V
    //   8: aload_0
    //   9: getfield 67	com/android/launcher3/IconCache:mPackageManager	Landroid/content/pm/PackageManager;
    //   12: aload_1
    //   13: sipush 8192
    //   16: invokevirtual 355	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   19: astore 5
    //   21: aload_0
    //   22: getfield 75	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   25: aload_2
    //   26: invokevirtual 390	com/android/launcher3/compat/UserManagerCompat:getSerialNumberForUser	(Landroid/os/UserHandle;)J
    //   29: lstore_3
    //   30: aload_0
    //   31: getfield 82	com/android/launcher3/IconCache:mLauncherApps	Lcom/android/launcher3/compat/LauncherAppsCompat;
    //   34: aload_1
    //   35: aload_2
    //   36: invokevirtual 641	com/android/launcher3/compat/LauncherAppsCompat:getActivityList	(Ljava/lang/String;Landroid/os/UserHandle;)Ljava/util/List;
    //   39: invokeinterface 637 1 0
    //   44: astore_1
    //   45: aload_1
    //   46: invokeinterface 532 1 0
    //   51: ifeq +35 -> 86
    //   54: aload_0
    //   55: aload_1
    //   56: invokeinterface 535 1 0
    //   61: checkcast 271	android/content/pm/LauncherActivityInfo
    //   64: aload 5
    //   66: lload_3
    //   67: iconst_0
    //   68: invokevirtual 734	com/android/launcher3/IconCache:addIconToDBAndMemCache	(Landroid/content/pm/LauncherActivityInfo;Landroid/content/pm/PackageInfo;JZ)V
    //   71: goto -26 -> 45
    //   74: astore_1
    //   75: ldc_w 432
    //   78: ldc_w 736
    //   81: aload_1
    //   82: invokestatic 440	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
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
      IconCache.CacheEntry localCacheEntry = cacheLocked(paramAppInfo.componentName, Provider.of(paramAppInfo.launcherActivityInfo), paramAppInfo.user, false, paramAppInfo.usingLowResIcon);
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
}
