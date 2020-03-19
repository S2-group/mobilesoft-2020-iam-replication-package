package ru.aviasales.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.wearable.Asset;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPInputStream;
import ru.aviasales.AviasalesApplication;
import ru.aviasales.BuildManager;
import ru.aviasales.ads.AdsManager;
import ru.aviasales.api.ads.params.Resolution;
import ru.aviasales.api.mobile_intelligence.params.ClientInfo;
import ru.aviasales.api.places.object.Coordinates;
import ru.aviasales.core.identification.UserIdentificationPrefs;
import ru.aviasales.currencies.CurrenciesManager;

public class AndroidUtils
{
  public static final int PHONE = 42;
  public static final int TABLET_LARGE = 34;
  public static final int TABLET_XLARGE = 35;
  public static Integer deviceType = null;
  private static Boolean isTablet = null;
  
  public AndroidUtils() {}
  
  public static void addToViewStatusBarMargin(View paramView)
  {
    if (Build.VERSION.SDK_INT < 21) {}
    while (paramView == null) {
      return;
    }
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    localMarginLayoutParams.setMargins(localMarginLayoutParams.leftMargin, localMarginLayoutParams.topMargin + getStatusBarHeight(paramView.getContext()), localMarginLayoutParams.rightMargin, localMarginLayoutParams.bottomMargin);
  }
  
  public static boolean canProvideRuntimePermissions()
  {
    return Build.VERSION.SDK_INT >= 23;
  }
  
  public static boolean canShowMap(Context paramContext)
  {
    return (isApplicationInstalled(paramContext.getPackageManager(), "com.google.android.apps.maps")) && (isApplicationInstalled(paramContext.getPackageManager(), "com.google.android.gms")) && (isGoogleServicesActual(paramContext));
  }
  
