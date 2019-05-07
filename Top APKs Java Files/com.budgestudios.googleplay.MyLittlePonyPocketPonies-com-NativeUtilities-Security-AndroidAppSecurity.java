package com.NativeUtilities.Security;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.unity3d.player.UnityPlayer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AndroidAppSecurity
{
  public AndroidAppSecurity() {}
  
  private static String[] getInstalledPackageNames()
  {
    List localList = getInstalledPackages();
    if (localList != null) {
      return getPackageNames(localList);
    }
    return null;
  }
  
  private static List<PackageInfo> getInstalledPackages()
  {
    Object localObject = UnityPlayer.currentActivity;
    if (localObject != null)
    {
      localObject = ((Context)localObject).getPackageManager();
      if (localObject != null) {
        return ((PackageManager)localObject).getInstalledPackages(128);
      }
    }
    return null;
  }
  
  private static PackageInfo getPackageInfo(String paramString, int paramInt)
  {
    Object localObject2 = null;
    try
    {
      Object localObject3 = UnityPlayer.currentActivity;
      Object localObject1 = localObject2;
      if (localObject3 != null)
      {
        localObject3 = ((Context)localObject3).getPackageManager();
        localObject1 = localObject2;
        if (localObject3 != null) {
          localObject1 = ((PackageManager)localObject3).getPackageInfo(paramString, 0);
        }
      }
      return localObject1;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return null;
  }
  
  private static String[] getPackageNames(List<PackageInfo> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramList.next();
      if (localPackageInfo != null) {
        localArrayList.add(localPackageInfo.packageName);
      }
    }
    return (String[])localArrayList.toArray(new String[0]);
  }
}
