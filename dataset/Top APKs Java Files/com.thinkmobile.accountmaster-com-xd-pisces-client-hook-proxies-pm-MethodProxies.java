package com.xd.pisces.client.hook.proxies.pm;

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
import com.xd.pisces.client.core.Pisces;
import com.xd.pisces.client.hook.base.MethodProxy;
import com.xd.pisces.client.hook.utils.MethodParameterUtils;
import com.xd.pisces.client.ipc.VPackageManager;
import com.xd.pisces.helper.collection.ArraySet;
import com.xd.pisces.helper.compat.ParceledListSliceCompat;
import com.xd.pisces.helper.utils.ArrayUtils;
import com.xd.pisces.os.VUserHandle;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import ref.RefMethod;
import ref.android.content.pm.ParceledListSlice;

class MethodProxies
{
  MethodProxies() {}
  
  static class ActivitySupportsIntent
    extends MethodProxy
  {
    ActivitySupportsIntent() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
      throws Throwable
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
      throws Throwable
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
      throws Throwable
    {
      return super.afterCall(paramObject1, paramMethod, paramArrayOfObject, paramObject2);
    }
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
      throws Throwable
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
      throws Throwable
    {
      Object localObject1;
      Object localObject3;
      Object localObject2;
      if ((paramVarArgs.length == 2) && ((paramVarArgs[0] instanceof String)) && ((paramVarArgs[1] instanceof String)))
      {
        localObject1 = Pisces.getPM();
        localObject3 = (String)paramVarArgs[0];
        localObject2 = (String)paramVarArgs[1];
      }
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
      catch (Throwable localThrowable)
      {
        for (;;) {}
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
      throws Throwable
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
      throws Throwable
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
      throws Throwable
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
      throws Throwable
    {
      paramObject = (String)paramVarArgs[0];
      try
      {
        Pisces.get().uninstallPackage(paramObject);
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
      throws Throwable
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
      throws Throwable
    {
      Object localObject1 = (ComponentName)paramVarArgs[0];
      boolean bool = getHostPkg().equals(((ComponentName)localObject1).getPackageName());
      Object localObject2 = null;
      if (bool) {
        return paramMethod.invoke(paramObject, paramVarArgs);
      }
      int i = VUserHandle.myUserId();
      int j = ((Integer)paramVarArgs[1]).intValue();
      ActivityInfo localActivityInfo = VPackageManager.get().getActivityInfo((ComponentName)localObject1, j, i);
      localObject1 = localActivityInfo;
      if (localActivityInfo == null)
      {
        paramMethod = (ActivityInfo)paramMethod.invoke(paramObject, paramVarArgs);
        paramObject = localObject2;
        if (paramMethod == null) {
          return paramObject;
        }
        localObject1 = paramMethod;
        if (!isVisiblePackage(paramMethod.applicationInfo)) {
          return null;
        }
      }
      paramObject = localObject1;
      return paramObject;
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
      throws Throwable
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
      throws Throwable
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
      throws Throwable
    {
      Object localObject2 = (String)paramVarArgs[0];
      int i = ((Integer)paramVarArgs[1]).intValue();
      boolean bool = getHostPkg().equals(localObject2);
      Object localObject1 = null;
      if (bool) {
        paramObject = paramMethod.invoke(paramObject, paramVarArgs);
      }
      for (;;)
      {
        return paramObject;
        int j = VUserHandle.myUserId();
        localObject2 = VPackageManager.get().getApplicationInfo((String)localObject2, i, j);
        if (localObject2 != null) {
          return localObject2;
        }
        paramMethod = (ApplicationInfo)paramMethod.invoke(paramObject, paramVarArgs);
        paramObject = localObject1;
        if (paramMethod != null)
        {
          if (isVisiblePackage(paramMethod)) {
            break;
          }
          paramObject = localObject1;
        }
      }
      return paramMethod;
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
      throws Throwable
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
      throws Throwable
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
      throws Throwable
    {
      int i = ((Integer)paramVarArgs[0]).intValue();
      int j = VUserHandle.myUserId();
      if (isAppProcess()) {
        paramObject = new ArrayList(Pisces.get().getInstalledAppCount());
      } else {
        paramObject = Pisces.get().getUnHookPackageManager().getInstalledPackages(i);
      }
      paramObject.addAll(VPackageManager.get().getInstalledPackages(i, j));
      paramVarArgs = paramObject;
      if (ParceledListSliceCompat.isReturnParceledListSlice(paramMethod)) {
        paramVarArgs = ParceledListSliceCompat.create(paramObject);
      }
      return paramVarArgs;
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
      throws Throwable
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
      throws Throwable
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
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(super.getMethodName());
      localStringBuilder.append("Etc");
      return localStringBuilder.toString();
    }
  }
  
  static final class GetPackageInfo
    extends MethodProxy
  {
    GetPackageInfo() {}
    
    public boolean beforeCall(Object paramObject, Method paramMethod, Object... paramVarArgs)
    {
      return (paramVarArgs != null) && (paramVarArgs[0] != null);
    }
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
      throws Throwable
    {
      Object localObject = (String)paramVarArgs[0];
      int i = ((Integer)paramVarArgs[1]).intValue();
      int j = VUserHandle.myUserId();
      localObject = VPackageManager.get().getPackageInfo((String)localObject, i, j);
      if (localObject != null) {
        return localObject;
      }
      paramObject = (PackageInfo)paramMethod.invoke(paramObject, paramVarArgs);
      if ((paramObject != null) && (isVisiblePackage(paramObject.applicationInfo))) {
        return paramObject;
      }
      return null;
    }
    
    public String getMethodName()
    {
      return "getPackageInfo";
    }
  }
  
  static class GetPackageInstaller
    extends MethodProxy
  {
    GetPackageInstaller() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
      throws Throwable
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
      throws Throwable
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
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(super.getMethodName());
      localStringBuilder.append("Etc");
      return localStringBuilder.toString();
    }
  }
  
  static class GetPackagesForUid
    extends MethodProxy
  {
    GetPackagesForUid() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
      throws Throwable
    {
      int j = ((Integer)paramVarArgs[0]).intValue();
      int k = Binder.getCallingUid();
      int i = j;
      if (j == Pisces.get().myUid()) {
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
      throws Throwable
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
      throws Throwable
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
      throws Throwable
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
      throws Throwable
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
      throws Throwable
    {
      Object localObject1 = (ComponentName)paramVarArgs[0];
      int i = ((Integer)paramVarArgs[1]).intValue();
      boolean bool = getHostPkg().equals(((ComponentName)localObject1).getPackageName());
      Object localObject2 = null;
      if (bool) {
        return paramMethod.invoke(paramObject, paramVarArgs);
      }
      int j = VUserHandle.myUserId();
      ProviderInfo localProviderInfo = VPackageManager.get().getProviderInfo((ComponentName)localObject1, i, j);
      localObject1 = localProviderInfo;
      if (localProviderInfo == null)
      {
        paramMethod = (ProviderInfo)paramMethod.invoke(paramObject, paramVarArgs);
        paramObject = localObject2;
        if (paramMethod == null) {
          return paramObject;
        }
        localObject1 = paramMethod;
        if (!isVisiblePackage(paramMethod.applicationInfo)) {
          return null;
        }
      }
      paramObject = localObject1;
      return paramObject;
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
      throws Throwable
    {
      Object localObject1 = (ComponentName)paramVarArgs[0];
      boolean bool = getHostPkg().equals(((ComponentName)localObject1).getPackageName());
      Object localObject2 = null;
      if (bool) {
        return paramMethod.invoke(paramObject, paramVarArgs);
      }
      int i = ((Integer)paramVarArgs[1]).intValue();
      ActivityInfo localActivityInfo = VPackageManager.get().getReceiverInfo((ComponentName)localObject1, i, 0);
      localObject1 = localActivityInfo;
      if (localActivityInfo == null)
      {
        paramMethod = (ActivityInfo)paramMethod.invoke(paramObject, paramVarArgs);
        paramObject = localObject2;
        if (paramMethod == null) {
          return paramObject;
        }
        localObject1 = paramMethod;
        if (!isVisiblePackage(paramMethod.applicationInfo)) {
          return null;
        }
      }
      paramObject = localObject1;
      return paramObject;
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
      throws Throwable
    {
      Object localObject = (ComponentName)paramVarArgs[0];
      int i = ((Integer)paramVarArgs[1]).intValue();
      int j = VUserHandle.myUserId();
      localObject = VPackageManager.get().getServiceInfo((ComponentName)localObject, i, j);
      if (localObject != null) {
        return localObject;
      }
      paramMethod = (ServiceInfo)paramMethod.invoke(paramObject, paramVarArgs);
      if (paramMethod != null)
      {
        paramObject = paramMethod;
        if (isVisiblePackage(paramMethod.applicationInfo)) {}
      }
      else
      {
        paramObject = null;
      }
      return paramObject;
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
      throws Throwable
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
      throws Throwable
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
      throws Throwable
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
      throws Throwable
    {
      boolean bool = ParceledListSliceCompat.isReturnParceledListSlice(paramMethod);
      int i = VUserHandle.myUserId();
      List localList = VPackageManager.get().queryIntentActivities((Intent)paramVarArgs[0], (String)paramVarArgs[1], ((Integer)paramVarArgs[2]).intValue(), i);
      paramMethod = paramMethod.invoke(paramObject, paramVarArgs);
      if (paramMethod != null)
      {
        paramObject = paramMethod;
        if (bool) {
          paramObject = ParceledListSlice.getList.call(paramMethod, new Object[0]);
        }
        paramObject = (List)paramObject;
        if (paramObject != null)
        {
          paramMethod = paramObject.iterator();
          while (paramMethod.hasNext())
          {
            paramVarArgs = (ResolveInfo)paramMethod.next();
            if ((paramVarArgs == null) || (paramVarArgs.activityInfo == null) || (!isVisiblePackage(paramVarArgs.activityInfo.applicationInfo))) {
              paramMethod.remove();
            }
          }
          localList.addAll(paramObject);
        }
      }
      paramObject = localList;
      if (bool) {
        paramObject = ParceledListSliceCompat.create(localList);
      }
      return paramObject;
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
      throws Throwable
    {
      boolean bool = ParceledListSliceCompat.isReturnParceledListSlice(paramMethod);
      int i = VUserHandle.myUserId();
      List localList = VPackageManager.get().queryIntentContentProviders((Intent)paramVarArgs[0], (String)paramVarArgs[1], ((Integer)paramVarArgs[2]).intValue(), i);
      paramObject = paramMethod.invoke(paramObject, paramVarArgs);
      if (bool) {
        paramObject = (List)ParceledListSlice.getList.call(paramObject, new Object[0]);
      } else {
        paramObject = (List)paramObject;
      }
      if (paramObject != null)
      {
        paramVarArgs = paramObject.iterator();
        while (paramVarArgs.hasNext())
        {
          ResolveInfo localResolveInfo = (ResolveInfo)paramVarArgs.next();
          if ((localResolveInfo == null) || (localResolveInfo.providerInfo == null) || (!isVisiblePackage(localResolveInfo.providerInfo.applicationInfo))) {
            paramVarArgs.remove();
          }
        }
        localList.addAll(paramObject);
      }
      paramObject = localList;
      if (ParceledListSliceCompat.isReturnParceledListSlice(paramMethod)) {
        paramObject = ParceledListSliceCompat.create(localList);
      }
      return paramObject;
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
      throws Throwable
    {
      boolean bool = ParceledListSliceCompat.isReturnParceledListSlice(paramMethod);
      int i = VUserHandle.myUserId();
      List localList = VPackageManager.get().queryIntentReceivers((Intent)paramVarArgs[0], (String)paramVarArgs[1], ((Integer)paramVarArgs[2]).intValue(), i);
      paramMethod = paramMethod.invoke(paramObject, paramVarArgs);
      paramObject = paramMethod;
      if (bool) {
        paramObject = ParceledListSlice.getList.call(paramMethod, new Object[0]);
      }
      paramObject = (List)paramObject;
      if (paramObject != null)
      {
        paramMethod = paramObject.iterator();
        while (paramMethod.hasNext())
        {
          paramVarArgs = (ResolveInfo)paramMethod.next();
          if ((paramVarArgs == null) || (paramVarArgs.activityInfo == null) || (!isVisiblePackage(paramVarArgs.activityInfo.applicationInfo))) {
            paramMethod.remove();
          }
        }
        localList.addAll(paramObject);
      }
      paramObject = localList;
      if (bool) {
        paramObject = ParceledListSliceCompat.create(localList);
      }
      return paramObject;
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
      throws Throwable
    {
      boolean bool = ParceledListSliceCompat.isReturnParceledListSlice(paramMethod);
      int i = VUserHandle.myUserId();
      List localList = VPackageManager.get().queryIntentServices((Intent)paramVarArgs[0], (String)paramVarArgs[1], ((Integer)paramVarArgs[2]).intValue(), i);
      paramMethod = paramMethod.invoke(paramObject, paramVarArgs);
      if (paramMethod != null)
      {
        paramObject = paramMethod;
        if (bool) {
          paramObject = ParceledListSlice.getList.call(paramMethod, new Object[0]);
        }
        paramObject = (List)paramObject;
        if (paramObject != null)
        {
          paramMethod = paramObject.iterator();
          while (paramMethod.hasNext())
          {
            paramVarArgs = (ResolveInfo)paramMethod.next();
            if ((paramVarArgs == null) || (paramVarArgs.serviceInfo == null) || (!isVisiblePackage(paramVarArgs.serviceInfo.applicationInfo))) {
              paramMethod.remove();
            }
          }
          localList.addAll(paramObject);
        }
      }
      paramObject = localList;
      if (bool) {
        paramObject = ParceledListSliceCompat.create(localList);
      }
      return paramObject;
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
      throws Throwable
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
      throws Throwable
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
          isVisiblePackage(paramObject.applicationInfo);
          localObject = paramObject;
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
      throws Throwable
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
      throws Throwable
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
      throws Throwable
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
      throws Throwable
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
      throws Throwable
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
      throws Throwable
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
      throws Throwable
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
      throws Throwable
    {
      ((Integer)paramVarArgs[0]).intValue();
      ((Integer)paramVarArgs[1]).intValue();
      return Integer.valueOf(0);
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
      throws Throwable
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
