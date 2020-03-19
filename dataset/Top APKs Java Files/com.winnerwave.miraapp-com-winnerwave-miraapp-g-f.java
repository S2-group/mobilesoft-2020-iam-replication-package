package com.winnerwave.miraapp.g;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

public class f
{
  public static String a(String paramString)
  {
    try
    {
      paramString = URLDecoder.decode(paramString, "UTF-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  public static List<ApplicationInfo> a(PackageManager paramPackageManager)
  {
    return paramPackageManager.getInstalledApplications(0);
  }
  
  public static void a(Context paramContext, String paramString)
  {
    paramContext.startActivity(paramContext.getPackageManager().getLaunchIntentForPackage(paramString));
  }
}
