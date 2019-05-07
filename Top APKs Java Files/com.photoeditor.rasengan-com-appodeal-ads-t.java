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
import com.appodeal.ads.utils.Log.LogLevel;
import com.appodeal.ads.utils.b.a;
import com.appodeal.ads.utils.d;
import com.appodeal.ads.utils.e;
import com.appodeal.ads.utils.p;
import com.appodeal.ads.utils.s;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
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
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class t
{
  private static final byte[] $ = { 79, 117, -64, 127, -11, 12, -12, 5, 8, 7 };
  private static int $$ = 136;
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
  
  private static String $(int paramInt, short paramShort, byte paramByte)
  {
    short s1 = 97 - paramShort * 3;
    paramByte = 3 - paramByte * 3;
    paramShort = 0;
    int i3 = 0;
    paramInt = 7 - paramInt * 4;
    byte[] arrayOfByte1 = $;
    byte[] arrayOfByte2 = new byte[paramInt];
    int i2 = paramInt - 1;
    paramInt = paramShort;
    paramShort = s1;
    byte b1 = paramByte;
    if (arrayOfByte1 == null)
    {
      b1 = i2;
      paramShort = paramByte;
      paramInt = i3;
      paramByte = b1;
    }
    for (;;)
    {
      paramByte = s1 + -paramByte + 2;
      b1 = paramShort;
      paramShort = paramByte;
      int i1 = (byte)paramShort;
      b1 += 1;
      arrayOfByte2[paramInt] = i1;
      if (paramInt == i2) {
        return new String(arrayOfByte2, 0);
      }
      paramInt += 1;
      paramByte = arrayOfByte1[b1];
      s1 = paramShort;
      paramShort = b1;
    }
  }
  
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
    boolean bool;
    if ((this.d.equals("banner")) || (this.d.equals("debug"))) {
      bool = true;
    } else {
      bool = false;
    }
    this.q = bool;
    if ((this.d.equals("banner_320")) || (this.d.equals("debug_banner_320"))) {
      bool = true;
    } else {
      bool = false;
    }
    this.r = bool;
    if ((this.d.equals("banner_mrec")) || (this.d.equals("debug_mrec"))) {
      bool = true;
    } else {
      bool = false;
    }
    this.s = bool;
    if ((this.d.equals("video")) || (this.d.equals("debug_video"))) {
      bool = true;
    } else {
      bool = false;
    }
    this.t = bool;
    if ((this.d.equals("rewarded_video")) || (this.d.equals("debug_rewarded_video"))) {
      bool = true;
    } else {
      bool = false;
    }
    this.u = bool;
    if ((this.d.equals("native")) || (this.d.equals("debug_native"))) {
      bool = true;
    } else {
      bool = false;
    }
    this.v = bool;
    if ((this.d.equals("debug")) || (this.d.equals("debug_banner_320")) || (this.d.equals("debug_video")) || (this.d.equals("debug_rewarded_video")) || (this.d.equals("debug_mrec")) || (this.d.equals("debug_native"))) {
      bool = true;
    } else {
      bool = false;
    }
    this.w = bool;
    if ((this.q) || (this.r) || (this.s) || (this.t) || (this.u) || (this.v)) {
      bool = true;
    } else {
      bool = false;
    }
    this.x = bool;
    if ((!this.d.equals("stats")) && (!this.d.equals("show")) && (!this.d.equals("click")) && (!this.d.equals("finish")) && (!this.d.equals("install"))) {
      bool = true;
    } else {
      bool = false;
    }
    this.y = bool;
  }
  
  private void a(JSONObject paramJSONObject)
  {
    JSONArray localJSONArray = paramJSONObject.optJSONArray("segments");
    if ((localJSONArray == null) || (localJSONArray.length() == 0)) {
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
          if (((Iterator)localObject3).hasNext())
          {
            localObject4 = (o)((Iterator)localObject3).next();
            if (((o)localObject4).g() == null) {
              break label1760;
            }
            ((JSONArray)localObject1).put(((o)localObject4).a());
            break label1760;
          }
        }
        if (this.t)
        {
          localObject3 = ah.a(this.b).iterator();
          label626:
          if (((Iterator)localObject3).hasNext())
          {
            localObject4 = (ap)((Iterator)localObject3).next();
            if (((ap)localObject4).g() == null) {
              break label1763;
            }
            ((JSONArray)localObject1).put(((ap)localObject4).a());
            break label1763;
          }
        }
        if (this.u)
        {
          localObject3 = ak.a(this.b).iterator();
          label691:
          if (((Iterator)localObject3).hasNext())
          {
            localObject4 = (ap)((Iterator)localObject3).next();
            if (((ap)localObject4).g() == null) {
              break label1766;
            }
            ((JSONArray)localObject1).put(((ap)localObject4).a());
            break label1766;
          }
        }
        if (this.r)
        {
          localObject3 = g.a(this.b).iterator();
          label756:
          if (((Iterator)localObject3).hasNext())
          {
            localObject4 = (h)((Iterator)localObject3).next();
            if (((h)localObject4).f() == null) {
              break label1769;
            }
            ((JSONArray)localObject1).put(((h)localObject4).a());
            break label1769;
          }
        }
        if (this.s)
        {
          localObject3 = v.a(this.b).iterator();
          label821:
          if (((Iterator)localObject3).hasNext())
          {
            localObject4 = (w)((Iterator)localObject3).next();
            if (((w)localObject4).f() == null) {
              break label1772;
            }
            ((JSONArray)localObject1).put(((w)localObject4).a());
            break label1772;
          }
        }
        if (this.v)
        {
          localObject3 = Native.a(this.b).iterator();
          label886:
          if (((Iterator)localObject3).hasNext())
          {
            localObject4 = (ac)((Iterator)localObject3).next();
            if (localObject4 == null) {
              break label1775;
            }
            ((JSONArray)localObject1).put(((ac)localObject4).a());
            break label1775;
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
    if ((!AppodealSettings.n) && (AppodealSettings.m) && (this.x) && (((Calendar)localObject2).getTimeInMillis() < System.currentTimeMillis())) {
      AppodealSettings.n = true;
    }
    label1760:
    label1763:
    label1766:
    label1769:
    label1772:
    label1775:
    label1778:
    for (;;)
    {
      try
      {
        localObject2 = new JSONArray();
        localObject4 = this.b.getPackageManager().getInstalledApplications(0);
        localObject3 = Pattern.compile("^?(?:com\\.android|com\\.google|com\\.sec|com\\.samsung|com\\.sonyericsson|com\\.sonymobile|com\\.motorola|com\\.htc).*$");
        if (localObject4 != null)
        {
          localObject4 = ((List)localObject4).iterator();
          if (((Iterator)localObject4).hasNext())
          {
            String str = ((ApplicationInfo)((Iterator)localObject4).next()).packageName;
            if ((((Pattern)localObject3).matcher(str).matches()) || (str.equals($(0, (short)0, (byte)0).intern()))) {
              break label1778;
            }
            ((JSONArray)localObject2).put(str);
            break label1778;
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
      if (this.h != null)
      {
        if (this.d.equals("stats"))
        {
          localJSONObject.put("id", this.h.a().toString());
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
            localJSONObject.put("bidder_id", this.h.e());
            return localJSONObject;
          }
        }
        else if (((this.d.equals("click")) || (this.d.equals("finish"))) && (this.h.f())) {
          localJSONObject.put("id", this.h.e());
        }
      }
      return localJSONObject;
      break;
      break label626;
      break label691;
      break label756;
      break label821;
      break label886;
    }
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
        Object localObject2;
        if (o == null)
        {
          o = new JSONObject();
          PackageManager localPackageManager = this.b.getPackageManager();
          String str3 = paramSharedPreferences.getString("appKey", null);
          if (str3 == null) {
            return null;
          }
          String str2 = paramSharedPreferences.getString("advertisingId", null);
          Object localObject3 = paramSharedPreferences.getString("advertisingTracking", null);
          localObject1 = str2;
          localObject2 = localObject3;
          if (str2 == null)
          {
            boolean bool = this.d.equals("install");
            localObject1 = str2;
            localObject2 = localObject3;
            if (!bool)
            {
              localObject1 = str2;
              localObject2 = localObject3;
              try
              {
                Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
                localObject1 = str2;
                localObject2 = localObject3;
                AdvertisingIdClient.class.getDeclaredMethod("getAdvertisingIdInfo", new Class[] { Context.class });
                localObject1 = str2;
                localObject2 = localObject3;
                AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.b);
                localObject1 = str2;
                localObject2 = localObject3;
                str2 = localInfo.getId();
                localObject1 = str2;
                localObject2 = localObject3;
                if (!localInfo.isLimitAdTrackingEnabled()) {
                  break label773;
                }
                localObject3 = "0";
                localObject1 = str2;
                localObject2 = localObject3;
                paramSharedPreferences = paramSharedPreferences.edit();
                localObject1 = str2;
                localObject2 = localObject3;
                paramSharedPreferences.putString("advertisingId", str2);
                localObject1 = str2;
                localObject2 = localObject3;
                paramSharedPreferences.putString("advertisingTracking", (String)localObject3);
                localObject1 = str2;
                localObject2 = localObject3;
                paramSharedPreferences.apply();
                localObject1 = str2;
                localObject2 = localObject3;
                Appodeal.a(String.format("Advertising ID: %s", new Object[] { str2 }));
                localObject1 = str2;
                localObject2 = localObject3;
              }
              catch (Exception paramSharedPreferences)
              {
                Appodeal.a(paramSharedPreferences);
              }
            }
          }
          paramSharedPreferences = (SharedPreferences)localObject1;
          if (localObject1 == null) {
            paramSharedPreferences = an.l(this.b);
          }
          o.put("app_key", str3);
          o.put($(0, (short)0, (byte)0).intern(), Build.VERSION.RELEASE);
          o.put("android_level", Build.VERSION.SDK_INT);
          o.put("sdk", "1.15.8");
          localObject1 = this.b.getPackageName();
          o.put("package", localObject1);
          try
          {
            localObject3 = localPackageManager.getPackageInfo((String)localObject1, 0);
            o.put("package_version", ((PackageInfo)localObject3).versionName);
            o.put("package_code", ((PackageInfo)localObject3).versionCode);
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            Appodeal.a(localNameNotFoundException);
          }
          try
          {
            ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo((String)localObject1, 128);
            o.put("framework", localApplicationInfo.metaData.getString("com.appodeal.framework"));
          }
          catch (Exception localException)
          {
            Appodeal.a(localException);
          }
          o.put("android_id", paramSharedPreferences);
          o.put("advertising_tracking", localObject2);
          paramSharedPreferences = an.f(this.b);
          o.put("width", paramSharedPreferences.first);
          o.put("height", paramSharedPreferences.second);
          if (an.n(this.b)) {
            o.put("device_type", "tablet");
          } else {
            o.put("device_type", "phone");
          }
          if (!Build.MANUFACTURER.equals("Amazon")) {
            break label781;
          }
          paramSharedPreferences = "amazon";
          o.put("platform", paramSharedPreferences);
          try
          {
            localObject1 = localPackageManager.getInstallerPackageName((String)localObject1);
            paramSharedPreferences = (SharedPreferences)localObject1;
            if (localObject1 == null) {
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
        Object localObject1 = o.keys();
        if (((Iterator)localObject1).hasNext())
        {
          localObject2 = (String)((Iterator)localObject1).next();
          paramSharedPreferences.put((String)localObject2, o.get((String)localObject2));
          continue;
        }
        return paramSharedPreferences;
      }
      finally {}
      label773:
      String str1 = "1";
      continue;
      label781:
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
      }
      return localJSONObject;
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
    
    protected JSONObject a(Void... paramVarArgs)
    {
      Object localObject1 = null;
      paramVarArgs = null;
      try
      {
        Object localObject2 = t.this.b.getSharedPreferences("appodeal", 0);
        JSONObject localJSONObject = t.this.a((SharedPreferences)localObject2);
        if (localJSONObject == null) {
          return null;
        }
        Object localObject5 = t.this.b();
        SharedPreferences localSharedPreferences = t.this.b.getSharedPreferences("Appodeal", 0);
        try
        {
          localObject2 = ((URL)localObject5).openConnection();
          paramVarArgs = (Void[])localObject2;
          localObject1 = localObject2;
          if (((URL)localObject5).getProtocol().equals("https"))
          {
            paramVarArgs = (Void[])localObject2;
            localObject1 = localObject2;
            ((HttpsURLConnection)localObject2).setSSLSocketFactory(t.a(t.this));
          }
          paramVarArgs = (Void[])localObject2;
          localObject1 = localObject2;
          if (t.this.y)
          {
            paramVarArgs = (Void[])localObject2;
            localObject1 = localObject2;
            if (localSharedPreferences.contains(t.this.d))
            {
              paramVarArgs = (Void[])localObject2;
              localObject1 = localObject2;
              ((URLConnection)localObject2).setConnectTimeout(10000);
              paramVarArgs = (Void[])localObject2;
              localObject1 = localObject2;
              ((URLConnection)localObject2).setReadTimeout(10000);
              break label206;
            }
          }
          paramVarArgs = (Void[])localObject2;
          localObject1 = localObject2;
          ((URLConnection)localObject2).setConnectTimeout(20000);
          paramVarArgs = (Void[])localObject2;
          localObject1 = localObject2;
          ((URLConnection)localObject2).setReadTimeout(20000);
          label206:
          paramVarArgs = (Void[])localObject2;
          localObject1 = localObject2;
          ((URLConnection)localObject2).setConnectTimeout(20000);
          paramVarArgs = (Void[])localObject2;
          localObject1 = localObject2;
          ((URLConnection)localObject2).setReadTimeout(20000);
          paramVarArgs = (Void[])localObject2;
          localObject1 = localObject2;
          ((URLConnection)localObject2).setRequestProperty("Content-Type", "text/plain; charset=UTF-8");
          paramVarArgs = (Void[])localObject2;
          localObject1 = localObject2;
          ((URLConnection)localObject2).setDoOutput(true);
          paramVarArgs = (Void[])localObject2;
          localObject1 = localObject2;
          localObject5 = new ByteArrayOutputStream();
          paramVarArgs = (Void[])localObject2;
          localObject1 = localObject2;
          GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream((OutputStream)localObject5);
          String str;
          try
          {
            localGZIPOutputStream.write(localJSONObject.toString().getBytes("UTF-8"));
            paramVarArgs = (Void[])localObject2;
            localObject1 = localObject2;
            try
            {
              localGZIPOutputStream.close();
            }
            catch (Exception localException1)
            {
              paramVarArgs = (Void[])localObject2;
              localObject1 = localObject2;
              Appodeal.a(localException1);
            }
            paramVarArgs = (Void[])localObject2;
          }
          finally
          {
            paramVarArgs = (Void[])localObject2;
            localObject1 = localObject2;
            try
            {
              localGZIPOutputStream.close();
            }
            catch (Exception localException2)
            {
              paramVarArgs = (Void[])localObject2;
              localObject1 = localObject2;
              Appodeal.a(localException2);
            }
            paramVarArgs = (Void[])localObject2;
            localObject1 = localObject2;
          }
          localObject1 = localObject2;
          if (!str.isEmpty())
          {
            paramVarArgs = (Void[])localObject2;
            localObject1 = localObject2;
            if (!str.equals(" "))
            {
              paramVarArgs = (Void[])localObject2;
              localObject1 = localObject2;
              bool = t.this.d.equals("stats");
              paramVarArgs = str;
              if (!bool) {
                break label488;
              }
            }
          }
          paramVarArgs = null;
          label488:
          localObject1 = paramVarArgs;
          if (localObject2 != null) {
            if ((localObject2 instanceof HttpsURLConnection))
            {
              ((HttpsURLConnection)localObject2).disconnect();
              localObject1 = paramVarArgs;
            }
            else
            {
              localObject1 = paramVarArgs;
              if ((localObject2 instanceof HttpURLConnection))
              {
                ((HttpURLConnection)localObject2).disconnect();
                localObject1 = paramVarArgs;
              }
            }
          }
        }
        catch (IOException localIOException)
        {
          boolean bool;
          localObject1 = paramVarArgs;
          Appodeal.a(localIOException);
          localObject1 = paramVarArgs;
          if (!localIOException.getMessage().equals("No valid pins found in chain!"))
          {
            localObject1 = paramVarArgs;
            bool = localIOException.getMessage().equals("java.security.cert.CertPathValidatorException: Trust anchor for certification path not found.");
            if (!bool) {}
          }
          else
          {
            if (paramVarArgs != null)
            {
              if ((paramVarArgs instanceof HttpsURLConnection))
              {
                ((HttpsURLConnection)paramVarArgs).disconnect();
                return null;
              }
              if ((paramVarArgs instanceof HttpURLConnection)) {
                ((HttpURLConnection)paramVarArgs).disconnect();
              }
            }
            return null;
          }
          Object localObject3 = null;
          localObject1 = localObject3;
          if (paramVarArgs != null) {
            if ((paramVarArgs instanceof HttpsURLConnection))
            {
              ((HttpsURLConnection)paramVarArgs).disconnect();
              localObject1 = localObject3;
            }
            else
            {
              localObject1 = localObject3;
              if ((paramVarArgs instanceof HttpURLConnection))
              {
                ((HttpURLConnection)paramVarArgs).disconnect();
                localObject1 = localObject3;
              }
            }
          }
        }
        finally
        {
          if (localObject1 != null) {
            if ((localObject1 instanceof HttpsURLConnection)) {
              ((HttpsURLConnection)localObject1).disconnect();
            } else if ((localObject1 instanceof HttpURLConnection)) {
              ((HttpURLConnection)localObject1).disconnect();
            }
          }
        }
        if (localObject1 == null)
        {
          if ((t.this.y) && (localSharedPreferences.contains(t.this.d)))
          {
            Appodeal.a(new a("/get error, using saved waterfall"));
            paramVarArgs = localSharedPreferences.getString(t.this.d, "");
          }
          else
          {
            return null;
          }
        }
        else
        {
          paramVarArgs = (Void[])localObject1;
          if (t.this.y)
          {
            paramVarArgs = localSharedPreferences.edit();
            paramVarArgs.putString(t.this.d, (String)localObject1);
            paramVarArgs.apply();
            paramVarArgs = (Void[])localObject1;
          }
        }
        try
        {
          localObject1 = new JSONObject(paramVarArgs);
          if (t.this.y) {
            Appodeal.a(paramVarArgs, Log.LogLevel.verbose);
          } else {
            Appodeal.a(paramVarArgs);
          }
        }
        catch (JSONException paramVarArgs)
        {
          Appodeal.a(paramVarArgs);
          return null;
        }
        try
        {
          UserSettings.userData = ((JSONObject)localObject1).optJSONObject("user_data");
          t.a(t.this, (JSONObject)localObject1);
          return localObject1;
        }
        catch (Exception paramVarArgs)
        {
          Appodeal.a(paramVarArgs);
          return localObject1;
        }
        return null;
      }
      catch (Exception paramVarArgs)
      {
        Appodeal.a(paramVarArgs);
      }
    }
    
    protected void a(JSONObject paramJSONObject)
    {
      try
      {
        if (t.this.a != null) {
          if (paramJSONObject == null) {
            t.this.a.a(t.this.c);
          } else {
            t.this.a.a(paramJSONObject, t.this.c, t.this.d);
          }
        }
        return;
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
