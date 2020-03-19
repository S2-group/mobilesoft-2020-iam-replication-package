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

public class UJA
{
  public boolean a = false;
  private TelephonyManager b;
  private Context c;
  private FH0 d;
  
  public UJA(Context paramContext)
  {
    this.c = paramContext;
    this.d = new FH0(paramContext);
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
      QO6.a(this.c, UJA.class, ZI.h, paramString, this.a);
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
      QO6.a(this.c, UJA.class, ZI.a, localException, this.a);
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
        QO6.a(this.c, UJA.class, ZI.b, localException, this.a);
        return "";
      }
    }
    return "";
  }
  
  private String i()
  {
    String str1 = "";
    do
    {
      try
      {
        Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
        if (!this.d.a(TM4.b).equals(""))
        {
          localObject1 = this.d.a(TM4.b);
          return localObject1;
        }
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        QO6.a(this.c, UJA.class, ZI.o, "The class 'com.google.android.gms.ads.identifier.AdvertisingIdClient' not found make sure you include it", this.a);
        return "";
      }
      localObject1 = localClassNotFoundException;
    } while (Looper.getMainLooper().equals(Looper.myLooper()));
    Object localObject1 = null;
    Object localObject2 = localClassNotFoundException;
    Object localObject3 = localClassNotFoundException;
    Object localObject4 = localClassNotFoundException;
    Object localObject5 = localClassNotFoundException;
    try
    {
      if (this.c.getApplicationContext() != null)
      {
        localObject2 = localClassNotFoundException;
        localObject3 = localClassNotFoundException;
        localObject4 = localClassNotFoundException;
        localObject5 = localClassNotFoundException;
        localObject1 = AdvertisingIdClient.getAdvertisingIdInfo(this.c.getApplicationContext());
      }
      Object localObject6 = localObject1;
      if (localObject1 == null)
      {
        localObject2 = localClassNotFoundException;
        localObject3 = localClassNotFoundException;
        localObject4 = localClassNotFoundException;
        localObject5 = localClassNotFoundException;
        localObject6 = AdvertisingIdClient.getAdvertisingIdInfo(this.c);
      }
      localObject2 = localClassNotFoundException;
      localObject3 = localClassNotFoundException;
      localObject4 = localClassNotFoundException;
      localObject5 = localClassNotFoundException;
      String str2 = ((AdvertisingIdClient.Info)localObject6).getId();
      localObject2 = str2;
      localObject3 = str2;
      localObject4 = str2;
      localObject5 = str2;
      this.d.a(TM4.b, str2);
      return str2;
    }
    catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
    {
      QO6.a(this.c, UJA.class, ZI.c, localGooglePlayServicesNotAvailableException, this.a);
      return localObject2;
    }
    catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
    {
      QO6.a(this.c, UJA.class, ZI.d, localGooglePlayServicesRepairableException, this.a);
      return localObject3;
    }
    catch (IOException localIOException)
    {
      QO6.a(this.c, UJA.class, ZI.e, localIOException, this.a);
      return localObject4;
    }
    catch (NullPointerException localNullPointerException)
    {
      QO6.a(this.c, UJA.class, ZI.f, localNullPointerException, this.a);
    }
    return localObject5;
  }
  
  private String j()
  {
    int i = 0;
    for (;;)
    {
      try
      {
        Object localObject = this.c.getPackageManager();
        String str = "{\"apps\":[";
        Iterator localIterator = ((PackageManager)localObject).getInstalledApplications(128).iterator();
        if (localIterator.hasNext())
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
          if (localApplicationInfo.sourceDir.startsWith("/data/app/"))
          {
            str = str + "{\"title\":\"" + localApplicationInfo.loadLabel((PackageManager)localObject) + "\",";
            str = str + "\"pakeagename\":\"" + localApplicationInfo.packageName + "\"}, ";
            i += 1;
          }
        }
        else
        {
          localObject = str;
          if (i > 0) {
            localObject = str.substring(0, str.length() - 2);
          }
          str = (String)localObject + "]}";
          return str;
        }
      }
      catch (Exception localException)
      {
        QO6.a(this.c, UJA.class, ZI.k, localException, this.a);
        return "";
      }
    }
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
          this.d.a(F3L.u, str3);
          return str3;
        }
      }
      else
      {
        str1 = this.d.a(F3L.u);
        return str1;
      }
    }
    catch (Exception localException)
    {
      QO6.a(this.c, UJA.class, ZI.g, localException, this.a);
      String str2 = this.d.a(F3L.u);
      return str2;
    }
  }
  
  public Map<F3L, String> a(Map<F3L, String> paramMap)
  {
    Object localObject = i();
    if (!((String)localObject).equals("")) {}
    for (;;)
    {
      if (!((String)localObject).equals("")) {
        paramMap.put(F3L.j, localObject);
      }
      localObject = g();
      if (!((String)localObject).equals("")) {
        paramMap.put(F3L.d, localObject);
      }
      localObject = h();
      if (!((String)localObject).equals("")) {
        paramMap.put(F3L.e, localObject);
      }
      localObject = i();
      if (!((String)localObject).equals("")) {
        paramMap.put(F3L.f, localObject);
      }
      localObject = c();
      if (!((String)localObject).equals("")) {
        paramMap.put(F3L.C, localObject);
      }
      localObject = d();
      if (!((String)localObject).equals("")) {
        paramMap.put(F3L.G, localObject);
      }
      localObject = e();
      if (!((String)localObject).equals("")) {
        paramMap.put(F3L.H, localObject);
      }
      localObject = f();
      if (!((String)localObject).equals("")) {
        paramMap.put(F3L.I, localObject);
      }
      return paramMap;
      String str = g();
      localObject = str;
      if (str.equals(""))
      {
        str = h();
        localObject = str;
        if (str.equals("")) {
          localObject = "";
        }
      }
    }
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
      QO6.a(this.c, UJA.class, ZI.i, localException, this.a);
    }
    return "";
  }
  
  public Map<F3L, String> b(Map<F3L, String> paramMap)
  {
    String str = b();
    if (!str.equals(""))
    {
      paramMap.put(F3L.h, str);
      paramMap.put(F3L.i, str);
    }
    str = a(str);
    if (!str.equals("")) {
      paramMap.put(F3L.m, str);
    }
    if (!"10".equals("")) {
      paramMap.put(F3L.r, "10");
    }
    if (!"1.3".equals("")) {
      paramMap.put(F3L.s, "1.3");
    }
    return paramMap;
  }
  
  public String c()
  {
    try
    {
      String str = Build.VERSION.SDK_INT;
      return str;
    }
    catch (Exception localException)
    {
      QO6.a(this.c, UJA.class, ZI.j, localException, this.a);
    }
    return "";
  }
  
  public Map<F3L, String> c(Map<F3L, String> paramMap)
  {
    String str = j();
    if (!str.equals("")) {
      paramMap.put(F3L.t, str);
    }
    return paramMap;
  }
  
  public String d()
  {
    try
    {
      String str = Build.MODEL;
      return str;
    }
    catch (Exception localException)
    {
      QO6.a(this.c, UJA.class, ZI.n, localException, this.a);
    }
    return "";
  }
  
  public Map<F3L, String> d(Map<F3L, String> paramMap)
  {
    return paramMap;
  }
  
  public String e()
  {
    try
    {
      String str = Build.MANUFACTURER;
      return str;
    }
    catch (Exception localException)
    {
      QO6.a(this.c, UJA.class, ZI.m, localException, this.a);
    }
    return "";
  }
  
  public Map<F3L, String> e(Map<F3L, String> paramMap)
  {
    return d(a(b(paramMap)));
  }
  
  public String f()
  {
    try
    {
      String str = Build.PRODUCT;
      return str;
    }
    catch (Exception localException)
    {
      QO6.a(this.c, UJA.class, ZI.l, localException, this.a);
    }
    return "";
  }
}
