package com.launcher.smart.android.booster;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import com.launcher.smart.android.booster.utils.AndroidAppProcess;
import com.launcher.smart.android.diy.Tool;
import com.launcher.smart.android.wizard.CleanWizard;
import java.util.Iterator;
import java.util.List;

public class BoosterViewLock
  extends FrameLayout
{
  static boolean clearingRam = false;
  private ImageView imFan;
  private IBoosterListener listener;
  private DonutProgress mProgress;
  private ValueAnimator mProgressAnimator;
  private ValueAnimator mProgressPreAnimator;
  private ValueAnimator mSpinAnimator1;
  private ValueAnimator mSpinAnimator2;
  
  public BoosterViewLock(Context paramContext)
  {
    super(paramContext);
    init();
  }
  
  public BoosterViewLock(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }
  
  public BoosterViewLock(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }
  
  @SuppressLint({"NewApi"})
  public BoosterViewLock(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    init();
  }
  
  public static int getRunningMemoryPercent(Context paramContext)
  {
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    ((ActivityManager)paramContext.getSystemService("activity")).getMemoryInfo(localMemoryInfo);
    return 100 - (int)((float)localMemoryInfo.availMem * 100.0F / (float)localMemoryInfo.totalMem);
  }
  
  private void init()
  {
    if (isInEditMode()) {}
  }
  
  private void onCleanComplete(long paramLong)
  {
    try
    {
      ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
      ((ActivityManager)getContext().getSystemService("activity")).getMemoryInfo(localMemoryInfo);
      final int i = (int)((float)localMemoryInfo.availMem * 100.0F / (float)localMemoryInfo.totalMem);
      final long l = localMemoryInfo.availMem / 1048576L;
      if (this.mProgressAnimator != null) {
        this.mProgressAnimator.cancel();
      }
      this.mProgressAnimator = ValueAnimator.ofInt(new int[] { 100, i });
      this.mProgressAnimator.setDuration(800L);
      this.mProgressAnimator.setInterpolator(new DecelerateInterpolator());
      this.mProgressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
      {
        public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
        {
          int i = ((Integer)paramAnonymousValueAnimator.getAnimatedValue()).intValue();
          BoosterViewLock.this.mProgress.setProgress(i);
          BoosterViewLock.this.mProgress.setText(String.valueOf(100 - i) + "%");
        }
      });
      this.mProgressAnimator.addListener(new AnimatorListenerAdapter()
      {
        public void onAnimationEnd(Animator paramAnonymousAnimator)
        {
          super.onAnimationEnd(paramAnonymousAnimator);
          BoosterViewLock.this.mProgress.setProgress(i);
          BoosterViewLock.this.mProgress.setText(String.valueOf(100 - i) + "%");
          CleanWizard.onCleanCompleted(BoosterViewLock.this.getContext(), l - this.val$pre, false);
          if (BoosterViewLock.this.listener != null) {
            if (l - this.val$pre <= 10L) {
              break label151;
            }
          }
          label151:
          for (paramAnonymousAnimator = BoosterViewLock.this.getResources().getString(2131231183, new Object[] { Long.valueOf(l - this.val$pre) });; paramAnonymousAnimator = BoosterViewLock.this.getResources().getString(2131231184))
          {
            BoosterViewLock.this.listener.onBoostComplated(paramAnonymousAnimator);
            return;
          }
        }
        
        public void onAnimationStart(Animator paramAnonymousAnimator)
        {
          super.onAnimationStart(paramAnonymousAnimator);
        }
      });
      clearingRam = false;
      return;
    }
    finally {}
  }
  
  public boolean boost()
  {
    if (clearingRam) {
      return false;
    }
    if ((this.mSpinAnimator1.isRunning()) || (this.mSpinAnimator2.isRunning())) {
      return false;
    }
    if ((this.mProgressAnimator != null) && (this.mProgressAnimator.isRunning())) {
      return false;
    }
    final Context localContext = getContext();
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    final ActivityManager localActivityManager = (ActivityManager)localContext.getSystemService("activity");
    localActivityManager.getMemoryInfo(localMemoryInfo);
    final long l = localMemoryInfo.availMem / 1048576L;
    int i = (int)((float)localMemoryInfo.availMem * 100.0F / (float)localMemoryInfo.totalMem);
    if (this.mProgressPreAnimator != null) {
      this.mProgressPreAnimator.cancel();
    }
    this.mProgressPreAnimator = ValueAnimator.ofInt(new int[] { i, 100 });
    this.mProgressPreAnimator.setDuration(800L);
    this.mProgressPreAnimator.setInterpolator(new DecelerateInterpolator());
    this.mProgressPreAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
    {
      public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
      {
        int i = ((Integer)paramAnonymousValueAnimator.getAnimatedValue()).intValue();
        BoosterViewLock.this.mProgress.setProgress(i);
        BoosterViewLock.this.mProgress.setText(String.valueOf(100 - i) + "%");
      }
    });
    this.mProgressPreAnimator.addListener(new AnimatorListenerAdapter()
    {
      public void onAnimationEnd(Animator paramAnonymousAnimator)
      {
        super.onAnimationEnd(paramAnonymousAnimator);
        BoosterViewLock.this.imFan.setVisibility(0);
        BoosterViewLock.this.mProgress.setVisibility(4);
        BoosterViewLock.this.mSpinAnimator1.start();
      }
    });
    this.mProgressPreAnimator.start();
    new AsyncTask()
    {
      protected Void doInBackground(Void... paramAnonymousVarArgs)
      {
        int i = 0;
        int j = 0;
        for (;;)
        {
          int k;
          try
          {
            if (Build.VERSION.SDK_INT <= 24)
            {
              paramAnonymousVarArgs = ProcessManager.getRunningAppProcesses().iterator();
              i = j;
              if (!paramAnonymousVarArgs.hasNext())
              {
                k = i;
                if (k == 0)
                {
                  paramAnonymousVarArgs = localContext.getPackageManager().getInstalledApplications(0).iterator();
                  if (paramAnonymousVarArgs.hasNext()) {}
                }
                else
                {
                  System.runFinalization();
                  Runtime.getRuntime().gc();
                  System.gc();
                  break label284;
                }
              }
              else
              {
                localObject = (AndroidAppProcess)paramAnonymousVarArgs.next();
                if (((AndroidAppProcess)localObject).getPackageName().equals(localContext.getPackageName())) {
                  continue;
                }
                localActivityManager.killBackgroundProcesses(((AndroidAppProcess)localObject).getPackageName());
                i += 1;
                continue;
              }
            }
            else
            {
              paramAnonymousVarArgs = localActivityManager.getRunningAppProcesses();
              int m = paramAnonymousVarArgs.size();
              j = 0;
              k = i;
              if (j >= m) {
                continue;
              }
              k = i;
              if (((android.app.ActivityManager.RunningAppProcessInfo)paramAnonymousVarArgs.get(j)).pkgList[0].equals(localContext.getPackageName())) {
                break label286;
              }
              localActivityManager.killBackgroundProcesses(((android.app.ActivityManager.RunningAppProcessInfo)paramAnonymousVarArgs.get(j)).pkgList[0]);
              k = i + 1;
              break label286;
            }
            Object localObject = (ApplicationInfo)paramAnonymousVarArgs.next();
            if (((((ApplicationInfo)localObject).flags & 0x80) != 0) || ((((ApplicationInfo)localObject).flags & 0x1) != 0) || (((ApplicationInfo)localObject).packageName.equals(localContext.getPackageName()))) {
              continue;
            }
            localActivityManager.killBackgroundProcesses(((ApplicationInfo)localObject).packageName);
            continue;
            return null;
          }
          catch (Exception paramAnonymousVarArgs) {}
          label284:
          label286:
          j += 1;
          i = k;
        }
      }
      
      protected void onPostExecute(Void paramAnonymousVoid)
      {
        super.onPostExecute(paramAnonymousVoid);
        BoosterViewLock.this.onCleanComplete(l);
      }
      
      protected void onPreExecute()
      {
        BoosterViewLock.clearingRam = true;
      }
    }.execute(new Void[0]);
    return true;
  }
  
  public boolean boost(IBoosterListener paramIBoosterListener)
  {
    this.listener = paramIBoosterListener;
    return boost();
  }
  
  public void cancelLongPress()
  {
    super.cancelLongPress();
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mProgress = ((DonutProgress)findViewById(2131493003));
    this.imFan = ((ImageView)findViewById(2131493154));
    this.mProgress.setStartingDegree(270);
    if (isInEditMode()) {
      return;
    }
    int i = Tool.dp2px(70, getContext());
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(i, i);
    localLayoutParams.gravity = 17;
    this.mProgress.setLayoutParams(localLayoutParams);
    this.imFan.setImageResource(2130837688);
    this.mProgress.setFinishedStrokeColor(-1);
    this.mProgress.setUnfinishedStrokeColor(-12303292);
    this.mProgress.setTextColor(-1);
    this.imFan.setColorFilter(-1, PorterDuff.Mode.SRC_ATOP);
    this.imFan.setTag(Integer.valueOf(0));
    this.imFan.setVisibility(4);
    this.mSpinAnimator1 = ObjectAnimator.ofFloat(this.imFan, "rotation", new float[] { 0.0F, 360.0F });
    this.mSpinAnimator1.setRepeatCount(-1);
    this.mSpinAnimator1.setDuration(120L);
    this.mSpinAnimator1.setInterpolator(new AccelerateDecelerateInterpolator());
    this.mSpinAnimator1.addListener(new AnimatorListenerAdapter()
    {
      public void onAnimationRepeat(Animator paramAnonymousAnimator)
      {
        super.onAnimationRepeat(paramAnonymousAnimator);
        int i = ((Integer)BoosterViewLock.this.imFan.getTag()).intValue() + 1;
        BoosterViewLock.this.imFan.setTag(Integer.valueOf(i));
        if ((i >= 5) && (!BoosterViewLock.clearingRam))
        {
          BoosterViewLock.this.mSpinAnimator1.cancel();
          BoosterViewLock.this.mSpinAnimator2.start();
          BoosterViewLock.this.imFan.setTag(Integer.valueOf(0));
        }
      }
      
      public void onAnimationStart(Animator paramAnonymousAnimator)
      {
        super.onAnimationStart(paramAnonymousAnimator);
        BoosterViewLock.this.mProgress.setVisibility(4);
      }
    });
    this.mSpinAnimator2 = ObjectAnimator.ofFloat(this.imFan, "rotation", new float[] { 0.0F, 360.0F });
    this.mSpinAnimator2.setRepeatCount(8);
    this.mSpinAnimator2.setDuration(150L);
    this.mSpinAnimator2.setInterpolator(new DecelerateInterpolator(1.0F));
    this.mSpinAnimator2.addListener(new AnimatorListenerAdapter()
    {
      public void onAnimationEnd(Animator paramAnonymousAnimator)
      {
        super.onAnimationEnd(paramAnonymousAnimator);
        BoosterViewLock.this.imFan.setTag(Integer.valueOf(0));
        BoosterViewLock.this.imFan.setVisibility(4);
        BoosterViewLock.this.mProgress.setVisibility(0);
        BoosterViewLock.this.mProgressAnimator.start();
      }
      
      public void onAnimationRepeat(Animator paramAnonymousAnimator)
      {
        super.onAnimationRepeat(paramAnonymousAnimator);
        int i = ((Integer)BoosterViewLock.this.imFan.getTag()).intValue() + 1;
        BoosterViewLock.this.imFan.setTag(Integer.valueOf(i));
        float f = (i + 3) / 10.0F;
        BoosterViewLock.this.mSpinAnimator2.setInterpolator(new DecelerateInterpolator(1.0F + f));
      }
    });
  }
  
  public void setListener(IBoosterListener paramIBoosterListener)
  {
    this.listener = paramIBoosterListener;
  }
  
  public void setTextDisable() {}
  
  public void update()
  {
    int i = getRunningMemoryPercent(getContext());
    this.mProgress.setProgress(100 - i);
    this.mProgress.setText(String.valueOf(i) + "%");
  }
  
  public static abstract interface IBoosterListener
  {
    public abstract void onBoostComplated(String paramString);
  }
}
