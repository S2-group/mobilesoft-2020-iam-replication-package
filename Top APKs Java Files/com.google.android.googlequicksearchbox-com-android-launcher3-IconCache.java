package com.android.launcher3;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
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
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.android.launcher3.compat.LauncherActivityInfoCompat;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.compat.UserHandleCompat;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.model.PackageItemInfo;
import com.android.launcher3.util.ComponentKey;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

public class IconCache
{
  public static final Object ICON_UPDATE_TOKEN = new Object();
  public final int mActivityBgColor;
  public final HashMap<ComponentKey, IconCache.CacheEntry> mCache = new HashMap(50);
  public final Context mContext;
  public final HashMap<UserHandleCompat, Bitmap> mDefaultIcons = new HashMap();
  public final IconCache.IconDB mIconDb;
  public final int mIconDpi;
  public final LauncherAppsCompat mLauncherApps;
  public Bitmap mLowResBitmap;
  public Canvas mLowResCanvas;
  public final BitmapFactory.Options mLowResOptions;
  public Paint mLowResPaint;
  public final MainThreadExecutor mMainThreadExecutor = new MainThreadExecutor();
  public final int mPackageBgColor;
  public final PackageManager mPackageManager;
  public String mSystemState;
  public final UserManagerCompat mUserManager;
  public final Handler mWorkerHandler;
  
  public IconCache(Context paramContext, InvariantDeviceProfile paramInvariantDeviceProfile)
  {
    this.mContext = paramContext;
    this.mPackageManager = paramContext.getPackageManager();
    this.mUserManager = UserManagerCompat.getInstance(this.mContext);
    this.mLauncherApps = LauncherAppsCompat.getInstance(this.mContext);
    this.mIconDpi = paramInvariantDeviceProfile.fillResIconDpi;
    this.mIconDb = new IconCache.IconDB(paramContext, paramInvariantDeviceProfile.iconBitmapSize);
    this.mWorkerHandler = new Handler(LauncherModel.sWorkerThread.getLooper());
    this.mActivityBgColor = paramContext.getResources().getColor(R.color.quantum_panel_bg_color);
    this.mPackageBgColor = paramContext.getResources().getColor(R.color.quantum_panel_bg_color_dark);
    this.mLowResOptions = new BitmapFactory.Options();
    this.mLowResOptions.inPreferredConfig = Bitmap.Config.RGB_565;
    updateSystemStateString();
  }
  
  private final void addIconToDB(ContentValues paramContentValues, ComponentName paramComponentName, PackageInfo paramPackageInfo, long paramLong)
  {
    paramContentValues.put("componentName", paramComponentName.flattenToString());
    paramContentValues.put("profileId", Long.valueOf(paramLong));
    paramContentValues.put("lastUpdated", Long.valueOf(paramPackageInfo.lastUpdateTime));
    paramContentValues.put("version", Integer.valueOf(paramPackageInfo.versionCode));
    this.mIconDb.insertOrReplace(paramContentValues);
  }
  
