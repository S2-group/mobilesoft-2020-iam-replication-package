package com.appodeal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.util.Pair;
import com.appodeal.ads.f.c;
import com.appodeal.ads.f.f;
import com.appodeal.ads.utils.d;
import com.appodeal.ads.utils.e;
import com.appodeal.ads.utils.q;
import com.appodeal.ads.utils.t;
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

public class u
{
  static JSONObject o;
  static SSLSocketFactory p;
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
  boolean q;
  boolean r;
  boolean s;
  boolean t;
  boolean u;
  boolean v;
  boolean w;
  boolean x;
  boolean y;
  
  private u(c paramC)
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
    while ((AppodealSettings.a) && ((this.a == null) || ((this.a instanceof com.appodeal.ads.utils.w))) && ((this.d.equals("stats")) || (this.d.equals("show")) || (this.d.equals("click")) || (this.d.equals("finish")) || (this.d.equals("install")))) {
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
        float f1 = ao.g(this.b);
        float f2 = ao.h(this.b);
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
      Object localObject1 = ao.d(this.b);
      localJSONObject.put("lt", ((Pair)localObject1).first);
      localJSONObject.put("lat", ((Pair)((Pair)localObject1).second).first);
      localJSONObject.put("lon", ((Pair)((Pair)localObject1).second).second);
      localObject1 = ao.b(this.b);
      localJSONObject.put("connection", ((ao.a)localObject1).a);
      localJSONObject.put("battery", ao.k(this.b));
      localJSONObject.put("connection_subtype", ((ao.a)localObject1).b);
      localJSONObject.put("connection_fast", ((ao.a)localObject1).c);
      localJSONObject.put("crr", ao.c(this.b));
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
          localObject3 = o.a(this.b).iterator();
          while (((Iterator)localObject3).hasNext())
          {
            localObject4 = (p)((Iterator)localObject3).next();
            if (((p)localObject4).g() != null)
            {
              ((JSONArray)localObject1).put(((p)localObject4).a());
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
        localJSONObject.put("sa", t.a(this.b));
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
              localObject3 = ai.a(this.b).iterator();
              while (((Iterator)localObject3).hasNext())
              {
                localObject4 = (aq)((Iterator)localObject3).next();
                if (((aq)localObject4).g() != null) {
                  localException1.put(((aq)localObject4).a());
                }
              }
            }
            if (this.u)
            {
              localObject3 = al.a(this.b).iterator();
              while (((Iterator)localObject3).hasNext())
              {
                localObject4 = (aq)((Iterator)localObject3).next();
                if (((aq)localObject4).g() != null) {
                  localException1.put(((aq)localObject4).a());
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
              localObject3 = w.a(this.b).iterator();
              while (((Iterator)localObject3).hasNext())
              {
                localObject4 = (x)((Iterator)localObject3).next();
                if (((x)localObject4).f() != null) {
                  localException1.put(((x)localObject4).a());
                }
              }
            }
            if (this.v)
            {
              localObject3 = Native.a(this.b).iterator();
              while (((Iterator)localObject3).hasNext())
              {
                localObject4 = (ad)((Iterator)localObject3).next();
                if (localObject4 != null) {
                  localException1.put(((ad)localObject4).a());
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
            new u.b(u.this, null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
          }
          new u.b(u.this, null).execute(new Void[0]);
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
  JSONObject b(SharedPreferences paramSharedPreferences)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 746	com/appodeal/ads/u:o	Lorg/json/JSONObject;
    //   6: ifnonnull +634 -> 640
    //   9: new 201	org/json/JSONObject
    //   12: dup
    //   13: invokespecial 747	org/json/JSONObject:<init>	()V
    //   16: putstatic 746	com/appodeal/ads/u:o	Lorg/json/JSONObject;
    //   19: aload_0
    //   20: getfield 64	com/appodeal/ads/u:b	Landroid/content/Context;
    //   23: invokevirtual 557	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   26: astore 7
    //   28: aload_1
    //   29: ldc_w 749
    //   32: aconst_null
    //   33: invokeinterface 447 3 0
    //   38: astore 8
    //   40: aload 8
    //   42: ifnonnull +8 -> 50
    //   45: ldc 2
    //   47: monitorexit
    //   48: aconst_null
    //   49: areturn
    //   50: aload_1
    //   51: ldc_w 751
    //   54: aconst_null
    //   55: invokeinterface 447 3 0
    //   60: astore 6
    //   62: aload_1
    //   63: ldc_w 753
    //   66: aconst_null
    //   67: invokeinterface 447 3 0
    //   72: astore_3
    //   73: aload_3
    //   74: astore 5
    //   76: aload 6
    //   78: astore 4
    //   80: aload 6
    //   82: ifnonnull +684 -> 766
    //   85: aload_0
    //   86: getfield 74	com/appodeal/ads/u:d	Ljava/lang/String;
    //   89: ldc -115
    //   91: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
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
    //   113: ldc_w 755
    //   116: invokestatic 761	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   119: pop
    //   120: aload_3
    //   121: astore 5
    //   123: aload 6
    //   125: astore 4
    //   127: ldc_w 763
    //   130: ldc_w 765
    //   133: iconst_1
    //   134: anewarray 757	java/lang/Class
    //   137: dup
    //   138: iconst_0
    //   139: ldc_w 553
    //   142: aastore
    //   143: invokevirtual 769	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   146: pop
    //   147: aload_3
    //   148: astore 5
    //   150: aload 6
    //   152: astore 4
    //   154: aload_0
    //   155: getfield 64	com/appodeal/ads/u:b	Landroid/content/Context;
    //   158: invokestatic 772	com/google/android/gms/ads/identifier/AdvertisingIdClient:getAdvertisingIdInfo	(Landroid/content/Context;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
    //   161: astore 9
    //   163: aload_3
    //   164: astore 5
    //   166: aload 6
    //   168: astore 4
    //   170: aload 9
    //   172: invokevirtual 777	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:getId	()Ljava/lang/String;
    //   175: astore 6
    //   177: aload_3
    //   178: astore 5
    //   180: aload 6
    //   182: astore 4
    //   184: aload 9
    //   186: invokevirtual 780	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:isLimitAdTrackingEnabled	()Z
    //   189: ifeq +510 -> 699
    //   192: ldc_w 782
    //   195: astore_3
    //   196: aload_3
    //   197: astore 5
    //   199: aload 6
    //   201: astore 4
    //   203: aload_1
    //   204: invokeinterface 532 1 0
    //   209: astore_1
    //   210: aload_3
    //   211: astore 5
    //   213: aload 6
    //   215: astore 4
    //   217: aload_1
    //   218: ldc_w 751
    //   221: aload 6
    //   223: invokeinterface 786 3 0
    //   228: pop
    //   229: aload_3
    //   230: astore 5
    //   232: aload 6
    //   234: astore 4
    //   236: aload_1
    //   237: ldc_w 753
    //   240: aload_3
    //   241: invokeinterface 786 3 0
    //   246: pop
    //   247: aload_3
    //   248: astore 5
    //   250: aload 6
    //   252: astore 4
    //   254: aload_1
    //   255: invokeinterface 545 1 0
    //   260: aload_3
    //   261: astore 5
    //   263: aload 6
    //   265: astore 4
    //   267: ldc_w 788
    //   270: iconst_1
    //   271: anewarray 4	java/lang/Object
    //   274: dup
    //   275: iconst_0
    //   276: aload 6
    //   278: aastore
    //   279: invokestatic 741	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   282: invokestatic 676	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
    //   285: aload 6
    //   287: astore_1
    //   288: aload_1
    //   289: ifnonnull +474 -> 763
    //   292: aload_0
    //   293: getfield 64	com/appodeal/ads/u:b	Landroid/content/Context;
    //   296: invokestatic 790	com/appodeal/ads/ao:l	(Landroid/content/Context;)Ljava/lang/String;
    //   299: astore_1
    //   300: getstatic 746	com/appodeal/ads/u:o	Lorg/json/JSONObject;
    //   303: ldc_w 792
    //   306: aload 8
    //   308: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   311: pop
    //   312: getstatic 746	com/appodeal/ads/u:o	Lorg/json/JSONObject;
    //   315: ldc_w 588
    //   318: getstatic 795	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   321: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   324: pop
    //   325: getstatic 746	com/appodeal/ads/u:o	Lorg/json/JSONObject;
    //   328: ldc_w 797
    //   331: getstatic 697	android/os/Build$VERSION:SDK_INT	I
    //   334: invokevirtual 652	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   337: pop
    //   338: getstatic 746	com/appodeal/ads/u:o	Lorg/json/JSONObject;
    //   341: ldc_w 799
    //   344: ldc_w 801
    //   347: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   350: pop
    //   351: aload_0
    //   352: getfield 64	com/appodeal/ads/u:b	Landroid/content/Context;
    //   355: invokevirtual 804	android/content/Context:getPackageName	()Ljava/lang/String;
    //   358: astore 4
    //   360: getstatic 746	com/appodeal/ads/u:o	Lorg/json/JSONObject;
    //   363: ldc_w 806
    //   366: aload 4
    //   368: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   371: pop
    //   372: aload 7
    //   374: aload 4
    //   376: iconst_0
    //   377: invokevirtual 810	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   380: astore 5
    //   382: getstatic 746	com/appodeal/ads/u:o	Lorg/json/JSONObject;
    //   385: ldc_w 812
    //   388: aload 5
    //   390: getfield 817	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   393: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   396: pop
    //   397: getstatic 746	com/appodeal/ads/u:o	Lorg/json/JSONObject;
    //   400: ldc_w 819
    //   403: aload 5
    //   405: getfield 822	android/content/pm/PackageInfo:versionCode	I
    //   408: invokevirtual 652	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   411: pop
    //   412: aload 7
    //   414: aload 4
    //   416: sipush 128
    //   419: invokevirtual 826	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   422: astore 5
    //   424: getstatic 746	com/appodeal/ads/u:o	Lorg/json/JSONObject;
    //   427: ldc_w 828
    //   430: aload 5
    //   432: getfield 832	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   435: ldc_w 834
    //   438: invokevirtual 838	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   441: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   444: pop
    //   445: getstatic 746	com/appodeal/ads/u:o	Lorg/json/JSONObject;
    //   448: ldc_w 840
    //   451: aload_1
    //   452: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   455: pop
    //   456: getstatic 746	com/appodeal/ads/u:o	Lorg/json/JSONObject;
    //   459: ldc_w 842
    //   462: aload_3
    //   463: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   466: pop
    //   467: aload_0
    //   468: getfield 64	com/appodeal/ads/u:b	Landroid/content/Context;
    //   471: invokestatic 844	com/appodeal/ads/ao:f	(Landroid/content/Context;)Landroid/util/Pair;
    //   474: astore_1
    //   475: getstatic 746	com/appodeal/ads/u:o	Lorg/json/JSONObject;
    //   478: ldc_w 846
    //   481: aload_1
    //   482: getfield 319	android/util/Pair:first	Ljava/lang/Object;
    //   485: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   488: pop
    //   489: getstatic 746	com/appodeal/ads/u:o	Lorg/json/JSONObject;
    //   492: ldc_w 848
    //   495: aload_1
    //   496: getfield 324	android/util/Pair:second	Ljava/lang/Object;
    //   499: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   502: pop
    //   503: aload_0
    //   504: getfield 64	com/appodeal/ads/u:b	Landroid/content/Context;
    //   507: invokestatic 851	com/appodeal/ads/ao:n	(Landroid/content/Context;)Z
    //   510: ifeq +224 -> 734
    //   513: getstatic 746	com/appodeal/ads/u:o	Lorg/json/JSONObject;
    //   516: ldc_w 853
    //   519: ldc_w 855
    //   522: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   525: pop
    //   526: getstatic 860	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   529: ldc_w 862
    //   532: invokevirtual 133	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   535: ifeq +240 -> 775
    //   538: ldc_w 864
    //   541: astore_1
    //   542: getstatic 746	com/appodeal/ads/u:o	Lorg/json/JSONObject;
    //   545: ldc_w 866
    //   548: aload_1
    //   549: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   552: pop
    //   553: aload 7
    //   555: aload 4
    //   557: invokevirtual 869	android/content/pm/PackageManager:getInstallerPackageName	(Ljava/lang/String;)Ljava/lang/String;
    //   560: astore_3
    //   561: aload_3
    //   562: astore_1
    //   563: aload_3
    //   564: ifnonnull +7 -> 571
    //   567: ldc_w 871
    //   570: astore_1
    //   571: getstatic 746	com/appodeal/ads/u:o	Lorg/json/JSONObject;
    //   574: ldc_w 873
    //   577: aload_1
    //   578: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   581: pop
    //   582: getstatic 746	com/appodeal/ads/u:o	Lorg/json/JSONObject;
    //   585: ldc_w 875
    //   588: getstatic 860	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   591: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   594: pop
    //   595: getstatic 746	com/appodeal/ads/u:o	Lorg/json/JSONObject;
    //   598: ldc_w 877
    //   601: ldc_w 879
    //   604: iconst_2
    //   605: anewarray 4	java/lang/Object
    //   608: dup
    //   609: iconst_0
    //   610: getstatic 860	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   613: aastore
    //   614: dup
    //   615: iconst_1
    //   616: getstatic 882	android/os/Build:MODEL	Ljava/lang/String;
    //   619: aastore
    //   620: invokestatic 741	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   623: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   626: pop
    //   627: getstatic 746	com/appodeal/ads/u:o	Lorg/json/JSONObject;
    //   630: ldc_w 884
    //   633: invokestatic 886	com/appodeal/ads/ao:a	()Z
    //   636: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   639: pop
    //   640: new 201	org/json/JSONObject
    //   643: dup
    //   644: invokespecial 747	org/json/JSONObject:<init>	()V
    //   647: astore_1
    //   648: getstatic 746	com/appodeal/ads/u:o	Lorg/json/JSONObject;
    //   651: invokevirtual 889	org/json/JSONObject:keys	()Ljava/util/Iterator;
    //   654: astore_3
    //   655: aload_3
    //   656: invokeinterface 468 1 0
    //   661: ifeq +97 -> 758
    //   664: aload_3
    //   665: invokeinterface 472 1 0
    //   670: checkcast 129	java/lang/String
    //   673: astore 4
    //   675: aload_1
    //   676: aload 4
    //   678: getstatic 746	com/appodeal/ads/u:o	Lorg/json/JSONObject;
    //   681: aload 4
    //   683: invokevirtual 892	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   686: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   689: pop
    //   690: goto -35 -> 655
    //   693: astore_1
    //   694: ldc 2
    //   696: monitorexit
    //   697: aload_1
    //   698: athrow
    //   699: ldc_w 894
    //   702: astore_3
    //   703: goto -507 -> 196
    //   706: astore_1
    //   707: aload_1
    //   708: invokestatic 240	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
    //   711: goto +55 -> 766
    //   714: astore 5
    //   716: aload 5
    //   718: invokestatic 240	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
    //   721: goto -309 -> 412
    //   724: astore 5
    //   726: aload 5
    //   728: invokestatic 240	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
    //   731: goto -286 -> 445
    //   734: getstatic 746	com/appodeal/ads/u:o	Lorg/json/JSONObject;
    //   737: ldc_w 853
    //   740: ldc_w 896
    //   743: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   746: pop
    //   747: goto -221 -> 526
    //   750: astore_1
    //   751: aload_1
    //   752: invokestatic 240	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
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
    //   775: ldc_w 898
    //   778: astore_1
    //   779: goto -237 -> 542
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	782	0	this	u
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
  
  JSONObject c()
  {
    JSONObject localJSONObject = new JSONObject();
    int i1 = g.y;
    int i2 = o.q;
    int i3 = ai.n;
    int i4 = al.n;
    int i5 = Native.p;
    int i6 = w.u;
    int i7 = g.z;
    int i8 = o.r;
    int i9 = ai.p;
    int i10 = al.p;
    int i11 = Native.q;
    int i12 = w.v;
    int i13 = al.o;
    int i14 = ai.o;
    try
    {
      localJSONObject.put("show", i1 + i2 + i3 + i4 + i5 + i6);
      localJSONObject.put("click", i7 + i8 + i9 + i10 + i11 + i12);
      if ((this.t) || (this.u) || ((this.k != null) && ((this.k.equals("video")) || (this.k.equals("rewarded_video"))))) {
        localJSONObject.put("finish", i13 + i14);
      }
      if ((this.q) || ((this.k != null) && (this.k.equals("banner"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "show" }), o.q);
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "click" }), o.r);
      }
      if ((this.t) || ((this.k != null) && (this.k.equals("video"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "show" }), ai.n);
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "click" }), ai.p);
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "finish" }), ai.o);
      }
      if ((this.u) || ((this.k != null) && (this.k.equals("rewarded_video"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "show" }), al.n);
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "click" }), al.p);
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "finish" }), al.o);
      }
      if ((this.r) || ((this.k != null) && (this.k.equals("banner_320"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "show" }), g.y);
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "click" }), g.z);
      }
      if ((this.s) || ((this.k != null) && (this.k.equals("banner_mrec"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "show" }), w.u);
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "click" }), w.v);
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
      //   1: getfield 14	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   4: getfield 31	com/appodeal/ads/u:b	Landroid/content/Context;
      //   7: ldc 33
      //   9: iconst_0
      //   10: invokevirtual 39	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   13: astore_1
      //   14: aload_0
      //   15: getfield 14	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   18: aload_1
      //   19: invokevirtual 42	com/appodeal/ads/u:a	(Landroid/content/SharedPreferences;)Lorg/json/JSONObject;
      //   22: astore_1
      //   23: aload_1
      //   24: ifnonnull +5 -> 29
      //   27: aconst_null
      //   28: areturn
      //   29: aload_0
      //   30: getfield 14	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   33: invokevirtual 45	com/appodeal/ads/u:b	()Ljava/net/URL;
      //   36: astore_3
      //   37: aload_0
      //   38: getfield 14	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   41: getfield 31	com/appodeal/ads/u:b	Landroid/content/Context;
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
      //   76: getfield 14	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   79: invokestatic 70	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljavax/net/ssl/SSLSocketFactory;
      //   82: invokevirtual 74	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
      //   85: aload_0
      //   86: getfield 14	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   89: getfield 78	com/appodeal/ads/u:y	Z
      //   92: ifeq +301 -> 393
      //   95: aload 5
      //   97: aload_0
      //   98: getfield 14	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   101: getfield 82	com/appodeal/ads/u:d	Ljava/lang/String;
      //   104: invokeinterface 88 2 0
      //   109: ifeq +284 -> 393
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
      //   166: astore 6
      //   168: new 114	java/util/zip/GZIPOutputStream
      //   171: dup
      //   172: aload 6
      //   174: invokespecial 117	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   177: astore_3
      //   178: aload_3
      //   179: aload_1
      //   180: invokevirtual 122	org/json/JSONObject:toString	()Ljava/lang/String;
      //   183: ldc 124
      //   185: invokevirtual 128	java/lang/String:getBytes	(Ljava/lang/String;)[B
      //   188: invokevirtual 132	java/util/zip/GZIPOutputStream:write	([B)V
      //   191: aload_3
      //   192: invokevirtual 135	java/util/zip/GZIPOutputStream:close	()V
      //   195: aload 6
      //   197: invokevirtual 139	java/io/ByteArrayOutputStream:toByteArray	()[B
      //   200: iconst_0
      //   201: invokestatic 145	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
      //   204: astore_1
      //   205: aload 4
      //   207: invokevirtual 149	java/net/URLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   210: aload_1
      //   211: invokestatic 154	com/appodeal/ads/ao:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   214: aload 4
      //   216: invokevirtual 158	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
      //   219: invokestatic 161	com/appodeal/ads/ao:a	(Ljava/io/InputStream;)Ljava/lang/String;
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
      //   244: getfield 14	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   247: getfield 82	com/appodeal/ads/u:d	Ljava/lang/String;
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
      //   274: ifeq +223 -> 497
      //   277: aload 4
      //   279: checkcast 67	javax/net/ssl/HttpsURLConnection
      //   282: invokevirtual 172	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   285: aload_3
      //   286: astore_1
      //   287: aload_1
      //   288: ifnonnull +308 -> 596
      //   291: aload_0
      //   292: getfield 14	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   295: getfield 78	com/appodeal/ads/u:y	Z
      //   298: ifeq +368 -> 666
      //   301: aload 5
      //   303: aload_0
      //   304: getfield 14	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   307: getfield 82	com/appodeal/ads/u:d	Ljava/lang/String;
      //   310: invokeinterface 88 2 0
      //   315: ifeq +351 -> 666
      //   318: new 174	com/appodeal/ads/utils/b/a
      //   321: dup
      //   322: ldc -80
      //   324: invokespecial 179	com/appodeal/ads/utils/b/a:<init>	(Ljava/lang/String;)V
      //   327: invokestatic 184	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   330: aload 5
      //   332: aload_0
      //   333: getfield 14	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   336: getfield 82	com/appodeal/ads/u:d	Ljava/lang/String;
      //   339: ldc -70
      //   341: invokeinterface 190 3 0
      //   346: astore_1
      //   347: new 119	org/json/JSONObject
      //   350: dup
      //   351: aload_1
      //   352: invokespecial 191	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   355: astore_1
      //   356: aload_1
      //   357: invokestatic 194	com/appodeal/ads/Appodeal:a	(Lorg/json/JSONObject;)V
      //   360: aload_1
      //   361: ldc -60
      //   363: invokevirtual 200	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   366: putstatic 206	com/appodeal/ads/UserSettings:userData	Lorg/json/JSONObject;
      //   369: aload_0
      //   370: getfield 14	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   373: aload_1
      //   374: invokestatic 209	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;Lorg/json/JSONObject;)V
      //   377: aload_1
      //   378: areturn
      //   379: astore_3
      //   380: aload_3
      //   381: invokestatic 184	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   384: aload_1
      //   385: areturn
      //   386: astore_1
      //   387: aload_1
      //   388: invokestatic 184	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   391: aconst_null
      //   392: areturn
      //   393: aload 4
      //   395: sipush 20000
      //   398: invokevirtual 94	java/net/URLConnection:setConnectTimeout	(I)V
      //   401: aload 4
      //   403: sipush 20000
      //   406: invokevirtual 97	java/net/URLConnection:setReadTimeout	(I)V
      //   409: goto -281 -> 128
      //   412: astore_3
      //   413: aload 4
      //   415: astore_1
      //   416: aload_3
      //   417: invokestatic 184	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   420: aload_3
      //   421: invokevirtual 212	java/io/IOException:getMessage	()Ljava/lang/String;
      //   424: ldc -42
      //   426: invokevirtual 65	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   429: ifne +17 -> 446
      //   432: aload_3
      //   433: invokevirtual 212	java/io/IOException:getMessage	()Ljava/lang/String;
      //   436: ldc -40
      //   438: invokevirtual 65	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   441: istore_2
      //   442: iload_2
      //   443: ifeq +94 -> 537
      //   446: aload_1
      //   447: ifnull +17 -> 464
      //   450: aload_1
      //   451: instanceof 67
      //   454: ifeq +66 -> 520
      //   457: aload_1
      //   458: checkcast 67	javax/net/ssl/HttpsURLConnection
      //   461: invokevirtual 172	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   464: aconst_null
      //   465: areturn
      //   466: astore_1
      //   467: aload_3
      //   468: invokevirtual 135	java/util/zip/GZIPOutputStream:close	()V
      //   471: aload_1
      //   472: athrow
      //   473: astore_3
      //   474: aload 4
      //   476: astore_1
      //   477: aload_1
      //   478: ifnull +17 -> 495
      //   481: aload_1
      //   482: instanceof 67
      //   485: ifeq +94 -> 579
      //   488: aload_1
      //   489: checkcast 67	javax/net/ssl/HttpsURLConnection
      //   492: invokevirtual 172	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   495: aload_3
      //   496: athrow
      //   497: aload_3
      //   498: astore_1
      //   499: aload 4
      //   501: instanceof 218
      //   504: ifeq -217 -> 287
      //   507: aload 4
      //   509: checkcast 218	java/net/HttpURLConnection
      //   512: invokevirtual 219	java/net/HttpURLConnection:disconnect	()V
      //   515: aload_3
      //   516: astore_1
      //   517: goto -230 -> 287
      //   520: aload_1
      //   521: instanceof 218
      //   524: ifeq -60 -> 464
      //   527: aload_1
      //   528: checkcast 218	java/net/HttpURLConnection
      //   531: invokevirtual 219	java/net/HttpURLConnection:disconnect	()V
      //   534: goto -70 -> 464
      //   537: aload_1
      //   538: ifnull +123 -> 661
      //   541: aload_1
      //   542: instanceof 67
      //   545: ifeq +15 -> 560
      //   548: aload_1
      //   549: checkcast 67	javax/net/ssl/HttpsURLConnection
      //   552: invokevirtual 172	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   555: aconst_null
      //   556: astore_1
      //   557: goto -270 -> 287
      //   560: aload_1
      //   561: instanceof 218
      //   564: ifeq +97 -> 661
      //   567: aload_1
      //   568: checkcast 218	java/net/HttpURLConnection
      //   571: invokevirtual 219	java/net/HttpURLConnection:disconnect	()V
      //   574: aconst_null
      //   575: astore_1
      //   576: goto -289 -> 287
      //   579: aload_1
      //   580: instanceof 218
      //   583: ifeq -88 -> 495
      //   586: aload_1
      //   587: checkcast 218	java/net/HttpURLConnection
      //   590: invokevirtual 219	java/net/HttpURLConnection:disconnect	()V
      //   593: goto -98 -> 495
      //   596: aload_0
      //   597: getfield 14	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   600: getfield 78	com/appodeal/ads/u:y	Z
      //   603: ifeq +65 -> 668
      //   606: aload 5
      //   608: invokeinterface 223 1 0
      //   613: astore_3
      //   614: aload_3
      //   615: aload_0
      //   616: getfield 14	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   619: getfield 82	com/appodeal/ads/u:d	Ljava/lang/String;
      //   622: aload_1
      //   623: invokeinterface 229 3 0
      //   628: pop
      //   629: aload_3
      //   630: invokeinterface 232 1 0
      //   635: goto +33 -> 668
      //   638: astore_1
      //   639: aload_1
      //   640: invokestatic 184	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   643: aconst_null
      //   644: areturn
      //   645: astore_3
      //   646: aconst_null
      //   647: astore_1
      //   648: goto -171 -> 477
      //   651: astore_3
      //   652: goto -175 -> 477
      //   655: astore_3
      //   656: aconst_null
      //   657: astore_1
      //   658: goto -242 -> 416
      //   661: aconst_null
      //   662: astore_1
      //   663: goto -376 -> 287
      //   666: aconst_null
      //   667: areturn
      //   668: goto -321 -> 347
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	671	0	this	b
      //   0	671	1	paramVarArgs	Void[]
      //   255	188	2	bool	boolean
      //   36	250	3	localObject1	Object
      //   379	2	3	localException	Exception
      //   412	56	3	localIOException1	java.io.IOException
      //   473	43	3	localObject2	Object
      //   613	17	3	localEditor	SharedPreferences.Editor
      //   645	1	3	localObject3	Object
      //   651	1	3	localObject4	Object
      //   655	1	3	localIOException2	java.io.IOException
      //   56	452	4	localURLConnection	java.net.URLConnection
      //   50	557	5	localSharedPreferences	SharedPreferences
      //   166	30	6	localByteArrayOutputStream	java.io.ByteArrayOutputStream
      // Exception table:
      //   from	to	target	type
      //   360	377	379	java/lang/Exception
      //   0	23	386	java/lang/Exception
      //   29	52	386	java/lang/Exception
      //   269	285	386	java/lang/Exception
      //   291	347	386	java/lang/Exception
      //   347	360	386	java/lang/Exception
      //   380	384	386	java/lang/Exception
      //   450	464	386	java/lang/Exception
      //   481	495	386	java/lang/Exception
      //   495	497	386	java/lang/Exception
      //   499	515	386	java/lang/Exception
      //   520	534	386	java/lang/Exception
      //   541	555	386	java/lang/Exception
      //   560	574	386	java/lang/Exception
      //   579	593	386	java/lang/Exception
      //   596	635	386	java/lang/Exception
      //   639	643	386	java/lang/Exception
      //   58	85	412	java/io/IOException
      //   85	128	412	java/io/IOException
      //   128	178	412	java/io/IOException
      //   191	223	412	java/io/IOException
      //   227	256	412	java/io/IOException
      //   393	409	412	java/io/IOException
      //   467	473	412	java/io/IOException
      //   178	191	466	finally
      //   58	85	473	finally
      //   85	128	473	finally
      //   128	178	473	finally
      //   191	223	473	finally
      //   227	256	473	finally
      //   393	409	473	finally
      //   467	473	473	finally
      //   347	360	638	org/json/JSONException
      //   52	58	645	finally
      //   416	442	651	finally
      //   52	58	655	java/io/IOException
    }
    
    protected void a(JSONObject paramJSONObject)
    {
      try
      {
        if (u.this.a != null)
        {
          if (paramJSONObject == null)
          {
            u.this.a.a(u.this.c);
            return;
          }
          u.this.a.a(paramJSONObject, u.this.c, u.this.d);
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
    private u.a d;
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
    
    public c a(u.a paramA)
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
    
    public u a()
    {
      return new u(this, null);
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
