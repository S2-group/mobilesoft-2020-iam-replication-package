package com.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
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
  private static String G = "";
  private static String H = "native";
  private static String I = "";
  private static boolean J = false;
  private static String K = "";
  private static float L = 1.0F;
  private static String M = null;
  private static Hashtable<String, String> P = null;
  private static String Q = "";
  public static final String a = "TapjoyConnect";
  private static Context b = null;
  private static b c = null;
  private static j d = null;
  private static d e = null;
  private static l f = null;
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
  private long N = 0L;
  private Timer O = null;
  
  public b(Context paramContext)
  {
    b = paramContext;
    d = new j();
    w();
    i.a("TapjoyConnect", "URL parameters: " + c());
    b();
    if ((i("user_id") != null) && (i("user_id").length() > 0))
    {
      i.a("TapjoyConnect", "Setting userID to: " + i("user_id"));
      d(i("user_id"));
    }
  }
  
  public static b a()
  {
    return c;
  }
  
  public static String a(long paramLong)
  {
    try
    {
      String str = k.b(r + ":" + h + ":" + paramLong + ":" + E);
      return str;
    }
    catch (Exception localException)
    {
      i.b("TapjoyConnect", "getVerifier ERROR: " + localException.toString());
    }
    return "";
  }
  
  public static String a(long paramLong, int paramInt, String paramString)
  {
    try
    {
      paramString = k.b(r + ":" + h + ":" + paramLong + ":" + E + ":" + paramInt + ":" + paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      i.b("TapjoyConnect", "getAwardPointsVerifier ERROR: " + paramString.toString());
    }
    return "";
  }
  
  public static String a(long paramLong, String paramString)
  {
    try
    {
      paramString = k.b(r + ":" + h + ":" + paramLong + ":" + E + ":" + paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      i.b("TapjoyConnect", "getVerifier ERROR: " + paramString.toString());
    }
    return "";
  }
  
  public static String a(String paramString)
  {
    try
    {
      paramString = k.b(r + ":" + h + ":" + E + ":" + paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      i.b("TapjoyConnect", "getEventVerifier ERROR: " + paramString.toString());
    }
    return "";
  }
  
  public static void a(int paramInt)
  {
    if (f != null) {
      f.a(paramInt);
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    a(paramContext, paramString1, paramString2, null);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, Hashtable<String, String> paramHashtable)
  {
    a(paramContext, paramString1, paramString2, paramHashtable, null);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, Hashtable<String, String> paramHashtable, d paramD)
  {
    r = paramString1;
    E = paramString2;
    P = paramHashtable;
    e = paramD;
    c = new b(paramContext);
  }
  
  public static void a(boolean paramBoolean)
  {
    J = paramBoolean;
  }
  
  public static void b(int paramInt)
  {
    if (f != null) {
      f.b(paramInt);
    }
  }
  
  public static void b(String paramString)
  {
    H = paramString;
  }
  
  public static String c()
  {
    String str2 = d() + "&";
    long l1 = System.currentTimeMillis() / 1000L;
    String str1 = a(l1);
    str2 = str2 + "timestamp=" + l1 + "&";
    return str2 + "verifier=" + str1;
  }
  
  public static void c(int paramInt)
  {
    if (f != null) {
      f.c(paramInt);
    }
  }
  
  public static void c(String paramString)
  {
    I = paramString;
  }
  
  public static String d()
  {
    String str = "" + "app_id=" + Uri.encode(r) + "&";
    return str + v();
  }
  
  public static void d(int paramInt)
  {
    if (f != null) {
      f.d(paramInt);
    }
  }
  
  public static void d(String paramString)
  {
    w = paramString;
    i.a("TapjoyConnect", "URL parameters: " + c());
    new Thread(new Runnable()
    {
      public void run()
      {
        i.a("TapjoyConnect", "setUserID...");
        Object localObject = b.c();
        String str = (String)localObject + "&publisher_user_id=" + b.h();
        localObject = str;
        if (!b.r().equals("")) {
          localObject = str + "&" + b.r();
        }
        localObject = b.s().b("https://ws.tapjoyads.com/set_publisher_user_id?", (String)localObject);
        if (localObject != null)
        {
          if (b.j((String)localObject)) {}
          i.a("TapjoyConnect", "setUserID successful...");
        }
      }
    }).start();
  }
  
  public static void e(int paramInt)
  {
    SharedPreferences.Editor localEditor = b.getSharedPreferences("tjcPrefrences", 0).edit();
    localEditor.putInt("last_tap_points", paramInt);
    localEditor.commit();
  }
  
  public static void e(String paramString)
  {
    K = paramString;
  }
  
  public static String f()
  {
    return r;
  }
  
  public static void f(String paramString)
  {
    h = paramString;
    paramString = b.getSharedPreferences("tjcPrefrences", 0).edit();
    paramString.putString("emulatorDeviceId", h);
    paramString.commit();
  }
  
  public static String g()
  {
    return h;
  }
  
  public static String h()
  {
    return w;
  }
  
  public static String i()
  {
    String str = "";
    if (J) {
      if (K.length() <= 0) {}
    }
    for (str = "video_offer_ids=" + K;; str = "hide_videos=true")
    {
      i.a("TapjoyConnect", "video parameters: " + str);
      return str;
    }
  }
  
  public static String i(String paramString)
  {
    String str = "";
    if (P != null) {
      str = (String)P.get(paramString);
    }
    paramString = str;
    if (str == null) {
      paramString = "";
    }
    return paramString;
  }
  
  public static String j()
  {
    return y;
  }
  
  public static String k()
  {
    String str1 = "";
    String str3 = str1;
    for (;;)
    {
      try
      {
        ConnectivityManager localConnectivityManager = (ConnectivityManager)b.getSystemService("connectivity");
        if (localConnectivityManager == null) {
          break;
        }
        str3 = str1;
        if (localConnectivityManager.getActiveNetworkInfo() == null) {
          break;
        }
        str3 = str1;
        switch (localConnectivityManager.getActiveNetworkInfo().getType())
        {
        case 1: 
          str3 = str1;
          i.a("TapjoyConnect", "connectivity: " + localConnectivityManager.getActiveNetworkInfo().getType());
          str3 = str1;
          i.a("TapjoyConnect", "connection_type: " + str1);
          return str1;
        }
      }
      catch (Exception localException)
      {
        i.b("TapjoyConnect", "getConnectionType error: " + localException.toString());
        return str3;
      }
      str1 = "wifi";
      continue;
      String str2 = "mobile";
    }
    return "";
  }
  
  private static boolean k(String paramString)
  {
    paramString = k.c(paramString);
    if (paramString != null)
    {
      Object localObject = k.a(paramString.getElementsByTagName("PackageNames"));
      if ((localObject != null) && (((String)localObject).length() > 0))
      {
        Vector localVector = new Vector();
        int i2;
        for (int i1 = 0;; i1 = i2 + 1)
        {
          i2 = ((String)localObject).indexOf(',', i1);
          if (i2 == -1)
          {
            i.a("TapjoyConnect", "parse: " + ((String)localObject).substring(i1).trim());
            localVector.add(((String)localObject).substring(i1).trim());
            Q = "";
            localObject = b.getPackageManager().getInstalledApplications(0).iterator();
            while (((Iterator)localObject).hasNext())
            {
              ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
              if (((localApplicationInfo.flags & 0x1) != 1) && (localVector.contains(localApplicationInfo.packageName)))
              {
                i.a("TapjoyConnect", "MATCH: installed packageName: " + localApplicationInfo.packageName);
                if (Q.length() > 0) {
                  Q += ",";
                }
                Q += localApplicationInfo.packageName;
              }
            }
          }
          i.a("TapjoyConnect", "parse: " + ((String)localObject).substring(i1, i2).trim());
          localVector.add(((String)localObject).substring(i1, i2).trim());
        }
      }
      paramString = k.a(paramString.getElementsByTagName("Success"));
      if ((paramString == null) || (!paramString.equals("true"))) {}
    }
    return true;
  }
  
  public static String l()
  {
    return F;
  }
  
  private boolean l(String paramString)
  {
    paramString = k.c(paramString);
    if (paramString != null)
    {
      paramString = k.a(paramString.getElementsByTagName("Success"));
      if ((paramString != null) && (paramString.equals("true")))
      {
        i.a("TapjoyConnect", "Successfully sent completed Pay-Per-Action to Tapjoy server.");
        return true;
      }
      i.b("TapjoyConnect", "Completed Pay-Per-Action call failed.");
    }
    return false;
  }
  
  public static Context m()
  {
    return b;
  }
  
  public static int n()
  {
    return b.getSharedPreferences("tjcPrefrences", 0).getInt("last_tap_points", 55537);
  }
  
  private static String v()
  {
    Object localObject1 = "" + "android_id=" + g + "&";
    if ((i("sha_2_udid") != null) && (i("sha_2_udid").equals("true"))) {}
    for (Object localObject2 = (String)localObject1 + "sha2_udid=" + Uri.encode(i) + "&";; localObject2 = (String)localObject1 + "udid=" + Uri.encode(h) + "&")
    {
      localObject1 = localObject2;
      if (j != null)
      {
        localObject1 = localObject2;
        if (j.length() > 0) {
          localObject1 = (String)localObject2 + "mac_address=" + Uri.encode(j) + "&";
        }
      }
      localObject2 = localObject1;
      if (k != null)
      {
        localObject2 = localObject1;
        if (k.length() > 0) {
          localObject2 = (String)localObject1 + "serial_id=" + Uri.encode(k) + "&";
        }
      }
      localObject1 = (String)localObject2 + "device_name=" + Uri.encode(l) + "&";
      localObject1 = (String)localObject1 + "device_manufacturer=" + Uri.encode(m) + "&";
      localObject1 = (String)localObject1 + "device_type=" + Uri.encode(n) + "&";
      localObject1 = (String)localObject1 + "os_version=" + Uri.encode(o) + "&";
      localObject1 = (String)localObject1 + "country_code=" + Uri.encode(p) + "&";
      localObject1 = (String)localObject1 + "language_code=" + Uri.encode(q) + "&";
      localObject1 = (String)localObject1 + "app_version=" + Uri.encode(s) + "&";
      localObject1 = (String)localObject1 + "library_version=" + Uri.encode(t) + "&";
      localObject1 = (String)localObject1 + "platform=" + Uri.encode(x) + "&";
      localObject2 = (String)localObject1 + "display_multiplier=" + Uri.encode(Float.toString(L));
      localObject1 = localObject2;
      if (y.length() > 0)
      {
        localObject1 = (String)localObject2 + "&";
        localObject1 = (String)localObject1 + "carrier_name=" + Uri.encode(y);
      }
      localObject2 = localObject1;
      if (z.length() > 0)
      {
        localObject1 = (String)localObject1 + "&";
        localObject2 = (String)localObject1 + "carrier_country_code=" + Uri.encode(z);
      }
      localObject1 = localObject2;
      if (A.length() > 0)
      {
        localObject1 = (String)localObject2 + "&";
        localObject1 = (String)localObject1 + "mobile_country_code=" + Uri.encode(A);
      }
      localObject2 = localObject1;
      if (B.length() > 0)
      {
        localObject1 = (String)localObject1 + "&";
        localObject2 = (String)localObject1 + "mobile_network_code=" + Uri.encode(B);
      }
      localObject1 = localObject2;
      if (u.length() > 0)
      {
        localObject1 = localObject2;
        if (v.length() > 0)
        {
          localObject1 = (String)localObject2 + "&";
          localObject1 = (String)localObject1 + "screen_density=" + Uri.encode(u) + "&";
          localObject1 = (String)localObject1 + "screen_layout_size=" + Uri.encode(v);
        }
      }
      C = k();
      localObject2 = localObject1;
      if (C.length() > 0)
      {
        localObject1 = (String)localObject1 + "&";
        localObject2 = (String)localObject1 + "connection_type=" + Uri.encode(C);
      }
      localObject1 = localObject2;
      if (H.length() > 0)
      {
        localObject1 = (String)localObject2 + "&";
        localObject1 = (String)localObject1 + "plugin=" + Uri.encode(H);
      }
      localObject2 = localObject1;
      if (I.length() > 0)
      {
        localObject1 = (String)localObject1 + "&";
        localObject2 = (String)localObject1 + "sdk_type=" + Uri.encode(I);
      }
      localObject1 = localObject2;
      if (D.length() > 0)
      {
        localObject1 = (String)localObject2 + "&";
        localObject1 = (String)localObject1 + "store_name=" + Uri.encode(D);
      }
      return localObject1;
    }
  }
  
  /* Error */
  private void w()
  {
    // Byte code:
    //   0: getstatic 73	com/c/b:b	Landroid/content/Context;
    //   3: invokevirtual 411	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   6: astore_3
    //   7: getstatic 153	com/c/b:P	Ljava/util/Hashtable;
    //   10: ifnonnull +13 -> 23
    //   13: new 340	java/util/Hashtable
    //   16: dup
    //   17: invokespecial 528	java/util/Hashtable:<init>	()V
    //   20: putstatic 153	com/c/b:P	Ljava/util/Hashtable;
    //   23: aload_3
    //   24: ifnull +160 -> 184
    //   27: aload_3
    //   28: getstatic 73	com/c/b:b	Landroid/content/Context;
    //   31: invokevirtual 531	android/content/Context:getPackageName	()Ljava/lang/String;
    //   34: sipush 128
    //   37: invokevirtual 535	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   40: astore 4
    //   42: aload 4
    //   44: ifnull +1828 -> 1872
    //   47: aload 4
    //   49: getfield 539	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   52: ifnull +1820 -> 1872
    //   55: getstatic 544	com/c/c:j	[Ljava/lang/String;
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
    //   79: getfield 539	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   82: new 171	java/lang/StringBuilder
    //   85: dup
    //   86: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   89: ldc_w 546
    //   92: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: aload 6
    //   97: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   103: invokevirtual 550	android/os/Bundle:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   106: astore 7
    //   108: aload 7
    //   110: ifnull +2143 -> 2253
    //   113: aload 7
    //   115: invokevirtual 551	java/lang/Object:toString	()Ljava/lang/String;
    //   118: astore 7
    //   120: aload 7
    //   122: ifnull +2131 -> 2253
    //   125: ldc 38
    //   127: new 171	java/lang/StringBuilder
    //   130: dup
    //   131: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   134: ldc_w 553
    //   137: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: aload 6
    //   142: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: ldc_w 555
    //   148: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: aload 7
    //   153: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   159: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   162: getstatic 153	com/c/b:P	Ljava/util/Hashtable;
    //   165: aload 6
    //   167: aload 7
    //   169: invokevirtual 559	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   172: pop
    //   173: goto +2080 -> 2253
    //   176: ldc 38
    //   178: ldc_w 561
    //   181: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   184: ldc_w 563
    //   187: invokestatic 196	com/c/b:i	(Ljava/lang/String;)Ljava/lang/String;
    //   190: ifnull +22 -> 212
    //   193: ldc_w 563
    //   196: invokestatic 196	com/c/b:i	(Ljava/lang/String;)Ljava/lang/String;
    //   199: ldc_w 454
    //   202: invokevirtual 457	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   205: ifeq +7 -> 212
    //   208: iconst_1
    //   209: invokestatic 565	com/c/i:a	(Z)V
    //   212: getstatic 73	com/c/b:b	Landroid/content/Context;
    //   215: invokevirtual 569	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   218: ldc_w 571
    //   221: invokestatic 577	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   224: putstatic 85	com/c/b:g	Ljava/lang/String;
    //   227: aload_3
    //   228: getstatic 73	com/c/b:b	Landroid/content/Context;
    //   231: invokevirtual 531	android/content/Context:getPackageName	()Ljava/lang/String;
    //   234: iconst_0
    //   235: invokevirtual 581	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   238: getfield 586	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   241: putstatic 109	com/c/b:s	Ljava/lang/String;
    //   244: ldc_w 588
    //   247: putstatic 99	com/c/b:n	Ljava/lang/String;
    //   250: ldc_w 588
    //   253: putstatic 119	com/c/b:x	Ljava/lang/String;
    //   256: getstatic 593	android/os/Build:MODEL	Ljava/lang/String;
    //   259: putstatic 95	com/c/b:l	Ljava/lang/String;
    //   262: getstatic 596	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   265: putstatic 97	com/c/b:m	Ljava/lang/String;
    //   268: getstatic 601	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   271: putstatic 101	com/c/b:o	Ljava/lang/String;
    //   274: invokestatic 607	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   277: invokevirtual 610	java/util/Locale:getCountry	()Ljava/lang/String;
    //   280: putstatic 103	com/c/b:p	Ljava/lang/String;
    //   283: invokestatic 607	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   286: invokevirtual 613	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   289: putstatic 105	com/c/b:q	Ljava/lang/String;
    //   292: ldc_w 615
    //   295: putstatic 111	com/c/b:t	Ljava/lang/String;
    //   298: getstatic 73	com/c/b:b	Landroid/content/Context;
    //   301: ldc_w 302
    //   304: iconst_0
    //   305: invokevirtual 308	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   308: astore_3
    //   309: getstatic 73	com/c/b:b	Landroid/content/Context;
    //   312: ldc_w 617
    //   315: invokevirtual 352	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   318: checkcast 619	android/telephony/TelephonyManager
    //   321: astore 4
    //   323: aload 4
    //   325: ifnull +107 -> 432
    //   328: ldc_w 621
    //   331: invokestatic 196	com/c/b:i	(Ljava/lang/String;)Ljava/lang/String;
    //   334: ifnull +1583 -> 1917
    //   337: ldc_w 621
    //   340: invokestatic 196	com/c/b:i	(Ljava/lang/String;)Ljava/lang/String;
    //   343: invokevirtual 202	java/lang/String:length	()I
    //   346: ifle +1571 -> 1917
    //   349: ldc_w 621
    //   352: invokestatic 196	com/c/b:i	(Ljava/lang/String;)Ljava/lang/String;
    //   355: putstatic 87	com/c/b:h	Ljava/lang/String;
    //   358: aload 4
    //   360: invokevirtual 624	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   363: putstatic 121	com/c/b:y	Ljava/lang/String;
    //   366: aload 4
    //   368: invokevirtual 627	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   371: putstatic 123	com/c/b:z	Ljava/lang/String;
    //   374: aload 4
    //   376: invokevirtual 630	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   379: ifnull +53 -> 432
    //   382: aload 4
    //   384: invokevirtual 630	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   387: invokevirtual 202	java/lang/String:length	()I
    //   390: iconst_5
    //   391: if_icmpeq +16 -> 407
    //   394: aload 4
    //   396: invokevirtual 630	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   399: invokevirtual 202	java/lang/String:length	()I
    //   402: bipush 6
    //   404: if_icmpne +28 -> 432
    //   407: aload 4
    //   409: invokevirtual 630	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   412: iconst_0
    //   413: iconst_3
    //   414: invokevirtual 450	java/lang/String:substring	(II)Ljava/lang/String;
    //   417: putstatic 125	com/c/b:A	Ljava/lang/String;
    //   420: aload 4
    //   422: invokevirtual 630	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   425: iconst_3
    //   426: invokevirtual 400	java/lang/String:substring	(I)Ljava/lang/String;
    //   429: putstatic 127	com/c/b:B	Ljava/lang/String;
    //   432: ldc 38
    //   434: new 171	java/lang/StringBuilder
    //   437: dup
    //   438: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   441: ldc_w 632
    //   444: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   447: getstatic 87	com/c/b:h	Ljava/lang/String;
    //   450: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   453: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   456: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   459: iconst_0
    //   460: istore_1
    //   461: getstatic 87	com/c/b:h	Ljava/lang/String;
    //   464: ifnonnull +1502 -> 1966
    //   467: ldc 38
    //   469: ldc_w 634
    //   472: invokestatic 227	com/c/i:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   475: iconst_1
    //   476: istore_1
    //   477: ldc 38
    //   479: new 171	java/lang/StringBuilder
    //   482: dup
    //   483: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   486: ldc_w 636
    //   489: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   492: getstatic 639	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   495: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   498: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   501: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   504: iload_1
    //   505: istore_2
    //   506: getstatic 639	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   509: invokestatic 645	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   512: bipush 9
    //   514: if_icmplt +99 -> 613
    //   517: ldc 38
    //   519: ldc_w 647
    //   522: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   525: new 649	com/c/g
    //   528: dup
    //   529: invokespecial 650	com/c/g:<init>	()V
    //   532: invokevirtual 652	com/c/g:a	()Ljava/lang/String;
    //   535: putstatic 93	com/c/b:k	Ljava/lang/String;
    //   538: iload_1
    //   539: ifeq +9 -> 548
    //   542: getstatic 93	com/c/b:k	Ljava/lang/String;
    //   545: putstatic 87	com/c/b:h	Ljava/lang/String;
    //   548: ldc 38
    //   550: ldc_w 654
    //   553: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   556: ldc 38
    //   558: new 171	java/lang/StringBuilder
    //   561: dup
    //   562: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   565: ldc_w 656
    //   568: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   571: getstatic 87	com/c/b:h	Ljava/lang/String;
    //   574: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   577: ldc_w 658
    //   580: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   583: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   586: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   589: ldc 38
    //   591: ldc_w 654
    //   594: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   597: getstatic 87	com/c/b:h	Ljava/lang/String;
    //   600: ifnonnull +1427 -> 2027
    //   603: ldc 38
    //   605: ldc_w 660
    //   608: invokestatic 227	com/c/i:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   611: iconst_1
    //   612: istore_2
    //   613: iload_2
    //   614: ifeq +53 -> 667
    //   617: new 662	java/lang/StringBuffer
    //   620: dup
    //   621: invokespecial 663	java/lang/StringBuffer:<init>	()V
    //   624: astore 4
    //   626: aload 4
    //   628: ldc_w 665
    //   631: invokevirtual 668	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   634: pop
    //   635: aload_3
    //   636: ldc_w 328
    //   639: aconst_null
    //   640: invokeinterface 671 3 0
    //   645: astore 5
    //   647: aload 5
    //   649: ifnull +1611 -> 2260
    //   652: aload 5
    //   654: ldc 83
    //   656: invokevirtual 457	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   659: ifne +1601 -> 2260
    //   662: aload 5
    //   664: putstatic 87	com/c/b:h	Ljava/lang/String;
    //   667: getstatic 117	com/c/b:w	Ljava/lang/String;
    //   670: invokevirtual 202	java/lang/String:length	()I
    //   673: ifne +9 -> 682
    //   676: getstatic 87	com/c/b:h	Ljava/lang/String;
    //   679: putstatic 117	com/c/b:w	Ljava/lang/String;
    //   682: getstatic 87	com/c/b:h	Ljava/lang/String;
    //   685: invokestatic 222	com/c/k:b	(Ljava/lang/String;)Ljava/lang/String;
    //   688: putstatic 89	com/c/b:i	Ljava/lang/String;
    //   691: getstatic 639	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   694: invokestatic 645	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   697: iconst_3
    //   698: if_icmple +67 -> 765
    //   701: new 673	com/c/f
    //   704: dup
    //   705: getstatic 73	com/c/b:b	Landroid/content/Context;
    //   708: invokespecial 674	com/c/f:<init>	(Landroid/content/Context;)V
    //   711: astore 4
    //   713: new 171	java/lang/StringBuilder
    //   716: dup
    //   717: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   720: ldc 83
    //   722: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   725: aload 4
    //   727: invokevirtual 676	com/c/f:a	()I
    //   730: invokevirtual 231	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   733: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   736: putstatic 113	com/c/b:u	Ljava/lang/String;
    //   739: new 171	java/lang/StringBuilder
    //   742: dup
    //   743: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   746: ldc 83
    //   748: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   751: aload 4
    //   753: invokevirtual 678	com/c/f:b	()I
    //   756: invokevirtual 231	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   759: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   762: putstatic 115	com/c/b:v	Ljava/lang/String;
    //   765: getstatic 73	com/c/b:b	Landroid/content/Context;
    //   768: ldc_w 369
    //   771: invokevirtual 352	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   774: checkcast 680	android/net/wifi/WifiManager
    //   777: astore 4
    //   779: aload 4
    //   781: ifnull +23 -> 804
    //   784: aload 4
    //   786: invokevirtual 684	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   789: astore 4
    //   791: aload 4
    //   793: ifnull +11 -> 804
    //   796: aload 4
    //   798: invokevirtual 689	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   801: putstatic 91	com/c/b:j	Ljava/lang/String;
    //   804: ldc_w 691
    //   807: invokestatic 196	com/c/b:i	(Ljava/lang/String;)Ljava/lang/String;
    //   810: ifnull +74 -> 884
    //   813: ldc_w 691
    //   816: invokestatic 196	com/c/b:i	(Ljava/lang/String;)Ljava/lang/String;
    //   819: invokevirtual 202	java/lang/String:length	()I
    //   822: ifle +62 -> 884
    //   825: ldc_w 691
    //   828: invokestatic 196	com/c/b:i	(Ljava/lang/String;)Ljava/lang/String;
    //   831: putstatic 131	com/c/b:D	Ljava/lang/String;
    //   834: new 693	java/util/ArrayList
    //   837: dup
    //   838: getstatic 695	com/c/c:k	[Ljava/lang/String;
    //   841: invokestatic 701	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   844: invokespecial 704	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   847: getstatic 131	com/c/b:D	Ljava/lang/String;
    //   850: invokevirtual 705	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   853: ifne +31 -> 884
    //   856: ldc 38
    //   858: new 171	java/lang/StringBuilder
    //   861: dup
    //   862: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   865: ldc_w 707
    //   868: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   871: getstatic 131	com/c/b:D	Ljava/lang/String;
    //   874: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   877: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   880: invokestatic 712	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   883: pop
    //   884: aload_3
    //   885: ldc_w 714
    //   888: aconst_null
    //   889: invokeinterface 671 3 0
    //   894: astore_3
    //   895: aload_3
    //   896: ifnull +16 -> 912
    //   899: aload_3
    //   900: ldc 83
    //   902: invokevirtual 457	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   905: ifne +7 -> 912
    //   908: aload_3
    //   909: putstatic 137	com/c/b:G	Ljava/lang/String;
    //   912: getstatic 73	com/c/b:b	Landroid/content/Context;
    //   915: invokevirtual 531	android/content/Context:getPackageName	()Ljava/lang/String;
    //   918: putstatic 135	com/c/b:F	Ljava/lang/String;
    //   921: ldc 38
    //   923: new 171	java/lang/StringBuilder
    //   926: dup
    //   927: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   930: ldc_w 716
    //   933: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   936: getstatic 107	com/c/b:r	Ljava/lang/String;
    //   939: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   942: ldc_w 658
    //   945: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   948: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   951: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   954: ldc 38
    //   956: new 171	java/lang/StringBuilder
    //   959: dup
    //   960: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   963: ldc_w 718
    //   966: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   969: getstatic 85	com/c/b:g	Ljava/lang/String;
    //   972: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   975: ldc_w 658
    //   978: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   981: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   984: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   987: ldc 38
    //   989: new 171	java/lang/StringBuilder
    //   992: dup
    //   993: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   996: ldc_w 720
    //   999: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1002: getstatic 135	com/c/b:F	Ljava/lang/String;
    //   1005: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1008: ldc_w 658
    //   1011: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1014: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1017: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1020: new 171	java/lang/StringBuilder
    //   1023: dup
    //   1024: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1027: ldc_w 722
    //   1030: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1033: getstatic 87	com/c/b:h	Ljava/lang/String;
    //   1036: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1039: ldc_w 658
    //   1042: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1045: astore 4
    //   1047: ldc_w 621
    //   1050: invokestatic 196	com/c/b:i	(Ljava/lang/String;)Ljava/lang/String;
    //   1053: ifnull +1194 -> 2247
    //   1056: ldc_w 621
    //   1059: invokestatic 196	com/c/b:i	(Ljava/lang/String;)Ljava/lang/String;
    //   1062: invokevirtual 202	java/lang/String:length	()I
    //   1065: ifle +1182 -> 2247
    //   1068: ldc_w 724
    //   1071: astore_3
    //   1072: ldc 38
    //   1074: aload 4
    //   1076: aload_3
    //   1077: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1080: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1083: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1086: ldc 38
    //   1088: new 171	java/lang/StringBuilder
    //   1091: dup
    //   1092: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1095: ldc_w 726
    //   1098: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1101: getstatic 89	com/c/b:i	Ljava/lang/String;
    //   1104: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1107: ldc_w 658
    //   1110: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1113: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1116: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1119: ldc 38
    //   1121: new 171	java/lang/StringBuilder
    //   1124: dup
    //   1125: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1128: ldc_w 728
    //   1131: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1134: getstatic 93	com/c/b:k	Ljava/lang/String;
    //   1137: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1140: ldc_w 658
    //   1143: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1146: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1149: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1152: ldc 38
    //   1154: new 171	java/lang/StringBuilder
    //   1157: dup
    //   1158: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1161: ldc_w 730
    //   1164: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1167: getstatic 91	com/c/b:j	Ljava/lang/String;
    //   1170: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1173: ldc_w 658
    //   1176: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1179: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1182: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1185: ldc 38
    //   1187: new 171	java/lang/StringBuilder
    //   1190: dup
    //   1191: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1194: ldc_w 732
    //   1197: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1200: getstatic 95	com/c/b:l	Ljava/lang/String;
    //   1203: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1206: ldc_w 658
    //   1209: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1212: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1215: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1218: ldc 38
    //   1220: new 171	java/lang/StringBuilder
    //   1223: dup
    //   1224: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1227: ldc_w 734
    //   1230: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1233: getstatic 97	com/c/b:m	Ljava/lang/String;
    //   1236: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1239: ldc_w 658
    //   1242: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1245: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1248: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1251: ldc 38
    //   1253: new 171	java/lang/StringBuilder
    //   1256: dup
    //   1257: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1260: ldc_w 736
    //   1263: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1266: getstatic 99	com/c/b:n	Ljava/lang/String;
    //   1269: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1272: ldc_w 658
    //   1275: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1278: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1281: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1284: ldc 38
    //   1286: new 171	java/lang/StringBuilder
    //   1289: dup
    //   1290: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1293: ldc_w 738
    //   1296: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1299: getstatic 111	com/c/b:t	Ljava/lang/String;
    //   1302: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1305: ldc_w 658
    //   1308: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1311: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1314: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1317: ldc 38
    //   1319: new 171	java/lang/StringBuilder
    //   1322: dup
    //   1323: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1326: ldc_w 740
    //   1329: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1332: getstatic 101	com/c/b:o	Ljava/lang/String;
    //   1335: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1338: ldc_w 658
    //   1341: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1344: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1347: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1350: ldc 38
    //   1352: new 171	java/lang/StringBuilder
    //   1355: dup
    //   1356: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1359: ldc_w 742
    //   1362: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1365: getstatic 103	com/c/b:p	Ljava/lang/String;
    //   1368: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1371: ldc_w 658
    //   1374: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1377: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1380: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1383: ldc 38
    //   1385: new 171	java/lang/StringBuilder
    //   1388: dup
    //   1389: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1392: ldc_w 744
    //   1395: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1398: getstatic 105	com/c/b:q	Ljava/lang/String;
    //   1401: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1404: ldc_w 658
    //   1407: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1410: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1413: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1416: ldc 38
    //   1418: new 171	java/lang/StringBuilder
    //   1421: dup
    //   1422: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1425: ldc_w 746
    //   1428: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1431: getstatic 113	com/c/b:u	Ljava/lang/String;
    //   1434: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1437: ldc_w 658
    //   1440: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1443: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1446: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1449: ldc 38
    //   1451: new 171	java/lang/StringBuilder
    //   1454: dup
    //   1455: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1458: ldc_w 748
    //   1461: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1464: getstatic 115	com/c/b:v	Ljava/lang/String;
    //   1467: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1470: ldc_w 658
    //   1473: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1476: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1479: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1482: ldc 38
    //   1484: new 171	java/lang/StringBuilder
    //   1487: dup
    //   1488: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1491: ldc_w 750
    //   1494: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1497: getstatic 121	com/c/b:y	Ljava/lang/String;
    //   1500: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1503: ldc_w 658
    //   1506: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1509: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1512: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1515: ldc 38
    //   1517: new 171	java/lang/StringBuilder
    //   1520: dup
    //   1521: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1524: ldc_w 752
    //   1527: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1530: getstatic 123	com/c/b:z	Ljava/lang/String;
    //   1533: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1536: ldc_w 658
    //   1539: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1542: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1545: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1548: ldc 38
    //   1550: new 171	java/lang/StringBuilder
    //   1553: dup
    //   1554: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1557: ldc_w 754
    //   1560: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1563: getstatic 125	com/c/b:A	Ljava/lang/String;
    //   1566: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1569: ldc_w 658
    //   1572: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1575: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1578: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1581: ldc 38
    //   1583: new 171	java/lang/StringBuilder
    //   1586: dup
    //   1587: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1590: ldc_w 756
    //   1593: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1596: getstatic 127	com/c/b:B	Ljava/lang/String;
    //   1599: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1602: ldc_w 658
    //   1605: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1608: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1611: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1614: ldc 38
    //   1616: new 171	java/lang/StringBuilder
    //   1619: dup
    //   1620: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1623: ldc_w 758
    //   1626: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1629: getstatic 131	com/c/b:D	Ljava/lang/String;
    //   1632: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1635: ldc_w 658
    //   1638: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1641: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1644: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1647: ldc 38
    //   1649: new 171	java/lang/StringBuilder
    //   1652: dup
    //   1653: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1656: ldc_w 760
    //   1659: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1662: getstatic 137	com/c/b:G	Ljava/lang/String;
    //   1665: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1668: ldc_w 658
    //   1671: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1674: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1677: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1680: getstatic 153	com/c/b:P	Ljava/util/Hashtable;
    //   1683: ifnull +188 -> 1871
    //   1686: ldc 38
    //   1688: ldc_w 762
    //   1691: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1694: ldc 38
    //   1696: ldc_w 764
    //   1699: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1702: getstatic 153	com/c/b:P	Ljava/util/Hashtable;
    //   1705: invokevirtual 768	java/util/Hashtable:entrySet	()Ljava/util/Set;
    //   1708: invokeinterface 771 1 0
    //   1713: astore_3
    //   1714: aload_3
    //   1715: invokeinterface 428 1 0
    //   1720: ifeq +151 -> 1871
    //   1723: aload_3
    //   1724: invokeinterface 432 1 0
    //   1729: checkcast 773	java/util/Map$Entry
    //   1732: astore 4
    //   1734: ldc 38
    //   1736: new 171	java/lang/StringBuilder
    //   1739: dup
    //   1740: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1743: ldc_w 775
    //   1746: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1749: aload 4
    //   1751: invokeinterface 778 1 0
    //   1756: checkcast 198	java/lang/String
    //   1759: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1762: ldc_w 780
    //   1765: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1768: aload 4
    //   1770: invokeinterface 783 1 0
    //   1775: checkcast 198	java/lang/String
    //   1778: invokestatic 287	android/net/Uri:encode	(Ljava/lang/String;)Ljava/lang/String;
    //   1781: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1784: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1787: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1790: aload 4
    //   1792: invokeinterface 778 1 0
    //   1797: checkcast 198	java/lang/String
    //   1800: ldc_w 472
    //   1803: invokevirtual 457	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1806: ifeq -92 -> 1714
    //   1809: getstatic 143	com/c/b:I	Ljava/lang/String;
    //   1812: ldc_w 785
    //   1815: invokevirtual 457	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1818: ifne -104 -> 1714
    //   1821: ldc 38
    //   1823: ldc_w 787
    //   1826: invokestatic 789	com/c/i:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   1829: getstatic 153	com/c/b:P	Ljava/util/Hashtable;
    //   1832: ldc_w 472
    //   1835: invokevirtual 792	java/util/Hashtable:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1838: pop
    //   1839: goto -125 -> 1714
    //   1842: astore_3
    //   1843: ldc 38
    //   1845: new 171	java/lang/StringBuilder
    //   1848: dup
    //   1849: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1852: ldc_w 794
    //   1855: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1858: aload_3
    //   1859: invokevirtual 225	java/lang/Exception:toString	()Ljava/lang/String;
    //   1862: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1865: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1868: invokestatic 227	com/c/i:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1871: return
    //   1872: ldc 38
    //   1874: ldc_w 796
    //   1877: invokestatic 189	com/c/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1880: goto -1696 -> 184
    //   1883: astore 4
    //   1885: ldc 38
    //   1887: new 171	java/lang/StringBuilder
    //   1890: dup
    //   1891: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1894: ldc_w 798
    //   1897: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1900: aload 4
    //   1902: invokevirtual 225	java/lang/Exception:toString	()Ljava/lang/String;
    //   1905: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1908: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1911: invokestatic 227	com/c/i:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1914: goto -1730 -> 184
    //   1917: aload 4
    //   1919: invokevirtual 801	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   1922: putstatic 87	com/c/b:h	Ljava/lang/String;
    //   1925: goto -1567 -> 358
    //   1928: astore 4
    //   1930: ldc 38
    //   1932: new 171	java/lang/StringBuilder
    //   1935: dup
    //   1936: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1939: ldc_w 803
    //   1942: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1945: aload 4
    //   1947: invokevirtual 225	java/lang/Exception:toString	()Ljava/lang/String;
    //   1950: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1953: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1956: invokestatic 227	com/c/i:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1959: aconst_null
    //   1960: putstatic 87	com/c/b:h	Ljava/lang/String;
    //   1963: goto -1296 -> 667
    //   1966: getstatic 87	com/c/b:h	Ljava/lang/String;
    //   1969: invokevirtual 202	java/lang/String:length	()I
    //   1972: ifeq +27 -> 1999
    //   1975: getstatic 87	com/c/b:h	Ljava/lang/String;
    //   1978: ldc_w 805
    //   1981: invokevirtual 457	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1984: ifne +15 -> 1999
    //   1987: getstatic 87	com/c/b:h	Ljava/lang/String;
    //   1990: ldc_w 807
    //   1993: invokevirtual 457	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1996: ifeq +16 -> 2012
    //   1999: ldc 38
    //   2001: ldc_w 809
    //   2004: invokestatic 227	com/c/i:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   2007: iconst_1
    //   2008: istore_1
    //   2009: goto -1532 -> 477
    //   2012: getstatic 87	com/c/b:h	Ljava/lang/String;
    //   2015: invokestatic 607	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   2018: invokevirtual 813	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   2021: putstatic 87	com/c/b:h	Ljava/lang/String;
    //   2024: goto -1547 -> 477
    //   2027: getstatic 87	com/c/b:h	Ljava/lang/String;
    //   2030: invokevirtual 202	java/lang/String:length	()I
    //   2033: ifeq +39 -> 2072
    //   2036: getstatic 87	com/c/b:h	Ljava/lang/String;
    //   2039: ldc_w 805
    //   2042: invokevirtual 457	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2045: ifne +27 -> 2072
    //   2048: getstatic 87	com/c/b:h	Ljava/lang/String;
    //   2051: ldc_w 807
    //   2054: invokevirtual 457	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2057: ifne +15 -> 2072
    //   2060: getstatic 87	com/c/b:h	Ljava/lang/String;
    //   2063: ldc_w 815
    //   2066: invokevirtual 457	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2069: ifeq +16 -> 2085
    //   2072: ldc 38
    //   2074: ldc_w 817
    //   2077: invokestatic 227	com/c/i:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   2080: iconst_1
    //   2081: istore_2
    //   2082: goto -1469 -> 613
    //   2085: getstatic 87	com/c/b:h	Ljava/lang/String;
    //   2088: invokevirtual 819	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   2091: putstatic 87	com/c/b:h	Ljava/lang/String;
    //   2094: iconst_0
    //   2095: istore_2
    //   2096: goto -1483 -> 613
    //   2099: iload_1
    //   2100: bipush 32
    //   2102: if_icmpge +33 -> 2135
    //   2105: aload 4
    //   2107: ldc_w 821
    //   2110: invokestatic 827	java/lang/Math:random	()D
    //   2113: ldc2_w 828
    //   2116: dmul
    //   2117: d2i
    //   2118: bipush 30
    //   2120: irem
    //   2121: invokevirtual 833	java/lang/String:charAt	(I)C
    //   2124: invokevirtual 836	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   2127: pop
    //   2128: iload_1
    //   2129: iconst_1
    //   2130: iadd
    //   2131: istore_1
    //   2132: goto -33 -> 2099
    //   2135: aload 4
    //   2137: invokevirtual 837	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   2140: invokevirtual 819	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   2143: putstatic 87	com/c/b:h	Ljava/lang/String;
    //   2146: aload_3
    //   2147: invokeinterface 314 1 0
    //   2152: astore 4
    //   2154: aload 4
    //   2156: ldc_w 328
    //   2159: getstatic 87	com/c/b:h	Ljava/lang/String;
    //   2162: invokeinterface 332 3 0
    //   2167: pop
    //   2168: aload 4
    //   2170: invokeinterface 326 1 0
    //   2175: pop
    //   2176: goto -1509 -> 667
    //   2179: astore 4
    //   2181: ldc 38
    //   2183: new 171	java/lang/StringBuilder
    //   2186: dup
    //   2187: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   2190: ldc_w 839
    //   2193: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2196: aload 4
    //   2198: invokevirtual 225	java/lang/Exception:toString	()Ljava/lang/String;
    //   2201: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2204: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2207: invokestatic 227	com/c/i:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   2210: goto -1445 -> 765
    //   2213: astore 4
    //   2215: ldc 38
    //   2217: new 171	java/lang/StringBuilder
    //   2220: dup
    //   2221: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   2224: ldc_w 841
    //   2227: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2230: aload 4
    //   2232: invokevirtual 225	java/lang/Exception:toString	()Ljava/lang/String;
    //   2235: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2238: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2241: invokestatic 227	com/c/i:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   2244: goto -1440 -> 804
    //   2247: ldc 83
    //   2249: astore_3
    //   2250: goto -1178 -> 1072
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
    //   6	1718	3	localObject1	Object
    //   1842	305	3	localException1	Exception
    //   2249	1	3	str1	String
    //   40	1751	4	localObject2	Object
    //   1883	35	4	localException2	Exception
    //   1928	208	4	localException3	Exception
    //   2152	17	4	localEditor	SharedPreferences.Editor
    //   2179	18	4	localException4	Exception
    //   2213	18	4	localException5	Exception
    //   58	605	5	localObject3	Object
    //   75	91	6	str2	String
    //   106	62	7	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   212	309	1842	java/lang/Exception
    //   667	682	1842	java/lang/Exception
    //   682	691	1842	java/lang/Exception
    //   804	884	1842	java/lang/Exception
    //   884	895	1842	java/lang/Exception
    //   899	912	1842	java/lang/Exception
    //   912	1068	1842	java/lang/Exception
    //   1072	1714	1842	java/lang/Exception
    //   1714	1839	1842	java/lang/Exception
    //   1930	1963	1842	java/lang/Exception
    //   2181	2210	1842	java/lang/Exception
    //   2215	2244	1842	java/lang/Exception
    //   7	23	1883	java/lang/Exception
    //   27	42	1883	java/lang/Exception
    //   47	64	1883	java/lang/Exception
    //   77	108	1883	java/lang/Exception
    //   113	120	1883	java/lang/Exception
    //   125	173	1883	java/lang/Exception
    //   176	184	1883	java/lang/Exception
    //   1872	1880	1883	java/lang/Exception
    //   309	323	1928	java/lang/Exception
    //   328	358	1928	java/lang/Exception
    //   358	407	1928	java/lang/Exception
    //   407	432	1928	java/lang/Exception
    //   432	459	1928	java/lang/Exception
    //   461	475	1928	java/lang/Exception
    //   477	504	1928	java/lang/Exception
    //   506	538	1928	java/lang/Exception
    //   542	548	1928	java/lang/Exception
    //   548	611	1928	java/lang/Exception
    //   617	647	1928	java/lang/Exception
    //   652	667	1928	java/lang/Exception
    //   1917	1925	1928	java/lang/Exception
    //   1966	1999	1928	java/lang/Exception
    //   1999	2007	1928	java/lang/Exception
    //   2012	2024	1928	java/lang/Exception
    //   2027	2072	1928	java/lang/Exception
    //   2072	2080	1928	java/lang/Exception
    //   2085	2094	1928	java/lang/Exception
    //   2105	2128	1928	java/lang/Exception
    //   2135	2176	1928	java/lang/Exception
    //   691	765	2179	java/lang/Exception
    //   765	779	2213	java/lang/Exception
    //   784	791	2213	java/lang/Exception
    //   796	804	2213	java/lang/Exception
  }
  
  public void a(float paramFloat)
  {
    i.a("TapjoyConnect", "setVirtualCurrencyMultiplier: " + paramFloat);
    L = paramFloat;
  }
  
  public void a(l paramL)
  {
    f = paramL;
  }
  
  public void b()
  {
    new Thread(new a()).start();
  }
  
  public void e()
  {
    c = null;
    d = null;
    i.a("TapjoyConnect", "Releasing core static instance.");
  }
  
  public void g(String paramString)
  {
    i.a("TapjoyConnect", "actionComplete: " + paramString);
    paramString = "app_id=" + paramString + "&";
    String str = paramString + v();
    if (i("sha_2_udid") != null)
    {
      paramString = str;
      if (i("sha_2_udid").equals("true")) {}
    }
    else
    {
      paramString = str + "&publisher_user_id=" + h();
    }
    paramString = paramString + "&";
    long l1 = System.currentTimeMillis() / 1000L;
    paramString = paramString + "timestamp=" + l1 + "&";
    paramString = paramString + "verifier=" + a(l1);
    i.a("TapjoyConnect", "PPA URL parameters: " + paramString);
    new Thread(new b(paramString)).start();
  }
  
  public void h(String paramString)
  {
    i.a("TapjoyConnect", "enablePaidAppWithActionID: " + paramString);
    M = paramString;
    this.N = b.getSharedPreferences("tjcPrefrences", 0).getLong("tapjoy_elapsed_time", 0L);
    i.a("TapjoyConnect", "paidApp elapsed: " + this.N);
    if (this.N >= 900000L) {
      if ((M != null) && (M.length() > 0))
      {
        i.a("TapjoyConnect", "Calling PPA actionComplete...");
        g(M);
      }
    }
    while (this.O != null) {
      return;
    }
    this.O = new Timer();
    this.O.schedule(new c(null), 10000L, 10000L);
  }
  
  public float o()
  {
    return L;
  }
  
  public class a
    implements Runnable
  {
    public a() {}
    
    public void run()
    {
      i.a("TapjoyConnect", "starting connect call...");
      Object localObject = b.c();
      localObject = b.s().a("https://ws.tapjoyads.com/connect?", (String)localObject);
      if ((localObject != null) && (((h)localObject).a == 200)) {
        if (b.j(((h)localObject).c))
        {
          i.a("TapjoyConnect", "Successfully connected to tapjoy site.");
          if (b.t() != null) {
            b.t().a();
          }
        }
      }
      while (b.t() == null) {
        for (;;)
        {
          if (b.u().length() > 0)
          {
            String str = b.d() + "&" + "package_names" + "=" + b.u() + "&";
            long l = System.currentTimeMillis() / 1000L;
            localObject = b.a(l, b.u());
            str = str + "timestamp=" + l + "&";
            localObject = str + "verifier=" + (String)localObject;
            localObject = b.s().a("https://ws.tapjoyads.com/apps_installed?", (String)localObject);
            if ((localObject != null) && (((h)localObject).a == 200)) {
              i.a("TapjoyConnect", "Successfully pinged sdkless api.");
            }
          }
          return;
          if (b.t() != null) {
            b.t().b();
          }
        }
      }
      b.t().b();
    }
  }
  
  public class b
    implements Runnable
  {
    private String b;
    
    public b(String paramString)
    {
      this.b = paramString;
    }
    
    public void run()
    {
      String str = b.s().b("https://ws.tapjoyads.com/connect?", this.b);
      if (str != null) {
        b.a(b.this, str);
      }
    }
  }
  
  private class c
    extends TimerTask
  {
    private c() {}
    
    public void run()
    {
      b.a(b.this, 10000L);
      i.a("TapjoyConnect", "elapsed_time: " + b.a(b.this) + " (" + b.a(b.this) / 1000L / 60L + "m " + b.a(b.this) / 1000L % 60L + "s)");
      SharedPreferences.Editor localEditor = b.p().getSharedPreferences("tjcPrefrences", 0).edit();
      localEditor.putLong("tapjoy_elapsed_time", b.a(b.this));
      localEditor.commit();
      if (b.a(b.this) >= 900000L)
      {
        i.a("TapjoyConnect", "timer done...");
        if ((b.q() != null) && (b.q().length() > 0))
        {
          i.a("TapjoyConnect", "Calling PPA actionComplete...");
          b.this.g(b.q());
        }
        cancel();
      }
    }
  }
}
