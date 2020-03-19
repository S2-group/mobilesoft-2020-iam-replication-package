package com.alipay.sdk.j;

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
import android.view.View;
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
import com.alipay.sdk.app.a.a;
import com.alipay.sdk.app.j;
import com.alipay.sdk.app.k;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint({"SetJavaScriptEnabled", "DefaultLocale"})
public final class m
{
  static final String a = "com.alipay.android.app";
  public static final String b = "com.eg.android.AlipayGphone";
  public static final int c = 99;
  public static final int d = 73;
  
  public m() {}
  
  public static WebView a(Activity paramActivity, String paramString1, String paramString2)
  {
    if (!TextUtils.isEmpty(paramString2))
    {
      CookieSyncManager.createInstance(paramActivity.getApplicationContext()).sync();
      CookieManager.getInstance().setCookie(paramString1, paramString2);
      CookieSyncManager.getInstance().sync();
    }
    Object localObject1 = new LinearLayout(paramActivity);
    Object localObject2 = new LinearLayout.LayoutParams(-1, -1);
    ((LinearLayout)localObject1).setOrientation(1);
    paramActivity.setContentView((View)localObject1, (ViewGroup.LayoutParams)localObject2);
    paramString2 = new WebView(paramActivity);
    ((LinearLayout.LayoutParams)localObject2).weight = 1.0F;
    paramString2.setVisibility(0);
    ((LinearLayout)localObject1).addView(paramString2, (ViewGroup.LayoutParams)localObject2);
    localObject1 = paramString2.getSettings();
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(((WebSettings)localObject1).getUserAgentString());
    ((StringBuilder)localObject2).append(d(paramActivity));
    ((WebSettings)localObject1).setUserAgentString(((StringBuilder)localObject2).toString());
    ((WebSettings)localObject1).setRenderPriority(WebSettings.RenderPriority.HIGH);
    ((WebSettings)localObject1).setSupportMultipleWindows(true);
    ((WebSettings)localObject1).setJavaScriptEnabled(true);
    ((WebSettings)localObject1).setSavePassword(false);
    ((WebSettings)localObject1).setJavaScriptCanOpenWindowsAutomatically(true);
    ((WebSettings)localObject1).setMinimumFontSize(((WebSettings)localObject1).getMinimumFontSize() + 8);
    ((WebSettings)localObject1).setAllowFileAccess(false);
    ((WebSettings)localObject1).setTextSize(WebSettings.TextSize.NORMAL);
    paramString2.setVerticalScrollbarOverlay(true);
    paramString2.setDownloadListener(new n(paramActivity));
    if (Build.VERSION.SDK_INT >= 7) {}
    for (;;)
    {
      try
      {
        paramActivity = paramString2.getSettings().getClass().getMethod("setDomStorageEnabled", new Class[] { Boolean.TYPE });
        if (paramActivity != null) {
          paramActivity.invoke(paramString2.getSettings(), new Object[] { Boolean.valueOf(true) });
        }
      }
      catch (Exception paramActivity)
      {
        continue;
      }
      try
      {
        paramString2.removeJavascriptInterface("searchBoxJavaBridge_");
        paramString2.removeJavascriptInterface("accessibility");
        paramString2.removeJavascriptInterface("accessibilityTraversal");
      }
      catch (Throwable paramActivity)
      {
        continue;
      }
      try
      {
        paramActivity = paramString2.getClass().getMethod("removeJavascriptInterface", new Class[0]);
        if (paramActivity != null)
        {
          paramActivity.invoke(paramString2, new Object[] { "searchBoxJavaBridge_" });
          paramActivity.invoke(paramString2, new Object[] { "accessibility" });
          paramActivity.invoke(paramString2, new Object[] { "accessibilityTraversal" });
        }
      }
      catch (Throwable paramActivity) {}
    }
    if (Build.VERSION.SDK_INT >= 19) {
      paramString2.getSettings().setCacheMode(1);
    }
    paramString2.loadUrl(paramString1);
    return paramString2;
  }
  
