package com.amazon.identity.auth.device.framework;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
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

public class ar
{
  private static final String TAG = ar.class.getName();
  private static volatile g ku;
  private final String kv;
  private final PackageManager kw;
  private final boolean kx;
  private volatile Set<Signature> ky;
  private final Context mContext;
  
  public ar(Context paramContext)
  {
    this(paramContext, false);
  }
  
  public ar(Context paramContext, boolean paramBoolean)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("context cannot be null");
    }
    if (ku == null)
    {
      z.S(TAG, "Trying to use default signature based package trust logic. This should be on 3P device");
      a(new h());
    }
    this.mContext = paramContext;
    this.kv = paramContext.getPackageName();
    this.kw = paramContext.getPackageManager();
    if (paramBoolean)
    {
      this.ky = TrustedAppUtils.b(paramContext, this.kw);
      this.kx = false;
      return;
    }
    this.ky = TrustedAppUtils.a(paramContext, this.kw);
    this.kx = IsolatedModeSwitcher.isAppInStaticIsolatedMode(paramContext);
  }
  
  private int a(int paramInt1, int paramInt2)
  {
    try
    {
      int i = this.kw.checkSignatures(paramInt1, paramInt2);
      return i;
    }
    catch (Exception localException)
    {
      a(localException);
    }
    return this.kw.checkSignatures(paramInt1, paramInt2);
  }
  
  public static PackageInfo a(String paramString, int paramInt, PackageManager paramPackageManager)
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
  
  public static void a(g paramG)
  {
    try
    {
      ku = paramG;
      z.S(TAG, "Setting package trust logic as: " + paramG.getDescription());
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
    z.b(TAG, "PackageManager call failed; retrying", paramException);
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
  
  private PackageInfo b(String paramString, int paramInt)
  {
    return a(paramString, paramInt, this.kw);
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
  
  private boolean bf(String paramString)
  {
    return ku.a(this.mContext, paramString, false);
  }
  
  private boolean bj(String paramString)
  {
    boolean bool2 = false;
    paramString = b(paramString, 64).signatures;
    Set localSet = this.ky;
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
  
  private int bk(String paramString)
  {
    try
    {
      int i = this.kw.checkSignatures(this.kv, paramString);
      return i;
    }
    catch (Exception localException)
    {
      a(localException);
    }
    return this.kw.checkSignatures(this.kv, paramString);
  }
  
  private List<PackageInfo> dw()
  {
    try
    {
      List localList = this.kw.getInstalledPackages(0);
      return localList;
    }
    catch (Exception localException)
    {
      a(localException);
    }
    return this.kw.getInstalledPackages(0);
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
  {
    try
    {
      ServiceInfo localServiceInfo = this.kw.getServiceInfo(paramComponentName, 128);
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
        paramComponentName = this.kw.getServiceInfo(paramComponentName, 128);
      }
      if (bg(paramComponentName.packageName)) {
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
      z.U(TAG, "PackageItemInfo cannot be null in getParserForPackage");
      return null;
    }
    return paramPackageItemInfo.loadXmlMetaData(this.kw, "com.amazon.dcp.sso.AccountSubAuthenticator");
  }
  
  public ProviderInfo b(Uri paramUri)
  {
    paramUri = b(paramUri, this.kw);
    if (paramUri == null) {
      return null;
    }
    if (!bg(paramUri.packageName))
    {
      z.U(TAG, String.format("Package %s does not qualify as a trusted package.", new Object[] { paramUri.packageName }));
      return null;
    }
    return paramUri;
  }
  
  public boolean bg(String paramString)
  {
    return ku.a(this.mContext, paramString, true);
  }
  
  public int bh(String paramString)
  {
    if ((this.kv.equals(paramString)) && (!at.fw())) {}
    for (;;)
    {
      return 0;
      if (this.kx) {
        return -3;
      }
      if (bk(paramString) != 0)
      {
        if (this.ky == null) {
          return -3;
        }
        try
        {
          boolean bool = bj(paramString);
          if (!bool) {
            return -3;
          }
        }
        catch (PackageManager.NameNotFoundException paramString) {}
      }
    }
    return -4;
  }
  
  public PackageInfo bi(String paramString)
  {
    if (bg(paramString)) {
      return b(paramString, 8);
    }
    throw new SecurityException(paramString + " is not trusted");
  }
  
  public List<ProviderInfo> dv()
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
  
  public String dx()
  {
    return this.mContext.getPackageManager().getNameForUid(Binder.getCallingUid());
  }
  
  public Resources getResourcesForApplication(String paramString)
  {
    Resources localResources = null;
    if (bf(paramString)) {}
    try
    {
      localResources = this.kw.getResourcesForApplication(paramString);
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
    return this.kw.getResourcesForApplication(paramString);
  }
  
  public Set<String> getTrustedInstalledPackages()
  {
    Object localObject = dw();
    HashSet localHashSet = new HashSet();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (bf(localPackageInfo.packageName)) {
        localHashSet.add(localPackageInfo.packageName);
      }
    }
    return localHashSet;
  }
  
  public boolean j(int paramInt)
  {
    int j = Process.myUid();
    if (at.fw()) {
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
      if (this.kx)
      {
        z.cH(TAG);
        return false;
      }
      k = a(paramInt, j);
    } while (k == 0);
    if (this.ky == null) {
      return false;
    }
    String[] arrayOfString2;
    int m;
    int i;
    try
    {
      String[] arrayOfString1 = this.kw.getPackagesForUid(paramInt);
      if (arrayOfString1 == null)
      {
        z.U(TAG, "Package name not found for uid : " + paramInt);
        return false;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        a(localException);
        arrayOfString2 = this.kw.getPackagesForUid(paramInt);
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
        bool = bj(str);
        if (bool)
        {
          bool = true;
          if (!bool) {
            z.U(TAG, String.format("Other uid %d %s and my uid %d are do not have matching signatures (result: %d). The trusted app check also failed.", new Object[] { Integer.valueOf(paramInt), Arrays.toString(arrayOfString2), Integer.valueOf(j), Integer.valueOf(k) }));
          }
          return bool;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        z.U(TAG, "Package name not found " + str);
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
      localObject = this.kw.queryIntentServices(paramIntent, paramInt);
      paramIntent = (Intent)localObject;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject;
        a(localException);
        paramIntent = this.kw.queryIntentServices(paramIntent, paramInt);
      }
      return localException;
    }
    localObject = new ArrayList();
    paramIntent = paramIntent.iterator();
    while (paramIntent.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramIntent.next();
      if (bf(localResolveInfo.serviceInfo.packageName)) {
        ((List)localObject).add(localResolveInfo);
      }
    }
  }
}
