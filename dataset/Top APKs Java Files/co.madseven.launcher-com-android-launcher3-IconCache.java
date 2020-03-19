package com.android.launcher3;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import com.android.launcher3.compat.LauncherActivityInfoCompat;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.compat.UserHandleCompat;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.model.PackageItemInfo;
import com.android.launcher3.util.ComponentKey;
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
  private static final String EMPTY_CLASS_NAME = ".";
  static final Object ICON_UPDATE_TOKEN = new Object();
  private static final int INITIAL_ICON_CACHE_CAPACITY = 50;
  private static final int LOW_RES_SCALE_FACTOR = 5;
  private static final String TAG = "Launcher.IconCache";
  private final int mActivityBgColor;
  private final HashMap<ComponentKey, CacheEntry> mCache = new HashMap(50);
  private final Context mContext;
  private final HashMap<UserHandleCompat, Bitmap> mDefaultIcons = new HashMap();
  final IconDB mIconDb;
  private final int mIconDpi;
  private IconProvider mIconProvider;
  private final LauncherAppsCompat mLauncherApps;
  private Canvas mLowResCanvas;
  private final BitmapFactory.Options mLowResOptions;
  private Paint mLowResPaint;
  final MainThreadExecutor mMainThreadExecutor = new MainThreadExecutor();
  private final int mPackageBgColor;
  private final PackageManager mPackageManager;
  final UserManagerCompat mUserManager;
  final Handler mWorkerHandler;
  
  public IconCache(Context paramContext, InvariantDeviceProfile paramInvariantDeviceProfile)
  {
    this.mContext = paramContext;
    this.mPackageManager = paramContext.getPackageManager();
    this.mUserManager = UserManagerCompat.getInstance(this.mContext);
    this.mLauncherApps = LauncherAppsCompat.getInstance(this.mContext);
    this.mIconDpi = paramInvariantDeviceProfile.fillResIconDpi;
    this.mIconDb = new IconDB(paramContext, paramInvariantDeviceProfile.iconBitmapSize);
    this.mLowResCanvas = new Canvas();
    this.mLowResPaint = new Paint(3);
    this.mIconProvider = IconProvider.loadByName(paramContext.getString(2131886419), paramContext);
    this.mWorkerHandler = new Handler(LauncherModel.getWorkerLooper());
    this.mActivityBgColor = paramContext.getResources().getColor(2131099834);
    paramContext = paramContext.obtainStyledAttributes(new int[] { 2130968709 });
    this.mPackageBgColor = paramContext.getColor(0, 0);
    paramContext.recycle();
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
  
  private CacheEntry cacheLocked(ComponentName paramComponentName, LauncherActivityInfoCompat paramLauncherActivityInfoCompat, UserHandleCompat paramUserHandleCompat, boolean paramBoolean1, boolean paramBoolean2)
  {
    ComponentKey localComponentKey = new ComponentKey(paramComponentName, paramUserHandleCompat);
    CacheEntry localCacheEntry2 = (CacheEntry)this.mCache.get(localComponentKey);
    CacheEntry localCacheEntry1;
    if (localCacheEntry2 != null)
    {
      localCacheEntry1 = localCacheEntry2;
      if (localCacheEntry2.isLowResIcon)
      {
        localCacheEntry1 = localCacheEntry2;
        if (paramBoolean2) {}
      }
    }
    else
    {
      localCacheEntry2 = new CacheEntry();
      this.mCache.put(localComponentKey, localCacheEntry2);
      if (!getEntryFromDB(localComponentKey, localCacheEntry2, paramBoolean2)) {
        if (paramLauncherActivityInfoCompat != null)
        {
          localCacheEntry2.icon = Utilities.createBadgedIconBitmap(this.mIconProvider.getIcon(paramLauncherActivityInfoCompat, this.mIconDpi), paramLauncherActivityInfoCompat.getUser(), this.mContext);
        }
        else
        {
          if (paramBoolean1)
          {
            paramComponentName = getEntryForPackageLocked(paramComponentName.getPackageName(), paramUserHandleCompat, false);
            if (paramComponentName != null)
            {
              localCacheEntry2.icon = paramComponentName.icon;
              localCacheEntry2.title = paramComponentName.title;
              localCacheEntry2.contentDescription = paramComponentName.contentDescription;
            }
          }
          if (localCacheEntry2.icon == null) {
            localCacheEntry2.icon = getDefaultIcon(paramUserHandleCompat);
          }
        }
      }
      localCacheEntry1 = localCacheEntry2;
      if (TextUtils.isEmpty(localCacheEntry2.title))
      {
        localCacheEntry1 = localCacheEntry2;
        if (paramLauncherActivityInfoCompat != null)
        {
          localCacheEntry2.title = paramLauncherActivityInfoCompat.getLabel();
          localCacheEntry2.contentDescription = this.mUserManager.getBadgedLabelForUser(localCacheEntry2.title, paramUserHandleCompat);
          localCacheEntry1 = localCacheEntry2;
        }
      }
    }
    return localCacheEntry1;
  }
  
  private Bitmap generateLowResIcon(Bitmap paramBitmap, int paramInt)
  {
    if (paramInt == 0) {
      return Bitmap.createScaledBitmap(paramBitmap, paramBitmap.getWidth() / 5, paramBitmap.getHeight() / 5, true);
    }
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth() / 5, paramBitmap.getHeight() / 5, Bitmap.Config.RGB_565);
    try
    {
      this.mLowResCanvas.setBitmap(localBitmap);
      this.mLowResCanvas.drawColor(paramInt);
      this.mLowResCanvas.drawBitmap(paramBitmap, new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight()), new Rect(0, 0, localBitmap.getWidth(), localBitmap.getHeight()), this.mLowResPaint);
      this.mLowResCanvas.setBitmap(null);
      return localBitmap;
    }
    finally {}
  }
  
  private CacheEntry getEntryForPackageLocked(String paramString, UserHandleCompat paramUserHandleCompat, boolean paramBoolean)
  {
    ComponentKey localComponentKey = getPackageKey(paramString, paramUserHandleCompat);
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
      if (!UserHandleCompat.myUserHandle().equals(paramUserHandleCompat)) {
        break label289;
      }
      i = 0;
    }
    catch (Throwable paramString)
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
    if (localObject != null)
    {
      localBitmap1 = Utilities.createBadgedIconBitmap(((ApplicationInfo)localObject).loadIcon(this.mPackageManager), paramUserHandleCompat, this.mContext);
      localBitmap2 = generateLowResIcon(localBitmap1, 0);
      localCacheEntry.title = ((ApplicationInfo)localObject).loadLabel(this.mPackageManager);
      localCacheEntry.contentDescription = this.mUserManager.getBadgedLabelForUser(localCacheEntry.title, paramUserHandleCompat);
      if (!paramBoolean) {
        break label297;
      }
      localObject = localBitmap2;
      localCacheEntry.icon = ((Bitmap)localObject);
      localCacheEntry.isLowResIcon = paramBoolean;
      addIconToDB(newContentValues(localBitmap1, localBitmap2, localCacheEntry.title.toString(), paramString), localComponentKey.componentName, localPackageInfo, this.mUserManager.getSerialNumberForUser(paramUserHandleCompat));
      i = j;
    }
    else
    {
      throw new PackageManager.NameNotFoundException("ApplicationInfo is null");
      i = 0;
    }
    localObject = localCacheEntry;
    if (i != 0)
    {
      this.mCache.put(localComponentKey, localCacheEntry);
      localObject = localCacheEntry;
    }
    return localObject;
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
    //   14: getfield 132	com/android/launcher3/IconCache:mIconDb	Lcom/android/launcher3/IconCache$IconDB;
    //   17: astore 9
    //   19: iload_3
    //   20: ifeq +273 -> 293
    //   23: ldc_w 441
    //   26: astore 5
    //   28: goto +3 -> 31
    //   31: aload 8
    //   33: astore 4
    //   35: aload_1
    //   36: getfield 426	com/android/launcher3/util/ComponentKey:componentName	Landroid/content/ComponentName;
    //   39: invokevirtual 226	android/content/ComponentName:flattenToString	()Ljava/lang/String;
    //   42: astore 10
    //   44: aload 8
    //   46: astore 4
    //   48: aload_0
    //   49: getfield 110	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   52: aload_1
    //   53: getfield 445	com/android/launcher3/util/ComponentKey:user	Lcom/android/launcher3/compat/UserHandleCompat;
    //   56: invokevirtual 430	com/android/launcher3/compat/UserManagerCompat:getSerialNumberForUser	(Lcom/android/launcher3/compat/UserHandleCompat;)J
    //   59: invokestatic 448	java/lang/Long:toString	(J)Ljava/lang/String;
    //   62: astore 11
    //   64: aload 8
    //   66: astore 4
    //   68: aload 9
    //   70: iconst_2
    //   71: anewarray 450	java/lang/String
    //   74: dup
    //   75: iconst_0
    //   76: aload 5
    //   78: aastore
    //   79: dup
    //   80: iconst_1
    //   81: ldc_w 452
    //   84: aastore
    //   85: ldc_w 454
    //   88: iconst_2
    //   89: anewarray 450	java/lang/String
    //   92: dup
    //   93: iconst_0
    //   94: aload 10
    //   96: aastore
    //   97: dup
    //   98: iconst_1
    //   99: aload 11
    //   101: aastore
    //   102: invokevirtual 458	com/android/launcher3/IconCache$IconDB:query	([Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   105: astore 5
    //   107: aload 5
    //   109: invokeinterface 464 1 0
    //   114: ifeq +103 -> 217
    //   117: aload 7
    //   119: astore 4
    //   121: iload_3
    //   122: ifeq +9 -> 131
    //   125: aload_0
    //   126: getfield 201	com/android/launcher3/IconCache:mLowResOptions	Landroid/graphics/BitmapFactory$Options;
    //   129: astore 4
    //   131: aload_2
    //   132: aload 5
    //   134: iconst_0
    //   135: aload 4
    //   137: invokestatic 468	com/android/launcher3/IconCache:loadIconNoResize	(Landroid/database/Cursor;ILandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   140: putfield 310	com/android/launcher3/IconCache$CacheEntry:icon	Landroid/graphics/Bitmap;
    //   143: aload_2
    //   144: iload_3
    //   145: putfield 282	com/android/launcher3/IconCache$CacheEntry:isLowResIcon	Z
    //   148: aload_2
    //   149: aload 5
    //   151: iconst_1
    //   152: invokeinterface 469 2 0
    //   157: putfield 321	com/android/launcher3/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   160: aload_2
    //   161: getfield 321	com/android/launcher3/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   164: ifnonnull +20 -> 184
    //   167: aload_2
    //   168: ldc_w 471
    //   171: putfield 321	com/android/launcher3/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   174: aload_2
    //   175: ldc_w 471
    //   178: putfield 324	com/android/launcher3/IconCache$CacheEntry:contentDescription	Ljava/lang/CharSequence;
    //   181: goto +22 -> 203
    //   184: aload_2
    //   185: aload_0
    //   186: getfield 110	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   189: aload_2
    //   190: getfield 321	com/android/launcher3/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   193: aload_1
    //   194: getfield 445	com/android/launcher3/util/ComponentKey:user	Lcom/android/launcher3/compat/UserHandleCompat;
    //   197: invokevirtual 342	com/android/launcher3/compat/UserManagerCompat:getBadgedLabelForUser	(Ljava/lang/CharSequence;Lcom/android/launcher3/compat/UserHandleCompat;)Ljava/lang/CharSequence;
    //   200: putfield 324	com/android/launcher3/IconCache$CacheEntry:contentDescription	Ljava/lang/CharSequence;
    //   203: aload 5
    //   205: ifnull +10 -> 215
    //   208: aload 5
    //   210: invokeinterface 474 1 0
    //   215: iconst_1
    //   216: ireturn
    //   217: aload 5
    //   219: ifnull +58 -> 277
    //   222: aload 5
    //   224: invokeinterface 474 1 0
    //   229: iconst_0
    //   230: ireturn
    //   231: astore_1
    //   232: aload 5
    //   234: astore 4
    //   236: goto +43 -> 279
    //   239: astore_2
    //   240: aload 5
    //   242: astore_1
    //   243: goto +11 -> 254
    //   246: astore_1
    //   247: goto +32 -> 279
    //   250: astore_2
    //   251: aload 6
    //   253: astore_1
    //   254: aload_1
    //   255: astore 4
    //   257: ldc 38
    //   259: ldc_w 476
    //   262: aload_2
    //   263: invokestatic 482	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   266: pop
    //   267: aload_1
    //   268: ifnull +9 -> 277
    //   271: aload_1
    //   272: invokeinterface 474 1 0
    //   277: iconst_0
    //   278: ireturn
    //   279: aload 4
    //   281: ifnull +10 -> 291
    //   284: aload 4
    //   286: invokeinterface 474 1 0
    //   291: aload_1
    //   292: athrow
    //   293: ldc_w 483
    //   296: astore 5
    //   298: goto -267 -> 31
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	301	0	this	IconCache
    //   0	301	1	paramComponentKey	ComponentKey
    //   0	301	2	paramCacheEntry	CacheEntry
    //   0	301	3	paramBoolean	boolean
    //   11	274	4	localObject1	Object
    //   26	271	5	localObject2	Object
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
    //   257	267	246	finally
    //   13	19	250	android/database/sqlite/SQLiteException
    //   35	44	250	android/database/sqlite/SQLiteException
    //   48	64	250	android/database/sqlite/SQLiteException
    //   68	107	250	android/database/sqlite/SQLiteException
  }
  
  private Drawable getFullResDefaultActivityIcon()
  {
    return getFullResIcon(Resources.getSystem(), 17629184);
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
  
  private Bitmap getNonNullIcon(CacheEntry paramCacheEntry, UserHandleCompat paramUserHandleCompat)
  {
    if (paramCacheEntry.icon == null) {
      return getDefaultIcon(paramUserHandleCompat);
    }
    return paramCacheEntry.icon;
  }
  
  private static ComponentKey getPackageKey(String paramString, UserHandleCompat paramUserHandleCompat)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(".");
    return new ComponentKey(new ComponentName(paramString, localStringBuilder.toString()), paramUserHandleCompat);
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
  
  private Bitmap makeDefaultIcon(UserHandleCompat paramUserHandleCompat)
  {
    return Utilities.createBadgedIconBitmap(getFullResDefaultActivityIcon(), paramUserHandleCompat, this.mContext);
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
  
  private void removeFromMemCacheLocked(String paramString, UserHandleCompat paramUserHandleCompat)
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.mCache.keySet().iterator();
    while (localIterator.hasNext())
    {
      ComponentKey localComponentKey = (ComponentKey)localIterator.next();
      if ((localComponentKey.componentName.getPackageName().equals(paramString)) && (localComponentKey.user.equals(paramUserHandleCompat))) {
        localHashSet.add(localComponentKey);
      }
    }
    paramString = localHashSet.iterator();
    while (paramString.hasNext())
    {
      paramUserHandleCompat = (ComponentKey)paramString.next();
      this.mCache.remove(paramUserHandleCompat);
    }
  }
  
  /* Error */
  private void updateDBIcons(UserHandleCompat paramUserHandleCompat, List<LauncherActivityInfoCompat> paramList, Set<String> paramSet)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 110	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   4: aload_1
    //   5: invokevirtual 430	com/android/launcher3/compat/UserManagerCompat:getSerialNumberForUser	(Lcom/android/launcher3/compat/UserHandleCompat;)J
    //   8: lstore 11
    //   10: aload_0
    //   11: getfield 94	com/android/launcher3/IconCache:mContext	Landroid/content/Context;
    //   14: invokevirtual 100	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   17: astore 18
    //   19: new 79	java/util/HashMap
    //   22: dup
    //   23: invokespecial 80	java/util/HashMap:<init>	()V
    //   26: astore 17
    //   28: aload 18
    //   30: sipush 8192
    //   33: invokevirtual 578	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   36: invokeinterface 581 1 0
    //   41: astore 18
    //   43: aload 18
    //   45: invokeinterface 560 1 0
    //   50: ifeq +31 -> 81
    //   53: aload 18
    //   55: invokeinterface 564 1 0
    //   60: checkcast 247	android/content/pm/PackageInfo
    //   63: astore 19
    //   65: aload 17
    //   67: aload 19
    //   69: getfield 584	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   72: aload 19
    //   74: invokevirtual 286	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   77: pop
    //   78: goto -35 -> 43
    //   81: new 79	java/util/HashMap
    //   84: dup
    //   85: invokespecial 80	java/util/HashMap:<init>	()V
    //   88: astore 18
    //   90: aload_2
    //   91: invokeinterface 581 1 0
    //   96: astore_2
    //   97: aload_2
    //   98: invokeinterface 560 1 0
    //   103: ifeq +30 -> 133
    //   106: aload_2
    //   107: invokeinterface 564 1 0
    //   112: checkcast 296	com/android/launcher3/compat/LauncherActivityInfoCompat
    //   115: astore 19
    //   117: aload 18
    //   119: aload 19
    //   121: invokevirtual 588	com/android/launcher3/compat/LauncherActivityInfoCompat:getComponentName	()Landroid/content/ComponentName;
    //   124: aload 19
    //   126: invokevirtual 286	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   129: pop
    //   130: goto -33 -> 97
    //   133: new 544	java/util/HashSet
    //   136: dup
    //   137: invokespecial 545	java/util/HashSet:<init>	()V
    //   140: astore 20
    //   142: new 590	java/util/Stack
    //   145: dup
    //   146: invokespecial 591	java/util/Stack:<init>	()V
    //   149: astore 19
    //   151: aload_0
    //   152: getfield 132	com/android/launcher3/IconCache:mIconDb	Lcom/android/launcher3/IconCache$IconDB;
    //   155: astore_2
    //   156: lload 11
    //   158: invokestatic 448	java/lang/Long:toString	(J)Ljava/lang/String;
    //   161: astore 21
    //   163: aload_2
    //   164: iconst_5
    //   165: anewarray 450	java/lang/String
    //   168: dup
    //   169: iconst_0
    //   170: ldc_w 593
    //   173: aastore
    //   174: dup
    //   175: iconst_1
    //   176: ldc -36
    //   178: aastore
    //   179: dup
    //   180: iconst_2
    //   181: ldc -11
    //   183: aastore
    //   184: dup
    //   185: iconst_3
    //   186: ldc -3
    //   188: aastore
    //   189: dup
    //   190: iconst_4
    //   191: ldc_w 536
    //   194: aastore
    //   195: ldc_w 595
    //   198: iconst_1
    //   199: anewarray 450	java/lang/String
    //   202: dup
    //   203: iconst_0
    //   204: aload 21
    //   206: aastore
    //   207: invokevirtual 458	com/android/launcher3/IconCache$IconDB:query	([Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   210: astore_2
    //   211: lload 11
    //   213: lstore 13
    //   215: aload_2
    //   216: ldc -36
    //   218: invokeinterface 599 2 0
    //   223: istore 4
    //   225: lload 11
    //   227: lstore 13
    //   229: aload_2
    //   230: ldc -11
    //   232: invokeinterface 599 2 0
    //   237: istore 6
    //   239: lload 11
    //   241: lstore 13
    //   243: aload_2
    //   244: ldc -3
    //   246: invokeinterface 599 2 0
    //   251: istore 5
    //   253: lload 11
    //   255: lstore 13
    //   257: aload_2
    //   258: ldc_w 593
    //   261: invokeinterface 599 2 0
    //   266: istore 7
    //   268: lload 11
    //   270: lstore 13
    //   272: aload_2
    //   273: ldc_w 536
    //   276: invokeinterface 599 2 0
    //   281: istore 8
    //   283: lload 11
    //   285: lstore 13
    //   287: aload_2
    //   288: invokeinterface 464 1 0
    //   293: ifeq +264 -> 557
    //   296: lload 11
    //   298: lstore 13
    //   300: aload_2
    //   301: iload 4
    //   303: invokeinterface 469 2 0
    //   308: invokestatic 603	android/content/ComponentName:unflattenFromString	(Ljava/lang/String;)Landroid/content/ComponentName;
    //   311: astore 21
    //   313: lload 11
    //   315: lstore 13
    //   317: aload 17
    //   319: aload 21
    //   321: invokevirtual 313	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   324: invokevirtual 279	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   327: checkcast 247	android/content/pm/PackageInfo
    //   330: astore 22
    //   332: aload 22
    //   334: ifnonnull +56 -> 390
    //   337: lload 11
    //   339: lstore 13
    //   341: aload_3
    //   342: aload 21
    //   344: invokevirtual 313	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   347: invokeinterface 606 2 0
    //   352: ifne +373 -> 725
    //   355: lload 11
    //   357: lstore 13
    //   359: aload_0
    //   360: aload 21
    //   362: aload_1
    //   363: invokevirtual 608	com/android/launcher3/IconCache:remove	(Landroid/content/ComponentName;Lcom/android/launcher3/compat/UserHandleCompat;)V
    //   366: lload 11
    //   368: lstore 13
    //   370: aload 20
    //   372: aload_2
    //   373: iload 7
    //   375: invokeinterface 611 2 0
    //   380: invokestatic 261	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   383: invokevirtual 568	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   386: pop
    //   387: goto +338 -> 725
    //   390: lload 11
    //   392: lstore 13
    //   394: aload 22
    //   396: getfield 402	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   399: getfield 614	android/content/pm/ApplicationInfo:flags	I
    //   402: ldc_w 615
    //   405: iand
    //   406: ifeq +6 -> 412
    //   409: goto +316 -> 725
    //   412: lload 11
    //   414: lstore 13
    //   416: aload_2
    //   417: iload 6
    //   419: invokeinterface 619 2 0
    //   424: lstore 15
    //   426: lload 11
    //   428: lstore 13
    //   430: aload_2
    //   431: iload 5
    //   433: invokeinterface 611 2 0
    //   438: istore 9
    //   440: lload 11
    //   442: lstore 13
    //   444: aload 18
    //   446: aload 21
    //   448: invokevirtual 572	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   451: checkcast 296	com/android/launcher3/compat/LauncherActivityInfoCompat
    //   454: astore 23
    //   456: lload 11
    //   458: lstore 13
    //   460: aload 22
    //   462: getfield 256	android/content/pm/PackageInfo:versionCode	I
    //   465: istore 10
    //   467: iload 9
    //   469: iload 10
    //   471: if_icmpne +261 -> 732
    //   474: lload 15
    //   476: aload 22
    //   478: getfield 251	android/content/pm/PackageInfo:lastUpdateTime	J
    //   481: lcmp
    //   482: ifne +32 -> 514
    //   485: aload_2
    //   486: iload 8
    //   488: invokeinterface 469 2 0
    //   493: aload_0
    //   494: getfield 155	com/android/launcher3/IconCache:mIconProvider	Lcom/android/launcher3/IconProvider;
    //   497: aload 22
    //   499: getfield 584	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   502: invokevirtual 540	com/android/launcher3/IconProvider:getIconSystemState	(Ljava/lang/String;)Ljava/lang/String;
    //   505: invokestatic 622	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   508: ifeq +6 -> 514
    //   511: goto +43 -> 554
    //   514: aload 23
    //   516: ifnonnull +30 -> 546
    //   519: aload_0
    //   520: aload 21
    //   522: aload_1
    //   523: invokevirtual 608	com/android/launcher3/IconCache:remove	(Landroid/content/ComponentName;Lcom/android/launcher3/compat/UserHandleCompat;)V
    //   526: aload 20
    //   528: aload_2
    //   529: iload 7
    //   531: invokeinterface 611 2 0
    //   536: invokestatic 261	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   539: invokevirtual 568	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   542: pop
    //   543: goto +11 -> 554
    //   546: aload 19
    //   548: aload 23
    //   550: invokevirtual 623	java/util/Stack:add	(Ljava/lang/Object;)Z
    //   553: pop
    //   554: goto -271 -> 283
    //   557: lload 11
    //   559: lstore 13
    //   561: aload_2
    //   562: ifnull +69 -> 631
    //   565: aload_2
    //   566: invokeinterface 474 1 0
    //   571: lload 11
    //   573: lstore 13
    //   575: goto +56 -> 631
    //   578: astore_1
    //   579: goto +134 -> 713
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
    //   597: goto +116 -> 713
    //   600: astore_3
    //   601: aconst_null
    //   602: astore_1
    //   603: ldc 38
    //   605: ldc_w 476
    //   608: aload_3
    //   609: invokestatic 482	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   612: pop
    //   613: lload 11
    //   615: lstore 13
    //   617: aload_1
    //   618: ifnull +13 -> 631
    //   621: aload_1
    //   622: invokeinterface 474 1 0
    //   627: lload 11
    //   629: lstore 13
    //   631: aload 20
    //   633: invokevirtual 625	java/util/HashSet:isEmpty	()Z
    //   636: ifne +19 -> 655
    //   639: aload_0
    //   640: getfield 132	com/android/launcher3/IconCache:mIconDb	Lcom/android/launcher3/IconCache$IconDB;
    //   643: ldc_w 593
    //   646: aload 20
    //   648: invokestatic 629	com/android/launcher3/Utilities:createDbSelectionQuery	(Ljava/lang/String;Ljava/lang/Iterable;)Ljava/lang/String;
    //   651: aconst_null
    //   652: invokevirtual 633	com/android/launcher3/IconCache$IconDB:delete	(Ljava/lang/String;[Ljava/lang/String;)V
    //   655: aload 18
    //   657: invokevirtual 634	java/util/HashMap:isEmpty	()Z
    //   660: ifeq +11 -> 671
    //   663: aload 19
    //   665: invokevirtual 635	java/util/Stack:isEmpty	()Z
    //   668: ifne +39 -> 707
    //   671: new 590	java/util/Stack
    //   674: dup
    //   675: invokespecial 591	java/util/Stack:<init>	()V
    //   678: astore_1
    //   679: aload_1
    //   680: aload 18
    //   682: invokevirtual 639	java/util/HashMap:values	()Ljava/util/Collection;
    //   685: invokevirtual 643	java/util/Stack:addAll	(Ljava/util/Collection;)Z
    //   688: pop
    //   689: new 19	com/android/launcher3/IconCache$SerializedIconUpdateTask
    //   692: dup
    //   693: aload_0
    //   694: lload 13
    //   696: aload 17
    //   698: aload_1
    //   699: aload 19
    //   701: invokespecial 646	com/android/launcher3/IconCache$SerializedIconUpdateTask:<init>	(Lcom/android/launcher3/IconCache;JLjava/util/HashMap;Ljava/util/Stack;Ljava/util/Stack;)V
    //   704: invokevirtual 649	com/android/launcher3/IconCache$SerializedIconUpdateTask:scheduleNext	()V
    //   707: return
    //   708: astore_3
    //   709: aload_1
    //   710: astore_2
    //   711: aload_3
    //   712: astore_1
    //   713: aload_2
    //   714: ifnull +9 -> 723
    //   717: aload_2
    //   718: invokeinterface 474 1 0
    //   723: aload_1
    //   724: athrow
    //   725: goto -442 -> 283
    //   728: astore_1
    //   729: goto -142 -> 587
    //   732: goto -218 -> 514
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	735	0	this	IconCache
    //   0	735	1	paramUserHandleCompat	UserHandleCompat
    //   0	735	2	paramList	List<LauncherActivityInfoCompat>
    //   0	735	3	paramSet	Set<String>
    //   223	79	4	i	int
    //   251	181	5	j	int
    //   237	181	6	k	int
    //   266	264	7	m	int
    //   281	206	8	n	int
    //   438	34	9	i1	int
    //   465	7	10	i2	int
    //   8	620	11	l1	long
    //   213	482	13	l2	long
    //   424	51	15	l3	long
    //   26	671	17	localHashMap	HashMap
    //   17	664	18	localObject1	Object
    //   63	637	19	localObject2	Object
    //   140	507	20	localHashSet	HashSet
    //   161	360	21	localObject3	Object
    //   330	168	22	localPackageInfo	PackageInfo
    //   454	95	23	localLauncherActivityInfoCompat	LauncherActivityInfoCompat
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
    //   603	613	708	finally
    //   474	511	728	android/database/sqlite/SQLiteException
    //   519	543	728	android/database/sqlite/SQLiteException
    //   546	554	728	android/database/sqlite/SQLiteException
  }
  
  void addIconToDBAndMemCache(LauncherActivityInfoCompat paramLauncherActivityInfoCompat, PackageInfo paramPackageInfo, long paramLong)
  {
    addIconToDB(updateCacheAndGetContentValues(paramLauncherActivityInfoCompat, false), paramLauncherActivityInfoCompat.getComponentName(), paramPackageInfo, paramLong);
  }
  
  public void cachePackageInstallInfo(String paramString, UserHandleCompat paramUserHandleCompat, Bitmap paramBitmap, CharSequence paramCharSequence)
  {
    try
    {
      removeFromMemCacheLocked(paramString, paramUserHandleCompat);
      ComponentKey localComponentKey = getPackageKey(paramString, paramUserHandleCompat);
      paramUserHandleCompat = (CacheEntry)this.mCache.get(localComponentKey);
      paramString = paramUserHandleCompat;
      if (paramUserHandleCompat == null)
      {
        paramString = new CacheEntry();
        this.mCache.put(localComponentKey, paramString);
      }
      if (!TextUtils.isEmpty(paramCharSequence)) {
        paramString.title = paramCharSequence;
      }
      if (paramBitmap != null) {
        paramString.icon = Utilities.createIconBitmap(paramBitmap, this.mContext);
      }
      return;
    }
    finally {}
  }
  
  public void clear()
  {
    try
    {
      this.mCache.clear();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public Bitmap getDefaultIcon(UserHandleCompat paramUserHandleCompat)
  {
    try
    {
      if (!this.mDefaultIcons.containsKey(paramUserHandleCompat)) {
        this.mDefaultIcons.put(paramUserHandleCompat, makeDefaultIcon(paramUserHandleCompat));
      }
      paramUserHandleCompat = (Bitmap)this.mDefaultIcons.get(paramUserHandleCompat);
      return paramUserHandleCompat;
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
  
  public Bitmap getIcon(Intent paramIntent, UserHandleCompat paramUserHandleCompat)
  {
    try
    {
      ComponentName localComponentName = paramIntent.getComponent();
      if (localComponentName == null)
      {
        paramIntent = getDefaultIcon(paramUserHandleCompat);
        return paramIntent;
      }
      paramIntent = cacheLocked(localComponentName, this.mLauncherApps.resolveActivity(paramIntent, paramUserHandleCompat), paramUserHandleCompat, true, false).icon;
      return paramIntent;
    }
    finally {}
  }
  
  public void getTitleAndIcon(AppInfo paramAppInfo, LauncherActivityInfoCompat paramLauncherActivityInfoCompat, boolean paramBoolean)
  {
    if (paramLauncherActivityInfoCompat == null) {}
    try
    {
      UserHandleCompat localUserHandleCompat = paramAppInfo.user;
      break label21;
      localUserHandleCompat = paramLauncherActivityInfoCompat.getUser();
      label21:
      paramLauncherActivityInfoCompat = cacheLocked(paramAppInfo.componentName, paramLauncherActivityInfoCompat, localUserHandleCompat, false, paramBoolean);
      paramAppInfo.title = Utilities.trim(paramLauncherActivityInfoCompat.title);
      paramAppInfo.contentDescription = paramLauncherActivityInfoCompat.contentDescription;
      paramAppInfo.iconBitmap = getNonNullIcon(paramLauncherActivityInfoCompat, localUserHandleCompat);
      paramAppInfo.usingLowResIcon = paramLauncherActivityInfoCompat.isLowResIcon;
      return;
    }
    finally
    {
      for (;;) {}
    }
    throw paramAppInfo;
  }
  
  public void getTitleAndIcon(ShortcutInfo paramShortcutInfo, ComponentName paramComponentName, LauncherActivityInfoCompat paramLauncherActivityInfoCompat, UserHandleCompat paramUserHandleCompat, boolean paramBoolean1, boolean paramBoolean2)
  {
    try
    {
      paramComponentName = cacheLocked(paramComponentName, paramLauncherActivityInfoCompat, paramUserHandleCompat, paramBoolean1, paramBoolean2);
      paramShortcutInfo.setIcon(getNonNullIcon(paramComponentName, paramUserHandleCompat));
      paramShortcutInfo.title = Utilities.trim(paramComponentName.title);
      paramShortcutInfo.contentDescription = paramComponentName.contentDescription;
      paramShortcutInfo.usingFallbackIcon = isDefaultIcon(paramComponentName.icon, paramUserHandleCompat);
      paramShortcutInfo.usingLowResIcon = paramComponentName.isLowResIcon;
      return;
    }
    finally
    {
      paramShortcutInfo = finally;
      throw paramShortcutInfo;
    }
  }
  
  public void getTitleAndIcon(ShortcutInfo paramShortcutInfo, Intent paramIntent, UserHandleCompat paramUserHandleCompat, boolean paramBoolean)
  {
    try
    {
      ComponentName localComponentName = paramIntent.getComponent();
      if (localComponentName == null)
      {
        paramShortcutInfo.setIcon(getDefaultIcon(paramUserHandleCompat));
        paramShortcutInfo.title = "";
        paramShortcutInfo.contentDescription = "";
        paramShortcutInfo.usingFallbackIcon = true;
        paramShortcutInfo.usingLowResIcon = false;
      }
      else
      {
        getTitleAndIcon(paramShortcutInfo, localComponentName, this.mLauncherApps.resolveActivity(paramIntent, paramUserHandleCompat), paramUserHandleCompat, true, paramBoolean);
      }
      return;
    }
    finally {}
  }
  
  public void getTitleAndIconForApp(PackageItemInfo paramPackageItemInfo, boolean paramBoolean)
  {
    try
    {
      CacheEntry localCacheEntry = getEntryForPackageLocked(paramPackageItemInfo.packageName, paramPackageItemInfo.user, paramBoolean);
      paramPackageItemInfo.title = Utilities.trim(localCacheEntry.title);
      paramPackageItemInfo.contentDescription = localCacheEntry.contentDescription;
      paramPackageItemInfo.iconBitmap = getNonNullIcon(localCacheEntry, paramPackageItemInfo.user);
      paramPackageItemInfo.usingLowResIcon = localCacheEntry.isLowResIcon;
      return;
    }
    finally
    {
      paramPackageItemInfo = finally;
      throw paramPackageItemInfo;
    }
  }
  
  public boolean isDefaultIcon(Bitmap paramBitmap, UserHandleCompat paramUserHandleCompat)
  {
    return this.mDefaultIcons.get(paramUserHandleCompat) == paramBitmap;
  }
  
  public void modifyIcon(ComponentName paramComponentName, Bitmap paramBitmap, String paramString)
  {
    if ((paramComponentName != null) && ((paramBitmap != null) || (paramString != null)))
    {
      Object localObject = UserHandleCompat.myUserHandle();
      remove(paramComponentName, (UserHandleCompat)localObject);
      long l = this.mUserManager.getSerialNumberForUser((UserHandleCompat)localObject);
      localObject = new ContentValues();
      if (paramBitmap != null)
      {
        paramBitmap = Utilities.createIconBitmap(paramBitmap, this.mContext);
        Bitmap localBitmap = generateLowResIcon(paramBitmap, 0);
        ((ContentValues)localObject).put("icon", Utilities.flattenBitmap(paramBitmap));
        ((ContentValues)localObject).put("icon_low_res", Utilities.flattenBitmap(localBitmap));
      }
      if (!TextUtils.isEmpty(paramString)) {
        ((ContentValues)localObject).put("label", paramString);
      }
      ((ContentValues)localObject).put("system_state", this.mIconProvider.getIconSystemState(paramComponentName.getPackageName()));
      ((ContentValues)localObject).put("componentName", paramComponentName.flattenToString());
      ((ContentValues)localObject).put("profileId", Long.valueOf(l));
      this.mIconDb.update((ContentValues)localObject, "componentName = ? AND profileId = ?", new String[] { paramComponentName.flattenToString(), Long.toString(l) });
    }
  }
  
  public void preloadIcon(ComponentName paramComponentName, Bitmap paramBitmap, int paramInt, String paramString)
  {
    try
    {
      this.mContext.getPackageManager().getActivityIcon(paramComponentName);
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
    modifyIcon(paramComponentName, paramBitmap, paramString);
  }
  
  public void remove(ComponentName paramComponentName, UserHandleCompat paramUserHandleCompat)
  {
    try
    {
      this.mCache.remove(new ComponentKey(paramComponentName, paramUserHandleCompat));
      return;
    }
    finally
    {
      paramComponentName = finally;
      throw paramComponentName;
    }
  }
  
  public void removeIconsForPkg(String paramString, UserHandleCompat paramUserHandleCompat)
  {
    try
    {
      removeFromMemCacheLocked(paramString, paramUserHandleCompat);
      long l = this.mUserManager.getSerialNumberForUser(paramUserHandleCompat);
      paramUserHandleCompat = this.mIconDb;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("/%");
      paramUserHandleCompat.delete("componentName LIKE ? AND profileId = ?", new String[] { localStringBuilder.toString(), Long.toString(l) });
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  ContentValues updateCacheAndGetContentValues(LauncherActivityInfoCompat paramLauncherActivityInfoCompat, boolean paramBoolean)
  {
    Object localObject3 = new ComponentKey(paramLauncherActivityInfoCompat.getComponentName(), paramLauncherActivityInfoCompat.getUser());
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (!paramBoolean)
    {
      localObject3 = (CacheEntry)this.mCache.get(localObject3);
      localObject1 = localObject2;
      if (localObject3 != null)
      {
        localObject1 = localObject2;
        if (!((CacheEntry)localObject3).isLowResIcon) {
          if (((CacheEntry)localObject3).icon == null) {
            localObject1 = localObject2;
          } else {
            localObject1 = localObject3;
          }
        }
      }
    }
    localObject2 = localObject1;
    if (localObject1 == null)
    {
      localObject2 = new CacheEntry();
      ((CacheEntry)localObject2).icon = Utilities.createBadgedIconBitmap(this.mIconProvider.getIcon(paramLauncherActivityInfoCompat, this.mIconDpi), paramLauncherActivityInfoCompat.getUser(), this.mContext);
    }
    ((CacheEntry)localObject2).title = paramLauncherActivityInfoCompat.getLabel();
    ((CacheEntry)localObject2).contentDescription = this.mUserManager.getBadgedLabelForUser(((CacheEntry)localObject2).title, paramLauncherActivityInfoCompat.getUser());
    this.mCache.put(new ComponentKey(paramLauncherActivityInfoCompat.getComponentName(), paramLauncherActivityInfoCompat.getUser()), localObject2);
    localObject1 = generateLowResIcon(((CacheEntry)localObject2).icon, 0);
    return newContentValues(((CacheEntry)localObject2).icon, (Bitmap)localObject1, ((CacheEntry)localObject2).title.toString(), paramLauncherActivityInfoCompat.getApplicationInfo().packageName);
  }
  
  public void updateDbIcons(Set<String> paramSet)
  {
    this.mWorkerHandler.removeCallbacksAndMessages(ICON_UPDATE_TOKEN);
    this.mIconProvider.updateSystemStateString();
    Iterator localIterator = this.mUserManager.getUserProfiles().iterator();
    while (localIterator.hasNext())
    {
      UserHandleCompat localUserHandleCompat = (UserHandleCompat)localIterator.next();
      List localList = this.mLauncherApps.getActivityList(null, localUserHandleCompat);
      if (localList != null)
      {
        if (localList.isEmpty()) {
          return;
        }
        Object localObject;
        if (UserHandleCompat.myUserHandle().equals(localUserHandleCompat)) {
          localObject = paramSet;
        } else {
          localObject = Collections.emptySet();
        }
        updateDBIcons(localUserHandleCompat, localList, (Set)localObject);
      }
      else {}
    }
  }
  
  public IconLoadRequest updateIconInBackground(final BubbleTextView paramBubbleTextView, final ItemInfo paramItemInfo)
  {
    paramBubbleTextView = new Runnable()
    {
      public void run()
      {
        if ((paramItemInfo instanceof AppInfo))
        {
          IconCache.this.getTitleAndIcon((AppInfo)paramItemInfo, null, false);
        }
        else
        {
          Object localObject;
          if ((paramItemInfo instanceof ShortcutInfo))
          {
            ShortcutInfo localShortcutInfo = (ShortcutInfo)paramItemInfo;
            IconCache localIconCache = IconCache.this;
            if (localShortcutInfo.promisedIntent != null) {
              localObject = localShortcutInfo.promisedIntent;
            } else {
              localObject = localShortcutInfo.intent;
            }
            localIconCache.getTitleAndIcon(localShortcutInfo, (Intent)localObject, localShortcutInfo.user, false);
          }
          else if ((paramItemInfo instanceof PackageItemInfo))
          {
            localObject = (PackageItemInfo)paramItemInfo;
            IconCache.this.getTitleAndIconForApp((PackageItemInfo)localObject, false);
          }
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
    this.mWorkerHandler.post(paramBubbleTextView);
    return new IconLoadRequest(paramBubbleTextView, this.mWorkerHandler);
  }
  
  /* Error */
  public void updateIconsForPkg(String paramString, UserHandleCompat paramUserHandleCompat)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_2
    //   5: invokevirtual 814	com/android/launcher3/IconCache:removeIconsForPkg	(Ljava/lang/String;Lcom/android/launcher3/compat/UserHandleCompat;)V
    //   8: aload_0
    //   9: getfield 102	com/android/launcher3/IconCache:mPackageManager	Landroid/content/pm/PackageManager;
    //   12: aload_1
    //   13: sipush 8192
    //   16: invokevirtual 398	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   19: astore 5
    //   21: aload_0
    //   22: getfield 110	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   25: aload_2
    //   26: invokevirtual 430	com/android/launcher3/compat/UserManagerCompat:getSerialNumberForUser	(Lcom/android/launcher3/compat/UserHandleCompat;)J
    //   29: lstore_3
    //   30: aload_0
    //   31: getfield 117	com/android/launcher3/IconCache:mLauncherApps	Lcom/android/launcher3/compat/LauncherAppsCompat;
    //   34: aload_1
    //   35: aload_2
    //   36: invokevirtual 790	com/android/launcher3/compat/LauncherAppsCompat:getActivityList	(Ljava/lang/String;Lcom/android/launcher3/compat/UserHandleCompat;)Ljava/util/List;
    //   39: invokeinterface 581 1 0
    //   44: astore_1
    //   45: aload_1
    //   46: invokeinterface 560 1 0
    //   51: ifeq +22 -> 73
    //   54: aload_0
    //   55: aload_1
    //   56: invokeinterface 564 1 0
    //   61: checkcast 296	com/android/launcher3/compat/LauncherActivityInfoCompat
    //   64: aload 5
    //   66: lload_3
    //   67: invokevirtual 816	com/android/launcher3/IconCache:addIconToDBAndMemCache	(Lcom/android/launcher3/compat/LauncherActivityInfoCompat;Landroid/content/pm/PackageInfo;J)V
    //   70: goto -25 -> 45
    //   73: aload_0
    //   74: monitorexit
    //   75: return
    //   76: astore_1
    //   77: ldc 38
    //   79: ldc_w 818
    //   82: aload_1
    //   83: invokestatic 482	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   86: pop
    //   87: aload_0
    //   88: monitorexit
    //   89: return
    //   90: astore_1
    //   91: aload_0
    //   92: monitorexit
    //   93: aload_1
    //   94: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	95	0	this	IconCache
    //   0	95	1	paramString	String
    //   0	95	2	paramUserHandleCompat	UserHandleCompat
    //   29	38	3	l	long
    //   19	46	5	localPackageInfo	PackageInfo
    // Exception table:
    //   from	to	target	type
    //   8	45	76	java/lang/Throwable
    //   45	70	76	java/lang/Throwable
    //   2	8	90	finally
    //   8	45	90	finally
    //   45	70	90	finally
    //   77	87	90	finally
  }
  
  public void updateTitleAndIcon(AppInfo paramAppInfo)
  {
    try
    {
      CacheEntry localCacheEntry = cacheLocked(paramAppInfo.componentName, null, paramAppInfo.user, false, paramAppInfo.usingLowResIcon);
      if ((localCacheEntry.icon != null) && (!isDefaultIcon(localCacheEntry.icon, paramAppInfo.user)))
      {
        paramAppInfo.title = Utilities.trim(localCacheEntry.title);
        paramAppInfo.contentDescription = localCacheEntry.contentDescription;
        paramAppInfo.iconBitmap = localCacheEntry.icon;
        paramAppInfo.usingLowResIcon = localCacheEntry.isLowResIcon;
      }
      return;
    }
    finally
    {
      paramAppInfo = finally;
      throw paramAppInfo;
    }
  }
  
  static class CacheEntry
  {
    public CharSequence contentDescription = "";
    public Bitmap icon;
    public boolean isLowResIcon;
    public CharSequence title = "";
    
    CacheEntry() {}
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
    private static final int DB_VERSION = 10;
    private static final String TABLE_NAME = "icons";
    
    public IconDB(Context paramContext, int paramInt) {}
    
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
  
  class SerializedIconUpdateTask
    implements Runnable
  {
    private final Stack<LauncherActivityInfoCompat> mAppsToAdd;
    private final Stack<LauncherActivityInfoCompat> mAppsToUpdate;
    private final HashMap<String, PackageInfo> mPkgInfoMap;
    private final HashSet<String> mUpdatedPackages = new HashSet();
    private final long mUserSerial;
    
    SerializedIconUpdateTask(HashMap<String, PackageInfo> paramHashMap, Stack<LauncherActivityInfoCompat> paramStack1, Stack<LauncherActivityInfoCompat> paramStack2)
    {
      this.mUserSerial = ???;
      this.mPkgInfoMap = paramStack1;
      this.mAppsToAdd = paramStack2;
      Object localObject;
      this.mAppsToUpdate = localObject;
    }
    
    public void run()
    {
      Object localObject3;
      if (!this.mAppsToUpdate.isEmpty())
      {
        LauncherActivityInfoCompat localLauncherActivityInfoCompat1 = (LauncherActivityInfoCompat)this.mAppsToUpdate.pop();
        localObject3 = localLauncherActivityInfoCompat1.getComponentName().getPackageName();
        PackageInfo localPackageInfo = (PackageInfo)this.mPkgInfoMap.get(localObject3);
        if (localPackageInfo != null) {
          synchronized (IconCache.this)
          {
            ContentValues localContentValues = IconCache.this.updateCacheAndGetContentValues(localLauncherActivityInfoCompat1, true);
            IconCache.this.addIconToDB(localContentValues, localLauncherActivityInfoCompat1.getComponentName(), localPackageInfo, this.mUserSerial);
            this.mUpdatedPackages.add(localObject3);
          }
        }
        if ((this.mAppsToUpdate.isEmpty()) && (!this.mUpdatedPackages.isEmpty())) {
          LauncherAppState.getInstance(IconCache.this.mContext).getModel().onPackageIconsUpdated(this.mUpdatedPackages, IconCache.this.mUserManager.getUserForSerialNumber(this.mUserSerial));
        }
        scheduleNext();
        return;
      }
      if (!this.mAppsToAdd.isEmpty())
      {
        LauncherActivityInfoCompat localLauncherActivityInfoCompat2 = (LauncherActivityInfoCompat)this.mAppsToAdd.pop();
        localObject3 = (PackageInfo)this.mPkgInfoMap.get(localLauncherActivityInfoCompat2.getComponentName().getPackageName());
        if (localObject3 != null) {
          synchronized (IconCache.this)
          {
            IconCache.this.addIconToDBAndMemCache(localLauncherActivityInfoCompat2, (PackageInfo)localObject3, this.mUserSerial);
          }
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
