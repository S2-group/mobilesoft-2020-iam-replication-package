package com.google.android.clockwork.companion.packagemanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.TransactionTooLargeException;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.clockwork.companion.mediacontrols.api21.browser.DefaultBasicHandlerFactory;
import com.google.android.clockwork.host.GKeys;
import com.google.android.clockwork.host.WearableHost;
import com.google.android.clockwork.host.WearableHostUtil;
import com.google.android.clockwork.packagemanager.CompanionPackageData;
import com.google.android.clockwork.packagemanager.CompanionPackageData.WearableData;
import com.google.android.clockwork.packagemanager.CompanionPackageData.WearableDataLoader;
import com.google.android.clockwork.utils.AssetUtil;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataApi.DataItemResult;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataItemBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.NodeApi.GetLocalNodeResult;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gsf.GservicesValue;
import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;

public abstract class AbstractPackageUpdateService
{
  private static final ArrayMap PERMS_WHITELIST_TO_MAX_SDK = new AbstractPackageUpdateService.1();
  private PackageInfo androidWearPackageInfo;
  private final String androidWearPackageName;
  public final ExecutorService executor;
  private final PackageManager packageManager;
  private final PackageUpdateService packageUpdateService;
  
  public AbstractPackageUpdateService(PackageUpdateService paramPackageUpdateService, PackageManager paramPackageManager, ExecutorService paramExecutorService, String paramString)
  {
    this.packageUpdateService = paramPackageUpdateService;
    this.packageManager = paramPackageManager;
    this.executor = paramExecutorService;
    this.androidWearPackageName = paramString;
  }
  
