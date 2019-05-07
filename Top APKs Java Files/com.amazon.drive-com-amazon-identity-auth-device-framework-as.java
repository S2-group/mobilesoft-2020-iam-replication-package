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
import android.os.Process;
import com.amazon.identity.auth.device.framework.security.g;
import com.amazon.identity.auth.device.framework.security.h;
import com.amazon.identity.platform.metric.b;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class as
{
  public static final String TAG = as.class.getName();
  private static volatile g kp;
  private final String kq;
  public final PackageManager kr;
  private final boolean ks;
  private volatile Set<Signature> kt;
  private final Context mContext;
  
  public as(Context paramContext)
  {
    this(paramContext, false);
  }
  
  public as(Context paramContext, boolean paramBoolean)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("context cannot be null");
    }
    if (kp == null)
    {
      com.amazon.identity.auth.device.utils.z.S(TAG, "Trying to use default signature based package trust logic. This should be on 3P device");
      a(new h());
    }
    this.mContext = paramContext;
    this.kq = paramContext.getPackageName();
    this.kr = paramContext.getPackageManager();
    if (paramBoolean)
    {
      this.kt = TrustedAppUtils.b(paramContext, this.kr);
      this.ks = false;
      return;
    }
    this.kt = TrustedAppUtils.a(paramContext, this.kr);
    this.ks = z.C(paramContext);
  }
  
  private int a(int paramInt1, int paramInt2)
  {
    try
    {
      int i = this.kr.checkSignatures(paramInt1, paramInt2);
      return i;
    }
    catch (Exception localException)
    {
      a(localException);
    }
    return this.kr.checkSignatures(paramInt1, paramInt2);
  }
  
  public static PackageInfo a(String paramString, int paramInt, PackageManager paramPackageManager)
    throws PackageManager.NameNotFoundException
  {
    try
    {
      PackageInfo localPackageInfo = paramPackageManager.getPackageInfo(paramString, paramInt);
      return localPackageInfo;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      throw paramString;
    }
    catch (Exception localException)
    {
      a(localException);
    }
    return paramPackageManager.getPackageInfo(paramString, paramInt);
  }
  
  private static void a(g paramG)
  {
    try
    {
      kp = paramG;
      com.amazon.identity.auth.device.utils.z.S(TAG, "Setting package trust logic as: " + paramG.getDescription());
      return;
    }
    finally
    {
      paramG = finally;
      throw paramG;
    }
  }
  
  private static void a(Exception paramException)
  {
    com.amazon.identity.auth.device.utils.z.b(TAG, "PackageManager call failed; retrying", paramException);
    b.a("PackageManagerError", new String[0]);
  }
  
  public static boolean a(ProviderInfo paramProviderInfo)
  {
    return (paramProviderInfo != null) && (paramProviderInfo.enabled) && (paramProviderInfo.applicationInfo != null) && (paramProviderInfo.applicationInfo.enabled);
  }
  
  public static boolean a(Uri paramUri, PackageManager paramPackageManager)
  {
    return b(paramUri, paramPackageManager) != null;
  }
  
  public static ProviderInfo b(Uri paramUri, PackageManager paramPackageManager)
  {
    try
    {
      ProviderInfo localProviderInfo = paramPackageManager.resolveContentProvider(paramUri.getAuthority(), 0);
      return localProviderInfo;
    }
    catch (Exception localException)
    {
      a(localException);
    }
    return paramPackageManager.resolveContentProvider(paramUri.getAuthority(), 0);
  }
  
  private boolean be(String paramString)
  {
    return kp.a(this.mContext, paramString, false);
  }
  
  private boolean bi(String paramString)
    throws PackageManager.NameNotFoundException
  {
    boolean bool2 = false;
    paramString = b(paramString, 64).signatures;
    Set localSet = this.kt;
    boolean bool1 = bool2;
    int j;
    int i;
    if (localSet != null)
    {
      j = paramString.length;
      i = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < j)
      {
        if (localSet.contains(paramString[i])) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  private int bj(String paramString)
  {
    try
    {
      int i = this.kr.checkSignatures(this.kq, paramString);
      return i;
    }
    catch (Exception localException)
    {
      a(localException);
    }
    return this.kr.checkSignatures(this.kq, paramString);
  }
  
  private List<PackageInfo> dD()
  {
    try
    {
      List localList = this.kr.getInstalledPackages(0);
      return localList;
    }
    catch (Exception localException)
    {
      a(localException);
    }
    return this.kr.getInstalledPackages(0);
  }
  
  public static boolean h(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      a(paramString, 64, paramContext);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public final ServiceInfo a(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    try
    {
      ServiceInfo localServiceInfo = this.kr.getServiceInfo(paramComponentName, 128);
      paramComponentName = localServiceInfo;
    }
    catch (PackageManager.NameNotFoundException paramComponentName)
    {
      throw paramComponentName;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        a(localException);
        paramComponentName = this.kr.getServiceInfo(paramComponentName, 128);
      }
      if (bf(paramComponentName.packageName)) {
        return paramComponentName;
      }
      com.amazon.identity.auth.device.utils.z.c(TAG, "Cannot get ServiceInfo from package %s since it is not signed with the Amazon Cert.", new Object[] { paramComponentName.packageName });
      return null;
    }
    if (paramComponentName == null) {
      return null;
    }
    return paramComponentName;
  }
  
  final PackageInfo b(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return a(paramString, paramInt, this.kr);
  }
  
  public final boolean bf(String paramString)
  {
    return kp.a(this.mContext, paramString, true);
  }
  
  public final int bg(String paramString)
  {
    if ((this.kq.equals(paramString)) && (!com.amazon.identity.auth.device.utils.as.fw())) {}
    for (;;)
    {
      return 0;
      if (this.ks) {
        return -3;
      }
      if (bj(paramString) != 0)
      {
        if (this.kt == null) {
          return -3;
        }
        try
        {
          boolean bool = bi(paramString);
          if (!bool) {
            return -3;
          }
        }
        catch (PackageManager.NameNotFoundException paramString) {}
      }
    }
    return -4;
  }
  
  public final List<ProviderInfo> dC()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = getTrustedInstalledPackages().iterator();
    for (;;)
    {
      if (localIterator.hasNext())
      {
        Object localObject = (String)localIterator.next();
        try
        {
          localObject = b((String)localObject, 8);
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
          com.amazon.identity.auth.device.utils.z.b(TAG, "Caught NameNotFoundException querying for package that existed a moment ago", localNameNotFoundException);
        }
      }
    }
    return localArrayList;
  }
  
  public final List<ResolveInfo> e(Intent paramIntent)
  {
    try
    {
      localObject = this.kr.queryIntentActivities(paramIntent, 0);
      paramIntent = (Intent)localObject;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject;
        a(localException);
        paramIntent = this.kr.queryIntentActivities(paramIntent, 0);
      }
      return localException;
    }
    localObject = new ArrayList();
    paramIntent = paramIntent.iterator();
    while (paramIntent.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramIntent.next();
      if (be(localResolveInfo.activityInfo.packageName)) {
        ((List)localObject).add(localResolveInfo);
      }
    }
  }
  
  public final Resources getResourcesForApplication(String paramString)
    throws PackageManager.NameNotFoundException
  {
    Resources localResources = null;
    if (be(paramString)) {}
    try
    {
      localResources = this.kr.getResourcesForApplication(paramString);
      return localResources;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      throw paramString;
    }
    catch (Exception localException)
    {
      a(localException);
    }
    return this.kr.getResourcesForApplication(paramString);
  }
  
  public final Set<String> getTrustedInstalledPackages()
  {
    Object localObject = dD();
    HashSet localHashSet = new HashSet();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (be(localPackageInfo.packageName)) {
        localHashSet.add(localPackageInfo.packageName);
      }
    }
    return localHashSet;
  }
  
  public final boolean j(int paramInt)
  {
    int j = Process.myUid();
    if (com.amazon.identity.auth.device.utils.as.fw()) {
      if (a(paramInt, j) != 0) {}
    }
    int k;
    do
    {
      do
      {
        return true;
        return false;
      } while (paramInt == j);
      if (this.ks) {
        return false;
      }
      k = a(paramInt, j);
    } while (k == 0);
    if (this.kt == null) {
      return false;
    }
    String[] arrayOfString2;
    int m;
    int i;
    try
    {
      String[] arrayOfString1 = this.kr.getPackagesForUid(paramInt);
      if (arrayOfString1 == null)
      {
        com.amazon.identity.auth.device.utils.z.U(TAG, "Package name not found for uid : " + paramInt);
        return false;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        a(localException);
        arrayOfString2 = this.kr.getPackagesForUid(paramInt);
      }
      m = arrayOfString2.length;
      i = 0;
    }
    String str;
    if (i < m) {
      str = arrayOfString2[i];
    }
    for (;;)
    {
      try
      {
        bool = bi(str);
        if (bool)
        {
          bool = true;
          if (!bool) {
            com.amazon.identity.auth.device.utils.z.U(TAG, String.format("Other uid %d %s and my uid %d are do not have matching signatures (result: %d). The trusted app check also failed.", new Object[] { Integer.valueOf(paramInt), Arrays.toString(arrayOfString2), Integer.valueOf(j), Integer.valueOf(k) }));
          }
          return bool;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        com.amazon.identity.auth.device.utils.z.U(TAG, "Package name not found " + str);
        i += 1;
      }
      break;
      boolean bool = false;
    }
  }
  
  public final List<ResolveInfo> queryIntentServices(Intent paramIntent, int paramInt)
  {
    try
    {
      localObject = this.kr.queryIntentServices(paramIntent, paramInt);
      paramIntent = (Intent)localObject;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject;
        a(localException);
        paramIntent = this.kr.queryIntentServices(paramIntent, paramInt);
      }
      return localException;
    }
    localObject = new ArrayList();
    paramIntent = paramIntent.iterator();
    while (paramIntent.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramIntent.next();
      if (be(localResolveInfo.serviceInfo.packageName)) {
        ((List)localObject).add(localResolveInfo);
      }
    }
  }
}
