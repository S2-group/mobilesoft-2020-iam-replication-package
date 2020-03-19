package com.appodeal.ads.f;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.UserSettings;
import com.appodeal.ads.UserSettings.Gender;
import com.appodeal.ads.UserSettings.Occupation;
import com.appodeal.ads.UserSettings.Relation;
import com.appodeal.ads.ao;
import com.appodeal.ads.ao.a;
import com.appodeal.ads.utils.d;
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
  private static double i;
  private static double j;
  private static boolean k;
  private final Context d;
  private final JSONObject e;
  private final Double f;
  private final Double g;
  private final boolean h;
  
  g(Context paramContext, double paramDouble1, double paramDouble2, boolean paramBoolean)
  {
    if (UserSettings.userData != null) {}
    for (this.e = UserSettings.userData.optJSONObject("user_settings");; this.e = null)
    {
      this.d = paramContext;
      this.f = Double.valueOf(paramDouble1);
      this.g = Double.valueOf(paramDouble2);
      this.h = paramBoolean;
      return;
    }
  }
  
  public g(Context paramContext, JSONObject paramJSONObject)
  {
    if (UserSettings.userData != null)
    {
      this.e = UserSettings.userData.optJSONObject("user_settings");
      this.d = paramContext;
      this.f = Double.valueOf(paramJSONObject.optDouble("inapp_sum", 0.0D));
      this.g = Double.valueOf(paramJSONObject.optDouble("inapp_sum_all_apps", 0.0D));
      if ((!paramJSONObject.has("inapp_sum")) || (this.f.doubleValue() <= 0.0D)) {
        break label119;
      }
    }
    label119:
    for (boolean bool = true;; bool = false)
    {
      this.h = bool;
      i = this.g.doubleValue();
      j = this.g.doubleValue();
      k = this.h;
      return;
      this.e = null;
      break;
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
        if (paramString.equals("country"))
        {
          if (UserSettings.userData != null) {
            return UserSettings.userData.optString("country_id");
          }
        }
        else
        {
          if (paramString.equals("app_version")) {
            return new h(this.d.getPackageManager().getPackageInfo(this.d.getPackageName(), 0).versionName);
          }
          if (paramString.equals("app")) {
            return this.d.getSharedPreferences("appodeal", 0).getString("appKey", null);
          }
          if (paramString.equals("sdk_version")) {
            return new h("1.15.2");
          }
          if (paramString.equals("android_version")) {
            return new h(Build.VERSION.RELEASE);
          }
          if (paramString.equals("has_app_installed")) {
            return o();
          }
          if (paramString.equals("session_count")) {
            return Integer.valueOf((int)d.a(this.d.getSharedPreferences("appodeal", 0)));
          }
          if (paramString.equals("average_session_length")) {
            return n();
          }
          if (paramString.equals("device_model")) {
            return String.format("%s %s", new Object[] { Build.MANUFACTURER, Build.MODEL });
          }
          if (paramString.equals("connection_type")) {
            return g();
          }
          if (paramString.equals("gender")) {
            return j();
          }
          if (paramString.equals("age")) {
            return e();
          }
          if (paramString.equals("occupation")) {
            return h();
          }
          if (paramString.equals("relation")) {
            return i();
          }
          if (paramString.equals("interests")) {
            return d();
          }
          if (paramString.equals("bought_inapps")) {
            return m();
          }
          if (paramString.equals("inapp_sum")) {
            return k();
          }
          if (paramString.equals("inapp_sum_all_apps")) {
            return l();
          }
          if (paramString.equals("last_session_time")) {
            return f();
          }
          if (paramString.equals("platform")) {
            return "android";
          }
          if (paramString.equals("device_type"))
          {
            if (ao.n(this.d)) {
              return "tablet";
            }
          }
          else
          {
            if ((b == null) || (!b.containsKey(paramString))) {
              continue;
            }
            paramString = b.get(paramString);
            return paramString;
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
    b();
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
    int n = paramArrayOfE.length;
    int m = 0;
    while (m < n)
    {
      e localE = paramArrayOfE[m];
      bool1 = bool2;
      if (!a(localE, a(localE.a))) {
        break label50;
      }
      m += 1;
    }
    boolean bool1 = true;
    label50:
    return bool1;
  }
  
  private boolean a(Integer[] paramArrayOfInteger, Integer paramInteger)
  {
    boolean bool2 = false;
    int n = paramArrayOfInteger.length;
    int m = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (m < n)
      {
        if (paramArrayOfInteger[m].equals(paramInteger)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      m += 1;
    }
  }
  
  private boolean a(String[] paramArrayOfString, String paramString)
  {
    boolean bool2 = false;
    int n = paramArrayOfString.length;
    int m = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (m < n)
      {
        if (paramString.contains(paramArrayOfString[m])) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      m += 1;
    }
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
      int m = Integer.parseInt(paramString);
      return Integer.valueOf(m);
    }
    catch (Exception paramString) {}
    return null;
  }
  
  private static void b()
  {
    Object localObject2;
    for (Object localObject1 = new g(Appodeal.b, i, j, k);; localObject2 = null) {
      try
      {
        if (c != null)
        {
          localObject1 = ((g)localObject1).a(c);
          if ((localObject1 != null) && (((f)localObject1).c() != a.c()))
          {
            ((f)localObject1).a();
            a((f)localObject1);
            c();
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
    int n = paramArrayOfE.length;
    int m = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (m < n)
      {
        e localE = paramArrayOfE[m];
        if (a(localE, a(localE.a))) {
          bool1 = true;
        }
      }
      else
      {
        return bool1;
      }
      m += 1;
    }
  }
  
  private static void c()
  {
    com.appodeal.ads.w.k = true;
    com.appodeal.ads.o.l = true;
    com.appodeal.ads.ai.j = true;
    com.appodeal.ads.Native.i = true;
    com.appodeal.ads.g.k = true;
    com.appodeal.ads.al.j = true;
  }
  
  private boolean c(e paramE, Object paramObject)
  {
    return (f(paramE, paramObject)) || (d(paramE, paramObject));
  }
  
  private String d()
  {
    if ((this.e != null) && (this.e.has("interests"))) {
      return this.e.getString("interests");
    }
    return Appodeal.getUserSettings(this.d).getInterests();
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
  
  private Integer e()
  {
    if ((this.e != null) && (this.e.has("age"))) {
      return Integer.valueOf(this.e.getInt("age"));
    }
    return Appodeal.getUserSettings(this.d).getAge();
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
  
  private Integer f()
  {
    long l = d.c();
    if (l == 0L) {
      return Integer.valueOf(0);
    }
    return Integer.valueOf((int)((System.currentTimeMillis() - l) / 60000L));
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
  
  private Integer g()
  {
    String str = ao.b(this.d).a;
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
    if ((this.e != null) && (this.e.has("occupation"))) {
      return Integer.valueOf(this.e.optInt("occupation"));
    }
    UserSettings.Occupation localOccupation = Appodeal.getUserSettings(this.d).getOccupation();
    if (localOccupation == null) {
      return Integer.valueOf(0);
    }
    return Integer.valueOf(localOccupation.getValue());
  }
  
  private Integer i()
  {
    if ((this.e != null) && (this.e.has("relation"))) {
      return Integer.valueOf(this.e.getInt("relation"));
    }
    UserSettings.Relation localRelation = Appodeal.getUserSettings(this.d).getRelation();
    if (localRelation == null) {
      return Integer.valueOf(0);
    }
    return Integer.valueOf(localRelation.getValue());
  }
  
  private Integer j()
  {
    if ((this.e != null) && (this.e.has("gender"))) {
      return b(this.e.getString("gender"));
    }
    UserSettings.Gender localGender = Appodeal.getUserSettings(this.d).getGender();
    if (localGender == null) {
      return Integer.valueOf(0);
    }
    return Integer.valueOf(localGender.getValue());
  }
  
  private Double k()
  {
    return this.f;
  }
  
  private Double l()
  {
    return this.g;
  }
  
  private Boolean m()
  {
    return Boolean.valueOf(this.h);
  }
  
  private Double n()
  {
    SharedPreferences localSharedPreferences = this.d.getSharedPreferences("appodeal", 0);
    long l = d.a(localSharedPreferences);
    return Double.valueOf(d.b(localSharedPreferences) / l / 60.0D);
  }
  
  private String o()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Object localObject = this.d.getPackageManager().getInstalledApplications(0);
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
    int m = 0;
    while (m < paramJSONArray.length()) {
      try
      {
        f localF = new f(paramJSONArray.getJSONObject(m));
        boolean bool = b(localF);
        if (bool) {
          return localF;
        }
      }
      catch (Exception localException)
      {
        Appodeal.a(localException);
        m += 1;
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
