package com.hmt.analytics.android;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Log;
import com.hmt.analytics.util.n;
import com.hmt.analytics.util.r;
import java.io.File;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class a
{
  public static final String a = a.class.getSimpleName();
  
  public a() {}
  
  public static String A(Context paramContext)
  {
    int i = 0;
    if (paramContext == null) {
      return "";
    }
    try
    {
      int j = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      i = j;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        a(a, "Collected:" + paramContext.getMessage());
      }
    }
    return String.valueOf(i);
  }
  
  public static String B(Context paramContext)
  {
    return "";
  }
  
  public static String C(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      paramContext = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).applicationInfo.loadLabel(localPackageManager).toString();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      a(a, "Collected:" + paramContext.getMessage());
    }
    return "";
  }
  
  public static String D(Context paramContext)
  {
    return (String)r.b(paramContext, "manual_setting_imei", "");
  }
  
  public static String E(Context paramContext)
  {
    Object localObject = D(paramContext);
    if (!TextUtils.isEmpty((CharSequence)localObject)) {
      return localObject;
    }
    if (j(paramContext))
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext == null) {
        return "";
      }
    }
    for (;;)
    {
      try
      {
        paramContext = paramContext.getDeviceId();
        localObject = paramContext;
        if (paramContext != null) {
          break;
        }
        return "";
      }
      catch (Exception paramContext)
      {
        a(a, "Collected:" + paramContext.getMessage());
        paramContext = (Context)localObject;
        continue;
      }
      a(a, "get_imei : lost permission = android.permission.READ_PHONE_STATE");
      paramContext = (Context)localObject;
    }
  }
  
  public static String F(Context paramContext)
  {
    try
    {
      if (a(paramContext, "android.permission.ACCESS_WIFI_STATE"))
      {
        Object localObject1 = (WifiManager)paramContext.getSystemService("wifi");
        if (localObject1 == null) {
          return "";
        }
        if (Build.VERSION.SDK_INT == 17) {
          localObject1 = "02:00:00:00:00:00";
        }
        String str;
        for (;;)
        {
          boolean bool = TextUtils.isEmpty((CharSequence)localObject1);
          if (!bool) {
            break;
          }
          return "";
          try
          {
            localObject1 = ((WifiManager)localObject1).getConnectionInfo();
            if (localObject1 == null) {
              return "";
            }
          }
          catch (Exception localException)
          {
            for (;;)
            {
              a(a, "Collected:" + localException.getMessage());
              Object localObject2 = null;
            }
          }
          catch (NoSuchFieldError localNoSuchFieldError)
          {
            for (;;)
            {
              a(a, "Collected:" + localNoSuchFieldError.getMessage());
              str = null;
            }
            str = str.getMacAddress();
          }
        }
        if (str.equals("02:00:00:00:00:00"))
        {
          str = T(paramContext);
          if (!TextUtils.isEmpty(str)) {
            return str.toLowerCase();
          }
          paramContext = U(paramContext);
          if (!TextUtils.isEmpty(paramContext))
          {
            paramContext = paramContext.toLowerCase();
            return paramContext;
          }
          return "02:00:00:00:00:00";
        }
        return str.toLowerCase();
      }
      a(a, "get_mac : lost permission = android.permission.ACCESS_WIFI_STATE");
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        a(a, "Collected:" + paramContext.getMessage());
      }
    }
    return "";
  }
  
  public static int G(Context paramContext)
  {
    if (j(paramContext)) {
      return ((TelephonyManager)paramContext.getSystemService("phone")).getPhoneType();
    }
    a(a, "getPhoneType : lost permission = android.permission.READ_PHONE_STATE");
    return 0;
  }
  
  public static String H(Context paramContext)
  {
    for (;;)
    {
      try
      {
        if (!j(paramContext)) {
          continue;
        }
        paramContext = (TelephonyManager)paramContext.getSystemService("phone");
        if (paramContext == null) {
          return "";
        }
        paramContext = paramContext.getSubscriberId();
      }
      catch (Exception paramContext)
      {
        a(a, "Collected:" + paramContext.getMessage());
        paramContext = null;
        continue;
      }
      if (paramContext != null) {
        break;
      }
      return "";
      a(a, "getImsi : lost permission = android.permission.READ_PHONE_STATE");
      paramContext = null;
    }
    return paramContext;
  }
  
  public static String I(Context paramContext)
  {
    paramContext = E(paramContext);
    if ((paramContext == null) || (paramContext.equals(""))) {
      return "";
    }
    return n.a(paramContext);
  }
  
  public static String J(Context paramContext)
  {
    String str = w(paramContext);
    paramContext = "";
    if (!str.equals("")) {
      paramContext = n.a(str);
    }
    return paramContext;
  }
  
  public static String K(Context paramContext)
  {
    String str2 = F(paramContext);
    String str1 = "";
    paramContext = str1;
    if (str2 != null)
    {
      paramContext = str1;
      if (!str2.equals("")) {
        paramContext = n.a(str2.replace(":", "").toUpperCase());
      }
    }
    return paramContext;
  }
  
  public static String L(Context paramContext)
  {
    String str2 = F(paramContext);
    String str1 = "";
    paramContext = str1;
    if (str2 != null)
    {
      paramContext = str1;
      if (!str2.equals("")) {
        paramContext = n.a(str2.toUpperCase());
      }
    }
    return paramContext;
  }
  
  public static void M(Context paramContext)
  {
    LocationManager localLocationManager;
    if (a(paramContext, "android.permission.ACCESS_FINE_LOCATION"))
    {
      a(a, "startLocating setp1");
      localLocationManager = (LocationManager)paramContext.getSystemService("location");
      paramContext = new am(paramContext);
      a(a, "startLocating setp2");
      if (!localLocationManager.isProviderEnabled("network")) {
        break label98;
      }
      localLocationManager.requestLocationUpdates("network", 0L, 0.0F, paramContext);
      a(a, "startLocating setp3 NETWORK_PROVIDER");
    }
    for (;;)
    {
      a(a, "startLocating setp4");
      new Thread(new b(localLocationManager, paramContext)).start();
      return;
      label98:
      if (localLocationManager.isProviderEnabled("gps"))
      {
        localLocationManager.requestLocationUpdates("gps", 0L, 0.0F, paramContext);
        a(a, "startLocating setp3 GPS_PROVIDER");
      }
      else if (localLocationManager.isProviderEnabled("passive"))
      {
        localLocationManager.requestLocationUpdates("passive", 0L, 0.0F, paramContext);
        a(a, "startLocating setp3 PASSIVE_PROVIDER");
      }
    }
  }
  
  public static boolean N(Context paramContext)
  {
    return (paramContext instanceof Application);
  }
  
  public static void O(Context paramContext)
  {
    long l = ((Long)r.b(paramContext, "hmt_data_clean_time", Long.valueOf(0L))).longValue();
    if (l != 0L) {
      g.e = l * 60L * 60L * 1000L;
    }
  }
  
  public static String P(Context paramContext)
  {
    Object localObject3 = null;
    Object localObject2 = null;
    paramContext = (WifiManager)paramContext.getSystemService("wifi");
    Object localObject1 = localObject3;
    int i;
    if (paramContext != null)
    {
      List localList = paramContext.getScanResults();
      WifiInfo localWifiInfo = paramContext.getConnectionInfo();
      localObject1 = localObject3;
      if (localList != null)
      {
        localObject1 = localObject3;
        if (localWifiInfo != null)
        {
          i = 0;
          paramContext = localObject2;
          localObject1 = paramContext;
          if (i < localList.size())
          {
            localObject1 = (ScanResult)localList.get(i);
            if (!localWifiInfo.getBSSID().equals(((ScanResult)localObject1).BSSID)) {
              break label108;
            }
            paramContext = ((ScanResult)localObject1).BSSID;
          }
        }
      }
    }
    label108:
    for (;;)
    {
      i += 1;
      break;
      return localObject1;
    }
  }
  
  public static List<ah> Q(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = paramContext.getPackageManager();
    if (localObject == null) {}
    do
    {
      return localArrayList;
      localObject = ((PackageManager)localObject).getInstalledPackages(0);
    } while ((localObject == null) || (((List)localObject).size() == 0));
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0)
      {
        ah localAh = new ah();
        localAh.a(localPackageInfo.packageName);
        localAh.b(localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString());
        localAh.c(localPackageInfo.versionName);
        localAh.d(localPackageInfo.firstInstallTime + "");
        localAh.e(localPackageInfo.lastUpdateTime + "");
        localArrayList.add(localAh);
      }
    }
    Collections.sort(localArrayList, new c());
    if (localArrayList.size() > 50) {}
    for (paramContext = localArrayList.subList(0, 50);; paramContext = localArrayList) {
      return paramContext;
    }
  }
  
  public static String R(Context paramContext)
  {
    paramContext = e(paramContext);
    if ((paramContext == null) || (paramContext.equals(""))) {
      return "";
    }
    return g.l + paramContext + ".config" + h.a();
  }
  
  public static void S(Context paramContext)
  {
    if (((Long)r.b(paramContext, "first_init_time", Long.valueOf(0L))).longValue() == 0L) {
      r.a(paramContext, "first_init_time", Long.valueOf(System.currentTimeMillis()));
    }
  }
  
  @SuppressLint({"NewApi"})
  private static String T(Context paramContext)
  {
    if (a(paramContext, "android.permission.INTERNET")) {
      try
      {
        paramContext = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
        while (paramContext.hasNext())
        {
          Object localObject = (NetworkInterface)paramContext.next();
          if (((NetworkInterface)localObject).getName().equalsIgnoreCase("wlan0"))
          {
            paramContext = ((NetworkInterface)localObject).getHardwareAddress();
            if (paramContext == null) {
              return "";
            }
            localObject = new StringBuilder();
            int j = paramContext.length;
            int i = 0;
            while (i < j)
            {
              ((StringBuilder)localObject).append(String.format("%02X:", new Object[] { Byte.valueOf(paramContext[i]) }));
              i += 1;
            }
            if (((StringBuilder)localObject).length() > 0) {
              ((StringBuilder)localObject).deleteCharAt(((StringBuilder)localObject).length() - 1);
            }
            paramContext = ((StringBuilder)localObject).toString();
            return paramContext;
          }
        }
      }
      catch (Exception paramContext)
      {
        a(a, "Collected:" + paramContext.getMessage());
      }
    }
    for (;;)
    {
      return "";
      a(a, "getAdressMacByInterface : need permission = android.permission.INTERNET");
    }
  }
  
  /* Error */
  @SuppressLint({"NewApi"})
  private static String U(Context paramContext)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: ldc 26
    //   4: astore_3
    //   5: new 450	java/io/File
    //   8: dup
    //   9: new 55	java/lang/StringBuilder
    //   12: dup
    //   13: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   16: ldc_w 452
    //   19: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: invokestatic 455	com/hmt/analytics/android/a:i	()Ljava/lang/String;
    //   25: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: ldc_w 457
    //   31: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   37: invokespecial 459	java/io/File:<init>	(Ljava/lang/String;)V
    //   40: astore 4
    //   42: aload 4
    //   44: invokevirtual 462	java/io/File:exists	()Z
    //   47: ifeq +252 -> 299
    //   50: aload 4
    //   52: invokevirtual 465	java/io/File:isFile	()Z
    //   55: ifeq +244 -> 299
    //   58: new 467	java/io/BufferedReader
    //   61: dup
    //   62: new 469	java/io/FileReader
    //   65: dup
    //   66: aload 4
    //   68: invokespecial 472	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   71: invokespecial 475	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   74: astore 4
    //   76: aload_3
    //   77: astore_0
    //   78: aload 4
    //   80: astore_3
    //   81: aload 4
    //   83: invokevirtual 478	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   86: astore 5
    //   88: aload 5
    //   90: ifnull +33 -> 123
    //   93: aload 4
    //   95: astore_3
    //   96: new 55	java/lang/StringBuilder
    //   99: dup
    //   100: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   103: aload_0
    //   104: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: aload 5
    //   109: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   112: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   115: astore 5
    //   117: aload 5
    //   119: astore_0
    //   120: goto -42 -> 78
    //   123: aload_0
    //   124: astore_3
    //   125: aload 4
    //   127: ifnull +10 -> 137
    //   130: aload 4
    //   132: invokevirtual 481	java/io/BufferedReader:close	()V
    //   135: aload_0
    //   136: astore_3
    //   137: aload_3
    //   138: areturn
    //   139: astore_3
    //   140: getstatic 16	com/hmt/analytics/android/a:a	Ljava/lang/String;
    //   143: new 55	java/lang/StringBuilder
    //   146: dup
    //   147: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   150: ldc 58
    //   152: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   155: aload_3
    //   156: invokevirtual 482	java/io/IOException:getMessage	()Ljava/lang/String;
    //   159: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   165: invokestatic 71	com/hmt/analytics/android/a:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   168: aload_0
    //   169: areturn
    //   170: astore 5
    //   172: aconst_null
    //   173: astore 4
    //   175: aload_3
    //   176: astore_0
    //   177: aload 4
    //   179: astore_3
    //   180: getstatic 16	com/hmt/analytics/android/a:a	Ljava/lang/String;
    //   183: new 55	java/lang/StringBuilder
    //   186: dup
    //   187: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   190: ldc 58
    //   192: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: aload 5
    //   197: invokevirtual 65	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   200: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   206: invokestatic 71	com/hmt/analytics/android/a:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   209: aload_0
    //   210: astore_3
    //   211: aload 4
    //   213: ifnull -76 -> 137
    //   216: aload 4
    //   218: invokevirtual 481	java/io/BufferedReader:close	()V
    //   221: aload_0
    //   222: areturn
    //   223: astore_3
    //   224: getstatic 16	com/hmt/analytics/android/a:a	Ljava/lang/String;
    //   227: new 55	java/lang/StringBuilder
    //   230: dup
    //   231: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   234: ldc 58
    //   236: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   239: aload_3
    //   240: invokevirtual 482	java/io/IOException:getMessage	()Ljava/lang/String;
    //   243: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   246: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   249: invokestatic 71	com/hmt/analytics/android/a:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   252: aload_0
    //   253: areturn
    //   254: astore_0
    //   255: aconst_null
    //   256: astore_3
    //   257: aload_3
    //   258: ifnull +7 -> 265
    //   261: aload_3
    //   262: invokevirtual 481	java/io/BufferedReader:close	()V
    //   265: aload_0
    //   266: athrow
    //   267: astore_3
    //   268: getstatic 16	com/hmt/analytics/android/a:a	Ljava/lang/String;
    //   271: new 55	java/lang/StringBuilder
    //   274: dup
    //   275: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   278: ldc 58
    //   280: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   283: aload_3
    //   284: invokevirtual 482	java/io/IOException:getMessage	()Ljava/lang/String;
    //   287: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   290: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   293: invokestatic 71	com/hmt/analytics/android/a:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   296: goto -31 -> 265
    //   299: aload_0
    //   300: ldc_w 403
    //   303: invokestatic 129	com/hmt/analytics/android/a:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   306: ifeq +130 -> 436
    //   309: invokestatic 455	com/hmt/analytics/android/a:i	()Ljava/lang/String;
    //   312: invokestatic 486	java/net/NetworkInterface:getByName	(Ljava/lang/String;)Ljava/net/NetworkInterface;
    //   315: astore_0
    //   316: aload_0
    //   317: ifnull -180 -> 137
    //   320: aload_0
    //   321: invokevirtual 425	java/net/NetworkInterface:getHardwareAddress	()[B
    //   324: astore_0
    //   325: aload_0
    //   326: ifnonnull +6 -> 332
    //   329: ldc 26
    //   331: areturn
    //   332: new 55	java/lang/StringBuilder
    //   335: dup
    //   336: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   339: astore_3
    //   340: aload_0
    //   341: arraylength
    //   342: istore_2
    //   343: iload_1
    //   344: iload_2
    //   345: if_icmpge +34 -> 379
    //   348: aload_3
    //   349: ldc_w 427
    //   352: iconst_1
    //   353: anewarray 4	java/lang/Object
    //   356: dup
    //   357: iconst_0
    //   358: aload_0
    //   359: iload_1
    //   360: baload
    //   361: invokestatic 432	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
    //   364: aastore
    //   365: invokestatic 436	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   368: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   371: pop
    //   372: iload_1
    //   373: iconst_1
    //   374: iadd
    //   375: istore_1
    //   376: goto -33 -> 343
    //   379: aload_3
    //   380: invokevirtual 439	java/lang/StringBuilder:length	()I
    //   383: ifle +14 -> 397
    //   386: aload_3
    //   387: aload_3
    //   388: invokevirtual 439	java/lang/StringBuilder:length	()I
    //   391: iconst_1
    //   392: isub
    //   393: invokevirtual 443	java/lang/StringBuilder:deleteCharAt	(I)Ljava/lang/StringBuilder;
    //   396: pop
    //   397: aload_3
    //   398: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   401: astore_0
    //   402: aload_0
    //   403: areturn
    //   404: astore_0
    //   405: getstatic 16	com/hmt/analytics/android/a:a	Ljava/lang/String;
    //   408: new 55	java/lang/StringBuilder
    //   411: dup
    //   412: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   415: ldc 58
    //   417: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   420: aload_0
    //   421: invokevirtual 65	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   424: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   427: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   430: invokestatic 71	com/hmt/analytics/android/a:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   433: ldc 26
    //   435: areturn
    //   436: getstatic 16	com/hmt/analytics/android/a:a	Ljava/lang/String;
    //   439: ldc_w 488
    //   442: invokestatic 71	com/hmt/analytics/android/a:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   445: ldc 26
    //   447: areturn
    //   448: astore_0
    //   449: goto -192 -> 257
    //   452: astore 5
    //   454: goto -277 -> 177
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	457	0	paramContext	Context
    //   1	375	1	i	int
    //   342	4	2	j	int
    //   4	134	3	localObject1	Object
    //   139	37	3	localIOException1	java.io.IOException
    //   179	32	3	localObject2	Object
    //   223	17	3	localIOException2	java.io.IOException
    //   256	6	3	localObject3	Object
    //   267	17	3	localIOException3	java.io.IOException
    //   339	59	3	localStringBuilder	StringBuilder
    //   40	177	4	localObject4	Object
    //   86	32	5	str	String
    //   170	26	5	localException1	Exception
    //   452	1	5	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   130	135	139	java/io/IOException
    //   58	76	170	java/lang/Exception
    //   216	221	223	java/io/IOException
    //   58	76	254	finally
    //   261	265	267	java/io/IOException
    //   309	316	404	java/lang/Exception
    //   320	325	404	java/lang/Exception
    //   332	343	404	java/lang/Exception
    //   348	372	404	java/lang/Exception
    //   379	397	404	java/lang/Exception
    //   397	402	404	java/lang/Exception
    //   81	88	448	finally
    //   96	117	448	finally
    //   180	209	448	finally
    //   81	88	452	java/lang/Exception
    //   96	117	452	java/lang/Exception
  }
  
  /* Error */
  public static an a(Context paramContext, boolean paramBoolean)
  {
    // Byte code:
    //   0: new 495	com/hmt/analytics/android/an
    //   3: dup
    //   4: invokespecial 496	com/hmt/analytics/android/an:<init>	()V
    //   7: astore_2
    //   8: aload_2
    //   9: ldc 26
    //   11: putfield 497	com/hmt/analytics/android/an:a	Ljava/lang/String;
    //   14: aload_2
    //   15: ldc 26
    //   17: putfield 499	com/hmt/analytics/android/an:b	Ljava/lang/String;
    //   20: iload_1
    //   21: ifeq +155 -> 176
    //   24: aload_0
    //   25: ldc -45
    //   27: invokevirtual 114	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   30: checkcast 213	android/location/LocationManager
    //   33: astore_3
    //   34: aload_3
    //   35: ifnull +141 -> 176
    //   38: aload_3
    //   39: invokevirtual 502	android/location/LocationManager:getAllProviders	()Ljava/util/List;
    //   42: invokeinterface 313 1 0
    //   47: astore 4
    //   49: aload 4
    //   51: invokeinterface 319 1 0
    //   56: ifeq +120 -> 176
    //   59: aload 4
    //   61: invokeinterface 323 1 0
    //   66: checkcast 49	java/lang/String
    //   69: astore 5
    //   71: getstatic 16	com/hmt/analytics/android/a:a	Ljava/lang/String;
    //   74: new 55	java/lang/StringBuilder
    //   77: dup
    //   78: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   81: ldc_w 504
    //   84: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: aload 5
    //   89: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   95: invokestatic 71	com/hmt/analytics/android/a:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   98: aload_0
    //   99: ldc -49
    //   101: invokestatic 129	com/hmt/analytics/android/a:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   104: istore_1
    //   105: iload_1
    //   106: ifeq -57 -> 49
    //   109: aload_3
    //   110: aload 5
    //   112: invokevirtual 508	android/location/LocationManager:getLastKnownLocation	(Ljava/lang/String;)Landroid/location/Location;
    //   115: astore 5
    //   117: aload 5
    //   119: ifnull -70 -> 49
    //   122: aload_2
    //   123: new 55	java/lang/StringBuilder
    //   126: dup
    //   127: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   130: aload 5
    //   132: invokevirtual 514	android/location/Location:getLatitude	()D
    //   135: invokevirtual 517	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   138: ldc 26
    //   140: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   146: putfield 497	com/hmt/analytics/android/an:a	Ljava/lang/String;
    //   149: aload_2
    //   150: new 55	java/lang/StringBuilder
    //   153: dup
    //   154: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   157: aload 5
    //   159: invokevirtual 520	android/location/Location:getLongitude	()D
    //   162: invokevirtual 517	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   165: ldc 26
    //   167: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   173: putfield 499	com/hmt/analytics/android/an:b	Ljava/lang/String;
    //   176: aload_2
    //   177: areturn
    //   178: astore 5
    //   180: getstatic 16	com/hmt/analytics/android/a:a	Ljava/lang/String;
    //   183: new 55	java/lang/StringBuilder
    //   186: dup
    //   187: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   190: ldc 58
    //   192: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: aload 5
    //   197: invokevirtual 521	java/lang/SecurityException:getMessage	()Ljava/lang/String;
    //   200: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   206: invokestatic 71	com/hmt/analytics/android/a:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   209: goto -160 -> 49
    //   212: astore_0
    //   213: getstatic 16	com/hmt/analytics/android/a:a	Ljava/lang/String;
    //   216: new 55	java/lang/StringBuilder
    //   219: dup
    //   220: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   223: ldc 58
    //   225: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   228: aload_0
    //   229: invokevirtual 65	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   232: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   235: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   238: invokestatic 71	com/hmt/analytics/android/a:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   241: aload_2
    //   242: areturn
    //   243: astore 5
    //   245: getstatic 16	com/hmt/analytics/android/a:a	Ljava/lang/String;
    //   248: new 55	java/lang/StringBuilder
    //   251: dup
    //   252: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   255: ldc 58
    //   257: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   260: aload 5
    //   262: invokevirtual 522	java/lang/IllegalArgumentException:getMessage	()Ljava/lang/String;
    //   265: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   268: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   271: invokestatic 71	com/hmt/analytics/android/a:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   274: goto -225 -> 49
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	277	0	paramContext	Context
    //   0	277	1	paramBoolean	boolean
    //   7	235	2	localAn	an
    //   33	77	3	localLocationManager	LocationManager
    //   47	13	4	localIterator	Iterator
    //   69	89	5	localObject	Object
    //   178	18	5	localSecurityException	SecurityException
    //   243	18	5	localIllegalArgumentException	IllegalArgumentException
    // Exception table:
    //   from	to	target	type
    //   109	117	178	java/lang/SecurityException
    //   122	176	178	java/lang/SecurityException
    //   24	34	212	java/lang/Exception
    //   38	49	212	java/lang/Exception
    //   49	105	212	java/lang/Exception
    //   109	117	212	java/lang/Exception
    //   122	176	212	java/lang/Exception
    //   180	209	212	java/lang/Exception
    //   245	274	212	java/lang/Exception
    //   109	117	243	java/lang/IllegalArgumentException
    //   122	176	243	java/lang/IllegalArgumentException
  }
  
  public static Boolean a(String[] paramArrayOfString, String paramString)
  {
    if (paramArrayOfString != null)
    {
      int i = 0;
      while (i < paramArrayOfString.length)
      {
        if (paramArrayOfString[i].equals(paramString)) {
          return Boolean.valueOf(true);
        }
        i += 1;
      }
    }
    return Boolean.valueOf(false);
  }
  
  public static String a()
  {
    return Long.valueOf(System.currentTimeMillis()).toString();
  }
  
  public static String a(Context paramContext, int paramInt)
  {
    Object localObject1;
    if (paramContext == null) {
      localObject1 = "";
    }
    for (;;)
    {
      return localObject1;
      try
      {
        localObject1 = paramContext.toString();
        str1 = ((String)localObject1).substring(0, ((String)localObject1).indexOf("@"));
        String str2 = paramContext.getPackageName();
        localObject1 = str1;
        if (str2 != null)
        {
          localObject1 = str1;
          if (!str2.equals(""))
          {
            localObject1 = str1;
            if (paramInt == 1)
            {
              localObject1 = str1.replaceFirst(str2, "");
              return localObject1;
            }
          }
        }
      }
      catch (Exception localException1)
      {
        String str1;
        a(a, "Collected:" + localException1.getMessage());
        if (a(paramContext, "android.permission.GET_TASKS"))
        {
          Object localObject2 = (ActivityManager)paramContext.getSystemService("activity");
          try
          {
            localObject2 = ((ActivityManager)localObject2).getRunningTasks(1);
            if ((localObject2 == null) || (((List)localObject2).size() <= 0)) {
              break label267;
            }
            localObject2 = ((ActivityManager.RunningTaskInfo)((List)localObject2).get(0)).topActivity;
            a(a, "getActivityName : " + ((ComponentName)localObject2).getClassName());
            str1 = ((ComponentName)localObject2).getClassName();
            paramContext = paramContext.getPackageName();
            localObject2 = str1;
            if (paramContext == null) {
              continue;
            }
            localObject2 = str1;
            if (paramContext.equals("")) {
              continue;
            }
            localObject2 = str1;
            if (paramInt != 1) {
              continue;
            }
            return str1.replaceFirst(paramContext, "");
          }
          catch (Exception localException2)
          {
            for (;;)
            {
              a(a, "Collected:" + localException2.getMessage());
              Object localObject3 = null;
            }
          }
        }
        else
        {
          a(a, "getActivityName : lost permission:android.permission.GET_TASKS");
        }
      }
    }
    label267:
    return "";
  }
  
  public static void a(Context paramContext, int paramInt, String paramString)
  {
    a(a, "setReportPolicy: reportType = " + paramInt);
    if ((paramInt == 0) || (paramInt == 1)) {
      synchronized (g.g)
      {
        r.a(paramContext, "hmt_agent_online_setting", "hmtlocal_report_policy_" + paramString, Integer.valueOf(paramInt));
        return;
      }
    }
  }
  
  public static void a(Context paramContext, String[] arg1)
  {
    String str1 = "";
    String str2 = str1;
    if (??? != null)
    {
      str2 = str1;
      if (???.length > 0)
      {
        int j = ???.length;
        int i = 0;
        for (;;)
        {
          str2 = str1;
          if (i >= j) {
            break;
          }
          str2 = ???[i];
          str1 = str1 + str2;
          str1 = str1 + "#";
          i += 1;
        }
      }
    }
    synchronized (g.g)
    {
      r.a(paramContext, "hmt_untracked_activity", str2);
      return;
    }
  }
  
  public static void a(Context paramContext, String[] arg1, String paramString)
  {
    String str1 = paramString;
    if (!paramString.equals("server")) {
      str1 = "client";
    }
    paramString = "";
    String str2 = paramString;
    if (??? != null)
    {
      str2 = paramString;
      if (???.length > 0)
      {
        int j = ???.length;
        int i = 0;
        for (;;)
        {
          str2 = paramString;
          if (i >= j) {
            break;
          }
          str2 = ???[i];
          paramString = paramString + str2;
          paramString = paramString + "#";
          i += 1;
        }
      }
    }
    a(a, "setUnTracked : type  = " + str1 + " , unTrackedStr = " + str2);
    synchronized (g.g)
    {
      r.a(paramContext, "hmt_agent_online_setting", "hmtlocal_untracked_" + str1, str2);
      return;
    }
  }
  
  public static void a(String paramString1, String paramString2)
  {
    paramString2.startsWith("Collected:");
    if ((g.b) && (!TextUtils.isEmpty(paramString2))) {
      Log.e(paramString1, paramString2);
    }
  }
  
  public static boolean a(Context paramContext)
  {
    try
    {
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext == null) {
        return false;
      }
      int i = paramContext.getActiveNetworkInfo().getType();
      return i == 1;
    }
    catch (Exception paramContext)
    {
      a(a, "Collected:" + paramContext.getMessage());
    }
    return false;
  }
  
  public static boolean a(Context paramContext, long paramLong)
  {
    long l1 = ((Long)r.b(paramContext, "first_init_time", Long.valueOf(0L))).longValue();
    if (l1 == 0L) {}
    long l2;
    do
    {
      return false;
      l2 = System.currentTimeMillis();
    } while (l2 < l1);
    if (l2 - l1 > paramLong * 60L * 60L * 1000L) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    boolean bool = false;
    try
    {
      int i = paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName());
      if (i == 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext)
    {
      a(a, "Collected:" + paramContext.getMessage());
    }
    return false;
  }
  
  public static boolean a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    while ((paramString.equals("02:00:00:00:00:00")) || (paramString.equals("00:00:00:00:00:00")) || (paramString.equals("ff:ff:ff:ff:ff:ff"))) {
      return false;
    }
    return true;
  }
  
  public static Boolean b(String[] paramArrayOfString, String paramString)
  {
    if (paramArrayOfString != null)
    {
      int i = 0;
      while (i < paramArrayOfString.length)
      {
        if (paramArrayOfString[i].equals(paramString)) {
          return Boolean.valueOf(true);
        }
        i += 1;
      }
    }
    return Boolean.valueOf(false);
  }
  
  public static String b(Context paramContext)
  {
    return (String)r.b(paramContext, "hmt_agent_online_setting", "muid", "");
  }
  
  public static void b(Context paramContext, String paramString)
  {
    r.a(paramContext, "manual_setting_imei", paramString);
  }
  
  public static boolean b()
  {
    try
    {
      if (("sdk".equals(Build.MODEL)) || ("sdk".equals(Build.PRODUCT))) {
        break label40;
      }
      boolean bool = "generic".equals(Build.DEVICE);
      if (bool) {
        break label40;
      }
    }
    catch (NoSuchFieldError localNoSuchFieldError)
    {
      label40:
      do
      {
        a(a, "Collected:" + localNoSuchFieldError.getMessage());
        if ("sdk".equals(Build.MODEL)) {
          break;
        }
      } while (!"sdk".equals(Build.PRODUCT));
    }
    return true;
    return false;
    return false;
  }
  
  public static boolean b(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    while ((paramString.equals("000000000000000")) || (paramString.equals("00000000"))) {
      return false;
    }
    return true;
  }
  
  public static String c()
  {
    String str1 = Build.MANUFACTURER;
    String str2 = Build.MODEL;
    if (str2.startsWith(str1)) {
      return e(str2);
    }
    return e(str1) + " " + str2;
  }
  
  public static void c(Context paramContext, String paramString)
  {
    r.a(paramContext, "manual_setting_channel_id", paramString.trim());
  }
  
  public static boolean c(Context paramContext)
  {
    boolean bool2 = false;
    if (a(paramContext, "android.permission.ACCESS_WIFI_STATE"))
    {
      paramContext = (ConnectivityManager)paramContext.getApplicationContext().getSystemService("connectivity");
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
          if ((paramContext[i].getTypeName().equals("WIFI")) && (paramContext[i].isConnected())) {
            bool1 = true;
          }
        }
        else {
          return bool1;
        }
        i += 1;
      }
    }
    a(a, "isWiFiActive : lost permission:android.permission.ACCESS_WIFI_STATE");
    return false;
  }
  
  public static boolean c(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    while ((paramString.equals("0f607264fc6318a92b9e13c65db7cd3c")) || (paramString.equals("3f0fe74b555ff95d563a2cfe3cb9c834")) || (paramString.equals("5284047f4ffb4e04824a2fd1d1f0cd62")) || (paramString.equals("528c8e6cd4a3c6598999a0e9df15ad32")) || (paramString.equals("b21929f60cb26fe36e48926c33f1903c")) || (paramString.equals("dd4b21e9ef71e1291183a46b913ae6f2")) || (paramString.equals("feef34bbe6f4a1f343ad614c1b25f9b9"))) {
      return false;
    }
    return true;
  }
  
  public static String d(String paramString)
  {
    paramString = f(paramString);
    return String.format("%0" + (paramString.length << 1) + "X", new Object[] { new BigInteger(1, paramString) });
  }
  
  public static void d(Context paramContext, String paramString)
  {
    r.a(paramContext, "manual_setting_appkey", paramString.trim());
  }
  
  public static boolean d()
  {
    String[] arrayOfString = new String[5];
    arrayOfString[0] = "/system/bin/";
    arrayOfString[1] = "/system/xbin/";
    arrayOfString[2] = "/system/sbin/";
    arrayOfString[3] = "/sbin/";
    arrayOfString[4] = "/vendor/bin/";
    int i = 0;
    try
    {
      while (i < arrayOfString.length)
      {
        File localFile = new File(arrayOfString[i] + "su");
        if (localFile != null)
        {
          boolean bool = localFile.exists();
          if (bool) {
            return true;
          }
        }
        i += 1;
      }
      return false;
    }
    catch (Exception localException)
    {
      a(a, "Collected:" + localException.getMessage());
    }
  }
  
  public static boolean d(Context paramContext)
  {
    if (a(paramContext, "android.permission.INTERNET"))
    {
      if (a(paramContext, "android.permission.ACCESS_NETWORK_STATE"))
      {
        paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
        if (paramContext == null) {
          return false;
        }
        try
        {
          paramContext = paramContext.getActiveNetworkInfo();
          if ((paramContext != null) && (paramContext.isAvailable())) {
            return true;
          }
        }
        catch (Exception paramContext)
        {
          for (;;)
          {
            a(a, "Collected:" + paramContext.getMessage());
            paramContext = null;
          }
          a(a, "isNetworkAvailable : Network error");
          return false;
        }
      }
      a(a, "isNetworkAvailable : lost permission: android.permission.ACCESS_NETWORK_STATE");
      return false;
    }
    a(a, "isNetworkAvailable : lost permission:android.permission.INTERNET");
    return false;
  }
  
  public static String e()
  {
    return g.m;
  }
  
  public static String e(Context paramContext)
  {
    Object localObject2;
    if (paramContext == null) {
      localObject2 = "";
    }
    for (;;)
    {
      return localObject2;
      localObject1 = (String)r.b(paramContext, "manual_setting_appkey", "");
      localObject2 = localObject1;
      if (TextUtils.isEmpty((CharSequence)localObject1)) {
        try
        {
          paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
          localObject2 = localObject1;
          if (paramContext != null)
          {
            paramContext = paramContext.metaData.getString("HMT_APPKEY");
            boolean bool = TextUtils.isEmpty(paramContext);
            if (bool) {}
          }
        }
        catch (Exception paramContext) {}
      }
    }
    try
    {
      localObject1 = paramContext.toString().trim();
      return localObject1;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localObject1 = paramContext;
        paramContext = localException;
      }
    }
    a(a, "getAppKey : Could not read HMT_APPKEY meta-data from AndroidManifest.xml.");
    return localObject1;
    a(a, "getAppKey exception : Could not read HMT_APPKEY meta-data from AndroidManifest.xml");
    a(a, "Collected:" + paramContext.getMessage());
    return localObject1;
  }
  
  private static String e(String paramString)
  {
    String str;
    if ((paramString == null) || (paramString.length() == 0)) {
      str = "";
    }
    char c;
    do
    {
      return str;
      c = paramString.charAt(0);
      str = paramString;
    } while (Character.isUpperCase(c));
    return Character.toUpperCase(c) + paramString.substring(1);
  }
  
  public static String f()
  {
    return g.n;
  }
  
  /* Error */
  public static String f(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +8 -> 9
    //   4: ldc 26
    //   6: astore_1
    //   7: aload_1
    //   8: areturn
    //   9: aload_0
    //   10: ldc_w 672
    //   13: ldc 26
    //   15: invokestatic 95	com/hmt/analytics/util/r:b	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;
    //   18: checkcast 49	java/lang/String
    //   21: astore_2
    //   22: aload_2
    //   23: astore_1
    //   24: aload_2
    //   25: invokestatic 104	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   28: ifeq -21 -> 7
    //   31: ldc_w 792
    //   34: astore_2
    //   35: aload_0
    //   36: invokevirtual 32	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   39: aload_0
    //   40: invokevirtual 35	android/content/Context:getPackageName	()Ljava/lang/String;
    //   43: sipush 128
    //   46: invokevirtual 752	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   49: astore_0
    //   50: aload_2
    //   51: astore_1
    //   52: aload_0
    //   53: ifnull -46 -> 7
    //   56: aload_0
    //   57: getfield 756	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   60: ldc_w 794
    //   63: invokevirtual 763	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   66: astore_0
    //   67: aload_2
    //   68: astore_1
    //   69: aload_0
    //   70: ifnull -63 -> 7
    //   73: aload_0
    //   74: invokevirtual 764	java/lang/String:toString	()Ljava/lang/String;
    //   77: invokevirtual 675	java/lang/String:trim	()Ljava/lang/String;
    //   80: astore_1
    //   81: aload_1
    //   82: areturn
    //   83: astore_0
    //   84: getstatic 16	com/hmt/analytics/android/a:a	Ljava/lang/String;
    //   87: ldc_w 796
    //   90: invokestatic 71	com/hmt/analytics/android/a:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   93: getstatic 16	com/hmt/analytics/android/a:a	Ljava/lang/String;
    //   96: new 55	java/lang/StringBuilder
    //   99: dup
    //   100: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   103: ldc 58
    //   105: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   108: aload_0
    //   109: invokevirtual 65	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   112: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   118: invokestatic 71	com/hmt/analytics/android/a:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   121: aload_2
    //   122: areturn
    //   123: astore_1
    //   124: aload_0
    //   125: astore_2
    //   126: aload_1
    //   127: astore_0
    //   128: goto -44 -> 84
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	131	0	paramContext	Context
    //   6	76	1	localObject1	Object
    //   123	4	1	localException	Exception
    //   21	105	2	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   35	50	83	java/lang/Exception
    //   56	67	83	java/lang/Exception
    //   73	81	123	java/lang/Exception
  }
  
  private static byte[] f(String paramString)
  {
    Object localObject = null;
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-256");
      localObject = localMessageDigest;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      for (;;)
      {
        a(a, "Collected:" + localNoSuchAlgorithmException.getMessage());
      }
    }
    return localObject.digest(paramString.getBytes());
  }
  
  public static String g()
  {
    return "";
  }
  
  public static String g(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).packageName;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      a(a, "Collected:" + paramContext.getMessage());
    }
    return "";
  }
  
  public static String h()
  {
    return "";
  }
  
  public static String h(Context paramContext)
  {
    String str = Build.VERSION.RELEASE;
    a(a, "getOsVersion : " + str);
    paramContext = str;
    if (str == null) {
      paramContext = "";
    }
    return paramContext;
  }
  
  private static String i()
  {
    if (Build.VERSION.SDK_INT > 27) {
      return "";
    }
    try
    {
      Object localObject = Class.forName("android.os.SystemProperties");
      localObject = (String)((Class)localObject).getDeclaredMethod("get", new Class[] { String.class, String.class }).invoke(((Class)localObject).newInstance(), new Object[] { "wifi.interface", "wlan0" });
      return localObject;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        a(a, "Collected:" + localException.getMessage());
        String str = "";
      }
    }
  }
  
  public static String i(Context paramContext)
  {
    if (paramContext == null) {
      localObject1 = "";
    }
    String str2;
    do
    {
      do
      {
        return localObject1;
        str2 = paramContext.getPackageName();
        localObject2 = (String)r.b(paramContext, g.s + str2, g.u, "");
        localObject1 = localObject2;
      } while (c((String)localObject2));
      localObject2 = (String)r.b(paramContext, g.t, g.u, "");
      localObject1 = localObject2;
    } while (c((String)localObject2));
    Object localObject2 = E(paramContext);
    Object localObject1 = localObject2;
    if (!b((String)localObject2)) {
      localObject1 = "";
    }
    String str1 = F(paramContext);
    localObject2 = str1;
    if (!a(str1)) {
      localObject2 = "";
    }
    localObject1 = (String)localObject1 + (String)localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject1))
    {
      localObject1 = w(paramContext);
      if ((!TextUtils.isEmpty((CharSequence)localObject1)) && (!((String)localObject1).equals("9774d56d682e549c")))
      {
        localObject2 = localObject1;
        if (((String)localObject1).length() >= 15) {
          break label198;
        }
      }
    }
    for (localObject2 = new BigInteger(64, new SecureRandom()).toString(16);; localObject2 = n.a((String)localObject1))
    {
      label198:
      localObject1 = localObject2;
      if (TextUtils.isEmpty((CharSequence)localObject2)) {
        break;
      }
      r.a(paramContext, g.s + str2, g.u, localObject2);
      r.a(paramContext, g.t, g.u, localObject2);
      return localObject2;
    }
  }
  
  public static boolean j(Context paramContext)
  {
    return a(paramContext, "android.permission.READ_PHONE_STATE");
  }
  
  public static String k(Context paramContext)
  {
    if (!j(paramContext))
    {
      paramContext = Build.VERSION.RELEASE;
      a(a, "getSdkVersion : " + paramContext);
      return paramContext;
    }
    a(a, "getSdkVersion : get failed");
    return null;
  }
  
  /* Error */
  public static String l(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 32	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: aload_0
    //   5: invokevirtual 35	android/content/Context:getPackageName	()Ljava/lang/String;
    //   8: iconst_0
    //   9: invokevirtual 41	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   12: getfield 340	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   15: astore_0
    //   16: aload_0
    //   17: ifnull +12 -> 29
    //   20: aload_0
    //   21: astore_1
    //   22: aload_0
    //   23: invokevirtual 769	java/lang/String:length	()I
    //   26: ifgt +6 -> 32
    //   29: ldc 26
    //   31: astore_1
    //   32: aload_1
    //   33: areturn
    //   34: astore_1
    //   35: ldc 26
    //   37: astore_0
    //   38: getstatic 16	com/hmt/analytics/android/a:a	Ljava/lang/String;
    //   41: new 55	java/lang/StringBuilder
    //   44: dup
    //   45: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   48: ldc 58
    //   50: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: aload_1
    //   54: invokevirtual 65	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   57: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   63: invokestatic 71	com/hmt/analytics/android/a:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   66: aload_0
    //   67: areturn
    //   68: astore_1
    //   69: goto -31 -> 38
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	72	0	paramContext	Context
    //   21	12	1	localObject	Object
    //   34	20	1	localException1	Exception
    //   68	1	1	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   0	16	34	java/lang/Exception
    //   22	29	68	java/lang/Exception
  }
  
  public static int m(Context paramContext)
  {
    int j = ((Integer)r.b(paramContext, "hmt_agent_online_setting", "hmtlocal_report_policy_server", Integer.valueOf(10000))).intValue();
    a(a, "getReportPolicyMode :server=======>" + j);
    int i = j;
    if (j == 10000) {
      i = ((Integer)r.b(paramContext, "hmt_agent_online_setting", "hmtlocal_report_policy_client", Integer.valueOf(10000))).intValue();
    }
    a(a, "getReportPolicyMode :client=======>" + i);
    j = i;
    if (i == 10000) {
      j = 0;
    }
    return j;
  }
  
  public static String[] n(Context paramContext)
  {
    String str2 = (String)r.b(paramContext, "hmt_agent_online_setting", "hmtlocal_untracked_server", "");
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = (String)r.b(paramContext, "hmt_agent_online_setting", "hmtlocal_untracked_client", "");
    }
    if ((str1 != null) && (str1 != "")) {
      return str1.split("#");
    }
    return null;
  }
  
  public static String[] o(Context paramContext)
  {
    paramContext = (String)r.b(paramContext, "hmt_untracked_activity", "");
    if (!TextUtils.isEmpty(paramContext)) {
      return paramContext.split("#");
    }
    return null;
  }
  
  public static String p(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext == null) {
      paramContext = "";
    }
    String str;
    do
    {
      return paramContext;
      str = paramContext.getSimOperator();
      paramContext = str;
    } while (str != null);
    return "";
  }
  
  public static as q(Context paramContext)
  {
    as localAs = new as();
    for (;;)
    {
      try
      {
        localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
        if (localTelephonyManager == null) {
          return null;
        }
        if (!a(paramContext, "android.permission.ACCESS_COARSE_LOCATION")) {
          continue;
        }
        if (!(localTelephonyManager.getCellLocation() instanceof GsmCellLocation)) {
          continue;
        }
        paramContext = (GsmCellLocation)localTelephonyManager.getCellLocation();
        if (paramContext == null)
        {
          a(a, "getCellInfo : GsmCellLocation is null");
          return null;
        }
        i = paramContext.getCid();
        localAs.d = paramContext.getLac();
        localAs.e = i;
      }
      catch (Exception paramContext)
      {
        TelephonyManager localTelephonyManager;
        int i;
        int j;
        a(a, "Collected:" + paramContext.getMessage());
        return localAs;
        a(a, "getCellInfo : lost permission = android.permission.ACCESS_COARSE_LOCATION");
        continue;
      }
      paramContext = localTelephonyManager.getNetworkOperator();
      if ((paramContext == null) || (paramContext.equals(""))) {
        return localAs;
      }
      i = Integer.parseInt(paramContext.substring(0, 3));
      j = Integer.parseInt(paramContext.substring(3));
      localAs.a = i;
      localAs.c = j;
      localAs.b = Integer.parseInt(paramContext);
      return localAs;
      if ((localTelephonyManager.getCellLocation() instanceof CdmaCellLocation))
      {
        paramContext = (CdmaCellLocation)localTelephonyManager.getCellLocation();
        if (paramContext == null)
        {
          a(a, "getCellInfo : CdmaCellLocation is null");
          return null;
        }
        i = paramContext.getBaseStationId();
        localAs.d = paramContext.getNetworkId();
        localAs.e = i;
      }
    }
    return localAs;
  }
  
  public static boolean r(Context paramContext)
  {
    if (!b()) {
      return false;
    }
    try
    {
      paramContext = (SensorManager)paramContext.getSystemService("sensor");
      if (paramContext == null) {
        return false;
      }
    }
    catch (NoSuchFieldError paramContext)
    {
      for (;;)
      {
        a(a, "Collected:" + paramContext.getMessage());
        paramContext = null;
      }
    }
    return true;
  }
  
  public static String s(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext == null) {
      paramContext = "";
    }
    int i;
    do
    {
      return paramContext;
      i = paramContext.getNetworkType();
      paramContext = "UNKNOWN";
      if (i == 4) {
        paramContext = "CDMA";
      }
      if (i == 2) {
        paramContext = "EDGE";
      }
      if (i == 5) {
        paramContext = "EVDO_0";
      }
      if (i == 6) {
        paramContext = "EVDO_A";
      }
      if (i == 1) {
        paramContext = "GPRS";
      }
      if (i == 8) {
        paramContext = "HSDPA";
      }
      if (i == 10) {
        paramContext = "HSPA";
      }
      if (i == 9) {
        paramContext = "HSUPA";
      }
      if (i == 3) {
        paramContext = "UMTS";
      }
      if (i == 0) {
        paramContext = "UNKNOWN";
      }
      if (i == 7) {
        paramContext = "1xRTT";
      }
      if (i == 11) {
        paramContext = "iDen";
      }
      if (i == 12) {
        paramContext = "EVDO_B";
      }
      if (i == 13) {
        paramContext = "LTE";
      }
      if (i == 14) {
        paramContext = "eHRPD";
      }
    } while (i != 15);
    return "HSPA+";
  }
  
  public static boolean t(Context paramContext)
  {
    if (a(paramContext, "android.permission.INTERNET"))
    {
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext == null) {
        return false;
      }
      paramContext = paramContext.getActiveNetworkInfo();
      if ((paramContext != null) && (paramContext.isAvailable()) && (paramContext.getTypeName().equals("WIFI"))) {
        return true;
      }
      a(a, "isNetworkTypeWifi : Network not wifi");
      return false;
    }
    a(a, "isNetworkTypeWifi : lost permission = android.permission.INTERNET");
    return false;
  }
  
  public static String u(Context paramContext)
  {
    Object localObject2;
    if (paramContext == null) {
      localObject2 = "";
    }
    for (;;)
    {
      return localObject2;
      try
      {
        str = (String)r.b(paramContext, "manual_app_version", "");
        localObject2 = str;
        localObject1 = str;
      }
      catch (Exception paramContext)
      {
        try
        {
          String str;
          if (!TextUtils.isEmpty(str)) {
            continue;
          }
          localObject1 = str;
          paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
          if (paramContext != null)
          {
            localObject2 = paramContext;
            localObject1 = paramContext;
            if (paramContext.length() > 0) {
              continue;
            }
          }
          return "";
        }
        catch (Exception paramContext)
        {
          Object localObject1;
          for (;;) {}
        }
        paramContext = paramContext;
        localObject1 = "";
      }
    }
    a(a, "Collected:" + paramContext.getMessage());
    return localObject1;
  }
  
  public static String v(Context paramContext)
  {
    return d.a(paramContext);
  }
  
  public static String w(Context paramContext)
  {
    try
    {
      String str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      paramContext = str;
      if (str == null) {
        paramContext = "";
      }
      return paramContext;
    }
    catch (NullPointerException paramContext)
    {
      a(a, "Collected:" + paramContext.getMessage());
    }
    return "";
  }
  
  public static String x(Context paramContext)
  {
    if (!o.b()) {
      o.a(paramContext);
    }
    String str = o.a();
    paramContext = str;
    if (str == null) {
      paramContext = "";
    }
    return paramContext;
  }
  
  public static String y(Context paramContext)
  {
    paramContext = x(paramContext);
    if (TextUtils.isEmpty(paramContext)) {
      return "";
    }
    return n.a(paramContext);
  }
  
  public static String z(Context paramContext)
  {
    Object localObject3 = null;
    for (;;)
    {
      try
      {
        Object localObject1 = paramContext.getApplicationContext().getPackageManager();
        ApplicationInfo localApplicationInfo;
        a(a, "Collected:" + localException1.getMessage());
      }
      catch (Exception localException1)
      {
        for (;;)
        {
          try
          {
            localApplicationInfo = ((PackageManager)localObject1).getApplicationInfo(paramContext.getPackageName(), 0);
            if (localObject1 == null) {
              continue;
            }
          }
          catch (Exception localException2)
          {
            Object localObject2;
            continue;
          }
          try
          {
            localObject1 = (String)((PackageManager)localObject1).getApplicationLabel(localApplicationInfo);
            paramContext = (String)localObject1 + "_" + u(paramContext);
            return paramContext;
          }
          catch (Exception paramContext)
          {
            a(a, "Collected:" + paramContext.getMessage());
          }
        }
        localException1 = localException1;
        localObject1 = null;
      }
      localObject2 = localObject3;
    }
    return "";
  }
}
