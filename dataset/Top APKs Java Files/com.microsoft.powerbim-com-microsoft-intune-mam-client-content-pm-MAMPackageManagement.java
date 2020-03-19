package com.microsoft.intune.mam.client.content.pm;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import com.microsoft.intune.mam.client.app.MAMComponents;
import java.util.List;

public final class MAMPackageManagement
{
  private static PackageManagementBehavior sCachedBehavior;
  
  private MAMPackageManagement() {}
  
  public static int checkPermission(PackageManager paramPackageManager, String paramString1, String paramString2)
  {
    return getBehavior().checkPermission(paramPackageManager, paramString1, paramString2);
  }
  
  public static int checkSignatures(PackageManager paramPackageManager, int paramInt1, int paramInt2)
  {
    return getBehavior().checkSignatures(paramPackageManager, paramInt1, paramInt2);
  }
  
  public static int checkSignatures(PackageManager paramPackageManager, String paramString1, String paramString2)
  {
    return getBehavior().checkSignatures(paramPackageManager, paramString1, paramString2);
  }
  
  public static Drawable getActivityIcon(PackageManager paramPackageManager, ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return getBehavior().getActivityIcon(paramPackageManager, paramComponentName);
  }
  
  public static Drawable getActivityIcon(PackageManager paramPackageManager, Intent paramIntent)
    throws PackageManager.NameNotFoundException
  {
    return getBehavior().getActivityIcon(paramPackageManager, paramIntent);
  }
  
  public static ActivityInfo getActivityInfo(PackageManager paramPackageManager, ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return getBehavior().getActivityInfo(paramPackageManager, paramComponentName, paramInt);
  }
  
  public static Drawable getActivityLogo(PackageManager paramPackageManager, ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return getBehavior().getActivityLogo(paramPackageManager, paramComponentName);
  }
  
  public static Drawable getActivityLogo(PackageManager paramPackageManager, Intent paramIntent)
    throws PackageManager.NameNotFoundException
  {
    return getBehavior().getActivityLogo(paramPackageManager, paramIntent);
  }
  
  public static int getApplicationEnabledSetting(PackageManager paramPackageManager, String paramString)
  {
    return getBehavior().getApplicationEnabledSetting(paramPackageManager, paramString);
  }
  
  public static Drawable getApplicationIcon(PackageManager paramPackageManager, ApplicationInfo paramApplicationInfo)
  {
    return getBehavior().getApplicationIcon(paramPackageManager, paramApplicationInfo);
  }
  
  public static Drawable getApplicationIcon(PackageManager paramPackageManager, String paramString)
    throws PackageManager.NameNotFoundException
  {
    return getBehavior().getApplicationIcon(paramPackageManager, paramString);
  }
  
  public static ApplicationInfo getApplicationInfo(PackageManager paramPackageManager, String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return getBehavior().getApplicationInfo(paramPackageManager, paramString, paramInt);
  }
  
  public static CharSequence getApplicationLabel(PackageManager paramPackageManager, ApplicationInfo paramApplicationInfo)
  {
    return getBehavior().getApplicationLabel(paramPackageManager, paramApplicationInfo);
  }
  
  public static Drawable getApplicationLogo(PackageManager paramPackageManager, ApplicationInfo paramApplicationInfo)
  {
    return getBehavior().getApplicationLogo(paramPackageManager, paramApplicationInfo);
  }
  
  public static Drawable getApplicationLogo(PackageManager paramPackageManager, String paramString)
    throws PackageManager.NameNotFoundException
  {
    return getBehavior().getApplicationLogo(paramPackageManager, paramString);
  }
  
  private static PackageManagementBehavior getBehavior()
  {
    if (sCachedBehavior != null) {
      return sCachedBehavior;
    }
    try
    {
      if (sCachedBehavior != null)
      {
        localPackageManagementBehavior = sCachedBehavior;
        return localPackageManagementBehavior;
      }
      sCachedBehavior = (PackageManagementBehavior)MAMComponents.get(PackageManagementBehavior.class);
      PackageManagementBehavior localPackageManagementBehavior = sCachedBehavior;
      return localPackageManagementBehavior;
    }
    finally {}
  }
  
  public static int getComponentEnabledSetting(PackageManager paramPackageManager, ComponentName paramComponentName)
  {
    return getBehavior().getComponentEnabledSetting(paramPackageManager, paramComponentName);
  }
  
  public static Drawable getDrawable(PackageManager paramPackageManager, String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return getBehavior().getDrawable(paramPackageManager, paramString, paramInt, paramApplicationInfo);
  }
  
  public static List<ApplicationInfo> getInstalledApplications(PackageManager paramPackageManager, int paramInt)
  {
    return getBehavior().getInstalledApplications(paramPackageManager, paramInt);
  }
  
  public static List<PackageInfo> getInstalledPackages(PackageManager paramPackageManager, int paramInt)
  {
    return getBehavior().getInstalledPackages(paramPackageManager, paramInt);
  }
  
  public static String getInstallerPackageName(PackageManager paramPackageManager, String paramString)
  {
    return getBehavior().getInstallerPackageName(paramPackageManager, paramString);
  }
  
  public static Intent getLaunchIntentForPackage(PackageManager paramPackageManager, String paramString)
  {
    return getBehavior().getLaunchIntentsForPackage(paramPackageManager, paramString);
  }
  
  public static String getNameForUid(PackageManager paramPackageManager, int paramInt)
  {
    return getBehavior().getNameForUid(paramPackageManager, paramInt);
  }
  
  public static int[] getPackageGids(PackageManager paramPackageManager, String paramString)
    throws PackageManager.NameNotFoundException
  {
    return getBehavior().getPackageGids(paramPackageManager, paramString);
  }
  
