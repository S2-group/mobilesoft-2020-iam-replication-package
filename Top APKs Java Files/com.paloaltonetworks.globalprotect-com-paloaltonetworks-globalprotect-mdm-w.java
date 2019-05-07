package com.paloaltonetworks.globalprotect.mdm;

import android.app.admin.DevicePolicyManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.paloaltonetworks.globalprotect.Log;
import com.paloaltonetworks.globalprotect.PanGPPreferences;
import com.paloaltonetworks.globalprotect.PanGPPreferences.Editor;
import com.paloaltonetworks.globalprotect.PanGPService;
import com.paloaltonetworks.globalprotect.PanMSAgentActivity;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.a.a.a.c;
import org.apache.a.a.a.e;

public class w
{
  public static final String a = "com.paloaltonetworks.globalprotect.mdm.PanMDMDeviceAdminReceiver";
  public static final String b = "<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">";
  public static final String c = "\n<plist version=\"1.0\">";
  public static final double d = 6.5D;
  public static final double e = 8.5D;
  public static final int f = 1;
  public static final int g = 2;
  public static final int h = 3;
  public static final String i = "";
  public static final String j = "wifi_mac_address";
  private static final String k = "PanMDMHipReport";
  private static final String l = "5123c579-f2e6-7a72-0000-0036";
  private static w t = null;
  private Context m = null;
  private TelephonyManager n = null;
  private WifiManager o = null;
  private String p = null;
  private String q = null;
  private String r = null;
  private String s = null;
  
  w() {}
  
