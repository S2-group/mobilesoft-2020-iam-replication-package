package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.Timer;
import java.util.Vector;
import org.w3c.dom.Document;

public class e
{
  private static String A = "";
  private static String B = "";
  private static String C = "";
  private static String D = "";
  private static String E = "";
  private static String F = "";
  private static String G = "";
  private static String H = "";
  private static String I = "";
  private static String J = "native";
  private static String K = "";
  private static String L = "";
  private static boolean M = false;
  private static String N = "";
  private static float O = 1.0F;
  private static boolean P = false;
  private static boolean Q = false;
  private static boolean R = false;
  private static boolean S = false;
  private static boolean T = false;
  private static String U = null;
  private static long Y = 0L;
  private static Context a = null;
  private static PackageManager aa;
  private static Hashtable ab = null;
  private static String ac = "";
  private static e b = null;
  private static u c = null;
  private static j d = null;
  private static ab e = null;
  private static Vector f = new Vector(Arrays.asList(k.a));
  private static String g = "";
  private static String h = "";
  private static String i = "";
  private static String j = "";
  private static String k = "";
  private static String l = "";
  private static String m = "";
  private static String n = "";
  private static String o = "";
  private static String p = "";
  private static String q = "";
  private static String r = "";
  private static String s = "";
  private static String t = "";
  private static String u = "";
  private static int v = 1;
  private static float w = 1.0F;
  private static int x = 1;
  private static String y = "";
  private static boolean z = false;
  private long V = 0L;
  private Timer W = null;
  private boolean X = false;
  private boolean Z = false;
  
  public e(Context paramContext)
  {
    a = paramContext;
    c = new u();
    if (o())
    {
      t.a("TapjoyConnect", "URL parameters: " + b());
      a();
      j();
      if ((i("user_id") != null) && (i("user_id").length() > 0))
      {
        t.a("TapjoyConnect", "Setting userID to: " + i("user_id"));
        e(i("user_id"));
      }
      this.Z = true;
    }
  }
  
  private void A()
  {
    int i2 = 0;
    Vector localVector = new Vector();
    String[] arrayOfString = k.b;
    int i3 = arrayOfString.length;
    int i1 = 0;
    String str;
    while (i1 < i3)
    {
      str = arrayOfString[i1];
      if (!l(str)) {
        localVector.add(str);
      }
      i1 += 1;
    }
    if (localVector.size() != 0)
    {
      if (localVector.size() == 1) {
        throw new s("Missing 1 permission in manifest: " + localVector.toString());
      }
      throw new s("Missing " + localVector.size() + " permissions in manifest: " + localVector.toString());
    }
    localVector = new Vector();
    arrayOfString = k.c;
    i3 = arrayOfString.length;
    i1 = i2;
    while (i1 < i3)
    {
      str = arrayOfString[i1];
      if (!l(str)) {
        localVector.add(str);
      }
      i1 += 1;
    }
    if (localVector.size() != 0)
    {
      if (localVector.size() == 1) {
        t.c("TapjoyConnect", "WARNING -- " + localVector.toString() + " permission was not found in manifest. The exclusion of this permission could cause problems.");
      }
    }
    else {
      return;
    }
    t.c("TapjoyConnect", "WARNING -- " + localVector.toString() + " permissions were not found in manifest. The exclusion of these permissions could cause problems.");
  }
  
  /* Error */
  private void B()
  {
    // Byte code:
    //   0: ldc_w 299
    //   3: invokestatic 305	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   6: astore_1
    //   7: aload_1
    //   8: ldc_w 307
    //   11: iconst_0
    //   12: anewarray 301	java/lang/Class
    //   15: invokevirtual 311	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   18: pop
    //   19: return
    //   20: astore_1
    //   21: new 271	com/tapjoy/s
    //   24: dup
    //   25: ldc_w 313
    //   28: invokespecial 276	com/tapjoy/s:<init>	(Ljava/lang/String;)V
    //   31: athrow
    //   32: astore_1
    //   33: new 271	com/tapjoy/s
    //   36: dup
    //   37: ldc_w 315
    //   40: invokespecial 276	com/tapjoy/s:<init>	(Ljava/lang/String;)V
    //   43: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	44	0	this	e
    //   6	2	1	localClass	Class
    //   20	1	1	localClassNotFoundException	ClassNotFoundException
    //   32	1	1	localNoSuchMethodException	NoSuchMethodException
    // Exception table:
    //   from	to	target	type
    //   0	7	20	java/lang/ClassNotFoundException
    //   7	19	32	java/lang/NoSuchMethodException
  }
  
