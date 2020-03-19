package com.phascinate.precisevolume.activities;

import android.app.Activity;
import android.app.ActivityManager.TaskDescription;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.ApplicationInfo.DisplayNameComparator;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.b;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils.SimpleStringSplitter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import cl;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.MaterialDialog.a;
import com.afollestad.materialdialogs.MaterialDialog.d;
import com.afollestad.materialdialogs.MaterialDialog.h;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.a;
import com.phascinate.precisevolume.Analytics;
import com.phascinate.precisevolume.MainAccessibilityService;
import com.phascinate.precisevolume.PresetObject;
import com.phascinate.precisevolume.e;
import com.phascinate.precisevolume.g;
import ie.c;
import ig;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import jg;
import ji;
import jj;

public class AutomaticAppsActivity
  extends AppCompatActivity
{
  public static boolean y = false;
  int A = 0;
  private jg B;
  Analytics a;
  ig b;
  boolean c = true;
  String d = "Automatic Apps Screen";
  Activity e = this;
  Handler f;
  SharedPreferences g;
  HashMap<String, HashMap<String, String>> h;
  com.google.android.gms.ads.c i;
  AdView j;
  String k = "So8b0a5IX96CqWEUxrWK6DAjjQcA6C521PCD4Dvf";
  String l = "";
  PackageManager m;
  jj n;
  LinearLayout o;
  RecyclerView p;
  SwipeRefreshLayout q;
  boolean r = true;
  List<ApplicationInfo> s;
  ArrayList<PresetObject> t;
  cl<Integer, Drawable> u;
  Switch v;
  Snackbar w;
  boolean x = false;
  Toast z;
  
  public AutomaticAppsActivity() {}
  
  private boolean e()
  {
    boolean bool2 = false;
    String str1 = getPackageName() + "/" + MainAccessibilityService.class.getCanonicalName();
    try
    {
      i1 = Settings.Secure.getInt(this.e.getApplicationContext().getContentResolver(), "accessibility_enabled");
      TextUtils.SimpleStringSplitter localSimpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
      boolean bool1 = bool2;
      if (i1 == 1)
      {
        String str2 = Settings.Secure.getString(this.e.getApplicationContext().getContentResolver(), "enabled_accessibility_services");
        bool1 = bool2;
        if (str2 != null)
        {
          localSimpleStringSplitter.setString(str2);
          do
          {
            bool1 = bool2;
            if (!localSimpleStringSplitter.hasNext()) {
              break;
            }
          } while (!localSimpleStringSplitter.next().equalsIgnoreCase(str1));
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (Settings.SettingNotFoundException localSettingNotFoundException)
    {
      for (;;)
      {
        int i1 = 0;
      }
    }
  }
  
  public String a(ApplicationInfo paramApplicationInfo)
  {
    return String.valueOf(this.m.getApplicationLabel(paramApplicationInfo));
  }
  
  public ArrayList<String> a(ArrayList<PresetObject> paramArrayList)
  {
    paramArrayList = new ArrayList();
    Iterator localIterator = this.t.iterator();
    while (localIterator.hasNext()) {
      paramArrayList.add(g.a((PresetObject)localIterator.next()));
    }
    return paramArrayList;
  }
  
  public void a()
  {
    new Thread()
    {
      public void run()
      {
        for (;;)
        {
          ApplicationInfo localApplicationInfo;
          try
          {
            Object localObject = AutomaticAppsActivity.this.m.getInstalledApplications(128);
            AutomaticAppsActivity.this.s = new ArrayList();
            localObject = ((List)localObject).iterator();
            if (!((Iterator)localObject).hasNext()) {
              break;
            }
            localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
            if (AutomaticAppsActivity.this.m.getLaunchIntentForPackage(localApplicationInfo.packageName) == null) {
              continue;
            }
            if (AutomaticAppsActivity.this.r)
            {
              AutomaticAppsActivity.this.s.add(localApplicationInfo);
              continue;
            }
            if (!AutomaticAppsActivity.this.a(localApplicationInfo, true)) {
              continue;
            }
          }
          catch (Exception localException)
          {
            System.out.println(localException);
            return;
          }
          AutomaticAppsActivity.this.s.add(localApplicationInfo);
        }
        Collections.sort(AutomaticAppsActivity.this.s, new ApplicationInfo.DisplayNameComparator(AutomaticAppsActivity.this.m));
        if (AutomaticAppsActivity.this.n == null)
        {
          AutomaticAppsActivity.this.u = new cl(g.k);
          AutomaticAppsActivity.this.n = new jj(AutomaticAppsActivity.this.e, AutomaticAppsActivity.this.u, AutomaticAppsActivity.this.m, AutomaticAppsActivity.this.s, AutomaticAppsActivity.this.h, AutomaticAppsActivity.this.t, false);
          if ((!AutomaticAppsActivity.a(AutomaticAppsActivity.this)) || (!AutomaticAppsActivity.this.x)) {
            AutomaticAppsActivity.this.n.g = true;
          }
          AutomaticAppsActivity.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              AutomaticAppsActivity.this.o.setVisibility(8);
              AutomaticAppsActivity.this.p.setAdapter(AutomaticAppsActivity.this.n);
              AutomaticAppsActivity.this.p.setLayoutManager(new LinearLayoutManager(AutomaticAppsActivity.this.e));
            }
          });
          return;
        }
        AutomaticAppsActivity.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            AutomaticAppsActivity.this.n.c = AutomaticAppsActivity.this.s;
            jj localJj = AutomaticAppsActivity.this.n;
            if (!AutomaticAppsActivity.this.x) {}
            for (boolean bool = true;; bool = false)
            {
              localJj.g = bool;
              AutomaticAppsActivity.this.u.a();
              AutomaticAppsActivity.this.n.d = AutomaticAppsActivity.this.u;
              AutomaticAppsActivity.this.n.d();
              AutomaticAppsActivity.this.n.h = true;
              AutomaticAppsActivity.this.p.post(new Runnable()
              {
                public void run()
                {
                  AutomaticAppsActivity.this.n.h = false;
                }
              });
              if (AutomaticAppsActivity.this.q.b()) {
                AutomaticAppsActivity.this.q.setRefreshing(false);
              }
              return;
            }
          }
        });
      }
    }.start();
  }
  
  public void a(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (!this.w.e())
      {
        this.w = Snackbar.a(this.p, 2131296361, -2).a(2131296338, new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (AutomaticAppsActivity.a(AutomaticAppsActivity.this))
            {
              if (!AutomaticAppsActivity.this.x)
              {
                AutomaticAppsActivity.this.x = true;
                AutomaticAppsActivity.this.v.setChecked(true);
              }
              return;
            }
            AutomaticAppsActivity.this.x = false;
            AutomaticAppsActivity.this.b(true);
          }
        });
        this.w.c();
        this.w.b().post(new Runnable()
        {
          public void run()
          {
            AutomaticAppsActivity.this.p.setPadding(0, 0, 0, AutomaticAppsActivity.this.w.b().getHeight());
          }
        });
      }
      return;
    }
    this.w.d();
    this.p.setPadding(0, 0, 0, 0);
  }
  
  public boolean a(ApplicationInfo paramApplicationInfo, boolean paramBoolean)
  {
    if ((paramApplicationInfo.flags & 0x1) != 0)
    {
      if (paramBoolean != true) {}
    }
    else {
      return (paramApplicationInfo.flags & 0x80) != 0;
    }
    return false;
  }
  
  public void b()
  {
    this.A = this.g.getInt("appTheme", 0);
    this.l = this.g.getString("isPro", "");
    this.r = this.g.getBoolean("automaticAppsShowSystemApps", this.r);
    this.x = this.g.getBoolean("automaticAppsEnabled", this.x);
    try
    {
      this.t = ((ArrayList)e.a(this.g.getString("presetObjects", "")));
      if (this.t == null) {
        this.t = g.b(this.e);
      }
      return;
    }
    catch (Exception localException)
    {
      this.t = g.b(this.e);
    }
  }
  
  public void b(boolean paramBoolean)
  {
    if (paramBoolean) {
      y = true;
    }
    Intent localIntent = new Intent("android.settings.ACCESSIBILITY_SETTINGS");
    localIntent.addFlags(1342177280);
    startActivity(localIntent);
    this.z.setText(2131296362);
    this.z.show();
  }
  
  public void c()
  {
    SharedPreferences.Editor localEditor = this.g.edit();
    localEditor.putBoolean("automaticAppsShowSystemApps", this.r);
    localEditor.putBoolean("automaticAppsEnabled", this.x);
    try
    {
      localEditor.putString("automaticApps", e.a(this.h));
      localEditor.commit();
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public void d()
  {
    this.c = this.g.getBoolean("analyticsEnabled", true);
    if (this.c) {
      new Thread()
      {
        public void run()
        {
          try
          {
            if (AutomaticAppsActivity.this.a == null) {
              AutomaticAppsActivity.this.a = ((Analytics)AutomaticAppsActivity.this.getApplication());
            }
            if (AutomaticAppsActivity.this.b == null) {
              AutomaticAppsActivity.this.b = AutomaticAppsActivity.this.a.a();
            }
            AutomaticAppsActivity.this.b.a(AutomaticAppsActivity.this.d);
            AutomaticAppsActivity.this.b.a(new ie.c().a());
            if (AutomaticAppsActivity.b(AutomaticAppsActivity.this) == null) {
              AutomaticAppsActivity.a(AutomaticAppsActivity.this, jg.a(AutomaticAppsActivity.this.e));
            }
            return;
          }
          catch (Exception localException) {}
        }
      }.start();
    }
  }
  
  public void finish()
  {
    super.finish();
    c();
    overridePendingTransition(0, 0);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramIntent != null) && (paramIntent.getBooleanExtra("presetsUpdated", false))) {
      b();
    }
    try
    {
      this.h = ((HashMap)e.a(this.g.getString("automaticApps", "")));
      if (this.h == null) {
        this.h = new HashMap();
      }
      MainActivity.aF = true;
      this.n.f = this.t;
      this.n.e = this.h;
      this.n.d();
      this.n.h = true;
      this.p.post(new Runnable()
      {
        public void run()
        {
          AutomaticAppsActivity.this.n.h = false;
        }
      });
      return;
    }
    catch (Exception paramIntent)
    {
      for (;;)
      {
        this.h = new HashMap();
      }
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    y = false;
    this.z = Toast.makeText(this.e, "", 1);
    this.g = PreferenceManager.getDefaultSharedPreferences(this);
    d();
    this.m = this.e.getPackageManager();
    b();
    if (this.A == 1)
    {
      setTheme(2131493044);
      if (Build.VERSION.SDK_INT >= 21) {
        setTaskDescription(new ActivityManager.TaskDescription(null, null, getResources().getColor(2131689510)));
      }
      this.A = 1;
    }
    for (;;)
    {
      super.onCreate(paramBundle);
      if (!this.l.equals(this.k)) {
        finish();
      }
      setContentView(2130968606);
      if (this.A == 0) {
        getWindow().setBackgroundDrawableResource(2131689620);
      }
      try
      {
        this.h = ((HashMap)e.a(this.g.getString("automaticApps", "")));
        if (this.h == null) {
          this.h = new HashMap();
        }
        getIntent();
        if (paramBundle != null)
        {
          this.r = paramBundle.getBoolean("showSystemApps", this.r);
          y = paramBundle.getBoolean("wentToAccessibility", y);
        }
        paramBundle = (Toolbar)findViewById(2131755239);
        setSupportActionBar(paramBundle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        if (this.A != 0) {
          paramBundle.setPopupTheme(2131493095);
        }
        this.o = ((LinearLayout)findViewById(2131755157));
        this.f = new Handler();
        this.p = ((RecyclerView)findViewById(2131755156));
        this.q = ((SwipeRefreshLayout)findViewById(2131755155));
        this.w = Snackbar.a(this.p, "", -2);
        a();
        com.phascinate.precisevolume.c.a(this.p).a(new com.phascinate.precisevolume.c.a()
        {
          public void a(final RecyclerView paramAnonymousRecyclerView, final int paramAnonymousInt, final View paramAnonymousView)
          {
            int m = 0;
            for (;;)
            {
              try
              {
                paramAnonymousRecyclerView = ((ApplicationInfo)AutomaticAppsActivity.this.s.get(paramAnonymousInt)).packageName;
                if (!AutomaticAppsActivity.this.h.containsKey(paramAnonymousRecyclerView)) {
                  break label365;
                }
                i = g.a(AutomaticAppsActivity.this.h, paramAnonymousRecyclerView, "selectedPreset").intValue();
                int k = g.a(AutomaticAppsActivity.this.h, paramAnonymousRecyclerView, "workingMode").intValue();
                try
                {
                  AutomaticAppsActivity.this.t.get(i);
                  j = i;
                  if (i != -1) {
                    j = i + 1;
                  }
                  i = j;
                  if (k == 0) {
                    i = 0;
                  }
                  j = g.a(AutomaticAppsActivity.this.h, paramAnonymousRecyclerView, "selectedPresetClosed").intValue();
                  n = g.a(AutomaticAppsActivity.this.h, paramAnonymousRecyclerView, "workingModeClosed").intValue();
                }
                catch (Exception paramAnonymousView)
                {
                  try
                  {
                    int n;
                    AutomaticAppsActivity.this.t.get(j);
                    k = j;
                    if (j != -1) {
                      k = j + 1;
                    }
                    if (n == 0)
                    {
                      j = m;
                      paramAnonymousView = (Drawable)AutomaticAppsActivity.this.u.a(Integer.valueOf(paramAnonymousInt));
                      new MaterialDialog.a(AutomaticAppsActivity.this.e).a(AutomaticAppsActivity.this.a((ApplicationInfo)AutomaticAppsActivity.this.s.get(paramAnonymousInt))).a(paramAnonymousView).d(2131296500).e(g.b(AutomaticAppsActivity.this.e, 2130771974)).g(g.b(AutomaticAppsActivity.this.e, 2130771976)).p(g.a(AutomaticAppsActivity.this.e, 42)).l(2131296330).f(2131361794).a(new MaterialDialog.d()
                      {
                        public void a(final MaterialDialog paramAnonymous2MaterialDialog, View paramAnonymous2View, int paramAnonymous2Int, CharSequence paramAnonymous2CharSequence)
                        {
                          if (paramAnonymous2Int == 0)
                          {
                            paramAnonymous2MaterialDialog = new MaterialDialog.a(AutomaticAppsActivity.this.e).a(AutomaticAppsActivity.this.a((ApplicationInfo)AutomaticAppsActivity.this.s.get(paramAnonymousInt))).b(2130968616, false).a(paramAnonymousView).p(g.a(AutomaticAppsActivity.this.e, 42)).c(new MaterialDialog.h()
                            {
                              public void a(MaterialDialog paramAnonymous3MaterialDialog, DialogAction paramAnonymous3DialogAction)
                              {
                                g.b(AutomaticAppsActivity.this.h, AutomaticAppsActivity.1.1.this.c, "selectedPreset");
                                g.b(AutomaticAppsActivity.this.h, AutomaticAppsActivity.1.1.this.c, "workingMode");
                                if ((g.a(AutomaticAppsActivity.this.h, AutomaticAppsActivity.1.1.this.c, "selectedPresetClosed").intValue() == -1) && (g.a(AutomaticAppsActivity.this.h, AutomaticAppsActivity.1.1.this.c, "workingModeClosed").intValue() == -1)) {
                                  AutomaticAppsActivity.this.h.remove(AutomaticAppsActivity.1.1.this.c);
                                }
                                AutomaticAppsActivity.this.n.e = AutomaticAppsActivity.this.h;
                                AutomaticAppsActivity.this.n.e(AutomaticAppsActivity.1.1.this.a);
                              }
                            }).l(2131296330);
                            if (i != -1) {
                              paramAnonymous2MaterialDialog.j(2131296347);
                            }
                            paramAnonymous2MaterialDialog = paramAnonymous2MaterialDialog.b();
                            paramAnonymous2View = (RecyclerView)paramAnonymous2MaterialDialog.i().findViewById(2131755264);
                            ((TextView)paramAnonymous2MaterialDialog.i().findViewById(16908299)).setText(2131296501);
                            paramAnonymous2CharSequence = AutomaticAppsActivity.this.a(AutomaticAppsActivity.this.t);
                            paramAnonymous2CharSequence.add(0, AutomaticAppsActivity.this.getResources().getString(2131296482));
                            paramAnonymous2View.setAdapter(new ji(AutomaticAppsActivity.this.e, paramAnonymous2CharSequence, i));
                            paramAnonymous2View.setLayoutManager(new LinearLayoutManager(AutomaticAppsActivity.this.e));
                            com.phascinate.precisevolume.c.a(paramAnonymous2View).a(new com.phascinate.precisevolume.c.a()
                            {
                              public void a(RecyclerView paramAnonymous3RecyclerView, int paramAnonymous3Int, View paramAnonymous3View)
                              {
                                if (paramAnonymous3Int != 0)
                                {
                                  g.a(AutomaticAppsActivity.this.h, AutomaticAppsActivity.1.1.this.c, "selectedPreset", paramAnonymous3Int - 1 + "");
                                  g.a(AutomaticAppsActivity.this.h, AutomaticAppsActivity.1.1.this.c, "workingMode", "1");
                                  AutomaticAppsActivity.this.n.e = AutomaticAppsActivity.this.h;
                                  AutomaticAppsActivity.this.n.e(AutomaticAppsActivity.1.1.this.a);
                                }
                                for (;;)
                                {
                                  paramAnonymous2MaterialDialog.dismiss();
                                  return;
                                  g.a(AutomaticAppsActivity.this.h, AutomaticAppsActivity.1.1.this.c, "workingMode", "0");
                                  AutomaticAppsActivity.this.n.e = AutomaticAppsActivity.this.h;
                                  AutomaticAppsActivity.this.n.e(AutomaticAppsActivity.1.1.this.a);
                                }
                              }
                            });
                            paramAnonymous2MaterialDialog.show();
                            return;
                          }
                          paramAnonymous2MaterialDialog = new MaterialDialog.a(AutomaticAppsActivity.this.e).a(AutomaticAppsActivity.this.a((ApplicationInfo)AutomaticAppsActivity.this.s.get(paramAnonymousInt))).b(2130968616, false).a(paramAnonymousView).p(g.a(AutomaticAppsActivity.this.e, 42)).c(new MaterialDialog.h()
                          {
                            public void a(MaterialDialog paramAnonymous3MaterialDialog, DialogAction paramAnonymous3DialogAction)
                            {
                              g.b(AutomaticAppsActivity.this.h, AutomaticAppsActivity.1.1.this.c, "selectedPresetClosed");
                              g.b(AutomaticAppsActivity.this.h, AutomaticAppsActivity.1.1.this.c, "workingModeClosed");
                              if ((g.a(AutomaticAppsActivity.this.h, AutomaticAppsActivity.1.1.this.c, "selectedPreset").intValue() == -1) && (g.a(AutomaticAppsActivity.this.h, AutomaticAppsActivity.1.1.this.c, "workingMode").intValue() == -1)) {
                                AutomaticAppsActivity.this.h.remove(AutomaticAppsActivity.1.1.this.c);
                              }
                              AutomaticAppsActivity.this.n.e = AutomaticAppsActivity.this.h;
                              AutomaticAppsActivity.this.n.e(AutomaticAppsActivity.1.1.this.a);
                            }
                          }).l(2131296330);
                          if (j != -1) {
                            paramAnonymous2MaterialDialog.j(2131296347);
                          }
                          paramAnonymous2MaterialDialog = paramAnonymous2MaterialDialog.b();
                          paramAnonymous2View = (RecyclerView)paramAnonymous2MaterialDialog.i().findViewById(2131755264);
                          ((TextView)paramAnonymous2MaterialDialog.i().findViewById(16908299)).setText(2131296502);
                          paramAnonymous2CharSequence = AutomaticAppsActivity.this.a(AutomaticAppsActivity.this.t);
                          paramAnonymous2CharSequence.add(0, AutomaticAppsActivity.this.getResources().getString(2131296586));
                          paramAnonymous2View.setAdapter(new ji(AutomaticAppsActivity.this.e, paramAnonymous2CharSequence, j));
                          paramAnonymous2View.setLayoutManager(new LinearLayoutManager(AutomaticAppsActivity.this.e));
                          com.phascinate.precisevolume.c.a(paramAnonymous2View).a(new com.phascinate.precisevolume.c.a()
                          {
                            public void a(RecyclerView paramAnonymous3RecyclerView, int paramAnonymous3Int, View paramAnonymous3View)
                            {
                              if (paramAnonymous3Int != 0)
                              {
                                g.a(AutomaticAppsActivity.this.h, AutomaticAppsActivity.1.1.this.c, "selectedPresetClosed", paramAnonymous3Int - 1 + "");
                                g.a(AutomaticAppsActivity.this.h, AutomaticAppsActivity.1.1.this.c, "workingModeClosed", "1");
                                AutomaticAppsActivity.this.n.e = AutomaticAppsActivity.this.h;
                                AutomaticAppsActivity.this.n.e(AutomaticAppsActivity.1.1.this.a);
                              }
                              for (;;)
                              {
                                paramAnonymous2MaterialDialog.dismiss();
                                return;
                                g.a(AutomaticAppsActivity.this.h, AutomaticAppsActivity.1.1.this.c, "workingModeClosed", "0");
                                AutomaticAppsActivity.this.n.e = AutomaticAppsActivity.this.h;
                                AutomaticAppsActivity.this.n.e(AutomaticAppsActivity.1.1.this.a);
                              }
                            }
                          });
                          paramAnonymous2MaterialDialog.show();
                        }
                      }).b().show();
                      return;
                      paramAnonymousView = paramAnonymousView;
                      i = -1;
                    }
                  }
                  catch (Exception paramAnonymousView)
                  {
                    j = -1;
                    continue;
                  }
                }
                j = k;
              }
              catch (Exception paramAnonymousRecyclerView)
              {
                System.out.println(paramAnonymousRecyclerView);
                return;
              }
              continue;
              label365:
              final int j = -1;
              final int i = -1;
            }
          }
        });
        this.q.setOnRefreshListener(new SwipeRefreshLayout.b()
        {
          public void a()
          {
            AutomaticAppsActivity.this.a();
          }
        });
        if (!this.l.equals(this.k))
        {
          this.j = ((AdView)findViewById(2131755150));
          this.i = new com.google.android.gms.ads.c.a().b(getResources().getString(2131296732)).a();
          this.j.a(this.i);
          this.j.setAdListener(new a()
          {
            public void a()
            {
              super.a();
              if (!AutomaticAppsActivity.this.l.equals(AutomaticAppsActivity.this.k)) {
                AutomaticAppsActivity.this.j.setVisibility(0);
              }
            }
          });
        }
        return;
        if (this.A == 2)
        {
          setTheme(2131493045);
          if (Build.VERSION.SDK_INT >= 21) {
            setTaskDescription(new ActivityManager.TaskDescription(null, null, getResources().getColor(2131689486)));
          }
          this.A = 2;
          continue;
        }
        setTheme(2131493043);
        if (Build.VERSION.SDK_INT < 21) {
          continue;
        }
        setTaskDescription(new ActivityManager.TaskDescription(null, null, getResources().getColor(2131689509)));
      }
      catch (Exception localException)
      {
        for (;;)
        {
          this.h = new HashMap();
        }
      }
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131820544, paramMenu);
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    }
    for (;;)
    {
      return super.onOptionsItemSelected(paramMenuItem);
      finish();
      return true;
      this.q.setRefreshing(true);
      a();
      return true;
      this.q.setRefreshing(true);
      if (!paramMenuItem.isChecked())
      {
        paramMenuItem.setChecked(true);
        this.r = true;
        a();
        return true;
      }
      paramMenuItem.setChecked(false);
      this.r = false;
      a();
      return true;
      b(false);
      return true;
      paramMenuItem = new Intent(this.e, ManagePresetsActivity.class);
      paramMenuItem.putExtra("requestCode", 1337);
      startActivityForResult(paramMenuItem, 1337);
      return true;
      Intent localIntent = new Intent(this.e, VolumeSettingsActivity.class);
      localIntent.putExtra("requestCode", 87);
      startActivityForResult(localIntent, 87);
    }
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    super.onPrepareOptionsMenu(paramMenu);
    paramMenu.findItem(2131755396).setChecked(this.r);
    this.v = ((Switch)paramMenu.findItem(2131755393).getActionView().findViewById(2131755383));
    if ((e()) && (this.x)) {
      this.v.setChecked(true);
    }
    this.v.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView) {}
    });
    this.v.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean) {
          if (AutomaticAppsActivity.a(AutomaticAppsActivity.this))
          {
            AutomaticAppsActivity.this.x = true;
            AutomaticAppsActivity.this.a(false);
            if (AutomaticAppsActivity.this.n.g) {
              AutomaticAppsActivity.this.a();
            }
          }
        }
        do
        {
          return;
          AutomaticAppsActivity.this.x = false;
          AutomaticAppsActivity.this.b(true);
          return;
          AutomaticAppsActivity.this.x = false;
          AutomaticAppsActivity.this.a(true);
        } while (AutomaticAppsActivity.this.n.g);
        AutomaticAppsActivity.this.a();
      }
    });
    return true;
  }
  
  public void onResume()
  {
    super.onResume();
    if (e()) {
      if (y)
      {
        this.x = true;
        a();
        if (this.v != null) {
          this.v.setChecked(true);
        }
        a(false);
      }
    }
    for (;;)
    {
      y = false;
      return;
      if (this.x)
      {
        if (this.v != null) {
          this.v.setChecked(true);
        }
        this.x = true;
        a(false);
        if ((this.n != null) && (this.n.g)) {
          a();
        }
      }
      else
      {
        if (this.v != null) {
          this.v.setChecked(false);
        }
        this.x = false;
        a(true);
        continue;
        if (this.v != null) {
          this.v.setChecked(false);
        }
        this.x = false;
        a(true);
      }
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    c();
    paramBundle.putBoolean("showSystemApps", this.r);
    paramBundle.putBoolean("wentToAccessibility", y);
  }
}
