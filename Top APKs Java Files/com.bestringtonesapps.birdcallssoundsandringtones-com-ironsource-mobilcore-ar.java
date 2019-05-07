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
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.apache.http.HttpEntity;
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

class ar
{
  protected static SharedPreferences a;
  private static final String[] b = { "9774d56d682e549c" };
  
  ar() {}
  
  public static int a(Context paramContext)
  {
    if (az.a(paramContext, 1)) {
      return 1;
    }
    if (az.a(paramContext, 0)) {
      return 0;
    }
    return -1;
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
    l.a("writeFile | writing " + paramString1 + "/" + paramString2, 55);
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
    return "none";
  }
  
  public static String a(Context paramContext, String paramString)
  {
    return MessageFormat.format("http://ads.mobilecore.com/?package={0}&ver={1}&type={2}", new Object[] { paramContext.getPackageName(), "0.9.1", paramString });
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
      l.a("^^^appName: " + paramString1, 55);
      return paramString1;
    }
    catch (Exception paramString1)
    {
      if (j == 0)
      {
        paramString2 = new Intent(paramContext, MobileCoreReport.class);
        paramString2.putExtra("1%dns#ge1%dk1%do1%dt", MobileCore.a());
        paramString2.putExtra("s#ge1%dp1%dys#gT1%dt1%dr1%do1%dps#ge1%dr", 70);
        StackTraceElement localStackTraceElement = paramString1.getStackTrace()[0];
        paramString2.putExtra("com.ironsource.mobilcore.MobileCoreReport_extra_ex", InstallationTracker.class.toString() + "###" + paramString1.getMessage() + "###" + localStackTraceElement.getClassName() + "@" + localStackTraceElement.getFileName() + ":" + localStackTraceElement.getLineNumber());
        paramContext.startService(paramString2);
      }
    }
    return null;
  }
  
  /* Error */
  private static String a(File paramFile)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 264	java/io/BufferedReader
    //   5: dup
    //   6: new 266	java/io/FileReader
    //   9: dup
    //   10: aload_0
    //   11: invokespecial 267	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   14: invokespecial 270	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   17: astore_1
    //   18: aload_1
    //   19: astore_0
    //   20: aload_1
    //   21: invokevirtual 273	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   24: astore_2
    //   25: aload_2
    //   26: astore_0
    //   27: aload_1
    //   28: invokevirtual 274	java/io/BufferedReader:close	()V
    //   31: aload_0
    //   32: areturn
    //   33: astore_2
    //   34: aconst_null
    //   35: astore_1
    //   36: aload_1
    //   37: astore_0
    //   38: aload_2
    //   39: invokevirtual 277	java/lang/Exception:printStackTrace	()V
    //   42: aload_3
    //   43: astore_0
    //   44: aload_1
    //   45: ifnull -14 -> 31
    //   48: aload_1
    //   49: invokevirtual 274	java/io/BufferedReader:close	()V
    //   52: aconst_null
    //   53: areturn
    //   54: astore_0
    //   55: aconst_null
    //   56: areturn
    //   57: astore_1
    //   58: aconst_null
    //   59: astore_0
    //   60: aload_0
    //   61: ifnull +7 -> 68
    //   64: aload_0
    //   65: invokevirtual 274	java/io/BufferedReader:close	()V
    //   68: aload_1
    //   69: athrow
    //   70: astore_1
    //   71: aload_0
    //   72: areturn
    //   73: astore_0
    //   74: goto -6 -> 68
    //   77: astore_1
    //   78: goto -18 -> 60
    //   81: astore_2
    //   82: goto -46 -> 36
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	85	0	paramFile	File
    //   17	32	1	localBufferedReader	BufferedReader
    //   57	12	1	localObject1	Object
    //   70	1	1	localIOException	IOException
    //   77	1	1	localObject2	Object
    //   24	2	2	str	String
    //   33	6	2	localException1	Exception
    //   81	1	2	localException2	Exception
    //   1	42	3	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   2	18	33	java/lang/Exception
    //   48	52	54	java/io/IOException
    //   2	18	57	finally
    //   27	31	70	java/io/IOException
    //   64	68	73	java/io/IOException
    //   20	25	77	finally
    //   38	42	77	finally
    //   20	25	81	java/lang/Exception
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
      l.a("Error formatting double value", 55);
      a(paramContext, ar.class.getName(), paramDouble);
    }
    return null;
  }
  
  public static String a(String paramString)
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
      a(MobileCore.c(), ar.class.getName(), paramString);
    }
    return "";
  }
  
  public static HttpClient a()
  {
    try
    {
      Object localObject = KeyStore.getInstance(KeyStore.getDefaultType());
      ((KeyStore)localObject).load(null, null);
      localObject = new H((KeyStore)localObject);
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
    if (paramBoolean)
    {
      a(new x(paramActivity, paramActivity), paramString, null);
      return;
    }
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(paramString));
    paramActivity.startActivity(localIntent);
  }
  
  public static void a(Context paramContext, String paramString, Exception paramException)
  {
    l.a("Error: " + paramException.getLocalizedMessage(), 2);
    StackTraceElement localStackTraceElement = paramException.getStackTrace()[0];
    b(paramContext, paramString, paramString + "###" + paramException.getMessage() + "###" + localStackTraceElement.getFileName() + "##" + localStackTraceElement.getMethodName() + ":" + localStackTraceElement.getLineNumber());
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3, int paramInt1, String paramString4, int paramInt2, String paramString5, int paramInt3)
  {
    l.a("monitorInstall | going to monitor package: " + paramString2, 55);
    Object localObject = n(paramContext);
    int i = ((SharedPreferences)localObject).getInt("com.ironsource.mobilecore.prefs_alarm_id", 0);
    localObject = ((SharedPreferences)localObject).edit();
    ((SharedPreferences.Editor)localObject).putInt("com.ironsource.mobilecore.prefs_alarm_id", i + 1);
    ((SharedPreferences.Editor)localObject).commit();
    l.a("monitorInstall | alaram id " + i, 55);
    localObject = (AlarmManager)paramContext.getSystemService("alarm");
    Intent localIntent = new Intent(paramContext, InstallationTracker.class);
    localIntent.setAction("track_install");
    localIntent.putExtra("1%dns#ge1%dk1%do1%dt", MobileCore.a());
    localIntent.putExtra("s#ge1%dms#ga1%dns#g_s#ges#ggs#ga1%dks#gcs#ga1%dps#g_s#ga1%dr1%dt1%dxs#ge", paramString2);
    localIntent.putExtra("1%dts#ge1%dk1%drs#ga1%dms#g_1%dks#gcs#ga1%dr1%dts#g_s#ga1%dr1%dt1%dxs#ge", paramInt1);
    localIntent.putExtra("com.ironsource.mobilcore.MobileCoreReport_extra_flow_type", paramString1);
    localIntent.putExtra("com.ironsource.mobilcore.MobileCoreReport_extra_offer", paramString4);
    localIntent.putExtra("com.ironsource.mobilecore.MobileCoreReport_extra_flow", paramString5);
    if (paramInt3 >= 0) {
      localIntent.putExtra("com.ironsource.mobilecore.prefs_tracker_id", paramInt3);
    }
    if (paramInt1 == 4) {
      localIntent.putExtra("com.ironsource.mobilcore.extra_download_filename", paramString3);
    }
    paramContext = PendingIntent.getBroadcast(paramContext, i, localIntent, 1073741824);
    ((AlarmManager)localObject).set(0, System.currentTimeMillis() + paramInt2 * 60 * 1000, paramContext);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    Intent localIntent = new Intent(paramContext, MobileCoreReport.class);
    localIntent.putExtra("s#ge1%dp1%dys#gT1%dt1%dr1%do1%dps#ge1%dr", 99);
    localIntent.putExtra("com.ironsource.mobilecore.MobileCoreReport_extra_flow", paramString1);
    localIntent.putExtra("com.ironsource.mobilcore.MobileCoreReport_extra_flow_type", paramString2);
    localIntent.putExtra("com.ironsource.mobilecore.MobileCoreReport_extra_result", paramString3);
    localIntent.putExtra("com.ironsource.mobilcore.MobileCoreReport_extra_offer", paramString4);
    localIntent.putExtra("1%dns#ge1%dk1%do1%dt", MobileCore.a());
    paramContext.startService(localIntent);
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
  
  @SuppressLint({"SetJavaScriptEnabled"})
  public static void a(WebView paramWebView, as paramAs)
  {
    paramWebView.getSettings().setJavaScriptEnabled(true);
    paramWebView.getSettings().setSupportMultipleWindows(false);
    paramWebView.clearCache(true);
    paramWebView.getSettings().setSupportZoom(false);
    paramWebView.setInitialScale(100);
    paramWebView.setHorizontalScrollBarEnabled(false);
    paramWebView.getSettings().setNeedInitialFocus(false);
    paramWebView.getSettings().setDomStorageEnabled(true);
    if (paramAs != null) {
      paramWebView.setWebChromeClient(paramAs);
    }
  }
  
  public static void a(x paramX, String paramString, x.a paramA)
  {
    paramX.loadUrl(paramString);
    if (paramA != null) {
      paramX.a(paramA);
    }
    paramX.c();
  }
  
  public static boolean a(File paramFile1, File paramFile2)
  {
    try
    {
      paramFile1 = new FileInputStream(paramFile1);
      paramFile2 = new FileOutputStream(paramFile2);
      byte[] arrayOfByte = new byte['Ѐ'];
      for (;;)
      {
        int i = paramFile1.read(arrayOfByte);
        if (i <= 0) {
          break;
        }
        paramFile2.write(arrayOfByte, 0, i);
      }
      paramFile1.close();
    }
    catch (Exception paramFile1)
    {
      a(MobileCore.c(), ar.class.getName(), paramFile1);
      return false;
    }
    paramFile2.close();
    l.a("File copied.", 55);
    return true;
  }
  
  public static SharedPreferences b()
  {
    return n(MobileCore.c());
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
      paramString = paramString.substring(paramString.lastIndexOf('/') + 1);
      return paramString;
    }
    catch (IndexOutOfBoundsException paramString)
    {
      l.a("fileFromPath error: " + paramString.getMessage(), 55);
    }
    return null;
  }
  
  public static void b(Context paramContext, String paramString1, String paramString2)
  {
    paramString1 = paramString1 + "###" + paramString2 + "###";
    paramString2 = new Intent(paramContext, MobileCoreReport.class);
    paramString2.putExtra("com.ironsource.mobilcore.MobileCoreReport_extra_ex", paramString1);
    paramString2.putExtra("s#ge1%dp1%dys#gT1%dt1%dr1%do1%dps#ge1%dr", 70);
    paramString2.putExtra("1%dns#ge1%dk1%do1%dt", MobileCore.a());
    paramContext.startService(paramString2);
  }
  
  private static void b(File paramFile)
  {
    File[] arrayOfFile = paramFile.listFiles();
    if (arrayOfFile != null)
    {
      int j = arrayOfFile.length;
      int i = 0;
      if (i < j)
      {
        File localFile = arrayOfFile[i];
        if (localFile.isDirectory()) {
          b(localFile);
        }
        for (;;)
        {
          i += 1;
          break;
          localFile.delete();
        }
      }
    }
    paramFile.delete();
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    return paramContext.checkCallingOrSelfPermission(paramString) == 0;
  }
  
  public static int c(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    Intent localIntent = new Intent("android.intent.action.MAIN", null);
    localIntent.addCategory("android.intent.category.LAUNCHER");
    int i = paramContext.queryIntentActivities(localIntent, 0).size();
    l.a("found an app total of " + i, 55);
    return i;
  }
  
  private static String c()
  {
    try
    {
      Object localObject = new File("/sys/class/net/");
      if (((File)localObject).exists())
      {
        List localList = Arrays.asList(((File)localObject).list());
        if (localList.contains("wifi0"))
        {
          localObject = new File(((File)localObject).getPath() + "/wifi0/address");
          if (((File)localObject).exists()) {
            return a((File)localObject);
          }
        }
        else if (localList.contains("eth0"))
        {
          localObject = new File(((File)localObject).getPath() + "/eth0/address");
          if (((File)localObject).exists()) {
            return a((File)localObject);
          }
        }
        else if (localList.contains("wlan0"))
        {
          localObject = new File(((File)localObject).getPath() + "/wlan0/address");
          if (((File)localObject).exists())
          {
            localObject = a((File)localObject);
            return localObject;
          }
        }
      }
    }
    catch (Exception localException) {}
    return null;
  }
  
  public static String c(String paramString)
  {
    String str = a(paramString);
    paramString = str;
    if (TextUtils.isEmpty(str)) {
      paramString = "file_".concat(String.valueOf(System.currentTimeMillis()));
    }
    return paramString.concat(".apk");
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      return true;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static int d(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().densityDpi;
  }
  
  public static String d(String paramString)
    throws UnsupportedEncodingException
  {
    return URLEncoder.encode(paramString, "UTF-8");
  }
  
  @SuppressLint({"NewApi"})
  public static double e(Context paramContext)
  {
    double d2 = 1.0D;
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramContext = (WindowManager)paramContext.getSystemService("window");
    double d3;
    double d1;
    if (Build.VERSION.SDK_INT >= 17)
    {
      paramContext.getDefaultDisplay().getRealMetrics(localDisplayMetrics);
      d3 = localDisplayMetrics.xdpi;
      d1 = d3;
      if (d3 == 0.0D) {
        d1 = 1.0D;
      }
      d3 = localDisplayMetrics.ydpi;
      if (d3 != 0.0D) {
        break label123;
      }
    }
    for (;;)
    {
      return Math.sqrt(Math.pow(localDisplayMetrics.widthPixels / d1, 2.0D) + Math.pow(localDisplayMetrics.heightPixels / d2, 2.0D));
      paramContext.getDefaultDisplay().getMetrics(localDisplayMetrics);
      break;
      label123:
      d2 = d3;
    }
  }
  
  public static String e(String paramString)
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
  
  public static void g(String paramString)
  {
    paramString = new File(paramString);
    b(paramString);
    paramString.mkdir();
  }
  
  @SuppressLint({"InlinedApi"})
  public static String h(Context paramContext)
  {
    Object localObject1 = n(paramContext).getString("s#gDs#gI1%drs#ge1%ds1%dus#gLs#gSs#gI", "");
    Object localObject2;
    if (!TextUtils.isEmpty((CharSequence)localObject1))
    {
      localObject2 = j.b((String)localObject1);
      l.a("MCUtils , getUniqueID() | found id in prefs. uniqueId: " + (String)localObject2, 55);
    }
    do
    {
      return localObject2;
      localObject2 = h(Settings.Secure.getString(paramContext.getApplicationContext().getContentResolver(), "android_id"));
      l.a("not from prefs: " + (String)localObject2, 55);
      localObject1 = localObject2;
      if (TextUtils.isEmpty((CharSequence)localObject2))
      {
        localObject1 = o(paramContext);
        l.a("telephony Manager device id: " + (String)localObject1, 55);
      }
      localObject2 = localObject1;
      if (TextUtils.isEmpty((CharSequence)localObject1))
      {
        localObject2 = p(paramContext);
        l.a("got MAC address: " + (String)localObject2, 55);
      }
      localObject1 = localObject2;
      if (TextUtils.isEmpty((CharSequence)localObject2))
      {
        localObject1 = UUID.randomUUID().toString();
        l.a("got UUID : " + (String)localObject1, 55);
      }
      localObject2 = localObject1;
    } while (!TextUtils.isEmpty(n(MobileCore.c()).getString("s#gDs#gI1%drs#ge1%ds1%dus#gLs#gSs#gI", "")));
    paramContext = n(paramContext).edit();
    paramContext.putString("s#gDs#gI1%drs#ge1%ds1%dus#gLs#gSs#gI", j.a((String)localObject1));
    paramContext.commit();
    l.a("Final UserID saved = " + (String)localObject1, 55);
    return localObject1;
  }
  
  private static String h(String paramString)
  {
    String[] arrayOfString = b;
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      String str = paramString;
      if (i < j)
      {
        if (arrayOfString[i].equals(paramString)) {
          str = null;
        }
      }
      else {
        return str;
      }
      i += 1;
    }
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
    return "'" + q(paramContext) + "'";
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
        a(paramContext, Utils.class.getName(), localException);
      }
    }
    i = Settings.Secure.getInt(paramContext.getContentResolver(), "install_non_market_apps");
    return i == 1;
  }
  
  @SuppressLint({"InlinedApi"})
  public static SharedPreferences m(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 9) {
      return paramContext.getSharedPreferences("1%dss#gfs#ge1%dr1%dps#g_s#gds#ge1%drs#gas#ghs#gSs#g_s#ge1%dr1%dos#gCs#ge1%dls#gis#gb1%do1%dm", 4);
    }
    return paramContext.getSharedPreferences("1%dss#gfs#ge1%dr1%dps#g_s#gds#ge1%drs#gas#ghs#gSs#g_s#ge1%dr1%dos#gCs#ge1%dls#gis#gb1%do1%dm", 0);
  }
  
  public static SharedPreferences n(Context paramContext)
  {
    if (a == null) {
      a = m(paramContext);
    }
    return a;
  }
  
  private static final String o(Context paramContext)
  {
    if (!b(paramContext, "android.permission.READ_PHONE_STATE")) {
      return null;
    }
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext != null)
    {
      paramContext = paramContext.getDeviceId();
      l.a("MCUtils , getDeviceIdFromTelephonyManager() | telephony Manager uniqueId: " + paramContext, 55);
    }
    for (;;)
    {
      return paramContext;
      paramContext = null;
    }
  }
  
  private static final String p(Context paramContext)
  {
    if (!b(paramContext, "android.permission.ACCESS_WIFI_STATE")) {
      try
      {
        paramContext = c();
        return paramContext;
      }
      catch (Exception paramContext)
      {
        return "";
      }
    }
    paramContext = (WifiManager)paramContext.getSystemService("wifi");
    if (paramContext != null)
    {
      paramContext = paramContext.getConnectionInfo().getMacAddress();
      l.a("MCUtils , getMACAddress() | got MAC address: " + paramContext, 55);
      return paramContext;
    }
    return "";
  }
  
  private static JSONObject q(Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      String str1 = Settings.Secure.getString(paramContext.getApplicationContext().getContentResolver(), "android_id");
      String str2 = o(paramContext);
      String str3 = p(paramContext);
      String str4 = j.b(n(paramContext).getString("1%dns#ge1%dk1%do1%dts#g_1%dss#gfs#ge1%dr1%dp", ""));
      localJSONObject.putOpt("os", Build.VERSION.RELEASE);
      localJSONObject.putOpt("deviceCode", Build.DEVICE);
      localJSONObject.putOpt("uid", str1);
      localJSONObject.putOpt("imei", str2);
      localJSONObject.putOpt("mac", str3);
      localJSONObject.putOpt("devId", str4);
      localJSONObject.putOpt("appId", paramContext.getApplicationContext().getPackageName());
      localJSONObject.putOpt("deviceName", Build.MODEL);
      localJSONObject.putOpt("deviceBrand", Build.MANUFACTURER);
      localJSONObject.putOpt("uns", Boolean.valueOf(l(paramContext)));
      localJSONObject.putOpt("externalStorage", Boolean.valueOf(b(paramContext, "android.permission.WRITE_EXTERNAL_STORAGE")));
      localJSONObject.putOpt("sdkVer", "0.9.1");
      l.a("MobileParams " + localJSONObject.toString(), 55);
      return localJSONObject;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        a(paramContext, ar.class.getName(), localException);
      }
    }
  }
  
  public static abstract interface a
  {
    public abstract void a(int paramInt);
    
    public abstract void a(Exception paramException);
    
    public abstract boolean a(HttpEntity paramHttpEntity);
  }
  
  public static abstract interface b
  {
    public abstract void a(String paramString);
    
    public abstract void a(String paramString, int paramInt);
    
    public abstract void a(String paramString, Exception paramException);
    
    public abstract void a(String paramString, boolean paramBoolean);
    
    public abstract void a(JSONObject paramJSONObject)
      throws JSONException;
  }
  
  public static abstract interface c
  {
    public abstract void a(String paramString, boolean paramBoolean);
    
    public abstract void a(boolean paramBoolean);
  }
}
