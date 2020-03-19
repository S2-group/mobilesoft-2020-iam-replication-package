package com.lody.virtual.server.pm;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import com.lody.virtual.client.core.SettingConfig;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.fixer.ComponentFixer;
import com.lody.virtual.client.stub.StubManifest;
import com.lody.virtual.helper.compat.ObjectsCompat;
import com.lody.virtual.helper.compat.PermissionCompat;
import com.lody.virtual.helper.utils.Singleton;
import com.lody.virtual.os.VUserHandle;
import com.lody.virtual.remote.VParceledListSlice;
import com.lody.virtual.server.interfaces.IPackageManager.Stub;
import com.lody.virtual.server.pm.installer.VPackageInstallerService;
import com.lody.virtual.server.pm.parser.PackageParserEx;
import com.lody.virtual.server.pm.parser.VPackage;
import com.lody.virtual.server.pm.parser.VPackage.ActivityComponent;
import com.lody.virtual.server.pm.parser.VPackage.ActivityIntentInfo;
import com.lody.virtual.server.pm.parser.VPackage.PermissionComponent;
import com.lody.virtual.server.pm.parser.VPackage.PermissionGroupComponent;
import com.lody.virtual.server.pm.parser.VPackage.ProviderComponent;
import com.lody.virtual.server.pm.parser.VPackage.ServiceComponent;
import com.lody.virtual.server.pm.parser.VPackage.ServiceIntentInfo;
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
import java.util.Set;

