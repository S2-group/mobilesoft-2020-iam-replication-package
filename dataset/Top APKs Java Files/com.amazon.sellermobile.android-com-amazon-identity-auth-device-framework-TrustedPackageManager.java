package com.amazon.identity.auth.device.framework;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import com.amazon.identity.auth.device.utils.MAPLog;
import com.amazon.identity.auth.device.utils.UnitTestUtils;
import com.amazon.identity.platform.metric.MetricsHelper;
import com.amazon.identity.platform.util.PlatformUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TrustedPackageManager
{
  private static final String TAG = TrustedPackageManager.class.getName();
  private final boolean mIsIsolatedApplication;
  private final String mMyPackageName;
  private final PackageManager mPackageManager;
  private volatile Set<Signature> mTrustedCerts;
  
  public TrustedPackageManager(Context paramContext)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("context cannot be null");
    }
    this.mMyPackageName = paramContext.getPackageName();
    this.mPackageManager = paramContext.getPackageManager();
    this.mTrustedCerts = TrustedAppUtils.getTrustedCerts(paramContext, this.mPackageManager);
    this.mIsIsolatedApplication = PlatformUtils.isIsolatedApplication(ServiceWrappingContext.create(paramContext));
  }
  
  private int checkSignature(String paramString)
  {
    if ((this.mMyPackageName.equals(paramString)) && (!UnitTestUtils.isRunningInUnitTest())) {}
    for (;;)
    {
      return 0;
      if (this.mIsIsolatedApplication) {
        return -3;
      }
      if (checkSignatureWithRetry(paramString) != 0)
      {
        if (this.mTrustedCerts == null) {
          return -3;
        }
        try
        {
          boolean bool = hasAtLeastOneTrustedSignature(paramString);
          if (!bool) {
            return -3;
          }
        }
        catch (PackageManager.NameNotFoundException paramString) {}
      }
    }
    return -4;
  }
  
  private int checkSignatureWithRetry(String paramString)
  {
    try
    {
      int i = this.mPackageManager.checkSignatures(this.mMyPackageName, paramString);
      return i;
    }
    catch (Exception localException)
    {
      logPackageManagerException(localException);
    }
    return this.mPackageManager.checkSignatures(this.mMyPackageName, paramString);
  }
  
  private int checkSignaturesWithRetry(int paramInt1, int paramInt2)
  {
    try
    {
      int i = this.mPackageManager.checkSignatures(paramInt1, paramInt2);
      return i;
    }
    catch (Exception localException)
    {
      logPackageManagerException(localException);
    }
    return this.mPackageManager.checkSignatures(paramInt1, paramInt2);
  }
  
  private List<PackageInfo> getInstalledPackagesWithRetry$22f3aa59()
  {
    try
    {
      List localList = this.mPackageManager.getInstalledPackages(0);
      return localList;
    }
    catch (Exception localException)
    {
      logPackageManagerException(localException);
    }
    return this.mPackageManager.getInstalledPackages(0);
  }
  
  private PackageInfo getPackageInfoWithRetry(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return getPackageInfoWithRetry(paramString, paramInt, this.mPackageManager);
  }
  
  public static PackageInfo getPackageInfoWithRetry(String paramString, int paramInt, PackageManager paramPackageManager)
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
      logPackageManagerException(localException);
    }
    return paramPackageManager.getPackageInfo(paramString, paramInt);
  }
  
  private boolean hasAtLeastOneTrustedSignature(String paramString)
    throws PackageManager.NameNotFoundException
  {
    boolean bool2 = false;
    paramString = getPackageInfoWithRetry(paramString, 64).signatures;
    Set localSet = this.mTrustedCerts;
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
  
  private static void logPackageManagerException(Exception paramException)
  {
    MAPLog.w(TAG, "PackageManager call failed; retrying", paramException);
    MetricsHelper.incrementCounter("PackageManagerError", new String[0]);
  }
  
  public static boolean unsecureIsContentProviderPresent$72171d0f(Uri paramUri, PackageManager paramPackageManager)
  {
    return unsecureResolveProviderWithRetry$7b050a83(paramUri, paramPackageManager) != null;
  }
  
  private static ProviderInfo unsecureResolveProviderWithRetry$7b050a83(Uri paramUri, PackageManager paramPackageManager)
  {
    try
    {
      ProviderInfo localProviderInfo = paramPackageManager.resolveContentProvider(paramUri.getAuthority(), 0);
      return localProviderInfo;
    }
    catch (Exception localException)
    {
      logPackageManagerException(localException);
    }
    return paramPackageManager.resolveContentProvider(paramUri.getAuthority(), 0);
  }
  
  public ProviderInfo getAmazonOwnedContentProvider$14505999(Uri paramUri)
  {
    ProviderInfo localProviderInfo = unsecureResolveProviderWithRetry$7b050a83(paramUri, this.mPackageManager);
    if (localProviderInfo == null) {
      paramUri = null;
    }
    do
    {
      return paramUri;
      paramUri = localProviderInfo;
    } while (isTrustedPackage(localProviderInfo.packageName));
    MAPLog.e(TAG, String.format("Package %s does not qualify as a trusted package.", new Object[] { localProviderInfo.packageName }));
    return null;
  }
  
  public PackageInfo getPackageInfoForTrustedPackage(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException, SecurityException
  {
    int i = checkSignature(paramString);
    if (i == 0) {
      return getPackageInfoWithRetry(paramString, paramInt);
    }
    if (i == -4) {
      throw new PackageManager.NameNotFoundException();
    }
    throw new SecurityException(paramString + " is not trusted");
  }
  
  public XmlResourceParser getParserForPackage$6d908fab(PackageItemInfo paramPackageItemInfo)
  {
    if (paramPackageItemInfo == null)
    {
      MAPLog.e(TAG, "PackageItemInfo cannot be null in getParserForPackage");
      return null;
    }
    return paramPackageItemInfo.loadXmlMetaData(this.mPackageManager, "com.amazon.dcp.sso.AccountSubAuthenticator");
  }
  
  public Resources getResourcesForApplication(String paramString)
    throws PackageManager.NameNotFoundException
  {
    Resources localResources = null;
    if (isTrustedPackage(paramString)) {}
    try
    {
      localResources = this.mPackageManager.getResourcesForApplication(paramString);
      return localResources;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      throw paramString;
    }
    catch (Exception localException)
    {
      logPackageManagerException(localException);
    }
    return this.mPackageManager.getResourcesForApplication(paramString);
  }
  
  public ServiceInfo getServiceInfo$42997c6b(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    try
    {
      localServiceInfo = this.mPackageManager.getServiceInfo(paramComponentName, 128);
      paramComponentName = localServiceInfo;
    }
    catch (PackageManager.NameNotFoundException paramComponentName)
    {
      ServiceInfo localServiceInfo;
      throw paramComponentName;
    }
    catch (Exception localException)
    {
      do
      {
        for (;;)
        {
          logPackageManagerException(localException);
          paramComponentName = this.mPackageManager.getServiceInfo(paramComponentName, 128);
        }
        ComponentName localComponentName = paramComponentName;
      } while (isTrustedPackage(paramComponentName.packageName));
      MAPLog.formattedError(TAG, "Cannot get ServiceInfo from package %s since it is not signed with the Amazon Cert.", new Object[] { paramComponentName.packageName });
    }
    if (paramComponentName == null)
    {
      localServiceInfo = null;
      return localServiceInfo;
    }
    return null;
  }
  
  public Set<String> getTrustedInstalledPackages()
  {
    Object localObject = getInstalledPackagesWithRetry$22f3aa59();
    HashSet localHashSet = new HashSet();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (isTrustedPackage(localPackageInfo.packageName)) {
        localHashSet.add(localPackageInfo.packageName);
      }
    }
    return localHashSet;
  }
  
  public boolean isTrustedPackage(String paramString)
  {
    return checkSignature(paramString) == 0;
  }
  
  public boolean isTrustedUid(int paramInt)
  {
    int j = Process.myUid();
    boolean bool2;
    if (UnitTestUtils.isRunningInUnitTest())
    {
      if (checkSignaturesWithRetry(paramInt, j) == 0)
      {
        bool2 = true;
        return bool2;
      }
      return false;
    }
    if (paramInt == j) {
      return true;
    }
    if (this.mIsIsolatedApplication) {
      return false;
    }
    int k = checkSignaturesWithRetry(paramInt, j);
    if (k == 0) {
      return true;
    }
    if (this.mTrustedCerts == null) {
      return false;
    }
    String[] arrayOfString2;
    int m;
    int i;
    try
    {
      String[] arrayOfString1 = this.mPackageManager.getPackagesForUid(paramInt);
      if (arrayOfString1 == null)
      {
        MAPLog.e(TAG, "Package name not found for uid : " + paramInt);
        return false;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        logPackageManagerException(localException);
        arrayOfString2 = this.mPackageManager.getPackagesForUid(paramInt);
      }
      bool2 = false;
      m = arrayOfString2.length;
      i = 0;
    }
    for (;;)
    {
      boolean bool1 = bool2;
      String str;
      if (i < m) {
        str = arrayOfString2[i];
      }
      try
      {
        bool1 = hasAtLeastOneTrustedSignature(str);
        if (bool1)
        {
          bool1 = true;
          bool2 = bool1;
          if (bool1) {
            break;
          }
          MAPLog.e(TAG, String.format("Other uid %d %s and my uid %d are do not have matching signatures (result: %d). The trusted app check also failed.", new Object[] { Integer.valueOf(paramInt), Arrays.toString(arrayOfString2), Integer.valueOf(j), Integer.valueOf(k) }));
          return bool1;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        MAPLog.e(TAG, "Package name not found " + str);
        i += 1;
      }
    }
  }
  
  public List<ResolveInfo> queryIntentActivities$46e5b6ea(Intent paramIntent)
  {
    try
    {
      localObject = this.mPackageManager.queryIntentActivities(paramIntent, 0);
      paramIntent = (Intent)localObject;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject;
        logPackageManagerException(localException);
        paramIntent = this.mPackageManager.queryIntentActivities(paramIntent, 0);
      }
      return localException;
    }
    localObject = new ArrayList();
    paramIntent = paramIntent.iterator();
    while (paramIntent.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramIntent.next();
      if (isTrustedPackage(localResolveInfo.activityInfo.packageName)) {
        ((List)localObject).add(localResolveInfo);
      }
    }
  }
  
  public List<ResolveInfo> queryIntentServices(Intent paramIntent, int paramInt)
  {
    try
    {
      localObject = this.mPackageManager.queryIntentServices(paramIntent, paramInt);
      paramIntent = (Intent)localObject;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject;
        logPackageManagerException(localException);
        paramIntent = this.mPackageManager.queryIntentServices(paramIntent, paramInt);
      }
      return localException;
    }
    localObject = new ArrayList();
    paramIntent = paramIntent.iterator();
    while (paramIntent.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramIntent.next();
      if (isTrustedPackage(localResolveInfo.serviceInfo.packageName)) {
        ((List)localObject).add(localResolveInfo);
      }
    }
  }
  
  public List<ProviderInfo> queryTrustedContentProviders()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = getTrustedInstalledPackages().iterator();
    for (;;)
    {
      if (localIterator.hasNext())
      {
        Object localObject1 = (String)localIterator.next();
        try
        {
          localObject1 = getPackageInfoWithRetry((String)localObject1, 8);
          if ((localObject1 != null) && (((PackageInfo)localObject1).providers != null))
          {
            localObject1 = ((PackageInfo)localObject1).providers;
            int j = localObject1.length;
            int i = 0;
            while (i < j)
            {
              Object localObject2 = localObject1[i];
              if (localObject2 != null) {
                localArrayList.add(localObject2);
              }
              i += 1;
            }
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          MAPLog.w(TAG, "Caught NameNotFoundException querying for package that existed a moment ago", localNameNotFoundException);
        }
      }
    }
    return localArrayList;
  }
}
