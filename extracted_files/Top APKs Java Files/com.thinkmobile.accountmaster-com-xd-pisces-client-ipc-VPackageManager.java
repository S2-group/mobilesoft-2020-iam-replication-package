package com.xd.pisces.client.ipc;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.RemoteException;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.xd.pisces.client.core.Pisces;
import com.xd.pisces.client.env.VirtualRuntime;
import com.xd.pisces.helper.ARM64Helper;
import com.xd.pisces.remote.VParceledListSlice;
import com.xd.pisces.server.IPackageInstaller;
import com.xd.pisces.server.IPackageManager;
import com.xd.pisces.server.IPackageManager.Stub;
import java.util.List;

public class VPackageManager
{
  private static final VPackageManager sMgr = new VPackageManager();
  private IPackageManager mRemote;
  private IPackageManager mRemote64Bit;
  
  public VPackageManager() {}
  
  public static VPackageManager get()
  {
    return sMgr;
  }
  
  private Object getRemoteInterface()
  {
    return IPackageManager.Stub.asInterface(ServiceManagerNative.getService("package"));
  }
  
  public boolean activitySupportsIntent(ComponentName paramComponentName, Intent paramIntent, String paramString)
  {
    try
    {
      return getInterface().activitySupportsIntent(paramComponentName, paramIntent, paramString);
    }
    catch (RemoteException paramComponentName) {}
    return ((Boolean)VirtualRuntime.crash(paramComponentName)).booleanValue();
  }
  
  public int checkPermission(String paramString1, String paramString2, int paramInt)
  {
    try
    {
      return getInterface().checkPermission(paramString1, paramString2, paramInt);
    }
    catch (RemoteException paramString1) {}
    return ((Integer)VirtualRuntime.crash(paramString1)).intValue();
  }
  
  public IPackageManager get64BitInterface()
  {
    if ((this.mRemote64Bit == null) || ((ServiceManagerNative.isBinderDead(this.mRemote64Bit)) && (!Pisces.get().isVAppProcess()))) {}
    try
    {
      this.mRemote64Bit = ((IPackageManager)LocalProxyUtils.genProxy(IPackageManager.class, IPackageManager.Stub.asInterface(ServiceManagerNative.get64BitService("package"))));
      return this.mRemote64Bit;
    }
    finally {}
  }
  
