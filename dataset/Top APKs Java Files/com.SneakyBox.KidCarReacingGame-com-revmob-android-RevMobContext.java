package com.revmob.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.revmob.RevMobTestingMode;
import com.revmob.ads.banner.RevMobBanner;
import com.revmob.client.RevMobClient;
import com.revmob.internal.HTTPHelper;
import com.revmob.internal.RMLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RevMobContext
{
  private static String adTrackingEnabled;
  private static String advertisingId;
  public static String fullUserInfo;
  private final int MAX_INSTALLED_APPS = 40;
  private Context activity;
  private Thread adThread;
  private DisplayMetrics metrics = new DisplayMetrics();
  
  public RevMobContext(Context paramContext)
  {
    this.activity = paramContext;
    ((Activity)this.activity).getWindowManager().getDefaultDisplay().getMetrics(this.metrics);
  }
  
  private boolean api8OrNewer()
  {
    return (!Build.VERSION.RELEASE.startsWith("1.")) && (!Build.VERSION.RELEASE.startsWith("2.0")) && (!Build.VERSION.RELEASE.startsWith("2.1"));
  }
  
  private String getAdTrackingEnabled()
  {
    if (adTrackingEnabled == null) {
      loadAdvertisingInfo();
    }
    return adTrackingEnabled;
  }
  
  private String getAdvertisingId()
  {
    if (advertisingId == null) {
      loadAdvertisingInfo();
    }
    return advertisingId;
  }
  
  private JSONObject getAppJSON()
    throws JSONException
  {
    int i = 0;
    JSONObject localJSONObject = new JSONObject();
    putIfNotEmpty(localJSONObject, "bundle_identifier", this.activity.getPackageName());
    try
    {
      Object localObject = this.activity.getResources();
      putIfNotEmpty(localJSONObject, "app_name", ((Resources)localObject).getText(((Resources)localObject).getIdentifier("app_name", "string", this.activity.getPackageName())).toString());
      try
      {
        localObject = this.activity.getPackageManager().getPackageInfo(this.activity.getPackageName(), 0);
        int j = ((PackageInfo)localObject).versionCode;
        putIfNotEmpty(localJSONObject, "app_version", "" + j);
        putIfNotEmpty(localJSONObject, "app_version_name", ((PackageInfo)localObject).versionName);
        if (!new StoredData(this.activity).isAlreadyTracked()) {
          i = 1;
        }
        if (i != 0) {
          putIfNotEmpty(localJSONObject, "install_not_registered", "true");
        }
        return localJSONObject;
      }
      catch (Exception localException1)
      {
        for (;;) {}
      }
    }
    catch (Exception localException2)
    {
      for (;;) {}
    }
  }
  
  private JSONObject getBannerJSON()
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
  
  private JSONObject getFetchTimeInfo()
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
  
  private JSONObject getIdentitiesJSON()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    putIfNotEmpty(localJSONObject, "android_id", getAndroidID());
    putIfNotEmpty(localJSONObject, "serial", getSerial());
    putIfNotEmpty(localJSONObject, "identifier_for_advertising", getAdvertisingId());
    putIfNotEmpty(localJSONObject, "limit_ad_tracking", getAdTrackingEnabled());
    return localJSONObject;
  }
  
  private JSONArray getInstalledApps()
    throws JSONException
  {
    JSONArray localJSONArray = new JSONArray();
    HTTPHelper.setShouldExtractOtherAppsData(true);
    if (HTTPHelper.getShouldExtractOtherAppsData())
    {
      PackageManager localPackageManager = this.activity.getPackageManager();
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
  
  @TargetApi(8)
  private String getOrientation()
  {
    Display localDisplay = ((WindowManager)this.activity.getSystemService("window")).getDefaultDisplay();
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
  
  private String getSerial()
  {
    return "";
  }
  
  private void loadAdvertisingInfo()
  {
    try
    {
      if (this.adThread == null)
      {
        this.adThread = new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              Object localObject = AdvertisingIdClient.getAdvertisingIdInfo(RevMobContext.this.activity);
              RevMobContext.access$102(((AdvertisingIdClient.AdInfo)localObject).getId());
              if (((AdvertisingIdClient.AdInfo)localObject).isLimitAdTrackingEnabled()) {}
              for (localObject = "true";; localObject = "false")
              {
                RevMobContext.access$202((String)localObject);
                return;
              }
              return;
            }
            catch (Exception localException)
            {
              localException.printStackTrace();
            }
          }
        });
        this.adThread.start();
      }
      return;
    }
    catch (Exception localException)
    {
      RMLog.e("Error with Google Play services.");
    }
  }
  
  private void putIfNotEmpty(JSONObject paramJSONObject, String paramString1, String paramString2)
    throws JSONException
  {
    if ((paramString2 != null) && (!paramString2.equals(""))) {
      paramJSONObject.put(paramString1, paramString2);
    }
  }
  
  public static String toPayload(Activity paramActivity)
  {
    return new RevMobContext(paramActivity).toPayload();
  }
  
  public String getAndroidID()
  {
    return "";
  }
  
  public int getApiVersion()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public JSONObject getDeviceJSON()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("identities", getIdentitiesJSON());
    localJSONObject.put("screen", getDeviceScreenJSON());
    putIfNotEmpty(localJSONObject, "model", getModel());
    putIfNotEmpty(localJSONObject, "api", "" + getApiVersion());
    putIfNotEmpty(localJSONObject, "manufacturer", getManufacturer());
    putIfNotEmpty(localJSONObject, "os_version", getOsVersion());
    putIfNotEmpty(localJSONObject, "orientation", getOrientation());
    putIfNotEmpty(localJSONObject, "locale", getLocale());
    putIfNotEmpty(localJSONObject, "ua", HTTPHelper.getUserAgent());
    return localJSONObject;
  }
  
  public JSONObject getDeviceScreenJSON()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("width", this.metrics.widthPixels);
    localJSONObject.put("height", this.metrics.heightPixels);
    localJSONObject.put("scale", this.metrics.density);
    localJSONObject.put("density_dpi", this.metrics.densityDpi);
    return localJSONObject;
  }
  
  public String getManufacturer()
  {
    return Build.MANUFACTURER;
  }
  
  public String getModel()
  {
    return Build.MODEL;
  }
  
  public String getOsVersion()
  {
    return Build.VERSION.RELEASE;
  }
  
  public JSONObject getSDKJSON()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("name", RevMobClient.SDK_NAME);
    localJSONObject.put("version", RevMobClient.SDK_VERSION);
    localJSONObject.put("testing_mode", RevMobClient.getInstance().getTestingMode().getValue());
    return localJSONObject;
  }
  
  public JSONObject getUserLocation()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      Object localObject = (LocationManager)this.activity.getSystemService("location");
      if (localObject != null)
      {
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
        if (localLocation != null)
        {
          localJSONObject.put("latitude", localLocation.getLatitude());
          localJSONObject.put("longitude", localLocation.getLongitude());
          localJSONObject.put("accuracy", localLocation.getAccuracy());
          return localJSONObject;
        }
        localJSONObject.put("latitude", ((Location)localObject).getLatitude());
        localJSONObject.put("longitude", ((Location)localObject).getLongitude());
        localJSONObject.put("accuracy", ((Location)localObject).getAccuracy());
      }
      return localJSONObject;
    }
    catch (Exception localException) {}
    return localJSONObject;
  }
  
  public boolean isSimulator()
  {
    return (getModel().contains("sdk")) || (getModel().contains("Emulator"));
  }
  
  public void printEnvironmentInformation(String paramString)
  {
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
      RMLog.i("Screen size: " + this.metrics.widthPixels + "," + this.metrics.heightPixels);
      RMLog.i("Density scale: " + this.metrics.density);
      RMLog.i("Density dpi: " + this.metrics.densityDpi);
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
  
  public String toPayload()
  {
    if (fullUserInfo == null) {
      fullUserInfo = userInfoToString(true);
    }
    return userInfoToString(false);
  }
  
  public String userInfoToString(boolean paramBoolean)
  {
    try
    {
      Object localObject = new JSONObject();
      ((JSONObject)localObject).put("device", getDeviceJSON());
      ((JSONObject)localObject).put("sdk", getSDKJSON());
      ((JSONObject)localObject).put("app", getAppJSON());
      if (paramBoolean == true)
      {
        ((JSONObject)localObject).put("userLocation", getUserLocation());
        ((JSONObject)localObject).put("installedApps", getInstalledApps());
      }
      if ((RevMobClient.t0 != 0L) && (RevMobClient.t1 != 0L) && (RevMobClient.t2 != 0L) && (RevMobClient.t3 != 0L)) {
        ((JSONObject)localObject).put("time", getFetchTimeInfo());
      }
      if (RevMobBanner.isBannerImpression)
      {
        ((JSONObject)localObject).put("bannerImpressions", getBannerJSON());
        RevMobBanner.setBannerImpression(false);
      }
      if (RevMobClient.getInstance().getTestingMode() != RevMobTestingMode.DISABLED)
      {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("response", RevMobClient.getInstance().getTestingMode().getValue());
        ((JSONObject)localObject).put("testing", localJSONObject);
      }
      localObject = ((JSONObject)localObject).toString();
      return localObject;
    }
    catch (JSONException localJSONException) {}
    return null;
  }
}
