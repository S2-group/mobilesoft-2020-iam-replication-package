package l;

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
public final class ﾏ
{
  public static final String[] ʻⁿ = { "10.1.5.1013151", "10.1.5.1013148" };
  
  public ﾏ() {}
  
  public static String a()
  {
    if (ঢ.ᴸ()) {
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
      ᒮ.a("auth", "GetPublicKeyFromSignEx", paramArrayOfByte);
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
      if (paramContext == null) {
        return false;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    return false;
    return true;
  }
  
  public static boolean b(String paramString)
  {
    return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipaydev|alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(paramString).matches();
  }
  
  public static String c()
  {
    Object localObject2 = f();
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
    for (;;)
    {
      try
      {
        if (!ঢ.ᴸ()) {
          break label59;
        }
        str = "com.eg.android.AlipayGphoneRC";
        paramContext = paramContext.getPackageManager().getPackageInfo(str, 128);
        if (paramContext == null) {
          return false;
        }
        int i = paramContext.versionCode;
        if (i <= 73) {
          return false;
        }
      }
      catch (Throwable paramContext)
      {
        ᒮ.a("biz", "CheckClientExistEx", paramContext);
        return false;
      }
      return true;
      label59:
      String str = "com.eg.android.AlipayGphone";
    }
  }
  
  public static String d()
  {
    return "-1;-1";
  }
  
  public static boolean d(Context paramContext)
  {
    try
    {
      if (!ঢ.ᴸ()) {
        break label74;
      }
      str = "com.eg.android.AlipayGphoneRC";
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        boolean bool;
        label68:
        continue;
        label74:
        String str = "com.eg.android.AlipayGphone";
      }
    }
    paramContext = paramContext.getPackageManager().getPackageInfo(str, 128);
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.versionName;
    if (!TextUtils.equals(paramContext, ʻⁿ[0]))
    {
      bool = TextUtils.equals(paramContext, ʻⁿ[1]);
      if (!bool) {}
    }
    else
    {
      return true;
    }
    break label68;
    return false;
    return false;
  }
  
  public static String e()
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
      case 0: 
        localStringBuilder.append(String.valueOf((char)(int)Math.round(Math.random() * 25.0D + 65.0D)));
        break;
      case 1: 
        localStringBuilder.append(String.valueOf((char)(int)Math.round(Math.random() * 25.0D + 97.0D)));
        break;
      case 2: 
        localStringBuilder.append(String.valueOf(new Random().nextInt(10)));
      }
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static boolean e(Context paramContext)
  {
    try
    {
      localPackageManager = paramContext.getPackageManager();
      if (!ঢ.ᴸ()) {
        break label53;
      }
      paramContext = "com.eg.android.AlipayGphoneRC";
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        PackageManager localPackageManager;
        int i;
        continue;
        label53:
        paramContext = "com.eg.android.AlipayGphone";
      }
    }
    paramContext = localPackageManager.getPackageInfo(paramContext, 128);
    if (paramContext == null) {
      return false;
    }
    i = paramContext.versionCode;
    return i < 99;
    return false;
  }
  
  private static String f()
  {
    try
    {
      Object localObject = new BufferedReader(new FileReader("/proc/version"), 256);
      try
      {
        String str = ((BufferedReader)localObject).readLine();
        ((BufferedReader)localObject).close();
      }
      finally
      {
        ((BufferedReader)localObject).close();
      }
      boolean bool = ((Matcher)localObject).matches();
      if (!bool) {
        return "Unavailable";
      }
      int i = ((Matcher)localObject).groupCount();
      if (i < 4) {
        return "Unavailable";
      }
      localObject = ((Matcher)localObject).group(1) + "\n" + ((Matcher)localObject).group(2) + " " + ((Matcher)localObject).group(3) + "\n" + ((Matcher)localObject).group(4);
      return localObject;
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
    return "Unavailable";
  }
  
  public static String g(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().locale.toString();
  }
  
  public static String h(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    paramContext = new StringBuilder();
    paramContext.append(localDisplayMetrics.widthPixels);
    paramContext.append("*");
    paramContext.append(localDisplayMetrics.heightPixels);
    return paramContext.toString();
  }
  
  public static String i(Context paramContext)
  {
    paramContext = ｮ.a(paramContext);
    return paramContext.substring(0, paramContext.indexOf("://"));
  }
  
  public static String j(Context paramContext)
  {
    localObject1 = "";
    for (;;)
    {
      try
      {
        Iterator localIterator = ((ActivityManager)paramContext.getApplicationContext().getSystemService("activity")).getRunningAppProcesses().iterator();
        paramContext = (Context)localObject1;
        if (!localIterator.hasNext()) {
          continue;
        }
        localObject3 = (ActivityManager.RunningAppProcessInfo)localIterator.next();
        localObject2 = ((ActivityManager.RunningAppProcessInfo)localObject3).processName;
        if (!ঢ.ᴸ()) {
          continue;
        }
        localObject1 = "com.eg.android.AlipayGphoneRC";
      }
      catch (Throwable paramContext)
      {
        Object localObject3;
        Object localObject2;
        String str;
        StringBuilder localStringBuilder;
        continue;
        localObject1 = "com.eg.android.AlipayGphone";
        continue;
        localObject1 = "com.eg.android.AlipayGphone";
        continue;
        paramContext = "com.eg.android.AlipayGphone";
        continue;
      }
      if (((String)localObject2).equals(localObject1))
      {
        paramContext = paramContext + "#M";
      }
      else
      {
        str = ((ActivityManager.RunningAppProcessInfo)localObject3).processName;
        localStringBuilder = new StringBuilder();
        if (!ঢ.ᴸ()) {
          continue;
        }
        localObject1 = "com.eg.android.AlipayGphoneRC";
        localObject2 = paramContext;
        if (str.startsWith((String)localObject1 + ":"))
        {
          localObject1 = new StringBuilder().append(paramContext).append("#");
          localObject2 = ((ActivityManager.RunningAppProcessInfo)localObject3).processName;
          localObject3 = new StringBuilder();
          if (!ঢ.ᴸ()) {
            continue;
          }
          paramContext = "com.eg.android.AlipayGphoneRC";
          localObject2 = ((String)localObject2).replace(((StringBuilder)localObject3).append(paramContext).append(":").toString(), "");
        }
        paramContext = (Context)localObject2;
      }
    }
    localObject1 = paramContext;
    break label240;
    localObject1 = "";
    label240:
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
  
  public static String k(Context paramContext)
  {
    for (;;)
    {
      int i;
      try
      {
        List localList = paramContext.getPackageManager().getInstalledPackages(0);
        StringBuilder localStringBuilder = new StringBuilder();
        i = 0;
        if (i < localList.size())
        {
          PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
          j = localPackageInfo.applicationInfo.flags;
          if (((j & 0x1) == 0) && ((j & 0x80) == 0))
          {
            j = 1;
            if (j == 0) {
              break label223;
            }
            String str = localPackageInfo.packageName;
            if (!ঢ.ᴸ()) {
              break label217;
            }
            paramContext = "com.eg.android.AlipayGphoneRC";
            if (str.equals(paramContext))
            {
              localStringBuilder.append(localPackageInfo.packageName).append(localPackageInfo.versionCode).append("-");
              break label223;
            }
            if ((localPackageInfo.packageName.contains("theme")) || (localPackageInfo.packageName.startsWith("com.google.")) || (localPackageInfo.packageName.startsWith("com.android."))) {
              break label223;
            }
            localStringBuilder.append(localPackageInfo.packageName).append("-");
            break label223;
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
        ᒮ.a("biz", "GetInstalledAppEx", paramContext);
        return "";
      }
      int j = 0;
      continue;
      label217:
      paramContext = "com.eg.android.AlipayGphone";
      continue;
      label223:
      i += 1;
    }
  }
  
  private static PackageInfo ʻ(Context paramContext, String paramString)
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
  
  public static Map<String, String> ʼॱ(String paramString)
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
  
  public static if ʿ(Context paramContext)
  {
    String str;
    if (ঢ.ᴸ()) {
      str = "com.eg.android.AlipayGphoneRC";
    } else {
      str = "com.eg.android.AlipayGphone";
    }
    return ᐝ(paramContext, str);
  }
  
  public static String ˋ(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      int j = paramString3.indexOf(paramString1) + paramString1.length();
      int i = 0;
      if (!TextUtils.isEmpty(paramString2)) {
        i = paramString3.indexOf(paramString2, j);
      }
      if (i <= 0)
      {
        paramString1 = paramString3.substring(j);
        return paramString1;
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
  
  public static WebView ˎ(Activity paramActivity, String paramString1, String paramString2)
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
    localObject = new StringBuilder().append(paramString2.getUserAgentString());
    String str1 = "Android " + Build.VERSION.RELEASE;
    String str2 = c();
    String str3 = localContext.getResources().getConfiguration().locale.toString();
    String str4 = h(localContext);
    paramString2.setUserAgentString(new StringBuilder(" (").append(str1).append(";").append(str2).append(";").append(str3).append(";;").append(str4).append(")(sdk android)").toString());
    paramString2.setRenderPriority(WebSettings.RenderPriority.HIGH);
    paramString2.setSupportMultipleWindows(true);
    paramString2.setJavaScriptEnabled(true);
    paramString2.setSavePassword(false);
    paramString2.setJavaScriptCanOpenWindowsAutomatically(true);
    paramString2.setMinimumFontSize(paramString2.getMinimumFontSize() + 8);
    paramString2.setAllowFileAccess(false);
    paramString2.setTextSize(WebSettings.TextSize.NORMAL);
    paramActivity.setVerticalScrollbarOverlay(true);
    paramActivity.setDownloadListener(new ｱ(localContext));
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
  
  public static boolean ˏ(WebView paramWebView, String paramString, Activity paramActivity)
  {
    if (TextUtils.isEmpty(paramString)) {
      return true;
    }
    if ((paramString.toLowerCase().startsWith("alipays://platformapi/startApp?".toLowerCase())) || (paramString.toLowerCase().startsWith("intent://platformapi/startapp?".toLowerCase()))) {}
    try
    {
      if (!ঢ.ᴸ()) {
        break label497;
      }
      paramWebView = "com.eg.android.AlipayGphoneRC";
    }
    catch (Throwable paramWebView)
    {
      for (;;)
      {
        boolean bool;
        continue;
        paramWebView = "com.eg.android.AlipayGphone";
      }
    }
    paramWebView = ᐝ(paramActivity, paramWebView);
    if (paramWebView != null)
    {
      bool = paramWebView.a();
      if (!bool) {}
    }
    else
    {
      return true;
    }
    paramWebView = paramString;
    if (paramString.startsWith("intent://platformapi/startapp")) {
      paramWebView = paramString.replaceFirst("intent://platformapi/startapp\\?", "alipays://platformapi/startApp?");
    }
    paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramWebView)));
    return true;
    if ((TextUtils.equals(paramString, "sdklite://h5quit")) || (TextUtils.equals(paramString, "http://m.alipay.com/?action=h5quit")))
    {
      paramWebView = ᒎ.ˋᐨ(ᒎ.Ǐ.h);
      ᒬ.a = ᒬ.a(paramWebView.h, paramWebView.i, "");
      paramActivity.finish();
      return true;
    }
    if (paramString.startsWith("sdklite://h5quit?result=")) {}
    try
    {
      paramWebView = paramString.substring(paramString.indexOf("sdklite://h5quit?result=") + 24);
      int i = Integer.parseInt(paramWebView.substring(paramWebView.lastIndexOf("&end_code=") + 10));
      if ((i == ᒎ.Ɩᐝ.h) || (i == ᒎ.ɩˈ.h))
      {
        if (ᒯ.r)
        {
          paramWebView = new StringBuilder();
          paramString = URLDecoder.decode(paramString);
          String str = URLDecoder.decode(paramString);
          str = str.substring(str.indexOf("sdklite://h5quit?result=") + 24, str.lastIndexOf("&end_code=")).split("&return_url=")[0];
          int j = paramString.indexOf("&return_url=") + 12;
          paramWebView.append(str).append("&return_url=").append(paramString.substring(j, paramString.indexOf("&", j))).append(paramString.substring(paramString.indexOf("&", j)));
          paramWebView = paramWebView.toString();
        }
        else
        {
          paramWebView = URLDecoder.decode(paramString);
          paramWebView = paramWebView.substring(paramWebView.indexOf("sdklite://h5quit?result=") + 24, paramWebView.lastIndexOf("&end_code="));
        }
        paramString = ᒎ.ˋᐨ(i);
        ᒬ.a = ᒬ.a(paramString.h, paramString.i, paramWebView);
      }
      else
      {
        paramWebView = ᒎ.ˋᐨ(ᒎ.Ɨ.h);
        ᒬ.a = ᒬ.a(paramWebView.h, paramWebView.i, "");
      }
    }
    catch (Exception paramWebView)
    {
      for (;;) {}
    }
    paramWebView = ᒎ.ˋᐨ(ᒎ.ȋᐝ.h);
    ᒬ.a = ᒬ.a(paramWebView.h, paramWebView.i, "");
    paramActivity.runOnUiThread(new ĉ(paramActivity));
    return true;
    paramWebView.loadUrl(paramString);
    return true;
  }
  
  public static String ॱ(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      int j = paramString3.indexOf(paramString1) + paramString1.length();
      int i = paramString1.length();
      if (j <= i) {
        return "";
      }
      i = 0;
      if (!TextUtils.isEmpty(paramString2)) {
        i = paramString3.indexOf(paramString2, j);
      }
      if (i <= 0)
      {
        paramString1 = paramString3.substring(j);
        return paramString1;
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
  
  private static boolean ॱ(PackageInfo paramPackageInfo)
  {
    String str = "";
    boolean bool = false;
    if (paramPackageInfo == null)
    {
      paramPackageInfo = "" + "info == null";
    }
    else if (paramPackageInfo.signatures == null)
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
    if (!bool) {
      ᒮ.a("auth", "NotIncludeSignatures", paramPackageInfo);
    }
    return bool;
  }
  
  private static if ᐝ(Context paramContext, String paramString)
  {
    Object localObject3 = null;
    try
    {
      Object localObject1 = paramContext.getPackageManager().getPackageInfo(paramString, 192);
      localObject3 = localObject1;
      localObject1 = localObject3;
      if (!ॱ(localObject3)) {
        try
        {
          localObject1 = ʻ(paramContext, paramString);
        }
        catch (Throwable paramContext)
        {
          ᒮ.a("auth", "GetInstalledPackagesEx", paramContext);
          localObject1 = localObject3;
        }
      }
      Object localObject2;
      if (ॱ(localPackageInfo)) {
        break label130;
      }
    }
    catch (Throwable localThrowable)
    {
      ᒮ.a("auth", "GetPackageInfoEx", localThrowable);
      localObject2 = localObject3;
      if (!ॱ(null)) {
        try
        {
          localObject2 = ʻ(paramContext, paramString);
        }
        catch (Throwable paramContext)
        {
          ᒮ.a("auth", "GetInstalledPackagesEx", paramContext);
          localObject2 = localObject3;
        }
      }
    }
    finally
    {
      if (!ॱ(null)) {
        try
        {
          ʻ(paramContext, paramString);
        }
        catch (Throwable paramContext)
        {
          ᒮ.a("auth", "GetInstalledPackagesEx", paramContext);
        }
      }
    }
    return null;
    label130:
    if (localPackageInfo == null) {
      return null;
    }
    paramContext = new if();
    paramContext.ʻﭠ = localPackageInfo.signatures;
    paramContext.b = localPackageInfo.versionCode;
    return paramContext;
  }
  
  public static final class if
  {
    public int b;
    public Signature[] ʻﭠ;
    
    public if() {}
    
    public final boolean a()
    {
      if ((this.ʻﭠ == null) || (this.ʻﭠ.length <= 0)) {
        return false;
      }
      int i = 0;
      while (i < this.ʻﭠ.length)
      {
        String str = ﾏ.a(this.ʻﭠ[i].toByteArray());
        if ((str != null) && (!TextUtils.equals(str, "b6cbad6cbd5ed0d209afc69ad3b7a617efaae9b3c47eabe0be42d924936fa78c8001b1fd74b079e5ff9690061dacfa4768e981a526b9ca77156ca36251cf2f906d105481374998a7e6e6e18f75ca98b8ed2eaf86ff402c874cca0a263053f22237858206867d210020daa38c48b20cc9dfd82b44a51aeb5db459b22794e2d649")))
        {
          ᒮ.a("biz", "PublicKeyUnmatch", str);
          return true;
        }
        i += 1;
      }
      return false;
    }
  }
}
