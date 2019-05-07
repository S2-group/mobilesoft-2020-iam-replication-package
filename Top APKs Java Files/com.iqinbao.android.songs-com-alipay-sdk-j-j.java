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
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.alipay.sdk.app.b;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint({"SetJavaScriptEnabled", "DefaultLocale"})
public final class j
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
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -1);
    paramString2.setOrientation(1);
    paramActivity.setContentView(paramString2, localLayoutParams);
    paramActivity = new WebView(localContext);
    localLayoutParams.weight = 1.0F;
    paramActivity.setVisibility(0);
    paramString2.addView(paramActivity, localLayoutParams);
    paramString2 = paramActivity.getSettings();
    paramString2.setUserAgentString(paramString2.getUserAgentString() + e(localContext));
    paramString2.setRenderPriority(WebSettings.RenderPriority.HIGH);
    paramString2.setSupportMultipleWindows(true);
    paramString2.setJavaScriptEnabled(true);
    paramString2.setSavePassword(false);
    paramString2.setJavaScriptCanOpenWindowsAutomatically(true);
    paramString2.setMinimumFontSize(paramString2.getMinimumFontSize() + 8);
    paramString2.setAllowFileAccess(false);
    paramString2.setTextSize(WebSettings.TextSize.NORMAL);
    paramActivity.setVerticalScrollbarOverlay(true);
    paramActivity.setDownloadListener(new k(localContext));
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
        if (Build.VERSION.SDK_INT >= 19) {
          paramActivity.getSettings().setCacheMode(2);
        }
        paramActivity.loadUrl(paramString1);
        return paramActivity;
      }
      catch (Throwable paramString2)
      {
        try
        {
          paramString2 = paramActivity.getClass().getMethod("removeJavascriptInterface", new Class[0]);
          if (paramString2 == null) {
            continue;
          }
          paramString2.invoke(paramActivity, new Object[] { "searchBoxJavaBridge_" });
          paramString2.invoke(paramActivity, new Object[] { "accessibility" });
          paramString2.invoke(paramActivity, new Object[] { "accessibilityTraversal" });
        }
        catch (Throwable paramString2) {}
      }
    }
  }
  
  public static a a(Context paramContext)
  {
    return a(paramContext, a());
  }
  
  private static a a(Context paramContext, String paramString)
  {
    for (;;)
    {
      try
      {
        localPackageInfo3 = paramContext.getPackageManager().getPackageInfo(paramString, 192);
        localPackageInfo1 = localPackageInfo3;
        if (a(localPackageInfo3)) {}
      }
      catch (Throwable localThrowable)
      {
        PackageInfo localPackageInfo3;
        PackageInfo localPackageInfo1;
        com.alipay.sdk.app.a.a.a("auth", "GetPackageInfoEx", localThrowable);
        if (a(null)) {
          break label147;
        }
        try
        {
          localPackageInfo2 = b(paramContext, paramString);
        }
        catch (Throwable paramContext)
        {
          com.alipay.sdk.app.a.a.a("auth", "GetInstalledPackagesEx", paramContext);
          PackageInfo localPackageInfo2 = null;
        }
        continue;
      }
      finally
      {
        if (a(null)) {
          continue;
        }
        try
        {
          b(paramContext, paramString);
          throw localObject1;
        }
        catch (Throwable paramContext)
        {
          com.alipay.sdk.app.a.a.a("auth", "GetInstalledPackagesEx", paramContext);
          continue;
        }
        if (localObject1 == null) {
          continue;
        }
        paramContext = new a();
        paramContext.a = localObject1.signatures;
        paramContext.b = localObject1.versionCode;
        return paramContext;
      }
      try
      {
        localPackageInfo1 = b(paramContext, paramString);
        if (!a(localPackageInfo1)) {
          return null;
        }
      }
      catch (Throwable paramContext)
      {
        com.alipay.sdk.app.a.a.a("auth", "GetInstalledPackagesEx", paramContext);
        localPackageInfo1 = localPackageInfo3;
        continue;
      }
      label147:
      Object localObject2 = null;
    }
  }
  
  public static String a()
  {
    if (b.a()) {
      return "com.eg.android.AlipayGphoneRC";
    }
    return "com.eg.android.AlipayGphone";
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
  
  private static boolean a(PackageInfo paramPackageInfo)
  {
    String str = "";
    boolean bool = false;
    if (paramPackageInfo == null) {
      paramPackageInfo = "" + "info == null";
    }
    for (;;)
    {
      if (!bool) {
        com.alipay.sdk.app.a.a.a("auth", "NotIncludeSignatures", paramPackageInfo);
      }
      return bool;
      if (paramPackageInfo.signatures == null)
      {
        paramPackageInfo = "" + "info.signatures == null";
      }
      else if (paramPackageInfo.signatures.length <= 0)
      {
        paramPackageInfo = "" + "info.signatures.length <= 0";
      }
      else
      {
        bool = true;
        paramPackageInfo = str;
      }
    }
  }
  
  public static boolean a(WebView paramWebView, String paramString, Activity paramActivity)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return true;
      if ((paramString.toLowerCase().startsWith("alipays://platformapi/startApp?".toLowerCase())) || (paramString.toLowerCase().startsWith("intent://platformapi/startapp?".toLowerCase()))) {
        try
        {
          paramWebView = a(paramActivity);
          if ((paramWebView != null) && (!paramWebView.a()))
          {
            paramWebView = paramString;
            if (paramString.startsWith("intent://platformapi/startapp")) {
              paramWebView = paramString.replaceFirst("intent://platformapi/startapp\\?", "alipays://platformapi/startApp?");
            }
            paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramWebView)));
            return true;
          }
        }
        catch (Throwable paramWebView)
        {
          return true;
        }
      }
    }
    if ((TextUtils.equals(paramString, "sdklite://h5quit")) || (TextUtils.equals(paramString, "http://m.alipay.com/?action=h5quit")))
    {
      com.alipay.sdk.app.j.a = com.alipay.sdk.app.j.a();
      paramActivity.finish();
      return true;
    }
    if (paramString.startsWith("sdklite://h5quit?result=")) {
      for (;;)
      {
        try
        {
          paramWebView = paramString.substring(paramString.indexOf("sdklite://h5quit?result=") + 24);
          int i = Integer.parseInt(paramWebView.substring(paramWebView.lastIndexOf("&end_code=") + 10));
          if ((i != com.alipay.sdk.app.k.a.h) && (i != com.alipay.sdk.app.k.g.h)) {
            continue;
          }
          if (!com.alipay.sdk.b.a.c) {
            continue;
          }
          paramWebView = new StringBuilder();
          paramString = URLDecoder.decode(paramString);
          String str = URLDecoder.decode(paramString);
          str = str.substring(str.indexOf("sdklite://h5quit?result=") + 24, str.lastIndexOf("&end_code=")).split("&return_url=")[0];
          int j = paramString.indexOf("&return_url=") + 12;
          paramWebView.append(str).append("&return_url=").append(paramString.substring(j, paramString.indexOf("&", j))).append(paramString.substring(paramString.indexOf("&", j)));
          paramWebView = paramWebView.toString();
          paramString = com.alipay.sdk.app.k.a(i);
          com.alipay.sdk.app.j.a = com.alipay.sdk.app.j.a(paramString.h, paramString.i, paramWebView);
        }
        catch (Exception paramWebView)
        {
          paramWebView = com.alipay.sdk.app.k.a(com.alipay.sdk.app.k.e.h);
          com.alipay.sdk.app.j.a = com.alipay.sdk.app.j.a(paramWebView.h, paramWebView.i, "");
          continue;
        }
        paramActivity.runOnUiThread(new l(paramActivity));
        return true;
        paramWebView = URLDecoder.decode(paramString);
        paramWebView = paramWebView.substring(paramWebView.indexOf("sdklite://h5quit?result=") + 24, paramWebView.lastIndexOf("&end_code="));
        continue;
        paramWebView = com.alipay.sdk.app.k.a(com.alipay.sdk.app.k.b.h);
        com.alipay.sdk.app.j.a = com.alipay.sdk.app.j.a(paramWebView.h, paramWebView.i, "");
      }
    }
    paramWebView.loadUrl(paramString);
    return true;
  }
  
  public static boolean a(String paramString)
  {
    return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipaydev|alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(paramString).matches();
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
    return "Android " + Build.VERSION.RELEASE;
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
    return "Linux " + (String)localObject2;
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
      if (i > 73) {
        return true;
      }
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
    if (i < 24)
    {
      switch (localRandom.nextInt(3))
      {
      }
      for (;;)
      {
        i += 1;
        break;
        localStringBuilder.append(String.valueOf((char)(int)Math.round(Math.random() * 25.0D + 65.0D)));
        continue;
        localStringBuilder.append(String.valueOf((char)(int)Math.round(Math.random() * 25.0D + 97.0D)));
        continue;
        localStringBuilder.append(String.valueOf(new Random().nextInt(10)));
      }
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
      if (i < 99) {
        return true;
      }
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
        String str2 = ((BufferedReader)localObject1).readLine();
        ((BufferedReader)localObject1).close();
        localObject1 = Pattern.compile("\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)").matcher(str2);
        if (!((Matcher)localObject1).matches()) {
          return "Unavailable";
        }
      }
      finally
      {
        ((BufferedReader)localObject1).close();
      }
      if (localIOException.groupCount() >= 4) {
        break label76;
      }
    }
    catch (IOException localIOException)
    {
      return "Unavailable";
    }
    return "Unavailable";
    label76:
    String str1 = localIOException.group(1) + "\n" + localIOException.group(2) + " " + localIOException.group(3) + "\n" + localIOException.group(4);
    return str1;
  }
  
  public static String e(Context paramContext)
  {
    String str1 = b();
    String str2 = c();
    String str3 = f(paramContext);
    paramContext = g(paramContext);
    return " (" + str1 + ";" + str2 + ";" + str3 + ";;" + paramContext + ")(sdk android)";
  }
  
  public static String f(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().locale.toString();
  }
  
  public static String g(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    paramContext = new StringBuilder();
    paramContext.append(localDisplayMetrics.widthPixels);
    paramContext.append("*");
    paramContext.append(localDisplayMetrics.heightPixels);
    return paramContext.toString();
  }
  
  public static String h(Context paramContext)
  {
    Object localObject = "";
    try
    {
      Iterator localIterator = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
      for (paramContext = (Context)localObject;; paramContext = paramContext + "#M")
      {
        localObject = paramContext;
        if (!localIterator.hasNext()) {
          break label179;
        }
        localObject = (ActivityManager.RunningAppProcessInfo)localIterator.next();
        if (!((ActivityManager.RunningAppProcessInfo)localObject).processName.equals(a())) {
          break;
        }
      }
      if (((ActivityManager.RunningAppProcessInfo)localObject).processName.startsWith(a() + ":")) {
        paramContext = paramContext + "#" + ((ActivityManager.RunningAppProcessInfo)localObject).processName.replace(new StringBuilder().append(a()).append(":").toString(), "");
      }
      label179:
      for (;;)
      {
        break;
      }
    }
    catch (Throwable paramContext)
    {
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
  }
  
  public static String i(Context paramContext)
  {
    for (;;)
    {
      StringBuilder localStringBuilder;
      int i;
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledPackages(0);
        localStringBuilder = new StringBuilder();
        i = 0;
        if (i < paramContext.size())
        {
          PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
          j = localPackageInfo.applicationInfo.flags;
          if (((j & 0x1) != 0) || ((j & 0x80) != 0)) {
            break label201;
          }
          j = 1;
          if (j == 0) {
            break label194;
          }
          if (localPackageInfo.packageName.equals(a())) {
            localStringBuilder.append(localPackageInfo.packageName).append(localPackageInfo.versionCode).append("-");
          } else if ((!localPackageInfo.packageName.contains("theme")) && (!localPackageInfo.packageName.startsWith("com.google.")) && (!localPackageInfo.packageName.startsWith("com.android."))) {
            localStringBuilder.append(localPackageInfo.packageName).append("-");
          }
        }
      }
      catch (Throwable paramContext)
      {
        com.alipay.sdk.app.a.a.a("biz", "GetInstalledAppEx", paramContext);
        return "";
      }
      paramContext = localStringBuilder.toString();
      return paramContext;
      label194:
      i += 1;
      continue;
      label201:
      int j = 0;
    }
  }
  
  public static final class a
  {
    public Signature[] a;
    public int b;
    
    public a() {}
    
    public final boolean a()
    {
      if ((this.a == null) || (this.a.length <= 0)) {}
      for (;;)
      {
        return false;
        int i = 0;
        while (i < this.a.length)
        {
          String str = j.a(this.a[i].toByteArray());
          if ((str != null) && (!TextUtils.equals(str, "b6cbad6cbd5ed0d209afc69ad3b7a617efaae9b3c47eabe0be42d924936fa78c8001b1fd74b079e5ff9690061dacfa4768e981a526b9ca77156ca36251cf2f906d105481374998a7e6e6e18f75ca98b8ed2eaf86ff402c874cca0a263053f22237858206867d210020daa38c48b20cc9dfd82b44a51aeb5db459b22794e2d649")))
          {
            com.alipay.sdk.app.a.a.a("biz", "PublicKeyUnmatch", str);
            return true;
          }
          i += 1;
        }
      }
    }
  }
}
