package com.chaozhuo.superme.client.hk.proxies.pm;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDataObserver;
import android.content.pm.IPackageDeleteObserver2;
import android.content.pm.IPackageInstallerCallback;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller.SessionParams;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IInterface;
import android.os.Process;
import com.chaozhuo.superme.a.a.b;
import com.chaozhuo.superme.a.b.l;
import com.chaozhuo.superme.a.c.a;
import com.chaozhuo.superme.client.SupermeCore;
import com.chaozhuo.superme.client.e.j;
import com.chaozhuo.superme.client.hk.base.MethodProxy;
import com.chaozhuo.superme.client.hk.utils.MethodParameterUtils;
import com.chaozhuo.superme.os.VUserHandle;
import com.chaozhuo.superme.remote.VParceledListSlice;
import com.chaozhuo.superme.server.h;
import com.chaozhuo.superme.server.pm.installer.SessionInfo;
import com.chaozhuo.superme.server.pm.installer.SessionParams;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import ref_framework.RefMethod;
import ref_framework.RefStaticMethod;
import ref_framework.android.content.pm.ParceledListSlice;
import ref_framework.java.lang.reflect.Proxy;

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
      return Boolean.valueOf(j.a().a(paramObject, paramMethod, paramVarArgs));
    }
    
    public String getMethodName()
    {
      return " activitySupportsIntent";
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
      return " addPackageToPreferred";
    }
  }
  
  static class CanRequestPackageInstalls
    extends MethodProxy
  {
    CanRequestPackageInstalls() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
      throws Throwable
    {
      MethodParameterUtils.replaceFirstAppPkg(paramVarArgs);
      return paramMethod.invoke(paramObject, paramVarArgs);
    }
    
    public String getMethodName()
    {
      return " canRequestPackageInstalls";
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
      return Integer.valueOf(j.a().a(paramObject, paramMethod, i));
    }
    
    public String getMethodName()
    {
      return " checkPermission";
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
      if ((paramVarArgs.length == 2) && ((paramVarArgs[0] instanceof String)) && ((paramVarArgs[1] instanceof String)))
      {
        Object localObject1 = SupermeCore.b();
        Object localObject3 = (String)paramVarArgs[0];
        Object localObject2 = (String)paramVarArgs[1];
        try
        {
          localObject3 = ((PackageManager)localObject1).getPackageInfo((String)localObject3, 64);
          localObject1 = ((PackageManager)localObject1).getPackageInfo((String)localObject2, 64);
          localObject2 = ((PackageInfo)localObject3).signatures;
          localObject1 = ((PackageInfo)localObject1).signatures;
          if (a.a((Object[])localObject2))
          {
            if (!a.a((Object[])localObject1)) {
              return Integer.valueOf(-1);
            }
            return Integer.valueOf(1);
          }
          if (a.a((Object[])localObject1)) {
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
      return " checkSignatures";
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
      return " clearPackagePersistentPreferredActivities";
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
      return " clearPackagePreferredActivities";
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
      return " deleteApplicationCacheFiles";
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
        SupermeCore.a().g(paramObject);
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
      return " deletePackage";
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
      return " freeStorageAndNotify";
    }
  }
  
  static class GetActivityInfo
    extends MethodProxy
  {
    GetActivityInfo() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
      throws Throwable
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
          localActivityInfo = j.a().b((ComponentName)localObject, j, i);
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
      return " getActivityInfo";
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
      return " getApplicationBlockedSettingAsUser";
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
      return " getApplicationEnabledSetting";
    }
  }
  
  static class GetApplicationInfo
    extends MethodProxy
  {
    GetApplicationInfo() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
      throws Throwable
    {
      Object localObject = (String)paramVarArgs[0];
      int i = ((Integer)paramVarArgs[1]).intValue();
      if (getHostPkg().equals(localObject)) {
        localObject = paramMethod.invoke(paramObject, paramVarArgs);
      }
      do
      {
        ApplicationInfo localApplicationInfo;
        do
        {
          return localObject;
          int j = VUserHandle.myUserId();
          localApplicationInfo = j.a().b((String)localObject, i, j);
          localObject = localApplicationInfo;
        } while (localApplicationInfo != null);
        paramObject = (ApplicationInfo)paramMethod.invoke(paramObject, paramVarArgs);
        if (paramObject == null) {
          break;
        }
        localObject = paramObject;
      } while (isVisiblePackage(paramObject));
      return null;
    }
    
    public String getMethodName()
    {
      return " getApplicationInfo";
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
      return " getComponentEnabledSetting";
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
      paramVarArgs = j.a().a(i, j);
      paramObject = paramVarArgs;
      if (l.a(paramMethod)) {
        paramObject = l.a(paramVarArgs);
      }
      return paramObject;
    }
    
    public String getMethodName()
    {
      return " getInstalledApplications";
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
      if (isAppProcess()) {}
      for (paramObject = new ArrayList(SupermeCore.a().x());; paramObject = SupermeCore.a().n().getInstalledPackages(i))
      {
        paramObject.addAll(j.a().b(i, j));
        paramVarArgs = paramObject;
        if (l.a(paramMethod)) {
          paramVarArgs = l.a(paramObject);
        }
        return paramVarArgs;
      }
    }
    
    public String getMethodName()
    {
      return " getInstalledPackages";
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
      return " getInstallerPackageName";
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
      return " getPackageGids";
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
    GetPackageInfo() {}
    
    public boolean beforeCall(Object paramObject, Method paramMethod, Object... paramVarArgs)
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
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
      throws Throwable
    {
      Object localObject = (String)paramVarArgs[0];
      int i = ((Integer)paramVarArgs[1]).intValue();
      int j = VUserHandle.myUserId();
      localObject = j.a().a((String)localObject, i, j);
      if (localObject != null) {
        paramObject = localObject;
      }
      do
      {
        return paramObject;
        paramMethod = (PackageInfo)paramMethod.invoke(paramObject, paramVarArgs);
        if (paramMethod == null) {
          break;
        }
        paramObject = paramMethod;
      } while (isVisiblePackage(paramMethod.applicationInfo));
      return null;
    }
    
    public String getMethodName()
    {
      return " getPackageInfo";
    }
  }
  
  static class GetPackageInstaller
    extends MethodProxy
  {
    GetPackageInstaller() {}
    
    public Object call(Object paramObject, final Method paramMethod, Object... paramVarArgs)
      throws Throwable
    {
      paramObject = (IInterface)paramMethod.invoke(paramObject, paramVarArgs);
      paramMethod = j.a().c();
      Proxy.newProxyInstance.call(new Object[] { paramObject.getClass().getClassLoader(), paramObject.getClass().getInterfaces(), new InvocationHandler()
      {
        public Object invoke(Object paramAnonymousObject, Method paramAnonymousMethod, Object[] paramAnonymousArrayOfObject)
          throws Throwable
        {
          paramAnonymousObject = paramAnonymousMethod.getName();
          int i = -1;
          switch (paramAnonymousObject.hashCode())
          {
          }
          for (;;)
          {
            switch (i)
            {
            default: 
              throw new RuntimeException("Not support PackageInstaller method : " + paramAnonymousMethod.getName());
              if (paramAnonymousObject.equals("createSession"))
              {
                i = 0;
                continue;
                if (paramAnonymousObject.equals("updateSessionAppIcon"))
                {
                  i = 1;
                  continue;
                  if (paramAnonymousObject.equals("updateSessionAppLabel"))
                  {
                    i = 2;
                    continue;
                    if (paramAnonymousObject.equals("abandonSession"))
                    {
                      i = 3;
                      continue;
                      if (paramAnonymousObject.equals("openSession"))
                      {
                        i = 4;
                        continue;
                        if (paramAnonymousObject.equals("getSessionInfo"))
                        {
                          i = 5;
                          continue;
                          if (paramAnonymousObject.equals("getAllSessions"))
                          {
                            i = 6;
                            continue;
                            if (paramAnonymousObject.equals("getMySessions"))
                            {
                              i = 7;
                              continue;
                              if (paramAnonymousObject.equals("registerCallback"))
                              {
                                i = 8;
                                continue;
                                if (paramAnonymousObject.equals("unregisterCallback"))
                                {
                                  i = 9;
                                  continue;
                                  if (paramAnonymousObject.equals("setPermissionsResult")) {
                                    i = 10;
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
              break;
            }
          }
          paramAnonymousObject = SessionParams.create((PackageInstaller.SessionParams)paramAnonymousArrayOfObject[0]);
          paramAnonymousMethod = (String)paramAnonymousArrayOfObject[1];
          return Integer.valueOf(paramMethod.a(paramAnonymousObject, paramAnonymousMethod, VUserHandle.myUserId()));
          i = ((Integer)paramAnonymousArrayOfObject[0]).intValue();
          paramAnonymousObject = (Bitmap)paramAnonymousArrayOfObject[1];
          paramMethod.a(i, paramAnonymousObject);
          return Integer.valueOf(0);
          i = ((Integer)paramAnonymousArrayOfObject[0]).intValue();
          paramAnonymousObject = (String)paramAnonymousArrayOfObject[1];
          paramMethod.a(i, paramAnonymousObject);
          return Integer.valueOf(0);
          paramMethod.a(((Integer)paramAnonymousArrayOfObject[0]).intValue());
          return Integer.valueOf(0);
          return paramMethod.b(((Integer)paramAnonymousArrayOfObject[0]).intValue());
          paramAnonymousObject = paramMethod.c(((Integer)paramAnonymousArrayOfObject[0]).intValue());
          if (paramAnonymousObject != null) {
            return paramAnonymousObject.alloc();
          }
          return null;
          return l.a(paramMethod.d(((Integer)paramAnonymousArrayOfObject[0]).intValue()).getList());
          paramAnonymousObject = (String)paramAnonymousArrayOfObject[0];
          i = ((Integer)paramAnonymousArrayOfObject[1]).intValue();
          return l.a(paramMethod.a(paramAnonymousObject, i).getList());
          paramAnonymousObject = (IPackageInstallerCallback)paramAnonymousArrayOfObject[0];
          paramMethod.a(paramAnonymousObject, VUserHandle.myUserId());
          return Integer.valueOf(0);
          paramAnonymousObject = (IPackageInstallerCallback)paramAnonymousArrayOfObject[0];
          paramMethod.a(paramAnonymousObject);
          return Integer.valueOf(0);
          i = ((Integer)paramAnonymousArrayOfObject[0]).intValue();
          boolean bool = ((Boolean)paramAnonymousArrayOfObject[1]).booleanValue();
          paramMethod.a(i, bool);
          return Integer.valueOf(0);
        }
      } });
    }
    
    public String getMethodName()
    {
      return " getPackageInstaller";
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
      return Integer.valueOf(VUserHandle.getAppId(j.a().d(str, 0)));
    }
    
    public String getMethodName()
    {
      return " getPackageUid";
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
      throws Throwable
    {
      int j = ((Integer)paramVarArgs[0]).intValue();
      int k = Binder.getCallingUid();
      int i = j;
      if (j == SupermeCore.a().e()) {
        i = getBaseVUid();
      }
      paramObject = j.a().b(k);
      paramMethod = j.a().b(i);
      paramVarArgs = j.a().b(Process.myUid());
      b localB = new b(2);
      if ((paramObject != null) && (paramObject.length > 0)) {
        localB.addAll(Arrays.asList(paramObject));
      }
      if ((paramMethod != null) && (paramMethod.length > 0)) {
        localB.addAll(Arrays.asList(paramMethod));
      }
      if ((paramVarArgs != null) && (paramVarArgs.length > 0)) {
        localB.addAll(Arrays.asList(paramVarArgs));
      }
      return localB.toArray(new String[localB.size()]);
    }
    
    public String getMethodName()
    {
      return " getPackagesForUid";
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
      return " getPermissionFlags";
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
      localObject = j.a().a((String)localObject, i);
      if (localObject != null) {
        return localObject;
      }
      return super.call(paramObject, paramMethod, paramVarArgs);
    }
    
    public String getMethodName()
    {
      return " getPermissionGroupInfo";
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
      return " getPermissions";
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
      return " getPreferredActivities";
    }
  }
  
  static class GetProviderInfo
    extends MethodProxy
  {
    GetProviderInfo() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
      throws Throwable
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
          localProviderInfo = j.a().d((ComponentName)localObject, i, j);
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
      return " getProviderInfo";
    }
  }
  
  static class GetReceiverInfo
    extends MethodProxy
  {
    GetReceiverInfo() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
      throws Throwable
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
          localActivityInfo = j.a().a((ComponentName)localObject, i, 0);
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
      return " getReceiverInfo";
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
      localObject = j.a().c((ComponentName)localObject, i, j);
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
      return " getServiceInfo";
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
      return " isPackageAvailable";
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
      return " isPackageForzen";
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
      paramVarArgs = j.a().d(paramObject, i, 0);
      paramObject = paramVarArgs;
      if (l.a(paramMethod)) {
        paramObject = l.a(paramVarArgs);
      }
      return paramObject;
    }
    
    public String getMethodName()
    {
      return " queryContentProviders";
    }
  }
  
  static class QueryIntentActivities
    extends MethodProxy
  {
    QueryIntentActivities() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
      throws Throwable
    {
      boolean bool = l.a(paramMethod);
      int i = VUserHandle.myUserId();
      List localList = j.a().e((Intent)paramVarArgs[0], (String)paramVarArgs[1], ((Integer)paramVarArgs[2]).intValue(), i);
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
        return l.a(localList);
      }
      return localList;
    }
    
    public String getMethodName()
    {
      return " queryIntentActivities";
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
      boolean bool = l.a(paramMethod);
      int i = VUserHandle.myUserId();
      List localList = j.a().c((Intent)paramVarArgs[0], (String)paramVarArgs[1], ((Integer)paramVarArgs[2]).intValue(), i);
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
      if (l.a(paramMethod)) {
        return l.a(localList);
      }
      return localList;
    }
    
    public String getMethodName()
    {
      return " queryIntentContentProviders";
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
      boolean bool = l.a(paramMethod);
      int i = VUserHandle.myUserId();
      List localList = j.a().d((Intent)paramVarArgs[0], (String)paramVarArgs[1], ((Integer)paramVarArgs[2]).intValue(), i);
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
        return l.a(localList);
      }
      return localList;
    }
    
    public String getMethodName()
    {
      return " queryIntentReceivers";
    }
  }
  
  static class QueryIntentServices
    extends MethodProxy
  {
    QueryIntentServices() {}
    
    public Object call(Object paramObject, Method paramMethod, Object... paramVarArgs)
      throws Throwable
    {
      boolean bool = l.a(paramMethod);
      int i = VUserHandle.myUserId();
      List localList = j.a().f((Intent)paramVarArgs[0], (String)paramVarArgs[1], ((Integer)paramVarArgs[2]).intValue(), i);
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
        return l.a(localList);
      }
      return localList;
    }
    
    public String getMethodName()
    {
      return " queryIntentServices";
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
      return " removePackageFromPreferred";
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
      ProviderInfo localProviderInfo = j.a().c((String)localObject, i, j);
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
      return " resolveContentProvider";
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
      localObject2 = j.a().b((Intent)localObject1, (String)localObject2, i, j);
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = (ResolveInfo)paramMethod.invoke(paramObject, paramVarArgs);
      }
      return localObject1;
    }
    
    public String getMethodName()
    {
      return " resolveIntent";
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
      localObject2 = j.a().a((Intent)localObject1, (String)localObject2, i, j);
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = (ResolveInfo)paramMethod.invoke(paramObject, paramVarArgs);
      }
      return localObject1;
    }
    
    public String getMethodName()
    {
      return " resolveService";
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
      return " revokeRuntimePermission";
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
      return " setApplicationBlockedSettingAsUser";
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
      return " setApplicationEnabledSetting";
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
      return " setComponentEnabledSetting";
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
      return " setPackageStoppedState";
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
      return " checkUidSignatures";
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
      return j.a().c(i);
    }
    
    public String getMethodName()
    {
      return " getNameForUid";
    }
  }
}