  public static boolean checkPlayServices(Context paramContext)
  {
    return GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext) == 0;
  }
  
  public static int convertDPtoPixels(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat * paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }
  
  @Nullable
  public static Asset createAssetFromBitmap(Bitmap paramBitmap)
  {
    if (paramBitmap != null)
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
      return Asset.createFromBytes(localByteArrayOutputStream.toByteArray());
    }
    return null;
  }
  
  public static ClientInfo createClientInfo()
  {
    Object localObject = AviasalesApplication.getContext();
    ClientInfo localClientInfo = new ClientInfo();
    if (BuildManager.isJetRadarApp())
    {
      localClientInfo.setApp("jetradar");
      localClientInfo.setAppVersion(getAppVersionName((Context)localObject));
      localClientInfo.setCurrency(CurrenciesManager.getInstance().getAppCurrency().toLowerCase());
      localClientInfo.setMarker(new UserIdentificationPrefs((Context)localObject).getMarker());
      localClientInfo.setOsVersion(Build.VERSION.RELEASE);
      if (!isTablet((Context)localObject)) {
        break label121;
      }
      localClientInfo.setPlatform("tablet");
    }
    for (;;)
    {
      localClientInfo.setOs("android");
      localClientInfo.setResolution(getResolution((Context)localObject));
      localObject = AdsManager.getInstance().getNearestIata();
      if (localObject != null) {
        localClientInfo.setGeoLocation((String)localObject);
      }
      return localClientInfo;
      localClientInfo.setApp("aviasales");
      break;
      label121:
      localClientInfo.setPlatform("phone");
    }
  }
  
  public static Long getAppInstallDate(Context paramContext)
  {
    if (paramContext == null) {
      return Long.valueOf(0L);
    }
    AviasalesApplication localAviasalesApplication = (AviasalesApplication)paramContext.getApplicationContext();
    long l2 = localAviasalesApplication.getPreferences().getLong("INSTALLATION_DATE", 0L);
    long l1;
    if (l2 == 0L)
    {
      try
      {
        l1 = new File(paramContext.getPackageManager().getApplicationInfo("ru.aviasales", 0).sourceDir).lastModified();
        l2 = l1;
        Log.d("as_debug", "getting install date: " + l1);
      }
      catch (Exception paramContext)
      {
        for (;;)
        {
          Log.d("as_debug", "cannot get install date, get current: " + l2);
          l1 = System.currentTimeMillis();
        }
      }
      localAviasalesApplication.getPreferences().edit().putLong("INSTALLATION_DATE", l1).commit();
    }
    for (;;)
    {
      return Long.valueOf(l1);
      Log.d("as_debug", "get install date from prefs: " + l2);
      l1 = l2;
    }
  }
  
  public static int getAppVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      throw new RuntimeException("Could not get package name: " + paramContext);
    }
  }
  
  public static String getAppVersionName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      throw new RuntimeException("Could not get package name: " + paramContext);
    }
  }
  
  public static int getDeviceType(Context paramContext)
  {
    if (deviceType == null)
    {
      if ((paramContext.getResources().getConfiguration().screenLayout & 0xF) != 4) {
        break label38;
      }
      deviceType = Integer.valueOf(35);
    }
    for (;;)
    {
      return deviceType.intValue();
      label38:
      if ((paramContext.getResources().getConfiguration().screenLayout & 0xF) == 3) {
        deviceType = Integer.valueOf(34);
      } else {
        deviceType = Integer.valueOf(42);
      }
    }
  }
  
  public static Point getDisplayDimensions()
  {
    Display localDisplay = ((WindowManager)AviasalesApplication.getContext().getSystemService("window")).getDefaultDisplay();
    Point localPoint = new Point();
    localDisplay.getSize(localPoint);
    return localPoint;
  }
  
  public static int getDisplayWidth(Context paramContext)
  {
    if (Build.VERSION.SDK_INT > 13)
    {
      paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
      Point localPoint = new Point();
      paramContext.getSize(localPoint);
      return localPoint.x;
    }
    return ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getWidth();
  }
  
  public static Coordinates getFastLocation(Context paramContext)
  {
    if (!isLocationPermissionGranted(paramContext)) {}
    do
    {
      return null;
      paramContext = (LocationManager)paramContext.getSystemService("location");
      localObject2 = paramContext.getAllProviders();
      localObject1 = new ArrayList();
      int i = 0;
      while (i < ((List)localObject2).size())
      {
        Location localLocation = paramContext.getLastKnownLocation((String)((List)localObject2).get(i));
        if (localLocation != null) {
          ((List)localObject1).add(localLocation);
        }
        i += 1;
      }
    } while (((List)localObject1).size() == 0);
    paramContext = (Location)((List)localObject1).get(0);
    Object localObject2 = ((List)localObject1).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject1 = (Location)((Iterator)localObject2).next();
      if (((Location)localObject1).getTime() > paramContext.getTime()) {
        paramContext = (Context)localObject1;
      }
    }
    Object localObject1 = new Coordinates();
    ((Coordinates)localObject1).setLatitude(paramContext.getLatitude());
    ((Coordinates)localObject1).setLongitude(paramContext.getLongitude());
    return localObject1;
  }
  
  public static Resolution getResolution(Context paramContext)
  {
    Resolution localResolution = new Resolution();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    localResolution.setWidth(localDisplayMetrics.widthPixels);
    localResolution.setHeight(localDisplayMetrics.heightPixels);
    return localResolution;
  }
  
  public static int getScreenHeight()
  {
    return getDisplayDimensions().y;
  }
  
  public static int getStatusBarHeight(Context paramContext)
  {
    int i = 0;
    int j = paramContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (j > 0) {
      i = paramContext.getResources().getDimensionPixelSize(j);
    }
    return i;
  }
  
  public static int getToolbarHeight(Context paramContext)
  {
    TypedValue localTypedValue = new TypedValue();
    if (paramContext.getTheme().resolveAttribute(2130772051, localTypedValue, true)) {
      return TypedValue.complexToDimensionPixelSize(localTypedValue.data, paramContext.getResources().getDisplayMetrics());
    }
    return paramContext.getResources().getDimensionPixelSize(2131361797);
  }
  
  public static String gzipInputStreamToString(InputStream paramInputStream)
  {
    BufferedReader localBufferedReader = null;
    try
    {
      paramInputStream = new GZIPInputStream(paramInputStream);
      localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
      paramInputStream = new StringBuilder();
      try
      {
        for (;;)
        {
          String str = localBufferedReader.readLine();
          if (str == null) {
            break;
          }
          paramInputStream.append(str);
        }
        return paramInputStream.toString();
      }
      catch (IOException localIOException)
      {
        Log.e("OLOLO", localIOException.toString());
      }
    }
    catch (IOException paramInputStream)
    {
      for (;;)
      {
        Log.e("OLOLO", paramInputStream.toString());
        paramInputStream = localIOException;
      }
    }
  }
  
  public static void hideSoftKeyboard(Activity paramActivity)
  {
    if ((paramActivity == null) || (paramActivity.getCurrentFocus() == null)) {
      return;
    }
    ((InputMethodManager)paramActivity.getSystemService("input_method")).hideSoftInputFromWindow(paramActivity.getCurrentFocus().getWindowToken(), 0);
  }
  
  public static boolean isApplicationInstalled(PackageManager paramPackageManager, String paramString)
  {
    try
    {
      paramPackageManager.getApplicationInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramPackageManager) {}
    return false;
  }
  
  @TargetApi(17)
  public static boolean isDeviceHasNavBar(Activity paramActivity)
  {
    Rect localRect = new Rect();
    paramActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
    paramActivity = paramActivity.getWindowManager().getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getRealMetrics(localDisplayMetrics);
    return localDisplayMetrics.heightPixels - localRect.bottom != 0;
  }
  
  public static boolean isGoogleServicesActual(Context paramContext)
  {
    return (GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext) != 1) && (GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext) != 2);
  }
  
  public static boolean isLandscape(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().orientation == 2;
  }
  
  public static boolean isLandscapeTablet(Context paramContext)
  {
    return (isTablet(paramContext)) && (isLandscape(paramContext));
  }
  
  public static boolean isLocationPermissionGranted(Context paramContext)
  {
    boolean bool = false;
    try
    {
      int i = ContextCompat.checkSelfPermission(paramContext, "android.permission.ACCESS_FINE_LOCATION");
      if (i == 0) {
        bool = true;
      }
      return bool;
    }
    catch (Throwable paramContext)
    {
      Crashlytics.logException(paramContext);
    }
    return false;
  }
  
  public static boolean isLocationServicesAvailable(Context paramContext)
  {
    paramContext = (LocationManager)paramContext.getSystemService("location");
    return (paramContext != null) && ((paramContext.isProviderEnabled("gps")) || (paramContext.isProviderEnabled("network")));
  }
  
  public static boolean isOnline(Context paramContext)
  {
    if (paramContext != null) {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    }
    return (paramContext != null) && (paramContext.isConnectedOrConnecting());
  }
  
  public static boolean isPhone(Context paramContext)
  {
    return !isTablet(paramContext);
  }
  
  public static boolean isPortrait(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().orientation == 1;
  }
  
  public static boolean isTablet(Context paramContext)
  {
    if (isTablet == null) {
      if ((getDeviceType(paramContext) != 34) && (getDeviceType(paramContext) != 35)) {
        break label40;
      }
    }
    label40:
    for (boolean bool = true;; bool = false)
    {
      isTablet = Boolean.valueOf(bool);
      return isTablet.booleanValue();
    }
  }
  
  public static boolean isTranslucentAvailableOnDevice(Context paramContext)
  {
    boolean bool = false;
    int i = paramContext.getResources().getIdentifier("config_enableTranslucentDecor", "bool", "android");
    if (i != 0) {
      bool = paramContext.getResources().getBoolean(i);
    }
    return bool;
  }
  
  public static boolean isVoiceSearchExists()
  {
    boolean bool1 = false;
    Object localObject1 = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject1).addCategory("android.intent.category.LAUNCHER");
    Object localObject2 = AviasalesApplication.getApp().getPackageManager().getInstalledPackages(0);
    localObject1 = AviasalesApplication.getApp().getPackageManager().queryIntentActivities((Intent)localObject1, 0);
    int i = 0;
    boolean bool2 = false;
    localObject2 = ((List)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      str = ((PackageInfo)((Iterator)localObject2).next()).applicationInfo.packageName;
      if ((str != null) && (str.equals("com.google.android.googlequicksearchbox"))) {
        i = 1;
      } else if ((str != null) && (str.equals("com.google.android.voicesearch"))) {
        bool1 = true;
      }
    }
    while (i == 0)
    {
      String str;
      return bool1;
    }
    localObject1 = ((List)localObject1).iterator();
    do
    {
      bool1 = bool2;
      if (!((Iterator)localObject1).hasNext()) {
        break;
      }
      localObject2 = ((ResolveInfo)((Iterator)localObject1).next()).activityInfo;
    } while ((((ActivityInfo)localObject2).name == null) || (!((ActivityInfo)localObject2).name.equals("com.google.android.googlequicksearchbox.VoiceSearchActivity")));
    bool1 = true;
    return bool1;
  }
  
  public static void showKeyboard(Context paramContext)
  {
    if (paramContext != null) {
      ((InputMethodManager)paramContext.getSystemService("input_method")).toggleSoftInput(2, 1);
    }
  }
  
  public static void showSoftKeyboard()
  {
    ((InputMethodManager)AviasalesApplication.getContext().getSystemService("input_method")).toggleSoftInput(2, 1);
  }
}