  public ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    try
    {
      if ((ARM64Helper.isEnableSupport()) && (ARM64Helper.is64BitApp(paramComponentName.getPackageName()))) {}
      for (IPackageManager localIPackageManager = get64BitInterface();; localIPackageManager = getInterface()) {
        return localIPackageManager.getActivityInfo(paramComponentName, paramInt1, paramInt2);
      }
      return (ActivityInfo)VirtualRuntime.crash(paramComponentName);
    }
    catch (RemoteException paramComponentName) {}
  }
  
  public List<PermissionGroupInfo> getAllPermissionGroups(int paramInt)
  {
    try
    {
      return getInterface().getAllPermissionGroups(paramInt);
    }
    catch (RemoteException localRemoteException)
    {
      return (List)VirtualRuntime.crash(localRemoteException);
    }
  }
  
  public ApplicationInfo getApplicationInfo(String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      if ((ARM64Helper.isEnableSupport()) && (ARM64Helper.is64BitApp(paramString))) {}
      for (IPackageManager localIPackageManager = get64BitInterface();; localIPackageManager = getInterface()) {
        return localIPackageManager.getApplicationInfo(paramString, paramInt1, paramInt2);
      }
      return (ApplicationInfo)VirtualRuntime.crash(paramString);
    }
    catch (RemoteException paramString) {}
  }
  
  public String[] getDangerousPermissions(String paramString)
  {
    try
    {
      return getInterface().getDangerousPermissions(paramString);
    }
    catch (RemoteException paramString) {}
    return (String[])VirtualRuntime.crash(paramString);
  }
  
  public List<ApplicationInfo> getInstalledApplications(int paramInt1, int paramInt2)
  {
    try
    {
      return getInterface().getInstalledApplications(paramInt1, paramInt2).getList();
    }
    catch (RemoteException localRemoteException)
    {
      return (List)VirtualRuntime.crash(localRemoteException);
    }
  }
  
  public List<PackageInfo> getInstalledPackages(int paramInt1, int paramInt2)
  {
    try
    {
      return getInterface().getInstalledPackages(paramInt1, paramInt2).getList();
    }
    catch (RemoteException localRemoteException)
    {
      return (List)VirtualRuntime.crash(localRemoteException);
    }
  }
  
  public IPackageManager getInterface()
  {
    if ((this.mRemote == null) || ((ServiceManagerNative.isBinderDead(this.mRemote)) && (!Pisces.get().isVAppProcess()))) {}
    try
    {
      this.mRemote = ((IPackageManager)LocalProxyUtils.genProxy(IPackageManager.class, getRemoteInterface()));
      return this.mRemote;
    }
    finally {}
  }
  
  public String getNameForUid(int paramInt)
  {
    try
    {
      return getInterface().getNameForUid(paramInt);
    }
    catch (RemoteException localRemoteException)
    {
      return (String)VirtualRuntime.crash(localRemoteException);
    }
  }
  
  public PackageInfo getPackageInfo(String paramString, int paramInt1, int paramInt2)
  {
    Object localObject = null;
    try
    {
      if ((ARM64Helper.isEnableSupport()) && (ARM64Helper.is64BitApp(paramString))) {}
      for (IPackageManager localIPackageManager = get64BitInterface();; localIPackageManager = getInterface()) {
        return localIPackageManager.getPackageInfo(paramString, paramInt1, paramInt2);
      }
      return paramString;
    }
    catch (Throwable paramString)
    {
      ThrowableExtension.printStackTrace(paramString);
      paramString = localObject;
    }
    catch (RemoteException paramString)
    {
      paramString = (PackageInfo)VirtualRuntime.crash(paramString);
    }
  }
  
  public IPackageInstaller getPackageInstaller()
  {
    try
    {
      return getInterface().getPackageInstaller();
    }
    catch (RemoteException localRemoteException)
    {
      return (IPackageInstaller)VirtualRuntime.crash(localRemoteException);
    }
  }
  
  public int getPackageUid(String paramString, int paramInt)
  {
    try
    {
      return getInterface().getPackageUid(paramString, paramInt);
    }
    catch (RemoteException paramString) {}
    return ((Integer)VirtualRuntime.crash(paramString)).intValue();
  }
  
  public String[] getPackagesForUid(int paramInt)
  {
    try
    {
      return getInterface().getPackagesForUid(paramInt);
    }
    catch (RemoteException localRemoteException)
    {
      return (String[])VirtualRuntime.crash(localRemoteException);
    }
  }
  
  public PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt)
  {
    try
    {
      return getInterface().getPermissionGroupInfo(paramString, paramInt);
    }
    catch (RemoteException paramString) {}
    return (PermissionGroupInfo)VirtualRuntime.crash(paramString);
  }
  
  public PermissionInfo getPermissionInfo(String paramString, int paramInt)
  {
    try
    {
      return getInterface().getPermissionInfo(paramString, paramInt);
    }
    catch (RemoteException paramString) {}
    return (PermissionInfo)VirtualRuntime.crash(paramString);
  }
  
  public ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    try
    {
      return getInterface().getProviderInfo(paramComponentName, paramInt1, paramInt2);
    }
    catch (RemoteException paramComponentName) {}
    return (ProviderInfo)VirtualRuntime.crash(paramComponentName);
  }
  
  public ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    try
    {
      return getInterface().getReceiverInfo(paramComponentName, paramInt1, paramInt2);
    }
    catch (RemoteException paramComponentName) {}
    return (ActivityInfo)VirtualRuntime.crash(paramComponentName);
  }
  
  public ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    try
    {
      return getInterface().getServiceInfo(paramComponentName, paramInt1, paramInt2);
    }
    catch (RemoteException paramComponentName) {}
    return (ServiceInfo)VirtualRuntime.crash(paramComponentName);
  }
  
  public List<ProviderInfo> queryContentProviders(String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      return getInterface().queryContentProviders(paramString, paramInt1, paramInt2).getList();
    }
    catch (RemoteException paramString) {}
    return (List)VirtualRuntime.crash(paramString);
  }
  
  public List<ResolveInfo> queryIntentActivities(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    Object localObject = paramIntent.getPackage();
    try
    {
      if ((ARM64Helper.isEnableSupport()) && (ARM64Helper.is64BitApp((String)localObject))) {}
      for (localObject = get64BitInterface();; localObject = getInterface()) {
        return ((IPackageManager)localObject).queryIntentActivities(paramIntent, paramString, paramInt1, paramInt2);
      }
      return (List)VirtualRuntime.crash(paramIntent);
    }
    catch (RemoteException paramIntent) {}
  }
  
  public List<ResolveInfo> queryIntentContentProviders(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      return getInterface().queryIntentContentProviders(paramIntent, paramString, paramInt1, paramInt2);
    }
    catch (RemoteException paramIntent) {}
    return (List)VirtualRuntime.crash(paramIntent);
  }
  
  public List<ResolveInfo> queryIntentReceivers(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      return getInterface().queryIntentReceivers(paramIntent, paramString, paramInt1, paramInt2);
    }
    catch (RemoteException paramIntent) {}
    return (List)VirtualRuntime.crash(paramIntent);
  }
  
  public List<ResolveInfo> queryIntentServices(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      return getInterface().queryIntentServices(paramIntent, paramString, paramInt1, paramInt2);
    }
    catch (RemoteException paramIntent) {}
    return (List)VirtualRuntime.crash(paramIntent);
  }
  
  public List<PermissionInfo> queryPermissionsByGroup(String paramString, int paramInt)
  {
    try
    {
      return getInterface().queryPermissionsByGroup(paramString, paramInt);
    }
    catch (RemoteException paramString) {}
    return (List)VirtualRuntime.crash(paramString);
  }
  
  public List<String> querySharedPackages(String paramString)
  {
    try
    {
      return getInterface().querySharedPackages(paramString);
    }
    catch (RemoteException paramString) {}
    return (List)VirtualRuntime.crash(paramString);
  }
  
  public ProviderInfo resolveContentProvider(String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      return getInterface().resolveContentProvider(paramString, paramInt1, paramInt2);
    }
    catch (RemoteException paramString) {}
    return (ProviderInfo)VirtualRuntime.crash(paramString);
  }
  
  public ResolveInfo resolveIntent(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      if ((ARM64Helper.isEnableSupport()) && (ARM64Helper.is64BitApp(paramIntent.getPackage()))) {}
      for (IPackageManager localIPackageManager = get64BitInterface();; localIPackageManager = getInterface()) {
        return localIPackageManager.resolveIntent(paramIntent, paramString, paramInt1, paramInt2);
      }
      return (ResolveInfo)VirtualRuntime.crash(paramIntent);
    }
    catch (RemoteException paramIntent) {}
  }
  
  public ResolveInfo resolveService(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      return getInterface().resolveService(paramIntent, paramString, paramInt1, paramInt2);
    }
    catch (RemoteException paramIntent) {}
    return (ResolveInfo)VirtualRuntime.crash(paramIntent);
  }
}
