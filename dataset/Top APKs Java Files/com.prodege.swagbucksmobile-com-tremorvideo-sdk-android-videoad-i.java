package com.tremorvideo.sdk.android.videoad;

import android.annotation.SuppressLint;
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

@SuppressLint({"NewApi"})
public class i
{
  private static float A;
  private static float B;
  private static boolean C;
  private static boolean D;
  private static boolean E;
  private static long F;
  private static String G;
  private static String H;
  private static bf I;
  private static long[] J;
  private static String K;
  private static String L;
  private static String M;
  private static String N;
  private static h O;
  private static be P;
  private static ba Q;
  private static boolean R;
  private static String S;
  private static String T;
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
  public static boolean n;
  public static b o;
  public static ArrayList<i.d> p;
  public static bx q;
  public static boolean r;
  public static String s;
  private static String t = "TremoPrefs";
  private static String u = "deviceID";
  private static long v = 0L;
  private static String[] w = new String[0];
  private static w x;
  private static b y;
  private static String z = "0";
  
  static
  {
    C = true;
    D = false;
    if (!C) {}
    for (boolean bool = true;; bool = false)
    {
      E = bool;
      F = 0L;
      H = null;
      I = new bf();
      J = new long[i.f.values().length];
      K = "http://ads.videohub.tv";
      L = null;
      M = null;
      N = null;
      Q = null;
      b = false;
      d = 2;
      e = 2000;
      f = 10000L;
      g = 8000L;
      h = false;
      i = 100;
      j = 5;
      k = true;
      l = false;
      m = false;
      n = false;
      o = null;
      p = new ArrayList();
      R = false;
      q = null;
      r = false;
      s = null;
      S = "com.google.market";
      T = "com.android.vending";
      return;
    }
  }
  
