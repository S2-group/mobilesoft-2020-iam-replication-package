package android.test.mock;

import android.content.ComponentName;
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
import java.util.List;

public class MockPackageManager
  extends PackageManager
{
  public MockPackageManager()
  {
    throw new RuntimeException("Stub!");
  }
  
  public void addPackageToPreferred(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPermission(PermissionInfo paramPermissionInfo)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPermissionAsync(PermissionInfo paramPermissionInfo)
  {
    throw new RuntimeException("Stub!");
  }
  
  public void addPreferredActivity(IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName)
  {
    throw new RuntimeException("Stub!");
  }
  
  public String[] canonicalToCurrentPackageNames(String[] paramArrayOfString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public int checkPermission(String paramString1, String paramString2)
  {
    throw new RuntimeException("Stub!");
  }
  
  public int checkSignatures(int paramInt1, int paramInt2)
  {
    throw new RuntimeException("Stub!");
  }
  
  public int checkSignatures(String paramString1, String paramString2)
  {
    throw new RuntimeException("Stub!");
  }
  
  public void clearPackagePreferredActivities(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public String[] currentToCanonicalPackageNames(String[] paramArrayOfString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public Drawable getActivityIcon(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    throw new RuntimeException("Stub!");
  }
  
  public Drawable getActivityIcon(Intent paramIntent)
    throws PackageManager.NameNotFoundException
  {
    throw new RuntimeException("Stub!");
  }
  
  public ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    throw new RuntimeException("Stub!");
  }
  
  public Drawable getActivityLogo(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    throw new RuntimeException("Stub!");
  }
  
  public Drawable getActivityLogo(Intent paramIntent)
    throws PackageManager.NameNotFoundException
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<PermissionGroupInfo> getAllPermissionGroups(int paramInt)
  {
    throw new RuntimeException("Stub!");
  }
  
  public int getApplicationEnabledSetting(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public Drawable getApplicationIcon(ApplicationInfo paramApplicationInfo)
  {
    throw new RuntimeException("Stub!");
  }
  
  public Drawable getApplicationIcon(String paramString)
    throws PackageManager.NameNotFoundException
  {
    throw new RuntimeException("Stub!");
  }
  
  public ApplicationInfo getApplicationInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    throw new RuntimeException("Stub!");
  }
  
  public CharSequence getApplicationLabel(ApplicationInfo paramApplicationInfo)
  {
    throw new RuntimeException("Stub!");
  }
  
  public Drawable getApplicationLogo(ApplicationInfo paramApplicationInfo)
  {
    throw new RuntimeException("Stub!");
  }
  
  public Drawable getApplicationLogo(String paramString)
    throws PackageManager.NameNotFoundException
  {
    throw new RuntimeException("Stub!");
  }
  
  public int getComponentEnabledSetting(ComponentName paramComponentName)
  {
    throw new RuntimeException("Stub!");
  }
  
  public Drawable getDefaultActivityIcon()
  {
    throw new RuntimeException("Stub!");
  }
  
  public Drawable getDrawable(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<ApplicationInfo> getInstalledApplications(int paramInt)
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<PackageInfo> getInstalledPackages(int paramInt)
  {
    throw new RuntimeException("Stub!");
  }
  
  public String getInstallerPackageName(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public InstrumentationInfo getInstrumentationInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    throw new RuntimeException("Stub!");
  }
  
  public Intent getLaunchIntentForPackage(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public String getNameForUid(int paramInt)
  {
    throw new RuntimeException("Stub!");
  }
  
  public PackageInfo getPackageArchiveInfo(String paramString, int paramInt)
  {
    throw new RuntimeException("Stub!");
  }
  
  public int[] getPackageGids(String paramString)
    throws PackageManager.NameNotFoundException
  {
    throw new RuntimeException("Stub!");
  }
  
  public PackageInfo getPackageInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    throw new RuntimeException("Stub!");
  }
  
  public String[] getPackagesForUid(int paramInt)
  {
    throw new RuntimeException("Stub!");
  }
  
  public PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    throw new RuntimeException("Stub!");
  }
  
  public PermissionInfo getPermissionInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    throw new RuntimeException("Stub!");
  }
  
  public int getPreferredActivities(List<IntentFilter> paramList, List<ComponentName> paramList1, String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<PackageInfo> getPreferredPackages(int paramInt)
  {
    throw new RuntimeException("Stub!");
  }
  
  public ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    throw new RuntimeException("Stub!");
  }
  
  public ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    throw new RuntimeException("Stub!");
  }
  
  public Resources getResourcesForActivity(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    throw new RuntimeException("Stub!");
  }
  
  public Resources getResourcesForApplication(ApplicationInfo paramApplicationInfo)
  {
    throw new RuntimeException("Stub!");
  }
  
  public Resources getResourcesForApplication(String paramString)
    throws PackageManager.NameNotFoundException
  {
    throw new RuntimeException("Stub!");
  }
  
  public ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    throw new RuntimeException("Stub!");
  }
  
  public FeatureInfo[] getSystemAvailableFeatures()
  {
    throw new RuntimeException("Stub!");
  }
  
  public String[] getSystemSharedLibraryNames()
  {
    throw new RuntimeException("Stub!");
  }
  
  public CharSequence getText(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    throw new RuntimeException("Stub!");
  }
  
  public XmlResourceParser getXml(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean hasSystemFeature(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean isSafeMode()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<ResolveInfo> queryBroadcastReceivers(Intent paramIntent, int paramInt)
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<ProviderInfo> queryContentProviders(String paramString, int paramInt1, int paramInt2)
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<InstrumentationInfo> queryInstrumentation(String paramString, int paramInt)
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<ResolveInfo> queryIntentActivities(Intent paramIntent, int paramInt)
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<ResolveInfo> queryIntentActivityOptions(ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt)
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<ResolveInfo> queryIntentServices(Intent paramIntent, int paramInt)
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<PermissionInfo> queryPermissionsByGroup(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    throw new RuntimeException("Stub!");
  }
  
  public void removePackageFromPreferred(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public void removePermission(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public ResolveInfo resolveActivity(Intent paramIntent, int paramInt)
  {
    throw new RuntimeException("Stub!");
  }
  
  public ProviderInfo resolveContentProvider(String paramString, int paramInt)
  {
    throw new RuntimeException("Stub!");
  }
  
  public ResolveInfo resolveService(Intent paramIntent, int paramInt)
  {
    throw new RuntimeException("Stub!");
  }
  
  public void setApplicationEnabledSetting(String paramString, int paramInt1, int paramInt2)
  {
    throw new RuntimeException("Stub!");
  }
  
  public void setComponentEnabledSetting(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    throw new RuntimeException("Stub!");
  }
}
