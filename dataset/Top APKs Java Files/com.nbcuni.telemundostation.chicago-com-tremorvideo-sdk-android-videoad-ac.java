package com.tremorvideo.sdk.android.videoad;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.Checksum;
import org.json.JSONException;
import org.json.JSONObject;

public class ac
{
  private static String A;
  private static float B;
  private static float C;
  private static boolean D;
  private static boolean E;
  private static boolean F;
  private static long G;
  private static String H;
  private static String I;
  private static bv J;
  private static long[] K;
  private static String L;
  private static String M;
  private static String N;
  private static String O;
  private static ab P;
  private static bu Q;
  private static bq R;
  private static boolean S;
  private static String T;
  private static String U;
  public static Context a;
  public static boolean b;
  public static String c;
  public static int d;
  public static int e;
  public static long f;
  public static long g;
  public static boolean h;
  public static int i;
  public static int j;
  public static boolean k;
  public static boolean l;
  public static boolean m;
  public static Settings n;
  public static ArrayList<ac.d> o;
  public static String p;
  public static n q;
  public static boolean r;
  public static String s;
  private static String t;
  private static String u;
  private static String v;
  private static long w;
  private static String[] x;
  private static au y;
  private static Settings z;
  
  static
  {
    boolean bool = true;
    t = "TremoPrefs";
    u = "deviceID";
    v = "__DEX_TAG__";
    w = 0L;
    x = new String[0];
    A = "0";
    D = true;
    E = false;
    if (!D) {}
    for (;;)
    {
      F = bool;
      G = 0L;
      I = null;
      J = new bv();
      K = new long[ac.f.values().length];
      L = "http://ads.videohub.tv";
      M = null;
      N = null;
      O = null;
      R = null;
      b = false;
      d = 2;
      e = 2000;
      f = 10000L;
      g = 8000L;
      h = false;
      i = 100;
      j = 5;
      k = false;
      l = false;
      m = false;
      n = null;
      o = new ArrayList();
      p = null;
      S = false;
      q = null;
      r = false;
      s = null;
      T = "com.google.market";
      U = "com.android.vending";
      return;
      bool = false;
    }
  }
  
  public static String A()
  {
    String str = "3.11.2.65";
    if (!"3.11.2.65".contains(".")) {
      str = "3.11.0.debug";
    }
    return str;
  }
  
