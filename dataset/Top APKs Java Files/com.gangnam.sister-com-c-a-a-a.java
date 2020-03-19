package com.c.a.a;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@TargetApi(14)
public final class a
{
  private static final String[] a = { "15555215554", "15555215556", "15555215558", "15555215560", "15555215562", "15555215564", "15555215566", "15555215568", "15555215570", "15555215572", "15555215574", "15555215576", "15555215578", "15555215580", "15555215582", "15555215584" };
  private static final String[] b = { "000000000000000", "e21833235b6eef10", "012345678912345" };
  private static final String[] c = { "310260000000000" };
  private static final String[] d = { "/dev/socket/genyd", "/dev/socket/baseband_genyd" };
  private static final String[] e = { "goldfish" };
  private static final String[] f = { "/dev/socket/qemud", "/dev/qemu_pipe" };
  private static final String[] g = { "ueventd.android_x86.rc", "x86.prop", "ueventd.ttVM_x86.rc", "init.ttVM_x86.rc", "fstab.ttVM_x86", "fstab.vbox86", "init.vbox86.rc", "ueventd.vbox86.rc" };
  private static final String[] h = { "fstab.andy", "ueventd.andy.rc" };
  private static final String[] i = { "fstab.nox", "init.nox.rc", "ueventd.nox.rc" };
  private static final c[] j = { new c("init.svc.qemud", null), new c("init.svc.qemu-props", null), new c("qemu.hw.mainkeys", null), new c("qemu.sf.fake_camera", null), new c("qemu.sf.lcd_density", null), new c("ro.bootloader", "unknown"), new c("ro.bootmode", "unknown"), new c("ro.hardware", "goldfish"), new c("ro.kernel.android.qemud", null), new c("ro.kernel.qemu.gles", null), new c("ro.kernel.qemu", "1"), new c("ro.product.device", "generic"), new c("ro.product.model", "sdk"), new c("ro.product.name", "sdk"), new c("ro.serialno", null) };
  @SuppressLint({"StaticFieldLeak"})
  private static Context k;
  @SuppressLint({"StaticFieldLeak"})
  private static a o;
  private boolean l = false;
  private boolean m = false;
  private List<String> n = new ArrayList();
  
  private a(Context paramContext)
  {
    k = paramContext;
    this.n.add("com.google.android.launcher.layouts.genymotion");
    this.n.add("com.bluestacks");
    this.n.add("com.bignox.app");
  }
  
  public static a a(Context paramContext)
  {
    if (o == null) {
      o = new a(paramContext);
    }
    return o;
  }
  
