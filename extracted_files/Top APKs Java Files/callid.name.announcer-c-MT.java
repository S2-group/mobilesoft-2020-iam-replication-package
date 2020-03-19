package c;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MT
{
  public boolean a = false;
  private TelephonyManager b;
  private Context c;
  private OMI d;
  
  public MT(Context paramContext)
  {
    this.c = paramContext;
    this.d = new OMI(paramContext);
    this.b = ((TelephonyManager)this.c.getSystemService("phone"));
  }
  
  private String a(String paramString)
  {
    try
    {
      long l = this.c.getPackageManager().getPackageInfo(paramString, 0).firstInstallTime;
      paramString = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss").format(new Date(l));
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      LO.a(this.c, MT.class, T7U.h, paramString, this.a);
    }
    return "";
  }
  
  private String k()
  {
    try
    {
      Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
      if (!this.d.a(D4T.d).equals("")) {
        return this.d.a(D4T.d);
      }
      if (Looper.getMainLooper().equals(Looper.myLooper())) {
        return "";
      }
      Object localObject1 = null;
      try
      {
        if (this.c.getApplicationContext() != null) {
          localObject1 = AdvertisingIdClient.getAdvertisingIdInfo(this.c.getApplicationContext());
        }
        Object localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = AdvertisingIdClient.getAdvertisingIdInfo(this.c);
        }
        if (localObject2 != null)
        {
          localObject1 = ((AdvertisingIdClient.Info)localObject2).getId();
          this.d.a(D4T.d, (String)localObject1);
        }
      }
      catch (NullPointerException localNullPointerException)
      {
        LO.a(this.c, MT.class, T7U.f, localNullPointerException, this.a);
      }
      catch (IOException localIOException)
      {
        LO.a(this.c, MT.class, T7U.e, localIOException, this.a);
      }
      catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
      {
        LO.a(this.c, MT.class, T7U.d, localGooglePlayServicesRepairableException, this.a);
      }
      catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
      {
        LO.a(this.c, MT.class, T7U.c, localGooglePlayServicesNotAvailableException, this.a);
      }
      return this.d.a(D4T.d, "");
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;) {}
    }
    LO.a(this.c, MT.class, T7U.o, "The class 'com.google.android.gms.ads.identifier.AdvertisingIdClient' not found make sure you include it", this.a, true);
    return "";
  }
  
  @TargetApi(17)
  private String l()
  {
    try
    {
      String str = WebSettings.getDefaultUserAgent(this.c);
      return str;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return "";
  }
  
  private String m()
  {
    Object localObject1;
    if (Build.VERSION.SDK_INT >= 17)
    {
      localObject1 = l();
      if (!((String)localObject1).equals("")) {
        return localObject1;
      }
    }
    try
    {
      localObject1 = WebSettings.class.getDeclaredConstructor(new Class[] { Context.class, WebView.class });
      ((Constructor)localObject1).setAccessible(true);
      try
      {
        String str = ((WebSettings)((Constructor)localObject1).newInstance(new Object[] { this.c, null })).getUserAgentString();
        return str;
      }
      finally
      {
        ((Constructor)localObject1).setAccessible(false);
      }
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return System.getProperty("http.agent");
  }
  
  private String n()
  {
    if (this.d.a("check_user_app_bundles", "0").equals("0")) {
      return "";
    }
    try
    {
      Object localObject2 = this.c.getPackageManager();
      Iterator localIterator = ((PackageManager)localObject2).getInstalledApplications(128).iterator();
      Object localObject1 = "{\"apps\":[";
      int i = 0;
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (localApplicationInfo.sourceDir.startsWith("/data/app/"))
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append((String)localObject1);
          localStringBuilder.append("{\"title\":\"");
          localStringBuilder.append(localApplicationInfo.loadLabel((PackageManager)localObject2));
          localStringBuilder.append("\",");
          localObject1 = localStringBuilder.toString();
          localStringBuilder = new StringBuilder();
          localStringBuilder.append((String)localObject1);
          localStringBuilder.append("\"pakeagename\":\"");
          localStringBuilder.append(localApplicationInfo.packageName);
          localStringBuilder.append("\"}, ");
          localObject1 = localStringBuilder.toString();
          i += 1;
        }
      }
      localObject2 = localObject1;
      if (i > 0) {
        localObject2 = ((String)localObject1).substring(0, ((String)localObject1).length() - 2);
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append((String)localObject2);
      ((StringBuilder)localObject1).append("]}");
      localObject1 = ((StringBuilder)localObject1).toString();
      return localObject1;
    }
    catch (Exception localException)
    {
      LO.a(this.c, MT.class, T7U.k, localException, this.a);
    }
    return "";
  }
  
  private Location o()
  {
    try
    {
      if ((this.c.getApplicationContext().checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) && (this.c.getApplicationContext().checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0))
      {
        Object localObject = (LocationManager)this.c.getSystemService("location");
        Location localLocation = ((LocationManager)localObject).getLastKnownLocation("gps");
        localObject = ((LocationManager)localObject).getLastKnownLocation("network");
        if (localLocation != null) {
          return localLocation;
        }
        return localObject;
      }
      return null;
    }
    catch (Exception localException) {}
    return null;
  }
  
  public String a()
  {
    return k();
  }
  
  public Map<ZQK, String> a(Map<ZQK, String> paramMap)
  {
    String str = n();
    if (!str.equals("")) {
      paramMap.put(ZQK.t, str);
    }
    str = e();
    if (!str.equals("")) {
      paramMap.put(ZQK.ae, str);
    }
    return paramMap;
  }
  
  public Map<Object, String> a(Map<Object, String> paramMap)
  {
    Object localObject = a();
    if (!((String)localObject).equals("")) {
      paramMap.put(ZQK.j, localObject);
    }
    localObject = k();
    if (!((String)localObject).equals("")) {
      paramMap.put(ZQK.f, localObject);
    }
    localObject = paramMap;
    if (!HL.b())
    {
      localObject = g();
      if (!((String)localObject).equals("")) {
        paramMap.put(ZQK.I, localObject);
      }
      localObject = h();
      if (!((String)localObject).equals("")) {
        paramMap.put(ZQK.M, localObject);
      }
      localObject = i();
      if (!((String)localObject).equals("")) {
        paramMap.put(ZQK.N, localObject);
      }
      localObject = j();
      if (!((String)localObject).equals("")) {
        paramMap.put(ZQK.S, localObject);
      }
      localObject = c(paramMap);
    }
    return localObject;
  }
  
  public String b()
  {
    String str = m();
    if (str.equals("")) {
      str = this.d.a(ZQK.u);
    } else {
      this.d.a(ZQK.u, str);
    }
    if (str.equals("")) {
      LO.a(this.c, MT.class, T7U.g, "EMPTY USER AGENT", this.a);
    }
    return str;
  }
  
  public Map<Object, String> b(Map<Object, String> paramMap)
  {
    String str = c();
    if (!str.equals("")) {
      paramMap.put(ZQK.r, str);
    }
    str = d();
    if (!str.equals("")) {
      paramMap.put(ZQK.s, str);
    }
    str = f();
    if (!str.equals(""))
    {
      paramMap.put(ZQK.h, str);
      paramMap.put(ZQK.i, str);
    }
    if (!HL.b())
    {
      str = a(str);
      if (!str.equals("")) {
        paramMap.put(ZQK.m, str);
      }
    }
    return paramMap;
  }
  
  public String c()
  {
    return "23";
  }
  
  public Map<Object, String> c(Map<Object, String> paramMap)
  {
    Location localLocation;
    if ((this.d.a(ZQK.aj, "1").equals("0")) && (this.d.a(ZQK.ak, "0").equals("1")))
    {
      localLocation = o();
      if (localLocation == null) {}
    }
    for (;;)
    {
      try
      {
        paramMap.put(ZQK.A, String.valueOf(localLocation.getLatitude()));
        paramMap.put(ZQK.y, String.valueOf(localLocation.getLongitude()));
      }
      catch (Exception localException1)
      {
        return paramMap;
      }
      try
      {
        paramMap.put(ZQK.C, String.valueOf(localLocation.getAccuracy()));
      }
      catch (Exception localException3) {}
    }
    paramMap.put(ZQK.C, "50");
    try
    {
      paramMap.put(ZQK.B, localLocation.getProvider());
    }
    catch (Exception localException4)
    {
      for (;;) {}
    }
    paramMap.put(ZQK.B, "unknown");
    try
    {
      paramMap.put(ZQK.D, String.valueOf(localLocation.getTime()));
      return paramMap;
    }
    catch (Exception localException2)
    {
      for (;;) {}
    }
    paramMap.put(ZQK.D, String.valueOf(System.currentTimeMillis()));
    return paramMap;
  }
  
  public String d()
  {
    return "1.7.242";
  }
  
  public Map<Object, String> d(Map<Object, String> paramMap)
  {
    return a(b(paramMap));
  }
  
  public String e()
  {
    if (this.d.a("check_user_permissions", "0").equals("0")) {
      return "";
    }
    if (Build.VERSION.SDK_INT >= 16) {}
    try
    {
      localPackageInfo = this.c.getPackageManager().getPackageInfo(this.c.getPackageName(), 4096);
      localObject1 = "[";
      i = 0;
      j = i;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        PackageInfo localPackageInfo;
        Object localObject1;
        int i;
        int k;
        Object localObject3;
        continue;
        i += 1;
        int j = k;
        Object localObject2 = localObject3;
      }
    }
    if (i < localPackageInfo.requestedPermissions.length)
    {
      k = j;
      localObject3 = localObject1;
      if ((localPackageInfo.requestedPermissionsFlags[i] & 0x2) != 0)
      {
        k = j + 1;
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append((String)localObject1);
        ((StringBuilder)localObject3).append("\"");
        ((StringBuilder)localObject3).append(localPackageInfo.requestedPermissions[i]);
        ((StringBuilder)localObject3).append("\",");
        localObject3 = ((StringBuilder)localObject3).toString();
      }
    }
    else
    {
      localObject3 = localObject1;
      if (j > 0) {
        localObject3 = ((String)localObject1).substring(0, ((String)localObject1).length() - 1);
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append((String)localObject3);
      ((StringBuilder)localObject1).append("]");
      localObject1 = ((StringBuilder)localObject1).toString();
      return localObject1;
      return null;
    }
  }
  
  public String f()
  {
    try
    {
      String str = this.c.getApplicationInfo().packageName;
      return str;
    }
    catch (Exception localException)
    {
      LO.a(this.c, MT.class, T7U.i, localException, this.a);
    }
    return "";
  }
  
  public String g()
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(Build.VERSION.SDK_INT);
      localObject = ((StringBuilder)localObject).toString();
      return localObject;
    }
    catch (Exception localException)
    {
      LO.a(this.c, MT.class, T7U.j, localException, this.a);
    }
    return "";
  }
  
  public String h()
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(Build.MODEL);
      localObject = ((StringBuilder)localObject).toString();
      return localObject;
    }
    catch (Exception localException)
    {
      LO.a(this.c, MT.class, T7U.n, localException, this.a);
    }
    return "";
  }
  
  public String i()
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(Build.MANUFACTURER);
      localObject = ((StringBuilder)localObject).toString();
      return localObject;
    }
    catch (Exception localException)
    {
      LO.a(this.c, MT.class, T7U.m, localException, this.a);
    }
    return "";
  }
  
  public String j()
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(Build.PRODUCT);
      localObject = ((StringBuilder)localObject).toString();
      return localObject;
    }
    catch (Exception localException)
    {
      LO.a(this.c, MT.class, T7U.l, localException, this.a);
    }
    return "";
  }
}
