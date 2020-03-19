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
import android.text.TextUtils;
import android.util.Log;
import com.duapps.ad.base.DuAdNetwork;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONStringer;

public class b
{
  private static boolean DEBUG = false;
  private static final String TAG = "b";
  private static boolean bEN = false;
  private static HashSet<Integer> bEO = new HashSet();
  private static Handler handler;
  
  public b() {}
  
  public static void KH()
  {
    Handler localHandler = handler;
    if (localHandler != null) {
      try
      {
        localHandler.removeCallbacksAndMessages(null);
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }
  
  static String a(Collection<Integer> paramCollection, String paramString)
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
    if (!bEN)
    {
      paramContext = paramContext.getApplicationContext();
      int i = 0;
      if (paramBundle != null)
      {
        paramBundle = paramBundle.getIntegerArrayList("ALL_PID");
        if (paramBundle != null)
        {
          bEO.addAll(paramBundle);
          j = 1;
          i = 1;
          break label49;
        }
      }
      int j = 0;
      label49:
      if (!bEO.contains(Integer.valueOf(paramInt)))
      {
        bEO.add(Integer.valueOf(paramInt));
        i = 1;
      }
      if (i != 0)
      {
        paramBundle = a(bEO, "native");
        String str = TAG;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("init config json is : ");
        localStringBuilder.append(paramBundle);
        x(str, localStringBuilder.toString());
        DuAdNetwork.init(t(paramContext, paramString), paramBundle);
        if (j != 0)
        {
          bEN = true;
          return;
        }
        paramContext = new StringBuilder();
        paramContext.append("Only the following placementIds ");
        paramContext.append(bEO);
        paramContext.append(" are initialized. It is strongly recommended to use DuAdExtrasBundleBuilder.addAllPlacementId() to pass all your valid placement IDs (for Native, Banner and Interstitial ads) when making an Ad Request, so that the DuAdNetwork can be properly initialized.");
        paramContext = paramContext.toString();
        Log.e(TAG, paramContext);
      }
    }
  }
  
  public static boolean dL(String paramString)
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
  
  public static void m(Runnable paramRunnable)
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
  
  static Context t(Context paramContext, String paramString)
  {
    int j = 0;
    int i = 0;
    try
    {
      Object localObject = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData;
      if (localObject != null)
      {
        localObject = ((Bundle)localObject).getString("app_license");
        StringBuilder localStringBuilder;
        if (!TextUtils.isEmpty((CharSequence)localObject))
        {
          paramString = TAG;
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("appId from meta is ");
          localStringBuilder.append((String)localObject);
          x(paramString, localStringBuilder.toString());
          i = j;
          break label171;
        }
        boolean bool = TextUtils.isEmpty(paramString);
        if (!bool) {
          try
          {
            localObject = TAG;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("appId from AdMob server is ");
            localStringBuilder.append(paramString);
            x((String)localObject, localStringBuilder.toString());
            paramString = new a(paramContext, paramString);
            paramContext = paramString;
            i = j;
          }
          catch (Exception paramString)
          {
            break label167;
          }
        }
      }
      i = 1;
    }
    catch (Exception paramString)
    {
      i = 1;
      label167:
      paramString.printStackTrace();
    }
    label171:
    if (i != 0)
    {
      Log.e(TAG, "No App Id found! Du Ad will not be able to make money for you properly! Have you configure it in your meta data or AdMob UI?");
      Log.e(TAG, "No App Id found! Du Ad will not be able to make money for you properly! Have you configure it in your meta data or AdMob UI?");
    }
    return paramContext;
  }
  
  public static void x(String paramString1, String paramString2)
  {
    if (DEBUG)
    {
      String str1 = Thread.currentThread().getName();
      String str2 = TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("[");
      localStringBuilder.append(paramString1);
      localStringBuilder.append("]: <");
      localStringBuilder.append(str1);
      localStringBuilder.append("> ");
      localStringBuilder.append(paramString2);
      Log.d(str2, localStringBuilder.toString());
    }
  }
  
  private static class a
    extends ContextWrapper
  {
    private final String appId;
    private final Context byC;
    
    public a(Context paramContext, String paramString)
    {
      super();
      this.byC = paramContext;
      this.appId = paramString;
    }
    
    public Context getApplicationContext()
    {
      return this;
    }
    
    public PackageManager getPackageManager()
    {
      return new b.b(this, this.byC.getPackageManager(), this.appId, null);
    }
  }
  
  private static class b
    extends PackageManager
  {
    private final String appId;
    private final PackageManager bEP;
    private final Context context;
    
    private b(Context paramContext, PackageManager paramPackageManager, String paramString)
    {
      this.bEP = paramPackageManager;
      this.context = paramContext;
      this.appId = paramString;
    }
    
    public void addPackageToPreferred(String paramString)
    {
      this.bEP.addPackageToPreferred(paramString);
    }
    
    public boolean addPermission(PermissionInfo paramPermissionInfo)
    {
      return this.bEP.addPermission(paramPermissionInfo);
    }
    
    public boolean addPermissionAsync(PermissionInfo paramPermissionInfo)
    {
      return this.bEP.addPermissionAsync(paramPermissionInfo);
    }
    
    public void addPreferredActivity(IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName)
    {
      this.bEP.addPreferredActivity(paramIntentFilter, paramInt, paramArrayOfComponentName, paramComponentName);
    }
    
    @TargetApi(26)
    public boolean canRequestPackageInstalls()
    {
      return this.bEP.canRequestPackageInstalls();
    }
    
    public String[] canonicalToCurrentPackageNames(String[] paramArrayOfString)
    {
      return this.bEP.canonicalToCurrentPackageNames(paramArrayOfString);
    }
    
    public int checkPermission(String paramString1, String paramString2)
    {
      return this.bEP.checkPermission(paramString1, paramString2);
    }
    
    public int checkSignatures(int paramInt1, int paramInt2)
    {
      return this.bEP.checkSignatures(paramInt1, paramInt2);
    }
    
    public int checkSignatures(String paramString1, String paramString2)
    {
      return this.bEP.checkSignatures(paramString1, paramString2);
    }
    
    @TargetApi(26)
    public void clearInstantAppCookie()
    {
      this.bEP.clearInstantAppCookie();
    }
    
    public void clearPackagePreferredActivities(String paramString)
    {
      this.bEP.clearPackagePreferredActivities(paramString);
    }
    
    public String[] currentToCanonicalPackageNames(String[] paramArrayOfString)
    {
      return this.bEP.currentToCanonicalPackageNames(paramArrayOfString);
    }
    
    @TargetApi(17)
    public void extendVerificationTimeout(int paramInt1, int paramInt2, long paramLong)
    {
      this.bEP.extendVerificationTimeout(paramInt1, paramInt2, paramLong);
    }
    
    @TargetApi(20)
    public Drawable getActivityBanner(ComponentName paramComponentName)
    {
      return this.bEP.getActivityBanner(paramComponentName);
    }
    
    @TargetApi(20)
    public Drawable getActivityBanner(Intent paramIntent)
    {
      return this.bEP.getActivityBanner(paramIntent);
    }
    
    public Drawable getActivityIcon(ComponentName paramComponentName)
    {
      return this.bEP.getActivityIcon(paramComponentName);
    }
    
    public Drawable getActivityIcon(Intent paramIntent)
    {
      return this.bEP.getActivityIcon(paramIntent);
    }
    
    public ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt)
    {
      return this.bEP.getActivityInfo(paramComponentName, paramInt);
    }
    
    public Drawable getActivityLogo(ComponentName paramComponentName)
    {
      return this.bEP.getActivityLogo(paramComponentName);
    }
    
    public Drawable getActivityLogo(Intent paramIntent)
    {
      return this.bEP.getActivityLogo(paramIntent);
    }
    
    public List<PermissionGroupInfo> getAllPermissionGroups(int paramInt)
    {
      return null;
    }
    
    @TargetApi(20)
    public Drawable getApplicationBanner(ApplicationInfo paramApplicationInfo)
    {
      return this.bEP.getApplicationBanner(paramApplicationInfo);
    }
    
    @TargetApi(20)
    public Drawable getApplicationBanner(String paramString)
    {
      return this.bEP.getApplicationBanner(paramString);
    }
    
    public int getApplicationEnabledSetting(String paramString)
    {
      return this.bEP.getApplicationEnabledSetting(paramString);
    }
    
    public Drawable getApplicationIcon(ApplicationInfo paramApplicationInfo)
    {
      return this.bEP.getApplicationIcon(paramApplicationInfo);
    }
    
    public Drawable getApplicationIcon(String paramString)
    {
      return this.bEP.getApplicationIcon(paramString);
    }
    
    public ApplicationInfo getApplicationInfo(String paramString, int paramInt)
    {
      if (paramString.equals(this.context.getPackageName()))
      {
        paramString = this.bEP.getApplicationInfo(paramString, paramInt);
        paramString.metaData.putString("app_license", this.appId);
        return paramString;
      }
      return this.bEP.getApplicationInfo(paramString, paramInt);
    }
    
    public CharSequence getApplicationLabel(ApplicationInfo paramApplicationInfo)
    {
      return this.bEP.getApplicationLabel(paramApplicationInfo);
    }
    
    public Drawable getApplicationLogo(ApplicationInfo paramApplicationInfo)
    {
      return this.bEP.getApplicationLogo(paramApplicationInfo);
    }
    
    public Drawable getApplicationLogo(String paramString)
    {
      return this.bEP.getApplicationLogo(paramString);
    }
    
    @TargetApi(26)
    public ChangedPackages getChangedPackages(int paramInt)
    {
      return this.bEP.getChangedPackages(paramInt);
    }
    
    public int getComponentEnabledSetting(ComponentName paramComponentName)
    {
      return this.bEP.getComponentEnabledSetting(paramComponentName);
    }
    
    public Drawable getDefaultActivityIcon()
    {
      return this.bEP.getDefaultActivityIcon();
    }
    
    public Drawable getDrawable(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
    {
      return this.bEP.getDrawable(paramString, paramInt, paramApplicationInfo);
    }
    
    public List<ApplicationInfo> getInstalledApplications(int paramInt)
    {
      return this.bEP.getInstalledApplications(paramInt);
    }
    
    public List<PackageInfo> getInstalledPackages(int paramInt)
    {
      return this.bEP.getInstalledPackages(paramInt);
    }
    
    public String getInstallerPackageName(String paramString)
    {
      return this.bEP.getInstallerPackageName(paramString);
    }
    
    @TargetApi(26)
    public byte[] getInstantAppCookie()
    {
      return this.bEP.getInstantAppCookie();
    }
    
    @TargetApi(26)
    public int getInstantAppCookieMaxBytes()
    {
      return this.bEP.getInstantAppCookieMaxBytes();
    }
    
    public InstrumentationInfo getInstrumentationInfo(ComponentName paramComponentName, int paramInt)
    {
      return this.bEP.getInstrumentationInfo(paramComponentName, paramInt);
    }
    
    public Intent getLaunchIntentForPackage(String paramString)
    {
      return this.bEP.getLaunchIntentForPackage(paramString);
    }
    
    @TargetApi(21)
    public Intent getLeanbackLaunchIntentForPackage(String paramString)
    {
      return this.bEP.getLeanbackLaunchIntentForPackage(paramString);
    }
    
    public String getNameForUid(int paramInt)
    {
      return this.bEP.getNameForUid(paramInt);
    }
    
    public int[] getPackageGids(String paramString)
    {
      return this.bEP.getPackageGids(paramString);
    }
    
    @TargetApi(24)
    public int[] getPackageGids(String paramString, int paramInt)
    {
      return this.bEP.getPackageGids(paramString, paramInt);
    }
    
    @TargetApi(26)
    public PackageInfo getPackageInfo(VersionedPackage paramVersionedPackage, int paramInt)
    {
      try
      {
        paramVersionedPackage = this.bEP.getPackageInfo(paramVersionedPackage, paramInt);
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
      return this.bEP.getPackageInfo(paramString, paramInt);
    }
    
    @TargetApi(21)
    public PackageInstaller getPackageInstaller()
    {
      return this.bEP.getPackageInstaller();
    }
    
    @TargetApi(24)
    public int getPackageUid(String paramString, int paramInt)
    {
      return this.bEP.getPackageUid(paramString, paramInt);
    }
    
    public String[] getPackagesForUid(int paramInt)
    {
      return this.bEP.getPackagesForUid(paramInt);
    }
    
    @TargetApi(18)
    public List<PackageInfo> getPackagesHoldingPermissions(String[] paramArrayOfString, int paramInt)
    {
      return this.bEP.getPackagesHoldingPermissions(paramArrayOfString, paramInt);
    }
    
    public PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt)
    {
      return this.bEP.getPermissionGroupInfo(paramString, paramInt);
    }
    
    public PermissionInfo getPermissionInfo(String paramString, int paramInt)
    {
      return this.bEP.getPermissionInfo(paramString, paramInt);
    }
    
    public int getPreferredActivities(List<IntentFilter> paramList, List<ComponentName> paramList1, String paramString)
    {
      return this.bEP.getPreferredActivities(paramList, paramList1, paramString);
    }
    
    public List<PackageInfo> getPreferredPackages(int paramInt)
    {
      return this.bEP.getPreferredPackages(paramInt);
    }
    
    public ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt)
    {
      return this.bEP.getProviderInfo(paramComponentName, paramInt);
    }
    
    public ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt)
    {
      return this.bEP.getReceiverInfo(paramComponentName, paramInt);
    }
    
