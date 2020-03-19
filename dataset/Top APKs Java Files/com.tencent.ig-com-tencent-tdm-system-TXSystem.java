package com.tencent.tdm.system;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.support.v4.content.b;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class TXSystem
{
  private static final String DefaultUUID = "UUID";
  private static TXSystem instance = null;
  private static final String tag = "TXSystem";
  private long m_szMemeryAvail = 0L;
  private long m_szMemoryTotal = 0L;
  private int m_szScreenHeight = 0;
  private int m_szScreenwidth = 0;
  private long m_szSpaceAvail = 0L;
  private long m_szSpaceTotal = 0L;
  
  private TXSystem() {}
  
  @TargetApi(16)
  private void GetMemoryInfo(Context paramContext)
  {
    for (;;)
    {
      try
      {
        paramContext = (ActivityManager)paramContext.getSystemService("activity");
        if (paramContext == null) {
          return;
        }
        ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
        paramContext.getMemoryInfo(localMemoryInfo);
        if (Build.VERSION.SDK_INT >= 16)
        {
          this.m_szMemoryTotal = (localMemoryInfo.totalMem >> 20);
          this.m_szMemeryAvail = (localMemoryInfo.availMem >> 20);
          return;
        }
      }
      catch (Exception paramContext)
      {
        TXLog.e("TXSystem", "GetMemoryInfo failed: " + paramContext.toString());
        return;
      }
      this.m_szMemoryTotal = 0L;
    }
  }
  
  @TargetApi(17)
  private void GetScreenSize(Context paramContext)
  {
    float f1;
    float f2;
    for (;;)
    {
      DisplayMetrics localDisplayMetrics;
      try
      {
        paramContext = (WindowManager)paramContext.getSystemService("window");
        if (paramContext == null) {
          return;
        }
        localDisplayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 17)
        {
          paramContext.getDefaultDisplay().getRealMetrics(localDisplayMetrics);
          f1 = localDisplayMetrics.widthPixels;
          f2 = localDisplayMetrics.heightPixels;
          if (f1 <= f2) {
            break;
          }
          this.m_szScreenHeight = ((int)f1);
          this.m_szScreenwidth = ((int)f2);
          return;
        }
      }
      catch (Exception paramContext)
      {
        TXLog.e("TXSystem", "get GetScreenSize failed: " + paramContext.toString());
        return;
      }
      paramContext.getDefaultDisplay().getMetrics(localDisplayMetrics);
    }
    this.m_szScreenHeight = ((int)f2);
    this.m_szScreenwidth = ((int)f1);
  }
  
  @TargetApi(18)
  private void GetSpaceInfo()
  {
    for (;;)
    {
      try
      {
        Object localObject = Environment.getExternalStorageDirectory();
        if (localObject == null) {
          return;
        }
        localObject = new StatFs(((File)localObject).getPath());
        if (Build.VERSION.SDK_INT >= 18)
        {
          l3 = ((StatFs)localObject).getBlockSizeLong();
          l2 = ((StatFs)localObject).getBlockCountLong();
          l1 = ((StatFs)localObject).getAvailableBlocksLong();
          this.m_szSpaceTotal = (l2 * l3 >> 20);
          this.m_szSpaceAvail = (l1 * l3 >> 20);
          return;
        }
      }
      catch (Exception localException)
      {
        TXLog.e("TXSystem", "getStorage failed: " + localException.toString());
        return;
      }
      long l3 = localException.getBlockSize();
      long l2 = localException.getBlockCount();
      int i = localException.getAvailableBlocks();
      long l1 = i;
    }
  }
  
  private String getCommentFromBuffer(byte[] paramArrayOfByte, int paramInt)
  {
    byte[] arrayOfByte = new byte[4];
    byte[] tmp7_5 = arrayOfByte;
    tmp7_5[0] = 80;
    byte[] tmp12_7 = tmp7_5;
    tmp12_7[1] = 75;
    byte[] tmp17_12 = tmp12_7;
    tmp17_12[2] = 5;
    byte[] tmp22_17 = tmp17_12;
    tmp22_17[3] = 6;
    tmp22_17;
    int i = paramInt - 22;
    if (i >= 0)
    {
      j = 0;
      label40:
      if (j >= arrayOfByte.length) {
        break label132;
      }
      if (paramArrayOfByte[(i + j)] == arrayOfByte[j]) {}
    }
    label132:
    for (int j = 0;; j = 1)
    {
      if (j != 0)
      {
        j = paramArrayOfByte[(i + 20)];
        return new String(paramArrayOfByte, i + 22, Math.min(paramArrayOfByte[(i + 21)] * 256 + j, paramInt - i - 22));
        j += 1;
        break label40;
      }
      i -= 1;
      break;
      return "";
    }
  }
  
  public static TXSystem getInstance()
  {
    if (instance == null) {
      instance = new TXSystem();
    }
    return instance;
  }
  
  public String GetApkPath(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 0).sourceDir;
      Object localObject = paramContext;
      if (paramContext == null) {
        localObject = "";
      }
      return localObject;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = null;
      }
    }
  }
  
  public int GetAppList(Context paramContext, List<String> paramList)
  {
    paramContext = paramContext.getPackageManager();
    if (paramContext == null)
    {
      TXLog.e("TXSystem", "get PackageManager is null");
      return -1;
    }
    paramContext = paramContext.getInstalledPackages(0);
    if (paramContext == null)
    {
      TXLog.e("TXSystem", "get getInstalledApplications is null");
      return -1;
    }
    if (paramList == null) {
      return paramContext.size();
    }
    Iterator localIterator = paramContext.iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
        paramList.add(localPackageInfo.packageName);
      }
    }
    return paramContext.size();
  }
  
  public String GetAppVersion(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 1).versionName;
      Object localObject = paramContext;
      if (paramContext == null) {
        localObject = "Unknown";
      }
      return localObject;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        TXLog.e("TXSystem", "GetAppVersion Exception:" + paramContext);
        paramContext = null;
      }
    }
  }
  
  public long GetAvailMemory(Context paramContext)
  {
    GetMemoryInfo(paramContext);
    return this.m_szMemeryAvail;
  }
  
  public long GetAvailSpace()
  {
    GetSpaceInfo();
    return this.m_szSpaceAvail;
  }
  
  public String GetBundleId(Context paramContext)
  {
    Object localObject = null;
    try
    {
      paramContext = paramContext.getPackageName();
      localObject = paramContext;
      if (paramContext == null) {
        localObject = "Unknown";
      }
      return localObject;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        TXLog.e("TXSystem", "GetBundleId Exception:" + paramContext);
        paramContext = (Context)localObject;
      }
    }
  }
  
  @TargetApi(21)
  public String GetCPUName()
  {
    Object localObject8 = null;
    Object localObject5 = null;
    Object localObject7 = null;
    boolean bool2 = false;
    Object localObject1;
    boolean bool1;
    if (Build.VERSION.SDK_INT >= 21)
    {
      localObject1 = Build.SUPPORTED_ABIS;
      bool1 = bool2;
      if (localObject1 != null)
      {
        bool1 = bool2;
        if (localObject1.length > 0) {
          bool1 = localObject1[0].equalsIgnoreCase("x86");
        }
      }
    }
    for (;;)
    {
      try
      {
        localFileReader = new FileReader("/proc/cpuinfo");
        localBufferedReader = new BufferedReader(localFileReader);
        localObject6 = "";
        localObject4 = null;
        if (localObject6 == null) {
          break label320;
        }
        localObject1 = localObject4;
      }
      catch (Exception localException2)
      {
        FileReader localFileReader;
        BufferedReader localBufferedReader;
        Object localObject4;
        Object localObject2;
        Object localObject6 = null;
        continue;
      }
      try
      {
        if (((String)localObject6).contains("Hardware"))
        {
          localObject1 = localObject4;
          localObject5 = localObject6.split(":")[1];
          localObject1 = localObject5;
          localObject5 = localObject1;
          localObject1 = localObject4;
          localObject4 = localObject5;
          localObject5 = localObject4;
          localObject6 = localObject1;
        }
      }
      catch (Exception localException3)
      {
        continue;
        return localException3;
      }
      try
      {
        localBufferedReader.close();
        localObject5 = localObject4;
        localObject6 = localObject1;
        localFileReader.close();
      }
      catch (Exception localException1)
      {
        TXLog.e("TXSystem", "GetCPUName Exception:" + localException1);
        localObject2 = localObject6;
        localObject4 = localObject5;
        continue;
      }
      if (localObject4 == null)
      {
        if ((bool1) && (localObject1 != null))
        {
          return localObject1;
          bool1 = Build.CPU_ABI.equalsIgnoreCase("x86");
          continue;
          localObject5 = localObject4;
          localObject1 = localObject4;
          if (((String)localObject6).contains("model name"))
          {
            localObject1 = localObject4;
            localObject5 = localObject6.split(":")[1];
          }
          localObject1 = localObject5;
          localObject6 = localBufferedReader.readLine();
          localObject4 = localObject5;
          continue;
          localObject5 = localObject8;
          localObject6 = localObject1;
          TXLog.e("TXSystem", "GetCPUName, readLine Exception:" + localObject4);
          localObject4 = localObject7;
        }
        else
        {
          return "Unknown";
        }
      }
      else {
        label320:
        Object localObject3 = null;
      }
    }
  }
  
  public String GetCommentInfo(String paramString)
  {
    String str1 = "";
    String str2 = str1;
    try
    {
      paramString = new File(paramString);
      str2 = str1;
      int i = (int)paramString.length();
      str2 = str1;
      FileInputStream localFileInputStream = new FileInputStream(paramString);
      str2 = str1;
      byte[] arrayOfByte = new byte[Math.min(i, 128)];
      try
      {
        localFileInputStream.skip(i - arrayOfByte.length);
        i = localFileInputStream.read(arrayOfByte);
        paramString = str1;
        if (i > 0) {
          paramString = getCommentFromBuffer(arrayOfByte, i);
        }
      }
      catch (Exception paramString)
      {
        for (;;)
        {
          str2 = str1;
          paramString.printStackTrace();
          paramString = str1;
        }
      }
      str2 = paramString;
      localFileInputStream.close();
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return str2;
  }
  
  public String GetDeviceID(Context paramContext)
  {
    if (b.b(paramContext, "android.permission.READ_PHONE_STATE") != 0)
    {
      TXLog.e("TXSystem", "getDeviceID, Permission Denied. ");
      return "";
    }
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext == null) {
        return "";
      }
      paramContext = paramContext.getDeviceId();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      TXLog.e("TXSystem", "get DeviceID failed: " + paramContext.toString());
    }
    return "";
  }
  
  public String GetMacAddress(Context paramContext)
  {
    int i = 0;
    localObject2 = null;
    StringBuffer localStringBuffer = new StringBuffer();
    try
    {
      paramContext = NetworkInterface.getByName("eth1");
      localObject1 = paramContext;
      if (paramContext == null)
      {
        TXLog.w("TXSystem", "networkInterface eth1 is null");
        localObject1 = NetworkInterface.getByName("wlan0");
      }
      paramContext = localObject2;
      if (localObject1 != null)
      {
        paramContext = ((NetworkInterface)localObject1).getHardwareAddress();
        int j = paramContext.length;
        while (i < j)
        {
          localStringBuffer.append(String.format("%02X:", new Object[] { Byte.valueOf(paramContext[i]) }));
          i += 1;
        }
        if (localStringBuffer.length() > 0) {
          localStringBuffer.deleteCharAt(localStringBuffer.length() - 1);
        }
        paramContext = localStringBuffer.toString();
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        Object localObject1;
        TXLog.e("TXSystem", "GetMacAdress Exception:" + paramContext);
        paramContext = localObject2;
      }
    }
    localObject1 = paramContext;
    if (paramContext == null) {
      localObject1 = "Unknown";
    }
    return localObject1;
  }
  
  public boolean GetMetaBool(Context paramContext, String paramString)
  {
    try
    {
      boolean bool = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.getBoolean(paramString);
      return bool;
    }
    catch (Exception paramContext)
    {
      TXLog.e("TXSystem", "GetMetaBool exception:" + paramContext.toString());
    }
    return false;
  }
  
  public String GetMetaString(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.getString(paramString);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      TXLog.e("TXSystem", "GetMetaString exception:" + paramContext.toString());
    }
    return null;
  }
  
  public String GetModel()
  {
    String str2 = Build.MODEL;
    String str1 = str2;
    if (str2 == null) {
      str1 = "Unknown";
    }
    return str1;
  }
  
  public NetworkType GetNetworkType(Context paramContext)
  {
    for (;;)
    {
      try
      {
        paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
        if (paramContext == null)
        {
          TXLog.w("TXSystem", "NetworkStateChecker connManager is null");
          return NetworkType.NETWORK_UNKNOWN;
        }
        paramContext = paramContext.getActiveNetworkInfo();
        if ((paramContext == null) || (!paramContext.isConnected()))
        {
          TXLog.w("TXSystem", "NetworkStateChecker netInfo is null");
          return NetworkType.NETWORK_UNKNOWN;
        }
        if (1 == paramContext.getType()) {
          return NetworkType.NETWORK_WIFI;
        }
        if (paramContext.getType() == 0) {}
        switch (paramContext.getSubtype())
        {
        case 1: 
          return NetworkType.NETWORK_UNKNOWN;
        case 2: 
        case 4: 
        case 7: 
        case 11: 
          return NetworkType.NETWORK_2G;
        case 3: 
        case 5: 
        case 6: 
        case 8: 
        case 9: 
        case 10: 
        case 12: 
        case 14: 
        case 15: 
          return NetworkType.NETWORK_3G;
        case 13: 
          return NetworkType.NETWORK_4G;
          paramContext = NetworkType.NETWORK_UNKNOWN;
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        TXLog.w("TXSystem", "check Get exception:" + paramContext.toString());
        return NetworkType.NETWORK_UNKNOWN;
      }
    }
  }
  
  public int GetScreenHeight(Context paramContext)
  {
    if (this.m_szScreenHeight == 0) {
      GetScreenSize(paramContext);
    }
    return this.m_szScreenHeight;
  }
  
  public int GetScreenWidth(Context paramContext)
  {
    if (this.m_szScreenwidth == 0) {
      GetScreenSize(paramContext);
    }
    return this.m_szScreenwidth;
  }
  
  public String GetSimOperator(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if ((paramContext == null) || (5 != paramContext.getSimState())) {
      return "-1";
    }
    return paramContext.getSimOperator();
  }
  
  public String GetSysVersion()
  {
    String str2 = Build.VERSION.RELEASE;
    String str1 = str2;
    if (str2 == null) {
      str1 = "Unknown";
    }
    return str1;
  }
  
  public long GetTotalMemory(Context paramContext)
  {
    if (this.m_szMemoryTotal == 0L) {
      GetMemoryInfo(paramContext);
    }
    return this.m_szMemoryTotal;
  }
  
  public long GetTotalSpace()
  {
    if (this.m_szSpaceTotal == 0L) {
      GetSpaceInfo();
    }
    return this.m_szSpaceTotal;
  }
  
  public String GetUUID(Context paramContext)
  {
    Object localObject = GetDeviceID(paramContext);
    String str = Build.SERIAL;
    paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    StringBuilder localStringBuilder = new StringBuilder();
    if (localObject != null)
    {
      localStringBuilder.append("%");
      localStringBuilder.append((String)localObject);
    }
    if (str != null)
    {
      localStringBuilder.append("%");
      localStringBuilder.append(str);
    }
    if (paramContext != null)
    {
      localStringBuilder.append("%");
      localStringBuilder.append(paramContext);
    }
    paramContext = localStringBuilder.toString();
    if (paramContext.length() == 0) {
      return "UUID";
    }
    try
    {
      localObject = MessageDigest.getInstance("MD5");
      if (localObject == null) {
        return "UUID";
      }
      ((MessageDigest)localObject).update(paramContext.getBytes());
      paramContext = ((MessageDigest)localObject).digest();
      localObject = new StringBuilder();
      int i = 0;
      while (i < paramContext.length)
      {
        ((StringBuilder)localObject).append(Integer.toHexString(paramContext[i] & 0xFF));
        i += 1;
      }
      paramContext = ((StringBuilder)localObject).toString().toUpperCase(Locale.ENGLISH);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      TXLog.e("TXSystem", "GetUUID Exception:" + paramContext);
    }
    return "UUID";
  }
}
