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
import android.os.Build.VERSION;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebView;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
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
import org.json.JSONObject;

final class n
{
  private static SharedPreferences a;
  
  public static int a(float paramFloat, Context paramContext)
  {
    return (int)(TypedValue.applyDimension(1, paramFloat, paramContext.getResources().getDisplayMetrics()) + 0.5F);
  }
  
  public static int a(Context paramContext)
  {
    return q.a(paramContext);
  }
  
  public static Bitmap a(String paramString)
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
  
  private static Writer a(String paramString, Writer paramWriter)
  {
    if (paramString != null)
    {
      if (paramString.length() == 0) {
        return paramWriter;
      }
      int m = paramString.length();
      int i = 0;
      int k;
      for (int j = i; i < m; j = k)
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
        o.a(s.b.d).a(paramContext).a();
      }
    }
    return null;
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
      o.a(s.b.d).a(paramDouble).a();
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
  
  public static String a(JSONObject paramJSONObject)
  {
    return paramJSONObject.optString("d", "");
  }
  
  public static String a(Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject != null) && (paramArrayOfObject.length != 0))
    {
      int i = 1;
      StringBuilder localStringBuilder = new StringBuilder(paramArrayOfObject.length * 7);
      localStringBuilder.append(paramArrayOfObject[0]);
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
    Object localObject = h(paramContext);
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
  
  @SuppressLint({"NewApi"})
  public static void a(WebView paramWebView, final String paramString)
  {
    c.b().post(new Runnable()
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
              localObject = o.a(s.b.d);
              localStringBuilder = new StringBuilder("unhandeld exception on evaluateJavascript and trying via loadUrl ");
              localStringBuilder.append(paramString);
              ((p)localObject).a(localException1, localStringBuilder.toString()).a();
              return;
            }
            catch (Exception localException2)
            {
              o.a(s.b.d).a(localException2, "unhandeld exception on evaluateJavascript and failed while using loadUrl").a();
              return;
            }
          }
          Object localObject = o.a(s.b.d);
          StringBuilder localStringBuilder = new StringBuilder("unhandeld exception on evaluateJavascript");
          localStringBuilder.append(paramString);
          ((p)localObject).a(localException2, localStringBuilder.toString()).a();
        }
      }
    });
  }
  
  public static void a(c.a paramA, boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder("PREFS_READY_EVENT_NEEDED_");
    localStringBuilder.append(paramA.toString());
    a(localStringBuilder.toString(), true);
  }
  
  public static void a(String paramString, int paramInt)
  {
    SharedPreferences.Editor localEditor = h(c.c()).edit();
    localEditor.putInt(paramString, paramInt);
    localEditor.commit();
  }
  
  public static void a(String paramString, long paramLong)
  {
    SharedPreferences.Editor localEditor = h(c.c()).edit();
    localEditor.putLong(paramString, paramLong);
    localEditor.commit();
  }
  
  public static void a(String paramString, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = h(c.c()).edit();
    localEditor.putBoolean(paramString, paramBoolean);
    localEditor.commit();
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    return paramContext.checkCallingOrSelfPermission(paramString) == 0;
  }
  
  public static SharedPreferences b()
  {
    return h(c.c());
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
        ((StringBuffer)localObject).append(Integer.toHexString(0xFF & paramString[i]));
        i += 1;
      }
      paramString = ((StringBuffer)localObject).toString();
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      o.a(s.b.d).a(paramString).a();
    }
    return "";
  }
  
  public static String b(JSONObject paramJSONObject)
  {
    return paramJSONObject.optString("bd", "");
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
    return h(c.c()).getString("com.ironsource.mobilcore.Consts.PREFS_PLUGIN_PARAM", null);
  }
  
  public static String c(String paramString)
  {
    String str = b(paramString);
    paramString = str;
    if (TextUtils.isEmpty(str)) {
      paramString = "file_".concat(String.valueOf(System.currentTimeMillis()));
    }
    return paramString.concat(".apk");
  }
  
  public static String c(JSONObject paramJSONObject)
  {
    return paramJSONObject.optString("sd", "");
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
          d1 = Math.sqrt(Math.pow(localDisplayMetrics.widthPixels / d1, 2.0D) + Math.pow(localDisplayMetrics.heightPixels / d2, 2.0D));
          return d1;
        }
      }
      catch (Exception paramContext)
      {
        o.a(s.b.d).a(paramContext).a();
        return -1.0D;
      }
      double d2 = d3;
    }
  }
  
  public static String d()
  {
    return h(c.c()).getString("com.ironsource.mobilcore.Consts.PREFS_MEDIATION_PARAM", null);
  }
  
  public static String d(String paramString)
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
  
  public static String e()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
    localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    return localSimpleDateFormat.format(Calendar.getInstance().getTime());
  }
  
  protected static String e(Context paramContext)
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
  
  public static int f(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().orientation;
  }
  
  public static boolean g(Context paramContext)
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
      o.a(s.b.d).a(paramContext).a();
    }
    label55:
    return false;
  }
  
  @SuppressLint({"InlinedApi"})
  public static SharedPreferences h(Context paramContext)
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
}
