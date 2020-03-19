import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class eea
{
  private static long a(long paramLong)
  {
    return paramLong / 1024L;
  }
  
  public static JSONArray a(Context paramContext)
  {
    JSONArray localJSONArray = new JSONArray();
    List localList = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    if (i < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      if (localPackageInfo.versionName == null) {}
      for (;;)
      {
        i += 1;
        break;
        if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
          try
          {
            localJSONArray.put(new JSONObject().put("appName", localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString()).put("packageName", localPackageInfo.packageName).put("versionName", localPackageInfo.versionName).put("versionCode", localPackageInfo.versionCode));
          }
          catch (JSONException localJSONException)
          {
            eeb.a(edx.a, "UserDetails", "Error parsing json : %s", new Object[] { localJSONException });
          }
        }
      }
    }
    return localJSONArray;
  }
  
  protected static JSONObject a()
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("manufacturer", Build.MANUFACTURER);
      localJSONObject.put("osVersion", Build.VERSION.SDK_INT);
      localJSONObject.put("brand", Build.BRAND);
      localJSONObject.put("model", Build.MODEL);
      return localJSONObject;
    }
    catch (Exception localException)
    {
      eeb.a(edx.a, "UserDetails", "Error in fetching deviceInfo: %s", new Object[] { localException });
    }
    return null;
  }
  
  public static Location b(Context paramContext)
  {
    LocationManager localLocationManager = (LocationManager)paramContext.getApplicationContext().getSystemService("location");
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (eeb.c(paramContext, "ACCESS_FINE_LOCATION"))
    {
      localObject1 = localObject2;
      if (eeb.a("gps", localLocationManager)) {
        localObject1 = localLocationManager.getLastKnownLocation("gps");
      }
    }
    if ((localObject1 == null) && ((eeb.c(paramContext, "ACCESS_COARSE_LOCATION")) || (eeb.c(paramContext, "ACCESS_FINE_LOCATION"))) && (eeb.a("network", localLocationManager))) {
      return localLocationManager.getLastKnownLocation("network");
    }
    return localObject1;
  }
  
  protected static String b()
  {
    return Locale.getDefault().getDisplayLanguage();
  }
  
  protected static JSONObject c()
  {
    try
    {
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      long l2;
      long l3;
      if (Build.VERSION.SDK_INT > 18)
      {
        l2 = localStatFs.getBlockSizeLong();
        l3 = localStatFs.getBlockCountLong();
      }
      int i;
      for (long l1 = localStatFs.getAvailableBlocksLong();; l1 = i)
      {
        l3 = a(l3 * l2);
        l1 = a(l1 * l2);
        return new JSONObject().put("total", l3).put("free", l1);
        l2 = localStatFs.getBlockSize();
        l3 = localStatFs.getBlockCount();
        i = localStatFs.getAvailableBlocks();
      }
      return null;
    }
    catch (Exception localException)
    {
      eeb.a(edx.a, "UserDetails", "Exception: ", new Object[] { localException });
    }
  }
  
  public static JSONObject c(Context paramContext)
  {
    paramContext = b(paramContext);
    if (paramContext == null)
    {
      eeb.a(edx.a, "UserDetails", "Location is null");
      return null;
    }
    eeb.a(edx.a, "UserDetails", "Location is null");
    try
    {
      paramContext = new JSONObject().put("type", paramContext.getProvider()).put("point", new JSONArray().put(paramContext.getLongitude()).put(paramContext.getLatitude()));
      return paramContext;
    }
    catch (JSONException paramContext)
    {
      eeb.a(edx.a, "UserDetails", "Error parsing json : %s", new Object[] { paramContext });
    }
    return null;
  }
  
  protected static String d()
  {
    TimeZone localTimeZone = TimeZone.getDefault();
    eeb.a(edx.a, "UserDetails", "TimeZone - %s", new Object[] { localTimeZone.getID() });
    return localTimeZone.getID();
  }
  
  protected static JSONObject d(Context paramContext)
  {
    try
    {
      paramContext = AdvertisingIdClient.getAdvertisingIdInfo(paramContext);
      if (paramContext == null) {
        return null;
      }
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("aid", paramContext.getId());
      localJSONObject.put("optOut", paramContext.isLimitAdTrackingEnabled());
      return localJSONObject;
    }
    catch (Exception paramContext)
    {
      eeb.a(edx.a, "UserDetails", "Error in fetching androidId: %s", new Object[] { paramContext });
      return null;
    }
    catch (Error paramContext)
    {
      eeb.a(edx.a, "UserDetails", "package not found androidId: %s", new Object[] { paramContext });
    }
    return null;
  }
  
  protected static String e(Context paramContext)
  {
    if (!eeb.c(paramContext, "READ_PHONE_STATE")) {
      return null;
    }
    try
    {
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      return paramContext;
    }
    catch (Exception paramContext)
    {
      eeb.a(edx.a, "UserDetails", "Error in fetching deviceId: %s", new Object[] { paramContext });
    }
    return null;
  }
  
  protected static JSONObject f(Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      String str = paramContext.getPackageName();
      int i = paramContext.getPackageManager().getPackageInfo(str, 0).versionCode;
      paramContext = paramContext.getPackageManager().getPackageInfo(str, 0).versionName;
      localJSONObject.put("verCode", i);
      localJSONObject.put("verName", paramContext);
      return localJSONObject;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      eeb.a(edx.a, "UserDetails", "Exception: ", new Object[] { paramContext });
    }
    return null;
  }
  
  protected static JSONObject g(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    int k = 0;
    int j = 0;
    if (i < paramContext.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
      if (localPackageInfo.versionName == null) {}
      for (;;)
      {
        i += 1;
        break;
        if ((localPackageInfo.applicationInfo.flags & 0x1) != 0) {
          j += 1;
        } else {
          k += 1;
        }
      }
    }
    try
    {
      paramContext = new JSONObject().put("system", j).put("installed", k);
      return paramContext;
    }
    catch (JSONException paramContext)
    {
      eeb.a(edx.a, "UserDetails", "Error parsing json : %s", new Object[] { paramContext });
    }
    return null;
  }
  
  public static String h(Context paramContext)
  {
    if (k(paramContext) == 0) {
      return l(paramContext);
    }
    if (k(paramContext) == 1) {
      return "WIFI";
    }
    return null;
  }
  
  protected static JSONObject i(Context paramContext)
  {
    for (;;)
    {
      try
      {
        paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
        Object localObject = new DisplayMetrics();
        paramContext.getMetrics((DisplayMetrics)localObject);
        int k = ((DisplayMetrics)localObject).widthPixels;
        int m = ((DisplayMetrics)localObject).heightPixels;
        int i = k;
        int j = m;
        if (Build.VERSION.SDK_INT >= 14)
        {
          int n = Build.VERSION.SDK_INT;
          i = k;
          j = m;
          if (n < 17) {
            i = k;
          }
        }
        try
        {
          k = ((Integer)Display.class.getMethod("getRawWidth", new Class[0]).invoke(paramContext, new Object[0])).intValue();
          i = k;
          j = ((Integer)Display.class.getMethod("getRawHeight", new Class[0]).invoke(paramContext, new Object[0])).intValue();
          i = k;
        }
        catch (Exception localException)
        {
          try
          {
            localObject = new Point();
            k = i;
            Display.class.getMethod("getRealSize", new Class[] { Point.class }).invoke(paramContext, new Object[] { localObject });
            k = i;
            i = ((Point)localObject).x;
            k = i;
            m = ((Point)localObject).y;
            j = m;
            paramContext = new JSONObject();
            paramContext.put("w", i).put("h", j);
            return paramContext;
          }
          catch (Exception paramContext)
          {
            eeb.a(edx.a, "UserDetails", "FetchingScreenDimensions: %s", new Object[] { paramContext });
          }
          localException = localException;
          eeb.a(edx.a, "UserDetails", "FetchingScreenDimensions: %s", new Object[] { localException });
          j = m;
          continue;
        }
        m = Build.VERSION.SDK_INT;
        k = i;
        if (m >= 17) {
          k = i;
        }
        i = k;
      }
      catch (JSONException paramContext)
      {
        eeb.a(edx.a, "UserDetails", "Error parsing json : %s", new Object[] { paramContext });
        return null;
      }
    }
  }
  
  public static String[] j(Context paramContext)
  {
    eeb.a(edx.a, "UserDetails", "getEmailFromAccounts called");
    if (!eeb.c(paramContext, "GET_ACCOUNTS")) {
      return null;
    }
    Pattern localPattern = Patterns.EMAIL_ADDRESS;
    paramContext = AccountManager.get(paramContext).getAccounts();
    ArrayList localArrayList = new ArrayList();
    int j = paramContext.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramContext[i];
      if (localPattern.matcher(localObject.name).matches()) {
        localArrayList.add(localObject.name);
      }
      i += 1;
    }
    return (String[])localArrayList.toArray(new String[0]);
  }
  
  private static int k(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if (paramContext != null)
    {
      if (paramContext.getType() == 1) {
        return 1;
      }
      if (paramContext.getType() == 0) {
        return 0;
      }
      return paramContext.getType();
    }
    return -1;
  }
  
  private static String l(Context paramContext)
  {
    switch (((TelephonyManager)paramContext.getSystemService("phone")).getNetworkType())
    {
    default: 
      return "unknown";
    case 1: 
    case 2: 
    case 4: 
    case 7: 
    case 11: 
      return "2g";
    case 3: 
    case 5: 
    case 6: 
    case 8: 
    case 9: 
    case 10: 
    case 12: 
    case 14: 
    case 15: 
      return "3g";
    }
    return "4g";
  }
}
