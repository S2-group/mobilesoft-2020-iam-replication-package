package com.unionbank.androidui.api.data.job.rootdetection;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.mutualmobile.androidshared.utils.MMLogger;
import com.unionbank.androidui.api.data.job.UBJob;
import com.unionbank.androidui.api.data.request.rootdetection.RootDetectionRequest;
import com.unionbank.androidui.utils.UBDeviceUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class RootDetectionJob
  extends UBJob<RootDetectionRequest>
{
  private String[] a = { "/system/bin/su", "/system/xbin/su", "/sbin/su", "/system/su", "/system/bin/.ext/.su", "/system/usr/we-need-root/su-backup", "/system/xbin/mu" };
  private String[] b = { "com.noshufou.android.su", "com.thirdparty.superuser", "eu.chainfire.supersu", "com.koushikdutta.superuser", "com.zachspong.temprootremovejb", "com.ramdroid.appquarantine" };
  
  public RootDetectionJob() {}
  
  private boolean b()
  {
    boolean bool2 = false;
    String[] arrayOfString = this.a;
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        String str = arrayOfString[i];
        if (new File(str).exists())
        {
          MMLogger.a("Root Detection: ", "Found su binary: " + str);
          bool1 = true;
        }
      }
      else
      {
        return bool1;
      }
      i += 1;
    }
  }
  
  private boolean c()
  {
    Object localObject = this.mContext.getPackageManager().getInstalledApplications(128);
    ArrayList localArrayList = new ArrayList(Arrays.asList(this.b));
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      if (localArrayList.contains(localApplicationInfo.packageName))
      {
        MMLogger.a("Root Detection: ", "Found root app: " + localApplicationInfo.packageName);
        return true;
      }
    }
    return false;
  }
  
  private boolean d()
  {
    Object localObject1 = this.mContext.getPackageManager();
    try
    {
      ActivityInfo localActivityInfo;
      do
      {
        localObject1 = ((PackageManager)localObject1).getInstalledPackages(1).iterator();
        Object localObject2;
        while (!((Iterator)localObject2).hasNext())
        {
          do
          {
            if (!((Iterator)localObject1).hasNext()) {
              break;
            }
            localObject2 = (PackageInfo)((Iterator)localObject1).next();
          } while (!"com.android.settings".equalsIgnoreCase(((PackageInfo)localObject2).packageName));
          localObject2 = Arrays.asList(((PackageInfo)localObject2).activities).iterator();
        }
        localActivityInfo = (ActivityInfo)((Iterator)localObject2).next();
      } while ((!localActivityInfo.name.toLowerCase().contains("superuser")) && (!localActivityInfo.name.toLowerCase().contains("cyanogenmod")));
      MMLogger.a("Root Detection: ", "Found CyanogenMod root app " + localActivityInfo.name);
      return true;
    }
    catch (Exception localException)
    {
      MMLogger.a("Root Detection: ", localException + "", new Exception[] { localException });
    }
    return false;
  }
  
  public Object processRequest(RootDetectionRequest paramRootDetectionRequest)
  {
    if ((b()) || (c()) || (d())) {}
    for (boolean bool = true;; bool = false)
    {
      UBDeviceUtils.setIsRooted(Boolean.valueOf(bool));
      return null;
    }
  }
}
