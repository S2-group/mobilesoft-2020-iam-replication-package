import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import com.autonavi.sdk.log.secondlog.AppInfo;
import com.autonavi.sdk.log.secondlog.AppInfos;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class bsf
{
  public static bsf a;
  
  public bsf() {}
  
  private static AppInfo a(PackageInfo paramPackageInfo, PackageManager paramPackageManager)
  {
    if (paramPackageInfo == null) {
      return null;
    }
    AppInfo localAppInfo = new AppInfo();
    ApplicationInfo localApplicationInfo = paramPackageInfo.applicationInfo;
    localAppInfo.mPackageName = paramPackageInfo.packageName;
    localAppInfo.mAppName = localApplicationInfo.loadLabel(paramPackageManager).toString();
    localAppInfo.mVersionCode = paramPackageInfo.versionCode;
    localAppInfo.mVersionName = paramPackageInfo.versionName;
    int i = localApplicationInfo.flags;
    if ((i & 0x80) != 0) {
      i = 1;
    }
    while (i != 0)
    {
      localAppInfo.mIsSystemApp = false;
      return localAppInfo;
      if ((i & 0x1) == 0) {
        i = 1;
      } else {
        i = 0;
      }
    }
    localAppInfo.mIsSystemApp = true;
    return localAppInfo;
  }
  
  static List<AppInfos> a(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    label155:
    for (;;)
    {
      try
      {
        Iterator localIterator = localPackageManager.getInstalledPackages(8192).iterator();
        AppInfo localAppInfo;
        if (localIterator.hasNext())
        {
          localAppInfo = a((PackageInfo)localIterator.next(), localPackageManager);
          if ((localAppInfo == null) || (!localAppInfo.mIsSystemApp)) {
            break label155;
          }
          localArrayList3.add(localAppInfo);
        }
        localArrayList2.add(localAppInfo);
      }
      catch (Exception localException)
      {
        localArrayList1.add(new AppInfos("UserInstalledApp", localArrayList2));
        localArrayList1.add(new AppInfos("SystemApp", localArrayList3));
        localArrayList1.add(new AppInfos("RecentlyUsedApp", e(paramContext)));
        return localArrayList1;
      }
    }
  }
  
  static String c(Context paramContext)
  {
    try
    {
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
      long l = localStatFs.getBlockSize();
      paramContext = Formatter.formatFileSize(paramContext, localStatFs.getBlockCount() * l);
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "0";
  }
  
  static String d(Context paramContext)
  {
    try
    {
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
      long l = localStatFs.getBlockSize();
      paramContext = Formatter.formatFileSize(paramContext, localStatFs.getAvailableBlocks() * l);
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "0";
  }
  
  private static List<AppInfo> e(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = (ActivityManager)paramContext.getSystemService("activity");
    try
    {
      localObject1 = ((ActivityManager)localObject1).getRecentTasks(Integer.MAX_VALUE, 1);
      if (localObject1 == null) {
        return null;
      }
    }
    catch (SecurityException localSecurityException)
    {
      Object localObject2;
      for (;;)
      {
        localObject2 = null;
      }
      paramContext = paramContext.getPackageManager();
      ActivityInfo localActivityInfo = new Intent("android.intent.action.MAIN").addCategory("android.intent.category.HOME").resolveActivityInfo(paramContext, 0);
      if (localObject2 == null) {
        return null;
      }
      int i = 0;
      while (i < localObject2.size())
      {
        Object localObject3 = (ActivityManager.RecentTaskInfo)localObject2.get(i);
        Intent localIntent = new Intent(((ActivityManager.RecentTaskInfo)localObject3).baseIntent);
        if (((ActivityManager.RecentTaskInfo)localObject2.get(i)).origActivity != null) {
          localIntent.setComponent(((ActivityManager.RecentTaskInfo)localObject3).origActivity);
        }
        if ((localActivityInfo == null) || (!localActivityInfo.packageName.equals(localIntent.getComponent().getPackageName())) || (!localActivityInfo.name.equals(localIntent.getComponent().getClassName())))
        {
          localIntent.setFlags(localIntent.getFlags() & 0xFFDFFFFF | 0x10000000);
          localObject3 = paramContext.resolveActivity(localIntent, 0);
          if (localObject3 == null) {}
        }
        try
        {
          localObject3 = paramContext.getPackageInfo(((ResolveInfo)localObject3).activityInfo.packageName, 8192);
          if (localObject3 != null) {
            localArrayList.add(a((PackageInfo)localObject3, paramContext));
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            localNameNotFoundException.printStackTrace();
          }
        }
        i += 1;
      }
    }
    return localArrayList;
  }
  
  public final void b(final Context paramContext)
  {
    new Thread("SecondLogUtilsThread")
    {
      /* Error */
      public final void run()
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 18	bsf$1:a	Landroid/content/Context;
        //   4: astore 6
        //   6: invokestatic 32	com/autonavi/minimap/net/NetworkParam:getDiu	()Ljava/lang/String;
        //   9: astore_3
        //   10: invokestatic 35	com/autonavi/minimap/net/NetworkParam:getMac	()Ljava/lang/String;
        //   13: astore 4
        //   15: invokestatic 38	com/autonavi/minimap/net/NetworkParam:getIsn	()Ljava/lang/String;
        //   18: astore 5
        //   20: invokestatic 41	com/autonavi/minimap/net/NetworkParam:getDic	()Ljava/lang/String;
        //   23: astore 7
        //   25: invokestatic 44	com/autonavi/minimap/net/NetworkParam:getDiv	()Ljava/lang/String;
        //   28: astore 8
        //   30: invokestatic 47	com/autonavi/minimap/net/NetworkParam:getDip	()Ljava/lang/String;
        //   33: astore 9
        //   35: getstatic 53	android/os/Build:MODEL	Ljava/lang/String;
        //   38: astore 10
        //   40: aload 6
        //   42: invokestatic 56	bsf:a	(Landroid/content/Context;)Ljava/util/List;
        //   45: astore 11
        //   47: new 58	java/util/ArrayList
        //   50: dup
        //   51: invokespecial 60	java/util/ArrayList:<init>	()V
        //   54: astore 12
        //   56: new 62	android/os/StatFs
        //   59: dup
        //   60: invokestatic 68	android/os/Environment:getDataDirectory	()Ljava/io/File;
        //   63: invokevirtual 73	java/io/File:getPath	()Ljava/lang/String;
        //   66: invokespecial 74	android/os/StatFs:<init>	(Ljava/lang/String;)V
        //   69: astore 13
        //   71: aload 13
        //   73: invokevirtual 78	android/os/StatFs:getBlockSize	()I
        //   76: i2l
        //   77: lstore_1
        //   78: aload 6
        //   80: aload 13
        //   82: invokevirtual 81	android/os/StatFs:getBlockCount	()I
        //   85: i2l
        //   86: lload_1
        //   87: lmul
        //   88: invokestatic 87	android/text/format/Formatter:formatFileSize	(Landroid/content/Context;J)Ljava/lang/String;
        //   91: astore 13
        //   93: new 62	android/os/StatFs
        //   96: dup
        //   97: invokestatic 68	android/os/Environment:getDataDirectory	()Ljava/io/File;
        //   100: invokevirtual 73	java/io/File:getPath	()Ljava/lang/String;
        //   103: invokespecial 74	android/os/StatFs:<init>	(Ljava/lang/String;)V
        //   106: astore 14
        //   108: aload 14
        //   110: invokevirtual 78	android/os/StatFs:getBlockSize	()I
        //   113: i2l
        //   114: lstore_1
        //   115: aload 12
        //   117: new 89	com/autonavi/sdk/log/secondlog/StorageInfo
        //   120: dup
        //   121: ldc 91
        //   123: aload 13
        //   125: aload 6
        //   127: aload 14
        //   129: invokevirtual 94	android/os/StatFs:getAvailableBlocks	()I
        //   132: i2l
        //   133: lload_1
        //   134: lmul
        //   135: invokestatic 87	android/text/format/Formatter:formatFileSize	(Landroid/content/Context;J)Ljava/lang/String;
        //   138: invokespecial 97	com/autonavi/sdk/log/secondlog/StorageInfo:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   141: invokeinterface 103 2 0
        //   146: pop
        //   147: aload 12
        //   149: new 89	com/autonavi/sdk/log/secondlog/StorageInfo
        //   152: dup
        //   153: ldc 105
        //   155: aload 6
        //   157: invokestatic 109	bsf:c	(Landroid/content/Context;)Ljava/lang/String;
        //   160: aload 6
        //   162: invokestatic 112	bsf:d	(Landroid/content/Context;)Ljava/lang/String;
        //   165: invokespecial 97	com/autonavi/sdk/log/secondlog/StorageInfo:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   168: invokeinterface 103 2 0
        //   173: pop
        //   174: new 114	com/autonavi/sdk/log/secondlog/ScanInfo
        //   177: dup
        //   178: aload_3
        //   179: aload 4
        //   181: aload 5
        //   183: aload 7
        //   185: aload 8
        //   187: aload 9
        //   189: aload 10
        //   191: aload 11
        //   193: aload 12
        //   195: new 116	java/util/Date
        //   198: dup
        //   199: invokespecial 117	java/util/Date:<init>	()V
        //   202: invokestatic 123	com/autonavi/common/utils/DateTimeUtil:getDateTimeString	(Ljava/util/Date;)Ljava/lang/String;
        //   205: invokespecial 126	com/autonavi/sdk/log/secondlog/ScanInfo:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;)V
        //   208: astore_3
        //   209: aload_3
        //   210: invokestatic 132	org/xidea/el/json/JSONEncoder:encode	(Ljava/lang/Object;)Ljava/lang/String;
        //   213: astore_3
        //   214: aload_3
        //   215: invokestatic 137	com/autonavi/common/utils/CommonUtils:encode	(Ljava/lang/String;)[B
        //   218: astore 7
        //   220: aload 7
        //   222: ifnull +141 -> 363
        //   225: aload 7
        //   227: arraylength
        //   228: ifle +135 -> 363
        //   231: new 70	java/io/File
        //   234: dup
        //   235: new 139	java/lang/StringBuilder
        //   238: dup
        //   239: invokespecial 140	java/lang/StringBuilder:<init>	()V
        //   242: invokestatic 145	com/autonavi/common/utils/FileUtil:getFilesDir	()Ljava/io/File;
        //   245: invokevirtual 148	java/io/File:getAbsolutePath	()Ljava/lang/String;
        //   248: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   251: ldc -102
        //   253: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   256: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   259: invokespecial 158	java/io/File:<init>	(Ljava/lang/String;)V
        //   262: astore 8
        //   264: aconst_null
        //   265: astore 4
        //   267: aconst_null
        //   268: astore 5
        //   270: aload 4
        //   272: astore_3
        //   273: aload 8
        //   275: invokevirtual 162	java/io/File:exists	()Z
        //   278: ifeq +12 -> 290
        //   281: aload 4
        //   283: astore_3
        //   284: aload 8
        //   286: invokevirtual 165	java/io/File:delete	()Z
        //   289: pop
        //   290: aload 4
        //   292: astore_3
        //   293: aload 8
        //   295: invokevirtual 168	java/io/File:createNewFile	()Z
        //   298: pop
        //   299: aload 4
        //   301: astore_3
        //   302: new 170	java/io/FileOutputStream
        //   305: dup
        //   306: aload 8
        //   308: invokespecial 173	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
        //   311: astore 4
        //   313: aload 4
        //   315: aload 7
        //   317: invokevirtual 179	java/io/OutputStream:write	([B)V
        //   320: aload 4
        //   322: invokevirtual 182	java/io/OutputStream:flush	()V
        //   325: aload 4
        //   327: invokestatic 187	bsq:a	(Ljava/io/Closeable;)V
        //   330: aload 6
        //   332: ldc -67
        //   334: iconst_0
        //   335: invokevirtual 195	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
        //   338: invokeinterface 201 1 0
        //   343: astore_3
        //   344: aload_3
        //   345: ldc -53
        //   347: invokestatic 209	java/lang/System:currentTimeMillis	()J
        //   350: invokeinterface 215 4 0
        //   355: pop
        //   356: aload_3
        //   357: invokeinterface 218 1 0
        //   362: pop
        //   363: return
        //   364: astore_3
        //   365: aload_3
        //   366: invokevirtual 221	java/lang/Exception:printStackTrace	()V
        //   369: return
        //   370: astore_3
        //   371: aload 5
        //   373: astore 4
        //   375: aload_3
        //   376: astore 5
        //   378: aload 4
        //   380: astore_3
        //   381: aload 5
        //   383: invokevirtual 221	java/lang/Exception:printStackTrace	()V
        //   386: aload 4
        //   388: invokestatic 187	bsq:a	(Ljava/io/Closeable;)V
        //   391: goto -61 -> 330
        //   394: astore 5
        //   396: aload_3
        //   397: astore 4
        //   399: aload 5
        //   401: astore_3
        //   402: aload 4
        //   404: invokestatic 187	bsq:a	(Ljava/io/Closeable;)V
        //   407: aload_3
        //   408: athrow
        //   409: astore_3
        //   410: goto -8 -> 402
        //   413: astore 5
        //   415: goto -37 -> 378
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	418	0	this	1
        //   77	57	1	l	long
        //   9	348	3	localObject1	Object
        //   364	2	3	localException1	Exception
        //   370	6	3	localException2	Exception
        //   380	28	3	localObject2	Object
        //   409	1	3	localObject3	Object
        //   13	390	4	localObject4	Object
        //   18	364	5	localObject5	Object
        //   394	6	5	localObject6	Object
        //   413	1	5	localException3	Exception
        //   4	327	6	localContext	Context
        //   23	293	7	localObject7	Object
        //   28	279	8	localObject8	Object
        //   33	155	9	str1	String
        //   38	152	10	str2	String
        //   45	147	11	localList	List
        //   54	140	12	localArrayList	ArrayList
        //   69	55	13	localObject9	Object
        //   106	22	14	localStatFs	StatFs
        // Exception table:
        //   from	to	target	type
        //   209	214	364	java/lang/Exception
        //   273	281	370	java/lang/Exception
        //   284	290	370	java/lang/Exception
        //   293	299	370	java/lang/Exception
        //   302	313	370	java/lang/Exception
        //   273	281	394	finally
        //   284	290	394	finally
        //   293	299	394	finally
        //   302	313	394	finally
        //   381	386	394	finally
        //   313	325	409	finally
        //   313	325	413	java/lang/Exception
      }
    }.start();
  }
}
