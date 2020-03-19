package com.amazon.dcp.framework;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class DCPIntentFactory
{
  protected static final ComponentFinder BROADCAST_RECEIVER_FINDER = new ComponentFinder()
  {
    public boolean componentExists(PackageManager paramAnonymousPackageManager, ComponentName paramAnonymousComponentName)
    {
      boolean bool = false;
      try
      {
        paramAnonymousPackageManager = paramAnonymousPackageManager.getReceiverInfo(paramAnonymousComponentName, 128);
        if (paramAnonymousPackageManager != null) {
          bool = true;
        }
        return bool;
      }
      catch (PackageManager.NameNotFoundException paramAnonymousPackageManager) {}
      return false;
    }
  };
  private static final String DCP_PACKAGE_NAME = "com.amazon.dcp";
  protected static final ComponentFinder SERVICE_FINDER = new ComponentFinder()
  {
    public boolean componentExists(PackageManager paramAnonymousPackageManager, ComponentName paramAnonymousComponentName)
    {
      boolean bool = false;
      try
      {
        paramAnonymousPackageManager = paramAnonymousPackageManager.getServiceInfo(paramAnonymousComponentName, 128);
        if (paramAnonymousPackageManager != null) {
          bool = true;
        }
        return bool;
      }
      catch (PackageManager.NameNotFoundException paramAnonymousPackageManager) {}
      return false;
    }
  };
  protected final ComponentName mComponentName;
  
  public DCPIntentFactory(ComponentName paramComponentName)
  {
    this.mComponentName = paramComponentName;
  }
  
  protected static ComponentName findDCPComponent(Context paramContext, String paramString, ComponentFinder paramComponentFinder)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject = new ComponentName[2];
    localObject[0] = new ComponentName("com.amazon.dcp", paramString);
    localObject[1] = new ComponentName(paramContext, paramString);
    int j = localObject.length;
    int i = 0;
    while (i < j)
    {
      ComponentName localComponentName = localObject[i];
      if (paramComponentFinder.componentExists(localPackageManager, localComponentName)) {
        return localComponentName;
      }
      i += 1;
    }
    paramContext = getTrustedPackages(paramContext).iterator();
    while (paramContext.hasNext())
    {
      localObject = new ComponentName(((PackageInfo)paramContext.next()).packageName, paramString);
      if (paramComponentFinder.componentExists(localPackageManager, (ComponentName)localObject)) {
        return localObject;
      }
    }
    return null;
  }
  
  private static List<PackageInfo> getTrustedPackages(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = paramContext.getPackageName();
    Object localObject = localPackageManager.getInstalledPackages(64);
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (localPackageManager.checkSignatures(paramContext, localPackageInfo.packageName) == 0) {
        localArrayList.add(localPackageInfo);
      }
    }
    return localArrayList;
  }
  
  public Intent buildIntent()
  {
    return buildIntent(null);
  }
  
  public Intent buildIntent(String paramString)
  {
    Intent localIntent = new Intent();
    if (paramString != null) {
      localIntent.setAction(paramString);
    }
    if (this.mComponentName != null) {
      localIntent.setComponent(this.mComponentName);
    }
    return localIntent;
  }
  
  protected static abstract interface ComponentFinder
  {
    public abstract boolean componentExists(PackageManager paramPackageManager, ComponentName paramComponentName);
  }
}
