package com.lody.virtual.client.hook.proxies.pm;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDataObserver;
import android.content.pm.IPackageDeleteObserver2;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Binder;
import android.os.IInterface;
import android.os.Process;
import com.lody.virtual.client.VClient;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.base.MethodProxy;
import com.lody.virtual.client.hook.utils.MethodParameterUtils;
import com.lody.virtual.client.ipc.VPackageManager;
import com.lody.virtual.helper.collection.ArraySet;
import com.lody.virtual.helper.compat.ParceledListSliceCompat;
import com.lody.virtual.helper.utils.ArrayUtils;
import com.lody.virtual.os.VUserHandle;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import mirror.RefMethod;
import mirror.android.content.pm.ParceledListSlice;

class MethodProxies
{
  MethodProxies() {}
  
  static class ActivitySupportsIntent
    extends MethodProxy
  {
    ActivitySupportsIntent() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      paramObject = (ComponentName)paramVarArgs[0];
      paramMethod = (Intent)paramVarArgs[1];
      paramVarArgs = (String)paramVarArgs[2];
      return Boolean.valueOf(VPackageManager.get().activitySupportsIntent(paramObject, paramMethod, paramVarArgs));
    }
    
    public String getMethodName()
    {
      return "activitySupportsIntent";
    }
  }
  
  static class AddPackageToPreferred
    extends MethodProxy
  {
    AddPackageToPreferred() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      return Integer.valueOf(0);
    }
    
    public String getMethodName()
    {
      return "addPackageToPreferred";
    }
  }
  
  static class CheckPermission
    extends MethodProxy
  {
    CheckPermission() {}
    
    public Object afterCall(Object paramObject1, Method paramMethod, Object[] paramArrayOfObject, Object paramObject2)
    {
      return super.afterCall(paramObject1, paramMethod, paramArrayOfObject, paramObject2);
    }
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      paramObject = (String)paramVarArgs[0];
      paramMethod = (String)paramVarArgs[1];
      int i = VUserHandle.myUserId();
      return Integer.valueOf(VPackageManager.get().checkPermission(paramObject, paramMethod, i));
    }
    
    public String getMethodName()
    {
      return "checkPermission";
    }
    
    public boolean isEnable()
    {
      return isAppProcess();
    }
  }
  
  @SuppressLint({"PackageManagerGetSignatures"})
  static class CheckSignatures
    extends MethodProxy
  {
    CheckSignatures() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      if ((paramVarArgs.length == 2) && ((paramVarArgs[0] instanceof String)) && ((paramVarArgs[1] instanceof String)))
      {
        Object localObject1 = VirtualCore.getPM();
        Object localObject3 = (String)paramVarArgs[0];
        Object localObject2 = (String)paramVarArgs[1];
        try
        {
          localObject3 = ((PackageManager)localObject1).getPackageInfo((String)localObject3, 64);
          localObject1 = ((PackageManager)localObject1).getPackageInfo((String)localObject2, 64);
          localObject2 = ((PackageInfo)localObject3).signatures;
          localObject1 = ((PackageInfo)localObject1).signatures;
          if (ArrayUtils.isEmpty((Object[])localObject2))
          {
            if (!ArrayUtils.isEmpty((Object[])localObject1)) {
              return Integer.valueOf(-1);
            }
            return Integer.valueOf(1);
          }
          if (ArrayUtils.isEmpty((Object[])localObject1)) {
            return Integer.valueOf(-2);
          }
          if (Arrays.equals((Object[])localObject2, (Object[])localObject1)) {
            return Integer.valueOf(0);
          }
          return Integer.valueOf(-3);
        }
        catch (Throwable localThrowable) {}
      }
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String getMethodName()
    {
      return "checkSignatures";
    }
  }
  
  static class ClearPackagePersistentPreferredActivities
    extends MethodProxy
  {
    ClearPackagePersistentPreferredActivities() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      MethodParameterUtils.replaceFirstAppPkg(paramVarArgs);
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String getMethodName()
    {
      return "clearPackagePersistentPreferredActivities";
    }
  }
  
  static class ClearPackagePreferredActivities
    extends MethodProxy
  {
    ClearPackagePreferredActivities() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      MethodParameterUtils.replaceFirstAppPkg(paramVarArgs);
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String getMethodName()
    {
      return "clearPackagePreferredActivities";
    }
  }
  
  static class DeleteApplicationCacheFiles
    extends MethodProxy
  {
    DeleteApplicationCacheFiles() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String getMethodName()
    {
      return "deleteApplicationCacheFiles";
    }
  }
  
  static class DeletePackage
    extends MethodProxy
  {
    DeletePackage() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      paramObject = (String)paramVarArgs[0];
      try
      {
        VirtualCore.get().uninstallPackage(paramObject);
        paramMethod = (IPackageDeleteObserver2)paramVarArgs[1];
        if (paramMethod != null) {
          paramMethod.onPackageDeleted(paramObject, 0, "done.");
        }
      }
      catch (Throwable paramObject)
      {
        for (;;) {}
      }
      return Integer.valueOf(0);
    }
    
    public String getMethodName()
    {
      return "deletePackage";
    }
  }
  
  static class FreeStorageAndNotify
    extends MethodProxy
  {
    FreeStorageAndNotify() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      if ((paramVarArgs[(paramVarArgs.length - 1)] instanceof IPackageDataObserver)) {
        ((IPackageDataObserver)paramVarArgs[(paramVarArgs.length - 1)]).onRemoveCompleted(null, true);
      }
      return Integer.valueOf(0);
    }
    
    public String getMethodName()
    {
      return "freeStorageAndNotify";
    }
  }
  
  static class GetActivityInfo
    extends MethodProxy
  {
    GetActivityInfo() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      Object localObject = (ComponentName)paramVarArgs[0];
      if (getHostPkg().equals(((ComponentName)localObject).getPackageName())) {
        localObject = paramMethod.invoke(paramObject, paramVarArgs);
      }
      do
      {
        ActivityInfo localActivityInfo;
        do
        {
          return localObject;
          int i = VUserHandle.myUserId();
          int j = ((Integer)paramVarArgs[1]).intValue();
          localActivityInfo = VPackageManager.get().getActivityInfo((ComponentName)localObject, j, i);
          localObject = localActivityInfo;
        } while (localActivityInfo != null);
        paramObject = (ActivityInfo)paramMethod.invoke(paramObject, paramVarArgs);
        if (paramObject == null) {
          break;
        }
        localObject = paramObject;
      } while (isVisiblePackage(paramObject.applicationInfo));
      return null;
    }
    
    public String getMethodName()
    {
      return "getActivityInfo";
    }
    
    public boolean isEnable()
    {
      return isAppProcess();
    }
  }
  
  static class GetApplicationBlockedSettingAsUser
    extends MethodProxy
  {
    GetApplicationBlockedSettingAsUser() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      MethodParameterUtils.replaceFirstAppPkg(paramVarArgs);
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String getMethodName()
    {
      return "getApplicationBlockedSettingAsUser";
    }
  }
  
  static class GetApplicationEnabledSetting
    extends MethodProxy
  {
    GetApplicationEnabledSetting() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      MethodParameterUtils.replaceFirstAppPkg(paramVarArgs);
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String getMethodName()
    {
      return "getApplicationEnabledSetting";
    }
  }
  
  static class GetApplicationInfo
    extends MethodProxy
  {
    GetApplicationInfo() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      Object localObject = (String)paramVarArgs[0];
      int i = ((Integer)paramVarArgs[1]).intValue();
      if (getHostPkg().equals(localObject)) {
        paramObject = paramMethod.invoke(paramObject, paramVarArgs);
      }
      do
      {
        do
        {
          return paramObject;
          int j = VUserHandle.myUserId();
          localObject = VPackageManager.get().getApplicationInfo((String)localObject, i, j);
          if (localObject == null) {
            break;
          }
          paramObject = localObject;
        } while (VClient.get().getVUid() > 0);
        ((ApplicationInfo)localObject).uid = getRealUid();
        return localObject;
        paramMethod = (ApplicationInfo)paramMethod.invoke(paramObject, paramVarArgs);
        if (paramMethod == null) {
          break;
        }
        paramObject = paramMethod;
      } while (isVisiblePackage(paramMethod));
      return null;
    }
    
    public String getMethodName()
    {
      return "getApplicationInfo";
    }
    
    public boolean isEnable()
    {
      return isAppProcess();
    }
  }
  
  static class GetComponentEnabledSetting
    extends MethodProxy
  {
    GetComponentEnabledSetting() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      if ((ComponentName)paramVarArgs[0] != null) {
        return Integer.valueOf(1);
      }
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String getMethodName()
    {
      return "getComponentEnabledSetting";
    }
  }
  
  static class GetInstalledApplications
    extends MethodProxy
  {
    GetInstalledApplications() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      int i = ((Integer)paramVarArgs[0]).intValue();
      int j = VUserHandle.myUserId();
      paramVarArgs = VPackageManager.get().getInstalledApplications(i, j);
      paramObject = paramVarArgs;
      if (ParceledListSliceCompat.isReturnParceledListSlice(paramMethod)) {
        paramObject = ParceledListSliceCompat.create(paramVarArgs);
      }
      return paramObject;
    }
    
    public String getMethodName()
    {
      return "getInstalledApplications";
    }
  }
  
  static class GetInstalledPackages
    extends MethodProxy
  {
    GetInstalledPackages() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      int i = ((Integer)paramVarArgs[0]).intValue();
      int j = VUserHandle.myUserId();
      if (isAppProcess()) {}
      for (paramObject = new ArrayList(VirtualCore.get().getInstalledAppCount());; paramObject = VirtualCore.get().getUnHookPackageManager().getInstalledPackages(i))
      {
        paramObject.addAll(VPackageManager.get().getInstalledPackages(i, j));
        paramVarArgs = paramObject;
        if (ParceledListSliceCompat.isReturnParceledListSlice(paramMethod)) {
          paramVarArgs = ParceledListSliceCompat.create(paramObject);
        }
        return paramVarArgs;
      }
    }
    
    public String getMethodName()
    {
      return "getInstalledPackages";
    }
  }
  
  static class GetInstallerPackageName
    extends MethodProxy
  {
    GetInstallerPackageName() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      return "com.android.vending";
    }
    
    public String getMethodName()
    {
      return "getInstallerPackageName";
    }
    
    public boolean isEnable()
    {
      return isAppProcess();
    }
  }
  
  static class GetPackageGids
    extends MethodProxy
  {
    GetPackageGids() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      MethodParameterUtils.replaceFirstAppPkg(paramVarArgs);
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String getMethodName()
    {
      return "getPackageGids";
    }
    
    public boolean isEnable()
    {
      return isAppProcess();
    }
  }
  
  static class GetPackageGidsEtc
    extends MethodProxies.GetPackageGids
  {
    GetPackageGidsEtc() {}
    
    public String getMethodName()
    {
      return super.getMethodName() + "Etc";
    }
  }
  
  static final class GetPackageInfo
    extends MethodProxy
  {
    private static final int MATCH_FACTORY_ONLY = 2097152;
    
    GetPackageInfo() {}
    
    public final boolean beforeCall(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (paramVarArgs != null)
      {
        bool1 = bool2;
        if (paramVarArgs[0] != null) {
          bool1 = true;
        }
      }
      return bool1;
    }
    
    public final Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      Object localObject = (String)paramVarArgs[0];
      int i = ((Integer)paramVarArgs[1]).intValue();
      int j = VUserHandle.myUserId();
      if ((0x200000 & i) != 0) {
        localObject = paramMethod.invoke(paramObject, paramVarArgs);
      }
      do
      {
        PackageInfo localPackageInfo;
        do
        {
          return localObject;
          localPackageInfo = VPackageManager.get().getPackageInfo((String)localObject, i, j);
          localObject = localPackageInfo;
        } while (localPackageInfo != null);
        paramObject = (PackageInfo)paramMethod.invoke(paramObject, paramVarArgs);
        if (paramObject == null) {
          break;
        }
        localObject = paramObject;
      } while (isVisiblePackage(paramObject.applicationInfo));
      return null;
    }
    
    public final String getMethodName()
    {
      return "getPackageInfo";
    }
  }
  
  static class GetPackageInstaller
    extends MethodProxy
  {
    GetPackageInstaller() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      paramObject = (IInterface)paramMethod.invoke(paramObject, paramVarArgs);
      paramMethod = VPackageManager.get().getPackageInstaller();
      return Proxy.newProxyInstance(paramObject.getClass().getClassLoader(), paramObject.getClass().getInterfaces(), new MethodProxies.GetPackageInstaller.1(this, paramMethod));
    }
    
    public String getMethodName()
    {
      return "getPackageInstaller";
    }
    
    public boolean isEnable()
    {
      return isAppProcess();
    }
  }
  
  static class GetPackageUid
    extends MethodProxy
  {
    GetPackageUid() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      String str = (String)paramVarArgs[0];
      if (str.equals(getHostPkg())) {
        return paramMethod.invoke(paramObject, paramVarArgs);
      }
      return Integer.valueOf(VUserHandle.getAppId(VPackageManager.get().getPackageUid(str, 0)));
    }
    
    public String getMethodName()
    {
      return "getPackageUid";
    }
    
    public boolean isEnable()
    {
      return isAppProcess();
    }
  }
  
  static class GetPackageUidEtc
    extends MethodProxies.GetPackageUid
  {
    GetPackageUidEtc() {}
    
    public String getMethodName()
    {
      return super.getMethodName() + "Etc";
    }
  }
  
  static class GetPackagesForUid
    extends MethodProxy
  {
    GetPackagesForUid() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      int j = ((Integer)paramVarArgs[0]).intValue();
      int k = Binder.getCallingUid();
      int i = j;
      if (j == VirtualCore.get().myUid()) {
        i = getBaseVUid();
      }
      paramObject = VPackageManager.get().getPackagesForUid(k);
      paramMethod = VPackageManager.get().getPackagesForUid(i);
      paramVarArgs = VPackageManager.get().getPackagesForUid(Process.myUid());
      ArraySet localArraySet = new ArraySet(2);
      if ((paramObject != null) && (paramObject.length > 0)) {
        localArraySet.addAll(Arrays.asList(paramObject));
      }
      if ((paramMethod != null) && (paramMethod.length > 0)) {
        localArraySet.addAll(Arrays.asList(paramMethod));
      }
      if ((paramVarArgs != null) && (paramVarArgs.length > 0)) {
        localArraySet.addAll(Arrays.asList(paramVarArgs));
      }
      return localArraySet.toArray(new String[localArraySet.size()]);
    }
    
    public String getMethodName()
    {
      return "getPackagesForUid";
    }
    
    public boolean isEnable()
    {
      return isAppProcess();
    }
  }
  
  @TargetApi(17)
  static class GetPermissionFlags
    extends MethodProxy
  {
    GetPermissionFlags() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String getMethodName()
    {
      return "getPermissionFlags";
    }
  }
  
  static class GetPermissionGroupInfo
    extends MethodProxy
  {
    GetPermissionGroupInfo() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      Object localObject = (String)paramVarArgs[0];
      int i = ((Integer)paramVarArgs[1]).intValue();
      localObject = VPackageManager.get().getPermissionGroupInfo((String)localObject, i);
      if (localObject != null) {
        return localObject;
      }
      return super.call(paramObject, paramMethod, paramVarArgs);
    }
    
    public String getMethodName()
    {
      return "getPermissionGroupInfo";
    }
    
    public boolean isEnable()
    {
      return isAppProcess();
    }
  }
  
  static class GetPermissions
    extends MethodProxy
  {
    GetPermissions() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String getMethodName()
    {
      return "getPermissions";
    }
  }
  
  static class GetPreferredActivities
    extends MethodProxy
  {
    GetPreferredActivities() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      MethodParameterUtils.replaceLastAppPkg(paramVarArgs);
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String getMethodName()
    {
      return "getPreferredActivities";
    }
  }
  
  static class GetProviderInfo
    extends MethodProxy
  {
    GetProviderInfo() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      Object localObject = (ComponentName)paramVarArgs[0];
      int i = ((Integer)paramVarArgs[1]).intValue();
      if (getHostPkg().equals(((ComponentName)localObject).getPackageName())) {
        localObject = paramMethod.invoke(paramObject, paramVarArgs);
      }
      do
      {
        ProviderInfo localProviderInfo;
        do
        {
          return localObject;
          int j = VUserHandle.myUserId();
          localProviderInfo = VPackageManager.get().getProviderInfo((ComponentName)localObject, i, j);
          localObject = localProviderInfo;
        } while (localProviderInfo != null);
        paramObject = (ProviderInfo)paramMethod.invoke(paramObject, paramVarArgs);
        if (paramObject == null) {
          break;
        }
        localObject = paramObject;
      } while (isVisiblePackage(paramObject.applicationInfo));
      return null;
    }
    
    public String getMethodName()
    {
      return "getProviderInfo";
    }
  }
  
  static class GetReceiverInfo
    extends MethodProxy
  {
    GetReceiverInfo() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      Object localObject = (ComponentName)paramVarArgs[0];
      if (getHostPkg().equals(((ComponentName)localObject).getPackageName())) {
        localObject = paramMethod.invoke(paramObject, paramVarArgs);
      }
      do
      {
        ActivityInfo localActivityInfo;
        do
        {
          return localObject;
          int i = ((Integer)paramVarArgs[1]).intValue();
          localActivityInfo = VPackageManager.get().getReceiverInfo((ComponentName)localObject, i, 0);
          localObject = localActivityInfo;
        } while (localActivityInfo != null);
        paramObject = (ActivityInfo)paramMethod.invoke(paramObject, paramVarArgs);
        if (paramObject == null) {
          break;
        }
        localObject = paramObject;
      } while (isVisiblePackage(paramObject.applicationInfo));
      return null;
    }
    
    public String getMethodName()
    {
      return "getReceiverInfo";
    }
    
    public boolean isEnable()
    {
      return isAppProcess();
    }
  }
  
  static class GetServiceInfo
    extends MethodProxy
  {
    GetServiceInfo() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      Object localObject = (ComponentName)paramVarArgs[0];
      int i = ((Integer)paramVarArgs[1]).intValue();
      int j = VUserHandle.myUserId();
      localObject = VPackageManager.get().getServiceInfo((ComponentName)localObject, i, j);
      if (localObject != null) {
        paramObject = localObject;
      }
      do
      {
        return paramObject;
        paramMethod = (ServiceInfo)paramMethod.invoke(paramObject, paramVarArgs);
        if (paramMethod == null) {
          break;
        }
        paramObject = paramMethod;
      } while (isVisiblePackage(paramMethod.applicationInfo));
      return null;
    }
    
    public String getMethodName()
    {
      return "getServiceInfo";
    }
    
    public boolean isEnable()
    {
      return isAppProcess();
    }
  }
  
  static class IsPackageAvailable
    extends MethodProxy
  {
    IsPackageAvailable() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      if (isAppPkg((String)paramVarArgs[0])) {
        return Boolean.valueOf(true);
      }
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String getMethodName()
    {
      return "isPackageAvailable";
    }
    
    public boolean isEnable()
    {
      return isAppProcess();
    }
  }
  
  static class IsPackageForzen
    extends MethodProxy
  {
    IsPackageForzen() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      return Boolean.valueOf(false);
    }
    
    public String getMethodName()
    {
      return "isPackageForzen";
    }
    
    public boolean isEnable()
    {
      return isAppProcess();
    }
  }
  
  static class QueryContentProviders
    extends MethodProxy
  {
    QueryContentProviders() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      paramObject = (String)paramVarArgs[0];
      int i = ((Integer)paramVarArgs[2]).intValue();
      paramVarArgs = VPackageManager.get().queryContentProviders(paramObject, i, 0);
      paramObject = paramVarArgs;
      if (ParceledListSliceCompat.isReturnParceledListSlice(paramMethod)) {
        paramObject = ParceledListSliceCompat.create(paramVarArgs);
      }
      return paramObject;
    }
    
    public String getMethodName()
    {
      return "queryContentProviders";
    }
  }
  
  static class QueryIntentActivities
    extends MethodProxy
  {
    QueryIntentActivities() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      boolean bool = ParceledListSliceCompat.isReturnParceledListSlice(paramMethod);
      int i = VUserHandle.myUserId();
      List localList = VPackageManager.get().queryIntentActivities((Intent)paramVarArgs[0], (String)paramVarArgs[1], ((Integer)paramVarArgs[2]).intValue(), i);
      paramObject = paramMethod.invoke(paramObject, paramVarArgs);
      if (paramObject != null)
      {
        if (bool) {
          paramObject = (List)ParceledListSlice.getList.call(paramObject, new Object[0]);
        }
        while (paramObject != null)
        {
          paramMethod = paramObject.iterator();
          for (;;)
          {
            if (paramMethod.hasNext())
            {
              paramVarArgs = (ResolveInfo)paramMethod.next();
              if ((paramVarArgs == null) || (paramVarArgs.activityInfo == null) || (!isVisiblePackage(paramVarArgs.activityInfo.applicationInfo)))
              {
                paramMethod.remove();
                continue;
                paramObject = (List)paramObject;
                break;
              }
            }
          }
          localList.addAll(paramObject);
        }
      }
      if (bool) {
        return ParceledListSliceCompat.create(localList);
      }
      return localList;
    }
    
    public String getMethodName()
    {
      return "queryIntentActivities";
    }
    
    public boolean isEnable()
    {
      return isAppProcess();
    }
  }
  
  @TargetApi(19)
  static class QueryIntentContentProviders
    extends MethodProxy
  {
    QueryIntentContentProviders() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      boolean bool = ParceledListSliceCompat.isReturnParceledListSlice(paramMethod);
      int i = VUserHandle.myUserId();
      List localList = VPackageManager.get().queryIntentContentProviders((Intent)paramVarArgs[0], (String)paramVarArgs[1], ((Integer)paramVarArgs[2]).intValue(), i);
      paramObject = paramMethod.invoke(paramObject, paramVarArgs);
      if (bool) {
        paramObject = (List)ParceledListSlice.getList.call(paramObject, new Object[0]);
      }
      while (paramObject != null)
      {
        paramVarArgs = paramObject.iterator();
        for (;;)
        {
          if (paramVarArgs.hasNext())
          {
            ResolveInfo localResolveInfo = (ResolveInfo)paramVarArgs.next();
            if ((localResolveInfo == null) || (localResolveInfo.providerInfo == null) || (!isVisiblePackage(localResolveInfo.providerInfo.applicationInfo)))
            {
              paramVarArgs.remove();
              continue;
              paramObject = (List)paramObject;
              break;
            }
          }
        }
        localList.addAll(paramObject);
      }
      if (ParceledListSliceCompat.isReturnParceledListSlice(paramMethod)) {
        return ParceledListSliceCompat.create(localList);
      }
      return localList;
    }
    
    public String getMethodName()
    {
      return "queryIntentContentProviders";
    }
    
    public boolean isEnable()
    {
      return isAppProcess();
    }
  }
  
  static class QueryIntentReceivers
    extends MethodProxy
  {
    QueryIntentReceivers() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      boolean bool = ParceledListSliceCompat.isReturnParceledListSlice(paramMethod);
      int i = VUserHandle.myUserId();
      List localList = VPackageManager.get().queryIntentReceivers((Intent)paramVarArgs[0], (String)paramVarArgs[1], ((Integer)paramVarArgs[2]).intValue(), i);
      paramObject = paramMethod.invoke(paramObject, paramVarArgs);
      if (bool) {
        paramObject = (List)ParceledListSlice.getList.call(paramObject, new Object[0]);
      }
      while (paramObject != null)
      {
        paramMethod = paramObject.iterator();
        for (;;)
        {
          if (paramMethod.hasNext())
          {
            paramVarArgs = (ResolveInfo)paramMethod.next();
            if ((paramVarArgs == null) || (paramVarArgs.activityInfo == null) || (!isVisiblePackage(paramVarArgs.activityInfo.applicationInfo)))
            {
              paramMethod.remove();
              continue;
              paramObject = (List)paramObject;
              break;
            }
          }
        }
        localList.addAll(paramObject);
      }
      if (bool) {
        return ParceledListSliceCompat.create(localList);
      }
      return localList;
    }
    
    public String getMethodName()
    {
      return "queryIntentReceivers";
    }
  }
  
  static class QueryIntentServices
    extends MethodProxy
  {
    QueryIntentServices() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      boolean bool = ParceledListSliceCompat.isReturnParceledListSlice(paramMethod);
      int i = VUserHandle.myUserId();
      List localList = VPackageManager.get().queryIntentServices((Intent)paramVarArgs[0], (String)paramVarArgs[1], ((Integer)paramVarArgs[2]).intValue(), i);
      paramObject = paramMethod.invoke(paramObject, paramVarArgs);
      if (paramObject != null)
      {
        if (bool) {
          paramObject = (List)ParceledListSlice.getList.call(paramObject, new Object[0]);
        }
        while (paramObject != null)
        {
          paramMethod = paramObject.iterator();
          for (;;)
          {
            if (paramMethod.hasNext())
            {
              paramVarArgs = (ResolveInfo)paramMethod.next();
              if ((paramVarArgs == null) || (paramVarArgs.serviceInfo == null) || (!isVisiblePackage(paramVarArgs.serviceInfo.applicationInfo)))
              {
                paramMethod.remove();
                continue;
                paramObject = (List)paramObject;
                break;
              }
            }
          }
          localList.addAll(paramObject);
        }
      }
      if (bool) {
        return ParceledListSliceCompat.create(localList);
      }
      return localList;
    }
    
    public String getMethodName()
    {
      return "queryIntentServices";
    }
    
    public boolean isEnable()
    {
      return isAppProcess();
    }
  }
  
  static class RemovePackageFromPreferred
    extends MethodProxy
  {
    RemovePackageFromPreferred() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      MethodParameterUtils.replaceFirstAppPkg(paramVarArgs);
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String getMethodName()
    {
      return "removePackageFromPreferred";
    }
  }
  
  static class ResolveContentProvider
    extends MethodProxy
  {
    ResolveContentProvider() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      Object localObject = (String)paramVarArgs[0];
      int i = ((Integer)paramVarArgs[1]).intValue();
      int j = VUserHandle.myUserId();
      ProviderInfo localProviderInfo = VPackageManager.get().resolveContentProvider((String)localObject, i, j);
      localObject = localProviderInfo;
      if (localProviderInfo == null)
      {
        paramObject = (ProviderInfo)paramMethod.invoke(paramObject, paramVarArgs);
        localObject = paramObject;
        if (paramObject != null)
        {
          localObject = paramObject;
          if (!isVisiblePackage(paramObject.applicationInfo)) {}
        }
      }
      return localObject;
    }
    
    public String getMethodName()
    {
      return "resolveContentProvider";
    }
  }
  
  static class ResolveIntent
    extends MethodProxy
  {
    ResolveIntent() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      Object localObject1 = (Intent)paramVarArgs[0];
      Object localObject2 = (String)paramVarArgs[1];
      int i = ((Integer)paramVarArgs[2]).intValue();
      int j = VUserHandle.myUserId();
      localObject2 = VPackageManager.get().resolveIntent((Intent)localObject1, (String)localObject2, i, j);
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = (ResolveInfo)paramMethod.invoke(paramObject, paramVarArgs);
      }
      return localObject1;
    }
    
    public String getMethodName()
    {
      return "resolveIntent";
    }
  }
  
  static class ResolveService
    extends MethodProxy
  {
    ResolveService() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      Object localObject1 = (Intent)paramVarArgs[0];
      Object localObject2 = (String)paramVarArgs[1];
      int i = ((Integer)paramVarArgs[2]).intValue();
      int j = VUserHandle.myUserId();
      localObject2 = VPackageManager.get().resolveService((Intent)localObject1, (String)localObject2, i, j);
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = (ResolveInfo)paramMethod.invoke(paramObject, paramVarArgs);
      }
      return localObject1;
    }
    
    public String getMethodName()
    {
      return "resolveService";
    }
  }
  
  static class RevokeRuntimePermission
    extends MethodProxy
  {
    RevokeRuntimePermission() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      MethodParameterUtils.replaceFirstAppPkg(paramVarArgs);
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String getMethodName()
    {
      return "revokeRuntimePermission";
    }
    
    public boolean isEnable()
    {
      return isAppProcess();
    }
  }
  
  static class SetApplicationBlockedSettingAsUser
    extends MethodProxy
  {
    SetApplicationBlockedSettingAsUser() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      MethodParameterUtils.replaceFirstAppPkg(paramVarArgs);
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String getMethodName()
    {
      return "setApplicationBlockedSettingAsUser";
    }
    
    public boolean isEnable()
    {
      return isAppProcess();
    }
  }
  
  static class SetApplicationEnabledSetting
    extends MethodProxy
  {
    SetApplicationEnabledSetting() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      MethodParameterUtils.replaceFirstAppPkg(paramVarArgs);
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String getMethodName()
    {
      return "setApplicationEnabledSetting";
    }
    
    public boolean isEnable()
    {
      return isAppProcess();
    }
  }
  
  static class SetComponentEnabledSetting
    extends MethodProxy
  {
    SetComponentEnabledSetting() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      return Integer.valueOf(0);
    }
    
    public String getMethodName()
    {
      return "setComponentEnabledSetting";
    }
    
    public boolean isEnable()
    {
      return isAppProcess();
    }
  }
  
  static class SetPackageStoppedState
    extends MethodProxy
  {
    SetPackageStoppedState() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      MethodParameterUtils.replaceFirstAppPkg(paramVarArgs);
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String getMethodName()
    {
      return "setPackageStoppedState";
    }
    
    public boolean isEnable()
    {
      return isAppProcess();
    }
  }
  
  static class checkUidSignatures
    extends MethodProxy
  {
    checkUidSignatures() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      int i = ((Integer)paramVarArgs[0]).intValue();
      int j = ((Integer)paramVarArgs[1]).intValue();
      if (i == j) {
        return Integer.valueOf(0);
      }
      paramObject = VPackageManager.get().getPackagesForUid(i);
      paramMethod = VPackageManager.get().getPackagesForUid(j);
      if ((paramObject == null) || (paramObject.length == 0)) {
        return Integer.valueOf(-4);
      }
      if ((paramMethod == null) || (paramMethod.length == 0)) {
        return Integer.valueOf(-4);
      }
      return Integer.valueOf(VPackageManager.get().checkSignatures(paramObject[0], paramMethod[0]));
    }
    
    public String getMethodName()
    {
      return "checkUidSignatures";
    }
  }
  
  static class getNameForUid
    extends MethodProxy
  {
    getNameForUid() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      int i = ((Integer)paramVarArgs[0]).intValue();
      return VPackageManager.get().getNameForUid(i);
    }
    
    public String getMethodName()
    {
      return "getNameForUid";
    }
  }
}
