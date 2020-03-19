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
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.dsi.ant.plugins.antplus.pcc.a.f;
import com.dsi.ant.plugins.internal.pluginsipc.AntPluginDeviceDbProvider.DeviceDbDeviceInfo;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;

public abstract class a
{
  private static final String a = a.class.getSimpleName();
  private static String b = "";
  private static String c = "";
  ServiceConnection C;
  HandlerThread D = new HandlerThread("PluginPCCMsgHandler");
  Handler.Callback E = new Handler.Callback()
  {
    public boolean handleMessage(Message paramAnonymousMessage)
    {
      com.dsi.ant.plugins.a.a.a.e(a.j(), "Plugin Msg Handler received: " + paramAnonymousMessage.what + ", " + paramAnonymousMessage.arg1);
      if (a.a(a.this).tryLock()) {
        try
        {
          a.this.b(paramAnonymousMessage);
          return true;
        }
        finally
        {
          a.a(a.this).unlock();
        }
      }
      try
      {
        a.this.H.exchange(paramAnonymousMessage);
        a.this.I.await();
        return true;
      }
      catch (BrokenBarrierException localBrokenBarrierException)
      {
        a.a(a.this, "BrokenBarrierException in mPluginMsgHandler trying to fwd message " + paramAnonymousMessage.what);
        return true;
      }
      catch (InterruptedException localInterruptedException)
      {
        a.a(a.this, "InterruptedException in mPluginMsgHandler trying to fwd message " + paramAnonymousMessage.what);
        Thread.currentThread().interrupt();
      }
      return true;
    }
  };
  UUID F;
  Messenger G;
  Exchanger H = new Exchanger();
  CyclicBarrier I = new CyclicBarrier(2);
  boolean J = false;
  CountDownLatch K = new CountDownLatch(1);
  Context L;
  AntPluginDeviceDbProvider.DeviceDbDeviceInfo M;
  Integer N = null;
  protected int O;
  b P;
  ExecutorService Q;
  private final ReentrantLock d = new ReentrantLock();
  private Thread e;
  private boolean f = false;
  private final Object g = new Object();
  
  public a() {}
  
  public static int a(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    PackageInfo localPackageInfo;
    do
    {
      if (!paramContext.hasNext()) {
        return -1;
      }
      localPackageInfo = (PackageInfo)paramContext.next();
    } while (!localPackageInfo.packageName.equals("com.dsi.ant.plugins.antplus"));
    return localPackageInfo.versionCode;
  }
  
  protected static AsyncScanController a(Context paramContext, int paramInt, a paramA, s paramS)
  {
    if (a(paramContext) < 10800)
    {
      com.dsi.ant.plugins.a.a.a.a(a, "Binding to plugin failed, version requirement not met for async scan controller mode");
      b = paramA.a().getComponent().getPackageName();
      c = paramA.b() + " minimum v.10800";
      paramS.onSearchStopped(f.e);
      return null;
    }
    if ((paramInt > 10) || (paramInt < 0)) {
      throw new IllegalArgumentException("searchProximityThreshold was out of range 0 to 10: " + paramInt);
    }
    if (paramS == null) {
      throw new NullPointerException("ScanResultReceiver passed  from client was null");
    }
    Bundle localBundle = new Bundle();
    localBundle.putInt("int_RequestAccessMode", 2);
    localBundle.putInt("int_ProximityBin", paramInt);
    paramS = new AsyncScanController(paramS, paramA);
    a(paramContext, localBundle, paramA, paramS.c());
    return paramS;
  }
  
  protected static void a(Context paramContext, int paramInt1, int paramInt2, a paramA, c paramC, b paramB)
  {
    if ((paramInt2 > 10) || (paramInt2 < 0)) {
      throw new IllegalArgumentException("searchProximityThreshold was out of range 0 to 10: " + paramInt2);
    }
    Bundle localBundle = new Bundle();
    localBundle.putInt("int_RequestAccessMode", 3);
    localBundle.putInt("int_AntDeviceID", paramInt1);
    localBundle.putInt("int_ProximityBin", paramInt2);
    a(paramContext, localBundle, paramA, new e(), paramC, paramB);
  }
  