  public static String B()
  {
    try
    {
      String str = A;
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public static au C()
  {
    return y;
  }
  
  public static boolean D()
  {
    if (y == null) {
      return false;
    }
    return y.l();
  }
  
  public static void E()
  {
    H = a(new GregorianCalendar());
    G = G();
  }
  
  public static void F()
  {
    Q.a();
    if (H != null)
    {
      long l1 = G();
      long l2 = G;
      z.a(H, l1 - l2);
      H = null;
    }
    P.b();
  }
  
  public static long G()
  {
    return System.nanoTime() / 1000000L - w;
  }
  
  public static int H()
  {
    Display localDisplay = ((WindowManager)a.getSystemService("window")).getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localDisplay.getMetrics(localDisplayMetrics);
    return (int)TypedValue.applyDimension(2, 15.0F, localDisplayMetrics);
  }
  
  public static int I()
  {
    return 25;
  }
  
  public static int J()
  {
    return 15;
  }
  
  public static int K()
  {
    Display localDisplay = ((WindowManager)a.getSystemService("window")).getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localDisplay.getMetrics(localDisplayMetrics);
    return (int)TypedValue.applyDimension(2, 12.0F, localDisplayMetrics);
  }
  
  public static int L()
  {
    return 20;
  }
  
  public static float M()
  {
    if (B == 0.0F) {
      B = O();
    }
    return B;
  }
  
  public static float N()
  {
    if (C == 0.0F) {
      C = U();
    }
    return C;
  }
  
  public static float O()
  {
    Display localDisplay = ((WindowManager)a.getSystemService("window")).getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localDisplay.getMetrics(localDisplayMetrics);
    return localDisplayMetrics.density;
  }
  
  public static void P()
  {
    if (r() < 17)
    {
      ((Activity)a).runOnUiThread(new ac.1());
      return;
    }
    p = WebSettings.getDefaultUserAgent(a);
  }
  
  public static void Q()
  {
    Object localObject = a.getSharedPreferences("PreConfig", 0).getString("preConfigJSON", null);
    e("Load pre config from cache:" + (String)localObject);
    if ((localObject != null) && (((String)localObject).length() > 0)) {}
    try
    {
      localObject = new JSONObject((String)localObject);
      M = ((JSONObject)localObject).getJSONObject("urls").getString("ads");
      if (((JSONObject)localObject).has("adrequest_timeout")) {
        e = ((JSONObject)localObject).getInt("adrequest_timeout");
      }
      if (((JSONObject)localObject).has("adstart_timeout")) {
        f = ((JSONObject)localObject).getLong("adstart_timeout");
      }
      if (((JSONObject)localObject).has("max_buffer_time")) {
        g = ((JSONObject)localObject).getLong("max_buffer_time");
      }
      if (((JSONObject)localObject).has("disable_vast_hls")) {
        h = ((JSONObject)localObject).getBoolean("disable_vast_hls");
      }
      if (((JSONObject)localObject).has("throttle"))
      {
        i = ((JSONObject)localObject).getInt("throttle");
        if ((i < 0) || (i > 100)) {
          i = 100;
        }
      }
      if (((JSONObject)localObject).has("maxRequestPerMinute")) {
        j = ((JSONObject)localObject).getInt("maxRequestPerMinute");
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  public static boolean R()
  {
    Iterator localIterator = a.getPackageManager().getInstalledPackages(8192).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((localPackageInfo.packageName.equals(T)) || (localPackageInfo.packageName.equals(U))) {
        return true;
      }
    }
    return false;
  }
  
  private static float U()
  {
    Display localDisplay = ((WindowManager)a.getSystemService("window")).getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localDisplay.getMetrics(localDisplayMetrics);
    return localDisplayMetrics.scaledDensity;
  }
  
  private static boolean V()
  {
    if (F == true) {}
    for (;;)
    {
      return true;
      try
      {
        Object localObject = Environment.getExternalStorageState();
        if ((((String)localObject).equals("mounted")) || (((String)localObject).equals("mounted_ro")))
        {
          localObject = Environment.getExternalStorageDirectory();
          boolean bool = new File(((File)localObject).getAbsolutePath() + File.separatorChar + "d89f24dc727d476db670624a16933ebd.debug").exists();
          if (bool) {
            continue;
          }
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          a(localException);
        }
      }
    }
    return false;
  }
  
  public static int a(int paramInt)
  {
    Display localDisplay = ((WindowManager)a.getSystemService("window")).getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localDisplay.getMetrics(localDisplayMetrics);
    return (int)TypedValue.applyDimension(2, paramInt, localDisplayMetrics);
  }
  
  public static aq a(Context paramContext)
  {
    if (Integer.parseInt(Build.VERSION.SDK) >= 7) {
      return new as(paramContext);
    }
    return new ar(paramContext);
  }
  
  public static String a(File paramFile)
  {
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new FileReader(paramFile));
      paramFile = new char[(int)paramFile.length()];
      localBufferedReader.read(paramFile);
      localBufferedReader.close();
      paramFile = String.valueOf(paramFile);
      return paramFile;
    }
    catch (IOException paramFile)
    {
      a(paramFile);
    }
    return "";
  }
  
  public static String a(InputStream paramInputStream)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      byte[] arrayOfByte = new byte['Ѐ'];
      for (;;)
      {
        int i1 = paramInputStream.read(arrayOfByte);
        if (i1 <= 0) {
          break;
        }
        localStringBuilder.append(new String(arrayOfByte, 0, i1));
      }
      paramInputStream = localStringBuilder.toString();
    }
    catch (Exception paramInputStream)
    {
      a(paramInputStream);
      return "";
    }
    return paramInputStream;
  }
  
