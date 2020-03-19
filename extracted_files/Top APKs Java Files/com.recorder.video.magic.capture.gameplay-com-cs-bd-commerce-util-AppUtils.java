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
  private static String sCurrProcessName;
  private static Long sInstallTime;
  
  public AppUtils() {}
  
  /* Error */
  public static boolean canReadGmailLabels(Context paramContext)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 5
    //   3: iconst_0
    //   4: istore_2
    //   5: aload_0
    //   6: invokevirtual 30	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   9: ldc 32
    //   11: sipush 4104
    //   14: invokevirtual 38	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   17: astore_0
    //   18: aload_0
    //   19: getfield 44	android/content/pm/PackageInfo:permissions	[Landroid/content/pm/PermissionInfo;
    //   22: ifnull +163 -> 185
    //   25: aload_0
    //   26: getfield 44	android/content/pm/PackageInfo:permissions	[Landroid/content/pm/PermissionInfo;
    //   29: arraylength
    //   30: istore_3
    //   31: iconst_0
    //   32: istore_1
    //   33: iload_1
    //   34: iload_3
    //   35: if_icmpge +150 -> 185
    //   38: aload_0
    //   39: getfield 44	android/content/pm/PackageInfo:permissions	[Landroid/content/pm/PermissionInfo;
    //   42: iload_1
    //   43: aaload
    //   44: astore 7
    //   46: ldc 46
    //   48: aload 7
    //   50: getfield 51	android/content/pm/PermissionInfo:name	Ljava/lang/String;
    //   53: invokevirtual 57	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   56: ifeq +122 -> 178
    //   59: aload 7
    //   61: getfield 61	android/content/pm/PermissionInfo:protectionLevel	I
    //   64: iconst_2
    //   65: if_icmpge +113 -> 178
    //   68: iconst_1
    //   69: istore_1
    //   70: goto +3 -> 73
    //   73: iload 5
    //   75: istore 4
    //   77: iload_1
    //   78: ifeq +90 -> 168
    //   81: iload 5
    //   83: istore 4
    //   85: aload_0
    //   86: getfield 65	android/content/pm/PackageInfo:providers	[Landroid/content/pm/ProviderInfo;
    //   89: ifnull +79 -> 168
    //   92: aload_0
    //   93: getfield 65	android/content/pm/PackageInfo:providers	[Landroid/content/pm/ProviderInfo;
    //   96: arraylength
    //   97: istore_3
    //   98: iconst_0
    //   99: istore 4
    //   101: iload_2
    //   102: istore_1
    //   103: iload_1
    //   104: iload_3
    //   105: if_icmpge +63 -> 168
    //   108: aload_0
    //   109: getfield 65	android/content/pm/PackageInfo:providers	[Landroid/content/pm/ProviderInfo;
    //   112: iload_1
    //   113: aaload
    //   114: astore 7
    //   116: iload 4
    //   118: istore 5
    //   120: ldc 32
    //   122: aload 7
    //   124: getfield 70	android/content/pm/ProviderInfo:authority	Ljava/lang/String;
    //   127: invokevirtual 57	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   130: ifeq +27 -> 157
    //   133: ldc 46
    //   135: aload 7
    //   137: getfield 73	android/content/pm/ProviderInfo:readPermission	Ljava/lang/String;
    //   140: invokestatic 78	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   143: istore 6
    //   145: iload 4
    //   147: istore 5
    //   149: iload 6
    //   151: ifeq +6 -> 157
    //   154: iconst_1
    //   155: istore 5
    //   157: iload_1
    //   158: iconst_1
    //   159: iadd
    //   160: istore_1
    //   161: iload 5
    //   163: istore 4
    //   165: goto -62 -> 103
    //   168: iload 4
    //   170: ireturn
    //   171: astore_0
    //   172: iconst_0
    //   173: ireturn
    //   174: astore_0
    //   175: goto -7 -> 168
    //   178: iload_1
    //   179: iconst_1
    //   180: iadd
    //   181: istore_1
    //   182: goto -149 -> 33
    //   185: iconst_0
    //   186: istore_1
    //   187: goto -114 -> 73
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	190	0	paramContext	Context
    //   32	155	1	i	int
    //   4	98	2	j	int
    //   30	76	3	k	int
    //   75	94	4	bool1	boolean
    //   1	161	5	bool2	boolean
    //   143	7	6	bool3	boolean
    //   44	92	7	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   5	31	171	android/content/pm/PackageManager$NameNotFoundException
    //   38	68	171	android/content/pm/PackageManager$NameNotFoundException
    //   85	98	171	android/content/pm/PackageManager$NameNotFoundException
    //   108	116	174	android/content/pm/PackageManager$NameNotFoundException
    //   120	145	174	android/content/pm/PackageManager$NameNotFoundException
  }
  
  public static List<ResolveInfo> filterPkgs(List<ResolveInfo> paramList, String[] paramArrayOfString)
  {
    if ((paramList != null) && (paramList.size() != 0) && (paramArrayOfString != null))
    {
      if (paramArrayOfString.length == 0) {
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
    return paramList;
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
    }
    try
    {
      paramContext = ((ApplicationInfo)localObject).loadIcon(paramContext);
      return paramContext;
    }
    catch (OutOfMemoryError paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static long getApkSize(PackageManager paramPackageManager, String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      if (paramPackageManager == null) {
        return 0L;
      }
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
      return 0L;
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
    if (paramString == null) {
      return null;
    }
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
    catch (OutOfMemoryError paramContext)
    {
      paramContext.printStackTrace();
      return null;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
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
    Object localObject = null;
    if (paramString == null) {
      return null;
    }
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      paramString = localPackageManager.getApplicationInfo(paramString, 0);
      paramContext = localObject;
      if (paramString != null) {
        paramContext = localPackageManager.getApplicationLabel(paramString).toString();
      }
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String getAppLable(Context paramContext, String paramString)
  {
    if (paramString == null) {
      return null;
    }
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
    return null;
  }
  
  public static PackageInfo getAppPackageInfo(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static int getAppVersionCode(Context paramContext)
  {
    return getAppVersionCode(paramContext, paramContext.getPackageName());
  }
  
  public static int getAppVersionCode(Context paramContext, String paramString)
  {
    if (paramContext != null) {
      if (paramString == null) {
        return -1;
      }
    }
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramString, 1024).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    paramContext = new StringBuilder();
    paramContext.append("Error :");
    paramContext.append(paramString);
    paramContext.append(" is not exist.");
    LogUtils.e("matt", paramContext.toString());
    return -1;
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
    if (paramContext != null) {
      if (paramString == null) {
        return "";
      }
    }
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 1024).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    paramContext = new StringBuilder();
    paramContext.append("Error :");
    paramContext.append(paramString);
    paramContext.append(" is not exist.");
    LogUtils.e("matt", paramContext.toString());
    return "";
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
    //   0: invokestatic 259	android/os/Process:myPid	()I
    //   3: istore_1
    //   4: aload_0
    //   5: ldc_w 261
    //   8: invokevirtual 265	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   11: checkcast 267	android/app/ActivityManager
    //   14: invokevirtual 271	android/app/ActivityManager:getRunningAppProcesses	()Ljava/util/List;
    //   17: astore_0
    //   18: aload_0
    //   19: ifnull +42 -> 61
    //   22: aload_0
    //   23: invokeinterface 95 1 0
    //   28: astore_0
    //   29: aload_0
    //   30: invokeinterface 101 1 0
    //   35: ifeq +26 -> 61
    //   38: aload_0
    //   39: invokeinterface 105 1 0
    //   44: checkcast 273	android/app/ActivityManager$RunningAppProcessInfo
    //   47: astore_2
    //   48: aload_2
    //   49: getfield 276	android/app/ActivityManager$RunningAppProcessInfo:pid	I
    //   52: iload_1
    //   53: if_icmpne -24 -> 29
    //   56: aload_2
    //   57: getfield 279	android/app/ActivityManager$RunningAppProcessInfo:processName	Ljava/lang/String;
    //   60: areturn
    //   61: aconst_null
    //   62: astore_3
    //   63: invokestatic 285	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   66: astore_0
    //   67: new 218	java/lang/StringBuilder
    //   70: dup
    //   71: invokespecial 219	java/lang/StringBuilder:<init>	()V
    //   74: astore_2
    //   75: aload_2
    //   76: ldc_w 287
    //   79: invokevirtual 225	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   82: pop
    //   83: aload_2
    //   84: iload_1
    //   85: invokevirtual 290	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   88: pop
    //   89: aload_0
    //   90: aload_2
    //   91: invokevirtual 228	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   94: invokevirtual 294	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   97: astore_0
    //   98: new 296	java/io/BufferedReader
    //   101: dup
    //   102: new 298	java/io/InputStreamReader
    //   105: dup
    //   106: aload_0
    //   107: invokevirtual 304	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   110: invokespecial 307	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   113: invokespecial 310	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   116: astore_2
    //   117: aload_0
    //   118: astore_3
    //   119: aload_2
    //   120: astore 4
    //   122: aload_2
    //   123: invokevirtual 313	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   126: ifnull +75 -> 201
    //   129: aload_0
    //   130: astore_3
    //   131: aload_2
    //   132: astore 4
    //   134: aload_2
    //   135: invokevirtual 313	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   138: astore 5
    //   140: aload 5
    //   142: ifnull +59 -> 201
    //   145: aload_0
    //   146: astore_3
    //   147: aload_2
    //   148: astore 4
    //   150: aload 5
    //   152: ldc_w 315
    //   155: ldc_w 316
    //   158: invokevirtual 320	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   161: astore 5
    //   163: aload_0
    //   164: astore_3
    //   165: aload_2
    //   166: astore 4
    //   168: aload 5
    //   170: aload 5
    //   172: arraylength
    //   173: iconst_1
    //   174: isub
    //   175: aaload
    //   176: astore 5
    //   178: aload_2
    //   179: invokevirtual 323	java/io/BufferedReader:close	()V
    //   182: goto +8 -> 190
    //   185: astore_2
    //   186: aload_2
    //   187: invokevirtual 324	java/io/IOException:printStackTrace	()V
    //   190: aload_0
    //   191: ifnull +7 -> 198
    //   194: aload_0
    //   195: invokevirtual 327	java/lang/Process:destroy	()V
    //   198: aload 5
    //   200: areturn
    //   201: aload_2
    //   202: invokevirtual 323	java/io/BufferedReader:close	()V
    //   205: goto +8 -> 213
    //   208: astore_2
    //   209: aload_2
    //   210: invokevirtual 324	java/io/IOException:printStackTrace	()V
    //   213: aload_0
    //   214: ifnull +74 -> 288
    //   217: goto +67 -> 284
    //   220: astore 5
    //   222: goto +32 -> 254
    //   225: astore 4
    //   227: aload_0
    //   228: astore_2
    //   229: aload 4
    //   231: astore_0
    //   232: goto +64 -> 296
    //   235: astore 5
    //   237: aconst_null
    //   238: astore_2
    //   239: goto +15 -> 254
    //   242: astore_0
    //   243: aconst_null
    //   244: astore_2
    //   245: goto +51 -> 296
    //   248: astore 5
    //   250: aconst_null
    //   251: astore_0
    //   252: aload_0
    //   253: astore_2
    //   254: aload_0
    //   255: astore_3
    //   256: aload_2
    //   257: astore 4
    //   259: aload 5
    //   261: invokevirtual 328	java/lang/Throwable:printStackTrace	()V
    //   264: aload_2
    //   265: ifnull +15 -> 280
    //   268: aload_2
    //   269: invokevirtual 323	java/io/BufferedReader:close	()V
    //   272: goto +8 -> 280
    //   275: astore_2
    //   276: aload_2
    //   277: invokevirtual 324	java/io/IOException:printStackTrace	()V
    //   280: aload_0
    //   281: ifnull +7 -> 288
    //   284: aload_0
    //   285: invokevirtual 327	java/lang/Process:destroy	()V
    //   288: aconst_null
    //   289: areturn
    //   290: astore_0
    //   291: aload_3
    //   292: astore_2
    //   293: aload 4
    //   295: astore_3
    //   296: aload_3
    //   297: ifnull +15 -> 312
    //   300: aload_3
    //   301: invokevirtual 323	java/io/BufferedReader:close	()V
    //   304: goto +8 -> 312
    //   307: astore_3
    //   308: aload_3
    //   309: invokevirtual 324	java/io/IOException:printStackTrace	()V
    //   312: aload_2
    //   313: ifnull +7 -> 320
    //   316: aload_2
    //   317: invokevirtual 327	java/lang/Process:destroy	()V
    //   320: aload_0
    //   321: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	322	0	paramContext	Context
    //   3	82	1	i	int
    //   47	132	2	localObject1	Object
    //   185	17	2	localIOException1	java.io.IOException
    //   208	2	2	localIOException2	java.io.IOException
    //   228	41	2	localContext1	Context
    //   275	2	2	localIOException3	java.io.IOException
    //   292	25	2	localObject2	Object
    //   62	239	3	localObject3	Object
    //   307	2	3	localIOException4	java.io.IOException
    //   120	47	4	localObject4	Object
    //   225	5	4	localObject5	Object
    //   257	37	4	localContext2	Context
    //   138	61	5	localObject6	Object
    //   220	1	5	localThrowable1	Throwable
    //   235	1	5	localThrowable2	Throwable
    //   248	12	5	localThrowable3	Throwable
    // Exception table:
    //   from	to	target	type
    //   178	182	185	java/io/IOException
    //   201	205	208	java/io/IOException
    //   122	129	220	java/lang/Throwable
    //   134	140	220	java/lang/Throwable
    //   150	163	220	java/lang/Throwable
    //   168	178	220	java/lang/Throwable
    //   98	117	225	finally
    //   98	117	235	java/lang/Throwable
    //   63	98	242	finally
    //   63	98	248	java/lang/Throwable
    //   268	272	275	java/io/IOException
    //   122	129	290	finally
    //   134	140	290	finally
    //   150	163	290	finally
    //   168	178	290	finally
    //   259	264	290	finally
    //   300	304	307	java/io/IOException
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
    paramContext = paramContext.getPackageManager();
    paramString = new Intent(paramString);
    paramString.addCategory("android.intent.category.LAUNCHER");
    try
    {
      paramContext = paramContext.queryIntentActivities(paramString, 0);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    catch (OutOfMemoryError paramContext)
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
    catch (Exception paramContext)
    {
      for (;;) {}
    }
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
    catch (Exception paramContext)
    {
      for (;;) {}
    }
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
      return null;
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
    if (paramString == null) {
      return false;
    }
    Object localObject = Uri.parse(paramString);
    if (localObject != null)
    {
      localObject = new Intent("android.intent.action.VIEW", (Uri)localObject);
      ((Intent)localObject).setFlags(268435456);
    }
    try
    {
      paramContext.startActivity((Intent)localObject);
      return true;
    }
    catch (ActivityNotFoundException paramContext)
    {
      for (;;) {}
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    paramContext = new StringBuilder();
    paramContext.append("gotoBrowser error, uri = ");
    paramContext.append(paramString);
    LogUtils.i("matt", paramContext.toString());
    return false;
    paramContext = new StringBuilder();
    paramContext.append("gotoBrowser error, uri = ");
    paramContext.append(paramString);
    LogUtils.i("matt", paramContext.toString());
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
    if (paramContext != null) {
      if (TextUtils.isEmpty(paramString)) {
        return false;
      }
    }
    try
    {
      paramContext.getPackageManager().getPackageInfo(paramString, 1024);
      return true;
    }
    catch (PackageManager.NameNotFoundException|Exception paramContext) {}
    return false;
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
    if (paramString == null) {
      return false;
    }
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.addCategory("android.intent.category.BROWSABLE");
    localIntent.setData(Uri.parse("http://www.baidu.com"));
    localIntent.setPackage(paramString);
    paramContext = paramContext.getPackageManager().queryIntentActivities(localIntent, 0);
    return (paramContext != null) && (paramContext.size() > 0);
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
      return false;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean isInstallOnSDCard(PackageManager paramPackageManager, String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      if (paramPackageManager == null) {
        return false;
      }
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
      return false;
    }
    return false;
  }
  
  public static boolean isInternalApp(Context paramContext, Intent paramIntent)
  {
    if (paramContext != null)
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      try
      {
        paramContext = Environment.getDataDirectory().getAbsolutePath();
        paramIntent = localPackageManager.getActivityInfo(paramIntent.getComponent(), 0).applicationInfo.publicSourceDir;
        if ((paramIntent != null) && (paramIntent.length() > 0))
        {
          boolean bool = paramIntent.startsWith(paramContext);
          return bool;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return false;
  }
  
  public static boolean isServiceRunning(ActivityManager paramActivityManager, String paramString1, String paramString2)
  {
    List localList = paramActivityManager.getRunningServices(Integer.MAX_VALUE);
    int i;
    if (localList == null) {
      i = 0;
    } else {
      i = localList.size();
    }
    int j = 0;
    while (j < i)
    {
      paramActivityManager = (ActivityManager.RunningServiceInfo)localList.get(j);
      if ((paramActivityManager != null) && (paramActivityManager.service != null))
      {
        String str1 = paramActivityManager.service.getPackageName();
        String str2 = paramActivityManager.service.getClassName();
        if ((str1 != null) && (str1.contains(paramString1)) && (str2 != null) && (str2.contains(paramString2)))
        {
          paramString1 = new StringBuilder();
          paramString1.append("package = ");
          paramString1.append(paramActivityManager.service.getPackageName());
          paramString1.append(" class = ");
          paramString1.append(paramActivityManager.service.getClassName());
          LogUtils.i("Notification", paramString1.toString());
          return true;
        }
      }
      j += 1;
    }
    return false;
  }
  
  public static boolean isServiceRunning(Context paramContext, String paramString1, String paramString2)
  {
    return isServiceRunning((ActivityManager)paramContext.getSystemService("activity"), paramString1, paramString2);
  }
  
  public static boolean isSystemApp(Context paramContext, Intent paramIntent)
  {
    if (paramContext != null)
    {
      paramContext = paramContext.getPackageManager();
      try
      {
        paramContext = paramContext.getActivityInfo(paramIntent.getComponent(), 0).applicationInfo;
        if (paramContext != null) {
          if ((paramContext.flags & 0x1) == 0)
          {
            int i = paramContext.flags;
            if ((i & 0x80) == 0) {}
          }
          else
          {
            return true;
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return false;
  }
  
  public static boolean isSystemApp(Context paramContext, String paramString)
  {
    paramContext = getApplicationInfo(paramContext, paramString);
    boolean bool = false;
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
      return false;
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
    if (paramContext != null) {
      try
      {
        paramContext.startActivity(paramIntent);
        return;
      }
      catch (Exception paramContext)
      {
        paramIntent = new StringBuilder();
        paramIntent.append("saveStartActivity err ");
        paramIntent.append(paramContext.getMessage());
        LogUtils.e("matt", paramIntent.toString());
        return;
      }
      catch (SecurityException paramContext)
      {
        paramIntent = new StringBuilder();
        paramIntent.append("saveStartActivity err ");
        paramIntent.append(paramContext.getMessage());
        LogUtils.e("matt", paramIntent.toString());
        return;
      }
      catch (ActivityNotFoundException paramContext)
      {
        paramIntent = new StringBuilder();
        paramIntent.append("saveStartActivity err ");
        paramIntent.append(paramContext.getMessage());
        LogUtils.e("matt", paramIntent.toString());
      }
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
    catch (ActivityNotFoundException|Exception paramContext)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static void safeStartActivityForResult(Activity paramActivity, Intent paramIntent, int paramInt)
  {
    if (paramActivity != null) {
      try
      {
        paramActivity.startActivityForResult(paramIntent, paramInt);
        return;
      }
      catch (SecurityException paramActivity)
      {
        paramIntent = new StringBuilder();
        paramIntent.append("saveStartActivityForResult err ");
        paramIntent.append(paramActivity.getMessage());
        LogUtils.e("matt", paramIntent.toString());
        return;
      }
      catch (ActivityNotFoundException paramActivity)
      {
        paramIntent = new StringBuilder();
        paramIntent.append("saveStartActivityForResult err ");
        paramIntent.append(paramActivity.getMessage());
        LogUtils.e("matt", paramIntent.toString());
      }
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
    else
    {
      String str;
      if (i == 8) {
        str = "pkg";
      } else {
        str = "com.android.settings.ApplicationPkgName";
      }
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
      localIntent.putExtra(str, paramString);
    }
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void uninstallApk(Context paramContext, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("package:");
    localStringBuilder.append(paramString);
    paramString = new Intent("android.intent.action.DELETE", Uri.parse(localStringBuilder.toString()));
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
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("package:");
    localStringBuilder.append(paramString);
    uninstallApp(paramActivity, Uri.parse(localStringBuilder.toString()));
  }
}