  protected static void a(Context paramContext, Bundle paramBundle, a paramA, Handler paramHandler)
  {
    String str = paramContext.getPackageName();
    Object localObject = paramContext.getApplicationInfo();
    localObject = paramContext.getPackageManager().getApplicationLabel((ApplicationInfo)localObject).toString();
    paramBundle.putString("str_ApplicationNamePackage", str);
    paramBundle.putString("str_ApplicationNameTitle", (String)localObject);
    paramA.L = paramContext;
    paramContext = new Messenger(paramHandler);
    paramBundle.putParcelable("msgr_PluginMsgHandler", paramA.d());
    paramBundle.putParcelable("msgr_ReqAccResultReceiver", paramContext);
    paramA.a(paramBundle);
  }
  
  protected static void a(Context paramContext, Bundle paramBundle, a paramA, d paramD, c paramC, b paramB)
  {
    paramA.P = paramB;
    paramD.a(paramA, paramC);
    a(paramContext, paramBundle, paramA, paramD);
  }
  
  private void a(final Bundle paramBundle)
  {
    com.dsi.ant.plugins.a.a.a.a("BBD20000");
    try
    {
      com.dsi.ant.plugins.a.a.a.a(this.L.createPackageContext("com.dsi.ant.plugins.antplus", 4));
      paramBundle.putInt("int_PluginLibVersion", 20000);
      paramBundle.putString("string_PluginLibVersion", "2.0.0");
      localIntent = a();
      localIterator = this.L.getPackageManager().getInstalledPackages(0).iterator();
      if (!localIterator.hasNext())
      {
        Object localObject = null;
        if (localObject != null) {
          break label172;
        }
        com.dsi.ant.plugins.a.a.a.a(a, "Binding to plugin failed, not installed");
        a((Messenger)paramBundle.getParcelable("msgr_ReqAccResultReceiver"), localIntent.getComponent().getPackageName(), b());
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Intent localIntent;
      label172:
      do
      {
        PackageInfo localPackageInfo;
        for (;;)
        {
          Iterator localIterator;
          com.dsi.ant.plugins.a.a.a.a(a, "Unable to configure logging, plugins package not found: " + localNameNotFoundException);
          continue;
          localPackageInfo = (PackageInfo)localIterator.next();
          if (!localPackageInfo.packageName.equals(localIntent.getComponent().getPackageName())) {}
        }
        if (localPackageInfo.versionCode < c())
        {
          com.dsi.ant.plugins.a.a.a.a(a, "Binding to plugin failed, version requirement not met");
          a((Messenger)paramBundle.getParcelable("msgr_ReqAccResultReceiver"), localIntent.getComponent().getPackageName(), b() + " minimum v." + c());
          return;
        }
        this.C = new ServiceConnection()
        {
          public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
          {
            paramAnonymousComponentName = new Messenger(paramAnonymousIBinder);
            paramAnonymousIBinder = Message.obtain();
            paramAnonymousIBinder.what = 0;
            paramAnonymousIBinder.setData(paramBundle);
            try
            {
              paramAnonymousComponentName.send(paramAnonymousIBinder);
              return;
            }
            catch (RemoteException paramAnonymousComponentName)
            {
              a.a(a.this, paramBundle);
            }
          }
          
          public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
          {
            a.a(a.this, "OnServiceDisconnected fired");
            if (!a.this.J) {
              a.a(a.this, paramBundle);
            }
          }
        };
      } while (this.L.bindService(localIntent, this.C, 1));
      com.dsi.ant.plugins.a.a.a.a(a, "Binding to plugin failed");
      b(paramBundle);
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
      com.dsi.ant.plugins.a.a.a.a(a, "Remote exception sending plugin 'dependency not installed' msg to client");
      i();
    }
  }
  
  private void b(Bundle paramBundle)
  {
    i();
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
  
  private void c(int paramInt)
  {
    this.P.onDeviceStateChange(com.dsi.ant.plugins.antplus.pcc.a.d.a(paramInt));
    if (paramInt == -100) {
      i();
    }
  }
  
  private void c(String arg1)
  {
    com.dsi.ant.plugins.a.a.a.a(a, "ConnectionDied: " + ???);
    Message localMessage;
    synchronized (this.g)
    {
      if (this.f) {
        return;
      }
      this.N = Integer.valueOf(-100);
      if (this.J)
      {
        localMessage = Message.obtain();
        Bundle localBundle = new Bundle();
        localBundle.putSerializable("uuid_AccessToken", this.F);
        localMessage.what = 10002;
        localMessage.setData(localBundle);
      }
    }
    try
    {
      this.G.send(localMessage);
      this.P.onDeviceStateChange(com.dsi.ant.plugins.antplus.pcc.a.d.a);
      i();
      this.f = true;
      return;
      localObject = finally;
      throw localObject;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.dsi.ant.plugins.a.a.a.a(a, "Unable to cleanly release access");
      }
    }
  }
  
  private Messenger d()
  {
    this.D.start();
    return new Messenger(new Handler(this.D.getLooper(), this.E));
  }
  
  public static String e()
  {
    return b;
  }
  
  public static String f()
  {
    return c;
  }
  
  protected abstract Intent a();
  
  protected abstract void a(Message paramMessage);
  
  void a(AntPluginDeviceDbProvider.DeviceDbDeviceInfo paramDeviceDbDeviceInfo, UUID paramUUID, Messenger paramMessenger, int paramInt1, int paramInt2)
  {
    this.M = paramDeviceDbDeviceInfo;
    this.F = paramUUID;
    this.G = paramMessenger;
    this.O = paramInt2;
    if (this.N == null) {
      this.N = Integer.valueOf(paramInt1);
    }
    this.J = true;
  }
  
  protected boolean a(int paramInt)
  {
    Message localMessage = Message.obtain();
    localMessage.what = 10000;
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
  
  protected abstract String b();
  
  protected void b(int paramInt)
  {
    Message localMessage1 = Message.obtain();
    localMessage1.what = 10001;
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
    switch (paramMessage.what)
    {
    case 2: 
    default: 
      com.dsi.ant.plugins.a.a.a.b(a, "Unrecognized plugin event received: " + paramMessage.arg1);
      return;
    case 1: 
      a(paramMessage);
      return;
    }
    final int i = paramMessage.arg1;
    this.N = Integer.valueOf(i);
    com.dsi.ant.plugins.a.a.a.e(a, "State event: " + i);
    if (!this.J)
    {
      com.dsi.ant.plugins.a.a.a.e(a, "Queueing state event: " + i);
      if (this.Q == null) {
        this.Q = Executors.newSingleThreadExecutor();
      }
      paramMessage = new Runnable()
      {
        public void run()
        {
          try
          {
            a.this.K.await();
            com.dsi.ant.plugins.a.a.a.e(a.j(), "Sending queued state event: " + i);
            a.a(a.this, i);
            return;
          }
          catch (InterruptedException localInterruptedException)
          {
            if (i == -100) {
              a.a(a.this, "InterruptedException waiting on deviceInitializedLatch in queued status update");
            }
            Thread.currentThread().interrupt();
          }
        }
      };
      this.Q.execute(paramMessage);
      return;
    }
    com.dsi.ant.plugins.a.a.a.e(a, "Sending state event: " + i);
    c(i);
  }
  
  protected abstract int c();
  
  protected Message c(Message paramMessage)
  {
    synchronized (this.d)
    {
      this.e = Thread.currentThread();
      Bundle localBundle2 = paramMessage.getData();
      Bundle localBundle1 = localBundle2;
      if (localBundle2 == null) {
        localBundle1 = new Bundle();
      }
      localBundle1.putSerializable("uuid_AccessToken", this.F);
      paramMessage.setData(localBundle1);
      try
      {
        if (!this.d.tryLock(7000L, TimeUnit.MILLISECONDS)) {
          throw new TimeoutException();
        }
      }
      catch (InterruptedException localInterruptedException1)
      {
        c("InterruptedException obtaining mPluginCommLock in sendPluginCommand on message " + paramMessage.what);
        Thread.currentThread().interrupt();
        return null;
      }
      catch (TimeoutException localTimeoutException1)
      {
        c("TimeoutException obtaining mPluginCommLock in sendPluginCommand on message " + paramMessage.what);
        return null;
      }
    }
    for (;;)
    {
      try
      {
        this.G.send(paramMessage);
        try
        {
          localMessage = (Message)this.H.exchange(null, 5L, TimeUnit.SECONDS);
          if (localMessage.what != paramMessage.what) {
            continue;
          }
          localMessage = Message.obtain(localMessage);
        }
        catch (InterruptedException localInterruptedException2)
        {
          Message localMessage;
          c("InterruptedException in sendPluginCommand (at mPluginCommMsgExch.exchange()) on message " + paramMessage.what);
          Thread.currentThread().interrupt();
          this.d.unlock();
          return null;
        }
        catch (TimeoutException localTimeoutException2)
        {
          c("TimeoutException in sendPluginCommand (at mPluginCommMsgExch.exchange()) on message " + paramMessage.what);
          this.d.unlock();
          return null;
        }
        try
        {
          this.I.await();
          return localMessage;
        }
        catch (BrokenBarrierException localBrokenBarrierException1)
        {
          c("BrokenBarrierException in sendPluginCommand finally on message " + paramMessage.what);
          return null;
        }
        catch (InterruptedException localInterruptedException3)
        {
          c("InterruptedException in sendPluginCommand finally on message " + paramMessage.what);
          Thread.currentThread().interrupt();
          return null;
        }
        paramMessage = finally;
        throw paramMessage;
      }
      catch (RemoteException localRemoteException)
      {
        c("RemoteException sending message " + paramMessage.what + " to plugin");
        this.d.unlock();
        return null;
      }
      finally
      {
        this.d.unlock();
      }
      b(localInterruptedException3);
      try
      {
        this.I.await();
      }
      catch (BrokenBarrierException localBrokenBarrierException2)
      {
        c("BrokenBarrierException in sendPluginCommand (at non-success mPluginCommProcessingBarrier) on message " + paramMessage.what);
        this.d.unlock();
        return null;
      }
      catch (InterruptedException localInterruptedException4)
      {
        c("InterruptedException in sendPluginCommand (at non-success mPluginCommProcessingBarrier) on message " + paramMessage.what);
        Thread.currentThread().interrupt();
        this.d.unlock();
        return null;
      }
    }
  }
  
  void g()
  {
    Message localMessage1 = Message.obtain();
    localMessage1.what = 10101;
    Message localMessage2 = c(localMessage1);
    if (localMessage2 == null)
    {
      com.dsi.ant.plugins.a.a.a.a(a, "connectToAsyncResult died in sendPluginCommand()");
      return;
    }
    if (localMessage2.arg1 != 0) {
      throw new RuntimeException("Request to stopAsyncScan Result failed with code " + localMessage1.arg1);
    }
    localMessage2.recycle();
    i();
  }
  
  public void h()
  {
    try
    {
      Message localMessage1 = Message.obtain();
      localMessage1.what = 10002;
      Message localMessage2 = c(localMessage1);
      if (localMessage2 == null)
      {
        com.dsi.ant.plugins.a.a.a.a(a, "ReleaseAccess died in sendPluginCommand()");
        return;
      }
      if (localMessage2.arg1 != 0) {
        com.dsi.ant.plugins.a.a.a.a(a, "ReleaseAccess failed unexpectedly with code " + localMessage1.arg1);
      }
      localMessage2.recycle();
      return;
    }
    finally
    {
      i();
    }
  }
  
  void i()
  {
    this.D.quit();
    try
    {
      this.D.join(1000L);
      if (this.C == null) {}
    }
    catch (InterruptedException localInterruptedException)
    {
      try
      {
        this.L.unbindService(this.C);
        this.C = null;
        if (this.Q != null) {
          this.Q.shutdownNow();
        }
        if (this.d.tryLock())
        {
          this.d.unlock();
          return;
          localInterruptedException = localInterruptedException;
          com.dsi.ant.plugins.a.a.a.a(a, "Plugin Msg Handler thread failed to shut down cleanly, InterruptedException");
          Thread.currentThread().interrupt();
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        for (;;)
        {
          com.dsi.ant.plugins.a.a.a.a(a, "Unexpected error unbinding service, " + localIllegalArgumentException);
        }
        this.e.interrupt();
      }
    }
  }
}
