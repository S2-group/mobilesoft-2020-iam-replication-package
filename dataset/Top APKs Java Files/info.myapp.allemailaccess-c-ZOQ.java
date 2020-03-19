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

public class ZOQ
{
  public boolean ˊ = false;
  private TelephonyManager ˋ;
  private Context ˎ;
  private DX1 ˏ;
  
  public ZOQ(Context paramContext)
  {
    this.ˎ = paramContext;
    this.ˏ = new DX1(paramContext);
    this.ˋ = ((TelephonyManager)this.ˎ.getSystemService("phone"));
  }
  
  private String ʼ()
  {
    try
    {
      String str = Settings.Secure.getString(this.ˎ.getContentResolver(), "android_id");
      return str;
    }
    catch (Exception localException)
    {
      JRA.ˊ(this.ˎ, ZOQ.class, Z48.ˊ, localException, this.ˊ);
    }
    return "";
  }
  
  private String ʽ()
  {
    if (this.ˎ.getApplicationContext().checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0) {
      try
      {
        String str = this.ˋ.getDeviceId();
        if (str != null) {
          return str;
        }
      }
      catch (Exception localException)
      {
        JRA.ˊ(this.ˎ, ZOQ.class, Z48.ˋ, localException, this.ˊ);
        return "";
      }
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
      JRA.ˊ(this.ˎ, ZOQ.class, Z48.ʽ, paramString, this.ˊ);
    }
    return "";
  }
  
