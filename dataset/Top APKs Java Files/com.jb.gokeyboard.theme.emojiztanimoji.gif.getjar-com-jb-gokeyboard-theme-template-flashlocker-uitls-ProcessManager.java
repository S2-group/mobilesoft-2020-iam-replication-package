package com.jb.gokeyboard.theme.template.flashlocker.uitls;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.text.TextUtils;
import com.jb.gokeyboard.theme.template.ThemeApplication;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ProcessManager
{
  public static final String TAG = "PCMgr";
  private static ProcessManager sProcMgr;
  private ActivityManager mActMgr;
  private float mAvaliableMemoryPercentage = 1.0F;
  private Context mContext = null;
  private HashSet<String> mLauncherAbleApps = null;
  private PackageManager mPkgMgr;
  
  private ProcessManager(Context paramContext)
  {
    this.mContext = paramContext.getApplicationContext();
    this.mActMgr = ((ActivityManager)this.mContext.getSystemService("activity"));
    this.mPkgMgr = this.mContext.getPackageManager();
    this.mLauncherAbleApps = new HashSet(getLauncherableApps().keySet());
  }
  
  public static List<PackageInfo> getInstalledPackages(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager();
    paramContext = null;
    try
    {
      localObject = ((PackageManager)localObject).getInstalledPackages(0);
      paramContext = (Context)localObject;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        localThrowable.printStackTrace();
      }
    }
    localObject = paramContext;
    if (paramContext == null) {
      localObject = new ArrayList();
    }
    return localObject;
  }
  
  public static ProcessManager getInstance(Context paramContext)
  {
    if (sProcMgr == null) {
      sProcMgr = new ProcessManager(paramContext);
    }
    return sProcMgr;
  }
  
  /* Error */
  private void getRunningAppListInternal(List<RunningAppModle> paramList, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 48	com/jb/gokeyboard/theme/template/flashlocker/uitls/ProcessManager:mActMgr	Landroid/app/ActivityManager;
    //   4: invokevirtual 105	android/app/ActivityManager:getRunningAppProcesses	()Ljava/util/List;
    //   7: astore 8
    //   9: aload_0
    //   10: invokespecial 109	com/jb/gokeyboard/theme/template/flashlocker/uitls/ProcessManager:isLol	()Z
    //   13: ifeq +26 -> 39
    //   16: aload 8
    //   18: ifnull +14 -> 32
    //   21: aload 8
    //   23: invokeinterface 115 1 0
    //   28: iconst_5
    //   29: if_icmpge +10 -> 39
    //   32: aload_0
    //   33: aload_1
    //   34: iload_2
    //   35: invokespecial 118	com/jb/gokeyboard/theme/template/flashlocker/uitls/ProcessManager:getRunningAppListInternalForAndroidApi22	(Ljava/util/List;Z)V
    //   38: return
    //   39: aload_0
    //   40: getfield 48	com/jb/gokeyboard/theme/template/flashlocker/uitls/ProcessManager:mActMgr	Landroid/app/ActivityManager;
    //   43: invokevirtual 105	android/app/ActivityManager:getRunningAppProcesses	()Ljava/util/List;
    //   46: astore 8
    //   48: invokestatic 124	android/os/SystemClock:elapsedRealtime	()J
    //   51: lstore 6
    //   53: aload_0
    //   54: bipush 100
    //   56: invokevirtual 127	com/jb/gokeyboard/theme/template/flashlocker/uitls/ProcessManager:getRunningServices	(I)Ljava/util/List;
    //   59: astore 10
    //   61: aload 8
    //   63: invokeinterface 131 1 0
    //   68: astore 11
    //   70: aload 11
    //   72: invokeinterface 136 1 0
    //   77: ifeq -39 -> 38
    //   80: aload 11
    //   82: invokeinterface 140 1 0
    //   87: checkcast 142	android/app/ActivityManager$RunningAppProcessInfo
    //   90: astore 12
    //   92: aload 12
    //   94: getfield 146	android/app/ActivityManager$RunningAppProcessInfo:pkgList	[Ljava/lang/String;
    //   97: astore 13
    //   99: aload 13
    //   101: arraylength
    //   102: istore 4
    //   104: iconst_0
    //   105: istore_3
    //   106: iload_3
    //   107: iload 4
    //   109: if_icmpge -39 -> 70
    //   112: aload 13
    //   114: iload_3
    //   115: aaload
    //   116: astore 14
    //   118: aload 14
    //   120: invokestatic 152	com/jb/gokeyboard/theme/template/flashlocker/uitls/EssentialProcessFilter:isEssentialProcess	(Ljava/lang/String;)Z
    //   123: ifeq +6 -> 129
    //   126: goto +225 -> 351
    //   129: iload_2
    //   130: ifeq +17 -> 147
    //   133: aload 14
    //   135: lload 6
    //   137: invokestatic 156	com/jb/gokeyboard/theme/template/flashlocker/uitls/EssentialProcessFilter:isRecentKilled	(Ljava/lang/String;J)Z
    //   140: istore 5
    //   142: iload 5
    //   144: ifne +207 -> 351
    //   147: aconst_null
    //   148: astore 8
    //   150: aload_0
    //   151: getfield 54	com/jb/gokeyboard/theme/template/flashlocker/uitls/ProcessManager:mPkgMgr	Landroid/content/pm/PackageManager;
    //   154: aload 14
    //   156: sipush 128
    //   159: invokevirtual 160	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   162: astore 9
    //   164: aload 9
    //   166: astore 8
    //   168: aload_0
    //   169: aload 14
    //   171: aload_1
    //   172: invokespecial 164	com/jb/gokeyboard/theme/template/flashlocker/uitls/ProcessManager:getRunningAppModleInList	(Ljava/lang/String;Ljava/util/List;)Lcom/jb/gokeyboard/theme/template/flashlocker/uitls/RunningAppModle;
    //   175: astore 9
    //   177: aload 9
    //   179: ifnull +44 -> 223
    //   182: aload 9
    //   184: getfield 170	com/jb/gokeyboard/theme/template/flashlocker/uitls/RunningAppModle:mPids	Ljava/util/ArrayList;
    //   187: aload 12
    //   189: getfield 174	android/app/ActivityManager$RunningAppProcessInfo:pid	I
    //   192: invokestatic 180	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   195: invokevirtual 184	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   198: pop
    //   199: goto +152 -> 351
    //   202: astore_1
    //   203: invokestatic 189	java/lang/System:gc	()V
    //   206: return
    //   207: astore 9
    //   209: aload 9
    //   211: invokevirtual 190	android/content/pm/PackageManager$NameNotFoundException:printStackTrace	()V
    //   214: goto -46 -> 168
    //   217: astore_1
    //   218: aload_1
    //   219: invokevirtual 191	java/lang/Exception:printStackTrace	()V
    //   222: return
    //   223: aload 8
    //   225: invokestatic 195	com/jb/gokeyboard/theme/template/flashlocker/uitls/ProcessManager:isSystemApp	(Landroid/content/pm/ApplicationInfo;)Z
    //   228: istore 5
    //   230: aload 14
    //   232: iload 5
    //   234: invokestatic 199	com/jb/gokeyboard/theme/template/flashlocker/uitls/EssentialProcessFilter:isEssentialProcessMock	(Ljava/lang/String;Z)Z
    //   237: ifne +114 -> 351
    //   240: new 166	com/jb/gokeyboard/theme/template/flashlocker/uitls/RunningAppModle
    //   243: dup
    //   244: invokespecial 200	com/jb/gokeyboard/theme/template/flashlocker/uitls/RunningAppModle:<init>	()V
    //   247: astore 9
    //   249: aload 9
    //   251: aload_0
    //   252: getfield 54	com/jb/gokeyboard/theme/template/flashlocker/uitls/ProcessManager:mPkgMgr	Landroid/content/pm/PackageManager;
    //   255: aload 8
    //   257: invokevirtual 204	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   260: invokeinterface 210 1 0
    //   265: putfield 213	com/jb/gokeyboard/theme/template/flashlocker/uitls/RunningAppModle:mAppName	Ljava/lang/String;
    //   268: aload 9
    //   270: getfield 170	com/jb/gokeyboard/theme/template/flashlocker/uitls/RunningAppModle:mPids	Ljava/util/ArrayList;
    //   273: aload 12
    //   275: getfield 174	android/app/ActivityManager$RunningAppProcessInfo:pid	I
    //   278: invokestatic 180	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   281: invokevirtual 184	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   284: pop
    //   285: aload 9
    //   287: aload 12
    //   289: getfield 216	android/app/ActivityManager$RunningAppProcessInfo:processName	Ljava/lang/String;
    //   292: putfield 219	com/jb/gokeyboard/theme/template/flashlocker/uitls/RunningAppModle:mProcessName	Ljava/lang/String;
    //   295: aload 9
    //   297: aload 8
    //   299: getfield 224	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   302: putfield 227	com/jb/gokeyboard/theme/template/flashlocker/uitls/RunningAppModle:mPackageName	Ljava/lang/String;
    //   305: aload 9
    //   307: iload 5
    //   309: putfield 231	com/jb/gokeyboard/theme/template/flashlocker/uitls/RunningAppModle:mIsSysApp	Z
    //   312: aload 9
    //   314: aload_0
    //   315: aload 10
    //   317: aload 8
    //   319: getfield 224	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   322: invokevirtual 235	com/jb/gokeyboard/theme/template/flashlocker/uitls/ProcessManager:isServiceRunningForeground	(Ljava/util/List;Ljava/lang/String;)Z
    //   325: putfield 238	com/jb/gokeyboard/theme/template/flashlocker/uitls/RunningAppModle:mIsForeground	Z
    //   328: aload 9
    //   330: aload_0
    //   331: aload 8
    //   333: getfield 224	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   336: invokevirtual 241	com/jb/gokeyboard/theme/template/flashlocker/uitls/ProcessManager:isLauncherableApp	(Ljava/lang/String;)Z
    //   339: putfield 244	com/jb/gokeyboard/theme/template/flashlocker/uitls/RunningAppModle:mIsLaunchableApp	Z
    //   342: aload_1
    //   343: aload 9
    //   345: invokeinterface 245 2 0
    //   350: pop
    //   351: iload_3
    //   352: iconst_1
    //   353: iadd
    //   354: istore_3
    //   355: goto -249 -> 106
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	358	0	this	ProcessManager
    //   0	358	1	paramList	List<RunningAppModle>
    //   0	358	2	paramBoolean	boolean
    //   105	250	3	i	int
    //   102	8	4	j	int
    //   140	168	5	bool	boolean
    //   51	85	6	l	long
    //   7	325	8	localObject1	Object
    //   162	21	9	localObject2	Object
    //   207	3	9	localNameNotFoundException	PackageManager.NameNotFoundException
    //   247	97	9	localRunningAppModle	RunningAppModle
    //   59	257	10	localList	List
    //   68	13	11	localIterator	Iterator
    //   90	198	12	localRunningAppProcessInfo	ActivityManager.RunningAppProcessInfo
    //   97	16	13	arrayOfString	String[]
    //   116	115	14	str	String
    // Exception table:
    //   from	to	target	type
    //   39	70	202	java/lang/OutOfMemoryError
    //   70	104	202	java/lang/OutOfMemoryError
    //   118	126	202	java/lang/OutOfMemoryError
    //   133	142	202	java/lang/OutOfMemoryError
    //   150	164	202	java/lang/OutOfMemoryError
    //   168	177	202	java/lang/OutOfMemoryError
    //   182	199	202	java/lang/OutOfMemoryError
    //   209	214	202	java/lang/OutOfMemoryError
    //   223	351	202	java/lang/OutOfMemoryError
    //   150	164	207	android/content/pm/PackageManager$NameNotFoundException
    //   39	70	217	java/lang/Exception
    //   70	104	217	java/lang/Exception
    //   118	126	217	java/lang/Exception
    //   133	142	217	java/lang/Exception
    //   150	164	217	java/lang/Exception
    //   168	177	217	java/lang/Exception
    //   182	199	217	java/lang/Exception
    //   209	214	217	java/lang/Exception
    //   223	351	217	java/lang/Exception
  }
  
  @TargetApi(22)
  private void getRunningAppListInternalForAndroidApi22(List<RunningAppModle> paramList, boolean paramBoolean)
  {
    try
    {
      Object localObject1 = getInstalledPackages(this.mContext);
      localMap = ProcessHelperUtil.getRunningAppProcesses(this.mContext);
      long l = SystemClock.elapsedRealtime();
      localList = getRunningServices(100);
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (PackageInfo)((Iterator)localObject1).next();
        str = ((PackageInfo)localObject2).packageName;
        if ((localMap.containsKey(str)) && (!isAppStop(this.mContext, str)) && (!EssentialProcessFilter.isEssentialProcess(str)) && ((!paramBoolean) || (!EssentialProcessFilter.isRecentKilled(str, l))))
        {
          localObject2 = ((PackageInfo)localObject2).applicationInfo;
          localRunningAppModle = getRunningAppModleInList(str, paramList);
          if (localRunningAppModle == null) {
            break label185;
          }
          if (localMap.containsKey(str))
          {
            localRunningAppModle.mPids.clear();
            localRunningAppModle.mPids.addAll((Collection)localMap.get(str));
          }
        }
      }
    }
    catch (OutOfMemoryError paramList)
    {
      for (;;)
      {
        Map localMap;
        List localList;
        Object localObject2;
        String str;
        RunningAppModle localRunningAppModle;
        System.gc();
        return;
        boolean bool = isSystemApp((ApplicationInfo)localObject2);
        if (!EssentialProcessFilter.isEssentialProcessMock(str, bool))
        {
          localRunningAppModle = new RunningAppModle();
          localRunningAppModle.mAppName = this.mPkgMgr.getApplicationLabel((ApplicationInfo)localObject2).toString();
          if (localMap.containsKey(str)) {
            localRunningAppModle.mPids.addAll((Collection)localMap.get(str));
          }
          localRunningAppModle.mPackageName = ((ApplicationInfo)localObject2).packageName;
          localRunningAppModle.mIsSysApp = bool;
          localRunningAppModle.mIsForeground = isServiceRunningForeground(localList, ((ApplicationInfo)localObject2).packageName);
          localRunningAppModle.mIsLaunchableApp = isLauncherableApp(((ApplicationInfo)localObject2).packageName);
          paramList.add(localRunningAppModle);
        }
      }
    }
    catch (Exception paramList)
    {
      label185:
      paramList.printStackTrace();
    }
  }
  
  private RunningAppModle getRunningAppModleInList(String paramString, List<RunningAppModle> paramList)
  {
    Object localObject = null;
    Iterator localIterator = paramList.iterator();
    do
    {
      paramList = localObject;
      if (!localIterator.hasNext()) {
        break;
      }
      paramList = (RunningAppModle)localIterator.next();
    } while (!paramList.mPackageName.equals(paramString));
    return paramList;
  }
  
  @TargetApi(12)
  public static boolean isAppStop(Context paramContext, String paramString)
  {
    try
    {
      int i = paramContext.getPackageManager().getApplicationInfo(paramString, 0).flags;
      return (i & 0x200000) != 0;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return true;
  }
  
  private boolean isLol()
  {
    return Build.VERSION.SDK_INT >= 21;
  }
  
  public static boolean isSystemApp(ApplicationInfo paramApplicationInfo)
  {
    boolean bool = false;
    if (paramApplicationInfo != null)
    {
      if (((paramApplicationInfo.flags & 0x1) != 0) || ((paramApplicationInfo.flags & 0x80) != 0)) {
        bool = true;
      }
    }
    else {
      return bool;
    }
    return false;
  }
  
  public static boolean isSystemApp(String paramString)
  {
    PackageManager localPackageManager = ThemeApplication.getContext().getPackageManager();
    try
    {
      boolean bool = isSystemApp(localPackageManager.getPackageInfo(paramString, 16384).applicationInfo);
      if (bool) {
        return true;
      }
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  public long getAvaliableMemory()
  {
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    this.mActMgr.getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.availMem >> 10;
  }
  
  public float getAvaliableMemoryPercentage()
  {
    return this.mAvaliableMemoryPercentage;
  }
  
  public HashMap<String, ResolveInfo> getLauncherableApps()
  {
    Object localObject1 = null;
    Object localObject2 = new Intent("android.intent.action.MAIN");
    ((Intent)localObject2).addCategory("android.intent.category.LAUNCHER");
    try
    {
      localObject2 = this.mPkgMgr.queryIntentActivities((Intent)localObject2, 0);
      localObject1 = localObject2;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      for (;;)
      {
        localOutOfMemoryError.printStackTrace();
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
      return localException;
    }
    localObject2 = new HashMap();
    if (localObject1 != null)
    {
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject1).next();
        ((HashMap)localObject2).put(localResolveInfo.activityInfo.processName, localResolveInfo);
      }
    }
  }
  
  public List<RunningAppModle> getRunningAppList()
  {
    return getRunningAppList(true);
  }
  
  public List<RunningAppModle> getRunningAppList(boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    getRunningAppListInternal(localArrayList, paramBoolean);
    Iterator localIterator = localArrayList.iterator();
    while (localIterator.hasNext()) {
      if (((RunningAppModle)localIterator.next()).mMemory <= 0L) {
        localIterator.remove();
      }
    }
    return localArrayList;
  }
  
  public List<RunningAppModle> getRunningAppListNoMemory()
  {
    ArrayList localArrayList = new ArrayList();
    getRunningAppListInternal(localArrayList, true);
    return localArrayList;
  }
  
  public List<ActivityManager.RunningServiceInfo> getRunningServices(int paramInt)
  {
    Object localObject1 = null;
    try
    {
      localObject2 = this.mActMgr.getRunningServices(paramInt);
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject2;
        localException.printStackTrace();
      }
    }
    localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = new ArrayList();
    }
    return localObject2;
  }
  
  /* Error */
  public long getTotalMemory()
  {
    // Byte code:
    //   0: lconst_0
    //   1: lstore_3
    //   2: aconst_null
    //   3: astore 10
    //   5: aconst_null
    //   6: astore 11
    //   8: aconst_null
    //   9: astore 6
    //   11: aconst_null
    //   12: astore 9
    //   14: aconst_null
    //   15: astore 8
    //   17: aconst_null
    //   18: astore 13
    //   20: aconst_null
    //   21: astore 14
    //   23: aconst_null
    //   24: astore 7
    //   26: aconst_null
    //   27: astore 12
    //   29: new 384	java/io/FileReader
    //   32: dup
    //   33: ldc_w 386
    //   36: invokespecial 387	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   39: astore 5
    //   41: new 389	java/io/BufferedReader
    //   44: dup
    //   45: aload 5
    //   47: sipush 4096
    //   50: invokespecial 392	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   53: astore 6
    //   55: aload 6
    //   57: invokevirtual 395	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   60: astore 7
    //   62: lload_3
    //   63: lstore_1
    //   64: aload 7
    //   66: ifnull +37 -> 103
    //   69: aload 7
    //   71: ldc_w 397
    //   74: invokevirtual 401	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   77: astore 7
    //   79: lload_3
    //   80: lstore_1
    //   81: aload 7
    //   83: ifnull +20 -> 103
    //   86: lload_3
    //   87: lstore_1
    //   88: aload 7
    //   90: arraylength
    //   91: iconst_1
    //   92: if_icmple +11 -> 103
    //   95: aload 7
    //   97: iconst_1
    //   98: aaload
    //   99: invokestatic 407	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   102: lstore_1
    //   103: aload 6
    //   105: ifnull +8 -> 113
    //   108: aload 6
    //   110: invokevirtual 410	java/io/BufferedReader:close	()V
    //   113: aload 5
    //   115: ifnull +348 -> 463
    //   118: aload 5
    //   120: invokevirtual 411	java/io/FileReader:close	()V
    //   123: lload_1
    //   124: lreturn
    //   125: astore 6
    //   127: aload 6
    //   129: invokevirtual 412	java/io/IOException:printStackTrace	()V
    //   132: goto -19 -> 113
    //   135: astore 5
    //   137: aload 5
    //   139: invokevirtual 412	java/io/IOException:printStackTrace	()V
    //   142: lload_1
    //   143: lreturn
    //   144: astore 9
    //   146: aload 12
    //   148: astore 5
    //   150: aload 8
    //   152: astore 6
    //   154: aload 5
    //   156: astore 7
    //   158: aload 9
    //   160: invokevirtual 413	java/lang/NumberFormatException:printStackTrace	()V
    //   163: aload 8
    //   165: ifnull +8 -> 173
    //   168: aload 8
    //   170: invokevirtual 410	java/io/BufferedReader:close	()V
    //   173: lload_3
    //   174: lstore_1
    //   175: aload 5
    //   177: ifnull -54 -> 123
    //   180: aload 5
    //   182: invokevirtual 411	java/io/FileReader:close	()V
    //   185: lconst_0
    //   186: lreturn
    //   187: astore 5
    //   189: aload 5
    //   191: invokevirtual 412	java/io/IOException:printStackTrace	()V
    //   194: lconst_0
    //   195: lreturn
    //   196: astore 6
    //   198: aload 6
    //   200: invokevirtual 412	java/io/IOException:printStackTrace	()V
    //   203: goto -30 -> 173
    //   206: astore 9
    //   208: aload 13
    //   210: astore 5
    //   212: aload 10
    //   214: astore 8
    //   216: aload 8
    //   218: astore 6
    //   220: aload 5
    //   222: astore 7
    //   224: aload 9
    //   226: invokevirtual 414	java/io/FileNotFoundException:printStackTrace	()V
    //   229: aload 8
    //   231: ifnull +8 -> 239
    //   234: aload 8
    //   236: invokevirtual 410	java/io/BufferedReader:close	()V
    //   239: lload_3
    //   240: lstore_1
    //   241: aload 5
    //   243: ifnull -120 -> 123
    //   246: aload 5
    //   248: invokevirtual 411	java/io/FileReader:close	()V
    //   251: lconst_0
    //   252: lreturn
    //   253: astore 5
    //   255: aload 5
    //   257: invokevirtual 412	java/io/IOException:printStackTrace	()V
    //   260: lconst_0
    //   261: lreturn
    //   262: astore 6
    //   264: aload 6
    //   266: invokevirtual 412	java/io/IOException:printStackTrace	()V
    //   269: goto -30 -> 239
    //   272: astore 9
    //   274: aload 14
    //   276: astore 5
    //   278: aload 11
    //   280: astore 8
    //   282: aload 8
    //   284: astore 6
    //   286: aload 5
    //   288: astore 7
    //   290: aload 9
    //   292: invokevirtual 412	java/io/IOException:printStackTrace	()V
    //   295: aload 8
    //   297: ifnull +8 -> 305
    //   300: aload 8
    //   302: invokevirtual 410	java/io/BufferedReader:close	()V
    //   305: lload_3
    //   306: lstore_1
    //   307: aload 5
    //   309: ifnull -186 -> 123
    //   312: aload 5
    //   314: invokevirtual 411	java/io/FileReader:close	()V
    //   317: lconst_0
    //   318: lreturn
    //   319: astore 5
    //   321: aload 5
    //   323: invokevirtual 412	java/io/IOException:printStackTrace	()V
    //   326: lconst_0
    //   327: lreturn
    //   328: astore 6
    //   330: aload 6
    //   332: invokevirtual 412	java/io/IOException:printStackTrace	()V
    //   335: goto -30 -> 305
    //   338: astore 5
    //   340: aload 6
    //   342: ifnull +8 -> 350
    //   345: aload 6
    //   347: invokevirtual 410	java/io/BufferedReader:close	()V
    //   350: aload 7
    //   352: ifnull +8 -> 360
    //   355: aload 7
    //   357: invokevirtual 411	java/io/FileReader:close	()V
    //   360: aload 5
    //   362: athrow
    //   363: astore 6
    //   365: aload 6
    //   367: invokevirtual 412	java/io/IOException:printStackTrace	()V
    //   370: goto -20 -> 350
    //   373: astore 6
    //   375: aload 6
    //   377: invokevirtual 412	java/io/IOException:printStackTrace	()V
    //   380: goto -20 -> 360
    //   383: astore 8
    //   385: aload 9
    //   387: astore 6
    //   389: aload 5
    //   391: astore 7
    //   393: aload 8
    //   395: astore 5
    //   397: goto -57 -> 340
    //   400: astore 8
    //   402: aload 5
    //   404: astore 7
    //   406: aload 8
    //   408: astore 5
    //   410: goto -70 -> 340
    //   413: astore 9
    //   415: aload 11
    //   417: astore 8
    //   419: goto -137 -> 282
    //   422: astore 9
    //   424: aload 6
    //   426: astore 8
    //   428: goto -146 -> 282
    //   431: astore 9
    //   433: aload 10
    //   435: astore 8
    //   437: goto -221 -> 216
    //   440: astore 9
    //   442: aload 6
    //   444: astore 8
    //   446: goto -230 -> 216
    //   449: astore 9
    //   451: goto -301 -> 150
    //   454: astore 9
    //   456: aload 6
    //   458: astore 8
    //   460: goto -310 -> 150
    //   463: lload_1
    //   464: lreturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	465	0	this	ProcessManager
    //   63	401	1	l1	long
    //   1	305	3	l2	long
    //   39	80	5	localFileReader	java.io.FileReader
    //   135	3	5	localIOException1	java.io.IOException
    //   148	33	5	localObject1	Object
    //   187	3	5	localIOException2	java.io.IOException
    //   210	37	5	localObject2	Object
    //   253	3	5	localIOException3	java.io.IOException
    //   276	37	5	localObject3	Object
    //   319	3	5	localIOException4	java.io.IOException
    //   338	52	5	localObject4	Object
    //   395	14	5	localObject5	Object
    //   9	100	6	localBufferedReader	java.io.BufferedReader
    //   125	3	6	localIOException5	java.io.IOException
    //   152	1	6	localObject6	Object
    //   196	3	6	localIOException6	java.io.IOException
    //   218	1	6	localObject7	Object
    //   262	3	6	localIOException7	java.io.IOException
    //   284	1	6	localObject8	Object
    //   328	18	6	localIOException8	java.io.IOException
    //   363	3	6	localIOException9	java.io.IOException
    //   373	3	6	localIOException10	java.io.IOException
    //   387	70	6	localObject9	Object
    //   24	381	7	localObject10	Object
    //   15	286	8	localObject11	Object
    //   383	11	8	localObject12	Object
    //   400	7	8	localObject13	Object
    //   417	42	8	localObject14	Object
    //   12	1	9	localObject15	Object
    //   144	15	9	localNumberFormatException1	NumberFormatException
    //   206	19	9	localFileNotFoundException1	java.io.FileNotFoundException
    //   272	114	9	localIOException11	java.io.IOException
    //   413	1	9	localIOException12	java.io.IOException
    //   422	1	9	localIOException13	java.io.IOException
    //   431	1	9	localFileNotFoundException2	java.io.FileNotFoundException
    //   440	1	9	localFileNotFoundException3	java.io.FileNotFoundException
    //   449	1	9	localNumberFormatException2	NumberFormatException
    //   454	1	9	localNumberFormatException3	NumberFormatException
    //   3	431	10	localObject16	Object
    //   6	410	11	localObject17	Object
    //   27	120	12	localObject18	Object
    //   18	191	13	localObject19	Object
    //   21	254	14	localObject20	Object
    // Exception table:
    //   from	to	target	type
    //   108	113	125	java/io/IOException
    //   118	123	135	java/io/IOException
    //   29	41	144	java/lang/NumberFormatException
    //   180	185	187	java/io/IOException
    //   168	173	196	java/io/IOException
    //   29	41	206	java/io/FileNotFoundException
    //   246	251	253	java/io/IOException
    //   234	239	262	java/io/IOException
    //   29	41	272	java/io/IOException
    //   312	317	319	java/io/IOException
    //   300	305	328	java/io/IOException
    //   29	41	338	finally
    //   158	163	338	finally
    //   224	229	338	finally
    //   290	295	338	finally
    //   345	350	363	java/io/IOException
    //   355	360	373	java/io/IOException
    //   41	55	383	finally
    //   55	62	400	finally
    //   69	79	400	finally
    //   88	103	400	finally
    //   41	55	413	java/io/IOException
    //   55	62	422	java/io/IOException
    //   69	79	422	java/io/IOException
    //   88	103	422	java/io/IOException
    //   41	55	431	java/io/FileNotFoundException
    //   55	62	440	java/io/FileNotFoundException
    //   69	79	440	java/io/FileNotFoundException
    //   88	103	440	java/io/FileNotFoundException
    //   41	55	449	java/lang/NumberFormatException
    //   55	62	454	java/lang/NumberFormatException
    //   69	79	454	java/lang/NumberFormatException
    //   88	103	454	java/lang/NumberFormatException
  }
  
  public boolean isForbidBoostApp(String paramString)
  {
    if (EssentialProcessFilter.isEssentialProcess(paramString)) {}
    do
    {
      return true;
      Object localObject = null;
      try
      {
        ApplicationInfo localApplicationInfo = this.mPkgMgr.getApplicationInfo(paramString, 128);
        localObject = localApplicationInfo;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;)
        {
          localNameNotFoundException.printStackTrace();
        }
      }
    } while ((localObject != null) && (EssentialProcessFilter.isEssentialProcessMock(paramString, isSystemApp(localObject))));
    return false;
  }
  
  public boolean isLauncherableApp(String paramString)
  {
    return this.mLauncherAbleApps.contains(paramString);
  }
  
  public boolean isServiceRunningForeground(List<ActivityManager.RunningServiceInfo> paramList, String paramString)
  {
    if ((paramList == null) || (paramList.size() == 0) || (TextUtils.isEmpty(paramString))) {}
    ActivityManager.RunningServiceInfo localRunningServiceInfo;
    do
    {
      while (!paramList.hasNext())
      {
        return false;
        paramList = paramList.iterator();
      }
      localRunningServiceInfo = (ActivityManager.RunningServiceInfo)paramList.next();
    } while ((!localRunningServiceInfo.service.getPackageName().equals(paramString)) || (!localRunningServiceInfo.foreground));
    return true;
  }
  
  public void killAppByPid(int paramInt)
  {
    Iterator localIterator = this.mActMgr.getRunningAppProcesses().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (ActivityManager.RunningAppProcessInfo)localIterator.next();
      if (((ActivityManager.RunningAppProcessInfo)localObject).pid == paramInt)
      {
        localObject = ((ActivityManager.RunningAppProcessInfo)localObject).pkgList;
        this.mActMgr.killBackgroundProcesses(localObject[0]);
      }
    }
  }
  
  public float updateAvaliableMemoryPercentage(long paramLong1, long paramLong2)
  {
    this.mAvaliableMemoryPercentage = ((float)paramLong1 / (float)paramLong2);
    return this.mAvaliableMemoryPercentage;
  }
}
