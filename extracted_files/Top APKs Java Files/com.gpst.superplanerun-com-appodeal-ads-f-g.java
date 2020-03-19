package com.appodeal.ads.f;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.UserSettings;
import com.appodeal.ads.UserSettings.Gender;
import com.appodeal.ads.UserSettings.Relation;
import com.appodeal.ads.az;
import com.appodeal.ads.az.a;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public class g
{
  @VisibleForTesting
  static f a;
  @VisibleForTesting
  static Map<String, Object> b;
  @VisibleForTesting
  static JSONArray c;
  private static int d = -1;
  private static int e = 0;
  private static double k;
  private static double l;
  private static boolean m;
  private final Context f;
  private final UserSettings g;
  private final Double h;
  private final Double i;
  private final boolean j;
  
  g(Context paramContext, double paramDouble1, double paramDouble2, boolean paramBoolean)
  {
    this.g = az.u(paramContext);
    this.f = paramContext;
    this.h = Double.valueOf(paramDouble1);
    this.i = Double.valueOf(paramDouble2);
    this.j = paramBoolean;
  }
  
  public g(Context paramContext, JSONObject paramJSONObject)
  {
    this.g = az.u(paramContext);
    this.f = paramContext;
    this.h = Double.valueOf(paramJSONObject.optDouble("inapp_sum", 0.0D));
    this.i = Double.valueOf(paramJSONObject.optDouble("inapp_sum_all_apps", 0.0D));
    if ((paramJSONObject.has("inapp_sum")) && (this.h.doubleValue() > 0.0D)) {}
    for (boolean bool = true;; bool = false)
    {
      this.j = bool;
      k = this.i.doubleValue();
      l = this.i.doubleValue();
      m = this.j;
      return;
    }
  }
  
  @NonNull
  public static f a()
  {
    if (a == null) {
      a = new f(new JSONObject());
    }
    return a;
  }
  
  private Object a(String paramString)
  {
    if (paramString == null) {}
    for (;;)
    {
      return null;
      try
      {
        if (paramString.equals("country")) {
          return this.g.e();
        }
        if (paramString.equals("app_version")) {
          return new h(this.f.getPackageManager().getPackageInfo(this.f.getPackageName(), 0).versionName);
        }
        if (paramString.equals("app")) {
          return this.f.getSharedPreferences("appodeal", 0).getString("appKey", null);
        }
        if (paramString.equals("sdk_version")) {
          return new h("2.1.3");
        }
        if (paramString.equals("android_version")) {
          return new h(Build.VERSION.RELEASE);
        }
        if (paramString.equals("has_app_installed")) {
          return s();
        }
        if (paramString.equals("session_count")) {
          return Integer.valueOf((int)com.appodeal.ads.utils.e.a(this.f.getSharedPreferences("appodeal", 0)));
        }
        if (paramString.equals("average_session_length")) {
          return r();
        }
        if (paramString.equals("device_model")) {
          return String.format("%s %s", new Object[] { Build.MANUFACTURER, Build.MODEL });
        }
        if (paramString.equals("connection_type")) {
          return l();
        }
        if (paramString.equals("gender")) {
          return n();
        }
        if (paramString.equals("age")) {
          return j();
        }
        if (paramString.equals("relation")) {
          return m();
        }
        if (paramString.equals("interests")) {
          return i();
        }
        if (paramString.equals("bought_inapps")) {
          return q();
        }
        if (paramString.equals("inapp_sum")) {
          return o();
        }
        if (paramString.equals("inapp_sum_all_apps")) {
          return p();
        }
        if (paramString.equals("last_session_time")) {
          return k();
        }
        if (paramString.equals("platform")) {
          return "android";
        }
        if (paramString.equals("device_type"))
        {
          if (az.n(this.f)) {
            return "tablet";
          }
        }
        else
        {
          if ((b != null) && (b.containsKey(paramString))) {
            return b.get(paramString);
          }
          if (paramString.equals("day"))
          {
            e = e();
            return Integer.valueOf(e);
          }
          if (paramString.equals("hour"))
          {
            d = d();
            return Integer.valueOf(d);
          }
          if (!paramString.equals("part_of_audience")) {
            continue;
          }
          int n = g();
          return Integer.valueOf(n);
        }
      }
      catch (Exception paramString)
      {
        Appodeal.a(paramString);
        return null;
      }
    }
    return "phone";
  }
  
  public static void a(@NonNull f paramF)
  {
    a = paramF;
    if ((paramF.d != null) && (paramF.d.a != null))
    {
      Appodeal.a(String.format("Matched segment #%s: %s", new Object[] { Long.valueOf(paramF.c()), paramF.d.a }));
      return;
    }
    Appodeal.a(String.format("Matched segment #%s", new Object[] { Long.valueOf(paramF.c()) }));
  }
  
  public static void a(String paramString, Object paramObject)
  {
    if (b == null) {
      b = new HashMap();
    }
    b.put(paramString, paramObject);
    c();
  }
  
  private boolean a(Integer[] paramArrayOfInteger, Integer paramInteger)
  {
    boolean bool2 = false;
    int i1 = paramArrayOfInteger.length;
    int n = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (n < i1)
      {
        if (paramArrayOfInteger[n].equals(paramInteger)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      n += 1;
    }
  }
  
  private boolean a(String[] paramArrayOfString, String paramString)
  {
    boolean bool2 = false;
    int i1 = paramArrayOfString.length;
    int n = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (n < i1)
      {
        if (paramString.contains(paramArrayOfString[n])) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      n += 1;
    }
  }
  
  static boolean b()
  {
    return a().c() == -1L;
  }
  
  @VisibleForTesting
  static void c()
  {
    if (Appodeal.d != null) {}
    Object localObject2;
    for (Object localObject1 = new g(Appodeal.d, k, l, m);; localObject2 = null) {
      try
      {
        if (c != null)
        {
          localObject1 = ((g)localObject1).a(c);
          if ((localObject1 != null) && (((f)localObject1).c() != a.c()))
          {
            ((f)localObject1).a();
            a((f)localObject1);
            h();
          }
          return;
        }
      }
      catch (Exception localException)
      {
        Appodeal.a(localException);
        return;
      }
    }
  }
  
  @VisibleForTesting
  static int d()
  {
    return Calendar.getInstance().get(11);
  }
  
  @VisibleForTesting
  static int e()
  {
    int n = 7;
    switch (Calendar.getInstance().get(7))
    {
    default: 
      n = 0;
    case 1: 
      return n;
    case 2: 
      return 1;
    case 3: 
      return 2;
    case 4: 
      return 3;
    case 5: 
      return 4;
    case 6: 
      return 5;
    }
    return 6;
  }
  
  public static void f()
  {
    if ((c != null) && ((e() != e) || (d() != d))) {
      c();
    }
  }
  
  private boolean g(e paramE, Object paramObject)
  {
    if (paramObject == null) {}
    do
    {
      do
      {
        return false;
        if (paramE.d == e.a.h)
        {
          paramE.d = e.a(paramObject);
          paramE.a();
        }
      } while (paramE.d == e.a.h);
      switch (1.b[paramE.b.ordinal()])
      {
      default: 
        return false;
      case 1: 
        return f(paramE, paramObject);
      case 2: 
        return e(paramE, paramObject);
      }
    } while (e(paramE, paramObject));
    return true;
    return c(paramE, paramObject);
    return d(paramE, paramObject);
    return b(paramE, paramObject);
    return a(paramE, paramObject);
  }
  
  private static void h()
  {
    com.appodeal.ads.ab.k = true;
    com.appodeal.ads.q.l = true;
    com.appodeal.ads.ar.h = true;
    com.appodeal.ads.Native.j = true;
    com.appodeal.ads.i.k = true;
    com.appodeal.ads.av.j = true;
  }
  
  private String i()
  {
    return this.g.b();
  }
  
  private Integer j()
  {
    return this.g.getAge();
  }
  
  private Integer k()
  {
    long l1 = com.appodeal.ads.utils.e.c();
    if (l1 == 0L) {
      return Integer.valueOf(0);
    }
    return Integer.valueOf((int)((System.currentTimeMillis() - l1) / 60000L));
  }
  
  private Integer l()
  {
    String str = az.b(this.f).a;
    if (str == null) {
      return Integer.valueOf(0);
    }
    if (str.equals("mobile")) {
      return Integer.valueOf(2);
    }
    if (str.equals("wifi")) {
      return Integer.valueOf(1);
    }
    return Integer.valueOf(0);
  }
  
  private Integer m()
  {
    UserSettings.Relation localRelation = this.g.a();
    if (localRelation == null) {
      return Integer.valueOf(0);
    }
    return Integer.valueOf(localRelation.getValue());
  }
  
  private Integer n()
  {
    UserSettings.Gender localGender = this.g.getGender();
    if (localGender == null) {
      return Integer.valueOf(0);
    }
    return Integer.valueOf(localGender.getValue());
  }
  
  private Double o()
  {
    return this.h;
  }
  
  private Double p()
  {
    return this.i;
  }
  
  private Boolean q()
  {
    return Boolean.valueOf(this.j);
  }
  
  private Double r()
  {
    SharedPreferences localSharedPreferences = this.f.getSharedPreferences("appodeal", 0);
    long l1 = com.appodeal.ads.utils.e.a(localSharedPreferences);
    return Double.valueOf(com.appodeal.ads.utils.e.b(localSharedPreferences) / l1 / 60.0D);
  }
  
  private String s()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Object localObject = this.f.getPackageManager().getInstalledApplications(0);
    Pattern localPattern = Pattern.compile("^?(?:com\\.android|com\\.google|com\\.sec|com\\.samsung|com\\.sonyericsson|com\\.sonymobile|com\\.motorola|com\\.htc).*$");
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = ((ApplicationInfo)((Iterator)localObject).next()).packageName;
      if ((!localPattern.matcher(str).matches()) && (!str.equals("android"))) {
        localStringBuilder.append(str).append(",");
      }
    }
    return localStringBuilder.toString();
  }
  
  public f a(JSONArray paramJSONArray)
  {
    int n = 0;
    while (n < paramJSONArray.length()) {
      try
      {
        f localF = new f(paramJSONArray.getJSONObject(n));
        boolean bool = b(localF);
        if (bool) {
          return localF;
        }
      }
      catch (Exception localException)
      {
        Appodeal.a(localException);
        n += 1;
      }
    }
    return null;
  }
  
  @VisibleForTesting
  boolean a(e paramE, Object paramObject)
  {
    return (e(paramE, paramObject)) || (d(paramE, paramObject));
  }
  
  @VisibleForTesting
  boolean a(e[] paramArrayOfE)
  {
    boolean bool2 = false;
    int i1 = paramArrayOfE.length;
    int n = 0;
    while (n < i1)
    {
      e localE = paramArrayOfE[n];
      bool1 = bool2;
      if (!g(localE, a(localE.a))) {
        break label50;
      }
      n += 1;
    }
    boolean bool1 = true;
    label50:
    return bool1;
  }
  
  @VisibleForTesting
  boolean b(e paramE, Object paramObject)
  {
    return (e(paramE, paramObject)) || (c(paramE, paramObject));
  }
  
  @VisibleForTesting
  boolean b(f paramF)
  {
    switch (1.a[paramF.a.ordinal()])
    {
    default: 
      return false;
    case 1: 
      return a(paramF.b);
    }
    return b(paramF.b);
  }
  
  public boolean b(@NonNull JSONArray paramJSONArray)
  {
    if (c == null)
    {
      c = paramJSONArray;
      return true;
    }
    if (!c.toString().equals(paramJSONArray.toString()))
    {
      c = paramJSONArray;
      return true;
    }
    return false;
  }
  
  @VisibleForTesting
  boolean b(e[] paramArrayOfE)
  {
    boolean bool2 = false;
    int i1 = paramArrayOfE.length;
    int n = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (n < i1)
      {
        e localE = paramArrayOfE[n];
        if (g(localE, a(localE.a))) {
          bool1 = true;
        }
      }
      else
      {
        return bool1;
      }
      n += 1;
    }
  }
  
  @VisibleForTesting
  boolean c(e paramE, Object paramObject)
  {
    boolean bool = true;
    if (paramE.d == e.a.f) {
      if (((Double)paramE.c).doubleValue() > ((Double)paramObject).doubleValue()) {
        bool = true;
      }
    }
    do
    {
      do
      {
        for (;;)
        {
          return bool;
          bool = false;
        }
        if (paramE.d != e.a.d) {
          break;
        }
      } while (((Integer)paramE.c).intValue() > ((Integer)paramObject).intValue());
      return false;
      if (paramE.d != e.a.a) {
        break;
      }
    } while (((h)paramE.c).compareTo(paramObject) > 0);
    return false;
    return false;
  }
  
  @VisibleForTesting
  boolean d(e paramE, Object paramObject)
  {
    boolean bool = true;
    if (paramE.d == e.a.f) {
      if (((Double)paramE.c).doubleValue() < ((Double)paramObject).doubleValue()) {
        bool = true;
      }
    }
    do
    {
      do
      {
        for (;;)
        {
          return bool;
          bool = false;
        }
        if (paramE.d != e.a.d) {
          break;
        }
      } while (((Integer)paramE.c).intValue() < ((Integer)paramObject).intValue());
      return false;
      if (paramE.d != e.a.a) {
        break;
      }
    } while (((h)paramE.c).compareTo(paramObject) < 0);
    return false;
    return false;
  }
  
  @VisibleForTesting
  boolean e(e paramE, Object paramObject)
  {
    boolean bool = true;
    switch (1.c[paramE.d.ordinal()])
    {
    default: 
      bool = false;
    }
    do
    {
      do
      {
        return bool;
        if (((h)paramE.c).compareTo(paramObject) == 0) {}
        for (bool = true;; bool = false) {
          return bool;
        }
      } while ((paramObject != null) && (((String)paramObject).contains((String)paramE.c)));
      return false;
    } while ((paramObject != null) && (paramObject.equals(paramE.c)));
    return false;
  }
  
  @VisibleForTesting
  boolean f(e paramE, Object paramObject)
  {
    switch (1.c[paramE.d.ordinal()])
    {
    case 3: 
    case 4: 
    case 5: 
    default: 
      return false;
    case 2: 
      return ((String)paramObject).toLowerCase().contains(((String)paramE.c).toLowerCase());
    case 6: 
      return a((String[])paramE.c, (String)paramObject);
    }
    return a((Integer[])paramE.c, (Integer)paramObject);
  }
  
  @VisibleForTesting
  int g()
  {
    SharedPreferences localSharedPreferences = this.f.getSharedPreferences("appodeal", 0);
    int i1 = localSharedPreferences.getInt("part_of_audience", -1);
    int n = i1;
    if (i1 == -1)
    {
      n = new Random().nextInt(100) + 1;
      localSharedPreferences.edit().putInt("part_of_audience", n).apply();
    }
    return n;
  }
}
