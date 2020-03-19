package com.dsi.ant.plugins.antplus.common;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.dsi.ant.AntService;
import com.dsi.ant.channel.AntChannel;
import com.dsi.ant.channel.Capabilities;
import com.dsi.ant.channel.PredefinedNetwork;
import com.dsi.ant.message.ChannelId;
import com.dsi.ant.message.LowPrioritySearchTimeout;
import com.dsi.ant.plugins.antplus.common.devices.AntPluginAntPlusReceiver;
import com.dsi.ant.plugins.antplus.common.devices.AntPluginDevice;
import com.dsi.ant.plugins.antplus.common.devices.AntPluginDevice.ClientInfo;
import com.dsi.ant.plugins.antplus.pcc.defines.DeviceType;
import com.dsi.ant.plugins.antplus.pccbase.AsyncScanController.AsyncScanResultDeviceInfo;
import com.dsi.ant.plugins.antplus.utility.db.SavedDeviceDb;
import com.dsi.ant.plugins.antplus.utility.ipc.MessageSender;
import com.dsi.ant.plugins.antplus.utility.search.ActivitySearchInfo;
import com.dsi.ant.plugins.antplus.utility.search.ActivitySearchInfo.SearchActivity;
import com.dsi.ant.plugins.antplus.utility.search.IConnectSearch;
import com.dsi.ant.plugins.antplus.utility.search.IConnectSearch.ConnectSearchStopReason;
import com.dsi.ant.plugins.antplus.utility.search.IConnectSearch.IConnectSearchResultHandler;
import com.dsi.ant.plugins.antplus.utility.search.IDiscoverySearch;
import com.dsi.ant.plugins.antplus.utility.search.IDiscoverySearch.DiscoverySearchStopReason;
import com.dsi.ant.plugins.antplus.utility.search.IDiscoverySearch.IDiscoverySearchResultHandler;
import com.dsi.ant.plugins.antplus.utility.search.SearchResultInfo;
import com.dsi.ant.plugins.antplus.utility.search.StandardConnectSearch;
import com.dsi.ant.plugins.antplus.utility.search.StandardDiscoverySearch;
import com.dsi.ant.plugins.antplus.utility.search.tasks.BasicSearchTask.SearchParams;
import com.dsi.ant.plugins.internal.pluginsipc.AntPluginDeviceDbProvider.DeviceDbDeviceInfo;
import com.dsi.ant.plugins.utility.log.LogAnt;
import com.dsi.ant.plugins.utility.log.LogAnt.DebugLevel;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AntPluginService
  extends Service
{
  private final String TAG = getClass().getSimpleName();
  BroadcastReceiver alreadyConnectedDeviceRequestReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      paramAnonymousContext = paramAnonymousIntent.getBundleExtra("com.dsi.ant.plugins.antplus.queryalreadyconnecteddevices.params");
      int i = paramAnonymousContext.getInt("Version_int");
      int j = paramAnonymousContext.getInt("Mode_int");
      if (j != 0) {
        LogAnt.w(AntPluginService.this.TAG, "Received broadcast for already connected devices unrecognized mode: " + j + ", version: " + i);
      }
      for (;;)
      {
        return;
        i = paramAnonymousContext.getInt("DevType_int");
        paramAnonymousIntent = AntPluginService.this.getSupportedShareableDeviceTypes();
        if ((paramAnonymousIntent != null) && (paramAnonymousIntent.size() != 0))
        {
          paramAnonymousIntent = paramAnonymousIntent.iterator();
          while (paramAnonymousIntent.hasNext())
          {
            Object localObject = (DeviceType)paramAnonymousIntent.next();
            if ((i == 0) || (i == ((DeviceType)localObject).getIntValue()))
            {
              AntPluginDeviceDbProvider.DeviceDbDeviceInfo[] arrayOfDeviceDbDeviceInfo = AntPluginService.this.getShareableDevices((DeviceType)localObject);
              if ((arrayOfDeviceDbDeviceInfo != null) && (arrayOfDeviceDbDeviceInfo.length != 0))
              {
                Message localMessage = Message.obtain();
                localMessage.what = 0;
                localMessage.arg1 = paramAnonymousContext.getInt("CmdSeqNum_int");
                localMessage.arg2 = 0;
                Bundle localBundle = new Bundle();
                localMessage.setData(localBundle);
                localBundle.putInt("DevType_int", ((DeviceType)localObject).getIntValue());
                localBundle.putParcelableArray("DevDbInfoList_parcelableArray", arrayOfDeviceDbDeviceInfo);
                localObject = (Messenger)paramAnonymousContext.getParcelable("ResultMsgr_messenger");
                try
                {
                  ((Messenger)localObject).send(localMessage);
                }
                catch (RemoteException localRemoteException)
                {
                  LogAnt.e(AntPluginService.this.TAG, "RemoteException attempting to send getAlreadyConnnectedDevice broadcast request");
                }
              }
            }
          }
        }
      }
    }
  };
  public final ArrayList<ActivitySearchInfo> mActivitySearches = new ArrayList();
  private final Object mArsBoundChange_LOCK = new Object();
  public AntService mArsComm = null;
  public ServiceConnection mArsConnection;
  public volatile boolean mClosed = false;
  public final ArrayList<AntPluginDevice> mConnectedDevices = new ArrayList();
  private final ArrayList<Message> mDeferredMessages = new ArrayList();
  private boolean mIsArsBound = false;
  protected final Map<Messenger, List<MessageSender>> mMessageSenders = new ConcurrentHashMap();
  protected Handler mPccHandler;
  public Messenger mPccMsgHandler;
  Messenger mPccReqAccessHandler;
  public ConcurrentHashMap<UUID, AsyncScanInfo> mToken_AsyncScanList = new ConcurrentHashMap();
  public TreeMap<UUID, AntPluginDevice> mToken_DeviceList = new TreeMap();
  HandlerThread pccHandlerThread;
  HandlerThread reqAccessHandlerThread;
  
  public AntPluginService() {}
  
  private void bindToArs(MessageSender paramMessageSender)
  {
    synchronized (this.mArsBoundChange_LOCK)
    {
      this.mArsConnection = new ServiceConnection()
      {
        private boolean serviceHasDied = false;
        
        public void onServiceConnected(ComponentName arg1, IBinder arg2)
        {
          LogAnt.v(AntPluginService.this.TAG, "ARS Bound, serviceHasDied: " + this.serviceHasDied);
          if (!this.serviceHasDied) {}
          synchronized (AntPluginService.this.mArsBoundChange_LOCK)
          {
            AntPluginService.this.mArsComm = new AntService(???);
            synchronized (AntPluginService.this.mDeferredMessages)
            {
              try
              {
                Iterator localIterator = AntPluginService.this.mDeferredMessages.iterator();
                while (localIterator.hasNext())
                {
                  Message localMessage = (Message)localIterator.next();
                  AntPluginService.this.mPccReqAccessHandler.send(localMessage);
                }
                return;
              }
              catch (RemoteException localRemoteException)
              {
                LogAnt.e(AntPluginService.this.TAG, "RemoteException sending message to local messenger, inconceivable");
                AntPluginService.this.mDeferredMessages.clear();
              }
            }
          }
        }
        
        public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
        {
          LogAnt.e(AntPluginService.this.TAG, "Plugin-ARS binder OnServiceDisconnected!");
          this.serviceHasDied = true;
          AntPluginService.this.unbindFromArs();
          paramAnonymousComponentName = AntPluginService.this.mConnectedDevices.iterator();
          while (paramAnonymousComponentName.hasNext()) {
            ((AntPluginDevice)paramAnonymousComponentName.next()).closeDevice();
          }
        }
      };
      if (!this.mIsArsBound)
      {
        this.mIsArsBound = true;
        if (!AntService.bindService(this, this.mArsConnection))
        {
          LogAnt.e(this.TAG, "Binding to ARS failed");
          Message localMessage = Message.obtain();
          localMessage.what = -4;
          dumbfireSendResult(paramMessageSender, localMessage);
          unbindFromArs();
        }
      }
      else
      {
        LogAnt.d(this.TAG, "ARS bind called when bound or bind is still in progress.");
      }
      return;
    }
  }
  
  private Bundle composeActivitySearchParams(final AntPluginDevice.ClientInfo paramClientInfo, final MessageSender paramMessageSender, final Bundle paramBundle)
  {
    Bundle localBundle = getPluginDeviceSearchParamBundle(paramBundle);
    ??? = acquireChannelWithRssi_helper((PredefinedNetwork)localBundle.getSerializable("predefinednetwork_NetKey"), null, paramMessageSender, paramClientInfo, paramBundle.getInt("int_RssiMode", 0));
    if (??? == null) {
      return null;
    }
    final ActivitySearchInfo localActivitySearchInfo = createActivitySearchInfo((AntChannel)???, paramBundle, this.reqAccessHandlerThread.getLooper());
    synchronized (this.mActivitySearches)
    {
      this.mActivitySearches.add(localActivitySearchInfo);
      localBundle.putParcelable("cls_ActivitySearchInfo", localActivitySearchInfo);
      addExisitingDevicesToSearchParams(localBundle, paramClientInfo, paramMessageSender, paramBundle);
      localBundle.putParcelable("msgr_SearchResultReceiver", new Messenger(new Handler()
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          LogAnt.v(AntPluginService.this.TAG, "ReqDev Handler received: " + paramAnonymousMessage.what);
          int m = 1;
          int n = 1;
          int i1 = 1;
          int i = 1;
          Message localMessage = Message.obtain();
          label210:
          synchronized (AntPluginService.this.mActivitySearches)
          {
            AntPluginService.this.mActivitySearches.remove(localActivitySearchInfo);
            j = n;
            k = i1;
          }
          int j = n;
          int k = i1;
          ArrayList localArrayList = AntPluginService.this.mConnectedDevices;
          j = n;
          k = i1;
          k = m;
          for (;;)
          {
            try
            {
              switch (paramAnonymousMessage.arg1)
              {
              case 0: 
                k = m;
                LogAnt.e(AntPluginService.this.TAG, "Plugin UI search failed internally: Unrecognized req acc success code.");
                k = m;
                localMessage.what = -4;
                k = m;
                AntPluginService.this.dumbfireSendResult(paramMessageSender, localMessage);
                k = m;
                if ((1 == 0) || (localActivitySearchInfo.channel == null)) {
                  break label210;
                }
                localActivitySearchInfo.channel.release();
                return;
              }
            }
            finally
            {
              boolean bool;
              j = k;
            }
            k = m;
            ??? = paramAnonymousMessage.getData();
            k = m;
            paramAnonymousMessage = (SearchResultInfo)((Bundle)???).getParcelable("clsRESULTINFO");
            k = m;
            ??? = AntPluginService.this.getDeviceInfoById(paramAnonymousMessage.id.getDeviceNumber(), ((Bundle)???).getString("str_SelectedDeviceName"), paramBundle);
            k = m;
            paramAnonymousMessage = AntPluginService.this.createNewDeviceFromSearchResults(localActivitySearchInfo.channel, (AntPluginDeviceDbProvider.DeviceDbDeviceInfo)???, paramBundle, paramAnonymousMessage);
            if (paramAnonymousMessage == null)
            {
              k = m;
              LogAnt.e(AntPluginService.this.TAG, "Plugin UI search failed internally: Device instantiation failed.");
              k = m;
              localMessage.what = -4;
              k = m;
              AntPluginService.this.dumbfireSendResult(paramMessageSender, localMessage);
              k = m;
              if ((1 == 0) || (localActivitySearchInfo.channel == null)) {
                break label210;
              }
              localActivitySearchInfo.channel.release();
              return;
            }
            i = 0;
            k = i;
            j = i;
            k = i;
            bool = AntPluginService.this.subscribeToDeviceAndNotifyClient(paramClientInfo, paramAnonymousMessage, paramMessageSender, null, paramBundle);
            if (!bool) {
              i = 1;
            }
            if ((i == 0) || (localActivitySearchInfo.channel == null)) {
              break label210;
            }
            localActivitySearchInfo.channel.release();
            return;
            k = m;
            j = paramAnonymousMessage.getData().getInt("int_ChannelDeviceId");
            k = m;
            ??? = AntPluginService.this.getAlreadyConnectedDevice(j, null, paramBundle);
            paramAnonymousMessage = (Message)???;
            if (??? == null)
            {
              k = m;
              LogAnt.e(AntPluginService.this.TAG, "Already connected device disconnected during search");
              k = m;
              localMessage.what = -4;
              k = m;
              paramMessageSender.send(localMessage);
              k = m;
              if ((1 == 0) || (localActivitySearchInfo.channel == null)) {
                break label210;
              }
              localActivitySearchInfo.channel.release();
              return;
              break;
            }
          }
        }
      }));
      return localBundle;
    }
  }
  
  private void connectArsAndRepost(Message paramMessage, MessageSender paramMessageSender)
  {
    LogAnt.v(this.TAG, "Attempting binding to ARS");
    int j = 0;
    ??? = getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      i = j;
      if (!((Iterator)???).hasNext()) {
        break;
      }
      ??? = (ApplicationInfo)((Iterator)???).next();
    } while (!((ApplicationInfo)???).packageName.equals("com.dsi.ant.service.socket"));
    int i = j;
    if (((ApplicationInfo)???).enabled) {
      i = 1;
    }
    if (i == 0)
    {
      LogAnt.e(this.TAG, "Binding to ARS failed, not installed");
      paramMessage = Message.obtain();
      paramMessage.what = -5;
      ??? = new Bundle();
      ((Bundle)???).putString("string_DependencyPackageName", "com.dsi.ant.service.socket");
      ((Bundle)???).putString("string_DependencyName", "ANT Radio Service");
      paramMessage.setData((Bundle)???);
      dumbfireSendResult(paramMessageSender, paramMessage);
      return;
    }
    synchronized (this.mArsBoundChange_LOCK)
    {
      ??? = this.mArsComm;
      if (??? == null) {}
    }
    for (;;)
    {
      try
      {
        this.mPccReqAccessHandler.send(Message.obtain(paramMessage));
        return;
        paramMessage = finally;
        throw paramMessage;
      }
      catch (RemoteException paramMessage)
      {
        LogAnt.e(this.TAG, "RemoteException sending message to local messenger, inconceivable");
        continue;
      }
      synchronized (this.mDeferredMessages)
      {
        this.mDeferredMessages.add(Message.obtain(paramMessage));
        if (!this.mIsArsBound) {
          bindToArs(paramMessageSender);
        }
      }
      LogAnt.v(this.TAG, "ARS already bound or bind is still in progress.");
    }
  }
  
  private void handleActivitySearchAccessRequest(AntPluginDevice.ClientInfo paramClientInfo, MessageSender paramMessageSender, Bundle paramBundle)
  {
    if (!paramBundle.containsKey("int_ProximityBin"))
    {
      LogAnt.e(this.TAG, "Bundle is missing proximity parameter");
      paramClientInfo = Message.obtain();
      paramClientInfo.what = -4;
      dumbfireSendResult(paramMessageSender, paramClientInfo);
    }
    Bundle localBundle;
    do
    {
      return;
      if (paramBundle.getInt("int_ProximityBin") == -1) {
        paramBundle.putInt("int_ProximityBin", getSharedPreferences(getResources().getString(2131099693), 0).getInt(getResources().getString(2131099694), 0));
      }
      localBundle = composeActivitySearchParams(paramClientInfo, paramMessageSender, paramBundle);
    } while (localBundle == null);
    paramClientInfo = new Intent();
    paramClientInfo.putExtra("com.dsi.ant.plugins.antplus.pcc.plugindata", localBundle);
    if (paramBundle.getBoolean("b_ForceManualSelect")) {
      paramClientInfo.setComponent(new ComponentName("com.dsi.ant.plugins.antplus", "com.dsi.ant.plugins.antplus.utility.search.Activity_SearchAllDevices"));
    }
    for (;;)
    {
      paramBundle = Message.obtain();
      paramBundle.what = 1;
      localBundle = new Bundle();
      localBundle.putParcelable("intent_ActivityToLaunch", paramClientInfo);
      paramBundle.setData(localBundle);
      dumbfireSendResult(paramMessageSender, paramBundle);
      return;
      paramClientInfo.setComponent(new ComponentName("com.dsi.ant.plugins.antplus", "com.dsi.ant.plugins.antplus.utility.search.Dialog_SearchPreferredDevice"));
    }
  }
  
  private void startAsyncScan(AntChannel paramAntChannel, final AsyncScanInfo paramAsyncScanInfo)
  {
    paramAsyncScanInfo.resultHandled = false;
    paramAsyncScanInfo.continueTask = null;
    paramAsyncScanInfo.scan.start(paramAntChannel, new IDiscoverySearch.IDiscoverySearchResultHandler()
    {
      public void onDeviceFound(SearchResultInfo paramAnonymousSearchResultInfo)
      {
        if (AntPluginService.this.isExistingDevice(paramAnonymousSearchResultInfo.id.getDeviceNumber(), paramAsyncScanInfo.reqParams)) {
          return;
        }
        Message localMessage = Message.obtain();
        localMessage.what = 2;
        Bundle localBundle2 = paramAnonymousSearchResultInfo.otherInfo;
        Bundle localBundle1 = localBundle2;
        if (localBundle2 == null) {
          localBundle1 = new Bundle();
        }
        localBundle1.putParcelable("parcelable_AsyncScanResultDeviceInfo", new AsyncScanController.AsyncScanResultDeviceInfo(null, AntPluginService.this.getDeviceInfoById(paramAnonymousSearchResultInfo.id.getDeviceNumber(), null, paramAsyncScanInfo.reqParams), false));
        localMessage.setData(localBundle1);
        try
        {
          paramAsyncScanInfo.getCurrentResultHandler().send(localMessage);
          return;
        }
        catch (RemoteException paramAnonymousSearchResultInfo)
        {
          LogAnt.e(AntPluginService.this.TAG, "RemoteException sending async scan result, closing scan.");
          AntPluginService.this.shutdownAndRemoveAsyncSearch(paramAsyncScanInfo.client.accessToken);
        }
      }
      
      public void onDiscoveryStopped(IDiscoverySearch.DiscoverySearchStopReason paramAnonymousDiscoverySearchStopReason, AntChannel paramAnonymousAntChannel)
      {
        Message localMessage = Message.obtain();
        paramAsyncScanInfo.resultHandled = true;
        AntChannel localAntChannel = paramAnonymousAntChannel;
        switch (AntPluginService.8.$SwitchMap$com$dsi$ant$plugins$antplus$utility$search$IDiscoverySearch$DiscoverySearchStopReason[paramAnonymousDiscoverySearchStopReason.ordinal()])
        {
        default: 
          localAntChannel = paramAnonymousAntChannel;
        }
        for (;;)
        {
          if (paramAsyncScanInfo.continueTask != null) {
            paramAsyncScanInfo.continueTask.run(localAntChannel);
          }
          return;
          localAntChannel = paramAnonymousAntChannel;
          if (paramAsyncScanInfo.continueTask == null)
          {
            LogAnt.i(AntPluginService.this.TAG, "Async scan cancelled.");
            paramAnonymousAntChannel.release();
            localAntChannel = null;
            continue;
            LogAnt.e(AntPluginService.this.TAG, "AsyncScan: unexpected timeout!");
            paramAnonymousAntChannel.release();
            localAntChannel = null;
            LogAnt.e(AntPluginService.this.TAG, "Plugin Async scan: scan crashed.");
            localMessage.what = -4;
            AntPluginService.this.dumbfireSendResult(paramAsyncScanInfo.getCurrentResultHandler(), localMessage);
          }
        }
      }
      
      public void onPing()
      {
        Message localMessage = Message.obtain();
        localMessage.what = -7;
        try
        {
          paramAsyncScanInfo.getCurrentResultHandler().send(localMessage);
          return;
        }
        catch (RemoteException localRemoteException)
        {
          LogAnt.e(AntPluginService.this.TAG, "RemoteException sending async scan timeout ping, closing scan.");
          AntPluginService.this.shutdownAndRemoveAsyncSearch(paramAsyncScanInfo.client.accessToken);
        }
      }
    });
  }
  
  public AntChannel acquireChannelWithRssi_helper(PredefinedNetwork paramPredefinedNetwork, Capabilities paramCapabilities, MessageSender paramMessageSender, AntPluginDevice.ClientInfo paramClientInfo, int paramInt)
  {
    Capabilities localCapabilities = null;
    switch (paramInt)
    {
    }
    for (;;)
    {
      return acquireChannel_helper(paramPredefinedNetwork, paramCapabilities, localCapabilities, paramMessageSender, paramClientInfo);
      localCapabilities = new Capabilities();
      localCapabilities.supportRssi(true);
    }
  }
  
  /* Error */
  public AntChannel acquireChannel_helper(PredefinedNetwork paramPredefinedNetwork, Capabilities paramCapabilities1, Capabilities paramCapabilities2, MessageSender paramMessageSender, AntPluginDevice.ClientInfo paramClientInfo)
  {
    // Byte code:
    //   0: invokestatic 438	android/os/SystemClock:elapsedRealtime	()J
    //   3: lstore 6
    //   5: invokestatic 438	android/os/SystemClock:elapsedRealtime	()J
    //   8: lload 6
    //   10: lsub
    //   11: ldc2_w 439
    //   14: lcmp
    //   15: ifle +37 -> 52
    //   18: aload_0
    //   19: getfield 89	com/dsi/ant/plugins/antplus/common/AntPluginService:TAG	Ljava/lang/String;
    //   22: ldc_w 442
    //   25: invokestatic 162	com/dsi/ant/plugins/utility/log/LogAnt:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   28: invokestatic 168	android/os/Message:obtain	()Landroid/os/Message;
    //   31: astore_1
    //   32: aload_1
    //   33: bipush -4
    //   35: putfield 172	android/os/Message:what	I
    //   38: aload 4
    //   40: ifnull +10 -> 50
    //   43: aload_0
    //   44: aload 4
    //   46: aload_1
    //   47: invokevirtual 176	com/dsi/ant/plugins/antplus/common/AntPluginService:dumbfireSendResult	(Lcom/dsi/ant/plugins/antplus/utility/ipc/MessageSender;Landroid/os/Message;)V
    //   50: aconst_null
    //   51: areturn
    //   52: aload_0
    //   53: getfield 91	com/dsi/ant/plugins/antplus/common/AntPluginService:mArsComm	Lcom/dsi/ant/AntService;
    //   56: invokevirtual 446	com/dsi/ant/AntService:getChannelProvider	()Lcom/dsi/ant/channel/AntChannelProvider;
    //   59: aload_0
    //   60: aload_1
    //   61: aload_2
    //   62: aload_3
    //   63: invokevirtual 452	com/dsi/ant/channel/AntChannelProvider:acquireChannel	(Landroid/content/Context;Lcom/dsi/ant/channel/PredefinedNetwork;Lcom/dsi/ant/channel/Capabilities;Lcom/dsi/ant/channel/Capabilities;)Lcom/dsi/ant/channel/AntChannel;
    //   66: astore 8
    //   68: aload 8
    //   70: invokevirtual 457	com/dsi/ant/channel/AntChannel:disableEventBuffer	()V
    //   73: aload 8
    //   75: areturn
    //   76: astore 8
    //   78: aload_0
    //   79: getfield 89	com/dsi/ant/plugins/antplus/common/AntPluginService:TAG	Ljava/lang/String;
    //   82: ldc_w 459
    //   85: invokestatic 162	com/dsi/ant/plugins/utility/log/LogAnt:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   88: invokestatic 168	android/os/Message:obtain	()Landroid/os/Message;
    //   91: astore 8
    //   93: aload 8
    //   95: bipush -4
    //   97: putfield 172	android/os/Message:what	I
    //   100: aload 4
    //   102: ifnull +258 -> 360
    //   105: aload_0
    //   106: aload 4
    //   108: aload 8
    //   110: invokevirtual 176	com/dsi/ant/plugins/antplus/common/AntPluginService:dumbfireSendResult	(Lcom/dsi/ant/plugins/antplus/utility/ipc/MessageSender;Landroid/os/Message;)V
    //   113: goto +247 -> 360
    //   116: astore 9
    //   118: aload_0
    //   119: getfield 89	com/dsi/ant/plugins/antplus/common/AntPluginService:TAG	Ljava/lang/String;
    //   122: new 461	java/lang/StringBuilder
    //   125: dup
    //   126: invokespecial 462	java/lang/StringBuilder:<init>	()V
    //   129: ldc_w 464
    //   132: invokevirtual 468	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: aload 9
    //   137: invokevirtual 472	com/dsi/ant/channel/AntCommandFailedException:getFailureReason	()Lcom/dsi/ant/channel/AntCommandFailureReason;
    //   140: invokevirtual 477	com/dsi/ant/channel/AntCommandFailureReason:toString	()Ljava/lang/String;
    //   143: invokevirtual 468	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: invokevirtual 478	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   149: invokestatic 162	com/dsi/ant/plugins/utility/log/LogAnt:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   152: goto -79 -> 73
    //   155: astore 8
    //   157: getstatic 482	com/dsi/ant/plugins/antplus/common/AntPluginService$8:$SwitchMap$com$dsi$ant$channel$ChannelNotAvailableReason	[I
    //   160: aload 8
    //   162: getfield 486	com/dsi/ant/channel/ChannelNotAvailableException:reasonCode	Lcom/dsi/ant/channel/ChannelNotAvailableReason;
    //   165: invokevirtual 492	com/dsi/ant/channel/ChannelNotAvailableReason:ordinal	()I
    //   168: iaload
    //   169: tableswitch	default:+193->362, 1:+82->251, 2:+82->251, 3:+141->310
    //   196: aload_0
    //   197: getfield 89	com/dsi/ant/plugins/antplus/common/AntPluginService:TAG	Ljava/lang/String;
    //   200: new 461	java/lang/StringBuilder
    //   203: dup
    //   204: invokespecial 462	java/lang/StringBuilder:<init>	()V
    //   207: ldc_w 494
    //   210: invokevirtual 468	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   213: aload 8
    //   215: getfield 486	com/dsi/ant/channel/ChannelNotAvailableException:reasonCode	Lcom/dsi/ant/channel/ChannelNotAvailableReason;
    //   218: invokevirtual 497	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   221: invokevirtual 478	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   224: invokestatic 162	com/dsi/ant/plugins/utility/log/LogAnt:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   227: invokestatic 168	android/os/Message:obtain	()Landroid/os/Message;
    //   230: astore_1
    //   231: aload_1
    //   232: bipush -3
    //   234: putfield 172	android/os/Message:what	I
    //   237: aload 4
    //   239: ifnull +10 -> 249
    //   242: aload_0
    //   243: aload 4
    //   245: aload_1
    //   246: invokevirtual 176	com/dsi/ant/plugins/antplus/common/AntPluginService:dumbfireSendResult	(Lcom/dsi/ant/plugins/antplus/utility/ipc/MessageSender;Landroid/os/Message;)V
    //   249: aconst_null
    //   250: areturn
    //   251: ldc2_w 498
    //   254: invokestatic 505	java/lang/Thread:sleep	(J)V
    //   257: goto -252 -> 5
    //   260: astore 8
    //   262: aload_0
    //   263: getfield 89	com/dsi/ant/plugins/antplus/common/AntPluginService:TAG	Ljava/lang/String;
    //   266: ldc_w 507
    //   269: invokestatic 184	com/dsi/ant/plugins/utility/log/LogAnt:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   272: goto -267 -> 5
    //   275: astore_1
    //   276: aload_0
    //   277: getfield 89	com/dsi/ant/plugins/antplus/common/AntPluginService:TAG	Ljava/lang/String;
    //   280: ldc_w 509
    //   283: invokestatic 162	com/dsi/ant/plugins/utility/log/LogAnt:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   286: invokestatic 168	android/os/Message:obtain	()Landroid/os/Message;
    //   289: astore_1
    //   290: aload_1
    //   291: bipush -4
    //   293: putfield 172	android/os/Message:what	I
    //   296: aload 4
    //   298: ifnull +10 -> 308
    //   301: aload_0
    //   302: aload 4
    //   304: aload_1
    //   305: invokevirtual 176	com/dsi/ant/plugins/antplus/common/AntPluginService:dumbfireSendResult	(Lcom/dsi/ant/plugins/antplus/utility/ipc/MessageSender;Landroid/os/Message;)V
    //   308: aconst_null
    //   309: areturn
    //   310: aload 5
    //   312: ifnull -116 -> 196
    //   315: aload 5
    //   317: getfield 514	com/dsi/ant/plugins/antplus/common/devices/AntPluginDevice$ClientInfo:pluginLibVersion	I
    //   320: sipush 20100
    //   323: if_icmplt -127 -> 196
    //   326: aload_0
    //   327: getfield 89	com/dsi/ant/plugins/antplus/common/AntPluginService:TAG	Ljava/lang/String;
    //   330: ldc_w 516
    //   333: invokestatic 184	com/dsi/ant/plugins/utility/log/LogAnt:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   336: invokestatic 168	android/os/Message:obtain	()Landroid/os/Message;
    //   339: astore_1
    //   340: aload_1
    //   341: bipush -10
    //   343: putfield 172	android/os/Message:what	I
    //   346: aload 4
    //   348: ifnull +10 -> 358
    //   351: aload_0
    //   352: aload 4
    //   354: aload_1
    //   355: invokevirtual 176	com/dsi/ant/plugins/antplus/common/AntPluginService:dumbfireSendResult	(Lcom/dsi/ant/plugins/antplus/utility/ipc/MessageSender;Landroid/os/Message;)V
    //   358: aconst_null
    //   359: areturn
    //   360: aconst_null
    //   361: areturn
    //   362: goto -166 -> 196
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	365	0	this	AntPluginService
    //   0	365	1	paramPredefinedNetwork	PredefinedNetwork
    //   0	365	2	paramCapabilities1	Capabilities
    //   0	365	3	paramCapabilities2	Capabilities
    //   0	365	4	paramMessageSender	MessageSender
    //   0	365	5	paramClientInfo	AntPluginDevice.ClientInfo
    //   3	6	6	l	long
    //   66	8	8	localAntChannel	AntChannel
    //   76	1	8	localNullPointerException	NullPointerException
    //   91	18	8	localMessage	Message
    //   155	59	8	localChannelNotAvailableException	com.dsi.ant.channel.ChannelNotAvailableException
    //   260	1	8	localInterruptedException	InterruptedException
    //   116	20	9	localAntCommandFailedException	com.dsi.ant.channel.AntCommandFailedException
    // Exception table:
    //   from	to	target	type
    //   52	68	76	java/lang/NullPointerException
    //   68	73	116	com/dsi/ant/channel/AntCommandFailedException
    //   52	68	155	com/dsi/ant/channel/ChannelNotAvailableException
    //   68	73	155	com/dsi/ant/channel/ChannelNotAvailableException
    //   78	100	155	com/dsi/ant/channel/ChannelNotAvailableException
    //   105	113	155	com/dsi/ant/channel/ChannelNotAvailableException
    //   118	152	155	com/dsi/ant/channel/ChannelNotAvailableException
    //   251	257	260	java/lang/InterruptedException
    //   5	38	275	android/os/RemoteException
    //   43	50	275	android/os/RemoteException
    //   52	68	275	android/os/RemoteException
    //   68	73	275	android/os/RemoteException
    //   78	100	275	android/os/RemoteException
    //   105	113	275	android/os/RemoteException
    //   118	152	275	android/os/RemoteException
    //   157	196	275	android/os/RemoteException
    //   196	237	275	android/os/RemoteException
    //   242	249	275	android/os/RemoteException
    //   251	257	275	android/os/RemoteException
    //   262	272	275	android/os/RemoteException
    //   315	346	275	android/os/RemoteException
    //   351	358	275	android/os/RemoteException
  }
  
  protected void addExisitingDevicesToSearchParams(Bundle paramBundle1, AntPluginDevice.ClientInfo paramClientInfo, MessageSender arg3, Bundle paramBundle2)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    synchronized (this.mConnectedDevices)
    {
      Iterator localIterator = this.mConnectedDevices.iterator();
      while (localIterator.hasNext())
      {
        AntPluginDevice localAntPluginDevice = (AntPluginDevice)localIterator.next();
        if (!localAntPluginDevice.hasAccess(paramClientInfo.appNamePkg, paramBundle2))
        {
          Bundle localBundle = new Bundle();
          localBundle.putInt("int_ChannelDeviceId", localAntPluginDevice.deviceInfo.antDeviceNumber.intValue());
          localArrayList1.add(localBundle);
          localArrayList2.add(localAntPluginDevice.deviceInfo);
        }
      }
    }
    paramBundle1.putParcelableArrayList("albdlCONNECTEDDEVICES", localArrayList1);
    paramBundle1.putParcelableArrayList("albdlCONNECTEDDEVICEINFOS", localArrayList2);
  }
  
  protected void connectToAsyncResult(final AsyncScanInfo paramAsyncScanInfo, final Bundle paramBundle)
  {
    Object localObject1 = (AsyncScanController.AsyncScanResultDeviceInfo)paramBundle.getParcelable("parcelable_AsyncScanResultDeviceInfo");
    final MessageSender localMessageSender = new MessageSender((Messenger)paramBundle.get("msgr_ReqAccResultReceiver"));
    ((List)this.mMessageSenders.get(paramAsyncScanInfo.client.responseMessenger.mMessenger)).add(localMessageSender);
    Object localObject2 = getAlreadyConnectedDevice(((AsyncScanController.AsyncScanResultDeviceInfo)localObject1).getAntDeviceNumber(), null, paramBundle);
    if (localObject2 != null)
    {
      subscribeToDeviceAndNotifyClient(paramAsyncScanInfo.client, (AntPluginDevice)localObject2, localMessageSender, null, paramBundle);
      shutdownAndRemoveAsyncSearch(paramAsyncScanInfo.client.accessToken);
      return;
    }
    localObject2 = new BasicSearchTask.SearchParams();
    Bundle localBundle = getPluginDeviceSearchParamBundle(paramBundle);
    ((BasicSearchTask.SearchParams)localObject2).id = new ChannelId(((AsyncScanController.AsyncScanResultDeviceInfo)localObject1).getAntDeviceNumber(), localBundle.getInt("int_DevType"), localBundle.getInt("int_TransType"));
    ((BasicSearchTask.SearchParams)localObject2).rfFreq = localBundle.getInt("int_RfFreq");
    ((BasicSearchTask.SearchParams)localObject2).channelPeriod = localBundle.getInt("int_Period");
    ((BasicSearchTask.SearchParams)localObject2).proximityThreshold = 0;
    ((BasicSearchTask.SearchParams)localObject2).searchTimeout = LowPrioritySearchTimeout.TEN_SECONDS;
    localObject1 = getSingleDeviceSearch((BasicSearchTask.SearchParams)localObject2, paramBundle, this.reqAccessHandlerThread.getLooper());
    if (paramAsyncScanInfo.resultHandled)
    {
      localObject2 = Message.obtain();
      ((Message)localObject2).what = -4;
      dumbfireSendResult(localMessageSender, (Message)localObject2);
    }
    paramAsyncScanInfo.continueTask = new AntPluginService.AsyncScanInfo.IContinueTask()
    {
      public void run(AntChannel paramAnonymousAntChannel)
      {
        if (paramAnonymousAntChannel == null)
        {
          paramAnonymousAntChannel = Message.obtain();
          paramAnonymousAntChannel.what = -4;
          AntPluginService.this.dumbfireSendResult(localMessageSender, paramAnonymousAntChannel);
          return;
        }
        this.val$search.start(paramAnonymousAntChannel, new IConnectSearch.IConnectSearchResultHandler()
        {
          public void onConnected(SearchResultInfo paramAnonymous2SearchResultInfo, AntChannel paramAnonymous2AntChannel)
          {
            AntPluginDeviceDbProvider.DeviceDbDeviceInfo localDeviceDbDeviceInfo = AntPluginService.this.getDeviceInfoById(paramAnonymous2SearchResultInfo.id.getDeviceNumber(), null, AntPluginService.6.this.val$reqParams);
            paramAnonymous2SearchResultInfo = AntPluginService.this.createNewDeviceFromSearchResults(paramAnonymous2AntChannel, localDeviceDbDeviceInfo, AntPluginService.6.this.val$reqParams, paramAnonymous2SearchResultInfo);
            if (paramAnonymous2SearchResultInfo == null)
            {
              paramAnonymous2SearchResultInfo = Message.obtain();
              LogAnt.e(AntPluginService.this.TAG, "Plugin async scan connect failed internally: Device instantiation failed.");
              paramAnonymous2SearchResultInfo.what = -4;
              AntPluginService.this.dumbfireSendResult(AntPluginService.6.this.val$msgr_ResultMessenger, paramAnonymous2SearchResultInfo);
              paramAnonymous2AntChannel.release();
            }
            for (;;)
            {
              AntPluginService.this.shutdownAndRemoveAsyncSearch(AntPluginService.6.this.val$asyncScanInfo.client.accessToken);
              return;
              if (!AntPluginService.this.subscribeToDeviceAndNotifyClient(AntPluginService.6.this.val$asyncScanInfo.client, paramAnonymous2SearchResultInfo, AntPluginService.6.this.val$msgr_ResultMessenger, null, AntPluginService.6.this.val$reqParams)) {
                paramAnonymous2AntChannel.release();
              }
            }
          }
          
          public void onSearchStopped(IConnectSearch.ConnectSearchStopReason paramAnonymous2ConnectSearchStopReason, AntChannel paramAnonymous2AntChannel)
          {
            Message localMessage = Message.obtain();
            switch (AntPluginService.8.$SwitchMap$com$dsi$ant$plugins$antplus$utility$search$IConnectSearch$ConnectSearchStopReason[paramAnonymous2ConnectSearchStopReason.ordinal()])
            {
            default: 
              return;
            case 1: 
              localMessage.what = -7;
              AntPluginService.this.dumbfireSendResult(AntPluginService.6.this.val$msgr_ResultMessenger, localMessage);
              AntPluginService.this.startAsyncScan(paramAnonymous2AntChannel, AntPluginService.6.this.val$asyncScanInfo);
              return;
            case 2: 
              LogAnt.e(AntPluginService.this.TAG, "Connect to async result: interrupted!");
              paramAnonymous2AntChannel.release();
            }
            LogAnt.e(AntPluginService.this.TAG, "Connect to device: search crashed.");
            localMessage.what = -4;
            AntPluginService.this.dumbfireSendResult(AntPluginService.6.this.val$msgr_ResultMessenger, localMessage);
            AntPluginService.this.shutdownAndRemoveAsyncSearch(AntPluginService.6.this.val$asyncScanInfo.client.accessToken);
          }
        });
      }
    };
    paramAsyncScanInfo.closeAsyncScan();
  }
  
  protected ActivitySearchInfo createActivitySearchInfo(AntChannel paramAntChannel, Bundle paramBundle, final Looper paramLooper)
  {
    final BasicSearchTask.SearchParams localSearchParams = new BasicSearchTask.SearchParams();
    Object localObject = getPluginDeviceSearchParamBundle(paramBundle);
    int i = ((Bundle)localObject).getInt("int_DevType");
    int j = ((Bundle)localObject).getInt("int_TransType");
    localSearchParams.id = new ChannelId(0, i, j);
    localSearchParams.rfFreq = ((Bundle)localObject).getInt("int_RfFreq");
    localSearchParams.channelPeriod = ((Bundle)localObject).getInt("int_Period");
    localSearchParams.proximityThreshold = paramBundle.getInt("int_ProximityBin");
    localSearchParams.searchTimeout = LowPrioritySearchTimeout.TEN_SECONDS;
    ActivitySearchInfo local4 = new ActivitySearchInfo()
    {
      public IConnectSearch getScanResultSearch(SearchResultInfo paramAnonymousSearchResultInfo, Bundle paramAnonymousBundle)
      {
        localSearchParams.id = paramAnonymousSearchResultInfo.id;
        localSearchParams.proximityThreshold = 0;
        return AntPluginService.this.getSingleDeviceSearch(localSearchParams, paramAnonymousBundle, paramLooper);
      }
    };
    local4.scan = getScanner(localSearchParams, paramLooper, 20000L, paramBundle);
    local4.initialProximityThreshold = localSearchParams.proximityThreshold;
    local4.channel = paramAntChannel;
    paramAntChannel = new SavedDeviceDb(this);
    try
    {
      localObject = paramAntChannel.getPreferredDevice(((Bundle)localObject).getString("str_PluginName"));
      paramAntChannel.close();
      if (localObject != null)
      {
        localSearchParams.id = new ChannelId(((AntPluginDeviceDbProvider.DeviceDbDeviceInfo)localObject).antDeviceNumber.intValue(), i, j);
        localSearchParams.proximityThreshold = 0;
        local4.preferredDeviceSearch = getSingleDeviceSearch(localSearchParams, paramBundle, paramLooper);
        local4.preferredDeviceName = ((AntPluginDeviceDbProvider.DeviceDbDeviceInfo)localObject).visibleName;
      }
      return local4;
    }
    finally
    {
      paramAntChannel.close();
    }
  }
  
  public abstract AntPluginDevice createNewDeviceFromSearchResults(AntChannel paramAntChannel, AntPluginDeviceDbProvider.DeviceDbDeviceInfo paramDeviceDbDeviceInfo, Bundle paramBundle, SearchResultInfo paramSearchResultInfo);
  
  public void dumbfireSendResult(MessageSender paramMessageSender, Message paramMessage)
  {
    try
    {
      paramMessageSender.send(paramMessage);
      return;
    }
    catch (RemoteException paramMessageSender)
    {
      LogAnt.e(this.TAG, "RemoteException dumbfiring reply to client. Reply code was: " + paramMessage.what);
    }
  }
  
  protected AntPluginDevice getAlreadyConnectedDevice(int paramInt, String paramString, Bundle paramBundle)
  {
    synchronized (this.mConnectedDevices)
    {
      Iterator localIterator = this.mConnectedDevices.iterator();
      while (localIterator.hasNext())
      {
        AntPluginDevice localAntPluginDevice = (AntPluginDevice)localIterator.next();
        if (((paramInt == 0) || (localAntPluginDevice.deviceInfo.antDeviceNumber.intValue() == paramInt)) && ((paramString == null) || (!localAntPluginDevice.hasAccess(paramString, paramBundle)))) {
          return localAntPluginDevice;
        }
      }
      return null;
    }
  }
  
  protected AntPluginDeviceDbProvider.DeviceDbDeviceInfo getDeviceInfoById(int paramInt, String paramString, Bundle paramBundle)
  {
    paramBundle = getPluginDeviceSearchParamBundle(paramBundle).getString("str_PluginName");
    Object localObject = new SavedDeviceDb(this);
    for (;;)
    {
      try
      {
        paramBundle = ((SavedDeviceDb)localObject).getDeviceInfoByChanDevId(paramInt, paramBundle);
        ((SavedDeviceDb)localObject).close();
        localObject = paramBundle;
        if (paramBundle == null)
        {
          localObject = new AntPluginDeviceDbProvider.DeviceDbDeviceInfo();
          ((AntPluginDeviceDbProvider.DeviceDbDeviceInfo)localObject).isPreferredDevice = Boolean.valueOf(false);
          if (paramString != null)
          {
            ((AntPluginDeviceDbProvider.DeviceDbDeviceInfo)localObject).visibleName = paramString;
            ((AntPluginDeviceDbProvider.DeviceDbDeviceInfo)localObject).antDeviceNumber = Integer.valueOf(paramInt);
          }
        }
        else
        {
          return localObject;
        }
      }
      finally
      {
        ((SavedDeviceDb)localObject).close();
      }
      ((AntPluginDeviceDbProvider.DeviceDbDeviceInfo)localObject).visibleName = ("Device: " + paramInt);
    }
  }
  
  public abstract Bundle getPluginDeviceSearchParamBundle(Bundle paramBundle);
  
  protected IDiscoverySearch getScanner(BasicSearchTask.SearchParams paramSearchParams, Looper paramLooper, long paramLong, Bundle paramBundle)
  {
    return new StandardDiscoverySearch(paramSearchParams, paramLooper, paramLong);
  }
  
  protected AntPluginDeviceDbProvider.DeviceDbDeviceInfo[] getShareableDevices(DeviceType arg1)
  {
    if (this.mConnectedDevices.size() > 0) {
      synchronized (this.mConnectedDevices)
      {
        AntPluginDeviceDbProvider.DeviceDbDeviceInfo[] arrayOfDeviceDbDeviceInfo = new AntPluginDeviceDbProvider.DeviceDbDeviceInfo[this.mConnectedDevices.size()];
        int i = 0;
        while (i < this.mConnectedDevices.size())
        {
          arrayOfDeviceDbDeviceInfo[i] = ((AntPluginDevice)this.mConnectedDevices.get(i)).deviceInfo;
          i += 1;
        }
        return arrayOfDeviceDbDeviceInfo;
      }
    }
    return null;
  }
  
  protected IConnectSearch getSingleDeviceSearch(BasicSearchTask.SearchParams paramSearchParams, Bundle paramBundle, Looper paramLooper)
  {
    return new StandardConnectSearch(paramSearchParams, paramLooper);
  }
  
  public abstract List<DeviceType> getSupportedShareableDeviceTypes();
  
  public boolean handleAccessRequest(int paramInt, MessageSender paramMessageSender, AntPluginDevice.ClientInfo paramClientInfo, Bundle paramBundle)
  {
    switch (paramInt)
    {
    default: 
      return false;
    case 1: 
      handleActivitySearchAccessRequest(paramClientInfo, paramMessageSender, paramBundle);
      return true;
    case 3: 
      handleAsyncAntDevNumberSearchRequest(paramClientInfo, paramMessageSender, paramBundle);
      return true;
    }
    handleAsyncSearchControllerRequest(paramClientInfo, paramMessageSender, paramBundle);
    return true;
  }
  
  protected void handleAsyncAntDevNumberSearchRequest(AntPluginDevice.ClientInfo paramClientInfo, MessageSender paramMessageSender, Bundle paramBundle)
  {
    if (!paramBundle.containsKey("int_AntDeviceID"))
    {
      paramClientInfo = Message.obtain();
      paramClientInfo.what = -4;
      dumbfireSendResult(paramMessageSender, paramClientInfo);
      return;
    }
    int i = paramBundle.getInt("int_AntDeviceID");
    if ((i < 0) || (i > 65535))
    {
      LogAnt.d(this.TAG, "Target device number out of range, value: " + i);
      paramClientInfo = Message.obtain();
      paramClientInfo.what = -9;
      dumbfireSendResult(paramMessageSender, paramClientInfo);
      return;
    }
    AntPluginDevice localAntPluginDevice = getAlreadyConnectedDevice(i, paramClientInfo.appNamePkg, paramBundle);
    if (localAntPluginDevice != null)
    {
      subscribeToDeviceAndNotifyClient(paramClientInfo, localAntPluginDevice, paramMessageSender, null, paramBundle);
      return;
    }
    startSearchByAntDeviceNumber(i, paramClientInfo, paramMessageSender, paramBundle);
  }
  
  /* Error */
  protected void handleAsyncSearchControllerRequest(AntPluginDevice.ClientInfo paramClientInfo, MessageSender paramMessageSender, Bundle paramBundle)
  {
    // Byte code:
    //   0: new 610	com/dsi/ant/plugins/antplus/utility/search/tasks/BasicSearchTask$SearchParams
    //   3: dup
    //   4: invokespecial 611	com/dsi/ant/plugins/antplus/utility/search/tasks/BasicSearchTask$SearchParams:<init>	()V
    //   7: astore 5
    //   9: aload_0
    //   10: aload_3
    //   11: invokevirtual 190	com/dsi/ant/plugins/antplus/common/AntPluginService:getPluginDeviceSearchParamBundle	(Landroid/os/Bundle;)Landroid/os/Bundle;
    //   14: astore 4
    //   16: aload 5
    //   18: new 613	com/dsi/ant/message/ChannelId
    //   21: dup
    //   22: iconst_0
    //   23: aload 4
    //   25: ldc_w 615
    //   28: invokevirtual 338	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   31: aload 4
    //   33: ldc_w 617
    //   36: invokevirtual 338	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   39: invokespecial 620	com/dsi/ant/message/ChannelId:<init>	(III)V
    //   42: putfield 624	com/dsi/ant/plugins/antplus/utility/search/tasks/BasicSearchTask$SearchParams:id	Lcom/dsi/ant/message/ChannelId;
    //   45: aload 5
    //   47: aload 4
    //   49: ldc_w 626
    //   52: invokevirtual 338	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   55: putfield 629	com/dsi/ant/plugins/antplus/utility/search/tasks/BasicSearchTask$SearchParams:rfFreq	I
    //   58: aload 5
    //   60: aload 4
    //   62: ldc_w 631
    //   65: invokevirtual 338	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   68: putfield 634	com/dsi/ant/plugins/antplus/utility/search/tasks/BasicSearchTask$SearchParams:channelPeriod	I
    //   71: aload 5
    //   73: aload_3
    //   74: ldc_w 329
    //   77: invokevirtual 338	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   80: putfield 637	com/dsi/ant/plugins/antplus/utility/search/tasks/BasicSearchTask$SearchParams:proximityThreshold	I
    //   83: aload 5
    //   85: getstatic 643	com/dsi/ant/message/LowPrioritySearchTimeout:TEN_SECONDS	Lcom/dsi/ant/message/LowPrioritySearchTimeout;
    //   88: putfield 646	com/dsi/ant/plugins/antplus/utility/search/tasks/BasicSearchTask$SearchParams:searchTimeout	Lcom/dsi/ant/message/LowPrioritySearchTimeout;
    //   91: aload 4
    //   93: ldc -64
    //   95: invokevirtual 198	android/os/Bundle:getSerializable	(Ljava/lang/String;)Ljava/io/Serializable;
    //   98: checkcast 200	com/dsi/ant/channel/PredefinedNetwork
    //   101: astore 4
    //   103: aload_0
    //   104: aload 5
    //   106: aload_0
    //   107: getfield 212	com/dsi/ant/plugins/antplus/common/AntPluginService:reqAccessHandlerThread	Landroid/os/HandlerThread;
    //   110: invokevirtual 218	android/os/HandlerThread:getLooper	()Landroid/os/Looper;
    //   113: ldc2_w 774
    //   116: aload_3
    //   117: invokevirtual 665	com/dsi/ant/plugins/antplus/common/AntPluginService:getScanner	(Lcom/dsi/ant/plugins/antplus/utility/search/tasks/BasicSearchTask$SearchParams;Landroid/os/Looper;JLandroid/os/Bundle;)Lcom/dsi/ant/plugins/antplus/utility/search/IDiscoverySearch;
    //   120: astore 5
    //   122: aload_1
    //   123: invokestatic 781	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   126: putfield 604	com/dsi/ant/plugins/antplus/common/devices/AntPluginDevice$ClientInfo:accessToken	Ljava/util/UUID;
    //   129: new 24	com/dsi/ant/plugins/antplus/common/AntPluginService$AsyncScanInfo
    //   132: dup
    //   133: aload_1
    //   134: aload 5
    //   136: invokespecial 784	com/dsi/ant/plugins/antplus/common/AntPluginService$AsyncScanInfo:<init>	(Lcom/dsi/ant/plugins/antplus/common/devices/AntPluginDevice$ClientInfo;Lcom/dsi/ant/plugins/antplus/utility/search/IDiscoverySearch;)V
    //   139: astore 5
    //   141: aload 5
    //   143: aload_2
    //   144: putfield 787	com/dsi/ant/plugins/antplus/common/AntPluginService$AsyncScanInfo:currentResultHandler	Lcom/dsi/ant/plugins/antplus/utility/ipc/MessageSender;
    //   147: aload 5
    //   149: aload_3
    //   150: putfield 791	com/dsi/ant/plugins/antplus/common/AntPluginService$AsyncScanInfo:reqParams	Landroid/os/Bundle;
    //   153: aload_0
    //   154: getfield 115	com/dsi/ant/plugins/antplus/common/AntPluginService:mToken_AsyncScanList	Ljava/util/concurrent/ConcurrentHashMap;
    //   157: aload_1
    //   158: getfield 604	com/dsi/ant/plugins/antplus/common/devices/AntPluginDevice$ClientInfo:accessToken	Ljava/util/UUID;
    //   161: aload 5
    //   163: invokevirtual 795	java/util/concurrent/ConcurrentHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   166: pop
    //   167: aload_0
    //   168: aload_1
    //   169: aload_2
    //   170: aload_3
    //   171: invokevirtual 798	com/dsi/ant/plugins/antplus/common/AntPluginService:sendConnectedDevicesAsync	(Lcom/dsi/ant/plugins/antplus/common/devices/AntPluginDevice$ClientInfo;Lcom/dsi/ant/plugins/antplus/utility/ipc/MessageSender;Landroid/os/Bundle;)V
    //   174: aload_0
    //   175: aload 4
    //   177: aconst_null
    //   178: aload_2
    //   179: aload_1
    //   180: aload_3
    //   181: ldc -54
    //   183: iconst_0
    //   184: invokevirtual 206	android/os/Bundle:getInt	(Ljava/lang/String;I)I
    //   187: invokevirtual 210	com/dsi/ant/plugins/antplus/common/AntPluginService:acquireChannelWithRssi_helper	(Lcom/dsi/ant/channel/PredefinedNetwork;Lcom/dsi/ant/channel/Capabilities;Lcom/dsi/ant/plugins/antplus/utility/ipc/MessageSender;Lcom/dsi/ant/plugins/antplus/common/devices/AntPluginDevice$ClientInfo;I)Lcom/dsi/ant/channel/AntChannel;
    //   190: astore_3
    //   191: aload_3
    //   192: ifnonnull +61 -> 253
    //   195: aload_0
    //   196: aload_1
    //   197: getfield 604	com/dsi/ant/plugins/antplus/common/devices/AntPluginDevice$ClientInfo:accessToken	Ljava/util/UUID;
    //   200: invokevirtual 608	com/dsi/ant/plugins/antplus/common/AntPluginService:shutdownAndRemoveAsyncSearch	(Ljava/util/UUID;)V
    //   203: return
    //   204: astore_1
    //   205: aload_0
    //   206: getfield 89	com/dsi/ant/plugins/antplus/common/AntPluginService:TAG	Ljava/lang/String;
    //   209: ldc_w 800
    //   212: aload_1
    //   213: invokestatic 804	com/dsi/ant/plugins/utility/log/LogAnt:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   216: invokestatic 168	android/os/Message:obtain	()Landroid/os/Message;
    //   219: astore_1
    //   220: aload_1
    //   221: bipush -9
    //   223: putfield 172	android/os/Message:what	I
    //   226: aload_0
    //   227: aload_2
    //   228: aload_1
    //   229: invokevirtual 176	com/dsi/ant/plugins/antplus/common/AntPluginService:dumbfireSendResult	(Lcom/dsi/ant/plugins/antplus/utility/ipc/MessageSender;Landroid/os/Message;)V
    //   232: return
    //   233: astore_2
    //   234: aload_0
    //   235: getfield 89	com/dsi/ant/plugins/antplus/common/AntPluginService:TAG	Ljava/lang/String;
    //   238: ldc_w 806
    //   241: invokestatic 162	com/dsi/ant/plugins/utility/log/LogAnt:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   244: aload_0
    //   245: aload_1
    //   246: getfield 604	com/dsi/ant/plugins/antplus/common/devices/AntPluginDevice$ClientInfo:accessToken	Ljava/util/UUID;
    //   249: invokevirtual 608	com/dsi/ant/plugins/antplus/common/AntPluginService:shutdownAndRemoveAsyncSearch	(Ljava/util/UUID;)V
    //   252: return
    //   253: aload_0
    //   254: aload_3
    //   255: aload 5
    //   257: invokespecial 143	com/dsi/ant/plugins/antplus/common/AntPluginService:startAsyncScan	(Lcom/dsi/ant/channel/AntChannel;Lcom/dsi/ant/plugins/antplus/common/AntPluginService$AsyncScanInfo;)V
    //   260: invokestatic 168	android/os/Message:obtain	()Landroid/os/Message;
    //   263: astore_3
    //   264: aload_3
    //   265: iconst_0
    //   266: putfield 172	android/os/Message:what	I
    //   269: new 194	android/os/Bundle
    //   272: dup
    //   273: invokespecial 297	android/os/Bundle:<init>	()V
    //   276: astore 4
    //   278: aload 4
    //   280: ldc_w 808
    //   283: aload_1
    //   284: getfield 604	com/dsi/ant/plugins/antplus/common/devices/AntPluginDevice$ClientInfo:accessToken	Ljava/util/UUID;
    //   287: invokevirtual 812	android/os/Bundle:putSerializable	(Ljava/lang/String;Ljava/io/Serializable;)V
    //   290: aload 4
    //   292: ldc_w 814
    //   295: aload_0
    //   296: getfield 816	com/dsi/ant/plugins/antplus/common/AntPluginService:mPccMsgHandler	Landroid/os/Messenger;
    //   299: invokevirtual 232	android/os/Bundle:putParcelable	(Ljava/lang/String;Landroid/os/Parcelable;)V
    //   302: aload_3
    //   303: aload 4
    //   305: invokevirtual 310	android/os/Message:setData	(Landroid/os/Bundle;)V
    //   308: aload_2
    //   309: aload_3
    //   310: invokevirtual 705	com/dsi/ant/plugins/antplus/utility/ipc/MessageSender:send	(Landroid/os/Message;)V
    //   313: return
    //   314: astore_2
    //   315: aload_0
    //   316: getfield 89	com/dsi/ant/plugins/antplus/common/AntPluginService:TAG	Ljava/lang/String;
    //   319: ldc_w 818
    //   322: invokestatic 162	com/dsi/ant/plugins/utility/log/LogAnt:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   325: aload_0
    //   326: aload_1
    //   327: getfield 604	com/dsi/ant/plugins/antplus/common/devices/AntPluginDevice$ClientInfo:accessToken	Ljava/util/UUID;
    //   330: invokevirtual 608	com/dsi/ant/plugins/antplus/common/AntPluginService:shutdownAndRemoveAsyncSearch	(Ljava/util/UUID;)V
    //   333: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	334	0	this	AntPluginService
    //   0	334	1	paramClientInfo	AntPluginDevice.ClientInfo
    //   0	334	2	paramMessageSender	MessageSender
    //   0	334	3	paramBundle	Bundle
    //   14	290	4	localObject1	Object
    //   7	249	5	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   103	122	204	java/lang/IllegalArgumentException
    //   167	174	233	android/os/RemoteException
    //   308	313	314	android/os/RemoteException
  }
  
  protected boolean isExistingDevice(int paramInt, Bundle paramBundle)
  {
    return getAlreadyConnectedDevice(paramInt, null, paramBundle) != null;
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    if (this.mPccReqAccessHandler == null)
    {
      this.reqAccessHandlerThread = new HandlerThread("AntPluginService ReqAcc Handler");
      this.reqAccessHandlerThread.start();
      this.mPccReqAccessHandler = new Messenger(new RequestAccessHandler(this.reqAccessHandlerThread.getLooper(), this));
    }
    if (this.mPccMsgHandler == null)
    {
      this.pccHandlerThread = new HandlerThread("AntPluginService PCC cmd handler");
      this.pccHandlerThread.start();
      this.mPccHandler = new PccCommandHandler(this.pccHandlerThread.getLooper());
      this.mPccMsgHandler = new Messenger(this.mPccHandler);
    }
    return this.mPccReqAccessHandler.getBinder();
  }
  
  public void onCreate()
  {
    super.onCreate();
    try
    {
      LogAnt.setVersion("BBC" + getPackageManager().getPackageInfo(getPackageName(), 0).versionCode);
      registerReceiver(this.alreadyConnectedDeviceRequestReceiver, new IntentFilter("com.dsi.ant.plugins.antplus.queryalreadyconnecteddevices"));
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        LogAnt.w(this.TAG, "ANT+ Plugins Version not found: " + localNameNotFoundException.toString());
      }
    }
  }
  
  public void onDestroy()
  {
    this.mClosed = true;
    LogAnt.v(this.TAG, "Entering OnDestroy()");
    if (this.mPccReqAccessHandler != null) {
      this.reqAccessHandlerThread.quit();
    }
    try
    {
      this.reqAccessHandlerThread.join(1000L);
      this.mPccReqAccessHandler = null;
      synchronized (this.mActivitySearches)
      {
        Iterator localIterator1 = this.mActivitySearches.iterator();
        while (localIterator1.hasNext())
        {
          ActivitySearchInfo localActivitySearchInfo = (ActivitySearchInfo)localIterator1.next();
          localActivitySearchInfo.isServiceDead = true;
          if (localActivitySearchInfo.activity != null) {
            localActivitySearchInfo.activity.onServiceDead();
          }
        }
      }
    }
    catch (InterruptedException localInterruptedException1)
    {
      for (;;)
      {
        Thread.currentThread().interrupt();
      }
      this.mActivitySearches.clear();
      if (this.mPccMsgHandler != null) {
        this.pccHandlerThread.quit();
      }
      try
      {
        this.pccHandlerThread.join(1000L);
        this.mPccMsgHandler = null;
        synchronized (this.mConnectedDevices)
        {
          unregisterReceiver(this.alreadyConnectedDeviceRequestReceiver);
          Iterator localIterator2 = this.mConnectedDevices.iterator();
          if (localIterator2.hasNext()) {
            ((AntPluginDevice)localIterator2.next()).closeDevice();
          }
        }
      }
      catch (InterruptedException localInterruptedException2)
      {
        for (;;)
        {
          Thread.currentThread().interrupt();
        }
        Iterator localIterator3 = this.mToken_AsyncScanList.keySet().iterator();
        while (localIterator3.hasNext()) {
          shutdownAndRemoveAsyncSearch((UUID)localIterator3.next());
        }
        this.mConnectedDevices.clear();
        this.mToken_DeviceList.clear();
        unbindFromArs();
        super.onDestroy();
      }
    }
  }
  
  public void requestAccessToken(Bundle paramBundle, MessageSender paramMessageSender)
  {
    int i = paramBundle.getInt("int_RequestAccessMode");
    Object localObject = (Messenger)paramBundle.get("msgr_PluginMsgHandler");
    MessageSender localMessageSender = new MessageSender((Messenger)localObject);
    ArrayList localArrayList = new ArrayList(3);
    localArrayList.add(localMessageSender);
    localArrayList.add(paramMessageSender);
    this.mMessageSenders.put(localObject, localArrayList);
    localObject = new AntPluginDevice.ClientInfo();
    ((AntPluginDevice.ClientInfo)localObject).appNamePkg = paramBundle.getString("str_ApplicationNamePackage");
    ((AntPluginDevice.ClientInfo)localObject).appNameLabel = paramBundle.getString("str_ApplicationNameTitle");
    ((AntPluginDevice.ClientInfo)localObject).responseMessenger = localMessageSender;
    ((AntPluginDevice.ClientInfo)localObject).pluginLibVersion = paramBundle.getInt("int_PluginLibVersion", 0);
    ((AntPluginDevice.ClientInfo)localObject).membersOnlyVersion = paramBundle.getInt("more", -1);
    LogAnt.v(this.TAG, "ReqAcc Mode: " + i + " from " + ((AntPluginDevice.ClientInfo)localObject).appNamePkg + ":" + ((AntPluginDevice.ClientInfo)localObject).pluginLibVersion);
    if (!handleAccessRequest(i, paramMessageSender, (AntPluginDevice.ClientInfo)localObject, paramBundle))
    {
      paramBundle = Message.obtain();
      paramBundle.what = -99999999;
      dumbfireSendResult(paramMessageSender, paramBundle);
    }
  }
  
  public void revokeAccess(UUID paramUUID, AntPluginDevice paramAntPluginDevice)
  {
    synchronized (this.mConnectedDevices)
    {
      this.mToken_DeviceList.remove(paramUUID);
      if (!paramAntPluginDevice.mIsOpen) {
        this.mConnectedDevices.remove(paramAntPluginDevice);
      }
      return;
    }
  }
  
  protected void sendConnectedDevicesAsync(AntPluginDevice.ClientInfo paramClientInfo, MessageSender paramMessageSender, Bundle paramBundle)
    throws RemoteException
  {
    synchronized (this.mConnectedDevices)
    {
      Iterator localIterator = this.mConnectedDevices.iterator();
      while (localIterator.hasNext())
      {
        AntPluginDevice localAntPluginDevice = (AntPluginDevice)localIterator.next();
        if (!localAntPluginDevice.hasAccess(paramClientInfo.appNamePkg, paramBundle))
        {
          Message localMessage = Message.obtain();
          localMessage.what = 2;
          Bundle localBundle = new Bundle();
          localBundle.putParcelable("parcelable_AsyncScanResultDeviceInfo", new AsyncScanController.AsyncScanResultDeviceInfo(null, localAntPluginDevice.deviceInfo, true));
          localMessage.setData(localBundle);
          paramMessageSender.send(localMessage);
        }
      }
    }
  }
  
  protected void shutdownAndRemoveAsyncSearch(UUID paramUUID)
  {
    AsyncScanInfo localAsyncScanInfo = (AsyncScanInfo)this.mToken_AsyncScanList.get(paramUUID);
    if (localAsyncScanInfo != null)
    {
      this.mToken_AsyncScanList.remove(paramUUID);
      localAsyncScanInfo.closeAsyncScan();
    }
  }
  
  public void startSearchByAntDeviceNumber(int paramInt, final AntPluginDevice.ClientInfo paramClientInfo, final MessageSender paramMessageSender, final Bundle paramBundle)
  {
    Object localObject1 = new BasicSearchTask.SearchParams();
    Object localObject2 = getPluginDeviceSearchParamBundle(paramBundle);
    if ((!paramBundle.containsKey("int_ProximityBin")) || (!((Bundle)localObject2).containsKey("predefinednetwork_NetKey")) || (!((Bundle)localObject2).containsKey("int_DevType")) || (!((Bundle)localObject2).containsKey("int_TransType")) || (!((Bundle)localObject2).containsKey("int_Period")) || (!((Bundle)localObject2).containsKey("int_RfFreq")))
    {
      LogAnt.e(this.TAG, "Bundle is missing parameters");
      paramClientInfo = Message.obtain();
      paramClientInfo.what = -4;
      dumbfireSendResult(paramMessageSender, paramClientInfo);
      return;
    }
    ((BasicSearchTask.SearchParams)localObject1).id = new ChannelId(paramInt, ((Bundle)localObject2).getInt("int_DevType"), ((Bundle)localObject2).getInt("int_TransType"));
    ((BasicSearchTask.SearchParams)localObject1).rfFreq = ((Bundle)localObject2).getInt("int_RfFreq");
    ((BasicSearchTask.SearchParams)localObject1).channelPeriod = ((Bundle)localObject2).getInt("int_Period");
    if (paramInt != 0) {}
    for (paramInt = 0;; paramInt = paramBundle.getInt("int_ProximityBin"))
    {
      ((BasicSearchTask.SearchParams)localObject1).proximityThreshold = paramInt;
      ((BasicSearchTask.SearchParams)localObject1).searchTimeout = LowPrioritySearchTimeout.TEN_SECONDS;
      paramInt = paramBundle.getInt("int_RssiMode", 0);
      localObject2 = acquireChannelWithRssi_helper((PredefinedNetwork)((Bundle)localObject2).getSerializable("predefinednetwork_NetKey"), null, paramMessageSender, paramClientInfo, paramInt);
      if (localObject2 == null) {
        break;
      }
      try
      {
        localObject1 = getSingleDeviceSearch((BasicSearchTask.SearchParams)localObject1, paramBundle, this.reqAccessHandlerThread.getLooper());
        ((IConnectSearch)localObject1).start((AntChannel)localObject2, new IConnectSearch.IConnectSearchResultHandler()
        {
          public void onConnected(SearchResultInfo paramAnonymousSearchResultInfo, AntChannel paramAnonymousAntChannel)
          {
            AntPluginDeviceDbProvider.DeviceDbDeviceInfo localDeviceDbDeviceInfo = AntPluginService.this.getDeviceInfoById(paramAnonymousSearchResultInfo.id.getDeviceNumber(), null, paramBundle);
            paramAnonymousSearchResultInfo = AntPluginService.this.createNewDeviceFromSearchResults(paramAnonymousAntChannel, localDeviceDbDeviceInfo, paramBundle, paramAnonymousSearchResultInfo);
            if (paramAnonymousSearchResultInfo == null)
            {
              paramAnonymousSearchResultInfo = Message.obtain();
              LogAnt.e(AntPluginService.this.TAG, "Plugin search by deviceNumber search failed internally: Device instantiation failed.");
              paramAnonymousSearchResultInfo.what = -4;
              AntPluginService.this.dumbfireSendResult(paramMessageSender, paramAnonymousSearchResultInfo);
              paramAnonymousAntChannel.release();
            }
            while (AntPluginService.this.subscribeToDeviceAndNotifyClient(paramClientInfo, paramAnonymousSearchResultInfo, paramMessageSender, null, paramBundle)) {
              return;
            }
            paramAnonymousAntChannel.release();
          }
          
          public void onSearchStopped(IConnectSearch.ConnectSearchStopReason paramAnonymousConnectSearchStopReason, AntChannel paramAnonymousAntChannel)
          {
            Message localMessage = Message.obtain();
            switch (AntPluginService.8.$SwitchMap$com$dsi$ant$plugins$antplus$utility$search$IConnectSearch$ConnectSearchStopReason[paramAnonymousConnectSearchStopReason.ordinal()])
            {
            default: 
              return;
            case 1: 
              localMessage.what = -7;
              AntPluginService.this.dumbfireSendResult(paramMessageSender, localMessage);
              paramAnonymousAntChannel.release();
              return;
            case 2: 
              LogAnt.e(AntPluginService.this.TAG, "Plugin search by device number: search was interrupted.");
              localMessage.what = -4;
              AntPluginService.this.dumbfireSendResult(paramMessageSender, localMessage);
              paramAnonymousAntChannel.release();
              return;
            }
            LogAnt.e(AntPluginService.this.TAG, "Plugin search by device number: search crashed!");
            localMessage.what = -4;
            AntPluginService.this.dumbfireSendResult(paramMessageSender, localMessage);
          }
        });
        return;
      }
      catch (IllegalArgumentException paramClientInfo)
      {
        LogAnt.w(this.TAG, "Invalid arguments when constructing scan", paramClientInfo);
        paramClientInfo = Message.obtain();
        paramClientInfo.what = -9;
        dumbfireSendResult(paramMessageSender, paramClientInfo);
      }
    }
  }
  
  public boolean subscribeToDeviceAndNotifyClient(AntPluginDevice.ClientInfo paramClientInfo, AntPluginDevice paramAntPluginDevice, MessageSender paramMessageSender, Bundle paramBundle1, Bundle paramBundle2)
  {
    if ((paramClientInfo.appNamePkg == null) || (paramClientInfo.appNameLabel == null) || (paramClientInfo.responseMessenger == null)) {
      throw new IllegalArgumentException("Client missing required info");
    }
    if (paramClientInfo.accessToken == null) {
      paramClientInfo.accessToken = UUID.randomUUID();
    }
    if ((this.mClosed) && (paramAntPluginDevice != null))
    {
      LogAnt.e(this.TAG, "Closing new device because service is closing/already closed");
      paramAntPluginDevice.closeDevice();
    }
    if ((paramAntPluginDevice == null) || (paramAntPluginDevice.isDeviceClosed()))
    {
      LogAnt.e(this.TAG, "Trying to subscribe to dead device");
      paramClientInfo = Message.obtain();
      paramClientInfo.what = -4;
      dumbfireSendResult(paramMessageSender, paramClientInfo);
      return false;
    }
    if (paramAntPluginDevice.hasAccess(paramClientInfo.appNamePkg, paramBundle2))
    {
      LogAnt.w(this.TAG, "Request Access Failed: App already has access to this device.");
      paramClientInfo = Message.obtain();
      paramClientInfo.what = -8;
      dumbfireSendResult(paramMessageSender, paramClientInfo);
      return false;
    }
    synchronized (this.mConnectedDevices)
    {
      if (!paramAntPluginDevice.addClient(paramClientInfo, paramMessageSender, paramBundle2)) {
        return false;
      }
      if (!this.mConnectedDevices.contains(paramAntPluginDevice)) {
        this.mConnectedDevices.add(paramAntPluginDevice);
      }
      this.mToken_DeviceList.put(paramClientInfo.accessToken, paramAntPluginDevice);
      ??? = Message.obtain();
      ((Message)???).what = 0;
      paramBundle2 = paramBundle1;
      paramBundle1 = paramBundle2;
      if (paramBundle2 == null) {
        paramBundle1 = new Bundle();
      }
    }
    try
    {
      paramBundle1.putInt("int_ServiceVersion", getPackageManager().getPackageInfo(getPackageName(), 0).versionCode);
      paramBundle1.putSerializable("uuid_AccessToken", paramClientInfo.accessToken);
      paramBundle1.putParcelable("msgr_PluginComm", this.mPccMsgHandler);
      paramBundle1.putInt("int_InitialDeviceStateCode", paramAntPluginDevice.getCurrentState());
      if (paramClientInfo.pluginLibVersion == 0)
      {
        paramBundle1.putString("str_DeviceName", paramAntPluginDevice.deviceInfo.visibleName);
        paramBundle1.putInt("int_AntDeviceID", paramAntPluginDevice.deviceInfo.antDeviceNumber.intValue());
        if (!(paramAntPluginDevice instanceof AntPluginAntPlusReceiver)) {}
      }
    }
    catch (PackageManager.NameNotFoundException paramBundle2)
    {
      try
      {
        for (;;)
        {
          bool = ((AntPluginAntPlusReceiver)paramAntPluginDevice).mAntChannel.getCapabilities().hasRssi();
          paramBundle1.putBoolean("bool_RssiSupport", bool);
          ((Message)???).setData(paramBundle1);
          try
          {
            paramMessageSender.send((Message)???);
            return true;
          }
          catch (RemoteException paramMessageSender)
          {
            LogAnt.e(this.TAG, "RemoteException sending request access 'SUCCESS' reply to client.");
            revokeAccess(paramClientInfo.accessToken, paramAntPluginDevice);
          }
          paramClientInfo = finally;
          throw paramClientInfo;
          paramBundle2 = paramBundle2;
          LogAnt.e(this.TAG, "Can't retrieve service version from plugin manager!");
          paramBundle1.putInt("int_ServiceVersion", -5);
        }
        paramBundle1.putParcelable("parcelable_DeviceDbInfo", paramAntPluginDevice.deviceInfo);
      }
      catch (RemoteException paramBundle2)
      {
        for (;;)
        {
          boolean bool = false;
        }
      }
    }
    return false;
  }
  
  public void unbindFromArs()
  {
    synchronized (this.mArsBoundChange_LOCK)
    {
      boolean bool = this.mIsArsBound;
      if (bool) {}
      try
      {
        if (this.mArsConnection != null) {
          unbindService(this.mArsConnection);
        }
        this.mArsConnection = null;
        this.mArsComm = null;
        this.mIsArsBound = false;
        return;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        for (;;)
        {
          LogAnt.e(this.TAG, "Unexpected error unbinding service, " + localIllegalArgumentException);
        }
      }
    }
  }
  
  public static class AsyncScanInfo
  {
    public AntPluginDevice.ClientInfo client;
    public IContinueTask continueTask;
    public MessageSender currentResultHandler;
    public Bundle reqParams;
    public boolean resultHandled;
    public final IDiscoverySearch scan;
    
    public AsyncScanInfo(AntPluginDevice.ClientInfo paramClientInfo, IDiscoverySearch paramIDiscoverySearch)
    {
      this.scan = paramIDiscoverySearch;
      this.client = paramClientInfo;
    }
    
    public void closeAsyncScan()
    {
      this.scan.interrupt();
    }
    
    public MessageSender getCurrentResultHandler()
    {
      return this.currentResultHandler;
    }
    
    public static abstract interface IContinueTask
    {
      public abstract void run(AntChannel paramAntChannel);
    }
  }
  
  class PccCommandHandler
    extends Handler
  {
    public PccCommandHandler(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      LogAnt.v(AntPluginService.this.TAG, "PCC Msg Handler received: " + paramMessage.what);
      Object localObject1 = paramMessage.getData();
      ((Bundle)localObject1).setClassLoader(getClass().getClassLoader());
      Object localObject2 = (UUID)((Bundle)localObject1).get("uuid_AccessToken");
      if (localObject2 != null)
      {
        AntPluginDevice localAntPluginDevice = (AntPluginDevice)AntPluginService.this.mToken_DeviceList.get(localObject2);
        if (localAntPluginDevice != null)
        {
          if ((paramMessage.what == 10101) || (paramMessage.what == 10100)) {
            LogAnt.e(AntPluginService.this.TAG, "cmd handler: async message received when target is a device. AntLib probably needs to be upgraded.");
          }
          localObject1 = null;
          if (paramMessage.what == 10002) {
            localObject1 = ((AntPluginDevice.ClientInfo)localAntPluginDevice.token_ClientMap.get(localObject2)).responseMessenger;
          }
          localAntPluginDevice.HandleCmdFromPcc((UUID)localObject2, paramMessage);
          if (paramMessage.what == 10002)
          {
            paramMessage = (List)AntPluginService.this.mMessageSenders.remove(((MessageSender)localObject1).mMessenger);
            if (paramMessage != null)
            {
              paramMessage = paramMessage.iterator();
              while (paramMessage.hasNext()) {
                ((MessageSender)paramMessage.next()).setDead();
              }
            }
            AntPluginService.this.revokeAccess((UUID)localObject2, localAntPluginDevice);
          }
        }
        for (;;)
        {
          return;
          localObject1 = (AntPluginService.AsyncScanInfo)AntPluginService.this.mToken_AsyncScanList.get(localObject2);
          if (localObject1 == null) {
            break label477;
          }
          if (paramMessage.what == 10100)
          {
            localObject2 = Message.obtain();
            ((Message)localObject2).what = 10100;
            ((Message)localObject2).arg1 = 0;
            try
            {
              ((AntPluginService.AsyncScanInfo)localObject1).client.responseMessenger.send((Message)localObject2);
              localObject2 = ((AntPluginService.AsyncScanInfo)localObject1).reqParams;
              ((Bundle)localObject2).putAll(paramMessage.getData());
              AntPluginService.this.connectToAsyncResult((AntPluginService.AsyncScanInfo)localObject1, (Bundle)localObject2);
              return;
            }
            catch (RemoteException paramMessage)
            {
              LogAnt.e(AntPluginService.this.TAG, "RemoteException sending response to CONNECTTOASYNCRESULT cmd, closing scan.");
              AntPluginService.this.shutdownAndRemoveAsyncSearch(((AntPluginService.AsyncScanInfo)localObject1).client.accessToken);
              return;
            }
          }
          if (paramMessage.what != 10101) {
            break;
          }
          paramMessage = Message.obtain();
          paramMessage.what = 10101;
          paramMessage.arg1 = 0;
          AntPluginService.this.dumbfireSendResult(((AntPluginService.AsyncScanInfo)localObject1).client.responseMessenger, paramMessage);
          AntPluginService.this.shutdownAndRemoveAsyncSearch((UUID)localObject2);
          paramMessage = (List)AntPluginService.this.mMessageSenders.remove(((AntPluginService.AsyncScanInfo)localObject1).client.responseMessenger.mMessenger);
          if (paramMessage != null)
          {
            paramMessage = paramMessage.iterator();
            while (paramMessage.hasNext()) {
              ((MessageSender)paramMessage.next()).setDead();
            }
          }
        }
        localObject2 = Message.obtain();
        ((Message)localObject2).what = paramMessage.what;
        ((Message)localObject2).arg1 = -99999999;
        AntPluginService.this.dumbfireSendResult(((AntPluginService.AsyncScanInfo)localObject1).client.responseMessenger, (Message)localObject2);
        return;
      }
      label477:
      LogAnt.e(AntPluginService.this.TAG, "Cmd Handler: Token missing or invalid!");
    }
  }
  
  private static class RequestAccessHandler
    extends Handler
  {
    private WeakReference<AntPluginService> mService;
    private final String sTAG;
    
    public RequestAccessHandler(Looper paramLooper, AntPluginService paramAntPluginService)
    {
      super();
      this.sTAG = (paramAntPluginService.getClass().getSimpleName() + " ");
      this.mService = new WeakReference(paramAntPluginService);
    }
    
    public void handleMessage(Message paramMessage)
    {
      Bundle localBundle = paramMessage.getData();
      localBundle.setClassLoader(getClass().getClassLoader());
      if (LogAnt.getDebugLevel() >= LogAnt.DebugLevel.DEBUG.ordinal())
      {
        LogAnt.d(this.sTAG, "ReqAcc Handler received: " + paramMessage.what);
        LogAnt.d(this.sTAG, "Requesting PluginLib reports as v." + localBundle.getString("string_PluginLibVersion"));
      }
      MessageSender localMessageSender = new MessageSender((Messenger)localBundle.get("msgr_ReqAccResultReceiver"));
      if (localMessageSender.mMessenger == null) {
        LogAnt.e(this.sTAG, "'MSG_REQACC_PARAM_msgrRESULTRECEIVER' missing from intent bundle");
      }
      AntPluginService localAntPluginService;
      Object localObject;
      do
      {
        return;
        localAntPluginService = (AntPluginService)this.mService.get();
        if ((localAntPluginService == null) || (localAntPluginService.mClosed))
        {
          LogAnt.e(this.sTAG, "Reqacc msg handler rcvd msg, but service is dead");
          paramMessage = Message.obtain();
          paramMessage.what = -4;
          try
          {
            localMessageSender.send(paramMessage);
            return;
          }
          catch (RemoteException paramMessage)
          {
            LogAnt.e(this.sTAG, "RemoteException sending service is dead message");
            return;
          }
        }
        localObject = localAntPluginService;
        try
        {
          Context localContext = localAntPluginService.createPackageContext("com.dsi.ant.plugins.antplus", 4);
          localObject = localContext;
          LogAnt.getDebugLevel(localContext);
          localObject = localContext;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            LogAnt.i(this.sTAG, "Package not found when reading debug level." + localNameNotFoundException);
          }
          if (localAntPluginService.mArsComm != null) {
            continue;
          }
          localAntPluginService.connectArsAndRepost(paramMessage, localMessageSender);
          return;
          localAntPluginService.requestAccessToken(localBundle, localMessageSender);
          return;
        }
        switch (paramMessage.what)
        {
        default: 
          LogAnt.e(this.sTAG, "Unhandled message rcvd in reqacc msg handler");
          paramMessage = Message.obtain();
          paramMessage.what = -99999999;
          localAntPluginService.dumbfireSendResult(localMessageSender, paramMessage);
          return;
        }
      } while (paramMessage.arg1 != 0);
      LogAnt.setDebugLevel(paramMessage.arg2, (Context)localObject);
      paramMessage = Message.obtain();
      paramMessage.what = 0;
      localAntPluginService.dumbfireSendResult(localMessageSender, paramMessage);
      return;
      if (localBundle.containsKey("str_ApplicationNamePackage"))
      {
        localObject = new AntPluginDevice.ClientInfo();
        ((AntPluginDevice.ClientInfo)localObject).appNamePkg = localBundle.getString("str_ApplicationNamePackage");
        ((AntPluginDevice.ClientInfo)localObject).pluginLibVersion = localBundle.getInt("int_PluginLibVersion", 0);
        LogAnt.v(this.sTAG, "Close Conn cmd from " + ((AntPluginDevice.ClientInfo)localObject).appNamePkg + ":" + ((AntPluginDevice.ClientInfo)localObject).pluginLibVersion);
      }
      for (;;)
      {
        paramMessage = (Messenger)paramMessage.getData().getParcelable("msgr_PluginMsgHandler");
        paramMessage = (List)localAntPluginService.mMessageSenders.remove(paramMessage);
        if (paramMessage == null) {
          break;
        }
        paramMessage = paramMessage.iterator();
        while (paramMessage.hasNext()) {
          ((MessageSender)paramMessage.next()).setDead();
        }
        LogAnt.v(this.sTAG, "Close Conn cmd from old version of library");
      }
      paramMessage = Message.obtain();
      paramMessage.what = 0;
      localAntPluginService.dumbfireSendResult(localMessageSender, paramMessage);
    }
  }
}
