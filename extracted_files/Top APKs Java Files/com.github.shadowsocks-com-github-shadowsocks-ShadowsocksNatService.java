package com.github.shadowsocks;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;
import com.github.shadowsocks.aidl.Config;
import com.github.shadowsocks.aidl.IShadowsocksService.Stub;
import com.github.shadowsocks.aidl.IShadowsocksServiceCallback;
import com.github.shadowsocks.utils.Action.;
import com.github.shadowsocks.utils.ConfigUtils.;
import com.github.shadowsocks.utils.Console.;
import com.github.shadowsocks.utils.Mode.;
import com.github.shadowsocks.utils.Route.;
import com.github.shadowsocks.utils.State.;
import com.github.shadowsocks.utils.TrafficMonitorThread;
import com.github.shadowsocks.utils.Utils.;
import com.google.android.gms.tagmanager.ContainerHolder;
import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.Locale;
import java.util.Timer;
import scala.MatchError;
import scala.None.;
import scala.Predef.;
import scala.Predef.ArrowAssoc.;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.JavaConversions.;
import scala.collection.JavaConverters.;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.convert.Decorators.AsScala;
import scala.collection.immutable.Map;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArrayOps;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Buffer.;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.WrappedArray;
import scala.reflect.ClassTag.;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction0.mcV.sp;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.NonLocalReturnControl.mcV.sp;