  private static String C()
  {
    t.a("TapjoyConnect", "generating sessionID...");
    try
    {
      str = v.a(System.currentTimeMillis() / 1000L + r + i);
      t.b("TapjoyConnect", "unable to generate session id: " + localException1.toString());
    }
    catch (Exception localException1)
    {
      try
      {
        Y = System.currentTimeMillis();
        return str;
      }
      catch (Exception localException2)
      {
        String str;
        for (;;) {}
      }
      localException1 = localException1;
      str = null;
    }
    return str;
  }
  
  private static String D()
  {
    if ((i != null) && (i.length() > 0)) {
      return i;
    }
    if ((k != null) && (k.length() > 0)) {
      return k;
    }
    if ((g != null) && (g.length() > 0)) {
      return g;
    }
    Log.e("TapjoyConnect", "Error -- no valid device identifier");
    return null;
  }
  
  private static String a(long paramLong)
  {
    try
    {
      String str = v.a(r + ":" + D() + ":" + paramLong + ":" + I);
      return str;
    }
    catch (Exception localException)
    {
      t.b("TapjoyConnect", "getVerifier ERROR: " + localException.toString());
    }
    return "";
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, Hashtable paramHashtable, j paramJ)
  {
    r = paramString1;
    I = paramString2;
    ab = paramHashtable;
    d = paramJ;
    b = new e(paramContext);
  }
  
  private void a(ActivityInfo paramActivityInfo)
  {
    if (f.contains(paramActivityInfo.name))
    {
      int i1 = f.indexOf(paramActivityInfo.name);
      Vector localVector;
      try
      {
        Class.forName((String)f.get(i1));
        localVector = new Vector();
        if ((paramActivityInfo.configChanges & 0x80) != 128) {
          localVector.add("orientation");
        }
        if ((paramActivityInfo.configChanges & 0x20) != 32) {
          localVector.add("keyboardHidden");
        }
        if ((Build.VERSION.SDK_INT >= 13) && ((paramActivityInfo.configChanges & 0x400) != 1024)) {
          localVector.add("screenSize");
        }
        if (localVector.size() == 0) {
          break label259;
        }
        if (localVector.size() == 1) {
          throw new s(localVector.toString() + " property is not specified in manifest configChanges for " + (String)f.get(i1));
        }
      }
      catch (ClassNotFoundException paramActivityInfo)
      {
        throw new s("[ClassNotFoundException] Could not find dependency class " + (String)f.get(i1));
      }
      throw new s(localVector.toString() + " properties are not specified in manifest configChanges for " + (String)f.get(i1));
      label259:
      if ((Build.VERSION.SDK_INT >= 11) && (paramActivityInfo.name.equals("com.tapjoy.TJAdUnitView")) && ((paramActivityInfo.flags & 0x200) != 512)) {
        throw new s("'hardwareAccelerated' property not specified in manifest for " + (String)f.get(i1));
      }
      f.remove(i1);
    }
  }
  
  private void a(Properties paramProperties)
  {
    Enumeration localEnumeration = paramProperties.keys();
    while (localEnumeration.hasMoreElements()) {
      try
      {
        String str1 = (String)localEnumeration.nextElement();
        String str2 = (String)paramProperties.get(str1);
        ab.put(str1, str2);
      }
      catch (ClassCastException localClassCastException)
      {
        t.b("TapjoyConnect", "Error parsing configuration properties in tapjoy_config.txt");
      }
    }
  }
  
  public static void a(boolean paramBoolean)
  {
    M = paramBoolean;
  }
  
