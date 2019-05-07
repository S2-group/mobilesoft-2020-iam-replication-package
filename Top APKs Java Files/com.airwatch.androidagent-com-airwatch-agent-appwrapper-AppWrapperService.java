package com.airwatch.agent.appwrapper;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
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
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import com.airwatch.agent.AirWatchApp;
import com.airwatch.agent.appwrapper.data.AppWrapperContentProvider;
import com.airwatch.util.n;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Hashtable;
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
    if (e()) {
      this.a = new l(this);
    }
  }
  
  static void a(BluetoothDevice paramBluetoothDevice, int paramInt)
  {
    int n = 0;
    if (paramBluetoothDevice.getBluetoothClass().getMajorDeviceClass() != 1024) {
      n = 1;
    }
    if (n != 0)
    {
      n.b("Restricted Device ");
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
        n.b("BOND_BONDING failed canceling pairing bluetooth turning bluetooth off.. ");
        m.a();
        return;
      }
      catch (Exception paramBluetoothDevice)
      {
        n.b("Exception in reflection , disablling bluetooth " + paramBluetoothDevice.getMessage());
        m.a();
        return;
      }
      try
      {
        if (!((Boolean)paramBluetoothDevice.getClass().getMethod("removeBond", new Class[0]).invoke(paramBluetoothDevice, new Object[0])).booleanValue())
        {
          n.b("BOND_BONDED failed canceling pairing bluetooth turning bluetooth off..");
          m.a();
          return;
        }
      }
      catch (Exception paramBluetoothDevice)
      {
        n.b("Exception , disablling bluetooth " + paramBluetoothDevice.getMessage());
        m.a();
      }
    }
  }
  
  private boolean a()
  {
    if (this.c.a.isEmpty()) {}
    for (;;)
    {
      return false;
      AppWrapperManager localAppWrapperManager = this.c;
      String str = b();
      if ((localAppWrapperManager.a.containsKey(str)) || (getPackageName().equals(b())))
      {
        if (!getPackageName().equalsIgnoreCase(b())) {}
        try
        {
          this.c.a(b());
          return true;
        }
        catch (Exception localException1)
        {
          for (;;)
          {
            n.d("Exception:" + localException1.getMessage());
          }
        }
      }
      if ((this.i != null) && (b().equalsIgnoreCase(this.i))) {
        try
        {
          boolean bool = this.c.a(AppWrapperManager.RestrictionType.d);
          if (bool) {
            return true;
          }
        }
        catch (Exception localException2) {}
      }
    }
    return false;
  }
  
  private String b()
  {
    return ((ActivityManager.RunningTaskInfo)((ActivityManager)getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.getPackageName();
  }
  
  private void c()
  {
    try
    {
      Iterator localIterator = getPackageManager().getInstalledApplications(128).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        String str = localApplicationInfo.loadLabel(getPackageManager()).toString();
        if ((str != null) && (str.equalsIgnoreCase("camera"))) {
          this.i = localApplicationInfo.processName;
        }
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  private void d()
  {
    if (!e()) {
      return;
    }
    ClipData localClipData = ClipData.newPlainText("Restricted", " ");
    this.g.setPrimaryClip(localClipData);
  }
  
  private static boolean e()
  {
    return Build.VERSION.SDK_INT >= 11;
  }
  
  private void f()
  {
    Object localObject1 = (WifiManager)getApplicationContext().getSystemService("wifi");
    if (localObject1 == null) {}
    int n;
    Object localObject2;
    label107:
    do
    {
      do
      {
        return;
        n = ((WifiManager)localObject1).getWifiState();
        localObject1 = ((WifiManager)localObject1).getConnectionInfo().getSSID();
      } while ((n == 0) || (n == 1) || (n == 4));
      localObject2 = this.j;
      localObject3 = (ConnectivityManager)AirWatchApp.f().getSystemService("connectivity");
      if (localObject3 == null) {
        break;
      }
      localObject3 = ((ConnectivityManager)localObject3).getActiveNetworkInfo();
      if ((localObject3 == null) || (localObject3 == null) || (!((NetworkInfo)localObject3).isConnected()) || (((NetworkInfo)localObject3).getType() != 1)) {
        break;
      }
      n = 1;
    } while (n == 0);
    String str2 = m.a(AppWrapperContentProvider.a, "allowedSSIDs", "packageId", (String)localObject2);
    Object localObject3 = Arrays.asList(str2.split(","));
    String str1 = "\"" + str2 + '"';
    Log.i("AirWatch", String.format("Allowed sites from DB : %s for the app : %s ", new Object[] { str2, localObject2 }));
    Log.i("AirWatch", String.format("Checking against SSID : %s for the app : %s", new Object[] { localObject1, localObject2 }));
    if ((str2 != null) && (str2.length() > 0)) {
      if ((((String)localObject1).endsWith("\"")) && (((String)localObject1).startsWith("\"")))
      {
        localObject1 = ((String)localObject1).substring(1, ((String)localObject1).length() - 1);
        label263:
        if ((!((List)localObject3).contains(localObject1)) && (!str1.toLowerCase().equals(((String)localObject1).toLowerCase()))) {
          break label409;
        }
      }
    }
    for (boolean bool = true;; bool = true)
    {
      Log.i("AirWatch", String.format("Allow Wifi : %s  for the app : %s ", new Object[] { Boolean.valueOf(bool), localObject2 }));
      if (bool) {
        break;
      }
      localObject1 = new g();
      localObject2 = (WifiManager)AirWatchApp.f().getSystemService("wifi");
      if (localObject2 == null) {
        break;
      }
      ((WifiManager)localObject2).removeNetwork(m.b());
      try
      {
        Toast.makeText(AirWatchApp.f(), AirWatchApp.f().getResources().getString(2131689584), 0).show();
        n = ((g)localObject1).a();
        if (n == -1) {
          break;
        }
        ((WifiManager)localObject2).enableNetwork(n, true);
        return;
        n = 0;
        break label107;
        break label263;
        label409:
        bool = false;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Log.i("AirWatch", String.format("Exception : %s", new Object[] { localException.getMessage() }));
        }
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
    n.b("AppWrapperService initialize handler");
    if (this.b == null) {
      this.b = new Handler();
    }
    if (e())
    {
      this.g = ((ClipboardManager)getSystemService("clipboard"));
      this.h = this.g.getPrimaryClip();
      this.g.addPrimaryClipChangedListener(this.a);
    }
    Object localObject1 = new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED");
    ((IntentFilter)localObject1).addAction("android.bluetooth.device.action.ACL_CONNECTED");
    ((IntentFilter)localObject1).addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
    this.d.a(m.b());
    Object localObject2 = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    c();
    registerReceiver(this.m, (IntentFilter)localObject1);
    registerReceiver(this.l, (IntentFilter)localObject2);
    this.c = AppWrapperManager.a();
    localObject1 = this.c;
    localObject2 = BluetoothAdapter.getDefaultAdapter();
    if (localObject2 != null) {
      ((AppWrapperManager)localObject1).c.put(AppWrapperManager.RestrictionType.b, Boolean.valueOf(((BluetoothAdapter)localObject2).isEnabled()));
    }
    ((AppWrapperManager)localObject1).c.put(AppWrapperManager.RestrictionType.d, Boolean.valueOf(m.c()));
  }
  
  public void onDestroy()
  {
    n.f("AppWrapperServcie onDestroy");
    super.onDestroy();
    unregisterReceiver(this.m);
    unregisterReceiver(this.l);
    this.b.removeCallbacks(this.k);
    this.c.b();
    if (e())
    {
      this.g.removePrimaryClipChangedListener(this.a);
      if ((this.h != null) && (this.f)) {
        this.g.setPrimaryClip(this.h);
      }
    }
    n.g("AppWrapperServcie onDestroy");
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
    f();
    if ((e()) && (this.c.a(AppWrapperManager.RestrictionType.e))) {
      d();
    }
    this.b.post(this.k);
    return super.onStartCommand(paramIntent, paramInt1, paramInt2);
  }
}
