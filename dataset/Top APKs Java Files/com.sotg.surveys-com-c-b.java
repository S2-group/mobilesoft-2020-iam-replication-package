package com.c;

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

public class b
{
  private static String A = "";
  private static String B = "";
  private static String C = "";
  private static String D = "";
  private static String E = "";
  private static String F = "";
  private static String G = "native";
  private static String H = "";
  private static boolean I = false;
  private static String J = "";
  private static float K = 1.0F;
  private static String L = null;
  private static Hashtable<String, String> O = null;
  private static String P = "";
  private static Context a = null;
  private static b b = null;
  private static l c = null;
  private static g d = null;
  private static n e = null;
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
  private long M = 0L;
  private Timer N = null;
  
  public b(Context paramContext)
  {
    a = paramContext;
    c = new l();
    l();
    k.a("TapjoyConnect", "URL parameters: " + c());
    b();
    if ((d("user_id") != null) && (d("user_id").length() > 0))
    {
      k.a("TapjoyConnect", "Setting userID to: " + d("user_id"));
      b(d("user_id"));
    }
  }
  
  public static b a()
  {
    return b;
  }
  
  public static String a(long paramLong)
  {
    try
    {
      String str = m.a(q + ":" + g + ":" + paramLong + ":" + D);
      return str;
    }
    catch (Exception localException)
    {
      k.b("TapjoyConnect", "getVerifier ERROR: " + localException.toString());
    }
    return "";
  }
  
