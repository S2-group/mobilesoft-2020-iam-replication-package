package com.yandex.metrica.impl.ob;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import java.util.ArrayList;
import java.util.List;

public class qa
{
  public qa() {}
  
  public PackageInfo a(Context paramContext, String paramString)
  {
    return a(paramContext, paramString, 0);
  }
  
  public PackageInfo a(Context paramContext, String paramString, int paramInt)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, paramInt);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public ServiceInfo a(Context paramContext, ComponentName paramComponentName, int paramInt)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getServiceInfo(paramComponentName, paramInt);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public List<PackageInfo> a(Context paramContext, int paramInt)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(paramInt);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    return new ArrayList();
  }
  
  public List<ResolveInfo> a(Context paramContext, Intent paramIntent, int paramInt)
  {
    try
    {
      paramContext = paramContext.getPackageManager().queryIntentActivities(paramIntent, paramInt);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    return new ArrayList();
  }
  
  public void a(Context paramContext, ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    try
    {
      paramContext.getPackageManager().setComponentEnabledSetting(paramComponentName, paramInt1, paramInt2);
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  public ApplicationInfo b(Context paramContext, String paramString, int paramInt)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramString, paramInt);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public List<ResolveInfo> b(Context paramContext, Intent paramIntent, int paramInt)
  {
    try
    {
      paramContext = paramContext.getPackageManager().queryBroadcastReceivers(paramIntent, paramInt);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    return new ArrayList();
  }
  
  public boolean b(Context paramContext, String paramString)
  {
    try
    {
      boolean bool = paramContext.getPackageManager().hasSystemFeature(paramString);
      return bool;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    return false;
  }
  
  public List<ResolveInfo> c(Context paramContext, Intent paramIntent, int paramInt)
  {
    try
    {
      paramContext = paramContext.getPackageManager().queryIntentServices(paramIntent, paramInt);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    return new ArrayList();
  }
  
  public ResolveInfo d(Context paramContext, Intent paramIntent, int paramInt)
  {
    try
    {
      paramContext = paramContext.getPackageManager().resolveService(paramIntent, paramInt);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    return null;
  }
}