  public static int[] getPackageGids(PackageManager paramPackageManager, String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return getBehavior().getPackageGids(paramPackageManager, paramString, paramInt);
  }
  
  public static PackageInfo getPackageInfo(PackageManager paramPackageManager, String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return getBehavior().getPackageInfo(paramPackageManager, paramString, paramInt);
  }
  
  public static int getPackageUid(PackageManager paramPackageManager, String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return getBehavior().getPackageUid(paramPackageManager, paramString, paramInt);
  }
  
  public static String[] getPackagesForUid(PackageManager paramPackageManager, int paramInt)
  {
    return getBehavior().getPackagesForUid(paramPackageManager, paramInt);
  }
  
  public static List<PackageInfo> getPackagesHoldingPermissions(PackageManager paramPackageManager, String[] paramArrayOfString, int paramInt)
  {
    return getBehavior().getPackagesHoldingPermissions(paramPackageManager, paramArrayOfString, paramInt);
  }
  
  public static List<PackageInfo> getPreferredPackages(PackageManager paramPackageManager, int paramInt)
  {
    return getBehavior().getPreferredPackages(paramPackageManager, paramInt);
  }
  
  public static ProviderInfo getProviderInfo(PackageManager paramPackageManager, ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return getBehavior().getProviderInfo(paramPackageManager, paramComponentName, paramInt);
  }
  
  public static ActivityInfo getReceiverInfo(PackageManager paramPackageManager, ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return getBehavior().getReceiverInfo(paramPackageManager, paramComponentName, paramInt);
  }
  
  public static Resources getResourcesForActivity(PackageManager paramPackageManager, ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return getBehavior().getResourcesForActivity(paramPackageManager, paramComponentName);
  }
  
  public static Resources getResourcesForApplication(PackageManager paramPackageManager, ApplicationInfo paramApplicationInfo)
    throws PackageManager.NameNotFoundException
  {
    return getBehavior().getResourcesForApplication(paramPackageManager, paramApplicationInfo);
  }
  
  public static Resources getResourcesForApplication(PackageManager paramPackageManager, String paramString)
    throws PackageManager.NameNotFoundException
  {
    return getBehavior().getResourcesForApplication(paramPackageManager, paramString);
  }
  
  public static ServiceInfo getServiceInfo(PackageManager paramPackageManager, ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return getBehavior().getServiceInfo(paramPackageManager, paramComponentName, paramInt);
  }
  
  public static CharSequence getText(PackageManager paramPackageManager, String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return getBehavior().getText(paramPackageManager, paramString, paramInt, paramApplicationInfo);
  }
  
  public static XmlResourceParser getXml(PackageManager paramPackageManager, String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return getBehavior().getXml(paramPackageManager, paramString, paramInt, paramApplicationInfo);
  }
  
  public static List<ResolveInfo> queryBroadcastReceivers(PackageManager paramPackageManager, Intent paramIntent, int paramInt)
  {
    return getBehavior().queryBroadcastReceivers(paramPackageManager, paramIntent, paramInt);
  }
  
  public static List<ProviderInfo> queryContentProviders(PackageManager paramPackageManager, String paramString, int paramInt1, int paramInt2)
  {
    return getBehavior().queryContentProviders(paramPackageManager, paramString, paramInt1, paramInt2);
  }
  
  public static List<InstrumentationInfo> queryInstrumentation(PackageManager paramPackageManager, String paramString, int paramInt)
  {
    return getBehavior().queryInstrumentation(paramPackageManager, paramString, paramInt);
  }
  
  public static List<ResolveInfo> queryIntentActivities(PackageManager paramPackageManager, Intent paramIntent, int paramInt)
  {
    return getBehavior().queryIntentActivities(paramPackageManager, paramIntent, paramInt);
  }
  
  public static List<ResolveInfo> queryIntentActivityOptions(PackageManager paramPackageManager, ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt)
  {
    return getBehavior().queryIntentActivityOptions(paramPackageManager, paramComponentName, paramArrayOfIntent, paramIntent, paramInt);
  }
  
  public static List<ResolveInfo> queryIntentContentProviders(PackageManager paramPackageManager, Intent paramIntent, int paramInt)
  {
    return getBehavior().queryIntentContentProviders(paramPackageManager, paramIntent, paramInt);
  }
  
  public static List<ResolveInfo> queryIntentServices(PackageManager paramPackageManager, Intent paramIntent, int paramInt)
  {
    return getBehavior().queryIntentServices(paramPackageManager, paramIntent, paramInt);
  }
  
  public static ResolveInfo resolveActivity(PackageManager paramPackageManager, Intent paramIntent, int paramInt)
  {
    return getBehavior().resolveActivity(paramPackageManager, paramIntent, paramInt);
  }
  
  public static ProviderInfo resolveContentProvider(PackageManager paramPackageManager, String paramString, int paramInt)
  {
    return getBehavior().resolveContentProvider(paramPackageManager, paramString, paramInt);
  }
  
  public static ResolveInfo resolveService(PackageManager paramPackageManager, Intent paramIntent, int paramInt)
  {
    return getBehavior().resolveService(paramPackageManager, paramIntent, paramInt);
  }
  
  public static void setApplicationEnabledSetting(PackageManager paramPackageManager, String paramString, int paramInt1, int paramInt2)
  {
    getBehavior().setApplicationEnabledSetting(paramPackageManager, paramString, paramInt1, paramInt2);
  }
  
  public static void setComponentEnabledSetting(PackageManager paramPackageManager, ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    getBehavior().setComponentEnabledSetting(paramPackageManager, paramComponentName, paramInt1, paramInt2);
  }
}
