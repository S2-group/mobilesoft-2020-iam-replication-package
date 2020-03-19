package com.belkin.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import java.util.Iterator;
import java.util.List;

public class NetCamUtil
{
  public NetCamUtil() {}
  
  public boolean checkNetCam(Context paramContext)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      bool1 = bool2;
      if (!paramContext.hasNext()) {
        break;
      }
    } while (!((ApplicationInfo)paramContext.next()).packageName.equals("com.belkin.android.androidbelkinnetcam"));
    boolean bool1 = true;
    return bool1;
  }
  
  public void launchNetCamApp(Context paramContext, String paramString1, String paramString2, int paramInt)
  {
    Log.i("NetCamUtil", "launchNetCamApp userName: " + paramString1 + " macAdd: " + paramString2 + " page: " + paramInt);
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.putExtra("username", paramString1);
    localIntent.putExtra("mac", paramString2);
    localIntent.putExtra("page", paramInt);
    localIntent.setComponent(new ComponentName("com.belkin.android.androidbelkinnetcam", "com.belkin.android.androidbelkinnetcam.AndroidSeedonkActivity"));
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
      showInMarket("com.belkin.android.androidbelkinnetcam", paramContext);
    }
  }
  
  public void showInMarket(String paramString, Context paramContext)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.belkin.android.androidbelkinnetcam"));
    paramString.setFlags(268435456);
    paramContext.startActivity(paramString);
  }
}
