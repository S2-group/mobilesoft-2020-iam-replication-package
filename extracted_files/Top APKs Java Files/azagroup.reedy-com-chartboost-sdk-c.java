package com.chartboost.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBLogging.Level;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.e.a;
import com.chartboost.sdk.Libraries.g;
import com.chartboost.sdk.Libraries.g.k;
import com.chartboost.sdk.impl.az;
import com.chartboost.sdk.impl.l.a;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public final class c
{
  private static boolean A = true;
  private static boolean B = true;
  private static boolean C = true;
  private static boolean D = true;
  private static boolean E = true;
  public static d.a a;
  private static final String b = c.class.getSimpleName();
  private static String c;
  private static String d;
  private static a e;
  private static boolean f = false;
  private static boolean g = false;
  private static boolean h = false;
  private static Chartboost.CBFramework i = null;
  private static String j = null;
  private static String k = null;
  private static String l = null;
  private static Chartboost.CBMediation m = null;
  private static String n = null;
  private static String o = null;
  private static SharedPreferences p = null;
  private static boolean q = true;
  private static volatile boolean r = false;
  private static Context s = null;
  private static Application t = null;
  private static boolean u = false;
  private static boolean v = true;
  private static boolean w = false;
  private static boolean x = true;
  private static float y = 250.0F;
  private static boolean z = false;
  
  private c() {}
  
  public static String A()
  {
    if (F().booleanValue()) {
      return "/webview/v1/interstitial/get";
    }
    return "/interstitial/get";
  }
  
  public static String B()
  {
    if (F().booleanValue()) {
      return "/webview/v1/reward/get";
    }
    return "/reward/get";
  }
  
  public static String C()
  {
    if (F().booleanValue()) {
      return "/webview/v1/prefetch";
    }
    return "/api/video-prefetch";
  }
  
  public static int D()
  {
    Float localFloat = e("cacheTTLs");
    if ((localFloat != null) && (localFloat.floatValue() >= 604800.0F)) {
      return (int)TimeUnit.SECONDS.toDays(localFloat.longValue());
    }
    return 7;
  }
  
  public static int E()
  {
    Float localFloat = e("cacheMaxUnits");
    if ((localFloat != null) && (localFloat.floatValue() > 0.0F)) {
      return localFloat.intValue();
    }
    return 10;
  }
  
  public static Boolean F()
  {
    Object localObject = G().getString("webview", "");
    if (!TextUtils.isEmpty((CharSequence)localObject))
    {
      localObject = e.a.k((String)localObject);
      if ((((e.a)localObject).c()) && (((e.a)localObject).a("enabled").c()))
      {
        if (Build.VERSION.SDK_INT >= 11) {
          return Boolean.valueOf(((e.a)localObject).j("enabled"));
        }
        return Boolean.valueOf(false);
      }
    }
    return Boolean.valueOf(false);
  }
  
  private static SharedPreferences G()
  {
    if (p == null) {
      p = CBUtility.a();
    }
    return p;
  }
  
  public static void a(Application paramApplication)
  {
    t = paramApplication;
  }
  
  public static void a(Context paramContext)
  {
    s = paramContext;
  }
  
  public static void a(Chartboost.CBFramework paramCBFramework)
  {
    if (!t()) {
      return;
    }
    if (paramCBFramework == null)
    {
      CBLogging.b(b, "Pass a valid CBFramework enum value");
      return;
    }
    i = paramCBFramework;
  }
  
  public static void a(Chartboost.CBFramework paramCBFramework, String paramString)
  {
    a(paramCBFramework);
    j = paramString;
  }
  
  public static void a(Chartboost.CBMediation paramCBMediation, String paramString)
  {
    if (!t()) {
      return;
    }
    m = paramCBMediation;
    n = paramString;
    l = m + " " + n;
  }
  
  public static void a(CBLogging.Level paramLevel)
  {
    t();
    CBLogging.a = paramLevel;
  }
  
  public static void a(e.a paramA)
  {
    SharedPreferences.Editor localEditor;
    for (;;)
    {
      String str;
      Object localObject;
      try
      {
        if (paramA.c())
        {
          paramA = paramA.f();
          if (paramA != null)
          {
            localEditor = G().edit();
            Iterator localIterator = paramA.keySet().iterator();
            if (!localIterator.hasNext()) {
              break;
            }
            str = (String)localIterator.next();
            localObject = paramA.get(str);
            if (!(localObject instanceof String)) {
              break label95;
            }
            localEditor.putString(str, (String)localObject);
            continue;
          }
        }
        return;
      }
      catch (Exception paramA)
      {
        paramA.printStackTrace();
      }
      label95:
      if ((localObject instanceof Integer)) {
        localEditor.putInt(str, ((Integer)localObject).intValue());
      } else if ((localObject instanceof Float)) {
        localEditor.putFloat(str, ((Float)localObject).floatValue());
      } else if ((localObject instanceof Long)) {
        localEditor.putLong(str, ((Long)localObject).longValue());
      } else if ((localObject instanceof Boolean)) {
        localEditor.putBoolean(str, ((Boolean)localObject).booleanValue());
      } else if (localObject != null) {
        localEditor.putString(str, localObject.toString());
      }
    }
    localEditor.commit();
  }
  
  public static void a(a paramA)
  {
    if (!t()) {
      return;
    }
    e = paramA;
  }
  
  public static void a(c.a paramA)
  {
    az localAz = new az("/api/config");
    localAz.a(false);
    localAz.b(false);
    localAz.a(l.a.c);
    localAz.a(g.a(new g.k[] { g.a("status", com.chartboost.sdk.Libraries.a.a) }));
    localAz.a(new c.1(paramA));
  }
  
  public static void a(String paramString)
  {
    if (!t()) {
      return;
    }
    if (i == null)
    {
      CBLogging.b(b, "Set a valid CBFramework first");
      return;
    }
    if (TextUtils.isEmpty(paramString))
    {
      CBLogging.b(b, "Invalid Version String");
      return;
    }
    k = paramString;
  }
  
  public static void a(boolean paramBoolean)
  {
    q = paramBoolean;
  }
  
  public static boolean a()
  {
    return A;
  }
  
  public static boolean a(Activity paramActivity)
  {
    if (paramActivity == null) {
      try
      {
        throw new Exception("Invalid activity context: Host Activity object is null, Please send a valid activity object");
      }
      catch (Exception paramActivity)
      {
        paramActivity.printStackTrace();
        return false;
      }
    }
    return true;
  }
  
  public static Chartboost.CBFramework b()
  {
    t();
    if (i == null) {
      return null;
    }
    return i;
  }
  
  public static void b(String paramString)
  {
    c = paramString;
    G().edit().putString("appId", paramString).commit();
  }
  
  protected static void b(boolean paramBoolean)
  {
    r = paramBoolean;
  }
  
  public static boolean b(Activity paramActivity)
  {
    if (paramActivity == null) {
      try
      {
        throw new RuntimeException("Invalid activity context passed during intitalization");
      }
      catch (Exception paramActivity)
      {
        paramActivity.printStackTrace();
        return false;
      }
    }
    int i4;
    int i3;
    int i2;
    int i1;
    int i5;
    if (Build.VERSION.SDK_INT >= 23)
    {
      i4 = paramActivity.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
      i3 = paramActivity.checkSelfPermission("android.permission.ACCESS_NETWORK_STATE");
      i2 = paramActivity.checkSelfPermission("android.permission.INTERNET");
      i1 = paramActivity.checkSelfPermission("android.permission.READ_PHONE_STATE");
      i5 = paramActivity.checkSelfPermission("android.permission.ACCESS_WIFI_STATE");
      if (i4 == 0) {
        break label145;
      }
    }
    label145:
    for (A = true;; A = false)
    {
      if (i2 == 0) {
        break label152;
      }
      B = true;
      throw new RuntimeException("Please add the permission : android.permission.INTERNET in your android manifest.xml");
      i4 = paramActivity.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
      i3 = paramActivity.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE");
      i2 = paramActivity.checkCallingOrSelfPermission("android.permission.INTERNET");
      i1 = paramActivity.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE");
      i5 = paramActivity.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE");
      break;
    }
    label152:
    B = false;
    if (i3 != 0)
    {
      C = true;
      throw new RuntimeException("Please add the permission :  android.permission.ACCESS_NETWORK_STATE in your android manifest.xml");
    }
    C = false;
    if (i1 == 0) {}
    for (D = false; i5 == 0; D = true)
    {
      E = false;
      return true;
    }
    E = true;
    return true;
  }
  
  public static boolean b(Context paramContext)
  {
    for (;;)
    {
      int i1;
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledPackages(1).iterator();
        if (paramContext.hasNext())
        {
          ActivityInfo[] arrayOfActivityInfo = ((PackageInfo)paramContext.next()).activities;
          if (arrayOfActivityInfo == null) {
            continue;
          }
          int i2 = arrayOfActivityInfo.length;
          i1 = 0;
          if (i1 >= i2) {
            continue;
          }
          if (!arrayOfActivityInfo[i1].name.contains("com.chartboost.sdk.CBImpressionActivity")) {
            break label98;
          }
          z = true;
          break label98;
        }
        if (!z) {
          throw new RuntimeException("Please add             <activity android:name=\"com.chartboost.sdk.CBImpressionActivity\"\n                  android:excludeFromRecents=\"true\"\n                  android:theme=\"@android:style/Theme.Translucent.NoTitleBar.Fullscreen\"\n                  android:configChanges=\"keyboardHidden|orientation|screenSize\"/> in your android manifest.xml");
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return false;
      }
      return true;
      label98:
      i1 += 1;
    }
  }
  
  public static String c()
  {
    return String.format("%s %s", new Object[] { i, j });
  }
  
  public static void c(String paramString)
  {
    d = paramString;
    G().edit().putString("appSignature", paramString).commit();
  }
  
  public static void c(boolean paramBoolean)
  {
    if (a != null) {
      a.a(paramBoolean);
    }
  }
  
  public static String d()
  {
    t();
    return k;
  }
  
  public static void d(String paramString)
  {
    if (!t()) {
      return;
    }
    o = paramString;
  }
  
  protected static void d(boolean paramBoolean)
  {
    u = paramBoolean;
  }
  
  private static Float e(String paramString)
  {
    Object localObject = G().getString("webview", "");
    if (!TextUtils.isEmpty((CharSequence)localObject))
    {
      localObject = e.a.k((String)localObject);
      if ((((e.a)localObject).c()) && (!((e.a)localObject).a(paramString).b())) {
        return Float.valueOf(((e.a)localObject).g(paramString));
      }
    }
    return null;
  }
  
  public static String e()
  {
    if (!t()) {
      return "";
    }
    String str = G().getString("appId", c);
    c = str;
    return str;
  }
  
  public static void e(boolean paramBoolean)
  {
    v = paramBoolean;
  }
  
  public static String f()
  {
    if (!t()) {
      return "";
    }
    String str = G().getString("appSignature", d);
    d = str;
    return str;
  }
  
  public static void f(boolean paramBoolean)
  {
    w = paramBoolean;
  }
  
  public static a g()
  {
    if (!t()) {
      return null;
    }
    return e;
  }
  
  public static void g(boolean paramBoolean)
  {
    x = paramBoolean;
  }
  
  public static boolean h()
  {
    return true;
  }
  
  public static boolean i()
  {
    if (!t()) {
      return false;
    }
    return h;
  }
  
  public static boolean j()
  {
    return q;
  }
  
  public static JSONObject k()
  {
    if (!t()) {}
    Object localObject;
    do
    {
      do
      {
        return null;
        localObject = G().getString("trackingLevels", "");
      } while (TextUtils.isEmpty((CharSequence)localObject));
      localObject = e.a.k((String)localObject);
    } while (!((e.a)localObject).c());
    return ((e.a)localObject).e();
  }
  
  public static boolean l()
  {
    t();
    return G().getBoolean("retriesEnabled", true);
  }
  
  public static boolean m()
  {
    if (!t()) {}
    JSONObject localJSONObject;
    do
    {
      return false;
      localJSONObject = k();
    } while ((localJSONObject == null) || ((!localJSONObject.optBoolean("debug")) && (!localJSONObject.optBoolean("session")) && (!localJSONObject.optBoolean("system")) && (!localJSONObject.optBoolean("user"))));
    return true;
  }
  
  public static CBLogging.Level n()
  {
    t();
    return CBLogging.a;
  }
  
  public static String o()
  {
    if (!t()) {
      return "";
    }
    return o;
  }
  
  public static boolean p()
  {
    return r;
  }
  
  public static boolean q()
  {
    return (t()) && (s()) && (r());
  }
  
  public static boolean r()
  {
    if (!p()) {
      try
      {
        throw new Exception("Session not started: Check if Chartboost.onStart() is called, if not the session won't be invoked");
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return false;
      }
    }
    return true;
  }
  
  public static boolean s()
  {
    if (Chartboost.b == null) {
      try
      {
        throw new Exception("Chartboost Weak Activity reference is null");
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return false;
      }
    }
    return true;
  }
  
  public static boolean t()
  {
    try
    {
      if (z() == null) {
        throw new Exception("SDK Initialization error. Activity context seems to be not initialized properly, host activity or application context is being sent as null");
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return false;
    }
    if (TextUtils.isEmpty(c)) {
      throw new Exception("SDK Initialization error. AppId is missing");
    }
    if (TextUtils.isEmpty(d)) {
      throw new Exception("SDK Initialization error. AppSignature is missing");
    }
    return true;
  }
  
  public static boolean u()
  {
    return u;
  }
  
  public static boolean v()
  {
    return v;
  }
  
  public static boolean w()
  {
    return w;
  }
  
  public static boolean x()
  {
    return x;
  }
  
  public static Context y()
  {
    return s;
  }
  
  public static Application z()
  {
    return t;
  }
}
