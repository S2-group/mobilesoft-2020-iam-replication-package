package com.fastcharger.batterysaver.fastcharging.activity;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.support.v4.app.r.c;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.eyalbira.loadingdots.LoadingDots;
import com.fastcharger.batterysaver.fastcharging.utils.a;
import com.fastcharger.batterysaver.fastcharging.utils.c;
import com.fastcharger.batterysaver.fastcharging.views.BatteryVerticalView;
import java.util.Iterator;
import java.util.List;
import me.itangqi.waveloadingview.WaveLoadingView;

public class FastChargerActivity
  extends AppCompatActivity
  implements View.OnClickListener
{
  private WaveLoadingView A;
  private WaveLoadingView B;
  private LoadingDots C;
  private LoadingDots D;
  private LinearLayout E;
  private LinearLayout F;
  private RelativeLayout G;
  private LinearLayout H;
  private LinearLayout I;
  private LinearLayout J;
  private LinearLayout K;
  private LinearLayout L;
  private LinearLayout M;
  private LinearLayout N;
  private RelativeLayout O;
  private RelativeLayout P;
  private RelativeLayout Q;
  private RelativeLayout R;
  private RelativeLayout S;
  private RelativeLayout T;
  private ImageView U;
  private ImageView V;
  private ImageView W;
  private ImageView X;
  private ImageView Y;
  private ImageView Z;
  private WifiManager aa;
  private BluetoothAdapter ab;
  private int ac;
  private ContentResolver ad;
  private Window ae;
  private AudioManager af;
  private int ag;
  private TextView ah;
  private TextView ai;
  private TextView aj;
  private BatteryVerticalView ak;
  private NotificationManager al;
  private BroadcastReceiver am = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      int i = paramAnonymousIntent.getIntExtra("level", -1);
      int j = paramAnonymousIntent.getIntExtra("scale", -1);
      if ((i != -1) && (j != -1))
      {
        FastChargerActivity.this.j = ((int)(i / j * 100.0F));
        paramAnonymousContext = FastChargerActivity.a(FastChargerActivity.this);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(FastChargerActivity.this.j);
        localStringBuilder.append(" %");
        paramAnonymousContext.setText(localStringBuilder.toString());
        FastChargerActivity.b(FastChargerActivity.this).setBatteryPct(FastChargerActivity.this.j);
      }
      i = paramAnonymousIntent.getIntExtra("plugged", 0);
      if (i != 4) {
        switch (i)
        {
        default: 
          i = 2131623982;
          break;
        case 2: 
          i = 2131623983;
          break;
        case 1: 
          i = 2131623981;
          break;
        }
      } else {
        i = 2131623984;
      }
      FastChargerActivity.c(FastChargerActivity.this).setText(FastChargerActivity.this.getString(i));
      i = paramAnonymousIntent.getIntExtra("status", -1);
      if ((i != 2) && (i != 5)) {
        i = 0;
      } else {
        i = 1;
      }
      if (i != 0)
      {
        FastChargerActivity.d(FastChargerActivity.this).setText(2131623996);
        FastChargerActivity.e(FastChargerActivity.this).setImageResource(2131165338);
        FastChargerActivity.f(FastChargerActivity.this).setImageResource(2131165340);
        FastChargerActivity.g(FastChargerActivity.this).setImageResource(2131165356);
        FastChargerActivity.h(FastChargerActivity.this).setVisibility(0);
        FastChargerActivity.i(FastChargerActivity.this).setVisibility(0);
        FastChargerActivity.j(FastChargerActivity.this).setVisibility(0);
        if ((FastChargerActivity.this.j >= 0) && (FastChargerActivity.this.j <= 79))
        {
          FastChargerActivity.k(FastChargerActivity.this).setVisibility(4);
          FastChargerActivity.h(FastChargerActivity.this).setProgressValue(FastChargerActivity.this.j * 100 / 79);
          FastChargerActivity.l(FastChargerActivity.this).setText(2131624044);
          FastChargerActivity.m(FastChargerActivity.this).setVisibility(4);
          FastChargerActivity.n(FastChargerActivity.this).setVisibility(4);
          FastChargerActivity.i(FastChargerActivity.this).setProgressValue(-10);
          FastChargerActivity.o(FastChargerActivity.this).setText(2131624085);
          FastChargerActivity.p(FastChargerActivity.this).setVisibility(4);
          FastChargerActivity.q(FastChargerActivity.this).setVisibility(4);
          FastChargerActivity.j(FastChargerActivity.this).setProgressValue(-10);
          FastChargerActivity.r(FastChargerActivity.this).setText(2131624085);
        }
        else if ((FastChargerActivity.this.j >= 80) && (FastChargerActivity.this.j <= 94))
        {
          FastChargerActivity.k(FastChargerActivity.this).setVisibility(0);
          FastChargerActivity.h(FastChargerActivity.this).setProgressValue(110);
          FastChargerActivity.h(FastChargerActivity.this).setWaveColor(-1);
          FastChargerActivity.l(FastChargerActivity.this).setText(2131624023);
          FastChargerActivity.m(FastChargerActivity.this).setVisibility(0);
          FastChargerActivity.n(FastChargerActivity.this).setVisibility(4);
          FastChargerActivity.i(FastChargerActivity.this).setProgressValue(FastChargerActivity.this.j * 100 / 94);
          FastChargerActivity.o(FastChargerActivity.this).setText(2131624044);
          FastChargerActivity.p(FastChargerActivity.this).setVisibility(4);
          FastChargerActivity.q(FastChargerActivity.this).setVisibility(4);
          FastChargerActivity.j(FastChargerActivity.this).setProgressValue(-10);
          FastChargerActivity.r(FastChargerActivity.this).setText(2131624085);
        }
        else if ((FastChargerActivity.this.j >= 95) && (FastChargerActivity.this.j <= 98))
        {
          FastChargerActivity.k(FastChargerActivity.this).setVisibility(0);
          FastChargerActivity.h(FastChargerActivity.this).setProgressValue(110);
          FastChargerActivity.h(FastChargerActivity.this).setWaveColor(-1);
          FastChargerActivity.l(FastChargerActivity.this).setText(2131624023);
          FastChargerActivity.m(FastChargerActivity.this).setVisibility(4);
          FastChargerActivity.n(FastChargerActivity.this).setVisibility(0);
          FastChargerActivity.i(FastChargerActivity.this).setProgressValue(110);
          FastChargerActivity.i(FastChargerActivity.this).setWaveColor(-1);
          FastChargerActivity.o(FastChargerActivity.this).setText(2131624023);
          FastChargerActivity.p(FastChargerActivity.this).setVisibility(0);
          FastChargerActivity.q(FastChargerActivity.this).setVisibility(4);
          FastChargerActivity.j(FastChargerActivity.this).setProgressValue(FastChargerActivity.this.j * 100 / 98);
          FastChargerActivity.r(FastChargerActivity.this).setText(2131624044);
        }
        else if (FastChargerActivity.this.j >= 99)
        {
          FastChargerActivity.k(FastChargerActivity.this).setVisibility(0);
          FastChargerActivity.h(FastChargerActivity.this).setProgressValue(110);
          FastChargerActivity.h(FastChargerActivity.this).setWaveColor(-1);
          FastChargerActivity.l(FastChargerActivity.this).setText(2131624023);
          FastChargerActivity.m(FastChargerActivity.this).setVisibility(4);
          FastChargerActivity.n(FastChargerActivity.this).setVisibility(0);
          FastChargerActivity.i(FastChargerActivity.this).setProgressValue(110);
          FastChargerActivity.i(FastChargerActivity.this).setWaveColor(-1);
          FastChargerActivity.o(FastChargerActivity.this).setText(2131624023);
          FastChargerActivity.p(FastChargerActivity.this).setVisibility(4);
          FastChargerActivity.q(FastChargerActivity.this).setVisibility(0);
          FastChargerActivity.j(FastChargerActivity.this).setProgressValue(110);
          FastChargerActivity.j(FastChargerActivity.this).setWaveColor(-1);
          FastChargerActivity.r(FastChargerActivity.this).setText(2131624023);
        }
      }
      else
      {
        FastChargerActivity.d(FastChargerActivity.this).setText(2131624072);
        FastChargerActivity.k(FastChargerActivity.this).setVisibility(4);
        FastChargerActivity.h(FastChargerActivity.this).setVisibility(4);
        FastChargerActivity.e(FastChargerActivity.this).setImageResource(2131165337);
        FastChargerActivity.l(FastChargerActivity.this).setText(2131624085);
        FastChargerActivity.m(FastChargerActivity.this).setVisibility(4);
        FastChargerActivity.n(FastChargerActivity.this).setVisibility(4);
        FastChargerActivity.i(FastChargerActivity.this).setVisibility(4);
        FastChargerActivity.f(FastChargerActivity.this).setImageResource(2131165339);
        FastChargerActivity.o(FastChargerActivity.this).setText(2131624085);
        FastChargerActivity.p(FastChargerActivity.this).setVisibility(4);
        FastChargerActivity.q(FastChargerActivity.this).setVisibility(4);
        FastChargerActivity.j(FastChargerActivity.this).setVisibility(4);
        FastChargerActivity.g(FastChargerActivity.this).setImageResource(2131165355);
        FastChargerActivity.r(FastChargerActivity.this).setText(2131624085);
      }
      FastChargerActivity.s(FastChargerActivity.this).setText(FastChargerActivity.a(FastChargerActivity.this, FastChargerActivity.this.j));
      FastChargerActivity.t(FastChargerActivity.this);
    }
  };
  int j;
  private ImageView l;
  private ImageView m;
  private ImageView n;
  private ImageView o;
  private ImageView p;
  private ImageView q;
  private ImageView r;
  private ImageView s;
  private ImageView t;
  private TextView u;
  private TextView v;
  private TextView w;
  private TextView x;
  private TextView y;
  private WaveLoadingView z;
  
  public FastChargerActivity() {}
  
  private void b(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      paramInt = -1;
      break;
    case 3: 
      paramInt = 40000;
      break;
    case 2: 
      paramInt = 30000;
      break;
    case 1: 
      paramInt = 20000;
      break;
    case 0: 
      paramInt = 10000;
    }
    Settings.System.putInt(getContentResolver(), "screen_off_timeout", paramInt);
  }
  
  private String c(int paramInt)
  {
    if ((paramInt < 100) && (paramInt > 97)) {
      return "00h10m";
    }
    if (paramInt == 100) {
      return "0h00m";
    }
    if ((paramInt > 95) && (paramInt <= 97)) {
      return "00h05m";
    }
    if ((paramInt > 90) && (paramInt <= 95)) {
      return "00h20m";
    }
    if ((paramInt > 80) && (paramInt <= 90)) {
      return "00h28m";
    }
    if ((paramInt > 70) && (paramInt <= 80)) {
      return "00h49m";
    }
    if ((paramInt > 60) && (paramInt <= 70)) {
      return "01h05m";
    }
    if ((paramInt > 50) && (paramInt <= 60)) {
      return "01h27m";
    }
    if ((paramInt > 40) && (paramInt <= 50)) {
      return "01h47m";
    }
    if ((paramInt > 30) && (paramInt <= 40)) {
      return "02h00m";
    }
    if ((paramInt > 20) && (paramInt <= 30)) {
      return "02h20m";
    }
    if ((paramInt > 10) && (paramInt <= 20)) {
      return "02h40m";
    }
    if ((paramInt > 0) && (paramInt <= 10)) {
      return "03h09m";
    }
    return null;
  }
  
  private void k()
  {
    this.t = ((ImageView)findViewById(2131296388));
    this.ak = ((BatteryVerticalView)findViewById(2131296317));
    this.l = ((ImageView)findViewById(2131296380));
    this.m = ((ImageView)findViewById(2131296315));
    this.u = ((TextView)findViewById(2131296559));
    this.n = ((ImageView)findViewById(2131296383));
    this.o = ((ImageView)findViewById(2131296368));
    this.v = ((TextView)findViewById(2131296563));
    this.p = ((ImageView)findViewById(2131296384));
    this.q = ((ImageView)findViewById(2131296369));
    this.w = ((TextView)findViewById(2131296564));
    this.r = ((ImageView)findViewById(2131296387));
    this.s = ((ImageView)findViewById(2131296370));
    this.x = ((TextView)findViewById(2131296565));
    this.z = ((WaveLoadingView)findViewById(2131296580));
    this.A = ((WaveLoadingView)findViewById(2131296581));
    this.B = ((WaveLoadingView)findViewById(2131296582));
    this.C = ((LoadingDots)findViewById(2131296307));
    this.D = ((LoadingDots)findViewById(2131296308));
    this.E = ((LinearLayout)findViewById(2131296419));
    this.E.setVisibility(8);
    this.F = ((LinearLayout)findViewById(2131296416));
    this.G = ((RelativeLayout)findViewById(2131296464));
    this.H = ((LinearLayout)findViewById(2131296413));
    this.I = ((LinearLayout)findViewById(2131296420));
    this.J = ((LinearLayout)findViewById(2131296407));
    this.K = ((LinearLayout)findViewById(2131296408));
    this.L = ((LinearLayout)findViewById(2131296405));
    this.M = ((LinearLayout)findViewById(2131296412));
    this.N = ((LinearLayout)findViewById(2131296417));
    this.O = ((RelativeLayout)findViewById(2131296469));
    this.U = ((ImageView)findViewById(2131296389));
    this.P = ((RelativeLayout)findViewById(2131296460));
    this.V = ((ImageView)findViewById(2131296382));
    this.Q = ((RelativeLayout)findViewById(2131296459));
    this.W = ((ImageView)findViewById(2131296381));
    this.R = ((RelativeLayout)findViewById(2131296457));
    this.X = ((ImageView)findViewById(2131296378));
    this.S = ((RelativeLayout)findViewById(2131296463));
    this.Y = ((ImageView)findViewById(2131296385));
    this.T = ((RelativeLayout)findViewById(2131296467));
    this.Z = ((ImageView)findViewById(2131296386));
    this.aj = ((TextView)findViewById(2131296560));
    this.y = ((TextView)findViewById(2131296566));
    this.ah = ((TextView)findViewById(2131296571));
    this.ai = ((TextView)findViewById(2131296569));
  }
  
  private void l()
  {
    this.l.setOnClickListener(this);
    this.m.setOnClickListener(this);
    this.G.setOnClickListener(this);
    this.H.setOnClickListener(this);
    this.I.setOnClickListener(this);
    this.J.setOnClickListener(this);
    this.K.setOnClickListener(this);
    this.L.setOnClickListener(this);
    this.M.setOnClickListener(this);
    this.N.setOnClickListener(this);
    this.t.setOnClickListener(this);
  }
  
  @SuppressLint({"WifiManagerLeak"})
  private void m()
  {
    this.aa = ((WifiManager)getSystemService("wifi"));
    if ((!k) && (this.aa == null)) {
      throw new AssertionError();
    }
    if (this.aa.isWifiEnabled())
    {
      this.O.setBackgroundResource(2131165290);
      this.U.setImageResource(2131165361);
      this.aa.setWifiEnabled(false);
      return;
    }
    this.O.setBackgroundResource(2131165291);
    this.U.setImageResource(2131165362);
    this.aa.setWifiEnabled(true);
  }
  
  private void n()
  {
    this.ab = BluetoothAdapter.getDefaultAdapter();
    if (this.ab.isEnabled())
    {
      this.P.setBackgroundResource(2131165290);
      this.V.setImageResource(2131165332);
      this.ab.disable();
      return;
    }
    this.P.setBackgroundResource(2131165292);
    this.V.setImageResource(2131165333);
    this.ab.enable();
  }
  
  private void o()
  {
    if (this.ac > 20)
    {
      Settings.System.putInt(this.ad, "screen_brightness", 20);
      localLayoutParams = this.ae.getAttributes();
      localLayoutParams.screenBrightness = 20.0F;
      this.ae.setAttributes(localLayoutParams);
      this.Q.setBackgroundResource(2131165290);
      this.W.setImageResource(2131165330);
      this.ac = 20;
      return;
    }
    Settings.System.putInt(this.ad, "screen_brightness_mode", 0);
    Settings.System.putInt(this.ad, "screen_brightness", 254);
    WindowManager.LayoutParams localLayoutParams = this.ae.getAttributes();
    localLayoutParams.screenBrightness = 254.0F;
    this.ae.setAttributes(localLayoutParams);
    this.Q.setBackgroundResource(2131165293);
    this.W.setImageResource(2131165331);
    this.ac = 254;
  }
  
  private void p()
  {
    if (this.ag == 0)
    {
      this.T.setBackgroundResource(2131165290);
      this.Z.setImageResource(2131165349);
      this.ag = 10000;
      return;
    }
    if (this.ag == 10000)
    {
      this.T.setBackgroundResource(2131165295);
      this.Z.setImageResource(2131165352);
      b(1);
      this.ag = 20000;
      return;
    }
    if (this.ag == 20000)
    {
      this.T.setBackgroundResource(2131165295);
      this.Z.setImageResource(2131165354);
      b(2);
      this.ag = 30000;
      return;
    }
    if (this.ag == 30000)
    {
      this.T.setBackgroundResource(2131165295);
      this.Z.setImageResource(2131165353);
      b(3);
      this.ag = 40000;
      return;
    }
    if (this.ag == 40000)
    {
      this.T.setBackgroundResource(2131165295);
      this.Z.setImageResource(2131165351);
      b(0);
      this.ag = 0;
    }
  }
  
  private void q()
  {
    switch (this.af.getRingerMode())
    {
    default: 
      return;
    case 2: 
      this.Y.setImageResource(2131165359);
      this.S.setBackgroundResource(2131165290);
      this.af.setRingerMode(0);
      return;
    case 1: 
      this.Y.setImageResource(2131165350);
      this.S.setBackgroundResource(2131165294);
      this.af.setRingerMode(2);
      return;
    }
    this.Y.setImageResource(2131165358);
    this.af.setRingerMode(1);
    this.S.setBackgroundResource(2131165290);
  }
  
  private void r()
  {
    Animation localAnimation = AnimationUtils.loadAnimation(getApplicationContext(), 2130771985);
    this.G.startAnimation(localAnimation);
    localAnimation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        FastChargerActivity.u(FastChargerActivity.this).setVisibility(4);
        FastChargerActivity.v(FastChargerActivity.this).setVisibility(0);
        FastChargerActivity.w(FastChargerActivity.this).setVisibility(8);
        FastChargerActivity.x(FastChargerActivity.this);
      }
      
      public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
      
      public void onAnimationStart(Animation paramAnonymousAnimation) {}
    });
  }
  
  private int s()
  {
    Object localObject = (LocationManager)getSystemService("location");
    if ((!k) && (localObject == null)) {
      throw new AssertionError();
    }
    localObject = Boolean.valueOf(((LocationManager)localObject).isProviderEnabled("gps"));
    if ((c.b(this)) && (((Boolean)localObject).booleanValue()) && (!c.a(this))) {
      return 3;
    }
    if (((c.b(this)) && (((Boolean)localObject).booleanValue())) || ((c.b(this)) && (!c.a(this))) || ((((Boolean)localObject).booleanValue()) && (!c.a(this)))) {
      return 2;
    }
    if ((!c.b(this)) && (!((Boolean)localObject).booleanValue()) && (c.a(this))) {
      return 0;
    }
    return 1;
  }
  
  private void t()
  {
    Object localObject2 = getPackageManager().getInstalledApplications(0);
    Object localObject1 = (ActivityManager)getApplicationContext().getSystemService("activity");
    localObject2 = ((List)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject2).next();
      if ((localApplicationInfo.flags & 0x1) != 1)
      {
        if ((!k) && (localObject1 == null)) {
          throw new AssertionError();
        }
        ((ActivityManager)localObject1).killBackgroundProcesses(localApplicationInfo.packageName);
      }
    }
    this.aa = ((WifiManager)getApplicationContext().getSystemService("wifi"));
    if ((!k) && (this.aa == null)) {
      throw new AssertionError();
    }
    this.aa.setWifiEnabled(false);
    this.O.setBackgroundResource(2131165290);
    this.U.setImageResource(2131165361);
    this.ab = BluetoothAdapter.getDefaultAdapter();
    if ((this.ab != null) && (this.ab.isEnabled()))
    {
      this.ab.disable();
      this.P.setBackgroundResource(2131165290);
      this.V.setImageResource(2131165332);
    }
    if (this.ac > 20)
    {
      Settings.System.putInt(this.ad, "screen_brightness", 20);
      localObject1 = this.ae.getAttributes();
      ((WindowManager.LayoutParams)localObject1).screenBrightness = 20.0F;
      this.ae.setAttributes((WindowManager.LayoutParams)localObject1);
      this.Q.setBackgroundResource(2131165290);
      this.W.setImageResource(2131165330);
      this.ac = 20;
    }
    if ((Build.VERSION.SDK_INT >= 23) && (!this.al.isNotificationPolicyAccessGranted()))
    {
      startActivity(new Intent("android.settings.NOTIFICATION_POLICY_ACCESS_SETTINGS"));
    }
    else
    {
      this.af.setRingerMode(0);
      this.S.setBackgroundResource(2131165290);
      this.Y.setImageResource(2131165358);
    }
    this.ag = 10000;
    this.T.setBackgroundResource(2131165295);
    this.Z.setImageResource(2131165352);
  }
  
  private void u()
  {
    this.aa = ((WifiManager)getApplicationContext().getSystemService("wifi"));
    if ((!k) && (this.aa == null)) {
      throw new AssertionError();
    }
    if (this.aa.isWifiEnabled())
    {
      this.O.setBackgroundResource(2131165291);
      this.U.setImageResource(2131165362);
    }
    else
    {
      this.O.setBackgroundResource(2131165290);
      this.U.setImageResource(2131165361);
    }
    this.ab = BluetoothAdapter.getDefaultAdapter();
    if (this.ab != null) {
      if (this.ab.isEnabled())
      {
        this.P.setBackgroundResource(2131165292);
        this.V.setImageResource(2131165333);
      }
      else
      {
        this.P.setBackgroundResource(2131165290);
        this.V.setImageResource(2131165332);
      }
    }
    if (this.ac > 20)
    {
      this.Q.setBackgroundResource(2131165293);
      this.W.setImageResource(2131165331);
    }
    else
    {
      this.Q.setBackgroundResource(2131165290);
      this.W.setImageResource(2131165330);
    }
    if (this.ag == 0)
    {
      this.T.setBackgroundResource(2131165290);
      this.Z.setImageResource(2131165349);
    }
    else if (this.ag == 10000)
    {
      this.T.setBackgroundResource(2131165295);
      this.Z.setImageResource(2131165352);
    }
    else if (this.ag == 20000)
    {
      this.T.setBackgroundResource(2131165295);
      this.Z.setImageResource(2131165354);
    }
    else if (this.ag == 30000)
    {
      this.T.setBackgroundResource(2131165295);
      this.Z.setImageResource(2131165353);
    }
    else if (this.ag == 40000)
    {
      this.T.setBackgroundResource(2131165295);
      this.Z.setImageResource(2131165351);
    }
    switch (this.af.getRingerMode())
    {
    default: 
      break;
    case 2: 
      this.Y.setImageResource(2131165358);
      this.S.setBackgroundResource(2131165290);
      break;
    case 1: 
      this.Y.setImageResource(2131165359);
      this.S.setBackgroundResource(2131165294);
      break;
    case 0: 
      this.Y.setImageResource(2131165350);
      this.S.setBackgroundResource(2131165294);
    }
    if (c.a(this))
    {
      this.R.setBackgroundResource(2131165293);
      this.X.setImageResource(2131165328);
      return;
    }
    this.R.setBackgroundResource(2131165290);
    this.X.setImageResource(2131165327);
  }
  
  private void v()
  {
    NotificationManager localNotificationManager1 = (NotificationManager)getSystemService("notification");
    Object localObject = new Intent(this, FastChargerActivity.class);
    ((Intent)localObject).setFlags(268468224);
    localObject = PendingIntent.getActivity(this, 0, (Intent)localObject, 0);
    if (Build.VERSION.SDK_INT >= 26)
    {
      NotificationChannel localNotificationChannel = new NotificationChannel("chanelId", "my channel", 3);
      localNotificationChannel.setDescription("channel description");
      NotificationManager localNotificationManager2 = (NotificationManager)getSystemService(NotificationManager.class);
      if ((!k) && (localNotificationManager2 == null)) {
        throw new AssertionError();
      }
      localNotificationManager2.createNotificationChannel(localNotificationChannel);
    }
    localObject = new r.c(this, "chanelId").a(2131165366).a(getString(2131624074)).b("Tap to turn on").b(0).a(true).a((PendingIntent)localObject).a(2131165366, "YES", (PendingIntent)localObject);
    if ((!k) && (localNotificationManager1 == null)) {
      throw new AssertionError();
    }
    localNotificationManager1.notify(1, ((r.c)localObject).b());
  }
  
  public void onBackPressed()
  {
    startActivity(new Intent(this, HomeActivity.class));
    finish();
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131296380)
    {
      if (getSharedPreferences("FAST_CHARGING", 0).getInt("showingAds", 0) != 1) {
        startActivity(new Intent(this, DialogVip.class).putExtra("close", 1));
      }
      startActivity(new Intent(this, InformationActivity.class));
      return;
    }
    if (paramView.getId() == 2131296315)
    {
      startActivity(new Intent(this, SettingActivity.class));
      return;
    }
    if (paramView.getId() == 2131296464)
    {
      r();
      return;
    }
    if (paramView.getId() == 2131296413)
    {
      paramView = new Intent(this, NotificationActivity.class);
      paramView.putExtra("SetValue", 1);
      startActivity(paramView);
      return;
    }
    if (paramView.getId() == 2131296388)
    {
      paramView = new Intent(this, NotificationActivity.class);
      paramView.putExtra("SetValue", 1);
      startActivity(paramView);
      return;
    }
    if (paramView.getId() == 2131296420)
    {
      if (getSharedPreferences("FAST_CHARGING", 0).getInt("showingAds", 0) != 1) {
        c.a(this, "/21617116612/337571547263745");
      }
      m();
      return;
    }
    if (paramView.getId() == 2131296407)
    {
      if (getSharedPreferences("FAST_CHARGING", 0).getInt("showingAds", 0) != 1) {
        c.a(this, "/21617116612/337571547263745");
      }
      n();
      return;
    }
    if (paramView.getId() == 2131296408)
    {
      if (getSharedPreferences("FAST_CHARGING", 0).getInt("showingAds", 0) != 1) {
        c.a(this, "/21617116612/337571547263745");
      }
      o();
      return;
    }
    if (paramView.getId() == 2131296405)
    {
      paramView = new Intent("android.settings.SETTINGS");
      paramView.setFlags(268435456);
      startActivity(paramView);
      return;
    }
    if (paramView.getId() == 2131296412)
    {
      if (getSharedPreferences("FAST_CHARGING", 0).getInt("showingAds", 0) != 1) {
        c.a(this, "/21617116612/337571547263745");
      }
      if ((Build.VERSION.SDK_INT >= 23) && (!this.al.isNotificationPolicyAccessGranted()))
      {
        startActivity(new Intent("android.settings.NOTIFICATION_POLICY_ACCESS_SETTINGS"));
        return;
      }
      q();
      return;
    }
    if (paramView.getId() == 2131296417) {
      p();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getWindow().setFlags(1024, 1024);
    setContentView(2131492893);
    this.al = ((NotificationManager)getSystemService("notification"));
    this.af = ((AudioManager)getSystemService("audio"));
    k();
    l();
  }
  
  protected void onPause()
  {
    super.onPause();
  }
  
  @SuppressLint({"SetTextI18n"})
  protected void onResume()
  {
    super.onResume();
    this.ad = getContentResolver();
    this.ae = getWindow();
    if (Build.VERSION.SDK_INT >= 23)
    {
      if (Settings.System.canWrite(this)) {
        try
        {
          Settings.System.putInt(this.ad, "screen_brightness_mode", 0);
          this.ag = Settings.System.getInt(this.ad, "screen_off_timeout");
          if (this.ag > 40000)
          {
            b(3);
            this.ag = 40000;
          }
          final Handler localHandler1 = new Handler();
          localHandler1.post(new Runnable()
          {
            public void run()
            {
              try
              {
                FastChargerActivity.b(FastChargerActivity.this, Settings.System.getInt(FastChargerActivity.y(FastChargerActivity.this), "screen_brightness"));
              }
              catch (Settings.SettingNotFoundException localSettingNotFoundException)
              {
                localSettingNotFoundException.printStackTrace();
              }
              localHandler1.postDelayed(this, 1000L);
              FastChargerActivity.z(FastChargerActivity.this);
            }
          });
        }
        catch (Settings.SettingNotFoundException localSettingNotFoundException1)
        {
          localSettingNotFoundException1.printStackTrace();
        }
      }
    }
    else {
      try
      {
        Settings.System.putInt(this.ad, "screen_brightness_mode", 0);
        this.ac = Settings.System.getInt(this.ad, "screen_brightness");
        this.ag = Settings.System.getInt(this.ad, "screen_off_timeout");
        if (this.ag > 40000)
        {
          b(3);
          this.ag = 40000;
        }
        final Handler localHandler2 = new Handler();
        localHandler2.post(new Runnable()
        {
          public void run()
          {
            try
            {
              FastChargerActivity.b(FastChargerActivity.this, Settings.System.getInt(FastChargerActivity.y(FastChargerActivity.this), "screen_brightness"));
            }
            catch (Settings.SettingNotFoundException localSettingNotFoundException)
            {
              localSettingNotFoundException.printStackTrace();
            }
            localHandler2.postDelayed(this, 1000L);
            FastChargerActivity.z(FastChargerActivity.this);
          }
        });
      }
      catch (Settings.SettingNotFoundException localSettingNotFoundException2)
      {
        localSettingNotFoundException2.printStackTrace();
      }
    }
    if (s() > 0)
    {
      this.H.setEnabled(true);
      this.H.setVisibility(0);
      TextView localTextView = this.aj;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(s());
      localStringBuilder.append("");
      localTextView.setText(localStringBuilder.toString());
      return;
    }
    this.H.setEnabled(false);
    this.H.setVisibility(8);
  }
  
  protected void onStart()
  {
    super.onStart();
    this.j = a.a(a.a, this);
    this.ak.setBatteryPct(this.j);
    registerReceiver(this.am, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
  }
  
  protected void onStop()
  {
    super.onStop();
    unregisterReceiver(this.am);
  }
}
