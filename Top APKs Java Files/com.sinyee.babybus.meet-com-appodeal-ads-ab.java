package com.appodeal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.as;
import android.util.Pair;
import com.appodeal.ads.f.d;
import com.appodeal.ads.utils.af;
import com.appodeal.ads.utils.f;
import com.appodeal.ads.utils.j;
import com.appodeal.ads.utils.p;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ab
{
  private static SSLSocketFactory F;
  @as
  static JSONObject s;
  @as
  boolean A;
  @as
  boolean B;
  @as
  String C;
  @as
  double D;
  private final JSONObject E;
  private final Handler G;
  private final int H;
  private final int I;
  private boolean J;
  @as
  final a a;
  @as
  final Context b;
  @as
  final int c;
  @as
  final String d;
  @as
  final String e;
  @as
  String f;
  @as
  String g;
  @as
  final com.appodeal.ads.d.h h;
  @as
  final d i;
  @as
  long j;
  @as
  final String k;
  @as
  Long l;
  @as
  final int m;
  @as
  final double n;
  @as
  final boolean o;
  @as
  String p;
  @as
  JSONObject q;
  @as
  String r;
  @as
  boolean t;
  @as
  boolean u;
  @as
  boolean v;
  @as
  boolean w;
  @as
  boolean x;
  @as
  boolean y;
  @as
  boolean z;
  
  private ab(c paramC)
  {
    boolean bool2 = false;
    this.H = 0;
    this.I = 1;
    this.a = c.a(paramC);
    this.b = c.b(paramC);
    this.c = c.c(paramC);
    this.d = c.d(paramC);
    this.e = c.e(paramC);
    this.h = c.f(paramC);
    this.i = c.g(paramC);
    this.k = c.h(paramC);
    this.m = c.i(paramC);
    this.n = c.j(paramC);
    this.o = c.k(paramC);
    this.C = c.l(paramC);
    this.D = c.m(paramC);
    this.E = c.n(paramC);
    if (c.o(paramC) != null)
    {
      this.q = c.o(paramC).F;
      String str;
      if ((c.o(paramC).v) && (c.o(paramC).w == null)) {
        str = c.o(paramC).E;
      } else {
        str = null;
      }
      this.p = str;
      this.l = c.o(paramC).C;
      this.j = c.o(paramC).i();
      this.g = c.o(paramC).w;
      this.f = c.o(paramC).m;
      this.r = c.o(paramC).l();
    }
    this.G = new Handler(Looper.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (ab.this.a != null)
        {
          switch (paramAnonymousMessage.what)
          {
          default: 
            return;
          case 1: 
            paramAnonymousMessage = (JSONObject)paramAnonymousMessage.obj;
            if (paramAnonymousMessage != null)
            {
              ab.this.a.a(paramAnonymousMessage, ab.this.c, ab.this.d);
              return;
            }
            break;
          }
          ab.this.a.a(ab.this.c);
        }
      }
    };
    if (this.d == null) {
      return;
    }
    if ((k.a) && ((this.a == null) || ((this.a instanceof af))) && ((this.d.equals("init")) || (this.d.equals("stats")) || (this.d.equals("show")) || (this.d.equals("click")) || (this.d.equals("finish")) || (this.d.equals("install")))) {
      return;
    }
    if ((!this.d.equals("banner")) && (!this.d.equals("debug"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.t = bool1;
    if ((!this.d.equals("banner_320")) && (!this.d.equals("debug_banner_320"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.u = bool1;
    if ((!this.d.equals("banner_mrec")) && (!this.d.equals("debug_mrec"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.v = bool1;
    if ((!this.d.equals("video")) && (!this.d.equals("debug_video"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.w = bool1;
    if ((!this.d.equals("rewarded_video")) && (!this.d.equals("debug_rewarded_video"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.x = bool1;
    if ((!this.d.equals("native")) && (!this.d.equals("debug_native"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.y = bool1;
    if ((!this.d.equals("debug")) && (!this.d.equals("debug_banner_320")) && (!this.d.equals("debug_video")) && (!this.d.equals("debug_rewarded_video")) && (!this.d.equals("debug_mrec")) && (!this.d.equals("debug_native"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.z = bool1;
    if ((!this.t) && (!this.u) && (!this.v) && (!this.w) && (!this.x) && (!this.y)) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.A = bool1;
    boolean bool1 = bool2;
    if (!this.d.equals("init"))
    {
      bool1 = bool2;
      if (!this.d.equals("stats"))
      {
        bool1 = bool2;
        if (!this.d.equals("show"))
        {
          bool1 = bool2;
          if (!this.d.equals("click"))
          {
            bool1 = bool2;
            if (!this.d.equals("finish"))
            {
              bool1 = bool2;
              if (!this.d.equals("install"))
              {
                bool1 = bool2;
                if (!this.d.equals("iap")) {
                  bool1 = true;
                }
              }
            }
          }
        }
      }
    }
    this.B = bool1;
    this.J = true;
  }
  
  private String a(String paramString)
  {
    return String.format("%s_timestamp", new Object[] { paramString });
  }
  
  private void a(JSONObject paramJSONObject)
  {
    JSONArray localJSONArray = paramJSONObject.optJSONArray("segments");
    if (localJSONArray != null)
    {
      if (localJSONArray.length() == 0) {
        return;
      }
      paramJSONObject = new com.appodeal.ads.f.h(this.b, paramJSONObject.optJSONObject("user_data"));
      paramJSONObject.b(localJSONArray);
      paramJSONObject = paramJSONObject.a(localJSONArray);
      if (paramJSONObject == null)
      {
        com.appodeal.ads.f.h.c();
        return;
      }
      if (paramJSONObject.c() != com.appodeal.ads.f.h.a().c())
      {
        try
        {
          paramJSONObject.a();
        }
        catch (JSONException localJSONException)
        {
          Appodeal.a(localJSONException);
        }
        com.appodeal.ads.f.h.a(paramJSONObject);
      }
    }
  }
  
  private String b(String paramString)
  {
    return String.format("%s_wst", new Object[] { paramString });
  }
  
  @android.support.annotation.ae
  private JSONObject c(SharedPreferences paramSharedPreferences)
  {
    for (;;)
    {
      try
      {
        Object localObject2;
        Object localObject1;
        if ((s == null) || (s.length() == 0))
        {
          s = new JSONObject();
          localObject2 = this.b.getPackageManager();
          paramSharedPreferences = paramSharedPreferences.getString("appKey", null);
          if (paramSharedPreferences == null) {
            return null;
          }
          s.put("app_key", paramSharedPreferences);
          s.put("android", Build.VERSION.RELEASE);
          s.put("android_level", Build.VERSION.SDK_INT);
          s.put("sdk", "2.4.2");
          String str = this.b.getPackageName();
          s.put("package", str);
          try
          {
            paramSharedPreferences = ((PackageManager)localObject2).getPackageInfo(str, 0);
            s.put("package_version", paramSharedPreferences.versionName);
            s.put("package_code", paramSharedPreferences.versionCode);
            s.put("install_time", paramSharedPreferences.firstInstallTime / 1000L);
            paramSharedPreferences = ((PackageManager)localObject2).getApplicationInfo(str, 0);
            s.put("target_sdk_version", paramSharedPreferences.targetSdkVersion);
          }
          catch (PackageManager.NameNotFoundException paramSharedPreferences)
          {
            Appodeal.a(paramSharedPreferences);
          }
          if (Appodeal.l != null) {
            s.put("framework", Appodeal.l);
          }
          if (Appodeal.m != null) {
            s.put("plugin_version", Appodeal.m);
          }
          paramSharedPreferences = bf.f(this.b);
          s.put("width", paramSharedPreferences.first);
          s.put("height", paramSharedPreferences.second);
          s.put("pxratio", bf.i(this.b));
          if (bf.n(this.b))
          {
            paramSharedPreferences = s;
            localObject1 = "tablet";
            paramSharedPreferences.put("device_type", localObject1);
          }
          else
          {
            paramSharedPreferences = s;
            localObject1 = "phone";
            continue;
          }
          if (Build.MANUFACTURER.equals("Amazon"))
          {
            paramSharedPreferences = "amazon";
            s.put("platform", paramSharedPreferences);
            try
            {
              localObject1 = ((PackageManager)localObject2).getInstallerPackageName(str);
              paramSharedPreferences = (SharedPreferences)localObject1;
              if (localObject1 == null) {
                paramSharedPreferences = "unknown";
              }
              s.put("installer", paramSharedPreferences);
            }
            catch (Exception paramSharedPreferences)
            {
              Appodeal.a(paramSharedPreferences);
            }
            s.put("manufacturer", Build.MANUFACTURER);
            s.put("rooted", bf.a());
            s.put("webview_version", bf.t(this.b));
            s.put("multidex", bf.b(new String[] { "android.support.multidex.MultiDex" }));
          }
        }
        else
        {
          paramSharedPreferences = new JSONObject();
          localObject1 = s.keys();
          if (((Iterator)localObject1).hasNext())
          {
            localObject2 = (String)((Iterator)localObject1).next();
            paramSharedPreferences.put((String)localObject2, s.get((String)localObject2));
            continue;
          }
          return paramSharedPreferences;
        }
      }
      finally {}
      paramSharedPreferences = "google";
    }
  }
  
  private SSLSocketFactory d()
  {
    try
    {
      if (F == null)
      {
        localObject1 = new ArrayList();
        ((List)localObject1).add("A1ABC1296E644B3A25179FCD3E277C8D36039BEE94478E2F5104FA4244237F54");
        ((List)localObject1).add("E91093227F02CE854C3214749DC7FB3459E0E43E80CAE27F01AA0EA92894C9E1");
        localObject1 = new com.appodeal.ads.utils.g((List)localObject1, 1526342400000L);
        SSLContext localSSLContext = SSLContext.getInstance("TLSv1");
        localSSLContext.init(null, new TrustManager[] { localObject1 }, null);
        F = localSSLContext.getSocketFactory();
      }
      Object localObject1 = F;
      return localObject1;
    }
    finally {}
  }
  
  @android.support.annotation.ae
  JSONObject a(SharedPreferences paramSharedPreferences)
  {
    if (paramSharedPreferences == null) {
      return null;
    }
    String str1 = paramSharedPreferences.getString("appKey", null);
    if (str1 == null) {
      return null;
    }
    String str2 = az.h();
    if (az.g()) {
      paramSharedPreferences = "0";
    } else {
      paramSharedPreferences = "1";
    }
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("app_key", str1);
    localJSONObject.put("sdk", "2.4.2");
    localJSONObject.put("package", this.b.getPackageName());
    localJSONObject.put("framework", Appodeal.l);
    localJSONObject.put("android_id", str2);
    localJSONObject.put("advertising_tracking", paramSharedPreferences);
    if (Build.MANUFACTURER.equals("Amazon")) {
      paramSharedPreferences = "amazon";
    } else {
      paramSharedPreferences = "google";
    }
    localJSONObject.put("platform", paramSharedPreferences);
    localJSONObject.put("consent", az.f());
    localJSONObject.put("fingerprint", az.i());
    return localJSONObject;
  }
  
  public void a()
  {
    if (this.J) {
      com.appodeal.ads.utils.t.a.execute(new b(null));
    }
  }
  
  @as
  void a(SharedPreferences paramSharedPreferences, int paramInt, String paramString)
  {
    paramSharedPreferences = paramSharedPreferences.edit();
    paramSharedPreferences.putInt(b(paramString), paramInt);
    paramSharedPreferences.apply();
  }
  
  @as
  void a(SharedPreferences paramSharedPreferences, String paramString1, String paramString2)
  {
    paramSharedPreferences = paramSharedPreferences.edit();
    paramSharedPreferences.putString(paramString1, paramString2);
    paramSharedPreferences.putLong(a(paramString1), System.currentTimeMillis());
    paramSharedPreferences.apply();
  }
  
  @as
  boolean a(SharedPreferences paramSharedPreferences, String paramString)
  {
    long l1 = paramSharedPreferences.getLong(a(paramString), 0L);
    if (System.currentTimeMillis() - l1 > b(paramSharedPreferences, paramString))
    {
      paramSharedPreferences = paramSharedPreferences.edit();
      paramSharedPreferences.remove(paramString);
      paramSharedPreferences.remove(a(paramString));
      paramSharedPreferences.remove(b(paramString));
      paramSharedPreferences.apply();
      return false;
    }
    return true;
  }
  
  @as
  int b(SharedPreferences paramSharedPreferences, String paramString)
  {
    return paramSharedPreferences.getInt(b(paramString), 86400000);
  }
  
  URL b()
  {
    if (this.B)
    {
      if (this.o) {
        return bf.f(this.d);
      }
      return new URL(j.a("get"));
    }
    return new URL(j.a(this.d));
  }
  
  @android.support.annotation.ae
  @as
  JSONObject b(SharedPreferences paramSharedPreferences)
  {
    JSONObject localJSONObject = c(paramSharedPreferences);
    if (localJSONObject == null) {
      return null;
    }
    Object localObject3 = az.h();
    Object localObject1;
    if (az.g()) {
      localObject1 = "0";
    } else {
      localObject1 = "1";
    }
    localJSONObject.put("android_id", localObject3);
    localJSONObject.put("advertising_tracking", localObject1);
    try
    {
      if (this.t) {
        localJSONObject.put("type", "banner");
      }
      if (this.u)
      {
        localJSONObject.put("type", "banner_320");
        float f1 = bf.g(this.b);
        float f2 = bf.h(this.b);
        if ((l.q) && (f1 >= 728.0F) && (f2 > 720.0F)) {
          localJSONObject.put("large_banners", true);
        }
      }
      if (this.v) {
        localJSONObject.put("type", "banner_mrec");
      }
      if ((this.w) || (this.x)) {
        localJSONObject.put("type", "video");
      }
      if (this.x) {
        localJSONObject.put("rewarded_video", true);
      }
      if (this.y) {
        localJSONObject.put("type", "native");
      }
      if (this.z) {
        localJSONObject.put("debug", true);
      }
      if (k.a) {
        localJSONObject.put("test", true);
      }
      localJSONObject.put("battery", bf.k(this.b));
      localJSONObject.put("crr", bf.c(this.b));
      localJSONObject.put("locale", Locale.getDefault().toString());
      localObject1 = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.ENGLISH);
      localJSONObject.put("timezone", new SimpleDateFormat("Z", Locale.ENGLISH).format(((Calendar)localObject1).getTime()));
      localJSONObject.put("local_time", System.currentTimeMillis() / 1000L);
      localJSONObject.put("user_agent", System.getProperty("http.agent"));
      f.c(this.b);
      localJSONObject.put("session_id", f.a(paramSharedPreferences));
      localJSONObject.put("session_uptime", f.b());
      localJSONObject.put("app_uptime", f.b(paramSharedPreferences));
      localJSONObject.put("consent", az.f());
      localJSONObject.put("fingerprint", az.b());
      if (!h.h)
      {
        localObject1 = bf.b(this.b);
        localJSONObject.put("connection", ((bf.a)localObject1).a);
        localJSONObject.put("connection_subtype", ((bf.a)localObject1).b);
        localJSONObject.put("connection_fast", ((bf.a)localObject1).c);
        localObject1 = bf.d(this.b);
        localJSONObject.put("lt", ((Pair)localObject1).first);
        localJSONObject.put("lat", ((Pair)((Pair)localObject1).second).first);
        localJSONObject.put("lon", ((Pair)((Pair)localObject1).second).second);
        localJSONObject.put("model", String.format("%s %s", new Object[] { Build.MANUFACTURER, Build.MODEL }));
      }
      localJSONObject.put("coppa", h.h);
      if (this.d.equals("iap"))
      {
        localJSONObject.put("currency", this.C);
        localJSONObject.put("amount", this.D);
      }
      if (this.A)
      {
        localObject1 = new JSONArray();
        if (this.t) {
          localObject1 = new JSONArray(t.b().b());
        }
        if (this.w) {
          localObject1 = new JSONArray(au.b().b());
        }
        if (this.x) {
          localObject1 = new JSONArray(ba.b().b());
        }
        if (this.u) {
          localObject1 = new JSONArray(l.b().b());
        }
        if (this.v) {
          localObject1 = new JSONArray(ae.b().b());
        }
        if (this.y) {
          localObject1 = new JSONArray(Native.b().b());
        }
        localJSONObject.put("show_array", localObject1);
      }
      if (this.g != null) {
        localJSONObject.put("loaded_offer", this.g);
      }
      if (this.p != null) {
        localJSONObject.put("last_offer", this.p);
      }
      if ((this.l != null) && (this.l.longValue() != -1L)) {
        localJSONObject.put("segment_id", this.l);
      }
      if (this.j != 0L) {
        localJSONObject.put("show_timestamp", this.j);
      }
      if (this.d.equals("click")) {
        localJSONObject.put("click_timestamp", System.currentTimeMillis() / 1000L);
      }
      if (this.d.equals("finish")) {
        localJSONObject.put("finish_timestamp", System.currentTimeMillis() / 1000L);
      }
      if (this.m > 1) {
        localJSONObject.put("capacity", this.m);
      }
      if (this.n > 0.0D) {
        localJSONObject.put("price_floor", this.n);
      }
      if (this.q != null) {
        localJSONObject.put("ad_properties", this.q);
      }
      if (this.r != null) {
        localJSONObject.put("impid", this.r);
      }
      localJSONObject.put("id", this.e);
      localJSONObject.put("main_id", this.f);
      if ((this.A) || (this.k != null)) {
        localJSONObject.put("ad_stats", c());
      }
      if ((this.A) && (k.o == null)) {
        localJSONObject.put("check_sdk_version", true);
      }
      if (this.i != null) {
        localJSONObject.put("placement_id", this.i.b());
      }
      if ((this.E != null) && (this.d.equals("stats"))) {
        localJSONObject.put("ad_unit_stat", this.E);
      }
      if (ExtraData.a().length() > 0) {
        localJSONObject.put("ext", ExtraData.a());
      }
    }
    catch (JSONException localJSONException)
    {
      Appodeal.a(localJSONException);
    }
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramSharedPreferences.getLong("lastSettingsTime", 0L));
    localCalendar.add(5, 1);
    if ((!UserSettings.a) && (this.A) && ((localCalendar.getTimeInMillis() < System.currentTimeMillis()) || (paramSharedPreferences.getBoolean("should_update_user_settings", true))))
    {
      UserSettings.a = true;
      try
      {
        localJSONObject.put("sa", com.appodeal.ads.utils.ab.a(this.b));
      }
      catch (Exception localException1)
      {
        Appodeal.a(localException1);
      }
      if (!h.h) {
        try
        {
          localJSONObject.put("user_settings", bf.u(this.b).k());
        }
        catch (Exception localException2)
        {
          Appodeal.a(localException2);
        }
      }
      localObject2 = paramSharedPreferences.edit();
      ((SharedPreferences.Editor)localObject2).putLong("lastSettingsTime", System.currentTimeMillis());
      ((SharedPreferences.Editor)localObject2).putBoolean("should_update_user_settings", false);
      ((SharedPreferences.Editor)localObject2).apply();
      UserSettings.a = false;
    }
    Object localObject2 = Calendar.getInstance();
    ((Calendar)localObject2).setTimeInMillis(paramSharedPreferences.getLong("lastAppTime", 0L));
    ((Calendar)localObject2).add(5, 1);
    if ((!k.l) && (k.k) && (this.A) && (((Calendar)localObject2).getTimeInMillis() < System.currentTimeMillis()))
    {
      k.l = true;
      try
      {
        localObject2 = new JSONArray();
        Object localObject4 = this.b.getPackageManager().getInstalledApplications(0);
        localObject3 = Pattern.compile("^?(?:com\\.android|com\\.google|com\\.sec|com\\.samsung|com\\.sonyericsson|com\\.sonymobile|com\\.motorola|com\\.htc).*$");
        if (localObject4 != null)
        {
          localObject4 = ((List)localObject4).iterator();
          while (((Iterator)localObject4).hasNext())
          {
            String str2 = ((ApplicationInfo)((Iterator)localObject4).next()).packageName;
            if ((!((Pattern)localObject3).matcher(str2).matches()) && (!str2.equals("android"))) {
              ((JSONArray)localObject2).put(str2);
            }
          }
        }
        localJSONObject.put("apps", localObject2);
      }
      catch (Exception localException3)
      {
        Appodeal.a(localException3);
      }
      paramSharedPreferences = paramSharedPreferences.edit();
      paramSharedPreferences.putLong("lastAppTime", System.currentTimeMillis());
      paramSharedPreferences.apply();
      k.l = false;
    }
    if (this.h != null)
    {
      String str1;
      if (this.d.equals("stats"))
      {
        paramSharedPreferences = "id";
        str1 = this.h.a().toString();
        localJSONObject.put(paramSharedPreferences, str1);
      }
      else if (this.d.equals("show"))
      {
        if (this.h.e())
        {
          if (!this.h.c())
          {
            Appodeal.a("/get error, rtb invalid check");
            return null;
          }
          localJSONObject.put("rtb_check", this.h.b());
        }
      }
      else
      {
        for (paramSharedPreferences = "bidder_id";; paramSharedPreferences = "id")
        {
          str1 = this.h.d();
          break;
          if (((!this.d.equals("click")) && (!this.d.equals("finish"))) || (!this.h.e())) {
            break label1754;
          }
        }
      }
    }
    label1754:
    az.a(localJSONObject);
    return localJSONObject;
  }
  
  @as
  JSONObject c()
  {
    JSONObject localJSONObject = new JSONObject();
    int i1 = p.a();
    int i2 = p.b();
    int i3 = p.c();
    try
    {
      localJSONObject.put("show", i1);
      localJSONObject.put("click", i2);
      if ((this.w) || (this.x) || ((this.k != null) && ((this.k.equals("video")) || (this.k.equals("rewarded_video"))))) {
        localJSONObject.put("finish", i3);
      }
      if ((this.t) || ((this.k != null) && (this.k.equals("banner"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "show" }), p.a("interstitial"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "click" }), p.b("interstitial"));
      }
      if ((this.w) || ((this.k != null) && (this.k.equals("video"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "show" }), p.a("video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "click" }), p.b("video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "finish" }), p.c("video"));
      }
      if ((this.x) || ((this.k != null) && (this.k.equals("rewarded_video"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "show" }), p.a("rewarded_video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "click" }), p.b("rewarded_video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "finish" }), p.c("rewarded_video"));
      }
      if ((this.u) || ((this.k != null) && (this.k.equals("banner_320"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "show" }), p.a("banner"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "click" }), p.b("banner"));
      }
      if ((this.v) || ((this.k != null) && (this.k.equals("banner_mrec"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "show" }), p.a("mrec"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "click" }), p.b("mrec"));
      }
      if ((this.y) || ((this.k != null) && (this.k.equals("native"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "native", "show" }), p.a("native"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "native", "click" }), p.b("native"));
        return localJSONObject;
      }
    }
    catch (Exception localException)
    {
      Appodeal.a(localException);
    }
    return localJSONObject;
  }
  
  public static abstract interface a
  {
    public abstract void a(int paramInt);
    
    public abstract void a(JSONObject paramJSONObject, int paramInt, String paramString);
  }
  
  private class b
    implements Runnable
  {
    private b() {}
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   4: getfield 32	com/appodeal/ads/ab:b	Landroid/content/Context;
      //   7: ldc 34
      //   9: iconst_0
      //   10: invokevirtual 40	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   13: astore_2
      //   14: aload_0
      //   15: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   18: getfield 44	com/appodeal/ads/ab:d	Ljava/lang/String;
      //   21: ldc 46
      //   23: invokevirtual 52	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   26: ifeq +15 -> 41
      //   29: aload_0
      //   30: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   33: aload_2
      //   34: invokevirtual 55	com/appodeal/ads/ab:a	(Landroid/content/SharedPreferences;)Lorg/json/JSONObject;
      //   37: astore_2
      //   38: goto +12 -> 50
      //   41: aload_0
      //   42: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   45: aload_2
      //   46: invokevirtual 57	com/appodeal/ads/ab:b	(Landroid/content/SharedPreferences;)Lorg/json/JSONObject;
      //   49: astore_2
      //   50: aload_2
      //   51: ifnonnull +16 -> 67
      //   54: aload_0
      //   55: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   58: invokestatic 60	com/appodeal/ads/ab:a	(Lcom/appodeal/ads/ab;)Landroid/os/Handler;
      //   61: iconst_0
      //   62: invokevirtual 66	android/os/Handler:sendEmptyMessage	(I)Z
      //   65: pop
      //   66: return
      //   67: aload_0
      //   68: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   71: invokevirtual 69	com/appodeal/ads/ab:b	()Ljava/net/URL;
      //   74: astore 9
      //   76: aload_0
      //   77: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   80: getfield 32	com/appodeal/ads/ab:b	Landroid/content/Context;
      //   83: ldc 71
      //   85: iconst_0
      //   86: invokevirtual 40	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   89: astore 10
      //   91: aconst_null
      //   92: astore 6
      //   94: aconst_null
      //   95: astore 7
      //   97: aconst_null
      //   98: astore 8
      //   100: aconst_null
      //   101: astore 5
      //   103: aload 9
      //   105: invokevirtual 77	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   108: astore 4
      //   110: aload 4
      //   112: astore_3
      //   113: aload 9
      //   115: invokevirtual 81	java/net/URL:getProtocol	()Ljava/lang/String;
      //   118: ldc 83
      //   120: invokevirtual 52	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   123: ifeq +21 -> 144
      //   126: aload 4
      //   128: astore_3
      //   129: aload 4
      //   131: checkcast 85	javax/net/ssl/HttpsURLConnection
      //   134: aload_0
      //   135: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   138: invokestatic 88	com/appodeal/ads/ab:b	(Lcom/appodeal/ads/ab;)Ljavax/net/ssl/SSLSocketFactory;
      //   141: invokevirtual 92	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
      //   144: aload 4
      //   146: astore_3
      //   147: aload_0
      //   148: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   151: getfield 96	com/appodeal/ads/ab:B	Z
      //   154: ifeq +48 -> 202
      //   157: aload 4
      //   159: astore_3
      //   160: aload 10
      //   162: aload_0
      //   163: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   166: getfield 44	com/appodeal/ads/ab:d	Ljava/lang/String;
      //   169: invokeinterface 102 2 0
      //   174: ifeq +28 -> 202
      //   177: aload 4
      //   179: astore_3
      //   180: aload 4
      //   182: sipush 10000
      //   185: invokevirtual 108	java/net/URLConnection:setConnectTimeout	(I)V
      //   188: aload 4
      //   190: astore_3
      //   191: aload 4
      //   193: sipush 10000
      //   196: invokevirtual 111	java/net/URLConnection:setReadTimeout	(I)V
      //   199: goto +25 -> 224
      //   202: aload 4
      //   204: astore_3
      //   205: aload 4
      //   207: sipush 20000
      //   210: invokevirtual 108	java/net/URLConnection:setConnectTimeout	(I)V
      //   213: aload 4
      //   215: astore_3
      //   216: aload 4
      //   218: sipush 20000
      //   221: invokevirtual 111	java/net/URLConnection:setReadTimeout	(I)V
      //   224: aload 4
      //   226: astore_3
      //   227: aload 4
      //   229: sipush 20000
      //   232: invokevirtual 108	java/net/URLConnection:setConnectTimeout	(I)V
      //   235: aload 4
      //   237: astore_3
      //   238: aload 4
      //   240: sipush 20000
      //   243: invokevirtual 111	java/net/URLConnection:setReadTimeout	(I)V
      //   246: aload 4
      //   248: astore_3
      //   249: aload 4
      //   251: ldc 113
      //   253: ldc 115
      //   255: invokevirtual 119	java/net/URLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   258: aload 4
      //   260: astore_3
      //   261: aload 4
      //   263: iconst_1
      //   264: invokevirtual 123	java/net/URLConnection:setDoOutput	(Z)V
      //   267: aload 4
      //   269: astore_3
      //   270: new 125	java/io/ByteArrayOutputStream
      //   273: dup
      //   274: invokespecial 126	java/io/ByteArrayOutputStream:<init>	()V
      //   277: astore 9
      //   279: aload 4
      //   281: astore_3
      //   282: new 128	java/util/zip/GZIPOutputStream
      //   285: dup
      //   286: aload 9
      //   288: invokespecial 131	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   291: astore 11
      //   293: aload 11
      //   295: aload_2
      //   296: invokevirtual 136	org/json/JSONObject:toString	()Ljava/lang/String;
      //   299: ldc -118
      //   301: invokevirtual 142	java/lang/String:getBytes	(Ljava/lang/String;)[B
      //   304: invokevirtual 146	java/util/zip/GZIPOutputStream:write	([B)V
      //   307: aload 4
      //   309: astore_3
      //   310: aload 11
      //   312: invokevirtual 149	java/util/zip/GZIPOutputStream:close	()V
      //   315: goto +11 -> 326
      //   318: astore_2
      //   319: aload 4
      //   321: astore_3
      //   322: aload_2
      //   323: invokestatic 154	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   326: aload 4
      //   328: astore_3
      //   329: aload 9
      //   331: invokevirtual 158	java/io/ByteArrayOutputStream:toByteArray	()[B
      //   334: iconst_0
      //   335: invokestatic 164	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
      //   338: astore_2
      //   339: aload 4
      //   341: astore_3
      //   342: aload 4
      //   344: invokevirtual 168	java/net/URLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   347: aload_2
      //   348: invokestatic 173	com/appodeal/ads/bf:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   351: aload 4
      //   353: astore_3
      //   354: aload 4
      //   356: invokevirtual 177	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
      //   359: invokestatic 180	com/appodeal/ads/bf:a	(Ljava/io/InputStream;)Ljava/lang/String;
      //   362: astore 9
      //   364: aload 5
      //   366: astore_2
      //   367: aload 9
      //   369: ifnull +62 -> 431
      //   372: aload 5
      //   374: astore_2
      //   375: aload 4
      //   377: astore_3
      //   378: aload 9
      //   380: invokevirtual 184	java/lang/String:isEmpty	()Z
      //   383: ifne +48 -> 431
      //   386: aload 5
      //   388: astore_2
      //   389: aload 4
      //   391: astore_3
      //   392: aload 9
      //   394: ldc -70
      //   396: invokevirtual 52	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   399: ifne +32 -> 431
      //   402: aload 4
      //   404: astore_3
      //   405: aload_0
      //   406: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   409: getfield 44	com/appodeal/ads/ab:d	Ljava/lang/String;
      //   412: ldc -68
      //   414: invokevirtual 52	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   417: istore_1
      //   418: iload_1
      //   419: ifeq +9 -> 428
      //   422: aload 5
      //   424: astore_2
      //   425: goto +6 -> 431
      //   428: aload 9
      //   430: astore_2
      //   431: aload_2
      //   432: astore_3
      //   433: aload 4
      //   435: ifnull +188 -> 623
      //   438: aload 4
      //   440: instanceof 85
      //   443: ifeq +18 -> 461
      //   446: aload 4
      //   448: checkcast 85	javax/net/ssl/HttpsURLConnection
      //   451: astore_3
      //   452: aload_3
      //   453: invokevirtual 191	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   456: aload_2
      //   457: astore_3
      //   458: goto +165 -> 623
      //   461: aload_2
      //   462: astore_3
      //   463: aload 4
      //   465: instanceof 193
      //   468: ifeq +155 -> 623
      //   471: aload 4
      //   473: checkcast 193	java/net/HttpURLConnection
      //   476: astore_3
      //   477: aload_3
      //   478: invokevirtual 194	java/net/HttpURLConnection:disconnect	()V
      //   481: aload_2
      //   482: astore_3
      //   483: goto +140 -> 623
      //   486: astore_2
      //   487: aload 4
      //   489: astore_3
      //   490: aload 11
      //   492: invokevirtual 149	java/util/zip/GZIPOutputStream:close	()V
      //   495: goto +13 -> 508
      //   498: astore 5
      //   500: aload 4
      //   502: astore_3
      //   503: aload 5
      //   505: invokestatic 154	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   508: aload 4
      //   510: astore_3
      //   511: aload_2
      //   512: athrow
      //   513: astore_2
      //   514: goto +483 -> 997
      //   517: astore 5
      //   519: aload 4
      //   521: astore_2
      //   522: goto +13 -> 535
      //   525: astore_2
      //   526: aconst_null
      //   527: astore_3
      //   528: goto +469 -> 997
      //   531: astore 5
      //   533: aconst_null
      //   534: astore_2
      //   535: aload_2
      //   536: astore_3
      //   537: aload 5
      //   539: invokestatic 154	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   542: aload_2
      //   543: astore_3
      //   544: aload 5
      //   546: invokevirtual 197	java/io/IOException:getMessage	()Ljava/lang/String;
      //   549: ldc -57
      //   551: invokevirtual 52	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   554: ifne +395 -> 949
      //   557: aload_2
      //   558: astore_3
      //   559: aload 5
      //   561: invokevirtual 197	java/io/IOException:getMessage	()Ljava/lang/String;
      //   564: ldc -55
      //   566: invokevirtual 52	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   569: istore_1
      //   570: iload_1
      //   571: ifeq +6 -> 577
      //   574: goto +375 -> 949
      //   577: aload 8
      //   579: astore_3
      //   580: aload_2
      //   581: ifnull +42 -> 623
      //   584: aload_2
      //   585: instanceof 85
      //   588: ifeq +14 -> 602
      //   591: aload_2
      //   592: checkcast 85	javax/net/ssl/HttpsURLConnection
      //   595: astore_3
      //   596: aload 6
      //   598: astore_2
      //   599: goto -147 -> 452
      //   602: aload 8
      //   604: astore_3
      //   605: aload_2
      //   606: instanceof 193
      //   609: ifeq +14 -> 623
      //   612: aload_2
      //   613: checkcast 193	java/net/HttpURLConnection
      //   616: astore_3
      //   617: aload 7
      //   619: astore_2
      //   620: goto -143 -> 477
      //   623: aload_3
      //   624: ifnonnull +94 -> 718
      //   627: aload_0
      //   628: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   631: getfield 96	com/appodeal/ads/ab:B	Z
      //   634: ifeq +71 -> 705
      //   637: aload 10
      //   639: aload_0
      //   640: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   643: getfield 44	com/appodeal/ads/ab:d	Ljava/lang/String;
      //   646: invokeinterface 102 2 0
      //   651: ifeq +54 -> 705
      //   654: aload_0
      //   655: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   658: aload 10
      //   660: aload_0
      //   661: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   664: getfield 44	com/appodeal/ads/ab:d	Ljava/lang/String;
      //   667: invokevirtual 204	com/appodeal/ads/ab:a	(Landroid/content/SharedPreferences;Ljava/lang/String;)Z
      //   670: ifeq +35 -> 705
      //   673: new 206	com/appodeal/ads/utils/c/a
      //   676: dup
      //   677: ldc -48
      //   679: invokespecial 211	com/appodeal/ads/utils/c/a:<init>	(Ljava/lang/String;)V
      //   682: invokestatic 154	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   685: aload 10
      //   687: aload_0
      //   688: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   691: getfield 44	com/appodeal/ads/ab:d	Ljava/lang/String;
      //   694: ldc -43
      //   696: invokeinterface 217 3 0
      //   701: astore_2
      //   702: goto +47 -> 749
      //   705: aload_0
      //   706: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   709: invokestatic 60	com/appodeal/ads/ab:a	(Lcom/appodeal/ads/ab;)Landroid/os/Handler;
      //   712: iconst_0
      //   713: invokevirtual 66	android/os/Handler:sendEmptyMessage	(I)Z
      //   716: pop
      //   717: return
      //   718: aload_3
      //   719: astore_2
      //   720: aload_0
      //   721: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   724: getfield 96	com/appodeal/ads/ab:B	Z
      //   727: ifeq +22 -> 749
      //   730: aload_0
      //   731: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   734: aload 10
      //   736: aload_0
      //   737: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   740: getfield 44	com/appodeal/ads/ab:d	Ljava/lang/String;
      //   743: aload_3
      //   744: invokevirtual 220	com/appodeal/ads/ab:a	(Landroid/content/SharedPreferences;Ljava/lang/String;Ljava/lang/String;)V
      //   747: aload_3
      //   748: astore_2
      //   749: new 133	org/json/JSONObject
      //   752: dup
      //   753: aload_2
      //   754: invokespecial 221	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   757: astore_3
      //   758: aload_0
      //   759: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   762: getfield 96	com/appodeal/ads/ab:B	Z
      //   765: ifeq +13 -> 778
      //   768: aload_2
      //   769: getstatic 227	com/appodeal/ads/utils/Log$LogLevel:verbose	Lcom/appodeal/ads/utils/Log$LogLevel;
      //   772: invokestatic 230	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;Lcom/appodeal/ads/utils/Log$LogLevel;)V
      //   775: goto +7 -> 782
      //   778: aload_2
      //   779: invokestatic 232	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   782: aload_0
      //   783: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   786: getfield 96	com/appodeal/ads/ab:B	Z
      //   789: ifeq +116 -> 905
      //   792: aload_0
      //   793: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   796: getfield 32	com/appodeal/ads/ab:b	Landroid/content/Context;
      //   799: aload_3
      //   800: ldc -22
      //   802: invokevirtual 238	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   805: invokestatic 243	com/appodeal/ads/UserSettings:a	(Landroid/content/Context;Lorg/json/JSONObject;)V
      //   808: aload_0
      //   809: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   812: getfield 32	com/appodeal/ads/ab:b	Landroid/content/Context;
      //   815: aload_3
      //   816: ldc -11
      //   818: invokevirtual 238	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   821: aload_3
      //   822: ldc -9
      //   824: invokevirtual 250	org/json/JSONObject:optBoolean	(Ljava/lang/String;)Z
      //   827: aload_3
      //   828: ldc -4
      //   830: invokevirtual 250	org/json/JSONObject:optBoolean	(Ljava/lang/String;)Z
      //   833: invokestatic 257	com/appodeal/ads/h:a	(Landroid/content/Context;Lorg/json/JSONObject;ZZ)V
      //   836: aload_0
      //   837: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   840: aload 10
      //   842: aload_3
      //   843: ldc_w 259
      //   846: ldc_w 260
      //   849: invokevirtual 264	org/json/JSONObject:optInt	(Ljava/lang/String;I)I
      //   852: aload_0
      //   853: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   856: getfield 44	com/appodeal/ads/ab:d	Ljava/lang/String;
      //   859: invokevirtual 267	com/appodeal/ads/ab:a	(Landroid/content/SharedPreferences;ILjava/lang/String;)V
      //   862: aload_0
      //   863: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   866: aload_3
      //   867: invokestatic 270	com/appodeal/ads/ab:a	(Lcom/appodeal/ads/ab;Lorg/json/JSONObject;)V
      //   870: aload_3
      //   871: ldc_w 272
      //   874: invokevirtual 276	org/json/JSONObject:optJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   877: invokestatic 281	com/appodeal/ads/f/e:a	(Lorg/json/JSONArray;)V
      //   880: invokestatic 284	com/appodeal/ads/f/e:c	()V
      //   883: getstatic 288	com/appodeal/ads/Appodeal:i	Lcom/appodeal/ads/utils/v;
      //   886: ifnull +19 -> 905
      //   889: getstatic 288	com/appodeal/ads/Appodeal:i	Lcom/appodeal/ads/utils/v;
      //   892: invokeinterface 292 1 0
      //   897: goto +8 -> 905
      //   900: astore_2
      //   901: aload_2
      //   902: invokestatic 154	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   905: aload_0
      //   906: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   909: invokestatic 60	com/appodeal/ads/ab:a	(Lcom/appodeal/ads/ab;)Landroid/os/Handler;
      //   912: iconst_1
      //   913: aload_3
      //   914: invokevirtual 296	android/os/Handler:obtainMessage	(ILjava/lang/Object;)Landroid/os/Message;
      //   917: astore_2
      //   918: aload_0
      //   919: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   922: invokestatic 60	com/appodeal/ads/ab:a	(Lcom/appodeal/ads/ab;)Landroid/os/Handler;
      //   925: aload_2
      //   926: invokevirtual 300	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   929: pop
      //   930: return
      //   931: astore_2
      //   932: aload_2
      //   933: invokestatic 154	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   936: aload_0
      //   937: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   940: invokestatic 60	com/appodeal/ads/ab:a	(Lcom/appodeal/ads/ab;)Landroid/os/Handler;
      //   943: iconst_0
      //   944: invokevirtual 66	android/os/Handler:sendEmptyMessage	(I)Z
      //   947: pop
      //   948: return
      //   949: aload_2
      //   950: astore_3
      //   951: aload_0
      //   952: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   955: invokestatic 60	com/appodeal/ads/ab:a	(Lcom/appodeal/ads/ab;)Landroid/os/Handler;
      //   958: iconst_0
      //   959: invokevirtual 66	android/os/Handler:sendEmptyMessage	(I)Z
      //   962: pop
      //   963: aload_2
      //   964: ifnull +88 -> 1052
      //   967: aload_2
      //   968: instanceof 85
      //   971: ifeq +11 -> 982
      //   974: aload_2
      //   975: checkcast 85	javax/net/ssl/HttpsURLConnection
      //   978: invokevirtual 191	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   981: return
      //   982: aload_2
      //   983: instanceof 193
      //   986: ifeq +66 -> 1052
      //   989: aload_2
      //   990: checkcast 193	java/net/HttpURLConnection
      //   993: invokevirtual 194	java/net/HttpURLConnection:disconnect	()V
      //   996: return
      //   997: aload_3
      //   998: ifnull +34 -> 1032
      //   1001: aload_3
      //   1002: instanceof 85
      //   1005: ifeq +13 -> 1018
      //   1008: aload_3
      //   1009: checkcast 85	javax/net/ssl/HttpsURLConnection
      //   1012: invokevirtual 191	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   1015: goto +17 -> 1032
      //   1018: aload_3
      //   1019: instanceof 193
      //   1022: ifeq +10 -> 1032
      //   1025: aload_3
      //   1026: checkcast 193	java/net/HttpURLConnection
      //   1029: invokevirtual 194	java/net/HttpURLConnection:disconnect	()V
      //   1032: aload_2
      //   1033: athrow
      //   1034: astore_2
      //   1035: aload_2
      //   1036: invokestatic 154	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   1039: aload_0
      //   1040: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   1043: invokestatic 60	com/appodeal/ads/ab:a	(Lcom/appodeal/ads/ab;)Landroid/os/Handler;
      //   1046: iconst_0
      //   1047: invokevirtual 66	android/os/Handler:sendEmptyMessage	(I)Z
      //   1050: pop
      //   1051: return
      //   1052: return
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	1053	0	this	b
      //   417	154	1	bool	boolean
      //   13	283	2	localObject1	Object
      //   318	5	2	localException1	Exception
      //   338	144	2	localObject2	Object
      //   486	26	2	localObject3	Object
      //   513	1	2	localObject4	Object
      //   521	1	2	localObject5	Object
      //   525	1	2	localObject6	Object
      //   534	245	2	localObject7	Object
      //   900	2	2	localException2	Exception
      //   917	9	2	localMessage	Message
      //   931	102	2	localJSONException	JSONException
      //   1034	2	2	localException3	Exception
      //   112	914	3	localObject8	Object
      //   108	412	4	localURLConnection	java.net.URLConnection
      //   101	322	5	localObject9	Object
      //   498	6	5	localException4	Exception
      //   517	1	5	localIOException1	java.io.IOException
      //   531	29	5	localIOException2	java.io.IOException
      //   92	505	6	localObject10	Object
      //   95	523	7	localObject11	Object
      //   98	505	8	localObject12	Object
      //   74	355	9	localObject13	Object
      //   89	752	10	localSharedPreferences	SharedPreferences
      //   291	200	11	localGZIPOutputStream	java.util.zip.GZIPOutputStream
      // Exception table:
      //   from	to	target	type
      //   310	315	318	java/lang/Exception
      //   293	307	486	finally
      //   490	495	498	java/lang/Exception
      //   113	126	513	finally
      //   129	144	513	finally
      //   147	157	513	finally
      //   160	177	513	finally
      //   180	188	513	finally
      //   191	199	513	finally
      //   205	213	513	finally
      //   216	224	513	finally
      //   227	235	513	finally
      //   238	246	513	finally
      //   249	258	513	finally
      //   261	267	513	finally
      //   270	279	513	finally
      //   282	293	513	finally
      //   310	315	513	finally
      //   322	326	513	finally
      //   329	339	513	finally
      //   342	351	513	finally
      //   354	364	513	finally
      //   378	386	513	finally
      //   392	402	513	finally
      //   405	418	513	finally
      //   490	495	513	finally
      //   503	508	513	finally
      //   511	513	513	finally
      //   537	542	513	finally
      //   544	557	513	finally
      //   559	570	513	finally
      //   951	963	513	finally
      //   113	126	517	java/io/IOException
      //   129	144	517	java/io/IOException
      //   147	157	517	java/io/IOException
      //   160	177	517	java/io/IOException
      //   180	188	517	java/io/IOException
      //   191	199	517	java/io/IOException
      //   205	213	517	java/io/IOException
      //   216	224	517	java/io/IOException
      //   227	235	517	java/io/IOException
      //   238	246	517	java/io/IOException
      //   249	258	517	java/io/IOException
      //   261	267	517	java/io/IOException
      //   270	279	517	java/io/IOException
      //   282	293	517	java/io/IOException
      //   310	315	517	java/io/IOException
      //   322	326	517	java/io/IOException
      //   329	339	517	java/io/IOException
      //   342	351	517	java/io/IOException
      //   354	364	517	java/io/IOException
      //   378	386	517	java/io/IOException
      //   392	402	517	java/io/IOException
      //   405	418	517	java/io/IOException
      //   490	495	517	java/io/IOException
      //   503	508	517	java/io/IOException
      //   511	513	517	java/io/IOException
      //   103	110	525	finally
      //   103	110	531	java/io/IOException
      //   782	897	900	java/lang/Exception
      //   749	775	931	org/json/JSONException
      //   778	782	931	org/json/JSONException
      //   0	38	1034	java/lang/Exception
      //   41	50	1034	java/lang/Exception
      //   54	66	1034	java/lang/Exception
      //   67	91	1034	java/lang/Exception
      //   438	452	1034	java/lang/Exception
      //   452	456	1034	java/lang/Exception
      //   463	477	1034	java/lang/Exception
      //   477	481	1034	java/lang/Exception
      //   584	596	1034	java/lang/Exception
      //   605	617	1034	java/lang/Exception
      //   627	702	1034	java/lang/Exception
      //   705	717	1034	java/lang/Exception
      //   720	747	1034	java/lang/Exception
      //   749	775	1034	java/lang/Exception
      //   778	782	1034	java/lang/Exception
      //   901	905	1034	java/lang/Exception
      //   905	930	1034	java/lang/Exception
      //   932	948	1034	java/lang/Exception
      //   967	981	1034	java/lang/Exception
      //   982	996	1034	java/lang/Exception
      //   1001	1015	1034	java/lang/Exception
      //   1018	1032	1034	java/lang/Exception
      //   1032	1034	1034	java/lang/Exception
    }
  }
  
  public static class c
  {
    private final Context a;
    private final int b;
    private final String c;
    private ab.a d;
    private String e;
    private com.appodeal.ads.d.h f;
    private d g;
    private String h;
    private int i = 1;
    private double j = -1.0D;
    private boolean k;
    private String l;
    private double m;
    private JSONObject n;
    private g o;
    
    public c(Context paramContext, int paramInt, String paramString)
    {
      this.a = paramContext;
      this.b = paramInt;
      this.c = paramString;
    }
    
    public c a(double paramDouble)
    {
      this.j = paramDouble;
      return this;
    }
    
    public c a(double paramDouble, String paramString)
    {
      this.m = paramDouble;
      this.l = paramString;
      return this;
    }
    
    public c a(int paramInt)
    {
      this.i = paramInt;
      return this;
    }
    
    public c a(ab.a paramA)
    {
      this.d = paramA;
      return this;
    }
    
    public c a(com.appodeal.ads.d.h paramH)
    {
      this.f = paramH;
      return this;
    }
    
    public c a(d paramD)
    {
      this.g = paramD;
      return this;
    }
    
    public c a(g paramG)
    {
      this.o = paramG;
      return this;
    }
    
    public c a(String paramString)
    {
      this.e = paramString;
      return this;
    }
    
    public c a(JSONObject paramJSONObject)
    {
      this.n = paramJSONObject;
      return this;
    }
    
    public c a(boolean paramBoolean)
    {
      this.k = paramBoolean;
      return this;
    }
    
    public ab a()
    {
      return new ab(this, null);
    }
    
    public c b(String paramString)
    {
      this.h = paramString;
      return this;
    }
  }
}
