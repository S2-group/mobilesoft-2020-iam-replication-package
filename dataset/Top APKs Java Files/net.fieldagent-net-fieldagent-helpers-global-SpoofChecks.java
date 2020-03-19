package net.fieldagent.helpers.global;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.provider.Settings.Secure;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SpoofChecks
{
  public SpoofChecks() {}
  
  public static boolean fieldagentHasMockLocationPermission(Context paramContext)
  {
    return getListOfMockLocationPermissionApps(paramContext).contains(paramContext.getPackageName());
  }
  
  public static ArrayList getListOfMockLocationPermissionApps(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
    for (;;)
    {
      ApplicationInfo localApplicationInfo;
      if (localIterator.hasNext()) {
        localApplicationInfo = (ApplicationInfo)localIterator.next();
      }
      try
      {
        String[] arrayOfString = localPackageManager.getPackageInfo(localApplicationInfo.packageName, 4096).requestedPermissions;
        if (arrayOfString != null)
        {
          int i = 0;
          while (i < arrayOfString.length)
          {
            if ((arrayOfString[i].equals("android.permission.ACCESS_MOCK_LOCATION")) && (!localApplicationInfo.packageName.equals(paramContext.getPackageName()))) {
              localArrayList.add(localApplicationInfo.packageName);
            }
            i += 1;
          }
          return localArrayList;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    }
  }
  
  public static boolean isMockLocationSettingsON(Context paramContext)
  {
    return !Settings.Secure.getString(paramContext.getContentResolver(), "mock_location").equals("0");
  }
  
  public static boolean isSpoofingAppInstalled(String paramString)
  {
    boolean bool2 = false;
    File localFile = new File(Environment.getDataDirectory() + "/app");
    boolean bool1 = bool2;
    if (localFile.exists())
    {
      bool1 = bool2;
      if (localFile.isDirectory())
      {
        localFile = new File(localFile + "/" + paramString + ".apk");
        bool1 = bool2;
        if (localFile.exists())
        {
          bool1 = bool2;
          if (localFile.isFile()) {
            bool1 = true;
          }
        }
      }
    }
    bool2 = bool1;
    if (!bool1)
    {
      localFile = new File(Environment.getRootDirectory() + "/app");
      bool2 = bool1;
      if (localFile.exists())
      {
        bool2 = bool1;
        if (localFile.isDirectory())
        {
          paramString = new File(localFile + "/" + paramString + ".apk");
          bool2 = bool1;
          if (paramString.exists())
          {
            bool2 = bool1;
            if (paramString.isFile()) {
              bool2 = true;
            }
          }
        }
      }
    }
    return bool2;
  }
}
