package com.logicx.secretcodesandhacks;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.h;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.ads.AudienceNetworkAds;
import com.logicx.secretcodesandhacks.Device.fragments.BatteryFragment;
import com.logicx.secretcodesandhacks.Device.fragments.DashboardFragment;
import com.logicx.secretcodesandhacks.Device.fragments.HomeFragment;
import com.logicx.secretcodesandhacks.Device.fragments.OSFragment;
import com.logicx.secretcodesandhacks.Device.fragments.a.b;
import com.mikepenz.crossfadedrawerlayout.view.CrossfadeDrawerLayout;
import com.mikepenz.google_material_typeface_library.GoogleMaterial.a;
import com.mikepenz.materialdrawer.c.a;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CrossfadeDrawerLayoutActvitiy
  extends android.support.v7.app.e
{
  @BindView
  ImageView ivBack;
  @BindView
  ImageView ivMenu;
  @BindView
  ImageView ivRate;
  @BindView
  ImageView ivShare;
  com.logicx.secretcodesandhacks.NewDatabase.a n;
  boolean o = false;
  Toolbar p;
  public com.logicx.secretcodesandhacks.Device.c.b q;
  private com.mikepenz.materialdrawer.a r = null;
  private com.mikepenz.materialdrawer.c s = null;
  private CrossfadeDrawerLayout t = null;
  @BindView
  TextView tvTitle;
  
  public CrossfadeDrawerLayoutActvitiy() {}
  
  public static void a(Context paramContext)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setType("text/plain");
      localIntent.putExtra("android.intent.extra.SUBJECT", paramContext.getResources().getString(2131623984));
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("\nSecret Codes and Hacks is an free app that would get you in and out of hidden menus within your device.Share with pepole nearby.\n\nLet's Download From play store\n");
      localStringBuilder.append("https://play.google.com/store/apps/details?id=");
      localStringBuilder.append(paramContext.getPackageName());
      localIntent.putExtra("android.intent.extra.TEXT", localStringBuilder.toString());
      paramContext.startActivity(Intent.createChooser(localIntent, "Share with pepole nearby"));
      return;
    }
    catch (Exception paramContext) {}
  }
  
  private void b(h paramH)
  {
    if (paramH != null)
    {
      this.q.a();
      this.q.a(paramH, false, true);
    }
  }
  
  private void n()
  {
    this.s.b().e(8388611);
  }
  
  private void o()
  {
    if ((this.s != null) && (this.s.d())) {
      this.s.c();
    }
  }
  
  private boolean p()
  {
    return ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo() != null;
  }
  
  public List<com.logicx.secretcodesandhacks.Device.b.a> m()
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(1152).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if ((localApplicationInfo.flags & 0x1) == 1) {
        localArrayList.add(new com.logicx.secretcodesandhacks.Device.b.a(1, localPackageManager.getApplicationIcon(localApplicationInfo), localPackageManager.getApplicationLabel(localApplicationInfo).toString(), localApplicationInfo.packageName));
      } else {
        localArrayList.add(new com.logicx.secretcodesandhacks.Device.b.a(2, localPackageManager.getApplicationIcon(localApplicationInfo), localPackageManager.getApplicationLabel(localApplicationInfo).toString(), localApplicationInfo.packageName));
      }
    }
    return localArrayList;
  }
  
  public void onBackPressed()
  {
    if (this.o)
    {
      super.onBackPressed();
      return;
    }
    if ((this.s != null) && (this.s.d()))
    {
      this.s.c();
      return;
    }
    this.o = true;
    Toast.makeText(this, "Please click BACK again to exit", 0).show();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        CrossfadeDrawerLayoutActvitiy.this.o = false;
      }
    }, 10000L);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    AudienceNetworkAds.initialize(this);
    setContentView(2131427361);
    ButterKnife.a(this);
    com.logicx.secretcodesandhacks.b.a.a(this);
    this.n = new com.logicx.secretcodesandhacks.NewDatabase.a(this);
    try
    {
      this.n.a();
    }
    catch (IOException localIOException)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(".");
      localStringBuilder.append(localIOException);
      Log.v("Home CreateDatabase", localStringBuilder.toString());
    }
    this.p = ((Toolbar)findViewById(2131296667));
    a(this.p);
    new g(this);
    this.q = new com.logicx.secretcodesandhacks.Device.c.b(this);
    this.ivMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CrossfadeDrawerLayoutActvitiy.a(CrossfadeDrawerLayoutActvitiy.this);
      }
    });
    this.ivRate.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CrossfadeDrawerLayoutActvitiy.b(CrossfadeDrawerLayoutActvitiy.this);
        if (CrossfadeDrawerLayoutActvitiy.c(CrossfadeDrawerLayoutActvitiy.this))
        {
          paramAnonymousView = new Intent("android.intent.action.VIEW");
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("https://play.google.com/store/apps/details?id=");
          localStringBuilder.append(CrossfadeDrawerLayoutActvitiy.this.getApplicationContext().getPackageName());
          paramAnonymousView.setData(Uri.parse(localStringBuilder.toString()));
          CrossfadeDrawerLayoutActvitiy.this.startActivity(paramAnonymousView);
          return;
        }
        Toast.makeText(CrossfadeDrawerLayoutActvitiy.this, "Please Check Internet Connection..!", 1).show();
      }
    });
    this.ivShare.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CrossfadeDrawerLayoutActvitiy.b(CrossfadeDrawerLayoutActvitiy.this);
        CrossfadeDrawerLayoutActvitiy.a(CrossfadeDrawerLayoutActvitiy.this);
      }
    });
    this.r = new com.mikepenz.materialdrawer.b().a(this).a(2131230812).a(paramBundle).a();
    b(new DashboardFragment());
    Object localObject = this.tvTitle;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("".concat(Build.BRAND));
    localStringBuilder.append(" (");
    localStringBuilder.append(Build.MODEL);
    localStringBuilder.append(")");
    ((TextView)localObject).setText(localStringBuilder.toString());
    this.s = new com.mikepenz.materialdrawer.d().a(this).a(this.p).c(2131427448).a(true).a(2131427370).b(72).c(true).a(new com.mikepenz.materialdrawer.d.a.a[] { (com.mikepenz.materialdrawer.d.a.a)((com.mikepenz.materialdrawer.d.i)((com.mikepenz.materialdrawer.d.i)new com.mikepenz.materialdrawer.d.i().a(2131624037)).a(GoogleMaterial.a.dc)).a(1L), (com.mikepenz.materialdrawer.d.a.a)((com.mikepenz.materialdrawer.d.i)((com.mikepenz.materialdrawer.d.i)new com.mikepenz.materialdrawer.d.i().a(2131624056)).a(GoogleMaterial.a.kB)).a(2L), (com.mikepenz.materialdrawer.d.a.a)((com.mikepenz.materialdrawer.d.i)((com.mikepenz.materialdrawer.d.i)new com.mikepenz.materialdrawer.d.i().a(2131625837)).a(GoogleMaterial.a.l)).a(3L), (com.mikepenz.materialdrawer.d.a.a)((com.mikepenz.materialdrawer.d.i)((com.mikepenz.materialdrawer.d.i)new com.mikepenz.materialdrawer.d.i().a(2131625893)).a(GoogleMaterial.a.pv)).a(4L), (com.mikepenz.materialdrawer.d.a.a)((com.mikepenz.materialdrawer.d.i)((com.mikepenz.materialdrawer.d.i)new com.mikepenz.materialdrawer.d.i().a(2131624030)).a(GoogleMaterial.a.dn)).a(5L), (com.mikepenz.materialdrawer.d.a.a)((com.mikepenz.materialdrawer.d.i)((com.mikepenz.materialdrawer.d.i)new com.mikepenz.materialdrawer.d.i().a(2131623997)).a(GoogleMaterial.a.av)).a(6L), (com.mikepenz.materialdrawer.d.a.a)((com.mikepenz.materialdrawer.d.i)((com.mikepenz.materialdrawer.d.i)new com.mikepenz.materialdrawer.d.i().a(2131625825)).a(GoogleMaterial.a.jM)).a(7L), (com.mikepenz.materialdrawer.d.a.a)((com.mikepenz.materialdrawer.d.i)((com.mikepenz.materialdrawer.d.i)new com.mikepenz.materialdrawer.d.i().a(2131624014)).a(GoogleMaterial.a.le)).a(8L), (com.mikepenz.materialdrawer.d.a.a)((com.mikepenz.materialdrawer.d.i)((com.mikepenz.materialdrawer.d.i)new com.mikepenz.materialdrawer.d.i().a(2131625906)).a(GoogleMaterial.a.jd)).a(9L), (com.mikepenz.materialdrawer.d.a.a)((com.mikepenz.materialdrawer.d.i)((com.mikepenz.materialdrawer.d.i)new com.mikepenz.materialdrawer.d.i().a(2131624059)).a(GoogleMaterial.a.oT)).a(10L), (com.mikepenz.materialdrawer.d.a.a)((com.mikepenz.materialdrawer.d.i)((com.mikepenz.materialdrawer.d.i)new com.mikepenz.materialdrawer.d.i().a(2131624744)).a(GoogleMaterial.a.oA)).a(11L), (com.mikepenz.materialdrawer.d.a.a)((com.mikepenz.materialdrawer.d.i)((com.mikepenz.materialdrawer.d.i)new com.mikepenz.materialdrawer.d.i().a(2131625935)).a(GoogleMaterial.a.bp)).a(12L), (com.mikepenz.materialdrawer.d.a.a)((com.mikepenz.materialdrawer.d.i)((com.mikepenz.materialdrawer.d.i)new com.mikepenz.materialdrawer.d.i().a(2131625908)).a(GoogleMaterial.a.qM)).a(13L), (com.mikepenz.materialdrawer.d.a.a)((com.mikepenz.materialdrawer.d.i)((com.mikepenz.materialdrawer.d.i)new com.mikepenz.materialdrawer.d.i().a(2131625816)).a(GoogleMaterial.a.nw)).a(14L) }).a(new c.a()
    {
      public boolean a(View paramAnonymousView, int paramAnonymousInt, com.mikepenz.materialdrawer.d.a.a paramAnonymousA)
      {
        switch ((int)paramAnonymousA.d())
        {
        default: 
          break;
        }
        try
        {
          CrossfadeDrawerLayoutActvitiy.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/developer?id=Ndroid+Developers")));
        }
        catch (ActivityNotFoundException paramAnonymousView)
        {
          for (;;) {}
        }
        CrossfadeDrawerLayoutActvitiy.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/developer?id=Ndroid+Developers")));
        break label604;
        CrossfadeDrawerLayoutActvitiy.a(CrossfadeDrawerLayoutActvitiy.this, com.logicx.secretcodesandhacks.Device.fragments.a.Z.a(1));
        paramAnonymousView = CrossfadeDrawerLayoutActvitiy.this.tvTitle;
        paramAnonymousA = CrossfadeDrawerLayoutActvitiy.this;
        paramAnonymousInt = 2131625908;
        break label520;
        CrossfadeDrawerLayoutActvitiy.a(CrossfadeDrawerLayoutActvitiy.this, com.logicx.secretcodesandhacks.Device.fragments.a.Z.a(2));
        paramAnonymousView = CrossfadeDrawerLayoutActvitiy.this.tvTitle;
        paramAnonymousA = CrossfadeDrawerLayoutActvitiy.this;
        paramAnonymousInt = 2131625935;
        break label520;
        CrossfadeDrawerLayoutActvitiy.a(CrossfadeDrawerLayoutActvitiy.this, new com.logicx.secretcodesandhacks.Device.fragments.f());
        paramAnonymousView = CrossfadeDrawerLayoutActvitiy.this.tvTitle;
        paramAnonymousA = CrossfadeDrawerLayoutActvitiy.this;
        paramAnonymousInt = 2131624743;
        break label520;
        CrossfadeDrawerLayoutActvitiy.a(CrossfadeDrawerLayoutActvitiy.this, new com.logicx.secretcodesandhacks.Device.fragments.d());
        paramAnonymousView = CrossfadeDrawerLayoutActvitiy.this.tvTitle;
        paramAnonymousA = CrossfadeDrawerLayoutActvitiy.this;
        paramAnonymousInt = 2131624059;
        break label520;
        CrossfadeDrawerLayoutActvitiy.a(CrossfadeDrawerLayoutActvitiy.this, new com.logicx.secretcodesandhacks.Device.fragments.i());
        paramAnonymousView = CrossfadeDrawerLayoutActvitiy.this.tvTitle;
        paramAnonymousA = CrossfadeDrawerLayoutActvitiy.this;
        paramAnonymousInt = 2131625906;
        break label520;
        CrossfadeDrawerLayoutActvitiy.a(CrossfadeDrawerLayoutActvitiy.this, new com.logicx.secretcodesandhacks.Device.fragments.c());
        paramAnonymousView = CrossfadeDrawerLayoutActvitiy.this.tvTitle;
        paramAnonymousA = CrossfadeDrawerLayoutActvitiy.this;
        paramAnonymousInt = 2131624014;
        break label520;
        CrossfadeDrawerLayoutActvitiy.a(CrossfadeDrawerLayoutActvitiy.this, new com.logicx.secretcodesandhacks.Device.fragments.e());
        paramAnonymousView = CrossfadeDrawerLayoutActvitiy.this.tvTitle;
        paramAnonymousA = CrossfadeDrawerLayoutActvitiy.this;
        paramAnonymousInt = 2131625825;
        break label520;
        CrossfadeDrawerLayoutActvitiy.a(CrossfadeDrawerLayoutActvitiy.this, new BatteryFragment());
        paramAnonymousView = CrossfadeDrawerLayoutActvitiy.this.tvTitle;
        paramAnonymousA = CrossfadeDrawerLayoutActvitiy.this;
        paramAnonymousInt = 2131623997;
        break label520;
        CrossfadeDrawerLayoutActvitiy.a(CrossfadeDrawerLayoutActvitiy.this, new com.logicx.secretcodesandhacks.Device.fragments.b());
        paramAnonymousView = CrossfadeDrawerLayoutActvitiy.this.tvTitle;
        paramAnonymousA = CrossfadeDrawerLayoutActvitiy.this;
        paramAnonymousInt = 2131624030;
        break label520;
        CrossfadeDrawerLayoutActvitiy.a(CrossfadeDrawerLayoutActvitiy.this, new com.logicx.secretcodesandhacks.Device.fragments.g());
        paramAnonymousView = CrossfadeDrawerLayoutActvitiy.this.tvTitle;
        paramAnonymousA = CrossfadeDrawerLayoutActvitiy.this;
        paramAnonymousInt = 2131625893;
        break label520;
        CrossfadeDrawerLayoutActvitiy.a(CrossfadeDrawerLayoutActvitiy.this, new OSFragment());
        paramAnonymousView = CrossfadeDrawerLayoutActvitiy.this.tvTitle;
        paramAnonymousA = CrossfadeDrawerLayoutActvitiy.this;
        paramAnonymousInt = 2131625832;
        break label520;
        CrossfadeDrawerLayoutActvitiy.a(CrossfadeDrawerLayoutActvitiy.this, new HomeFragment());
        paramAnonymousView = CrossfadeDrawerLayoutActvitiy.this.tvTitle;
        paramAnonymousA = CrossfadeDrawerLayoutActvitiy.this;
        paramAnonymousInt = 2131624056;
        label520:
        paramAnonymousA = paramAnonymousA.getString(paramAnonymousInt);
        break label599;
        CrossfadeDrawerLayoutActvitiy.a(CrossfadeDrawerLayoutActvitiy.this, new DashboardFragment());
        paramAnonymousView = CrossfadeDrawerLayoutActvitiy.this.tvTitle;
        paramAnonymousA = new StringBuilder();
        paramAnonymousA.append("".concat(Build.BRAND));
        paramAnonymousA.append(" (");
        paramAnonymousA.append(Build.MODEL);
        paramAnonymousA.append(")");
        paramAnonymousA = paramAnonymousA.toString();
        label599:
        paramAnonymousView.setText(paramAnonymousA);
        label604:
        CrossfadeDrawerLayoutActvitiy.b(CrossfadeDrawerLayoutActvitiy.this);
        return false;
      }
    }).a(paramBundle).b(false).e();
    this.t = ((CrossfadeDrawerLayout)this.s.b());
    this.t.setMaxWidthPx(com.mikepenz.materialdrawer.e.c.a(this));
    paramBundle = this.s.e();
    localObject = paramBundle.a(this);
    ((View)localObject).setBackgroundColor(com.mikepenz.materialize.c.b.a(this, 2130968907, 2131099970));
    this.t.getSmallView().addView((View)localObject, -1, -1);
    paramBundle.a(new com.mikepenz.materialdrawer.c.a()
    {
      public void a()
      {
        boolean bool = b();
        CrossfadeDrawerLayoutActvitiy.d(CrossfadeDrawerLayoutActvitiy.this).i(400);
        if (bool) {
          CrossfadeDrawerLayoutActvitiy.e(CrossfadeDrawerLayoutActvitiy.this).b().f(8388611);
        }
      }
      
      public boolean b()
      {
        return CrossfadeDrawerLayoutActvitiy.d(CrossfadeDrawerLayoutActvitiy.this).e();
      }
    });
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle = this.s.a(paramBundle);
    super.onSaveInstanceState(this.r.a(paramBundle));
  }
}
