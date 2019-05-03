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
import java.io.IOException;
import java.util.List;

public final class e
{
  private Context a;
  
  public e(Context paramContext)
  {
    this.a = new a(paramContext);
  }
  
  public final p.a a()
  {
    try
    {
      p.a localA = p.a(this.a);
      return localA;
    }
    catch (s localS)
    {
      return null;
    }
    catch (q localQ)
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
  
  static final class a
    extends ContextWrapper
  {
    private final e.b a;
    private final e.c b;
    
    public a(Context paramContext)
    {
      super();
      this.a = new e.b(paramContext);
      this.b = new e.c(paramContext.getResources());
    }
    
    public final PackageManager getPackageManager()
    {
      return this.a;
    }
    
    public final Resources getResources()
    {
      return this.b;
    }
  }
  
  static final class b
    extends PackageManager
  {
    private final Context a;
    private final PackageManager b;
    
    public b(Context paramContext)
    {
      this.a = paramContext;
      this.b = paramContext.getPackageManager();
    }
    
    public final void addPackageToPreferred(String paramString)
    {
      this.b.addPackageToPreferred(paramString);
    }
    
    public final boolean addPermission(PermissionInfo paramPermissionInfo)
    {
      return this.b.addPermission(paramPermissionInfo);
    }
    
    public final boolean addPermissionAsync(PermissionInfo paramPermissionInfo)
    {
      return this.b.addPermissionAsync(paramPermissionInfo);
    }
    
    public final void addPreferredActivity(IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName)
    {
      this.b.addPreferredActivity(paramIntentFilter, paramInt, paramArrayOfComponentName, paramComponentName);
    }
    
    public final String[] canonicalToCurrentPackageNames(String[] paramArrayOfString)
    {
      return this.b.canonicalToCurrentPackageNames(paramArrayOfString);
    }
    
    public final int checkPermission(String paramString1, String paramString2)
    {
      return this.b.checkPermission(paramString1, paramString2);
    }
    
    public final int checkSignatures(int paramInt1, int paramInt2)
    {
      return this.b.checkSignatures(paramInt1, paramInt2);
    }
    
    public final int checkSignatures(String paramString1, String paramString2)
    {
      return this.b.checkSignatures(paramString1, paramString2);
    }
    
    public final void clearPackagePreferredActivities(String paramString)
    {
      this.b.clearPackagePreferredActivities(paramString);
    }
    
    public final String[] currentToCanonicalPackageNames(String[] paramArrayOfString)
    {
      return this.b.currentToCanonicalPackageNames(paramArrayOfString);
    }
    
    public final void extendVerificationTimeout(int paramInt1, int paramInt2, long paramLong)
    {
      this.b.extendVerificationTimeout(paramInt1, paramInt2, paramLong);
    }
    
    public final Drawable getActivityBanner(ComponentName paramComponentName)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getActivityBanner(paramComponentName);
    }
    
    public final Drawable getActivityBanner(Intent paramIntent)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getActivityBanner(paramIntent);
    }
    
