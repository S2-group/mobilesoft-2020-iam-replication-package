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
import com.chartboost.sdk.Libraries.a;
import com.chartboost.sdk.Libraries.b;
import com.chartboost.sdk.Libraries.g.a;
import com.chartboost.sdk.Libraries.i;
import com.chartboost.sdk.Libraries.i.k;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.impl.l.a;
import com.chartboost.sdk.impl.m;
import com.chartboost.sdk.impl.m.c;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public final class e
{
  private static boolean A = true;
  private static boolean B = true;
  private static boolean C = true;
  private static boolean D = true;
  private static boolean E = true;
  public static f.a a;
  private static final String b = "e";
  private static String c;
  private static String d;
  private static d e;
  private static boolean f = false;
  private static boolean g = false;
  private static boolean h = false;
  private static Chartboost.CBFramework i;
  private static String j;
  private static String k;
  private static String l;
  private static Chartboost.CBMediation m;
  private static String n;
  private static String o;
  private static SharedPreferences p;
  private static boolean q = true;
  private static volatile boolean r = false;
  private static Context s;
  private static Application t;
  private static boolean u = false;
  private static boolean v = true;
  private static boolean w = false;
  private static boolean x = true;
  private static float y = 250.0F;
  private static boolean z = false;
  
  private e() {}
  
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
      localObject = g.a.k((String)localObject);
      if ((((g.a)localObject).c()) && (((g.a)localObject).a("enabled").c()))
      {
        if (Build.VERSION.SDK_INT >= 11) {
          return Boolean.valueOf(((g.a)localObject).j("enabled"));
        }
        return Boolean.valueOf(false);
      }
    }
    return Boolean.valueOf(false);
  }
  
  private static SharedPreferences G()
  {
    if (p == null) {
      p = a.a();
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
    paramCBMediation = new StringBuilder();
    paramCBMediation.append(m);
    paramCBMediation.append(" ");
    paramCBMediation.append(n);
    l = paramCBMediation.toString();
  }
  
  public static void a(CBLogging.Level paramLevel)
  {
    t();
    CBLogging.a = paramLevel;
  }
  
  public static void a(g.a paramA)
  {
    try
    {
      if (paramA.c())
      {
        paramA = paramA.f();
        if (paramA != null)
        {
          SharedPreferences.Editor localEditor = G().edit();
          Iterator localIterator = paramA.keySet().iterator();
          while (localIterator.hasNext())
          {
            String str = (String)localIterator.next();
            Object localObject = paramA.get(str);
            if ((localObject instanceof String)) {
              localEditor.putString(str, (String)localObject);
            } else if ((localObject instanceof Integer)) {
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
          return;
        }
      }
    }
    catch (Exception paramA)
    {
      paramA.printStackTrace();
    }
  }
  
  public static void a(d paramD)
  {
    if (!t()) {
      return;
    }
    e = paramD;
  }
  
  public static void a(a paramA)
  {
    m localM = new m("/api/config");
    localM.a(false);
    localM.b(false);
    localM.a(l.a.c);
    localM.a(i.a(new i.k[] { i.a("status", b.a) }));
    localM.a(new m.c()
    {
      public void a(g.a paramAnonymousA, m paramAnonymousM)
      {
        if (paramAnonymousA.c())
        {
          paramAnonymousA = paramAnonymousA.a("response");
          if (paramAnonymousA.c()) {
            e.a(paramAnonymousA);
          }
        }
        if (this.a != null) {
          this.a.a();
        }
      }
      
      public void a(g.a paramAnonymousA, m paramAnonymousM, CBError paramAnonymousCBError)
      {
        if (this.a != null) {
          this.a.a();
        }
      }
    });
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
    if (paramActivity == null) {}
    try
    {
      throw new RuntimeException("Invalid activity context passed during intitalization");
    }
    catch (Exception paramActivity)
    {
      int i1;
      int i2;
      int i3;
      int i4;
      int i5;
      for (;;) {}
    }
    if (Build.VERSION.SDK_INT >= 23)
    {
      i1 = paramActivity.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
      i2 = paramActivity.checkSelfPermission("android.permission.ACCESS_NETWORK_STATE");
      i3 = paramActivity.checkSelfPermission("android.permission.INTERNET");
      i4 = paramActivity.checkSelfPermission("android.permission.READ_PHONE_STATE");
      i5 = paramActivity.checkSelfPermission("android.permission.ACCESS_WIFI_STATE");
    }
    else
    {
      i1 = paramActivity.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
      i2 = paramActivity.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE");
      i3 = paramActivity.checkCallingOrSelfPermission("android.permission.INTERNET");
      i4 = paramActivity.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE");
      i5 = paramActivity.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE");
    }
    if (i1 != 0) {
      A = true;
    } else {
      A = false;
    }
    if (i3 != 0)
    {
      B = true;
      throw new RuntimeException("Please add the permission : android.permission.INTERNET in your android manifest.xml");
    }
    B = false;
    if (i2 != 0)
    {
      C = true;
      throw new RuntimeException("Please add the permission :  android.permission.ACCESS_NETWORK_STATE in your android manifest.xml");
    }
    C = false;
    if (i4 == 0) {
      D = false;
    } else {
      D = true;
    }
    if (i5 == 0)
    {
      E = false;
      return true;
    }
    E = true;
    return true;
    paramActivity.printStackTrace();
    return false;
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
          if (arrayOfActivityInfo[i1].name.contains("com.chartboost.sdk.CBImpressionActivity")) {
            z = true;
          }
        }
        else
        {
          if (!z) {
            throw new RuntimeException("Please add             <activity android:name=\"com.chartboost.sdk.CBImpressionActivity\"\n                  android:excludeFromRecents=\"true\"\n                  android:theme=\"@android:style/Theme.Translucent.NoTitleBar.Fullscreen\"\n                  android:configChanges=\"keyboardHidden|orientation|screenSize\"/> in your android manifest.xml");
          }
          return true;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return false;
      }
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
      localObject = g.a.k((String)localObject);
      if ((((g.a)localObject).c()) && (!((g.a)localObject).a(paramString).b())) {
        return Float.valueOf(((g.a)localObject).g(paramString));
      }
    }
    return null;
  }
  
  public static String e()
  {
    if (!t()) {
      return "";
    }
    c = G().getString("appId", c);
    return c;
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
    d = G().getString("appSignature", d);
    return d;
  }
  
  public static void f(boolean paramBoolean)
  {
    w = paramBoolean;
  }
  
  public static d g()
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
    boolean bool = t();
    Object localObject2 = null;
    if (!bool) {
      return null;
    }
    Object localObject3 = G().getString("trackingLevels", "");
    Object localObject1 = localObject2;
    if (!TextUtils.isEmpty((CharSequence)localObject3))
    {
      localObject3 = g.a.k((String)localObject3);
      localObject1 = localObject2;
      if (((g.a)localObject3).c()) {
        localObject1 = ((g.a)localObject3).e();
      }
    }
    return localObject1;
  }
  
  public static boolean l()
  {
    t();
    return G().getBoolean("retriesEnabled", true);
  }
  
  public static boolean m()
  {
    if (!t()) {
      return false;
    }
    JSONObject localJSONObject = k();
    return (localJSONObject != null) && ((localJSONObject.optBoolean("debug")) || (localJSONObject.optBoolean("session")) || (localJSONObject.optBoolean("system")) || (localJSONObject.optBoolean("user")));
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
    if (Chartboost.c == null) {
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
      if (TextUtils.isEmpty(c)) {
        throw new Exception("SDK Initialization error. AppId is missing");
      }
      if (TextUtils.isEmpty(d)) {
        throw new Exception("SDK Initialization error. AppSignature is missing");
      }
      return true;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
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
  
  public static abstract interface a
  {
    public abstract void a();
  }
}
