package com.xinmei365.font;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.PowerManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import com.xinmei.adsdk.nativeads.models.RequestNativeAdData;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

public class wa
{
  private static String a = "https://play.google.com/store/apps/";
  private static String b = null;
  private static String c = null;
  private static String d = null;
  private static String e = null;
  private static String f = null;
  private static String g = null;
  private static Map<String, String> h = new HashMap();
  private static long i = 0L;
  private static String j = null;
  private static String k = null;
  private static String l = null;
  private static String m = null;
  private static String n = null;
  private static String o = null;
  
  public wa() {}
  
  public static String A(Context paramContext)
  {
    try
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperator();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      if (vr.a()) {
        vr.b(ur.a(paramContext));
      }
    }
    return "";
  }
  
  public static String B(Context paramContext)
  {
    try
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperatorName();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      if (vr.a()) {
        vr.b(ur.a(paramContext));
      }
    }
    return "";
  }
  
  public static String C(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionName;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      if (vr.a()) {
        vr.b(ur.a(paramContext));
      }
    }
    return "";
  }
  
  @SuppressLint({"NewApi"})
  public static boolean D(Context paramContext)
  {
    try
    {
      paramContext = (PowerManager)paramContext.getSystemService("power");
      if (Build.VERSION.SDK_INT < 20) {
        return paramContext.isScreenOn();
      }
      boolean bool = paramContext.isInteractive();
      return bool;
    }
    catch (Exception paramContext)
    {
      if (vr.a()) {
        vr.b(ur.a(paramContext));
      }
    }
    return false;
  }
  
  public static String E(Context paramContext)
  {
    return d(paramContext, "com.android.vending");
  }
  
  private String F(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if (paramContext != null)
    {
      if (paramContext.getType() == 1) {
        return "WIFI";
      }
      if (paramContext.getType() == 0) {
        return "MOBILE";
      }
    }
    return "unknown";
  }
  
  public static int a(int paramInt)
  {
    try
    {
      paramInt = new Random().nextInt(paramInt);
      return paramInt;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }
  
  public static String a()
  {
    return g;
  }
  
  public static String a(Context paramContext)
  {
    if (n != null) {
      return n;
    }
    try
    {
      n = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).packageName;
      paramContext = n;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      if (vr.a()) {
        vr.a(ur.a(paramContext));
      }
    }
    return null;
  }
  
  /* Error */
  public static String a(Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: new 193	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 194	java/lang/StringBuilder:<init>	()V
    //   7: astore 4
    //   9: aload_0
    //   10: invokevirtual 198	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   13: iload_1
    //   14: invokevirtual 204	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   17: astore_0
    //   18: new 206	java/io/BufferedReader
    //   21: dup
    //   22: new 208	java/io/InputStreamReader
    //   25: dup
    //   26: aload_0
    //   27: ldc -46
    //   29: invokespecial 213	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   32: invokespecial 216	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   35: astore_2
    //   36: aload_2
    //   37: astore_0
    //   38: aload_2
    //   39: invokevirtual 219	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   42: astore_3
    //   43: aload_3
    //   44: ifnull +41 -> 85
    //   47: aload_2
    //   48: astore_0
    //   49: aload 4
    //   51: aload_3
    //   52: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: ldc -31
    //   57: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: pop
    //   61: goto -25 -> 36
    //   64: astore_3
    //   65: aload_2
    //   66: astore_0
    //   67: aload_3
    //   68: invokevirtual 226	java/io/UnsupportedEncodingException:printStackTrace	()V
    //   71: aload_2
    //   72: ifnull +7 -> 79
    //   75: aload_2
    //   76: invokevirtual 229	java/io/BufferedReader:close	()V
    //   79: aload 4
    //   81: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   84: areturn
    //   85: aload_2
    //   86: ifnull -7 -> 79
    //   89: aload_2
    //   90: invokevirtual 229	java/io/BufferedReader:close	()V
    //   93: goto -14 -> 79
    //   96: astore_0
    //   97: aload_0
    //   98: invokevirtual 233	java/io/IOException:printStackTrace	()V
    //   101: goto -22 -> 79
    //   104: astore_0
    //   105: aload_0
    //   106: invokevirtual 233	java/io/IOException:printStackTrace	()V
    //   109: goto -30 -> 79
    //   112: astore_3
    //   113: aconst_null
    //   114: astore_2
    //   115: aload_2
    //   116: astore_0
    //   117: aload_3
    //   118: invokevirtual 233	java/io/IOException:printStackTrace	()V
    //   121: aload_2
    //   122: ifnull -43 -> 79
    //   125: aload_2
    //   126: invokevirtual 229	java/io/BufferedReader:close	()V
    //   129: goto -50 -> 79
    //   132: astore_0
    //   133: aload_0
    //   134: invokevirtual 233	java/io/IOException:printStackTrace	()V
    //   137: goto -58 -> 79
    //   140: astore_2
    //   141: aconst_null
    //   142: astore_0
    //   143: aload_0
    //   144: ifnull +7 -> 151
    //   147: aload_0
    //   148: invokevirtual 229	java/io/BufferedReader:close	()V
    //   151: aload_2
    //   152: athrow
    //   153: astore_0
    //   154: aload_0
    //   155: invokevirtual 233	java/io/IOException:printStackTrace	()V
    //   158: goto -7 -> 151
    //   161: astore_2
    //   162: goto -19 -> 143
    //   165: astore_3
    //   166: goto -51 -> 115
    //   169: astore_3
    //   170: aconst_null
    //   171: astore_2
    //   172: goto -107 -> 65
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	175	0	paramContext	Context
    //   0	175	1	paramInt	int
    //   35	91	2	localBufferedReader	BufferedReader
    //   140	12	2	localObject1	Object
    //   161	1	2	localObject2	Object
    //   171	1	2	localObject3	Object
    //   42	10	3	str	String
    //   64	4	3	localUnsupportedEncodingException1	UnsupportedEncodingException
    //   112	6	3	localIOException1	IOException
    //   165	1	3	localIOException2	IOException
    //   169	1	3	localUnsupportedEncodingException2	UnsupportedEncodingException
    //   7	73	4	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   38	43	64	java/io/UnsupportedEncodingException
    //   49	61	64	java/io/UnsupportedEncodingException
    //   89	93	96	java/io/IOException
    //   75	79	104	java/io/IOException
    //   18	36	112	java/io/IOException
    //   125	129	132	java/io/IOException
    //   18	36	140	finally
    //   147	151	153	java/io/IOException
    //   38	43	161	finally
    //   49	61	161	finally
    //   67	71	161	finally
    //   117	121	161	finally
    //   38	43	165	java/io/IOException
    //   49	61	165	java/io/IOException
    //   18	36	169	java/io/UnsupportedEncodingException
  }
  
  public static String a(Context paramContext, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    paramString = null;
    try
    {
      Object localObject = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.get("AD_AGENT_SECRET");
      paramContext = paramString;
      if (localObject != null) {
        paramContext = localObject.toString();
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = "";
      }
    }
    localStringBuilder.append(paramContext);
    return e(localStringBuilder.toString());
  }
  
  /* Error */
  public static String a(Context paramContext, String paramString, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: new 259	org/json/JSONObject
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 261	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   8: astore 4
    //   10: new 263	java/util/TreeMap
    //   13: dup
    //   14: invokespecial 264	java/util/TreeMap:<init>	()V
    //   17: astore_1
    //   18: aload 4
    //   20: invokevirtual 268	org/json/JSONObject:keys	()Ljava/util/Iterator;
    //   23: astore 5
    //   25: aload 5
    //   27: invokeinterface 273 1 0
    //   32: ifeq +69 -> 101
    //   35: aload 5
    //   37: invokeinterface 277 1 0
    //   42: checkcast 279	java/lang/String
    //   45: astore 6
    //   47: aload 4
    //   49: aload 6
    //   51: invokevirtual 280	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   54: ifnull -29 -> 25
    //   57: aload_1
    //   58: aload 6
    //   60: aload 4
    //   62: aload 6
    //   64: invokevirtual 280	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   67: invokevirtual 284	java/util/TreeMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   70: pop
    //   71: goto -46 -> 25
    //   74: astore_0
    //   75: invokestatic 87	com/xinmei365/font/vr:a	()Z
    //   78: ifeq +10 -> 88
    //   81: aload_0
    //   82: invokestatic 92	com/xinmei365/font/ur:a	(Ljava/lang/Exception;)Ljava/lang/String;
    //   85: invokestatic 95	com/xinmei365/font/vr:b	(Ljava/lang/String;)V
    //   88: ldc_w 286
    //   91: areturn
    //   92: astore_0
    //   93: aload_0
    //   94: invokevirtual 287	org/json/JSONException:printStackTrace	()V
    //   97: ldc_w 286
    //   100: areturn
    //   101: new 193	java/lang/StringBuilder
    //   104: dup
    //   105: invokespecial 194	java/lang/StringBuilder:<init>	()V
    //   108: astore 4
    //   110: aload_1
    //   111: invokevirtual 291	java/util/TreeMap:entrySet	()Ljava/util/Set;
    //   114: invokeinterface 296 1 0
    //   119: astore_1
    //   120: aload_1
    //   121: invokeinterface 273 1 0
    //   126: ifeq +40 -> 166
    //   129: aload_1
    //   130: invokeinterface 277 1 0
    //   135: checkcast 298	java/util/Map$Entry
    //   138: astore 5
    //   140: aload 5
    //   142: invokeinterface 301 1 0
    //   147: ifnull -27 -> 120
    //   150: aload 4
    //   152: aload 5
    //   154: invokeinterface 301 1 0
    //   159: invokevirtual 304	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   162: pop
    //   163: goto -43 -> 120
    //   166: aconst_null
    //   167: astore_1
    //   168: aload_0
    //   169: invokevirtual 106	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   172: aload_0
    //   173: invokevirtual 181	android/content/Context:getPackageName	()Ljava/lang/String;
    //   176: sipush 128
    //   179: invokevirtual 237	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   182: getfield 243	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   185: ldc -11
    //   187: invokevirtual 250	android/os/Bundle:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   190: astore 5
    //   192: aload_1
    //   193: astore_0
    //   194: aload 5
    //   196: ifnull +9 -> 205
    //   199: aload 5
    //   201: invokevirtual 251	java/lang/Object:toString	()Ljava/lang/String;
    //   204: astore_0
    //   205: aload_0
    //   206: ifnull +10 -> 216
    //   209: aload 4
    //   211: aload_0
    //   212: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: pop
    //   216: aload 4
    //   218: iload_2
    //   219: invokevirtual 307	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   222: pop
    //   223: aload 4
    //   225: iload_3
    //   226: invokevirtual 307	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   229: pop
    //   230: aload 4
    //   232: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   235: astore_0
    //   236: aload_0
    //   237: ifnull +28 -> 265
    //   240: aload_0
    //   241: invokestatic 254	com/xinmei365/font/wa:e	(Ljava/lang/String;)Ljava/lang/String;
    //   244: areturn
    //   245: astore_0
    //   246: invokestatic 87	com/xinmei365/font/vr:a	()Z
    //   249: ifeq +10 -> 259
    //   252: aload_0
    //   253: invokestatic 92	com/xinmei365/font/ur:a	(Ljava/lang/Exception;)Ljava/lang/String;
    //   256: invokestatic 95	com/xinmei365/font/vr:b	(Ljava/lang/String;)V
    //   259: ldc 97
    //   261: astore_0
    //   262: goto -57 -> 205
    //   265: ldc 97
    //   267: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	268	0	paramContext	Context
    //   0	268	1	paramString	String
    //   0	268	2	paramInt1	int
    //   0	268	3	paramInt2	int
    //   8	223	4	localObject1	Object
    //   23	177	5	localObject2	Object
    //   45	18	6	str	String
    // Exception table:
    //   from	to	target	type
    //   25	71	74	org/json/JSONException
    //   0	10	92	org/json/JSONException
    //   168	192	245	java/lang/Exception
    //   199	205	245	java/lang/Exception
  }
  
  public static String a(String paramString, int paramInt)
  {
    int i1 = paramString.indexOf("market", paramInt);
    if (i1 == -1) {}
    for (paramInt = paramString.indexOf("http", paramInt);; paramInt = i1)
    {
      if (paramInt == -1) {
        return null;
      }
      i1 = paramInt + 6;
      while (paramString.charAt(i1) != '\'') {
        i1 += 1;
      }
      return paramString.substring(paramInt, i1);
    }
  }
  
  /* Error */
  public static String a(String paramString1, boolean paramBoolean, Map<String, String> paramMap, java.util.Set<String> paramSet, vc paramVc, long paramLong, int paramInt, String paramString2)
  {
    // Byte code:
    //   0: invokestatic 87	com/xinmei365/font/vr:a	()Z
    //   3: ifeq +26 -> 29
    //   6: new 193	java/lang/StringBuilder
    //   9: dup
    //   10: invokespecial 194	java/lang/StringBuilder:<init>	()V
    //   13: ldc_w 329
    //   16: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: aload_0
    //   20: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   26: invokestatic 186	com/xinmei365/font/vr:a	(Ljava/lang/String;)V
    //   29: iconst_m1
    //   30: istore 9
    //   32: aconst_null
    //   33: astore 12
    //   35: aload 4
    //   37: ifnull +154 -> 191
    //   40: iload 7
    //   42: istore 10
    //   44: aload 4
    //   46: invokevirtual 332	com/xinmei365/font/vc:a	()Z
    //   49: istore 11
    //   51: iload 11
    //   53: ifeq +138 -> 191
    //   56: iload 9
    //   58: sipush 400
    //   61: if_icmplt +1021 -> 1082
    //   64: iload 9
    //   66: sipush 503
    //   69: if_icmpgt +1013 -> 1082
    //   72: new 193	java/lang/StringBuilder
    //   75: dup
    //   76: invokespecial 194	java/lang/StringBuilder:<init>	()V
    //   79: ldc_w 334
    //   82: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: iload 7
    //   87: invokevirtual 307	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   90: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   93: invokestatic 186	com/xinmei365/font/vr:a	(Ljava/lang/String;)V
    //   96: iload 7
    //   98: bipush 6
    //   100: if_icmpge +982 -> 1082
    //   103: aload_3
    //   104: aload 12
    //   106: invokevirtual 340	java/net/HttpURLConnection:getURL	()Ljava/net/URL;
    //   109: invokevirtual 343	java/net/URL:toString	()Ljava/lang/String;
    //   112: invokeinterface 347 2 0
    //   117: pop
    //   118: aload 12
    //   120: invokevirtual 340	java/net/HttpURLConnection:getURL	()Ljava/net/URL;
    //   123: invokevirtual 343	java/net/URL:toString	()Ljava/lang/String;
    //   126: iconst_1
    //   127: aload_2
    //   128: aload_3
    //   129: aload 4
    //   131: lload 5
    //   133: iload 7
    //   135: aload 8
    //   137: invokestatic 349	com/xinmei365/font/wa:a	(Ljava/lang/String;ZLjava/util/Map;Ljava/util/Set;Lcom/xinmei365/font/vc;JILjava/lang/String;)Ljava/lang/String;
    //   140: astore_3
    //   141: aload_3
    //   142: astore_0
    //   143: aload 12
    //   145: ifnull +8 -> 153
    //   148: aload 12
    //   150: invokevirtual 352	java/net/HttpURLConnection:disconnect	()V
    //   153: aload_0
    //   154: astore_2
    //   155: invokestatic 87	com/xinmei365/font/vr:a	()Z
    //   158: ifeq +28 -> 186
    //   161: new 193	java/lang/StringBuilder
    //   164: dup
    //   165: invokespecial 194	java/lang/StringBuilder:<init>	()V
    //   168: ldc_w 354
    //   171: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: aload_0
    //   175: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   178: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   181: invokestatic 186	com/xinmei365/font/vr:a	(Ljava/lang/String;)V
    //   184: aload_0
    //   185: astore_2
    //   186: aload_2
    //   187: invokestatic 356	com/xinmei365/font/wa:c	(Ljava/lang/String;)Ljava/lang/String;
    //   190: areturn
    //   191: iload 7
    //   193: istore 10
    //   195: aload_3
    //   196: aload_0
    //   197: invokeinterface 359 2 0
    //   202: ifeq +893 -> 1095
    //   205: iload 7
    //   207: bipush 20
    //   209: if_icmple +6 -> 215
    //   212: goto -156 -> 56
    //   215: iload 7
    //   217: istore 10
    //   219: aload_0
    //   220: ldc_w 361
    //   223: invokevirtual 365	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   226: ifeq +66 -> 292
    //   229: iload 7
    //   231: istore 10
    //   233: new 193	java/lang/StringBuilder
    //   236: dup
    //   237: invokespecial 194	java/lang/StringBuilder:<init>	()V
    //   240: getstatic 29	com/xinmei365/font/wa:a	Ljava/lang/String;
    //   243: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   246: aload_0
    //   247: ldc_w 361
    //   250: invokevirtual 368	java/lang/String:length	()I
    //   253: invokevirtual 371	java/lang/String:substring	(I)Ljava/lang/String;
    //   256: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   259: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   262: astore 13
    //   264: iload_1
    //   265: ifeq +830 -> 1095
    //   268: iload 7
    //   270: istore 10
    //   272: aload 13
    //   274: iconst_1
    //   275: aload_2
    //   276: aload_3
    //   277: aload 4
    //   279: lload 5
    //   281: iload 7
    //   283: aload 8
    //   285: invokestatic 349	com/xinmei365/font/wa:a	(Ljava/lang/String;ZLjava/util/Map;Ljava/util/Set;Lcom/xinmei365/font/vc;JILjava/lang/String;)Ljava/lang/String;
    //   288: pop
    //   289: goto -233 -> 56
    //   292: iload 7
    //   294: istore 10
    //   296: new 342	java/net/URL
    //   299: dup
    //   300: aload_0
    //   301: invokespecial 372	java/net/URL:<init>	(Ljava/lang/String;)V
    //   304: astore 13
    //   306: aload_2
    //   307: ifnull +800 -> 1107
    //   310: iload 7
    //   312: istore 10
    //   314: invokestatic 87	com/xinmei365/font/vr:a	()Z
    //   317: ifeq +43 -> 360
    //   320: iload 7
    //   322: istore 10
    //   324: new 193	java/lang/StringBuilder
    //   327: dup
    //   328: invokespecial 194	java/lang/StringBuilder:<init>	()V
    //   331: ldc_w 374
    //   334: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   337: iload 7
    //   339: iconst_1
    //   340: iadd
    //   341: invokevirtual 307	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   344: ldc_w 376
    //   347: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   350: aload_0
    //   351: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   354: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   357: invokestatic 186	com/xinmei365/font/vr:a	(Ljava/lang/String;)V
    //   360: iload 7
    //   362: istore 10
    //   364: aload_2
    //   365: aload_0
    //   366: invokestatic 382	java/lang/System:currentTimeMillis	()J
    //   369: invokestatic 388	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   372: invokevirtual 389	java/lang/Long:toString	()Ljava/lang/String;
    //   375: invokeinterface 392 3 0
    //   380: pop
    //   381: iload 7
    //   383: iconst_1
    //   384: iadd
    //   385: istore 7
    //   387: iload 7
    //   389: istore 10
    //   391: aload_0
    //   392: ldc_w 394
    //   395: invokevirtual 365	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   398: ifeq +10 -> 408
    //   401: iload_1
    //   402: ifne +6 -> 408
    //   405: goto -349 -> 56
    //   408: iload 7
    //   410: istore 10
    //   412: aload_0
    //   413: ldc_w 396
    //   416: invokevirtual 365	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   419: ifeq +10 -> 429
    //   422: iload_1
    //   423: ifne +6 -> 429
    //   426: goto -370 -> 56
    //   429: iload 7
    //   431: istore 10
    //   433: aload 13
    //   435: invokevirtual 400	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   438: checkcast 336	java/net/HttpURLConnection
    //   441: astore 13
    //   443: aload_0
    //   444: astore 12
    //   446: aload_0
    //   447: astore 14
    //   449: aload 13
    //   451: ldc_w 402
    //   454: aload 8
    //   456: invokevirtual 406	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   459: aload_0
    //   460: astore 12
    //   462: aload_0
    //   463: astore 14
    //   465: aload 13
    //   467: iconst_0
    //   468: invokevirtual 410	java/net/HttpURLConnection:setInstanceFollowRedirects	(Z)V
    //   471: aload_0
    //   472: astore 12
    //   474: aload_0
    //   475: astore 14
    //   477: invokestatic 382	java/lang/System:currentTimeMillis	()J
    //   480: lload 5
    //   482: lsub
    //   483: invokestatic 416	java/lang/Math:abs	(J)J
    //   486: l2i
    //   487: istore 9
    //   489: aload_0
    //   490: astore 12
    //   492: aload_0
    //   493: astore 14
    //   495: aload 13
    //   497: sipush 8000
    //   500: iload 9
    //   502: isub
    //   503: invokevirtual 420	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   506: aload_0
    //   507: astore 12
    //   509: aload_0
    //   510: astore 14
    //   512: aload 13
    //   514: sipush 8000
    //   517: iload 9
    //   519: isub
    //   520: invokevirtual 423	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   523: aload_0
    //   524: astore 12
    //   526: aload_0
    //   527: astore 14
    //   529: aload 13
    //   531: invokevirtual 426	java/net/HttpURLConnection:getResponseCode	()I
    //   534: istore 9
    //   536: aload_0
    //   537: astore 12
    //   539: aload_0
    //   540: astore 14
    //   542: invokestatic 87	com/xinmei365/font/vr:a	()Z
    //   545: ifeq +33 -> 578
    //   548: aload_0
    //   549: astore 12
    //   551: aload_0
    //   552: astore 14
    //   554: new 193	java/lang/StringBuilder
    //   557: dup
    //   558: invokespecial 194	java/lang/StringBuilder:<init>	()V
    //   561: ldc_w 428
    //   564: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   567: iload 9
    //   569: invokevirtual 307	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   572: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   575: invokestatic 186	com/xinmei365/font/vr:a	(Ljava/lang/String;)V
    //   578: iload 9
    //   580: sipush 200
    //   583: if_icmpne +85 -> 668
    //   586: aload_0
    //   587: astore 12
    //   589: aload_0
    //   590: astore 14
    //   592: aload_0
    //   593: ldc_w 394
    //   596: invokevirtual 365	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   599: ifeq +22 -> 621
    //   602: aload_0
    //   603: astore 15
    //   605: aload_0
    //   606: astore 12
    //   608: aload_0
    //   609: astore 14
    //   611: aload_0
    //   612: ldc_w 396
    //   615: invokevirtual 365	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   618: ifne +212 -> 830
    //   621: aload_0
    //   622: astore 12
    //   624: aload_0
    //   625: astore 14
    //   627: aload 13
    //   629: invokestatic 431	com/xinmei365/font/wa:a	(Ljava/net/HttpURLConnection;)Ljava/lang/String;
    //   632: invokestatic 433	com/xinmei365/font/wa:d	(Ljava/lang/String;)Ljava/lang/String;
    //   635: astore 15
    //   637: aload 15
    //   639: ifnull +465 -> 1104
    //   642: aload_0
    //   643: astore 12
    //   645: aload_0
    //   646: astore 14
    //   648: aload 15
    //   650: iload_1
    //   651: aload_2
    //   652: aload_3
    //   653: aload 4
    //   655: lload 5
    //   657: iload 7
    //   659: aload 8
    //   661: invokestatic 349	com/xinmei365/font/wa:a	(Ljava/lang/String;ZLjava/util/Map;Ljava/util/Set;Lcom/xinmei365/font/vc;JILjava/lang/String;)Ljava/lang/String;
    //   664: astore_0
    //   665: goto +451 -> 1116
    //   668: aload_0
    //   669: astore 12
    //   671: aload_0
    //   672: astore 14
    //   674: aload 13
    //   676: ldc_w 435
    //   679: invokevirtual 438	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   682: astore 15
    //   684: aload_0
    //   685: astore 12
    //   687: aload 15
    //   689: ifnull +105 -> 794
    //   692: aload_0
    //   693: astore 12
    //   695: aload_0
    //   696: astore 14
    //   698: aload 15
    //   700: ldc_w 316
    //   703: invokevirtual 365	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   706: ifne +392 -> 1098
    //   709: aload_0
    //   710: astore 12
    //   712: aload_0
    //   713: astore 14
    //   715: aload 15
    //   717: ldc_w 310
    //   720: invokevirtual 365	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   723: ifne +375 -> 1098
    //   726: aload_0
    //   727: astore 12
    //   729: aload_0
    //   730: astore 14
    //   732: new 193	java/lang/StringBuilder
    //   735: dup
    //   736: invokespecial 194	java/lang/StringBuilder:<init>	()V
    //   739: aload_0
    //   740: iconst_0
    //   741: aload_0
    //   742: ldc_w 440
    //   745: bipush 10
    //   747: invokevirtual 314	java/lang/String:indexOf	(Ljava/lang/String;I)I
    //   750: invokevirtual 324	java/lang/String:substring	(II)Ljava/lang/String;
    //   753: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   756: aload 15
    //   758: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   761: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   764: astore_0
    //   765: aload_0
    //   766: astore 12
    //   768: aload_0
    //   769: astore 14
    //   771: aload_0
    //   772: ldc_w 442
    //   775: ldc_w 444
    //   778: invokevirtual 448	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   781: ldc_w 450
    //   784: ldc_w 452
    //   787: invokevirtual 448	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   790: astore_0
    //   791: aload_0
    //   792: astore 12
    //   794: iload 9
    //   796: sipush 302
    //   799: if_icmpeq +286 -> 1085
    //   802: iload 9
    //   804: sipush 301
    //   807: if_icmpeq +278 -> 1085
    //   810: iload 9
    //   812: sipush 307
    //   815: if_icmpeq +270 -> 1085
    //   818: iload 9
    //   820: sipush 303
    //   823: if_icmpeq +262 -> 1085
    //   826: aload 12
    //   828: astore 15
    //   830: aload 15
    //   832: astore_0
    //   833: aload 13
    //   835: astore 12
    //   837: goto -781 -> 56
    //   840: astore 4
    //   842: aload 13
    //   844: astore_3
    //   845: aload 12
    //   847: astore_0
    //   848: invokestatic 87	com/xinmei365/font/vr:a	()Z
    //   851: ifeq +8 -> 859
    //   854: aload 4
    //   856: invokevirtual 453	java/lang/Throwable:printStackTrace	()V
    //   859: aload_2
    //   860: ifnull +91 -> 951
    //   863: aload_2
    //   864: new 193	java/lang/StringBuilder
    //   867: dup
    //   868: invokespecial 194	java/lang/StringBuilder:<init>	()V
    //   871: ldc_w 455
    //   874: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   877: iload 7
    //   879: iconst_1
    //   880: isub
    //   881: invokevirtual 307	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   884: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   887: invokeinterface 458 2 0
    //   892: ifnull +59 -> 951
    //   895: aload_2
    //   896: new 193	java/lang/StringBuilder
    //   899: dup
    //   900: invokespecial 194	java/lang/StringBuilder:<init>	()V
    //   903: ldc_w 455
    //   906: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   909: iload 7
    //   911: iconst_1
    //   912: isub
    //   913: invokevirtual 307	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   916: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   919: invokeinterface 458 2 0
    //   924: checkcast 279	java/lang/String
    //   927: aload_0
    //   928: invokevirtual 461	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   931: ifne +20 -> 951
    //   934: aload_2
    //   935: aload_0
    //   936: invokestatic 382	java/lang/System:currentTimeMillis	()J
    //   939: invokestatic 388	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   942: invokevirtual 389	java/lang/Long:toString	()Ljava/lang/String;
    //   945: invokeinterface 392 3 0
    //   950: pop
    //   951: aload_3
    //   952: ifnull +7 -> 959
    //   955: aload_3
    //   956: invokevirtual 352	java/net/HttpURLConnection:disconnect	()V
    //   959: invokestatic 87	com/xinmei365/font/vr:a	()Z
    //   962: ifeq +115 -> 1077
    //   965: new 193	java/lang/StringBuilder
    //   968: dup
    //   969: invokespecial 194	java/lang/StringBuilder:<init>	()V
    //   972: ldc_w 354
    //   975: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   978: aload_0
    //   979: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   982: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   985: invokestatic 186	com/xinmei365/font/vr:a	(Ljava/lang/String;)V
    //   988: aload_0
    //   989: astore_2
    //   990: goto -804 -> 186
    //   993: astore_2
    //   994: aload 13
    //   996: astore 12
    //   998: aload 14
    //   1000: astore_0
    //   1001: aload 12
    //   1003: ifnull +8 -> 1011
    //   1006: aload 12
    //   1008: invokevirtual 352	java/net/HttpURLConnection:disconnect	()V
    //   1011: invokestatic 87	com/xinmei365/font/vr:a	()Z
    //   1014: ifeq +26 -> 1040
    //   1017: new 193	java/lang/StringBuilder
    //   1020: dup
    //   1021: invokespecial 194	java/lang/StringBuilder:<init>	()V
    //   1024: ldc_w 354
    //   1027: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1030: aload_0
    //   1031: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1034: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1037: invokestatic 186	com/xinmei365/font/vr:a	(Ljava/lang/String;)V
    //   1040: aload_2
    //   1041: athrow
    //   1042: astore_2
    //   1043: goto -42 -> 1001
    //   1046: astore_2
    //   1047: goto -46 -> 1001
    //   1050: astore_2
    //   1051: aload_3
    //   1052: astore 12
    //   1054: goto -53 -> 1001
    //   1057: astore 4
    //   1059: aload 12
    //   1061: astore_3
    //   1062: iload 10
    //   1064: istore 7
    //   1066: goto -218 -> 848
    //   1069: astore 4
    //   1071: aload 12
    //   1073: astore_3
    //   1074: goto -226 -> 848
    //   1077: aload_0
    //   1078: astore_2
    //   1079: goto -893 -> 186
    //   1082: goto -939 -> 143
    //   1085: aload 12
    //   1087: astore_0
    //   1088: aload 13
    //   1090: astore 12
    //   1092: goto -1057 -> 35
    //   1095: goto -1039 -> 56
    //   1098: aload 15
    //   1100: astore_0
    //   1101: goto -336 -> 765
    //   1104: goto +12 -> 1116
    //   1107: iload 7
    //   1109: iconst_1
    //   1110: iadd
    //   1111: istore 7
    //   1113: goto -726 -> 387
    //   1116: aload 13
    //   1118: astore 12
    //   1120: goto -1064 -> 56
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1123	0	paramString1	String
    //   0	1123	1	paramBoolean	boolean
    //   0	1123	2	paramMap	Map<String, String>
    //   0	1123	3	paramSet	java.util.Set<String>
    //   0	1123	4	paramVc	vc
    //   0	1123	5	paramLong	long
    //   0	1123	7	paramInt	int
    //   0	1123	8	paramString2	String
    //   30	794	9	i1	int
    //   42	1021	10	i2	int
    //   49	3	11	bool	boolean
    //   33	1086	12	localObject1	Object
    //   262	855	13	localObject2	Object
    //   447	552	14	str	String
    //   603	496	15	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   449	459	840	java/lang/Throwable
    //   465	471	840	java/lang/Throwable
    //   477	489	840	java/lang/Throwable
    //   495	506	840	java/lang/Throwable
    //   512	523	840	java/lang/Throwable
    //   529	536	840	java/lang/Throwable
    //   542	548	840	java/lang/Throwable
    //   554	578	840	java/lang/Throwable
    //   592	602	840	java/lang/Throwable
    //   611	621	840	java/lang/Throwable
    //   627	637	840	java/lang/Throwable
    //   648	665	840	java/lang/Throwable
    //   674	684	840	java/lang/Throwable
    //   698	709	840	java/lang/Throwable
    //   715	726	840	java/lang/Throwable
    //   732	765	840	java/lang/Throwable
    //   771	791	840	java/lang/Throwable
    //   449	459	993	finally
    //   465	471	993	finally
    //   477	489	993	finally
    //   495	506	993	finally
    //   512	523	993	finally
    //   529	536	993	finally
    //   542	548	993	finally
    //   554	578	993	finally
    //   592	602	993	finally
    //   611	621	993	finally
    //   627	637	993	finally
    //   648	665	993	finally
    //   674	684	993	finally
    //   698	709	993	finally
    //   715	726	993	finally
    //   732	765	993	finally
    //   771	791	993	finally
    //   44	51	1042	finally
    //   195	205	1042	finally
    //   219	229	1042	finally
    //   233	264	1042	finally
    //   272	289	1042	finally
    //   296	306	1042	finally
    //   314	320	1042	finally
    //   324	360	1042	finally
    //   364	381	1042	finally
    //   391	401	1042	finally
    //   412	422	1042	finally
    //   433	443	1042	finally
    //   72	96	1046	finally
    //   103	141	1046	finally
    //   848	859	1050	finally
    //   863	951	1050	finally
    //   44	51	1057	java/lang/Throwable
    //   195	205	1057	java/lang/Throwable
    //   219	229	1057	java/lang/Throwable
    //   233	264	1057	java/lang/Throwable
    //   272	289	1057	java/lang/Throwable
    //   296	306	1057	java/lang/Throwable
    //   314	320	1057	java/lang/Throwable
    //   324	360	1057	java/lang/Throwable
    //   364	381	1057	java/lang/Throwable
    //   391	401	1057	java/lang/Throwable
    //   412	422	1057	java/lang/Throwable
    //   433	443	1057	java/lang/Throwable
    //   72	96	1069	java/lang/Throwable
    //   103	141	1069	java/lang/Throwable
  }
  
  public static String a(HttpURLConnection paramHttpURLConnection)
    throws Exception
  {
    Object localObject1 = null;
    int i2 = paramHttpURLConnection.getContentLength();
    if (vr.a()) {
      vr.a("contentLength = " + i2);
    }
    if (i2 > 1600) {
      paramHttpURLConnection = localObject1;
    }
    for (;;)
    {
      return paramHttpURLConnection;
      if (i2 > 0)
      {
        paramHttpURLConnection = paramHttpURLConnection.getInputStream();
        localObject3 = new BufferedInputStream(paramHttpURLConnection);
        try
        {
          Object localObject4 = new byte[i2];
          int i1 = ((BufferedInputStream)localObject3).read((byte[])localObject4, 0, i2);
          while (i1 < i2) {
            i1 += ((BufferedInputStream)localObject3).read((byte[])localObject4, i1, i2 - i1);
          }
          if (vr.a()) {
            vr.a(new String((byte[])localObject4));
          }
          localObject4 = new String((byte[])localObject4);
          return localObject4;
        }
        catch (Exception localException)
        {
          if (vr.a()) {
            vr.a(ur.a(localException));
          }
          if (paramHttpURLConnection != null) {
            paramHttpURLConnection.close();
          }
          paramHttpURLConnection = localObject1;
          if (localObject3 == null) {
            continue;
          }
          ((BufferedInputStream)localObject3).close();
          return null;
        }
        finally
        {
          if (paramHttpURLConnection != null) {
            paramHttpURLConnection.close();
          }
          if (localObject3 != null) {
            ((BufferedInputStream)localObject3).close();
          }
        }
      }
      InputStream localInputStream = paramHttpURLConnection.getInputStream();
      Object localObject3 = new BufferedReader(new InputStreamReader(localInputStream));
      try
      {
        paramHttpURLConnection = new StringBuffer();
        for (;;)
        {
          String str = ((BufferedReader)localObject3).readLine();
          if (str == null) {
            break;
          }
          paramHttpURLConnection.append(str).append("\n");
        }
      }
      catch (Exception paramHttpURLConnection)
      {
        if (vr.a()) {
          vr.a(ur.a(paramHttpURLConnection));
        }
        if (localInputStream != null) {
          localInputStream.close();
        }
        paramHttpURLConnection = localObject2;
        if (localObject3 == null) {
          continue;
        }
        ((BufferedReader)localObject3).close();
        return null;
        paramHttpURLConnection = paramHttpURLConnection.toString();
        HttpURLConnection localHttpURLConnection = paramHttpURLConnection;
        if (localInputStream != null) {
          localInputStream.close();
        }
        paramHttpURLConnection = localHttpURLConnection;
        if (localObject3 == null) {
          continue;
        }
        ((BufferedReader)localObject3).close();
        return localHttpURLConnection;
      }
      finally
      {
        if (localInputStream != null) {
          localInputStream.close();
        }
        if (localObject3 != null) {
          ((BufferedReader)localObject3).close();
        }
      }
    }
  }
  
  public static List<RequestNativeAdData> a(Context paramContext, List<RequestNativeAdData> paramList, boolean paramBoolean)
  {
    LinkedList localLinkedList = new LinkedList();
    if ((paramContext == null) || (a(paramList))) {
      return localLinkedList;
    }
    paramContext = a(paramContext.getPackageManager());
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      RequestNativeAdData localRequestNativeAdData = (RequestNativeAdData)paramList.next();
      if (vr.a()) {
        vr.a("package name > " + localRequestNativeAdData.getPkgname());
      }
      if ((!paramContext.contains(localRequestNativeAdData.getPkgname())) || ("SHOW_CACHE".equals(localRequestNativeAdData.getAd_click_type())) || (paramBoolean)) {
        localLinkedList.add(localRequestNativeAdData);
      }
    }
    return localLinkedList;
  }
  
  public static List<String> a(PackageManager paramPackageManager)
  {
    Object localObject = b(paramPackageManager);
    if (localObject == null) {
      return null;
    }
    paramPackageManager = new ArrayList(((List)localObject).size());
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramPackageManager.add(((ResolveInfo)((Iterator)localObject).next()).activityInfo.packageName);
    }
    return paramPackageManager;
  }
  
  public static void a(long paramLong)
  {
    i = paramLong;
  }
  
  public static void a(Context paramContext, int paramInt, String paramString)
  {
    try
    {
      paramContext = paramContext.getSharedPreferences("META_INFO", 0).edit();
      paramContext.putInt(paramString, paramInt);
      paramContext.apply();
      return;
    }
    catch (Exception paramContext)
    {
      while (!vr.a()) {}
      vr.b(ur.a(paramContext));
    }
  }
  
  public static void a(Context paramContext, long paramLong, String paramString)
  {
    try
    {
      paramContext = paramContext.getSharedPreferences("META_INFO", 0).edit();
      paramContext.putLong(paramString, paramLong);
      paramContext.apply();
      return;
    }
    catch (Exception paramContext)
    {
      while (!vr.a()) {}
      vr.b(ur.a(paramContext));
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = paramContext.getSharedPreferences("META_INFO", 0).edit();
      paramContext.putString(paramString2, paramString1);
      paramContext.apply();
      return;
    }
    catch (Exception paramContext)
    {
      while (!vr.a()) {}
      vr.b(ur.a(paramContext));
    }
  }
  
  public static void a(Context paramContext, boolean paramBoolean, String paramString)
  {
    try
    {
      paramContext = paramContext.getSharedPreferences("META_INFO", 0).edit();
      paramContext.putBoolean(paramString, paramBoolean);
      paramContext.apply();
      return;
    }
    catch (Exception paramContext)
    {
      while (!vr.a()) {}
      vr.b(ur.a(paramContext));
    }
  }
  
  public static void a(String paramString)
  {
    g = paramString;
  }
  
  public static boolean a(Bitmap paramBitmap, String paramString1, String paramString2)
  {
    boolean bool2 = false;
    Bitmap.CompressFormat localCompressFormat = Bitmap.CompressFormat.PNG;
    paramString1 = new File(paramString1, paramString2);
    if (vr.a()) {
      vr.a(paramString1.getAbsolutePath());
    }
    for (;;)
    {
      try
      {
        paramString1 = new FileOutputStream(paramString1);
        bool1 = bool2;
        if (paramString1 == null) {}
      }
      catch (Exception paramBitmap)
      {
        try
        {
          if (paramBitmap.compress(localCompressFormat, 90, paramString1))
          {
            paramString1.flush();
            paramString1.close();
            bool1 = true;
          }
          return bool1;
        }
        catch (Exception paramBitmap)
        {
          boolean bool1;
          for (;;) {}
        }
        paramBitmap = paramBitmap;
        paramString1 = null;
      }
      try
      {
        paramString1.close();
        bool1 = bool2;
        if (!vr.a()) {
          continue;
        }
        vr.b(ur.a(paramBitmap));
        return false;
      }
      catch (IOException paramString1)
      {
        for (;;)
        {
          paramString1.printStackTrace();
        }
      }
    }
  }
  
  public static boolean a(String paramString, Context paramContext)
  {
    try
    {
      if ((!paramString.contains("com.google")) && (!paramString.contains("com.android.")) && (!paramString.contains("samsung")))
      {
        boolean bool = paramString.equals(a(paramContext));
        if (!bool) {}
      }
      else
      {
        return true;
      }
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static boolean a(List paramList)
  {
    return (paramList == null) || (paramList.isEmpty());
  }
  
  public static int b(Context paramContext, int paramInt, String paramString)
  {
    try
    {
      i1 = paramContext.getSharedPreferences("META_INFO", 0).getInt(paramString, paramInt);
      return i1;
    }
    catch (Exception paramContext)
    {
      do
      {
        int i1 = paramInt;
      } while (!vr.a());
      vr.b(ur.a(paramContext));
    }
    return paramInt;
  }
  
  public static long b(Context paramContext, long paramLong, String paramString)
  {
    try
    {
      l1 = paramContext.getSharedPreferences("META_INFO", 0).getLong(paramString, paramLong);
      return l1;
    }
    catch (Exception paramContext)
    {
      do
      {
        long l1 = paramLong;
      } while (!vr.a());
      vr.b(ur.a(paramContext));
    }
    return paramLong;
  }
  
  public static String b()
  {
    try
    {
      InetAddress localInetAddress;
      do
      {
        localObject = NetworkInterface.getNetworkInterfaces();
        Enumeration localEnumeration;
        while (!localEnumeration.hasMoreElements())
        {
          if (!((Enumeration)localObject).hasMoreElements()) {
            break;
          }
          localEnumeration = ((NetworkInterface)((Enumeration)localObject).nextElement()).getInetAddresses();
        }
        localInetAddress = (InetAddress)localEnumeration.nextElement();
      } while (localInetAddress.isLoopbackAddress());
      Object localObject = localInetAddress.getHostAddress().toString();
      return localObject;
    }
    catch (SocketException localSocketException)
    {
      localSocketException.printStackTrace();
    }
    return null;
  }
  
  /* Error */
  public static String b(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 39	com/xinmei365/font/wa:f	Ljava/lang/String;
    //   6: ifnull +12 -> 18
    //   9: getstatic 39	com/xinmei365/font/wa:f	Ljava/lang/String;
    //   12: astore_0
    //   13: ldc 2
    //   15: monitorexit
    //   16: aload_0
    //   17: areturn
    //   18: invokestatic 673	com/xinmei365/font/vy:d	()Landroid/os/Handler;
    //   21: new 675	com/xinmei365/font/wa$1
    //   24: dup
    //   25: aload_0
    //   26: invokespecial 678	com/xinmei365/font/wa$1:<init>	(Landroid/content/Context;)V
    //   29: invokevirtual 684	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   32: pop
    //   33: getstatic 39	com/xinmei365/font/wa:f	Ljava/lang/String;
    //   36: astore_0
    //   37: goto -24 -> 13
    //   40: astore_0
    //   41: ldc 2
    //   43: monitorexit
    //   44: aload_0
    //   45: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	46	0	paramContext	Context
    // Exception table:
    //   from	to	target	type
    //   3	13	40	finally
    //   18	37	40	finally
  }
  
  public static String b(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = paramContext.getSharedPreferences("META_INFO", 0).getString(paramString2, paramString1);
      return paramContext;
    }
    catch (Exception paramString2)
    {
      do
      {
        paramContext = paramString1;
      } while (!vr.a());
      vr.b(ur.a(paramString2));
    }
    return paramString1;
  }
  
  public static List<ResolveInfo> b(PackageManager paramPackageManager)
  {
    if (paramPackageManager == null) {
      return null;
    }
    Intent localIntent = new Intent("android.intent.action.MAIN", null);
    localIntent.addCategory("android.intent.category.LAUNCHER");
    return paramPackageManager.queryIntentActivities(localIntent, 0);
  }
  
  public static void b(String paramString)
  {
    f = paramString;
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    boolean bool = false;
    try
    {
      int i1 = paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName());
      if (i1 == 0) {
        bool = true;
      }
    }
    catch (Exception paramContext)
    {
      while (!vr.a()) {}
      vr.b(ur.a(paramContext));
    }
    return bool;
    return false;
  }
  
  public static boolean b(Context paramContext, boolean paramBoolean, String paramString)
  {
    try
    {
      bool = paramContext.getSharedPreferences("META_INFO", 0).getBoolean(paramString, paramBoolean);
      return bool;
    }
    catch (Exception paramContext)
    {
      do
      {
        boolean bool = paramBoolean;
      } while (!vr.a());
      vr.b(ur.a(paramContext));
    }
    return paramBoolean;
  }
  
  @SuppressLint({"DefaultLocale"})
  public static String c()
  {
    String str3 = Build.VERSION.RELEASE;
    String str4 = Build.MODEL;
    String str5 = Build.ID;
    Object localObject = Locale.getDefault();
    String str2 = ((Locale)localObject).getLanguage();
    String str1 = "en";
    if (str2 != null)
    {
      str2 = str2.toLowerCase();
      localObject = ((Locale)localObject).getCountry();
      str1 = str2;
      if (localObject != null) {
        str1 = str2 + "-" + ((String)localObject).toLowerCase();
      }
    }
    return String.format("Mozilla/5.0 (Linux; U; Android %1$s; %2$s; %3$s Build/%4$s) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1", new Object[] { str3, str1, str4, str5 });
  }
  
  /* Error */
  public static String c(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 31	com/xinmei365/font/wa:b	Ljava/lang/String;
    //   6: ifnull +12 -> 18
    //   9: getstatic 31	com/xinmei365/font/wa:b	Ljava/lang/String;
    //   12: astore_0
    //   13: ldc 2
    //   15: monitorexit
    //   16: aload_0
    //   17: areturn
    //   18: aload_0
    //   19: invokevirtual 758	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   22: ldc_w 760
    //   25: invokestatic 765	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   28: putstatic 31	com/xinmei365/font/wa:b	Ljava/lang/String;
    //   31: getstatic 31	com/xinmei365/font/wa:b	Ljava/lang/String;
    //   34: ifnonnull +9 -> 43
    //   37: ldc 97
    //   39: astore_0
    //   40: goto -27 -> 13
    //   43: getstatic 31	com/xinmei365/font/wa:b	Ljava/lang/String;
    //   46: astore_0
    //   47: goto -34 -> 13
    //   50: astore_0
    //   51: ldc 2
    //   53: monitorexit
    //   54: aload_0
    //   55: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	56	0	paramContext	Context
    // Exception table:
    //   from	to	target	type
    //   3	13	50	finally
    //   18	37	50	finally
    //   43	47	50	finally
  }
  
  public static String c(String paramString)
  {
    if ((paramString != null) && ((paramString.startsWith("market://")) || (paramString.startsWith(a)))) {
      return paramString;
    }
    return "http://recommend.kikakeyboard.com/list";
  }
  
  public static void c(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      localIntent = new Intent("com.android.vending.INSTALL_REFERRER");
      localIntent.setPackage(paramString1);
      if (!vr.a()) {
        break label79;
      }
      vr.a("send referrer > " + paramString2);
    }
    catch (Exception paramContext)
    {
      label79:
      do
      {
        Intent localIntent;
        if (!vr.a()) {
          break;
        }
        vr.b(ur.a(paramContext));
        return;
      } while (paramString2 != null);
    }
    localIntent.putExtra("referrer", paramString2);
    paramContext.sendBroadcast(localIntent);
    return;
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getPackageManager().getPackageInfo(paramString, 0);
      return true;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static String d(Context paramContext)
  {
    for (;;)
    {
      try
      {
        if (d != null)
        {
          paramContext = d;
          return paramContext;
        }
        if (paramContext == null)
        {
          paramContext = "";
        }
        else
        {
          try
          {
            paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
            if (paramContext != null) {
              d = paramContext.metaData.getString("AD_AGENT_APPKEY");
            }
          }
          catch (Exception paramContext)
          {
            if (!vr.a()) {
              continue;
            }
            vr.b(ur.a(paramContext));
            continue;
          }
          if (d == null) {
            paramContext = "";
          } else {
            paramContext = d;
          }
        }
      }
      finally {}
    }
  }
  
  public static String d(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
      while (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        if (paramString.equals(localPackageInfo.packageName))
        {
          paramContext = localPackageInfo.versionName;
          return paramContext;
        }
      }
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  @SuppressLint({"DefaultLocale"})
  public static String d(String paramString)
    throws Exception
  {
    if (vr.a()) {
      vr.a("html = " + paramString);
    }
    Object localObject;
    int i1;
    if ((paramString == null) || (paramString.equals("")) || (paramString.equals("\n")) || (paramString.length() > 1600)) {
      if (vr.a())
      {
        localObject = new StringBuilder().append("html.length(): ");
        if (!TextUtils.isEmpty(paramString))
        {
          i1 = paramString.length();
          vr.a(i1);
        }
      }
      else
      {
        paramString = null;
      }
    }
    do
    {
      return paramString;
      i1 = 0;
      break;
      paramString = paramString.replaceAll("\\s+", "").replaceAll("\"", "'");
      int i2 = Integer.MAX_VALUE;
      if (paramString.toLowerCase().contains("market://details")) {
        i1 = paramString.indexOf("market://details");
      }
      while (i1 == -1)
      {
        return null;
        if ((paramString.toLowerCase().contains("refresh")) && (paramString.toLowerCase().contains("http-equiv")))
        {
          i1 = paramString.indexOf("refresh");
        }
        else if (paramString.toLowerCase().contains("window.location.href"))
        {
          i1 = paramString.indexOf("window.location.href");
        }
        else if (paramString.toLowerCase().contains("window.location"))
        {
          i1 = paramString.indexOf("window.location");
        }
        else
        {
          i1 = i2;
          if (paramString.toLowerCase().contains("onload"))
          {
            i1 = i2;
            if (paramString.toLowerCase().contains("document.location.replace")) {
              i1 = paramString.indexOf("document.location.replace");
            }
          }
        }
      }
      localObject = a(paramString, i1);
      paramString = (String)localObject;
    } while (!vr.a());
    vr.a("endurl = " + (String)localObject);
    return localObject;
  }
  
  public static boolean d()
  {
    return false;
  }
  
  public static String e()
  {
    try
    {
      InetAddress localInetAddress;
      do
      {
        localObject = NetworkInterface.getNetworkInterfaces();
        Enumeration localEnumeration;
        while (!localEnumeration.hasMoreElements())
        {
          if (!((Enumeration)localObject).hasMoreElements()) {
            break;
          }
          localEnumeration = ((NetworkInterface)((Enumeration)localObject).nextElement()).getInetAddresses();
        }
        localInetAddress = (InetAddress)localEnumeration.nextElement();
      } while ((localInetAddress.isLoopbackAddress()) || (localInetAddress.isLinkLocalAddress()));
      Object localObject = localInetAddress.getHostAddress();
      return localObject;
    }
    catch (SocketException localSocketException)
    {
      localSocketException.printStackTrace();
    }
    return "";
  }
  
  public static String e(Context paramContext)
  {
    for (;;)
    {
      try
      {
        if (j != null)
        {
          paramContext = j;
          return paramContext;
        }
        if (paramContext == null)
        {
          paramContext = "";
        }
        else
        {
          try
          {
            paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
            if (paramContext != null) {
              j = paramContext.metaData.getString("FACEBOOK_AD_UNIT_ID");
            }
          }
          catch (Exception paramContext)
          {
            if (!vr.a()) {
              continue;
            }
            vr.b(ur.a(paramContext));
            continue;
          }
          if (j == null) {
            paramContext = "";
          } else {
            paramContext = j;
          }
        }
      }
      finally {}
    }
  }
  
  public static String e(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes("UTF8"));
      paramString = ((MessageDigest)localObject).digest();
      localObject = new StringBuffer();
      int i1 = 0;
      while (i1 < paramString.length)
      {
        int i2 = paramString[i1] & 0xFF;
        if (i2 < 16) {
          ((StringBuffer)localObject).append("0");
        }
        ((StringBuffer)localObject).append(Integer.toHexString(i2));
        i1 += 1;
      }
      paramString = ((StringBuffer)localObject).toString();
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      paramString.printStackTrace();
      return "";
    }
    catch (UnsupportedEncodingException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  public static Bitmap f(String paramString)
  {
    try
    {
      paramString = BitmapFactory.decodeStream(new URL(paramString).openStream());
      return paramString;
    }
    catch (MalformedURLException paramString)
    {
      paramString.printStackTrace();
      return null;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static String f()
  {
    try
    {
      String str = Build.MODEL;
      return str;
    }
    catch (Exception localException)
    {
      if (vr.a()) {
        vr.b(ur.a(localException));
      }
    }
    return "";
  }
  
  public static String f(Context paramContext)
  {
    for (;;)
    {
      try
      {
        if (k != null)
        {
          paramContext = k;
          return paramContext;
        }
        if (paramContext == null)
        {
          paramContext = "";
        }
        else
        {
          try
          {
            paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
            if (paramContext != null) {
              k = paramContext.metaData.getString("FACEBOOK_INTERSTITIAL_AD_UNIT_ID");
            }
          }
          catch (Exception paramContext)
          {
            if (!vr.a()) {
              continue;
            }
            vr.b(ur.a(paramContext));
            continue;
          }
          if (k == null) {
            paramContext = "";
          } else {
            paramContext = k;
          }
        }
      }
      finally {}
    }
  }
  
  public static Bitmap g(String paramString)
  {
    if (new File(paramString).exists()) {
      return BitmapFactory.decodeFile(paramString);
    }
    return null;
  }
  
  public static String g()
  {
    return Locale.getDefault().getCountry();
  }
  
  public static String g(Context paramContext)
  {
    for (;;)
    {
      try
      {
        if (l != null)
        {
          paramContext = l;
          return paramContext;
        }
        if (paramContext == null)
        {
          paramContext = "";
        }
        else
        {
          try
          {
            paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
            if (paramContext != null) {
              l = paramContext.metaData.getString("ADMOB_INTERSTITIAL_AD_UNIT_ID");
            }
          }
          catch (Exception paramContext)
          {
            if (!vr.a()) {
              continue;
            }
            vr.b(ur.a(paramContext));
            continue;
          }
          if (l == null) {
            paramContext = "";
          } else {
            paramContext = l;
          }
        }
      }
      finally {}
    }
  }
  
  public static String h()
  {
    return "4.1.3.1";
  }
  
  public static String h(Context paramContext)
  {
    for (;;)
    {
      try
      {
        if (m != null)
        {
          paramContext = m;
          return paramContext;
        }
        if (paramContext == null)
        {
          paramContext = "";
        }
        else
        {
          try
          {
            paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
            if (paramContext != null) {
              m = paramContext.metaData.getString("ADMOB_NATIVE_AD_UNIT_ID");
            }
          }
          catch (Exception paramContext)
          {
            if (!vr.a()) {
              continue;
            }
            vr.b(ur.a(paramContext));
            continue;
          }
          if (m == null) {
            paramContext = "";
          } else {
            paramContext = m;
          }
        }
      }
      finally {}
    }
  }
  
  public static String h(String paramString)
  {
    Object localObject;
    if (um.O() == 0) {
      localObject = null;
    }
    for (;;)
    {
      return localObject;
      if (Math.abs(System.currentTimeMillis() - i) > um.P()) {
        h.clear();
      }
      String str = (String)h.get(paramString);
      localObject = str;
      if (str != null) {
        continue;
      }
      try
      {
        str = InetAddress.getByName(paramString).getHostAddress();
        localObject = str;
        if (str == null) {
          continue;
        }
        h.put(paramString, str);
        return str;
      }
      catch (UnknownHostException localUnknownHostException)
      {
        for (;;)
        {
          if (vr.a()) {
            vr.b(ur.a(localUnknownHostException));
          }
          str = null;
        }
      }
    }
  }
  
  /* Error */
  public static String i(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 33	com/xinmei365/font/wa:c	Ljava/lang/String;
    //   6: ifnull +12 -> 18
    //   9: getstatic 33	com/xinmei365/font/wa:c	Ljava/lang/String;
    //   12: astore_0
    //   13: ldc 2
    //   15: monitorexit
    //   16: aload_0
    //   17: areturn
    //   18: new 193	java/lang/StringBuilder
    //   21: dup
    //   22: invokespecial 194	java/lang/StringBuilder:<init>	()V
    //   25: aload_0
    //   26: invokestatic 915	com/xinmei365/font/wa:n	(Landroid/content/Context;)Ljava/lang/String;
    //   29: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: aload_0
    //   33: invokestatic 917	com/xinmei365/font/wa:m	(Landroid/content/Context;)Ljava/lang/String;
    //   36: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   42: invokestatic 254	com/xinmei365/font/wa:e	(Ljava/lang/String;)Ljava/lang/String;
    //   45: putstatic 33	com/xinmei365/font/wa:c	Ljava/lang/String;
    //   48: getstatic 33	com/xinmei365/font/wa:c	Ljava/lang/String;
    //   51: ifnonnull +9 -> 60
    //   54: ldc 97
    //   56: astore_0
    //   57: goto -44 -> 13
    //   60: getstatic 33	com/xinmei365/font/wa:c	Ljava/lang/String;
    //   63: astore_0
    //   64: goto -51 -> 13
    //   67: astore_0
    //   68: ldc 2
    //   70: monitorexit
    //   71: aload_0
    //   72: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	73	0	paramContext	Context
    // Exception table:
    //   from	to	target	type
    //   3	13	67	finally
    //   18	54	67	finally
    //   60	64	67	finally
  }
  
  public static String i(String paramString)
  {
    try
    {
      if ((paramString.contains("api.tinyhoneybee.com")) && (h("api.tinyhoneybee.com") != null)) {
        return paramString.replace("api.tinyhoneybee.com", h("api.tinyhoneybee.com"));
      }
      if ((paramString.contains("smart.tinyhoneybee.com")) && (h("smart.tinyhoneybee.com") != null))
      {
        String str = paramString.replace("smart.tinyhoneybee.com", h("smart.tinyhoneybee.com"));
        return str;
      }
    }
    catch (Exception localException)
    {
      if (vr.a()) {
        vr.b(ur.a(localException));
      }
    }
    return paramString;
  }
  
  public static void i()
  {
    um.B(0);
  }
  
  public static String j()
  {
    try
    {
      String str = Build.MANUFACTURER;
      return str;
    }
    catch (Exception localException)
    {
      if (vr.a()) {
        vr.b(ur.a(localException));
      }
    }
    return "";
  }
  
  public static String j(Context paramContext)
  {
    for (;;)
    {
      try
      {
        if (e != null)
        {
          paramContext = e;
          return paramContext;
        }
        PackageManager localPackageManager = paramContext.getPackageManager();
        try
        {
          e = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).versionName;
          if (e == null) {
            paramContext = "1.0";
          }
        }
        catch (PackageManager.NameNotFoundException paramContext)
        {
          paramContext.printStackTrace();
          continue;
        }
        paramContext = e;
      }
      finally {}
    }
  }
  
  public static String j(String paramString)
  {
    try
    {
      paramString = new StringBuffer(new String(Base64.encode(("AA" + paramString + "ZZ").getBytes(), 4))).reverse().toString();
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  public static String k()
  {
    try
    {
      String str = Build.PRODUCT;
      return str;
    }
    catch (Exception localException)
    {
      if (vr.a()) {
        vr.b(ur.a(localException));
      }
    }
    return "";
  }
  
  public static String k(Context paramContext)
  {
    return Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
  }
  
  public static boolean k(String paramString)
  {
    boolean bool2 = false;
    String[] arrayOfString = um.W();
    int i1 = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i1 < arrayOfString.length - 1)
      {
        if (paramString.toLowerCase().endsWith(arrayOfString[i1])) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i1 += 1;
    }
  }
  
  public static String l()
  {
    try
    {
      Object localObject = TimeZone.getDefault();
      localObject = ((TimeZone)localObject).getDisplayName(false, 0) + " " + ((TimeZone)localObject).getID();
      return localObject;
    }
    catch (Exception localException)
    {
      if (vr.a()) {
        vr.b(ur.a(localException));
      }
    }
    return "";
  }
  
  @SuppressLint({"NewApi"})
  public static String l(Context paramContext)
  {
    if (paramContext != null) {}
    for (;;)
    {
      try
      {
        paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getSimCountryIso();
        Object localObject;
        if (paramContext != null)
        {
          localObject = paramContext;
          if (!paramContext.isEmpty()) {}
        }
        else
        {
          localObject = Locale.getDefault().getCountry();
        }
        return localObject;
      }
      catch (Exception paramContext)
      {
        if (vr.a()) {
          vr.b(ur.a(paramContext));
        }
      }
      paramContext = null;
    }
  }
  
  public static String l(String paramString)
  {
    int i3 = 0;
    Object localObject2 = "";
    Object localObject1 = localObject2;
    int i2;
    int[] arrayOfInt;
    int i1;
    try
    {
      String[] arrayOfString = paramString.split(",");
      localObject1 = localObject2;
      i2 = Array.getLength(arrayOfString);
      localObject1 = localObject2;
      arrayOfInt = new int[i2];
      i1 = 0;
      while (i1 < i2)
      {
        localObject1 = localObject2;
        arrayOfInt[i1] = Integer.parseInt(arrayOfString[i1], 8);
        i1 += 1;
        continue;
        for (;;)
        {
          localObject1 = paramString;
          localObject2 = paramString;
          if (i1 >= Array.getLength(arrayOfString)) {
            break;
          }
          localObject1 = paramString;
          paramString = paramString + String.format("%c", new Object[] { Character.valueOf((char)arrayOfInt[i1]) });
          i1 += 1;
        }
        return localObject2;
      }
    }
    catch (Exception paramString)
    {
      localObject2 = localObject1;
    }
    i2 -= 1;
    for (;;)
    {
      paramString = (String)localObject2;
      i1 = i3;
      if (i2 < 0) {
        break;
      }
      if (i2 != 0) {
        arrayOfInt[i2] -= arrayOfInt[(i2 - 1)] % 17;
      }
      i2 -= 1;
    }
  }
  
  public static String m(Context paramContext)
  {
    return ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
  }
  
  /* Error */
  public static List<String> m(String paramString)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic 1020	com/xinmei365/font/vv$g:a	(Ljava/lang/String;)Ljava/util/List;
    //   7: astore_0
    //   8: invokestatic 87	com/xinmei365/font/vr:a	()Z
    //   11: ifeq +76 -> 87
    //   14: aload_0
    //   15: invokeinterface 510 1 0
    //   20: astore_1
    //   21: aload_1
    //   22: invokeinterface 273 1 0
    //   27: ifeq +60 -> 87
    //   30: aload_1
    //   31: invokeinterface 277 1 0
    //   36: checkcast 279	java/lang/String
    //   39: astore_2
    //   40: new 193	java/lang/StringBuilder
    //   43: dup
    //   44: invokespecial 194	java/lang/StringBuilder:<init>	()V
    //   47: ldc_w 1022
    //   50: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: aload_2
    //   54: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   60: invokestatic 186	com/xinmei365/font/vr:a	(Ljava/lang/String;)V
    //   63: goto -42 -> 21
    //   66: astore_0
    //   67: invokestatic 87	com/xinmei365/font/vr:a	()Z
    //   70: ifeq +10 -> 80
    //   73: aload_0
    //   74: invokestatic 92	com/xinmei365/font/ur:a	(Ljava/lang/Exception;)Ljava/lang/String;
    //   77: invokestatic 186	com/xinmei365/font/vr:a	(Ljava/lang/String;)V
    //   80: aconst_null
    //   81: astore_0
    //   82: ldc 2
    //   84: monitorexit
    //   85: aload_0
    //   86: areturn
    //   87: goto -5 -> 82
    //   90: astore_0
    //   91: ldc 2
    //   93: monitorexit
    //   94: aload_0
    //   95: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	96	0	paramString	String
    //   20	11	1	localIterator	Iterator
    //   39	15	2	str	String
    // Exception table:
    //   from	to	target	type
    //   3	21	66	java/lang/Exception
    //   21	63	66	java/lang/Exception
    //   3	21	90	finally
    //   21	63	90	finally
    //   67	80	90	finally
  }
  
  public static String n(Context paramContext)
  {
    if (f != null) {
      return f;
    }
    return null;
  }
  
  public static boolean o(Context paramContext)
  {
    try
    {
      if (paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0)
      {
        paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (paramContext == null) {
          return false;
        }
        int i1 = paramContext.getType();
        if ((i1 == 1) || (i1 == 0))
        {
          boolean bool = paramContext.isConnected();
          return bool;
        }
        return false;
      }
      return true;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static String p(Context paramContext)
  {
    int i1;
    int i2;
    try
    {
      if (paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0)
      {
        paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (paramContext == null) {
          return "UNKNOWN";
        }
        i1 = paramContext.getType();
        i2 = paramContext.getSubtype();
        if (i1 == 1) {
          return "WIFI";
        }
      }
      else
      {
        return "UNKNOWN";
      }
    }
    catch (Exception paramContext)
    {
      return "";
    }
    if (i1 == 6) {
      return "WIMAX";
    }
    if (i1 == 0)
    {
      switch (i2)
      {
      default: 
        return "MOBILE";
      case 1: 
      case 2: 
      case 4: 
      case 7: 
      case 11: 
        return "2G";
      case 3: 
      case 5: 
      case 6: 
      case 8: 
      case 9: 
      case 10: 
      case 12: 
      case 14: 
      case 15: 
        return "3G";
      }
      return "4G";
    }
    return "UNKNOWN";
  }
  
  public static String q(Context paramContext)
  {
    return System.getProperty("http.agent");
  }
  
  public static int r(Context paramContext)
  {
    try
    {
      int i1 = ((Integer)ActivityManager.class.getMethod("getMemoryClass", new Class[0]).invoke((ActivityManager)paramContext.getSystemService("activity"), new Object[0])).intValue();
      return i1;
    }
    catch (Exception paramContext)
    {
      if (vr.a()) {
        vr.b(ur.a(paramContext));
      }
    }
    return 16;
  }
  
  public static String s(Context paramContext)
  {
    try
    {
      if (TextUtils.isEmpty(o)) {
        o = System.getProperty("http.agent");
      }
      paramContext = o;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = "Apache-HttpClient/UNAVAILABLE (java 1.4)";
      }
    }
    finally {}
    return paramContext;
  }
  
  public static boolean t(Context paramContext)
  {
    try
    {
      if (b(paramContext, "android.permission.ACCESS_WIFI_STATE"))
      {
        paramContext = (ConnectivityManager)paramContext.getApplicationContext().getSystemService("connectivity");
        if (paramContext != null)
        {
          paramContext = paramContext.getAllNetworkInfo();
          if (paramContext != null)
          {
            int i1 = 0;
            while (i1 < paramContext.length)
            {
              if (paramContext[i1].getTypeName().equals("WIFI"))
              {
                boolean bool = paramContext[i1].isConnected();
                if (bool) {
                  return true;
                }
              }
              i1 += 1;
            }
          }
        }
        return false;
      }
      return false;
    }
    catch (Exception paramContext)
    {
      if (vr.a()) {
        vr.b(ur.a(paramContext));
      }
    }
    return false;
  }
  
  public static String u(Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    try
    {
      if (b(paramContext, "android.permission.READ_PHONE_STATE")) {
        localObject1 = Build.VERSION.RELEASE;
      }
      return localObject1;
    }
    catch (Exception paramContext)
    {
      do
      {
        localObject1 = localObject2;
      } while (!vr.a());
      vr.b(ur.a(paramContext));
    }
    return null;
  }
  
  public static String v(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if (paramContext != null)
      {
        paramContext = paramContext.metaData.getString("KOALA_CHANNEL");
        if (paramContext != null)
        {
          paramContext = paramContext.toString();
          return paramContext;
        }
      }
    }
    catch (Exception paramContext)
    {
      if (vr.a()) {
        vr.b(ur.a(paramContext));
      }
    }
    return "";
  }
  
  @Deprecated
  public static String w(Context paramContext)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext.getSimCountryIso() == null) {
        return Locale.getDefault().getCountry();
      }
      paramContext = paramContext.getSimCountryIso();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      if (vr.a()) {
        vr.b(ur.a(paramContext));
      }
    }
    return "";
  }
  
  public static String x(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      Context localContext;
      if (paramContext != null) {
        localContext = paramContext;
      }
      return localContext;
    }
    catch (Exception localException1)
    {
      try
      {
        if (paramContext.length() > 0) {
          return localContext;
        }
        return "";
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
      localException1 = localException1;
      paramContext = "";
      localContext = paramContext;
      if (vr.a())
      {
        vr.b(ur.a(localException1));
        return paramContext;
      }
    }
  }
  
  public static String y(Context paramContext)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext.getSimState() == 5)
      {
        if (paramContext.getSimOperatorName() == null) {
          return "";
        }
        return paramContext.getSimOperatorName();
      }
      return "";
    }
    catch (Exception paramContext)
    {
      if (vr.a()) {
        vr.b(ur.a(paramContext));
      }
    }
    return "";
  }
  
  public static String z(Context paramContext)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext.getSimState() == 5) {
        return paramContext.getSimOperator();
      }
      return "";
    }
    catch (Exception paramContext)
    {
      if (vr.a()) {
        vr.b(ur.a(paramContext));
      }
    }
    return "";
  }
}
