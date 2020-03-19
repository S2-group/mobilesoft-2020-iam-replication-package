package com.microsoft.launcher;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ContentResolver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Process;
import android.support.b.e;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.microsoft.launcher.mru.bj;
import com.microsoft.launcher.next.c.d;
import com.microsoft.launcher.utils.ah;
import com.microsoft.launcher.utils.ak;
import com.microsoft.launcher.utils.an;
import com.microsoft.launcher.utils.ap;
import com.microsoft.launcher.utils.as;
import com.microsoft.launcher.utils.g;
import com.microsoft.launcher.utils.v;
import com.microsoft.launcher.wallpaper.b.y;
import com.microsoft.launcher.wallpaper.dal.BingWallpaperDownloadService;
import com.microsoft.launcher.wallpaper.dal.h;
import com.microsoft.launcher.wallpaper.dal.r;
import com.microsoft.loop.sdk.api.UserRegistration;
import com.microsoft.loop.sdk.core.LoopSDK;
import com.microsoft.loop.sdk.profile.Applications;
import com.microsoft.loop.sdk.utils.LoopLogger;
import com.microsoft.services.msaoxo.aa;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import ms.loop.lib.core.LoopCallback;
import ms.loop.lib.core.LoopError;
import ms.loop.lib.profile.Application;
import ms.loop.lib.utils.State;
import org.acra.ACRA;
import org.acra.ErrorReporter;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;
import org.acra.sender.HttpSender.Method;
import org.acra.sender.HttpSender.Type;

