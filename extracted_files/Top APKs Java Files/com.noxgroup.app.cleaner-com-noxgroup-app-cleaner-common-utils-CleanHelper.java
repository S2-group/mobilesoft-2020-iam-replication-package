package com.noxgroup.app.cleaner.common.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AppOpsManager;
import android.app.admin.DevicePolicyManager;
import android.app.usage.StorageStats;
import android.app.usage.StorageStatsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.os.Process;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.Log;
import com.noxgroup.app.cleaner.NoxApplication;
import com.noxgroup.app.cleaner.bean.AppRunInfo;
import com.noxgroup.app.cleaner.bean.AppUsageInfo;
import com.noxgroup.app.cleaner.bean.PacakgeCacheInfo;
import com.noxgroup.app.cleaner.model.CleanFileBean;
import com.noxgroup.app.cleaner.module.cleanapp.a;
import java.io.File;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CleanHelper
{
  public static final String e = "largeFiles";
  public static final String f = "apkFiles";
  public static final String g = "duplicateFiles";
  public static final String h = "cacheFiles";
  public static final String i = "pre_key_image_space";
  public static final String j = "pre_key_audio_space";
  public static final String k = "pre_key_video_space";
  public static final String l = "pre_key_doc_space";
  public static final String m = "pre_key_apk_space";
  public static final String n = "pre_key_other_space";
  public static final String o = "pre_key_large_file_space";
  public static final String p = "pre_key_cache_space";
  public static final String q = "pre_key_repeat_space";
  public static final String r = "pre_key_file_count";
  private static Method s;
  private static Method t;
  private static final DateFormat u = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private static final BigDecimal v = BigDecimal.valueOf(1024L);
  private static final BigDecimal w = BigDecimal.valueOf(1048576L);
  private static final BigDecimal x = BigDecimal.valueOf(1073741824L);
  private static final String y = "bignox_v1_";
  private volatile long A = 0L;
  private volatile long B = 0L;
  private BroadcastReceiver C = new CleanHelper.4(this);
  public volatile double a = 0.78D;
  public volatile double b = 0.4D;
  public volatile boolean c = false;
  public volatile boolean d = false;
  private volatile long z = 0L;
  
  static
  {
    System.loadLibrary("noxclean");
  }
  
  private CleanHelper()
  {
    if (Runtime.getRuntime().availableProcessors() > 4) {
      this.c = true;
    }
  }
  
  private AppRunInfo a(String paramString, int paramInt, PackageManager paramPackageManager)
  {
    String str = a(paramPackageManager, paramInt, paramString);
    try
    {
      PackageInfo localPackageInfo = paramPackageManager.getPackageInfo(str, 1);
      paramString = localPackageInfo;
      if (localPackageInfo == null) {
        paramString = paramPackageManager.getPackageInfo(str, 4);
      }
      paramInt = a(paramString);
      if (paramInt == 1)
      {
        paramPackageManager = new AppRunInfo();
        try
        {
          paramPackageManager.setPackageName(str);
          paramPackageManager.setFlag(paramInt);
          return paramPackageManager;
        }
        catch (Exception paramString) {}
      }
      else
      {
        return null;
      }
    }
    catch (Exception paramString)
    {
      paramPackageManager = null;
      paramString.printStackTrace();
    }
    return paramPackageManager;
  }
  
  public static CleanHelper a()
  {
    return CleanHelper.a.a();
  }
  
  private String a(PackageManager paramPackageManager, int paramInt, String paramString)
  {
    Object localObject1 = paramPackageManager.getPackagesForUid(paramInt);
    Object localObject2 = new HashSet();
    ((Set)localObject2).addAll(Arrays.asList((Object[])localObject1));
    if (((Set)localObject2).contains(paramString)) {
      return paramString;
    }
    localObject1 = paramString;
    for (;;)
    {
      try
      {
        if (paramString.contains(":")) {
          localObject1 = paramString.substring(0, paramString.indexOf(":"));
        }
        paramString = ((Set)localObject2).iterator();
        if (paramString.hasNext())
        {
          localObject2 = (String)paramString.next();
          Object localObject3 = paramPackageManager.getPackageInfo((String)localObject2, 1);
          if (localObject3 != null)
          {
            ActivityInfo[] arrayOfActivityInfo = ((PackageInfo)localObject3).activities;
            if (arrayOfActivityInfo != null)
            {
              paramInt = 0;
              if (paramInt < arrayOfActivityInfo.length)
              {
                if (!arrayOfActivityInfo[paramInt].processName.equals(localObject1)) {
                  break label225;
                }
                return ((PackageInfo)localObject3).packageName;
              }
            }
          }
          localObject2 = paramPackageManager.getPackageInfo((String)localObject2, 4);
          if (localObject2 == null) {
            continue;
          }
          localObject3 = ((PackageInfo)localObject2).services;
          if (localObject3 == null) {
            continue;
          }
          paramInt = 0;
          if (paramInt < localObject3.length)
          {
            if (localObject3[paramInt].processName.equals(localObject1))
            {
              paramPackageManager = ((PackageInfo)localObject2).packageName;
              return paramPackageManager;
            }
            paramInt += 1;
            continue;
          }
        }
        else
        {
          return "";
        }
      }
      catch (PackageManager.NameNotFoundException paramPackageManager)
      {
        return "";
      }
      label225:
      paramInt += 1;
    }
  }
  
  private List<AppRunInfo> a(Context paramContext, PackageManager paramPackageManager)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(1024).iterator();
    while (paramContext.hasNext())
    {
      Object localObject = (ActivityManager.RunningServiceInfo)paramContext.next();
      if (((ActivityManager.RunningServiceInfo)localObject).uid > 2000)
      {
        int i1 = ((ActivityManager.RunningServiceInfo)localObject).uid;
        localObject = a(((ActivityManager.RunningServiceInfo)localObject).process, i1, paramPackageManager);
        if (localObject != null) {
          localArrayList.add(localObject);
        }
      }
    }
    return localArrayList;
  }
  
  private List<AppRunInfo> c(PackageManager paramPackageManager, Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    while (paramContext.hasNext())
    {
      Object localObject = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      localObject = a(((ActivityManager.RunningAppProcessInfo)localObject).processName, ((ActivityManager.RunningAppProcessInfo)localObject).uid, paramPackageManager);
      if (localObject != null) {
        localArrayList.add(localObject);
      }
    }
    return localArrayList;
  }
  
  private int d(int paramInt)
  {
    return (paramInt & 0xFF & 0xFFFFFFF) >> 3 | ((0xFF0000 & paramInt) >> 16 & 0xFFFFFFFF) << 7 & 0x7C00 | ((0xFF00 & paramInt) >> 8 & 0xFFFFFFFF) << 2 & 0x3E0;
  }
  
  private int e(int paramInt)
  {
    paramInt = (((0xFF0000 & paramInt) >> 16) + ((0xFF00 & paramInt) >> 8) + (paramInt & 0xFF)) / 3;
    return paramInt << 16 | 0xFF000000 | paramInt << 8 | paramInt;
  }
  
  public static boolean g()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  private List<AppRunInfo> h(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject;
    if (Build.VERSION.SDK_INT >= 21) {
      localObject = (UsageStatsManager)paramContext.getSystemService("usagestats");
    }
    try
    {
      long l1 = System.currentTimeMillis() + 3000L;
      localObject = ((UsageStatsManager)localObject).queryUsageStats(4, l1 - 7200000L, l1).iterator();
      while (((Iterator)localObject).hasNext())
      {
        UsageStats localUsageStats = (UsageStats)((Iterator)localObject).next();
        if (localUsageStats.getTotalTimeInForeground() > 1000L)
        {
          AppRunInfo localAppRunInfo = new AppRunInfo();
          int i1 = a(a(paramContext, localUsageStats.getPackageName()));
          if (i1 == 1)
          {
            localAppRunInfo.setPackageName(localUsageStats.getPackageName());
            localAppRunInfo.setFlag(i1);
            localArrayList.add(localAppRunInfo);
          }
        }
      }
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    Collections.emptyList();
    return localArrayList;
    return Collections.emptyList();
  }
  
  public int a(PackageInfo paramPackageInfo)
  {
    if (paramPackageInfo == null) {
      return 2;
    }
    int i1 = paramPackageInfo.applicationInfo.flags;
    int i2 = 0;
    if ((i1 & 0x1) != 0) {
      i1 = 1;
    } else {
      i1 = 0;
    }
    if (i1 != 0) {
      return 2;
    }
    i1 = i2;
    if ((paramPackageInfo.applicationInfo.flags & 0x80) != 0) {
      i1 = 1;
    }
    if (i1 != 0) {
      return 6;
    }
    return 1;
  }
  
  public int a(String paramString, int paramInt1, int paramInt2)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    if (paramInt1 <= paramInt2) {
      paramInt1 = paramInt2;
    }
    paramInt2 = localOptions.outWidth / paramInt1;
    paramInt1 = localOptions.outHeight / paramInt1;
    paramString = new StringBuilder();
    paramString.append("scaleX = ");
    paramString.append(paramInt2);
    paramString.append("scaleY =");
    paramString.append(paramInt1);
    Log.d("fengshu", paramString.toString());
    if (paramInt2 > paramInt1 * 3) {
      paramInt1 = Math.max(paramInt1, 2);
    } else if (paramInt1 > paramInt2 * 3) {
      paramInt1 = Math.max(paramInt2, 2);
    } else {
      paramInt1 = Math.min(paramInt2, paramInt1);
    }
    return Math.max(paramInt1, 1);
  }
  
  public PackageInfo a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 0);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public String a(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("/proc/");
    localStringBuilder.append(paramInt);
    localStringBuilder.append("/cmdline");
    return a(new File(localStringBuilder.toString()));
  }
  
  public String a(AssetManager paramAssetManager)
  {
    try
    {
      paramAssetManager = initCommon(paramAssetManager);
      return paramAssetManager;
    }
    finally
    {
      paramAssetManager = finally;
      throw paramAssetManager;
    }
  }
  
  /* Error */
  public String a(File paramFile)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +136 -> 137
    //   4: aload_1
    //   5: invokevirtual 447	java/io/File:exists	()Z
    //   8: ifne +7 -> 15
    //   11: ldc_w 261
    //   14: areturn
    //   15: aconst_null
    //   16: astore_3
    //   17: aconst_null
    //   18: astore_2
    //   19: new 449	java/io/RandomAccessFile
    //   22: dup
    //   23: aload_1
    //   24: ldc_w 450
    //   27: invokespecial 453	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   30: astore_1
    //   31: aload_1
    //   32: invokevirtual 456	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   35: astore_3
    //   36: aload_3
    //   37: astore_2
    //   38: aload_1
    //   39: ifnull +73 -> 112
    //   42: aload_1
    //   43: invokevirtual 459	java/io/RandomAccessFile:close	()V
    //   46: aload_3
    //   47: areturn
    //   48: ldc_w 461
    //   51: ldc_w 463
    //   54: invokestatic 414	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   57: pop
    //   58: aload_3
    //   59: areturn
    //   60: astore_3
    //   61: aload_1
    //   62: astore_2
    //   63: aload_3
    //   64: astore_1
    //   65: goto +49 -> 114
    //   68: goto +7 -> 75
    //   71: astore_1
    //   72: goto +42 -> 114
    //   75: aload_1
    //   76: astore_2
    //   77: ldc_w 461
    //   80: ldc_w 465
    //   83: invokestatic 414	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   86: pop
    //   87: aload_1
    //   88: ifnull +20 -> 108
    //   91: aload_1
    //   92: invokevirtual 459	java/io/RandomAccessFile:close	()V
    //   95: goto +13 -> 108
    //   98: ldc_w 461
    //   101: ldc_w 463
    //   104: invokestatic 414	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   107: pop
    //   108: ldc_w 261
    //   111: astore_2
    //   112: aload_2
    //   113: areturn
    //   114: aload_2
    //   115: ifnull +20 -> 135
    //   118: aload_2
    //   119: invokevirtual 459	java/io/RandomAccessFile:close	()V
    //   122: goto +13 -> 135
    //   125: ldc_w 461
    //   128: ldc_w 463
    //   131: invokestatic 414	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   134: pop
    //   135: aload_1
    //   136: athrow
    //   137: ldc_w 261
    //   140: areturn
    //   141: astore_1
    //   142: aload_3
    //   143: astore_1
    //   144: goto -69 -> 75
    //   147: astore_2
    //   148: goto -80 -> 68
    //   151: astore_1
    //   152: goto -104 -> 48
    //   155: astore_1
    //   156: goto -58 -> 98
    //   159: astore_2
    //   160: goto -35 -> 125
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	163	0	this	CleanHelper
    //   0	163	1	paramFile	File
    //   18	101	2	localObject1	Object
    //   147	1	2	localException	Exception
    //   159	1	2	localIOException	java.io.IOException
    //   16	43	3	str	String
    //   60	83	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   31	36	60	finally
    //   19	31	71	finally
    //   77	87	71	finally
    //   19	31	141	java/lang/Exception
    //   31	36	147	java/lang/Exception
    //   42	46	151	java/io/IOException
    //   91	95	155	java/io/IOException
    //   118	122	159	java/io/IOException
  }
  
  public List<AppUsageInfo> a(PackageManager paramPackageManager, Activity paramActivity)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = Calendar.getInstance();
    ((Calendar)localObject1).set(1, -1);
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramPackageManager = ((UsageStatsManager)paramActivity.getSystemService("usagestats")).queryUsageStats(0, ((Calendar)localObject1).getTimeInMillis(), System.currentTimeMillis()).iterator();
      while (paramPackageManager.hasNext())
      {
        paramActivity = (UsageStats)paramPackageManager.next();
        localObject1 = new AppUsageInfo();
        ((AppUsageInfo)localObject1).setPackageName(paramActivity.getPackageName());
        ((AppUsageInfo)localObject1).setLastUsedTime(u.format(Long.valueOf(paramActivity.getLastTimeUsed())));
      }
    }
    paramActivity = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Iterator localIterator = paramPackageManager.getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      try
      {
        Object localObject2 = paramPackageManager.getApplicationInfo(localPackageInfo.packageName, 1152);
        if ((((ApplicationInfo)localObject2).flags & 0x1) <= 0)
        {
          localObject2 = getLastUseTime(((ApplicationInfo)localObject2).sourceDir);
          long l1 = Long.valueOf((String)localObject2).longValue();
          paramActivity.format(new Date(System.currentTimeMillis()));
          if (l1 * 1000L > ((Calendar)localObject1).getTimeInMillis())
          {
            AppUsageInfo localAppUsageInfo = new AppUsageInfo();
            localAppUsageInfo.setPackageName(localPackageInfo.packageName);
            localAppUsageInfo.setLastUsedTime(paramActivity.format(new Date(Long.valueOf((String)localObject2).longValue() * 1000L)));
            localArrayList.add(localAppUsageInfo);
          }
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return localArrayList;
  }
  
  public List<AppRunInfo> a(PackageManager paramPackageManager, Context paramContext)
  {
    if (Process.myPid() <= 0) {
      return Collections.emptyList();
    }
    ArrayList localArrayList = new ArrayList();
    String[] arrayOfString = new File("/proc").list();
    int i1;
    if ((arrayOfString != null) && (arrayOfString.length > 0)) {
      i1 = 0;
    }
    while (i1 < arrayOfString.length)
    {
      if ((!TextUtils.isEmpty(arrayOfString[i1])) && (TextUtils.isDigitsOnly(arrayOfString[i1])))
      {
        int i2 = Integer.parseInt(arrayOfString[i1]);
        String str = a(i2);
        paramContext = str;
        if (str != null) {
          paramContext = str.trim();
        }
        i2 = b(i2);
        if ((i2 > 2000) && (paramContext != null) && (!paramContext.contains("/")) && (!paramContext.contains("zygote")) && (!paramContext.contains("system")))
        {
          paramContext = a(paramContext, i2, paramPackageManager);
          if (paramContext != null) {
            localArrayList.add(paramContext);
          }
        }
      }
      i1 += 1;
      continue;
      Log.d("MMMMMMMMMMMMM", " ========/proc  is Null");
    }
    return localArrayList;
  }
  
  public Set<String> a(Set<String> paramSet)
  {
    HashSet localHashSet = new HashSet();
    paramSet = paramSet.iterator();
    while (paramSet.hasNext())
    {
      String str = (String)paramSet.next();
      if (a.a(str)) {
        localHashSet.add(str);
      }
    }
    return localHashSet;
  }
  
  public void a(long paramLong)
  {
    this.z += paramLong;
  }
  
  public void a(Activity paramActivity)
  {
    if (!this.c) {
      return;
    }
    CleanHelper.1 local1 = new CleanHelper.1(this, paramActivity);
    paramActivity = new CleanHelper.2(this, paramActivity);
    new Thread(local1).start();
    new Thread(paramActivity).start();
  }
  
  public void a(Activity paramActivity, String paramString)
  {
    paramActivity.startActivityForResult(new Intent("android.intent.action.DELETE", Uri.fromParts("package", paramString, (String)null)), 768);
  }
  
  public void a(Context paramContext)
  {
    try
    {
      AccountManager localAccountManager = (AccountManager)paramContext.getSystemService("account");
      paramContext = localAccountManager.getAccountsByType("com.noxgroup.app.cleaner.account.type");
      if ((paramContext != null) && (paramContext.length > 0)) {
        paramContext = paramContext[0];
      } else {
        paramContext = new Account("noxcleaner", "com.noxgroup.app.cleaner.account.type.admin");
      }
      if (localAccountManager.addAccountExplicitly(paramContext, (String)null, (Bundle)null))
      {
        ContentResolver.setIsSyncable(paramContext, "com.noxgroup.app.cleaner.common.utils.AccountAuthProvider", 1);
        ContentResolver.setSyncAutomatically(paramContext, "com.noxgroup.app.cleaner.common.utils.AccountAuthProvider", true);
        ContentResolver.addPeriodicSync(paramContext, "com.noxgroup.app.cleaner.common.utils.AccountAuthProvider", new Bundle(), 60L);
        return;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      Log.d("Error", "NoxCleaner  addAccount Failed");
    }
  }
  
  public void a(String paramString, PacakgeCacheInfo paramPacakgeCacheInfo)
  {
    paramString = new Message();
    Bundle localBundle = new Bundle();
    localBundle.putString("bignox_clean_message_type", "nox_clean_type_appcache");
    localBundle.putString("bignox_clean_message_size", paramPacakgeCacheInfo.getCacheSizeString());
    localBundle.putString("bignox_clean_message_rule", paramPacakgeCacheInfo.getPackageName());
    paramString.setData(localBundle);
  }
  
  public void a(String paramString1, String paramString2, String paramString3)
  {
    paramString1 = getFileSize(paramString1);
    Message localMessage = new Message();
    Bundle localBundle = new Bundle();
    localBundle.putString("bignox_clean_message_type", paramString3);
    localBundle.putString("bignox_clean_message_size", paramString1);
    localBundle.putString("bignox_clean_message_rule", paramString2);
    localMessage.setData(localBundle);
  }
  
  public boolean a(String paramString)
  {
    Object localObject1 = NoxApplication.a();
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("bignox_v1_nox_blur_jpeg_");
    ((StringBuilder)localObject2).append(paramString);
    localObject2 = ((NoxApplication)localObject1).b(((StringBuilder)localObject2).toString());
    StringBuilder localStringBuilder;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (!"".equals(((String)localObject2).trim())) {}
    }
    else
    {
      localObject1 = isBlurImageJpeg(paramString);
      localObject2 = NoxApplication.a();
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("bignox_v1_nox_blur_jpeg_");
      localStringBuilder.append(paramString);
      ((NoxApplication)localObject2).a(localStringBuilder.toString(), String.valueOf(localObject1));
    }
    if (Double.valueOf(Double.parseDouble((String)localObject1)).doubleValue() > 0.29D)
    {
      localObject1 = NoxApplication.a();
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("bignox_v1_nox_blur_lap_");
      ((StringBuilder)localObject2).append(paramString);
      localObject2 = ((NoxApplication)localObject1).b(((StringBuilder)localObject2).toString());
      if (localObject2 != null)
      {
        localObject1 = localObject2;
        if (!"".equals(((String)localObject2).trim())) {}
      }
      else
      {
        localObject1 = isBlurImageLap(paramString);
        localObject2 = NoxApplication.a();
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("bignox_v1_nox_blur_lap_");
        localStringBuilder.append(paramString);
        ((NoxApplication)localObject2).a(localStringBuilder.toString(), (String)localObject1);
      }
      paramString = Double.valueOf(Double.parseDouble((String)localObject1));
      if ((paramString.doubleValue() > 1.0D) && (paramString.doubleValue() < 380.0D)) {
        return true;
      }
    }
    return false;
  }
  
  public native HashMap analyticsFile(String paramString);
  
  public int b(int paramInt)
  {
    try
    {
      if (s == null)
      {
        Method localMethod = Process.class.getDeclaredMethod("getUidForPid", new Class[] { Integer.TYPE });
        s = localMethod;
        localMethod.setAccessible(true);
      }
      paramInt = ((Integer)s.invoke(null, new Object[] { Integer.valueOf(paramInt) })).intValue();
      return paramInt;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return -1;
  }
  
  public long b()
  {
    return this.A;
  }
  
  public Set<String> b(PackageManager paramPackageManager, Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    ArrayList localArrayList = new ArrayList();
    if (Build.VERSION.SDK_INT >= 26)
    {
      paramPackageManager = localArrayList;
      if (d(paramContext)) {
        paramPackageManager = h(paramContext);
      }
    }
    else if (Build.VERSION.SDK_INT >= 24)
    {
      paramPackageManager = a(paramContext, paramPackageManager);
    }
    else if (Build.VERSION.SDK_INT >= 21)
    {
      Log.d("MMMMMMMMMMMM", "    android   LOLLIPOP           ");
      paramPackageManager = a(paramPackageManager, paramContext);
    }
    else
    {
      paramPackageManager = c(paramPackageManager, paramContext);
    }
    paramPackageManager = paramPackageManager.iterator();
    while (paramPackageManager.hasNext())
    {
      paramContext = (AppRunInfo)paramPackageManager.next();
      if (!localHashSet.contains(paramContext.getPackageName())) {
        localHashSet.add(paramContext.getPackageName());
      }
    }
    return localHashSet;
  }
  
  public void b(long paramLong)
  {
    this.A += paramLong;
  }
  
  public void b(Context paramContext)
  {
    if (paramContext != null) {
      paramContext.registerReceiver(this.C, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    }
  }
  
  public boolean b(String paramString)
  {
    Object localObject = new BitmapFactory.Options();
    ((BitmapFactory.Options)localObject).inSampleSize = e(paramString);
    paramString = BitmapFactory.decodeFile(paramString, (BitmapFactory.Options)localObject);
    boolean bool = false;
    if (paramString != null)
    {
      int i11 = paramString.getWidth();
      int i12 = paramString.getHeight();
      localObject = new int[9];
      Object tmp53_51 = localObject;
      tmp53_51[0] = -1;
      Object tmp57_53 = tmp53_51;
      tmp57_53[1] = -1;
      Object tmp61_57 = tmp57_53;
      tmp61_57[2] = -1;
      Object tmp65_61 = tmp61_57;
      tmp65_61[3] = -1;
      Object tmp69_65 = tmp65_61;
      tmp69_65[4] = 9;
      Object tmp74_69 = tmp69_65;
      tmp74_69[5] = -1;
      Object tmp78_74 = tmp74_69;
      tmp78_74[6] = -1;
      Object tmp83_78 = tmp78_74;
      tmp83_78[7] = -1;
      Object tmp88_83 = tmp83_78;
      tmp88_83[8] = -1;
      tmp88_83;
      int i5 = 1;
      int i1 = 0;
      while (i5 < i11 - 1)
      {
        int i6 = 1;
        while (i6 < i12 - 1)
        {
          int i13 = d(paramString.getPixel(i5, i6));
          int i7 = -1;
          int i2 = 0;
          int i3 = 0;
          int i4 = 0;
          int i8 = 0;
          while (i7 <= 1)
          {
            int i9 = i4;
            i4 = i2;
            int i10 = -1;
            i2 = i9;
            i9 = i10;
            while (i9 <= 1)
            {
              i10 = e(paramString.getPixel(i5 + i9, i6 + i7));
              i4 += Color.red(i10) * localObject[i8];
              i3 += Color.green(i10) * localObject[i8];
              i2 += Color.blue(i10) * localObject[i8];
              i8 += 1;
              i9 += 1;
            }
            i7 += 1;
            i9 = i2;
            i2 = i4;
            i4 = i9;
          }
          i7 = i1;
          if (Math.abs(Color.red(i13) - i2) + Math.abs(Color.green(i13) - i3) + Math.abs(Color.blue(i13) - i4) > 100) {
            i7 = i1 + 1;
          }
          i6 += 1;
          i1 = i7;
        }
        i5 += 1;
      }
      if (i1 * 100 / (i11 * i12) < 81.0D) {
        bool = true;
      }
      return bool;
    }
    return false;
  }
  
  public double c(String paramString)
  {
    Object localObject = NoxApplication.a();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("bignox_v1_nox_sami_");
    localStringBuilder.append(paramString);
    localObject = ((NoxApplication)localObject).b(localStringBuilder.toString());
    if ((localObject != null) && (!"".equals(((String)localObject).trim()))) {
      return Double.parseDouble((String)localObject);
    }
    double d1 = d(paramString);
    localObject = NoxApplication.a();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("bignox_v1_nox_sami_");
    localStringBuilder.append(paramString);
    ((NoxApplication)localObject).a(localStringBuilder.toString(), String.valueOf(d1));
    return d1;
  }
  
  public int c(int paramInt)
  {
    try
    {
      if (t == null)
      {
        Method localMethod = Process.class.getDeclaredMethod("getParentPid", new Class[] { Integer.TYPE });
        t = localMethod;
        localMethod.setAccessible(true);
      }
      paramInt = ((Integer)t.invoke(null, new Object[] { Integer.valueOf(paramInt) })).intValue();
      return paramInt;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return -1;
  }
  
  public long c()
  {
    return this.z;
  }
  
  public Set<String> c(Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    paramContext = (DevicePolicyManager)paramContext.getSystemService("device_policy");
    if (paramContext != null)
    {
      paramContext = paramContext.getActiveAdmins();
      if (paramContext != null)
      {
        paramContext = paramContext.iterator();
        while (paramContext.hasNext())
        {
          ComponentName localComponentName = (ComponentName)paramContext.next();
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("===============");
          localStringBuilder.append(localComponentName.getPackageName());
          Log.d("MMMMMMMMMMMM", localStringBuilder.toString());
          if (!TextUtils.isEmpty(localComponentName.getPackageName()))
          {
            localStringBuilder = new StringBuilder();
            localStringBuilder.append(" activeAds  package :");
            localStringBuilder.append(localComponentName.getPackageName());
            Log.d("MMMMMMMM", localStringBuilder.toString());
            localHashSet.add(localComponentName.getPackageName());
          }
        }
      }
      Log.d("MMMMMMMMM", "  activieAds     ===================NUll");
      return localHashSet;
    }
    Log.d("MMMMMMMMMMM", "devicePolicyManager ================Null");
    return localHashSet;
  }
  
  public void c(long paramLong)
  {
    this.B += paramLong;
  }
  
  public double d(String paramString)
  {
    Object localObject = new BitmapFactory.Options();
    ((BitmapFactory.Options)localObject).inSampleSize = e(paramString);
    paramString = BitmapFactory.decodeFile(paramString, (BitmapFactory.Options)localObject);
    double d1 = 0.0D;
    double d2 = d1;
    if (paramString != null)
    {
      int i3 = paramString.getWidth();
      int i2 = paramString.getHeight();
      localObject = new int[i3 * i2];
      paramString.getPixels((int[])localObject, 0, i3, 0, 0, i3, i2);
      int i1 = 0;
      while (i1 < i2 - 1)
      {
        int i4 = 0;
        while (i4 < i3 - 1)
        {
          int i5 = (i1 + 1) * i3 + i4;
          int i7 = d(localObject[i5]);
          int i6 = i1 * i3 + i4;
          d2 = Math.pow(i7 - d(localObject[i6]), 2.0D);
          i7 = i6 + 1;
          d1 += Math.sqrt(d2 + Math.pow(d(localObject[i7]) - d(localObject[i6]), 2.0D)) + (Math.abs(d(localObject[i5]) - d(localObject[i6])) + Math.abs(d(localObject[i7]) - d(localObject[i6])));
          i4 += 1;
        }
        i1 += 1;
      }
      paramString.recycle();
      d2 = d1;
    }
    return d2;
  }
  
  public long d()
  {
    return this.B;
  }
  
  public String d(long paramLong)
  {
    Object localObject = BigDecimal.valueOf(paramLong);
    StringBuilder localStringBuilder;
    if (((BigDecimal)localObject).compareTo(x) > 0)
    {
      localObject = ((BigDecimal)localObject).divide(x, 2, 4);
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(String.format("%.2f", new Object[] { Float.valueOf(((BigDecimal)localObject).floatValue()) }));
      localStringBuilder.append("GB");
      return localStringBuilder.toString();
    }
    if (((BigDecimal)localObject).compareTo(w) > 0)
    {
      localObject = ((BigDecimal)localObject).divide(w, 2, 4);
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(String.format("%.1f", new Object[] { Float.valueOf(((BigDecimal)localObject).floatValue()) }));
      localStringBuilder.append("MB");
      return localStringBuilder.toString();
    }
    if (((BigDecimal)localObject).compareTo(v) > 0)
    {
      localObject = ((BigDecimal)localObject).divide(v, 2, 4);
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(String.format("%.1f", new Object[] { Float.valueOf(((BigDecimal)localObject).floatValue()) }));
      localStringBuilder.append("KB");
      return localStringBuilder.toString();
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramLong);
    ((StringBuilder)localObject).append("B");
    return ((StringBuilder)localObject).toString();
  }
  
  public boolean d(Context paramContext)
  {
    int i1 = Build.VERSION.SDK_INT;
    boolean bool = false;
    if (i1 < 21) {
      return false;
    }
    try
    {
      i1 = ((AppOpsManager)paramContext.getSystemService("appops")).checkOp("android:get_usage_stats", paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 0).uid, paramContext.getPackageName());
      if (i1 == 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public native String deleteFiles(String paramString);
  
  public int e(String paramString)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    int i1 = 1;
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    if (localOptions.outWidth / 128 > 1) {
      i1 = localOptions.outWidth / 128;
    }
    int i2 = i1;
    if (localOptions.outHeight / 128 > i1) {
      i2 = localOptions.outHeight / 128;
    }
    return i2;
  }
  
  public HashMap e()
  {
    HashMap localHashMap = analyticsFile(Environment.getExternalStorageDirectory().getPath());
    Object localObject = (HashMap)localHashMap.remove("duplicateFiles");
    localObject = f.a().a((HashMap)localObject);
    f.a().b();
    localHashMap.put("duplicateFiles", localObject);
    return localHashMap;
  }
  
  public void e(Context paramContext)
  {
    Intent localIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("package:");
    localStringBuilder.append(paramContext.getPackageName());
    localIntent.setData(Uri.parse(localStringBuilder.toString()));
    paramContext.startActivity(localIntent);
  }
  
  public long f()
  {
    return j(Environment.getDataDirectory().getPath());
  }
  
  public void f(Context paramContext)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRecentTasks(1024, 2).iterator();
    while (paramContext.hasNext()) {
      ActivityManager.RecentTaskInfo localRecentTaskInfo = (ActivityManager.RecentTaskInfo)paramContext.next();
    }
  }
  
  public void f(String paramString)
  {
    NoxApplication localNoxApplication = NoxApplication.a();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("bignox_v1_nox_sami_");
    localStringBuilder.append(paramString);
    localNoxApplication.d(localStringBuilder.toString());
    localNoxApplication = NoxApplication.a();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("bignox_v1_nox_blur_jpeg_");
    localStringBuilder.append(paramString);
    localNoxApplication.d(localStringBuilder.toString());
    localNoxApplication = NoxApplication.a();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("bignox_v1_nox_blur_lap_");
    localStringBuilder.append(paramString);
    localNoxApplication.d(localStringBuilder.toString());
  }
  
  public long g(Context paramContext)
  {
    int i1 = Build.VERSION.SDK_INT;
    long l2 = 0L;
    long l1 = l2;
    if (i1 >= 26)
    {
      Object localObject1 = paramContext.getPackageManager().getInstalledPackages(0);
      Object localObject2 = (StorageManager)paramContext.getSystemService("storage");
      paramContext = (StorageStatsManager)paramContext.getSystemService("storagestats");
      l1 = l2;
      try
      {
        localObject1 = ((List)localObject1).iterator();
        for (;;)
        {
          l1 = l2;
          if (!((Iterator)localObject1).hasNext()) {
            break;
          }
          l1 = l2;
          localObject2 = (PackageInfo)((Iterator)localObject1).next();
          l1 = l2;
          StorageStats localStorageStats = paramContext.queryStatsForPackage(StorageManager.UUID_DEFAULT, ((PackageInfo)localObject2).packageName, Process.myUserHandle());
          if (localStorageStats != null)
          {
            l1 = l2;
            StringBuilder localStringBuilder = new StringBuilder();
            l1 = l2;
            localStringBuilder.append("packageName:");
            l1 = l2;
            localStringBuilder.append(((PackageInfo)localObject2).packageName);
            l1 = l2;
            localStringBuilder.append(" =================cacheSize:");
            l1 = l2;
            localStringBuilder.append(localStorageStats.getCacheBytes());
            l1 = l2;
            j.a(localStringBuilder.toString());
            l1 = l2;
            l2 += localStorageStats.getCacheBytes();
          }
        }
        l1 = l2;
        paramContext = new StringBuilder();
        l1 = l2;
        paramContext.append("ALL   CacheSize:");
        l1 = l2;
        paramContext.append(l2);
        l1 = l2;
        j.a(paramContext.toString());
        return l2;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return l1;
  }
  
  public BitmapFactory.Options g(String paramString)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    return localOptions;
  }
  
  public native String genFile();
  
  public native long getApkSize();
  
  public native long getCacheSize();
  
  public native String getConfigData(AssetManager paramAssetManager, int paramInt1, int paramInt2);
  
  public native String getConfigLine(AssetManager paramAssetManager);
  
  public native long getDocSize();
  
  public native String getFileSize(String paramString);
  
  public native String getFileSizeSp(String paramString);
  
  public native String getImageBlur(int[] paramArrayOfInt, int paramInt1, int paramInt2);
  
  public native long getImageSize();
  
  public native String getJNIString();
  
  public native long getLargeFileSize();
  
  public native String getLastUseTime(String paramString);
  
  public native String getPublicKey();
  
  public native long getRepeatSize();
  
  public native long getScannedSize();
  
  public int h()
  {
    Object localObject1 = new File("/sys/class/thermal/").listFiles(new CleanHelper.3(this));
    int i3 = -1;
    int i2;
    if (localObject1 != null)
    {
      i2 = 0;
      for (;;)
      {
        i1 = i3;
        if (i2 >= localObject1.length) {
          break;
        }
        Object localObject2 = localObject1[i2];
        String str = a(new File((File)localObject2, "temp"));
        if (!"".equals(str)) {
          i1 = Integer.parseInt(str);
        } else {
          i1 = -1;
        }
        localObject2 = a(new File((File)localObject2, "type"));
        if ((!"".equals(localObject2)) && (i1 > -1) && (!"battery".equalsIgnoreCase((String)localObject2)) && (!"bms".equalsIgnoreCase((String)localObject2)) && (!"ti-charger".equalsIgnoreCase((String)localObject2)) && (!"ti-bms".equalsIgnoreCase((String)localObject2)) && (!"mtktswmt".equalsIgnoreCase((String)localObject2)) && (!"mtktspmic".equalsIgnoreCase((String)localObject2)) && (!"mtktsabb".equalsIgnoreCase((String)localObject2)) && (!"mtktsbattery".equalsIgnoreCase((String)localObject2)) && (!"mtktsbuck".equalsIgnoreCase((String)localObject2)) && (!"mtktsAP".equalsIgnoreCase((String)localObject2)) && (!"GPU-therm".equalsIgnoreCase((String)localObject2)) && (!"MEM-therm".equalsIgnoreCase((String)localObject2)) && (!"PLL-therm".equalsIgnoreCase((String)localObject2)) && (!"Tboard-therm".equalsIgnoreCase((String)localObject2)) && (!"Tboard_tegra".equalsIgnoreCase((String)localObject2)) && (!"Tdiode_tegra".equalsIgnoreCase((String)localObject2)) && (!((String)localObject2).contains("mpp2_div1")) && (!"therm_est".equalsIgnoreCase((String)localObject2)) && (!"emmc_therm".equalsIgnoreCase((String)localObject2)) && (!((String)localObject2).startsWith("pm8")) && (!((String)localObject2).startsWith("pa_therm")) && (!((String)localObject2).equalsIgnoreCase("chg_therm")) && (!((String)localObject2).equalsIgnoreCase("wchg_therm")) && (!((String)localObject2).contains("battery")) && (!((String)localObject2).contains("max77854-fuelgauge")) && (!((String)localObject2).contains("sensor")) && (!((String)localObject2).equalsIgnoreCase("ac")) && (i1 > 10))
        {
          while (i1 > 100) {
            i1 /= 10;
          }
          break;
        }
        i2 += 1;
      }
    }
    localObject1 = a(new File("/sys/class/hwmon/hwmon1/temp1_input"));
    int i1 = i3;
    if (!"".equals(localObject1))
    {
      i2 = Integer.parseInt((String)localObject1);
      i1 = i3;
      if (i2 > 10) {
        for (;;)
        {
          i1 = i2;
          if (i2 <= 100) {
            break;
          }
          i2 /= 10;
        }
      }
    }
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("        temperature :");
    ((StringBuilder)localObject1).append(i1);
    Log.d("MMMMMMMMMM", ((StringBuilder)localObject1).toString());
    return i1;
  }
  
  public long h(String paramString)
  {
    try
    {
      paramString = new StatFs(paramString);
      int i1 = paramString.getBlockCount();
      int i2 = paramString.getBlockSize();
      return i1 * i2;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return 0L;
  }
  
  public List<CleanFileBean> i()
  {
    setDefaultLargeFileSize(0L);
    k.a().c();
    List localList1 = k.a().f();
    List localList2 = subGetLargeFiles(Environment.getExternalStorageDirectory().getPath());
    int i1 = 0;
    while (i1 < localList2.size())
    {
      CleanFileBean localCleanFileBean = (CleanFileBean)localList2.get(i1);
      if (!k.a().a(localCleanFileBean.getFileAbsolutePath())) {
        localList2.remove(i1);
      }
      i1 += 1;
    }
    localList1.addAll(localList2);
    return localList1;
  }
  
  public boolean i(String paramString)
  {
    return new File(paramString).exists();
  }
  
  public native String initCommon(AssetManager paramAssetManager);
  
  public native String isBlurImage(String paramString);
  
  public native String isBlurImageJpeg(String paramString);
  
  public native String isBlurImageLap(String paramString);
  
  public long j(String paramString)
  {
    paramString = new StatFs(paramString);
    return paramString.getBlockSizeLong() * paramString.getBlockCountLong();
  }
  
  public List<CleanFileBean> j()
  {
    this.z = 0L;
    k.a().c();
    return k.a().g();
  }
  
  public List<CleanFileBean> k()
  {
    this.A = 0L;
    k.a().c();
    return k.a().h();
  }
  
  public List<List<CleanFileBean>> l()
  {
    this.B = 0L;
    k.a().c();
    Map localMap = k.a().i();
    return f.a().a(localMap);
  }
  
  public native void logScanTime(String paramString1, String paramString2, String paramString3);
  
  public void m()
  {
    k.a().d();
    stopLargeScan();
  }
  
  public native void setDefaultLargeFileSize(long paramLong);
  
  public native void setLargeFileSize(long paramLong);
  
  public native void stopLargeScan();
  
  public native List subGetLargeFiles(String paramString);
}
