package com.oasisfeng.condom;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ChangedPackages;
import android.content.pm.FeatureInfo;
import android.content.pm.IPackageDataObserver;
import android.content.pm.IPackageDeleteObserver;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.InstrumentationInfo;
import android.content.pm.KeySet;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.MoveCallback;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager.OnPermissionsChangedListener;
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
import android.os.Handler;
import android.os.UserHandle;
import androidx.annotation.Keep;
import java.util.List;

@Keep
class PackageManagerWrapper
  extends PackageManager
{
  private PackageManager mBase;
  
  PackageManagerWrapper(PackageManager paramPackageManager)
  {
    this.mBase = paramPackageManager;
  }
  
  public void addCrossProfileIntentFilter(IntentFilter paramIntentFilter, int paramInt1, int paramInt2, int paramInt3)
  {
    this.mBase.addCrossProfileIntentFilter(paramIntentFilter, paramInt1, paramInt2, paramInt3);
  }
  
  public void addOnPermissionsChangeListener(PackageManager.OnPermissionsChangedListener paramOnPermissionsChangedListener)
  {
    this.mBase.addOnPermissionsChangeListener(paramOnPermissionsChangedListener);
  }
  
  public void addPackageToPreferred(String paramString)
  {
    this.mBase.addPackageToPreferred(paramString);
  }
  
  public boolean addPermission(PermissionInfo paramPermissionInfo)
  {
    return this.mBase.addPermission(paramPermissionInfo);
  }
  
  public boolean addPermissionAsync(PermissionInfo paramPermissionInfo)
  {
    return this.mBase.addPermissionAsync(paramPermissionInfo);
  }
  
  public void addPreferredActivity(IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName)
  {
    this.mBase.addPreferredActivity(paramIntentFilter, paramInt, paramArrayOfComponentName, paramComponentName);
  }
  
  public void addPreferredActivityAsUser(IntentFilter paramIntentFilter, int paramInt1, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName, int paramInt2)
  {
    this.mBase.addPreferredActivityAsUser(paramIntentFilter, paramInt1, paramArrayOfComponentName, paramComponentName, paramInt2);
  }
  
  public Intent buildRequestPermissionsIntent(String[] paramArrayOfString)
  {
    return this.mBase.buildRequestPermissionsIntent(paramArrayOfString);
  }
  
  public boolean canRequestPackageInstalls()
  {
    return this.mBase.canRequestPackageInstalls();
  }
  
  public String[] canonicalToCurrentPackageNames(String[] paramArrayOfString)
  {
    return this.mBase.canonicalToCurrentPackageNames(paramArrayOfString);
  }
  
  public int checkPermission(String paramString1, String paramString2)
  {
    return this.mBase.checkPermission(paramString1, paramString2);
  }
  
  public int checkSignatures(int paramInt1, int paramInt2)
  {
    return this.mBase.checkSignatures(paramInt1, paramInt2);
  }
  
  public int checkSignatures(String paramString1, String paramString2)
  {
    return this.mBase.checkSignatures(paramString1, paramString2);
  }
  
  public void clearApplicationUserData(String paramString, IPackageDataObserver paramIPackageDataObserver)
  {
    this.mBase.clearApplicationUserData(paramString, paramIPackageDataObserver);
  }
  
  public void clearCrossProfileIntentFilters(int paramInt)
  {
    this.mBase.clearCrossProfileIntentFilters(paramInt);
  }
  
  public void clearInstantAppCookie()
  {
    this.mBase.clearInstantAppCookie();
  }
  
  public void clearPackagePreferredActivities(String paramString)
  {
    this.mBase.clearPackagePreferredActivities(paramString);
  }
  
  public String[] currentToCanonicalPackageNames(String[] paramArrayOfString)
  {
    return this.mBase.currentToCanonicalPackageNames(paramArrayOfString);
  }
  
  public void deleteApplicationCacheFiles(String paramString, IPackageDataObserver paramIPackageDataObserver)
  {
    this.mBase.deleteApplicationCacheFiles(paramString, paramIPackageDataObserver);
  }
  
  public void deleteApplicationCacheFilesAsUser(String paramString, int paramInt, IPackageDataObserver paramIPackageDataObserver)
  {
    this.mBase.deleteApplicationCacheFilesAsUser(paramString, paramInt, paramIPackageDataObserver);
  }
  
  public void deletePackage(String paramString, IPackageDeleteObserver paramIPackageDeleteObserver, int paramInt)
  {
    this.mBase.deletePackage(paramString, paramIPackageDeleteObserver, paramInt);
  }
  
  public void deletePackageAsUser(String paramString, IPackageDeleteObserver paramIPackageDeleteObserver, int paramInt1, int paramInt2)
  {
    this.mBase.deletePackageAsUser(paramString, paramIPackageDeleteObserver, paramInt1, paramInt2);
  }
  
  public void extendVerificationTimeout(int paramInt1, int paramInt2, long paramLong)
  {
    this.mBase.extendVerificationTimeout(paramInt1, paramInt2, paramLong);
  }
  
  public void flushPackageRestrictionsAsUser(int paramInt)
  {
    this.mBase.flushPackageRestrictionsAsUser(paramInt);
  }
  
  public void freeStorage(long paramLong, IntentSender paramIntentSender)
  {
    this.mBase.freeStorage(paramLong, paramIntentSender);
  }
  
  public void freeStorage(String paramString, long paramLong, IntentSender paramIntentSender)
  {
    this.mBase.freeStorage(paramString, paramLong, paramIntentSender);
  }
  
  public void freeStorageAndNotify(long paramLong, IPackageDataObserver paramIPackageDataObserver)
  {
    this.mBase.freeStorageAndNotify(paramLong, paramIPackageDataObserver);
  }
  
  public void freeStorageAndNotify(String paramString, long paramLong, IPackageDataObserver paramIPackageDataObserver)
  {
    this.mBase.freeStorageAndNotify(paramString, paramLong, paramIPackageDataObserver);
  }
  
  public Drawable getActivityBanner(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getActivityBanner(paramComponentName);
  }
  
  public Drawable getActivityBanner(Intent paramIntent)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getActivityBanner(paramIntent);
  }
  
  public Drawable getActivityIcon(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getActivityIcon(paramComponentName);
  }
  
  public Drawable getActivityIcon(Intent paramIntent)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getActivityIcon(paramIntent);
  }
  
  public ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getActivityInfo(paramComponentName, paramInt);
  }
  
  public Drawable getActivityLogo(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getActivityLogo(paramComponentName);
  }
  
  public Drawable getActivityLogo(Intent paramIntent)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getActivityLogo(paramIntent);
  }
  
  public List<IntentFilter> getAllIntentFilters(String paramString)
  {
    return this.mBase.getAllIntentFilters(paramString);
  }
  
  public List<PermissionGroupInfo> getAllPermissionGroups(int paramInt)
  {
    return this.mBase.getAllPermissionGroups(paramInt);
  }
  
  public Drawable getApplicationBanner(ApplicationInfo paramApplicationInfo)
  {
    return this.mBase.getApplicationBanner(paramApplicationInfo);
  }
  
  public Drawable getApplicationBanner(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getApplicationBanner(paramString);
  }
  
  public int getApplicationEnabledSetting(String paramString)
  {
    return this.mBase.getApplicationEnabledSetting(paramString);
  }
  
  public boolean getApplicationHiddenSettingAsUser(String paramString, UserHandle paramUserHandle)
  {
    return this.mBase.getApplicationHiddenSettingAsUser(paramString, paramUserHandle);
  }
  
  public Drawable getApplicationIcon(ApplicationInfo paramApplicationInfo)
  {
    return this.mBase.getApplicationIcon(paramApplicationInfo);
  }
  
  public Drawable getApplicationIcon(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getApplicationIcon(paramString);
  }
  
  public ApplicationInfo getApplicationInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getApplicationInfo(paramString, paramInt);
  }
  
  public ApplicationInfo getApplicationInfoAsUser(String paramString, int paramInt1, int paramInt2)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getApplicationInfoAsUser(paramString, paramInt1, paramInt2);
  }
  
  public CharSequence getApplicationLabel(ApplicationInfo paramApplicationInfo)
  {
    return this.mBase.getApplicationLabel(paramApplicationInfo);
  }
  
  public Drawable getApplicationLogo(ApplicationInfo paramApplicationInfo)
  {
    return this.mBase.getApplicationLogo(paramApplicationInfo);
  }
  
  public Drawable getApplicationLogo(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getApplicationLogo(paramString);
  }
  
  public ChangedPackages getChangedPackages(int paramInt)
  {
    return this.mBase.getChangedPackages(paramInt);
  }
  
  public int getComponentEnabledSetting(ComponentName paramComponentName)
  {
    return this.mBase.getComponentEnabledSetting(paramComponentName);
  }
  
  public Drawable getDefaultActivityIcon()
  {
    return this.mBase.getDefaultActivityIcon();
  }
  
  public String getDefaultBrowserPackageNameAsUser(int paramInt)
  {
    return this.mBase.getDefaultBrowserPackageNameAsUser(paramInt);
  }
  
  public Drawable getDrawable(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.mBase.getDrawable(paramString, paramInt, paramApplicationInfo);
  }
  
  public Drawable getEphemeralApplicationIcon(String paramString)
  {
    return this.mBase.getEphemeralApplicationIcon(paramString);
  }
  
  public byte[] getEphemeralCookie()
  {
    return this.mBase.getEphemeralCookie();
  }
  
  public int getEphemeralCookieMaxSizeBytes()
  {
    return this.mBase.getEphemeralCookieMaxSizeBytes();
  }
  
  public ComponentName getHomeActivities(List<ResolveInfo> paramList)
  {
    return this.mBase.getHomeActivities(paramList);
  }
  
  public List<ApplicationInfo> getInstalledApplications(int paramInt)
  {
    return this.mBase.getInstalledApplications(paramInt);
  }
  
  public List<PackageInfo> getInstalledPackages(int paramInt)
  {
    return this.mBase.getInstalledPackages(paramInt);
  }
  
  public List<PackageInfo> getInstalledPackagesAsUser(int paramInt1, int paramInt2)
  {
    return this.mBase.getInstalledPackagesAsUser(paramInt1, paramInt2);
  }
  
  public String getInstallerPackageName(String paramString)
  {
    return this.mBase.getInstallerPackageName(paramString);
  }
  
  public byte[] getInstantAppCookie()
  {
    return this.mBase.getInstantAppCookie();
  }
  
  public int getInstantAppCookieMaxBytes()
  {
    return this.mBase.getInstantAppCookieMaxBytes();
  }
  
  public InstrumentationInfo getInstrumentationInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getInstrumentationInfo(paramComponentName, paramInt);
  }
  
  public int getIntentVerificationStatusAsUser(String paramString, int paramInt)
  {
    return this.mBase.getIntentVerificationStatusAsUser(paramString, paramInt);
  }
  
  public KeySet getKeySetByAlias(String paramString1, String paramString2)
  {
    return this.mBase.getKeySetByAlias(paramString1, paramString2);
  }
  
  public Intent getLaunchIntentForPackage(String paramString)
  {
    return this.mBase.getLaunchIntentForPackage(paramString);
  }
  
  public Intent getLeanbackLaunchIntentForPackage(String paramString)
  {
    return this.mBase.getLeanbackLaunchIntentForPackage(paramString);
  }
  
  public Drawable getManagedUserBadgedDrawable(Drawable paramDrawable, Rect paramRect, int paramInt)
  {
    return this.mBase.getManagedUserBadgedDrawable(paramDrawable, paramRect, paramInt);
  }
  
  public int getMoveStatus(int paramInt)
  {
    return this.mBase.getMoveStatus(paramInt);
  }
  
  public String getNameForUid(int paramInt)
  {
    return this.mBase.getNameForUid(paramInt);
  }
  
  public PackageInfo getPackageArchiveInfo(String paramString, int paramInt)
  {
    return this.mBase.getPackageArchiveInfo(paramString, paramInt);
  }
  
  public int[] getPackageGids(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getPackageGids(paramString);
  }
  
  public int[] getPackageGids(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getPackageGids(paramString, paramInt);
  }
  
  public PackageInfo getPackageInfo(VersionedPackage paramVersionedPackage, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getPackageInfo(paramVersionedPackage, paramInt);
  }
  
  public PackageInfo getPackageInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getPackageInfo(paramString, paramInt);
  }
  
  public PackageInfo getPackageInfoAsUser(String paramString, int paramInt1, int paramInt2)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getPackageInfoAsUser(paramString, paramInt1, paramInt2);
  }
  
  public PackageInstaller getPackageInstaller()
  {
    return this.mBase.getPackageInstaller();
  }
  
  public void getPackageSizeInfo(String paramString, IPackageStatsObserver paramIPackageStatsObserver)
  {
    this.mBase.getPackageSizeInfo(paramString, paramIPackageStatsObserver);
  }
  
  public void getPackageSizeInfoAsUser(String paramString, int paramInt, IPackageStatsObserver paramIPackageStatsObserver)
  {
    this.mBase.getPackageSizeInfoAsUser(paramString, paramInt, paramIPackageStatsObserver);
  }
  
  public int getPackageUid(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getPackageUid(paramString, paramInt);
  }
  
  public int getPackageUidAsUser(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getPackageUidAsUser(paramString, paramInt);
  }
  
  public int getPackageUidAsUser(String paramString, int paramInt1, int paramInt2)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getPackageUidAsUser(paramString, paramInt1, paramInt2);
  }
  
  public String[] getPackagesForUid(int paramInt)
  {
    return this.mBase.getPackagesForUid(paramInt);
  }
  
  public List<PackageInfo> getPackagesHoldingPermissions(String[] paramArrayOfString, int paramInt)
  {
    return this.mBase.getPackagesHoldingPermissions(paramArrayOfString, paramInt);
  }
  
  public String getPermissionControllerPackageName()
  {
    return this.mBase.getPermissionControllerPackageName();
  }
  
  public int getPermissionFlags(String paramString1, String paramString2, UserHandle paramUserHandle)
  {
    return this.mBase.getPermissionFlags(paramString1, paramString2, paramUserHandle);
  }
  
  public PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getPermissionGroupInfo(paramString, paramInt);
  }
  
  public PermissionInfo getPermissionInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getPermissionInfo(paramString, paramInt);
  }
  
  public int getPreferredActivities(List<IntentFilter> paramList, List<ComponentName> paramList1, String paramString)
  {
    return this.mBase.getPreferredActivities(paramList, paramList1, paramString);
  }
  
  public List<PackageInfo> getPreferredPackages(int paramInt)
  {
    return this.mBase.getPreferredPackages(paramInt);
  }
  
  public ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getProviderInfo(paramComponentName, paramInt);
  }
  
  public ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getReceiverInfo(paramComponentName, paramInt);
  }
  
  public Resources getResourcesForActivity(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getResourcesForActivity(paramComponentName);
  }
  
  public Resources getResourcesForApplication(ApplicationInfo paramApplicationInfo)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getResourcesForApplication(paramApplicationInfo);
  }
  
  public Resources getResourcesForApplication(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getResourcesForApplication(paramString);
  }
  
  public Resources getResourcesForApplicationAsUser(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getResourcesForApplicationAsUser(paramString, paramInt);
  }
  
  public ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getServiceInfo(paramComponentName, paramInt);
  }
  
  public String getServicesSystemSharedLibraryPackageName()
  {
    return this.mBase.getServicesSystemSharedLibraryPackageName();
  }
  
  public List<SharedLibraryInfo> getSharedLibraries(int paramInt)
  {
    return this.mBase.getSharedLibraries(paramInt);
  }
  
  public String getSharedSystemSharedLibraryPackageName()
  {
    return this.mBase.getSharedSystemSharedLibraryPackageName();
  }
  
  public KeySet getSigningKeySet(String paramString)
  {
    return this.mBase.getSigningKeySet(paramString);
  }
  
  public FeatureInfo[] getSystemAvailableFeatures()
  {
    return this.mBase.getSystemAvailableFeatures();
  }
  
  public String[] getSystemSharedLibraryNames()
  {
    return this.mBase.getSystemSharedLibraryNames();
  }
  
  public CharSequence getText(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.mBase.getText(paramString, paramInt, paramApplicationInfo);
  }
  
  public int getUidForSharedUser(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.getUidForSharedUser(paramString);
  }
  
  public Drawable getUserBadgeForDensity(UserHandle paramUserHandle, int paramInt)
  {
    return this.mBase.getUserBadgeForDensity(paramUserHandle, paramInt);
  }
  
  public Drawable getUserBadgeForDensityNoBackground(UserHandle paramUserHandle, int paramInt)
  {
    return this.mBase.getUserBadgeForDensityNoBackground(paramUserHandle, paramInt);
  }
  
  public Drawable getUserBadgedDrawableForDensity(Drawable paramDrawable, UserHandle paramUserHandle, Rect paramRect, int paramInt)
  {
    return this.mBase.getUserBadgedDrawableForDensity(paramDrawable, paramUserHandle, paramRect, paramInt);
  }
  
  public Drawable getUserBadgedIcon(Drawable paramDrawable, UserHandle paramUserHandle)
  {
    return this.mBase.getUserBadgedIcon(paramDrawable, paramUserHandle);
  }
  
  public CharSequence getUserBadgedLabel(CharSequence paramCharSequence, UserHandle paramUserHandle)
  {
    return this.mBase.getUserBadgedLabel(paramCharSequence, paramUserHandle);
  }
  
  public XmlResourceParser getXml(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.mBase.getXml(paramString, paramInt, paramApplicationInfo);
  }
  
  public void grantRuntimePermission(String paramString1, String paramString2, UserHandle paramUserHandle)
  {
    this.mBase.grantRuntimePermission(paramString1, paramString2, paramUserHandle);
  }
  
  public boolean hasSystemFeature(String paramString)
  {
    return this.mBase.hasSystemFeature(paramString);
  }
  
  public boolean hasSystemFeature(String paramString, int paramInt)
  {
    return this.mBase.hasSystemFeature(paramString, paramInt);
  }
  
  public int installExistingPackage(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.installExistingPackage(paramString);
  }
  
  public int installExistingPackageAsUser(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.installExistingPackageAsUser(paramString, paramInt);
  }
  
  public boolean isEphemeralApplication()
  {
    return this.mBase.isEphemeralApplication();
  }
  
  public boolean isInstantApp()
  {
    return this.mBase.isInstantApp();
  }
  
  public boolean isInstantApp(String paramString)
  {
    return this.mBase.isInstantApp(paramString);
  }
  
  public boolean isPackageAvailable(String paramString)
  {
    return this.mBase.isPackageAvailable(paramString);
  }
  
  public boolean isPackageSuspendedForUser(String paramString, int paramInt)
  {
    return this.mBase.isPackageSuspendedForUser(paramString, paramInt);
  }
  
  public boolean isPermissionRevokedByPolicy(String paramString1, String paramString2)
  {
    return this.mBase.isPermissionRevokedByPolicy(paramString1, paramString2);
  }
  
  public boolean isSafeMode()
  {
    return this.mBase.isSafeMode();
  }
  
  public boolean isSignedBy(String paramString, KeySet paramKeySet)
  {
    return this.mBase.isSignedBy(paramString, paramKeySet);
  }
  
  public boolean isSignedByExactly(String paramString, KeySet paramKeySet)
  {
    return this.mBase.isSignedByExactly(paramString, paramKeySet);
  }
  
  public boolean isUpgrade()
  {
    return this.mBase.isUpgrade();
  }
  
  public Drawable loadItemIcon(PackageItemInfo paramPackageItemInfo, ApplicationInfo paramApplicationInfo)
  {
    return this.mBase.loadItemIcon(paramPackageItemInfo, paramApplicationInfo);
  }
  
  public Drawable loadUnbadgedItemIcon(PackageItemInfo paramPackageItemInfo, ApplicationInfo paramApplicationInfo)
  {
    return this.mBase.loadUnbadgedItemIcon(paramPackageItemInfo, paramApplicationInfo);
  }
  
  public List<ResolveInfo> queryBroadcastReceivers(Intent paramIntent, int paramInt)
  {
    return this.mBase.queryBroadcastReceivers(paramIntent, paramInt);
  }
  
  @Deprecated
  public List<ResolveInfo> queryBroadcastReceivers(Intent paramIntent, int paramInt1, int paramInt2)
  {
    return this.mBase.queryBroadcastReceivers(paramIntent, paramInt1, paramInt2);
  }
  
  public List<ResolveInfo> queryBroadcastReceiversAsUser(Intent paramIntent, int paramInt1, int paramInt2)
  {
    return this.mBase.queryBroadcastReceiversAsUser(paramIntent, paramInt1, paramInt2);
  }
  
  public List<ResolveInfo> queryBroadcastReceiversAsUser(Intent paramIntent, int paramInt, UserHandle paramUserHandle)
  {
    return this.mBase.queryBroadcastReceiversAsUser(paramIntent, paramInt, paramUserHandle);
  }
  
  public List<ProviderInfo> queryContentProviders(String paramString, int paramInt1, int paramInt2)
  {
    return this.mBase.queryContentProviders(paramString, paramInt1, paramInt2);
  }
  
  public List<InstrumentationInfo> queryInstrumentation(String paramString, int paramInt)
  {
    return this.mBase.queryInstrumentation(paramString, paramInt);
  }
  
  public List<ResolveInfo> queryIntentActivities(Intent paramIntent, int paramInt)
  {
    return this.mBase.queryIntentActivities(paramIntent, paramInt);
  }
  
  public List<ResolveInfo> queryIntentActivitiesAsUser(Intent paramIntent, int paramInt1, int paramInt2)
  {
    return this.mBase.queryIntentActivitiesAsUser(paramIntent, paramInt1, paramInt2);
  }
  
  public List<ResolveInfo> queryIntentActivityOptions(ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt)
  {
    return this.mBase.queryIntentActivityOptions(paramComponentName, paramArrayOfIntent, paramIntent, paramInt);
  }
  
  public List<ResolveInfo> queryIntentContentProviders(Intent paramIntent, int paramInt)
  {
    return this.mBase.queryIntentContentProviders(paramIntent, paramInt);
  }
  
  public List<ResolveInfo> queryIntentContentProvidersAsUser(Intent paramIntent, int paramInt1, int paramInt2)
  {
    return this.mBase.queryIntentContentProvidersAsUser(paramIntent, paramInt1, paramInt2);
  }
  
  public List<ResolveInfo> queryIntentServices(Intent paramIntent, int paramInt)
  {
    return this.mBase.queryIntentServices(paramIntent, paramInt);
  }
  
  public List<ResolveInfo> queryIntentServicesAsUser(Intent paramIntent, int paramInt1, int paramInt2)
  {
    return this.mBase.queryIntentServicesAsUser(paramIntent, paramInt1, paramInt2);
  }
  
  public List<PermissionInfo> queryPermissionsByGroup(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mBase.queryPermissionsByGroup(paramString, paramInt);
  }
  
  public void registerMoveCallback(PackageManager.MoveCallback paramMoveCallback, Handler paramHandler)
  {
    this.mBase.registerMoveCallback(paramMoveCallback, paramHandler);
  }
  
  public void removeOnPermissionsChangeListener(PackageManager.OnPermissionsChangedListener paramOnPermissionsChangedListener)
  {
    this.mBase.removeOnPermissionsChangeListener(paramOnPermissionsChangedListener);
  }
  
  public void removePackageFromPreferred(String paramString)
  {
    this.mBase.removePackageFromPreferred(paramString);
  }
  
  public void removePermission(String paramString)
  {
    this.mBase.removePermission(paramString);
  }
  
  @Deprecated
  public void replacePreferredActivity(IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName)
  {
    this.mBase.replacePreferredActivity(paramIntentFilter, paramInt, paramArrayOfComponentName, paramComponentName);
  }
  
  @Deprecated
  public void replacePreferredActivityAsUser(IntentFilter paramIntentFilter, int paramInt1, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName, int paramInt2)
  {
    this.mBase.replacePreferredActivityAsUser(paramIntentFilter, paramInt1, paramArrayOfComponentName, paramComponentName, paramInt2);
  }
  
  public ResolveInfo resolveActivity(Intent paramIntent, int paramInt)
  {
    return this.mBase.resolveActivity(paramIntent, paramInt);
  }
  
  public ResolveInfo resolveActivityAsUser(Intent paramIntent, int paramInt1, int paramInt2)
  {
    return this.mBase.resolveActivityAsUser(paramIntent, paramInt1, paramInt2);
  }
  
  public ProviderInfo resolveContentProvider(String paramString, int paramInt)
  {
    return this.mBase.resolveContentProvider(paramString, paramInt);
  }
  
  public ProviderInfo resolveContentProviderAsUser(String paramString, int paramInt1, int paramInt2)
  {
    return this.mBase.resolveContentProviderAsUser(paramString, paramInt1, paramInt2);
  }
  
  public ResolveInfo resolveService(Intent paramIntent, int paramInt)
  {
    return this.mBase.resolveService(paramIntent, paramInt);
  }
  
  public void revokeRuntimePermission(String paramString1, String paramString2, UserHandle paramUserHandle)
  {
    this.mBase.revokeRuntimePermission(paramString1, paramString2, paramUserHandle);
  }
  
  public void setApplicationCategoryHint(String paramString, int paramInt)
  {
    this.mBase.setApplicationCategoryHint(paramString, paramInt);
  }
  
  public void setApplicationEnabledSetting(String paramString, int paramInt1, int paramInt2)
  {
    this.mBase.setApplicationEnabledSetting(paramString, paramInt1, paramInt2);
  }
  
  public boolean setApplicationHiddenSettingAsUser(String paramString, boolean paramBoolean, UserHandle paramUserHandle)
  {
    return this.mBase.setApplicationHiddenSettingAsUser(paramString, paramBoolean, paramUserHandle);
  }
  
  public void setComponentEnabledSetting(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    this.mBase.setComponentEnabledSetting(paramComponentName, paramInt1, paramInt2);
  }
  
  public boolean setDefaultBrowserPackageNameAsUser(String paramString, int paramInt)
  {
    return this.mBase.setDefaultBrowserPackageNameAsUser(paramString, paramInt);
  }
  
  public boolean setEphemeralCookie(byte[] paramArrayOfByte)
  {
    return this.mBase.setEphemeralCookie(paramArrayOfByte);
  }
  
  public void setInstallerPackageName(String paramString1, String paramString2)
  {
    this.mBase.setInstallerPackageName(paramString1, paramString2);
  }
  
  public String[] setPackagesSuspendedAsUser(String[] paramArrayOfString, boolean paramBoolean, int paramInt)
  {
    return this.mBase.setPackagesSuspendedAsUser(paramArrayOfString, paramBoolean, paramInt);
  }
  
  public boolean shouldShowRequestPermissionRationale(String paramString)
  {
    return this.mBase.shouldShowRequestPermissionRationale(paramString);
  }
  
  public void unregisterMoveCallback(PackageManager.MoveCallback paramMoveCallback)
  {
    this.mBase.unregisterMoveCallback(paramMoveCallback);
  }
  
  public void updateInstantAppCookie(byte[] paramArrayOfByte)
  {
    this.mBase.updateInstantAppCookie(paramArrayOfByte);
  }
  
  public boolean updateIntentVerificationStatusAsUser(String paramString, int paramInt1, int paramInt2)
  {
    return this.mBase.updateIntentVerificationStatusAsUser(paramString, paramInt1, paramInt2);
  }
  
  public void updatePermissionFlags(String paramString1, String paramString2, int paramInt1, int paramInt2, UserHandle paramUserHandle)
  {
    this.mBase.updatePermissionFlags(paramString1, paramString2, paramInt1, paramInt2, paramUserHandle);
  }
  
  public void verifyIntentFilter(int paramInt1, int paramInt2, List<String> paramList)
  {
    this.mBase.verifyIntentFilter(paramInt1, paramInt2, paramList);
  }
  
  public void verifyPendingInstall(int paramInt1, int paramInt2)
  {
    this.mBase.verifyPendingInstall(paramInt1, paramInt2);
  }
}