  public static String a(Calendar paramCalendar)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramCalendar.get(1));
    localStringBuilder.append("-");
    localStringBuilder.append(paramCalendar.get(2) + 1);
    localStringBuilder.append("-");
    localStringBuilder.append(paramCalendar.get(7));
    localStringBuilder.append(" ");
    localStringBuilder.append(paramCalendar.get(11));
    localStringBuilder.append(":");
    localStringBuilder.append(paramCalendar.get(12));
    localStringBuilder.append(":");
    localStringBuilder.append(paramCalendar.get(13));
    return localStringBuilder.toString();
  }
  
  public static void a()
  {
    r = true;
  }
  
  public static void a(Activity paramActivity, int paramInt)
  {
    if ((paramInt == 0) || (paramInt == 6))
    {
      c(paramActivity);
      return;
    }
    if ((paramInt == 1) || (paramInt == 7))
    {
      b(paramActivity);
      return;
    }
    paramActivity.setRequestedOrientation(-1);
  }
  
  public static void a(Activity paramActivity, String paramString)
  {
    long l1 = G();
    int i1 = ac.f.a.ordinal();
    if (l1 - K[i1] >= 2000L)
    {
      e("Sending Custom Event: " + paramString);
      K[i1] = l1;
      z.a(paramActivity, paramString, new HashMap());
      return;
    }
    e("Can't send a new event so soon: " + paramString);
  }
  
  public static void a(Activity paramActivity, String paramString, Map<String, String> paramMap)
  {
    long l1 = G();
    int i1 = ac.f.b.ordinal();
    if (l1 - K[i1] >= 2000L)
    {
      e("Sending Custom Event with Parameters: " + paramString);
      K[i1] = l1;
      z.a(paramActivity, paramString, paramMap);
      return;
    }
    e("Can't send a new event so soon: " + paramString);
  }
  
  public static void a(Context paramContext, String[] paramArrayOfString)
  {
    w = System.nanoTime() / 1000000L;
    a = paramContext;
    z = new Settings();
    B = 0.0F;
    C = 0.0F;
    P = new ab(paramContext);
    z.a();
    Q = new bu(paramContext);
    x = (String[])paramArrayOfString.clone();
    P();
    int i1 = 0;
    while (i1 < K.length)
    {
      K[i1] = 0L;
      i1 += 1;
    }
    try
    {
      bp.a(paramContext, true);
      F = V();
      paramContext = new ac.e();
      if (Build.VERSION.SDK_INT >= 11)
      {
        paramContext.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
        return;
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        e("Core: Unable to get location: " + paramContext.toString());
      }
      paramContext.execute(new String[0]);
    }
  }
  
  public static void a(Window paramWindow)
  {
    if (Integer.parseInt(Build.VERSION.SDK) >= 11) {
      new ac.a(paramWindow).a();
    }
  }
  
  public static void a(Settings paramSettings)
  {
    try
    {
      z = new Settings(paramSettings);
      return;
    }
    finally
    {
      paramSettings = finally;
      throw paramSettings;
    }
  }
  
  public static void a(ac.c paramC, String paramString)
  {
    a(paramC, paramString, null);
  }
  
  public static void a(ac.c paramC, String paramString, Throwable paramThrowable)
  {
    int i2 = 0;
    if (!F) {}
    int i3;
    int i1;
    do
    {
      return;
      if (paramString != null)
      {
        paramC = paramString.split("\n");
        i3 = paramC.length;
        i1 = 0;
        while (i1 < i3)
        {
          Log.v("tremor", paramC[i1]);
          i1 += 1;
        }
      }
    } while (paramThrowable == null);
    paramC = paramThrowable.getMessage();
    if (paramC != null)
    {
      paramC = paramC.split("\n");
      i3 = paramC.length;
      i1 = i2;
      while (i1 < i3)
      {
        Log.v("tremor", paramC[i1]);
        i1 += 1;
      }
    }
    Log.v("tremor", paramThrowable.toString());
    a(paramThrowable.getStackTrace());
  }
  
  public static void a(String paramString)
  {
    s = paramString;
  }
  
  public static void a(String paramString1, String paramString2)
  {
    if (y == null)
    {
      o.add(new ac.d(paramString1, paramString2));
      return;
    }
    y.a(paramString1, paramString2);
  }
  
  public static void a(String paramString, Throwable paramThrowable)
  {
    a(ac.c.a, paramString, paramThrowable);
  }
  
  public static void a(String paramString, String[][] paramArrayOfString1, String[][] paramArrayOfString2)
  {
    Log.v("tremorQA", "[");
    Log.v("tremorQA", "    {");
    Log.v("tremorQA", "        \"event_type\": \"" + paramString + "\"");
    Log.v("tremorQA", "        \"event_data\":{");
    int i1 = 0;
    while (i1 < paramArrayOfString1.length)
    {
      Log.v("tremorQA", "            \"" + paramArrayOfString1[i1][0] + "\": \"" + paramArrayOfString1[i1][1] + "\"");
      i1 += 1;
    }
    Log.v("tremorQA", "            \"parameters\":{");
    i1 = 0;
    while (i1 < paramArrayOfString2.length)
    {
      Log.v("tremorQA", "                \"" + paramArrayOfString2[i1][0] + "\": \"" + paramArrayOfString2[i1][1] + "\"");
      i1 += 1;
    }
    Log.v("tremorQA", "                }");
    Log.v("tremorQA", "        }");
    Log.v("tremorQA", "    }");
    Log.v("tremorQA", "],");
  }
  
  public static void a(Throwable paramThrowable)
  {
    a(ac.c.a, null, paramThrowable);
  }
  
  public static void a(Checksum paramChecksum, File paramFile)
  {
    paramChecksum.reset();
    try
    {
      if (paramFile.exists())
      {
        byte[] arrayOfByte = new byte['Ѐ'];
        paramFile = new FileInputStream(paramFile);
        for (;;)
        {
          int i1 = paramFile.read(arrayOfByte);
          if (i1 <= 0) {
            break;
          }
          paramChecksum.update(arrayOfByte, 0, i1);
        }
      }
      return;
    }
    catch (Exception paramFile)
    {
      a(paramFile);
      paramChecksum.reset();
    }
    paramFile.close();
  }
  
  public static void a(JSONObject paramJSONObject)
  {
    try
    {
      c = paramJSONObject.getJSONObject("parameters").getString("AppID");
      ((Activity)a).runOnUiThread(new ac.2(paramJSONObject));
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        c = "";
      }
    }
    finally {}
  }
  
  public static void a(StackTraceElement[] paramArrayOfStackTraceElement)
  {
    int i2 = paramArrayOfStackTraceElement.length;
    int i1 = 0;
    while (i1 < i2)
    {
      StackTraceElement localStackTraceElement = paramArrayOfStackTraceElement[i1];
      e("  " + localStackTraceElement.toString());
      i1 += 1;
    }
  }
  
  public static boolean a(Activity paramActivity)
  {
    if (Integer.parseInt(Build.VERSION.SDK) >= 11) {
      return new ac.b(paramActivity).a();
    }
    return false;
  }
  
  public static boolean a(aw.b paramB)
  {
    bs localBs = null;
    if (y != null) {
      localBs = y.i();
    }
    if (localBs == null) {}
    while (!localBs.a(paramB)) {
      return true;
    }
    return false;
  }
  
  public static boolean a(Checksum paramChecksum, File paramFile, long paramLong)
  {
    if (paramLong == 0L) {
      e("No CRC to check.");
    }
    long l1;
    for (;;)
    {
      return true;
      e("Calculating CRC...");
      a(paramChecksum, paramFile);
      l1 = paramChecksum.getValue();
      if (l1 != paramLong) {
        break;
      }
      e("CRC is good: " + l1);
    }
    e("CRC is invalid. Expected: " + paramLong + ". Got: " + l1);
    return false;
  }
  
  public static void b()
  {
    Runtime localRuntime = Runtime.getRuntime();
    long l1 = localRuntime.freeMemory();
    l1 = (localRuntime.maxMemory() - localRuntime.totalMemory() + l1) / 1024L / 1024L;
    e("Free Memory: " + l1 + "MB");
  }
  
  public static void b(Activity paramActivity)
  {
    if (Integer.parseInt(Build.VERSION.SDK) >= 9)
    {
      paramActivity.setRequestedOrientation(7);
      return;
    }
    paramActivity.setRequestedOrientation(1);
  }
  
  public static void b(Activity paramActivity, String paramString)
  {
    long l1 = G();
    int i1 = ac.f.c.ordinal();
    if (l1 - K[i1] >= 2000L)
    {
      e("Sending State Change: " + paramString);
      K[i1] = l1;
      z.a(paramActivity, paramString);
      return;
    }
    e("Can't send a new event so soon: " + paramString);
  }
  
  public static boolean b(String paramString)
  {
    return a.checkCallingOrSelfPermission(paramString) == 0;
  }
  
  public static void c()
  {
    a(ac.c.b, "Core - Destroy");
    w = 0L;
    z = new Settings();
    a = null;
    if (R != null)
    {
      R.a();
      R = null;
    }
    y.c();
    y = null;
    N = null;
    O = null;
    int i1 = 0;
    while (i1 < K.length)
    {
      K[i1] = 0L;
      i1 += 1;
    }
  }
  
  public static void c(Activity paramActivity)
  {
    if (Integer.parseInt(Build.VERSION.SDK) >= 9)
    {
      paramActivity.setRequestedOrientation(6);
      return;
    }
    paramActivity.setRequestedOrientation(0);
  }
  
  public static void c(String paramString)
  {
    try
    {
      A = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public static String d()
  {
    if (0 != 0) {
      return null;
    }
    if (N != null) {
      return N;
    }
    if (D)
    {
      if ((M != null) && (M.length() > 0))
      {
        if (s != null) {
          return M + "?ssF=default&ssCI=" + s;
        }
        return M;
      }
      if (s != null) {
        return L + "/tap/ad/Ad" + "?ssF=default&ssCI=" + s;
      }
      return L + "/tap/ad/Ad";
    }
    if ((M != null) && (M.length() > 0))
    {
      if (s != null) {
        return M + "?ssF=default&ssCI=" + s;
      }
      return M;
    }
    if (s != null) {
      return L + "/tap/ad/Ad" + "?ssF=default&ssCI=" + s;
    }
    return L + "/tap/ad/Ad";
  }
  
  public static GregorianCalendar d(String paramString)
  {
    String[] arrayOfString = paramString.split(" ");
    paramString = arrayOfString[0].split("-");
    arrayOfString = arrayOfString[1].split(":");
    return new GregorianCalendar(Integer.parseInt(paramString[0]), Integer.parseInt(paramString[1]), Integer.parseInt(paramString[2]), Integer.parseInt(arrayOfString[0]), Integer.parseInt(arrayOfString[1]), Integer.parseInt(arrayOfString[2]));
  }
  
  public static void e(String paramString)
  {
    a(ac.c.a, paramString, null);
  }
  
  public static boolean e()
  {
    return F;
  }
  
  public static String f()
  {
    if (D) {
      return "http://l0.videohub.tv/ssframework/tap/avail/Avail";
    }
    return "http://l0.videohub/ssframework/tap/avail/Avail";
  }
  
  public static void f(String paramString)
  {
    Log.v("tremorQA", paramString);
  }
  
  public static ab g()
  {
    return P;
  }
  
  public static void g(String paramString)
  {
    Object localObject = null;
    if (paramString != null) {}
    for (;;)
    {
      JSONObject localJSONObject1;
      try
      {
        int i1 = paramString.length();
        if (i1 <= 0) {
          break label424;
        }
      }
      finally {}
      try
      {
        e("pre config response new :" + paramString);
        localJSONObject1 = new JSONObject(paramString);
        if (localJSONObject1.has("urls"))
        {
          JSONObject localJSONObject2 = localJSONObject1.getJSONObject("urls");
          if (localJSONObject2.has("ads")) {
            localObject = localJSONObject2.getString("ads");
          }
          if ((localObject == null) || (((String)localObject).length() <= 0) || (!URLUtil.isValidUrl((String)localObject))) {
            break label388;
          }
          M = (String)localObject;
          if (localJSONObject1.has("adrequest_timeout")) {
            e = localJSONObject1.getInt("adrequest_timeout");
          }
          if (localJSONObject1.has("adstart_timeout")) {
            f = localJSONObject1.getLong("adstart_timeout");
          }
          if (localJSONObject1.has("max_buffer_time")) {
            g = localJSONObject1.getLong("max_buffer_time");
          }
          if (localJSONObject1.has("disable_vast_hls")) {
            h = localJSONObject1.getBoolean("disable_vast_hls");
          }
          if (localJSONObject1.has("throttle"))
          {
            i = localJSONObject1.getInt("throttle");
            if ((i < 0) || (i > 100)) {
              i = 100;
            }
          }
          if (localJSONObject1.has("maxRequestPerMinute")) {
            j = localJSONObject1.getInt("maxRequestPerMinute");
          }
          localObject = a.getSharedPreferences("PreConfig", 0).edit();
          ((SharedPreferences.Editor)localObject).putString("preConfigJSON", paramString);
          ((SharedPreferences.Editor)localObject).commit();
        }
      }
      catch (Exception paramString)
      {
        e("Exception pre config JSON processing " + paramString);
        Q();
        continue;
      }
      e("FlowType: " + d);
      y = new au(a, d());
      k = true;
      TremorVideo._Initialized = 1;
      if (o.size() <= 0) {
        break label436;
      }
      paramString = o.iterator();
      if (!paramString.hasNext()) {
        break;
      }
      localObject = (ac.d)paramString.next();
      a(((ac.d)localObject).a, ((ac.d)localObject).b);
      continue;
      label388:
      Q();
      continue;
      label424:
      Q();
    }
    o.clear();
    label436:
    if (n != null)
    {
      e("Delayed set Settings");
      a(n);
      n = null;
    }
    if ((d == 0) && (l))
    {
      l = false;
      C().a(false);
    }
    if ((d == 2) && (m))
    {
      m = false;
      C().a(false);
    }
  }
  
  public static bu h()
  {
    return Q;
  }
  
  public static String i()
  {
    if (D) {
      return "http://l0.videohub.tv/ssframework/tap/ad/Session";
    }
    return "http://l0.videohub.tv/ssframework/tap/ad/Session";
  }
  
  public static String j()
  {
    try
    {
      String str = a.getPackageName();
      return str;
    }
    catch (Exception localException) {}
    return "unknown";
  }
  
  public static String k()
  {
    try
    {
      String str = a.getPackageName();
      str = a.getPackageManager().getPackageInfo(str, 0).versionName;
      return str;
    }
    catch (Exception localException) {}
    return "unknown";
  }
  
  public static bv l()
  {
    return J;
  }
  
  public static int m()
  {
    Display localDisplay = ((WindowManager)a.getSystemService("window")).getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localDisplay.getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }
  
  public static int n()
  {
    Display localDisplay = ((WindowManager)a.getSystemService("window")).getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localDisplay.getMetrics(localDisplayMetrics);
    return localDisplayMetrics.heightPixels;
  }
  
  public static String o()
  {
    try
    {
      Object localObject = a.getPackageName();
      localObject = a.getPackageManager().getPackageInfo((String)localObject, 0).applicationInfo;
      Iterator localIterator = ((ActivityManager)a.getSystemService("activity")).getRunningAppProcesses().iterator();
      PackageManager localPackageManager = a.getPackageManager();
      while (localIterator.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
        if (localRunningAppProcessInfo.processName.compareTo(((ApplicationInfo)localObject).processName) == 0)
        {
          localObject = localPackageManager.getApplicationLabel(localPackageManager.getApplicationInfo(localRunningAppProcessInfo.processName, 128)).toString();
          return localObject;
        }
      }
    }
    catch (Exception localException)
    {
      a(localException);
    }
    return "unknown";
  }
  
  public static String p()
  {
    return x[0];
  }
  
  public static String q()
  {
    SharedPreferences localSharedPreferences = a.getSharedPreferences(t, 0);
    Object localObject = localSharedPreferences.getString(u, null);
    if ((localObject == null) || (((String)localObject).length() == 0))
    {
      e("DeviceID: No stored ID found");
      String str = Settings.Secure.getString(a.getContentResolver(), "android_id");
      e("DeviceID: Android ID = " + str);
      if ((str != null) && (str.length() != 0))
      {
        localObject = str;
        if (!str.equals("9774d56d682e549c")) {}
      }
      else
      {
        e("DeviceID: falling back to randomUUID.");
        localObject = UUID.randomUUID().toString();
        e("DeviceID: udid = " + (String)localObject);
      }
      e("DeviceID: Save ID: " + (String)localObject);
      localSharedPreferences.edit().putString(u, (String)localObject).commit();
      if (localObject != null) {
        break label201;
      }
    }
    label201:
    for (I = "";; I = (String)localObject)
    {
      return I;
      e("DeviceID: Stored ID found");
      break;
    }
  }
  
  public static int r()
  {
    return Integer.parseInt(Build.VERSION.SDK);
  }
  
  public static int s()
  {
    try
    {
      int i1 = a.getPackageManager().getPackageInfo(j(), 0).applicationInfo.targetSdkVersion;
      return i1;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      a(localNameNotFoundException);
    }
    return 0;
  }
  
  public static boolean t()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)a.getSystemService("phone");
    int i2 = localTelephonyManager.getPhoneType();
    if (x().checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0) {}
    for (int i1 = 1; i1 == 0; i1 = 0) {
      return false;
    }
    if (i2 == 0) {
      return false;
    }
    return localTelephonyManager.getLine1Number() != null;
  }
  
  public static String[] u()
  {
    try
    {
      String[] arrayOfString = x;
      return arrayOfString;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public static Settings v()
  {
    try
    {
      Settings localSettings = z;
      return localSettings;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public static boolean w()
  {
    return E;
  }
  
  public static Context x()
  {
    return a;
  }
  
  public static String y()
  {
    return p;
  }
  
  public static String z()
  {
    String str1 = Build.BRAND;
    String str2 = Build.MODEL;
    return "TransperaSDK v" + A() + " : " + str1 + " : " + str2 + " : Android OS : " + Build.VERSION.RELEASE;
  }
}
