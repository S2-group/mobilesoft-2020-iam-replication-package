package com.yandex.metrica.impl.ob;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class qa
{
  public qa() {}
  
  @Nullable
  public PackageInfo a(Context paramContext, String paramString)
  {
    return a(paramContext, paramString, 0);
  }
  
  @Nullable
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
  
  @Nullable
  public ServiceInfo a(@NonNull Context paramContext, ComponentName paramComponentName, int paramInt)
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
  
  @NonNull
  public List<PackageInfo> a(@NonNull Context paramContext, int paramInt)
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
  
  @NonNull
  public List<ResolveInfo> a(@NonNull Context paramContext, Intent paramIntent, int paramInt)
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
  
  public void a(@NonNull Context paramContext, ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    try
    {
      paramContext.getPackageManager().setComponentEnabledSetting(paramComponentName, paramInt1, paramInt2);
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  @Nullable
  public ApplicationInfo b(@NonNull Context paramContext, String paramString, int paramInt)
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
  
  @NonNull
  public List<ResolveInfo> b(@NonNull Context paramContext, Intent paramIntent, int paramInt)
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
  
  public boolean b(@NonNull Context paramContext, @NonNull String paramString)
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
  
  @NonNull
  public List<ResolveInfo> c(@NonNull Context paramContext, Intent paramIntent, int paramInt)
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
  
  @Nullable
  public ResolveInfo d(@NonNull Context paramContext, Intent paramIntent, int paramInt)
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
