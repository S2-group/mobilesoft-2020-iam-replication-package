package com.ironsource.mobilcore;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
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
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;
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
import org.apache.http.client.methods.HttpGet;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class aE
{
  aE() {}
  
  public static int a(Context paramContext)
  {
    return aK.a(paramContext);
  }
  
  public static Intent a(Context paramContext, String paramString1, String paramString2, aJ.a paramA, String paramString3, JSONArray paramJSONArray)
  {
    paramContext = m(paramContext);
    paramContext.putExtra("com.ironsource.mobilecore.MobileCoreReport_extra_result", paramA.a());
    paramContext.putExtra("s#ge1%dp1%dys#gT1%dt1%dr1%do1%dps#ge1%dr", paramA.b());
    paramContext.putExtra("com.ironsource.mobilecore.MobileCoreReport_extra_flow", paramString1);
    paramContext.putExtra("com.ironsource.mobilcore.MobileCoreReport_extra_flow_type", paramString2);
    if (!TextUtils.isEmpty(paramString3)) {
      paramContext.putExtra("com.ironsource.mobilcore.MobileCoreReport_extra_offer", paramString3);
    }
    return paramContext;
  }
  
  public static final Intent a(JSONObject paramJSONObject)
  {
    String str1 = paramJSONObject.optString("shareDialogTitle", "");
    String str2 = paramJSONObject.optString("shareSubject", "");
    paramJSONObject = paramJSONObject.optString("shareText", "");
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.SEND");
    localIntent.setType("text/plain");
    if (!TextUtils.isEmpty(str2)) {
      localIntent.putExtra("android.intent.extra.SUBJECT", str2);
    }
    if (!TextUtils.isEmpty(paramJSONObject)) {
      localIntent.putExtra("android.intent.extra.TEXT", paramJSONObject);
    }
    return Intent.createChooser(localIntent, str1);
  }
  
  public static File a(InputStream paramInputStream, String paramString1, String paramString2)
    throws IOException
  {
    File localFile = new File(paramString1, paramString2);
    new File(paramString1).mkdirs();
    A.a("writeFile | writing " + paramString1 + "/" + paramString2, 55);
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
  
  public static String a(int paramInt)
  {
    if (paramInt == 1) {
      return "cell";
    }
    if (paramInt == 0) {
      return "wifi";
    }
    if (paramInt == 2) {
      return "wimax";
    }
    if (paramInt == 3) {
      return "ethernet";
    }
    return "none";
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
      paramString2 = paramContext.getPackageManager().getPackageArchiveInfo(paramString1, 0);
      j = k;
      if (TextUtils.isEmpty(paramString1)) {
        i = 0;
      }
      j = i;
      paramString2.applicationInfo.sourceDir = paramString1;
      j = i;
      paramString2.applicationInfo.publicSourceDir = paramString1;
      j = i;
      paramString1 = paramString2.applicationInfo.packageName;
      j = i;
      A.a("^^^appName: " + paramString1, 55);
      return paramString1;
    }
    catch (Exception paramString1)
    {
      if (j == 0) {
        a(paramContext, aE.class.getName(), paramString1);
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
  
  public static String a(Double paramDouble, Context paramContext)
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
      A.a("Error formatting double value", 55);
      a(paramContext, aE.class.getName(), paramDouble);
    }
    return null;
  }
  
  public static String a(Exception paramException)
  {
    StackTraceElement localStackTraceElement = paramException.getStackTrace()[0];
    return paramException.getMessage() + "###" + localStackTraceElement.getFileName() + "##" + localStackTraceElement.getMethodName() + ":" + localStackTraceElement.getLineNumber();
  }
  
  public static String a(String paramString)
  {
    return MessageFormat.format("http://ads.mobilecore.com/?package={0}&ver={1}&type={2}", new Object[] { MobileCore.c().getPackageName(), "0.9.4", paramString });
  }
  
  public static HttpClient a()
  {
    try
    {
      Object localObject = KeyStore.getInstance(KeyStore.getDefaultType());
      ((KeyStore)localObject).load(null, null);
      localObject = new U((KeyStore)localObject);
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
  
  public static HttpGet a(URI paramURI)
  {
    paramURI = new HttpGet(paramURI);
    paramURI.addHeader("Accept-Encoding", "gzip");
    paramURI.addHeader("Content-Encoding", "gzip");
    return paramURI;
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
      paramActivity = new M(paramActivity, paramActivity);
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
  
  public static void a(Context paramContext, Intent paramIntent)
  {
    paramContext.startService(paramIntent);
  }
  
  public static void a(Context paramContext, String paramString, Exception paramException)
  {
    paramException = paramString + "###" + a(paramException);
    A.a("reportError | Error: " + paramException, 2);
    b(paramContext, paramString, paramException);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, aJ.a paramA, String paramString3, String paramString4)
  {
    paramString1 = a(paramContext, paramString1, paramString2, paramA, paramString3, null);
    if (!TextUtils.isEmpty(paramString4)) {
      paramString1.putExtra("com.ironsource.mobilcore.MobileCoreReport_extra_offers", paramString4);
    }
    paramContext.startService(paramString1);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    paramString1 = paramString1 + "###" + paramString2;
    paramString2 = new Intent(paramContext, MobileCoreReport.class);
    paramString2.putExtra("com.ironsource.mobilcore.MobileCoreReport_extra_ex", paramString1);
    paramString2.putExtra("s#ge1%dp1%dys#gT1%dt1%dr1%do1%dps#ge1%dr", 70);
    paramString2.putExtra("1%dns#ge1%dk1%do1%dt", paramString3);
    paramString2.putExtra("s#gds#gis#g_s#ge1%du1%dqs#gi1%dn1%dus#g_s#ga1%dr1%dt1%dxs#ge", aC.a(paramContext).a());
    paramContext.startService(paramString2);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3, int paramInt1, String paramString4, int paramInt2)
  {
    A.a("monitorInstall | going to monitor package: " + paramString2, 55);
    Object localObject = n(paramContext);
    int i = ((SharedPreferences)localObject).getInt("com.ironsource.mobilecore.prefs_alarm_id", 0);
    localObject = ((SharedPreferences)localObject).edit();
    ((SharedPreferences.Editor)localObject).putInt("com.ironsource.mobilecore.prefs_alarm_id", i + 1);
    ((SharedPreferences.Editor)localObject).commit();
    A.a("monitorInstall | alaram id " + i, 55);
    localObject = (AlarmManager)paramContext.getSystemService("alarm");
    Intent localIntent = new Intent(paramContext, InstallationTracker.class);
    localIntent.setAction("track_install");
    localIntent.putExtra("1%dns#ge1%dk1%do1%dt", MobileCore.a(paramContext));
    localIntent.putExtra("s#ge1%dms#ga1%dns#g_s#ges#ggs#ga1%dks#gcs#ga1%dps#g_s#ga1%dr1%dt1%dxs#ge", paramString2);
    localIntent.putExtra("com.ironsource.mobilcore.MobileCoreReport_extra_flow_type", paramString1);
    localIntent.putExtra("com.ironsource.mobilcore.MobileCoreReport_extra_offer", paramString3);
    localIntent.putExtra("com.ironsource.mobilecore.MobileCoreReport_extra_flow", paramString4);
    localIntent.putExtra("com.ironsource.mobilecore.prefs_tracker_id", paramInt2);
    paramContext = PendingIntent.getBroadcast(paramContext, i, localIntent, 1073741824);
    ((AlarmManager)localObject).set(0, System.currentTimeMillis() + paramInt1 * 60 * 1000, paramContext);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    paramContext.startService(a(paramContext, paramString1, paramString2, aJ.a.a(paramString3), paramString4, null));
  }
  
  public static <G, R> void a(AsyncTask<Void, G, R> paramAsyncTask)
  {
    a(paramAsyncTask, null);
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
    paramWebView.setWebViewClient(new WebViewClient());
  }
  
  @SuppressLint({"SetJavaScriptEnabled"})
  public static void a(WebView paramWebView, WebChromeClient paramWebChromeClient)
  {
    paramWebView.getSettings().setJavaScriptEnabled(true);
    paramWebView.getSettings().setSupportMultipleWindows(false);
    paramWebView.getSettings().setSupportZoom(false);
    paramWebView.setInitialScale(100);
    paramWebView.setHorizontalScrollBarEnabled(false);
    paramWebView.getSettings().setNeedInitialFocus(false);
    paramWebView.getSettings().setDomStorageEnabled(true);
    if (paramWebChromeClient != null) {
      paramWebView.setWebChromeClient(paramWebChromeClient);
    }
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    return paramContext.checkCallingOrSelfPermission(paramString) == 0;
  }
  
  public static String b()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = MobileCore.c().getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((ApplicationInfo)localIterator.next()).packageName);
    }
    return "'" + TextUtils.join(",", localArrayList) + "'";
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
  
  public static String b(String paramString)
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
      a(MobileCore.c(), aE.class.getName(), paramString);
    }
    return "";
  }
  
  public static void b(Context paramContext, String paramString1, String paramString2)
  {
    paramString1 = paramString1 + "###" + paramString2;
    paramString2 = m(paramContext);
    paramString2.putExtra("com.ironsource.mobilcore.MobileCoreReport_extra_ex", paramString1);
    paramString2.putExtra("s#ge1%dp1%dys#gT1%dt1%dr1%do1%dps#ge1%dr", 70);
    paramContext.startService(paramString2);
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    boolean bool = true;
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      A.a("checkAlreadyInstalled, already installed = " + bool, 3);
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
    paramContext = paramContext.getPackageManager();
    Intent localIntent = new Intent("android.intent.action.MAIN", null);
    localIntent.addCategory("android.intent.category.LAUNCHER");
    int i = paramContext.queryIntentActivities(localIntent, 0).size();
    A.a("found an app total of " + i, 55);
    return i;
  }
  
  public static String c()
  {
    switch (d(MobileCore.c()))
    {
    default: 
      return "";
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
    }
    return "xxhdpi";
  }
  
  public static String c(String paramString)
  {
    try
    {
      paramString = paramString.substring(paramString.lastIndexOf('/') + 1);
      return paramString;
    }
    catch (IndexOutOfBoundsException paramString)
    {
      A.a("fileFromPath error: " + paramString.getMessage(), 55);
    }
    return null;
  }
  
  public static int d(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().densityDpi;
  }
  
  public static SharedPreferences d()
  {
    return n(MobileCore.c());
  }
  
  public static String d(String paramString)
  {
    String str = b(paramString);
    paramString = str;
    if (TextUtils.isEmpty(str)) {
      paramString = "file_".concat(String.valueOf(System.currentTimeMillis()));
    }
    return paramString.concat(".apk");
  }
  
  @SuppressLint({"NewApi"})
  public static double e(Context paramContext)
  {
    try
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      WindowManager localWindowManager = (WindowManager)paramContext.getSystemService("window");
      double d1;
      if (Build.VERSION.SDK_INT >= 17)
      {
        localWindowManager.getDefaultDisplay().getRealMetrics(localDisplayMetrics);
        d1 = localDisplayMetrics.xdpi;
        if (d1 != 0.0D) {
          break label162;
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
        localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
        break;
      }
    }
    catch (Exception localException)
    {
      b(paramContext, aE.class.getName(), "getDeviceScreenSizeError " + localException.getMessage());
      return -1.0D;
    }
  }
  
  public static String e(String paramString)
    throws UnsupportedEncodingException
  {
    return URLEncoder.encode(paramString, "UTF-8");
  }
  
  @SuppressLint({"NewApi"})
  public static int f(Context paramContext)
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
  
  public static String f(String paramString)
  {
    String str = paramString;
    if (paramString.contains("//play.google.com")) {
      str = "market://details?id=" + paramString.substring(paramString.indexOf("id=") + 3);
    }
    return str;
  }
  
  protected static String g(Context paramContext)
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
  
  public static String g(String paramString)
  {
    int i = paramString.indexOf("id=");
    int j = i + 3;
    int k = paramString.indexOf("&", i);
    if (i >= 0)
    {
      if (k >= 0) {
        return paramString.substring(j, k);
      }
      return paramString.substring(j);
    }
    return "idFieldMissing";
  }
  
  public static int h(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().orientation;
  }
  
  public static String h(String paramString)
  {
    paramString = paramString.substring(paramString.lastIndexOf("/") + 1, paramString.length());
    if (paramString.lastIndexOf(".") < 0) {
      return ".png";
    }
    return paramString.substring(paramString.lastIndexOf("."), paramString.length());
  }
  
  @SuppressLint({"NewApi"})
  public static InputStream i(String paramString)
  {
    return new ByteArrayInputStream(paramString.getBytes(Charset.defaultCharset()));
  }
  
  public static boolean i(Context paramContext)
  {
    boolean bool = false;
    String str = paramContext.getPackageName();
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    if (paramContext.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if ((localRunningAppProcessInfo.importance != 100) || (!localRunningAppProcessInfo.processName.equals(str))) {
        break label72;
      }
      bool = true;
    }
    label72:
    for (;;)
    {
      break;
      return bool;
    }
  }
  
  public static String j(Context paramContext)
  {
    return MessageFormat.format("https://play.google.com/store/apps/details?id={0}", new Object[] { paramContext.getPackageName() });
  }
  
  public static String k(Context paramContext)
  {
    return "'" + o(paramContext) + "'";
  }
  
  public static boolean l(Context paramContext)
  {
    if (Build.VERSION.SDK_INT <= 16) {}
    try
    {
      if (Settings.Secure.getInt(paramContext.getContentResolver(), "install_non_market_apps") != 1) {
        break label40;
      }
      return true;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        int i;
        label40:
        a(paramContext, aE.class.getName(), localException);
      }
    }
    i = Settings.Secure.getInt(paramContext.getContentResolver(), "install_non_market_apps");
    return i == 1;
  }
  
  public static Intent m(Context paramContext)
  {
    Intent localIntent = new Intent(paramContext, MobileCoreReport.class);
    localIntent.putExtra("1%dns#ge1%dk1%do1%dt", MobileCore.a(paramContext));
    localIntent.putExtra("s#gds#gis#g_s#ge1%du1%dqs#gi1%dn1%dus#g_s#ga1%dr1%dt1%dxs#ge", aC.a(paramContext).a());
    return localIntent;
  }
  
  @SuppressLint({"InlinedApi"})
  public static SharedPreferences n(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 9) {
      return paramContext.getSharedPreferences("1%dss#gfs#ge1%dr1%dps#g_s#gds#ge1%drs#gas#ghs#gSs#g_s#ge1%dr1%dos#gCs#ge1%dls#gis#gb1%do1%dm", 4);
    }
    return paramContext.getSharedPreferences("1%dss#gfs#ge1%dr1%dps#g_s#gds#ge1%drs#gas#ghs#gSs#g_s#ge1%dr1%dos#gCs#ge1%dls#gis#gb1%do1%dm", 0);
  }
  
  private static JSONObject o(Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      Object localObject = aC.a(paramContext);
      String str1 = ((aC)localObject).d();
      String str2 = ((aC)localObject).b();
      localObject = ((aC)localObject).c();
      String str3 = MobileCore.a(paramContext);
      localJSONObject.putOpt("os", Build.VERSION.RELEASE);
      localJSONObject.putOpt("deviceCode", Build.DEVICE);
      localJSONObject.putOpt("uid", str1);
      localJSONObject.putOpt("imei", str2);
      localJSONObject.putOpt("mac", localObject);
      localJSONObject.putOpt("devId", str3);
      localJSONObject.putOpt("appId", paramContext.getApplicationContext().getPackageName());
      localJSONObject.putOpt("deviceName", Build.MODEL);
      localJSONObject.putOpt("deviceBrand", Build.MANUFACTURER);
      localJSONObject.putOpt("uns", Boolean.valueOf(l(paramContext)));
      localJSONObject.putOpt("externalStorage", Boolean.valueOf(a(paramContext, "android.permission.WRITE_EXTERNAL_STORAGE")));
      localJSONObject.putOpt("sdkVer", "0.9.4");
      localJSONObject.putOpt("conn", Integer.valueOf(aK.a(paramContext)));
      A.a("getMobileParams " + localJSONObject.toString(), 55);
      return localJSONObject;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        a(paramContext, aE.class.getName(), localException);
      }
    }
  }
  
  public static abstract class a
    implements aE.c
  {
    public a() {}
    
    public final void a(String paramString) {}
    
    public void a(String paramString, int paramInt) {}
    
    public void a(String paramString, Exception paramException) {}
    
    public final void a(String paramString, boolean paramBoolean) {}
    
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
    public abstract String a();
    
    public abstract void a(boolean paramBoolean);
  }
  
  public static abstract class e
    implements aE.d
  {
    public e() {}
    
    public final String a()
    {
      return null;
    }
    
    public void a(boolean paramBoolean) {}
  }
}
