package com.hipu.yidian.ui.guide;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.Window;
import android.widget.ProgressBar;
import arb;
import are;
import arf;
import aro;
import ass;
import att;
import atu;
import aug;
import awd;
import awj;
import awm;
import awn;
import awo;
import ax;
import axo;
import axo.a;
import axp;
import axq;
import axr;
import axs;
import axs.a;
import ba;
import bai;
import bak;
import bao;
import bap;
import baq;
import baq.a;
import bat;
import bbh.a;
import com.crittercism.app.Crittercism;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.applinks.AppLinkData;
import com.facebook.applinks.AppLinkData.CompletionHandler;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.hipu.yidian.HipuApplication;
import com.hipu.yidian.ui.HipuBaseFragmentActivity;
import com.hipu.yidian.ui.navibar.NavibarHomeActivity;
import com.hipu.yidian.ui.settings.RegisterActivity;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

public class UserGuideActivity
  extends HipuBaseFragmentActivity
  implements axo.a, axs.a
{
  static final String i = UserGuideActivity.class.getSimpleName();
  private String A = null;
  private boolean B = false;
  private boolean C = false;
  private long D;
  private int E = 0;
  private int F = 5000;
  private Uri G = null;
  private final String H = "campid";
  private final String I = "color";
  private final String J = "zip";
  private final String K = "fromid";
  private awn L = new awn()
  {
    public final void a(awm paramAnonymousAwm)
    {
      if (UserGuideActivity.b(UserGuideActivity.this) != null) {
        UserGuideActivity.b(UserGuideActivity.this).setVisibility(8);
      }
      paramAnonymousAwm = (aro)paramAnonymousAwm;
      if ((paramAnonymousAwm.h().a()) && (paramAnonymousAwm.b.b))
      {
        paramAnonymousAwm = paramAnonymousAwm.l;
        if ((paramAnonymousAwm != null) && (paramAnonymousAwm.size() > 0))
        {
          att.a().e().a(paramAnonymousAwm);
          UserGuideActivity.j(UserGuideActivity.this);
          return;
        }
        UserGuideActivity.this.d();
        return;
      }
      UserGuideActivity.this.d();
    }
    
    public final void onCancel() {}
  };
  int j = 0;
  boolean k = false;
  Handler l = new Handler(Looper.getMainLooper());
  boolean m = false;
  boolean n = false;
  boolean o = false;
  Fragment p = null;
  int q = 1000;
  Fragment r = null;
  axo s = null;
  boolean t = false;
  boolean u = false;
  boolean v = false;
  Timer w = null;
  b x = new b();
  axo y = null;
  private ProgressBar z;
  
  public UserGuideActivity() {}
  
  private static boolean a(atu paramAtu)
  {
    return (paramAtu != null) && (paramAtu.c > 0);
  }
  
  private void f()
  {
    HipuApplication.a().X = false;
    att.a().c();
    Object localObject1 = att.a().k();
    if (a((atu)localObject1))
    {
      this.n = true;
      if ((baq.a(baq.a.a, false)) && (HipuApplication.a().z))
      {
        this.n = false;
        this.t = true;
        switch (((atu)localObject1).a)
        {
        default: 
          this.t = false;
        }
      }
      for (;;)
      {
        this.n = true;
        this.l.postDelayed(this.x, this.q);
        localObject1 = att.a().e();
        if ((localObject1 != null) && (((aug)localObject1).c.size() <= 0))
        {
          att.a().j();
          att.a().g();
        }
        new ass((byte)0).b_();
        return;
        Object localObject2 = new axp(this);
        ((axp)localObject2).c((atu)localObject1);
        ((axo)localObject2).j = this;
        this.s = ((axo)localObject2);
        continue;
        if (((atu)localObject1).m == 9)
        {
          localObject2 = new axq(this);
          ((axq)localObject2).a((atu)localObject1);
          ((axo)localObject2).j = this;
          this.s = ((axo)localObject2);
        }
      }
    }
    this.n = false;
    bak.a().a(true);
    this.l.postDelayed(new a(), 300L);
  }
  
  private void g()
  {
    this.r = new axs();
    try
    {
      getSupportFragmentManager().a().a(this.p).b(2131689857, this.r).b();
      return;
    }
    catch (IllegalStateException localIllegalStateException) {}
  }
  
  private void h()
  {
    if (this.n)
    {
      d();
      return;
    }
    bao.a(i, "Wait for next screen");
    this.l.postDelayed(new b(), 500L);
  }
  
  private void i()
  {
    startActivity(new Intent(this, OnboardingActivity.class));
    overridePendingTransition(2131034134, 2131034128);
    finish();
  }
  
  private boolean j()
  {
    boolean bool = false;
    HipuApplication.a();
    if (HipuApplication.A())
    {
      new aro(this.L).b_();
      if (this.z != null) {
        this.z.setVisibility(0);
      }
      bool = true;
    }
    return bool;
  }
  
  private boolean k()
  {
    do
    {
      try
      {
        localContentResolver = getContentResolver();
        if (this.A != null) {
          break label137;
        }
        localObject3 = bat.a("launcher_authority");
        localObject1 = localObject3;
        if (localObject3 != null) {
          break label131;
        }
        localObject1 = getPackageManager().getInstalledPackages(8);
        if (localObject1 == null) {
          break label328;
        }
        localObject1 = ((List)localObject1).iterator();
        do
        {
          if (!((Iterator)localObject1).hasNext()) {
            break;
          }
          localObject3 = ((PackageInfo)((Iterator)localObject1).next()).providers;
        } while (localObject3 == null);
        i2 = localObject3.length;
        i1 = 0;
      }
      catch (Exception localException1)
      {
        for (;;)
        {
          int i1;
          try
          {
            ContentResolver localContentResolver;
            Object localObject3;
            Object localObject1;
            int i2;
            Object localObject4;
            ((Cursor)localObject1).close();
            bool = true;
            return bool;
          }
          catch (Exception localException2)
          {
            bool = true;
            continue;
          }
          localException1 = localException1;
          boolean bool = false;
          localException1.printStackTrace();
          continue;
          bool = false;
          continue;
          i1 += 1;
          continue;
          Object localObject2 = null;
        }
      }
    } while (i1 >= i2);
    localObject4 = localObject3[i1];
    if ("com.android.launcher.permission.READ_SETTINGS".equals(localObject4.readPermission)) {}
    for (localObject1 = localObject4.authority;; localObject1 = localObject4.authority)
    {
      bat.a("launcher_authority", (String)localObject1);
      label131:
      this.A = ((String)localObject1);
      label137:
      if (this.A != null) {
        break;
      }
      return false;
      if (!"com.android.launcher.permission.READ_SETTINGS".equals(localObject4.writePermission)) {
        break label321;
      }
    }
    localObject1 = Uri.parse("content://" + this.A + "/favorites?notify=true");
    localObject3 = getString(2131230842);
    localObject1 = localContentResolver.query((Uri)localObject1, new String[] { "title", "iconResource" }, "title=?", new String[] { localObject3 }, null);
    new StringBuilder("Count = ").append(((Cursor)localObject1).getCount());
    if (localObject1 != null)
    {
      i1 = ((Cursor)localObject1).getCount();
      if (i1 <= 0) {}
    }
  }
  
  public final void a(int paramInt)
  {
    if (this.w != null)
    {
      this.w.cancel();
      this.w = null;
    }
    if (paramInt == 0)
    {
      HipuApplication.a().b();
      try
      {
        atu localAtu = att.a().k();
        if ((localAtu != null) && (localAtu.c > 0))
        {
          awj.f(Integer.toString(localAtu.c));
          awj.b(HipuApplication.a().al);
          awj.c(HipuApplication.a().W.i);
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
        }
      }
      if (!j()) {
        this.n = true;
      }
      return;
    }
    this.n = false;
    if ((this.s != null) && (this.s.a() == 34))
    {
      this.l.postDelayed(new Runnable()
      {
        public final void run()
        {
          UserGuideActivity.h(UserGuideActivity.this);
        }
      }, 300L);
      this.s = null;
      return;
    }
    this.s = null;
    if (this.t)
    {
      this.n = true;
      return;
    }
    bai.a(2131230886, false);
  }
  
  public final void b()
  {
    startActivityForResult(new Intent(this, RegisterActivity.class), 306);
  }
  
  public final void c()
  {
    this.l.post(new a());
    this.z.setVisibility(0);
  }
  
  public final void d()
  {
    this.C = false;
    bat.a("login_finished", true);
    Intent localIntent = new Intent(this, NavibarHomeActivity.class);
    localIntent.setFlags(67108864);
    startActivity(localIntent);
    overridePendingTransition(2131034134, 2131034128);
    finish();
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    HipuApplication.a();
    HipuApplication.y().onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 303) {
      if (paramInt2 == -1)
      {
        HipuApplication.a().n();
        HipuApplication.a().z = bap.a();
        f();
      }
    }
    for (;;)
    {
      return;
      finish();
      return;
      if (paramInt2 != 0)
      {
        if ((paramInt1 == 304) && (paramInt2 == -1)) {
          d();
        }
        if (this.s == null)
        {
          if ((paramInt1 == 306) && (paramInt2 == -1))
          {
            aug localAug = att.a().e();
            if ((!att.a().h) || (localAug == null) || (localAug.d == null) || (localAug.d.size() <= 0)) {
              break label164;
            }
            i();
          }
          while (this.r != null)
          {
            this.r.onActivityResult(paramInt1, paramInt2, paramIntent);
            return;
            label164:
            d();
          }
        }
      }
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    this.D = System.currentTimeMillis();
    getWindow().requestFeature(1);
    getWindow().setFlags(1024, 1024);
    super.onCreate(paramBundle);
    setContentView(2130968691);
    this.z = ((ProgressBar)findViewById(2131689858));
    awd.a("PageUserGuide");
    awj.a();
    paramBundle = getIntent();
    this.k = paramBundle.getBooleanExtra("relogin", false);
    this.j = paramBundle.getIntExtra("purpose", 0);
    this.B = paramBundle.getBooleanExtra("isShortcut", false);
    paramBundle = att.a().k();
    boolean bool = bat.a("firstLaunch", Boolean.valueOf(true));
    bao.a(i, "firstLaunch=" + bool);
    if ((!a(paramBundle)) && (bool))
    {
      this.C = true;
      bao.a(i, "getFacebookDeferredAppLink");
      AppLinkData.fetchDeferredAppLinkData(getApplicationContext(), new AppLinkData.CompletionHandler()
      {
        public final void onDeferredAppLinkDataFetched(AppLinkData paramAnonymousAppLinkData)
        {
          bao.a(UserGuideActivity.e(), "appLinkData=" + paramAnonymousAppLinkData + "; time:" + (System.currentTimeMillis() - UserGuideActivity.c(UserGuideActivity.this)));
          if (paramAnonymousAppLinkData != null)
          {
            UserGuideActivity.a(UserGuideActivity.this, paramAnonymousAppLinkData.getTargetUri());
            UserGuideActivity.e();
            new StringBuilder("facebook target uri:").append(UserGuideActivity.i(UserGuideActivity.this));
          }
          try
          {
            paramAnonymousAppLinkData = UserGuideActivity.i(UserGuideActivity.this).getQueryParameter("campid");
            if ((paramAnonymousAppLinkData != null) && (paramAnonymousAppLinkData.length() > 0)) {
              HipuApplication.a().f(paramAnonymousAppLinkData);
            }
            paramAnonymousAppLinkData = UserGuideActivity.i(UserGuideActivity.this).getQueryParameter("color");
            if ((paramAnonymousAppLinkData != null) && (paramAnonymousAppLinkData.length() > 0)) {
              HipuApplication.a().a(bbh.a.a(paramAnonymousAppLinkData));
            }
            paramAnonymousAppLinkData = UserGuideActivity.i(UserGuideActivity.this).getQueryParameter("zip");
            if ((paramAnonymousAppLinkData != null) && (paramAnonymousAppLinkData.length() > 0)) {
              HipuApplication.a().e(paramAnonymousAppLinkData);
            }
            paramAnonymousAppLinkData = UserGuideActivity.i(UserGuideActivity.this).getQueryParameter("fromid");
            if ((paramAnonymousAppLinkData != null) && (paramAnonymousAppLinkData.length() > 0)) {
              HipuApplication.a().am = paramAnonymousAppLinkData;
            }
            paramAnonymousAppLinkData = UserGuideActivity.i(UserGuideActivity.this).toString().substring(UserGuideActivity.i(UserGuideActivity.this).toString().indexOf("?") + 1);
            HipuApplication.a().g(paramAnonymousAppLinkData);
            awj.e(paramAnonymousAppLinkData);
            return;
          }
          catch (Exception paramAnonymousAppLinkData)
          {
            UserGuideActivity.e();
          }
        }
      });
    }
    if (bool) {
      bat.a("firstLaunch", false);
    }
    if (!"newsbreak".equals("yidian")) {
      "newsbreak".equals("innow");
    }
    this.F = 5000;
    if (!j())
    {
      if (!this.k) {
        break label393;
      }
      g();
    }
    for (;;)
    {
      if (("com.particlenews.newsbreak".equals("in.indianow")) && (!bat.a("create_shortcut", Boolean.valueOf(false))))
      {
        bat.a("create_shortcut", true);
        if (!k())
        {
          paramBundle = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
          paramBundle.putExtra("android.intent.extra.shortcut.NAME", getString(2131230842));
          paramBundle.putExtra("duplicate", false);
          Intent localIntent = new Intent(this, UserGuideActivity.class);
          localIntent.setAction("android.intent.action.MAIN");
          localIntent.putExtra("isShortcut", true);
          paramBundle.putExtra("android.intent.extra.shortcut.INTENT", localIntent);
          paramBundle.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(this, 2130837909));
          sendBroadcast(paramBundle);
        }
      }
      Crittercism.a(getApplicationContext(), getResources().getString(2131230895));
      return;
      label393:
      f();
    }
  }
  
  public void onFacebookLogin()
  {
    this.y = new axq(this);
    this.y.j = this;
    LoginManager localLoginManager = LoginManager.getInstance();
    HipuApplication.a();
    localLoginManager.registerCallback(HipuApplication.y(), new FacebookCallback()
    {
      public final void onCancel() {}
      
      public final void onError(FacebookException paramAnonymousFacebookException)
      {
        UserGuideActivity.e();
      }
    });
    LoginManager.getInstance().logInWithReadPermissions(this, arb.i);
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
  }
  
  public void onResume()
  {
    super.onResume();
    this.u = false;
    if (this.v)
    {
      h();
      this.v = false;
    }
  }
  
  public void onStop()
  {
    this.u = true;
    super.onStop();
  }
  
  final class a
    implements Runnable
  {
    a() {}
    
    public final void run()
    {
      Object localObject1 = HipuApplication.a().al;
      long l = System.currentTimeMillis() - UserGuideActivity.c(UserGuideActivity.this);
      if ((!UserGuideActivity.d(UserGuideActivity.this)) || (localObject1 != null) || (l > UserGuideActivity.e(UserGuideActivity.this))) {}
      for (int i = 1;; i = 0)
      {
        if (i != 0)
        {
          bao.a(UserGuideActivity.e(), "start first login");
          localObject1 = UserGuideActivity.this;
          Object localObject2 = att.a().k();
          if (((atu)localObject2).a != 0)
          {
            ((atu)localObject2).d();
            att.a().a(null);
          }
          awd.c("guest", UserGuideActivity.i);
          if (((atu)localObject2).c > 0) {
            ((UserGuideActivity)localObject1).d();
          }
          for (;;)
          {
            UserGuideActivity.this.l.post(UserGuideActivity.this.x);
            return;
            localObject2 = new axr((Activity)localObject1);
            ((axo)localObject2).j = ((axo.a)localObject1);
            ((axr)localObject2).b(false);
            ((UserGuideActivity)localObject1).y = ((axo)localObject2);
          }
        }
        bao.a(UserGuideActivity.e(), "wait for first login: " + UserGuideActivity.f(UserGuideActivity.this));
        UserGuideActivity.g(UserGuideActivity.this);
        UserGuideActivity.this.l.postDelayed(new a(UserGuideActivity.this), Math.min(1000L, Math.max(0L, UserGuideActivity.e(UserGuideActivity.this) - l)));
        return;
      }
    }
  }
  
  final class b
    implements Runnable
  {
    b() {}
    
    public final void run()
    {
      if (!UserGuideActivity.this.u)
      {
        UserGuideActivity.a(UserGuideActivity.this);
        return;
      }
      UserGuideActivity.this.v = true;
    }
  }
}
