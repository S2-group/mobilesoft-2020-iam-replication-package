package com.d;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.text.TextUtils;
import com.c.a.h;
import java.util.List;

class d
  extends PackageManager
{
  PackageManager a;
  c b;
  private PackageInfo c;
  
  private d(c paramC, PackageManager paramPackageManager)
  {
    if (paramPackageManager == null) {
      throw new IllegalArgumentException("the parameter innerPackageManager should not return null");
    }
    this.a = paramPackageManager;
    this.b = paramC;
  }
  
  public void addPackageToPreferred(String paramString)
  {
    this.a.addPackageToPreferred(paramString);
  }
  
  public boolean addPermission(PermissionInfo paramPermissionInfo)
  {
    return this.a.addPermission(paramPermissionInfo);
  }
  
  public boolean addPermissionAsync(PermissionInfo paramPermissionInfo)
  {
    return this.a.addPermissionAsync(paramPermissionInfo);
  }
  
  public void addPreferredActivity(IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName)
  {
    this.a.addPreferredActivity(paramIntentFilter, paramInt, paramArrayOfComponentName, paramComponentName);
  }
  
  public String[] canonicalToCurrentPackageNames(String[] paramArrayOfString)
  {
    return this.a.canonicalToCurrentPackageNames(paramArrayOfString);
  }
  
  public int checkPermission(String paramString1, String paramString2)
  {
    return this.a.checkPermission(paramString1, paramString2);
  }
  
  public int checkSignatures(int paramInt1, int paramInt2)
  {
    return this.a.checkSignatures(paramInt1, paramInt2);
  }
  
  public int checkSignatures(String paramString1, String paramString2)
  {
    return this.a.checkSignatures(paramString1, paramString2);
  }
  
  public void clearPackagePreferredActivities(String paramString)
  {
    this.a.clearPackagePreferredActivities(paramString);
  }
  
  public String[] currentToCanonicalPackageNames(String[] paramArrayOfString)
  {
    return this.a.currentToCanonicalPackageNames(paramArrayOfString);
  }
  
  @TargetApi(17)
  public void extendVerificationTimeout(int paramInt1, int paramInt2, long paramLong)
  {
    this.a.extendVerificationTimeout(paramInt1, paramInt2, paramLong);
  }
  
  @TargetApi(20)
  public Drawable getActivityBanner(ComponentName paramComponentName)
  {
    return this.a.getActivityBanner(paramComponentName);
  }
  
  @TargetApi(20)
  public Drawable getActivityBanner(Intent paramIntent)
  {
    return this.a.getActivityBanner(paramIntent);
  }
  
  public Drawable getActivityIcon(ComponentName paramComponentName)
  {
    return this.a.getActivityIcon(paramComponentName);
  }
  
  public Drawable getActivityIcon(Intent paramIntent)
  {
    return this.a.getActivityIcon(paramIntent);
  }
  
  public ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt)
  {
    return this.a.getActivityInfo(paramComponentName, paramInt);
  }
  
  public Drawable getActivityLogo(ComponentName paramComponentName)
  {
    return this.a.getActivityLogo(paramComponentName);
  }
  
  public Drawable getActivityLogo(Intent paramIntent)
  {
    return this.a.getActivityLogo(paramIntent);
  }
  
  public List<PermissionGroupInfo> getAllPermissionGroups(int paramInt)
  {
    return null;
  }
  
  @TargetApi(20)
  public Drawable getApplicationBanner(ApplicationInfo paramApplicationInfo)
  {
    return this.a.getApplicationBanner(paramApplicationInfo);
  }
  
  @TargetApi(20)
  public Drawable getApplicationBanner(String paramString)
  {
    return this.a.getApplicationBanner(paramString);
  }
  
  public int getApplicationEnabledSetting(String paramString)
  {
    return this.a.getApplicationEnabledSetting(paramString);
  }
  
  public Drawable getApplicationIcon(ApplicationInfo paramApplicationInfo)
  {
    return this.a.getApplicationIcon(paramApplicationInfo);
  }
  
  public Drawable getApplicationIcon(String paramString)
  {
    return this.a.getApplicationIcon(paramString);
  }
  
  public ApplicationInfo getApplicationInfo(String paramString, int paramInt)
  {
    if (paramString.equals(this.b.a))
    {
      paramString = new ApplicationInfo();
      paramString.packageName = this.b.a;
      return paramString;
    }
    return this.a.getApplicationInfo(paramString, paramInt);
  }
  
  public CharSequence getApplicationLabel(ApplicationInfo paramApplicationInfo)
  {
    if (paramApplicationInfo.packageName.equals(this.b.a)) {
      return this.b.b;
    }
    return this.a.getApplicationLabel(paramApplicationInfo);
  }
  
  public Drawable getApplicationLogo(ApplicationInfo paramApplicationInfo)
  {
    return this.a.getApplicationLogo(paramApplicationInfo);
  }
  
  public Drawable getApplicationLogo(String paramString)
  {
    return this.a.getApplicationLogo(paramString);
  }
  
  public int getComponentEnabledSetting(ComponentName paramComponentName)
  {
    return this.a.getComponentEnabledSetting(paramComponentName);
  }
  
  public Drawable getDefaultActivityIcon()
  {
    return this.a.getDefaultActivityIcon();
  }
  
  public Drawable getDrawable(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.a.getDrawable(paramString, paramInt, paramApplicationInfo);
  }
  
  public List<ApplicationInfo> getInstalledApplications(int paramInt)
  {
    return this.a.getInstalledApplications(paramInt);
  }
  
  public List<PackageInfo> getInstalledPackages(int paramInt)
  {
    return this.a.getInstalledPackages(paramInt);
  }
  
  public String getInstallerPackageName(String paramString)
  {
    String str2 = h.a();
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = this.a.getInstallerPackageName(paramString);
    }
    return str1;
  }
  
  public InstrumentationInfo getInstrumentationInfo(ComponentName paramComponentName, int paramInt)
  {
    return this.a.getInstrumentationInfo(paramComponentName, paramInt);
  }
  
  public Intent getLaunchIntentForPackage(String paramString)
  {
    return this.a.getLaunchIntentForPackage(paramString);
  }
  
  @TargetApi(21)
  public Intent getLeanbackLaunchIntentForPackage(String paramString)
  {
    return this.a.getLeanbackLaunchIntentForPackage(paramString);
  }
  
  public String getNameForUid(int paramInt)
  {
    return this.a.getNameForUid(paramInt);
  }
  
  public int[] getPackageGids(String paramString)
  {
    return this.a.getPackageGids(paramString);
  }
  
  public PackageInfo getPackageInfo(String paramString, int paramInt)
  {
    if (paramString.equals(this.b.a))
    {
      if (this.c == null)
      {
        this.c = new PackageInfo();
        this.c.packageName = this.b.a;
        this.c.versionCode = this.b.c;
        this.c.versionName = this.b.d;
        this.c.signatures = this.b.e;
      }
      return this.c;
    }
    return this.a.getPackageInfo(paramString, paramInt);
  }
  
  @TargetApi(21)
  public PackageInstaller getPackageInstaller()
  {
    return this.a.getPackageInstaller();
  }
  
  public String[] getPackagesForUid(int paramInt)
  {
    return this.a.getPackagesForUid(paramInt);
  }
  
  @TargetApi(18)
  public List<PackageInfo> getPackagesHoldingPermissions(String[] paramArrayOfString, int paramInt)
  {
    return this.a.getPackagesHoldingPermissions(paramArrayOfString, paramInt);
  }
  
  public PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt)
  {
    return this.a.getPermissionGroupInfo(paramString, paramInt);
  }
  
  public PermissionInfo getPermissionInfo(String paramString, int paramInt)
  {
    return this.a.getPermissionInfo(paramString, paramInt);
  }
  
  public int getPreferredActivities(List<IntentFilter> paramList, List<ComponentName> paramList1, String paramString)
  {
    return this.a.getPreferredActivities(paramList, paramList1, paramString);
  }
  
  public List<PackageInfo> getPreferredPackages(int paramInt)
  {
    return this.a.getPreferredPackages(paramInt);
  }
  
  public ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt)
  {
    return this.a.getProviderInfo(paramComponentName, paramInt);
  }
  
  public ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt)
  {
    return this.a.getReceiverInfo(paramComponentName, paramInt);
  }
  
  public Resources getResourcesForActivity(ComponentName paramComponentName)
  {
    return this.a.getResourcesForActivity(paramComponentName);
  }
  
  public Resources getResourcesForApplication(ApplicationInfo paramApplicationInfo)
  {
    return this.a.getResourcesForApplication(paramApplicationInfo);
  }
  
  public Resources getResourcesForApplication(String paramString)
  {
    return this.a.getResourcesForApplication(paramString);
  }
  
  public ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt)
  {
    return this.a.getServiceInfo(paramComponentName, paramInt);
  }
  
  public FeatureInfo[] getSystemAvailableFeatures()
  {
    return this.a.getSystemAvailableFeatures();
  }
  
  public String[] getSystemSharedLibraryNames()
  {
    return this.a.getSystemSharedLibraryNames();
  }
  
  public CharSequence getText(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.a.getText(paramString, paramInt, paramApplicationInfo);
  }
  
  @TargetApi(21)
  public Drawable getUserBadgedDrawableForDensity(Drawable paramDrawable, UserHandle paramUserHandle, Rect paramRect, int paramInt)
  {
    return this.a.getUserBadgedDrawableForDensity(paramDrawable, paramUserHandle, paramRect, paramInt);
  }
  
  @TargetApi(21)
  public Drawable getUserBadgedIcon(Drawable paramDrawable, UserHandle paramUserHandle)
  {
    return this.a.getUserBadgedIcon(paramDrawable, paramUserHandle);
  }
  
  @TargetApi(21)
  public CharSequence getUserBadgedLabel(CharSequence paramCharSequence, UserHandle paramUserHandle)
  {
    return this.a.getUserBadgedLabel(paramCharSequence, paramUserHandle);
  }
  
  public XmlResourceParser getXml(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.a.getXml(paramString, paramInt, paramApplicationInfo);
  }
  
  public boolean hasSystemFeature(String paramString)
  {
    return this.a.hasSystemFeature(paramString);
  }
  
  public boolean isSafeMode()
  {
    return this.a.isSafeMode();
  }
  
  public List<ResolveInfo> queryBroadcastReceivers(Intent paramIntent, int paramInt)
  {
    return this.a.queryBroadcastReceivers(paramIntent, paramInt);
  }
  
  public List<ProviderInfo> queryContentProviders(String paramString, int paramInt1, int paramInt2)
  {
    return this.a.queryContentProviders(paramString, paramInt1, paramInt2);
  }
  
  public List<InstrumentationInfo> queryInstrumentation(String paramString, int paramInt)
  {
    return this.a.queryInstrumentation(paramString, paramInt);
  }
  
  public List<ResolveInfo> queryIntentActivities(Intent paramIntent, int paramInt)
  {
    return this.a.queryIntentActivities(paramIntent, paramInt);
  }
  
  public List<ResolveInfo> queryIntentActivityOptions(ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt)
  {
    return this.a.queryIntentActivityOptions(paramComponentName, paramArrayOfIntent, paramIntent, paramInt);
  }
  
  @TargetApi(19)
  public List<ResolveInfo> queryIntentContentProviders(Intent paramIntent, int paramInt)
  {
    return this.a.queryIntentContentProviders(paramIntent, paramInt);
  }
  
  public List<ResolveInfo> queryIntentServices(Intent paramIntent, int paramInt)
  {
    return this.a.queryIntentServices(paramIntent, paramInt);
  }
  
  public List<PermissionInfo> queryPermissionsByGroup(String paramString, int paramInt)
  {
    return this.a.queryPermissionsByGroup(paramString, paramInt);
  }
  
  public void removePackageFromPreferred(String paramString)
  {
    this.a.removePackageFromPreferred(paramString);
  }
  
  public void removePermission(String paramString)
  {
    this.a.removePermission(paramString);
  }
  
  public ResolveInfo resolveActivity(Intent paramIntent, int paramInt)
  {
    return this.a.resolveActivity(paramIntent, paramInt);
  }
  
  public ProviderInfo resolveContentProvider(String paramString, int paramInt)
  {
    return this.a.resolveContentProvider(paramString, paramInt);
  }
  
  public ResolveInfo resolveService(Intent paramIntent, int paramInt)
  {
    return this.a.resolveService(paramIntent, paramInt);
  }
  
  public void setApplicationEnabledSetting(String paramString, int paramInt1, int paramInt2)
  {
    this.a.setApplicationEnabledSetting(paramString, paramInt1, paramInt2);
  }
  
  public void setComponentEnabledSetting(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    this.a.setComponentEnabledSetting(paramComponentName, paramInt1, paramInt2);
  }
  
  public void setInstallerPackageName(String paramString1, String paramString2)
  {
    this.a.setInstallerPackageName(paramString1, paramString2);
  }
  
  public void verifyPendingInstall(int paramInt1, int paramInt2)
  {
    this.a.verifyPendingInstall(paramInt1, paramInt2);
  }
}
