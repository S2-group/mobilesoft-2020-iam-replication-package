package com.newgen.alwayson;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.preference.TwoStatePreference;
import android.provider.Settings.Secure;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.afollestad.materialdialogs.f.a;
import com.afollestad.materialdialogs.prefs.MaterialListPreference;
import com.github.a.b.a.a;
import com.github.a.b.a.b;
import com.google.android.gms.ads.c.a;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.g.a;
import com.google.android.gms.location.l;
import com.newgen.alwayson.activities.BackgroundPicker;
import com.newgen.alwayson.activities.Picker;
import com.newgen.alwayson.services.StarterService;
import com.newgen.alwayson.views.SeekBarPreference;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class e
  extends PreferenceFragment
  implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener, com.google.android.gms.ads.reward.c, b
{
  public static com.google.android.gms.ads.reward.b p;
  public static com.google.android.gms.ads.h q;
  private com.google.android.gms.location.b A;
  private LocationRequest B;
  boolean i;
  Button j;
  Button k;
  Button l;
  Button m;
  Button n;
  Button o;
  ProgressBar r;
  Dialog s;
  TextView t;
  private View v;
  private com.newgen.alwayson.c.e w;
  private Context x;
  private Intent y;
  private boolean z;
  
  public e() {}
  
  private void a(Intent paramIntent)
  {
    if (this.x.getPackageManager().queryIntentActivities(paramIntent, 65536).size() > 0) {
      startActivity(paramIntent);
    }
  }
  
  @SuppressLint({"MissingPermission"})
  private void a(final ProgressBar paramProgressBar)
  {
    com.google.android.gms.location.f.b(this.x).f().a(new com.google.android.gms.d.e()
    {
      public void a(Location paramAnonymousLocation)
      {
        if (paramAnonymousLocation != null)
        {
          double d1 = paramAnonymousLocation.getLatitude();
          double d2 = paramAnonymousLocation.getLongitude();
          Object localObject = new StringBuilder();
          ((StringBuilder)localObject).append(Double.toString(paramAnonymousLocation.getLatitude()));
          ((StringBuilder)localObject).append(", ");
          ((StringBuilder)localObject).append(Double.toString(paramAnonymousLocation.getLongitude()));
          localObject = ((StringBuilder)localObject).toString();
          Geocoder localGeocoder = new Geocoder(e.a(e.this), Locale.getDefault());
          try
          {
            paramAnonymousLocation = localGeocoder.getFromLocation(paramAnonymousLocation.getLatitude(), paramAnonymousLocation.getLongitude(), 1);
            if (paramAnonymousLocation.size() > 0)
            {
              paramProgressBar.setVisibility(8);
              e.this.findPreference("weather_city").getSharedPreferences().edit().putString("weather_city", String.valueOf(d1)).apply();
              e.this.findPreference("weather_city").setSummary(((Address)paramAnonymousLocation.get(0)).getLocality());
              e.this.findPreference("weather_cityB").getSharedPreferences().edit().putString("weather_cityB", String.valueOf(d2)).apply();
              e.this.findPreference("weather_cityB").setSummary((CharSequence)localObject);
              if ((e.this.s != null) && (e.this.s.isShowing()))
              {
                e.this.s.dismiss();
                return;
              }
            }
          }
          catch (IOException paramAnonymousLocation)
          {
            paramAnonymousLocation.printStackTrace();
          }
        }
      }
    }).a(new com.google.android.gms.d.d()
    {
      public void a(Exception paramAnonymousException)
      {
        paramProgressBar.setVisibility(8);
        Toast.makeText(e.a(e.this), e.this.getString(2131624098), 1).show();
      }
    });
  }
  
