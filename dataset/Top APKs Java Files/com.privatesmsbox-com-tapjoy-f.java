package com.tapjoy;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.Vector;
import org.w3c.dom.Document;

public class f
{
  private static String A = "";
  private static String B = "";
  private static String C = "";
  private static String D = "";
  private static String E = "";
  private static String F = "native";
  private static String G = "";
  private static boolean H = false;
  private static String I = "";
  private static float J = 1.0F;
  private static String K = null;
  private static Hashtable N = null;
  private static String O = "";
  private static Context a = null;
  private static f b = null;
  private static z c = null;
  private static String d = "";
  private static String e = "";
  private static String f = "";
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
  private static String v = "";
  private static String w = "";
  private static String x = "";
  private static String y = "";
  private static String z = "";
  private long L = 0L;
  private Timer M = null;
  
  public f(Context paramContext)
  {
    a = paramContext;
    c = new z();
    l();
    u.a("TapjoyConnect", "URL parameters: " + c());
    new Thread(new g(this)).start();
  }
  
  public static f a()
  {
    return b;
  }
  
  public static String a(long paramLong)
  {
    try
    {
      String str = aa.b(p + ":" + e + ":" + paramLong + ":" + C);
      return str;
    }
    catch (Exception localException)
    {
      u.b("TapjoyConnect", "getVerifier ERROR: " + localException.toString());
    }
    return "";
  }
  
