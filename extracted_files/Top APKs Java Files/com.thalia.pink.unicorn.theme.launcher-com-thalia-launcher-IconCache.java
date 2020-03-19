package com.thalia.launcher;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
import android.util.Log;
import com.thalia.launcher.compat.LauncherActivityInfoCompat;
import com.thalia.launcher.compat.LauncherAppsCompat;
import com.thalia.launcher.compat.UserHandleCompat;
import com.thalia.launcher.compat.UserManagerCompat;
import com.thalia.launcher.model.PackageItemInfo;
import com.thalia.launcher.util.ComponentKey;
import java.util.Arrays;
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
  private static final boolean DEBUG = false;
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
  private final LauncherAppsCompat mLauncherApps;
  private Bitmap mLowResBitmap;
  private Canvas mLowResCanvas;
  private final BitmapFactory.Options mLowResOptions;
  private Paint mLowResPaint;
  final MainThreadExecutor mMainThreadExecutor = new MainThreadExecutor();
  private final int mPackageBgColor;
  private final PackageManager mPackageManager;
  private String mSystemState;
  final UserManagerCompat mUserManager;
  final Handler mWorkerHandler;
  
  public IconCache(Context paramContext, InvariantDeviceProfile paramInvariantDeviceProfile)
  {
    this.mContext = paramContext;
    this.mPackageManager = paramContext.getPackageManager();
    this.mUserManager = UserManagerCompat.getInstance(this.mContext);
    this.mLauncherApps = LauncherAppsCompat.getInstance(this.mContext);
    this.mIconDpi = paramInvariantDeviceProfile.fillResIconDpi;
    this.mIconDb = new IconDB(paramContext);
    this.mWorkerHandler = new Handler(LauncherModel.getWorkerLooper());
    this.mActivityBgColor = paramContext.getResources().getColor(2131034279);
    this.mPackageBgColor = paramContext.getResources().getColor(2131034279);
    this.mLowResOptions = new BitmapFactory.Options();
    this.mLowResOptions.inPreferredConfig = Bitmap.Config.RGB_565;
    updateSystemStateString();
  }
  
  private void addIconToDB(ContentValues paramContentValues, ComponentName paramComponentName, PackageInfo paramPackageInfo, long paramLong)
  {
    paramContentValues.put("componentName", paramComponentName.flattenToString());
    paramContentValues.put("profileId", Long.valueOf(paramLong));
    paramContentValues.put("lastUpdated", Long.valueOf(paramPackageInfo.lastUpdateTime));
    paramContentValues.put("version", Integer.valueOf(paramPackageInfo.versionCode));
    this.mIconDb.getWritableDatabase().insertWithOnConflict("icons", null, paramContentValues, 5);
  }
  
  private CacheEntry cacheLocked(ComponentName paramComponentName, LauncherActivityInfoCompat paramLauncherActivityInfoCompat, UserHandleCompat paramUserHandleCompat, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject2 = new ComponentKey(paramComponentName, paramUserHandleCompat);
    CacheEntry localCacheEntry = (CacheEntry)this.mCache.get(localObject2);
    Object localObject1;
    if (localCacheEntry != null)
    {
      localObject1 = localCacheEntry;
      if (localCacheEntry.isLowResIcon)
      {
        localObject1 = localCacheEntry;
        if (paramBoolean2) {}
      }
    }
    else
    {
      localCacheEntry = new CacheEntry();
      this.mCache.put(localObject2, localCacheEntry);
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("cacheLocked - TITTLE: ");
      ((StringBuilder)localObject1).append(localCacheEntry.title.toString());
      Log.e("CACHE_PUT", ((StringBuilder)localObject1).toString());
      if (!getEntryFromDB((ComponentKey)localObject2, localCacheEntry, paramBoolean2)) {
        if (paramLauncherActivityInfoCompat != null)
        {
          localCacheEntry.icon = Utilities.createIconBitmap(paramLauncherActivityInfoCompat.getBadgedIcon(this.mIconDpi), this.mContext);
        }
        else
        {
          if (paramBoolean1)
          {
            paramComponentName = getEntryForPackageLocked(paramComponentName.getPackageName(), paramUserHandleCompat, false);
            if (paramComponentName != null)
            {
              localCacheEntry.icon = paramComponentName.icon;
              localCacheEntry.title = paramComponentName.title;
              localCacheEntry.contentDescription = paramComponentName.contentDescription;
            }
          }
          if (localCacheEntry.icon == null) {
            localCacheEntry.icon = getDefaultIcon(paramUserHandleCompat);
          }
        }
      }
      paramComponentName = localCacheEntry.icon;
      if ((paramLauncherActivityInfoCompat != null) && (paramLauncherActivityInfoCompat.getApplicationInfo() != null))
      {
        localObject1 = Bitmap.createBitmap(paramComponentName.getWidth(), paramComponentName.getWidth(), Bitmap.Config.ARGB_8888);
        localObject2 = new Canvas((Bitmap)localObject1);
        int i = this.mContext.getSharedPreferences(this.mContext.getPackageName(), 0).getInt("SELECTED_THEME_PREF", 0);
        Object localObject3 = this.mContext.getResources();
        Resources localResources = this.mContext.getResources();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("ic_custom_bg_");
        localStringBuilder.append(i);
        ((Canvas)localObject2).drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource((Resources)localObject3, localResources.getIdentifier(localStringBuilder.toString(), "drawable", this.mContext.getPackageName())), paramComponentName.getWidth(), paramComponentName.getHeight(), true), 0.0F, 0.0F, new Paint());
        localCacheEntry.contentDescription = this.mUserManager.getBadgedLabelForUser(localCacheEntry.title, paramUserHandleCompat);
        if (Arrays.asList(this.mContext.getResources().getStringArray(2130837511)).contains(paramLauncherActivityInfoCompat.getApplicationInfo().packageName))
        {
          ((Canvas)localObject2).drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131165341), paramComponentName.getWidth(), paramComponentName.getHeight(), true), 0.0F, 0.0F, new Paint());
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getLabel());
          ((StringBuilder)localObject3).append(", dialer, ");
          ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getApplicationInfo().packageName);
          Log.v("CUSTOM_ICON_TEST", ((StringBuilder)localObject3).toString());
        }
        else if (Arrays.asList(this.mContext.getResources().getStringArray(2130837517)).contains(paramLauncherActivityInfoCompat.getApplicationInfo().packageName))
        {
          ((Canvas)localObject2).drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131165361), paramComponentName.getWidth(), paramComponentName.getHeight(), true), 0.0F, 0.0F, new Paint());
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getLabel());
          ((StringBuilder)localObject3).append(", sms, ");
          ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getApplicationInfo().packageName);
          Log.v("CUSTOM_ICON_TEST", ((StringBuilder)localObject3).toString());
        }
        else if (Arrays.asList(this.mContext.getResources().getStringArray(2130837515)).contains(paramLauncherActivityInfoCompat.getApplicationInfo().packageName))
        {
          if ((!String.valueOf(paramLauncherActivityInfoCompat.getLabel()).equalsIgnoreCase("Phone")) && (!String.valueOf(paramLauncherActivityInfoCompat.getLabel()).equalsIgnoreCase("Dialer")))
          {
            ((Canvas)localObject2).drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131165356), paramComponentName.getWidth(), paramComponentName.getHeight(), true), 0.0F, 0.0F, new Paint());
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getLabel());
            ((StringBuilder)localObject3).append(", people, ");
            ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getApplicationInfo().packageName);
            Log.v("CUSTOM_ICON_TEST", ((StringBuilder)localObject3).toString());
          }
          else
          {
            ((Canvas)localObject2).drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131165341), paramComponentName.getWidth(), paramComponentName.getHeight(), true), 0.0F, 0.0F, new Paint());
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getLabel());
            ((StringBuilder)localObject3).append(", ZAJEB, ");
            ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getApplicationInfo().packageName);
            Log.v("CUSTOM_ICON_TEST", ((StringBuilder)localObject3).toString());
          }
        }
        else if (Arrays.asList(this.mContext.getResources().getStringArray(2130837509)).contains(paramLauncherActivityInfoCompat.getApplicationInfo().packageName))
        {
          ((Canvas)localObject2).drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131165326), paramComponentName.getWidth(), paramComponentName.getHeight(), true), 0.0F, 0.0F, new Paint());
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getLabel());
          ((StringBuilder)localObject3).append(", camera, ");
          ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getApplicationInfo().packageName);
          Log.v("CUSTOM_ICON_TEST", ((StringBuilder)localObject3).toString());
        }
        else if (Arrays.asList(this.mContext.getResources().getStringArray(2130837516)).contains(paramLauncherActivityInfoCompat.getApplicationInfo().packageName))
        {
          ((Canvas)localObject2).drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131165360), paramComponentName.getWidth(), paramComponentName.getHeight(), true), 0.0F, 0.0F, new Paint());
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getLabel());
          ((StringBuilder)localObject3).append(", settings, ");
          ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getApplicationInfo().packageName);
          Log.v("CUSTOM_ICON_TEST", ((StringBuilder)localObject3).toString());
        }
        else if (Arrays.asList(this.mContext.getResources().getStringArray(2130837512)).contains(paramLauncherActivityInfoCompat.getApplicationInfo().packageName))
        {
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append("info: ");
          ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getComponentName().getClassName());
          Log.e("Launcher", ((StringBuilder)localObject3).toString());
          if (paramLauncherActivityInfoCompat.getComponentName().getClassName().contains("com.android.camera.CameraLauncher"))
          {
            ((Canvas)localObject2).drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131165326), paramComponentName.getWidth(), paramComponentName.getHeight(), true), 0.0F, 0.0F, new Paint());
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getLabel());
            ((StringBuilder)localObject3).append(", camera, ");
            ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getApplicationInfo().packageName);
            Log.v("CUSTOM_ICON_TEST", ((StringBuilder)localObject3).toString());
          }
          else
          {
            ((Canvas)localObject2).drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131165342), paramComponentName.getWidth(), paramComponentName.getHeight(), true), 0.0F, 0.0F, new Paint());
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getLabel());
            ((StringBuilder)localObject3).append(", gallery, ");
            ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getApplicationInfo().packageName);
            Log.v("CUSTOM_ICON_TEST", ((StringBuilder)localObject3).toString());
          }
        }
        else if (Arrays.asList(this.mContext.getResources().getStringArray(2130837514)).contains(paramLauncherActivityInfoCompat.getApplicationInfo().packageName))
        {
          ((Canvas)localObject2).drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131165347), paramComponentName.getWidth(), paramComponentName.getHeight(), true), 0.0F, 0.0F, new Paint());
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getLabel());
          ((StringBuilder)localObject3).append(", mail, ");
          ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getApplicationInfo().packageName);
          Log.v("CUSTOM_ICON_TEST", ((StringBuilder)localObject3).toString());
        }
        else if (Arrays.asList(this.mContext.getResources().getStringArray(2130837510)).contains(paramLauncherActivityInfoCompat.getApplicationInfo().packageName))
        {
          ((Canvas)localObject2).drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131165328), paramComponentName.getWidth(), paramComponentName.getHeight(), true), 0.0F, 0.0F, new Paint());
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getLabel());
          ((StringBuilder)localObject3).append(", clock, ");
          ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getApplicationInfo().packageName);
          Log.v("CUSTOM_ICON_TEST", ((StringBuilder)localObject3).toString());
        }
        else if (Arrays.asList(this.mContext.getResources().getStringArray(2130837507)).contains(paramLauncherActivityInfoCompat.getApplicationInfo().packageName))
        {
          ((Canvas)localObject2).drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131165324), paramComponentName.getWidth(), paramComponentName.getHeight(), true), 0.0F, 0.0F, new Paint());
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getLabel());
          ((StringBuilder)localObject3).append(", calculator, ");
          ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getApplicationInfo().packageName);
          Log.v("CUSTOM_ICON_TEST", ((StringBuilder)localObject3).toString());
        }
        else if (Arrays.asList(this.mContext.getResources().getStringArray(2130837506)).contains(paramLauncherActivityInfoCompat.getApplicationInfo().packageName))
        {
          ((Canvas)localObject2).drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131165323), paramComponentName.getWidth(), paramComponentName.getHeight(), true), 0.0F, 0.0F, new Paint());
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getLabel());
          ((StringBuilder)localObject3).append(", browser, ");
          ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getApplicationInfo().packageName);
          Log.v("CUSTOM_ICON_TEST", ((StringBuilder)localObject3).toString());
        }
        else if (Arrays.asList(this.mContext.getResources().getStringArray(2130837508)).contains(paramLauncherActivityInfoCompat.getApplicationInfo().packageName))
        {
          ((Canvas)localObject2).drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131165325), paramComponentName.getWidth(), paramComponentName.getHeight(), true), 0.0F, 0.0F, new Paint());
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getLabel());
          ((StringBuilder)localObject3).append(", calendar, ");
          ((StringBuilder)localObject3).append(paramLauncherActivityInfoCompat.getApplicationInfo().packageName);
          Log.v("CUSTOM_ICON_TEST", ((StringBuilder)localObject3).toString());
        }
        else
        {
          float f = Float.parseFloat(this.mContext.getString(2131689622));
          ((Canvas)localObject2).drawBitmap(Bitmap.createScaledBitmap(paramComponentName, (int)Math.floor(paramComponentName.getWidth() / f), (int)Math.floor(paramComponentName.getWidth() / f), true), (int)Math.floor((paramComponentName.getWidth() - paramComponentName.getWidth() / f) / 2.0F), (int)Math.floor((paramComponentName.getHeight() - paramComponentName.getHeight() / f) / 2.0F), new Paint());
        }
        localObject3 = this.mContext.getResources();
        localResources = this.mContext.getResources();
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("ic_custom_over_");
        localStringBuilder.append(i);
        ((Canvas)localObject2).drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource((Resources)localObject3, localResources.getIdentifier(localStringBuilder.toString(), "drawable", this.mContext.getPackageName())), paramComponentName.getWidth(), paramComponentName.getHeight(), true), 0.0F, 0.0F, new Paint());
        if (String.valueOf(paramLauncherActivityInfoCompat.getApplicationInfo().packageName).equalsIgnoreCase(this.mContext.getPackageName())) {
          localCacheEntry.icon = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131558400), paramComponentName.getWidth(), paramComponentName.getHeight(), true);
        } else {
          localCacheEntry.icon = ((Bitmap)localObject1);
        }
      }
      localObject1 = localCacheEntry;
      if (TextUtils.isEmpty(localCacheEntry.title))
      {
        localObject1 = localCacheEntry;
        if (paramLauncherActivityInfoCompat != null)
        {
          localCacheEntry.title = paramLauncherActivityInfoCompat.getLabel();
          localCacheEntry.contentDescription = this.mUserManager.getBadgedLabelForUser(localCacheEntry.title, paramUserHandleCompat);
          localObject1 = localCacheEntry;
        }
      }
    }
    return localObject1;
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
        break label315;
      }
      i = 0;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;)
      {
        continue;
        i = 8192;
      }
    }
    paramString = this.mPackageManager.getPackageInfo(paramString, i);
    Object localObject = paramString.applicationInfo;
    if (localObject == null) {
      throw new PackageManager.NameNotFoundException("ApplicationInfo is null");
    }
    localCacheEntry.icon = Utilities.createIconBitmap(this.mUserManager.getBadgedDrawableForUser(((ApplicationInfo)localObject).loadIcon(this.mPackageManager), paramUserHandleCompat), this.mContext);
    localCacheEntry.title = ((ApplicationInfo)localObject).loadLabel(this.mPackageManager);
    localCacheEntry.contentDescription = this.mUserManager.getBadgedLabelForUser(localCacheEntry.title, paramUserHandleCompat);
    localCacheEntry.isLowResIcon = false;
    addIconToDB(newContentValues(localCacheEntry.icon, localCacheEntry.title.toString(), this.mPackageBgColor), localComponentKey.componentName, paramString, this.mUserManager.getSerialNumberForUser(paramUserHandleCompat));
    i = j;
    break label241;
    i = 0;
    label241:
    localObject = localCacheEntry;
    if (i != 0)
    {
      this.mCache.put(localComponentKey, localCacheEntry);
      paramString = new StringBuilder();
      paramString.append("getEntryForPackageLocked TITTLE: ");
      paramString.append(localCacheEntry.title.toString());
      Log.e("CACHE_PUT", paramString.toString());
      localObject = localCacheEntry;
    }
    return localObject;
  }
  
  private boolean getEntryFromDB(ComponentKey paramComponentKey, CacheEntry paramCacheEntry, boolean paramBoolean)
  {
    Log.e("ICON_DB", "getEntryFromDB");
    return false;
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
    Object localObject = getFullResDefaultActivityIcon();
    paramUserHandleCompat = this.mUserManager.getBadgedDrawableForUser((Drawable)localObject, paramUserHandleCompat);
    localObject = Bitmap.createBitmap(Math.max(paramUserHandleCompat.getIntrinsicWidth(), 1), Math.max(paramUserHandleCompat.getIntrinsicHeight(), 1), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas((Bitmap)localObject);
    paramUserHandleCompat.setBounds(0, 0, ((Bitmap)localObject).getWidth(), ((Bitmap)localObject).getHeight());
    paramUserHandleCompat.draw(localCanvas);
    localCanvas.setBitmap(null);
    return localObject;
  }
  
  private ContentValues newContentValues(Bitmap paramBitmap, String paramString, int paramInt)
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
  
  private void updateDBIcons(UserHandleCompat paramUserHandleCompat, List<LauncherActivityInfoCompat> paramList, Set<String> paramSet)
  {
    Log.e("ICON_DB", "updateDbIcons2");
    long l1 = this.mUserManager.getSerialNumberForUser(paramUserHandleCompat);
    Object localObject1 = this.mContext.getPackageManager();
    HashMap localHashMap = new HashMap();
    localObject1 = ((PackageManager)localObject1).getInstalledPackages(8192).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (PackageInfo)((Iterator)localObject1).next();
      localHashMap.put(((PackageInfo)localObject2).packageName, localObject2);
    }
    localObject1 = new HashMap();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      localObject2 = (LauncherActivityInfoCompat)paramList.next();
      ((HashMap)localObject1).put(((LauncherActivityInfoCompat)localObject2).getComponentName(), localObject2);
    }
    paramList = this.mIconDb.getReadableDatabase();
    Object localObject2 = Long.toString(l1);
    localObject2 = paramList.query("icons", new String[] { "rowid", "componentName", "lastUpdated", "version", "system_state" }, "profileId = ? ", new String[] { localObject2 }, null, null, null);
    int i = ((Cursor)localObject2).getColumnIndex("componentName");
    int k = ((Cursor)localObject2).getColumnIndex("lastUpdated");
    int j = ((Cursor)localObject2).getColumnIndex("version");
    int m = ((Cursor)localObject2).getColumnIndex("rowid");
    int n = ((Cursor)localObject2).getColumnIndex("system_state");
    HashSet localHashSet = new HashSet();
    paramList = new Stack();
    while (((Cursor)localObject2).moveToNext())
    {
      ComponentName localComponentName = ComponentName.unflattenFromString(((Cursor)localObject2).getString(i));
      PackageInfo localPackageInfo = (PackageInfo)localHashMap.get(localComponentName.getPackageName());
      if (localPackageInfo == null) {
        if (!paramSet.contains(localComponentName.getPackageName()))
        {
          remove(localComponentName, paramUserHandleCompat);
          localHashSet.add(Integer.valueOf(((Cursor)localObject2).getInt(m)));
        }
      }
      while ((localPackageInfo.applicationInfo.flags & 0x1000000) != 0) {
        break;
      }
      long l2 = ((Cursor)localObject2).getLong(k);
      int i1 = ((Cursor)localObject2).getInt(j);
      LauncherActivityInfoCompat localLauncherActivityInfoCompat = (LauncherActivityInfoCompat)((HashMap)localObject1).remove(localComponentName);
      if ((i1 != localPackageInfo.versionCode) || (l2 != localPackageInfo.lastUpdateTime) || (!TextUtils.equals(this.mSystemState, ((Cursor)localObject2).getString(n)))) {
        for (;;)
        {
          if (localLauncherActivityInfoCompat == null)
          {
            remove(localComponentName, paramUserHandleCompat);
            localHashSet.add(Integer.valueOf(((Cursor)localObject2).getInt(m)));
          }
          else
          {
            paramList.add(localLauncherActivityInfoCompat);
          }
        }
      }
    }
    ((Cursor)localObject2).close();
    if (!localHashSet.isEmpty()) {
      this.mIconDb.getWritableDatabase().delete("icons", Utilities.createDbSelectionQuery("rowid", localHashSet), null);
    }
    if ((!((HashMap)localObject1).isEmpty()) || (!paramList.isEmpty()))
    {
      paramUserHandleCompat = new Stack();
      paramUserHandleCompat.addAll(((HashMap)localObject1).values());
      new SerializedIconUpdateTask(l1, localHashMap, paramUserHandleCompat, paramList).scheduleNext();
    }
  }
  
  private void updateSystemStateString()
  {
    this.mSystemState = Locale.getDefault().toString();
  }
  
  void addIconToDBAndMemCache(LauncherActivityInfoCompat paramLauncherActivityInfoCompat, PackageInfo paramPackageInfo, long paramLong)
  {
    Log.e("ICON_DB", "addIconToDBAndMemCache");
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
        paramUserHandleCompat = new StringBuilder();
        paramUserHandleCompat.append("cachePackageInstallInfo - TITTLE: ");
        paramUserHandleCompat.append(paramString.title.toString());
        Log.e("CACHE_PUT", paramUserHandleCompat.toString());
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
  
  public void flush()
  {
    this.mCache.clear();
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
      paramAppInfo.iconBitmap = getNonNullIcon(paramLauncherActivityInfoCompat, localUserHandleCompat);
      paramAppInfo.contentDescription = paramLauncherActivityInfoCompat.contentDescription;
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
  
  public void getTitleAndIconForApp(String paramString, UserHandleCompat paramUserHandleCompat, boolean paramBoolean, PackageItemInfo paramPackageItemInfo)
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
  
  public boolean isDefaultIcon(Bitmap paramBitmap, UserHandleCompat paramUserHandleCompat)
  {
    return this.mDefaultIcons.get(paramUserHandleCompat) == paramBitmap;
  }
  
  public void preloadIcon(ComponentName paramComponentName, Bitmap paramBitmap, int paramInt, String paramString, long paramLong, InvariantDeviceProfile paramInvariantDeviceProfile)
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
    paramBitmap = newContentValues(Bitmap.createScaledBitmap(paramBitmap, paramInvariantDeviceProfile.iconBitmapSize, paramInvariantDeviceProfile.iconBitmapSize, true), paramString, 0);
    paramBitmap.put("componentName", paramComponentName.flattenToString());
    paramBitmap.put("profileId", Long.valueOf(paramLong));
    this.mIconDb.getWritableDatabase().insertWithOnConflict("icons", null, paramBitmap, 5);
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
      paramUserHandleCompat = this.mIconDb.getWritableDatabase();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("/%");
      paramUserHandleCompat.delete("icons", "componentName LIKE ? AND profileId = ?", new String[] { localStringBuilder.toString(), Long.toString(l) });
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
    Log.e("ICON_DB", "updateCacheAndGetContentValues");
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
      localObject1 = new CacheEntry();
      ((CacheEntry)localObject1).icon = Utilities.createIconBitmap(paramLauncherActivityInfoCompat.getBadgedIcon(this.mIconDpi), this.mContext);
      localObject2 = localObject1;
      if (!paramLauncherActivityInfoCompat.getLabel().toString().equals(this.mContext.getString(2131689539)))
      {
        float f = Float.parseFloat(this.mContext.getString(2131689622));
        localObject2 = Bitmap.createBitmap(((CacheEntry)localObject1).icon.getWidth(), ((CacheEntry)localObject1).icon.getWidth(), Bitmap.Config.ARGB_8888);
        localObject3 = new Canvas((Bitmap)localObject2);
        int i = this.mContext.getSharedPreferences(this.mContext.getPackageName(), 0).getInt("SELECTED_THEME_PREF", 0);
        Object localObject4 = new StringBuilder();
        ((StringBuilder)localObject4).append("updateCacheAndGetContentValues TITTLE ENTRY == NULL: ");
        ((StringBuilder)localObject4).append(paramLauncherActivityInfoCompat.getLabel().toString());
        Log.e("CACHE_PUT", ((StringBuilder)localObject4).toString());
        localObject4 = this.mContext.getResources();
        Resources localResources = this.mContext.getResources();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("ic_custom_bg_");
        localStringBuilder.append(i);
        ((Canvas)localObject3).drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource((Resources)localObject4, localResources.getIdentifier(localStringBuilder.toString(), "drawable", this.mContext.getPackageName())), ((CacheEntry)localObject1).icon.getWidth(), ((CacheEntry)localObject1).icon.getHeight(), true), 0.0F, 0.0F, new Paint());
        ((Canvas)localObject3).drawBitmap(Bitmap.createScaledBitmap(((CacheEntry)localObject1).icon, (int)Math.floor(((CacheEntry)localObject1).icon.getWidth() / f), (int)Math.floor(((CacheEntry)localObject1).icon.getWidth() / f), true), (int)Math.floor((((CacheEntry)localObject1).icon.getWidth() - ((CacheEntry)localObject1).icon.getWidth() / f) / 2.0F), (int)Math.floor((((CacheEntry)localObject1).icon.getHeight() - ((CacheEntry)localObject1).icon.getHeight() / f) / 2.0F), new Paint());
        localObject4 = this.mContext.getResources();
        localResources = this.mContext.getResources();
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("ic_custom_over_");
        localStringBuilder.append(i);
        ((Canvas)localObject3).drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource((Resources)localObject4, localResources.getIdentifier(localStringBuilder.toString(), "drawable", this.mContext.getPackageName())), ((CacheEntry)localObject1).icon.getWidth(), ((CacheEntry)localObject1).icon.getHeight(), true), 0.0F, 0.0F, new Paint());
        ((CacheEntry)localObject1).icon = ((Bitmap)localObject2);
        localObject2 = localObject1;
      }
    }
    ((CacheEntry)localObject2).title = paramLauncherActivityInfoCompat.getLabel();
    ((CacheEntry)localObject2).contentDescription = this.mUserManager.getBadgedLabelForUser(((CacheEntry)localObject2).title, paramLauncherActivityInfoCompat.getUser());
    this.mCache.put(new ComponentKey(paramLauncherActivityInfoCompat.getComponentName(), paramLauncherActivityInfoCompat.getUser()), localObject2);
    paramLauncherActivityInfoCompat = new StringBuilder();
    paramLauncherActivityInfoCompat.append("updateCacheAndGetContentValues TITTLE: ");
    paramLauncherActivityInfoCompat.append(((CacheEntry)localObject2).title.toString());
    Log.e("CACHE_PUT", paramLauncherActivityInfoCompat.toString());
    return newContentValues(((CacheEntry)localObject2).icon, ((CacheEntry)localObject2).title.toString(), this.mActivityBgColor);
  }
  
  public void updateDbIcons(Set<String> paramSet)
  {
    this.mWorkerHandler.removeCallbacksAndMessages(ICON_UPDATE_TOKEN);
    Log.e("ICON_DB", "updateDbIcons");
    updateSystemStateString();
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
    Log.e("ICON_DB", "updateIconInBackground");
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
            IconCache.this.getTitleAndIconForApp(((PackageItemInfo)localObject).packageName, ((PackageItemInfo)localObject).user, false, (PackageItemInfo)localObject);
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
    //   5: invokevirtual 964	com/thalia/launcher/IconCache:removeIconsForPkg	(Ljava/lang/String;Lcom/thalia/launcher/compat/UserHandleCompat;)V
    //   8: aload_0
    //   9: getfield 102	com/thalia/launcher/IconCache:mPackageManager	Landroid/content/pm/PackageManager;
    //   12: aload_1
    //   13: sipush 8192
    //   16: invokevirtual 536	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   19: astore 5
    //   21: aload_0
    //   22: getfield 110	com/thalia/launcher/IconCache:mUserManager	Lcom/thalia/launcher/compat/UserManagerCompat;
    //   25: aload_2
    //   26: invokevirtual 568	com/thalia/launcher/compat/UserManagerCompat:getSerialNumberForUser	(Lcom/thalia/launcher/compat/UserHandleCompat;)J
    //   29: lstore_3
    //   30: aload_0
    //   31: getfield 117	com/thalia/launcher/IconCache:mLauncherApps	Lcom/thalia/launcher/compat/LauncherAppsCompat;
    //   34: aload_1
    //   35: aload_2
    //   36: invokevirtual 939	com/thalia/launcher/compat/LauncherAppsCompat:getActivityList	(Ljava/lang/String;Lcom/thalia/launcher/compat/UserHandleCompat;)Ljava/util/List;
    //   39: invokeinterface 715 1 0
    //   44: astore_1
    //   45: aload_1
    //   46: invokeinterface 690 1 0
    //   51: ifeq +22 -> 73
    //   54: aload_0
    //   55: aload_1
    //   56: invokeinterface 694 1 0
    //   61: checkcast 285	com/thalia/launcher/compat/LauncherActivityInfoCompat
    //   64: aload 5
    //   66: lload_3
    //   67: invokevirtual 966	com/thalia/launcher/IconCache:addIconToDBAndMemCache	(Lcom/thalia/launcher/compat/LauncherActivityInfoCompat;Landroid/content/pm/PackageInfo;J)V
    //   70: goto -25 -> 45
    //   73: aload_0
    //   74: monitorexit
    //   75: return
    //   76: astore_1
    //   77: ldc 37
    //   79: ldc_w 968
    //   82: aload_1
    //   83: invokestatic 972	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
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
    //   8	45	76	android/content/pm/PackageManager$NameNotFoundException
    //   45	70	76	android/content/pm/PackageManager$NameNotFoundException
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
  
  static class CacheEntry
  {
    public CharSequence contentDescription = "";
    public Bitmap icon;
    public boolean isLowResIcon;
    public CharSequence title = "";
    
    CacheEntry() {}
  }
  
  private static final class IconDB
    extends SQLiteOpenHelper
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
    private static final int DB_VERSION = 7;
    private static final String TABLE_NAME = "icons";
    
    public IconDB(Context paramContext)
    {
      super("app_icons.db", null, 7);
    }
    
    private void clearDB(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS icons");
      onCreate(paramSQLiteDatabase);
    }
    
    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS icons (componentName TEXT NOT NULL, profileId INTEGER NOT NULL, lastUpdated INTEGER NOT NULL DEFAULT 0, version INTEGER NOT NULL DEFAULT 0, icon BLOB, icon_low_res BLOB, label TEXT, system_state TEXT, PRIMARY KEY (componentName, profileId) );");
    }
    
    public void onDowngrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      if (paramInt1 != paramInt2) {
        clearDB(paramSQLiteDatabase);
      }
    }
    
    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      if (paramInt1 != paramInt2) {
        clearDB(paramSQLiteDatabase);
      }
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
      Object localObject2;
      Object localObject4;
      if (!this.mAppsToUpdate.isEmpty())
      {
        ??? = (LauncherActivityInfoCompat)this.mAppsToUpdate.pop();
        localObject2 = ((LauncherActivityInfoCompat)???).getComponentName().flattenToString();
        localObject4 = IconCache.this.updateCacheAndGetContentValues((LauncherActivityInfoCompat)???, true);
        IconCache.this.mIconDb.getWritableDatabase().update("icons", (ContentValues)localObject4, "componentName = ? AND profileId = ?", new String[] { localObject2, Long.toString(this.mUserSerial) });
        this.mUpdatedPackages.add(((LauncherActivityInfoCompat)???).getComponentName().getPackageName());
        if ((this.mAppsToUpdate.isEmpty()) && (!this.mUpdatedPackages.isEmpty())) {
          LauncherAppState.getInstance().getModel().onPackageIconsUpdated(this.mUpdatedPackages, IconCache.this.mUserManager.getUserForSerialNumber(this.mUserSerial));
        }
        scheduleNext();
        return;
      }
      if (!this.mAppsToAdd.isEmpty())
      {
        localObject2 = (LauncherActivityInfoCompat)this.mAppsToAdd.pop();
        localObject4 = (PackageInfo)this.mPkgInfoMap.get(((LauncherActivityInfoCompat)localObject2).getComponentName().getPackageName());
        if (localObject4 != null) {
          synchronized (IconCache.this)
          {
            IconCache.this.addIconToDBAndMemCache((LauncherActivityInfoCompat)localObject2, (PackageInfo)localObject4, this.mUserSerial);
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
