package com.kdanmobile.pdfreader.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.io.File;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"SimpleDateFormat"})
public final class SystemTool
{
  private SystemTool()
  {
    throw new UnsupportedOperationException("cannot be instantiated");
  }
  
  private static java.util.Date Date(java.util.Date paramDate)
  {
    return new java.sql.Date(paramDate.getTime());
  }
  
  public static boolean checkNet(Context paramContext)
  {
    return ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo() != null;
  }
  
  public static long formatDate(String paramString)
  {
    try
    {
      paramString = (java.sql.Date)Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(paramString));
      return paramString.getTime();
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString = null;
      }
    }
  }
  
  public static int gc(Context paramContext)
  {
    long l = getDeviceUsableMemory(paramContext);
    ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
    Object localObject1 = localActivityManager.getRunningServices(100);
    if (localObject1 != null)
    {
      localObject1 = ((List)localObject1).iterator();
      j = 0;
      for (;;)
      {
        i = j;
        if (!((Iterator)localObject1).hasNext()) {
          break;
        }
        ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)((Iterator)localObject1).next();
        if (localRunningServiceInfo.pid != Process.myPid()) {
          try
          {
            Process.killProcess(localRunningServiceInfo.pid);
            j += 1;
          }
          catch (Exception localException1)
          {
            localException1.getStackTrace();
          }
        }
      }
    }
    int i = 0;
    localObject1 = localActivityManager.getRunningAppProcesses();
    int j = i;
    if (localObject1 != null) {
      localObject1 = ((List)localObject1).iterator();
    }
    label280:
    for (;;)
    {
      j = i;
      if (((Iterator)localObject1).hasNext())
      {
        Object localObject2 = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject1).next();
        if (((ActivityManager.RunningAppProcessInfo)localObject2).importance > 200)
        {
          localObject2 = ((ActivityManager.RunningAppProcessInfo)localObject2).pkgList;
          int k = localObject2.length;
          j = 0;
          for (;;)
          {
            if (j >= k) {
              break label280;
            }
            String str = localObject2[j];
            LogUtil.print_d("pdf reader SystemTool", "======正在杀死包名：" + str);
            try
            {
              localActivityManager.killBackgroundProcesses(str);
              i += 1;
            }
            catch (Exception localException2)
            {
              for (;;)
              {
                localException2.getStackTrace();
              }
            }
            j += 1;
          }
        }
      }
      else
      {
        LogUtil.print_d("pdf reader SystemTool", "清理了" + (getDeviceUsableMemory(paramContext) - l) + "M内存");
        return j;
      }
    }
  }
  
  public static int getAppVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      throw new RuntimeException(SystemTool.class.getName() + "the application not found");
    }
  }
  
  public static String getAppVersionName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      throw new RuntimeException(SystemTool.class.getName() + "the application not found");
    }
  }
  
  public static String getDataTime()
  {
    return getDataTime("HH:mm");
  }
  
  public static String getDataTime(String paramString)
  {
    return new SimpleDateFormat(paramString).format(new java.util.Date());
  }
  
  public static int getDeviceUsableMemory(Context paramContext)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    paramContext.getMemoryInfo(localMemoryInfo);
    return (int)(localMemoryInfo.availMem / 1048576L);
  }
  
  public static String getPhoneIMEI(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
  }
  
  public static int getSDKVersion()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static String getSign(Context paramContext, String paramString)
  {
    try
    {
      paramContext = hexdigest(paramContext.getPackageManager().getPackageInfo(paramString, 64).signatures[0].toByteArray());
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      throw new RuntimeException(SystemTool.class.getName() + "the " + paramString + "'s application not found");
    }
  }
  
  public static String getSystemVersion()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static void goHome(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    localIntent.addFlags(270532608);
    paramContext.startActivity(localIntent);
  }
  
  private static String hexdigest(byte[] paramArrayOfByte)
  {
    int j = 0;
    char[] arrayOfChar = new char[16];
    char[] tmp10_8 = arrayOfChar;
    tmp10_8[0] = 48;
    char[] tmp16_10 = tmp10_8;
    tmp16_10[1] = 49;
    char[] tmp22_16 = tmp16_10;
    tmp22_16[2] = 50;
    char[] tmp28_22 = tmp22_16;
    tmp28_22[3] = 51;
    char[] tmp34_28 = tmp28_22;
    tmp34_28[4] = 52;
    char[] tmp40_34 = tmp34_28;
    tmp40_34[5] = 53;
    char[] tmp46_40 = tmp40_34;
    tmp46_40[6] = 54;
    char[] tmp53_46 = tmp46_40;
    tmp53_46[7] = 55;
    char[] tmp60_53 = tmp53_46;
    tmp60_53[8] = 56;
    char[] tmp67_60 = tmp60_53;
    tmp67_60[9] = 57;
    char[] tmp74_67 = tmp67_60;
    tmp74_67[10] = 97;
    char[] tmp81_74 = tmp74_67;
    tmp81_74[11] = 98;
    char[] tmp88_81 = tmp81_74;
    tmp88_81[12] = 99;
    char[] tmp95_88 = tmp88_81;
    tmp95_88[13] = 100;
    char[] tmp102_95 = tmp95_88;
    tmp102_95[14] = 101;
    char[] tmp109_102 = tmp102_95;
    tmp109_102[15] = 102;
    tmp109_102;
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramArrayOfByte);
      paramArrayOfByte = ((MessageDigest)localObject).digest();
      localObject = new char[32];
      int i = 0;
      for (;;)
      {
        if (i >= 16) {
          return new String((char[])localObject);
        }
        int k = paramArrayOfByte[i];
        localObject[j] = arrayOfChar[(k >>> 4 & 0xF)];
        j += 1;
        localObject[j] = arrayOfChar[(k & 0xF)];
        i += 1;
        j += 1;
      }
      return "";
    }
    catch (Exception paramArrayOfByte) {}
  }
  
  public static void hideKeyBoard(Activity paramActivity)
  {
    ((InputMethodManager)paramActivity.getSystemService("input_method")).hideSoftInputFromWindow(paramActivity.getCurrentFocus().getWindowToken(), 2);
  }
  
  public static void installApk(Context paramContext, File paramFile)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.setType("application/vnd.android.package-archive");
    localIntent.setData(Uri.fromFile(paramFile));
    localIntent.setDataAndType(Uri.fromFile(paramFile), "application/vnd.android.package-archive");
    localIntent.setFlags(268435456);
    paramContext.startActivity(localIntent);
  }
  
  public static boolean isApkInstall(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
  
  public static boolean isBackground(Context paramContext)
  {
    Object localObject = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (localObject == null) {}
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
    do
    {
      do
      {
        do
        {
          return false;
          localObject = ((List)localObject).iterator();
        } while (!((Iterator)localObject).hasNext());
        localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
      } while ((localRunningAppProcessInfo == null) || (paramContext == null));
    } while (!localRunningAppProcessInfo.processName.equals(paramContext.getPackageName()));
    if (localRunningAppProcessInfo.importance == 400) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean isSleeping(Context paramContext)
  {
    return ((KeyguardManager)paramContext.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
  }
  
  public static boolean isWiFi(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(1).getState();
    return NetworkInfo.State.CONNECTED == paramContext;
  }
  
  public static void sendSMS(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:"));
    localIntent.putExtra("sms_body", paramString);
    paramContext.startActivity(localIntent);
  }
  
  public boolean isRoot()
  {
    boolean bool1 = false;
    try
    {
      if (!new File("/system/bin/su").exists())
      {
        boolean bool2 = new File("/system/xbin/su").exists();
        if (!bool2) {}
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
}
