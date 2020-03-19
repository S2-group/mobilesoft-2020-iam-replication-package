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
import com.appodeal.ads.aj;
import com.appodeal.ads.aj.a;
import com.appodeal.ads.utils.b;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public class f
{
  public static Long a = null;
  private static Map<String, Object> b;
  private final Context c;
  private final JSONObject d;
  private final JSONObject e;
  
  public f(Context paramContext, JSONObject paramJSONObject)
  {
    this.d = paramJSONObject;
    if (UserSettings.userData != null) {}
    for (this.e = UserSettings.userData.optJSONObject("user_settings");; this.e = null)
    {
      this.c = paramContext;
      return;
    }
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
            return new h(this.c.getPackageManager().getPackageInfo(this.c.getPackageName(), 0).versionName);
          }
          if (paramString.equals("app")) {
            return this.c.getSharedPreferences("appodeal", 0).getString("appKey", null);
          }
          if (paramString.equals("sdk_version")) {
            return new h("1.14.12");
          }
          if (paramString.equals("android_version")) {
            return new h(Build.VERSION.RELEASE);
          }
          if (paramString.equals("has_app_installed")) {
            return l();
          }
          if (paramString.equals("session_count")) {
            return Integer.valueOf((int)b.a(this.c.getSharedPreferences("appodeal", 0)));
          }
          if (paramString.equals("average_session_length")) {
            return k();
          }
          if (paramString.equals("device_model")) {
            return String.format("%s %s", new Object[] { Build.MANUFACTURER, Build.MODEL });
          }
          if (paramString.equals("connection_type")) {
            return d();
          }
          if (paramString.equals("gender")) {
            return g();
          }
          if (paramString.equals("age")) {
            return b();
          }
          if (paramString.equals("occupation")) {
            return e();
          }
          if (paramString.equals("relation")) {
            return f();
          }
          if (paramString.equals("interests")) {
            return a();
          }
          if (paramString.equals("bought_inapps")) {
            return j();
          }
          if (paramString.equals("inapp_sum")) {
            return h();
          }
          if (paramString.equals("inapp_sum_all_apps")) {
            return i();
          }
          if (paramString.equals("last_session_time")) {
            return c();
          }
          if (paramString.equals("platform")) {
            return "android";
          }
          if (paramString.equals("device_type"))
          {
            if (aj.l(this.c)) {
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
  
  private String a()
  {
    if ((this.e != null) && (this.e.has("interests"))) {
      return this.e.getString("interests");
    }
    return Appodeal.getUserSettings(this.c).getInterests();
  }
  
  public static void a(String paramString, Object paramObject)
  {
    if (b == null) {
      b = new HashMap();
    }
    b.put(paramString, paramObject);
  }
  
  private boolean a(d paramD, Object paramObject)
  {
    if (paramObject == null) {}
    do
    {
      do
      {
        return false;
        if (paramD.d == d.a.h)
        {
          paramD.d = d.a(paramObject);
          paramD.a();
        }
      } while (paramD.d == d.a.h);
      switch (1.b[paramD.b.ordinal()])
      {
      default: 
        return false;
      case 1: 
        return g(paramD, paramObject);
      case 2: 
        return f(paramD, paramObject);
      }
    } while (f(paramD, paramObject));
    return true;
    return d(paramD, paramObject);
    return e(paramD, paramObject);
    return c(paramD, paramObject);
    return b(paramD, paramObject);
  }
  
  private boolean a(e paramE)
  {
    switch (1.a[paramE.a.ordinal()])
    {
    default: 
      return false;
    case 1: 
      return a(paramE.b);
    }
    return b(paramE.b);
  }
  
  private boolean a(d[] paramArrayOfD)
  {
    boolean bool2 = false;
    int j = paramArrayOfD.length;
    int i = 0;
    while (i < j)
    {
      d localD = paramArrayOfD[i];
      bool1 = bool2;
      if (!a(localD, a(localD.a))) {
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
  
  private Integer b()
  {
    if ((this.e != null) && (this.e.has("age"))) {
      return Integer.valueOf(this.e.getInt("age"));
    }
    return Appodeal.getUserSettings(this.c).getAge();
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
  
  private boolean b(d paramD, Object paramObject)
  {
    return (f(paramD, paramObject)) || (e(paramD, paramObject));
  }
  
  private boolean b(d[] paramArrayOfD)
  {
    boolean bool2 = false;
    int j = paramArrayOfD.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        d localD = paramArrayOfD[i];
        if (a(localD, a(localD.a))) {
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
    long l = b.c();
    if (l == 0L) {
      return Integer.valueOf(0);
    }
    return Integer.valueOf((int)((System.currentTimeMillis() - l) / 60000L));
  }
  
  private boolean c(d paramD, Object paramObject)
  {
    return (f(paramD, paramObject)) || (d(paramD, paramObject));
  }
  
  private Integer d()
  {
    String str = aj.b(this.c).a;
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
  
  private boolean d(d paramD, Object paramObject)
  {
    boolean bool = true;
    if (paramD.d == d.a.f) {
      if (((Double)paramD.c).doubleValue() > ((Double)paramObject).doubleValue()) {
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
        if (paramD.d != d.a.d) {
          break;
        }
      } while (((Integer)paramD.c).intValue() > ((Integer)paramObject).intValue());
      return false;
      if (paramD.d != d.a.a) {
        break;
      }
    } while (((h)paramD.c).compareTo(paramObject) > 0);
    return false;
    return false;
  }
  
  private Integer e()
  {
    if ((this.e != null) && (this.e.has("occupation"))) {
      return Integer.valueOf(this.e.optInt("occupation"));
    }
    UserSettings.Occupation localOccupation = Appodeal.getUserSettings(this.c).getOccupation();
    if (localOccupation == null) {
      return Integer.valueOf(0);
    }
    return Integer.valueOf(localOccupation.getValue());
  }
  
  private boolean e(d paramD, Object paramObject)
  {
    boolean bool = true;
    if (paramD.d == d.a.f) {
      if (((Double)paramD.c).doubleValue() < ((Double)paramObject).doubleValue()) {
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
        if (paramD.d != d.a.d) {
          break;
        }
      } while (((Integer)paramD.c).intValue() < ((Integer)paramObject).intValue());
      return false;
      if (paramD.d != d.a.a) {
        break;
      }
    } while (((h)paramD.c).compareTo(paramObject) < 0);
    return false;
    return false;
  }
  
  private Integer f()
  {
    if ((this.e != null) && (this.e.has("relation"))) {
      return Integer.valueOf(this.e.getInt("relation"));
    }
    UserSettings.Relation localRelation = Appodeal.getUserSettings(this.c).getRelation();
    if (localRelation == null) {
      return Integer.valueOf(0);
    }
    return Integer.valueOf(localRelation.getValue());
  }
  
  private boolean f(d paramD, Object paramObject)
  {
    boolean bool = true;
    switch (1.c[paramD.d.ordinal()])
    {
    default: 
      bool = false;
    }
    do
    {
      do
      {
        return bool;
        if (((h)paramD.c).compareTo(paramObject) == 0) {}
        for (bool = true;; bool = false) {
          return bool;
        }
      } while ((paramObject != null) && (((String)paramObject).contains((String)paramD.c)));
      return false;
    } while ((paramObject != null) && (paramObject.equals(paramD.c)));
    return false;
  }
  
  private Integer g()
  {
    if ((this.e != null) && (this.e.has("gender"))) {
      return b(this.e.getString("gender"));
    }
    UserSettings.Gender localGender = Appodeal.getUserSettings(this.c).getGender();
    if (localGender == null) {
      return Integer.valueOf(0);
    }
    return Integer.valueOf(localGender.getValue());
  }
  
  private boolean g(d paramD, Object paramObject)
  {
    switch (1.c[paramD.d.ordinal()])
    {
    case 3: 
    case 4: 
    case 5: 
    default: 
      return false;
    case 2: 
      return ((String)paramObject).toLowerCase().contains(((String)paramD.c).toLowerCase());
    case 6: 
      return a((String[])paramD.c, (String)paramObject);
    }
    return a((Integer[])paramD.c, (Integer)paramObject);
  }
  
  private Double h()
  {
    if (this.d.has("inapp_sum")) {
      return Double.valueOf(this.d.getString("inapp_sum"));
    }
    return Double.valueOf(0.0D);
  }
  
  private Double i()
  {
    if (this.d.has("inapp_sum_all_apps")) {
      return Double.valueOf(this.d.getString("inapp_sum_all_apps"));
    }
    return Double.valueOf(0.0D);
  }
  
  private Boolean j()
  {
    try
    {
      if ((this.d.has("inapp_sum")) && (this.d.getDouble("inapp_sum") > 0.0D)) {
        return Boolean.valueOf(true);
      }
    }
    catch (Exception localException)
    {
      Appodeal.a(localException);
    }
    return Boolean.valueOf(false);
  }
  
  private Double k()
  {
    SharedPreferences localSharedPreferences = this.c.getSharedPreferences("appodeal", 0);
    long l = b.a(localSharedPreferences);
    return Double.valueOf(b.b(localSharedPreferences) / l / 60.0D);
  }
  
  private String l()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Object localObject = this.c.getPackageManager().getInstalledApplications(0);
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
  
  public e a(JSONArray paramJSONArray)
  {
    int i = 0;
    while (i < paramJSONArray.length()) {
      try
      {
        e localE = new e(paramJSONArray.getJSONObject(i));
        boolean bool = a(localE);
        if (bool) {
          return localE;
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