@ScalaSignature
public class ShadowsocksNatService
  extends Service
  implements BaseService
{
  private final String CMD_IPTABLES_DNAT_ADD_SOCKS;
  private final String CMD_IPTABLES_RETURN;
  private final String TAG;
  private final IShadowsocksService.Stub binder;
  private final RemoteCallbackList<IShadowsocksServiceCallback> callbacks;
  private int callbacksCount;
  private BroadcastReceiver closeReceiver;
  private volatile int com$github$shadowsocks$BaseService$$state;
  private final SparseArray<String> com$github$shadowsocks$ShadowsocksNatService$$dnsAddressCache;
  private ShadowsocksNotification com$github$shadowsocks$ShadowsocksNatService$$notification;
  private volatile Config config;
  private final int myUid;
  private Process pdnsdProcess;
  private Process redsocksProcess;
  private Process sslocalProcess;
  private Process sstunnelProcess;
  private Timer timer;
  private TrafficMonitorThread trafficMonitorThread;
  
  public ShadowsocksNatService()
  {
    BaseService.class.$init$(this);
    this.TAG = "ShadowsocksNatService";
    this.CMD_IPTABLES_RETURN = " -t nat -A OUTPUT -p tcp -d 0.0.0.0 -j RETURN";
    this.CMD_IPTABLES_DNAT_ADD_SOCKS = " -t nat -A OUTPUT -p tcp -j DNAT --to-destination 127.0.0.1:8123";
    this.myUid = android.os.Process.myUid();
    this.com$github$shadowsocks$ShadowsocksNatService$$dnsAddressCache = new SparseArray();
  }
  
  private ShadowsocksNotification com$github$shadowsocks$ShadowsocksNatService$$notification()
  {
    return this.com$github$shadowsocks$ShadowsocksNatService$$notification;
  }
  
  public String CMD_IPTABLES_DNAT_ADD_SOCKS()
  {
    return this.CMD_IPTABLES_DNAT_ADD_SOCKS;
  }
  
  public String CMD_IPTABLES_RETURN()
  {
    return this.CMD_IPTABLES_RETURN;
  }
  
  public String TAG()
  {
    return this.TAG;
  }
  
  public IShadowsocksService.Stub binder()
  {
    return this.binder;
  }
  
  public final RemoteCallbackList<IShadowsocksServiceCallback> callbacks()
  {
    return this.callbacks;
  }
  
  public int callbacksCount()
  {
    return this.callbacksCount;
  }
  
  public void callbacksCount_$eq(int paramInt)
  {
    this.callbacksCount = paramInt;
  }
  
  public void changeState(int paramInt)
  {
    BaseService.class.changeState(this, paramInt);
  }
  
  public void changeState(int paramInt, String paramString)
  {
    BaseService.class.changeState(this, paramInt, paramString);
  }
  
  public BroadcastReceiver closeReceiver()
  {
    return this.closeReceiver;
  }
  
  public void closeReceiver_$eq(BroadcastReceiver paramBroadcastReceiver)
  {
    this.closeReceiver = paramBroadcastReceiver;
  }
  
  public int com$github$shadowsocks$BaseService$$state()
  {
    return this.com$github$shadowsocks$BaseService$$state;
  }
  
  public void com$github$shadowsocks$BaseService$$state_$eq(int paramInt)
  {
    this.com$github$shadowsocks$BaseService$$state = paramInt;
  }
  
  public void com$github$shadowsocks$BaseService$_setter_$binder_$eq(IShadowsocksService.Stub paramStub)
  {
    this.binder = paramStub;
  }
  
  public final void com$github$shadowsocks$BaseService$_setter_$callbacks_$eq(RemoteCallbackList paramRemoteCallbackList)
  {
    this.callbacks = paramRemoteCallbackList;
  }
  
  public SparseArray<String> com$github$shadowsocks$ShadowsocksNatService$$dnsAddressCache()
  {
    return this.com$github$shadowsocks$ShadowsocksNatService$$dnsAddressCache;
  }
  
  public void com$github$shadowsocks$ShadowsocksNatService$$notification_$eq(ShadowsocksNotification paramShadowsocksNotification)
  {
    this.com$github$shadowsocks$ShadowsocksNatService$$notification = paramShadowsocksNotification;
  }
  
  public final void com$github$shadowsocks$ShadowsocksNatService$$onReceive$body$1(Context paramContext, Intent paramIntent)
  {
    setupDns();
  }
  
  public final void com$github$shadowsocks$ShadowsocksNatService$$onReceive$body$2(Context paramContext, Intent paramIntent)
  {
    Toast.makeText(paramContext, 2131165322, 0).show();
    stopRunner();
  }
  
  public Config config()
  {
    return this.config;
  }
  
  public void config_$eq(Config paramConfig)
  {
    this.config = paramConfig;
  }
  
  public void flushDns()
  {
    if (Utils..MODULE$.isLollipopOrAbove())
    {
      final ConnectivityManager localConnectivityManager = (ConnectivityManager)getSystemService("connectivity");
      Network[] arrayOfNetwork = localConnectivityManager.getAllNetworks();
      final ArrayBuffer localArrayBuffer = new ArrayBuffer();
      Predef..MODULE$.refArrayOps((Object[])arrayOfNetwork).foreach(new AbstractFunction1()
      {
        private final ArrayBuffer cmdBuf$3;
        private final ConnectivityManager manager$2;
        
        public final void apply(Network paramAnonymousNetwork)
        {
          if (localConnectivityManager.getNetworkInfo(paramAnonymousNetwork).isAvailable())
          {
            int i = BoxesRunTime.unboxToInt(paramAnonymousNetwork.getClass().getDeclaredField("netId").get(paramAnonymousNetwork));
            localArrayBuffer.append(Predef..MODULE$.wrapRefArray((Object[])new String[] { new StringOps(Predef..MODULE$.augmentString("ndc resolver flushnet %d")).formatLocal(Locale.ENGLISH, Predef..MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(i) })) }));
          }
        }
      });
      Console..MODULE$.runRootCommand((String[])localArrayBuffer.toArray(ClassTag..MODULE$.apply(String.class)));
      return;
    }
    Console..MODULE$.runRootCommand(Predef..MODULE$.wrapRefArray((Object[])new String[] { "ndc resolver flushdefaultif", "ndc resolver flushif wlan0" }));
  }
  
  public Context getContext()
  {
    return getBaseContext();
  }
  
  public int getNetId(Network paramNetwork)
  {
    return BoxesRunTime.unboxToInt(paramNetwork.getClass().getDeclaredField("netId").get(paramNetwork));
  }
  
  public int getServiceMode()
  {
    return Mode..MODULE$.NAT();
  }
  
  public int getState()
  {
    return BaseService.class.getState(this);
  }
  
  public boolean handleConnection()
  {
    startTunnel();
    if (!config().isUdpDns) {
      startDnsDaemon();
    }
    startRedsocksDaemon();
    startShadowsocksDaemon();
    setupIptables();
    return true;
  }
  
  public void killProcesses()
  {
    if (sslocalProcess() != null)
    {
      sslocalProcess().destroy();
      sslocalProcess_$eq(null);
    }
    if (sstunnelProcess() != null)
    {
      sstunnelProcess().destroy();
      sstunnelProcess_$eq(null);
    }
    if (redsocksProcess() != null)
    {
      redsocksProcess().destroy();
      redsocksProcess_$eq(null);
    }
    if (pdnsdProcess() != null)
    {
      pdnsdProcess().destroy();
      pdnsdProcess_$eq(null);
    }
    Console..MODULE$.runRootCommand(Predef..MODULE$.wrapRefArray((Object[])new String[] { new StringBuilder().append(Utils..MODULE$.getIptables()).append(" -t nat -F OUTPUT").toString() }));
  }
  
  public int myUid()
  {
    return this.myUid;
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    Log.d(TAG(), "onBind");
    String str = Action..MODULE$.SERVICE();
    paramIntent = paramIntent.getAction();
    if (str == null)
    {
      if (paramIntent == null) {}
    }
    else {
      while (!str.equals(paramIntent)) {
        return null;
      }
    }
    return binder();
  }
  
  public void onCreate()
  {
    super.onCreate();
    ConfigUtils..MODULE$.refresh(this);
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    return BaseService.class.onStartCommand(this, paramIntent, paramInt1, paramInt2);
  }
  
  public Process pdnsdProcess()
  {
    return this.pdnsdProcess;
  }
  
  public void pdnsdProcess_$eq(Process paramProcess)
  {
    this.pdnsdProcess = paramProcess;
  }
  
  public Process redsocksProcess()
  {
    return this.redsocksProcess;
  }
  
  public void redsocksProcess_$eq(Process paramProcess)
  {
    this.redsocksProcess = paramProcess;
  }
  
  public void setDnsForAllNetwork(final String paramString)
  {
    final Object localObject = new Object();
    try
    {
      final ConnectivityManager localConnectivityManager = (ConnectivityManager)getSystemService("connectivity");
      Network[] arrayOfNetwork = localConnectivityManager.getAllNetworks();
      if (arrayOfNetwork == null) {
        return;
      }
      final ArrayBuffer localArrayBuffer = new ArrayBuffer();
      Predef..MODULE$.refArrayOps((Object[])arrayOfNetwork).foreach(new AbstractFunction1()
      {
        private final ArrayBuffer cmdBuf$2;
        private final String dns$1;
        private final ConnectivityManager manager$1;
        private final Object nonLocalReturnKey1$1;
        
        public final void apply(Network paramAnonymousNetwork)
        {
          Object localObject = localConnectivityManager.getNetworkInfo(paramAnonymousNetwork);
          if (localObject == null) {
            throw new NonLocalReturnControl.mcV.sp(localObject, BoxedUnit.UNIT);
          }
          int i;
          if (((NetworkInfo)localObject).isConnected())
          {
            i = ShadowsocksNatService.this.getNetId(paramAnonymousNetwork);
            paramAnonymousNetwork = localConnectivityManager.getLinkProperties(paramAnonymousNetwork).getDnsServers();
            if (paramAnonymousNetwork != null)
            {
              paramAnonymousNetwork = ((TraversableOnce)((TraversableLike)JavaConverters..MODULE$.asScalaBufferConverter(paramAnonymousNetwork).asScala()).map(new AbstractFunction1()
              {
                public final String apply(InetAddress paramAnonymous2InetAddress)
                {
                  return paramAnonymous2InetAddress.getHostAddress();
                }
              }, Buffer..MODULE$.canBuildFrom())).mkString(" ");
              localObject = paramString;
              if (paramAnonymousNetwork != null) {
                break label197;
              }
              if (localObject == null) {}
            }
          }
          for (;;)
          {
            ShadowsocksNatService.this.com$github$shadowsocks$ShadowsocksNatService$$dnsAddressCache().put(i, paramAnonymousNetwork);
            localArrayBuffer.append(Predef..MODULE$.wrapRefArray((Object[])new String[] { new StringOps(Predef..MODULE$.augmentString("ndc resolver setnetdns %d \"\" %s")).formatLocal(Locale.ENGLISH, Predef..MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(i), paramString })) }));
            label197:
            do
            {
              return;
            } while (paramAnonymousNetwork.equals(localObject));
          }
        }
      });
      if (localArrayBuffer.nonEmpty())
      {
        Console..MODULE$.runRootCommand((String[])localArrayBuffer.toArray(ClassTag..MODULE$.apply(String.class)));
        return;
      }
    }
    catch (NonLocalReturnControl paramString)
    {
      if (paramString.key() == localObject)
      {
        paramString.value$mcV$sp();
        return;
      }
      throw paramString;
    }
  }
  
  public void setupDns()
  {
    setDnsForAllNetwork("127.0.0.1");
  }
  
  public String setupIptables()
  {
    final ArrayBuffer localArrayBuffer1 = new ArrayBuffer();
    final ArrayBuffer localArrayBuffer2 = new ArrayBuffer();
    localArrayBuffer1.append(Predef..MODULE$.wrapRefArray((Object[])new String[] { "ulimit -n 4096" }));
    localArrayBuffer1.append(Predef..MODULE$.wrapRefArray((Object[])new String[] { new StringBuilder().append(Utils..MODULE$.getIptables()).append(" -t nat -F OUTPUT").toString() }));
    final String str = new StringBuilder().append(Utils..MODULE$.getIptables()).append(CMD_IPTABLES_RETURN()).toString();
    if (!(InetAddress.getByName(config().proxy.toUpperCase()) instanceof Inet6Address)) {
      localArrayBuffer1.append(Predef..MODULE$.wrapRefArray((Object[])new String[] { str.replace("-p tcp -d 0.0.0.0", new StringBuilder().append("-d ").append(config().proxy).toString()) }));
    }
    localArrayBuffer1.append(Predef..MODULE$.wrapRefArray((Object[])new String[] { str.replace("-p tcp -d 0.0.0.0", "-d 127.0.0.1") }));
    localArrayBuffer1.append(Predef..MODULE$.wrapRefArray((Object[])new String[] { str.replace("-p tcp -d 0.0.0.0", new StringBuilder().append("-m owner --uid-owner ").append(BoxesRunTime.boxToInteger(myUid())).toString()) }));
    localArrayBuffer1.append(Predef..MODULE$.wrapRefArray((Object[])new String[] { str.replace("-d 0.0.0.0", "--dport 53") }));
    localArrayBuffer1.append(Predef..MODULE$.wrapRefArray((Object[])new String[] { new StringBuilder().append(Utils..MODULE$.getIptables()).append(" -t nat -A OUTPUT -p udp --dport 53 -j DNAT --to-destination 127.0.0.1:8153").toString() }));
    if ((!config().isProxyApps) || (config().isBypassApps)) {
      localArrayBuffer2.append(Predef..MODULE$.wrapRefArray((Object[])new String[] { new StringBuilder().append(Utils..MODULE$.getIptables()).append(CMD_IPTABLES_DNAT_ADD_SOCKS()).toString() }));
    }
    if (config().isProxyApps)
    {
      final Map localMap = ((TraversableOnce)JavaConversions..MODULE$.asScalaBuffer(getPackageManager().getInstalledApplications(0)).map(new AbstractFunction1()
      {
        public final Tuple2<String, Object> apply(ApplicationInfo paramAnonymousApplicationInfo)
        {
          return Predef.ArrowAssoc..MODULE$.$minus$greater$extension(Predef..MODULE$.ArrowAssoc(paramAnonymousApplicationInfo.packageName), BoxesRunTime.boxToInteger(paramAnonymousApplicationInfo.uid));
        }
      }, Buffer..MODULE$.canBuildFrom())).toMap(Predef..MODULE$.$conforms());
      Predef..MODULE$.refArrayOps((Object[])new StringOps(Predef..MODULE$.augmentString(config().proxiedAppString)).split('\n')).foreach(new AbstractFunction1()
      {
        private final String cmd_bypass$1;
        private final ArrayBuffer http_sb$1;
        private final ArrayBuffer init_sb$1;
        private final Map uidMap$1;
        
        public final void apply(String paramAnonymousString)
        {
          paramAnonymousString = localMap.get(paramAnonymousString);
          if ((paramAnonymousString instanceof Some))
          {
            int i = BoxesRunTime.unboxToInt(((Some)paramAnonymousString).x());
            if (ShadowsocksNatService.this.config().isBypassApps)
            {
              localArrayBuffer1.append(Predef..MODULE$.wrapRefArray((Object[])new String[] { str.replace("-d 0.0.0.0", new StringBuilder().append("-m owner --uid-owner ").append(BoxesRunTime.boxToInteger(i)).toString()) }));
              paramAnonymousString = BoxedUnit.UNIT;
              return;
            }
            localArrayBuffer2.append(Predef..MODULE$.wrapRefArray((Object[])new String[] { new StringBuilder().append(Utils..MODULE$.getIptables()).append(ShadowsocksNatService.this.CMD_IPTABLES_DNAT_ADD_SOCKS()).toString().replace("-t nat", new StringBuilder().append("-t nat -m owner --uid-owner ").append(BoxesRunTime.boxToInteger(i)).toString()) }));
            paramAnonymousString = BoxedUnit.UNIT;
            return;
          }
          paramAnonymousString = BoxedUnit.UNIT;
        }
      });
    }
    return Console..MODULE$.runRootCommand((String[])localArrayBuffer1.$plus$plus(localArrayBuffer2).toArray(ClassTag..MODULE$.apply(String.class)));
  }
  
  public Process sslocalProcess()
  {
    return this.sslocalProcess;
  }
  
  public void sslocalProcess_$eq(Process paramProcess)
  {
    this.sslocalProcess = paramProcess;
  }
  
  public Process sstunnelProcess()
  {
    return this.sstunnelProcess;
  }
  
  public void sstunnelProcess_$eq(Process paramProcess)
  {
    this.sstunnelProcess = paramProcess;
  }
  
  public void startDnsDaemon()
  {
    final String str1 = config().route;
    String str2 = Route..MODULE$.BYPASS_CHN();
    if (str1 == null) {
      if (str2 == null) {
        break label251;
      }
    }
    for (str1 = new StringOps(Predef..MODULE$.augmentString(ConfigUtils..MODULE$.PDNSD_LOCAL())).formatLocal(Locale.ENGLISH, Predef..MODULE$.genericWrapArray(new Object[] { getApplicationInfo().dataDir, "127.0.0.1", BoxesRunTime.boxToInteger(8153), BoxesRunTime.boxToInteger(8163), "" }));; str1 = new StringOps(Predef..MODULE$.augmentString(ConfigUtils..MODULE$.PDNSD_DIRECT())).formatLocal(Locale.ENGLISH, Predef..MODULE$.genericWrapArray(new Object[] { getApplicationInfo().dataDir, "127.0.0.1", BoxesRunTime.boxToInteger(8153), str1, str2, BoxesRunTime.boxToInteger(8163), "" })))
    {
      ConfigUtils..MODULE$.printToFile(new File(new StringBuilder().append(getApplicationInfo().dataDir).append("/pdnsd-nat.conf").toString()), new AbstractFunction1()
      {
        private final String conf$4;
        
        public final void apply(PrintWriter paramAnonymousPrintWriter)
        {
          paramAnonymousPrintWriter.println(str1);
        }
      });
      str1 = new StringBuilder().append(getApplicationInfo().dataDir).append("/pdnsd -c ").append(getApplicationInfo().dataDir).append("/pdnsd-nat.conf").toString();
      pdnsdProcess_$eq(new ProcessBuilder(new String[0]).command(JavaConversions..MODULE$.seqAsJavaList(Predef..MODULE$.refArrayOps((Object[])str1.split(" ")).toSeq())).redirectErrorStream(true).start());
      return;
      if (!str1.equals(str2)) {
        break;
      }
      label251:
      str1 = ConfigUtils..MODULE$.getRejectList(getContext());
      str2 = ConfigUtils..MODULE$.getBlackList(getContext());
    }
  }
  
  public void startRedsocksDaemon()
  {
    final String str1 = new StringOps(Predef..MODULE$.augmentString(ConfigUtils..MODULE$.REDSOCKS())).formatLocal(Locale.ENGLISH, Predef..MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(config().localPort) }));
    String str2 = new StringOps(Predef..MODULE$.augmentString("%s/redsocks -c %s/redsocks-nat.conf")).formatLocal(Locale.ENGLISH, Predef..MODULE$.genericWrapArray(new Object[] { getApplicationInfo().dataDir, getApplicationInfo().dataDir }));
    ConfigUtils..MODULE$.printToFile(new File(new StringBuilder().append(getApplicationInfo().dataDir).append("/redsocks-nat.conf").toString()), new AbstractFunction1()
    {
      private final String conf$5;
      
      public final void apply(PrintWriter paramAnonymousPrintWriter)
      {
        paramAnonymousPrintWriter.println(str1);
      }
    });
    redsocksProcess_$eq(new ProcessBuilder(new String[0]).command(JavaConversions..MODULE$.seqAsJavaList(Predef..MODULE$.refArrayOps((Object[])str2.split(" ")).toSeq())).redirectErrorStream(true).start());
  }
  
  public void startRunner(final Config paramConfig)
  {
    if (Console..MODULE$.isRoot())
    {
      BaseService.class.startRunner(this, paramConfig);
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.intent.action.ACTION_SHUTDOWN");
      localIntentFilter.addAction(Action..MODULE$.CLOSE());
      closeReceiver_$eq(new BroadcastReceiver()
      {
        public final void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
        {
          ShadowsocksNatService.this.com$github$shadowsocks$ShadowsocksNatService$$onReceive$body$2(paramAnonymousContext, paramAnonymousIntent);
        }
      });
      registerReceiver(closeReceiver(), localIntentFilter);
      ShadowsocksApplication..MODULE$.track(TAG(), "start");
      changeState(State..MODULE$.CONNECTING());
      package..MODULE$.ThrowableFuture(new AbstractFunction0.mcV.sp()
      {
        private final Config config$1;
        
        public final void apply()
        {
          apply$mcV$sp();
        }
        
        public void apply$mcV$sp()
        {
          Object localObject1 = paramConfig.proxy;
          int i;
          if (localObject1 == null)
          {
            if ("198.199.101.152" == null) {
              break label122;
            }
            if (ShadowsocksNatService.this.config() != null)
            {
              ShadowsocksNatService.this.killProcesses();
              if (!Utils..MODULE$.isNumeric(paramConfig.proxy)) {
                break label203;
              }
              i = 1;
            }
          }
          label122:
          label203:
          Object localObject2;
          for (;;)
          {
            if ((i == 0) || (!ShadowsocksNatService.this.handleConnection())) {
              break label272;
            }
            ShadowsocksNatService.this.flushDns();
            ShadowsocksNatService.this.changeState(State..MODULE$.CONNECTED());
            ShadowsocksNatService.this.com$github$shadowsocks$ShadowsocksNatService$$notification_$eq(new ShadowsocksNotification(ShadowsocksNatService.this, paramConfig.profileName, true));
            return;
            if (!localObject1.equals("198.199.101.152")) {
              break;
            }
            localObject1 = ShadowsocksApplication..MODULE$.containerHolder();
            try
            {
              ShadowsocksNatService.this.config_$eq(ConfigUtils..MODULE$.getPublicConfig(ShadowsocksNatService.this.getBaseContext(), ((ContainerHolder)localObject1).getContainer(), paramConfig));
            }
            catch (Exception localException)
            {
              ShadowsocksNatService.this.changeState(State..MODULE$.STOPPED(), ShadowsocksNatService.this.getString(2131165312));
              ShadowsocksNatService.this.stopRunner();
              ShadowsocksNatService.this.config_$eq(null);
            }
            break;
            localObject2 = Utils..MODULE$.resolve(paramConfig.proxy, true);
            if ((localObject2 instanceof Some))
            {
              localObject2 = (String)((Some)localObject2).x();
              paramConfig.proxy = ((String)localObject2);
              i = 1;
              localObject2 = BoxedUnit.UNIT;
            }
            else
            {
              if (!None..MODULE$.equals(localObject2)) {
                break label302;
              }
              i = 0;
              localObject2 = BoxedUnit.UNIT;
            }
          }
          label272:
          ShadowsocksNatService.this.changeState(State..MODULE$.STOPPED(), ShadowsocksNatService.this.getString(2131165312));
          ShadowsocksNatService.this.stopRunner();
          return;
          label302:
          throw new MatchError(localObject2);
        }
      });
      return;
    }
    changeState(State..MODULE$.STOPPED(), getString(2131165282));
  }
  
  public void startShadowsocksDaemon()
  {
    Object localObject1 = config().route;
    Object localObject2 = Route..MODULE$.ALL();
    if (localObject1 == null)
    {
      if (localObject2 == null) {}
    }
    else {
      label76:
      while (!localObject1.equals(localObject2))
      {
        localObject1 = config().route;
        localObject2 = Route..MODULE$.BYPASS_LAN();
        if (localObject2 != null) {
          break;
        }
        if (localObject1 == null) {
          break label490;
        }
        localObject2 = Route..MODULE$.BYPASS_CHN();
        if (localObject2 != null) {
          break label565;
        }
        if (localObject1 == null) {
          break label573;
        }
        localObject2 = Route..MODULE$.BYPASS_LAN_CHN();
        if (localObject2 != null) {
          break label600;
        }
        if (localObject1 == null) {
          break label608;
        }
        throw new MatchError(localObject1);
      }
    }
    label93:
    localObject1 = new StringOps(Predef..MODULE$.augmentString(ConfigUtils..MODULE$.SHADOWSOCKS())).formatLocal(Locale.ENGLISH, Predef..MODULE$.genericWrapArray(new Object[] { config().proxy, BoxesRunTime.boxToInteger(config().remotePort), BoxesRunTime.boxToInteger(config().localPort), config().sitekey, config().encMethod, BoxesRunTime.boxToInteger(600) }));
    ConfigUtils..MODULE$.printToFile(new File(new StringBuilder().append(getApplicationInfo().dataDir).append("/ss-local-nat.conf").toString()), new AbstractFunction1()
    {
      private final String conf$1;
      
      public final void apply(PrintWriter paramAnonymousPrintWriter)
      {
        paramAnonymousPrintWriter.println(this.conf$1);
      }
    });
    localObject1 = new ArrayBuffer();
    ((ArrayBuffer)localObject1).$plus$eq(new StringBuilder().append(getApplicationInfo().dataDir).append("/ss-local").toString(), "-b", Predef..MODULE$.wrapRefArray((Object[])new String[] { "127.0.0.1", "-t", "600", "-P", getApplicationInfo().dataDir, "-c", new StringBuilder().append(getApplicationInfo().dataDir).append("/ss-local-nat.conf").toString() }));
    label387:
    String str;
    if (config().isAuth)
    {
      ((ArrayBuffer)localObject1).$plus$eq("-A");
      localObject2 = config().route;
      str = Route..MODULE$.ALL();
      if (localObject2 != null) {
        break label655;
      }
      if (str == null) {
        break label663;
      }
      label410:
      ((ArrayBuffer)localObject1).$plus$eq("--acl");
      ((ArrayBuffer)localObject1).$plus$eq(new StringBuilder().append(getApplicationInfo().dataDir).append("/acl.list").toString());
    }
    for (;;)
    {
      sslocalProcess_$eq(new ProcessBuilder(new String[0]).command(JavaConversions..MODULE$.bufferAsJavaList((Buffer)localObject1)).redirectErrorStream(true).start());
      return;
      if (!localObject2.equals(localObject1)) {
        break;
      }
      label490:
      localObject1 = (String[][])new String[][] { getResources().getStringArray(2131492869) };
      for (;;)
      {
        ConfigUtils..MODULE$.printToFile(new File(new StringBuilder().append(getApplicationInfo().dataDir).append("/acl.list").toString()), new AbstractFunction1()
        {
          private final String[][] acl$1;
          
          public final void apply(final PrintWriter paramAnonymousPrintWriter)
          {
            Predef..MODULE$.refArrayOps((Object[])Predef..MODULE$.refArrayOps((Object[])this.acl$1).flatten(new AbstractFunction1()
            {
              public final WrappedArray<String> apply(String[] paramAnonymous2ArrayOfString)
              {
                return Predef..MODULE$.wrapRefArray((Object[])paramAnonymous2ArrayOfString);
              }
            }, ClassTag..MODULE$.apply(String.class))).foreach(new AbstractFunction1()
            {
              private final PrintWriter p$1;
              
              public final void apply(String paramAnonymous2String)
              {
                paramAnonymousPrintWriter.println(paramAnonymous2String);
              }
            });
          }
        });
        break label93;
        label565:
        if (!localObject2.equals(localObject1)) {
          break;
        }
        label573:
        localObject1 = (String[][])new String[][] { getResources().getStringArray(2131492866) };
        continue;
        label600:
        if (!localObject2.equals(localObject1)) {
          break label76;
        }
        label608:
        localObject1 = (String[][])new String[][] { getResources().getStringArray(2131492869), getResources().getStringArray(2131492866) };
      }
      localObject2 = BoxedUnit.UNIT;
      break label387;
      label655:
      if (!localObject2.equals(str)) {
        break label410;
      }
      label663:
      localObject2 = BoxedUnit.UNIT;
    }
  }
  
  public void startTunnel()
  {
    BoxedUnit localBoxedUnit;
    if (config().isUdpDns)
    {
      localObject = new StringOps(Predef..MODULE$.augmentString(ConfigUtils..MODULE$.SHADOWSOCKS())).formatLocal(Locale.ENGLISH, Predef..MODULE$.genericWrapArray(new Object[] { config().proxy, BoxesRunTime.boxToInteger(config().remotePort), BoxesRunTime.boxToInteger(8153), config().sitekey, config().encMethod, BoxesRunTime.boxToInteger(10) }));
      ConfigUtils..MODULE$.printToFile(new File(new StringBuilder().append(getApplicationInfo().dataDir).append("/ss-tunnel-nat.conf").toString()), new AbstractFunction1()
      {
        private final String conf$2;
        
        public final void apply(PrintWriter paramAnonymousPrintWriter)
        {
          paramAnonymousPrintWriter.println(this.conf$2);
        }
      });
      localObject = new ArrayBuffer();
      ((ArrayBuffer)localObject).$plus$eq(new StringBuilder().append(getApplicationInfo().dataDir).append("/ss-tunnel").toString(), "-u", Predef..MODULE$.wrapRefArray((Object[])new String[] { "-t", "10", "-b", "127.0.0.1", "-L", "8.8.8.8:53", "-P", getApplicationInfo().dataDir, "-c", new StringBuilder().append(getApplicationInfo().dataDir).append("/ss-tunnel-nat.conf").toString() }));
      ((ArrayBuffer)localObject).$plus$eq("-l", "8153", Predef..MODULE$.wrapRefArray((Object[])new String[0]));
      if (config().isAuth) {
        ((ArrayBuffer)localObject).$plus$eq("-A");
      }
      for (;;)
      {
        sstunnelProcess_$eq(new ProcessBuilder(new String[0]).command(JavaConversions..MODULE$.bufferAsJavaList((Buffer)localObject)).redirectErrorStream(true).start());
        return;
        localBoxedUnit = BoxedUnit.UNIT;
      }
    }
    Object localObject = new StringOps(Predef..MODULE$.augmentString(ConfigUtils..MODULE$.SHADOWSOCKS())).formatLocal(Locale.ENGLISH, Predef..MODULE$.genericWrapArray(new Object[] { config().proxy, BoxesRunTime.boxToInteger(config().remotePort), BoxesRunTime.boxToInteger(8163), config().sitekey, config().encMethod, BoxesRunTime.boxToInteger(10) }));
    ConfigUtils..MODULE$.printToFile(new File(new StringBuilder().append(getApplicationInfo().dataDir).append("/ss-tunnel-nat.conf").toString()), new AbstractFunction1()
    {
      private final String conf$3;
      
      public final void apply(PrintWriter paramAnonymousPrintWriter)
      {
        paramAnonymousPrintWriter.println(this.conf$3);
      }
    });
    localObject = new ArrayBuffer();
    ((ArrayBuffer)localObject).$plus$eq(new StringBuilder().append(getApplicationInfo().dataDir).append("/ss-tunnel").toString(), "-u", Predef..MODULE$.wrapRefArray((Object[])new String[] { "-t", "10", "-b", "127.0.0.1", "-l", "8163", "-L", "8.8.8.8:53", "-P", getApplicationInfo().dataDir, "-c", new StringBuilder().append(getApplicationInfo().dataDir).append("/ss-tunnel-nat.conf").toString() }));
    if (config().isAuth) {
      ((ArrayBuffer)localObject).$plus$eq("-A");
    }
    for (;;)
    {
      sstunnelProcess_$eq(new ProcessBuilder(new String[0]).command(JavaConversions..MODULE$.bufferAsJavaList((Buffer)localObject)).redirectErrorStream(true).start());
      return;
      localBoxedUnit = BoxedUnit.UNIT;
    }
  }
  
  public void stopRunner()
  {
    changeState(State..MODULE$.STOPPING());
    if (closeReceiver() != null)
    {
      unregisterReceiver(closeReceiver());
      closeReceiver_$eq(null);
    }
    if (com$github$shadowsocks$ShadowsocksNatService$$notification() != null) {
      com$github$shadowsocks$ShadowsocksNatService$$notification().destroy();
    }
    ShadowsocksApplication..MODULE$.track(TAG(), "stop");
    killProcesses();
    BaseService.class.stopRunner(this);
  }
  
  public Timer timer()
  {
    return this.timer;
  }
  
  public void timer_$eq(Timer paramTimer)
  {
    this.timer = paramTimer;
  }
  
  public TrafficMonitorThread trafficMonitorThread()
  {
    return this.trafficMonitorThread;
  }
  
  public void trafficMonitorThread_$eq(TrafficMonitorThread paramTrafficMonitorThread)
  {
    this.trafficMonitorThread = paramTrafficMonitorThread;
  }
  
  public void updateTrafficRate()
  {
    BaseService.class.updateTrafficRate(this);
  }
  
  public void updateTrafficTotal(long paramLong1, long paramLong2)
  {
    BaseService.class.updateTrafficTotal(this, paramLong1, paramLong2);
  }
}
