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
    catch (Exception paramString) {}
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
      if (i < 3) {
        try
        {
          List localList = paramContext.getPackageManager().getInstalledPackages(8);
          return localList;
        }
        catch (RuntimeException localRuntimeException) {}
      }
      try
      {
        Thread.sleep(100L);
        i += 1;
        continue;
        return new ArrayList();
      }
      catch (Exception localException)
      {
        for (;;) {}
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
    boolean bool2 = false;
    Iterator localIterator = getActivityManager().getRecentTasks(20, 1).iterator();
    String str;
    do
    {
      bool1 = bool2;
      if (!localIterator.hasNext()) {
        break;
      }
      str = ((ActivityManager.RecentTaskInfo)localIterator.next()).baseIntent.getComponent().getPackageName();
      Utils.log(this.isTest, "recent: " + str);
    } while ((str == null) || (!str.equals(paramString)));
    boolean bool1 = true;
    return bool1;
  }
  
  public boolean appRunning(String paramString)
  {
    boolean bool2 = false;
    Iterator localIterator = getActivityManager().getRunningAppProcesses().iterator();
    for (;;)
    {
      boolean bool1 = bool2;
      Object localObject;
      if (localIterator.hasNext())
      {
        localObject = (ActivityManager.RunningAppProcessInfo)localIterator.next();
        if (((ActivityManager.RunningAppProcessInfo)localObject).pkgList.length != 1) {
          break label77;
        }
        localObject = localObject.pkgList[0];
      }
      while ((localObject != null) && (((String)localObject).equals(paramString)))
      {
        bool1 = true;
        return bool1;
        label77:
        String str = ((ActivityManager.RunningAppProcessInfo)localObject).processName;
        localObject = str;
        if (str.indexOf(":") > 0) {
          localObject = str.split(":")[0];
        }
      }
    }
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
    Object localObject = getLocalAppsPkgInfo(this.mContext);
    int i = 0;
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      String str = localPackageInfo.applicationInfo.packageName;
      if ((localPackageInfo.applicationInfo.flags & 0x1) != 0) {}
      for (int j = 1;; j = 0)
      {
        if (paramInt != 0) {
          break label84;
        }
        i += 1;
        break;
      }
      label84:
      if (paramInt == 1)
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
    Object localObject = getLocalAppsPkgInfo(this.mContext);
    int i = 0;
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = ((PackageInfo)((Iterator)localObject).next()).applicationInfo.packageName;
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
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: aconst_null
    //   6: astore_2
    //   7: new 382	java/io/RandomAccessFile
    //   10: dup
    //   11: ldc_w 384
    //   14: ldc_w 386
    //   17: invokespecial 389	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   20: astore_1
    //   21: aload_1
    //   22: invokevirtual 392	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   25: astore_3
    //   26: aload_3
    //   27: invokevirtual 395	java/lang/String:trim	()Ljava/lang/String;
    //   30: aload_3
    //   31: ldc -20
    //   33: invokevirtual 240	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   36: iconst_1
    //   37: iadd
    //   38: aload_3
    //   39: ldc_w 397
    //   42: invokevirtual 240	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   45: invokevirtual 401	java/lang/String:substring	(II)Ljava/lang/String;
    //   48: invokevirtual 395	java/lang/String:trim	()Ljava/lang/String;
    //   51: astore_3
    //   52: aload_3
    //   53: astore_2
    //   54: aload_1
    //   55: invokevirtual 404	java/io/RandomAccessFile:close	()V
    //   58: aload_2
    //   59: invokestatic 410	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   62: ldc2_w 411
    //   65: ldiv
    //   66: lreturn
    //   67: astore_1
    //   68: goto -10 -> 58
    //   71: astore_1
    //   72: aload 4
    //   74: astore_1
    //   75: aload_1
    //   76: invokevirtual 404	java/io/RandomAccessFile:close	()V
    //   79: goto -21 -> 58
    //   82: astore_1
    //   83: goto -25 -> 58
    //   86: astore_1
    //   87: aload_3
    //   88: astore_2
    //   89: aload_2
    //   90: invokevirtual 404	java/io/RandomAccessFile:close	()V
    //   93: aload_1
    //   94: athrow
    //   95: astore_2
    //   96: goto -3 -> 93
    //   99: astore_3
    //   100: aload_1
    //   101: astore_2
    //   102: aload_3
    //   103: astore_1
    //   104: goto -15 -> 89
    //   107: astore_3
    //   108: goto -33 -> 75
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	111	0	this	Sys
    //   20	35	1	localRandomAccessFile	java.io.RandomAccessFile
    //   67	1	1	localIOException1	java.io.IOException
    //   71	1	1	localIOException2	java.io.IOException
    //   74	2	1	localObject1	Object
    //   82	1	1	localIOException3	java.io.IOException
    //   86	15	1	localObject2	Object
    //   103	1	1	localObject3	Object
    //   6	84	2	str1	String
    //   95	1	2	localIOException4	java.io.IOException
    //   101	1	2	localObject4	Object
    //   1	87	3	str2	String
    //   99	4	3	localObject5	Object
    //   107	1	3	localIOException5	java.io.IOException
    //   3	70	4	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   54	58	67	java/io/IOException
    //   7	21	71	java/io/IOException
    //   75	79	82	java/io/IOException
    //   7	21	86	finally
    //   89	93	95	java/io/IOException
    //   21	52	99	finally
    //   21	52	107	java/io/IOException
  }
  
  public boolean hasPermission(String paramString)
  {
    boolean bool2 = false;
    try
    {
      Object localObject = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 4096);
      boolean bool1 = bool2;
      int j;
      int i;
      if (((PackageInfo)localObject).requestedPermissions != null)
      {
        localObject = ((PackageInfo)localObject).requestedPermissions;
        j = localObject.length;
        i = 0;
      }
      for (;;)
      {
        bool1 = bool2;
        if (i < j)
        {
          bool1 = localObject[i].equals(paramString);
          if (bool1) {
            bool1 = true;
          }
        }
        else
        {
          return bool1;
        }
        i += 1;
      }
      return false;
    }
    catch (Exception paramString) {}
  }
  
  public boolean isRooted()
  {
    String str = Build.TAGS;
    if ((str != null) && (str.contains("test-keys"))) {}
    for (;;)
    {
      return true;
      try
      {
        boolean bool = new File("/system/app/Superuser.apk").exists();
        if ((bool) || (canExecuteCommand("/system/xbin/which su")) || (canExecuteCommand("/system/bin/which su")) || (canExecuteCommand("which su"))) {}
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
    return false;
  }
}
