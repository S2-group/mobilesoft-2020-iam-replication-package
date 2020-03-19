package com.appodeal.ads.f;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
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
import org.a.a;
import org.a.c;

public class g
{
  private static f a;
  private static Map<String, Object> b;
  private static a c;
  private static int d = -1;
  private static int e = 0;
  private static double k;
  private static double l;
  private static boolean m;
  private final Context f;
  private final c g;
  private final Double h;
  private final Double i;
  private final boolean j;
  
  g(Context paramContext, double paramDouble1, double paramDouble2, boolean paramBoolean)
  {
    if (UserSettings.userData != null) {}
    for (this.g = UserSettings.userData.optJSONObject("user_settings");; this.g = null)
    {
      this.f = paramContext;
      this.h = Double.valueOf(paramDouble1);
      this.i = Double.valueOf(paramDouble2);
      this.j = paramBoolean;
      return;
    }
  }
  
  public g(Context paramContext, c paramC)
  {
    if (UserSettings.userData != null)
    {
      this.g = UserSettings.userData.optJSONObject("user_settings");
      this.f = paramContext;
      this.h = Double.valueOf(paramC.optDouble("inapp_sum", 0.0D));
      this.i = Double.valueOf(paramC.optDouble("inapp_sum_all_apps", 0.0D));
      if ((!paramC.has("inapp_sum")) || (this.h.doubleValue() <= 0.0D)) {
        break label119;
      }
    }
    label119:
    for (boolean bool = true;; bool = false)
    {
      this.j = bool;
      k = this.i.doubleValue();
      l = this.i.doubleValue();
      m = this.j;
      return;
      this.g = null;
      break;
    }
  }
  
  public static f a()
  {
    if (a == null) {
      a = new f(new c());
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
        if (paramString.equals("country"))
        {
          if (UserSettings.userData != null) {
            return UserSettings.userData.optString("country_id");
          }
        }
        else
        {
          if (paramString.equals("app_version")) {
            return new h(this.f.getPackageManager().getPackageInfo(this.f.getPackageName(), 0).versionName);
          }
          if (paramString.equals("app")) {
            return this.f.getSharedPreferences("appodeal", 0).getString("appKey", null);
          }
          if (paramString.equals("sdk_version")) {
            return new h("1.15.7");
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
            if (an.n(this.f)) {
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
              e = c();
              return Integer.valueOf(e);
            }
            if (!paramString.equals("hour")) {
              continue;
            }
            d = b();
            int n = d;
            return Integer.valueOf(n);
          }
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
  
  public static void a(f paramF)
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
        return g(paramE, paramObject);
      case 2: 
        return f(paramE, paramObject);
      }
    } while (f(paramE, paramObject));
    return true;
    return d(paramE, paramObject);
    return e(paramE, paramObject);
    return c(paramE, paramObject);
    return b(paramE, paramObject);
  }
  
  private boolean a(e[] paramArrayOfE)
  {
    boolean bool2 = false;
    int i1 = paramArrayOfE.length;
    int n = 0;
    while (n < i1)
    {
      e localE = paramArrayOfE[n];
      bool1 = bool2;
      if (!a(localE, a(localE.a))) {
        break label50;
      }
      n += 1;
    }
    boolean bool1 = true;
    label50:
    return bool1;
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
  
  static int b()
  {
    return Calendar.getInstance().get(11);
  }
  
  private Integer b(String paramString)
  {
    if (paramString.equalsIgnoreCase("o")) {
      return Integer.valueOf(0);
    }
    if (paramString.equalsIgnoreCase("f")) {
      return Integer.valueOf(1);
    }
    if (paramString.equalsIgnoreCase("m")) {
      return Integer.valueOf(2);
    }
    try
    {
      int n = Integer.parseInt(paramString);
      return Integer.valueOf(n);
    }
    catch (Exception paramString) {}
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
    case 1: 
      return a(paramF.b);
    }
    return b(paramF.b);
  }
  
  private boolean b(e[] paramArrayOfE)
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
        if (a(localE, a(localE.a))) {
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
  
  static int c()
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
  
  private static void e()
  {
    Object localObject2;
    for (Object localObject1 = new g(Appodeal.b, k, l, m);; localObject2 = null) {
      try
      {
        if (c != null)
        {
          localObject1 = ((g)localObject1).a(c);
          if ((localObject1 != null) && (((f)localObject1).c() != a.c()))
          {
            ((f)localObject1).a();
            a((f)localObject1);
            f();
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
  
  private boolean e(e paramE, Object paramObject)
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
  
  private String g()
  {
    if ((this.g != null) && (this.g.has("interests"))) {
      return this.g.getString("interests");
    }
    return Appodeal.getUserSettings(this.f).getInterests();
  }
  
  private boolean g(e paramE, Object paramObject)
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
    if (l1 == 0L) {
      return Integer.valueOf(0);
    }
    return Integer.valueOf((int)((System.currentTimeMillis() - l1) / 60000L));
  }
  
  private Integer j()
  {
    String str = an.b(this.f).a;
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
  
  private Integer k()
  {
    if ((this.g != null) && (this.g.has("occupation"))) {
      return Integer.valueOf(this.g.optInt("occupation"));
    }
    UserSettings.Occupation localOccupation = Appodeal.getUserSettings(this.f).getOccupation();
    if (localOccupation == null) {
      return Integer.valueOf(0);
    }
    return Integer.valueOf(localOccupation.getValue());
  }
  
  private Integer l()
  {
    if ((this.g != null) && (this.g.has("relation"))) {
      return Integer.valueOf(this.g.getInt("relation"));
    }
    UserSettings.Relation localRelation = Appodeal.getUserSettings(this.f).getRelation();
    if (localRelation == null) {
      return Integer.valueOf(0);
    }
    return Integer.valueOf(localRelation.getValue());
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
    return Double.valueOf(d.b(localSharedPreferences) / l1 / 60.0D);
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
      if ((!localPattern.matcher(str).matches()) && (!str.equals("android"))) {
        localStringBuilder.append(str).append(",");
      }
    }
    return localStringBuilder.toString();
  }
  
  public f a(a paramA)
  {
    int n = 0;
    while (n < paramA.login()) {
      try
      {
        f localF = new f(paramA.jdMethod_abstract(n));
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
  
  public boolean b(a paramA)
  {
    if (c == null)
    {
      c = paramA;
      return true;
    }
    if (!c.toString().equals(paramA.toString()))
    {
      c = paramA;
      return true;
    }
    return false;
  }
}
