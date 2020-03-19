package com.nextmediatw.utilities;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;

public class SocialUtils
{
  public static final String classTag = "SocialUtils";
  
  public SocialUtils() {}
  
  public static boolean isLineInstalled(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext()) {
      if (((PackageInfo)paramContext.next()).packageName.contains("jp.naver.line")) {
        return true;
      }
    }
    return false;
  }
}
