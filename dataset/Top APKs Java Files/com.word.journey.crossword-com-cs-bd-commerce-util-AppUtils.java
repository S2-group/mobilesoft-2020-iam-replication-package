package com.cs.bd.commerce.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AppUtils
{
  public static final String ACTION_SETTINGS = "android.settings.APPLICATION_DETAILS_SETTINGS";
  private static final String TAG = "matt";
  private static String sCurrProcessName = null;
  private static Long sInstallTime;
  
  public AppUtils() {}
  
  public static boolean canReadGmailLabels(Context paramContext)
  {
    boolean bool4 = false;
    boolean bool5 = false;
    boolean bool1 = false;
    boolean bool2 = bool4;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo("com.google.android.gm", 4104);
      int k = 0;
      int j = k;
      bool2 = bool4;
      int i;
      int m;
      if (paramContext.permissions != null)
      {
        i = 0;
        bool2 = bool4;
        m = paramContext.permissions.length;
      }
      boolean bool3;
      for (;;)
      {
        j = k;
        Object localObject;
        if (i < m)
        {
          bool2 = bool4;
          localObject = paramContext.permissions[i];
          bool2 = bool4;
          if ("com.google.android.gm.permission.READ_CONTENT_PROVIDER".equals(((PermissionInfo)localObject).name))
          {
            bool2 = bool4;
            if (((PermissionInfo)localObject).protectionLevel < 2) {
              j = 1;
            }
          }
        }
        else
        {
          bool3 = bool5;
          if (j == 0) {
            break;
          }
          bool2 = bool4;
          bool3 = bool5;
          if (paramContext.providers == null) {
            break;
          }
          i = 0;
          bool2 = bool4;
          j = paramContext.providers.length;
          for (;;)
          {
            bool3 = bool1;
            if (i >= j) {
              break;
            }
            bool2 = bool1;
            localObject = paramContext.providers[i];
            bool3 = bool1;
            bool2 = bool1;
            if ("com.google.android.gm".equals(((ProviderInfo)localObject).authority))
            {
              bool2 = bool1;
              bool4 = TextUtils.equals("com.google.android.gm.permission.READ_CONTENT_PROVIDER", ((ProviderInfo)localObject).readPermission);
              bool3 = bool1;
              if (bool4) {
                bool3 = true;
              }
            }
            i += 1;
            bool1 = bool3;
          }
        }
        i += 1;
      }
      return bool3;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      bool3 = bool2;
    }
  }
  
  public static List<ResolveInfo> filterPkgs(List<ResolveInfo> paramList, String[] paramArrayOfString)
  {
    if ((paramList == null) || (paramList.size() == 0) || (paramArrayOfString == null) || (paramArrayOfString.length == 0)) {
      return paramList;
    }
    ArrayList localArrayList = new ArrayList(paramList);
    Iterator localIterator = paramList.iterator();
    label132:
    for (;;)
    {
      paramList = localArrayList;
      if (!localIterator.hasNext()) {
        break;
      }
      paramList = (ResolveInfo)localIterator.next();
      if ((paramList != null) && (paramList.activityInfo != null) && (paramList.activityInfo.packageName != null))
      {
        int j = paramArrayOfString.length;
        int i = 0;
        for (;;)
        {
          if (i >= j) {
            break label132;
          }
          String str = paramArrayOfString[i];
          if (paramList.activityInfo.packageName.equals(str))
          {
            localArrayList.remove(paramList);
            break;
          }
          i += 1;
        }
      }
    }
  }
  
  public static List<PackageInfo> getAllInstalledApps(Context paramContext)
  {
    return paramContext.getPackageManager().getInstalledPackages(0);
  }
  
  public static Drawable getApkIcon(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    Object localObject = paramContext.getPackageArchiveInfo(paramString, 1);
    if (localObject != null)
    {
      localObject = ((PackageInfo)localObject).applicationInfo;
      ((ApplicationInfo)localObject).sourceDir = paramString;
      ((ApplicationInfo)localObject).publicSourceDir = paramString;
      try
      {
        paramContext = ((ApplicationInfo)localObject).loadIcon(paramContext);
        return paramContext;
      }
      catch (OutOfMemoryError paramContext) {}
    }
    return null;
  }
  
  public static long getApkSize(PackageManager paramPackageManager, String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramPackageManager == null)) {}
    for (;;)
    {
      return 0L;
      try
      {
        paramPackageManager = paramPackageManager.getApplicationInfo(paramString, 0);
        if ((paramPackageManager != null) && (paramPackageManager.sourceDir != null))
        {
          long l = new File(paramPackageManager.sourceDir).length();
          return l;
        }
      }
      catch (PackageManager.NameNotFoundException paramPackageManager)
      {
        paramPackageManager.printStackTrace();
      }
    }
    return 0L;
  }
  
  public static Context getAppContext(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.createPackageContext(paramString, 2);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static Drawable getAppIcon(Context paramContext, String paramString)
  {
    if (paramString == null) {}
    for (;;)
    {
      return null;
      try
      {
        paramContext = paramContext.getPackageManager();
        paramString = paramContext.getApplicationInfo(paramString, 0);
        if (paramString != null)
        {
          paramContext = paramString.loadIcon(paramContext);
          return paramContext;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext.printStackTrace();
        return null;
      }
      catch (OutOfMemoryError paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return null;
  }
  
  public static long getAppInstallTime(Context paramContext, String paramString, long paramLong)
  {
    try
    {
      long l = paramContext.getPackageManager().getPackageInfo(paramString, 0).firstInstallTime;
      return l;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return paramLong;
  }
  
  public static String getAppLabel(Context paramContext, String paramString)
  {
    if (paramString == null) {}
    for (;;)
    {
      return null;
      try
      {
        paramContext = paramContext.getPackageManager();
        paramString = paramContext.getApplicationInfo(paramString, 0);
        if (paramString != null)
        {
          paramContext = paramContext.getApplicationLabel(paramString).toString();
          return paramContext;
        }
      }
      catch (Exception paramContext) {}
    }
    return null;
  }
  
  public static String getAppLable(Context paramContext, String paramString)
  {
    if (paramString == null) {}
    for (;;)
    {
      return null;
      try
      {
        paramContext = paramContext.getPackageManager();
        paramString = paramContext.getApplicationInfo(paramString, 0);
        if (paramString != null)
        {
          paramContext = paramContext.getApplicationLabel(paramString).toString();
          return paramContext;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return null;
  }
  
  public static PackageInfo getAppPackageInfo(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static int getAppVersionCode(Context paramContext)
  {
    return getAppVersionCode(paramContext, paramContext.getPackageName());
  }
  
  public static int getAppVersionCode(Context paramContext, String paramString)
  {
    int i = -1;
    if ((paramContext == null) || (paramString == null)) {
      return -1;
    }
    try
    {
      int j = paramContext.getPackageManager().getPackageInfo(paramString, 1024).versionCode;
      i = j;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        LogUtils.e("matt", "Error :" + paramString + " is not exist.");
      }
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return i;
  }
  
  public static String getAppVersionName(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return getAppVersionName(paramContext, paramContext.getPackageName());
  }
  
  public static String getAppVersionName(Context paramContext, String paramString)
  {
    String str = "";
    if ((paramContext == null) || (paramString == null)) {
      return "";
    }
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 1024).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        LogUtils.e("matt", "Error :" + paramString + " is not exist.");
        paramContext = str;
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = str;
      }
    }
  }
  
  public static ApplicationInfo getApplicationInfo(Context paramContext, String paramString)
  {
    if (paramString == null) {
      return null;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramString, 0);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String getCurrProcessName(Context paramContext)
  {
    if (sCurrProcessName == null) {
      sCurrProcessName = getCurrProcessNameSystem(paramContext);
    }
    return sCurrProcessName;
  }
  
  /* Error */
  private static String getCurrProcessNameSystem(Context paramContext)
  {
    // Byte code:
    //   0: invokestatic 259	android/os/Process:myPid	()I
    //   3: istore_1
    //   4: aload_0
    //   5: ldc_w 261
    //   8: invokevirtual 265	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   11: checkcast 267	android/app/ActivityManager
    //   14: invokevirtual 271	android/app/ActivityManager:getRunningAppProcesses	()Ljava/util/List;
    //   17: astore_0
    //   18: aload_0
    //   19: ifnull +44 -> 63
    //   22: aload_0
    //   23: invokeinterface 97 1 0
    //   28: astore_0
    //   29: aload_0
    //   30: invokeinterface 103 1 0
    //   35: ifeq +28 -> 63
    //   38: aload_0
    //   39: invokeinterface 107 1 0
    //   44: checkcast 273	android/app/ActivityManager$RunningAppProcessInfo
    //   47: astore_2
    //   48: aload_2
    //   49: getfield 276	android/app/ActivityManager$RunningAppProcessInfo:pid	I
    //   52: iload_1
    //   53: if_icmpne -24 -> 29
    //   56: aload_2
    //   57: getfield 279	android/app/ActivityManager$RunningAppProcessInfo:processName	Ljava/lang/String;
    //   60: astore_2
    //   61: aload_2
    //   62: areturn
    //   63: aconst_null
    //   64: astore_2
    //   65: aconst_null
    //   66: astore 4
    //   68: aconst_null
    //   69: astore 5
    //   71: aconst_null
    //   72: astore 7
    //   74: aload 5
    //   76: astore_3
    //   77: invokestatic 285	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   80: new 220	java/lang/StringBuilder
    //   83: dup
    //   84: invokespecial 221	java/lang/StringBuilder:<init>	()V
    //   87: ldc_w 287
    //   90: invokevirtual 227	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: iload_1
    //   94: invokevirtual 290	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   97: invokevirtual 230	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   100: invokevirtual 294	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   103: astore_0
    //   104: aload_0
    //   105: astore 4
    //   107: aload_0
    //   108: astore_2
    //   109: aload 5
    //   111: astore_3
    //   112: new 296	java/io/BufferedReader
    //   115: dup
    //   116: new 298	java/io/InputStreamReader
    //   119: dup
    //   120: aload_0
    //   121: invokevirtual 304	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   124: invokespecial 307	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   127: invokespecial 310	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   130: astore 5
    //   132: aload 5
    //   134: invokevirtual 313	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   137: ifnull +61 -> 198
    //   140: aload 5
    //   142: invokevirtual 313	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   145: astore_2
    //   146: aload_2
    //   147: ifnull +51 -> 198
    //   150: aload_2
    //   151: ldc_w 315
    //   154: ldc_w 316
    //   157: invokevirtual 320	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   160: astore_2
    //   161: aload_2
    //   162: aload_2
    //   163: arraylength
    //   164: iconst_1
    //   165: isub
    //   166: aaload
    //   167: astore_3
    //   168: aload 5
    //   170: ifnull +8 -> 178
    //   173: aload 5
    //   175: invokevirtual 323	java/io/BufferedReader:close	()V
    //   178: aload_3
    //   179: astore_2
    //   180: aload_0
    //   181: ifnull -120 -> 61
    //   184: aload_0
    //   185: invokevirtual 326	java/lang/Process:destroy	()V
    //   188: aload_3
    //   189: areturn
    //   190: astore_2
    //   191: aload_2
    //   192: invokevirtual 327	java/io/IOException:printStackTrace	()V
    //   195: goto -17 -> 178
    //   198: aload 5
    //   200: ifnull +8 -> 208
    //   203: aload 5
    //   205: invokevirtual 323	java/io/BufferedReader:close	()V
    //   208: aload_0
    //   209: ifnull +109 -> 318
    //   212: aload_0
    //   213: invokevirtual 326	java/lang/Process:destroy	()V
    //   216: aconst_null
    //   217: areturn
    //   218: astore_2
    //   219: aload_2
    //   220: invokevirtual 327	java/io/IOException:printStackTrace	()V
    //   223: goto -15 -> 208
    //   226: astore 6
    //   228: aload 7
    //   230: astore 5
    //   232: aload 4
    //   234: astore_0
    //   235: aload_0
    //   236: astore_2
    //   237: aload 5
    //   239: astore_3
    //   240: aload 6
    //   242: invokevirtual 328	java/lang/Throwable:printStackTrace	()V
    //   245: aload 5
    //   247: ifnull +8 -> 255
    //   250: aload 5
    //   252: invokevirtual 323	java/io/BufferedReader:close	()V
    //   255: aload_0
    //   256: ifnull -40 -> 216
    //   259: aload_0
    //   260: invokevirtual 326	java/lang/Process:destroy	()V
    //   263: goto -47 -> 216
    //   266: astore_2
    //   267: aload_2
    //   268: invokevirtual 327	java/io/IOException:printStackTrace	()V
    //   271: goto -16 -> 255
    //   274: astore 4
    //   276: aload_2
    //   277: astore_0
    //   278: aload_3
    //   279: ifnull +7 -> 286
    //   282: aload_3
    //   283: invokevirtual 323	java/io/BufferedReader:close	()V
    //   286: aload_0
    //   287: ifnull +7 -> 294
    //   290: aload_0
    //   291: invokevirtual 326	java/lang/Process:destroy	()V
    //   294: aload 4
    //   296: athrow
    //   297: astore_2
    //   298: aload_2
    //   299: invokevirtual 327	java/io/IOException:printStackTrace	()V
    //   302: goto -16 -> 286
    //   305: astore 4
    //   307: aload 5
    //   309: astore_3
    //   310: goto -32 -> 278
    //   313: astore 6
    //   315: goto -80 -> 235
    //   318: goto -102 -> 216
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	321	0	paramContext	Context
    //   3	91	1	i	int
    //   47	133	2	localObject1	Object
    //   190	2	2	localIOException1	java.io.IOException
    //   218	2	2	localIOException2	java.io.IOException
    //   236	1	2	localContext1	Context
    //   266	11	2	localIOException3	java.io.IOException
    //   297	2	2	localIOException4	java.io.IOException
    //   76	234	3	localObject2	Object
    //   66	167	4	localContext2	Context
    //   274	21	4	localObject3	Object
    //   305	1	4	localObject4	Object
    //   69	239	5	localObject5	Object
    //   226	15	6	localThrowable1	Throwable
    //   313	1	6	localThrowable2	Throwable
    //   72	157	7	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   173	178	190	java/io/IOException
    //   203	208	218	java/io/IOException
    //   77	104	226	java/lang/Throwable
    //   112	132	226	java/lang/Throwable
    //   250	255	266	java/io/IOException
    //   77	104	274	finally
    //   112	132	274	finally
    //   240	245	274	finally
    //   282	286	297	java/io/IOException
    //   132	146	305	finally
    //   150	168	305	finally
    //   132	146	313	java/lang/Throwable
    //   150	168	313	java/lang/Throwable
  }
  
  public static String getDefaultLauncher(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 65536);
    if (paramContext.activityInfo == null) {
      return null;
    }
    if (paramContext.activityInfo.packageName.equals("android")) {
      return null;
    }
    return paramContext.activityInfo.packageName;
  }
  
  public static List<ApplicationInfo> getInstallAppInfo(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    return paramContext.getPackageManager().getInstalledApplications(8192);
  }
  
  public static List<PackageInfo> getInstalledApps(Context paramContext)
  {
    Object localObject1 = paramContext.getPackageManager();
    paramContext = paramContext.getPackageName();
    Object localObject2 = ((PackageManager)localObject1).getInstalledPackages(0);
    localObject1 = new ArrayList();
    localObject2 = ((List)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject2).next();
      if (((localPackageInfo.applicationInfo.flags & 0x1) == 0) && (!paramContext.equals(localPackageInfo.packageName))) {
        ((List)localObject1).add(localPackageInfo);
      }
    }
    return localObject1;
  }
  
  public static List<ResolveInfo> getLauncherApps(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    return paramContext.queryIntentActivities(localIntent, 0);
  }
  
  public static List<ResolveInfo> getLauncherApps(Context paramContext, String paramString)
  {
    Object localObject = null;
    paramContext = paramContext.getPackageManager();
    paramString = new Intent(paramString);
    paramString.addCategory("android.intent.category.LAUNCHER");
    try
    {
      paramContext = paramContext.queryIntentActivities(paramString, 0);
      return paramContext;
    }
    catch (OutOfMemoryError paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = localObject;
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = localObject;
      }
    }
  }
  
  public static List<PackageInfo> getLauncherableApps(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = paramContext.getPackageName();
    Object localObject = localPackageManager.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((localPackageManager.getLaunchIntentForPackage(localPackageInfo.applicationInfo.packageName) != null) && (!paramContext.equals(localPackageInfo.packageName))) {
        localArrayList.add(localPackageInfo);
      }
    }
    return localArrayList;
  }
  
  public static long getMyAppInstallTime(Context paramContext, long paramLong)
  {
    if (sInstallTime == null)
    {
      long l = getAppInstallTime(paramContext, paramContext.getPackageName(), 0L);
      if (l > 0L) {
        sInstallTime = Long.valueOf(l);
      }
    }
    if (sInstallTime != null) {
      paramLong = sInstallTime.longValue();
    }
    return paramLong;
  }
  
  public static String getPackageName(Intent paramIntent)
  {
    if (paramIntent != null)
    {
      paramIntent = paramIntent.getComponent();
      if (paramIntent != null) {
        return paramIntent.getPackageName();
      }
    }
    return null;
  }
  
  public static int getPidByProcessName(Context paramContext, String paramString)
  {
    int j = 0;
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
    do
    {
      i = j;
      if (!paramContext.hasNext()) {
        break;
      }
      localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
    } while (!localRunningAppProcessInfo.processName.equals(paramString));
    int i = localRunningAppProcessInfo.pid;
    return i;
  }
  
  public static Set<String> getRunningApps(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return ProcessHelperUtil.getRunningAppProcesses(paramContext).keySet();
    }
    return getRunningAppsBelowApiLevel20(paramContext);
  }
  
  private static Set<String> getRunningAppsBelowApiLevel20(Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    while (paramContext.hasNext())
    {
      Object localObject = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if (((ActivityManager.RunningAppProcessInfo)localObject).pkgList != null)
      {
        localObject = ((ActivityManager.RunningAppProcessInfo)localObject).pkgList;
        int j = localObject.length;
        int i = 0;
        while (i < j)
        {
          localHashSet.add(localObject[i]);
          i += 1;
        }
      }
    }
    return localHashSet;
  }
  
  public static String getSelfTopActivityName(Context paramContext)
  {
    try
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1);
      if (paramContext.size() > 0)
      {
        paramContext = ((ActivityManager.RunningTaskInfo)paramContext.get(0)).topActivity.getClassName();
        return paramContext;
      }
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static ServiceInfo getServiceInfo(Context paramContext, ComponentName paramComponentName)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getServiceInfo(paramComponentName, 4);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  @Deprecated
  public static String getTopAppPackageName(Context paramContext)
  {
    try
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1);
      if (paramContext.size() > 0)
      {
        paramContext = ((ActivityManager.RunningTaskInfo)paramContext.get(0)).topActivity.getPackageName();
        return paramContext;
      }
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  @Deprecated
  public static String getTopAppPkgName(Context paramContext)
  {
    if (Machine.IS_SDK_ABOVE_L) {
      return null;
    }
    try
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1);
      if (paramContext.size() > 0)
      {
        paramContext = ((ActivityManager.RunningTaskInfo)paramContext.get(0)).topActivity.getPackageName();
        return paramContext;
      }
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static int getUidByPkgName(Context paramContext, String paramString)
  {
    try
    {
      int i = paramContext.getPackageManager().getApplicationInfo(paramString, 1).uid;
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static boolean gotoBrowser(Context paramContext, String paramString)
  {
    bool2 = false;
    if (paramString == null) {
      return false;
    }
    Object localObject = Uri.parse(paramString);
    bool1 = bool2;
    if (localObject != null)
    {
      localObject = new Intent("android.intent.action.VIEW", (Uri)localObject);
      ((Intent)localObject).setFlags(268435456);
    }
    try
    {
      paramContext.startActivity((Intent)localObject);
      bool1 = true;
    }
    catch (ActivityNotFoundException paramContext)
    {
      for (;;)
      {
        LogUtils.i("matt", "gotoBrowser error, uri = " + paramString);
        bool1 = bool2;
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        LogUtils.i("matt", "gotoBrowser error, uri = " + paramString);
        bool1 = bool2;
      }
    }
    return bool1;
  }
  
  public static void installApk(Context paramContext, File paramFile)
  {
    if (paramFile.exists())
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setFlags(268435456);
      localIntent.setDataAndType(Uri.fromFile(paramFile), "application/vnd.android.package-archive");
      ((ContextWrapper)paramContext).startActivity(localIntent);
    }
  }
  
  public static boolean isAppExist(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {
      return false;
    }
    try
    {
      paramContext.getPackageManager().getPackageInfo(paramString, 1024);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      return false;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean isAppRunning(Context paramContext, String paramString)
  {
    paramContext = getRunningApps(paramContext);
    return (paramContext != null) && (paramContext.contains(paramString));
  }
  
  @Deprecated
  public static boolean isAppRunningInForground(Context paramContext, String paramString)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return isForgroundApp(paramContext, paramString);
    }
    return isTopActivity(paramContext, paramString);
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
  
  public static boolean isBrowser(Context paramContext, String paramString)
  {
    if (paramString == null) {}
    do
    {
      return false;
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.addCategory("android.intent.category.DEFAULT");
      localIntent.addCategory("android.intent.category.BROWSABLE");
      localIntent.setData(Uri.parse("http://www.baidu.com"));
      localIntent.setPackage(paramString);
      paramContext = paramContext.getPackageManager().queryIntentActivities(localIntent, 0);
    } while ((paramContext == null) || (paramContext.size() <= 0));
    return true;
  }
  
  public static boolean isDefaultLauncher(Context paramContext)
  {
    return paramContext.getPackageName().equals(getDefaultLauncher(paramContext));
  }
  
  @Deprecated
  private static boolean isForgroundApp(Context paramContext, String paramString)
  {
    try
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
      if (paramContext == null) {
        return false;
      }
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        if (localRunningAppProcessInfo.importance == 100) {
          if (!localRunningAppProcessInfo.processName.equals(paramString))
          {
            boolean bool = Arrays.asList(localRunningAppProcessInfo.pkgList).contains(paramString);
            if (!bool) {
              break;
            }
          }
          else
          {
            return true;
          }
        }
      }
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean isInstallOnSDCard(PackageManager paramPackageManager, String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramPackageManager == null)) {}
    for (;;)
    {
      return false;
      try
      {
        int i = paramPackageManager.getApplicationInfo(paramString, 0).flags;
        if ((i & 0x40000) != 0) {
          return true;
        }
      }
      catch (PackageManager.NameNotFoundException paramPackageManager)
      {
        paramPackageManager.printStackTrace();
      }
    }
    return false;
  }
  
  public static boolean isInternalApp(Context paramContext, Intent paramIntent)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    PackageManager localPackageManager;
    if (paramContext != null) {
      localPackageManager = paramContext.getPackageManager();
    }
    try
    {
      paramContext = Environment.getDataDirectory().getAbsolutePath();
      paramIntent = localPackageManager.getActivityInfo(paramIntent.getComponent(), 0).applicationInfo.publicSourceDir;
      bool1 = bool2;
      if (paramIntent != null)
      {
        bool1 = bool2;
        if (paramIntent.length() > 0) {
          bool1 = paramIntent.startsWith(paramContext);
        }
      }
      return bool1;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean isServiceRunning(ActivityManager paramActivityManager, String paramString1, String paramString2)
  {
    boolean bool2 = false;
    paramActivityManager = paramActivityManager.getRunningServices(Integer.MAX_VALUE);
    int i;
    int j;
    if (paramActivityManager == null)
    {
      i = 0;
      j = 0;
    }
    for (;;)
    {
      boolean bool1 = bool2;
      if (j < i)
      {
        ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)paramActivityManager.get(j);
        if ((localRunningServiceInfo != null) && (localRunningServiceInfo.service != null))
        {
          String str1 = localRunningServiceInfo.service.getPackageName();
          String str2 = localRunningServiceInfo.service.getClassName();
          if ((str1 != null) && (str1.contains(paramString1)) && (str2 != null) && (str2.contains(paramString2)))
          {
            LogUtils.i("Notification", "package = " + localRunningServiceInfo.service.getPackageName() + " class = " + localRunningServiceInfo.service.getClassName());
            bool1 = true;
          }
        }
      }
      else
      {
        return bool1;
        i = paramActivityManager.size();
        break;
      }
      j += 1;
    }
  }
  
  public static boolean isServiceRunning(Context paramContext, String paramString1, String paramString2)
  {
    return isServiceRunning((ActivityManager)paramContext.getSystemService("activity"), paramString1, paramString2);
  }
  
  public static boolean isSystemApp(Context paramContext, Intent paramIntent)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramContext != null) {
      paramContext = paramContext.getPackageManager();
    }
    try
    {
      paramContext = paramContext.getActivityInfo(paramIntent.getComponent(), 0).applicationInfo;
      bool1 = bool2;
      if (paramContext != null)
      {
        if ((paramContext.flags & 0x1) == 0)
        {
          int i = paramContext.flags;
          if ((i & 0x80) == 0) {}
        }
        else
        {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      return false;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean isSystemApp(Context paramContext, String paramString)
  {
    boolean bool = false;
    paramContext = getApplicationInfo(paramContext, paramString);
    if (paramContext == null) {
      return false;
    }
    if (((paramContext.flags & 0x1) != 0) || ((paramContext.flags & 0x80) != 0)) {
      bool = true;
    }
    return bool;
  }
  
  @Deprecated
  private static boolean isTopActivity(Context paramContext, String paramString)
  {
    try
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1);
      if (paramContext.size() > 0)
      {
        boolean bool = paramString.equals(((ActivityManager.RunningTaskInfo)paramContext.get(0)).topActivity.getPackageName());
        if (bool) {
          return true;
        }
      }
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static void killProcess()
  {
    killProcess(Process.myPid());
  }
  
  public static void killProcess(int paramInt)
  {
    new Exception().printStackTrace();
    Process.killProcess(paramInt);
  }
  
  public static void safeStartActivity(Context paramContext, Intent paramIntent)
  {
    if (paramContext != null) {}
    try
    {
      paramContext.startActivity(paramIntent);
      return;
    }
    catch (ActivityNotFoundException paramContext)
    {
      LogUtils.e("matt", "saveStartActivity err " + paramContext.getMessage());
      return;
    }
    catch (SecurityException paramContext)
    {
      LogUtils.e("matt", "saveStartActivity err " + paramContext.getMessage());
      return;
    }
    catch (Exception paramContext)
    {
      LogUtils.e("matt", "saveStartActivity err " + paramContext.getMessage());
    }
  }
  
  public static boolean safeStartActivity(Context paramContext, String paramString)
  {
    if (paramContext != null) {}
    try
    {
      if (!TextUtils.isEmpty(paramString))
      {
        Intent localIntent2 = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
        Intent localIntent1 = localIntent2;
        if (localIntent2 == null)
        {
          PackageInfo localPackageInfo = getAppPackageInfo(paramContext, paramString);
          localIntent1 = localIntent2;
          if (localPackageInfo != null)
          {
            localIntent1 = localIntent2;
            if (!TextUtils.isEmpty(localPackageInfo.applicationInfo.className))
            {
              localIntent1 = new Intent();
              localIntent1.addFlags(268435456);
              localIntent1.setClassName(paramString, localPackageInfo.applicationInfo.className);
            }
          }
        }
        if (localIntent1 != null)
        {
          paramContext.startActivity(localIntent1);
          return true;
        }
      }
    }
    catch (Exception paramContext)
    {
      return false;
    }
    catch (ActivityNotFoundException paramContext)
    {
      for (;;) {}
    }
  }
  
  public static void safeStartActivityForResult(Activity paramActivity, Intent paramIntent, int paramInt)
  {
    if (paramActivity != null) {}
    try
    {
      paramActivity.startActivityForResult(paramIntent, paramInt);
      return;
    }
    catch (ActivityNotFoundException paramActivity)
    {
      LogUtils.e("matt", "saveStartActivityForResult err " + paramActivity.getMessage());
      return;
    }
    catch (SecurityException paramActivity)
    {
      LogUtils.e("matt", "saveStartActivityForResult err " + paramActivity.getMessage());
    }
  }
  
  public static void showAppDetails(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    int i = Build.VERSION.SDK_INT;
    if (i >= 9)
    {
      localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
      localIntent.setData(Uri.fromParts("package", paramString, null));
    }
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      String str;
      paramContext.printStackTrace();
    }
    if (i == 8) {}
    for (str = "pkg";; str = "com.android.settings.ApplicationPkgName")
    {
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
      localIntent.putExtra(str, paramString);
      break;
    }
  }
  
  public static void uninstallApk(Context paramContext, String paramString)
  {
    paramString = new Intent("android.intent.action.DELETE", Uri.parse("package:" + paramString));
    if (!"Xiaomi".equals(Build.BRAND)) {
      paramString.setFlags(1073741824);
    }
    paramContext.startActivity(paramString);
  }
  
  public static void uninstallApp(Activity paramActivity, Uri paramUri)
  {
    paramActivity.startActivity(new Intent("android.intent.action.DELETE", paramUri));
  }
  
  public static void uninstallPackage(Activity paramActivity, String paramString)
  {
    uninstallApp(paramActivity, Uri.parse("package:" + paramString));
  }
}
