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
import android.util.Pair;
import com.appodeal.ads.f.c;
import com.appodeal.ads.f.g;
import com.appodeal.ads.utils.n;
import com.appodeal.ads.utils.r;
import com.appodeal.ads.utils.z;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
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

public class aa
{
  private static SSLSocketFactory C;
  static JSONObject r;
  boolean A;
  private final JSONObject B;
  private final Handler D;
  private final int E;
  private final int F;
  private boolean G;
  final a a;
  final Context b;
  final int c;
  final String d;
  final String e;
  final String f;
  final String g;
  final com.appodeal.ads.d.h h;
  final c i;
  final long j;
  final String k;
  final Long l;
  final int m;
  final double n;
  final boolean o;
  final String p;
  final JSONObject q;
  boolean s;
  boolean t;
  boolean u;
  boolean v;
  boolean w;
  boolean x;
  boolean y;
  boolean z;
  
  private aa(c paramC)
  {
    boolean bool2 = false;
    this.E = 0;
    this.F = 1;
    this.a = c.a(paramC);
    this.b = c.b(paramC);
    this.c = c.c(paramC);
    this.d = c.d(paramC);
    this.e = c.e(paramC);
    this.f = c.f(paramC);
    this.g = c.g(paramC);
    this.h = c.h(paramC);
    this.j = c.i(paramC);
    this.i = c.j(paramC);
    this.k = c.k(paramC);
    this.l = c.l(paramC);
    this.m = c.m(paramC);
    this.n = c.n(paramC);
    this.o = c.o(paramC);
    this.p = c.p(paramC);
    this.q = c.q(paramC);
    this.B = c.r(paramC);
    this.D = new Handler(Looper.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (aa.this.a != null)
        {
          switch (paramAnonymousMessage.what)
          {
          default: 
            return;
          case 1: 
            paramAnonymousMessage = (JSONObject)paramAnonymousMessage.obj;
            if (paramAnonymousMessage != null)
            {
              aa.this.a.a(paramAnonymousMessage, aa.this.c, aa.this.d);
              return;
            }
            break;
          }
          aa.this.a.a(aa.this.c);
        }
      }
    };
    if (this.d == null) {
      return;
    }
    if ((j.a) && ((this.a == null) || ((this.a instanceof com.appodeal.ads.utils.ad))) && ((this.d.equals("stats")) || (this.d.equals("show")) || (this.d.equals("click")) || (this.d.equals("finish")) || (this.d.equals("install")))) {
      return;
    }
    if ((!this.d.equals("banner")) && (!this.d.equals("debug"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.s = bool1;
    if ((!this.d.equals("banner_320")) && (!this.d.equals("debug_banner_320"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.t = bool1;
    if ((!this.d.equals("banner_mrec")) && (!this.d.equals("debug_mrec"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.u = bool1;
    if ((!this.d.equals("video")) && (!this.d.equals("debug_video"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.v = bool1;
    if ((!this.d.equals("rewarded_video")) && (!this.d.equals("debug_rewarded_video"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.w = bool1;
    if ((!this.d.equals("native")) && (!this.d.equals("debug_native"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.x = bool1;
    if ((!this.d.equals("debug")) && (!this.d.equals("debug_banner_320")) && (!this.d.equals("debug_video")) && (!this.d.equals("debug_rewarded_video")) && (!this.d.equals("debug_mrec")) && (!this.d.equals("debug_native"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.y = bool1;
    if ((!this.s) && (!this.t) && (!this.u) && (!this.v) && (!this.w) && (!this.x)) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.z = bool1;
    boolean bool1 = bool2;
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
            if (!this.d.equals("install")) {
              bool1 = true;
            }
          }
        }
      }
    }
    this.A = bool1;
    this.G = true;
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
      paramJSONObject = new g(this.b, paramJSONObject);
      if (!paramJSONObject.b(localJSONArray)) {
        return;
      }
      paramJSONObject = paramJSONObject.a(localJSONArray);
      if (paramJSONObject != null)
      {
        try
        {
          paramJSONObject.a();
        }
        catch (JSONException localJSONException)
        {
          Appodeal.a(localJSONException);
        }
        g.a(paramJSONObject);
      }
    }
  }
  
  private String b(String paramString)
  {
    return String.format("%s_wst", new Object[] { paramString });
  }
  
  private JSONObject b(SharedPreferences paramSharedPreferences)
  {
    for (;;)
    {
      try
      {
        if ((r == null) || (r.length() == 0))
        {
          r = new JSONObject();
          PackageManager localPackageManager = this.b.getPackageManager();
          String str2 = paramSharedPreferences.getString("appKey", null);
          if (str2 == null) {
            return null;
          }
          String str1 = paramSharedPreferences.getString("advertisingId", null);
          Object localObject4 = paramSharedPreferences.getString("advertisingTracking", null);
          localObject3 = localObject4;
          Object localObject1 = str1;
          if (str1 == null)
          {
            boolean bool = this.d.equals("install");
            localObject3 = localObject4;
            localObject1 = str1;
            if (!bool) {
              try
              {
                Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
                AdvertisingIdClient.class.getDeclaredMethod("getAdvertisingIdInfo", new Class[] { Context.class });
                localObject3 = AdvertisingIdClient.getAdvertisingIdInfo(this.b);
                localObject1 = ((AdvertisingIdClient.Info)localObject3).getId();
                try
                {
                  if (!((AdvertisingIdClient.Info)localObject3).isLimitAdTrackingEnabled()) {
                    break label795;
                  }
                  localObject3 = "0";
                  localObject4 = localObject3;
                  paramSharedPreferences = paramSharedPreferences.edit();
                  localObject4 = localObject3;
                  paramSharedPreferences.putString("advertisingId", (String)localObject1);
                  localObject4 = localObject3;
                  paramSharedPreferences.putString("advertisingTracking", (String)localObject3);
                  localObject4 = localObject3;
                  paramSharedPreferences.apply();
                  localObject4 = localObject3;
                  Appodeal.a(String.format("Advertising ID: %s", new Object[] { localObject1 }));
                }
                catch (Exception paramSharedPreferences)
                {
                  localObject3 = localObject4;
                }
                Appodeal.a(paramSharedPreferences);
              }
              catch (Exception paramSharedPreferences)
              {
                localObject1 = str1;
                localObject3 = localObject4;
              }
            }
          }
          paramSharedPreferences = (SharedPreferences)localObject1;
          if (localObject1 == null) {
            paramSharedPreferences = bd.l(this.b);
          }
          r.put("app_key", str2);
          r.put("android", Build.VERSION.RELEASE);
          r.put("android_level", Build.VERSION.SDK_INT);
          r.put("sdk", "2.2.3");
          localObject4 = this.b.getPackageName();
          r.put("package", localObject4);
          try
          {
            localObject1 = localPackageManager.getPackageInfo((String)localObject4, 0);
            r.put("package_version", ((PackageInfo)localObject1).versionName);
            r.put("package_code", ((PackageInfo)localObject1).versionCode);
            r.put("install_time", ((PackageInfo)localObject1).firstInstallTime / 1000L);
            localObject1 = localPackageManager.getApplicationInfo((String)localObject4, 0);
            r.put("target_sdk_version", ((ApplicationInfo)localObject1).targetSdkVersion);
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            Appodeal.a(localNameNotFoundException);
          }
          if (Appodeal.j != null) {
            r.put("framework", Appodeal.j);
          }
          if (Appodeal.k != null) {
            r.put("plugin_version", Appodeal.k);
          }
          r.put("android_id", paramSharedPreferences);
          r.put("advertising_tracking", localObject3);
          paramSharedPreferences = bd.f(this.b);
          r.put("width", paramSharedPreferences.first);
          r.put("height", paramSharedPreferences.second);
          r.put("pxratio", bd.i(this.b));
          if (bd.n(this.b))
          {
            paramSharedPreferences = r;
            localObject2 = "tablet";
            paramSharedPreferences.put("device_type", localObject2);
          }
          else
          {
            paramSharedPreferences = r;
            localObject2 = "phone";
            continue;
          }
          if (!Build.MANUFACTURER.equals("Amazon")) {
            break label803;
          }
          paramSharedPreferences = "amazon";
          r.put("platform", paramSharedPreferences);
          try
          {
            localObject2 = localPackageManager.getInstallerPackageName((String)localObject4);
            paramSharedPreferences = (SharedPreferences)localObject2;
            if (localObject2 == null) {
              paramSharedPreferences = "unknown";
            }
            r.put("installer", paramSharedPreferences);
          }
          catch (Exception paramSharedPreferences)
          {
            Appodeal.a(paramSharedPreferences);
          }
          r.put("manufacturer", Build.MANUFACTURER);
          r.put("rooted", bd.a());
          r.put("webview_version", bd.t(this.b));
          r.put("multidex", bd.b(new String[] { "android.support.multidex.MultiDex" }));
        }
        paramSharedPreferences = new JSONObject();
        Object localObject2 = r.keys();
        if (((Iterator)localObject2).hasNext())
        {
          localObject3 = (String)((Iterator)localObject2).next();
          paramSharedPreferences.put((String)localObject3, r.get((String)localObject3));
          continue;
        }
        return paramSharedPreferences;
      }
      finally {}
      label795:
      Object localObject3 = "1";
      continue;
      label803:
      paramSharedPreferences = "google";
    }
  }
  
  private SSLSocketFactory d()
  {
    try
    {
      if (C == null)
      {
        localObject1 = new ArrayList();
        ((List)localObject1).add("A1ABC1296E644B3A25179FCD3E277C8D36039BEE94478E2F5104FA4244237F54");
        ((List)localObject1).add("E91093227F02CE854C3214749DC7FB3459E0E43E80CAE27F01AA0EA92894C9E1");
        localObject1 = new com.appodeal.ads.utils.f((List)localObject1, 1526342400000L);
        SSLContext localSSLContext = SSLContext.getInstance("TLSv1");
        localSSLContext.init(null, new TrustManager[] { localObject1 }, null);
        C = localSSLContext.getSocketFactory();
      }
      Object localObject1 = C;
      return localObject1;
    }
    finally {}
  }
  
  JSONObject a(SharedPreferences paramSharedPreferences)
  {
    JSONObject localJSONObject = b(paramSharedPreferences);
    if (localJSONObject == null) {
      return null;
    }
    Object localObject3;
    try
    {
      if (this.s) {
        localJSONObject.put("type", "banner");
      }
      if (this.t)
      {
        localJSONObject.put("type", "banner_320");
        float f1 = bd.g(this.b);
        float f2 = bd.h(this.b);
        if ((k.o) && (f1 >= 728.0F) && (f2 > 720.0F)) {
          localJSONObject.put("large_banners", true);
        }
      }
      if (this.u) {
        localJSONObject.put("type", "banner_mrec");
      }
      if ((this.v) || (this.w)) {
        localJSONObject.put("type", "video");
      }
      if (this.w) {
        localJSONObject.put("rewarded_video", true);
      }
      if (this.x) {
        localJSONObject.put("type", "native");
      }
      if (this.y) {
        localJSONObject.put("debug", true);
      }
      if (j.a) {
        localJSONObject.put("test", true);
      }
      Object localObject1 = bd.b(this.b);
      localJSONObject.put("battery", bd.k(this.b));
      localJSONObject.put("crr", bd.c(this.b));
      localJSONObject.put("locale", Locale.getDefault().toString());
      localObject3 = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.ENGLISH);
      localJSONObject.put("timezone", new SimpleDateFormat("Z", Locale.ENGLISH).format(((Calendar)localObject3).getTime()));
      localJSONObject.put("local_time", System.currentTimeMillis() / 1000L);
      localJSONObject.put("user_agent", System.getProperty("http.agent"));
      com.appodeal.ads.utils.e.c(this.b);
      localJSONObject.put("session_id", com.appodeal.ads.utils.e.a(paramSharedPreferences));
      localJSONObject.put("session_uptime", com.appodeal.ads.utils.e.b());
      localJSONObject.put("app_uptime", com.appodeal.ads.utils.e.b(paramSharedPreferences));
      if (!h.h)
      {
        localJSONObject.put("connection", ((bd.a)localObject1).a);
        localJSONObject.put("connection_subtype", ((bd.a)localObject1).b);
        localJSONObject.put("connection_fast", ((bd.a)localObject1).c);
        localObject1 = bd.d(this.b);
        localJSONObject.put("lt", ((Pair)localObject1).first);
        localJSONObject.put("lat", ((Pair)((Pair)localObject1).second).first);
        localJSONObject.put("lon", ((Pair)((Pair)localObject1).second).second);
        localJSONObject.put("model", String.format("%s %s", new Object[] { Build.MANUFACTURER, Build.MODEL }));
      }
      localJSONObject.put("coppa", h.h);
      localObject1 = paramSharedPreferences.getString("inapps", null);
      if (localObject1 != null) {
        localJSONObject.put("inapps", new JSONObject((String)localObject1));
      }
      if (this.z)
      {
        localObject1 = new JSONArray();
        if (this.s) {
          localObject1 = new JSONArray(s.b().b());
        }
        if (this.v) {
          localObject1 = new JSONArray(at.b().b());
        }
        if (this.w) {
          localObject1 = new JSONArray(ay.b().b());
        }
        if (this.t) {
          localObject1 = new JSONArray(k.b().b());
        }
        if (this.u) {
          localObject1 = new JSONArray(ad.b().b());
        }
        if (this.x) {
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
      localJSONObject.put("id", this.e);
      localJSONObject.put("main_id", this.f);
      if ((this.z) || (this.k != null)) {
        localJSONObject.put("ad_stats", c());
      }
      if ((this.z) && (j.p == null)) {
        localJSONObject.put("check_sdk_version", true);
      }
      if (this.i != null) {
        localJSONObject.put("placement_id", this.i.b());
      }
      if ((this.B != null) && (this.d.equals("stats"))) {
        localJSONObject.put("ad_unit_stat", this.B);
      }
    }
    catch (JSONException localJSONException)
    {
      Appodeal.a(localJSONException);
    }
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramSharedPreferences.getLong("lastSettingsTime", 0L));
    localCalendar.add(5, 1);
    if ((!UserSettings.a) && (this.z) && ((localCalendar.getTimeInMillis() < System.currentTimeMillis()) || (paramSharedPreferences.getBoolean("should_update_user_settings", true))))
    {
      UserSettings.a = true;
      try
      {
        localJSONObject.put("sa", z.a(this.b));
      }
      catch (Exception localException1)
      {
        Appodeal.a(localException1);
      }
      if (!h.h) {
        try
        {
          localJSONObject.put("user_settings", bd.u(this.b).k());
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
    if ((!j.l) && (j.k) && (this.z) && (((Calendar)localObject2).getTimeInMillis() < System.currentTimeMillis()))
    {
      j.l = true;
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
      j.l = false;
    }
    if (this.h != null)
    {
      String str1;
      if (this.d.equals("stats"))
      {
        paramSharedPreferences = "id";
        str1 = this.h.a().toString();
        localJSONObject.put(paramSharedPreferences, str1);
        return localJSONObject;
      }
      if (this.d.equals("show"))
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
      else {
        for (paramSharedPreferences = "bidder_id";; paramSharedPreferences = "id")
        {
          str1 = this.h.d();
          break;
          if (((!this.d.equals("click")) && (!this.d.equals("finish"))) || (!this.h.e())) {
            break label1640;
          }
        }
      }
    }
    label1640:
    return localJSONObject;
  }
  
  public void a()
  {
    if (this.G) {
      r.a.execute(new b(null));
    }
  }
  
  void a(SharedPreferences paramSharedPreferences, int paramInt, String paramString)
  {
    paramSharedPreferences = paramSharedPreferences.edit();
    paramSharedPreferences.putInt(b(paramString), paramInt);
    paramSharedPreferences.apply();
  }
  
  void a(SharedPreferences paramSharedPreferences, String paramString1, String paramString2)
  {
    paramSharedPreferences = paramSharedPreferences.edit();
    paramSharedPreferences.putString(paramString1, paramString2);
    paramSharedPreferences.putLong(a(paramString1), System.currentTimeMillis());
    paramSharedPreferences.apply();
  }
  
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
  
  int b(SharedPreferences paramSharedPreferences, String paramString)
  {
    return paramSharedPreferences.getInt(b(paramString), 86400000);
  }
  
  URL b()
  {
    if (this.A)
    {
      if (this.o) {
        return bd.g(this.d);
      }
      return new URL(com.appodeal.ads.utils.h.a("get"));
    }
    return new URL(com.appodeal.ads.utils.h.a(this.d));
  }
  
  JSONObject c()
  {
    JSONObject localJSONObject = new JSONObject();
    int i1 = n.a();
    int i2 = n.b();
    int i3 = n.c();
    try
    {
      localJSONObject.put("show", i1);
      localJSONObject.put("click", i2);
      if ((this.v) || (this.w) || ((this.k != null) && ((this.k.equals("video")) || (this.k.equals("rewarded_video"))))) {
        localJSONObject.put("finish", i3);
      }
      if ((this.s) || ((this.k != null) && (this.k.equals("banner"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "show" }), n.a("interstitial"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "click" }), n.b("interstitial"));
      }
      if ((this.v) || ((this.k != null) && (this.k.equals("video"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "show" }), n.a("video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "click" }), n.b("video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "finish" }), n.c("video"));
      }
      if ((this.w) || ((this.k != null) && (this.k.equals("rewarded_video"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "show" }), n.a("rewarded_video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "click" }), n.b("rewarded_video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "finish" }), n.c("rewarded_video"));
      }
      if ((this.t) || ((this.k != null) && (this.k.equals("banner_320"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "show" }), n.a("banner"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "click" }), n.b("banner"));
      }
      if ((this.u) || ((this.k != null) && (this.k.equals("banner_mrec"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "show" }), n.a("mrec"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "click" }), n.b("mrec"));
      }
      if ((this.x) || ((this.k != null) && (this.k.equals("native"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "native", "show" }), n.a("native"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "native", "click" }), n.b("native"));
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
      //   1: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   4: getfield 32	com/appodeal/ads/aa:b	Landroid/content/Context;
      //   7: ldc 34
      //   9: iconst_0
      //   10: invokevirtual 40	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   13: astore_2
      //   14: aload_0
      //   15: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   18: aload_2
      //   19: invokevirtual 43	com/appodeal/ads/aa:a	(Landroid/content/SharedPreferences;)Lorg/json/JSONObject;
      //   22: astore_2
      //   23: aload_2
      //   24: ifnonnull +16 -> 40
      //   27: aload_0
      //   28: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   31: invokestatic 46	com/appodeal/ads/aa:a	(Lcom/appodeal/ads/aa;)Landroid/os/Handler;
      //   34: iconst_0
      //   35: invokevirtual 52	android/os/Handler:sendEmptyMessage	(I)Z
      //   38: pop
      //   39: return
      //   40: aload_0
      //   41: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   44: invokevirtual 55	com/appodeal/ads/aa:b	()Ljava/net/URL;
      //   47: astore 9
      //   49: aload_0
      //   50: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   53: getfield 32	com/appodeal/ads/aa:b	Landroid/content/Context;
      //   56: ldc 57
      //   58: iconst_0
      //   59: invokevirtual 40	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   62: astore 10
      //   64: aconst_null
      //   65: astore 6
      //   67: aconst_null
      //   68: astore 7
      //   70: aconst_null
      //   71: astore 8
      //   73: aconst_null
      //   74: astore 5
      //   76: aload 9
      //   78: invokevirtual 63	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   81: astore 4
      //   83: aload 4
      //   85: astore_3
      //   86: aload 9
      //   88: invokevirtual 67	java/net/URL:getProtocol	()Ljava/lang/String;
      //   91: ldc 69
      //   93: invokevirtual 75	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   96: ifeq +21 -> 117
      //   99: aload 4
      //   101: astore_3
      //   102: aload 4
      //   104: checkcast 77	javax/net/ssl/HttpsURLConnection
      //   107: aload_0
      //   108: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   111: invokestatic 80	com/appodeal/ads/aa:b	(Lcom/appodeal/ads/aa;)Ljavax/net/ssl/SSLSocketFactory;
      //   114: invokevirtual 84	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
      //   117: aload 4
      //   119: astore_3
      //   120: aload_0
      //   121: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   124: getfield 88	com/appodeal/ads/aa:A	Z
      //   127: ifeq +48 -> 175
      //   130: aload 4
      //   132: astore_3
      //   133: aload 10
      //   135: aload_0
      //   136: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   139: getfield 92	com/appodeal/ads/aa:d	Ljava/lang/String;
      //   142: invokeinterface 98 2 0
      //   147: ifeq +28 -> 175
      //   150: aload 4
      //   152: astore_3
      //   153: aload 4
      //   155: sipush 10000
      //   158: invokevirtual 104	java/net/URLConnection:setConnectTimeout	(I)V
      //   161: aload 4
      //   163: astore_3
      //   164: aload 4
      //   166: sipush 10000
      //   169: invokevirtual 107	java/net/URLConnection:setReadTimeout	(I)V
      //   172: goto +25 -> 197
      //   175: aload 4
      //   177: astore_3
      //   178: aload 4
      //   180: sipush 20000
      //   183: invokevirtual 104	java/net/URLConnection:setConnectTimeout	(I)V
      //   186: aload 4
      //   188: astore_3
      //   189: aload 4
      //   191: sipush 20000
      //   194: invokevirtual 107	java/net/URLConnection:setReadTimeout	(I)V
      //   197: aload 4
      //   199: astore_3
      //   200: aload 4
      //   202: sipush 20000
      //   205: invokevirtual 104	java/net/URLConnection:setConnectTimeout	(I)V
      //   208: aload 4
      //   210: astore_3
      //   211: aload 4
      //   213: sipush 20000
      //   216: invokevirtual 107	java/net/URLConnection:setReadTimeout	(I)V
      //   219: aload 4
      //   221: astore_3
      //   222: aload 4
      //   224: ldc 109
      //   226: ldc 111
      //   228: invokevirtual 115	java/net/URLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   231: aload 4
      //   233: astore_3
      //   234: aload 4
      //   236: iconst_1
      //   237: invokevirtual 119	java/net/URLConnection:setDoOutput	(Z)V
      //   240: aload 4
      //   242: astore_3
      //   243: new 121	java/io/ByteArrayOutputStream
      //   246: dup
      //   247: invokespecial 122	java/io/ByteArrayOutputStream:<init>	()V
      //   250: astore 9
      //   252: aload 4
      //   254: astore_3
      //   255: new 124	java/util/zip/GZIPOutputStream
      //   258: dup
      //   259: aload 9
      //   261: invokespecial 127	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   264: astore 11
      //   266: aload 11
      //   268: aload_2
      //   269: invokevirtual 132	org/json/JSONObject:toString	()Ljava/lang/String;
      //   272: ldc -122
      //   274: invokevirtual 138	java/lang/String:getBytes	(Ljava/lang/String;)[B
      //   277: invokevirtual 142	java/util/zip/GZIPOutputStream:write	([B)V
      //   280: aload 4
      //   282: astore_3
      //   283: aload 11
      //   285: invokevirtual 145	java/util/zip/GZIPOutputStream:close	()V
      //   288: goto +11 -> 299
      //   291: astore_2
      //   292: aload 4
      //   294: astore_3
      //   295: aload_2
      //   296: invokestatic 150	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   299: aload 4
      //   301: astore_3
      //   302: aload 9
      //   304: invokevirtual 154	java/io/ByteArrayOutputStream:toByteArray	()[B
      //   307: iconst_0
      //   308: invokestatic 160	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
      //   311: astore_2
      //   312: aload 4
      //   314: astore_3
      //   315: aload 4
      //   317: invokevirtual 164	java/net/URLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   320: aload_2
      //   321: invokestatic 169	com/appodeal/ads/bd:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   324: aload 4
      //   326: astore_3
      //   327: aload 4
      //   329: invokevirtual 173	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
      //   332: invokestatic 176	com/appodeal/ads/bd:a	(Ljava/io/InputStream;)Ljava/lang/String;
      //   335: astore 9
      //   337: aload 5
      //   339: astore_2
      //   340: aload 9
      //   342: ifnull +62 -> 404
      //   345: aload 5
      //   347: astore_2
      //   348: aload 4
      //   350: astore_3
      //   351: aload 9
      //   353: invokevirtual 180	java/lang/String:isEmpty	()Z
      //   356: ifne +48 -> 404
      //   359: aload 5
      //   361: astore_2
      //   362: aload 4
      //   364: astore_3
      //   365: aload 9
      //   367: ldc -74
      //   369: invokevirtual 75	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   372: ifne +32 -> 404
      //   375: aload 4
      //   377: astore_3
      //   378: aload_0
      //   379: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   382: getfield 92	com/appodeal/ads/aa:d	Ljava/lang/String;
      //   385: ldc -72
      //   387: invokevirtual 75	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   390: istore_1
      //   391: iload_1
      //   392: ifeq +9 -> 401
      //   395: aload 5
      //   397: astore_2
      //   398: goto +6 -> 404
      //   401: aload 9
      //   403: astore_2
      //   404: aload_2
      //   405: astore_3
      //   406: aload 4
      //   408: ifnull +188 -> 596
      //   411: aload 4
      //   413: instanceof 77
      //   416: ifeq +18 -> 434
      //   419: aload 4
      //   421: checkcast 77	javax/net/ssl/HttpsURLConnection
      //   424: astore_3
      //   425: aload_3
      //   426: invokevirtual 187	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   429: aload_2
      //   430: astore_3
      //   431: goto +165 -> 596
      //   434: aload_2
      //   435: astore_3
      //   436: aload 4
      //   438: instanceof 189
      //   441: ifeq +155 -> 596
      //   444: aload 4
      //   446: checkcast 189	java/net/HttpURLConnection
      //   449: astore_3
      //   450: aload_3
      //   451: invokevirtual 190	java/net/HttpURLConnection:disconnect	()V
      //   454: aload_2
      //   455: astore_3
      //   456: goto +140 -> 596
      //   459: astore_2
      //   460: aload 4
      //   462: astore_3
      //   463: aload 11
      //   465: invokevirtual 145	java/util/zip/GZIPOutputStream:close	()V
      //   468: goto +13 -> 481
      //   471: astore 5
      //   473: aload 4
      //   475: astore_3
      //   476: aload 5
      //   478: invokestatic 150	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   481: aload 4
      //   483: astore_3
      //   484: aload_2
      //   485: athrow
      //   486: astore_2
      //   487: goto +482 -> 969
      //   490: astore 5
      //   492: aload 4
      //   494: astore_2
      //   495: goto +13 -> 508
      //   498: astore_2
      //   499: aconst_null
      //   500: astore_3
      //   501: goto +468 -> 969
      //   504: astore 5
      //   506: aconst_null
      //   507: astore_2
      //   508: aload_2
      //   509: astore_3
      //   510: aload 5
      //   512: invokestatic 150	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   515: aload_2
      //   516: astore_3
      //   517: aload 5
      //   519: invokevirtual 193	java/io/IOException:getMessage	()Ljava/lang/String;
      //   522: ldc -61
      //   524: invokevirtual 75	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   527: ifne +394 -> 921
      //   530: aload_2
      //   531: astore_3
      //   532: aload 5
      //   534: invokevirtual 193	java/io/IOException:getMessage	()Ljava/lang/String;
      //   537: ldc -59
      //   539: invokevirtual 75	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   542: istore_1
      //   543: iload_1
      //   544: ifeq +6 -> 550
      //   547: goto +374 -> 921
      //   550: aload 8
      //   552: astore_3
      //   553: aload_2
      //   554: ifnull +42 -> 596
      //   557: aload_2
      //   558: instanceof 77
      //   561: ifeq +14 -> 575
      //   564: aload_2
      //   565: checkcast 77	javax/net/ssl/HttpsURLConnection
      //   568: astore_3
      //   569: aload 6
      //   571: astore_2
      //   572: goto -147 -> 425
      //   575: aload 8
      //   577: astore_3
      //   578: aload_2
      //   579: instanceof 189
      //   582: ifeq +14 -> 596
      //   585: aload_2
      //   586: checkcast 189	java/net/HttpURLConnection
      //   589: astore_3
      //   590: aload 7
      //   592: astore_2
      //   593: goto -143 -> 450
      //   596: aload_3
      //   597: ifnonnull +94 -> 691
      //   600: aload_0
      //   601: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   604: getfield 88	com/appodeal/ads/aa:A	Z
      //   607: ifeq +71 -> 678
      //   610: aload 10
      //   612: aload_0
      //   613: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   616: getfield 92	com/appodeal/ads/aa:d	Ljava/lang/String;
      //   619: invokeinterface 98 2 0
      //   624: ifeq +54 -> 678
      //   627: aload_0
      //   628: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   631: aload 10
      //   633: aload_0
      //   634: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   637: getfield 92	com/appodeal/ads/aa:d	Ljava/lang/String;
      //   640: invokevirtual 200	com/appodeal/ads/aa:a	(Landroid/content/SharedPreferences;Ljava/lang/String;)Z
      //   643: ifeq +35 -> 678
      //   646: new 202	com/appodeal/ads/utils/c/a
      //   649: dup
      //   650: ldc -52
      //   652: invokespecial 207	com/appodeal/ads/utils/c/a:<init>	(Ljava/lang/String;)V
      //   655: invokestatic 150	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   658: aload 10
      //   660: aload_0
      //   661: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   664: getfield 92	com/appodeal/ads/aa:d	Ljava/lang/String;
      //   667: ldc -47
      //   669: invokeinterface 213 3 0
      //   674: astore_2
      //   675: goto +47 -> 722
      //   678: aload_0
      //   679: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   682: invokestatic 46	com/appodeal/ads/aa:a	(Lcom/appodeal/ads/aa;)Landroid/os/Handler;
      //   685: iconst_0
      //   686: invokevirtual 52	android/os/Handler:sendEmptyMessage	(I)Z
      //   689: pop
      //   690: return
      //   691: aload_3
      //   692: astore_2
      //   693: aload_0
      //   694: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   697: getfield 88	com/appodeal/ads/aa:A	Z
      //   700: ifeq +22 -> 722
      //   703: aload_0
      //   704: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   707: aload 10
      //   709: aload_0
      //   710: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   713: getfield 92	com/appodeal/ads/aa:d	Ljava/lang/String;
      //   716: aload_3
      //   717: invokevirtual 216	com/appodeal/ads/aa:a	(Landroid/content/SharedPreferences;Ljava/lang/String;Ljava/lang/String;)V
      //   720: aload_3
      //   721: astore_2
      //   722: new 129	org/json/JSONObject
      //   725: dup
      //   726: aload_2
      //   727: invokespecial 217	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   730: astore_3
      //   731: aload_0
      //   732: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   735: getfield 88	com/appodeal/ads/aa:A	Z
      //   738: ifeq +13 -> 751
      //   741: aload_2
      //   742: getstatic 223	com/appodeal/ads/utils/Log$LogLevel:verbose	Lcom/appodeal/ads/utils/Log$LogLevel;
      //   745: invokestatic 226	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;Lcom/appodeal/ads/utils/Log$LogLevel;)V
      //   748: goto +7 -> 755
      //   751: aload_2
      //   752: invokestatic 228	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   755: aload_0
      //   756: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   759: getfield 88	com/appodeal/ads/aa:A	Z
      //   762: ifeq +115 -> 877
      //   765: aload_0
      //   766: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   769: getfield 32	com/appodeal/ads/aa:b	Landroid/content/Context;
      //   772: aload_3
      //   773: ldc -26
      //   775: invokevirtual 234	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   778: invokestatic 239	com/appodeal/ads/UserSettings:a	(Landroid/content/Context;Lorg/json/JSONObject;)V
      //   781: aload_0
      //   782: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   785: getfield 32	com/appodeal/ads/aa:b	Landroid/content/Context;
      //   788: aload_3
      //   789: ldc -15
      //   791: invokevirtual 234	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   794: aload_3
      //   795: ldc -13
      //   797: invokevirtual 246	org/json/JSONObject:optBoolean	(Ljava/lang/String;)Z
      //   800: aload_3
      //   801: ldc -8
      //   803: invokevirtual 246	org/json/JSONObject:optBoolean	(Ljava/lang/String;)Z
      //   806: invokestatic 253	com/appodeal/ads/h:a	(Landroid/content/Context;Lorg/json/JSONObject;ZZ)V
      //   809: aload_0
      //   810: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   813: aload 10
      //   815: aload_3
      //   816: ldc -1
      //   818: ldc_w 256
      //   821: invokevirtual 260	org/json/JSONObject:optInt	(Ljava/lang/String;I)I
      //   824: aload_0
      //   825: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   828: getfield 92	com/appodeal/ads/aa:d	Ljava/lang/String;
      //   831: invokevirtual 263	com/appodeal/ads/aa:a	(Landroid/content/SharedPreferences;ILjava/lang/String;)V
      //   834: aload_0
      //   835: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   838: aload_3
      //   839: invokestatic 266	com/appodeal/ads/aa:a	(Lcom/appodeal/ads/aa;Lorg/json/JSONObject;)V
      //   842: aload_3
      //   843: ldc_w 268
      //   846: invokevirtual 272	org/json/JSONObject:optJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   849: invokestatic 277	com/appodeal/ads/f/d:a	(Lorg/json/JSONArray;)V
      //   852: invokestatic 280	com/appodeal/ads/f/d:c	()V
      //   855: getstatic 284	com/appodeal/ads/Appodeal:g	Lcom/appodeal/ads/utils/t;
      //   858: ifnull +19 -> 877
      //   861: getstatic 284	com/appodeal/ads/Appodeal:g	Lcom/appodeal/ads/utils/t;
      //   864: invokeinterface 288 1 0
      //   869: goto +8 -> 877
      //   872: astore_2
      //   873: aload_2
      //   874: invokestatic 150	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   877: aload_0
      //   878: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   881: invokestatic 46	com/appodeal/ads/aa:a	(Lcom/appodeal/ads/aa;)Landroid/os/Handler;
      //   884: iconst_1
      //   885: aload_3
      //   886: invokevirtual 292	android/os/Handler:obtainMessage	(ILjava/lang/Object;)Landroid/os/Message;
      //   889: astore_2
      //   890: aload_0
      //   891: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   894: invokestatic 46	com/appodeal/ads/aa:a	(Lcom/appodeal/ads/aa;)Landroid/os/Handler;
      //   897: aload_2
      //   898: invokevirtual 296	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   901: pop
      //   902: return
      //   903: astore_2
      //   904: aload_2
      //   905: invokestatic 150	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   908: aload_0
      //   909: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   912: invokestatic 46	com/appodeal/ads/aa:a	(Lcom/appodeal/ads/aa;)Landroid/os/Handler;
      //   915: iconst_0
      //   916: invokevirtual 52	android/os/Handler:sendEmptyMessage	(I)Z
      //   919: pop
      //   920: return
      //   921: aload_2
      //   922: astore_3
      //   923: aload_0
      //   924: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   927: invokestatic 46	com/appodeal/ads/aa:a	(Lcom/appodeal/ads/aa;)Landroid/os/Handler;
      //   930: iconst_0
      //   931: invokevirtual 52	android/os/Handler:sendEmptyMessage	(I)Z
      //   934: pop
      //   935: aload_2
      //   936: ifnull +88 -> 1024
      //   939: aload_2
      //   940: instanceof 77
      //   943: ifeq +11 -> 954
      //   946: aload_2
      //   947: checkcast 77	javax/net/ssl/HttpsURLConnection
      //   950: invokevirtual 187	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   953: return
      //   954: aload_2
      //   955: instanceof 189
      //   958: ifeq +66 -> 1024
      //   961: aload_2
      //   962: checkcast 189	java/net/HttpURLConnection
      //   965: invokevirtual 190	java/net/HttpURLConnection:disconnect	()V
      //   968: return
      //   969: aload_3
      //   970: ifnull +34 -> 1004
      //   973: aload_3
      //   974: instanceof 77
      //   977: ifeq +13 -> 990
      //   980: aload_3
      //   981: checkcast 77	javax/net/ssl/HttpsURLConnection
      //   984: invokevirtual 187	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   987: goto +17 -> 1004
      //   990: aload_3
      //   991: instanceof 189
      //   994: ifeq +10 -> 1004
      //   997: aload_3
      //   998: checkcast 189	java/net/HttpURLConnection
      //   1001: invokevirtual 190	java/net/HttpURLConnection:disconnect	()V
      //   1004: aload_2
      //   1005: athrow
      //   1006: astore_2
      //   1007: aload_2
      //   1008: invokestatic 150	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   1011: aload_0
      //   1012: getfield 15	com/appodeal/ads/aa$b:a	Lcom/appodeal/ads/aa;
      //   1015: invokestatic 46	com/appodeal/ads/aa:a	(Lcom/appodeal/ads/aa;)Landroid/os/Handler;
      //   1018: iconst_0
      //   1019: invokevirtual 52	android/os/Handler:sendEmptyMessage	(I)Z
      //   1022: pop
      //   1023: return
      //   1024: return
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	1025	0	this	b
      //   390	154	1	bool	boolean
      //   13	256	2	localObject1	Object
      //   291	5	2	localException1	Exception
      //   311	144	2	localObject2	Object
      //   459	26	2	localObject3	Object
      //   486	1	2	localObject4	Object
      //   494	1	2	localObject5	Object
      //   498	1	2	localObject6	Object
      //   507	245	2	localObject7	Object
      //   872	2	2	localException2	Exception
      //   889	9	2	localMessage	Message
      //   903	102	2	localJSONException	JSONException
      //   1006	2	2	localException3	Exception
      //   85	913	3	localObject8	Object
      //   81	412	4	localURLConnection	java.net.URLConnection
      //   74	322	5	localObject9	Object
      //   471	6	5	localException4	Exception
      //   490	1	5	localIOException1	java.io.IOException
      //   504	29	5	localIOException2	java.io.IOException
      //   65	505	6	localObject10	Object
      //   68	523	7	localObject11	Object
      //   71	505	8	localObject12	Object
      //   47	355	9	localObject13	Object
      //   62	752	10	localSharedPreferences	SharedPreferences
      //   264	200	11	localGZIPOutputStream	java.util.zip.GZIPOutputStream
      // Exception table:
      //   from	to	target	type
      //   283	288	291	java/lang/Exception
      //   266	280	459	finally
      //   463	468	471	java/lang/Exception
      //   86	99	486	finally
      //   102	117	486	finally
      //   120	130	486	finally
      //   133	150	486	finally
      //   153	161	486	finally
      //   164	172	486	finally
      //   178	186	486	finally
      //   189	197	486	finally
      //   200	208	486	finally
      //   211	219	486	finally
      //   222	231	486	finally
      //   234	240	486	finally
      //   243	252	486	finally
      //   255	266	486	finally
      //   283	288	486	finally
      //   295	299	486	finally
      //   302	312	486	finally
      //   315	324	486	finally
      //   327	337	486	finally
      //   351	359	486	finally
      //   365	375	486	finally
      //   378	391	486	finally
      //   463	468	486	finally
      //   476	481	486	finally
      //   484	486	486	finally
      //   510	515	486	finally
      //   517	530	486	finally
      //   532	543	486	finally
      //   923	935	486	finally
      //   86	99	490	java/io/IOException
      //   102	117	490	java/io/IOException
      //   120	130	490	java/io/IOException
      //   133	150	490	java/io/IOException
      //   153	161	490	java/io/IOException
      //   164	172	490	java/io/IOException
      //   178	186	490	java/io/IOException
      //   189	197	490	java/io/IOException
      //   200	208	490	java/io/IOException
      //   211	219	490	java/io/IOException
      //   222	231	490	java/io/IOException
      //   234	240	490	java/io/IOException
      //   243	252	490	java/io/IOException
      //   255	266	490	java/io/IOException
      //   283	288	490	java/io/IOException
      //   295	299	490	java/io/IOException
      //   302	312	490	java/io/IOException
      //   315	324	490	java/io/IOException
      //   327	337	490	java/io/IOException
      //   351	359	490	java/io/IOException
      //   365	375	490	java/io/IOException
      //   378	391	490	java/io/IOException
      //   463	468	490	java/io/IOException
      //   476	481	490	java/io/IOException
      //   484	486	490	java/io/IOException
      //   76	83	498	finally
      //   76	83	504	java/io/IOException
      //   755	869	872	java/lang/Exception
      //   722	748	903	org/json/JSONException
      //   751	755	903	org/json/JSONException
      //   0	23	1006	java/lang/Exception
      //   27	39	1006	java/lang/Exception
      //   40	64	1006	java/lang/Exception
      //   411	425	1006	java/lang/Exception
      //   425	429	1006	java/lang/Exception
      //   436	450	1006	java/lang/Exception
      //   450	454	1006	java/lang/Exception
      //   557	569	1006	java/lang/Exception
      //   578	590	1006	java/lang/Exception
      //   600	675	1006	java/lang/Exception
      //   678	690	1006	java/lang/Exception
      //   693	720	1006	java/lang/Exception
      //   722	748	1006	java/lang/Exception
      //   751	755	1006	java/lang/Exception
      //   873	877	1006	java/lang/Exception
      //   877	902	1006	java/lang/Exception
      //   904	920	1006	java/lang/Exception
      //   939	953	1006	java/lang/Exception
      //   954	968	1006	java/lang/Exception
      //   973	987	1006	java/lang/Exception
      //   990	1004	1006	java/lang/Exception
      //   1004	1006	1006	java/lang/Exception
    }
  }
  
  public static class c
  {
    private final Context a;
    private final int b;
    private final String c;
    private aa.a d;
    private String e;
    private String f;
    private String g;
    private com.appodeal.ads.d.h h;
    private c i;
    private long j;
    private String k;
    private Long l;
    private int m = 1;
    private double n = -1.0D;
    private boolean o;
    private String p;
    private JSONObject q;
    private JSONObject r;
    
    public c(Context paramContext, int paramInt, String paramString)
    {
      this.a = paramContext;
      this.b = paramInt;
      this.c = paramString;
    }
    
    public c a(double paramDouble)
    {
      this.n = paramDouble;
      return this;
    }
    
    public c a(int paramInt)
    {
      this.m = paramInt;
      return this;
    }
    
    public c a(long paramLong)
    {
      this.j = paramLong;
      return this;
    }
    
    public c a(aa.a paramA)
    {
      this.d = paramA;
      return this;
    }
    
    public c a(com.appodeal.ads.d.h paramH)
    {
      this.h = paramH;
      return this;
    }
    
    public c a(c paramC)
    {
      this.i = paramC;
      return this;
    }
    
    public c a(Long paramLong)
    {
      this.l = paramLong;
      return this;
    }
    
    public c a(String paramString)
    {
      this.e = paramString;
      return this;
    }
    
    public c a(JSONObject paramJSONObject)
    {
      this.q = paramJSONObject;
      return this;
    }
    
    public c a(boolean paramBoolean)
    {
      this.o = paramBoolean;
      return this;
    }
    
    public aa a()
    {
      return new aa(this, null);
    }
    
    public c b(String paramString)
    {
      this.f = paramString;
      return this;
    }
    
    public c b(JSONObject paramJSONObject)
    {
      this.r = paramJSONObject;
      return this;
    }
    
    public c c(String paramString)
    {
      this.g = paramString;
      return this;
    }
    
    public c d(String paramString)
    {
      this.k = paramString;
      return this;
    }
    
    public c e(String paramString)
    {
      this.p = paramString;
      return this;
    }
  }
}
