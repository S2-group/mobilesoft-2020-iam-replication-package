package qubin.de.corelibrary.g;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import java.util.List;
import qubin.de.corelibrary.c;

public class a
{
  private static final String a = a.class.getSimpleName();
  
  public a() {}
  
  public static boolean a()
  {
    boolean bool2 = false;
    List localList = c.a().getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < localList.size())
      {
        PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
        if (TextUtils.equals(localPackageInfo.packageName, "com.google.android.wearable.app"))
        {
          h.a("checkWearAppVersion: p.packageName: " + localPackageInfo.packageName, new Object[0]);
          h.a("checkWearAppVersion: p.versionName: " + localPackageInfo.versionName, new Object[0]);
          h.a("checkWearAppVersion: p.versionCode: " + localPackageInfo.versionCode, new Object[0]);
          if (localPackageInfo.versionCode >= 701889093) {
            bool1 = true;
          }
        }
      }
      else
      {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static boolean b()
  {
    boolean bool = false;
    try
    {
      PackageInfo localPackageInfo = c.a().getPackageManager().getPackageInfo("com.google.android.gms", 0);
      h.a("checkGooglePlayservicesVersion: p.packageName: " + localPackageInfo.packageName, new Object[0]);
      h.a("checkGooglePlayservicesVersion: p.versionName: " + localPackageInfo.versionName, new Object[0]);
      h.a("checkGooglePlayservicesVersion: p.versionCode: " + localPackageInfo.versionCode, new Object[0]);
      int i = localPackageInfo.versionCode;
      if (i >= 7327000) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      h.b("checkGooglePlayservicesVersion: " + localNameNotFoundException, new Object[0]);
    }
    return false;
  }
}
