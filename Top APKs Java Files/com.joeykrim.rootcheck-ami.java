import android.content.ComponentName;
import android.content.Context;
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
import android.os.Bundle;
import android.os.UserHandle;
import java.util.List;

public final class ami
  extends PackageManager
{
  private final Context jdField_do_of_type_AndroidContentContext;
  private final PackageManager jdField_do_of_type_AndroidContentPmPackageManager;
  
  public ami(Context paramContext)
  {
    this.jdField_do_of_type_AndroidContentContext = paramContext;
    this.jdField_do_of_type_AndroidContentPmPackageManager = paramContext.getPackageManager();
  }
  
  public final void addPackageToPreferred(String paramString)
  {
    this.jdField_do_of_type_AndroidContentPmPackageManager.addPackageToPreferred(paramString);
  }
  
  public final boolean addPermission(PermissionInfo paramPermissionInfo)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.addPermission(paramPermissionInfo);
  }
  
  public final boolean addPermissionAsync(PermissionInfo paramPermissionInfo)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.addPermissionAsync(paramPermissionInfo);
  }
  
  public final void addPreferredActivity(IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName)
  {
    this.jdField_do_of_type_AndroidContentPmPackageManager.addPreferredActivity(paramIntentFilter, paramInt, paramArrayOfComponentName, paramComponentName);
  }
  
  public final String[] canonicalToCurrentPackageNames(String[] paramArrayOfString)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.canonicalToCurrentPackageNames(paramArrayOfString);
  }
  
  public final int checkPermission(String paramString1, String paramString2)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.checkPermission(paramString1, paramString2);
  }
  
  public final int checkSignatures(int paramInt1, int paramInt2)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.checkSignatures(paramInt1, paramInt2);
  }
  
  public final int checkSignatures(String paramString1, String paramString2)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.checkSignatures(paramString1, paramString2);
  }
  
  public final void clearPackagePreferredActivities(String paramString)
  {
    this.jdField_do_of_type_AndroidContentPmPackageManager.clearPackagePreferredActivities(paramString);
  }
  
  public final String[] currentToCanonicalPackageNames(String[] paramArrayOfString)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.currentToCanonicalPackageNames(paramArrayOfString);
  }
  
  public final void extendVerificationTimeout(int paramInt1, int paramInt2, long paramLong)
  {
    this.jdField_do_of_type_AndroidContentPmPackageManager.extendVerificationTimeout(paramInt1, paramInt2, paramLong);
  }
  
  public final Drawable getActivityBanner(ComponentName paramComponentName)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getActivityBanner(paramComponentName);
  }
  
  public final Drawable getActivityBanner(Intent paramIntent)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getActivityBanner(paramIntent);
  }
  
  public final Drawable getActivityIcon(ComponentName paramComponentName)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getActivityIcon(paramComponentName);
  }
  
  public final Drawable getActivityIcon(Intent paramIntent)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getActivityIcon(paramIntent);
  }
  
  public final ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getActivityInfo(paramComponentName, paramInt);
  }
  
  public final Drawable getActivityLogo(ComponentName paramComponentName)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getActivityLogo(paramComponentName);
  }
  
  public final Drawable getActivityLogo(Intent paramIntent)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getActivityLogo(paramIntent);
  }
  
  public final List<PermissionGroupInfo> getAllPermissionGroups(int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getAllPermissionGroups(paramInt);
  }
  
  public final Drawable getApplicationBanner(ApplicationInfo paramApplicationInfo)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getApplicationBanner(paramApplicationInfo);
  }
  
  public final Drawable getApplicationBanner(String paramString)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getApplicationBanner(paramString);
  }
  
  public final int getApplicationEnabledSetting(String paramString)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getApplicationEnabledSetting(paramString);
  }
  
  public final Drawable getApplicationIcon(ApplicationInfo paramApplicationInfo)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getApplicationIcon(paramApplicationInfo);
  }
  
  public final Drawable getApplicationIcon(String paramString)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getApplicationIcon(paramString);
  }
  
  public final ApplicationInfo getApplicationInfo(String paramString, int paramInt)
  {
    ApplicationInfo localApplicationInfo = this.jdField_do_of_type_AndroidContentPmPackageManager.getApplicationInfo(paramString, paramInt);
    if ((paramString.equals(this.jdField_do_of_type_AndroidContentContext.getPackageName())) && ((paramInt & 0x80) == 128))
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
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getApplicationLabel(paramApplicationInfo);
  }
  
  public final Drawable getApplicationLogo(ApplicationInfo paramApplicationInfo)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getApplicationLogo(paramApplicationInfo);
  }
  
  public final Drawable getApplicationLogo(String paramString)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getApplicationLogo(paramString);
  }
  
  public final int getComponentEnabledSetting(ComponentName paramComponentName)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getComponentEnabledSetting(paramComponentName);
  }
  
  public final Drawable getDefaultActivityIcon()
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getDefaultActivityIcon();
  }
  
  public final Drawable getDrawable(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getDrawable(paramString, paramInt, paramApplicationInfo);
  }
  
  public final List<ApplicationInfo> getInstalledApplications(int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getInstalledApplications(paramInt);
  }
  
  public final List<PackageInfo> getInstalledPackages(int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getInstalledPackages(paramInt);
  }
  
  public final String getInstallerPackageName(String paramString)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getInstallerPackageName(paramString);
  }
  
  public final InstrumentationInfo getInstrumentationInfo(ComponentName paramComponentName, int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getInstrumentationInfo(paramComponentName, paramInt);
  }
  
  public final Intent getLaunchIntentForPackage(String paramString)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getLaunchIntentForPackage(paramString);
  }
  
  public final Intent getLeanbackLaunchIntentForPackage(String paramString)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getLeanbackLaunchIntentForPackage(paramString);
  }
  
  public final String getNameForUid(int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getNameForUid(paramInt);
  }
  
  public final int[] getPackageGids(String paramString)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getPackageGids(paramString);
  }
  
  public final PackageInfo getPackageInfo(String paramString, int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getPackageInfo(paramString, paramInt);
  }
  
  public final PackageInstaller getPackageInstaller()
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getPackageInstaller();
  }
  
  public final String[] getPackagesForUid(int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getPackagesForUid(paramInt);
  }
  
  public final List<PackageInfo> getPackagesHoldingPermissions(String[] paramArrayOfString, int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getPackagesHoldingPermissions(paramArrayOfString, paramInt);
  }
  
  public final PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getPermissionGroupInfo(paramString, paramInt);
  }
  
  public final PermissionInfo getPermissionInfo(String paramString, int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getPermissionInfo(paramString, paramInt);
  }
  
  public final int getPreferredActivities(List<IntentFilter> paramList, List<ComponentName> paramList1, String paramString)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getPreferredActivities(paramList, paramList1, paramString);
  }
  
  public final List<PackageInfo> getPreferredPackages(int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getPreferredPackages(paramInt);
  }
  
  public final ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getProviderInfo(paramComponentName, paramInt);
  }
  
  public final ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getReceiverInfo(paramComponentName, paramInt);
  }
  
  public final Resources getResourcesForActivity(ComponentName paramComponentName)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getResourcesForActivity(paramComponentName);
  }
  
  public final Resources getResourcesForApplication(ApplicationInfo paramApplicationInfo)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getResourcesForApplication(paramApplicationInfo);
  }
  
  public final Resources getResourcesForApplication(String paramString)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getResourcesForApplication(paramString);
  }
  
  public final ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getServiceInfo(paramComponentName, paramInt);
  }
  
  public final FeatureInfo[] getSystemAvailableFeatures()
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getSystemAvailableFeatures();
  }
  
  public final String[] getSystemSharedLibraryNames()
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getSystemSharedLibraryNames();
  }
  
  public final CharSequence getText(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getText(paramString, paramInt, paramApplicationInfo);
  }
  
  public final Drawable getUserBadgedDrawableForDensity(Drawable paramDrawable, UserHandle paramUserHandle, Rect paramRect, int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getUserBadgedDrawableForDensity(paramDrawable, paramUserHandle, paramRect, paramInt);
  }
  
  public final Drawable getUserBadgedIcon(Drawable paramDrawable, UserHandle paramUserHandle)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getUserBadgedIcon(paramDrawable, paramUserHandle);
  }
  
  public final CharSequence getUserBadgedLabel(CharSequence paramCharSequence, UserHandle paramUserHandle)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getUserBadgedLabel(paramCharSequence, paramUserHandle);
  }
  
  public final XmlResourceParser getXml(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.getXml(paramString, paramInt, paramApplicationInfo);
  }
  
  public final boolean hasSystemFeature(String paramString)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.hasSystemFeature(paramString);
  }
  
  public final boolean isSafeMode()
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.isSafeMode();
  }
  
  public final List<ResolveInfo> queryBroadcastReceivers(Intent paramIntent, int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.queryBroadcastReceivers(paramIntent, paramInt);
  }
  
  public final List<ProviderInfo> queryContentProviders(String paramString, int paramInt1, int paramInt2)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.queryContentProviders(paramString, paramInt1, paramInt2);
  }
  
  public final List<InstrumentationInfo> queryInstrumentation(String paramString, int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.queryInstrumentation(paramString, paramInt);
  }
  
  public final List<ResolveInfo> queryIntentActivities(Intent paramIntent, int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.queryIntentActivities(paramIntent, paramInt);
  }
  
  public final List<ResolveInfo> queryIntentActivityOptions(ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.queryIntentActivityOptions(paramComponentName, paramArrayOfIntent, paramIntent, paramInt);
  }
  
  public final List<ResolveInfo> queryIntentContentProviders(Intent paramIntent, int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.queryIntentContentProviders(paramIntent, paramInt);
  }
  
  public final List<ResolveInfo> queryIntentServices(Intent paramIntent, int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.queryIntentServices(paramIntent, paramInt);
  }
  
  public final List<PermissionInfo> queryPermissionsByGroup(String paramString, int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.queryPermissionsByGroup(paramString, paramInt);
  }
  
  public final void removePackageFromPreferred(String paramString)
  {
    this.jdField_do_of_type_AndroidContentPmPackageManager.removePackageFromPreferred(paramString);
  }
  
  public final void removePermission(String paramString)
  {
    this.jdField_do_of_type_AndroidContentPmPackageManager.removePermission(paramString);
  }
  
  public final ResolveInfo resolveActivity(Intent paramIntent, int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.resolveActivity(paramIntent, paramInt);
  }
  
  public final ProviderInfo resolveContentProvider(String paramString, int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.resolveContentProvider(paramString, paramInt);
  }
  
  public final ResolveInfo resolveService(Intent paramIntent, int paramInt)
  {
    return this.jdField_do_of_type_AndroidContentPmPackageManager.resolveService(paramIntent, paramInt);
  }
  
  public final void setApplicationEnabledSetting(String paramString, int paramInt1, int paramInt2)
  {
    this.jdField_do_of_type_AndroidContentPmPackageManager.setApplicationEnabledSetting(paramString, paramInt1, paramInt2);
  }
  
  public final void setComponentEnabledSetting(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    this.jdField_do_of_type_AndroidContentPmPackageManager.setComponentEnabledSetting(paramComponentName, paramInt1, paramInt2);
  }
  
  public final void setInstallerPackageName(String paramString1, String paramString2)
  {
    this.jdField_do_of_type_AndroidContentPmPackageManager.setInstallerPackageName(paramString1, paramString2);
  }
  
  public final void verifyPendingInstall(int paramInt1, int paramInt2)
  {
    this.jdField_do_of_type_AndroidContentPmPackageManager.verifyPendingInstall(paramInt1, paramInt2);
  }
}
