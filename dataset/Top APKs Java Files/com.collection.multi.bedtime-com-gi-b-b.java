package com.gi.b;

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
    String str1 = b() + "&";
    long l1 = System.currentTimeMillis() / 1000L;
    String str2 = a(l1);
    return new StringBuilder(String.valueOf(str1)).append("timestamp=").append(l1).append("&").toString() + "verifier=" + str2;
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
    return new StringBuilder(String.valueOf("")).append("app_id=").append(Uri.encode(l)).append("&").toString() + f();
  }
  
  public static void b(String paramString)
  {
    B = paramString;
  }
  
  public static String c()
  {
    String str1 = "";
    String str3 = str1;
    for (;;)
    {
      try
      {
        ConnectivityManager localConnectivityManager = (ConnectivityManager)a.getSystemService("connectivity");
        if (localConnectivityManager == null) {
          break label152;
        }
        str3 = str1;
        if (localConnectivityManager.getActiveNetworkInfo() == null) {
          break label152;
        }
        str3 = str1;
        switch (localConnectivityManager.getActiveNetworkInfo().getType())
        {
        case 1: 
          str3 = str1;
          f.a("TapjoyConnect", "connectivity: " + localConnectivityManager.getActiveNetworkInfo().getType());
          str3 = str1;
          f.a("TapjoyConnect", "connection_type: " + str1);
          return str1;
        }
      }
      catch (Exception localException)
      {
        f.b("TapjoyConnect", "getConnectionType error: " + localException.toString());
        return str3;
      }
      str1 = "wifi";
      continue;
      label152:
      return "";
      String str2 = "mobile";
    }
  }
  
  private static boolean d(String paramString)
  {
    paramString = h.b(paramString);
    Object localObject;
    Vector localVector;
    int i1;
    int i2;
    if (paramString != null)
    {
      localObject = h.a(paramString.getElementsByTagName("PackageNames"));
      if ((localObject != null) && (((String)localObject).length() > 0))
      {
        localVector = new Vector();
        i1 = 0;
        i2 = ((String)localObject).indexOf(',', i1);
        if (i2 != -1) {
          break label165;
        }
        f.a("TapjoyConnect", "parse: " + ((String)localObject).substring(i1).trim());
        localVector.add(((String)localObject).substring(i1).trim());
        J = "";
        localObject = a.getPackageManager().getInstalledApplications(0).iterator();
      }
    }
    for (;;)
    {
      if (!((Iterator)localObject).hasNext())
      {
        paramString = h.a(paramString.getElementsByTagName("Success"));
        if ((paramString != null) && (paramString.equals("true"))) {}
        return true;
        label165:
        f.a("TapjoyConnect", "parse: " + ((String)localObject).substring(i1, i2).trim());
        localVector.add(((String)localObject).substring(i1, i2).trim());
        i1 = i2 + 1;
        break;
      }
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
  
  private static String f()
  {
    Object localObject2 = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("")).append("android_id=").append(d).append("&").toString())).append("udid=").append(Uri.encode(e)).append("&").toString())).append("device_name=").append(Uri.encode(f)).append("&").toString())).append("device_manufacturer=").append(Uri.encode(g)).append("&").toString())).append("device_type=").append(Uri.encode(h)).append("&").toString())).append("os_version=").append(Uri.encode(i)).append("&").toString())).append("country_code=").append(Uri.encode(j)).append("&").toString())).append("language_code=").append(Uri.encode(k)).append("&").toString())).append("app_version=").append(Uri.encode(m)).append("&").toString())).append("library_version=").append(Uri.encode(n)).append("&").toString())).append("platform=").append(Uri.encode(r)).append("&").toString() + "display_multiplier=" + Uri.encode(Float.toString(F));
    Object localObject1 = localObject2;
    if (s.length() > 0) {
      localObject1 = new StringBuilder(String.valueOf(localObject2)).append("&").toString() + "carrier_name=" + Uri.encode(s);
    }
    localObject2 = localObject1;
    if (t.length() > 0) {
      localObject2 = new StringBuilder(String.valueOf(localObject1)).append("&").toString() + "carrier_country_code=" + Uri.encode(t);
    }
    localObject1 = localObject2;
    if (u.length() > 0) {
      localObject1 = new StringBuilder(String.valueOf(localObject2)).append("&").toString() + "mobile_country_code=" + Uri.encode(u);
    }
    localObject2 = localObject1;
    if (v.length() > 0) {
      localObject2 = new StringBuilder(String.valueOf(localObject1)).append("&").toString() + "mobile_network_code=" + Uri.encode(v);
    }
    localObject1 = localObject2;
    if (o.length() > 0)
    {
      localObject1 = localObject2;
      if (p.length() > 0) {
        localObject1 = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(localObject2)).append("&").toString())).append("screen_density=").append(Uri.encode(o)).append("&").toString() + "screen_layout_size=" + Uri.encode(p);
      }
    }
    w = c();
    localObject2 = localObject1;
    if (w.length() > 0) {
      localObject2 = new StringBuilder(String.valueOf(localObject1)).append("&").toString() + "connection_type=" + Uri.encode(w);
    }
    localObject1 = localObject2;
    if (A.length() > 0) {
      localObject1 = new StringBuilder(String.valueOf(localObject2)).append("&").toString() + "plugin=" + Uri.encode(A);
    }
    localObject2 = localObject1;
    if (B.length() > 0) {
      localObject2 = new StringBuilder(String.valueOf(localObject1)).append("&").toString() + "sdk_type=" + Uri.encode(B);
    }
    return localObject2;
  }
  
  /* Error */
  private void g()
  {
    // Byte code:
    //   0: getstatic 52	com/gi/b/b:a	Landroid/content/Context;
    //   3: invokevirtual 304	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   6: astore_3
    //   7: getstatic 52	com/gi/b/b:a	Landroid/content/Context;
    //   10: invokevirtual 404	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   13: ldc_w 406
    //   16: invokestatic 412	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   19: putstatic 60	com/gi/b/b:d	Ljava/lang/String;
    //   22: aload_3
    //   23: getstatic 52	com/gi/b/b:a	Landroid/content/Context;
    //   26: invokevirtual 415	android/content/Context:getPackageName	()Ljava/lang/String;
    //   29: iconst_0
    //   30: invokevirtual 419	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   33: getfield 424	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   36: putstatic 78	com/gi/b/b:m	Ljava/lang/String;
    //   39: ldc_w 426
    //   42: putstatic 68	com/gi/b/b:h	Ljava/lang/String;
    //   45: ldc_w 426
    //   48: putstatic 88	com/gi/b/b:r	Ljava/lang/String;
    //   51: getstatic 431	android/os/Build:MODEL	Ljava/lang/String;
    //   54: putstatic 64	com/gi/b/b:f	Ljava/lang/String;
    //   57: getstatic 434	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   60: putstatic 66	com/gi/b/b:g	Ljava/lang/String;
    //   63: getstatic 439	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   66: putstatic 70	com/gi/b/b:i	Ljava/lang/String;
    //   69: invokestatic 445	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   72: invokevirtual 448	java/util/Locale:getCountry	()Ljava/lang/String;
    //   75: putstatic 72	com/gi/b/b:j	Ljava/lang/String;
    //   78: invokestatic 445	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   81: invokevirtual 451	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   84: putstatic 74	com/gi/b/b:k	Ljava/lang/String;
    //   87: ldc_w 453
    //   90: putstatic 80	com/gi/b/b:n	Ljava/lang/String;
    //   93: getstatic 52	com/gi/b/b:a	Landroid/content/Context;
    //   96: ldc_w 455
    //   99: iconst_0
    //   100: invokevirtual 459	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   103: astore_3
    //   104: getstatic 52	com/gi/b/b:a	Landroid/content/Context;
    //   107: ldc_w 461
    //   110: invokevirtual 234	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   113: checkcast 463	android/telephony/TelephonyManager
    //   116: astore 4
    //   118: aload 4
    //   120: ifnull +85 -> 205
    //   123: aload 4
    //   125: invokevirtual 466	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   128: putstatic 62	com/gi/b/b:e	Ljava/lang/String;
    //   131: aload 4
    //   133: invokevirtual 469	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   136: putstatic 90	com/gi/b/b:s	Ljava/lang/String;
    //   139: aload 4
    //   141: invokevirtual 472	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   144: putstatic 92	com/gi/b/b:t	Ljava/lang/String;
    //   147: aload 4
    //   149: invokevirtual 475	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   152: ifnull +53 -> 205
    //   155: aload 4
    //   157: invokevirtual 475	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   160: invokevirtual 280	java/lang/String:length	()I
    //   163: iconst_5
    //   164: if_icmpeq +16 -> 180
    //   167: aload 4
    //   169: invokevirtual 475	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   172: invokevirtual 280	java/lang/String:length	()I
    //   175: bipush 6
    //   177: if_icmpne +28 -> 205
    //   180: aload 4
    //   182: invokevirtual 475	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   185: iconst_0
    //   186: iconst_3
    //   187: invokevirtual 332	java/lang/String:substring	(II)Ljava/lang/String;
    //   190: putstatic 94	com/gi/b/b:u	Ljava/lang/String;
    //   193: aload 4
    //   195: invokevirtual 475	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   198: iconst_3
    //   199: invokevirtual 293	java/lang/String:substring	(I)Ljava/lang/String;
    //   202: putstatic 96	com/gi/b/b:v	Ljava/lang/String;
    //   205: ldc -118
    //   207: new 140	java/lang/StringBuilder
    //   210: dup
    //   211: ldc_w 477
    //   214: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   217: getstatic 62	com/gi/b/b:e	Ljava/lang/String;
    //   220: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   226: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   229: getstatic 62	com/gi/b/b:e	Ljava/lang/String;
    //   232: ifnonnull +858 -> 1090
    //   235: ldc -118
    //   237: ldc_w 479
    //   240: invokestatic 213	com/gi/b/f:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   243: iconst_1
    //   244: istore_1
    //   245: ldc -118
    //   247: new 140	java/lang/StringBuilder
    //   250: dup
    //   251: ldc_w 481
    //   254: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   257: getstatic 484	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   260: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   263: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   266: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   269: iload_1
    //   270: istore_2
    //   271: iload_1
    //   272: ifeq +99 -> 371
    //   275: iload_1
    //   276: istore_2
    //   277: getstatic 484	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   280: invokestatic 490	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   283: bipush 9
    //   285: if_icmplt +86 -> 371
    //   288: ldc -118
    //   290: ldc_w 492
    //   293: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   296: new 494	com/gi/b/d
    //   299: dup
    //   300: invokespecial 495	com/gi/b/d:<init>	()V
    //   303: invokevirtual 496	com/gi/b/d:a	()Ljava/lang/String;
    //   306: putstatic 62	com/gi/b/b:e	Ljava/lang/String;
    //   309: ldc -118
    //   311: ldc_w 498
    //   314: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   317: ldc -118
    //   319: new 140	java/lang/StringBuilder
    //   322: dup
    //   323: ldc_w 500
    //   326: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   329: getstatic 62	com/gi/b/b:e	Ljava/lang/String;
    //   332: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   335: ldc_w 502
    //   338: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   341: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   344: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   347: ldc -118
    //   349: ldc_w 498
    //   352: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   355: getstatic 62	com/gi/b/b:e	Ljava/lang/String;
    //   358: ifnonnull +792 -> 1150
    //   361: ldc -118
    //   363: ldc_w 504
    //   366: invokestatic 213	com/gi/b/f:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   369: iconst_1
    //   370: istore_2
    //   371: iload_2
    //   372: ifeq +53 -> 425
    //   375: new 506	java/lang/StringBuffer
    //   378: dup
    //   379: invokespecial 507	java/lang/StringBuffer:<init>	()V
    //   382: astore 4
    //   384: aload 4
    //   386: ldc_w 509
    //   389: invokevirtual 512	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   392: pop
    //   393: aload_3
    //   394: ldc_w 514
    //   397: aconst_null
    //   398: invokeinterface 519 3 0
    //   403: astore 5
    //   405: aload 5
    //   407: ifnull +971 -> 1378
    //   410: aload 5
    //   412: ldc 58
    //   414: invokevirtual 329	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   417: ifne +961 -> 1378
    //   420: aload 5
    //   422: putstatic 62	com/gi/b/b:e	Ljava/lang/String;
    //   425: getstatic 86	com/gi/b/b:q	Ljava/lang/String;
    //   428: invokevirtual 280	java/lang/String:length	()I
    //   431: ifne +9 -> 440
    //   434: getstatic 62	com/gi/b/b:e	Ljava/lang/String;
    //   437: putstatic 86	com/gi/b/b:q	Ljava/lang/String;
    //   440: getstatic 484	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   443: invokestatic 490	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   446: iconst_3
    //   447: if_icmple +57 -> 504
    //   450: new 521	com/gi/b/c
    //   453: dup
    //   454: getstatic 52	com/gi/b/b:a	Landroid/content/Context;
    //   457: invokespecial 522	com/gi/b/c:<init>	(Landroid/content/Context;)V
    //   460: astore 4
    //   462: new 140	java/lang/StringBuilder
    //   465: dup
    //   466: invokespecial 523	java/lang/StringBuilder:<init>	()V
    //   469: aload 4
    //   471: invokevirtual 525	com/gi/b/c:a	()I
    //   474: invokevirtual 251	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   477: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   480: putstatic 82	com/gi/b/b:o	Ljava/lang/String;
    //   483: new 140	java/lang/StringBuilder
    //   486: dup
    //   487: invokespecial 523	java/lang/StringBuilder:<init>	()V
    //   490: aload 4
    //   492: invokevirtual 527	com/gi/b/c:b	()I
    //   495: invokevirtual 251	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   498: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   501: putstatic 84	com/gi/b/b:p	Ljava/lang/String;
    //   504: aload_3
    //   505: ldc_w 529
    //   508: aconst_null
    //   509: invokeinterface 519 3 0
    //   514: astore_3
    //   515: aload_3
    //   516: ifnull +16 -> 532
    //   519: aload_3
    //   520: ldc 58
    //   522: invokevirtual 329	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   525: ifne +7 -> 532
    //   528: aload_3
    //   529: putstatic 104	com/gi/b/b:z	Ljava/lang/String;
    //   532: getstatic 52	com/gi/b/b:a	Landroid/content/Context;
    //   535: invokevirtual 415	android/content/Context:getPackageName	()Ljava/lang/String;
    //   538: putstatic 102	com/gi/b/b:y	Ljava/lang/String;
    //   541: ldc -118
    //   543: ldc_w 531
    //   546: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   549: ldc -118
    //   551: new 140	java/lang/StringBuilder
    //   554: dup
    //   555: ldc_w 533
    //   558: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   561: getstatic 76	com/gi/b/b:l	Ljava/lang/String;
    //   564: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   567: ldc_w 502
    //   570: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   573: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   576: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   579: ldc -118
    //   581: new 140	java/lang/StringBuilder
    //   584: dup
    //   585: ldc_w 535
    //   588: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   591: getstatic 60	com/gi/b/b:d	Ljava/lang/String;
    //   594: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   597: ldc_w 502
    //   600: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   603: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   606: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   609: ldc -118
    //   611: new 140	java/lang/StringBuilder
    //   614: dup
    //   615: ldc_w 537
    //   618: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   621: getstatic 102	com/gi/b/b:y	Ljava/lang/String;
    //   624: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   627: ldc_w 502
    //   630: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   633: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   636: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   639: ldc -118
    //   641: new 140	java/lang/StringBuilder
    //   644: dup
    //   645: ldc_w 539
    //   648: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   651: getstatic 62	com/gi/b/b:e	Ljava/lang/String;
    //   654: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   657: ldc_w 502
    //   660: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   663: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   666: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   669: ldc -118
    //   671: new 140	java/lang/StringBuilder
    //   674: dup
    //   675: ldc_w 541
    //   678: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   681: getstatic 64	com/gi/b/b:f	Ljava/lang/String;
    //   684: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   687: ldc_w 502
    //   690: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   693: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   696: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   699: ldc -118
    //   701: new 140	java/lang/StringBuilder
    //   704: dup
    //   705: ldc_w 543
    //   708: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   711: getstatic 66	com/gi/b/b:g	Ljava/lang/String;
    //   714: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   717: ldc_w 502
    //   720: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   723: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   726: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   729: ldc -118
    //   731: new 140	java/lang/StringBuilder
    //   734: dup
    //   735: ldc_w 545
    //   738: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   741: getstatic 68	com/gi/b/b:h	Ljava/lang/String;
    //   744: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   747: ldc_w 502
    //   750: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   753: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   756: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   759: ldc -118
    //   761: new 140	java/lang/StringBuilder
    //   764: dup
    //   765: ldc_w 547
    //   768: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   771: getstatic 80	com/gi/b/b:n	Ljava/lang/String;
    //   774: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   777: ldc_w 502
    //   780: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   783: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   786: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   789: ldc -118
    //   791: new 140	java/lang/StringBuilder
    //   794: dup
    //   795: ldc_w 549
    //   798: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   801: getstatic 70	com/gi/b/b:i	Ljava/lang/String;
    //   804: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   807: ldc_w 502
    //   810: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   813: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   816: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   819: ldc -118
    //   821: new 140	java/lang/StringBuilder
    //   824: dup
    //   825: ldc_w 551
    //   828: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   831: getstatic 72	com/gi/b/b:j	Ljava/lang/String;
    //   834: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   837: ldc_w 502
    //   840: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   843: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   846: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   849: ldc -118
    //   851: new 140	java/lang/StringBuilder
    //   854: dup
    //   855: ldc_w 553
    //   858: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   861: getstatic 74	com/gi/b/b:k	Ljava/lang/String;
    //   864: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   867: ldc_w 502
    //   870: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   873: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   876: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   879: ldc -118
    //   881: new 140	java/lang/StringBuilder
    //   884: dup
    //   885: ldc_w 555
    //   888: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   891: getstatic 82	com/gi/b/b:o	Ljava/lang/String;
    //   894: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   897: ldc_w 502
    //   900: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   903: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   906: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   909: ldc -118
    //   911: new 140	java/lang/StringBuilder
    //   914: dup
    //   915: ldc_w 557
    //   918: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   921: getstatic 84	com/gi/b/b:p	Ljava/lang/String;
    //   924: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   927: ldc_w 502
    //   930: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   933: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   936: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   939: ldc -118
    //   941: new 140	java/lang/StringBuilder
    //   944: dup
    //   945: ldc_w 559
    //   948: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   951: getstatic 90	com/gi/b/b:s	Ljava/lang/String;
    //   954: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   957: ldc_w 502
    //   960: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   963: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   966: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   969: ldc -118
    //   971: new 140	java/lang/StringBuilder
    //   974: dup
    //   975: ldc_w 561
    //   978: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   981: getstatic 92	com/gi/b/b:t	Ljava/lang/String;
    //   984: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   987: ldc_w 502
    //   990: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   993: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   996: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   999: ldc -118
    //   1001: new 140	java/lang/StringBuilder
    //   1004: dup
    //   1005: ldc_w 563
    //   1008: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1011: getstatic 94	com/gi/b/b:u	Ljava/lang/String;
    //   1014: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1017: ldc_w 502
    //   1020: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1023: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1026: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1029: ldc -118
    //   1031: new 140	java/lang/StringBuilder
    //   1034: dup
    //   1035: ldc_w 565
    //   1038: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1041: getstatic 96	com/gi/b/b:v	Ljava/lang/String;
    //   1044: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1047: ldc_w 502
    //   1050: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1053: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1056: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1059: ldc -118
    //   1061: new 140	java/lang/StringBuilder
    //   1064: dup
    //   1065: ldc_w 567
    //   1068: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1071: getstatic 104	com/gi/b/b:z	Ljava/lang/String;
    //   1074: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1077: ldc_w 502
    //   1080: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1083: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1086: invokestatic 160	com/gi/b/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1089: return
    //   1090: getstatic 62	com/gi/b/b:e	Ljava/lang/String;
    //   1093: invokevirtual 280	java/lang/String:length	()I
    //   1096: ifeq +27 -> 1123
    //   1099: getstatic 62	com/gi/b/b:e	Ljava/lang/String;
    //   1102: ldc_w 569
    //   1105: invokevirtual 329	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1108: ifne +15 -> 1123
    //   1111: getstatic 62	com/gi/b/b:e	Ljava/lang/String;
    //   1114: ldc_w 571
    //   1117: invokevirtual 329	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1120: ifeq +16 -> 1136
    //   1123: ldc -118
    //   1125: ldc_w 573
    //   1128: invokestatic 213	com/gi/b/f:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1131: iconst_1
    //   1132: istore_1
    //   1133: goto -888 -> 245
    //   1136: getstatic 62	com/gi/b/b:e	Ljava/lang/String;
    //   1139: invokevirtual 576	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   1142: putstatic 62	com/gi/b/b:e	Ljava/lang/String;
    //   1145: iconst_0
    //   1146: istore_1
    //   1147: goto -902 -> 245
    //   1150: getstatic 62	com/gi/b/b:e	Ljava/lang/String;
    //   1153: invokevirtual 280	java/lang/String:length	()I
    //   1156: ifeq +39 -> 1195
    //   1159: getstatic 62	com/gi/b/b:e	Ljava/lang/String;
    //   1162: ldc_w 569
    //   1165: invokevirtual 329	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1168: ifne +27 -> 1195
    //   1171: getstatic 62	com/gi/b/b:e	Ljava/lang/String;
    //   1174: ldc_w 571
    //   1177: invokevirtual 329	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1180: ifne +15 -> 1195
    //   1183: getstatic 62	com/gi/b/b:e	Ljava/lang/String;
    //   1186: ldc_w 578
    //   1189: invokevirtual 329	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1192: ifeq +16 -> 1208
    //   1195: ldc -118
    //   1197: ldc_w 580
    //   1200: invokestatic 213	com/gi/b/f:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1203: iconst_1
    //   1204: istore_2
    //   1205: goto -834 -> 371
    //   1208: getstatic 62	com/gi/b/b:e	Ljava/lang/String;
    //   1211: invokevirtual 576	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   1214: putstatic 62	com/gi/b/b:e	Ljava/lang/String;
    //   1217: iconst_0
    //   1218: istore_2
    //   1219: goto -848 -> 371
    //   1222: iload_1
    //   1223: bipush 32
    //   1225: if_icmplt +92 -> 1317
    //   1228: aload 4
    //   1230: invokevirtual 581	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   1233: invokevirtual 576	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   1236: putstatic 62	com/gi/b/b:e	Ljava/lang/String;
    //   1239: aload_3
    //   1240: invokeinterface 585 1 0
    //   1245: astore 4
    //   1247: aload 4
    //   1249: ldc_w 514
    //   1252: getstatic 62	com/gi/b/b:e	Ljava/lang/String;
    //   1255: invokeinterface 591 3 0
    //   1260: pop
    //   1261: aload 4
    //   1263: invokeinterface 594 1 0
    //   1268: pop
    //   1269: goto -844 -> 425
    //   1272: astore 4
    //   1274: ldc -118
    //   1276: new 140	java/lang/StringBuilder
    //   1279: dup
    //   1280: ldc_w 596
    //   1283: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1286: aload 4
    //   1288: invokevirtual 211	java/lang/Exception:toString	()Ljava/lang/String;
    //   1291: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1294: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1297: invokestatic 213	com/gi/b/f:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1300: aconst_null
    //   1301: putstatic 62	com/gi/b/b:e	Ljava/lang/String;
    //   1304: goto -879 -> 425
    //   1307: astore_3
    //   1308: ldc -118
    //   1310: ldc_w 598
    //   1313: invokestatic 213	com/gi/b/f:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1316: return
    //   1317: aload 4
    //   1319: ldc_w 600
    //   1322: invokestatic 606	java/lang/Math:random	()D
    //   1325: ldc2_w 607
    //   1328: dmul
    //   1329: d2i
    //   1330: bipush 30
    //   1332: irem
    //   1333: invokevirtual 612	java/lang/String:charAt	(I)C
    //   1336: invokevirtual 615	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   1339: pop
    //   1340: iload_1
    //   1341: iconst_1
    //   1342: iadd
    //   1343: istore_1
    //   1344: goto -122 -> 1222
    //   1347: astore 4
    //   1349: ldc -118
    //   1351: new 140	java/lang/StringBuilder
    //   1354: dup
    //   1355: ldc_w 617
    //   1358: invokespecial 145	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1361: aload 4
    //   1363: invokevirtual 211	java/lang/Exception:toString	()Ljava/lang/String;
    //   1366: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1369: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1372: invokestatic 213	com/gi/b/f:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1375: goto -871 -> 504
    //   1378: iconst_0
    //   1379: istore_1
    //   1380: goto -158 -> 1222
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1383	0	this	b
    //   244	1136	1	i1	int
    //   270	949	2	i2	int
    //   6	1234	3	localObject1	Object
    //   1307	1	3	localException1	Exception
    //   116	1146	4	localObject2	Object
    //   1272	46	4	localException2	Exception
    //   1347	15	4	localException3	Exception
    //   403	18	5	str	String
    // Exception table:
    //   from	to	target	type
    //   104	118	1272	java/lang/Exception
    //   123	180	1272	java/lang/Exception
    //   180	205	1272	java/lang/Exception
    //   205	243	1272	java/lang/Exception
    //   245	269	1272	java/lang/Exception
    //   277	369	1272	java/lang/Exception
    //   375	405	1272	java/lang/Exception
    //   410	425	1272	java/lang/Exception
    //   1090	1123	1272	java/lang/Exception
    //   1123	1131	1272	java/lang/Exception
    //   1136	1145	1272	java/lang/Exception
    //   1150	1195	1272	java/lang/Exception
    //   1195	1203	1272	java/lang/Exception
    //   1208	1217	1272	java/lang/Exception
    //   1228	1269	1272	java/lang/Exception
    //   1317	1340	1272	java/lang/Exception
    //   7	104	1307	java/lang/Exception
    //   425	440	1307	java/lang/Exception
    //   504	515	1307	java/lang/Exception
    //   519	532	1307	java/lang/Exception
    //   532	1089	1307	java/lang/Exception
    //   1274	1304	1307	java/lang/Exception
    //   1349	1375	1307	java/lang/Exception
    //   440	504	1347	java/lang/Exception
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
          localObject = b.b() + "&" + "package_names" + "=" + b.e() + "&";
          long l = System.currentTimeMillis() / 1000L;
          String str = b.a(l, b.e());
          localObject = new StringBuilder(String.valueOf(localObject)).append("timestamp=").append(l).append("&").toString() + "verifier=" + str;
          localObject = b.d().a("https://ws.tapjoyads.com/apps_installed?", (String)localObject);
          if ((localObject != null) && (((e)localObject).a == 200)) {
            f.a("TapjoyConnect", "Successfully pinged sdkless api.");
          }
        }
      }
    }
  }
}