@ReportsCrashes(customReportContent={org.acra.ReportField.APP_VERSION_CODE, org.acra.ReportField.APP_VERSION_NAME, org.acra.ReportField.ANDROID_VERSION, org.acra.ReportField.PACKAGE_NAME, org.acra.ReportField.REPORT_ID, org.acra.ReportField.BUILD, org.acra.ReportField.STACK_TRACE, org.acra.ReportField.USER_APP_START_DATE, org.acra.ReportField.USER_CRASH_DATE, org.acra.ReportField.TOTAL_MEM_SIZE, org.acra.ReportField.AVAILABLE_MEM_SIZE, org.acra.ReportField.CUSTOM_DATA, org.acra.ReportField.INSTALLATION_ID, org.acra.ReportField.DEVICE_ID}, formUri="https://fexu.cloudant.com/acra-arrowlauncher/_design/acra-storage/_update/report", formUriBasicAuthLogin="ayestedgersedgendurnetim", formUriBasicAuthPassword="7370238c16da1f02c66bb20df9de989dd2a366a0", httpMethod=HttpSender.Method.POST, mode=ReportingInteractionMode.SILENT, reportType=HttpSender.Type.JSON)
public class LauncherApplication
  extends e
  implements LoopCallback
{
  public static com.microsoft.launcher.h.a A = com.microsoft.launcher.h.a.a;
  public static boolean B = false;
  public static int C;
  private static String E = "91a2f69ace254d58943a6bfef5c4a76b";
  private static String F = "Xe0KGgnP+l9CDj/PP/GXYA61fa0o8/OWVlJDiHFxf0eRBFQYuzsVId2iqtUPQSKDxpmWdVz9z9Phz7Is7r0F7Q==";
  private static int G = 1;
  private static HashMap<String, Drawable> H = new HashMap();
  private static boolean I;
  private static float J;
  private static int K = 300;
  private static volatile Context L = null;
  private static Object M = new Object();
  public static final Object a = new Object();
  public static AssetManager b;
  public static Context c;
  public static Launcher d;
  public static Context e;
  public static Handler f;
  public static Resources g;
  public static boolean h;
  public static String i;
  public static int j;
  public static int k;
  public static boolean l = false;
  public static boolean m = false;
  public static boolean n = false;
  public static String o;
  public static String p;
  public static boolean q = true;
  public static boolean r = true;
  public static boolean s = false;
  public static boolean t = false;
  public static String u = "IsLoopFrequencyInit1.2.0";
  public static String v = "isMigratedFromLoopToLocal";
  public static String w = "";
  public static boolean x = false;
  public static boolean y = false;
  public static boolean z = false;
  WeakReference<LauncherProvider> D;
  private jk N;
  private final ContentObserver O = new jg(this, new Handler());
  private en P;
  private nn Q;
  private kr R;
  private Thread.UncaughtExceptionHandler S;
  private Thread.UncaughtExceptionHandler T;
  private boolean U = false;
  private com.microsoft.launcher.wallpaper.b.f V;
  private h W;
  
  public LauncherApplication() {}
  
  public static String a()
  {
    return "com.android.launcher2.prefs";
  }
  
  /* Error */
  public static List<android.content.pm.PackageInfo> a(int paramInt)
  {
    // Byte code:
    //   0: getstatic 205	com/microsoft/launcher/LauncherApplication:c	Landroid/content/Context;
    //   3: invokevirtual 211	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   6: astore_3
    //   7: aload_3
    //   8: iload_0
    //   9: invokevirtual 216	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   12: astore_1
    //   13: aload_1
    //   14: areturn
    //   15: astore_1
    //   16: new 218	java/util/ArrayList
    //   19: dup
    //   20: invokespecial 219	java/util/ArrayList:<init>	()V
    //   23: astore 4
    //   25: aconst_null
    //   26: astore_1
    //   27: invokestatic 225	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   30: ldc -29
    //   32: invokevirtual 231	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   35: astore 5
    //   37: new 233	java/io/BufferedReader
    //   40: dup
    //   41: new 235	java/io/InputStreamReader
    //   44: dup
    //   45: aload 5
    //   47: invokevirtual 241	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   50: invokespecial 244	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   53: invokespecial 247	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   56: astore_2
    //   57: aload_2
    //   58: astore_1
    //   59: aload_2
    //   60: invokevirtual 250	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   63: astore 6
    //   65: aload 6
    //   67: ifnull +64 -> 131
    //   70: aload_2
    //   71: astore_1
    //   72: aload 4
    //   74: aload_3
    //   75: aload 6
    //   77: aload 6
    //   79: bipush 58
    //   81: invokevirtual 256	java/lang/String:indexOf	(I)I
    //   84: iconst_1
    //   85: iadd
    //   86: invokevirtual 260	java/lang/String:substring	(I)Ljava/lang/String;
    //   89: iload_0
    //   90: invokevirtual 264	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   93: invokeinterface 270 2 0
    //   98: pop
    //   99: goto -42 -> 57
    //   102: astore_3
    //   103: aload_2
    //   104: astore_1
    //   105: aload_3
    //   106: invokevirtual 273	java/lang/Exception:printStackTrace	()V
    //   109: aload 4
    //   111: astore_1
    //   112: aload_2
    //   113: ifnull -100 -> 13
    //   116: aload_2
    //   117: invokevirtual 276	java/io/BufferedReader:close	()V
    //   120: aload 4
    //   122: areturn
    //   123: astore_1
    //   124: aload_1
    //   125: invokevirtual 277	java/io/IOException:printStackTrace	()V
    //   128: aload 4
    //   130: areturn
    //   131: aload_2
    //   132: astore_1
    //   133: aload 5
    //   135: invokevirtual 281	java/lang/Process:waitFor	()I
    //   138: pop
    //   139: aload 4
    //   141: astore_1
    //   142: aload_2
    //   143: ifnull -130 -> 13
    //   146: aload_2
    //   147: invokevirtual 276	java/io/BufferedReader:close	()V
    //   150: aload 4
    //   152: areturn
    //   153: astore_1
    //   154: aload_1
    //   155: invokevirtual 277	java/io/IOException:printStackTrace	()V
    //   158: aload 4
    //   160: areturn
    //   161: astore_3
    //   162: aload_1
    //   163: astore_2
    //   164: aload_3
    //   165: astore_1
    //   166: aload_2
    //   167: ifnull +7 -> 174
    //   170: aload_2
    //   171: invokevirtual 276	java/io/BufferedReader:close	()V
    //   174: aload_1
    //   175: athrow
    //   176: astore_2
    //   177: aload_2
    //   178: invokevirtual 277	java/io/IOException:printStackTrace	()V
    //   181: goto -7 -> 174
    //   184: astore_3
    //   185: aload_1
    //   186: astore_2
    //   187: aload_3
    //   188: astore_1
    //   189: goto -23 -> 166
    //   192: astore_3
    //   193: aconst_null
    //   194: astore_2
    //   195: goto -92 -> 103
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	198	0	paramInt	int
    //   12	2	1	localList	List
    //   15	1	1	localException1	Exception
    //   26	86	1	localObject1	Object
    //   123	2	1	localIOException1	java.io.IOException
    //   132	10	1	localObject2	Object
    //   153	10	1	localIOException2	java.io.IOException
    //   165	24	1	localObject3	Object
    //   56	115	2	localObject4	Object
    //   176	2	2	localIOException3	java.io.IOException
    //   186	9	2	localObject5	Object
    //   6	69	3	localPackageManager	PackageManager
    //   102	4	3	localException2	Exception
    //   161	4	3	localObject6	Object
    //   184	4	3	localObject7	Object
    //   192	1	3	localException3	Exception
    //   23	136	4	localArrayList	java.util.ArrayList
    //   35	99	5	localProcess	Process
    //   63	15	6	str	String
    // Exception table:
    //   from	to	target	type
    //   7	13	15	java/lang/Exception
    //   59	65	102	java/lang/Exception
    //   72	99	102	java/lang/Exception
    //   133	139	102	java/lang/Exception
    //   116	120	123	java/io/IOException
    //   146	150	153	java/io/IOException
    //   27	57	161	finally
    //   170	174	176	java/io/IOException
    //   59	65	184	finally
    //   72	99	184	finally
    //   105	109	184	finally
    //   133	139	184	finally
    //   27	57	192	java/lang/Exception
  }
  
  public static boolean a(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().orientation == 2;
  }
  
  private void b(Context paramContext)
  {
    try
    {
      com.microsoft.launcher.d.b.a(new com.microsoft.launcher.d.a(paramContext));
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static boolean b()
  {
    return I;
  }
  
  public static float c()
  {
    return J;
  }
  
  public static int d()
  {
    return K;
  }
  
  public static boolean e()
  {
    return (!TextUtils.isEmpty("prod")) && ("prod".equals("samsung"));
  }
  
  public static Context f()
  {
    if (L != null) {
      return L;
    }
    synchronized (M)
    {
      for (;;)
      {
        Context localContext = L;
        if (localContext != null) {
          break;
        }
        try
        {
          M.wait();
        }
        catch (InterruptedException localInterruptedException) {}
      }
      return L;
    }
  }
  
  private static String n()
  {
    int i1 = Process.myPid();
    Iterator localIterator = ((ActivityManager)c.getSystemService("activity")).getRunningAppProcesses().iterator();
    while (localIterator.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
      if (localRunningAppProcessInfo.pid == i1) {
        return localRunningAppProcessInfo.processName;
      }
    }
    return null;
  }
  
  private void o()
  {
    boolean bool2 = true;
    boolean bool1;
    if (!com.microsoft.launcher.utils.b.c(com.microsoft.launcher.utils.w.L, false))
    {
      if (e())
      {
        com.microsoft.launcher.utils.b.b(com.microsoft.launcher.utils.w.z, false);
        com.microsoft.launcher.utils.b.b(com.microsoft.launcher.utils.w.L, true);
      }
    }
    else
    {
      boolean bool3 = com.microsoft.launcher.utils.b.c(com.microsoft.launcher.utils.w.z, true);
      if (bool3) {
        break label106;
      }
      bool1 = bool2;
      label46:
      Workspace.am = bool1;
      if (!bool3) {
        break label111;
      }
    }
    label106:
    label111:
    for (String str = "Order by usage";; str = "Free style")
    {
      v.a("Apps page type", str);
      return;
      new Random(System.currentTimeMillis());
      if (!com.microsoft.launcher.utils.b.c("IsFirstLoad", true)) {}
      for (bool1 = true;; bool1 = false)
      {
        com.microsoft.launcher.utils.b.b(com.microsoft.launcher.utils.w.z, bool1);
        break;
      }
      bool1 = false;
      break label46;
    }
  }
  
  private void p()
  {
    SharedPreferences localSharedPreferences = getSharedPreferences(a(), 0);
    if ((localSharedPreferences != null) && (localSharedPreferences.getBoolean("First run", true)))
    {
      v.a("Homepage", "Frequent Page");
      v.a("Language", Locale.getDefault().getDisplayLanguage());
      v.a("Channel", com.microsoft.launcher.utils.a.b(this));
      v.a("Arrow first install", 1.0F);
      ak.a("Mixpanel: Arrow first install Homepage Frequent Page lan: " + Locale.getDefault().getDisplayLanguage());
      localSharedPreferences.edit().putBoolean("First run", false).apply();
    }
  }
  
  private void q()
  {
    g.b("uncaughtException", "manually exit in custom uncaught exception handler.");
    this.U = true;
    com.microsoft.launcher.utils.b.b("debug_last_appcrash_page", w);
    Process.killProcess(Process.myPid());
    System.exit(0);
  }
  
  private void r()
  {
    an.a(new jj(this), ap.a);
  }
  
  private void s()
  {
    Object localObject = c;
    r localR = new r((Context)localObject, as.c((Context)localObject));
    localR.a();
    h localH = h.a();
    localH.a((Context)localObject);
    localObject = new y((Context)localObject, localR);
    this.V = com.microsoft.launcher.wallpaper.b.f.a();
    this.V.a(c, localR, localH, (y)localObject, com.microsoft.launcher.next.model.c.a.c.a());
    if ((com.microsoft.launcher.utils.b.c("IsFirstLoad", true)) || (com.microsoft.launcher.utils.b.c("IsLauncherApplicationFirstLoad", true)))
    {
      this.V.g();
      com.microsoft.launcher.utils.b.a("IsLauncherApplicationFirstLoad", false);
      return;
    }
    this.V.b();
  }
  
  private void t()
  {
    this.V.a(this.W);
    this.V.c();
    this.W.b();
  }
  
  private void u()
  {
    g.a("LauncherApplication", "startRegularAlarm");
  }
  
  private void v()
  {
    g.a("LauncherApplication", "stopRegularAlarm");
  }
  
  private void w()
  {
    SharedPreferences localSharedPreferences = getSharedPreferences(a(), 0);
    String str1;
    if (localSharedPreferences != null)
    {
      String str2 = localSharedPreferences.getString("acra_uuid", null);
      str1 = str2;
      if (str2 == null)
      {
        str1 = UUID.randomUUID().toString();
        localSharedPreferences.edit().putString("acra_uuid", str1).apply();
      }
      ACRA.getErrorReporter().putCustomData("acra_uuid", str1);
    }
    try
    {
      str1 = MixpanelAPI.getInstance(this, o).getDistinctId();
      ACRA.getErrorReporter().putCustomData("mixpanel_id", str1);
      v.a("mixpanel id", str1);
      com.microsoft.launcher.utils.b.a("mixpanel id", str1);
      return;
    }
    catch (Exception localException)
    {
      v.a("getMixpanelIdException");
    }
  }
  
  private void x()
  {
    if ((j <= 600) || (!com.microsoft.launcher.next.c.w.c())) {}
    for (boolean bool = true;; bool = false)
    {
      B = com.microsoft.launcher.utils.b.c(com.microsoft.launcher.utils.w.h, bool);
      if (!com.microsoft.launcher.utils.b.b(com.microsoft.launcher.utils.w.h))
      {
        com.microsoft.launcher.utils.b.a(com.microsoft.launcher.utils.w.h, B);
        if (B) {
          v.a("High performance mode turn on off", "Status", String.valueOf(B), "Type", "Automatic", 0.1F);
        }
      }
      return;
    }
  }
  
  private void y()
  {
    if (!com.microsoft.launcher.f.c.b) {
      return;
    }
    switch (G)
    {
    default: 
      return;
    }
    E = "27945dc7094f44759acd13e7601c2ac4";
    F = "4LIip/2D51X0vQs+MOSDxefxMqhNYgXvv2Y9LlSSWrKC3i7mH3Zs8HPduxgzIDJGI1KLy6VVA6Au26ul7RxU3A==";
  }
  
  private void z()
  {
    Object localObject1;
    Object localObject2;
    String str;
    Object localObject3;
    if (!com.microsoft.launcher.utils.b.c(u, false))
    {
      localObject1 = com.microsoft.launcher.utils.b.c("AppFrequency_Home_Backup", new ConcurrentHashMap());
      if (((ConcurrentHashMap)localObject1).size() > 0)
      {
        localObject1 = ((ConcurrentHashMap)localObject1).entrySet().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (Map.Entry)((Iterator)localObject1).next();
          str = (String)((Map.Entry)localObject2).getKey();
          localObject3 = d.b(str);
          if (localObject3 != null)
          {
            com.microsoft.launcher.next.c.b.a(str, (String)localObject3, (int)((Double)((Map.Entry)localObject2).getValue()).doubleValue());
            g.b("migrateFrequency", "1.1.3: " + (String)((Map.Entry)localObject2).getKey(), new Object[] { "" + ((Double)((Map.Entry)localObject2).getValue()).doubleValue() });
          }
        }
      }
      localObject1 = com.microsoft.launcher.utils.b.b("AppFrequency_Home", new ConcurrentHashMap());
      if (((ConcurrentHashMap)localObject1).size() > 0)
      {
        localObject1 = ((ConcurrentHashMap)localObject1).entrySet().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (Map.Entry)((Iterator)localObject1).next();
          str = (String)((Map.Entry)localObject2).getKey();
          localObject3 = d.b(str);
          if (localObject3 != null)
          {
            com.microsoft.launcher.next.c.b.a(str, (String)localObject3, (int)((Long)((Map.Entry)localObject2).getValue()).longValue());
            g.b("migrateFrequency", (String)((Map.Entry)localObject2).getKey(), new Object[] { "" + ((Long)((Map.Entry)localObject2).getValue()).longValue() });
          }
        }
      }
      com.microsoft.launcher.next.c.b.b();
      com.microsoft.launcher.utils.b.a(u, true);
    }
    if (!com.microsoft.launcher.utils.b.c(v, false))
    {
      localObject1 = com.microsoft.launcher.f.a.a().b();
      if ((localObject1 != null) && (((Map)localObject1).size() > 0))
      {
        localObject1 = ((Map)localObject1).entrySet().iterator();
        while (((Iterator)localObject1).hasNext()) {
          try
          {
            localObject3 = (Map.Entry)((Iterator)localObject1).next();
            localObject2 = (String)((Map.Entry)localObject3).getKey();
            str = d.b((String)localObject2);
            localObject3 = (Application)((Map.Entry)localObject3).getValue();
            if ((localObject3 != null) && (((Application)localObject3).score != 0.0D) && (str != null)) {
              com.microsoft.launcher.next.c.b.a((String)localObject2, str, (int)((Application)localObject3).score);
            }
          }
          catch (Exception localException)
          {
            v.a("MigrationFromLoopToLocalFailed", 1.0F);
          }
        }
      }
      com.microsoft.launcher.utils.b.a(v, true);
    }
  }
  
  jk a(Launcher paramLauncher)
  {
    this.N.a(paramLauncher);
    return this.N;
  }
  
  void a(LauncherProvider paramLauncherProvider)
  {
    this.D = new WeakReference(paramLauncherProvider);
  }
  
  public void a(Thread paramThread, Throwable paramThrowable)
  {
    g.b("uncaughtException", "bottomWrapperUncaughtException called");
    long l1 = com.microsoft.launcher.utils.b.c("debug_last_appcrash_time_in_bottom_wrapper", 0L);
    Object localObject3 = com.microsoft.launcher.utils.b.c("debug_last_appcrash_trace_in_bottom_wrapper", "");
    Object localObject2 = Log.getStackTraceString(paramThrowable);
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = "";
    }
    localObject2 = localObject3;
    if (((String)localObject3).length() > 40) {
      localObject2 = ((String)localObject3).substring(0, 40);
    }
    if (((String)localObject1).length() > 40) {
      localObject3 = ((String)localObject1).substring(0, 40);
    }
    for (;;)
    {
      boolean bool = ((String)localObject2).equals(localObject3);
      if (!TextUtils.isEmpty((CharSequence)localObject1)) {
        com.microsoft.launcher.utils.b.a("debug_last_appcrash_trace_in_bottom_wrapper", (String)localObject1);
      }
      com.microsoft.launcher.utils.b.b("debug_last_appcrash_time_in_bottom_wrapper", System.currentTimeMillis());
      if (!this.U)
      {
        long l2 = System.currentTimeMillis() - l1;
        if ((l2 >= 20000L) && ((!bool) || (l2 >= 300000L))) {
          break label202;
        }
      }
      try
      {
        Thread.sleep(500L);
        if (this.S != null)
        {
          g.b("uncaughtException", "Passing to default uncaught exception handler.");
          this.S.uncaughtException(paramThread, paramThrowable);
        }
        return;
        localObject3 = localObject1;
        continue;
        label202:
        g.b("uncaughtException", System.currentTimeMillis() + "," + l1 + "," + (System.currentTimeMillis() - l1));
        g.b("uncaughtException", "first time");
      }
      catch (InterruptedException localInterruptedException)
      {
        try
        {
          Thread.sleep(3000L);
          g.b("uncaughtException", "start exit");
          q();
          return;
          localInterruptedException = localInterruptedException;
        }
        catch (InterruptedException paramThread)
        {
          for (;;) {}
        }
      }
    }
  }
  
  public void b(Thread paramThread, Throwable paramThrowable)
  {
    int i2 = 1;
    g.b("uncaughtException", "topWrapperUncaughtException called");
    long l1 = com.microsoft.launcher.utils.b.c("debug_last_appcrash_time", 0L);
    paramThrowable.printStackTrace();
    Object localObject4 = com.microsoft.launcher.utils.b.c("debug_last_appcrash_trace", "");
    Object localObject3 = Log.getStackTraceString(paramThrowable);
    Object localObject1;
    boolean bool;
    long l2;
    if (localObject3 == null)
    {
      localObject1 = "";
      localObject3 = localObject4;
      if (((String)localObject4).length() > 40) {
        localObject3 = ((String)localObject4).substring(0, 40);
      }
      if (((String)localObject1).length() <= 40) {
        break label372;
      }
      localObject4 = ((String)localObject1).substring(0, 40);
      bool = ((String)localObject3).equals(localObject4);
      if (!TextUtils.isEmpty((CharSequence)localObject1)) {
        com.microsoft.launcher.utils.b.a("debug_last_appcrash_trace", (String)localObject1);
      }
      com.microsoft.launcher.utils.b.b("debug_last_appcrash_time", System.currentTimeMillis());
      com.microsoft.launcher.utils.b.b("debug_last_appcrash_handling", true);
      if (!this.U)
      {
        l2 = System.currentTimeMillis() - l1;
        if ((l2 >= 20000L) && ((!bool) || (l2 >= 300000L))) {
          break label390;
        }
        g.b("uncaughtException", System.currentTimeMillis() + "," + l1 + "," + (System.currentTimeMillis() - l1));
      }
    }
    for (;;)
    {
      try
      {
        c.getPackageManager().clearPackagePreferredActivities(c.getPackageName());
        i1 = com.microsoft.launcher.utils.b.c(com.microsoft.launcher.utils.w.A, 0) + 1;
        com.microsoft.launcher.utils.b.a(com.microsoft.launcher.utils.w.A, i1);
        if (i1 < com.microsoft.launcher.utils.w.B) {
          break label448;
        }
        g.b("uncaughtException", "exit duplicate uncaught exception limit: count = " + i1);
        i1 = 0;
        if (i1 == 0) {
          break label423;
        }
        if (this.T != null)
        {
          g.b("uncaughtException", "send by ACRA");
          this.T.uncaughtException(paramThread, paramThrowable);
        }
        return;
        if (!((String)localObject3).contains("com.microsoft.loop"))
        {
          localObject1 = localObject3;
          if (!((String)localObject3).contains("ms.loop.lib")) {
            break;
          }
        }
        com.microsoft.launcher.utils.b.b(com.microsoft.launcher.utils.w.e, false);
        Object localObject2;
        try
        {
          LoopSDK.deleteAllSignals();
          localObject1 = localObject3;
        }
        catch (Exception localException1)
        {
          localObject2 = localObject3;
        }
        break;
        label372:
        localObject4 = localObject2;
      }
      catch (Exception localException2)
      {
        v.a("resetDefaultLauncherException");
        continue;
      }
      label390:
      int i1 = i2;
      if (!bool)
      {
        i1 = i2;
        if (l2 > 3600000L)
        {
          com.microsoft.launcher.utils.b.b(com.microsoft.launcher.utils.w.A, 0);
          i1 = i2;
          continue;
          label423:
          if (this.S != null)
          {
            g.b("Passing to default uncaught exception handler.");
            this.S.uncaughtException(paramThread, paramThrowable);
            return;
            label448:
            i1 = 1;
          }
        }
      }
    }
  }
  
  public en g()
  {
    return this.P;
  }
  
  jk h()
  {
    return this.N;
  }
  
  nn i()
  {
    return this.Q;
  }
  
  public void initializeLoopLibrary()
  {
    try
    {
      y();
      if (!LoopSDK.isLoopInitialized())
      {
        LoopSDK.setEnvironment(G);
        LoopSDK.setApplicationContext(this);
        if (com.microsoft.launcher.utils.b.c(com.microsoft.launcher.utils.w.u, false))
        {
          String str = UserRegistration.createClientId(this);
          LoopSDK.setClientId(str);
          LoopSDK.setUserId(str);
          LoopSDK.setDeviceId(str);
        }
        ak.a("loop initialization started");
        LoopSDK.initialize(this, E, F, this);
        Applications.initialize();
        ak.a("loop initialization finished");
        State.set("lastHour", -1);
        com.microsoft.launcher.f.b.a().b();
        z();
        return;
      }
      LoopLogger.log("LauncherApplication", 40, "onCreate -- loopSDK is already initialized");
      return;
    }
    catch (Exception localException)
    {
      v.a("Error: LauncherApplication initializeLoopLibrary initialize Loop Exception");
    }
  }
  
  public void j()
  {
    if (this.Q != null) {
      this.Q.close();
    }
    this.Q = new nn(this);
  }
  
  public LauncherProvider k()
  {
    if (this.D != null) {
      return (LauncherProvider)this.D.get();
    }
    return null;
  }
  
  public kr l()
  {
    return this.R;
  }
  
  public void m()
  {
    try
    {
      LoopSDK.unInitialize();
      com.microsoft.launcher.f.b.a().c();
      return;
    }
    catch (Exception localException)
    {
      v.a("unIntializeLoopException");
    }
  }
  
  public void onCreate()
  {
    c = getApplicationContext();
    L = c;
    synchronized (M)
    {
      M.notifyAll();
      ??? = n();
      if ((??? != null) && (((String)???).endsWith("Uninstall"))) {
        return;
      }
    }
    super.onCreate();
    f = new Handler();
    g = getResources();
    b = getAssets();
    j = as.e();
    k = as.f();
    b(c);
    v.a();
    v.a(this);
    C = android.support.v4.b.a.b(this, 2131689561);
    o();
    if (com.microsoft.launcher.utils.b.c("debug_last_appcrash_handling", false))
    {
      com.microsoft.launcher.utils.b.a("debug_last_appcrash_handling", false);
      v.a("Recover from crash and restart", "Page current", com.microsoft.launcher.utils.b.c("debug_last_appcrash_page", ""), 1.0F);
    }
    x();
    initializeLoopLibrary();
    this.S = Thread.getDefaultUncaughtExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler(new jh(this));
    ACRA.init(this);
    w();
    this.T = Thread.getDefaultUncaughtExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler(new ji(this));
    aa.a().a(getApplicationContext(), "000000004406D438", Arrays.asList(new String[] { "service::ssl.live.com::MBI_SSL" }), false);
    if ((!e()) && (com.microsoft.launcher.utils.b.c("IsFirstLoad", true)))
    {
      BingWallpaperDownloadService.a(c, true);
      c.startService(BingWallpaperDownloadService.a(c));
    }
    bj.a().a(this);
    com.microsoft.launcher.mru.identity.f.a().a(this);
    com.microsoft.launcher.mru.model.a.a().a(this);
    try
    {
      I = getResources().getBoolean(2131427336);
      J = getResources().getDisplayMetrics().density;
      this.Q = new nn(this);
      this.P = new en(this);
      this.N = new jk(this, this.P);
      this.R = new kr();
      ??? = new IntentFilter("android.intent.action.PACKAGE_ADDED");
      ((IntentFilter)???).addAction("android.intent.action.PACKAGE_REMOVED");
      ((IntentFilter)???).addAction("android.intent.action.PACKAGE_CHANGED");
      ((IntentFilter)???).addDataScheme("package");
      registerReceiver(this.N, (IntentFilter)???);
      ??? = new IntentFilter();
      ((IntentFilter)???).addAction("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE");
      ((IntentFilter)???).addAction("android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE");
      ((IntentFilter)???).addAction("android.intent.action.LOCALE_CHANGED");
      ((IntentFilter)???).addAction("android.intent.action.CONFIGURATION_CHANGED");
      registerReceiver(this.N, (IntentFilter)???);
      ??? = new IntentFilter();
      ((IntentFilter)???).addAction("android.search.action.GLOBAL_SEARCH_ACTIVITY_CHANGED");
      registerReceiver(this.N, (IntentFilter)???);
      ??? = new IntentFilter();
      ((IntentFilter)???).addAction("android.search.action.SEARCHABLES_CHANGED");
      registerReceiver(this.N, (IntentFilter)???);
      getContentResolver().registerContentObserver(kz.a, true, this.O);
      ah.a(this);
      r();
      u();
      p();
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        I = false;
      }
    }
  }
  
  public void onInitializationFailed(LoopError paramLoopError)
  {
    if (!com.microsoft.launcher.f.c.b) {
      return;
    }
    LoopLogger.log("LauncherApplication", 20, paramLoopError.toString());
  }
  
  public void onInitialized()
  {
    if (!com.microsoft.launcher.f.c.b) {
      return;
    }
    LoopLogger.log("LauncherApplication", 20, "sdk initialized");
  }
  
  public void onServiceStarted(int paramInt)
  {
    if (!com.microsoft.launcher.f.c.b) {
      return;
    }
    LoopLogger.log("LauncherApplication", 20, "service started " + paramInt);
  }
  
  public void onServiceStopped(int paramInt)
  {
    if (!com.microsoft.launcher.f.c.b) {
      return;
    }
    LoopLogger.log("LauncherApplication", 20, "service stopped " + paramInt);
  }
  
  public void onTerminate()
  {
    String str = n();
    if ((str != null) && (str.endsWith("Uninstall"))) {
      return;
    }
    super.onTerminate();
    v();
    unregisterReceiver(this.N);
    getContentResolver().unregisterContentObserver(this.O);
    t();
  }
}
