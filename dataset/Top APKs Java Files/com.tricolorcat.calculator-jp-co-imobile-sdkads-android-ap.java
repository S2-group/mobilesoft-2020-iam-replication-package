package jp.co.imobile.sdkads.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

final class ap
  implements LocationListener
{
  private static ap c = new ap();
  LocationManager a = null;
  List b;
  private String d = "android";
  private String e = "";
  private String f = "";
  private String g = "";
  private String h = "";
  private String i = "wifi";
  private String j = "";
  private String k = "";
  private String l = "";
  private String m = "";
  private Boolean n = Boolean.valueOf(true);
  private float o = 0.0F;
  private int p = 0;
  private int q = 0;
  private double r = 0.0D;
  private double s = 0.0D;
  private boolean t = false;
  
  private ap() {}
  
  static int a(int paramInt)
  {
    return (int)(paramInt * c.o + 0.5F);
  }
  
  static int a(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      int i1 = paramJSONObject.getInt(paramString);
      return i1;
    }
    catch (JSONException paramJSONObject)
    {
      new StringBuilder("getJsonIntValueFromKey JSON parce error. value:").append(paramString);
      c.b("SDK API Message", "PARSE INT");
      throw new d(FailNotificationReason.c);
    }
  }
  
  static String a(Activity paramActivity, AdOrientation paramAdOrientation, Rect paramRect, boolean paramBoolean1, boolean paramBoolean2)
  {
    JSONObject localJSONObject1 = f();
    for (;;)
    {
      try
      {
        JSONObject localJSONObject2 = localJSONObject1.getJSONObject("result");
        switch (h()[paramAdOrientation.ordinal()])
        {
        case 2: 
          paramAdOrientation = a(paramActivity);
          localJSONObject2.put("rotation", paramAdOrientation);
          localJSONObject2.put("top", paramRect.top);
          localJSONObject2.put("left", paramRect.left);
          localJSONObject2.put("width", paramRect.right);
          localJSONObject2.put("height", paramRect.bottom);
          localJSONObject2.put("contentHeight", ((ViewGroup)paramActivity.getWindow().getDecorView().findViewById(16908290)).getHeight());
          localJSONObject2.put("sizeAdjust", paramBoolean1);
          localJSONObject2.put("positionAdjust", paramBoolean2);
          localJSONObject1.put("status", "succeed");
          return localJSONObject1.toString();
        }
      }
      catch (JSONException paramActivity)
      {
        new StringBuilder("getDisplayOrientationInfoToJSonString:").append(paramActivity.getMessage());
        c.b("SDK API Message", "DM");
        throw new d(FailNotificationReason.c);
      }
      paramAdOrientation = "p";
      continue;
      paramAdOrientation = "l";
    }
  }
  
  private static String a(Context paramContext)
  {
    String str = "";
    if (paramContext.getResources().getConfiguration().orientation == 1) {
      str = "p";
    }
    while (paramContext.getResources().getConfiguration().orientation != 2) {
      return str;
    }
    return "l";
  }
  
  static final void a(Activity paramActivity, String paramString)
  {
    Intent localIntent;
    if (a(paramString))
    {
      localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setData(Uri.parse(paramString));
      if (paramActivity == null)
      {
        localIntent.setFlags(268435456);
        ImobileSdkAd.b().startActivity(localIntent);
      }
    }
    else
    {
      return;
    }
    paramActivity.startActivity(localIntent);
  }
  
  static final void a(Context paramContext, ac paramAc, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    if (paramContext != null)
    {
      paramContext = new AlertDialog.Builder(paramContext);
      paramContext.setTitle(paramString1);
      paramContext.setMessage(paramString2);
      paramString1 = new at(paramAc, paramString4);
      paramAc = new au(paramAc, paramString6);
      if (Build.VERSION.SDK_INT >= 14) {
        break label145;
      }
      paramContext.setNegativeButton(paramString3, paramString1);
      paramContext.setCancelable(true);
      if ((paramString5 != null) && (!paramString5.equals(""))) {
        paramContext.setPositiveButton(paramString5, paramAc);
      }
    }
    for (;;)
    {
      paramContext = paramContext.create();
      paramContext.setCanceledOnTouchOutside(false);
      paramContext.setOnKeyListener(new av());
      paramContext.show();
      ((TextView)paramContext.findViewById(paramContext.getContext().getResources().getIdentifier("android:id/alertTitle", null, null))).setGravity(17);
      return;
      label145:
      paramContext.setPositiveButton(paramString3, paramString1);
      if ((paramString5 != null) && (!paramString5.equals("")))
      {
        paramContext.setNegativeButton(paramString5, paramAc);
        paramContext.setCancelable(true);
      }
    }
  }
  
  static final void a(String paramString1, String paramString2)
  {
    Object localObject = ImobileSdkAd.b();
    if (localObject != null)
    {
      localObject = PreferenceManager.getDefaultSharedPreferences((Context)localObject);
      new StringBuilder("writePreference. key : ").append(paramString1).append(" value : ").append(paramString2);
      c.a(null);
      ((SharedPreferences)localObject).edit().putString(paramString1, paramString2).commit();
    }
  }
  
  static final void a(String paramString1, String paramString2, ac paramAc)
  {
    paramString1 = new ar(paramString1);
    paramString1 = Executors.newCachedThreadPool().submit(paramString1);
    Executors.newCachedThreadPool().submit(new as(paramString1, paramString2, paramAc));
  }
  
  static final boolean a(String paramString)
  {
    return ImobileSdkAd.b().getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(paramString + "://")), 65536).size() > 0;
  }
  
  static final boolean a(JSONObject paramJSONObject)
  {
    try
    {
      boolean bool = ai.a(paramJSONObject, null).a().booleanValue();
      return bool;
    }
    catch (d paramJSONObject)
    {
      c.a(paramJSONObject);
    }
    return false;
  }
  
  static final int b(Activity paramActivity)
  {
    int i3 = 9;
    int i4 = 8;
    int i5 = 1;
    int i1;
    int i2;
    int i6;
    if (a(paramActivity).equals("p"))
    {
      i1 = 1;
      i2 = 1;
      i6 = ((WindowManager)ImobileSdkAd.b().getSystemService("window")).getDefaultDisplay().getRotation();
      if (Build.VERSION.SDK_INT <= 8) {}
    }
    else
    {
      switch (i6)
      {
      default: 
      case 0: 
      case 1: 
        do
        {
          do
          {
            return 0;
          } while (!a(paramActivity).equals("l"));
          i1 = 0;
          i2 = 0;
          break;
          if (i1 != 0) {}
          for (i1 = i5;; i1 = 0) {
            return i1;
          }
        } while (i1 == 0);
        return 9;
      case 2: 
        if (i1 != 0) {}
        for (i1 = i3;; i1 = 8) {
          return i1;
        }
      }
      if (i1 == 0) {}
      for (i1 = i4;; i1 = 1) {
        return i1;
      }
    }
    return i2;
  }
  
  static String b(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString(paramString);
      return paramJSONObject;
    }
    catch (JSONException paramJSONObject)
    {
      new StringBuilder("getJsonStringValueFromKey JSON parce error. value:").append(paramString);
      c.b("SDK API Message", "PARSE STRING");
      throw new d(FailNotificationReason.c);
    }
  }
  
  static ap b()
  {
    return c;
  }
  
  static final void b(Activity paramActivity, String paramString)
  {
    Intent localIntent = ImobileSdkAd.b().getPackageManager().getLaunchIntentForPackage(paramString);
    try
    {
      paramActivity.startActivity(localIntent);
      return;
    }
    catch (Exception paramActivity)
    {
      new StringBuilder("Applicaiotn Start faild. applicaiotnID:").append(paramString).append(" message:").append(paramActivity.getMessage());
      c.b("SDK API Message", "Application not installed.");
    }
  }
  
  static String c()
  {
    if (ImobileSdkAd.b().checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == -1) {
      return "mobile";
    }
    Object localObject = (ConnectivityManager)ImobileSdkAd.b().getSystemService("connectivity");
    if (localObject == null) {
      return "mobile";
    }
    localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
    if ((localObject != null) && (((NetworkInfo)localObject).isConnectedOrConnecting())) {
      return ((NetworkInfo)localObject).getTypeName().toLowerCase(Locale.getDefault());
    }
    return "";
  }
  
  static final String c(String paramString)
  {
    Object localObject = ImobileSdkAd.b();
    if (localObject != null)
    {
      localObject = PreferenceManager.getDefaultSharedPreferences((Context)localObject).getString(paramString, "");
      new StringBuilder("readPreference. key : ").append(paramString).append(" value : ").append((String)localObject);
      c.a(null);
      return localObject;
    }
    return "";
  }
  
  static final void d(String paramString)
  {
    if (ImobileSdkAd.b() != null) {
      PreferenceManager.getDefaultSharedPreferences(ImobileSdkAd.b()).edit().remove(paramString).commit();
    }
  }
  
  static JSONObject e(String paramString)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      new StringBuilder("getJsonObjectFromJsonString JSON parce error. value:").append(paramString);
      c.b("SDK API Message", "PARSE");
      throw new d(FailNotificationReason.c);
    }
  }
  
  static JSONObject f()
  {
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      localJSONObject1.put("status", "faild");
      JSONObject localJSONObject2 = new JSONObject();
      localJSONObject2.put("code", "");
      localJSONObject2.put("message", "");
      localJSONObject1.put("error", localJSONObject2);
      localJSONObject1.put("result", new JSONObject());
      return localJSONObject1;
    }
    catch (JSONException localJSONException)
    {
      new StringBuilder("getJsonBaseForHtml JSON parce error. value:").append(localJSONException.getMessage());
      c.b("SDK API Message", "BASE PARSE");
      throw new d(FailNotificationReason.c);
    }
  }
  
  final String a()
  {
    return this.j;
  }
  
  final void a(Activity paramActivity)
  {
    Object localObject;
    DisplayMetrics localDisplayMetrics;
    if (!this.t)
    {
      localObject = ImobileSdkAd.b();
      this.e = ((Context)localObject).getPackageName();
      this.f = "V2.0.9";
      this.g = Locale.getDefault().getLanguage();
      this.h = Build.VERSION.RELEASE;
      this.i = c();
      this.j = Build.BRAND;
      this.k = Build.DEVICE;
      new Thread(new aq(this, (Context)localObject)).start();
      localObject = new DisplayMetrics();
      ((WindowManager)paramActivity.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
      this.o = ((DisplayMetrics)localObject).density;
      localObject = new Point(0, 0);
      localDisplayMetrics = new DisplayMetrics();
      ((WindowManager)paramActivity.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
      if (!a(paramActivity).equals("l")) {
        break label254;
      }
      ((Point)localObject).x = localDisplayMetrics.heightPixels;
      ((Point)localObject).y = localDisplayMetrics.widthPixels;
      this.p = ((Point)localObject).x;
      this.q = ((Point)localObject).y;
      if ((paramActivity.getWindow().getAttributes().flags & 0x400) != 0) {
        break label275;
      }
    }
    label254:
    label275:
    for (boolean bool = true;; bool = false)
    {
      this.n = Boolean.valueOf(bool);
      d();
      this.t = true;
      c.b("SDK API Message", "Sdk api init complete.");
      return;
      ((Point)localObject).x = localDisplayMetrics.widthPixels;
      ((Point)localObject).y = localDisplayMetrics.heightPixels;
      break;
    }
  }
  
  final void a(Uri.Builder paramBuilder)
  {
    paramBuilder.appendQueryParameter("spt", this.d);
    paramBuilder.appendQueryParameter("appid", this.e);
    paramBuilder.appendQueryParameter("sdkv", this.f);
    paramBuilder.appendQueryParameter("lang", this.g);
    paramBuilder.appendQueryParameter("os", this.h);
    paramBuilder.appendQueryParameter("nk", this.i);
    paramBuilder.appendQueryParameter("dvbrand", this.j);
    paramBuilder.appendQueryParameter("dvname", this.k);
    if ((this.m != null) && (!this.m.equals(""))) {
      paramBuilder.appendQueryParameter("gaid", this.m);
    }
    paramBuilder.appendQueryParameter("dpr", Float.toString(this.o));
    paramBuilder.appendQueryParameter("dpw", Integer.toString(this.p));
    paramBuilder.appendQueryParameter("dph", Integer.toString(this.q));
    if ((this.r > 0.0D) && (this.s > 0.0D))
    {
      paramBuilder.appendQueryParameter("lat", Double.toString(this.r));
      paramBuilder.appendQueryParameter("lng", Double.toString(this.s));
    }
  }
  
  final boolean b(String paramString)
  {
    Iterator localIterator;
    if (this.b == null)
    {
      this.b = new ArrayList();
      localIterator = ImobileSdkAd.b().getPackageManager().getInstalledApplications(0).iterator();
    }
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        if (this.b.indexOf(paramString) < 0) {
          break;
        }
        return true;
      }
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      this.b.add(localApplicationInfo.packageName);
    }
    return false;
  }
  
  final void d()
  {
    if (this.a != null) {
      return;
    }
    this.a = ((LocationManager)ImobileSdkAd.b().getSystemService("location"));
    String str;
    if (ImobileSdkAd.b().checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == -1)
    {
      c.a(null);
      str = "";
    }
    for (;;)
    {
      if (ImobileSdkAd.b().checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == -1) {
        c.a(null);
      }
      label136:
      for (;;)
      {
        if (str.equals(""))
        {
          c.a(null);
          return;
          if (!this.a.isProviderEnabled("gps")) {
            break label139;
          }
          str = "gps";
          break;
          if ((!this.a.isProviderEnabled("network")) || (!str.equals(""))) {
            break label136;
          }
          str = "network";
          continue;
        }
        this.a.requestLocationUpdates(str, 0L, 0.0F, this);
        return;
      }
      label139:
      str = "";
    }
  }
  
  final void e()
  {
    if (this.a != null)
    {
      this.a.removeUpdates(this);
      this.r = 0.0D;
      this.s = 0.0D;
      this.a = null;
    }
  }
  
  final String g()
  {
    JSONObject localJSONObject1 = f();
    for (;;)
    {
      try
      {
        JSONObject localJSONObject2 = localJSONObject1.getJSONObject("result");
        localJSONObject2.put("spt", this.d);
        localJSONObject2.put("appid", this.e);
        localJSONObject2.put("sdkv", this.f);
        localJSONObject2.put("lang", this.g);
        localJSONObject2.put("os", this.h);
        localJSONObject2.put("nk", this.i);
        localJSONObject2.put("dvbrand", this.j);
        localJSONObject2.put("dvname", this.k);
        switch (h()[ImobileSdkAd.e().ordinal()])
        {
        case 2: 
          str = a(ImobileSdkAd.b());
          localJSONObject2.put("rotation", str);
          localJSONObject2.put("deviceid", this.l);
          localJSONObject2.put("advertisingid", this.m);
          localJSONObject2.put("statusbar", this.n);
          localJSONObject2.put("dpr", Float.toString(this.o));
          localJSONObject2.put("dpw", Integer.toString(this.p));
          localJSONObject2.put("dph", Integer.toString(this.q));
          localJSONObject2.put("lat", Double.toString(this.r));
          localJSONObject2.put("lng", Double.toString(this.s));
          localJSONObject1.put("status", "succeed");
          return localJSONObject1.toString();
        }
      }
      catch (JSONException localJSONException)
      {
        String str;
        new StringBuilder("getDeviceInfoJsonForHtml JSON parce error. value:").append(localJSONException.getMessage());
        c.b("SDK API Message", "DEVICE PARSE");
        throw new d(FailNotificationReason.c);
      }
      str = "p";
      continue;
      str = "l";
    }
  }
  
  public final void onLocationChanged(Location paramLocation)
  {
    this.r = paramLocation.getLatitude();
    this.s = paramLocation.getLongitude();
    new StringBuilder("Location get lat:").append(paramLocation.getLatitude()).append(" lng:").append(paramLocation.getLongitude());
    c.a(null);
  }
  
  public final void onProviderDisabled(String paramString) {}
  
  public final void onProviderEnabled(String paramString) {}
  
  public final void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {}
}
