package com.appodeal.ads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import android.util.Pair;
import com.appodeal.ads.a.a;
import com.appodeal.ads.a.a.a;
import com.appodeal.ads.a.c;
import com.appodeal.ads.a.c.a;
import com.appodeal.ads.a.e;
import com.appodeal.ads.a.e.a;
import com.appodeal.ads.a.e.b;
import com.appodeal.ads.a.e.c;
import com.appodeal.ads.a.i.a;
import com.appodeal.ads.a.k.a;
import com.appodeal.ads.a.k.b;
import com.appodeal.ads.a.o;
import com.appodeal.ads.a.o.a;
import com.appodeal.ads.a.q;
import com.appodeal.ads.a.q.a;
import com.appodeal.ads.a.s.a;
import com.appodeal.ads.a.u.d;
import com.appodeal.ads.a.w;
import com.appodeal.ads.a.w.a;
import com.appodeal.ads.a.y.a;
import com.appodeal.ads.utils.af;
import com.appodeal.ads.utils.ai;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public class bm
{
  @VisibleForTesting
  static t a = new u();
  
  @SuppressLint({"MissingPermission"})
  static e.b a(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    int i = paramContext.getType();
    if (i != 9)
    {
      switch (i)
      {
      default: 
        return e.b.a;
      case 1: 
        return e.b.c;
      }
      i = paramContext.getSubtype();
      if (i != 0)
      {
        if (i != 4)
        {
          if (i != 16) {
            return e.b.g;
          }
          return e.b.e;
        }
        return e.b.f;
      }
      return e.b.d;
    }
    return e.b.b;
  }
  
  static com.appodeal.ads.a.i a(@NonNull Context paramContext, @Nullable i paramI, double paramDouble)
  {
    i.a localA = com.appodeal.ads.a.i.d();
    localA.a((float)paramDouble);
    if ((paramI != null) && (paramI.r() != null)) {
      localA.a(paramI.r().toString());
    }
    if (a.j())
    {
      Object localObject = paramContext.getPackageManager();
      paramI = Pattern.compile("^?(?:com\\.android|com\\.google|com\\.sec|com\\.samsung|com\\.sonyericsson|com\\.sonymobile|com\\.motorola|com\\.htc).*$");
      localObject = ((PackageManager)localObject).getInstalledApplications(0);
      if (localObject != null)
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          String str = ((ApplicationInfo)((Iterator)localObject).next()).packageName;
          if ((!paramI.matcher(str).matches()) && (!str.equals("android"))) {
            localA.b(str);
          }
        }
      }
    }
    paramContext = ai.a(paramContext);
    if (paramContext != null)
    {
      paramContext = paramContext.toString();
      if (paramContext != null) {
        localA.c(paramContext);
      }
    }
    return (com.appodeal.ads.a.i)localA.build();
  }
  
  static o a()
  {
    return (o)o.a().a(v.h).build();
  }
  
  static q.a a(@NonNull Context paramContext, @Nullable k paramK, @Nullable i paramI, double paramDouble)
  {
    q.a localA = q.m();
    localA.a(c(paramContext));
    localA.a(a(paramContext, paramK));
    localA.a(b(paramContext));
    localA.a(e(paramContext));
    localA.a(a());
    localA.a(d(paramContext));
    localA.a(a(paramContext, paramI, paramDouble));
    localA.a(System.currentTimeMillis());
    if (paramK != null)
    {
      paramContext = paramK.C();
      if (paramContext != null) {
        localA.a(paramContext);
      }
      paramContext = paramK.c();
      if (paramContext != null) {
        localA.b(paramContext);
      }
    }
    return localA;
  }
  
  static com.appodeal.ads.a.s a(@NonNull Context paramContext, @Nullable k paramK)
  {
    Object localObject1 = paramContext.getSharedPreferences("appodeal", 0);
    paramContext = com.appodeal.ads.a.s.e();
    paramContext.a(y.b);
    Object localObject2 = ExtraData.a().toString();
    if ((ExtraData.a().length() != 0) && (localObject2 != null)) {
      paramContext.a((String)localObject2);
    }
    localObject2 = bl.b();
    if (localObject2 != null) {
      paramContext.b(((JSONObject)localObject2).toString());
    }
    paramContext.a(com.appodeal.ads.utils.i.a((SharedPreferences)localObject1));
    localObject1 = Appodeal.e().a();
    if (localObject1 != null) {
      paramContext.c((String)localObject1);
    }
    paramContext.b(com.appodeal.ads.utils.i.b());
    if (paramK != null)
    {
      paramK = paramK.d();
      if (paramK != null) {
        paramContext.a(paramK.intValue());
      }
    }
    if (a.k()) {
      paramContext.a(b());
    }
    return (com.appodeal.ads.a.s)paramContext.build();
  }
  
  static void a(k paramK, i paramI)
  {
    u.d localD = paramK.Q();
    new s.c(s.d.a).a(localD.build()).a(paramK).a(paramI).b();
  }
  
  static a b()
  {
    a.a localA = a.a();
    localA.a(com.appodeal.ads.utils.s.a());
    localA.b(com.appodeal.ads.utils.s.b());
    localA.c(com.appodeal.ads.utils.s.c());
    localA.d(com.appodeal.ads.utils.s.a("interstitial"));
    localA.e(com.appodeal.ads.utils.s.b("interstitial"));
    localA.f(com.appodeal.ads.utils.s.a("video"));
    localA.g(com.appodeal.ads.utils.s.b("video"));
    localA.h(com.appodeal.ads.utils.s.c("video"));
    localA.j(com.appodeal.ads.utils.s.b("rewarded_video"));
    localA.k(com.appodeal.ads.utils.s.c("rewarded_video"));
    localA.i(com.appodeal.ads.utils.s.a("rewarded_video"));
    localA.l(com.appodeal.ads.utils.s.a("banner"));
    localA.m(com.appodeal.ads.utils.s.b("banner"));
    localA.n(com.appodeal.ads.utils.s.a("mrec"));
    localA.o(com.appodeal.ads.utils.s.b("mrec"));
    localA.p(com.appodeal.ads.utils.s.a("native"));
    localA.q(com.appodeal.ads.utils.s.b("native"));
    return (a)localA.build();
  }
  
  static e b(@NonNull Context paramContext)
  {
    e.a localA = e.j();
    Object localObject = bt.z(paramContext);
    if (!TextUtils.isEmpty((CharSequence)localObject)) {
      localA.a((String)localObject);
    }
    if (Build.VERSION.RELEASE != null) {
      localA.b(Build.VERSION.RELEASE);
    }
    localObject = bt.f(paramContext);
    localA.c("Android");
    if (((Pair)localObject).first != null) {
      localA.a(((Integer)((Pair)localObject).first).intValue());
    }
    if (((Pair)localObject).second != null) {
      localA.b(((Integer)((Pair)localObject).second).intValue());
    }
    localA.a(bt.i(paramContext));
    if (bt.m(paramContext)) {
      localObject = e.c.c;
    } else {
      localObject = e.c.b;
    }
    localA.a((e.c)localObject);
    if ((a.e()) && (Build.MANUFACTURER != null)) {
      localA.d(Build.MANUFACTURER);
    }
    if (a.f())
    {
      localObject = String.format("%s %s", new Object[] { Build.MANUFACTURER, Build.MODEL });
      if (localObject != null) {
        localA.e((String)localObject);
      }
    }
    if (a.d()) {
      localA.a(a(paramContext));
    }
    if (a.g())
    {
      localObject = bt.c(paramContext);
      if (localObject != null) {
        localA.g((String)localObject);
      }
    }
    if (a.h())
    {
      localObject = Locale.getDefault().toString();
      if (localObject != null) {
        localA.h((String)localObject);
      }
    }
    localA.a(bt.a());
    localObject = bt.s(paramContext);
    if (localObject != null) {
      localA.f((String)localObject);
    }
    localA.c((int)bt.k(paramContext));
    localA.i(a.l());
    localA.d(bl.g() ^ true);
    localA.b(bl.j());
    return (e)localA.build();
  }
  
  static c c(@NonNull Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("appodeal", 0);
    c.a localA = c.i();
    String str = paramContext.getPackageName();
    if (str != null) {
      localA.a(str);
    }
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = localPackageManager.getPackageInfo(str, 0);
    if (paramContext.versionName != null) {
      localA.b(paramContext.versionName);
    }
    localA.a(paramContext.firstInstallTime / 1000L);
    str = localPackageManager.getInstallerPackageName(str);
    if (str != null) {
      localA.d(str);
    }
    localA.a(bt.a(new String[] { "android.support.multidex.MultiDex" }));
    str = localSharedPreferences.getString("appKey", null);
    if (str != null) {
      localA.e(str);
    }
    localA.f("2.5.0");
    localA.a(paramContext.versionCode);
    localA.b(com.appodeal.ads.utils.i.b(localSharedPreferences));
    if (Appodeal.k != null) {
      localA.g(Appodeal.k);
    }
    if (Appodeal.m != null) {
      localA.h(Appodeal.m);
    }
    if (Appodeal.l != null) {
      localA.c(Appodeal.l);
    }
    return (c)localA.build();
  }
  
  static com.appodeal.ads.a.k d(@NonNull Context paramContext)
  {
    k.a localA = com.appodeal.ads.a.k.a();
    localA.a((int)TimeUnit.MILLISECONDS.toMinutes(TimeZone.getDefault().getOffset(System.currentTimeMillis())));
    localA.a(System.currentTimeMillis() / 1000L);
    paramContext = g(paramContext);
    if (a.a()) {
      localA.a((k.b)paramContext.first);
    }
    if (paramContext.second != null)
    {
      if (a.b()) {
        localA.a(((float[])paramContext.second)[0]);
      }
      if (a.c()) {
        localA.b(((float[])paramContext.second)[1]);
      }
    }
    return (com.appodeal.ads.a.k)localA.build();
  }
  
  static w e(@NonNull Context paramContext)
  {
    w.a localA = w.b();
    localA.a(bl.f());
    if (a.i()) {
      localA.a(f(paramContext));
    }
    return (w)localA.build();
  }
  
  static com.appodeal.ads.a.y f(@NonNull Context paramContext)
  {
    y.a localA = com.appodeal.ads.a.y.c();
    paramContext = bl.b(paramContext);
    if (paramContext != null)
    {
      String str = paramContext.getUserId();
      if (str != null) {
        localA.a(str);
      }
      str = bt.a(paramContext);
      if (str != null) {
        localA.b(str);
      }
      paramContext = paramContext.getAge();
      if (paramContext != null) {
        localA.a(paramContext.intValue());
      }
    }
    return (com.appodeal.ads.a.y)localA.build();
  }
  
  private static Pair<k.b, float[]> g(Context paramContext)
  {
    paramContext = bt.e(paramContext);
    if (paramContext != null) {
      return new Pair(k.b.b, new float[] { Double.valueOf(paramContext.getLatitude()).floatValue(), Double.valueOf(paramContext.getLongitude()).floatValue() });
    }
    return new Pair(k.b.a, null);
  }
}
