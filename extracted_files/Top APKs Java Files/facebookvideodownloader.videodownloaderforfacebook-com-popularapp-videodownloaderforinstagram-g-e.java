package com.popularapp.videodownloaderforinstagram.g;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"NewApi"})
public class e
{
  private static e a;
  
  private e() {}
  
  public static e a()
  {
    try
    {
      if (a == null) {
        a = new e();
      }
      e localE = a;
      return localE;
    }
    finally {}
  }
  
  public boolean a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(8192);
      if ((paramContext != null) && (paramContext.size() > 0))
      {
        paramContext = paramContext.iterator();
        boolean bool;
        do
        {
          PackageInfo localPackageInfo;
          do
          {
            if (!paramContext.hasNext()) {
              break;
            }
            localPackageInfo = (PackageInfo)paramContext.next();
          } while (TextUtils.isEmpty(localPackageInfo.packageName));
          bool = localPackageInfo.packageName.equals("com.google.android.gm");
        } while (!bool);
        return true;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return false;
    }
    catch (Error paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public boolean b(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(8192);
      if ((paramContext != null) && (paramContext.size() > 0))
      {
        paramContext = paramContext.iterator();
        boolean bool;
        do
        {
          PackageInfo localPackageInfo;
          do
          {
            if (!paramContext.hasNext()) {
              break;
            }
            localPackageInfo = (PackageInfo)paramContext.next();
          } while (TextUtils.isEmpty(localPackageInfo.packageName));
          bool = localPackageInfo.packageName.equals("com.android.email");
        } while (!bool);
        return true;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return false;
    }
    catch (Error paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
}
