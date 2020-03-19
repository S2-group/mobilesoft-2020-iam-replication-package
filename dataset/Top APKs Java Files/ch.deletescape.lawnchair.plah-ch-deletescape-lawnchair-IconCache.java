package ch.deletescape.lawnchair;

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
import android.os.UserHandle;
import android.text.TextUtils;
import ch.deletescape.lawnchair.compat.LauncherActivityInfoCompat;
import ch.deletescape.lawnchair.compat.LauncherAppsCompat;
import ch.deletescape.lawnchair.compat.UserManagerCompat;
import ch.deletescape.lawnchair.model.PackageItemInfo;
import ch.deletescape.lawnchair.pixelify.PixelIconProvider;
import ch.deletescape.lawnchair.preferences.IPreferenceProvider;
import ch.deletescape.lawnchair.util.ComponentKey;
import ch.deletescape.lawnchair.util.SQLiteCacheHelper;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Stack;

public class IconCache
{
  static final Object ICON_UPDATE_TOKEN = new Object();
  private final int mActivityBgColor;
  private final HashMap<ComponentKey, CacheEntry> mCache = new HashMap(50);
  private final Context mContext;
  private final HashMap<UserHandle, Bitmap> mDefaultIcons = new HashMap();
  final IconDB mIconDb;
  private final int mIconDpi;
  private final LauncherAppsCompat mLauncherApps;
  private Canvas mLowResCanvas;
  private final BitmapFactory.Options mLowResOptions;
  private Paint mLowResPaint;
  final MainThreadExecutor mMainThreadExecutor = new MainThreadExecutor();
  private final int mPackageBgColor;
  private final PackageManager mPackageManager;
  final UserManagerCompat mUserManager;
  final Handler mWorkerHandler;
  public PixelIconProvider pip;
  
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
    this.pip = new PixelIconProvider(paramContext);
    this.mWorkerHandler = new Handler(LauncherModel.getWorkerLooper());
    this.mActivityBgColor = paramContext.getResources().getColor(2131099835);
    paramContext = paramContext.obtainStyledAttributes(new int[] { 2130968708 });
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
  
  private CacheEntry cacheLocked(ComponentName paramComponentName, LauncherActivityInfoCompat paramLauncherActivityInfoCompat, UserHandle paramUserHandle, boolean paramBoolean1, boolean paramBoolean2)
  {
    ComponentKey localComponentKey = new ComponentKey(paramComponentName, paramUserHandle);
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
      if (!getEntryFromDB(localComponentKey, localCacheEntry2, paramBoolean2))
      {
        if (paramLauncherActivityInfoCompat != null) {}
        for (paramComponentName = Utilities.createBadgedIconBitmap(this.pip.getIcon(paramLauncherActivityInfoCompat, this.mIconDpi), paramLauncherActivityInfoCompat.getUser(), this.mContext);; paramComponentName = getDefaultIcon(paramUserHandle))
        {
          localCacheEntry2.icon = paramComponentName;
          break;
          if (paramBoolean1)
          {
            paramComponentName = getEntryForPackageLocked(paramComponentName.getPackageName(), paramUserHandle, false);
            if (paramComponentName != null)
            {
              localCacheEntry2.icon = paramComponentName.icon;
              localCacheEntry2.title = paramComponentName.title;
              localCacheEntry2.contentDescription = paramComponentName.contentDescription;
            }
          }
          if (localCacheEntry2.icon != null) {
            break;
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
          localCacheEntry2.contentDescription = this.mUserManager.getBadgedLabelForUser(localCacheEntry2.title, paramUserHandle);
          localCacheEntry1 = localCacheEntry2;
        }
      }
    }
    return localCacheEntry1;
  }
  
