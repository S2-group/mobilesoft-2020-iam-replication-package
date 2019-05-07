package com.jiubang.golauncher.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
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
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class AppUtils
{
  private static String a;
  private static AsyncTask<Context, Void, String> b = new AsyncTask()
  {
    protected String a(Context... paramAnonymousVarArgs)
    {
      String str = "UNABLE-TO-RETRIEVE";
      try
      {
        paramAnonymousVarArgs = AdvertisingIdClient.getAdvertisingIdInfo(paramAnonymousVarArgs[0]);
        if (paramAnonymousVarArgs != null) {
          str = paramAnonymousVarArgs.getId();
        }
        return str;
      }
      catch (Throwable paramAnonymousVarArgs)
      {
        for (;;)
        {
          paramAnonymousVarArgs.printStackTrace();
          paramAnonymousVarArgs = null;
        }
      }
    }
  };
  
  public AppUtils() {}
  
  private static boolean a(Context paramContext, String paramString)
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
  
  public static void cancelNotificaiton(Context paramContext, int paramInt)
  {
    if (paramContext != null) {}
    try
    {
      ((NotificationManager)paramContext.getSystemService("notification")).cancel(paramInt);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static float changeVersionNameToFloat(String paramString)
  {
    if ((paramString != null) && (!paramString.equals(""))) {
      try
      {
        String str = paramString.trim().toLowerCase();
        paramString = str;
        if (str.contains("_")) {
          paramString = str.substring(0, str.indexOf("_"));
        }
        str = paramString;
        if (paramString.contains("beta")) {
          str = paramString.replace("beta", "");
        }
        int i = str.indexOf(".", str.indexOf(".") + 1);
        if (i != -1) {
          return Float.parseFloat(str.substring(0, i) + str.substring(i + 1, str.length()));
        }
        float f = Float.parseFloat(str);
        return f;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
    return 0.0F;
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
      }
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
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static long getAvailableInternalMemorySize()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getAvailableBlocks() * l;
  }
  
  public static String getBaseDeviceInfo()
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
      String str8 = "\nTotalMemSize=" + getTotalInternalMemorySize() / 1024L / 1024L + "MB";
      String str9 = "\nFreeMemSize=" + getAvailableInternalMemorySize() / 1024L / 1024L + "MB";
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
  
  /* Error */
  public static String getCurProcessName(Context paramContext)
  {
    // Byte code:
    //   0: invokestatic 357	android/os/Process:myPid	()I
    //   3: istore_1
    //   4: aload_0
    //   5: ldc 25
    //   7: invokevirtual 31	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   10: checkcast 33	android/app/ActivityManager
    //   13: invokevirtual 361	android/app/ActivityManager:getRunningAppProcesses	()Ljava/util/List;
    //   16: astore_0
    //   17: aload_0
    //   18: ifnull +44 -> 62
    //   21: aload_0
    //   22: invokeinterface 192 1 0
    //   27: astore_0
    //   28: aload_0
    //   29: invokeinterface 198 1 0
    //   34: ifeq +28 -> 62
    //   37: aload_0
    //   38: invokeinterface 202 1 0
    //   43: checkcast 363	android/app/ActivityManager$RunningAppProcessInfo
    //   46: astore_2
    //   47: aload_2
    //   48: getfield 366	android/app/ActivityManager$RunningAppProcessInfo:pid	I
    //   51: iload_1
    //   52: if_icmpne -24 -> 28
    //   55: aload_2
    //   56: getfield 369	android/app/ActivityManager$RunningAppProcessInfo:processName	Ljava/lang/String;
    //   59: astore_2
    //   60: aload_2
    //   61: areturn
    //   62: invokestatic 338	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   65: new 165	java/lang/StringBuilder
    //   68: dup
    //   69: invokespecial 166	java/lang/StringBuilder:<init>	()V
    //   72: ldc_w 371
    //   75: invokevirtual 170	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: iload_1
    //   79: invokevirtual 374	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   82: invokevirtual 176	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   85: invokevirtual 378	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   88: astore_0
    //   89: new 380	java/io/BufferedReader
    //   92: dup
    //   93: new 382	java/io/InputStreamReader
    //   96: dup
    //   97: aload_0
    //   98: invokevirtual 388	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   101: invokespecial 391	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   104: invokespecial 394	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   107: astore_2
    //   108: aload_2
    //   109: astore 4
    //   111: aload_0
    //   112: astore_3
    //   113: aload_2
    //   114: invokevirtual 397	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   117: astore 5
    //   119: aload 5
    //   121: ifnull +146 -> 267
    //   124: aload_2
    //   125: astore 4
    //   127: aload_0
    //   128: astore_3
    //   129: ldc_w 399
    //   132: new 165	java/lang/StringBuilder
    //   135: dup
    //   136: invokespecial 166	java/lang/StringBuilder:<init>	()V
    //   139: ldc_w 401
    //   142: invokevirtual 170	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: aload 5
    //   147: invokevirtual 170	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: invokevirtual 176	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   153: invokestatic 407	com/jiubang/golauncher/utils/Logcat:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   156: aload_2
    //   157: astore 4
    //   159: aload_0
    //   160: astore_3
    //   161: aload_2
    //   162: invokevirtual 397	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   165: astore 5
    //   167: aload 5
    //   169: ifnull +98 -> 267
    //   172: aload_2
    //   173: astore 4
    //   175: aload_0
    //   176: astore_3
    //   177: ldc_w 399
    //   180: new 165	java/lang/StringBuilder
    //   183: dup
    //   184: invokespecial 166	java/lang/StringBuilder:<init>	()V
    //   187: ldc_w 401
    //   190: invokevirtual 170	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: aload 5
    //   195: invokevirtual 170	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   198: invokevirtual 176	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   201: invokestatic 407	com/jiubang/golauncher/utils/Logcat:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   204: aload_2
    //   205: astore 4
    //   207: aload_0
    //   208: astore_3
    //   209: aload 5
    //   211: ldc_w 409
    //   214: ldc_w 410
    //   217: invokevirtual 414	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   220: astore 5
    //   222: aload_2
    //   223: astore 4
    //   225: aload_0
    //   226: astore_3
    //   227: aload 5
    //   229: aload 5
    //   231: arraylength
    //   232: iconst_1
    //   233: isub
    //   234: aaload
    //   235: astore 5
    //   237: aload_2
    //   238: ifnull +7 -> 245
    //   241: aload_2
    //   242: invokevirtual 417	java/io/BufferedReader:close	()V
    //   245: aload 5
    //   247: astore_2
    //   248: aload_0
    //   249: ifnull -189 -> 60
    //   252: aload_0
    //   253: invokevirtual 420	java/lang/Process:destroy	()V
    //   256: aload 5
    //   258: areturn
    //   259: astore_2
    //   260: aload_2
    //   261: invokevirtual 421	java/io/IOException:printStackTrace	()V
    //   264: goto -19 -> 245
    //   267: aload_2
    //   268: ifnull +7 -> 275
    //   271: aload_2
    //   272: invokevirtual 417	java/io/BufferedReader:close	()V
    //   275: aload_0
    //   276: ifnull +7 -> 283
    //   279: aload_0
    //   280: invokevirtual 420	java/lang/Process:destroy	()V
    //   283: aconst_null
    //   284: areturn
    //   285: astore_2
    //   286: aload_2
    //   287: invokevirtual 421	java/io/IOException:printStackTrace	()V
    //   290: goto -15 -> 275
    //   293: astore 5
    //   295: aconst_null
    //   296: astore_2
    //   297: aconst_null
    //   298: astore_0
    //   299: aload_2
    //   300: astore 4
    //   302: aload_0
    //   303: astore_3
    //   304: aload 5
    //   306: invokevirtual 422	java/lang/Throwable:printStackTrace	()V
    //   309: aload_2
    //   310: ifnull +7 -> 317
    //   313: aload_2
    //   314: invokevirtual 417	java/io/BufferedReader:close	()V
    //   317: aload_0
    //   318: ifnull -35 -> 283
    //   321: aload_0
    //   322: invokevirtual 420	java/lang/Process:destroy	()V
    //   325: goto -42 -> 283
    //   328: astore_2
    //   329: aload_2
    //   330: invokevirtual 421	java/io/IOException:printStackTrace	()V
    //   333: goto -16 -> 317
    //   336: astore_2
    //   337: aconst_null
    //   338: astore 4
    //   340: aconst_null
    //   341: astore_0
    //   342: aload 4
    //   344: ifnull +8 -> 352
    //   347: aload 4
    //   349: invokevirtual 417	java/io/BufferedReader:close	()V
    //   352: aload_0
    //   353: ifnull +7 -> 360
    //   356: aload_0
    //   357: invokevirtual 420	java/lang/Process:destroy	()V
    //   360: aload_2
    //   361: athrow
    //   362: astore_3
    //   363: aload_3
    //   364: invokevirtual 421	java/io/IOException:printStackTrace	()V
    //   367: goto -15 -> 352
    //   370: astore_2
    //   371: aconst_null
    //   372: astore 4
    //   374: goto -32 -> 342
    //   377: astore_2
    //   378: aload_3
    //   379: astore_0
    //   380: goto -38 -> 342
    //   383: astore 5
    //   385: aconst_null
    //   386: astore_2
    //   387: goto -88 -> 299
    //   390: astore 5
    //   392: goto -93 -> 299
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	395	0	paramContext	Context
    //   3	76	1	i	int
    //   46	202	2	localObject1	Object
    //   259	13	2	localIOException1	java.io.IOException
    //   285	2	2	localIOException2	java.io.IOException
    //   296	18	2	localObject2	Object
    //   328	2	2	localIOException3	java.io.IOException
    //   336	25	2	localObject3	Object
    //   370	1	2	localObject4	Object
    //   377	1	2	localObject5	Object
    //   386	1	2	localObject6	Object
    //   112	192	3	localContext	Context
    //   362	17	3	localIOException4	java.io.IOException
    //   109	264	4	localObject7	Object
    //   117	140	5	localObject8	Object
    //   293	12	5	localThrowable1	Throwable
    //   383	1	5	localThrowable2	Throwable
    //   390	1	5	localThrowable3	Throwable
    // Exception table:
    //   from	to	target	type
    //   241	245	259	java/io/IOException
    //   271	275	285	java/io/IOException
    //   62	89	293	java/lang/Throwable
    //   313	317	328	java/io/IOException
    //   62	89	336	finally
    //   347	352	362	java/io/IOException
    //   89	108	370	finally
    //   113	119	377	finally
    //   129	156	377	finally
    //   161	167	377	finally
    //   177	204	377	finally
    //   209	222	377	finally
    //   227	237	377	finally
    //   304	309	377	finally
    //   89	108	383	java/lang/Throwable
    //   113	119	390	java/lang/Throwable
    //   129	156	390	java/lang/Throwable
    //   161	167	390	java/lang/Throwable
    //   177	204	390	java/lang/Throwable
    //   209	222	390	java/lang/Throwable
    //   227	237	390	java/lang/Throwable
  }
  
  public static String getDefaultLauncher(Context paramContext)
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
  
  public static String getDefaultLauncherPackage(Context paramContext)
  {
    Object localObject1 = paramContext.getPackageManager();
    paramContext = new ArrayList();
    ((PackageManager)localObject1).getPreferredActivities(new ArrayList(), paramContext, null);
    Object localObject2 = new Intent("android.intent.action.MAIN");
    ((Intent)localObject2).addCategory("android.intent.category.HOME");
    ((Intent)localObject2).addCategory("android.intent.category.DEFAULT");
    localObject1 = ((PackageManager)localObject1).queryIntentActivities((Intent)localObject2, 0);
    int k = ((List)localObject1).size();
    int m = paramContext.size();
    int i = 0;
    if (i < k)
    {
      localObject2 = (ResolveInfo)((List)localObject1).get(i);
      if (localObject2 == null) {}
      do
      {
        i += 1;
        break;
        localObject2 = ((ResolveInfo)localObject2).activityInfo.packageName;
      } while (localObject2 == null);
      int j = 0;
      label134:
      ComponentName localComponentName;
      if (j < m)
      {
        localComponentName = (ComponentName)paramContext.get(j);
        if (localComponentName != null) {
          break label164;
        }
      }
      label164:
      while (!((String)localObject2).equals(localComponentName.getPackageName()))
      {
        j += 1;
        break label134;
        break;
      }
      return localObject2;
    }
    return null;
  }
  
  public static String getFileOption(String paramString)
  {
    Object localObject = "ls -l " + paramString;
    paramString = new StringBuffer();
    BufferedReader localBufferedReader;
    try
    {
      localObject = Runtime.getRuntime().exec((String)localObject).getInputStream();
      localBufferedReader = new BufferedReader(new InputStreamReader((InputStream)localObject));
      for (;;)
      {
        String str = localBufferedReader.readLine();
        if (str == null) {
          break;
        }
        paramString.append(str);
      }
      return paramString.toString();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    for (;;)
    {
      if (localException != null) {
        localException.close();
      }
      if (localBufferedReader != null) {
        localBufferedReader.close();
      }
    }
  }
  
  public static String getGoogleAdvertisingId()
  {
    if (a == null) {
      return "UNABLE-TO-RETRIEVE";
    }
    return a;
  }
  
  public static String getHttpRedirectUrl(String paramString)
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
  
  public static List<ApplicationInfo> getInstallAppInfo(Context paramContext)
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
  
  public static long getInstalledTime(Context paramContext, String paramString)
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
  
  public static List<ResolveInfo> getLauncherApps(Context paramContext)
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
  
  public static String getPackage(Intent paramIntent)
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
  
  public static long getPackageSize(Context paramContext, String paramString)
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
  
  public static int getPidByProcessName(Context paramContext, String paramString)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (paramContext != null)
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        if (localRunningAppProcessInfo.processName.equals(paramString)) {
          return localRunningAppProcessInfo.pid;
        }
      }
    }
    return 0;
  }
  
  public static List<Integer> getPidsByProcessNamePrefix(Context paramContext, String paramString)
  {
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = null;
    Object localObject = null;
    if (TextUtils.isEmpty(paramString)) {}
    do
    {
      return localObject;
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    } while (paramContext == null);
    Iterator localIterator = paramContext.iterator();
    paramContext = localRunningAppProcessInfo;
    for (;;)
    {
      localObject = paramContext;
      if (!localIterator.hasNext()) {
        break;
      }
      localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
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
  
  public static String getTopActivity(Context paramContext)
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
  
  public static long getTotalInternalMemorySize()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getBlockCount() * l;
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
  
  public static int getVersionCodeByPkgName(Context paramContext, String paramString)
  {
    int i = 1;
    if (paramString != null) {
      paramContext = paramContext.getPackageManager();
    }
    try
    {
      i = paramContext.getPackageInfo(paramString, 0).versionCode;
      return i;
    }
    catch (Exception paramContext)
    {
      Logcat.i("AppUtils", "getVersionCodeByPkgName=" + paramString + " has " + paramContext.getMessage());
    }
    return 1;
  }
  
  public static String getVersionNameByPkgName(Context paramContext, String paramString)
  {
    String str = "1.0";
    if (paramString != null) {
      paramContext = paramContext.getPackageManager();
    }
    try
    {
      str = paramContext.getPackageInfo(paramString, 0).versionName;
      return str;
    }
    catch (Exception paramContext) {}
    return "1.0";
  }
  
  public static boolean gotoBrowser(Context paramContext, String paramString)
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
  
  public static boolean gotoBrowserIfFailtoMarket(Context paramContext, String paramString1, String paramString2)
  {
    boolean bool2 = gotoMarket(paramContext, paramString1);
    boolean bool1 = bool2;
    if (!bool2) {
      bool1 = gotoBrowser(paramContext, paramString2);
    }
    return bool1;
  }
  
  public static boolean gotoIns(Context paramContext, String paramString)
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
  
  /* Error */
  public static boolean gotoMarket(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: astore_3
    //   2: aload_1
    //   3: invokestatic 526	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   6: ifne +77 -> 83
    //   9: aload_0
    //   10: ldc_w 671
    //   13: iconst_0
    //   14: invokevirtual 675	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   17: astore 4
    //   19: aload_1
    //   20: astore_3
    //   21: aload 4
    //   23: ldc_w 677
    //   26: iconst_0
    //   27: invokeinterface 683 3 0
    //   32: ifeq +51 -> 83
    //   35: aload 4
    //   37: ldc_w 685
    //   40: aconst_null
    //   41: invokeinterface 689 3 0
    //   46: astore 4
    //   48: aload 4
    //   50: invokestatic 526	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   53: istore_2
    //   54: aload_1
    //   55: astore_3
    //   56: iload_2
    //   57: ifne +26 -> 83
    //   60: aload_0
    //   61: invokestatic 692	com/jiubang/golauncher/utils/AppUtils:isMarketExist	(Landroid/content/Context;)Z
    //   64: ifne +89 -> 153
    //   67: aload_0
    //   68: aload 4
    //   70: invokestatic 663	com/jiubang/golauncher/utils/AppUtils:gotoBrowser	(Landroid/content/Context;Ljava/lang/String;)Z
    //   73: istore_2
    //   74: iload_2
    //   75: ireturn
    //   76: astore_3
    //   77: aload_3
    //   78: invokevirtual 128	java/lang/Exception:printStackTrace	()V
    //   81: aload_1
    //   82: astore_3
    //   83: new 425	android/content/Intent
    //   86: dup
    //   87: ldc_w 644
    //   90: aload_3
    //   91: invokestatic 642	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   94: invokespecial 647	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   97: astore_1
    //   98: aload_1
    //   99: ldc_w 694
    //   102: invokevirtual 564	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
    //   105: pop
    //   106: aload_0
    //   107: instanceof 668
    //   110: ifeq +18 -> 128
    //   113: aload_1
    //   114: ldc_w 669
    //   117: invokevirtual 652	android/content/Intent:setFlags	(I)Landroid/content/Intent;
    //   120: pop
    //   121: aload_0
    //   122: aload_1
    //   123: invokevirtual 656	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   126: iconst_1
    //   127: ireturn
    //   128: aload_1
    //   129: ldc_w 648
    //   132: invokevirtual 652	android/content/Intent:setFlags	(I)Landroid/content/Intent;
    //   135: pop
    //   136: goto -15 -> 121
    //   139: astore_0
    //   140: aload_0
    //   141: invokevirtual 128	java/lang/Exception:printStackTrace	()V
    //   144: iconst_0
    //   145: ireturn
    //   146: astore_3
    //   147: aload 4
    //   149: astore_1
    //   150: goto -73 -> 77
    //   153: aload 4
    //   155: astore_3
    //   156: goto -73 -> 83
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	159	0	paramContext	Context
    //   0	159	1	paramString	String
    //   53	22	2	bool	boolean
    //   1	55	3	str1	String
    //   76	2	3	localException1	Exception
    //   82	9	3	str2	String
    //   146	1	3	localException2	Exception
    //   155	1	3	localObject1	Object
    //   17	137	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   9	19	76	java/lang/Exception
    //   21	54	76	java/lang/Exception
    //   121	126	139	java/lang/Exception
    //   60	74	146	java/lang/Exception
  }
  
  public static boolean gotoMarketMyApp(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    Intent localIntent;
    if (getVersionCodeByPkgName(paramContext, "com.android.vending") >= 8006027)
    {
      localIntent = new Intent("com.google.android.finsky.VIEW_MY_DOWNLOADS");
      localIntent.setClassName("com.android.vending", "com.google.android.finsky.activities.MainActivity");
    }
    for (;;)
    {
      try
      {
        localIntent.setFlags(268435456);
        paramContext.startActivity(localIntent);
        return true;
      }
      catch (ActivityNotFoundException paramContext)
      {
        paramContext.printStackTrace();
        return false;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      localIntent = paramContext.getPackageManager().getLaunchIntentForPackage("com.android.vending");
    }
    return false;
  }
  
  public static void gotoWebView(Context paramContext, String paramString, Class<?> paramClass)
  {
    if (paramClass != null) {}
    for (paramClass = new Intent(paramContext, paramClass);; paramClass = new Intent("android.intent.action.VIEW"))
    {
      paramClass.putExtra("url", paramString);
      paramClass.setFlags(268435456);
      paramContext.startActivity(paramClass);
      return;
    }
  }
  
  public static String initGoogleAdvertisingId(Context paramContext)
  {
    if (b.getStatus() == AsyncTask.Status.RUNNING) {
      return "UNABLE-TO-RETRIEVE";
    }
    if (a == null) {}
    try
    {
      a = (String)b.execute(new Context[] { paramContext }).get(8000L, TimeUnit.MILLISECONDS);
      if (a == null) {
        a = "UNABLE-TO-RETRIEVE";
      }
      return a;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public static boolean isAppEnable(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null)) {}
    for (;;)
    {
      return false;
      try
      {
        paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 1024);
        if ((paramContext != null) && (paramContext.applicationInfo != null))
        {
          boolean bool = paramContext.applicationInfo.enabled;
          return bool;
        }
      }
      catch (Exception paramContext)
      {
        return false;
      }
      catch (PackageManager.NameNotFoundException paramContext) {}
    }
    return false;
  }
  
  public static boolean isAppExist(Context paramContext, Intent paramIntent)
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
  
  public static boolean isAppExist(Context paramContext, String paramString)
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
  
  public static boolean isAppRunning(Context paramContext, String paramString)
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
  
  public static boolean isAppRunningInForeground(Context paramContext, String paramString)
  {
    if (Machine.IS_SDK_ABOVE_LOLIP) {
      return isForegroundApp(paramContext, paramString);
    }
    return a(paramContext, paramString);
  }
  
  public static boolean isApplicationExsit(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      bool1 = bool2;
      if (paramString == null) {}
    }
    try
    {
      paramContext.getPackageManager().getPackageInfo(paramString, 1024);
      bool1 = true;
      return bool1;
    }
    catch (Exception paramContext) {}
    return false;
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
      localIntent.setData(Uri.parse("http://3g.cn"));
      localIntent.setPackage(paramString);
      paramContext = paramContext.getPackageManager().queryIntentActivities(localIntent, 0);
    } while ((paramContext == null) || (paramContext.size() <= 0));
    return true;
  }
  
  public static boolean isDefaultLauncher(Context paramContext)
  {
    return paramContext.getPackageName().equals(getDefaultLauncher(paramContext));
  }
  
  public static boolean isForegroundApp(Context paramContext, String paramString)
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
  
  public static boolean isForegroundTopApp(Context paramContext, String paramString)
  {
    try
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
      if (paramContext == null) {
        return false;
      }
      paramContext = (ActivityManager.RunningAppProcessInfo)paramContext.get(0);
      if (paramContext.importance == 100)
      {
        boolean bool = paramContext.processName.equals(paramString);
        if (bool) {
          return true;
        }
      }
    }
    catch (Exception paramContext)
    {
      return false;
    }
    return false;
  }
  
  public static boolean isInstallOnSDCard(Context paramContext, String paramString)
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
  
  public static boolean isLauncherApp(Context paramContext, String paramString)
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
          Logcat.d("zyz", "resolveInfo.activityInfo.packageName:" + ((ResolveInfo)localObject).activityInfo.packageName);
          return true;
        }
      }
    }
    return false;
  }
  
  public static boolean isMarketExist(Context paramContext)
  {
    return isAppExist(paramContext, "com.android.vending");
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
            Logcat.i("Notification", "package = " + localRunningServiceInfo.service.getPackageName() + " class = " + localRunningServiceInfo.service.getClassName());
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
  
  public static boolean isTopActivity(Context paramContext, String paramString1, String paramString2)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2))) {
      return false;
    }
    paramContext = ((ActivityManager.RunningTaskInfo)((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1).get(0)).topActivity;
    return (paramContext != null) && (paramString1.equals(paramContext.getPackageName())) && (paramString2.equals(paramContext.getClassName()));
  }
  
  public static void killProcess()
  {
    Logcat.i("Test", Log.getStackTraceString(new RuntimeException("kill process: " + android.os.Process.myPid())));
    killProcess(android.os.Process.myPid());
  }
  
  public static void killProcess(int paramInt)
  {
    Logcat.i("Test", Log.getStackTraceString(new RuntimeException("kill process: " + paramInt)));
    android.os.Process.killProcess(paramInt);
  }
  
  public static String marketUrl2BrowserUrl(String paramString)
  {
    if (paramString == null) {
      return "";
    }
    return paramString.replace("market://details?id=", "https://play.google.com/store/apps/details?id=");
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
  
  public static void triggerAlarm(AlarmManager paramAlarmManager, int paramInt, long paramLong, PendingIntent paramPendingIntent)
  {
    if (Machine.IS_SDK_ABOVE_KITKAT)
    {
      paramAlarmManager.setExact(paramInt, paramLong, paramPendingIntent);
      return;
    }
    paramAlarmManager.set(paramInt, paramLong, paramPendingIntent);
  }
  
  public static void uninstallApp(Context paramContext, Uri paramUri)
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
  
  public static void uninstallPackage(Context paramContext, String paramString)
  {
    uninstallApp(paramContext, Uri.parse("package:" + paramString));
  }
  
  public static void viewAppDetail(Context paramContext, String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString));
    try
    {
      paramContext.startActivity(paramString);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
}
