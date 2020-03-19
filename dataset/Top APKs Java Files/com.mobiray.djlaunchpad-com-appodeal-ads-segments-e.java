package com.appodeal.ads.segments;

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
import com.appodeal.ads.utils.b;
import com.appodeal.ads.v;
import com.appodeal.ads.v.a;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public class e
{
  public static Long a = null;
  public static Map b;
  private final Context c;
  private final JSONObject d;
  private JSONObject e;
  
  public e(Context paramContext, JSONObject paramJSONObject)
  {
    this.d = paramJSONObject;
    if (UserSettings.userData != null) {}
    for (this.e = UserSettings.userData.optJSONObject("user_settings");; this.e = null)
    {
      this.c = paramContext;
      return;
    }
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
  
  private boolean a(d paramD)
  {
    switch (1.a[paramD.a.ordinal()])
    {
    default: 
      return false;
    case 1: 
      return a(paramD.b);
    }
    return b(paramD.b);
  }
  
  private boolean a(c[] paramArrayOfC)
  {
    boolean bool2 = false;
    int j = paramArrayOfC.length;
    int i = 0;
    while (i < j)
    {
      c localC = paramArrayOfC[i];
      bool1 = bool2;
      if (!a(localC, a(localC.a))) {
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
    return null;
  }
  
  private boolean b(c paramC, Object paramObject)
  {
    if (f(paramC, paramObject)) {
      return true;
    }
    return e(paramC, paramObject);
  }
  
  private boolean b(c[] paramArrayOfC)
  {
    boolean bool2 = false;
    int j = paramArrayOfC.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        c localC = paramArrayOfC[i];
        if (a(localC, a(localC.a))) {
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
  
  private boolean c(c paramC, Object paramObject)
  {
    if (f(paramC, paramObject)) {
      return true;
    }
    return d(paramC, paramObject);
  }
  
  private Integer d()
  {
    String str = v.b(this.c).a;
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
  
  private boolean d(c paramC, Object paramObject)
  {
    boolean bool = true;
    if (paramC.d == c.a.f) {
      if (((Double)paramC.c).doubleValue() > ((Double)paramObject).doubleValue()) {
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
        if (paramC.d != c.a.d) {
          break;
        }
      } while (((Integer)paramC.c).intValue() > ((Integer)paramObject).intValue());
      return false;
      if (paramC.d != c.a.a) {
        break;
      }
    } while (((g)paramC.c).compareTo(paramObject) > 0);
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
  
  private boolean e(c paramC, Object paramObject)
  {
    boolean bool = true;
    if (paramC.d == c.a.f) {
      if (((Double)paramC.c).doubleValue() < ((Double)paramObject).doubleValue()) {
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
        if (paramC.d != c.a.d) {
          break;
        }
      } while (((Integer)paramC.c).intValue() < ((Integer)paramObject).intValue());
      return false;
      if (paramC.d != c.a.a) {
        break;
      }
    } while (((g)paramC.c).compareTo(paramObject) < 0);
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
  
  private boolean f(c paramC, Object paramObject)
  {
    boolean bool = true;
    switch (1.c[paramC.d.ordinal()])
    {
    default: 
      bool = false;
    }
    do
    {
      do
      {
        return bool;
        if (((g)paramC.c).compareTo(paramObject) == 0) {}
        for (bool = true;; bool = false) {
          return bool;
        }
      } while ((paramObject != null) && (((String)paramObject).contains((String)paramC.c)));
      return false;
    } while ((paramObject != null) && (paramObject.equals(paramC.c)));
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
  
  private boolean g(c paramC, Object paramObject)
  {
    switch (1.c[paramC.d.ordinal()])
    {
    case 3: 
    case 4: 
    case 5: 
    default: 
      return false;
    case 2: 
      return ((String)paramObject).toLowerCase().contains(((String)paramC.c).toLowerCase());
    case 6: 
      return a((String[])paramC.c, (String)paramObject);
    }
    return a((Integer[])paramC.c, (Integer)paramObject);
  }
  
  private Double h()
  {
    if (this.d.has("inapp_sum")) {
      return Double.valueOf(this.d.getString("inapp_sum"));
    }
    return null;
  }
  
  private Double i()
  {
    if (this.d.has("inapp_sum_all_apps")) {
      return Double.valueOf(this.d.getString("inapp_sum_all_apps"));
    }
    return null;
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
  
  public d a(JSONArray paramJSONArray)
  {
    int i = 0;
    while (i < paramJSONArray.length()) {
      try
      {
        d localD = new d(paramJSONArray.getJSONObject(i));
        boolean bool = a(localD);
        if (bool) {
          return localD;
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
  
  public Object a(String paramString)
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
            return new g(this.c.getPackageManager().getPackageInfo(this.c.getPackageName(), 0).versionName);
          }
          if (paramString.equals("sdk_version")) {
            return new g("1.13.10");
          }
          if (paramString.equals("android_version")) {
            return new g(Build.VERSION.RELEASE);
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
          if ((b != null) && (b.containsKey(paramString)))
          {
            paramString = b.get(paramString);
            return paramString;
          }
        }
      }
      catch (Exception paramString)
      {
        Appodeal.a(paramString);
      }
    }
    return null;
  }
  
  boolean a(c paramC, Object paramObject)
  {
    if (paramObject == null) {}
    do
    {
      do
      {
        return false;
        if (paramC.d == c.a.h)
        {
          paramC.d = c.a(paramObject);
          paramC.a();
        }
      } while (paramC.d == c.a.h);
      switch (1.b[paramC.b.ordinal()])
      {
      default: 
        return false;
      case 1: 
        return g(paramC, paramObject);
      case 2: 
        return f(paramC, paramObject);
      }
    } while (f(paramC, paramObject));
    return true;
    return d(paramC, paramObject);
    return e(paramC, paramObject);
    return c(paramC, paramObject);
    return b(paramC, paramObject);
  }
}
