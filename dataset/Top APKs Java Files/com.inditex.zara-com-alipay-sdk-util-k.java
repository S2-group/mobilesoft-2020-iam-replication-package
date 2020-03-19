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
import com.alipay.sdk.app.h;
import com.alipay.sdk.app.i;
import com.alipay.sdk.app.statistic.a;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
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
  static final String a = "com.alipay.android.app";
  public static final String b = "com.eg.android.AlipayGphone";
  private static final String c = "7.0.0";
  
  public k() {}
  
  private static int a(String paramString1, String paramString2)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    localArrayList1.addAll(Arrays.asList(paramString1.split("\\.")));
    localArrayList2.addAll(Arrays.asList(paramString2.split("\\.")));
    int j = Math.max(localArrayList1.size(), localArrayList2.size());
    while (localArrayList1.size() < j) {
      localArrayList1.add("0");
    }
    while (localArrayList2.size() < j) {
      localArrayList2.add("0");
    }
    int i = 0;
    while (i < j)
    {
      if (Integer.parseInt((String)localArrayList1.get(i)) != Integer.parseInt((String)localArrayList2.get(i))) {
        return Integer.parseInt((String)localArrayList1.get(i)) - Integer.parseInt((String)localArrayList2.get(i));
      }
      i += 1;
    }
    return 0;
  }
  
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
    ((StringBuilder)localObject2).append(c(paramActivity));
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
    paramString2.setDownloadListener(new l(paramActivity));
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
  
  public static a a(Context paramContext, String paramString)
  {
    Iterator localIterator = paramContext.getPackageManager().getInstalledPackages(64).iterator();
    while (localIterator.hasNext())
    {
      paramContext = (PackageInfo)localIterator.next();
      if (paramContext.packageName.equals(paramString))
      {
        paramString = new a();
        paramString.a = paramContext.signatures[0].toByteArray();
        paramString.b = paramContext.versionCode;
        return paramString;
      }
    }
    return null;
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
      for (;;) {}
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
  
  @SuppressLint({"InlinedApi"})
  private static boolean a(PackageInfo paramPackageInfo)
  {
    int i = paramPackageInfo.applicationInfo.flags;
    return ((i & 0x1) == 0) && ((i & 0x80) == 0);
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
      if ((i != i.a.h) && (i != i.g.h))
      {
        paramWebView = i.a(i.b.h);
        h.a = h.a(paramWebView.h, paramWebView.i, "");
      }
      else
      {
        StringBuilder localStringBuilder = new StringBuilder();
        paramWebView = URLDecoder.decode(paramString);
        paramString = paramWebView.substring(paramWebView.indexOf("sdklite://h5quit?result=") + 24, paramWebView.lastIndexOf("&end_code="));
        paramWebView = paramString;
        if (paramString.contains("&return_url="))
        {
          paramWebView = paramString.split("&return_url=")[0];
          int j = paramString.indexOf("&return_url=") + 12;
          localStringBuilder.append(paramWebView);
          localStringBuilder.append("&return_url=");
          localStringBuilder.append(paramString.substring(j, paramString.indexOf("\"&", j)));
          localStringBuilder.append(paramString.substring(paramString.indexOf("\"&", j)));
          paramWebView = localStringBuilder.toString();
        }
        paramString = i.a(i);
        h.a = h.a(paramString.h, paramString.i, paramWebView);
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
            paramWebView = a(paramWebView.a);
            if ((paramWebView != null) && (!TextUtils.equals(paramWebView, "b6cbad6cbd5ed0d209afc69ad3b7a617efaae9b3c47eabe0be42d924936fa78c8001b1fd74b079e5ff9690061dacfa4768e981a526b9ca77156ca36251cf2f906d105481374998a7e6e6e18f75ca98b8ed2eaf86ff402c874cca0a263053f22237858206867d210020daa38c48b20cc9dfd82b44a51aeb5db459b22794e2d649")))
            {
              a.a("biz", "ClientSignError", paramWebView);
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
    paramWebView = i.a(i.e.h);
    h.a = h.a(paramWebView.h, paramWebView.i, "");
    paramActivity.runOnUiThread(new m(paramActivity));
    return true;
    paramWebView.loadUrl(paramString);
    return true;
    h.a = h.a();
    paramActivity.finish();
    return true;
    return true;
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
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager().getPackageInfo("com.eg.android.AlipayGphone", 128);
        if (paramContext == null) {
          return false;
        }
        paramContext = paramContext.versionName;
        ArrayList localArrayList1 = new ArrayList();
        ArrayList localArrayList2 = new ArrayList();
        localArrayList1.addAll(Arrays.asList(paramContext.split("\\.")));
        localArrayList2.addAll(Arrays.asList("7.0.0".split("\\.")));
        int j = Math.max(localArrayList1.size(), localArrayList2.size());
        if (localArrayList1.size() < j)
        {
          localArrayList1.add("0");
          continue;
        }
        if (localArrayList2.size() < j)
        {
          localArrayList2.add("0");
          continue;
          if (i < j)
          {
            if (Integer.parseInt((String)localArrayList1.get(i)) != Integer.parseInt((String)localArrayList2.get(i)))
            {
              j = Integer.parseInt((String)localArrayList1.get(i));
              i = Integer.parseInt((String)localArrayList2.get(i));
              i = j - i;
            }
            else
            {
              i += 1;
            }
          }
          else {
            i = 0;
          }
          return i >= 0;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        return false;
      }
      int i = 0;
    }
  }
  
  public static boolean b(String paramString)
  {
    return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(paramString).matches();
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
  
  public static String c(Context paramContext)
  {
    String str1 = a();
    String str2 = b();
    String str3 = d(paramContext);
    paramContext = e(paramContext);
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
    return paramContext.getResources().getConfiguration().locale.toString();
  }
  
  private static String e()
  {
    return "-1;-1";
  }
  
  public static String e(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    paramContext = new StringBuilder();
    paramContext.append(localDisplayMetrics.widthPixels);
    paramContext.append("*");
    paramContext.append(localDisplayMetrics.heightPixels);
    return paramContext.toString();
  }
  
  public static String f(Context paramContext)
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
  
  private static DisplayMetrics g(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }
  
  private static String h(Context paramContext)
  {
    paramContext = j.a(paramContext);
    return paramContext.substring(0, paramContext.indexOf("://"));
  }
  
  private static String i(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramContext.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
      int j = localPackageInfo.applicationInfo.flags;
      if (((j & 0x1) == 0) && ((j & 0x80) == 0)) {
        j = 1;
      } else {
        j = 0;
      }
      if (j != 0) {
        if (localPackageInfo.packageName.equals("com.eg.android.AlipayGphone"))
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
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static final class a
  {
    public byte[] a;
    public int b;
    
    public a() {}
  }
}
