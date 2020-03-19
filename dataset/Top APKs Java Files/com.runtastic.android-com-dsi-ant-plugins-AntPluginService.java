package com.dsi.ant.plugins;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import com.dsi.ant.AntService;
import com.dsi.ant.channel.AntChannel;
import com.dsi.ant.channel.AntChannelProvider;
import com.dsi.ant.channel.Capabilities;
import com.dsi.ant.channel.ChannelNotAvailableException;
import com.dsi.ant.channel.PredefinedNetwork;
import com.dsi.ant.plugins.utility.executor.AntChannelExecutor;
import com.dsi.ant.plugins.utility.executor.AntChannelExecutor.IDeathHandler;
import com.dsi.ant.plugins.utility.search.SingleSearchControllerTask;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

public abstract class AntPluginService
  extends Service
{
  public AntService mArsComm = null;
  public ServiceConnection mArsConnection;
  public final ArrayList<AntPluginDevice> mConnectedDevices = new ArrayList();
  public Messenger mPccMsgHandler = new Messenger(new AntPluginService.2(this));
  Messenger mPccReqAccessHandler = new Messenger(new AntPluginService.1(this));
  public TreeMap<UUID, AntPluginDevice> mToken_DeviceList = new TreeMap();
  
  public AntPluginService() {}
  
  private void handleActivitySearchAccessRequest(AntPluginDevice.ClientInfo paramClientInfo, Messenger paramMessenger, Bundle paramBundle)
  {
    if (!paramBundle.containsKey("int_ProximityBin"))
    {
      Log.e("AntPluginService", "Bundle is missing proximity parameter");
      paramClientInfo = Message.obtain();
      paramClientInfo.what = -4;
      dumbfireSendResult(paramMessenger, paramClientInfo);
    }
    Object localObject;
    label148:
    do
    {
      return;
      localObject = getPackageManager().getInstalledApplications(0).iterator();
      if (!((Iterator)localObject).hasNext()) {}
      for (int i = 0;; i = 1)
      {
        if (i != 0) {
          break label148;
        }
        Log.e("PluginService", "PluginManager not installed");
        paramClientInfo = Message.obtain();
        paramClientInfo.what = -5;
        paramBundle = new Bundle();
        paramBundle.putString("string_DependencyPackageName", "com.dsi.ant.plugins.antplus");
        paramBundle.putString("string_DependencyName", "ANT+ Plugins");
        paramClientInfo.setData(paramBundle);
        dumbfireSendResult(paramMessenger, paramClientInfo);
        return;
        if (!((ApplicationInfo)((Iterator)localObject).next()).packageName.equals("com.dsi.ant.plugins.antplus")) {
          break;
        }
      }
      localObject = composeActivitySearchParams(paramClientInfo, paramMessenger, paramBundle);
    } while (localObject == null);
    paramClientInfo = new Intent();
    paramClientInfo.putExtra("com.dsi.ant.plugins.antplus.pcc.plugindata", (Bundle)localObject);
    if (paramBundle.getBoolean("b_ForceManualSelect")) {
      paramClientInfo.setComponent(new ComponentName("com.dsi.ant.plugins.antplus", "com.dsi.ant.plugins.antplus.utility.search.Activity_SearchAllDevices"));
    }
    for (;;)
    {
      paramBundle = Message.obtain();
      paramBundle.what = 1;
      localObject = new Bundle();
      ((Bundle)localObject).putParcelable("intent_ActivityToLaunch", paramClientInfo);
      paramBundle.setData((Bundle)localObject);
      dumbfireSendResult(paramMessenger, paramBundle);
      return;
      paramClientInfo.setComponent(new ComponentName("com.dsi.ant.plugins.antplus", "com.dsi.ant.plugins.antplus.utility.search.Dialog_SearchPreferredDevice"));
    }
  }
  
  private void handleAsyncAntDevIdSearchRequest(AntPluginDevice.ClientInfo paramClientInfo, Messenger paramMessenger, Bundle paramBundle)
  {
    if (!paramBundle.containsKey("int_AntDeviceID"))
    {
      paramClientInfo = Message.obtain();
      paramClientInfo.what = -4;
      dumbfireSendResult(paramMessenger, paramClientInfo);
      return;
    }
    int j = paramBundle.getInt("int_AntDeviceID");
    synchronized (this.mConnectedDevices)
    {
      Iterator localIterator = this.mConnectedDevices.iterator();
      AntPluginDevice localAntPluginDevice;
      do
      {
        if (!localIterator.hasNext()) {
          localObject = null;
        }
        do
        {
          if (localObject == null) {
            break label147;
          }
          subscribeToDeviceAndNotifyClient(paramClientInfo, (AntPluginDevice)localObject, paramMessenger, null);
          return;
          localAntPluginDevice = (AntPluginDevice)localIterator.next();
          if (localAntPluginDevice.hasAccess(paramClientInfo.appNamePkg)) {
            break;
          }
          localObject = localAntPluginDevice;
        } while (j == 0);
        i = localAntPluginDevice.mChan_DeviceId.intValue();
      } while (i != j);
      localObject = localAntPluginDevice;
    }
    label147:
    Object localObject = getPackageManager().getInstalledApplications(0).iterator();
    if (!((Iterator)localObject).hasNext()) {}
    for (int i = 0;; i = 1)
    {
      if (i != 0) {
        break label261;
      }
      Log.e("PluginService", "PluginManager not installed");
      paramClientInfo = Message.obtain();
      paramClientInfo.what = -5;
      paramBundle = new Bundle();
      paramBundle.putString("string_DependencyPackageName", "com.dsi.ant.plugins.antplus");
      paramBundle.putString("string_DependencyName", "ANT+ Plugins");
      paramClientInfo.setData(paramBundle);
      dumbfireSendResult(paramMessenger, paramClientInfo);
      return;
      if (!((ApplicationInfo)((Iterator)localObject).next()).packageName.equals("com.dsi.ant.plugins.antplus")) {
        break;
      }
    }
    label261:
    startSearchByAntDeviceID(j, paramClientInfo, paramMessenger, paramBundle);
  }
  
  public AntChannel acquireChannel_helper(PredefinedNetwork paramPredefinedNetwork, Capabilities paramCapabilities, Messenger paramMessenger)
  {
    if (this.mArsComm == null)
    {
      Log.d("PluginService", "acquireChannel_helper called with null ArsComm");
      return null;
    }
    for (;;)
    {
      try
      {
        Thread.sleep(200L);
        try
        {
          AntChannel localAntChannel = this.mArsComm.getChannelProvider().acquireChannel(this, paramPredefinedNetwork, paramCapabilities);
          return localAntChannel;
        }
        catch (ChannelNotAvailableException localChannelNotAvailableException)
        {
          switch (localChannelNotAvailableException.reasonCode)
          {
          }
        }
        Log.e("PluginService", "Could not acquire channel: " + localChannelNotAvailableException.reasonCode);
        paramPredefinedNetwork = Message.obtain();
        paramPredefinedNetwork.what = -3;
        if (paramMessenger == null) {
          break;
        }
        dumbfireSendResult(paramMessenger, paramPredefinedNetwork);
        return null;
      }
      catch (RemoteException paramPredefinedNetwork)
      {
        Log.e("AntPluginService", "RemoteException acquiring channel from ARS");
        paramPredefinedNetwork = Message.obtain();
        paramPredefinedNetwork.what = -4;
        if (paramMessenger == null) {
          break;
        }
        dumbfireSendResult(paramMessenger, paramPredefinedNetwork);
        return null;
      }
      catch (InterruptedException localInterruptedException)
      {
        Log.d("SearchController", "Sleep interupted attempting to acquire not yet ready channel, trying again");
      }
    }
  }
  
  protected void addExisitingDevicesToSearchParams(Bundle paramBundle1, AntPluginDevice.ClientInfo paramClientInfo, Messenger arg3, Bundle paramBundle2)
  {
    paramBundle2 = new ArrayList();
    ArrayList localArrayList = new ArrayList();
    synchronized (this.mConnectedDevices)
    {
      Iterator localIterator = this.mConnectedDevices.iterator();
      AntPluginDevice localAntPluginDevice;
      do
      {
        if (!localIterator.hasNext())
        {
          paramBundle1.putIntegerArrayList("intarl_AvailableConnectedDevices", paramBundle2);
          paramBundle1.putStringArrayList("intarl_AvailableConnectedDevNames", localArrayList);
          return;
        }
        localAntPluginDevice = (AntPluginDevice)localIterator.next();
      } while (localAntPluginDevice.hasAccess(paramClientInfo.appNamePkg));
      paramBundle2.add(localAntPluginDevice.mChan_DeviceId);
      localArrayList.add(localAntPluginDevice.mDeviceName);
    }
  }
  
  protected Bundle composeActivitySearchParams(AntPluginDevice.ClientInfo paramClientInfo, Messenger paramMessenger, Bundle paramBundle)
  {
    Bundle localBundle = getPluginDeviceSearchParamBundle();
    AntChannel localAntChannel = acquireChannel_helper((PredefinedNetwork)localBundle.getSerializable("predefinednetwork_NetKey"), null, paramMessenger);
    if (localAntChannel == null) {
      return null;
    }
    localBundle.putParcelable("antchannel_Channel", localAntChannel);
    localBundle.putInt("int_ProximityBin", paramBundle.getInt("int_ProximityBin"));
    addExisitingDevicesToSearchParams(localBundle, paramClientInfo, paramMessenger, paramBundle);
    localBundle.putParcelable("msgr_SearchResultReceiver", new Messenger(new AntPluginService.4(this, localAntChannel, paramMessenger, paramClientInfo)));
    return localBundle;
  }
  
  public abstract AntPluginDevice createNewDeviceFromSearchResults(AntChannel paramAntChannel, int paramInt, String paramString);
  
  public void dumbfireSendResult(Messenger paramMessenger, Message paramMessage)
  {
    try
    {
      paramMessenger.send(paramMessage);
      return;
    }
    catch (RemoteException paramMessenger)
    {
      Log.e("AntPluginService", "RemoteException sending reply to request access to client. Reply code was: " + paramMessage.what);
    }
  }
  
  protected AntPluginDevice getAlreadyConnectedDevice(int paramInt)
  {
    Iterator localIterator = this.mConnectedDevices.iterator();
    AntPluginDevice localAntPluginDevice;
    do
    {
      if (!localIterator.hasNext()) {
        return null;
      }
      localAntPluginDevice = (AntPluginDevice)localIterator.next();
    } while (localAntPluginDevice.mChan_DeviceId.intValue() != paramInt);
    return localAntPluginDevice;
  }
  
  public abstract Bundle getPluginDeviceSearchParamBundle();
  
  public boolean handleAccessRequest(int paramInt, Messenger paramMessenger, AntPluginDevice.ClientInfo paramClientInfo, Bundle paramBundle)
  {
    switch (paramInt)
    {
    case 2: 
    default: 
      return false;
    case 1: 
      handleActivitySearchAccessRequest(paramClientInfo, paramMessenger, paramBundle);
      return true;
    }
    handleAsyncAntDevIdSearchRequest(paramClientInfo, paramMessenger, paramBundle);
    return true;
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return this.mPccReqAccessHandler.getBinder();
  }
  
  public void onDestroy()
  {
    Log.v("AntPluginService", "Entering OnDestroy()");
    Iterator localIterator = this.mConnectedDevices.iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        this.mConnectedDevices.clear();
        this.mToken_DeviceList.clear();
        if (this.mArsComm != null)
        {
          unbindService(this.mArsConnection);
          this.mArsComm = null;
          this.mArsConnection = null;
        }
        super.onDestroy();
        return;
      }
      ((AntPluginDevice)localIterator.next()).closeDevice();
    }
  }
  
  public void requestAccessToken(Bundle paramBundle)
  {
    Messenger localMessenger = (Messenger)paramBundle.get("msgr_ReqAccResultReceiver");
    if (localMessenger == null) {
      throw new IllegalArgumentException("'MSG_REQACC_PARAM_msgrRESULTRECEIVER' missing from intent bundle");
    }
    if (this.mArsComm == null) {
      this.mArsConnection = new AntPluginService.3(this, paramBundle);
    }
    int i;
    Object localObject;
    label135:
    label162:
    do
    {
      do
      {
        paramBundle = getPackageManager().getInstalledApplications(0).iterator();
        break label135;
        break label135;
        if (!paramBundle.hasNext()) {}
        for (i = 0;; i = 1)
        {
          if (i != 0) {
            break label162;
          }
          Log.e("PluginService", "Binding to ARS failed, not installed");
          paramBundle = Message.obtain();
          paramBundle.what = -5;
          localObject = new Bundle();
          ((Bundle)localObject).putString("string_DependencyPackageName", "com.dsi.ant.service.socket");
          ((Bundle)localObject).putString("string_DependencyName", "ANT Radio Service");
          paramBundle.setData((Bundle)localObject);
          dumbfireSendResult(localMessenger, paramBundle);
          return;
          if (!((ApplicationInfo)paramBundle.next()).packageName.equals("com.dsi.ant.service.socket")) {
            break;
          }
        }
      } while (AntService.bindService(this, this.mArsConnection));
      Log.e("PluginService", "Binding to ARS failed");
      paramBundle = Message.obtain();
      paramBundle.what = -4;
      dumbfireSendResult(localMessenger, paramBundle);
      return;
      i = paramBundle.getInt("int_RequestAccessMode");
      localObject = new AntPluginDevice.ClientInfo();
      ((AntPluginDevice.ClientInfo)localObject).appNamePkg = paramBundle.getString("str_ApplicationNamePackage");
      ((AntPluginDevice.ClientInfo)localObject).appNameLabel = paramBundle.getString("str_ApplicationNameTitle");
      ((AntPluginDevice.ClientInfo)localObject).responseMessenger = ((Messenger)paramBundle.get("msgr_PluginMsgHandler"));
    } while (handleAccessRequest(i, localMessenger, (AntPluginDevice.ClientInfo)localObject, paramBundle));
    paramBundle = Message.obtain();
    paramBundle.what = -3;
    dumbfireSendResult(localMessenger, paramBundle);
  }
  
  public void revokeAccess(UUID paramUUID, AntPluginDevice paramAntPluginDevice)
  {
    synchronized (this.mConnectedDevices)
    {
      this.mToken_DeviceList.remove(paramUUID);
      if (!paramAntPluginDevice.mIsOpen)
      {
        this.mConnectedDevices.remove(paramAntPluginDevice);
        if (this.mConnectedDevices.size() == 0)
        {
          Log.i("AntPluginService", "Closing service because no open devices remain");
          stopSelf();
        }
      }
      return;
    }
  }
  
  public void startSearchByAntDeviceID(int paramInt, AntPluginDevice.ClientInfo paramClientInfo, Messenger paramMessenger, Bundle paramBundle)
  {
    int i = 0;
    Bundle localBundle = getPluginDeviceSearchParamBundle();
    if ((!paramBundle.containsKey("int_ProximityBin")) || (!localBundle.containsKey("predefinednetwork_NetKey")) || (!localBundle.containsKey("int_DevType")) || (!localBundle.containsKey("int_TransType")) || (!localBundle.containsKey("int_Period")) || (!localBundle.containsKey("int_RfFreq")))
    {
      Log.e("AntPluginService", "Bundle is missing parameters");
      paramClientInfo = Message.obtain();
      paramClientInfo.what = -4;
      dumbfireSendResult(paramMessenger, paramClientInfo);
    }
    Object localObject;
    do
    {
      return;
      localObject = acquireChannel_helper((PredefinedNetwork)localBundle.getSerializable("predefinednetwork_NetKey"), null, paramMessenger);
    } while (localObject == null);
    int j = localBundle.getInt("int_DevType");
    int k = localBundle.getInt("int_TransType");
    int m = localBundle.getInt("int_Period");
    int n = localBundle.getInt("int_RfFreq");
    if (paramInt == 0) {}
    for (;;)
    {
      paramBundle = new AntPluginService.5(this, paramMessenger);
      try
      {
        localObject = new AntChannelExecutor((AntChannel)localObject, paramBundle);
        paramClientInfo = new SingleSearchControllerTask(paramInt, n, m, j, k, i, new AntPluginService.6(this, (AntChannelExecutor)localObject, paramMessenger, paramClientInfo));
        try
        {
          ((AntChannelExecutor)localObject).startTask(paramClientInfo, 0);
          return;
        }
        catch (InterruptedException paramClientInfo)
        {
          ((AntChannelExecutor)localObject).shutdown(true);
          paramClientInfo = Message.obtain();
          Log.e("AntPluginService", "Plugin search by deviceID failed to start task on executor");
          paramClientInfo.what = -4;
          dumbfireSendResult(paramMessenger, paramClientInfo);
          return;
        }
        i = paramBundle.getInt("int_ProximityBin");
      }
      catch (RemoteException paramClientInfo)
      {
        paramBundle.onExecutorDeath();
      }
    }
  }
  
  public boolean subscribeToDeviceAndNotifyClient(AntPluginDevice.ClientInfo paramClientInfo, AntPluginDevice paramAntPluginDevice, Messenger paramMessenger, Bundle paramBundle)
  {
    if ((paramClientInfo.appNamePkg == null) || (paramClientInfo.appNameLabel == null) || (paramClientInfo.responseMessenger == null)) {
      throw new IllegalArgumentException("Client missing required info");
    }
    paramClientInfo.accessToken = UUID.randomUUID();
    if ((paramAntPluginDevice == null) || (!paramAntPluginDevice.mIsOpen)) {
      throw new IllegalArgumentException("Trying to subscribe to dead device");
    }
    Message localMessage;
    synchronized (this.mConnectedDevices)
    {
      paramAntPluginDevice.addClient(paramClientInfo);
      this.mToken_DeviceList.put(paramClientInfo.accessToken, paramAntPluginDevice);
      localMessage = Message.obtain();
      localMessage.what = 0;
      ??? = paramBundle;
      if (paramBundle == null) {
        ??? = new Bundle();
      }
      ((Bundle)???).putSerializable("uuid_AccessToken", paramClientInfo.accessToken);
      ((Bundle)???).putString("str_DeviceName", paramAntPluginDevice.mDeviceName);
      ((Bundle)???).putParcelable("msgr_PluginComm", this.mPccMsgHandler);
      ((Bundle)???).putInt("int_InitialDeviceStateCode", paramAntPluginDevice.getCurrentState());
      ((Bundle)???).putInt("int_AntDeviceID", paramAntPluginDevice.mChan_DeviceId.intValue());
      localMessage.setData((Bundle)???);
    }
    return false;
  }
}
