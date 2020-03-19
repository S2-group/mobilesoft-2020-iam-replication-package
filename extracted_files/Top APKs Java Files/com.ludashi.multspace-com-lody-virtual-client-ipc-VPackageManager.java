package com.lody.virtual.client.ipc;

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
import android.text.TextUtils;
import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.helper.utils.IInterfaceUtils;
import com.lody.virtual.remote.VParceledListSlice;
import com.lody.virtual.server.IPackageInstaller;
import com.lody.virtual.server.IPackageInstaller.Stub;
import com.lody.virtual.server.interfaces.IPackageManager;
import com.lody.virtual.server.interfaces.IPackageManager.Stub;
import java.util.List;

public class VPackageManager
{
  private static final VPackageManager sMgr = new VPackageManager();
  private IPackageManager mService;
  
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
      boolean bool = getService().activitySupportsIntent(paramComponentName, paramIntent, paramString);
      return bool;
    }
    catch (RemoteException paramComponentName) {}
    return ((Boolean)VirtualRuntime.crash(paramComponentName)).booleanValue();
  }
  
  public int checkPermission(String paramString1, String paramString2, int paramInt)
  {
    if (TextUtils.isEmpty(paramString1)) {
      return 0;
    }
    try
    {
      paramInt = getService().checkPermission(paramString1, paramString2, paramInt);
      return paramInt;
    }
    catch (RemoteException paramString1) {}
    return -1;
  }
  
  public int checkSignatures(String paramString1, String paramString2)
  {
    try
    {
      int i = getService().checkSignatures(paramString1, paramString2);
      return i;
    }
    catch (RemoteException paramString1) {}
    return ((Integer)VirtualRuntime.crash(paramString1)).intValue();
  }
  
  public ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    try
    {
      paramComponentName = getService().getActivityInfo(paramComponentName, paramInt1, paramInt2);
      return paramComponentName;
    }
    catch (RemoteException paramComponentName) {}
    return (ActivityInfo)VirtualRuntime.crash(paramComponentName);
  }
  
  public String[] getAllAuthorities()
  {
    try
    {
      String[] arrayOfString = getService().getAllAuthorities();
      return arrayOfString;
    }
    catch (RemoteException localRemoteException)
    {
      return (String[])VirtualRuntime.crash(localRemoteException);
    }
  }
  
  public List getAllPermissionGroups(int paramInt)
  {
    try
    {
      List localList = getService().getAllPermissionGroups(paramInt);
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
      paramString = getService().getApplicationInfo(paramString, paramInt1, paramInt2);
      return paramString;
    }
    catch (RemoteException paramString) {}
    return (ApplicationInfo)VirtualRuntime.crash(paramString);
  }
  
  public String[] getDangrousPermissions(String paramString)
  {
    try
    {
      paramString = getService().getDangrousPermissions(paramString);
      return paramString;
    }
    catch (RemoteException paramString) {}
    return (String[])VirtualRuntime.crash(paramString);
  }
  
  public List getInstalledApplications(int paramInt1, int paramInt2)
  {
    try
    {
      List localList = getService().getInstalledApplications(paramInt1, paramInt2).getList();
      return localList;
    }
    catch (RemoteException localRemoteException)
    {
      return (List)VirtualRuntime.crash(localRemoteException);
    }
  }
  
  public List getInstalledPackages(int paramInt1, int paramInt2)
  {
    try
    {
      List localList = getService().getInstalledPackages(paramInt1, paramInt2).getList();
      return localList;
    }
    catch (RemoteException localRemoteException)
    {
      return (List)VirtualRuntime.crash(localRemoteException);
    }
  }
  
  public String getNameForUid(int paramInt)
  {
    try
    {
      String str = getService().getNameForUid(paramInt);
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
      paramString = getService().getPackageInfo(paramString, paramInt1, paramInt2);
      return paramString;
    }
    catch (RemoteException paramString) {}
    return (PackageInfo)VirtualRuntime.crash(paramString);
  }
  
  public IPackageInstaller getPackageInstaller()
  {
    try
    {
      IPackageInstaller localIPackageInstaller = IPackageInstaller.Stub.asInterface(getService().getPackageInstaller());
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
      paramInt = getService().getPackageUid(paramString, paramInt);
      return paramInt;
    }
    catch (RemoteException paramString) {}
    return ((Integer)VirtualRuntime.crash(paramString)).intValue();
  }
  
  public String[] getPackagesForUid(int paramInt)
  {
    try
    {
      String[] arrayOfString = getService().getPackagesForUid(paramInt);
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
      paramString = getService().getPermissionGroupInfo(paramString, paramInt);
      return paramString;
    }
    catch (RemoteException paramString) {}
    return (PermissionGroupInfo)VirtualRuntime.crash(paramString);
  }
  
  public PermissionInfo getPermissionInfo(String paramString, int paramInt)
  {
    try
    {
      paramString = getService().getPermissionInfo(paramString, paramInt);
      return paramString;
    }
    catch (RemoteException paramString) {}
    return (PermissionInfo)VirtualRuntime.crash(paramString);
  }
  
  public ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    try
    {
      paramComponentName = getService().getProviderInfo(paramComponentName, paramInt1, paramInt2);
      return paramComponentName;
    }
    catch (RemoteException paramComponentName) {}
    return (ProviderInfo)VirtualRuntime.crash(paramComponentName);
  }
  
  public ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    try
    {
      paramComponentName = getService().getReceiverInfo(paramComponentName, paramInt1, paramInt2);
      return paramComponentName;
    }
    catch (RemoteException paramComponentName) {}
    return (ActivityInfo)VirtualRuntime.crash(paramComponentName);
  }
  
  public IPackageManager getService()
  {
    if (!IInterfaceUtils.isAlive(this.mService)) {}
    try
    {
      this.mService = ((IPackageManager)LocalProxyUtils.genProxy(IPackageManager.class, getRemoteInterface()));
      return this.mService;
    }
    finally {}
  }
  
  public ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    try
    {
      paramComponentName = getService().getServiceInfo(paramComponentName, paramInt1, paramInt2);
      return paramComponentName;
    }
    catch (RemoteException paramComponentName) {}
    return (ServiceInfo)VirtualRuntime.crash(paramComponentName);
  }
  
  public List queryContentProviders(String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      paramString = getService().queryContentProviders(paramString, paramInt1, paramInt2).getList();
      return paramString;
    }
    catch (RemoteException paramString) {}
    return (List)VirtualRuntime.crash(paramString);
  }
  
  public List queryIntentActivities(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      paramIntent = getService().queryIntentActivities(paramIntent, paramString, paramInt1, paramInt2);
      return paramIntent;
    }
    catch (RemoteException paramIntent) {}
    return (List)VirtualRuntime.crash(paramIntent);
  }
  
  public List queryIntentContentProviders(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      paramIntent = getService().queryIntentContentProviders(paramIntent, paramString, paramInt1, paramInt2);
      return paramIntent;
    }
    catch (RemoteException paramIntent) {}
    return (List)VirtualRuntime.crash(paramIntent);
  }
  
  public List queryIntentReceivers(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      paramIntent = getService().queryIntentReceivers(paramIntent, paramString, paramInt1, paramInt2);
      return paramIntent;
    }
    catch (RemoteException paramIntent) {}
    return (List)VirtualRuntime.crash(paramIntent);
  }
  
  public List queryIntentServices(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      paramIntent = getService().queryIntentServices(paramIntent, paramString, paramInt1, paramInt2);
      return paramIntent;
    }
    catch (RemoteException paramIntent) {}
    return (List)VirtualRuntime.crash(paramIntent);
  }
  
  public List queryPermissionsByGroup(String paramString, int paramInt)
  {
    try
    {
      paramString = getService().queryPermissionsByGroup(paramString, paramInt);
      return paramString;
    }
    catch (RemoteException paramString) {}
    return (List)VirtualRuntime.crash(paramString);
  }
  
  public List querySharedPackages(String paramString)
  {
    try
    {
      paramString = getService().querySharedPackages(paramString);
      return paramString;
    }
    catch (RemoteException paramString) {}
    return (List)VirtualRuntime.crash(paramString);
  }
  
  public ProviderInfo resolveContentProvider(String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      paramString = getService().resolveContentProvider(paramString, paramInt1, paramInt2);
      return paramString;
    }
    catch (RemoteException paramString) {}
    return (ProviderInfo)VirtualRuntime.crash(paramString);
  }
  
  public ResolveInfo resolveIntent(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      paramIntent = getService().resolveIntent(paramIntent, paramString, paramInt1, paramInt2);
      return paramIntent;
    }
    catch (RemoteException paramIntent) {}
    return (ResolveInfo)VirtualRuntime.crash(paramIntent);
  }
  
  public ResolveInfo resolveService(Intent paramIntent, String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      paramIntent = getService().resolveService(paramIntent, paramString, paramInt1, paramInt2);
      return paramIntent;
    }
    catch (RemoteException paramIntent) {}
    return (ResolveInfo)VirtualRuntime.crash(paramIntent);
  }
}
