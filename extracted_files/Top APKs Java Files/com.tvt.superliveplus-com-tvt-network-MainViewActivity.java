package com.tvt.network;

import abv;
import af.c;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.Notification.Builder;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Process;
import android.os.StrictMode;
import android.os.StrictMode.VmPolicy.Builder;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.KeyEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsoluteLayout;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tvt.fcm.MyFirebaseReceiver;
import com.tvt.fcm.MyFirebaseReceiver.a;
import com.tvt.feedback.BurialPointUtil;
import com.tvt.other.ExitAppliation;
import com.tvt.other.MyDownloadService;
import com.tvt.push.PushMessageService;
import com.tvt.push.PushMessageService.a;
import com.tvt.skin.RoundProgressBar;
import dt;
import dt.a;
import du;
import dv;
import dv.a;
import ed;
import eg;
import ff;
import fg;
import fg.a;
import fi;
import fi.a;
import fl;
import fl.b;
import fw;
import ge;
import gx;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import kv;
import kx;
import ld;
import lm;
import lw;
import ns;
import nt;
import nu;
import op;
import or;
import os;
import ot;
import ou;
import ov;
import ox;
import oy;
import pw;
import pz;
import qa;
import qa.a;
import sb;
import si;
import sr.a;
import sy;
import uc;
import ud;
import ue;
import uf;
import ug;
import uh;
import ul;
import un;
import un.a;
import ur;
import ur.a;
import uu;
import uw;
import ux;
import va;
import vb;
import ve;
import ve.a;
import vf;
import vi;
import vj;
import vn;
import vo;
import vo.c;
import vp;
import vr;
import vt;
import vv;
import vw;
import vw.a;
import wa;
import wc;
import wd;
import wf;
import wg;
import wi;
import wn;
import wx;
import xb;
import xl;
import xm;
import yi;
import yi.a;
import ym.a;
import yq;
import yt;
import zj;
import zl;
import zo;
import zr;

