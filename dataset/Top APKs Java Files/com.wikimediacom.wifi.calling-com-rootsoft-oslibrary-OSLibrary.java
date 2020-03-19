package com.rootsoft.oslibrary;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RecentTaskInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.PowerManager;
import android.os.RecoverySystem;
import android.os.StatFs;
import android.util.DisplayMetrics;
import android.util.Log;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.Author;
import anywheresoftware.b4a.BA.ShortName;
import anywheresoftware.b4a.BA.Version;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

@BA.Author("XverhelstX")
@BA.ShortName("OperatingSystem")
@BA.Version(1.4F)
public class OSLibrary
{
  private String BatteryInfo;
  public String OS;
  private List<ApplicationInfo> appList;
  private BA ba;
  private String eventName;
  private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      int i = paramAnonymousIntent.getIntExtra("level", 0);
      OSLibrary.this.BatteryInfo = (String.valueOf(i) + "%");
    }
  };
  
  public OSLibrary() {}
  
  public void BatteryReceiver()
  {
    this.ba.activity.registerReceiver(this.mBatInfoReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
  }
  
  public void CountDownTimer(long paramLong)
  {
    new CountDownTimer(paramLong, 1000L)
    {
      public void onFinish()
      {
        OSLibrary.this.OS = "Operating System Library Rocks! :)";
        OSLibrary.this.ba.raiseEvent(this, OSLibrary.this.eventName + "_onfinish", new Object[] { OSLibrary.this.OS });
      }
      
      public void onTick(long paramAnonymousLong)
      {
        OSLibrary.this.ba.raiseEvent(this, OSLibrary.this.eventName + "_ontick", new Object[] { Long.valueOf(paramAnonymousLong) });
      }
    }.start();
  }
  
  public void Initialize(BA paramBA, String paramString)
  {
    this.ba = paramBA;
    this.eventName = paramString.toLowerCase(BA.cul);
  }
  
  public void PackagesInfo()
  {
    this.appList = BA.applicationContext.getPackageManager().getInstalledApplications(8192);
  }
  
  public String ReadCPUinfo()
  {
    String str1 = "";
    str2 = str1;
    try
    {
      InputStream localInputStream = new ProcessBuilder(new String[] { "/system/bin/cat", "/proc/cpuinfo" }).start().getInputStream();
      str2 = str1;
      byte[] arrayOfByte = new byte['Ѐ'];
      for (;;)
      {
        str2 = str1;
        if (localInputStream.read(arrayOfByte) == -1)
        {
          str2 = str1;
          localInputStream.close();
          return str1;
        }
        str2 = str1;
        System.out.println(new String(arrayOfByte));
        str2 = str1;
        str1 = str1 + new String(arrayOfByte);
      }
      return str2;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }
  
  public List<ActivityManager.RunningAppProcessInfo> RunningAppProcessInfo(List<String> paramList1, List<Integer> paramList, List<String> paramList2)
  {
    List localList = ((ActivityManager)BA.applicationContext.getSystemService("activity")).getRunningAppProcesses();
    Iterator localIterator = localList.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localList;
      }
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
      paramList1.add(localRunningAppProcessInfo.processName);
      paramList.add(Integer.valueOf(localRunningAppProcessInfo.pid));
      paramList2.add(localRunningAppProcessInfo.processName + ": " + localRunningAppProcessInfo.pid);
      Log.v("B4A", localRunningAppProcessInfo.processName + " : " + localRunningAppProcessInfo.pid);
    }
  }
  
  public List<ActivityManager.RunningServiceInfo> RunningServiceInfo(int paramInt, List<String> paramList1, List<Integer> paramList, List<String> paramList2)
  {
    List localList = ((ActivityManager)BA.applicationContext.getSystemService("activity")).getRunningServices(paramInt);
    Iterator localIterator = localList.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localList;
      }
      ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)localIterator.next();
      paramList1.add(localRunningServiceInfo.process);
      paramList.add(Integer.valueOf(localRunningServiceInfo.pid));
      paramList2.add(localRunningServiceInfo.process + ": " + localRunningServiceInfo.pid);
      Log.v("B4A", localRunningServiceInfo.process + " : " + localRunningServiceInfo.pid);
    }
  }
  
  public List<ActivityManager.RunningTaskInfo> RunningTaskInfo(int paramInt, List<Integer> paramList1, List<Integer> paramList2, List<Integer> paramList3)
  {
    List localList = ((ActivityManager)BA.applicationContext.getSystemService("activity")).getRunningTasks(paramInt);
    Iterator localIterator = localList.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localList;
      }
      ActivityManager.RunningTaskInfo localRunningTaskInfo = (ActivityManager.RunningTaskInfo)localIterator.next();
      paramList1.add(Integer.valueOf(localRunningTaskInfo.id));
      paramList2.add(Integer.valueOf(localRunningTaskInfo.numActivities));
      paramList3.add(Integer.valueOf(localRunningTaskInfo.numRunning));
      Log.v("B4A", localRunningTaskInfo.numActivities + " : " + localRunningTaskInfo.id);
    }
  }
  
  /* Error */
  public float calculateCPUusage()
  {
    // Byte code:
    //   0: new 275	java/io/RandomAccessFile
    //   3: dup
    //   4: ldc_w 277
    //   7: ldc_w 279
    //   10: invokespecial 282	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   13: astore 19
    //   15: aload 19
    //   17: invokevirtual 285	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   20: ldc_w 287
    //   23: invokevirtual 291	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   26: astore 20
    //   28: aload 20
    //   30: iconst_5
    //   31: aaload
    //   32: invokestatic 297	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   35: lstore_1
    //   36: aload 20
    //   38: iconst_2
    //   39: aaload
    //   40: invokestatic 297	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   43: lstore_3
    //   44: aload 20
    //   46: iconst_3
    //   47: aaload
    //   48: invokestatic 297	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   51: lstore 5
    //   53: aload 20
    //   55: iconst_4
    //   56: aaload
    //   57: invokestatic 297	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   60: lstore 7
    //   62: aload 20
    //   64: bipush 6
    //   66: aaload
    //   67: invokestatic 297	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   70: lstore 9
    //   72: aload 20
    //   74: bipush 7
    //   76: aaload
    //   77: invokestatic 297	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   80: lstore 11
    //   82: aload 20
    //   84: bipush 8
    //   86: aaload
    //   87: invokestatic 297	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   90: lstore 13
    //   92: lload_3
    //   93: lload 5
    //   95: ladd
    //   96: lload 7
    //   98: ladd
    //   99: lload 9
    //   101: ladd
    //   102: lload 11
    //   104: ladd
    //   105: lload 13
    //   107: ladd
    //   108: lstore_3
    //   109: ldc2_w 298
    //   112: invokestatic 304	java/lang/Thread:sleep	(J)V
    //   115: aload 19
    //   117: lconst_0
    //   118: invokevirtual 307	java/io/RandomAccessFile:seek	(J)V
    //   121: aload 19
    //   123: invokevirtual 285	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   126: astore 20
    //   128: aload 19
    //   130: invokevirtual 308	java/io/RandomAccessFile:close	()V
    //   133: aload 20
    //   135: ldc_w 287
    //   138: invokevirtual 291	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   141: astore 19
    //   143: aload 19
    //   145: iconst_5
    //   146: aaload
    //   147: invokestatic 297	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   150: lstore 5
    //   152: aload 19
    //   154: iconst_2
    //   155: aaload
    //   156: invokestatic 297	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   159: lstore 7
    //   161: aload 19
    //   163: iconst_3
    //   164: aaload
    //   165: invokestatic 297	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   168: lstore 9
    //   170: aload 19
    //   172: iconst_4
    //   173: aaload
    //   174: invokestatic 297	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   177: lstore 11
    //   179: aload 19
    //   181: bipush 6
    //   183: aaload
    //   184: invokestatic 297	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   187: lstore 13
    //   189: aload 19
    //   191: bipush 7
    //   193: aaload
    //   194: invokestatic 297	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   197: lstore 15
    //   199: aload 19
    //   201: bipush 8
    //   203: aaload
    //   204: invokestatic 297	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   207: lstore 17
    //   209: lload 7
    //   211: lload 9
    //   213: ladd
    //   214: lload 11
    //   216: ladd
    //   217: lload 13
    //   219: ladd
    //   220: lload 15
    //   222: ladd
    //   223: lload 17
    //   225: ladd
    //   226: lstore 7
    //   228: lload 7
    //   230: lload_3
    //   231: lsub
    //   232: l2f
    //   233: lload 7
    //   235: lload 5
    //   237: ladd
    //   238: lload_3
    //   239: lload_1
    //   240: ladd
    //   241: lsub
    //   242: l2f
    //   243: fdiv
    //   244: freturn
    //   245: astore 19
    //   247: aload 19
    //   249: invokevirtual 174	java/io/IOException:printStackTrace	()V
    //   252: fconst_0
    //   253: freturn
    //   254: astore 20
    //   256: goto -141 -> 115
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	259	0	this	OSLibrary
    //   35	205	1	l1	long
    //   43	196	3	l2	long
    //   51	185	5	l3	long
    //   60	174	7	l4	long
    //   70	142	9	l5	long
    //   80	135	11	l6	long
    //   90	128	13	l7	long
    //   197	24	15	l8	long
    //   207	17	17	l9	long
    //   13	187	19	localObject1	Object
    //   245	3	19	localIOException	IOException
    //   26	108	20	localObject2	Object
    //   254	1	20	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   0	92	245	java/io/IOException
    //   109	115	245	java/io/IOException
    //   115	209	245	java/io/IOException
    //   109	115	254	java/lang/Exception
  }
  
  public float density()
  {
    return BA.applicationContext.getResources().getDisplayMetrics().density;
  }
  
  public int densityDpi()
  {
    return BA.applicationContext.getResources().getDisplayMetrics().densityDpi;
  }
  
  public boolean externalMemoryAvailable()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public String formatSize(long paramLong)
  {
    String str = null;
    long l = paramLong;
    if (paramLong >= 1024L)
    {
      str = "KiB";
      paramLong /= 1024L;
      l = paramLong;
      if (paramLong >= 1024L)
      {
        str = "MiB";
        l = paramLong / 1024L;
      }
    }
    StringBuilder localStringBuilder = new StringBuilder(Long.toString(l));
    int i = localStringBuilder.length() - 3;
    for (;;)
    {
      if (i <= 0)
      {
        if (str != null) {
          localStringBuilder.append(str);
        }
        return localStringBuilder.toString();
      }
      localStringBuilder.insert(i, ',');
      i -= 3;
    }
  }
  
  public int getAvailableBlocks(String paramString)
  {
    return new StatFs(paramString).getAvailableBlocks();
  }
  
  public long getAvailableExternalMemorySize()
  {
    if (externalMemoryAvailable())
    {
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
      long l = localStatFs.getBlockSize();
      return localStatFs.getAvailableBlocks() * l;
    }
    return -1L;
  }
  
  public long getAvailableInternalMemorySize()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getAvailableBlocks() * l;
  }
  
  public long getAvailableMemory()
  {
    return new ActivityManager.MemoryInfo().availMem;
  }
  
  public String getBatteryLevel()
  {
    return this.BatteryInfo;
  }
  
  public int getBlockCount(String paramString)
  {
    return new StatFs(paramString).getBlockCount();
  }
  
  public int getBlockSize(String paramString)
  {
    return new StatFs(paramString).getBlockSize();
  }
  
  public String getBoard()
  {
    return Build.BOARD;
  }
  
  public String getBootloader()
  {
    return Build.BOOTLOADER;
  }
  
  public String getBrand()
  {
    return Build.BRAND;
  }
  
  public String getCPUABI()
  {
    return Build.CPU_ABI;
  }
  
  public String getCPUABI2()
  {
    return Build.CPU_ABI2;
  }
  
  public String getCodename()
  {
    return Build.VERSION.CODENAME;
  }
  
  public String getDevice()
  {
    return Build.DEVICE;
  }
  
  public String getDisplay()
  {
    return Build.DISPLAY;
  }
  
  public long getElaspedCPUTime()
  {
    return android.os.Process.getElapsedCpuTime();
  }
  
  public String getFingerprint()
  {
    return Build.FINGERPRINT;
  }
  
  public int getFreeBlocks(String paramString)
  {
    return new StatFs(paramString).getFreeBlocks();
  }
  
  public int getGidForName(int paramInt)
  {
    return android.os.Process.getThreadPriority(paramInt);
  }
  
  public int getGidForName(String paramString)
  {
    return android.os.Process.getGidForName(paramString);
  }
  
  public String getHardware()
  {
    return Build.HARDWARE;
  }
  
  public String getHost()
  {
    return Build.HOST;
  }
  
  public String getID()
  {
    return Build.ID;
  }
  
  public String getIncremental()
  {
    return Build.VERSION.INCREMENTAL;
  }
  
  public String getInfo(String paramString)
  {
    if (paramString == null) {}
    ApplicationInfo localApplicationInfo;
    do
    {
      Iterator localIterator;
      while (!localIterator.hasNext())
      {
        return null;
        localIterator = this.appList.iterator();
      }
      localApplicationInfo = (ApplicationInfo)localIterator.next();
    } while (!paramString.equals(localApplicationInfo.processName));
    return localApplicationInfo.toString();
  }
  
  public String getManufacturer()
  {
    return Build.MANUFACTURER;
  }
  
  public String getModel()
  {
    return Build.MODEL;
  }
  
  public String getProduct()
  {
    return Build.PRODUCT;
  }
  
  public String getRadio()
  {
    return Build.RADIO;
  }
  
  public List<ActivityManager.RecentTaskInfo> getRecentTasks(int paramInt1, int paramInt2)
  {
    return ((ActivityManager)BA.applicationContext.getSystemService("activity")).getRecentTasks(paramInt1, paramInt2);
  }
  
  public String getRelease()
  {
    return Build.VERSION.RELEASE;
  }
  
  public List<ActivityManager.RunningAppProcessInfo> getRunningAppProcess()
  {
    return ((ActivityManager)BA.applicationContext.getSystemService("activity")).getRunningAppProcesses();
  }
  
  public List<ActivityManager.RunningAppProcessInfo> getRunningAppProcesses()
  {
    return ((ActivityManager)BA.applicationContext.getSystemService("activity")).getRunningAppProcesses();
  }
  
  public List<ActivityManager.RunningServiceInfo> getRunningServices(int paramInt)
  {
    return ((ActivityManager)BA.applicationContext.getSystemService("activity")).getRunningServices(paramInt);
  }
  
  public List<ActivityManager.RunningTaskInfo> getRunningTasks(int paramInt)
  {
    return ((ActivityManager)BA.applicationContext.getSystemService("activity")).getRunningTasks(paramInt);
  }
  
  public int getSDK()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public String getSerial()
  {
    return Build.SERIAL;
  }
  
  public String getTags()
  {
    return Build.TAGS;
  }
  
  public long getThreshold()
  {
    return new ActivityManager.MemoryInfo().threshold;
  }
  
  public long getTime()
  {
    return Build.TIME;
  }
  
  public long getTotalExternalMemorySize()
  {
    if (externalMemoryAvailable())
    {
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
      long l = localStatFs.getBlockSize();
      return localStatFs.getBlockCount() * l;
    }
    return -1L;
  }
  
  public long getTotalInternalMemorySize()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getBlockCount() * l;
  }
  
  public String getType()
  {
    return Build.TYPE;
  }
  
  public int getUidForName(String paramString)
  {
    return android.os.Process.getUidForName(paramString);
  }
  
  public String getUser()
  {
    return Build.USER;
  }
  
  public void goToSleep(long paramLong)
  {
    ((PowerManager)BA.applicationContext.getSystemService("power")).goToSleep(paramLong);
  }
  
  public int heightPixels()
  {
    return BA.applicationContext.getResources().getDisplayMetrics().heightPixels;
  }
  
  public void installPackage(File paramFile)
    throws IOException
  {
    RecoverySystem.installPackage(this.ba.context, paramFile);
  }
  
  public void killBackgroundProcesses(String paramString)
  {
    ((ActivityManager)BA.applicationContext.getSystemService("activity")).killBackgroundProcesses(paramString);
  }
  
  public void killProcess(int paramInt)
  {
    android.os.Process.killProcess(paramInt);
  }
  
  public int myPid()
  {
    return android.os.Process.myPid();
  }
  
  public int myTid()
  {
    return android.os.Process.myTid();
  }
  
  public int myUid()
  {
    return android.os.Process.myUid();
  }
  
  public float physicalScreenHeight()
  {
    return ydpi() * heightPixels();
  }
  
  public float physicalScreenWidth()
  {
    return xdpi() * widthPixels();
  }
  
  public void reboot(String paramString)
  {
    ((PowerManager)BA.applicationContext.getSystemService("power")).reboot(paramString);
  }
  
  public void rebootWipeUserData()
    throws IOException
  {
    RecoverySystem.rebootWipeUserData(this.ba.context);
  }
  
  public void restat(String paramString)
  {
    new StatFs(paramString).restat(paramString);
  }
  
  public float scaledDensity()
  {
    return BA.applicationContext.getResources().getDisplayMetrics().scaledDensity;
  }
  
  public void sendSignal(int paramInt1, int paramInt2)
  {
    android.os.Process.sendSignal(paramInt1, paramInt2);
  }
  
  public boolean setLowMemory()
  {
    return new ActivityManager.MemoryInfo().lowMemory;
  }
  
  public void setThreadPriority(int paramInt1, int paramInt2)
  {
    android.os.Process.setThreadPriority(paramInt1, paramInt2);
  }
  
  public boolean supportsProcesses()
  {
    return android.os.Process.supportsProcesses();
  }
  
  public void userActivity(long paramLong, boolean paramBoolean)
  {
    ((PowerManager)BA.applicationContext.getSystemService("power")).userActivity(paramLong, paramBoolean);
  }
  
  public int widthPixels()
  {
    return BA.applicationContext.getResources().getDisplayMetrics().widthPixels;
  }
  
  public float xdpi()
  {
    return BA.applicationContext.getResources().getDisplayMetrics().xdpi;
  }
  
  public float ydpi()
  {
    return BA.applicationContext.getResources().getDisplayMetrics().ydpi;
  }
}
