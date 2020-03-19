package com.amazon.identity.auth.device.framework;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.Iterator;
import java.util.List;

public abstract class SSOIntentFactory
{
  protected static final ComponentFinder BROADCAST_RECEIVER_FINDER = new ComponentFinder()
  {
    public boolean componentExists(TrustedPackageManager paramAnonymousTrustedPackageManager, ComponentName paramAnonymousComponentName)
    {
      boolean bool = false;
      try
      {
        paramAnonymousTrustedPackageManager = paramAnonymousTrustedPackageManager.getReceiverInfo(paramAnonymousComponentName, 128);
        if (paramAnonymousTrustedPackageManager != null) {
          bool = true;
        }
        return bool;
      }
      catch (PackageManager.NameNotFoundException paramAnonymousTrustedPackageManager) {}
      return false;
    }
  };
  protected static final ComponentFinder SERVICE_FINDER = new ComponentFinder()
  {
    public boolean componentExists(TrustedPackageManager paramAnonymousTrustedPackageManager, ComponentName paramAnonymousComponentName)
    {
      boolean bool = false;
      try
      {
        paramAnonymousTrustedPackageManager = paramAnonymousTrustedPackageManager.getServiceInfo(paramAnonymousComponentName, 128);
        if (paramAnonymousTrustedPackageManager != null) {
          bool = true;
        }
        return bool;
      }
      catch (PackageManager.NameNotFoundException paramAnonymousTrustedPackageManager) {}
      return false;
    }
  };
  protected final ComponentName mComponentName;
  
  public SSOIntentFactory(ComponentName paramComponentName)
  {
    this.mComponentName = paramComponentName;
  }
  
  protected static ComponentName findSSOComponent(Context paramContext, String paramString, ComponentFinder paramComponentFinder)
  {
    TrustedPackageManager localTrustedPackageManager = new TrustedPackageManager(paramContext);
    Object localObject = new ComponentName[4];
    localObject[0] = new ComponentName("com.amazon.imp", paramString);
    localObject[1] = new ComponentName("com.amazon.sso", paramString);
    localObject[2] = new ComponentName("com.amazon.dcp", paramString);
    localObject[3] = new ComponentName(paramContext, paramString);
    int j = localObject.length;
    int i = 0;
    while (i < j)
    {
      paramContext = localObject[i];
      if (paramComponentFinder.componentExists(localTrustedPackageManager, paramContext)) {
        return paramContext;
      }
      i += 1;
    }
    paramContext = localTrustedPackageManager.getInstalledPackages().iterator();
    while (paramContext.hasNext())
    {
      localObject = new ComponentName(((PackageInfo)paramContext.next()).packageName, paramString);
      if (paramComponentFinder.componentExists(localTrustedPackageManager, (ComponentName)localObject)) {
        return localObject;
      }
    }
    return null;
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
    public abstract boolean componentExists(TrustedPackageManager paramTrustedPackageManager, ComponentName paramComponentName);
  }
}
