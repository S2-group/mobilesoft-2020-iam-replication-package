package com.yahoo.mobile.client.share.provider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.yahoo.mobile.client.share.apps.ApplicationBase;
import com.yahoo.mobile.client.share.logging.Log;
import com.yahoo.mobile.client.share.util.Util;
import java.util.List;

public class SharedDataAccessResolver
  extends BroadcastReceiver
{
  private static final String MAIL_PACKAGE_ID = ApplicationBase.getStringConfig("PACKAGE_NAME_BASE") + "mail";
  private static final String PACKAGE_DATA_PREFIX = "package:";
  public static final String TAG_SHARE_CONTENT_RESOLVER = "share.resolver";
  private static boolean sIsInit = false;
  private static boolean sIsMailInstalled = false;
  
  public SharedDataAccessResolver() {}
  
  public static String getShareAppId(boolean paramBoolean)
  {
    if ((!paramBoolean) && (sIsMailInstalled)) {
      return "mail";
    }
    return ApplicationBase.getStringConfig("APP_ID");
  }
  
  public static void init(Context paramContext)
  {
    int i;
    if (!sIsInit)
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(0);
      i = 0;
    }
    for (;;)
    {
      if (i < paramContext.size())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.get(i);
        if (MAIL_PACKAGE_ID.equals(localApplicationInfo.packageName)) {
          sIsMailInstalled = true;
        }
      }
      else
      {
        sIsInit = true;
        return;
      }
      i += 1;
    }
  }
  
  public static boolean isMailInstalled(Context paramContext)
  {
    return sIsMailInstalled;
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    init(paramContext);
    if ("android.intent.action.PACKAGE_ADDED".equals(paramIntent.getAction()))
    {
      paramContext = paramIntent.getDataString();
      if ((!Util.isEmpty(paramContext)) && (("package:" + MAIL_PACKAGE_ID).equals(paramContext)))
      {
        if (Log.sLogLevel <= 4) {
          Log.i("share.resolver", "Mail app was installed");
        }
        sIsMailInstalled = true;
      }
    }
    do
    {
      do
      {
        return;
        if (!"android.intent.action.PACKAGE_REMOVED".equals(paramIntent.getAction())) {
          break;
        }
        paramContext = paramIntent.getDataString();
      } while ((Util.isEmpty(paramContext)) || (!("package:" + MAIL_PACKAGE_ID).equals(paramContext)));
      if (Log.sLogLevel <= 4) {
        Log.i("share.resolver", "Mail app was removed and is no longer present");
      }
      sIsMailInstalled = false;
      return;
    } while (!"com.yahoo.mobile.client.android.APP_STARTUP".equals(paramIntent.getAction()));
    init(paramContext);
  }
}
