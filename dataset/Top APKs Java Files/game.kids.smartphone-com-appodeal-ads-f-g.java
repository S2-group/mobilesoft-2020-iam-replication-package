package com.appodeal.ads.f;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
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
  static f a;
  static Map<String, Object> b;
  static JSONArray c;
  private static int d = -1;
  private static int e;
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
    boolean bool;
    if ((paramJSONObject.has("inapp_sum")) && (this.h.doubleValue() > 0.0D)) {
      bool = true;
    } else {
      bool = false;
    }
    this.j = bool;
    k = this.i.doubleValue();
    l = this.i.doubleValue();
    m = this.j;
  }
  
  public static f a()
  {
    if (a == null) {
      a = new f(new JSONObject());
    }
    return a;
  }
  
  private Object a(String paramString)
  {
    if (paramString == null) {
      return null;
    }
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
        return new h("2.1.9");
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
        if (!az.n(this.f)) {
          break label475;
        }
        return "tablet";
      }
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
      if (paramString.equals("part_of_audience"))
      {
        int n = g();
        return Integer.valueOf(n);
      }
    }
    catch (Exception paramString)
    {
      Appodeal.a(paramString);
    }
    return null;
    label475:
    return "phone";
  }
  
  public static void a(f paramF)
  {
    a = paramF;
    if ((paramF.d != null) && (paramF.d.a != null)) {
      paramF = String.format("Matched segment #%s: %s", new Object[] { Long.valueOf(paramF.c()), paramF.d.a });
    } else {
      paramF = String.format("Matched segment #%s", new Object[] { Long.valueOf(paramF.c()) });
    }
    Appodeal.a(paramF);
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
    int i1 = paramArrayOfInteger.length;
    int n = 0;
    while (n < i1)
    {
      if (paramArrayOfInteger[n].equals(paramInteger)) {
        return true;
      }
      n += 1;
    }
    return false;
  }
  
  private boolean a(String[] paramArrayOfString, String paramString)
  {
    int i1 = paramArrayOfString.length;
    int n = 0;
    while (n < i1)
    {
      if (paramString.contains(paramArrayOfString[n])) {
        return true;
      }
      n += 1;
    }
    return false;
  }
  
  static boolean b()
  {
    return a().c() == -1L;
  }
  
  static void c()
  {
    if (Appodeal.d != null)
    {
      g localG = new g(Appodeal.d, k, l, m);
      f localF = null;
      try
      {
        if (c != null) {
          localF = localG.a(c);
        }
        if ((localF != null) && (localF.c() != a.c()))
        {
          localF.a();
          a(localF);
          h();
          return;
        }
      }
      catch (Exception localException)
      {
        Appodeal.a(localException);
      }
    }
  }
  
  static int d()
  {
    return Calendar.getInstance().get(11);
  }
  
  static int e()
  {
    switch (Calendar.getInstance().get(7))
    {
    default: 
      return 0;
    case 7: 
      return 6;
    case 6: 
      return 5;
    case 5: 
      return 4;
    case 4: 
      return 3;
    case 3: 
      return 2;
    case 2: 
      return 1;
    }
    return 7;
  }
  
  public static void f()
  {
    if ((c != null) && ((e() != e) || (d() != d))) {
      c();
    }
  }
  
  private boolean g(e paramE, Object paramObject)
  {
    if (paramObject == null) {
      return false;
    }
    if (paramE.d == e.a.h)
    {
      paramE.d = e.a(paramObject);
      paramE.a();
    }
    if (paramE.d == e.a.h) {
      return false;
    }
    switch (1.b[paramE.b.ordinal()])
    {
    default: 
      return false;
    case 7: 
      return a(paramE, paramObject);
    case 6: 
      return b(paramE, paramObject);
    case 5: 
      return d(paramE, paramObject);
    case 4: 
      return c(paramE, paramObject);
    case 3: 
      return e(paramE, paramObject) ^ true;
    case 2: 
      return e(paramE, paramObject);
    }
    return f(paramE, paramObject);
  }
  
  private static void h()
  {
    com.appodeal.ads.ab.h = true;
    com.appodeal.ads.q.i = true;
    com.appodeal.ads.ar.g = true;
    com.appodeal.ads.Native.i = true;
    com.appodeal.ads.i.h = true;
    com.appodeal.ads.av.i = true;
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
    if (l1 == 0L) {}
    for (int n = 0;; n = (int)((System.currentTimeMillis() - l1) / 60000L)) {
      return Integer.valueOf(n);
    }
  }
  
  private Integer l()
  {
    String str = az.b(this.f).a;
    if (str == null) {
      return Integer.valueOf(0);
    }
    if (str.equals("mobile")) {}
    for (int n = 2;; n = 1)
    {
      return Integer.valueOf(n);
      if (!str.equals("wifi")) {
        break;
      }
    }
    return Integer.valueOf(0);
  }
  
  private Integer m()
  {
    UserSettings.Relation localRelation = this.g.a();
    if (localRelation == null) {}
    for (int n = 0;; n = localRelation.getValue()) {
      return Integer.valueOf(n);
    }
  }
  
  private Integer n()
  {
    UserSettings.Gender localGender = this.g.getGender();
    if (localGender == null) {}
    for (int n = 0;; n = localGender.getValue()) {
      return Integer.valueOf(n);
    }
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
      if ((!localPattern.matcher(str).matches()) && (!str.equals("android")))
      {
        localStringBuilder.append(str);
        localStringBuilder.append(",");
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
  
  boolean a(e paramE, Object paramObject)
  {
    return (e(paramE, paramObject)) || (d(paramE, paramObject));
  }
  
  boolean a(e[] paramArrayOfE)
  {
    int i1 = paramArrayOfE.length;
    int n = 0;
    while (n < i1)
    {
      e localE = paramArrayOfE[n];
      if (g(localE, a(localE.a))) {
        n += 1;
      } else {
        return false;
      }
    }
    return true;
  }
  
  boolean b(e paramE, Object paramObject)
  {
    return (e(paramE, paramObject)) || (c(paramE, paramObject));
  }
  
  boolean b(f paramF)
  {
    switch (1.a[paramF.a.ordinal()])
    {
    default: 
      return false;
    case 2: 
      return b(paramF.b);
    }
    return a(paramF.b);
  }
  
  public boolean b(JSONArray paramJSONArray)
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
  
  boolean b(e[] paramArrayOfE)
  {
    int i1 = paramArrayOfE.length;
    int n = 0;
    while (n < i1)
    {
      e localE = paramArrayOfE[n];
      if (g(localE, a(localE.a))) {
        return true;
      }
      n += 1;
    }
    return false;
  }
  
  boolean c(e paramE, Object paramObject)
  {
    if (paramE.d == e.a.f) {
      return ((Double)paramE.c).doubleValue() > ((Double)paramObject).doubleValue();
    }
    if (paramE.d == e.a.d) {
      return ((Integer)paramE.c).intValue() > ((Integer)paramObject).intValue();
    }
    if (paramE.d == e.a.a) {
      return ((h)paramE.c).compareTo(paramObject) > 0;
    }
    return false;
  }
  
  boolean d(e paramE, Object paramObject)
  {
    if (paramE.d == e.a.f) {
      return ((Double)paramE.c).doubleValue() < ((Double)paramObject).doubleValue();
    }
    if (paramE.d == e.a.d) {
      return ((Integer)paramE.c).intValue() < ((Integer)paramObject).intValue();
    }
    if (paramE.d == e.a.a) {
      return ((h)paramE.c).compareTo(paramObject) < 0;
    }
    return false;
  }
  
  boolean e(e paramE, Object paramObject)
  {
    switch (1.c[paramE.d.ordinal()])
    {
    default: 
      return false;
    case 3: 
    case 4: 
    case 5: 
      return (paramObject != null) && (paramObject.equals(paramE.c));
    case 2: 
      return (paramObject != null) && (((String)paramObject).contains((String)paramE.c));
    }
    return ((h)paramE.c).compareTo(paramObject) == 0;
  }
  
  boolean f(e paramE, Object paramObject)
  {
    int n = 1.c[paramE.d.ordinal()];
    if (n != 2)
    {
      switch (n)
      {
      default: 
        return false;
      case 7: 
        return a((Integer[])paramE.c, (Integer)paramObject);
      }
      return a((String[])paramE.c, (String)paramObject);
    }
    return ((String)paramObject).toLowerCase().contains(((String)paramE.c).toLowerCase());
  }
  
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
