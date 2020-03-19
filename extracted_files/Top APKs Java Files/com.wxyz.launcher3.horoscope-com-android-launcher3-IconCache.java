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
    this.mIconProvider = ((IconProvider)Utilities.getOverrideObject(IconProvider.class, paramContext, 2131362961));
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
  
  private Bitmap generateLowResIcon(Bitmap paramBitmap)
  {
    return Bitmap.createScaledBitmap(paramBitmap, paramBitmap.getWidth() / 5, paramBitmap.getHeight() / 5, true);
  }
  
  private CacheEntry getEntryForPackageLocked(String paramString, UserHandle paramUserHandle, boolean paramBoolean)
  {
    Preconditions.assertWorkerThread();
    ComponentKey localComponentKey = getPackageKey(paramString, paramUserHandle);
    localObject2 = (CacheEntry)this.mCache.get(localComponentKey);
    CacheEntry localCacheEntry;
    int j;
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
    try
    {
      if (!Process.myUserHandle().equals(paramUserHandle)) {
        break label333;
      }
      i = 0;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;)
      {
        PackageInfo localPackageInfo;
        ApplicationInfo localApplicationInfo;
        Bitmap localBitmap;
        continue;
        i = 8192;
        continue;
        localObject2 = localObject1;
      }
    }
    localPackageInfo = this.mPackageManager.getPackageInfo(paramString, i);
    localApplicationInfo = localPackageInfo.applicationInfo;
    if (localApplicationInfo != null)
    {
      localObject2 = LauncherIcons.createBadgedIconBitmap(localApplicationInfo.loadIcon(this.mPackageManager), paramUserHandle, this.mContext, localApplicationInfo.targetSdkVersion);
      localObject1 = localObject2;
      if (this.mInstantAppResolver.isInstantApp(localApplicationInfo)) {
        localObject1 = LauncherIcons.badgeWithDrawable((Bitmap)localObject2, this.mContext.getDrawable(2130837823), this.mContext);
      }
      localBitmap = generateLowResIcon((Bitmap)localObject1);
      localCacheEntry.title = localApplicationInfo.loadLabel(this.mPackageManager);
      localCacheEntry.contentDescription = this.mUserManager.getBadgedLabelForUser(localCacheEntry.title, paramUserHandle);
      if (!paramBoolean) {
        break label341;
      }
      localObject2 = localBitmap;
      localCacheEntry.icon = ((Bitmap)localObject2);
      localCacheEntry.isLowResIcon = paramBoolean;
      addIconToDB(newContentValues((Bitmap)localObject1, localBitmap, localCacheEntry.title.toString(), paramString), localComponentKey.componentName, localPackageInfo, this.mUserManager.getSerialNumberForUser(paramUserHandle));
      i = j;
    }
    else
    {
      throw new PackageManager.NameNotFoundException("ApplicationInfo is null");
      i = 0;
    }
    localObject1 = localCacheEntry;
    if (i != 0)
    {
      this.mCache.put(localComponentKey, localCacheEntry);
      localObject1 = localCacheEntry;
    }
    return localObject1;
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
    //   20: ifeq +274 -> 294
    //   23: ldc_w 389
    //   26: astore 5
    //   28: goto +3 -> 31
    //   31: aload 8
    //   33: astore 4
    //   35: aload_1
    //   36: getfield 371	com/android/launcher3/util/ComponentKey:componentName	Landroid/content/ComponentName;
    //   39: invokevirtual 179	android/content/ComponentName:flattenToString	()Ljava/lang/String;
    //   42: astore 10
    //   44: aload 8
    //   46: astore 4
    //   48: aload_0
    //   49: getfield 97	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   52: aload_1
    //   53: getfield 390	com/android/launcher3/util/ComponentKey:user	Landroid/os/UserHandle;
    //   56: invokevirtual 375	com/android/launcher3/compat/UserManagerCompat:getSerialNumberForUser	(Landroid/os/UserHandle;)J
    //   59: invokestatic 393	java/lang/Long:toString	(J)Ljava/lang/String;
    //   62: astore 11
    //   64: aload 8
    //   66: astore 4
    //   68: aload 9
    //   70: iconst_2
    //   71: anewarray 395	java/lang/String
    //   74: dup
    //   75: iconst_0
    //   76: aload 5
    //   78: aastore
    //   79: dup
    //   80: iconst_1
    //   81: ldc_w 397
    //   84: aastore
    //   85: ldc_w 399
    //   88: iconst_2
    //   89: anewarray 395	java/lang/String
    //   92: dup
    //   93: iconst_0
    //   94: aload 10
    //   96: aastore
    //   97: dup
    //   98: iconst_1
    //   99: aload 11
    //   101: aastore
    //   102: invokevirtual 403	com/android/launcher3/IconCache$IconDB:query	([Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   105: astore 5
    //   107: aload 5
    //   109: invokeinterface 409 1 0
    //   114: ifeq +103 -> 217
    //   117: aload 7
    //   119: astore 4
    //   121: iload_3
    //   122: ifeq +9 -> 131
    //   125: aload_0
    //   126: getfield 156	com/android/launcher3/IconCache:mLowResOptions	Landroid/graphics/BitmapFactory$Options;
    //   129: astore 4
    //   131: aload_2
    //   132: aload 5
    //   134: iconst_0
    //   135: aload 4
    //   137: invokestatic 413	com/android/launcher3/IconCache:loadIconNoResize	(Landroid/database/Cursor;ILandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   140: putfield 242	com/android/launcher3/IconCache$CacheEntry:icon	Landroid/graphics/Bitmap;
    //   143: aload_2
    //   144: iload_3
    //   145: putfield 257	com/android/launcher3/IconCache$CacheEntry:isLowResIcon	Z
    //   148: aload_2
    //   149: aload 5
    //   151: iconst_1
    //   152: invokeinterface 417 2 0
    //   157: putfield 227	com/android/launcher3/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   160: aload_2
    //   161: getfield 227	com/android/launcher3/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   164: ifnonnull +20 -> 184
    //   167: aload_2
    //   168: ldc_w 419
    //   171: putfield 227	com/android/launcher3/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   174: aload_2
    //   175: ldc_w 419
    //   178: putfield 237	com/android/launcher3/IconCache$CacheEntry:contentDescription	Ljava/lang/CharSequence;
    //   181: goto +22 -> 203
    //   184: aload_2
    //   185: aload_0
    //   186: getfield 97	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   189: aload_2
    //   190: getfield 227	com/android/launcher3/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   193: aload_1
    //   194: getfield 390	com/android/launcher3/util/ComponentKey:user	Landroid/os/UserHandle;
    //   197: invokevirtual 357	com/android/launcher3/compat/UserManagerCompat:getBadgedLabelForUser	(Ljava/lang/CharSequence;Landroid/os/UserHandle;)Ljava/lang/CharSequence;
    //   200: putfield 237	com/android/launcher3/IconCache$CacheEntry:contentDescription	Ljava/lang/CharSequence;
    //   203: aload 5
    //   205: ifnull +10 -> 215
    //   208: aload 5
    //   210: invokeinterface 422 1 0
    //   215: iconst_1
    //   216: ireturn
    //   217: aload 5
    //   219: ifnull +59 -> 278
    //   222: aload 5
    //   224: invokeinterface 422 1 0
    //   229: iconst_0
    //   230: ireturn
    //   231: astore_1
    //   232: aload 5
    //   234: astore 4
    //   236: goto +44 -> 280
    //   239: astore_2
    //   240: aload 5
    //   242: astore_1
    //   243: goto +11 -> 254
    //   246: astore_1
    //   247: goto +33 -> 280
    //   250: astore_2
    //   251: aload 6
    //   253: astore_1
    //   254: aload_1
    //   255: astore 4
    //   257: ldc_w 424
    //   260: ldc_w 426
    //   263: aload_2
    //   264: invokestatic 432	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   267: pop
    //   268: aload_1
    //   269: ifnull +9 -> 278
    //   272: aload_1
    //   273: invokeinterface 422 1 0
    //   278: iconst_0
    //   279: ireturn
    //   280: aload 4
    //   282: ifnull +10 -> 292
    //   285: aload 4
    //   287: invokeinterface 422 1 0
    //   292: aload_1
    //   293: athrow
    //   294: ldc_w 433
    //   297: astore 5
    //   299: goto -268 -> 31
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	302	0	this	IconCache
    //   0	302	1	paramComponentKey	ComponentKey
    //   0	302	2	paramCacheEntry	CacheEntry
    //   0	302	3	paramBoolean	boolean
    //   11	275	4	localObject1	Object
    //   26	272	5	localObject2	Object
    //   4	248	6	localObject3	Object
    //   7	111	7	localObject4	Object
    //   1	64	8	localObject5	Object
    //   17	52	9	localIconDB	IconDB
    //   42	53	10	str1	String
    //   62	38	11	str2	String
    // Exception table:
    //   from	to	target	type
    //   107	117	231	finally
    //   125	131	231	finally
    //   131	181	231	finally
    //   184	203	231	finally
    //   107	117	239	android/database/sqlite/SQLiteException
    //   125	131	239	android/database/sqlite/SQLiteException
    //   131	181	239	android/database/sqlite/SQLiteException
    //   184	203	239	android/database/sqlite/SQLiteException
    //   13	19	246	finally
    //   35	44	246	finally
    //   48	64	246	finally
    //   68	107	246	finally
    //   257	268	246	finally
    //   13	19	250	android/database/sqlite/SQLiteException
    //   35	44	250	android/database/sqlite/SQLiteException
    //   48	64	250	android/database/sqlite/SQLiteException
    //   68	107	250	android/database/sqlite/SQLiteException
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
  
  private static Bitmap loadIconNoResize(Cursor paramCursor, int paramInt, BitmapFactory.Options paramOptions)
  {
    paramCursor = paramCursor.getBlob(paramInt);
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
  
  /* Error */
  private void updateDBIcons(UserHandle paramUserHandle, List<LauncherActivityInfo> paramList, Set<String> paramSet)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 97	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   4: aload_1
    //   5: invokevirtual 375	com/android/launcher3/compat/UserManagerCompat:getSerialNumberForUser	(Landroid/os/UserHandle;)J
    //   8: lstore 11
    //   10: aload_0
    //   11: getfield 81	com/android/launcher3/IconCache:mContext	Landroid/content/Context;
    //   14: invokevirtual 87	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   17: astore 18
    //   19: new 66	java/util/HashMap
    //   22: dup
    //   23: invokespecial 67	java/util/HashMap:<init>	()V
    //   26: astore 17
    //   28: aload 18
    //   30: sipush 8192
    //   33: invokevirtual 554	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   36: invokeinterface 557 1 0
    //   41: astore 18
    //   43: aload 18
    //   45: invokeinterface 533 1 0
    //   50: ifeq +31 -> 81
    //   53: aload 18
    //   55: invokeinterface 537 1 0
    //   60: checkcast 200	android/content/pm/PackageInfo
    //   63: astore 19
    //   65: aload 17
    //   67: aload 19
    //   69: getfield 561	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   72: aload 19
    //   74: invokevirtual 385	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   77: pop
    //   78: goto -35 -> 43
    //   81: new 66	java/util/HashMap
    //   84: dup
    //   85: invokespecial 67	java/util/HashMap:<init>	()V
    //   88: astore 18
    //   90: aload_2
    //   91: invokeinterface 557 1 0
    //   96: astore_2
    //   97: aload_2
    //   98: invokeinterface 533 1 0
    //   103: ifeq +30 -> 133
    //   106: aload_2
    //   107: invokeinterface 537 1 0
    //   112: checkcast 563	android/content/pm/LauncherActivityInfo
    //   115: astore 19
    //   117: aload 18
    //   119: aload 19
    //   121: invokevirtual 566	android/content/pm/LauncherActivityInfo:getComponentName	()Landroid/content/ComponentName;
    //   124: aload 19
    //   126: invokevirtual 385	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   129: pop
    //   130: goto -33 -> 97
    //   133: new 517	java/util/HashSet
    //   136: dup
    //   137: invokespecial 518	java/util/HashSet:<init>	()V
    //   140: astore 20
    //   142: new 568	java/util/Stack
    //   145: dup
    //   146: invokespecial 569	java/util/Stack:<init>	()V
    //   149: astore 19
    //   151: aload_0
    //   152: getfield 127	com/android/launcher3/IconCache:mIconDb	Lcom/android/launcher3/IconCache$IconDB;
    //   155: astore_2
    //   156: lload 11
    //   158: invokestatic 393	java/lang/Long:toString	(J)Ljava/lang/String;
    //   161: astore 21
    //   163: aload_2
    //   164: iconst_5
    //   165: anewarray 395	java/lang/String
    //   168: dup
    //   169: iconst_0
    //   170: ldc_w 571
    //   173: aastore
    //   174: dup
    //   175: iconst_1
    //   176: ldc -83
    //   178: aastore
    //   179: dup
    //   180: iconst_2
    //   181: ldc -58
    //   183: aastore
    //   184: dup
    //   185: iconst_3
    //   186: ldc -50
    //   188: aastore
    //   189: dup
    //   190: iconst_4
    //   191: ldc_w 509
    //   194: aastore
    //   195: ldc_w 573
    //   198: iconst_1
    //   199: anewarray 395	java/lang/String
    //   202: dup
    //   203: iconst_0
    //   204: aload 21
    //   206: aastore
    //   207: invokevirtual 403	com/android/launcher3/IconCache$IconDB:query	([Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   210: astore_2
    //   211: lload 11
    //   213: lstore 13
    //   215: aload_2
    //   216: ldc -83
    //   218: invokeinterface 577 2 0
    //   223: istore 4
    //   225: lload 11
    //   227: lstore 13
    //   229: aload_2
    //   230: ldc -58
    //   232: invokeinterface 577 2 0
    //   237: istore 6
    //   239: lload 11
    //   241: lstore 13
    //   243: aload_2
    //   244: ldc -50
    //   246: invokeinterface 577 2 0
    //   251: istore 5
    //   253: lload 11
    //   255: lstore 13
    //   257: aload_2
    //   258: ldc_w 571
    //   261: invokeinterface 577 2 0
    //   266: istore 7
    //   268: lload 11
    //   270: lstore 13
    //   272: aload_2
    //   273: ldc_w 509
    //   276: invokeinterface 577 2 0
    //   281: istore 8
    //   283: lload 11
    //   285: lstore 13
    //   287: aload_2
    //   288: invokeinterface 409 1 0
    //   293: ifeq +264 -> 557
    //   296: lload 11
    //   298: lstore 13
    //   300: aload_2
    //   301: iload 4
    //   303: invokeinterface 417 2 0
    //   308: invokestatic 581	android/content/ComponentName:unflattenFromString	(Ljava/lang/String;)Landroid/content/ComponentName;
    //   311: astore 21
    //   313: lload 11
    //   315: lstore 13
    //   317: aload 17
    //   319: aload 21
    //   321: invokevirtual 540	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   324: invokevirtual 292	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   327: checkcast 200	android/content/pm/PackageInfo
    //   330: astore 22
    //   332: aload 22
    //   334: ifnonnull +56 -> 390
    //   337: lload 11
    //   339: lstore 13
    //   341: aload_3
    //   342: aload 21
    //   344: invokevirtual 540	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   347: invokeinterface 584 2 0
    //   352: ifne +374 -> 726
    //   355: lload 11
    //   357: lstore 13
    //   359: aload_0
    //   360: aload 21
    //   362: aload_1
    //   363: invokevirtual 586	com/android/launcher3/IconCache:remove	(Landroid/content/ComponentName;Landroid/os/UserHandle;)V
    //   366: lload 11
    //   368: lstore 13
    //   370: aload 20
    //   372: aload_2
    //   373: iload 7
    //   375: invokeinterface 590 2 0
    //   380: invokestatic 214	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   383: invokevirtual 544	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   386: pop
    //   387: goto +339 -> 726
    //   390: lload 11
    //   392: lstore 13
    //   394: aload 22
    //   396: getfield 319	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   399: getfield 593	android/content/pm/ApplicationInfo:flags	I
    //   402: ldc_w 594
    //   405: iand
    //   406: ifeq +6 -> 412
    //   409: goto +317 -> 726
    //   412: lload 11
    //   414: lstore 13
    //   416: aload_2
    //   417: iload 6
    //   419: invokeinterface 598 2 0
    //   424: lstore 15
    //   426: lload 11
    //   428: lstore 13
    //   430: aload_2
    //   431: iload 5
    //   433: invokeinterface 590 2 0
    //   438: istore 9
    //   440: lload 11
    //   442: lstore 13
    //   444: aload 18
    //   446: aload 21
    //   448: invokevirtual 548	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   451: checkcast 563	android/content/pm/LauncherActivityInfo
    //   454: astore 23
    //   456: lload 11
    //   458: lstore 13
    //   460: aload 22
    //   462: getfield 209	android/content/pm/PackageInfo:versionCode	I
    //   465: istore 10
    //   467: iload 9
    //   469: iload 10
    //   471: if_icmpne +262 -> 733
    //   474: lload 15
    //   476: aload 22
    //   478: getfield 204	android/content/pm/PackageInfo:lastUpdateTime	J
    //   481: lcmp
    //   482: ifne +32 -> 514
    //   485: aload_2
    //   486: iload 8
    //   488: invokeinterface 417 2 0
    //   493: aload_0
    //   494: getfield 138	com/android/launcher3/IconCache:mIconProvider	Lcom/android/launcher3/IconProvider;
    //   497: aload 22
    //   499: getfield 561	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   502: invokevirtual 513	com/android/launcher3/IconProvider:getIconSystemState	(Ljava/lang/String;)Ljava/lang/String;
    //   505: invokestatic 603	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   508: ifeq +6 -> 514
    //   511: goto +43 -> 554
    //   514: aload 23
    //   516: ifnonnull +30 -> 546
    //   519: aload_0
    //   520: aload 21
    //   522: aload_1
    //   523: invokevirtual 586	com/android/launcher3/IconCache:remove	(Landroid/content/ComponentName;Landroid/os/UserHandle;)V
    //   526: aload 20
    //   528: aload_2
    //   529: iload 7
    //   531: invokeinterface 590 2 0
    //   536: invokestatic 214	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   539: invokevirtual 544	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   542: pop
    //   543: goto +11 -> 554
    //   546: aload 19
    //   548: aload 23
    //   550: invokevirtual 604	java/util/Stack:add	(Ljava/lang/Object;)Z
    //   553: pop
    //   554: goto -271 -> 283
    //   557: lload 11
    //   559: lstore 13
    //   561: aload_2
    //   562: ifnull +70 -> 632
    //   565: aload_2
    //   566: invokeinterface 422 1 0
    //   571: lload 11
    //   573: lstore 13
    //   575: goto +57 -> 632
    //   578: astore_1
    //   579: goto +135 -> 714
    //   582: astore_1
    //   583: lload 13
    //   585: lstore 11
    //   587: aload_1
    //   588: astore_3
    //   589: aload_2
    //   590: astore_1
    //   591: goto +12 -> 603
    //   594: astore_1
    //   595: aconst_null
    //   596: astore_2
    //   597: goto +117 -> 714
    //   600: astore_3
    //   601: aconst_null
    //   602: astore_1
    //   603: ldc_w 424
    //   606: ldc_w 426
    //   609: aload_3
    //   610: invokestatic 432	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   613: pop
    //   614: lload 11
    //   616: lstore 13
    //   618: aload_1
    //   619: ifnull +13 -> 632
    //   622: aload_1
    //   623: invokeinterface 422 1 0
    //   628: lload 11
    //   630: lstore 13
    //   632: aload 20
    //   634: invokevirtual 607	java/util/HashSet:isEmpty	()Z
    //   637: ifne +19 -> 656
    //   640: aload_0
    //   641: getfield 127	com/android/launcher3/IconCache:mIconDb	Lcom/android/launcher3/IconCache$IconDB;
    //   644: ldc_w 571
    //   647: aload 20
    //   649: invokestatic 611	com/android/launcher3/Utilities:createDbSelectionQuery	(Ljava/lang/String;Ljava/lang/Iterable;)Ljava/lang/String;
    //   652: aconst_null
    //   653: invokevirtual 615	com/android/launcher3/IconCache$IconDB:delete	(Ljava/lang/String;[Ljava/lang/String;)V
    //   656: aload 18
    //   658: invokevirtual 616	java/util/HashMap:isEmpty	()Z
    //   661: ifeq +11 -> 672
    //   664: aload 19
    //   666: invokevirtual 617	java/util/Stack:isEmpty	()Z
    //   669: ifne +39 -> 708
    //   672: new 568	java/util/Stack
    //   675: dup
    //   676: invokespecial 569	java/util/Stack:<init>	()V
    //   679: astore_1
    //   680: aload_1
    //   681: aload 18
    //   683: invokevirtual 621	java/util/HashMap:values	()Ljava/util/Collection;
    //   686: invokevirtual 625	java/util/Stack:addAll	(Ljava/util/Collection;)Z
    //   689: pop
    //   690: new 25	com/android/launcher3/IconCache$SerializedIconUpdateTask
    //   693: dup
    //   694: aload_0
    //   695: lload 13
    //   697: aload 17
    //   699: aload_1
    //   700: aload 19
    //   702: invokespecial 628	com/android/launcher3/IconCache$SerializedIconUpdateTask:<init>	(Lcom/android/launcher3/IconCache;JLjava/util/HashMap;Ljava/util/Stack;Ljava/util/Stack;)V
    //   705: invokevirtual 631	com/android/launcher3/IconCache$SerializedIconUpdateTask:scheduleNext	()V
    //   708: return
    //   709: astore_3
    //   710: aload_1
    //   711: astore_2
    //   712: aload_3
    //   713: astore_1
    //   714: aload_2
    //   715: ifnull +9 -> 724
    //   718: aload_2
    //   719: invokeinterface 422 1 0
    //   724: aload_1
    //   725: athrow
    //   726: goto -443 -> 283
    //   729: astore_1
    //   730: goto -143 -> 587
    //   733: goto -219 -> 514
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	736	0	this	IconCache
    //   0	736	1	paramUserHandle	UserHandle
    //   0	736	2	paramList	List<LauncherActivityInfo>
    //   0	736	3	paramSet	Set<String>
    //   223	79	4	i	int
    //   251	181	5	j	int
    //   237	181	6	k	int
    //   266	264	7	m	int
    //   281	206	8	n	int
    //   438	34	9	i1	int
    //   465	7	10	i2	int
    //   8	621	11	l1	long
    //   213	483	13	l2	long
    //   424	51	15	l3	long
    //   26	672	17	localHashMap	HashMap
    //   17	665	18	localObject1	Object
    //   63	638	19	localObject2	Object
    //   140	508	20	localHashSet	HashSet
    //   161	360	21	localObject3	Object
    //   330	168	22	localPackageInfo	PackageInfo
    //   454	95	23	localLauncherActivityInfo	LauncherActivityInfo
    // Exception table:
    //   from	to	target	type
    //   215	225	578	finally
    //   229	239	578	finally
    //   243	253	578	finally
    //   257	268	578	finally
    //   272	283	578	finally
    //   287	296	578	finally
    //   300	313	578	finally
    //   317	332	578	finally
    //   341	355	578	finally
    //   359	366	578	finally
    //   370	387	578	finally
    //   394	409	578	finally
    //   416	426	578	finally
    //   430	440	578	finally
    //   444	456	578	finally
    //   460	467	578	finally
    //   474	511	578	finally
    //   519	543	578	finally
    //   546	554	578	finally
    //   215	225	582	android/database/sqlite/SQLiteException
    //   229	239	582	android/database/sqlite/SQLiteException
    //   243	253	582	android/database/sqlite/SQLiteException
    //   257	268	582	android/database/sqlite/SQLiteException
    //   272	283	582	android/database/sqlite/SQLiteException
    //   287	296	582	android/database/sqlite/SQLiteException
    //   300	313	582	android/database/sqlite/SQLiteException
    //   317	332	582	android/database/sqlite/SQLiteException
    //   341	355	582	android/database/sqlite/SQLiteException
    //   359	366	582	android/database/sqlite/SQLiteException
    //   370	387	582	android/database/sqlite/SQLiteException
    //   394	409	582	android/database/sqlite/SQLiteException
    //   416	426	582	android/database/sqlite/SQLiteException
    //   430	440	582	android/database/sqlite/SQLiteException
    //   444	456	582	android/database/sqlite/SQLiteException
    //   460	467	582	android/database/sqlite/SQLiteException
    //   151	211	594	finally
    //   151	211	600	android/database/sqlite/SQLiteException
    //   603	614	709	finally
    //   474	511	729	android/database/sqlite/SQLiteException
    //   519	543	729	android/database/sqlite/SQLiteException
    //   546	554	729	android/database/sqlite/SQLiteException
  }
  
  void addIconToDBAndMemCache(LauncherActivityInfo paramLauncherActivityInfo, PackageInfo paramPackageInfo, long paramLong, boolean paramBoolean)
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
          ((CacheEntry)localObject2).icon = LauncherIcons.createBadgedIconBitmap(getFullResIcon(paramLauncherActivityInfo), paramLauncherActivityInfo.getUser(), this.mContext, paramLauncherActivityInfo.getApplicationInfo().targetSdkVersion);
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
  
  protected CacheEntry cacheLocked(ComponentName paramComponentName, Provider<LauncherActivityInfo> paramProvider, UserHandle paramUserHandle, boolean paramBoolean1, boolean paramBoolean2)
  {
    Preconditions.assertWorkerThread();
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
      int i;
      if (getEntryFromDB(localComponentKey, localCacheEntry, paramBoolean2))
      {
        i = 0;
        paramComponentName = (ComponentName)localObject1;
      }
      else
      {
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
            i = j;
            paramComponentName = (ComponentName)localObject1;
          }
        }
      }
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
  
  public void getTitleAndIcon(ItemInfoWithIcon paramItemInfoWithIcon, boolean paramBoolean)
  {
    try
    {
      if (paramItemInfoWithIcon.getTargetComponent() == null)
      {
        paramItemInfoWithIcon.iconBitmap = getDefaultIcon(paramItemInfoWithIcon.user);
        paramItemInfoWithIcon.title = "";
        paramItemInfoWithIcon.contentDescription = "";
        paramItemInfoWithIcon.usingLowResIcon = false;
      }
      else
      {
        getTitleAndIcon(paramItemInfoWithIcon, new ActivityInfoProvider(paramItemInfoWithIcon.getIntent(), paramItemInfoWithIcon.user), true, paramBoolean);
      }
      return;
    }
    finally {}
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
  
  public void updateDbIcons(Set<String> paramSet)
  {
    this.mWorkerHandler.removeCallbacksAndMessages(ICON_UPDATE_TOKEN);
    this.mIconProvider.updateSystemStateString();
    Iterator localIterator = this.mUserManager.getUserProfiles().iterator();
    while (localIterator.hasNext())
    {
      UserHandle localUserHandle = (UserHandle)localIterator.next();
      List localList = this.mLauncherApps.getActivityList(null, localUserHandle);
      if (localList != null)
      {
        if (localList.isEmpty()) {
          return;
        }
        Object localObject;
        if (Process.myUserHandle().equals(localUserHandle)) {
          localObject = paramSet;
        } else {
          localObject = Collections.emptySet();
        }
        updateDBIcons(localUserHandle, localList, (Set)localObject);
      }
      else {}
    }
  }
  
  public IconLoadRequest updateIconInBackground(final ItemInfoUpdateReceiver paramItemInfoUpdateReceiver, final ItemInfoWithIcon paramItemInfoWithIcon)
  {
    paramItemInfoUpdateReceiver = new Runnable()
    {
      public void run()
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
          public void run()
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
  public void updateIconsForPkg(String paramString, UserHandle paramUserHandle)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_2
    //   5: invokevirtual 767	com/android/launcher3/IconCache:removeIconsForPkg	(Ljava/lang/String;Landroid/os/UserHandle;)V
    //   8: aload_0
    //   9: getfield 89	com/android/launcher3/IconCache:mPackageManager	Landroid/content/pm/PackageManager;
    //   12: aload_1
    //   13: sipush 8192
    //   16: invokevirtual 315	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   19: astore 5
    //   21: aload_0
    //   22: getfield 97	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   25: aload_2
    //   26: invokevirtual 375	com/android/launcher3/compat/UserManagerCompat:getSerialNumberForUser	(Landroid/os/UserHandle;)J
    //   29: lstore_3
    //   30: aload_0
    //   31: getfield 104	com/android/launcher3/IconCache:mLauncherApps	Lcom/android/launcher3/compat/LauncherAppsCompat;
    //   34: aload_1
    //   35: aload_2
    //   36: invokevirtual 743	com/android/launcher3/compat/LauncherAppsCompat:getActivityList	(Ljava/lang/String;Landroid/os/UserHandle;)Ljava/util/List;
    //   39: invokeinterface 557 1 0
    //   44: astore_1
    //   45: aload_1
    //   46: invokeinterface 533 1 0
    //   51: ifeq +35 -> 86
    //   54: aload_0
    //   55: aload_1
    //   56: invokeinterface 537 1 0
    //   61: checkcast 563	android/content/pm/LauncherActivityInfo
    //   64: aload 5
    //   66: lload_3
    //   67: iconst_0
    //   68: invokevirtual 769	com/android/launcher3/IconCache:addIconToDBAndMemCache	(Landroid/content/pm/LauncherActivityInfo;Landroid/content/pm/PackageInfo;JZ)V
    //   71: goto -26 -> 45
    //   74: astore_1
    //   75: ldc_w 424
    //   78: ldc_w 771
    //   81: aload_1
    //   82: invokestatic 432	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
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
    private static final int RELEASE_VERSION = true + (FeatureFlags.LAUNCHER3_DISABLE_ICON_NORMALIZATION ^ true);
    
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
        return;
      }
      if (!this.mAppsToAdd.isEmpty())
      {
        localLauncherActivityInfo = (LauncherActivityInfo)this.mAppsToAdd.pop();
        localObject = (PackageInfo)this.mPkgInfoMap.get(localLauncherActivityInfo.getComponentName().getPackageName());
        if (localObject != null) {
          IconCache.this.addIconToDBAndMemCache(localLauncherActivityInfo, (PackageInfo)localObject, this.mUserSerial, false);
        }
        if (!this.mAppsToAdd.isEmpty()) {
          scheduleNext();
        }
      }
    }
    
    public void scheduleNext()
    {
      IconCache.this.mWorkerHandler.postAtTime(this, IconCache.ICON_UPDATE_TOKEN, SystemClock.uptimeMillis() + 1L);
    }
  }
}
