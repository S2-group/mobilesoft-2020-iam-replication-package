package com.gi.remoteconfig.c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.Vector;
import org.w3c.dom.Document;

public class b
{
  private static String A = "native";
  private static String B = "";
  private static boolean C = false;
  private static boolean D = true;
  private static String E = "";
  private static float F = 1.0F;
  private static String G = null;
  private static String J = "";
  private static Context a = null;
  private static b b = null;
  private static g c = null;
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
  private long H = 0L;
  private Timer I = null;
  
  public b(Context paramContext)
  {
    a = paramContext;
    c = new g();
    g();
    f.a("TapjoyConnect", "URL parameters: " + a());
    new Thread(new a()).start();
  }
  
  public static String a()
  {
    String str2 = b() + "&";
    long l1 = System.currentTimeMillis() / 1000L;
    String str1 = a(l1);
    str2 = str2 + "timestamp=" + l1 + "&";
    return str2 + "verifier=" + str1;
  }
  
  public static String a(long paramLong)
  {
    try
    {
      String str = h.a(l + ":" + e + ":" + paramLong + ":" + x);
      return str;
    }
    catch (Exception localException)
    {
      f.b("TapjoyConnect", "getVerifier ERROR: " + localException.toString());
    }
    return "";
  }
  
  public static String a(long paramLong, String paramString)
  {
    try
    {
      paramString = h.a(l + ":" + e + ":" + paramLong + ":" + x + ":" + paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      f.b("TapjoyConnect", "getVerifier ERROR: " + paramString.toString());
    }
    return "";
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    l = paramString1;
    x = paramString2;
    b = new b(paramContext);
  }
  
  public static void a(String paramString)
  {
    A = paramString;
  }
  
  public static String b()
  {
    String str = "" + "app_id=" + Uri.encode(l) + "&";
    return str + f();
  }
  
  public static void b(String paramString)
  {
    B = paramString;
  }
  
  public static String c()
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
          f.a("TapjoyConnect", "connectivity: " + localConnectivityManager.getActiveNetworkInfo().getType());
          str3 = str1;
          f.a("TapjoyConnect", "connection_type: " + str1);
          str4 = str1;
        }
      }
      catch (Exception localException)
      {
        f.b("TapjoyConnect", "getConnectionType error: " + localException.toString());
        return str3;
      }
      str1 = "wifi";
      continue;
      String str2 = "mobile";
    }
    return str4;
  }
  
  private static boolean d(String paramString)
  {
    paramString = h.b(paramString);
    if (paramString != null)
    {
      Object localObject = h.a(paramString.getElementsByTagName("PackageNames"));
      if ((localObject != null) && (((String)localObject).length() > 0))
      {
        Vector localVector = new Vector();
        int i2;
        for (int i1 = 0;; i1 = i2 + 1)
        {
          i2 = ((String)localObject).indexOf(',', i1);
          if (i2 == -1)
          {
            f.a("TapjoyConnect", "parse: " + ((String)localObject).substring(i1).trim());
            localVector.add(((String)localObject).substring(i1).trim());
            J = "";
            localObject = a.getPackageManager().getInstalledApplications(0).iterator();
            while (((Iterator)localObject).hasNext())
            {
              ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
              if (((localApplicationInfo.flags & 0x1) != 1) && (localVector.contains(localApplicationInfo.packageName)))
              {
                f.a("TapjoyConnect", "MATCH: installed packageName: " + localApplicationInfo.packageName);
                if (J.length() > 0) {
                  J += ",";
                }
                J += localApplicationInfo.packageName;
              }
            }
          }
          f.a("TapjoyConnect", "parse: " + ((String)localObject).substring(i1, i2).trim());
          localVector.add(((String)localObject).substring(i1, i2).trim());
        }
      }
      paramString = h.a(paramString.getElementsByTagName("Success"));
      if ((paramString == null) || (!paramString.equals("true"))) {}
    }
    return true;
  }
  
  private static String f()
  {
    Object localObject1 = "" + "android_id=" + d + "&";
    localObject1 = (String)localObject1 + "udid=" + Uri.encode(e) + "&";
    localObject1 = (String)localObject1 + "device_name=" + Uri.encode(f) + "&";
    localObject1 = (String)localObject1 + "device_manufacturer=" + Uri.encode(g) + "&";
    localObject1 = (String)localObject1 + "device_type=" + Uri.encode(h) + "&";
    localObject1 = (String)localObject1 + "os_version=" + Uri.encode(i) + "&";
    localObject1 = (String)localObject1 + "country_code=" + Uri.encode(j) + "&";
    localObject1 = (String)localObject1 + "language_code=" + Uri.encode(k) + "&";
    localObject1 = (String)localObject1 + "app_version=" + Uri.encode(m) + "&";
    localObject1 = (String)localObject1 + "library_version=" + Uri.encode(n) + "&";
    localObject1 = (String)localObject1 + "platform=" + Uri.encode(r) + "&";
    Object localObject2 = (String)localObject1 + "display_multiplier=" + Uri.encode(Float.toString(F));
    localObject1 = localObject2;
    if (s.length() > 0)
    {
      localObject1 = (String)localObject2 + "&";
      localObject1 = (String)localObject1 + "carrier_name=" + Uri.encode(s);
    }
    localObject2 = localObject1;
    if (t.length() > 0)
    {
      localObject1 = (String)localObject1 + "&";
      localObject2 = (String)localObject1 + "carrier_country_code=" + Uri.encode(t);
    }
    localObject1 = localObject2;
    if (u.length() > 0)
    {
      localObject1 = (String)localObject2 + "&";
      localObject1 = (String)localObject1 + "mobile_country_code=" + Uri.encode(u);
    }
    localObject2 = localObject1;
    if (v.length() > 0)
    {
      localObject1 = (String)localObject1 + "&";
      localObject2 = (String)localObject1 + "mobile_network_code=" + Uri.encode(v);
    }
    localObject1 = localObject2;
    if (o.length() > 0)
    {
      localObject1 = localObject2;
      if (p.length() > 0)
      {
        localObject1 = (String)localObject2 + "&";
        localObject1 = (String)localObject1 + "screen_density=" + Uri.encode(o) + "&";
        localObject1 = (String)localObject1 + "screen_layout_size=" + Uri.encode(p);
      }
    }
    w = c();
    localObject2 = localObject1;
    if (w.length() > 0)
    {
      localObject1 = (String)localObject1 + "&";
      localObject2 = (String)localObject1 + "connection_type=" + Uri.encode(w);
    }
    localObject1 = localObject2;
    if (A.length() > 0)
    {
      localObject1 = (String)localObject2 + "&";
      localObject1 = (String)localObject1 + "plugin=" + Uri.encode(A);
    }
    localObject2 = localObject1;
    if (B.length() > 0)
    {
      localObject1 = (String)localObject1 + "&";
      localObject2 = (String)localObject1 + "sdk_type=" + Uri.encode(B);
    }
    return localObject2;
  }
  
  /* Error */
  private void g()
  {
    // Byte code:
    //   0: getstatic 52	com/gi/remoteconfig/c/b:a	Landroid/content/Context;
    //   3: invokevirtual 299	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   6: astore_3
    //   7: getstatic 52	com/gi/remoteconfig/c/b:a	Landroid/content/Context;
    //   10: invokevirtual 399	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   13: ldc_w 401
    //   16: invokestatic 407	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   19: putstatic 60	com/gi/remoteconfig/c/b:d	Ljava/lang/String;
    //   22: aload_3
    //   23: getstatic 52	com/gi/remoteconfig/c/b:a	Landroid/content/Context;
    //   26: invokevirtual 410	android/content/Context:getPackageName	()Ljava/lang/String;
    //   29: iconst_0
    //   30: invokevirtual 414	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   33: getfield 419	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   36: putstatic 78	com/gi/remoteconfig/c/b:m	Ljava/lang/String;
    //   39: ldc_w 421
    //   42: putstatic 68	com/gi/remoteconfig/c/b:h	Ljava/lang/String;
    //   45: ldc_w 421
    //   48: putstatic 88	com/gi/remoteconfig/c/b:r	Ljava/lang/String;
    //   51: getstatic 426	android/os/Build:MODEL	Ljava/lang/String;
    //   54: putstatic 64	com/gi/remoteconfig/c/b:f	Ljava/lang/String;
    //   57: getstatic 429	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   60: putstatic 66	com/gi/remoteconfig/c/b:g	Ljava/lang/String;
    //   63: getstatic 434	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   66: putstatic 70	com/gi/remoteconfig/c/b:i	Ljava/lang/String;
    //   69: invokestatic 440	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   72: invokevirtual 443	java/util/Locale:getCountry	()Ljava/lang/String;
    //   75: putstatic 72	com/gi/remoteconfig/c/b:j	Ljava/lang/String;
    //   78: invokestatic 440	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   81: invokevirtual 446	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   84: putstatic 74	com/gi/remoteconfig/c/b:k	Ljava/lang/String;
    //   87: ldc_w 448
    //   90: putstatic 80	com/gi/remoteconfig/c/b:n	Ljava/lang/String;
    //   93: getstatic 52	com/gi/remoteconfig/c/b:a	Landroid/content/Context;
    //   96: ldc_w 450
    //   99: iconst_0
    //   100: invokevirtual 454	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   103: astore_3
    //   104: getstatic 52	com/gi/remoteconfig/c/b:a	Landroid/content/Context;
    //   107: ldc_w 456
    //   110: invokevirtual 227	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   113: checkcast 458	android/telephony/TelephonyManager
    //   116: astore 4
    //   118: aload 4
    //   120: ifnull +85 -> 205
    //   123: aload 4
    //   125: invokevirtual 461	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   128: putstatic 62	com/gi/remoteconfig/c/b:e	Ljava/lang/String;
    //   131: aload 4
    //   133: invokevirtual 464	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   136: putstatic 90	com/gi/remoteconfig/c/b:s	Ljava/lang/String;
    //   139: aload 4
    //   141: invokevirtual 467	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   144: putstatic 92	com/gi/remoteconfig/c/b:t	Ljava/lang/String;
    //   147: aload 4
    //   149: invokevirtual 470	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   152: ifnull +53 -> 205
    //   155: aload 4
    //   157: invokevirtual 470	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   160: invokevirtual 275	java/lang/String:length	()I
    //   163: iconst_5
    //   164: if_icmpeq +16 -> 180
    //   167: aload 4
    //   169: invokevirtual 470	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   172: invokevirtual 275	java/lang/String:length	()I
    //   175: bipush 6
    //   177: if_icmpne +28 -> 205
    //   180: aload 4
    //   182: invokevirtual 470	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   185: iconst_0
    //   186: iconst_3
    //   187: invokevirtual 339	java/lang/String:substring	(II)Ljava/lang/String;
    //   190: putstatic 94	com/gi/remoteconfig/c/b:u	Ljava/lang/String;
    //   193: aload 4
    //   195: invokevirtual 470	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   198: iconst_3
    //   199: invokevirtual 288	java/lang/String:substring	(I)Ljava/lang/String;
    //   202: putstatic 96	com/gi/remoteconfig/c/b:v	Ljava/lang/String;
    //   205: ldc -118
    //   207: new 140	java/lang/StringBuilder
    //   210: dup
    //   211: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   214: ldc_w 472
    //   217: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: getstatic 62	com/gi/remoteconfig/c/b:e	Ljava/lang/String;
    //   223: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   226: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   229: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   232: getstatic 62	com/gi/remoteconfig/c/b:e	Ljava/lang/String;
    //   235: ifnonnull +928 -> 1163
    //   238: ldc -118
    //   240: ldc_w 474
    //   243: invokestatic 205	com/gi/remoteconfig/c/f:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   246: iconst_1
    //   247: istore_1
    //   248: ldc -118
    //   250: new 140	java/lang/StringBuilder
    //   253: dup
    //   254: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   257: ldc_w 476
    //   260: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   263: getstatic 479	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   266: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   269: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   272: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   275: iload_1
    //   276: istore_2
    //   277: iload_1
    //   278: ifeq +102 -> 380
    //   281: iload_1
    //   282: istore_2
    //   283: getstatic 479	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   286: invokestatic 485	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   289: bipush 9
    //   291: if_icmplt +89 -> 380
    //   294: ldc -118
    //   296: ldc_w 487
    //   299: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   302: new 489	com/gi/remoteconfig/c/d
    //   305: dup
    //   306: invokespecial 490	com/gi/remoteconfig/c/d:<init>	()V
    //   309: invokevirtual 491	com/gi/remoteconfig/c/d:a	()Ljava/lang/String;
    //   312: putstatic 62	com/gi/remoteconfig/c/b:e	Ljava/lang/String;
    //   315: ldc -118
    //   317: ldc_w 493
    //   320: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   323: ldc -118
    //   325: new 140	java/lang/StringBuilder
    //   328: dup
    //   329: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   332: ldc_w 495
    //   335: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   338: getstatic 62	com/gi/remoteconfig/c/b:e	Ljava/lang/String;
    //   341: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   344: ldc_w 497
    //   347: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   350: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   353: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   356: ldc -118
    //   358: ldc_w 493
    //   361: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   364: getstatic 62	com/gi/remoteconfig/c/b:e	Ljava/lang/String;
    //   367: ifnonnull +856 -> 1223
    //   370: ldc -118
    //   372: ldc_w 499
    //   375: invokestatic 205	com/gi/remoteconfig/c/f:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   378: iconst_1
    //   379: istore_2
    //   380: iload_2
    //   381: ifeq +53 -> 434
    //   384: new 501	java/lang/StringBuffer
    //   387: dup
    //   388: invokespecial 502	java/lang/StringBuffer:<init>	()V
    //   391: astore 4
    //   393: aload 4
    //   395: ldc_w 504
    //   398: invokevirtual 507	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   401: pop
    //   402: aload_3
    //   403: ldc_w 509
    //   406: aconst_null
    //   407: invokeinterface 514 3 0
    //   412: astore 5
    //   414: aload 5
    //   416: ifnull +1041 -> 1457
    //   419: aload 5
    //   421: ldc 58
    //   423: invokevirtual 346	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   426: ifne +1031 -> 1457
    //   429: aload 5
    //   431: putstatic 62	com/gi/remoteconfig/c/b:e	Ljava/lang/String;
    //   434: getstatic 86	com/gi/remoteconfig/c/b:q	Ljava/lang/String;
    //   437: invokevirtual 275	java/lang/String:length	()I
    //   440: ifne +9 -> 449
    //   443: getstatic 62	com/gi/remoteconfig/c/b:e	Ljava/lang/String;
    //   446: putstatic 86	com/gi/remoteconfig/c/b:q	Ljava/lang/String;
    //   449: getstatic 479	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   452: invokestatic 485	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   455: iconst_3
    //   456: if_icmple +67 -> 523
    //   459: new 516	com/gi/remoteconfig/c/c
    //   462: dup
    //   463: getstatic 52	com/gi/remoteconfig/c/b:a	Landroid/content/Context;
    //   466: invokespecial 517	com/gi/remoteconfig/c/c:<init>	(Landroid/content/Context;)V
    //   469: astore 4
    //   471: new 140	java/lang/StringBuilder
    //   474: dup
    //   475: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   478: ldc 58
    //   480: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   483: aload 4
    //   485: invokevirtual 519	com/gi/remoteconfig/c/c:a	()I
    //   488: invokevirtual 244	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   491: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   494: putstatic 82	com/gi/remoteconfig/c/b:o	Ljava/lang/String;
    //   497: new 140	java/lang/StringBuilder
    //   500: dup
    //   501: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   504: ldc 58
    //   506: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   509: aload 4
    //   511: invokevirtual 521	com/gi/remoteconfig/c/c:b	()I
    //   514: invokevirtual 244	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   517: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   520: putstatic 84	com/gi/remoteconfig/c/b:p	Ljava/lang/String;
    //   523: aload_3
    //   524: ldc_w 523
    //   527: aconst_null
    //   528: invokeinterface 514 3 0
    //   533: astore_3
    //   534: aload_3
    //   535: ifnull +16 -> 551
    //   538: aload_3
    //   539: ldc 58
    //   541: invokevirtual 346	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   544: ifne +7 -> 551
    //   547: aload_3
    //   548: putstatic 104	com/gi/remoteconfig/c/b:z	Ljava/lang/String;
    //   551: getstatic 52	com/gi/remoteconfig/c/b:a	Landroid/content/Context;
    //   554: invokevirtual 410	android/content/Context:getPackageName	()Ljava/lang/String;
    //   557: putstatic 102	com/gi/remoteconfig/c/b:y	Ljava/lang/String;
    //   560: ldc -118
    //   562: ldc_w 525
    //   565: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   568: ldc -118
    //   570: new 140	java/lang/StringBuilder
    //   573: dup
    //   574: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   577: ldc_w 527
    //   580: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   583: getstatic 76	com/gi/remoteconfig/c/b:l	Ljava/lang/String;
    //   586: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   589: ldc_w 497
    //   592: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   595: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   598: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   601: ldc -118
    //   603: new 140	java/lang/StringBuilder
    //   606: dup
    //   607: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   610: ldc_w 529
    //   613: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   616: getstatic 60	com/gi/remoteconfig/c/b:d	Ljava/lang/String;
    //   619: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   622: ldc_w 497
    //   625: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   628: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   631: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   634: ldc -118
    //   636: new 140	java/lang/StringBuilder
    //   639: dup
    //   640: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   643: ldc_w 531
    //   646: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   649: getstatic 102	com/gi/remoteconfig/c/b:y	Ljava/lang/String;
    //   652: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   655: ldc_w 497
    //   658: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   661: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   664: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   667: ldc -118
    //   669: new 140	java/lang/StringBuilder
    //   672: dup
    //   673: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   676: ldc_w 533
    //   679: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   682: getstatic 62	com/gi/remoteconfig/c/b:e	Ljava/lang/String;
    //   685: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   688: ldc_w 497
    //   691: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   694: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   697: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   700: ldc -118
    //   702: new 140	java/lang/StringBuilder
    //   705: dup
    //   706: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   709: ldc_w 535
    //   712: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   715: getstatic 64	com/gi/remoteconfig/c/b:f	Ljava/lang/String;
    //   718: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   721: ldc_w 497
    //   724: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   727: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   730: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   733: ldc -118
    //   735: new 140	java/lang/StringBuilder
    //   738: dup
    //   739: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   742: ldc_w 537
    //   745: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   748: getstatic 66	com/gi/remoteconfig/c/b:g	Ljava/lang/String;
    //   751: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   754: ldc_w 497
    //   757: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   760: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   763: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   766: ldc -118
    //   768: new 140	java/lang/StringBuilder
    //   771: dup
    //   772: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   775: ldc_w 539
    //   778: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   781: getstatic 68	com/gi/remoteconfig/c/b:h	Ljava/lang/String;
    //   784: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   787: ldc_w 497
    //   790: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   793: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   796: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   799: ldc -118
    //   801: new 140	java/lang/StringBuilder
    //   804: dup
    //   805: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   808: ldc_w 541
    //   811: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   814: getstatic 80	com/gi/remoteconfig/c/b:n	Ljava/lang/String;
    //   817: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   820: ldc_w 497
    //   823: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   826: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   829: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   832: ldc -118
    //   834: new 140	java/lang/StringBuilder
    //   837: dup
    //   838: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   841: ldc_w 543
    //   844: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   847: getstatic 70	com/gi/remoteconfig/c/b:i	Ljava/lang/String;
    //   850: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   853: ldc_w 497
    //   856: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   859: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   862: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   865: ldc -118
    //   867: new 140	java/lang/StringBuilder
    //   870: dup
    //   871: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   874: ldc_w 545
    //   877: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   880: getstatic 72	com/gi/remoteconfig/c/b:j	Ljava/lang/String;
    //   883: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   886: ldc_w 497
    //   889: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   892: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   895: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   898: ldc -118
    //   900: new 140	java/lang/StringBuilder
    //   903: dup
    //   904: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   907: ldc_w 547
    //   910: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   913: getstatic 74	com/gi/remoteconfig/c/b:k	Ljava/lang/String;
    //   916: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   919: ldc_w 497
    //   922: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   925: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   928: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   931: ldc -118
    //   933: new 140	java/lang/StringBuilder
    //   936: dup
    //   937: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   940: ldc_w 549
    //   943: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   946: getstatic 82	com/gi/remoteconfig/c/b:o	Ljava/lang/String;
    //   949: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   952: ldc_w 497
    //   955: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   958: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   961: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   964: ldc -118
    //   966: new 140	java/lang/StringBuilder
    //   969: dup
    //   970: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   973: ldc_w 551
    //   976: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   979: getstatic 84	com/gi/remoteconfig/c/b:p	Ljava/lang/String;
    //   982: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   985: ldc_w 497
    //   988: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   991: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   994: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   997: ldc -118
    //   999: new 140	java/lang/StringBuilder
    //   1002: dup
    //   1003: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   1006: ldc_w 553
    //   1009: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1012: getstatic 90	com/gi/remoteconfig/c/b:s	Ljava/lang/String;
    //   1015: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1018: ldc_w 497
    //   1021: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1024: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1027: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1030: ldc -118
    //   1032: new 140	java/lang/StringBuilder
    //   1035: dup
    //   1036: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   1039: ldc_w 555
    //   1042: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1045: getstatic 92	com/gi/remoteconfig/c/b:t	Ljava/lang/String;
    //   1048: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1051: ldc_w 497
    //   1054: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1057: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1060: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1063: ldc -118
    //   1065: new 140	java/lang/StringBuilder
    //   1068: dup
    //   1069: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   1072: ldc_w 557
    //   1075: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1078: getstatic 94	com/gi/remoteconfig/c/b:u	Ljava/lang/String;
    //   1081: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1084: ldc_w 497
    //   1087: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1090: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1093: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1096: ldc -118
    //   1098: new 140	java/lang/StringBuilder
    //   1101: dup
    //   1102: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   1105: ldc_w 559
    //   1108: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1111: getstatic 96	com/gi/remoteconfig/c/b:v	Ljava/lang/String;
    //   1114: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1117: ldc_w 497
    //   1120: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1123: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1126: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1129: ldc -118
    //   1131: new 140	java/lang/StringBuilder
    //   1134: dup
    //   1135: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   1138: ldc_w 561
    //   1141: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1144: getstatic 104	com/gi/remoteconfig/c/b:z	Ljava/lang/String;
    //   1147: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1150: ldc_w 497
    //   1153: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1156: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1159: invokestatic 158	com/gi/remoteconfig/c/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1162: return
    //   1163: getstatic 62	com/gi/remoteconfig/c/b:e	Ljava/lang/String;
    //   1166: invokevirtual 275	java/lang/String:length	()I
    //   1169: ifeq +27 -> 1196
    //   1172: getstatic 62	com/gi/remoteconfig/c/b:e	Ljava/lang/String;
    //   1175: ldc_w 563
    //   1178: invokevirtual 346	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1181: ifne +15 -> 1196
    //   1184: getstatic 62	com/gi/remoteconfig/c/b:e	Ljava/lang/String;
    //   1187: ldc_w 565
    //   1190: invokevirtual 346	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1193: ifeq +16 -> 1209
    //   1196: ldc -118
    //   1198: ldc_w 567
    //   1201: invokestatic 205	com/gi/remoteconfig/c/f:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1204: iconst_1
    //   1205: istore_1
    //   1206: goto -958 -> 248
    //   1209: getstatic 62	com/gi/remoteconfig/c/b:e	Ljava/lang/String;
    //   1212: invokevirtual 570	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   1215: putstatic 62	com/gi/remoteconfig/c/b:e	Ljava/lang/String;
    //   1218: iconst_0
    //   1219: istore_1
    //   1220: goto -972 -> 248
    //   1223: getstatic 62	com/gi/remoteconfig/c/b:e	Ljava/lang/String;
    //   1226: invokevirtual 275	java/lang/String:length	()I
    //   1229: ifeq +39 -> 1268
    //   1232: getstatic 62	com/gi/remoteconfig/c/b:e	Ljava/lang/String;
    //   1235: ldc_w 563
    //   1238: invokevirtual 346	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1241: ifne +27 -> 1268
    //   1244: getstatic 62	com/gi/remoteconfig/c/b:e	Ljava/lang/String;
    //   1247: ldc_w 565
    //   1250: invokevirtual 346	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1253: ifne +15 -> 1268
    //   1256: getstatic 62	com/gi/remoteconfig/c/b:e	Ljava/lang/String;
    //   1259: ldc_w 572
    //   1262: invokevirtual 346	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1265: ifeq +16 -> 1281
    //   1268: ldc -118
    //   1270: ldc_w 574
    //   1273: invokestatic 205	com/gi/remoteconfig/c/f:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1276: iconst_1
    //   1277: istore_2
    //   1278: goto -898 -> 380
    //   1281: getstatic 62	com/gi/remoteconfig/c/b:e	Ljava/lang/String;
    //   1284: invokevirtual 570	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   1287: putstatic 62	com/gi/remoteconfig/c/b:e	Ljava/lang/String;
    //   1290: iconst_0
    //   1291: istore_2
    //   1292: goto -912 -> 380
    //   1295: iload_1
    //   1296: bipush 32
    //   1298: if_icmpge +33 -> 1331
    //   1301: aload 4
    //   1303: ldc_w 576
    //   1306: invokestatic 582	java/lang/Math:random	()D
    //   1309: ldc2_w 583
    //   1312: dmul
    //   1313: d2i
    //   1314: bipush 30
    //   1316: irem
    //   1317: invokevirtual 588	java/lang/String:charAt	(I)C
    //   1320: invokevirtual 591	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   1323: pop
    //   1324: iload_1
    //   1325: iconst_1
    //   1326: iadd
    //   1327: istore_1
    //   1328: goto -33 -> 1295
    //   1331: aload 4
    //   1333: invokevirtual 592	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   1336: invokevirtual 570	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   1339: putstatic 62	com/gi/remoteconfig/c/b:e	Ljava/lang/String;
    //   1342: aload_3
    //   1343: invokeinterface 596 1 0
    //   1348: astore 4
    //   1350: aload 4
    //   1352: ldc_w 509
    //   1355: getstatic 62	com/gi/remoteconfig/c/b:e	Ljava/lang/String;
    //   1358: invokeinterface 602 3 0
    //   1363: pop
    //   1364: aload 4
    //   1366: invokeinterface 605 1 0
    //   1371: pop
    //   1372: goto -938 -> 434
    //   1375: astore 4
    //   1377: ldc -118
    //   1379: new 140	java/lang/StringBuilder
    //   1382: dup
    //   1383: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   1386: ldc_w 607
    //   1389: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1392: aload 4
    //   1394: invokevirtual 203	java/lang/Exception:toString	()Ljava/lang/String;
    //   1397: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1400: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1403: invokestatic 205	com/gi/remoteconfig/c/f:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1406: aconst_null
    //   1407: putstatic 62	com/gi/remoteconfig/c/b:e	Ljava/lang/String;
    //   1410: goto -976 -> 434
    //   1413: astore_3
    //   1414: ldc -118
    //   1416: ldc_w 609
    //   1419: invokestatic 205	com/gi/remoteconfig/c/f:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1422: return
    //   1423: astore 4
    //   1425: ldc -118
    //   1427: new 140	java/lang/StringBuilder
    //   1430: dup
    //   1431: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   1434: ldc_w 611
    //   1437: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1440: aload 4
    //   1442: invokevirtual 203	java/lang/Exception:toString	()Ljava/lang/String;
    //   1445: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1448: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1451: invokestatic 205	com/gi/remoteconfig/c/f:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1454: goto -931 -> 523
    //   1457: iconst_0
    //   1458: istore_1
    //   1459: goto -164 -> 1295
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1462	0	this	b
    //   247	1212	1	i1	int
    //   276	1016	2	i2	int
    //   6	1337	3	localObject1	Object
    //   1413	1	3	localException1	Exception
    //   116	1249	4	localObject2	Object
    //   1375	18	4	localException2	Exception
    //   1423	18	4	localException3	Exception
    //   412	18	5	str	String
    // Exception table:
    //   from	to	target	type
    //   104	118	1375	java/lang/Exception
    //   123	180	1375	java/lang/Exception
    //   180	205	1375	java/lang/Exception
    //   205	246	1375	java/lang/Exception
    //   248	275	1375	java/lang/Exception
    //   283	378	1375	java/lang/Exception
    //   384	414	1375	java/lang/Exception
    //   419	434	1375	java/lang/Exception
    //   1163	1196	1375	java/lang/Exception
    //   1196	1204	1375	java/lang/Exception
    //   1209	1218	1375	java/lang/Exception
    //   1223	1268	1375	java/lang/Exception
    //   1268	1276	1375	java/lang/Exception
    //   1281	1290	1375	java/lang/Exception
    //   1301	1324	1375	java/lang/Exception
    //   1331	1372	1375	java/lang/Exception
    //   7	104	1413	java/lang/Exception
    //   434	449	1413	java/lang/Exception
    //   523	534	1413	java/lang/Exception
    //   538	551	1413	java/lang/Exception
    //   551	1162	1413	java/lang/Exception
    //   1377	1410	1413	java/lang/Exception
    //   1425	1454	1413	java/lang/Exception
    //   449	523	1423	java/lang/Exception
  }
  
  public class a
    implements Runnable
  {
    public a() {}
    
    public void run()
    {
      f.a("TapjoyConnect", "starting connect call...");
      Object localObject = b.a();
      localObject = b.d().a("https://ws.tapjoyads.com/connect?", (String)localObject);
      if ((localObject != null) && (((e)localObject).a == 200))
      {
        if (b.c(((e)localObject).c)) {
          f.a("TapjoyConnect", "Successfully connected to tapjoy site.");
        }
        if (b.e().length() > 0)
        {
          String str = b.b() + "&" + "package_names" + "=" + b.e() + "&";
          long l = System.currentTimeMillis() / 1000L;
          localObject = b.a(l, b.e());
          str = str + "timestamp=" + l + "&";
          localObject = str + "verifier=" + (String)localObject;
          localObject = b.d().a("https://ws.tapjoyads.com/apps_installed?", (String)localObject);
          if ((localObject != null) && (((e)localObject).a == 200)) {
            f.a("TapjoyConnect", "Successfully pinged sdkless api.");
          }
        }
      }
    }
  }
}
