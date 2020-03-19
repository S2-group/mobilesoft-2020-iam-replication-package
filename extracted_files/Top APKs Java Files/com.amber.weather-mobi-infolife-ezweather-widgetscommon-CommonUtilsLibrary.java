package mobi.infolife.ezweather.widgetscommon;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;

public class CommonUtilsLibrary
{
  public static final String ACTION_DOWNLOAD_WEATHER_SUCCESS = "com.amber.weather.update.weather.action";
  public static final String REFRESH_ACTION_SOURCE = "refresh_action_source";
  static final String TAG = CommonUtilsLibrary.class.getName();
  public static final String UPDATE_VIEW_AFTER_CHANGE_CONFIG_ACTION = "update.view.change.config.date.action";
  public static final String WEATHER_DATA_ID_PARAM = "weather_data_id";
  
  public CommonUtilsLibrary() {}
  
  public static IpLocation getBestIPLocation(int paramInt)
  {
    return IpLocation.getBetter(getCityInfoFromJson(paramInt / 2, "http://api.ipinfodb.com/v3/ip-city/?key=57afa841d97146c059d6fb4b5ba39903ffaad522e91fa22cca8e98f903db25b4&format=json"), getCityInfoFromJson(paramInt / 2, "http://f.loca.amberweather.com/ipcity.php"));
  }
  
  public static IpLocation getCityInfoFromJson(int paramInt, String paramString)
  {
    Object localObject;
    StringBuilder localStringBuilder;
    try
    {
      paramString = new URL(paramString).openConnection();
      paramString.setConnectTimeout(paramInt);
      paramString = (HttpURLConnection)paramString;
      if (paramString.getResponseCode() == 200)
      {
        paramString = paramString.getInputStream();
        localObject = new BufferedReader(new InputStreamReader(paramString, "utf-8"));
        localStringBuilder = new StringBuilder();
        for (;;)
        {
          String str = ((BufferedReader)localObject).readLine();
          if (str == null) {
            break;
          }
          localStringBuilder.append(str + "\n");
        }
      }
      return null;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    boolean bool;
    do
    {
      paramString.close();
      paramString = new JSONObject(localStringBuilder.toString());
      localObject = new IpLocation();
      ((IpLocation)localObject).setCityName(paramString.get("cityName").toString());
      ((IpLocation)localObject).setCountryName(paramString.get("countryName").toString());
      double d1 = Double.parseDouble(paramString.get("latitude").toString());
      double d2 = Double.parseDouble(paramString.get("longitude").toString());
      ((IpLocation)localObject).setLatitude(d1);
      ((IpLocation)localObject).setLongitude(d2);
      ((IpLocation)localObject).setRegionName(paramString.get("regionName").toString());
      bool = ((IpLocation)localObject).isLatAndLngAvailable();
    } while (!bool);
    return localObject;
  }
  
  public static int getCurrentVersionCode(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = paramContext.getPackageName();
    try
    {
      int i = localPackageManager.getPackageInfo(paramContext, 0).versionCode;
      return i;
    }
    catch (Exception paramContext) {}
    return 1;
  }
  
  public static String getCurrentVersionName(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = paramContext.getPackageName();
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramContext, 0).versionName;
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "Beta";
  }
  
  public static String getCurrentVersionNameWithTabPrefix(Context paramContext)
  {
    return "\t" + getCurrentVersionName(paramContext);
  }
  
  public static boolean isAvilible(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
  
  public static boolean isServiceAlive(Context paramContext, String paramString)
  {
    List localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
    if (localList.size() == 0) {}
    for (;;)
    {
      return false;
      int i = 0;
      while (i < localList.size())
      {
        ComponentName localComponentName = ((ActivityManager.RunningServiceInfo)localList.get(i)).service;
        if ((localComponentName.getClassName().toString().equals(paramString)) && (paramContext.getPackageName().equals(localComponentName.getPackageName()))) {}
        for (int j = 1; j != 0; j = 0) {
          return true;
        }
        i += 1;
      }
    }
  }
  
  public static boolean isWifiAvailable(Context paramContext)
  {
    boolean bool2 = false;
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    boolean bool1 = bool2;
    int i;
    if (paramContext != null)
    {
      paramContext = paramContext.getAllNetworkInfo();
      bool1 = bool2;
      if (paramContext != null) {
        i = 0;
      }
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < paramContext.length)
      {
        if ((paramContext[i].getState() == NetworkInfo.State.CONNECTED) && (paramContext[i].getTypeName().equals("WIFI"))) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static void l(String paramString) {}
  
  public static void log(String paramString) {}
  
  public static byte[] readStream(InputStream paramInputStream)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        break;
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
    localByteArrayOutputStream.close();
    paramInputStream.close();
    return localByteArrayOutputStream.toByteArray();
  }
  
  public static void sendAfterConfigChangedBroadcast(Context paramContext, int paramInt)
  {
    Intent localIntent = new Intent("update.view.change.config.date.action");
    localIntent.putExtra("weather_data_id", paramInt);
    paramContext.sendBroadcast(localIntent);
  }
  
  public static void sendDeletCityBroadcast(Context paramContext, int paramInt)
  {
    Intent localIntent = new Intent("delete_city_at_main_app");
    localIntent.putExtra("delete_city_id", paramInt);
    paramContext.sendBroadcast(localIntent);
  }
  
  public static void sendDownloadWeatherSuccessBroadcast(Context paramContext, int paramInt, boolean paramBoolean)
  {
    Intent localIntent = new Intent("com.amber.weather.update.weather.action");
    localIntent.putExtra("weather_data_id", paramInt);
    localIntent.putExtra("refresh_action_source", paramBoolean);
    paramContext.sendBroadcast(localIntent);
  }
  
  public static void setLocale(Activity paramActivity)
  {
    Object localObject = PreferencesLibrary.getLanguage(paramActivity);
    if (!TextUtils.equals((CharSequence)localObject, "auto"))
    {
      if (!((String)localObject).contains("_")) {
        break label86;
      }
      localObject = ((String)localObject).split("_");
    }
    label86:
    for (localObject = new Locale(localObject[0], localObject[1]);; localObject = new Locale((String)localObject))
    {
      Locale.setDefault((Locale)localObject);
      Configuration localConfiguration = new Configuration();
      localConfiguration.locale = ((Locale)localObject);
      paramActivity.getBaseContext().getResources().updateConfiguration(localConfiguration, paramActivity.getBaseContext().getResources().getDisplayMetrics());
      return;
    }
  }
  
  public static void showShortToast(Context paramContext, String paramString)
  {
    Toast.makeText(paramContext, paramString, 0).show();
  }
  
  public static void startActivity(Intent paramIntent, Context paramContext, String paramString1, String paramString2)
  {
    paramIntent.setComponent(new ComponentName(paramString1, paramString2));
    if ("mobi.infolife.store.activity.StoreActivity".equals(paramString2)) {
      paramIntent.setFlags(32768);
    }
    for (;;)
    {
      paramContext.startActivity(paramIntent);
      return;
      paramIntent.setFlags(268435456);
    }
  }
  
  public static void updateApp(Context paramContext)
  {
    String str = paramContext.getPackageName();
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str)));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      try
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + str)));
        return;
      }
      catch (Exception paramContext) {}
    }
  }
}