  /* Error */
  public static a a(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 235	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: aload_1
    //   5: sipush 192
    //   8: invokevirtual 241	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   11: astore_2
    //   12: aload_2
    //   13: invokestatic 244	com/alipay/sdk/j/m:a	(Landroid/content/pm/PackageInfo;)Z
    //   16: ifne +21 -> 37
    //   19: aload_0
    //   20: aload_1
    //   21: invokestatic 247	com/alipay/sdk/j/m:c	(Landroid/content/Context;Ljava/lang/String;)Landroid/content/pm/PackageInfo;
    //   24: astore_0
    //   25: goto +58 -> 83
    //   28: astore_0
    //   29: ldc -7
    //   31: ldc -5
    //   33: aload_0
    //   34: invokestatic 256	com/alipay/sdk/app/a/a:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   37: aload_2
    //   38: astore_0
    //   39: goto +44 -> 83
    //   42: astore_2
    //   43: goto +81 -> 124
    //   46: astore_2
    //   47: ldc -7
    //   49: ldc_w 258
    //   52: aload_2
    //   53: invokestatic 256	com/alipay/sdk/app/a/a:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   56: aconst_null
    //   57: invokestatic 244	com/alipay/sdk/j/m:a	(Landroid/content/pm/PackageInfo;)Z
    //   60: ifne +21 -> 81
    //   63: aload_0
    //   64: aload_1
    //   65: invokestatic 247	com/alipay/sdk/j/m:c	(Landroid/content/Context;Ljava/lang/String;)Landroid/content/pm/PackageInfo;
    //   68: astore_0
    //   69: goto +14 -> 83
    //   72: astore_0
    //   73: ldc -7
    //   75: ldc -5
    //   77: aload_0
    //   78: invokestatic 256	com/alipay/sdk/app/a/a:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   81: aconst_null
    //   82: astore_0
    //   83: aload_0
    //   84: invokestatic 244	com/alipay/sdk/j/m:a	(Landroid/content/pm/PackageInfo;)Z
    //   87: ifne +5 -> 92
    //   90: aconst_null
    //   91: areturn
    //   92: aload_0
    //   93: ifnonnull +5 -> 98
    //   96: aconst_null
    //   97: areturn
    //   98: new 6	com/alipay/sdk/j/m$a
    //   101: dup
    //   102: invokespecial 259	com/alipay/sdk/j/m$a:<init>	()V
    //   105: astore_1
    //   106: aload_1
    //   107: aload_0
    //   108: getfield 265	android/content/pm/PackageInfo:signatures	[Landroid/content/pm/Signature;
    //   111: putfield 267	com/alipay/sdk/j/m$a:a	[Landroid/content/pm/Signature;
    //   114: aload_1
    //   115: aload_0
    //   116: getfield 270	android/content/pm/PackageInfo:versionCode	I
    //   119: putfield 272	com/alipay/sdk/j/m$a:b	I
    //   122: aload_1
    //   123: areturn
    //   124: aconst_null
    //   125: invokestatic 244	com/alipay/sdk/j/m:a	(Landroid/content/pm/PackageInfo;)Z
    //   128: ifne +21 -> 149
    //   131: aload_0
    //   132: aload_1
    //   133: invokestatic 247	com/alipay/sdk/j/m:c	(Landroid/content/Context;Ljava/lang/String;)Landroid/content/pm/PackageInfo;
    //   136: pop
    //   137: goto +12 -> 149
    //   140: astore_0
    //   141: ldc -7
    //   143: ldc -5
    //   145: aload_0
    //   146: invokestatic 256	com/alipay/sdk/app/a/a:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   149: aload_2
    //   150: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	151	0	paramContext	Context
    //   0	151	1	paramString	String
    //   11	27	2	localPackageInfo	PackageInfo
    //   42	1	2	localObject	Object
    //   46	104	2	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   19	25	28	java/lang/Throwable
    //   0	12	42	finally
    //   47	56	42	finally
    //   0	12	46	java/lang/Throwable
    //   63	69	72	java/lang/Throwable
    //   131	137	140	java/lang/Throwable
  }
  
  public static String a()
  {
    StringBuilder localStringBuilder = new StringBuilder("Android ");
    localStringBuilder.append(Build.VERSION.RELEASE);
    return localStringBuilder.toString();
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
      if (i <= 0) {
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
      paramArrayOfByte = ((X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(paramArrayOfByte))).getPublicKey().toString();
      if (paramArrayOfByte.indexOf("modulus") != -1)
      {
        paramArrayOfByte = paramArrayOfByte.substring(paramArrayOfByte.indexOf("modulus") + 8, paramArrayOfByte.lastIndexOf(",")).trim();
        return paramArrayOfByte;
      }
    }
    catch (Exception paramArrayOfByte)
    {
      a.a("auth", "GetPublicKeyFromSignEx", paramArrayOfByte);
    }
    return null;
  }
  
