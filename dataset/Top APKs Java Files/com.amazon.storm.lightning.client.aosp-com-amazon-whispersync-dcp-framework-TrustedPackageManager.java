package com.amazon.whispersync.dcp.framework;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.util.Log;
import com.amazon.whispersync.com.google.inject.Inject;
import com.amazon.whispersync.com.google.inject.name.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TrustedPackageManager
{
  private static final String TAG = TrustedPackageManager.class.getName();
  private final String mCurrentPackageName;
  private final PackageManagerWrapper mPackageManager;
  
  @Deprecated
  public TrustedPackageManager(Context paramContext)
  {
    this(paramContext.getPackageName(), new PackageManagerWrapper(paramContext.getPackageManager()));
  }
  
  @Inject
  public TrustedPackageManager(@Named("Context_packageName") String paramString, PackageManagerWrapper paramPackageManagerWrapper)
  {
    this.mCurrentPackageName = paramString;
    this.mPackageManager = paramPackageManagerWrapper;
  }
  
  private int getUidForPackage(String paramString)
    throws PackageManager.NameNotFoundException
  {
    paramString = this.mPackageManager.getApplicationInfo(paramString, 0);
    if (paramString == null) {
      throw new PackageManager.NameNotFoundException();
    }
    return paramString.uid;
  }
  
  private int getUidForSystem()
    throws PackageManager.NameNotFoundException
  {
    return this.mPackageManager.getUidForSharedUser("android.uid.system");
  }
  
  private boolean isAmazonSignedPackage(String paramString)
  {
    return this.mPackageManager.checkSignatures(this.mCurrentPackageName, paramString) == 0;
  }
  
  private boolean isSystemPackage(String paramString)
  {
    boolean bool = false;
    try
    {
      int i = getUidForSystem();
      int j = getUidForPackage(paramString);
      i = this.mPackageManager.checkSignatures(i, j);
      if (i == 0) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Log.e(TAG, "Name not found for package: " + paramString, localNameNotFoundException);
    }
    return false;
  }
  
  public List<PackageInfo> getInstalledPackages()
  {
    return getInstalledPackages(new TrustedPackageManager.TrustLevel[] { TrustedPackageManager.TrustLevel.Signature, TrustedPackageManager.TrustLevel.System });
  }
  
  public List<PackageInfo> getInstalledPackages(TrustedPackageManager.TrustLevel... paramVarArgs)
  {
    Object localObject = this.mPackageManager.getInstalledPackages(64);
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (isTrustedPackage(localPackageInfo.packageName, paramVarArgs)) {
        localArrayList.add(localPackageInfo);
      }
    }
    return localArrayList;
  }
  
  public List<PackageInfo> getInstalledPackagesFromNames(List<String> paramList)
  {
    if (paramList.isEmpty())
    {
      localObject = Collections.emptyList();
      return localObject;
    }
    Object localObject = this.mPackageManager.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = ((List)localObject).iterator();
    for (;;)
    {
      localObject = localArrayList;
      if (!localIterator.hasNext()) {
        break;
      }
      localObject = (PackageInfo)localIterator.next();
      if (paramList.contains(((PackageInfo)localObject).packageName)) {
        localArrayList.add(localObject);
      }
    }
  }
  
  public Resources getResourcesForApplication(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return getResourcesForApplication(paramString, new TrustedPackageManager.TrustLevel[] { TrustedPackageManager.TrustLevel.Signature, TrustedPackageManager.TrustLevel.System });
  }
  
  public Resources getResourcesForApplication(String paramString, TrustedPackageManager.TrustLevel... paramVarArgs)
    throws PackageManager.NameNotFoundException
  {
    Resources localResources = null;
    if (isTrustedPackage(paramString, paramVarArgs)) {
      localResources = this.mPackageManager.getResourcesForApplication(paramString);
    }
    return localResources;
  }
  
  public boolean hasPermission(String paramString1, String paramString2)
  {
    return this.mPackageManager.checkPermission(paramString2, paramString1) == 0;
  }
  
  public boolean isTrustedPackage(String paramString)
  {
    return isTrustedPackage(paramString, new TrustedPackageManager.TrustLevel[] { TrustedPackageManager.TrustLevel.Signature, TrustedPackageManager.TrustLevel.System });
  }
  
  public boolean isTrustedPackage(String paramString, TrustedPackageManager.TrustLevel... paramVarArgs)
  {
    paramVarArgs = new HashSet(Arrays.asList(paramVarArgs));
    return ((paramVarArgs.contains(TrustedPackageManager.TrustLevel.Signature)) && (isAmazonSignedPackage(paramString))) || ((paramVarArgs.contains(TrustedPackageManager.TrustLevel.System)) && (isSystemPackage(paramString)));
  }
  
  public List<ResolveInfo> queryBroadcastReceivers(Intent paramIntent, int paramInt)
  {
    return queryBroadcastReceivers(paramIntent, paramInt, new TrustedPackageManager.TrustLevel[] { TrustedPackageManager.TrustLevel.Signature, TrustedPackageManager.TrustLevel.System });
  }
  
  public List<ResolveInfo> queryBroadcastReceivers(Intent paramIntent, int paramInt, TrustedPackageManager.TrustLevel... paramVarArgs)
  {
    Object localObject = this.mPackageManager.queryBroadcastReceivers(paramIntent, paramInt);
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
      if (!isTrustedPackage(localResolveInfo.activityInfo.packageName, paramVarArgs)) {
        Log.w(TAG, String.format("Receiver %s/%s responds to intent %s, but is not trusted.", new Object[] { localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name, paramIntent.toString() }));
      } else {
        localArrayList.add(localResolveInfo);
      }
    }
    return localArrayList;
  }
  
  public List<ResolveInfo> queryIntentActivities(Intent paramIntent, int paramInt)
  {
    return queryIntentActivities(paramIntent, paramInt, new TrustedPackageManager.TrustLevel[] { TrustedPackageManager.TrustLevel.Signature, TrustedPackageManager.TrustLevel.System });
  }
  
  public List<ResolveInfo> queryIntentActivities(Intent paramIntent, int paramInt, TrustedPackageManager.TrustLevel... paramVarArgs)
  {
    Object localObject = this.mPackageManager.queryIntentActivities(paramIntent, paramInt);
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
      if (!isTrustedPackage(localResolveInfo.activityInfo.packageName, paramVarArgs)) {
        Log.w(TAG, String.format("Activity %s/%s responds to intent %s, but is not trusted.", new Object[] { localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name, paramIntent.toString() }));
      } else {
        localArrayList.add(localResolveInfo);
      }
    }
    return localArrayList;
  }
  
  public List<ResolveInfo> queryIntentServices(Intent paramIntent, int paramInt)
  {
    return queryIntentServicesAsUser(paramIntent, paramInt, -2, (String)null, new TrustedPackageManager.TrustLevel[] { TrustedPackageManager.TrustLevel.Signature, TrustedPackageManager.TrustLevel.System });
  }
  
  public List<ResolveInfo> queryIntentServices(Intent paramIntent, int paramInt, String paramString)
  {
    return queryIntentServices(paramIntent, paramInt, paramString, new TrustedPackageManager.TrustLevel[] { TrustedPackageManager.TrustLevel.Signature, TrustedPackageManager.TrustLevel.System });
  }
  
  public List<ResolveInfo> queryIntentServices(Intent paramIntent, int paramInt, String paramString, TrustedPackageManager.TrustLevel... paramVarArgs)
  {
    Object localObject = this.mPackageManager.queryIntentServices(paramIntent, paramInt);
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
      String str = localResolveInfo.serviceInfo.packageName;
      if ((isTrustedPackage(str, paramVarArgs)) || ((paramString != null) && (hasPermission(str, paramString)))) {
        localArrayList.add(localResolveInfo);
      } else {
        Log.w(TAG, String.format("Service %s/%s responds to intent %s, but is not trusted.", new Object[] { localResolveInfo.serviceInfo.packageName, localResolveInfo.serviceInfo.name, paramIntent.toString() }));
      }
    }
    return localArrayList;
  }
  
  public List<ResolveInfo> queryIntentServicesAsUser(Intent paramIntent, int paramInt1, int paramInt2)
  {
    return queryIntentServicesAsUser(paramIntent, paramInt1, paramInt2, (String)null, new TrustedPackageManager.TrustLevel[] { TrustedPackageManager.TrustLevel.Signature, TrustedPackageManager.TrustLevel.System });
  }
  
  public List<ResolveInfo> queryIntentServicesAsUser(Intent paramIntent, int paramInt1, int paramInt2, String paramString)
  {
    return queryIntentServicesAsUser(paramIntent, paramInt1, paramInt2, paramString, new TrustedPackageManager.TrustLevel[] { TrustedPackageManager.TrustLevel.Signature, TrustedPackageManager.TrustLevel.System });
  }
  
  public List<ResolveInfo> queryIntentServicesAsUser(Intent paramIntent, int paramInt1, int paramInt2, String paramString, TrustedPackageManager.TrustLevel... paramVarArgs)
  {
    Object localObject = this.mPackageManager.queryIntentServicesAsUser(paramIntent, paramInt1, paramInt2);
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
      String str = localResolveInfo.serviceInfo.packageName;
      if ((isTrustedPackage(str, paramVarArgs)) || ((paramString != null) && (hasPermission(str, paramString)))) {
        localArrayList.add(localResolveInfo);
      } else {
        Log.w(TAG, String.format("Service %s/%s responds to intent %s, but is not trusted.", new Object[] { localResolveInfo.serviceInfo.packageName, localResolveInfo.serviceInfo.name, paramIntent.toString() }));
      }
    }
    return localArrayList;
  }
  
  public List<ResolveInfo> queryIntentServicesAsUser(Intent paramIntent, int paramInt1, int paramInt2, TrustedPackageManager.TrustLevel... paramVarArgs)
  {
    return queryIntentServicesAsUser(paramIntent, paramInt1, paramInt2, null, paramVarArgs);
  }
}
