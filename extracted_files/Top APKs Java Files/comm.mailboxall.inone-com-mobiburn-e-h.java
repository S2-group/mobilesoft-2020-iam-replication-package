package com.mobiburn.e;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.net.Uri.Builder;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.LocaleList;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import com.mobiburn.b.e;
import com.mobiburn.d.b;
import com.mobiburn.d.i;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class h
{
  public static PackageInfo a(PackageManager paramPackageManager, String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramString != null)
    {
      localObject1 = localObject2;
      if (paramPackageManager == null) {}
    }
    try
    {
      localObject1 = paramPackageManager.getPackageInfo(paramString, 0);
      return localObject1;
    }
    catch (Exception paramPackageManager) {}
    return null;
  }
  
  public static String a()
  {
    try
    {
      Object localObject = Calendar.getInstance().getTime();
      localObject = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ", Locale.ENGLISH).format((Date)localObject);
      return localObject;
    }
    catch (Throwable localThrowable) {}
    return null;
  }
  
  public static String a(long paramLong)
  {
    return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ", Locale.ENGLISH).format(Long.valueOf(paramLong));
  }
  
  public static String a(Context paramContext)
  {
    String str2 = "";
    Object localObject = str2;
    for (;;)
    {
      try
      {
        TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
        String str1 = str2;
        if (localTelephonyManager != null)
        {
          localObject = str2;
          str2 = localTelephonyManager.getSimCountryIso();
          localObject = str2;
          str1 = str2;
          if ("".equals(str2))
          {
            localObject = str2;
            str1 = localTelephonyManager.getNetworkCountryIso();
            localObject = str1;
          }
        }
        localObject = str1;
      }
      catch (Throwable paramContext)
      {
        try
        {
          if (!"".equals(localObject)) {
            break;
          }
          if (Build.VERSION.SDK_INT >= 24) {
            return paramContext.getResources().getConfiguration().getLocales().get(0).getCountry();
          }
          paramContext = paramContext.getResources().getConfiguration().locale.getCountry();
          return paramContext;
        }
        catch (Throwable paramContext)
        {
          continue;
        }
        paramContext = paramContext;
        c.a(paramContext);
        return localObject;
      }
    }
    return localObject;
  }
  
  public static String a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    return paramString3 + paramString2 + paramString1 + paramString4 + paramString6 + paramString5;
  }
  
  public static JSONArray a(ArrayList paramArrayList)
  {
    if (paramArrayList == null) {
      return null;
    }
    JSONArray localJSONArray = new JSONArray();
    Iterator localIterator = paramArrayList.iterator();
    label119:
    while (localIterator.hasNext())
    {
      paramArrayList = localIterator.next();
      if (paramArrayList != null)
      {
        if ((paramArrayList instanceof String)) {}
        for (;;)
        {
          if (paramArrayList == null) {
            break label119;
          }
          localJSONArray.put(paramArrayList);
          break;
          if ((paramArrayList instanceof b)) {
            paramArrayList = ((b)paramArrayList).a();
          } else if ((paramArrayList instanceof com.mobiburn.d.g)) {
            paramArrayList = ((com.mobiburn.d.g)paramArrayList).a();
          } else if ((paramArrayList instanceof i)) {
            paramArrayList = ((i)paramArrayList).a();
          } else {
            paramArrayList = paramArrayList.toString();
          }
        }
      }
    }
    return localJSONArray;
  }
  
  public static JSONObject a(Location paramLocation)
    throws JSONException
  {
    if (paramLocation == null) {
      return null;
    }
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("longitude", paramLocation.getLongitude());
    localJSONObject.put("latitude", paramLocation.getLatitude());
    localJSONObject.put("provider", paramLocation.getProvider());
    localJSONObject.put("time", paramLocation.getTime());
    return localJSONObject;
  }
  
  public static JSONObject a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    paramString = Uri.parse("https://graph.facebook.com/v2.10/me").buildUpon().appendQueryParameter("access_token", paramString).appendQueryParameter("fields", "id,first_name,gender,last_name,link,locale,name,timezone,updated_time,verified,email").build().toString();
    try
    {
      paramString = (JSONObject)new AsyncTask()
      {
        protected JSONObject a(Void... paramAnonymousVarArgs)
        {
          try
          {
            paramAnonymousVarArgs = (HttpsURLConnection)new URL(this.a).openConnection();
            paramAnonymousVarArgs.setSSLSocketFactory(e.a());
            paramAnonymousVarArgs.setRequestMethod("GET");
            paramAnonymousVarArgs.setRequestProperty("Content-Type", "application/json; charset=utf8");
            if (paramAnonymousVarArgs.getResponseCode() < 400)
            {
              paramAnonymousVarArgs = new BufferedReader(new InputStreamReader(paramAnonymousVarArgs.getInputStream()));
              StringBuilder localStringBuilder = new StringBuilder();
              for (;;)
              {
                String str = paramAnonymousVarArgs.readLine();
                if (str == null) {
                  break;
                }
                localStringBuilder.append(str);
              }
              paramAnonymousVarArgs.close();
              paramAnonymousVarArgs = new JSONObject(localStringBuilder.toString());
              return paramAnonymousVarArgs;
            }
            return null;
          }
          catch (Throwable paramAnonymousVarArgs) {}
          return null;
        }
      }.execute(new Void[0]).get();
      return paramString;
    }
    catch (Throwable paramString) {}
    return null;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    return (paramContext != null) && (paramContext.getApplicationContext() != null) && (paramContext.getApplicationContext().checkCallingOrSelfPermission(paramString) == 0);
  }
  
  public static boolean a(Calendar paramCalendar1, Calendar paramCalendar2)
  {
    return (paramCalendar1.get(1) == paramCalendar2.get(1)) && (paramCalendar1.get(2) == paramCalendar2.get(2)) && (paramCalendar1.get(5) == paramCalendar2.get(5));
  }
  
  public static String b()
  {
    Object localObject2 = new Class[0];
    try
    {
      Class localClass = Class.forName("com.facebook.AccessToken");
      Object localObject1;
      if (localClass != null)
      {
        localObject1 = localClass.getDeclaredMethod("getCurrentAccessToken", (Class[])localObject2);
        if (localObject1 == null) {
          break label70;
        }
        localObject1 = ((Method)localObject1).invoke(localClass, null);
        label36:
        if (localObject1 == null) {
          break label75;
        }
      }
      label70:
      label75:
      for (localObject2 = localClass.getDeclaredMethod("getToken", (Class[])localObject2);; localObject2 = null)
      {
        if (localObject2 == null) {
          break label80;
        }
        localObject1 = (String)((Method)localObject2).invoke(localObject1, null);
        return localObject1;
        localObject1 = null;
        break;
        localObject1 = null;
        break label36;
      }
      label80:
      return null;
    }
    catch (Throwable localThrowable) {}
    return null;
  }
  
  public static String b(Context paramContext)
  {
    try
    {
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return null;
  }
  
  public static String c(Context paramContext)
  {
    try
    {
      paramContext = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getDeclaredMethod("getAdvertisingIdInfo", new Class[] { Context.class }).invoke(null, new Object[] { paramContext });
      paramContext = paramContext.getClass().getMethod("getId", new Class[0]).invoke(paramContext, new Object[0]).toString();
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return null;
  }
  
  public static Location d(Context paramContext)
    throws ExecutionException, InterruptedException
  {
    Object localObject = null;
    Location localLocation = null;
    if ((!a(paramContext, "android.permission.ACCESS_COARSE_LOCATION")) && (!a(paramContext, "android.permission.ACCESS_FINE_LOCATION")))
    {
      localObject = localLocation;
      return localObject;
    }
    LocationManager localLocationManager = (LocationManager)paramContext.getSystemService("location");
    Iterator localIterator = localLocationManager.getAllProviders().iterator();
    for (paramContext = (Context)localObject;; paramContext = (Context)localObject)
    {
      localObject = paramContext;
      if (!localIterator.hasNext()) {
        break;
      }
      localObject = (String)localIterator.next();
      try
      {
        if (!localLocationManager.isProviderEnabled((String)localObject)) {
          break label155;
        }
        localLocation = localLocationManager.getLastKnownLocation((String)localObject);
        if (localLocation == null) {
          break label155;
        }
        localObject = localLocation;
        if (paramContext != null)
        {
          long l1 = paramContext.getTime();
          long l2 = localLocation.getTime();
          localObject = localLocation;
          if (l1 >= l2) {
            localObject = paramContext;
          }
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Context localContext = paramContext;
          continue;
          localContext = paramContext;
        }
      }
    }
  }
  
  public static String[] e(Context paramContext)
  {
    if (!a(paramContext, "android.permission.READ_PHONE_STATE")) {
      return null;
    }
    try
    {
      Object localObject = (TelephonyManager)paramContext.getSystemService("phone");
      paramContext = ((TelephonyManager)localObject).getLine1Number();
      localObject = ((TelephonyManager)localObject).getDeviceId();
      return new String[] { paramContext, localObject };
    }
    catch (Throwable paramContext)
    {
      c.a(paramContext);
    }
    return null;
  }
  
  public static Integer[] f(Context paramContext)
  {
    if ((!a(paramContext, "android.permission.READ_PHONE_STATE")) || ((!a(paramContext, "android.permission.ACCESS_COARSE_LOCATION")) && (!a(paramContext, "android.permission.ACCESS_FINE_LOCATION")))) {
      return null;
    }
    for (;;)
    {
      Object localObject2;
      Integer localInteger;
      try
      {
        localObject2 = (TelephonyManager)paramContext.getSystemService("phone");
        if (((TelephonyManager)localObject2).getPhoneType() == 1)
        {
          localObject1 = ((TelephonyManager)localObject2).getNetworkOperator();
          boolean bool = TextUtils.isEmpty((CharSequence)localObject1);
          if (bool) {
            break label202;
          }
        }
      }
      catch (Throwable paramContext)
      {
        Object localObject1;
        int i;
        c.a(paramContext);
      }
      try
      {
        i = Integer.parseInt(((String)localObject1).substring(0, 3));
        paramContext = Integer.valueOf(i);
      }
      catch (NumberFormatException paramContext)
      {
        paramContext = null;
        continue;
      }
      try
      {
        i = Integer.parseInt(((String)localObject1).substring(3));
        localInteger = Integer.valueOf(i);
        localObject1 = paramContext;
        paramContext = localInteger;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        localInteger = null;
        localContext = paramContext;
        paramContext = localInteger;
        continue;
        localInteger = null;
        localObject2 = null;
        continue;
      }
      if (Build.VERSION.SDK_INT < 26)
      {
        localObject2 = (GsmCellLocation)((TelephonyManager)localObject2).getCellLocation();
        if (localObject2 != null)
        {
          localInteger = Integer.valueOf(((GsmCellLocation)localObject2).getLac());
          localObject2 = Integer.valueOf(((GsmCellLocation)localObject2).getCid());
          return new Integer[] { localInteger, localObject2, localObject1, paramContext };
          return null;
        }
      }
      label202:
      paramContext = null;
      Context localContext = null;
    }
  }
  
  public static String g(Context paramContext)
  {
    if (!a(paramContext, "android.permission.ACCESS_NETWORK_STATE")) {}
    for (;;)
    {
      return null;
      try
      {
        String str = a.a("wlan0");
        paramContext = str;
        if (TextUtils.isEmpty(str)) {
          paramContext = a.a("eth0");
        }
        boolean bool = TextUtils.isEmpty(paramContext);
        if (!bool) {
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        c.a(paramContext);
      }
    }
    return null;
  }
  
  public static JSONArray h(Context paramContext)
    throws JSONException
  {
    return new JSONArray(g.a(paramContext).l());
  }
  
  /* Error */
  public static ArrayList<b> i(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: invokestatic 419	com/mobiburn/e/g:a	(Landroid/content/Context;)Lcom/mobiburn/e/g;
    //   6: invokevirtual 428	com/mobiburn/e/g:n	()Z
    //   9: ifeq +5 -> 14
    //   12: aconst_null
    //   13: areturn
    //   14: new 143	java/util/ArrayList
    //   17: dup
    //   18: invokespecial 429	java/util/ArrayList:<init>	()V
    //   21: astore_1
    //   22: aload_0
    //   23: invokevirtual 433	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   26: astore_2
    //   27: aload_2
    //   28: sipush 128
    //   31: invokevirtual 437	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   34: invokeinterface 346 1 0
    //   39: astore_3
    //   40: aload_3
    //   41: invokeinterface 153 1 0
    //   46: ifeq +96 -> 142
    //   49: aload_3
    //   50: invokeinterface 157 1 0
    //   55: checkcast 439	android/content/pm/ApplicationInfo
    //   58: astore_0
    //   59: aload_0
    //   60: getfield 443	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   63: astore 4
    //   65: aload_0
    //   66: ifnull -26 -> 40
    //   69: aload 4
    //   71: ldc_w 445
    //   74: invokevirtual 448	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   77: ifne -37 -> 40
    //   80: aload_2
    //   81: aload_0
    //   82: invokevirtual 452	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   85: invokeinterface 455 1 0
    //   90: astore_0
    //   91: aload 4
    //   93: invokestatic 219	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   96: ifne -56 -> 40
    //   99: aload_1
    //   100: new 163	com/mobiburn/d/b
    //   103: dup
    //   104: aload_0
    //   105: aload 4
    //   107: aload_2
    //   108: aload 4
    //   110: invokestatic 457	com/mobiburn/e/h:a	(Landroid/content/pm/PackageManager;Ljava/lang/String;)Landroid/content/pm/PackageInfo;
    //   113: invokespecial 460	com/mobiburn/d/b:<init>	(Ljava/lang/String;Ljava/lang/String;Landroid/content/pm/PackageInfo;)V
    //   116: invokevirtual 463	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   119: pop
    //   120: goto -80 -> 40
    //   123: astore_2
    //   124: aload_1
    //   125: astore_0
    //   126: aload_2
    //   127: astore_1
    //   128: aload_1
    //   129: invokestatic 124	com/mobiburn/e/c:a	(Ljava/lang/Throwable;)V
    //   132: aload_0
    //   133: areturn
    //   134: astore_0
    //   135: ldc_w 465
    //   138: astore_0
    //   139: goto -48 -> 91
    //   142: aload_1
    //   143: areturn
    //   144: astore_1
    //   145: aload_2
    //   146: astore_0
    //   147: goto -19 -> 128
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	150	0	paramContext	Context
    //   21	122	1	localObject	Object
    //   144	1	1	localException1	Exception
    //   1	107	2	localPackageManager	PackageManager
    //   123	23	2	localException2	Exception
    //   39	11	3	localIterator	Iterator
    //   63	46	4	str	String
    // Exception table:
    //   from	to	target	type
    //   22	40	123	java/lang/Exception
    //   40	65	123	java/lang/Exception
    //   69	80	123	java/lang/Exception
    //   91	120	123	java/lang/Exception
    //   80	91	134	java/lang/Exception
    //   14	22	144	java/lang/Exception
  }
  
  public static ArrayList<com.mobiburn.d.g> j(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = com.mobiburn.a.a().d;
    if ((localObject != null) && (Patterns.EMAIL_ADDRESS.matcher((CharSequence)localObject).matches())) {
      localArrayList.add(new com.mobiburn.d.g((String)localObject));
    }
    if ((!a(paramContext, "android.permission.GET_ACCOUNTS")) && (localArrayList.size() == 0))
    {
      Log.w("Mobiburn", "Cannot found 'Mobiburn.setEmailAddress(\"...\");' call nor 'android.permission.GET_ACCOUNTS' Permission");
      return localArrayList;
    }
    try
    {
      paramContext = AccountManager.get(paramContext).getAccounts();
      if ((paramContext != null) && (paramContext.length != 0))
      {
        paramContext = Arrays.asList(paramContext);
        int i = 0;
        while (i < paramContext.size())
        {
          localObject = (Account)paramContext.get(i);
          if (Patterns.EMAIL_ADDRESS.matcher(((Account)localObject).name).matches()) {
            localArrayList.add(new com.mobiburn.d.g(((Account)localObject).name));
          }
          i += 1;
        }
        return localArrayList;
      }
    }
    catch (Exception paramContext)
    {
      c.a(paramContext);
    }
    return localArrayList;
  }
  
  public static final class a
  {
    public static String a(String paramString)
    {
      try
      {
        Object localObject = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
        while (((Iterator)localObject).hasNext())
        {
          NetworkInterface localNetworkInterface = (NetworkInterface)((Iterator)localObject).next();
          if ((paramString != null) && (localNetworkInterface.getName().equalsIgnoreCase(paramString)))
          {
            paramString = localNetworkInterface.getHardwareAddress();
            if (paramString == null) {
              return null;
            }
            localObject = new StringBuilder();
            int i = 0;
            while (i < paramString.length)
            {
              ((StringBuilder)localObject).append(String.format("%02X:", new Object[] { Byte.valueOf(paramString[i]) }));
              i += 1;
            }
            if (((StringBuilder)localObject).length() > 0) {
              ((StringBuilder)localObject).deleteCharAt(((StringBuilder)localObject).length() - 1);
            }
            paramString = ((StringBuilder)localObject).toString();
            return paramString;
          }
        }
      }
      catch (Throwable paramString)
      {
        com.mobiburn.c.a.a("Error getting MAC address" + paramString.toString());
      }
      return null;
    }
    
    public static ArrayList<i> a(Context paramContext)
    {
      ArrayList localArrayList = new ArrayList();
      if (h.a(paramContext, "android.permission.ACCESS_WIFI_STATE"))
      {
        paramContext = (WifiManager)paramContext.getSystemService("wifi");
        if (paramContext == null) {
          return localArrayList;
        }
        paramContext = paramContext.getConfiguredNetworks();
        if (paramContext == null) {
          return localArrayList;
        }
        paramContext = paramContext.iterator();
        while (paramContext.hasNext())
        {
          WifiConfiguration localWifiConfiguration = (WifiConfiguration)paramContext.next();
          if (localWifiConfiguration != null) {
            localArrayList.add(new i(localWifiConfiguration));
          }
        }
      }
      return localArrayList;
    }
    
    public static String[] a()
    {
      try
      {
        InetAddress localInetAddress;
        do
        {
          localObject2 = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
          Iterator localIterator;
          while (!localIterator.hasNext())
          {
            if (!((Iterator)localObject2).hasNext()) {
              break;
            }
            localObject1 = (NetworkInterface)((Iterator)localObject2).next();
            localIterator = Collections.list(((NetworkInterface)localObject1).getInetAddresses()).iterator();
          }
          localInetAddress = (InetAddress)localIterator.next();
        } while ((localInetAddress.isLoopbackAddress()) || (!(localInetAddress instanceof Inet4Address)));
        Object localObject2 = localInetAddress.getHostAddress().toUpperCase();
        Object localObject1 = ((NetworkInterface)localObject1).getName();
        return new String[] { localObject2, localObject1 };
      }
      catch (Throwable localThrowable) {}
      return null;
    }
    
    public static ArrayList<i> b(Context paramContext)
    {
      ArrayList localArrayList = new ArrayList();
      if ((h.a(paramContext, "android.permission.ACCESS_WIFI_STATE")) && ((h.a(paramContext, "android.permission.ACCESS_COARSE_LOCATION")) || (h.a(paramContext, "android.permission.ACCESS_FINE_LOCATION")))) {
        try
        {
          paramContext = (WifiManager)paramContext.getSystemService("wifi");
          if (paramContext == null) {
            return localArrayList;
          }
          paramContext = paramContext.getScanResults();
          if ((paramContext != null) && (!paramContext.isEmpty()))
          {
            paramContext = paramContext.iterator();
            while (paramContext.hasNext())
            {
              ScanResult localScanResult = (ScanResult)paramContext.next();
              if (localScanResult != null) {
                localArrayList.add(new i(localScanResult));
              }
            }
            return localArrayList;
          }
        }
        catch (Exception paramContext)
        {
          c.a(paramContext);
        }
      }
      return localArrayList;
    }
  }
}
