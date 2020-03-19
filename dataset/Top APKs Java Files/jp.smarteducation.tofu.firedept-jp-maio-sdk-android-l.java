package jp.maio.sdk.android;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

class l
{
  private static String a = "android";
  private static String b = "";
  private static String c = "";
  private static String d = "";
  private static float e = 0.0F;
  private static int f = 0;
  private static int g = 0;
  private static int h = 0;
  private static LocationManager i = null;
  private static double j = 0.0D;
  private static double k = 0.0D;
  private static HashSet<String> l;
  private static final LocationListener m = new LocationListener()
  {
    public void onLocationChanged(Location paramAnonymousLocation)
    {
      l.a(paramAnonymousLocation.getLatitude());
      l.b(paramAnonymousLocation.getLongitude());
      ba.a("SDK API Message", "Location get lat:" + l.o() + " lng:" + l.p(), null);
    }
    
    public void onProviderDisabled(String paramAnonymousString) {}
    
    public void onProviderEnabled(String paramAnonymousString) {}
    
    public void onStatusChanged(String paramAnonymousString, int paramAnonymousInt, Bundle paramAnonymousBundle) {}
  };
  
  private l() {}
  
  private static int a(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      int n = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return n;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return 0;
  }
  
  public static void a()
  {
    b = g.a().getPackageName();
    c = Locale.getDefault().getLanguage();
    h = a(g.a());
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(g.a());
          if ((localInfo == null) || (localInfo.isLimitAdTrackingEnabled()))
          {
            ba.a("SDK API Message", "AdvertisingId get Error. (LimitAdTrackingEnabled = True)", null);
            return;
          }
          l.c(localInfo.getId());
          ba.a("SDK API Message", "AdvertisingId get success. (advertisementId: " + l.n() + ")", null);
          return;
        }
        catch (IOException localIOException)
        {
          ba.a("SDK API Message", "AdvertisingId get error. (IOException) ", localIOException);
          return;
        }
        catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
        {
          ba.a("SDK API Message", "AdvertisingId get error. (GooglePlayServicesNotAvailableException) ", localGooglePlayServicesNotAvailableException);
          return;
        }
        catch (IllegalStateException localIllegalStateException)
        {
          ba.a("SDK API Message", "AdvertisingId get error. (IllegalStateException) ", localIllegalStateException);
          throw localIllegalStateException;
        }
        catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
        {
          ba.a("SDK API Message", "AdvertisingId get error. (GooglePlayServicesRepairableException) ", localGooglePlayServicesRepairableException);
          return;
        }
        catch (VerifyError localVerifyError)
        {
          ba.a("SDK API Message", "AdvertisingId get error. (VerifyError) ", localVerifyError);
          return;
        }
        catch (NullPointerException localNullPointerException)
        {
          ba.a("SDK API Message", "AdvertisingId get error (NullPointerError) ", localNullPointerException);
        }
      }
    }).start();
    e = r().density;
    Point localPoint = q();
    f = localPoint.x;
    g = localPoint.y;
    ba.a("SDK API Message", "", "Sdk api init complete.", null);
  }
  
  public static boolean a(String paramString)
  {
    return g.a().getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(paramString + "://")), 65536).size() > 0;
  }
  
  public static int b()
  {
    return h;
  }
  
  public static boolean b(String paramString)
  {
    if (l == null) {
      try
      {
        if (l == null)
        {
          l = new HashSet();
          Iterator localIterator = g.a().getPackageManager().getInstalledApplications(0).iterator();
          while (localIterator.hasNext())
          {
            ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
            l.add(localApplicationInfo.packageName);
          }
        }
      }
      finally {}
    }
    return l.contains(paramString);
  }
  
  public static String c()
  {
    return a;
  }
  
  public static String d()
  {
    return b;
  }
  
  public static String e()
  {
    return c;
  }
  
  public static String f()
  {
    return d;
  }
  
  public static String g()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static String h()
  {
    return Build.BRAND;
  }
  
  public static String i()
  {
    return Build.DEVICE;
  }
  
  public static float j()
  {
    return e;
  }
  
  public static int k()
  {
    return f;
  }
  
  public static int l()
  {
    return g;
  }
  
  public static String m()
  {
    Object localObject = g.a();
    if (((Context)localObject).checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == -1) {
      return "mobile";
    }
    localObject = (ConnectivityManager)((Context)localObject).getSystemService("connectivity");
    if (localObject == null) {
      return "mobile";
    }
    localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
    if ((localObject != null) && (((NetworkInfo)localObject).isConnectedOrConnecting())) {
      return ((NetworkInfo)localObject).getTypeName().toLowerCase(Locale.getDefault());
    }
    return "";
  }
  
  private static Point q()
  {
    Point localPoint = new Point(0, 0);
    DisplayMetrics localDisplayMetrics = r();
    if (s().equals("l"))
    {
      localPoint.x = localDisplayMetrics.heightPixels;
      localPoint.y = localDisplayMetrics.widthPixels;
      return localPoint;
    }
    localPoint.x = localDisplayMetrics.widthPixels;
    localPoint.y = localDisplayMetrics.heightPixels;
    return localPoint;
  }
  
  private static DisplayMetrics r()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)g.a().getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }
  
  private static String s()
  {
    switch (g.a().getResources().getConfiguration().orientation)
    {
    default: 
      return "";
    case 1: 
      return "p";
    }
    return "l";
  }
}
