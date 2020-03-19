package com.fastcharger.batterysaver.fastcharging.activity;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.a.a;
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
import android.widget.Toast;
import com.fastcharger.batterysaver.fastcharging.broadcast.BatteryReceiver;
import com.fastcharger.batterysaver.fastcharging.utils.c;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity
  extends AppCompatActivity
  implements View.OnClickListener
{
  private LinearLayout A;
  private TextView B;
  private TextView C;
  private TextView D;
  private TextView E;
  private TextView F;
  private LinearLayout G;
  private LinearLayout H;
  private LinearLayout I;
  private LinearLayout J;
  private LinearLayout K;
  private LinearLayout L;
  private ImageView M;
  private ImageView N;
  private ImageView O;
  private ImageView P;
  private ImageView Q;
  private ImageView R;
  private ImageView S;
  private BluetoothAdapter T;
  private int U;
  private int V;
  private ContentResolver W;
  private Window X;
  private AudioManager Y;
  private WifiManager Z;
  private a.a aa;
  private ImageView ab;
  private int ac;
  private LinearLayout ad;
  private Timer ae;
  private Runnable af;
  private NotificationManager ag;
  protected RelativeLayout j;
  protected RelativeLayout k;
  protected RelativeLayout l;
  protected RelativeLayout m;
  protected RelativeLayout n;
  protected RelativeLayout o;
  protected RelativeLayout p;
  protected Integer q;
  final Handler r = new Handler();
  protected boolean s;
  protected boolean t;
  protected boolean u;
  protected boolean v;
  protected boolean w;
  public BroadcastReceiver x = new BroadcastReceiver()
  {
    @SuppressLint({"SetTextI18n"})
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      paramAnonymousContext = HomeActivity.a(HomeActivity.this);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramAnonymousIntent.getIntExtra("temperature", 0) / 10);
      localStringBuilder.append(Character.toString('°'));
      localStringBuilder.append(" C");
      paramAnonymousContext.setText(localStringBuilder.toString());
      paramAnonymousContext = HomeActivity.b(HomeActivity.this);
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramAnonymousIntent.getIntExtra("voltage", 0) / 1000.0F);
      localStringBuilder.append(Character.toString('°'));
      localStringBuilder.append(" V");
      paramAnonymousContext.setText(localStringBuilder.toString());
      HomeActivity.c(HomeActivity.this).setText(Integer.toString(paramAnonymousIntent.getIntExtra("level", 0)));
    }
  };
  private LinearLayout z;
  
  public HomeActivity() {}
  
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
  
  private void k()
  {
    TextView localTextView = (TextView)findViewById(2131296561);
    this.S = ((ImageView)findViewById(2131296388));
    this.ab = ((ImageView)findViewById(2131296315));
    this.B = ((TextView)findViewById(2131296562));
    this.C = ((TextView)findViewById(2131296557));
    this.z = ((LinearLayout)findViewById(2131296414));
    this.A = ((LinearLayout)findViewById(2131296415));
    this.D = ((TextView)findViewById(2131296568));
    this.E = ((TextView)findViewById(2131296573));
    this.F = ((TextView)findViewById(2131296558));
    this.z.setVisibility(0);
    this.G = ((LinearLayout)findViewById(2131296420));
    this.H = ((LinearLayout)findViewById(2131296407));
    this.I = ((LinearLayout)findViewById(2131296408));
    this.J = ((LinearLayout)findViewById(2131296405));
    this.K = ((LinearLayout)findViewById(2131296412));
    this.L = ((LinearLayout)findViewById(2131296417));
    this.k = ((RelativeLayout)findViewById(2131296469));
    this.M = ((ImageView)findViewById(2131296389));
    this.l = ((RelativeLayout)findViewById(2131296460));
    this.N = ((ImageView)findViewById(2131296382));
    this.m = ((RelativeLayout)findViewById(2131296459));
    this.O = ((ImageView)findViewById(2131296381));
    this.n = ((RelativeLayout)findViewById(2131296457));
    this.P = ((ImageView)findViewById(2131296378));
    this.o = ((RelativeLayout)findViewById(2131296463));
    this.Q = ((ImageView)findViewById(2131296385));
    this.p = ((RelativeLayout)findViewById(2131296467));
    this.R = ((ImageView)findViewById(2131296386));
    this.j = ((RelativeLayout)findViewById(2131296458));
    this.ad = ((LinearLayout)findViewById(2131296411));
    this.Y = ((AudioManager)getSystemService("audio"));
    if (!localTextView.getText().toString().equals(Integer.valueOf(2131624046))) {
      localTextView.setTextSize(20.0F);
    }
  }
  
  private void l()
  {
    this.ab.setOnClickListener(this);
    this.A.setOnClickListener(this);
    this.S.setOnClickListener(this);
    this.G.setOnClickListener(this);
    this.H.setOnClickListener(this);
    this.I.setOnClickListener(this);
    this.J.setOnClickListener(this);
    this.K.setOnClickListener(this);
    this.L.setOnClickListener(this);
    this.z.setOnClickListener(this);
    this.ad.setOnClickListener(this);
  }
  
  private void m()
  {
    this.ac = 0;
    registerReceiver(new BroadcastReceiver()new IntentFilter
    {
      @SuppressLint({"SetTextI18n"})
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        paramAnonymousContext.unregisterReceiver(this);
        final int i = paramAnonymousIntent.getIntExtra("level", -1);
        int j = paramAnonymousIntent.getIntExtra("scale", -1);
        if ((i != -1) && (j != -1))
        {
          j = (int)(i / j * 100.0F);
          com.fastcharger.batterysaver.fastcharging.utils.a.a(com.fastcharger.batterysaver.fastcharging.utils.a.a, j, HomeActivity.this);
          paramAnonymousContext = HomeActivity.c(HomeActivity.this);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(j);
          localStringBuilder.append("");
          paramAnonymousContext.setText(localStringBuilder.toString());
        }
        paramAnonymousContext = new StringBuilder();
        paramAnonymousContext.append(paramAnonymousIntent.getIntExtra("temperature", 0) / 10);
        paramAnonymousContext.append(Character.toString('°'));
        paramAnonymousContext.append("C");
        paramAnonymousContext = paramAnonymousContext.toString();
        HomeActivity.a(HomeActivity.this).setText(paramAnonymousContext);
        paramAnonymousContext = new StringBuilder();
        paramAnonymousContext.append(paramAnonymousIntent.getIntExtra("voltage", 0) / 1000.0F);
        paramAnonymousContext.append(" V");
        paramAnonymousContext = paramAnonymousContext.toString();
        HomeActivity.b(HomeActivity.this).setText(paramAnonymousContext);
        HomeActivity.a(HomeActivity.this, new Timer());
        HomeActivity.f(HomeActivity.this).schedule(new TimerTask()
        {
          public void run()
          {
            HomeActivity.this.runOnUiThread(new Runnable()
            {
              public void run()
              {
                if (HomeActivity.d(HomeActivity.this) < 100)
                {
                  if (HomeActivity.d(HomeActivity.this) == HomeActivity.3.1.this.a)
                  {
                    localTextView = HomeActivity.e(HomeActivity.this);
                    localStringBuilder = new StringBuilder();
                    localStringBuilder.append(HomeActivity.3.1.this.a);
                    localStringBuilder.append(" %");
                    localTextView.setText(localStringBuilder.toString());
                    HomeActivity.f(HomeActivity.this).cancel();
                    return;
                  }
                  if (HomeActivity.d(HomeActivity.this) < 100) {
                    HomeActivity.g(HomeActivity.this);
                  }
                  TextView localTextView = HomeActivity.e(HomeActivity.this);
                  StringBuilder localStringBuilder = new StringBuilder();
                  localStringBuilder.append(HomeActivity.d(HomeActivity.this));
                  localStringBuilder.append(" %");
                  localTextView.setText(localStringBuilder.toString());
                  return;
                }
                HomeActivity.e(HomeActivity.this).setText("100 %");
              }
            });
          }
        }, 200L, i);
      }
    }, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    registerReceiver(new BatteryReceiver(), new IntentFilter("android.intent.action.ACTION_POWER_CONNECTED"));
  }
  
  private void n()
  {
    Object localObject2 = getPackageManager().getInstalledApplications(0);
    Object localObject1 = (ActivityManager)getApplicationContext().getSystemService("activity");
    localObject2 = ((List)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject2).next();
      if ((localApplicationInfo.flags & 0x1) != 1)
      {
        if ((!y) && (localObject1 == null)) {
          throw new AssertionError();
        }
        ((ActivityManager)localObject1).killBackgroundProcesses(localApplicationInfo.packageName);
      }
    }
    this.Z.setWifiEnabled(false);
    this.k.setBackgroundResource(2131165290);
    this.M.setImageResource(2131165361);
    this.T = BluetoothAdapter.getDefaultAdapter();
    if ((this.T != null) && (this.T.isEnabled()))
    {
      this.l.setBackgroundResource(2131165290);
      this.N.setImageResource(2131165332);
      this.T.disable();
    }
    if (this.U > 20)
    {
      Settings.System.putInt(this.W, "screen_brightness", 20);
      localObject1 = this.X.getAttributes();
      ((WindowManager.LayoutParams)localObject1).screenBrightness = 20.0F;
      this.X.setAttributes((WindowManager.LayoutParams)localObject1);
      this.m.setBackgroundResource(2131165290);
      this.O.setImageResource(2131165330);
      this.U = 20;
    }
    if ((Build.VERSION.SDK_INT >= 23) && (!this.ag.isNotificationPolicyAccessGranted()))
    {
      startActivity(new Intent("android.settings.NOTIFICATION_POLICY_ACCESS_SETTINGS"));
    }
    else
    {
      this.Y.setRingerMode(0);
      this.o.setBackgroundResource(2131165290);
      this.Q.setImageResource(2131165358);
    }
    this.V = 10000;
    this.p.setBackgroundResource(2131165295);
    this.R.setImageResource(2131165352);
    localObject1 = AnimationUtils.loadAnimation(this, 2130771984);
    this.z.startAnimation((Animation)localObject1);
    ((Animation)localObject1).setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        HomeActivity.h(HomeActivity.this).setVisibility(4);
        HomeActivity.i(HomeActivity.this);
        Toast.makeText(HomeActivity.this, HomeActivity.this.getString(2131624047), 0).show();
      }
      
      public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
      
      public void onAnimationStart(Animation paramAnonymousAnimation) {}
    });
  }
  
  @SuppressLint({"SetTextI18n"})
  private void o()
  {
    if (s() > 0)
    {
      this.ad.setEnabled(true);
      this.ad.setVisibility(0);
      TextView localTextView = this.C;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(s());
      localStringBuilder.append("");
      localTextView.setText(localStringBuilder.toString());
      return;
    }
    this.ad.setEnabled(false);
    this.ad.setVisibility(8);
  }
  
  @SuppressLint({"WifiManagerLeak"})
  private void p()
  {
    if (this.Z.isWifiEnabled())
    {
      this.k.setBackgroundResource(2131165291);
      this.M.setImageResource(2131165362);
      this.s = true;
    }
    else
    {
      this.k.setBackgroundResource(2131165290);
      this.M.setImageResource(2131165361);
      this.s = false;
    }
    this.T = BluetoothAdapter.getDefaultAdapter();
    if (this.T != null) {
      if (this.T.isEnabled())
      {
        this.l.setBackgroundResource(2131165292);
        this.N.setImageResource(2131165333);
        this.t = true;
      }
      else
      {
        this.l.setBackgroundResource(2131165290);
        this.N.setImageResource(2131165332);
        this.t = false;
      }
    }
    if (this.U > 20)
    {
      this.m.setBackgroundResource(2131165293);
      this.O.setImageResource(2131165331);
      this.u = true;
    }
    else
    {
      this.m.setBackgroundResource(2131165290);
      this.O.setImageResource(2131165330);
      this.u = false;
    }
    if (this.V == 0)
    {
      this.p.setBackgroundResource(2131165290);
      this.R.setImageResource(2131165349);
    }
    else if (this.V == 10000)
    {
      this.p.setBackgroundResource(2131165295);
      this.R.setImageResource(2131165352);
    }
    else if (this.V == 20000)
    {
      this.p.setBackgroundResource(2131165295);
      this.R.setImageResource(2131165354);
    }
    else if (this.V == 30000)
    {
      this.p.setBackgroundResource(2131165295);
      this.R.setImageResource(2131165353);
    }
    else if (this.V == 40000)
    {
      this.p.setBackgroundResource(2131165295);
      this.R.setImageResource(2131165351);
    }
    switch (this.Y.getRingerMode())
    {
    default: 
      break;
    case 2: 
      this.Q.setImageResource(2131165359);
      this.o.setBackgroundResource(2131165294);
      break;
    case 1: 
      this.Q.setImageResource(2131165350);
      this.o.setBackgroundResource(2131165294);
      break;
    case 0: 
      this.Q.setImageResource(2131165358);
      this.o.setBackgroundResource(2131165290);
    }
    if (c.a(this))
    {
      this.n.setBackgroundResource(2131165293);
      this.P.setImageResource(2131165328);
      return;
    }
    this.n.setBackgroundResource(2131165290);
    this.P.setImageResource(2131165327);
  }
  
  private void q()
  {
    if ((!this.s) && (!this.u) && (!this.t) && (!this.v) && (!this.w))
    {
      this.z.setVisibility(8);
      return;
    }
    this.z.setVisibility(0);
  }
  
  private void r()
  {
    a.a localA = new a.a(this);
    localA.a(2131624038);
    localA.b(2131624045);
    localA.a(2131624056, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        try
        {
          paramAnonymousDialogInterface = HomeActivity.this;
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("market://details?id=");
          localStringBuilder.append(HomeActivity.this.getPackageName());
          paramAnonymousDialogInterface.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
          return;
        }
        catch (ActivityNotFoundException paramAnonymousDialogInterface)
        {
          StringBuilder localStringBuilder;
          for (;;) {}
        }
        paramAnonymousDialogInterface = HomeActivity.this;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("http://play.google.com/store/apps/details?id=");
        localStringBuilder.append(HomeActivity.this.getPackageName());
        paramAnonymousDialogInterface.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
      }
    });
    localA.b(2131624018, new DialogInterface.OnClickListener()
    {
      @SuppressLint({"NewApi"})
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        HomeActivity.m(HomeActivity.this).setRingerMode(HomeActivity.this.q.intValue());
        HomeActivity.b(HomeActivity.this, 3);
        HomeActivity.this.finish();
      }
    });
    this.aa = new a.a(this);
    this.aa.a(2131624034);
    this.aa.a(false);
    this.aa.b(2131624035);
    this.aa.a(getString(2131624043), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (Build.VERSION.SDK_INT >= 23)
        {
          paramAnonymousDialogInterface = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("package:");
          localStringBuilder.append(HomeActivity.this.getPackageName());
          paramAnonymousDialogInterface.setData(Uri.parse(localStringBuilder.toString()));
          HomeActivity.this.startActivity(paramAnonymousDialogInterface);
        }
      }
    });
  }
  
  private int s()
  {
    Object localObject = (LocationManager)getSystemService("location");
    if ((!y) && (localObject == null)) {
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
    a.a localA = new a.a(this);
    localA.a(getString(2131624018));
    localA.b(getString(2131624039)).a(false).a("Yes", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        HomeActivity.this.finish();
      }
    }).b("No", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    });
    localA.b().show();
  }
  
  public void onBackPressed()
  {
    t();
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      
    case 2131296420: 
      if (getSharedPreferences("FAST_CHARGING", 0).getInt("showingAds", 0) != 1) {
        c.a(this, "/21617116612/337571547263745");
      }
      if (this.Z.isWifiEnabled()) {
        this.Z.setWifiEnabled(false);
      } else {
        this.Z.setWifiEnabled(true);
      }
      q();
      return;
    case 2131296417: 
      if (this.V == 0)
      {
        this.p.setBackgroundResource(2131165290);
        this.R.setImageResource(2131165349);
        this.V = 10000;
        return;
      }
      if (this.V == 10000)
      {
        this.p.setBackgroundResource(2131165295);
        this.R.setImageResource(2131165352);
        b(1);
        this.V = 20000;
        return;
      }
      if (this.V == 20000)
      {
        this.p.setBackgroundResource(2131165295);
        this.R.setImageResource(2131165354);
        b(2);
        this.V = 30000;
        return;
      }
      if (this.V == 30000)
      {
        this.p.setBackgroundResource(2131165295);
        this.R.setImageResource(2131165353);
        b(3);
        this.V = 40000;
        return;
      }
      if (this.V == 40000)
      {
        this.p.setBackgroundResource(2131165295);
        this.R.setImageResource(2131165351);
        b(0);
        this.V = 0;
        return;
      }
      break;
    case 2131296415: 
      startActivity(new Intent(this, InformationActivity.class));
      return;
    case 2131296414: 
      if (getSharedPreferences("FAST_CHARGING", 0).getInt("showingAds", 0) != 1) {
        startActivity(new Intent(this, DialogVip.class).putExtra("close", 1));
      }
      n();
      return;
    case 2131296412: 
      if (getSharedPreferences("FAST_CHARGING", 0).getInt("showingAds", 0) != 1) {
        startActivity(new Intent(this, DialogVip.class).putExtra("close", 1));
      }
      if ((Build.VERSION.SDK_INT >= 23) && (!this.ag.isNotificationPolicyAccessGranted())) {
        startActivity(new Intent("android.settings.NOTIFICATION_POLICY_ACCESS_SETTINGS"));
      } else {
        switch (this.Y.getRingerMode())
        {
        default: 
          break;
        case 2: 
          this.w = false;
          this.Q.setImageResource(2131165359);
          this.o.setBackgroundResource(2131165290);
          this.Y.setRingerMode(0);
          break;
        case 1: 
          this.w = true;
          this.Q.setImageResource(2131165350);
          this.o.setBackgroundResource(2131165294);
          this.Y.setRingerMode(2);
          break;
        case 0: 
          this.w = true;
          this.Q.setImageResource(2131165358);
          this.Y.setRingerMode(1);
          this.o.setBackgroundResource(2131165290);
        }
      }
      q();
      return;
    case 2131296411: 
      paramView = new Intent(this, NotificationActivity.class);
      paramView.putExtra("SetValue", 1);
      startActivity(paramView);
      return;
    case 2131296408: 
      if (getSharedPreferences("FAST_CHARGING", 0).getInt("showingAds", 0) != 1) {
        c.a(this, "/21617116612/337571547263745");
      }
      if (this.U > 20)
      {
        Settings.System.putInt(this.W, "screen_brightness", 20);
        paramView = this.X.getAttributes();
        paramView.screenBrightness = 20.0F;
        this.X.setAttributes(paramView);
        this.m.setBackgroundResource(2131165290);
        this.O.setImageResource(2131165330);
        this.U = 20;
      }
      else
      {
        Settings.System.putInt(this.W, "screen_brightness_mode", 0);
        Settings.System.putInt(this.W, "screen_brightness", 254);
        paramView = this.X.getAttributes();
        paramView.screenBrightness = 254.0F;
        this.X.setAttributes(paramView);
        this.m.setBackgroundResource(2131165293);
        this.O.setImageResource(2131165331);
        this.U = 254;
      }
      q();
      return;
    case 2131296407: 
      if (getSharedPreferences("FAST_CHARGING", 0).getInt("showingAds", 0) != 1) {
        c.a(this, "/21617116612/337571547263745");
      }
      this.T = BluetoothAdapter.getDefaultAdapter();
      if (this.T.isEnabled()) {
        this.T.disable();
      } else {
        this.T.enable();
      }
      q();
      return;
    case 2131296405: 
      paramView = new Intent("android.settings.SETTINGS");
      paramView.setFlags(268435456);
      startActivity(paramView);
      return;
    case 2131296388: 
      paramView = new Intent(this, NotificationActivity.class);
      paramView.putExtra("SetValue", 1);
      startActivity(paramView);
      return;
    case 2131296315: 
      startActivity(new Intent(this, SettingActivity.class));
    }
  }
  
  @SuppressLint({"WifiManagerLeak"})
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    if (Build.VERSION.SDK_INT >= 21) {
      getWindow().setStatusBarColor(0);
    }
    this.Z = ((WifiManager)getSystemService("wifi"));
    this.ag = ((NotificationManager)getSystemService("notification"));
    setContentView(2131492894);
    k();
    l();
  }
  
  protected void onDestroy()
  {
    if (this.af != null) {
      this.r.removeCallbacks(this.af);
    }
    this.ae.cancel();
    super.onDestroy();
  }
  
  protected void onPause()
  {
    if (this.af != null) {
      this.r.removeCallbacks(this.af);
    }
    this.ae.cancel();
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
    this.W = getContentResolver();
    this.X = getWindow();
    r();
    if (Build.VERSION.SDK_INT >= 23)
    {
      if (Settings.System.canWrite(this)) {
        try
        {
          Settings.System.putInt(this.W, "screen_brightness_mode", 0);
          this.V = Settings.System.getInt(this.W, "screen_off_timeout");
          if (this.V > 40000)
          {
            b(3);
            this.V = 40000;
          }
          this.af = new Runnable()
          {
            public void run()
            {
              try
              {
                HomeActivity.a(HomeActivity.this, Settings.System.getInt(HomeActivity.j(HomeActivity.this), "screen_brightness"));
                HomeActivity.k(HomeActivity.this);
                HomeActivity.l(HomeActivity.this);
                HomeActivity.i(HomeActivity.this);
              }
              catch (Settings.SettingNotFoundException localSettingNotFoundException)
              {
                localSettingNotFoundException.printStackTrace();
              }
              HomeActivity.this.r.postDelayed(this, 50L);
            }
          };
          this.r.post(this.af);
        }
        catch (Settings.SettingNotFoundException localSettingNotFoundException1)
        {
          localSettingNotFoundException1.printStackTrace();
        }
      } else {
        this.aa.c();
      }
    }
    else {
      try
      {
        Settings.System.putInt(this.W, "screen_brightness_mode", 0);
        this.U = Settings.System.getInt(this.W, "screen_brightness");
        this.V = Settings.System.getInt(this.W, "screen_off_timeout");
        if (this.V > 40000)
        {
          b(3);
          this.V = 40000;
        }
        this.af = new Runnable()
        {
          public void run()
          {
            try
            {
              HomeActivity.a(HomeActivity.this, Settings.System.getInt(HomeActivity.j(HomeActivity.this), "screen_brightness"));
              HomeActivity.k(HomeActivity.this);
              HomeActivity.l(HomeActivity.this);
              HomeActivity.i(HomeActivity.this);
            }
            catch (Settings.SettingNotFoundException localSettingNotFoundException)
            {
              localSettingNotFoundException.printStackTrace();
            }
            HomeActivity.this.r.postDelayed(this, 50L);
          }
        };
        this.r.post(this.af);
      }
      catch (Settings.SettingNotFoundException localSettingNotFoundException2)
      {
        localSettingNotFoundException2.printStackTrace();
      }
    }
    o();
    m();
  }
  
  protected void onStart()
  {
    super.onStart();
    registerReceiver(this.x, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
  }
  
  protected void onStop()
  {
    super.onStop();
    if (this.af != null) {
      this.r.removeCallbacks(this.af);
    }
    this.ae.cancel();
  }
}
