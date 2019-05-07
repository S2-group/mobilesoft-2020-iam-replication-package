package in.datacha.classes;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.google.a.e;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.g;
import com.google.android.gms.common.h;
import in.datacha.main.DataChain;
import in.datacha.models.AppsInfo;
import in.datacha.models.User;
import in.datacha.models.UserEmail;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class d
{
  private static String a(String paramString)
  {
    try
    {
      paramString = MessageDigest.getInstance("MD5").digest(paramString.getBytes());
      StringBuilder localStringBuilder = new StringBuilder();
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        localStringBuilder.append(Integer.toHexString(paramString[i] & 0xFF | 0x100).substring(1, 3));
        i += 1;
      }
      paramString = localStringBuilder.toString();
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  static void a(Context paramContext, final String paramString)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          Object localObject1 = new Date();
          int i = Integer.parseInt(new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format((Date)localObject1));
          if (i > in.datacha.b.a.b(this.a, "Datachain_last_updates_time", 0))
          {
            in.datacha.b.a.a(this.a, "Datachain_publisher_key", paramString);
            localObject1 = null;
            try
            {
              AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.a);
              localObject1 = localInfo;
            }
            catch (h localH)
            {
              localH.printStackTrace();
            }
            catch (g localG)
            {
              localG.printStackTrace();
            }
            catch (IOException localIOException)
            {
              localIOException.printStackTrace();
            }
            if (localObject1 != null)
            {
              User localUser = new User();
              localUser.setPublisher_key(paramString);
              localUser.setAdvertising_id(((AdvertisingIdClient.Info)localObject1).getId());
              in.datacha.b.a.a(this.a, "Datachain_user_advertising_id", ((AdvertisingIdClient.Info)localObject1).getId());
              localUser.setAndroid_version(Build.VERSION.SDK_INT);
              localUser.setAndroid_version_release(Build.VERSION.RELEASE);
              localUser.setDevice_model(Build.MODEL);
              localUser.setDevice_manufacturer(Build.MANUFACTURER);
              localObject1 = this.a.getResources().getDisplayMetrics();
              int j = (int)(((DisplayMetrics)localObject1).density * 160.0F);
              Object localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append(j);
              ((StringBuilder)localObject2).append(" DPI");
              localUser.setScreen_density(((StringBuilder)localObject2).toString());
              localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append(((DisplayMetrics)localObject1).widthPixels);
              ((StringBuilder)localObject2).append(" x ");
              ((StringBuilder)localObject2).append(((DisplayMetrics)localObject1).heightPixels);
              localUser.setScreen_size(((StringBuilder)localObject2).toString());
              localObject1 = (TelephonyManager)this.a.getSystemService("phone");
              if (localObject1 != null)
              {
                localUser.setCarrier_name(((TelephonyManager)localObject1).getSimOperatorName());
                localUser.setCountry(((TelephonyManager)localObject1).getNetworkCountryIso());
              }
              localUser.setApp_package_name(this.a.getPackageName());
              localUser.setDatachain_sdk_version();
              localUser.setAndroid_id(Settings.Secure.getString(this.a.getContentResolver(), "android_id"));
              localUser.setLanguage(Locale.getDefault().getDisplayLanguage());
              localObject2 = (ConnectivityManager)this.a.getSystemService("connectivity");
              if (localObject2 != null)
              {
                localObject1 = ((ConnectivityManager)localObject2).getNetworkInfo(1);
                localObject2 = ((ConnectivityManager)localObject2).getNetworkInfo(0);
                if (((NetworkInfo)localObject1).isConnectedOrConnecting()) {}
                for (localObject1 = "Wifi";; localObject1 = "Mobile Data")
                {
                  localUser.setNetwork_type((String)localObject1);
                  break;
                  if (!((NetworkInfo)localObject2).isConnectedOrConnecting()) {
                    break;
                  }
                }
              }
              localUser.setSession_count(in.datacha.b.a.b(this.a, "Datachain_user_session_count", 0));
              localUser.setApps(d.a(this.a));
              localUser.setTimezone();
              localUser.setTime();
              localObject1 = new e();
              new in.datacha.a.a(this.a).execute(new String[] { "https://b959y1efld.execute-api.ap-south-1.amazonaws.com/prod/userdetails", paramString, ((e)localObject1).a(localUser), "Datachain_user_detail" });
              in.datacha.b.a.a(this.a, "Datachain_last_updates_time", i);
              return;
            }
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  public static void a(Context paramContext, List<String> paramList)
  {
    Object localObject = new ArrayList();
    paramList = paramList.iterator();
    String str1;
    while (paramList.hasNext())
    {
      str1 = a((String)paramList.next());
      if ((str1 != null) && (str1.length() > 0)) {
        ((List)localObject).add(str1);
      }
    }
    if (((List)localObject).size() > 0)
    {
      paramList = new e();
      String str2 = in.datacha.b.a.b(paramContext, "Datachain_user_advertising_id", "");
      str1 = in.datacha.b.a.b(paramContext, "Datachain_publisher_key", "");
      if ((str2.length() > 0) && (str1.length() > 0))
      {
        localObject = new UserEmail(str1, str2, (List)localObject);
        new in.datacha.a.a(paramContext).execute(new String[] { "https://b959y1efld.execute-api.ap-south-1.amazonaws.com/prod/useremails", str1, paramList.a(localObject), "Datachain_user_detail" });
      }
    }
  }
  
  public static void a(DataChain paramDataChain)
  {
    if (paramDataChain != null)
    {
      ((Application)paramDataChain.getContext().getApplicationContext()).registerActivityLifecycleCallbacks(new b());
      int i = in.datacha.b.a.b(paramDataChain.getContext(), "Datachain_user_session_count", 0);
      in.datacha.b.a.a(paramDataChain.getContext(), "Datachain_user_session_count", i + 1);
      in.datacha.b.a.a(paramDataChain.getContext(), "Datachain_publisher_key", paramDataChain.getPublisher_key());
      a(paramDataChain.getContext(), paramDataChain.getPublisher_key());
      if (paramDataChain.getEnableLocation().booleanValue())
      {
        in.datacha.b.a.a(paramDataChain.getContext(), "Datachain_location_update_interval", paramDataChain.getLocationUpdateInterval());
        c.a();
        return;
      }
      in.datacha.b.b.a("Location is not enabled");
    }
  }
  
  private static List<AppsInfo> b(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledApplications(0);
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = ((List)localObject).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if ((localApplicationInfo.flags & 0x1) != 1)
      {
        String str = "undefined";
        localObject = str;
        if (Build.VERSION.SDK_INT >= 26)
        {
          localObject = str;
          switch (localApplicationInfo.category)
          {
          default: 
            localObject = str;
            break;
          case 7: 
            localObject = "productivity";
            break;
          case 6: 
            localObject = "maps";
            break;
          case 5: 
            localObject = "news";
            break;
          case 4: 
            localObject = "social";
            break;
          case 3: 
            localObject = "image";
            break;
          case 2: 
            localObject = "video";
            break;
          case 1: 
            localObject = "audio";
            break;
          case 0: 
            localObject = "game";
          }
        }
        localArrayList.add(new AppsInfo(localApplicationInfo.loadLabel(paramContext.getPackageManager()).toString(), localApplicationInfo.packageName, (String)localObject));
      }
    }
    return localArrayList;
  }
  
  public static void b(Context paramContext, String paramString)
  {
    Object localObject = a(paramString);
    if ((localObject != null) && (((String)localObject).length() > 0))
    {
      paramString = new e();
      String str2 = in.datacha.b.a.b(paramContext, "Datachain_user_advertising_id", "");
      String str1 = in.datacha.b.a.b(paramContext, "Datachain_publisher_key", "");
      if ((str2.length() > 0) && (str1.length() > 0))
      {
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(localObject);
        localObject = new UserEmail(str1, str2, localArrayList);
        new in.datacha.a.a(paramContext).execute(new String[] { "https://b959y1efld.execute-api.ap-south-1.amazonaws.com/prod/useremails", str1, paramString.a(localObject), "Datachain_user_detail" });
      }
    }
  }
}
