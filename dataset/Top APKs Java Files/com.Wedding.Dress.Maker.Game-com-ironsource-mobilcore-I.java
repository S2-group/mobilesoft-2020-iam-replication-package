package com.ironsource.mobilcore;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONException;
import org.json.JSONObject;

final class I
{
  public static int a(float paramFloat, Context paramContext)
  {
    return (int)(TypedValue.applyDimension(1, paramFloat, paramContext.getResources().getDisplayMetrics()) + 0.5F);
  }
  
  public static int a(Context paramContext)
  {
    return Q.a(paramContext);
  }
  
  public static M a(String paramString, Method paramMethod)
  {
    paramMethod = paramMethod.getName() + "(" + a(paramMethod, false) + ")";
    return L.a(T.b.b).a(paramString, "api_call", paramMethod);
  }
  
  public static File a(InputStream paramInputStream, String paramString1, String paramString2)
    throws IOException
  {
    File localFile = new File(paramString1, paramString2);
    new File(paramString1).mkdirs();
    B.a("writeFile | writing " + paramString1 + "/" + paramString2, 55);
    paramString1 = new FileOutputStream(localFile);
    paramString2 = new ByteArrayBuffer(512);
    for (;;)
    {
      int i = paramInputStream.read();
      if (i == -1) {
        break;
      }
      paramString2.append((byte)i);
      if (paramString2.isFull())
      {
        paramString1.write(paramString2.toByteArray());
        paramString1.flush();
        paramString2.clear();
      }
    }
    paramString1.write(paramString2.toByteArray());
    paramString1.flush();
    paramString1.close();
    return localFile;
  }
  
  public static File a(String paramString1, String paramString2)
  {
    paramString1 = new File(paramString1, paramString2);
    if (paramString1.exists()) {
      return paramString1;
    }
    return null;
  }
  
  private static Writer a(String paramString, Writer paramWriter)
    throws IOException
  {
    if ((paramString == null) || (paramString.length() == 0)) {}
    int m;
    int i;
    int j;
    do
    {
      return paramWriter;
      m = paramString.length();
      i = 0;
      j = 0;
    } while (i >= m);
    int k = paramString.charAt(i);
    switch (k)
    {
    default: 
      if ((k < 32) || ((k >= 128) && (k < 160)) || ((k >= 8192) && (k < 8448)))
      {
        paramWriter.write("\\u");
        String str = Integer.toHexString(k);
        paramWriter.write("0000", 0, 4 - str.length());
        paramWriter.write(str);
      }
      break;
    }
    for (;;)
    {
      i += 1;
      j = k;
      break;
      paramWriter.write(92);
      paramWriter.write(k);
      continue;
      if (j == 60) {
        paramWriter.write(92);
      }
      paramWriter.write(k);
      continue;
      paramWriter.write("\\b");
      continue;
      paramWriter.write("\\t");
      continue;
      paramWriter.write("\\n");
      continue;
      paramWriter.write("\\f");
      continue;
      paramWriter.write("\\r");
    }
  }
  
  public static String a(Context paramContext, String paramString1, String paramString2)
  {
    int k = 1;
    int i = 1;
    int j = k;
    try
    {
      paramString1 = paramString1 + "/" + paramString2;
      j = k;
      paramContext = paramContext.getPackageManager().getPackageArchiveInfo(paramString1, 0);
      j = k;
      if (TextUtils.isEmpty(paramString1)) {
        i = 0;
      }
      j = i;
      paramContext.applicationInfo.sourceDir = paramString1;
      j = i;
      paramContext.applicationInfo.publicSourceDir = paramString1;
      j = i;
      paramContext = paramContext.applicationInfo.packageName;
      j = i;
      B.a("^^^appName: " + paramContext, 55);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      if (j == 0) {
        L.a(T.b.c).a(paramContext).a();
      }
    }
    return null;
  }
  