  private boolean A()
  {
    DevicePolicyManager localDevicePolicyManager = (DevicePolicyManager)this.m.getSystemService("device_policy");
    try
    {
      boolean bool = localDevicePolicyManager.isActivePasswordSufficient();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return true;
  }
  
  private boolean B()
  {
    DevicePolicyManager localDevicePolicyManager = (DevicePolicyManager)this.m.getSystemService("device_policy");
    try
    {
      boolean bool = localDevicePolicyManager.isActivePasswordSufficient();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return true;
  }
  
  private boolean C()
  {
    DevicePolicyManager localDevicePolicyManager = (DevicePolicyManager)this.m.getSystemService("device_policy");
    Object localObject = localDevicePolicyManager.getActiveAdmins();
    int i1;
    if (localObject != null)
    {
      i1 = 0;
      if (i1 < ((List)localObject).size())
      {
        Log.LOG_DEBUG("PanMDMHipReport", "admin " + i1 + " =" + ((ComponentName)((List)localObject).get(i1)).getClassName());
        if (((ComponentName)((List)localObject).get(i1)).getClassName().equals("com.paloaltonetworks.globalprotect.mdm.PanMDMDeviceAdminReceiver"))
        {
          localObject = (ComponentName)((List)localObject).get(i1);
          label120:
          Log.LOG_DEBUG("PanMDMHipReport", "admin=" + ((ComponentName)localObject).toString());
        }
      }
    }
    for (;;)
    {
      if (localObject == null)
      {
        Log.LOG_ERROR("PanMDMHipReport", "device admin is null!");
        return false;
        i1 += 1;
        break;
      }
      i1 = localDevicePolicyManager.getPasswordMinimumLength((ComponentName)localObject);
      int i2 = localDevicePolicyManager.getPasswordQuality((ComponentName)localObject);
      Log.LOG_DEBUG("PanMDMHipReport", "backupMinPasswdLen=" + i1);
      localDevicePolicyManager.setPasswordQuality((ComponentName)localObject, 131072);
      localDevicePolicyManager.setPasswordMinimumLength((ComponentName)localObject, 4);
      boolean bool = localDevicePolicyManager.isActivePasswordSufficient();
      localDevicePolicyManager.setPasswordQuality((ComponentName)localObject, i2);
      localDevicePolicyManager.setPasswordMinimumLength((ComponentName)localObject, i1);
      Log.LOG_INFO("PanMDMHipReport", "is password present=" + bool);
      return bool;
      localObject = null;
      break label120;
      localObject = null;
    }
  }
  
  private boolean D()
  {
    DevicePolicyManager localDevicePolicyManager = (DevicePolicyManager)this.m.getSystemService("device_policy");
    return (localDevicePolicyManager.getActiveAdmins() == null) || (localDevicePolicyManager.isActivePasswordSufficient());
  }
  
  public static w a()
  {
    if (t == null) {
      t = new w();
    }
    return t;
  }
  
  public static String a(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar1 = new char[16];
    char[] tmp6_5 = arrayOfChar1;
    tmp6_5[0] = 48;
    char[] tmp11_6 = tmp6_5;
    tmp11_6[1] = 49;
    char[] tmp16_11 = tmp11_6;
    tmp16_11[2] = 50;
    char[] tmp21_16 = tmp16_11;
    tmp21_16[3] = 51;
    char[] tmp26_21 = tmp21_16;
    tmp26_21[4] = 52;
    char[] tmp31_26 = tmp26_21;
    tmp31_26[5] = 53;
    char[] tmp36_31 = tmp31_26;
    tmp36_31[6] = 54;
    char[] tmp42_36 = tmp36_31;
    tmp42_36[7] = 55;
    char[] tmp48_42 = tmp42_36;
    tmp48_42[8] = 56;
    char[] tmp54_48 = tmp48_42;
    tmp54_48[9] = 57;
    char[] tmp60_54 = tmp54_48;
    tmp60_54[10] = 97;
    char[] tmp66_60 = tmp60_54;
    tmp66_60[11] = 98;
    char[] tmp72_66 = tmp66_60;
    tmp72_66[12] = 99;
    char[] tmp78_72 = tmp72_66;
    tmp78_72[13] = 100;
    char[] tmp84_78 = tmp78_72;
    tmp84_78[14] = 101;
    char[] tmp90_84 = tmp84_78;
    tmp90_84[15] = 102;
    tmp90_84;
    char[] arrayOfChar2 = new char[paramArrayOfByte.length * 2];
    int i1 = 0;
    while (i1 < paramArrayOfByte.length)
    {
      int i2 = paramArrayOfByte[i1] & 0xFF;
      arrayOfChar2[(i1 * 2)] = arrayOfChar1[(i2 >>> 4)];
      arrayOfChar2[(i1 * 2 + 1)] = arrayOfChar1[(i2 & 0xF)];
      i1 += 1;
    }
    return new String(arrayOfChar2);
  }
  
  public static byte[] a(String paramString)
  {
    int i2 = paramString.length();
    byte[] arrayOfByte = new byte[i2 / 2];
    int i1 = 0;
    while (i1 < i2)
    {
      arrayOfByte[(i1 / 2)] = ((byte)((Character.digit(paramString.charAt(i1), 16) << 4) + Character.digit(paramString.charAt(i1 + 1), 16)));
      i1 += 2;
    }
    return arrayOfByte;
  }
  
  public static String b(String paramString)
  {
    byte[] arrayOfByte = null;
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-256");
      if (paramString != null) {
        arrayOfByte = paramString.getBytes();
      }
      localMessageDigest.update(arrayOfByte);
      return b(localMessageDigest.digest());
    }
    catch (Exception paramString)
    {
      Log.LOG_INFO("PanMDMHipReport", "Can not get MessageDigest's SHA-256 instance " + paramString.getMessage());
    }
    return null;
  }
  
  public static String b(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i2 = paramArrayOfByte.length;
    int i1 = 0;
    while (i1 < i2)
    {
      int i3 = paramArrayOfByte[i1] & 0xFF;
      if (i3 < 16) {
        localStringBuffer.append("0");
      }
      localStringBuffer.append(Integer.toHexString(i3));
      i1 += 1;
    }
    return localStringBuffer.toString();
  }
  
  public static boolean d()
  {
    boolean bool = false;
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    PanMSAgentActivity localPanMSAgentActivity = PanMSAgentActivity.getExistInstance();
    if (localPanMSAgentActivity != null)
    {
      localPanMSAgentActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
      if (Math.sqrt(Math.pow(localDisplayMetrics.widthPixels / localDisplayMetrics.xdpi, 2.0D) + Math.pow(localDisplayMetrics.heightPixels / localDisplayMetrics.ydpi, 2.0D)) <= 6.5D) {
        bool = true;
      }
      return bool;
    }
    Log.LOG_WARNING("PanMDMHipReport", "unable to decide device type: phone or tablet?");
    return false;
  }
  
  public static int e()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    PanMSAgentActivity localPanMSAgentActivity = PanMSAgentActivity.getExistInstance();
    if (localPanMSAgentActivity != null)
    {
      localPanMSAgentActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
      double d1 = Math.sqrt(Math.pow(localDisplayMetrics.widthPixels / localDisplayMetrics.xdpi, 2.0D) + Math.pow(localDisplayMetrics.heightPixels / localDisplayMetrics.ydpi, 2.0D));
      if (d1 <= 6.5D) {
        return 1;
      }
      if (d1 <= 8.5D) {
        return 2;
      }
      return 3;
    }
    Log.LOG_WARNING("PanMDMHipReport", "unable to decide device type..");
    return 1;
  }
  
