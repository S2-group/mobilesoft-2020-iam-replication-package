package com.amazon.identity.auth.device.framework;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.net.Uri;
import com.amazon.identity.auth.device.framework.a.g;
import com.amazon.identity.auth.device.utils.aw;
import com.amazon.identity.auth.device.utils.t;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class y
{
  public static final String a = "com.amazon.identity.auth.device.framework.y";
  private static volatile g c;
  public final PackageManager b;
  private final String d;
  private final boolean e;
  private final Context f;
  private volatile Set<Signature> g;
  
  public y(Context paramContext)
  {
    this(paramContext, false);
  }
  
  public y(Context paramContext, boolean paramBoolean)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("context cannot be null");
    }
    if (c == null)
    {
      aw.a(a, "Trying to use default signature based package trust logic. This should be on 3P device");
      a(new com.amazon.identity.auth.device.framework.a.h());
    }
    this.f = paramContext;
    this.d = paramContext.getPackageName();
    this.b = paramContext.getPackageManager();
    if (paramBoolean)
    {
      this.g = TrustedAppUtils.b(paramContext, this.b);
      this.e = false;
      return;
    }
    this.g = TrustedAppUtils.a(paramContext, this.b);
    this.e = b.a(paramContext);
  }
  
  public static PackageInfo a(String paramString, int paramInt, PackageManager paramPackageManager)
    throws PackageManager.NameNotFoundException
  {
    try
    {
      PackageInfo localPackageInfo = paramPackageManager.getPackageInfo(paramString, paramInt);
      return localPackageInfo;
    }
    catch (Exception localException)
    {
      a(localException);
      return paramPackageManager.getPackageInfo(paramString, paramInt);
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      throw paramString;
    }
  }
  
  private static void a(g paramG)
  {
    try
    {
      c = paramG;
      String str = a;
      StringBuilder localStringBuilder = new StringBuilder("Setting package trust logic as: ");
      localStringBuilder.append(paramG.a());
      aw.a(str, localStringBuilder.toString());
      return;
    }
    finally
    {
      paramG = finally;
      throw paramG;
    }
  }
  
  static void a(Exception paramException)
  {
    aw.b(a, "PackageManager call failed; retrying", paramException);
    com.amazon.identity.platform.a.b.a("PackageManagerError", new String[0]);
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      a(paramString, 64, paramContext);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static boolean a(ProviderInfo paramProviderInfo)
  {
    return (paramProviderInfo != null) && (paramProviderInfo.enabled) && (paramProviderInfo.applicationInfo != null) && (paramProviderInfo.applicationInfo.enabled);
  }
  
  public static boolean a(Uri paramUri, PackageManager paramPackageManager)
  {
    return h.a(paramUri, paramPackageManager) != null;
  }
  
  public static ProviderInfo b(Uri paramUri, PackageManager paramPackageManager)
  {
    return h.a(paramUri, paramPackageManager);
  }
  
  private List<PackageInfo> c()
  {
    try
    {
      List localList = this.b.getInstalledPackages(0);
      return localList;
    }
    catch (Exception localException)
    {
      a(localException);
    }
    return this.b.getInstalledPackages(0);
  }
  
  private boolean d(String paramString)
  {
    return c.a(this.f, paramString, false);
  }
  
  private int e(String paramString)
  {
    try
    {
      int i = this.b.checkSignatures(this.d, paramString);
      return i;
    }
    catch (Exception localException)
    {
      a(localException);
    }
    return this.b.checkSignatures(this.d, paramString);
  }
  
  final PackageInfo a(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return a(paramString, paramInt, this.b);
  }
  
  public final ServiceInfo a(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    try
    {
      try
      {
        ServiceInfo localServiceInfo = this.b.getServiceInfo(paramComponentName, 128);
        paramComponentName = localServiceInfo;
      }
      catch (Exception localException)
      {
        a(localException);
        paramComponentName = this.b.getServiceInfo(paramComponentName, 128);
      }
      if (paramComponentName == null) {
        return null;
      }
      if (!a(paramComponentName.packageName))
      {
        aw.c(a, "Cannot get ServiceInfo from package %s since it is not signed with the Amazon Cert.", new Object[] { paramComponentName.packageName });
        return null;
      }
      return paramComponentName;
    }
    catch (PackageManager.NameNotFoundException paramComponentName)
    {
      throw paramComponentName;
    }
  }
  
  public final List<ProviderInfo> a()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = b().iterator();
    for (;;)
    {
      if (localIterator.hasNext())
      {
        Object localObject = (String)localIterator.next();
        try
        {
          localObject = a((String)localObject, 8);
          if ((localObject != null) && (((PackageInfo)localObject).providers != null))
          {
            localObject = ((PackageInfo)localObject).providers;
            int j = localObject.length;
            int i = 0;
            while (i < j)
            {
              ProviderInfo localProviderInfo = localObject[i];
              if (a(localProviderInfo)) {
                localArrayList.add(localProviderInfo);
              }
              i += 1;
            }
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          aw.b(a, "Caught NameNotFoundException querying for package that existed a moment ago", localNameNotFoundException);
        }
      }
    }
    return localArrayList;
  }
  
  public final List<ResolveInfo> a(Intent paramIntent)
  {
    try
    {
      List localList = this.b.queryIntentActivities(paramIntent, 0);
      paramIntent = localList;
    }
    catch (Exception localException)
    {
      a(localException);
      paramIntent = this.b.queryIntentActivities(paramIntent, 0);
    }
    ArrayList localArrayList = new ArrayList();
    paramIntent = paramIntent.iterator();
    while (paramIntent.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramIntent.next();
      if (d(localResolveInfo.activityInfo.packageName)) {
        localArrayList.add(localResolveInfo);
      }
    }
    return localArrayList;
  }
  
  public final List<ResolveInfo> a(Intent paramIntent, int paramInt)
  {
    try
    {
      List localList = this.b.queryIntentServices(paramIntent, paramInt);
      paramIntent = localList;
    }
    catch (Exception localException)
    {
      a(localException);
      paramIntent = this.b.queryIntentServices(paramIntent, paramInt);
    }
    ArrayList localArrayList = new ArrayList();
    paramIntent = paramIntent.iterator();
    while (paramIntent.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramIntent.next();
      if (d(localResolveInfo.serviceInfo.packageName)) {
        localArrayList.add(localResolveInfo);
      }
    }
    return localArrayList;
  }
  
  public final boolean a(String paramString)
  {
    return c.a(this.f, paramString, true);
  }
  
  public final int b(String paramString)
  {
    if ((this.d.equals(paramString)) && (!t.a())) {
      return 0;
    }
    if (this.e) {
      return -3;
    }
    if (e(paramString) == 0) {
      return 0;
    }
    if (this.g == null) {
      return -3;
    }
    try
    {
      paramString = a(paramString, 64).signatures;
      Set localSet = this.g;
      if (localSet != null)
      {
        int j = paramString.length;
        i = 0;
        while (i < j)
        {
          boolean bool = localSet.contains(paramString[i]);
          if (bool)
          {
            i = 1;
            break label112;
          }
          i += 1;
        }
      }
      int i = 0;
      label112:
      if (i == 0) {
        return -3;
      }
      return 0;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;) {}
    }
    return -4;
  }
  
  public final Set<String> b()
  {
    Object localObject = c();
    HashSet localHashSet = new HashSet();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (d(localPackageInfo.packageName)) {
        localHashSet.add(localPackageInfo.packageName);
      }
    }
    return localHashSet;
  }
  
  public final Resources c(String paramString)
    throws PackageManager.NameNotFoundException
  {
    if (d(paramString)) {
      try
      {
        Resources localResources = this.b.getResourcesForApplication(paramString);
        return localResources;
      }
      catch (Exception localException)
      {
        a(localException);
        return this.b.getResourcesForApplication(paramString);
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        throw paramString;
      }
    }
    return null;
  }
}
