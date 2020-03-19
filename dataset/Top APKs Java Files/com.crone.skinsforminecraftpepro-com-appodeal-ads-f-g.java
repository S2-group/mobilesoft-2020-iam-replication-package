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
import com.appodeal.ads.ak;
import com.appodeal.ads.ak.a;
import com.appodeal.ads.utils.b;
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
  public static Long a = null;
  private static f b;
  private static Map<String, Object> c;
  private final Context d;
  private final JSONObject e;
  private final JSONObject f;
  
  public g(Context paramContext, JSONObject paramJSONObject)
  {
    this.e = paramJSONObject;
    if (UserSettings.userData != null) {}
    for (this.f = UserSettings.userData.optJSONObject("user_settings");; this.f = null)
    {
      this.d = paramContext;
      return;
    }
  }
  
  @NonNull
  public static f a()
  {
    if (b == null) {
      b = new f(new JSONObject());
    }
    return b;
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
            return new h("1.14.14");
          }
          if (paramString.equals("android_version")) {
            return new h(Build.VERSION.RELEASE);
          }
          if (paramString.equals("has_app_installed")) {
            return m();
          }
          if (paramString.equals("session_count")) {
            return Integer.valueOf((int)b.a(this.d.getSharedPreferences("appodeal", 0)));
          }
          if (paramString.equals("average_session_length")) {
            return l();
          }
          if (paramString.equals("device_model")) {
            return String.format("%s %s", new Object[] { Build.MANUFACTURER, Build.MODEL });
          }
          if (paramString.equals("connection_type")) {
            return e();
          }
          if (paramString.equals("gender")) {
            return h();
          }
          if (paramString.equals("age")) {
            return c();
          }
          if (paramString.equals("occupation")) {
            return f();
          }
          if (paramString.equals("relation")) {
            return g();
          }
          if (paramString.equals("interests")) {
            return b();
          }
          if (paramString.equals("bought_inapps")) {
            return k();
          }
          if (paramString.equals("inapp_sum")) {
            return i();
          }
          if (paramString.equals("inapp_sum_all_apps")) {
            return j();
          }
          if (paramString.equals("last_session_time")) {
            return d();
          }
          if (paramString.equals("platform")) {
            return "android";
          }
          if (paramString.equals("device_type"))
          {
            if (ak.n(this.d)) {
              return "tablet";
            }
          }
          else
          {
            if ((c == null) || (!c.containsKey(paramString))) {
              continue;
            }
            paramString = c.get(paramString);
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
    b = paramF;
  }
  
  public static void a(String paramString, Object paramObject)
  {
    if (c == null) {
      c = new HashMap();
    }
    c.put(paramString, paramObject);
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
    int j = paramArrayOfE.length;
    int i = 0;
    while (i < j)
    {
      e localE = paramArrayOfE[i];
      bool1 = bool2;
      if (!a(localE, a(localE.a))) {
        break label50;
      }
      i += 1;
    }
    boolean bool1 = true;
    label50:
    return bool1;
  }
  
  private boolean a(Integer[] paramArrayOfInteger, Integer paramInteger)
  {
    boolean bool2 = false;
    int j = paramArrayOfInteger.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        if (paramArrayOfInteger[i].equals(paramInteger)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  private boolean a(String[] paramArrayOfString, String paramString)
  {
    boolean bool2 = false;
    int j = paramArrayOfString.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        if (paramString.contains(paramArrayOfString[i])) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
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
      int i = Integer.parseInt(paramString);
      return Integer.valueOf(i);
    }
    catch (Exception paramString) {}
    return null;
  }
  
  private String b()
  {
    if ((this.f != null) && (this.f.has("interests"))) {
      return this.f.getString("interests");
    }
    return Appodeal.getUserSettings(this.d).getInterests();
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
    int j = paramArrayOfE.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        e localE = paramArrayOfE[i];
        if (a(localE, a(localE.a))) {
          bool1 = true;
        }
      }
      else
      {
        return bool1;
      }
      i += 1;
    }
  }
  
  private Integer c()
  {
    if ((this.f != null) && (this.f.has("age"))) {
      return Integer.valueOf(this.f.getInt("age"));
    }
    return Appodeal.getUserSettings(this.d).getAge();
  }
  
  private boolean c(e paramE, Object paramObject)
  {
    return (f(paramE, paramObject)) || (d(paramE, paramObject));
  }
  
  private Integer d()
  {
    long l = b.c();
    if (l == 0L) {
      return Integer.valueOf(0);
    }
    return Integer.valueOf((int)((System.currentTimeMillis() - l) / 60000L));
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
    String str = ak.b(this.d).a;
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
    if ((this.f != null) && (this.f.has("occupation"))) {
      return Integer.valueOf(this.f.optInt("occupation"));
    }
    UserSettings.Occupation localOccupation = Appodeal.getUserSettings(this.d).getOccupation();
    if (localOccupation == null) {
      return Integer.valueOf(0);
    }
    return Integer.valueOf(localOccupation.getValue());
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
    if ((this.f != null) && (this.f.has("relation"))) {
      return Integer.valueOf(this.f.getInt("relation"));
    }
    UserSettings.Relation localRelation = Appodeal.getUserSettings(this.d).getRelation();
    if (localRelation == null) {
      return Integer.valueOf(0);
    }
    return Integer.valueOf(localRelation.getValue());
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
    if ((this.f != null) && (this.f.has("gender"))) {
      return b(this.f.getString("gender"));
    }
    UserSettings.Gender localGender = Appodeal.getUserSettings(this.d).getGender();
    if (localGender == null) {
      return Integer.valueOf(0);
    }
    return Integer.valueOf(localGender.getValue());
  }
  
  private Double i()
  {
    if (this.e.has("inapp_sum")) {
      return Double.valueOf(this.e.getString("inapp_sum"));
    }
    return Double.valueOf(0.0D);
  }
  
  private Double j()
  {
    if (this.e.has("inapp_sum_all_apps")) {
      return Double.valueOf(this.e.getString("inapp_sum_all_apps"));
    }
    return Double.valueOf(0.0D);
  }
  
  private Boolean k()
  {
    try
    {
      if ((this.e.has("inapp_sum")) && (this.e.getDouble("inapp_sum") > 0.0D)) {
        return Boolean.valueOf(true);
      }
    }
    catch (Exception localException)
    {
      Appodeal.a(localException);
    }
    return Boolean.valueOf(false);
  }
  
  private Double l()
  {
    SharedPreferences localSharedPreferences = this.d.getSharedPreferences("appodeal", 0);
    long l = b.a(localSharedPreferences);
    return Double.valueOf(b.b(localSharedPreferences) / l / 60.0D);
  }
  
  private String m()
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
    int i = 0;
    while (i < paramJSONArray.length()) {
      try
      {
        f localF = new f(paramJSONArray.getJSONObject(i));
        boolean bool = b(localF);
        if (bool) {
          return localF;
        }
      }
      catch (Exception localException)
      {
        Appodeal.a(localException);
        i += 1;
      }
    }
    return null;
  }
}
