package com.dewmobile.kuaiya.fgmt;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.dewmobile.kuaiya.a.f;
import com.dewmobile.kuaiya.act.DmAudioPlayerActivity;
import com.dewmobile.kuaiya.act.MainActivity;
import com.dewmobile.kuaiya.act.TransferProgressingActivity;
import com.dewmobile.kuaiya.act.qr.DmQrActivity;
import com.dewmobile.kuaiya.adpt.DmCategory;
import com.dewmobile.kuaiya.app.MyApplication;
import com.dewmobile.kuaiya.b.a.c;
import com.dewmobile.kuaiya.ui.ZapyaTransferModeManager;
import com.dewmobile.kuaiya.ui.ZapyaTransferModeManager.ZapyaMode;
import com.dewmobile.kuaiya.ui.snackbar.SnackBar.a;
import com.dewmobile.kuaiya.ui.snackbar.SnackBar.b;
import com.dewmobile.kuaiya.util.z;
import com.dewmobile.kuaiya.view.DmMultiTouchLayout;
import com.dewmobile.kuaiya.view.DmViewPager;
import com.dewmobile.kuaiya.view.PagerSlidingTabStrip;
import com.dewmobile.kuaiya.view.PagerSlidingTabStrip.b;
import com.dewmobile.kuaiya.view.PagerSlidingTabStrip.c;
import com.dewmobile.kuaiya.view.j;
import com.dewmobile.kuaiya.view.j.a;
import com.dewmobile.kuaiya.view.s;
import com.dewmobile.library.i.e;
import com.dewmobile.library.logging.DmLog;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import org.json.JSONException;
import org.json.JSONObject;

