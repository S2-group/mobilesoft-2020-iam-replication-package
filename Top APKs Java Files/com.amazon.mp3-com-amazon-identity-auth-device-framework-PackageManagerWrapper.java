package com.amazon.identity.auth.device.framework;

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
import android.os.Process;
import com.amazon.identity.auth.device.utils.UnitTestUtils;
import com.amazon.identity.platform.util.PlatformUtils;
import java.util.List;

public class PackageManagerWrapper
  extends PackageManager
{
  private static final String LOG_TAG = PackageManagerWrapper.class.getName();
  private final Context mContext;
  protected PackageManager mPackageManager;
  
  public PackageManagerWrapper()
  {
    this.mPackageManager = null;
    this.mContext = null;
  }
  
  public PackageManagerWrapper(Context paramContext)
  {
    this.mPackageManager = paramContext.getPackageManager();
    this.mContext = paramContext;
  }
  
  public PackageManagerWrapper(Context paramContext, PackageManager paramPackageManager)
  {
    this.mPackageManager = paramPackageManager;
    this.mContext = paramContext.getApplicationContext();
  }
  
  private boolean isRunningInUnitTest(int paramInt1, int paramInt2)
  {
    return this.mContext.getPackageManager().checkSignatures(paramInt1, paramInt2) == 0;
  }
  
  private boolean isRunningInUnitTest(String paramString1, String paramString2)
  {
    return this.mContext.getPackageManager().checkSignatures(paramString1, paramString2) == 0;
  }
  
  public void addPackageToPreferred(String paramString)
  {
    this.mPackageManager.addPackageToPreferred(paramString);
  }
  
  public boolean addPermission(PermissionInfo paramPermissionInfo)
  {
    return this.mPackageManager.addPermission(paramPermissionInfo);
  }
  
  public boolean addPermissionAsync(PermissionInfo paramPermissionInfo)
  {
    return this.mPackageManager.addPermissionAsync(paramPermissionInfo);
  }
  
  public void addPreferredActivity(IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName)
  {
    this.mPackageManager.addPreferredActivity(paramIntentFilter, paramInt, paramArrayOfComponentName, paramComponentName);
  }
  
  public String[] canonicalToCurrentPackageNames(String[] paramArrayOfString)
  {
    return this.mPackageManager.canonicalToCurrentPackageNames(paramArrayOfString);
  }
  
  public int checkPermission(String paramString1, String paramString2)
  {
    return this.mPackageManager.checkPermission(paramString1, paramString2);
  }
  
  public int checkSignatures(int paramInt1, int paramInt2)
  {
    return this.mPackageManager.checkSignatures(paramInt1, paramInt2);
  }
  
  public int checkSignatures(String paramString1, String paramString2)
  {
    return this.mPackageManager.checkSignatures(paramString1, paramString2);
  }
  
  public void clearPackagePreferredActivities(String paramString)
  {
    this.mPackageManager.clearPackagePreferredActivities(paramString);
  }
  
  public String[] currentToCanonicalPackageNames(String[] paramArrayOfString)
  {
    return this.mPackageManager.currentToCanonicalPackageNames(paramArrayOfString);
  }
  
  public boolean doSignaturesMatch(int paramInt1, int paramInt2)
  {
    boolean bool2 = true;
    boolean bool1;
    if (UnitTestUtils.isRunningInUnitTest()) {
      bool1 = isRunningInUnitTest(paramInt1, paramInt2);
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramInt1 == paramInt2);
      bool1 = bool2;
    } while (this.mPackageManager.checkSignatures(paramInt1, paramInt2) == 0);
    if (!PlatformUtils.isAmazonDevice()) {
      return false;
    }
    int i = Process.myUid();
    if (paramInt1 == i) {
      return TrustedAppUtils.isTrustedApp(paramInt2, this.mPackageManager);
    }
    if (paramInt2 == i) {
      return TrustedAppUtils.isTrustedApp(paramInt1, this.mPackageManager);
    }
    return false;
  }
  
  public boolean doSignaturesMatch(String paramString1, String paramString2)
  {
    boolean bool2 = true;
    boolean bool1;
    if (UnitTestUtils.isRunningInUnitTest()) {
      bool1 = isRunningInUnitTest(paramString1, paramString2);
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramString1.equals(paramString2));
      bool1 = bool2;
    } while (this.mPackageManager.checkSignatures(paramString1, paramString2) == 0);
    if (!PlatformUtils.isAmazonDevice()) {
      return false;
    }
    String str = this.mContext.getPackageName();
    if (str.equals(paramString1)) {
      return TrustedAppUtils.isTrustedApp(paramString2, this.mPackageManager);
    }
    if (str.equals(paramString2)) {
      return TrustedAppUtils.isTrustedApp(paramString1, this.mPackageManager);
    }
    return false;
  }
  
  public Drawable getActivityIcon(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return this.mPackageManager.getActivityIcon(paramComponentName);
  }
  
  public Drawable getActivityIcon(Intent paramIntent)
    throws PackageManager.NameNotFoundException
  {
    return this.mPackageManager.getActivityIcon(paramIntent);
  }
  
  public ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mPackageManager.getActivityInfo(paramComponentName, paramInt);
  }
  
  public List<PermissionGroupInfo> getAllPermissionGroups(int paramInt)
  {
    return this.mPackageManager.getAllPermissionGroups(paramInt);
  }
  
  public int getApplicationEnabledSetting(String paramString)
  {
    return this.mPackageManager.getApplicationEnabledSetting(paramString);
  }
  
  public Drawable getApplicationIcon(ApplicationInfo paramApplicationInfo)
  {
    return this.mPackageManager.getApplicationIcon(paramApplicationInfo);
  }
  
  public Drawable getApplicationIcon(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.mPackageManager.getApplicationIcon(paramString);
  }
  
  public ApplicationInfo getApplicationInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mPackageManager.getApplicationInfo(paramString, paramInt);
  }
  
  public CharSequence getApplicationLabel(ApplicationInfo paramApplicationInfo)
  {
    return this.mPackageManager.getApplicationLabel(paramApplicationInfo);
  }
  
  public int getComponentEnabledSetting(ComponentName paramComponentName)
  {
    return this.mPackageManager.getComponentEnabledSetting(paramComponentName);
  }
  
  public Drawable getDefaultActivityIcon()
  {
    return this.mPackageManager.getDefaultActivityIcon();
  }
  
  public Drawable getDrawable(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.mPackageManager.getDrawable(paramString, paramInt, paramApplicationInfo);
  }
  
  public List<ApplicationInfo> getInstalledApplications(int paramInt)
  {
    return this.mPackageManager.getInstalledApplications(paramInt);
  }
  
  public List<PackageInfo> getInstalledPackages(int paramInt)
  {
    return this.mPackageManager.getInstalledPackages(paramInt);
  }
  
  public String getInstallerPackageName(String paramString)
  {
    return this.mPackageManager.getInstallerPackageName(paramString);
  }
  
  public InstrumentationInfo getInstrumentationInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mPackageManager.getInstrumentationInfo(paramComponentName, paramInt);
  }
  
  public Intent getLaunchIntentForPackage(String paramString)
  {
    return this.mPackageManager.getLaunchIntentForPackage(paramString);
  }
  
  public String getNameForUid(int paramInt)
  {
    return this.mPackageManager.getNameForUid(paramInt);
  }
  
  public PackageInfo getPackageArchiveInfo(String paramString, int paramInt)
  {
    return this.mPackageManager.getPackageArchiveInfo(paramString, paramInt);
  }
  
  public int[] getPackageGids(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.mPackageManager.getPackageGids(paramString);
  }
  
  public PackageInfo getPackageInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mPackageManager.getPackageInfo(paramString, paramInt);
  }
  
  public String[] getPackagesForUid(int paramInt)
  {
    return this.mPackageManager.getPackagesForUid(paramInt);
  }
  
  public PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mPackageManager.getPermissionGroupInfo(paramString, paramInt);
  }
  
  public PermissionInfo getPermissionInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mPackageManager.getPermissionInfo(paramString, paramInt);
  }
  
  public int getPreferredActivities(List<IntentFilter> paramList, List<ComponentName> paramList1, String paramString)
  {
    return this.mPackageManager.getPreferredActivities(paramList, paramList1, paramString);
  }
  
  public List<PackageInfo> getPreferredPackages(int paramInt)
  {
    return this.mPackageManager.getPreferredPackages(paramInt);
  }
  
  public ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mPackageManager.getReceiverInfo(paramComponentName, paramInt);
  }
  
  public Resources getResourcesForActivity(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return this.mPackageManager.getResourcesForActivity(paramComponentName);
  }
  
  public Resources getResourcesForApplication(ApplicationInfo paramApplicationInfo)
    throws PackageManager.NameNotFoundException
  {
    return this.mPackageManager.getResourcesForApplication(paramApplicationInfo);
  }
  
  public Resources getResourcesForApplication(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.mPackageManager.getResourcesForApplication(paramString);
  }
  
  public ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mPackageManager.getServiceInfo(paramComponentName, paramInt);
  }
  
  public FeatureInfo[] getSystemAvailableFeatures()
  {
    return this.mPackageManager.getSystemAvailableFeatures();
  }
  
  public String[] getSystemSharedLibraryNames()
  {
    return this.mPackageManager.getSystemSharedLibraryNames();
  }
  
  public CharSequence getText(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.mPackageManager.getText(paramString, paramInt, paramApplicationInfo);
  }
  
  public XmlResourceParser getXml(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.mPackageManager.getXml(paramString, paramInt, paramApplicationInfo);
  }
  
  public boolean hasSystemFeature(String paramString)
  {
    return this.mPackageManager.hasSystemFeature(paramString);
  }
  
  public boolean isSafeMode()
  {
    return this.mPackageManager.isSafeMode();
  }
  
  public List<ResolveInfo> queryBroadcastReceivers(Intent paramIntent, int paramInt)
  {
    return this.mPackageManager.queryBroadcastReceivers(paramIntent, paramInt);
  }
  
  public List<ProviderInfo> queryContentProviders(String paramString, int paramInt1, int paramInt2)
  {
    return this.mPackageManager.queryContentProviders(paramString, paramInt1, paramInt2);
  }
  
  public List<InstrumentationInfo> queryInstrumentation(String paramString, int paramInt)
  {
    return this.mPackageManager.queryInstrumentation(paramString, paramInt);
  }
  
  public List<ResolveInfo> queryIntentActivities(Intent paramIntent, int paramInt)
  {
    return this.mPackageManager.queryIntentActivities(paramIntent, paramInt);
  }
  
  public List<ResolveInfo> queryIntentActivityOptions(ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt)
  {
    return this.mPackageManager.queryIntentActivityOptions(paramComponentName, paramArrayOfIntent, paramIntent, paramInt);
  }
  
  public List<ResolveInfo> queryIntentServices(Intent paramIntent, int paramInt)
  {
    return this.mPackageManager.queryIntentServices(paramIntent, paramInt);
  }
  
  public List<PermissionInfo> queryPermissionsByGroup(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.mPackageManager.queryPermissionsByGroup(paramString, paramInt);
  }
  
  public void removePackageFromPreferred(String paramString)
  {
    this.mPackageManager.removePackageFromPreferred(paramString);
  }
  
  public void removePermission(String paramString)
  {
    this.mPackageManager.removePermission(paramString);
  }
  
  public ResolveInfo resolveActivity(Intent paramIntent, int paramInt)
  {
    return this.mPackageManager.resolveActivity(paramIntent, paramInt);
  }
  
  public ProviderInfo resolveContentProvider(String paramString, int paramInt)
  {
    return this.mPackageManager.resolveContentProvider(paramString, paramInt);
  }
  
  public ResolveInfo resolveService(Intent paramIntent, int paramInt)
  {
    return this.mPackageManager.resolveService(paramIntent, paramInt);
  }
  
  public void setApplicationEnabledSetting(String paramString, int paramInt1, int paramInt2)
  {
    this.mPackageManager.setApplicationEnabledSetting(paramString, paramInt1, paramInt2);
  }
  
  public void setComponentEnabledSetting(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    this.mPackageManager.setComponentEnabledSetting(paramComponentName, paramInt1, paramInt2);
  }
}
