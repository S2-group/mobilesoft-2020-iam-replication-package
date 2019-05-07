package o;

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
import java.util.List;

public class ams
  extends PackageManager
{
  private final PackageManager eN;
  
  ams(PackageManager paramPackageManager)
  {
    this.eN = paramPackageManager;
  }
  
  @Deprecated
  public void addPackageToPreferred(String paramString)
  {
    this.eN.addPackageToPreferred(paramString);
  }
  
  public boolean addPermission(PermissionInfo paramPermissionInfo)
  {
    return this.eN.addPermission(paramPermissionInfo);
  }
  
  public boolean addPermissionAsync(PermissionInfo paramPermissionInfo)
  {
    return this.eN.addPermissionAsync(paramPermissionInfo);
  }
  
  @Deprecated
  public void addPreferredActivity(IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName)
  {
    this.eN.addPreferredActivity(paramIntentFilter, paramInt, paramArrayOfComponentName, paramComponentName);
  }
  
  public String[] canonicalToCurrentPackageNames(String[] paramArrayOfString)
  {
    return this.eN.currentToCanonicalPackageNames(paramArrayOfString);
  }
  
  public int checkPermission(String paramString1, String paramString2)
  {
    return this.eN.checkPermission(paramString1, paramString2);
  }
  
  public int checkSignatures(int paramInt1, int paramInt2)
  {
    return this.eN.checkSignatures(paramInt1, paramInt2);
  }
  
  public int checkSignatures(String paramString1, String paramString2)
  {
    return this.eN.checkSignatures(paramString1, paramString2);
  }
  
  public void clearPackagePreferredActivities(String paramString)
  {
    this.eN.clearPackagePreferredActivities(paramString);
  }
  
  public String[] currentToCanonicalPackageNames(String[] paramArrayOfString)
  {
    return this.eN.currentToCanonicalPackageNames(paramArrayOfString);
  }
  
  @TargetApi(17)
  public void extendVerificationTimeout(int paramInt1, int paramInt2, long paramLong)
  {
    this.eN.extendVerificationTimeout(paramInt1, paramInt2, paramLong);
  }
  
  @TargetApi(20)
  public Drawable getActivityBanner(ComponentName paramComponentName)
  {
    return this.eN.getActivityBanner(paramComponentName);
  }
  
  @TargetApi(20)
  public Drawable getActivityBanner(Intent paramIntent)
  {
    return this.eN.getActivityBanner(paramIntent);
  }
  
  public Drawable getActivityIcon(ComponentName paramComponentName)
  {
    return this.eN.getActivityIcon(paramComponentName);
  }
  
  public Drawable getActivityIcon(Intent paramIntent)
  {
    return this.eN.getActivityIcon(paramIntent);
  }
  
  public ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt)
  {
    return this.eN.getActivityInfo(paramComponentName, paramInt);
  }
  
  public Drawable getActivityLogo(ComponentName paramComponentName)
  {
    return this.eN.getActivityLogo(paramComponentName);
  }
  
  public Drawable getActivityLogo(Intent paramIntent)
  {
    return this.eN.getActivityLogo(paramIntent);
  }
  
  public List getAllPermissionGroups(int paramInt)
  {
    return this.eN.getAllPermissionGroups(paramInt);
  }
  
  @TargetApi(20)
  public Drawable getApplicationBanner(ApplicationInfo paramApplicationInfo)
  {
    return this.eN.getApplicationBanner(paramApplicationInfo);
  }
  
  @TargetApi(20)
  public Drawable getApplicationBanner(String paramString)
  {
    return this.eN.getApplicationBanner(paramString);
  }
  
  public int getApplicationEnabledSetting(String paramString)
  {
    return this.eN.getApplicationEnabledSetting(paramString);
  }
  
  public Drawable getApplicationIcon(ApplicationInfo paramApplicationInfo)
  {
    return this.eN.getApplicationIcon(paramApplicationInfo);
  }
  
  public Drawable getApplicationIcon(String paramString)
  {
    return this.eN.getApplicationIcon(paramString);
  }
  
  public ApplicationInfo getApplicationInfo(String paramString, int paramInt)
  {
    return this.eN.getApplicationInfo(paramString, paramInt);
  }
  
  public CharSequence getApplicationLabel(ApplicationInfo paramApplicationInfo)
  {
    return this.eN.getApplicationLabel(paramApplicationInfo);
  }
  
  public Drawable getApplicationLogo(ApplicationInfo paramApplicationInfo)
  {
    return this.eN.getApplicationLogo(paramApplicationInfo);
  }
  
  public Drawable getApplicationLogo(String paramString)
  {
    return this.eN.getApplicationLogo(paramString);
  }
  
  public int getComponentEnabledSetting(ComponentName paramComponentName)
  {
    return this.eN.getComponentEnabledSetting(paramComponentName);
  }
  
  public Drawable getDefaultActivityIcon()
  {
    return this.eN.getDefaultActivityIcon();
  }
  
  public Drawable getDrawable(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.eN.getDrawable(paramString, paramInt, paramApplicationInfo);
  }
  
  public List getInstalledApplications(int paramInt)
  {
    return this.eN.getInstalledApplications(paramInt);
  }
  
  public List getInstalledPackages(int paramInt)
  {
    return this.eN.getInstalledPackages(paramInt);
  }
  
  public String getInstallerPackageName(String paramString)
  {
    return this.eN.getInstallerPackageName(paramString);
  }
  
  public InstrumentationInfo getInstrumentationInfo(ComponentName paramComponentName, int paramInt)
  {
    return this.eN.getInstrumentationInfo(paramComponentName, paramInt);
  }
  
  public Intent getLaunchIntentForPackage(String paramString)
  {
    return this.eN.getLaunchIntentForPackage(paramString);
  }
  
  @TargetApi(21)
  public Intent getLeanbackLaunchIntentForPackage(String paramString)
  {
    return this.eN.getLeanbackLaunchIntentForPackage(paramString);
  }
  
  public String getNameForUid(int paramInt)
  {
    return this.eN.getNameForUid(paramInt);
  }
  
  public int[] getPackageGids(String paramString)
  {
    return this.eN.getPackageGids(paramString);
  }
  
  public PackageInfo getPackageInfo(String paramString, int paramInt)
  {
    return this.eN.getPackageInfo(paramString, paramInt);
  }
  
  @TargetApi(21)
  public PackageInstaller getPackageInstaller()
  {
    return this.eN.getPackageInstaller();
  }
  
  public String[] getPackagesForUid(int paramInt)
  {
    return this.eN.getPackagesForUid(paramInt);
  }
  
  @TargetApi(18)
  public List getPackagesHoldingPermissions(String[] paramArrayOfString, int paramInt)
  {
    return this.eN.getPackagesHoldingPermissions(paramArrayOfString, paramInt);
  }
  
  public PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt)
  {
    return this.eN.getPermissionGroupInfo(paramString, paramInt);
  }
  
  public PermissionInfo getPermissionInfo(String paramString, int paramInt)
  {
    return this.eN.getPermissionInfo(paramString, paramInt);
  }
  
  public int getPreferredActivities(List paramList1, List paramList2, String paramString)
  {
    return this.eN.getPreferredActivities(paramList1, paramList2, paramString);
  }
  
  public List getPreferredPackages(int paramInt)
  {
    return this.eN.getPreferredPackages(paramInt);
  }
  
  public ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt)
  {
    return this.eN.getProviderInfo(paramComponentName, paramInt);
  }
  
  public ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt)
  {
    return this.eN.getReceiverInfo(paramComponentName, paramInt);
  }
  
  public Resources getResourcesForActivity(ComponentName paramComponentName)
  {
    return this.eN.getResourcesForActivity(paramComponentName);
  }
  
  public Resources getResourcesForApplication(ApplicationInfo paramApplicationInfo)
  {
    return this.eN.getResourcesForApplication(paramApplicationInfo);
  }
  
  public Resources getResourcesForApplication(String paramString)
  {
    return this.eN.getResourcesForApplication(paramString);
  }
  
  public ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt)
  {
    return this.eN.getServiceInfo(paramComponentName, paramInt);
  }
  
  public FeatureInfo[] getSystemAvailableFeatures()
  {
    return this.eN.getSystemAvailableFeatures();
  }
  
  public String[] getSystemSharedLibraryNames()
  {
    return this.eN.getSystemSharedLibraryNames();
  }
  
  public CharSequence getText(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.eN.getText(paramString, paramInt, paramApplicationInfo);
  }
  
  @TargetApi(21)
  public Drawable getUserBadgedDrawableForDensity(Drawable paramDrawable, UserHandle paramUserHandle, Rect paramRect, int paramInt)
  {
    return this.eN.getUserBadgedDrawableForDensity(paramDrawable, paramUserHandle, paramRect, paramInt);
  }
  
  @TargetApi(21)
  public Drawable getUserBadgedIcon(Drawable paramDrawable, UserHandle paramUserHandle)
  {
    return this.eN.getUserBadgedIcon(paramDrawable, paramUserHandle);
  }
  
  @TargetApi(21)
  public CharSequence getUserBadgedLabel(CharSequence paramCharSequence, UserHandle paramUserHandle)
  {
    return this.eN.getUserBadgedLabel(paramCharSequence, paramUserHandle);
  }
  
  public XmlResourceParser getXml(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.eN.getXml(paramString, paramInt, paramApplicationInfo);
  }
  
  public boolean hasSystemFeature(String paramString)
  {
    return this.eN.hasSystemFeature(paramString);
  }
  
  public boolean isPermissionRevokedByPolicy(String paramString1, String paramString2)
  {
    return this.eN.isPermissionRevokedByPolicy(paramString1, paramString2);
  }
  
  public boolean isSafeMode()
  {
    return this.eN.isSafeMode();
  }
  
  public List queryBroadcastReceivers(Intent paramIntent, int paramInt)
  {
    return this.eN.queryBroadcastReceivers(paramIntent, paramInt);
  }
  
  public List queryContentProviders(String paramString, int paramInt1, int paramInt2)
  {
    return this.eN.queryContentProviders(paramString, paramInt1, paramInt2);
  }
  
  public List queryInstrumentation(String paramString, int paramInt)
  {
    return this.eN.queryInstrumentation(paramString, paramInt);
  }
  
  public List queryIntentActivities(Intent paramIntent, int paramInt)
  {
    return this.eN.queryIntentActivities(paramIntent, paramInt);
  }
  
  public List queryIntentActivityOptions(ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt)
  {
    return this.eN.queryIntentActivityOptions(paramComponentName, paramArrayOfIntent, paramIntent, paramInt);
  }
  
  @TargetApi(19)
  public List queryIntentContentProviders(Intent paramIntent, int paramInt)
  {
    return this.eN.queryIntentContentProviders(paramIntent, paramInt);
  }
  
  public List queryIntentServices(Intent paramIntent, int paramInt)
  {
    return this.eN.queryIntentServices(paramIntent, paramInt);
  }
  
  public List queryPermissionsByGroup(String paramString, int paramInt)
  {
    return this.eN.queryPermissionsByGroup(paramString, paramInt);
  }
  
  @Deprecated
  public void removePackageFromPreferred(String paramString)
  {
    this.eN.removePackageFromPreferred(paramString);
  }
  
  public void removePermission(String paramString)
  {
    this.eN.removePermission(paramString);
  }
  
  public ResolveInfo resolveActivity(Intent paramIntent, int paramInt)
  {
    return this.eN.resolveActivity(paramIntent, paramInt);
  }
  
  public ProviderInfo resolveContentProvider(String paramString, int paramInt)
  {
    return this.eN.resolveContentProvider(paramString, paramInt);
  }
  
  public ResolveInfo resolveService(Intent paramIntent, int paramInt)
  {
    return this.eN.resolveService(paramIntent, paramInt);
  }
  
  public void setApplicationEnabledSetting(String paramString, int paramInt1, int paramInt2)
  {
    this.eN.setApplicationEnabledSetting(paramString, paramInt1, paramInt2);
  }
  
  public void setComponentEnabledSetting(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    this.eN.setComponentEnabledSetting(paramComponentName, paramInt1, paramInt2);
  }
  
  public void setInstallerPackageName(String paramString1, String paramString2)
  {
    this.eN.setInstallerPackageName(paramString1, paramString2);
  }
  
  public void verifyPendingInstall(int paramInt1, int paramInt2)
  {
    this.eN.verifyPendingInstall(paramInt1, paramInt2);
  }
}
