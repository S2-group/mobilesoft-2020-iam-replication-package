package com.tocaboca.plugin;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.tocaboca.R.bool;
import com.unity3d.player.UnityPlayer;
import java.util.Iterator;
import java.util.List;

public class AppInfo
{
  public AppInfo() {}
  
  public String getAppStore()
  {
    if (UnityPlayer.currentActivity.getResources().getBoolean(R.bool.build_for_amazon)) {
      return "Amazon";
    }
    return "GooglePlay";
  }
  
  public String getInstalledTocaBocaApps()
  {
    Object localObject = UnityPlayer.currentActivity.getPackageManager();
    String str = null;
    localObject = ((PackageManager)localObject).getInstalledApplications(128).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      if (localApplicationInfo.packageName.startsWith("com.tocaboca"))
      {
        if (str == null) {}
        for (str = "";; str = str + ",")
        {
          str = str + localApplicationInfo.packageName;
          break;
        }
      }
    }
    return str;
  }
  
  public String getInstalledTocaBocaAppsAsJSON()
  {
    Object localObject = UnityPlayer.currentActivity.getPackageManager();
    String str = "{";
    Iterator localIterator = ((PackageManager)localObject).getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (localApplicationInfo.packageName.startsWith("com.tocaboca"))
      {
        localObject = str;
        if (str.length() > 1) {
          localObject = str + ",";
        }
        str = (String)localObject + "\"" + localApplicationInfo.packageName + "\":true";
      }
    }
    return str + "}";
  }
  
  public boolean getWiFiConnected()
  {
    Object localObject = UnityPlayer.currentActivity;
    if (localObject == null) {}
    do
    {
      do
      {
        return false;
        localObject = (ConnectivityManager)((Activity)localObject).getSystemService("connectivity");
      } while (localObject == null);
      localObject = ((ConnectivityManager)localObject).getNetworkInfo(1);
    } while (localObject == null);
    return ((NetworkInfo)localObject).isConnected();
  }
}
