package com.babybus.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.widget.Toast;
import java.util.Iterator;
import java.util.List;

public class WindowUtil
{
  public WindowUtil() {}
  
  public static boolean hasAnyMarketInstalled(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setData(Uri.parse("market://details?id=android.browser"));
    return paramContext.getPackageManager().queryIntentActivities(localIntent, 65536).size() != 0;
  }
  
  public static boolean isAppInstalled(Activity paramActivity, String paramString)
  {
    int i;
    if ((paramString != null) && (!"".equals(paramString)))
    {
      paramActivity = paramActivity.getPackageManager().getInstalledPackages(0);
      i = 0;
    }
    for (;;)
    {
      if (i >= paramActivity.size()) {
        return false;
      }
      if (paramString.endsWith(((PackageInfo)paramActivity.get(i)).packageName)) {
        return true;
      }
      i += 1;
    }
  }
  
  public static void launchApp(Activity paramActivity, String paramString)
  {
    launchApp(paramActivity, paramString, true);
  }
  
  public static void launchApp(Activity paramActivity, String paramString, boolean paramBoolean)
  {
    Object localObject = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject).setPackage(paramString);
    localObject = (ResolveInfo)paramActivity.getPackageManager().queryIntentActivities((Intent)localObject, 0).iterator().next();
    if (localObject != null)
    {
      paramString = ((ResolveInfo)localObject).activityInfo.packageName;
      localObject = ((ResolveInfo)localObject).activityInfo.name;
      Intent localIntent = new Intent("android.intent.action.MAIN");
      localIntent.addCategory("android.intent.category.LAUNCHER");
      localIntent.setComponent(new ComponentName(paramString, (String)localObject));
      localIntent.putExtra("launchFlag", true);
      paramActivity.startActivity(localIntent);
      if (paramBoolean) {
        paramActivity.finish();
      }
    }
  }
  
  public static void openMarketToDownload(final Activity paramActivity, String paramString)
  {
    try
    {
      paramActivity.runOnUiThread(new Runnable()
      {
        public void run()
        {
          Object localObject = Uri.parse("market://details?id=" + WindowUtil.this.trim());
          if (WindowUtil.hasAnyMarketInstalled(paramActivity))
          {
            localObject = new Intent("android.intent.action.VIEW", (Uri)localObject);
            paramActivity.startActivity((Intent)localObject);
            return;
          }
          try
          {
            Intent localIntent = new Intent();
            localIntent.setAction("android.intent.action.VIEW");
            localIntent.setData((Uri)localObject);
            paramActivity.startActivity(localIntent);
            return;
          }
          catch (Exception localException)
          {
            Toast.makeText(paramActivity, "Browser does not open.", 0).show();
          }
        }
      });
      return;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
}
