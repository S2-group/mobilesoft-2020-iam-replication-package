package app.magic.cleaner.boost;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.h;
import android.support.v7.widget.RecyclerView.i;
import android.support.v7.widget.RecyclerView.j;
import android.support.v7.widget.RecyclerView.u;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import b.a.a.a.c;
import com.github.ybq.android.spinkit.c.m;
import com.skyfishjy.library.RippleBackground;
import com.wang.avi.AVLoadingIndicatorView;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Sacnning_Junk
  extends Activity
  implements a.a
{
  AVLoadingIndicatorView a;
  AVLoadingIndicatorView b;
  AVLoadingIndicatorView c;
  AVLoadingIndicatorView d;
  AVLoadingIndicatorView e;
  AVLoadingIndicatorView f;
  ImageView g;
  ImageView h;
  int i = 0;
  TextView j;
  List<ApplicationInfo> k;
  int l = 0;
  Timer m;
  app.magic.cleaner.boost.BCJ.a n;
  RecyclerView o;
  public List<app.magic.cleaner.boost.a.a> p;
  PackageManager q;
  TextView r;
  private a s;
  
  public Sacnning_Junk() {}
  
  private a c()
  {
    if (this.s == null) {
      d();
    }
    return this.s;
  }
  
  private void d()
  {
    if (this.s == null)
    {
      this.s = new a(this, this);
      return;
    }
    this.s.c();
  }
  
  public void a()
  {
    if (c().b) {
      c().a();
    }
  }
  
  void a(int paramInt)
  {
    if (paramInt == 1)
    {
      this.a.b();
      this.c.b();
      this.e.b();
      this.b.a();
      this.d.a();
    }
    for (AVLoadingIndicatorView localAVLoadingIndicatorView = this.f;; localAVLoadingIndicatorView = this.e)
    {
      localAVLoadingIndicatorView.a();
      return;
      if (paramInt != 2) {
        break;
      }
      this.b.b();
      this.d.b();
      this.f.b();
      this.a.a();
      this.c.a();
    }
    if (paramInt == 3)
    {
      this.b.b();
      this.d.b();
      this.f.b();
      this.a.b();
      this.c.b();
    }
    for (localAVLoadingIndicatorView = this.e;; localAVLoadingIndicatorView = this.f)
    {
      localAVLoadingIndicatorView.b();
      return;
      if (paramInt != 4) {
        break;
      }
      this.b.b();
      this.c.b();
      this.e.b();
      this.a.b();
      this.b.b();
    }
  }
  
  public void b()
  {
    if (c().b) {
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          Sacnning_Junk.this.finish();
        }
      }, 1000L);
    }
  }
  
  public void onBackPressed() {}
  
  protected void onCreate(final Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getIntent().getExtras();
    setContentView(2131492958);
    this.a = ((AVLoadingIndicatorView)findViewById(2131296504));
    this.b = ((AVLoadingIndicatorView)findViewById(2131296505));
    this.c = ((AVLoadingIndicatorView)findViewById(2131296506));
    this.d = ((AVLoadingIndicatorView)findViewById(2131296507));
    this.e = ((AVLoadingIndicatorView)findViewById(2131296508));
    this.f = ((AVLoadingIndicatorView)findViewById(2131296509));
    this.j = ((TextView)findViewById(2131296399));
    this.h = ((ImageView)findViewById(2131296326));
    this.r = ((TextView)findViewById(2131296512));
    this.p = new ArrayList();
    Object localObject = new RotateAnimation(0.0F, 360.0F, 1, 0.5F, 1, 0.5F);
    ((RotateAnimation)localObject).setDuration(1500L);
    ((RotateAnimation)localObject).setRepeatCount(4);
    ((RotateAnimation)localObject).setInterpolator(new LinearInterpolator());
    ((RotateAnimation)localObject).setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        Sacnning_Junk.this.m.cancel();
        Sacnning_Junk.this.m.purge();
        Sacnning_Junk.this.a.a();
        Sacnning_Junk.this.b.a();
        Sacnning_Junk.this.c.a();
        Sacnning_Junk.this.d.a();
        Sacnning_Junk.this.e.a();
        Sacnning_Junk.this.f.a();
        Sacnning_Junk.this.j.setText("");
      }
      
      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
        paramAnonymousAnimation = Sacnning_Junk.this;
        paramAnonymousAnimation.i += 1;
        Sacnning_Junk.this.a(Sacnning_Junk.this.i);
      }
      
      public void onAnimationStart(Animation paramAnonymousAnimation) {}
    });
    this.g = ((ImageView)findViewById(2131296411));
    this.g.startAnimation((Animation)localObject);
    this.q = getPackageManager();
    this.k = this.q.getInstalledApplications(0);
    localObject = (ActivityManager)getSystemService("activity");
    this.m = new Timer();
    this.m.scheduleAtFixedRate(new TimerTask()
    {
      public void run()
      {
        Sacnning_Junk.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            if (Sacnning_Junk.this.l < Sacnning_Junk.this.k.size())
            {
              Object localObject = Sacnning_Junk.this.j;
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("");
              localStringBuilder.append(((ApplicationInfo)Sacnning_Junk.this.k.get(Sacnning_Junk.this.l)).sourceDir);
              ((TextView)localObject).setText(localStringBuilder.toString());
              localObject = Sacnning_Junk.this;
              ((Sacnning_Junk)localObject).l += 1;
              return;
            }
            Sacnning_Junk.this.m.cancel();
            Sacnning_Junk.this.m.purge();
          }
        });
      }
    }, 80L, 80L);
    this.o = ((RecyclerView)findViewById(2131296494));
    this.o.setItemAnimator(new b.a.a.a.b());
    this.o.a(new a(this));
    this.n = new app.magic.cleaner.boost.BCJ.a(this.p);
    localObject = new LinearLayoutManager(getApplicationContext(), 1, false);
    this.o.setLayoutManager((RecyclerView.i)localObject);
    this.o.setItemAnimator(new c(new OvershootInterpolator(1.0F)));
    this.o.computeHorizontalScrollExtent();
    this.o.setAdapter(this.n);
    this.n.c();
    this.o.a(new a(this));
    new Handler().postDelayed(new Runnable()
    {
      public void run() {}
    }, 1000L);
    new Handler().postDelayed(new Runnable()
    {
      public void run() {}
    }, 2000L);
    new Handler().postDelayed(new Runnable()
    {
      public void run() {}
    }, 3000L);
    new Handler().postDelayed(new Runnable()
    {
      public void run() {}
    }, 4000L);
    new Handler().postDelayed(new Runnable()
    {
      public void run() {}
    }, 5000L);
    new Handler().postDelayed(new Runnable()
    {
      public void run() {}
    }, 6000L);
    new Handler().postDelayed(new Runnable()
    {
      public void run() {}
    }, 7000L);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        final RippleBackground localRippleBackground = (RippleBackground)Sacnning_Junk.this.findViewById(2131296368);
        Object localObject = (ImageView)Sacnning_Junk.this.findViewById(2131296342);
        localRippleBackground.a();
        Sacnning_Junk.this.g.setImageResource(2131230961);
        Sacnning_Junk.this.h.setImageResource(2131230885);
        localObject = (ProgressBar)Sacnning_Junk.this.findViewById(2131296549);
        ((ProgressBar)localObject).setIndeterminateDrawable(new m());
        ((ProgressBar)localObject).setVisibility(8);
        Sacnning_Junk.this.r.setPadding(20, 0, 0, 0);
        if (Build.VERSION.SDK_INT < 23)
        {
          Sacnning_Junk.this.r.setTextAppearance(Sacnning_Junk.this.getApplicationContext(), 16973892);
          localObject = Sacnning_Junk.this.r;
        }
        for (StringBuilder localStringBuilder = new StringBuilder();; localStringBuilder = new StringBuilder())
        {
          localStringBuilder.append(paramBundle.getString("junk"));
          localStringBuilder.append(Sacnning_Junk.this.getString(2131689473));
          ((TextView)localObject).setText(localStringBuilder.toString());
          break;
          Sacnning_Junk.this.r.setTextAppearance(16973892);
          localObject = Sacnning_Junk.this.r;
        }
        localObject = (ObjectAnimator)AnimatorInflater.loadAnimator(Sacnning_Junk.this.getApplicationContext(), 2130837507);
        ((ObjectAnimator)localObject).setTarget(Sacnning_Junk.this.g);
        ((ObjectAnimator)localObject).setDuration(3000L);
        ((ObjectAnimator)localObject).start();
        ((ObjectAnimator)localObject).addListener(new Animator.AnimatorListener()
        {
          public void onAnimationCancel(Animator paramAnonymous2Animator) {}
          
          public void onAnimationEnd(Animator paramAnonymous2Animator)
          {
            localRippleBackground.b();
            if (b.b)
            {
              Sacnning_Junk.a(Sacnning_Junk.this).b();
              return;
            }
            new Handler().postDelayed(new Runnable()
            {
              public void run()
              {
                Sacnning_Junk.this.finish();
              }
            }, 1000L);
          }
          
          public void onAnimationRepeat(Animator paramAnonymous2Animator) {}
          
          public void onAnimationStart(Animator paramAnonymous2Animator)
          {
            paramAnonymous2Animator = Sacnning_Junk.this.r;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append(Sacnning_Junk.this.getString(2131689537));
            localStringBuilder.append(Sacnning_Junk.2.this.a.getString("junk"));
            localStringBuilder.append(Sacnning_Junk.this.getString(2131689472));
            paramAnonymous2Animator.setText(localStringBuilder.toString());
            Sacnning_Junk.this.r.setTextColor(Color.parseColor("#FFFFFF"));
          }
        });
        Sacnning_Junk.this.j.setText("");
      }
    }, 8000L);
  }
  
  public class a
    extends RecyclerView.h
  {
    private Drawable b;
    
    public a(Context paramContext)
    {
      if (Build.VERSION.SDK_INT >= 21) {}
      for (this$1 = paramContext.getResources().getDrawable(2131230899, paramContext.getTheme());; this$1 = paramContext.getResources().getDrawable(2131230899))
      {
        this.b = Sacnning_Junk.this;
        return;
      }
    }
    
    public void a(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.u paramU)
    {
      int j = paramRecyclerView.getPaddingLeft();
      int k = paramRecyclerView.getWidth();
      int m = paramRecyclerView.getPaddingRight();
      int n = paramRecyclerView.getChildCount();
      int i = 0;
      while (i < n)
      {
        paramU = paramRecyclerView.getChildAt(i);
        RecyclerView.j localJ = (RecyclerView.j)paramU.getLayoutParams();
        int i1 = paramU.getBottom() + localJ.bottomMargin;
        int i2 = this.b.getIntrinsicHeight();
        this.b.setBounds(j, i1, k - m, i2 + i1);
        this.b.draw(paramCanvas);
        i += 1;
      }
    }
  }
}
