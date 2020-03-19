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

public class PLR
{
  public boolean a = false;
  private TelephonyManager b;
  private Context c;
  private IK d;
  
  public PLR(Context paramContext)
  {
    this.c = paramContext;
    this.d = new IK(paramContext);
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
      X28.a(this.c, PLR.class, Q5R.h, paramString, this.a);
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
      X28.a(this.c, PLR.class, Q5R.a, localException, this.a);
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
        X28.a(this.c, PLR.class, Q5R.b, localException, this.a);
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
      if (!this.d.a(BB.b).equals("")) {
        return this.d.a(BB.b);
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
          this.d.a(BB.b, (String)localObject1);
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
        X28.a(this.c, PLR.class, Q5R.c, localGooglePlayServicesNotAvailableException2, this.a);
      }
      catch (NullPointerException localNullPointerException2)
      {
        localObject1 = localGooglePlayServicesNotAvailableException1;
        X28.a(this.c, PLR.class, Q5R.f, localNullPointerException2, this.a);
        return localObject1;
      }
      catch (IOException localIOException2)
      {
        localObject1 = localGooglePlayServicesNotAvailableException1;
        X28.a(this.c, PLR.class, Q5R.e, localIOException2, this.a);
        return localObject1;
      }
      catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException2)
      {
        localObject1 = localGooglePlayServicesNotAvailableException1;
        X28.a(this.c, PLR.class, Q5R.d, localGooglePlayServicesRepairableException2, this.a);
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
    X28.a(this.c, PLR.class, Q5R.o, "The class 'com.google.android.gms.ads.identifier.AdvertisingIdClient' not found make sure you include it", this.a);
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
      X28.a(this.c, PLR.class, Q5R.k, localException, this.a);
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
          this.d.a(PEU.u, str3);
          return str3;
        }
      }
      else
      {
        str1 = this.d.a(PEU.u);
        return str1;
      }
    }
    catch (Exception localException)
    {
      X28.a(this.c, PLR.class, Q5R.g, localException, this.a);
      String str2 = this.d.a(PEU.u);
      return str2;
    }
  }
  
  public Map<PEU, String> a(Map<PEU, String> paramMap)
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
      paramMap.put(PEU.j, str);
    }
    str = g();
    if (!str.equals("")) {
      paramMap.put(PEU.d, str);
    }
    str = h();
    if (!str.equals("")) {
      paramMap.put(PEU.e, str);
    }
    str = i();
    if (!str.equals("")) {
      paramMap.put(PEU.f, str);
    }
    str = c();
    if (!str.equals("")) {
      paramMap.put(PEU.C, str);
    }
    str = d();
    if (!str.equals("")) {
      paramMap.put(PEU.G, str);
    }
    str = e();
    if (!str.equals("")) {
      paramMap.put(PEU.H, str);
    }
    str = f();
    if (!str.equals("")) {
      paramMap.put(PEU.I, str);
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
      X28.a(this.c, PLR.class, Q5R.i, localException, this.a);
    }
    return "";
  }
  
  public Map<PEU, String> b(Map<PEU, String> paramMap)
  {
    String str = b();
    if (!str.equals(""))
    {
      paramMap.put(PEU.h, str);
      paramMap.put(PEU.i, str);
    }
    str = a(str);
    if (!str.equals("")) {
      paramMap.put(PEU.m, str);
    }
    if (!"10".equals("")) {
      paramMap.put(PEU.r, "10");
    }
    if (!"1.3".equals("")) {
      paramMap.put(PEU.s, "1.3");
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
      X28.a(this.c, PLR.class, Q5R.j, localException, this.a);
    }
    return "";
  }
  
  public Map<PEU, String> c(Map<PEU, String> paramMap)
  {
    String str = j();
    if (!str.equals("")) {
      paramMap.put(PEU.t, str);
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
      X28.a(this.c, PLR.class, Q5R.n, localException, this.a);
    }
    return "";
  }
  
  public Map<PEU, String> d(Map<PEU, String> paramMap)
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
      X28.a(this.c, PLR.class, Q5R.m, localException, this.a);
    }
    return "";
  }
  
  public Map<PEU, String> e(Map<PEU, String> paramMap)
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
      X28.a(this.c, PLR.class, Q5R.l, localException, this.a);
    }
    return "";
  }
}