  public static String a(long paramLong, String paramString)
  {
    try
    {
      paramString = m.a(q + ":" + g + ":" + paramLong + ":" + D + ":" + paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      k.b("TapjoyConnect", "getVerifier ERROR: " + paramString.toString());
    }
    return "";
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, Hashtable<String, String> paramHashtable, g paramG)
  {
    q = paramString1;
    D = paramString2;
    O = paramHashtable;
    d = paramG;
    b = new b(paramContext);
  }
  
  public static void a(String paramString)
  {
    H = paramString;
  }
  
  public static void b(String paramString)
  {
    v = paramString;
    k.a("TapjoyConnect", "URL parameters: " + c());
    new Thread(new c()).start();
  }
  
  public static String c()
  {
    String str2 = d() + "&";
    long l1 = System.currentTimeMillis() / 1000L;
    String str1 = a(l1);
    str2 = str2 + "timestamp=" + l1 + "&";
    return str2 + "verifier=" + str1;
  }
  
  public static String d()
  {
    String str = "" + "app_id=" + Uri.encode(q) + "&";
    return str + k();
  }
  
  public static String d(String paramString)
  {
    String str = "";
    if (O != null) {
      str = (String)O.get(paramString);
    }
    paramString = str;
    if (str == null) {
      paramString = "";
    }
    return paramString;
  }
  
  public static String e()
  {
    return v;
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
          k.a("TapjoyConnect", "connectivity: " + localConnectivityManager.getActiveNetworkInfo().getType());
          str3 = str1;
          k.a("TapjoyConnect", "connection_type: " + str1);
          str4 = str1;
        }
      }
      catch (Exception localException)
      {
        k.b("TapjoyConnect", "getConnectionType error: " + localException.toString());
        return str3;
      }
      str1 = "wifi";
      continue;
      String str2 = "mobile";
    }
    return str4;
  }
  
  private static boolean f(String paramString)
  {
    paramString = m.b(paramString);
    if (paramString != null)
    {
      Object localObject = m.a(paramString.getElementsByTagName("PackageNames"));
      if ((localObject != null) && (((String)localObject).length() > 0))
      {
        Vector localVector = new Vector();
        int i2;
        for (int i1 = 0;; i1 = i2 + 1)
        {
          i2 = ((String)localObject).indexOf(',', i1);
          if (i2 == -1)
          {
            k.a("TapjoyConnect", "parse: " + ((String)localObject).substring(i1).trim());
            localVector.add(((String)localObject).substring(i1).trim());
            P = "";
            localObject = a.getPackageManager().getInstalledApplications(0).iterator();
            while (((Iterator)localObject).hasNext())
            {
              ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
              if (((localApplicationInfo.flags & 0x1) != 1) && (localVector.contains(localApplicationInfo.packageName)))
              {
                k.a("TapjoyConnect", "MATCH: installed packageName: " + localApplicationInfo.packageName);
                if (P.length() > 0) {
                  P += ",";
                }
                P += localApplicationInfo.packageName;
              }
            }
          }
          k.a("TapjoyConnect", "parse: " + ((String)localObject).substring(i1, i2).trim());
          localVector.add(((String)localObject).substring(i1, i2).trim());
        }
      }
      paramString = m.a(paramString.getElementsByTagName("Success"));
      if ((paramString == null) || (!paramString.equals("true"))) {}
    }
    return true;
  }
  
  private boolean g(String paramString)
  {
    paramString = m.b(paramString);
    if (paramString != null)
    {
      paramString = m.a(paramString.getElementsByTagName("Success"));
      if ((paramString != null) && (paramString.equals("true")))
      {
        k.a("TapjoyConnect", "Successfully sent completed Pay-Per-Action to Tapjoy server.");
        return true;
      }
      k.b("TapjoyConnect", "Completed Pay-Per-Action call failed.");
    }
    return false;
  }
  
  private static String k()
  {
    Object localObject1 = "" + "android_id=" + f + "&";
    if ((d("sha_2_udid") != null) && (d("sha_2_udid").equals("true"))) {}
    for (Object localObject2 = (String)localObject1 + "sha2_udid=" + Uri.encode(h) + "&";; localObject2 = (String)localObject1 + "udid=" + Uri.encode(g) + "&")
    {
      localObject1 = localObject2;
      if (i != null)
      {
        localObject1 = localObject2;
        if (i.length() > 0) {
          localObject1 = (String)localObject2 + "mac_address=" + Uri.encode(i) + "&";
        }
      }
      localObject2 = localObject1;
      if (j != null)
      {
        localObject2 = localObject1;
        if (j.length() > 0) {
          localObject2 = (String)localObject1 + "serial_id=" + Uri.encode(j) + "&";
        }
      }
      localObject1 = (String)localObject2 + "device_name=" + Uri.encode(k) + "&";
      localObject1 = (String)localObject1 + "device_manufacturer=" + Uri.encode(l) + "&";
      localObject1 = (String)localObject1 + "device_type=" + Uri.encode(m) + "&";
      localObject1 = (String)localObject1 + "os_version=" + Uri.encode(n) + "&";
      localObject1 = (String)localObject1 + "country_code=" + Uri.encode(o) + "&";
      localObject1 = (String)localObject1 + "language_code=" + Uri.encode(p) + "&";
      localObject1 = (String)localObject1 + "app_version=" + Uri.encode(r) + "&";
      localObject1 = (String)localObject1 + "library_version=" + Uri.encode(s) + "&";
      localObject1 = (String)localObject1 + "platform=" + Uri.encode(w) + "&";
      localObject2 = (String)localObject1 + "display_multiplier=" + Uri.encode(Float.toString(K));
      localObject1 = localObject2;
      if (x.length() > 0)
      {
        localObject1 = (String)localObject2 + "&";
        localObject1 = (String)localObject1 + "carrier_name=" + Uri.encode(x);
      }
      localObject2 = localObject1;
      if (y.length() > 0)
      {
        localObject1 = (String)localObject1 + "&";
        localObject2 = (String)localObject1 + "carrier_country_code=" + Uri.encode(y);
      }
      localObject1 = localObject2;
      if (z.length() > 0)
      {
        localObject1 = (String)localObject2 + "&";
        localObject1 = (String)localObject1 + "mobile_country_code=" + Uri.encode(z);
      }
      localObject2 = localObject1;
      if (A.length() > 0)
      {
        localObject1 = (String)localObject1 + "&";
        localObject2 = (String)localObject1 + "mobile_network_code=" + Uri.encode(A);
      }
      localObject1 = localObject2;
      if (t.length() > 0)
      {
        localObject1 = localObject2;
        if (u.length() > 0)
        {
          localObject1 = (String)localObject2 + "&";
          localObject1 = (String)localObject1 + "screen_density=" + Uri.encode(t) + "&";
          localObject1 = (String)localObject1 + "screen_layout_size=" + Uri.encode(u);
        }
      }
      B = f();
      localObject2 = localObject1;
      if (B.length() > 0)
      {
        localObject1 = (String)localObject1 + "&";
        localObject2 = (String)localObject1 + "connection_type=" + Uri.encode(B);
      }
      localObject1 = localObject2;
      if (G.length() > 0)
      {
        localObject1 = (String)localObject2 + "&";
        localObject1 = (String)localObject1 + "plugin=" + Uri.encode(G);
      }
      localObject2 = localObject1;
      if (H.length() > 0)
      {
        localObject1 = (String)localObject1 + "&";
        localObject2 = (String)localObject1 + "sdk_type=" + Uri.encode(H);
      }
      localObject1 = localObject2;
      if (C.length() > 0)
      {
        localObject1 = (String)localObject2 + "&";
        localObject1 = (String)localObject1 + "store_name=" + Uri.encode(C);
      }
      return localObject1;
    }
  }
  
  /* Error */
  private void l()
  {
    // Byte code:
    //   0: getstatic 60	com/c/b:a	Landroid/content/Context;
    //   3: invokevirtual 340	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   6: astore_3
    //   7: getstatic 140	com/c/b:O	Ljava/util/Hashtable;
    //   10: ifnonnull +13 -> 23
    //   13: new 264	java/util/Hashtable
    //   16: dup
    //   17: invokespecial 453	java/util/Hashtable:<init>	()V
    //   20: putstatic 140	com/c/b:O	Ljava/util/Hashtable;
    //   23: aload_3
    //   24: ifnull +160 -> 184
    //   27: aload_3
    //   28: getstatic 60	com/c/b:a	Landroid/content/Context;
    //   31: invokevirtual 456	android/content/Context:getPackageName	()Ljava/lang/String;
    //   34: sipush 128
    //   37: invokevirtual 460	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   40: astore 4
    //   42: aload 4
    //   44: ifnull +1826 -> 1870
    //   47: aload 4
    //   49: getfield 464	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   52: ifnull +1818 -> 1870
    //   55: getstatic 469	com/c/f:a	[Ljava/lang/String;
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
    //   79: getfield 464	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   82: new 160	java/lang/StringBuilder
    //   85: dup
    //   86: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   89: ldc_w 471
    //   92: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: aload 6
    //   97: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   103: invokevirtual 475	android/os/Bundle:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   106: astore 7
    //   108: aload 7
    //   110: ifnull +2143 -> 2253
    //   113: aload 7
    //   115: invokevirtual 476	java/lang/Object:toString	()Ljava/lang/String;
    //   118: astore 7
    //   120: aload 7
    //   122: ifnull +2131 -> 2253
    //   125: ldc -98
    //   127: new 160	java/lang/StringBuilder
    //   130: dup
    //   131: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   134: ldc_w 478
    //   137: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: aload 6
    //   142: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: ldc_w 480
    //   148: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: aload 7
    //   153: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   159: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   162: getstatic 140	com/c/b:O	Ljava/util/Hashtable;
    //   165: aload 6
    //   167: aload 7
    //   169: invokevirtual 484	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   172: pop
    //   173: goto +2080 -> 2253
    //   176: ldc -98
    //   178: ldc_w 486
    //   181: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   184: ldc_w 488
    //   187: invokestatic 185	com/c/b:d	(Ljava/lang/String;)Ljava/lang/String;
    //   190: ifnull +22 -> 212
    //   193: ldc_w 488
    //   196: invokestatic 185	com/c/b:d	(Ljava/lang/String;)Ljava/lang/String;
    //   199: ldc_w 384
    //   202: invokevirtual 387	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   205: ifeq +7 -> 212
    //   208: iconst_1
    //   209: invokestatic 491	com/c/k:a	(Z)V
    //   212: getstatic 60	com/c/b:a	Landroid/content/Context;
    //   215: invokevirtual 495	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   218: ldc_w 497
    //   221: invokestatic 503	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   224: putstatic 72	com/c/b:f	Ljava/lang/String;
    //   227: aload_3
    //   228: getstatic 60	com/c/b:a	Landroid/content/Context;
    //   231: invokevirtual 456	android/content/Context:getPackageName	()Ljava/lang/String;
    //   234: iconst_0
    //   235: invokevirtual 507	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   238: getfield 512	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   241: putstatic 96	com/c/b:r	Ljava/lang/String;
    //   244: ldc_w 514
    //   247: putstatic 86	com/c/b:m	Ljava/lang/String;
    //   250: ldc_w 514
    //   253: putstatic 106	com/c/b:w	Ljava/lang/String;
    //   256: getstatic 519	android/os/Build:MODEL	Ljava/lang/String;
    //   259: putstatic 82	com/c/b:k	Ljava/lang/String;
    //   262: getstatic 522	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   265: putstatic 84	com/c/b:l	Ljava/lang/String;
    //   268: getstatic 527	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   271: putstatic 88	com/c/b:n	Ljava/lang/String;
    //   274: invokestatic 533	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   277: invokevirtual 536	java/util/Locale:getCountry	()Ljava/lang/String;
    //   280: putstatic 90	com/c/b:o	Ljava/lang/String;
    //   283: invokestatic 533	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   286: invokevirtual 539	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   289: putstatic 92	com/c/b:p	Ljava/lang/String;
    //   292: ldc_w 541
    //   295: putstatic 98	com/c/b:s	Ljava/lang/String;
    //   298: getstatic 60	com/c/b:a	Landroid/content/Context;
    //   301: ldc_w 543
    //   304: iconst_0
    //   305: invokevirtual 547	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   308: astore_3
    //   309: getstatic 60	com/c/b:a	Landroid/content/Context;
    //   312: ldc_w 549
    //   315: invokevirtual 278	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   318: checkcast 551	android/telephony/TelephonyManager
    //   321: astore 4
    //   323: aload 4
    //   325: ifnull +107 -> 432
    //   328: ldc_w 553
    //   331: invokestatic 185	com/c/b:d	(Ljava/lang/String;)Ljava/lang/String;
    //   334: ifnull +1581 -> 1915
    //   337: ldc_w 553
    //   340: invokestatic 185	com/c/b:d	(Ljava/lang/String;)Ljava/lang/String;
    //   343: invokevirtual 191	java/lang/String:length	()I
    //   346: ifle +1569 -> 1915
    //   349: ldc_w 553
    //   352: invokestatic 185	com/c/b:d	(Ljava/lang/String;)Ljava/lang/String;
    //   355: putstatic 74	com/c/b:g	Ljava/lang/String;
    //   358: aload 4
    //   360: invokevirtual 556	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   363: putstatic 108	com/c/b:x	Ljava/lang/String;
    //   366: aload 4
    //   368: invokevirtual 559	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   371: putstatic 110	com/c/b:y	Ljava/lang/String;
    //   374: aload 4
    //   376: invokevirtual 562	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   379: ifnull +53 -> 432
    //   382: aload 4
    //   384: invokevirtual 562	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   387: invokevirtual 191	java/lang/String:length	()I
    //   390: iconst_5
    //   391: if_icmpeq +16 -> 407
    //   394: aload 4
    //   396: invokevirtual 562	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   399: invokevirtual 191	java/lang/String:length	()I
    //   402: bipush 6
    //   404: if_icmpne +28 -> 432
    //   407: aload 4
    //   409: invokevirtual 562	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   412: iconst_0
    //   413: iconst_3
    //   414: invokevirtual 380	java/lang/String:substring	(II)Ljava/lang/String;
    //   417: putstatic 112	com/c/b:z	Ljava/lang/String;
    //   420: aload 4
    //   422: invokevirtual 562	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   425: iconst_3
    //   426: invokevirtual 329	java/lang/String:substring	(I)Ljava/lang/String;
    //   429: putstatic 114	com/c/b:A	Ljava/lang/String;
    //   432: ldc -98
    //   434: new 160	java/lang/StringBuilder
    //   437: dup
    //   438: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   441: ldc_w 564
    //   444: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   447: getstatic 74	com/c/b:g	Ljava/lang/String;
    //   450: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   453: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   456: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   459: getstatic 74	com/c/b:g	Ljava/lang/String;
    //   462: ifnonnull +1502 -> 1964
    //   465: ldc -98
    //   467: ldc_w 566
    //   470: invokestatic 214	com/c/k:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   473: iconst_1
    //   474: istore_1
    //   475: ldc -98
    //   477: new 160	java/lang/StringBuilder
    //   480: dup
    //   481: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   484: ldc_w 568
    //   487: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   490: getstatic 571	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   493: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   496: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   499: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   502: iload_1
    //   503: istore_2
    //   504: getstatic 571	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   507: invokestatic 577	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   510: bipush 9
    //   512: if_icmplt +99 -> 611
    //   515: ldc -98
    //   517: ldc_w 579
    //   520: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   523: new 581	com/c/i
    //   526: dup
    //   527: invokespecial 582	com/c/i:<init>	()V
    //   530: invokevirtual 584	com/c/i:a	()Ljava/lang/String;
    //   533: putstatic 80	com/c/b:j	Ljava/lang/String;
    //   536: iload_1
    //   537: ifeq +9 -> 546
    //   540: getstatic 80	com/c/b:j	Ljava/lang/String;
    //   543: putstatic 74	com/c/b:g	Ljava/lang/String;
    //   546: ldc -98
    //   548: ldc_w 586
    //   551: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   554: ldc -98
    //   556: new 160	java/lang/StringBuilder
    //   559: dup
    //   560: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   563: ldc_w 588
    //   566: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   569: getstatic 74	com/c/b:g	Ljava/lang/String;
    //   572: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   575: ldc_w 590
    //   578: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   581: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   584: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   587: ldc -98
    //   589: ldc_w 586
    //   592: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   595: getstatic 74	com/c/b:g	Ljava/lang/String;
    //   598: ifnonnull +1429 -> 2027
    //   601: ldc -98
    //   603: ldc_w 592
    //   606: invokestatic 214	com/c/k:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   609: iconst_1
    //   610: istore_2
    //   611: iload_2
    //   612: ifeq +53 -> 665
    //   615: new 594	java/lang/StringBuffer
    //   618: dup
    //   619: invokespecial 595	java/lang/StringBuffer:<init>	()V
    //   622: astore 4
    //   624: aload 4
    //   626: ldc_w 597
    //   629: invokevirtual 600	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   632: pop
    //   633: aload_3
    //   634: ldc_w 602
    //   637: aconst_null
    //   638: invokeinterface 607 3 0
    //   643: astore 5
    //   645: aload 5
    //   647: ifnull +1613 -> 2260
    //   650: aload 5
    //   652: ldc 70
    //   654: invokevirtual 387	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   657: ifne +1603 -> 2260
    //   660: aload 5
    //   662: putstatic 74	com/c/b:g	Ljava/lang/String;
    //   665: getstatic 104	com/c/b:v	Ljava/lang/String;
    //   668: invokevirtual 191	java/lang/String:length	()I
    //   671: ifne +9 -> 680
    //   674: getstatic 74	com/c/b:g	Ljava/lang/String;
    //   677: putstatic 104	com/c/b:v	Ljava/lang/String;
    //   680: getstatic 74	com/c/b:g	Ljava/lang/String;
    //   683: invokestatic 209	com/c/m:a	(Ljava/lang/String;)Ljava/lang/String;
    //   686: putstatic 76	com/c/b:h	Ljava/lang/String;
    //   689: getstatic 571	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   692: invokestatic 577	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   695: iconst_3
    //   696: if_icmple +67 -> 763
    //   699: new 609	com/c/h
    //   702: dup
    //   703: getstatic 60	com/c/b:a	Landroid/content/Context;
    //   706: invokespecial 610	com/c/h:<init>	(Landroid/content/Context;)V
    //   709: astore 4
    //   711: new 160	java/lang/StringBuilder
    //   714: dup
    //   715: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   718: ldc 70
    //   720: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   723: aload 4
    //   725: invokevirtual 612	com/c/h:a	()I
    //   728: invokevirtual 294	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   731: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   734: putstatic 100	com/c/b:t	Ljava/lang/String;
    //   737: new 160	java/lang/StringBuilder
    //   740: dup
    //   741: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   744: ldc 70
    //   746: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   749: aload 4
    //   751: invokevirtual 614	com/c/h:b	()I
    //   754: invokevirtual 294	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   757: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   760: putstatic 102	com/c/b:u	Ljava/lang/String;
    //   763: getstatic 60	com/c/b:a	Landroid/content/Context;
    //   766: ldc_w 298
    //   769: invokevirtual 278	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   772: checkcast 616	android/net/wifi/WifiManager
    //   775: astore 4
    //   777: aload 4
    //   779: ifnull +23 -> 802
    //   782: aload 4
    //   784: invokevirtual 620	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   787: astore 4
    //   789: aload 4
    //   791: ifnull +11 -> 802
    //   794: aload 4
    //   796: invokevirtual 625	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   799: putstatic 78	com/c/b:i	Ljava/lang/String;
    //   802: ldc_w 627
    //   805: invokestatic 185	com/c/b:d	(Ljava/lang/String;)Ljava/lang/String;
    //   808: ifnull +74 -> 882
    //   811: ldc_w 627
    //   814: invokestatic 185	com/c/b:d	(Ljava/lang/String;)Ljava/lang/String;
    //   817: invokevirtual 191	java/lang/String:length	()I
    //   820: ifle +62 -> 882
    //   823: ldc_w 627
    //   826: invokestatic 185	com/c/b:d	(Ljava/lang/String;)Ljava/lang/String;
    //   829: putstatic 118	com/c/b:C	Ljava/lang/String;
    //   832: new 629	java/util/ArrayList
    //   835: dup
    //   836: getstatic 631	com/c/f:b	[Ljava/lang/String;
    //   839: invokestatic 637	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   842: invokespecial 640	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   845: getstatic 118	com/c/b:C	Ljava/lang/String;
    //   848: invokevirtual 641	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   851: ifne +31 -> 882
    //   854: ldc -98
    //   856: new 160	java/lang/StringBuilder
    //   859: dup
    //   860: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   863: ldc_w 643
    //   866: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   869: getstatic 118	com/c/b:C	Ljava/lang/String;
    //   872: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   875: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   878: invokestatic 648	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   881: pop
    //   882: aload_3
    //   883: ldc_w 650
    //   886: aconst_null
    //   887: invokeinterface 607 3 0
    //   892: astore_3
    //   893: aload_3
    //   894: ifnull +16 -> 910
    //   897: aload_3
    //   898: ldc 70
    //   900: invokevirtual 387	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   903: ifne +7 -> 910
    //   906: aload_3
    //   907: putstatic 124	com/c/b:F	Ljava/lang/String;
    //   910: getstatic 60	com/c/b:a	Landroid/content/Context;
    //   913: invokevirtual 456	android/content/Context:getPackageName	()Ljava/lang/String;
    //   916: putstatic 122	com/c/b:E	Ljava/lang/String;
    //   919: ldc -98
    //   921: new 160	java/lang/StringBuilder
    //   924: dup
    //   925: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   928: ldc_w 652
    //   931: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   934: getstatic 94	com/c/b:q	Ljava/lang/String;
    //   937: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   940: ldc_w 590
    //   943: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   946: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   949: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   952: ldc -98
    //   954: new 160	java/lang/StringBuilder
    //   957: dup
    //   958: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   961: ldc_w 654
    //   964: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   967: getstatic 72	com/c/b:f	Ljava/lang/String;
    //   970: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   973: ldc_w 590
    //   976: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   979: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   982: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   985: ldc -98
    //   987: new 160	java/lang/StringBuilder
    //   990: dup
    //   991: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   994: ldc_w 656
    //   997: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1000: getstatic 122	com/c/b:E	Ljava/lang/String;
    //   1003: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1006: ldc_w 590
    //   1009: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1012: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1015: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1018: new 160	java/lang/StringBuilder
    //   1021: dup
    //   1022: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   1025: ldc_w 658
    //   1028: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1031: getstatic 74	com/c/b:g	Ljava/lang/String;
    //   1034: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1037: ldc_w 590
    //   1040: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1043: astore 4
    //   1045: ldc_w 553
    //   1048: invokestatic 185	com/c/b:d	(Ljava/lang/String;)Ljava/lang/String;
    //   1051: ifnull +1196 -> 2247
    //   1054: ldc_w 553
    //   1057: invokestatic 185	com/c/b:d	(Ljava/lang/String;)Ljava/lang/String;
    //   1060: invokevirtual 191	java/lang/String:length	()I
    //   1063: ifle +1184 -> 2247
    //   1066: ldc_w 660
    //   1069: astore_3
    //   1070: ldc -98
    //   1072: aload 4
    //   1074: aload_3
    //   1075: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1078: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1081: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1084: ldc -98
    //   1086: new 160	java/lang/StringBuilder
    //   1089: dup
    //   1090: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   1093: ldc_w 662
    //   1096: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1099: getstatic 76	com/c/b:h	Ljava/lang/String;
    //   1102: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1105: ldc_w 590
    //   1108: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1111: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1114: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1117: ldc -98
    //   1119: new 160	java/lang/StringBuilder
    //   1122: dup
    //   1123: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   1126: ldc_w 664
    //   1129: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1132: getstatic 80	com/c/b:j	Ljava/lang/String;
    //   1135: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1138: ldc_w 590
    //   1141: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1144: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1147: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1150: ldc -98
    //   1152: new 160	java/lang/StringBuilder
    //   1155: dup
    //   1156: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   1159: ldc_w 666
    //   1162: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1165: getstatic 78	com/c/b:i	Ljava/lang/String;
    //   1168: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1171: ldc_w 590
    //   1174: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1177: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1180: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1183: ldc -98
    //   1185: new 160	java/lang/StringBuilder
    //   1188: dup
    //   1189: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   1192: ldc_w 668
    //   1195: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1198: getstatic 82	com/c/b:k	Ljava/lang/String;
    //   1201: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1204: ldc_w 590
    //   1207: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1210: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1213: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1216: ldc -98
    //   1218: new 160	java/lang/StringBuilder
    //   1221: dup
    //   1222: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   1225: ldc_w 670
    //   1228: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1231: getstatic 84	com/c/b:l	Ljava/lang/String;
    //   1234: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1237: ldc_w 590
    //   1240: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1243: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1246: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1249: ldc -98
    //   1251: new 160	java/lang/StringBuilder
    //   1254: dup
    //   1255: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   1258: ldc_w 672
    //   1261: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1264: getstatic 86	com/c/b:m	Ljava/lang/String;
    //   1267: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1270: ldc_w 590
    //   1273: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1276: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1279: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1282: ldc -98
    //   1284: new 160	java/lang/StringBuilder
    //   1287: dup
    //   1288: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   1291: ldc_w 674
    //   1294: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1297: getstatic 98	com/c/b:s	Ljava/lang/String;
    //   1300: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1303: ldc_w 590
    //   1306: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1309: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1312: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1315: ldc -98
    //   1317: new 160	java/lang/StringBuilder
    //   1320: dup
    //   1321: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   1324: ldc_w 676
    //   1327: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1330: getstatic 88	com/c/b:n	Ljava/lang/String;
    //   1333: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1336: ldc_w 590
    //   1339: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1342: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1345: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1348: ldc -98
    //   1350: new 160	java/lang/StringBuilder
    //   1353: dup
    //   1354: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   1357: ldc_w 678
    //   1360: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1363: getstatic 90	com/c/b:o	Ljava/lang/String;
    //   1366: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1369: ldc_w 590
    //   1372: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1375: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1378: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1381: ldc -98
    //   1383: new 160	java/lang/StringBuilder
    //   1386: dup
    //   1387: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   1390: ldc_w 680
    //   1393: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1396: getstatic 92	com/c/b:p	Ljava/lang/String;
    //   1399: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1402: ldc_w 590
    //   1405: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1408: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1411: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1414: ldc -98
    //   1416: new 160	java/lang/StringBuilder
    //   1419: dup
    //   1420: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   1423: ldc_w 682
    //   1426: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1429: getstatic 100	com/c/b:t	Ljava/lang/String;
    //   1432: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1435: ldc_w 590
    //   1438: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1441: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1444: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1447: ldc -98
    //   1449: new 160	java/lang/StringBuilder
    //   1452: dup
    //   1453: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   1456: ldc_w 684
    //   1459: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1462: getstatic 102	com/c/b:u	Ljava/lang/String;
    //   1465: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1468: ldc_w 590
    //   1471: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1474: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1477: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1480: ldc -98
    //   1482: new 160	java/lang/StringBuilder
    //   1485: dup
    //   1486: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   1489: ldc_w 686
    //   1492: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1495: getstatic 108	com/c/b:x	Ljava/lang/String;
    //   1498: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1501: ldc_w 590
    //   1504: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1507: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1510: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1513: ldc -98
    //   1515: new 160	java/lang/StringBuilder
    //   1518: dup
    //   1519: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   1522: ldc_w 688
    //   1525: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1528: getstatic 110	com/c/b:y	Ljava/lang/String;
    //   1531: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1534: ldc_w 590
    //   1537: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1540: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1543: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1546: ldc -98
    //   1548: new 160	java/lang/StringBuilder
    //   1551: dup
    //   1552: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   1555: ldc_w 690
    //   1558: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1561: getstatic 112	com/c/b:z	Ljava/lang/String;
    //   1564: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1567: ldc_w 590
    //   1570: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1573: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1576: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1579: ldc -98
    //   1581: new 160	java/lang/StringBuilder
    //   1584: dup
    //   1585: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   1588: ldc_w 692
    //   1591: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1594: getstatic 114	com/c/b:A	Ljava/lang/String;
    //   1597: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1600: ldc_w 590
    //   1603: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1606: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1609: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1612: ldc -98
    //   1614: new 160	java/lang/StringBuilder
    //   1617: dup
    //   1618: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   1621: ldc_w 694
    //   1624: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1627: getstatic 118	com/c/b:C	Ljava/lang/String;
    //   1630: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1633: ldc_w 590
    //   1636: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1639: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1642: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1645: ldc -98
    //   1647: new 160	java/lang/StringBuilder
    //   1650: dup
    //   1651: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   1654: ldc_w 696
    //   1657: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1660: getstatic 124	com/c/b:F	Ljava/lang/String;
    //   1663: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1666: ldc_w 590
    //   1669: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1672: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1675: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1678: getstatic 140	com/c/b:O	Ljava/util/Hashtable;
    //   1681: ifnull +188 -> 1869
    //   1684: ldc -98
    //   1686: ldc_w 698
    //   1689: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1692: ldc -98
    //   1694: ldc_w 700
    //   1697: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1700: getstatic 140	com/c/b:O	Ljava/util/Hashtable;
    //   1703: invokevirtual 704	java/util/Hashtable:entrySet	()Ljava/util/Set;
    //   1706: invokeinterface 707 1 0
    //   1711: astore_3
    //   1712: aload_3
    //   1713: invokeinterface 358 1 0
    //   1718: ifeq +151 -> 1869
    //   1721: aload_3
    //   1722: invokeinterface 362 1 0
    //   1727: checkcast 709	java/util/Map$Entry
    //   1730: astore 4
    //   1732: ldc -98
    //   1734: new 160	java/lang/StringBuilder
    //   1737: dup
    //   1738: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   1741: ldc_w 711
    //   1744: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1747: aload 4
    //   1749: invokeinterface 714 1 0
    //   1754: checkcast 187	java/lang/String
    //   1757: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1760: ldc_w 716
    //   1763: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1766: aload 4
    //   1768: invokeinterface 719 1 0
    //   1773: checkcast 187	java/lang/String
    //   1776: invokestatic 260	android/net/Uri:encode	(Ljava/lang/String;)Ljava/lang/String;
    //   1779: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1782: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1785: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1788: aload 4
    //   1790: invokeinterface 714 1 0
    //   1795: checkcast 187	java/lang/String
    //   1798: ldc_w 397
    //   1801: invokevirtual 387	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1804: ifeq -92 -> 1712
    //   1807: getstatic 130	com/c/b:H	Ljava/lang/String;
    //   1810: ldc_w 721
    //   1813: invokevirtual 387	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1816: ifne -104 -> 1712
    //   1819: ldc -98
    //   1821: ldc_w 723
    //   1824: invokestatic 725	com/c/k:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   1827: getstatic 140	com/c/b:O	Ljava/util/Hashtable;
    //   1830: ldc_w 397
    //   1833: invokevirtual 728	java/util/Hashtable:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1836: pop
    //   1837: goto -125 -> 1712
    //   1840: astore_3
    //   1841: ldc -98
    //   1843: new 160	java/lang/StringBuilder
    //   1846: dup
    //   1847: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   1850: ldc_w 730
    //   1853: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1856: aload_3
    //   1857: invokevirtual 212	java/lang/Exception:toString	()Ljava/lang/String;
    //   1860: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1863: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1866: invokestatic 214	com/c/k:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1869: return
    //   1870: ldc -98
    //   1872: ldc_w 732
    //   1875: invokestatic 178	com/c/k:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1878: goto -1694 -> 184
    //   1881: astore 4
    //   1883: ldc -98
    //   1885: new 160	java/lang/StringBuilder
    //   1888: dup
    //   1889: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   1892: ldc_w 734
    //   1895: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1898: aload 4
    //   1900: invokevirtual 212	java/lang/Exception:toString	()Ljava/lang/String;
    //   1903: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1906: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1909: invokestatic 214	com/c/k:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1912: goto -1728 -> 184
    //   1915: aload 4
    //   1917: invokevirtual 737	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   1920: putstatic 74	com/c/b:g	Ljava/lang/String;
    //   1923: goto -1565 -> 358
    //   1926: astore 4
    //   1928: ldc -98
    //   1930: new 160	java/lang/StringBuilder
    //   1933: dup
    //   1934: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   1937: ldc_w 739
    //   1940: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1943: aload 4
    //   1945: invokevirtual 212	java/lang/Exception:toString	()Ljava/lang/String;
    //   1948: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1951: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1954: invokestatic 214	com/c/k:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1957: aconst_null
    //   1958: putstatic 74	com/c/b:g	Ljava/lang/String;
    //   1961: goto -1296 -> 665
    //   1964: getstatic 74	com/c/b:g	Ljava/lang/String;
    //   1967: invokevirtual 191	java/lang/String:length	()I
    //   1970: ifeq +27 -> 1997
    //   1973: getstatic 74	com/c/b:g	Ljava/lang/String;
    //   1976: ldc_w 741
    //   1979: invokevirtual 387	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1982: ifne +15 -> 1997
    //   1985: getstatic 74	com/c/b:g	Ljava/lang/String;
    //   1988: ldc_w 743
    //   1991: invokevirtual 387	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1994: ifeq +16 -> 2010
    //   1997: ldc -98
    //   1999: ldc_w 745
    //   2002: invokestatic 214	com/c/k:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   2005: iconst_1
    //   2006: istore_1
    //   2007: goto -1532 -> 475
    //   2010: getstatic 74	com/c/b:g	Ljava/lang/String;
    //   2013: invokestatic 533	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   2016: invokevirtual 749	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   2019: putstatic 74	com/c/b:g	Ljava/lang/String;
    //   2022: iconst_0
    //   2023: istore_1
    //   2024: goto -1549 -> 475
    //   2027: getstatic 74	com/c/b:g	Ljava/lang/String;
    //   2030: invokevirtual 191	java/lang/String:length	()I
    //   2033: ifeq +39 -> 2072
    //   2036: getstatic 74	com/c/b:g	Ljava/lang/String;
    //   2039: ldc_w 741
    //   2042: invokevirtual 387	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2045: ifne +27 -> 2072
    //   2048: getstatic 74	com/c/b:g	Ljava/lang/String;
    //   2051: ldc_w 743
    //   2054: invokevirtual 387	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2057: ifne +15 -> 2072
    //   2060: getstatic 74	com/c/b:g	Ljava/lang/String;
    //   2063: ldc_w 751
    //   2066: invokevirtual 387	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2069: ifeq +16 -> 2085
    //   2072: ldc -98
    //   2074: ldc_w 753
    //   2077: invokestatic 214	com/c/k:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   2080: iconst_1
    //   2081: istore_2
    //   2082: goto -1471 -> 611
    //   2085: getstatic 74	com/c/b:g	Ljava/lang/String;
    //   2088: invokevirtual 755	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   2091: putstatic 74	com/c/b:g	Ljava/lang/String;
    //   2094: iconst_0
    //   2095: istore_2
    //   2096: goto -1485 -> 611
    //   2099: iload_1
    //   2100: bipush 32
    //   2102: if_icmpge +33 -> 2135
    //   2105: aload 4
    //   2107: ldc_w 757
    //   2110: invokestatic 763	java/lang/Math:random	()D
    //   2113: ldc2_w 764
    //   2116: dmul
    //   2117: d2i
    //   2118: bipush 30
    //   2120: irem
    //   2121: invokevirtual 769	java/lang/String:charAt	(I)C
    //   2124: invokevirtual 772	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   2127: pop
    //   2128: iload_1
    //   2129: iconst_1
    //   2130: iadd
    //   2131: istore_1
    //   2132: goto -33 -> 2099
    //   2135: aload 4
    //   2137: invokevirtual 773	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   2140: invokevirtual 755	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   2143: putstatic 74	com/c/b:g	Ljava/lang/String;
    //   2146: aload_3
    //   2147: invokeinterface 777 1 0
    //   2152: astore 4
    //   2154: aload 4
    //   2156: ldc_w 602
    //   2159: getstatic 74	com/c/b:g	Ljava/lang/String;
    //   2162: invokeinterface 783 3 0
    //   2167: pop
    //   2168: aload 4
    //   2170: invokeinterface 786 1 0
    //   2175: pop
    //   2176: goto -1511 -> 665
    //   2179: astore 4
    //   2181: ldc -98
    //   2183: new 160	java/lang/StringBuilder
    //   2186: dup
    //   2187: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   2190: ldc_w 788
    //   2193: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2196: aload 4
    //   2198: invokevirtual 212	java/lang/Exception:toString	()Ljava/lang/String;
    //   2201: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2204: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2207: invokestatic 214	com/c/k:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   2210: goto -1447 -> 763
    //   2213: astore 4
    //   2215: ldc -98
    //   2217: new 160	java/lang/StringBuilder
    //   2220: dup
    //   2221: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   2224: ldc_w 790
    //   2227: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2230: aload 4
    //   2232: invokevirtual 212	java/lang/Exception:toString	()Ljava/lang/String;
    //   2235: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2238: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2241: invokestatic 214	com/c/k:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   2244: goto -1442 -> 802
    //   2247: ldc 70
    //   2249: astore_3
    //   2250: goto -1180 -> 1070
    //   2253: iload_1
    //   2254: iconst_1
    //   2255: iadd
    //   2256: istore_1
    //   2257: goto -2191 -> 66
    //   2260: iconst_0
    //   2261: istore_1
    //   2262: goto -163 -> 2099
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2265	0	this	b
    //   65	2197	1	i1	int
    //   63	2033	2	i2	int
    //   6	1716	3	localObject1	Object
    //   1840	307	3	localException1	Exception
    //   2249	1	3	str1	String
    //   40	1749	4	localObject2	Object
    //   1881	35	4	localException2	Exception
    //   1926	210	4	localException3	Exception
    //   2152	17	4	localEditor	android.content.SharedPreferences.Editor
    //   2179	18	4	localException4	Exception
    //   2213	18	4	localException5	Exception
    //   58	603	5	localObject3	Object
    //   75	91	6	str2	String
    //   106	62	7	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   212	309	1840	java/lang/Exception
    //   665	680	1840	java/lang/Exception
    //   680	689	1840	java/lang/Exception
    //   802	882	1840	java/lang/Exception
    //   882	893	1840	java/lang/Exception
    //   897	910	1840	java/lang/Exception
    //   910	1066	1840	java/lang/Exception
    //   1070	1712	1840	java/lang/Exception
    //   1712	1837	1840	java/lang/Exception
    //   1928	1961	1840	java/lang/Exception
    //   2181	2210	1840	java/lang/Exception
    //   2215	2244	1840	java/lang/Exception
    //   7	23	1881	java/lang/Exception
    //   27	42	1881	java/lang/Exception
    //   47	64	1881	java/lang/Exception
    //   77	108	1881	java/lang/Exception
    //   113	120	1881	java/lang/Exception
    //   125	173	1881	java/lang/Exception
    //   176	184	1881	java/lang/Exception
    //   1870	1878	1881	java/lang/Exception
    //   309	323	1926	java/lang/Exception
    //   328	358	1926	java/lang/Exception
    //   358	407	1926	java/lang/Exception
    //   407	432	1926	java/lang/Exception
    //   432	473	1926	java/lang/Exception
    //   475	502	1926	java/lang/Exception
    //   504	536	1926	java/lang/Exception
    //   540	546	1926	java/lang/Exception
    //   546	609	1926	java/lang/Exception
    //   615	645	1926	java/lang/Exception
    //   650	665	1926	java/lang/Exception
    //   1915	1923	1926	java/lang/Exception
    //   1964	1997	1926	java/lang/Exception
    //   1997	2005	1926	java/lang/Exception
    //   2010	2022	1926	java/lang/Exception
    //   2027	2072	1926	java/lang/Exception
    //   2072	2080	1926	java/lang/Exception
    //   2085	2094	1926	java/lang/Exception
    //   2105	2128	1926	java/lang/Exception
    //   2135	2176	1926	java/lang/Exception
    //   689	763	2179	java/lang/Exception
    //   763	777	2213	java/lang/Exception
    //   782	789	2213	java/lang/Exception
    //   794	802	2213	java/lang/Exception
  }
  
  public void b()
  {
    new Thread(new d(this)).start();
  }
  
  public void c(String paramString)
  {
    k.a("TapjoyConnect", "actionComplete: " + paramString);
    paramString = "app_id=" + paramString + "&";
    String str = paramString + k();
    if (d("sha_2_udid") != null)
    {
      paramString = str;
      if (d("sha_2_udid").equals("true")) {}
    }
    else
    {
      paramString = str + "&publisher_user_id=" + e();
    }
    paramString = paramString + "&";
    long l1 = System.currentTimeMillis() / 1000L;
    paramString = paramString + "timestamp=" + l1 + "&";
    paramString = paramString + "verifier=" + a(l1);
    k.a("TapjoyConnect", "PPA URL parameters: " + paramString);
    new Thread(new e(this, paramString)).start();
  }
}