  public static String a()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Build.PRODUCT: ");
    localStringBuilder.append(Build.PRODUCT);
    localStringBuilder.append("\nBuild.MANUFACTURER: ");
    localStringBuilder.append(Build.MANUFACTURER);
    localStringBuilder.append("\nBuild.BRAND: ");
    localStringBuilder.append(Build.BRAND);
    localStringBuilder.append("\nBuild.DEVICE: ");
    localStringBuilder.append(Build.DEVICE);
    localStringBuilder.append("\nBuild.MODEL: ");
    localStringBuilder.append(Build.MODEL);
    localStringBuilder.append("\nBuild.HARDWARE: ");
    localStringBuilder.append(Build.HARDWARE);
    localStringBuilder.append("\nBuild.FINGERPRINT: ");
    localStringBuilder.append(Build.FINGERPRINT);
    return localStringBuilder.toString();
  }
  
  private String a(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getClassLoader().loadClass("android.os.SystemProperties");
      paramContext = (String)paramContext.getMethod("get", new Class[] { String.class }).invoke(paramContext, new Object[] { paramString });
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  private boolean a(String[] paramArrayOfString, String paramString)
  {
    int i2 = paramArrayOfString.length;
    int i1 = 0;
    while (i1 < i2)
    {
      if (new File(paramArrayOfString[i1]).exists())
      {
        paramArrayOfString = new StringBuilder();
        paramArrayOfString.append("Check ");
        paramArrayOfString.append(paramString);
        paramArrayOfString.append(" is detected");
        b(paramArrayOfString.toString());
        return true;
      }
      i1 += 1;
    }
    return false;
  }
  
  private void b(String paramString)
  {
    if (this.l) {
      Log.d(getClass().getName(), paramString);
    }
  }
  
  private boolean b()
  {
    b(a());
    boolean bool2 = c();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Check basic ");
    localStringBuilder.append(bool2);
    b(localStringBuilder.toString());
    boolean bool1 = bool2;
    if (!bool2)
    {
      bool1 = d();
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Check Advanced ");
      localStringBuilder.append(bool1);
      b(localStringBuilder.toString());
    }
    bool2 = bool1;
    if (!bool1)
    {
      bool2 = e();
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Check Package Name ");
      localStringBuilder.append(bool2);
      b(localStringBuilder.toString());
    }
    return bool2;
  }
  
  private boolean c()
  {
    boolean bool = Build.FINGERPRINT.startsWith("generic");
    int i3 = 0;
    int i1;
    if ((!bool) && (!Build.MODEL.contains("google_sdk")) && (!Build.MODEL.toLowerCase().contains("droid4x")) && (!Build.MODEL.contains("Emulator")) && (!Build.MODEL.contains("Android SDK built for x86")) && (!Build.MANUFACTURER.contains("Genymotion")) && (!Build.HARDWARE.equals("goldfish")) && (!Build.HARDWARE.equals("vbox86")) && (!Build.PRODUCT.equals("sdk")) && (!Build.PRODUCT.equals("google_sdk")) && (!Build.PRODUCT.equals("sdk_x86")) && (!Build.PRODUCT.equals("vbox86p")) && (!Build.BOARD.toLowerCase().contains("nox")) && (!Build.BOOTLOADER.toLowerCase().contains("nox")) && (!Build.HARDWARE.toLowerCase().contains("nox")) && (!Build.PRODUCT.toLowerCase().contains("nox")) && (!Build.SERIAL.toLowerCase().contains("nox"))) {
      i1 = 0;
    } else {
      i1 = 1;
    }
    if (i1 != 0) {
      return true;
    }
    int i2 = i3;
    if (Build.BRAND.startsWith("generic"))
    {
      i2 = i3;
      if (Build.DEVICE.startsWith("generic")) {
        i2 = 1;
      }
    }
    i1 |= i2;
    if (i1 != 0) {
      return true;
    }
    return i1 | "google_sdk".equals(Build.PRODUCT);
  }
  
  private boolean d()
  {
    return (f()) || (a(d, "Geny")) || (a(h, "Andy")) || (a(i, "Nox")) || (k()) || (a(f, "Pipes")) || (m()) || ((l()) && (a(g, "X86")));
  }
  
  private boolean e()
  {
    Object localObject = k.getPackageManager().getInstalledApplications(128).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = ((ApplicationInfo)((Iterator)localObject).next()).packageName;
      if (this.n.contains(str))
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Detected ");
        ((StringBuilder)localObject).append(str);
        b(((StringBuilder)localObject).toString());
        return true;
      }
    }
    return false;
  }
  
  private boolean f()
  {
    int i1 = ContextCompat.checkSelfPermission(k, "android.permission.READ_PHONE_STATE");
    boolean bool = false;
    if ((i1 == 0) && (this.m) && (n()))
    {
      if ((g()) || (h()) || (i()) || (j())) {
        bool = true;
      }
      return bool;
    }
    return false;
  }
  
  private boolean g()
  {
    String str = ((TelephonyManager)k.getSystemService("phone")).getLine1Number();
    String[] arrayOfString = a;
    int i2 = arrayOfString.length;
    int i1 = 0;
    while (i1 < i2)
    {
      if (arrayOfString[i1].equalsIgnoreCase(str))
      {
        b(" check phone number is detected");
        return true;
      }
      i1 += 1;
    }
    return false;
  }
  
  private boolean h()
  {
    String str = ((TelephonyManager)k.getSystemService("phone")).getDeviceId();
    String[] arrayOfString = b;
    int i2 = arrayOfString.length;
    int i1 = 0;
    while (i1 < i2)
    {
      if (arrayOfString[i1].equalsIgnoreCase(str))
      {
        b("Check device id is detected");
        return true;
      }
      i1 += 1;
    }
    return false;
  }
  
  private boolean i()
  {
    String str = ((TelephonyManager)k.getSystemService("phone")).getSubscriberId();
    String[] arrayOfString = c;
    int i2 = arrayOfString.length;
    int i1 = 0;
    while (i1 < i2)
    {
      if (arrayOfString[i1].equalsIgnoreCase(str))
      {
        b("Check imsi is detected");
        return true;
      }
      i1 += 1;
    }
    return false;
  }
  
  private boolean j()
  {
    if (((TelephonyManager)k.getSystemService("phone")).getNetworkOperatorName().equalsIgnoreCase("android"))
    {
      b("Check operator name android is detected");
      return true;
    }
    return false;
  }
  
  private boolean k()
  {
    File[] arrayOfFile = new File[2];
    arrayOfFile[0] = new File("/proc/tty/drivers");
    arrayOfFile[1] = new File("/proc/cpuinfo");
    int i3 = arrayOfFile.length;
    int i1 = 0;
    while (i1 < i3)
    {
      Object localObject2 = arrayOfFile[i1];
      if ((((File)localObject2).exists()) && (((File)localObject2).canRead()))
      {
        Object localObject1 = new byte['Ѐ'];
        try
        {
          localObject2 = new FileInputStream((File)localObject2);
          ((InputStream)localObject2).read((byte[])localObject1);
          ((InputStream)localObject2).close();
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
        localObject1 = new String((byte[])localObject1);
        String[] arrayOfString = e;
        int i4 = arrayOfString.length;
        int i2 = 0;
        while (i2 < i4)
        {
          if (((String)localObject1).contains(arrayOfString[i2]))
          {
            b("Check QEmuDrivers is detected");
            return true;
          }
          i2 += 1;
        }
      }
      i1 += 1;
    }
    return false;
  }
  
  private boolean l()
  {
    c[] arrayOfC = j;
    int i4 = arrayOfC.length;
    int i3 = 0;
    int i1 = 0;
    while (i3 < i4)
    {
      c localC = arrayOfC[i3];
      String str = a(k, localC.a);
      int i2 = i1;
      if (localC.b == null)
      {
        i2 = i1;
        if (str != null) {
          i2 = i1 + 1;
        }
      }
      i1 = i2;
      if (localC.b != null)
      {
        i1 = i2;
        if (str.contains(localC.b)) {
          i1 = i2 + 1;
        }
      }
      i3 += 1;
    }
    if (i1 >= 5)
    {
      b("Check QEmuProps is detected");
      return true;
    }
    return false;
  }
  
  private boolean m()
  {
    if (ContextCompat.checkSelfPermission(k, "android.permission.INTERNET") == 0) {
      localObject1 = new StringBuilder();
    }
    try
    {
      localObject2 = new ProcessBuilder(new String[] { "/system/bin/netcfg" });
      ((ProcessBuilder)localObject2).directory(new File("/system/bin/"));
      ((ProcessBuilder)localObject2).redirectErrorStream(true);
      localObject2 = ((ProcessBuilder)localObject2).start().getInputStream();
      byte[] arrayOfByte = new byte['Ѐ'];
      while (((InputStream)localObject2).read(arrayOfByte) != -1) {
        ((StringBuilder)localObject1).append(new String(arrayOfByte));
      }
      ((InputStream)localObject2).close();
    }
    catch (Exception localException)
    {
      Object localObject2;
      int i2;
      int i1;
      for (;;) {}
    }
    Object localObject1 = ((StringBuilder)localObject1).toString();
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("netcfg data -> ");
    ((StringBuilder)localObject2).append((String)localObject1);
    b(((StringBuilder)localObject2).toString());
    if (!TextUtils.isEmpty((CharSequence)localObject1))
    {
      localObject1 = ((String)localObject1).split("\n");
      i2 = localObject1.length;
      i1 = 0;
      while (i1 < i2)
      {
        localObject2 = localObject1[i1];
        if (((((String)localObject2).contains("wlan0")) || (((String)localObject2).contains("tunl0")) || (((String)localObject2).contains("eth0"))) && (((String)localObject2).contains("10.0.2.15")))
        {
          b("Check IP is detected");
          return true;
        }
        i1 += 1;
      }
    }
    return false;
  }
  
  private boolean n()
  {
    boolean bool = k.getPackageManager().hasSystemFeature("android.hardware.telephony");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Supported TelePhony: ");
    localStringBuilder.append(bool);
    b(localStringBuilder.toString());
    return bool;
  }
  
  public a a(String paramString)
  {
    this.n.add(paramString);
    return this;
  }
  
  public void a(a paramA)
  {
    new Thread(new b(this, paramA)).start();
  }
  
  public static abstract interface a
  {
    public abstract void onResult(boolean paramBoolean);
  }
}
