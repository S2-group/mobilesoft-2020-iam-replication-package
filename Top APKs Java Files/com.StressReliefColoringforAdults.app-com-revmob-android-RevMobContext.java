package com.revmob.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.revmob.RevMobAdsListener;
import com.revmob.RevMobTestingMode;
import com.revmob.ads.banner.RevMobBanner;
import com.revmob.client.RevMobClient;
import com.revmob.client.RevMobClientListener;
import com.revmob.internal.HTTPHelper;
import com.revmob.internal.RMLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RevMobContext
{
  private static Context activity;
  public static Thread adThread;
  private static String adTrackingEnabled;
  private static String advertisingId;
  private static String appId;
  public static boolean getInstalledApps = false;
  private static RevMobClientListener listener;
  private static DisplayMetrics metrics = new DisplayMetrics();
  private static String[] permissions;
  private static RevMobAdsListener publisherListener;
  private final int MAX_INSTALLED_APPS = 40;
  
  public RevMobContext() {}
  
  private static boolean api8OrNewer()
  {
    return (!Build.VERSION.RELEASE.startsWith("1.")) && (!Build.VERSION.RELEASE.startsWith("2.0")) && (!Build.VERSION.RELEASE.startsWith("2.1"));
  }
  
  private static String getAdTrackingEnabled()
  {
    return adTrackingEnabled;
  }
  
  private static String getAdvertisingId()
  {
    return advertisingId;
  }
  
  public static String getAndroidID()
  {
    return "";
  }
  
  public static int getApiVersion()
  {
    return Build.VERSION.SDK_INT;
  }
  
  private static JSONObject getAppJSON()
    throws JSONException
  {
    int i = 0;
    JSONObject localJSONObject = new JSONObject();
    putIfNotEmpty(localJSONObject, "bundle_identifier", activity.getPackageName());
    try
    {
      Object localObject = activity.getResources();
      putIfNotEmpty(localJSONObject, "app_name", ((Resources)localObject).getText(((Resources)localObject).getIdentifier("app_name", "string", activity.getPackageName())).toString());
      try
      {
        localObject = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
        int j = ((PackageInfo)localObject).versionCode;
        putIfNotEmpty(localJSONObject, "app_version", "" + j);
        if (getAppPermissions() != null) {
          putArrayIfNotEmpty(localJSONObject, "permissions", getAppPermissions());
        }
        putIfNotEmpty(localJSONObject, "app_version_name", ((PackageInfo)localObject).versionName);
      }
      catch (Exception localException1)
      {
        for (;;) {}
      }
      if (!new StoredData(activity).isAlreadyTracked()) {
        i = 1;
      }
      if (i != 0) {
        putIfNotEmpty(localJSONObject, "install_not_registered", "true");
      }
      return localJSONObject;
    }
    catch (Exception localException2)
    {
      for (;;) {}
    }
  }
  
  public static JSONArray getAppPermissions()
  {
    try
    {
      String str = activity.getApplicationContext().getPackageName();
      permissions = activity.getPackageManager().getPackageInfo(str, 4096).requestedPermissions;
      return new JSONArray(Arrays.asList(permissions));
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return null;
  }
  
  public static Context getApplicationContext()
  {
    return activity;
  }
  
  private static JSONObject getBannerJSON()
    throws JSONException
  {
    JSONObject localJSONObject1 = new JSONObject();
    localJSONObject1.put("bannerCount", RevMobBanner.bannerCount);
    JSONObject localJSONObject2 = new JSONObject();
    int i = 0;
    while (i < RevMobBanner.usedCampaigns.size())
    {
      localJSONObject2.put(String.valueOf(i + 1), RevMobBanner.usedCampaigns.get(i));
      i += 1;
    }
    localJSONObject1.put("campaigns", localJSONObject2);
    return localJSONObject1;
  }
  
  public static JSONObject getDeviceJSON()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("screen", getDeviceScreenJSON());
    putIfNotEmpty(localJSONObject, "model", getModel());
    putIfNotEmpty(localJSONObject, "api", "" + getApiVersion());
    putIfNotEmpty(localJSONObject, "manufacturer", getManufacturer());
    putIfNotEmpty(localJSONObject, "os_version", getOsVersion());
    putIfNotEmpty(localJSONObject, "orientation", getOrientation());
    putIfNotEmpty(localJSONObject, "locale", getLocale());
    putIfNotEmpty(localJSONObject, "ua", HTTPHelper.getUserAgent());
    if (HTTPHelper.getShouldExtractGeolocation()) {
      localJSONObject.put("location", getUserLocation());
    }
    putIfNotEmpty(localJSONObject, "android_id", getAndroidID());
    putIfNotEmpty(localJSONObject, "serial", getSerial());
    putIfNotEmpty(localJSONObject, "identifier_for_advertising", getAdvertisingId());
    putIfNotEmpty(localJSONObject, "limit_ad_tracking", getAdTrackingEnabled());
    return localJSONObject;
  }
  
  public static JSONObject getDeviceScreenJSON()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("width", metrics.widthPixels);
    localJSONObject.put("height", metrics.heightPixels);
    localJSONObject.put("scale", metrics.density);
    localJSONObject.put("density_dpi", metrics.densityDpi);
    return localJSONObject;
  }
  
  private static JSONObject getFetchTimeInfo()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    double d1 = RevMobClient.t1 - RevMobClient.t0;
    double d2 = RevMobClient.t2 - RevMobClient.t1;
    double d3 = RevMobClient.t3 - RevMobClient.t2;
    localJSONObject.put("fetchTime", d1 / 1000.0D);
    localJSONObject.put("sdkTime", d2 / 1000.0D);
    localJSONObject.put("creativeTime", d3 / 1000.0D);
    return localJSONObject;
  }
  
  private static JSONObject getIdentitiesJSON()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    putIfNotEmpty(localJSONObject, "android_id", getAndroidID());
    putIfNotEmpty(localJSONObject, "serial", getSerial());
    putIfNotEmpty(localJSONObject, "identifier_for_advertising", getAdvertisingId());
    putIfNotEmpty(localJSONObject, "limit_ad_tracking", getAdTrackingEnabled());
    return localJSONObject;
  }
  
  private static JSONArray getInstalledApps()
    throws JSONException
  {
    JSONArray localJSONArray = new JSONArray();
    HTTPHelper.setShouldExtractOtherAppsData(true);
    if (HTTPHelper.getShouldExtractOtherAppsData())
    {
      PackageManager localPackageManager = activity.getPackageManager();
      Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
      for (;;)
      {
        localObject = localJSONArray;
        if (!localIterator.hasNext()) {
          break;
        }
        localObject = (ApplicationInfo)localIterator.next();
        if ((((ApplicationInfo)localObject).flags & 0x1) != 1)
        {
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("packageName", ((ApplicationInfo)localObject).packageName);
          localJSONObject.put("name", ((ApplicationInfo)localObject).loadLabel(localPackageManager));
          localJSONArray.put(localJSONObject);
        }
      }
    }
    Object localObject = null;
    return localObject;
  }
  
  public static String getLanguage()
  {
    return Locale.getDefault().getLanguage();
  }
  
  public static String getLocale()
  {
    return Locale.getDefault().toString().replace('_', '-');
  }
  
  public static String getManufacturer()
  {
    return Build.MANUFACTURER;
  }
  
  public static String getModel()
  {
    return Build.MODEL;
  }
  
  @TargetApi(8)
  private static String getOrientation()
  {
    Display localDisplay = ((WindowManager)activity.getSystemService("window")).getDefaultDisplay();
    if (api8OrNewer()) {}
    for (int i = localDisplay.getRotation(); i == 0; i = localDisplay.getOrientation()) {
      return "0";
    }
    if (i == 1) {
      return "90";
    }
    if (i == 2) {
      return "180";
    }
    if (i == 3) {
      return "270";
    }
    return "-1";
  }
  
  public static String getOsVersion()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static JSONObject getSDKJSON()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("name", RevMobClient.SDK_NAME);
    localJSONObject.put("version", RevMobClient.SDK_VERSION);
    localJSONObject.put("testing_mode", RevMobClient.getInstance().getTestingMode().getValue());
    return localJSONObject;
  }
  
  private static String getSerial()
  {
    return "";
  }
  
  private static JSONObject getSocialJSON()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    UserData.addUserInfo(localJSONObject);
    return localJSONObject;
  }
  
  public static JSONObject getUserLocation()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    Object localObject;
    try
    {
      localObject = (LocationManager)activity.getSystemService("location");
      if ((localObject == null) || ((activity.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0) && (activity.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0))) {
        return localJSONObject;
      }
      Location localLocation = ((LocationManager)localObject).getLastKnownLocation("gps");
      localObject = ((LocationManager)localObject).getLastKnownLocation("network");
      if ((localLocation != null) && (localObject != null))
      {
        if (localLocation.getTime() > ((Location)localObject).getTime())
        {
          localJSONObject.put("latitude", localLocation.getLatitude());
          localJSONObject.put("longitude", localLocation.getLongitude());
          localJSONObject.put("accuracy", localLocation.getAccuracy());
          return localJSONObject;
        }
        localJSONObject.put("latitude", ((Location)localObject).getLatitude());
        localJSONObject.put("longitude", ((Location)localObject).getLongitude());
        localJSONObject.put("accuracy", ((Location)localObject).getAccuracy());
        return localJSONObject;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return localJSONObject;
    }
    if (localException != null)
    {
      localJSONObject.put("latitude", localException.getLatitude());
      localJSONObject.put("longitude", localException.getLongitude());
      localJSONObject.put("accuracy", localException.getAccuracy());
      return localJSONObject;
    }
    if (localObject != null)
    {
      localJSONObject.put("latitude", ((Location)localObject).getLatitude());
      localJSONObject.put("longitude", ((Location)localObject).getLongitude());
      localJSONObject.put("accuracy", ((Location)localObject).getAccuracy());
    }
    return localJSONObject;
  }
  
  public static boolean isApplicationStarted()
  {
    return activity != null;
  }
  
  public static boolean isSimulator()
  {
    return (getModel().contains("sdk")) || (getModel().contains("Emulator"));
  }
  
  public static void loadAdvertisingInfo(String paramString, RevMobClientListener paramRevMobClientListener, RevMobAdsListener paramRevMobAdsListener, Activity paramActivity)
  {
    try
    {
      appId = paramString;
      listener = paramRevMobClientListener;
      publisherListener = paramRevMobAdsListener;
      activity = paramActivity;
      if (adThread == null)
      {
        adThread = new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              Object localObject = AdvertisingIdClient.getAdvertisingIdInfo(RevMobContext.activity);
              RevMobContext.access$102(((AdvertisingIdClient.AdInfo)localObject).getId());
              if (((AdvertisingIdClient.AdInfo)localObject).isLimitAdTrackingEnabled()) {}
              for (localObject = "true";; localObject = "false")
              {
                RevMobContext.access$202((String)localObject);
                RevMobClient.getInstance().startSession(RevMobContext.appId, RevMobContext.toPayload((Activity)RevMobContext.activity), RevMobContext.listener, RevMobContext.publisherListener);
                return;
              }
              return;
            }
            catch (Exception localException)
            {
              RMLog.e("Error with Google Play Services: " + localException.getMessage());
              localException.printStackTrace();
            }
          }
        });
        adThread.start();
      }
      return;
    }
    catch (Exception paramString)
    {
      RMLog.e("Error loading advertising info: " + paramString.getMessage());
    }
  }
  
  public static void printEnvironmentInformation(String paramString, Activity paramActivity)
  {
    activity = paramActivity;
    if (RevMobClient.SDK_SOURCE_NAME != null) {
      RMLog.i("RevMob SDK Version: " + RevMobClient.SDK_VERSION + " (" + RevMobClient.SDK_SOURCE_NAME + "-" + RevMobClient.SDK_SOURCE_VERSION + ")");
    }
    for (;;)
    {
      RMLog.i("App ID: " + paramString);
      RMLog.i("IP Address: " + HTTPHelper.getIpAddress());
      RMLog.i("Simulator: " + isSimulator());
      RMLog.i("OS Version: " + getOsVersion());
      RMLog.i("Android API: " + getApiVersion());
      RMLog.i("Manufacturer: " + getManufacturer());
      RMLog.i("Model: " + getModel());
      RMLog.i("Android ID: " + getAndroidID());
      RMLog.i("Serial number: " + getSerial());
      RMLog.i("ID for Advertising: " + getAdvertisingId());
      RMLog.i("Limit Ad Tracking: " + getAdTrackingEnabled());
      RMLog.i("Language: " + getLanguage());
      RMLog.i("Locale: " + getLocale());
      RMLog.i("User Agent: " + HTTPHelper.getUserAgent());
      RMLog.i("Screen size: " + metrics.widthPixels + "," + metrics.heightPixels);
      RMLog.i("Density scale: " + metrics.density);
      RMLog.i("Density dpi: " + metrics.densityDpi);
      try
      {
        RMLog.i("Installed Apps: " + getInstalledApps());
        try
        {
          RMLog.i("User Location: " + getUserLocation());
          return;
        }
        catch (JSONException paramString)
        {
          paramString.printStackTrace();
        }
        RMLog.i("RevMob SDK Version: " + RevMobClient.SDK_VERSION);
      }
      catch (JSONException paramString)
      {
        for (;;)
        {
          paramString.printStackTrace();
        }
      }
    }
  }
  
  private static void putArrayIfNotEmpty(JSONObject paramJSONObject, String paramString, JSONArray paramJSONArray)
    throws JSONException
  {
    if ((paramJSONArray != null) && (!paramJSONArray.equals(""))) {
      paramJSONObject.put(paramString, paramJSONArray);
    }
  }
  
  private static void putIfNotEmpty(JSONObject paramJSONObject, String paramString1, String paramString2)
    throws JSONException
  {
    if ((paramString2 != null) && (!paramString2.equals(""))) {
      paramJSONObject.put(paramString1, paramString2);
    }
  }
  
  public static JSONObject revmobJSON()
  {
    try
    {
      JSONObject localJSONObject1 = new JSONObject();
      localJSONObject1.put("device", getDeviceJSON());
      localJSONObject1.put("sdk", getSDKJSON());
      localJSONObject1.put("app", getAppJSON());
      if (HTTPHelper.getShouldExtractSocial()) {
        localJSONObject1.put("social", getSocialJSON());
      }
      if (getInstalledApps)
      {
        getInstalledApps = false;
        localJSONObject1.put("installedApps", getInstalledApps());
      }
      if ((RevMobClient.t0 != 0L) && (RevMobClient.t1 != 0L) && (RevMobClient.t2 != 0L) && (RevMobClient.t3 != 0L)) {
        localJSONObject1.put("time", getFetchTimeInfo());
      }
      if (RevMobBanner.isBannerImpression)
      {
        localJSONObject1.put("bannerImpressions", getBannerJSON());
        RevMobBanner.setBannerImpression(false);
      }
      if (RevMobClient.getInstance().getTestingMode() != RevMobTestingMode.DISABLED)
      {
        JSONObject localJSONObject2 = new JSONObject();
        localJSONObject2.put("response", RevMobClient.getInstance().getTestingMode().getValue());
        localJSONObject1.put("testing", localJSONObject2);
      }
      return localJSONObject1;
    }
    catch (JSONException localJSONException) {}
    return null;
  }
  
  public static String revmobJSONString()
  {
    return revmobJSON().toString();
  }
  
  public static String toPayload(Activity paramActivity)
  {
    activity = paramActivity;
    ((Activity)activity).getWindowManager().getDefaultDisplay().getMetrics(metrics);
    return revmobJSONString();
  }
}
