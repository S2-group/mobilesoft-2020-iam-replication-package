package c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ZLU
{
  public boolean a = false;
  private TelephonyManager b;
  private Context c;
  private Z_L d;
  
  public ZLU(Context paramContext)
  {
    this.c = paramContext;
    this.d = new Z_L(paramContext);
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
      WAP.a(this.c, ZLU.class, T7P.h, paramString, this.a);
    }
    return "";
  }
  
  private String g()
  {
    try
    {
      String str = Settings.Secure.getString(this.c.getContentResolver(), "android_id");
      return str;
    }
    catch (Exception localException)
    {
      WAP.a(this.c, ZLU.class, T7P.a, localException, this.a);
    }
    return "";
  }
  
  private String h()
  {
    if (this.c.getApplicationContext().checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0) {
      try
      {
        String str = this.b.getDeviceId();
        if (str != null) {
          return str;
        }
      }
      catch (Exception localException)
      {
        WAP.a(this.c, ZLU.class, T7P.b, localException, this.a);
        return "";
      }
    }
    return "";
  }
  
  private String i()
  {
    String str = "";
    try
    {
      Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
      if (!this.d.a(QGO.b).equals("")) {
        return this.d.a(QGO.b);
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
        localObject1 = ((AdvertisingIdClient.Info)localObject2).getId();
        try
        {
          this.d.a(QGO.b, (String)localObject1);
          return localObject1;
        }
        catch (NullPointerException localNullPointerException1)
        {
          localObject2 = localNullPointerException1;
        }
        catch (IOException localIOException1)
        {
          localObject2 = localIOException1;
        }
        catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException1)
        {
          localObject2 = localGooglePlayServicesRepairableException1;
        }
        catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException1)
        {
          localObject2 = localGooglePlayServicesNotAvailableException1;
        }
        WAP.a(this.c, ZLU.class, T7P.c, localGooglePlayServicesNotAvailableException2, this.a);
      }
      catch (NullPointerException localNullPointerException2)
      {
        localObject1 = localGooglePlayServicesNotAvailableException1;
        WAP.a(this.c, ZLU.class, T7P.f, localNullPointerException2, this.a);
        return localObject1;
      }
      catch (IOException localIOException2)
      {
        localObject1 = localGooglePlayServicesNotAvailableException1;
        WAP.a(this.c, ZLU.class, T7P.e, localIOException2, this.a);
        return localObject1;
      }
      catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException2)
      {
        localObject1 = localGooglePlayServicesNotAvailableException1;
        WAP.a(this.c, ZLU.class, T7P.d, localGooglePlayServicesRepairableException2, this.a);
        return localObject1;
      }
      catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException2)
      {
        localObject1 = localGooglePlayServicesNotAvailableException1;
      }
      return localObject1;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;) {}
    }
    WAP.a(this.c, ZLU.class, T7P.o, "The class 'com.google.android.gms.ads.identifier.AdvertisingIdClient' not found make sure you include it", this.a);
    return "";
  }
  
  private String j()
  {
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
      WAP.a(this.c, ZLU.class, T7P.k, localException, this.a);
    }
    return "";
  }
  
  public String a()
  {
    try
    {
      String str1;
      if (Looper.getMainLooper().equals(Looper.myLooper()))
      {
        String str3 = new WebView(this.c).getSettings().getUserAgentString();
        str1 = str3;
        if (!str3.equals(""))
        {
          this.d.a(J.u, str3);
          return str3;
        }
      }
      else
      {
        str1 = this.d.a(J.u);
        return str1;
      }
    }
    catch (Exception localException)
    {
      WAP.a(this.c, ZLU.class, T7P.g, localException, this.a);
      String str2 = this.d.a(J.u);
      return str2;
    }
  }
  
  public Map<J, String> a(Map<J, String> paramMap)
  {
    String str = i();
    if (str.equals(""))
    {
      str = g();
      if (str.equals(""))
      {
        str = h();
        if (str.equals("")) {
          str = "";
        }
      }
    }
    if (!str.equals("")) {
      paramMap.put(J.j, str);
    }
    str = g();
    if (!str.equals("")) {
      paramMap.put(J.d, str);
    }
    str = h();
    if (!str.equals("")) {
      paramMap.put(J.e, str);
    }
    str = i();
    if (!str.equals("")) {
      paramMap.put(J.f, str);
    }
    str = c();
    if (!str.equals("")) {
      paramMap.put(J.C, str);
    }
    str = d();
    if (!str.equals("")) {
      paramMap.put(J.G, str);
    }
    str = e();
    if (!str.equals("")) {
      paramMap.put(J.H, str);
    }
    str = f();
    if (!str.equals("")) {
      paramMap.put(J.I, str);
    }
    return paramMap;
  }
  
  public String b()
  {
    try
    {
      String str = this.c.getApplicationInfo().packageName;
      return str;
    }
    catch (Exception localException)
    {
      WAP.a(this.c, ZLU.class, T7P.i, localException, this.a);
    }
    return "";
  }
  
  public Map<J, String> b(Map<J, String> paramMap)
  {
    String str = b();
    if (!str.equals(""))
    {
      paramMap.put(J.h, str);
      paramMap.put(J.i, str);
    }
    str = a(str);
    if (!str.equals("")) {
      paramMap.put(J.m, str);
    }
    if (!"10".equals("")) {
      paramMap.put(J.r, "10");
    }
    if (!"1.3".equals("")) {
      paramMap.put(J.s, "1.3");
    }
    return paramMap;
  }
  
  public String c()
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
      WAP.a(this.c, ZLU.class, T7P.j, localException, this.a);
    }
    return "";
  }
  
  public Map<J, String> c(Map<J, String> paramMap)
  {
    String str = j();
    if (!str.equals("")) {
      paramMap.put(J.t, str);
    }
    return paramMap;
  }
  
  public String d()
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
      WAP.a(this.c, ZLU.class, T7P.n, localException, this.a);
    }
    return "";
  }
  
  public Map<J, String> d(Map<J, String> paramMap)
  {
    return paramMap;
  }
  
  public String e()
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
      WAP.a(this.c, ZLU.class, T7P.m, localException, this.a);
    }
    return "";
  }
  
  public Map<J, String> e(Map<J, String> paramMap)
  {
    return d(a(b(paramMap)));
  }
  
  public String f()
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
      WAP.a(this.c, ZLU.class, T7P.l, localException, this.a);
    }
    return "";
  }
}
