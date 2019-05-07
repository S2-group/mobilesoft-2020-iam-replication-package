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
import android.os.Process;
import com.amazon.identity.a.a.b;
import com.amazon.identity.auth.device.utils.av;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class bb
{
  private static final String a = "com.amazon.identity.auth.device.framework.bb";
  private final boolean b;
  private final String c;
  private final PackageManager d;
  private volatile Set<Signature> e;
  
  public bb(Context paramContext)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("context cannot be null");
    }
    this.c = paramContext.getPackageName();
    this.d = paramContext.getPackageManager();
    this.e = ba.a(paramContext, this.d);
    this.b = ac.d(paramContext);
  }
  
  private int a(int paramInt1, int paramInt2)
  {
    try
    {
      int i = this.d.checkSignatures(paramInt1, paramInt2);
      return i;
    }
    catch (Exception localException)
    {
      a(localException);
    }
    return this.d.checkSignatures(paramInt1, paramInt2);
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
  
  private static void a(Exception paramException)
  {
    com.amazon.identity.auth.device.utils.ac.c(a, "PackageManager call failed; retrying", paramException);
    b.a("PackageManagerError", new String[0]);
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
    return b(paramUri, paramPackageManager) != null;
  }
  
  private PackageInfo b(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return a(paramString, paramInt, this.d);
  }
  
  private static ProviderInfo b(Uri paramUri, PackageManager paramPackageManager)
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
  
  private int c(String paramString)
  {
    if ((!this.c.equals(paramString)) || (av.a()))
    {
      if (this.b) {
        return -3;
      }
      if (d(paramString) != 0) {
        if (this.e == null) {
          return -3;
        }
      }
    }
    try
    {
      boolean bool = e(paramString);
      if (bool) {
        break label64;
      }
      return -3;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      label64:
      for (;;) {}
    }
    return -4;
    return 0;
  }
  
  private List<PackageInfo> c()
  {
    try
    {
      List localList = this.d.getInstalledPackages(0);
      return localList;
    }
    catch (Exception localException)
    {
      a(localException);
    }
    return this.d.getInstalledPackages(0);
  }
  
  private int d(String paramString)
  {
    try
    {
      int i = this.d.checkSignatures(this.c, paramString);
      return i;
    }
    catch (Exception localException)
    {
      a(localException);
    }
    return this.d.checkSignatures(this.c, paramString);
  }
  
  private boolean e(String paramString)
    throws PackageManager.NameNotFoundException
  {
    paramString = b(paramString, 64).signatures;
    Set localSet = this.e;
    if (localSet != null)
    {
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        if (localSet.contains(paramString[i])) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public PackageInfo a(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException, SecurityException
  {
    int i = c(paramString);
    if (i == 0) {
      return b(paramString, paramInt);
    }
    if (i == -4) {
      throw new PackageManager.NameNotFoundException();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(" is not trusted");
    throw new SecurityException(localStringBuilder.toString());
  }
  
  public ProviderInfo a(Uri paramUri)
  {
    paramUri = b(paramUri, this.d);
    if (paramUri == null) {
      return null;
    }
    if (!b(paramUri.packageName))
    {
      com.amazon.identity.auth.device.utils.ac.a(a, String.format("Package %s does not qualify as a trusted package.", new Object[] { paramUri.packageName }));
      return null;
    }
    return paramUri;
  }
  
  public ServiceInfo a(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    try
    {
      try
      {
        ServiceInfo localServiceInfo = this.d.getServiceInfo(paramComponentName, 128);
        paramComponentName = localServiceInfo;
      }
      catch (Exception localException)
      {
        a(localException);
        paramComponentName = this.d.getServiceInfo(paramComponentName, 128);
      }
      if (paramComponentName == null) {
        return null;
      }
      if (!b(paramComponentName.packageName))
      {
        com.amazon.identity.auth.device.utils.ac.a(a, "Cannot get ServiceInfo from package %s since it is not signed with the Amazon Cert.", new Object[] { paramComponentName.packageName });
        return null;
      }
      return paramComponentName;
    }
    catch (PackageManager.NameNotFoundException paramComponentName)
    {
      throw paramComponentName;
    }
  }
  
  public Resources a(String paramString)
    throws PackageManager.NameNotFoundException
  {
    if (b(paramString)) {
      try
      {
        Resources localResources = this.d.getResourcesForApplication(paramString);
        return localResources;
      }
      catch (Exception localException)
      {
        a(localException);
        return this.d.getResourcesForApplication(paramString);
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        throw paramString;
      }
    }
    return null;
  }
  
  public XmlResourceParser a(PackageItemInfo paramPackageItemInfo)
  {
    if (paramPackageItemInfo == null)
    {
      com.amazon.identity.auth.device.utils.ac.a(a, "PackageItemInfo cannot be null in getParserForPackage");
      return null;
    }
    return paramPackageItemInfo.loadXmlMetaData(this.d, "com.amazon.dcp.sso.AccountSubAuthenticator");
  }
  
  public List<ResolveInfo> a(Intent paramIntent)
  {
    try
    {
      List localList = this.d.queryIntentActivities(paramIntent, 0);
      paramIntent = localList;
    }
    catch (Exception localException)
    {
      a(localException);
      paramIntent = this.d.queryIntentActivities(paramIntent, 0);
    }
    ArrayList localArrayList = new ArrayList();
    paramIntent = paramIntent.iterator();
    while (paramIntent.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramIntent.next();
      if (b(localResolveInfo.activityInfo.packageName)) {
        localArrayList.add(localResolveInfo);
      }
    }
    return localArrayList;
  }
  
  public List<ResolveInfo> a(Intent paramIntent, int paramInt)
  {
    try
    {
      List localList = this.d.queryIntentServices(paramIntent, paramInt);
      paramIntent = localList;
    }
    catch (Exception localException)
    {
      a(localException);
      paramIntent = this.d.queryIntentServices(paramIntent, paramInt);
    }
    ArrayList localArrayList = new ArrayList();
    paramIntent = paramIntent.iterator();
    while (paramIntent.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramIntent.next();
      if (b(localResolveInfo.serviceInfo.packageName)) {
        localArrayList.add(localResolveInfo);
      }
    }
    return localArrayList;
  }
  
  public Set<String> a()
  {
    Object localObject = c();
    HashSet localHashSet = new HashSet();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (b(localPackageInfo.packageName)) {
        localHashSet.add(localPackageInfo.packageName);
      }
    }
    return localHashSet;
  }
  
  public boolean a(int paramInt)
  {
    int j = Process.myUid();
    if (av.a()) {
      return a(paramInt, j) == 0;
    }
    if (paramInt == j) {
      return true;
    }
    if (this.b) {
      return false;
    }
    int k = a(paramInt, j);
    if (k == 0) {
      return true;
    }
    if (this.e == null) {
      return false;
    }
    Object localObject;
    try
    {
      String[] arrayOfString = this.d.getPackagesForUid(paramInt);
    }
    catch (Exception localException)
    {
      a(localException);
      localObject = this.d.getPackagesForUid(paramInt);
    }
    StringBuilder localStringBuilder1;
    if (localObject == null)
    {
      localObject = a;
      localStringBuilder1 = new StringBuilder("Package name not found for uid : ");
      localStringBuilder1.append(paramInt);
      com.amazon.identity.auth.device.utils.ac.a((String)localObject, localStringBuilder1.toString());
      return false;
    }
    int m = localObject.length;
    int i = 0;
    while (i < m)
    {
      localStringBuilder1 = localObject[i];
      try
      {
        bool = e(localStringBuilder1);
        if (!bool) {
          break label207;
        }
        bool = true;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        boolean bool;
        String str;
        StringBuilder localStringBuilder2;
        for (;;) {}
      }
      str = a;
      localStringBuilder2 = new StringBuilder("Package name not found ");
      localStringBuilder2.append(localStringBuilder1);
      com.amazon.identity.auth.device.utils.ac.a(str, localStringBuilder2.toString());
      label207:
      i += 1;
    }
    bool = false;
    if (!bool)
    {
      com.amazon.identity.auth.device.utils.ac.a(a, String.format("Other uid %d %s and my uid %d are do not have matching signatures (result: %d). The trusted app check also failed.", new Object[] { Integer.valueOf(paramInt), Arrays.toString((Object[])localObject), Integer.valueOf(j), Integer.valueOf(k) }));
      return bool;
    }
    return bool;
  }
  
  public List<ProviderInfo> b()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = a().iterator();
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
          com.amazon.identity.auth.device.utils.ac.c(a, "Caught NameNotFoundException querying for package that existed a moment ago", localNameNotFoundException);
        }
      }
    }
    return localArrayList;
  }
  
  public boolean b(String paramString)
  {
    return c(paramString) == 0;
  }
}