    public Resources getResourcesForActivity(ComponentName paramComponentName)
    {
      return this.bEP.getResourcesForActivity(paramComponentName);
    }
    
    public Resources getResourcesForApplication(ApplicationInfo paramApplicationInfo)
    {
      return this.bEP.getResourcesForApplication(paramApplicationInfo);
    }
    
    public Resources getResourcesForApplication(String paramString)
    {
      return this.bEP.getResourcesForApplication(paramString);
    }
    
    public ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt)
    {
      return this.bEP.getServiceInfo(paramComponentName, paramInt);
    }
    
    @TargetApi(26)
    public List<SharedLibraryInfo> getSharedLibraries(int paramInt)
    {
      return this.bEP.getSharedLibraries(paramInt);
    }
    
    public FeatureInfo[] getSystemAvailableFeatures()
    {
      return this.bEP.getSystemAvailableFeatures();
    }
    
    public String[] getSystemSharedLibraryNames()
    {
      return this.bEP.getSystemSharedLibraryNames();
    }
    
    public CharSequence getText(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
    {
      return this.bEP.getText(paramString, paramInt, paramApplicationInfo);
    }
    
    @TargetApi(21)
    public Drawable getUserBadgedDrawableForDensity(Drawable paramDrawable, UserHandle paramUserHandle, Rect paramRect, int paramInt)
    {
      return this.bEP.getUserBadgedDrawableForDensity(paramDrawable, paramUserHandle, paramRect, paramInt);
    }
    
    @TargetApi(21)
    public Drawable getUserBadgedIcon(Drawable paramDrawable, UserHandle paramUserHandle)
    {
      return this.bEP.getUserBadgedIcon(paramDrawable, paramUserHandle);
    }
    
    @TargetApi(21)
    public CharSequence getUserBadgedLabel(CharSequence paramCharSequence, UserHandle paramUserHandle)
    {
      return this.bEP.getUserBadgedLabel(paramCharSequence, paramUserHandle);
    }
    
    public XmlResourceParser getXml(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
    {
      return this.bEP.getXml(paramString, paramInt, paramApplicationInfo);
    }
    
    public boolean hasSystemFeature(String paramString)
    {
      return this.bEP.hasSystemFeature(paramString);
    }
    
    public boolean hasSystemFeature(String paramString, int paramInt)
    {
      return false;
    }
    
    @TargetApi(26)
    public boolean isInstantApp()
    {
      return this.bEP.isInstantApp();
    }
    
    @TargetApi(26)
    public boolean isInstantApp(String paramString)
    {
      return this.bEP.isInstantApp(paramString);
    }
    
    public boolean isPermissionRevokedByPolicy(String paramString1, String paramString2)
    {
      return false;
    }
    
    public boolean isSafeMode()
    {
      return this.bEP.isSafeMode();
    }
    
    public List<ResolveInfo> queryBroadcastReceivers(Intent paramIntent, int paramInt)
    {
      return this.bEP.queryBroadcastReceivers(paramIntent, paramInt);
    }
    
    public List<ProviderInfo> queryContentProviders(String paramString, int paramInt1, int paramInt2)
    {
      return this.bEP.queryContentProviders(paramString, paramInt1, paramInt2);
    }
    
    public List<InstrumentationInfo> queryInstrumentation(String paramString, int paramInt)
    {
      return this.bEP.queryInstrumentation(paramString, paramInt);
    }
    
    public List<ResolveInfo> queryIntentActivities(Intent paramIntent, int paramInt)
    {
      return this.bEP.queryIntentActivities(paramIntent, paramInt);
    }
    
    public List<ResolveInfo> queryIntentActivityOptions(ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt)
    {
      return this.bEP.queryIntentActivityOptions(paramComponentName, paramArrayOfIntent, paramIntent, paramInt);
    }
    
    @TargetApi(19)
    public List<ResolveInfo> queryIntentContentProviders(Intent paramIntent, int paramInt)
    {
      return this.bEP.queryIntentContentProviders(paramIntent, paramInt);
    }
    
    public List<ResolveInfo> queryIntentServices(Intent paramIntent, int paramInt)
    {
      return this.bEP.queryIntentServices(paramIntent, paramInt);
    }
    
    public List<PermissionInfo> queryPermissionsByGroup(String paramString, int paramInt)
    {
      return this.bEP.queryPermissionsByGroup(paramString, paramInt);
    }
    
    public void removePackageFromPreferred(String paramString)
    {
      this.bEP.removePackageFromPreferred(paramString);
    }
    
    public void removePermission(String paramString)
    {
      this.bEP.removePermission(paramString);
    }
    
    public ResolveInfo resolveActivity(Intent paramIntent, int paramInt)
    {
      return this.bEP.resolveActivity(paramIntent, paramInt);
    }
    
    public ProviderInfo resolveContentProvider(String paramString, int paramInt)
    {
      return this.bEP.resolveContentProvider(paramString, paramInt);
    }
    
    public ResolveInfo resolveService(Intent paramIntent, int paramInt)
    {
      return this.bEP.resolveService(paramIntent, paramInt);
    }
    
    @TargetApi(26)
    public void setApplicationCategoryHint(String paramString, int paramInt)
    {
      this.bEP.setApplicationCategoryHint(paramString, paramInt);
    }
    
    public void setApplicationEnabledSetting(String paramString, int paramInt1, int paramInt2)
    {
      this.bEP.setApplicationEnabledSetting(paramString, paramInt1, paramInt2);
    }
    
    public void setComponentEnabledSetting(ComponentName paramComponentName, int paramInt1, int paramInt2)
    {
      this.bEP.setComponentEnabledSetting(paramComponentName, paramInt1, paramInt2);
    }
    
    public void setInstallerPackageName(String paramString1, String paramString2)
    {
      this.bEP.setInstallerPackageName(paramString1, paramString2);
    }
    
    @TargetApi(26)
    public void updateInstantAppCookie(byte[] paramArrayOfByte)
    {
      this.bEP.updateInstantAppCookie(paramArrayOfByte);
    }
    
    public void verifyPendingInstall(int paramInt1, int paramInt2)
    {
      this.bEP.verifyPendingInstall(paramInt1, paramInt2);
    }
  }
}
