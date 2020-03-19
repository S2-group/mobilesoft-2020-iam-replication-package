package com.revmob.android;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.revmob.RevMobAdsListener;
import com.revmob.RevMobTestingMode;
import com.revmob.ads.banner.RevMobBanner;
import com.revmob.client.RevMobClient;
import com.revmob.internal.RMLog;
import com.revmob.internal.c;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class e
{
  public static Context a;
  public static RevMobAdsListener b;
  public static boolean c;
  public static boolean d;
  public static boolean e;
  public static boolean f;
  public static Thread g;
  private static DisplayMetrics h = new DisplayMetrics();
  private static String i;
  private static String j;
  private static String k;
  private static com.revmob.client.f l;
  private static int m;
  private static int n;
  private static int o;
  private static JSONArray p;
  private static JSONArray q;
  
  public static String a()
  {
    ((Activity)a).getWindowManager().getDefaultDisplay().getMetrics(h);
    return e().toString();
  }
  
  private static JSONArray a(int paramInt, JSONArray paramJSONArray)
  {
    JSONArray localJSONArray = new JSONArray();
    int i1 = paramInt;
    if (paramJSONArray.length() < paramInt) {
      i1 = paramJSONArray.length();
    }
    paramInt = 0;
    while (paramInt < i1)
    {
      try
      {
        localJSONArray.put(paramJSONArray.get(paramInt));
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
      paramInt += 1;
    }
    return localJSONArray;
  }
  
  private static JSONArray a(JSONArray paramJSONArray, int paramInt)
  {
    JSONArray localJSONArray = new JSONArray();
    int i2 = paramJSONArray.length();
    if (paramJSONArray != null)
    {
      int i1 = 0;
      while (i1 < i2)
      {
        if (i1 != paramInt) {
          try
          {
            localJSONArray.put(paramJSONArray.get(i1));
          }
          catch (JSONException localJSONException)
          {
            localJSONException.printStackTrace();
          }
        }
        i1 += 1;
      }
    }
    return localJSONArray;
  }
  
  private static JSONArray a(JSONArray paramJSONArray, Object paramObject)
  {
    JSONArray localJSONArray = new JSONArray();
    localJSONArray.put(paramObject);
    int i1 = 0;
    for (;;)
    {
      if (i1 < paramJSONArray.length()) {}
      try
      {
        localJSONArray.put(paramJSONArray.get(i1));
        i1 += 1;
        continue;
        return localJSONArray;
      }
      catch (JSONException paramObject)
      {
        for (;;) {}
      }
    }
  }
  
  public static void a(int paramInt1, int paramInt2, int paramInt3)
  {
    m = paramInt2;
    n = paramInt3;
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(a);
    if (paramInt1 > 0) {
      try
      {
        p = new JSONArray(localSharedPreferences.getString("sessions", "[]"));
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("date", (int)(System.currentTimeMillis() / 1000L));
        p = a(p, localJSONObject);
        p = a(paramInt1, p);
      }
      catch (JSONException localJSONException2)
      {
        localJSONException2.printStackTrace();
      }
    }
    if (m > 0) {
      try
      {
        q = new JSONArray(localSharedPreferences.getString("history", "[]"));
        q = a(m, q);
        return;
      }
      catch (JSONException localJSONException1)
      {
        localJSONException1.printStackTrace();
      }
    }
  }
  
  public static void a(String paramString, Activity paramActivity)
  {
    a = paramActivity;
    StringBuilder localStringBuilder;
    if (RevMobClient.c != null)
    {
      localStringBuilder = new StringBuilder("RevMob SDK Version: ");
      localStringBuilder.append(RevMobClient.b);
      localStringBuilder.append(" (");
      localStringBuilder.append(RevMobClient.c);
      localStringBuilder.append("-");
      localStringBuilder.append(RevMobClient.d);
    }
    for (paramActivity = ")";; paramActivity = RevMobClient.b)
    {
      localStringBuilder.append(paramActivity);
      RMLog.i(localStringBuilder.toString());
      break;
      localStringBuilder = new StringBuilder("RevMob SDK Version: ");
    }
    paramActivity = new StringBuilder("App ID: ");
    paramActivity.append(paramString);
    RMLog.i(paramActivity.toString());
    paramString = new StringBuilder("IP Address: ");
    paramString.append(c.b());
    RMLog.i(paramString.toString());
    paramString = new StringBuilder("Simulator: ");
    boolean bool;
    if ((!Build.MODEL.contains("sdk")) && (!Build.MODEL.contains("Emulator"))) {
      bool = false;
    } else {
      bool = true;
    }
    paramString.append(bool);
    RMLog.i(paramString.toString());
    paramString = new StringBuilder("OS Version: ");
    paramString.append(Build.VERSION.RELEASE);
    RMLog.i(paramString.toString());
    paramString = new StringBuilder("Android API: ");
    paramString.append(Build.VERSION.SDK_INT);
    RMLog.i(paramString.toString());
    paramString = new StringBuilder("Manufacturer: ");
    paramString.append(Build.MANUFACTURER);
    RMLog.i(paramString.toString());
    paramString = new StringBuilder("Model: ");
    paramString.append(Build.MODEL);
    RMLog.i(paramString.toString());
    RMLog.i("Android ID: ");
    RMLog.i("Serial number: ");
    paramString = new StringBuilder("ID for Advertising: ");
    paramString.append(i);
    RMLog.i(paramString.toString());
    paramString = new StringBuilder("Limit Ad Tracking: ");
    paramString.append(j);
    RMLog.i(paramString.toString());
    paramString = new StringBuilder("Language: ");
    paramString.append(Locale.getDefault().getLanguage());
    RMLog.i(paramString.toString());
    paramString = new StringBuilder("Locale: ");
    paramString.append(j());
    RMLog.i(paramString.toString());
    paramString = new StringBuilder("User Agent: ");
    paramString.append(c.a());
    RMLog.i(paramString.toString());
    paramString = new StringBuilder("Screen size: ");
    paramString.append(h.widthPixels);
    paramString.append(",");
    paramString.append(h.heightPixels);
    RMLog.i(paramString.toString());
    paramString = new StringBuilder("Density scale: ");
    paramString.append(h.density);
    RMLog.i(paramString.toString());
    paramString = new StringBuilder("Density dpi: ");
    paramString.append(h.densityDpi);
    RMLog.i(paramString.toString());
    try
    {
      paramString = new StringBuilder("User Location: ");
      paramString.append(g());
      RMLog.i(paramString.toString());
      return;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static void a(String paramString, com.revmob.client.f paramF, RevMobAdsListener paramRevMobAdsListener, Activity paramActivity)
  {
    try
    {
      k = paramString;
      l = paramF;
      b = paramRevMobAdsListener;
      a = paramActivity;
      if (g == null)
      {
        paramString = new Thread(new f());
        g = paramString;
        paramString.start();
      }
      return;
    }
    catch (Exception paramString)
    {
      paramF = new StringBuilder("Error loading advertising info: ");
      paramF.append(paramString.getMessage());
      RMLog.e(paramF.toString());
    }
  }
  
  public static void a(String paramString1, String paramString2)
  {
    int i1;
    if ((paramString1 != null) && (!paramString1.equals("")) && (!paramString2.equals("")))
    {
      o += 1;
      if (q != null)
      {
        localObject1 = null;
        i1 = 0;
        for (;;)
        {
          localObject2 = localObject1;
          if (i1 >= q.length()) {
            break;
          }
          try
          {
            localObject2 = q.getJSONObject(i1);
          }
          catch (JSONException localJSONException2)
          {
            for (;;) {}
          }
          localObject2 = null;
          try
          {
            boolean bool = ((JSONObject)localObject2).names().getString(0).equals(paramString1);
            if (!bool) {
              break label123;
            }
            try
            {
              q = a(q, i1);
            }
            catch (JSONException localJSONException4) {}
            localJSONException5.printStackTrace();
          }
          catch (JSONException localJSONException5)
          {
            localObject2 = localObject1;
          }
          localObject1 = localObject2;
          label123:
          i1 += 1;
        }
        if (localObject2 == null)
        {
          localObject2 = new JSONObject();
          localObject1 = localObject2;
        }
      }
    }
    try
    {
      ((JSONObject)localObject2).put(paramString1, 1);
      localObject1 = localObject2;
    }
    catch (JSONException localJSONException3)
    {
      for (;;) {}
    }
    Object localObject1 = localObject2;
    ((JSONObject)localObject2).put(paramString1, ((JSONObject)localObject2).getInt(paramString1) + 1);
    localObject1 = localObject2;
    q = a(q, localObject1);
    q = a(m, q);
    if (p != null) {}
    try
    {
      localObject1 = p.getJSONObject(0).getJSONObject(paramString2);
    }
    catch (JSONException localJSONException1)
    {
      for (;;) {}
    }
    localObject1 = null;
    Object localObject2 = localObject1;
    if (localObject1 == null)
    {
      localObject2 = new JSONObject();
      try
      {
        p.getJSONObject(0).put(paramString2, localObject2);
      }
      catch (JSONException paramString2)
      {
        paramString2.printStackTrace();
      }
    }
    try
    {
      paramString2 = ((JSONObject)localObject2).get(paramString1);
    }
    catch (Exception paramString2)
    {
      for (;;) {}
    }
    paramString2 = null;
    if ((paramString2 != null) || (n != 0)) {}
    try
    {
      paramString2 = new StringBuilder("[");
      paramString2.append(o);
      paramString2.append("]");
      ((JSONObject)localObject2).put(paramString1, new JSONArray(paramString2.toString()));
    }
    catch (JSONException paramString1)
    {
      for (;;) {}
    }
    ((JSONObject)localObject2).put(paramString1, 1);
    break label429;
    if ((paramString2 instanceof JSONArray))
    {
      localObject1 = (JSONArray)paramString2;
      ((JSONArray)localObject1).put(o);
      if (((JSONArray)localObject1).length() > n)
      {
        ((JSONObject)localObject2).remove(paramString1);
        i1 = ((JSONArray)paramString2).length();
      }
    }
    else
    {
      for (;;)
      {
        ((JSONObject)localObject2).put(paramString1, i1);
        break;
        i1 = ((Integer)paramString2).intValue();
        i1 += 1;
      }
    }
    label429:
    paramString1 = PreferenceManager.getDefaultSharedPreferences(a).edit();
    if (q != null) {
      paramString1.putString("history", q.toString());
    }
    if (p != null) {
      paramString1.putString("sessions", p.toString());
    }
    paramString1.commit();
    return;
    localObject1 = new StringBuilder("Null object somewhere: ");
    ((StringBuilder)localObject1).append(paramString2);
    ((StringBuilder)localObject1).append(paramString1);
    RMLog.d(((StringBuilder)localObject1).toString());
  }
  
  private static void a(JSONObject paramJSONObject, String paramString1, String paramString2)
  {
    if ((paramString2 != null) && (!paramString2.equals(""))) {
      paramJSONObject.put(paramString1, paramString2);
    }
  }
  
  private static String d()
  {
    try
    {
      Object localObject = Class.forName("com.revmob.internal.RevMobSocialInfo").getConstructor(new Class[0]).newInstance(new Object[0]);
      localObject = (String)Class.forName("com.revmob.internal.RevMobSocialInfo").getMethod("getFacebookToken", new Class[] { Context.class }).invoke(localObject, new Object[] { a.getApplicationContext() });
      return localObject;
    }
    catch (Exception localException) {}
    return "";
  }
  
  private static JSONObject e()
  {
    try
    {
      localJSONObject1 = new JSONObject();
      localJSONObject2 = new JSONObject();
      localObject = new JSONObject();
      ((JSONObject)localObject).put("width", h.widthPixels);
      ((JSONObject)localObject).put("height", h.heightPixels);
      ((JSONObject)localObject).put("scale", h.density);
      ((JSONObject)localObject).put("density_dpi", h.densityDpi);
      localJSONObject2.put("screen", localObject);
      a(localJSONObject2, "model", Build.MODEL);
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(Build.VERSION.SDK_INT);
      a(localJSONObject2, "api", ((StringBuilder)localObject).toString());
      a(localJSONObject2, "manufacturer", Build.MANUFACTURER);
      a(localJSONObject2, "os_version", Build.VERSION.RELEASE);
      localObject = ((WindowManager)a.getSystemService("window")).getDefaultDisplay();
      if ((Build.VERSION.RELEASE.startsWith("1.")) || (Build.VERSION.RELEASE.startsWith("2.0"))) {
        break label735;
      }
      if (!Build.VERSION.RELEASE.startsWith("2.1")) {
        break label729;
      }
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        JSONObject localJSONObject1;
        JSONObject localJSONObject2;
        Object localObject;
        double d1;
        double d2;
        double d3;
        continue;
        label729:
        int i1 = 1;
        continue;
        label735:
        i1 = 0;
        continue;
        label741:
        String str;
        if (i1 == 0) {
          str = "0";
        } else if (i1 == 1) {
          str = "90";
        } else if (i1 == 2) {
          str = "180";
        } else if (i1 == 3) {
          str = "270";
        } else {
          str = "-1";
        }
      }
    }
    if (i1 != 0)
    {
      i1 = ((Display)localObject).getRotation();
    }
    else
    {
      i1 = ((Display)localObject).getOrientation();
      break label741;
      a(localJSONObject2, "orientation", (String)localObject);
      a(localJSONObject2, "locale", j());
      if (c.c()) {
        localJSONObject2.put("location", g());
      }
      a(localJSONObject2, "android_id", "");
      a(localJSONObject2, "serial", "");
      a(localJSONObject2, "identifier_for_advertising", i);
      a(localJSONObject2, "limit_ad_tracking", j);
      localJSONObject1.put("device", localJSONObject2);
      localObject = new JSONObject();
      ((JSONObject)localObject).put("name", RevMobClient.a);
      ((JSONObject)localObject).put("version", RevMobClient.b);
      ((JSONObject)localObject).put("testing_mode", RevMobClient.a().b().getValue());
      localJSONObject1.put("sdk", localObject);
      localJSONObject1.put("app", i());
      localJSONObject1.put("social", f());
      if (c) {
        localJSONObject1.put("installedApps", k());
      }
      if (d) {
        localJSONObject1.put("runningApps", l());
      }
      if ((e) && (RevMobClient.e != 0L) && (RevMobClient.f != 0L) && (RevMobClient.g != 0L) && (RevMobClient.h != 0L))
      {
        localObject = new JSONObject();
        d1 = RevMobClient.f - RevMobClient.e;
        d2 = RevMobClient.g - RevMobClient.f;
        d3 = RevMobClient.h - RevMobClient.g;
        ((JSONObject)localObject).put("fetchTime", d1 / 1000.0D);
        ((JSONObject)localObject).put("sdkTime", d2 / 1000.0D);
        ((JSONObject)localObject).put("creativeTime", d3 / 1000.0D);
        localJSONObject1.put("time", localObject);
      }
      if (RevMobBanner.isBannerImpression)
      {
        localJSONObject1.put("bannerImpressions", h());
        RevMobBanner.setBannerImpression(false);
      }
      if (f)
      {
        if (q != null) {
          localJSONObject1.put("campaigns", q);
        }
        if (p != null) {
          localJSONObject1.put("sessions", p);
        }
      }
      if (RevMobClient.a().b() != RevMobTestingMode.DISABLED)
      {
        localObject = new JSONObject();
        ((JSONObject)localObject).put("response", RevMobClient.a().b().getValue());
        localJSONObject1.put("testing", localObject);
      }
      c = false;
      d = false;
      e = false;
      f = false;
      return localJSONObject1;
      return null;
    }
  }
  
  private static JSONObject f()
  {
    JSONObject localJSONObject = new JSONObject();
    h.a(localJSONObject);
    try
    {
      Class.forName("com.facebook.a");
      localJSONObject.put("facebook_token", d());
      return localJSONObject;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;) {}
    }
    RMLog.d("Facebook SDK not found.");
    return localJSONObject;
  }
  
  private static JSONObject g()
  {
    localJSONObject = new JSONObject();
    try
    {
      Object localObject = (LocationManager)a.getSystemService("location");
      if ((localObject != null) && ((a.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) || (a.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0)))
      {
        Location localLocation = ((LocationManager)localObject).getLastKnownLocation("gps");
        localObject = ((LocationManager)localObject).getLastKnownLocation("network");
        if ((localLocation != null) && (localObject != null))
        {
          if (localLocation.getTime() > ((Location)localObject).getTime())
          {
            localJSONObject.put("latitude", localLocation.getLatitude());
            localJSONObject.put("longitude", localLocation.getLongitude());
            f1 = localLocation.getAccuracy();
            localJSONObject.put("accuracy", f1);
            return localJSONObject;
          }
          localJSONObject.put("latitude", ((Location)localObject).getLatitude());
          localJSONObject.put("longitude", ((Location)localObject).getLongitude());
        }
        for (float f1 = ((Location)localObject).getAccuracy();; f1 = ((Location)localObject).getAccuracy())
        {
          localJSONObject.put("accuracy", f1);
          return localJSONObject;
          if (localLocation != null)
          {
            localJSONObject.put("latitude", localLocation.getLatitude());
            localJSONObject.put("longitude", localLocation.getLongitude());
            f1 = localLocation.getAccuracy();
            break;
          }
          if (localObject == null) {
            return localJSONObject;
          }
          localJSONObject.put("latitude", ((Location)localObject).getLatitude());
          localJSONObject.put("longitude", ((Location)localObject).getLongitude());
        }
      }
      return localJSONObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private static JSONObject h()
  {
    JSONObject localJSONObject1 = new JSONObject();
    localJSONObject1.put("bannerCount", RevMobBanner.bannerCount);
    JSONObject localJSONObject2 = new JSONObject();
    int i2;
    for (int i1 = 0; i1 < RevMobBanner.usedCampaigns.size(); i1 = i2)
    {
      i2 = i1 + 1;
      localJSONObject2.put(String.valueOf(i2), RevMobBanner.usedCampaigns.get(i1));
    }
    localJSONObject1.put("campaigns", localJSONObject2);
    return localJSONObject1;
  }
  
  private static JSONObject i()
  {
    JSONObject localJSONObject = new JSONObject();
    a(localJSONObject, "bundle_identifier", a.getPackageName());
    try
    {
      localObject = a.getResources();
      a(localJSONObject, "app_name", ((Resources)localObject).getText(((Resources)localObject).getIdentifier("app_name", "string", a.getPackageName())).toString());
    }
    catch (Exception localException1)
    {
      try
      {
        for (;;)
        {
          Object localObject = a.getPackageManager().getPackageInfo(a.getPackageName(), 0);
          a(localJSONObject, "app_version", String.valueOf(((PackageInfo)localObject).versionCode));
          a(localJSONObject, "app_version_name", ((PackageInfo)localObject).versionName);
          if ((new g(a).b() ^ true)) {
            a(localJSONObject, "install_not_registered", "true");
          }
          return localJSONObject;
          localException1 = localException1;
        }
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
    }
  }
  
  private static String j()
  {
    return Locale.getDefault().toString().replace('_', '-');
  }
  
  private static JSONArray k()
  {
    JSONArray localJSONArray = new JSONArray();
    c.b(true);
    if (c.d())
    {
      PackageManager localPackageManager = a.getPackageManager();
      Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if ((localApplicationInfo.flags & 0x1) != 1)
        {
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("packageName", localApplicationInfo.packageName);
          localJSONObject.put("name", localApplicationInfo.loadLabel(localPackageManager));
          localJSONArray.put(localJSONObject);
        }
      }
      return localJSONArray;
    }
    return null;
  }
  
  private static JSONArray l()
  {
    List localList = ((ActivityManager)a.getSystemService("activity")).getRunningAppProcesses();
    JSONArray localJSONArray = new JSONArray();
    int i1 = 0;
    while (i1 < localList.size())
    {
      localJSONArray.put(((ActivityManager.RunningAppProcessInfo)localList.get(i1)).processName);
      i1 += 1;
    }
    return localJSONArray;
  }
}
