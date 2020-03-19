package com.framgia.android.emulator;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
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
public final class EmulatorDetector
{
  private static final String[] afA = { "/dev/socket/qemud", "/dev/qemu_pipe" };
  private static final String[] afB = { "ueventd.android_x86.rc", "x86.prop", "ueventd.ttVM_x86.rc", "init.ttVM_x86.rc", "fstab.ttVM_x86", "fstab.vbox86", "init.vbox86.rc", "ueventd.vbox86.rc" };
  private static final String[] afC = { "fstab.andy", "ueventd.andy.rc" };
  private static final String[] afD = { "fstab.nox", "init.nox.rc", "ueventd.nox.rc" };
  private static final Property[] afE = { new Property("init.svc.qemud", null), new Property("init.svc.qemu-props", null), new Property("qemu.hw.mainkeys", null), new Property("qemu.sf.fake_camera", null), new Property("qemu.sf.lcd_density", null), new Property("ro.bootloader", "unknown"), new Property("ro.bootmode", "unknown"), new Property("ro.hardware", "goldfish"), new Property("ro.kernel.android.qemud", null), new Property("ro.kernel.qemu.gles", null), new Property("ro.kernel.qemu", "1"), new Property("ro.product.device", "generic"), new Property("ro.product.model", "sdk"), new Property("ro.product.name", "sdk"), new Property("ro.serialno", null) };
  @SuppressLint({"StaticFieldLeak"})
  private static EmulatorDetector afI;
  private static final String[] afv = { "15555215554", "15555215556", "15555215558", "15555215560", "15555215562", "15555215564", "15555215566", "15555215568", "15555215570", "15555215572", "15555215574", "15555215576", "15555215578", "15555215580", "15555215582", "15555215584" };
  private static final String[] afw = { "000000000000000", "e21833235b6eef10", "012345678912345" };
  private static final String[] afx = { "310260000000000" };
  private static final String[] afy = { "/dev/socket/genyd", "/dev/socket/baseband_genyd" };
  private static final String[] afz = { "goldfish" };
  @SuppressLint({"StaticFieldLeak"})
  private static Context mContext;
  private boolean afF = false;
  private boolean afG = false;
  private List<String> afH = new ArrayList();
  
  private EmulatorDetector(Context paramContext)
  {
    mContext = paramContext;
    this.afH.add("com.google.android.launcher.layouts.genymotion");
    this.afH.add("com.bluestacks");
    this.afH.add("com.bignox.app");
  }
  
  private boolean a(String[] paramArrayOfString, String paramString)
  {
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (new File(paramArrayOfString[i]).exists())
      {
        paramArrayOfString = new StringBuilder("Check ");
        paramArrayOfString.append(paramString);
        paramArrayOfString.append(" is detected");
        log(paramArrayOfString.toString());
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static EmulatorDetector ag(Context paramContext)
  {
    if (afI == null) {
      afI = new EmulatorDetector(paramContext);
    }
    return afI;
  }
  
  private static String k(Context paramContext, String paramString)
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
  
  private boolean ke()
  {
    Object localObject = mContext.getPackageManager().getInstalledApplications(128).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = ((ApplicationInfo)((Iterator)localObject).next()).packageName;
      if (this.afH.contains(str))
      {
        localObject = new StringBuilder("Detected ");
        ((StringBuilder)localObject).append(str);
        log(((StringBuilder)localObject).toString());
        return true;
      }
    }
    return false;
  }
  
  private boolean kf()
  {
    String str = ((TelephonyManager)mContext.getSystemService("phone")).getLine1Number();
    String[] arrayOfString = afv;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (arrayOfString[i].equalsIgnoreCase(str))
      {
        log(" check phone number is detected");
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private boolean kg()
  {
    String str = ((TelephonyManager)mContext.getSystemService("phone")).getDeviceId();
    String[] arrayOfString = afw;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (arrayOfString[i].equalsIgnoreCase(str))
      {
        log("Check device id is detected");
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private boolean kh()
  {
    String str = ((TelephonyManager)mContext.getSystemService("phone")).getSubscriberId();
    String[] arrayOfString = afx;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (arrayOfString[i].equalsIgnoreCase(str))
      {
        log("Check imsi is detected");
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private boolean ki()
  {
    File localFile1 = new File("/proc/tty/drivers");
    File localFile2 = new File("/proc/cpuinfo");
    int i = 0;
    while (i < 2)
    {
      Object localObject2 = new File[] { localFile1, localFile2 }[i];
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
        String[] arrayOfString = afz;
        int k = arrayOfString.length;
        int j = 0;
        while (j < k)
        {
          if (((String)localObject1).contains(arrayOfString[j]))
          {
            log("Check QEmuDrivers is detected");
            return true;
          }
          j += 1;
        }
      }
      i += 1;
    }
    return false;
  }
  
  private boolean kj()
  {
    Property[] arrayOfProperty = afE;
    int m = arrayOfProperty.length;
    int k = 0;
    int i = k;
    while (k < m)
    {
      Property localProperty = arrayOfProperty[k];
      String str = k(mContext, localProperty.name);
      int j = i;
      if (localProperty.afL == null)
      {
        j = i;
        if (str != null) {
          j = i + 1;
        }
      }
      i = j;
      if (localProperty.afL != null)
      {
        i = j;
        if (str.contains(localProperty.afL)) {
          i = j + 1;
        }
      }
      k += 1;
    }
    if (i >= 5)
    {
      log("Check QEmuProps is detected");
      return true;
    }
    return false;
  }
  
  private boolean kk()
  {
    if (ContextCompat.checkSelfPermission(mContext, "android.permission.INTERNET") == 0) {
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
      int j;
      int i;
      for (;;) {}
    }
    Object localObject1 = ((StringBuilder)localObject1).toString();
    localObject2 = new StringBuilder("netcfg data -> ");
    ((StringBuilder)localObject2).append((String)localObject1);
    log(((StringBuilder)localObject2).toString());
    if (!TextUtils.isEmpty((CharSequence)localObject1))
    {
      localObject1 = ((String)localObject1).split("\n");
      j = localObject1.length;
      i = 0;
      while (i < j)
      {
        localObject2 = localObject1[i];
        if (((((String)localObject2).contains("wlan0")) || (((String)localObject2).contains("tunl0")) || (((String)localObject2).contains("eth0"))) && (((String)localObject2).contains("10.0.2.15")))
        {
          log("Check IP is detected");
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  private void log(String paramString)
  {
    if (this.afF) {
      Log.d(getClass().getName(), paramString);
    }
  }
  
  public final EmulatorDetector L(boolean paramBoolean)
  {
    this.afF = false;
    return this;
  }
  
  public final EmulatorDetector M(boolean paramBoolean)
  {
    this.afG = false;
    return this;
  }
  
  public final void a(EmulatorDetector.OnEmulatorDetectorListener paramOnEmulatorDetectorListener)
  {
    new Thread(new EmulatorDetector.1(this, paramOnEmulatorDetectorListener)).start();
  }
  
  public final EmulatorDetector aN(String paramString)
  {
    this.afH.add(paramString);
    return this;
  }
}
