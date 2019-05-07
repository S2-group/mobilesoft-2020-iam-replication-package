package com.ironsource.mobilcore;

import android.annotation.SuppressLint;
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONException;
import org.json.JSONObject;

final class ak
{
  private static SharedPreferences a;
  
  public static int a(float paramFloat, Context paramContext)
  {
    return (int)(TypedValue.applyDimension(1, paramFloat, paramContext.getResources().getDisplayMetrics()) + 0.5F);
  }
  
  public static int a(Context paramContext)
  {
    return as.a(paramContext);
  }
  
  public static ao a(String paramString, Method paramMethod)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramMethod.getName());
    localStringBuilder.append("(");
    localStringBuilder.append(a(paramMethod, false));
    localStringBuilder.append(")");
    paramMethod = localStringBuilder.toString();
    return an.a(ax.c.b).a(paramString, "api_call", paramMethod);
  }
  
  public static File a(InputStream paramInputStream, String paramString1, String paramString2)
    throws IOException
  {
    paramString2 = new File(paramString1, paramString2);
    new File(paramString1).mkdirs();
    paramString1 = new FileOutputStream(paramString2);
    ByteArrayBuffer localByteArrayBuffer = new ByteArrayBuffer(512);
    for (;;)
    {
      int i = paramInputStream.read();
      if (i == -1) {
        break;
      }
      localByteArrayBuffer.append((byte)i);
      if (localByteArrayBuffer.isFull())
      {
        paramString1.write(localByteArrayBuffer.toByteArray());
        paramString1.flush();
        localByteArrayBuffer.clear();
      }
    }
    paramString1.write(localByteArrayBuffer.toByteArray());
    paramString1.flush();
    paramString1.close();
    return paramString2;
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
    if (paramString != null)
    {
      if (paramString.length() == 0) {
        return paramWriter;
      }
      int m = paramString.length();
      int i = 0;
      int k;
      for (int j = 0; i < m; j = k)
      {
        k = paramString.charAt(i);
        if (k != 34) {
          if (k != 47)
          {
            if (k != 92) {
              switch (k)
              {
              default: 
                switch (k)
                {
                default: 
                  if ((k >= 32) && ((k < 128) || (k >= 160)) && ((k < 8192) || (k >= 8448))) {
                    break;
                  }
                  paramWriter.write("\\u");
                  String str = Integer.toHexString(k);
                  paramWriter.write("0000", 0, 4 - str.length());
                  paramWriter.write(str);
                  break;
                case 13: 
                  paramWriter.write("\\r");
                  break;
                case 12: 
                  paramWriter.write("\\f");
                }
                break;
              case 10: 
                paramWriter.write("\\n");
                break;
              case 9: 
                paramWriter.write("\\t");
                break;
              case 8: 
                paramWriter.write("\\b");
                break;
              }
            }
          }
          else
          {
            if (j == 60) {
              paramWriter.write(92);
            }
            paramWriter.write(k);
            break label261;
          }
        }
        paramWriter.write(92);
        paramWriter.write(k);
        label261:
        i += 1;
      }
      return paramWriter;
    }
    return paramWriter;
  }
  
  public static String a(Context paramContext, String paramString1, String paramString2)
  {
    boolean bool2 = true;
    boolean bool1 = bool2;
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      bool1 = bool2;
      localStringBuilder.append(paramString1);
      bool1 = bool2;
      localStringBuilder.append("/");
      bool1 = bool2;
      localStringBuilder.append(paramString2);
      bool1 = bool2;
      paramString1 = localStringBuilder.toString();
      bool1 = bool2;
      paramContext = paramContext.getPackageManager().getPackageArchiveInfo(paramString1, 0);
      bool1 = bool2;
      bool2 = true ^ TextUtils.isEmpty(paramString1);
      bool1 = bool2;
      paramContext.applicationInfo.sourceDir = paramString1;
      bool1 = bool2;
      paramContext.applicationInfo.publicSourceDir = paramString1;
      bool1 = bool2;
      paramContext = paramContext.applicationInfo.packageName;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      if (!bool1) {
        an.a(ax.c.d).a(paramContext).a();
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
      an.a(ax.c.d).a(paramDouble).a();
    }
    return null;
  }
  
  public static String a(Exception paramException)
  {
    StackTraceElement localStackTraceElement = paramException.getStackTrace()[0];
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramException.getMessage());
    localStringBuilder.append(" ### ");
    localStringBuilder.append(localStackTraceElement.getFileName());
    localStringBuilder.append("##");
    localStringBuilder.append(localStackTraceElement.getMethodName());
    localStringBuilder.append(":");
    localStringBuilder.append(localStackTraceElement.getLineNumber());
    return localStringBuilder.toString();
  }
  
  public static String a(Exception paramException, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(" ###");
    localStringBuilder.append(a(paramException));
    return localStringBuilder.toString();
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
    return MessageFormat.format("http://ads.yemonisoni.com/?package={0}&ver={1}&type={2}&platform={3}", new Object[] { MobileCore.d().getPackageName(), "2.0", paramString, "android" });
  }
  
  private static String a(Method paramMethod, boolean paramBoolean)
  {
    Class[] arrayOfClass = paramMethod.getParameterTypes();
    if (arrayOfClass.length == 0) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramBoolean) {
      paramMethod = arrayOfClass[0].getName();
    } else {
      paramMethod = arrayOfClass[0].getSimpleName();
    }
    localStringBuilder.append(paramMethod);
    int i = 1;
    while (i < arrayOfClass.length)
    {
      localStringBuilder.append("|");
      if (paramBoolean) {
        paramMethod = arrayOfClass[i].getName();
      } else {
        paramMethod = arrayOfClass[i].getSimpleName();
      }
      localStringBuilder.append(paramMethod);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static String a(JSONObject paramJSONObject)
  {
    return paramJSONObject.optString("d", "");
  }
  
  public static String a(Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject != null) && (paramArrayOfObject.length != 0))
    {
      StringBuilder localStringBuilder = new StringBuilder(paramArrayOfObject.length * 7);
      localStringBuilder.append(paramArrayOfObject[0]);
      int i = 1;
      while (i < paramArrayOfObject.length)
      {
        localStringBuilder.append(",");
        localStringBuilder.append(paramArrayOfObject[i]);
        i += 1;
      }
      return localStringBuilder.toString();
    }
    return null;
  }
  
  public static HttpClient a()
  {
    try
    {
      KeyStore.getInstance(KeyStore.getDefaultType()).load(null, null);
      Object localObject = new BasicHttpParams();
      HttpProtocolParams.setVersion((HttpParams)localObject, HttpVersion.HTTP_1_1);
      HttpProtocolParams.setContentCharset((HttpParams)localObject, "UTF-8");
      HttpConnectionParams.setConnectionTimeout((HttpParams)localObject, 60000);
      SchemeRegistry localSchemeRegistry = new SchemeRegistry();
      localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
      SSLSocketFactory localSSLSocketFactory = SSLSocketFactory.getSocketFactory();
      localSSLSocketFactory.setHostnameVerifier((X509HostnameVerifier)SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      localSchemeRegistry.register(new Scheme("https", localSSLSocketFactory, 443));
      localObject = new DefaultHttpClient(new ThreadSafeClientConnManager((HttpParams)localObject, localSchemeRegistry), (HttpParams)localObject);
      return localObject;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return new DefaultHttpClient();
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt1, String paramString5, int paramInt2, String paramString6)
  {
    if (TextUtils.isEmpty(paramString3)) {
      return;
    }
    Object localObject = j(paramContext);
    int i = ((SharedPreferences)localObject).getInt("com.ironsource.mobilecore.prefs_alarm_id", 0);
    localObject = ((SharedPreferences)localObject).edit();
    ((SharedPreferences.Editor)localObject).putInt("com.ironsource.mobilecore.prefs_alarm_id", i + 1);
    ((SharedPreferences.Editor)localObject).commit();
    localObject = (AlarmManager)paramContext.getSystemService("alarm");
    Intent localIntent = new Intent(paramContext, InstallationTracker.class);
    localIntent.setAction("track_install");
    localIntent.putExtra("1%dns#ge1%dk1%do1%dt", paramString1);
    localIntent.putExtra("s#ge1%dms#ga1%dns#g_s#ges#ggs#ga1%dks#gcs#ga1%dps#g_s#ga1%dr1%dt1%dxs#ge", paramString3);
    localIntent.putExtra("com.ironsource.mobilcore.MobileCoreReport_extra_flow_type", paramString2);
    localIntent.putExtra("com.ironsource.mobilcore.MobileCoreReport_extra_offer", paramString4);
    localIntent.putExtra("com.ironsource.mobilecore.MobileCoreReport_extra_flow", paramString5);
    localIntent.putExtra("com.ironsource.mobilecore.prefs_tracker_id", paramInt2);
    localIntent.putExtra("trigger", paramString6);
    paramContext = PendingIntent.getBroadcast(paramContext, i, localIntent, 1073741824);
    ((AlarmManager)localObject).set(0, System.currentTimeMillis() + paramInt1 * 60 * 1000, paramContext);
  }
  
  public static <G, R> void a(AsyncTask<Void, G, R> paramAsyncTask)
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      paramAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
      return;
    }
    paramAsyncTask.execute(null);
  }
  
  public static void a(WebView paramWebView)
  {
    paramWebView.setWebViewClient(new WebViewClient()
    {
      public final void onReceivedError(WebView paramAnonymousWebView, int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2)
      {
        Object localObject = new StringBuilder("WebView: ");
        ((StringBuilder)localObject).append(paramAnonymousWebView.getClass().getName());
        ((StringBuilder)localObject).append(", onReceivedError errorCode:");
        ((StringBuilder)localObject).append(paramAnonymousInt);
        ((StringBuilder)localObject).append(" , description:");
        ((StringBuilder)localObject).append(paramAnonymousString1);
        ((StringBuilder)localObject).append(" , failingUrl:");
        ((StringBuilder)localObject).append(paramAnonymousString2);
        localObject = ((StringBuilder)localObject).toString();
        an.a(ax.c.d).b((String)localObject).a();
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
    if (Build.VERSION.SDK_INT >= 16) {
      try
      {
        paramWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        paramWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
      }
      catch (Exception localException)
      {
        an.a(ax.c.d).a(localException).a();
      }
    }
    if (paramWebChromeClient != null) {
      paramWebView.setWebChromeClient(paramWebChromeClient);
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
          WebView localWebView = this.a;
          localObject = new StringBuilder("javascript:");
          ((StringBuilder)localObject).append(paramString);
          localWebView.loadUrl(((StringBuilder)localObject).toString());
          return;
        }
        catch (Exception localException1)
        {
          if ((localException1 instanceof IllegalStateException)) {
            try
            {
              localObject = this.a;
              localStringBuilder = new StringBuilder("javascript:");
              localStringBuilder.append(paramString);
              ((WebView)localObject).loadUrl(localStringBuilder.toString());
              localObject = an.a(ax.c.d);
              localStringBuilder = new StringBuilder("unhandeld exception on evaluateJavascript and trying via loadUrl ");
              localStringBuilder.append(paramString);
              ((ao)localObject).a(localException1, localStringBuilder.toString()).a();
              return;
            }
            catch (Exception localException2)
            {
              an.a(ax.c.d).a(localException2, "unhandeld exception on evaluateJavascript and failed while using loadUrl").a();
              return;
            }
          }
          Object localObject = an.a(ax.c.d);
          StringBuilder localStringBuilder = new StringBuilder("unhandeld exception on evaluateJavascript");
          localStringBuilder.append(paramString);
          ((ao)localObject).a(localException2, localStringBuilder.toString()).a();
        }
      }
    });
  }
  
  public static void a(MobileCore.AD_UNITS paramAD_UNITS, boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder("PREFS_READY_EVENT_NEEDED_");
    localStringBuilder.append(paramAD_UNITS.toString());
    a(localStringBuilder.toString(), true);
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
  
  public static boolean a(Method paramMethod)
  {
    return j(MobileCore.d()).getBoolean(d(paramMethod), false);
  }
  
  public static boolean a(Method paramMethod, Object[] paramArrayOfObject)
  {
    return j(MobileCore.d()).getBoolean(c(paramMethod, paramArrayOfObject), false);
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
    while (paramContext.hasNext())
    {
      long l2 = new File(((ApplicationInfo)paramContext.next()).sourceDir).lastModified();
      if (l2 > l1) {
        l1 = l2;
      }
    }
    paramContext = new Date(l1);
    return new SimpleDateFormat("ddMMyyyy-HH:mm:ss", Locale.UK).format(paramContext);
  }
  
  public static String b(JSONObject paramJSONObject)
  {
    return paramJSONObject.optString("bd", "");
  }
  
  public static void b(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = j(MobileCore.d()).edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }
  
  public static void b(Method paramMethod)
  {
    a(d(paramMethod), true);
  }
  
  public static void b(Method paramMethod, Object[] paramArrayOfObject)
  {
    a(c(paramMethod, paramArrayOfObject), true);
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      return true;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static int c(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().densityDpi;
  }
  
  @SuppressLint({"InlinedApi"})
  public static SharedPreferences c(Context paramContext, String paramString)
  {
    int i;
    if (Build.VERSION.SDK_INT >= 9) {
      i = 4;
    } else {
      i = 0;
    }
    return paramContext.getSharedPreferences(paramString, i);
  }
  
  public static String c()
  {
    int i = c(MobileCore.d());
    if (i != 120)
    {
      if (i != 160)
      {
        if (i != 213)
        {
          if (i != 240)
          {
            if (i != 320)
            {
              if (i != 480)
              {
                if (i != 640) {
                  return "xhdpi";
                }
                return "xxxhdpi";
              }
              return "xxhdpi";
            }
            return "xhdpi";
          }
          return "hdpi";
        }
        return "tvdpi";
      }
      return "mdpi";
    }
    return "ldpi";
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
      an.a(ax.c.d).a(paramString).a();
    }
    return "";
  }
  
  private static String c(Method paramMethod, Object[] paramArrayOfObject)
  {
    StringBuilder localStringBuilder = new StringBuilder("com.mobilecore.PREFS_API_CALLED_KEY_");
    localStringBuilder.append(j());
    localStringBuilder.append("_");
    localStringBuilder.append(paramMethod);
    localStringBuilder.append("_");
    localStringBuilder.append(Arrays.toString(paramArrayOfObject));
    return localStringBuilder.toString();
  }
  
  public static String c(JSONObject paramJSONObject)
  {
    return paramJSONObject.optString("sd", "");
  }
  
  public static boolean c(Method paramMethod)
  {
    if (MobileCore.d() != null) {
      return true;
    }
    StringBuilder localStringBuilder = new StringBuilder("Trying to use ");
    localStringBuilder.append(paramMethod.getName());
    localStringBuilder.append(" before MobileCore SDK is initialized, make sure to call MobileCore.init() first");
    ae.a(localStringBuilder.toString(), 2);
    return false;
  }
  
  @SuppressLint({"NewApi"})
  public static double d(Context paramContext)
  {
    for (;;)
    {
      double d3;
      try
      {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        paramContext = (WindowManager)paramContext.getSystemService("window");
        if (Build.VERSION.SDK_INT >= 17) {
          paramContext.getDefaultDisplay().getRealMetrics(localDisplayMetrics);
        } else {
          paramContext.getDefaultDisplay().getMetrics(localDisplayMetrics);
        }
        d3 = localDisplayMetrics.xdpi;
        d2 = 1.0D;
        double d1 = d3;
        if (d3 == 0.0D) {
          d1 = 1.0D;
        }
        d3 = localDisplayMetrics.ydpi;
        if (d3 == 0.0D)
        {
          int i = localDisplayMetrics.widthPixels;
          d3 = i;
          Double.isNaN(d3);
          d1 = d3 / d1;
          d1 = Math.pow(d1, 2.0D);
          i = localDisplayMetrics.heightPixels;
          d3 = i;
          Double.isNaN(d3);
          d2 = d3 / d2;
          d1 = Math.sqrt(d1 + Math.pow(d2, 2.0D));
          return d1;
        }
      }
      catch (Exception paramContext)
      {
        an.a(ax.c.d).a(paramContext).a();
        return -1.0D;
      }
      double d2 = d3;
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
    StringBuilder localStringBuilder = new StringBuilder("com.mobilecore.PREFS_API_CALLED_KEY_");
    localStringBuilder.append(j());
    localStringBuilder.append("_");
    localStringBuilder.append(paramMethod);
    return localStringBuilder.toString();
  }
  
  @SuppressLint({"NewApi"})
  public static int e(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramContext = (WindowManager)paramContext.getSystemService("window");
    if (Build.VERSION.SDK_INT >= 17) {
      paramContext.getDefaultDisplay().getRealMetrics(localDisplayMetrics);
    } else {
      paramContext.getDefaultDisplay().getMetrics(localDisplayMetrics);
    }
    return localDisplayMetrics.widthPixels;
  }
  
  public static String e()
  {
    return j(MobileCore.d()).getString("com.ironsource.mobilcore.Consts.PREFS_PLUGIN_PARAM", null);
  }
  
  public static String e(String paramString)
  {
    try
    {
      localObject = new URI(paramString).getHost();
      if ((TextUtils.isEmpty((CharSequence)localObject)) || (!((String)localObject).contains("play.google.com"))) {
        break label98;
      }
      localObject = new StringBuilder("market://details?");
      ((StringBuilder)localObject).append(paramString.substring(paramString.indexOf("id=")));
      localObject = ((StringBuilder)localObject).toString();
      return localObject;
    }
    catch (Exception localException)
    {
      Object localObject;
      StringBuilder localStringBuilder;
      label98:
      for (;;) {}
    }
    localObject = an.a(ax.c.d);
    localStringBuilder = new StringBuilder("urlToGooglePlayFormattedUrl exception, url=");
    localStringBuilder.append(paramString);
    ((ao)localObject).b(localStringBuilder.toString()).a();
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
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
      paramContext = null;
    }
    if (paramContext == null) {
      return "null";
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
  
  public static String g()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
    localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    return localSimpleDateFormat.format(Calendar.getInstance().getTime());
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
    }
    catch (IOException paramString)
    {
      for (;;) {}
    }
    return "";
  }
  
  private static String h()
  {
    try
    {
      String str3 = MobileCore.d().getPackageManager().getInstallerPackageName(MobileCore.d().getPackageName());
      String str1 = str3;
      if (str3 == null) {
        return "";
      }
    }
    catch (Exception localException)
    {
      an.a(ax.c.d).a(localException).a();
      String str2 = "error";
      return str2;
    }
  }
  
  public static String h(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder("'");
    localStringBuilder.append(k(paramContext));
    localStringBuilder.append("'");
    return localStringBuilder.toString();
  }
  
  public static void h(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    b("com.ironsource.mobilcore.Consts.PREFS_PLUGIN_PARAM", paramString);
  }
  
  private static String i()
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
    catch (Exception localException)
    {
      for (;;) {}
    }
    return "-1";
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
        break label55;
      }
      return true;
    }
    catch (Exception paramContext)
    {
      int i;
      for (;;) {}
    }
    i = Settings.Secure.getInt(paramContext.getContentResolver(), "install_non_market_apps");
    if (i == 1)
    {
      return true;
      an.a(ax.c.d).a(paramContext).a();
    }
    label55:
    return false;
  }
  
  private static int j()
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
  
  @SuppressLint({"InlinedApi"})
  public static SharedPreferences j(Context paramContext)
  {
    try
    {
      if (a == null) {
        if (Build.VERSION.SDK_INT >= 9) {
          a = paramContext.getSharedPreferences("1%dss#gfs#ge1%dr1%dps#g_s#gds#ge1%drs#gas#ghs#gSs#g_s#ge1%dr1%dos#gCs#ge1%dls#gis#gb1%do1%dm", 4);
        } else {
          a = paramContext.getSharedPreferences("1%dss#gfs#ge1%dr1%dps#g_s#gds#ge1%drs#gas#ghs#gSs#g_s#ge1%dr1%dos#gCs#ge1%dls#gis#gb1%do1%dm", 0);
        }
      }
      paramContext = a;
      return paramContext;
    }
    finally {}
  }
  
  public static String j(String paramString)
  {
    try
    {
      SharedPreferences localSharedPreferences = j(MobileCore.d());
      StringBuilder localStringBuilder = new StringBuilder("com.ironsource.mobilcore.Consts.PREFS_FLOW_FILE_VERSION_");
      localStringBuilder.append(paramString);
      paramString = localSharedPreferences.getString(localStringBuilder.toString(), "-1");
      return paramString;
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    return "-1";
  }
  
  private static JSONObject k(Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    boolean bool;
    int i;
    try
    {
      Object localObject2 = aj.a(paramContext);
      Object localObject1 = ((aj)localObject2).a();
      str1 = ((aj)localObject2).g();
      bool = ((aj)localObject2).d();
      String str2 = ((aj)localObject2).e();
      String str3 = ((aj)localObject2).f();
      String str4 = ((aj)localObject2).c();
      localObject2 = ((aj)localObject2).b();
      String str5 = MobileCore.a(paramContext);
      localJSONObject.putOpt("os", g(Build.VERSION.RELEASE));
      localJSONObject.putOpt("deviceCode", g(Build.DEVICE));
      localJSONObject.putOpt("uid", localObject1);
      localJSONObject.putOpt("mcid", localObject2);
      localJSONObject.putOpt("gaid", str4);
      localJSONObject.putOpt("late", Boolean.valueOf(bool));
      localJSONObject.putOpt("imei", str2);
      localJSONObject.putOpt("mac", str3);
      localJSONObject.putOpt("devId", str5);
      localJSONObject.putOpt("carVer", f(paramContext));
      localJSONObject.putOpt("bv", u.a);
      localJSONObject.putOpt("appId", paramContext.getApplicationContext().getPackageName());
      localJSONObject.putOpt("deviceName", g(Build.MODEL));
      localJSONObject.putOpt("deviceBrand", g(Build.MANUFACTURER));
      localJSONObject.putOpt("uns", Boolean.valueOf(i(paramContext)));
      localJSONObject.putOpt("externalStorage", Boolean.valueOf(a(paramContext, "android.permission.WRITE_EXTERNAL_STORAGE")));
      localJSONObject.putOpt("sdkVer", "2.0");
      localJSONObject.putOpt("conn", Integer.valueOf(as.a(paramContext)));
      localObject1 = new Intent(MobileCore.d(), InterstitialVideoActivity.class);
      i = Build.VERSION.SDK_INT;
      j = 0;
      if (i < 14) {
        break label504;
      }
      if (MobileCore.d().getPackageManager().queryIntentActivities((Intent)localObject1, 65536).size() <= 0) {
        break label493;
      }
      i = 1;
    }
    catch (Exception paramContext)
    {
      String str1;
      int j;
      an.a(ax.c.d).a(paramContext).a();
      return localJSONObject;
    }
    localJSONObject.putOpt("vs", Boolean.valueOf(bool));
    localJSONObject.putOpt("ipn", h());
    localJSONObject.putOpt("gpv", i());
    if (g(paramContext) == 1) {
      i = j;
    }
    for (;;)
    {
      localJSONObject.putOpt("orientation", String.valueOf(i));
      paramContext = f();
      if (!TextUtils.isEmpty(paramContext)) {
        localJSONObject.putOpt("mediation", paramContext);
      }
      localJSONObject.putOpt("userProp", MobileCore.b());
      localJSONObject.putOpt("uit", str1);
      return localJSONObject;
      label493:
      i = 0;
      if (i != 0)
      {
        bool = true;
        break;
      }
      label504:
      bool = false;
      break;
      i = 1;
    }
  }
  
  public static abstract class a
    implements ak.c
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
    implements ak.d
  {
    public e() {}
    
    public void a(boolean paramBoolean) {}
  }
}
