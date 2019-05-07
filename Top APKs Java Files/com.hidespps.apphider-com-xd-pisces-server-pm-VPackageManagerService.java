package com.xd.pisces.server.pm;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Log;
import com.xd.pisces.client.core.Pisces;
import com.xd.pisces.client.fixer.ComponentFixer;
import com.xd.pisces.client.stub.VASettings;
import com.xd.pisces.helper.compat.ObjectsCompat;
import com.xd.pisces.helper.compat.PermissionCompat;
import com.xd.pisces.os.VUserHandle;
import com.xd.pisces.remote.VParceledListSlice;
import com.xd.pisces.server.IPackageInstaller;
import com.xd.pisces.server.IPackageManager.Stub;
import com.xd.pisces.server.pm.installer.VPackageInstallerService;
import com.xd.pisces.server.pm.parser.PackageParserEx;
import com.xd.pisces.server.pm.parser.VPackage;
import com.xd.pisces.server.pm.parser.VPackage.ActivityComponent;
import com.xd.pisces.server.pm.parser.VPackage.ActivityIntentInfo;
import com.xd.pisces.server.pm.parser.VPackage.PermissionComponent;
import com.xd.pisces.server.pm.parser.VPackage.PermissionGroupComponent;
import com.xd.pisces.server.pm.parser.VPackage.ProviderComponent;
import com.xd.pisces.server.pm.parser.VPackage.ServiceComponent;
import com.xd.pisces.server.pm.parser.VPackage.ServiceIntentInfo;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class VPackageManagerService
  extends IPackageManager.Stub
{
  static final String TAG = "PackageManager";
  private static final AtomicReference<VPackageManagerService> gService = new AtomicReference();
  private static final Comparator<ProviderInfo> sProviderInitOrderSorter = new Comparator()
  {
    public int compare(ProviderInfo paramAnonymousProviderInfo1, ProviderInfo paramAnonymousProviderInfo2)
    {
      int i = paramAnonymousProviderInfo1.initOrder;
      int j = paramAnonymousProviderInfo2.initOrder;
      if (i > j) {
        return -1;
      }
      if (i < j) {
        return 1;
      }
      return 0;
    }
  };
  static final Comparator<ResolveInfo> sResolvePrioritySorter = new Comparator()
  {
    public int compare(ResolveInfo paramAnonymousResolveInfo1, ResolveInfo paramAnonymousResolveInfo2)
    {
      int n = paramAnonymousResolveInfo1.priority;
      int i1 = paramAnonymousResolveInfo2.priority;
      int j = 1;
      int k = 1;
      int i = 1;
      int m = 1;
      if (n != i1)
      {
        i = m;
        if (n > i1) {
          i = -1;
        }
        return i;
      }
      m = paramAnonymousResolveInfo1.preferredOrder;
      n = paramAnonymousResolveInfo2.preferredOrder;
      if (m != n)
      {
        i = j;
        if (m > n) {
          i = -1;
        }
        return i;
      }
      if (paramAnonymousResolveInfo1.isDefault != paramAnonymousResolveInfo2.isDefault)
      {
        i = k;
        if (paramAnonymousResolveInfo1.isDefault) {
          i = -1;
        }
        return i;
      }
      j = paramAnonymousResolveInfo1.match;
      k = paramAnonymousResolveInfo2.match;
      if (j != k)
      {
        if (j > k) {
          i = -1;
        }
        return i;
      }
      return 0;
    }
  };
  private final ActivityIntentResolver mActivities;
  private final HashMap<String, String[]> mDangerousPermissions;
  private final Map<String, VPackage> mPackages;
  private final HashMap<String, VPackage.PermissionGroupComponent> mPermissionGroups;
  private final HashMap<String, VPackage.PermissionComponent> mPermissions;
  private final ProviderIntentResolver mProviders;
  private final HashMap<String, VPackage.ProviderComponent> mProvidersByAuthority;
  private final HashMap<ComponentName, VPackage.ProviderComponent> mProvidersByComponent;
  private final ActivityIntentResolver mReceivers;
  private final ResolveInfo mResolveInfo;
  private final ServiceIntentResolver mServices;
  
  public VPackageManagerService()
  {
    Object localObject = null;
    this.mActivities = new ActivityIntentResolver(null);
    this.mDangerousPermissions = new HashMap();
    this.mServices = new ServiceIntentResolver(null);
    this.mReceivers = new ActivityIntentResolver(null);
    if (Build.VERSION.SDK_INT >= 19) {
      localObject = new ProviderIntentResolver();
    }
    this.mProviders = ((ProviderIntentResolver)localObject);
    this.mProvidersByComponent = new HashMap();
    this.mPermissions = new HashMap();
    this.mPermissionGroups = new HashMap();
    this.mProvidersByAuthority = new HashMap();
    this.mPackages = PackageCacheManager.PACKAGE_CACHE;
    localObject = new Intent();
    ((Intent)localObject).setClassName(Pisces.get().getHostPkg(), VASettings.RESOLVER_ACTIVITY);
    this.mResolveInfo = Pisces.get().getUnHookPackageManager().resolveActivity((Intent)localObject, 0);
  }
  
  private void checkUserId(int paramInt)
  {
    if (VUserManagerService.get().exists(paramInt)) {
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Invalid userId ");
    localStringBuilder.append(paramInt);
    throw new SecurityException(localStringBuilder.toString());
  }
  
  private ResolveInfo chooseBestActivity(Intent paramIntent, String paramString, int paramInt, List<ResolveInfo> paramList)
  {
    if (paramList != null)
    {
      int i = paramList.size();
      if (i == 1) {
        return (ResolveInfo)paramList.get(0);
      }
      if (i > 1)
      {
        ResolveInfo localResolveInfo1 = (ResolveInfo)paramList.get(0);
        ResolveInfo localResolveInfo2 = (ResolveInfo)paramList.get(1);
        if ((localResolveInfo1.priority == localResolveInfo2.priority) && (localResolveInfo1.preferredOrder == localResolveInfo2.preferredOrder) && (localResolveInfo1.isDefault == localResolveInfo2.isDefault))
        {
          paramIntent = findPreferredActivity(paramIntent, paramString, paramInt, paramList, localResolveInfo1.priority);
          if (paramIntent != null) {
            return paramIntent;
          }
          return (ResolveInfo)paramList.get(0);
        }
        return (ResolveInfo)paramList.get(0);
      }
    }
    return null;
  }
  
  private ResolveInfo findPreferredActivity(Intent paramIntent, String paramString, int paramInt1, List<ResolveInfo> paramList, int paramInt2)
  {
    return null;
  }
  
  private PackageInfo generatePackageInfo(VPackage paramVPackage, PackageSetting paramPackageSetting, int paramInt1, int paramInt2)
  {
    paramVPackage = PackageParserEx.generatePackageInfo(paramVPackage, updateFlagsNought(paramInt1), paramPackageSetting.firstInstallTime, paramPackageSetting.lastUpdateTime, paramPackageSetting.readUserState(paramInt2), paramInt2);
    if (paramVPackage != null) {
      return paramVPackage;
    }
    return null;
  }
  
  public static VPackageManagerService get()
  {
    return (VPackageManagerService)gService.get();
  }
  
  public static void systemReady()
  {
    VPackageManagerService localVPackageManagerService = new VPackageManagerService();
    Context localContext = Pisces.get().getContext();
    Map localMap = localVPackageManagerService.mPackages;
    new VUserManagerService(localContext, localVPackageManagerService, new char[0], localMap);
    gService.set(localVPackageManagerService);
  }
  
  private int updateFlagsNought(int paramInt)
  {
    if (Build.VERSION.SDK_INT < 24) {
      return paramInt;
    }
    if ((paramInt & 0xC0000) != 0) {
      return paramInt;
    }
    return paramInt | 0xC0000;
  }
  
  public boolean activitySupportsIntent(ComponentName paramComponentName, Intent paramIntent, String paramString)
  {
    for (;;)
    {
      synchronized (this.mPackages)
      {
        paramComponentName = (VPackage.ActivityComponent)this.mActivities.mActivities.get(paramComponentName);
        if (paramComponentName == null)
        {
          return false;
          if (i < paramComponentName.intents.size())
          {
            if (((VPackage.ActivityIntentInfo)paramComponentName.intents.get(i)).filter.match(paramIntent.getAction(), paramString, paramIntent.getScheme(), paramIntent.getData(), paramIntent.getCategories(), "PackageManager") < 0) {
              break label107;
            }
            return true;
          }
          return false;
        }
      }
      int i = 0;
      continue;
      label107:
      i += 1;
    }
  }
  
  void analyzePackageLocked(VPackage paramVPackage)
  {
    int j = paramVPackage.activities.size();
    int k = 0;
    int i = 0;
    while (i < j)
    {
      ??? = (VPackage.ActivityComponent)paramVPackage.activities.get(i);
      if (((VPackage.ActivityComponent)???).info.processName == null) {
        ((VPackage.ActivityComponent)???).info.processName = ((VPackage.ActivityComponent)???).info.packageName;
      }
      this.mActivities.addActivity((VPackage.ActivityComponent)???, "activity");
      i += 1;
    }
    j = paramVPackage.services.size();
    i = 0;
    while (i < j)
    {
      ??? = (VPackage.ServiceComponent)paramVPackage.services.get(i);
      if (((VPackage.ServiceComponent)???).info.processName == null) {
        ((VPackage.ServiceComponent)???).info.processName = ((VPackage.ServiceComponent)???).info.packageName;
      }
      this.mServices.addService((VPackage.ServiceComponent)???);
      i += 1;
    }
    j = paramVPackage.receivers.size();
    i = 0;
    while (i < j)
    {
      ??? = (VPackage.ActivityComponent)paramVPackage.receivers.get(i);
      if (((VPackage.ActivityComponent)???).info.processName == null) {
        ((VPackage.ActivityComponent)???).info.processName = ((VPackage.ActivityComponent)???).info.packageName;
      }
      this.mReceivers.addActivity((VPackage.ActivityComponent)???, "receiver");
      i += 1;
    }
    int m = paramVPackage.providers.size();
    i = 0;
    while (i < m)
    {
      ??? = (VPackage.ProviderComponent)paramVPackage.providers.get(i);
      if (((VPackage.ProviderComponent)???).info.processName == null) {
        ((VPackage.ProviderComponent)???).info.processName = ((VPackage.ProviderComponent)???).info.packageName;
      }
      if (Build.VERSION.SDK_INT >= 19) {
        this.mProviders.addProvider((VPackage.ProviderComponent)???);
      }
      String[] arrayOfString = ((VPackage.ProviderComponent)???).info.authority.split(";");
      int n = arrayOfString.length;
      j = 0;
      while (j < n)
      {
        String str = arrayOfString[j];
        if (!this.mProvidersByAuthority.containsKey(str)) {
          this.mProvidersByAuthority.put(str, ???);
        }
        j += 1;
      }
      this.mProvidersByComponent.put(((VPackage.ProviderComponent)???).getComponentName(), ???);
      i += 1;
    }
    j = paramVPackage.permissions.size();
    i = 0;
    while (i < j)
    {
      ??? = (VPackage.PermissionComponent)paramVPackage.permissions.get(i);
      this.mPermissions.put(((VPackage.PermissionComponent)???).className, ???);
      i += 1;
    }
    j = paramVPackage.permissionGroups.size();
    i = k;
    while (i < j)
    {
      ??? = (VPackage.PermissionGroupComponent)paramVPackage.permissionGroups.get(i);
      this.mPermissionGroups.put(((VPackage.PermissionGroupComponent)???).className, ???);
      i += 1;
    }
    synchronized (this.mDangerousPermissions)
    {
      this.mDangerousPermissions.put(paramVPackage.packageName, PermissionCompat.findDangerousPermissions(paramVPackage.requestedPermissions));
      return;
    }
  }
  
  public int checkPermission(String paramString1, String paramString2, int paramInt)
  {
    if ((!"android.permission.INTERACT_ACROSS_USERS".equals(paramString1)) && (!"android.permission.INTERACT_ACROSS_USERS_FULL".equals(paramString1))) {
      return Pisces.get().getPackageManager().checkPermission(paramString1, Pisces.get().getHostPkg());
    }
    return -1;
  }
  
  void cleanUpUser(int paramInt)
  {
    Iterator localIterator = this.mPackages.values().iterator();
    while (localIterator.hasNext()) {
      ((PackageSetting)((VPackage)localIterator.next()).mExtras).removeUser(paramInt);
    }
  }
  
  void createNewUser(int paramInt, File paramFile)
  {
    paramFile = this.mPackages.values().iterator();
    while (paramFile.hasNext()) {
      ((PackageSetting)((VPackage)paramFile.next()).mExtras).modifyUserState(paramInt);
    }
  }
  
  void deletePackageLocked(String paramString)
  {
    paramString = (VPackage)this.mPackages.get(paramString);
    if (paramString == null) {
      return;
    }
    int j = paramString.activities.size();
    int k = 0;
    int i = 0;
    Object localObject;
    while (i < j)
    {
      localObject = (VPackage.ActivityComponent)paramString.activities.get(i);
      this.mActivities.removeActivity((VPackage.ActivityComponent)localObject, "activity");
      i += 1;
    }
    j = paramString.services.size();
    i = 0;
    while (i < j)
    {
      localObject = (VPackage.ServiceComponent)paramString.services.get(i);
      this.mServices.removeService((VPackage.ServiceComponent)localObject);
      i += 1;
    }
    j = paramString.receivers.size();
    i = 0;
    while (i < j)
    {
      localObject = (VPackage.ActivityComponent)paramString.receivers.get(i);
      this.mReceivers.removeActivity((VPackage.ActivityComponent)localObject, "receiver");
      i += 1;
    }
    int m = paramString.providers.size();
    i = 0;
    while (i < m)
    {
      localObject = (VPackage.ProviderComponent)paramString.providers.get(i);
      if (Build.VERSION.SDK_INT >= 19) {
        this.mProviders.removeProvider((VPackage.ProviderComponent)localObject);
      }
      String[] arrayOfString = ((VPackage.ProviderComponent)localObject).info.authority.split(";");
      int n = arrayOfString.length;
      j = 0;
      while (j < n)
      {
        String str = arrayOfString[j];
        this.mProvidersByAuthority.remove(str);
        j += 1;
      }
      this.mProvidersByComponent.remove(((VPackage.ProviderComponent)localObject).getComponentName());
      i += 1;
    }
    j = paramString.permissions.size();
    i = 0;
    while (i < j)
    {
      localObject = (VPackage.PermissionComponent)paramString.permissions.get(i);
      this.mPermissions.remove(((VPackage.PermissionComponent)localObject).className);
      i += 1;
    }
    j = paramString.permissionGroups.size();
    i = k;
    while (i < j)
    {
      localObject = (VPackage.PermissionGroupComponent)paramString.permissionGroups.get(i);
      this.mPermissionGroups.remove(((VPackage.PermissionGroupComponent)localObject).className);
      i += 1;
    }
  }
  
  public ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    checkUserId(paramInt2);
    paramInt1 = updateFlagsNought(paramInt1);
    synchronized (this.mPackages)
    {
      Object localObject = (VPackage)this.mPackages.get(paramComponentName.getPackageName());
      if (localObject != null)
      {
        localObject = (PackageSetting)((VPackage)localObject).mExtras;
        paramComponentName = (VPackage.ActivityComponent)this.mActivities.mActivities.get(paramComponentName);
        if (paramComponentName != null)
        {
          paramComponentName = PackageParserEx.generateActivityInfo(paramComponentName, paramInt1, ((PackageSetting)localObject).readUserState(paramInt2), paramInt2);
          ComponentFixer.fixComponentInfo((PackageSetting)localObject, paramComponentName, paramInt2);
          return paramComponentName;
        }
      }
      return null;
    }
  }
  
  public List<PermissionGroupInfo> getAllPermissionGroups(int paramInt)
  {
    synchronized (this.mPackages)
    {
      ArrayList localArrayList = new ArrayList(this.mPermissionGroups.size());
      Iterator localIterator = this.mPermissionGroups.values().iterator();
      while (localIterator.hasNext()) {
        localArrayList.add(new PermissionGroupInfo(((VPackage.PermissionGroupComponent)localIterator.next()).info));
      }
      return localArrayList;
    }
  }
  
  public ApplicationInfo getApplicationInfo(String paramString, int paramInt1, int paramInt2)
  {
    checkUserId(paramInt2);
    paramInt1 = updateFlagsNought(paramInt1);
    synchronized (this.mPackages)
    {
      paramString = (VPackage)this.mPackages.get(paramString);
      if (paramString != null)
      {
        paramString = PackageParserEx.generateApplicationInfo(paramString, paramInt1, ((PackageSetting)paramString.mExtras).readUserState(paramInt2), paramInt2);
        return paramString;
      }
      return null;
    }
  }
  
  public String[] getDangerousPermissions(String paramString)
  {
    synchronized (this.mDangerousPermissions)
    {
      paramString = (String[])this.mDangerousPermissions.get(paramString);
      return paramString;
    }
  }
  
  public VParceledListSlice<ApplicationInfo> getInstalledApplications(int paramInt1, int paramInt2)
  {
    checkUserId(paramInt2);
    paramInt1 = updateFlagsNought(paramInt1);
    ArrayList localArrayList = new ArrayList(this.mPackages.size());
    synchronized (this.mPackages)
    {
      Iterator localIterator = this.mPackages.values().iterator();
      while (localIterator.hasNext())
      {
        VPackage localVPackage = (VPackage)localIterator.next();
        localArrayList.add(PackageParserEx.generateApplicationInfo(localVPackage, paramInt1, ((PackageSetting)localVPackage.mExtras).readUserState(paramInt2), paramInt2));
      }
      return new VParceledListSlice(localArrayList);
    }
  }
  
  public VParceledListSlice<PackageInfo> getInstalledPackages(int paramInt1, int paramInt2)
  {
    checkUserId(paramInt2);
    ArrayList localArrayList = new ArrayList(this.mPackages.size());
    synchronized (this.mPackages)
    {
      Iterator localIterator = this.mPackages.values().iterator();
      while (localIterator.hasNext())
      {
        Object localObject2 = (VPackage)localIterator.next();
        localObject2 = generatePackageInfo((VPackage)localObject2, (PackageSetting)((VPackage)localObject2).mExtras, paramInt1, paramInt2);
        if (localObject2 != null) {
          localArrayList.add(localObject2);
        }
      }
      return new VParceledListSlice(localArrayList);
    }
  }
  
  public String getNameForUid(int paramInt)
  {
    paramInt = VUserHandle.getAppId(paramInt);
    synchronized (this.mPackages)
    {
      Object localObject1 = this.mPackages.values().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        PackageSetting localPackageSetting = (PackageSetting)((VPackage)((Iterator)localObject1).next()).mExtras;
        if (localPackageSetting.appId == paramInt)
        {
          localObject1 = localPackageSetting.packageName;
          return localObject1;
        }
      }
      return null;
    }
  }
  
  public PackageInfo getPackageInfo(String paramString, int paramInt1, int paramInt2)
  {
    checkUserId(paramInt2);
    synchronized (this.mPackages)
    {
      paramString = (VPackage)this.mPackages.get(paramString);
      if (paramString != null)
      {
        paramString = generatePackageInfo(paramString, (PackageSetting)paramString.mExtras, paramInt1, paramInt2);
        return paramString;
      }
      return null;
    }
  }
  
  public IPackageInstaller getPackageInstaller()
  {
    return VPackageInstallerService.get();
  }
  
  public int getPackageUid(String paramString, int paramInt)
  {
    checkUserId(paramInt);
    synchronized (this.mPackages)
    {
      paramString = (VPackage)this.mPackages.get(paramString);
      if (paramString != null)
      {
        paramInt = VUserHandle.getUid(paramInt, ((PackageSetting)paramString.mExtras).appId);
        return paramInt;
      }
      return -1;
    }
  }
  
  public String[] getPackagesForUid(int paramInt)
  {
    int i = VUserHandle.getUserId(paramInt);
    checkUserId(i);
    try
    {
      Object localObject1 = new ArrayList(2);
      Iterator localIterator = this.mPackages.values().iterator();
      while (localIterator.hasNext())
      {
        VPackage localVPackage = (VPackage)localIterator.next();
        if (VUserHandle.getUid(i, ((PackageSetting)localVPackage.mExtras).appId) == paramInt) {
          ((List)localObject1).add(localVPackage.packageName);
        }
      }
      localObject1 = (String[])((List)localObject1).toArray(new String[((List)localObject1).size()]);
      return localObject1;
    }
    finally {}
  }
  
  public PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt)
  {
    synchronized (this.mPackages)
    {
      paramString = (VPackage.PermissionGroupComponent)this.mPermissionGroups.get(paramString);
      if (paramString != null)
      {
        paramString = new PermissionGroupInfo(paramString.info);
        return paramString;
      }
      return null;
    }
  }
  
  public PermissionInfo getPermissionInfo(String paramString, int paramInt)
  {
    synchronized (this.mPackages)
    {
      paramString = (VPackage.PermissionComponent)this.mPermissions.get(paramString);
      if (paramString != null)
      {
        paramString = new PermissionInfo(paramString.info);
        return paramString;
      }
      return null;
    }
  }
  
  public ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    checkUserId(paramInt2);
    paramInt1 = updateFlagsNought(paramInt1);
    synchronized (this.mPackages)
    {
      Object localObject = (VPackage)this.mPackages.get(paramComponentName.getPackageName());
      if (localObject != null)
      {
        localObject = (PackageSetting)((VPackage)localObject).mExtras;
        paramComponentName = (VPackage.ProviderComponent)this.mProvidersByComponent.get(paramComponentName);
        if (paramComponentName != null)
        {
          paramComponentName = PackageParserEx.generateProviderInfo(paramComponentName, paramInt1, ((PackageSetting)localObject).readUserState(paramInt2), paramInt2);
          ComponentFixer.fixComponentInfo((PackageSetting)localObject, paramComponentName, paramInt2);
          return paramComponentName;
        }
      }
      return null;
    }
  }
  
  public ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    checkUserId(paramInt2);
    paramInt1 = updateFlagsNought(paramInt1);
    synchronized (this.mPackages)
    {
      Object localObject = (VPackage)this.mPackages.get(paramComponentName.getPackageName());
      if (localObject != null)
      {
        localObject = (PackageSetting)((VPackage)localObject).mExtras;
        paramComponentName = (VPackage.ActivityComponent)this.mReceivers.mActivities.get(paramComponentName);
        if (paramComponentName != null)
        {
          paramComponentName = PackageParserEx.generateActivityInfo(paramComponentName, paramInt1, ((PackageSetting)localObject).readUserState(paramInt2), paramInt2);
          ComponentFixer.fixComponentInfo((PackageSetting)localObject, paramComponentName, paramInt2);
          return paramComponentName;
        }
      }
      return null;
    }
  }
  
  public ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    checkUserId(paramInt2);
    paramInt1 = updateFlagsNought(paramInt1);
    synchronized (this.mPackages)
    {
      Object localObject = (VPackage)this.mPackages.get(paramComponentName.getPackageName());
      if (localObject != null)
      {
        localObject = (PackageSetting)((VPackage)localObject).mExtras;
        paramComponentName = (VPackage.ServiceComponent)this.mServices.mServices.get(paramComponentName);
        if (paramComponentName != null)
        {
          paramComponentName = PackageParserEx.generateServiceInfo(paramComponentName, paramInt1, ((PackageSetting)localObject).readUserState(paramInt2), paramInt2);
          ComponentFixer.fixComponentInfo((PackageSetting)localObject, paramComponentName, paramInt2);
          return paramComponentName;
        }
      }
      return null;
    }
  }
  
  public List<String> getSharedLibraries(String paramString)
  {
    synchronized (this.mPackages)
    {
      paramString = (VPackage)this.mPackages.get(paramString);
      if (paramString != null)
      {
        paramString = paramString.usesLibraries;
        return paramString;
      }
      return null;
    }
  }
  
  public boolean isVirtualAuthority(String paramString)
  {
    synchronized (this.mProvidersByAuthority)
    {
      boolean bool = this.mProvidersByAuthority.containsKey(paramString);
      return bool;
    }
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
  {
    try
    {
      boolean bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      return bool;
    }
    catch (Throwable paramParcel1)
    {
      paramParcel1.printStackTrace();
      throw paramParcel1;
    }
  }
  
  public VParceledListSlice<ProviderInfo> queryContentProviders(String paramString, int paramInt1, int paramInt2)
  {
    int i = VUserHandle.getUserId(paramInt1);
    checkUserId(i);
    paramInt2 = updateFlagsNought(paramInt2);
    ArrayList localArrayList = new ArrayList(3);
    synchronized (this.mPackages)
    {
      Iterator localIterator = this.mProvidersByComponent.values().iterator();
      while (localIterator.hasNext())
      {
        VPackage.ProviderComponent localProviderComponent = (VPackage.ProviderComponent)localIterator.next();
        PackageSetting localPackageSetting = (PackageSetting)localProviderComponent.owner.mExtras;
        if ((paramString == null) || ((localPackageSetting.appId == VUserHandle.getAppId(paramInt1)) && (localProviderComponent.info.processName.equals(paramString)))) {
          localArrayList.add(PackageParserEx.generateProviderInfo(localProviderComponent, paramInt2, localPackageSetting.readUserState(i), i));
        }
      }
      if (!localArrayList.isEmpty()) {
        Collections.sort(localArrayList, sProviderInitOrderSorter);
      }
      return new VParceledListSlice(localArrayList);
    }
  }
  
  public List<ResolveInfo> queryIntentActivities(Intent arg1, String paramString, int paramInt1, int paramInt2)
  {
    checkUserId(paramInt2);
    paramInt1 = updateFlagsNought(paramInt1);
    ComponentName localComponentName = ???.getComponent();
    Object localObject1 = ???;
    Object localObject2 = localComponentName;
    if (localComponentName == null)
    {
      localObject1 = ???;
      localObject2 = localComponentName;
      if (Build.VERSION.SDK_INT >= 15)
      {
        localObject1 = ???;
        localObject2 = localComponentName;
        if (???.getSelector() != null)
        {
          localObject1 = ???.getSelector();
          localObject2 = ((Intent)localObject1).getComponent();
        }
      }
    }
    if (localObject2 != null)
    {
      ??? = new ArrayList(1);
      paramString = getActivityInfo((ComponentName)localObject2, paramInt1, paramInt2);
      if (paramString != null)
      {
        localObject1 = new ResolveInfo();
        ((ResolveInfo)localObject1).activityInfo = paramString;
        ???.add(localObject1);
      }
      return ???;
    }
    synchronized (this.mPackages)
    {
      localObject2 = ((Intent)localObject1).getPackage();
      if (localObject2 == null)
      {
        paramString = this.mActivities.queryIntent((Intent)localObject1, paramString, paramInt1, paramInt2);
        return paramString;
      }
      localObject2 = (VPackage)this.mPackages.get(localObject2);
      if (localObject2 != null)
      {
        paramString = this.mActivities.queryIntentForPackage((Intent)localObject1, paramString, paramInt1, ((VPackage)localObject2).activities, paramInt2);
        return paramString;
      }
      paramString = Collections.emptyList();
      return paramString;
    }
  }
  
  @TargetApi(19)
  public List<ResolveInfo> queryIntentContentProviders(Intent arg1, String paramString, int paramInt1, int paramInt2)
  {
    checkUserId(paramInt2);
    paramInt1 = updateFlagsNought(paramInt1);
    ComponentName localComponentName = ???.getComponent();
    Object localObject1 = ???;
    Object localObject2 = localComponentName;
    if (localComponentName == null)
    {
      localObject1 = ???;
      localObject2 = localComponentName;
      if (Build.VERSION.SDK_INT >= 15)
      {
        localObject1 = ???;
        localObject2 = localComponentName;
        if (???.getSelector() != null)
        {
          localObject1 = ???.getSelector();
          localObject2 = ((Intent)localObject1).getComponent();
        }
      }
    }
    if (localObject2 != null)
    {
      ??? = new ArrayList(1);
      paramString = getProviderInfo((ComponentName)localObject2, paramInt1, paramInt2);
      if (paramString != null)
      {
        localObject1 = new ResolveInfo();
        ((ResolveInfo)localObject1).providerInfo = paramString;
        ???.add(localObject1);
      }
      return ???;
    }
    synchronized (this.mPackages)
    {
      localObject2 = ((Intent)localObject1).getPackage();
      if (localObject2 == null)
      {
        paramString = this.mProviders.queryIntent((Intent)localObject1, paramString, paramInt1, paramInt2);
        return paramString;
      }
      localObject2 = (VPackage)this.mPackages.get(localObject2);
      if (localObject2 != null)
      {
        paramString = this.mProviders.queryIntentForPackage((Intent)localObject1, paramString, paramInt1, ((VPackage)localObject2).providers, paramInt2);
        return paramString;
      }
      paramString = Collections.emptyList();
      return paramString;
    }
  }
  
  public List<ResolveInfo> queryIntentReceivers(Intent arg1, String paramString, int paramInt1, int paramInt2)
  {
    checkUserId(paramInt2);
    paramInt1 = updateFlagsNought(paramInt1);
    ComponentName localComponentName = ???.getComponent();
    Object localObject1 = ???;
    Object localObject2 = localComponentName;
    if (localComponentName == null)
    {
      localObject1 = ???;
      localObject2 = localComponentName;
      if (Build.VERSION.SDK_INT >= 15)
      {
        localObject1 = ???;
        localObject2 = localComponentName;
        if (???.getSelector() != null)
        {
          localObject1 = ???.getSelector();
          localObject2 = ((Intent)localObject1).getComponent();
        }
      }
    }
    if (localObject2 != null)
    {
      ??? = new ArrayList(1);
      paramString = getReceiverInfo((ComponentName)localObject2, paramInt1, paramInt2);
      if (paramString != null)
      {
        localObject1 = new ResolveInfo();
        ((ResolveInfo)localObject1).activityInfo = paramString;
        ???.add(localObject1);
      }
      return ???;
    }
    synchronized (this.mPackages)
    {
      localObject2 = ((Intent)localObject1).getPackage();
      if (localObject2 == null)
      {
        paramString = this.mReceivers.queryIntent((Intent)localObject1, paramString, paramInt1, paramInt2);
        return paramString;
      }
      localObject2 = (VPackage)this.mPackages.get(localObject2);
      if (localObject2 != null)
      {
        paramString = this.mReceivers.queryIntentForPackage((Intent)localObject1, paramString, paramInt1, ((VPackage)localObject2).receivers, paramInt2);
        return paramString;
      }
      paramString = Collections.emptyList();
      return paramString;
    }
  }
  
  public List<ResolveInfo> queryIntentServices(Intent arg1, String paramString, int paramInt1, int paramInt2)
  {
    checkUserId(paramInt2);
    paramInt1 = updateFlagsNought(paramInt1);
    ComponentName localComponentName = ???.getComponent();
    Object localObject1 = ???;
    Object localObject2 = localComponentName;
    if (localComponentName == null)
    {
      localObject1 = ???;
      localObject2 = localComponentName;
      if (Build.VERSION.SDK_INT >= 15)
      {
        localObject1 = ???;
        localObject2 = localComponentName;
        if (???.getSelector() != null)
        {
          localObject1 = ???.getSelector();
          localObject2 = ((Intent)localObject1).getComponent();
        }
      }
    }
    if (localObject2 != null)
    {
      ??? = new ArrayList(1);
      paramString = getServiceInfo((ComponentName)localObject2, paramInt1, paramInt2);
      if (paramString != null)
      {
        localObject1 = new ResolveInfo();
        ((ResolveInfo)localObject1).serviceInfo = paramString;
        ???.add(localObject1);
      }
      return ???;
    }
    synchronized (this.mPackages)
    {
      localObject2 = ((Intent)localObject1).getPackage();
      if (localObject2 == null)
      {
        paramString = this.mServices.queryIntent((Intent)localObject1, paramString, paramInt1, paramInt2);
        return paramString;
      }
      localObject2 = (VPackage)this.mPackages.get(localObject2);
      if (localObject2 != null)
      {
        paramString = this.mServices.queryIntentForPackage((Intent)localObject1, paramString, paramInt1, ((VPackage)localObject2).services, paramInt2);
        return paramString;
      }
      paramString = Collections.emptyList();
      return paramString;
    }
  }
  
  public List<PermissionInfo> queryPermissionsByGroup(String arg1, int paramInt)
  {
    synchronized (this.mPackages)
    {
      return null;
    }
  }
  
  public List<String> querySharedPackages(String paramString)
  {
    synchronized (this.mPackages)
    {
      paramString = (VPackage)this.mPackages.get(paramString);
      if ((paramString != null) && (paramString.mSharedUserId != null))
      {
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = this.mPackages.values().iterator();
        while (localIterator.hasNext())
        {
          VPackage localVPackage = (VPackage)localIterator.next();
          if (TextUtils.equals(localVPackage.mSharedUserId, paramString.mSharedUserId)) {
            localArrayList.add(localVPackage.packageName);
          }
        }
        return localArrayList;
      }
      paramString = Collections.EMPTY_LIST;
      return paramString;
    }
  }
  
  public ProviderInfo resolveContentProvider(String paramString, int paramInt1, int paramInt2)
  {
    checkUserId(paramInt2);
    paramInt1 = updateFlagsNought(paramInt1);
    synchronized (this.mPackages)
    {
      paramString = (VPackage.ProviderComponent)this.mProvidersByAuthority.get(paramString);
      if (paramString != null)
      {
        paramString = PackageParserEx.generateProviderInfo(paramString, paramInt1, ((PackageSetting)paramString.owner.mExtras).readUserState(paramInt2), paramInt2);
        if (paramString != null)
        {
          ComponentFixer.fixComponentInfo((PackageSetting)((VPackage)this.mPackages.get(paramString.packageName)).mExtras, paramString, paramInt2);
          return paramString;
        }
      }
      return null;
    }
  }
  
  public ResolveInfo resolveIntent(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    checkUserId(paramInt2);
    paramInt1 = updateFlagsNought(paramInt1);
    return chooseBestActivity(paramIntent, paramString, paramInt1, queryIntentActivities(paramIntent, paramString, paramInt1, 0));
  }
  
  public ResolveInfo resolveService(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    checkUserId(paramInt2);
    paramIntent = queryIntentServices(paramIntent, paramString, updateFlagsNought(paramInt1), paramInt2);
    if ((paramIntent != null) && (paramIntent.size() >= 1)) {
      return (ResolveInfo)paramIntent.get(0);
    }
    return null;
  }
  
  final class ActivityIntentResolver
    extends IntentResolver<VPackage.ActivityIntentInfo, ResolveInfo>
  {
    private final HashMap<ComponentName, VPackage.ActivityComponent> mActivities = new HashMap();
    private int mFlags;
    
    private ActivityIntentResolver() {}
    
    public final void addActivity(VPackage.ActivityComponent paramActivityComponent, String paramString)
    {
      this.mActivities.put(paramActivityComponent.getComponentName(), paramActivityComponent);
      int j = paramActivityComponent.intents.size();
      int i = 0;
      while (i < j)
      {
        VPackage.ActivityIntentInfo localActivityIntentInfo = (VPackage.ActivityIntentInfo)paramActivityComponent.intents.get(i);
        if ((localActivityIntentInfo.filter.getPriority() > 0) && ("activity".equals(paramString)))
        {
          localActivityIntentInfo.filter.setPriority(0);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Package ");
          localStringBuilder.append(paramActivityComponent.info.applicationInfo.packageName);
          localStringBuilder.append(" has activity ");
          localStringBuilder.append(paramActivityComponent.className);
          localStringBuilder.append(" with priority > 0, forcing to 0");
          Log.w("PackageManager", localStringBuilder.toString());
        }
        addFilter(localActivityIntentInfo);
        i += 1;
      }
    }
    
    protected boolean allowFilterResult(VPackage.ActivityIntentInfo paramActivityIntentInfo, List<ResolveInfo> paramList)
    {
      paramActivityIntentInfo = paramActivityIntentInfo.activity.info;
      int i = paramList.size() - 1;
      while (i >= 0)
      {
        ActivityInfo localActivityInfo = ((ResolveInfo)paramList.get(i)).activityInfo;
        if ((ObjectsCompat.equals(localActivityInfo.name, paramActivityIntentInfo.name)) && (ObjectsCompat.equals(localActivityInfo.packageName, paramActivityIntentInfo.packageName))) {
          return false;
        }
        i -= 1;
      }
      return true;
    }
    
    protected void dumpFilter(PrintWriter paramPrintWriter, String paramString, VPackage.ActivityIntentInfo paramActivityIntentInfo) {}
    
    protected void dumpFilterLabel(PrintWriter paramPrintWriter, String paramString, Object paramObject, int paramInt) {}
    
    protected Object filterToLabel(VPackage.ActivityIntentInfo paramActivityIntentInfo)
    {
      return paramActivityIntentInfo.activity;
    }
    
    protected boolean isFilterStopped(VPackage.ActivityIntentInfo paramActivityIntentInfo)
    {
      return false;
    }
    
    protected boolean isPackageForFilter(String paramString, VPackage.ActivityIntentInfo paramActivityIntentInfo)
    {
      return paramString.equals(paramActivityIntentInfo.activity.owner.packageName);
    }
    
    protected VPackage.ActivityIntentInfo[] newArray(int paramInt)
    {
      return new VPackage.ActivityIntentInfo[paramInt];
    }
    
    protected ResolveInfo newResult(VPackage.ActivityIntentInfo paramActivityIntentInfo, int paramInt1, int paramInt2)
    {
      VPackage.ActivityComponent localActivityComponent = paramActivityIntentInfo.activity;
      Object localObject = (PackageSetting)localActivityComponent.owner.mExtras;
      localObject = PackageParserEx.generateActivityInfo(localActivityComponent, this.mFlags, ((PackageSetting)localObject).readUserState(paramInt2), paramInt2);
      if (localObject == null) {
        return null;
      }
      ResolveInfo localResolveInfo = new ResolveInfo();
      localResolveInfo.activityInfo = ((ActivityInfo)localObject);
      if ((this.mFlags & 0x40) != 0) {
        localResolveInfo.filter = paramActivityIntentInfo.filter;
      }
      localResolveInfo.priority = paramActivityIntentInfo.filter.getPriority();
      localResolveInfo.preferredOrder = localActivityComponent.owner.mPreferredOrder;
      localResolveInfo.match = paramInt1;
      localResolveInfo.isDefault = paramActivityIntentInfo.hasDefault;
      localResolveInfo.labelRes = paramActivityIntentInfo.labelRes;
      localResolveInfo.nonLocalizedLabel = paramActivityIntentInfo.nonLocalizedLabel;
      localResolveInfo.icon = paramActivityIntentInfo.icon;
      return localResolveInfo;
    }
    
    List<ResolveInfo> queryIntent(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
    {
      this.mFlags = paramInt1;
      boolean bool;
      if ((paramInt1 & 0x10000) != 0) {
        bool = true;
      } else {
        bool = false;
      }
      return super.queryIntent(paramIntent, paramString, bool, paramInt2);
    }
    
    public List<ResolveInfo> queryIntent(Intent paramIntent, String paramString, boolean paramBoolean, int paramInt)
    {
      int i;
      if (paramBoolean) {
        i = 65536;
      } else {
        i = 0;
      }
      this.mFlags = i;
      return super.queryIntent(paramIntent, paramString, paramBoolean, paramInt);
    }
    
    List<ResolveInfo> queryIntentForPackage(Intent paramIntent, String paramString, int paramInt1, ArrayList<VPackage.ActivityComponent> paramArrayList, int paramInt2)
    {
      if (paramArrayList == null) {
        return null;
      }
      this.mFlags = paramInt1;
      int i = 0;
      boolean bool;
      if ((paramInt1 & 0x10000) != 0) {
        bool = true;
      } else {
        bool = false;
      }
      int j = paramArrayList.size();
      ArrayList localArrayList1 = new ArrayList(j);
      paramInt1 = i;
      while (paramInt1 < j)
      {
        ArrayList localArrayList2 = ((VPackage.ActivityComponent)paramArrayList.get(paramInt1)).intents;
        if ((localArrayList2 != null) && (localArrayList2.size() > 0))
        {
          VPackage.ActivityIntentInfo[] arrayOfActivityIntentInfo = new VPackage.ActivityIntentInfo[localArrayList2.size()];
          localArrayList2.toArray(arrayOfActivityIntentInfo);
          localArrayList1.add(arrayOfActivityIntentInfo);
        }
        paramInt1 += 1;
      }
      return super.queryIntentFromList(paramIntent, paramString, bool, localArrayList1, paramInt2);
    }
    
    public final void removeActivity(VPackage.ActivityComponent paramActivityComponent, String paramString)
    {
      this.mActivities.remove(paramActivityComponent.getComponentName());
      int j = paramActivityComponent.intents.size();
      int i = 0;
      while (i < j)
      {
        removeFilter((VPackage.ActivityIntentInfo)paramActivityComponent.intents.get(i));
        i += 1;
      }
    }
    
    protected void sortResults(List<ResolveInfo> paramList)
    {
      Collections.sort(paramList, VPackageManagerService.sResolvePrioritySorter);
    }
  }
  
  final class ServiceIntentResolver
    extends IntentResolver<VPackage.ServiceIntentInfo, ResolveInfo>
  {
    private int mFlags;
    private final HashMap<ComponentName, VPackage.ServiceComponent> mServices = new HashMap();
    
    private ServiceIntentResolver() {}
    
    public final void addService(VPackage.ServiceComponent paramServiceComponent)
    {
      this.mServices.put(paramServiceComponent.getComponentName(), paramServiceComponent);
      int j = paramServiceComponent.intents.size();
      int i = 0;
      while (i < j)
      {
        addFilter((VPackage.ServiceIntentInfo)paramServiceComponent.intents.get(i));
        i += 1;
      }
    }
    
    protected boolean allowFilterResult(VPackage.ServiceIntentInfo paramServiceIntentInfo, List<ResolveInfo> paramList)
    {
      paramServiceIntentInfo = paramServiceIntentInfo.service.info;
      int i = paramList.size() - 1;
      while (i >= 0)
      {
        ServiceInfo localServiceInfo = ((ResolveInfo)paramList.get(i)).serviceInfo;
        if ((ObjectsCompat.equals(localServiceInfo.name, paramServiceIntentInfo.name)) && (ObjectsCompat.equals(localServiceInfo.packageName, paramServiceIntentInfo.packageName))) {
          return false;
        }
        i -= 1;
      }
      return true;
    }
    
    protected void dumpFilter(PrintWriter paramPrintWriter, String paramString, VPackage.ServiceIntentInfo paramServiceIntentInfo) {}
    
    protected void dumpFilterLabel(PrintWriter paramPrintWriter, String paramString, Object paramObject, int paramInt) {}
    
    protected Object filterToLabel(VPackage.ServiceIntentInfo paramServiceIntentInfo)
    {
      return paramServiceIntentInfo.service;
    }
    
    protected boolean isFilterStopped(VPackage.ServiceIntentInfo paramServiceIntentInfo)
    {
      return false;
    }
    
    protected boolean isPackageForFilter(String paramString, VPackage.ServiceIntentInfo paramServiceIntentInfo)
    {
      return paramString.equals(paramServiceIntentInfo.service.owner.packageName);
    }
    
    protected VPackage.ServiceIntentInfo[] newArray(int paramInt)
    {
      return new VPackage.ServiceIntentInfo[paramInt];
    }
    
    protected ResolveInfo newResult(VPackage.ServiceIntentInfo paramServiceIntentInfo, int paramInt1, int paramInt2)
    {
      VPackage.ServiceComponent localServiceComponent = paramServiceIntentInfo.service;
      Object localObject = (PackageSetting)localServiceComponent.owner.mExtras;
      localObject = PackageParserEx.generateServiceInfo(localServiceComponent, this.mFlags, ((PackageSetting)localObject).readUserState(paramInt2), paramInt2);
      if (localObject == null) {
        return null;
      }
      ResolveInfo localResolveInfo = new ResolveInfo();
      localResolveInfo.serviceInfo = ((ServiceInfo)localObject);
      if ((this.mFlags & 0x40) != 0) {
        localResolveInfo.filter = paramServiceIntentInfo.filter;
      }
      localResolveInfo.priority = paramServiceIntentInfo.filter.getPriority();
      localResolveInfo.preferredOrder = localServiceComponent.owner.mPreferredOrder;
      localResolveInfo.match = paramInt1;
      localResolveInfo.isDefault = paramServiceIntentInfo.hasDefault;
      localResolveInfo.labelRes = paramServiceIntentInfo.labelRes;
      localResolveInfo.nonLocalizedLabel = paramServiceIntentInfo.nonLocalizedLabel;
      localResolveInfo.icon = paramServiceIntentInfo.icon;
      return localResolveInfo;
    }
    
    public List<ResolveInfo> queryIntent(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
    {
      this.mFlags = paramInt1;
      boolean bool;
      if ((paramInt1 & 0x10000) != 0) {
        bool = true;
      } else {
        bool = false;
      }
      return super.queryIntent(paramIntent, paramString, bool, paramInt2);
    }
    
    public List<ResolveInfo> queryIntent(Intent paramIntent, String paramString, boolean paramBoolean, int paramInt)
    {
      int i;
      if (paramBoolean) {
        i = 65536;
      } else {
        i = 0;
      }
      this.mFlags = i;
      return super.queryIntent(paramIntent, paramString, paramBoolean, paramInt);
    }
    
    public List<ResolveInfo> queryIntentForPackage(Intent paramIntent, String paramString, int paramInt1, ArrayList<VPackage.ServiceComponent> paramArrayList, int paramInt2)
    {
      if (paramArrayList == null) {
        return null;
      }
      this.mFlags = paramInt1;
      int i = 0;
      boolean bool;
      if ((paramInt1 & 0x10000) != 0) {
        bool = true;
      } else {
        bool = false;
      }
      int j = paramArrayList.size();
      ArrayList localArrayList1 = new ArrayList(j);
      paramInt1 = i;
      while (paramInt1 < j)
      {
        ArrayList localArrayList2 = ((VPackage.ServiceComponent)paramArrayList.get(paramInt1)).intents;
        if ((localArrayList2 != null) && (localArrayList2.size() > 0))
        {
          VPackage.ServiceIntentInfo[] arrayOfServiceIntentInfo = new VPackage.ServiceIntentInfo[localArrayList2.size()];
          localArrayList2.toArray(arrayOfServiceIntentInfo);
          localArrayList1.add(arrayOfServiceIntentInfo);
        }
        paramInt1 += 1;
      }
      return super.queryIntentFromList(paramIntent, paramString, bool, localArrayList1, paramInt2);
    }
    
    public final void removeService(VPackage.ServiceComponent paramServiceComponent)
    {
      this.mServices.remove(paramServiceComponent.getComponentName());
      int j = paramServiceComponent.intents.size();
      int i = 0;
      while (i < j)
      {
        removeFilter((VPackage.ServiceIntentInfo)paramServiceComponent.intents.get(i));
        i += 1;
      }
    }
    
    protected void sortResults(List<ResolveInfo> paramList)
    {
      Collections.sort(paramList, VPackageManagerService.sResolvePrioritySorter);
    }
  }
}
