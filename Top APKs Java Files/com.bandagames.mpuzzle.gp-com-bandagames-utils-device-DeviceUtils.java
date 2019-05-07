package com.bandagames.utils.device;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.Nullable;
import com.bandagames.utils.ResUtils;
import java.util.Iterator;
import java.util.List;
import timber.log.Timber;

public final class DeviceUtils
{
  private DeviceUtils() {}
  
  public static MarketEnum detectMarket(Context paramContext)
  {
    return detectMarket(paramContext, new MarketEnum[] { MarketEnum.AMAZON, MarketEnum.ANDROID });
  }
  
  public static MarketEnum detectMarket(Context paramContext, MarketEnum... paramVarArgs)
  {
    Object localObject1 = paramContext.getPackageManager();
    int j = 0;
    localObject1 = ((PackageManager)localObject1).getInstalledPackages(0);
    Object localObject2 = paramContext.getApplicationInfo().packageName;
    paramContext = paramContext.getPackageManager().getInstallerPackageName((String)localObject2);
    Timber.i("Installer  = %s", new Object[] { paramContext });
    int k = paramVarArgs.length;
    int i = 0;
    while (i < k)
    {
      localObject2 = paramVarArgs[i];
      if (((MarketEnum)localObject2).getPackage().equals(paramContext))
      {
        Timber.i("Market = %s", new Object[] { localObject2 });
        return localObject2;
      }
      i += 1;
    }
    k = paramVarArgs.length;
    i = j;
    while (i < k)
    {
      paramContext = paramVarArgs[i];
      localObject2 = paramContext.getPackage();
      Iterator localIterator = ((List)localObject1).iterator();
      while (localIterator.hasNext()) {
        if (((String)localObject2).equals(((PackageInfo)localIterator.next()).packageName)) {
          return paramContext;
        }
      }
      i += 1;
    }
    return null;
  }
  
  @Nullable
  private static NetworkInfo getActiveNetworkInfo()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)ResUtils.getInstance().getAppContext().getSystemService("connectivity");
    if (localConnectivityManager != null) {
      return localConnectivityManager.getActiveNetworkInfo();
    }
    return null;
  }
  
  public static boolean isDeviceSupportCameraFeature(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    return (paramContext.hasSystemFeature("android.hardware.camera")) || (paramContext.hasSystemFeature("android.hardware.camera.front"));
  }
  
  public static boolean isInternetAvailable()
  {
    NetworkInfo localNetworkInfo = getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
  }
  
  public static boolean isWifiAvailable()
  {
    NetworkInfo localNetworkInfo = getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnected()) && (localNetworkInfo.getType() == 1);
  }
}
