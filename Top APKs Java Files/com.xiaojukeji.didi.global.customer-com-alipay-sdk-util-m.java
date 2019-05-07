package com.alipay.sdk.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.i;
import com.alipay.sdk.app.j;
import com.alipay.sdk.app.k;
import com.alipay.sdk.data.a.a;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled", "DefaultLocale"})
public class m
{
  static final String a = "com.alipay.android.app";
  public static final String b = "com.eg.android.AlipayGphone";
  public static final int c = 99;
  public static final int d = 73;
  public static final String[] e = { "10.1.5.1013151", "10.1.5.1013148" };
  private static final String f = "com.eg.android.AlipayGphoneRC";
  private static final String g = ".alipay.wallet";
  private static final char[] h = "0123456789ABCDEF".toCharArray();
  
  public m() {}
  
  public static WebView a(Activity paramActivity, String paramString1, String paramString2)
  {
    Context localContext = paramActivity.getApplicationContext();
    if (!TextUtils.isEmpty(paramString2))
    {
      CookieSyncManager.createInstance(localContext).sync();
      CookieManager.getInstance().setCookie(paramString1, paramString2);
      CookieSyncManager.getInstance().sync();
    }
    paramString2 = new LinearLayout(localContext);
    Object localObject = new LinearLayout.LayoutParams(-1, -1);
    paramString2.setOrientation(1);
    paramActivity.setContentView(paramString2, (ViewGroup.LayoutParams)localObject);
    paramActivity = new WebView(localContext);
    ((LinearLayout.LayoutParams)localObject).weight = 1.0F;
    paramActivity.setVisibility(0);
    paramString2.addView(paramActivity, (ViewGroup.LayoutParams)localObject);
    paramString2 = paramActivity.getSettings();
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramString2.getUserAgentString());
    ((StringBuilder)localObject).append(c(localContext));
    paramString2.setUserAgentString(((StringBuilder)localObject).toString());
    paramString2.setRenderPriority(WebSettings.RenderPriority.HIGH);
    paramString2.setSupportMultipleWindows(true);
    paramString2.setJavaScriptEnabled(true);
    paramString2.setSavePassword(false);
    paramString2.setJavaScriptCanOpenWindowsAutomatically(true);
    paramString2.setMinimumFontSize(paramString2.getMinimumFontSize() + 8);
    paramString2.setAllowFileAccess(false);
    paramString2.setTextSize(WebSettings.TextSize.NORMAL);
    paramActivity.setVerticalScrollbarOverlay(true);
    paramActivity.setDownloadListener(new n(localContext));
    if (Build.VERSION.SDK_INT >= 7) {}
    for (;;)
    {
      try
      {
        paramString2 = paramActivity.getSettings().getClass().getMethod("setDomStorageEnabled", new Class[] { Boolean.TYPE });
        if (paramString2 != null) {
          paramString2.invoke(paramActivity.getSettings(), new Object[] { Boolean.valueOf(true) });
        }
      }
      catch (Exception paramString2)
      {
        continue;
      }
      try
      {
        paramActivity.removeJavascriptInterface("searchBoxJavaBridge_");
        paramActivity.removeJavascriptInterface("accessibility");
        paramActivity.removeJavascriptInterface("accessibilityTraversal");
      }
      catch (Throwable paramString2)
      {
        continue;
      }
      try
      {
        paramString2 = paramActivity.getClass().getMethod("removeJavascriptInterface", new Class[0]);
        if (paramString2 != null)
        {
          paramString2.invoke(paramActivity, new Object[] { "searchBoxJavaBridge_" });
          paramString2.invoke(paramActivity, new Object[] { "accessibility" });
          paramString2.invoke(paramActivity, new Object[] { "accessibilityTraversal" });
        }
      }
      catch (Throwable paramString2) {}
    }
    if (Build.VERSION.SDK_INT >= 19) {
      paramActivity.getSettings().setCacheMode(2);
    }
    paramActivity.loadUrl(paramString1);
    return paramActivity;
  }
  
  /* Error */
  private static a a(Context paramContext, String paramString1, int paramInt, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokestatic 257	com/alipay/sdk/util/m:d	(Landroid/content/Context;Ljava/lang/String;)Landroid/content/pm/PackageInfo;
    //   5: astore 4
    //   7: aload 4
    //   9: invokestatic 260	com/alipay/sdk/util/m:b	(Landroid/content/pm/PackageInfo;)Z
    //   12: ifne +23 -> 35
    //   15: aload_0
    //   16: aload_1
    //   17: invokestatic 262	com/alipay/sdk/util/m:e	(Landroid/content/Context;Ljava/lang/String;)Landroid/content/pm/PackageInfo;
    //   20: astore_0
    //   21: goto +66 -> 87
    //   24: astore_0
    //   25: ldc_w 264
    //   28: ldc_w 266
    //   31: aload_0
    //   32: invokestatic 271	com/alipay/sdk/app/statistic/a:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   35: aload 4
    //   37: astore_0
    //   38: goto +49 -> 87
    //   41: astore_3
    //   42: goto +61 -> 103
    //   45: astore 4
    //   47: ldc_w 264
    //   50: ldc_w 273
    //   53: aload 4
    //   55: invokestatic 271	com/alipay/sdk/app/statistic/a:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   58: aconst_null
    //   59: invokestatic 260	com/alipay/sdk/util/m:b	(Landroid/content/pm/PackageInfo;)Z
    //   62: ifne +23 -> 85
    //   65: aload_0
    //   66: aload_1
    //   67: invokestatic 262	com/alipay/sdk/util/m:e	(Landroid/content/Context;Ljava/lang/String;)Landroid/content/pm/PackageInfo;
    //   70: astore_0
    //   71: goto +16 -> 87
    //   74: astore_0
    //   75: ldc_w 264
    //   78: ldc_w 266
    //   81: aload_0
    //   82: invokestatic 271	com/alipay/sdk/app/statistic/a:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   85: aconst_null
    //   86: astore_0
    //   87: aload_0
    //   88: invokestatic 260	com/alipay/sdk/util/m:b	(Landroid/content/pm/PackageInfo;)Z
    //   91: ifne +5 -> 96
    //   94: aconst_null
    //   95: areturn
    //   96: aload_0
    //   97: iload_2
    //   98: aload_3
    //   99: invokestatic 276	com/alipay/sdk/util/m:a	(Landroid/content/pm/PackageInfo;ILjava/lang/String;)Lcom/alipay/sdk/util/m$a;
    //   102: areturn
    //   103: aconst_null
    //   104: invokestatic 260	com/alipay/sdk/util/m:b	(Landroid/content/pm/PackageInfo;)Z
    //   107: ifne +23 -> 130
    //   110: aload_0
    //   111: aload_1
    //   112: invokestatic 262	com/alipay/sdk/util/m:e	(Landroid/content/Context;Ljava/lang/String;)Landroid/content/pm/PackageInfo;
    //   115: pop
    //   116: goto +14 -> 130
    //   119: astore_0
    //   120: ldc_w 264
    //   123: ldc_w 266
    //   126: aload_0
    //   127: invokestatic 271	com/alipay/sdk/app/statistic/a:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   130: aload_3
    //   131: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	132	0	paramContext	Context
    //   0	132	1	paramString1	String
    //   0	132	2	paramInt	int
    //   0	132	3	paramString2	String
    //   5	31	4	localPackageInfo	PackageInfo
    //   45	9	4	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   15	21	24	java/lang/Throwable
    //   0	7	41	finally
    //   47	58	41	finally
    //   0	7	45	java/lang/Throwable
    //   65	71	74	java/lang/Throwable
    //   110	116	119	java/lang/Throwable
  }
  
  public static a a(Context paramContext, List<a.a> paramList)
  {
    if (paramList == null) {
      return null;
    }
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Object localObject = (a.a)paramList.next();
      if (localObject != null)
      {
        localObject = a(paramContext, ((a.a)localObject).a, ((a.a)localObject).b, ((a.a)localObject).c);
        if ((localObject != null) && (!((a)localObject).a()) && (!((a)localObject).b())) {
          return localObject;
        }
      }
    }
    return null;
  }
  
  private static a a(PackageInfo paramPackageInfo, int paramInt, String paramString)
  {
    if (paramPackageInfo == null) {
      return null;
    }
    return new a(paramPackageInfo, paramInt, paramString);
  }
  
  public static String a()
  {
    if (EnvUtils.isSandBox()) {
      return "com.eg.android.AlipayGphoneRC";
    }
    try
    {
      String str = ((a.a)i.a.get(0)).a;
      return str;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return "com.eg.android.AlipayGphone";
  }
  
  public static String a(int paramInt)
  {
    Random localRandom = new Random();
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramInt)
    {
      switch (localRandom.nextInt(3))
      {
      default: 
        break;
      case 2: 
        localStringBuilder.append(String.valueOf(new Random().nextInt(10)));
        break;
      case 1: 
        localStringBuilder.append(String.valueOf((char)(int)Math.round(Math.random() * 25.0D + 97.0D)));
        break;
      case 0: 
        localStringBuilder.append(String.valueOf((char)(int)Math.round(Math.random() * 25.0D + 65.0D)));
      }
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static String a(Context paramContext, String paramString)
  {
    Object localObject1 = "";
    try
    {
      Iterator localIterator = ((ActivityManager)paramContext.getApplicationContext().getSystemService("activity")).getRunningAppProcesses().iterator();
      paramContext = (Context)localObject1;
      for (;;)
      {
        localObject1 = paramContext;
        if (!localIterator.hasNext()) {
          break;
        }
        localObject1 = (ActivityManager.RunningAppProcessInfo)localIterator.next();
        if (((ActivityManager.RunningAppProcessInfo)localObject1).processName.equals(paramString))
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append(paramContext);
          ((StringBuilder)localObject1).append("#M");
          paramContext = ((StringBuilder)localObject1).toString();
        }
        else
        {
          Object localObject2 = ((ActivityManager.RunningAppProcessInfo)localObject1).processName;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(paramString);
          localStringBuilder.append(":");
          if (((String)localObject2).startsWith(localStringBuilder.toString()))
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append(paramContext);
            ((StringBuilder)localObject2).append("#");
            paramContext = ((ActivityManager.RunningAppProcessInfo)localObject1).processName;
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append(paramString);
            ((StringBuilder)localObject1).append(":");
            ((StringBuilder)localObject2).append(paramContext.replace(((StringBuilder)localObject1).toString(), ""));
            paramContext = ((StringBuilder)localObject2).toString();
          }
        }
      }
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    localObject1 = "";
    paramContext = (Context)localObject1;
    if (((String)localObject1).length() > 0) {
      paramContext = ((String)localObject1).substring(1);
    }
    paramString = paramContext;
    if (paramContext.length() == 0) {
      paramString = "N";
    }
    return paramString;
  }
  
  public static String a(Signature paramSignature)
  {
    try
    {
      paramSignature = b(MessageDigest.getInstance("SHA-256").digest(paramSignature.toByteArray()));
      return paramSignature;
    }
    catch (Throwable paramSignature)
    {
      com.alipay.sdk.app.statistic.a.a("biz", "CheckClientSignEx", paramSignature);
    }
    return "";
  }
  
  public static String a(String paramString)
  {
    if ((EnvUtils.isSandBox()) && (TextUtils.equals(paramString, "com.eg.android.AlipayGphone"))) {
      return "com.eg.android.AlipayGphoneRC.IAlixPay";
    }
    return "com.eg.android.AlipayGphone.IAlixPay";
  }
  
  public static String a(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      int j = paramString3.indexOf(paramString1) + paramString1.length();
      if (j <= paramString1.length()) {
        return "";
      }
      int i = 0;
      if (!TextUtils.isEmpty(paramString2)) {
        i = paramString3.indexOf(paramString2, j);
      }
      if (i < 1) {
        return paramString3.substring(j);
      }
      paramString1 = paramString3.substring(j, i);
      return paramString1;
    }
    catch (Throwable paramString1)
    {
      for (;;) {}
    }
    return "";
  }
  
  public static String a(byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = ((X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(paramArrayOfByte))).getPublicKey();
      if ((paramArrayOfByte instanceof RSAPublicKey))
      {
        paramArrayOfByte = ((RSAPublicKey)paramArrayOfByte).getModulus();
        if (paramArrayOfByte != null)
        {
          paramArrayOfByte = paramArrayOfByte.toString(16);
          return paramArrayOfByte;
        }
      }
    }
    catch (Exception paramArrayOfByte)
    {
      com.alipay.sdk.app.statistic.a.a("auth", "GetPublicKeyFromSignEx", paramArrayOfByte);
    }
    return null;
  }
  
  public static boolean a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo("com.alipay.android.app", 128);
      return paramContext != null;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean a(PackageInfo paramPackageInfo)
  {
    if (paramPackageInfo == null) {
      return false;
    }
    try
    {
      paramPackageInfo = paramPackageInfo.versionName;
      if (!TextUtils.equals(paramPackageInfo, e[0]))
      {
        boolean bool = TextUtils.equals(paramPackageInfo, e[1]);
        return bool;
      }
      return true;
    }
    catch (Throwable paramPackageInfo) {}
    return false;
  }
  
  public static boolean a(WebView paramWebView, String paramString, Activity paramActivity)
  {
    if (TextUtils.isEmpty(paramString)) {
      return true;
    }
    if ((paramString.toLowerCase().startsWith("alipays://platformapi/startApp?".toLowerCase())) || (paramString.toLowerCase().startsWith("intent://platformapi/startapp?".toLowerCase())) || ((TextUtils.equals(paramString, "sdklite://h5quit")) || (TextUtils.equals(paramString, "http://m.alipay.com/?action=h5quit")) || (paramString.startsWith("sdklite://h5quit?result=")))) {}
    try
    {
      paramWebView = paramString.substring(paramString.indexOf("sdklite://h5quit?result=") + "sdklite://h5quit?result=".length());
      int i = Integer.parseInt(paramWebView.substring(paramWebView.lastIndexOf("&end_code=") + "&end_code=".length()));
      if ((i != k.a.a()) && (i != k.g.a()))
      {
        paramWebView = k.b(k.b.a());
        j.a(j.a(paramWebView.a(), paramWebView.b(), ""));
      }
      else
      {
        if (com.alipay.sdk.cons.a.s)
        {
          paramWebView = new StringBuilder();
          paramString = URLDecoder.decode(paramString);
          String str = URLDecoder.decode(paramString);
          str = str.substring(str.indexOf("sdklite://h5quit?result=") + "sdklite://h5quit?result=".length(), str.lastIndexOf("&end_code=")).split("&return_url=")[0];
          int j = paramString.indexOf("&return_url=") + "&return_url=".length();
          paramWebView.append(str);
          paramWebView.append("&return_url=");
          paramWebView.append(paramString.substring(j, paramString.indexOf("&", j)));
          paramWebView.append(paramString.substring(paramString.indexOf("&", j)));
          paramWebView = paramWebView.toString();
        }
        else
        {
          paramWebView = URLDecoder.decode(paramString);
          paramWebView = paramWebView.substring(paramWebView.indexOf("sdklite://h5quit?result=") + "sdklite://h5quit?result=".length(), paramWebView.lastIndexOf("&end_code="));
        }
        paramString = k.b(i);
        j.a(j.a(paramString.a(), paramString.b(), paramWebView));
      }
    }
    catch (Exception paramWebView)
    {
      for (;;)
      {
        try
        {
          paramWebView = a(paramActivity, i.a);
          if ((paramWebView != null) && (!paramWebView.b()))
          {
            if (paramWebView.a()) {
              return true;
            }
            paramWebView = paramString;
            if (paramString.startsWith("intent://platformapi/startapp")) {
              paramWebView = paramString.replaceFirst("intent://platformapi/startapp\\?", "alipays://platformapi/startApp?");
            }
            paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramWebView)));
            return true;
          }
          return true;
        }
        catch (Throwable paramWebView) {}
        paramWebView = paramWebView;
      }
    }
    j.a(j.e());
    paramActivity.runOnUiThread(new o(paramActivity));
    return true;
    paramWebView.loadUrl(paramString);
    return true;
    j.a(j.c());
    paramActivity.finish();
    return true;
    return true;
  }
  
  public static String b()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Android ");
    localStringBuilder.append(Build.VERSION.RELEASE);
    return localStringBuilder.toString();
  }
  
  public static String b(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 128).versionName;
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      com.alipay.sdk.app.statistic.a.a("biz", "GetPackageInfoEx", paramContext);
    }
    return "";
  }
  
  public static String b(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      int j = paramString3.indexOf(paramString1) + paramString1.length();
      int i = 0;
      if (!TextUtils.isEmpty(paramString2)) {
        i = paramString3.indexOf(paramString2, j);
      }
      if (i < 1) {
        return paramString3.substring(j);
      }
      paramString1 = paramString3.substring(j, i);
      return paramString1;
    }
    catch (Throwable paramString1)
    {
      for (;;) {}
    }
    return "";
  }
  
  private static String b(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer(paramArrayOfByte.length * 3 - 1);
    int j = paramArrayOfByte.length - 1;
    int i = 0;
    while (i <= j)
    {
      int k = paramArrayOfByte[i];
      localStringBuffer.append(h[((k & 0xF0) >> 4)]);
      localStringBuffer.append(h[(k & 0xF)]);
      if (i < j) {
        localStringBuffer.append(':');
      }
      i += 1;
    }
    return localStringBuffer.toString();
  }
  
  public static Map<String, String> b(String paramString)
  {
    HashMap localHashMap = new HashMap();
    paramString = paramString.split("&");
    int j = paramString.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramString[i];
      int k = localObject.indexOf("=", 1);
      if (-1 != k) {
        localHashMap.put(localObject.substring(0, k), URLDecoder.decode(localObject.substring(k + 1)));
      }
      i += 1;
    }
    return localHashMap;
  }
  
  public static boolean b(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(a(), 128);
      if (paramContext == null) {
        return false;
      }
      int i = paramContext.versionCode;
      return i < 99;
    }
    catch (Throwable paramContext)
    {
      c.a(paramContext);
    }
    return false;
  }
  
  public static boolean b(Context paramContext, List<a.a> paramList)
  {
    for (;;)
    {
      try
      {
        paramList = paramList.iterator();
        if (paramList.hasNext())
        {
          localObject = (a.a)paramList.next();
          if (localObject == null) {
            continue;
          }
          localObject = ((a.a)localObject).a;
          localPackageManager = paramContext.getPackageManager();
        }
      }
      catch (Throwable paramContext)
      {
        Object localObject;
        PackageManager localPackageManager;
        com.alipay.sdk.app.statistic.a.a("biz", "CheckLaunchAppExistEx", paramContext);
        return false;
      }
      try
      {
        localObject = localPackageManager.getPackageInfo((String)localObject, 128);
        if (localObject == null) {
          continue;
        }
        return true;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
      return false;
    }
  }
  
  private static boolean b(PackageInfo paramPackageInfo)
  {
    String str = "";
    boolean bool = false;
    if (paramPackageInfo == null)
    {
      paramPackageInfo = new StringBuilder();
      paramPackageInfo.append("");
      paramPackageInfo.append("info == null");
      paramPackageInfo = paramPackageInfo.toString();
    }
    else if (paramPackageInfo.signatures == null)
    {
      paramPackageInfo = new StringBuilder();
      paramPackageInfo.append("");
      paramPackageInfo.append("info.signatures == null");
      paramPackageInfo = paramPackageInfo.toString();
    }
    else if (paramPackageInfo.signatures.length <= 0)
    {
      paramPackageInfo = new StringBuilder();
      paramPackageInfo.append("");
      paramPackageInfo.append("info.signatures.length <= 0");
      paramPackageInfo = paramPackageInfo.toString();
    }
    else
    {
      bool = true;
      paramPackageInfo = str;
    }
    if (!bool) {
      com.alipay.sdk.app.statistic.a.a("auth", "NotIncludeSignatures", paramPackageInfo);
    }
    return bool;
  }
  
  public static int c(Context paramContext, String paramString)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramString, 128).versionCode;
      return i;
    }
    catch (Throwable paramContext)
    {
      com.alipay.sdk.app.statistic.a.a("biz", "GetPackageInfoEx", paramContext);
    }
    return -1;
  }
  
  public static String c()
  {
    Object localObject2 = d();
    int i = ((String)localObject2).indexOf("-");
    Object localObject1 = localObject2;
    if (i != -1) {
      localObject1 = ((String)localObject2).substring(0, i);
    }
    i = ((String)localObject1).indexOf("\n");
    localObject2 = localObject1;
    if (i != -1) {
      localObject2 = ((String)localObject1).substring(0, i);
    }
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("Linux ");
    ((StringBuilder)localObject1).append((String)localObject2);
    return ((StringBuilder)localObject1).toString();
  }
  
  public static String c(Context paramContext)
  {
    String str1 = b();
    String str2 = c();
    String str3 = d(paramContext);
    paramContext = e(paramContext);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(" (");
    localStringBuilder.append(str1);
    localStringBuilder.append(";");
    localStringBuilder.append(str2);
    localStringBuilder.append(";");
    localStringBuilder.append(str3);
    localStringBuilder.append(";");
    localStringBuilder.append(";");
    localStringBuilder.append(paramContext);
    localStringBuilder.append(")");
    localStringBuilder.append("(sdk android)");
    return localStringBuilder.toString();
  }
  
  public static Map<String, String> c(String paramString)
  {
    HashMap localHashMap = new HashMap(4);
    int i = paramString.indexOf('?');
    if ((i != -1) && (i < paramString.length() - 1))
    {
      paramString = paramString.substring(i + 1).split("&");
      int j = paramString.length;
      i = 0;
      while (i < j)
      {
        Object localObject = paramString[i];
        int k = localObject.indexOf('=', 1);
        if ((k != -1) && (k < localObject.length() - 1)) {
          localHashMap.put(localObject.substring(0, k), e(localObject.substring(k + 1)));
        }
        i += 1;
      }
    }
    return localHashMap;
  }
  
  @SuppressLint({"InlinedApi"})
  private static boolean c(PackageInfo paramPackageInfo)
  {
    int i = paramPackageInfo.applicationInfo.flags;
    return ((i & 0x1) == 0) && ((i & 0x80) == 0);
  }
  
  private static PackageInfo d(Context paramContext, String paramString)
    throws PackageManager.NameNotFoundException
  {
    return paramContext.getPackageManager().getPackageInfo(paramString, 192);
  }
  
  public static String d()
  {
    try
    {
      Object localObject1 = new BufferedReader(new FileReader("/proc/version"), 256);
      try
      {
        Object localObject2 = ((BufferedReader)localObject1).readLine();
        ((BufferedReader)localObject1).close();
        localObject1 = Pattern.compile("\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)").matcher((CharSequence)localObject2);
        if (!((Matcher)localObject1).matches()) {
          return "Unavailable";
        }
        if (((Matcher)localObject1).groupCount() < 4) {
          return "Unavailable";
        }
        localObject2 = new StringBuilder(((Matcher)localObject1).group(1));
        ((StringBuilder)localObject2).append("\n");
        ((StringBuilder)localObject2).append(((Matcher)localObject1).group(2));
        ((StringBuilder)localObject2).append(" ");
        ((StringBuilder)localObject2).append(((Matcher)localObject1).group(3));
        ((StringBuilder)localObject2).append("\n");
        ((StringBuilder)localObject2).append(((Matcher)localObject1).group(4));
        return ((StringBuilder)localObject2).toString();
      }
      finally
      {
        ((BufferedReader)localObject1).close();
      }
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
    return "Unavailable";
  }
  
  public static String d(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().locale.toString();
  }
  
  public static JSONObject d(String paramString)
  {
    try
    {
      paramString = new JSONObject(paramString);
      return paramString;
    }
    catch (Throwable paramString)
    {
      for (;;) {}
    }
    return new JSONObject();
  }
  
  private static PackageInfo e(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(192).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if (localPackageInfo.packageName.equals(paramString)) {
        return localPackageInfo;
      }
    }
    return null;
  }
  
  public static String e(Context paramContext)
  {
    paramContext = f(paramContext);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramContext.widthPixels);
    localStringBuilder.append("*");
    localStringBuilder.append(paramContext.heightPixels);
    return localStringBuilder.toString();
  }
  
  public static String e(String paramString)
  {
    try
    {
      paramString = URLDecoder.decode(paramString, "utf-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      com.alipay.sdk.app.statistic.a.a("biz", "H5PayDataAnalysisError", paramString);
    }
    return "";
  }
  
  public static DisplayMetrics f(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }
  
  public static boolean f(String paramString)
  {
    return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipaydev|alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(paramString).matches();
  }
  
  public static String g(Context paramContext)
  {
    paramContext = l.a(paramContext);
    return paramContext.substring(0, paramContext.indexOf("://"));
  }
  
  public static String h(Context paramContext)
  {
    return "-1;-1";
  }
  
  public static String i(Context paramContext)
  {
    for (;;)
    {
      int i;
      try
      {
        paramContext = paramContext.getPackageManager();
        i = 0;
        paramContext = paramContext.getInstalledPackages(0);
        StringBuilder localStringBuilder = new StringBuilder();
        if (i < paramContext.size())
        {
          PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
          if (c(localPackageInfo)) {
            if (localPackageInfo.packageName.equals(a()))
            {
              localStringBuilder.append(localPackageInfo.packageName);
              localStringBuilder.append(localPackageInfo.versionCode);
              localStringBuilder.append("-");
            }
            else if ((!localPackageInfo.packageName.contains("theme")) && (!localPackageInfo.packageName.startsWith("com.google.")) && (!localPackageInfo.packageName.startsWith("com.android.")))
            {
              localStringBuilder.append(localPackageInfo.packageName);
              localStringBuilder.append("-");
            }
          }
        }
        else
        {
          paramContext = localStringBuilder.toString();
          return paramContext;
        }
      }
      catch (Throwable paramContext)
      {
        com.alipay.sdk.app.statistic.a.a("biz", "GetInstalledAppEx", paramContext);
        return "";
      }
      i += 1;
    }
  }
  
  public static String j(Context paramContext)
  {
    return b(paramContext, paramContext.getPackageName());
  }
  
  public static int k(Context paramContext)
  {
    return c(paramContext, paramContext.getPackageName());
  }
  
  public static final class a
  {
    public final PackageInfo a;
    public final int b;
    public final String c;
    
    public a(PackageInfo paramPackageInfo, int paramInt, String paramString)
    {
      this.a = paramPackageInfo;
      this.b = paramInt;
      this.c = paramString;
    }
    
    public boolean a()
    {
      Signature[] arrayOfSignature = this.a.signatures;
      if (arrayOfSignature != null)
      {
        if (arrayOfSignature.length == 0) {
          return false;
        }
        int j = arrayOfSignature.length;
        int i = 0;
        while (i < j)
        {
          String str = m.a(arrayOfSignature[i].toByteArray());
          if ((str != null) && (!TextUtils.equals(str, this.c)))
          {
            com.alipay.sdk.app.statistic.a.a("biz", "PublicKeyUnmatch", String.format("Got %s, expected %s", new Object[] { str, this.c }));
            return true;
          }
          i += 1;
        }
        return false;
      }
      return false;
    }
    
    public boolean b()
    {
      return this.a.versionCode < this.b;
    }
  }
}
