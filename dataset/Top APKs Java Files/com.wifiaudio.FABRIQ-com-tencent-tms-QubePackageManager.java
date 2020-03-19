package com.tencent.tms;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.tencent.tms.device.compat.LauncherActivityInfoCompat;
import com.tencent.tms.device.compat.LauncherAppsCompat;
import com.tencent.tms.device.compat.UserHandleCompat;
import com.tencent.tms.engine.statistics.UserStatAction;
import com.tencent.tms.qube.display.QubeDisplayManager;
import com.tencent.tms.utils.PublicDeviceUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QubePackageManager
{
  private static LauncherAppsCompat mLauncherAppsCompat;
  
  public QubePackageManager() {}
  
  public static Drawable getActivityIcon(Context paramContext, ComponentName paramComponentName)
  {
    Context localContext = null;
    try
    {
      Drawable localDrawable = paramContext.getPackageManager().getActivityIcon(paramComponentName);
      return localDrawable;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      throw paramContext;
    }
    catch (Exception localException)
    {
      PublicDeviceUtils.resetIPackageManager(paramContext);
      try
      {
        paramContext = paramContext.getPackageManager().getActivityIcon(paramComponentName);
        localContext = paramContext;
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_387");
        return paramContext;
      }
      catch (Exception paramContext)
      {
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_388");
      }
    }
    return localContext;
  }
  
  public static Drawable getActivityIcon(Context paramContext, String paramString1, String paramString2)
  {
    return getActivityIcon(paramContext, new ComponentName(paramString1, paramString2));
  }
  
  public static ActivityInfo getActivityInfo(Context paramContext, ComponentName paramComponentName, int paramInt)
  {
    Context localContext = null;
    try
    {
      ActivityInfo localActivityInfo = paramContext.getPackageManager().getActivityInfo(paramComponentName, paramInt);
      return localActivityInfo;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      throw paramContext;
    }
    catch (Exception localException)
    {
      PublicDeviceUtils.resetIPackageManager(paramContext);
      try
      {
        paramContext = paramContext.getPackageManager().getActivityInfo(paramComponentName, paramInt);
        localContext = paramContext;
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_387");
        return paramContext;
      }
      catch (Exception paramContext)
      {
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_388");
      }
    }
    return localContext;
  }
  
  public static Drawable getApplicationIcon(Context paramContext, ApplicationInfo paramApplicationInfo)
  {
    Context localContext = null;
    try
    {
      Drawable localDrawable = paramContext.getPackageManager().getApplicationIcon(paramApplicationInfo);
      return localDrawable;
    }
    catch (Exception localException)
    {
      PublicDeviceUtils.resetIPackageManager(paramContext);
      try
      {
        paramContext = paramContext.getPackageManager().getApplicationIcon(paramApplicationInfo);
        localContext = paramContext;
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_387");
        return paramContext;
      }
      catch (Exception paramContext)
      {
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_388");
      }
    }
    return localContext;
  }
  
  public static ApplicationInfo getApplicationInfo(Context paramContext, String paramString, int paramInt)
  {
    Context localContext = null;
    try
    {
      ApplicationInfo localApplicationInfo = paramContext.getPackageManager().getApplicationInfo(paramString, paramInt);
      return localApplicationInfo;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      throw paramContext;
    }
    catch (Exception localException)
    {
      PublicDeviceUtils.resetIPackageManager(paramContext);
      try
      {
        paramContext = paramContext.getPackageManager().getApplicationInfo(paramString, paramInt);
        localContext = paramContext;
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_387");
        return paramContext;
      }
      catch (Exception paramContext)
      {
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_388");
      }
    }
    return localContext;
  }
  
  public static int getComponentEnabledSetting(Context paramContext, ComponentName paramComponentName)
  {
    int i = 1;
    try
    {
      j = paramContext.getPackageManager().getComponentEnabledSetting(paramComponentName);
      return j;
    }
    catch (Exception localException)
    {
      int j;
      PublicDeviceUtils.resetIPackageManager(paramContext);
      try
      {
        j = paramContext.getPackageManager().getComponentEnabledSetting(paramComponentName);
        i = j;
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_387");
        return j;
      }
      catch (Exception paramContext)
      {
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_388");
      }
    }
    return i;
  }
  
  public static List<PackageInfo> getInstalledPackages(Context paramContext, int paramInt)
  {
    localObject = null;
    try
    {
      List localList = paramContext.getPackageManager().getInstalledPackages(paramInt);
      paramContext = localList;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        PublicDeviceUtils.resetIPackageManager(paramContext);
        try
        {
          paramContext = paramContext.getPackageManager().getInstalledPackages(paramInt);
          localObject = paramContext;
          UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_387");
        }
        catch (Exception paramContext)
        {
          UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_388");
          paramContext = (Context)localObject;
        }
      }
    }
    localObject = paramContext;
    if (paramContext == null) {
      localObject = new ArrayList();
    }
    return localObject;
  }
  
  public static Intent getLaunchIntentForPackage(Context paramContext, String paramString)
  {
    Context localContext = null;
    try
    {
      Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
      return localIntent;
    }
    catch (Exception localException)
    {
      PublicDeviceUtils.resetIPackageManager(paramContext);
      try
      {
        paramContext = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
        localContext = paramContext;
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_387");
        return paramContext;
      }
      catch (Exception paramContext)
      {
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_388");
      }
    }
    return localContext;
  }
  
  public static PackageInfo getPackageArchiveInfo(Context paramContext, String paramString, int paramInt)
  {
    Context localContext = null;
    try
    {
      PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageArchiveInfo(paramString, paramInt);
      return localPackageInfo;
    }
    catch (Throwable localThrowable)
    {
      PublicDeviceUtils.resetIPackageManager(paramContext);
      try
      {
        paramContext = paramContext.getPackageManager().getPackageArchiveInfo(paramString, paramInt);
        localContext = paramContext;
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_387");
        return paramContext;
      }
      catch (Throwable paramContext)
      {
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_388");
      }
    }
    return localContext;
  }
  
  public static PackageInfo getPackageInfo(Context paramContext, String paramString, int paramInt)
  {
    Context localContext = null;
    try
    {
      PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(paramString, paramInt);
      return localPackageInfo;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      throw paramContext;
    }
    catch (Exception localException)
    {
      PublicDeviceUtils.resetIPackageManager(paramContext);
      try
      {
        paramContext = paramContext.getPackageManager().getPackageInfo(paramString, paramInt);
        localContext = paramContext;
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_387");
        return paramContext;
      }
      catch (Exception paramContext)
      {
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_388");
      }
    }
    return localContext;
  }
  
  public static String[] getPackagesForUid(Context paramContext, int paramInt)
  {
    localObject = null;
    try
    {
      String[] arrayOfString = paramContext.getPackageManager().getPackagesForUid(paramInt);
      paramContext = arrayOfString;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        PublicDeviceUtils.resetIPackageManager(paramContext);
        try
        {
          paramContext = paramContext.getPackageManager().getPackagesForUid(paramInt);
          localObject = paramContext;
          UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_387");
        }
        catch (Exception paramContext)
        {
          UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_388");
          paramContext = (Context)localObject;
        }
      }
    }
    localObject = paramContext;
    if (paramContext == null) {
      localObject = new String[0];
    }
    return localObject;
  }
  
  public static int getPreferredActivities(Context paramContext, List<IntentFilter> paramList, List<ComponentName> paramList1, String paramString)
  {
    int i = 0;
    try
    {
      j = paramContext.getPackageManager().getPreferredActivities(paramList, paramList1, paramString);
      return j;
    }
    catch (Exception localException)
    {
      int j;
      PublicDeviceUtils.resetIPackageManager(paramContext);
      try
      {
        j = paramContext.getPackageManager().getPreferredActivities(paramList, paramList1, paramString);
        i = j;
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_387");
        return j;
      }
      catch (Exception paramContext)
      {
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_388");
      }
    }
    return i;
  }
  
  public static Resources getResourcesForApplication(Context paramContext, ApplicationInfo paramApplicationInfo)
  {
    return getResourcesForApplication(paramContext, paramApplicationInfo.packageName);
  }
  
  public static Resources getResourcesForApplication(Context paramContext, String paramString)
  {
    localObject = null;
    try
    {
      Resources localResources = paramContext.getPackageManager().getResourcesForApplication(paramString);
      localObject = localResources;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      throw paramContext;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        PublicDeviceUtils.resetIPackageManager(paramContext);
        try
        {
          paramString = paramContext.getPackageManager().getResourcesForApplication(paramString);
          localObject = paramString;
          UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_387");
          localObject = paramString;
        }
        catch (Exception paramString)
        {
          UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_388");
        }
      }
    }
    if (localObject != null) {
      QubeDisplayManager.adaptResources((Resources)localObject, paramContext);
    }
    return localObject;
  }
  
  public static FeatureInfo[] getSystemAvailableFeatures(Context paramContext)
  {
    Context localContext = null;
    try
    {
      FeatureInfo[] arrayOfFeatureInfo = paramContext.getPackageManager().getSystemAvailableFeatures();
      return arrayOfFeatureInfo;
    }
    catch (Exception localException)
    {
      PublicDeviceUtils.resetIPackageManager(paramContext);
      try
      {
        paramContext = paramContext.getPackageManager().getSystemAvailableFeatures();
        localContext = paramContext;
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_387");
        return paramContext;
      }
      catch (Exception paramContext)
      {
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_388");
      }
    }
    return localContext;
  }
  
  public static boolean hasApplicationExist(Context paramContext, String paramString)
  {
    try
    {
      getPackageInfo(paramContext, paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static List<ResolveInfo> queryBroadcastReceivers(Context paramContext, Intent paramIntent, int paramInt)
  {
    localContext = null;
    try
    {
      List localList = paramContext.getPackageManager().queryBroadcastReceivers(paramIntent, paramInt);
      paramContext = localList;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        PublicDeviceUtils.resetIPackageManager(paramContext);
        try
        {
          paramContext = paramContext.getPackageManager().queryBroadcastReceivers(paramIntent, paramInt);
          localContext = paramContext;
          UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_387");
        }
        catch (Exception paramContext)
        {
          UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_388");
          paramContext = localContext;
        }
      }
    }
    paramIntent = paramContext;
    if (paramContext == null) {
      paramIntent = new ArrayList();
    }
    return paramIntent;
  }
  
  public static List<ProviderInfo> queryContentProviders(Context paramContext, String paramString, int paramInt1, int paramInt2)
  {
    localContext = null;
    try
    {
      List localList = paramContext.getPackageManager().queryContentProviders(paramString, paramInt1, paramInt2);
      paramContext = localList;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        PublicDeviceUtils.resetIPackageManager(paramContext);
        try
        {
          paramContext = paramContext.getPackageManager().queryContentProviders(paramString, paramInt1, paramInt2);
          localContext = paramContext;
          UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_387");
        }
        catch (Exception paramContext)
        {
          UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_388");
          paramContext = localContext;
        }
      }
    }
    paramString = paramContext;
    if (paramContext == null) {
      paramString = new ArrayList();
    }
    return paramString;
  }
  
  public static List<ResolveInfo> queryIntentActivities(Context paramContext, Intent paramIntent, int paramInt)
  {
    localContext = null;
    try
    {
      List localList = paramContext.getPackageManager().queryIntentActivities(paramIntent, paramInt);
      paramContext = localList;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        PublicDeviceUtils.resetIPackageManager(paramContext);
        try
        {
          paramContext = paramContext.getPackageManager().queryIntentActivities(paramIntent, paramInt);
          localContext = paramContext;
          UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_387");
        }
        catch (Exception paramContext)
        {
          UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_388");
          paramContext = localContext;
        }
      }
    }
    paramIntent = paramContext;
    if (paramContext == null) {
      paramIntent = new ArrayList();
    }
    return paramIntent;
  }
  
  public static List<ResolveInfo> queryIntentServices(Context paramContext, Intent paramIntent, int paramInt)
  {
    localContext = null;
    try
    {
      List localList = paramContext.getPackageManager().queryIntentServices(paramIntent, paramInt);
      paramContext = localList;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        PublicDeviceUtils.resetIPackageManager(paramContext);
        try
        {
          paramContext = paramContext.getPackageManager().queryIntentServices(paramIntent, paramInt);
          localContext = paramContext;
          UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_387");
        }
        catch (Exception paramContext)
        {
          UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_388");
          paramContext = localContext;
        }
      }
    }
    paramIntent = paramContext;
    if (paramContext == null) {
      paramIntent = new ArrayList();
    }
    return paramIntent;
  }
  
  public static List<LauncherActivityInfoCompat> queryLauncherActivities(Context paramContext, String paramString)
  {
    try
    {
      if (mLauncherAppsCompat == null) {
        mLauncherAppsCompat = LauncherAppsCompat.getInstance(paramContext);
      }
      paramContext = UserHandleCompat.myUserHandle();
      paramContext = mLauncherAppsCompat.getActivityList(paramString, paramContext);
      return paramContext;
    }
    finally {}
  }
  
  public static LauncherActivityInfoCompat queryLauncherActivity(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = queryLauncherActivities(paramContext, paramString1);
    if ((paramContext == null) || (paramContext.size() == 0)) {
      return null;
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      paramString1 = (LauncherActivityInfoCompat)paramContext.next();
      if (TextUtils.equals(paramString2, paramString1.getComponentName().getClassName())) {
        return paramString1;
      }
    }
    return null;
  }
  
  public static ResolveInfo resolveActivity(Context paramContext, Intent paramIntent, int paramInt)
  {
    Context localContext = null;
    try
    {
      ResolveInfo localResolveInfo = paramContext.getPackageManager().resolveActivity(paramIntent, paramInt);
      return localResolveInfo;
    }
    catch (Exception localException)
    {
      PublicDeviceUtils.resetIPackageManager(paramContext);
      try
      {
        paramContext = paramContext.getPackageManager().resolveActivity(paramIntent, paramInt);
        localContext = paramContext;
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_387");
        return paramContext;
      }
      catch (Exception paramContext)
      {
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_388");
      }
    }
    return localContext;
  }
  
  public static void setApplicationEnabledSetting(Context paramContext, String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      paramContext.getPackageManager().setApplicationEnabledSetting(paramString, paramInt1, paramInt2);
      return;
    }
    catch (Exception localException)
    {
      PublicDeviceUtils.resetIPackageManager(paramContext);
      try
      {
        paramContext.getPackageManager().setApplicationEnabledSetting(paramString, paramInt1, paramInt2);
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_387");
        return;
      }
      catch (Exception paramContext)
      {
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_388");
      }
    }
  }
  
  public static void setComponentEnabledSetting(Context paramContext, ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    try
    {
      paramContext.getPackageManager().setComponentEnabledSetting(paramComponentName, paramInt1, paramInt2);
      return;
    }
    catch (Exception localException)
    {
      PublicDeviceUtils.resetIPackageManager(paramContext);
      try
      {
        paramContext.getPackageManager().setComponentEnabledSetting(paramComponentName, paramInt1, paramInt2);
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_387");
        return;
      }
      catch (Exception paramContext)
      {
        UserStatAction.triggerUserActionCntByWifi("QLAUNCHER_WIFI_COUNT_388");
      }
    }
  }
}
