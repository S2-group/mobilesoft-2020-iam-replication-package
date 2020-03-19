package com.jiubang.golauncher.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.ads.a.b;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class a
{
  private static String a;
  
  public static String a(String paramString)
  {
    localDefaultHttpClient = new DefaultHttpClient();
    Object localObject = new BasicHttpContext();
    try
    {
      paramString = new HttpGet(paramString);
      localDefaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(30000));
      localDefaultHttpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(30000));
      localDefaultHttpClient.execute(paramString, (HttpContext)localObject);
      paramString = (HttpHost)((HttpContext)localObject).getAttribute("http.target_host");
      localObject = (HttpUriRequest)((HttpContext)localObject).getAttribute("http.request");
      paramString = paramString.toURI().toString();
      boolean bool = TextUtils.isEmpty(paramString);
      if (bool) {
        return null;
      }
      localObject = ((HttpUriRequest)localObject).getURI().toString();
      bool = TextUtils.isEmpty((CharSequence)localObject);
      if (bool) {
        return null;
      }
      paramString = paramString + (String)localObject;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
        localDefaultHttpClient.getConnectionManager().shutdown();
        paramString = null;
      }
    }
    catch (OutOfMemoryError paramString)
    {
      return null;
    }
    finally
    {
      localDefaultHttpClient.getConnectionManager().shutdown();
    }
    return paramString;
  }
  
  public static List<ResolveInfo> a(List<ResolveInfo> paramList, String[] paramArrayOfString)
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
  
  public static void a()
  {
    Log.i("Test", Log.getStackTraceString(new RuntimeException("kill process: " + Process.myPid())));
    a(Process.myPid());
  }
  
  public static void a(int paramInt)
  {
    Log.i("Test", Log.getStackTraceString(new RuntimeException("kill process: " + paramInt)));
    Process.killProcess(paramInt);
  }
  
  public static void a(Context paramContext, Uri paramUri)
  {
    try
    {
      paramUri = new Intent("android.intent.action.DELETE", paramUri);
      paramUri.addFlags(268435456);
      paramContext.startActivity(paramUri);
      return;
    }
    catch (Exception paramUri)
    {
      paramUri.printStackTrace();
      Toast.makeText(paramContext, "Uninstall failed", 0).show();
    }
  }
  
  public static boolean a(Context paramContext)
  {
    return a(paramContext, "com.android.vending");
  }
  
  public static boolean a(Context paramContext, Intent paramIntent)
  {
    boolean bool2 = false;
    try
    {
      paramContext = paramContext.getPackageManager().queryIntentActivities(paramIntent, 0);
      boolean bool1 = bool2;
      if (paramContext != null)
      {
        bool1 = bool2;
        if (paramContext.size() > 0) {
          bool1 = true;
        }
      }
      return bool1;
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
  
  public static boolean a(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null)) {
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
  
  public static boolean a(Context paramContext, String paramString1, String paramString2)
  {
    boolean bool2 = d(paramContext, paramString1);
    boolean bool1 = bool2;
    if (!bool2) {
      bool1 = e(paramContext, paramString2);
    }
    return bool1;
  }
  
  public static long b()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getAvailableBlocks() * l;
  }
  
  public static PackageInfo b(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static List<ResolveInfo> b(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    try
    {
      List localList1 = paramContext.queryIntentActivities(localIntent, 0);
      return localList1;
    }
    catch (Exception localException)
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = paramContext.getInstalledApplications(0).iterator();
      while (localIterator.hasNext())
      {
        localIntent.setPackage(((ApplicationInfo)localIterator.next()).packageName);
        List localList2 = paramContext.queryIntentActivities(localIntent, 0);
        if ((localList2 != null) && (!localList2.isEmpty())) {
          localArrayList.addAll(localList2);
        }
      }
      return localArrayList;
    }
  }
  
  public static int c(Context paramContext, String paramString)
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
  
  public static long c()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getBlockCount() * l;
  }
  
  public static String c(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 65536);
    if ((paramContext == null) || (paramContext.activityInfo == null)) {}
    while (paramContext.activityInfo.packageName.equals("android")) {
      return null;
    }
    return paramContext.activityInfo.packageName;
  }
  
  public static String d()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      String str1 = "Product=" + Build.PRODUCT;
      String str2 = "\nPhoneModel=" + Build.MODEL;
      String str3 = "\nKernel=" + Machine.getLinuxKernel();
      String str4 = "\nROM=" + Build.DISPLAY;
      String str5 = "\nBoard=" + Build.BOARD;
      String str6 = "\nDevice=" + Build.DEVICE;
      String str7 = "\nAndroidVersion=" + Build.VERSION.RELEASE;
      String str8 = "\nTotalMemSize=" + c() / 1024L / 1024L + "MB";
      String str9 = "\nFreeMemSize=" + b() / 1024L / 1024L + "MB";
      String str10 = "\nRom App Heap Size=" + Integer.toString((int)(Runtime.getRuntime().maxMemory() / 1024L / 1024L)) + "MB";
      localStringBuilder.append(str1).append(str2).append(str3).append(str4).append(str5).append(str6).append(str7).append(str8).append(str9).append(str10);
      return localStringBuilder.toString();
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  public static boolean d(Context paramContext)
  {
    return paramContext.getPackageName().equals(c(paramContext));
  }
  
  public static boolean d(Context paramContext, String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    paramString.setPackage("com.android.vending");
    if ((paramContext instanceof Activity)) {
      paramString.setFlags(1073741824);
    }
    for (;;)
    {
      try
      {
        paramContext.startActivity(paramString);
        return true;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      paramString.setFlags(268435456);
    }
    return false;
  }
  
  public static boolean e(Context paramContext)
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
                  break label222;
                }
                bool2 = bool4;
                bool3 = bool5;
                if (paramContext.providers == null) {
                  break label222;
                }
                bool2 = bool4;
                j = paramContext.providers.length;
                i = 0;
                bool3 = bool1;
                if (i >= j) {
                  break label222;
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
      label222:
      int i = 0;
    }
  }
  
  public static boolean e(Context paramContext, String paramString)
  {
    if (paramString == null) {}
    do
    {
      return false;
      paramString = Uri.parse(paramString);
    } while (paramString == null);
    paramString = new Intent("android.intent.action.VIEW", paramString);
    paramString.setFlags(268435456);
    try
    {
      paramContext.startActivity(paramString);
      return true;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  /* Error */
  public static String f(Context paramContext)
  {
    // Byte code:
    //   0: invokestatic 163	android/os/Process:myPid	()I
    //   3: istore_1
    //   4: aload_0
    //   5: ldc_w 446
    //   8: invokevirtual 449	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   11: checkcast 451	android/app/ActivityManager
    //   14: invokevirtual 455	android/app/ActivityManager:getRunningAppProcesses	()Ljava/util/List;
    //   17: astore_0
    //   18: aload_0
    //   19: ifnull +44 -> 63
    //   22: aload_0
    //   23: invokeinterface 122 1 0
    //   28: astore_0
    //   29: aload_0
    //   30: invokeinterface 128 1 0
    //   35: ifeq +28 -> 63
    //   38: aload_0
    //   39: invokeinterface 132 1 0
    //   44: checkcast 457	android/app/ActivityManager$RunningAppProcessInfo
    //   47: astore_2
    //   48: aload_2
    //   49: getfield 460	android/app/ActivityManager$RunningAppProcessInfo:pid	I
    //   52: iload_1
    //   53: if_icmpne -24 -> 29
    //   56: aload_2
    //   57: getfield 463	android/app/ActivityManager$RunningAppProcessInfo:processName	Ljava/lang/String;
    //   60: astore_2
    //   61: aload_2
    //   62: areturn
    //   63: invokestatic 381	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   66: new 96	java/lang/StringBuilder
    //   69: dup
    //   70: invokespecial 97	java/lang/StringBuilder:<init>	()V
    //   73: ldc_w 465
    //   76: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: iload_1
    //   80: invokevirtual 166	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   83: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   86: invokevirtual 469	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   89: astore_0
    //   90: new 471	java/io/BufferedReader
    //   93: dup
    //   94: new 473	java/io/InputStreamReader
    //   97: dup
    //   98: aload_0
    //   99: invokevirtual 479	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   102: invokespecial 482	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   105: invokespecial 485	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   108: astore_2
    //   109: aload_2
    //   110: astore 4
    //   112: aload_0
    //   113: astore_3
    //   114: aload_2
    //   115: invokevirtual 488	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   118: astore 5
    //   120: aload 5
    //   122: ifnull +146 -> 268
    //   125: aload_2
    //   126: astore 4
    //   128: aload_0
    //   129: astore_3
    //   130: ldc -102
    //   132: new 96	java/lang/StringBuilder
    //   135: dup
    //   136: invokespecial 97	java/lang/StringBuilder:<init>	()V
    //   139: ldc_w 490
    //   142: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: aload 5
    //   147: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   153: invokestatic 177	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   156: pop
    //   157: aload_2
    //   158: astore 4
    //   160: aload_0
    //   161: astore_3
    //   162: aload_2
    //   163: invokevirtual 488	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   166: astore 5
    //   168: aload 5
    //   170: ifnull +98 -> 268
    //   173: aload_2
    //   174: astore 4
    //   176: aload_0
    //   177: astore_3
    //   178: ldc -102
    //   180: new 96	java/lang/StringBuilder
    //   183: dup
    //   184: invokespecial 97	java/lang/StringBuilder:<init>	()V
    //   187: ldc_w 490
    //   190: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: aload 5
    //   195: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   198: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   201: invokestatic 177	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   204: pop
    //   205: aload_2
    //   206: astore 4
    //   208: aload_0
    //   209: astore_3
    //   210: aload 5
    //   212: ldc_w 492
    //   215: ldc_w 493
    //   218: invokevirtual 497	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   221: astore 5
    //   223: aload_2
    //   224: astore 4
    //   226: aload_0
    //   227: astore_3
    //   228: aload 5
    //   230: aload 5
    //   232: arraylength
    //   233: iconst_1
    //   234: isub
    //   235: aaload
    //   236: astore 5
    //   238: aload_2
    //   239: ifnull +7 -> 246
    //   242: aload_2
    //   243: invokevirtual 500	java/io/BufferedReader:close	()V
    //   246: aload 5
    //   248: astore_2
    //   249: aload_0
    //   250: ifnull -189 -> 61
    //   253: aload_0
    //   254: invokevirtual 503	java/lang/Process:destroy	()V
    //   257: aload 5
    //   259: areturn
    //   260: astore_2
    //   261: aload_2
    //   262: invokevirtual 504	java/io/IOException:printStackTrace	()V
    //   265: goto -19 -> 246
    //   268: aload_2
    //   269: ifnull +7 -> 276
    //   272: aload_2
    //   273: invokevirtual 500	java/io/BufferedReader:close	()V
    //   276: aload_0
    //   277: ifnull +7 -> 284
    //   280: aload_0
    //   281: invokevirtual 503	java/lang/Process:destroy	()V
    //   284: aconst_null
    //   285: areturn
    //   286: astore_2
    //   287: aload_2
    //   288: invokevirtual 504	java/io/IOException:printStackTrace	()V
    //   291: goto -15 -> 276
    //   294: astore 5
    //   296: aconst_null
    //   297: astore_2
    //   298: aconst_null
    //   299: astore_0
    //   300: aload_2
    //   301: astore 4
    //   303: aload_0
    //   304: astore_3
    //   305: aload 5
    //   307: invokevirtual 505	java/lang/Throwable:printStackTrace	()V
    //   310: aload_2
    //   311: ifnull +7 -> 318
    //   314: aload_2
    //   315: invokevirtual 500	java/io/BufferedReader:close	()V
    //   318: aload_0
    //   319: ifnull -35 -> 284
    //   322: aload_0
    //   323: invokevirtual 503	java/lang/Process:destroy	()V
    //   326: goto -42 -> 284
    //   329: astore_2
    //   330: aload_2
    //   331: invokevirtual 504	java/io/IOException:printStackTrace	()V
    //   334: goto -16 -> 318
    //   337: astore_2
    //   338: aconst_null
    //   339: astore 4
    //   341: aconst_null
    //   342: astore_0
    //   343: aload 4
    //   345: ifnull +8 -> 353
    //   348: aload 4
    //   350: invokevirtual 500	java/io/BufferedReader:close	()V
    //   353: aload_0
    //   354: ifnull +7 -> 361
    //   357: aload_0
    //   358: invokevirtual 503	java/lang/Process:destroy	()V
    //   361: aload_2
    //   362: athrow
    //   363: astore_3
    //   364: aload_3
    //   365: invokevirtual 504	java/io/IOException:printStackTrace	()V
    //   368: goto -15 -> 353
    //   371: astore_2
    //   372: aconst_null
    //   373: astore 4
    //   375: goto -32 -> 343
    //   378: astore_2
    //   379: aload_3
    //   380: astore_0
    //   381: goto -38 -> 343
    //   384: astore 5
    //   386: aconst_null
    //   387: astore_2
    //   388: goto -88 -> 300
    //   391: astore 5
    //   393: goto -93 -> 300
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	396	0	paramContext	Context
    //   3	77	1	i	int
    //   47	202	2	localObject1	Object
    //   260	13	2	localIOException1	java.io.IOException
    //   286	2	2	localIOException2	java.io.IOException
    //   297	18	2	localObject2	Object
    //   329	2	2	localIOException3	java.io.IOException
    //   337	25	2	localObject3	Object
    //   371	1	2	localObject4	Object
    //   378	1	2	localObject5	Object
    //   387	1	2	localObject6	Object
    //   113	192	3	localContext	Context
    //   363	17	3	localIOException4	java.io.IOException
    //   110	264	4	localObject7	Object
    //   118	140	5	localObject8	Object
    //   294	12	5	localThrowable1	Throwable
    //   384	1	5	localThrowable2	Throwable
    //   391	1	5	localThrowable3	Throwable
    // Exception table:
    //   from	to	target	type
    //   242	246	260	java/io/IOException
    //   272	276	286	java/io/IOException
    //   63	90	294	java/lang/Throwable
    //   314	318	329	java/io/IOException
    //   63	90	337	finally
    //   348	353	363	java/io/IOException
    //   90	109	371	finally
    //   114	120	378	finally
    //   130	157	378	finally
    //   162	168	378	finally
    //   178	205	378	finally
    //   210	223	378	finally
    //   228	238	378	finally
    //   305	310	378	finally
    //   90	109	384	java/lang/Throwable
    //   114	120	391	java/lang/Throwable
    //   130	157	391	java/lang/Throwable
    //   162	168	391	java/lang/Throwable
    //   178	205	391	java/lang/Throwable
    //   210	223	391	java/lang/Throwable
    //   228	238	391	java/lang/Throwable
  }
  
  public static void f(Context paramContext, String paramString)
  {
    a(paramContext, Uri.parse("package:" + paramString));
  }
  
  public static List<ApplicationInfo> g(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getInstalledApplications(0);
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static List<Integer> g(Context paramContext, String paramString)
  {
    Object localObject = null;
    Iterator localIterator = null;
    if (TextUtils.isEmpty(paramString))
    {
      localObject = localIterator;
      return localObject;
    }
    localIterator = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    paramContext = (Context)localObject;
    for (;;)
    {
      localObject = paramContext;
      if (!localIterator.hasNext()) {
        break;
      }
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
      if (localRunningAppProcessInfo.processName.startsWith(paramString))
      {
        localObject = paramContext;
        if (paramContext == null) {
          localObject = new ArrayList();
        }
        ((List)localObject).add(Integer.valueOf(localRunningAppProcessInfo.pid));
        paramContext = (Context)localObject;
      }
    }
  }
  
  public static int h(Context paramContext, String paramString)
  {
    int i = 0;
    if (paramString != null) {
      paramContext = paramContext.getPackageManager();
    }
    try
    {
      i = paramContext.getPackageInfo(paramString, 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Log.i("AppUtils", "getVersionCodeByPkgName=" + paramString + " has " + paramContext.getMessage());
    }
    return 0;
  }
  
  public static String h(Context paramContext)
  {
    if (a != null) {
      return a;
    }
    Object localObject = null;
    try
    {
      paramContext = com.google.android.gms.ads.a.a.b(paramContext);
      if (paramContext != null)
      {
        a = paramContext.a();
        Log.i("Test", "googleId: " + a);
        return a;
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = localObject;
      }
      Log.i("Test", "googleId: UNABLE-TO-RETRIEVE");
    }
    return "UNABLE-TO-RETRIEVE";
  }
  
  public static String i(Context paramContext)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    if (Machine.IS_SDK_ABOVE_LOLIP)
    {
      paramContext = paramContext.getRunningAppProcesses();
      if ((paramContext != null) && (!paramContext.isEmpty()))
      {
        paramContext = paramContext.iterator();
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
        do
        {
          if (!paramContext.hasNext()) {
            break;
          }
          localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        } while ((localRunningAppProcessInfo.importance != 100) || (localRunningAppProcessInfo.pkgList == null) || (localRunningAppProcessInfo.pkgList.length <= 0));
        return localRunningAppProcessInfo.pkgList[0];
      }
    }
    else
    {
      paramContext = ((ActivityManager.RunningTaskInfo)paramContext.getRunningTasks(1).get(0)).topActivity;
      if (paramContext != null) {
        return paramContext.getPackageName();
      }
    }
    return null;
  }
  
  public static String i(Context paramContext, String paramString)
  {
    String str = "0.0";
    if (paramString != null) {
      paramContext = paramContext.getPackageManager();
    }
    try
    {
      str = paramContext.getPackageInfo(paramString, 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return "0.0";
  }
  
  public static Context j(Context paramContext, String paramString)
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
  
  public static Drawable k(Context paramContext, String paramString)
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
      }
    }
    return null;
  }
  
  public static String l(Context paramContext, String paramString)
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
  
  public static long m(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramString, 0);
      if ((paramContext != null) && (paramContext.sourceDir != null))
      {
        long l = new File(paramContext.sourceDir).length();
        return l;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0L;
  }
  
  public static boolean n(Context paramContext, String paramString)
  {
    boolean bool = false;
    try
    {
      int i = paramContext.getPackageManager().getApplicationInfo(paramString, 0).flags;
      if ((i & 0x40000) != 0) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static long o(Context paramContext, String paramString)
  {
    try
    {
      long l = new File(paramContext.getPackageManager().getApplicationInfo(paramString, 0).sourceDir).lastModified();
      return l;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0L;
  }
  
  public static boolean p(Context paramContext, String paramString)
  {
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
          if (localObject[i].equals(paramString)) {
            return true;
          }
          i += 1;
        }
      }
    }
    return false;
  }
  
  public static boolean q(Context paramContext, String paramString)
  {
    if (Machine.IS_SDK_ABOVE_LOLIP) {
      return u(paramContext, paramString);
    }
    return t(paramContext, paramString);
  }
  
  public static boolean r(Context paramContext, String paramString)
  {
    if (paramString != null)
    {
      paramContext = paramContext.getPackageManager();
      Object localObject = new Intent("android.intent.action.MAIN");
      ((Intent)localObject).addCategory("android.intent.category.HOME");
      paramContext = paramContext.queryIntentActivities((Intent)localObject, 0).iterator();
      while (paramContext.hasNext())
      {
        localObject = (ResolveInfo)paramContext.next();
        if (((ResolveInfo)localObject).activityInfo.packageName.equals(paramString))
        {
          Log.d("zyz", "resolveInfo.activityInfo.packageName:" + ((ResolveInfo)localObject).activityInfo.packageName);
          return true;
        }
      }
    }
    return false;
  }
  
  public static boolean s(Context paramContext, String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    paramString.setPackage("com.instagram.android");
    if ((paramContext instanceof Activity)) {
      paramString.setFlags(1073741824);
    }
    for (;;)
    {
      try
      {
        paramContext.startActivity(paramString);
        return true;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      paramString.setFlags(268435456);
    }
    return false;
  }
  
  private static boolean t(Context paramContext, String paramString)
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
  
  private static boolean u(Context paramContext, String paramString)
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
}