  public static void a(String paramString, Context paramContext)
  {
    try
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("market://details?id=");
      localStringBuilder.append(paramString);
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
      return;
    }
    catch (Exception localException)
    {
      StringBuilder localStringBuilder;
      for (;;) {}
    }
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://play.google.com/store/apps/details?id=");
    localStringBuilder.append(paramString);
    com.newgen.alwayson.c.i.b(paramContext, localStringBuilder.toString());
  }
  
  private boolean a(Context paramContext, boolean paramBoolean)
  {
    String str = Settings.Secure.getString(paramContext.getContentResolver(), "enabled_notification_listeners");
    paramContext = paramContext.getPackageName();
    if ((str != null) && (str.contains(paramContext))) {
      return true;
    }
    ((SwitchPreference)findPreference("notifications_alerts")).setChecked(false);
    if ((com.newgen.alwayson.c.i.a()) && (paramBoolean))
    {
      paramContext = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
      paramContext.setFlags(268435456);
    }
    for (;;)
    {
      a(paramContext);
      this.i = true;
      com.newgen.alwayson.activities.PreferencesActivity.i = true;
      return false;
      if (!paramBoolean) {
        break;
      }
      paramContext = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
    }
    return false;
  }
  
  private void h()
  {
    getActivity().stopService(this.y);
    getActivity().startService(this.y);
  }
  
  private void i()
  {
    try
    {
      new a.a(getActivity()).a(getString(2131624227)).b(getString(2131624354)).a(true).b(true).a(2131230968).d(getString(2131624240)).d(2131099690).b(new a.b()
      {
        public void a(final com.github.a.b.a paramAnonymousA)
        {
          if (e.f(e.this))
          {
            paramAnonymousA = new Dialog(e.this.getActivity());
            paramAnonymousA.requestWindowFeature(1);
            ((Window)Objects.requireNonNull(paramAnonymousA.getWindow())).setBackgroundDrawable(null);
            paramAnonymousA.setContentView(2131427403);
            paramAnonymousA.setCancelable(false);
            e.this.r = ((ProgressBar)paramAnonymousA.findViewById(2131296560));
            e.this.r.setVisibility(0);
            paramAnonymousA.show();
            new Handler().postDelayed(new Runnable()
            {
              public void run()
              {
                e.this.r.setVisibility(8);
                paramAnonymousA.cancel();
                if (e.p.a())
                {
                  e.p.b();
                  return;
                }
                if (e.q.a())
                {
                  e.q.b();
                  return;
                }
                Toast.makeText(e.this.getActivity(), "OOPS! Could not fetch AD. Please try again!!", 1).show();
              }
            }, 5000L);
            return;
          }
          Toast.makeText(e.this.getActivity(), e.this.getString(2131624066), 1).show();
        }
      }).c(getString(2131624228)).b(2131099691).c(17170443).a(new a.b()
      {
        public void a(com.github.a.b.a paramAnonymousA)
        {
          com.newgen.alwayson.b.a.a(e.this.getActivity()).a();
        }
      }).b();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private boolean j()
  {
    if (android.support.v4.content.a.checkSelfPermission(getActivity(), "android.permission.ACCESS_FINE_LOCATION") == 0) {
      return true;
    }
    k();
    return false;
  }
  
  private void k()
  {
    ActivityCompat.requestPermissions(getActivity(), new String[] { "android.permission.ACCESS_FINE_LOCATION" }, 1);
  }
  
  private void l()
  {
    if ((this.i) && (a(this.x, false))) {
      ((TwoStatePreference)findPreference("notifications_alerts")).setChecked(true);
    }
    if (((MaterialListPreference)findPreference("stop_delay")).getValue().equals("0"))
    {
      findPreference("stop_delay").setSummary(2131624336);
      return;
    }
    findPreference("stop_delay").setSummary("%s");
  }
  
  private void m()
  {
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        try
        {
          e.b(e.this).b().edit().putBoolean("clicked", false).apply();
        }
        catch (Exception localException1)
        {
          localException1.printStackTrace();
          e.b(e.this).b().edit().remove("clicked").apply();
          e.b(e.this).b().edit().putBoolean("clicked", false).apply();
        }
        try
        {
          e.b(e.this).b().edit().putInt("watchface_clock", 1).apply();
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
          e.b(e.this).b().edit().remove("watchface_clock").apply();
          e.b(e.this).b().edit().putInt("watchface_clock", 1).apply();
        }
        try
        {
          e.b(e.this).b().edit().putInt("choose_background", 0).apply();
        }
        catch (Exception localException3)
        {
          localException3.printStackTrace();
          e.b(e.this).b().edit().remove("choose_background").apply();
          e.b(e.this).b().edit().putInt("choose_background", 0).apply();
        }
        try
        {
          e.b(e.this).b().edit().putBoolean("draw_screen", false).apply();
        }
        catch (Exception localException4)
        {
          localException4.printStackTrace();
          e.b(e.this).b().edit().remove("draw_screen").apply();
          e.b(e.this).b().edit().putBoolean("draw_screen", false).apply();
        }
        try
        {
          if (e.b(e.this).b().getString("swipe_up_action", "0").equals("5")) {
            e.b(e.this).b().edit().putString("swipe_up_action", "0").apply();
          }
          if (e.b(e.this).b().getString("swipe_down_action", "0").equals("5")) {
            e.b(e.this).b().edit().putString("swipe_down_action", "0").apply();
          }
          if (e.b(e.this).b().getString("swipe_left_action", "0").equals("5")) {
            e.b(e.this).b().edit().putString("swipe_left_action", "0").apply();
          }
          if (e.b(e.this).b().getString("swipe_right_action", "0").equals("5")) {
            e.b(e.this).b().edit().putString("swipe_right_action", "0").apply();
          }
        }
        catch (Exception localException5)
        {
          localException5.printStackTrace();
        }
        try
        {
          e.b(e.this).b().edit().putBoolean("show_badges", false).apply();
          return;
        }
        catch (Exception localException6)
        {
          localException6.printStackTrace();
          e.b(e.this).b().edit().remove("show_badges").apply();
          e.b(e.this).b().edit().putBoolean("show_badges", false).apply();
        }
      }
    }, 300000L);
  }
  
  private void n()
  {
    if (Picker.i) {}
    try
    {
      getActivity().sendBroadcast(new Intent("restartPicker"));
    }
    catch (Exception localException1)
    {
      for (;;) {}
    }
    Toast.makeText(getActivity(), getString(2131624129), 1).show();
    if (BackgroundPicker.j) {}
    try
    {
      getActivity().sendBroadcast(new Intent("restartBgPicker"));
    }
    catch (Exception localException2)
    {
      for (;;) {}
    }
    Toast.makeText(getActivity(), getString(2131624129), 1).show();
    if (this.w.b().getBoolean("clicked", true)) {
      Toast.makeText(getActivity(), getString(2131623966), 1).show();
    }
  }
  
  private boolean o()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)getActivity().getSystemService("connectivity");
    boolean bool = false;
    NetworkInfo[] arrayOfNetworkInfo = new NetworkInfo[0];
    if (localConnectivityManager != null) {
      arrayOfNetworkInfo = localConnectivityManager.getAllNetworkInfo();
    }
    int i6 = arrayOfNetworkInfo.length;
    int i2 = 0;
    int i4 = 0;
    int i5;
    for (int i1 = 0; i2 < i6; i1 = i5)
    {
      localConnectivityManager = arrayOfNetworkInfo[i2];
      int i3 = i4;
      if (localConnectivityManager.getTypeName().equalsIgnoreCase("WIFI"))
      {
        i3 = i4;
        if (localConnectivityManager.isConnected()) {
          i3 = 1;
        }
      }
      i5 = i1;
      if (localConnectivityManager.getTypeName().equalsIgnoreCase("MOBILE"))
      {
        i5 = i1;
        if (localConnectivityManager.isConnected()) {
          i5 = 1;
        }
      }
      i2 += 1;
      i4 = i3;
    }
    if ((i4 != 0) || (i1 != 0)) {
      bool = true;
    }
    return bool;
  }
  
  private void p()
  {
    if ((!com.newgen.alwayson.c.i.a(this.x, "com.ngmobile.amoledpluginprobeta")) && (!this.w.m) && (this.z)) {
      p.a("ca-app-pub-2456991391194362/4383921854", new c.a().a());
    }
  }
  
  public void a() {}
  
  public void a(int paramInt)
  {
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        e.h(e.this);
      }
    }, 3500L);
  }
  
  public void a(com.google.android.gms.ads.reward.a paramA)
  {
    try
    {
      this.w.b().edit().putBoolean("clicked", true).apply();
      return;
    }
    catch (Exception paramA)
    {
      paramA.printStackTrace();
      this.w.b().edit().remove("clicked").apply();
      this.w.b().edit().putBoolean("clicked", true).apply();
    }
  }
  
  public boolean a(String paramString)
  {
    Iterator localIterator = this.x.getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public void b() {}
  
  public void c() {}
  
  public void d()
  {
    p();
    m();
    n();
  }
  
  public void e() {}
  
  public void f() {}
  
  protected void g()
  {
    Object localObject = LocationRequest.a();
    this.B = new LocationRequest();
    this.B.a(100);
    if (this.w.e) {
      this.B.a(3600000L);
    }
    localObject = new g.a().a((LocationRequest)localObject);
    ((g.a)localObject).a(this.B);
    com.google.android.gms.location.g localG = ((g.a)localObject).a();
    ((g.a)localObject).a(true);
    com.google.android.gms.location.f.a(this.x).a(localG);
    if ((ActivityCompat.checkSelfPermission(getActivity(), "android.permission.ACCESS_FINE_LOCATION") != 0) && (ActivityCompat.checkSelfPermission(getActivity(), "android.permission.ACCESS_COARSE_LOCATION") != 0))
    {
      ActivityCompat.requestPermissions(getActivity(), new String[] { "android.permission.ACCESS_FINE_LOCATION" }, 1);
      return;
    }
    com.google.android.gms.location.f.b(this.x).a(this.B, new com.google.android.gms.location.d()
    {
      public void a(LocationResult paramAnonymousLocationResult)
      {
        e.a(e.this, e.this.r);
      }
    }, Looper.myLooper());
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    paramIntent = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Activity result");
    localStringBuilder.append(paramInt2);
    com.newgen.alwayson.c.i.a(paramIntent, localStringBuilder.toString());
    if (paramInt1 == 4)
    {
      paramIntent = (CheckBoxPreference)findPreference("proximity_to_lock");
      boolean bool;
      if (paramInt2 == -1) {
        bool = true;
      } else {
        bool = false;
      }
      paramIntent.setChecked(bool);
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    addPreferencesFromResource(2131820545);
    this.x = getActivity().getApplicationContext();
    this.w = new com.newgen.alwayson.c.e(this.x);
    this.w.a();
    this.z = true;
    q = new com.google.android.gms.ads.h(getActivity());
    q.a("ca-app-pub-2456991391194362/8615930460");
    if ((!com.newgen.alwayson.c.i.a(this.x, "com.ngmobile.amoledpluginprobeta")) && (!this.w.m) && (this.z)) {
      q.a(new c.a().a());
    }
    q.a(new com.google.android.gms.ads.a()
    {
      public void a(int paramAnonymousInt)
      {
        if ((!com.newgen.alwayson.c.i.a(e.a(e.this), "com.ngmobile.amoledpluginprobeta")) && (!e.b(e.this).m) && (e.c(e.this))) {
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              e.q.a(new c.a().a());
            }
          }, 5000L);
        }
      }
      
      public void b()
      {
        try
        {
          e.b(e.this).b().edit().putBoolean("clicked", true).apply();
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          e.b(e.this).b().edit().remove("clicked").apply();
          e.b(e.this).b().edit().putBoolean("clicked", true).apply();
        }
      }
      
      public void c()
      {
        e.q.a(new c.a().a());
        e.d(e.this);
        e.e(e.this);
      }
    });
    p = com.google.android.gms.ads.i.a(getActivity());
    p.a(this);
    p();
    if (getActivity().getSharedPreferences("PREFERENCE", 0).getBoolean("isFirstRun", true))
    {
      try
      {
        this.w.b().edit().putBoolean("clicked", false).apply();
      }
      catch (Exception paramBundle)
      {
        paramBundle.printStackTrace();
        this.w.b().edit().remove("clicked").apply();
        this.w.b().edit().putBoolean("clicked", false).apply();
      }
      getActivity().getSharedPreferences("PREFERENCE", 0).edit().putBoolean("isFirstRun", false).apply();
    }
    findPreference("weather_city").setOnPreferenceClickListener(this);
    findPreference("enabled").setOnPreferenceChangeListener(this);
    findPreference("persistent_notification").setOnPreferenceChangeListener(this);
    findPreference("raise_to_wake").setOnPreferenceChangeListener(this);
    findPreference("wave_to_wake").setOnPreferenceChangeListener(this);
    findPreference("glance_display").setOnPreferenceChangeListener(this);
    findPreference("proximity_to_lock").setOnPreferenceChangeListener(this);
    findPreference("startafterlock").setOnPreferenceChangeListener(this);
    findPreference("notifications_alerts").setOnPreferenceChangeListener(this);
    findPreference("stop_delay").setOnPreferenceChangeListener(this);
    findPreference("fahrenheit").setOnPreferenceChangeListener(this);
    findPreference("round_circle").setOnPreferenceChangeListener(this);
    findPreference("screen_button_light").setOnPreferenceChangeListener(this);
    findPreference("weather_state").setOnPreferenceChangeListener(this);
    findPreference("color_wave").setOnPreferenceChangeListener(this);
    findPreference("glance_display").setOnPreferenceChangeListener(this);
    findPreference("brightness_mode").setOnPreferenceChangeListener(this);
    findPreference("draw_screen").setOnPreferenceChangeListener(this);
    findPreference("force_foreground").setOnPreferenceChangeListener(this);
    findPreference("show_badges").setOnPreferenceChangeListener(this);
    ((SeekBarPreference)findPreference("font_size")).a(1);
    ((SeekBarPreference)findPreference("glance_delay")).a(1);
    ((SeekBarPreference)findPreference("brightness")).a(1);
    ((SeekBarPreference)findPreference("clock_size")).a(40);
    ((SeekBarPreference)findPreference("memo_font_size")).a(1);
    ((SeekBarPreference)findPreference("icon_size")).a(1);
    ((SeekBarPreference)findPreference("msgbox_size")).a(5);
    ((SeekBarPreference)findPreference("msgbox_alpha")).a(10);
    ((SeekBarPreference)findPreference("swipe_sensitivity")).a(1);
    if (!this.w.K)
    {
      paramBundle = findPreference("glance_delay");
      paramBundle.setEnabled(false);
      paramBundle.setShouldDisableView(true);
    }
    if (this.w.b)
    {
      paramBundle = findPreference("msgbox_alpha");
      paramBundle.setEnabled(false);
      paramBundle.setShouldDisableView(true);
    }
    if (this.w.F) {
      try
      {
        paramBundle = findPreference("color_wavea");
        paramBundle.setEnabled(false);
        paramBundle.setShouldDisableView(true);
        paramBundle = findPreference("color_waveb");
        paramBundle.setEnabled(false);
        paramBundle.setShouldDisableView(true);
        paramBundle = findPreference("color_wavec");
        paramBundle.setEnabled(false);
        paramBundle.setShouldDisableView(true);
        paramBundle = findPreference("color_waved");
        paramBundle.setEnabled(false);
        paramBundle.setShouldDisableView(true);
      }
      catch (Exception paramBundle)
      {
        paramBundle.printStackTrace();
      }
    }
    if (Build.VERSION.SDK_INT > 25) {
      try
      {
        paramBundle = findPreference("startafterlock");
        paramBundle.setEnabled(false);
        paramBundle.setShouldDisableView(true);
      }
      catch (Exception paramBundle)
      {
        paramBundle.printStackTrace();
      }
    }
    if (Build.VERSION.SDK_INT < 26) {
      try
      {
        paramBundle = findPreference("finger_print");
        paramBundle.setEnabled(false);
        paramBundle.setShouldDisableView(true);
      }
      catch (Exception paramBundle)
      {
        paramBundle.printStackTrace();
      }
    }
    try
    {
      this.o = ((Button)getActivity().findViewById(2131296438));
      this.o.setBackground(getResources().getDrawable(2131230902));
      if (this.w.l) {
        this.o.setVisibility(8);
      }
      this.o.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          new AlertDialog.Builder(e.this.getActivity()).setMessage(e.this.getText(2131624100)).setPositiveButton(e.this.getString(2131624102), i.a).setNegativeButton(e.this.getString(2131624101), new j(this)).show();
        }
      });
    }
    catch (Exception paramBundle)
    {
      paramBundle.printStackTrace();
    }
    try
    {
      this.j = ((Button)getActivity().findViewById(2131296643));
      this.j.setBackground(getResources().getDrawable(2131230947));
      this.j.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          com.newgen.alwayson.activities.PreferencesActivity.i = true;
          paramAnonymousView = new Intent();
          paramAnonymousView.addFlags(268435456);
          paramAnonymousView.setClass(e.this.getActivity(), SettingActivity.class);
          e.this.startActivity(paramAnonymousView);
        }
      });
    }
    catch (Exception paramBundle)
    {
      paramBundle.printStackTrace();
    }
    try
    {
      this.k = ((Button)getActivity().findViewById(2131296706));
      this.k.setBackground(getResources().getDrawable(2131230985));
      this.k.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          com.newgen.alwayson.activities.PreferencesActivity.i = true;
          paramAnonymousView = new Intent(e.a(e.this), Picker.class);
          paramAnonymousView.setFlags(268435456);
          paramAnonymousView.putExtra("grid_type", 1);
          e.a(e.this).startActivity(paramAnonymousView);
        }
      });
    }
    catch (Exception paramBundle)
    {
      paramBundle.printStackTrace();
    }
    try
    {
      this.l = ((Button)getActivity().findViewById(2131296704));
      this.l.setBackground(getResources().getDrawable(2131230920));
      this.l.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          com.newgen.alwayson.activities.PreferencesActivity.i = true;
          paramAnonymousView = new Intent(e.a(e.this), BackgroundPicker.class);
          paramAnonymousView.setFlags(268435456);
          e.a(e.this).startActivity(paramAnonymousView);
        }
      });
    }
    catch (Exception paramBundle)
    {
      paramBundle.printStackTrace();
    }
    try
    {
      this.m = ((Button)getActivity().findViewById(2131296707));
      this.m.setBackground(getResources().getDrawable(2131230951));
      this.m.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = new com.newgen.alwayson.views.c(e.a(e.this), 2130903051);
          new f.a(e.this.getActivity()).a(2131624291).c(-16777216).b(-1).a(paramAnonymousView, new k(this)).c();
        }
      });
    }
    catch (Exception paramBundle)
    {
      paramBundle.printStackTrace();
    }
    try
    {
      this.n = ((Button)getActivity().findViewById(2131296705));
      this.n.setBackground(getResources().getDrawable(2131230945));
      this.n.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          com.newgen.alwayson.activities.PreferencesActivity.i = true;
          paramAnonymousView = new Intent(e.a(e.this), Picker.class);
          paramAnonymousView.setFlags(268435456);
          paramAnonymousView.putExtra("grid_type", 2);
          e.a(e.this).startActivity(paramAnonymousView);
        }
      });
    }
    catch (Exception paramBundle)
    {
      paramBundle.printStackTrace();
    }
    PreferenceManager.getDefaultSharedPreferences(this.x).registerOnSharedPreferenceChangeListener(this);
    paramBundle = new String[5];
    paramBundle[0] = "double_tap_action";
    paramBundle[1] = "swipe_up_action";
    paramBundle[2] = "swipe_down_action";
    paramBundle[3] = "swipe_left_action";
    paramBundle[4] = "swipe_right_action";
    int i2 = paramBundle.length;
    int i1 = 0;
    while (i1 < i2)
    {
      findPreference(paramBundle[i1]).setOnPreferenceChangeListener(new f(this));
      i1 += 1;
    }
    a(this.x, false);
    this.y = new Intent(getActivity().getApplicationContext(), StarterService.class);
    com.newgen.alwayson.c.i.a(String.valueOf(((MaterialListPreference)findPreference("rules")).getValue()), " Selected");
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    p.b(getActivity());
    this.z = false;
  }
  
  public void onPause()
  {
    super.onPause();
    p.a(getActivity());
  }
  
  public boolean onPreferenceChange(Preference paramPreference, Object paramObject)
  {
    this.w.a();
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(paramPreference.getKey());
    ((StringBuilder)localObject1).append(" Value:");
    ((StringBuilder)localObject1).append(paramObject.toString());
    com.newgen.alwayson.c.i.a("Preference change", ((StringBuilder)localObject1).toString());
    if (paramPreference.getKey().equals("notifications_alerts"))
    {
      paramPreference = (Boolean)paramObject;
      if (paramPreference.booleanValue()) {
        return a(this.x, true);
      }
      if (!paramPreference.booleanValue()) {
        ((TwoStatePreference)findPreference("notifications_alerts_preview")).setChecked(false);
      }
      return true;
    }
    if (paramPreference.getKey().equals("raise_to_wake"))
    {
      if (!com.newgen.alwayson.c.i.d(this.x)) {
        h();
      }
      h();
    }
    if (paramPreference.getKey().equals("wave_to_wake"))
    {
      if (!com.newgen.alwayson.c.i.d(this.x)) {
        h();
      }
      h();
    }
    if (paramPreference.getKey().equals("glance_display"))
    {
      if (!com.newgen.alwayson.c.i.d(this.x)) {
        h();
      }
      h();
    }
    if (paramPreference.getKey().equals("persistent_notification"))
    {
      if (!((Boolean)paramObject).booleanValue()) {
        Snackbar.a(this.v, 2131624373, 10000).a(2131623965, new g(this, paramPreference)).b();
      }
      h();
    }
    if (paramPreference.getKey().equals("force_foreground")) {
      h();
    }
    if (paramPreference.getKey().equals("enabled"))
    {
      this.x.sendBroadcast(new Intent("service toggled"));
      h();
    }
    if (paramPreference.getKey().equals("proximity_to_lock")) {
      return (DevicePolicyManager)this.x.getSystemService("device_policy") != null;
    }
    if ((paramPreference.getKey().equals("startafterlock")) && (!((Boolean)paramObject).booleanValue())) {
      Snackbar.a(this.v, 2131624374, 10000).a(2131623965, new h(paramPreference)).b();
    }
    if ((paramPreference.getKey().equals("weather_state")) && (((Boolean)paramObject).booleanValue())) {
      j();
    }
    Object localObject2;
    if (paramPreference.getKey().equals("color_wave"))
    {
      localObject1 = (Boolean)paramObject;
      if (((Boolean)localObject1).booleanValue())
      {
        try
        {
          localObject1 = findPreference("color_wavea");
          ((Preference)localObject1).setEnabled(false);
          ((Preference)localObject1).setShouldDisableView(true);
          localObject1 = findPreference("color_waveb");
          ((Preference)localObject1).setEnabled(false);
          ((Preference)localObject1).setShouldDisableView(true);
          localObject1 = findPreference("color_wavec");
          ((Preference)localObject1).setEnabled(false);
          ((Preference)localObject1).setShouldDisableView(true);
          localObject1 = findPreference("color_waved");
          ((Preference)localObject1).setEnabled(false);
          ((Preference)localObject1).setShouldDisableView(true);
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
      else if (!localException.booleanValue())
      {
        localObject2 = findPreference("color_wavea");
        ((Preference)localObject2).setEnabled(true);
        ((Preference)localObject2).setShouldDisableView(false);
        localObject2 = findPreference("color_waveb");
        ((Preference)localObject2).setEnabled(true);
        ((Preference)localObject2).setShouldDisableView(false);
        localObject2 = findPreference("color_wavec");
        ((Preference)localObject2).setEnabled(true);
        ((Preference)localObject2).setShouldDisableView(false);
        localObject2 = findPreference("color_waved");
        ((Preference)localObject2).setEnabled(true);
        ((Preference)localObject2).setShouldDisableView(false);
      }
    }
    if (paramPreference.getKey().equals("brightness_mode"))
    {
      localObject2 = (Boolean)paramObject;
      if (((Boolean)localObject2).booleanValue()) {
        findPreference("msgbox_alpha").setEnabled(false);
      } else if (!((Boolean)localObject2).booleanValue()) {
        findPreference("msgbox_alpha").setEnabled(true);
      }
    }
    if (paramPreference.getKey().equals("glance_display"))
    {
      paramObject = (Boolean)paramObject;
      if (paramObject.booleanValue()) {
        findPreference("glance_delay").setEnabled(true);
      } else if (!paramObject.booleanValue()) {
        findPreference("glance_delay").setEnabled(false);
      }
    }
    if (paramPreference.getKey().equals("draw_screen")) {
      if ((!this.w.m) && (!this.w.b().getBoolean("clicked", true)) && (!com.newgen.alwayson.c.i.a(getActivity(), "com.ngmobile.amoledpluginprobeta")) && (!com.newgen.alwayson.c.i.a(getActivity(), "com.newgen.amoledpluginprobeta")))
      {
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            ((TwoStatePreference)e.this.findPreference("draw_screen")).setChecked(false);
          }
        }, 100L);
        i();
      }
      else if (ActivityCompat.checkSelfPermission(this.x, "android.permission.WRITE_EXTERNAL_STORAGE") != 0)
      {
        ActivityCompat.requestPermissions(getActivity(), new String[] { "android.permission.WRITE_EXTERNAL_STORAGE" }, 1);
      }
    }
    if (paramPreference.getKey().equals("show_badges")) {
      if ((!this.w.m) && (!this.w.b().getBoolean("clicked", true)) && (!com.newgen.alwayson.c.i.a(getActivity(), "com.ngmobile.amoledpluginprobeta")) && (!com.newgen.alwayson.c.i.a(getActivity(), "com.newgen.amoledpluginprobeta")))
      {
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            ((TwoStatePreference)e.this.findPreference("show_badges")).setChecked(false);
          }
        }, 100L);
        i();
      }
      else
      {
        com.newgen.alwayson.c.i.a("Items Owned", "Unlock Badges");
      }
    }
    if (paramPreference.getKey().equals("screen_button_light")) {}
    try
    {
      if (ActivityCompat.checkSelfPermission(this.x, "android.permission.CAMERA") == 0) {
        break label1025;
      }
      ActivityCompat.requestPermissions(getActivity(), new String[] { "android.permission.CAMERA" }, 5);
      return true;
    }
    catch (Exception paramPreference)
    {
      for (;;) {}
    }
    Toast.makeText(getActivity(), "Error: Unable to retrieve camera permission", 1).show();
    label1025:
    return true;
  }
  
  public boolean onPreferenceClick(Preference paramPreference)
  {
    if (paramPreference.getKey().equals("weather_city"))
    {
      this.s = new Dialog(getActivity());
      this.s.requestWindowFeature(1);
      ((Window)Objects.requireNonNull(this.s.getWindow())).setBackgroundDrawable(null);
      this.s.setContentView(2131427402);
      this.A = com.google.android.gms.location.f.a(getActivity());
      paramPreference = (AppCompatButton)this.s.findViewById(2131296419);
      this.r = ((ProgressBar)this.s.findViewById(2131296560));
      this.t = ((TextView)this.s.findViewById(2131296570));
      paramPreference.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          try
          {
            paramAnonymousView = (LocationManager)e.a(e.this).getSystemService("location");
            if (!e.g(e.this)) {
              break label119;
            }
            if (!((LocationManager)Objects.requireNonNull(paramAnonymousView)).isProviderEnabled("gps"))
            {
              Toast.makeText(e.a(e.this), e.this.getString(2131624368), 1).show();
              return;
            }
            e.this.t.setVisibility(8);
            e.this.g();
            e.this.r.setVisibility(0);
            return;
          }
          catch (Exception paramAnonymousView)
          {
            label119:
            for (;;) {}
          }
          Toast.makeText(e.a(e.this), e.this.getString(2131624097), 1).show();
        }
      });
      this.s.show();
    }
    return true;
  }
  
  public void onResume()
  {
    super.onResume();
    l();
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
    super.onSaveInstanceState(paramBundle);
  }
  
  public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
  {
    l();
  }
  
  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.v = getView();
    if ((!u) && (this.v == null)) {
      throw new AssertionError();
    }
    ((ListView)this.v.findViewById(16908298)).setDivider(null);
    this.w = new com.newgen.alwayson.c.e(this.x);
    this.w.a();
  }
}