  private String ͺ()
  {
    String str1 = "";
    do
    {
      try
      {
        Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
        if (!this.ˏ.ˊ(ELD.ˋ).equals(""))
        {
          localObject1 = this.ˏ.ˊ(ELD.ˋ);
          return localObject1;
        }
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        JRA.ˊ(this.ˎ, ZOQ.class, Z48.ˌ, "The class 'com.google.android.gms.ads.identifier.AdvertisingIdClient' not found make sure you include it", this.ˊ);
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
      if (this.ˎ.getApplicationContext() != null)
      {
        localObject2 = localClassNotFoundException;
        localObject3 = localClassNotFoundException;
        localObject4 = localClassNotFoundException;
        localObject5 = localClassNotFoundException;
        localObject1 = AdvertisingIdClient.getAdvertisingIdInfo(this.ˎ.getApplicationContext());
      }
      Object localObject6 = localObject1;
      if (localObject1 == null)
      {
        localObject2 = localClassNotFoundException;
        localObject3 = localClassNotFoundException;
        localObject4 = localClassNotFoundException;
        localObject5 = localClassNotFoundException;
        localObject6 = AdvertisingIdClient.getAdvertisingIdInfo(this.ˎ);
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
      this.ˏ.ˊ(ELD.ˋ, str2);
      return str2;
    }
    catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
    {
      JRA.ˊ(this.ˎ, ZOQ.class, Z48.ˎ, localGooglePlayServicesNotAvailableException, this.ˊ);
      return localObject2;
    }
    catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
    {
      JRA.ˊ(this.ˎ, ZOQ.class, Z48.ˏ, localGooglePlayServicesRepairableException, this.ˊ);
      return localObject3;
    }
    catch (IOException localIOException)
    {
      JRA.ˊ(this.ˎ, ZOQ.class, Z48.ᐝ, localIOException, this.ˊ);
      return localObject4;
    }
    catch (NullPointerException localNullPointerException)
    {
      JRA.ˊ(this.ˎ, ZOQ.class, Z48.ʻ, localNullPointerException, this.ˊ);
    }
    return localObject5;
  }
  
  private String ι()
  {
    int i = 0;
    for (;;)
    {
      try
      {
        Object localObject = this.ˎ.getPackageManager();
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
        JRA.ˊ(this.ˎ, ZOQ.class, Z48.ʾ, localException, this.ˊ);
        return "";
      }
    }
  }
  
  public String ʻ()
  {
    try
    {
      String str = Build.PRODUCT;
      return str;
    }
    catch (Exception localException)
    {
      JRA.ˊ(this.ˎ, ZOQ.class, Z48.ʿ, localException, this.ˊ);
    }
    return "";
  }
  
  public String ˊ()
  {
    try
    {
      String str1;
      if (Looper.getMainLooper().equals(Looper.myLooper()))
      {
        String str3 = new WebView(this.ˎ).getSettings().getUserAgentString();
        str1 = str3;
        if (!str3.equals(""))
        {
          this.ˏ.ˊ(P3D.ﹳ, str3);
          return str3;
        }
      }
      else
      {
        str1 = this.ˏ.ˊ(P3D.ﹳ);
        return str1;
      }
    }
    catch (Exception localException)
    {
      JRA.ˊ(this.ˎ, ZOQ.class, Z48.ʼ, localException, this.ˊ);
      String str2 = this.ˏ.ˊ(P3D.ﹳ);
      return str2;
    }
  }
  
  public Map<P3D, String> ˊ(Map<P3D, String> paramMap)
  {
    Object localObject = ͺ();
    if (!((String)localObject).equals("")) {}
    for (;;)
    {
      if (!((String)localObject).equals("")) {
        paramMap.put(P3D.ι, localObject);
      }
      localObject = ʼ();
      if (!((String)localObject).equals("")) {
        paramMap.put(P3D.ˏ, localObject);
      }
      localObject = ʽ();
      if (!((String)localObject).equals("")) {
        paramMap.put(P3D.ᐝ, localObject);
      }
      localObject = ͺ();
      if (!((String)localObject).equals("")) {
        paramMap.put(P3D.ʻ, localObject);
      }
      localObject = ˎ();
      if (!((String)localObject).equals("")) {
        paramMap.put(P3D.ᵔ, localObject);
      }
      localObject = ˏ();
      if (!((String)localObject).equals("")) {
        paramMap.put(P3D.ﹺ, localObject);
      }
      localObject = ᐝ();
      if (!((String)localObject).equals("")) {
        paramMap.put(P3D.ｰ, localObject);
      }
      localObject = ʻ();
      if (!((String)localObject).equals("")) {
        paramMap.put(P3D.ʳ, localObject);
      }
      return paramMap;
      String str = ʼ();
      localObject = str;
      if (str.equals(""))
      {
        str = ʽ();
        localObject = str;
        if (str.equals("")) {
          localObject = "";
        }
      }
    }
  }
  
  public String ˋ()
  {
    try
    {
      String str = this.ˎ.getApplicationInfo().packageName;
      return str;
    }
    catch (Exception localException)
    {
      JRA.ˊ(this.ˎ, ZOQ.class, Z48.ͺ, localException, this.ˊ);
    }
    return "";
  }
  
  public Map<P3D, String> ˋ(Map<P3D, String> paramMap)
  {
    String str = ˋ();
    if (!str.equals(""))
    {
      paramMap.put(P3D.ʽ, str);
      paramMap.put(P3D.ͺ, str);
    }
    str = ˊ(str);
    if (!str.equals("")) {
      paramMap.put(P3D.ˈ, str);
    }
    if (!"10".equals("")) {
      paramMap.put(P3D.ـ, "10");
    }
    if (!"1.3".equals("")) {
      paramMap.put(P3D.ᐧ, "1.3");
    }
    return paramMap;
  }
  
  public String ˎ()
  {
    try
    {
      String str = Build.VERSION.SDK_INT;
      return str;
    }
    catch (Exception localException)
    {
      JRA.ˊ(this.ˎ, ZOQ.class, Z48.ι, localException, this.ˊ);
    }
    return "";
  }
  
  public Map<P3D, String> ˎ(Map<P3D, String> paramMap)
  {
    String str = ι();
    if (!str.equals("")) {
      paramMap.put(P3D.ᐨ, str);
    }
    return paramMap;
  }
  
  public String ˏ()
  {
    try
    {
      String str = Build.MODEL;
      return str;
    }
    catch (Exception localException)
    {
      JRA.ˊ(this.ˎ, ZOQ.class, Z48.ˉ, localException, this.ˊ);
    }
    return "";
  }
  
  public Map<P3D, String> ˏ(Map<P3D, String> paramMap)
  {
    return paramMap;
  }
  
  public String ᐝ()
  {
    try
    {
      String str = Build.MANUFACTURER;
      return str;
    }
    catch (Exception localException)
    {
      JRA.ˊ(this.ˎ, ZOQ.class, Z48.ˈ, localException, this.ˊ);
    }
    return "";
  }
  
  public Map<P3D, String> ᐝ(Map<P3D, String> paramMap)
  {
    return ˏ(ˊ(ˋ(paramMap)));
  }
}
