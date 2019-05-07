package com.orangenose;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.unity3d.player.UnityPlayer;
import java.util.ArrayList;
import java.util.List;

public class DeepLink
{
  private static String m_TAG = "Unity";
  private static Activity m_activity = UnityPlayer.currentActivity;
  
  public DeepLink() {}
  
  public static boolean detectDownloadPackageName(String paramString)
  {
    List localList = m_activity.getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      if (i >= localList.size()) {
        return false;
      }
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      if (((localPackageInfo.applicationInfo.flags & 0x1) <= 0) && (localPackageInfo.packageName.equals(paramString)))
      {
        Log.d(m_TAG, "detectDownloadPackageName :: package name = " + localPackageInfo.packageName);
        return true;
      }
      i += 1;
    }
  }
  
  public static List<PackageInfo> getAllApps(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      if (i >= paramContext.size()) {
        return localArrayList;
      }
      PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
      if ((localPackageInfo.applicationInfo.flags & 0x1) <= 0) {
        localArrayList.add(localPackageInfo);
      }
      i += 1;
    }
  }
  
  public boolean DetectGame(String paramString)
  {
    return detectDownloadPackageName(paramString);
  }
  
  public void OpenGame(String paramString)
  {
    paramString = m_activity.getPackageManager().getLaunchIntentForPackage(paramString);
    m_activity.startActivity(paramString);
  }
}
