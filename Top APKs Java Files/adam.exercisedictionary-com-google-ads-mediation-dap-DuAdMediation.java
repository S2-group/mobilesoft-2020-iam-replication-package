package com.google.ads.mediation.dap;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
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
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.duapps.ad.base.DuAdNetwork;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONStringer;

public class DuAdMediation
{
  private static boolean DEBUG = false;
  public static final String KEY_ALL_PLACEMENT_ID = "ALL_PID";
  public static final String KEY_ALL_VIDEO_PLACEMENT_ID = "ALL_V_PID";
  public static final String KEY_APP_ID = "appId";
  public static final String KEY_APP_LICENSE = "app_license";
  public static final String KEY_DAP_PID = "placementId";
  private static final String TAG = DuAdMediation.class.getSimpleName();
  private static Handler handler;
  private static HashSet<Integer> initializedPlacementIds = new HashSet();
  private static boolean isInitialized;
  
  static
  {
    DEBUG = false;
    isInitialized = false;
  }
  
  public DuAdMediation() {}
  
  static String buildJsonFromPidsNative(@NonNull Collection<Integer> paramCollection, String paramString)
  {
    try
    {
      paramString = new JSONStringer().object().key(paramString).array();
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext())
      {
        Integer localInteger = (Integer)paramCollection.next();
        paramString.object().key("pid").value(localInteger).endObject();
      }
      paramString.endArray().endObject();
    }
    catch (JSONException paramCollection)
    {
      paramCollection.printStackTrace();
      return null;
    }
    paramCollection = paramString.toString();
    return paramCollection;
  }
  
  public static boolean checkClassExist(String paramString)
  {
    try
    {
      Class.forName(paramString);
      return true;
    }
    catch (ClassNotFoundException paramString) {}
    return false;
  }
  
  public static void d(String paramString1, String paramString2)
  {
    if (DEBUG)
    {
      String str = Thread.currentThread().getName();
      Log.d(TAG, "[" + paramString1 + "]: " + "<" + str + "> " + paramString2);
    }
  }
  
  static void initializeSDK(Context paramContext, Bundle paramBundle, int paramInt, String paramString)
  {
    if (!isInitialized)
    {
      paramContext = paramContext.getApplicationContext();
      int k = 0;
      int m = 0;
      int j = k;
      int i = m;
      if (paramBundle != null)
      {
        paramBundle = paramBundle.getIntegerArrayList("ALL_PID");
        j = k;
        i = m;
        if (paramBundle != null)
        {
          initializedPlacementIds.addAll(paramBundle);
          i = 1;
          j = 1;
        }
      }
      if (!initializedPlacementIds.contains(Integer.valueOf(paramInt)))
      {
        initializedPlacementIds.add(Integer.valueOf(paramInt));
        i = 1;
      }
      if (i != 0)
      {
        paramBundle = buildJsonFromPidsNative(initializedPlacementIds, "native");
        d(TAG, "init config json is : " + paramBundle);
        DuAdNetwork.init(setAppIdInMeta(paramContext, paramString), paramBundle);
        if (j == 0) {
          break label147;
        }
        isInitialized = true;
      }
    }
    return;
    label147:
    paramContext = "Only the following placementIds " + initializedPlacementIds + " are initialized. " + "It is strongly recommended to use DuAdExtrasBundleBuilder.addAllPlacementId() to pass all " + "your valid placement IDs (for Native, Banner and Interstitial ads) when making an Ad Request, " + "so that the DuAdNetwork can be properly initialized.";
    Log.e(TAG, paramContext);
  }
  
  public static void removeAllCallbacks()
  {
    if (handler != null) {}
    try
    {
      handler.removeCallbacksAndMessages(null);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public static void runOnUIThread(Runnable paramRunnable)
  {
    try
    {
      if (handler == null) {
        handler = new Handler(Looper.getMainLooper());
      }
      handler.post(paramRunnable);
      return;
    }
    catch (Exception paramRunnable)
    {
      paramRunnable.printStackTrace();
    }
  }
  
  static Context setAppIdInMeta(Context paramContext, String paramString)
  {
    int m = 1;
    int k = 1;
    j = m;
    for (;;)
    {
      try
      {
        Bundle localBundle = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData;
        i = k;
        localObject = paramContext;
        if (localBundle != null)
        {
          j = m;
          localObject = localBundle.getString("app_license");
          j = m;
          if (TextUtils.isEmpty((CharSequence)localObject)) {
            continue;
          }
          j = m;
          d(TAG, "appId from meta is " + (String)localObject);
          i = 0;
          localObject = paramContext;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        int i = j;
        Object localObject = paramContext;
        continue;
      }
      if (i != 0)
      {
        Log.e(TAG, "No App Id found! Du Ad will not be able to make money for you properly! Have you configure it in your meta data or AdMob UI?");
        Log.e(TAG, "No App Id found! Du Ad will not be able to make money for you properly! Have you configure it in your meta data or AdMob UI?");
      }
      return localObject;
      i = k;
      localObject = paramContext;
      j = m;
      if (!TextUtils.isEmpty(paramString))
      {
        k = 0;
        i = 0;
        j = k;
        d(TAG, "appId from AdMob server is " + paramString);
        j = k;
        localObject = new AppIdContext(paramContext, paramString);
      }
    }
  }
  
  public static void setDebug(boolean paramBoolean)
  {
    DEBUG = paramBoolean;
  }
  
  private static class AppIdContext
    extends ContextWrapper
  {
    private final String appId;
    private final Context base;
    
    public AppIdContext(Context paramContext, String paramString)
    {
      super();
      this.base = paramContext;
      this.appId = paramString;
    }
    
    public Context getApplicationContext()
    {
      return this;
    }
    
    public PackageManager getPackageManager()
    {
      return new DuAdMediation.AppIdPackageManager(this, this.base.getPackageManager(), this.appId, null);
    }
  }
  
  private static class AppIdPackageManager
    extends PackageManager
  {
    private final String appId;
    private final Context context;
    private final PackageManager innerPackageManager;
    
    private AppIdPackageManager(Context paramContext, PackageManager paramPackageManager, String paramString)
    {
      this.innerPackageManager = paramPackageManager;
      this.context = paramContext;
      this.appId = paramString;
    }
    
    public void addPackageToPreferred(String paramString)
    {
      this.innerPackageManager.addPackageToPreferred(paramString);
    }
    
    public boolean addPermission(PermissionInfo paramPermissionInfo)
    {
      return this.innerPackageManager.addPermission(paramPermissionInfo);
    }
    
    public boolean addPermissionAsync(PermissionInfo paramPermissionInfo)
    {
      return this.innerPackageManager.addPermissionAsync(paramPermissionInfo);
    }
    
    public void addPreferredActivity(IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName)
    {
      this.innerPackageManager.addPreferredActivity(paramIntentFilter, paramInt, paramArrayOfComponentName, paramComponentName);
    }
    
    @TargetApi(26)
    public boolean canRequestPackageInstalls()
    {
      return this.innerPackageManager.canRequestPackageInstalls();
    }
    
    public String[] canonicalToCurrentPackageNames(String[] paramArrayOfString)
    {
      return this.innerPackageManager.canonicalToCurrentPackageNames(paramArrayOfString);
    }
    
    public int checkPermission(String paramString1, String paramString2)
    {
      return this.innerPackageManager.checkPermission(paramString1, paramString2);
    }
    
    public int checkSignatures(int paramInt1, int paramInt2)
    {
      return this.innerPackageManager.checkSignatures(paramInt1, paramInt2);
    }
    
    public int checkSignatures(String paramString1, String paramString2)
    {
      return this.innerPackageManager.checkSignatures(paramString1, paramString2);
    }
    
    @TargetApi(26)
    public void clearInstantAppCookie()
    {
      this.innerPackageManager.clearInstantAppCookie();
    }
    
    public void clearPackagePreferredActivities(String paramString)
    {
      this.innerPackageManager.clearPackagePreferredActivities(paramString);
    }
    
    public String[] currentToCanonicalPackageNames(String[] paramArrayOfString)
    {
      return this.innerPackageManager.currentToCanonicalPackageNames(paramArrayOfString);
    }
    
    @TargetApi(17)
    public void extendVerificationTimeout(int paramInt1, int paramInt2, long paramLong)
    {
      this.innerPackageManager.extendVerificationTimeout(paramInt1, paramInt2, paramLong);
    }
    
    @TargetApi(20)
    public Drawable getActivityBanner(ComponentName paramComponentName)
      throws PackageManager.NameNotFoundException
    {
      return this.innerPackageManager.getActivityBanner(paramComponentName);
    }
    
    @TargetApi(20)
    public Drawable getActivityBanner(Intent paramIntent)
      throws PackageManager.NameNotFoundException
    {
      return this.innerPackageManager.getActivityBanner(paramIntent);
    }
    
    public Drawable getActivityIcon(ComponentName paramComponentName)
      throws PackageManager.NameNotFoundException
    {
      return this.innerPackageManager.getActivityIcon(paramComponentName);
    }
    
    public Drawable getActivityIcon(Intent paramIntent)
      throws PackageManager.NameNotFoundException
    {
      return this.innerPackageManager.getActivityIcon(paramIntent);
    }
    
    public ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.innerPackageManager.getActivityInfo(paramComponentName, paramInt);
    }
    
    public Drawable getActivityLogo(ComponentName paramComponentName)
      throws PackageManager.NameNotFoundException
    {
      return this.innerPackageManager.getActivityLogo(paramComponentName);
    }
    
    public Drawable getActivityLogo(Intent paramIntent)
      throws PackageManager.NameNotFoundException
    {
      return this.innerPackageManager.getActivityLogo(paramIntent);
    }
    
    public List<PermissionGroupInfo> getAllPermissionGroups(int paramInt)
    {
      return null;
    }
    
    @TargetApi(20)
    public Drawable getApplicationBanner(ApplicationInfo paramApplicationInfo)
    {
      return this.innerPackageManager.getApplicationBanner(paramApplicationInfo);
    }
    
    @TargetApi(20)
    public Drawable getApplicationBanner(String paramString)
      throws PackageManager.NameNotFoundException
    {
      return this.innerPackageManager.getApplicationBanner(paramString);
    }
    
    public int getApplicationEnabledSetting(String paramString)
    {
      return this.innerPackageManager.getApplicationEnabledSetting(paramString);
    }
    
    public Drawable getApplicationIcon(ApplicationInfo paramApplicationInfo)
    {
      return this.innerPackageManager.getApplicationIcon(paramApplicationInfo);
    }
    
    public Drawable getApplicationIcon(String paramString)
      throws PackageManager.NameNotFoundException
    {
      return this.innerPackageManager.getApplicationIcon(paramString);
    }
    
    public ApplicationInfo getApplicationInfo(String paramString, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      if (paramString.equals(this.context.getPackageName()))
      {
        paramString = this.innerPackageManager.getApplicationInfo(paramString, paramInt);
        paramString.metaData.putString("app_license", this.appId);
        return paramString;
      }
      return this.innerPackageManager.getApplicationInfo(paramString, paramInt);
    }
    
    public CharSequence getApplicationLabel(ApplicationInfo paramApplicationInfo)
    {
      return this.innerPackageManager.getApplicationLabel(paramApplicationInfo);
    }
    
    public Drawable getApplicationLogo(ApplicationInfo paramApplicationInfo)
    {
      return this.innerPackageManager.getApplicationLogo(paramApplicationInfo);
    }
    
    public Drawable getApplicationLogo(String paramString)
      throws PackageManager.NameNotFoundException
    {
      return this.innerPackageManager.getApplicationLogo(paramString);
    }
    
    @TargetApi(26)
    public ChangedPackages getChangedPackages(int paramInt)
    {
      return this.innerPackageManager.getChangedPackages(paramInt);
    }
    
    public int getComponentEnabledSetting(ComponentName paramComponentName)
    {
      return this.innerPackageManager.getComponentEnabledSetting(paramComponentName);
    }
    
    public Drawable getDefaultActivityIcon()
    {
      return this.innerPackageManager.getDefaultActivityIcon();
    }
    
    public Drawable getDrawable(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
    {
      return this.innerPackageManager.getDrawable(paramString, paramInt, paramApplicationInfo);
    }
    
    public List<ApplicationInfo> getInstalledApplications(int paramInt)
    {
      return this.innerPackageManager.getInstalledApplications(paramInt);
    }
    
    public List<PackageInfo> getInstalledPackages(int paramInt)
    {
      return this.innerPackageManager.getInstalledPackages(paramInt);
    }
    
    public String getInstallerPackageName(String paramString)
    {
      return this.innerPackageManager.getInstallerPackageName(paramString);
    }
    
    @TargetApi(26)
    public byte[] getInstantAppCookie()
    {
      return this.innerPackageManager.getInstantAppCookie();
    }
    
    @TargetApi(26)
    public int getInstantAppCookieMaxBytes()
    {
      return this.innerPackageManager.getInstantAppCookieMaxBytes();
    }
    
    public InstrumentationInfo getInstrumentationInfo(ComponentName paramComponentName, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.innerPackageManager.getInstrumentationInfo(paramComponentName, paramInt);
    }
    
    public Intent getLaunchIntentForPackage(String paramString)
    {
      return this.innerPackageManager.getLaunchIntentForPackage(paramString);
    }
    
    @TargetApi(21)
    public Intent getLeanbackLaunchIntentForPackage(String paramString)
    {
      return this.innerPackageManager.getLeanbackLaunchIntentForPackage(paramString);
    }
    
    public String getNameForUid(int paramInt)
    {
      return this.innerPackageManager.getNameForUid(paramInt);
    }
    
    public int[] getPackageGids(String paramString)
      throws PackageManager.NameNotFoundException
    {
      return this.innerPackageManager.getPackageGids(paramString);
    }
    
    @TargetApi(24)
    public int[] getPackageGids(String paramString, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.innerPackageManager.getPackageGids(paramString, paramInt);
    }
    
    @TargetApi(26)
    public PackageInfo getPackageInfo(VersionedPackage paramVersionedPackage, int paramInt)
    {
      try
      {
        paramVersionedPackage = this.innerPackageManager.getPackageInfo(paramVersionedPackage, paramInt);
        return paramVersionedPackage;
      }
      catch (PackageManager.NameNotFoundException paramVersionedPackage)
      {
        paramVersionedPackage.printStackTrace();
      }
      return null;
    }
    
    public PackageInfo getPackageInfo(String paramString, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.innerPackageManager.getPackageInfo(paramString, paramInt);
    }
    
    @TargetApi(21)
    public PackageInstaller getPackageInstaller()
    {
      return this.innerPackageManager.getPackageInstaller();
    }
    
    @TargetApi(24)
    public int getPackageUid(String paramString, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.innerPackageManager.getPackageUid(paramString, paramInt);
    }
    
    public String[] getPackagesForUid(int paramInt)
    {
      return this.innerPackageManager.getPackagesForUid(paramInt);
    }
    
    @TargetApi(18)
    public List<PackageInfo> getPackagesHoldingPermissions(String[] paramArrayOfString, int paramInt)
    {
      return this.innerPackageManager.getPackagesHoldingPermissions(paramArrayOfString, paramInt);
    }
    
    public PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.innerPackageManager.getPermissionGroupInfo(paramString, paramInt);
    }
    
    public PermissionInfo getPermissionInfo(String paramString, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.innerPackageManager.getPermissionInfo(paramString, paramInt);
    }
    
    public int getPreferredActivities(List<IntentFilter> paramList, List<ComponentName> paramList1, String paramString)
    {
      return this.innerPackageManager.getPreferredActivities(paramList, paramList1, paramString);
    }
    
    public List<PackageInfo> getPreferredPackages(int paramInt)
    {
      return this.innerPackageManager.getPreferredPackages(paramInt);
    }
    
    public ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.innerPackageManager.getProviderInfo(paramComponentName, paramInt);
    }
    
    public ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.innerPackageManager.getReceiverInfo(paramComponentName, paramInt);
    }
    
    public Resources getResourcesForActivity(ComponentName paramComponentName)
      throws PackageManager.NameNotFoundException
    {
      return this.innerPackageManager.getResourcesForActivity(paramComponentName);
    }
    
    public Resources getResourcesForApplication(ApplicationInfo paramApplicationInfo)
      throws PackageManager.NameNotFoundException
    {
      return this.innerPackageManager.getResourcesForApplication(paramApplicationInfo);
    }
    
    public Resources getResourcesForApplication(String paramString)
      throws PackageManager.NameNotFoundException
    {
      return this.innerPackageManager.getResourcesForApplication(paramString);
    }
    
    public ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.innerPackageManager.getServiceInfo(paramComponentName, paramInt);
    }
    
    @TargetApi(26)
    public List<SharedLibraryInfo> getSharedLibraries(int paramInt)
    {
      return this.innerPackageManager.getSharedLibraries(paramInt);
    }
    
    public FeatureInfo[] getSystemAvailableFeatures()
    {
      return this.innerPackageManager.getSystemAvailableFeatures();
    }
    
    public String[] getSystemSharedLibraryNames()
    {
      return this.innerPackageManager.getSystemSharedLibraryNames();
    }
    
    public CharSequence getText(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
    {
      return this.innerPackageManager.getText(paramString, paramInt, paramApplicationInfo);
    }
    
    @TargetApi(21)
    public Drawable getUserBadgedDrawableForDensity(Drawable paramDrawable, UserHandle paramUserHandle, Rect paramRect, int paramInt)
    {
      return this.innerPackageManager.getUserBadgedDrawableForDensity(paramDrawable, paramUserHandle, paramRect, paramInt);
    }
    
    @TargetApi(21)
    public Drawable getUserBadgedIcon(Drawable paramDrawable, UserHandle paramUserHandle)
    {
      return this.innerPackageManager.getUserBadgedIcon(paramDrawable, paramUserHandle);
    }
    
    @TargetApi(21)
    public CharSequence getUserBadgedLabel(CharSequence paramCharSequence, UserHandle paramUserHandle)
    {
      return this.innerPackageManager.getUserBadgedLabel(paramCharSequence, paramUserHandle);
    }
    
    public XmlResourceParser getXml(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
    {
      return this.innerPackageManager.getXml(paramString, paramInt, paramApplicationInfo);
    }
    
    public boolean hasSystemFeature(String paramString)
    {
      return this.innerPackageManager.hasSystemFeature(paramString);
    }
    
    public boolean hasSystemFeature(String paramString, int paramInt)
    {
      return false;
    }
    
    @TargetApi(26)
    public boolean isInstantApp()
    {
      return this.innerPackageManager.isInstantApp();
    }
    
    @TargetApi(26)
    public boolean isInstantApp(String paramString)
    {
      return this.innerPackageManager.isInstantApp(paramString);
    }
    
    public boolean isPermissionRevokedByPolicy(@NonNull String paramString1, @NonNull String paramString2)
    {
      return false;
    }
    
    public boolean isSafeMode()
    {
      return this.innerPackageManager.isSafeMode();
    }
    
    public List<ResolveInfo> queryBroadcastReceivers(Intent paramIntent, int paramInt)
    {
      return this.innerPackageManager.queryBroadcastReceivers(paramIntent, paramInt);
    }
    
    public List<ProviderInfo> queryContentProviders(String paramString, int paramInt1, int paramInt2)
    {
      return this.innerPackageManager.queryContentProviders(paramString, paramInt1, paramInt2);
    }
    
    public List<InstrumentationInfo> queryInstrumentation(String paramString, int paramInt)
    {
      return this.innerPackageManager.queryInstrumentation(paramString, paramInt);
    }
    
    public List<ResolveInfo> queryIntentActivities(Intent paramIntent, int paramInt)
    {
      return this.innerPackageManager.queryIntentActivities(paramIntent, paramInt);
    }
    
    public List<ResolveInfo> queryIntentActivityOptions(ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt)
    {
      return this.innerPackageManager.queryIntentActivityOptions(paramComponentName, paramArrayOfIntent, paramIntent, paramInt);
    }
    
    @TargetApi(19)
    public List<ResolveInfo> queryIntentContentProviders(Intent paramIntent, int paramInt)
    {
      return this.innerPackageManager.queryIntentContentProviders(paramIntent, paramInt);
    }
    
    public List<ResolveInfo> queryIntentServices(Intent paramIntent, int paramInt)
    {
      return this.innerPackageManager.queryIntentServices(paramIntent, paramInt);
    }
    
    public List<PermissionInfo> queryPermissionsByGroup(String paramString, int paramInt)
      throws PackageManager.NameNotFoundException
    {
      return this.innerPackageManager.queryPermissionsByGroup(paramString, paramInt);
    }
    
    public void removePackageFromPreferred(String paramString)
    {
      this.innerPackageManager.removePackageFromPreferred(paramString);
    }
    
    public void removePermission(String paramString)
    {
      this.innerPackageManager.removePermission(paramString);
    }
    
    public ResolveInfo resolveActivity(Intent paramIntent, int paramInt)
    {
      return this.innerPackageManager.resolveActivity(paramIntent, paramInt);
    }
    
    public ProviderInfo resolveContentProvider(String paramString, int paramInt)
    {
      return this.innerPackageManager.resolveContentProvider(paramString, paramInt);
    }
    
    public ResolveInfo resolveService(Intent paramIntent, int paramInt)
    {
      return this.innerPackageManager.resolveService(paramIntent, paramInt);
    }
    
    @TargetApi(26)
    public void setApplicationCategoryHint(@NonNull String paramString, int paramInt)
    {
      this.innerPackageManager.setApplicationCategoryHint(paramString, paramInt);
    }
    
    public void setApplicationEnabledSetting(String paramString, int paramInt1, int paramInt2)
    {
      this.innerPackageManager.setApplicationEnabledSetting(paramString, paramInt1, paramInt2);
    }
    
    public void setComponentEnabledSetting(ComponentName paramComponentName, int paramInt1, int paramInt2)
    {
      this.innerPackageManager.setComponentEnabledSetting(paramComponentName, paramInt1, paramInt2);
    }
    
    public void setInstallerPackageName(String paramString1, String paramString2)
    {
      this.innerPackageManager.setInstallerPackageName(paramString1, paramString2);
    }
    
    @TargetApi(26)
    public void updateInstantAppCookie(@Nullable byte[] paramArrayOfByte)
    {
      this.innerPackageManager.updateInstantAppCookie(paramArrayOfByte);
    }
    
    public void verifyPendingInstall(int paramInt1, int paramInt2)
    {
      this.innerPackageManager.verifyPendingInstall(paramInt1, paramInt2);
    }
  }
}
