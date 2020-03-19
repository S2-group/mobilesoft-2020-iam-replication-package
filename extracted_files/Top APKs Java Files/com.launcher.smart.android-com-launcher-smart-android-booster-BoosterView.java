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
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region.Op;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.launcher.smart.android.CheckLongPressHelper;
import com.launcher.smart.android.DeviceProfile;
import com.launcher.smart.android.DynamicGrid;
import com.launcher.smart.android.HolographicOutlineHelper;
import com.launcher.smart.android.ItemInfo;
import com.launcher.smart.android.LauncherAppState;
import com.launcher.smart.android.LauncherModel;
import com.launcher.smart.android.ShortcutInfo;
import com.launcher.smart.android.Utilities;
import com.launcher.smart.android.booster.utils.AndroidAppProcess;
import com.launcher.smart.android.contest.interfaces.IOnTaskDone;
import com.launcher.smart.android.contest.utils.ApiRef;
import com.launcher.smart.android.contest.utils.ContestUtils;
import com.launcher.smart.android.contest.utils.UserPref;
import com.launcher.smart.android.contest.utils.UserSettings;
import com.launcher.smart.android.diy.ThemeHelper;
import com.launcher.smart.android.diy.ThemePack;
import com.launcher.smart.android.diy.Tool;
import com.launcher.smart.android.settings.AppSettings;
import com.launcher.smart.android.settings.ThemeSettings;
import com.launcher.smart.android.wizard.CleanWizard;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BoosterView
  extends LinearLayout
{
  static final float PADDING_H = 8.0F;
  static final float PADDING_V = 3.0F;
  static final int SHADOW_LARGE_COLOUR = -587202560;
  static final float SHADOW_LARGE_RADIUS = 4.0F;
  static final int SHADOW_SMALL_COLOUR = -872415232;
  static final float SHADOW_SMALL_RADIUS = 1.75F;
  static final float SHADOW_Y_OFFSET = 2.0F;
  static boolean clearingRam = false;
  private ImageView imFan;
  private IBoosterListener listener;
  private CheckLongPressHelper mLongPressHelper;
  private HolographicOutlineHelper mOutlineHelper;
  private int mPressedGlowColor;
  private Bitmap mPressedOrFocusedBackground;
  private int mPressedOutlineColor;
  private DonutProgress mProgress;
  private ValueAnimator mProgressAnimator;
  private ValueAnimator mProgressPreAnimator;
  private boolean mShadowsEnabled = false;
  private ValueAnimator mSpinAnimator1;
  private ValueAnimator mSpinAnimator2;
  private final Canvas mTempCanvas = new Canvas();
  private final Rect mTempRect = new Rect();
  private TextView tvBooster;
  
  public BoosterView(Context paramContext)
  {
    super(paramContext);
    init();
  }
  
  public BoosterView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }
  
  public BoosterView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }
  
  @SuppressLint({"NewApi"})
  public BoosterView(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    init();
  }
  
  private void boostDone()
  {
    if ((UserSettings.get(getContext()).canEarnBoostPhone()) && (Utilities.isNetworkAvailable(getContext()))) {
      ContestUtils.addCompleteTask(getContext(), "boost_phone", ((Integer)ApiRef._CONTEST_COIN_MAP.get("boost_phone")).intValue(), new IOnTaskDone()
      {
        public void onTaskDone()
        {
          UserSettings.get(BoosterView.this.getContext()).setCanEarnBoostPhone(false);
        }
      });
    }
  }
  
  private Bitmap createGlowingOutline(Canvas paramCanvas, int paramInt1, int paramInt2)
  {
    int i = this.mOutlineHelper.mMaxOuterBlurRadius;
    Bitmap localBitmap = Bitmap.createBitmap(getWidth() + i, getHeight() + i, Bitmap.Config.ARGB_8888);
    paramCanvas.setBitmap(localBitmap);
    drawWithPadding(paramCanvas, i);
    this.mOutlineHelper.applyExtraThickExpensiveOutlineWithBlur(localBitmap, paramCanvas, paramInt2, paramInt1);
    paramCanvas.setBitmap(null);
    return localBitmap;
  }
  
  private void drawWithPadding(Canvas paramCanvas, int paramInt)
  {
    Rect localRect = this.mTempRect;
    getDrawingRect(localRect);
    localRect.bottom = (this.tvBooster.getExtendedPaddingTop() - 3 + this.tvBooster.getLayout().getLineTop(0));
    paramCanvas.save();
    paramCanvas.scale(getScaleX(), getScaleY(), (getWidth() + paramInt) / 2, (getHeight() + paramInt) / 2);
    paramCanvas.translate(-getScrollX() + paramInt / 2, -getScrollY() + paramInt / 2);
    paramCanvas.clipRect(localRect, Region.Op.REPLACE);
    draw(paramCanvas);
    paramCanvas.restore();
  }
  
  public static int getRunningMemoryPercent(Context paramContext)
  {
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    ((ActivityManager)paramContext.getSystemService("activity")).getMemoryInfo(localMemoryInfo);
    return 100 - (int)((float)localMemoryInfo.availMem * 100.0F / (float)localMemoryInfo.totalMem);
  }
  
  private void init()
  {
    setOrientation(1);
    if (isInEditMode()) {
      return;
    }
    this.mLongPressHelper = new CheckLongPressHelper(this);
    this.mOutlineHelper = HolographicOutlineHelper.obtain(getContext());
  }
  
  private void onCleanComplete(long paramLong1, final long paramLong2, final int paramInt)
  {
    try
    {
      if (this.mProgressAnimator != null) {
        this.mProgressAnimator.cancel();
      }
      this.mProgressAnimator = ValueAnimator.ofInt(new int[] { 100, paramInt });
      this.mProgressAnimator.setDuration(800L);
      this.mProgressAnimator.setInterpolator(new DecelerateInterpolator());
      this.mProgressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
      {
        public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
        {
          int i = ((Integer)paramAnonymousValueAnimator.getAnimatedValue()).intValue();
          BoosterView.this.mProgress.setProgress(i);
          BoosterView.this.mProgress.setText(String.valueOf(100 - i) + "%");
        }
      });
      this.mProgressAnimator.addListener(new AnimatorListenerAdapter()
      {
        public void onAnimationEnd(Animator paramAnonymousAnimator)
        {
          super.onAnimationEnd(paramAnonymousAnimator);
          BoosterView.this.tvBooster.setVisibility(0);
          BoosterView.this.mProgress.setProgress(paramInt);
          BoosterView.this.mProgress.setText(String.valueOf(100 - paramInt) + "%");
          boolean bool = CleanWizard.onCleanCompleted(BoosterView.this.getContext(), paramLong2 - this.val$pre, true);
          BoosterView.this.boostDone();
          if (BoosterView.this.listener != null)
          {
            if (paramLong2 - this.val$pre > 10L) {}
            for (paramAnonymousAnimator = BoosterView.this.getResources().getString(2131231183, new Object[] { Long.valueOf(paramLong2 - this.val$pre) });; paramAnonymousAnimator = BoosterView.this.getResources().getString(2131231184))
            {
              BoosterView.this.listener.onBoostComplated(paramAnonymousAnimator, bool);
              return;
            }
          }
          if (paramLong2 - this.val$pre > 10L)
          {
            Toast.makeText(BoosterView.this.getContext(), BoosterView.this.getResources().getString(2131231108, new Object[] { Long.valueOf(paramLong2), Long.valueOf(paramLong2 - this.val$pre) }), 0).show();
            return;
          }
          Toast.makeText(BoosterView.this.getContext(), BoosterView.this.getResources().getString(2131231109, new Object[] { Long.valueOf(paramLong2) }), 0).show();
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
  
  public void applyFromShortcutInfo(ShortcutInfo paramShortcutInfo)
  {
    setTag(paramShortcutInfo);
    if ((paramShortcutInfo.container == -101L) || (!AppSettings.get().isDesktopShowLabel()))
    {
      this.tvBooster.setTextColor(0);
      this.tvBooster.getPaint().clearShadowLayer();
    }
    paramShortcutInfo = ThemeHelper.get().getTypeface();
    if (paramShortcutInfo != null) {
      this.tvBooster.setTypeface(paramShortcutInfo);
    }
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
        BoosterView.this.mProgress.setProgress(i);
        BoosterView.this.mProgress.setText(String.valueOf(100 - i) + "%");
      }
    });
    this.mProgressPreAnimator.addListener(new AnimatorListenerAdapter()
    {
      public void onAnimationEnd(Animator paramAnonymousAnimator)
      {
        super.onAnimationEnd(paramAnonymousAnimator);
        BoosterView.this.imFan.setVisibility(0);
        BoosterView.this.mProgress.setVisibility(4);
        BoosterView.this.mSpinAnimator1.start();
      }
    });
    this.mProgressPreAnimator.start();
    new AsyncTask()
    {
      protected Void doInBackground(Void... paramAnonymousVarArgs)
      {
        i = 0;
        j = 0;
        for (;;)
        {
          try
          {
            if (Build.VERSION.SDK_INT > 24) {
              continue;
            }
            paramAnonymousVarArgs = ProcessManager.getRunningAppProcesses().iterator();
            i = j;
            if (paramAnonymousVarArgs.hasNext()) {
              continue;
            }
            k = i;
            if (k == 0)
            {
              paramAnonymousVarArgs = localContext.getPackageManager().getInstalledApplications(0).iterator();
              boolean bool = paramAnonymousVarArgs.hasNext();
              if (bool) {
                continue;
              }
            }
            System.runFinalization();
          }
          catch (Exception paramAnonymousVarArgs)
          {
            int k;
            Object localObject;
            int m;
            continue;
            j += 1;
            i = k;
            continue;
          }
          Runtime.getRuntime().gc();
          System.gc();
          return null;
          localObject = (AndroidAppProcess)paramAnonymousVarArgs.next();
          if (!((AndroidAppProcess)localObject).getPackageName().equals(localContext.getPackageName()))
          {
            localActivityManager.killBackgroundProcesses(((AndroidAppProcess)localObject).getPackageName());
            i += 1;
            continue;
            paramAnonymousVarArgs = localActivityManager.getRunningAppProcesses();
            m = paramAnonymousVarArgs.size();
            j = 0;
            k = i;
            if (j < m)
            {
              k = i;
              if (((android.app.ActivityManager.RunningAppProcessInfo)paramAnonymousVarArgs.get(j)).pkgList[0].equals(localContext.getPackageName())) {
                continue;
              }
              localActivityManager.killBackgroundProcesses(((android.app.ActivityManager.RunningAppProcessInfo)paramAnonymousVarArgs.get(j)).pkgList[0]);
              k = i + 1;
              continue;
              localObject = (ApplicationInfo)paramAnonymousVarArgs.next();
              if (((((ApplicationInfo)localObject).flags & 0x80) == 0) && ((((ApplicationInfo)localObject).flags & 0x1) == 0) && (!((ApplicationInfo)localObject).packageName.equals(localContext.getPackageName()))) {
                localActivityManager.killBackgroundProcesses(((ApplicationInfo)localObject).packageName);
              }
            }
          }
        }
      }
      
      protected void onPostExecute(Void paramAnonymousVoid)
      {
        super.onPostExecute(paramAnonymousVoid);
        paramAnonymousVoid = new ActivityManager.MemoryInfo();
        localActivityManager.getMemoryInfo(paramAnonymousVoid);
        int i = (int)((float)paramAnonymousVoid.availMem * 100.0F / (float)paramAnonymousVoid.totalMem);
        long l = paramAnonymousVoid.availMem / 1048576L;
        BoosterView.this.onCleanComplete(l, l, i);
      }
      
      protected void onPreExecute()
      {
        BoosterView.clearingRam = true;
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
    this.mLongPressHelper.cancelLongPress();
  }
  
  void clearPressedOrFocusedBackground()
  {
    this.mPressedOrFocusedBackground = null;
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mProgress = ((DonutProgress)findViewById(2131493003));
    this.tvBooster = ((TextView)findViewById(2131493155));
    this.imFan = ((ImageView)findViewById(2131493154));
    this.mProgress.setStartingDegree(270);
    if (isInEditMode()) {
      return;
    }
    Object localObject = LauncherAppState.getInstance().getDynamicGrid().getDeviceProfile();
    int i = Math.min(((DeviceProfile)localObject).cellWidthPx, ((DeviceProfile)localObject).cellHeightPx);
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(i, i);
    localLayoutParams.gravity = 17;
    this.mProgress.setLayoutParams(localLayoutParams);
    this.imFan.setLayoutParams(localLayoutParams);
    this.tvBooster.setTextSize(0, ((DeviceProfile)localObject).iconTextSizePx);
    findViewById(2131493153).setBackgroundColor(0);
    this.mShadowsEnabled = ThemeSettings.get().isShadowWorkspaceDrawer();
    if (this.mShadowsEnabled) {
      this.tvBooster.setShadowLayer(4.0F, 0.0F, 2.0F, -587202560);
    }
    localObject = ThemeHelper.get().getTypeface();
    if (localObject != null) {
      this.tvBooster.setTypeface((Typeface)localObject);
    }
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
        int i = ((Integer)BoosterView.this.imFan.getTag()).intValue() + 1;
        BoosterView.this.imFan.setTag(Integer.valueOf(i));
        if ((i >= 5) && (!BoosterView.clearingRam))
        {
          BoosterView.this.mSpinAnimator1.cancel();
          BoosterView.this.mSpinAnimator2.start();
          BoosterView.this.imFan.setTag(Integer.valueOf(0));
        }
      }
      
      public void onAnimationStart(Animator paramAnonymousAnimator)
      {
        super.onAnimationStart(paramAnonymousAnimator);
        BoosterView.this.mProgress.setVisibility(4);
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
        BoosterView.this.imFan.setTag(Integer.valueOf(0));
        BoosterView.this.imFan.setVisibility(4);
        BoosterView.this.mProgress.setVisibility(0);
        BoosterView.this.mProgressAnimator.start();
      }
      
      public void onAnimationRepeat(Animator paramAnonymousAnimator)
      {
        super.onAnimationRepeat(paramAnonymousAnimator);
        int i = ((Integer)BoosterView.this.imFan.getTag()).intValue() + 1;
        BoosterView.this.imFan.setTag(Integer.valueOf(i));
        float f = (i + 3) / 10.0F;
        BoosterView.this.mSpinAnimator2.setInterpolator(new DecelerateInterpolator(1.0F + f));
      }
    });
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = super.onTouchEvent(paramMotionEvent);
    switch (paramMotionEvent.getAction())
    {
    case 2: 
    default: 
      return bool;
    case 0: 
      if (this.mPressedOrFocusedBackground == null) {
        this.mPressedOrFocusedBackground = createGlowingOutline(this.mTempCanvas, this.mPressedGlowColor, this.mPressedOutlineColor);
      }
      isPressed();
      this.mLongPressHelper.postCheckForLongPress();
      return bool;
    }
    if (!isPressed()) {
      this.mPressedOrFocusedBackground = null;
    }
    this.mLongPressHelper.cancelLongPress();
    return bool;
  }
  
  public void setListener(IBoosterListener paramIBoosterListener)
  {
    this.listener = paramIBoosterListener;
  }
  
  public void setTag(Object paramObject)
  {
    if ((paramObject != null) && (!isInEditMode())) {
      LauncherModel.checkItemInfo((ItemInfo)paramObject);
    }
    super.setTag(paramObject);
  }
  
  public void setTextDisable()
  {
    this.tvBooster.setVisibility(8);
  }
  
  public void update()
  {
    int i = getRunningMemoryPercent(getContext());
    this.mProgress.setProgress(100 - i);
    this.mProgress.setText(String.valueOf(i) + "%");
  }
  
  public void updateTheme()
  {
    Object localObject = ThemeHelper.get().getTheme();
    Bitmap localBitmap = ((ThemePack)localObject).loadBitmap("booster_fan");
    if (localBitmap != null) {
      this.imFan.setImageBitmap(localBitmap);
    }
    localObject = ((ThemePack)localObject).loadDrawable("booster_bg");
    if (localObject != null)
    {
      findViewById(2131493153).setBackground((Drawable)localObject);
      localObject = LauncherAppState.getInstance().getDynamicGrid().getDeviceProfile();
      int i = Math.min(((DeviceProfile)localObject).cellWidthPx, ((DeviceProfile)localObject).cellHeightPx);
      int j = Tool.dp2px(4, getContext());
      localObject = new FrameLayout.LayoutParams(i - j, i - j);
      ((FrameLayout.LayoutParams)localObject).gravity = 17;
      this.mProgress.setLayoutParams((ViewGroup.LayoutParams)localObject);
      localObject = new LinearLayout.LayoutParams(i, i);
      ((LinearLayout.LayoutParams)localObject).gravity = 1;
      findViewById(2131493153).setLayoutParams((ViewGroup.LayoutParams)localObject);
      localObject = new FrameLayout.LayoutParams(i, i);
      ((FrameLayout.LayoutParams)localObject).gravity = 17;
      this.imFan.setLayoutParams((ViewGroup.LayoutParams)localObject);
    }
    this.mProgress.setFinishedStrokeColor(ThemeSettings.get().getBoosterColorUnFill());
    this.mProgress.setUnfinishedStrokeColor(ThemeSettings.get().getBoosterColorFill());
    this.mProgress.setTextColor(ThemeSettings.get().getBoosterCenterTextColor());
    if ((getTag() != null) && ((((ShortcutInfo)getTag()).container == -101L) || (!AppSettings.get().isDesktopShowLabel())))
    {
      this.tvBooster.setTextColor(0);
      this.tvBooster.getPaint().clearShadowLayer();
      return;
    }
    this.tvBooster.setTextColor(ThemeSettings.get().getWorkspaceAppTextColor());
  }
  
  public static abstract interface IBoosterListener
  {
    public abstract void onBoostComplated(String paramString, boolean paramBoolean);
  }
}
