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
  private static final byte[] $ = { 114, -95, -7, -32, 13, -2, 4, -4, -4, 7, -12, -1, 11 };
  private static int $$ = 166;
  private static f a;
  private static Map<String, Object> b;
  private static JSONArray c;
  private static int d = -1;
  private static int e = 0;
  private static double k;
  private static double l;
  private static boolean m;
  private final Context f;
  private final JSONObject g;
  private final Double h;
  private final Double i;
  private final boolean j;
  
  private static String $(int paramInt, byte paramByte1, byte paramByte2)
  {
    byte b1 = 0;
    byte b3 = 0;
    paramInt = 103 - paramInt * 6;
    byte b2 = paramByte1 * 3 + 3;
    byte[] arrayOfByte1 = $;
    paramByte1 = 8 - paramByte2 * 2;
    byte[] arrayOfByte2 = new byte[b2];
    paramByte2 = paramInt;
    paramInt = paramByte1;
    if (arrayOfByte1 == null)
    {
      paramByte2 = b2;
      b1 = paramByte1;
      paramInt = paramByte1;
      paramByte1 = b3;
    }
    for (;;)
    {
      paramByte2 = paramByte2 + b1 + 2;
      paramInt += 1;
      b1 = paramByte1;
      paramByte1 = b1 + 1;
      arrayOfByte2[b1] = ((byte)paramByte2);
      if (paramByte1 == b2) {
        return new String(arrayOfByte2, 0);
      }
      b1 = arrayOfByte1[paramInt];
    }
  }
  
  g(Context paramContext, double paramDouble1, double paramDouble2, boolean paramBoolean)
  {
    if (UserSettings.userData != null) {
      this.g = UserSettings.userData.optJSONObject("user_settings");
    } else {
      this.g = null;
    }
    this.f = paramContext;
    this.h = Double.valueOf(paramDouble1);
    this.i = Double.valueOf(paramDouble2);
    this.j = paramBoolean;
  }
  
  public g(Context paramContext, JSONObject paramJSONObject)
  {
    if (UserSettings.userData != null) {
      this.g = UserSettings.userData.optJSONObject("user_settings");
    } else {
      this.g = null;
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
        paramString = UserSettings.userData.optString("country_id");
        return paramString;
      }
      if (paramString.equals("app_version"))
      {
        paramString = new h(this.f.getPackageManager().getPackageInfo(this.f.getPackageName(), 0).versionName);
        return paramString;
      }
      int n = (byte)-$[11];
      if (paramString.equals($(n, (byte)(n - 1), (byte)-$[5]).intern()))
      {
        paramString = this.f.getSharedPreferences("appodeal", 0).getString("appKey", null);
        return paramString;
      }
      if (paramString.equals("sdk_version"))
      {
        paramString = new h("1.15.8");
        return paramString;
      }
      if (paramString.equals("android_version"))
      {
        paramString = new h(Build.VERSION.RELEASE);
        return paramString;
      }
      if (paramString.equals("has_app_installed"))
      {
        paramString = r();
        return paramString;
      }
      if (paramString.equals("session_count"))
      {
        n = (int)d.a(this.f.getSharedPreferences("appodeal", 0));
        return Integer.valueOf(n);
      }
      if (paramString.equals("average_session_length"))
      {
        paramString = q();
        return paramString;
      }
      if (paramString.equals("device_model"))
      {
        paramString = String.format("%s %s", new Object[] { Build.MANUFACTURER, Build.MODEL });
        return paramString;
      }
      if (paramString.equals("connection_type"))
      {
        paramString = j();
        return paramString;
      }
      n = (byte)($[11] + 1);
      byte b1 = (byte)-$[11];
      if (paramString.equals($(n, b1, (byte)(b1 - 1)).intern()))
      {
        paramString = m();
        return paramString;
      }
      n = (byte)-$[11];
      if (paramString.equals($(n, (byte)(n - 1), (byte)-$[11]).intern()))
      {
        paramString = h();
        return paramString;
      }
      if (paramString.equals("occupation"))
      {
        paramString = k();
        return paramString;
      }
      if (paramString.equals("relation"))
      {
        paramString = l();
        return paramString;
      }
      if (paramString.equals("interests"))
      {
        paramString = g();
        return paramString;
      }
      if (paramString.equals("bought_inapps"))
      {
        paramString = p();
        return paramString;
      }
      if (paramString.equals("inapp_sum"))
      {
        paramString = n();
        return paramString;
      }
      if (paramString.equals("inapp_sum_all_apps"))
      {
        paramString = o();
        return paramString;
      }
      if (paramString.equals("last_session_time"))
      {
        paramString = i();
        return paramString;
      }
      boolean bool = paramString.equals("platform");
      if (bool) {
        return "android";
      }
      if (paramString.equals("device_type"))
      {
        bool = an.n(this.f);
        if (bool) {
          return "tablet";
        }
        return "phone";
      }
      if ((b != null) && (b.containsKey(paramString)))
      {
        paramString = b.get(paramString);
        return paramString;
      }
      if (paramString.equals("day"))
      {
        e = c();
        n = e;
        return Integer.valueOf(n);
      }
      if (paramString.equals("hour"))
      {
        d = b();
        n = d;
        return Integer.valueOf(n);
      }
    }
    catch (Exception paramString)
    {
      Appodeal.a(paramString);
    }
    return null;
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
      break;
    case 1: 
      return g(paramE, paramObject);
    case 2: 
      return f(paramE, paramObject);
    case 3: 
      return !f(paramE, paramObject);
    case 4: 
      return d(paramE, paramObject);
    case 5: 
      return e(paramE, paramObject);
    case 6: 
      return c(paramE, paramObject);
    case 7: 
      return b(paramE, paramObject);
    }
    return false;
  }
  
  private boolean a(e[] paramArrayOfE)
  {
    int i1 = paramArrayOfE.length;
    int n = 0;
    while (n < i1)
    {
      e localE = paramArrayOfE[n];
      if (!a(localE, a(localE.a))) {
        return false;
      }
      n += 1;
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
      break;
    case 1: 
      return a(paramF.b);
    case 2: 
      return b(paramF.b);
    }
    return false;
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
      break;
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
    case 7: 
      return 6;
    case 1: 
      return 7;
    }
    return 0;
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
      }
      return;
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
      break;
    case 1: 
      return ((h)paramE.c).compareTo(paramObject) == 0;
    case 2: 
      return (paramObject != null) && (((String)paramObject).contains((String)paramE.c));
    case 3: 
    case 4: 
    case 5: 
      return (paramObject != null) && (paramObject.equals(paramE.c));
    }
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
    default: 
      break;
    case 2: 
      return ((String)paramObject).toLowerCase().contains(((String)paramE.c).toLowerCase());
    case 6: 
      return a((String[])paramE.c, (String)paramObject);
    case 7: 
      return a((Integer[])paramE.c, (Integer)paramObject);
    }
    return false;
  }
  
  private Integer h()
  {
    if (this.g != null)
    {
      JSONObject localJSONObject = this.g;
      int n = (byte)-$[11];
      if (localJSONObject.has($(n, (byte)(n - 1), (byte)-$[11]).intern()))
      {
        localJSONObject = this.g;
        n = (byte)-$[11];
        return Integer.valueOf(localJSONObject.getInt($(n, (byte)(n - 1), (byte)-$[11]).intern()));
      }
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
    if (this.g != null)
    {
      localObject = this.g;
      int n = (byte)($[11] + 1);
      byte b1 = (byte)-$[11];
      if (((JSONObject)localObject).has($(n, b1, (byte)(b1 - 1)).intern()))
      {
        localObject = this.g;
        n = (byte)($[11] + 1);
        b1 = (byte)-$[11];
        return b(((JSONObject)localObject).getString($(n, b1, (byte)(b1 - 1)).intern()));
      }
    }
    Object localObject = Appodeal.getUserSettings(this.f).getGender();
    if (localObject == null) {
      return Integer.valueOf(0);
    }
    return Integer.valueOf(((UserSettings.Gender)localObject).getValue());
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
  
  public f a(JSONArray paramJSONArray)
  {
    int n = 0;
    while (n < paramJSONArray.length())
    {
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
      }
      n += 1;
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
