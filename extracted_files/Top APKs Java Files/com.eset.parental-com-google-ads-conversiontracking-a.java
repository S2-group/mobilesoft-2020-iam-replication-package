package com.google.ads.conversiontracking;

import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserHandle;
import ayw;
import ayw.a;
import ayx;
import ayy;
import java.io.IOException;
import java.util.List;

public class a
{
  private Context a;
  
  public a(Context paramContext)
  {
    this.a = new a(paramContext);
  }
  
  public ayw.a a()
  {
    try
    {
      ayw.a localA = ayw.a(this.a);
      return localA;
    }
    catch (ayy localAyy)
    {
      return null;
    }
    catch (ayx localAyx)
    {
      for (;;) {}
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;) {}
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
  }
  
  static class a
    extends ContextWrapper
  {
    private final a.b a;
    private final a.c b;
    
    public a(Context paramContext)
    {
      super();
      this.a = new a.b(paramContext);
      this.b = new a.c(paramContext.getResources());
    }
    
    public PackageManager getPackageManager()
    {
      return this.a;
    }
    
    public Resources getResources()
    {
      return this.b;
    }
  }
  
  static class b
    extends PackageManager
  {
    private final Context a;
    private final PackageManager b;
    
    public b(Context paramContext)
    {
      this.a = paramContext;
      this.b = paramContext.getPackageManager();
    }
    
    public void addPackageToPreferred(String paramString)
    {
      this.b.addPackageToPreferred(paramString);
    }
    
    public boolean addPermission(PermissionInfo paramPermissionInfo)
    {
      return this.b.addPermission(paramPermissionInfo);
    }
    
    public boolean addPermissionAsync(PermissionInfo paramPermissionInfo)
    {
      return this.b.addPermissionAsync(paramPermissionInfo);
    }
    
    public void addPreferredActivity(IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName)
    {
      this.b.addPreferredActivity(paramIntentFilter, paramInt, paramArrayOfComponentName, paramComponentName);
    }
    
    public String[] canonicalToCurrentPackageNames(String[] paramArrayOfString)
    {
      return this.b.canonicalToCurrentPackageNames(paramArrayOfString);
    }
    
    public int checkPermission(String paramString1, String paramString2)
    {
      return this.b.checkPermission(paramString1, paramString2);
    }
    
    public int checkSignatures(int paramInt1, int paramInt2)
    {
      return this.b.checkSignatures(paramInt1, paramInt2);
    }
    
    public int checkSignatures(String paramString1, String paramString2)
    {
      return this.b.checkSignatures(paramString1, paramString2);
    }
    
    public void clearPackagePreferredActivities(String paramString)
    {
      this.b.clearPackagePreferredActivities(paramString);
    }
    
    public String[] currentToCanonicalPackageNames(String[] paramArrayOfString)
    {
      return this.b.currentToCanonicalPackageNames(paramArrayOfString);
    }
    
    public void extendVerificationTimeout(int paramInt1, int paramInt2, long paramLong)
    {
      this.b.extendVerificationTimeout(paramInt1, paramInt2, paramLong);
    }
    
    public Drawable getActivityBanner(ComponentName paramComponentName)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getActivityBanner(paramComponentName);
    }
    