  private final boolean addPackage(String paramString, boolean paramBoolean, int paramInt)
  {
    Object localObject4 = getExistingDataItem(paramString);
    Object localObject3 = "application_label";
    Object localObject2 = "wearables";
    Object localObject1 = "host_ungranted_permissions";
    Object localObject6;
    Object localObject5;
    Object localObject8;
    Object localObject7;
    if (localObject4 == null)
    {
      localObject1 = null;
      localObject2 = "application_label";
      localObject3 = "wearables";
      localObject4 = "host_ungranted_permissions";
    }
    else
    {
      localObject6 = ((DataMapItem)localObject4).zzprh;
      localCompanionPackageData = new CompanionPackageData(paramString);
      localCompanionPackageData.applicationLabel = ((DataMap)localObject6).getString("application_label");
      localCompanionPackageData.status = ((DataMap)localObject6).getInt("status", 0);
      localCompanionPackageData.fingerprint = ((DataMap)localObject6).getString("fingerprint");
      localCompanionPackageData.lastForceInstallTimestamp = ((DataMap)localObject6).getLong("last_force_install_timestamp", 0L);
      localCompanionPackageData.companionSdkVersion = ((DataMap)localObject6).getInt("companion_sdk_version", 0);
      localCompanionPackageData.companionDeviceVersion = ((DataMap)localObject6).getInt("companion_device_version", 0);
      if (((DataMap)localObject6).containsKey("host_granted_permissions")) {
        localCompanionPackageData.setGrantedPermissions(((DataMap)localObject6).getStringArrayList("host_granted_permissions"));
      }
      if (((DataMap)localObject6).containsKey("host_ungranted_permissions")) {
        localCompanionPackageData.setUngrantedPermissions(((DataMap)localObject6).getStringArrayList("host_ungranted_permissions"));
      }
      if (((DataMap)localObject6).containsKey("wearables"))
      {
        localObject4 = ((DataMap)localObject6).getDataMap("wearables");
        localObject5 = ((DataMap)localObject4).zzalf.keySet().iterator();
        while (((Iterator)localObject5).hasNext())
        {
          localObject8 = (String)((Iterator)localObject5).next();
          localObject7 = ((DataMap)localObject4).getDataMap((String)localObject8);
          localObject8 = new CompanionPackageData.WearableData((String)localObject8, ((DataMap)localObject7).getBoolean("unbundled", false));
          ((CompanionPackageData.WearableData)localObject8).checksum = ((DataMap)localObject7).getString("checksum");
          ((CompanionPackageData.WearableData)localObject8).asset = ((DataMap)localObject7).getAsset("apk");
          ((CompanionPackageData.WearableData)localObject8).apkCount = ((DataMap)localObject7).getInt("apk_count", 0);
          localCompanionPackageData.addWearable((CompanionPackageData.WearableData)localObject8);
        }
        localObject4 = localObject1;
        localObject1 = localObject3;
        localObject3 = localObject4;
      }
      else
      {
        localObject1 = "application_label";
        localObject2 = "wearables";
        localObject3 = "host_ungranted_permissions";
      }
      if (((DataMap)localObject6).containsKey("application_icon")) {
        localCompanionPackageData.icon = ((DataMap)localObject6).getAsset("application_icon");
      }
      localCompanionPackageData.downloadOnly = ((DataMap)localObject6).getBoolean("download_only", false);
      localObject4 = localCompanionPackageData.wearablesMap.values().iterator();
      while (((Iterator)localObject4).hasNext())
      {
        localObject5 = (CompanionPackageData.WearableData)((Iterator)localObject4).next();
        localObject6 = ((CompanionPackageData.WearableData)localObject5).asset;
        if (localObject6 != null) {
          ((CompanionPackageData.WearableData)localObject5).loader = new AbstractPackageUpdateService.WearableDataFromGmsAssetLoader(this, (Asset)localObject6);
        }
      }
      localObject4 = localObject3;
      localObject3 = localObject2;
      localObject2 = localObject1;
      localObject1 = localCompanionPackageData;
    }
    boolean bool = isAndroidWear(paramString);
    int i = bool ^ true;
    CompanionPackageData localCompanionPackageData = loadCompanionPackageData(paramString);
    if (localCompanionPackageData != null) {
      if (localCompanionPackageData.hasWearables())
      {
        if (paramBoolean) {
          localCompanionPackageData.lastForceInstallTimestamp = System.currentTimeMillis();
        } else if (localObject1 != null) {
          if ((i != 0) && (!localCompanionPackageData.matchesPermissions((CompanionPackageData)localObject1)))
          {
            localCompanionPackageData.lastForceInstallTimestamp = System.currentTimeMillis();
          }
          else
          {
            localCompanionPackageData.lastForceInstallTimestamp = ((CompanionPackageData)localObject1).lastForceInstallTimestamp;
            if ((((CompanionPackageData)localObject1).hasWearables()) && (MoreObjects.equal(localCompanionPackageData.fingerprint, ((CompanionPackageData)localObject1).fingerprint)) && (localCompanionPackageData.status == ((CompanionPackageData)localObject1).status))
            {
              paramString = String.valueOf(paramString);
              if (paramString.length() == 0) {
                paramString = new String("Fingerprint & Status matches, not requesting installs for ");
              } else {
                paramString = "Fingerprint & Status matches, not requesting installs for ".concat(paramString);
              }
              Log.i("WearablePkgInstaller", paramString);
              return true;
            }
            localCompanionPackageData.ensureChecksumsLoaded();
            if (bool) {
              ((CompanionPackageData)localObject1).ensureChecksumsLoaded();
            }
            if ((localCompanionPackageData.packageName.equals(((CompanionPackageData)localObject1).packageName)) && ((i == 0) || (localCompanionPackageData.matchesPermissions((CompanionPackageData)localObject1))) && (localCompanionPackageData.lastForceInstallTimestamp == ((CompanionPackageData)localObject1).lastForceInstallTimestamp) && (localCompanionPackageData.companionSdkVersion == ((CompanionPackageData)localObject1).companionSdkVersion) && (localCompanionPackageData.companionDeviceVersion == ((CompanionPackageData)localObject1).companionDeviceVersion) && (localCompanionPackageData.status == ((CompanionPackageData)localObject1).status) && (localCompanionPackageData.downloadOnly == ((CompanionPackageData)localObject1).downloadOnly))
            {
              localObject5 = localCompanionPackageData.wearablesMap.entrySet().iterator();
              while (((Iterator)localObject5).hasNext())
              {
                localObject7 = (Map.Entry)((Iterator)localObject5).next();
                localObject6 = (CompanionPackageData.WearableData)((CompanionPackageData)localObject1).wearablesMap.get(((Map.Entry)localObject7).getKey());
                if (localObject6 == null) {
                  break label980;
                }
                localObject7 = (CompanionPackageData.WearableData)((Map.Entry)localObject7).getValue();
                if ((((CompanionPackageData.WearableData)localObject7).packageName.equals(((CompanionPackageData.WearableData)localObject6).packageName)) && (MoreObjects.equal(((CompanionPackageData.WearableData)localObject7).checksum, ((CompanionPackageData.WearableData)localObject6).checksum))) {
                  i = 1;
                } else {
                  i = 0;
                }
                if (i == 0) {
                  break label980;
                }
              }
              localObject5 = ((CompanionPackageData)localObject1).wearablesMap.keySet().iterator();
              while (((Iterator)localObject5).hasNext())
              {
                localObject6 = (String)((Iterator)localObject5).next();
                if (!localCompanionPackageData.wearablesMap.containsKey(localObject6)) {
                  break label980;
                }
              }
              paramString = String.valueOf(paramString);
              if (paramString.length() == 0) {
                paramString = new String("Companion package metadata matches, not requesting installs for ");
              } else {
                paramString = "Companion package metadata matches, not requesting installs for ".concat(paramString);
              }
              Log.i("WearablePkgInstaller", paramString);
              return true;
            }
          }
        }
        label980:
        localObject6 = PutDataMapRequest.create(getDataItemPath(paramString)).setUrgent();
        localObject7 = ((PutDataMapRequest)localObject6).zzprh;
        i = getDataMapVersion();
        ((DataMap)localObject7).putInt("status", localCompanionPackageData.status);
        ((DataMap)localObject7).putString("fingerprint", localCompanionPackageData.fingerprint);
        ((DataMap)localObject7).putLong("last_force_install_timestamp", localCompanionPackageData.lastForceInstallTimestamp);
        ((DataMap)localObject7).putInt("companion_sdk_version", localCompanionPackageData.companionSdkVersion);
        ((DataMap)localObject7).putInt("companion_device_version", localCompanionPackageData.companionDeviceVersion);
        ((DataMap)localObject7).putStringArrayList("host_granted_permissions", new ArrayList(localCompanionPackageData.grantedPermissions));
        ((DataMap)localObject7).putStringArrayList((String)localObject4, new ArrayList(localCompanionPackageData.ungrantedPermissions));
        localObject8 = new DataMap();
        localObject4 = localCompanionPackageData.wearablesMap.values().iterator();
        while (((Iterator)localObject4).hasNext())
        {
          CompanionPackageData.WearableData localWearableData = (CompanionPackageData.WearableData)((Iterator)localObject4).next();
          String str = localWearableData.packageName;
          paramBoolean = localCompanionPackageData.downloadOnly;
          DataMap localDataMap = new DataMap();
          localObject5 = localWearableData.checksum;
          if (localObject5 != null) {
            localDataMap.putString("checksum", (String)localObject5);
          }
          if (paramBoolean)
          {
            localObject5 = localWearableData.loader;
            if (localObject5 == null)
            {
              localWearableData.apkCount = 0;
            }
            else if (((CompanionPackageData.WearableDataLoader)localObject5).loadApkData() != null)
            {
              localWearableData.apkCount = 1;
            }
            else
            {
              localObject5 = String.valueOf(localWearableData.packageName);
              if (((String)localObject5).length() == 0) {
                localObject5 = new String("Call to mLoader.loadApkData() failed for ");
              } else {
                localObject5 = "Call to mLoader.loadApkData() failed for ".concat((String)localObject5);
              }
              Log.w("WearablePkgInstaller", (String)localObject5);
              localWearableData.apkCount = 0;
            }
            localDataMap.putInt("apk_count", localWearableData.apkCount);
          }
          else if (localWearableData.asset == null)
          {
            localObject5 = localWearableData.loader;
            if (localObject5 != null) {
              if (((CompanionPackageData.WearableDataLoader)localObject5).loadApkData() == null)
              {
                if (Log.isLoggable("WearablePkgInstaller", 3))
                {
                  localObject5 = localWearableData.packageName;
                  StringBuilder localStringBuilder = new StringBuilder(String.valueOf(localObject5).length() + 77);
                  localStringBuilder.append("Failed to load data for wearable package in ");
                  localStringBuilder.append((String)localObject5);
                  localStringBuilder.append("possibly due to no asset included");
                  Log.d("WearablePkgInstaller", localStringBuilder.toString());
                }
              }
              else {
                localWearableData.asset = Asset.createFromFd(localWearableData.loader.loadApkData());
              }
            }
          }
          localObject5 = localWearableData.asset;
          if (localObject5 != null) {
            localDataMap.putAsset("apk", (Asset)localObject5);
          }
          localDataMap.putBoolean("unbundled", localWearableData.isUnbundled);
          ((DataMap)localObject8).putDataMap(str, localDataMap);
        }
        ((DataMap)localObject7).putDataMap((String)localObject3, (DataMap)localObject8);
        localObject3 = localCompanionPackageData.icon;
        if (localObject3 != null) {
          ((DataMap)localObject7).putAsset("application_icon", (Asset)localObject3);
        }
        if (i == 2)
        {
          ((DataMap)localObject7).putString((String)localObject2, localCompanionPackageData.applicationLabel);
          ((DataMap)localObject7).putBoolean("download_only", localCompanionPackageData.downloadOnly);
        }
        if (paramInt > 0) {
          ((PutDataMapRequest)localObject6).zzprh.putInt("package_count_hint", paramInt);
        }
        if (Log.isLoggable("WearablePkgInstaller", 3))
        {
          localObject1 = String.valueOf(localObject1);
          localObject2 = String.valueOf(localCompanionPackageData);
          localObject3 = new StringBuilder(String.valueOf(paramString).length() + 75 + String.valueOf(localObject1).length() + String.valueOf(localObject2).length());
          ((StringBuilder)localObject3).append("Setting DataItem to install wearable apps for ");
          ((StringBuilder)localObject3).append(paramString);
          ((StringBuilder)localObject3).append(", existing data: ");
          ((StringBuilder)localObject3).append((String)localObject1);
          ((StringBuilder)localObject3).append(", new data: ");
          ((StringBuilder)localObject3).append((String)localObject2);
          Log.d("WearablePkgInstaller", ((StringBuilder)localObject3).toString());
        }
        else
        {
          paramString = String.valueOf(paramString);
          if (paramString.length() == 0) {
            paramString = new String("Setting DataItem to install wearable apps for ");
          } else {
            paramString = "Setting DataItem to install wearable apps for ".concat(paramString);
          }
          Log.i("WearablePkgInstaller", paramString);
        }
        return ((DataApi.DataItemResult)WearableHost.await(Wearable.DataApi.putDataItem(WearableHost.getSharedClient(), ((PutDataMapRequest)localObject6).asPutDataRequest()))).getStatus().isSuccess();
      }
    }
    removePackage(paramString);
    return true;
  }
  
