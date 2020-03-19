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
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager().getPackageInfo("com.google.android.gm", 4104);
        bool2 = bool4;
        if (paramContext.permissions != null)
        {
          bool2 = bool4;
          int j = paramContext.permissions.length;
          i = 0;
          if (i < j)
          {
            bool2 = bool4;
            Object localObject = paramContext.permissions[i];
            bool2 = bool4;
            boolean bool3;
            if ("com.google.android.gm.permission.READ_CONTENT_PROVIDER".equals(((PermissionInfo)localObject).name))
            {
              bool2 = bool4;
              if (((PermissionInfo)localObject).protectionLevel < 2)
              {
                i = 1;
                bool3 = bool5;
                if (i == 0) {
                  break label218;
                }
                bool2 = bool4;
                bool3 = bool5;
                if (paramContext.providers == null) {
                  break label218;
                }
                bool2 = bool4;
                j = paramContext.providers.length;
                i = 0;
                bool3 = bool1;
                if (i >= j) {
                  break label218;
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
                continue;
              }
            }
            i += 1;
            continue;
            return bool3;
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        bool3 = bool2;
      }
      label218:
      int i = 0;
    }
  }
  
  public static List<ResolveInfo> filterPkgs(List<ResolveInfo> paramList, String[] paramArrayOfString)
  {
    if ((paramList == null) || (paramList.size() == 0) || (paramArrayOfString == null) || (paramArrayOfString.length == 0)) {
      return paramList;
    }
    ArrayList localArrayList = new ArrayList(paramList);
    paramList = paramList.iterator();
    label132:
    while (paramList.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramList.next();
      if ((localResolveInfo != null) && (localResolveInfo.activityInfo != null) && (localResolveInfo.activityInfo.packageName != null))
      {
        int j = paramArrayOfString.length;
        int i = 0;
        for (;;)
        {
          if (i >= j) {
            break label132;
          }
          String str = paramArrayOfString[i];
          if (localResolveInfo.activityInfo.packageName.equals(str))
          {
            localArrayList.remove(localResolveInfo);
            break;
          }
          i += 1;
        }
      }
    }
    return localArrayList;
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
    if ((paramContext == null) || (paramString == null)) {
      return -1;
    }
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramString, 1024).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      LogUtils.e("matt", "Error :" + paramString + " is not exist.");
      return -1;
    }
    catch (Exception paramContext) {}
    return -1;
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
      LogUtils.e("matt", "Error :" + paramString + " is not exist.");
      return "";
    }
    catch (Exception paramContext) {}
    return "";
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
    //   0: aconst_null
    //   1: astore 4
    //   3: invokestatic 259	android/os/Process:myPid	()I
    //   6: istore_1
    //   7: aload_0
    //   8: ldc_w 261
    //   11: invokevirtual 265	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   14: checkcast 267	android/app/ActivityManager
    //   17: invokevirtual 271	android/app/ActivityManager:getRunningAppProcesses	()Ljava/util/List;
    //   20: astore_0
    //   21: aload_0
    //   22: ifnull +44 -> 66
    //   25: aload_0
    //   26: invokeinterface 97 1 0
    //   31: astore_0
    //   32: aload_0
    //   33: invokeinterface 103 1 0
    //   38: ifeq +28 -> 66
    //   41: aload_0
    //   42: invokeinterface 107 1 0
    //   47: checkcast 273	android/app/ActivityManager$RunningAppProcessInfo
    //   50: astore_2
    //   51: aload_2
    //   52: getfield 276	android/app/ActivityManager$RunningAppProcessInfo:pid	I
    //   55: iload_1
    //   56: if_icmpne -24 -> 32
    //   59: aload_2
    //   60: getfield 279	android/app/ActivityManager$RunningAppProcessInfo:processName	Ljava/lang/String;
    //   63: astore_2
    //   64: aload_2
    //   65: areturn
    //   66: invokestatic 285	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   69: new 220	java/lang/StringBuilder
    //   72: dup
    //   73: invokespecial 221	java/lang/StringBuilder:<init>	()V
    //   76: ldc_w 287
    //   79: invokevirtual 227	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   82: iload_1
    //   83: invokevirtual 290	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   86: invokevirtual 230	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   89: invokevirtual 294	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   92: astore_0
    //   93: new 296	java/io/BufferedReader
    //   96: dup
    //   97: new 298	java/io/InputStreamReader
    //   100: dup
    //   101: aload_0
    //   102: invokevirtual 304	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   105: invokespecial 307	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   108: invokespecial 310	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   111: astore_2
    //   112: aload_2
    //   113: astore 4
    //   115: aload_0
    //   116: astore_3
    //   117: aload_2
    //   118: invokevirtual 313	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   121: ifnull +82 -> 203
    //   124: aload_2
    //   125: astore 4
    //   127: aload_0
    //   128: astore_3
    //   129: aload_2
    //   130: invokevirtual 313	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   133: astore 5
    //   135: aload 5
    //   137: ifnull +66 -> 203
    //   140: aload_2
    //   141: astore 4
    //   143: aload_0
    //   144: astore_3
    //   145: aload 5
    //   147: ldc_w 315
    //   150: ldc_w 316
    //   153: invokevirtual 320	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   156: astore 5
    //   158: aload_2
    //   159: astore 4
    //   161: aload_0
    //   162: astore_3
    //   163: aload 5
    //   165: aload 5
    //   167: arraylength
    //   168: iconst_1
    //   169: isub
    //   170: aaload
    //   171: astore 5
    //   173: aload_2
    //   174: ifnull +7 -> 181
    //   177: aload_2
    //   178: invokevirtual 323	java/io/BufferedReader:close	()V
    //   181: aload 5
    //   183: astore_2
    //   184: aload_0
    //   185: ifnull -121 -> 64
    //   188: aload_0
    //   189: invokevirtual 326	java/lang/Process:destroy	()V
    //   192: aload 5
    //   194: areturn
    //   195: astore_2
    //   196: aload_2
    //   197: invokevirtual 327	java/io/IOException:printStackTrace	()V
    //   200: goto -19 -> 181
    //   203: aload_2
    //   204: ifnull +7 -> 211
    //   207: aload_2
    //   208: invokevirtual 323	java/io/BufferedReader:close	()V
    //   211: aload_0
    //   212: ifnull +7 -> 219
    //   215: aload_0
    //   216: invokevirtual 326	java/lang/Process:destroy	()V
    //   219: aconst_null
    //   220: areturn
    //   221: astore_2
    //   222: aload_2
    //   223: invokevirtual 327	java/io/IOException:printStackTrace	()V
    //   226: goto -15 -> 211
    //   229: astore 5
    //   231: aconst_null
    //   232: astore_2
    //   233: aconst_null
    //   234: astore_0
    //   235: aload_2
    //   236: astore 4
    //   238: aload_0
    //   239: astore_3
    //   240: aload 5
    //   242: invokevirtual 328	java/lang/Throwable:printStackTrace	()V
    //   245: aload_2
    //   246: ifnull +7 -> 253
    //   249: aload_2
    //   250: invokevirtual 323	java/io/BufferedReader:close	()V
    //   253: aload_0
    //   254: ifnull -35 -> 219
    //   257: aload_0
    //   258: invokevirtual 326	java/lang/Process:destroy	()V
    //   261: goto -42 -> 219
    //   264: astore_2
    //   265: aload_2
    //   266: invokevirtual 327	java/io/IOException:printStackTrace	()V
    //   269: goto -16 -> 253
    //   272: astore_2
    //   273: aconst_null
    //   274: astore_0
    //   275: aload 4
    //   277: ifnull +8 -> 285
    //   280: aload 4
    //   282: invokevirtual 323	java/io/BufferedReader:close	()V
    //   285: aload_0
    //   286: ifnull +7 -> 293
    //   289: aload_0
    //   290: invokevirtual 326	java/lang/Process:destroy	()V
    //   293: aload_2
    //   294: athrow
    //   295: astore_3
    //   296: aload_3
    //   297: invokevirtual 327	java/io/IOException:printStackTrace	()V
    //   300: goto -15 -> 285
    //   303: astore_2
    //   304: goto -29 -> 275
    //   307: astore_2
    //   308: aload_3
    //   309: astore_0
    //   310: goto -35 -> 275
    //   313: astore 5
    //   315: aconst_null
    //   316: astore_2
    //   317: goto -82 -> 235
    //   320: astore 5
    //   322: goto -87 -> 235
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	325	0	paramContext	Context
    //   6	77	1	i	int
    //   50	134	2	localObject1	Object
    //   195	13	2	localIOException1	java.io.IOException
    //   221	2	2	localIOException2	java.io.IOException
    //   232	18	2	localObject2	Object
    //   264	2	2	localIOException3	java.io.IOException
    //   272	22	2	localObject3	Object
    //   303	1	2	localObject4	Object
    //   307	1	2	localObject5	Object
    //   316	1	2	localObject6	Object
    //   116	124	3	localContext	Context
    //   295	14	3	localIOException4	java.io.IOException
    //   1	280	4	localObject7	Object
    //   133	60	5	localObject8	Object
    //   229	12	5	localThrowable1	Throwable
    //   313	1	5	localThrowable2	Throwable
    //   320	1	5	localThrowable3	Throwable
    // Exception table:
    //   from	to	target	type
    //   177	181	195	java/io/IOException
    //   207	211	221	java/io/IOException
    //   66	93	229	java/lang/Throwable
    //   249	253	264	java/io/IOException
    //   66	93	272	finally
    //   280	285	295	java/io/IOException
    //   93	112	303	finally
    //   117	124	307	finally
    //   129	135	307	finally
    //   145	158	307	finally
    //   163	173	307	finally
    //   240	245	307	finally
    //   93	112	313	java/lang/Throwable
    //   117	124	320	java/lang/Throwable
    //   129	135	320	java/lang/Throwable
    //   145	158	320	java/lang/Throwable
    //   163	173	320	java/lang/Throwable
  }
  
  public static String getDefaultLauncher(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 65536);
    if (paramContext.activityInfo == null) {}
    while (paramContext.activityInfo.packageName.equals("android")) {
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
      paramContext.printStackTrace();
      return null;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
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
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if (localRunningAppProcessInfo.processName.equals(paramString)) {
        return localRunningAppProcessInfo.pid;
      }
    }
    return 0;
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
    if (paramString == null) {}
    do
    {
      return false;
      localObject = Uri.parse(paramString);
    } while (localObject == null);
    Object localObject = new Intent("android.intent.action.VIEW", (Uri)localObject);
    ((Intent)localObject).setFlags(268435456);
    try
    {
      paramContext.startActivity((Intent)localObject);
      return true;
    }
    catch (ActivityNotFoundException paramContext)
    {
      LogUtils.i("matt", "gotoBrowser error, uri = " + paramString);
      return false;
    }
    catch (Exception paramContext)
    {
      LogUtils.i("matt", "gotoBrowser error, uri = " + paramString);
    }
    return false;
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
    catch (Exception paramContext)
    {
      return false;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
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
      if (paramContext != null) {
        if ((paramContext.flags & 0x1) == 0)
        {
          int i = paramContext.flags;
          bool1 = bool2;
          if ((i & 0x80) == 0) {}
        }
        else
        {
          bool1 = true;
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
  
  public static boolean isSystemApp(Context paramContext, String paramString)
  {
    paramContext = getApplicationInfo(paramContext, paramString);
    if (paramContext == null) {}
    while (((paramContext.flags & 0x1) == 0) && ((paramContext.flags & 0x80) == 0)) {
      return false;
    }
    return true;
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
