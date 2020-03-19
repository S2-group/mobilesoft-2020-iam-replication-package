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
  public boolean ˊ = false;
  private TelephonyManager ˋ;
  private Context ˎ;
  private OMI ˏ;
  
  public MT(Context paramContext)
  {
    this.ˎ = paramContext;
    this.ˏ = new OMI(paramContext);
    this.ˋ = ((TelephonyManager)this.ˎ.getSystemService("phone"));
  }
  
  private String ʾ()
  {
    try
    {
      Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
      if (!this.ˏ.ˊ(D4T.ˏ).equals("")) {
        return this.ˏ.ˊ(D4T.ˏ);
      }
      if (Looper.getMainLooper().equals(Looper.myLooper())) {
        return "";
      }
      Object localObject1 = null;
      try
      {
        if (this.ˎ.getApplicationContext() != null) {
          localObject1 = AdvertisingIdClient.getAdvertisingIdInfo(this.ˎ.getApplicationContext());
        }
        Object localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = AdvertisingIdClient.getAdvertisingIdInfo(this.ˎ);
        }
        if (localObject2 != null)
        {
          localObject1 = ((AdvertisingIdClient.Info)localObject2).getId();
          this.ˏ.ˊ(D4T.ˏ, (String)localObject1);
        }
      }
      catch (NullPointerException localNullPointerException)
      {
        LO.ˊ(this.ˎ, MT.class, T7U.ʻ, localNullPointerException, this.ˊ);
      }
      catch (IOException localIOException)
      {
        LO.ˊ(this.ˎ, MT.class, T7U.ᐝ, localIOException, this.ˊ);
      }
      catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
      {
        LO.ˊ(this.ˎ, MT.class, T7U.ˏ, localGooglePlayServicesRepairableException, this.ˊ);
      }
      catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
      {
        LO.ˊ(this.ˎ, MT.class, T7U.ˎ, localGooglePlayServicesNotAvailableException, this.ˊ);
      }
      return this.ˏ.ˊ(D4T.ˏ, "");
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;) {}
    }
    LO.ˊ(this.ˎ, MT.class, T7U.ˌ, "The class 'com.google.android.gms.ads.identifier.AdvertisingIdClient' not found make sure you include it", this.ˊ, true);
    return "";
  }
  
  @TargetApi(17)
  private String ʿ()
  {
    try
    {
      String str = WebSettings.getDefaultUserAgent(this.ˎ);
      return str;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return "";
  }
  
  private String ˈ()
  {
    Object localObject1;
    if (Build.VERSION.SDK_INT >= 17)
    {
      localObject1 = ʿ();
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
        String str = ((WebSettings)((Constructor)localObject1).newInstance(new Object[] { this.ˎ, null })).getUserAgentString();
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
  
  private String ˉ()
  {
    if (this.ˏ.ˊ("check_user_app_bundles", "0").equals("0")) {
      return "";
    }
    try
    {
      Object localObject2 = this.ˎ.getPackageManager();
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
      LO.ˊ(this.ˎ, MT.class, T7U.ʾ, localException, this.ˊ);
    }
    return "";
  }
  
  private String ˊ(String paramString)
  {
    try
    {
      long l = this.ˎ.getPackageManager().getPackageInfo(paramString, 0).firstInstallTime;
      paramString = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss").format(new Date(l));
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      LO.ˊ(this.ˎ, MT.class, T7U.ʽ, paramString, this.ˊ);
    }
    return "";
  }
  
  private Location ˌ()
  {
    try
    {
      if ((this.ˎ.getApplicationContext().checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) && (this.ˎ.getApplicationContext().checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0))
      {
        Object localObject = (LocationManager)this.ˎ.getSystemService("location");
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
  
  public String ʻ()
  {
    try
    {
      String str = this.ˎ.getApplicationInfo().packageName;
      return str;
    }
    catch (Exception localException)
    {
      LO.ˊ(this.ˎ, MT.class, T7U.ͺ, localException, this.ˊ);
    }
    return "";
  }
  
  public String ʼ()
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
      LO.ˊ(this.ˎ, MT.class, T7U.ι, localException, this.ˊ);
    }
    return "";
  }
  
  public String ʽ()
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
      LO.ˊ(this.ˎ, MT.class, T7U.ˉ, localException, this.ˊ);
    }
    return "";
  }
  
  public String ˊ()
  {
    return ʾ();
  }
  
  public Map<ZQK, String> ˊ(Map<ZQK, String> paramMap)
  {
    String str = ˉ();
    if (!str.equals("")) {
      paramMap.put(ZQK.ᐨ, str);
    }
    str = ᐝ();
    if (!str.equals("")) {
      paramMap.put(ZQK.ᗮ, str);
    }
    return paramMap;
  }
  
  public Map<Object, String> ˊ(Map<Object, String> paramMap)
  {
    Object localObject = ˊ();
    if (!((String)localObject).equals("")) {
      paramMap.put(ZQK.ι, localObject);
    }
    localObject = ʾ();
    if (!((String)localObject).equals("")) {
      paramMap.put(ZQK.ʻ, localObject);
    }
    localObject = paramMap;
    if (!HL.ˋ())
    {
      localObject = ʼ();
      if (!((String)localObject).equals("")) {
        paramMap.put(ZQK.ʳ, localObject);
      }
      localObject = ʽ();
      if (!((String)localObject).equals("")) {
        paramMap.put(ZQK.ˡ, localObject);
      }
      localObject = ͺ();
      if (!((String)localObject).equals("")) {
        paramMap.put(ZQK.ˮ, localObject);
      }
      localObject = ι();
      if (!((String)localObject).equals("")) {
        paramMap.put(ZQK.ᑊ, localObject);
      }
      localObject = ˎ(paramMap);
    }
    return localObject;
  }
  
  public String ˋ()
  {
    String str = ˈ();
    if (str.equals("")) {
      str = this.ˏ.ˊ(ZQK.ﹳ);
    } else {
      this.ˏ.ˊ(ZQK.ﹳ, str);
    }
    if (str.equals("")) {
      LO.ˊ(this.ˎ, MT.class, T7U.ʼ, "EMPTY USER AGENT", this.ˊ);
    }
    return str;
  }
  
  public Map<Object, String> ˋ(Map<Object, String> paramMap)
  {
    String str = ˎ();
    if (!str.equals("")) {
      paramMap.put(ZQK.ـ, str);
    }
    str = ˏ();
    if (!str.equals("")) {
      paramMap.put(ZQK.ᐧ, str);
    }
    str = ʻ();
    if (!str.equals(""))
    {
      paramMap.put(ZQK.ʽ, str);
      paramMap.put(ZQK.ͺ, str);
    }
    if (!HL.ˋ())
    {
      str = ˊ(str);
      if (!str.equals("")) {
        paramMap.put(ZQK.ˈ, str);
      }
    }
    return paramMap;
  }
  
  public String ˎ()
  {
    return "23";
  }
  
  public Map<Object, String> ˎ(Map<Object, String> paramMap)
  {
    Location localLocation;
    if ((this.ˏ.ˊ(ZQK.ᵗ, "1").equals("0")) && (this.ˏ.ˊ(ZQK.ﾟ, "0").equals("1")))
    {
      localLocation = ˌ();
      if (localLocation == null) {}
    }
    for (;;)
    {
      try
      {
        paramMap.put(ZQK.ᴵ, String.valueOf(localLocation.getLatitude()));
        paramMap.put(ZQK.י, String.valueOf(localLocation.getLongitude()));
      }
      catch (Exception localException1)
      {
        return paramMap;
      }
      try
      {
        paramMap.put(ZQK.ᵔ, String.valueOf(localLocation.getAccuracy()));
      }
      catch (Exception localException3) {}
    }
    paramMap.put(ZQK.ᵔ, "50");
    try
    {
      paramMap.put(ZQK.ᵎ, localLocation.getProvider());
    }
    catch (Exception localException4)
    {
      for (;;) {}
    }
    paramMap.put(ZQK.ᵎ, "unknown");
    try
    {
      paramMap.put(ZQK.ᵢ, String.valueOf(localLocation.getTime()));
      return paramMap;
    }
    catch (Exception localException2)
    {
      for (;;) {}
    }
    paramMap.put(ZQK.ᵢ, String.valueOf(System.currentTimeMillis()));
    return paramMap;
  }
  
  public String ˏ()
  {
    return "1.7.242";
  }
  
  public Map<Object, String> ˏ(Map<Object, String> paramMap)
  {
    return ˊ(ˋ(paramMap));
  }
  
  public String ͺ()
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
      LO.ˊ(this.ˎ, MT.class, T7U.ˈ, localException, this.ˊ);
    }
    return "";
  }
  
  public String ᐝ()
  {
    if (this.ˏ.ˊ("check_user_permissions", "0").equals("0")) {
      return "";
    }
    if (Build.VERSION.SDK_INT >= 16) {}
    try
    {
      localPackageInfo = this.ˎ.getPackageManager().getPackageInfo(this.ˎ.getPackageName(), 4096);
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
  
  public String ι()
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
      LO.ˊ(this.ˎ, MT.class, T7U.ʿ, localException, this.ˊ);
    }
    return "";
  }
}
