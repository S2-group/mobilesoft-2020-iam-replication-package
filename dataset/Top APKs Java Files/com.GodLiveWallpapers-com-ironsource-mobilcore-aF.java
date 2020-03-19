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
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
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
import org.json.JSONException;
import org.json.JSONObject;

final class aF
{
  public static int a(float paramFloat, Context paramContext)
  {
    return (int)(TypedValue.applyDimension(1, paramFloat, paramContext.getResources().getDisplayMetrics()) + 0.5F);
  }
  
  public static int a(Context paramContext)
  {
    return aP.a(paramContext);
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
  
  public static aL a(String paramString, Method paramMethod)
  {
    paramMethod = paramMethod.getName() + "(" + a(paramMethod, false) + ")";
    return aK.a(aS.b.b).a(paramString, "api_call", paramMethod);
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
        aK.a(aS.b.c).a(paramContext).a();
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
      aK.a(aS.b.c).a(paramDouble).a();
    }
    return null;
  }
  
  public static String a(Exception paramException)
  {
    StackTraceElement localStackTraceElement = paramException.getStackTrace()[0];
    return paramException.getMessage() + "###" + localStackTraceElement.getFileName() + "##" + localStackTraceElement.getMethodName() + ":" + localStackTraceElement.getLineNumber();
  }
  
  public static String a(Exception paramException, String paramString)
  {
    return paramString + "###" + a(paramException);
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
    return MessageFormat.format("http://ads.mobilecore.com/?package={0}&ver={1}&type={2}", new Object[] { MobileCore.c().getPackageName(), "1.0", paramString });
  }
  
  private static String a(Method paramMethod, boolean paramBoolean)
  {
    Class[] arrayOfClass = paramMethod.getParameterTypes();
    if (arrayOfClass.length == 0) {
      return "";
    }
    StringBuilder localStringBuilder1 = new StringBuilder();
    int i;
    label42:
    StringBuilder localStringBuilder2;
    if (paramBoolean)
    {
      paramMethod = arrayOfClass[0].getName();
      localStringBuilder1.append(paramMethod);
      i = 1;
      if (i >= arrayOfClass.length) {
        break label103;
      }
      localStringBuilder2 = localStringBuilder1.append("|");
      if (!paramBoolean) {
        break label93;
      }
    }
    label93:
    for (paramMethod = arrayOfClass[i].getName();; paramMethod = arrayOfClass[i].getSimpleName())
    {
      localStringBuilder2.append(paramMethod);
      i += 1;
      break label42;
      paramMethod = arrayOfClass[0].getSimpleName();
      break;
    }
    label103:
    return localStringBuilder1.toString();
  }
  
  public static HttpClient a()
  {
    try
    {
      Object localObject = KeyStore.getInstance(KeyStore.getDefaultType());
      ((KeyStore)localObject).load(null, null);
      localObject = new V((KeyStore)localObject);
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
      paramActivity = new N(paramActivity, paramActivity);
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
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3, int paramInt1, String paramString4, int paramInt2)
  {
    if (TextUtils.isEmpty(paramString2))
    {
      B.a("monitorInstall | not monitoring - package name is empty!", 55);
      return;
    }
    B.a("monitorInstall | going to monitor package: " + paramString2, 55);
    Object localObject = j(paramContext);
    int i = ((SharedPreferences)localObject).getInt("com.ironsource.mobilecore.prefs_alarm_id", 0);
    localObject = ((SharedPreferences)localObject).edit();
    ((SharedPreferences.Editor)localObject).putInt("com.ironsource.mobilecore.prefs_alarm_id", i + 1);
    ((SharedPreferences.Editor)localObject).commit();
    B.a("monitorInstall | alaram id " + i, 55);
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
  
  @SuppressLint({"NewApi"})
  public static void a(WebView paramWebView, final String paramString)
  {
    MobileCore.b().post(new Runnable()
    {
      public final void run()
      {
        if (Build.VERSION.SDK_INT >= 19)
        {
          this.a.evaluateJavascript(paramString, null);
          return;
        }
        this.a.loadUrl("javascript:" + paramString);
      }
    });
  }
  
  public static void a(MobileCore.AD_UNITS paramAD_UNITS, boolean paramBoolean)
  {
    a("PREFS_READY_EVENT_NEEDED_" + paramAD_UNITS.toString(), paramBoolean);
  }
  
  public static void a(String paramString, int paramInt)
  {
    SharedPreferences.Editor localEditor = j(MobileCore.c()).edit();
    localEditor.putInt(paramString, paramInt);
    localEditor.commit();
  }
  
  public static void a(String paramString, long paramLong)
  {
    SharedPreferences.Editor localEditor = j(MobileCore.c()).edit();
    localEditor.putLong(paramString, paramLong);
    localEditor.commit();
  }
  
  public static void a(String paramString, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = j(MobileCore.c()).edit();
    localEditor.putBoolean(paramString, paramBoolean);
    localEditor.commit();
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    return paramContext.checkCallingOrSelfPermission(paramString) == 0;
  }
  
  public static boolean a(MobileCore.AD_UNITS paramAD_UNITS)
  {
    return j(MobileCore.c()).getBoolean("PREFS_READY_EVENT_NEEDED_" + paramAD_UNITS.toString(), false);
  }
  
  public static boolean a(Method paramMethod)
  {
    return j(MobileCore.c()).getBoolean(c(paramMethod), false);
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
  
  public static ArrayList<String> b()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = MobileCore.c().getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((ApplicationInfo)localIterator.next()).packageName);
    }
    return localArrayList;
  }
  
