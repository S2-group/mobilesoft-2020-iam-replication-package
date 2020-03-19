package com.estrongs.android.pop;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlarmManager;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Process;
import android.support.multidex.MultiDexApplication;
import com.baidu.alivetimelib.delegate.ESAliveSender;
import com.baidu.sapi2.SapiAccountManager;
import com.baidu.sapi2.SapiConfiguration.Builder;
import com.baidu.sapi2.SapiConfiguration.SmsLoginConfig;
import com.baidu.sapi2.utils.enums.Domain;
import com.baidu.sapi2.utils.enums.FastLoginFeature;
import com.baidu.sapi2.utils.enums.LoginShareStrategy;
import com.baidu.sapi2.utils.enums.Switch;
import com.estrongs.android.nativetool.NativeAuth;
import com.estrongs.android.pop.app.ReportAliveReceiver;
import com.estrongs.android.pop.app.bi;
import com.estrongs.android.pop.app.openscreenad.SplashScreenManager;
import com.estrongs.android.pop.app.scene.e.k;
import com.estrongs.android.pop.app.scene.e.p;
import com.estrongs.android.pop.app.unlock.l;
import com.estrongs.android.pop.utils.w;
import com.estrongs.android.pop.view.FileExplorerActivity;
import com.estrongs.android.scanner.service.FileScannerService;
import com.estrongs.android.ui.preference.q;
import com.estrongs.android.ui.view.an;
import com.estrongs.android.util.aq;
import com.estrongs.android.util.as;
import com.estrongs.android.util.ba;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class FexApplication
  extends MultiDexApplication
  implements com.estrongs.android.pop.app.f.b
{
  public static boolean a = true;
  private static FexApplication f = null;
  private static boolean s = false;
  boolean b = false;
  boolean c = false;
  public boolean d;
  Application.ActivityLifecycleCallbacks e = new i(this);
  private boolean g = false;
  private PackageManager h;
  private List<j> i;
  private boolean j = false;
  private boolean k = false;
  private boolean l = false;
  private boolean m = false;
  private com.estrongs.android.pop.app.service.b n;
  private Handler o;
  private List<q> p;
  private boolean q;
  private long r = -1L;
  private int t = 0;
  
  public FexApplication() {}
  
  private void A()
  {
    com.estrongs.android.pop.view.a.a = com.estrongs.android.pop.utils.a.a(a(), "oem");
    com.estrongs.android.pop.view.a.b = com.estrongs.android.pop.utils.a.a(a(), "channel");
  }
  
  private void B()
  {
    com.estrongs.android.j.e localE = new com.estrongs.android.j.e("lock_realtimemonitor");
    com.estrongs.android.j.g.a().a(localE);
    if (localE.c())
    {
      this.d = false;
      localE.b(false, false);
    }
    for (;;)
    {
      localE = new com.estrongs.android.j.e("lock_nomedia");
      localE.a("lock_realtimemonitor");
      com.estrongs.android.j.g.a().a(localE);
      localE = new com.estrongs.android.j.e("lock_theme");
      com.estrongs.android.j.g.a().a(localE);
      localE = new com.estrongs.android.j.e("lock_SMB2");
      com.estrongs.android.j.g.a().a(localE);
      localE = new com.estrongs.android.j.e("lock_summer_theme", true, true, 1);
      com.estrongs.android.j.g.a().a(localE);
      l.a().b();
      return;
      localE.a(true, false);
      this.d = true;
    }
  }
  
  private void C()
  {
    com.dianxinos.dxservice.stat.t localT = com.dianxinos.dxservice.stat.t.a(this);
    localT.a();
    localT.a(true, "com.estrongs.*");
  }
  
  public static FexApplication a()
  {
    return f;
  }
  
  public static String a(Context paramContext, int paramInt)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.getRunningAppProcesses();
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if (localRunningAppProcessInfo.pid == paramInt) {
        return localRunningAppProcessInfo.processName;
      }
    }
    return null;
  }
  
  public static void a(Context paramContext)
  {
    if (!s) {}
    try
    {
      paramContext = new SapiConfiguration.Builder(paramContext).setProductLineInfo("esfb", "1", "3e504de3df373ce5e1080f3b9c33afba").fastLoginSupport(new FastLoginFeature[] { FastLoginFeature.SINA_WEIBO_WEBVIEW, FastLoginFeature.TX_QQ_WEBVIEW, FastLoginFeature.RENREN_WEBVIEW }).enableB2CSync(true).setRuntimeEnvironment(Domain.DOMAIN_ONLINE).initialShareStrategy(LoginShareStrategy.SILENT).smsLoginConfig(new SapiConfiguration.SmsLoginConfig(Switch.OFF, Switch.OFF, Switch.OFF)).debug(true).build();
      SapiAccountManager.getInstance().init(paramContext);
      s = true;
      return;
    }
    catch (Exception paramContext)
    {
      s = false;
    }
  }
  
  private void a(String paramString)
  {
    paramString = com.estrongs.android.pop.app.messagebox.d.a().a(paramString);
    if ((paramString != null) && ((paramString instanceof com.estrongs.android.pop.app.scene.b.b))) {
      ((com.estrongs.android.pop.app.scene.b.b)paramString).d();
    }
  }
  
  public static boolean e()
  {
    do
    {
      try
      {
        if (com.estrongs.android.pop.view.a.a.equalsIgnoreCase("Market")) {
          return false;
        }
        FexApplication localFexApplication = f;
        localObject = localFexApplication;
        if (localFexApplication != null) {
          continue;
        }
        localObject = f;
      }
      catch (Exception localException)
      {
        Object localObject;
        boolean bool;
        return false;
      }
      localObject = (ActivityManager)((Context)localObject).getSystemService("activity");
      if (localObject == null) {
        return false;
      }
      bool = ((Boolean)new aq(localObject).a("isUserAMonkey")).booleanValue();
      return bool;
    } while (localException != null);
    return false;
  }
  
  private void r()
  {
    try
    {
      
      if (!ae.w)
      {
        this.g = true;
        return;
      }
      this.i = new ArrayList();
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  private void s()
  {
    long l1 = System.currentTimeMillis();
    com.estrongs.android.h.a.a();
    B();
    x();
    com.estrongs.android.pop.app.charge.a.a().a(this);
    com.estrongs.android.b.a.a.a(this);
    com.estrongs.android.pop.app.b.a.a().a(this);
    try
    {
      com.estrongs.android.i.c.a().b();
      AlarmManager localAlarmManager = (AlarmManager)getSystemService("alarm");
      PendingIntent localPendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(this, ReportAliveReceiver.class), 134217728);
      localAlarmManager.cancel(localPendingIntent);
      localAlarmManager.setRepeating(0, System.currentTimeMillis() + 86400000, 86400000, localPendingIntent);
      com.estrongs.android.k.c.a().d();
      ap.a().d();
      com.estrongs.android.b.c.a.a(this);
      com.estrongs.android.b.b.a.a(this);
      FileScannerService.a(this);
      com.estrongs.android.pop.app.c.t.b();
      new ba().b(this);
      ESAliveSender.start(this);
      com.estrongs.android.util.n.a("initBackground takes : " + (System.currentTimeMillis() - l1));
      if (com.estrongs.android.ui.notification.a.c.a().i()) {
        com.estrongs.android.ui.notification.a.c.a().c();
      }
      am.b();
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  private void t()
  {
    new Thread(new d(this)).start();
  }
  
  private void u()
  {
    com.dianxinos.dxservice.core.f.a("others");
    try
    {
      com.dianxinos.dxservice.core.f.b("prod");
      v();
      C();
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  private void v()
  {
    try
    {
      com.dianxinos.library.notify.g localG = new com.dianxinos.library.notify.g();
      localG.a = a();
      localG.b = false;
      localG.e = "others";
      com.dianxinos.library.notify.c.a(localG);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void w()
  {
    com.estrongs.fs.d.a("http", "com.estrongs.fs.impl.http.HttpFileSystem");
    Object localObject = new com.estrongs.fs.impl.m.b();
    com.estrongs.fs.d.a("http", (com.estrongs.fs.x)localObject);
    com.estrongs.fs.d.a("https", (com.estrongs.fs.x)localObject);
    com.estrongs.fs.d.a("flashair", new com.estrongs.fs.impl.k.c());
    localObject = new com.estrongs.fs.impl.l.a();
    ((com.estrongs.fs.impl.l.a)localObject).a("local", new com.estrongs.fs.impl.l.c(getContentResolver()));
    com.estrongs.fs.d.a("gallery", (com.estrongs.fs.x)localObject);
    com.estrongs.fs.d.a("adb", new com.estrongs.fs.impl.adb.c());
    com.estrongs.fs.d.a(as.bR("search://"), com.estrongs.fs.impl.s.b.a());
    com.estrongs.fs.d.c();
  }
  
  private void x()
  {
    Object localObject = new p(2);
    com.estrongs.android.pop.app.scene.c.a().a((com.estrongs.android.pop.app.scene.e.c)localObject);
    localObject = new com.estrongs.android.pop.app.scene.e.c(1);
    com.estrongs.android.pop.app.scene.c.a().a((com.estrongs.android.pop.app.scene.e.c)localObject);
    localObject = new com.estrongs.android.pop.app.scene.e.c(3);
    com.estrongs.android.pop.app.scene.c.a().a((com.estrongs.android.pop.app.scene.e.c)localObject);
    com.estrongs.android.pop.app.scene.c.a().a(3, new com.estrongs.android.pop.app.scene.a.e());
    localObject = new com.estrongs.android.pop.app.scene.e.g(4);
    com.estrongs.android.pop.app.scene.c.a().a((com.estrongs.android.pop.app.scene.e.c)localObject);
    localObject = new com.estrongs.android.pop.app.scene.e.h(5);
    com.estrongs.android.pop.app.scene.c.a().a((com.estrongs.android.pop.app.scene.e.c)localObject);
    localObject = new k(6);
    com.estrongs.android.pop.app.scene.c.a().a((com.estrongs.android.pop.app.scene.e.c)localObject);
    localObject = new com.estrongs.android.pop.app.scene.e.n(7);
    com.estrongs.android.pop.app.scene.c.a().a((com.estrongs.android.pop.app.scene.e.c)localObject);
    a(com.estrongs.android.pop.app.a.a.k);
    a(com.estrongs.android.pop.app.a.a.l);
    a(com.estrongs.android.pop.app.a.a.m);
    a(com.estrongs.android.pop.app.a.a.n);
    a(com.estrongs.android.pop.app.a.a.o);
  }
  
  /* Error */
  private void y()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore_3
    //   4: aload_0
    //   5: invokevirtual 619	com/estrongs/android/pop/FexApplication:getFilesDir	()Ljava/io/File;
    //   8: astore_1
    //   9: aload_3
    //   10: astore_2
    //   11: invokestatic 624	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   14: astore 5
    //   16: aload_1
    //   17: ifnull +142 -> 159
    //   20: aload 5
    //   22: astore_2
    //   23: aload_0
    //   24: invokevirtual 619	com/estrongs/android/pop/FexApplication:getFilesDir	()Ljava/io/File;
    //   27: invokevirtual 629	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   30: invokestatic 632	com/estrongs/android/util/g:a	(Ljava/lang/String;)V
    //   33: aload 5
    //   35: astore_2
    //   36: aload_0
    //   37: invokevirtual 619	com/estrongs/android/pop/FexApplication:getFilesDir	()Ljava/io/File;
    //   40: invokevirtual 629	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   43: invokestatic 635	com/estrongs/fs/impl/local/m:a	(Ljava/lang/String;)V
    //   46: aload_1
    //   47: astore_3
    //   48: aload 5
    //   50: astore 4
    //   52: aload 4
    //   54: ifnull +104 -> 158
    //   57: new 626	java/io/File
    //   60: dup
    //   61: getstatic 638	com/estrongs/android/pop/a:a	Ljava/lang/String;
    //   64: invokespecial 639	java/io/File:<init>	(Ljava/lang/String;)V
    //   67: astore_1
    //   68: aload_1
    //   69: invokevirtual 642	java/io/File:exists	()Z
    //   72: ifeq +181 -> 253
    //   75: aload_1
    //   76: invokevirtual 645	java/io/File:isFile	()Z
    //   79: ifeq +174 -> 253
    //   82: aload_1
    //   83: invokevirtual 648	java/io/File:delete	()Z
    //   86: pop
    //   87: aload_1
    //   88: invokevirtual 651	java/io/File:mkdir	()Z
    //   91: ifne +162 -> 253
    //   94: new 196	java/lang/Exception
    //   97: dup
    //   98: invokespecial 652	java/lang/Exception:<init>	()V
    //   101: athrow
    //   102: astore_1
    //   103: aload_0
    //   104: invokevirtual 655	com/estrongs/android/pop/FexApplication:getCacheDir	()Ljava/io/File;
    //   107: invokevirtual 629	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   110: invokestatic 658	com/estrongs/android/util/br:a	(Ljava/lang/String;)V
    //   113: aload_0
    //   114: invokevirtual 655	com/estrongs/android/pop/FexApplication:getCacheDir	()Ljava/io/File;
    //   117: invokevirtual 629	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   120: invokestatic 661	com/estrongs/android/util/aa:a	(Ljava/lang/String;)V
    //   123: aload_0
    //   124: invokevirtual 655	com/estrongs/android/pop/FexApplication:getCacheDir	()Ljava/io/File;
    //   127: invokevirtual 629	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   130: astore_2
    //   131: aload_3
    //   132: ifnull +257 -> 389
    //   135: aload_3
    //   136: invokevirtual 629	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   139: astore_1
    //   140: aload_2
    //   141: aload_1
    //   142: invokestatic 664	com/estrongs/fs/impl/o/b:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   145: aload_3
    //   146: ifnull +250 -> 396
    //   149: aload_3
    //   150: invokevirtual 629	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   153: astore_1
    //   154: aload_1
    //   155: invokestatic 667	com/estrongs/android/util/bc:a	(Ljava/lang/String;)V
    //   158: return
    //   159: aload 5
    //   161: astore_2
    //   162: aload_0
    //   163: ldc_w 669
    //   166: iconst_3
    //   167: invokevirtual 673	com/estrongs/android/pop/FexApplication:getDir	(Ljava/lang/String;I)Ljava/io/File;
    //   170: astore 6
    //   172: aload 5
    //   174: astore_2
    //   175: aload 6
    //   177: invokevirtual 642	java/io/File:exists	()Z
    //   180: ifne +29 -> 209
    //   183: aload 5
    //   185: astore 4
    //   187: aload_1
    //   188: astore_3
    //   189: aload 5
    //   191: astore_2
    //   192: aload 6
    //   194: invokevirtual 651	java/io/File:mkdir	()Z
    //   197: ifne -145 -> 52
    //   200: aload 5
    //   202: astore 4
    //   204: aload_1
    //   205: astore_3
    //   206: goto -154 -> 52
    //   209: aload 5
    //   211: astore_2
    //   212: aload 6
    //   214: invokevirtual 629	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   217: invokestatic 632	com/estrongs/android/util/g:a	(Ljava/lang/String;)V
    //   220: aload 5
    //   222: astore_2
    //   223: aload 6
    //   225: invokevirtual 629	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   228: invokestatic 635	com/estrongs/fs/impl/local/m:a	(Ljava/lang/String;)V
    //   231: aload 5
    //   233: astore 4
    //   235: aload_1
    //   236: astore_3
    //   237: goto -185 -> 52
    //   240: astore_3
    //   241: aload_3
    //   242: invokevirtual 468	java/lang/Exception:printStackTrace	()V
    //   245: aload_2
    //   246: astore 4
    //   248: aload_1
    //   249: astore_3
    //   250: goto -198 -> 52
    //   253: new 626	java/io/File
    //   256: dup
    //   257: getstatic 675	com/estrongs/android/pop/a:i	Ljava/lang/String;
    //   260: invokespecial 639	java/io/File:<init>	(Ljava/lang/String;)V
    //   263: astore_1
    //   264: aload_1
    //   265: invokevirtual 642	java/io/File:exists	()Z
    //   268: ifne +18 -> 286
    //   271: aload_1
    //   272: invokevirtual 678	java/io/File:mkdirs	()Z
    //   275: ifne +11 -> 286
    //   278: new 196	java/lang/Exception
    //   281: dup
    //   282: invokespecial 652	java/lang/Exception:<init>	()V
    //   285: athrow
    //   286: invokestatic 682	com/estrongs/android/pop/b:d	()Z
    //   289: ifne +17 -> 306
    //   292: ldc_w 684
    //   295: invokestatic 687	com/estrongs/android/util/z:a	(Ljava/lang/String;)V
    //   298: new 196	java/lang/Exception
    //   301: dup
    //   302: invokespecial 652	java/lang/Exception:<init>	()V
    //   305: athrow
    //   306: getstatic 675	com/estrongs/android/pop/a:i	Ljava/lang/String;
    //   309: invokestatic 658	com/estrongs/android/util/br:a	(Ljava/lang/String;)V
    //   312: getstatic 675	com/estrongs/android/pop/a:i	Ljava/lang/String;
    //   315: invokestatic 661	com/estrongs/android/util/aa:a	(Ljava/lang/String;)V
    //   318: getstatic 675	com/estrongs/android/pop/a:i	Ljava/lang/String;
    //   321: astore_2
    //   322: aload_3
    //   323: ifnull +45 -> 368
    //   326: aload_3
    //   327: invokevirtual 629	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   330: astore_1
    //   331: aload_2
    //   332: aload_1
    //   333: invokestatic 664	com/estrongs/fs/impl/o/b:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   336: getstatic 675	com/estrongs/android/pop/a:i	Ljava/lang/String;
    //   339: astore_2
    //   340: aload_3
    //   341: ifnull +34 -> 375
    //   344: aload_3
    //   345: invokevirtual 629	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   348: astore_1
    //   349: aload_2
    //   350: aload_1
    //   351: invokestatic 692	com/estrongs/android/pop/spfs/SPFileSystem:setPath	(Ljava/lang/String;Ljava/lang/String;)V
    //   354: aload_3
    //   355: ifnull +27 -> 382
    //   358: aload_3
    //   359: invokevirtual 629	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   362: astore_1
    //   363: aload_1
    //   364: invokestatic 667	com/estrongs/android/util/bc:a	(Ljava/lang/String;)V
    //   367: return
    //   368: getstatic 675	com/estrongs/android/pop/a:i	Ljava/lang/String;
    //   371: astore_1
    //   372: goto -41 -> 331
    //   375: getstatic 675	com/estrongs/android/pop/a:i	Ljava/lang/String;
    //   378: astore_1
    //   379: goto -30 -> 349
    //   382: getstatic 675	com/estrongs/android/pop/a:i	Ljava/lang/String;
    //   385: astore_1
    //   386: goto -23 -> 363
    //   389: getstatic 675	com/estrongs/android/pop/a:i	Ljava/lang/String;
    //   392: astore_1
    //   393: goto -253 -> 140
    //   396: getstatic 675	com/estrongs/android/pop/a:i	Ljava/lang/String;
    //   399: astore_1
    //   400: goto -246 -> 154
    //   403: astore_1
    //   404: return
    //   405: astore_3
    //   406: aconst_null
    //   407: astore_1
    //   408: goto -167 -> 241
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	411	0	this	FexApplication
    //   8	80	1	localFile1	java.io.File
    //   102	1	1	localException1	Exception
    //   139	261	1	localObject1	Object
    //   403	1	1	localException2	Exception
    //   407	1	1	localObject2	Object
    //   1	349	2	localObject3	Object
    //   3	234	3	localObject4	Object
    //   240	2	3	localException3	Exception
    //   249	110	3	localObject5	Object
    //   405	1	3	localException4	Exception
    //   50	197	4	localObject6	Object
    //   14	218	5	localFile2	java.io.File
    //   170	54	6	localFile3	java.io.File
    // Exception table:
    //   from	to	target	type
    //   57	102	102	java/lang/Exception
    //   253	286	102	java/lang/Exception
    //   286	306	102	java/lang/Exception
    //   306	322	102	java/lang/Exception
    //   326	331	102	java/lang/Exception
    //   331	340	102	java/lang/Exception
    //   344	349	102	java/lang/Exception
    //   349	354	102	java/lang/Exception
    //   358	363	102	java/lang/Exception
    //   363	367	102	java/lang/Exception
    //   368	372	102	java/lang/Exception
    //   375	379	102	java/lang/Exception
    //   382	386	102	java/lang/Exception
    //   11	16	240	java/lang/Exception
    //   23	33	240	java/lang/Exception
    //   36	46	240	java/lang/Exception
    //   162	172	240	java/lang/Exception
    //   175	183	240	java/lang/Exception
    //   192	200	240	java/lang/Exception
    //   212	220	240	java/lang/Exception
    //   223	231	240	java/lang/Exception
    //   103	131	403	java/lang/Exception
    //   135	140	403	java/lang/Exception
    //   140	145	403	java/lang/Exception
    //   149	154	403	java/lang/Exception
    //   154	158	403	java/lang/Exception
    //   389	393	403	java/lang/Exception
    //   396	400	403	java/lang/Exception
    //   4	9	405	java/lang/Exception
  }
  
  private void z()
  {
    SapiAccountManager.registerSilentShareListener(new g(this));
    SapiAccountManager.registerReceiveShareListener(new h(this));
  }
  
  public List<ApplicationInfo> a(int paramInt)
  {
    try
    {
      List localList1 = getPackageManager().getInstalledApplications(paramInt);
      List localList2 = localList1;
      if (localList1 == null) {
        localList2 = Collections.EMPTY_LIST;
      }
      return localList2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        Object localObject1 = null;
      }
    }
    finally {}
  }
  
  public void a(com.estrongs.android.pop.app.service.b paramB)
  {
    this.n = paramB;
  }
  
  public void a(j paramJ)
  {
    if (this.i != null) {
      this.i.add(paramJ);
    }
  }
  
  /* Error */
  public void a(q paramQ)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 299	com/estrongs/android/pop/FexApplication:p	Ljava/util/List;
    //   6: astore_2
    //   7: aload_2
    //   8: monitorenter
    //   9: aload_0
    //   10: getfield 299	com/estrongs/android/pop/FexApplication:p	Ljava/util/List;
    //   13: aload_1
    //   14: invokeinterface 732 2 0
    //   19: pop
    //   20: aload_2
    //   21: monitorexit
    //   22: aload_0
    //   23: monitorexit
    //   24: return
    //   25: astore_1
    //   26: aload_2
    //   27: monitorexit
    //   28: aload_1
    //   29: athrow
    //   30: astore_1
    //   31: aload_0
    //   32: monitorexit
    //   33: aload_1
    //   34: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	35	0	this	FexApplication
    //   0	35	1	paramQ	q
    // Exception table:
    //   from	to	target	type
    //   9	22	25	finally
    //   26	28	25	finally
    //   2	9	30	finally
    //   28	30	30	finally
  }
  
  public void a(Runnable paramRunnable)
  {
    this.o.post(paramRunnable);
  }
  
  public void a(String paramString, Object paramObject)
  {
    try
    {
      this.o.post(new f(this, paramString, paramObject));
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void a(boolean paramBoolean)
  {
    boolean bool = this.g;
    this.g = paramBoolean;
    if ((!bool) && (paramBoolean) && (this.i != null))
    {
      Iterator localIterator = this.i.iterator();
      while (localIterator.hasNext()) {
        ((j)localIterator.next()).a();
      }
    }
  }
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(paramContext);
    android.support.multidex.a.a(this);
  }
  
  public List<PackageInfo> b(int paramInt)
  {
    try
    {
      List localList1 = getPackageManager().getInstalledPackages(paramInt);
      List localList2 = localList1;
      if (localList1 == null) {
        localList2 = Collections.EMPTY_LIST;
      }
      return localList2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        Object localObject1 = null;
      }
    }
    finally {}
  }
  
  /* Error */
  public void b(q paramQ)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 299	com/estrongs/android/pop/FexApplication:p	Ljava/util/List;
    //   6: astore_2
    //   7: aload_2
    //   8: monitorenter
    //   9: aload_0
    //   10: getfield 299	com/estrongs/android/pop/FexApplication:p	Ljava/util/List;
    //   13: aload_1
    //   14: invokeinterface 764 2 0
    //   19: pop
    //   20: aload_2
    //   21: monitorexit
    //   22: aload_0
    //   23: monitorexit
    //   24: return
    //   25: astore_1
    //   26: aload_2
    //   27: monitorexit
    //   28: aload_1
    //   29: athrow
    //   30: astore_1
    //   31: aload_0
    //   32: monitorexit
    //   33: aload_1
    //   34: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	35	0	this	FexApplication
    //   0	35	1	paramQ	q
    // Exception table:
    //   from	to	target	type
    //   9	22	25	finally
    //   26	28	25	finally
    //   2	9	30	finally
    //   28	30	30	finally
  }
  
  public void b(boolean paramBoolean)
  {
    this.j = paramBoolean;
  }
  
  public boolean b()
  {
    return this.q;
  }
  
  public void c()
  {
    this.q = true;
    if ((this.r != -1L) && (System.currentTimeMillis() - this.r > 3000L)) {
      new Thread(new e(this)).start();
    }
  }
  
  public void c(boolean paramBoolean)
  {
    this.k = paramBoolean;
  }
  
  public void d()
  {
    this.q = false;
    this.r = System.currentTimeMillis();
  }
  
  public void d(boolean paramBoolean)
  {
    this.l = paramBoolean;
  }
  
  public void e(boolean paramBoolean)
  {
    this.m = paramBoolean;
  }
  
  public void f()
  {
    if (this.i != null) {
      this.i.clear();
    }
  }
  
  public boolean g()
  {
    return this.g;
  }
  
  public PackageManager getPackageManager()
  {
    try
    {
      if (this.h == null) {
        this.h = super.getPackageManager();
      }
      PackageManager localPackageManager = this.h;
      return localPackageManager;
    }
    finally {}
  }
  
  public Resources getResources()
  {
    return com.estrongs.android.pop.esclasses.x.a(super.getResources());
  }
  
  public boolean h()
  {
    return (this.k) && (!this.j);
  }
  
  public boolean i()
  {
    return (this.l) && (!this.j);
  }
  
  public boolean j()
  {
    return (this.m) && (!this.j);
  }
  
  /* Error */
  public void k()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 299	com/estrongs/android/pop/FexApplication:p	Ljava/util/List;
    //   6: astore_1
    //   7: aload_1
    //   8: monitorenter
    //   9: aload_0
    //   10: getfield 299	com/estrongs/android/pop/FexApplication:p	Ljava/util/List;
    //   13: invokeinterface 774 1 0
    //   18: aload_1
    //   19: monitorexit
    //   20: aload_0
    //   21: monitorexit
    //   22: return
    //   23: astore_2
    //   24: aload_1
    //   25: monitorexit
    //   26: aload_2
    //   27: athrow
    //   28: astore_1
    //   29: aload_0
    //   30: monitorexit
    //   31: aload_1
    //   32: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	33	0	this	FexApplication
    //   28	4	1	localObject1	Object
    //   23	4	2	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   9	20	23	finally
    //   24	26	23	finally
    //   2	9	28	finally
    //   26	28	28	finally
  }
  
  public com.estrongs.android.pop.app.service.b l()
  {
    if (this.n == null)
    {
      FileExplorerActivity localFileExplorerActivity = FileExplorerActivity.ab();
      if (localFileExplorerActivity != null) {
        localFileExplorerActivity.l();
      }
    }
    return this.n;
  }
  
  public boolean m()
  {
    if (!this.c) {
      if ((!com.estrongs.android.pop.view.a.a.equalsIgnoreCase("Market")) || ((com.estrongs.android.pop.view.a.b != null) && (!"".equals(com.estrongs.android.pop.view.a.b)))) {
        break label57;
      }
    }
    label57:
    for (this.b = w.a("com.android.vending");; this.b = false)
    {
      this.c = true;
      return this.b;
    }
  }
  
  public com.estrongs.android.ui.theme.ar n()
  {
    return com.estrongs.android.ui.theme.ar.b();
  }
  
  public Context o()
  {
    return this;
  }
  
  public void onCreate()
  {
    super.onCreate();
    f = this;
    Object localObject = a(this, Process.myPid());
    if ("com.estrongs.android.pop:p0".equals(localObject))
    {
      com.estrongs.android.plugmgr.d.a(this);
      localObject = androidx.pluginmgr.a.a(this, false);
    }
    do
    {
      try
      {
        androidx.pluginmgr.env.b localB = ((androidx.pluginmgr.a)localObject).a(com.estrongs.android.plugmgr.d.a(), true);
        if ((localB != null) && (!((androidx.pluginmgr.a)localObject).a(localB, localB.j().activityInfo.name))) {
          com.estrongs.android.plugmgr.d.a(false);
        }
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      if (".esfm".equals(localException))
      {
        b.c();
        v();
        return;
      }
    } while ((":remote".equals(localException)) || (com.android.apps.pros.j.b(this)));
    if ("com.estrongs.android.pop".equals(localException)) {
      t();
    }
    com.b.a.a.a(f);
    A();
    an.a();
    com.estrongs.android.pop.view.a.a(this);
    this.o = new Handler();
    try
    {
      com.estrongs.android.pop.esclasses.i.a(this);
      getResources();
      u();
      com.estrongs.android.util.ar.a().b();
      as.a(true);
      r();
      b.c();
      am.a();
      com.estrongs.android.g.a.f.b();
      com.estrongs.android.ui.d.t.a(this);
      com.estrongs.android.g.f.a(this);
      com.estrongs.android.g.f.a(ai.a().v());
      w();
      bi.b();
      y();
      com.estrongs.android.ui.notification.d.c();
      this.p = new ArrayList();
      ai localAi = ai.a();
      c(localAi.K());
      d(localAi.L());
      e(localAi.M());
      NativeAuth.a();
      NativeAuth.a = true;
      z();
      com.estrongs.android.pop.app.e.a.a().b();
      SplashScreenManager.b().a();
      com.estrongs.android.pop.app.messagebox.d.a().b();
      com.estrongs.android.pop.app.ad.a.a().a(this);
      com.estrongs.android.pop.app.swipe.f.a().a(this);
      registerActivityLifecycleCallbacks(this.e);
      new c(this).start();
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        localThrowable.printStackTrace();
      }
    }
  }
  
  public void onLowMemory()
  {
    super.onLowMemory();
    com.estrongs.android.g.a.f.d();
  }
  
  public void onTerminate()
  {
    super.onTerminate();
    com.estrongs.android.g.a.f.a().destroy();
  }
  
  public void onTrimMemory(int paramInt)
  {
    super.onTrimMemory(paramInt);
    com.estrongs.android.util.n.a("onTrimMemory level = " + paramInt);
    if (paramInt >= 60)
    {
      com.estrongs.android.g.a.f.a().clearMemoryCache();
      System.gc();
    }
  }
  
  public void p()
  {
    ai localAi = ai.a();
    c(localAi.K());
    d(localAi.L());
    e(localAi.M());
    com.estrongs.android.g.f.a(localAi.v());
  }
  
  public boolean q()
  {
    return this.t > 0;
  }
  
  public void sendBroadcast(Intent paramIntent)
  {
    try
    {
      super.sendBroadcast(paramIntent);
      return;
    }
    catch (Exception paramIntent) {}
  }
  
  public void sendBroadcast(Intent paramIntent, String paramString)
  {
    try
    {
      super.sendBroadcast(paramIntent, paramString);
      return;
    }
    catch (Exception paramIntent) {}
  }
}