  private static String b(long paramLong, String paramString)
  {
    try
    {
      paramString = v.a(r + ":" + i + ":" + paramLong + ":" + I + ":" + paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      t.b("TapjoyConnect", "getVerifier ERROR: " + paramString.toString());
    }
    return "";
  }
  
  public static Map b()
  {
    Map localMap = c();
    localMap.putAll(h());
    return localMap;
  }
  
  public static Map c()
  {
    Map localMap = n();
    v.a(localMap, "app_id", r, true);
    return localMap;
  }
  
  public static String d()
  {
    return i("TJC_SERVICE_URL");
  }
  
  public static void d(String paramString)
  {
    K = paramString;
  }
  
  public static void e(String paramString)
  {
    y = paramString;
    t.a("TapjoyConnect", "URL parameters: " + b());
    new Thread(new f()).start();
  }
  
  public static String f()
  {
    String str1 = "";
    String str3 = str1;
    String str4;
    for (;;)
    {
      try
      {
        ConnectivityManager localConnectivityManager = (ConnectivityManager)a.getSystemService("connectivity");
        str4 = str1;
        if (localConnectivityManager == null) {
          break;
        }
        str3 = str1;
        str4 = str1;
        if (localConnectivityManager.getActiveNetworkInfo() == null) {
          break;
        }
        str3 = str1;
        switch (localConnectivityManager.getActiveNetworkInfo().getType())
        {
        case 1: 
          str3 = str1;
          t.a("TapjoyConnect", "connectivity: " + localConnectivityManager.getActiveNetworkInfo().getType());
          str3 = str1;
          t.a("TapjoyConnect", "connection_type: " + str1);
          str4 = str1;
        }
      }
      catch (Exception localException)
      {
        t.b("TapjoyConnect", "getConnectionType error: " + localException.toString());
        return str3;
      }
      str1 = "wifi";
      continue;
      String str2 = "mobile";
    }
    return str4;
  }
  
  public static void f(String paramString)
  {
    N = paramString;
  }
  
  public static String g()
  {
    try
    {
      Object localObject = (ConnectivityManager)a.getSystemService("connectivity");
      if (localObject != null) {
        localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo().getSubtypeName();
      }
      return "";
    }
    catch (Exception localException1)
    {
      try
      {
        t.a("TapjoyConnect", "connection_sub_type: " + (String)localObject);
        return localObject;
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
      localException1 = localException1;
      localObject = "";
      t.b("TapjoyConnect", "getConnectionSubType error: " + localException1.toString());
      return localObject;
    }
  }
  
  public static void g(String paramString)
  {
    String str = paramString;
    if (!paramString.endsWith("/")) {
      str = paramString + "/";
    }
    ab.put("TJC_SERVICE_URL", str);
  }
  
  public static Map h()
  {
    HashMap localHashMap = new HashMap();
    long l1 = System.currentTimeMillis() / 1000L;
    String str = a(l1);
    v.a(localHashMap, "timestamp", String.valueOf(l1), true);
    v.a(localHashMap, "verifier", str, true);
    return localHashMap;
  }
  
  public static void h(String paramString)
  {
    SharedPreferences.Editor localEditor = a.getSharedPreferences("tapjoyOfflineLog", 0).edit();
    localEditor.remove(paramString);
    localEditor.commit();
  }
  
  public static String i(String paramString)
  {
    String str = "";
    if (ab != null) {
      str = (String)ab.get(paramString);
    }
    paramString = str;
    if (str == null) {
      paramString = "";
    }
    return paramString;
  }
  
  public static Map i()
  {
    return a.getSharedPreferences("tapjoyOfflineLog", 0).getAll();
  }
  
  public static void j()
  {
    new Thread(new g()).start();
  }
  
  private static boolean k(String paramString)
  {
    boolean bool2 = false;
    paramString = v.b(paramString);
    if (paramString != null)
    {
      Object localObject = v.a(paramString.getElementsByTagName("PackageNames"));
      if ((localObject != null) && (((String)localObject).length() > 0))
      {
        Vector localVector = new Vector();
        int i2;
        for (int i1 = 0;; i1 = i2 + 1)
        {
          i2 = ((String)localObject).indexOf(',', i1);
          if (i2 == -1)
          {
            t.a("TapjoyConnect", "parse: " + ((String)localObject).substring(i1).trim());
            localVector.add(((String)localObject).substring(i1).trim());
            ac = "";
            localObject = aa.getInstalledApplications(0).iterator();
            while (((Iterator)localObject).hasNext())
            {
              ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
              if (((localApplicationInfo.flags & 0x1) != 1) && (localVector.contains(localApplicationInfo.packageName)))
              {
                t.a("TapjoyConnect", "MATCH: installed packageName: " + localApplicationInfo.packageName);
                if (ac.length() > 0) {
                  ac += ",";
                }
                ac += localApplicationInfo.packageName;
              }
            }
          }
          t.a("TapjoyConnect", "parse: " + ((String)localObject).substring(i1, i2).trim());
          localVector.add(((String)localObject).substring(i1, i2).trim());
        }
      }
      paramString = v.a(paramString.getElementsByTagName("Success"));
      boolean bool1 = bool2;
      if (paramString != null)
      {
        bool1 = bool2;
        if (paramString.equals("true")) {
          bool1 = true;
        }
      }
      return bool1;
    }
    return true;
  }
  
  private boolean l(String paramString)
  {
    return aa.checkPermission(paramString, a.getPackageName()) == 0;
  }
  
  private static Map n()
  {
    HashMap localHashMap = new HashMap();
    if ((i("sha_2_udid") != null) && (i("sha_2_udid").equals("true")))
    {
      v.a(localHashMap, "sha2_udid", j, true);
      v.a(localHashMap, "publisher_user_id", y, true);
      v.a(localHashMap, "android_id", g, true);
      v.a(localHashMap, "mac_address", k, true);
      v.a(localHashMap, "device_name", l, true);
      v.a(localHashMap, "device_type", n, true);
      v.a(localHashMap, "os_version", o, true);
      v.a(localHashMap, "country_code", p, true);
      v.a(localHashMap, "language_code", q, true);
      v.a(localHashMap, "app_version", s, true);
      v.a(localHashMap, "library_version", t, true);
      v.a(localHashMap, "bridge_version", u, true);
      v.a(localHashMap, "platform", A, true);
      v.a(localHashMap, "display_multiplier", Float.toString(O), true);
      v.a(localHashMap, "carrier_name", B, true);
      v.a(localHashMap, "carrier_country_code", C, true);
      v.a(localHashMap, "mobile_country_code", D, true);
      v.a(localHashMap, "mobile_network_code", E, true);
      v.a(localHashMap, "device_manufacturer", m, true);
      v.a(localHashMap, "screen_density", "" + v, true);
      v.a(localHashMap, "screen_layout_size", "" + x, true);
      F = f();
      v.a(localHashMap, "connection_type", F, true);
      G = g();
      v.a(localHashMap, "connection_subtype", G, true);
      v.a(localHashMap, "plugin", J, true);
      v.a(localHashMap, "sdk_type", K, true);
      v.a(localHashMap, "store_name", H, true);
      v.a(localHashMap, "device_location", String.valueOf(z), true);
      v.a(localHashMap, "share_facebook", String.valueOf(P), true);
      v.a(localHashMap, "share_twitter", String.valueOf(Q), true);
      v.a(localHashMap, "share_googleplus", String.valueOf(R), true);
      v.a(localHashMap, "share_linkedin", String.valueOf(S), true);
      v.a(localHashMap, "store_view", String.valueOf(T), true);
      if ((h != null) && (h.length() != 0) && (System.currentTimeMillis() - Y <= 1800000L)) {
        break label515;
      }
      h = C();
    }
    for (;;)
    {
      v.a(localHashMap, "session_id", h, true);
      return localHashMap;
      v.a(localHashMap, "udid", i, true);
      break;
      label515:
      Y = System.currentTimeMillis();
    }
  }
  
  private boolean o()
  {
    aa = a.getPackageManager();
    z();
    w();
    q();
    if (i("TJC_SERVICE_URL") == "") {
      g("https://ws.tapjoyads.com/");
    }
    if ((i("debug_host_url") != null) && (i("debug_host_url").length() > 0)) {
      g(i("debug_host_url"));
    }
    Object localObject1 = i("TJC_SERVICE_URL");
    L = ((String)localObject1).substring(((String)localObject1).indexOf("//") + "//".length(), ((String)localObject1).lastIndexOf("/"));
    Object localObject2 = new StringBuilder().append("deviceID: ").append(i);
    if ((i("debug_device_id") != null) && (i("debug_device_id").length() > 0)) {}
    for (localObject1 = " *debug_device_id*";; localObject1 = "")
    {
      t.a("TapjoyConnect", (String)localObject1);
      t.a("TapjoyConnect", "sha2_udid: " + j);
      localObject1 = c().entrySet().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Map.Entry)((Iterator)localObject1).next();
        t.a("TapjoyConnect", (String)((Map.Entry)localObject2).getKey() + ": " + (String)((Map.Entry)localObject2).getValue());
      }
    }
    if (ab != null) {
      p();
    }
    return true;
  }
  
