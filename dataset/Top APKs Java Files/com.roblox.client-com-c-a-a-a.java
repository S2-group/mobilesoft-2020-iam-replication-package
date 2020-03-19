package com.c.a.a;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.a.c;
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
  private static final b[] j = { new b("init.svc.qemud", null), new b("init.svc.qemu-props", null), new b("qemu.hw.mainkeys", null), new b("qemu.sf.fake_camera", null), new b("qemu.sf.lcd_density", null), new b("ro.bootloader", "unknown"), new b("ro.bootmode", "unknown"), new b("ro.hardware", "goldfish"), new b("ro.kernel.android.qemud", null), new b("ro.kernel.qemu.gles", null), new b("ro.kernel.qemu", "1"), new b("ro.product.device", "generic"), new b("ro.product.model", "sdk"), new b("ro.product.name", "sdk"), new b("ro.serialno", null) };
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
    return "Build.PRODUCT: " + Build.PRODUCT + "\nBuild.MANUFACTURER: " + Build.MANUFACTURER + "\nBuild.BRAND: " + Build.BRAND + "\nBuild.DEVICE: " + Build.DEVICE + "\nBuild.MODEL: " + Build.MODEL + "\nBuild.HARDWARE: " + Build.HARDWARE + "\nBuild.FINGERPRINT: " + Build.FINGERPRINT;
  }
  
  private String a(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getClassLoader().loadClass("android.os.SystemProperties");
      paramContext = (String)paramContext.getMethod("get", new Class[] { String.class }).invoke(paramContext, new Object[] { paramString });
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  private void a(String paramString)
  {
    if (this.l) {
      Log.d(getClass().getName(), paramString);
    }
  }
  
  private boolean a(String[] paramArrayOfString, String paramString)
  {
    boolean bool2 = false;
    int i2 = paramArrayOfString.length;
    int i1 = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i1 < i2)
      {
        if (new File(paramArrayOfString[i1]).exists())
        {
          a("Check " + paramString + " is detected");
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i1 += 1;
    }
  }
  
  private boolean b()
  {
    a(a());
    boolean bool2 = c();
    a("Check basic " + bool2);
    boolean bool1 = bool2;
    if (!bool2)
    {
      bool1 = d();
      a("Check Advanced " + bool1);
    }
    bool2 = bool1;
    if (!bool1)
    {
      bool2 = e();
      a("Check Package Name " + bool2);
    }
    return bool2;
  }
  
  private boolean c()
  {
    int i3 = 0;
    int i1;
    if ((Build.FINGERPRINT.startsWith("generic")) || (Build.MODEL.contains("google_sdk")) || (Build.MODEL.toLowerCase().contains("droid4x")) || (Build.MODEL.contains("Emulator")) || (Build.MODEL.contains("Android SDK built for x86")) || (Build.MANUFACTURER.contains("Genymotion")) || (Build.HARDWARE.equals("goldfish")) || (Build.HARDWARE.equals("vbox86")) || (Build.PRODUCT.equals("sdk")) || (Build.PRODUCT.equals("google_sdk")) || (Build.PRODUCT.equals("sdk_x86")) || (Build.PRODUCT.equals("vbox86p")) || (Build.BOARD.toLowerCase().contains("nox")) || (Build.BOOTLOADER.toLowerCase().contains("nox")) || (Build.HARDWARE.toLowerCase().contains("nox")) || (Build.PRODUCT.toLowerCase().contains("nox")) || (Build.SERIAL.toLowerCase().contains("nox")))
    {
      i1 = 1;
      if (i1 == 0) {
        break label234;
      }
    }
    label234:
    do
    {
      return true;
      i1 = 0;
      break;
      int i2 = i3;
      if (Build.BRAND.startsWith("generic"))
      {
        i2 = i3;
        if (Build.DEVICE.startsWith("generic")) {
          i2 = 1;
        }
      }
      i1 = i2 | i1;
    } while (i1 != 0);
    return "google_sdk".equals(Build.PRODUCT) | i1;
  }
  
  private boolean d()
  {
    return (f()) || (a(d, "Geny")) || (a(h, "Andy")) || (a(i, "Nox")) || (k()) || (a(f, "Pipes")) || (m()) || ((l()) && (a(g, "X86")));
  }
  
  private boolean e()
  {
    Iterator localIterator = k.getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      String str = ((ApplicationInfo)localIterator.next()).packageName;
      if (this.n.contains(str))
      {
        a("Detected " + str);
        return true;
      }
    }
    return false;
  }
  
  private boolean f()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (c.a(k, "android.permission.READ_PHONE_STATE") == 0)
    {
      bool1 = bool2;
      if (this.m)
      {
        bool1 = bool2;
        if (n()) {
          if ((!g()) && (!h()) && (!i()))
          {
            bool1 = bool2;
            if (!j()) {}
          }
          else
          {
            bool1 = true;
          }
        }
      }
    }
    return bool1;
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
        a(" check phone number is detected");
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
        a("Check device id is detected");
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
        a("Check imsi is detected");
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
      a("Check operator name android is detected");
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
          localObject1 = new String((byte[])localObject1);
          localObject2 = e;
          int i4 = localObject2.length;
          i2 = 0;
          if (i2 < i4) {
            if (((String)localObject1).contains(localObject2[i2]))
            {
              a("Check QEmuDrivers is detected");
              return true;
            }
          }
        }
        catch (Exception localException)
        {
          for (;;)
          {
            int i2;
            localException.printStackTrace();
            continue;
            i2 += 1;
          }
        }
      }
      i1 += 1;
    }
    return false;
  }
  
  private boolean l()
  {
    b[] arrayOfB = j;
    int i4 = arrayOfB.length;
    int i3 = 0;
    int i1 = 0;
    while (i3 < i4)
    {
      b localB = arrayOfB[i3];
      String str = a(k, localB.a);
      int i2 = i1;
      if (localB.b == null)
      {
        i2 = i1;
        if (str != null) {
          i2 = i1 + 1;
        }
      }
      i1 = i2;
      if (localB.b != null)
      {
        i1 = i2;
        if (str.contains(localB.b)) {
          i1 = i2 + 1;
        }
      }
      i3 += 1;
    }
    if (i1 >= 5)
    {
      a("Check QEmuProps is detected");
      return true;
    }
    return false;
  }
  
  private boolean m()
  {
    if (c.a(k, "android.permission.INTERNET") == 0)
    {
      Object localObject1 = new StringBuilder();
      try
      {
        Object localObject2 = new ProcessBuilder(new String[] { "/system/bin/netcfg" });
        ((ProcessBuilder)localObject2).directory(new File("/system/bin/"));
        ((ProcessBuilder)localObject2).redirectErrorStream(true);
        localObject2 = ((ProcessBuilder)localObject2).start().getInputStream();
        byte[] arrayOfByte = new byte['Ѐ'];
        while (((InputStream)localObject2).read(arrayOfByte) != -1) {
          ((StringBuilder)localObject1).append(new String(arrayOfByte));
        }
        localObject1 = ((StringBuilder)localObject1).toString();
      }
      catch (Exception localException) {}
      a("netcfg data -> " + (String)localObject1);
      if (!TextUtils.isEmpty((CharSequence)localObject1))
      {
        localObject1 = ((String)localObject1).split("\n");
        int i2 = localObject1.length;
        int i1 = 0;
        for (;;)
        {
          if (i1 >= i2) {
            break label236;
          }
          Object localObject3 = localObject1[i1];
          if (((localObject3.contains("wlan0")) || (localObject3.contains("tunl0")) || (localObject3.contains("eth0"))) && (localObject3.contains("10.0.2.15")))
          {
            a("Check IP is detected");
            return true;
            localObject3.close();
            break;
          }
          i1 += 1;
        }
      }
    }
    label236:
    return false;
  }
  
  private boolean n()
  {
    boolean bool = k.getPackageManager().hasSystemFeature("android.hardware.telephony");
    a("Supported TelePhony: " + bool);
    return bool;
  }
  
  public a a(boolean paramBoolean)
  {
    this.m = paramBoolean;
    return this;
  }
  
  public void a(final a paramA)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        boolean bool = a.a(a.this);
        a.a(a.this, "This System is Emulator: " + bool);
        if (paramA != null) {
          paramA.a(bool);
        }
      }
    }).start();
  }
  
  public static abstract interface a
  {
    public abstract void a(boolean paramBoolean);
  }
}