  public static String A()
  {
    try
    {
      String str = z;
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public static w B()
  {
    return x;
  }
  
  public static boolean C()
  {
    if (x == null) {
      return false;
    }
    return x.l();
  }
  
  public static void D()
  {
    G = a(new GregorianCalendar());
    F = F();
  }
  
  public static void E()
  {
    P.a();
    if (G != null)
    {
      long l1 = F();
      long l2 = F;
      f.a(G, l1 - l2);
      G = null;
    }
    O.b();
  }
  
  public static long F()
  {
    return System.nanoTime() / 1000000L - v;
  }
  
  public static int G()
  {
    Display localDisplay = ((WindowManager)a.getSystemService("window")).getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localDisplay.getMetrics(localDisplayMetrics);
    return (int)TypedValue.applyDimension(2, 15.0F, localDisplayMetrics);
  }
  
  public static int H()
  {
    return 25;
  }
  
  public static int I()
  {
    return 15;
  }
  
  public static int J()
  {
    Display localDisplay = ((WindowManager)a.getSystemService("window")).getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localDisplay.getMetrics(localDisplayMetrics);
    return (int)TypedValue.applyDimension(2, 12.0F, localDisplayMetrics);
  }
  
  public static int K()
  {
    return 20;
  }
  
  public static float L()
  {
    if (A == 0.0F) {
      A = N();
    }
    return A;
  }
  
  public static float M()
  {
    if (B == 0.0F) {
      B = T();
    }
    return B;
  }
  
  public static float N()
  {
    Display localDisplay = ((WindowManager)a.getSystemService("window")).getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localDisplay.getMetrics(localDisplayMetrics);
    return localDisplayMetrics.density;
  }
  
  public static void O()
  {
    Object localObject = a.getSharedPreferences("PreConfig", 0).getString("preConfigJSON", null);
    e("Load pre config from cache:" + (String)localObject);
    if ((localObject != null) && (((String)localObject).length() > 0)) {
      for (;;)
      {
        try
        {
          localObject = new JSONObject((String)localObject);
          L = ((JSONObject)localObject).getJSONObject("urls").getString("ads");
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
          if (((JSONObject)localObject).has("moat_config"))
          {
            localObject = ((JSONObject)localObject).getJSONObject("moat_config");
            if (((JSONObject)localObject).has("enable"))
            {
              k = ((JSONObject)localObject).getBoolean("enable");
              e("Moat in Preconfig: Enabled: " + k);
              e("Moat in Preconfig: Enable moat after preconfig cache: " + k);
              return;
            }
            e("Moat in Preconfig: No 'enable' parameter in 'moat_config' field");
            continue;
          }
          e("Moat in Preconfig: No 'moat_config' field at all");
        }
        catch (Exception localException)
        {
          e("Preconfig parse error: " + localException.getMessage());
          return;
        }
      }
    }
  }
  
  public static boolean P()
  {
    Iterator localIterator = a.getPackageManager().getInstalledPackages(8192).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((localPackageInfo.packageName.equals(S)) || (localPackageInfo.packageName.equals(T))) {
        return true;
      }
    }
    return false;
  }
  
  private static float T()
  {
    Display localDisplay = ((WindowManager)a.getSystemService("window")).getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localDisplay.getMetrics(localDisplayMetrics);
    return localDisplayMetrics.scaledDensity;
  }
  
  private static boolean U()
  {
    if (E) {}
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
  
  private static void V()
  {
    i.e localE = new i.e();
    if (Build.VERSION.SDK_INT >= 11)
    {
      localE.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
      return;
    }
    localE.execute(new String[0]);
  }
  
  public static int a(int paramInt)
  {
    Display localDisplay = ((WindowManager)a.getSystemService("window")).getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localDisplay.getMetrics(localDisplayMetrics);
    return (int)TypedValue.applyDimension(2, paramInt, localDisplayMetrics);
  }
  
  public static ar a(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 7) {
      return new at(paramContext);
    }
    return new as(paramContext);
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
    long l1 = F();
    int i1 = i.f.a.ordinal();
    if (l1 - J[i1] >= 2000L)
    {
      e("Sending Custom Event: " + paramString);
      J[i1] = l1;
      f.a(paramActivity, paramString, new HashMap());
      return;
    }
    e("Can't send a new event so soon: " + paramString);
  }
  
  public static void a(Activity paramActivity, String paramString, Map<String, String> paramMap)
  {
    long l1 = F();
    int i1 = i.f.b.ordinal();
    if (l1 - J[i1] >= 2000L)
    {
      e("Sending Custom Event with Parameters: " + paramString);
      J[i1] = l1;
      f.a(paramActivity, paramString, paramMap);
      return;
    }
    e("Can't send a new event so soon: " + paramString);
  }
  
  public static void a(Context paramContext, String[] paramArrayOfString)
  {
    v = System.nanoTime() / 1000000L;
    a = paramContext;
    y = new b();
    A = 0.0F;
    B = 0.0F;
    O = new h(paramContext);
    f.b();
    P = new be(paramContext);
    w = (String[])paramArrayOfString.clone();
    int i1 = 0;
    while (i1 < J.length)
    {
      J[i1] = 0L;
      i1 += 1;
    }
    try
    {
      ay.a(paramContext, true);
      E = U();
      if (Build.VERSION.SDK_INT < 16)
      {
        ((Activity)a).runOnUiThread(new i.1());
        return;
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        e("Core: Unable to get location: " + paramContext.toString());
      }
      V();
    }
  }
  
  public static void a(Window paramWindow)
  {
    if (Build.VERSION.SDK_INT >= 11) {
      new i.a(paramWindow).a();
    }
  }
  
  public static void a(b paramB)
  {
    try
    {
      y = new b(paramB);
      return;
    }
    finally
    {
      paramB = finally;
      throw paramB;
    }
  }
  
  public static void a(i.c paramC, String paramString)
  {
    a(paramC, paramString, null);
  }
  
  public static void a(i.c paramC, String paramString, Throwable paramThrowable)
  {
    int i2 = 0;
    if (!e()) {}
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
    if (x == null)
    {
      p.add(new i.d(paramString1, paramString2));
      return;
    }
    x.a(paramString1, paramString2);
  }
  
  public static void a(String paramString, Throwable paramThrowable)
  {
    a(i.c.a, paramString, paramThrowable);
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
    a(i.c.a, null, paramThrowable);
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
      ((Activity)a).runOnUiThread(new i.2(paramJSONObject));
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
    if (Build.VERSION.SDK_INT >= 11) {
      return new i.b(paramActivity).a();
    }
    return false;
  }
  
  public static boolean a(y.b paramB)
  {
    bc localBc = null;
    if (x != null) {
      localBc = x.i();
    }
    if (localBc == null) {}
    while (!localBc.a(paramB)) {
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
    if (Build.VERSION.SDK_INT >= 9)
    {
      paramActivity.setRequestedOrientation(7);
      return;
    }
    paramActivity.setRequestedOrientation(1);
  }
  
  public static void b(Activity paramActivity, String paramString)
  {
    long l1 = F();
    int i1 = i.f.c.ordinal();
    if (l1 - J[i1] >= 2000L)
    {
      e("Sending State Change: " + paramString);
      J[i1] = l1;
      f.a(paramActivity, paramString);
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
    a(i.c.b, "Core - Destroy");
    v = 0L;
    y = new b();
    if (Q != null)
    {
      Q.a();
      Q = null;
    }
    x.c();
    x = null;
    M = null;
    N = null;
    int i1 = 0;
    while (i1 < J.length)
    {
      J[i1] = 0L;
      i1 += 1;
    }
  }
  
  public static void c(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT >= 9)
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
      z = paramString;
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
    if (M != null) {
      return M;
    }
    if (C)
    {
      if ((L != null) && (L.length() > 0))
      {
        if (s != null) {
          return L + "?ssF=default&ssCI=" + s;
        }
        return L;
      }
      if (s != null) {
        return K + "/tap/ad/Ad" + "?ssF=default&ssCI=" + s;
      }
      return K + "/tap/ad/Ad";
    }
    if ((L != null) && (L.length() > 0))
    {
      if (s != null) {
        return L + "?ssF=default&ssCI=" + s;
      }
      return L;
    }
    if (s != null) {
      return K + "/tap/ad/Ad" + "?ssF=default&ssCI=" + s;
    }
    return K + "/tap/ad/Ad";
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
    a(i.c.a, paramString, null);
  }
  
  public static boolean e()
  {
    return E;
  }
  
  public static String f()
  {
    if (C) {
      return "http://l0.videohub.tv/ssframework/tap/avail/Avail";
    }
    return "http://l0.videohub/ssframework/tap/avail/Avail";
  }
  
  public static void f(String paramString)
  {
    Log.v("tremorQA", paramString);
  }
  
  public static h g()
  {
    return O;
  }
  
  public static void g(String paramString)
  {
    Object localObject = null;
    if (paramString != null)
    {
      for (;;)
      {
        JSONObject localJSONObject1;
        JSONObject localJSONObject2;
        try
        {
          int i1 = paramString.length();
          if (i1 <= 0) {
            break label530;
          }
        }
        finally {}
        try
        {
          e("pre config response new :" + paramString);
          localJSONObject1 = new JSONObject(paramString);
          if (!localJSONObject1.has("urls")) {
            continue;
          }
          localJSONObject2 = localJSONObject1.getJSONObject("urls");
          if (localJSONObject2.has("ads")) {
            localObject = localJSONObject2.getString("ads");
          }
          if ((localObject == null) || (((String)localObject).length() <= 0) || (!URLUtil.isValidUrl((String)localObject))) {
            break label524;
          }
          L = (String)localObject;
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
          if (!localJSONObject1.has("moat_config")) {
            break label515;
          }
          localObject = localJSONObject1.getJSONObject("moat_config");
          if (!((JSONObject)localObject).has("enable")) {
            break label476;
          }
          k = ((JSONObject)localObject).getBoolean("enable");
          e("Moat in Preconfig: Enabled: " + k);
        }
        catch (Exception paramString)
        {
          e("Exception pre config JSON processing " + paramString);
          O();
          continue;
          e("Moat in Preconfig: No 'moat_config' field at all");
          continue;
          O();
        }
      }
      e("Moat in Preconfig: Enable moat after preconfig: " + k);
      localObject = a.getSharedPreferences("PreConfig", 0).edit();
      ((SharedPreferences.Editor)localObject).putString("preConfigJSON", paramString);
      ((SharedPreferences.Editor)localObject).commit();
    }
    for (;;)
    {
      e("FlowType: " + d);
      x = new w(a, d());
      l = true;
      d.a = 1;
      if (p.size() <= 0) {
        break label542;
      }
      paramString = p.iterator();
      while (paramString.hasNext())
      {
        localObject = (i.d)paramString.next();
        a(((i.d)localObject).a, ((i.d)localObject).b);
      }
      label476:
      e("Moat in Preconfig: No 'enable' parameter in 'moat_config' field");
      break;
      label515:
      label524:
      label530:
      O();
    }
    p.clear();
    label542:
    if (o != null)
    {
      e("Delayed set Settings");
      a(o);
      o = null;
    }
    if ((d == 0) && (m))
    {
      m = false;
      B().a(false);
    }
    if ((d == 2) && (n))
    {
      n = false;
      B().a(false);
    }
    bh.a(bh.a.a, new Object[0]);
  }
  
  public static be h()
  {
    return P;
  }
  
  public static String i()
  {
    if (C) {
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
  
  public static bf l()
  {
    return I;
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
      Object localObject1 = a.getPackageName();
      localObject1 = a.getPackageManager().getPackageInfo((String)localObject1, 0).applicationInfo;
      Object localObject2 = ((ActivityManager)a.getSystemService("activity")).getRunningAppProcesses();
      if (localObject2 == null) {
        return "unknown";
      }
      localObject2 = ((List)localObject2).iterator();
      PackageManager localPackageManager = a.getPackageManager();
      while (((Iterator)localObject2).hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject2).next();
        if (localRunningAppProcessInfo.processName.compareTo(((ApplicationInfo)localObject1).processName) == 0)
        {
          localObject1 = localPackageManager.getApplicationLabel(localPackageManager.getApplicationInfo(localRunningAppProcessInfo.processName, 128)).toString();
          return localObject1;
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
    return w[0];
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
    for (H = "";; H = (String)localObject)
    {
      return H;
      e("DeviceID: Stored ID found");
      break;
    }
  }
  
  public static int r()
  {
    return Build.VERSION.SDK_INT;
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
      String[] arrayOfString = w;
      return arrayOfString;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public static b v()
  {
    try
    {
      b localB = y;
      return localB;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public static boolean w()
  {
    return D;
  }
  
  public static Context x()
  {
    return a;
  }
  
  public static String y()
  {
    String str1 = Build.BRAND;
    String str2 = Build.MODEL;
    return "TransperaSDK v" + z() + " : " + str1 + " : " + str2 + " : Android OS : " + Build.VERSION.RELEASE;
  }
  
  public static String z()
  {
    return bj.a;
  }
}