public class VPackageManagerService
  extends IPackageManager.Stub
{
  static final String TAG = "PackageManager";
  private static final Singleton gService = new VPackageManagerService.2();
  private static final Comparator sProviderInitOrderSorter = new VPackageManagerService.3();
  static final Comparator sResolvePrioritySorter = new VPackageManagerService.1();
  private final ActivityIntentResolver mActivities = new ActivityIntentResolver(null);
  private final Map mDangrousPermissions;
  private BroadcastReceiver mPackageMonitor;
  private final Map mPackages;
  private final HashMap mPermissionGroups;
  private final HashMap mPermissions;
  private final ProviderIntentResolver mProviders;
  private final HashMap mProvidersByAuthority;
  private final HashMap mProvidersByComponent;
  private final ActivityIntentResolver mReceivers = new ActivityIntentResolver(null);
  private final ResolveInfo mResolveInfo;
  private final ServiceIntentResolver mServices = new ServiceIntentResolver(null);
  
  public VPackageManagerService()
  {
    if (Build.VERSION.SDK_INT >= 19) {
      localObject = new ProviderIntentResolver();
    }
    this.mProviders = ((ProviderIntentResolver)localObject);
    this.mProvidersByComponent = new HashMap();
    this.mPermissions = new HashMap();
    this.mPermissionGroups = new HashMap();
    this.mProvidersByAuthority = new HashMap();
    this.mPackages = PackageCacheManager.PACKAGE_CACHE;
    this.mDangrousPermissions = new HashMap();
    this.mPackageMonitor = new VPackageManagerService.4(this);
    localObject = new Intent();
    ((Intent)localObject).setClassName(VirtualCore.get().getHostPkg(), StubManifest.RESOLVER_ACTIVITY);
    this.mResolveInfo = VirtualCore.get().getUnHookPackageManager().resolveActivity((Intent)localObject, 0);
  }
  
  private boolean checkUpdateForNotCopyApk()
  {
    return VirtualCore.getConfig().autoCheckUpdateForNotCopyApk();
  }
  
  private void checkUserId(int paramInt)
  {
    if (!VUserManagerService.get().exists(paramInt)) {
      throw new SecurityException("Invalid userId " + paramInt);
    }
  }
  
  private ResolveInfo chooseBestActivity(Intent paramIntent, String paramString, int paramInt, List paramList)
  {
    if (paramList != null)
    {
      int i = paramList.size();
      if (i == 1) {
        paramIntent = (ResolveInfo)paramList.get(0);
      }
      do
      {
        return paramIntent;
        if (i <= 1) {
          break;
        }
        ResolveInfo localResolveInfo1 = (ResolveInfo)paramList.get(0);
        ResolveInfo localResolveInfo2 = (ResolveInfo)paramList.get(1);
        if ((localResolveInfo1.priority != localResolveInfo2.priority) || (localResolveInfo1.preferredOrder != localResolveInfo2.preferredOrder) || (localResolveInfo1.isDefault != localResolveInfo2.isDefault)) {
          return (ResolveInfo)paramList.get(0);
        }
        paramString = findPreferredActivity(paramIntent, paramString, paramInt, paramList, localResolveInfo1.priority);
        paramIntent = paramString;
      } while (paramString != null);
      return (ResolveInfo)paramList.get(0);
    }
    return null;
  }
  
  private PermissionInfo findPermission(String paramString)
  {
    synchronized (this.mPackages)
    {
      VPackage.PermissionComponent localPermissionComponent;
      do
      {
        Iterator localIterator = this.mPackages.values().iterator();
        Object localObject;
        while (!((Iterator)localObject).hasNext())
        {
          do
          {
            if (!localIterator.hasNext()) {
              break;
            }
            localObject = ((VPackage)localIterator.next()).permissions;
          } while (localObject == null);
          localObject = ((ArrayList)localObject).iterator();
        }
        localPermissionComponent = (VPackage.PermissionComponent)((Iterator)localObject).next();
      } while ((localPermissionComponent.info == null) || (!TextUtils.equals(paramString, localPermissionComponent.info.name)));
      paramString = localPermissionComponent.info;
      return paramString;
      return null;
    }
  }
  
  private ResolveInfo findPreferredActivity(Intent paramIntent, String paramString, int paramInt1, List paramList, int paramInt2)
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
  
  private PackageSetting getPackageSettingLocked(String paramString)
  {
    synchronized (this.mPackages)
    {
      paramString = (VPackage)this.mPackages.get(paramString);
      if (paramString != null) {
        return (PackageSetting)paramString.mExtras;
      }
    }
    return null;
  }
  
  private boolean hasRequestedPermission(String paramString1, String paramString2)
  {
    synchronized (this.mPackages)
    {
      paramString2 = (VPackage)this.mPackages.get(paramString2);
      if ((paramString2 != null) && (paramString2.requestedPermissions != null)) {
        return paramString2.requestedPermissions.contains(paramString1);
      }
    }
    return false;
  }
  
  private void registerPackageMonitor()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.PACKAGE_ADDED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
    localIntentFilter.addDataScheme("package");
    VirtualCore.get().getContext().registerReceiver(this.mPackageMonitor, localIntentFilter);
  }
  
  public static void systemReady()
  {
    Context localContext = VirtualCore.get().getContext();
    VPackageManagerService localVPackageManagerService = get();
    Map localMap = get().mPackages;
    new VUserManagerService(localContext, localVPackageManagerService, new char[0], localMap);
  }
  
  private int updateFlagsNought(int paramInt)
  {
    if (Build.VERSION.SDK_INT < 24) {}
    while ((paramInt & 0xC0000) != 0) {
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
        if (paramComponentName != null) {
          break label110;
        }
        return false;
        if (i >= paramComponentName.intents.size()) {
          break label105;
        }
        if (((VPackage.ActivityIntentInfo)paramComponentName.intents.get(i)).filter.match(paramIntent.getAction(), paramString, paramIntent.getScheme(), paramIntent.getData(), paramIntent.getCategories(), "PackageManager") >= 0) {
          return true;
        }
      }
      i += 1;
      continue;
      label105:
      return false;
      label110:
      int i = 0;
    }
  }
  
  void analyzePackageLocked(VPackage paramVPackage)
  {
    int k = 0;
    int j = paramVPackage.activities.size();
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
    VPackage.ProviderComponent localProviderComponent;
    String[] arrayOfString;
    if (i < m)
    {
      localProviderComponent = (VPackage.ProviderComponent)paramVPackage.providers.get(i);
      if (localProviderComponent.info.processName == null) {
        localProviderComponent.info.processName = localProviderComponent.info.packageName;
      }
      if (Build.VERSION.SDK_INT >= 19) {
        this.mProviders.addProvider(localProviderComponent);
      }
      arrayOfString = localProviderComponent.info.authority.split(";");
    }
    for (;;)
    {
      synchronized (this.mProvidersByAuthority)
      {
        int n = arrayOfString.length;
        j = 0;
        if (j < n)
        {
          String str = arrayOfString[j];
          if (this.mProvidersByAuthority.containsKey(str)) {
            break label539;
          }
          this.mProvidersByAuthority.put(str, localProviderComponent);
          break label539;
        }
        this.mProvidersByComponent.put(localProviderComponent.getComponentName(), localProviderComponent);
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
      synchronized (this.mDangrousPermissions)
      {
        this.mDangrousPermissions.put(paramVPackage.packageName, PermissionCompat.findDangrousPermissions(paramVPackage.requestedPermissions));
        return;
      }
      label539:
      j += 1;
    }
  }
  
  public int checkPermission(String paramString1, String paramString2, int paramInt)
  {
    if (("android.permission.INTERACT_ACROSS_USERS".equals(paramString1)) || ("android.permission.INTERACT_ACROSS_USERS_FULL".equals(paramString1))) {
      return -1;
    }
    return VirtualCore.get().getPackageManager().checkPermission(paramString1, VirtualCore.get().getHostPkg());
  }
  
  /* Error */
  public int checkSignatures(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_1
    //   1: aload_2
    //   2: invokestatic 261	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   5: ifeq +5 -> 10
    //   8: iconst_0
    //   9: ireturn
    //   10: aload_0
    //   11: aload_1
    //   12: bipush 64
    //   14: iconst_0
    //   15: invokevirtual 502	com/lody/virtual/server/pm/VPackageManagerService:getPackageInfo	(Ljava/lang/String;II)Landroid/content/pm/PackageInfo;
    //   18: astore 5
    //   20: aload_0
    //   21: aload_2
    //   22: bipush 64
    //   24: iconst_0
    //   25: invokevirtual 502	com/lody/virtual/server/pm/VPackageManagerService:getPackageInfo	(Ljava/lang/String;II)Landroid/content/pm/PackageInfo;
    //   28: astore 4
    //   30: aload 5
    //   32: astore_3
    //   33: aload 5
    //   35: ifnonnull +16 -> 51
    //   38: invokestatic 117	com/lody/virtual/client/core/VirtualCore:get	()Lcom/lody/virtual/client/core/VirtualCore;
    //   41: invokevirtual 134	com/lody/virtual/client/core/VirtualCore:getUnHookPackageManager	()Landroid/content/pm/PackageManager;
    //   44: aload_1
    //   45: bipush 64
    //   47: invokevirtual 505	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   50: astore_3
    //   51: aload 4
    //   53: astore_1
    //   54: aload 4
    //   56: ifnonnull +16 -> 72
    //   59: invokestatic 117	com/lody/virtual/client/core/VirtualCore:get	()Lcom/lody/virtual/client/core/VirtualCore;
    //   62: invokevirtual 134	com/lody/virtual/client/core/VirtualCore:getUnHookPackageManager	()Landroid/content/pm/PackageManager;
    //   65: aload_2
    //   66: bipush 64
    //   68: invokevirtual 505	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   71: astore_1
    //   72: aload_3
    //   73: getfield 511	android/content/pm/PackageInfo:signatures	[Landroid/content/pm/Signature;
    //   76: aload_1
    //   77: getfield 511	android/content/pm/PackageInfo:signatures	[Landroid/content/pm/Signature;
    //   80: invokestatic 517	com/lody/virtual/helper/utils/SignaturesUtils:compareSignatures	([Landroid/content/pm/Signature;[Landroid/content/pm/Signature;)I
    //   83: ireturn
    //   84: astore_1
    //   85: bipush -4
    //   87: ireturn
    //   88: astore_1
    //   89: bipush -4
    //   91: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	92	0	this	VPackageManagerService
    //   0	92	1	paramString1	String
    //   0	92	2	paramString2	String
    //   32	41	3	localPackageInfo1	PackageInfo
    //   28	27	4	localPackageInfo2	PackageInfo
    //   18	16	5	localPackageInfo3	PackageInfo
    // Exception table:
    //   from	to	target	type
    //   38	51	84	android/content/pm/PackageManager$NameNotFoundException
    //   59	72	88	android/content/pm/PackageManager$NameNotFoundException
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
    int k = 0;
    paramString = (VPackage)this.mPackages.get(paramString);
    if (paramString == null) {}
    for (;;)
    {
      return;
      int j = paramString.activities.size();
      int i = 0;
      while (i < j)
      {
        ??? = (VPackage.ActivityComponent)paramString.activities.get(i);
        this.mActivities.removeActivity((VPackage.ActivityComponent)???, "activity");
        i += 1;
      }
      j = paramString.services.size();
      i = 0;
      while (i < j)
      {
        ??? = (VPackage.ServiceComponent)paramString.services.get(i);
        this.mServices.removeService((VPackage.ServiceComponent)???);
        i += 1;
      }
      j = paramString.receivers.size();
      i = 0;
      while (i < j)
      {
        ??? = (VPackage.ActivityComponent)paramString.receivers.get(i);
        this.mReceivers.removeActivity((VPackage.ActivityComponent)???, "receiver");
        i += 1;
      }
      int m = paramString.providers.size();
      i = 0;
      while (i < m)
      {
        VPackage.ProviderComponent localProviderComponent = (VPackage.ProviderComponent)paramString.providers.get(i);
        if (Build.VERSION.SDK_INT >= 19) {
          this.mProviders.removeProvider(localProviderComponent);
        }
        String[] arrayOfString = localProviderComponent.info.authority.split(";");
        synchronized (this.mProvidersByAuthority)
        {
          int n = arrayOfString.length;
          j = 0;
          while (j < n)
          {
            String str = arrayOfString[j];
            this.mProvidersByAuthority.remove(str);
            j += 1;
          }
          this.mProvidersByComponent.remove(localProviderComponent.getComponentName());
          i += 1;
        }
      }
      j = paramString.permissions.size();
      i = 0;
      while (i < j)
      {
        ??? = (VPackage.PermissionComponent)paramString.permissions.get(i);
        this.mPermissions.remove(((VPackage.PermissionComponent)???).className);
        i += 1;
      }
      j = paramString.permissionGroups.size();
      i = k;
      while (i < j)
      {
        ??? = (VPackage.PermissionGroupComponent)paramString.permissionGroups.get(i);
        this.mPermissionGroups.remove(((VPackage.PermissionGroupComponent)???).className);
        i += 1;
      }
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
  
  public String[] getAllAuthorities()
  {
    synchronized (this.mProvidersByAuthority)
    {
      String[] arrayOfString = new String[this.mProvidersByAuthority.keySet().size()];
      this.mProvidersByAuthority.keySet().toArray(arrayOfString);
      return arrayOfString;
    }
  }
  
  public List getAllPermissionGroups(int paramInt)
  {
    synchronized (this.mPackages)
    {
      ArrayList localArrayList = new ArrayList(this.mPermissionGroups.size());
      Iterator localIterator = this.mPermissionGroups.values().iterator();
      if (localIterator.hasNext()) {
        localArrayList.add(new PermissionGroupInfo(((VPackage.PermissionGroupComponent)localIterator.next()).info));
      }
    }
    return localList;
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
        paramString = PackageParserEx.generateApplicationInfoOut(paramString, paramInt1, ((PackageSetting)paramString.mExtras).readUserState(paramInt2), paramInt2);
        return paramString;
      }
      return null;
    }
  }
  
  public String[] getDangrousPermissions(String paramString)
  {
    synchronized (this.mDangrousPermissions)
    {
      paramString = (String[])this.mDangrousPermissions.get(paramString);
      return paramString;
    }
  }
  
  public VParceledListSlice getInstalledApplications(int paramInt1, int paramInt2)
  {
    checkUserId(paramInt2);
    paramInt1 = updateFlagsNought(paramInt1);
    ArrayList localArrayList = new ArrayList(this.mPackages.size());
    synchronized (this.mPackages)
    {
      Iterator localIterator = this.mPackages.values().iterator();
      while (localIterator.hasNext())
      {
        Object localObject = (VPackage)localIterator.next();
        localObject = PackageParserEx.generateApplicationInfoOut((VPackage)localObject, paramInt1, ((PackageSetting)((VPackage)localObject).mExtras).readUserState(paramInt2), paramInt2);
        if (localObject != null) {
          localArrayList.add(localObject);
        }
      }
    }
    return new VParceledListSlice(localList);
  }
  
  public VParceledListSlice getInstalledPackages(int paramInt1, int paramInt2)
  {
    checkUserId(paramInt2);
    ArrayList localArrayList = new ArrayList(this.mPackages.size());
    synchronized (this.mPackages)
    {
      Iterator localIterator = this.mPackages.values().iterator();
      while (localIterator.hasNext())
      {
        Object localObject = (VPackage)localIterator.next();
        localObject = generatePackageInfo((VPackage)localObject, (PackageSetting)((VPackage)localObject).mExtras, paramInt1, paramInt2);
        if (localObject != null) {
          localArrayList.add(localObject);
        }
      }
    }
    return new VParceledListSlice(localList);
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
  
  public ApplicationInfo getOutSideApplicationInfo(String paramString)
  {
    try
    {
      paramString = VirtualCore.get().getUnHookPackageManager().getApplicationInfo(paramString, 0);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return null;
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
  
  public IBinder getPackageInstaller()
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
      ArrayList localArrayList = new ArrayList(2);
      Iterator localIterator = this.mPackages.values().iterator();
      while (localIterator.hasNext())
      {
        VPackage localVPackage = (VPackage)localIterator.next();
        if (VUserHandle.getUid(i, ((PackageSetting)localVPackage.mExtras).appId) == paramInt) {
          localArrayList.add(localVPackage.packageName);
        }
      }
      arrayOfString = (String[])localObject.toArray(new String[0]);
    }
    finally {}
    String[] arrayOfString;
    return arrayOfString;
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
  
  public List getSharedLibraries(String paramString)
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
  
  public VParceledListSlice queryContentProviders(String paramString, int paramInt1, int paramInt2)
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
        if ((!localProviderComponent.info.multiprocess) && ((paramString == null) || ((localPackageSetting.appId == VUserHandle.getAppId(paramInt1)) && (localProviderComponent.info.processName.equals(paramString))))) {
          localArrayList.add(PackageParserEx.generateProviderInfo(localProviderComponent, paramInt2, localPackageSetting.readUserState(i), i));
        }
      }
    }
    if (!localArrayList.isEmpty()) {
      Collections.sort(localArrayList, sProviderInitOrderSorter);
    }
    return new VParceledListSlice(localArrayList);
  }
  
  public List queryIntentActivities(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    checkUserId(paramInt2);
    paramInt1 = updateFlagsNought(paramInt1);
    ??? = paramIntent.getComponent();
    if ((??? == null) && (Build.VERSION.SDK_INT >= 15) && (paramIntent.getSelector() != null))
    {
      paramIntent = paramIntent.getSelector();
      ??? = paramIntent.getComponent();
    }
    for (;;)
    {
      if (??? != null)
      {
        paramIntent = new ArrayList(1);
        paramString = getActivityInfo((ComponentName)???, paramInt1, paramInt2);
        if (paramString != null)
        {
          ??? = new ResolveInfo();
          ((ResolveInfo)???).activityInfo = paramString;
          paramIntent.add(???);
        }
        return paramIntent;
      }
      synchronized (this.mPackages)
      {
        localObject2 = paramIntent.getPackage();
        if (localObject2 == null)
        {
          paramIntent = this.mActivities.queryIntent(paramIntent, paramString, paramInt1, paramInt2);
          return paramIntent;
        }
      }
      Object localObject2 = (VPackage)this.mPackages.get(localObject2);
      if (localObject2 != null)
      {
        paramIntent = this.mActivities.queryIntentForPackage(paramIntent, paramString, paramInt1, ((VPackage)localObject2).activities, paramInt2);
        return paramIntent;
      }
      paramIntent = Collections.emptyList();
      return paramIntent;
    }
  }
  
  @TargetApi(19)
  public List queryIntentContentProviders(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    checkUserId(paramInt2);
    paramInt1 = updateFlagsNought(paramInt1);
    ??? = paramIntent.getComponent();
    if ((??? == null) && (Build.VERSION.SDK_INT >= 15) && (paramIntent.getSelector() != null))
    {
      paramIntent = paramIntent.getSelector();
      ??? = paramIntent.getComponent();
    }
    for (;;)
    {
      if (??? != null)
      {
        paramIntent = new ArrayList(1);
        paramString = getProviderInfo((ComponentName)???, paramInt1, paramInt2);
        if (paramString != null)
        {
          ??? = new ResolveInfo();
          ((ResolveInfo)???).providerInfo = paramString;
          paramIntent.add(???);
        }
        return paramIntent;
      }
      synchronized (this.mPackages)
      {
        localObject2 = paramIntent.getPackage();
        if (localObject2 == null)
        {
          paramIntent = this.mProviders.queryIntent(paramIntent, paramString, paramInt1, paramInt2);
          return paramIntent;
        }
      }
      Object localObject2 = (VPackage)this.mPackages.get(localObject2);
      if (localObject2 != null)
      {
        paramIntent = this.mProviders.queryIntentForPackage(paramIntent, paramString, paramInt1, ((VPackage)localObject2).providers, paramInt2);
        return paramIntent;
      }
      paramIntent = Collections.emptyList();
      return paramIntent;
    }
  }
  
  public List queryIntentReceivers(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    checkUserId(paramInt2);
    paramInt1 = updateFlagsNought(paramInt1);
    ??? = paramIntent.getComponent();
    if ((??? == null) && (Build.VERSION.SDK_INT >= 15) && (paramIntent.getSelector() != null))
    {
      paramIntent = paramIntent.getSelector();
      ??? = paramIntent.getComponent();
    }
    for (;;)
    {
      if (??? != null)
      {
        paramIntent = new ArrayList(1);
        paramString = getReceiverInfo((ComponentName)???, paramInt1, paramInt2);
        if (paramString != null)
        {
          ??? = new ResolveInfo();
          ((ResolveInfo)???).activityInfo = paramString;
          paramIntent.add(???);
        }
        return paramIntent;
      }
      synchronized (this.mPackages)
      {
        localObject2 = paramIntent.getPackage();
        if (localObject2 == null)
        {
          paramIntent = this.mReceivers.queryIntent(paramIntent, paramString, paramInt1, paramInt2);
          return paramIntent;
        }
      }
      Object localObject2 = (VPackage)this.mPackages.get(localObject2);
      if (localObject2 != null)
      {
        paramIntent = this.mReceivers.queryIntentForPackage(paramIntent, paramString, paramInt1, ((VPackage)localObject2).receivers, paramInt2);
        return paramIntent;
      }
      paramIntent = Collections.emptyList();
      return paramIntent;
    }
  }
  
  public List queryIntentServices(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    checkUserId(paramInt2);
    paramInt1 = updateFlagsNought(paramInt1);
    ??? = paramIntent.getComponent();
    if ((??? == null) && (Build.VERSION.SDK_INT >= 15) && (paramIntent.getSelector() != null))
    {
      paramIntent = paramIntent.getSelector();
      ??? = paramIntent.getComponent();
    }
    for (;;)
    {
      if (??? != null)
      {
        paramIntent = new ArrayList(1);
        paramString = getServiceInfo((ComponentName)???, paramInt1, paramInt2);
        if (paramString != null)
        {
          ??? = new ResolveInfo();
          ((ResolveInfo)???).serviceInfo = paramString;
          paramIntent.add(???);
        }
        return paramIntent;
      }
      synchronized (this.mPackages)
      {
        localObject2 = paramIntent.getPackage();
        if (localObject2 == null)
        {
          paramIntent = this.mServices.queryIntent(paramIntent, paramString, paramInt1, paramInt2);
          return paramIntent;
        }
      }
      Object localObject2 = (VPackage)this.mPackages.get(localObject2);
      if (localObject2 != null)
      {
        paramIntent = this.mServices.queryIntentForPackage(paramIntent, paramString, paramInt1, ((VPackage)localObject2).services, paramInt2);
        return paramIntent;
      }
      paramIntent = Collections.emptyList();
      return paramIntent;
    }
  }
  
  public List queryPermissionsByGroup(String arg1, int paramInt)
  {
    synchronized (this.mPackages)
    {
      return null;
    }
  }
  
  public List querySharedPackages(String paramString)
  {
    ArrayList localArrayList;
    synchronized (this.mPackages)
    {
      paramString = (VPackage)this.mPackages.get(paramString);
      if ((paramString == null) || (paramString.mSharedUserId == null))
      {
        paramString = Collections.EMPTY_LIST;
        return paramString;
      }
      localArrayList = new ArrayList();
      Iterator localIterator = this.mPackages.values().iterator();
      while (localIterator.hasNext())
      {
        VPackage localVPackage = (VPackage)localIterator.next();
        if (TextUtils.equals(localVPackage.mSharedUserId, paramString.mSharedUserId)) {
          localArrayList.add(localVPackage.packageName);
        }
      }
    }
    return localArrayList;
  }
  
  public ProviderInfo resolveContentProvider(String paramString, int paramInt1, int paramInt2)
  {
    checkUserId(paramInt2);
    paramInt1 = updateFlagsNought(paramInt1);
    synchronized (this.mProvidersByAuthority)
    {
      paramString = (VPackage.ProviderComponent)this.mProvidersByAuthority.get(paramString);
      if (paramString != null)
      {
        paramString = PackageParserEx.generateProviderInfo(paramString, paramInt1, ((PackageSetting)paramString.owner.mExtras).readUserState(paramInt2), paramInt2);
        if (paramString != null)
        {
          ComponentFixer.fixComponentInfo(getPackageSettingLocked(paramString.packageName), paramString, paramInt2);
          return paramString;
        }
      }
    }
    return null;
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
    if ((paramIntent != null) && (paramIntent.size() > 0)) {
      return (ResolveInfo)paramIntent.get(0);
    }
    return null;
  }
  
  private final class ActivityIntentResolver
    extends IntentResolver
  {
    private final HashMap mActivities = new HashMap();
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
          Log.w("PackageManager", "Package " + paramActivityComponent.info.applicationInfo.packageName + " has activity " + paramActivityComponent.className + " with priority > 0, forcing to 0");
        }
        addFilter(localActivityIntentInfo);
        i += 1;
      }
    }
    
    protected final boolean allowFilterResult(VPackage.ActivityIntentInfo paramActivityIntentInfo, List paramList)
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
    
    protected final void dumpFilter(PrintWriter paramPrintWriter, String paramString, VPackage.ActivityIntentInfo paramActivityIntentInfo) {}
    
    protected final void dumpFilterLabel(PrintWriter paramPrintWriter, String paramString, Object paramObject, int paramInt) {}
    
    protected final Object filterToLabel(VPackage.ActivityIntentInfo paramActivityIntentInfo)
    {
      return paramActivityIntentInfo.activity;
    }
    
    protected final boolean isFilterStopped(VPackage.ActivityIntentInfo paramActivityIntentInfo)
    {
      return false;
    }
    
    protected final boolean isPackageForFilter(String paramString, VPackage.ActivityIntentInfo paramActivityIntentInfo)
    {
      return paramString.equals(paramActivityIntentInfo.activity.owner.packageName);
    }
    
    protected final VPackage.ActivityIntentInfo[] newArray(int paramInt)
    {
      return new VPackage.ActivityIntentInfo[paramInt];
    }
    
    protected final ResolveInfo newResult(VPackage.ActivityIntentInfo paramActivityIntentInfo, int paramInt1, int paramInt2)
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
    
    final List queryIntent(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
    {
      this.mFlags = paramInt1;
      if ((0x10000 & paramInt1) != 0) {}
      for (boolean bool = true;; bool = false) {
        return super.queryIntent(paramIntent, paramString, bool, paramInt2);
      }
    }
    
    public final List queryIntent(Intent paramIntent, String paramString, boolean paramBoolean, int paramInt)
    {
      if (paramBoolean) {}
      for (int i = 65536;; i = 0)
      {
        this.mFlags = i;
        return super.queryIntent(paramIntent, paramString, paramBoolean, paramInt);
      }
    }
    
    final List queryIntentForPackage(Intent paramIntent, String paramString, int paramInt1, ArrayList paramArrayList, int paramInt2)
    {
      if (paramArrayList == null) {
        return null;
      }
      this.mFlags = paramInt1;
      if ((0x10000 & paramInt1) != 0) {}
      ArrayList localArrayList1;
      for (boolean bool = true;; bool = false)
      {
        int i = paramArrayList.size();
        localArrayList1 = new ArrayList(i);
        paramInt1 = 0;
        while (paramInt1 < i)
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
    
    protected final void sortResults(List paramList)
    {
      Collections.sort(paramList, VPackageManagerService.sResolvePrioritySorter);
    }
  }
  
  private final class ServiceIntentResolver
    extends IntentResolver
  {
    private int mFlags;
    private final HashMap mServices = new HashMap();
    
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
    
    protected final boolean allowFilterResult(VPackage.ServiceIntentInfo paramServiceIntentInfo, List paramList)
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
    
    protected final void dumpFilter(PrintWriter paramPrintWriter, String paramString, VPackage.ServiceIntentInfo paramServiceIntentInfo) {}
    
    protected final void dumpFilterLabel(PrintWriter paramPrintWriter, String paramString, Object paramObject, int paramInt) {}
    
    protected final Object filterToLabel(VPackage.ServiceIntentInfo paramServiceIntentInfo)
    {
      return paramServiceIntentInfo.service;
    }
    
    protected final boolean isFilterStopped(VPackage.ServiceIntentInfo paramServiceIntentInfo)
    {
      return false;
    }
    
    protected final boolean isPackageForFilter(String paramString, VPackage.ServiceIntentInfo paramServiceIntentInfo)
    {
      return paramString.equals(paramServiceIntentInfo.service.owner.packageName);
    }
    
    protected final VPackage.ServiceIntentInfo[] newArray(int paramInt)
    {
      return new VPackage.ServiceIntentInfo[paramInt];
    }
    
    protected final ResolveInfo newResult(VPackage.ServiceIntentInfo paramServiceIntentInfo, int paramInt1, int paramInt2)
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
    
    public final List queryIntent(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
    {
      this.mFlags = paramInt1;
      if ((0x10000 & paramInt1) != 0) {}
      for (boolean bool = true;; bool = false) {
        return super.queryIntent(paramIntent, paramString, bool, paramInt2);
      }
    }
    
    public final List queryIntent(Intent paramIntent, String paramString, boolean paramBoolean, int paramInt)
    {
      if (paramBoolean) {}
      for (int i = 65536;; i = 0)
      {
        this.mFlags = i;
        return super.queryIntent(paramIntent, paramString, paramBoolean, paramInt);
      }
    }
    
    public final List queryIntentForPackage(Intent paramIntent, String paramString, int paramInt1, ArrayList paramArrayList, int paramInt2)
    {
      if (paramArrayList == null) {
        return null;
      }
      this.mFlags = paramInt1;
      if ((0x10000 & paramInt1) != 0) {}
      ArrayList localArrayList1;
      for (boolean bool = true;; bool = false)
      {
        int i = paramArrayList.size();
        localArrayList1 = new ArrayList(i);
        paramInt1 = 0;
        while (paramInt1 < i)
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
    
    protected final void sortResults(List paramList)
    {
      Collections.sort(paramList, VPackageManagerService.sResolvePrioritySorter);
    }
  }
}
