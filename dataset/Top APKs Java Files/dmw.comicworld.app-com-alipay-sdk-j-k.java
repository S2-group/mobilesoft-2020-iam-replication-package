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
import com.alipay.sdk.app.i;
import com.alipay.sdk.app.j;
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
public final class k
{
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
    ((StringBuilder)localObject).append(e(localContext));
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
    paramActivity.setDownloadListener(new l(localContext));
    if (Build.VERSION.SDK_INT >= 7) {}
    for (;;)
    {
      try
      {
        paramString2 = paramActivity.getSettings().getClass().getMethod("setDomStorageEnabled", new Class[] { Boolean.TYPE });
        if (paramString2 != null) {
          paramString2.invoke(paramActivity.getSettings(), new Object[] { Boolean.TRUE });
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
  
  public static a a(Context paramContext)
  {
    return a(paramContext, a());
  }
  
  /* Error */
  private static a a(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 228	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: aload_1
    //   5: sipush 192
    //   8: invokevirtual 234	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   11: astore_2
    //   12: aload_2
    //   13: invokestatic 237	com/alipay/sdk/j/k:a	(Landroid/content/pm/PackageInfo;)Z
    //   16: ifne +21 -> 37
    //   19: aload_0
    //   20: aload_1
    //   21: invokestatic 241	com/alipay/sdk/j/k:b	(Landroid/content/Context;Ljava/lang/String;)Landroid/content/pm/PackageInfo;
    //   24: astore_0
    //   25: goto +57 -> 82
    //   28: astore_0
    //   29: ldc -13
    //   31: ldc -11
    //   33: aload_0
    //   34: invokestatic 250	com/alipay/sdk/app/a/a:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   37: aload_2
    //   38: astore_0
    //   39: goto +43 -> 82
    //   42: astore_2
    //   43: goto +80 -> 123
    //   46: astore_2
    //   47: ldc -13
    //   49: ldc -4
    //   51: aload_2
    //   52: invokestatic 250	com/alipay/sdk/app/a/a:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   55: aconst_null
    //   56: invokestatic 237	com/alipay/sdk/j/k:a	(Landroid/content/pm/PackageInfo;)Z
    //   59: ifne +21 -> 80
    //   62: aload_0
    //   63: aload_1
    //   64: invokestatic 241	com/alipay/sdk/j/k:b	(Landroid/content/Context;Ljava/lang/String;)Landroid/content/pm/PackageInfo;
    //   67: astore_0
    //   68: goto +14 -> 82
    //   71: astore_0
    //   72: ldc -13
    //   74: ldc -11
    //   76: aload_0
    //   77: invokestatic 250	com/alipay/sdk/app/a/a:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   80: aconst_null
    //   81: astore_0
    //   82: aload_0
    //   83: invokestatic 237	com/alipay/sdk/j/k:a	(Landroid/content/pm/PackageInfo;)Z
    //   86: ifne +5 -> 91
    //   89: aconst_null
    //   90: areturn
    //   91: aload_0
    //   92: ifnonnull +5 -> 97
    //   95: aconst_null
    //   96: areturn
    //   97: new 6	com/alipay/sdk/j/k$a
    //   100: dup
    //   101: invokespecial 253	com/alipay/sdk/j/k$a:<init>	()V
    //   104: astore_1
    //   105: aload_1
    //   106: aload_0
    //   107: getfield 259	android/content/pm/PackageInfo:signatures	[Landroid/content/pm/Signature;
    //   110: putfield 261	com/alipay/sdk/j/k$a:a	[Landroid/content/pm/Signature;
    //   113: aload_1
    //   114: aload_0
    //   115: getfield 264	android/content/pm/PackageInfo:versionCode	I
    //   118: putfield 266	com/alipay/sdk/j/k$a:b	I
    //   121: aload_1
    //   122: areturn
    //   123: aconst_null
    //   124: invokestatic 237	com/alipay/sdk/j/k:a	(Landroid/content/pm/PackageInfo;)Z
    //   127: ifne +21 -> 148
    //   130: aload_0
    //   131: aload_1
    //   132: invokestatic 241	com/alipay/sdk/j/k:b	(Landroid/content/Context;Ljava/lang/String;)Landroid/content/pm/PackageInfo;
    //   135: pop
    //   136: goto +12 -> 148
    //   139: astore_0
    //   140: ldc -13
    //   142: ldc -11
    //   144: aload_0
    //   145: invokestatic 250	com/alipay/sdk/app/a/a:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   148: aload_2
    //   149: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	150	0	paramContext	Context
    //   0	150	1	paramString	String
    //   11	27	2	localPackageInfo	PackageInfo
    //   42	1	2	localObject	Object
    //   46	103	2	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   19	25	28	java/lang/Throwable
    //   0	12	42	finally
    //   47	55	42	finally
    //   0	12	46	java/lang/Throwable
    //   62	68	71	java/lang/Throwable
    //   130	136	139	java/lang/Throwable
  }
  
  public static String a()
  {
    if (com.alipay.sdk.app.a.a()) {
      return "com.eg.android.AlipayGphoneRC";
    }
    return "com.eg.android.AlipayGphone";
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
      com.alipay.sdk.app.a.a.a("auth", "GetPublicKeyFromSignEx", paramArrayOfByte);
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
  
  private static boolean a(PackageInfo paramPackageInfo)
  {
    Object localObject = "";
    boolean bool = false;
    if (paramPackageInfo == null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("");
      paramPackageInfo = "info == null";
    }
    for (;;)
    {
      ((StringBuilder)localObject).append(paramPackageInfo);
      paramPackageInfo = ((StringBuilder)localObject).toString();
      break label109;
      if (paramPackageInfo.signatures == null)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("");
        paramPackageInfo = "info.signatures == null";
      }
      else
      {
        if (paramPackageInfo.signatures.length > 0) {
          break;
        }
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("");
        paramPackageInfo = "info.signatures.length <= 0";
      }
    }
    bool = true;
    paramPackageInfo = (PackageInfo)localObject;
    label109:
    if (!bool) {
      com.alipay.sdk.app.a.a.a("auth", "NotIncludeSignatures", paramPackageInfo);
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
      if ((i != j.a.h) && (i != j.g.h)) {
        paramWebView = j.a(j.b.h);
      }
      for (paramWebView = i.a(paramWebView.h, paramWebView.i, "");; paramWebView = i.a(paramString.h, paramString.i, paramWebView))
      {
        i.a = paramWebView;
        break;
        if (com.alipay.sdk.b.a.c)
        {
          paramWebView = new StringBuilder();
          paramString = URLDecoder.decode(paramString);
          String str = URLDecoder.decode(paramString);
          str = str.substring(str.indexOf("sdklite://h5quit?result=") + 24, str.lastIndexOf("&end_code=")).split("&return_url=")[0];
          int j = paramString.indexOf("&return_url=") + 12;
          paramWebView.append(str);
          paramWebView.append("&return_url=");
          paramWebView.append(paramString.substring(j, paramString.indexOf("&", j)));
          paramWebView.append(paramString.substring(paramString.indexOf("&", j)));
          paramWebView = paramWebView.toString();
        }
        else
        {
          paramWebView = URLDecoder.decode(paramString);
          paramWebView = paramWebView.substring(paramWebView.indexOf("sdklite://h5quit?result=") + 24, paramWebView.lastIndexOf("&end_code="));
        }
        paramString = j.a(i);
      }
    }
    catch (Exception paramWebView)
    {
      for (;;)
      {
        try
        {
          paramWebView = a(paramActivity);
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
          }
          return true;
        }
        catch (Throwable paramWebView) {}
        paramWebView = paramWebView;
      }
    }
    paramWebView = j.a(j.e.h);
    i.a = i.a(paramWebView.h, paramWebView.i, "");
    paramActivity.runOnUiThread(new m(paramActivity));
    return true;
    paramWebView.loadUrl(paramString);
    return true;
    i.a = i.a();
    paramActivity.finish();
    return true;
    return true;
  }
  
  private static PackageInfo b(Context paramContext, String paramString)
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
  
  public static String b()
  {
    StringBuilder localStringBuilder = new StringBuilder("Android ");
    localStringBuilder.append(Build.VERSION.RELEASE);
    return localStringBuilder.toString();
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
  
  public static boolean b(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo("com.alipay.android.app", 128);
      return paramContext != null;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean b(String paramString)
  {
    return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipaydev|alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(paramString).matches();
  }
  
  public static String c()
  {
    Object localObject2 = e();
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
    return "Linux ".concat(String.valueOf(localObject2));
  }
  
  public static boolean c(Context paramContext)
  {
    try
    {
      String str = a();
      paramContext = paramContext.getPackageManager().getPackageInfo(str, 128);
      if (paramContext == null) {
        return false;
      }
      int i = paramContext.versionCode;
      return i > 73;
    }
    catch (Throwable paramContext)
    {
      com.alipay.sdk.app.a.a.a("biz", "CheckClientExistEx", paramContext);
    }
    return false;
  }
  
  public static String d()
  {
    Random localRandom = new Random();
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < 24)
    {
      double d2;
      double d1;
      switch (localRandom.nextInt(3))
      {
      default: 
        break;
      case 2: 
        str = String.valueOf(new Random().nextInt(10));
        break;
      case 1: 
        d2 = Math.random() * 25.0D;
        d1 = 97.0D;
        break;
      case 0: 
        d2 = Math.random() * 25.0D;
        d1 = 65.0D;
      }
      String str = String.valueOf((char)(int)Math.round(d2 + d1));
      localStringBuilder.append(str);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static boolean d(Context paramContext)
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
    catch (Throwable paramContext) {}
    return false;
  }
  
  private static String e()
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
  
  public static String e(Context paramContext)
  {
    String str1 = b();
    String str2 = c();
    String str3 = f(paramContext);
    paramContext = g(paramContext);
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
  
  public static String f(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().locale.toString();
  }
  
  public static String g(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    paramContext = new StringBuilder();
    paramContext.append(localDisplayMetrics.widthPixels);
    paramContext.append("*");
    paramContext.append(localDisplayMetrics.heightPixels);
    return paramContext.toString();
  }
  
  public static String h(Context paramContext)
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
        if (((ActivityManager.RunningAppProcessInfo)localObject1).processName.equals(a()))
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
          localStringBuilder.append(a());
          localStringBuilder.append(":");
          if (((String)localObject2).startsWith(localStringBuilder.toString()))
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append(paramContext);
            ((StringBuilder)localObject2).append("#");
            paramContext = ((ActivityManager.RunningAppProcessInfo)localObject1).processName;
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append(a());
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
    localObject1 = paramContext;
    if (paramContext.length() == 0) {
      localObject1 = "N";
    }
    return localObject1;
  }
  
  public static String i(Context paramContext)
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
              break label200;
            }
            if (localPackageInfo.packageName.equals(a()))
            {
              localStringBuilder.append(localPackageInfo.packageName);
              localStringBuilder.append(localPackageInfo.versionCode);
              localStringBuilder.append("-");
              break label200;
            }
            if ((localPackageInfo.packageName.contains("theme")) || (localPackageInfo.packageName.startsWith("com.google.")) || (localPackageInfo.packageName.startsWith("com.android."))) {
              break label200;
            }
            localStringBuilder.append(localPackageInfo.packageName);
            continue;
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
        com.alipay.sdk.app.a.a.a("biz", "GetInstalledAppEx", paramContext);
        return "";
      }
      int j = 0;
      continue;
      label200:
      i += 1;
    }
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
          String str = k.a(this.a[i].toByteArray());
          if ((str != null) && (!TextUtils.equals(str, "b6cbad6cbd5ed0d209afc69ad3b7a617efaae9b3c47eabe0be42d924936fa78c8001b1fd74b079e5ff9690061dacfa4768e981a526b9ca77156ca36251cf2f906d105481374998a7e6e6e18f75ca98b8ed2eaf86ff402c874cca0a263053f22237858206867d210020daa38c48b20cc9dfd82b44a51aeb5db459b22794e2d649")))
          {
            com.alipay.sdk.app.a.a.a("biz", "PublicKeyUnmatch", str);
            return true;
          }
          i += 1;
        }
      }
      return false;
    }
  }
}
