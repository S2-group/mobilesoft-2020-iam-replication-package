package com.aduwant.ads.expr;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RecentTaskInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Sys
{
  static final String[] avAppsArray = { "com.antivirus", "com.avast.android.mobilesecurity", "com.antivirus.tablet", "com.zrgiu.antivirus", "com.cleanmaster.security", "com.avira.android", "com.qihoo.security", "com.drweb", "com.cleanmaster.mguard", "com.wsandroid.suite", "com.symantec.mobilesecurity", "and.anti", "com.lookout", "com.eset.ems2.gp", "com.kms.free", "com.bitdefender.antivirus", "com.trustgo.mobile.security", "com.lab4apps.antivirus", "com.psafe.msuite", "com.antivirus.antivirus", "com.pablosoftware.virusscan", "com.iobit.mobilecare", "com.nqmobile.antivirus20", "com.androidantivirus", "com.bitdefender.security", "com.zoner.android.antivirus", "com.drweb.pro", "jp.naver.lineantivirus.android", "com.androhelm.antivirus.free", "org.antivirus", "com.qihoo.msafe", "com.sophos.smsec", "com.cyou.security", "com.trendmicro.tmmspersonal" };
  boolean isTest = false;
  private ActivityManager mActivityManager = null;
  Context mContext;
  
  public Sys(Context paramContext, boolean paramBoolean)
  {
    this.mContext = paramContext;
  }
  
  private boolean canExecuteCommand(String paramString)
  {
    try
    {
      Runtime.getRuntime().exec(paramString);
      return true;
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    return false;
  }
  
  private ActivityManager getActivityManager()
  {
    if (this.mActivityManager == null) {
      this.mActivityManager = ((ActivityManager)this.mContext.getSystemService("activity"));
    }
    return this.mActivityManager;
  }
  
  private List<PackageInfo> getLocalAppsPkgInfo(Context paramContext)
  {
    int i = 0;
    for (;;)
    {
      if (i < 3) {}
      try
      {
        List localList = paramContext.getPackageManager().getInstalledPackages(8);
        return localList;
      }
      catch (RuntimeException localRuntimeException)
      {
        try
        {
          for (;;)
          {
            Thread.sleep(100L);
            i += 1;
            break;
            return new ArrayList();
            localRuntimeException = localRuntimeException;
          }
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    }
  }
  
  public boolean appInstalled(String paramString)
  {
    PackageManager localPackageManager = this.mContext.getPackageManager();
    try
    {
      localPackageManager.getPackageInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public boolean appRecentlyStarted(String paramString)
  {
    Iterator localIterator = getActivityManager().getRecentTasks(20, 1).iterator();
    while (localIterator.hasNext())
    {
      String str = ((ActivityManager.RecentTaskInfo)localIterator.next()).baseIntent.getComponent().getPackageName();
      boolean bool = this.isTest;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("recent: ");
      localStringBuilder.append(str);
      Utils.log(bool, localStringBuilder.toString());
      if ((str != null) && (str.equals(paramString))) {
        return true;
      }
    }
    return false;
  }
  
  public boolean appRunning(String paramString)
  {
    Iterator localIterator = getActivityManager().getRunningAppProcesses().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (ActivityManager.RunningAppProcessInfo)localIterator.next();
      if (((ActivityManager.RunningAppProcessInfo)localObject).pkgList.length == 1)
      {
        localObject = localObject.pkgList[0];
      }
      else
      {
        String str = ((ActivityManager.RunningAppProcessInfo)localObject).processName;
        localObject = str;
        if (str.indexOf(":") > 0) {
          localObject = str.split(":")[0];
        }
      }
      if ((localObject != null) && (((String)localObject).equals(paramString))) {
        return true;
      }
    }
    return false;
  }
  
  public boolean appsInstalled(String... paramVarArgs)
  {
    return getInstalledAppsCount(paramVarArgs) > 0;
  }
  
  public boolean avInstalled()
  {
    return getInstalledAVCount() > 0;
  }
  
  public boolean couldListTasks()
  {
    return hasPermission("android.permission.GET_TASKS");
  }
  
  public boolean externalStorageMounted()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public int getAllAppsCount(int paramInt)
  {
    Iterator localIterator = getLocalAppsPkgInfo(this.mContext).iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      String str = localPackageInfo.applicationInfo.packageName;
      int j;
      if ((localPackageInfo.applicationInfo.flags & 0x1) != 0) {
        j = 1;
      } else {
        j = 0;
      }
      if (paramInt == 0) {
        i += 1;
      } else if (paramInt == 1)
      {
        if (j == 0) {
          i += 1;
        }
      }
      else if (j != 0) {
        i += 1;
      }
    }
    return i;
  }
  
  public int getAvailExternalStoragePercent()
  {
    long l2 = getTotalInternalStorageSize();
    double d = getAvailInternalStorageSize();
    long l1 = l2;
    if (l2 == 0L) {
      l1 = 1L;
    }
    return (int)(d * 100.0D / l1);
  }
  
  public long getAvailExternalStorageSize()
  {
    if (externalStorageMounted())
    {
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
      long l = localStatFs.getBlockSize();
      return localStatFs.getAvailableBlocks() * l / 1048576L;
    }
    return 0L;
  }
  
  public int getAvailInternalStoragePercent()
  {
    long l2 = getTotalInternalStorageSize();
    double d = getAvailInternalStorageSize();
    long l1 = l2;
    if (l2 == 0L) {
      l1 = 1L;
    }
    return (int)(d * 100.0D / l1);
  }
  
  public long getAvailInternalStorageSize()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getAvailableBlocks() * l / 1048576L;
  }
  
  public int getAvailRamPercent()
  {
    return (int)(getAvailRamSize() * 100.0D / getTotalRamSize());
  }
  
  public long getAvailRamSize()
  {
    ActivityManager localActivityManager = (ActivityManager)this.mContext.getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    localActivityManager.getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.availMem / 1048576L;
  }
  
  public int getBatteryLevel()
  {
    return this.mContext.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("level", -1);
  }
  
  public int getInstalledAVCount()
  {
    return getInstalledAppsCount(avAppsArray);
  }
  
  public int getInstalledAppsCount(String... paramVarArgs)
  {
    paramVarArgs = Arrays.asList(paramVarArgs);
    Iterator localIterator = getLocalAppsPkgInfo(this.mContext).iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      String str = ((PackageInfo)localIterator.next()).applicationInfo.packageName;
      Utils.log(this.isTest, str);
      if ((str != null) && (paramVarArgs.contains(str))) {
        i += 1;
      }
    }
    return i;
  }
  
  public long getTotalExternalStorageSize()
  {
    if (externalStorageMounted())
    {
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
      long l = localStatFs.getBlockSize();
      return localStatFs.getBlockCount() * l / 1048576L;
    }
    return 0L;
  }
  
  public long getTotalInternalStorageSize()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getBlockCount() * l / 1048576L;
  }
  
  /* Error */
  public long getTotalRamSize()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 382	java/io/RandomAccessFile
    //   5: dup
    //   6: ldc_w 384
    //   9: ldc_w 386
    //   12: invokespecial 389	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   15: astore_1
    //   16: aload_1
    //   17: invokevirtual 392	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   20: astore_3
    //   21: aload_3
    //   22: invokevirtual 395	java/lang/String:trim	()Ljava/lang/String;
    //   25: aload_3
    //   26: ldc -20
    //   28: invokevirtual 240	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   31: iconst_1
    //   32: iadd
    //   33: aload_3
    //   34: ldc_w 397
    //   37: invokevirtual 240	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   40: invokevirtual 401	java/lang/String:substring	(II)Ljava/lang/String;
    //   43: invokevirtual 395	java/lang/String:trim	()Ljava/lang/String;
    //   46: astore_3
    //   47: aload_1
    //   48: invokevirtual 404	java/io/RandomAccessFile:close	()V
    //   51: aload_3
    //   52: astore_1
    //   53: goto +24 -> 77
    //   56: astore_2
    //   57: goto +6 -> 63
    //   60: astore_2
    //   61: aconst_null
    //   62: astore_1
    //   63: aload_1
    //   64: invokevirtual 404	java/io/RandomAccessFile:close	()V
    //   67: aload_2
    //   68: athrow
    //   69: aconst_null
    //   70: astore_1
    //   71: aload_1
    //   72: invokevirtual 404	java/io/RandomAccessFile:close	()V
    //   75: aload_2
    //   76: astore_1
    //   77: aload_1
    //   78: invokestatic 410	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   81: ldc2_w 411
    //   84: ldiv
    //   85: lreturn
    //   86: astore_1
    //   87: goto -18 -> 69
    //   90: astore_3
    //   91: goto -20 -> 71
    //   94: astore_1
    //   95: goto -44 -> 51
    //   98: astore_1
    //   99: goto -32 -> 67
    //   102: astore_1
    //   103: aload_2
    //   104: astore_1
    //   105: goto -28 -> 77
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	108	0	this	Sys
    //   15	63	1	localObject1	Object
    //   86	1	1	localIOException1	java.io.IOException
    //   94	1	1	localIOException2	java.io.IOException
    //   98	1	1	localIOException3	java.io.IOException
    //   102	1	1	localIOException4	java.io.IOException
    //   104	1	1	localObject2	Object
    //   1	1	2	localObject3	Object
    //   56	1	2	localObject4	Object
    //   60	44	2	localObject5	Object
    //   20	32	3	str	String
    //   90	1	3	localIOException5	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   16	47	56	finally
    //   2	16	60	finally
    //   2	16	86	java/io/IOException
    //   16	47	90	java/io/IOException
    //   47	51	94	java/io/IOException
    //   63	67	98	java/io/IOException
    //   71	75	102	java/io/IOException
  }
  
  public boolean hasPermission(String paramString)
  {
    try
    {
      Object localObject = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 4096);
      if (((PackageInfo)localObject).requestedPermissions != null)
      {
        localObject = ((PackageInfo)localObject).requestedPermissions;
        int j = localObject.length;
        int i = 0;
        while (i < j)
        {
          boolean bool = localObject[i].equals(paramString);
          if (bool) {
            return true;
          }
          i += 1;
        }
      }
      return false;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public boolean isRooted()
  {
    String str = Build.TAGS;
    boolean bool2 = true;
    if ((str != null) && (str.contains("test-keys"))) {
      return true;
    }
    try
    {
      bool1 = new File("/system/app/Superuser.apk").exists();
      if (bool1) {
        return true;
      }
    }
    catch (Exception localException)
    {
      boolean bool1;
      for (;;) {}
    }
    bool1 = bool2;
    if (!canExecuteCommand("/system/xbin/which su"))
    {
      bool1 = bool2;
      if (!canExecuteCommand("/system/bin/which su"))
      {
        if (canExecuteCommand("which su")) {
          return true;
        }
        bool1 = false;
      }
    }
    return bool1;
  }
}
