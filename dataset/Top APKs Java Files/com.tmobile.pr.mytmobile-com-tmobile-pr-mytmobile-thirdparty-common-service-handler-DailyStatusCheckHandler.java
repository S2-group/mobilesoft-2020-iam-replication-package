package com.tmobile.pr.mytmobile.thirdparty.common.service.handler;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.tmobile.pr.mytmobile.thirdparty.ThirdPartyApplication;
import com.tmobile.pr.mytmobile.thirdparty.aetherpal.AetherpalManager;
import com.tmobile.pr.mytmobile.thirdparty.common.service.ThirdPartyAppsServiceAction;
import com.tmobile.pr.mytmobile.tmocommons.logger.DebugLog;
import com.tmobile.pr.mytmobile.tmocommons.utils.PackageUtils;
import eqy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DailyStatusCheckHandler
  implements eqy<ThirdPartyAppsServiceAction>
{
  private static final String AETHERPAL_IS_NOT_ALLOWED_SKIPPING_DAILY_STATUS_CHECK = "AetherPal is not allowed, skipping daily status check";
  private static final String AETHERPAL_PACKAGE_PREFIX = "com.aetherpal";
  private static final String ENABLED_PACKAGE = "enabled %s package: %s";
  private static final String FAILED_TO_ENABLE_PACKAGE = "failed to enable %s package: %s";
  private static final String WARNING_MULTIPLE_CLIENT_PACKAGES_DETECTED = "warning: multiple %s client packages detected";
  private final Context context;
  
  public DailyStatusCheckHandler(Context paramContext)
  {
    this.context = paramContext;
  }
  
  private String getAppPrefix(ThirdPartyApplication paramThirdPartyApplication)
  {
    switch (DailyStatusCheckHandler.1.$SwitchMap$com$tmobile$pr$mytmobile$thirdparty$ThirdPartyApplication[paramThirdPartyApplication.ordinal()])
    {
    default: 
      return null;
    }
    return "com.aetherpal";
  }
  
  private List<String> getDisabledAppPackages(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.context.getPackageManager().getInstalledPackages(512).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((localPackageInfo.packageName.startsWith(paramString)) && (!localPackageInfo.applicationInfo.enabled)) {
        localArrayList.add(localPackageInfo.packageName);
      }
    }
    return localArrayList;
  }
  
  public void dispose() {}
  
  public void handleIntent(ThirdPartyAppsServiceAction paramThirdPartyAppsServiceAction, Intent paramIntent)
  {
    paramThirdPartyAppsServiceAction = ThirdPartyApplication.valueOf(paramIntent.getStringExtra("third_party_application"));
    if ((paramThirdPartyAppsServiceAction == ThirdPartyApplication.AETHERPAL) && (!AetherpalManager.isAetherpalAllowed(this.context))) {
      DebugLog.i("AetherPal is not allowed, skipping daily status check");
    }
    for (;;)
    {
      return;
      paramIntent = getDisabledAppPackages(getAppPrefix(paramThirdPartyAppsServiceAction));
      if (paramIntent.size() > 1) {
        DebugLog.w(String.format("warning: multiple %s client packages detected", new Object[] { paramThirdPartyAppsServiceAction.getSimpleName() }));
      }
      paramIntent = paramIntent.iterator();
      while (paramIntent.hasNext())
      {
        String str = (String)paramIntent.next();
        if (!PackageUtils.setApplicationEnabled(this.context, str, true)) {
          DebugLog.e(String.format("failed to enable %s package: %s", new Object[] { paramThirdPartyAppsServiceAction.getSimpleName(), str }));
        } else {
          DebugLog.i(String.format("enabled %s package: %s", new Object[] { paramThirdPartyAppsServiceAction.getSimpleName(), str }));
        }
      }
    }
  }
}