  public static String a(InputStream paramInputStream)
    throws IOException
  {
    paramInputStream = new BufferedReader(new InputStreamReader(paramInputStream));
    StringBuilder localStringBuilder = new StringBuilder();
    for (;;)
    {
      String str = paramInputStream.readLine();
      if (str == null) {
        break;
      }
      localStringBuilder.append(str);
    }
    return localStringBuilder.toString();
  }
  
  public static String a(Double paramDouble)
  {
    try
    {
      DecimalFormat localDecimalFormat = (DecimalFormat)NumberFormat.getNumberInstance(Locale.US);
      localDecimalFormat.setMaximumFractionDigits(2);
      DecimalFormatSymbols localDecimalFormatSymbols = localDecimalFormat.getDecimalFormatSymbols();
      localDecimalFormatSymbols.setDecimalSeparator('.');
      localDecimalFormat.setDecimalFormatSymbols(localDecimalFormatSymbols);
      paramDouble = localDecimalFormat.format(paramDouble);
      return paramDouble;
    }
    catch (Exception paramDouble)
    {
      B.a("Error formatting double value", 55);
      L.a(T.b.c).a(paramDouble).a();
    }
    return null;
  }
  
  public static String a(Exception paramException)
  {
    StackTraceElement localStackTraceElement = paramException.getStackTrace()[0];
    return paramException.getMessage() + " ### " + localStackTraceElement.getFileName() + "##" + localStackTraceElement.getMethodName() + ":" + localStackTraceElement.getLineNumber();
  }
  
  public static String a(Exception paramException, String paramString)
  {
    return paramString + " ###" + a(paramException);
  }
  
  public static String a(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    return paramObject.toString();
  }
  
  public static String a(String paramString)
  {
    return MessageFormat.format("http://ads.yemonisoni.com/?package={0}&ver={1}&type={2}", new Object[] { MobileCore.d().getPackageName(), "1.1", paramString });
  }
  
  private static String a(Method paramMethod, boolean paramBoolean)
  {
    Class[] arrayOfClass = paramMethod.getParameterTypes();
    if (arrayOfClass.length == 0) {
      return "";
    }
    StringBuilder localStringBuilder1 = new StringBuilder();
    int i;
    label43:
    StringBuilder localStringBuilder2;
    if (paramBoolean)
    {
      paramMethod = arrayOfClass[0].getName();
      localStringBuilder1.append(paramMethod);
      i = 1;
      if (i >= arrayOfClass.length) {
        break label104;
      }
      localStringBuilder2 = localStringBuilder1.append("|");
      if (!paramBoolean) {
        break label94;
      }
    }
    label94:
    for (paramMethod = arrayOfClass[i].getName();; paramMethod = arrayOfClass[i].getSimpleName())
    {
      localStringBuilder2.append(paramMethod);
      i += 1;
      break label43;
      paramMethod = arrayOfClass[0].getSimpleName();
      break;
    }
    label104:
    return localStringBuilder1.toString();
  }
  