  public static String f()
  {
    PanGPPreferences localPanGPPreferences = PanGPService.getService().p();
    if (localPanGPPreferences != null)
    {
      localObject1 = localPanGPPreferences.getString("wifi_mac_address", null);
      if (localObject1 != null)
      {
        Log.LOG_DEBUG("PanMDMHipReport", "wifi mac addr=" + (String)localObject1);
        return localObject1;
      }
    }
    else
    {
      Log.LOG_ERROR("PanMDMHipReport", "app preference data structure corrupted");
      return "";
    }
    Object localObject1 = (WifiManager)PanGPService.getService().getApplicationContext().getSystemService("wifi");
    boolean bool = ((WifiManager)localObject1).isWifiEnabled();
    if (!bool)
    {
      Log.LOG_INFO("PanMDMHipReport", "turning on wifi..");
      ((WifiManager)localObject1).setWifiEnabled(true);
    }
    try
    {
      Thread.sleep(3000L);
      try
      {
        Object localObject2 = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
        do
        {
          if (!((Iterator)localObject2).hasNext()) {
            break;
          }
          localObject3 = (NetworkInterface)((Iterator)localObject2).next();
        } while (!((NetworkInterface)localObject3).getName().equalsIgnoreCase("wlan0"));
        localObject2 = ((NetworkInterface)localObject3).getHardwareAddress();
        if (localObject2 == null) {
          return "";
        }
        Object localObject3 = new StringBuilder();
        int i1 = 0;
        while (i1 < localObject2.length)
        {
          ((StringBuilder)localObject3).append(String.format("%02X:", new Object[] { Byte.valueOf(localObject2[i1]) }));
          i1 += 1;
        }
        if (((StringBuilder)localObject3).length() > 0) {
          ((StringBuilder)localObject3).deleteCharAt(((StringBuilder)localObject3).length() - 1);
        }
        if (!bool)
        {
          Log.LOG_INFO("PanMDMHipReport", "turning off wifi..");
          ((WifiManager)localObject1).setWifiEnabled(false);
        }
        localObject2 = ((StringBuilder)localObject3).toString().toLowerCase();
        localPanGPPreferences.a().a("wifi_mac_address", (String)localObject2);
        Log.LOG_DEBUG("PanMDMHipReport", "wifi mac addr2=" + (String)localObject2);
        return localObject2;
      }
      catch (Exception localException1)
      {
        if (!bool)
        {
          Log.LOG_INFO("PanMDMHipReport", "turning off wifi..");
          ((WifiManager)localObject1).setWifiEnabled(false);
        }
        return "";
      }
    }
    catch (Exception localException2)
    {
      for (;;) {}
    }
  }
  
