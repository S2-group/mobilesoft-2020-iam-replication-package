package ms.loop.lib.listeners;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v4.b.t;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ms.loop.lib.core.LoopLibrary;
import ms.loop.lib.core.LoopServiceManager;
import ms.loop.lib.profile.Application;
import ms.loop.lib.signal.Signal;
import ms.loop.lib.utils.Logger;
import ms.loop.lib.utils.State;
import org.json.JSONException;
import org.json.JSONObject;

public class ApplicationListener
  extends t
  implements ILoopListener
{
  private static final String FOREGROUND_ID = "foreground";
  private static final String INIT_POLL_ID = "init";
  private static final String INSTALL_ID = "install";
  public static final String SHARED_PREF_INIT_APP_POLLED = "POLL_APP_INSTALL";
  public static final String SIGNAL_APPLICATION_FOREGROUND = "/device/application/foreground";
  public static final String SIGNAL_APPLICATION_INIT_APP_POLLED = "/device/application/init";
  public static final String SIGNAL_APPLICATION_INSTALL = "/device/application/install";
  private static final String SIGNAL_APPLICATION_ROOT = "/device/application";
  public static final String SIGNAL_APPLICATION_SYNC = "/device/application/sync";
  public static final String SIGNAL_APPLICATION_UNINSTALL = "/device/application/uninstall";
  private static final String SYNC_ID = "sync";
  private static final String TAG = ApplicationListener.class.getSimpleName();
  private static final String UNINSTALL_ID = "uninstall";
  private boolean listenerStarted;
  
  public ApplicationListener() {}
  
  private static Long firstNonNull(Long... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      Long localLong = paramVarArgs[i];
      if (localLong != null) {
        return localLong;
      }
      i += 1;
    }
    return null;
  }
  
  private String getAction(String paramString)
  {
    int i = -1;
    switch (paramString.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        Logger.log(TAG, 40, "illegal intent: %s", new Object[] { paramString });
        return "";
        if (paramString.equals("android.intent.action.PACKAGE_ADDED"))
        {
          i = 0;
          continue;
          if (paramString.equals("android.intent.action.PACKAGE_REMOVED")) {
            i = 1;
          }
        }
        break;
      }
    }
    return "install";
    return "uninstall";
  }
  
  private static List<ResolveInfo> getAllInstalledAppResolveInfos()
  {
    Context localContext = LoopLibrary.applicationContext;
    try
    {
      Object localObject1 = new Intent("android.intent.action.MAIN", null);
      ((Intent)localObject1).addCategory("android.intent.category.LAUNCHER");
      localObject1 = localContext.getPackageManager().queryIntentActivities((Intent)localObject1, 0);
      return localObject1;
    }
    catch (Exception localException2)
    {
      Logger.log(TAG, 40, localException2.toString());
      try
      {
        Object localObject2 = getInstalledPackages(0);
        ArrayList localArrayList = new ArrayList();
        localObject2 = ((List)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          Object localObject3 = (PackageInfo)((Iterator)localObject2).next();
          Intent localIntent = new Intent("android.intent.action.MAIN", null);
          localIntent.addCategory("android.intent.category.LAUNCHER");
          localIntent.setPackage(((PackageInfo)localObject3).packageName);
          localObject3 = (ResolveInfo)localContext.getPackageManager().queryIntentActivities(localIntent, 0).iterator().next();
          if (localObject3 != null) {
            localArrayList.add(localObject3);
          }
        }
        return localArrayList;
      }
      catch (Exception localException1)
      {
        Logger.log(TAG, 40, localException1.toString());
        return null;
      }
    }
  }
  
  private static void getAppInstallAndUpdateTimes(List<Application> paramList, boolean paramBoolean)
  {
    PackageManager localPackageManager = LoopLibrary.applicationContext.getPackageManager();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Object localObject = (Application)paramList.next();
      String str1 = ((Application)localObject).packageName;
      String str2 = getApplicationName(str1);
      Long localLong1 = getUpdateTime(localPackageManager, str1);
      Long localLong2 = getInstallTime(localPackageManager, str1, localLong1);
      Logger.log(TAG, 10, "app %s %s %d %d", new Object[] { str1, str2, localLong1, localLong2 });
      ((Application)localObject).applicationName = str2;
      ((Application)localObject).applicationUpdatedAt = localLong1;
      ((Application)localObject).applicationInstalledAt = localLong2;
      if (paramBoolean)
      {
        localObject = LoopServiceManager.createNewSignal();
        ((Signal)localObject).initialize("/device/application", "install", "listener");
        ((Signal)localObject).put("packageName", str1);
        ((Signal)localObject).put("applicationName", str2);
        ((Signal)localObject).put("applicationUpdatedAt", localLong1);
        ((Signal)localObject).put("applicationInstalledAt", localLong2);
        ((Signal)localObject).put("isInitialPoll", Boolean.valueOf(true));
        LoopServiceManager.processSignal((Signal)localObject);
      }
    }
  }
  
  public static String getApplicationName(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    Object localObject = LoopLibrary.applicationContext.getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getApplicationInfo(paramString, 0).loadLabel((PackageManager)localObject).toString();
      return localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Logger.log(TAG, 40, "package name not found: %s", new Object[] { paramString });
    }
    return "";
  }
  
  private static List<ResolveInfo> getInitialInstalledApplications()
  {
    Object localObject2 = LoopLibrary.applicationContext;
    Object localObject1 = new Intent("android.intent.action.MAIN", null);
    localObject2 = ((Context)localObject2).getPackageManager();
    try
    {
      localObject1 = ((PackageManager)localObject2).queryIntentActivities((Intent)localObject1, 0);
      Logger.log(TAG, 10, "app init - get applications done");
      return localObject1;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Logger.log(TAG, 40, localException.toString());
        List localList = getAllInstalledAppResolveInfos();
      }
    }
  }
  
  public static Long getInstallTime(PackageManager paramPackageManager, String paramString, Long paramLong)
  {
    return firstNonNull(new Long[] { installTimeFromPackageInfo(paramPackageManager, paramString), paramLong, Long.valueOf(System.currentTimeMillis()) });
  }
  
  /* Error */
  private static List<PackageInfo> getInstalledPackages(int paramInt)
  {
    // Byte code:
    //   0: getstatic 115	ms/loop/lib/core/LoopLibrary:applicationContext	Landroid/content/Context;
    //   3: invokevirtual 134	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   6: astore 5
    //   8: aload 5
    //   10: iload_0
    //   11: invokevirtual 293	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   14: astore_3
    //   15: aload_3
    //   16: areturn
    //   17: astore_1
    //   18: getstatic 56	ms/loop/lib/listeners/ApplicationListener:TAG	Ljava/lang/String;
    //   21: bipush 40
    //   23: aload_1
    //   24: invokevirtual 143	java/lang/Exception:toString	()Ljava/lang/String;
    //   27: invokestatic 146	ms/loop/lib/utils/Logger:log	(Ljava/lang/String;ILjava/lang/String;)V
    //   30: new 152	java/util/ArrayList
    //   33: dup
    //   34: invokespecial 153	java/util/ArrayList:<init>	()V
    //   37: astore 4
    //   39: aconst_null
    //   40: astore_1
    //   41: aconst_null
    //   42: astore_2
    //   43: aload_1
    //   44: astore_3
    //   45: invokestatic 299	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   48: ldc_w 301
    //   51: invokevirtual 305	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   54: astore 6
    //   56: aload_1
    //   57: astore_3
    //   58: new 307	java/io/BufferedReader
    //   61: dup
    //   62: new 309	java/io/InputStreamReader
    //   65: dup
    //   66: aload 6
    //   68: invokevirtual 315	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   71: invokespecial 318	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   74: invokespecial 321	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   77: astore_1
    //   78: aload_1
    //   79: invokevirtual 324	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   82: astore_2
    //   83: aload_2
    //   84: ifnull +81 -> 165
    //   87: aload 4
    //   89: aload 5
    //   91: aload_2
    //   92: aload_2
    //   93: bipush 58
    //   95: invokevirtual 328	java/lang/String:indexOf	(I)I
    //   98: iconst_1
    //   99: iadd
    //   100: invokevirtual 332	java/lang/String:substring	(I)Ljava/lang/String;
    //   103: iload_0
    //   104: invokevirtual 336	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   107: invokeinterface 182 2 0
    //   112: pop
    //   113: goto -35 -> 78
    //   116: astore_3
    //   117: aload_1
    //   118: astore_2
    //   119: aload_3
    //   120: astore_1
    //   121: aload_2
    //   122: astore_3
    //   123: getstatic 56	ms/loop/lib/listeners/ApplicationListener:TAG	Ljava/lang/String;
    //   126: bipush 40
    //   128: aload_1
    //   129: invokevirtual 143	java/lang/Exception:toString	()Ljava/lang/String;
    //   132: invokestatic 146	ms/loop/lib/utils/Logger:log	(Ljava/lang/String;ILjava/lang/String;)V
    //   135: aload 4
    //   137: astore_3
    //   138: aload_2
    //   139: ifnull -124 -> 15
    //   142: aload_2
    //   143: invokevirtual 339	java/io/BufferedReader:close	()V
    //   146: aload 4
    //   148: areturn
    //   149: astore_1
    //   150: getstatic 56	ms/loop/lib/listeners/ApplicationListener:TAG	Ljava/lang/String;
    //   153: bipush 40
    //   155: aload_1
    //   156: invokevirtual 340	java/io/IOException:toString	()Ljava/lang/String;
    //   159: invokestatic 146	ms/loop/lib/utils/Logger:log	(Ljava/lang/String;ILjava/lang/String;)V
    //   162: aload 4
    //   164: areturn
    //   165: aload 6
    //   167: invokevirtual 343	java/lang/Process:waitFor	()I
    //   170: pop
    //   171: aload 4
    //   173: astore_3
    //   174: aload_1
    //   175: ifnull -160 -> 15
    //   178: aload_1
    //   179: invokevirtual 339	java/io/BufferedReader:close	()V
    //   182: aload 4
    //   184: areturn
    //   185: astore_1
    //   186: getstatic 56	ms/loop/lib/listeners/ApplicationListener:TAG	Ljava/lang/String;
    //   189: bipush 40
    //   191: aload_1
    //   192: invokevirtual 340	java/io/IOException:toString	()Ljava/lang/String;
    //   195: invokestatic 146	ms/loop/lib/utils/Logger:log	(Ljava/lang/String;ILjava/lang/String;)V
    //   198: aload 4
    //   200: areturn
    //   201: astore_1
    //   202: aload_3
    //   203: ifnull +7 -> 210
    //   206: aload_3
    //   207: invokevirtual 339	java/io/BufferedReader:close	()V
    //   210: aload_1
    //   211: athrow
    //   212: astore_2
    //   213: getstatic 56	ms/loop/lib/listeners/ApplicationListener:TAG	Ljava/lang/String;
    //   216: bipush 40
    //   218: aload_2
    //   219: invokevirtual 340	java/io/IOException:toString	()Ljava/lang/String;
    //   222: invokestatic 146	ms/loop/lib/utils/Logger:log	(Ljava/lang/String;ILjava/lang/String;)V
    //   225: goto -15 -> 210
    //   228: astore_2
    //   229: aload_1
    //   230: astore_3
    //   231: aload_2
    //   232: astore_1
    //   233: goto -31 -> 202
    //   236: astore_1
    //   237: goto -116 -> 121
    //   240: astore_3
    //   241: aload_1
    //   242: astore_2
    //   243: aload_3
    //   244: astore_1
    //   245: goto -124 -> 121
    //   248: astore_1
    //   249: goto -128 -> 121
    //   252: astore_3
    //   253: aload_1
    //   254: astore_2
    //   255: aload_3
    //   256: astore_1
    //   257: goto -136 -> 121
    //   260: astore_1
    //   261: aconst_null
    //   262: astore_2
    //   263: goto -142 -> 121
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	266	0	paramInt	int
    //   17	7	1	localException	Exception
    //   40	89	1	localObject1	Object
    //   149	30	1	localIOException1	java.io.IOException
    //   185	7	1	localIOException2	java.io.IOException
    //   201	29	1	localObject2	Object
    //   232	1	1	localObject3	Object
    //   236	6	1	localInterruptedException1	InterruptedException
    //   244	1	1	localInterruptedException2	InterruptedException
    //   248	6	1	localIOException3	java.io.IOException
    //   256	1	1	localIOException4	java.io.IOException
    //   260	1	1	localNameNotFoundException1	PackageManager.NameNotFoundException
    //   42	101	2	localObject4	Object
    //   212	7	2	localIOException5	java.io.IOException
    //   228	4	2	localObject5	Object
    //   242	21	2	localObject6	Object
    //   14	44	3	localObject7	Object
    //   116	4	3	localNameNotFoundException2	PackageManager.NameNotFoundException
    //   122	109	3	localObject8	Object
    //   240	4	3	localInterruptedException3	InterruptedException
    //   252	4	3	localIOException6	java.io.IOException
    //   37	162	4	localArrayList	ArrayList
    //   6	84	5	localPackageManager	PackageManager
    //   54	112	6	localProcess	Process
    // Exception table:
    //   from	to	target	type
    //   8	15	17	java/lang/Exception
    //   78	83	116	android/content/pm/PackageManager$NameNotFoundException
    //   87	113	116	android/content/pm/PackageManager$NameNotFoundException
    //   165	171	116	android/content/pm/PackageManager$NameNotFoundException
    //   142	146	149	java/io/IOException
    //   178	182	185	java/io/IOException
    //   45	56	201	finally
    //   58	78	201	finally
    //   123	135	201	finally
    //   206	210	212	java/io/IOException
    //   78	83	228	finally
    //   87	113	228	finally
    //   165	171	228	finally
    //   45	56	236	java/lang/InterruptedException
    //   58	78	236	java/lang/InterruptedException
    //   78	83	240	java/lang/InterruptedException
    //   87	113	240	java/lang/InterruptedException
    //   165	171	240	java/lang/InterruptedException
    //   45	56	248	java/io/IOException
    //   58	78	248	java/io/IOException
    //   78	83	252	java/io/IOException
    //   87	113	252	java/io/IOException
    //   165	171	252	java/io/IOException
    //   45	56	260	android/content/pm/PackageManager$NameNotFoundException
    //   58	78	260	android/content/pm/PackageManager$NameNotFoundException
  }
  
  public static Long getUpdateTime(PackageManager paramPackageManager, String paramString)
  {
    return firstNonNull(new Long[] { updateTimeFromPackageInfo(paramPackageManager, paramString), updateTimeFromApplicationInfo(paramPackageManager, paramString), Long.valueOf(System.currentTimeMillis()) });
  }
  
  public static boolean initialAppsPolled()
  {
    return State.getLong("POLL_APP_INSTALL") != 0L;
  }
  
  public static void initializeAppsDB()
  {
    initializeAppsDB(false);
  }
  
  public static void initializeAppsDB(boolean paramBoolean)
  {
    if (!initialAppsPolled())
    {
      Logger.log(TAG, 10, "loading initial apps");
      new ApplicationListener.PollInitialAppsTask(null).execute(new Boolean[] { Boolean.valueOf(paramBoolean) });
      return;
    }
    Logger.log(TAG, 10, "initial apps loaded");
  }
  
  private static Long installTimeFromPackageInfo(PackageManager paramPackageManager, String paramString)
  {
    try
    {
      long l = paramPackageManager.getPackageInfo(paramString, 0).firstInstallTime;
      return Long.valueOf(l);
    }
    catch (PackageManager.NameNotFoundException paramPackageManager)
    {
      Logger.log(TAG, 40, paramPackageManager.toString());
      return null;
    }
    catch (IllegalArgumentException paramPackageManager)
    {
      Logger.log(TAG, 40, paramPackageManager.toString());
      return null;
    }
    catch (SecurityException paramPackageManager)
    {
      for (;;) {}
    }
  }
  
  public static void setInitialAppsPolled()
  {
    State.set("POLL_APP_INSTALL", System.currentTimeMillis());
  }
  
  private static List<Application> storeInitialInstalledApplicationList(List<ResolveInfo> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Object localObject = (ResolveInfo)paramList.next();
      JSONObject localJSONObject = new JSONObject();
      try
      {
        localJSONObject.put("packageName", ((ResolveInfo)localObject).activityInfo.packageName);
        localObject = new Application(localJSONObject);
        ((Application)localObject).persist = true;
        localArrayList.add(localObject);
      }
      catch (JSONException localJSONException)
      {
        Logger.log(TAG, 40, localJSONException.toString());
      }
    }
    Application.batchCreate(localArrayList);
    Logger.log(TAG, 10, "app init - app list done");
    return localArrayList;
  }
  
  private static Long updateTimeFromApplicationInfo(PackageManager paramPackageManager, String paramString)
  {
    Object localObject = null;
    try
    {
      paramString = new File(paramPackageManager.getApplicationInfo(paramString, 0).sourceDir);
      paramPackageManager = localObject;
      if (paramString.exists())
      {
        long l = paramString.lastModified();
        paramPackageManager = Long.valueOf(l);
      }
      return paramPackageManager;
    }
    catch (PackageManager.NameNotFoundException paramPackageManager)
    {
      Logger.log(TAG, 40, paramPackageManager.toString());
    }
    return null;
  }
  
  private static Long updateTimeFromPackageInfo(PackageManager paramPackageManager, String paramString)
  {
    try
    {
      long l = paramPackageManager.getPackageInfo(paramString, 0).lastUpdateTime;
      return Long.valueOf(l);
    }
    catch (PackageManager.NameNotFoundException paramPackageManager)
    {
      Logger.log(TAG, 40, paramPackageManager.toString());
      return null;
    }
    catch (IllegalArgumentException paramPackageManager)
    {
      Logger.log(TAG, 40, paramPackageManager.toString());
      return null;
    }
    catch (SecurityException paramPackageManager)
    {
      for (;;) {}
    }
  }
  
  public String getListenerType()
  {
    return "application";
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Intent localIntent = new Intent(paramContext, ApplicationListener.ApplicationListenerService.class);
    localIntent.putExtra("action", getAction(paramIntent.getAction()));
    localIntent.putExtra("data", paramIntent.getData().toString());
    startWakefulService(paramContext, localIntent);
  }
  
  public void start()
  {
    start("ignore");
  }
  
  public void start(String paramString)
  {
    if (this.listenerStarted)
    {
      Logger.log(TAG, 20, "ApplicationListener already initialized");
      return;
    }
    this.listenerStarted = true;
    paramString = new IntentFilter();
    paramString.addAction("android.intent.action.PACKAGE_ADDED");
    paramString.addAction("android.intent.action.PACKAGE_REMOVED");
    paramString.addDataScheme("package");
    LoopLibrary.applicationContext.registerReceiver(this, paramString);
    ApplicationStateListener.initialize();
    Logger.log(TAG, 20, "started");
  }
  
  public void stop()
  {
    if (this.listenerStarted) {}
    try
    {
      LoopLibrary.applicationContext.unregisterReceiver(this);
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;)
      {
        Logger.log(TAG, 40, localIllegalStateException.toString());
        this.listenerStarted = false;
      }
    }
    finally
    {
      this.listenerStarted = false;
    }
    Logger.log(TAG, 20, "stopped");
  }
}