    public final Drawable getActivityIcon(ComponentName paramComponentName)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getActivityIcon(paramComponentName);
    }
    
    public final Drawable getActivityIcon(Intent paramIntent)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getActivityIcon(paramIntent);
    }
    
    public final ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getActivityInfo(paramComponentName, paramInt);
    }
    
    public final Drawable getActivityLogo(ComponentName paramComponentName)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getActivityLogo(paramComponentName);
    }
    
    public final Drawable getActivityLogo(Intent paramIntent)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getActivityLogo(paramIntent);
    }
    
    public final List<PermissionGroupInfo> getAllPermissionGroups(int paramInt)
    {
      return this.b.getAllPermissionGroups(paramInt);
    }
    
    public final Drawable getApplicationBanner(ApplicationInfo paramApplicationInfo)
    {
      return this.b.getApplicationBanner(paramApplicationInfo);
    }
    
    public final Drawable getApplicationBanner(String paramString)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getApplicationBanner(paramString);
    }
    
    public final int getApplicationEnabledSetting(String paramString)
    {
      return this.b.getApplicationEnabledSetting(paramString);
    }
    
    public final Drawable getApplicationIcon(ApplicationInfo paramApplicationInfo)
    {
      return this.b.getApplicationIcon(paramApplicationInfo);
    }
    
    public final Drawable getApplicationIcon(String paramString)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getApplicationIcon(paramString);
    }
    
    public final ApplicationInfo getApplicationInfo(String paramString, int paramInt)
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
    
    public final CharSequence getApplicationLabel(ApplicationInfo paramApplicationInfo)
    {
      return this.b.getApplicationLabel(paramApplicationInfo);
    }
    
    public final Drawable getApplicationLogo(ApplicationInfo paramApplicationInfo)
    {
      return this.b.getApplicationLogo(paramApplicationInfo);
    }
    
    public final Drawable getApplicationLogo(String paramString)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getApplicationLogo(paramString);
    }
    
    public final int getComponentEnabledSetting(ComponentName paramComponentName)
    {
      return this.b.getComponentEnabledSetting(paramComponentName);
    }
    
    public final Drawable getDefaultActivityIcon()
    {
      return this.b.getDefaultActivityIcon();
    }
    
    public final Drawable getDrawable(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
    {
      return this.b.getDrawable(paramString, paramInt, paramApplicationInfo);
    }
    
    public final List<ApplicationInfo> getInstalledApplications(int paramInt)
    {
      return this.b.getInstalledApplications(paramInt);
    }
    
    public final List<PackageInfo> getInstalledPackages(int paramInt)
    {
      return this.b.getInstalledPackages(paramInt);
    }
    
    public final String getInstallerPackageName(String paramString)
    {
      return this.b.getInstallerPackageName(paramString);
    }
    
    public final InstrumentationInfo getInstrumentationInfo(ComponentName paramComponentName, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getInstrumentationInfo(paramComponentName, paramInt);
    }
    
    public final Intent getLaunchIntentForPackage(String paramString)
    {
      return this.b.getLaunchIntentForPackage(paramString);
    }
    
    public final Intent getLeanbackLaunchIntentForPackage(String paramString)
    {
      return this.b.getLeanbackLaunchIntentForPackage(paramString);
    }
    
    public final String getNameForUid(int paramInt)
    {
      return this.b.getNameForUid(paramInt);
    }
    
    public final int[] getPackageGids(String paramString)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getPackageGids(paramString);
    }
    
    public final PackageInfo getPackageInfo(String paramString, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getPackageInfo(paramString, paramInt);
    }
    
    public final PackageInstaller getPackageInstaller()
    {
      return this.b.getPackageInstaller();
    }
    
    public final String[] getPackagesForUid(int paramInt)
    {
      return this.b.getPackagesForUid(paramInt);
    }
    
    public final List<PackageInfo> getPackagesHoldingPermissions(String[] paramArrayOfString, int paramInt)
    {
      return this.b.getPackagesHoldingPermissions(paramArrayOfString, paramInt);
    }
    
    public final PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getPermissionGroupInfo(paramString, paramInt);
    }
    
    public final PermissionInfo getPermissionInfo(String paramString, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getPermissionInfo(paramString, paramInt);
    }
    
    public final int getPreferredActivities(List<IntentFilter> paramList, List<ComponentName> paramList1, String paramString)
    {
      return this.b.getPreferredActivities(paramList, paramList1, paramString);
    }
    
    public final List<PackageInfo> getPreferredPackages(int paramInt)
    {
      return this.b.getPreferredPackages(paramInt);
    }
    
    public final ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getProviderInfo(paramComponentName, paramInt);
    }
    
    public final ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getReceiverInfo(paramComponentName, paramInt);
    }
    
    public final Resources getResourcesForActivity(ComponentName paramComponentName)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getResourcesForActivity(paramComponentName);
    }
    
    public final Resources getResourcesForApplication(ApplicationInfo paramApplicationInfo)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getResourcesForApplication(paramApplicationInfo);
    }
    
    public final Resources getResourcesForApplication(String paramString)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getResourcesForApplication(paramString);
    }
    
    public final ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.b.getServiceInfo(paramComponentName, paramInt);
    }
    
    public final FeatureInfo[] getSystemAvailableFeatures()
    {
      return this.b.getSystemAvailableFeatures();
    }
    
    public final String[] getSystemSharedLibraryNames()
    {
      return this.b.getSystemSharedLibraryNames();
    }
    
    public final CharSequence getText(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
    {
      return this.b.getText(paramString, paramInt, paramApplicationInfo);
    }
    
    public final Drawable getUserBadgedDrawableForDensity(Drawable paramDrawable, UserHandle paramUserHandle, Rect paramRect, int paramInt)
    {
      return this.b.getUserBadgedDrawableForDensity(paramDrawable, paramUserHandle, paramRect, paramInt);
    }
    
    public final Drawable getUserBadgedIcon(Drawable paramDrawable, UserHandle paramUserHandle)
    {
      return this.b.getUserBadgedIcon(paramDrawable, paramUserHandle);
    }
    
    public final CharSequence getUserBadgedLabel(CharSequence paramCharSequence, UserHandle paramUserHandle)
    {
      return this.b.getUserBadgedLabel(paramCharSequence, paramUserHandle);
    }
    
    public final XmlResourceParser getXml(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
    {
      return this.b.getXml(paramString, paramInt, paramApplicationInfo);
    }
    
    public final boolean hasSystemFeature(String paramString)
    {
      return this.b.hasSystemFeature(paramString);
    }
    
    public final boolean isSafeMode()
    {
      return this.b.isSafeMode();
    }
    
    public final List<ResolveInfo> queryBroadcastReceivers(Intent paramIntent, int paramInt)
    {
      return this.b.queryBroadcastReceivers(paramIntent, paramInt);
    }
    
    public final List<ProviderInfo> queryContentProviders(String paramString, int paramInt1, int paramInt2)
    {
      return this.b.queryContentProviders(paramString, paramInt1, paramInt2);
    }
    
    public final List<InstrumentationInfo> queryInstrumentation(String paramString, int paramInt)
    {
      return this.b.queryInstrumentation(paramString, paramInt);
    }
    
    public final List<ResolveInfo> queryIntentActivities(Intent paramIntent, int paramInt)
    {
      return this.b.queryIntentActivities(paramIntent, paramInt);
    }
    
    public final List<ResolveInfo> queryIntentActivityOptions(ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt)
    {
      return this.b.queryIntentActivityOptions(paramComponentName, paramArrayOfIntent, paramIntent, paramInt);
    }
    
    public final List<ResolveInfo> queryIntentContentProviders(Intent paramIntent, int paramInt)
    {
      return this.b.queryIntentContentProviders(paramIntent, paramInt);
    }
    
    public final List<ResolveInfo> queryIntentServices(Intent paramIntent, int paramInt)
    {
      return this.b.queryIntentServices(paramIntent, paramInt);
    }
    
    public final List<PermissionInfo> queryPermissionsByGroup(String paramString, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.b.queryPermissionsByGroup(paramString, paramInt);
    }
    
    public final void removePackageFromPreferred(String paramString)
    {
      this.b.removePackageFromPreferred(paramString);
    }
    
    public final void removePermission(String paramString)
    {
      this.b.removePermission(paramString);
    }
    
    public final ResolveInfo resolveActivity(Intent paramIntent, int paramInt)
    {
      return this.b.resolveActivity(paramIntent, paramInt);
    }
    
    public final ProviderInfo resolveContentProvider(String paramString, int paramInt)
    {
      return this.b.resolveContentProvider(paramString, paramInt);
    }
    
    public final ResolveInfo resolveService(Intent paramIntent, int paramInt)
    {
      return this.b.resolveService(paramIntent, paramInt);
    }
    
    public final void setApplicationEnabledSetting(String paramString, int paramInt1, int paramInt2)
    {
      this.b.setApplicationEnabledSetting(paramString, paramInt1, paramInt2);
    }
    
    public final void setComponentEnabledSetting(ComponentName paramComponentName, int paramInt1, int paramInt2)
    {
      this.b.setComponentEnabledSetting(paramComponentName, paramInt1, paramInt2);
    }
    
    public final void setInstallerPackageName(String paramString1, String paramString2)
    {
      this.b.setInstallerPackageName(paramString1, paramString2);
    }
    
    public final void verifyPendingInstall(int paramInt1, int paramInt2)
    {
      this.b.verifyPendingInstall(paramInt1, paramInt2);
    }
  }
  
  static final class c
    extends Resources
  {
    public c(Resources paramResources)
    {
      super(paramResources.getDisplayMetrics(), paramResources.getConfiguration());
    }
    
    public final String getString(int paramInt)
    {
      return "";
    }
  }
}
