package com.logicx.secretcodesandhacks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.a;
import android.support.v4.app.h;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.c;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.logicx.secretcodesandhacks.Device.fragments.BatteryFragment;
import com.logicx.secretcodesandhacks.Device.fragments.DashboardFragment;
import com.logicx.secretcodesandhacks.Device.fragments.HomeFragment;
import com.logicx.secretcodesandhacks.Device.fragments.OSFragment;
import com.logicx.secretcodesandhacks.Device.fragments.a.b;
import com.logicx.secretcodesandhacks.Device.fragments.c;
import com.logicx.secretcodesandhacks.Device.fragments.f;
import com.logicx.secretcodesandhacks.Device.fragments.g;
import com.logicx.secretcodesandhacks.Device.fragments.i;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NewMainMenu
  extends android.support.v7.app.e
{
  public static int q;
  @BindView
  DrawerLayout drawerLayout;
  @BindView
  FrameLayout fragmentContainer;
  public com.logicx.secretcodesandhacks.Device.c.b n;
  @BindView
  NavigationView navigationView;
  public View o;
  public int p = -1;
  ImageView r;
  ImageView s;
  ImageView t;
  @BindView
  TextView tvTitle;
  ImageView u;
  Toolbar v;
  private Handler w = new Handler();
  
  public NewMainMenu() {}
  
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
  
  private boolean s()
  {
    return ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo() != null;
  }
  
  private void t()
  {
    this.navigationView.getMenu().getItem(q).setChecked(true);
  }
  
  private h u()
  {
    switch (q)
    {
    default: 
      return new HomeFragment();
    case 17: 
      return HomeFragment.c(7);
    case 16: 
      return HomeFragment.c(7);
    case 15: 
      return HomeFragment.c(7);
    case 13: 
    case 14: 
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("https://play.google.com/store/apps/details?id=");
      localStringBuilder.append(getPackageName());
      com.logicx.secretcodesandhacks.Device.c.d.a(localStringBuilder.toString());
      return null;
    case 12: 
      return com.logicx.secretcodesandhacks.Device.fragments.a.Z.a(1);
    case 11: 
      return com.logicx.secretcodesandhacks.Device.fragments.a.Z.a(2);
    case 10: 
      return new f();
    case 9: 
      return new com.logicx.secretcodesandhacks.Device.fragments.d();
    case 8: 
      return new i();
    case 7: 
      return new c();
    case 6: 
      return new com.logicx.secretcodesandhacks.Device.fragments.e();
    case 5: 
      return new BatteryFragment();
    case 4: 
      return new com.logicx.secretcodesandhacks.Device.fragments.b();
    case 3: 
      return new g();
    case 2: 
      return new OSFragment();
    case 1: 
      return new HomeFragment();
    }
    return new DashboardFragment();
  }
  
  public void m()
  {
    this.navigationView.getMenu().getItem(0).setChecked(true);
    this.navigationView.setItemIconTintList(null);
    Object localObject2 = this.navigationView.c(0);
    Object localObject1 = (TextView)((View)localObject2).findViewById(2131296710);
    TextView localTextView = (TextView)((View)localObject2).findViewById(2131296738);
    localObject2 = (LinearLayout)((View)localObject2).findViewById(2131296484);
    n();
    ((TextView)localObject1).setText("".concat(Build.BRAND));
    localTextView.setText("".concat(Build.MODEL));
    this.navigationView.setNavigationItemSelectedListener(new NavigationView.a()
    {
      public boolean a(MenuItem paramAnonymousMenuItem)
      {
        int i;
        switch (paramAnonymousMenuItem.getItemId())
        {
        case 2131296568: 
        default: 
          NewMainMenu.q = 0;
          break;
        case 2131296580: 
          i = 11;
          break;
        case 2131296579: 
          i = 12;
          break;
        case 2131296578: 
          i = 8;
          break;
        case 2131296577: 
          i = 13;
          break;
        case 2131296576: 
          i = 3;
          break;
        case 2131296575: 
          i = 14;
          break;
        case 2131296574: 
          i = 2;
          break;
        case 2131296573: 
          i = 6;
          break;
        case 2131296572: 
          i = 15;
          break;
        case 2131296571: 
          i = 10;
          break;
        case 2131296570: 
          i = 9;
          break;
        case 2131296569: 
          NewMainMenu.q = 1;
          break;
        case 2131296567: 
          i = 4;
          break;
        case 2131296566: 
          i = 16;
          break;
        case 2131296565: 
          i = 7;
          break;
        case 2131296564: 
          i = 5;
        }
        NewMainMenu.q = i;
        if (paramAnonymousMenuItem.isChecked()) {
          paramAnonymousMenuItem.setChecked(false);
        } else {
          paramAnonymousMenuItem.setChecked(true);
        }
        paramAnonymousMenuItem.setChecked(true);
        NewMainMenu.this.o();
        return true;
      }
    });
    localObject1 = new android.support.v7.app.b(this, this.drawerLayout, 2131625823, 2131625822);
    this.drawerLayout.a((DrawerLayout.c)localObject1);
    ((android.support.v7.app.b)localObject1).a();
  }
  
  public void n()
  {
    try
    {
      this.drawerLayout.a(new DrawerLayout.c()
      {
        public void a(int paramAnonymousInt) {}
        
        public void a(View paramAnonymousView)
        {
          NewMainMenu.this.drawerLayout.e(8388611);
        }
        
        @SuppressLint({"NewApi"})
        public void a(View paramAnonymousView, float paramAnonymousFloat)
        {
          try
          {
            float f = NewMainMenu.this.drawerLayout.getWidth();
            NewMainMenu.this.findViewById(2131296581).getWidth();
            if (Build.VERSION.SDK_INT >= 11) {
              return;
            }
            paramAnonymousView = new TranslateAnimation(0.0F, f * paramAnonymousFloat, 0.0F, 0.0F);
            paramAnonymousView.setDuration(0L);
            paramAnonymousView.setFillAfter(true);
            return;
          }
          catch (Exception paramAnonymousView)
          {
            paramAnonymousView.printStackTrace();
          }
        }
        
        public void b(View paramAnonymousView)
        {
          NewMainMenu.this.drawerLayout.f(8388611);
        }
      });
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void o()
  {
    t();
    if (this.p == q)
    {
      this.drawerLayout.b();
      return;
    }
    this.p = q;
    this.drawerLayout.b();
    this.w.postDelayed(new Runnable()
    {
      public void run()
      {
        h localH = NewMainMenu.b(NewMainMenu.this);
        if (localH != null)
        {
          NewMainMenu.this.n.a();
          NewMainMenu.this.n.a(localH, false, true);
        }
      }
    }, 300L);
  }
  
  public void onBackPressed()
  {
    if (this.drawerLayout.g(8388611))
    {
      this.drawerLayout.b();
      return;
    }
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427358);
    ButterKnife.a(this);
    this.v = ((Toolbar)findViewById(2131296667));
    this.r = ((ImageView)findViewById(2131296461));
    this.s = ((ImageView)findViewById(2131296456));
    this.t = ((ImageView)findViewById(2131296454));
    this.u = ((ImageView)findViewById(2131296460));
    paramBundle = this.tvTitle;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("".concat(Build.BRAND));
    localStringBuilder.append(" (");
    localStringBuilder.append(Build.MODEL);
    localStringBuilder.append(")");
    paramBundle.setText(localStringBuilder.toString());
    this.n = new com.logicx.secretcodesandhacks.Device.c.b(this);
    this.o = this.navigationView.c(0);
    this.o = this.navigationView.c(0);
    m();
    this.drawerLayout.setStatusBarBackground(2131099915);
    r();
    this.n.a();
    this.n.a(new DashboardFragment(), true, true);
    if (Build.VERSION.SDK_INT >= 21) {
      getWindow().setNavigationBarColor(android.support.v4.a.b.c(this, 2131099915));
    }
    this.r.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        NewMainMenu.this.p();
      }
    });
    this.t.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        NewMainMenu.this.q();
        if (NewMainMenu.a(NewMainMenu.this))
        {
          paramAnonymousView = new Intent("android.intent.action.VIEW");
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("https://play.google.com/store/apps/details?id=");
          localStringBuilder.append(NewMainMenu.this.getApplicationContext().getPackageName());
          paramAnonymousView.setData(Uri.parse(localStringBuilder.toString()));
          NewMainMenu.this.startActivity(paramAnonymousView);
          return;
        }
        Toast.makeText(NewMainMenu.this, "Please Check Internet Connection..!", 1).show();
      }
    });
    this.s.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        NewMainMenu.this.q();
        NewMainMenu.a(NewMainMenu.this);
      }
    });
  }
  
  public void p()
  {
    com.logicx.secretcodesandhacks.Device.c.d.b(this);
    if (this.drawerLayout.g(8388611))
    {
      this.drawerLayout.b();
      return;
    }
    this.drawerLayout.e(8388611);
  }
  
  public void q()
  {
    this.drawerLayout.f(8388611);
  }
  
  public List<com.logicx.secretcodesandhacks.Device.b.a> r()
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
}
