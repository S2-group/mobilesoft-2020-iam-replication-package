package com.airwatch.agent.appwrapper;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ClipboardManager.OnPrimaryClipChangedListener;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.IBinder;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

public class AppWrapperService
  extends Service
{
  ClipboardManager.OnPrimaryClipChangedListener a;
  private Handler b = null;
  private AppWrapperManager c;
  private g d = new g();
  private int e = 100;
  private boolean f = false;
  private ClipboardManager g;
  private ClipData h;
  private String i = null;
  private String j = "";
  private final Runnable k = new i(this);
  private final BroadcastReceiver l = new j(this);
  private final BroadcastReceiver m = new k(this);
  
  public AppWrapperService()
  {
    if (g()) {
      this.a = new l(this);
    }
  }
  
  private void a()
  {
    com.airwatch.util.k.b("AppWrapperService initialize handler");
    if (this.b == null) {
      this.b = new Handler();
    }
    if (g())
    {
      this.g = ((ClipboardManager)getSystemService("clipboard"));
      this.h = this.g.getPrimaryClip();
      this.g.addPrimaryClipChangedListener(this.a);
    }
  }
  
  private boolean a(int paramInt)
  {
    return paramInt != 1024;
  }
  
  private boolean b()
  {
    if (this.c.c()) {}
    for (;;)
    {
      return false;
      if ((this.c.a(d())) || (getPackageName().equals(d())))
      {
        if (!getPackageName().equalsIgnoreCase(d())) {}
        try
        {
          this.c.b(d());
          return true;
        }
        catch (Exception localException1)
        {
          for (;;)
          {
            com.airwatch.util.k.d("Exception occurred while updating application state:", localException1);
          }
        }
      }
      if ((this.i != null) && (d().equalsIgnoreCase(this.i))) {
        try
        {
          boolean bool = this.c.a(AppWrapperManager.RestrictionType.d);
          if (bool) {
            return true;
          }
        }
        catch (Exception localException2)
        {
          com.airwatch.util.k.d("Exception while getting camera restriction:", localException2);
        }
      }
    }
    return false;
  }
  
  private boolean c()
  {
    return getPackageName().equalsIgnoreCase(d());
  }
  
  private String d()
  {
    return ((ActivityManager.RunningTaskInfo)((ActivityManager)getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.getPackageName();
  }
  
  private void e()
  {
    try
    {
      Iterator localIterator = getPackageManager().getInstalledApplications(128).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        String str = localApplicationInfo.loadLabel(getPackageManager()).toString();
        if ((str != null) && ("camera".equalsIgnoreCase(str))) {
          this.i = localApplicationInfo.processName;
        }
      }
      return;
    }
    catch (Exception localException)
    {
      com.airwatch.util.k.d("Exception occurred while getting camera application:", localException);
    }
  }
  
  private void f()
  {
    if (!g()) {
      return;
    }
    ClipData localClipData = ClipData.newPlainText("Restricted", " ");
    this.g.setPrimaryClip(localClipData);
  }
  
  private boolean g()
  {
    return Build.VERSION.SDK_INT >= 11;
  }
  
  private void h()
  {
    Object localObject = (WifiManager)getApplicationContext().getSystemService("wifi");
    if (localObject == null) {}
    int n;
    do
    {
      return;
      n = ((WifiManager)localObject).getWifiState();
      localObject = ((WifiManager)localObject).getConnectionInfo().getSSID();
    } while ((n == 0) || (n == 1) || (n == 4));
    m.a(this.j, (String)localObject);
  }
  
  void a(BluetoothDevice paramBluetoothDevice, int paramInt)
  {
    if (a(paramBluetoothDevice.getBluetoothClass().getMajorDeviceClass()))
    {
      com.airwatch.util.k.b("Restricted Device ");
      switch (paramInt)
      {
      }
    }
    for (;;)
    {
      return;
      try
      {
        if (((Boolean)paramBluetoothDevice.getClass().getMethod("cancelBondProcess", new Class[0]).invoke(paramBluetoothDevice, new Object[0])).booleanValue()) {
          continue;
        }
        com.airwatch.util.k.b("BOND_BONDING failed canceling pairing bluetooth turning bluetooth off.. ");
        m.a();
        return;
      }
      catch (Exception paramBluetoothDevice)
      {
        com.airwatch.util.k.b("Exception in reflection , disablling bluetooth " + paramBluetoothDevice.getMessage());
        m.a();
        return;
      }
      try
      {
        if (!((Boolean)paramBluetoothDevice.getClass().getMethod("removeBond", new Class[0]).invoke(paramBluetoothDevice, new Object[0])).booleanValue())
        {
          com.airwatch.util.k.b("BOND_BONDED failed canceling pairing bluetooth turning bluetooth off..");
          m.a();
          return;
        }
      }
      catch (Exception paramBluetoothDevice)
      {
        com.airwatch.util.k.b("Exception , disablling bluetooth " + paramBluetoothDevice.getMessage());
        m.a();
      }
    }
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    a();
    IntentFilter localIntentFilter1 = new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED");
    localIntentFilter1.addAction("android.bluetooth.device.action.ACL_CONNECTED");
    localIntentFilter1.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
    this.d.a(m.e());
    IntentFilter localIntentFilter2 = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    e();
    registerReceiver(this.m, localIntentFilter1);
    registerReceiver(this.l, localIntentFilter2);
    this.c = AppWrapperManager.a();
    this.c.e();
  }
  
  public void onDestroy()
  {
    com.airwatch.util.k.f("AppWrapperServcie onDestroy");
    super.onDestroy();
    unregisterReceiver(this.m);
    unregisterReceiver(this.l);
    this.b.removeCallbacks(this.k);
    this.c.b();
    if (g())
    {
      this.g.removePrimaryClipChangedListener(this.a);
      if ((this.h != null) && (this.f)) {
        this.g.setPrimaryClip(this.h);
      }
    }
    com.airwatch.util.k.g("AppWrapperServcie onDestroy");
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (paramIntent == null) {
      return super.onStartCommand(paramIntent, paramInt1, paramInt2);
    }
    String str = paramIntent.getStringExtra("processId");
    if (str != null) {
      this.c.a(getApplicationContext(), str);
    }
    this.j = str;
    h();
    if ((g()) && (this.c.a(AppWrapperManager.RestrictionType.e))) {
      f();
    }
    this.b.post(this.k);
    return super.onStartCommand(paramIntent, paramInt1, paramInt2);
  }
}
