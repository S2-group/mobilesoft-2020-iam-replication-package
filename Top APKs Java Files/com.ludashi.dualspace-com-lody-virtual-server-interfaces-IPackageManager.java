package com.lody.virtual.server.interfaces;

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
import android.os.IBinder;
import com.lody.virtual.remote.VParceledListSlice;
import java.util.List;

public abstract interface IPackageManager
  extends IPCInterface
{
  public abstract boolean activitySupportsIntent(ComponentName paramComponentName, Intent paramIntent, String paramString);
  
  public abstract int checkPermission(String paramString1, String paramString2, int paramInt);
  
  public abstract int checkSignatures(String paramString1, String paramString2);
  
  public abstract ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt1, int paramInt2);
  
  public abstract List getAllPermissionGroups(int paramInt);
  
  public abstract ApplicationInfo getApplicationInfo(String paramString, int paramInt1, int paramInt2);
  
  public abstract VParceledListSlice getInstalledApplications(int paramInt1, int paramInt2);
  
  public abstract VParceledListSlice getInstalledPackages(int paramInt1, int paramInt2);
  
  public abstract String getNameForUid(int paramInt);
  
  public abstract PackageInfo getPackageInfo(String paramString, int paramInt1, int paramInt2);
  
  public abstract IBinder getPackageInstaller();
  
  public abstract int getPackageUid(String paramString, int paramInt);
  
  public abstract String[] getPackagesForUid(int paramInt);
  
  public abstract PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt);
  
  public abstract PermissionInfo getPermissionInfo(String paramString, int paramInt);
  
  public abstract ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt1, int paramInt2);
  
  public abstract ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt1, int paramInt2);
  
  public abstract ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt1, int paramInt2);
  
  public abstract List getSharedLibraries(String paramString);
  
  public abstract VParceledListSlice queryContentProviders(String paramString, int paramInt1, int paramInt2);
  
  public abstract List queryIntentActivities(Intent paramIntent, String paramString, int paramInt1, int paramInt2);
  
  public abstract List queryIntentContentProviders(Intent paramIntent, String paramString, int paramInt1, int paramInt2);
  
  public abstract List queryIntentReceivers(Intent paramIntent, String paramString, int paramInt1, int paramInt2);
  
  public abstract List queryIntentServices(Intent paramIntent, String paramString, int paramInt1, int paramInt2);
  
  public abstract List queryPermissionsByGroup(String paramString, int paramInt);
  
  public abstract List querySharedPackages(String paramString);
  
  public abstract ProviderInfo resolveContentProvider(String paramString, int paramInt1, int paramInt2);
  
  public abstract ResolveInfo resolveIntent(Intent paramIntent, String paramString, int paramInt1, int paramInt2);
  
  public abstract ResolveInfo resolveService(Intent paramIntent, String paramString, int paramInt1, int paramInt2);
  
  public static abstract class Stub
    implements IPackageManager
  {
    public Stub() {}
    
    public boolean isBinderAlive()
    {
      return false;
    }
    
    public boolean pingBinder()
    {
      return false;
    }
  }
}