  public static HttpClient a()
  {
    try
    {
      Object localObject = KeyStore.getInstance(KeyStore.getDefaultType());
      ((KeyStore)localObject).load(null, null);
      localObject = new G((KeyStore)localObject);
      ((SSLSocketFactory)localObject).setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      BasicHttpParams localBasicHttpParams = new BasicHttpParams();
      HttpProtocolParams.setVersion(localBasicHttpParams, HttpVersion.HTTP_1_1);
      HttpProtocolParams.setContentCharset(localBasicHttpParams, "UTF-8");
      HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 60000);
      SchemeRegistry localSchemeRegistry = new SchemeRegistry();
      localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
      localSchemeRegistry.register(new Scheme("https", (SocketFactory)localObject, 443));
      localObject = new DefaultHttpClient(new ThreadSafeClientConnManager(localBasicHttpParams, localSchemeRegistry), localBasicHttpParams);
      return localObject;
    }
    catch (Exception localException) {}
    return new DefaultHttpClient();
  }
  
  public static void a(Activity paramActivity, String paramString, boolean paramBoolean)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    String str = paramString;
    if (!paramString.startsWith("https://"))
    {
      str = paramString;
      if (!paramString.startsWith("http://")) {
        str = "http://" + paramString;
      }
    }
    if (paramBoolean)
    {
      paramActivity = new F(paramActivity, paramActivity);
      paramActivity.loadUrl(str);
      if (0 != 0) {
        paramActivity.a(null);
      }
      paramActivity.a();
      return;
    }
    paramString = new Intent();
    paramString.setAction("android.intent.action.VIEW");
    paramString.setData(Uri.parse(str));
    paramActivity.startActivity(paramString);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt1, String paramString5, int paramInt2)
  {
    if (TextUtils.isEmpty(paramString3))
    {
      B.a("monitorInstall | not monitoring - package name is empty!", 55);
      return;
    }
    B.a("monitorInstall | going to monitor package: " + paramString3, 55);
    Object localObject = j(paramContext);
    int i = ((SharedPreferences)localObject).getInt("com.ironsource.mobilecore.prefs_alarm_id", 0);
    localObject = ((SharedPreferences)localObject).edit();
    ((SharedPreferences.Editor)localObject).putInt("com.ironsource.mobilecore.prefs_alarm_id", i + 1);
    ((SharedPreferences.Editor)localObject).commit();
    B.a("monitorInstall | alaram id " + i, 55);
    localObject = (AlarmManager)paramContext.getSystemService("alarm");
    Intent localIntent = new Intent(paramContext, InstallationTracker.class);
    localIntent.setAction("track_install");
    localIntent.putExtra("1%dns#ge1%dk1%do1%dt", paramString1);
    localIntent.putExtra("s#ge1%dms#ga1%dns#g_s#ges#ggs#ga1%dks#gcs#ga1%dps#g_s#ga1%dr1%dt1%dxs#ge", paramString3);
    localIntent.putExtra("com.ironsource.mobilcore.MobileCoreReport_extra_flow_type", paramString2);
    localIntent.putExtra("com.ironsource.mobilcore.MobileCoreReport_extra_offer", paramString4);
    localIntent.putExtra("com.ironsource.mobilecore.MobileCoreReport_extra_flow", paramString5);
    localIntent.putExtra("com.ironsource.mobilecore.prefs_tracker_id", paramInt2);
    paramContext = PendingIntent.getBroadcast(paramContext, i, localIntent, 1073741824);
    ((AlarmManager)localObject).set(0, System.currentTimeMillis() + paramInt1 * 60 * 1000, paramContext);
  }
  
  @SuppressLint({"InlinedApi", "NewApi"})
  public static <P, G, R> void a(AsyncTask<P, G, R> paramAsyncTask, P... paramVarArgs)
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      paramAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paramVarArgs);
      return;
    }
    paramAsyncTask.execute(paramVarArgs);
  }
  
  public static void a(WebView paramWebView)
  {
    paramWebView.setWebViewClient(new WebViewClient()
    {
      public final void onReceivedError(WebView paramAnonymousWebView, int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2)
      {
        String str = "WebView: " + paramAnonymousWebView.getClass().getName() + ", onReceivedError errorCode:" + paramAnonymousInt + " , description:" + paramAnonymousString1 + " , failingUrl:" + paramAnonymousString2;
        B.a(str, 55);
        L.a(T.b.c).b(str).a();
        super.onReceivedError(paramAnonymousWebView, paramAnonymousInt, paramAnonymousString1, paramAnonymousString2);
      }
    });
  }
  
  @SuppressLint({"SetJavaScriptEnabled", "NewApi"})
  public static void a(WebView paramWebView, WebChromeClient paramWebChromeClient)
  {
    paramWebView.getSettings().setJavaScriptEnabled(true);
    paramWebView.getSettings().setSupportMultipleWindows(false);
    paramWebView.getSettings().setSupportZoom(false);
    paramWebView.setInitialScale(100);
    paramWebView.setHorizontalScrollBarEnabled(false);
    paramWebView.getSettings().setNeedInitialFocus(false);
    paramWebView.getSettings().setDomStorageEnabled(true);
    if (Build.VERSION.SDK_INT >= 16) {}
    try
    {
      paramWebView.getSettings().setAllowFileAccessFromFileURLs(true);
      paramWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
      if (paramWebChromeClient != null) {
        paramWebView.setWebChromeClient(paramWebChromeClient);
      }
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        L.a(T.b.c).a(localException).a();
      }
    }
  }
  
  @SuppressLint({"NewApi"})
  public static void a(WebView paramWebView, final String paramString)
  {
    MobileCore.c().post(new Runnable()
    {
      public final void run()
      {
        try
        {
          if (Build.VERSION.SDK_INT >= 19)
          {
            this.a.evaluateJavascript(paramString, null);
            return;
          }
          this.a.loadUrl("javascript:" + paramString);
          return;
        }
        catch (Exception localException1)
        {
          if ((localException1 instanceof IllegalStateException)) {
            try
            {
              this.a.loadUrl("javascript:" + paramString);
              L.a(T.b.c).a(localException1, "unhandeld exception on evaluateJavascript and trying via loadUrl " + paramString).a();
              return;
            }
            catch (Exception localException2)
            {
              L.a(T.b.c).a(localException2, "unhandeld exception on evaluateJavascript and failed while using loadUrl").a();
              return;
            }
          }
          L.a(T.b.c).a(localException2, "unhandeld exception on evaluateJavascript" + paramString).a();
        }
      }
    });
  }
  
  public static void a(MobileCore.AD_UNITS paramAD_UNITS, boolean paramBoolean)
  {
    a("PREFS_READY_EVENT_NEEDED_" + paramAD_UNITS.toString(), paramBoolean);
  }
  
  public static void a(String paramString, int paramInt)
  {
    SharedPreferences.Editor localEditor = j(MobileCore.d()).edit();
    localEditor.putInt(paramString, paramInt);
    localEditor.commit();
  }
  
  public static void a(String paramString, long paramLong)
  {
    SharedPreferences.Editor localEditor = j(MobileCore.d()).edit();
    localEditor.putLong(paramString, paramLong);
    localEditor.commit();
  }
  
  public static void a(String paramString, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = j(MobileCore.d()).edit();
    localEditor.putBoolean(paramString, paramBoolean);
    localEditor.commit();
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    return paramContext.checkCallingOrSelfPermission(paramString) == 0;
  }
  
  public static boolean a(MobileCore.AD_UNITS paramAD_UNITS)
  {
    return j(MobileCore.d()).getBoolean("PREFS_READY_EVENT_NEEDED_" + paramAD_UNITS.toString(), false);
  }
  
  public static boolean a(Method paramMethod)
  {
    return j(MobileCore.d()).getBoolean(d(paramMethod), false);
  }
  
  public static Bitmap b(String paramString)
  {
    try
    {
      paramString = (HttpURLConnection)new URL(paramString).openConnection();
      paramString.setDoInput(true);
      paramString.connect();
      paramString = BitmapFactory.decodeStream(paramString.getInputStream());
      return paramString;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static String b()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = MobileCore.d().getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((ApplicationInfo)localIterator.next()).packageName);
    }
    return TextUtils.join(",", localArrayList);
  }
  
  public static String b(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
    long l1 = Long.MIN_VALUE;
    if (paramContext.hasNext())
    {
      long l2 = new File(((ApplicationInfo)paramContext.next()).sourceDir).lastModified();
      if (l2 <= l1) {
        break label90;
      }
      l1 = l2;
    }
    label90:
    for (;;)
    {
      break;
      paramContext = new Date(l1);
      return new SimpleDateFormat("ddMMyyyy-HH:mm:ss", Locale.UK).format(paramContext);
    }
  }
  
  private static void b(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = j(MobileCore.d()).edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }
  
  public static void b(Method paramMethod)
  {
    a(d(paramMethod), true);
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    boolean bool = true;
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      B.a("checkAlreadyInstalled, already installed = " + bool, 55);
      return bool;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        bool = false;
      }
    }
  }
  
  public static int c(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().densityDpi;
  }
  
  @SuppressLint({"InlinedApi"})
  public static SharedPreferences c(Context paramContext, String paramString)
  {
    if (Build.VERSION.SDK_INT >= 9) {}
    for (int i = 4;; i = 0) {
      return paramContext.getSharedPreferences(paramString, i);
    }
  }
  
  public static String c()
  {
    switch (c(MobileCore.d()))
    {
    default: 
      return "xhdpi";
    case 120: 
      return "ldpi";
    case 160: 
      return "mdpi";
    case 213: 
      return "tvdpi";
    case 240: 
      return "hdpi";
    case 320: 
      return "xhdpi";
    case 480: 
      return "xxhdpi";
    }
    return "xxxhdpi";
  }
  
  public static String c(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      paramString = ((MessageDigest)localObject).digest();
      localObject = new StringBuffer();
      int i = 0;
      while (i < paramString.length)
      {
        ((StringBuffer)localObject).append(Integer.toHexString(paramString[i] & 0xFF));
        i += 1;
      }
      paramString = ((StringBuffer)localObject).toString();
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      L.a(T.b.c).a(paramString).a();
    }
    return "";
  }
  
  public static boolean c(Method paramMethod)
  {
    if (MobileCore.d() != null) {
      return true;
    }
    B.a("Trying to use " + paramMethod.getName() + " before MobileCore SDK is initialized, make sure to call MobileCore.init() first", 2);
    return false;
  }
  
  @SuppressLint({"NewApi"})
  public static double d(Context paramContext)
  {
    try
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      paramContext = (WindowManager)paramContext.getSystemService("window");
      double d1;
      if (Build.VERSION.SDK_INT >= 17)
      {
        paramContext.getDefaultDisplay().getRealMetrics(localDisplayMetrics);
        d1 = localDisplayMetrics.xdpi;
        if (d1 != 0.0D) {
          break label141;
        }
        d1 = 1.0D;
      }
      for (;;)
      {
        double d3 = localDisplayMetrics.ydpi;
        double d2 = d3;
        if (d3 == 0.0D) {
          d2 = 1.0D;
        }
        d1 = Math.pow(localDisplayMetrics.widthPixels / d1, 2.0D);
        return Math.sqrt(Math.pow(localDisplayMetrics.heightPixels / d2, 2.0D) + d1);
        paramContext.getDefaultDisplay().getMetrics(localDisplayMetrics);
        break;
      }
    }
    catch (Exception paramContext)
    {
      L.a(T.b.c).a(paramContext).a();
      return -1.0D;
    }
  }
  
  public static SharedPreferences d()
  {
    return j(MobileCore.d());
  }
  
  public static String d(String paramString)
  {
    String str = c(paramString);
    paramString = str;
    if (TextUtils.isEmpty(str)) {
      paramString = "file_".concat(String.valueOf(System.currentTimeMillis()));
    }
    return paramString.concat(".apk");
  }
  
  private static String d(Method paramMethod)
  {
    return "com.mobilecore.PREFS_API_CALLED_KEY_" + i() + "_" + paramMethod;
  }
  
  @SuppressLint({"NewApi"})
  public static int e(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramContext = (WindowManager)paramContext.getSystemService("window");
    if (Build.VERSION.SDK_INT >= 17) {
      paramContext.getDefaultDisplay().getRealMetrics(localDisplayMetrics);
    }
    for (;;)
    {
      return localDisplayMetrics.widthPixels;
      paramContext.getDefaultDisplay().getMetrics(localDisplayMetrics);
    }
  }
  
  public static String e()
  {
    return j(MobileCore.d()).getString("com.ironsource.mobilcore.Consts.PREFS_PLUGIN_PARAM", null);
  }
  
  public static String e(String paramString)
  {
    try
    {
      String str2 = new URI(paramString).getHost();
      String str1 = paramString;
      if (!TextUtils.isEmpty(str2))
      {
        str1 = paramString;
        if (str2.contains("play.google.com")) {
          str1 = "market://details?" + paramString.substring(paramString.indexOf("id="));
        }
      }
      return str1;
    }
    catch (Exception localException)
    {
      L.a(T.b.c).b("urlToGooglePlayFormattedUrl exception, url=" + paramString).a();
    }
    return paramString;
  }
  
  public static String f()
  {
    return j(MobileCore.d()).getString("com.ironsource.mobilcore.Consts.PREFS_MEDIATION_PARAM", null);
  }
  
  protected static String f(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      if (paramContext == null) {
        return "null";
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = null;
      }
    }
    return paramContext.versionName;
  }
  
  public static String f(String paramString)
  {
    paramString = paramString.substring(paramString.lastIndexOf("/") + 1, paramString.length());
    if (paramString.lastIndexOf(".") < 0) {
      return ".png";
    }
    return paramString.substring(paramString.lastIndexOf("."), paramString.length());
  }
  
  public static int g(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().orientation;
  }
  
  private static String g()
  {
    try
    {
      String str2 = MobileCore.d().getPackageManager().getInstallerPackageName(MobileCore.d().getPackageName());
      String str1 = str2;
      if (str2 == null) {
        str1 = "";
      }
      return str1;
    }
    catch (Exception localException)
    {
      L.a(T.b.c).a(localException).a();
    }
    return "error";
  }
  
  public static String g(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    StringWriter localStringWriter = new StringWriter();
    try
    {
      synchronized (localStringWriter.getBuffer())
      {
        paramString = a(paramString.replace('\'', ' '), localStringWriter).toString();
        return paramString;
      }
      return "";
    }
    catch (IOException paramString) {}
  }
  
  private static String h()
  {
    try
    {
      Object localObject = MobileCore.d().getPackageManager().getPackageInfo("com.android.vending", 0);
      if (localObject != null)
      {
        localObject = ((PackageInfo)localObject).versionName;
        return localObject;
      }
    }
    catch (Exception localException) {}
    return "-1";
  }
  
  public static String h(Context paramContext)
  {
    return "'" + k(paramContext) + "'";
  }
  
  public static void h(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    b("com.ironsource.mobilcore.Consts.PREFS_PLUGIN_PARAM", paramString);
  }
  
  private static int i()
  {
    try
    {
      int i = MobileCore.d().getPackageManager().getPackageInfo(MobileCore.d().getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return -1;
  }
  
  public static void i(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    b("com.ironsource.mobilcore.Consts.PREFS_MEDIATION_PARAM", paramString);
  }
  
  public static boolean i(Context paramContext)
  {
    if (Build.VERSION.SDK_INT <= 16) {}
    try
    {
      if (Settings.Secure.getInt(paramContext.getContentResolver(), "install_non_market_apps") != 1) {
        break label40;
      }
      return true;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        int i;
        label40:
        L.a(T.b.c).a(paramContext).a();
      }
    }
    i = Settings.Secure.getInt(paramContext.getContentResolver(), "install_non_market_apps");
    return i == 1;
  }
  
  @SuppressLint({"InlinedApi"})
  public static SharedPreferences j(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 9) {
      return paramContext.getSharedPreferences("1%dss#gfs#ge1%dr1%dps#g_s#gds#ge1%drs#gas#ghs#gSs#g_s#ge1%dr1%dos#gCs#ge1%dls#gis#gb1%do1%dm", 4);
    }
    return paramContext.getSharedPreferences("1%dss#gfs#ge1%dr1%dps#g_s#gds#ge1%drs#gas#ghs#gSs#g_s#ge1%dr1%dos#gCs#ge1%dls#gis#gb1%do1%dm", 0);
  }
  
  public static String j(String paramString)
  {
    try
    {
      paramString = j(MobileCore.d()).getString("com.ironsource.mobilcore.Consts.PREFS_FLOW_FILE_VERSION_" + paramString, "-1");
      return paramString;
    }
    catch (Exception paramString) {}
    return "-1";
  }
  
  private static JSONObject k(Context paramContext)
  {
    int j = 0;
    JSONObject localJSONObject = new JSONObject();
    try
    {
      Object localObject2 = H.a(paramContext);
      Object localObject1 = ((H)localObject2).a();
      String str1 = ((H)localObject2).b();
      localObject2 = ((H)localObject2).c();
      String str2 = MobileCore.a(paramContext);
      localJSONObject.putOpt("os", g(Build.VERSION.RELEASE));
      localJSONObject.putOpt("deviceCode", g(Build.DEVICE));
      localJSONObject.putOpt("uid", localObject1);
      localJSONObject.putOpt("imei", str1);
      localJSONObject.putOpt("mac", localObject2);
      localJSONObject.putOpt("devId", str2);
      localJSONObject.putOpt("appId", paramContext.getApplicationContext().getPackageName());
      localJSONObject.putOpt("deviceName", g(Build.MODEL));
      localJSONObject.putOpt("deviceBrand", g(Build.MANUFACTURER));
      localJSONObject.putOpt("uns", Boolean.valueOf(i(paramContext)));
      localJSONObject.putOpt("externalStorage", Boolean.valueOf(a(paramContext, "android.permission.WRITE_EXTERNAL_STORAGE")));
      localJSONObject.putOpt("sdkVer", "1.1");
      localJSONObject.putOpt("conn", Integer.valueOf(Q.a(paramContext)));
      localObject1 = new Intent(MobileCore.d(), InterstitialVideoActivity.class);
      if (Build.VERSION.SDK_INT < 14) {
        break label404;
      }
      if (MobileCore.d().getPackageManager().queryIntentActivities((Intent)localObject1, 65536).size() <= 0) {
        break label399;
      }
      i = 1;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        int i;
        boolean bool;
        L.a(T.b.c).a(paramContext).a();
        continue;
        if (i != 0) {
          bool = true;
        }
      }
    }
    localJSONObject.putOpt("vs", Boolean.valueOf(bool));
    localJSONObject.putOpt("ipn", g());
    localJSONObject.putOpt("gpv", h());
    if (g(paramContext) == 1) {}
    for (i = j;; i = 1)
    {
      localJSONObject.putOpt("orientation", String.valueOf(i));
      paramContext = f();
      if (!TextUtils.isEmpty(paramContext)) {
        localJSONObject.putOpt("mediation", paramContext);
      }
      localJSONObject.putOpt("userProp", MobileCore.b());
      B.a("getMobileParams " + localJSONObject.toString(), 55);
      return localJSONObject;
      label399:
      i = 0;
      break label431;
      label404:
      bool = false;
      break;
    }
  }
  
  public static abstract class a
    implements I.c
  {
    public a() {}
    
    public final void a(String paramString) {}
    
    public void a(String paramString, int paramInt) {}
    
    public void a(String paramString, Exception paramException) {}
    
    public void a(String paramString, boolean paramBoolean) {}
    
    public final void a(JSONObject paramJSONObject)
      throws JSONException
    {}
    
    public final void a(JSONObject paramJSONObject, String paramString, boolean paramBoolean) {}
  }
  
  public static abstract interface b
  {
    public abstract void a(int paramInt);
    
    public abstract void a(Exception paramException);
    
    public abstract boolean a(HttpResponse paramHttpResponse);
  }
  
  public static abstract interface c
  {
    public abstract void a(String paramString);
    
    public abstract void a(String paramString, int paramInt);
    
    public abstract void a(String paramString, Exception paramException);
    
    public abstract void a(String paramString, boolean paramBoolean);
    
    public abstract void a(JSONObject paramJSONObject)
      throws JSONException;
    
    public abstract void a(JSONObject paramJSONObject, String paramString, boolean paramBoolean);
  }
  
  public static abstract interface d
  {
    public abstract void a(boolean paramBoolean);
  }
  
  public static abstract class e
    implements I.d
  {
    public e() {}
    
    public void a(boolean paramBoolean) {}
  }
}
