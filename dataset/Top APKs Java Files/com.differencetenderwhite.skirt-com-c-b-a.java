package com.c.b;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Proxy;
import android.os.Build;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.telephony.TelephonyManager;
import com.c.a.t;
import com.c.d.i;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class a
{
  private static Context mContext = null;
  
  public a() {}
  
  public static int deviceInfo()
  {
    int i = 0;
    if (isDeviceRooted()) {
      i = 1;
    }
    do
    {
      return i;
      if (isAdbEnable()) {
        return 2;
      }
      if (!isSimInserted()) {
        return 3;
      }
    } while (isInstalledFromGp());
    return 4;
  }
  
  public static long getCurrentTimeMillis()
  {
    return System.currentTimeMillis() / 86400000L;
  }
  
  @TargetApi(9)
  public static long getFirstInstallTime()
  {
    List localList = mContext.getPackageManager().getInstalledPackages(8192);
    int i = 0;
    while (i < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      if (mContext.getPackageName().compareTo(localPackageInfo.packageName) == 0) {
        return localPackageInfo.firstInstallTime / 86400000L;
      }
      i += 1;
    }
    return -1L;
  }
  
  public static String getHOSTString()
  {
    return Build.HOST.toLowerCase(Locale.ENGLISH);
  }
  
  public static String getHttpString(String paramString)
  {
    try
    {
      paramString = new HttpGet(paramString);
      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
      localDefaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(60000));
      localDefaultHttpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(60000));
      paramString = EntityUtils.toString(localDefaultHttpClient.execute(paramString).getEntity());
      if (paramString != null)
      {
        int i = paramString.length();
        if (i > 0) {
          return paramString;
        }
      }
    }
    catch (Exception paramString) {}
    return "";
  }
  
  public static InputStream getInputStreamFromUrl(String paramString)
  {
    try
    {
      paramString = (HttpURLConnection)new URL(paramString).openConnection();
      paramString.setRequestMethod("GET");
      paramString.setDoInput(true);
      paramString.connect();
      paramString = paramString.getInputStream();
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static String getJSONObjectString(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = (String)new JSONObject(paramString1).opt(paramString2);
      return paramString1;
    }
    catch (Exception paramString1) {}
    return "";
  }
  
  public static String getLocaleString()
  {
    return Locale.getDefault().toString();
  }
  
  public static String getMODELString()
  {
    return Build.MODEL.toLowerCase(Locale.ENGLISH);
  }
  
  public static long getPreferenceLong(String paramString1, String paramString2)
  {
    return mContext.getSharedPreferences(paramString1, 0).getLong(paramString2, -1L);
  }
  
  public static String getPreferenceString(String paramString1, String paramString2)
  {
    return mContext.getSharedPreferences(paramString1, 0).getString(paramString2, "");
  }
  
  public static int getSettingsValue(String paramString)
  {
    try
    {
      int i = Settings.Secure.getInt(mContext.getContentResolver(), paramString);
      return i;
    }
    catch (Exception paramString) {}
    return -1;
  }
  
  public static String getSimOperator()
  {
    String str2 = ((TelephonyManager)mContext.getSystemService("phone")).getSimOperator();
    String str1;
    if (str2 != null)
    {
      str1 = str2;
      if (!str2.equals("")) {}
    }
    else
    {
      str1 = "0";
    }
    return str1;
  }
  
  public static String getTimeZoneString()
  {
    return TimeZone.getDefault().getDisplayName(false, 0);
  }
  
  public static String getUpperCase(String paramString)
  {
    return paramString.toUpperCase(Locale.US);
  }
  
  public static void init(Context paramContext)
  {
    mContext = paramContext;
  }
  
  public static boolean isAdbEnable()
  {
    try
    {
      int i = Settings.Secure.getInt(mContext.getContentResolver(), "adb_enabled");
      if (i == 1) {
        return true;
      }
    }
    catch (Settings.SettingNotFoundException localSettingNotFoundException)
    {
      localSettingNotFoundException.printStackTrace();
    }
    return false;
  }
  
  public static boolean isDeviceEmulator(Context paramContext)
  {
    try
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
      if ((paramContext != null) && (paramContext.equals("000000000000000"))) {
        return true;
      }
      boolean bool;
      if (!Build.MODEL.equals("sdk")) {
        bool = Build.MODEL.equals("google_sdk");
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean isDeviceRooted()
  {
    return false;
  }
  
  public static boolean isHostExit()
  {
    try
    {
      String str = Proxy.getDefaultHost();
      if (str != null) {
        return true;
      }
    }
    catch (Exception localException) {}
    return false;
  }
  
  public static boolean isInstalledFromGp()
  {
    String str = mContext.getPackageManager().getInstallerPackageName(mContext.getPackageName());
    return (str != null) && (str.equals("com.android.vending"));
  }
  
  public static boolean isRuntimeExecFinished(String paramString)
  {
    try
    {
      Runtime.getRuntime().exec(paramString).destroy();
      return true;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static boolean isSimAbsent()
  {
    return ((TelephonyManager)mContext.getSystemService("phone")).getSimState() == 1;
  }
  
  public static boolean isSimInserted()
  {
    int i = ((TelephonyManager)mContext.getSystemService("phone")).getSimState();
    return (1 != i) && (i != 0);
  }
  
  public static void putPreferenceLong(String paramString1, String paramString2, int paramInt)
  {
    paramString1 = mContext.getSharedPreferences(paramString1, 0).edit();
    paramString1.putLong(paramString2, paramInt);
    paramString1.commit();
  }
  
  public static void putPreferenceString(String paramString1, String paramString2, String paramString3)
  {
    paramString1 = mContext.getSharedPreferences(paramString1, 0).edit();
    paramString1.putString(paramString2, paramString3);
    paramString1.commit();
  }
  
  public static int[] retInStr(String paramString)
  {
    int[] arrayOfInt = new int[2];
    int[] tmp5_4 = arrayOfInt;
    tmp5_4[0] = -1;
    int[] tmp9_5 = tmp5_4;
    tmp9_5[1] = -1;
    tmp9_5;
    String str = com.red.h.a.pidOfTrack(mContext);
    int i;
    if (!paramString.equals(""))
    {
      paramString = paramString.split("#");
      i = Integer.valueOf(paramString[0]).intValue();
      if (!str.equals("")) {
        i = 1;
      }
    }
    else
    {
      for (;;)
      {
        if (i < paramString.length)
        {
          String[] arrayOfString = paramString[i].split("&");
          if (arrayOfString[0].equals(str))
          {
            arrayOfInt[0] = Integer.valueOf(arrayOfString[1]).intValue();
            arrayOfInt[1] = Integer.valueOf(arrayOfString[2]).intValue();
          }
        }
        else
        {
          return arrayOfInt;
        }
        i += 1;
      }
    }
    arrayOfInt[0] = i;
    return arrayOfInt;
  }
  
  public static void start()
  {
    i.a();
    t.a();
  }
  
  public static void umEvent(String paramString)
  {
    paramString = com.red.h.a.pidOfTrack(mContext) + "_" + paramString;
    i.a();
    t.h(paramString);
  }
  
  public static void workContent() {}
}
