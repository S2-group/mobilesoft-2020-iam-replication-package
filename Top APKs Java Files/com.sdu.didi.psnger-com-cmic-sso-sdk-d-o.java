package com.cmic.sso.sdk.d;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import java.util.Iterator;
import java.util.List;

public class o
{
  public static byte[] a(Context paramContext, String paramString)
  {
    Object localObject = paramContext.getPackageManager();
    if (paramContext.getPackageName().equalsIgnoreCase(paramString)) {
      try
      {
        paramContext = ((PackageManager)localObject).getPackageInfo(paramContext.getPackageName(), 64);
        if (paramContext.packageName.equals(paramString)) {
          return paramContext.signatures[0].toByteArray();
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext.printStackTrace();
        return null;
      }
    }
    paramContext = ((PackageManager)localObject).getInstalledPackages(64).iterator();
    while (paramContext.hasNext())
    {
      localObject = (PackageInfo)paramContext.next();
      if (((PackageInfo)localObject).packageName.equals(paramString)) {
        return localObject.signatures[0].toByteArray();
      }
    }
    return null;
  }
}
