package com.dodo.clean.master.battery.saver.cpu.cooled;

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
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.dodo.clean.master.battery.saver.cpu.cooled.Classes.Apps;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.InterstitialAd;
import com.skyfishjy.library.RippleBackground;
import com.wang.avi.AVLoadingIndicatorView;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class Sacnning_Junk
  extends Activity
{
  Timer T2;
  Timer T3;
  public List<Apps> apps;
  AVLoadingIndicatorView avi1;
  AVLoadingIndicatorView avi2;
  AVLoadingIndicatorView avi3;
  AVLoadingIndicatorView avi4;
  AVLoadingIndicatorView avi5;
  AVLoadingIndicatorView avi6;
  ImageView back;
  int check = 0;
  TextView files;
  ImageView front;
  Junk_Apps_Adapter mAdapter;
  ImageView mImgCheck;
  InterstitialAd mInterstitialAd;
  Handler myHandler;
  Handler myHandler2;
  Runnable myRunnable;
  Runnable myRunnable2;
  List<ApplicationInfo> packages;
  PackageManager pm;
  int prog = 0;
  RecyclerView recyclerView;
  TextView scanning;
  
  public Sacnning_Junk() {}
  
  public void add(String paramString, int paramInt)
  {
    double d1 = Math.random();
    double d2 = this.packages.size() - 1 + 0 + 1;
    Double.isNaN(d2);
    int i = (int)(d1 * d2) + 0;
    paramString = new Apps();
    String str1 = ((ApplicationInfo)this.packages.get(i)).packageName;
    try
    {
      String str2 = (String)this.pm.getApplicationLabel(this.pm.getApplicationInfo(str1, 128));
      this.pm.getApplicationInfo(str1, 128);
      paramString.setImage(getPackageManager().getApplicationIcon(((ApplicationInfo)this.packages.get(i)).packageName));
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    paramString.setSize(((ApplicationInfo)this.packages.get(i)).dataDir);
    this.apps.add(paramString);
    this.mAdapter.notifyItemInserted(paramInt);
  }
  
  public void onBackPressed() {}
  
  protected void onCreate(final Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getIntent().getExtras();
    setContentView(2131361868);
    this.avi1 = ((AVLoadingIndicatorView)findViewById(2131230954));
    this.avi2 = ((AVLoadingIndicatorView)findViewById(2131230955));
    this.avi3 = ((AVLoadingIndicatorView)findViewById(2131230956));
    this.avi4 = ((AVLoadingIndicatorView)findViewById(2131230957));
    this.avi5 = ((AVLoadingIndicatorView)findViewById(2131230958));
    this.avi6 = ((AVLoadingIndicatorView)findViewById(2131230959));
    this.files = ((TextView)findViewById(2131230855));
    this.back = ((ImageView)findViewById(2131230792));
    this.scanning = ((TextView)findViewById(2131230962));
    this.apps = new ArrayList();
    this.mInterstitialAd = new InterstitialAd(getApplicationContext());
    this.mInterstitialAd.setAdUnitId(getResources().getString(2131558537));
    Object localObject = new AdRequest.Builder().build();
    this.mInterstitialAd.setAdListener(new AdListener()
    {
      public void onAdLoaded() {}
    });
    this.mInterstitialAd.loadAd((AdRequest)localObject);
    localObject = new RotateAnimation(0.0F, 360.0F, 1, 0.5F, 1, 0.5F);
    ((RotateAnimation)localObject).setDuration(1500L);
    ((RotateAnimation)localObject).setRepeatCount(4);
    ((RotateAnimation)localObject).setInterpolator(new LinearInterpolator());
    ((RotateAnimation)localObject).setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        Sacnning_Junk.this.T2.cancel();
        Sacnning_Junk.this.T2.purge();
        Sacnning_Junk.this.avi1.hide();
        Sacnning_Junk.this.avi2.hide();
        Sacnning_Junk.this.avi3.hide();
        Sacnning_Junk.this.avi4.hide();
        Sacnning_Junk.this.avi5.hide();
        Sacnning_Junk.this.avi6.hide();
        Sacnning_Junk.this.files.setText("");
      }
      
      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
        paramAnonymousAnimation = Sacnning_Junk.this;
        paramAnonymousAnimation.check += 1;
        paramAnonymousAnimation = Sacnning_Junk.this;
        paramAnonymousAnimation.startAnim(paramAnonymousAnimation.check);
      }
      
      public void onAnimationStart(Animation paramAnonymousAnimation) {}
    });
    this.front = ((ImageView)findViewById(2131230864));
    this.front.startAnimation((Animation)localObject);
    this.pm = getPackageManager();
    this.packages = this.pm.getInstalledApplications(0);
    localObject = (ActivityManager)getSystemService("activity");
    this.T2 = new Timer();
    this.T2.scheduleAtFixedRate(new TimerTask()
    {
      public void run()
      {
        Sacnning_Junk.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            if (Sacnning_Junk.this.prog < Sacnning_Junk.this.packages.size())
            {
              Object localObject = Sacnning_Junk.this.files;
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("");
              localStringBuilder.append(((ApplicationInfo)Sacnning_Junk.this.packages.get(Sacnning_Junk.this.prog)).sourceDir);
              ((TextView)localObject).setText(localStringBuilder.toString());
              localObject = Sacnning_Junk.this;
              ((Sacnning_Junk)localObject).prog += 1;
              return;
            }
            Sacnning_Junk.this.T2.cancel();
            Sacnning_Junk.this.T2.purge();
          }
        });
      }
    }, 80L, 80L);
    this.recyclerView = ((RecyclerView)findViewById(2131230944));
    this.recyclerView.setItemAnimator(new SlideInLeftAnimator());
    this.recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
    this.mAdapter = new Junk_Apps_Adapter(this.apps);
    localObject = new LinearLayoutManager(getApplicationContext(), 1, false);
    this.recyclerView.setLayoutManager((RecyclerView.LayoutManager)localObject);
    this.recyclerView.setItemAnimator(new SlideInUpAnimator(new OvershootInterpolator(1.0F)));
    this.recyclerView.computeHorizontalScrollExtent();
    this.recyclerView.setAdapter(this.mAdapter);
    this.mAdapter.notifyDataSetChanged();
    this.recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        Sacnning_Junk localSacnning_Junk = Sacnning_Junk.this;
        localSacnning_Junk.add(localSacnning_Junk.getResources().getString(2131558447), 0);
      }
    }, 1000L);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        Sacnning_Junk localSacnning_Junk = Sacnning_Junk.this;
        localSacnning_Junk.add(localSacnning_Junk.getResources().getString(2131558424), 1);
      }
    }, 2000L);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        Sacnning_Junk localSacnning_Junk = Sacnning_Junk.this;
        localSacnning_Junk.add(localSacnning_Junk.getResources().getString(2131558415), 2);
      }
    }, 3000L);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        Sacnning_Junk localSacnning_Junk = Sacnning_Junk.this;
        localSacnning_Junk.add(localSacnning_Junk.getResources().getString(2131558416), 3);
      }
    }, 4000L);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        Sacnning_Junk.this.remove(0);
      }
    }, 5000L);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        Sacnning_Junk.this.remove(0);
      }
    }, 6000L);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        Sacnning_Junk.this.remove(0);
      }
    }, 7000L);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        Sacnning_Junk.this.remove(0);
        final RippleBackground localRippleBackground = (RippleBackground)Sacnning_Junk.this.findViewById(2131230821);
        Object localObject = (ImageView)Sacnning_Junk.this.findViewById(2131230805);
        localRippleBackground.startRippleAnimation();
        Sacnning_Junk.this.front.setImageResource(2131165377);
        Sacnning_Junk.this.back.setImageResource(2131165333);
        localObject = (ProgressBar)Sacnning_Junk.this.findViewById(2131230995);
        ((ProgressBar)localObject).setIndeterminateDrawable(new ThreeBounce());
        ((ProgressBar)localObject).setVisibility(8);
        Sacnning_Junk.this.scanning.setPadding(20, 0, 0, 0);
        StringBuilder localStringBuilder;
        if (Build.VERSION.SDK_INT < 23)
        {
          Sacnning_Junk.this.scanning.setTextAppearance(Sacnning_Junk.this.getApplicationContext(), 16973892);
          Sacnning_Junk.this.scanning.setTextColor(Sacnning_Junk.this.getResources().getColor(2131034160));
          localObject = Sacnning_Junk.this.scanning;
          localStringBuilder = new StringBuilder();
          localStringBuilder.append(paramBundle.getString("junk"));
          localStringBuilder.append(" MB ");
          localStringBuilder.append(Sacnning_Junk.this.getResources().getString(2131558539));
          ((TextView)localObject).setText(localStringBuilder.toString());
        }
        else
        {
          Sacnning_Junk.this.scanning.setTextAppearance(16973892);
          Sacnning_Junk.this.scanning.setTextColor(Sacnning_Junk.this.getResources().getColor(2131034160));
          localObject = Sacnning_Junk.this.scanning;
          localStringBuilder = new StringBuilder();
          localStringBuilder.append(paramBundle.getString("junk"));
          localStringBuilder.append(" MB ");
          localStringBuilder.append(Sacnning_Junk.this.getResources().getString(2131558539));
          ((TextView)localObject).setText(localStringBuilder.toString());
        }
        localObject = (ObjectAnimator)AnimatorInflater.loadAnimator(Sacnning_Junk.this.getApplicationContext(), 2130837505);
        ((ObjectAnimator)localObject).setTarget(Sacnning_Junk.this.front);
        ((ObjectAnimator)localObject).setDuration(3000L);
        ((ObjectAnimator)localObject).start();
        ((ObjectAnimator)localObject).addListener(new Animator.AnimatorListener()
        {
          public void onAnimationCancel(Animator paramAnonymous2Animator) {}
          
          public void onAnimationEnd(Animator paramAnonymous2Animator)
          {
            Sacnning_Junk.this.mInterstitialAd.show();
            localRippleBackground.stopRippleAnimation();
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
            Sacnning_Junk.this.scanning.setTextColor(Sacnning_Junk.this.getResources().getColor(2131034160));
            paramAnonymous2Animator = Sacnning_Junk.this.scanning;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append(Sacnning_Junk.this.getResources().getString(2131558414));
            localStringBuilder.append(Sacnning_Junk.11.this.val$junk.getString("junk"));
            localStringBuilder.append(" MB");
            paramAnonymous2Animator.setText(localStringBuilder.toString());
            Sacnning_Junk.this.scanning.setTextColor(Color.parseColor("#FFFFFF"));
          }
        });
        Sacnning_Junk.this.files.setText("");
      }
    }, 8000L);
  }
  
  public void remove(int paramInt)
  {
    this.mAdapter.notifyItemRemoved(paramInt);
    this.apps.remove(paramInt);
  }
  
  void startAnim(int paramInt)
  {
    if (paramInt == 1)
    {
      this.avi1.show();
      this.avi3.show();
      this.avi5.show();
      this.avi2.hide();
      this.avi4.hide();
      this.avi6.hide();
      return;
    }
    if (paramInt == 2)
    {
      this.avi2.show();
      this.avi4.show();
      this.avi6.show();
      this.avi1.hide();
      this.avi3.hide();
      this.avi5.hide();
      return;
    }
    if (paramInt == 3)
    {
      this.avi2.show();
      this.avi4.show();
      this.avi6.show();
      this.avi1.show();
      this.avi3.show();
      this.avi5.show();
      return;
    }
    if (paramInt == 4)
    {
      this.avi2.show();
      this.avi3.show();
      this.avi5.show();
      this.avi1.show();
      this.avi2.show();
      this.avi6.show();
    }
  }
  
  void stopAnim()
  {
    this.avi1.hide();
    this.avi3.hide();
    this.avi5.hide();
    this.avi2.show();
    this.avi4.show();
    this.avi6.show();
  }
  
  public class SimpleDividerItemDecoration
    extends RecyclerView.ItemDecoration
  {
    private Drawable mDivider;
    
    public SimpleDividerItemDecoration(Context paramContext)
    {
      if (Build.VERSION.SDK_INT >= 21)
      {
        this.mDivider = paramContext.getResources().getDrawable(2131165339, paramContext.getTheme());
        return;
      }
      this.mDivider = paramContext.getResources().getDrawable(2131165339);
    }
    
    public void onDrawOver(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.State paramState)
    {
      int j = paramRecyclerView.getPaddingLeft();
      int k = paramRecyclerView.getWidth();
      int m = paramRecyclerView.getPaddingRight();
      int n = paramRecyclerView.getChildCount();
      int i = 0;
      while (i < n)
      {
        paramState = paramRecyclerView.getChildAt(i);
        RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)paramState.getLayoutParams();
        int i1 = paramState.getBottom() + localLayoutParams.bottomMargin;
        int i2 = this.mDivider.getIntrinsicHeight();
        this.mDivider.setBounds(j, i1, k - m, i2 + i1);
        this.mDivider.draw(paramCanvas);
        i += 1;
      }
    }
  }
}
