package com.dsi.ant.plugins.antplus.pccbase;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.dsi.ant.plugins.internal.pluginsipc.AntPluginDeviceDbProvider.DeviceDbDeviceInfo;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;

public abstract class a
{
  static volatile String A = "";
  static volatile String B = "";
  private static final String a = a.class.getSimpleName();
  ServiceConnection C;
  HandlerThread D = new HandlerThread("PluginPCCMsgHandler");
  Handler.Callback E = new c(this);
  HandlerThread F = new HandlerThread("PluginPCCEventHandler");
  volatile Handler G;
  Handler.Callback H = new d(this);
  UUID I;
  Messenger J;
  Exchanger<Message> K = new Exchanger();
  CyclicBarrier L = new CyclicBarrier(2);
  boolean M = false;
  CountDownLatch N = new CountDownLatch(1);
  Context O;
  AntPluginDeviceDbProvider.DeviceDbDeviceInfo P;
  Integer Q = null;
  protected int R;
  e S;
  volatile ah<?> T;
  private final ReentrantLock b = new ReentrantLock();
  private Thread c;
  private volatile boolean d = false;
  private boolean e = false;
  private Object f = new Object();
  
  public a() {}
  
  public static int a(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if (localPackageInfo.packageName.equals("com.dsi.ant.plugins.antplus"))
      {
        if (!localPackageInfo.applicationInfo.enabled) {
          return -2;
        }
        return localPackageInfo.versionCode;
      }
    }
    return -1;
  }
  
  protected static <T extends a> ah<T> a(Context paramContext, int paramInt1, int paramInt2, T paramT, f<T> paramF, e paramE)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("int_RequestAccessMode", 3);
    localBundle.putInt("int_AntDeviceID", paramInt1);
    localBundle.putInt("int_ProximityBin", paramInt2);
    return a(paramContext, localBundle, paramT, new h(), paramF, paramE);
  }
  
  protected static <T extends a> ah<T> a(Context paramContext, Bundle paramBundle, T paramT, g<T> paramG, f<T> paramF, e paramE)
  {
    if ((paramF == null) || (paramE == null))
    {
      paramBundle = new StringBuilder().append("Invalid argument: ");
      if (paramF == null) {}
      for (paramContext = "resultReceiver ";; paramContext = "stateReceiver ") {
        throw new IllegalArgumentException(paramContext + " is null ");
      }
    }
    paramT.getClass();
    paramF = new i(paramT, paramF, paramE);
    paramT.T = paramF;
    paramT.S = paramF.g;
    paramG.a(paramT, paramF.f);
    a(paramContext, paramBundle, paramT, paramG);
    return paramF;
  }
  
  protected static <T extends a> void a(Context paramContext, Bundle paramBundle, T paramT, Handler paramHandler)
  {
    if (paramHandler == null) {
      throw new IllegalArgumentException("resultHandler passed from client was null");
    }
    String str = paramContext.getPackageName();
    Object localObject = paramContext.getApplicationInfo();
    localObject = paramContext.getPackageManager().getApplicationLabel((ApplicationInfo)localObject).toString();
    paramBundle.putString("str_ApplicationNamePackage", str);
    paramBundle.putString("str_ApplicationNameTitle", (String)localObject);
    paramT.a(paramContext, paramBundle, paramHandler);
  }
  
  private void a(Intent paramIntent, Bundle paramBundle)
  {
    synchronized (this.f)
    {
      if (!this.e)
      {
        this.e = true;
        if (!this.O.bindService(paramIntent, this.C, 1))
        {
          com.dsi.ant.plugins.a.a.a.a(a, "Binding to plugin failed");
          a(paramBundle);
        }
      }
      return;
    }
  }
  
  private void a(Bundle paramBundle)
  {
    g();
    paramBundle = (Messenger)paramBundle.getParcelable("msgr_ReqAccResultReceiver");
    Message localMessage = Message.obtain();
    localMessage.what = -4;
    try
    {
      paramBundle.send(localMessage);
      return;
    }
    catch (RemoteException paramBundle)
    {
      com.dsi.ant.plugins.a.a.a.a(a, "Remote exception sending failure msg to client");
    }
  }
  
  private void a(Messenger paramMessenger, String paramString1, String paramString2)
  {
    Message localMessage = Message.obtain();
    localMessage.what = -5;
    Bundle localBundle = new Bundle();
    localBundle.putString("string_DependencyPackageName", paramString1);
    localBundle.putString("string_DependencyName", paramString2);
    localMessage.setData(localBundle);
    try
    {
      paramMessenger.send(localMessage);
      return;
    }
    catch (RemoteException paramMessenger)
    {
      a("Remote exception sending plugin 'dependency not installed' msg to client");
    }
  }
  
  private Messenger c()
  {
    this.F.start();
    this.G = new Handler(this.F.getLooper(), this.H);
    this.D.start();
    return new Messenger(new Handler(this.D.getLooper(), this.E));
  }
  
  private void c(int paramInt)
  {
    synchronized (this.b)
    {
      try
      {
        if (this.J != null) {
          this.J.send(a(paramInt, null));
        }
        return;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          com.dsi.ant.plugins.a.a.a.a(a, "RemoteException, unable to cleanly release (cmd " + paramInt + ")");
        }
      }
    }
  }
  
  private Message d(Message paramMessage)
  {
    synchronized (this.b)
    {
      this.c = Thread.currentThread();
      Messenger localMessenger = this.J;
      if (localMessenger == null) {
        break label492;
      }
      try
      {
        if (!this.b.tryLock(7000L, TimeUnit.MILLISECONDS)) {
          throw new TimeoutException();
        }
      }
      catch (InterruptedException localInterruptedException1)
      {
        a("InterruptedException obtaining mPluginCommLock in sendPluginCommand on message " + paramMessage.what);
        Thread.currentThread().interrupt();
        return null;
      }
      catch (TimeoutException localTimeoutException1)
      {
        a("TimeoutException obtaining mPluginCommLock in sendPluginCommand on message " + paramMessage.what);
        return null;
      }
    }
    for (;;)
    {
      try
      {
        this.J.send(paramMessage);
        try
        {
          localMessage = (Message)this.K.exchange(null, 5L, TimeUnit.SECONDS);
          if (localMessage.what != paramMessage.what) {
            continue;
          }
          localMessage = Message.obtain(localMessage);
        }
        catch (InterruptedException localInterruptedException2)
        {
          Message localMessage;
          a("InterruptedException in sendPluginCommand (at mPluginCommMsgExch.exchange()) on message " + paramMessage.what);
          Thread.currentThread().interrupt();
          this.b.unlock();
          return null;
        }
        catch (TimeoutException localTimeoutException2)
        {
          a("TimeoutException in sendPluginCommand (at mPluginCommMsgExch.exchange()) on message " + paramMessage.what);
          this.b.unlock();
          return null;
        }
        try
        {
          this.L.await();
          return localMessage;
        }
        catch (BrokenBarrierException localBrokenBarrierException1)
        {
          a("BrokenBarrierException in sendPluginCommand finally on message " + paramMessage.what);
          return null;
        }
        catch (InterruptedException localInterruptedException3)
        {
          a("InterruptedException in sendPluginCommand finally on message " + paramMessage.what);
          Thread.currentThread().interrupt();
          return null;
        }
        paramMessage = finally;
        throw paramMessage;
      }
      catch (RemoteException localRemoteException)
      {
        a("RemoteException sending message " + paramMessage.what + " to plugin");
        this.b.unlock();
        return null;
      }
      finally
      {
        this.b.unlock();
      }
      b(localInterruptedException3);
      try
      {
        this.L.await();
      }
      catch (BrokenBarrierException localBrokenBarrierException2)
      {
        a("BrokenBarrierException in sendPluginCommand (at non-success mPluginCommProcessingBarrier) on message " + paramMessage.what);
        this.b.unlock();
        return null;
      }
      catch (InterruptedException localInterruptedException4)
      {
        a("InterruptedException in sendPluginCommand (at non-success mPluginCommProcessingBarrier) on message " + paramMessage.what);
        Thread.currentThread().interrupt();
        this.b.unlock();
        return null;
      }
    }
    label492:
    return null;
  }
  
  private void i()
  {
    synchronized (this.f)
    {
      boolean bool = this.e;
      if (bool) {}
      try
      {
        this.O.unbindService(this.C);
        this.e = false;
        return;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        for (;;)
        {
          com.dsi.ant.plugins.a.a.a.a(a, "Unexpected error unbinding service, " + localIllegalArgumentException);
        }
      }
    }
  }
  
  protected abstract Intent a();
  
  protected Message a(int paramInt, Bundle paramBundle)
  {
    Message localMessage = Message.obtain();
    localMessage.what = paramInt;
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    localBundle.putSerializable("uuid_AccessToken", this.I);
    localMessage.setData(localBundle);
    return localMessage;
  }
  
  protected void a(Context paramContext, Bundle paramBundle, Handler paramHandler)
  {
    this.O = paramContext;
    paramContext = new Messenger(paramHandler);
    paramBundle.putParcelable("msgr_PluginMsgHandler", c());
    paramBundle.putParcelable("msgr_ReqAccResultReceiver", paramContext);
    com.dsi.ant.plugins.a.a.a.a("BBD30100");
    for (;;)
    {
      try
      {
        com.dsi.ant.plugins.a.a.a.a(this.O.createPackageContext("com.dsi.ant.plugins.antplus", 4));
        paramBundle.putInt("int_PluginLibVersion", 30100);
        paramBundle.putString("string_PluginLibVersion", "3.1.0");
        paramBundle.putInt("more", 0);
        paramHandler = a();
        Iterator localIterator = this.O.getPackageManager().getInstalledPackages(0).iterator();
        if (localIterator.hasNext())
        {
          paramContext = (PackageInfo)localIterator.next();
          if (!paramContext.packageName.equals(paramHandler.getComponent().getPackageName())) {
            continue;
          }
          if (paramContext == null)
          {
            com.dsi.ant.plugins.a.a.a.a(a, "Binding to plugin failed, not installed");
            a((Messenger)paramBundle.getParcelable("msgr_ReqAccResultReceiver"), paramHandler.getComponent().getPackageName(), "ANT+ Plugins Service");
            return;
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        com.dsi.ant.plugins.a.a.a.a(a, "Unable to configure logging, plugins package not found: " + paramContext);
        continue;
        if (paramContext.versionCode < b())
        {
          com.dsi.ant.plugins.a.a.a.a(a, "Binding to plugin failed, version requirement not met");
          a((Messenger)paramBundle.getParcelable("msgr_ReqAccResultReceiver"), paramHandler.getComponent().getPackageName(), "ANT+ Plugins Service minimum v." + b());
          return;
        }
        this.C = new b(this, paramBundle);
        a(paramHandler, paramBundle);
        return;
      }
      paramContext = null;
    }
  }
  
  protected abstract void a(Message paramMessage);
  
  void a(AntPluginDeviceDbProvider.DeviceDbDeviceInfo paramDeviceDbDeviceInfo, UUID paramUUID, Messenger paramMessenger, int paramInt1, int paramInt2)
  {
    this.P = paramDeviceDbDeviceInfo;
    this.I = paramUUID;
    this.J = paramMessenger;
    this.R = paramInt2;
    if (this.Q == null) {
      this.Q = Integer.valueOf(paramInt1);
    }
    this.M = true;
  }
  
  void a(String paramString)
  {
    com.dsi.ant.plugins.a.a.a.b(a, "ConnectionDied: " + paramString);
    if ((this.T == null) || (this.T.c)) {
      return;
    }
    f();
    this.S.a(com.dsi.ant.plugins.antplus.pcc.a.b.a);
  }
  
  protected boolean a(int paramInt)
  {
    Message localMessage = a(10000, null);
    localMessage.arg1 = paramInt;
    localMessage = c(localMessage);
    if (localMessage == null)
    {
      com.dsi.ant.plugins.a.a.a.a(a, "subscribeToEvent died in sendPluginCommand()");
      return false;
    }
    if (localMessage.arg1 != 0)
    {
      com.dsi.ant.plugins.a.a.a.a(a, "Subscribing to event " + paramInt + " failed with code " + localMessage.arg1);
      localMessage.recycle();
      return false;
    }
    localMessage.recycle();
    return true;
  }
  
  protected abstract int b();
  
  protected void b(int paramInt)
  {
    Message localMessage1 = a(10001, null);
    localMessage1.arg1 = paramInt;
    Message localMessage2 = c(localMessage1);
    if (localMessage2 == null)
    {
      com.dsi.ant.plugins.a.a.a.a(a, "unsubscribeFromEvent died in sendPluginCommand()");
      return;
    }
    if (localMessage2.arg1 != 0) {
      throw new RuntimeException("Unsubscribing to event " + paramInt + " failed with code " + localMessage1.arg1);
    }
    localMessage2.recycle();
  }
  
  protected void b(Message paramMessage)
  {
    Handler localHandler = this.G;
    if (localHandler != null)
    {
      Message localMessage = localHandler.obtainMessage(paramMessage.what, paramMessage.arg1, paramMessage.arg2, paramMessage.obj);
      localMessage.setData(paramMessage.getData());
      localMessage.replyTo = paramMessage.replyTo;
      localHandler.sendMessage(localMessage);
    }
  }
  
  protected Message c(Message paramMessage)
  {
    Bundle localBundle2 = paramMessage.getData();
    Bundle localBundle1 = localBundle2;
    if (localBundle2 == null)
    {
      localBundle1 = new Bundle();
      paramMessage.setData(localBundle1);
    }
    localBundle1.putSerializable("uuid_AccessToken", this.I);
    return d(paramMessage);
  }
  
  public void d()
  {
    this.T.c();
  }
  
  void e()
  {
    c(10101);
  }
  
  void f()
  {
    synchronized (this.b)
    {
      this.Q = Integer.valueOf(-100);
      try
      {
        c(10002);
        g();
        return;
      }
      finally
      {
        localObject1 = finally;
        g();
        throw localObject1;
      }
    }
  }
  
  void g()
  {
    if (this.d) {
      return;
    }
    this.d = true;
    this.D.quit();
    try
    {
      this.D.join(1000L);
      this.G = null;
      this.F.quit();
    }
    catch (InterruptedException localInterruptedException1)
    {
      try
      {
        this.F.join(1000L);
        i();
        if (this.b.tryLock())
        {
          this.b.unlock();
          synchronized (this.b)
          {
            this.J = null;
            return;
          }
          localInterruptedException1 = localInterruptedException1;
          com.dsi.ant.plugins.a.a.a.a(a, "Plugin Msg Handler thread failed to shut down cleanly, InterruptedException");
          Thread.currentThread().interrupt();
        }
      }
      catch (InterruptedException localInterruptedException2)
      {
        for (;;)
        {
          com.dsi.ant.plugins.a.a.a.a(a, "Plugin Event Handler thread failed to shut down cleanly, InterruptedException");
          Thread.currentThread().interrupt();
          continue;
          this.c.interrupt();
        }
      }
    }
  }
}
