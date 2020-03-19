package com.appodeal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Pair;
import com.appodeal.ads.f.c;
import com.appodeal.ads.f.f;
import com.appodeal.ads.utils.d;
import com.appodeal.ads.utils.e;
import com.appodeal.ads.utils.p;
import com.appodeal.ads.utils.s;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class t
{
  @VisibleForTesting
  static JSONObject o;
  static SSLSocketFactory p;
  @VisibleForTesting
  final a a;
  @VisibleForTesting
  final Context b;
  @VisibleForTesting
  final int c;
  @VisibleForTesting
  final String d;
  @VisibleForTesting
  final String e;
  @VisibleForTesting
  final String f;
  @VisibleForTesting
  final String g;
  @VisibleForTesting
  final com.appodeal.ads.d.h h;
  @VisibleForTesting
  final c i;
  @VisibleForTesting
  final long j;
  @VisibleForTesting
  final String k;
  @VisibleForTesting
  final Long l;
  @VisibleForTesting
  final int m;
  @VisibleForTesting
  final double n;
  @VisibleForTesting
  boolean q;
  @VisibleForTesting
  boolean r;
  @VisibleForTesting
  boolean s;
  @VisibleForTesting
  boolean t;
  @VisibleForTesting
  boolean u;
  @VisibleForTesting
  boolean v;
  @VisibleForTesting
  boolean w;
  @VisibleForTesting
  boolean x;
  @VisibleForTesting
  boolean y;
  
  private t(c paramC)
  {
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
    if (this.d == null) {
      return;
    }
    if ((AppodealSettings.a) && ((this.a == null) || ((this.a instanceof com.appodeal.ads.utils.v))) && ((this.d.equals("stats")) || (this.d.equals("show")) || (this.d.equals("click")) || (this.d.equals("finish")) || (this.d.equals("install")))) {
      return;
    }
    boolean bool1 = this.d.equals("banner");
    boolean bool2 = false;
    if ((!bool1) && (!this.d.equals("debug"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.q = bool1;
    if ((!this.d.equals("banner_320")) && (!this.d.equals("debug_banner_320"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.r = bool1;
    if ((!this.d.equals("banner_mrec")) && (!this.d.equals("debug_mrec"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.s = bool1;
    if ((!this.d.equals("video")) && (!this.d.equals("debug_video"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.t = bool1;
    if ((!this.d.equals("rewarded_video")) && (!this.d.equals("debug_rewarded_video"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.u = bool1;
    if ((!this.d.equals("native")) && (!this.d.equals("debug_native"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.v = bool1;
    if ((!this.d.equals("debug")) && (!this.d.equals("debug_banner_320")) && (!this.d.equals("debug_video")) && (!this.d.equals("debug_rewarded_video")) && (!this.d.equals("debug_mrec")) && (!this.d.equals("debug_native"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.w = bool1;
    if ((!this.q) && (!this.r) && (!this.s) && (!this.t) && (!this.u) && (!this.v)) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.x = bool1;
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
            if (!this.d.equals("install")) {
              bool1 = true;
            }
          }
        }
      }
    }
    this.y = bool1;
  }
  
  private void a(JSONObject paramJSONObject)
  {
    JSONArray localJSONArray = paramJSONObject.optJSONArray("segments");
    if (localJSONArray != null)
    {
      if (localJSONArray.length() == 0) {
        return;
      }
      paramJSONObject = new com.appodeal.ads.f.g(this.b, paramJSONObject);
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
        com.appodeal.ads.f.g.a(paramJSONObject);
      }
      if (Appodeal.d != null) {
        Appodeal.d.a();
      }
    }
  }
  
  private SSLSocketFactory d()
  {
    try
    {
      if (p == null)
      {
        localObject1 = new ArrayList();
        ((List)localObject1).add("A1ABC1296E644B3A25179FCD3E277C8D36039BEE94478E2F5104FA4244237F54");
        ((List)localObject1).add("E91093227F02CE854C3214749DC7FB3459E0E43E80CAE27F01AA0EA92894C9E1");
        localObject1 = new e((List)localObject1, 1494633600000L);
        SSLContext localSSLContext = SSLContext.getInstance("TLSv1");
        localSSLContext.init(null, new TrustManager[] { localObject1 }, null);
        p = localSSLContext.getSocketFactory();
      }
      Object localObject1 = p;
      return localObject1;
    }
    finally {}
  }
  
  @Nullable
  @VisibleForTesting
  JSONObject a(SharedPreferences paramSharedPreferences)
  {
    JSONObject localJSONObject = b(paramSharedPreferences);
    if (localJSONObject == null) {
      return null;
    }
    Object localObject3;
    Object localObject4;
    try
    {
      if (this.q) {
        localJSONObject.put("type", "banner");
      }
      if (this.r)
      {
        localJSONObject.put("type", "banner_320");
        float f1 = an.g(this.b);
        float f2 = an.h(this.b);
        if ((g.t) && (f1 >= 728.0F) && (f2 > 720.0F)) {
          localJSONObject.put("large_banners", true);
        }
      }
      if (this.s) {
        localJSONObject.put("type", "banner_mrec");
      }
      if ((this.t) || (this.u)) {
        localJSONObject.put("type", "video");
      }
      if (this.u) {
        localJSONObject.put("rewarded_video", true);
      }
      if (this.v) {
        localJSONObject.put("type", "native");
      }
      if (this.w) {
        localJSONObject.put("debug", true);
      }
      if (AppodealSettings.a) {
        localJSONObject.put("test", true);
      }
      Object localObject1 = an.d(this.b);
      localJSONObject.put("lt", ((Pair)localObject1).first);
      localJSONObject.put("lat", ((Pair)((Pair)localObject1).second).first);
      localJSONObject.put("lon", ((Pair)((Pair)localObject1).second).second);
      localObject1 = an.b(this.b);
      localJSONObject.put("connection", ((an.a)localObject1).a);
      localJSONObject.put("battery", an.k(this.b));
      localJSONObject.put("connection_subtype", ((an.a)localObject1).b);
      localJSONObject.put("connection_fast", ((an.a)localObject1).c);
      localJSONObject.put("crr", an.c(this.b));
      localJSONObject.put("locale", Locale.getDefault().toString());
      localObject1 = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.ENGLISH);
      localJSONObject.put("timezone", new SimpleDateFormat("Z", Locale.ENGLISH).format(((Calendar)localObject1).getTime()));
      localJSONObject.put("local_time", System.currentTimeMillis() / 1000L);
      localJSONObject.put("user_agent", System.getProperty("http.agent"));
      d.c(this.b);
      localJSONObject.put("session_id", d.a(paramSharedPreferences));
      localJSONObject.put("session_uptime", d.b());
      localJSONObject.put("app_uptime", d.b(paramSharedPreferences));
      localObject1 = paramSharedPreferences.getString("inapps", null);
      if (localObject1 != null) {
        localJSONObject.put("inapps", new JSONObject((String)localObject1));
      }
      if (this.x)
      {
        localObject1 = new JSONArray();
        if (this.q)
        {
          localObject3 = n.a(this.b).iterator();
          while (((Iterator)localObject3).hasNext())
          {
            localObject4 = (o)((Iterator)localObject3).next();
            if (((o)localObject4).g() != null) {
              ((JSONArray)localObject1).put(((o)localObject4).a());
            }
          }
        }
        if (this.t)
        {
          localObject3 = ah.a(this.b).iterator();
          while (((Iterator)localObject3).hasNext())
          {
            localObject4 = (ap)((Iterator)localObject3).next();
            if (((ap)localObject4).g() != null) {
              ((JSONArray)localObject1).put(((ap)localObject4).a());
            }
          }
        }
        if (this.u)
        {
          localObject3 = ak.a(this.b).iterator();
          while (((Iterator)localObject3).hasNext())
          {
            localObject4 = (ap)((Iterator)localObject3).next();
            if (((ap)localObject4).g() != null) {
              ((JSONArray)localObject1).put(((ap)localObject4).a());
            }
          }
        }
        if (this.r)
        {
          localObject3 = g.a(this.b).iterator();
          while (((Iterator)localObject3).hasNext())
          {
            localObject4 = (h)((Iterator)localObject3).next();
            if (((h)localObject4).f() != null) {
              ((JSONArray)localObject1).put(((h)localObject4).a());
            }
          }
        }
        if (this.s)
        {
          localObject3 = v.a(this.b).iterator();
          while (((Iterator)localObject3).hasNext())
          {
            localObject4 = (w)((Iterator)localObject3).next();
            if (((w)localObject4).f() != null) {
              ((JSONArray)localObject1).put(((w)localObject4).a());
            }
          }
        }
        if (this.v)
        {
          localObject3 = Native.a(this.b).iterator();
          while (((Iterator)localObject3).hasNext())
          {
            localObject4 = (ac)((Iterator)localObject3).next();
            if (localObject4 != null) {
              ((JSONArray)localObject1).put(((ac)localObject4).a());
            }
          }
        }
        localJSONObject.put("show_array", localObject1);
      }
      if (this.g != null) {
        localJSONObject.put("loaded_offer", this.g);
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
      localJSONObject.put("id", this.e);
      localJSONObject.put("main_id", this.f);
      if ((this.x) || (this.k != null)) {
        localJSONObject.put("ad_stats", c());
      }
      if (this.i != null) {
        localJSONObject.put("placement_id", this.i.a());
      }
    }
    catch (JSONException localJSONException)
    {
      Appodeal.a(localJSONException);
    }
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramSharedPreferences.getLong("lastSettingsTime", 0L));
    localCalendar.add(5, 1);
    if ((!UserSettings.sendingInProgress) && (this.x) && ((localCalendar.getTimeInMillis() < System.currentTimeMillis()) || (paramSharedPreferences.getBoolean("should_update_user_settings", true))))
    {
      UserSettings.sendingInProgress = true;
      try
      {
        localJSONObject.put("sa", s.a(this.b));
      }
      catch (Exception localException1)
      {
        Appodeal.a(localException1);
      }
      try
      {
        localJSONObject.put("user_settings", Appodeal.getUserSettings(this.b).a());
      }
      catch (Exception localException2)
      {
        Appodeal.a(localException2);
      }
      localObject2 = paramSharedPreferences.edit();
      ((SharedPreferences.Editor)localObject2).putLong("lastSettingsTime", System.currentTimeMillis());
      ((SharedPreferences.Editor)localObject2).putBoolean("should_update_user_settings", false);
      ((SharedPreferences.Editor)localObject2).apply();
      UserSettings.sendingInProgress = false;
    }
    Object localObject2 = Calendar.getInstance();
    ((Calendar)localObject2).setTimeInMillis(paramSharedPreferences.getLong("lastAppTime", 0L));
    ((Calendar)localObject2).add(5, 1);
    if ((!AppodealSettings.n) && (AppodealSettings.m) && (this.x) && (((Calendar)localObject2).getTimeInMillis() < System.currentTimeMillis()))
    {
      AppodealSettings.n = true;
      try
      {
        localObject2 = new JSONArray();
        localObject4 = this.b.getPackageManager().getInstalledApplications(0);
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
      AppodealSettings.n = false;
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
        if (this.h.f())
        {
          if (!this.h.d())
          {
            Appodeal.a("/get error, rtb invalid check");
            return null;
          }
          localJSONObject.put("rtb_check", this.h.c());
        }
      }
      else {
        for (paramSharedPreferences = "bidder_id";; paramSharedPreferences = "id")
        {
          str1 = this.h.e();
          break;
          if (((!this.d.equals("click")) && (!this.d.equals("finish"))) || (!this.h.f())) {
            break label1745;
          }
        }
      }
    }
    label1745:
    return localJSONObject;
  }
  
  public void a()
  {
    if ((this.b instanceof Activity))
    {
      ((Activity)this.b).runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (Build.VERSION.SDK_INT >= 11)
          {
            new t.b(t.this, null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
          }
          new t.b(t.this, null).execute(new Void[0]);
        }
      });
      return;
    }
    if (Build.VERSION.SDK_INT >= 11)
    {
      new b(null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
      return;
    }
    new b(null).execute(new Void[0]);
  }
  
  URL b()
  {
    if (this.y) {
      return new URL(String.format("%s%s:%s/%s", new Object[] { com.appodeal.ads.utils.g.a(com.appodeal.ads.utils.g.c()), com.appodeal.ads.utils.g.b(), Integer.valueOf(com.appodeal.ads.utils.g.c()), "get" }));
    }
    return new URL(String.format("%s%s:%s/%s", new Object[] { com.appodeal.ads.utils.g.a(com.appodeal.ads.utils.g.c()), com.appodeal.ads.utils.g.b(), Integer.valueOf(com.appodeal.ads.utils.g.c()), this.d }));
  }
  
  @Nullable
  @VisibleForTesting
  JSONObject b(SharedPreferences paramSharedPreferences)
  {
    for (;;)
    {
      try
      {
        if (o == null)
        {
          o = new JSONObject();
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
                    break label720;
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
            paramSharedPreferences = an.l(this.b);
          }
          o.put("app_key", str2);
          o.put("android", Build.VERSION.RELEASE);
          o.put("android_level", Build.VERSION.SDK_INT);
          o.put("sdk", "1.15.9");
          localObject4 = this.b.getPackageName();
          o.put("package", localObject4);
          try
          {
            localObject1 = localPackageManager.getPackageInfo((String)localObject4, 0);
            o.put("package_version", ((PackageInfo)localObject1).versionName);
            o.put("package_code", ((PackageInfo)localObject1).versionCode);
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            Appodeal.a(localNameNotFoundException);
          }
          try
          {
            ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo((String)localObject4, 128);
            o.put("framework", localApplicationInfo.metaData.getString("com.appodeal.framework"));
          }
          catch (Exception localException)
          {
            Appodeal.a(localException);
          }
          o.put("android_id", paramSharedPreferences);
          o.put("advertising_tracking", localObject3);
          paramSharedPreferences = an.f(this.b);
          o.put("width", paramSharedPreferences.first);
          o.put("height", paramSharedPreferences.second);
          if (an.n(this.b))
          {
            paramSharedPreferences = o;
            localObject2 = "tablet";
            paramSharedPreferences.put("device_type", localObject2);
          }
          else
          {
            paramSharedPreferences = o;
            localObject2 = "phone";
            continue;
          }
          if (!Build.MANUFACTURER.equals("Amazon")) {
            break label728;
          }
          paramSharedPreferences = "amazon";
          o.put("platform", paramSharedPreferences);
          try
          {
            localObject2 = localPackageManager.getInstallerPackageName((String)localObject4);
            paramSharedPreferences = (SharedPreferences)localObject2;
            if (localObject2 == null) {
              paramSharedPreferences = "unknown";
            }
            o.put("installer", paramSharedPreferences);
          }
          catch (Exception paramSharedPreferences)
          {
            Appodeal.a(paramSharedPreferences);
          }
          o.put("manufacturer", Build.MANUFACTURER);
          o.put("model", String.format("%s %s", new Object[] { Build.MANUFACTURER, Build.MODEL }));
          o.put("rooted", an.a());
        }
        paramSharedPreferences = new JSONObject();
        Object localObject2 = o.keys();
        if (((Iterator)localObject2).hasNext())
        {
          localObject3 = (String)((Iterator)localObject2).next();
          paramSharedPreferences.put((String)localObject3, o.get((String)localObject3));
          continue;
        }
        return paramSharedPreferences;
      }
      finally {}
      label720:
      Object localObject3 = "1";
      continue;
      label728:
      paramSharedPreferences = "google";
    }
  }
  
  @VisibleForTesting
  JSONObject c()
  {
    JSONObject localJSONObject = new JSONObject();
    int i1 = g.y;
    int i2 = n.q;
    int i3 = ah.n;
    int i4 = ak.n;
    int i5 = Native.p;
    int i6 = v.u;
    int i7 = g.z;
    int i8 = n.r;
    int i9 = ah.p;
    int i10 = ak.p;
    int i11 = Native.q;
    int i12 = v.v;
    int i13 = ak.o;
    int i14 = ah.o;
    try
    {
      localJSONObject.put("show", i1 + i2 + i3 + i4 + i5 + i6);
      localJSONObject.put("click", i7 + i8 + i9 + i10 + i11 + i12);
      if ((this.t) || (this.u) || ((this.k != null) && ((this.k.equals("video")) || (this.k.equals("rewarded_video"))))) {
        localJSONObject.put("finish", i13 + i14);
      }
      if ((this.q) || ((this.k != null) && (this.k.equals("banner"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "show" }), n.q);
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "click" }), n.r);
      }
      if ((this.t) || ((this.k != null) && (this.k.equals("video"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "show" }), ah.n);
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "click" }), ah.p);
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "finish" }), ah.o);
      }
      if ((this.u) || ((this.k != null) && (this.k.equals("rewarded_video"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "show" }), ak.n);
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "click" }), ak.p);
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "finish" }), ak.o);
      }
      if ((this.r) || ((this.k != null) && (this.k.equals("banner_320"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "show" }), g.y);
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "click" }), g.z);
      }
      if ((this.s) || ((this.k != null) && (this.k.equals("banner_mrec"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "show" }), v.u);
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "click" }), v.v);
      }
      if ((this.v) || ((this.k != null) && (this.k.equals("native"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "native", "show" }), Native.p);
        localJSONObject.put(String.format("%s_%s", new Object[] { "native", "click" }), Native.q);
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
    extends AsyncTask<Void, Void, JSONObject>
  {
    private b() {}
    
    /* Error */
    protected JSONObject a(Void... paramVarArgs)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   4: getfield 31	com/appodeal/ads/t:b	Landroid/content/Context;
      //   7: ldc 33
      //   9: iconst_0
      //   10: invokevirtual 39	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   13: astore_1
      //   14: aload_0
      //   15: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   18: aload_1
      //   19: invokevirtual 42	com/appodeal/ads/t:a	(Landroid/content/SharedPreferences;)Lorg/json/JSONObject;
      //   22: astore_3
      //   23: aload_3
      //   24: ifnonnull +5 -> 29
      //   27: aconst_null
      //   28: areturn
      //   29: aload_0
      //   30: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   33: invokevirtual 45	com/appodeal/ads/t:b	()Ljava/net/URL;
      //   36: astore 5
      //   38: aload_0
      //   39: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   42: getfield 31	com/appodeal/ads/t:b	Landroid/content/Context;
      //   45: ldc 47
      //   47: iconst_0
      //   48: invokevirtual 39	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   51: astore 6
      //   53: aload 5
      //   55: invokevirtual 53	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   58: astore 4
      //   60: aload 4
      //   62: astore_1
      //   63: aload 5
      //   65: invokevirtual 57	java/net/URL:getProtocol	()Ljava/lang/String;
      //   68: ldc 59
      //   70: invokevirtual 65	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   73: ifeq +21 -> 94
      //   76: aload 4
      //   78: astore_1
      //   79: aload 4
      //   81: checkcast 67	javax/net/ssl/HttpsURLConnection
      //   84: aload_0
      //   85: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   88: invokestatic 70	com/appodeal/ads/t:a	(Lcom/appodeal/ads/t;)Ljavax/net/ssl/SSLSocketFactory;
      //   91: invokevirtual 74	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
      //   94: aload 4
      //   96: astore_1
      //   97: aload_0
      //   98: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   101: getfield 78	com/appodeal/ads/t:y	Z
      //   104: ifeq +48 -> 152
      //   107: aload 4
      //   109: astore_1
      //   110: aload 6
      //   112: aload_0
      //   113: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   116: getfield 82	com/appodeal/ads/t:d	Ljava/lang/String;
      //   119: invokeinterface 88 2 0
      //   124: ifeq +28 -> 152
      //   127: aload 4
      //   129: astore_1
      //   130: aload 4
      //   132: sipush 10000
      //   135: invokevirtual 94	java/net/URLConnection:setConnectTimeout	(I)V
      //   138: aload 4
      //   140: astore_1
      //   141: aload 4
      //   143: sipush 10000
      //   146: invokevirtual 97	java/net/URLConnection:setReadTimeout	(I)V
      //   149: goto +25 -> 174
      //   152: aload 4
      //   154: astore_1
      //   155: aload 4
      //   157: sipush 20000
      //   160: invokevirtual 94	java/net/URLConnection:setConnectTimeout	(I)V
      //   163: aload 4
      //   165: astore_1
      //   166: aload 4
      //   168: sipush 20000
      //   171: invokevirtual 97	java/net/URLConnection:setReadTimeout	(I)V
      //   174: aload 4
      //   176: astore_1
      //   177: aload 4
      //   179: sipush 20000
      //   182: invokevirtual 94	java/net/URLConnection:setConnectTimeout	(I)V
      //   185: aload 4
      //   187: astore_1
      //   188: aload 4
      //   190: sipush 20000
      //   193: invokevirtual 97	java/net/URLConnection:setReadTimeout	(I)V
      //   196: aload 4
      //   198: astore_1
      //   199: aload 4
      //   201: ldc 99
      //   203: ldc 101
      //   205: invokevirtual 105	java/net/URLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   208: aload 4
      //   210: astore_1
      //   211: aload 4
      //   213: iconst_1
      //   214: invokevirtual 109	java/net/URLConnection:setDoOutput	(Z)V
      //   217: aload 4
      //   219: astore_1
      //   220: new 111	java/io/ByteArrayOutputStream
      //   223: dup
      //   224: invokespecial 112	java/io/ByteArrayOutputStream:<init>	()V
      //   227: astore 5
      //   229: aload 4
      //   231: astore_1
      //   232: new 114	java/util/zip/GZIPOutputStream
      //   235: dup
      //   236: aload 5
      //   238: invokespecial 117	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   241: astore 7
      //   243: aload 7
      //   245: aload_3
      //   246: invokevirtual 122	org/json/JSONObject:toString	()Ljava/lang/String;
      //   249: ldc 124
      //   251: invokevirtual 128	java/lang/String:getBytes	(Ljava/lang/String;)[B
      //   254: invokevirtual 132	java/util/zip/GZIPOutputStream:write	([B)V
      //   257: aload 4
      //   259: astore_1
      //   260: aload 7
      //   262: invokevirtual 135	java/util/zip/GZIPOutputStream:close	()V
      //   265: goto +11 -> 276
      //   268: astore_3
      //   269: aload 4
      //   271: astore_1
      //   272: aload_3
      //   273: invokestatic 140	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   276: aload 4
      //   278: astore_1
      //   279: aload 5
      //   281: invokevirtual 144	java/io/ByteArrayOutputStream:toByteArray	()[B
      //   284: iconst_0
      //   285: invokestatic 150	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
      //   288: astore_3
      //   289: aload 4
      //   291: astore_1
      //   292: aload 4
      //   294: invokevirtual 154	java/net/URLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   297: aload_3
      //   298: invokestatic 159	com/appodeal/ads/an:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   301: aload 4
      //   303: astore_1
      //   304: aload 4
      //   306: invokevirtual 163	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
      //   309: invokestatic 166	com/appodeal/ads/an:a	(Ljava/io/InputStream;)Ljava/lang/String;
      //   312: astore_3
      //   313: aload_3
      //   314: ifnull +45 -> 359
      //   317: aload 4
      //   319: astore_1
      //   320: aload_3
      //   321: invokevirtual 170	java/lang/String:isEmpty	()Z
      //   324: ifne +35 -> 359
      //   327: aload 4
      //   329: astore_1
      //   330: aload_3
      //   331: ldc -84
      //   333: invokevirtual 65	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   336: ifne +23 -> 359
      //   339: aload 4
      //   341: astore_1
      //   342: aload_0
      //   343: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   346: getfield 82	com/appodeal/ads/t:d	Ljava/lang/String;
      //   349: ldc -82
      //   351: invokevirtual 65	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   354: istore_2
      //   355: iload_2
      //   356: ifeq +5 -> 361
      //   359: aconst_null
      //   360: astore_3
      //   361: aload_3
      //   362: astore_1
      //   363: aload 4
      //   365: ifnull +176 -> 541
      //   368: aload 4
      //   370: instanceof 67
      //   373: ifeq +16 -> 389
      //   376: aload 4
      //   378: checkcast 67	javax/net/ssl/HttpsURLConnection
      //   381: invokevirtual 177	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   384: aload_3
      //   385: astore_1
      //   386: goto +155 -> 541
      //   389: aload_3
      //   390: astore_1
      //   391: aload 4
      //   393: instanceof 179
      //   396: ifeq +145 -> 541
      //   399: aload 4
      //   401: checkcast 179	java/net/HttpURLConnection
      //   404: invokevirtual 180	java/net/HttpURLConnection:disconnect	()V
      //   407: aload_3
      //   408: astore_1
      //   409: goto +132 -> 541
      //   412: astore_3
      //   413: aload 4
      //   415: astore_1
      //   416: aload 7
      //   418: invokevirtual 135	java/util/zip/GZIPOutputStream:close	()V
      //   421: goto +13 -> 434
      //   424: astore 5
      //   426: aload 4
      //   428: astore_1
      //   429: aload 5
      //   431: invokestatic 140	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   434: aload 4
      //   436: astore_1
      //   437: aload_3
      //   438: athrow
      //   439: astore_3
      //   440: goto +309 -> 749
      //   443: astore 5
      //   445: aload 4
      //   447: astore_3
      //   448: goto +13 -> 461
      //   451: astore_3
      //   452: aconst_null
      //   453: astore_1
      //   454: goto +295 -> 749
      //   457: astore 5
      //   459: aconst_null
      //   460: astore_3
      //   461: aload_3
      //   462: astore_1
      //   463: aload 5
      //   465: invokestatic 140	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   468: aload_3
      //   469: astore_1
      //   470: aload 5
      //   472: invokevirtual 183	java/io/IOException:getMessage	()Ljava/lang/String;
      //   475: ldc -71
      //   477: invokevirtual 65	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   480: ifne +233 -> 713
      //   483: aload_3
      //   484: astore_1
      //   485: aload 5
      //   487: invokevirtual 183	java/io/IOException:getMessage	()Ljava/lang/String;
      //   490: ldc -69
      //   492: invokevirtual 65	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   495: istore_2
      //   496: iload_2
      //   497: ifeq +6 -> 503
      //   500: goto +213 -> 713
      //   503: aload_3
      //   504: ifnull +289 -> 793
      //   507: aload_3
      //   508: instanceof 67
      //   511: ifeq +13 -> 524
      //   514: aload_3
      //   515: checkcast 67	javax/net/ssl/HttpsURLConnection
      //   518: invokevirtual 177	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   521: goto +272 -> 793
      //   524: aload_3
      //   525: instanceof 179
      //   528: ifeq +265 -> 793
      //   531: aload_3
      //   532: checkcast 179	java/net/HttpURLConnection
      //   535: invokevirtual 180	java/net/HttpURLConnection:disconnect	()V
      //   538: goto +255 -> 793
      //   541: aload_1
      //   542: ifnonnull +62 -> 604
      //   545: aload_0
      //   546: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   549: getfield 78	com/appodeal/ads/t:y	Z
      //   552: ifeq +246 -> 798
      //   555: aload 6
      //   557: aload_0
      //   558: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   561: getfield 82	com/appodeal/ads/t:d	Ljava/lang/String;
      //   564: invokeinterface 88 2 0
      //   569: ifeq +229 -> 798
      //   572: new 189	com/appodeal/ads/utils/b/a
      //   575: dup
      //   576: ldc -65
      //   578: invokespecial 194	com/appodeal/ads/utils/b/a:<init>	(Ljava/lang/String;)V
      //   581: invokestatic 140	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   584: aload 6
      //   586: aload_0
      //   587: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   590: getfield 82	com/appodeal/ads/t:d	Ljava/lang/String;
      //   593: ldc -60
      //   595: invokeinterface 200 3 0
      //   600: astore_3
      //   601: goto +46 -> 647
      //   604: aload_1
      //   605: astore_3
      //   606: aload_0
      //   607: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   610: getfield 78	com/appodeal/ads/t:y	Z
      //   613: ifeq +34 -> 647
      //   616: aload 6
      //   618: invokeinterface 204 1 0
      //   623: astore_3
      //   624: aload_3
      //   625: aload_0
      //   626: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   629: getfield 82	com/appodeal/ads/t:d	Ljava/lang/String;
      //   632: aload_1
      //   633: invokeinterface 210 3 0
      //   638: pop
      //   639: aload_3
      //   640: invokeinterface 213 1 0
      //   645: aload_1
      //   646: astore_3
      //   647: new 119	org/json/JSONObject
      //   650: dup
      //   651: aload_3
      //   652: invokespecial 214	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   655: astore_1
      //   656: aload_0
      //   657: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   660: getfield 78	com/appodeal/ads/t:y	Z
      //   663: ifeq +13 -> 676
      //   666: aload_3
      //   667: getstatic 220	com/appodeal/ads/utils/Log$LogLevel:verbose	Lcom/appodeal/ads/utils/Log$LogLevel;
      //   670: invokestatic 223	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;Lcom/appodeal/ads/utils/Log$LogLevel;)V
      //   673: goto +7 -> 680
      //   676: aload_3
      //   677: invokestatic 225	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   680: aload_1
      //   681: ldc -29
      //   683: invokevirtual 231	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   686: putstatic 237	com/appodeal/ads/UserSettings:userData	Lorg/json/JSONObject;
      //   689: aload_0
      //   690: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   693: aload_1
      //   694: invokestatic 240	com/appodeal/ads/t:a	(Lcom/appodeal/ads/t;Lorg/json/JSONObject;)V
      //   697: aload_1
      //   698: areturn
      //   699: astore_3
      //   700: aload_3
      //   701: invokestatic 140	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   704: aload_1
      //   705: areturn
      //   706: astore_1
      //   707: aload_1
      //   708: invokestatic 140	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   711: aconst_null
      //   712: areturn
      //   713: aload_3
      //   714: ifnull +86 -> 800
      //   717: aload_3
      //   718: instanceof 67
      //   721: ifeq +12 -> 733
      //   724: aload_3
      //   725: checkcast 67	javax/net/ssl/HttpsURLConnection
      //   728: invokevirtual 177	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   731: aconst_null
      //   732: areturn
      //   733: aload_3
      //   734: instanceof 179
      //   737: ifeq +63 -> 800
      //   740: aload_3
      //   741: checkcast 179	java/net/HttpURLConnection
      //   744: invokevirtual 180	java/net/HttpURLConnection:disconnect	()V
      //   747: aconst_null
      //   748: areturn
      //   749: aload_1
      //   750: ifnull +34 -> 784
      //   753: aload_1
      //   754: instanceof 67
      //   757: ifne +20 -> 777
      //   760: aload_1
      //   761: instanceof 179
      //   764: ifeq +20 -> 784
      //   767: aload_1
      //   768: checkcast 179	java/net/HttpURLConnection
      //   771: invokevirtual 180	java/net/HttpURLConnection:disconnect	()V
      //   774: goto +10 -> 784
      //   777: aload_1
      //   778: checkcast 67	javax/net/ssl/HttpsURLConnection
      //   781: invokevirtual 177	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   784: aload_3
      //   785: athrow
      //   786: astore_1
      //   787: aload_1
      //   788: invokestatic 140	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   791: aconst_null
      //   792: areturn
      //   793: aconst_null
      //   794: astore_1
      //   795: goto -254 -> 541
      //   798: aconst_null
      //   799: areturn
      //   800: aconst_null
      //   801: areturn
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	802	0	this	b
      //   0	802	1	paramVarArgs	Void[]
      //   354	143	2	bool	boolean
      //   22	224	3	localJSONObject	JSONObject
      //   268	5	3	localException1	Exception
      //   288	120	3	str	String
      //   412	26	3	localObject1	Object
      //   439	1	3	localObject2	Object
      //   447	1	3	localObject3	Object
      //   451	1	3	localObject4	Object
      //   460	217	3	localObject5	Object
      //   699	86	3	localException2	Exception
      //   58	388	4	localURLConnection	java.net.URLConnection
      //   36	244	5	localObject6	Object
      //   424	6	5	localException3	Exception
      //   443	1	5	localIOException1	java.io.IOException
      //   457	29	5	localIOException2	java.io.IOException
      //   51	566	6	localSharedPreferences	SharedPreferences
      //   241	176	7	localGZIPOutputStream	java.util.zip.GZIPOutputStream
      // Exception table:
      //   from	to	target	type
      //   260	265	268	java/lang/Exception
      //   243	257	412	finally
      //   416	421	424	java/lang/Exception
      //   63	76	439	finally
      //   79	94	439	finally
      //   97	107	439	finally
      //   110	127	439	finally
      //   130	138	439	finally
      //   141	149	439	finally
      //   155	163	439	finally
      //   166	174	439	finally
      //   177	185	439	finally
      //   188	196	439	finally
      //   199	208	439	finally
      //   211	217	439	finally
      //   220	229	439	finally
      //   232	243	439	finally
      //   260	265	439	finally
      //   272	276	439	finally
      //   279	289	439	finally
      //   292	301	439	finally
      //   304	313	439	finally
      //   320	327	439	finally
      //   330	339	439	finally
      //   342	355	439	finally
      //   416	421	439	finally
      //   429	434	439	finally
      //   437	439	439	finally
      //   463	468	439	finally
      //   470	483	439	finally
      //   485	496	439	finally
      //   63	76	443	java/io/IOException
      //   79	94	443	java/io/IOException
      //   97	107	443	java/io/IOException
      //   110	127	443	java/io/IOException
      //   130	138	443	java/io/IOException
      //   141	149	443	java/io/IOException
      //   155	163	443	java/io/IOException
      //   166	174	443	java/io/IOException
      //   177	185	443	java/io/IOException
      //   188	196	443	java/io/IOException
      //   199	208	443	java/io/IOException
      //   211	217	443	java/io/IOException
      //   220	229	443	java/io/IOException
      //   232	243	443	java/io/IOException
      //   260	265	443	java/io/IOException
      //   272	276	443	java/io/IOException
      //   279	289	443	java/io/IOException
      //   292	301	443	java/io/IOException
      //   304	313	443	java/io/IOException
      //   320	327	443	java/io/IOException
      //   330	339	443	java/io/IOException
      //   342	355	443	java/io/IOException
      //   416	421	443	java/io/IOException
      //   429	434	443	java/io/IOException
      //   437	439	443	java/io/IOException
      //   53	60	451	finally
      //   53	60	457	java/io/IOException
      //   680	697	699	java/lang/Exception
      //   647	673	706	org/json/JSONException
      //   676	680	706	org/json/JSONException
      //   0	23	786	java/lang/Exception
      //   29	53	786	java/lang/Exception
      //   368	384	786	java/lang/Exception
      //   391	407	786	java/lang/Exception
      //   507	521	786	java/lang/Exception
      //   524	538	786	java/lang/Exception
      //   545	601	786	java/lang/Exception
      //   606	645	786	java/lang/Exception
      //   647	673	786	java/lang/Exception
      //   676	680	786	java/lang/Exception
      //   700	704	786	java/lang/Exception
      //   707	711	786	java/lang/Exception
      //   717	731	786	java/lang/Exception
      //   733	747	786	java/lang/Exception
      //   753	774	786	java/lang/Exception
      //   777	784	786	java/lang/Exception
      //   784	786	786	java/lang/Exception
    }
    
    protected void a(JSONObject paramJSONObject)
    {
      try
      {
        if (t.this.a != null)
        {
          if (paramJSONObject == null)
          {
            t.this.a.a(t.this.c);
            return;
          }
          t.this.a.a(paramJSONObject, t.this.c, t.this.d);
          return;
        }
      }
      catch (Exception paramJSONObject)
      {
        Appodeal.a(paramJSONObject);
      }
    }
  }
  
  public static class c
  {
    private final Context a;
    private final int b;
    private final String c;
    private t.a d;
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
    
    public c a(t.a paramA)
    {
      this.d = paramA;
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
    
    public t a()
    {
      return new t(this, null);
    }
    
    public c b(String paramString)
    {
      this.f = paramString;
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
  }
}
