package com.alipay.sdk.lIII;

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
import android.webkit.WebView;
import com.alipay.sdk.app.lIlI;
import com.alipay.sdk.app.lIll;
import com.alipay.sdk.l.I.I;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
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
public class llIl
{
  public static final String[] I = { "10.1.5.1013151", "10.1.5.1013148" };
  private static final char[] l = "0123456789ABCDEF".toCharArray();
  
  /* Error */
  private static I I(Context paramContext, String paramString1, int paramInt, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokestatic 40	com/alipay/sdk/lIII/llIl:lI	(Landroid/content/Context;Ljava/lang/String;)Landroid/content/pm/PackageInfo;
    //   5: astore 4
    //   7: aload 4
    //   9: invokestatic 43	com/alipay/sdk/lIII/llIl:l	(Landroid/content/pm/PackageInfo;)Z
    //   12: ifne +21 -> 33
    //   15: aload_0
    //   16: aload_1
    //   17: invokestatic 46	com/alipay/sdk/lIII/llIl:ll	(Landroid/content/Context;Ljava/lang/String;)Landroid/content/pm/PackageInfo;
    //   20: astore_0
    //   21: goto +60 -> 81
    //   24: astore_0
    //   25: ldc 48
    //   27: ldc 50
    //   29: aload_0
    //   30: invokestatic 55	com/alipay/sdk/app/I/I:I	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   33: aload 4
    //   35: astore_0
    //   36: goto +45 -> 81
    //   39: astore_3
    //   40: goto +57 -> 97
    //   43: astore 4
    //   45: ldc 48
    //   47: ldc 57
    //   49: aload 4
    //   51: invokestatic 55	com/alipay/sdk/app/I/I:I	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   54: aconst_null
    //   55: invokestatic 43	com/alipay/sdk/lIII/llIl:l	(Landroid/content/pm/PackageInfo;)Z
    //   58: ifne +21 -> 79
    //   61: aload_0
    //   62: aload_1
    //   63: invokestatic 46	com/alipay/sdk/lIII/llIl:ll	(Landroid/content/Context;Ljava/lang/String;)Landroid/content/pm/PackageInfo;
    //   66: astore_0
    //   67: goto +14 -> 81
    //   70: astore_0
    //   71: ldc 48
    //   73: ldc 50
    //   75: aload_0
    //   76: invokestatic 55	com/alipay/sdk/app/I/I:I	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   79: aconst_null
    //   80: astore_0
    //   81: aload_0
    //   82: invokestatic 43	com/alipay/sdk/lIII/llIl:l	(Landroid/content/pm/PackageInfo;)Z
    //   85: ifne +5 -> 90
    //   88: aconst_null
    //   89: areturn
    //   90: aload_0
    //   91: iload_2
    //   92: aload_3
    //   93: invokestatic 60	com/alipay/sdk/lIII/llIl:I	(Landroid/content/pm/PackageInfo;ILjava/lang/String;)Lcom/alipay/sdk/lIII/llIl$I;
    //   96: areturn
    //   97: aconst_null
    //   98: invokestatic 43	com/alipay/sdk/lIII/llIl:l	(Landroid/content/pm/PackageInfo;)Z
    //   101: ifne +21 -> 122
    //   104: aload_0
    //   105: aload_1
    //   106: invokestatic 46	com/alipay/sdk/lIII/llIl:ll	(Landroid/content/Context;Ljava/lang/String;)Landroid/content/pm/PackageInfo;
    //   109: pop
    //   110: goto +12 -> 122
    //   113: astore_0
    //   114: ldc 48
    //   116: ldc 50
    //   118: aload_0
    //   119: invokestatic 55	com/alipay/sdk/app/I/I:I	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   122: aload_3
    //   123: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	124	0	paramContext	Context
    //   0	124	1	paramString1	String
    //   0	124	2	paramInt	int
    //   0	124	3	paramString2	String
    //   5	29	4	localPackageInfo	PackageInfo
    //   43	7	4	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   15	21	24	java/lang/Throwable
    //   0	7	39	finally
    //   45	54	39	finally
    //   0	7	43	java/lang/Throwable
    //   61	67	70	java/lang/Throwable
    //   104	110	113	java/lang/Throwable
  }
  
