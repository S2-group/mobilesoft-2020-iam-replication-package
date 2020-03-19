package com.sencatech.iwawa.iwawakaraoke.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.io.PrintStream;
import java.util.List;

public class c
{
  public static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < paramContext.size())
    {
      if (((PackageInfo)paramContext.get(i)).packageName.equalsIgnoreCase(paramString))
      {
        paramContext = System.out;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("packageName:");
        localStringBuilder.append(paramString);
        paramContext.println(localStringBuilder.toString());
        return true;
      }
      i += 1;
    }
    return false;
  }
}
