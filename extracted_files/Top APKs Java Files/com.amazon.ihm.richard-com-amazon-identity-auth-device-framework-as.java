package com.amazon.identity.auth.device.framework;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.net.Uri;
import android.os.Binder;
import android.os.Process;
import com.amazon.identity.auth.device.framework.security.g;
import com.amazon.identity.auth.device.framework.security.h;
import com.amazon.identity.auth.device.utils.at;
import com.amazon.identity.auth.device.utils.z;
import com.amazon.identity.platform.metric.b;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class as
{
  private static final String TAG = as.class.getName();
  private static volatile g kx;
  private final boolean kA;
  private volatile Set<Signature> kB;
  private final String ky;
  private final PackageManager kz;
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
    if (kx == null)
    {
      z.R(TAG, "Trying to use default signature based package trust logic. This should be on 3P device");
      a(new h());
    }
    this.mContext = paramContext;
    this.ky = paramContext.getPackageName();
    this.kz = paramContext.getPackageManager();
    if (paramBoolean)
    {
      this.kB = TrustedAppUtils.b(paramContext, this.kz);
      this.kA = false;
      return;
    }
    this.kB = TrustedAppUtils.a(paramContext, this.kz);
    this.kA = IsolatedModeSwitcher.isAppInStaticIsolatedMode(paramContext);
  }
  
  private int a(int paramInt1, int paramInt2)
  {
    try
    {
      int i = this.kz.checkSignatures(paramInt1, paramInt2);
      return i;
    }
    catch (Exception localException)
    {
      a(localException);
    }
    return this.kz.checkSignatures(paramInt1, paramInt2);
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
  
  public static ProviderInfo a(Uri paramUri, PackageManager paramPackageManager)
  {
    return ab.a(paramUri, paramPackageManager);
  }
  
  public static void a(g paramG)
  {
    try
    {
      kx = paramG;
      z.R(TAG, "Setting package trust logic as: " + paramG.getDescription());
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
    z.b(TAG, "PackageManager call failed; retrying", paramException);
    b.a("PackageManagerError", new String[0]);
  }
  
  public static boolean a(ProviderInfo paramProviderInfo)
  {
    return (paramProviderInfo != null) && (paramProviderInfo.enabled) && (paramProviderInfo.applicationInfo != null) && (paramProviderInfo.applicationInfo.enabled);
  }
  
  private PackageInfo b(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return a(paramString, paramInt, this.kz);
  }
  
  public static boolean b(Uri paramUri, PackageManager paramPackageManager)
  {
    return ab.a(paramUri, paramPackageManager) != null;
  }
  
  private boolean bj(String paramString)
  {
    return kx.a(this.mContext, paramString, false);
  }
  
  private boolean bn(String paramString)
    throws PackageManager.NameNotFoundException
  {
    boolean bool2 = false;
    paramString = b(paramString, 64).signatures;
    Set localSet = this.kB;
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
  
  private int bo(String paramString)
  {
    try
    {
      int i = this.kz.checkSignatures(this.ky, paramString);
      return i;
    }
    catch (Exception localException)
    {
      a(localException);
    }
    return this.kz.checkSignatures(this.ky, paramString);
  }
  
  private List<PackageInfo> dD()
  {
    try
    {
      List localList = this.kz.getInstalledPackages(0);
      return localList;
    }
    catch (Exception localException)
    {
      a(localException);
    }
    return this.kz.getInstalledPackages(0);
  }
  
  public static boolean g(Context paramContext, String paramString)
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
  
  public ServiceInfo a(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    try
    {
      ServiceInfo localServiceInfo = this.kz.getServiceInfo(paramComponentName, 128);
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
        paramComponentName = this.kz.getServiceInfo(paramComponentName, 128);
      }
      if (bk(paramComponentName.packageName)) {
        return paramComponentName;
      }
      z.c(TAG, "Cannot get ServiceInfo from package %s since it is not signed with the Amazon Cert.", new Object[] { paramComponentName.packageName });
      return null;
    }
    if (paramComponentName == null) {
      return null;
    }
    return paramComponentName;
  }
  
  public XmlResourceParser a(PackageItemInfo paramPackageItemInfo)
  {
    if (paramPackageItemInfo == null)
    {
      z.T(TAG, "PackageItemInfo cannot be null in getParserForPackage");
      return null;
    }
    return paramPackageItemInfo.loadXmlMetaData(this.kz, "com.amazon.dcp.sso.AccountSubAuthenticator");
  }
  
  public ProviderInfo b(Uri paramUri)
  {
    paramUri = ab.a(paramUri, this.kz);
    if (paramUri == null) {
      return null;
    }
    if (!bk(paramUri.packageName))
    {
      z.T(TAG, String.format("Package %s does not qualify as a trusted package.", new Object[] { paramUri.packageName }));
      return null;
    }
    return paramUri;
  }
  
  public boolean bk(String paramString)
  {
    return kx.a(this.mContext, paramString, true);
  }
  
  public int bl(String paramString)
  {
    if ((this.ky.equals(paramString)) && (!at.fE())) {}
    for (;;)
    {
      return 0;
      if (this.kA) {
        return -3;
      }
      if (bo(paramString) != 0)
      {
        if (this.kB == null) {
          return -3;
        }
        try
        {
          boolean bool = bn(paramString);
          if (!bool) {
            return -3;
          }
        }
        catch (PackageManager.NameNotFoundException paramString) {}
      }
    }
    return -4;
  }
  
  public PackageInfo bm(String paramString)
    throws PackageManager.NameNotFoundException, SecurityException
  {
    if (bk(paramString)) {
      return b(paramString, 8);
    }
    throw new SecurityException(paramString + " is not trusted");
  }
  
  public List<ProviderInfo> dC()
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
          z.b(TAG, "Caught NameNotFoundException querying for package that existed a moment ago", localNameNotFoundException);
        }
      }
    }
    return localArrayList;
  }
  
  public String dE()
  {
    return this.mContext.getPackageManager().getNameForUid(Binder.getCallingUid());
  }
  
  public List<ResolveInfo> e(Intent paramIntent)
  {
    try
    {
      localObject = this.kz.queryIntentActivities(paramIntent, 0);
      paramIntent = (Intent)localObject;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject;
        a(localException);
        paramIntent = this.kz.queryIntentActivities(paramIntent, 0);
      }
      return localException;
    }
    localObject = new ArrayList();
    paramIntent = paramIntent.iterator();
    while (paramIntent.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramIntent.next();
      if (bj(localResolveInfo.activityInfo.packageName)) {
        ((List)localObject).add(localResolveInfo);
      }
    }
  }
  
  public Resources getResourcesForApplication(String paramString)
    throws PackageManager.NameNotFoundException
  {
    Resources localResources = null;
    if (bj(paramString)) {}
    try
    {
      localResources = this.kz.getResourcesForApplication(paramString);
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
    return this.kz.getResourcesForApplication(paramString);
  }
  
  public Set<String> getTrustedInstalledPackages()
  {
    Object localObject = dD();
    HashSet localHashSet = new HashSet();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (bj(localPackageInfo.packageName)) {
        localHashSet.add(localPackageInfo.packageName);
      }
    }
    return localHashSet;
  }
  
  public boolean j(int paramInt)
  {
    int j = Process.myUid();
    if (at.fE()) {
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
      if (this.kA)
      {
        z.cJ(TAG);
        return false;
      }
      k = a(paramInt, j);
    } while (k == 0);
    if (this.kB == null) {
      return false;
    }
    String[] arrayOfString2;
    int m;
    int i;
    try
    {
      String[] arrayOfString1 = this.kz.getPackagesForUid(paramInt);
      if (arrayOfString1 == null)
      {
        z.T(TAG, "Package name not found for uid : " + paramInt);
        return false;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        a(localException);
        arrayOfString2 = this.kz.getPackagesForUid(paramInt);
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
        bool = bn(str);
        if (bool)
        {
          bool = true;
          if (!bool) {
            z.T(TAG, String.format("Other uid %d %s and my uid %d are do not have matching signatures (result: %d). The trusted app check also failed.", new Object[] { Integer.valueOf(paramInt), Arrays.toString(arrayOfString2), Integer.valueOf(j), Integer.valueOf(k) }));
          }
          return bool;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        z.T(TAG, "Package name not found " + str);
        i += 1;
      }
      break;
      boolean bool = false;
    }
  }
  
  public List<ResolveInfo> queryIntentServices(Intent paramIntent, int paramInt)
  {
    try
    {
      localObject = this.kz.queryIntentServices(paramIntent, paramInt);
      paramIntent = (Intent)localObject;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject;
        a(localException);
        paramIntent = this.kz.queryIntentServices(paramIntent, paramInt);
      }
      return localException;
    }
    localObject = new ArrayList();
    paramIntent = paramIntent.iterator();
    while (paramIntent.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramIntent.next();
      if (bj(localResolveInfo.serviceInfo.packageName)) {
        ((List)localObject).add(localResolveInfo);
      }
    }
  }
}
