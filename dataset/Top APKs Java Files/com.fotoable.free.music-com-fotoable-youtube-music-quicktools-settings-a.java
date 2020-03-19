package com.fotoable.youtube.music.quicktools.settings;

import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.hardware.camera2.CameraManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.provider.Settings.System;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.List;

public class a
{
  private static final String a = a.class.getSimpleName();
  private static String b = "WRITE_SETTINGS permission not granted!";
  private static String c = "CAMERA permission not granted!";
  private static String d = "The flash is not supported!";
  private static String e = "Calculator is not found!";
  private static String f = "Open calculator failed!";
  private a g;
  private WifiManager h;
  private LocationManager i;
  private ConnectivityManager j;
  private AudioManager k;
  private BluetoothAdapter l;
  private ContentResolver m;
  private boolean n = false;
  private CameraManager o;
  private boolean p = false;
  
  public a(a paramA)
  {
    this.g = paramA;
  }
  
  private boolean A(Context paramContext)
  {
    return paramContext.getPackageManager().hasSystemFeature("android.hardware.camera.flash");
  }
  
  private CameraManager B(Context paramContext)
  {
    if ((this.o == null) && (Build.VERSION.SDK_INT >= 21)) {
      this.o = ((CameraManager)paramContext.getApplicationContext().getSystemService("camera"));
    }
    return this.o;
  }
  
  private PackageInfo a(Context paramContext, String paramString1, String paramString2)
  {
    int i3 = 0;
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i1 = 0;
    int i2;
    PackageInfo localPackageInfo;
    for (;;)
    {
      i2 = i3;
      if (i1 >= paramContext.size()) {
        break;
      }
      localPackageInfo = (PackageInfo)paramContext.get(i1);
      Log.e(a, "==本机应用===" + localPackageInfo.packageName);
      i1 += 1;
    }
    do
    {
      i2 += 1;
      if (i2 >= paramContext.size()) {
        break;
      }
      localPackageInfo = (PackageInfo)paramContext.get(i2);
    } while ((!localPackageInfo.packageName.contains("android")) || ((!localPackageInfo.packageName.contains(paramString1)) && (!localPackageInfo.packageName.contains(paramString2))));
    return localPackageInfo;
    return null;
  }
  
  private void a(String paramString)
  {
    if (this.g != null) {
      this.g.a(paramString);
    }
  }
  