  private final IconCache.CacheEntry cacheLocked(ComponentName paramComponentName, LauncherActivityInfoCompat paramLauncherActivityInfoCompat, UserHandleCompat paramUserHandleCompat, boolean paramBoolean1, boolean paramBoolean2)
  {
    ComponentKey localComponentKey = new ComponentKey(paramComponentName, paramUserHandleCompat);
    IconCache.CacheEntry localCacheEntry2 = (IconCache.CacheEntry)this.mCache.get(localComponentKey);
    IconCache.CacheEntry localCacheEntry1;
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
      localCacheEntry2 = new IconCache.CacheEntry();
      this.mCache.put(localComponentKey, localCacheEntry2);
      if (!getEntryFromDB(localComponentKey, localCacheEntry2, paramBoolean2))
      {
        if (paramLauncherActivityInfoCompat == null) {
          break label170;
        }
        localCacheEntry2.icon = Utilities.createBadgedIconBitmap(paramLauncherActivityInfoCompat.getIcon(this.mIconDpi), paramLauncherActivityInfoCompat.getUser(), this.mContext);
      }
    }
    for (;;)
    {
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
      return localCacheEntry1;
      label170:
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
  
  private final IconCache.CacheEntry getEntryForPackageLocked(String paramString, UserHandleCompat paramUserHandleCompat, boolean paramBoolean)
  {
    ComponentKey localComponentKey = getPackageKey(paramString, paramUserHandleCompat);
    Object localObject2 = (IconCache.CacheEntry)this.mCache.get(localComponentKey);
    Object localObject1;
    int i;
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
        break label252;
      }
      try
      {
        if (UserHandleCompat.myUserHandle().equals(paramUserHandleCompat))
        {
          i = 0;
          paramString = this.mPackageManager.getPackageInfo(paramString, i);
          localObject2 = paramString.applicationInfo;
          if (localObject2 != null) {
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
      ((IconCache.CacheEntry)localObject1).icon = Utilities.createBadgedIconBitmap(((ApplicationInfo)localObject2).loadIcon(this.mPackageManager), paramUserHandleCompat, this.mContext);
      ((IconCache.CacheEntry)localObject1).title = ((ApplicationInfo)localObject2).loadLabel(this.mPackageManager);
      ((IconCache.CacheEntry)localObject1).contentDescription = this.mUserManager.getBadgedLabelForUser(((IconCache.CacheEntry)localObject1).title, paramUserHandleCompat);
      ((IconCache.CacheEntry)localObject1).isLowResIcon = false;
      addIconToDB(newContentValues(((IconCache.CacheEntry)localObject1).icon, ((IconCache.CacheEntry)localObject1).title.toString(), this.mPackageBgColor), localComponentKey.componentName, paramString, this.mUserManager.getSerialNumberForUser(paramUserHandleCompat));
      i = 1;
      continue;
      label252:
      i = 1;
    }
  }
  
  /* Error */
  private final boolean getEntryFromDB(ComponentKey paramComponentKey, IconCache.CacheEntry paramCacheEntry, boolean paramBoolean)
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
    //   14: getfield 105	com/android/launcher3/IconCache:mIconDb	Lcom/android/launcher3/IconCache$IconDB;
    //   17: astore 9
    //   19: iload_3
    //   20: ifeq +172 -> 192
    //   23: ldc_w 353
    //   26: astore 5
    //   28: aload 8
    //   30: astore 4
    //   32: aload_1
    //   33: getfield 343	com/android/launcher3/util/ComponentKey:componentName	Landroid/content/ComponentName;
    //   36: invokevirtual 173	android/content/ComponentName:flattenToString	()Ljava/lang/String;
    //   39: astore 10
    //   41: aload 8
    //   43: astore 4
    //   45: aload_0
    //   46: getfield 81	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   49: aload_1
    //   50: getfield 357	com/android/launcher3/util/ComponentKey:user	Lcom/android/launcher3/compat/UserHandleCompat;
    //   53: invokevirtual 347	com/android/launcher3/compat/UserManagerCompat:getSerialNumberForUser	(Lcom/android/launcher3/compat/UserHandleCompat;)J
    //   56: invokestatic 360	java/lang/Long:toString	(J)Ljava/lang/String;
    //   59: astore 11
    //   61: aload 8
    //   63: astore 4
    //   65: aload 9
    //   67: iconst_2
    //   68: anewarray 362	java/lang/String
    //   71: dup
    //   72: iconst_0
    //   73: aload 5
    //   75: aastore
    //   76: dup
    //   77: iconst_1
    //   78: ldc_w 364
    //   81: aastore
    //   82: ldc_w 366
    //   85: iconst_2
    //   86: anewarray 362	java/lang/String
    //   89: dup
    //   90: iconst_0
    //   91: aload 10
    //   93: aastore
    //   94: dup
    //   95: iconst_1
    //   96: aload 11
    //   98: aastore
    //   99: invokevirtual 370	com/android/launcher3/IconCache$IconDB:query	([Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   102: astore 5
    //   104: aload 5
    //   106: invokeinterface 376 1 0
    //   111: ifeq +141 -> 252
    //   114: aload 7
    //   116: astore 4
    //   118: iload_3
    //   119: ifeq +9 -> 128
    //   122: aload_0
    //   123: getfield 151	com/android/launcher3/IconCache:mLowResOptions	Landroid/graphics/BitmapFactory$Options;
    //   126: astore 4
    //   128: aload_2
    //   129: aload 5
    //   131: iconst_0
    //   132: aload 4
    //   134: invokestatic 380	com/android/launcher3/IconCache:loadIconNoResize	(Landroid/database/Cursor;ILandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   137: putfield 259	com/android/launcher3/IconCache$CacheEntry:icon	Landroid/graphics/Bitmap;
    //   140: aload_2
    //   141: iload_3
    //   142: putfield 232	com/android/launcher3/IconCache$CacheEntry:isLowResIcon	Z
    //   145: aload_2
    //   146: aload 5
    //   148: iconst_1
    //   149: invokeinterface 384 2 0
    //   154: putfield 263	com/android/launcher3/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   157: aload_2
    //   158: getfield 263	com/android/launcher3/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   161: ifnonnull +39 -> 200
    //   164: aload_2
    //   165: ldc_w 386
    //   168: putfield 263	com/android/launcher3/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   171: aload_2
    //   172: ldc_w 386
    //   175: putfield 280	com/android/launcher3/IconCache$CacheEntry:contentDescription	Ljava/lang/CharSequence;
    //   178: aload 5
    //   180: ifnull +10 -> 190
    //   183: aload 5
    //   185: invokeinterface 389 1 0
    //   190: iconst_1
    //   191: ireturn
    //   192: ldc_w 390
    //   195: astore 5
    //   197: goto -169 -> 28
    //   200: aload_2
    //   201: aload_0
    //   202: getfield 81	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   205: aload_2
    //   206: getfield 263	com/android/launcher3/IconCache$CacheEntry:title	Ljava/lang/CharSequence;
    //   209: aload_1
    //   210: getfield 357	com/android/launcher3/util/ComponentKey:user	Lcom/android/launcher3/compat/UserHandleCompat;
    //   213: invokevirtual 277	com/android/launcher3/compat/UserManagerCompat:getBadgedLabelForUser	(Ljava/lang/CharSequence;Lcom/android/launcher3/compat/UserHandleCompat;)Ljava/lang/CharSequence;
    //   216: putfield 280	com/android/launcher3/IconCache$CacheEntry:contentDescription	Ljava/lang/CharSequence;
    //   219: goto -41 -> 178
    //   222: astore_2
    //   223: aload 5
    //   225: astore_1
    //   226: aload_1
    //   227: astore 4
    //   229: ldc_w 392
    //   232: ldc_w 394
    //   235: aload_2
    //   236: invokestatic 400	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   239: pop
    //   240: aload_1
    //   241: ifnull +9 -> 250
    //   244: aload_1
    //   245: invokeinterface 389 1 0
    //   250: iconst_0
    //   251: ireturn
    //   252: aload 5
    //   254: ifnull -4 -> 250
    //   257: aload 5
    //   259: invokeinterface 389 1 0
    //   264: goto -14 -> 250
    //   267: astore_1
    //   268: aload 4
    //   270: ifnull +10 -> 280
    //   273: aload 4
    //   275: invokeinterface 389 1 0
    //   280: aload_1
    //   281: athrow
    //   282: astore_1
    //   283: aload 5
    //   285: astore 4
    //   287: goto -19 -> 268
    //   290: astore_2
    //   291: aload 6
    //   293: astore_1
    //   294: goto -68 -> 226
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	297	0	this	IconCache
    //   0	297	1	paramComponentKey	ComponentKey
    //   0	297	2	paramCacheEntry	IconCache.CacheEntry
    //   0	297	3	paramBoolean	boolean
    //   11	275	4	localObject1	Object
    //   26	258	5	localObject2	Object
    //   1	291	6	localObject3	Object
    //   7	108	7	localObject4	Object
    //   4	58	8	localObject5	Object
    //   17	49	9	localIconDB	IconCache.IconDB
    //   39	53	10	str1	String
    //   59	38	11	str2	String
    // Exception table:
    //   from	to	target	type
    //   104	114	222	android/database/sqlite/SQLiteException
    //   122	128	222	android/database/sqlite/SQLiteException
    //   128	178	222	android/database/sqlite/SQLiteException
    //   200	219	222	android/database/sqlite/SQLiteException
    //   13	19	267	finally
    //   32	41	267	finally
    //   45	61	267	finally
    //   65	104	267	finally
    //   229	240	267	finally
    //   104	114	282	finally
    //   122	128	282	finally
    //   128	178	282	finally
    //   200	219	282	finally
    //   13	19	290	android/database/sqlite/SQLiteException
    //   32	41	290	android/database/sqlite/SQLiteException
    //   45	61	290	android/database/sqlite/SQLiteException
    //   65	104	290	android/database/sqlite/SQLiteException
  }
  
  private final Drawable getFullResDefaultActivityIcon()
  {
    return getFullResIcon(Resources.getSystem(), 17629184);
  }
  
  private final Drawable getFullResIcon(Resources paramResources, int paramInt)
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
  
  private final Bitmap getNonNullIcon(IconCache.CacheEntry paramCacheEntry, UserHandleCompat paramUserHandleCompat)
  {
    if (paramCacheEntry.icon == null) {
      return getDefaultIcon(paramUserHandleCompat);
    }
    return paramCacheEntry.icon;
  }
  
  private static ComponentKey getPackageKey(String paramString, UserHandleCompat paramUserHandleCompat)
  {
    String str1 = String.valueOf(paramString);
    String str2 = String.valueOf(".");
    if (str2.length() != 0) {}
    for (str1 = str1.concat(str2);; str1 = new String(str1)) {
      return new ComponentKey(new ComponentName(paramString, str1), paramUserHandleCompat);
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
  
  private final ContentValues newContentValues(Bitmap paramBitmap, String paramString, int paramInt)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("icon", Utilities.flattenBitmap(paramBitmap));
    localContentValues.put("label", paramString);
    localContentValues.put("system_state", this.mSystemState);
    if (paramInt == 0)
    {
      localContentValues.put("icon_low_res", Utilities.flattenBitmap(Bitmap.createScaledBitmap(paramBitmap, paramBitmap.getWidth() / 5, paramBitmap.getHeight() / 5, true)));
      return localContentValues;
    }
    try
    {
      if (this.mLowResBitmap == null)
      {
        this.mLowResBitmap = Bitmap.createBitmap(paramBitmap.getWidth() / 5, paramBitmap.getHeight() / 5, Bitmap.Config.RGB_565);
        this.mLowResCanvas = new Canvas(this.mLowResBitmap);
        this.mLowResPaint = new Paint(3);
      }
      this.mLowResCanvas.drawColor(paramInt);
      this.mLowResCanvas.drawBitmap(paramBitmap, new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight()), new Rect(0, 0, this.mLowResBitmap.getWidth(), this.mLowResBitmap.getHeight()), this.mLowResPaint);
      localContentValues.put("icon_low_res", Utilities.flattenBitmap(this.mLowResBitmap));
      return localContentValues;
    }
    finally {}
  }
  
  private final void removeFromMemCacheLocked(String paramString, UserHandleCompat paramUserHandleCompat)
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
  
  private final void updateSystemStateString()
  {
    this.mSystemState = Locale.getDefault().toString();
  }
  
  final void addIconToDBAndMemCache(LauncherActivityInfoCompat paramLauncherActivityInfoCompat, PackageInfo paramPackageInfo, long paramLong)
  {
    addIconToDB(updateCacheAndGetContentValues(paramLauncherActivityInfoCompat, false), paramLauncherActivityInfoCompat.getComponentName(), paramPackageInfo, paramLong);
  }
  
  public final void cachePackageInstallInfo(String paramString, UserHandleCompat paramUserHandleCompat, Bitmap paramBitmap, CharSequence paramCharSequence)
  {
    try
    {
      removeFromMemCacheLocked(paramString, paramUserHandleCompat);
      ComponentKey localComponentKey = getPackageKey(paramString, paramUserHandleCompat);
      paramUserHandleCompat = (IconCache.CacheEntry)this.mCache.get(localComponentKey);
      paramString = paramUserHandleCompat;
      if (paramUserHandleCompat == null)
      {
        paramString = new IconCache.CacheEntry();
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
  
  public final Bitmap getDefaultIcon(UserHandleCompat paramUserHandleCompat)
  {
    try
    {
      if (!this.mDefaultIcons.containsKey(paramUserHandleCompat)) {
        this.mDefaultIcons.put(paramUserHandleCompat, Utilities.createBadgedIconBitmap(getFullResDefaultActivityIcon(), paramUserHandleCompat, this.mContext));
      }
      paramUserHandleCompat = (Bitmap)this.mDefaultIcons.get(paramUserHandleCompat);
      return paramUserHandleCompat;
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
  
  /* Error */
  public final Bitmap getIcon(android.content.Intent paramIntent, UserHandleCompat paramUserHandleCompat)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual 583	android/content/Intent:getComponent	()Landroid/content/ComponentName;
    //   6: astore_3
    //   7: aload_3
    //   8: ifnonnull +13 -> 21
    //   11: aload_0
    //   12: aload_2
    //   13: invokevirtual 291	com/android/launcher3/IconCache:getDefaultIcon	(Lcom/android/launcher3/compat/UserHandleCompat;)Landroid/graphics/Bitmap;
    //   16: astore_1
    //   17: aload_0
    //   18: monitorexit
    //   19: aload_1
    //   20: areturn
    //   21: aload_0
    //   22: aload_3
    //   23: aload_0
    //   24: getfield 88	com/android/launcher3/IconCache:mLauncherApps	Lcom/android/launcher3/compat/LauncherAppsCompat;
    //   27: aload_1
    //   28: aload_2
    //   29: invokevirtual 587	com/android/launcher3/compat/LauncherAppsCompat:resolveActivity	(Landroid/content/Intent;Lcom/android/launcher3/compat/UserHandleCompat;)Lcom/android/launcher3/compat/LauncherActivityInfoCompat;
    //   32: aload_2
    //   33: iconst_1
    //   34: iconst_0
    //   35: invokespecial 589	com/android/launcher3/IconCache:cacheLocked	(Landroid/content/ComponentName;Lcom/android/launcher3/compat/LauncherActivityInfoCompat;Lcom/android/launcher3/compat/UserHandleCompat;ZZ)Lcom/android/launcher3/IconCache$CacheEntry;
    //   38: getfield 259	com/android/launcher3/IconCache$CacheEntry:icon	Landroid/graphics/Bitmap;
    //   41: astore_1
    //   42: goto -25 -> 17
    //   45: astore_1
    //   46: aload_0
    //   47: monitorexit
    //   48: aload_1
    //   49: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	50	0	this	IconCache
    //   0	50	1	paramIntent	android.content.Intent
    //   0	50	2	paramUserHandleCompat	UserHandleCompat
    //   6	17	3	localComponentName	ComponentName
    // Exception table:
    //   from	to	target	type
    //   2	7	45	finally
    //   11	17	45	finally
    //   21	42	45	finally
  }
  
  public final void getTitleAndIcon(AppInfo paramAppInfo, LauncherActivityInfoCompat paramLauncherActivityInfoCompat, boolean paramBoolean)
  {
    if (paramLauncherActivityInfoCompat == null) {}
    for (;;)
    {
      try
      {
        localUserHandleCompat = paramAppInfo.user;
        paramLauncherActivityInfoCompat = cacheLocked(paramAppInfo.componentName, paramLauncherActivityInfoCompat, localUserHandleCompat, false, paramBoolean);
        paramAppInfo.title = Utilities.trim(paramLauncherActivityInfoCompat.title);
        paramAppInfo.iconBitmap = getNonNullIcon(paramLauncherActivityInfoCompat, localUserHandleCompat);
        paramAppInfo.contentDescription = paramLauncherActivityInfoCompat.contentDescription;
        paramAppInfo.usingLowResIcon = paramLauncherActivityInfoCompat.isLowResIcon;
        return;
      }
      finally {}
      UserHandleCompat localUserHandleCompat = paramLauncherActivityInfoCompat.getUser();
    }
  }
  
  public final void getTitleAndIcon(ShortcutInfo paramShortcutInfo, ComponentName paramComponentName, LauncherActivityInfoCompat paramLauncherActivityInfoCompat, UserHandleCompat paramUserHandleCompat, boolean paramBoolean1, boolean paramBoolean2)
  {
    try
    {
      paramComponentName = cacheLocked(paramComponentName, paramLauncherActivityInfoCompat, paramUserHandleCompat, paramBoolean1, paramBoolean2);
      paramShortcutInfo.mIcon = getNonNullIcon(paramComponentName, paramUserHandleCompat);
      paramShortcutInfo.title = Utilities.trim(paramComponentName.title);
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
  
  /* Error */
  public final void getTitleAndIcon(ShortcutInfo paramShortcutInfo, android.content.Intent paramIntent, UserHandleCompat paramUserHandleCompat, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_2
    //   3: invokevirtual 583	android/content/Intent:getComponent	()Landroid/content/ComponentName;
    //   6: astore 5
    //   8: aload 5
    //   10: ifnonnull +32 -> 42
    //   13: aload_1
    //   14: aload_0
    //   15: aload_3
    //   16: invokevirtual 291	com/android/launcher3/IconCache:getDefaultIcon	(Lcom/android/launcher3/compat/UserHandleCompat;)Landroid/graphics/Bitmap;
    //   19: putfield 615	com/android/launcher3/ShortcutInfo:mIcon	Landroid/graphics/Bitmap;
    //   22: aload_1
    //   23: ldc_w 386
    //   26: putfield 616	com/android/launcher3/ShortcutInfo:title	Ljava/lang/CharSequence;
    //   29: aload_1
    //   30: iconst_1
    //   31: putfield 623	com/android/launcher3/ShortcutInfo:usingFallbackIcon	Z
    //   34: aload_1
    //   35: iconst_0
    //   36: putfield 624	com/android/launcher3/ShortcutInfo:usingLowResIcon	Z
    //   39: aload_0
    //   40: monitorexit
    //   41: return
    //   42: aload_0
    //   43: aload_1
    //   44: aload 5
    //   46: aload_0
    //   47: getfield 88	com/android/launcher3/IconCache:mLauncherApps	Lcom/android/launcher3/compat/LauncherAppsCompat;
    //   50: aload_2
    //   51: aload_3
    //   52: invokevirtual 587	com/android/launcher3/compat/LauncherAppsCompat:resolveActivity	(Landroid/content/Intent;Lcom/android/launcher3/compat/UserHandleCompat;)Lcom/android/launcher3/compat/LauncherActivityInfoCompat;
    //   55: aload_3
    //   56: iconst_1
    //   57: iload 4
    //   59: invokevirtual 627	com/android/launcher3/IconCache:getTitleAndIcon	(Lcom/android/launcher3/ShortcutInfo;Landroid/content/ComponentName;Lcom/android/launcher3/compat/LauncherActivityInfoCompat;Lcom/android/launcher3/compat/UserHandleCompat;ZZ)V
    //   62: goto -23 -> 39
    //   65: astore_1
    //   66: aload_0
    //   67: monitorexit
    //   68: aload_1
    //   69: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	70	0	this	IconCache
    //   0	70	1	paramShortcutInfo	ShortcutInfo
    //   0	70	2	paramIntent	android.content.Intent
    //   0	70	3	paramUserHandleCompat	UserHandleCompat
    //   0	70	4	paramBoolean	boolean
    //   6	39	5	localComponentName	ComponentName
    // Exception table:
    //   from	to	target	type
    //   2	8	65	finally
    //   13	39	65	finally
    //   42	62	65	finally
  }
  
  public final void getTitleAndIconForApp(String paramString, UserHandleCompat paramUserHandleCompat, boolean paramBoolean, PackageItemInfo paramPackageItemInfo)
  {
    try
    {
      paramString = getEntryForPackageLocked(paramString, paramUserHandleCompat, paramBoolean);
      paramPackageItemInfo.iconBitmap = getNonNullIcon(paramString, paramUserHandleCompat);
      paramPackageItemInfo.title = Utilities.trim(paramString.title);
      paramPackageItemInfo.usingLowResIcon = paramString.isLowResIcon;
      paramPackageItemInfo.contentDescription = paramString.contentDescription;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public final boolean isDefaultIcon(Bitmap paramBitmap, UserHandleCompat paramUserHandleCompat)
  {
    return this.mDefaultIcons.get(paramUserHandleCompat) == paramBitmap;
  }
  
  public final void preloadIcon$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDLO6URJ5DPQ4SOBDCKTKOOBECHP6UQB45TJN4OBGD1KM6SPF89KN8RB1E0TKIJ3AC5R62BRCC5N6EBQJEHP6IRJ77D54OORFDKNM2RJ4E9NMIP1FDHGNARJ3D1IN4CPF95N7COBID5GMST24CLR6IOR5A1P6UPJ9DHIJMAAM0(ComponentName paramComponentName, Bitmap paramBitmap, String paramString, long paramLong, InvariantDeviceProfile paramInvariantDeviceProfile)
  {
    try
    {
      this.mContext.getPackageManager().getActivityIcon(paramComponentName);
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      paramBitmap = newContentValues(Bitmap.createScaledBitmap(paramBitmap, paramInvariantDeviceProfile.iconBitmapSize, paramInvariantDeviceProfile.iconBitmapSize, true), paramString, 0);
      paramBitmap.put("componentName", paramComponentName.flattenToString());
      paramBitmap.put("profileId", Long.valueOf(paramLong));
      this.mIconDb.insertOrReplace(paramBitmap);
    }
  }
  
  public final void remove(ComponentName paramComponentName, UserHandleCompat paramUserHandleCompat)
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
  
  public final void removeIconsForPkg(String paramString, UserHandleCompat paramUserHandleCompat)
  {
    try
    {
      removeFromMemCacheLocked(paramString, paramUserHandleCompat);
      long l = this.mUserManager.getSerialNumberForUser(paramUserHandleCompat);
      this.mIconDb.delete("componentName LIKE ? AND profileId = ?", new String[] { String.valueOf(paramString).concat("/%"), Long.toString(l) });
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  final ContentValues updateCacheAndGetContentValues(LauncherActivityInfoCompat paramLauncherActivityInfoCompat, boolean paramBoolean)
  {
    Object localObject1 = new ComponentKey(paramLauncherActivityInfoCompat.getComponentName(), paramLauncherActivityInfoCompat.getUser());
    Object localObject2;
    if (!paramBoolean)
    {
      localObject2 = (IconCache.CacheEntry)this.mCache.get(localObject1);
      if ((localObject2 != null) && (!((IconCache.CacheEntry)localObject2).isLowResIcon))
      {
        localObject1 = localObject2;
        if (((IconCache.CacheEntry)localObject2).icon != null) {
          break label59;
        }
      }
    }
    for (localObject1 = null;; localObject1 = null)
    {
      label59:
      localObject2 = localObject1;
      if (localObject1 == null)
      {
        localObject2 = new IconCache.CacheEntry();
        ((IconCache.CacheEntry)localObject2).icon = Utilities.createBadgedIconBitmap(paramLauncherActivityInfoCompat.getIcon(this.mIconDpi), paramLauncherActivityInfoCompat.getUser(), this.mContext);
      }
      ((IconCache.CacheEntry)localObject2).title = paramLauncherActivityInfoCompat.getLabel();
      ((IconCache.CacheEntry)localObject2).contentDescription = this.mUserManager.getBadgedLabelForUser(((IconCache.CacheEntry)localObject2).title, paramLauncherActivityInfoCompat.getUser());
      this.mCache.put(new ComponentKey(paramLauncherActivityInfoCompat.getComponentName(), paramLauncherActivityInfoCompat.getUser()), localObject2);
      return newContentValues(((IconCache.CacheEntry)localObject2).icon, ((IconCache.CacheEntry)localObject2).title.toString(), this.mActivityBgColor);
    }
  }
  
  /* Error */
  public final void updateDbIcons(Set<String> paramSet)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 124	com/android/launcher3/IconCache:mWorkerHandler	Landroid/os/Handler;
    //   4: getstatic 46	com/android/launcher3/IconCache:ICON_UPDATE_TOKEN	Ljava/lang/Object;
    //   7: invokevirtual 656	android/os/Handler:removeCallbacksAndMessages	(Ljava/lang/Object;)V
    //   10: aload_0
    //   11: invokespecial 163	com/android/launcher3/IconCache:updateSystemStateString	()V
    //   14: aload_0
    //   15: getfield 81	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   18: invokevirtual 660	com/android/launcher3/compat/UserManagerCompat:getUserProfiles	()Ljava/util/List;
    //   21: invokeinterface 663 1 0
    //   26: astore 15
    //   28: aload 15
    //   30: invokeinterface 522 1 0
    //   35: ifeq +42 -> 77
    //   38: aload 15
    //   40: invokeinterface 526 1 0
    //   45: checkcast 299	com/android/launcher3/compat/UserHandleCompat
    //   48: astore 18
    //   50: aload_0
    //   51: getfield 88	com/android/launcher3/IconCache:mLauncherApps	Lcom/android/launcher3/compat/LauncherAppsCompat;
    //   54: aconst_null
    //   55: aload 18
    //   57: invokevirtual 667	com/android/launcher3/compat/LauncherAppsCompat:getActivityList	(Ljava/lang/String;Lcom/android/launcher3/compat/UserHandleCompat;)Ljava/util/List;
    //   60: astore 12
    //   62: aload 12
    //   64: ifnull +13 -> 77
    //   67: aload 12
    //   69: invokeinterface 669 1 0
    //   74: ifeq +4 -> 78
    //   77: return
    //   78: invokestatic 302	com/android/launcher3/compat/UserHandleCompat:myUserHandle	()Lcom/android/launcher3/compat/UserHandleCompat;
    //   81: aload 18
    //   83: invokevirtual 306	com/android/launcher3/compat/UserHandleCompat:equals	(Ljava/lang/Object;)Z
    //   86: ifeq +88 -> 174
    //   89: aload_1
    //   90: astore 13
    //   92: aload_0
    //   93: getfield 81	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   96: aload 18
    //   98: invokevirtual 347	com/android/launcher3/compat/UserManagerCompat:getSerialNumberForUser	(Lcom/android/launcher3/compat/UserHandleCompat;)J
    //   101: lstore 8
    //   103: aload_0
    //   104: getfield 65	com/android/launcher3/IconCache:mContext	Landroid/content/Context;
    //   107: invokevirtual 71	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   110: astore 14
    //   112: new 50	java/util/HashMap
    //   115: dup
    //   116: invokespecial 51	java/util/HashMap:<init>	()V
    //   119: astore 16
    //   121: aload 14
    //   123: sipush 8192
    //   126: invokevirtual 673	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   129: invokeinterface 663 1 0
    //   134: astore 14
    //   136: aload 14
    //   138: invokeinterface 522 1 0
    //   143: ifeq +39 -> 182
    //   146: aload 14
    //   148: invokeinterface 526 1 0
    //   153: checkcast 194	android/content/pm/PackageInfo
    //   156: astore 17
    //   158: aload 16
    //   160: aload 17
    //   162: getfield 676	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   165: aload 17
    //   167: invokevirtual 236	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   170: pop
    //   171: goto -35 -> 136
    //   174: invokestatic 681	java/util/Collections:emptySet	()Ljava/util/Set;
    //   177: astore 13
    //   179: goto -87 -> 92
    //   182: new 50	java/util/HashMap
    //   185: dup
    //   186: invokespecial 51	java/util/HashMap:<init>	()V
    //   189: astore 17
    //   191: aload 12
    //   193: invokeinterface 663 1 0
    //   198: astore 12
    //   200: aload 12
    //   202: invokeinterface 522 1 0
    //   207: ifeq +31 -> 238
    //   210: aload 12
    //   212: invokeinterface 526 1 0
    //   217: checkcast 242	com/android/launcher3/compat/LauncherActivityInfoCompat
    //   220: astore 14
    //   222: aload 17
    //   224: aload 14
    //   226: invokevirtual 551	com/android/launcher3/compat/LauncherActivityInfoCompat:getComponentName	()Landroid/content/ComponentName;
    //   229: aload 14
    //   231: invokevirtual 236	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   234: pop
    //   235: goto -35 -> 200
    //   238: new 506	java/util/HashSet
    //   241: dup
    //   242: invokespecial 507	java/util/HashSet:<init>	()V
    //   245: astore 20
    //   247: new 683	java/util/Stack
    //   250: dup
    //   251: invokespecial 684	java/util/Stack:<init>	()V
    //   254: astore 19
    //   256: aconst_null
    //   257: astore 12
    //   259: aload_0
    //   260: getfield 105	com/android/launcher3/IconCache:mIconDb	Lcom/android/launcher3/IconCache$IconDB;
    //   263: astore 14
    //   265: lload 8
    //   267: invokestatic 360	java/lang/Long:toString	(J)Ljava/lang/String;
    //   270: astore 21
    //   272: aload 14
    //   274: iconst_5
    //   275: anewarray 362	java/lang/String
    //   278: dup
    //   279: iconst_0
    //   280: ldc_w 686
    //   283: aastore
    //   284: dup
    //   285: iconst_1
    //   286: ldc -89
    //   288: aastore
    //   289: dup
    //   290: iconst_2
    //   291: ldc -64
    //   293: aastore
    //   294: dup
    //   295: iconst_3
    //   296: ldc -56
    //   298: aastore
    //   299: dup
    //   300: iconst_4
    //   301: ldc_w 458
    //   304: aastore
    //   305: ldc_w 688
    //   308: iconst_1
    //   309: anewarray 362	java/lang/String
    //   312: dup
    //   313: iconst_0
    //   314: aload 21
    //   316: aastore
    //   317: invokevirtual 370	com/android/launcher3/IconCache$IconDB:query	([Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   320: astore 14
    //   322: aload 14
    //   324: astore 12
    //   326: aload 12
    //   328: ldc -89
    //   330: invokeinterface 692 2 0
    //   335: istore_2
    //   336: aload 12
    //   338: ldc -64
    //   340: invokeinterface 692 2 0
    //   345: istore_3
    //   346: aload 12
    //   348: ldc -56
    //   350: invokeinterface 692 2 0
    //   355: istore 4
    //   357: aload 12
    //   359: ldc_w 686
    //   362: invokeinterface 692 2 0
    //   367: istore 5
    //   369: aload 12
    //   371: ldc_w 458
    //   374: invokeinterface 692 2 0
    //   379: istore 6
    //   381: aload 12
    //   383: invokeinterface 376 1 0
    //   388: ifeq +336 -> 724
    //   391: aload 12
    //   393: iload_2
    //   394: invokeinterface 384 2 0
    //   399: invokestatic 696	android/content/ComponentName:unflattenFromString	(Ljava/lang/String;)Landroid/content/ComponentName;
    //   402: astore 14
    //   404: aload 16
    //   406: aload 14
    //   408: invokevirtual 283	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   411: invokevirtual 226	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   414: checkcast 194	android/content/pm/PackageInfo
    //   417: astore 21
    //   419: aload 21
    //   421: ifnonnull +155 -> 576
    //   424: aload 13
    //   426: aload 14
    //   428: invokevirtual 283	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   431: invokeinterface 699 2 0
    //   436: ifne -55 -> 381
    //   439: aload_0
    //   440: aload 14
    //   442: aload 18
    //   444: invokevirtual 701	com/android/launcher3/IconCache:remove	(Landroid/content/ComponentName;Lcom/android/launcher3/compat/UserHandleCompat;)V
    //   447: aload 20
    //   449: aload 12
    //   451: iload 5
    //   453: invokeinterface 704 2 0
    //   458: invokestatic 208	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   461: invokevirtual 530	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   464: pop
    //   465: goto -84 -> 381
    //   468: astore 13
    //   470: ldc_w 392
    //   473: ldc_w 394
    //   476: aload 13
    //   478: invokestatic 400	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   481: pop
    //   482: aload 12
    //   484: ifnull +10 -> 494
    //   487: aload 12
    //   489: invokeinterface 389 1 0
    //   494: aload 20
    //   496: invokevirtual 705	java/util/HashSet:isEmpty	()Z
    //   499: ifne +19 -> 518
    //   502: aload_0
    //   503: getfield 105	com/android/launcher3/IconCache:mIconDb	Lcom/android/launcher3/IconCache$IconDB;
    //   506: ldc_w 686
    //   509: aload 20
    //   511: invokestatic 709	com/android/launcher3/Utilities:createDbSelectionQuery	(Ljava/lang/String;Ljava/lang/Iterable;)Ljava/lang/String;
    //   514: aconst_null
    //   515: invokevirtual 650	com/android/launcher3/IconCache$IconDB:delete	(Ljava/lang/String;[Ljava/lang/String;)V
    //   518: aload 17
    //   520: invokevirtual 710	java/util/HashMap:isEmpty	()Z
    //   523: ifeq +11 -> 534
    //   526: aload 19
    //   528: invokevirtual 711	java/util/Stack:isEmpty	()Z
    //   531: ifne -503 -> 28
    //   534: new 683	java/util/Stack
    //   537: dup
    //   538: invokespecial 684	java/util/Stack:<init>	()V
    //   541: astore 12
    //   543: aload 12
    //   545: aload 17
    //   547: invokevirtual 715	java/util/HashMap:values	()Ljava/util/Collection;
    //   550: invokevirtual 719	java/util/Stack:addAll	(Ljava/util/Collection;)Z
    //   553: pop
    //   554: new 721	com/android/launcher3/IconCache$SerializedIconUpdateTask
    //   557: dup
    //   558: aload_0
    //   559: lload 8
    //   561: aload 16
    //   563: aload 12
    //   565: aload 19
    //   567: invokespecial 724	com/android/launcher3/IconCache$SerializedIconUpdateTask:<init>	(Lcom/android/launcher3/IconCache;JLjava/util/HashMap;Ljava/util/Stack;Ljava/util/Stack;)V
    //   570: invokevirtual 727	com/android/launcher3/IconCache$SerializedIconUpdateTask:scheduleNext	()V
    //   573: goto -545 -> 28
    //   576: aload 21
    //   578: getfield 316	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   581: getfield 730	android/content/pm/ApplicationInfo:flags	I
    //   584: ldc_w 731
    //   587: iand
    //   588: ifne -207 -> 381
    //   591: aload 12
    //   593: iload_3
    //   594: invokeinterface 735 2 0
    //   599: lstore 10
    //   601: aload 12
    //   603: iload 4
    //   605: invokeinterface 704 2 0
    //   610: istore 7
    //   612: aload 17
    //   614: aload 14
    //   616: invokevirtual 534	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   619: checkcast 242	com/android/launcher3/compat/LauncherActivityInfoCompat
    //   622: astore 22
    //   624: iload 7
    //   626: aload 21
    //   628: getfield 203	android/content/pm/PackageInfo:versionCode	I
    //   631: if_icmpne +33 -> 664
    //   634: lload 10
    //   636: aload 21
    //   638: getfield 198	android/content/pm/PackageInfo:lastUpdateTime	J
    //   641: lcmp
    //   642: ifne +22 -> 664
    //   645: aload_0
    //   646: getfield 460	com/android/launcher3/IconCache:mSystemState	Ljava/lang/String;
    //   649: aload 12
    //   651: iload 6
    //   653: invokeinterface 384 2 0
    //   658: invokestatic 738	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   661: ifne -280 -> 381
    //   664: aload 22
    //   666: ifnonnull +47 -> 713
    //   669: aload_0
    //   670: aload 14
    //   672: aload 18
    //   674: invokevirtual 701	com/android/launcher3/IconCache:remove	(Landroid/content/ComponentName;Lcom/android/launcher3/compat/UserHandleCompat;)V
    //   677: aload 20
    //   679: aload 12
    //   681: iload 5
    //   683: invokeinterface 704 2 0
    //   688: invokestatic 208	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   691: invokevirtual 530	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   694: pop
    //   695: goto -314 -> 381
    //   698: astore_1
    //   699: aload 12
    //   701: ifnull +10 -> 711
    //   704: aload 12
    //   706: invokeinterface 389 1 0
    //   711: aload_1
    //   712: athrow
    //   713: aload 19
    //   715: aload 22
    //   717: invokevirtual 739	java/util/Stack:add	(Ljava/lang/Object;)Z
    //   720: pop
    //   721: goto -340 -> 381
    //   724: aload 12
    //   726: ifnull -232 -> 494
    //   729: aload 12
    //   731: invokeinterface 389 1 0
    //   736: goto -242 -> 494
    //   739: astore_1
    //   740: aconst_null
    //   741: astore 12
    //   743: goto -44 -> 699
    //   746: astore_1
    //   747: goto -48 -> 699
    //   750: astore 13
    //   752: goto -282 -> 470
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	755	0	this	IconCache
    //   0	755	1	paramSet	Set<String>
    //   335	59	2	i	int
    //   345	249	3	j	int
    //   355	249	4	k	int
    //   367	315	5	m	int
    //   379	273	6	n	int
    //   610	22	7	i1	int
    //   101	459	8	l1	long
    //   599	36	10	l2	long
    //   60	682	12	localObject1	Object
    //   90	335	13	localObject2	Object
    //   468	9	13	localSQLiteException1	android.database.sqlite.SQLiteException
    //   750	1	13	localSQLiteException2	android.database.sqlite.SQLiteException
    //   110	561	14	localObject3	Object
    //   26	13	15	localIterator	Iterator
    //   119	443	16	localHashMap	HashMap
    //   156	457	17	localObject4	Object
    //   48	625	18	localUserHandleCompat	UserHandleCompat
    //   254	460	19	localStack	java.util.Stack
    //   245	433	20	localHashSet	HashSet
    //   270	367	21	localObject5	Object
    //   622	94	22	localLauncherActivityInfoCompat	LauncherActivityInfoCompat
    // Exception table:
    //   from	to	target	type
    //   326	381	468	android/database/sqlite/SQLiteException
    //   381	419	468	android/database/sqlite/SQLiteException
    //   424	465	468	android/database/sqlite/SQLiteException
    //   576	664	468	android/database/sqlite/SQLiteException
    //   669	695	468	android/database/sqlite/SQLiteException
    //   713	721	468	android/database/sqlite/SQLiteException
    //   326	381	698	finally
    //   381	419	698	finally
    //   424	465	698	finally
    //   576	664	698	finally
    //   669	695	698	finally
    //   713	721	698	finally
    //   259	322	739	finally
    //   470	482	746	finally
    //   259	322	750	android/database/sqlite/SQLiteException
  }
  
  public final IconCache.IconLoadRequest updateIconInBackground(BubbleTextView paramBubbleTextView, ItemInfo paramItemInfo)
  {
    paramBubbleTextView = new IconCache.1(this, paramItemInfo, paramBubbleTextView);
    this.mWorkerHandler.post(paramBubbleTextView);
    return new IconCache.IconLoadRequest(paramBubbleTextView, this.mWorkerHandler);
  }
  
  /* Error */
  public final void updateIconsForPkg(String paramString, UserHandleCompat paramUserHandleCompat)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_2
    //   5: invokevirtual 760	com/android/launcher3/IconCache:removeIconsForPkg	(Ljava/lang/String;Lcom/android/launcher3/compat/UserHandleCompat;)V
    //   8: aload_0
    //   9: getfield 73	com/android/launcher3/IconCache:mPackageManager	Landroid/content/pm/PackageManager;
    //   12: aload_1
    //   13: sipush 8192
    //   16: invokevirtual 312	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   19: astore 5
    //   21: aload_0
    //   22: getfield 81	com/android/launcher3/IconCache:mUserManager	Lcom/android/launcher3/compat/UserManagerCompat;
    //   25: aload_2
    //   26: invokevirtual 347	com/android/launcher3/compat/UserManagerCompat:getSerialNumberForUser	(Lcom/android/launcher3/compat/UserHandleCompat;)J
    //   29: lstore_3
    //   30: aload_0
    //   31: getfield 88	com/android/launcher3/IconCache:mLauncherApps	Lcom/android/launcher3/compat/LauncherAppsCompat;
    //   34: aload_1
    //   35: aload_2
    //   36: invokevirtual 667	com/android/launcher3/compat/LauncherAppsCompat:getActivityList	(Ljava/lang/String;Lcom/android/launcher3/compat/UserHandleCompat;)Ljava/util/List;
    //   39: invokeinterface 663 1 0
    //   44: astore_1
    //   45: aload_1
    //   46: invokeinterface 522 1 0
    //   51: ifeq +34 -> 85
    //   54: aload_0
    //   55: aload_1
    //   56: invokeinterface 526 1 0
    //   61: checkcast 242	com/android/launcher3/compat/LauncherActivityInfoCompat
    //   64: aload 5
    //   66: lload_3
    //   67: invokevirtual 762	com/android/launcher3/IconCache:addIconToDBAndMemCache	(Lcom/android/launcher3/compat/LauncherActivityInfoCompat;Landroid/content/pm/PackageInfo;J)V
    //   70: goto -25 -> 45
    //   73: astore_1
    //   74: ldc_w 392
    //   77: ldc_w 764
    //   80: aload_1
    //   81: invokestatic 400	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
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
    //   0	93	2	paramUserHandleCompat	UserHandleCompat
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
  
  public final void updateTitleAndIcon(AppInfo paramAppInfo)
  {
    try
    {
      IconCache.CacheEntry localCacheEntry = cacheLocked(paramAppInfo.componentName, null, paramAppInfo.user, false, paramAppInfo.usingLowResIcon);
      if ((localCacheEntry.icon != null) && (!isDefaultIcon(localCacheEntry.icon, paramAppInfo.user)))
      {
        paramAppInfo.title = Utilities.trim(localCacheEntry.title);
        paramAppInfo.iconBitmap = localCacheEntry.icon;
        paramAppInfo.contentDescription = localCacheEntry.contentDescription;
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
}
