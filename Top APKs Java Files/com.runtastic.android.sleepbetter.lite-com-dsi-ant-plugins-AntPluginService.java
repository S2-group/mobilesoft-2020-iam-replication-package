package com.dsi.ant.plugins;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
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
import com.dsi.ant.message.ChannelId;
import com.dsi.ant.plugins.utility.executor.AntChannelExecutor;
import com.dsi.ant.plugins.utility.executor.AntChannelExecutor.IDeathHandler;
import com.dsi.ant.plugins.utility.search.AbstractSearchControllerTask.SearchResultReceiver;
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
  public Messenger mPccMsgHandler = new Messenger(new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      Log.v("AntPluginService", "PCC Msg Handler received: " + paramAnonymousMessage.what);
      UUID localUUID = (UUID)paramAnonymousMessage.getData().get("uuid_AccessToken");
      AntPluginDevice localAntPluginDevice;
      if (localUUID != null)
      {
        localAntPluginDevice = (AntPluginDevice)AntPluginService.this.mToken_DeviceList.get(localUUID);
        if (localAntPluginDevice != null) {}
      }
      else
      {
        Log.e("AntPluginService", "Cmd Handler: Token missing or invalid!");
      }
      do
      {
        return;
        localAntPluginDevice.HandleCmdFromPcc(localUUID, paramAnonymousMessage);
      } while (paramAnonymousMessage.what != 10002);
      AntPluginService.this.revokeAccess(localUUID, localAntPluginDevice);
    }
  });
  Messenger mPccReqAccessHandler = new Messenger(new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      Log.v("AntPluginService", "ReqAcc Handler received: " + paramAnonymousMessage.what);
      switch (paramAnonymousMessage.what)
      {
      default: 
        throw new RuntimeException("Unhandled message rcvd in reqacc msg handler");
      }
      AntPluginService.this.requestAccessToken(paramAnonymousMessage.getData());
    }
  });
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
            break label143;
          }
          subscribeToDeviceAndNotifyClient(paramClientInfo, (AntPluginDevice)localObject, paramMessenger, null);
          return;
          localAntPluginDevice = (AntPluginDevice)localIterator.next();
          if (localAntPluginDevice.hasAccess(paramClientInfo.appNamePkg)) {
            break;
          }
          localObject = localAntPluginDevice;
        } while (j == 0);
      } while (localAntPluginDevice.mChan_DeviceId.intValue() != j);
      localObject = localAntPluginDevice;
    }
    label143:
    Object localObject = getPackageManager().getInstalledApplications(0).iterator();
    if (!((Iterator)localObject).hasNext()) {}
    for (int i = 0;; i = 1)
    {
      if (i != 0) {
        break label257;
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
    label257:
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
  
  protected Bundle composeActivitySearchParams(final AntPluginDevice.ClientInfo paramClientInfo, final Messenger paramMessenger, Bundle paramBundle)
  {
    Bundle localBundle = getPluginDeviceSearchParamBundle();
    final AntChannel localAntChannel = acquireChannel_helper((PredefinedNetwork)localBundle.getSerializable("predefinednetwork_NetKey"), null, paramMessenger);
    if (localAntChannel == null) {
      return null;
    }
    localBundle.putParcelable("antchannel_Channel", localAntChannel);
    localBundle.putInt("int_ProximityBin", paramBundle.getInt("int_ProximityBin"));
    addExisitingDevicesToSearchParams(localBundle, paramClientInfo, paramMessenger, paramBundle);
    localBundle.putParcelable("msgr_SearchResultReceiver", new Messenger(new Handler()
    {
      /* Error */
      public void handleMessage(Message paramAnonymousMessage)
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 5
        //   3: iconst_1
        //   4: istore 6
        //   6: iconst_1
        //   7: istore 7
        //   9: iconst_1
        //   10: istore 4
        //   12: ldc 37
        //   14: new 39	java/lang/StringBuilder
        //   17: dup
        //   18: ldc 41
        //   20: invokespecial 44	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   23: aload_1
        //   24: getfield 50	android/os/Message:what	I
        //   27: invokevirtual 54	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
        //   30: invokevirtual 58	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   33: invokestatic 64	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
        //   36: pop
        //   37: invokestatic 68	android/os/Message:obtain	()Landroid/os/Message;
        //   40: astore 10
        //   42: iload 6
        //   44: istore_3
        //   45: iload 7
        //   47: istore_2
        //   48: aload_1
        //   49: getfield 50	android/os/Message:what	I
        //   52: tableswitch	default:+535->587, -2:+463->515, -1:+28->80, 0:+96->148
        //   80: iload 6
        //   82: istore_3
        //   83: iload 7
        //   85: istore_2
        //   86: ldc 37
        //   88: new 39	java/lang/StringBuilder
        //   91: dup
        //   92: ldc 70
        //   94: invokespecial 44	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   97: aload 10
        //   99: getfield 50	android/os/Message:what	I
        //   102: invokevirtual 54	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
        //   105: invokevirtual 58	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   108: invokestatic 73	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
        //   111: pop
        //   112: iload 6
        //   114: istore_3
        //   115: iload 7
        //   117: istore_2
        //   118: aload 10
        //   120: bipush -4
        //   122: putfield 50	android/os/Message:what	I
        //   125: iload 6
        //   127: istore_3
        //   128: iload 7
        //   130: istore_2
        //   131: aload_0
        //   132: getfield 25	com/dsi/ant/plugins/AntPluginService$4:val$msgr_ResultMessenger	Landroid/os/Messenger;
        //   135: aload 10
        //   137: invokevirtual 78	android/os/Messenger:send	(Landroid/os/Message;)V
        //   140: aload_0
        //   141: getfield 23	com/dsi/ant/plugins/AntPluginService$4:val$antChannel	Lcom/dsi/ant/channel/AntChannel;
        //   144: invokevirtual 83	com/dsi/ant/channel/AntChannel:release	()V
        //   147: return
        //   148: iload 6
        //   150: istore_3
        //   151: iload 7
        //   153: istore_2
        //   154: aload_0
        //   155: getfield 21	com/dsi/ant/plugins/AntPluginService$4:this$0	Lcom/dsi/ant/plugins/AntPluginService;
        //   158: getfield 87	com/dsi/ant/plugins/AntPluginService:mConnectedDevices	Ljava/util/ArrayList;
        //   161: astore 9
        //   163: iload 6
        //   165: istore_3
        //   166: iload 7
        //   168: istore_2
        //   169: aload 9
        //   171: monitorenter
        //   172: iload 5
        //   174: istore_3
        //   175: aload_1
        //   176: getfield 90	android/os/Message:arg1	I
        //   179: tableswitch	default:+411->590, 0:+72->251, 1:+221->400
        //   200: iload 5
        //   202: istore_3
        //   203: ldc 37
        //   205: ldc 92
        //   207: invokestatic 73	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
        //   210: pop
        //   211: iload 5
        //   213: istore_3
        //   214: aload 10
        //   216: bipush -4
        //   218: putfield 50	android/os/Message:what	I
        //   221: iload 5
        //   223: istore_3
        //   224: aload_0
        //   225: getfield 21	com/dsi/ant/plugins/AntPluginService$4:this$0	Lcom/dsi/ant/plugins/AntPluginService;
        //   228: aload_0
        //   229: getfield 25	com/dsi/ant/plugins/AntPluginService$4:val$msgr_ResultMessenger	Landroid/os/Messenger;
        //   232: aload 10
        //   234: invokevirtual 96	com/dsi/ant/plugins/AntPluginService:dumbfireSendResult	(Landroid/os/Messenger;Landroid/os/Message;)V
        //   237: iload 5
        //   239: istore_3
        //   240: aload 9
        //   242: monitorexit
        //   243: aload_0
        //   244: getfield 23	com/dsi/ant/plugins/AntPluginService$4:val$antChannel	Lcom/dsi/ant/channel/AntChannel;
        //   247: invokevirtual 83	com/dsi/ant/channel/AntChannel:release	()V
        //   250: return
        //   251: iload 5
        //   253: istore_3
        //   254: aload_1
        //   255: invokevirtual 100	android/os/Message:getData	()Landroid/os/Bundle;
        //   258: astore 11
        //   260: iload 5
        //   262: istore_3
        //   263: aload_0
        //   264: getfield 21	com/dsi/ant/plugins/AntPluginService$4:this$0	Lcom/dsi/ant/plugins/AntPluginService;
        //   267: aload_0
        //   268: getfield 23	com/dsi/ant/plugins/AntPluginService$4:val$antChannel	Lcom/dsi/ant/channel/AntChannel;
        //   271: aload_1
        //   272: getfield 103	android/os/Message:arg2	I
        //   275: aload 11
        //   277: ldc 105
        //   279: invokevirtual 111	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
        //   282: invokevirtual 115	com/dsi/ant/plugins/AntPluginService:createNewDeviceFromSearchResults	(Lcom/dsi/ant/channel/AntChannel;ILjava/lang/String;)Lcom/dsi/ant/plugins/AntPluginDevice;
        //   285: astore_1
        //   286: aload_1
        //   287: ifnonnull +54 -> 341
        //   290: iload 5
        //   292: istore_3
        //   293: ldc 37
        //   295: ldc 117
        //   297: invokestatic 73	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
        //   300: pop
        //   301: iload 5
        //   303: istore_3
        //   304: aload 10
        //   306: bipush -4
        //   308: putfield 50	android/os/Message:what	I
        //   311: iload 5
        //   313: istore_3
        //   314: aload_0
        //   315: getfield 21	com/dsi/ant/plugins/AntPluginService$4:this$0	Lcom/dsi/ant/plugins/AntPluginService;
        //   318: aload_0
        //   319: getfield 25	com/dsi/ant/plugins/AntPluginService$4:val$msgr_ResultMessenger	Landroid/os/Messenger;
        //   322: aload 10
        //   324: invokevirtual 96	com/dsi/ant/plugins/AntPluginService:dumbfireSendResult	(Landroid/os/Messenger;Landroid/os/Message;)V
        //   327: iload 5
        //   329: istore_3
        //   330: aload 9
        //   332: monitorexit
        //   333: aload_0
        //   334: getfield 23	com/dsi/ant/plugins/AntPluginService$4:val$antChannel	Lcom/dsi/ant/channel/AntChannel;
        //   337: invokevirtual 83	com/dsi/ant/channel/AntChannel:release	()V
        //   340: return
        //   341: iload 5
        //   343: istore_3
        //   344: aload_0
        //   345: getfield 21	com/dsi/ant/plugins/AntPluginService$4:this$0	Lcom/dsi/ant/plugins/AntPluginService;
        //   348: getfield 87	com/dsi/ant/plugins/AntPluginService:mConnectedDevices	Ljava/util/ArrayList;
        //   351: aload_1
        //   352: invokevirtual 123	java/util/ArrayList:add	(Ljava/lang/Object;)Z
        //   355: pop
        //   356: iconst_0
        //   357: istore_2
        //   358: aload 9
        //   360: monitorexit
        //   361: aload_0
        //   362: getfield 21	com/dsi/ant/plugins/AntPluginService$4:this$0	Lcom/dsi/ant/plugins/AntPluginService;
        //   365: aload_0
        //   366: getfield 27	com/dsi/ant/plugins/AntPluginService$4:val$prospectiveClient	Lcom/dsi/ant/plugins/AntPluginDevice$ClientInfo;
        //   369: aload_1
        //   370: aload_0
        //   371: getfield 25	com/dsi/ant/plugins/AntPluginService$4:val$msgr_ResultMessenger	Landroid/os/Messenger;
        //   374: aconst_null
        //   375: invokevirtual 127	com/dsi/ant/plugins/AntPluginService:subscribeToDeviceAndNotifyClient	(Lcom/dsi/ant/plugins/AntPluginDevice$ClientInfo;Lcom/dsi/ant/plugins/AntPluginDevice;Landroid/os/Messenger;Landroid/os/Bundle;)Z
        //   378: istore 8
        //   380: iload 8
        //   382: ifne +197 -> 579
        //   385: iload 4
        //   387: istore_2
        //   388: iload_2
        //   389: ifeq -242 -> 147
        //   392: aload_0
        //   393: getfield 23	com/dsi/ant/plugins/AntPluginService$4:val$antChannel	Lcom/dsi/ant/channel/AntChannel;
        //   396: invokevirtual 83	com/dsi/ant/channel/AntChannel:release	()V
        //   399: return
        //   400: iload 5
        //   402: istore_3
        //   403: aload_1
        //   404: getfield 103	android/os/Message:arg2	I
        //   407: istore_2
        //   408: iload 5
        //   410: istore_3
        //   411: aload_0
        //   412: getfield 21	com/dsi/ant/plugins/AntPluginService$4:this$0	Lcom/dsi/ant/plugins/AntPluginService;
        //   415: iload_2
        //   416: invokevirtual 131	com/dsi/ant/plugins/AntPluginService:getAlreadyConnectedDevice	(I)Lcom/dsi/ant/plugins/AntPluginDevice;
        //   419: astore_1
        //   420: aload_1
        //   421: ifnonnull +161 -> 582
        //   424: iload 5
        //   426: istore_3
        //   427: ldc 37
        //   429: ldc -123
        //   431: invokestatic 73	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
        //   434: pop
        //   435: iload 5
        //   437: istore_3
        //   438: aload 10
        //   440: bipush -4
        //   442: putfield 50	android/os/Message:what	I
        //   445: iload 5
        //   447: istore_3
        //   448: aload_0
        //   449: getfield 25	com/dsi/ant/plugins/AntPluginService$4:val$msgr_ResultMessenger	Landroid/os/Messenger;
        //   452: aload 10
        //   454: invokevirtual 78	android/os/Messenger:send	(Landroid/os/Message;)V
        //   457: iload 5
        //   459: istore_3
        //   460: aload 9
        //   462: monitorexit
        //   463: aload_0
        //   464: getfield 23	com/dsi/ant/plugins/AntPluginService$4:val$antChannel	Lcom/dsi/ant/channel/AntChannel;
        //   467: invokevirtual 83	com/dsi/ant/channel/AntChannel:release	()V
        //   470: return
        //   471: astore_1
        //   472: iload_3
        //   473: istore_2
        //   474: iload_2
        //   475: istore_3
        //   476: aload 9
        //   478: monitorexit
        //   479: iload_2
        //   480: istore_3
        //   481: aload_1
        //   482: athrow
        //   483: astore_1
        //   484: iload_3
        //   485: istore_2
        //   486: ldc 37
        //   488: ldc -121
        //   490: invokestatic 73	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
        //   493: pop
        //   494: iload_3
        //   495: istore_2
        //   496: aload_0
        //   497: getfield 23	com/dsi/ant/plugins/AntPluginService$4:val$antChannel	Lcom/dsi/ant/channel/AntChannel;
        //   500: invokevirtual 83	com/dsi/ant/channel/AntChannel:release	()V
        //   503: iload_3
        //   504: ifeq -357 -> 147
        //   507: aload_0
        //   508: getfield 23	com/dsi/ant/plugins/AntPluginService$4:val$antChannel	Lcom/dsi/ant/channel/AntChannel;
        //   511: invokevirtual 83	com/dsi/ant/channel/AntChannel:release	()V
        //   514: return
        //   515: iload 6
        //   517: istore_3
        //   518: iload 7
        //   520: istore_2
        //   521: aload 10
        //   523: bipush -2
        //   525: putfield 50	android/os/Message:what	I
        //   528: iload 6
        //   530: istore_3
        //   531: iload 7
        //   533: istore_2
        //   534: aload_0
        //   535: getfield 25	com/dsi/ant/plugins/AntPluginService$4:val$msgr_ResultMessenger	Landroid/os/Messenger;
        //   538: aload 10
        //   540: invokevirtual 78	android/os/Messenger:send	(Landroid/os/Message;)V
        //   543: aload_0
        //   544: getfield 23	com/dsi/ant/plugins/AntPluginService$4:val$antChannel	Lcom/dsi/ant/channel/AntChannel;
        //   547: invokevirtual 83	com/dsi/ant/channel/AntChannel:release	()V
        //   550: return
        //   551: astore_1
        //   552: iload_2
        //   553: ifeq +10 -> 563
        //   556: aload_0
        //   557: getfield 23	com/dsi/ant/plugins/AntPluginService$4:val$antChannel	Lcom/dsi/ant/channel/AntChannel;
        //   560: invokevirtual 83	com/dsi/ant/channel/AntChannel:release	()V
        //   563: aload_1
        //   564: athrow
        //   565: astore_1
        //   566: goto -14 -> 552
        //   569: astore_1
        //   570: iload_2
        //   571: istore_3
        //   572: goto -88 -> 484
        //   575: astore_1
        //   576: goto -102 -> 474
        //   579: goto -191 -> 388
        //   582: iconst_1
        //   583: istore_2
        //   584: goto -226 -> 358
        //   587: goto -507 -> 80
        //   590: goto -390 -> 200
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	593	0	this	4
        //   0	593	1	paramAnonymousMessage	Message
        //   47	537	2	i	int
        //   44	528	3	j	int
        //   10	376	4	k	int
        //   1	457	5	m	int
        //   4	525	6	n	int
        //   7	525	7	i1	int
        //   378	3	8	bool	boolean
        //   161	316	9	localArrayList	ArrayList
        //   40	499	10	localMessage	Message
        //   258	18	11	localBundle	Bundle
        // Exception table:
        //   from	to	target	type
        //   175	200	471	finally
        //   203	211	471	finally
        //   214	221	471	finally
        //   224	237	471	finally
        //   240	243	471	finally
        //   254	260	471	finally
        //   263	286	471	finally
        //   293	301	471	finally
        //   304	311	471	finally
        //   314	327	471	finally
        //   330	333	471	finally
        //   344	356	471	finally
        //   403	408	471	finally
        //   411	420	471	finally
        //   427	435	471	finally
        //   438	445	471	finally
        //   448	457	471	finally
        //   460	463	471	finally
        //   476	479	471	finally
        //   48	80	483	android/os/RemoteException
        //   86	112	483	android/os/RemoteException
        //   118	125	483	android/os/RemoteException
        //   131	140	483	android/os/RemoteException
        //   154	163	483	android/os/RemoteException
        //   169	172	483	android/os/RemoteException
        //   481	483	483	android/os/RemoteException
        //   521	528	483	android/os/RemoteException
        //   534	543	483	android/os/RemoteException
        //   48	80	551	finally
        //   86	112	551	finally
        //   118	125	551	finally
        //   131	140	551	finally
        //   154	163	551	finally
        //   169	172	551	finally
        //   481	483	551	finally
        //   486	494	551	finally
        //   496	503	551	finally
        //   521	528	551	finally
        //   534	543	551	finally
        //   361	380	565	finally
        //   361	380	569	android/os/RemoteException
        //   358	361	575	finally
      }
    }));
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
  
  public void requestAccessToken(final Bundle paramBundle)
  {
    Messenger localMessenger = (Messenger)paramBundle.get("msgr_ReqAccResultReceiver");
    if (localMessenger == null) {
      throw new IllegalArgumentException("'MSG_REQACC_PARAM_msgrRESULTRECEIVER' missing from intent bundle");
    }
    if (this.mArsComm == null) {
      this.mArsConnection = new ServiceConnection()
      {
        private final Object connLock = new Object();
        private boolean serviceHasDied = false;
        
        public void onServiceConnected(ComponentName arg1, IBinder paramAnonymousIBinder)
        {
          synchronized (this.connLock)
          {
            if (!this.serviceHasDied)
            {
              AntPluginService.this.mArsComm = new AntService(paramAnonymousIBinder);
              AntPluginService.this.requestAccessToken(paramBundle);
            }
            return;
          }
        }
        
        public void onServiceDisconnected(ComponentName arg1)
        {
          synchronized (this.connLock)
          {
            Log.e("PluginService", "Plugin-ARS binder OnServiceDisconnected!");
            this.serviceHasDied = true;
            AntPluginService.this.mArsComm = null;
            AntPluginService.this.mArsConnection = null;
            Iterator localIterator = AntPluginService.this.mConnectedDevices.iterator();
            if (!localIterator.hasNext()) {
              return;
            }
            ((AntPluginDevice)localIterator.next()).closeDevice();
          }
        }
      };
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
  
  public void startSearchByAntDeviceID(int paramInt, final AntPluginDevice.ClientInfo paramClientInfo, final Messenger paramMessenger, Bundle paramBundle)
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
      paramBundle = new AntChannelExecutor.IDeathHandler()
      {
        public void onExecutorDeath()
        {
          Log.e("AntPluginService", "Plugin search by deviceID search failed: channel died");
          Message localMessage = Message.obtain();
          localMessage.what = -4;
          AntPluginService.this.dumbfireSendResult(paramMessenger, localMessage);
        }
      };
      try
      {
        localObject = new AntChannelExecutor((AntChannel)localObject, paramBundle);
        paramClientInfo = new SingleSearchControllerTask(paramInt, n, m, j, k, i, new AbstractSearchControllerTask.SearchResultReceiver()
        {
          public void onSearchResult(int paramAnonymousInt, ChannelId paramAnonymousChannelId)
          {
            Message localMessage = Message.obtain();
            switch (paramAnonymousInt)
            {
            default: 
              this.val$executor.shutdown(true);
              Log.e("AntPluginService", "Plugin search by deviceID search failed internally with search result: " + paramAnonymousInt);
              localMessage.what = -4;
              AntPluginService.this.dumbfireSendResult(paramMessenger, localMessage);
            case 10: 
              AntChannel localAntChannel;
              do
              {
                return;
                localAntChannel = this.val$executor.shutdown(false);
                if (localAntChannel == null)
                {
                  Log.e("AntPluginService", "Plugin search by deviceID failed to shutdown executor cleanly");
                  localMessage.what = -4;
                  AntPluginService.this.dumbfireSendResult(paramMessenger, localMessage);
                  return;
                }
                String str = "Device " + paramAnonymousChannelId.getDeviceNumber();
                paramAnonymousChannelId = AntPluginService.this.createNewDeviceFromSearchResults(localAntChannel, paramAnonymousChannelId.getDeviceNumber(), str);
                if (paramAnonymousChannelId == null)
                {
                  Log.e("AntPluginService", "Plugin search by deviceID search failed internally: Device instantiation failed.");
                  localMessage.what = -4;
                  AntPluginService.this.dumbfireSendResult(paramMessenger, localMessage);
                  return;
                }
                AntPluginService.this.mConnectedDevices.add(paramAnonymousChannelId);
              } while (AntPluginService.this.subscribeToDeviceAndNotifyClient(paramClientInfo, paramAnonymousChannelId, paramMessenger, null));
              localAntChannel.release();
              return;
            }
            this.val$executor.shutdown(true);
            localMessage.what = -7;
            AntPluginService.this.dumbfireSendResult(paramMessenger, localMessage);
          }
        });
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