    public Drawable getActivityBanner(Intent paramIntent)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getActivityBanner(paramIntent);
    }
    
    public Drawable getActivityIcon(ComponentName paramComponentName)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getActivityIcon(paramComponentName);
    }
    
    public Drawable getActivityIcon(Intent paramIntent)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getActivityIcon(paramIntent);
    }
    
    public ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getActivityInfo(paramComponentName, paramInt);
    }
    
    public Drawable getActivityLogo(ComponentName paramComponentName)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getActivityLogo(paramComponentName);
    }
    
    public Drawable getActivityLogo(Intent paramIntent)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getActivityLogo(paramIntent);
    }
    
    public List<PermissionGroupInfo> getAllPermissionGroups(int paramInt)
    {
      return this.b.getAllPermissionGroups(paramInt);
    }
    
    public Drawable getApplicationBanner(ApplicationInfo paramApplicationInfo)
    {
      return this.b.getApplicationBanner(paramApplicationInfo);
    }
    
    public Drawable getApplicationBanner(String paramString)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getApplicationBanner(paramString);
    }
    
    public int getApplicationEnabledSetting(String paramString)
    {
      return this.b.getApplicationEnabledSetting(paramString);
    }
    
    public Drawable getApplicationIcon(ApplicationInfo paramApplicationInfo)
    {
      return this.b.getApplicationIcon(paramApplicationInfo);
    }
    
    public Drawable getApplicationIcon(String paramString)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getApplicationIcon(paramString);
    }
    
    public ApplicationInfo getApplicationInfo(String paramString, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      ApplicationInfo localApplicationInfo = this.b.getApplicationInfo(paramString, paramInt);
      if ((paramString.equals(this.a.getPackageName())) && ((paramInt & 0x80) == 128))
      {
        if (localApplicationInfo.metaData == null) {
          localApplicationInfo.metaData = new Bundle();
        }
        localApplicationInfo.metaData.putInt("com.google.android.gms.version", 4323000);
      }
      return localApplicationInfo;
    }
    
    public CharSequence getApplicationLabel(ApplicationInfo paramApplicationInfo)
    {
      return this.b.getApplicationLabel(paramApplicationInfo);
    }
    
    public Drawable getApplicationLogo(ApplicationInfo paramApplicationInfo)
    {
      return this.b.getApplicationLogo(paramApplicationInfo);
    }
    
    public Drawable getApplicationLogo(String paramString)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getApplicationLogo(paramString);
    }
    
    public int getComponentEnabledSetting(ComponentName paramComponentName)
    {
      return this.b.getComponentEnabledSetting(paramComponentName);
    }
    
    public Drawable getDefaultActivityIcon()
    {
      return this.b.getDefaultActivityIcon();
    }
    
    public Drawable getDrawable(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
    {
      return this.b.getDrawable(paramString, paramInt, paramApplicationInfo);
    }
    
    public List<ApplicationInfo> getInstalledApplications(int paramInt)
    {
      return this.b.getInstalledApplications(paramInt);
    }
    
    public List<PackageInfo> getInstalledPackages(int paramInt)
    {
      return this.b.getInstalledPackages(paramInt);
    }
    
    public String getInstallerPackageName(String paramString)
    {
      return this.b.getInstallerPackageName(paramString);
    }
    
    public InstrumentationInfo getInstrumentationInfo(ComponentName paramComponentName, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getInstrumentationInfo(paramComponentName, paramInt);
    }
    
    public Intent getLaunchIntentForPackage(String paramString)
    {
      return this.b.getLaunchIntentForPackage(paramString);
    }
    
    public Intent getLeanbackLaunchIntentForPackage(String paramString)
    {
      return this.b.getLeanbackLaunchIntentForPackage(paramString);
    }
    
    public String getNameForUid(int paramInt)
    {
      return this.b.getNameForUid(paramInt);
    }
    
    public int[] getPackageGids(String paramString)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getPackageGids(paramString);
    }
    
    public PackageInfo getPackageInfo(String paramString, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getPackageInfo(paramString, paramInt);
    }
    
    public PackageInstaller getPackageInstaller()
    {
      return this.b.getPackageInstaller();
    }
    
    public String[] getPackagesForUid(int paramInt)
    {
      return this.b.getPackagesForUid(paramInt);
    }
    
    public List<PackageInfo> getPackagesHoldingPermissions(String[] paramArrayOfString, int paramInt)
    {
      return this.b.getPackagesHoldingPermissions(paramArrayOfString, paramInt);
    }
    
    public PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getPermissionGroupInfo(paramString, paramInt);
    }
    
    public PermissionInfo getPermissionInfo(String paramString, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getPermissionInfo(paramString, paramInt);
    }
    
    public int getPreferredActivities(List<IntentFilter> paramList, List<ComponentName> paramList1, String paramString)
    {
      return this.b.getPreferredActivities(paramList, paramList1, paramString);
    }
    
    public List<PackageInfo> getPreferredPackages(int paramInt)
    {
      return this.b.getPreferredPackages(paramInt);
    }
    
    public ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getProviderInfo(paramComponentName, paramInt);
    }
    
    public ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getReceiverInfo(paramComponentName, paramInt);
    }
    
    public Resources getResourcesForActivity(ComponentName paramComponentName)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getResourcesForActivity(paramComponentName);
    }
    
    public Resources getResourcesForApplication(ApplicationInfo paramApplicationInfo)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getResourcesForApplication(paramApplicationInfo);
    }
    
    public Resources getResourcesForApplication(String paramString)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getResourcesForApplication(paramString);
    }
    
    public ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getServiceInfo(paramComponentName, paramInt);
    }
    
    public FeatureInfo[] getSystemAvailableFeatures()
    {
      return this.b.getSystemAvailableFeatures();
    }
    
    public String[] getSystemSharedLibraryNames()
    {
      return this.b.getSystemSharedLibraryNames();
    }
    
    public CharSequence getText(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
    {
      return this.b.getText(paramString, paramInt, paramApplicationInfo);
    }
    
    public Drawable getUserBadgedDrawableForDensity(Drawable paramDrawable, UserHandle paramUserHandle, Rect paramRect, int paramInt)
    {
      return this.b.getUserBadgedDrawableForDensity(paramDrawable, paramUserHandle, paramRect, paramInt);
    }
    
    public Drawable getUserBadgedIcon(Drawable paramDrawable, UserHandle paramUserHandle)
    {
      return this.b.getUserBadgedIcon(paramDrawable, paramUserHandle);
    }
    
    public CharSequence getUserBadgedLabel(CharSequence paramCharSequence, UserHandle paramUserHandle)
    {
      return this.b.getUserBadgedLabel(paramCharSequence, paramUserHandle);
    }
    
    public XmlResourceParser getXml(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
    {
      return this.b.getXml(paramString, paramInt, paramApplicationInfo);
    }
    
    public boolean hasSystemFeature(String paramString)
    {
      return this.b.hasSystemFeature(paramString);
    }
    
    public boolean isSafeMode()
    {
      return this.b.isSafeMode();
    }
    
    public List<ResolveInfo> queryBroadcastReceivers(Intent paramIntent, int paramInt)
    {
      return this.b.queryBroadcastReceivers(paramIntent, paramInt);
    }
    
    public List<ProviderInfo> queryContentProviders(String paramString, int paramInt1, int paramInt2)
    {
      return this.b.queryContentProviders(paramString, paramInt1, paramInt2);
    }
    
    public List<InstrumentationInfo> queryInstrumentation(String paramString, int paramInt)
    {
      return this.b.queryInstrumentation(paramString, paramInt);
    }
    
    public List<ResolveInfo> queryIntentActivities(Intent paramIntent, int paramInt)
    {
      return this.b.queryIntentActivities(paramIntent, paramInt);
    }
    
    public List<ResolveInfo> queryIntentActivityOptions(ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt)
    {
      return this.b.queryIntentActivityOptions(paramComponentName, paramArrayOfIntent, paramIntent, paramInt);
    }
    
    public List<ResolveInfo> queryIntentContentProviders(Intent paramIntent, int paramInt)
    {
      return this.b.queryIntentContentProviders(paramIntent, paramInt);
    }
    
    public List<ResolveInfo> queryIntentServices(Intent paramIntent, int paramInt)
    {
      return this.b.queryIntentServices(paramIntent, paramInt);
    }
    
    public List<PermissionInfo> queryPermissionsByGroup(String paramString, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.b.queryPermissionsByGroup(paramString, paramInt);
    }
    
    public void removePackageFromPreferred(String paramString)
    {
      this.b.removePackageFromPreferred(paramString);
    }
    
    public void removePermission(String paramString)
    {
      this.b.removePermission(paramString);
    }
    
    public ResolveInfo resolveActivity(Intent paramIntent, int paramInt)
    {
      return this.b.resolveActivity(paramIntent, paramInt);
    }
    
    public ProviderInfo resolveContentProvider(String paramString, int paramInt)
    {
      return this.b.resolveContentProvider(paramString, paramInt);
    }
    
    public ResolveInfo resolveService(Intent paramIntent, int paramInt)
    {
      return this.b.resolveService(paramIntent, paramInt);
    }
    
    public void setApplicationEnabledSetting(String paramString, int paramInt1, int paramInt2)
    {
      this.b.setApplicationEnabledSetting(paramString, paramInt1, paramInt2);
    }
    
    public void setComponentEnabledSetting(ComponentName paramComponentName, int paramInt1, int paramInt2)
    {
      this.b.setComponentEnabledSetting(paramComponentName, paramInt1, paramInt2);
    }
    
    public void setInstallerPackageName(String paramString1, String paramString2)
    {
      this.b.setInstallerPackageName(paramString1, paramString2);
    }
    
    public void verifyPendingInstall(int paramInt1, int paramInt2)
    {
      this.b.verifyPendingInstall(paramInt1, paramInt2);
    }
  }
  
  static class c
    extends Resources
  {
    public c(Resources paramResources)
    {
      super(paramResources.getDisplayMetrics(), paramResources.getConfiguration());
    }
    
    public String getString(int paramInt)
    {
      return "";
    }
  }
}