public class bd
  extends i
  implements View.OnClickListener, ad.a, x, s
{
  public static DmCategory[] e;
  private static final String i = bd.class.getSimpleName();
  private static int[] m;
  private static int[] n;
  private static List<Integer> o;
  private boolean A = false;
  private boolean B = false;
  private View C;
  private View D;
  private ImageView E;
  private TextView F;
  private TextView G;
  private TextView H;
  private String I;
  private View J;
  private TitleFragment K;
  private View L;
  private String M;
  private boolean N = false;
  private BroadcastReceiver O = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if ("transfer.state.finish.act".equals(paramAnonymousIntent.getAction()))
      {
        paramAnonymousContext = paramAnonymousIntent.getStringExtra("res_string");
        bd.c(bd.this).setText(Html.fromHtml(paramAnonymousContext));
        bd.c(bd.this).setVisibility(0);
        bd.d(bd.this).removeCallbacksAndMessages(null);
        bd.d(bd.this).postDelayed(new Runnable()
        {
          public void run()
          {
            bd.c(bd.this).setVisibility(8);
          }
        }, 5000L);
        bd.e(bd.this);
      }
      while (!"transfer.state.transfer.act".equals(paramAnonymousIntent.getAction())) {
        return;
      }
      bd.f(bd.this);
    }
  };
  private BroadcastReceiver P = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      int i = 1;
      int j;
      if ("com.dewmobile.kuaiya.play.enter.app".equals(paramAnonymousIntent.getAction()))
      {
        j = paramAnonymousIntent.getIntExtra("position", -1);
        if (j != -1) {
          break label215;
        }
        if (!bd.a(bd.this)) {}
      }
      for (;;)
      {
        if ((bd.this.b != null) && (i < bd.this.d.getCount())) {
          bd.this.b.setCurrentItem(i);
        }
        label134:
        do
        {
          do
          {
            return;
            i = 0;
            break;
            if (!"com.dewmobile.kuaiya.play.taophone.request".equals(paramAnonymousIntent.getAction())) {
              break label134;
            }
          } while ((bd.g(bd.this) == null) || (!bd.a(bd.this)) || (bd.h(bd.this) == 0));
          bd.g(bd.this).setVisibility(0);
          return;
          if ("com.dewmobile.kuaiya.play.enter.local".equals(paramAnonymousIntent.getAction()))
          {
            bd.i(bd.this);
            return;
          }
          if ("com.dewmobile.kuaiya.play.enter.sendmode".equals(paramAnonymousIntent.getAction()))
          {
            bd.i(bd.this);
            return;
          }
        } while (!"com.dewmobile.kuaiya.play.ACTION_UI_SHOWN".equals(paramAnonymousIntent.getAction()));
        bd.a(bd.this, true);
        new Handler().post(new Runnable()
        {
          public void run()
          {
            if (bd.this.isVisible()) {
              bd.j(bd.this);
            }
          }
        });
        return;
        label215:
        i = j;
      }
    }
  };
  private com.dewmobile.kuaiya.b.a.a Q = new com.dewmobile.kuaiya.b.a.a()
  {
    public void a(com.dewmobile.kuaiya.b.a.b paramAnonymousB)
    {
      if ((bd.this.isAdded()) && (paramAnonymousB != null))
      {
        if (paramAnonymousB.a != 5) {
          break label170;
        }
        if (paramAnonymousB.c != 0) {
          break label145;
        }
        bd.k(bd.this).setVisibility(4);
        if (paramAnonymousB.e - paramAnonymousB.c <= 0) {
          break label131;
        }
        i = paramAnonymousB.e - paramAnonymousB.c;
        if (i <= 99) {
          break label123;
        }
        paramAnonymousB = 99 + "+";
        bd.l(bd.this).setText(paramAnonymousB);
        bd.l(bd.this).setVisibility(0);
      }
      for (;;)
      {
        bd.l(bd.this).setVisibility(4);
        return;
        label123:
        paramAnonymousB = String.valueOf(i);
        break;
        label131:
        bd.l(bd.this).setVisibility(4);
        continue;
        label145:
        bd.k(bd.this).setVisibility(0);
        bd.l(bd.this).setVisibility(4);
        continue;
        label170:
        if (paramAnonymousB.a == 10) {
          if (paramAnonymousB.e - paramAnonymousB.c > 0)
          {
            bd.l(bd.this).setVisibility(4);
            bd.k(bd.this).setVisibility(0);
          }
          else
          {
            bd.k(bd.this).setVisibility(4);
            if (paramAnonymousB.c != 0) {
              break label248;
            }
            bd.l(bd.this).setVisibility(4);
          }
        }
      }
      label248:
      bd.l(bd.this).setVisibility(0);
      int i = paramAnonymousB.c;
      if (i > 99) {}
      for (paramAnonymousB = 99 + "+";; paramAnonymousB = String.valueOf(i))
      {
        bd.l(bd.this).setText(paramAnonymousB);
        break;
      }
    }
  };
  private boolean R = true;
  private boolean S = false;
  private boolean T = false;
  private boolean U = false;
  private PagerSlidingTabStrip.b V = new PagerSlidingTabStrip.b()
  {
    public View a(int paramAnonymousInt, ViewGroup paramAnonymousViewGroup)
    {
      int i;
      if (!bd.a(bd.this))
      {
        i = bd.e()[paramAnonymousInt];
        paramAnonymousViewGroup = bd.r(bd.this).inflate(2130903501, paramAnonymousViewGroup, false);
        ((TextView)paramAnonymousViewGroup.findViewById(2131558489)).setText(bd.this.d.getPageTitle(paramAnonymousInt));
        paramAnonymousInt = i;
      }
      for (;;)
      {
        ((ImageView)paramAnonymousViewGroup.findViewById(2131558598)).setImageResource(paramAnonymousInt);
        return paramAnonymousViewGroup;
        paramAnonymousViewGroup = bd.r(bd.this).inflate(2130903502, paramAnonymousViewGroup, false);
        i = ((Integer)bd.f().get(paramAnonymousInt)).intValue();
        View localView = paramAnonymousViewGroup.findViewById(2131558668);
        if (paramAnonymousInt == 0)
        {
          bd.a(bd.this, (TextView)paramAnonymousViewGroup.findViewById(2131558955));
          if (bd.s(bd.this))
          {
            localView.setVisibility(8);
            paramAnonymousInt = i;
          }
          else
          {
            localView.setVisibility(0);
            paramAnonymousInt = i;
          }
        }
        else
        {
          localView.setVisibility(4);
          paramAnonymousInt = i;
        }
      }
    }
  };
  private boolean W = false;
  private boolean X = false;
  f a;
  DmViewPager b;
  PagerSlidingTabStrip c;
  a d;
  Animation f;
  boolean g = false;
  j.a h = new j.a()
  {
    public void a()
    {
      if (bd.this.b != null) {
        bd.this.b.setScrollable(true);
      }
    }
  };
  private boolean[] j;
  private j k;
  private FragmentManager l;
  private com.dewmobile.library.g.b p;
  private String q;
  private String r = null;
  private int s = 0;
  private int t;
  private int[] u = new int[5];
  private boolean v;
  private LayoutInflater w;
  private boolean x = false;
  private TextView y;
  private Handler z;
  
  public bd() {}
  
  private String a(int paramInt, boolean paramBoolean)
  {
    int i2 = 0;
    int i1 = paramInt;
    if (this.b != null)
    {
      i1 = paramInt;
      if (paramBoolean) {
        i1 = this.b.getCurrentItem();
      }
    }
    if (this.A)
    {
      if (i1 == 0) {
        return "page_hometao";
      }
      paramInt = i1 - 1;
    }
    for (;;)
    {
      i1 = paramInt;
      if (paramInt < 0) {
        i1 = 0;
      }
      if (e != null) {
        break;
      }
      return "page_app";
      paramInt = i1;
      if (i1 >= 5) {
        paramInt = 4;
      }
    }
    if (i1 >= e.length) {}
    for (paramInt = i2;; paramInt = i1)
    {
      DmCategory localDmCategory = e[paramInt];
      return "page_" + localDmCategory.toString();
    }
  }
  
  private static boolean b(DmCategory paramDmCategory, String paramString)
  {
    if ("0".equals(paramString)) {
      if ((paramDmCategory.d()) || (paramDmCategory.e()) || (paramDmCategory.g())) {}
    }
    do
    {
      return true;
      return false;
      if ("1".equals(paramString)) {
        return false;
      }
    } while (!"2".equals(paramString));
    return true;
  }
  
  private void c(boolean paramBoolean)
  {
    if (this.b != null) {
      this.b.setScrollable(paramBoolean);
    }
  }
  
  private void d(boolean paramBoolean)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("com.dewmobile.kuaiya.play.action.multi_mode_update");
    localIntent.putExtra("mode_state", paramBoolean);
    getActivity().sendBroadcast(localIntent);
  }
  
  private void e(boolean paramBoolean)
  {
    int i3 = 5;
    int i4 = 0;
    Object localObject = (FrameLayout.LayoutParams)this.J.getLayoutParams();
    int i1;
    label71:
    label101:
    int i2;
    if (paramBoolean)
    {
      i1 = z.a(getContext(), 80.0F);
      ((FrameLayout.LayoutParams)localObject).topMargin = i1;
      this.J.setLayoutParams((ViewGroup.LayoutParams)localObject);
      localObject = this.l.beginTransaction();
      if (!paramBoolean) {
        break label305;
      }
      ((FragmentTransaction)localObject).hide(this.K);
      ((FragmentTransaction)localObject).commitAllowingStateLoss();
      if ((this.A) && (paramBoolean) && ((this.A) || (paramBoolean))) {
        break label318;
      }
      i1 = 1;
      this.A = paramBoolean;
      if (!paramBoolean) {
        break label323;
      }
      i2 = 0;
      label112:
      if (this.C != null)
      {
        this.C.setVisibility(i2);
        if (!paramBoolean) {
          break label328;
        }
      }
      label131:
      if ((this.d != null) && (i1 != 0))
      {
        if (!paramBoolean) {
          break label335;
        }
        this.N = true;
        this.d.a(0, "tao");
        label162:
        i1 = this.t;
        if (!paramBoolean) {
          break label371;
        }
        i2 = i1 + 1;
        i1 = i2;
        if (i2 > 5) {
          i1 = 5;
        }
        label184:
        if (this.c != null)
        {
          localObject = (RelativeLayout.LayoutParams)this.c.getLayoutParams();
          if (!paramBoolean) {
            break label387;
          }
        }
      }
    }
    label305:
    label318:
    label323:
    label328:
    label335:
    label371:
    label387:
    for (((RelativeLayout.LayoutParams)localObject).width = -2;; ((RelativeLayout.LayoutParams)localObject).width = -1)
    {
      this.c.setLayoutParams((ViewGroup.LayoutParams)localObject);
      this.c.a();
      i2 = i3;
      if (paramBoolean) {
        i2 = 6;
      }
      this.u = new int[i2];
      if (this.u[i1] != 1)
      {
        localObject = this.d.b(i1);
        if ((localObject instanceof ResourceBaseFragment)) {
          ((ResourceBaseFragment)localObject).j();
        }
        this.u[i1] = 1;
      }
      return;
      i1 = getResources().getDimensionPixelSize(2131165258);
      break;
      ((FragmentTransaction)localObject).show(this.K);
      break label71;
      i1 = 0;
      break label101;
      i2 = 4;
      break label112;
      h();
      break label131;
      if (!"tao".equals((String)this.d.c(0))) {
        break label162;
      }
      this.N = true;
      this.d.a(0);
      break label162;
      i2 = i1 - 1;
      i1 = i4;
      if (i2 < 0) {
        break label184;
      }
      i1 = i2;
      break label184;
    }
  }
  
  private void f(final boolean paramBoolean)
  {
    if (this.W) {}
    while ((!"HUAWEI".equals(Build.MANUFACTURER)) || (Build.VERSION.SDK_INT <= 20)) {
      return;
    }
    e.c.execute(new Runnable()
    {
      public void run()
      {
        if (paramBoolean) {}
        try
        {
          Thread.sleep(2000L);
          if (bd.this.isVisible())
          {
            bd.c(bd.this, true);
            List localList = MyApplication.b.getPackageManager().getInstalledPackages(128);
            if ((localList != null) && (localList.size() < 4) && (bd.this.getActivity() != null)) {
              bd.this.getActivity().runOnUiThread(new Runnable()
              {
                public void run()
                {
                  if ((bd.this.getActivity() == null) || (bd.t(bd.this))) {
                    return;
                  }
                  bd.d(bd.this, true);
                  new SnackBar.a(bd.this.getActivity(), bd.u(bd.this)).a(new SnackBar.b()
                  {
                    public void a(Parcelable paramAnonymous3Parcelable)
                    {
                      try
                      {
                        paramAnonymous3Parcelable = new Intent();
                        paramAnonymous3Parcelable.setFlags(268435456);
                        paramAnonymous3Parcelable.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.PermissionSettingActivity"));
                        bd.this.startActivity(paramAnonymous3Parcelable);
                        return;
                      }
                      catch (Exception paramAnonymous3Parcelable)
                      {
                        try
                        {
                          paramAnonymous3Parcelable = new Intent();
                          paramAnonymous3Parcelable.setFlags(268435456);
                          paramAnonymous3Parcelable.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity"));
                          bd.this.startActivity(paramAnonymous3Parcelable);
                          return;
                        }
                        catch (Exception paramAnonymous3Parcelable)
                        {
                          Toast.makeText(bd.this.getActivity(), 2131232449, 1).show();
                        }
                      }
                    }
                  }).a(2131232449).b(2131230907).a(Short.valueOf((short)5000)).a();
                }
              });
            }
          }
          return;
        }
        catch (InterruptedException localInterruptedException)
        {
          for (;;) {}
        }
      }
    });
  }
  
  private void g()
  {
    if (this.g) {}
    do
    {
      return;
      if (this.f == null)
      {
        this.f = AnimationUtils.loadAnimation(com.dewmobile.library.d.b.a(), 2130968604);
        LinearInterpolator localLinearInterpolator = new LinearInterpolator();
        this.f.setInterpolator(localLinearInterpolator);
      }
    } while (this.E == null);
    this.E.clearAnimation();
    this.E.startAnimation(this.f);
    this.g = true;
  }
  
  private void h()
  {
    if ((this.E != null) && (this.f != null))
    {
      this.E.clearAnimation();
      this.f.cancel();
      this.g = false;
    }
  }
  
  private void i()
  {
    if (ZapyaTransferModeManager.a().m()) {
      this.H.setText(2131231641);
    }
    for (;;)
    {
      this.H.setVisibility(0);
      this.z.postDelayed(new Runnable()
      {
        public void run()
        {
          TranslateAnimation localTranslateAnimation = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 0.0F, 1, -2.0F);
          localTranslateAnimation.setDuration(500L);
          bd.b(bd.this).startAnimation(localTranslateAnimation);
          localTranslateAnimation.setAnimationListener(new Animation.AnimationListener()
          {
            public void onAnimationEnd(Animation paramAnonymous2Animation)
            {
              bd.b(bd.this).setVisibility(8);
            }
            
            public void onAnimationRepeat(Animation paramAnonymous2Animation) {}
            
            public void onAnimationStart(Animation paramAnonymous2Animation) {}
          });
        }
      }, 3000L);
      return;
      this.H.setText(2131231644);
    }
  }
  
  private void j()
  {
    e = new DmCategory[5];
    m = new int[5];
    n = new int[5];
    o = new ArrayList(5);
    this.j = new boolean[5];
    int i1 = 0;
    while (i1 < 5)
    {
      this.j[i1] = false;
      i1 += 1;
    }
  }
  
  private void k()
  {
    String str = a(this.t, true);
    if ((this.M != null) && (this.M.equals(str))) {
      return;
    }
    this.M = str;
    com.dewmobile.kuaiya.g.a.a(this.M);
  }
  
  private void l()
  {
    if (this.M != null)
    {
      com.dewmobile.kuaiya.g.a.b(this.M);
      this.M = null;
    }
  }
  
  private void m()
  {
    this.p = com.dewmobile.library.g.b.a();
    this.q = this.p.h();
    this.a = f.a();
    this.b.setOffscreenPageLimit(6);
    this.d = new a(this.l, this.k, getResources());
    Object localObject1 = e;
    int i2 = localObject1.length;
    int i1 = 0;
    while (i1 < i2)
    {
      Object localObject2 = localObject1[i1];
      this.d.a(localObject2.toString());
      i1 += 1;
    }
    this.b.setAdapter(this.d);
    this.b.setCurrentItem(this.s);
    this.t = this.s;
    try
    {
      localObject1 = new JSONObject();
      ((JSONObject)localObject1).put("rid", this.s);
      com.dewmobile.kuaiya.g.a.a(com.dewmobile.library.d.b.a(), "z-400-0046", ((JSONObject)localObject1).toString());
      this.c.setAdapter(this.V);
      this.c.setViewPager(this.b);
      this.c.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
      {
        private boolean b = false;
        
        public void onPageScrollStateChanged(int paramAnonymousInt)
        {
          if (paramAnonymousInt == 0)
          {
            paramAnonymousInt = bd.this.b.getCurrentItem();
            Fragment localFragment = bd.this.d.b(paramAnonymousInt);
            if ((localFragment instanceof ResourceBaseFragment)) {
              ((ResourceBaseFragment)localFragment).c();
            }
          }
          else
          {
            return;
          }
          ((DmMultiTouchLayout)bd.this.getActivity().findViewById(2131559612)).a(null);
        }
        
        public void onPageScrolled(int paramAnonymousInt1, float paramAnonymousFloat, int paramAnonymousInt2)
        {
          paramAnonymousInt2 = paramAnonymousInt1;
          if (paramAnonymousFloat > 0.0F) {
            paramAnonymousInt2 = paramAnonymousInt1 + 1;
          }
          if (bd.a(bd.this))
          {
            paramAnonymousInt2 -= 1;
            paramAnonymousInt1 = paramAnonymousInt2;
            if (paramAnonymousInt2 >= 0) {}
          }
          for (paramAnonymousInt1 = 0;; paramAnonymousInt1 = paramAnonymousInt2)
          {
            if (paramAnonymousInt1 >= 5) {
              paramAnonymousInt1 = 4;
            }
            for (;;)
            {
              if (bd.p(bd.this)[paramAnonymousInt1] != 1)
              {
                Fragment localFragment = bd.this.d.b(paramAnonymousInt1);
                if ((localFragment instanceof ResourceBaseFragment)) {
                  ((ResourceBaseFragment)localFragment).j();
                }
                bd.p(bd.this)[paramAnonymousInt1] = 1;
              }
              return;
            }
          }
        }
        
        public void onPageSelected(int paramAnonymousInt)
        {
          if ((!bd.m(bd.this)) && (!com.dewmobile.kuaiya.g.a.c)) {
            bd.n(bd.this);
          }
          bd.a(bd.this, paramAnonymousInt);
          if ((!bd.m(bd.this)) && (!com.dewmobile.kuaiya.g.a.c)) {
            bd.j(bd.this);
          }
          bd.b(bd.this, false);
          com.dewmobile.kuaiya.g.a.a(com.dewmobile.library.d.b.a(), "z-400-0046", bd.this.a(paramAnonymousInt));
          bd.this.a(true);
          if (((!bd.a(bd.this)) && (paramAnonymousInt == 3)) || ((bd.a(bd.this)) && (paramAnonymousInt == 4) && (!this.b)))
          {
            this.b = true;
            DmAudioPlayerActivity.c();
          }
          if (bd.this.getActivity() != null) {
            ((MainActivity)bd.this.getActivity()).a(3, bd.h(bd.this));
          }
          if ((paramAnonymousInt == 0) && (bd.a(bd.this)))
          {
            View localView = bd.this.c.getChildAt(paramAnonymousInt);
            if (localView != null)
            {
              localView = localView.findViewById(2131558668);
              if (localView != null)
              {
                localView.setVisibility(4);
                bd.o(bd.this);
                if (bd.g(bd.this) != null) {
                  bd.g(bd.this).setVisibility(8);
                }
              }
            }
          }
        }
      });
      if (this.r != null)
      {
        this.d.e = this.r;
        this.d.f = this.s;
      }
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
  }
  
  private void n()
  {
    m[0] = 2131231228;
    m[1] = 2131231236;
    m[2] = 2131231234;
    m[3] = 2131231235;
    m[4] = 2131231238;
    n[0] = 2130838438;
    n[1] = 2130838444;
    n[2] = 2130838447;
    n[3] = 2130838440;
    n[4] = 2130838442;
    o.add(Integer.valueOf(2130838446));
    o.add(Integer.valueOf(2130838439));
    o.add(Integer.valueOf(2130838445));
    o.add(Integer.valueOf(2130838448));
    o.add(Integer.valueOf(2130838441));
    o.add(Integer.valueOf(2130838443));
    e[0] = new DmCategory(1, 0, m[0]);
    e[1] = new DmCategory(4, 1, m[1]);
    e[2] = new DmCategory(3, 0, m[2]);
    e[3] = new DmCategory(2, 0, m[3]);
    e[4] = new DmCategory(8, 0, m[4]);
  }
  
  private void o()
  {
    this.U = true;
    SharedPreferences.Editor localEditor = com.dewmobile.library.d.b.a.getSharedPreferences("connect_tips", 0).edit();
    localEditor.putBoolean("has_showed", true);
    localEditor.apply();
  }
  
  private boolean p()
  {
    if (this.U) {
      return this.U;
    }
    this.U = com.dewmobile.library.d.b.a.getSharedPreferences("connect_tips", 0).getBoolean("has_showed", false);
    return this.U;
  }
  
  public int a()
  {
    return this.t;
  }
  
  protected String a(int paramInt)
  {
    if (!this.A)
    {
      switch (paramInt)
      {
      default: 
        return null;
      case 0: 
        return "app";
      case 1: 
        return "photo";
      case 2: 
        return "video";
      case 3: 
        return "audio";
      }
      return "file";
    }
    switch (paramInt)
    {
    default: 
      return null;
    case 0: 
      return "tao";
    case 1: 
      return "app";
    case 2: 
      return "photo";
    case 3: 
      return "video";
    case 4: 
      return "audio";
    }
    return "file";
  }
  
  public void a(Fragment paramFragment, boolean paramBoolean)
  {
    this.v = paramBoolean;
    if (!paramBoolean) {}
    for (boolean bool = true;; bool = false)
    {
      c(bool);
      d(paramBoolean);
      if (!this.v) {
        break;
      }
      ((MainActivity)getActivity()).d(2);
      return;
    }
    ((MainActivity)getActivity()).c(1);
  }
  
  public void a(j paramJ)
  {
    this.k = paramJ;
    if (this.k != null) {
      this.k.a(this.h);
    }
  }
  
  public void a(String paramString, ArrayList<Uri> paramArrayList)
  {
    if ((paramArrayList == null) && (paramString == null)) {}
    String str2;
    String str1;
    int i1;
    Object localObject;
    label141:
    label153:
    do
    {
      do
      {
        do
        {
          return;
          str2 = "enterFolderByUri(uriPath=" + paramArrayList + ")";
          str1 = null;
          if (paramString == null) {
            break;
          }
          paramArrayList = new ArrayList();
          i1 = paramString.lastIndexOf("/");
        } while (i1 < 1);
        localObject = paramString.substring(0, i1);
        paramArrayList.add(paramString.substring(i1 + 1));
        DmLog.d(i, str2 + "FILE:folderPath=" + (String)localObject + ",fileList=" + paramArrayList);
        paramString = (String)localObject;
        if (!TextUtils.isEmpty(paramString)) {
          break label333;
        }
        paramString = "/";
        if (!this.A) {
          break label323;
        }
        b(5);
        localObject = b();
      } while (!(b() instanceof bb));
      ((bb)localObject).a(paramString, paramArrayList);
      return;
      localObject = new ArrayList(paramArrayList.size());
      i1 = 0;
      paramString = str1;
      if (i1 >= paramArrayList.size()) {
        break;
      }
      str1 = com.dewmobile.library.f.a.a().a((Uri)paramArrayList.get(i1));
    } while (TextUtils.isEmpty(str1));
    int i2 = str1.lastIndexOf("/");
    if (paramString == null) {}
    for (;;)
    {
      try
      {
        paramString = str1.substring(0, i2);
        ((ArrayList)localObject).add(str1.substring(i2 + 1));
        i1 += 1;
      }
      catch (Exception paramString)
      {
        label323:
        return;
      }
      DmLog.d(i, str2 + "URI:firstPath=" + paramString + ",fileList=" + localObject);
      paramArrayList = (ArrayList<Uri>)localObject;
      break;
      b(4);
      break label153;
      label333:
      break label141;
    }
  }
  
  public void a(ArrayList<Uri> paramArrayList, String paramString)
  {
    c(false);
    if (isAdded())
    {
      if (this.v) {
        a(false);
      }
      a(paramString, paramArrayList);
    }
  }
  
  public boolean a(boolean paramBoolean)
  {
    if ((this.d == null) || (this.b == null)) {
      return false;
    }
    if (this.d.c(this.t) == null) {
      return false;
    }
    Fragment localFragment = this.d.b(a());
    if (this.v)
    {
      if ((localFragment instanceof ad.b)) {
        ((ad.b)localFragment).b(false);
      }
      return true;
    }
    if ((localFragment instanceof x)) {
      return ((x)localFragment).a(paramBoolean);
    }
    return false;
  }
  
  public Fragment b()
  {
    if (this.d != null) {
      return this.d.b(a());
    }
    return null;
  }
  
  public void b(int paramInt)
  {
    int i1 = 5;
    if (this.A) {
      i1 = 6;
    }
    if ((!isAdded()) || (paramInt < 0) || (paramInt >= i1) || (paramInt == this.t)) {
      return;
    }
    this.t = paramInt;
    this.b.setCurrentItem(paramInt);
  }
  
  public void b(boolean paramBoolean)
  {
    if (this.J != null)
    {
      e(paramBoolean);
      return;
    }
    this.B = paramBoolean;
  }
  
  public void c()
  {
    Fragment localFragment = b();
    if ((localFragment instanceof ResourceBaseFragment)) {
      ((ResourceBaseFragment)localFragment).g();
    }
  }
  
  public void c(int paramInt)
  {
    b(paramInt);
  }
  
  public void d(int paramInt)
  {
    if (paramInt < 5) {
      this.s = paramInt;
    }
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    paramBundle = getActivity();
    if ((paramBundle instanceof MainActivity)) {
      ((MainActivity)paramBundle).a(this.K);
    }
    this.w = paramBundle.getLayoutInflater();
    j();
    n();
    m();
    paramBundle = new ArrayList();
    paramBundle.add(new c(5, 0));
    paramBundle.add(new c(10, 0));
    this.I = com.dewmobile.kuaiya.b.a.i.a().a(paramBundle, this.Q);
    e(this.B);
    f(true);
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
    case 2131559053: 
    case 2131560185: 
      do
      {
        return;
        startActivity(new Intent(getContext(), TransferProgressingActivity.class));
        com.dewmobile.kuaiya.g.a.a(getContext(), "z-400-0024", "resource");
      } while (this.F.getVisibility() != 0);
      com.dewmobile.kuaiya.g.a.a(getContext(), "z-410-0018", "histsmall");
      return;
    }
    paramView = new Intent(getActivity().getApplicationContext(), DmQrActivity.class);
    getActivity().startActivityForResult(paramView, 1555);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903503, paramViewGroup, false);
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    if (this.k != null) {
      this.k.b(this.h);
    }
    l();
    this.k = null;
    this.d = null;
    this.c.setOnPageChangeListener(null);
    this.c.setAdapter(null);
    this.z.removeCallbacksAndMessages(null);
    com.dewmobile.kuaiya.b.a.i.a().a(this.I, this.Q);
    LocalBroadcastManager.getInstance(com.dewmobile.library.d.b.a()).unregisterReceiver(this.O);
  }
  
  public void onDetach()
  {
    super.onDetach();
    LocalBroadcastManager.getInstance(com.dewmobile.library.d.b.a()).unregisterReceiver(this.P);
  }
  
  public void onHiddenChanged(boolean paramBoolean)
  {
    super.onHiddenChanged(paramBoolean);
    if (this.S)
    {
      if (!paramBoolean) {
        break label42;
      }
      l();
    }
    for (;;)
    {
      if ((!paramBoolean) && (!this.R)) {
        f(false);
      }
      this.R = false;
      return;
      label42:
      k();
    }
  }
  
  public void onPause()
  {
    super.onPause();
    if (!isHidden())
    {
      MainActivity localMainActivity = (MainActivity)getActivity();
      if ((localMainActivity != null) && (!localMainActivity.g())) {
        break label31;
      }
    }
    label31:
    while ((!this.S) || (this.M == null) || (!this.M.equals(com.dewmobile.kuaiya.g.a.a))) {
      return;
    }
    l();
  }
  
  public void onResume()
  {
    super.onResume();
    if ((!isHidden()) && (isVisible()) && (this.S))
    {
      MainActivity localMainActivity = (MainActivity)getActivity();
      if ((localMainActivity != null) && (!localMainActivity.g())) {}
    }
    else
    {
      return;
    }
    k();
  }
  
  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.L = paramView.findViewById(2131559562);
    this.b = ((DmViewPager)paramView.findViewById(2131558502));
    this.b.setPageMargin(1);
    this.J = paramView.findViewById(2131558488);
    this.c = ((PagerSlidingTabStrip)paramView.findViewById(2131558501));
    this.c.setTabClickListerner(new PagerSlidingTabStrip.c()
    {
      public void a(int paramAnonymousInt)
      {
        if (bd.a(bd.this)) {}
        for (String str = "z-400-0048";; str = "z-400-0047")
        {
          com.dewmobile.kuaiya.g.a.a(com.dewmobile.library.d.b.a(), str, bd.this.a(paramAnonymousInt));
          return;
        }
      }
    });
    this.l = getChildFragmentManager();
    this.K = ((TitleFragment)this.l.findFragmentById(2131560184));
    this.K.a();
    this.E = ((ImageView)paramView.findViewById(2131560187));
    this.C = paramView.findViewById(2131560185);
    this.C.setOnClickListener(this);
    if (this.A) {
      this.C.setVisibility(0);
    }
    for (;;)
    {
      this.D = this.C.findViewById(2131560188);
      this.F = ((TextView)this.C.findViewById(2131558668));
      this.y = ((TextView)paramView.findViewById(2131560189));
      this.H = ((TextView)paramView.findViewById(2131560190));
      this.z = new Handler(Looper.getMainLooper());
      paramView = new IntentFilter("transfer.state.finish.act");
      paramView.addAction("transfer.state.transfer.act");
      LocalBroadcastManager.getInstance(com.dewmobile.library.d.b.a()).registerReceiver(this.O, paramView);
      paramView = new IntentFilter("com.dewmobile.kuaiya.play.enter.app");
      paramView.addAction("com.dewmobile.kuaiya.play.taophone.request");
      paramView.addAction("com.dewmobile.kuaiya.play.enter.local");
      paramView.addAction("com.dewmobile.kuaiya.play.enter.sendmode");
      paramView.addAction("com.dewmobile.kuaiya.play.ACTION_UI_SHOWN");
      LocalBroadcastManager.getInstance(com.dewmobile.library.d.b.a()).registerReceiver(this.P, paramView);
      if ((ZapyaTransferModeManager.a().i() == ZapyaTransferModeManager.ZapyaMode.c) || (ZapyaTransferModeManager.a().m())) {
        i();
      }
      return;
      this.C.setVisibility(4);
    }
  }
  
  private class a
    extends com.dewmobile.kuaiya.view.a<String>
  {
    j a;
    Resources b;
    int c;
    int d;
    String e;
    int f;
    
    public a(FragmentManager paramFragmentManager, j paramJ, Resources paramResources)
    {
      super();
      this.a = paramJ;
      this.b = paramResources;
      this.c = this.b.getDimensionPixelSize(2131165248);
      this.d = this.b.getDimensionPixelSize(2131165247);
    }
    
    public Fragment a(String paramString, int paramInt)
    {
      int i;
      Bundle localBundle;
      if (bd.a(bd.this))
      {
        if (paramInt == 0) {
          return new bg();
        }
        i = paramInt - 1;
        localBundle = new Bundle();
        localBundle.putInt("position", i);
        paramString = bd.e[i];
        if ((i == this.f) && (this.e != null)) {
          paramString.a(this.e);
        }
        localBundle.putParcelable("category", paramString);
        if (!paramString.e()) {
          break label120;
        }
        paramString = new ay();
      }
      for (;;)
      {
        paramString.a(this.a);
        paramString.setArguments(localBundle);
        return paramString;
        i = paramInt;
        if (paramInt < 5) {
          break;
        }
        return null;
        label120:
        if (paramString.b())
        {
          paramString = new az();
        }
        else
        {
          if (paramString.j())
          {
            paramString = new bb();
            paramString.a(bd.q(bd.this));
            return paramString;
          }
          if (paramString.k()) {
            return new bh();
          }
          if (bd.a(paramString, "0"))
          {
            if (paramString.h()) {
              paramString = new av();
            } else if (paramString.f()) {
              paramString = new ap();
            } else if (!paramString.c()) {
              paramString = new ax();
            } else {
              paramString = new az();
            }
          }
          else if (!paramString.d()) {
            paramString = new ay();
          } else {
            paramString = new ar();
          }
        }
      }
    }
    
    public CharSequence getPageTitle(int paramInt)
    {
      int i = paramInt;
      if (bd.a(bd.this)) {
        if (paramInt != 0) {
          break label31;
        }
      }
      label31:
      for (i = paramInt;; i = paramInt - 1) {
        return this.b.getString(bd.d()[i]);
      }
    }
  }
}
