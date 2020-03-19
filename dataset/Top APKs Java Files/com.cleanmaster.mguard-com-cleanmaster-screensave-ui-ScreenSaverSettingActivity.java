package com.cleanmaster.screensave.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.cleanmaster.base.activity.i;
import com.cleanmaster.kinfoc.p;
import com.cleanmaster.screensave.ScreenSaveUtils;
import com.cleanmaster.settings.ui.FloatSwipeSettingsActivity.a;
import com.cleanmaster.ui.app.activity.AppCategoryAddGridAdapter;
import com.cleanmaster.util.ai;
import com.cleanmaster.util.an;
import com.ijinshan.cleaner.bean.g;
import com.keniu.security.main.MainActivity;
import com.keniu.security.main.widget.CommonSwitchButton;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ScreenSaverSettingActivity
  extends i
  implements View.OnClickListener
{
  private int c = 0;
  private ImageButton d;
  private TextView e;
  private ImageView g;
  private int h;
  private FrameLayout i;
  private ImageView j;
  private ImageView k;
  private ViewStub l;
  private boolean m;
  private boolean n;
  private CommonSwitchButton o = null;
  private CommonSwitchButton p = null;
  private CommonSwitchButton q = null;
  private CommonSwitchButton r = null;
  private com.cleanmaster.configmanager.e s = null;
  private FloatSwipeSettingsActivity.a t;
  private boolean u = false;
  private boolean v = false;
  private TextView w;
  private ContentObserver x = new ContentObserver(new Handler(Looper.getMainLooper()))
  {
    public final void onChange(boolean paramAnonymousBoolean, Uri paramAnonymousUri)
    {
      super.onChange(paramAnonymousBoolean, paramAnonymousUri);
      com.lock.sideslip.conflict.sideslip.b.a();
      com.lock.sideslip.conflict.sideslip.b.a(ScreenSaverSettingActivity.this.getApplicationContext());
    }
  };
  private Comparator<g> y = new Comparator() {};
  
  public ScreenSaverSettingActivity() {}
  
  public static void a(Context paramContext, int paramInt)
  {
    Intent localIntent = new Intent(paramContext, ScreenSaverSettingActivity.class);
    localIntent.addFlags(268435456);
    localIntent.addFlags(67108864);
    localIntent.putExtra("from_type", paramInt);
    paramContext.startActivity(localIntent);
  }
  
  private void a(TextView paramTextView, boolean paramBoolean)
  {
    if (paramTextView != null)
    {
      if (paramBoolean) {
        paramTextView.setTextColor(getResources().getColor(2131493007));
      }
    }
    else {
      return;
    }
    paramTextView.setTextColor(getResources().getColor(2131493291));
  }
  
  private void a(boolean paramBoolean1, final boolean paramBoolean2)
  {
    this.u = false;
    com.ijinshan.notificationlib.notificationhelper.b.a(this, 1, paramBoolean1, new com.ijinshan.notificationlib.notificationhelper.c(this)
    {
      public final void a(boolean paramAnonymousBoolean)
      {
        if (paramBoolean2) {
          com.cleanmaster.configmanager.d.a(ScreenSaverSettingActivity.this.getApplicationContext()).E(true);
        }
        if (!ScreenSaverSettingActivity.p(ScreenSaverSettingActivity.this))
        {
          com.cleanmaster.screensave.newscreensaver.init.a.a(com.keniu.security.d.a()).u();
          super.a(paramAnonymousBoolean);
        }
      }
      
      public final boolean b()
      {
        return (super.b()) || (ScreenSaverSettingActivity.p(ScreenSaverSettingActivity.this));
      }
    });
  }
  
  private void b(TextView paramTextView, boolean paramBoolean)
  {
    if (paramTextView != null)
    {
      if (paramBoolean) {
        paramTextView.setTextColor(getResources().getColor(2131493701));
      }
    }
    else {
      return;
    }
    paramTextView.setTextColor(getResources().getColor(2131493291));
  }
  
  private void c(boolean paramBoolean)
  {
    a((TextView)findViewById(2131624491), paramBoolean);
    a((TextView)findViewById(2131624483), paramBoolean);
    b((TextView)findViewById(2131624485), paramBoolean);
  }
  
  private void d(boolean paramBoolean)
  {
    a((TextView)findViewById(2131624487), paramBoolean);
    b((TextView)findViewById(2131624488), paramBoolean);
  }
  
  private void h()
  {
    com.cleanmaster.configmanager.d localD = com.cleanmaster.configmanager.d.a(com.keniu.security.d.a());
    boolean bool1 = i();
    boolean bool2 = localD.bR();
    a((TextView)findViewById(2131624480), bool2);
    a((TextView)findViewById(2131624494), bool1);
    this.o.setEnabled(bool1);
    if (bool1)
    {
      this.q.setEnabled(true);
      CommonSwitchButton localCommonSwitchButton = this.o;
      int i1;
      if (bool1)
      {
        int i2 = localD.a("charge_screen_message_enable_search_bar", -1);
        i1 = i2;
        if (i2 == -1)
        {
          if (LibcoreWrapper.a.a(1, "screen_saver_search_bar_section", "screen_saver_search_bar_enable_key", false)) {
            i1 = 1;
          }
        }
        else {
          if (i1 != 1) {
            break label176;
          }
        }
      }
      label176:
      for (bool1 = true;; bool1 = false)
      {
        localCommonSwitchButton.a(bool1, false);
        if (com.ijinshan.notificationlib.notificationhelper.b.a(this)) {
          break label181;
        }
        this.q.a(false, false);
        this.r.a(false, false);
        this.r.setEnabled(false);
        c(false);
        d(false);
        return;
        i1 = 0;
        break;
      }
      label181:
      if (localD.bR())
      {
        c(true);
        this.q.a(true, false);
        if (localD.bU())
        {
          this.r.a(true, false);
          this.r.setEnabled(true);
          d(true);
          return;
        }
        d(false);
        return;
      }
      c(false);
      d(false);
      return;
    }
    this.o.a(false, false);
    this.q.setEnabled(false);
    this.r.setEnabled(false);
    a((TextView)findViewById(2131624480), false);
    c(false);
    d(false);
  }
  
  private static boolean i()
  {
    return com.cleanmaster.configmanager.d.a(com.keniu.security.d.a()).D(true);
  }
  
  private boolean j()
  {
    if ((this.c == 3) || (this.c == 1))
    {
      MainActivity.a(this, 19);
      finish();
      return true;
    }
    return false;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 1)
    {
      this.u = true;
      if ((i()) && (this.v)) {
        ScreenSaveUtils.a(getApplicationContext(), 104);
      }
      this.v = false;
      if ((i()) && (com.ijinshan.notificationlib.notificationhelper.b.a(this)))
      {
        paramIntent = com.cleanmaster.configmanager.d.a(com.keniu.security.d.a());
        if (!paramIntent.bR())
        {
          paramIntent.E(true);
          this.q.a(true, false);
          if (paramIntent.bU()) {
            this.r.a(true, false);
          }
        }
        if (!paramIntent.bU())
        {
          paramIntent.F(true);
          this.r.a(true, false);
        }
      }
    }
  }
  
  public void onBackPressed()
  {
    if (j()) {
      return;
    }
    super.onBackPressed();
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    }
    do
    {
      do
      {
        return;
      } while (j());
      finish();
      return;
    } while (j());
    finish();
  }
  
  protected void onCreate(final Bundle paramBundle)
  {
    super.a(paramBundle, 2131427481);
    setContentView(2130903093);
    this.c = getIntent().getIntExtra("from_type", 0);
    if ((3 == this.c) && (getIntent().getBooleanExtra("ss_type", false))) {
      getWindow().addFlags(524288);
    }
    if ((this.c == 1) || (this.c == 2))
    {
      com.cleanmaster.notification.e.a();
      com.cleanmaster.notification.e.a(23);
    }
    this.s = com.cleanmaster.configmanager.e.a(this);
    this.t = new FloatSwipeSettingsActivity.a();
    if (!this.s.a("swipe_msg_alert_default", false))
    {
      localObject1 = getPackageManager().getInstalledPackages(0);
      paramBundle = new ArrayList();
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = ((PackageInfo)((Iterator)localObject1).next()).packageName;
        if (com.ijinshan.notificationlib.notificationhelper.b.a((String)localObject2)) {
          paramBundle.add(localObject2);
        }
      }
      this.s.b(paramBundle);
      this.s.b("swipe_msg_alert_default", true);
    }
    findViewById(2131624106).setBackgroundResource(2130840601);
    this.d = ((ImageButton)findViewById(2131625350));
    this.d.setVisibility(4);
    this.d.setEnabled(false);
    this.e = ((TextView)findViewById(2131624198));
    this.e.setText(2131299804);
    this.e.setOnClickListener(this);
    this.g = ((ImageView)findViewById(2131626133));
    this.g.setOnClickListener(this);
    paramBundle = com.cleanmaster.configmanager.d.a(com.keniu.security.d.a());
    this.i = ((FrameLayout)findViewById(2131624468));
    this.p = ((CommonSwitchButton)findViewById(2131624476));
    this.q = ((CommonSwitchButton)findViewById(2131624481));
    this.r = ((CommonSwitchButton)findViewById(2131624484));
    this.k = ((ImageView)findViewById(2131624472));
    this.k.setVisibility(8);
    this.j = ((ImageView)findViewById(2131624471));
    this.j.setVisibility(8);
    findViewById(2131624493);
    this.o = ((CommonSwitchButton)findViewById(2131624495));
    findViewById(2131624486);
    this.w = ((TextView)findViewById(2131624488));
    Object localObject1 = this.w;
    Object localObject2 = new StringBuffer();
    ((StringBuffer)localObject2).append("07:00 - - 23:00");
    ((TextView)localObject1).setText(paramBundle.a("scm_night_mode_time", ((StringBuffer)localObject2).toString()));
    boolean bool1;
    if (this.l == null)
    {
      this.l = ((ViewStub)findViewById(2131624469));
      this.l.inflate();
      this.i.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
      {
        public final void onGlobalLayout()
        {
          ScreenSaverSettingActivity.f(ScreenSaverSettingActivity.this).getViewTreeObserver().removeGlobalOnLayoutListener(this);
          int j;
          if (ScreenSaverSettingActivity.g(ScreenSaverSettingActivity.this) == 0)
          {
            ScreenSaverSettingActivity.a(ScreenSaverSettingActivity.this, ScreenSaverSettingActivity.f(ScreenSaverSettingActivity.this).getWidth());
            if (an.a().b())
            {
              j = ai.b();
              i = ai.a();
              if (j <= i) {
                break label159;
              }
              ScreenSaverSettingActivity.a(ScreenSaverSettingActivity.this, ScreenSaverSettingActivity.f(ScreenSaverSettingActivity.this).getWidth());
              if (i == j) {
                ScreenSaverSettingActivity.a(ScreenSaverSettingActivity.this, i);
              }
            }
            if (ScreenSaverSettingActivity.g(ScreenSaverSettingActivity.this) > 0)
            {
              j = ScreenSaverSettingActivity.g(ScreenSaverSettingActivity.this);
              if (j <= 250) {
                break label164;
              }
            }
          }
          label159:
          label164:
          for (int i = ScreenSaverSettingActivity.g(ScreenSaverSettingActivity.this) * 480 / 631;; i = ScreenSaverSettingActivity.g(ScreenSaverSettingActivity.this) * 330 / 631)
          {
            LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(j, i);
            ScreenSaverSettingActivity.f(ScreenSaverSettingActivity.this).setLayoutParams(localLayoutParams);
            return;
            i = j;
            break;
          }
        }
      });
      bool1 = paramBundle.D(true);
      if (!bool1) {
        break label777;
      }
      this.m = true;
      this.p.a(true, false);
      com.cleanmaster.screensave.newscreensaver.init.a.a(getApplicationContext()).a(System.currentTimeMillis());
      label584:
      boolean bool2 = paramBundle.bR();
      if ((bool2) && (bool1)) {
        break label794;
      }
      this.q.a(false, false);
      label606:
      boolean bool3 = paramBundle.bU();
      if ((bool2) && (bool1) && (bool3)) {
        break label806;
      }
      this.r.a(false, false);
      label634:
      findViewById(2131624495).setOnClickListener(new View.OnClickListener()
      {
        public final void onClick(View paramAnonymousView)
        {
          if (!ScreenSaverSettingActivity.h(ScreenSaverSettingActivity.this).isChecked()) {}
          for (int i = 1;; i = 0)
          {
            paramBundle.b("charge_screen_message_enable_search_bar", i);
            return;
          }
        }
      });
      findViewById(2131624476).setOnClickListener(new View.OnClickListener()
      {
        public final void onClick(View paramAnonymousView)
        {
          if (ScreenSaverSettingActivity.c())
          {
            ScreenSaverSettingActivity.i(ScreenSaverSettingActivity.this);
            ScreenSaverSettingActivity.e(ScreenSaverSettingActivity.this);
            return;
          }
          paramAnonymousView = com.cleanmaster.screensave.newscreensaver.init.a.a(ScreenSaverSettingActivity.this.getApplicationContext());
          if (paramAnonymousView.w() == 0L) {
            paramAnonymousView.a(System.currentTimeMillis());
          }
          com.ijinshan.screensavershared.a.a.a("charge_master_enabled_time_from_setting", System.currentTimeMillis());
          com.ijinshan.screensavernew.c.b.a();
          com.ijinshan.screensavernew.c.b.a(new com.ijinshan.screensavernew.c.a.a((byte)2, (byte)1, 0));
          ScreenSaverSettingActivity.b(ScreenSaverSettingActivity.this);
          int i;
          if (com.ijinshan.notificationlib.notificationhelper.b.a(ScreenSaverSettingActivity.this))
          {
            paramAnonymousView = com.cleanmaster.configmanager.d.a(com.keniu.security.d.a());
            if (!paramAnonymousView.bR())
            {
              paramAnonymousView.E(true);
              ScreenSaverSettingActivity.c(ScreenSaverSettingActivity.this).a(true, false);
              if (paramAnonymousView.bU())
              {
                p.a().a("cm_charge_setting", "chargesetting=5", true);
                ScreenSaverSettingActivity.d(ScreenSaverSettingActivity.this).a(true, false);
              }
            }
            if (!paramAnonymousView.bU())
            {
              paramAnonymousView.F(true);
              p.a().a("cm_charge_setting", "chargesetting=5", true);
              ScreenSaverSettingActivity.d(ScreenSaverSettingActivity.this).a(true, false);
            }
            i = 1;
            label189:
            ScreenSaveUtils.a(0, null);
            com.cleanmaster.screensave.newscreensaver.init.a.a(com.keniu.security.d.a()).b("screen_locker_source_from", 6);
            if (i == 0) {
              break label309;
            }
            ScreenSaveUtils.a(ScreenSaverSettingActivity.this.getApplicationContext(), 104);
          }
          for (;;)
          {
            ScreenSaverSettingActivity.b(ScreenSaverSettingActivity.this);
            ScreenSaverSettingActivity.a(ScreenSaverSettingActivity.this).a(true, false);
            p.a().a("cm_charge_setting", "chargesetting=5", true);
            ScreenSaverSettingActivity.f();
            break;
            if ((!paramBundle.bZ()) && (com.cleanmaster.ui.resultpage.ctrl.c.j()))
            {
              ScreenSaverSettingActivity.j(ScreenSaverSettingActivity.this);
              paramBundle.dh();
              ScreenSaverSettingActivity.k(ScreenSaverSettingActivity.this);
              i = 0;
              break label189;
            }
            i = 1;
            break label189;
            label309:
            if (!ScreenSaverSettingActivity.l(ScreenSaverSettingActivity.this)) {
              Toast.makeText(com.keniu.security.d.a(), 2131299806, 0).show();
            }
          }
        }
      });
      findViewById(2131624481).setOnClickListener(new View.OnClickListener()
      {
        public final void onClick(View paramAnonymousView)
        {
          if (!ScreenSaverSettingActivity.c()) {
            return;
          }
          paramAnonymousView = com.cleanmaster.configmanager.d.a(com.keniu.security.d.a());
          if (!com.ijinshan.notificationlib.notificationhelper.b.a(ScreenSaverSettingActivity.this)) {
            paramAnonymousView.E(false);
          }
          boolean bool = paramAnonymousView.bR();
          if (bool)
          {
            paramAnonymousView.E(false);
            ScreenSaverSettingActivity.c(ScreenSaverSettingActivity.this).a(false, false);
            ScreenSaverSettingActivity.d(ScreenSaverSettingActivity.this).a(false, false);
            ScreenSaverSettingActivity.d(ScreenSaverSettingActivity.this).setEnabled(false);
            paramAnonymousView.b("charge_screen_message_close_flag", true);
            ScreenSaverSettingActivity.b(ScreenSaverSettingActivity.this, bool);
            ScreenSaverSettingActivity.e(ScreenSaverSettingActivity.this);
            return;
          }
          if (!com.ijinshan.notificationlib.notificationhelper.b.a(ScreenSaverSettingActivity.this)) {
            ScreenSaverSettingActivity.a(ScreenSaverSettingActivity.this, ScreenSaverSettingActivity.m(ScreenSaverSettingActivity.this));
          }
          for (;;)
          {
            ScreenSaverSettingActivity.d(ScreenSaverSettingActivity.this).setEnabled(true);
            break;
            paramAnonymousView.E(true);
            ScreenSaverSettingActivity.c(ScreenSaverSettingActivity.this).a(true, false);
            if (paramAnonymousView.bU()) {
              ScreenSaverSettingActivity.d(ScreenSaverSettingActivity.this).a(true, false);
            }
          }
        }
      });
      findViewById(2131624484).setOnClickListener(new View.OnClickListener()
      {
        public final void onClick(View paramAnonymousView)
        {
          if ((!ScreenSaverSettingActivity.c()) || (!ScreenSaverSettingActivity.g())) {
            return;
          }
          paramAnonymousView = com.cleanmaster.configmanager.d.a(com.keniu.security.d.a());
          if (paramAnonymousView.bU())
          {
            paramAnonymousView.F(false);
            ScreenSaverSettingActivity.d(ScreenSaverSettingActivity.this).a(false, false);
          }
          for (;;)
          {
            ScreenSaverSettingActivity.e(ScreenSaverSettingActivity.this);
            return;
            paramAnonymousView.F(true);
            ScreenSaverSettingActivity.d(ScreenSaverSettingActivity.this).a(true, false);
          }
        }
      });
      findViewById(2131624486).setOnClickListener(new View.OnClickListener()
      {
        public final void onClick(View paramAnonymousView)
        {
          paramAnonymousView = com.cleanmaster.configmanager.d.a(com.keniu.security.d.a());
          if ((ScreenSaverSettingActivity.c()) && (paramAnonymousView.bR()) && (paramAnonymousView.bU())) {
            ScreenSaverSettingActivity.n(ScreenSaverSettingActivity.this);
          }
        }
      });
      findViewById(2131624490).setOnClickListener(new View.OnClickListener()
      {
        public final void onClick(View paramAnonymousView)
        {
          paramAnonymousView = com.cleanmaster.configmanager.d.a(com.keniu.security.d.a());
          if ((ScreenSaverSettingActivity.c()) && (paramAnonymousView.bR())) {
            ScreenSaverSettingActivity.o(ScreenSaverSettingActivity.this);
          }
        }
      });
      if (!paramBundle.bQ())
      {
        if (!bool1) {
          break label818;
        }
        ScreenSaveUtils.a(0, null);
      }
    }
    for (;;)
    {
      paramBundle.bY();
      return;
      this.l.setVisibility(0);
      break;
      label777:
      this.m = false;
      this.p.a(false, false);
      break label584;
      label794:
      this.q.a(true, false);
      break label606;
      label806:
      this.r.a(true, false);
      break label634;
      label818:
      paramBundle.C(bool1);
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    p localP = p.a();
    StringBuilder localStringBuilder = new StringBuilder("frompage=").append(this.c).append("&click=");
    if (this.n)
    {
      str = "1";
      localStringBuilder = localStringBuilder.append(str).append("&landingstate=");
      if (!this.m) {
        break label90;
      }
    }
    label90:
    for (String str = "1";; str = "2")
    {
      localP.a("cm_charge_landing", str, true);
      return;
      str = "2";
      break;
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    com.cleanmaster.configmanager.d localD = com.cleanmaster.configmanager.d.a(com.keniu.security.d.a());
    if (localD.bS())
    {
      localD.bT();
      if (com.ijinshan.notificationlib.notificationhelper.b.a(this))
      {
        localD.E(true);
        this.q.a(true, false);
        if (localD.bU()) {
          this.r.a(true, false);
        }
      }
    }
    h();
  }
  
  protected void onSaveInstanceState(Bundle paramBundle) {}
  
  protected void onStart()
  {
    super.onStart();
    com.lock.sideslip.conflict.sideslip.b.a();
    com.lock.sideslip.conflict.sideslip.b.a(getApplicationContext());
    com.lock.sideslip.conflict.sideslip.b.a();
    com.lock.sideslip.conflict.sideslip.b.b(getApplicationContext(), this.x);
  }
  
  protected void onStop()
  {
    super.onStop();
    com.lock.sideslip.conflict.sideslip.b.a();
    com.lock.sideslip.conflict.sideslip.b.a(getApplicationContext(), this.x);
    com.lock.sideslip.conflict.sideslip.b.a();
    com.lock.sideslip.conflict.sideslip.b.a(getApplicationContext(), 256);
  }
}
