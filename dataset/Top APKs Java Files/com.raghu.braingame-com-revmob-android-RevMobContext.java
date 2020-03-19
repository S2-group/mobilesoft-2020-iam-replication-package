package com.revmob.android;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.revmob.RevMobTestingMode;
import com.revmob.client.RevMobClient;
import com.revmob.internal.HTTPHelper;
import com.revmob.internal.RMLog;
import java.io.IOException;
import java.lang.reflect.Field;
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
  private final int MAX_INSTALLED_APPS = 40;
  private Context activity;
  private DisplayMetrics metrics = new DisplayMetrics();
  
  public RevMobContext(Context paramContext)
  {
    this.activity = paramContext;
    ((Activity)this.activity).getWindowManager().getDefaultDisplay().getMetrics(this.metrics);
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
    if (HTTPHelper.getShouldExtractOtherAppsData())
    {
      PackageManager localPackageManager = this.activity.getPackageManager();
      Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
      for (;;)
      {
        ApplicationInfo localApplicationInfo;
        if (localIterator.hasNext())
        {
          localApplicationInfo = (ApplicationInfo)localIterator.next();
          if (localJSONArray.length() < 40) {}
        }
        else
        {
          return localJSONArray;
        }
        if ((localApplicationInfo.flags & 0x1) != 1)
        {
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("packageName", localApplicationInfo.packageName);
          localJSONObject.put("name", localApplicationInfo.loadLabel(localPackageManager));
          localJSONArray.put(localJSONObject);
        }
      }
    }
    return null;
  }
  
  public static String getLanguage()
  {
    return Locale.getDefault().getLanguage();
  }
  
  public static String getLocale()
  {
    return Locale.getDefault().toString().replace('_', '-');
  }
  
  private JSONObject getLocationJSON()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    UserInformation.getInstance().addLocationData(localJSONObject);
    return localJSONObject;
  }
  
  private String getSerial()
  {
    try
    {
      String str = (String)Build.class.getDeclaredField("SERIAL").get(String.class);
      return str;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      return null;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      return null;
    }
    catch (SecurityException localSecurityException)
    {
      return null;
    }
    catch (IllegalArgumentException localIllegalArgumentException) {}
    return null;
  }
  
  private JSONObject getSocialJSON()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    UserInformation.getInstance().addUserData(localJSONObject);
    return localJSONObject;
  }
  
  private void loadAdvertisingInfo()
  {
    try
    {
      if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.activity) == 0)
      {
        if (this.activity.getPackageManager().getApplicationInfo(this.activity.getPackageName(), 128).metaData.getInt("com.google.android.gms.version") == 0)
        {
          RMLog.e("You must set the com.google.android.gms.version value in the AndroidManifest.xml file.");
          return;
        }
        new Thread()
        {
          public void run()
          {
            Object localObject = null;
            try
            {
              AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(RevMobContext.this.activity);
              localObject = localInfo;
            }
            catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
            {
              for (;;) {}
            }
            catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
            {
              for (;;) {}
            }
            catch (IOException localIOException)
            {
              for (;;) {}
            }
            RevMobContext.access$102(((AdvertisingIdClient.Info)localObject).getId());
            if (((AdvertisingIdClient.Info)localObject).isLimitAdTrackingEnabled()) {}
            for (localObject = "true";; localObject = "false")
            {
              RevMobContext.access$202((String)localObject);
              return;
            }
          }
        }.start();
        return;
      }
    }
    catch (Exception localException) {}
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
    try
    {
      String str = Settings.Secure.getString(this.activity.getContentResolver(), "android_id");
      return str;
    }
    catch (Throwable localThrowable) {}
    return null;
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
    if (HTTPHelper.getShouldExtractOtherAppsData()) {
      localJSONObject.put("installedApps", getInstalledApps());
    }
    putIfNotEmpty(localJSONObject, "model", getModel());
    putIfNotEmpty(localJSONObject, "api", "" + getApiVersion());
    putIfNotEmpty(localJSONObject, "manufacturer", getManufacturer());
    putIfNotEmpty(localJSONObject, "os_version", getOsVersion());
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
      return;
      RMLog.i("RevMob SDK Version: " + RevMobClient.SDK_VERSION);
    }
  }
  
  public String toPayload()
  {
    try
    {
      Object localObject = new JSONObject();
      ((JSONObject)localObject).put("device", getDeviceJSON());
      ((JSONObject)localObject).put("sdk", getSDKJSON());
      if (HTTPHelper.getShouldExtractSocial()) {
        ((JSONObject)localObject).put("social", getSocialJSON());
      }
      if (HTTPHelper.getShouldExtractGeolocation()) {
        ((JSONObject)localObject).put("location", getLocationJSON());
      }
      ((JSONObject)localObject).put("app", getAppJSON());
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
