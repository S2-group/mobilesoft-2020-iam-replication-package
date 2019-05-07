package org.hola;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.util.Patterns;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class svc
  extends Service
{
  public static final Map a = new HashMap();
  private static boolean t = false;
  final List b = new LinkedList();
  final Messenger c = new Messenger(new dc(this));
  private do d = null;
  private int e = 0;
  private boolean f = false;
  private boolean g = false;
  private final ReentrantLock h = new ReentrantLock();
  private Timer i;
  private Timer j;
  private HandlerThread k;
  private BroadcastReceiver l;
  private boolean m = false;
  private SharedPreferences.OnSharedPreferenceChangeListener n = new cz(this);
  private SharedPreferences o;
  private boolean p = false;
  private String q;
  private String r;
  private df s;
  
  static
  {
    a.put("com.pandora.android", "Pandora");
    a.put("com.netflix.mediaclient", "Netflix");
    a.put("com.rhythmnewmedia.tvdotcom", "TV.com");
    a.put("air.ITVMobilePlayer", "ITV");
    a.put("com.facebook.katana", "Facebook");
    a.put("com.vimeo.android.videoapp", "Vimeo");
    a.put("com.nbcuni.oxygen.oxygenlive", "Oxygen");
    a.put("com.twitter.android", "Twitter");
    a.put("com.spotify.mobile.android.ui", "Spotify");
  }
  
  public svc() {}
  
  private static int a(String paramString, int paramInt)
  {
    long l1 = util.n();
    int i1 = 0;
    int i2;
    for (;;)
    {
      i2 = i1;
      if (util.i("libhola_svc.so") <= 0) {
        break;
      }
      i1 = (int)(util.n() - l1);
      i2 = i1;
      if (i1 >= paramInt) {
        break;
      }
      util.a(10);
    }
    return i2;
  }
  
  private int a(String paramString1, String paramString2)
  {
    return util.a(this, paramString1, paramString2);
  }
  
  public static String a(Context paramContext)
  {
    String str = f(paramContext);
    paramContext = util.f(paramContext);
    if (str != null) {}
    for (boolean bool = true;; bool = false)
    {
      util.a(paramContext, "env_workdir_passed", Boolean.valueOf(bool));
      return str;
    }
  }
  
  private String a(Context paramContext, int paramInt)
  {
    return paramContext.getString(paramInt);
  }
  
  private void a()
  {
    widget.a(this, this.o.getBoolean("svc_up", false));
  }
  
  private void a(int paramInt)
  {
    if (this.i != null) {
      this.i.cancel();
    }
    this.i = new Timer();
    cw localCw = new cw(this);
    this.i.schedule(localCw, paramInt);
  }
  
  private void a(Message paramMessage)
  {
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext())
    {
      Messenger localMessenger = (Messenger)localIterator.next();
      try
      {
        paramMessage.replyTo = this.c;
        localMessenger.send(paramMessage);
      }
      catch (RemoteException localRemoteException)
      {
        b(3, "UI failed to process message: " + localRemoteException.toString());
      }
    }
  }
  
  public static boolean a(Context paramContext, ServiceConnection paramServiceConnection)
  {
    b(5, "bind svc");
    boolean bool = paramContext.bindService(new Intent(paramContext, svc.class), paramServiceConnection, 0);
    if (!bool) {
      util.a(paramContext, "svc_bind_fail", "failed binding svc");
    }
    return bool;
  }
  
  private static boolean a(String paramString)
  {
    boolean bool = false;
    try
    {
      paramString = File.createTempFile("hola", "tmp", new File(paramString));
      if (paramString != null)
      {
        paramString.delete();
        bool = true;
      }
      return bool;
    }
    catch (IOException paramString) {}
    return false;
  }
  
  private static int b(int paramInt, String paramString)
  {
    return util.a("svc", paramInt, paramString);
  }
  
  private void b()
  {
    if (!this.o.getBoolean("svc_up", false))
    {
      this.h.lock();
      if (this.f)
      {
        this.e = 0;
        a(0);
      }
      for (;;)
      {
        this.h.unlock();
        this.e = 0;
        return;
        this.g = false;
      }
    }
    boolean bool = this.o.getBoolean("battery", false);
    b(7, "notify battery " + bool + " retries " + this.e);
    Object localObject = util.a();
    localObject.getClass();
    util.ipc_result localIpc_result = new util.ipc_result((util)localObject);
    if (bool) {}
    for (localObject = "1";; localObject = "0")
    {
      util localUtil = util.a();
      localUtil.getClass();
      localObject = new cx(this, localUtil, new String[] { "notify_battery", localObject }, localIpc_result, bool, localIpc_result);
      if (this.d == null) {
        a("battery_ipc_thread_null", "battery m_ipc_thread is null");
      }
      this.d.a((dn)localObject);
      return;
    }
  }
  
  public static void b(Context paramContext)
  {
    b(5, "start svc");
    if (paramContext.startService(new Intent(paramContext, svc.class)) == null) {
      util.a(paramContext, "svc_start_fail", "failed starting svc");
    }
  }
  
  private void b(String paramString)
  {
    if (util.a(this.r + "/libiptables.so " + paramString) == 127) {
      util.a("/system/bin/iptables " + paramString);
    }
  }
  
  private void b(boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("notify_apk_set ");
    if (paramBoolean) {}
    for (String str = "on";; str = "off")
    {
      b(7, str);
      if (app.a) {
        break;
      }
      return;
    }
    if (this.j != null) {
      this.j.cancel();
    }
    if (!paramBoolean)
    {
      util.c(getApplicationContext(), "hola_notify_apk");
      this.j = null;
      return;
    }
    this.j = new Timer();
    this.j.schedule(new cy(this), 0L, 3000L);
  }
  
  private static int c(boolean paramBoolean)
  {
    int i2 = 3;
    util.a("libhola_svc.so", paramBoolean);
    long l1 = util.n();
    int i3 = a("libhola_svc.so", 3000);
    int i1 = i3;
    if (i3 >= 3000)
    {
      util.a("libhola_svc.so", 9, paramBoolean);
      i1 = i3 + a("libhola_svc.so", 3000);
    }
    if (i1 >= 6000) {
      return b(3, "failed to kill svc");
    }
    if (i1 > 1000) {}
    for (i1 = i2;; i1 = 7)
    {
      b(i1, "took " + (util.n() - l1) + "ms to kill svc");
      return 0;
    }
  }
  
  public static String c(Context paramContext)
  {
    Pattern localPattern = Patterns.EMAIL_ADDRESS;
    Account[] arrayOfAccount = AccountManager.get(paramContext).getAccounts();
    int i2 = arrayOfAccount.length;
    paramContext = "";
    int i1 = 0;
    while (i1 < i2)
    {
      Account localAccount = arrayOfAccount[i1];
      String str = localAccount.name.toLowerCase();
      Object localObject = paramContext;
      if (localPattern.matcher(localAccount.name).matches()) {
        localObject = paramContext + " " + localAccount.type + ":" + str;
      }
      i1 += 1;
      paramContext = (Context)localObject;
    }
    return paramContext.trim();
  }
  
  private String c(String paramString)
  {
    String str = util.b(this.r + "/libiptables.so " + paramString);
    if ((str != null) && (!str.equals(""))) {
      return str;
    }
    return util.b("/system/bin/iptables " + paramString);
  }
  
  private void c()
  {
    this.h.lock();
    if (this.g)
    {
      this.f = true;
      this.h.unlock();
      return;
    }
    this.g = true;
    this.h.unlock();
    this.e = 0;
    b();
  }
  
  public static int d(Context paramContext)
  {
    Object localObject2 = paramContext.getPackageManager().getInstalledApplications(128);
    Object localObject1 = new HashMap();
    localObject2 = ((List)localObject2).iterator();
    Object localObject3;
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (ApplicationInfo)((Iterator)localObject2).next();
      if (((ApplicationInfo)localObject3).uid != 0) {
        if (((ApplicationInfo)localObject3).packageName.isEmpty())
        {
          if (!t) {
            util.a(paramContext, "pkg_name_empty", "uid " + ((ApplicationInfo)localObject3).uid);
          }
          t = true;
        }
        else if (((Map)localObject1).containsKey(Integer.valueOf(((ApplicationInfo)localObject3).uid)))
        {
          ((Map)localObject1).put(Integer.valueOf(((ApplicationInfo)localObject3).uid), (String)((Map)localObject1).get(Integer.valueOf(((ApplicationInfo)localObject3).uid)) + " " + ((ApplicationInfo)localObject3).packageName);
        }
        else
        {
          ((Map)localObject1).put(Integer.valueOf(((ApplicationInfo)localObject3).uid), ((ApplicationInfo)localObject3).packageName);
        }
      }
    }
    localObject2 = new StringBuilder();
    localObject1 = ((Map)localObject1).entrySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject3 = (Map.Entry)((Iterator)localObject1).next();
      ((StringBuilder)localObject2).append(((Map.Entry)localObject3).getKey() + ": " + (String)((Map.Entry)localObject3).getValue() + "\n");
    }
    paramContext = util.d(paramContext) + "/db";
    util.h(paramContext);
    if (util.b(paramContext + "/uid2apk.id", ((StringBuilder)localObject2).toString()) < 0) {
      return b(3, "failed creating uid2apk.id with uid to app_name table");
    }
    return 0;
  }
  
  private void d()
  {
    boolean bool = false;
    if (this.l == null)
    {
      localObject = new IntentFilter("android.intent.action.ACTION_POWER_CONNECTED");
      ((IntentFilter)localObject).addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
      ((IntentFilter)localObject).addAction("android.intent.action.ACTION_SHUTDOWN");
      ((IntentFilter)localObject).addAction("android.intent.action.SCREEN_ON");
      ((IntentFilter)localObject).addAction("android.intent.action.SCREEN_OFF");
      this.l = new bcast_recv();
      registerReceiver(this.l, (IntentFilter)localObject);
    }
    Object localObject = registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    if (localObject == null) {
      a("battery_init_status", "registerReceiver no battery sticky intent");
    }
    for (int i1 = 0;; i1 = ((Intent)localObject).getIntExtra("plugged", -1))
    {
      localObject = (PowerManager)getSystemService("power");
      util.a(this.o, "screen", Boolean.valueOf(false));
      util.a(this.o, "screen", Boolean.valueOf(((PowerManager)localObject).isScreenOn()));
      localObject = util.a();
      localObject.getClass();
      this.d = new do((util)localObject, getApplicationContext());
      this.d.a();
      localObject = this.o;
      if (i1 == 0) {
        bool = true;
      }
      util.a((SharedPreferences)localObject, "battery", Boolean.valueOf(bool));
      return;
      ((Intent)localObject).getIntExtra("plugged", -1);
    }
  }
  
  private void e()
  {
    boolean bool1 = this.o.getBoolean("svc_up", false);
    boolean bool2 = this.o.getBoolean("env_check_passed", false);
    boolean bool3 = this.o.getBoolean("enabled", false);
    b(7, "state update enabled " + bool3 + " svc_up " + bool1 + " env_passed " + bool2);
    if ((bool3) && (!bool1) && ((bool2) || (this.o.getString("run_mode", "").equals("peer")))) {
      this.s.sendMessage(this.s.obtainMessage(1));
    }
    while ((bool3) || (!bool1)) {
      return;
    }
    b(5, "svc stop");
    new db(this).start();
  }
  
  public static void e(Context paramContext)
  {
    c(util.f(paramContext).getBoolean("svc_run_as_root", false));
  }
  
  private int f()
  {
    return c(this.p);
  }
  
  private static String f(Context paramContext)
  {
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      localObject = paramContext.getExternalCacheDir();
      if ((localObject != null) && (a(((File)localObject).toString()))) {
        return ((File)localObject).toString();
      }
      b(3, "no free space on external storage");
    }
    paramContext = paramContext.getCacheDir();
    if (paramContext == null)
    {
      b(3, "failed to get cache dir");
      return null;
    }
    paramContext = paramContext.toString();
    Object localObject = new StatFs(paramContext);
    if (((StatFs)localObject).getBlockSize() * ((StatFs)localObject).getBlockCount() < 1073741824L)
    {
      b(3, "less than 1GB in internal memory: " + ((StatFs)localObject).getBlockSize() * ((StatFs)localObject).getBlockCount() + " block " + ((StatFs)localObject).getBlockSize() + " count " + ((StatFs)localObject).getBlockCount());
      return null;
    }
    if (a(paramContext)) {}
    for (;;)
    {
      return paramContext;
      paramContext = null;
    }
  }
  
  private static int g(Context paramContext)
  {
    Object localObject = c(paramContext);
    String str = util.d(paramContext) + "/db";
    paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    util.h(str);
    str = str + "/hola.id";
    StringBuilder localStringBuilder = new StringBuilder().append("os_id: ").append(paramContext);
    if (((String)localObject).equals(""))
    {
      paramContext = "";
      localObject = localStringBuilder.append(paramContext);
      if (util.e() != null) {
        break label161;
      }
    }
    label161:
    for (paramContext = "";; paramContext = "\nimei: " + util.e())
    {
      if (util.c(str, paramContext) >= 0) {
        break label187;
      }
      return b(3, "failed creating hola.id with android ids");
      paramContext = "\nemail: " + (String)localObject;
      break;
    }
    label187:
    return 0;
  }
  
  private void g()
  {
    String[] arrayOfString1 = c("-t nat -nL --line-numbers").split("\\n");
    Object localObject1 = "";
    int i2 = 0;
    int i3 = 0;
    if (i2 < arrayOfString1.length)
    {
      String[] arrayOfString2 = arrayOfString1[i2].split("\\s+");
      int i1;
      Object localObject2;
      if (arrayOfString2.length < 2)
      {
        i1 = i3;
        localObject2 = localObject1;
      }
      for (;;)
      {
        i2 += 1;
        localObject1 = localObject2;
        i3 = i1;
        break;
        if (arrayOfString2[0].equals("Chain"))
        {
          localObject2 = arrayOfString2[1];
          i1 = 0;
        }
        else if (arrayOfString2[1].indexOf("HOLA_") < 0)
        {
          localObject2 = localObject1;
          i1 = i3;
          if (((String)localObject1).indexOf("HOLA_") < 0) {}
        }
        else
        {
          int i4 = util.k(arrayOfString2[0]);
          localObject2 = localObject1;
          i1 = i3;
          if (i4 != 0)
          {
            b("-t nat -D " + (String)localObject1 + " " + (i4 - i3));
            i1 = i3 + 1;
            localObject2 = localObject1;
          }
        }
      }
    }
    b("-t nat -X HOLA_OUTPUT");
    b("-t nat -X HOLA_PREROUTING");
  }
  
  private boolean h()
  {
    boolean bool2 = false;
    String[] arrayOfString1 = c("-t nat -nL --line-numbers").split("\\n");
    Object localObject1 = "";
    int i1 = 0;
    boolean bool1 = bool2;
    String[] arrayOfString2;
    Object localObject2;
    label91:
    int i2;
    if (i1 < arrayOfString1.length)
    {
      arrayOfString2 = arrayOfString1[i1].split("\\s+");
      if (arrayOfString2.length < 2) {
        localObject2 = localObject1;
      }
      do
      {
        do
        {
          do
          {
            for (;;)
            {
              i1 += 1;
              localObject1 = localObject2;
              break;
              if (!arrayOfString2[0].equals("Chain")) {
                break label91;
              }
              localObject2 = arrayOfString2[1];
            }
            localObject2 = localObject1;
          } while (arrayOfString2[1].indexOf("HOLA_") >= 0);
          localObject2 = localObject1;
        } while (((String)localObject1).indexOf("HOLA_") >= 0);
        localObject2 = localObject1;
      } while (util.k(arrayOfString2[0]) == 0);
      i2 = 6;
    }
    for (;;)
    {
      localObject2 = localObject1;
      if (i2 >= arrayOfString2.length) {
        break;
      }
      if (arrayOfString2[i2].equals("dpt:80"))
      {
        bool1 = true;
        return bool1;
      }
      i2 += 1;
    }
  }
  
  private void i()
  {
    f();
    if (this.p) {
      g();
    }
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return this.c.getBinder();
  }
  
  public void onCreate()
  {
    this.o = util.f(this);
    util.a(this, a(this));
    b(7, "started");
    this.q = util.d(this);
    this.r = util.c(this);
    this.k = new HandlerThread("svc_prefs_notify");
    this.k.start();
    this.k.getLooper();
    this.o.registerOnSharedPreferenceChangeListener(this.n);
    d();
    d(this);
    if (g(this) < 0) {
      b(3, "create_hola_id failed");
    }
    HandlerThread localHandlerThread = new HandlerThread("svc_mon", -2);
    localHandlerThread.start();
    this.s = new df(this, localHandlerThread.getLooper());
    e();
  }
  
  public void onDestroy()
  {
    if (this.l != null) {
      unregisterReceiver(this.l);
    }
    this.l = null;
    this.o.unregisterOnSharedPreferenceChangeListener(this.n);
    if (!this.k.quit()) {
      b(3, "svc prefs notify thread quit failed");
    }
    try
    {
      this.k.join();
      if (this.i != null) {
        this.i.cancel();
      }
      this.i = null;
      b(false);
      this.d.b();
      this.d = null;
      b(7, "shutdown");
      util.f();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        b(3, "svc prefs notify thread interrupt failed " + localInterruptedException);
      }
    }
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    b(7, "svc onstart");
    return 1;
  }
}
