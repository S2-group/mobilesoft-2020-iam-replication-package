import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

public final class bi
{
  private static Object j = new Object();
  private static bi k = null;
  public String a = null;
  public int b = 0;
  private Context c = null;
  private TelephonyManager d = null;
  private WifiManager e = null;
  private String f = null;
  private String g = null;
  private String h = null;
  private String i = null;
  
  private bi(Context paramContext)
  {
    if (paramContext == null) {}
    do
    {
      return;
      this.c = paramContext;
      this.a = Build.MODEL;
      this.d = ((TelephonyManager)paramContext.getSystemService("phone"));
      this.e = ((WifiManager)paramContext.getSystemService("wifi"));
    } while ((this.d == null) || (this.e == null));
    try
    {
      this.f = this.d.getDeviceId();
      this.g = this.d.getSubscriberId();
      if (this.e.getConnectionInfo() != null)
      {
        this.h = this.e.getConnectionInfo().getMacAddress();
        if ((this.h != null) && (this.h.length() > 0)) {
          this.h = this.h.replace(":", "");
        }
      }
      this.b = this.d.getNetworkType();
      this.i = paramContext.getPackageName();
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public static bi a(Context paramContext)
  {
    if (k == null) {}
    for (;;)
    {
      synchronized (j)
      {
        if ((k == null) && (c(paramContext)))
        {
          Object localObject2 = (LocationManager)paramContext.getSystemService("location");
          if (localObject2 == null) {
            continue;
          }
          localObject2 = ((LocationManager)localObject2).getAllProviders().iterator();
          if (!((Iterator)localObject2).hasNext()) {
            continue;
          }
          String str = (String)((Iterator)localObject2).next();
          if (str.equals("passive")) {
            break label123;
          }
          if (!str.equals("gps")) {
            continue;
          }
          break label123;
          if (m != 0) {
            k = new bi(paramContext);
          }
        }
        return k;
        m = 0;
      }
      label123:
      int m = 1;
    }
  }
  
  public static boolean a(Location paramLocation)
  {
    if (paramLocation == null) {
      return false;
    }
    for (;;)
    {
      try
      {
        Method localMethod = Location.class.getMethod("isFromMockProvider", null);
        if (localMethod != null)
        {
          bool = ((Boolean)localMethod.invoke(paramLocation, null)).booleanValue();
          return bool;
        }
      }
      catch (Exception paramLocation)
      {
        return false;
      }
      boolean bool = false;
    }
  }
  
  public static boolean b(Context paramContext)
  {
    if (paramContext == null) {
      return true;
    }
    PackageManager localPackageManager;
    Object localObject;
    boolean bool1;
    if (!Settings.Secure.getString(paramContext.getContentResolver(), "mock_location").equals("0"))
    {
      localPackageManager = paramContext.getPackageManager();
      localObject = localPackageManager.getInstalledApplications(128);
      paramContext = paramContext.getPackageName();
      localObject = ((List)localObject).iterator();
      bool1 = false;
    }
    for (;;)
    {
      boolean bool2 = bool1;
      ApplicationInfo localApplicationInfo;
      if (((Iterator)localObject).hasNext())
      {
        localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
        bool2 = bool1;
        if (bool1) {}
      }
      try
      {
        String[] arrayOfString = localPackageManager.getPackageInfo(localApplicationInfo.packageName, 4096).requestedPermissions;
        if (arrayOfString != null)
        {
          int n = arrayOfString.length;
          int m = 0;
          while (m < n)
          {
            if (arrayOfString[m].equals("android.permission.ACCESS_MOCK_LOCATION"))
            {
              bool2 = localApplicationInfo.packageName.equals(paramContext);
              if (bool2) {
                break;
              }
              bool1 = true;
              break;
            }
            m += 1;
          }
          bool2 = false;
          return bool2;
        }
      }
      catch (Exception localException) {}
    }
  }
  
  private static boolean c(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramContext.getPackageName(), 4096);
      paramContext = paramContext.requestedPermissions;
      int m = 0;
      while (m < bk.h.length)
      {
        if (!bk.a(paramContext, bk.h[m])) {
          return false;
        }
        m += 1;
      }
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public final String a()
  {
    if ((this.f == null) && (this.c != null))
    {
      this.d = ((TelephonyManager)this.c.getSystemService("phone"));
      if (this.d == null) {}
    }
    try
    {
      this.f = this.d.getDeviceId();
      if (this.f != null) {
        return this.f;
      }
      return "";
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public final String b()
  {
    if ((this.g == null) && (this.c != null))
    {
      this.d = ((TelephonyManager)this.c.getSystemService("phone"));
      if (this.d != null) {
        this.g = this.d.getSubscriberId();
      }
    }
    if (this.g != null) {
      return this.g;
    }
    return "";
  }
  
  public final String c()
  {
    if ((this.h == null) && (this.c != null))
    {
      this.e = ((WifiManager)this.c.getSystemService("wifi"));
      if ((this.e != null) && (this.e.getConnectionInfo() != null))
      {
        this.h = this.e.getConnectionInfo().getMacAddress();
        if ((this.h != null) && (this.h.length() > 0)) {
          this.h = this.h.replace(":", "");
        }
      }
    }
    if (this.h != null) {
      return this.h;
    }
    return "";
  }
  
  public final String d()
  {
    if ((this.i == null) && (this.c != null)) {
      this.i = this.c.getPackageName();
    }
    if (this.i != null) {
      return this.i;
    }
    return "";
  }
}
