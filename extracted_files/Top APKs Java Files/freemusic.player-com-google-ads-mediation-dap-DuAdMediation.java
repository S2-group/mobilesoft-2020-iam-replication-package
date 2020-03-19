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
  public static final String KEY_ALL_PLACEMENT_ID = "ALL_PID";
  public static final String KEY_ALL_VIDEO_PLACEMENT_ID = "ALL_V_PID";
  public static final String KEY_APP_ID = "appId";
  public static final String KEY_APP_LICENSE = "app_license";
  public static final String KEY_DAP_PID = "placementId";
  private static final String a = "DuAdMediation";
  private static boolean b = false;
  private static boolean c = false;
  private static HashSet<Integer> d = new HashSet();
  private static Handler e;
  
  public DuAdMediation() {}
  
  static Context a(Context paramContext, String paramString)
  {
    int j = 0;
    int i = 0;
    try
    {
      Object localObject = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData;
      if (localObject != null)
      {
        localObject = ((Bundle)localObject).getString("app_license");
        if (!TextUtils.isEmpty((CharSequence)localObject))
        {
          d(a, "appId from meta is ".concat(String.valueOf(localObject)));
          i = j;
          break label125;
        }
        boolean bool = TextUtils.isEmpty(paramString);
        if (!bool) {
          try
          {
            d(a, "appId from AdMob server is ".concat(String.valueOf(paramString)));
            paramString = new AppIdContext(paramContext, paramString);
            paramContext = paramString;
            i = j;
          }
          catch (Exception paramString)
          {
            break label121;
          }
        }
      }
      i = 1;
    }
    catch (Exception paramString)
    {
      i = 1;
      label121:
      paramString.printStackTrace();
    }
    label125:
    if (i != 0)
    {
      Log.e(a, "No App Id found! Du Ad will not be able to make money for you properly! Have you configure it in your meta data or AdMob UI?");
      Log.e(a, "No App Id found! Du Ad will not be able to make money for you properly! Have you configure it in your meta data or AdMob UI?");
    }
    return paramContext;
  }
  
  static String a(@NonNull Collection<Integer> paramCollection, String paramString)
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
      paramCollection = paramString.toString();
      return paramCollection;
    }
    catch (JSONException paramCollection)
    {
      paramCollection.printStackTrace();
    }
    return null;
  }
  
  static void a(Context paramContext, Bundle paramBundle, int paramInt, String paramString)
  {
    if (!c)
    {
      paramContext = paramContext.getApplicationContext();
      int i = 0;
      if (paramBundle != null)
      {
        paramBundle = paramBundle.getIntegerArrayList("ALL_PID");
        if (paramBundle != null)
        {
          d.addAll(paramBundle);
          j = 1;
          i = 1;
          break label49;
        }
      }
      int j = 0;
      label49:
      if (!d.contains(Integer.valueOf(paramInt)))
      {
        d.add(Integer.valueOf(paramInt));
        i = 1;
      }
      if (i != 0)
      {
        paramBundle = a(d, "native");
        d(a, "init config json is : ".concat(String.valueOf(paramBundle)));
        DuAdNetwork.init(a(paramContext, paramString), paramBundle);
        if (j != 0)
        {
          c = true;
          return;
        }
        paramContext = new StringBuilder("Only the following placementIds ");
        paramContext.append(d);
        paramContext.append(" are initialized. It is strongly recommended to use DuAdExtrasBundleBuilder.addAllPlacementId() to pass all your valid placement IDs (for Native, Banner and Interstitial ads) when making an Ad Request, so that the DuAdNetwork can be properly initialized.");
        paramContext = paramContext.toString();
        Log.e(a, paramContext);
      }
    }
  }
  
  public static boolean checkClassExist(String paramString)
  {
    try
    {
      Class.forName(paramString);
      return true;
    }
    catch (ClassNotFoundException paramString)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static void d(String paramString1, String paramString2)
  {
    if (b)
    {
      String str = Thread.currentThread().getName();
      StringBuilder localStringBuilder = new StringBuilder("[");
      localStringBuilder.append(paramString1);
      localStringBuilder.append("]: <");
      localStringBuilder.append(str);
      localStringBuilder.append("> ");
      localStringBuilder.append(paramString2);
    }
  }
  
  public static void removeAllCallbacks()
  {
    if (e != null) {
      try
      {
        e.removeCallbacksAndMessages(null);
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }
  
  public static void runOnUIThread(Runnable paramRunnable)
  {
    try
    {
      if (e == null) {
        e = new Handler(Looper.getMainLooper());
      }
      e.post(paramRunnable);
      return;
    }
    catch (Exception paramRunnable)
    {
      paramRunnable.printStackTrace();
    }
  }
  
  public static void setDebug(boolean paramBoolean)
  {
    b = paramBoolean;
  }
  
  static class AppIdContext
    extends ContextWrapper
  {
    private final String a;
    private final Context b;
    
    public AppIdContext(Context paramContext, String paramString)
    {
      super();
      this.b = paramContext;
      this.a = paramString;
    }
    
    public Context getApplicationContext()
    {
      return this;
    }
    
    public PackageManager getPackageManager()
    {
      return new DuAdMediation.AppIdPackageManager(this, this.b.getPackageManager(), this.a, (byte)0);
    }
  }
  
  static class AppIdPackageManager
    extends PackageManager
  {
    private final String a;
    private final PackageManager b;
    private final Context c;
    
    private AppIdPackageManager(Context paramContext, PackageManager paramPackageManager, String paramString)
    {
      this.b = paramPackageManager;
      this.c = paramContext;
      this.a = paramString;
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
    
    @TargetApi(26)
    public boolean canRequestPackageInstalls()
    {
      return this.b.canRequestPackageInstalls();
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
    
    @TargetApi(26)
    public void clearInstantAppCookie()
    {
      this.b.clearInstantAppCookie();
    }
    
    public void clearPackagePreferredActivities(String paramString)
    {
      this.b.clearPackagePreferredActivities(paramString);
    }
    
    public String[] currentToCanonicalPackageNames(String[] paramArrayOfString)
    {
      return this.b.currentToCanonicalPackageNames(paramArrayOfString);
    }
    
    @TargetApi(17)
    public void extendVerificationTimeout(int paramInt1, int paramInt2, long paramLong)
    {
      this.b.extendVerificationTimeout(paramInt1, paramInt2, paramLong);
    }
    
    @TargetApi(20)
    public Drawable getActivityBanner(ComponentName paramComponentName)
    {
      return this.b.getActivityBanner(paramComponentName);
    }
    
    @TargetApi(20)
    public Drawable getActivityBanner(Intent paramIntent)
    {
      return this.b.getActivityBanner(paramIntent);
    }
    
    public Drawable getActivityIcon(ComponentName paramComponentName)
    {
      return this.b.getActivityIcon(paramComponentName);
    }
    
    public Drawable getActivityIcon(Intent paramIntent)
    {
      return this.b.getActivityIcon(paramIntent);
    }
    
    public ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt)
    {
      return this.b.getActivityInfo(paramComponentName, paramInt);
    }
    
    public Drawable getActivityLogo(ComponentName paramComponentName)
    {
      return this.b.getActivityLogo(paramComponentName);
    }
    
    public Drawable getActivityLogo(Intent paramIntent)
    {
      return this.b.getActivityLogo(paramIntent);
    }
    
    public List<PermissionGroupInfo> getAllPermissionGroups(int paramInt)
    {
      return null;
    }
    
    @TargetApi(20)
    public Drawable getApplicationBanner(ApplicationInfo paramApplicationInfo)
    {
      return this.b.getApplicationBanner(paramApplicationInfo);
    }
    
    @TargetApi(20)
    public Drawable getApplicationBanner(String paramString)
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
    {
      return this.b.getApplicationIcon(paramString);
    }
    
    public ApplicationInfo getApplicationInfo(String paramString, int paramInt)
    {
      if (paramString.equals(this.c.getPackageName()))
      {
        paramString = this.b.getApplicationInfo(paramString, paramInt);
        paramString.metaData.putString("app_license", this.a);
        return paramString;
      }
      return this.b.getApplicationInfo(paramString, paramInt);
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
    {
      return this.b.getApplicationLogo(paramString);
    }
    
    @TargetApi(26)
    public ChangedPackages getChangedPackages(int paramInt)
    {
      return this.b.getChangedPackages(paramInt);
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
    
    @TargetApi(26)
    public byte[] getInstantAppCookie()
    {
      return this.b.getInstantAppCookie();
    }
    
    @TargetApi(26)
    public int getInstantAppCookieMaxBytes()
    {
      return this.b.getInstantAppCookieMaxBytes();
    }
    
    public InstrumentationInfo getInstrumentationInfo(ComponentName paramComponentName, int paramInt)
    {
      return this.b.getInstrumentationInfo(paramComponentName, paramInt);
    }
    
    public Intent getLaunchIntentForPackage(String paramString)
    {
      return this.b.getLaunchIntentForPackage(paramString);
    }
    
    @TargetApi(21)
    public Intent getLeanbackLaunchIntentForPackage(String paramString)
    {
      return this.b.getLeanbackLaunchIntentForPackage(paramString);
    }
    
    public String getNameForUid(int paramInt)
    {
      return this.b.getNameForUid(paramInt);
    }
    
    public int[] getPackageGids(String paramString)
    {
      return this.b.getPackageGids(paramString);
    }
    
    @TargetApi(24)
    public int[] getPackageGids(String paramString, int paramInt)
    {
      return this.b.getPackageGids(paramString, paramInt);
    }
    
    @TargetApi(26)
    public PackageInfo getPackageInfo(VersionedPackage paramVersionedPackage, int paramInt)
    {
      try
      {
        paramVersionedPackage = this.b.getPackageInfo(paramVersionedPackage, paramInt);
        return paramVersionedPackage;
      }
      catch (PackageManager.NameNotFoundException paramVersionedPackage)
      {
        paramVersionedPackage.printStackTrace();
      }
      return null;
    }
    
    public PackageInfo getPackageInfo(String paramString, int paramInt)
    {
      return this.b.getPackageInfo(paramString, paramInt);
    }
    
    @TargetApi(21)
    public PackageInstaller getPackageInstaller()
    {
      return this.b.getPackageInstaller();
    }
    
    @TargetApi(24)
    public int getPackageUid(String paramString, int paramInt)
    {
      return this.b.getPackageUid(paramString, paramInt);
    }
    
    public String[] getPackagesForUid(int paramInt)
    {
      return this.b.getPackagesForUid(paramInt);
    }
    
    @TargetApi(18)
    public List<PackageInfo> getPackagesHoldingPermissions(String[] paramArrayOfString, int paramInt)
    {
      return this.b.getPackagesHoldingPermissions(paramArrayOfString, paramInt);
    }
    
    public PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt)
    {
      return this.b.getPermissionGroupInfo(paramString, paramInt);
    }
    
    public PermissionInfo getPermissionInfo(String paramString, int paramInt)
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
    {
      return this.b.getProviderInfo(paramComponentName, paramInt);
    }
    
    public ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt)
    {
      return this.b.getReceiverInfo(paramComponentName, paramInt);
    }
    
    public Resources getResourcesForActivity(ComponentName paramComponentName)
    {
      return this.b.getResourcesForActivity(paramComponentName);
    }
    
    public Resources getResourcesForApplication(ApplicationInfo paramApplicationInfo)
    {
      return this.b.getResourcesForApplication(paramApplicationInfo);
    }
    
    public Resources getResourcesForApplication(String paramString)
    {
      return this.b.getResourcesForApplication(paramString);
    }
    
    public ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt)
    {
      return this.b.getServiceInfo(paramComponentName, paramInt);
    }
    
    @TargetApi(26)
    public List<SharedLibraryInfo> getSharedLibraries(int paramInt)
    {
      return this.b.getSharedLibraries(paramInt);
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
    
    @TargetApi(21)
    public Drawable getUserBadgedDrawableForDensity(Drawable paramDrawable, UserHandle paramUserHandle, Rect paramRect, int paramInt)
    {
      return this.b.getUserBadgedDrawableForDensity(paramDrawable, paramUserHandle, paramRect, paramInt);
    }
    
    @TargetApi(21)
    public Drawable getUserBadgedIcon(Drawable paramDrawable, UserHandle paramUserHandle)
    {
      return this.b.getUserBadgedIcon(paramDrawable, paramUserHandle);
    }
    
    @TargetApi(21)
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
    
    public boolean hasSystemFeature(String paramString, int paramInt)
    {
      return false;
    }
    
    @TargetApi(26)
    public boolean isInstantApp()
    {
      return this.b.isInstantApp();
    }
    
    @TargetApi(26)
    public boolean isInstantApp(String paramString)
    {
      return this.b.isInstantApp(paramString);
    }
    
    public boolean isPermissionRevokedByPolicy(@NonNull String paramString1, @NonNull String paramString2)
    {
      return false;
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
    
    @TargetApi(19)
    public List<ResolveInfo> queryIntentContentProviders(Intent paramIntent, int paramInt)
    {
      return this.b.queryIntentContentProviders(paramIntent, paramInt);
    }
    
    public List<ResolveInfo> queryIntentServices(Intent paramIntent, int paramInt)
    {
      return this.b.queryIntentServices(paramIntent, paramInt);
    }
    
    public List<PermissionInfo> queryPermissionsByGroup(String paramString, int paramInt)
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
    
    @TargetApi(26)
    public void setApplicationCategoryHint(@NonNull String paramString, int paramInt)
    {
      this.b.setApplicationCategoryHint(paramString, paramInt);
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
    
    @TargetApi(26)
    public void updateInstantAppCookie(@Nullable byte[] paramArrayOfByte)
    {
      this.b.updateInstantAppCookie(paramArrayOfByte);
    }
    
    public void verifyPendingInstall(int paramInt1, int paramInt2)
    {
      this.b.verifyPendingInstall(paramInt1, paramInt2);
    }
  }
}
