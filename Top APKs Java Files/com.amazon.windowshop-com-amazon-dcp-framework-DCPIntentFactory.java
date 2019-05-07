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
    public final boolean componentExists(PackageManager paramAnonymousPackageManager, ComponentName paramAnonymousComponentName)
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
  protected static final ComponentFinder SERVICE_FINDER = new ComponentFinder()
  {
    public final boolean componentExists(PackageManager paramAnonymousPackageManager, ComponentName paramAnonymousComponentName)
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
  
  public static List<PackageInfo> getTrustedPackages(Context paramContext)
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
  
  public final Intent buildIntent()
  {
    Intent localIntent = new Intent();
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