  public static void j()
  {
    PackageManager localPackageManager = PanGPService.getService().getPackageManager();
    Object localObject1 = localPackageManager.getInstalledApplications(128);
    byte[] arrayOfByte = new byte[20];
    q localQ = new q(PanGPService.getService().getApplicationContext());
    localQ.a();
    int i4 = ((List)localObject1).size();
    Iterator localIterator = ((List)localObject1).iterator();
    int i1 = 0;
    label525:
    label530:
    for (;;)
    {
      if (localIterator.hasNext())
      {
        localObject1 = (ApplicationInfo)localIterator.next();
        if (Thread.interrupted())
        {
          Log.LOG_WARNING("PanMDMHipReport", "cancelling the pre-calculation thread..");
          localQ.c();
          return;
        }
      }
      try
      {
        localPackageInfo = localPackageManager.getPackageInfo(((ApplicationInfo)localObject1).packageName, 0);
        if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
          break label530;
        }
        i2 = 1;
      }
      catch (Exception localException3)
      {
        for (;;)
        {
          PackageInfo localPackageInfo;
          Object localObject2;
          continue;
          int i3 = 0;
          continue;
          int i2 = 0;
        }
      }
      localObject2 = localQ.b(localPackageInfo.packageName, localPackageInfo.versionName);
      if (localObject2 != null)
      {
        Log.LOG_DEBUG("PanMDMHipReport", localPackageInfo.packageName + " checksum from db=" + (String)localObject2);
      }
      else
      {
        Log.LOG_INFO("PanMDMHipReport", "calculating checksum for " + ((ApplicationInfo)localObject1).sourceDir + " " + i1 + "/" + i4);
        i1 += 1;
        if (i2 == 0) {}
        for (;;)
        {
          try
          {
            localObject1 = new FileInputStream(new File(((ApplicationInfo)localObject1).sourceDir));
            localObject2 = new e().a("jar", (InputStream)localObject1);
            localObject1 = (org.apache.a.a.a.f.t)((c)localObject2).a();
            if (localObject1 == null) {
              break label525;
            }
            if (((org.apache.a.a.a.f.t)localObject1).getName().equals("classes.dex"))
            {
              i3 = 1;
              if (i3 != 0) {
                continue;
              }
              Log.LOG_INFO("PanMDMHipReport", "classes.dex is NOT found..");
              ((c)localObject2).close();
            }
          }
          catch (Exception localException1)
          {
            long l1;
            localException1.printStackTrace();
            continue;
          }
          try
          {
            l1 = new File(localPackageInfo.applicationInfo.sourceDir).length();
            localObject1 = a(arrayOfByte);
            localQ.a(localPackageInfo.packageName, localPackageInfo.versionName, (String)localObject1, (int)l1, i2);
          }
          catch (Exception localException2) {}
          localObject1 = (org.apache.a.a.a.f.t)((c)localObject2).a();
          continue;
          ((c)localObject2).skip(12L);
          ((c)localObject2).read(arrayOfByte, 0, 20);
          Log.LOG_DEBUG("PanMDMHipReport", "sha1=" + a(arrayOfByte));
          continue;
          Log.LOG_INFO("PanMDMHipReport", "Exception: " + localException2.getMessage());
          break;
          System.arraycopy("0123456789abcdefghij".getBytes(), 0, arrayOfByte, 0, 20);
        }
        localQ.c();
        return;
      }
    }
  }
  
  private String l()
  {
    try
    {
      Object localObject = BluetoothAdapter.getDefaultAdapter();
      if (localObject == null)
      {
        Log.LOG_INFO("PanMDMHipReport", "no bluetooth hardware found...");
        return null;
      }
      String str2 = ((BluetoothAdapter)localObject).getAddress();
      localObject = str2;
      if (str2 == null)
      {
        Log.LOG_INFO("PanMDMHipReport", "unable to retrieve bluetooth mac address, set to default 00:00:00:00:00:00");
        localObject = "";
      }
      Log.LOG_INFO("PanMDMHipReport", "bluetooth mac=" + (String)localObject);
      str2 = ((String)localObject).toLowerCase();
      localObject = str2;
      if (str2.contains("unknown")) {
        return "";
      }
    }
    catch (Exception localException)
    {
      Log.LOG_INFO("PanMDMHipReport", "exception when tried to retrieve mac addr for bluetooth.");
      String str1 = null;
      return str1;
    }
  }
  
  private boolean m()
  {
    try
    {
      int i1 = Settings.Secure.getInt(this.m.getContentResolver(), "data_roaming");
      return i1 == 1;
    }
    catch (Settings.SettingNotFoundException localSettingNotFoundException) {}
    return false;
  }
  
  private String n()
  {
    return Build.MODEL;
  }
  
  private String o()
  {
    return Build.MODEL;
  }
  
  private String p()
  {
    String str2 = "";
    String str3 = Build.MANUFACTURER;
    String str1 = str2;
    if (str3 != null)
    {
      str1 = str2;
      if (!str3.isEmpty()) {
        str1 = str3.substring(0, 1).toUpperCase() + str3.substring(1);
      }
    }
    return str1;
  }
  
  private String q()
  {
    return this.n.getSimSerialNumber();
  }
  
  private String r()
  {
    return this.n.getLine1Number();
  }
  
  private String s()
  {
    return this.n.getDeviceId();
  }
  
  private boolean t()
  {
    return this.n.isNetworkRoaming();
  }
  
  private String u()
  {
    return Build.VERSION.RELEASE;
  }
  
  private String v()
  {
    return Build.PRODUCT;
  }
  
  private String w()
  {
    return Build.SERIAL;
  }
  
  private String x()
  {
    return this.n.getNetworkOperatorName();
  }
  
  /* Error */
  private String y()
  {
    // Byte code:
    //   0: invokestatic 80	com/paloaltonetworks/globalprotect/PanGPService:getService	()Lcom/paloaltonetworks/globalprotect/PanGPService;
    //   3: invokevirtual 433	com/paloaltonetworks/globalprotect/PanGPService:getPackageManager	()Landroid/content/pm/PackageManager;
    //   6: astore 9
    //   8: aload 9
    //   10: sipush 128
    //   13: invokevirtual 439	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   16: astore 8
    //   18: ldc_w 641
    //   21: astore 7
    //   23: sipush 8192
    //   26: newarray byte
    //   28: astore 10
    //   30: bipush 20
    //   32: newarray byte
    //   34: astore 10
    //   36: ldc -25
    //   38: invokestatic 237	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   41: astore 11
    //   43: ldc -25
    //   45: invokestatic 237	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   48: astore 12
    //   50: invokestatic 646	com/paloaltonetworks/globalprotect/mdm/j:a	()Ljava/lang/Thread;
    //   53: astore 13
    //   55: aload 13
    //   57: ifnull +30 -> 87
    //   60: aload 13
    //   62: invokevirtual 649	java/lang/Thread:isAlive	()Z
    //   65: ifeq +22 -> 87
    //   68: ldc 37
    //   70: ldc_w 651
    //   73: invokestatic 325	com/paloaltonetworks/globalprotect/Log:LOG_WARNING	(Ljava/lang/String;Ljava/lang/String;)V
    //   76: aload 13
    //   78: invokevirtual 654	java/lang/Thread:interrupt	()V
    //   81: ldc2_w 655
    //   84: invokestatic 361	java/lang/Thread:sleep	(J)V
    //   87: new 441	com/paloaltonetworks/globalprotect/mdm/q
    //   90: dup
    //   91: invokestatic 80	com/paloaltonetworks/globalprotect/PanGPService:getService	()Lcom/paloaltonetworks/globalprotect/PanGPService;
    //   94: invokevirtual 344	com/paloaltonetworks/globalprotect/PanGPService:getApplicationContext	()Landroid/content/Context;
    //   97: invokespecial 444	com/paloaltonetworks/globalprotect/mdm/q:<init>	(Landroid/content/Context;)V
    //   100: astore 13
    //   102: aload 13
    //   104: invokevirtual 446	com/paloaltonetworks/globalprotect/mdm/q:a	()V
    //   107: aload 8
    //   109: invokeinterface 120 1 0
    //   114: istore 4
    //   116: aload 8
    //   118: invokeinterface 377 1 0
    //   123: astore 14
    //   125: iconst_0
    //   126: istore_1
    //   127: aload 14
    //   129: invokeinterface 382 1 0
    //   134: ifeq +30 -> 164
    //   137: aload 14
    //   139: invokeinterface 386 1 0
    //   144: checkcast 448	android/content/pm/ApplicationInfo
    //   147: astore 15
    //   149: invokestatic 661	com/paloaltonetworks/globalprotect/mdm/t:ar	()Z
    //   152: iconst_1
    //   153: if_icmpne +69 -> 222
    //   156: ldc 37
    //   158: ldc_w 663
    //   161: invokestatic 192	com/paloaltonetworks/globalprotect/Log:LOG_INFO	(Ljava/lang/String;Ljava/lang/String;)V
    //   164: aload 13
    //   166: invokevirtual 455	com/paloaltonetworks/globalprotect/mdm/q:c	()V
    //   169: aload_0
    //   170: aload 12
    //   172: invokevirtual 248	java/security/MessageDigest:digest	()[B
    //   175: invokestatic 250	com/paloaltonetworks/globalprotect/mdm/w:b	([B)Ljava/lang/String;
    //   178: putfield 74	com/paloaltonetworks/globalprotect/mdm/w:s	Ljava/lang/String;
    //   181: aload_0
    //   182: aload 7
    //   184: putfield 70	com/paloaltonetworks/globalprotect/mdm/w:q	Ljava/lang/String;
    //   187: aload 7
    //   189: areturn
    //   190: astore 7
    //   192: ldc 37
    //   194: new 122	java/lang/StringBuilder
    //   197: dup
    //   198: invokespecial 123	java/lang/StringBuilder:<init>	()V
    //   201: ldc -4
    //   203: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   206: aload 7
    //   208: invokevirtual 255	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   211: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   214: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   217: invokestatic 192	com/paloaltonetworks/globalprotect/Log:LOG_INFO	(Ljava/lang/String;Ljava/lang/String;)V
    //   220: aconst_null
    //   221: areturn
    //   222: aload 11
    //   224: invokevirtual 666	java/security/MessageDigest:reset	()V
    //   227: lconst_0
    //   228: lstore 5
    //   230: aload 9
    //   232: aload 15
    //   234: getfield 458	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   237: iconst_0
    //   238: invokevirtual 462	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   241: astore 16
    //   243: iconst_0
    //   244: istore_2
    //   245: aload 16
    //   247: getfield 468	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   250: getfield 471	android/content/pm/ApplicationInfo:flags	I
    //   253: iconst_1
    //   254: iand
    //   255: ifeq +5 -> 260
    //   258: iconst_1
    //   259: istore_2
    //   260: aload 13
    //   262: aload 16
    //   264: getfield 472	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   267: aload 16
    //   269: getfield 475	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   272: invokevirtual 477	com/paloaltonetworks/globalprotect/mdm/q:b	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   275: astore 8
    //   277: aload 8
    //   279: ifnull +111 -> 390
    //   282: ldc 37
    //   284: new 122	java/lang/StringBuilder
    //   287: dup
    //   288: invokespecial 123	java/lang/StringBuilder:<init>	()V
    //   291: aload 16
    //   293: getfield 472	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   296: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   299: ldc_w 479
    //   302: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   305: aload 8
    //   307: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   310: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   313: invokestatic 153	com/paloaltonetworks/globalprotect/Log:LOG_DEBUG	(Ljava/lang/String;Ljava/lang/String;)V
    //   316: aload 12
    //   318: aload 8
    //   320: invokestatic 668	com/paloaltonetworks/globalprotect/mdm/w:a	(Ljava/lang/String;)[B
    //   323: invokevirtual 245	java/security/MessageDigest:update	([B)V
    //   326: iload_1
    //   327: iconst_1
    //   328: iadd
    //   329: istore_1
    //   330: invokestatic 673	com/paloaltonetworks/globalprotect/mdm/s:a	()Lcom/paloaltonetworks/globalprotect/mdm/s;
    //   333: ifnull +26 -> 359
    //   336: invokestatic 673	com/paloaltonetworks/globalprotect/mdm/s:a	()Lcom/paloaltonetworks/globalprotect/mdm/s;
    //   339: ldc2_w 674
    //   342: iload_1
    //   343: i2d
    //   344: dmul
    //   345: iload 4
    //   347: i2d
    //   348: ddiv
    //   349: invokestatic 679	java/lang/Math:round	(D)J
    //   352: l2i
    //   353: bipush 20
    //   355: iadd
    //   356: invokevirtual 682	com/paloaltonetworks/globalprotect/mdm/s:a	(I)V
    //   359: new 684	java/util/StringTokenizer
    //   362: dup
    //   363: aload 15
    //   365: getfield 484	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   368: invokespecial 685	java/util/StringTokenizer:<init>	(Ljava/lang/String;)V
    //   371: astore 17
    //   373: aload 17
    //   375: invokevirtual 688	java/util/StringTokenizer:hasMoreTokens	()Z
    //   378: ifeq +345 -> 723
    //   381: aload 17
    //   383: invokevirtual 691	java/util/StringTokenizer:nextToken	()Ljava/lang/String;
    //   386: pop
    //   387: goto -14 -> 373
    //   390: ldc 37
    //   392: new 122	java/lang/StringBuilder
    //   395: dup
    //   396: invokespecial 123	java/lang/StringBuilder:<init>	()V
    //   399: ldc_w 481
    //   402: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   405: aload 15
    //   407: getfield 484	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   410: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   413: ldc_w 486
    //   416: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   419: iload_1
    //   420: invokevirtual 132	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   423: ldc_w 488
    //   426: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   429: iload 4
    //   431: invokevirtual 132	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   434: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   437: invokestatic 153	com/paloaltonetworks/globalprotect/Log:LOG_DEBUG	(Ljava/lang/String;Ljava/lang/String;)V
    //   440: iload_1
    //   441: iconst_1
    //   442: iadd
    //   443: istore_1
    //   444: iload_2
    //   445: ifne +260 -> 705
    //   448: new 490	java/io/FileInputStream
    //   451: dup
    //   452: new 492	java/io/File
    //   455: dup
    //   456: aload 15
    //   458: getfield 484	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   461: invokespecial 495	java/io/File:<init>	(Ljava/lang/String;)V
    //   464: invokespecial 498	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   467: astore 8
    //   469: new 500	org/apache/a/a/a/e
    //   472: dup
    //   473: invokespecial 501	org/apache/a/a/a/e:<init>	()V
    //   476: ldc_w 503
    //   479: aload 8
    //   481: invokevirtual 506	org/apache/a/a/a/e:a	(Ljava/lang/String;Ljava/io/InputStream;)Lorg/apache/a/a/a/c;
    //   484: astore 17
    //   486: aload 17
    //   488: invokevirtual 511	org/apache/a/a/a/c:a	()Lorg/apache/a/a/a/a;
    //   491: checkcast 513	org/apache/a/a/a/f/t
    //   494: astore 8
    //   496: aload 8
    //   498: ifnull +372 -> 870
    //   501: aload 8
    //   503: invokevirtual 514	org/apache/a/a/a/f/t:getName	()Ljava/lang/String;
    //   506: ldc_w 516
    //   509: invokevirtual 159	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   512: ifeq +84 -> 596
    //   515: iconst_1
    //   516: istore_3
    //   517: iload_3
    //   518: ifne +91 -> 609
    //   521: ldc 37
    //   523: ldc_w 518
    //   526: invokestatic 192	com/paloaltonetworks/globalprotect/Log:LOG_INFO	(Ljava/lang/String;Ljava/lang/String;)V
    //   529: aload 17
    //   531: invokevirtual 521	org/apache/a/a/a/c:close	()V
    //   534: new 492	java/io/File
    //   537: dup
    //   538: aload 16
    //   540: getfield 468	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   543: getfield 484	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   546: invokespecial 495	java/io/File:<init>	(Ljava/lang/String;)V
    //   549: invokevirtual 524	java/io/File:length	()J
    //   552: lstore 5
    //   554: aload 10
    //   556: invokestatic 526	com/paloaltonetworks/globalprotect/mdm/w:a	([B)Ljava/lang/String;
    //   559: astore 8
    //   561: aload 13
    //   563: aload 16
    //   565: getfield 472	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   568: aload 16
    //   570: getfield 475	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   573: aload 8
    //   575: lload 5
    //   577: l2i
    //   578: iload_2
    //   579: invokevirtual 529	com/paloaltonetworks/globalprotect/mdm/q:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)Lcom/paloaltonetworks/globalprotect/mdm/aa;
    //   582: pop
    //   583: aload 12
    //   585: aload 8
    //   587: invokestatic 668	com/paloaltonetworks/globalprotect/mdm/w:a	(Ljava/lang/String;)[B
    //   590: invokevirtual 245	java/security/MessageDigest:update	([B)V
    //   593: goto -267 -> 326
    //   596: aload 17
    //   598: invokevirtual 511	org/apache/a/a/a/c:a	()Lorg/apache/a/a/a/a;
    //   601: checkcast 513	org/apache/a/a/a/f/t
    //   604: astore 8
    //   606: goto -110 -> 496
    //   609: aload 17
    //   611: ldc2_w 530
    //   614: invokevirtual 535	org/apache/a/a/a/c:skip	(J)J
    //   617: pop2
    //   618: aload 17
    //   620: aload 10
    //   622: iconst_0
    //   623: bipush 20
    //   625: invokevirtual 539	org/apache/a/a/a/c:read	([BII)I
    //   628: pop
    //   629: ldc 37
    //   631: new 122	java/lang/StringBuilder
    //   634: dup
    //   635: invokespecial 123	java/lang/StringBuilder:<init>	()V
    //   638: ldc_w 541
    //   641: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   644: aload 10
    //   646: invokestatic 526	com/paloaltonetworks/globalprotect/mdm/w:a	([B)Ljava/lang/String;
    //   649: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   652: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   655: invokestatic 153	com/paloaltonetworks/globalprotect/Log:LOG_DEBUG	(Ljava/lang/String;Ljava/lang/String;)V
    //   658: goto -129 -> 529
    //   661: astore 8
    //   663: aload 8
    //   665: invokevirtual 108	java/lang/Exception:printStackTrace	()V
    //   668: goto -134 -> 534
    //   671: astore 8
    //   673: ldc 37
    //   675: new 122	java/lang/StringBuilder
    //   678: dup
    //   679: invokespecial 123	java/lang/StringBuilder:<init>	()V
    //   682: ldc_w 543
    //   685: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   688: aload 8
    //   690: invokevirtual 255	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   693: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   696: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   699: invokestatic 192	com/paloaltonetworks/globalprotect/Log:LOG_INFO	(Ljava/lang/String;Ljava/lang/String;)V
    //   702: goto -575 -> 127
    //   705: ldc_w 545
    //   708: invokevirtual 241	java/lang/String:getBytes	()[B
    //   711: iconst_0
    //   712: aload 10
    //   714: iconst_0
    //   715: bipush 20
    //   717: invokestatic 551	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   720: goto -186 -> 534
    //   723: new 122	java/lang/StringBuilder
    //   726: dup
    //   727: invokespecial 123	java/lang/StringBuilder:<init>	()V
    //   730: ldc_w 693
    //   733: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   736: ldc_w 695
    //   739: bipush 6
    //   741: anewarray 4	java/lang/Object
    //   744: dup
    //   745: iconst_0
    //   746: lload 5
    //   748: invokestatic 700	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   751: aastore
    //   752: dup
    //   753: iconst_1
    //   754: aload 15
    //   756: getfield 458	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   759: aastore
    //   760: dup
    //   761: iconst_2
    //   762: aload 9
    //   764: aload 15
    //   766: invokevirtual 704	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   769: aastore
    //   770: dup
    //   771: iconst_3
    //   772: aload 16
    //   774: getfield 475	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   777: aastore
    //   778: dup
    //   779: iconst_4
    //   780: aload 16
    //   782: getfield 707	android/content/pm/PackageInfo:versionCode	I
    //   785: invokestatic 710	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   788: aastore
    //   789: dup
    //   790: iconst_5
    //   791: aload 8
    //   793: aastore
    //   794: invokestatic 410	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   797: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   800: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   803: astore 8
    //   805: new 122	java/lang/StringBuilder
    //   808: dup
    //   809: invokespecial 123	java/lang/StringBuilder:<init>	()V
    //   812: aload 8
    //   814: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   817: ldc_w 712
    //   820: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   823: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   826: astore 8
    //   828: iload_2
    //   829: ifne +38 -> 867
    //   832: new 122	java/lang/StringBuilder
    //   835: dup
    //   836: invokespecial 123	java/lang/StringBuilder:<init>	()V
    //   839: aload 7
    //   841: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   844: aload 8
    //   846: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   849: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   852: astore 7
    //   854: goto -727 -> 127
    //   857: astore 13
    //   859: goto -772 -> 87
    //   862: astore 8
    //   864: goto -191 -> 673
    //   867: goto -13 -> 854
    //   870: iconst_0
    //   871: istore_3
    //   872: goto -355 -> 517
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	875	0	this	w
    //   126	318	1	i1	int
    //   244	585	2	i2	int
    //   516	356	3	i3	int
    //   114	316	4	i4	int
    //   228	519	5	l1	long
    //   21	167	7	str1	String
    //   190	650	7	localException1	Exception
    //   852	1	7	str2	String
    //   16	589	8	localObject1	Object
    //   661	3	8	localException2	Exception
    //   671	121	8	localException3	Exception
    //   803	42	8	str3	String
    //   862	1	8	localException4	Exception
    //   6	757	9	localPackageManager	PackageManager
    //   28	685	10	arrayOfByte	byte[]
    //   41	182	11	localMessageDigest1	MessageDigest
    //   48	536	12	localMessageDigest2	MessageDigest
    //   53	509	13	localObject2	Object
    //   857	1	13	localException5	Exception
    //   123	15	14	localIterator	Iterator
    //   147	618	15	localApplicationInfo	ApplicationInfo
    //   241	540	16	localPackageInfo	PackageInfo
    //   371	248	17	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   36	50	190	java/lang/Exception
    //   448	496	661	java/lang/Exception
    //   501	515	661	java/lang/Exception
    //   521	529	661	java/lang/Exception
    //   529	534	661	java/lang/Exception
    //   596	606	661	java/lang/Exception
    //   609	658	661	java/lang/Exception
    //   534	593	671	java/lang/Exception
    //   663	668	671	java/lang/Exception
    //   705	720	671	java/lang/Exception
    //   81	87	857	java/lang/Exception
    //   230	243	862	java/lang/Exception
    //   245	258	862	java/lang/Exception
    //   260	277	862	java/lang/Exception
    //   282	326	862	java/lang/Exception
    //   390	440	862	java/lang/Exception
  }
  
  private int z()
  {
    DevicePolicyManager localDevicePolicyManager = (DevicePolicyManager)this.m.getSystemService("device_policy");
    try
    {
      int i1 = localDevicePolicyManager.getStorageEncryptionStatus();
      return i1;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }
  
  public String b()
  {
    this.p = null;
    this.p = "<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">";
    this.p += "\n<plist version=\"1.0\">";
    this.p += String.format("\n<dict>\n\t<key>CommandUUID</key>\n\t<string>%s</string>\n", new Object[] { "5123c579-f2e6-7a72-0000-0036" });
    this.p += c();
    this.p += k();
    this.p += y();
    this.p += String.format("\n<key>Status</key>\n\t<string>Acknowledged</string>\n\t<key>UDID</key>\n\t<string>%s</string>\n</dict>", new Object[] { t.c().V() });
    Log.LOG_INFO("PanMDMHipReport", "hipReport=\n" + this.p);
    try
    {
      PrintWriter localPrintWriter = new PrintWriter(t.c().K);
      localPrintWriter.write(this.p);
      localPrintWriter.close();
      return this.p;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.LOG_INFO("PanMDMHipReport", localException.toString());
      }
    }
  }
  
  public String c()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("<key>QueryResponses</key>\n<dict>");
    String str4 = l();
    String str1;
    String str5;
    String str6;
    String str7;
    String str8;
    String str2;
    label67:
    String str9;
    String str10;
    String str11;
    String str12;
    String str13;
    String str14;
    String str15;
    if (m())
    {
      str1 = "true";
      str5 = n();
      str6 = q();
      str7 = r();
      str8 = s();
      if (!t()) {
        break label225;
      }
      str2 = "true";
      str9 = p();
      str10 = o();
      str11 = v();
      str12 = w();
      str13 = x();
      str14 = f();
      str15 = u();
      if (!d()) {
        break label232;
      }
    }
    label225:
    label232:
    for (String str3 = "phone";; str3 = "tablet")
    {
      return String.format("\n\t\t<key>BluetoothMAC</key>\n\t\t<string>%s</string>\n\t\t<key>DataRoamingEnabled</key>\n\t\t<%s/>\n\t\t<key>DeviceName</key>\n\t\t<string>%s</string>\n\t\t<key>ICCID</key>\n\t\t<string>%s</string>\n\t\t<key>PhoneNumber</key>\n\t\t<string>%s</string>\n\t\t<key>IMEI</key>\n\t\t<string>%s</string>\n\t\t<key>IsRoaming</key>\n\t\t<%s/>\n\t\t<key>Manufacturer</key>\n\t\t<string>%s</string>\n\t\t<key>Model</key>\n\t\t<string>%s</string>\n\t\t<key>ProductName</key>\n\t\t<string>%s</string>\n\t\t<key>SerialNumber</key>\n\t\t<string>%s</string>\n\t\t<key>SubscriberCarrierNetwork</key>\n\t\t<string>%s</string>\n\t\t<key>WiFiMAC</key>\n\t\t<string>%s</string>\n\t\t<key>OSVersion</key>\n\t\t<string>%s</string>\n\t\t<key>ModelName</key>\n\t\t<string>%s</string>\n\t</dict>\n", new Object[] { str4, str1, str5, str6, str7, str8, str2, str9, str10, str11, str12, str13, str14, str15, str3 });
      str1 = "false";
      break;
      str2 = "false";
      break label67;
    }
  }
  
  public String g()
  {
    byte[] arrayOfByte = null;
    String str = z.e();
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-256");
      str = str + this.p;
      if (str != null) {
        arrayOfByte = str.getBytes();
      }
      localMessageDigest.update(arrayOfByte);
      this.r = b(localMessageDigest.digest());
      return this.r;
    }
    catch (Exception localException)
    {
      Log.LOG_INFO("PanMDMHipReport", "Can not get MessageDigest's SHA-256 instance " + localException.getMessage());
    }
    return null;
  }
  
  public String h()
  {
    return this.s;
  }
  
  public String i()
  {
    return this.q;
  }
  
  public String k()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("<key>SecurityInfo</key>\n<dict>");
    int i1 = z();
    String str1;
    String str2;
    if (A())
    {
      str1 = "true";
      if (!B()) {
        break label100;
      }
      str2 = "true";
      label42:
      if (!C()) {
        break label107;
      }
    }
    label100:
    label107:
    for (String str3 = "true";; str3 = "false")
    {
      return String.format("\n\t<key>HardwareEncryptionCaps</key>\n\t<integer>%d</integer>\n\t<key>PasscodeCompliant</key>\n\t<%s/>\n\t<key>PasscodeCompliantWithProfiles</key>\n\t<%s/>\n\t<key>PasscodePresent</key>\n\t<%s/>\n</dict>", new Object[] { Integer.valueOf(i1), str1, str2, str3 });
      str1 = "false";
      break;
      str2 = "false";
      break label42;
    }
  }
}