  /* Error */
  static String getChecksumForInputStream(java.io.InputStream paramInputStream)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +106 -> 107
    //   4: ldc_w 452
    //   7: invokestatic 458	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   10: astore_2
    //   11: sipush 1024
    //   14: newarray byte
    //   16: astore_3
    //   17: aload_0
    //   18: aload_3
    //   19: invokevirtual 464	java/io/InputStream:read	([B)I
    //   22: istore_1
    //   23: iload_1
    //   24: ifle +13 -> 37
    //   27: aload_2
    //   28: aload_3
    //   29: iconst_0
    //   30: iload_1
    //   31: invokevirtual 468	java/security/MessageDigest:update	([BII)V
    //   34: goto -17 -> 17
    //   37: aload_2
    //   38: invokevirtual 472	java/security/MessageDigest:digest	()[B
    //   41: bipush 11
    //   43: invokestatic 478	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
    //   46: astore_2
    //   47: aload_0
    //   48: invokevirtual 481	java/io/InputStream:close	()V
    //   51: aload_2
    //   52: areturn
    //   53: astore_0
    //   54: aload_2
    //   55: areturn
    //   56: astore_2
    //   57: goto +40 -> 97
    //   60: astore_2
    //   61: goto +16 -> 77
    //   64: astore_2
    //   65: new 483	java/lang/IllegalStateException
    //   68: dup
    //   69: ldc_w 485
    //   72: aload_2
    //   73: invokespecial 488	java/lang/IllegalStateException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   76: athrow
    //   77: ldc_w 259
    //   80: ldc_w 490
    //   83: aload_2
    //   84: invokestatic 493	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   87: pop
    //   88: aload_0
    //   89: invokevirtual 481	java/io/InputStream:close	()V
    //   92: aconst_null
    //   93: areturn
    //   94: astore_0
    //   95: aconst_null
    //   96: areturn
    //   97: aload_0
    //   98: invokevirtual 481	java/io/InputStream:close	()V
    //   101: goto +4 -> 105
    //   104: astore_0
    //   105: aload_2
    //   106: athrow
    //   107: aconst_null
    //   108: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	109	0	paramInputStream	java.io.InputStream
    //   22	9	1	i	int
    //   10	45	2	localObject1	Object
    //   56	1	2	localObject2	Object
    //   60	1	2	localIOException	java.io.IOException
    //   64	42	2	localNoSuchAlgorithmException	java.security.NoSuchAlgorithmException
    //   16	13	3	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   47	51	53	java/io/IOException
    //   4	11	56	finally
    //   11	17	56	finally
    //   17	23	56	finally
    //   27	34	56	finally
    //   37	47	56	finally
    //   65	77	56	finally
    //   77	88	56	finally
    //   4	11	60	java/io/IOException
    //   11	17	60	java/io/IOException
    //   17	23	60	java/io/IOException
    //   27	34	60	java/io/IOException
    //   37	47	60	java/io/IOException
    //   65	77	60	java/io/IOException
    //   4	11	64	java/security/NoSuchAlgorithmException
    //   88	92	94	java/io/IOException
    //   97	101	104	java/io/IOException
  }
  
  private final DataMapItem getExistingDataItem(String paramString)
  {
    Object localObject = (NodeApi.GetLocalNodeResult)WearableHost.await(Wearable.NodeApi.getLocalNode(WearableHost.getSharedClient()));
    if (!((NodeApi.GetLocalNodeResult)localObject).getStatus().isSuccess())
    {
      paramString = String.valueOf(((NodeApi.GetLocalNodeResult)localObject).getStatus());
      localObject = new StringBuilder(String.valueOf(paramString).length() + 30);
      ((StringBuilder)localObject).append("Error finding the local node: ");
      ((StringBuilder)localObject).append(paramString);
      Log.e("WearablePkgInstaller", ((StringBuilder)localObject).toString());
      return null;
    }
    paramString = WearableHostUtil.wearUri(((NodeApi.GetLocalNodeResult)localObject).getNode(), getDataItemPath(paramString));
    paramString = (DataApi.DataItemResult)WearableHost.await(Wearable.DataApi.getDataItem(WearableHost.getSharedClient(), paramString));
    if (!paramString.getStatus().isSuccess())
    {
      paramString = String.valueOf(paramString.getStatus());
      localObject = new StringBuilder(String.valueOf(paramString).length() + 34);
      ((StringBuilder)localObject).append("Error getting existing data item: ");
      ((StringBuilder)localObject).append(paramString);
      Log.e("WearablePkgInstaller", ((StringBuilder)localObject).toString());
      return null;
    }
    if (paramString.getDataItem() != null) {
      return DataMapItem.fromDataItem(paramString.getDataItem());
    }
    return null;
  }
  
  private static int getMicroMetadataResId(ApplicationInfo paramApplicationInfo)
  {
    if ((paramApplicationInfo != null) && (paramApplicationInfo.metaData != null)) {
      return paramApplicationInfo.metaData.getInt("com.google.android.wearable.beta.app");
    }
    return 0;
  }
  
  private final void handleAllPackages(boolean paramBoolean, Intent paramIntent)
  {
    int i = 0;
    try
    {
      Object localObject2 = this.packageManager.getInstalledApplications(128);
      Object localObject1 = this.packageUpdateService;
      ((PackageUpdateService)localObject1).alarmManager.cancel(PendingIntent.getService((Context)localObject1, 0, paramIntent, 0));
      localObject1 = new HashSet();
      paramIntent = ((List)localObject2).iterator();
      while (paramIntent.hasNext())
      {
        localObject2 = (ApplicationInfo)paramIntent.next();
        if ((!isAndroidWear(((ApplicationInfo)localObject2).packageName)) && (getMicroMetadataResId((ApplicationInfo)localObject2) != 0)) {
          i += 1;
        }
        ((Set)localObject1).add(((ApplicationInfo)localObject2).packageName);
      }
      setSyncedPackagesCountDataItem(i, true);
      localObject2 = ((Set)localObject1).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        paramIntent = (String)((Iterator)localObject2).next();
        if (!addPackage(paramIntent, paramBoolean, i))
        {
          paramIntent = String.valueOf(paramIntent);
          if (paramIntent.length() == 0) {
            paramIntent = new String("Adding failed for package ");
          } else {
            paramIntent = "Adding failed for package ".concat(paramIntent);
          }
          Log.e("WearablePkgInstaller", paramIntent);
        }
      }
      localObject2 = (DataItemBuffer)WearableHost.await(Wearable.DataApi.getDataItems(WearableHost.getSharedClient(), WearableHostUtil.pathToWearUri(getPackagePrependPath()), 1));
      Object localObject3;
      try
      {
        localObject3 = ((AbstractDataBuffer)localObject2).iterator();
        while (((Iterator)localObject3).hasNext())
        {
          paramIntent = (DataItem)((Iterator)localObject3).next();
          String str = getPackageNameFromDataItemPath(paramIntent.getUri().getPath());
          if ((str != null) && (!((Set)localObject1).contains(str)) && (isDataItemForInstall(paramIntent)) && (!removePackage(str)))
          {
            paramIntent = String.valueOf(str);
            if (paramIntent.length() != 0) {
              paramIntent = "Removing failed for uninstalled package: ".concat(paramIntent);
            } else {
              paramIntent = new String("Removing failed for uninstalled package: ");
            }
            Log.e("WearablePkgInstaller", paramIntent);
          }
        }
        return;
      }
      finally
      {
        ((AbstractDataBuffer)localObject2).release();
      }
      PackageUpdateService localPackageUpdateService;
      long l;
      return;
    }
    catch (Exception localException)
    {
      Log.w("WearablePkgInstaller", "Get installed applications failed, attempting to schedule retry", localException);
      localPackageUpdateService = this.packageUpdateService;
      i = paramIntent.getIntExtra("com.google.android.wearable.RETRY_COUNT", 0);
      if (i < ((Integer)GKeys.NUM_RETRY_PACKAGE_INSTALL.retrieve$5166KOBMC4NMOOBECSNL6T3ID5N6EEP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0()).intValue())
      {
        l = Math.min((i ^ 0x3) * 60, 3600);
        localObject2 = paramIntent.getAction();
        localObject3 = new StringBuilder(String.valueOf(localObject2).length() + 52);
        ((StringBuilder)localObject3).append("Action ");
        ((StringBuilder)localObject3).append((String)localObject2);
        ((StringBuilder)localObject3).append(" failed; retry in");
        ((StringBuilder)localObject3).append(l);
        ((StringBuilder)localObject3).append(" seconds");
        Log.w("WearablePkgInstaller", ((StringBuilder)localObject3).toString());
        localPackageUpdateService.alarmManager.set(2, SystemClock.elapsedRealtime() + l * 1000L, PendingIntent.getService(localPackageUpdateService, 0, paramIntent.putExtra("com.google.android.wearable.RETRY_COUNT", i + 1), 134217728));
        return;
      }
      paramIntent = String.valueOf(paramIntent.getAction());
      if (paramIntent.length() == 0) {
        paramIntent = new String("Exceeded max retries for action:");
      } else {
        paramIntent = "Exceeded max retries for action:".concat(paramIntent);
      }
      Log.e("WearablePkgInstaller", paramIntent);
    }
  }
  
  private final boolean isAndroidWear(String paramString)
  {
    return this.androidWearPackageName.equals(paramString);
  }
  
  private final boolean isDataItemForInstall(DataItem paramDataItem)
  {
    String str = getPackageNameFromDataItemPath(paramDataItem.getUri().getPath());
    int i = DataMapItem.fromDataItem(paramDataItem).zzprh.getInt("status", 1);
    return (!TextUtils.isEmpty(str)) && (i == 0);
  }
  
  private final CompanionPackageData loadCompanionPackageData(String paramString)
  {
    try
    {
      localObject1 = this.packageManager.getPackageInfo(paramString, 4224);
      Resources localResources = this.packageManager.getResourcesForApplication(((PackageInfo)localObject1).applicationInfo);
      CompanionPackageData localCompanionPackageData = new CompanionPackageData(paramString);
      localCompanionPackageData.downloadOnly = shouldSetDownloadOnly();
      if (this.packageManager.getApplicationLabel(((PackageInfo)localObject1).applicationInfo) != null) {
        localCompanionPackageData.applicationLabel = this.packageManager.getApplicationLabel(((PackageInfo)localObject1).applicationInfo).toString();
      }
      int j = getMicroMetadataResId(((PackageInfo)localObject1).applicationInfo);
      Object localObject2 = this.packageManager.getApplicationIcon(((PackageInfo)localObject1).applicationInfo);
      if (localObject2 != null) {
        localCompanionPackageData.icon = AssetUtil.createAssetFromDrawable((Drawable)localObject2);
      }
      if (j != 0)
      {
        if (!((PackageInfo)localObject1).applicationInfo.enabled)
        {
          if (Log.isLoggable("WearablePkgInstaller", 3))
          {
            localObject1 = new StringBuilder(String.valueOf(paramString).length() + 22);
            ((StringBuilder)localObject1).append("Package ");
            ((StringBuilder)localObject1).append(paramString);
            ((StringBuilder)localObject1).append(" was disabled.");
            Log.d("WearablePkgInstaller", ((StringBuilder)localObject1).toString());
          }
          return localCompanionPackageData;
        }
        if (((PackageInfo)localObject1).requestedPermissions != null)
        {
          localObject2 = new ArrayList();
          ArrayList localArrayList = new ArrayList();
          int i = 0;
          while (i < ((PackageInfo)localObject1).requestedPermissions.length)
          {
            String str3 = localObject1.requestedPermissions[i];
            if ((localObject1.requestedPermissionsFlags[i] & 0x2) == 0)
            {
              int k = Build.VERSION.SDK_INT;
              if (((!PERMS_WHITELIST_TO_MAX_SDK.containsKey(str3)) || (k > ((Integer)PERMS_WHITELIST_TO_MAX_SDK.get(str3)).intValue())) && (!passesAndroidWearCheck(str3, ((PackageInfo)localObject1).firstInstallTime)))
              {
                localArrayList.add(str3);
                break label335;
              }
            }
            ((ArrayList)localObject2).add(str3);
            label335:
            i += 1;
          }
          localCompanionPackageData.setGrantedPermissions((Collection)localObject2);
          localCompanionPackageData.setUngrantedPermissions(localArrayList);
        }
        paramString = DefaultBasicHandlerFactory.getWearableData(this.packageUpdateService.getApplicationContext(), this.executor, localResources, paramString, null, j).iterator();
        while (paramString.hasNext()) {
          localCompanionPackageData.addWearable((CompanionPackageData.WearableData)paramString.next());
        }
        localCompanionPackageData.fingerprint = String.format(Locale.US, "%s>-<%d", new Object[] { Integer.valueOf(((PackageInfo)localObject1).versionCode), Long.valueOf(((PackageInfo)localObject1).lastUpdateTime) });
        localCompanionPackageData.companionSdkVersion = ((PackageInfo)localObject1).applicationInfo.targetSdkVersion;
        localCompanionPackageData.companionDeviceVersion = Build.VERSION.SDK_INT;
      }
      return localCompanionPackageData;
    }
    catch (RuntimeException localRuntimeException)
    {
      String str1;
      if ((localRuntimeException.getCause() instanceof TransactionTooLargeException))
      {
        str1 = String.valueOf(localRuntimeException);
        localObject1 = new StringBuilder(String.valueOf(paramString).length() + 33 + String.valueOf(str1).length());
        ((StringBuilder)localObject1).append("Failed to get package info for ");
        ((StringBuilder)localObject1).append(paramString);
        ((StringBuilder)localObject1).append(": ");
        ((StringBuilder)localObject1).append(str1);
        Log.e("WearablePkgInstaller", ((StringBuilder)localObject1).toString());
        return null;
      }
      throw str1;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      String str2 = String.valueOf(localNameNotFoundException);
      Object localObject1 = new StringBuilder(String.valueOf(paramString).length() + 25 + String.valueOf(str2).length());
      ((StringBuilder)localObject1).append("Could not find package ");
      ((StringBuilder)localObject1).append(paramString);
      ((StringBuilder)localObject1).append(": ");
      ((StringBuilder)localObject1).append(str2);
      Log.e("WearablePkgInstaller", ((StringBuilder)localObject1).toString());
    }
    return null;
  }
  
  private final boolean passesAndroidWearCheck(String paramString, long paramLong)
  {
    if (this.androidWearPackageInfo == null) {
      try
      {
        this.androidWearPackageInfo = this.packageManager.getPackageInfo(this.androidWearPackageName, 4224);
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        return false;
      }
    }
    if (this.androidWearPackageInfo.firstInstallTime >= paramLong)
    {
      Object localObject = this.androidWearPackageInfo.permissions;
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        if (localObject[i].name.equals(paramString))
        {
          if (!Log.isLoggable("WearablePkgInstaller", 3)) {
            return true;
          }
          localObject = new StringBuilder(String.valueOf(paramString).length() + 39);
          ((StringBuilder)localObject).append("Permission ");
          ((StringBuilder)localObject).append(paramString);
          ((StringBuilder)localObject).append(" was defined in Android Wear");
          Log.d("WearablePkgInstaller", ((StringBuilder)localObject).toString());
          return true;
        }
        i += 1;
      }
      return false;
    }
    return false;
  }
  
  private final boolean removePackage(String paramString)
  {
    Object localObject = getExistingDataItem(paramString);
    if (localObject != null)
    {
      localObject = PutDataMapRequest.createFromDataMapItem((DataMapItem)localObject).setUrgent();
      DataMap localDataMap = ((PutDataMapRequest)localObject).zzprh;
      if (localDataMap.getInt("status", 0) != 1)
      {
        paramString = String.valueOf(paramString);
        if (paramString.length() == 0) {
          paramString = new String("Requesting uninstall of wearable packages for ");
        } else {
          paramString = "Requesting uninstall of wearable packages for ".concat(paramString);
        }
        Log.i("WearablePkgInstaller", paramString);
        localDataMap.putInt("status", 1);
        return ((DataApi.DataItemResult)WearableHost.await(Wearable.DataApi.putDataItem(WearableHost.getSharedClient(), ((PutDataMapRequest)localObject).asPutDataRequest()))).getStatus().isSuccess();
      }
    }
    return true;
  }
  
  private final void setSyncedPackagesCountDataItem(int paramInt, boolean paramBoolean)
  {
    PutDataMapRequest localPutDataMapRequest = PutDataMapRequest.create(getPackageCountPath()).setUrgent();
    localPutDataMapRequest.zzprh.putInt("package_count", paramInt);
    if (Log.isLoggable("WearablePkgInstaller", 3))
    {
      StringBuilder localStringBuilder = new StringBuilder(39);
      localStringBuilder.append("There are ");
      localStringBuilder.append(paramInt);
      localStringBuilder.append(" Install DataItems");
      Log.d("WearablePkgInstaller", localStringBuilder.toString());
    }
    if (paramBoolean) {
      localPutDataMapRequest.zzprh.putLong("initial_sync_start_ts", System.currentTimeMillis());
    }
    if (!((DataApi.DataItemResult)WearableHost.await(Wearable.DataApi.putDataItem(WearableHost.getSharedClient(), localPutDataMapRequest.asPutDataRequest()))).getStatus().isSuccess()) {
      Log.w("WearablePkgInstaller", "Failed to put package count data item.");
    }
  }
  
  protected abstract String getDataItemPath(String paramString);
  
  protected abstract int getDataMapVersion();
  
  protected abstract String getPackageCountPath();
  
  protected abstract String getPackageNameFromDataItemPath(String paramString);
  
  protected abstract String getPackagePrependPath();
  
  public final void onHandleIntent(Intent paramIntent)
  {
    Object localObject1 = paramIntent.getAction();
    if ("com.google.android.wearable.FIRST_START".equals(localObject1)) {
      handleAllPackages(false, paramIntent);
    } else if ("com.google.android.wearable.SYNC_ALL".equals(localObject1)) {
      handleAllPackages(paramIntent.getBooleanExtra("com.google.android.wearable.FORCE_INSTALL", true), paramIntent);
    }
    try
    {
      localObject1 = paramIntent.getAction();
      Object localObject4 = paramIntent.getData();
      if (localObject4 == null)
      {
        localObject1 = String.valueOf(paramIntent);
        localObject4 = new StringBuilder(String.valueOf(localObject1).length() + 19);
        ((StringBuilder)localObject4).append("Expecting a URI in ");
        ((StringBuilder)localObject4).append((String)localObject1);
        Log.e("WearablePkgInstaller", ((StringBuilder)localObject4).toString());
        PackageUpdateReceiver.completeWakefulIntent(paramIntent);
      }
      else
      {
        localObject4 = ((Uri)localObject4).getSchemeSpecificPart();
        if (localObject4 == null)
        {
          Log.e("WearablePkgInstaller", "Expecting a package name.");
          PackageUpdateReceiver.completeWakefulIntent(paramIntent);
        }
        else
        {
          if ("android.intent.action.PACKAGE_ADDED".equals(localObject1))
          {
            if (!addPackage((String)localObject4, false, -1))
            {
              localObject1 = String.valueOf(localObject4);
              if (((String)localObject1).length() != 0) {
                localObject1 = "Failed to add ".concat((String)localObject1);
              } else {
                localObject1 = new String("Failed to add ");
              }
              Log.e("WearablePkgInstaller", (String)localObject1);
            }
          }
          else if ((!paramIntent.getBooleanExtra("android.intent.extra.REPLACING", false)) && (!removePackage((String)localObject4)))
          {
            localObject1 = String.valueOf(localObject4);
            if (((String)localObject1).length() != 0) {
              localObject1 = "Failed to remove: ".concat((String)localObject1);
            } else {
              localObject1 = new String("Failed to remove: ");
            }
            Log.e("WearablePkgInstaller", (String)localObject1);
          }
          PackageUpdateReceiver.completeWakefulIntent(paramIntent);
        }
      }
      paramIntent = (DataItemBuffer)WearableHost.await(Wearable.DataApi.getDataItems(WearableHost.getSharedClient(), WearableHostUtil.pathToWearUri(getPackagePrependPath()), 1));
      for (;;)
      {
        try
        {
          localObject1 = paramIntent.iterator();
          int i = 0;
          if (((Iterator)localObject1).hasNext())
          {
            localObject4 = (DataItem)((Iterator)localObject1).next();
            if ((!isAndroidWear(getPackageNameFromDataItemPath(((DataItem)localObject4).getUri().getPath()))) && (isDataItemForInstall((DataItem)localObject4))) {
              i += 1;
            }
          }
          else
          {
            setSyncedPackagesCountDataItem(i, false);
            return;
          }
        }
        finally
        {
          paramIntent.release();
        }
      }
    }
    finally
    {
      PackageUpdateReceiver.completeWakefulIntent(paramIntent);
    }
  }
  
  protected abstract boolean shouldSetDownloadOnly();
}
