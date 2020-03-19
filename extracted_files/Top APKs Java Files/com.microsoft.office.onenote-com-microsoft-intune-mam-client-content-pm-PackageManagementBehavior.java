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
import java.util.List;

public abstract interface PackageManagementBehavior
{
  public abstract int checkPermission(PackageManager paramPackageManager, String paramString1, String paramString2);
  
  public abstract int checkSignatures(PackageManager paramPackageManager, int paramInt1, int paramInt2);
  
  public abstract int checkSignatures(PackageManager paramPackageManager, String paramString1, String paramString2);
  
  public abstract Drawable getActivityIcon(PackageManager paramPackageManager, ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException;
  
  public abstract Drawable getActivityIcon(PackageManager paramPackageManager, Intent paramIntent)
    throws PackageManager.NameNotFoundException;
  
  public abstract ActivityInfo getActivityInfo(PackageManager paramPackageManager, ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException;
  
  public abstract Drawable getActivityLogo(PackageManager paramPackageManager, ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException;
  
  public abstract Drawable getActivityLogo(PackageManager paramPackageManager, Intent paramIntent)
    throws PackageManager.NameNotFoundException;
  
  public abstract int getApplicationEnabledSetting(PackageManager paramPackageManager, String paramString);
  
  public abstract Drawable getApplicationIcon(PackageManager paramPackageManager, ApplicationInfo paramApplicationInfo);
  
  public abstract Drawable getApplicationIcon(PackageManager paramPackageManager, String paramString)
    throws PackageManager.NameNotFoundException;
  
  public abstract ApplicationInfo getApplicationInfo(PackageManager paramPackageManager, String paramString, int paramInt)
    throws PackageManager.NameNotFoundException;
  
  public abstract CharSequence getApplicationLabel(PackageManager paramPackageManager, ApplicationInfo paramApplicationInfo);
  
  public abstract Drawable getApplicationLogo(PackageManager paramPackageManager, ApplicationInfo paramApplicationInfo);
  
  public abstract Drawable getApplicationLogo(PackageManager paramPackageManager, String paramString)
    throws PackageManager.NameNotFoundException;
  
  public abstract int getComponentEnabledSetting(PackageManager paramPackageManager, ComponentName paramComponentName);
  
  public abstract Drawable getDrawable(PackageManager paramPackageManager, String paramString, int paramInt, ApplicationInfo paramApplicationInfo);
  
  public abstract List<ApplicationInfo> getInstalledApplications(PackageManager paramPackageManager, int paramInt);
  
  public abstract List<PackageInfo> getInstalledPackages(PackageManager paramPackageManager, int paramInt);
  
  public abstract String getInstallerPackageName(PackageManager paramPackageManager, String paramString);
  
  public abstract Intent getLaunchIntentsForPackage(PackageManager paramPackageManager, String paramString);
  
  public abstract String getNameForUid(PackageManager paramPackageManager, int paramInt);
  
  public abstract int[] getPackageGids(PackageManager paramPackageManager, String paramString)
    throws PackageManager.NameNotFoundException;
  
  public abstract int[] getPackageGids(PackageManager paramPackageManager, String paramString, int paramInt)
    throws PackageManager.NameNotFoundException;
  
  public abstract PackageInfo getPackageInfo(PackageManager paramPackageManager, String paramString, int paramInt)
    throws PackageManager.NameNotFoundException;
  
  public abstract int getPackageUid(PackageManager paramPackageManager, String paramString, int paramInt)
    throws PackageManager.NameNotFoundException;
  
  public abstract String[] getPackagesForUid(PackageManager paramPackageManager, int paramInt);
  
  public abstract List<PackageInfo> getPackagesHoldingPermissions(PackageManager paramPackageManager, String[] paramArrayOfString, int paramInt);
  
  public abstract List<PackageInfo> getPreferredPackages(PackageManager paramPackageManager, int paramInt);
  
  public abstract ProviderInfo getProviderInfo(PackageManager paramPackageManager, ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException;
  
  public abstract ActivityInfo getReceiverInfo(PackageManager paramPackageManager, ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException;
  
  public abstract Resources getResourcesForActivity(PackageManager paramPackageManager, ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException;
  
  public abstract Resources getResourcesForApplication(PackageManager paramPackageManager, ApplicationInfo paramApplicationInfo)
    throws PackageManager.NameNotFoundException;
  
  public abstract Resources getResourcesForApplication(PackageManager paramPackageManager, String paramString)
    throws PackageManager.NameNotFoundException;
  
  public abstract ServiceInfo getServiceInfo(PackageManager paramPackageManager, ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException;
  
  public abstract CharSequence getText(PackageManager paramPackageManager, String paramString, int paramInt, ApplicationInfo paramApplicationInfo);
  
  public abstract XmlResourceParser getXml(PackageManager paramPackageManager, String paramString, int paramInt, ApplicationInfo paramApplicationInfo);
  
  public abstract List<ResolveInfo> queryBroadcastReceivers(PackageManager paramPackageManager, Intent paramIntent, int paramInt);
  
  public abstract List<ProviderInfo> queryContentProviders(PackageManager paramPackageManager, String paramString, int paramInt1, int paramInt2);
  
  public abstract List<InstrumentationInfo> queryInstrumentation(PackageManager paramPackageManager, String paramString, int paramInt);
  
  public abstract List<ResolveInfo> queryIntentActivities(PackageManager paramPackageManager, Intent paramIntent, int paramInt);
  
  public abstract List<ResolveInfo> queryIntentActivityOptions(PackageManager paramPackageManager, ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt);
  
  public abstract List<ResolveInfo> queryIntentContentProviders(PackageManager paramPackageManager, Intent paramIntent, int paramInt);
  
  public abstract List<ResolveInfo> queryIntentServices(PackageManager paramPackageManager, Intent paramIntent, int paramInt);
  
  public abstract ResolveInfo resolveActivity(PackageManager paramPackageManager, Intent paramIntent, int paramInt);
  
  public abstract ProviderInfo resolveContentProvider(PackageManager paramPackageManager, String paramString, int paramInt);
  
  public abstract ResolveInfo resolveService(PackageManager paramPackageManager, Intent paramIntent, int paramInt);
  
  public abstract void setApplicationEnabledSetting(PackageManager paramPackageManager, String paramString, int paramInt1, int paramInt2);
  
  public abstract void setComponentEnabledSetting(PackageManager paramPackageManager, ComponentName paramComponentName, int paramInt1, int paramInt2);
}
