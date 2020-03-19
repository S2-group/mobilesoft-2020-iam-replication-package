package com.appodeal.ads.f;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.appodeal.ads.UserSettings.Occupation;
import com.appodeal.ads.UserSettings.Relation;
import com.appodeal.ads.an;
import com.appodeal.ads.an.a;
import com.appodeal.ads.utils.d;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public class g
{
  private static f a;
  private static Map<String, Object> b;
  private static JSONArray c;
  private static int d = -1;
  private static int e;
  private static double k;
  private static double l;
  private static boolean m;
  private final Context f;
  private final JSONObject g;
  private final Double h;
  private final Double i;
  private final boolean j;
  
  g(Context paramContext, double paramDouble1, double paramDouble2, boolean paramBoolean)
  {
    if (UserSettings.userData != null) {}
    for (JSONObject localJSONObject = UserSettings.userData.optJSONObject("user_settings");; localJSONObject = null)
    {
      this.g = localJSONObject;
      break;
    }
    this.f = paramContext;
    this.h = Double.valueOf(paramDouble1);
    this.i = Double.valueOf(paramDouble2);
    this.j = paramBoolean;
  }
  
  public g(Context paramContext, JSONObject paramJSONObject)
  {
    if (UserSettings.userData != null) {}
    for (JSONObject localJSONObject = UserSettings.userData.optJSONObject("user_settings");; localJSONObject = null)
    {
      this.g = localJSONObject;
      break;
    }
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
    if (paramString == null) {
      return null;
    }
    try
    {
      if (paramString.equals("country"))
      {
        if (UserSettings.userData == null) {
          return null;
        }
        return UserSettings.userData.optString("country_id");
      }
      if (paramString.equals("app_version")) {
        return new h(this.f.getPackageManager().getPackageInfo(this.f.getPackageName(), 0).versionName);
      }
      if (paramString.equals("app")) {
        return this.f.getSharedPreferences("appodeal", 0).getString("appKey", null);
      }
      if (paramString.equals("sdk_version")) {
        return new h("1.15.9");
      }
      if (paramString.equals("android_version")) {
        return new h(Build.VERSION.RELEASE);
      }
      if (paramString.equals("has_app_installed")) {
        return r();
      }
      if (paramString.equals("session_count")) {
        return Integer.valueOf((int)d.a(this.f.getSharedPreferences("appodeal", 0)));
      }
      if (paramString.equals("average_session_length")) {
        return q();
      }
      if (paramString.equals("device_model")) {
        return String.format("%s %s", new Object[] { Build.MANUFACTURER, Build.MODEL });
      }
      if (paramString.equals("connection_type")) {
        return j();
      }
      if (paramString.equals("gender")) {
        return m();
      }
      if (paramString.equals("age")) {
        return h();
      }
      if (paramString.equals("occupation")) {
        return k();
      }
      if (paramString.equals("relation")) {
        return l();
      }
      if (paramString.equals("interests")) {
        return g();
      }
      if (paramString.equals("bought_inapps")) {
        return p();
      }
      if (paramString.equals("inapp_sum")) {
        return n();
      }
      if (paramString.equals("inapp_sum_all_apps")) {
        return o();
      }
      if (paramString.equals("last_session_time")) {
        return i();
      }
      if (paramString.equals("platform")) {
        return "android";
      }
      if (paramString.equals("device_type"))
      {
        if (!an.n(this.f)) {
          break label483;
        }
        return "tablet";
      }
      if ((b != null) && (b.containsKey(paramString))) {
        return b.get(paramString);
      }
      if (paramString.equals("day"))
      {
        e = c();
        return Integer.valueOf(e);
      }
      if (paramString.equals("hour"))
      {
        d = b();
        int n = d;
        return Integer.valueOf(n);
      }
    }
    catch (Exception paramString)
    {
      Appodeal.a(paramString);
    }
    return null;
    label483:
    return "phone";
  }
  
  public static void a(@NonNull f paramF)
  {
    a = paramF;
    Appodeal.a(String.format("Matched segment #%s", new Object[] { Long.valueOf(paramF.c()) }));
  }
  
  public static void a(String paramString, Object paramObject)
  {
    if (b == null) {
      b = new HashMap();
    }
    b.put(paramString, paramObject);
    e();
  }
  
  private boolean a(e paramE, Object paramObject)
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
      return b(paramE, paramObject);
    case 6: 
      return c(paramE, paramObject);
    case 5: 
      return e(paramE, paramObject);
    case 4: 
      return d(paramE, paramObject);
    case 3: 
      return f(paramE, paramObject) ^ true;
    case 2: 
      return f(paramE, paramObject);
    }
    return g(paramE, paramObject);
  }
  
  private boolean a(e[] paramArrayOfE)
  {
    int i1 = paramArrayOfE.length;
    int n = 0;
    while (n < i1)
    {
      e localE = paramArrayOfE[n];
      if (a(localE, a(localE.a))) {
        n += 1;
      } else {
        return false;
      }
    }
    return true;
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
  
  @VisibleForTesting
  static int b()
  {
    return Calendar.getInstance().get(11);
  }
  
  private Integer b(String paramString)
  {
    int n;
    if (paramString.equalsIgnoreCase("o")) {
      n = 0;
    }
    for (;;)
    {
      return Integer.valueOf(n);
      if (paramString.equalsIgnoreCase("f"))
      {
        n = 1;
      }
      else
      {
        if (!paramString.equalsIgnoreCase("m")) {
          break;
        }
        n = 2;
      }
    }
    try
    {
      n = Integer.parseInt(paramString);
      return Integer.valueOf(n);
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    return null;
  }
  
  private boolean b(e paramE, Object paramObject)
  {
    return (f(paramE, paramObject)) || (e(paramE, paramObject));
  }
  
  private boolean b(f paramF)
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
  
  private boolean b(e[] paramArrayOfE)
  {
    int i1 = paramArrayOfE.length;
    int n = 0;
    while (n < i1)
    {
      e localE = paramArrayOfE[n];
      if (a(localE, a(localE.a))) {
        return true;
      }
      n += 1;
    }
    return false;
  }
  
  @VisibleForTesting
  static int c()
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
  
  private boolean c(e paramE, Object paramObject)
  {
    return (f(paramE, paramObject)) || (d(paramE, paramObject));
  }
  
  public static void d()
  {
    if ((c != null) && ((c() != e) || (b() != d))) {
      e();
    }
  }
  
  private boolean d(e paramE, Object paramObject)
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
  
  private static void e()
  {
    g localG = new g(Appodeal.b, k, l, m);
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
        f();
        return;
      }
    }
    catch (Exception localException)
    {
      Appodeal.a(localException);
    }
  }
  
  private boolean e(e paramE, Object paramObject)
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
  
  private static void f()
  {
    com.appodeal.ads.v.k = true;
    com.appodeal.ads.n.l = true;
    com.appodeal.ads.ah.j = true;
    com.appodeal.ads.Native.i = true;
    com.appodeal.ads.g.k = true;
    com.appodeal.ads.ak.j = true;
  }
  
  private boolean f(e paramE, Object paramObject)
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
  
  private String g()
  {
    if ((this.g != null) && (this.g.has("interests"))) {
      return this.g.getString("interests");
    }
    return Appodeal.getUserSettings(this.f).getInterests();
  }
  
  private boolean g(e paramE, Object paramObject)
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
  
  private Integer h()
  {
    if ((this.g != null) && (this.g.has("age"))) {
      return Integer.valueOf(this.g.getInt("age"));
    }
    return Appodeal.getUserSettings(this.f).getAge();
  }
  
  private Integer i()
  {
    long l1 = d.c();
    if (l1 == 0L) {}
    for (int n = 0;; n = (int)((System.currentTimeMillis() - l1) / 60000L)) {
      return Integer.valueOf(n);
    }
  }
  
  private Integer j()
  {
    String str = an.b(this.f).a;
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
  
  private Integer k()
  {
    int n;
    if ((this.g != null) && (this.g.has("occupation"))) {
      n = this.g.optInt("occupation");
    }
    for (;;)
    {
      return Integer.valueOf(n);
      UserSettings.Occupation localOccupation = Appodeal.getUserSettings(this.f).getOccupation();
      if (localOccupation == null) {
        n = 0;
      } else {
        n = localOccupation.getValue();
      }
    }
  }
  
  private Integer l()
  {
    int n;
    if ((this.g != null) && (this.g.has("relation"))) {
      n = this.g.getInt("relation");
    }
    for (;;)
    {
      return Integer.valueOf(n);
      UserSettings.Relation localRelation = Appodeal.getUserSettings(this.f).getRelation();
      if (localRelation == null) {
        n = 0;
      } else {
        n = localRelation.getValue();
      }
    }
  }
  
  private Integer m()
  {
    if ((this.g != null) && (this.g.has("gender"))) {
      return b(this.g.getString("gender"));
    }
    UserSettings.Gender localGender = Appodeal.getUserSettings(this.f).getGender();
    if (localGender == null) {
      return Integer.valueOf(0);
    }
    return Integer.valueOf(localGender.getValue());
  }
  
  private Double n()
  {
    return this.h;
  }
  
  private Double o()
  {
    return this.i;
  }
  
  private Boolean p()
  {
    return Boolean.valueOf(this.j);
  }
  
  private Double q()
  {
    SharedPreferences localSharedPreferences = this.f.getSharedPreferences("appodeal", 0);
    long l1 = d.a(localSharedPreferences);
    double d1 = d.b(localSharedPreferences);
    double d2 = l1;
    Double.isNaN(d1);
    Double.isNaN(d2);
    return Double.valueOf(d1 / d2 / 60.0D);
  }
  
  private String r()
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
}
