package com.narvii.account;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.AlertDialog.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.narvii.amino.MainActivity;
import com.narvii.app.FragmentWrapperActivity;
import com.narvii.app.NVActivity;
import com.narvii.app.NVApplication;
import com.narvii.config.ConfigService;
import com.narvii.master.CommunityDetailFragment;
import com.narvii.model.api.ApiResponse;
import com.narvii.model.api.UserResponse;
import com.narvii.services.AffiliationsHelper;
import com.narvii.services.EnterCommunityHelper;
import com.narvii.util.AndroidBug5497Workaround;
import com.narvii.util.Log;
import com.narvii.util.NativeHelper;
import com.narvii.util.PaletteUtils;
import com.narvii.util.SoftKeyboard;
import com.narvii.util.Utils;
import com.narvii.util.http.ApiRequest;
import com.narvii.util.http.ApiRequest.Builder;
import com.narvii.util.http.ApiResponseListener;
import com.narvii.util.http.ApiService;
import com.narvii.util.http.NameValuePair;
import com.narvii.util.image.NVImageLoader;
import com.narvii.util.statistics.StatisticsEventBuilder;
import com.narvii.util.statistics.StatisticsService;
import com.narvii.util.statusbar.StatusBarUtils;
import com.narvii.widget.BlurImageView;
import com.narvii.widget.Flipper;
import com.narvii.widget.Flipper.FlipperAdapter;
import com.narvii.widget.Flipper.OnFlipperScrollListener;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;