  public static Map<String, String> a(String paramString)
  {
    HashMap localHashMap = new HashMap();
    paramString = paramString.split("&");
    int j = paramString.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramString[i];
      int k = localObject.indexOf("=", 1);
      localHashMap.put(localObject.substring(0, k), URLDecoder.decode(localObject.substring(k + 1)));
      i += 1;
    }
    return localHashMap;
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
  
  private static boolean a(PackageInfo paramPackageInfo)
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
      a.a("auth", "NotIncludeSignatures", paramPackageInfo);
    }
    return bool;
  }
  
  public static boolean a(WebView paramWebView, String paramString, Activity paramActivity)
  {
    if (TextUtils.isEmpty(paramString)) {
      return true;
    }
    if ((paramString.toLowerCase().startsWith("alipays://platformapi/startApp?".toLowerCase())) || (paramString.toLowerCase().startsWith("intent://platformapi/startapp?".toLowerCase())) || ((TextUtils.equals(paramString, "sdklite://h5quit")) || (TextUtils.equals(paramString, "http://m.alipay.com/?action=h5quit")) || (paramString.startsWith("sdklite://h5quit?result=")))) {}
    try
    {
      paramWebView = paramString.substring(paramString.indexOf("sdklite://h5quit?result=") + 24);
      int i = Integer.parseInt(paramWebView.substring(paramWebView.lastIndexOf("&end_code=") + 10));
      if ((i != k.a.h) && (i != k.g.h))
      {
        paramWebView = k.a(k.b.h);
        j.a = j.a(paramWebView.h, paramWebView.i, "");
      }
      else
      {
        paramWebView = URLDecoder.decode(paramString);
        paramWebView = paramWebView.substring(paramWebView.indexOf("sdklite://h5quit?result=") + 24, paramWebView.lastIndexOf("&end_code="));
        paramString = k.a(i);
        j.a = j.a(paramString.h, paramString.i, paramWebView);
      }
    }
    catch (Exception paramWebView)
    {
      for (;;)
      {
        try
        {
          paramWebView = a(paramActivity, "com.eg.android.AlipayGphone");
          if (paramWebView != null)
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
    paramWebView = k.a(k.e.h);
    j.a = j.a(paramWebView.h, paramWebView.i, "");
    paramActivity.runOnUiThread(new o(paramActivity));
    return true;
    paramWebView.loadUrl(paramString);
    return true;
    j.a = j.a();
    paramActivity.finish();
    return true;
    return true;
  }
  
  private static PackageInfo b(Context paramContext, String paramString)
    throws PackageManager.NameNotFoundException
  {
    return paramContext.getPackageManager().getPackageInfo(paramString, 192);
  }
  
  private static a b(PackageInfo paramPackageInfo)
  {
    if (paramPackageInfo == null) {
      return null;
    }
    a localA = new a();
    localA.a = paramPackageInfo.signatures;
    localA.b = paramPackageInfo.versionCode;
    return localA;
  }
  
  public static String b()
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
    localObject1 = new StringBuilder("Linux ");
    ((StringBuilder)localObject1).append((String)localObject2);
    return ((StringBuilder)localObject1).toString();
  }
  
  public static boolean b(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo("com.eg.android.AlipayGphone", 128);
      if (paramContext == null) {
        return false;
      }
      int i = paramContext.versionCode;
      return i > 73;
    }
    catch (Throwable paramContext)
    {
      a.a("biz", "CheckClientExistEx", paramContext);
    }
    return false;
  }
  
  public static boolean b(String paramString)
  {
    return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(paramString).matches();
  }
  
  private static PackageInfo c(Context paramContext, String paramString)
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
  
  public static String c()
  {
    Random localRandom = new Random();
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < 24)
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
  
  public static boolean c(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo("com.eg.android.AlipayGphone", 128);
      if (paramContext == null) {
        return false;
      }
      int i = paramContext.versionCode;
      return i < 99;
    }
    catch (Throwable paramContext) {}
    return false;
  }
  
  @SuppressLint({"InlinedApi"})
  private static boolean c(PackageInfo paramPackageInfo)
  {
    int i = paramPackageInfo.applicationInfo.flags;
    return ((i & 0x1) == 0) && ((i & 0x80) == 0);
  }
  
  private static String d()
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
    String str1 = a();
    String str2 = b();
    String str3 = e(paramContext);
    paramContext = f(paramContext);
    StringBuilder localStringBuilder = new StringBuilder(" (");
    localStringBuilder.append(str1);
    localStringBuilder.append(";");
    localStringBuilder.append(str2);
    localStringBuilder.append(";");
    localStringBuilder.append(str3);
    localStringBuilder.append(";;");
    localStringBuilder.append(paramContext);
    localStringBuilder.append(")(sdk android)");
    return localStringBuilder.toString();
  }
  
  private static String e()
  {
    return "-1;-1";
  }
  
  public static String e(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().locale.toString();
  }
  
  public static String f(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    paramContext = new StringBuilder();
    paramContext.append(localDisplayMetrics.widthPixels);
    paramContext.append("*");
    paramContext.append(localDisplayMetrics.heightPixels);
    return paramContext.toString();
  }
  
  public static String g(Context paramContext)
  {
    Object localObject = "";
    try
    {
      Iterator localIterator = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
      paramContext = (Context)localObject;
      for (;;)
      {
        localObject = paramContext;
        if (!localIterator.hasNext()) {
          break;
        }
        localObject = (ActivityManager.RunningAppProcessInfo)localIterator.next();
        if (((ActivityManager.RunningAppProcessInfo)localObject).processName.equals("com.eg.android.AlipayGphone"))
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append(paramContext);
          ((StringBuilder)localObject).append("#M");
          paramContext = ((StringBuilder)localObject).toString();
        }
        else if (((ActivityManager.RunningAppProcessInfo)localObject).processName.startsWith("com.eg.android.AlipayGphone:"))
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(paramContext);
          localStringBuilder.append("#");
          localStringBuilder.append(((ActivityManager.RunningAppProcessInfo)localObject).processName.replace("com.eg.android.AlipayGphone:", ""));
          paramContext = localStringBuilder.toString();
        }
      }
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    localObject = "";
    paramContext = (Context)localObject;
    if (((String)localObject).length() > 0) {
      paramContext = ((String)localObject).substring(1);
    }
    localObject = paramContext;
    if (paramContext.length() == 0) {
      localObject = "N";
    }
    return localObject;
  }
  
  public static String h(Context paramContext)
  {
    for (;;)
    {
      int i;
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledPackages(0);
        StringBuilder localStringBuilder = new StringBuilder();
        i = 0;
        if (i < paramContext.size())
        {
          PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
          j = localPackageInfo.applicationInfo.flags;
          if (((j & 0x1) == 0) && ((j & 0x80) == 0))
          {
            j = 1;
            if (j == 0) {
              break label207;
            }
            if (localPackageInfo.packageName.equals("com.eg.android.AlipayGphone"))
            {
              localStringBuilder.append(localPackageInfo.packageName);
              localStringBuilder.append(localPackageInfo.versionCode);
              localStringBuilder.append("-");
              break label207;
            }
            if ((localPackageInfo.packageName.contains("theme")) || (localPackageInfo.packageName.startsWith("com.google.")) || (localPackageInfo.packageName.startsWith("com.android."))) {
              break label207;
            }
            localStringBuilder.append(localPackageInfo.packageName);
            localStringBuilder.append("-");
            break label207;
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
        a.a("biz", "GetInstalledAppEx", paramContext);
        return "";
      }
      int j = 0;
      continue;
      label207:
      i += 1;
    }
  }
  
  private static a i(Context paramContext)
  {
    return a(paramContext, "com.eg.android.AlipayGphone");
  }
  
  private static DisplayMetrics j(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }
  
  private static String k(Context paramContext)
  {
    paramContext = l.a(paramContext);
    return paramContext.substring(0, paramContext.indexOf("://"));
  }
  
  public static final class a
  {
    public Signature[] a;
    public int b;
    
    public a() {}
    
    public final boolean a()
    {
      if (this.a != null)
      {
        if (this.a.length <= 0) {
          return false;
        }
        int i = 0;
        while (i < this.a.length)
        {
          String str = m.a(this.a[i].toByteArray());
          if ((str != null) && (!TextUtils.equals(str, "b6cbad6cbd5ed0d209afc69ad3b7a617efaae9b3c47eabe0be42d924936fa78c8001b1fd74b079e5ff9690061dacfa4768e981a526b9ca77156ca36251cf2f906d105481374998a7e6e6e18f75ca98b8ed2eaf86ff402c874cca0a263053f22237858206867d210020daa38c48b20cc9dfd82b44a51aeb5db459b22794e2d649")))
          {
            a.a("biz", "PublicKeyUnmatch", str);
            return true;
          }
          i += 1;
        }
        return false;
      }
      return false;
    }
  }
}
