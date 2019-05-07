package com.callpod.android_apps.keeper.fastfill;

import aco;
import acx;
import aea;
import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Process;
import android.provider.Settings.Secure;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.inputmethod.InputMethodManager;
import apk;
import arg;
import arn;
import art;
import asd;
import asr;
import ast;
import asw;
import asz;
import atb;
import atc;
import atd;
import ate;
import atf;
import ath;
import ati;
import atj;
import atk;
import atl;
import atm;
import atn;
import ato;
import atp;
import atr;
import awo;
import bky;
import blc;
import blv;
import bms;
import bnc;
import bne;
import bnn;
import com.callpod.android_apps.keeper.KeeperApp;
import com.callpod.android_apps.keeper.fastfill.layouts.FastFillBaseView;
import com.callpod.android_apps.keeper.fastfill.layouts.FastFillEdit;
import com.callpod.android_apps.keeper.fastfill.layouts.FastFillFill;
import com.callpod.android_apps.keeper.fastfill.layouts.FastFillLogin;
import com.callpod.android_apps.keeper.fastfill.proxy.data.GlobalVO;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicBoolean;

@TargetApi(14)
public class MainService
  extends AccessibilityService
{
  private static WeakReference B;
  private static int D;
  private static AlertDialog E;
  private static boolean F;
  private static boolean G;
  private static boolean H;
  private static Timer L;
  public static int a;
  private static final String b = MainService.class.getSimpleName();
  private static MainService c;
  private static boolean d;
  private static String j;
  private static String k;
  private static String l;
  private static String m;
  private static Bitmap n;
  private static Set o;
  private static String p;
  private static String q;
  private static Intent r;
  private static WeakReference t;
  private static WeakReference u;
  private static boolean v;
  private static boolean w;
  private static boolean z;
  private boolean A;
  private boolean C;
  private boolean I;
  private ast J;
  private BroadcastReceiver K;
  private arn e;
  private arn f;
  private arn g;
  private arn h;
  private arn i;
  private BroadcastReceiver s;
  private boolean x;
  private boolean y;
  
  public MainService() {}
  
  public static boolean A()
  {
    return (u != null) && (u.get() != null);
  }
  
  public static String B()
  {
    return q;
  }
  
  private void L()
  {
    IntentFilter localIntentFilter = new IntentFilter("login_action");
    this.K = new atb(this);
    registerReceiver(this.K, localIntentFilter);
  }
  
  private void M()
  {
    D = c.getResources().getConfiguration().screenLayout & 0xF;
    setCreatingInputMethod(false);
    f();
    j = "";
    a = -1;
  }
  
  private static void N()
  {
    if ((t == null) || (t.get() == null))
    {
      t = new WeakReference(new art(t()));
      ((art)t.get()).a(new ati());
    }
  }
  
  private static boolean O()
  {
    boolean bool = bms.a("prefs_fastfill", "shown_snackbar_info");
    if (!bool) {
      bms.a("prefs_fastfill", "shown_snackbar_info", true);
    }
    return bool;
  }
  
  private static void P()
  {
    if (((KeeperApp)t().getApplicationContext()).f()) {
      return;
    }
    d();
    if (A()) {
      ((asw)u.get()).d();
    }
    u = new WeakReference(new asw(c));
    w = true;
    if (bne.c())
    {
      ((asw)u.get()).a();
      return;
    }
    ((asw)u.get()).b();
  }
  
  private static void Q()
  {
    Object localObject = ((LayoutInflater)new ContextThemeWrapper(t(), 2131296387).getSystemService("layout_inflater")).inflate(2130903120, null);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(t(), 2131296387);
    localBuilder.setView((View)localObject).setCancelable(false).setPositiveButton(2131165416, new atj());
    localObject = localBuilder.create();
    if (Build.VERSION.SDK_INT > 19) {
      ((AlertDialog)localObject).getWindow().setBackgroundDrawable(new ColorDrawable(-1));
    }
    ((AlertDialog)localObject).getWindow().setType(2003);
    ((AlertDialog)localObject).show();
  }
  
  private void R()
  {
    if (bne.f()) {
      return;
    }
    this.e = new arn(new Handler(), this, arn.e);
    getContentResolver().registerContentObserver(arn.e, true, this.e);
    this.f = new arn(new Handler(), this, arn.c);
    getContentResolver().registerContentObserver(arn.c, true, this.f);
    this.g = new arn(new Handler(), this, arn.b);
    getContentResolver().registerContentObserver(arn.b, true, this.g);
    this.h = new arn(new Handler(), this, arn.a);
    getContentResolver().registerContentObserver(arn.a, true, this.h);
    this.i = new arn(new Handler(), this, arn.d);
    getContentResolver().registerContentObserver(arn.d, true, this.i);
  }
  
  private void S()
  {
    if ((!aea.a.f()) && (!aea.a.d()))
    {
      localBlv = blv.a;
      if (blv.f.get())
      {
        if (FastFillInputMethodService.i() == null) {
          d();
        }
        if (FastFillInputMethodService.i() != null)
        {
          localBlv = blv.a;
          if (blv.f.getAndSet(false)) {
            new FastFillLogin(FastFillInputMethodService.i()).m();
          }
        }
      }
    }
    do
    {
      do
      {
        return;
      } while (!aea.a.f());
      localBlv = blv.a;
    } while (!blv.f.get());
    blv localBlv = blv.a;
    blv.f.set(false);
    aea.a.a(false);
  }
  
  private void T()
  {
    new ato(this).execute(new Void[0]);
  }
  
  private boolean U()
  {
    if (TextUtils.isEmpty(l)) {}
    while ((c == null) || (l.contains(c.getString(2131166096))) || (!l.startsWith("https"))) {
      return false;
    }
    return true;
  }
  
  private static boolean V()
  {
    return (t != null) && (t.get() != null);
  }
  
  private void W()
  {
    new Handler().postDelayed(new atc(this), 50L);
  }
  
  private boolean X()
  {
    if (aea.a.e())
    {
      aea.a.b(false);
      q = "";
    }
    if (TextUtils.isEmpty(q)) {
      if (n().equals(c(p))) {}
    }
    while ((TextUtils.isEmpty(p)) || (!q.equals(p)))
    {
      return true;
      return false;
    }
    return false;
  }
  
  private void Y()
  {
    if ((!asz.a()) && (!asz.b(c)) && (!H)) {
      Z();
    }
  }
  
  private void Z()
  {
    new Handler().postDelayed(new atd(this), 50L);
  }
  
  private static void a(int paramInt)
  {
    if (!apk.b(t())) {
      return;
    }
    if (L != null)
    {
      L.cancel();
      L.purge();
    }
    L = new Timer();
    atf localAtf = new atf(new Handler());
    L.schedule(localAtf, paramInt * 1000);
  }
  
  public static void a(Context paramContext)
  {
    if (paramContext == null) {}
    while ((E != null) && (E.isShowing())) {
      return;
    }
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setMessage(paramContext.getString(2131165629));
    localBuilder.setPositiveButton(paramContext.getString(2131165416), new atk());
    E = localBuilder.create();
    E.getWindow().setType(2003);
    E.show();
  }
  
  private void a(AccessibilityEvent paramAccessibilityEvent, atr paramAtr)
  {
    if (paramAccessibilityEvent == null) {}
    label154:
    do
    {
      do
      {
        return;
        if (a(paramAtr)) {
          k();
        }
        if (!c(paramAccessibilityEvent, paramAtr)) {
          break label154;
        }
        if ((!asz.a()) && (!acx.d()))
        {
          Y();
          j();
          return;
        }
        if (!b(paramAccessibilityEvent, paramAtr)) {
          break;
        }
      } while ((FastFillSnackbar.c()) || (!asz.b(c)));
      H = true;
      paramAccessibilityEvent = FastFillSnackbar.a(c, 2131165699, 0).a(2131165698, new atn(this));
      B = new WeakReference(paramAccessibilityEvent);
      paramAccessibilityEvent.a();
      a(300);
      return;
      if ((this.I) && (!acx.d()))
      {
        ((art)t.get()).a();
        this.I = false;
      }
      i();
      return;
    } while (h(paramAccessibilityEvent));
    j();
  }
  
  private void a(GlobalVO paramGlobalVO)
  {
    if (paramGlobalVO != null)
    {
      if ((paramGlobalVO.a() != null) && (paramGlobalVO.a().length > 0))
      {
        bnn.a.f(new String(paramGlobalVO.a()));
        paramGlobalVO.f();
        aea.a.a(true);
        aea.a.c();
        if (aea.a.f())
        {
          blv localBlv = blv.a;
          blv.f.compareAndSet(false, true);
        }
      }
      if (!bnc.e(paramGlobalVO.c())) {
        blv.a.c(paramGlobalVO.c());
      }
      if ((bnn.a.h()) && (!bnc.e(paramGlobalVO.d()))) {
        blv.a.a(paramGlobalVO.d(), paramGlobalVO.e());
      }
    }
  }
  
  public static boolean a()
  {
    return z;
  }
  
  private static boolean a(int paramInt, String paramString)
  {
    return (c != null) && (!TextUtils.isEmpty(paramString)) && (paramString.equals(c.getString(paramInt)));
  }
  
  @SuppressLint({"NewApi"})
  private boolean a(AccessibilityEvent paramAccessibilityEvent, String paramString)
  {
    if (v) {
      return false;
    }
    if (paramAccessibilityEvent.getSource() == null) {
      return false;
    }
    if (a()) {
      return false;
    }
    if ((f(paramAccessibilityEvent)) && (A())) {
      ((asw)u.get()).d();
    }
    if ((paramAccessibilityEvent == null) || (TextUtils.isEmpty(paramString))) {
      return false;
    }
    if ((!k(paramString)) || (!k(paramAccessibilityEvent.getPackageName().toString())))
    {
      j();
      k();
      y();
      return false;
    }
    paramString = (KeyguardManager)getSystemService("keyguard");
    if ((bne.c()) && (paramString.isKeyguardLocked())) {
      return false;
    }
    if (!g(paramAccessibilityEvent)) {
      return false;
    }
    j(paramAccessibilityEvent);
    if ((c(paramAccessibilityEvent)) && (!bne.f()))
    {
      j();
      if (this.C) {
        f();
      }
      y();
      return false;
    }
    return true;
  }
  
  private boolean a(atr paramAtr)
  {
    return (apk.a(c)) && (acx.d()) && (!paramAtr.i());
  }
  
  private void aa()
  {
    FastFillBaseView localFastFillBaseView = FastFillInputMethodService.j();
    if ((localFastFillBaseView instanceof FastFillFill))
    {
      ((FastFillFill)localFastFillBaseView).setSelectionForNode();
      ((FastFillFill)localFastFillBaseView).r();
    }
    while (!(localFastFillBaseView instanceof FastFillEdit)) {
      return;
    }
    ((FastFillEdit)localFastFillBaseView).setSelectionForNode(a);
  }
  
  private static void ab()
  {
    if (!aea.a.d()) {
      return;
    }
    Object localObject = t();
    AlarmManager localAlarmManager = (AlarmManager)((Context)localObject).getSystemService("alarm");
    Intent localIntent = new Intent("login_action");
    localIntent.setPackage(t().getApplicationContext().getPackageName());
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("login_info", awo.e());
    localIntent.putExtra("extra", localBundle);
    localObject = PendingIntent.getBroadcast((Context)localObject, 0, localIntent, 0);
    localAlarmManager.set(1, System.currentTimeMillis() + 6000L, (PendingIntent)localObject);
    Log.i(b, "Set alarm");
  }
  
  private static void ac()
  {
    Process.killProcess(Process.myPid());
  }
  
  @TargetApi(23)
  private static void ad()
  {
    if (c == null) {
      return;
    }
    NotificationCompat.Builder localBuilder = new NotificationCompat.Builder(c).setContentTitle(c.getString(2131166074)).setContentText(c.getString(2131165649)).setSmallIcon(2130837622).setLargeIcon(BitmapFactory.decodeResource(c.getResources(), 2130837587)).setAutoCancel(true).setStyle(new NotificationCompat.BigTextStyle().bigText(c.getString(2131165649))).setDefaults(1).setPriority(1);
    Intent localIntent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + c.getPackageName()));
    localIntent.setFlags(268435456);
    localBuilder.setContentIntent(PendingIntent.getActivity(c.getApplicationContext(), (int)System.currentTimeMillis(), localIntent, 1073741824));
    ((NotificationManager)c.getSystemService("notification")).notify(999, localBuilder.build());
  }
  
  private boolean ae()
  {
    return (V()) && (((art)t.get()).f());
  }
  
  private static boolean af()
  {
    String str1 = Settings.Secure.getString(c.getContentResolver(), "default_input_method");
    if (TextUtils.isEmpty(str1)) {}
    String str2;
    do
    {
      return false;
      str2 = c.getPackageName() + "/";
    } while ((!str1.startsWith(str2)) || (str1.length() == str2.length()));
    str1 = str1.substring(str1.lastIndexOf("/") + 1);
    return FastFillInputMethodService.class.getName().contains(str1);
  }
  
  private static boolean ag()
  {
    return (B != null) && (B.get() != null) && (FastFillSnackbar.c());
  }
  
  private static boolean ah()
  {
    if (F) {
      return G;
    }
    if (c == null) {
      return false;
    }
    if (c.getPackageManager() == null) {
      return false;
    }
    Object localObject = c.getPackageManager().getInstalledPackages(8);
    if (localObject == null) {
      return false;
    }
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ProviderInfo[] arrayOfProviderInfo = ((PackageInfo)((Iterator)localObject).next()).providers;
      if (arrayOfProviderInfo != null)
      {
        int i2 = arrayOfProviderInfo.length;
        int i1 = 0;
        while (i1 < i2)
        {
          ProviderInfo localProviderInfo = arrayOfProviderInfo[i1];
          if ((!TextUtils.isEmpty(localProviderInfo.authority)) && (localProviderInfo.authority.contains("asusbrowser")))
          {
            F = true;
            G = true;
            return true;
          }
          i1 += 1;
        }
      }
    }
    F = true;
    return G;
  }
  
  private static void ai()
  {
    FastFillBaseView localFastFillBaseView = FastFillInputMethodService.j();
    if (localFastFillBaseView == null) {
      return;
    }
    localFastFillBaseView.g();
  }
  
  public static String b(AccessibilityEvent paramAccessibilityEvent)
  {
    if ((paramAccessibilityEvent == null) || (TextUtils.isEmpty(paramAccessibilityEvent.getPackageName()))) {
      return "";
    }
    return paramAccessibilityEvent.getPackageName().toString();
  }
  
  public static void b()
  {
    Object localObject = new AlertDialog.Builder(KeeperApp.a());
    ((AlertDialog.Builder)localObject).setTitle(2131165972);
    ((AlertDialog.Builder)localObject).setMessage(2131165321);
    ((AlertDialog.Builder)localObject).setPositiveButton(2131165416, new ath());
    localObject = ((AlertDialog.Builder)localObject).create();
    ((AlertDialog)localObject).getWindow().setType(2003);
    ((AlertDialog)localObject).show();
  }
  
  private boolean b(AccessibilityEvent paramAccessibilityEvent, atr paramAtr)
  {
    return (apk.a(c)) && (acx.d()) && (l(paramAccessibilityEvent.getPackageName().toString())) && (paramAtr.i()) && (!h(paramAccessibilityEvent));
  }
  
  public static boolean b(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    while ((c == null) || ((!paramString.equals(c.getString(2131166085))) && (!paramString.equals(c.getString(2131166090))) && (!paramString.equals(c.getString(2131166083))) && (!paramString.equals(c.getString(2131166089))) && (!paramString.equals(c.getString(2131166088))) && (!paramString.equals(c.getString(2131166087))) && (!paramString.equals(c.getString(2131166086))) && (!paramString.equals(c.getString(2131166084))))) {
      return false;
    }
    return true;
  }
  
  public static String c(String paramString)
  {
    String str = paramString;
    if (TextUtils.isEmpty(paramString)) {
      str = "";
    }
    return str.toLowerCase(acx.i);
  }
  
  public static void c()
  {
    aco.b();
    if (acx.d())
    {
      a(t());
      return;
    }
    if (r != null) {
      t().stopService(r);
    }
    if (!O())
    {
      Q();
      return;
    }
    P();
  }
  
  public static boolean c(AccessibilityEvent paramAccessibilityEvent)
  {
    return e(b(paramAccessibilityEvent));
  }
  
  private boolean c(AccessibilityEvent paramAccessibilityEvent, atr paramAtr)
  {
    if (paramAccessibilityEvent.getSource() == null) {}
    do
    {
      return false;
      if ((h(paramAccessibilityEvent)) && (paramAtr.h()))
      {
        if (paramAtr.n()) {
          a = 1;
        }
        while (a != -1)
        {
          aa();
          return true;
          if (paramAtr.o()) {
            a = 0;
          } else if (asr.a(w(), v())) {
            a = 0;
          } else if (asr.b(w(), v())) {
            a = 1;
          } else {
            a = -1;
          }
        }
      }
      if (paramAtr.h()) {
        return true;
      }
    } while (!d(paramAccessibilityEvent));
    if ((!TextUtils.isEmpty(k)) && (k.equals(l))) {
      return this.y;
    }
    this.y = false;
    T();
    return ae();
  }
  
  public static void d()
  {
    r = new Intent(t(), FastFillInputMethodService.class);
    t().startService(r);
  }
  
  public static void d(String paramString)
  {
    if (a(2131166088, paramString)) {
      if (!DolphinBrowserExtension.a()) {}
    }
    while (bne.f()) {
      return;
    }
    if (a(2131166090, paramString))
    {
      arn.a(arn.c, t());
      return;
    }
    if (a(2131166085, paramString))
    {
      arn.a(arn.b, t());
      return;
    }
    if (a(2131166089, paramString))
    {
      if (bne.d())
      {
        arn.a(arn.a, t());
        return;
      }
      arn.a(arn.e, t());
      return;
    }
    if (a(2131166083, paramString))
    {
      if (ah())
      {
        arn.a(arn.d, t());
        return;
      }
      arn.a(arn.e, t());
      return;
    }
    if ((a(2131166086, paramString)) || (a(2131166087, paramString)))
    {
      arn.a(arn.e, t());
      return;
    }
    r();
  }
  
  private boolean d(AccessibilityEvent paramAccessibilityEvent)
  {
    return (w()) && (U()) && (!e(paramAccessibilityEvent));
  }
  
  public static boolean e()
  {
    int i1 = c.getResources().getConfiguration().screenLayout & 0xF;
    if (D != i1) {}
    for (boolean bool = true;; bool = false)
    {
      if (bool) {
        D = i1;
      }
      return bool;
    }
  }
  
  private boolean e(AccessibilityEvent paramAccessibilityEvent)
  {
    paramAccessibilityEvent = b(paramAccessibilityEvent);
    if (TextUtils.isEmpty(paramAccessibilityEvent)) {}
    while ((c == null) || (paramAccessibilityEvent.equals(c.getString(2131166088))) || (((!bne.d()) || (!paramAccessibilityEvent.equals(c.getString(2131166085)))) && (!bne.e()))) {
      return false;
    }
    return true;
  }
  
  public static boolean e(String paramString)
  {
    if ((w()) && (c(l).contains("keepersecurity"))) {
      return true;
    }
    return f(paramString);
  }
  
  public static void f()
  {
    try
    {
      FastFillInputMethodService localFastFillInputMethodService = FastFillInputMethodService.i();
      if (localFastFillInputMethodService == null)
      {
        j();
        return;
      }
      InputMethodManager localInputMethodManager = (InputMethodManager)localFastFillInputMethodService.getSystemService("input_method");
      IBinder localIBinder = localFastFillInputMethodService.getWindow().getWindow().getAttributes().token;
      if (localIBinder != null)
      {
        localInputMethodManager.switchToLastInputMethod(localIBinder);
        a(300);
      }
      localFastFillInputMethodService.k();
      localFastFillInputMethodService.stopSelf();
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  private boolean f(AccessibilityEvent paramAccessibilityEvent)
  {
    if ((paramAccessibilityEvent == null) || (TextUtils.isEmpty(paramAccessibilityEvent.getPackageName()))) {
      return false;
    }
    if (paramAccessibilityEvent.getPackageName().toString().contentEquals("android")) {
      this.x = true;
    }
    while (!this.x) {
      return false;
    }
    this.x = false;
    return true;
  }
  
  public static boolean f(String paramString)
  {
    return (c != null) && (!TextUtils.isEmpty(paramString)) && (paramString.startsWith(c.getString(2131166104)));
  }
  
  private boolean g(AccessibilityEvent paramAccessibilityEvent)
  {
    return (paramAccessibilityEvent != null) && (!h(paramAccessibilityEvent)) && ((h(paramAccessibilityEvent)) || (i(paramAccessibilityEvent)));
  }
  
  public static void h()
  {
    if (V()) {
      ((art)t.get()).a(aea.a.d());
    }
  }
  
  private boolean h(AccessibilityEvent paramAccessibilityEvent)
  {
    boolean bool = false;
    if (paramAccessibilityEvent == null) {
      return false;
    }
    int i1 = paramAccessibilityEvent.getEventType();
    if ((i1 == 1) || (i1 == 8)) {
      bool = true;
    }
    this.A = bool;
    if (this.A) {
      W();
    }
    return this.A;
  }
  
  public static void i()
  {
    if (acx.d())
    {
      j();
      return;
    }
    aco.b();
    if (!asz.a())
    {
      j();
      return;
    }
    if (af())
    {
      j();
      return;
    }
    N();
    ((art)t.get()).d();
  }
  
  private void i(String paramString)
  {
    Object localObject = paramString;
    if (!paramString.contains("://")) {
      localObject = "https://" + paramString;
    }
    try
    {
      paramString = new URL((String)localObject);
      if (paramString != null) {}
      try
      {
        localObject = new URL(paramString.getProtocol().concat("://").concat(paramString.getHost()).concat("/").concat("favicon.ico"));
        paramString = (String)localObject;
      }
      catch (MalformedURLException localMalformedURLException)
      {
        for (;;)
        {
          localMalformedURLException.printStackTrace();
        }
      }
      new bky(new atl(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new URL[] { paramString });
      return;
    }
    catch (MalformedURLException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
        paramString = null;
      }
    }
  }
  
  private boolean i(AccessibilityEvent paramAccessibilityEvent)
  {
    if (paramAccessibilityEvent == null) {}
    int i1;
    do
    {
      return false;
      i1 = paramAccessibilityEvent.getEventType();
    } while ((i1 != 32) && (i1 != 2048));
    return true;
  }
  
  public static void j()
  {
    if (V())
    {
      if ((t != null) && (t.get() != null))
      {
        ((art)t.get()).b();
        t.clear();
      }
      asd.b();
    }
  }
  
  private void j(AccessibilityEvent paramAccessibilityEvent)
  {
    String str = b(paramAccessibilityEvent);
    if (k(paramAccessibilityEvent)) {}
    do
    {
      return;
      d = b(str);
    } while ((!d) || (a()));
    d(str);
  }
  
  private boolean j(String paramString)
  {
    return (!TextUtils.isEmpty(j)) && (!TextUtils.equals(j, paramString));
  }
  
  public static void k()
  {
    if (ag())
    {
      ((FastFillSnackbar)B.get()).b();
      B.clear();
    }
  }
  
  private boolean k(AccessibilityEvent paramAccessibilityEvent)
  {
    if (paramAccessibilityEvent == null) {}
    String str;
    do
    {
      return false;
      str = b(paramAccessibilityEvent);
    } while ((TextUtils.isEmpty(str)) || (((!str.contentEquals("android")) || (paramAccessibilityEvent.isFullScreen())) && (!f(str))));
    return true;
  }
  
  private boolean k(String paramString)
  {
    if ((paramString.equals(getApplicationContext().getPackageName())) || (paramString.endsWith(".launcher")) || (paramString.toLowerCase(acx.i).matches("com.*.camera")) || (paramString.contains("com.android.system")) || (paramString.contains("com.android.settings")) || (paramString.contains("com.android.cts.stub"))) {}
    while ((c != null) && (Arrays.asList(c.getResources().getStringArray(2131492866)).contains(paramString))) {
      return false;
    }
    return true;
  }
  
  public static boolean l()
  {
    return (!TextUtils.isEmpty(n())) || (!TextUtils.isEmpty(s())) || (m().size() >= 1);
  }
  
  private boolean l(String paramString)
  {
    return (c == null) || (!Arrays.asList(c.getResources().getStringArray(2131492867)).contains(paramString));
  }
  
  public static Set m()
  {
    return o;
  }
  
  public static String n()
  {
    Object localObject2 = q;
    Object localObject1 = localObject2;
    if (TextUtils.isEmpty(l))
    {
      localObject1 = localObject2;
      if (TextUtils.isEmpty((CharSequence)localObject2)) {
        localObject1 = s().replace(" ", "");
      }
    }
    localObject2 = localObject1;
    if (TextUtils.isEmpty((CharSequence)localObject1)) {
      localObject2 = "";
    }
    return ((String)localObject2).toLowerCase(acx.i);
  }
  
  public static String o()
  {
    Object localObject = "";
    if (c == null) {}
    String str;
    do
    {
      do
      {
        return localObject;
        if ((TextUtils.isEmpty(l)) && (TextUtils.isEmpty(q))) {
          break;
        }
        str = q;
        localObject = str;
      } while (!TextUtils.isEmpty(str));
      return "";
      str = s();
      localObject = str;
    } while (!TextUtils.isEmpty(str));
    return "";
  }
  
  public static String p()
  {
    if ((w()) && (!TextUtils.isEmpty(m))) {
      return m;
    }
    if ((w()) && (TextUtils.isEmpty(m)) && (!TextUtils.isEmpty(o()))) {
      return o();
    }
    return s();
  }
  
  public static Bitmap q()
  {
    Object localObject;
    if (w()) {
      localObject = n;
    }
    for (;;)
    {
      return localObject;
      try
      {
        Bitmap localBitmap = ((BitmapDrawable)c.getPackageManager().getApplicationIcon(u())).getBitmap();
        localObject = localBitmap;
        if (localBitmap != null) {}
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;) {}
      }
    }
    return null;
  }
  
  public static void r()
  {
    if (!bne.f()) {
      setBrowserDetails("", "", null);
    }
  }
  
  public static String s()
  {
    Object localObject1;
    if (c == null) {
      localObject1 = "";
    }
    for (;;)
    {
      return localObject1;
      Object localObject2 = c.getPackageManager();
      try
      {
        localObject1 = ((PackageManager)localObject2).getApplicationInfo(j, 0);
        if (localObject1 != null)
        {
          localObject1 = ((PackageManager)localObject2).getApplicationLabel((ApplicationInfo)localObject1);
          localObject2 = (String)localObject1;
          localObject1 = localObject2;
          if (!TextUtils.isEmpty((CharSequence)localObject2)) {
            continue;
          }
          return "";
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;)
        {
          String str = null;
          continue;
          str = "";
        }
      }
    }
  }
  
  public static void setBrowserDetails(String paramString1, String paramString2, Bitmap paramBitmap)
  {
    String str = paramString2;
    if (paramString2 != null)
    {
      str = paramString2;
      if (paramString2.contains("|")) {
        str = paramString2.replace("|", "");
      }
    }
    setBrowserTitle(paramString1);
    setBrowserUrl(str);
    setBrowserFavicon(paramBitmap);
    ai();
  }
  
  public static void setBrowserFavicon(Bitmap paramBitmap)
  {
    n = paramBitmap;
  }
  
  public static void setBrowserTitle(String paramString)
  {
    m = paramString;
  }
  
  public static void setBrowserUrl(String paramString)
  {
    l = paramString;
  }
  
  public static void setCreatingInputMethod(boolean paramBoolean)
  {
    z = paramBoolean;
  }
  
  public static void setDomainUrl(String paramString)
  {
    q = paramString;
  }
  
  public static void setScreenState(boolean paramBoolean)
  {
    v = paramBoolean;
  }
  
  public static MainService t()
  {
    return c;
  }
  
  public static String u()
  {
    return j;
  }
  
  public static String v()
  {
    return l;
  }
  
  public static boolean w()
  {
    return d;
  }
  
  public static boolean x()
  {
    return e(j);
  }
  
  public static void y()
  {
    if (A()) {
      ((asw)u.get()).d();
    }
  }
  
  public static String[] z()
  {
    ArrayList localArrayList = new ArrayList();
    if (c != null) {
      localArrayList.add(c.getString(2131166094));
    }
    return (String[])localArrayList.toArray(new String[localArrayList.size()]);
  }
  
  protected atr a(AccessibilityEvent paramAccessibilityEvent)
  {
    atp localAtp = new atp(c, w(), l);
    localAtp.a(new ate(this));
    paramAccessibilityEvent = localAtp.a(paramAccessibilityEvent);
    paramAccessibilityEvent.m();
    return paramAccessibilityEvent;
  }
  
  public void a(AccessibilityEvent paramAccessibilityEvent, String paramString, boolean paramBoolean)
  {
    if (h(paramAccessibilityEvent)) {
      if (X())
      {
        if (V()) {
          ((art)t.get()).g();
        }
        k();
        p = q;
        if (!paramBoolean)
        {
          FastFillInputMethodService.p();
          d(paramString);
        }
        H = false;
      }
    }
    while (!i(paramAccessibilityEvent)) {
      return;
    }
    if (X())
    {
      if (V()) {
        ((art)t.get()).g();
      }
      if (!paramBoolean) {
        FastFillInputMethodService.p();
      }
      p = q;
      H = false;
    }
    d(paramString);
    aco.b();
  }
  
  final void a(String paramString)
  {
    if ((TextUtils.isEmpty(j)) || (!j.equals(paramString))) {
      j = paramString;
    }
  }
  
  public final void g()
  {
    if (V())
    {
      ((art)t.get()).b();
      t.clear();
    }
    if (r != null) {
      stopService(r);
    }
    stopForeground(true);
    stopSelf();
  }
  
  public void onAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    Object localObject = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localObject != null) && (((NetworkInfo)localObject).isConnected()) && (((NetworkInfo)localObject).isAvailable())) {}
    localObject = AccessibilityEvent.obtain(paramAccessibilityEvent);
    this.C = ((AccessibilityEvent)localObject).isFullScreen();
    String str = b(paramAccessibilityEvent);
    if (!a((AccessibilityEvent)localObject, str)) {}
    atr localAtr;
    do
    {
      return;
      localAtr = a(paramAccessibilityEvent);
      if ((bne.f()) && (localAtr.q() != null))
      {
        setBrowserUrl(localAtr.q());
        i(localAtr.q());
        ai();
      }
      S();
    } while (k((AccessibilityEvent)localObject));
    a(paramAccessibilityEvent, localAtr);
    if (j(str))
    {
      f();
      j();
      if (V()) {
        ((art)t.get()).g();
      }
    }
    if (e()) {
      M();
    }
    a(str);
    if ((w()) && (!TextUtils.isEmpty(l)))
    {
      q = ast.b(l);
      o = new HashSet();
      o.add(q);
      a((AccessibilityEvent)localObject, str, false);
      return;
    }
    if ((this.J != null) && (this.J.getStatus() == AsyncTask.Status.RUNNING)) {
      this.J.cancel(true);
    }
    this.J = new ast(c, str, new atm(this, str, (AccessibilityEvent)localObject));
    this.J.execute(new Void[0]);
  }
  
  public void onCreate()
  {
    super.onCreate();
    blv.a.h(true);
    if (!asz.d(this))
    {
      stopSelf();
      return;
    }
    c = this;
    o = new HashSet();
    if (arg.a().equals(arg.c))
    {
      getTheme();
      setTheme(2131296606);
    }
    for (;;)
    {
      L();
      M();
      R();
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.intent.action.SCREEN_ON");
      localIntentFilter.addAction("android.intent.action.SCREEN_OFF");
      this.s = new ScreenReceiver();
      registerReceiver(this.s, localIntentFilter);
      return;
      blc.a(c);
    }
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    if (this.s != null) {
      unregisterReceiver(this.s);
    }
    if (V())
    {
      ((art)t.get()).b();
      t.clear();
    }
    if (bne.f()) {}
    do
    {
      return;
      if (this.e != null) {
        getContentResolver().unregisterContentObserver(this.e);
      }
      if (this.f != null) {
        getContentResolver().unregisterContentObserver(this.f);
      }
      if (this.g != null) {
        getContentResolver().unregisterContentObserver(this.g);
      }
      if (this.h != null) {
        getContentResolver().unregisterContentObserver(this.h);
      }
    } while (this.i == null);
    getContentResolver().unregisterContentObserver(this.i);
  }
  
  public void onInterrupt()
  {
    if (V())
    {
      if ((t != null) && (t.get() != null))
      {
        ((art)t.get()).b();
        t.clear();
      }
      j();
    }
  }
  
  public boolean onUnbind(Intent paramIntent)
  {
    super.onUnbind(paramIntent);
    ac();
    return false;
  }
}