  public static I I(Context paramContext, List<I.I> paramList)
  {
    if (paramList == null) {
      return null;
    }
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Object localObject = (I.I)paramList.next();
      if (localObject != null)
      {
        localObject = I(paramContext, ((I.I)localObject).I, ((I.I)localObject).l, ((I.I)localObject).lI);
        if ((localObject != null) && (!((I)localObject).I()) && (!((I)localObject).l())) {
          return localObject;
        }
      }
    }
    return null;
  }
  
  private static I I(PackageInfo paramPackageInfo, int paramInt, String paramString)
  {
    if (paramPackageInfo == null) {
      return null;
    }
    return new I(paramPackageInfo, paramInt, paramString);
  }
  
  public static String I()
  {
    if (com.alipay.sdk.app.I.I()) {
      return "com.eg.android.AlipayGphoneRC";
    }
    return com.alipay.sdk.I.I.I;
  }
  
  public static String I(int paramInt)
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
  
  public static String I(Context paramContext, String paramString)
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
  
  public static String I(String paramString)
  {
    if ((com.alipay.sdk.app.I.I()) && (TextUtils.equals(paramString, "com.eg.android.AlipayGphone"))) {
      return "com.eg.android.AlipayGphoneRC.IAlixPay";
    }
    return "com.eg.android.AlipayGphone.IAlixPay";
  }
  
  public static String I(String paramString1, String paramString2, String paramString3)
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
  
  public static String I(byte[] paramArrayOfByte)
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
      com.alipay.sdk.app.I.I.I("auth", "GetPublicKeyFromSignEx", paramArrayOfByte);
    }
    return null;
  }
  
  public static boolean I(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo("com.alipay.android.app", 128);
      return paramContext != null;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean I(PackageInfo paramPackageInfo)
  {
    if (paramPackageInfo == null) {
      return false;
    }
    try
    {
      paramPackageInfo = paramPackageInfo.versionName;
      if (!TextUtils.equals(paramPackageInfo, I[0]))
      {
        boolean bool = TextUtils.equals(paramPackageInfo, I[1]);
        return bool;
      }
      return true;
    }
    catch (Throwable paramPackageInfo) {}
    return false;
  }
  
  public static boolean I(WebView paramWebView, String paramString, Activity paramActivity)
  {
    if (TextUtils.isEmpty(paramString)) {
      return true;
    }
    if ((paramString.toLowerCase().startsWith("alipays://platformapi/startApp?".toLowerCase())) || (paramString.toLowerCase().startsWith("intent://platformapi/startapp?".toLowerCase())) || ((TextUtils.equals(paramString, "sdklite://h5quit")) || (TextUtils.equals(paramString, "http://m.alipay.com/?action=h5quit")) || (paramString.startsWith("sdklite://h5quit?result=")))) {}
    try
    {
      paramWebView = paramString.substring(paramString.indexOf("sdklite://h5quit?result=") + "sdklite://h5quit?result=".length());
      int i = Integer.parseInt(paramWebView.substring(paramWebView.lastIndexOf("&end_code=") + "&end_code=".length()));
      if ((i != lIll.a.I()) && (i != lIll.g.I()))
      {
        paramWebView = lIll.I(lIll.b.I());
        lIlI.I(lIlI.I(paramWebView.I(), paramWebView.l(), ""));
      }
      else
      {
        if (com.alipay.sdk.I.I.lI)
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
        paramString = lIll.I(i);
        lIlI.I(lIlI.I(paramString.I(), paramString.l(), paramWebView));
      }
    }
    catch (Exception paramWebView)
    {
      for (;;)
      {
        try
        {
          paramWebView = I(paramActivity, com.alipay.sdk.I.l);
          if ((paramWebView != null) && (!paramWebView.l()))
          {
            if (paramWebView.I()) {
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
    lIlI.I(lIlI.lII());
    paramActivity.runOnUiThread(new lllI(paramActivity));
    return true;
    paramWebView.loadUrl(paramString);
    return true;
    lIlI.I(lIlI.lI());
    paramActivity.finish();
    return true;
    return true;
  }
  
  public static String l()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Android ");
    localStringBuilder.append(Build.VERSION.RELEASE);
    return localStringBuilder.toString();
  }
  
  public static String l(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 128).versionName;
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      com.alipay.sdk.app.I.I.I("biz", "GetPackageInfoEx", paramContext);
    }
    return "";
  }
  
  public static String l(String paramString1, String paramString2, String paramString3)
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
  
  public static Map<String, String> l(String paramString)
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
  
  public static boolean l(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(I(), 128);
      if (paramContext == null) {
        return false;
      }
      int i = paramContext.versionCode;
      return i < 99;
    }
    catch (Throwable paramContext)
    {
      ll.I(paramContext);
    }
    return false;
  }
  
  public static boolean l(Context paramContext, List<I.I> paramList)
  {
    for (;;)
    {
      try
      {
        paramList = paramList.iterator();
        if (paramList.hasNext())
        {
          localObject = (I.I)paramList.next();
          if (localObject == null) {
            continue;
          }
          localObject = ((I.I)localObject).I;
          localPackageManager = paramContext.getPackageManager();
        }
      }
      catch (Throwable paramContext)
      {
        Object localObject;
        PackageManager localPackageManager;
        com.alipay.sdk.app.I.I.I("biz", "CheckLaunchAppExistEx", paramContext);
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
  
  private static boolean l(PackageInfo paramPackageInfo)
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
      com.alipay.sdk.app.I.I.I("auth", "NotIncludeSignatures", paramPackageInfo);
    }
    return bool;
  }
  
  private static PackageInfo lI(Context paramContext, String paramString)
    throws PackageManager.NameNotFoundException
  {
    return paramContext.getPackageManager().getPackageInfo(paramString, 192);
  }
  
  public static String lI()
  {
    Object localObject2 = ll();
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
  
  public static String lI(Context paramContext)
  {
    String str1 = l();
    String str2 = lI();
    String str3 = ll(paramContext);
    paramContext = lII(paramContext);
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
  
  public static Map<String, String> lI(String paramString)
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
          localHashMap.put(localObject.substring(0, k), lII(localObject.substring(k + 1)));
        }
        i += 1;
      }
    }
    return localHashMap;
  }
  
  @SuppressLint({"InlinedApi"})
  private static boolean lI(PackageInfo paramPackageInfo)
  {
    int i = paramPackageInfo.applicationInfo.flags;
    return ((i & 0x1) == 0) && ((i & 0x80) == 0);
  }
  
  public static String lII(Context paramContext)
  {
    paramContext = lIl(paramContext);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramContext.widthPixels);
    localStringBuilder.append("*");
    localStringBuilder.append(paramContext.heightPixels);
    return localStringBuilder.toString();
  }
  
  public static String lII(String paramString)
  {
    try
    {
      paramString = URLDecoder.decode(paramString, "utf-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      com.alipay.sdk.app.I.I.I("biz", "H5PayDataAnalysisError", paramString);
    }
    return "";
  }
  
  public static String lIII(Context paramContext)
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
          if (lI(localPackageInfo)) {
            if (localPackageInfo.packageName.equals(I()))
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
        com.alipay.sdk.app.I.I.I("biz", "GetInstalledAppEx", paramContext);
        return "";
      }
      i += 1;
    }
  }
  
  public static String lIIl(Context paramContext)
  {
    return l(paramContext, paramContext.getPackageName());
  }
  
  public static DisplayMetrics lIl(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }
  
  public static boolean lIl(String paramString)
  {
    return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipaydev|alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(paramString).matches();
  }
  
  private static PackageInfo ll(Context paramContext, String paramString)
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
  
  public static String ll()
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
  
  public static String ll(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().locale.toString();
  }
  
  public static JSONObject ll(String paramString)
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
  
  public static String llI(Context paramContext)
  {
    paramContext = llII.I(paramContext);
    return paramContext.substring(0, paramContext.indexOf("://"));
  }
  
  public static String lll(Context paramContext)
  {
    return "-1;-1";
  }
  
  public static final class I
  {
    public final PackageInfo I;
    public final int l;
    public final String lI;
    
    public I(PackageInfo paramPackageInfo, int paramInt, String paramString)
    {
      this.I = paramPackageInfo;
      this.l = paramInt;
      this.lI = paramString;
    }
    
    public boolean I()
    {
      Signature[] arrayOfSignature = this.I.signatures;
      if (arrayOfSignature != null)
      {
        if (arrayOfSignature.length == 0) {
          return false;
        }
        int j = arrayOfSignature.length;
        int i = 0;
        while (i < j)
        {
          String str = llIl.I(arrayOfSignature[i].toByteArray());
          if ((str != null) && (!TextUtils.equals(str, this.lI)))
          {
            com.alipay.sdk.app.I.I.I("biz", "PublicKeyUnmatch", String.format("Got %s, expected %s", new Object[] { str, this.lI }));
            return true;
          }
          i += 1;
        }
        return false;
      }
      return false;
    }
    
    public boolean l()
    {
      return this.I.versionCode < this.l;
    }
  }
}