public class LoginActivity
  extends NVActivity
{
  static final int JOIN_REQUEST = 2;
  public static final int NOTIFY_ID = 4609;
  float[] accMax;
  float[] accMin;
  AccountService account;
  BlurImageView blurImageView;
  private float density;
  Animation fadeIn;
  Animation fadeOut;
  Flipper<Integer> flipper;
  private Flipper.FlipperAdapter<Integer> flipperAdapter = new Flipper.FlipperAdapter()
  {
    final int count = 4;
    final View[] views = new View[4];
    
    public Integer getNextItem(Integer paramAnonymousInteger)
    {
      if (paramAnonymousInteger.intValue() < 3) {
        return Integer.valueOf(paramAnonymousInteger.intValue() + 1);
      }
      return null;
    }
    
    public Integer getPreviousItem(Integer paramAnonymousInteger)
    {
      if (paramAnonymousInteger.intValue() > 0) {
        return Integer.valueOf(paramAnonymousInteger.intValue() - 1);
      }
      return null;
    }
    
    public View getView(Integer paramAnonymousInteger, View paramAnonymousView)
    {
      if (paramAnonymousInteger == null) {
        return null;
      }
      ImageView localImageView;
      int i;
      if (this.views[paramAnonymousInteger.intValue()] == null)
      {
        localImageView = (ImageView)LoginActivity.this.getLayoutInflater().inflate(2130903047, null, false);
        switch (paramAnonymousInteger.intValue())
        {
        default: 
          i = 2130837510;
        }
      }
      for (;;)
      {
        paramAnonymousView = null;
        try
        {
          Drawable localDrawable = LoginActivity.this.getResources().getDrawable(i);
          paramAnonymousView = localDrawable;
        }
        catch (OutOfMemoryError localOutOfMemoryError)
        {
          for (;;)
          {
            Log.w("OutOfMemory when load login pic");
          }
        }
        localImageView.setImageDrawable(paramAnonymousView);
        this.views[paramAnonymousInteger.intValue()] = localImageView;
        return this.views[paramAnonymousInteger.intValue()];
        i = 2130837507;
        continue;
        i = 2130837508;
        continue;
        i = 2130837509;
      }
    }
    
    public void onMoved(Integer paramAnonymousInteger1, Integer paramAnonymousInteger2) {}
    
    public void onMoving(Integer paramAnonymousInteger1, Integer paramAnonymousInteger2) {}
    
    public void onTap(Integer paramAnonymousInteger) {}
    
    public void recycleView(View paramAnonymousView) {}
  };
  private Flipper.OnFlipperScrollListener flipperScrollListener = new Flipper.OnFlipperScrollListener()
  {
    boolean b = false;
    
    public void onScroll(int paramAnonymousInt)
    {
      LoginActivity.this.updateGradient();
      if (paramAnonymousInt == 0) {
        if (this.b)
        {
          this.b = false;
          localView = LoginActivity.this.flipper.getCurrentView();
          if (localView != null) {
            localView.setVisibility(0);
          }
          localView = LoginActivity.this.flipper.getNextView();
          if (localView != null) {
            localView.setVisibility(0);
          }
          localView = LoginActivity.this.flipper.getPreviousView();
          if (localView != null) {
            localView.setVisibility(0);
          }
          LoginActivity.this.flipper.animate().alpha(1.0F).setDuration(300L).start();
        }
      }
      while (this.b) {
        return;
      }
      this.b = true;
      View localView = LoginActivity.this.flipper.getNextView();
      if (localView != null) {
        localView.setVisibility(4);
      }
      localView = LoginActivity.this.flipper.getPreviousView();
      if (localView != null) {
        localView.setVisibility(4);
      }
      LoginActivity.this.flipper.animate().alpha(0.0F).setDuration(150L).start();
    }
  };
  private int[] gradientColors1 = { -228545, -3274201, -13622354, -12414175 };
  private int[] gradientColors2 = { -734669, -700065, -3641897, -7351274 };
  GradientView gradientView;
  float[] gyoMax;
  float[] gyoMin;
  private int ic;
  boolean joiningCommunity;
  int lastErrorCode;
  String lastErrorMsg;
  float[] lightMax;
  float[] lightMin;
  private SensorManager mSensorManager;
  int maxLoginStep;
  int maxSignupStep;
  private int mc;
  private MessageDigest md;
  View overlayView;
  private final BroadcastReceiver receiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if ("com.narvii.action.KEYCHAIN_STATUS_CHANGED".equals(paramAnonymousIntent.getAction()))
      {
        if (LoginActivity.this.account.hasAccount()) {
          LoginActivity.this.finishWithResult(null, true, 0, null);
        }
      }
      else {
        return;
      }
      LoginActivity.this.updateViews();
    }
  };
  private final Runnable scheduleSignupWakeup = new Runnable()
  {
    public void run()
    {
      AlarmManager localAlarmManager = (AlarmManager)LoginActivity.this.getSystemService("alarm");
      PendingIntent localPendingIntent = PendingIntent.getService(LoginActivity.this, 0, new Intent(LoginActivity.this, FinishSignupNotificationService.class), 0);
      if (NVApplication.DEBUG) {}
      for (long l = 8000L;; l = 60000L)
      {
        localAlarmManager.set(1, System.currentTimeMillis() + l, localPendingIntent);
        return;
      }
    }
  };
  private SensorEventListener sel;
  boolean signupWakeup;
  boolean startingActivity;
  LoginBaseFragment submittingFragment;
  private int ut;
  
  public LoginActivity() {}
  
  private byte[] getIds(int paramInt)
  {
    switch (paramInt)
    {
    }
    for (;;)
    {
      return null;
      try
      {
        localObject1 = MessageDigest.getInstance("SHA-1");
        localObject2 = new byte['က'];
        ((MessageDigest)localObject1).update((Build.VERSION.SDK_INT + Build.VERSION.RELEASE + Build.VERSION.INCREMENTAL).getBytes());
        localObject3 = new String[3];
        localObject3[0] = "/proc/cpuinfo";
        localObject3[1] = "/proc/partitions";
        localObject3[2] = "/proc/version";
        int i = localObject3.length;
        paramInt = 0;
        while (paramInt < i)
        {
          FileInputStream localFileInputStream = new FileInputStream(localObject3[paramInt]);
          for (;;)
          {
            int j = localFileInputStream.read((byte[])localObject2);
            if (j == -1) {
              break;
            }
            ((MessageDigest)localObject1).update((byte[])localObject2, 0, j);
          }
          localFileInputStream.close();
          paramInt += 1;
        }
        localObject3 = new FileInputStream("/proc/meminfo");
        paramInt = ((FileInputStream)localObject3).read((byte[])localObject2);
        ((FileInputStream)localObject3).close();
        localObject2 = new String((byte[])localObject2, 0, paramInt);
        if (((String)localObject2).startsWith("MemTotal:"))
        {
          paramInt = ((String)localObject2).indexOf('\n');
          if (paramInt != -1) {
            ((MessageDigest)localObject1).update(((String)localObject2).substring(0, paramInt).getBytes());
          }
        }
        return ((MessageDigest)localObject1).digest();
      }
      catch (Exception localException) {}
      Object localObject2 = MessageDigest.getInstance("SHA-1");
      Object localObject1 = getPackageManager();
      Object localObject3 = ((PackageManager)localObject1).getInstalledApplications(128);
      if (((List)localObject3).size() < 16)
      {
        localObject3 = ((PackageManager)localObject1).queryIntentActivities(new Intent("android.intent.action.MAIN"), 131072).iterator();
        if (((Iterator)localObject3).hasNext())
        {
          localObject1 = (ResolveInfo)((Iterator)localObject3).next();
          if (((ResolveInfo)localObject1).activityInfo == null) {}
          for (localObject1 = null;; localObject1 = ((ResolveInfo)localObject1).activityInfo.packageName)
          {
            ((MessageDigest)localObject2).update(((String)localObject1).getBytes());
            break;
          }
        }
      }
      else
      {
        localObject1 = ((List)localObject3).iterator();
        while (((Iterator)localObject1).hasNext()) {
          ((MessageDigest)localObject2).update(((ApplicationInfo)((Iterator)localObject1).next()).packageName.getBytes());
        }
      }
      return ((MessageDigest)localObject2).digest();
      localObject1 = MessageDigest.getInstance("SHA-1");
      localObject2 = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] { "_data", "width", "height" }, null, null, null);
      paramInt = ((Cursor)localObject2).getCount();
      this.ic = paramInt;
      ((MessageDigest)localObject1).update(String.valueOf(paramInt).getBytes());
      if ((localObject2 != null) && (((Cursor)localObject2).moveToLast()))
      {
        paramInt = 0;
        do
        {
          ((MessageDigest)localObject1).update((((Cursor)localObject2).getString(0) + ((Cursor)localObject2).getInt(1) + ((Cursor)localObject2).getInt(2)).getBytes());
          paramInt += 1;
        } while ((paramInt < 16) && (((Cursor)localObject2).moveToPrevious()));
      }
      ((Cursor)localObject2).close();
      return ((MessageDigest)localObject1).digest();
      localObject1 = this.md.digest();
      return localObject1;
    }
  }
  
  private int[] getVals()
  {
    int k = 0;
    int j = 0;
    float f2 = 0.0F;
    int i = k;
    float f1 = f2;
    if (this.lightMin != null)
    {
      i = k;
      f1 = f2;
      if (this.lightMax != null)
      {
        f1 = this.lightMax[0] - this.lightMin[0];
        if (f1 != 0.0F) {
          break label153;
        }
        if (this.lightMax[0] != 0.0F) {
          break label146;
        }
        f1 = -20.0F;
        i = 0x0 | 0x2;
      }
    }
    for (;;)
    {
      f1 = 0.0F + f1;
      j = i;
      f2 = f1;
      if (this.accMin == null) {
        break label208;
      }
      j = i;
      f2 = f1;
      if (this.accMax == null) {
        break label208;
      }
      f2 = 0.0F;
      j = 0;
      while (j < this.accMax.length)
      {
        f2 += this.accMax[j] - this.accMin[j];
        j += 1;
      }
      label146:
      f1 = -5.0F;
      break;
      label153:
      f2 = Math.min(15.0F, 1.0F * f1);
      f1 = f2;
      i = j;
      if (f2 < 15.0F)
      {
        i = 0x0 | 0x4;
        f1 = f2;
      }
    }
    if (f2 == 0.0F)
    {
      f2 = -30.0F;
      j = i | 0x8;
    }
    label208:
    float f3;
    for (;;)
    {
      f2 = f1 + f2;
      i = j;
      f1 = f2;
      if (this.gyoMin == null) {
        break label335;
      }
      i = j;
      f1 = f2;
      if (this.gyoMax == null) {
        break label335;
      }
      f1 = 0.0F;
      i = 0;
      while (i < this.gyoMax.length)
      {
        f1 += this.gyoMax[i] - this.gyoMin[i];
        i += 1;
      }
      f3 = Math.min(30.0F, 3.0F * f2);
      f2 = f3;
      j = i;
      if (f3 < 30.0F)
      {
        j = i | 0x10;
        f2 = f3;
      }
    }
    label335:
    Object localObject;
    label358:
    label375:
    label392:
    label409:
    label426:
    label443:
    label460:
    label477:
    label498:
    int m;
    if (f1 == 0.0F)
    {
      f1 = -10.0F;
      i = j | 0x20;
      f1 = f2 + f1;
      localObject = getPackageManager();
      if (!((PackageManager)localObject).hasSystemFeature("android.hardware.bluetooth")) {
        break label735;
      }
      f1 += 10.0F;
      if (!((PackageManager)localObject).hasSystemFeature("android.hardware.bluetooth_le")) {
        break label746;
      }
      f1 += 5.0F;
      if (!((PackageManager)localObject).hasSystemFeature("android.hardware.camera.autofocus")) {
        break label757;
      }
      f1 += 10.0F;
      if (!((PackageManager)localObject).hasSystemFeature("android.hardware.camera.flash")) {
        break label768;
      }
      f1 += 10.0F;
      if (!((PackageManager)localObject).hasSystemFeature("android.hardware.sensor.barometer")) {
        break label779;
      }
      f1 += 5.0F;
      if (!((PackageManager)localObject).hasSystemFeature("android.hardware.sensor.compass")) {
        break label790;
      }
      f1 += 10.0F;
      if (!((PackageManager)localObject).hasSystemFeature("android.hardware.sensor.gyroscope")) {
        break label801;
      }
      f1 += 10.0F;
      if (!((PackageManager)localObject).hasSystemFeature("android.hardware.sensor.light")) {
        break label812;
      }
      f1 += 10.0F;
      if (!((PackageManager)localObject).hasSystemFeature("android.hardware.sensor.proximity")) {
        break label822;
      }
      f2 = f1 + 5.0F;
      j = i;
      k = -1;
      m = -1;
      i = k;
    }
    try
    {
      localObject = registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      i = k;
      k = ((Intent)localObject).getIntExtra("level", -1);
      i = k;
      int n = ((Intent)localObject).getIntExtra("scale", -1);
      m = n;
      i = k;
    }
    catch (Exception localException)
    {
      label587:
      label609:
      float f4;
      label630:
      float f5;
      label735:
      label746:
      label757:
      label768:
      label779:
      label790:
      label801:
      label812:
      label822:
      label886:
      label938:
      label984:
      for (;;) {}
    }
    if ((i == 50) && (m == 100))
    {
      f1 = 0.0F;
      j |= 0x100000;
      if (this.ut >= 30000) {
        break label886;
      }
      f3 = -20.0F;
      i = j | 0x400000;
      if (this.ic >= 2) {
        break label938;
      }
      f4 = -10.0F;
      j = i | 0x1000000;
      f5 = Math.min(20, (this.mc - 6) * 5);
      if (f5 >= 0.0F) {
        break label984;
      }
      i = j | 0x4000000;
    }
    for (;;)
    {
      return new int[] { 100 - (int)(100.0F * (f2 + f1 + f3 + f4 + f5) / 195.0F), i };
      f3 = Math.min(15.0F, 10.0F * f1);
      f1 = f3;
      i = j;
      if (f3 >= 15.0F) {
        break;
      }
      i = j | 0x40;
      f1 = f3;
      break;
      i |= 0x400;
      break label358;
      i |= 0x800;
      break label375;
      i |= 0x1000;
      break label392;
      i |= 0x2000;
      break label409;
      i |= 0x4000;
      break label426;
      i |= 0x8000;
      break label443;
      i |= 0x10000;
      break label460;
      i |= 0x20000;
      break label477;
      j = i | 0x40000;
      f2 = f1;
      break label498;
      if ((i < 0) && (m < 0))
      {
        f1 = 0.0F;
        j |= 0x200000;
        break label587;
      }
      if ((i == 100) && (m == 100))
      {
        f1 = 5.0F;
        break label587;
      }
      f1 = 10.0F;
      break label587;
      f4 = Math.min(20.0F, 1.0F * (this.ut - 30) / 90.0F);
      f3 = f4;
      i = j;
      if (f4 >= 20.0F) {
        break label609;
      }
      i = j | 0x800000;
      f3 = f4;
      break label609;
      f5 = Math.min(10, this.ic - 2);
      f4 = f5;
      j = i;
      if (f5 >= 10.0F) {
        break label630;
      }
      j = i | 0x2000000;
      f4 = f5;
      break label630;
      i = j;
      if (f5 < 20.0F) {
        i = j | 0x8000000;
      }
    }
  }
  
  private void setVisibilityAnim(View paramView, boolean paramBoolean)
  {
    if ((paramBoolean) && (paramView.getVisibility() != 0))
    {
      paramView.setVisibility(0);
      paramView.startAnimation(this.fadeIn);
    }
    while ((paramBoolean) || (paramView.getVisibility() != 0)) {
      return;
    }
    paramView.setVisibility(8);
    paramView.startAnimation(this.fadeOut);
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    }
    for (;;)
    {
      return super.dispatchTouchEvent(paramMotionEvent);
      if (this.md != null)
      {
        this.md.update((byte)paramMotionEvent.getAction());
        this.md.update(Integer.toString((int)(paramMotionEvent.getRawX() / this.density)).getBytes());
        this.md.update(Integer.toString((int)(paramMotionEvent.getRawY() / this.density)).getBytes());
      }
      this.mc += 1;
    }
  }
  
  void fakeFade(final Runnable paramRunnable)
  {
    final View localView = findViewById(2131427446);
    localView.animate().setDuration(200L).alpha(0.0F).setListener(new Animator.AnimatorListener()
    {
      public void onAnimationCancel(Animator paramAnonymousAnimator)
      {
        onAnimationEnd(paramAnonymousAnimator);
      }
      
      public void onAnimationEnd(Animator paramAnonymousAnimator)
      {
        if (LoginActivity.this.isDestoryed()) {
          return;
        }
        paramRunnable.run();
        localView.animate().setDuration(300L).alpha(1.0F).setListener(null);
      }
      
      public void onAnimationRepeat(Animator paramAnonymousAnimator) {}
      
      public void onAnimationStart(Animator paramAnonymousAnimator) {}
    });
  }
  
  public void finish()
  {
    if ((NVApplication.CLIENT_TYPE == 101) && (this.account.hasAccount()))
    {
      MainActivity.setPendingCommand(1048608);
      EnterCommunityHelper localEnterCommunityHelper = (EnterCommunityHelper)NVApplication.instance().getService("_enterCommunityHelper");
      if (localEnterCommunityHelper != null) {
        localEnterCommunityHelper.logEnterCommunity(NVApplication.instance(), 0L);
      }
    }
    super.finish();
  }
  
  void finishWithResult(LoginBaseFragment paramLoginBaseFragment, final boolean paramBoolean, int paramInt, final String paramString)
  {
    if (isDestoryed()) {}
    label102:
    label161:
    label209:
    label215:
    do
    {
      do
      {
        for (;;)
        {
          return;
          if (!paramBoolean) {
            break;
          }
          if (paramInt == 1)
          {
            paramBoolean = true;
            if (NVApplication.CLIENT_TYPE != 101) {
              break label161;
            }
            paramString = (ConfigService)NVApplication.instance().getService("config");
            ApiRequest localApiRequest = ApiRequest.builder().post().communityId(paramString.getCommunityId()).path("/community/join").build();
            ((ApiService)NVApplication.instance().getService("api")).exec(localApiRequest, new ApiResponseListener(UserResponse.class)
            {
              private void stat()
              {
                StatisticsService localStatisticsService = (StatisticsService)LoginActivity.this.getService("statistics");
                int i = ((ConfigService)NVApplication.instance().getService("config")).getCommunityId();
                localStatisticsService.event(null).userProp("Communities Joined Total", 1).userProp("Communities Joined", new int[] { i });
                ((AffiliationsHelper)NVApplication.instance().getService("_affiliationsHelper")).check(NVApplication.instance());
              }
              
              public void onFail(ApiRequest paramAnonymousApiRequest, int paramAnonymousInt, List<NameValuePair> paramAnonymousList, String paramAnonymousString, ApiResponse paramAnonymousApiResponse, Throwable paramAnonymousThrowable)
              {
                LoginActivity.this.joiningCommunity = false;
                Toast.makeText(LoginActivity.this, paramAnonymousString, 1).show();
                stat();
                paramAnonymousApiRequest = FragmentWrapperActivity.intent(CommunityDetailFragment.class);
                paramAnonymousApiRequest.putExtra("id", paramString.getCommunityId());
                paramAnonymousApiRequest.putExtra("joinOnly", true);
                LoginActivity.this.startActivityForResult(paramAnonymousApiRequest, 2);
              }
              
              public void onFinish(ApiRequest paramAnonymousApiRequest, UserResponse paramAnonymousUserResponse)
                throws Exception
              {
                LoginActivity.this.joiningCommunity = false;
                ((StatisticsService)LoginActivity.this.getService("statistics")).event("Joins a Community").priority(8).param("Type", "join").param("Community ID", paramString.getCommunityId());
                stat();
                ((AccountService)LoginActivity.this.getService("account")).updateProfile(paramAnonymousUserResponse.user, paramAnonymousUserResponse.timestamp, true);
                paramAnonymousApiRequest = LoginActivity.this.getIntent();
                paramAnonymousApiRequest.putExtra("newAccount", paramBoolean);
                LoginActivity.this.setResult(-1, paramAnonymousApiRequest);
                LoginActivity.this.finish();
              }
            });
            this.joiningCommunity = true;
            if (paramLoginBaseFragment != null) {
              break label209;
            }
          }
          for (paramLoginBaseFragment = "Auto Login";; paramLoginBaseFragment = paramLoginBaseFragment.getName())
          {
            if (paramLoginBaseFragment == null) {
              break label215;
            }
            ((StatisticsService)getService("statistics")).event("Registration Succeed").priority(10).param("Type", paramLoginBaseFragment).source(getStringParam("Source"));
            return;
            paramBoolean = false;
            break;
            ((AffiliationsHelper)NVApplication.instance().getService("_affiliationsHelper")).check(NVApplication.instance());
            paramString = getIntent();
            paramString.putExtra("newAccount", paramBoolean);
            setResult(-1, paramString);
            finish();
            break label102;
          }
        }
      } while (this.submittingFragment != paramLoginBaseFragment);
      setSubmitting(null);
      if (paramString != null)
      {
        this.lastErrorCode = paramInt;
        this.lastErrorMsg = paramString;
      }
    } while (paramString == null);
    if (paramInt / 100 == 2)
    {
      paramLoginBaseFragment = new AlertDialog.Builder(this);
      paramLoginBaseFragment.setMessage(paramString);
      paramLoginBaseFragment.setNegativeButton(17039370, Utils.DIALOG_BUTTON_EMPTY_LISTENER);
      paramLoginBaseFragment.show();
      return;
    }
    Toast.makeText(getContext(), paramString, 0).show();
  }
  
  public boolean isGlobal()
  {
    return true;
  }
  
  public boolean isModel()
  {
    return true;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 2)
    {
      setResult(-1);
      finish();
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    if (this.joiningCommunity) {
      return;
    }
    if ((this.submittingFragment != null) && (this.submittingFragment.cancel()))
    {
      setSubmitting(null);
      return;
    }
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.account = ((AccountService)getService("account"));
    setContentView(2130903048);
    AndroidBug5497Workaround.assistActivity(this);
    this.gradientView = ((GradientView)findViewById(2131427445));
    this.fadeOut = AnimationUtils.loadAnimation(this, 17432577);
    this.fadeIn = AnimationUtils.loadAnimation(this, 17432576);
    this.blurImageView = ((BlurImageView)findViewById(2131427442));
    this.flipper = ((Flipper)findViewById(2131427444));
    this.overlayView = findViewById(2131427443);
    Object localObject;
    BlurImageView localBlurImageView;
    if (NVApplication.CLIENT_TYPE == 101)
    {
      this.blurImageView.setVisibility(0);
      localObject = new Rect();
      getWindow().getDecorView().getWindowVisibleDisplayFrame((Rect)localObject);
      localObject = ((NVImageLoader)getService("imageLoader")).getLocal("assets://icon-community.jpg", ((Rect)localObject).width() / 2, ((Rect)localObject).height() / 2, true);
      localBlurImageView = this.blurImageView;
      if (localObject == null) {
        localObject = null;
      }
    }
    for (;;)
    {
      localBlurImageView.setImageDrawable2((Drawable)localObject);
      this.flipper.setVisibility(8);
      this.gradientView.setVisibility(8);
      if ((this.blurImageView.getDrawable() != null) && ((this.blurImageView.getDrawable() instanceof BitmapDrawable)) && (((BitmapDrawable)this.blurImageView.getDrawable()).getBitmap() != null) && (PaletteUtils.isLightTone(this.blurImageView))) {
        this.overlayView.setVisibility(0);
      }
      if (paramBundle == null)
      {
        getSupportFragmentManager().beginTransaction().add(new SignupLocationFragment(), "signupLocation").commit();
        getSupportFragmentManager().beginTransaction().add(2131427446, new LoginOrSignupFragment()).commit();
      }
      updateViews();
      registerLocalReceiver(this.receiver, new IntentFilter("com.narvii.action.KEYCHAIN_STATUS_CHANGED"));
      getActionBar().hide();
      this.signupWakeup = getBooleanParam("signupWakeup");
      StatusBarUtils.addMarginTopToContentChild(this, findViewById(2131427446));
      this.ut = ((int)SystemClock.uptimeMillis());
      try
      {
        this.mSensorManager = ((SensorManager)getSystemService("sensor"));
        this.sel = new SEL(null);
        paramBundle = this.mSensorManager.getDefaultSensor(5);
        this.mSensorManager.registerListener(this.sel, paramBundle, 3);
        paramBundle = this.mSensorManager.getDefaultSensor(1);
        this.mSensorManager.registerListener(this.sel, paramBundle, 3);
        paramBundle = this.mSensorManager.getDefaultSensor(4);
        this.mSensorManager.registerListener(this.sel, paramBundle, 3);
        try
        {
          this.md = MessageDigest.getInstance("SHA-1");
          this.density = getResources().getDisplayMetrics().density;
          return;
          localObject = new BitmapDrawable((Bitmap)localObject);
          continue;
          this.flipper.setOnFlipperScrollListener(this.flipperScrollListener);
          this.flipper.setAdapter(this.flipperAdapter);
          this.flipper.setCurrentItem(Integer.valueOf(0));
          new Thread()
          {
            public void run()
            {
              try
              {
                Thread.sleep(1000L);
                int i = 0;
                while (i < 4)
                {
                  LoginActivity.this.flipperAdapter.getView(Integer.valueOf(i), null);
                  i += 1;
                }
                return;
              }
              catch (Throwable localThrowable) {}
            }
          }.start();
          updateGradient();
        }
        catch (Exception paramBundle)
        {
          for (;;) {}
        }
      }
      catch (Throwable paramBundle)
      {
        for (;;) {}
      }
    }
  }
  
  protected void onDestroy()
  {
    if ((this.mSensorManager != null) && (this.sel != null)) {
      this.mSensorManager.unregisterListener(this.sel);
    }
    unregisterLocalReceiver(this.receiver);
    if (isFinishing()) {
      Utils.handler.removeCallbacks(this.scheduleSignupWakeup);
    }
    super.onDestroy();
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    this.signupWakeup |= paramIntent.getBooleanExtra("signupWakeup", false);
  }
  
  protected void onResume()
  {
    super.onResume();
    this.startingActivity = false;
  }
  
  protected void onStart()
  {
    ((AlarmManager)getSystemService("alarm")).cancel(PendingIntent.getService(this, 0, new Intent(this, FinishSignupNotificationService.class), 0));
    ((NotificationManager)getSystemService("notification")).cancel(4609);
    super.onStart();
  }
  
  protected void onStop()
  {
    super.onStop();
    Object localObject2;
    Object localObject1;
    if ((!this.startingActivity) && (!this.account.hasAccount()))
    {
      localObject2 = (StatisticsService)getService("statistics");
      if (this.maxLoginStep <= 0) {
        break label263;
      }
      localObject1 = "Login";
      localObject1 = ((StatisticsService)localObject2).event("Registration Quit").priority(10).param("Step", (String)localObject1).source(getStringParam("Source"));
      if (this.lastErrorMsg != null)
      {
        localObject2 = this.lastErrorMsg;
        switch (this.lastErrorCode)
        {
        }
      }
    }
    for (;;)
    {
      ((StatisticsEventBuilder)localObject1).param("Error", this.lastErrorCode + " - " + this.lastErrorMsg);
      ((StatisticsEventBuilder)localObject1).param("Error Code", this.lastErrorCode);
      if (!this.signupWakeup)
      {
        int j = 0;
        localObject1 = getSupportFragmentManager().getFragments().iterator();
        do
        {
          i = j;
          if (!((Iterator)localObject1).hasNext()) {
            break;
          }
          localObject2 = (Fragment)((Iterator)localObject1).next();
        } while ((!(localObject2 instanceof Signup1Fragment)) && (!(localObject2 instanceof Signup2Fragment)));
        int i = 1;
        if (i != 0) {
          Utils.postDelayed(this.scheduleSignupWakeup, 100L);
        }
      }
      return;
      label263:
      if (this.maxSignupStep > 0)
      {
        localObject1 = "Signup " + this.maxSignupStep;
        break;
      }
      localObject1 = "Zero";
      break;
      continue;
      continue;
      continue;
    }
  }
  
  void procReq(ApiRequest.Builder paramBuilder)
  {
    int[] arrayOfInt = new int[4];
    int[] tmp7_5 = arrayOfInt;
    tmp7_5[0] = 2;
    int[] tmp11_7 = tmp7_5;
    tmp11_7[1] = 3;
    int[] tmp15_11 = tmp11_7;
    tmp15_11[2] = 4;
    int[] tmp19_15 = tmp15_11;
    tmp19_15[3] = 5;
    tmp19_15;
    String str = getString(2131100866);
    int j = arrayOfInt.length;
    int i = 0;
    while (i < j)
    {
      int k = arrayOfInt[i];
      Object localObject = getIds(k);
      if (localObject != null)
      {
        localObject = NativeHelper.C((byte[])localObject, str, 1);
        paramBuilder.param("deviceID" + k, localObject);
      }
      i += 1;
    }
    arrayOfInt = getVals();
    i = 0;
    while (i < arrayOfInt.length)
    {
      paramBuilder.param("val" + (i + 1), Integer.valueOf(arrayOfInt[i]));
      i += 1;
    }
  }
  
  void setSubmitting(LoginBaseFragment paramLoginBaseFragment)
  {
    if (paramLoginBaseFragment != null) {
      SoftKeyboard.hideSoftKeyboard(this);
    }
    this.submittingFragment = paramLoginBaseFragment;
    updateViews();
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt)
  {
    this.startingActivity = true;
    super.startActivityForResult(paramIntent, paramInt);
  }
  
  public void startActivityFromFragment(Fragment paramFragment, Intent paramIntent, int paramInt, Bundle paramBundle)
  {
    this.startingActivity = true;
    super.startActivityFromFragment(paramFragment, paramIntent, paramInt, paramBundle);
  }
  
  void updateGradient()
  {
    int i = this.gradientView.getWidth();
    int j = ((Integer)this.flipper.getCurrentItem()).intValue();
    int k = this.gradientColors1.length;
    float f2 = this.flipper.flipDistance();
    float f1 = f2;
    if (Utils.isRtl()) {
      f1 = -f2;
    }
    if (i == 0) {
      f1 = 0.0F;
    }
    for (;;)
    {
      f2 = f1;
      if (f1 >= 0.0F) {
        break;
      }
      f1 += k;
      continue;
      f1 = j + Math.min(1.0F, Math.max(0.0F, f1 / i));
    }
    while (f2 >= k) {
      f2 -= k;
    }
    j = this.gradientColors1[((int)f2)];
    int[] arrayOfInt = this.gradientColors1;
    if ((int)f2 == 3)
    {
      i = 0;
      i = arrayOfInt[i];
      f1 = f2 - (int)f2;
      j = Color.argb((int)(Color.alpha(j) * (1.0F - f1) + Color.alpha(i) * f1), (int)(Color.red(j) * (1.0F - f1) + Color.red(i) * f1), (int)(Color.green(j) * (1.0F - f1) + Color.green(i) * f1), (int)(Color.blue(j) * (1.0F - f1) + Color.blue(i) * f1));
      k = this.gradientColors2[((int)f2)];
      arrayOfInt = this.gradientColors2;
      if ((int)f2 != 3) {
        break label351;
      }
    }
    label351:
    for (i = 0;; i = (int)f2 + 1)
    {
      i = arrayOfInt[i];
      i = Color.argb((int)(Color.alpha(k) * (1.0F - f1) + Color.alpha(i) * f1), (int)(Color.red(k) * (1.0F - f1) + Color.red(i) * f1), (int)(Color.green(k) * (1.0F - f1) + Color.green(i) * f1), (int)(Color.blue(k) * (1.0F - f1) + Color.blue(i) * f1));
      this.gradientView.setColor(j, i);
      return;
      i = (int)f2 + 1;
      break;
    }
  }
  
  void updateViews()
  {
    boolean bool2 = true;
    Object localObject = findViewById(2131427446);
    View localView = findViewById(2131427447);
    TextView localTextView = (TextView)localView.findViewById(2131427448);
    boolean bool1;
    if ((this.submittingFragment != null) || (this.account.getKeychainStatus() > 0))
    {
      bool1 = true;
      if (bool1) {
        break label89;
      }
      label55:
      setVisibilityAnim((View)localObject, bool2);
      setVisibilityAnim(localView, bool1);
      if (this.submittingFragment != null) {
        break label94;
      }
    }
    label89:
    label94:
    for (localObject = null;; localObject = this.submittingFragment.getProgressText())
    {
      localTextView.setText((CharSequence)localObject);
      return;
      bool1 = false;
      break;
      bool2 = false;
      break label55;
    }
  }
  
  private class SEL
    implements SensorEventListener
  {
    private SEL() {}
    
    private float[] copy(float[] paramArrayOfFloat)
    {
      float[] arrayOfFloat = new float[paramArrayOfFloat.length];
      System.arraycopy(paramArrayOfFloat, 0, arrayOfFloat, 0, paramArrayOfFloat.length);
      return arrayOfFloat;
    }
    
    public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
    
    public void onSensorChanged(SensorEvent paramSensorEvent)
    {
      Object localObject3 = null;
      Object localObject4 = null;
      Object localObject1 = localObject4;
      Object localObject2 = localObject3;
      switch (paramSensorEvent.sensor.getType())
      {
      default: 
        localObject2 = localObject3;
        localObject1 = localObject4;
      }
      for (;;)
      {
        int i = 0;
        int j = Math.min(paramSensorEvent.values.length, localObject2.length);
        while (i < j)
        {
          localObject2[i] = Math.min(localObject2[i], paramSensorEvent.values[i]);
          localObject1[i] = Math.max(localObject1[i], paramSensorEvent.values[i]);
          i += 1;
        }
        if (LoginActivity.this.lightMin == null) {
          LoginActivity.this.lightMin = copy(paramSensorEvent.values);
        }
        if (LoginActivity.this.lightMax == null) {
          LoginActivity.this.lightMax = copy(paramSensorEvent.values);
        }
        localObject2 = LoginActivity.this.lightMin;
        localObject1 = LoginActivity.this.lightMax;
        continue;
        if (LoginActivity.this.accMin == null) {
          LoginActivity.this.accMin = copy(paramSensorEvent.values);
        }
        if (LoginActivity.this.accMax == null) {
          LoginActivity.this.accMax = copy(paramSensorEvent.values);
        }
        localObject2 = LoginActivity.this.accMin;
        localObject1 = LoginActivity.this.accMax;
        continue;
        if (LoginActivity.this.gyoMin == null) {
          LoginActivity.this.gyoMin = copy(paramSensorEvent.values);
        }
        if (LoginActivity.this.gyoMax == null) {
          LoginActivity.this.gyoMax = copy(paramSensorEvent.values);
        }
        localObject2 = LoginActivity.this.gyoMin;
        localObject1 = LoginActivity.this.gyoMax;
      }
    }
  }
}
