package com.appodeal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Pair;
import com.appodeal.ads.f.c;
import com.appodeal.ads.f.f;
import com.appodeal.ads.utils.d;
import com.appodeal.ads.utils.e;
import com.appodeal.ads.utils.p;
import com.appodeal.ads.utils.s;
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
    if (this.d == null) {}
    while ((AppodealSettings.a) && ((this.a == null) || ((this.a instanceof com.appodeal.ads.utils.v))) && ((this.d.equals("stats")) || (this.d.equals("show")) || (this.d.equals("click")) || (this.d.equals("finish")) || (this.d.equals("install")))) {
      return;
    }
    if ((this.d.equals("banner")) || (this.d.equals("debug")))
    {
      bool1 = true;
      this.q = bool1;
      if ((!this.d.equals("banner_320")) && (!this.d.equals("debug_banner_320"))) {
        break label596;
      }
      bool1 = true;
      label266:
      this.r = bool1;
      if ((!this.d.equals("banner_mrec")) && (!this.d.equals("debug_mrec"))) {
        break label601;
      }
      bool1 = true;
      label297:
      this.s = bool1;
      if ((!this.d.equals("video")) && (!this.d.equals("debug_video"))) {
        break label606;
      }
      bool1 = true;
      label328:
      this.t = bool1;
      if ((!this.d.equals("rewarded_video")) && (!this.d.equals("debug_rewarded_video"))) {
        break label611;
      }
      bool1 = true;
      label359:
      this.u = bool1;
      if ((!this.d.equals("native")) && (!this.d.equals("debug_native"))) {
        break label616;
      }
      bool1 = true;
      label390:
      this.v = bool1;
      if ((!this.d.equals("debug")) && (!this.d.equals("debug_banner_320")) && (!this.d.equals("debug_video")) && (!this.d.equals("debug_rewarded_video")) && (!this.d.equals("debug_mrec")) && (!this.d.equals("debug_native"))) {
        break label621;
      }
      bool1 = true;
      label469:
      this.w = bool1;
      if ((!this.q) && (!this.r) && (!this.s) && (!this.t) && (!this.u) && (!this.v)) {
        break label626;
      }
      bool1 = true;
      label518:
      this.x = bool1;
      if ((this.d.equals("stats")) || (this.d.equals("show")) || (this.d.equals("click")) || (this.d.equals("finish")) || (this.d.equals("install"))) {
        break label631;
      }
    }
    label596:
    label601:
    label606:
    label611:
    label616:
    label621:
    label626:
    label631:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      this.y = bool1;
      return;
      bool1 = false;
      break;
      bool1 = false;
      break label266;
      bool1 = false;
      break label297;
      bool1 = false;
      break label328;
      bool1 = false;
      break label359;
      bool1 = false;
      break label390;
      bool1 = false;
      break label469;
      bool1 = false;
      break label518;
    }
  }
  
  private void a(JSONObject paramJSONObject)
  {
    JSONArray localJSONArray = paramJSONObject.optJSONArray("segments");
    if ((localJSONArray == null) || (localJSONArray.length() == 0)) {}
    for (;;)
    {
      return;
      paramJSONObject = new com.appodeal.ads.f.g(this.b, paramJSONObject);
      if (!paramJSONObject.b(localJSONArray)) {
        continue;
      }
      paramJSONObject = paramJSONObject.a(localJSONArray);
      if (paramJSONObject != null) {}
      try
      {
        paramJSONObject.a();
        com.appodeal.ads.f.g.a(paramJSONObject);
        if (Appodeal.d == null) {
          continue;
        }
        Appodeal.d.a();
        return;
      }
      catch (JSONException localJSONException)
      {
        for (;;)
        {
          Appodeal.a(localJSONException);
        }
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
            if (((o)localObject4).g() != null)
            {
              ((JSONArray)localObject1).put(((o)localObject4).a());
              continue;
              localObject2 = Calendar.getInstance();
            }
          }
        }
      }
    }
    catch (JSONException localJSONException)
    {
      Appodeal.a(localJSONException);
    }
    for (;;)
    {
      Object localObject2;
      ((Calendar)localObject2).setTimeInMillis(paramSharedPreferences.getLong("lastSettingsTime", 0L));
      ((Calendar)localObject2).add(5, 1);
      if ((!UserSettings.sendingInProgress) && (this.x) && ((((Calendar)localObject2).getTimeInMillis() < System.currentTimeMillis()) || (paramSharedPreferences.getBoolean("should_update_user_settings", true)))) {
        UserSettings.sendingInProgress = true;
      }
      try
      {
        localJSONObject.put("sa", s.a(this.b));
      }
      catch (Exception localException2)
      {
        try
        {
          for (;;)
          {
            localJSONObject.put("user_settings", Appodeal.getUserSettings(this.b).a());
            localObject2 = paramSharedPreferences.edit();
            ((SharedPreferences.Editor)localObject2).putLong("lastSettingsTime", System.currentTimeMillis());
            ((SharedPreferences.Editor)localObject2).putBoolean("should_update_user_settings", false);
            ((SharedPreferences.Editor)localObject2).apply();
            UserSettings.sendingInProgress = false;
            localObject2 = Calendar.getInstance();
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
                if (localObject4 == null) {
                  break label1612;
                }
                localObject4 = ((List)localObject4).iterator();
                while (((Iterator)localObject4).hasNext())
                {
                  String str = ((ApplicationInfo)((Iterator)localObject4).next()).packageName;
                  if ((!((Pattern)localObject3).matcher(str).matches()) && (!str.equals("android"))) {
                    ((JSONArray)localObject2).put(str);
                  }
                }
                paramSharedPreferences = paramSharedPreferences.edit();
              }
              catch (Exception localException1)
              {
                Appodeal.a(localException1);
              }
              paramSharedPreferences.putLong("lastAppTime", System.currentTimeMillis());
              paramSharedPreferences.apply();
              AppodealSettings.n = false;
            }
            if (this.h != null)
            {
              if (!this.d.equals("stats")) {
                break label1626;
              }
              localJSONObject.put("id", this.h.a().toString());
            }
            return localJSONObject;
            if (this.t)
            {
              localObject3 = ah.a(this.b).iterator();
              while (((Iterator)localObject3).hasNext())
              {
                localObject4 = (ap)((Iterator)localObject3).next();
                if (((ap)localObject4).g() != null) {
                  localException1.put(((ap)localObject4).a());
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
                  localException1.put(((ap)localObject4).a());
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
                  localException1.put(((h)localObject4).a());
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
                  localException1.put(((w)localObject4).a());
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
                  localException1.put(((ac)localObject4).a());
                }
              }
            }
            localJSONObject.put("show_array", localException1);
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
            if (this.i == null) {
              break;
            }
            localJSONObject.put("placement_id", this.i.a());
            break;
            localException2 = localException2;
            Appodeal.a(localException2);
          }
        }
        catch (Exception localException3)
        {
          for (;;)
          {
            Appodeal.a(localException3);
            continue;
            label1612:
            localJSONObject.put("apps", localException3);
            continue;
            label1626:
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
              }
            }
            else if (((this.d.equals("click")) || (this.d.equals("finish"))) && (this.h.f())) {
              localJSONObject.put("id", this.h.e());
            }
          }
        }
      }
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
  
  /* Error */
  @Nullable
  @VisibleForTesting
  JSONObject b(SharedPreferences paramSharedPreferences)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 749	com/appodeal/ads/t:o	Lorg/json/JSONObject;
    //   6: ifnonnull +634 -> 640
    //   9: new 202	org/json/JSONObject
    //   12: dup
    //   13: invokespecial 750	org/json/JSONObject:<init>	()V
    //   16: putstatic 749	com/appodeal/ads/t:o	Lorg/json/JSONObject;
    //   19: aload_0
    //   20: getfield 65	com/appodeal/ads/t:b	Landroid/content/Context;
    //   23: invokevirtual 559	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   26: astore 7
    //   28: aload_1
    //   29: ldc_w 752
    //   32: aconst_null
    //   33: invokeinterface 449 3 0
    //   38: astore 8
    //   40: aload 8
    //   42: ifnonnull +8 -> 50
    //   45: ldc 2
    //   47: monitorexit
    //   48: aconst_null
    //   49: areturn
    //   50: aload_1
    //   51: ldc_w 754
    //   54: aconst_null
    //   55: invokeinterface 449 3 0
    //   60: astore 6
    //   62: aload_1
    //   63: ldc_w 756
    //   66: aconst_null
    //   67: invokeinterface 449 3 0
    //   72: astore_3
    //   73: aload_3
    //   74: astore 5
    //   76: aload 6
    //   78: astore 4
    //   80: aload 6
    //   82: ifnonnull +684 -> 766
    //   85: aload_0
    //   86: getfield 75	com/appodeal/ads/t:d	Ljava/lang/String;
    //   89: ldc -114
    //   91: invokevirtual 134	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   94: istore_2
    //   95: aload_3
    //   96: astore 5
    //   98: aload 6
    //   100: astore 4
    //   102: iload_2
    //   103: ifne +663 -> 766
    //   106: aload_3
    //   107: astore 5
    //   109: aload 6
    //   111: astore 4
    //   113: ldc_w 758
    //   116: invokestatic 764	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   119: pop
    //   120: aload_3
    //   121: astore 5
    //   123: aload 6
    //   125: astore 4
    //   127: ldc_w 766
    //   130: ldc_w 768
    //   133: iconst_1
    //   134: anewarray 760	java/lang/Class
    //   137: dup
    //   138: iconst_0
    //   139: ldc_w 555
    //   142: aastore
    //   143: invokevirtual 772	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   146: pop
    //   147: aload_3
    //   148: astore 5
    //   150: aload 6
    //   152: astore 4
    //   154: aload_0
    //   155: getfield 65	com/appodeal/ads/t:b	Landroid/content/Context;
    //   158: invokestatic 775	com/google/android/gms/ads/identifier/AdvertisingIdClient:getAdvertisingIdInfo	(Landroid/content/Context;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
    //   161: astore 9
    //   163: aload_3
    //   164: astore 5
    //   166: aload 6
    //   168: astore 4
    //   170: aload 9
    //   172: invokevirtual 780	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:getId	()Ljava/lang/String;
    //   175: astore 6
    //   177: aload_3
    //   178: astore 5
    //   180: aload 6
    //   182: astore 4
    //   184: aload 9
    //   186: invokevirtual 783	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:isLimitAdTrackingEnabled	()Z
    //   189: ifeq +510 -> 699
    //   192: ldc_w 785
    //   195: astore_3
    //   196: aload_3
    //   197: astore 5
    //   199: aload 6
    //   201: astore 4
    //   203: aload_1
    //   204: invokeinterface 534 1 0
    //   209: astore_1
    //   210: aload_3
    //   211: astore 5
    //   213: aload 6
    //   215: astore 4
    //   217: aload_1
    //   218: ldc_w 754
    //   221: aload 6
    //   223: invokeinterface 789 3 0
    //   228: pop
    //   229: aload_3
    //   230: astore 5
    //   232: aload 6
    //   234: astore 4
    //   236: aload_1
    //   237: ldc_w 756
    //   240: aload_3
    //   241: invokeinterface 789 3 0
    //   246: pop
    //   247: aload_3
    //   248: astore 5
    //   250: aload 6
    //   252: astore 4
    //   254: aload_1
    //   255: invokeinterface 547 1 0
    //   260: aload_3
    //   261: astore 5
    //   263: aload 6
    //   265: astore 4
    //   267: ldc_w 791
    //   270: iconst_1
    //   271: anewarray 4	java/lang/Object
    //   274: dup
    //   275: iconst_0
    //   276: aload 6
    //   278: aastore
    //   279: invokestatic 744	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   282: invokestatic 678	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
    //   285: aload 6
    //   287: astore_1
    //   288: aload_1
    //   289: ifnonnull +474 -> 763
    //   292: aload_0
    //   293: getfield 65	com/appodeal/ads/t:b	Landroid/content/Context;
    //   296: invokestatic 793	com/appodeal/ads/an:l	(Landroid/content/Context;)Ljava/lang/String;
    //   299: astore_1
    //   300: getstatic 749	com/appodeal/ads/t:o	Lorg/json/JSONObject;
    //   303: ldc_w 795
    //   306: aload 8
    //   308: invokevirtual 291	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   311: pop
    //   312: getstatic 749	com/appodeal/ads/t:o	Lorg/json/JSONObject;
    //   315: ldc_w 590
    //   318: getstatic 798	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   321: invokevirtual 291	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   324: pop
    //   325: getstatic 749	com/appodeal/ads/t:o	Lorg/json/JSONObject;
    //   328: ldc_w 800
    //   331: getstatic 700	android/os/Build$VERSION:SDK_INT	I
    //   334: invokevirtual 654	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   337: pop
    //   338: getstatic 749	com/appodeal/ads/t:o	Lorg/json/JSONObject;
    //   341: ldc_w 802
    //   344: ldc_w 804
    //   347: invokevirtual 291	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   350: pop
    //   351: aload_0
    //   352: getfield 65	com/appodeal/ads/t:b	Landroid/content/Context;
    //   355: invokevirtual 807	android/content/Context:getPackageName	()Ljava/lang/String;
    //   358: astore 4
    //   360: getstatic 749	com/appodeal/ads/t:o	Lorg/json/JSONObject;
    //   363: ldc_w 809
    //   366: aload 4
    //   368: invokevirtual 291	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   371: pop
    //   372: aload 7
    //   374: aload 4
    //   376: iconst_0
    //   377: invokevirtual 813	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   380: astore 5
    //   382: getstatic 749	com/appodeal/ads/t:o	Lorg/json/JSONObject;
    //   385: ldc_w 815
    //   388: aload 5
    //   390: getfield 820	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   393: invokevirtual 291	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   396: pop
    //   397: getstatic 749	com/appodeal/ads/t:o	Lorg/json/JSONObject;
    //   400: ldc_w 822
    //   403: aload 5
    //   405: getfield 825	android/content/pm/PackageInfo:versionCode	I
    //   408: invokevirtual 654	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   411: pop
    //   412: aload 7
    //   414: aload 4
    //   416: sipush 128
    //   419: invokevirtual 829	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   422: astore 5
    //   424: getstatic 749	com/appodeal/ads/t:o	Lorg/json/JSONObject;
    //   427: ldc_w 831
    //   430: aload 5
    //   432: getfield 835	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   435: ldc_w 837
    //   438: invokevirtual 841	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   441: invokevirtual 291	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   444: pop
    //   445: getstatic 749	com/appodeal/ads/t:o	Lorg/json/JSONObject;
    //   448: ldc_w 843
    //   451: aload_1
    //   452: invokevirtual 291	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   455: pop
    //   456: getstatic 749	com/appodeal/ads/t:o	Lorg/json/JSONObject;
    //   459: ldc_w 845
    //   462: aload_3
    //   463: invokevirtual 291	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   466: pop
    //   467: aload_0
    //   468: getfield 65	com/appodeal/ads/t:b	Landroid/content/Context;
    //   471: invokestatic 847	com/appodeal/ads/an:f	(Landroid/content/Context;)Landroid/util/Pair;
    //   474: astore_1
    //   475: getstatic 749	com/appodeal/ads/t:o	Lorg/json/JSONObject;
    //   478: ldc_w 849
    //   481: aload_1
    //   482: getfield 321	android/util/Pair:first	Ljava/lang/Object;
    //   485: invokevirtual 291	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   488: pop
    //   489: getstatic 749	com/appodeal/ads/t:o	Lorg/json/JSONObject;
    //   492: ldc_w 851
    //   495: aload_1
    //   496: getfield 326	android/util/Pair:second	Ljava/lang/Object;
    //   499: invokevirtual 291	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   502: pop
    //   503: aload_0
    //   504: getfield 65	com/appodeal/ads/t:b	Landroid/content/Context;
    //   507: invokestatic 854	com/appodeal/ads/an:n	(Landroid/content/Context;)Z
    //   510: ifeq +224 -> 734
    //   513: getstatic 749	com/appodeal/ads/t:o	Lorg/json/JSONObject;
    //   516: ldc_w 856
    //   519: ldc_w 858
    //   522: invokevirtual 291	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   525: pop
    //   526: getstatic 863	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   529: ldc_w 865
    //   532: invokevirtual 134	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   535: ifeq +240 -> 775
    //   538: ldc_w 867
    //   541: astore_1
    //   542: getstatic 749	com/appodeal/ads/t:o	Lorg/json/JSONObject;
    //   545: ldc_w 869
    //   548: aload_1
    //   549: invokevirtual 291	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   552: pop
    //   553: aload 7
    //   555: aload 4
    //   557: invokevirtual 872	android/content/pm/PackageManager:getInstallerPackageName	(Ljava/lang/String;)Ljava/lang/String;
    //   560: astore_3
    //   561: aload_3
    //   562: astore_1
    //   563: aload_3
    //   564: ifnonnull +7 -> 571
    //   567: ldc_w 874
    //   570: astore_1
    //   571: getstatic 749	com/appodeal/ads/t:o	Lorg/json/JSONObject;
    //   574: ldc_w 876
    //   577: aload_1
    //   578: invokevirtual 291	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   581: pop
    //   582: getstatic 749	com/appodeal/ads/t:o	Lorg/json/JSONObject;
    //   585: ldc_w 878
    //   588: getstatic 863	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   591: invokevirtual 291	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   594: pop
    //   595: getstatic 749	com/appodeal/ads/t:o	Lorg/json/JSONObject;
    //   598: ldc_w 880
    //   601: ldc_w 882
    //   604: iconst_2
    //   605: anewarray 4	java/lang/Object
    //   608: dup
    //   609: iconst_0
    //   610: getstatic 863	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   613: aastore
    //   614: dup
    //   615: iconst_1
    //   616: getstatic 885	android/os/Build:MODEL	Ljava/lang/String;
    //   619: aastore
    //   620: invokestatic 744	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   623: invokevirtual 291	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   626: pop
    //   627: getstatic 749	com/appodeal/ads/t:o	Lorg/json/JSONObject;
    //   630: ldc_w 887
    //   633: invokestatic 889	com/appodeal/ads/an:a	()Z
    //   636: invokevirtual 308	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   639: pop
    //   640: new 202	org/json/JSONObject
    //   643: dup
    //   644: invokespecial 750	org/json/JSONObject:<init>	()V
    //   647: astore_1
    //   648: getstatic 749	com/appodeal/ads/t:o	Lorg/json/JSONObject;
    //   651: invokevirtual 892	org/json/JSONObject:keys	()Ljava/util/Iterator;
    //   654: astore_3
    //   655: aload_3
    //   656: invokeinterface 470 1 0
    //   661: ifeq +97 -> 758
    //   664: aload_3
    //   665: invokeinterface 474 1 0
    //   670: checkcast 130	java/lang/String
    //   673: astore 4
    //   675: aload_1
    //   676: aload 4
    //   678: getstatic 749	com/appodeal/ads/t:o	Lorg/json/JSONObject;
    //   681: aload 4
    //   683: invokevirtual 895	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   686: invokevirtual 291	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   689: pop
    //   690: goto -35 -> 655
    //   693: astore_1
    //   694: ldc 2
    //   696: monitorexit
    //   697: aload_1
    //   698: athrow
    //   699: ldc_w 897
    //   702: astore_3
    //   703: goto -507 -> 196
    //   706: astore_1
    //   707: aload_1
    //   708: invokestatic 241	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   711: goto +55 -> 766
    //   714: astore 5
    //   716: aload 5
    //   718: invokestatic 241	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   721: goto -309 -> 412
    //   724: astore 5
    //   726: aload 5
    //   728: invokestatic 241	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   731: goto -286 -> 445
    //   734: getstatic 749	com/appodeal/ads/t:o	Lorg/json/JSONObject;
    //   737: ldc_w 856
    //   740: ldc_w 899
    //   743: invokevirtual 291	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   746: pop
    //   747: goto -221 -> 526
    //   750: astore_1
    //   751: aload_1
    //   752: invokestatic 241	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   755: goto -173 -> 582
    //   758: ldc 2
    //   760: monitorexit
    //   761: aload_1
    //   762: areturn
    //   763: goto -463 -> 300
    //   766: aload 4
    //   768: astore_1
    //   769: aload 5
    //   771: astore_3
    //   772: goto -484 -> 288
    //   775: ldc_w 901
    //   778: astore_1
    //   779: goto -237 -> 542
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	782	0	this	t
    //   0	782	1	paramSharedPreferences	SharedPreferences
    //   94	9	2	bool	boolean
    //   72	700	3	localObject1	Object
    //   78	689	4	str1	String
    //   74	357	5	localObject2	Object
    //   714	3	5	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   724	46	5	localException	Exception
    //   60	226	6	str2	String
    //   26	528	7	localPackageManager	PackageManager
    //   38	269	8	str3	String
    //   161	24	9	localInfo	com.google.android.gms.ads.identifier.AdvertisingIdClient.Info
    // Exception table:
    //   from	to	target	type
    //   3	40	693	finally
    //   45	48	693	finally
    //   50	73	693	finally
    //   85	95	693	finally
    //   113	120	693	finally
    //   127	147	693	finally
    //   154	163	693	finally
    //   170	177	693	finally
    //   184	192	693	finally
    //   203	210	693	finally
    //   217	229	693	finally
    //   236	247	693	finally
    //   254	260	693	finally
    //   267	285	693	finally
    //   292	300	693	finally
    //   300	372	693	finally
    //   372	412	693	finally
    //   412	445	693	finally
    //   445	526	693	finally
    //   526	538	693	finally
    //   542	553	693	finally
    //   553	561	693	finally
    //   571	582	693	finally
    //   582	640	693	finally
    //   640	655	693	finally
    //   655	690	693	finally
    //   694	697	693	finally
    //   707	711	693	finally
    //   716	721	693	finally
    //   726	731	693	finally
    //   734	747	693	finally
    //   751	755	693	finally
    //   758	761	693	finally
    //   113	120	706	java/lang/Exception
    //   127	147	706	java/lang/Exception
    //   154	163	706	java/lang/Exception
    //   170	177	706	java/lang/Exception
    //   184	192	706	java/lang/Exception
    //   203	210	706	java/lang/Exception
    //   217	229	706	java/lang/Exception
    //   236	247	706	java/lang/Exception
    //   254	260	706	java/lang/Exception
    //   267	285	706	java/lang/Exception
    //   372	412	714	android/content/pm/PackageManager$NameNotFoundException
    //   412	445	724	java/lang/Exception
    //   553	561	750	java/lang/Exception
    //   571	582	750	java/lang/Exception
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
      //   22: astore_1
      //   23: aload_1
      //   24: ifnonnull +5 -> 29
      //   27: aconst_null
      //   28: areturn
      //   29: aload_0
      //   30: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   33: invokevirtual 45	com/appodeal/ads/t:b	()Ljava/net/URL;
      //   36: astore_3
      //   37: aload_0
      //   38: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   41: getfield 31	com/appodeal/ads/t:b	Landroid/content/Context;
      //   44: ldc 47
      //   46: iconst_0
      //   47: invokevirtual 39	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   50: astore 5
      //   52: aload_3
      //   53: invokevirtual 53	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   56: astore 4
      //   58: aload_3
      //   59: invokevirtual 57	java/net/URL:getProtocol	()Ljava/lang/String;
      //   62: ldc 59
      //   64: invokevirtual 65	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   67: ifeq +18 -> 85
      //   70: aload 4
      //   72: checkcast 67	javax/net/ssl/HttpsURLConnection
      //   75: aload_0
      //   76: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   79: invokestatic 70	com/appodeal/ads/t:a	(Lcom/appodeal/ads/t;)Ljavax/net/ssl/SSLSocketFactory;
      //   82: invokevirtual 74	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
      //   85: aload_0
      //   86: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   89: getfield 78	com/appodeal/ads/t:y	Z
      //   92: ifeq +314 -> 406
      //   95: aload 5
      //   97: aload_0
      //   98: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   101: getfield 82	com/appodeal/ads/t:d	Ljava/lang/String;
      //   104: invokeinterface 88 2 0
      //   109: ifeq +297 -> 406
      //   112: aload 4
      //   114: sipush 10000
      //   117: invokevirtual 94	java/net/URLConnection:setConnectTimeout	(I)V
      //   120: aload 4
      //   122: sipush 10000
      //   125: invokevirtual 97	java/net/URLConnection:setReadTimeout	(I)V
      //   128: aload 4
      //   130: sipush 20000
      //   133: invokevirtual 94	java/net/URLConnection:setConnectTimeout	(I)V
      //   136: aload 4
      //   138: sipush 20000
      //   141: invokevirtual 97	java/net/URLConnection:setReadTimeout	(I)V
      //   144: aload 4
      //   146: ldc 99
      //   148: ldc 101
      //   150: invokevirtual 105	java/net/URLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   153: aload 4
      //   155: iconst_1
      //   156: invokevirtual 109	java/net/URLConnection:setDoOutput	(Z)V
      //   159: new 111	java/io/ByteArrayOutputStream
      //   162: dup
      //   163: invokespecial 112	java/io/ByteArrayOutputStream:<init>	()V
      //   166: astore_3
      //   167: new 114	java/util/zip/GZIPOutputStream
      //   170: dup
      //   171: aload_3
      //   172: invokespecial 117	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   175: astore 6
      //   177: aload 6
      //   179: aload_1
      //   180: invokevirtual 122	org/json/JSONObject:toString	()Ljava/lang/String;
      //   183: ldc 124
      //   185: invokevirtual 128	java/lang/String:getBytes	(Ljava/lang/String;)[B
      //   188: invokevirtual 132	java/util/zip/GZIPOutputStream:write	([B)V
      //   191: aload 6
      //   193: invokevirtual 135	java/util/zip/GZIPOutputStream:close	()V
      //   196: aload_3
      //   197: invokevirtual 139	java/io/ByteArrayOutputStream:toByteArray	()[B
      //   200: iconst_0
      //   201: invokestatic 145	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
      //   204: astore_1
      //   205: aload 4
      //   207: invokevirtual 149	java/net/URLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   210: aload_1
      //   211: invokestatic 154	com/appodeal/ads/an:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   214: aload 4
      //   216: invokevirtual 158	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
      //   219: invokestatic 161	com/appodeal/ads/an:a	(Ljava/io/InputStream;)Ljava/lang/String;
      //   222: astore_3
      //   223: aload_3
      //   224: ifnull +36 -> 260
      //   227: aload_3
      //   228: invokevirtual 165	java/lang/String:isEmpty	()Z
      //   231: ifne +29 -> 260
      //   234: aload_3
      //   235: ldc -89
      //   237: invokevirtual 65	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   240: ifne +20 -> 260
      //   243: aload_0
      //   244: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   247: getfield 82	com/appodeal/ads/t:d	Ljava/lang/String;
      //   250: ldc -87
      //   252: invokevirtual 65	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   255: istore_2
      //   256: iload_2
      //   257: ifeq +5 -> 262
      //   260: aconst_null
      //   261: astore_3
      //   262: aload_3
      //   263: astore_1
      //   264: aload 4
      //   266: ifnull +21 -> 287
      //   269: aload 4
      //   271: instanceof 67
      //   274: ifeq +253 -> 527
      //   277: aload 4
      //   279: checkcast 67	javax/net/ssl/HttpsURLConnection
      //   282: invokevirtual 172	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   285: aload_3
      //   286: astore_1
      //   287: aload_1
      //   288: ifnonnull +338 -> 626
      //   291: aload_0
      //   292: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   295: getfield 78	com/appodeal/ads/t:y	Z
      //   298: ifeq +405 -> 703
      //   301: aload 5
      //   303: aload_0
      //   304: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   307: getfield 82	com/appodeal/ads/t:d	Ljava/lang/String;
      //   310: invokeinterface 88 2 0
      //   315: ifeq +388 -> 703
      //   318: new 174	com/appodeal/ads/utils/b/a
      //   321: dup
      //   322: ldc -80
      //   324: invokespecial 179	com/appodeal/ads/utils/b/a:<init>	(Ljava/lang/String;)V
      //   327: invokestatic 184	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   330: aload 5
      //   332: aload_0
      //   333: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   336: getfield 82	com/appodeal/ads/t:d	Ljava/lang/String;
      //   339: ldc -70
      //   341: invokeinterface 190 3 0
      //   346: astore_1
      //   347: new 119	org/json/JSONObject
      //   350: dup
      //   351: aload_1
      //   352: invokespecial 191	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   355: astore_3
      //   356: aload_0
      //   357: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   360: getfield 78	com/appodeal/ads/t:y	Z
      //   363: ifeq +305 -> 668
      //   366: aload_1
      //   367: getstatic 197	com/appodeal/ads/utils/Log$LogLevel:verbose	Lcom/appodeal/ads/utils/Log$LogLevel;
      //   370: invokestatic 200	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;Lcom/appodeal/ads/utils/Log$LogLevel;)V
      //   373: aload_3
      //   374: ldc -54
      //   376: invokevirtual 206	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   379: putstatic 212	com/appodeal/ads/UserSettings:userData	Lorg/json/JSONObject;
      //   382: aload_0
      //   383: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   386: aload_3
      //   387: invokestatic 215	com/appodeal/ads/t:a	(Lcom/appodeal/ads/t;Lorg/json/JSONObject;)V
      //   390: aload_3
      //   391: areturn
      //   392: astore_1
      //   393: aload_1
      //   394: invokestatic 184	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   397: aload_3
      //   398: areturn
      //   399: astore_1
      //   400: aload_1
      //   401: invokestatic 184	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   404: aconst_null
      //   405: areturn
      //   406: aload 4
      //   408: sipush 20000
      //   411: invokevirtual 94	java/net/URLConnection:setConnectTimeout	(I)V
      //   414: aload 4
      //   416: sipush 20000
      //   419: invokevirtual 97	java/net/URLConnection:setReadTimeout	(I)V
      //   422: goto -294 -> 128
      //   425: astore_3
      //   426: aload 4
      //   428: astore_1
      //   429: aload_3
      //   430: invokestatic 184	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   433: aload_3
      //   434: invokevirtual 218	java/io/IOException:getMessage	()Ljava/lang/String;
      //   437: ldc -36
      //   439: invokevirtual 65	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   442: ifne +17 -> 459
      //   445: aload_3
      //   446: invokevirtual 218	java/io/IOException:getMessage	()Ljava/lang/String;
      //   449: ldc -34
      //   451: invokevirtual 65	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   454: istore_2
      //   455: iload_2
      //   456: ifeq +111 -> 567
      //   459: aload_1
      //   460: ifnull +17 -> 477
      //   463: aload_1
      //   464: instanceof 67
      //   467: ifeq +83 -> 550
      //   470: aload_1
      //   471: checkcast 67	javax/net/ssl/HttpsURLConnection
      //   474: invokevirtual 172	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   477: aconst_null
      //   478: areturn
      //   479: astore_1
      //   480: aload_1
      //   481: invokestatic 184	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   484: goto -288 -> 196
      //   487: astore_3
      //   488: aload 4
      //   490: astore_1
      //   491: aload_1
      //   492: ifnull +17 -> 509
      //   495: aload_1
      //   496: instanceof 67
      //   499: ifeq +110 -> 609
      //   502: aload_1
      //   503: checkcast 67	javax/net/ssl/HttpsURLConnection
      //   506: invokevirtual 172	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   509: aload_3
      //   510: athrow
      //   511: astore_1
      //   512: aload 6
      //   514: invokevirtual 135	java/util/zip/GZIPOutputStream:close	()V
      //   517: aload_1
      //   518: athrow
      //   519: astore_3
      //   520: aload_3
      //   521: invokestatic 184	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   524: goto -7 -> 517
      //   527: aload_3
      //   528: astore_1
      //   529: aload 4
      //   531: instanceof 224
      //   534: ifeq -247 -> 287
      //   537: aload 4
      //   539: checkcast 224	java/net/HttpURLConnection
      //   542: invokevirtual 225	java/net/HttpURLConnection:disconnect	()V
      //   545: aload_3
      //   546: astore_1
      //   547: goto -260 -> 287
      //   550: aload_1
      //   551: instanceof 224
      //   554: ifeq -77 -> 477
      //   557: aload_1
      //   558: checkcast 224	java/net/HttpURLConnection
      //   561: invokevirtual 225	java/net/HttpURLConnection:disconnect	()V
      //   564: goto -87 -> 477
      //   567: aload_1
      //   568: ifnull +130 -> 698
      //   571: aload_1
      //   572: instanceof 67
      //   575: ifeq +15 -> 590
      //   578: aload_1
      //   579: checkcast 67	javax/net/ssl/HttpsURLConnection
      //   582: invokevirtual 172	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   585: aconst_null
      //   586: astore_1
      //   587: goto -300 -> 287
      //   590: aload_1
      //   591: instanceof 224
      //   594: ifeq +104 -> 698
      //   597: aload_1
      //   598: checkcast 224	java/net/HttpURLConnection
      //   601: invokevirtual 225	java/net/HttpURLConnection:disconnect	()V
      //   604: aconst_null
      //   605: astore_1
      //   606: goto -319 -> 287
      //   609: aload_1
      //   610: instanceof 224
      //   613: ifeq -104 -> 509
      //   616: aload_1
      //   617: checkcast 224	java/net/HttpURLConnection
      //   620: invokevirtual 225	java/net/HttpURLConnection:disconnect	()V
      //   623: goto -114 -> 509
      //   626: aload_0
      //   627: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   630: getfield 78	com/appodeal/ads/t:y	Z
      //   633: ifeq +32 -> 665
      //   636: aload 5
      //   638: invokeinterface 229 1 0
      //   643: astore_3
      //   644: aload_3
      //   645: aload_0
      //   646: getfield 14	com/appodeal/ads/t$b:a	Lcom/appodeal/ads/t;
      //   649: getfield 82	com/appodeal/ads/t:d	Ljava/lang/String;
      //   652: aload_1
      //   653: invokeinterface 235 3 0
      //   658: pop
      //   659: aload_3
      //   660: invokeinterface 238 1 0
      //   665: goto -318 -> 347
      //   668: aload_1
      //   669: invokestatic 240	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   672: goto -299 -> 373
      //   675: astore_1
      //   676: aload_1
      //   677: invokestatic 184	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   680: aconst_null
      //   681: areturn
      //   682: astore_3
      //   683: aconst_null
      //   684: astore_1
      //   685: goto -194 -> 491
      //   688: astore_3
      //   689: goto -198 -> 491
      //   692: astore_3
      //   693: aconst_null
      //   694: astore_1
      //   695: goto -266 -> 429
      //   698: aconst_null
      //   699: astore_1
      //   700: goto -413 -> 287
      //   703: aconst_null
      //   704: areturn
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	705	0	this	b
      //   0	705	1	paramVarArgs	Void[]
      //   255	201	2	bool	boolean
      //   36	362	3	localObject1	Object
      //   425	21	3	localIOException1	java.io.IOException
      //   487	23	3	localObject2	Object
      //   519	27	3	localException	Exception
      //   643	17	3	localEditor	SharedPreferences.Editor
      //   682	1	3	localObject3	Object
      //   688	1	3	localObject4	Object
      //   692	1	3	localIOException2	java.io.IOException
      //   56	482	4	localURLConnection	java.net.URLConnection
      //   50	587	5	localSharedPreferences	SharedPreferences
      //   175	338	6	localGZIPOutputStream	java.util.zip.GZIPOutputStream
      // Exception table:
      //   from	to	target	type
      //   373	390	392	java/lang/Exception
      //   0	23	399	java/lang/Exception
      //   29	52	399	java/lang/Exception
      //   269	285	399	java/lang/Exception
      //   291	347	399	java/lang/Exception
      //   347	373	399	java/lang/Exception
      //   393	397	399	java/lang/Exception
      //   463	477	399	java/lang/Exception
      //   495	509	399	java/lang/Exception
      //   509	511	399	java/lang/Exception
      //   529	545	399	java/lang/Exception
      //   550	564	399	java/lang/Exception
      //   571	585	399	java/lang/Exception
      //   590	604	399	java/lang/Exception
      //   609	623	399	java/lang/Exception
      //   626	665	399	java/lang/Exception
      //   668	672	399	java/lang/Exception
      //   676	680	399	java/lang/Exception
      //   58	85	425	java/io/IOException
      //   85	128	425	java/io/IOException
      //   128	177	425	java/io/IOException
      //   191	196	425	java/io/IOException
      //   196	223	425	java/io/IOException
      //   227	256	425	java/io/IOException
      //   406	422	425	java/io/IOException
      //   480	484	425	java/io/IOException
      //   512	517	425	java/io/IOException
      //   517	519	425	java/io/IOException
      //   520	524	425	java/io/IOException
      //   191	196	479	java/lang/Exception
      //   58	85	487	finally
      //   85	128	487	finally
      //   128	177	487	finally
      //   191	196	487	finally
      //   196	223	487	finally
      //   227	256	487	finally
      //   406	422	487	finally
      //   480	484	487	finally
      //   512	517	487	finally
      //   517	519	487	finally
      //   520	524	487	finally
      //   177	191	511	finally
      //   512	517	519	java/lang/Exception
      //   347	373	675	org/json/JSONException
      //   668	672	675	org/json/JSONException
      //   52	58	682	finally
      //   429	455	688	finally
      //   52	58	692	java/io/IOException
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