  private boolean a(Context paramContext, String paramString)
  {
    boolean bool = false;
    try
    {
      paramString = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
      if (paramString != null)
      {
        paramString.setFlags(268435456);
        paramContext.startActivity(paramString);
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  private BluetoothAdapter e()
  {
    if (this.l == null) {
      this.l = BluetoothAdapter.getDefaultAdapter();
    }
    return this.l;
  }
  
  private WifiManager v(Context paramContext)
  {
    if (this.h == null) {
      this.h = ((WifiManager)paramContext.getApplicationContext().getSystemService("wifi"));
    }
    return this.h;
  }
  
  private LocationManager w(Context paramContext)
  {
    if (this.i == null) {
      this.i = ((LocationManager)paramContext.getApplicationContext().getSystemService("location"));
    }
    return this.i;
  }
  
  private ConnectivityManager x(Context paramContext)
  {
    if (this.j == null) {
      this.j = ((ConnectivityManager)paramContext.getApplicationContext().getSystemService("connectivity"));
    }
    return this.j;
  }
  
  private AudioManager y(Context paramContext)
  {
    if (this.k == null) {
      this.k = ((AudioManager)paramContext.getApplicationContext().getSystemService("audio"));
    }
    return this.k;
  }
  
  private ContentResolver z(Context paramContext)
  {
    if (this.m == null) {
      this.m = paramContext.getApplicationContext().getContentResolver();
    }
    return this.m;
  }
  
  public void a()
  {
    if (Build.VERSION.SDK_INT < 23) {
      try
      {
        if (Build.VERSION.SDK_INT >= 21)
        {
          this.p = false;
          this.n = false;
        }
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
    }
    this.n = false;
  }
  
  public void a(Context paramContext)
  {
    b = paramContext.getResources().getString(2131296491);
    c = paramContext.getResources().getString(2131296490);
    d = paramContext.getResources().getString(2131296489);
    e = paramContext.getResources().getString(2131296488);
    f = paramContext.getResources().getString(2131296487);
  }
  
  public void a(Context paramContext, int paramInt)
  {
    try
    {
      this.m = z(paramContext);
      if ((Build.VERSION.SDK_INT >= 23) && (!Settings.System.canWrite(paramContext)))
      {
        Log.e(a, b);
        return;
      }
      paramContext = Settings.System.getUriFor("screen_brightness");
      Settings.System.putInt(this.m, "screen_brightness", paramInt);
      this.m.notifyChange(paramContext, null);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      Log.e(a, "Screen Brightness unavailable!");
    }
  }
  
  @RequiresApi(api=21)
  public void a(boolean paramBoolean)
  {
    if (!paramBoolean) {}
    try
    {
      a();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void b()
  {
    try
    {
      this.l = e();
      if (c())
      {
        this.l.disable();
        return;
      }
      this.l.enable();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      Log.e(a, "Bluetooth unavailable!");
    }
  }
  
  public void b(Context paramContext, int paramInt)
  {
    try
    {
      this.m = z(paramContext);
      Settings.System.putInt(this.m, "screen_brightness_mode", paramInt);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      Log.e(a, "Screen Brightness unavailable!");
    }
  }
  
  public boolean b(Context paramContext)
  {
    try
    {
      this.h = v(paramContext);
      if (this.h.isWifiEnabled()) {
        return this.h.setWifiEnabled(false);
      }
      boolean bool = this.h.setWifiEnabled(true);
      return bool;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      Log.e(a, "WIFI unavailable!");
    }
    return false;
  }
  
  public void c(Context paramContext, int paramInt)
  {
    try
    {
      this.k = y(paramContext);
      this.k.setStreamVolume(3, paramInt, 0);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      Log.e(a, "Sound Volume unavailable!");
    }
  }
  
  public boolean c()
  {
    boolean bool = false;
    try
    {
      this.l = e();
      int i1 = this.l.getState();
      if (12 == i1) {
        bool = true;
      }
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      Log.e(a, "Bluetooth unavailable!");
    }
    return false;
  }
  
  public boolean c(Context paramContext)
  {
    try
    {
      this.h = v(paramContext);
      boolean bool = this.h.isWifiEnabled();
      return bool;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      Log.e(a, "WIFI unavailable!");
    }
    return false;
  }
  
  public int d()
  {
    return 255;
  }
  
  public void d(Context paramContext)
  {
    try
    {
      Intent localIntent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
      localIntent.addFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public boolean e(Context paramContext)
  {
    try
    {
      this.i = w(paramContext);
      boolean bool = this.i.isProviderEnabled("gps");
      return bool;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      Log.e(a, "GPS unavailable!");
    }
    return false;
  }
  
  public void f(Context paramContext)
  {
    for (boolean bool1 = true;; bool1 = false) {
      try
      {
        if (Build.VERSION.SDK_INT >= 21)
        {
          localObject = new Intent("android.settings.DATA_ROAMING_SETTINGS");
          ((Intent)localObject).setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity"));
          ((Intent)localObject).addFlags(268435456);
          paramContext.startActivity((Intent)localObject);
          return;
        }
        this.j = x(paramContext);
        boolean bool2 = g(paramContext);
        paramContext = this.j.getClass().getMethod("setMobileDataEnabled", new Class[] { Boolean.TYPE });
        paramContext.setAccessible(true);
        Object localObject = this.j;
        if (!bool2)
        {
          paramContext.invoke(localObject, new Object[] { Boolean.valueOf(bool1) });
          return;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        Log.e(a, "Mobile Data unavailable!");
        return;
      }
    }
  }
  
  public boolean g(Context paramContext)
  {
    try
    {
      this.j = x(paramContext);
      boolean bool = ((Boolean)this.j.getClass().getMethod("getMobileDataEnabled", new Class[0]).invoke(this.j, new Object[0])).booleanValue();
      return bool;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      Log.e(a, "Mobile Data unavailable!");
    }
    return false;
  }
  
  public void h(Context paramContext)
  {
    try
    {
      this.k = y(paramContext);
      switch (i(paramContext))
      {
      case 2: 
        this.k.setRingerMode(0);
        return;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      Log.e(a, "Sound Mode unavailable!");
      return;
    }
    this.k.setRingerMode(1);
    return;
    this.k.setRingerMode(2);
    return;
  }
  
  public int i(Context paramContext)
  {
    try
    {
      this.k = y(paramContext);
      int i1 = this.k.getRingerMode();
      return i1;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      Log.e(a, "Sound Mode unavailable!");
    }
    return 0;
  }
  
  public void j(Context paramContext)
  {
    boolean bool1 = true;
    boolean bool2 = true;
    for (;;)
    {
      try
      {
        if (!A(paramContext))
        {
          a(d);
          return;
        }
        if (Build.VERSION.SDK_INT < 23) {
          break;
        }
        this.o = B(paramContext);
        paramContext = this.o;
        if (!this.n)
        {
          bool1 = true;
          paramContext.setTorchMode("0", bool1);
          if (this.n) {
            break label106;
          }
          bool1 = bool2;
          this.n = bool1;
          return;
        }
      }
      catch (Exception paramContext)
      {
        a();
        paramContext.printStackTrace();
        Log.e(a, "Flash Light unavailable!");
        a(c);
        return;
      }
      bool1 = false;
      continue;
      label106:
      bool1 = false;
    }
    if (Build.VERSION.SDK_INT >= 21)
    {
      if (!this.p) {
        k(paramContext);
      }
      if (!this.n) {}
      for (;;)
      {
        this.n = bool1;
        a(this.n);
        return;
        bool1 = false;
      }
    }
  }
  
  @RequiresApi(api=21)
  public void k(Context paramContext)
    throws Exception
  {
    if (ActivityCompat.checkSelfPermission(paramContext, "android.permission.CAMERA") != 0)
    {
      a(c);
      return;
    }
    a(d);
    this.p = true;
  }
  
  public boolean l(Context paramContext)
  {
    try
    {
      if (!A(paramContext)) {
        return false;
      }
      if (Build.VERSION.SDK_INT >= 21)
      {
        boolean bool = this.n;
        return bool;
      }
    }
    catch (Exception paramContext)
    {
      a();
      paramContext.printStackTrace();
      Log.e(a, "Flash Light unavailable!");
    }
    return false;
  }
  
  public void m(Context paramContext)
  {
    boolean bool1;
    do
    {
      try
      {
        boolean bool2 = a(paramContext, "com.android.calculator2");
        bool1 = bool2;
        if (bool2) {
          continue;
        }
        bool1 = a(paramContext, "com.sec.android.app.popupcalculator");
      }
      catch (Exception paramContext)
      {
        PackageInfo localPackageInfo;
        paramContext.printStackTrace();
        a(f);
        return;
      }
      localPackageInfo = a(paramContext, "Calculator", "calculator");
      if (localPackageInfo != null)
      {
        paramContext.startActivity(paramContext.getPackageManager().getLaunchIntentForPackage(localPackageInfo.packageName));
        return;
      }
      a(e);
      return;
    } while (!bool1);
  }
  
  public int n(Context paramContext)
  {
    try
    {
      this.m = z(paramContext);
      int i1 = Settings.System.getInt(this.m, "screen_brightness");
      return i1;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      Log.e(a, "Screen Brightness unavailable!");
    }
    return 0;
  }
  
  public int o(Context paramContext)
  {
    try
    {
      this.m = z(paramContext);
      int i1 = Settings.System.getInt(this.m, "screen_brightness_mode");
      return i1;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      Log.e(a, "Screen Brightness unavailable!");
    }
    return 0;
  }
  
  public boolean p(Context paramContext)
  {
    try
    {
      this.m = z(paramContext);
      int i1 = o(paramContext);
      return i1 == 1;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      Log.e(a, "Screen Brightness unavailable!");
    }
    return false;
  }
  
  public void q(Context paramContext)
  {
    try
    {
      this.m = z(paramContext);
      Settings.System.putInt(this.m, "screen_brightness_mode", 0);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      Log.e(a, "Screen Brightness unavailable!");
    }
  }
  
  public int r(Context paramContext)
  {
    try
    {
      this.k = y(paramContext);
      int i1 = this.k.getStreamVolume(3);
      return i1;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      Log.e(a, "Sound Volume unavailable!");
    }
    return 0;
  }
  
  public int s(Context paramContext)
  {
    try
    {
      this.k = y(paramContext);
      int i1 = this.k.getStreamMaxVolume(3);
      return i1;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      Log.e(a, "Sound Volume unavailable!");
    }
    return 0;
  }
  
  public void t(Context paramContext)
  {
    try
    {
      this.k = y(paramContext);
      this.k.adjustStreamVolume(3, -1, 1);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      Log.e(a, "Sound Volume unavailable!");
    }
  }
  
  public void u(Context paramContext)
  {
    try
    {
      this.k = y(paramContext);
      this.k.adjustStreamVolume(3, 1, 1);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      Log.e(a, "Sound Volume unavailable!");
    }
  }
  
  public static abstract interface a
  {
    public abstract void a(String paramString);
  }
}