  public static void b(Method paramMethod)
  {
    a(c(paramMethod), true);
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
  
  public static String c()
  {
    switch (c(MobileCore.c()))
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
      aK.a(aS.b.c).a(paramString).a();
    }
    return "";
  }
  
  private static String c(Method paramMethod)
  {
    return "com.mobilecore.PREFS_API_CALLED_KEY_" + f() + "_" + paramMethod;
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
      aK.a(aS.b.c).a(paramContext).a();
      return -1.0D;
    }
  }
  
  public static SharedPreferences d()
  {
    return j(MobileCore.c());
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
  
  private static String e()
  {
    try
    {
      String str2 = MobileCore.c().getPackageManager().getInstallerPackageName(MobileCore.c().getPackageName());
      String str1 = str2;
      if (str2 == null) {
        str1 = "";
      }
      return str1;
    }
    catch (Exception localException)
    {
      aK.a(aS.b.c).a(localException).a();
    }
    return "error";
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
    catch (URISyntaxException localURISyntaxException) {}
    return paramString;
  }
  
  private static int f()
  {
    try
    {
      int i = MobileCore.c().getPackageManager().getPackageInfo(MobileCore.c().getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return -1;
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
  
  public static String g(Context paramContext)
  {
    return MessageFormat.format("https://play.google.com/store/apps/details?id={0}", new Object[] { paramContext.getPackageName() });
  }
  
  public static String h(Context paramContext)
  {
    return "'" + k(paramContext) + "'";
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
        aK.a(aS.b.c).a(paramContext).a();
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
  
  private static JSONObject k(Context paramContext)
  {
    boolean bool = true;
    JSONObject localJSONObject = new JSONObject();
    for (;;)
    {
      try
      {
        String str1 = aD.a(paramContext).a();
        String str2 = MobileCore.a(paramContext);
        localJSONObject.putOpt("os", Build.VERSION.RELEASE);
        localJSONObject.putOpt("deviceCode", Build.DEVICE);
        localJSONObject.putOpt("uid", str1);
        localJSONObject.putOpt("devId", str2);
        localJSONObject.putOpt("appId", paramContext.getApplicationContext().getPackageName());
        localJSONObject.putOpt("deviceName", Build.MODEL);
        localJSONObject.putOpt("deviceBrand", Build.MANUFACTURER);
        localJSONObject.putOpt("uns", Boolean.valueOf(i(paramContext)));
        localJSONObject.putOpt("externalStorage", Boolean.valueOf(a(paramContext, "android.permission.WRITE_EXTERNAL_STORAGE")));
        localJSONObject.putOpt("sdkVer", "1.0");
        localJSONObject.putOpt("conn", Integer.valueOf(aP.a(paramContext)));
        paramContext = new Intent(MobileCore.c(), InterstitialVideoActivity.class);
        if (Build.VERSION.SDK_INT < 14) {
          continue;
        }
        if (MobileCore.c().getPackageManager().queryIntentActivities(paramContext, 65536).size() <= 0) {
          continue;
        }
        i = 1;
      }
      catch (Exception paramContext)
      {
        int i;
        aK.a(aS.b.c).a(paramContext).a();
        continue;
        if (i == 0) {
          continue;
        }
        continue;
      }
      localJSONObject.putOpt("vs", Boolean.valueOf(bool));
      localJSONObject.putOpt("ipn", e());
      B.a("getMobileParams " + localJSONObject.toString(), 55);
      return localJSONObject;
      i = 0;
      continue;
      bool = false;
    }
  }
  
  public static abstract class a
    implements aF.c
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
    public abstract void a(boolean paramBoolean);
  }
  
  public static abstract class e
    implements aF.d
  {
    public e() {}
    
    public void a(boolean paramBoolean) {}
  }
}