  private void p()
  {
    t.a("TapjoyConnect", "Connect Flags:");
    t.a("TapjoyConnect", "--------------------");
    Iterator localIterator = ab.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      t.a("TapjoyConnect", "key: " + (String)localEntry.getKey() + ", value: " + Uri.encode((String)localEntry.getValue()));
      if ((((String)localEntry.getKey()).equals("sha_2_udid")) && (!K.equals("connect")))
      {
        t.c("TapjoyConnect", "WARNING -- only the Connect/Advertiser SDK can support sha_2_udid");
        ab.remove("sha_2_udid");
      }
    }
    t.a("TapjoyConnect", "hostURL: [" + i("TJC_SERVICE_URL") + "]");
    t.a("TapjoyConnect", "redirectDomain: [" + L + "]");
    t.a("TapjoyConnect", "--------------------");
  }
  
  private void q()
  {
    g = Settings.Secure.getString(a.getContentResolver(), "android_id");
    if (g != null) {
      g = g.toLowerCase();
    }
    try
    {
      s = aa.getPackageInfo(a.getPackageName(), 0).versionName;
      n = "android";
      A = "android";
      l = Build.MODEL;
      m = Build.MANUFACTURER;
      o = Build.VERSION.RELEASE;
      p = Locale.getDefault().getCountry();
      q = Locale.getDefault().getLanguage();
      t = "9.1.1";
      u = "1.0.4";
      r();
      s();
      t();
      u();
      v();
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      throw new p(localNameNotFoundException.getMessage());
    }
  }
  
  private void r()
  {
    try
    {
      if (Build.VERSION.SDK_INT > 3)
      {
        n localN = new n(a);
        v = localN.a();
        w = localN.b();
        x = localN.c();
      }
      return;
    }
    catch (Exception localException)
    {
      t.b("TapjoyConnect", "Error getting screen density/dimensions/layout: " + localException.toString());
    }
  }
  
  private void s()
  {
    if (l("android.permission.ACCESS_WIFI_STATE")) {
      try
      {
        Object localObject = (WifiManager)a.getSystemService("wifi");
        if (localObject != null)
        {
          localObject = ((WifiManager)localObject).getConnectionInfo();
          if (localObject != null)
          {
            k = ((WifiInfo)localObject).getMacAddress();
            if (k != null) {
              k = k.replace(":", "").toLowerCase();
            }
          }
        }
        return;
      }
      catch (Exception localException)
      {
        t.b("TapjoyConnect", "Error getting device mac address: " + localException.toString());
        return;
      }
    }
    t.a("TapjoyConnect", "*** ignore macAddress");
  }
  
  private void t()
  {
    Object localObject = (TelephonyManager)a.getSystemService("phone");
    if (localObject != null)
    {
      B = ((TelephonyManager)localObject).getNetworkOperatorName();
      C = ((TelephonyManager)localObject).getNetworkCountryIso();
      if ((((TelephonyManager)localObject).getNetworkOperator() != null) && ((((TelephonyManager)localObject).getNetworkOperator().length() == 5) || (((TelephonyManager)localObject).getNetworkOperator().length() == 6)))
      {
        D = ((TelephonyManager)localObject).getNetworkOperator().substring(0, 3);
        E = ((TelephonyManager)localObject).getNetworkOperator().substring(3);
      }
      if (l("android.permission.READ_PHONE_STATE")) {
        for (;;)
        {
          int i1;
          int i2;
          try
          {
            if ((i("debug_device_id") != null) && (i("debug_device_id").length() > 0))
            {
              i = i("debug_device_id");
              t.a("TapjoyConnect", "deviceID: " + i);
              if (i == null)
              {
                t.b("TapjoyConnect", "Device id is null.");
                i1 = 0;
                t.a("TapjoyConnect", "ANDROID SDK VERSION: " + Build.VERSION.SDK_INT);
                i2 = i1;
                if (Build.VERSION.SDK_INT >= 9)
                {
                  t.a("TapjoyConnect", "TRYING TO GET SERIAL OF 2.3+ DEVICE...");
                  localObject = e();
                  if (i1 == 0) {
                    i = (String)localObject;
                  }
                  if (i != null) {
                    break label362;
                  }
                  t.b("TapjoyConnect", "SERIAL: Device id is null.");
                  i2 = i1;
                }
                if (i2 == 0) {
                  break;
                }
                j = v.a(i);
              }
            }
            else
            {
              i = ((TelephonyManager)localObject).getDeviceId();
              continue;
            }
            if (i.length() == 0) {
              break label332;
            }
          }
          catch (Exception localException)
          {
            t.b("TapjoyConnect", "Cannot get deviceID. e: " + localException.toString());
            i = null;
            return;
          }
          if ((i.equals("000000000000000")) || (i.equals("0")))
          {
            label332:
            t.b("TapjoyConnect", "Device id is empty or an emulator.");
            i1 = 0;
          }
          else
          {
            i = i.toLowerCase(Locale.getDefault());
            i1 = 1;
            continue;
            label362:
            if ((i.length() == 0) || (i.equals("000000000000000")) || (i.equals("0")) || (i.equals("unknown")))
            {
              t.b("TapjoyConnect", "SERIAL: Device id is empty or an emulator.");
              i2 = i1;
            }
            else
            {
              i = i.toLowerCase(Locale.getDefault());
              i2 = 1;
            }
          }
        }
      }
      t.a("TapjoyConnect", "*** ignore deviceID");
    }
  }
  
  private void u()
  {
    if ((i != null) && (i.length() > 0))
    {
      y = i;
      return;
    }
    if ((k != null) && (k.length() > 0))
    {
      y = k;
      return;
    }
    if ((g != null) && (g.length() > 0))
    {
      y = g;
      return;
    }
    throw new p("ERROR -- No valid device identifier");
  }
  
  private void v()
  {
    try
    {
      z = a("android.hardware.location", "android.permission.ACCESS_FINE_LOCATION");
    }
    catch (Exception localException1)
    {
      try
      {
        for (;;)
        {
          P = b("com.facebook.");
          Q = b("com.twitter.");
          R = b("com.google.android.apps.plus");
          S = b("com.linkedin.");
          if ((i("store_name") != null) && (i("store_name").length() > 0))
          {
            H = i("store_name");
            if (!new ArrayList(Arrays.asList(i.b)).contains(H)) {
              t.c("TapjoyConnect", "Warning -- undefined STORE_NAME: " + H);
            }
          }
          try
          {
            T = c(H);
            return;
          }
          catch (Exception localException3)
          {
            t.b("TapjoyConnect", "Error trying to detect store intent on devicee: " + localException3.toString());
          }
          localException1 = localException1;
          t.b("TapjoyConnect", "Error trying to detect capabilities on devicee: " + localException1.toString());
        }
      }
      catch (Exception localException2)
      {
        for (;;)
        {
          t.b("TapjoyConnect", "Error trying to detect sharing applications installed on devicee: " + localException2.toString());
        }
      }
    }
  }
  
  private void w()
  {
    if (ab == null) {
      ab = new Hashtable();
    }
    if ((i("enable_logging") != null) && (i("enable_logging").equals("true"))) {
      t.a(true);
    }
    x();
    y();
  }
  
  private void x()
  {
    for (;;)
    {
      int i1;
      try
      {
        if (aa == null) {
          break;
        }
        ApplicationInfo localApplicationInfo = aa.getApplicationInfo(a.getPackageName(), 128);
        if ((localApplicationInfo != null) && (localApplicationInfo.metaData != null))
        {
          String[] arrayOfString = i.a;
          int i2 = arrayOfString.length;
          i1 = 0;
          if (i1 < i2)
          {
            String str1 = arrayOfString[i1];
            String str2 = localApplicationInfo.metaData.getString("tapjoy." + str1);
            if (str2 != null)
            {
              t.a("TapjoyConnect", "Found manifest flag: " + str1 + ", " + str2);
              ab.put(str1, str2);
            }
          }
          else
          {
            t.a("TapjoyConnect", "Metadata successfully loaded");
          }
        }
        else
        {
          t.a("TapjoyConnect", "No metadata present.");
          return;
        }
      }
      catch (Exception localException)
      {
        t.b("TapjoyConnect", "Error reading manifest meta-data: " + localException.toString());
        return;
      }
      i1 += 1;
    }
  }
  
  private void y()
  {
    int i1 = a.getResources().getIdentifier("raw/tapjoy_config", null, a.getPackageName());
    Properties localProperties = new Properties();
    try
    {
      localProperties.load(a.getResources().openRawResource(i1));
      a(localProperties);
      return;
    }
    catch (Exception localException)
    {
      t.a("TapjoyConnect", "No raw/tapjoy_config file present.");
    }
  }
  
  private void z()
  {
    try
    {
      Object localObject = Arrays.asList(aa.getPackageInfo(a.getPackageName(), 1).activities);
      if (localObject != null)
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext()) {
          a((ActivityInfo)((Iterator)localObject).next());
        }
      }
      if (f.size() == 0) {
        break label183;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      throw new s("NameNotFoundException: Could not find package.");
    }
    if (f.size() == 1) {
      throw new s("Missing " + f.size() + " dependency class in manifest: " + f.toString());
    }
    throw new s("Missing " + f.size() + " dependency classes in manifest: " + f.toString());
    label183:
    A();
    B();
  }
  
  public void a()
  {
    new Thread(new h(this)).start();
  }
  
  protected boolean a(String paramString)
  {
    Iterator localIterator = aa.getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.startsWith(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  protected boolean a(String paramString1, String paramString2)
  {
    FeatureInfo[] arrayOfFeatureInfo = aa.getSystemAvailableFeatures();
    int i2 = arrayOfFeatureInfo.length;
    int i1 = 0;
    while (i1 < i2)
    {
      if (arrayOfFeatureInfo[i1].name.matches(paramString1))
      {
        if (paramString2 == null) {}
        while (aa.checkPermission(paramString2, a.getPackageName()) == 0) {
          return true;
        }
        return false;
      }
      i1 += 1;
    }
    return false;
  }
  
  protected boolean b(String paramString)
  {
    Object localObject = new Intent("android.intent.action.SEND");
    ((Intent)localObject).setType("text/plain");
    localObject = aa.queryIntentActivities((Intent)localObject, 0).iterator();
    while (((Iterator)localObject).hasNext()) {
      if (((ResolveInfo)((Iterator)localObject).next()).activityInfo.packageName.startsWith(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  protected boolean c(String paramString)
  {
    boolean bool = true;
    Intent localIntent = new Intent("android.intent.action.VIEW");
    if (paramString.length() < 1)
    {
      localIntent.setData(Uri.parse("market://details"));
      if (aa.queryIntentActivities(localIntent, 0).size() <= 0) {
        break label96;
      }
    }
    for (;;)
    {
      if (bool) {}
      return bool;
      if (paramString.equals("gfan")) {
        bool = a("com.mappn.gfan");
      } else if (paramString.equals("skt")) {
        bool = a("com.skt.skaf.TSCINSTALL");
      } else {
        label96:
        bool = false;
      }
    }
  }
  
  /* Error */
  public String e()
  {
    // Byte code:
    //   0: ldc_w 1110
    //   3: invokestatic 305	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   6: ldc_w 1112
    //   9: invokevirtual 1116	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   12: astore_1
    //   13: aload_1
    //   14: invokevirtual 1121	java/lang/reflect/Field:isAccessible	()Z
    //   17: ifne +8 -> 25
    //   20: aload_1
    //   21: iconst_1
    //   22: invokevirtual 1124	java/lang/reflect/Field:setAccessible	(Z)V
    //   25: aload_1
    //   26: ldc_w 815
    //   29: invokevirtual 1125	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   32: invokevirtual 1126	java/lang/Object:toString	()Ljava/lang/String;
    //   35: astore_1
    //   36: ldc -44
    //   38: new 214	java/lang/StringBuilder
    //   41: dup
    //   42: invokespecial 215	java/lang/StringBuilder:<init>	()V
    //   45: ldc_w 1128
    //   48: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: aload_1
    //   52: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   58: invokestatic 236	com/tapjoy/t:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   61: aload_1
    //   62: areturn
    //   63: astore_2
    //   64: aconst_null
    //   65: astore_1
    //   66: ldc -44
    //   68: aload_2
    //   69: invokevirtual 337	java/lang/Exception:toString	()Ljava/lang/String;
    //   72: invokestatic 339	com/tapjoy/t:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   75: aload_1
    //   76: areturn
    //   77: astore_2
    //   78: goto -12 -> 66
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	81	0	this	e
    //   12	64	1	localObject	Object
    //   63	6	2	localException1	Exception
    //   77	1	2	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   0	25	63	java/lang/Exception
    //   25	36	63	java/lang/Exception
    //   36	61	77	java/lang/Exception
  }
}
