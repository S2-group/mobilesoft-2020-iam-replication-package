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
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import com.amazon.identity.auth.device.utils.MAPLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TrustedPackageManager
{
  private static final String HANDLE_MESSAGE_PERMISSION = "com.amazon.dcp.messaging.permission.HANDLE_DEVICE_MESSAGE";
  private static final String TAG = TrustedPackageManager.class.getName();
  private final String mAuthenticatorPackageName;
  private final PackageManagerWrapper mPackageManager;
  
  public TrustedPackageManager(Context paramContext)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("context cannot be null");
    }
    this.mAuthenticatorPackageName = paramContext.getPackageName();
    this.mPackageManager = new PackageManagerWrapper(paramContext);
  }
  
  TrustedPackageManager(Context paramContext, PackageManager paramPackageManager)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("context cannot be null");
    }
    this.mAuthenticatorPackageName = paramContext.getPackageName();
    this.mPackageManager = new PackageManagerWrapper(paramContext, paramPackageManager);
  }
  
  public List<PackageInfo> getInstalledPackages()
  {
    Object localObject = this.mPackageManager.getInstalledPackages(64);
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (isTrustedPackage(localPackageInfo.packageName)) {
        localArrayList.add(localPackageInfo);
      }
    }
    return localArrayList;
  }
  
  public XmlResourceParser getParserForPackage(PackageItemInfo paramPackageItemInfo, String paramString)
  {
    if (paramPackageItemInfo == null)
    {
      MAPLog.e(TAG, "PackageItemInfo cannot be null in getParserForPackage");
      return null;
    }
    return paramPackageItemInfo.loadXmlMetaData(this.mPackageManager, "com.amazon.dcp.sso.AccountSubAuthenticator");
  }
  
  public ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    ActivityInfo localActivityInfo = this.mPackageManager.getReceiverInfo(paramComponentName, paramInt);
    if (localActivityInfo == null) {
      paramComponentName = null;
    }
    do
    {
      return paramComponentName;
      paramComponentName = localActivityInfo;
    } while (isTrustedPackage(localActivityInfo.packageName));
    MAPLog.e(TAG, "Cannot get Receiver ActivityInfo from package %s since it is not signed with the Amazon Cert.", new String[] { localActivityInfo.packageName });
    return null;
  }
  
  public Resources getResourcesForApplication(String paramString)
    throws PackageManager.NameNotFoundException
  {
    Resources localResources = null;
    if (isTrustedPackage(paramString)) {
      localResources = this.mPackageManager.getResourcesForApplication(paramString);
    }
    return localResources;
  }
  
  public ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    ServiceInfo localServiceInfo = this.mPackageManager.getServiceInfo(paramComponentName, paramInt);
    if (localServiceInfo == null) {
      paramComponentName = null;
    }
    do
    {
      return paramComponentName;
      paramComponentName = localServiceInfo;
    } while (isTrustedPackage(localServiceInfo.packageName));
    MAPLog.e(TAG, "Cannot get ServiceInfo from package %s since it is not signed with the Amazon Cert.", new String[] { localServiceInfo.packageName });
    return null;
  }
  
  public boolean hasContentProviderWithAuthority(String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    paramString1 = this.mPackageManager.queryContentProviders(paramString1, paramInt1, paramInt2).iterator();
    while (paramString1.hasNext())
    {
      ProviderInfo localProviderInfo = (ProviderInfo)paramString1.next();
      if ((TextUtils.equals(paramString2, localProviderInfo.authority)) && (isTrustedPackage(localProviderInfo.packageName))) {
        return true;
      }
    }
    return false;
  }
  
  public boolean hasHandleDeviceMessagePermission(String paramString)
  {
    return this.mPackageManager.checkPermission("com.amazon.dcp.messaging.permission.HANDLE_DEVICE_MESSAGE", paramString) == 0;
  }
  
  public boolean isTrustedPackage(String paramString)
  {
    return this.mPackageManager.doSignaturesMatch(this.mAuthenticatorPackageName, paramString);
  }
  
  public List<ResolveInfo> queryBroadcastReceivers(Intent paramIntent, int paramInt)
  {
    Object localObject = this.mPackageManager.queryBroadcastReceivers(paramIntent, paramInt);
    paramIntent = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
      if (isTrustedPackage(localResolveInfo.activityInfo.packageName)) {
        paramIntent.add(localResolveInfo);
      }
    }
    return paramIntent;
  }
  
  public List<ProviderInfo> queryContentProviders(String paramString, int paramInt1, int paramInt2)
  {
    Object localObject = this.mPackageManager.queryContentProviders(paramString, paramInt1, paramInt2);
    paramString = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ProviderInfo localProviderInfo = (ProviderInfo)((Iterator)localObject).next();
      if (isTrustedPackage(localProviderInfo.packageName)) {
        paramString.add(localProviderInfo);
      }
    }
    return paramString;
  }
  
  public List<ResolveInfo> queryIntentActivities(Intent paramIntent, int paramInt)
  {
    Object localObject = this.mPackageManager.queryIntentActivities(paramIntent, paramInt);
    paramIntent = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
      if (isTrustedPackage(localResolveInfo.activityInfo.packageName)) {
        paramIntent.add(localResolveInfo);
      }
    }
    return paramIntent;
  }
  
  public List<ResolveInfo> queryIntentServices(Intent paramIntent, int paramInt)
  {
    Object localObject = this.mPackageManager.queryIntentServices(paramIntent, paramInt);
    paramIntent = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
      boolean bool1 = isTrustedPackage(localResolveInfo.serviceInfo.packageName);
      boolean bool2 = hasHandleDeviceMessagePermission(localResolveInfo.serviceInfo.packageName);
      if ((bool1) || (bool2)) {
        paramIntent.add(localResolveInfo);
      }
    }
    return paramIntent;
  }
}