  private Bitmap generateLowResIcon(Bitmap paramBitmap, int paramInt)
  {
    int i = Math.max(1, paramBitmap.getWidth() / 5);
    int j = Math.max(1, paramBitmap.getHeight() / 5);
    if (paramInt == 0) {
      return Bitmap.createScaledBitmap(paramBitmap, i, j, true);
    }
    Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.RGB_565);
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
      if (!Utilities.myUserHandle().equals(paramUserHandle)) {
        break label285;
      }
      i = 0;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;)
      {
        PackageInfo localPackageInfo;
        Bitmap localBitmap;
        continue;
        i = 8192;
        continue;
        paramString = (String)localObject;
      }
    }
    localPackageInfo = this.mPackageManager.getPackageInfo(paramString, i);
    paramString = localPackageInfo.applicationInfo;
    if (paramString == null) {
      throw new PackageManager.NameNotFoundException("ApplicationInfo is null");
    }
    localObject = Utilities.createBadgedIconBitmap(paramString.loadIcon(this.mPackageManager), paramUserHandle, this.mContext);
    localBitmap = generateLowResIcon((Bitmap)localObject, this.mPackageBgColor);
    localCacheEntry.title = paramString.loadLabel(this.mPackageManager);
    localCacheEntry.contentDescription = this.mUserManager.getBadgedLabelForUser(localCacheEntry.title, paramUserHandle);
    if (paramBoolean)
    {
      paramString = localBitmap;
      localCacheEntry.icon = paramString;
      localCacheEntry.isLowResIcon = paramBoolean;
      addIconToDB(newContentValues((Bitmap)localObject, localBitmap, localCacheEntry.title.toString()), localComponentKey.componentName, localPackageInfo, this.mUserManager.getSerialNumberForUser(paramUserHandle));
      i = j;
      break label253;
      i = 0;
      label253:
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
    //   14: getfield 117	ch/deletescape/lawnchair/IconCache:mIconDb	Lch/deletescape/lawnchair/IconCache$IconDB;
    //   17: astore 9
    //   19: iload_3
    //   20: ifeq +276 -> 296
    //   23: ldc_w 422
    //   26: astore 5
    //   28: goto +3 -> 31
    //   31: aload 8
    //   33: astore 4
    //   35: aload_1
    //   36: getfield 414	ch/deletescape/lawnchair/util/ComponentKey:componentName	Landroid/content/ComponentName;
    //   39: invokevirtual 203	android/content/ComponentName:flattenToString	()Ljava/lang/String;
    //   42: astore 10
    //   44: aload 8
    //   46: astore 4
    //   48: aload_0
    //   49: getfield 95	ch/deletescape/lawnchair/IconCache:mUserManager	Lch/deletescape/lawnchair/compat/UserManagerCompat;
    //   52: aload_1
    //   53: getfield 426	ch/deletescape/lawnchair/util/ComponentKey:user	Landroid/os/UserHandle;
    //   56: invokevirtual 418	ch/deletescape/lawnchair/compat/UserManagerCompat:getSerialNumberForUser	(Landroid/os/UserHandle;)J
    //   59: invokestatic 429	java/lang/Long:toString	(J)Ljava/lang/String;
    //   62: astore 11
    //   64: aload 8
    //   66: astore 4
    //   68: aload 9
    //   70: iconst_2
    //   71: anewarray 431	java/lang/String
    //   74: dup
    //   75: iconst_0
    //   76: aload 5
    //   78: aastore
    //   79: dup
    //   80: iconst_1
    //   81: ldc_w 433
    //   84: aastore
    //   85: ldc_w 435
    //   88: iconst_2
    //   89: anewarray 431	java/lang/String
    //   92: dup
    //   93: iconst_0
    //   94: aload 10
    //   96: aastore
    //   97: dup
    //   98: iconst_1
    //   99: aload 11
    //   101: aastore
    //   102: invokevirtual 439	ch/deletescape/lawnchair/IconCache$IconDB:query	([Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   105: astore 5
    //   107: aload 5
    //   109: invokeinterface 445 1 0
    //   114: ifeq +105 -> 219
    //   117: aload 7
    //   119: astore 4
    //   121: iload_3
    //   122: ifeq +9 -> 131
    //   125: aload_0
    //   126: getfield 180	ch/deletescape/lawnchair/IconCache:mLowResOptions	Landroid/graphics/BitmapFactory$Options;
    //   129: astore 4
    //   131: aload_2
    //   132: aload 5
    //   134: iconst_0
    //   135: aload 4
    //   137: invokestatic 449	ch/deletescape/lawnchair/IconCache:loadIconNoResize	(Landroid/database/Cursor;ILandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   140: putfield 288	ch/deletescape/lawnchair/IconCache$CacheEntry:icon	Landroid/graphics/Bitmap;
    //   143: aload_2
    //   144: iload_3
    //   145: putfield 260	ch/deletescape/lawnchair/IconCache$CacheEntry:isLowResIcon	Z
    //   148: aload_2
    //   149: aload 5
    //   151: iconst_1
    //   152: invokeinterface 453 2 0
    //   157: putfield 299	ch/deletescape/lawnchair/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   160: aload_2
    //   161: getfield 299	ch/deletescape/lawnchair/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   164: ifnonnull +22 -> 186
    //   167: aload_2
    //   168: ldc_w 455
    //   171: putfield 299	ch/deletescape/lawnchair/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   174: ldc_w 455
    //   177: astore_1
    //   178: aload_2
    //   179: aload_1
    //   180: putfield 302	ch/deletescape/lawnchair/IconCache$CacheEntry:contentDescription	Ljava/lang/CharSequence;
    //   183: goto +22 -> 205
    //   186: aload_0
    //   187: getfield 95	ch/deletescape/lawnchair/IconCache:mUserManager	Lch/deletescape/lawnchair/compat/UserManagerCompat;
    //   190: aload_2
    //   191: getfield 299	ch/deletescape/lawnchair/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   194: aload_1
    //   195: getfield 426	ch/deletescape/lawnchair/util/ComponentKey:user	Landroid/os/UserHandle;
    //   198: invokevirtual 320	ch/deletescape/lawnchair/compat/UserManagerCompat:getBadgedLabelForUser	(Ljava/lang/CharSequence;Landroid/os/UserHandle;)Ljava/lang/CharSequence;
    //   201: astore_1
    //   202: goto -24 -> 178
    //   205: aload 5
    //   207: ifnull +10 -> 217
    //   210: aload 5
    //   212: invokeinterface 458 1 0
    //   217: iconst_1
    //   218: ireturn
    //   219: aload 5
    //   221: ifnull +59 -> 280
    //   224: aload 5
    //   226: invokeinterface 458 1 0
    //   231: iconst_0
    //   232: ireturn
    //   233: astore_1
    //   234: aload 5
    //   236: astore 4
    //   238: goto +44 -> 282
    //   241: astore_2
    //   242: aload 5
    //   244: astore_1
    //   245: goto +11 -> 256
    //   248: astore_1
    //   249: goto +33 -> 282
    //   252: astore_2
    //   253: aload 6
    //   255: astore_1
    //   256: aload_1
    //   257: astore 4
    //   259: ldc_w 460
    //   262: ldc_w 462
    //   265: aload_2
    //   266: invokestatic 468	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   269: pop
    //   270: aload_1
    //   271: ifnull +9 -> 280
    //   274: aload_1
    //   275: invokeinterface 458 1 0
    //   280: iconst_0
    //   281: ireturn
    //   282: aload 4
    //   284: ifnull +10 -> 294
    //   287: aload 4
    //   289: invokeinterface 458 1 0
    //   294: aload_1
    //   295: athrow
    //   296: ldc_w 469
    //   299: astore 5
    //   301: goto -270 -> 31
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	304	0	this	IconCache
    //   0	304	1	paramComponentKey	ComponentKey
    //   0	304	2	paramCacheEntry	CacheEntry
    //   0	304	3	paramBoolean	boolean
    //   11	277	4	localObject1	Object
    //   26	274	5	localObject2	Object
    //   4	250	6	localObject3	Object
    //   7	111	7	localObject4	Object
    //   1	64	8	localObject5	Object
    //   17	52	9	localIconDB	IconDB
    //   42	53	10	str1	String
    //   62	38	11	str2	String
    // Exception table:
    //   from	to	target	type
    //   107	117	233	finally
    //   125	131	233	finally
    //   131	174	233	finally
    //   178	183	233	finally
    //   186	202	233	finally
    //   107	117	241	android/database/sqlite/SQLiteException
    //   125	131	241	android/database/sqlite/SQLiteException
    //   131	174	241	android/database/sqlite/SQLiteException
    //   178	183	241	android/database/sqlite/SQLiteException
    //   186	202	241	android/database/sqlite/SQLiteException
    //   13	19	248	finally
    //   35	44	248	finally
    //   48	64	248	finally
    //   68	107	248	finally
    //   259	270	248	finally
    //   13	19	252	android/database/sqlite/SQLiteException
    //   35	44	252	android/database/sqlite/SQLiteException
    //   48	64	252	android/database/sqlite/SQLiteException
    //   68	107	252	android/database/sqlite/SQLiteException
  }
  
  private Drawable getFullResDefaultActivityIcon()
  {
    return getFullResIcon(Resources.getSystem(), 17629184);
  }
  
  private Drawable getFullResIcon(Resources paramResources, int paramInt)
  {
    try
    {
      paramResources = paramResources.getDrawableForDensity(paramInt, this.mIconDpi, null);
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
  
  private Bitmap getNonNullIcon(CacheEntry paramCacheEntry, UserHandle paramUserHandle)
  {
    if (paramCacheEntry.icon == null) {
      return getDefaultIcon(paramUserHandle);
    }
    return paramCacheEntry.icon;
  }
  
  private static ComponentKey getPackageKey(String paramString, UserHandle paramUserHandle)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(".");
    return new ComponentKey(new ComponentName(paramString, localStringBuilder.toString()), paramUserHandle);
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
  
  private Bitmap makeDefaultIcon(UserHandle paramUserHandle)
  {
    return Utilities.createBadgedIconBitmap(getFullResDefaultActivityIcon(), paramUserHandle, this.mContext);
  }
  
  private ContentValues newContentValues(Bitmap paramBitmap1, Bitmap paramBitmap2, String paramString)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("icon", Utilities.flattenBitmap(paramBitmap1));
    localContentValues.put("icon_low_res", Utilities.flattenBitmap(paramBitmap2));
    localContentValues.put("label", paramString);
    localContentValues.put("system_state", Locale.getDefault().toString());
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
  private void updateDBIcons(UserHandle paramUserHandle, List<LauncherActivityInfoCompat> paramList, Set<String> paramSet)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 95	ch/deletescape/lawnchair/IconCache:mUserManager	Lch/deletescape/lawnchair/compat/UserManagerCompat;
    //   4: aload_1
    //   5: invokevirtual 418	ch/deletescape/lawnchair/compat/UserManagerCompat:getSerialNumberForUser	(Landroid/os/UserHandle;)J
    //   8: lstore 11
    //   10: aload_0
    //   11: getfield 79	ch/deletescape/lawnchair/IconCache:mContext	Landroid/content/Context;
    //   14: invokevirtual 85	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   17: astore 16
    //   19: new 64	java/util/HashMap
    //   22: dup
    //   23: invokespecial 65	java/util/HashMap:<init>	()V
    //   26: astore 15
    //   28: aload 16
    //   30: sipush 8192
    //   33: invokevirtual 569	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   36: invokeinterface 572 1 0
    //   41: astore 16
    //   43: aload 16
    //   45: invokeinterface 551 1 0
    //   50: ifeq +31 -> 81
    //   53: aload 16
    //   55: invokeinterface 555 1 0
    //   60: checkcast 224	android/content/pm/PackageInfo
    //   63: astore 17
    //   65: aload 15
    //   67: aload 17
    //   69: getfield 576	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   72: aload 17
    //   74: invokevirtual 264	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   77: pop
    //   78: goto -35 -> 43
    //   81: new 64	java/util/HashMap
    //   84: dup
    //   85: invokespecial 65	java/util/HashMap:<init>	()V
    //   88: astore 18
    //   90: aload_2
    //   91: invokeinterface 572 1 0
    //   96: astore_2
    //   97: aload_2
    //   98: invokeinterface 551 1 0
    //   103: ifeq +30 -> 133
    //   106: aload_2
    //   107: invokeinterface 555 1 0
    //   112: checkcast 274	ch/deletescape/lawnchair/compat/LauncherActivityInfoCompat
    //   115: astore 16
    //   117: aload 18
    //   119: aload 16
    //   121: invokevirtual 580	ch/deletescape/lawnchair/compat/LauncherActivityInfoCompat:getComponentName	()Landroid/content/ComponentName;
    //   124: aload 16
    //   126: invokevirtual 264	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   129: pop
    //   130: goto -33 -> 97
    //   133: new 535	java/util/HashSet
    //   136: dup
    //   137: invokespecial 536	java/util/HashSet:<init>	()V
    //   140: astore 20
    //   142: new 582	java/util/Stack
    //   145: dup
    //   146: invokespecial 583	java/util/Stack:<init>	()V
    //   149: astore 19
    //   151: aload_0
    //   152: getfield 117	ch/deletescape/lawnchair/IconCache:mIconDb	Lch/deletescape/lawnchair/IconCache$IconDB;
    //   155: astore_2
    //   156: lload 11
    //   158: invokestatic 429	java/lang/Long:toString	(J)Ljava/lang/String;
    //   161: astore 16
    //   163: aload_2
    //   164: iconst_5
    //   165: anewarray 431	java/lang/String
    //   168: dup
    //   169: iconst_0
    //   170: ldc_w 585
    //   173: aastore
    //   174: dup
    //   175: iconst_1
    //   176: ldc -59
    //   178: aastore
    //   179: dup
    //   180: iconst_2
    //   181: ldc -34
    //   183: aastore
    //   184: dup
    //   185: iconst_3
    //   186: ldc -26
    //   188: aastore
    //   189: dup
    //   190: iconst_4
    //   191: ldc_w 524
    //   194: aastore
    //   195: ldc_w 587
    //   198: iconst_1
    //   199: anewarray 431	java/lang/String
    //   202: dup
    //   203: iconst_0
    //   204: aload 16
    //   206: aastore
    //   207: invokevirtual 439	ch/deletescape/lawnchair/IconCache$IconDB:query	([Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   210: astore 17
    //   212: lload 11
    //   214: lstore 13
    //   216: aload 15
    //   218: astore 16
    //   220: aload 17
    //   222: ldc -59
    //   224: invokeinterface 591 2 0
    //   229: istore 4
    //   231: lload 11
    //   233: lstore 13
    //   235: aload 15
    //   237: astore 16
    //   239: aload 17
    //   241: ldc -34
    //   243: invokeinterface 591 2 0
    //   248: istore 6
    //   250: lload 11
    //   252: lstore 13
    //   254: aload 15
    //   256: astore 16
    //   258: aload 17
    //   260: ldc -26
    //   262: invokeinterface 591 2 0
    //   267: istore 5
    //   269: lload 11
    //   271: lstore 13
    //   273: aload 15
    //   275: astore 16
    //   277: aload 17
    //   279: ldc_w 585
    //   282: invokeinterface 591 2 0
    //   287: istore 7
    //   289: lload 11
    //   291: lstore 13
    //   293: aload 15
    //   295: astore 16
    //   297: aload 17
    //   299: ldc_w 524
    //   302: invokeinterface 591 2 0
    //   307: istore 8
    //   309: aload 15
    //   311: astore_2
    //   312: lload 11
    //   314: lstore 13
    //   316: aload_2
    //   317: astore 16
    //   319: aload 17
    //   321: invokeinterface 445 1 0
    //   326: ifeq +259 -> 585
    //   329: lload 11
    //   331: lstore 13
    //   333: aload_2
    //   334: astore 16
    //   336: aload 17
    //   338: iload 4
    //   340: invokeinterface 453 2 0
    //   345: invokestatic 595	android/content/ComponentName:unflattenFromString	(Ljava/lang/String;)Landroid/content/ComponentName;
    //   348: astore 21
    //   350: lload 11
    //   352: lstore 13
    //   354: aload_2
    //   355: astore 16
    //   357: aload_2
    //   358: aload 21
    //   360: invokevirtual 291	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   363: invokevirtual 256	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   366: checkcast 224	android/content/pm/PackageInfo
    //   369: astore 22
    //   371: aload 22
    //   373: ifnonnull +57 -> 430
    //   376: aload 21
    //   378: invokevirtual 291	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   381: astore 15
    //   383: aload_3
    //   384: aload 15
    //   386: invokeinterface 598 2 0
    //   391: ifne +28 -> 419
    //   394: aload_0
    //   395: aload 21
    //   397: aload_1
    //   398: invokevirtual 600	ch/deletescape/lawnchair/IconCache:remove	(Landroid/content/ComponentName;Landroid/os/UserHandle;)V
    //   401: aload 20
    //   403: aload 17
    //   405: iload 7
    //   407: invokeinterface 603 2 0
    //   412: invokestatic 238	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   415: invokevirtual 559	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   418: pop
    //   419: goto -107 -> 312
    //   422: astore_1
    //   423: goto +4 -> 427
    //   426: astore_1
    //   427: goto +200 -> 627
    //   430: aload_2
    //   431: astore 15
    //   433: aload 22
    //   435: getfield 385	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   438: getfield 606	android/content/pm/ApplicationInfo:flags	I
    //   441: ldc_w 607
    //   444: iand
    //   445: ifeq +6 -> 451
    //   448: goto -29 -> 419
    //   451: aload 17
    //   453: iload 6
    //   455: invokeinterface 611 2 0
    //   460: lstore 13
    //   462: aload 17
    //   464: iload 5
    //   466: invokeinterface 603 2 0
    //   471: istore 9
    //   473: aload 18
    //   475: aload 21
    //   477: invokevirtual 563	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   480: checkcast 274	ch/deletescape/lawnchair/compat/LauncherActivityInfoCompat
    //   483: astore_2
    //   484: aload 22
    //   486: getfield 233	android/content/pm/PackageInfo:versionCode	I
    //   489: istore 10
    //   491: iload 9
    //   493: iload 10
    //   495: if_icmpne +285 -> 780
    //   498: lload 13
    //   500: aload 22
    //   502: getfield 228	android/content/pm/PackageInfo:lastUpdateTime	J
    //   505: lcmp
    //   506: ifne +27 -> 533
    //   509: aload 17
    //   511: iload 8
    //   513: invokeinterface 453 2 0
    //   518: invokestatic 530	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   521: invokevirtual 531	java/util/Locale:toString	()Ljava/lang/String;
    //   524: invokestatic 614	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   527: ifeq +6 -> 533
    //   530: goto +42 -> 572
    //   533: aload_2
    //   534: ifnonnull +31 -> 565
    //   537: aload_0
    //   538: aload 21
    //   540: aload_1
    //   541: invokevirtual 600	ch/deletescape/lawnchair/IconCache:remove	(Landroid/content/ComponentName;Landroid/os/UserHandle;)V
    //   544: aload 20
    //   546: aload 17
    //   548: iload 7
    //   550: invokeinterface 603 2 0
    //   555: invokestatic 238	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   558: invokevirtual 559	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   561: pop
    //   562: goto +10 -> 572
    //   565: aload 19
    //   567: aload_2
    //   568: invokevirtual 615	java/util/Stack:add	(Ljava/lang/Object;)Z
    //   571: pop
    //   572: aload 15
    //   574: astore_2
    //   575: goto -263 -> 312
    //   578: astore_1
    //   579: aload 15
    //   581: astore_2
    //   582: goto +45 -> 627
    //   585: aload_2
    //   586: astore_3
    //   587: lload 11
    //   589: lstore 13
    //   591: aload 17
    //   593: ifnull +87 -> 680
    //   596: aload 17
    //   598: invokeinterface 458 1 0
    //   603: aload_2
    //   604: astore_3
    //   605: lload 11
    //   607: lstore 13
    //   609: goto +71 -> 680
    //   612: astore_1
    //   613: aload 17
    //   615: astore_2
    //   616: goto +145 -> 761
    //   619: astore_1
    //   620: lload 13
    //   622: lstore 11
    //   624: aload 16
    //   626: astore_2
    //   627: aload_1
    //   628: astore_3
    //   629: aload 17
    //   631: astore_1
    //   632: goto +15 -> 647
    //   635: astore_1
    //   636: aconst_null
    //   637: astore_2
    //   638: goto +123 -> 761
    //   641: astore_3
    //   642: aload 15
    //   644: astore_2
    //   645: aconst_null
    //   646: astore_1
    //   647: ldc_w 460
    //   650: ldc_w 462
    //   653: aload_3
    //   654: invokestatic 468	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   657: pop
    //   658: aload_2
    //   659: astore_3
    //   660: lload 11
    //   662: lstore 13
    //   664: aload_1
    //   665: ifnull +15 -> 680
    //   668: aload_1
    //   669: invokeinterface 458 1 0
    //   674: lload 11
    //   676: lstore 13
    //   678: aload_2
    //   679: astore_3
    //   680: aload 20
    //   682: invokevirtual 617	java/util/HashSet:isEmpty	()Z
    //   685: ifne +19 -> 704
    //   688: aload_0
    //   689: getfield 117	ch/deletescape/lawnchair/IconCache:mIconDb	Lch/deletescape/lawnchair/IconCache$IconDB;
    //   692: ldc_w 585
    //   695: aload 20
    //   697: invokestatic 621	ch/deletescape/lawnchair/Utilities:createDbSelectionQuery	(Ljava/lang/String;Ljava/lang/Iterable;)Ljava/lang/String;
    //   700: aconst_null
    //   701: invokevirtual 625	ch/deletescape/lawnchair/IconCache$IconDB:delete	(Ljava/lang/String;[Ljava/lang/String;)V
    //   704: aload 18
    //   706: invokevirtual 626	java/util/HashMap:isEmpty	()Z
    //   709: ifeq +11 -> 720
    //   712: aload 19
    //   714: invokevirtual 627	java/util/Stack:isEmpty	()Z
    //   717: ifne +38 -> 755
    //   720: new 582	java/util/Stack
    //   723: dup
    //   724: invokespecial 583	java/util/Stack:<init>	()V
    //   727: astore_1
    //   728: aload_1
    //   729: aload 18
    //   731: invokevirtual 631	java/util/HashMap:values	()Ljava/util/Collection;
    //   734: invokevirtual 635	java/util/Stack:addAll	(Ljava/util/Collection;)Z
    //   737: pop
    //   738: new 19	ch/deletescape/lawnchair/IconCache$SerializedIconUpdateTask
    //   741: dup
    //   742: aload_0
    //   743: lload 13
    //   745: aload_3
    //   746: aload_1
    //   747: aload 19
    //   749: invokespecial 638	ch/deletescape/lawnchair/IconCache$SerializedIconUpdateTask:<init>	(Lch/deletescape/lawnchair/IconCache;JLjava/util/HashMap;Ljava/util/Stack;Ljava/util/Stack;)V
    //   752: invokevirtual 641	ch/deletescape/lawnchair/IconCache$SerializedIconUpdateTask:scheduleNext	()V
    //   755: return
    //   756: astore_3
    //   757: aload_1
    //   758: astore_2
    //   759: aload_3
    //   760: astore_1
    //   761: aload_2
    //   762: ifnull +9 -> 771
    //   765: aload_2
    //   766: invokeinterface 458 1 0
    //   771: aload_1
    //   772: athrow
    //   773: astore_1
    //   774: aload 15
    //   776: astore_2
    //   777: goto -150 -> 627
    //   780: goto -247 -> 533
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	783	0	this	IconCache
    //   0	783	1	paramUserHandle	UserHandle
    //   0	783	2	paramList	List<LauncherActivityInfoCompat>
    //   0	783	3	paramSet	Set<String>
    //   229	110	4	i	int
    //   267	198	5	j	int
    //   248	206	6	k	int
    //   287	262	7	m	int
    //   307	205	8	n	int
    //   471	25	9	i1	int
    //   489	7	10	i2	int
    //   8	667	11	l1	long
    //   214	530	13	l2	long
    //   26	749	15	localObject1	Object
    //   17	608	16	localObject2	Object
    //   63	567	17	localObject3	Object
    //   88	642	18	localHashMap	HashMap
    //   149	599	19	localStack	Stack
    //   140	556	20	localHashSet	HashSet
    //   348	191	21	localComponentName	ComponentName
    //   369	132	22	localPackageInfo	PackageInfo
    // Exception table:
    //   from	to	target	type
    //   383	419	422	android/database/sqlite/SQLiteException
    //   376	383	426	android/database/sqlite/SQLiteException
    //   433	448	578	android/database/sqlite/SQLiteException
    //   451	491	578	android/database/sqlite/SQLiteException
    //   220	231	612	finally
    //   239	250	612	finally
    //   258	269	612	finally
    //   277	289	612	finally
    //   297	309	612	finally
    //   319	329	612	finally
    //   336	350	612	finally
    //   357	371	612	finally
    //   376	383	612	finally
    //   383	419	612	finally
    //   433	448	612	finally
    //   451	491	612	finally
    //   498	530	612	finally
    //   537	562	612	finally
    //   565	572	612	finally
    //   220	231	619	android/database/sqlite/SQLiteException
    //   239	250	619	android/database/sqlite/SQLiteException
    //   258	269	619	android/database/sqlite/SQLiteException
    //   277	289	619	android/database/sqlite/SQLiteException
    //   297	309	619	android/database/sqlite/SQLiteException
    //   319	329	619	android/database/sqlite/SQLiteException
    //   336	350	619	android/database/sqlite/SQLiteException
    //   357	371	619	android/database/sqlite/SQLiteException
    //   151	212	635	finally
    //   151	212	641	android/database/sqlite/SQLiteException
    //   647	658	756	finally
    //   498	530	773	android/database/sqlite/SQLiteException
    //   537	562	773	android/database/sqlite/SQLiteException
    //   565	572	773	android/database/sqlite/SQLiteException
  }
  
  void addIconToDBAndMemCache(LauncherActivityInfoCompat paramLauncherActivityInfoCompat, PackageInfo paramPackageInfo, long paramLong)
  {
    addIconToDB(updateCacheAndGetContentValues(paramLauncherActivityInfoCompat, false), paramLauncherActivityInfoCompat.getComponentName(), paramPackageInfo, paramLong);
  }
  
  public void cachePackageInstallInfo(String paramString, UserHandle paramUserHandle, Bitmap paramBitmap, CharSequence paramCharSequence)
  {
    try
    {
      removeFromMemCacheLocked(paramString, paramUserHandle);
      ComponentKey localComponentKey = getPackageKey(paramString, paramUserHandle);
      paramUserHandle = (CacheEntry)this.mCache.get(localComponentKey);
      paramString = paramUserHandle;
      if (paramUserHandle == null)
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
    return paramLauncherActivityInfo.getIcon(this.mIconDpi);
  }
  
  public Drawable getFullResIcon(LauncherActivityInfoCompat paramLauncherActivityInfoCompat, boolean paramBoolean)
  {
    return this.pip.getIcon(paramLauncherActivityInfoCompat, this.mIconDpi);
  }
  
  public Bitmap getIcon(Intent paramIntent, UserHandle paramUserHandle)
  {
    try
    {
      ComponentName localComponentName = paramIntent.getComponent();
      if (localComponentName == null)
      {
        paramIntent = getDefaultIcon(paramUserHandle);
        return paramIntent;
      }
      paramIntent = cacheLocked(localComponentName, this.mLauncherApps.resolveActivity(paramIntent, paramUserHandle), paramUserHandle, true, false).icon;
      return paramIntent;
    }
    finally {}
  }
  
  public void getTitleAndIcon(AppInfo paramAppInfo, LauncherActivityInfoCompat paramLauncherActivityInfoCompat, boolean paramBoolean)
  {
    if (paramLauncherActivityInfoCompat == null) {}
    try
    {
      UserHandle localUserHandle = paramAppInfo.user;
      break label21;
      localUserHandle = paramLauncherActivityInfoCompat.getUser();
      label21:
      paramLauncherActivityInfoCompat = cacheLocked(paramAppInfo.componentName, paramLauncherActivityInfoCompat, localUserHandle, false, paramBoolean);
      paramAppInfo.originalTitle = Utilities.trim(paramLauncherActivityInfoCompat.title);
      String str = paramAppInfo.componentName.flattenToString();
      paramAppInfo.title = Utilities.getPrefs(this.mContext).itemAlias(str, paramAppInfo.originalTitle.toString());
      paramAppInfo.contentDescription = paramLauncherActivityInfoCompat.contentDescription;
      paramAppInfo.iconBitmap = getNonNullIcon(paramLauncherActivityInfoCompat, localUserHandle);
      paramAppInfo.usingLowResIcon = paramLauncherActivityInfoCompat.isLowResIcon;
      return;
    }
    finally
    {
      for (;;) {}
    }
    throw paramAppInfo;
  }
  
  public void getTitleAndIcon(ShortcutInfo paramShortcutInfo, ComponentName paramComponentName, LauncherActivityInfoCompat paramLauncherActivityInfoCompat, UserHandle paramUserHandle, boolean paramBoolean1, boolean paramBoolean2)
  {
    try
    {
      paramLauncherActivityInfoCompat = cacheLocked(paramComponentName, paramLauncherActivityInfoCompat, paramUserHandle, paramBoolean1, paramBoolean2);
      paramShortcutInfo.setIcon(getNonNullIcon(paramLauncherActivityInfoCompat, paramUserHandle));
      String str = Utilities.trim(paramLauncherActivityInfoCompat.title);
      paramComponentName = paramComponentName.flattenToString();
      paramShortcutInfo.title = Utilities.getPrefs(this.mContext).itemAlias(paramComponentName, str);
      paramShortcutInfo.contentDescription = paramLauncherActivityInfoCompat.contentDescription;
      paramShortcutInfo.usingFallbackIcon = isDefaultIcon(paramLauncherActivityInfoCompat.icon, paramUserHandle);
      paramShortcutInfo.usingLowResIcon = paramLauncherActivityInfoCompat.isLowResIcon;
      return;
    }
    finally
    {
      paramShortcutInfo = finally;
      throw paramShortcutInfo;
    }
  }
  
  public void getTitleAndIcon(ShortcutInfo paramShortcutInfo, Intent paramIntent, UserHandle paramUserHandle, boolean paramBoolean)
  {
    try
    {
      ComponentName localComponentName = paramIntent.getComponent();
      if (localComponentName == null)
      {
        paramShortcutInfo.setIcon(getDefaultIcon(paramUserHandle));
        paramShortcutInfo.title = "";
        paramShortcutInfo.contentDescription = "";
        paramShortcutInfo.usingFallbackIcon = true;
        paramShortcutInfo.usingLowResIcon = false;
      }
      else
      {
        getTitleAndIcon(paramShortcutInfo, localComponentName, this.mLauncherApps.resolveActivity(paramIntent, paramUserHandle), paramUserHandle, true, paramBoolean);
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
  
  public boolean isDefaultIcon(Bitmap paramBitmap, UserHandle paramUserHandle)
  {
    return this.mDefaultIcons.get(paramUserHandle) == paramBitmap;
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
      ((CacheEntry)localObject2).icon = Utilities.createBadgedIconBitmap(this.pip.getIcon(paramLauncherActivityInfoCompat, this.mIconDpi), paramLauncherActivityInfoCompat.getUser(), this.mContext);
    }
    ((CacheEntry)localObject2).title = paramLauncherActivityInfoCompat.getLabel();
    ((CacheEntry)localObject2).contentDescription = this.mUserManager.getBadgedLabelForUser(((CacheEntry)localObject2).title, paramLauncherActivityInfoCompat.getUser());
    this.mCache.put(new ComponentKey(paramLauncherActivityInfoCompat.getComponentName(), paramLauncherActivityInfoCompat.getUser()), localObject2);
    paramLauncherActivityInfoCompat = generateLowResIcon(((CacheEntry)localObject2).icon, this.mActivityBgColor);
    return newContentValues(((CacheEntry)localObject2).icon, paramLauncherActivityInfoCompat, ((CacheEntry)localObject2).title.toString());
  }
  
  public void updateDbIcons(Set<String> paramSet)
  {
    this.mWorkerHandler.removeCallbacksAndMessages(ICON_UPDATE_TOKEN);
    Iterator localIterator = this.mUserManager.getUserProfiles().iterator();
    UserHandle localUserHandle;
    do
    {
      for (;;)
      {
        if (!localIterator.hasNext()) {
          return;
        }
        localUserHandle = (UserHandle)localIterator.next();
        List localList = this.mLauncherApps.getActivityList(null, localUserHandle);
        if ((localList == null) || (localList.isEmpty())) {
          break;
        }
        Object localObject;
        if (Utilities.myUserHandle().equals(localUserHandle)) {
          localObject = paramSet;
        } else {
          localObject = Collections.emptySet();
        }
        updateDBIcons(localUserHandle, localList, (Set)localObject);
      }
    } while (!localUserHandle.equals(Utilities.myUserHandle()));
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
  public void updateIconsForPkg(String paramString, UserHandle paramUserHandle)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_2
    //   5: invokevirtual 800	ch/deletescape/lawnchair/IconCache:removeIconsForPkg	(Ljava/lang/String;Landroid/os/UserHandle;)V
    //   8: aload_0
    //   9: getfield 87	ch/deletescape/lawnchair/IconCache:mPackageManager	Landroid/content/pm/PackageManager;
    //   12: aload_1
    //   13: sipush 8192
    //   16: invokevirtual 381	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   19: astore 5
    //   21: aload_0
    //   22: getfield 95	ch/deletescape/lawnchair/IconCache:mUserManager	Lch/deletescape/lawnchair/compat/UserManagerCompat;
    //   25: aload_2
    //   26: invokevirtual 418	ch/deletescape/lawnchair/compat/UserManagerCompat:getSerialNumberForUser	(Landroid/os/UserHandle;)J
    //   29: lstore_3
    //   30: aload_0
    //   31: getfield 102	ch/deletescape/lawnchair/IconCache:mLauncherApps	Lch/deletescape/lawnchair/compat/LauncherAppsCompat;
    //   34: aload_1
    //   35: aload_2
    //   36: invokevirtual 776	ch/deletescape/lawnchair/compat/LauncherAppsCompat:getActivityList	(Ljava/lang/String;Landroid/os/UserHandle;)Ljava/util/List;
    //   39: invokeinterface 572 1 0
    //   44: astore_1
    //   45: aload_1
    //   46: invokeinterface 551 1 0
    //   51: ifeq +34 -> 85
    //   54: aload_0
    //   55: aload_1
    //   56: invokeinterface 555 1 0
    //   61: checkcast 274	ch/deletescape/lawnchair/compat/LauncherActivityInfoCompat
    //   64: aload 5
    //   66: lload_3
    //   67: invokevirtual 802	ch/deletescape/lawnchair/IconCache:addIconToDBAndMemCache	(Lch/deletescape/lawnchair/compat/LauncherActivityInfoCompat;Landroid/content/pm/PackageInfo;J)V
    //   70: goto -25 -> 45
    //   73: astore_1
    //   74: ldc_w 460
    //   77: ldc_w 804
    //   80: aload_1
    //   81: invokestatic 468	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
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
    //   8	45	73	android/content/pm/PackageManager$NameNotFoundException
    //   45	70	73	android/content/pm/PackageManager$NameNotFoundException
    //   2	8	88	finally
    //   8	45	88	finally
    //   45	70	88	finally
    //   74	85	88	finally
  }
  
  public void updateTitleAndIcon(AppInfo paramAppInfo)
  {
    try
    {
      CacheEntry localCacheEntry = cacheLocked(paramAppInfo.componentName, null, paramAppInfo.user, false, paramAppInfo.usingLowResIcon);
      if ((localCacheEntry.icon != null) && (!isDefaultIcon(localCacheEntry.icon, paramAppInfo.user)))
      {
        paramAppInfo.originalTitle = Utilities.trim(localCacheEntry.title);
        String str = paramAppInfo.componentName.flattenToString();
        paramAppInfo.title = Utilities.getPrefs(this.mContext).itemAlias(str, paramAppInfo.originalTitle.toString());
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
    public IconDB(Context paramContext, int paramInt)
    {
      super("app_icons.db", 720896 + paramInt, "icons");
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
          LauncherAppState.getInstance().getModel().onPackageIconsUpdated(this.mUpdatedPackages, IconCache.this.mUserManager.getUserForSerialNumber(this.mUserSerial));
        }
      }
      do
      {
        scheduleNext();
        return;
        if (this.mAppsToAdd.isEmpty()) {
          break;
        }
        LauncherActivityInfoCompat localLauncherActivityInfoCompat2 = (LauncherActivityInfoCompat)this.mAppsToAdd.pop();
        localObject3 = (PackageInfo)this.mPkgInfoMap.get(localLauncherActivityInfoCompat2.getComponentName().getPackageName());
        if (localObject3 != null) {
          synchronized (IconCache.this)
          {
            IconCache.this.addIconToDBAndMemCache(localLauncherActivityInfoCompat2, (PackageInfo)localObject3, this.mUserSerial);
          }
        }
      } while (!this.mAppsToAdd.isEmpty());
    }
    
    public void scheduleNext()
    {
      IconCache.this.mWorkerHandler.postAtTime(this, IconCache.ICON_UPDATE_TOKEN, SystemClock.uptimeMillis() + 1L);
    }
  }
}
