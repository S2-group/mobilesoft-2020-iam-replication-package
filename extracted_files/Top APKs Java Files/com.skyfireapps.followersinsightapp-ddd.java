import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ddd
{
  public static Context a;
  public static boolean b = false;
  public static boolean c = false;
  public static ddf d = ddf.a;
  public static dax e;
  public static Thread f;
  private static DisplayMetrics g = new DisplayMetrics();
  private static String h;
  private static String i;
  private static String j;
  private static String[] k;
  private static ddn l;
  private static JSONObject m = new JSONObject();
  private static boolean n = false;
  private static boolean o = false;
  private static boolean p = false;
  private static JSONObject q = new JSONObject();
  
  private static String A()
  {
    return i;
  }
  
  private static String B()
  {
    return h;
  }
  
  public static String a()
  {
    return c().toString();
  }
  
  public static String a(Activity paramActivity)
  {
    a = paramActivity;
    ((Activity)a).getWindowManager().getDefaultDisplay().getMetrics(g);
    return a();
  }
  
  public static void a(String paramString, ddn paramDdn, dax paramDax, Activity paramActivity)
  {
    try
    {
      j = paramString;
      l = paramDdn;
      e = paramDax;
      a = paramActivity;
      if (f == null)
      {
        f = new Thread(new dde());
        f.start();
      }
      return;
    }
    catch (Exception paramString)
    {
      ded.d("Error loading advertising info: " + paramString.getMessage());
    }
  }
  
  private static void a(JSONObject paramJSONObject, String paramString1, String paramString2)
  {
    if ((paramString2 != null) && (!paramString2.equals(""))) {
      paramJSONObject.put(paramString1, paramString2);
    }
  }
  
  private static void a(JSONObject paramJSONObject, String paramString, JSONArray paramJSONArray)
  {
    if ((paramJSONArray != null) && (!paramJSONArray.equals(""))) {
      paramJSONObject.put(paramString, paramJSONArray);
    }
  }
  
  public static JSONArray b()
  {
    try
    {
      String str = a.getApplicationContext().getPackageName();
      k = a.getPackageManager().getPackageInfo(str, 4096).requestedPermissions;
      return new JSONArray(Arrays.asList(k));
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return null;
  }
  
  public static JSONObject c()
  {
    try
    {
      JSONObject localJSONObject1 = new JSONObject();
      localJSONObject1.put("device", f());
      localJSONObject1.put("sdk", e());
      localJSONObject1.put("app", w());
      localJSONObject1.put("social", r());
      if (b)
      {
        b = false;
        localJSONObject1.put("installedApps", y());
      }
      if ((d == ddf.c) && (c))
      {
        c = false;
        localJSONObject1.put("runningApps", z());
      }
      if ((ddl.e != 0L) && (ddl.f != 0L) && (ddl.g != 0L) && (ddl.h != 0L)) {
        localJSONObject1.put("time", u());
      }
      if (dbb.d)
      {
        localJSONObject1.put("bannerImpressions", v());
        dbb.setBannerImpression(false);
      }
      if (ddl.a().b() != daz.c)
      {
        JSONObject localJSONObject2 = new JSONObject();
        localJSONObject2.put("response", ddl.a().b().a());
        localJSONObject1.put("testing", localJSONObject2);
      }
      return localJSONObject1;
    }
    catch (JSONException localJSONException) {}
    return null;
  }
  
  public static JSONObject d()
  {
    try
    {
      JSONObject localJSONObject1 = new JSONObject();
      JSONObject localJSONObject2 = new JSONObject();
      a(localJSONObject1, "android_id", i());
      a(localJSONObject1, "serial", x());
      a(localJSONObject1, "identifier_for_advertising", B());
      localJSONObject2.put("identifiers", localJSONObject1);
      return localJSONObject2;
    }
    catch (JSONException localJSONException) {}
    return null;
  }
  
  public static JSONObject e()
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("name", ddl.a);
    localJSONObject.put("version", ddl.b);
    localJSONObject.put("testing_mode", ddl.a().b().a());
    return localJSONObject;
  }
  
  public static JSONObject f()
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("screen", g());
    a(localJSONObject, "model", j());
    a(localJSONObject, "api", "" + m());
    a(localJSONObject, "manufacturer", k());
    a(localJSONObject, "os_version", l());
    a(localJSONObject, "orientation", s());
    a(localJSONObject, "locale", n());
    a(localJSONObject, "ua", ddu.a());
    if (ddu.c()) {
      localJSONObject.put("location", h());
    }
    a(localJSONObject, "android_id", i());
    a(localJSONObject, "serial", x());
    a(localJSONObject, "identifier_for_advertising", B());
    a(localJSONObject, "limit_ad_tracking", A());
    return localJSONObject;
  }
  
  public static JSONObject g()
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("width", g.widthPixels);
    localJSONObject.put("height", g.heightPixels);
    localJSONObject.put("scale", g.density);
    localJSONObject.put("density_dpi", g.densityDpi);
    return localJSONObject;
  }
  
  public static JSONObject h()
  {
    JSONObject localJSONObject = new JSONObject();
    Object localObject;
    try
    {
      localObject = (LocationManager)a.getSystemService("location");
      if ((localObject == null) || ((a.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0) && (a.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0))) {
        return localJSONObject;
      }
      Location localLocation = ((LocationManager)localObject).getLastKnownLocation("gps");
      localObject = ((LocationManager)localObject).getLastKnownLocation("network");
      if ((localLocation != null) && (localObject != null))
      {
        if (localLocation.getTime() > ((Location)localObject).getTime())
        {
          localJSONObject.put("latitude", localLocation.getLatitude());
          localJSONObject.put("longitude", localLocation.getLongitude());
          localJSONObject.put("accuracy", localLocation.getAccuracy());
          return localJSONObject;
        }
        localJSONObject.put("latitude", ((Location)localObject).getLatitude());
        localJSONObject.put("longitude", ((Location)localObject).getLongitude());
        localJSONObject.put("accuracy", ((Location)localObject).getAccuracy());
        return localJSONObject;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return localJSONObject;
    }
    if (localException != null)
    {
      localJSONObject.put("latitude", localException.getLatitude());
      localJSONObject.put("longitude", localException.getLongitude());
      localJSONObject.put("accuracy", localException.getAccuracy());
      return localJSONObject;
    }
    if (localObject != null)
    {
      localJSONObject.put("latitude", ((Location)localObject).getLatitude());
      localJSONObject.put("longitude", ((Location)localObject).getLongitude());
      localJSONObject.put("accuracy", ((Location)localObject).getAccuracy());
    }
    return localJSONObject;
  }
  
  public static String i()
  {
    return "";
  }
  
  public static String j()
  {
    return Build.MODEL;
  }
  
  public static String k()
  {
    return Build.MANUFACTURER;
  }
  
  public static String l()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static int m()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static String n()
  {
    return Locale.getDefault().toString().replace('_', '-');
  }
  
  private static String q()
  {
    try
    {
      Object localObject = Class.forName("der").getConstructor(new Class[0]).newInstance(new Object[0]);
      localObject = (String)Class.forName("der").getMethod("getFacebookToken", new Class[] { Context.class }).invoke(localObject, new Object[] { a.getApplicationContext() });
      return localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
  
  private static JSONObject r()
  {
    JSONObject localJSONObject = new JSONObject();
    ddi.a(localJSONObject);
    try
    {
      Class.forName("com.facebook.Session");
      ded.b("Has Facebook SDK!");
      localJSONObject.put("facebook_token", q());
      return localJSONObject;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      ded.b("Facebook SDK not found.");
    }
    return localJSONObject;
  }
  
  @TargetApi(8)
  private static String s()
  {
    Display localDisplay = ((WindowManager)a.getSystemService("window")).getDefaultDisplay();
    if (t()) {}
    for (int i1 = localDisplay.getRotation(); i1 == 0; i1 = localDisplay.getOrientation()) {
      return "0";
    }
    if (i1 == 1) {
      return "90";
    }
    if (i1 == 2) {
      return "180";
    }
    if (i1 == 3) {
      return "270";
    }
    return "-1";
  }
  
  private static boolean t()
  {
    return (!Build.VERSION.RELEASE.startsWith("1.")) && (!Build.VERSION.RELEASE.startsWith("2.0")) && (!Build.VERSION.RELEASE.startsWith("2.1"));
  }
  
  private static JSONObject u()
  {
    JSONObject localJSONObject = new JSONObject();
    double d1 = ddl.f - ddl.e;
    double d2 = ddl.g - ddl.f;
    double d3 = ddl.h - ddl.g;
    localJSONObject.put("fetchTime", d1 / 1000.0D);
    localJSONObject.put("sdkTime", d2 / 1000.0D);
    localJSONObject.put("creativeTime", d3 / 1000.0D);
    return localJSONObject;
  }
  
  private static JSONObject v()
  {
    JSONObject localJSONObject1 = new JSONObject();
    localJSONObject1.put("bannerCount", dbb.b);
    JSONObject localJSONObject2 = new JSONObject();
    int i1 = 0;
    while (i1 < dbb.c.size())
    {
      localJSONObject2.put(String.valueOf(i1 + 1), dbb.c.get(i1));
      i1 += 1;
    }
    localJSONObject1.put("campaigns", localJSONObject2);
    return localJSONObject1;
  }
  
  private static JSONObject w()
  {
    int i1 = 0;
    JSONObject localJSONObject = new JSONObject();
    a(localJSONObject, "bundle_identifier", a.getPackageName());
    try
    {
      Object localObject = a.getResources();
      a(localJSONObject, "app_name", ((Resources)localObject).getText(((Resources)localObject).getIdentifier("app_name", "string", a.getPackageName())).toString());
      try
      {
        localObject = a.getPackageManager().getPackageInfo(a.getPackageName(), 0);
        int i2 = ((PackageInfo)localObject).versionCode;
        a(localJSONObject, "app_version", "" + i2);
        if (b() != null) {
          a(localJSONObject, "permissions", b());
        }
        a(localJSONObject, "app_version_name", ((PackageInfo)localObject).versionName);
      }
      catch (Exception localException1)
      {
        for (;;) {}
      }
      if (!new ddh(a).b()) {
        i1 = 1;
      }
      if (i1 != 0) {
        a(localJSONObject, "install_not_registered", "true");
      }
      return localJSONObject;
    }
    catch (Exception localException2)
    {
      for (;;) {}
    }
  }
  
  private static String x()
  {
    return "";
  }
  
  private static JSONArray y()
  {
    JSONArray localJSONArray = new JSONArray();
    ddu.c(true);
    if (ddu.d())
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
  
  private static JSONObject z()
  {
    Object localObject = PreferenceManager.getDefaultSharedPreferences(a.getApplicationContext());
    new JSONArray();
    localObject = new JSONArray(((SharedPreferences)localObject).getString("runningApps", new JSONArray().toString()));
    return (JSONObject)((JSONArray)localObject).get(((JSONArray)localObject).length() - 1);
  }
}
