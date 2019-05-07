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
      boolean bool = getInterface().activitySupportsIntent(paramComponentName, paramIntent, paramString);
      return bool;
    }
    catch (RemoteException paramComponentName) {}
    return ((Boolean)VirtualRuntime.crash(paramComponentName)).booleanValue();
  }
  
  public int checkPermission(String paramString1, String paramString2, int paramInt)
  {
    try
    {
      paramInt = getInterface().checkPermission(paramString1, paramString2, paramInt);
      return paramInt;
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
      if ((ARM64Helper.isEnableSupport()) && (ARM64Helper.is64BitApp(paramComponentName.getPackageName()))) {
        return get64BitInterface().getActivityInfo(paramComponentName, paramInt1, paramInt2);
      }
      paramComponentName = getInterface().getActivityInfo(paramComponentName, paramInt1, paramInt2);
      return paramComponentName;
    }
    catch (RemoteException paramComponentName) {}
    return (ActivityInfo)VirtualRuntime.crash(paramComponentName);
  }
  
  public List<PermissionGroupInfo> getAllPermissionGroups(int paramInt)
  {
    try
    {
      List localList = getInterface().getAllPermissionGroups(paramInt);
      return localList;
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
      if ((ARM64Helper.isEnableSupport()) && (ARM64Helper.is64BitApp(paramString))) {
        return get64BitInterface().getApplicationInfo(paramString, paramInt1, paramInt2);
      }
      paramString = getInterface().getApplicationInfo(paramString, paramInt1, paramInt2);
      return paramString;
    }
    catch (RemoteException paramString) {}
    return (ApplicationInfo)VirtualRuntime.crash(paramString);
  }
  
  public String[] getDangerousPermissions(String paramString)
  {
    try
    {
      paramString = getInterface().getDangerousPermissions(paramString);
      return paramString;
    }
    catch (RemoteException paramString) {}
    return (String[])VirtualRuntime.crash(paramString);
  }
  
  public List<ApplicationInfo> getInstalledApplications(int paramInt1, int paramInt2)
  {
    try
    {
      List localList = getInterface().getInstalledApplications(paramInt1, paramInt2).getList();
      return localList;
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
      List localList = getInterface().getInstalledPackages(paramInt1, paramInt2).getList();
      return localList;
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
      String str = getInterface().getNameForUid(paramInt);
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      return (String)VirtualRuntime.crash(localRemoteException);
    }
  }
  
  public PackageInfo getPackageInfo(String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      if ((ARM64Helper.isEnableSupport()) && (ARM64Helper.is64BitApp(paramString))) {
        return get64BitInterface().getPackageInfo(paramString, paramInt1, paramInt2);
      }
      paramString = getInterface().getPackageInfo(paramString, paramInt1, paramInt2);
      return paramString;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
      return null;
    }
    catch (RemoteException paramString) {}
    return (PackageInfo)VirtualRuntime.crash(paramString);
  }
  
  public IPackageInstaller getPackageInstaller()
  {
    try
    {
      IPackageInstaller localIPackageInstaller = getInterface().getPackageInstaller();
      return localIPackageInstaller;
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
      paramInt = getInterface().getPackageUid(paramString, paramInt);
      return paramInt;
    }
    catch (RemoteException paramString) {}
    return ((Integer)VirtualRuntime.crash(paramString)).intValue();
  }
  
  public String[] getPackagesForUid(int paramInt)
  {
    try
    {
      String[] arrayOfString = getInterface().getPackagesForUid(paramInt);
      return arrayOfString;
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
      paramString = getInterface().getPermissionGroupInfo(paramString, paramInt);
      return paramString;
    }
    catch (RemoteException paramString) {}
    return (PermissionGroupInfo)VirtualRuntime.crash(paramString);
  }
  
  public PermissionInfo getPermissionInfo(String paramString, int paramInt)
  {
    try
    {
      paramString = getInterface().getPermissionInfo(paramString, paramInt);
      return paramString;
    }
    catch (RemoteException paramString) {}
    return (PermissionInfo)VirtualRuntime.crash(paramString);
  }
  
  public ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    try
    {
      paramComponentName = getInterface().getProviderInfo(paramComponentName, paramInt1, paramInt2);
      return paramComponentName;
    }
    catch (RemoteException paramComponentName) {}
    return (ProviderInfo)VirtualRuntime.crash(paramComponentName);
  }
  
  public ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    try
    {
      paramComponentName = getInterface().getReceiverInfo(paramComponentName, paramInt1, paramInt2);
      return paramComponentName;
    }
    catch (RemoteException paramComponentName) {}
    return (ActivityInfo)VirtualRuntime.crash(paramComponentName);
  }
  
  public ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    try
    {
      paramComponentName = getInterface().getServiceInfo(paramComponentName, paramInt1, paramInt2);
      return paramComponentName;
    }
    catch (RemoteException paramComponentName) {}
    return (ServiceInfo)VirtualRuntime.crash(paramComponentName);
  }
  
  public boolean isVirtualAuthority(String paramString)
  {
    try
    {
      boolean bool = getInterface().isVirtualAuthority(paramString);
      return bool;
    }
    catch (RemoteException paramString)
    {
      VirtualRuntime.crash(paramString);
    }
    return false;
  }
  
  public List<ProviderInfo> queryContentProviders(String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      paramString = getInterface().queryContentProviders(paramString, paramInt1, paramInt2).getList();
      return paramString;
    }
    catch (RemoteException paramString) {}
    return (List)VirtualRuntime.crash(paramString);
  }
  
  public List<ResolveInfo> queryIntentActivities(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    String str = paramIntent.getPackage();
    try
    {
      if ((ARM64Helper.isEnableSupport()) && (ARM64Helper.is64BitApp(str))) {
        return get64BitInterface().queryIntentActivities(paramIntent, paramString, paramInt1, paramInt2);
      }
      paramIntent = getInterface().queryIntentActivities(paramIntent, paramString, paramInt1, paramInt2);
      return paramIntent;
    }
    catch (RemoteException paramIntent) {}
    return (List)VirtualRuntime.crash(paramIntent);
  }
  
  public List<ResolveInfo> queryIntentContentProviders(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      paramIntent = getInterface().queryIntentContentProviders(paramIntent, paramString, paramInt1, paramInt2);
      return paramIntent;
    }
    catch (RemoteException paramIntent) {}
    return (List)VirtualRuntime.crash(paramIntent);
  }
  
  public List<ResolveInfo> queryIntentReceivers(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      paramIntent = getInterface().queryIntentReceivers(paramIntent, paramString, paramInt1, paramInt2);
      return paramIntent;
    }
    catch (RemoteException paramIntent) {}
    return (List)VirtualRuntime.crash(paramIntent);
  }
  
  public List<ResolveInfo> queryIntentServices(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      paramIntent = getInterface().queryIntentServices(paramIntent, paramString, paramInt1, paramInt2);
      return paramIntent;
    }
    catch (RemoteException paramIntent) {}
    return (List)VirtualRuntime.crash(paramIntent);
  }
  
  public List<PermissionInfo> queryPermissionsByGroup(String paramString, int paramInt)
  {
    try
    {
      paramString = getInterface().queryPermissionsByGroup(paramString, paramInt);
      return paramString;
    }
    catch (RemoteException paramString) {}
    return (List)VirtualRuntime.crash(paramString);
  }
  
  public List<String> querySharedPackages(String paramString)
  {
    try
    {
      paramString = getInterface().querySharedPackages(paramString);
      return paramString;
    }
    catch (RemoteException paramString) {}
    return (List)VirtualRuntime.crash(paramString);
  }
  
  public ProviderInfo resolveContentProvider(String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      paramString = getInterface().resolveContentProvider(paramString, paramInt1, paramInt2);
      return paramString;
    }
    catch (RemoteException paramString) {}
    return (ProviderInfo)VirtualRuntime.crash(paramString);
  }
  
  public ResolveInfo resolveIntent(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      if ((ARM64Helper.isEnableSupport()) && (ARM64Helper.is64BitApp(paramIntent.getPackage()))) {
        return get64BitInterface().resolveIntent(paramIntent, paramString, paramInt1, paramInt2);
      }
      paramIntent = getInterface().resolveIntent(paramIntent, paramString, paramInt1, paramInt2);
      return paramIntent;
    }
    catch (RemoteException paramIntent) {}
    return (ResolveInfo)VirtualRuntime.crash(paramIntent);
  }
  
  public ResolveInfo resolveService(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      paramIntent = getInterface().resolveService(paramIntent, paramString, paramInt1, paramInt2);
      return paramIntent;
    }
    catch (RemoteException paramIntent) {}
    return (ResolveInfo)VirtualRuntime.crash(paramIntent);
  }
}
