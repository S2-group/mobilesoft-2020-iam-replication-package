package pl.mobiem.pierdofon;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import pl.mobiem.android.processes.models.AndroidAppProcess;

public class oo
{
  private static final String a = om.a("Utils");
  
  public static long a(SharedPreferences paramSharedPreferences, String paramString, long paramLong)
  {
    try
    {
      long l = paramSharedPreferences.getLong(paramString, paramLong);
      return l;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        try
        {
          int i = paramSharedPreferences.getInt(paramString, i);
          return i;
        }
        catch (Exception paramSharedPreferences) {}
        localException = localException;
      }
    }
    i = (int)paramLong;
    return paramLong;
  }
  
  public static String a()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      Object localObject = nn.a();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        AndroidAppProcess localAndroidAppProcess = (AndroidAppProcess)((Iterator)localObject).next();
        if (localAndroidAppProcess.a)
        {
          localStringBuilder.append(localAndroidAppProcess.a());
          localStringBuilder.append(";");
        }
      }
      return localStringBuilder.toString();
    }
    catch (NullPointerException localNullPointerException)
    {
      for (;;) {}
    }
    return localStringBuilder.toString();
  }
  
  private static String a(String paramString)
  {
    try
    {
      Object localObject1 = MessageDigest.getInstance("MD5");
      Object localObject2 = a;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("bytes= ");
      localStringBuilder.append(paramString.getBytes().length);
      om.a((String)localObject2, localStringBuilder.toString());
      ((MessageDigest)localObject1).update(paramString.getBytes());
      localObject1 = ((MessageDigest)localObject1).digest();
      localObject2 = new StringBuffer();
      int i = 0;
      while (i < localObject1.length)
      {
        for (paramString = Integer.toHexString(localObject1[i] & 0xFF); paramString.length() < 2; paramString = localStringBuilder.toString())
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("0");
          localStringBuilder.append(paramString);
        }
        ((StringBuffer)localObject2).append(paramString);
        i += 1;
      }
      paramString = ((StringBuffer)localObject2).toString();
      return paramString;
    }
    catch (NullPointerException paramString)
    {
      paramString.printStackTrace();
    }
    catch (NoSuchAlgorithmException paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  public static String a(String paramString1, String paramString2)
  {
    if ((paramString1 == null) && (paramString2 == null)) {
      return "";
    }
    try
    {
      paramString1 = nm.a(paramString1);
    }
    catch (IllegalArgumentException paramString1)
    {
      for (;;) {}
    }
    paramString1 = new HashSet();
    try
    {
      paramString2 = nm.a(paramString2);
    }
    catch (IllegalArgumentException paramString2)
    {
      for (;;) {}
    }
    paramString2 = new HashSet();
    paramString1.addAll(paramString2);
    paramString2 = new StringBuilder();
    paramString1 = paramString1.iterator();
    while (paramString1.hasNext()) {
      paramString2.append(((nm)paramString1.next()).a());
    }
    return paramString2.toString();
  }
  
  public static void a(Context paramContext, SharedPreferences paramSharedPreferences, boolean paramBoolean)
  {
    om.a(a, "setNextMobiemPush");
    if (!paramBoolean) {}
    for (long l1 = paramSharedPreferences.getLong("time_between_notification", 3600000L);; l1 = 172800000L) {
      break;
    }
    paramSharedPreferences = (AlarmManager)paramContext.getSystemService("alarm");
    Object localObject1 = a;
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("!!!!!!!set alarm package name = ");
    ((StringBuilder)localObject2).append(paramContext.getPackageName());
    om.a((String)localObject1, ((StringBuilder)localObject2).toString());
    try
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(paramContext.getPackageName());
      ((StringBuilder)localObject1).append(".BootReceiver");
      localObject1 = new Intent(paramContext, Class.forName(((StringBuilder)localObject1).toString()));
      long l2 = System.currentTimeMillis();
      localObject2 = a;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("setNextMobiemPush now = ");
      localStringBuilder.append(new Date(l2).toLocaleString());
      om.a((String)localObject2, localStringBuilder.toString());
      localObject1 = PendingIntent.getBroadcast(paramContext, 0, (Intent)localObject1, 134217728);
      paramSharedPreferences.cancel((PendingIntent)localObject1);
      paramContext = (AlarmManager)paramContext.getSystemService("alarm");
      l2 += l1;
      paramContext.setRepeating(0, l2, l1, (PendingIntent)localObject1);
      paramContext = a;
      paramSharedPreferences = new StringBuilder();
      paramSharedPreferences.append("setNextMobiemPush next = ");
      paramSharedPreferences.append(new Date(l2).toLocaleString());
      om.a(paramContext, paramSharedPreferences.toString());
      return;
    }
    catch (ClassNotFoundException paramContext)
    {
      paramSharedPreferences = a;
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("setAlarm error: ");
      ((StringBuilder)localObject1).append(paramContext.toString());
      om.c(paramSharedPreferences, ((StringBuilder)localObject1).toString());
    }
  }
  
  public static boolean a(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    return (paramContext != null) && (paramContext.getActiveNetworkInfo() != null) && (paramContext.getActiveNetworkInfo().isAvailable()) && (paramContext.getActiveNetworkInfo().isConnected());
  }
  
  public static String b(Context paramContext)
  {
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext);
    String str = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("getAdvertisingID isGPSA: ");
    localStringBuilder.append(i);
    om.a(str, localStringBuilder.toString());
    try
    {
      paramContext = AdvertisingIdClient.getAdvertisingIdInfo(paramContext);
      str = a;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("getAdvertisingID : ");
      localStringBuilder.append(paramContext.getId());
      om.a(str, localStringBuilder.toString());
      paramContext = paramContext.getId();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      str = a;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("getAdvertisingID exception: ");
      localStringBuilder.append(paramContext.toString());
      om.c(str, localStringBuilder.toString());
    }
    return "";
  }
  
  public static String c(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      localObject = paramContext.getInstalledApplications(128);
      paramContext = new JSONArray();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        paramContext.put(((ApplicationInfo)((Iterator)localObject).next()).packageName);
      }
      paramContext = paramContext.toString();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      Object localObject = a;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("getInstalledApps exception: ");
      localStringBuilder.append(paramContext.toString());
      om.c((String)localObject, localStringBuilder.toString());
    }
    return "";
  }
  
  public static String d(Context paramContext)
  {
    om.a(a, "getDeviceImei");
    Object localObject1;
    try
    {
      String str = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
    }
    catch (Exception localException)
    {
      localObject2 = a;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("getDeviceImei exception: ");
      localStringBuilder.append(localException.toString());
      om.c((String)localObject2, localStringBuilder.toString());
      localObject1 = null;
    }
    Object localObject2 = localObject1;
    if (localObject1 == null)
    {
      om.a(a, "getDeviceImei imei is null generate imei");
      localObject2 = e(paramContext);
      localObject1 = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      paramContext = (Context)localObject1;
      if (localObject2 != null)
      {
        paramContext = new StringBuilder();
        paramContext.append((String)localObject2);
        paramContext.append((String)localObject1);
        paramContext = paramContext.toString();
      }
      localObject1 = paramContext;
      if (paramContext == null)
      {
        paramContext = new StringBuilder();
        paramContext.append("");
        paramContext.append(Build.BOARD.length() % 10);
        paramContext.append(Build.BRAND.length() % 10);
        paramContext.append(Build.CPU_ABI.length() % 10);
        paramContext.append(Build.DEVICE.length() % 10);
        paramContext.append(Build.DISPLAY.length() % 10);
        paramContext.append(Build.HOST.length() % 10);
        paramContext.append(Build.ID.length() % 10);
        paramContext.append(Build.MANUFACTURER.length() % 10);
        paramContext.append(Build.MODEL.length() % 10);
        paramContext.append(Build.PRODUCT.length() % 10);
        paramContext.append(Build.TAGS.length() % 10);
        paramContext.append(Build.TYPE.length() % 10);
        paramContext.append(Build.USER.length() % 10);
        paramContext.append(Build.FINGERPRINT);
        localObject1 = paramContext.toString();
      }
      paramContext = a;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("getDeviceImei before end: ");
      ((StringBuilder)localObject2).append((String)localObject1);
      om.a(paramContext, ((StringBuilder)localObject2).toString());
      paramContext = new StringBuilder();
      paramContext.append("56");
      paramContext.append(a((String)localObject1));
      localObject2 = paramContext.toString();
    }
    return localObject2;
  }
  
  public static String e(Context paramContext)
  {
    return ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
  }
  
  public static String f(Context paramContext)
  {
    String str = "NONE";
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    paramContext = str;
    if (localNetworkInfo != null)
    {
      switch (localNetworkInfo.getType())
      {
      default: 
        return "NONE";
      case 1: 
        return "WIFI";
      }
      paramContext = "MOBILE";
    }
    return paramContext;
  }
  
  public static op g(Context paramContext)
  {
    op localOp = new op();
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      GsmCellLocation localGsmCellLocation = (GsmCellLocation)paramContext.getCellLocation();
      localOp.a(localGsmCellLocation.getCid() & 0xFFFF);
      localOp.b(localGsmCellLocation.getLac());
      localOp.d(paramContext.getNetworkOperatorName());
      localOp.c(paramContext.getSimOperatorName());
      paramContext = paramContext.getNetworkOperator();
      if (paramContext != null)
      {
        localOp.b(paramContext.substring(0, 3));
        localOp.a(paramContext.substring(3));
      }
      return localOp;
    }
    catch (Exception paramContext) {}
    return localOp;
  }
  
  public static int h(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return 0;
  }
}
