package com.plantronics.pdp.service.utility.masterresolver;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import com.plantronics.pdp.protocol.IncomingMessage;
import com.plantronics.pdp.protocol.MessageCallback;
import com.plantronics.pdp.protocol.PDPException;
import com.plantronics.pdp.protocol.control.ResolvingMaster;
import com.plantronics.pdp.service.Log;
import com.plantronics.pdp.service.PDPCommunicator;
import com.plantronics.pdp.service.PDPServiceCommandReceiver;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MasterResolver
{
  public static final String TAG = MasterResolver.class.getSimpleName();
  
  public MasterResolver() {}
  
  public static void connectDeviceFromMasterAppAfterUninstallingBroadcast(Context paramContext)
  {
    Log.d(TAG, "connectDeviceFromMasterAppAfterUninstallingBroadcast");
    if (MasterSharedPreferences.getIsMaster(paramContext))
    {
      manageReceiverForStartingPDPService(paramContext);
      PDPCommunicator.getInstance(paramContext).initializeAfterResolvingMaster();
    }
  }
  
  private static Map.Entry<String, Long> getLatestPDPApp(Map<String, Long> paramMap)
  {
    Map.Entry localEntry = null;
    Iterator localIterator = paramMap.entrySet().iterator();
    for (paramMap = localEntry; localIterator.hasNext(); paramMap = localEntry)
    {
      label16:
      localEntry = (Map.Entry)localIterator.next();
      if ((paramMap != null) && (((Long)localEntry.getValue()).longValue() <= ((Long)paramMap.getValue()).longValue())) {
        break label16;
      }
    }
    return paramMap;
  }
  
  private static Map<String, Long> getPDPAppsWithInstallationTime(Context paramContext, List<ResolveInfo> paramList)
  {
    List localList = paramContext.getPackageManager().getInstalledApplications(128);
    HashMap localHashMap = new HashMap();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      String str = ((ResolveInfo)paramList.next()).activityInfo.packageName;
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (localApplicationInfo.packageName.equalsIgnoreCase(str)) {
          try
          {
            PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(localApplicationInfo.packageName, 0);
            localHashMap.put(localApplicationInfo.packageName, Long.valueOf(localPackageInfo.firstInstallTime));
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            localNameNotFoundException.printStackTrace();
          }
        }
      }
    }
    return localHashMap;
  }
  
  private static boolean isOnePDPAppInstalled(List<ResolveInfo> paramList)
  {
    return paramList.size() == 1;
  }
  
  private static void manageReceiverForStartingPDPService(Context paramContext)
  {
    ComponentName localComponentName = new ComponentName(paramContext, PDPServiceCommandReceiver.class);
    int i = paramContext.getPackageManager().getComponentEnabledSetting(localComponentName);
    if (((i == 1) || (i == 0)) && (!MasterSharedPreferences.getIsMaster(paramContext)))
    {
      paramContext.getPackageManager().setComponentEnabledSetting(localComponentName, 2, 1);
      Log.d(TAG, "Master resolver: not master; disable receiver that starts service");
    }
    while ((i != 2) || (!MasterSharedPreferences.getIsMaster(paramContext))) {
      return;
    }
    paramContext.getPackageManager().setComponentEnabledSetting(localComponentName, 1, 1);
    Log.d(TAG, "Master resolver: is master; enable receiver that starts service");
  }
  
  public static void resolveMaster(Context paramContext)
  {
    Log.d(TAG, "resolveMaster");
    Object localObject = new Intent("com.plantronics.pdp.resolver.START_RESOLVING", null);
    localObject = paramContext.getPackageManager().queryBroadcastReceivers((Intent)localObject, 0);
    if (isOnePDPAppInstalled((List)localObject)) {
      MasterSharedPreferences.storeIsMaster(paramContext, true);
    }
    for (;;)
    {
      MasterSharedPreferences.storeIsMasterResolved(paramContext, true);
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          MasterResolver.manageReceiverForStartingPDPService(this.val$context);
        }
      }, 1500L);
      return;
      resolveMasterWhenMultiplePDPAppsInstalled(paramContext, (List)localObject);
    }
  }
  
  private static void resolveMasterWhenMultiplePDPAppsInstalled(Context paramContext, List<ResolveInfo> paramList)
  {
    Log.d(TAG, "resolveMasterWhenMultiplePDPAppsInstalled");
    paramList = getPDPAppsWithInstallationTime(paramContext, paramList);
    MasterSharedPreferences.storePDPApps(paramContext, paramList.keySet());
    paramList = getLatestPDPApp(paramList);
    if (paramContext.getPackageName().equals(paramList.getKey()))
    {
      Log.d(TAG, "resolveMasterWhenMultiplePDPAppsInstalled isMaster = true");
      MasterSharedPreferences.storeIsMaster(paramContext, true);
      return;
    }
    Log.d(TAG, "resolveMasterWhenMultiplePDPAppsInstalled isMaster = false");
    PDPCommunicator.getInstance(paramContext).execute(new ResolvingMaster(), PDPCommunicator.getInstance(paramContext).getTargetBluetoothDevice(), new MessageCallback()
    {
      public void onFailure(PDPException paramAnonymousPDPException) {}
      
      public void onSuccess(IncomingMessage paramAnonymousIncomingMessage) {}
    });
    MasterSharedPreferences.storeIsMaster(paramContext, false);
  }
}
