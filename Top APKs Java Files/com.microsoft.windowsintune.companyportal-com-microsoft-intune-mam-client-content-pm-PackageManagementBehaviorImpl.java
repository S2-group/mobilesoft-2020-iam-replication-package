package com.microsoft.intune.mam.client.content.pm;

import android.annotation.TargetApi;
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
import com.microsoft.intune.mam.client.app.SystemServiceTracker;
import com.microsoft.intune.mam.client.content.MAMContext;
import com.microsoft.intune.mam.log.MAMLogger;
import com.microsoft.intune.mam.log.MAMLoggerProvider;
import java.util.List;

public class PackageManagementBehaviorImpl
  implements PackageManagementBehavior
{
  private static final MAMLogger LOGGER = MAMLoggerProvider.getLogger(PackageManagementBehaviorImpl.class);
  private final SystemServiceTracker mSystemServiceTracker;
  
  public PackageManagementBehaviorImpl(SystemServiceTracker paramSystemServiceTracker)
  {
    this.mSystemServiceTracker = paramSystemServiceTracker;
  }
  
  private MAMPackageManager getMAMPackageManager(PackageManager paramPackageManager)
  {
    paramPackageManager = this.mSystemServiceTracker.get(paramPackageManager);
    if (paramPackageManager == null)
    {
      LOGGER.severe("Unable to find MAMContext for PackageManager");
      return null;
    }
    return paramPackageManager.getMAMPackageManager();
  }
  
  public int checkPermission(PackageManager paramPackageManager, String paramString1, String paramString2)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.checkPermission(paramString1, paramString2);
    }
    return paramPackageManager.checkPermission(paramString1, paramString2);
  }
  
  public int checkSignatures(PackageManager paramPackageManager, int paramInt1, int paramInt2)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.checkSignatures(paramInt1, paramInt2);
    }
    return paramPackageManager.checkSignatures(paramInt1, paramInt2);
  }
  
  public int checkSignatures(PackageManager paramPackageManager, String paramString1, String paramString2)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.checkSignatures(paramString1, paramString2);
    }
    return paramPackageManager.checkSignatures(paramString1, paramString2);
  }
  
  public Drawable getActivityIcon(PackageManager paramPackageManager, ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getActivityIcon(paramComponentName);
    }
    return paramPackageManager.getActivityIcon(paramComponentName);
  }
  
  public Drawable getActivityIcon(PackageManager paramPackageManager, Intent paramIntent)
    throws PackageManager.NameNotFoundException
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getActivityIcon(paramIntent);
    }
    return paramPackageManager.getActivityIcon(paramIntent);
  }
  
  public ActivityInfo getActivityInfo(PackageManager paramPackageManager, ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getActivityInfo(paramComponentName, paramInt);
    }
    return paramPackageManager.getActivityInfo(paramComponentName, paramInt);
  }
  
  public Drawable getActivityLogo(PackageManager paramPackageManager, ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getActivityLogo(paramComponentName);
    }
    return paramPackageManager.getActivityLogo(paramComponentName);
  }
  
  public Drawable getActivityLogo(PackageManager paramPackageManager, Intent paramIntent)
    throws PackageManager.NameNotFoundException
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getActivityLogo(paramIntent);
    }
    return paramPackageManager.getActivityLogo(paramIntent);
  }
  
  public int getApplicationEnabledSetting(PackageManager paramPackageManager, String paramString)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getApplicationEnabledSetting(paramString);
    }
    return paramPackageManager.getApplicationEnabledSetting(paramString);
  }
  
  public Drawable getApplicationIcon(PackageManager paramPackageManager, ApplicationInfo paramApplicationInfo)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getApplicationIcon(paramApplicationInfo);
    }
    return paramPackageManager.getApplicationIcon(paramApplicationInfo);
  }
  
  public Drawable getApplicationIcon(PackageManager paramPackageManager, String paramString)
    throws PackageManager.NameNotFoundException
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getApplicationIcon(paramString);
    }
    return paramPackageManager.getApplicationIcon(paramString);
  }
  
  public ApplicationInfo getApplicationInfo(PackageManager paramPackageManager, String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getApplicationInfo(paramString, paramInt);
    }
    return paramPackageManager.getApplicationInfo(paramString, paramInt);
  }
  
  public CharSequence getApplicationLabel(PackageManager paramPackageManager, ApplicationInfo paramApplicationInfo)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getApplicationLabel(paramApplicationInfo);
    }
    return paramPackageManager.getApplicationLabel(paramApplicationInfo);
  }
  
  public Drawable getApplicationLogo(PackageManager paramPackageManager, ApplicationInfo paramApplicationInfo)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getApplicationLogo(paramApplicationInfo);
    }
    return paramPackageManager.getApplicationLogo(paramApplicationInfo);
  }
  
  public Drawable getApplicationLogo(PackageManager paramPackageManager, String paramString)
    throws PackageManager.NameNotFoundException
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getApplicationLogo(paramString);
    }
    return paramPackageManager.getApplicationLogo(paramString);
  }
  
  public int getComponentEnabledSetting(PackageManager paramPackageManager, ComponentName paramComponentName)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getComponentEnabledSetting(paramComponentName);
    }
    return paramPackageManager.getComponentEnabledSetting(paramComponentName);
  }
  
  public Drawable getDrawable(PackageManager paramPackageManager, String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getDrawable(paramString, paramInt, paramApplicationInfo);
    }
    return paramPackageManager.getDrawable(paramString, paramInt, paramApplicationInfo);
  }
  
  public List<ApplicationInfo> getInstalledApplications(PackageManager paramPackageManager, int paramInt)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getInstalledApplications(paramInt);
    }
    return paramPackageManager.getInstalledApplications(paramInt);
  }
  
  public List<PackageInfo> getInstalledPackages(PackageManager paramPackageManager, int paramInt)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getInstalledPackages(paramInt);
    }
    return paramPackageManager.getInstalledPackages(paramInt);
  }
  
  public String getInstallerPackageName(PackageManager paramPackageManager, String paramString)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getInstallerPackageName(paramString);
    }
    return paramPackageManager.getInstallerPackageName(paramString);
  }
  
  public Intent getLaunchIntentsForPackage(PackageManager paramPackageManager, String paramString)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getLaunchIntentForPackage(paramString);
    }
    return paramPackageManager.getLaunchIntentForPackage(paramString);
  }
  
  public String getNameForUid(PackageManager paramPackageManager, int paramInt)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getNameForUid(paramInt);
    }
    return paramPackageManager.getNameForUid(paramInt);
  }
  
  public int[] getPackageGids(PackageManager paramPackageManager, String paramString)
    throws PackageManager.NameNotFoundException
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getPackageGids(paramString);
    }
    return paramPackageManager.getPackageGids(paramString);
  }
  
  @TargetApi(24)
  public int[] getPackageGids(PackageManager paramPackageManager, String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getPackageGids(paramString, paramInt);
    }
    return paramPackageManager.getPackageGids(paramString, paramInt);
  }
  
  public PackageInfo getPackageInfo(PackageManager paramPackageManager, String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getPackageInfo(paramString, paramInt);
    }
    return paramPackageManager.getPackageInfo(paramString, paramInt);
  }
  
  @TargetApi(24)
  public int getPackageUid(PackageManager paramPackageManager, String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getPackageUid(paramString, paramInt);
    }
    return paramPackageManager.getPackageUid(paramString, paramInt);
  }
  
  public String[] getPackagesForUid(PackageManager paramPackageManager, int paramInt)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getPackagesForUid(paramInt);
    }
    return paramPackageManager.getPackagesForUid(paramInt);
  }
  
  @TargetApi(18)
  public List<PackageInfo> getPackagesHoldingPermissions(PackageManager paramPackageManager, String[] paramArrayOfString, int paramInt)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getPackagesHoldingPermissions(paramArrayOfString, paramInt);
    }
    return paramPackageManager.getPackagesHoldingPermissions(paramArrayOfString, paramInt);
  }
  
  public List<PackageInfo> getPreferredPackages(PackageManager paramPackageManager, int paramInt)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getPreferredPackages(paramInt);
    }
    return paramPackageManager.getPreferredPackages(paramInt);
  }
  
  public ProviderInfo getProviderInfo(PackageManager paramPackageManager, ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getProviderInfo(paramComponentName, paramInt);
    }
    return paramPackageManager.getProviderInfo(paramComponentName, paramInt);
  }
  
  public ActivityInfo getReceiverInfo(PackageManager paramPackageManager, ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getReceiverInfo(paramComponentName, paramInt);
    }
    return paramPackageManager.getReceiverInfo(paramComponentName, paramInt);
  }
  
  public Resources getResourcesForActivity(PackageManager paramPackageManager, ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getResourcesForActivity(paramComponentName);
    }
    return paramPackageManager.getResourcesForActivity(paramComponentName);
  }
  
  public Resources getResourcesForApplication(PackageManager paramPackageManager, ApplicationInfo paramApplicationInfo)
    throws PackageManager.NameNotFoundException
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getResourcesForApplication(paramApplicationInfo);
    }
    return paramPackageManager.getResourcesForApplication(paramApplicationInfo);
  }
  
  public Resources getResourcesForApplication(PackageManager paramPackageManager, String paramString)
    throws PackageManager.NameNotFoundException
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getResourcesForApplication(paramString);
    }
    return paramPackageManager.getResourcesForApplication(paramString);
  }
  
  public ServiceInfo getServiceInfo(PackageManager paramPackageManager, ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getServiceInfo(paramComponentName, paramInt);
    }
    return paramPackageManager.getServiceInfo(paramComponentName, paramInt);
  }
  
  public CharSequence getText(PackageManager paramPackageManager, String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getText(paramString, paramInt, paramApplicationInfo);
    }
    return paramPackageManager.getText(paramString, paramInt, paramApplicationInfo);
  }
  
  public XmlResourceParser getXml(PackageManager paramPackageManager, String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.getXml(paramString, paramInt, paramApplicationInfo);
    }
    return paramPackageManager.getXml(paramString, paramInt, paramApplicationInfo);
  }
  
  public List<ResolveInfo> queryBroadcastReceivers(PackageManager paramPackageManager, Intent paramIntent, int paramInt)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.queryBroadcastReceivers(paramIntent, paramInt);
    }
    return paramPackageManager.queryBroadcastReceivers(paramIntent, paramInt);
  }
  
  public List<ProviderInfo> queryContentProviders(PackageManager paramPackageManager, String paramString, int paramInt1, int paramInt2)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.queryContentProviders(paramString, paramInt1, paramInt2);
    }
    return paramPackageManager.queryContentProviders(paramString, paramInt1, paramInt2);
  }
  
  public List<InstrumentationInfo> queryInstrumentation(PackageManager paramPackageManager, String paramString, int paramInt)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.queryInstrumentation(paramString, paramInt);
    }
    return paramPackageManager.queryInstrumentation(paramString, paramInt);
  }
  
  public List<ResolveInfo> queryIntentActivities(PackageManager paramPackageManager, Intent paramIntent, int paramInt)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.queryIntentActivities(paramIntent, paramInt);
    }
    return paramPackageManager.queryIntentActivities(paramIntent, paramInt);
  }
  
  public List<ResolveInfo> queryIntentActivityOptions(PackageManager paramPackageManager, ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.queryIntentActivityOptions(paramComponentName, paramArrayOfIntent, paramIntent, paramInt);
    }
    return paramPackageManager.queryIntentActivityOptions(paramComponentName, paramArrayOfIntent, paramIntent, paramInt);
  }
  
  @TargetApi(19)
  public List<ResolveInfo> queryIntentContentProviders(PackageManager paramPackageManager, Intent paramIntent, int paramInt)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.queryIntentContentProviders(paramIntent, paramInt);
    }
    return paramPackageManager.queryIntentContentProviders(paramIntent, paramInt);
  }
  
  public List<ResolveInfo> queryIntentServices(PackageManager paramPackageManager, Intent paramIntent, int paramInt)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.queryIntentServices(paramIntent, paramInt);
    }
    return paramPackageManager.queryIntentServices(paramIntent, paramInt);
  }
  
  public ResolveInfo resolveActivity(PackageManager paramPackageManager, Intent paramIntent, int paramInt)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.resolveActivity(paramIntent, paramInt);
    }
    return paramPackageManager.resolveActivity(paramIntent, paramInt);
  }
  
  public ProviderInfo resolveContentProvider(PackageManager paramPackageManager, String paramString, int paramInt)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.resolveContentProvider(paramString, paramInt);
    }
    return paramPackageManager.resolveContentProvider(paramString, paramInt);
  }
  
  public ResolveInfo resolveService(PackageManager paramPackageManager, Intent paramIntent, int paramInt)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null) {
      return localMAMPackageManager.resolveService(paramIntent, paramInt);
    }
    return paramPackageManager.resolveService(paramIntent, paramInt);
  }
  
  public void setApplicationEnabledSetting(PackageManager paramPackageManager, String paramString, int paramInt1, int paramInt2)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null)
    {
      localMAMPackageManager.setApplicationEnabledSetting(paramString, paramInt1, paramInt2);
      return;
    }
    paramPackageManager.setApplicationEnabledSetting(paramString, paramInt1, paramInt2);
  }
  
  public void setComponentEnabledSetting(PackageManager paramPackageManager, ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    MAMPackageManager localMAMPackageManager = getMAMPackageManager(paramPackageManager);
    if (localMAMPackageManager != null)
    {
      localMAMPackageManager.setComponentEnabledSetting(paramComponentName, paramInt1, paramInt2);
      return;
    }
    paramPackageManager.setComponentEnabledSetting(paramComponentName, paramInt1, paramInt2);
  }
}