@SuppressLint({"NewApi"})
public class MainViewActivity
  extends Activity
  implements ns, vn, xb
{
  private int A = 0;
  private uc B = null;
  private int C = 0;
  private int D = 0;
  private boolean E = false;
  private AppStatusRecevier F;
  private Intent G = null;
  private MyFirebaseReceiver H = null;
  private uw I = null;
  private un J = null;
  private PushMessageService K = null;
  private boolean L = false;
  private vt M = null;
  private int N = 0;
  private vp O = null;
  private String P = null;
  private vv Q = null;
  private boolean R = false;
  private boolean S = false;
  private boolean T = false;
  private Timer U;
  private TimerTask V;
  private Handler W = new ur(new ur.a()
  {
    public void a(Message paramAnonymousMessage)
    {
      int i = paramAnonymousMessage.what;
      switch (i)
      {
      default: 
        switch (i)
        {
        default: 
          
        case 4108: 
          ld.c("----MESSAGE_ID_FACE_IMAGE_GET_TIMEOUT-----获取数据超时--------", new Object[0]);
          if (MainViewActivity.f(MainViewActivity.this) != null) {
            MainViewActivity.f(MainViewActivity.this).e();
          }
          MainViewActivity.this.a(MainViewActivity.this, MainViewActivity.this.getResources().getString(2131493900));
          return;
        case 4107: 
          if (MainViewActivity.e(MainViewActivity.this) != null)
          {
            MainViewActivity.e(MainViewActivity.this).a(paramAnonymousMessage.arg1, (String)paramAnonymousMessage.obj);
            return;
          }
          break;
        case 4106: 
          paramAnonymousMessage = (Bundle)paramAnonymousMessage.obj;
          if (MainViewActivity.d(MainViewActivity.this) != null)
          {
            MainViewActivity.d(MainViewActivity.this).e(paramAnonymousMessage.getInt("mobilePushState"));
            return;
          }
          break;
        case 4105: 
          if (MainViewActivity.d(MainViewActivity.this) != null)
          {
            MainViewActivity.d(MainViewActivity.this).j();
            MainViewActivity.d(MainViewActivity.this).n();
            return;
          }
          break;
        case 4104: 
          if (MainViewActivity.c(MainViewActivity.this) != null)
          {
            MainViewActivity.c(MainViewActivity.this).d(paramAnonymousMessage.arg1);
            return;
          }
          break;
        case 4103: 
          i = paramAnonymousMessage.arg1;
          paramAnonymousMessage = (String)paramAnonymousMessage.obj;
          Object localObject = new yi.a(1000, 2131099666);
          MainViewActivity localMainViewActivity = MainViewActivity.this;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("P2P connect failed.DeviceNo:");
          localStringBuilder.append(paramAnonymousMessage);
          localStringBuilder.append(" Error Code:");
          localStringBuilder.append(i);
          paramAnonymousMessage = yi.a(localMainViewActivity, localStringBuilder.toString(), (yi.a)localObject);
          paramAnonymousMessage.a(Integer.MAX_VALUE);
          localObject = new FrameLayout.LayoutParams(-2, -2);
          ((FrameLayout.LayoutParams)localObject).bottomMargin = (wx.w * ot.c / 1136);
          ((FrameLayout.LayoutParams)localObject).gravity = 81;
          paramAnonymousMessage.a((ViewGroup.LayoutParams)localObject);
          paramAnonymousMessage.a();
          return;
        case 4102: 
          MainViewActivity.this.a(MainViewActivity.this, MainViewActivity.this.getResources().getString(2131493748));
          return;
        case 4101: 
          if (MainViewActivity.b(MainViewActivity.this) != null)
          {
            MainViewActivity.b(MainViewActivity.this).setVisibility(4);
            return;
          }
          break;
        case 4100: 
          MainViewActivity.this.z();
          return;
        case 4099: 
          MainViewActivity.a(MainViewActivity.this);
          return;
        case 4098: 
          paramAnonymousMessage = (String)paramAnonymousMessage.obj;
          return;
        case 4097: 
          MainViewActivity.this.u();
        }
        break;
      }
    }
  });
  private ImageView X = null;
  private TextView Y = null;
  private int Z;
  public ArrayList<wi> a = null;
  private op aa;
  private final BroadcastReceiver ab = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      MainViewActivity.this.n();
    }
  };
  private OrientationEventListener ac = null;
  private boolean ad = false;
  private boolean ae = false;
  private boolean af = true;
  private boolean ag = true;
  private int ah = 0;
  private int ai = 0;
  private fi aj;
  private boolean ak = false;
  private zr al = null;
  private wn am = null;
  private RoundProgressBar an = null;
  private TextView ao = null;
  private Timer ap = null;
  private TimerTask aq = null;
  private ServiceConnection ar = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      ld.c("operation--->PushMessageService conncet", new Object[0]);
      paramAnonymousComponentName = (PushMessageService.a)paramAnonymousIBinder;
      MainViewActivity.a(MainViewActivity.this, paramAnonymousComponentName.a());
      MainViewActivity.k(MainViewActivity.this).a(MainViewActivity.this);
      MainViewActivity.this.a(MainViewActivity.v(MainViewActivity.this));
      if (MainViewActivity.w(MainViewActivity.this)) {
        MainViewActivity.this.a(ot.ar.b());
      }
      MainViewActivity.k(MainViewActivity.this).o();
      MainViewActivity.k(MainViewActivity.this).a(0L, true);
      MainViewActivity.x(MainViewActivity.this);
      MainViewActivity.f(MainViewActivity.this, false);
    }
    
    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      ld.c("operation--->PushMessageService disconnect", new Object[0]);
    }
  };
  private a as = new a(null);
  private ve at = null;
  private va au;
  public ArrayList<wi> b = null;
  public Animation c = null;
  public Animation d = null;
  public boolean e = true;
  public int f = 0;
  yi.a g;
  yi h = null;
  private NotificationManager i;
  private AbsoluteLayout j = null;
  private ov k = null;
  private pz l = null;
  private pw m = null;
  private lw n = null;
  private ge o = null;
  private nt p = null;
  private sb q = null;
  private lm r = null;
  private ox s = null;
  private wc t = null;
  private wa u = null;
  private vr v = null;
  private wn w = null;
  private os x = null;
  private AudioManager y = null;
  private int z = 0;
  
  public MainViewActivity() {}
  
  public static String N()
  {
    return TimeZone.getDefault().getDisplayName(false, 0);
  }
  
  private void R()
  {
    if (!f(true))
    {
      this.j.removeView(this.X);
      this.j.removeView(this.Y);
      c();
    }
    D();
    Intent localIntent = new Intent(getApplicationContext(), PushMessageService.class);
    uu.a(this, localIntent);
    bindService(localIntent, this.ar, 1);
    this.R = true;
  }
  
  private void S()
  {
    Intent localIntent = getIntent();
    if (localIntent != null)
    {
      try
      {
        localObject2 = localIntent.getStringExtra("AlarmType");
        if ((localObject2 != null) && (!TextUtils.isEmpty((CharSequence)localObject2)) && (((String)localObject2).equals("DevAlarm"))) {
          this.O = ((vp)new Gson().fromJson(localIntent.getStringExtra("AlarmData"), vp.class));
        }
      }
      catch (JsonSyntaxException localJsonSyntaxException)
      {
        ld.b(localJsonSyntaxException.toString(), new Object[0]);
      }
      if (this.O != null)
      {
        this.P = this.O.a;
        if (this.O.i == null) {
          this.O = null;
        }
      }
    }
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(getResources().getString(2131494025));
    ((StringBuilder)localObject1).append(ot.an);
    localObject1 = getSharedPreferences(((StringBuilder)localObject1).toString(), 0);
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("FirstEnterApp");
    ((StringBuilder)localObject2).append(T());
    boolean bool = ((SharedPreferences)localObject1).getString(((StringBuilder)localObject2).toString(), "true").equals("true");
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(ot.w);
      ((StringBuilder)localObject1).append("/");
      ((StringBuilder)localObject1).append(ot.y);
      ((StringBuilder)localObject1).append("/logsys/crash/");
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    else
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(getFilesDir().getAbsolutePath());
      ((StringBuilder)localObject1).append("/");
      ((StringBuilder)localObject1).append(ot.y);
      ((StringBuilder)localObject1).append("/logsys/crash/");
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    localObject1 = new File((String)localObject1);
    if (bool) {
      Y();
    }
    if ((!bool) && (((File)localObject1).listFiles() != null) && (((File)localObject1).listFiles().length > 0))
    {
      U();
      v();
    }
    else
    {
      this.Q = vv.a();
      this.Q.a(0, 0L, new vi()
      {
        public void a(int paramAnonymousInt)
        {
          MainViewActivity.g(MainViewActivity.this).sendEmptyMessageDelayed(4099, 2000L);
          MainViewActivity.h(MainViewActivity.this);
          MainViewActivity.i(MainViewActivity.this);
        }
      });
    }
    this.J = new un(this, new un.a()
    {
      public void a(float[] paramAnonymousArrayOfFloat)
      {
        if ((MainViewActivity.j(MainViewActivity.this) != null) || (paramAnonymousArrayOfFloat != null))
        {
          if (MainViewActivity.this.getResources().getConfiguration().orientation == 2) {
            SensorManager.remapCoordinateSystem(paramAnonymousArrayOfFloat, 2, 129, paramAnonymousArrayOfFloat);
          }
          paramAnonymousArrayOfFloat[0] = (-paramAnonymousArrayOfFloat[0]);
          paramAnonymousArrayOfFloat[1] = (-paramAnonymousArrayOfFloat[1]);
          paramAnonymousArrayOfFloat[4] = (-paramAnonymousArrayOfFloat[4]);
          paramAnonymousArrayOfFloat[5] = (-paramAnonymousArrayOfFloat[5]);
          paramAnonymousArrayOfFloat[8] = (-paramAnonymousArrayOfFloat[8]);
          paramAnonymousArrayOfFloat[9] = (-paramAnonymousArrayOfFloat[9]);
          MainViewActivity.j(MainViewActivity.this).a(paramAnonymousArrayOfFloat);
        }
      }
    });
    this.G = new Intent(this, AppStatusService.class);
    startService(this.G);
    this.F = new AppStatusRecevier(this, new oy()
    {
      public void a()
      {
        MainViewActivity.this.n();
      }
    });
    this.F.a();
    W();
    localObject1 = new dt.a().a(2131099885).b(2131100246).a(true).b(false).a(Bitmap.Config.RGB_565).a(ed.e).a();
    localObject1 = new dv.a(this).a(eg.a).a((dt)localObject1).a(240, 240).a();
    du.a().a((dv)localObject1);
    vj.a.a(this);
    ag();
  }
  
  private String T()
  {
    try
    {
      String str = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return "";
  }
  
  private void U()
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(getResources().getString(2131494025));
    ((StringBuilder)localObject1).append(ot.an);
    localObject1 = ((StringBuilder)localObject1).toString();
    boolean bool = false;
    localObject1 = getSharedPreferences((String)localObject1, 0);
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("FirstInstallApp");
    ((StringBuilder)localObject2).append(T());
    ot.bu = ((SharedPreferences)localObject1).getBoolean(((StringBuilder)localObject2).toString(), true);
    ExitAppliation.a().a(this);
    ot.b(this);
    ot.f();
    ot.s = getApplication().getFilesDir().getAbsolutePath();
    ot.u = ot.s;
    ot.E = ot.b();
    ot.as.a(this);
    if (ot.as.a().equals(""))
    {
      ot.t = ot.s;
      ot.v = ot.s;
      ot.w = ot.s;
    }
    else
    {
      ot.v = ot.as.a();
      ot.w = ot.as.a();
      ot.t = ot.as.a();
    }
    ot.ar.q();
    ot.ar.p();
    ot.ar.w();
    localObject2 = getSharedPreferences(getString(2131494025), 0);
    try
    {
      ot.L = ((SharedPreferences)localObject2).getBoolean("CodeStreamType", false);
    }
    catch (Exception localException)
    {
      if (((SharedPreferences)localObject2).getInt("CodeStreamType", 0) == 1) {
        bool = true;
      }
      ot.L = bool;
      localObject2 = ((SharedPreferences)localObject2).edit();
      ((SharedPreferences.Editor)localObject2).putBoolean("CodeStreamType", ot.L);
      ((SharedPreferences.Editor)localObject2).commit();
      localException.printStackTrace();
    }
    if (this.ad)
    {
      ot.h = ot.a(this, ot.b * 20 / 1136.0F);
      ot.i = ot.a(this, ot.b * 24 / 1136.0F);
      ot.j = ot.a(this, ot.b * 22 / 1136.0F);
      ot.k = ot.a(this, ot.b * 28 / 1136.0F);
      ot.l = ot.a(this, ot.b * 30 / 1136.0F);
      ot.m = ot.a(this, ot.b * 32 / 1136.0F);
      ot.n = ot.a(this, ot.b * 34 / 1136.0F);
      ot.o = ot.a(this, ot.b * 38 / 1136.0F);
      ot.p = ot.a(this, ot.b * 26 / 1136.0F);
      ot.r = ot.a(this, ot.b * 36 / 1136.0F);
      ot.q = ot.a(this, ot.b * 42 / 1136.0F);
    }
    else
    {
      ot.h = ot.a(this, ot.c * 20 / 1136.0F);
      ot.i = ot.a(this, ot.c * 24 / 1136.0F);
      ot.j = ot.a(this, ot.c * 22 / 1136.0F);
      ot.k = ot.a(this, ot.c * 28 / 1136.0F);
      ot.l = ot.a(this, ot.c * 30 / 1136.0F);
      ot.m = ot.a(this, ot.c * 32 / 1136.0F);
      ot.n = ot.a(this, ot.c * 34 / 1136.0F);
      ot.o = ot.a(this, ot.c * 38 / 1136.0F);
      ot.p = ot.a(this, ot.c * 26 / 1136.0F);
      ot.r = ot.a(this, ot.c * 36 / 1136.0F);
      ot.q = ot.a(this, ot.c * 42 / 1136.0F);
    }
    if (ot.bx) {
      so.a.a = 72000;
    }
    BurialPointUtil.getInstance().init(this);
  }
  
  private int V()
  {
    ot.ar.c(false);
    ot.ar.l();
    int i1 = ot.ar.v();
    ue.a.a();
    if ((i1 > 32) || (!ot.ag))
    {
      ue.a.b();
      ot.ar.e(false);
    }
    Object localObject = ot.ar.A();
    if (localObject == null) {
      ot.ar.B();
    } else if (i1 == 0) {
      ot.ar.a((ux)localObject);
    }
    BurialPointUtil.getInstance().sendInfoEventDevNumber(i1);
    ot.ar.n();
    ot.ar.o();
    ot.ar.a(this);
    ot.ar.a(false);
    ot.ar.k();
    ot.a(this);
    ot.E = ot.b();
    this.i = ((NotificationManager)getSystemService("notification"));
    this.i.cancelAll();
    if (Build.VERSION.SDK_INT >= 26)
    {
      localObject = new NotificationChannel("1001", getResources().getString(2131493793), 3);
      ((NotificationChannel)localObject).enableLights(true);
      ((NotificationChannel)localObject).setLightColor(-16711936);
      ((NotificationChannel)localObject).setShowBadge(true);
      ((NotificationChannel)localObject).setSound(null, null);
      ((NotificationChannel)localObject).enableVibration(false);
      this.i.createNotificationChannel((NotificationChannel)localObject);
    }
    this.u = new wa(this);
    localObject = b(this);
    if (localObject != null)
    {
      localObject = ((String)localObject).split(":");
      ot.aD[0] = ((byte)Integer.parseInt(localObject[0], 16));
      ot.aD[1] = ((byte)Integer.parseInt(localObject[1], 16));
      ot.aD[2] = ((byte)Integer.parseInt(localObject[2], 16));
      ot.aD[3] = ((byte)Integer.parseInt(localObject[3], 16));
      ot.aD[4] = ((byte)Integer.parseInt(localObject[4], 16));
      ot.aD[5] = ((byte)Integer.parseInt(localObject[5], 16));
    }
    this.y = ((AudioManager)getSystemService("audio"));
    this.z = this.y.getStreamMaxVolume(3);
    this.A = this.y.getStreamVolume(3);
    this.B = new uc(this);
    this.C = 255;
    this.D = this.B.b();
    this.E = this.B.a();
    ae();
    t();
    yq.a(new yt(getApplicationContext()), new ym.a().a(0L).b(0L).a());
    gx.a().a(getApplicationContext());
    localObject = gx.a().a(getString(2131494025), Locale.getDefault());
    ot.bz = gx.a().a((String)localObject);
    ot.i(this);
    this.H = new MyFirebaseReceiver(this, new MyFirebaseReceiver.a()
    {
      public void a(String paramAnonymousString)
      {
        if (MainViewActivity.k(MainViewActivity.this) != null) {
          MainViewActivity.k(MainViewActivity.this).c(ot.h(), paramAnonymousString);
        }
      }
      
      public void a(vp paramAnonymousVp)
      {
        if ((MainViewActivity.k(MainViewActivity.this) != null) && (paramAnonymousVp != null)) {
          MainViewActivity.k(MainViewActivity.this).a(paramAnonymousVp);
        }
      }
      
      public void a(vw paramAnonymousVw)
      {
        if (MainViewActivity.k(MainViewActivity.this) != null) {
          MainViewActivity.k(MainViewActivity.this).a(paramAnonymousVw);
        }
      }
    });
    if (getIntent() != null)
    {
      localObject = getIntent();
      String str = ((Intent)localObject).getStringExtra("AlarmType");
      if ((str != null) && (str.equals("ADMsg")))
      {
        localObject = (vw)new Gson().fromJson(((Intent)localObject).getStringExtra("AlarmData"), vw.class);
        if ((ot.h().equals("fcm")) && (localObject != null)) {
          fg.a.a(((vw)localObject).h);
        }
      }
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Softwarename:");
    ((StringBuilder)localObject).append(ot.y);
    ((StringBuilder)localObject).append(";versionName:");
    ((StringBuilder)localObject).append(ot.an);
    ((StringBuilder)localObject).append(";Phone model:");
    ((StringBuilder)localObject).append(Build.MODEL);
    ((StringBuilder)localObject).append(";phone vesion:");
    ((StringBuilder)localObject).append(ot.ak);
    ((StringBuilder)localObject).append(";Sim Operator:");
    ((StringBuilder)localObject).append(ot.by);
    ((StringBuilder)localObject).append(";MemoryNum:");
    ((StringBuilder)localObject).append(ot.i());
    ((StringBuilder)localObject).append(";Cpucore:");
    ((StringBuilder)localObject).append(ot.j());
    ((StringBuilder)localObject).append(";MobileDevNetConnType");
    ((StringBuilder)localObject).append(ot.g());
    ld.c(((StringBuilder)localObject).toString(), new Object[0]);
    return 0;
  }
  
  private void W()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.SCREEN_OFF");
    registerReceiver(this.ab, localIntentFilter);
  }
  
  private void X()
  {
    if (ot.bx) {
      return;
    }
    this.ac = new OrientationEventListener(this)
    {
      public void onOrientationChanged(int paramAnonymousInt)
      {
        if (paramAnonymousInt < 0) {
          return;
        }
        if ((paramAnonymousInt >= 10) && ((170 > paramAnonymousInt) || (paramAnonymousInt >= 190)) && (paramAnonymousInt < 350))
        {
          if (((80 <= paramAnonymousInt) && (paramAnonymousInt < 100)) || ((260 <= paramAnonymousInt) && (paramAnonymousInt < 280)))
          {
            if (MainViewActivity.m(MainViewActivity.this))
            {
              if ((!MainViewActivity.n(MainViewActivity.this)) && (!MainViewActivity.p(MainViewActivity.this))) {
                return;
              }
              MainViewActivity.d(MainViewActivity.this, true);
              MainViewActivity.b(MainViewActivity.this, false);
              MainViewActivity.c(MainViewActivity.this, true);
              return;
            }
            if (!MainViewActivity.n(MainViewActivity.this))
            {
              if (!ot.Z) {
                MainViewActivity.this.a(6);
              } else {
                MainViewActivity.this.a(7);
              }
              MainViewActivity.c(MainViewActivity.this, true);
              MainViewActivity.b(MainViewActivity.this, false);
            }
          }
        }
        else
        {
          if (MainViewActivity.m(MainViewActivity.this))
          {
            if ((MainViewActivity.n(MainViewActivity.this)) && (!MainViewActivity.o(MainViewActivity.this))) {
              return;
            }
            MainViewActivity.a(MainViewActivity.this, true);
            MainViewActivity.b(MainViewActivity.this, false);
            MainViewActivity.c(MainViewActivity.this, false);
            return;
          }
          if (MainViewActivity.n(MainViewActivity.this))
          {
            if (!ot.Z) {
              MainViewActivity.this.a(7);
            } else {
              MainViewActivity.this.a(6);
            }
            MainViewActivity.c(MainViewActivity.this, false);
            MainViewActivity.b(MainViewActivity.this, false);
          }
        }
      }
    };
  }
  
  private void Y()
  {
    Object localObject;
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(ot.w);
      ((StringBuilder)localObject).append("/");
      ((StringBuilder)localObject).append(ot.y);
      ((StringBuilder)localObject).append("/logsys/crash/");
      localObject = ((StringBuilder)localObject).toString();
    }
    else
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(getFilesDir().getAbsolutePath());
      ((StringBuilder)localObject).append("/");
      ((StringBuilder)localObject).append(ot.y);
      ((StringBuilder)localObject).append("/logsys/crash/");
      localObject = ((StringBuilder)localObject).toString();
    }
    ot.g((String)localObject);
  }
  
  private void Z()
  {
    if (this.ap != null)
    {
      this.ap.cancel();
      this.ap = null;
    }
  }
  
  private void a(final Bundle paramBundle)
  {
    if (Build.VERSION.SDK_INT >= 28)
    {
      final View localView = getWindow().getDecorView();
      localView.post(new Runnable()
      {
        public void run()
        {
          Object localObject = localView.getRootWindowInsets();
          if (localObject != null)
          {
            localObject = ((WindowInsets)localObject).getDisplayCutout();
            if (localObject != null)
            {
              localObject = ((DisplayCutout)localObject).getBoundingRects();
              if ((localObject != null) && (((List)localObject).size() > 0)) {
                MainViewActivity.this.f = MainViewActivity.this.a();
              }
            }
          }
          MainViewActivity.a(MainViewActivity.this, paramBundle);
        }
      });
      return;
    }
    if (ug.a(this)) {
      this.f = a();
    }
    b(paramBundle);
  }
  
  private void a(ff paramFf, final boolean paramBoolean)
  {
    int i1 = ot.b;
    int i2 = ot.c;
    if (getResources().getConfiguration().orientation == 2)
    {
      i1 = ot.c;
      i2 = ot.b;
    }
    if (getResources().getConfiguration().orientation == 2) {
      a(1);
    }
    if ((this.aj != null) && (this.aj.getVisibility() == 0))
    {
      this.aj.a();
      this.aj.removeAllViews();
      this.j.removeView(this.aj);
      this.aj = null;
    }
    this.aj = new fi(this, this);
    this.aj.setCallback(new fi.a()
    {
      public void a()
      {
        MainViewActivity.g(MainViewActivity.this).post(new Runnable()
        {
          public void run()
          {
            MainViewActivity.q(MainViewActivity.this).removeAllViews();
            MainViewActivity.r(MainViewActivity.this).removeView(MainViewActivity.q(MainViewActivity.this));
            MainViewActivity.a(MainViewActivity.this, null);
            if (MainViewActivity.6.this.a) {
              MainViewActivity.this.c();
            }
          }
        });
      }
    });
    this.j.addView(this.aj, new AbsoluteLayout.LayoutParams(i1, i2, 0, 0));
    this.aj.a(this.j, paramFf.b, paramFf.c, paramFf.d, paramFf.e);
    this.aj.a_(paramFf.e);
  }
  
  private void a(final vp paramVp, byte[] paramArrayOfByte, si paramSi)
  {
    if (((this.l != null) && (this.l.getVisibility() == 0)) || ((this.q != null) && (this.q.getVisibility() == 0)) || ((this.aa != null) && (this.aa.getVisibility() == 0)))
    {
      a(1);
      p();
    }
    if ((this.l != null) && (this.l == this.w))
    {
      this.l.aL();
      this.l.aI();
    }
    if ((this.q != null) && (this.q == this.w))
    {
      this.q.I();
      this.q.E();
      this.q.F();
    }
    if (paramSi == null) {
      return;
    }
    int i1;
    if (getResources().getConfiguration().orientation == 1) {
      i1 = 1;
    } else {
      i1 = 0;
    }
    if (i1 == 0)
    {
      ot.c(this);
      if (ot.b > ot.c)
      {
        i1 = ot.c;
        ot.c = ot.b;
        ot.b = i1;
      }
    }
    this.at = new ve(this, this);
    this.at.setLayoutParams(new AbsoluteLayout.LayoutParams(ot.b, ot.c, 0, 0));
    this.j.addView(this.at);
    this.at.a();
    this.at.setCallback(new ve.a()
    {
      public void a()
      {
        MainViewActivity.f(MainViewActivity.this).i();
        MainViewActivity.f(MainViewActivity.this).setCallback(null);
        MainViewActivity.f(MainViewActivity.this).removeAllViews();
        MainViewActivity.a(MainViewActivity.this, null);
        if (((MainViewActivity.z(MainViewActivity.this) != null) && (MainViewActivity.z(MainViewActivity.this).getVisibility() == 0)) || ((MainViewActivity.A(MainViewActivity.this) != null) && (MainViewActivity.A(MainViewActivity.this).getVisibility() == 0)) || ((MainViewActivity.B(MainViewActivity.this) != null) && (MainViewActivity.B(MainViewActivity.this).getVisibility() == 0)))
        {
          MainViewActivity.this.a(4);
          MainViewActivity.this.q();
        }
      }
      
      public void a(int paramAnonymousInt)
      {
        MainViewActivity.this.a(paramAnonymousInt, paramVp);
      }
    });
    this.as.a(ot.a("picId=%s,chlId=%s,frameTime=%s", new Object[] { paramVp.m.get(3), paramVp.m.get(2), paramVp.m.get(5) }));
    this.as.a(paramVp);
    O();
    paramSi.a((String)paramVp.m.get(3), (String)paramVp.m.get(2), (String)paramVp.m.get(5), this.as);
  }
  
  private void a(vw paramVw)
  {
    if (this.K != null) {
      this.K.a(paramVw);
    }
  }
  
  public static boolean a(Context paramContext)
  {
    return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(paramContext) == 0;
  }
  
  private void aa()
  {
    Z();
    this.aq = new TimerTask()
    {
      public void run()
      {
        MainViewActivity.g(MainViewActivity.this).sendEmptyMessage(4101);
      }
    };
    this.ap = new Timer();
    this.ap.schedule(this.aq, 1000L);
  }
  
  private void ab()
  {
    if (this.M == null) {
      return;
    }
    int i1 = ot.b;
    this.M.b(i1, this.M.getWindowHeight());
  }
  
  private Date ac()
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.set(11, 24);
    localCalendar.set(13, 0);
    localCalendar.set(12, 0);
    localCalendar.set(14, 0);
    return localCalendar.getTime();
  }
  
  private void ad()
  {
    if ((this.O != null) && (this.T))
    {
      this.T = false;
      if (this.O.b.equals("ADMsg"))
      {
        f(false);
        return;
      }
      if (this.O.b.equals("DevAlarm"))
      {
        a(this.O.a);
        if (this.O.c.equals("expiredAlarmMsg"))
        {
          vv.a().a(0, 1000L, new vi()
          {
            public void a(int paramAnonymousInt)
            {
              MainViewActivity.g(MainViewActivity.this).post(new Runnable()
              {
                public void run()
                {
                  MainViewActivity.this.b(MainViewActivity.l(MainViewActivity.this).a, "EA");
                  MainViewActivity.a(MainViewActivity.this, null);
                }
              });
            }
          });
          return;
        }
        if (this.O.c.equals("FaceMatch"))
        {
          b(this.O);
          this.O = null;
          return;
        }
        if (this.O.n.equals("PB"))
        {
          c(this.O);
          this.O = null;
          return;
        }
        d(this.O);
        this.O = null;
      }
    }
  }
  
  private void ae()
  {
    String str = ((TelephonyManager)getSystemService("phone")).getSimOperator();
    if (str != null) {
      ot.by = str;
    }
  }
  
  private void af()
  {
    if (this.K != null) {
      this.K.p();
    }
  }
  
  private void ag()
  {
    Object localObject = ot.h();
    if ((localObject == null) || (TextUtils.isEmpty((CharSequence)localObject)))
    {
      N().equals("GMT-8");
      boolean bool = b(this, "com.google.android.gms");
      String str = "tvt";
      localObject = str;
      if (bool)
      {
        localObject = str;
        if (a(this)) {
          localObject = "fcm";
        }
      }
      ot.m((String)localObject);
    }
  }
  
  private void ah()
  {
    if (!new uf().a(this, getPackageName())) {
      throw new RuntimeException("------------包名未授权----------");
    }
  }
  
  private void ai()
  {
    this.au = new va(this, new vb()
    {
      public int a()
      {
        return 0;
      }
      
      public String[] b()
      {
        return new String[] { "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO", "android.permission.CAMERA", "android.permission.READ_PHONE_STATE" };
      }
      
      public void c()
      {
        MainViewActivity.g(MainViewActivity.this).post(new Runnable()
        {
          public void run()
          {
            MainViewActivity.C(MainViewActivity.this);
          }
        });
      }
      
      public void d()
      {
        MainViewActivity.g(MainViewActivity.this).post(new Runnable()
        {
          public void run()
          {
            MainViewActivity.C(MainViewActivity.this);
          }
        });
      }
    });
    this.au.a();
  }
  
  private String b(Context paramContext)
  {
    paramContext = (WifiManager)paramContext.getSystemService("wifi");
    if (paramContext != null) {
      return paramContext.getConnectionInfo().getMacAddress();
    }
    return null;
  }
  
  private void b(Bundle paramBundle)
  {
    if (Build.VERSION.SDK_INT >= 24) {
      StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
    }
    paramBundle = new NatTraveral();
    paramBundle.SetNatLogLevel(ot.bD);
    paramBundle.Destroy();
    ai();
    if (ot.bA == 0L) {
      ot.bA = System.currentTimeMillis();
    }
    ld.a(new kv());
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      paramBundle = new StringBuilder();
      paramBundle.append(ot.w);
      paramBundle.append("/");
      paramBundle.append(ot.y);
      paramBundle.append("/logsys/logger/");
      paramBundle = paramBundle.toString();
    }
    else
    {
      paramBundle = new StringBuilder();
      paramBundle.append(getFilesDir().getAbsolutePath());
      paramBundle.append("/");
      paramBundle.append(ot.y);
      paramBundle.append("/logsys/logger/");
      paramBundle = paramBundle.toString();
    }
    ld.a(new kx(paramBundle));
    paramBundle = new StringBuilder();
    paramBundle.append("performance --->start app time:");
    paramBundle.append(System.currentTimeMillis() - ot.bA);
    ld.c(paramBundle.toString(), new Object[0]);
    ah();
    ul.a().a(this, "tvt");
    if (ot.aH == 0)
    {
      new uf().a(this, getPackageName());
      ot.bl = a();
      ot.ak = Build.VERSION.RELEASE;
      ot.aH = Build.VERSION.SDK_INT;
      if (((ot.aH == 16) && (ot.ak.startsWith("4.1.1"))) || (ot.ak.startsWith("4.1.0"))) {
        ot.aH = 15;
      }
    }
    try
    {
      paramBundle = getPackageManager().getPackageInfo(getPackageName(), 16384);
      ot.an = paramBundle.versionName;
      ot.am = paramBundle.versionCode;
      paramBundle = new StringBuilder();
      paramBundle.append(ot.y);
      paramBundle.append(ot.an);
      paramBundle = getSharedPreferences(paramBundle.toString(), 0);
      this.Z = paramBundle.getInt("displayWidth", 0);
      int i3 = paramBundle.getInt("screenwidth", 0);
      Object localObject;
      int i1;
      int i2;
      if ((this.Z <= 0) && (i3 != 0))
      {
        localObject = getWindowManager().getDefaultDisplay();
        i1 = ((Display)localObject).getWidth();
        i2 = ((Display)localObject).getHeight();
        if (i1 > i2) {
          i1 = i2;
        }
        this.Z = i1;
        localObject = paramBundle.edit();
        ((SharedPreferences.Editor)localObject).putInt("displayWidth", this.Z);
        ((SharedPreferences.Editor)localObject).commit();
      }
      else
      {
        localObject = getWindowManager().getDefaultDisplay();
        i1 = ((Display)localObject).getWidth();
        i2 = ((Display)localObject).getHeight();
        if (i1 > i2) {
          i1 = i2;
        }
        if ((this.Z != i1) || (i3 == 0)) {
          break label1072;
        }
      }
      this.R = false;
      Process.setThreadPriority(-19);
      getWindow().setBackgroundDrawable(null);
      if (ot.bl == 0) {
        ot.bl = a();
      }
      ot.bl -= this.f;
      X();
      ot.c(paramBundle);
      paramBundle = new StringBuilder();
      paramBundle.append(ot.y);
      paramBundle.append("1.4.1");
      boolean bool = getSharedPreferences(paramBundle.toString(), 0).getBoolean("privacyAgreement", ot.F);
      if (bool) {
        ot.F = bool;
      } else {
        ot.F = getSharedPreferences(ot.y, 0).getBoolean("privacyAgreement", ot.F);
      }
      if (ot.bx)
      {
        ot.a(this);
        setRequestedOrientation(0);
        ot.d(this);
        i3 = ot.c * 400 / 640;
        i2 = (ot.b - i3) / 2;
        i1 = ot.b * 120 / 1136;
        this.ad = true;
      }
      else
      {
        setRequestedOrientation(1);
        if (getResources().getConfiguration().orientation == 2) {
          bool = true;
        } else {
          bool = false;
        }
        this.ad = bool;
        if (this.ad) {
          r();
        }
        i3 = ot.b * 400 / 640;
        i2 = ot.b * 120 / 640;
        i1 = ot.c * 237 / 1136;
      }
      this.j = new AbsoluteLayout(this);
      this.j.setBackgroundColor(getResources().getColor(ot.bj));
      this.j.setLayoutParams(new AbsoluteLayout.LayoutParams(ot.b, ot.c, 0, 0));
      setContentView(this.j);
      paramBundle = new or(this);
      paramBundle.setLayoutParams(new AbsoluteLayout.LayoutParams(1, 1, 0, 0));
      paramBundle.d();
      this.j.addView(paramBundle);
      this.w = new wn(this);
      this.X = new ImageView(this);
      this.X.setLayoutParams(new AbsoluteLayout.LayoutParams(ot.b, ot.c, 0, 0));
      if (ot.bx) {
        this.X.setBackgroundResource(2131100382);
      } else {
        this.X.setBackgroundResource(2131100380);
      }
      this.j.addView(this.X);
      this.Y = new TextView(this);
      if (getResources().getString(2131494025).equals("SuperLive Plus"))
      {
        this.Y.setLayoutParams(new AbsoluteLayout.LayoutParams(i3, i3 / 2, i2, i1));
        this.Y.setBackgroundResource(2131100381);
      }
      this.j.addView(this.Y);
      return;
      label1072:
      paramBundle = paramBundle.edit();
      paramBundle.putInt("displayWidth", i1);
      paramBundle.commit();
      startActivity(new Intent(this, MainActivity.class));
      finish();
      onDestroy();
      return;
    }
    catch (PackageManager.NameNotFoundException paramBundle)
    {
      for (;;) {}
    }
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    if (paramContext != null)
    {
      int i1 = 0;
      while (i1 < paramContext.size())
      {
        if (paramString.equals(((PackageInfo)paramContext.get(i1)).packageName)) {
          return true;
        }
        i1 += 1;
      }
    }
    return false;
  }
  
  private boolean c(vp paramVp)
  {
    int i3 = 0;
    int i4 = 0;
    if (paramVp == null) {
      return false;
    }
    uh localUh = ot.ar.a(paramVp.i, paramVp.j);
    if ((localUh != null) && (localUh.S))
    {
      Object localObject = new Date(sy.a());
      try
      {
        Date localDate = ot.k("yyyy-MM-dd HH:mm:ss").parse(sy.a(paramVp.e));
        localObject = localDate;
      }
      catch (ParseException localParseException)
      {
        localParseException.printStackTrace();
      }
      if (((Date)localObject).getTime() > ac().getTime())
      {
        a(this, getResources().getString(2131494015));
        return false;
      }
      if (this.l == this.w)
      {
        this.l.aL();
        this.l.aI();
        this.l.aJ();
        this.l.g(true);
        this.l.l();
      }
      ArrayList localArrayList = new ArrayList();
      int i2;
      int i1;
      if ((!paramVp.c.equals("motion")) && (!paramVp.c.equals("osc")) && (!paramVp.c.equals("avd")) && (!paramVp.c.equals("tripwire")) && (!paramVp.c.equals("pea")) && (!paramVp.c.equals("vfd")) && (!paramVp.c.equals("FaceMatch")))
      {
        int i5 = paramVp.m.size();
        if (i5 > 0)
        {
          i2 = i4;
          i1 = i5;
          if (i5 > 4)
          {
            i1 = 4;
            i2 = i4;
          }
          while (i2 < i1)
          {
            localArrayList.add(Integer.valueOf(localUh.b.a(wg.c((String)paramVp.m.get(i2))) - 1));
            i2 += 1;
          }
        }
        i4 = localUh.G;
        i2 = i3;
        i1 = i4;
        if (i4 > 4)
        {
          i1 = 4;
          i2 = i3;
        }
      }
      while (i2 < i1)
      {
        localArrayList.add(Integer.valueOf(i2));
        i2 += 1;
        continue;
        localArrayList.add(Integer.valueOf(localUh.b.a(wg.c(paramVp.k)) - 1));
      }
      if ((this.q != null) && (this.q.getRemoteState()))
      {
        this.q.I();
        this.q.E();
        this.q.F();
        this.q.g();
        this.q.H();
        this.q.a(true, localUh.j, localUh.i, localArrayList, ((Date)localObject).getTime() - ot.ab * 1000);
        return true;
      }
      a(true, localUh.j, localUh.i, localArrayList, ((Date)localObject).getTime() - ot.ab * 1000, null);
      return true;
    }
    this.W.sendEmptyMessage(4102);
    return false;
  }
  
  private boolean d(vp paramVp)
  {
    if (paramVp == null) {
      return false;
    }
    uh localUh1 = ot.ar.a(paramVp.i, paramVp.j);
    if ((localUh1 != null) && (localUh1.S))
    {
      ArrayList localArrayList = new ArrayList();
      int i1;
      int i2;
      uh localUh2;
      if ((!paramVp.c.equals("motion")) && (!paramVp.c.equals("osc")) && (!paramVp.c.equals("avd")) && (!paramVp.c.equals("tripwire")) && (!paramVp.c.equals("pea")) && (!paramVp.c.equals("FaceMatch")) && (!paramVp.c.equals("vfd")))
      {
        i1 = paramVp.m.size();
        if (paramVp.c.equals("expiredAlarmMsg")) {
          i1 = 0;
        }
        if (i1 > 0)
        {
          i2 = i1;
          if (i1 > 16) {
            i2 = 16;
          }
          i1 = 0;
          while (i1 < i2)
          {
            localUh2 = new uh();
            localUh2.j = localUh1.j;
            localUh2.ad = localUh1.b.a(wg.c((String)paramVp.m.get(i1)));
            localUh2.af = true;
            localUh2.ag = i1;
            localArrayList.add(localUh2);
            i1 += 1;
          }
        }
        i2 = localUh1.G;
        i1 = i2;
        if (i2 > 9) {
          i1 = 9;
        }
        i2 = 0;
      }
      while (i2 < i1)
      {
        paramVp = new uh();
        paramVp.j = localUh1.j;
        int i3 = i2 + 1;
        paramVp.ad = i3;
        paramVp.af = true;
        paramVp.ag = i2;
        localArrayList.add(paramVp);
        i2 = i3;
        continue;
        localUh2 = new uh();
        localUh2.j = localUh1.j;
        localUh2.ad = localUh1.b.a(wg.c(paramVp.k));
        localUh2.af = true;
        localUh2.ag = 0;
        localArrayList.add(localUh2);
      }
      ot.a(localArrayList);
      if (this.q != null)
      {
        this.q.l();
      }
      else if (this.l == this.w)
      {
        this.l.aL();
        this.l.aK();
        this.l.g(false);
        this.l.aI();
      }
      a(localUh1.j, 4);
      return true;
    }
    this.W.sendEmptyMessage(4102);
    return false;
  }
  
  private void e(vp paramVp)
  {
    if ((this.q != null) && (this.q.getVisibility() == 0) && (this.q.getRemoteState()))
    {
      this.q.E();
      this.q.F();
      this.q.l();
    }
    else
    {
      this.l.aI();
      this.l.aJ();
      this.l.g(true);
      this.l.l();
    }
    i();
    this.v.a(paramVp);
  }
  
  private boolean f(final boolean paramBoolean)
  {
    if (this.ak)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("AddAdsArea mNewAdsState = ");
      ((StringBuilder)localObject1).append(String.valueOf(this.ak));
      ld.c(((StringBuilder)localObject1).toString(), new Object[0]);
      this.ak = false;
      return false;
    }
    Object localObject1 = fg.a.d();
    if (localObject1 == null)
    {
      ld.c("adsInfo is null", new Object[0]);
      return false;
    }
    Object localObject2 = new File(((ff)localObject1).c);
    if ((!((File)localObject2).exists()) && (((ff)localObject1).g == 1))
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(((ff)localObject1).c);
      ((StringBuilder)localObject2).append(" is not exist");
      ld.c(((StringBuilder)localObject2).toString(), new Object[0]);
      return false;
    }
    SimpleDateFormat localSimpleDateFormat = ot.k("yyyy-MM-dd HH:mm:ss");
    long l1 = 0L;
    try
    {
      long l2 = localSimpleDateFormat.parse(sy.a(((ff)localObject1).f)).getTime();
      l1 = l2;
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
    if (System.currentTimeMillis() > l1)
    {
      ld.c("current time > valid time", new Object[0]);
      ((File)localObject2).delete();
      return false;
    }
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("image download state = ");
    ((StringBuilder)localObject2).append(String.valueOf(((ff)localObject1).g));
    ld.c(((StringBuilder)localObject2).toString(), new Object[0]);
    if (((ff)localObject1).g != 1)
    {
      if (!fg.a.c())
      {
        localObject1 = ot.j(this);
        int i1 = ot.b;
        int i2 = ot.c;
        if (localObject1 != null)
        {
          i1 = localObject1[0];
          i2 = localObject1[1];
        }
        fg.a.a(fg.a.b(), i1, i2, null);
      }
      zj.a(100L, TimeUnit.MILLISECONDS).a(20L).b(abv.b()).a(zo.a()).a(new zl()
      {
        public void a(Long paramAnonymousLong)
        {
          paramAnonymousLong = fg.a.d();
          if (paramAnonymousLong != null)
          {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("AdsId: ");
            localStringBuilder.append(paramAnonymousLong.a);
            localStringBuilder.append(" --> onNext image download state = ");
            localStringBuilder.append(String.valueOf(paramAnonymousLong.g));
            ld.c(localStringBuilder.toString(), new Object[0]);
          }
          if ((paramAnonymousLong != null) && (paramAnonymousLong.g == 1))
          {
            MainViewActivity.r(MainViewActivity.this).removeView(MainViewActivity.s(MainViewActivity.this));
            MainViewActivity.r(MainViewActivity.this).removeView(MainViewActivity.t(MainViewActivity.this));
            MainViewActivity.u(MainViewActivity.this).a();
            MainViewActivity.a(MainViewActivity.this, paramAnonymousLong, paramBoolean);
          }
        }
        
        public void a(Throwable paramAnonymousThrowable) {}
        
        public void a(zr paramAnonymousZr)
        {
          MainViewActivity.a(MainViewActivity.this, paramAnonymousZr);
        }
        
        public void m_()
        {
          MainViewActivity.r(MainViewActivity.this).removeView(MainViewActivity.s(MainViewActivity.this));
          MainViewActivity.r(MainViewActivity.this).removeView(MainViewActivity.t(MainViewActivity.this));
          MainViewActivity.this.c();
        }
      });
      return true;
    }
    this.j.removeView(this.X);
    this.j.removeView(this.Y);
    a((ff)localObject1, paramBoolean);
    return true;
  }
  
  private void g(boolean paramBoolean)
  {
    if (this.am == null) {
      return;
    }
    AbsoluteLayout.LayoutParams localLayoutParams;
    if (!paramBoolean)
    {
      localLayoutParams = (AbsoluteLayout.LayoutParams)this.am.getLayoutParams();
      localLayoutParams.x = ((ot.b - localLayoutParams.width) / 2);
      localLayoutParams.y = ((ot.c - localLayoutParams.height) * 2 / 5);
    }
    else
    {
      if (ot.b > ot.c)
      {
        int i1 = ot.c;
        ot.c = ot.b;
        ot.b = i1;
      }
      localLayoutParams = (AbsoluteLayout.LayoutParams)this.am.getLayoutParams();
      localLayoutParams.x = ((ot.b - localLayoutParams.width) / 2);
      localLayoutParams.y = ((ot.c - localLayoutParams.height) * 2 / 5);
    }
    this.am.requestLayout();
  }
  
  void A()
  {
    new xm(this, new xl()
    {
      public void d_(int paramAnonymousInt)
      {
        if (paramAnonymousInt == 513)
        {
          Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(ot.B));
          MainViewActivity.this.startActivity(localIntent);
          MainViewActivity.this.d(true);
        }
      }
      
      public void e_(int paramAnonymousInt)
      {
        MainViewActivity.this.d(false);
      }
      
      public void f_(int paramAnonymousInt) {}
    }).a(getResources().getString(2131493906), getResources().getString(2131493902), getResources().getString(2131493904), 513, 2131100190);
  }
  
  public void B()
  {
    if (this.am != null)
    {
      this.am.removeAllViews();
      this.j.removeView(this.am);
      this.am = null;
    }
  }
  
  public void C()
  {
    int i2 = getResources().getConfiguration().orientation;
    int i1 = 1;
    if (i2 != 1) {
      i1 = 0;
    }
    if (i1 == 0)
    {
      ot.c(this);
      if (ot.b > ot.c)
      {
        i2 = ot.c;
        ot.c = ot.b;
        ot.b = i2;
      }
    }
    i2 = wx.aO * ot.b / 640;
    int i3 = wx.aT * ot.c / 1136;
    int i4 = wx.am * ot.c / 1136;
    int i5 = wx.aS * ot.c / 1136;
    int i6 = ot.b * 2 / 3;
    int i7 = wx.a * ot.c / 1136;
    this.am = new wn(this);
    this.am.setLayoutParams(new AbsoluteLayout.LayoutParams(i2, i3, (ot.b - i2) / 2, i6 + i7 - i3));
    this.am.setBackgroundResource(2131099656);
    this.j.addView(this.am);
    this.an = new RoundProgressBar(this);
    this.an.setLayoutParams(new AbsoluteLayout.LayoutParams(i5, i5, (i2 - i5) / 2, (i3 - i5) / 2));
    this.an.setMax(255);
    this.an.setProgress(100);
    this.an.setCircleColor(-1);
    this.an.setCircleProgressColor(getResources().getColor(2130968590));
    this.am.addView(this.an);
    this.ao = this.am.b(this, this.am, "", i4, i4, (i2 - i4) / 2, (i3 - i4) / 2, 1);
    this.ao.setBackgroundResource(2131099657);
    this.am.setVisibility(4);
    if (i1 == 0)
    {
      ot.d(this);
      g(false);
    }
  }
  
  public void D()
  {
    if (this.M != null)
    {
      this.M.removeAllViews();
      this.j.removeView(this.M);
      this.M = null;
    }
    int i2 = getResources().getConfiguration().orientation;
    int i1 = 1;
    if (i2 != 1) {
      i1 = 0;
    }
    if (i1 == 0)
    {
      ot.c(this);
      if (ot.b > ot.c)
      {
        i2 = ot.c;
        ot.c = ot.b;
        ot.b = i2;
      }
    }
    i2 = ot.b;
    int i3 = wx.Z * ot.c / 1136;
    int i4 = wx.az * ot.c / 1136;
    this.M = new vt(this, this);
    this.M.a(i2 - i3, i4);
    if (i1 == 0)
    {
      ot.d(this);
      ab();
    }
  }
  
  public int E()
  {
    return this.N;
  }
  
  public void F()
  {
    if (this.K != null) {
      this.K.j();
    }
    this.N = 0;
    if (this.l != null) {
      this.l.m(this.N);
    }
  }
  
  public void G()
  {
    if (this.K != null) {
      this.K.k();
    }
  }
  
  public vp H()
  {
    return this.O;
  }
  
  public void I()
  {
    this.O = null;
  }
  
  public void J()
  {
    if (this.K != null) {
      this.K.n();
    }
  }
  
  public ArrayList<wi> K()
  {
    if (this.u != null)
    {
      ArrayList localArrayList1 = this.u.a();
      ArrayList localArrayList2 = new ArrayList();
      int i1 = 0;
      while (i1 < localArrayList1.size())
      {
        wi localWi1 = (wi)localArrayList1.get(i1);
        wi localWi2 = this.u.a(localWi1);
        localWi2.c = localWi1.c;
        localWi2.d = localWi1.d;
        localWi2.e = localWi1.e;
        localWi2.w = localWi1.w;
        localWi2.s = localWi1.s;
        localArrayList2.add(localWi2);
        i1 += 1;
      }
      return localArrayList2;
    }
    return null;
  }
  
  public ArrayList<wi> L()
  {
    if (this.u != null) {
      return this.u.b();
    }
    return null;
  }
  
  public ArrayList<wi> M()
  {
    if (this.u != null) {
      return this.u.c();
    }
    return null;
  }
  
  public void O()
  {
    ld.c("--------FaceImageGetTimeoutCheck()--------", new Object[0]);
    this.U = new Timer();
    this.V = new TimerTask()
    {
      public void run()
      {
        MainViewActivity.g(MainViewActivity.this).sendEmptyMessage(4108);
      }
    };
    this.U.schedule(this.V, 30000L);
  }
  
  public void P()
  {
    if (ue.a.c()) {
      ue.a.b();
    } else {
      ue.a.a();
    }
    if (ot.ag)
    {
      ot.ar.a(getApplicationContext(), this);
      return;
    }
    ot.ar.z();
  }
  
  public int Q()
  {
    return this.f;
  }
  
  public int a()
  {
    int i1 = getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (i1 > 0) {
      return getResources().getDimensionPixelSize(i1);
    }
    return 0;
  }
  
  public void a(int paramInt)
  {
    int i1 = paramInt;
    if (this.J != null)
    {
      i1 = paramInt;
      if (this.J.a()) {
        if (paramInt == 6)
        {
          i1 = 0;
        }
        else
        {
          i1 = paramInt;
          if (paramInt == 7) {
            i1 = 1;
          }
        }
      }
    }
    if (ot.bx)
    {
      setRequestedOrientation(0);
      return;
    }
    setRequestedOrientation(i1);
  }
  
  public void a(int paramInt, ArrayList<wd> paramArrayList)
  {
    if ((this.t != null) && (paramInt == 0) && (paramArrayList.size() > 0))
    {
      Object localObject = this.u.a();
      ArrayList localArrayList = this.u.b();
      paramInt = 0;
      int i1;
      while (paramInt < paramArrayList.size())
      {
        i1 = 0;
        while (i1 < ((ArrayList)localObject).size())
        {
          wi localWi = (wi)((ArrayList)localObject).get(i1);
          if ((localWi != null) && (!localWi.w) && (localWi.v.replace("-", "").equals(((wd)paramArrayList.get(paramInt)).a)))
          {
            localWi.c = ((wd)paramArrayList.get(paramInt)).b;
            ((ArrayList)localObject).set(i1, localWi);
            break;
          }
          i1 += 1;
        }
        paramInt += 1;
      }
      paramInt = 0;
      while (paramInt < paramArrayList.size())
      {
        i1 = 0;
        while (i1 < localArrayList.size())
        {
          localObject = (wi)localArrayList.get(i1);
          if ((localObject != null) && (!((wi)localObject).w) && (((wi)localObject).v.replace("-", "").equals(((wd)paramArrayList.get(paramInt)).a)))
          {
            ((wi)localObject).c = ((wd)paramArrayList.get(paramInt)).b;
            localArrayList.set(i1, localObject);
            break;
          }
          i1 += 1;
        }
        paramInt += 1;
      }
      ot.ar.b(localArrayList);
      this.W.sendEmptyMessage(4105);
    }
  }
  
  public void a(int paramInt1, uh paramUh, int paramInt2, String paramString) {}
  
  public void a(int paramInt, vp paramVp)
  {
    if (paramVp == null) {
      return;
    }
    Object localObject = ot.ar.a(paramVp.i, paramVp.j);
    if ((localObject != null) && (((uh)localObject).S))
    {
      localObject = ((uh)localObject).b;
      if (localObject == null) {
        return;
      }
      ((si)localObject).c(paramInt, paramVp.k, paramVp.l);
      return;
    }
    this.W.sendEmptyMessage(4102);
  }
  
  public void a(Context paramContext, String paramString)
  {
    if (this.g == null) {
      this.g = new yi.a(1000, 2131099666);
    }
    if (this.h != null) {
      paramContext = this.h;
    } else {
      paramContext = yi.a((Activity)paramContext, paramString, this.g);
    }
    paramContext.a(Integer.MAX_VALUE);
    paramString = new FrameLayout.LayoutParams(-2, -2);
    paramString.bottomMargin = (wx.w * ot.c / 1136);
    paramString.gravity = 81;
    paramContext.a(paramString);
    paramContext.a();
  }
  
  public void a(String paramString)
  {
    boolean bool;
    if (this.K != null) {
      bool = this.K.b(paramString);
    } else {
      bool = false;
    }
    if (bool)
    {
      this.N -= 1;
      if (this.N < 0) {
        this.N = 0;
      }
      if (this.l != null) {
        this.l.m(this.N);
      }
    }
  }
  
  public void a(String paramString, int paramInt)
  {
    m();
    q();
    if (this.l == null)
    {
      this.l = new pz(this, this);
      this.l.K();
      this.l.v();
      this.j.addView(this.l, new AbsoluteLayout.LayoutParams(ot.b, ot.c, 0, 0));
    }
    else
    {
      this.l.K();
      this.l.r();
      this.l.setVisibility(0);
    }
    boolean bool;
    if (getResources().getConfiguration().orientation == 2) {
      bool = true;
    } else {
      bool = false;
    }
    BurialPointUtil.getInstance().sendEventGotoLivePage(bool);
    this.l.setId(256);
    if (this.O != null)
    {
      if (this.O.c.equals("expiredAlarmMsg"))
      {
        this.l.aH();
        vv.a().a(0, 1000L, new vi()
        {
          public void a(int paramAnonymousInt)
          {
            MainViewActivity.g(MainViewActivity.this).post(new Runnable()
            {
              public void run()
              {
                MainViewActivity.this.b(MainViewActivity.l(MainViewActivity.this).a, "EA");
                MainViewActivity.a(MainViewActivity.this, null);
              }
            });
          }
        });
        return;
      }
      if (this.O.c.equals("FaceMatch"))
      {
        this.O.n = "LP";
      }
      else if (this.O.n.equals("PB"))
      {
        uh localUh = ot.ar.a(this.O.i, this.O.j);
        if (localUh != null)
        {
          long l1 = 0L;
          try
          {
            long l2 = ot.k("yyyy-MM-dd HH:mm:ss").parse(sy.a(this.O.e)).getTime();
            paramInt = ot.ab;
            l1 = l2 - paramInt * 1000;
          }
          catch (ParseException paramString)
          {
            paramString.printStackTrace();
          }
          paramString = new ArrayList();
          paramString.add(Integer.valueOf(-1));
          a(true, localUh.j, localUh.i, paramString, l1, this.O.m);
          this.q.J();
          return;
        }
        this.O = null;
      }
    }
    this.w = this.l;
    if ((paramInt != 3) || (!ot.W) || (this.S) || (ot.bx)) {
      this.l.b(paramString, paramInt);
    }
    this.S = false;
    if (!ot.bx) {
      this.W.sendEmptyMessage(4100);
    }
    C();
    this.l.m(this.N);
    if (ot.bx) {
      a(4);
    }
    if (this.l.aM())
    {
      a(1);
      p();
    }
  }
  
  public void a(String paramString1, String paramString2, String paramString3)
  {
    if (this.K != null) {
      this.K.a(paramString1, paramString2, paramString3);
    }
    ArrayList localArrayList = L();
    if ((localArrayList != null) && (paramString3 != null) && (paramString1 != null))
    {
      if (paramString2 == null) {
        return;
      }
      int i2 = localArrayList.size();
      int i1 = 0;
      while (i1 < i2)
      {
        wi localWi = (wi)localArrayList.get(i1);
        if ((localWi != null) && (localWi.a.equals(paramString1)) && (localWi.v.equals(paramString3)))
        {
          localWi.a = paramString2;
          localArrayList.set(i1, localWi);
          ot.ar.b(localArrayList);
          return;
        }
        i1 += 1;
      }
      return;
    }
  }
  
  public void a(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    paramString1 = ot.ar.a(paramString1, paramString2);
    if ((paramString1 != null) && (paramString1.S) && (paramString1.b != null))
    {
      paramString2 = new ArrayList();
      paramString2.add("LOG_ALARM_ALL");
      paramString2.add("LOG_EXCEPTION_ALL");
      paramString1.b.a(paramString3, paramString4, paramString2);
      return;
    }
    this.v.a(-1, null);
  }
  
  public void a(String paramString, vp paramVp)
  {
    if (this.K == null) {
      return;
    }
    if (this.at != null)
    {
      this.at.i();
      this.at.setCallback(null);
      this.at.removeAllViews();
      this.j.removeView(this.at);
      this.at = null;
    }
    if (paramString.equals("LP"))
    {
      d(paramVp);
      return;
    }
    if (paramString.equals("PB"))
    {
      c(paramVp);
      return;
    }
    if (paramString.equals("MA"))
    {
      a(paramVp);
      return;
    }
    if (paramString.equals("EA"))
    {
      e(paramVp);
      return;
    }
    if (paramString.equals("FM")) {
      b(paramVp);
    }
  }
  
  public void a(ArrayList<uh> paramArrayList)
  {
    if (paramArrayList == null) {
      return;
    }
    int i2 = paramArrayList.size();
    ArrayList localArrayList = new ArrayList();
    int i1 = 0;
    while (i1 < i2)
    {
      uh localUh = (uh)paramArrayList.get(i1);
      if ((localUh != null) && (localUh.al))
      {
        vo.c localC = new vo.c();
        localC.b(localUh.i);
        localC.a(localUh.aq);
        localArrayList.add(localC);
      }
      i1 += 1;
    }
    if (this.K != null)
    {
      if ((this.K.a(localArrayList, 0, true)) && (this.u != null)) {
        this.u.f();
      }
    }
    else {
      this.L = true;
    }
  }
  
  public void a(ArrayList<wi> paramArrayList, String paramString1, String paramString2)
  {
    if ((this.K != null) && (this.u != null))
    {
      PushMessageService localPushMessageService = this.K;
      wa localWa = this.u;
      localPushMessageService.b(wa.a(paramArrayList, paramString1), paramString2);
    }
  }
  
  public void a(ArrayList<wi> paramArrayList1, ArrayList<wi> paramArrayList2)
  {
    if (this.u != null) {
      this.u.b(paramArrayList1, paramArrayList2);
    }
  }
  
  public void a(List<wf> paramList, String paramString)
  {
    if (ud.a.b())
    {
      if ((this.v != null) && (!this.v.a())) {
        return;
      }
      ud.a.a();
      uh localUh = ot.ar.a(paramString, true);
      if (localUh == null) {
        return;
      }
      paramString = this.v;
      int i3 = 0;
      if (paramString != null)
      {
        paramString = this.v.getSelectMessage();
        if (paramString != null) {
          try
          {
            i1 = Integer.parseInt((String)paramString.m.get(2));
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        } else {
          i1 = 0;
        }
        this.v.b(paramString);
      }
      else
      {
        i1 = 0;
      }
      Message localMessage = null;
      paramString = localMessage;
      if (paramList != null)
      {
        int i2 = i1;
        if (i1 <= 0) {
          i2 = paramList.size();
        }
        wi localWi = d(localUh.i, localUh.aq);
        paramString = "LP";
        if (localWi != null) {
          if (localWi.e == 0) {
            paramString = "LP";
          } else {
            paramString = "PB";
          }
        }
        Iterator localIterator = paramList.iterator();
        i1 = 0;
        while (localIterator.hasNext())
        {
          Object localObject = (wf)localIterator.next();
          if (!a(ot.l(((wf)localObject).a), ((wf)localObject).f, localWi))
          {
            i1 += 1;
          }
          else
          {
            vp localVp = new vp();
            localVp.a = wg.b().d();
            localVp.b = "DevAlarm";
            localVp.c = ot.l(((wf)localObject).a);
            localVp.e = ((wf)localObject).b;
            localVp.i = localUh.aq;
            localVp.j = localUh.i;
            localVp.k = ((wf)localObject).f.d();
            localVp.l = ((wf)localObject).g;
            localObject = ((wf)localObject).h.iterator();
            while (((Iterator)localObject).hasNext())
            {
              wg localWg = (wg)((Iterator)localObject).next();
              localVp.m.add(localWg.d());
            }
            localVp.n = paramString;
            localVp.o = true;
            localVp.p = false;
            localVp.q = true;
            localVp.r = "";
            if ((this.K != null) && (!this.K.b(localVp))) {
              i1 += 1;
            }
          }
        }
        if (i1 <= 0)
        {
          paramString = localMessage;
          if (i2 <= paramList.size()) {}
        }
        else
        {
          paramString = ot.a(getResources().getString(2131493879), new Object[] { Integer.valueOf(i2), Integer.valueOf(i2 - paramList.size() + i1) });
        }
      }
      localMessage = this.W.obtainMessage();
      int i1 = i3;
      if (paramList == null) {
        i1 = -1;
      }
      localMessage.arg1 = i1;
      localMessage.obj = paramString;
      localMessage.what = 4107;
      this.W.sendMessage(localMessage);
      return;
    }
  }
  
  public void a(uh paramUh)
  {
    if (this.u != null) {
      this.u.b(paramUh);
    }
  }
  
  public void a(uh paramUh, int paramInt)
  {
    if ((paramInt == 12288) && (this.w != null) && ((this.w instanceof nt)))
    {
      ((nt)this.w).a(paramUh);
      return;
    }
    if (paramInt == 12289)
    {
      a(ot.ar.b());
      return;
    }
    if (paramInt == 12290)
    {
      if (this.u != null) {
        this.u.a(paramUh);
      }
      if ((this.t != null) && (paramUh != null)) {
        paramUh = paramUh.b;
      }
      ot.ar.a(true);
      this.W.sendEmptyMessage(4105);
      return;
    }
    Bundle localBundle;
    if (paramInt == 12291)
    {
      paramUh = this.W.obtainMessage();
      paramUh.what = 4106;
      localBundle = new Bundle();
      localBundle.putInt("mobilePushState", wc.a);
      paramUh.obj = localBundle;
      this.W.sendMessage(paramUh);
      return;
    }
    if (paramInt == 12292)
    {
      paramUh = this.W.obtainMessage();
      paramUh.what = 4106;
      localBundle = new Bundle();
      localBundle.putInt("mobilePushState", wc.b);
      paramUh.obj = localBundle;
      this.W.sendMessage(paramUh);
      return;
    }
    if (paramInt == 12293)
    {
      paramUh = this.W.obtainMessage();
      paramUh.what = 4106;
      localBundle = new Bundle();
      localBundle.putInt("mobilePushState", wc.d);
      paramUh.obj = localBundle;
      this.W.sendMessage(paramUh);
      return;
    }
    if ((paramInt == 12298) && (this.p != null) && (this.p.getVisibility() == 0)) {
      this.p.k();
    }
  }
  
  public void a(vp paramVp)
  {
    if ((this.q != null) && (this.q.getVisibility() == 0) && (this.q.getRemoteState()))
    {
      if (c(paramVp))
      {
        this.q.E();
        this.q.F();
        this.q.D();
      }
    }
    else if (d(paramVp))
    {
      this.l.aI();
      this.l.ao();
    }
  }
  
  public void a(vp paramVp, boolean paramBoolean)
  {
    if (paramVp == null) {
      return;
    }
    if (((this.k == null) || (this.k.getVisibility() == 4)) && ((this.v == null) || (this.v.getVisibility() == 4)))
    {
      boolean bool = paramBoolean;
      if (this.w != null)
      {
        bool = paramBoolean;
        if (this.w.e_()) {
          bool = true;
        }
      }
      if ((this.x != null) && (this.x.getVisibility() != 4)) {
        paramBoolean = false;
      } else {
        paramBoolean = true;
      }
      if ((this.M != null) && (!bool) && (!paramVp.r.equals("")))
      {
        bool = paramVp.c.equals("FaceMatch");
        if (!paramVp.c.equals("expiredAlarmMsg")) {
          this.M.a(vo.a(this, paramVp.c, paramVp.d, paramVp.m), vf.b(this, sy.a(paramVp.e)), vo.a(this, paramVp), paramVp.a, paramVp.r, false, bool, paramBoolean, this.j);
        } else {
          this.M.a(vo.a(this, paramVp.c, paramVp.d, paramVp.m), vf.b(this, sy.a(paramVp.e)), paramVp.j, paramVp.a, paramVp.r, true, bool, paramBoolean, this.j);
        }
        this.M.bringToFront();
      }
    }
    if (this.v != null) {
      this.v.c(paramVp);
    }
    this.N = this.K.l();
    if (this.l != null) {
      this.l.m(this.N);
    }
  }
  
  public void a(boolean paramBoolean)
  {
    this.S = paramBoolean;
  }
  
  public void a(boolean paramBoolean, int paramInt)
  {
    if (this.y == null) {
      return;
    }
    if (paramBoolean) {
      this.y.setStreamVolume(3, paramInt, 0);
    } else {
      this.y.setStreamVolume(3, paramInt, 0);
    }
    if ((this.am != null) && (this.am.getVisibility() == 4)) {
      this.am.setVisibility(0);
    }
    if (this.ao != null) {
      this.ao.setBackgroundResource(2131099658);
    }
    if (this.an != null)
    {
      this.an.setMax(this.z);
      this.an.setProgress(paramInt);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("operation--->ChangeAudioVolume=");
      localStringBuilder.append(paramInt);
      ld.c(localStringBuilder.toString(), new Object[0]);
    }
  }
  
  public void a(boolean paramBoolean, String paramString1, String paramString2, List<Integer> paramList, long paramLong, ArrayList<String> paramArrayList)
  {
    m();
    this.q = new sb(this, this, paramBoolean, paramString1, paramString2, paramList, null, paramLong, paramArrayList);
    this.q.setId(257);
    if (this.d == null) {
      this.d = AnimationUtils.loadAnimation(this, 2130771977);
    }
    this.j.addView(this.q, new AbsoluteLayout.LayoutParams(ot.b, ot.c, 0, 0));
    this.q.r();
    this.w = this.q;
    C();
  }
  
  public void a(boolean paramBoolean1, boolean paramBoolean2, int paramInt, boolean paramBoolean3)
  {
    int i1 = 0;
    if (!paramBoolean1)
    {
      if (!paramBoolean3)
      {
        paramInt = paramInt * this.z / (ot.c * 2 / 3);
        if (paramBoolean2) {
          paramInt = this.A + paramInt;
        } else {
          paramInt = this.A - paramInt;
        }
        if (paramInt > this.z) {
          paramInt = this.z;
        } else if (paramInt < 0) {
          paramInt = i1;
        }
        this.ah = paramInt;
        a(paramBoolean2, paramInt);
        return;
      }
      this.A = this.y.getStreamVolume(3);
      this.A = this.ah;
      if (this.am != null) {
        this.am.setVisibility(4);
      }
    }
    else
    {
      if (this.B == null) {
        return;
      }
      if (!paramBoolean3)
      {
        if (this.E)
        {
          this.B.c();
          this.E = false;
        }
        paramInt = paramInt * this.C / (ot.c * 2 / 3);
        if (paramBoolean2) {
          paramInt = this.D + paramInt;
        } else {
          paramInt = this.D - paramInt;
        }
        if (paramInt > this.C)
        {
          i1 = this.C;
        }
        else
        {
          i1 = paramInt;
          if (paramInt < 10) {
            i1 = 10;
          }
        }
        if ((this.am != null) && (this.am.getVisibility() == 4)) {
          this.am.setVisibility(0);
        }
        if (this.ao != null) {
          this.ao.setBackgroundResource(2131099657);
        }
        if (this.an != null)
        {
          this.an.setMax(255);
          this.an.setProgress(i1);
        }
        this.B.a(i1);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("operation--->setBrightness=");
        localStringBuilder.append(i1);
        ld.c(localStringBuilder.toString(), new Object[0]);
        this.ai = i1;
        return;
      }
      if (this.E) {
        this.B.d();
      }
      this.D = this.B.b();
      this.D = this.ai;
      if (this.am != null) {
        this.am.setVisibility(4);
      }
    }
  }
  
  public boolean a(String paramString1, String paramString2)
  {
    int i1;
    int i2;
    do
    {
      do
      {
        try
        {
          paramString2.indexOf(".");
          paramString1.indexOf(".");
          paramString2 = paramString2.split("\\.");
          paramString1 = paramString1.split("\\.");
          if (paramString2.length >= 2)
          {
            if (paramString1.length < 2) {
              return false;
            }
            i1 = Integer.parseInt(paramString2[0]);
            i2 = Integer.parseInt(paramString1[0]);
            if (i2 > i1)
            {
              return true;
              i1 = Integer.parseInt(paramString2[1]);
              i2 = Integer.parseInt(paramString1[1]);
              if (i2 <= i1) {
                break;
              }
              return true;
              if (paramString1.length > paramString2.length) {
                return true;
              }
              if ((paramString1.length == 3) && (paramString2.length == 3))
              {
                i1 = Integer.parseInt(paramString2[2]);
                i2 = Integer.parseInt(paramString1[2]);
                if (i2 > i1) {
                  return true;
                }
              }
              return false;
            }
          }
          else
          {
            return false;
          }
        }
        catch (Exception paramString1)
        {
          paramString1.printStackTrace();
          return false;
        }
      } while (i2 >= i1);
      return false;
    } while (i2 >= i1);
    return false;
  }
  
  public boolean a(String paramString, wg paramWg, wi paramWi)
  {
    if (paramWg != null)
    {
      if (paramString.equals("")) {
        return false;
      }
      if (paramWi == null) {
        return true;
      }
      if (paramString.equals("motion"))
      {
        if (paramWi.f == null) {
          return false;
        }
        paramString = paramWi.f.iterator();
        do
        {
          if (!paramString.hasNext()) {
            break;
          }
          paramWi = (fw)paramString.next();
        } while (!paramWi.e.a(paramWg));
        return paramWi.d;
      }
      if (paramString.equals("sensor"))
      {
        if (paramWi.g == null) {
          return false;
        }
        paramString = paramWi.g.iterator();
        do
        {
          if (!paramString.hasNext()) {
            break;
          }
          paramWi = (fw)paramString.next();
        } while (!paramWi.e.a(paramWg));
        return paramWi.d;
      }
      if (paramString.equals("intelligent"))
      {
        fw localFw;
        if (paramWi.h != null)
        {
          paramString = paramWi.h.iterator();
          while (paramString.hasNext())
          {
            localFw = (fw)paramString.next();
            if (localFw.e.a(paramWg)) {
              return localFw.d;
            }
          }
        }
        if (paramWi.i != null)
        {
          paramString = paramWi.i.iterator();
          while (paramString.hasNext())
          {
            localFw = (fw)paramString.next();
            if (localFw.e.a(paramWg)) {
              return localFw.d;
            }
          }
        }
        if (paramWi.j != null)
        {
          paramString = paramWi.j.iterator();
          while (paramString.hasNext())
          {
            localFw = (fw)paramString.next();
            if (localFw.e.a(paramWg)) {
              return localFw.d;
            }
          }
        }
        if (paramWi.k != null)
        {
          paramString = paramWi.k.iterator();
          do
          {
            if (!paramString.hasNext()) {
              break;
            }
            paramWi = (fw)paramString.next();
          } while (!paramWi.e.a(paramWg));
          return paramWi.d;
        }
      }
      else
      {
        if (paramString.equals("frontEndOffline")) {
          return paramWi.p;
        }
        if (paramString.equals("videoLoss")) {
          return paramWi.q;
        }
        if (paramString.equals("diskRWError")) {
          return paramWi.m;
        }
        if (paramString.equals("diskFull")) {
          return paramWi.n;
        }
        if (paramString.equals("noDisk")) {
          return paramWi.l;
        }
        if (paramString.equals("hddPullOut")) {
          return paramWi.r;
        }
        if (paramString.equals("illegalAccess")) {
          return paramWi.o;
        }
      }
      return false;
    }
    return false;
  }
  
  public void b()
  {
    if (ot.bx) {
      return;
    }
    if (this.ad) {
      r();
    }
    p();
    a(1);
    if (this.x == null)
    {
      this.x = new os(this, this, this);
      if (this.j != null)
      {
        this.j.removeView(this.x);
        this.j.addView(this.x, new AbsoluteLayout.LayoutParams(ot.b, ot.c, 0, 0));
      }
      this.x.e();
    }
    else
    {
      if (this.j != null)
      {
        this.j.removeView(this.x);
        this.j.addView(this.x, new AbsoluteLayout.LayoutParams(ot.b, ot.c, 0, 0));
      }
      this.x.setVisibility(0);
    }
    this.x.g();
    this.x.setGesturePswLockState(1);
  }
  
  public void b(int paramInt)
  {
    this.N = paramInt;
    if (this.l != null) {
      this.l.m(this.N);
    }
  }
  
  public void b(String paramString)
  {
    if (this.K != null) {
      this.K.c(paramString);
    }
  }
  
  public void b(String paramString, int paramInt)
  {
    Message localMessage = this.W.obtainMessage();
    localMessage.what = 4103;
    localMessage.obj = paramString;
    localMessage.arg1 = paramInt;
    this.W.sendMessage(localMessage);
  }
  
  public void b(String paramString1, String paramString2)
  {
    if (this.v != null)
    {
      this.v.b(paramString1);
    }
    else
    {
      this.N -= 1;
      if (this.N < 0) {
        this.N = 0;
      }
      if (this.l != null) {
        this.l.m(this.N);
      }
    }
    if (this.K != null)
    {
      this.K.b(paramString1);
      paramString1 = this.K.a(paramString1);
      if (paramString1 != null) {
        a(paramString2, paramString1);
      }
    }
  }
  
  public void b(ArrayList<String> paramArrayList)
  {
    if ((this.K != null) && (paramArrayList != null) && (paramArrayList.size() > 0)) {
      this.K.a(paramArrayList);
    }
  }
  
  public void b(si paramSi, uh paramUh, int paramInt)
  {
    if (paramInt == 3)
    {
      uh localUh = ot.ar.a(paramUh.j, true);
      paramUh = localUh;
      if (localUh != null)
      {
        localUh.S = false;
        paramUh = localUh;
      }
    }
    if (this.w != null) {
      if ((this.x != null) && (this.x.getVisibility() == 0) && (this.x.getGesturePswLockState() == 1))
      {
        if (paramInt == 0) {
          this.w.a(paramSi, paramUh, paramInt);
        }
      }
      else {
        this.w.a(paramSi, paramUh, paramInt);
      }
    }
    if ((this.x != null) && (this.x.getVisibility() == 0)) {
      this.x.a(paramSi, paramUh, paramInt);
    }
  }
  
  public void b(vp paramVp)
  {
    if (paramVp == null) {
      return;
    }
    if ((paramVp.m != null) && (paramVp.m.size() != 0))
    {
      Object localObject = ot.ar.a(paramVp.i, paramVp.j);
      if ((localObject != null) && (((uh)localObject).S))
      {
        localObject = ((uh)localObject).b;
        if (localObject == null) {
          return;
        }
        a(paramVp, null, (si)localObject);
        return;
      }
      this.W.sendEmptyMessage(4102);
      return;
    }
    this.W.post(new Runnable()
    {
      public void run()
      {
        MainViewActivity.this.a(MainViewActivity.this, MainViewActivity.this.getResources().getString(2131493884));
      }
    });
  }
  
  public void b(boolean paramBoolean)
  {
    if (!paramBoolean) {
      m();
    }
    p();
    a(1);
    this.o = new ge(this, this, paramBoolean);
    this.o.setId(260);
    if (this.d == null) {
      this.d = AnimationUtils.loadAnimation(this, 2130771977);
    }
    this.j.addView(this.o, new AbsoluteLayout.LayoutParams(ot.b, ot.c, 0, 0));
    this.o.a();
    this.w = this.o;
  }
  
  public ArrayList<fw> c(ArrayList<fw> paramArrayList)
  {
    if (this.u != null) {
      return this.u.a(paramArrayList);
    }
    return null;
  }
  
  public void c()
  {
    m();
    a(1);
    this.k = new ov(this, this);
    this.j.addView(this.k, new AbsoluteLayout.LayoutParams(ot.b, ot.c, 0, 0));
    this.w = this.k;
    this.k.a();
  }
  
  public void c(int paramInt)
  {
    if (this.t != null) {
      this.t.d(paramInt);
    }
  }
  
  public void c(String paramString)
  {
    if (this.K != null) {
      this.K.e(paramString);
    }
  }
  
  public void c(String paramString1, String paramString2)
  {
    if (this.K != null)
    {
      this.K.a(paramString1, paramString2.replaceAll("-", ""));
      this.N = this.K.l();
      b(this.N);
    }
  }
  
  public void c(boolean paramBoolean)
  {
    ad();
    af();
    if (this.aa != null) {
      a(4);
    }
    if (this.x != null)
    {
      this.x.setVisibility(4);
      if (this.i != null)
      {
        this.i = ((NotificationManager)getSystemService("notification"));
        this.i.cancelAll();
      }
      if (this.w != null)
      {
        int i1 = this.x.getGesturePswLockState();
        this.w.a(i1, paramBoolean);
      }
      ot.bg = false;
      ot.ar.i();
      return;
    }
  }
  
  public wi d(String paramString1, String paramString2)
  {
    Object localObject = L();
    if ((localObject != null) && (paramString1 != null))
    {
      if (paramString2 == null) {
        return null;
      }
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        wi localWi = (wi)((Iterator)localObject).next();
        if ((localWi != null) && (localWi.a.equals(paramString1)) && (localWi.v.equals(paramString2))) {
          return localWi;
        }
      }
      return null;
    }
    return null;
  }
  
  public void d()
  {
    m();
    a(1);
    this.n = new lw(this, this);
    this.n.setId(259);
    if (this.d == null) {
      this.d = AnimationUtils.loadAnimation(this, 2130771977);
    }
    this.j.addView(this.n, new AbsoluteLayout.LayoutParams(ot.b, ot.c, 0, 0));
    this.w = this.n;
  }
  
  /* Error */
  void d(boolean paramBoolean)
  {
    // Byte code:
    //   0: new 445	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 446	java/lang/StringBuilder:<init>	()V
    //   7: astore_2
    //   8: aload_2
    //   9: getstatic 682	ot:s	Ljava/lang/String;
    //   12: invokevirtual 461	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: pop
    //   16: aload_2
    //   17: ldc_w 2357
    //   20: invokevirtual 461	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: pop
    //   24: new 2359	java/io/FileWriter
    //   27: dup
    //   28: aload_2
    //   29: invokevirtual 466	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   32: iconst_0
    //   33: invokespecial 2362	java/io/FileWriter:<init>	(Ljava/lang/String;Z)V
    //   36: astore_2
    //   37: new 445	java/lang/StringBuilder
    //   40: dup
    //   41: invokespecial 446	java/lang/StringBuilder:<init>	()V
    //   44: astore 4
    //   46: aload 4
    //   48: ldc_w 650
    //   51: invokevirtual 461	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: pop
    //   55: iload_1
    //   56: ifeq +11 -> 67
    //   59: iconst_1
    //   60: invokestatic 1608	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   63: astore_3
    //   64: goto +32 -> 96
    //   67: new 445	java/lang/StringBuilder
    //   70: dup
    //   71: invokespecial 446	java/lang/StringBuilder:<init>	()V
    //   74: astore_3
    //   75: aload_3
    //   76: ldc_w 2364
    //   79: invokevirtual 461	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   82: pop
    //   83: aload_3
    //   84: invokestatic 1362	java/lang/System:currentTimeMillis	()J
    //   87: invokevirtual 1378	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   90: pop
    //   91: aload_3
    //   92: invokevirtual 466	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   95: astore_3
    //   96: aload 4
    //   98: aload_3
    //   99: invokevirtual 2367	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   102: pop
    //   103: aload_2
    //   104: aload 4
    //   106: invokevirtual 466	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   109: invokevirtual 2370	java/io/FileWriter:write	(Ljava/lang/String;)V
    //   112: aload_2
    //   113: ifnull +55 -> 168
    //   116: aload_2
    //   117: invokevirtual 2373	java/io/FileWriter:close	()V
    //   120: return
    //   121: astore 4
    //   123: aload_2
    //   124: astore_3
    //   125: aload 4
    //   127: astore_2
    //   128: goto +6 -> 134
    //   131: astore_2
    //   132: aconst_null
    //   133: astore_3
    //   134: aload_3
    //   135: ifnull +15 -> 150
    //   138: aload_3
    //   139: invokevirtual 2373	java/io/FileWriter:close	()V
    //   142: goto +8 -> 150
    //   145: astore_3
    //   146: aload_3
    //   147: invokevirtual 2374	java/io/IOException:printStackTrace	()V
    //   150: aload_2
    //   151: athrow
    //   152: aconst_null
    //   153: astore_2
    //   154: aload_2
    //   155: ifnull +13 -> 168
    //   158: aload_2
    //   159: invokevirtual 2373	java/io/FileWriter:close	()V
    //   162: return
    //   163: astore_2
    //   164: aload_2
    //   165: invokevirtual 2374	java/io/IOException:printStackTrace	()V
    //   168: return
    //   169: astore_2
    //   170: goto -18 -> 152
    //   173: astore_3
    //   174: goto -20 -> 154
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	177	0	this	MainViewActivity
    //   0	177	1	paramBoolean	boolean
    //   7	121	2	localObject1	Object
    //   131	20	2	localObject2	Object
    //   153	6	2	localObject3	Object
    //   163	2	2	localIOException1	java.io.IOException
    //   169	1	2	localIOException2	java.io.IOException
    //   63	76	3	localObject4	Object
    //   145	2	3	localIOException3	java.io.IOException
    //   173	1	3	localIOException4	java.io.IOException
    //   44	61	4	localStringBuilder	StringBuilder
    //   121	5	4	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   37	55	121	finally
    //   59	64	121	finally
    //   67	96	121	finally
    //   96	112	121	finally
    //   0	37	131	finally
    //   138	142	145	java/io/IOException
    //   116	120	163	java/io/IOException
    //   158	162	163	java/io/IOException
    //   0	37	169	java/io/IOException
    //   37	55	173	java/io/IOException
    //   59	64	173	java/io/IOException
    //   67	96	173	java/io/IOException
    //   96	112	173	java/io/IOException
  }
  
  public void e()
  {
    m();
    this.aa = new op(this, this);
    q();
    this.aa.setId(262);
    this.j.addView(this.aa, new AbsoluteLayout.LayoutParams(-1, -1, 0, 0));
    this.w = this.aa;
    if (this.d == null) {
      this.d = AnimationUtils.loadAnimation(this, 2130771977);
    }
  }
  
  public boolean e(boolean paramBoolean)
  {
    if (this.J == null) {
      return false;
    }
    if (paramBoolean)
    {
      if (getRequestedOrientation() == 6) {
        setRequestedOrientation(0);
      } else if (getRequestedOrientation() == 7) {
        setRequestedOrientation(1);
      }
      return this.J.b();
    }
    this.J.c();
    if (getRequestedOrientation() == 0)
    {
      setRequestedOrientation(6);
      return true;
    }
    if (getRequestedOrientation() == 1) {
      setRequestedOrientation(7);
    }
    return true;
  }
  
  public void f()
  {
    m();
    a(1);
    this.s = new ox(this, this);
    this.s.setId(264);
    if (this.d == null) {
      this.d = AnimationUtils.loadAnimation(this, 2130771977);
    }
    this.j.addView(this.s, new AbsoluteLayout.LayoutParams(ot.b, ot.c, 0, 0));
    this.s.a();
    this.w = this.s;
  }
  
  public void g()
  {
    m();
    a(1);
    this.p = new nt(this, this, false);
    this.p.setId(261);
    if (this.d == null) {
      this.d = AnimationUtils.loadAnimation(this, 2130771977);
    }
    this.j.addView(this.p, new AbsoluteLayout.LayoutParams(ot.b, ot.c, 0, 0));
    this.p.c();
    this.w = this.p;
  }
  
  public void h()
  {
    ld.c("operation---> SetupPushConfigUI   ", new Object[0]);
    m();
    a(1);
    this.u.d();
    this.u.e();
    this.t = new wc(this, this);
    this.t.setId(265);
    if (this.d == null) {
      this.d = AnimationUtils.loadAnimation(this, 2130771977);
    }
    this.j.addView(this.t, new AbsoluteLayout.LayoutParams(ot.b, ot.c, 0, 0));
    this.t.h();
    this.w = this.t;
  }
  
  public void i()
  {
    ld.c("operation---> SetupPushMessageUI", new Object[0]);
    m();
    a(1);
    this.v = new vr(this, this);
    this.v.setId(266);
    if (this.d == null) {
      this.d = AnimationUtils.loadAnimation(this, 2130771977);
    }
    this.j.addView(this.v, new AbsoluteLayout.LayoutParams(ot.b, ot.c, 0, 0));
    ArrayList localArrayList = null;
    if (this.K != null) {
      localArrayList = this.K.c();
    }
    this.v.a(localArrayList);
    this.w = this.v;
  }
  
  public void j()
  {
    p();
    a(1);
    if (this.p == null)
    {
      this.p = new nt(this, this, true);
      this.p.setId(261);
      this.j.addView(this.p, new AbsoluteLayout.LayoutParams(ot.b, ot.c, 0, 0));
      if (this.d == null) {
        this.d = AnimationUtils.loadAnimation(this, 2130771977);
      }
      this.p.c();
    }
    this.p.o();
    this.w = this.p;
  }
  
  public void k()
  {
    m();
    p();
    a(1);
    this.r = new lm(this, this);
    this.r.setId(263);
    if (this.d == null) {
      this.d = AnimationUtils.loadAnimation(this, 2130771977);
    }
    this.j.addView(this.r, new AbsoluteLayout.LayoutParams(ot.b, ot.c, 0, 0));
    this.r.a();
    this.w = this.r;
  }
  
  public void l()
  {
    if (this.o != null)
    {
      this.o.removeAllViews();
      this.j.removeView(this.o);
      this.o = null;
      if (this.k != null) {
        this.w = this.k;
      }
    }
  }
  
  void m()
  {
    this.w = null;
    if ((this.l != null) && (this.l.getVisibility() == 0))
    {
      this.l.setVisibility(8);
      this.l.aK();
      p();
      boolean bool;
      if (getResources().getConfiguration().orientation == 2) {
        bool = true;
      } else {
        bool = false;
      }
      BurialPointUtil.getInstance().sendEventExitLivePage(bool);
    }
    if (this.k != null)
    {
      this.k.l();
      this.k.removeAllViews();
      this.j.removeView(this.k);
      this.k = null;
    }
    if (this.m != null)
    {
      this.m.removeAllViews();
      this.j.removeView(this.m);
      this.m = null;
    }
    if (this.n != null)
    {
      this.n.c();
      this.n.removeAllViews();
      this.j.removeView(this.n);
      this.n = null;
    }
    if (this.o != null)
    {
      this.o.o();
      this.o.removeAllViews();
      this.j.removeView(this.o);
      this.o = null;
    }
    if (this.p != null)
    {
      this.p.q();
      this.p.removeAllViews();
      this.j.removeView(this.p);
      this.p = null;
    }
    if (this.aa != null)
    {
      this.aa.l();
      this.aa.removeAllViews();
      this.j.removeView(this.aa);
      this.aa = null;
    }
    if (this.q != null)
    {
      this.q.E();
      this.q.l();
      this.q.removeAllViews();
      this.j.removeView(this.q);
      this.q = null;
    }
    if (this.r != null)
    {
      this.r.e();
      this.r.removeAllViews();
      this.j.removeView(this.r);
      this.r = null;
    }
    if (this.s != null)
    {
      this.s.removeAllViews();
      this.j.removeView(this.s);
      this.s = null;
    }
    if (this.am != null) {
      try
      {
        this.am.removeAllViews();
        this.am = null;
      }
      catch (Exception localException)
      {
        Log.e("error 1", localException.toString());
      }
    }
    if (this.t != null)
    {
      this.t.o();
      this.t.removeAllViews();
      this.j.removeView(this.t);
      this.t = null;
    }
    if (this.v != null)
    {
      this.v.e();
      this.v.removeAllViews();
      this.j.removeView(this.v);
      this.v = null;
    }
    e(false);
  }
  
  public void n()
  {
    o();
  }
  
  public void o()
  {
    ot.bg = true;
    if ((this.x != null) && (this.x.getVisibility() == 0)) {
      this.x.b_();
    }
    if (this.w != null) {
      this.w.b_();
    }
    Object localObject1 = getString(2131494025);
    String str = getString(2131493793);
    Object localObject2 = new Intent(this, MainViewActivity.class);
    ((Intent)localObject2).addCategory("android.intent.category.LAUNCHER");
    localObject2 = PendingIntent.getActivity(this, 1, (Intent)localObject2, 134217728);
    if (Build.VERSION.SDK_INT >= 26)
    {
      Notification.Builder localBuilder = new Notification.Builder(this, "1001");
      localBuilder.setSmallIcon(2131099942).setContentTitle((CharSequence)localObject1).setContentText(str).setWhen(System.currentTimeMillis()).setContentIntent((PendingIntent)localObject2).setAutoCancel(true).setOngoing(true).setDefaults(4);
      if (this.i != null) {
        this.i.notify(1, localBuilder.build());
      }
    }
    else
    {
      localObject1 = new af.c(this).a(2131099943).a((CharSequence)localObject1).b(str).a(System.currentTimeMillis()).a((PendingIntent)localObject2).b(true).a(true).b(4);
      ((af.c)localObject1).a(BitmapFactory.decodeResource(getResources(), 2131099941));
      if (this.i != null) {
        this.i.notify(1, ((af.c)localObject1).a());
      }
    }
    if (this.M != null)
    {
      this.M.a();
      ld.c("----1111-------HideToBackgroud()-----------", new Object[0]);
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 != 20480) && (paramInt1 != 20481)) {
      if ((paramInt1 != 20482) && (paramInt1 != 20483))
      {
        if ((this.aa != null) && (this.aa.getVisibility() == 0)) {
          this.aa.a(paramInt1, paramInt2, paramIntent);
        } else if ((this.p != null) && (this.p.getVisibility() == 0)) {
          this.p.a(paramInt1, paramInt2, paramIntent);
        } else if ((this.k != null) && (this.k.getVisibility() == 0)) {
          this.k.a(paramInt1, paramInt2, paramIntent);
        }
      }
      else if (this.l != null) {
        this.l.a(paramInt1, paramInt2, paramIntent);
      }
    }
    ot.X = true;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (paramConfiguration.orientation == 2) {
      ot.a(this);
    } else {
      ot.b(this);
    }
    ot.a(paramConfiguration.orientation, this);
    if (this.w != null)
    {
      this.w.a(this.w, ot.b, ot.c, 0, 0);
      this.w.a(paramConfiguration);
    }
    ab();
    int i1 = paramConfiguration.orientation;
    boolean bool = true;
    if (i1 != 1) {
      bool = false;
    }
    g(bool);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    a(paramBundle);
  }
  
  protected void onDestroy()
  {
    ld.c("----------mainviewactivity.onDestroy()------------", new Object[0]);
    super.onDestroy();
    this.W.removeCallbacksAndMessages(null);
    try
    {
      unbindService(this.ar);
      e(false);
      if (!ot.bm)
      {
        s();
        if (this.w != null) {
          this.w.l();
        }
        ot.ar.u();
      }
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt != 4)
    {
      switch (paramInt)
      {
      default: 
        return true;
      case 25: 
        if (this.y == null) {
          break;
        }
        this.A -= 1;
        if (this.A < 0) {
          this.A = 0;
        }
        a(true, this.A);
        aa();
        return true;
      case 24: 
        this.A += 1;
        if (this.A > this.z) {
          this.A = this.z;
        }
        a(false, this.A);
        aa();
        return true;
      }
    }
    else
    {
      if ((this.x != null) && (this.x.getVisibility() == 0))
      {
        this.x.l_();
        return true;
      }
      if ((this.aj != null) && (this.aj.getVisibility() == 0))
      {
        this.aj.l_();
        return true;
      }
      if ((this.at != null) && (this.at.getVisibility() == 0))
      {
        this.at.l_();
        return true;
      }
      if ((this.R) && (this.w != null) && (this.e)) {
        this.w.l_();
      }
    }
    return true;
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    if (paramIntent != null)
    {
      this.T = true;
      String str = paramIntent.getStringExtra("AlarmType");
      if ((str != null) && (!TextUtils.isEmpty(str)))
      {
        if (str.equals("ADMsg"))
        {
          paramIntent = (vw)new Gson().fromJson(paramIntent.getStringExtra("AlarmData"), vw.class);
          if (ot.h().equals("fcm")) {
            a(paramIntent);
          }
          this.O = new vp();
          this.O.b = "ADMsg";
          return;
        }
        this.O = ((vp)new Gson().fromJson(paramIntent.getStringExtra("AlarmData"), vp.class));
      }
    }
  }
  
  protected void onPause()
  {
    super.onPause();
    if (this.w != null) {
      this.w.d_();
    }
    if (Build.VERSION.SDK_INT < 14) {
      yi.a(this);
    }
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    if (this.au.a(paramInt, paramArrayOfString, paramArrayOfInt)) {
      return;
    }
    super.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
  }
  
  protected void onRestart()
  {
    super.onRestart();
  }
  
  protected void onRestoreInstanceState(Bundle paramBundle)
  {
    super.onRestoreInstanceState(paramBundle);
    ot.az = paramBundle.getInt("loginType");
  }
  
  protected void onResume()
  {
    super.onResume();
    if (ot.bg)
    {
      if ((ot.W) && (!ot.bx))
      {
        if (this.ad) {
          r();
        }
        p();
        a(1);
        if (this.x == null)
        {
          this.x = new os(this, this, this);
          if (this.j != null)
          {
            if (ot.b > ot.c)
            {
              int i1 = ot.b;
              ot.b = ot.c;
              ot.c = i1 - ot.bl;
            }
            this.j.addView(this.x, new AbsoluteLayout.LayoutParams(ot.b, ot.c, 0, 0));
          }
          this.x.e();
        }
        else
        {
          if (this.j != null)
          {
            this.j.removeView(this.x);
            this.j.addView(this.x);
          }
          this.x.a();
          this.x.setVisibility(0);
          this.x.bringToFront();
        }
        this.x.g();
        this.x.setGesturePswLockState(2);
        a(1);
        ot.bg = false;
      }
      else
      {
        if (this.i != null)
        {
          this.i = ((NotificationManager)getSystemService("notification"));
          this.i.cancelAll();
        }
        if (this.w != null) {
          this.w.a(2, false);
        }
        if (!ot.W)
        {
          ad();
          af();
        }
        ot.bg = false;
        ot.ar.i();
      }
      ot.X = false;
      ld.c("-----------onBaseCompleteGesturePsw()----------", new Object[0]);
    }
    if (this.w != null) {
      this.w.c_();
    }
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putInt("loginType", ot.az);
  }
  
  protected void onStart()
  {
    super.onStart();
  }
  
  protected void onStop()
  {
    super.onStop();
  }
  
  public void p()
  {
    if (this.ac != null) {
      this.ac.disable();
    }
  }
  
  public void q()
  {
    if (ot.bx) {
      return;
    }
    if (this.ac != null) {
      this.ac.enable();
    }
  }
  
  public void r()
  {
    if (ot.bx) {
      return;
    }
    this.ae = true;
    if (!this.ad)
    {
      a(6);
      this.ad = true;
      this.af = false;
      return;
    }
    a(7);
    this.ad = false;
    this.ag = false;
  }
  
  public void s()
  {
    if (this.i != null)
    {
      this.i = ((NotificationManager)getSystemService("notification"));
      this.i.cancelAll();
    }
    if (this.l != null)
    {
      this.l.g(true);
      this.l.F();
    }
    if (this.F != null) {
      this.F.b();
    }
    if (this.H != null) {
      this.H.b(this);
    }
    if (this.G != null) {
      stopService(this.G);
    }
    if (this.K != null)
    {
      this.K.d();
      this.K.e();
      this.K.g();
    }
    if ((this.x != null) && (this.x.getVisibility() == 0) && (this.w != null)) {
      this.w.l();
    }
  }
  
  public void t()
  {
    Object localObject1 = fl.b();
    Object localObject2;
    if (!((fl)localObject1).d())
    {
      localObject2 = ot.a(getString(2131494025), 0);
      ((fl)localObject1).a(ot.h(this), (String)localObject2, ot.an);
    }
    ((fl)localObject1).a("GetVersion", null, new fl.b()
    {
      public void a(String paramAnonymousString1, int paramAnonymousInt, String paramAnonymousString2)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(MainViewActivity.this.getPackageName());
        localStringBuilder.append(",update--->");
        localStringBuilder.append(ot.bq);
        ld.c(localStringBuilder.toString(), new Object[0]);
        if (!ot.bq) {
          return;
        }
        if ((paramAnonymousString1.compareTo("GetVersion") == 0) && (paramAnonymousInt == 0))
        {
          ot.bb = paramAnonymousString2;
          if (MainViewActivity.this.a(paramAnonymousString2, ot.an)) {
            MainViewActivity.g(MainViewActivity.this).sendEmptyMessage(4097);
          }
        }
      }
    });
    localObject1 = ot.a(getResources().getString(2131494025), 0);
    if (!fg.a.a())
    {
      localObject2 = fg.a;
      String str1 = ot.h(this);
      String str2 = ot.an;
      String str3 = ot.s;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(ot.w);
      localStringBuilder.append("/");
      localStringBuilder.append(ot.y);
      ((fg)localObject2).a(str1, (String)localObject1, str2, str3, localStringBuilder.toString());
    }
    localObject1 = ot.j(this);
    int i1 = ot.b;
    int i2 = ot.c;
    if (localObject1 != null)
    {
      i1 = localObject1[0];
      i2 = localObject1[1];
    }
    fg.a.a(i1, i2, new fg.a()
    {
      public void a(int paramAnonymousInt) {}
      
      public void a(int paramAnonymousInt, vw.a paramAnonymousA)
      {
        paramAnonymousA = MainViewActivity.this;
        boolean bool;
        if (paramAnonymousInt == 0) {
          bool = true;
        } else {
          bool = false;
        }
        MainViewActivity.e(paramAnonymousA, bool);
      }
    });
  }
  
  public void u()
  {
    new xm(this, new xl()
    {
      public void d_(int paramAnonymousInt)
      {
        if (paramAnonymousInt == 512) {
          MainViewActivity.this.w();
        }
      }
      
      public void e_(int paramAnonymousInt) {}
      
      public void f_(int paramAnonymousInt) {}
    }).a(getResources().getString(2131493745), getResources().getString(2131493744), getResources().getString(2131493743), 512);
  }
  
  public void v()
  {
    qa localQa = new qa(this, this);
    this.j.addView(localQa);
    localQa.setCallback(new qa.a()
    {
      public void a()
      {
        MainViewActivity.i(MainViewActivity.this);
        MainViewActivity.h(MainViewActivity.this);
        MainViewActivity.g(MainViewActivity.this).sendEmptyMessage(4099);
      }
    });
    if (ot.F)
    {
      localQa.a(getResources().getString(2131493727), getResources().getString(2131493726), getResources().getString(2131492947), getResources().getString(2131493361), 515);
      return;
    }
    localQa.a(getResources().getString(2131493727), getResources().getString(2131493726), getResources().getString(2131493728), 515);
  }
  
  public void w()
  {
    startService(new Intent(this, MyDownloadService.class));
  }
  
  public int x()
  {
    return this.z;
  }
  
  public int y()
  {
    return this.y.getStreamVolume(3);
  }
  
  void z()
  {
    if (!ot.bo) {
      return;
    }
    if (!ot.d("appraise.txt")) {
      return;
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(ot.s);
    ((StringBuilder)localObject).append("appraise.txt");
    localObject = ot.a(((StringBuilder)localObject).toString());
    int i1 = 0;
    if ((localObject != null) && (!((String)localObject).equals("")))
    {
      int i2 = ((String)localObject).indexOf("\r\n");
      if (i2 == -1) {
        return;
      }
      localObject = ((String)localObject).substring(0, i2);
      i2 = ((String)localObject).indexOf("@");
      if (i2 == -1) {
        return;
      }
      if (Integer.parseInt(((String)localObject).substring(0, i2)) == 1) {
        i1 = 1;
      }
      if (i1 != 0) {
        return;
      }
      long l1 = Long.parseLong(((String)localObject).substring("@".length() + i2));
      if ((System.currentTimeMillis() - l1) / 1000L > 604800L) {
        A();
      }
      return;
    }
    d(false);
  }
  
  class a
    implements sr.a
  {
    private String b = "";
    private vp c = null;
    
    private a() {}
    
    private boolean b(String paramString)
    {
      return (this.b != null) && (paramString != null) && (!TextUtils.isEmpty(this.b)) && (!TextUtils.isEmpty(paramString)) && (this.b.equals(paramString));
    }
    
    public void a(final int paramInt1, String paramString, int paramInt2)
    {
      if (!b(paramString)) {
        return;
      }
      MainViewActivity.g(MainViewActivity.this).post(new Runnable()
      {
        public void run()
        {
          if (paramInt1 == 0)
          {
            MainViewActivity.this.a(MainViewActivity.this, MainViewActivity.this.getResources().getString(2131493561));
            return;
          }
          MainViewActivity.this.a(MainViewActivity.this, MainViewActivity.this.getResources().getString(2131493559));
        }
      });
    }
    
    public void a(final int paramInt, String paramString, final byte[] paramArrayOfByte)
    {
      MainViewActivity.y(MainViewActivity.this).cancel();
      if (!b(paramString)) {
        return;
      }
      paramString = new StringBuilder();
      paramString.append("-----mainviewactivity-------onGetFaceImage---------code = ");
      paramString.append(paramInt);
      ld.c(paramString.toString(), new Object[0]);
      if (MainViewActivity.f(MainViewActivity.this) == null) {
        return;
      }
      MainViewActivity.g(MainViewActivity.this).post(new Runnable()
      {
        public void run()
        {
          MainViewActivity.f(MainViewActivity.this).e();
          if (paramInt == 0)
          {
            MainViewActivity.f(MainViewActivity.this).setImageData(paramArrayOfByte);
            MainViewActivity.f(MainViewActivity.this).h();
            return;
          }
          if (paramInt == 536871033) {
            MainViewActivity.this.a(MainViewActivity.this, MainViewActivity.this.getResources().getString(2131493252));
          }
        }
      });
    }
    
    void a(String paramString)
    {
      this.b = paramString;
    }
    
    void a(vp paramVp)
    {
      this.c = paramVp;
    }
  }
}
