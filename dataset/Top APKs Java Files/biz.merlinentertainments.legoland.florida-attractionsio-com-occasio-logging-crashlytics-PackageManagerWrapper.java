package attractionsio.com.occasio.logging.crashlytics;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ChangedPackages;
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
import android.content.pm.SharedLibraryInfo;
import android.content.pm.VersionedPackage;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import java.util.List;

class PackageManagerWrapper
  extends PackageManager
{
  private final PackageManager packageManager;
  
  public PackageManagerWrapper(PackageManager paramPackageManager)
  {
    this.packageManager = paramPackageManager;
  }
  
  public void addPackageToPreferred(String paramString)
  {
    this.packageManager.addPackageToPreferred(paramString);
  }
  
  public boolean addPermission(PermissionInfo paramPermissionInfo)
  {
    return this.packageManager.addPermission(paramPermissionInfo);
  }
  
  public boolean addPermissionAsync(PermissionInfo paramPermissionInfo)
  {
    return this.packageManager.addPermissionAsync(paramPermissionInfo);
  }
  
  public void addPreferredActivity(IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName)
  {
    this.packageManager.addPreferredActivity(paramIntentFilter, paramInt, paramArrayOfComponentName, paramComponentName);
  }
  
  @RequiresApi(api=26)
  public boolean canRequestPackageInstalls()
  {
    return this.packageManager.canRequestPackageInstalls();
  }
  
  public String[] canonicalToCurrentPackageNames(String[] paramArrayOfString)
  {
    return this.packageManager.canonicalToCurrentPackageNames(paramArrayOfString);
  }
  
  public int checkPermission(String paramString1, String paramString2)
  {
    return this.packageManager.checkPermission(paramString1, paramString2);
  }
  
  public int checkSignatures(int paramInt1, int paramInt2)
  {
    return this.packageManager.checkSignatures(paramInt1, paramInt2);
  }
  
  public int checkSignatures(String paramString1, String paramString2)
  {
    return this.packageManager.checkSignatures(paramString1, paramString2);
  }
  
  @RequiresApi(api=26)
  public void clearInstantAppCookie()
  {
    this.packageManager.clearInstantAppCookie();
  }
  
  public void clearPackagePreferredActivities(String paramString)
  {
    this.packageManager.clearPackagePreferredActivities(paramString);
  }
  
  public String[] currentToCanonicalPackageNames(String[] paramArrayOfString)
  {
    return this.packageManager.currentToCanonicalPackageNames(paramArrayOfString);
  }
  
  @RequiresApi(api=17)
  public void extendVerificationTimeout(int paramInt1, int paramInt2, long paramLong)
  {
    this.packageManager.extendVerificationTimeout(paramInt1, paramInt2, paramLong);
  }
  
  @RequiresApi(api=20)
  public Drawable getActivityBanner(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getActivityBanner(paramComponentName);
  }
  
  @RequiresApi(api=20)
  public Drawable getActivityBanner(Intent paramIntent)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getActivityBanner(paramIntent);
  }
  
  public Drawable getActivityIcon(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getActivityIcon(paramComponentName);
  }
  
  public Drawable getActivityIcon(Intent paramIntent)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getActivityIcon(paramIntent);
  }
  
  public ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getActivityInfo(paramComponentName, paramInt);
  }
  
  public Drawable getActivityLogo(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getActivityLogo(paramComponentName);
  }
  
  public Drawable getActivityLogo(Intent paramIntent)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getActivityLogo(paramIntent);
  }
  
  public List<PermissionGroupInfo> getAllPermissionGroups(int paramInt)
  {
    return this.packageManager.getAllPermissionGroups(paramInt);
  }
  
  @RequiresApi(api=20)
  public Drawable getApplicationBanner(ApplicationInfo paramApplicationInfo)
  {
    return this.packageManager.getApplicationBanner(paramApplicationInfo);
  }
  
  @RequiresApi(api=20)
  public Drawable getApplicationBanner(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getApplicationBanner(paramString);
  }
  
  public int getApplicationEnabledSetting(String paramString)
  {
    return this.packageManager.getApplicationEnabledSetting(paramString);
  }
  
  public Drawable getApplicationIcon(ApplicationInfo paramApplicationInfo)
  {
    return this.packageManager.getApplicationIcon(paramApplicationInfo);
  }
  
  public Drawable getApplicationIcon(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getApplicationIcon(paramString);
  }
  
  public ApplicationInfo getApplicationInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getApplicationInfo(paramString, paramInt);
  }
  
  public CharSequence getApplicationLabel(ApplicationInfo paramApplicationInfo)
  {
    return this.packageManager.getApplicationLabel(paramApplicationInfo);
  }
  
  public Drawable getApplicationLogo(ApplicationInfo paramApplicationInfo)
  {
    return this.packageManager.getApplicationLogo(paramApplicationInfo);
  }
  
  public Drawable getApplicationLogo(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getApplicationLogo(paramString);
  }
  
  @Nullable
  @RequiresApi(api=26)
  public ChangedPackages getChangedPackages(int paramInt)
  {
    return this.packageManager.getChangedPackages(paramInt);
  }
  
  public int getComponentEnabledSetting(ComponentName paramComponentName)
  {
    return this.packageManager.getComponentEnabledSetting(paramComponentName);
  }
  
  public Drawable getDefaultActivityIcon()
  {
    return this.packageManager.getDefaultActivityIcon();
  }
  
  public Drawable getDrawable(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.packageManager.getDrawable(paramString, paramInt, paramApplicationInfo);
  }
  
  public List<ApplicationInfo> getInstalledApplications(int paramInt)
  {
    return this.packageManager.getInstalledApplications(paramInt);
  }
  
  public List<PackageInfo> getInstalledPackages(int paramInt)
  {
    return this.packageManager.getInstalledPackages(paramInt);
  }
  
  public String getInstallerPackageName(String paramString)
  {
    return this.packageManager.getInstallerPackageName(paramString);
  }
  
  @NonNull
  @RequiresApi(api=26)
  public byte[] getInstantAppCookie()
  {
    return this.packageManager.getInstantAppCookie();
  }
  
  @RequiresApi(api=26)
  public int getInstantAppCookieMaxBytes()
  {
    return this.packageManager.getInstantAppCookieMaxBytes();
  }
  
  public InstrumentationInfo getInstrumentationInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getInstrumentationInfo(paramComponentName, paramInt);
  }
  
  @Nullable
  public Intent getLaunchIntentForPackage(@NonNull String paramString)
  {
    return this.packageManager.getLaunchIntentForPackage(paramString);
  }
  
  @Nullable
  @RequiresApi(api=21)
  public Intent getLeanbackLaunchIntentForPackage(@NonNull String paramString)
  {
    return this.packageManager.getLeanbackLaunchIntentForPackage(paramString);
  }
  
  @Nullable
  public String getNameForUid(int paramInt)
  {
    return this.packageManager.getNameForUid(paramInt);
  }
  
  public int[] getPackageGids(@NonNull String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getPackageGids(paramString);
  }
  
  @RequiresApi(api=24)
  public int[] getPackageGids(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getPackageGids(paramString, paramInt);
  }
  
  @RequiresApi(api=26)
  public PackageInfo getPackageInfo(VersionedPackage paramVersionedPackage, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getPackageInfo(paramVersionedPackage, paramInt);
  }
  
  public PackageInfo getPackageInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getPackageInfo(paramString, paramInt);
  }
  
  @NonNull
  @RequiresApi(api=21)
  public PackageInstaller getPackageInstaller()
  {
    return this.packageManager.getPackageInstaller();
  }
  
  @RequiresApi(api=24)
  public int getPackageUid(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getPackageUid(paramString, paramInt);
  }
  
  @Nullable
  public String[] getPackagesForUid(int paramInt)
  {
    return this.packageManager.getPackagesForUid(paramInt);
  }
  
  @RequiresApi(api=18)
  public List<PackageInfo> getPackagesHoldingPermissions(String[] paramArrayOfString, int paramInt)
  {
    return this.packageManager.getPackagesHoldingPermissions(paramArrayOfString, paramInt);
  }
  
  public PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getPermissionGroupInfo(paramString, paramInt);
  }
  
  public PermissionInfo getPermissionInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getPermissionInfo(paramString, paramInt);
  }
  
  public int getPreferredActivities(@NonNull List<IntentFilter> paramList, @NonNull List<ComponentName> paramList1, String paramString)
  {
    return this.packageManager.getPreferredActivities(paramList, paramList1, paramString);
  }
  
  public List<PackageInfo> getPreferredPackages(int paramInt)
  {
    return this.packageManager.getPreferredPackages(paramInt);
  }
  
  public ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getProviderInfo(paramComponentName, paramInt);
  }
  
  public ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getReceiverInfo(paramComponentName, paramInt);
  }
  
  public Resources getResourcesForActivity(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getResourcesForActivity(paramComponentName);
  }
  
  public Resources getResourcesForApplication(ApplicationInfo paramApplicationInfo)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getResourcesForApplication(paramApplicationInfo);
  }
  
  public Resources getResourcesForApplication(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getResourcesForApplication(paramString);
  }
  
  public ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.getServiceInfo(paramComponentName, paramInt);
  }
  
  @NonNull
  @RequiresApi(api=26)
  public List<SharedLibraryInfo> getSharedLibraries(int paramInt)
  {
    return this.packageManager.getSharedLibraries(paramInt);
  }
  
  public FeatureInfo[] getSystemAvailableFeatures()
  {
    return this.packageManager.getSystemAvailableFeatures();
  }
  
  public String[] getSystemSharedLibraryNames()
  {
    return this.packageManager.getSystemSharedLibraryNames();
  }
  
  public CharSequence getText(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.packageManager.getText(paramString, paramInt, paramApplicationInfo);
  }
  
  @RequiresApi(api=21)
  public Drawable getUserBadgedDrawableForDensity(Drawable paramDrawable, UserHandle paramUserHandle, Rect paramRect, int paramInt)
  {
    return this.packageManager.getUserBadgedDrawableForDensity(paramDrawable, paramUserHandle, paramRect, paramInt);
  }
  
  @RequiresApi(api=21)
  public Drawable getUserBadgedIcon(Drawable paramDrawable, UserHandle paramUserHandle)
  {
    return this.packageManager.getUserBadgedIcon(paramDrawable, paramUserHandle);
  }
  
  @RequiresApi(api=21)
  public CharSequence getUserBadgedLabel(CharSequence paramCharSequence, UserHandle paramUserHandle)
  {
    return this.packageManager.getUserBadgedLabel(paramCharSequence, paramUserHandle);
  }
  
  public XmlResourceParser getXml(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.packageManager.getXml(paramString, paramInt, paramApplicationInfo);
  }
  
  public boolean hasSystemFeature(String paramString)
  {
    return this.packageManager.hasSystemFeature(paramString);
  }
  
  @RequiresApi(api=24)
  public boolean hasSystemFeature(String paramString, int paramInt)
  {
    return this.packageManager.hasSystemFeature(paramString, paramInt);
  }
  
  @RequiresApi(api=26)
  public boolean isInstantApp()
  {
    return this.packageManager.isInstantApp();
  }
  
  @RequiresApi(api=26)
  public boolean isInstantApp(String paramString)
  {
    return this.packageManager.isInstantApp(paramString);
  }
  
  @RequiresApi(api=23)
  public boolean isPermissionRevokedByPolicy(@NonNull String paramString1, @NonNull String paramString2)
  {
    return this.packageManager.isPermissionRevokedByPolicy(paramString1, paramString2);
  }
  
  public boolean isSafeMode()
  {
    return this.packageManager.isSafeMode();
  }
  
  public List<ResolveInfo> queryBroadcastReceivers(Intent paramIntent, int paramInt)
  {
    return this.packageManager.queryBroadcastReceivers(paramIntent, paramInt);
  }
  
  public List<ProviderInfo> queryContentProviders(String paramString, int paramInt1, int paramInt2)
  {
    return this.packageManager.queryContentProviders(paramString, paramInt1, paramInt2);
  }
  
  public List<InstrumentationInfo> queryInstrumentation(String paramString, int paramInt)
  {
    return this.packageManager.queryInstrumentation(paramString, paramInt);
  }
  
  public List<ResolveInfo> queryIntentActivities(Intent paramIntent, int paramInt)
  {
    return this.packageManager.queryIntentActivities(paramIntent, paramInt);
  }
  
  public List<ResolveInfo> queryIntentActivityOptions(@Nullable ComponentName paramComponentName, @Nullable Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt)
  {
    return this.packageManager.queryIntentActivityOptions(paramComponentName, paramArrayOfIntent, paramIntent, paramInt);
  }
  
  @RequiresApi(api=19)
  public List<ResolveInfo> queryIntentContentProviders(Intent paramIntent, int paramInt)
  {
    return this.packageManager.queryIntentContentProviders(paramIntent, paramInt);
  }
  
  public List<ResolveInfo> queryIntentServices(Intent paramIntent, int paramInt)
  {
    return this.packageManager.queryIntentServices(paramIntent, paramInt);
  }
  
  public List<PermissionInfo> queryPermissionsByGroup(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.packageManager.queryPermissionsByGroup(paramString, paramInt);
  }
  
  public void removePackageFromPreferred(String paramString)
  {
    this.packageManager.removePackageFromPreferred(paramString);
  }
  
  public void removePermission(String paramString)
  {
    this.packageManager.removePermission(paramString);
  }
  
  public ResolveInfo resolveActivity(Intent paramIntent, int paramInt)
  {
    return this.packageManager.resolveActivity(paramIntent, paramInt);
  }
  
  public ProviderInfo resolveContentProvider(String paramString, int paramInt)
  {
    return this.packageManager.resolveContentProvider(paramString, paramInt);
  }
  
  public ResolveInfo resolveService(Intent paramIntent, int paramInt)
  {
    return this.packageManager.resolveService(paramIntent, paramInt);
  }
  
  @RequiresApi(api=26)
  public void setApplicationCategoryHint(@NonNull String paramString, int paramInt)
  {
    this.packageManager.setApplicationCategoryHint(paramString, paramInt);
  }
  
  public void setApplicationEnabledSetting(String paramString, int paramInt1, int paramInt2)
  {
    this.packageManager.setApplicationEnabledSetting(paramString, paramInt1, paramInt2);
  }
  
  public void setComponentEnabledSetting(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    this.packageManager.setComponentEnabledSetting(paramComponentName, paramInt1, paramInt2);
  }
  
  public void setInstallerPackageName(String paramString1, String paramString2)
  {
    this.packageManager.setInstallerPackageName(paramString1, paramString2);
  }
  
  @RequiresApi(api=26)
  public void updateInstantAppCookie(@Nullable byte[] paramArrayOfByte)
  {
    this.packageManager.updateInstantAppCookie(paramArrayOfByte);
  }
  
  public void verifyPendingInstall(int paramInt1, int paramInt2)
  {
    this.packageManager.verifyPendingInstall(paramInt1, paramInt2);
  }
}