  public static String a(long paramLong, String paramString)
  {
    try
    {
      paramString = aa.b(p + ":" + e + ":" + paramLong + ":" + C + ":" + paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      u.b("TapjoyConnect", "getVerifier ERROR: " + paramString.toString());
    }
    return "";
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, Hashtable paramHashtable)
  {
    p = paramString1;
    C = paramString2;
    N = paramHashtable;
    b = new f(paramContext);
  }
  
  public static void a(String paramString)
  {
    F = paramString;
  }
  
  public static void a(boolean paramBoolean)
  {
    H = paramBoolean;
  }
  
  public static void b(String paramString)
  {
    G = paramString;
  }
  
  public static String c()
  {
    String str2 = d() + "&";
    long l1 = System.currentTimeMillis() / 1000L;
    String str1 = a(l1);
    str2 = str2 + "timestamp=" + l1 + "&";
    return str2 + "verifier=" + str1;
  }
  
  public static void c(String paramString)
  {
    I = paramString;
  }
  
  public static String d()
  {
    String str = "" + "app_id=" + Uri.encode(p) + "&";
    return str + k();
  }
  
  public static String e()
  {
    return u;
  }
  
  public static String e(String paramString)
  {
    if (N != null) {
      return (String)N.get(paramString);
    }
    return "";
  }
  
  public static String f()
  {
    String str = "";
    if (H) {
      if (I.length() <= 0) {}
    }
    for (str = "video_offer_ids=" + I;; str = "hide_videos=true")
    {
      u.a("TapjoyConnect", "video parameters: " + str);
      return str;
    }
  }
  
  public static String g()
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
          u.a("TapjoyConnect", "connectivity: " + localConnectivityManager.getActiveNetworkInfo().getType());
          str3 = str1;
          u.a("TapjoyConnect", "connection_type: " + str1);
          str4 = str1;
        }
      }
      catch (Exception localException)
      {
        u.b("TapjoyConnect", "getConnectionType error: " + localException.toString());
        return str3;
      }
      str1 = "wifi";
      continue;
      String str2 = "mobile";
    }
    return str4;
  }
  
  private static boolean g(String paramString)
  {
    paramString = aa.c(paramString);
    if (paramString != null)
    {
      Object localObject = aa.a(paramString.getElementsByTagName("PackageNames"));
      if ((localObject != null) && (((String)localObject).length() > 0))
      {
        Vector localVector = new Vector();
        int i2;
        for (int i1 = 0;; i1 = i2 + 1)
        {
          i2 = ((String)localObject).indexOf(',', i1);
          if (i2 == -1)
          {
            u.a("TapjoyConnect", "parse: " + ((String)localObject).substring(i1).trim());
            localVector.add(((String)localObject).substring(i1).trim());
            O = "";
            localObject = a.getPackageManager().getInstalledApplications(0).iterator();
            while (((Iterator)localObject).hasNext())
            {
              ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
              if (((localApplicationInfo.flags & 0x1) != 1) && (localVector.contains(localApplicationInfo.packageName)))
              {
                u.a("TapjoyConnect", "MATCH: installed packageName: " + localApplicationInfo.packageName);
                if (O.length() > 0) {
                  O += ",";
                }
                O += localApplicationInfo.packageName;
              }
            }
          }
          u.a("TapjoyConnect", "parse: " + ((String)localObject).substring(i1, i2).trim());
          localVector.add(((String)localObject).substring(i1, i2).trim());
        }
      }
      paramString = aa.a(paramString.getElementsByTagName("Success"));
      if ((paramString == null) || (!paramString.equals("true"))) {}
    }
    return true;
  }
  
  public static String h()
  {
    return D;
  }
  
  private boolean h(String paramString)
  {
    paramString = aa.c(paramString);
    if (paramString != null)
    {
      paramString = aa.a(paramString.getElementsByTagName("Success"));
      if ((paramString != null) && (paramString.equals("true")))
      {
        u.a("TapjoyConnect", "Successfully sent completed Pay-Per-Action to Tapjoy server.");
        return true;
      }
      u.b("TapjoyConnect", "Completed Pay-Per-Action call failed.");
    }
    return false;
  }
  
  private static String k()
  {
    Object localObject1 = "" + "android_id=" + d + "&";
    if ((e("sha_2_udid") != null) && (e("sha_2_udid").equals("true"))) {}
    for (Object localObject2 = (String)localObject1 + "sha2_udid=" + Uri.encode(f) + "&";; localObject2 = (String)localObject1 + "udid=" + Uri.encode(e) + "&")
    {
      localObject1 = localObject2;
      if (g != null)
      {
        localObject1 = localObject2;
        if (g.length() > 0) {
          localObject1 = (String)localObject2 + "sha1_mac_address=" + Uri.encode(h) + "&";
        }
      }
      localObject2 = localObject1;
      if (i != null)
      {
        localObject2 = localObject1;
        if (i.length() > 0) {
          localObject2 = (String)localObject1 + "serial_id=" + Uri.encode(i) + "&";
        }
      }
      localObject1 = (String)localObject2 + "device_name=" + Uri.encode(j) + "&";
      localObject1 = (String)localObject1 + "device_manufacturer=" + Uri.encode(k) + "&";
      localObject1 = (String)localObject1 + "device_type=" + Uri.encode(l) + "&";
      localObject1 = (String)localObject1 + "os_version=" + Uri.encode(m) + "&";
      localObject1 = (String)localObject1 + "country_code=" + Uri.encode(n) + "&";
      localObject1 = (String)localObject1 + "language_code=" + Uri.encode(o) + "&";
      localObject1 = (String)localObject1 + "app_version=" + Uri.encode(q) + "&";
      localObject1 = (String)localObject1 + "library_version=" + Uri.encode(r) + "&";
      localObject1 = (String)localObject1 + "platform=" + Uri.encode(v) + "&";
      localObject2 = (String)localObject1 + "display_multiplier=" + Uri.encode(Float.toString(J));
      localObject1 = localObject2;
      if (w.length() > 0)
      {
        localObject1 = (String)localObject2 + "&";
        localObject1 = (String)localObject1 + "carrier_name=" + Uri.encode(w);
      }
      localObject2 = localObject1;
      if (x.length() > 0)
      {
        localObject1 = (String)localObject1 + "&";
        localObject2 = (String)localObject1 + "carrier_country_code=" + Uri.encode(x);
      }
      localObject1 = localObject2;
      if (y.length() > 0)
      {
        localObject1 = (String)localObject2 + "&";
        localObject1 = (String)localObject1 + "mobile_country_code=" + Uri.encode(y);
      }
      localObject2 = localObject1;
      if (z.length() > 0)
      {
        localObject1 = (String)localObject1 + "&";
        localObject2 = (String)localObject1 + "mobile_network_code=" + Uri.encode(z);
      }
      localObject1 = localObject2;
      if (s.length() > 0)
      {
        localObject1 = localObject2;
        if (t.length() > 0)
        {
          localObject1 = (String)localObject2 + "&";
          localObject1 = (String)localObject1 + "screen_density=" + Uri.encode(s) + "&";
          localObject1 = (String)localObject1 + "screen_layout_size=" + Uri.encode(t);
        }
      }
      A = g();
      localObject2 = localObject1;
      if (A.length() > 0)
      {
        localObject1 = (String)localObject1 + "&";
        localObject2 = (String)localObject1 + "connection_type=" + Uri.encode(A);
      }
      localObject1 = localObject2;
      if (F.length() > 0)
      {
        localObject1 = (String)localObject2 + "&";
        localObject1 = (String)localObject1 + "plugin=" + Uri.encode(F);
      }
      localObject2 = localObject1;
      if (G.length() > 0)
      {
        localObject1 = (String)localObject1 + "&";
        localObject2 = (String)localObject1 + "sdk_type=" + Uri.encode(G);
      }
      localObject1 = localObject2;
      if (B.length() > 0)
      {
        localObject1 = (String)localObject2 + "&";
        localObject1 = (String)localObject1 + "store_name=" + Uri.encode(B);
      }
      return localObject1;
    }
  }
  
  /* Error */
  private void l()
  {
    // Byte code:
    //   0: getstatic 56	com/tapjoy/f:a	Landroid/content/Context;
    //   3: invokevirtual 331	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   6: astore_3
    //   7: getstatic 134	com/tapjoy/f:N	Ljava/util/Hashtable;
    //   10: ifnonnull +13 -> 23
    //   13: new 243	java/util/Hashtable
    //   16: dup
    //   17: invokespecial 445	java/util/Hashtable:<init>	()V
    //   20: putstatic 134	com/tapjoy/f:N	Ljava/util/Hashtable;
    //   23: aload_3
    //   24: ifnull +160 -> 184
    //   27: aload_3
    //   28: getstatic 56	com/tapjoy/f:a	Landroid/content/Context;
    //   31: invokevirtual 448	android/content/Context:getPackageName	()Ljava/lang/String;
    //   34: sipush 128
    //   37: invokevirtual 452	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   40: astore 4
    //   42: aload 4
    //   44: ifnull +1809 -> 1853
    //   47: aload 4
    //   49: getfield 456	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   52: ifnull +1801 -> 1853
    //   55: getstatic 461	com/tapjoy/i:a	[Ljava/lang/String;
    //   58: astore 5
    //   60: aload 5
    //   62: arraylength
    //   63: istore_2
    //   64: iconst_0
    //   65: istore_1
    //   66: iload_1
    //   67: iload_2
    //   68: if_icmpge +108 -> 176
    //   71: aload 5
    //   73: iload_1
    //   74: aaload
    //   75: astore 6
    //   77: aload 4
    //   79: getfield 456	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   82: new 154	java/lang/StringBuilder
    //   85: dup
    //   86: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   89: ldc_w 463
    //   92: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: aload 6
    //   97: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   103: invokevirtual 467	android/os/Bundle:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   106: astore 7
    //   108: aload 7
    //   110: ifnull +2106 -> 2216
    //   113: aload 7
    //   115: invokevirtual 468	java/lang/Object:toString	()Ljava/lang/String;
    //   118: astore 7
    //   120: aload 7
    //   122: ifnull +2094 -> 2216
    //   125: ldc -104
    //   127: new 154	java/lang/StringBuilder
    //   130: dup
    //   131: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   134: ldc_w 470
    //   137: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: aload 6
    //   142: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: ldc_w 472
    //   148: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: aload 7
    //   153: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   159: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   162: getstatic 134	com/tapjoy/f:N	Ljava/util/Hashtable;
    //   165: aload 6
    //   167: aload 7
    //   169: invokevirtual 476	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   172: pop
    //   173: goto +2043 -> 2216
    //   176: ldc -104
    //   178: ldc_w 478
    //   181: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   184: getstatic 56	com/tapjoy/f:a	Landroid/content/Context;
    //   187: invokevirtual 482	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   190: ldc_w 484
    //   193: invokestatic 490	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   196: putstatic 64	com/tapjoy/f:d	Ljava/lang/String;
    //   199: aload_3
    //   200: getstatic 56	com/tapjoy/f:a	Landroid/content/Context;
    //   203: invokevirtual 448	android/content/Context:getPackageName	()Ljava/lang/String;
    //   206: iconst_0
    //   207: invokevirtual 494	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   210: getfield 499	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   213: putstatic 90	com/tapjoy/f:q	Ljava/lang/String;
    //   216: ldc_w 501
    //   219: putstatic 80	com/tapjoy/f:l	Ljava/lang/String;
    //   222: ldc_w 501
    //   225: putstatic 100	com/tapjoy/f:v	Ljava/lang/String;
    //   228: getstatic 506	android/os/Build:MODEL	Ljava/lang/String;
    //   231: putstatic 76	com/tapjoy/f:j	Ljava/lang/String;
    //   234: getstatic 509	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   237: putstatic 78	com/tapjoy/f:k	Ljava/lang/String;
    //   240: getstatic 514	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   243: putstatic 82	com/tapjoy/f:m	Ljava/lang/String;
    //   246: invokestatic 520	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   249: invokevirtual 523	java/util/Locale:getCountry	()Ljava/lang/String;
    //   252: putstatic 84	com/tapjoy/f:n	Ljava/lang/String;
    //   255: invokestatic 520	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   258: invokevirtual 526	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   261: putstatic 86	com/tapjoy/f:o	Ljava/lang/String;
    //   264: ldc_w 528
    //   267: putstatic 92	com/tapjoy/f:r	Ljava/lang/String;
    //   270: getstatic 56	com/tapjoy/f:a	Landroid/content/Context;
    //   273: ldc_w 530
    //   276: iconst_0
    //   277: invokevirtual 534	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   280: astore_3
    //   281: getstatic 56	com/tapjoy/f:a	Landroid/content/Context;
    //   284: ldc_w 536
    //   287: invokevirtual 269	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   290: checkcast 538	android/telephony/TelephonyManager
    //   293: astore 4
    //   295: aload 4
    //   297: ifnull +85 -> 382
    //   300: aload 4
    //   302: invokevirtual 541	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   305: putstatic 66	com/tapjoy/f:e	Ljava/lang/String;
    //   308: aload 4
    //   310: invokevirtual 544	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   313: putstatic 102	com/tapjoy/f:w	Ljava/lang/String;
    //   316: aload 4
    //   318: invokevirtual 547	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   321: putstatic 104	com/tapjoy/f:x	Ljava/lang/String;
    //   324: aload 4
    //   326: invokevirtual 550	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   329: ifnull +53 -> 382
    //   332: aload 4
    //   334: invokevirtual 550	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   337: invokevirtual 253	java/lang/String:length	()I
    //   340: iconst_5
    //   341: if_icmpeq +16 -> 357
    //   344: aload 4
    //   346: invokevirtual 550	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   349: invokevirtual 253	java/lang/String:length	()I
    //   352: bipush 6
    //   354: if_icmpne +28 -> 382
    //   357: aload 4
    //   359: invokevirtual 550	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   362: iconst_0
    //   363: iconst_3
    //   364: invokevirtual 371	java/lang/String:substring	(II)Ljava/lang/String;
    //   367: putstatic 106	com/tapjoy/f:y	Ljava/lang/String;
    //   370: aload 4
    //   372: invokevirtual 550	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   375: iconst_3
    //   376: invokevirtual 320	java/lang/String:substring	(I)Ljava/lang/String;
    //   379: putstatic 108	com/tapjoy/f:z	Ljava/lang/String;
    //   382: ldc -104
    //   384: new 154	java/lang/StringBuilder
    //   387: dup
    //   388: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   391: ldc_w 552
    //   394: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   397: getstatic 66	com/tapjoy/f:e	Ljava/lang/String;
    //   400: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   403: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   406: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   409: getstatic 66	com/tapjoy/f:e	Ljava/lang/String;
    //   412: ifnonnull +1486 -> 1898
    //   415: ldc -104
    //   417: ldc_w 554
    //   420: invokestatic 204	com/tapjoy/u:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   423: iconst_1
    //   424: istore_1
    //   425: ldc -104
    //   427: new 154	java/lang/StringBuilder
    //   430: dup
    //   431: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   434: ldc_w 556
    //   437: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   440: getstatic 559	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   443: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   446: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   449: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   452: iload_1
    //   453: istore_2
    //   454: getstatic 559	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   457: invokestatic 565	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   460: bipush 9
    //   462: if_icmplt +99 -> 561
    //   465: ldc -104
    //   467: ldc_w 567
    //   470: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   473: new 569	com/tapjoy/s
    //   476: dup
    //   477: invokespecial 570	com/tapjoy/s:<init>	()V
    //   480: invokevirtual 572	com/tapjoy/s:a	()Ljava/lang/String;
    //   483: putstatic 74	com/tapjoy/f:i	Ljava/lang/String;
    //   486: iload_1
    //   487: ifeq +9 -> 496
    //   490: getstatic 74	com/tapjoy/f:i	Ljava/lang/String;
    //   493: putstatic 66	com/tapjoy/f:e	Ljava/lang/String;
    //   496: ldc -104
    //   498: ldc_w 574
    //   501: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   504: ldc -104
    //   506: new 154	java/lang/StringBuilder
    //   509: dup
    //   510: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   513: ldc_w 576
    //   516: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   519: getstatic 66	com/tapjoy/f:e	Ljava/lang/String;
    //   522: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   525: ldc_w 578
    //   528: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   531: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   534: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   537: ldc -104
    //   539: ldc_w 574
    //   542: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   545: getstatic 66	com/tapjoy/f:e	Ljava/lang/String;
    //   548: ifnonnull +1410 -> 1958
    //   551: ldc -104
    //   553: ldc_w 580
    //   556: invokestatic 204	com/tapjoy/u:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   559: iconst_1
    //   560: istore_2
    //   561: iload_2
    //   562: ifeq +53 -> 615
    //   565: new 582	java/lang/StringBuffer
    //   568: dup
    //   569: invokespecial 583	java/lang/StringBuffer:<init>	()V
    //   572: astore 4
    //   574: aload 4
    //   576: ldc_w 585
    //   579: invokevirtual 588	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   582: pop
    //   583: aload_3
    //   584: ldc_w 590
    //   587: aconst_null
    //   588: invokeinterface 595 3 0
    //   593: astore 5
    //   595: aload 5
    //   597: ifnull +1626 -> 2223
    //   600: aload 5
    //   602: ldc 62
    //   604: invokevirtual 378	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   607: ifne +1616 -> 2223
    //   610: aload 5
    //   612: putstatic 66	com/tapjoy/f:e	Ljava/lang/String;
    //   615: getstatic 98	com/tapjoy/f:u	Ljava/lang/String;
    //   618: invokevirtual 253	java/lang/String:length	()I
    //   621: ifne +9 -> 630
    //   624: getstatic 66	com/tapjoy/f:e	Ljava/lang/String;
    //   627: putstatic 98	com/tapjoy/f:u	Ljava/lang/String;
    //   630: getstatic 66	com/tapjoy/f:e	Ljava/lang/String;
    //   633: invokestatic 199	com/tapjoy/aa:b	(Ljava/lang/String;)Ljava/lang/String;
    //   636: putstatic 68	com/tapjoy/f:f	Ljava/lang/String;
    //   639: getstatic 559	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   642: invokestatic 565	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   645: iconst_3
    //   646: if_icmple +67 -> 713
    //   649: new 597	com/tapjoy/l
    //   652: dup
    //   653: getstatic 56	com/tapjoy/f:a	Landroid/content/Context;
    //   656: invokespecial 598	com/tapjoy/l:<init>	(Landroid/content/Context;)V
    //   659: astore 4
    //   661: new 154	java/lang/StringBuilder
    //   664: dup
    //   665: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   668: ldc 62
    //   670: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   673: aload 4
    //   675: invokevirtual 600	com/tapjoy/l:a	()I
    //   678: invokevirtual 285	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   681: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   684: putstatic 94	com/tapjoy/f:s	Ljava/lang/String;
    //   687: new 154	java/lang/StringBuilder
    //   690: dup
    //   691: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   694: ldc 62
    //   696: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   699: aload 4
    //   701: invokevirtual 602	com/tapjoy/l:b	()I
    //   704: invokevirtual 285	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   707: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   710: putstatic 96	com/tapjoy/f:t	Ljava/lang/String;
    //   713: getstatic 56	com/tapjoy/f:a	Landroid/content/Context;
    //   716: ldc_w 289
    //   719: invokevirtual 269	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   722: checkcast 604	android/net/wifi/WifiManager
    //   725: astore 4
    //   727: aload 4
    //   729: ifnull +56 -> 785
    //   732: aload 4
    //   734: invokevirtual 608	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   737: astore 4
    //   739: aload 4
    //   741: ifnull +44 -> 785
    //   744: aload 4
    //   746: invokevirtual 613	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   749: putstatic 70	com/tapjoy/f:g	Ljava/lang/String;
    //   752: getstatic 70	com/tapjoy/f:g	Ljava/lang/String;
    //   755: ifnull +30 -> 785
    //   758: getstatic 70	com/tapjoy/f:g	Ljava/lang/String;
    //   761: invokevirtual 253	java/lang/String:length	()I
    //   764: ifle +21 -> 785
    //   767: getstatic 70	com/tapjoy/f:g	Ljava/lang/String;
    //   770: invokevirtual 616	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   773: putstatic 70	com/tapjoy/f:g	Ljava/lang/String;
    //   776: getstatic 70	com/tapjoy/f:g	Ljava/lang/String;
    //   779: invokestatic 618	com/tapjoy/aa:a	(Ljava/lang/String;)Ljava/lang/String;
    //   782: putstatic 72	com/tapjoy/f:h	Ljava/lang/String;
    //   785: ldc_w 620
    //   788: invokestatic 389	com/tapjoy/f:e	(Ljava/lang/String;)Ljava/lang/String;
    //   791: ifnull +74 -> 865
    //   794: ldc_w 620
    //   797: invokestatic 389	com/tapjoy/f:e	(Ljava/lang/String;)Ljava/lang/String;
    //   800: invokevirtual 253	java/lang/String:length	()I
    //   803: ifle +62 -> 865
    //   806: ldc_w 620
    //   809: invokestatic 389	com/tapjoy/f:e	(Ljava/lang/String;)Ljava/lang/String;
    //   812: putstatic 112	com/tapjoy/f:B	Ljava/lang/String;
    //   815: new 622	java/util/ArrayList
    //   818: dup
    //   819: getstatic 624	com/tapjoy/i:b	[Ljava/lang/String;
    //   822: invokestatic 630	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   825: invokespecial 633	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   828: getstatic 112	com/tapjoy/f:B	Ljava/lang/String;
    //   831: invokevirtual 634	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   834: ifne +31 -> 865
    //   837: ldc -104
    //   839: new 154	java/lang/StringBuilder
    //   842: dup
    //   843: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   846: ldc_w 636
    //   849: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   852: getstatic 112	com/tapjoy/f:B	Ljava/lang/String;
    //   855: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   858: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   861: invokestatic 641	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   864: pop
    //   865: aload_3
    //   866: ldc_w 643
    //   869: aconst_null
    //   870: invokeinterface 595 3 0
    //   875: astore_3
    //   876: aload_3
    //   877: ifnull +16 -> 893
    //   880: aload_3
    //   881: ldc 62
    //   883: invokevirtual 378	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   886: ifne +7 -> 893
    //   889: aload_3
    //   890: putstatic 118	com/tapjoy/f:E	Ljava/lang/String;
    //   893: getstatic 56	com/tapjoy/f:a	Landroid/content/Context;
    //   896: invokevirtual 448	android/content/Context:getPackageName	()Ljava/lang/String;
    //   899: putstatic 116	com/tapjoy/f:D	Ljava/lang/String;
    //   902: ldc -104
    //   904: new 154	java/lang/StringBuilder
    //   907: dup
    //   908: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   911: ldc_w 645
    //   914: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   917: getstatic 88	com/tapjoy/f:p	Ljava/lang/String;
    //   920: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   923: ldc_w 578
    //   926: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   929: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   932: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   935: ldc -104
    //   937: new 154	java/lang/StringBuilder
    //   940: dup
    //   941: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   944: ldc_w 647
    //   947: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   950: getstatic 64	com/tapjoy/f:d	Ljava/lang/String;
    //   953: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   956: ldc_w 578
    //   959: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   962: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   965: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   968: ldc -104
    //   970: new 154	java/lang/StringBuilder
    //   973: dup
    //   974: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   977: ldc_w 649
    //   980: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   983: getstatic 116	com/tapjoy/f:D	Ljava/lang/String;
    //   986: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   989: ldc_w 578
    //   992: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   995: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   998: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1001: ldc -104
    //   1003: new 154	java/lang/StringBuilder
    //   1006: dup
    //   1007: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   1010: ldc_w 651
    //   1013: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1016: getstatic 66	com/tapjoy/f:e	Ljava/lang/String;
    //   1019: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1022: ldc_w 578
    //   1025: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1028: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1031: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1034: ldc -104
    //   1036: new 154	java/lang/StringBuilder
    //   1039: dup
    //   1040: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   1043: ldc_w 653
    //   1046: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1049: getstatic 68	com/tapjoy/f:f	Ljava/lang/String;
    //   1052: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1055: ldc_w 578
    //   1058: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1061: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1064: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1067: ldc -104
    //   1069: new 154	java/lang/StringBuilder
    //   1072: dup
    //   1073: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   1076: ldc_w 655
    //   1079: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1082: getstatic 74	com/tapjoy/f:i	Ljava/lang/String;
    //   1085: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1088: ldc_w 578
    //   1091: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1094: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1097: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1100: ldc -104
    //   1102: new 154	java/lang/StringBuilder
    //   1105: dup
    //   1106: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   1109: ldc_w 657
    //   1112: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1115: getstatic 70	com/tapjoy/f:g	Ljava/lang/String;
    //   1118: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1121: ldc_w 578
    //   1124: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1127: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1130: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1133: ldc -104
    //   1135: new 154	java/lang/StringBuilder
    //   1138: dup
    //   1139: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   1142: ldc_w 659
    //   1145: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1148: getstatic 72	com/tapjoy/f:h	Ljava/lang/String;
    //   1151: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1154: ldc_w 578
    //   1157: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1160: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1163: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1166: ldc -104
    //   1168: new 154	java/lang/StringBuilder
    //   1171: dup
    //   1172: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   1175: ldc_w 661
    //   1178: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1181: getstatic 76	com/tapjoy/f:j	Ljava/lang/String;
    //   1184: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1187: ldc_w 578
    //   1190: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1193: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1196: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1199: ldc -104
    //   1201: new 154	java/lang/StringBuilder
    //   1204: dup
    //   1205: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   1208: ldc_w 663
    //   1211: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1214: getstatic 78	com/tapjoy/f:k	Ljava/lang/String;
    //   1217: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1220: ldc_w 578
    //   1223: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1226: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1229: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1232: ldc -104
    //   1234: new 154	java/lang/StringBuilder
    //   1237: dup
    //   1238: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   1241: ldc_w 665
    //   1244: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1247: getstatic 80	com/tapjoy/f:l	Ljava/lang/String;
    //   1250: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1253: ldc_w 578
    //   1256: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1259: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1262: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1265: ldc -104
    //   1267: new 154	java/lang/StringBuilder
    //   1270: dup
    //   1271: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   1274: ldc_w 667
    //   1277: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1280: getstatic 92	com/tapjoy/f:r	Ljava/lang/String;
    //   1283: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1286: ldc_w 578
    //   1289: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1292: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1295: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1298: ldc -104
    //   1300: new 154	java/lang/StringBuilder
    //   1303: dup
    //   1304: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   1307: ldc_w 669
    //   1310: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1313: getstatic 82	com/tapjoy/f:m	Ljava/lang/String;
    //   1316: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1319: ldc_w 578
    //   1322: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1325: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1328: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1331: ldc -104
    //   1333: new 154	java/lang/StringBuilder
    //   1336: dup
    //   1337: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   1340: ldc_w 671
    //   1343: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1346: getstatic 84	com/tapjoy/f:n	Ljava/lang/String;
    //   1349: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1352: ldc_w 578
    //   1355: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1358: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1361: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1364: ldc -104
    //   1366: new 154	java/lang/StringBuilder
    //   1369: dup
    //   1370: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   1373: ldc_w 673
    //   1376: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1379: getstatic 86	com/tapjoy/f:o	Ljava/lang/String;
    //   1382: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1385: ldc_w 578
    //   1388: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1391: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1394: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1397: ldc -104
    //   1399: new 154	java/lang/StringBuilder
    //   1402: dup
    //   1403: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   1406: ldc_w 675
    //   1409: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1412: getstatic 94	com/tapjoy/f:s	Ljava/lang/String;
    //   1415: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1418: ldc_w 578
    //   1421: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1424: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1427: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1430: ldc -104
    //   1432: new 154	java/lang/StringBuilder
    //   1435: dup
    //   1436: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   1439: ldc_w 677
    //   1442: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1445: getstatic 96	com/tapjoy/f:t	Ljava/lang/String;
    //   1448: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1451: ldc_w 578
    //   1454: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1457: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1460: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1463: ldc -104
    //   1465: new 154	java/lang/StringBuilder
    //   1468: dup
    //   1469: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   1472: ldc_w 679
    //   1475: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1478: getstatic 102	com/tapjoy/f:w	Ljava/lang/String;
    //   1481: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1484: ldc_w 578
    //   1487: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1490: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1493: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1496: ldc -104
    //   1498: new 154	java/lang/StringBuilder
    //   1501: dup
    //   1502: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   1505: ldc_w 681
    //   1508: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1511: getstatic 104	com/tapjoy/f:x	Ljava/lang/String;
    //   1514: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1517: ldc_w 578
    //   1520: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1523: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1526: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1529: ldc -104
    //   1531: new 154	java/lang/StringBuilder
    //   1534: dup
    //   1535: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   1538: ldc_w 683
    //   1541: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1544: getstatic 106	com/tapjoy/f:y	Ljava/lang/String;
    //   1547: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1550: ldc_w 578
    //   1553: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1556: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1559: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1562: ldc -104
    //   1564: new 154	java/lang/StringBuilder
    //   1567: dup
    //   1568: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   1571: ldc_w 685
    //   1574: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1577: getstatic 108	com/tapjoy/f:z	Ljava/lang/String;
    //   1580: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1583: ldc_w 578
    //   1586: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1589: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1592: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1595: ldc -104
    //   1597: new 154	java/lang/StringBuilder
    //   1600: dup
    //   1601: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   1604: ldc_w 687
    //   1607: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1610: getstatic 112	com/tapjoy/f:B	Ljava/lang/String;
    //   1613: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1616: ldc_w 578
    //   1619: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1622: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1625: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1628: ldc -104
    //   1630: new 154	java/lang/StringBuilder
    //   1633: dup
    //   1634: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   1637: ldc_w 689
    //   1640: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1643: getstatic 118	com/tapjoy/f:E	Ljava/lang/String;
    //   1646: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1649: ldc_w 578
    //   1652: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1655: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1658: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1661: getstatic 134	com/tapjoy/f:N	Ljava/util/Hashtable;
    //   1664: ifnull +188 -> 1852
    //   1667: ldc -104
    //   1669: ldc_w 691
    //   1672: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1675: ldc -104
    //   1677: ldc_w 693
    //   1680: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1683: getstatic 134	com/tapjoy/f:N	Ljava/util/Hashtable;
    //   1686: invokevirtual 697	java/util/Hashtable:entrySet	()Ljava/util/Set;
    //   1689: invokeinterface 700 1 0
    //   1694: astore_3
    //   1695: aload_3
    //   1696: invokeinterface 349 1 0
    //   1701: ifeq +151 -> 1852
    //   1704: aload_3
    //   1705: invokeinterface 353 1 0
    //   1710: checkcast 702	java/util/Map$Entry
    //   1713: astore 4
    //   1715: ldc -104
    //   1717: new 154	java/lang/StringBuilder
    //   1720: dup
    //   1721: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   1724: ldc_w 704
    //   1727: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1730: aload 4
    //   1732: invokeinterface 707 1 0
    //   1737: checkcast 249	java/lang/String
    //   1740: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1743: ldc_w 709
    //   1746: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1749: aload 4
    //   1751: invokeinterface 712 1 0
    //   1756: checkcast 249	java/lang/String
    //   1759: invokestatic 239	android/net/Uri:encode	(Ljava/lang/String;)Ljava/lang/String;
    //   1762: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1765: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1768: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1771: aload 4
    //   1773: invokeinterface 707 1 0
    //   1778: checkcast 249	java/lang/String
    //   1781: ldc_w 387
    //   1784: invokevirtual 378	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1787: ifeq -92 -> 1695
    //   1790: getstatic 124	com/tapjoy/f:G	Ljava/lang/String;
    //   1793: ldc_w 714
    //   1796: invokevirtual 378	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1799: ifne -104 -> 1695
    //   1802: ldc -104
    //   1804: ldc_w 716
    //   1807: invokestatic 718	com/tapjoy/u:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   1810: getstatic 134	com/tapjoy/f:N	Ljava/util/Hashtable;
    //   1813: ldc_w 387
    //   1816: invokevirtual 721	java/util/Hashtable:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1819: pop
    //   1820: goto -125 -> 1695
    //   1823: astore_3
    //   1824: ldc -104
    //   1826: new 154	java/lang/StringBuilder
    //   1829: dup
    //   1830: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   1833: ldc_w 723
    //   1836: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1839: aload_3
    //   1840: invokevirtual 202	java/lang/Exception:toString	()Ljava/lang/String;
    //   1843: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1846: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1849: invokestatic 204	com/tapjoy/u:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1852: return
    //   1853: ldc -104
    //   1855: ldc_w 725
    //   1858: invokestatic 172	com/tapjoy/u:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1861: goto -1677 -> 184
    //   1864: astore 4
    //   1866: ldc -104
    //   1868: new 154	java/lang/StringBuilder
    //   1871: dup
    //   1872: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   1875: ldc_w 727
    //   1878: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1881: aload 4
    //   1883: invokevirtual 202	java/lang/Exception:toString	()Ljava/lang/String;
    //   1886: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1889: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1892: invokestatic 204	com/tapjoy/u:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1895: goto -1711 -> 184
    //   1898: getstatic 66	com/tapjoy/f:e	Ljava/lang/String;
    //   1901: invokevirtual 253	java/lang/String:length	()I
    //   1904: ifeq +27 -> 1931
    //   1907: getstatic 66	com/tapjoy/f:e	Ljava/lang/String;
    //   1910: ldc_w 729
    //   1913: invokevirtual 378	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1916: ifne +15 -> 1931
    //   1919: getstatic 66	com/tapjoy/f:e	Ljava/lang/String;
    //   1922: ldc_w 731
    //   1925: invokevirtual 378	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1928: ifeq +16 -> 1944
    //   1931: ldc -104
    //   1933: ldc_w 733
    //   1936: invokestatic 204	com/tapjoy/u:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1939: iconst_1
    //   1940: istore_1
    //   1941: goto -1516 -> 425
    //   1944: getstatic 66	com/tapjoy/f:e	Ljava/lang/String;
    //   1947: invokevirtual 736	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   1950: putstatic 66	com/tapjoy/f:e	Ljava/lang/String;
    //   1953: iconst_0
    //   1954: istore_1
    //   1955: goto -1530 -> 425
    //   1958: getstatic 66	com/tapjoy/f:e	Ljava/lang/String;
    //   1961: invokevirtual 253	java/lang/String:length	()I
    //   1964: ifeq +39 -> 2003
    //   1967: getstatic 66	com/tapjoy/f:e	Ljava/lang/String;
    //   1970: ldc_w 729
    //   1973: invokevirtual 378	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1976: ifne +27 -> 2003
    //   1979: getstatic 66	com/tapjoy/f:e	Ljava/lang/String;
    //   1982: ldc_w 731
    //   1985: invokevirtual 378	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1988: ifne +15 -> 2003
    //   1991: getstatic 66	com/tapjoy/f:e	Ljava/lang/String;
    //   1994: ldc_w 738
    //   1997: invokevirtual 378	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2000: ifeq +16 -> 2016
    //   2003: ldc -104
    //   2005: ldc_w 740
    //   2008: invokestatic 204	com/tapjoy/u:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   2011: iconst_1
    //   2012: istore_2
    //   2013: goto -1452 -> 561
    //   2016: getstatic 66	com/tapjoy/f:e	Ljava/lang/String;
    //   2019: invokevirtual 736	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   2022: putstatic 66	com/tapjoy/f:e	Ljava/lang/String;
    //   2025: iconst_0
    //   2026: istore_2
    //   2027: goto -1466 -> 561
    //   2030: iload_1
    //   2031: bipush 32
    //   2033: if_icmpge +33 -> 2066
    //   2036: aload 4
    //   2038: ldc_w 742
    //   2041: invokestatic 748	java/lang/Math:random	()D
    //   2044: ldc2_w 749
    //   2047: dmul
    //   2048: d2i
    //   2049: bipush 30
    //   2051: irem
    //   2052: invokevirtual 754	java/lang/String:charAt	(I)C
    //   2055: invokevirtual 757	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   2058: pop
    //   2059: iload_1
    //   2060: iconst_1
    //   2061: iadd
    //   2062: istore_1
    //   2063: goto -33 -> 2030
    //   2066: aload 4
    //   2068: invokevirtual 758	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   2071: invokevirtual 736	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   2074: putstatic 66	com/tapjoy/f:e	Ljava/lang/String;
    //   2077: aload_3
    //   2078: invokeinterface 762 1 0
    //   2083: astore 4
    //   2085: aload 4
    //   2087: ldc_w 590
    //   2090: getstatic 66	com/tapjoy/f:e	Ljava/lang/String;
    //   2093: invokeinterface 768 3 0
    //   2098: pop
    //   2099: aload 4
    //   2101: invokeinterface 771 1 0
    //   2106: pop
    //   2107: goto -1492 -> 615
    //   2110: astore 4
    //   2112: ldc -104
    //   2114: new 154	java/lang/StringBuilder
    //   2117: dup
    //   2118: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   2121: ldc_w 773
    //   2124: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2127: aload 4
    //   2129: invokevirtual 202	java/lang/Exception:toString	()Ljava/lang/String;
    //   2132: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2135: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2138: invokestatic 204	com/tapjoy/u:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   2141: aconst_null
    //   2142: putstatic 66	com/tapjoy/f:e	Ljava/lang/String;
    //   2145: goto -1530 -> 615
    //   2148: astore 4
    //   2150: ldc -104
    //   2152: new 154	java/lang/StringBuilder
    //   2155: dup
    //   2156: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   2159: ldc_w 775
    //   2162: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2165: aload 4
    //   2167: invokevirtual 202	java/lang/Exception:toString	()Ljava/lang/String;
    //   2170: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2173: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2176: invokestatic 204	com/tapjoy/u:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   2179: goto -1466 -> 713
    //   2182: astore 4
    //   2184: ldc -104
    //   2186: new 154	java/lang/StringBuilder
    //   2189: dup
    //   2190: invokespecial 155	java/lang/StringBuilder:<init>	()V
    //   2193: ldc_w 777
    //   2196: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2199: aload 4
    //   2201: invokevirtual 202	java/lang/Exception:toString	()Ljava/lang/String;
    //   2204: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2207: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2210: invokestatic 204	com/tapjoy/u:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   2213: goto -1428 -> 785
    //   2216: iload_1
    //   2217: iconst_1
    //   2218: iadd
    //   2219: istore_1
    //   2220: goto -2154 -> 66
    //   2223: iconst_0
    //   2224: istore_1
    //   2225: goto -195 -> 2030
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2228	0	this	f
    //   65	2160	1	i1	int
    //   63	1964	2	i2	int
    //   6	1699	3	localObject1	Object
    //   1823	255	3	localException1	Exception
    //   40	1732	4	localObject2	Object
    //   1864	203	4	localException2	Exception
    //   2083	17	4	localEditor	android.content.SharedPreferences.Editor
    //   2110	18	4	localException3	Exception
    //   2148	18	4	localException4	Exception
    //   2182	18	4	localException5	Exception
    //   58	553	5	localObject3	Object
    //   75	91	6	str	String
    //   106	62	7	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   184	281	1823	java/lang/Exception
    //   615	630	1823	java/lang/Exception
    //   630	639	1823	java/lang/Exception
    //   785	865	1823	java/lang/Exception
    //   865	876	1823	java/lang/Exception
    //   880	893	1823	java/lang/Exception
    //   893	1695	1823	java/lang/Exception
    //   1695	1820	1823	java/lang/Exception
    //   2112	2145	1823	java/lang/Exception
    //   2150	2179	1823	java/lang/Exception
    //   2184	2213	1823	java/lang/Exception
    //   7	23	1864	java/lang/Exception
    //   27	42	1864	java/lang/Exception
    //   47	64	1864	java/lang/Exception
    //   77	108	1864	java/lang/Exception
    //   113	120	1864	java/lang/Exception
    //   125	173	1864	java/lang/Exception
    //   176	184	1864	java/lang/Exception
    //   1853	1861	1864	java/lang/Exception
    //   281	295	2110	java/lang/Exception
    //   300	357	2110	java/lang/Exception
    //   357	382	2110	java/lang/Exception
    //   382	423	2110	java/lang/Exception
    //   425	452	2110	java/lang/Exception
    //   454	486	2110	java/lang/Exception
    //   490	496	2110	java/lang/Exception
    //   496	559	2110	java/lang/Exception
    //   565	595	2110	java/lang/Exception
    //   600	615	2110	java/lang/Exception
    //   1898	1931	2110	java/lang/Exception
    //   1931	1939	2110	java/lang/Exception
    //   1944	1953	2110	java/lang/Exception
    //   1958	2003	2110	java/lang/Exception
    //   2003	2011	2110	java/lang/Exception
    //   2016	2025	2110	java/lang/Exception
    //   2036	2059	2110	java/lang/Exception
    //   2066	2107	2110	java/lang/Exception
    //   639	713	2148	java/lang/Exception
    //   713	727	2182	java/lang/Exception
    //   732	739	2182	java/lang/Exception
    //   744	785	2182	java/lang/Exception
  }
  
  public void b()
  {
    new Thread(new g(this)).start();
  }
  
  public void d(String paramString)
  {
    u.a("TapjoyConnect", "actionComplete: " + paramString);
    paramString = "app_id=" + paramString + "&";
    String str = paramString + k();
    if (e("sha_2_udid") != null)
    {
      paramString = str;
      if (e("sha_2_udid").equals("true")) {}
    }
    else
    {
      paramString = str + "&publisher_user_id=" + e();
    }
    paramString = paramString + "&";
    long l1 = System.currentTimeMillis() / 1000L;
    paramString = paramString + "timestamp=" + l1 + "&";
    paramString = paramString + "verifier=" + a(l1);
    u.a("TapjoyConnect", "PPA URL parameters: " + paramString);
    new Thread(new h(this, paramString)).start();
  }
}
