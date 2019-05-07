package com.amazon.whispersync.dcp.framework;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import com.amazon.whispersync.com.google.inject.Inject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class PackageManagerWrapper
  extends PackageManager
{
  protected PackageManager mInner;
  
  public PackageManagerWrapper()
  {
    this.mInner = null;
  }
  
  public PackageManagerWrapper(Context paramContext)
  {
    this.mInner = paramContext.getPackageManager();
  }
  
  @Inject
  public PackageManagerWrapper(PackageManager paramPackageManager)
  {
    this.mInner = paramPackageManager;
  }
  
  public void addPackageToPreferred(String paramString)
  {
    this.mInner.addPackageToPreferred(paramString);
  }
  
  public boolean addPermission(PermissionInfo paramPermissionInfo)
  {
    return this.mInner.addPermission(paramPermissionInfo);
  }
  
  public boolean addPermissionAsync(PermissionInfo paramPermissionInfo)
  {
    return this.mInner.addPermissionAsync(paramPermissionInfo);
  }
  
  public void addPreferredActivity(IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName)
  {
    this.mInner.addPreferredActivity(paramIntentFilter, paramInt, paramArrayOfComponentName, paramComponentName);
  }
  
  public String[] canonicalToCurrentPackageNames(String[] paramArrayOfString)
  {
    return this.mInner.canonicalToCurrentPackageNames(paramArrayOfString);
  }
  
  public int checkPermission(String paramString1, String paramString2)
  {
    return this.mInner.checkPermission(paramString1, paramString2);
  }
  
  public int checkSignatures(int paramInt1, int paramInt2)
  {
    return this.mInner.checkSignatures(paramInt1, paramInt2);
  }
  
  public int checkSignatures(String paramString1, String paramString2)
  {
    return this.mInner.checkSignatures(paramString1, paramString2);
  }
  
  public void clearPackagePreferredActivities(String paramString)
  {
    this.mInner.clearPackagePreferredActivities(paramString);
  }
  
  public String[] currentToCanonicalPackageNames(String[] paramArrayOfString)
  {
    return this.mInner.currentToCanonicalPackageNames(paramArrayOfString);
  }
  
  public void extendVerificationTimeout(int paramInt1, int paramInt2, long paramLong)
  {
    this.mInner.extendVerificationTimeout(paramInt1, paramInt2, paramLong);
  }
  
  public Drawable getActivityIcon(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return this.mInner.getActivityIcon(paramComponentName);
  }
  
  public Drawable getActivityIcon(Intent paramIntent)
    throws PackageManager.NameNotFoundException
  {
    return this.mInner.getActivityIcon(paramIntent);
  }
  
  public ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mInner.getActivityInfo(paramComponentName, paramInt);
  }
  
  public Drawable getActivityLogo(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return this.mInner.getActivityLogo(paramComponentName);
  }
  
  public Drawable getActivityLogo(Intent paramIntent)
    throws PackageManager.NameNotFoundException
  {
    return this.mInner.getActivityLogo(paramIntent);
  }
  
  public List<PermissionGroupInfo> getAllPermissionGroups(int paramInt)
  {
    return this.mInner.getAllPermissionGroups(paramInt);
  }
  
  public int getApplicationEnabledSetting(String paramString)
  {
    return this.mInner.getApplicationEnabledSetting(paramString);
  }
  
  public Drawable getApplicationIcon(ApplicationInfo paramApplicationInfo)
  {
    return this.mInner.getApplicationIcon(paramApplicationInfo);
  }
  
  public Drawable getApplicationIcon(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.mInner.getApplicationIcon(paramString);
  }
  
  public ApplicationInfo getApplicationInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mInner.getApplicationInfo(paramString, paramInt);
  }
  
  public CharSequence getApplicationLabel(ApplicationInfo paramApplicationInfo)
  {
    return this.mInner.getApplicationLabel(paramApplicationInfo);
  }
  
  public Drawable getApplicationLogo(ApplicationInfo paramApplicationInfo)
  {
    return this.mInner.getApplicationLogo(paramApplicationInfo);
  }
  
  public Drawable getApplicationLogo(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.mInner.getApplicationLogo(paramString);
  }
  
  public int getComponentEnabledSetting(ComponentName paramComponentName)
  {
    return this.mInner.getComponentEnabledSetting(paramComponentName);
  }
  
  public Drawable getDefaultActivityIcon()
  {
    return this.mInner.getDefaultActivityIcon();
  }
  
  public Drawable getDrawable(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.mInner.getDrawable(paramString, paramInt, paramApplicationInfo);
  }
  
  public List<ApplicationInfo> getInstalledApplications(int paramInt)
  {
    return this.mInner.getInstalledApplications(paramInt);
  }
  
  public List<PackageInfo> getInstalledPackages(int paramInt)
  {
    return this.mInner.getInstalledPackages(paramInt);
  }
  
  public String getInstallerPackageName(String paramString)
  {
    return this.mInner.getInstallerPackageName(paramString);
  }
  
  public InstrumentationInfo getInstrumentationInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mInner.getInstrumentationInfo(paramComponentName, paramInt);
  }
  
  public Intent getLaunchIntentForPackage(String paramString)
  {
    return this.mInner.getLaunchIntentForPackage(paramString);
  }
  
  public String getNameForUid(int paramInt)
  {
    return this.mInner.getNameForUid(paramInt);
  }
  
  public PackageInfo getPackageArchiveInfo(String paramString, int paramInt)
  {
    return this.mInner.getPackageArchiveInfo(paramString, paramInt);
  }
  
  public int[] getPackageGids(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.mInner.getPackageGids(paramString);
  }
  
  public PackageInfo getPackageInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mInner.getPackageInfo(paramString, paramInt);
  }
  
  public String[] getPackagesForUid(int paramInt)
  {
    return this.mInner.getPackagesForUid(paramInt);
  }
  
  public PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mInner.getPermissionGroupInfo(paramString, paramInt);
  }
  
  public PermissionInfo getPermissionInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mInner.getPermissionInfo(paramString, paramInt);
  }
  
  public int getPreferredActivities(List<IntentFilter> paramList, List<ComponentName> paramList1, String paramString)
  {
    return this.mInner.getPreferredActivities(paramList, paramList1, paramString);
  }
  
  public List<PackageInfo> getPreferredPackages(int paramInt)
  {
    return this.mInner.getPreferredPackages(paramInt);
  }
  
  public ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mInner.getProviderInfo(paramComponentName, paramInt);
  }
  
  public ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mInner.getReceiverInfo(paramComponentName, paramInt);
  }
  
  public Resources getResourcesForActivity(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return this.mInner.getResourcesForActivity(paramComponentName);
  }
  
  public Resources getResourcesForApplication(ApplicationInfo paramApplicationInfo)
    throws PackageManager.NameNotFoundException
  {
    return this.mInner.getResourcesForApplication(paramApplicationInfo);
  }
  
  public Resources getResourcesForApplication(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.mInner.getResourcesForApplication(paramString);
  }
  
  public ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mInner.getServiceInfo(paramComponentName, paramInt);
  }
  
  public FeatureInfo[] getSystemAvailableFeatures()
  {
    return this.mInner.getSystemAvailableFeatures();
  }
  
  public String[] getSystemSharedLibraryNames()
  {
    return this.mInner.getSystemSharedLibraryNames();
  }
  
  public CharSequence getText(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.mInner.getText(paramString, paramInt, paramApplicationInfo);
  }
  
  public int getUidForSharedUser(String paramString)
    throws PackageManager.NameNotFoundException
  {
    ReflectionHelper localReflectionHelper = new ReflectionHelper();
    Method localMethod = localReflectionHelper.getHiddenMethod(this.mInner.getClass(), "getUidForSharedUser");
    try
    {
      int i = ((Integer)localReflectionHelper.invoke(localMethod, this.mInner, new Object[] { paramString })).intValue();
      return i;
    }
    catch (IllegalAccessException paramString)
    {
      throw new IllegalStateException(paramString);
    }
    catch (InvocationTargetException paramString)
    {
      if ((paramString.getCause() instanceof PackageManager.NameNotFoundException)) {
        throw ((PackageManager.NameNotFoundException)paramString.getCause());
      }
      if ((paramString.getCause() instanceof RuntimeException)) {
        throw ((RuntimeException)paramString.getCause());
      }
      if ((paramString.getCause() instanceof Error)) {
        throw ((Error)paramString.getCause());
      }
      throw new IllegalStateException("Unknown checked exception", paramString);
    }
  }
  
  public XmlResourceParser getXml(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.mInner.getXml(paramString, paramInt, paramApplicationInfo);
  }
  
  public boolean hasSystemFeature(String paramString)
  {
    return this.mInner.hasSystemFeature(paramString);
  }
  
  public boolean isSafeMode()
  {
    return this.mInner.isSafeMode();
  }
  
  public List<ResolveInfo> queryBroadcastReceivers(Intent paramIntent, int paramInt)
  {
    return this.mInner.queryBroadcastReceivers(paramIntent, paramInt);
  }
  
  public List<ProviderInfo> queryContentProviders(String paramString, int paramInt1, int paramInt2)
  {
    return this.mInner.queryContentProviders(paramString, paramInt1, paramInt2);
  }
  
  public List<InstrumentationInfo> queryInstrumentation(String paramString, int paramInt)
  {
    return this.mInner.queryInstrumentation(paramString, paramInt);
  }
  
  public List<ResolveInfo> queryIntentActivities(Intent paramIntent, int paramInt)
  {
    return this.mInner.queryIntentActivities(paramIntent, paramInt);
  }
  
  public List<ResolveInfo> queryIntentActivityOptions(ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt)
  {
    return this.mInner.queryIntentActivityOptions(paramComponentName, paramArrayOfIntent, paramIntent, paramInt);
  }
  
  public List<ResolveInfo> queryIntentServices(Intent paramIntent, int paramInt)
  {
    return this.mInner.queryIntentServices(paramIntent, paramInt);
  }
  
  public List<ResolveInfo> queryIntentServicesAsUser(Intent paramIntent, int paramInt1, int paramInt2)
  {
    ReflectionHelper localReflectionHelper = new ReflectionHelper();
    Method localMethod = localReflectionHelper.getHiddenMethod(this.mInner.getClass(), "queryIntentServicesAsUser");
    try
    {
      paramIntent = (List)localReflectionHelper.invoke(localMethod, this.mInner, new Object[] { paramIntent, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
      return paramIntent;
    }
    catch (IllegalAccessException paramIntent)
    {
      throw new IllegalStateException(paramIntent);
    }
    catch (InvocationTargetException paramIntent)
    {
      if ((paramIntent.getCause() instanceof RuntimeException)) {
        throw ((RuntimeException)paramIntent.getCause());
      }
      if ((paramIntent.getCause() instanceof Error)) {
        throw ((Error)paramIntent.getCause());
      }
      throw new IllegalStateException("Unknown checked exception", paramIntent);
    }
  }
  
  public List<PermissionInfo> queryPermissionsByGroup(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mInner.queryPermissionsByGroup(paramString, paramInt);
  }
  
  public void removePackageFromPreferred(String paramString)
  {
    this.mInner.removePackageFromPreferred(paramString);
  }
  
  public void removePermission(String paramString)
  {
    this.mInner.removePermission(paramString);
  }
  
  public ResolveInfo resolveActivity(Intent paramIntent, int paramInt)
  {
    return this.mInner.resolveActivity(paramIntent, paramInt);
  }
  
  public ProviderInfo resolveContentProvider(String paramString, int paramInt)
  {
    return this.mInner.resolveContentProvider(paramString, paramInt);
  }
  
  public ResolveInfo resolveService(Intent paramIntent, int paramInt)
  {
    return this.mInner.resolveService(paramIntent, paramInt);
  }
  
  public void setApplicationEnabledSetting(String paramString, int paramInt1, int paramInt2)
  {
    this.mInner.setApplicationEnabledSetting(paramString, paramInt1, paramInt2);
  }
  
  public void setComponentEnabledSetting(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    this.mInner.setComponentEnabledSetting(paramComponentName, paramInt1, paramInt2);
  }
  
  public void setInstallerPackageName(String paramString1, String paramString2)
  {
    this.mInner.setInstallerPackageName(paramString1, paramString2);
  }
  
  public void verifyPendingInstall(int paramInt1, int paramInt2)
  {
    this.mInner.verifyPendingInstall(paramInt1, paramInt2);
  }
}
