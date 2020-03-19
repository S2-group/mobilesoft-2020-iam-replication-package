package jp.co.forii.jajajajanen;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.media.SoundPool;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders.EventBuilder;
import com.google.android.gms.analytics.Tracker;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.growthbeat.GrowthbeatJNI;
import com.growthbeat.analytics.GrowthAnalytics;
import com.growthbeat.analytics.GrowthAnalytics.TrackOption;
import com.growthbeat.link.GrowthLinkJNI;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import jp.co.forii.jajajajanen.util.AppUUID;
import jp.co.forii.jajajajanen.util.IabHelper;
import org.cocos2dx.lib.Cocos2dxActivity;

public class ForiiEhon
  extends Cocos2dxActivity
  implements VideoView.VideoPlayListener, View.OnClickListener, SeekBar.OnSeekBarChangeListener
{
  static final String APP_TITLE = "Moving Books! jajajajan";
  static final String FACEBOOK_APP_ID = "629292810451188";
  static final boolean IS_DEBUG = false;
  static final String PUSHWOOSH_APP_ID = "E7EC7-B9CA6";
  private static final int REQ_RESOLVE_SERVICE_MISSING = 2;
  private static final int REQ_START_STANDALONE_PLAYER = 1;
  static final int SCREEN_ORIENTATION_SENSOR_LANDSCAPE = 6;
  static final String SENDER_ID = "380830502334";
  public static GoogleAnalytics analytics;
  private static ForiiEhon instance;
  private static GLSurfaceView mGLView;
  private static Drawable mPauseDrawable;
  private static Drawable mPlayDrawable;
  private static Drawable mRepeatAllDrawable;
  private static Drawable mRepeatOffDrawable;
  private static Drawable mRepeatOneDrawable;
  static int mShareType = 0;
  static String mShareUrl;
  private static Activity me = null;
  AnimationDrawable mAnimationDrawable;
  LinearLayout mAnimationLayout;
  private AutoHideProgressRunnable mAutoHideProgressRunnable;
  TextView[] mBaseTextView;
  private LinearLayout mBottomLayout;
  private int mButtonSoundID = 0;
  private long mCallbackAddress;
  private String mCheckImageFolder;
  private int mCloseSoundID = 0;
  private LinearLayout mConfirmLayout;
  private String mCurrentBookId;
  private Drawable[] mFrame;
  private LinearLayout mGateFrameLayout;
  private LinearLayout mGateTmpLayout;
  private Handler mHandler;
  private LinearLayout mHeaderLayout;
  private String mImageFolder;
  TextView[] mInputTextView;
  private boolean mIsStoped = false;
  CustomProgressDialog mLoadingDialog;
  private RelativeLayout mLoadingParentLayout;
  private LinearLayout mNextInfoLayout;
  private RelativeLayout mParentLayout;
  private Button mPlayBtn;
  private UpdateVideoProgressRunnable mProgressRunnable;
  private SeekBar mProgressSekBar;
  private Button mRepeatBtn;
  private REPEAT_TYPE mRepeatType = REPEAT_TYPE.OFF;
  private float mScaleX = 1.0F;
  private float mScaleY = 1.0F;
  private SoundPool mSoundPool = null;
  private String mStartVideoName = "opening_android_en.mp4";
  private TextView mTimeTxt;
  private final String mVideoCategroy = "videoPlayer";
  private VideoView mVideoView;
  public Tracker tracker;
  
  static
  {
    mShareUrl = "";
  }
  
  public ForiiEhon() {}
  
  private static native void JNICallBack(long paramLong);
  
  private static native void alertCallback(int paramInt1, int paramInt2);
  
  private void backPrevPage()
  {
    if (this.mHandler != null) {
      this.mHandler.removeCallbacks(this.mProgressRunnable);
    }
    releaseView();
    JNICallBack(this.mCallbackAddress);
  }
  
  private boolean canResolveIntent(Intent paramIntent)
  {
    boolean bool2 = false;
    paramIntent = getPackageManager().queryIntentActivities(paramIntent, 0);
    boolean bool1 = bool2;
    if (paramIntent != null)
    {
      bool1 = bool2;
      if (!paramIntent.isEmpty()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private void changeRepeatType()
  {
    if (this.mRepeatType == REPEAT_TYPE.OFF)
    {
      this.mRepeatBtn.setBackgroundDrawable(mRepeatOneDrawable);
      this.mRepeatType = REPEAT_TYPE.ONE;
    }
    for (;;)
    {
      SharedPreferences.Editor localEditor = getSharedPreferences("DataSave", 0).edit();
      localEditor.putInt("RepeatType", this.mRepeatType.ordinal());
      localEditor.apply();
      return;
      if (this.mRepeatType == REPEAT_TYPE.ONE)
      {
        this.mRepeatBtn.setBackgroundDrawable(mRepeatAllDrawable);
        this.mRepeatType = REPEAT_TYPE.ALL;
      }
      else if (this.mRepeatType == REPEAT_TYPE.ALL)
      {
        this.mRepeatBtn.setBackgroundDrawable(mRepeatOffDrawable);
        this.mRepeatType = REPEAT_TYPE.OFF;
      }
    }
  }
  
  private void checkNextBookInfo(int paramInt)
  {
    Object localObject = getNextInfo(paramInt);
    System.out.println(String.format("Next video info:%s", new Object[] { localObject }));
    if ((localObject == null) || (((String)localObject).length() == 0))
    {
      playError();
      backPrevPage();
      return;
    }
    this.mVideoView.pausePlay();
    if ((localObject != null) && (((String)localObject).length() > 0))
    {
      localObject = ((String)localObject).split(",");
      if ((localObject.length > 1) && (localObject[1] != null) && (localObject[1].length() > 0))
      {
        int i = localObject.length;
        this.mIsStoped = false;
        this.mPlayBtn.setBackgroundDrawable(mPauseDrawable);
        this.mVideoView.startPlay(localObject[1]);
        this.mCurrentBookId = localObject[2];
      }
    }
    else
    {
      if (paramInt != -1) {
        break label187;
      }
      sendAnalyticsEvent("videoPlayer", "prevMovie", this.mCurrentBookId, this.mVideoView.getPercentage());
    }
    for (;;)
    {
      sendAnalyticsEvent("videoPlayer", "started", this.mCurrentBookId, this.mVideoView.getPercentage());
      return;
      this.mIsStoped = true;
      showNextBookInfo(localObject[0]);
      break;
      label187:
      if (paramInt == 0) {
        sendAnalyticsEvent("videoPlayer", "repeatMovie", this.mCurrentBookId, this.mVideoView.getPercentage());
      } else if (paramInt == 1) {
        sendAnalyticsEvent("videoPlayer", "nextMovie", this.mCurrentBookId, this.mVideoView.getPercentage());
      }
    }
  }
  
  private void dismissConfirmView()
  {
    if (this.mConfirmLayout != null)
    {
      this.mConfirmLayout.removeAllViews();
      this.mParentLayout.removeView(this.mConfirmLayout);
      this.mConfirmLayout = null;
    }
  }
  
  private void dismissNextBookInfoView()
  {
    if (this.mNextInfoLayout != null)
    {
      this.mNextInfoLayout.removeAllViews();
      this.mParentLayout.removeView(this.mNextInfoLayout);
      this.mNextInfoLayout = null;
    }
  }
  
  public static String getCacheFileRoot()
  {
    localObject1 = "";
    try
    {
      localObject2 = Environment.getExternalStorageDirectory().getPath();
      localObject1 = localObject2;
      String str = String.format("%s/ForiiEhon/", new Object[] { localObject2 });
      localObject1 = str;
      File localFile = new File(str);
      localObject2 = str;
      localObject1 = str;
      if (!localFile.exists())
      {
        localObject2 = str;
        localObject1 = str;
        if (!localFile.mkdirs()) {
          localObject2 = "";
        }
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject2;
        System.out.println(localException.getMessage());
        Object localObject3 = localObject1;
      }
    }
    System.out.println(String.format("cache file path:%s", new Object[] { localObject2 }));
    return localObject2;
  }
  
  private String getCheckImageFolder()
  {
    int j;
    int k;
    int i;
    String str;
    if (this.mCheckImageFolder == null)
    {
      j = Math.max(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
      k = Math.min(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
      if (k <= 740) {
        break label142;
      }
      i = 1;
      if (i == 0) {
        break label147;
      }
      str = "tablet/check/";
      label75:
      this.mCheckImageFolder = str;
      if (i == 0) {
        break label155;
      }
      f = j / 1920.0F;
      label92:
      this.mScaleX = f;
      if (i == 0) {
        break label165;
      }
    }
    label142:
    label147:
    label155:
    label165:
    for (float f = k / 1200.0F;; f = k / 720.0F)
    {
      this.mScaleY = f;
      System.out.println(String.format("image folder:%s", new Object[] { this.mImageFolder }));
      return this.mCheckImageFolder;
      i = 0;
      break;
      str = "phone/check/";
      break label75;
      f = j / 1280.0F;
      break label92;
    }
  }
  
  public static String getDeviceUUID()
  {
    return AppUUID.id(me);
  }
  
  private String getImageFolder()
  {
    int j;
    int k;
    int i;
    String str;
    if (this.mImageFolder == null)
    {
      j = Math.max(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
      k = Math.min(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
      if (k <= 740) {
        break label142;
      }
      i = 1;
      if (i == 0) {
        break label147;
      }
      str = "tablet/video/";
      label75:
      this.mImageFolder = str;
      if (i == 0) {
        break label155;
      }
      f = j / 1920.0F;
      label92:
      this.mScaleX = f;
      if (i == 0) {
        break label165;
      }
    }
    label142:
    label147:
    label155:
    label165:
    for (float f = k / 1200.0F;; f = k / 720.0F)
    {
      this.mScaleY = f;
      System.out.println(String.format("image folder:%s", new Object[] { this.mImageFolder }));
      return this.mImageFolder;
      i = 0;
      break;
      str = "phone/video/";
      break label75;
      f = j / 1280.0F;
      break label92;
    }
  }
  
  private String getLoadingImageFolder()
  {
    int j;
    int k;
    int i;
    if (this.mImageFolder == null)
    {
      j = Math.max(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
      k = Math.min(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
      if (k <= 740) {
        break label111;
      }
      i = 1;
      this.mImageFolder = "common/animation/loading/";
      if (i == 0) {
        break label116;
      }
      f = j / 1920.0F;
      label84:
      this.mScaleX = f;
      if (i == 0) {
        break label126;
      }
    }
    label111:
    label116:
    label126:
    for (float f = k / 1200.0F;; f = k / 720.0F)
    {
      this.mScaleY = f;
      return this.mImageFolder;
      i = 0;
      break;
      f = j / 1280.0F;
      break label84;
    }
  }
  
  private static native String getNextInfo(int paramInt);
  
  private float getRatio()
  {
    if (isPad()) {}
    for (;;)
    {
      return 2.0F / getContext().getResources().getDisplayMetrics().density;
    }
  }
  
  private String getVideoDurationText(int paramInt)
  {
    String str2 = new SimpleDateFormat("mm:ss").format(Integer.valueOf(paramInt));
    String str1 = str2;
    if (str2.startsWith("0")) {
      str1 = str2.substring(1, str2.length());
    }
    return str1;
  }
  
  private void initView(boolean paramBoolean)
  {
    this.mGateFrameLayout = null;
    if (this.mParentLayout != null) {
      System.out.println("no need create new view");
    }
    Object localObject1;
    do
    {
      return;
      this.mParentLayout = new RelativeLayout(this);
      this.mParentLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
      ((ViewGroup)getWindow().getDecorView()).addView(this.mParentLayout);
      localObject1 = new LinearLayout.LayoutParams(-2, -2);
      this.mVideoView = new VideoView(this);
      this.mVideoView.setVideoPlayListener(this);
      this.mVideoView.setZOrderMediaOverlay(true);
      this.mVideoView.setOnClickListener(this);
      this.mVideoView.setTag(Integer.valueOf(4));
      this.mParentLayout.addView(this.mVideoView);
    } while (!paramBoolean);
    this.mHeaderLayout = new LinearLayout(this);
    Object localObject2 = new RelativeLayout.LayoutParams(-2, -2);
    ((RelativeLayout.LayoutParams)localObject2).addRule(14);
    this.mHeaderLayout.setLayoutParams((ViewGroup.LayoutParams)localObject2);
    this.mParentLayout.addView(this.mHeaderLayout);
    localObject2 = new Button(this);
    ((Button)localObject2).setOnClickListener(this);
    ((Button)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject1);
    ((Button)localObject2).setTag(Integer.valueOf(3));
    ((Button)localObject2).setBackgroundDrawable(readImageInAssets("player_home_off.png", 0));
    this.mHeaderLayout.addView((View)localObject2);
    ((Button)localObject2).setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        if (paramAnonymousMotionEvent.getAction() == 0) {
          paramAnonymousView.setBackgroundDrawable(ForiiEhon.this.readImageInAssets("player_home_on.png", 0));
        }
        while (paramAnonymousMotionEvent.getAction() != 1) {
          return false;
        }
        paramAnonymousView.setBackgroundDrawable(ForiiEhon.this.readImageInAssets("player_home_off.png", 0));
        return false;
      }
    });
    localObject2 = new Button(this);
    ((Button)localObject2).setOnClickListener(this);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
    localLayoutParams.setMargins(30, 0, 0, 0);
    ((Button)localObject2).setLayoutParams(localLayoutParams);
    ((Button)localObject2).setBackgroundDrawable(readImageInAssets("player_prev_off.png", 0));
    ((Button)localObject2).setTag(Integer.valueOf(5));
    this.mHeaderLayout.addView((View)localObject2);
    ((Button)localObject2).setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        if (paramAnonymousMotionEvent.getAction() == 0) {
          paramAnonymousView.setBackgroundDrawable(ForiiEhon.this.readImageInAssets("player_prev_on.png", 0));
        }
        while (paramAnonymousMotionEvent.getAction() != 1) {
          return false;
        }
        paramAnonymousView.setBackgroundDrawable(ForiiEhon.this.readImageInAssets("player_prev_off.png", 0));
        return false;
      }
    });
    mPlayDrawable = readImageInAssets("player_play.png", 0);
    mPauseDrawable = readImageInAssets("player_pause_off.png", 0);
    mRepeatOffDrawable = readImageInAssets("player_repeat_off.png", 0);
    mRepeatOneDrawable = readImageInAssets("player_repeat_one.png", 0);
    mRepeatAllDrawable = readImageInAssets("player_repeat_all.png", 0);
    this.mPlayBtn = new Button(this);
    this.mPlayBtn.setOnClickListener(this);
    this.mPlayBtn.setTag(Integer.valueOf(1));
    this.mPlayBtn.setLayoutParams((ViewGroup.LayoutParams)localObject1);
    this.mPlayBtn.setBackgroundDrawable(mPauseDrawable);
    this.mHeaderLayout.addView(this.mPlayBtn);
    localObject2 = new Button(this);
    ((Button)localObject2).setOnClickListener(this);
    ((Button)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject1);
    ((Button)localObject2).setBackgroundDrawable(readImageInAssets("player_next_off.png", 0));
    ((Button)localObject2).setTag(Integer.valueOf(6));
    this.mHeaderLayout.addView((View)localObject2);
    ((Button)localObject2).setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        if (paramAnonymousMotionEvent.getAction() == 0) {
          paramAnonymousView.setBackgroundDrawable(ForiiEhon.this.readImageInAssets("player_next_on.png", 0));
        }
        while (paramAnonymousMotionEvent.getAction() != 1) {
          return false;
        }
        paramAnonymousView.setBackgroundDrawable(ForiiEhon.this.readImageInAssets("player_next_off.png", 0));
        return false;
      }
    });
    int i = getSharedPreferences("DataSave", 0).getInt("RepeatType", 0);
    this.mRepeatType = REPEAT_TYPE.values()[i];
    this.mRepeatBtn = new Button(this);
    this.mRepeatBtn.setOnClickListener(this);
    this.mRepeatBtn.setLayoutParams((ViewGroup.LayoutParams)localObject1);
    if (this.mRepeatType == REPEAT_TYPE.OFF) {
      this.mRepeatBtn.setBackgroundDrawable(mRepeatOffDrawable);
    }
    for (;;)
    {
      this.mRepeatBtn.setTag(Integer.valueOf(8));
      this.mHeaderLayout.addView(this.mRepeatBtn);
      this.mBottomLayout = new LinearLayout(this);
      localObject1 = new RelativeLayout.LayoutParams(-1, -2);
      ((RelativeLayout.LayoutParams)localObject1).setMargins(0, 0, 0, 5);
      ((RelativeLayout.LayoutParams)localObject1).addRule(12);
      this.mBottomLayout.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      this.mBottomLayout.setGravity(17);
      this.mParentLayout.addView(this.mBottomLayout);
      this.mProgressSekBar = new SeekBar(this);
      localObject1 = new LinearLayout.LayoutParams(-1, -2);
      ((LinearLayout.LayoutParams)localObject1).setMargins(180, 0, 0, 0);
      this.mProgressSekBar.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      this.mProgressSekBar.setThumb(readImageInAssets("play_timeline_jan.png", 0));
      this.mProgressSekBar.setPadding((int)(50.0F * this.mScaleX), 0, (int)(50.0F * this.mScaleX), 0);
      this.mProgressSekBar.setOnSeekBarChangeListener(this);
      this.mBottomLayout.addView(this.mProgressSekBar);
      this.mTimeTxt = new TextView(this);
      localObject1 = new LinearLayout.LayoutParams(100, -2);
      ((LinearLayout.LayoutParams)localObject1).setMargins(0, 0, 100, 0);
      this.mTimeTxt.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      this.mTimeTxt.setTextColor(-1);
      this.mTimeTxt.setText("0::00");
      this.mBottomLayout.addView(this.mTimeTxt);
      this.mGateTmpLayout = new LinearLayout(this);
      this.mGateTmpLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
      this.mGateTmpLayout.setGravity(17);
      this.mGateTmpLayout.setClipChildren(false);
      this.mParentLayout.addView(this.mGateTmpLayout);
      return;
      if (this.mRepeatType == REPEAT_TYPE.ONE) {
        this.mRepeatBtn.setBackgroundDrawable(mRepeatOneDrawable);
      } else if (this.mRepeatType == REPEAT_TYPE.ALL) {
        this.mRepeatBtn.setBackgroundDrawable(mRepeatAllDrawable);
      }
    }
  }
  
  private boolean isPad()
  {
    return Math.min(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels) > 740;
  }
  
  public static boolean isSony()
  {
    return (Build.MANUFACTURER.equals("Sony")) || (Build.MANUFACTURER.equals("sony")) || (Build.MANUFACTURER.equals("SONY"));
  }
  
  public static void launchUrl(String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    me.startActivity(paramString);
  }
  
  private void loadSounds()
  {
    this.mSoundPool = new SoundPool(2, 3, 0);
    try
    {
      this.mButtonSoundID = this.mSoundPool.load(getAssets().openFd("sound/button.mp3"), 1);
      this.mCloseSoundID = this.mSoundPool.load(getAssets().openFd("sound/close.mp3"), 1);
      return;
    }
    catch (IOException localIOException) {}
  }
  
  private void moveOpenVideo()
  {
    for (;;)
    {
      Object localObject;
      byte[] arrayOfByte;
      int i;
      try
      {
        InputStream localInputStream = getAssets().open(this.mStartVideoName);
        localObject = new File(String.format("%s/%s", new Object[] { getCacheFileRoot(), this.mStartVideoName }));
        if (!((File)localObject).getParentFile().exists()) {
          break;
        }
        if (((File)localObject).exists()) {
          return;
        }
        localObject = new FileOutputStream((File)localObject);
        arrayOfByte = new byte[' '];
        i = localInputStream.read(arrayOfByte);
        if (i <= 0)
        {
          ((FileOutputStream)localObject).flush();
          ((FileOutputStream)localObject).close();
          localInputStream.close();
          return;
        }
      }
      catch (Exception localException)
      {
        System.out.println(localException.getMessage());
        return;
      }
      ((FileOutputStream)localObject).write(arrayOfByte, 0, i);
    }
  }
  
  private void onClickParentalgatelNum(int paramInt)
  {
    int i = 1;
    if (this.mInputTextView[0].getVisibility() == 4)
    {
      this.mInputTextView[0].setVisibility(0);
      this.mInputTextView[0].setText(paramInt);
    }
    for (;;)
    {
      return;
      if (this.mInputTextView[1].getVisibility() == 4)
      {
        this.mInputTextView[1].setVisibility(0);
        this.mInputTextView[1].setText(paramInt);
        return;
      }
      if (this.mInputTextView[2].getVisibility() == 4)
      {
        this.mInputTextView[2].setVisibility(0);
        this.mInputTextView[2].setText(paramInt);
        return;
      }
      if (this.mInputTextView[3].getVisibility() == 4)
      {
        this.mInputTextView[3].setVisibility(0);
        this.mInputTextView[3].setText(paramInt);
        if ((Integer.parseInt(this.mInputTextView[3].getText().toString()) == Integer.parseInt(this.mBaseTextView[3].getText().toString())) && (Integer.parseInt(this.mInputTextView[2].getText().toString()) == Integer.parseInt(this.mBaseTextView[2].getText().toString())) && (Integer.parseInt(this.mInputTextView[1].getText().toString()) == Integer.parseInt(this.mBaseTextView[1].getText().toString())) && (Integer.parseInt(this.mInputTextView[0].getText().toString()) == Integer.parseInt(this.mBaseTextView[0].getText().toString())))
        {
          onCloseParentalGate();
          if (mShareType == 1) {}
          for (paramInt = i;; paramInt = 0)
          {
            onSnsBtn(paramInt, mShareUrl, "", "");
            return;
          }
        }
        Random localRandom = new Random();
        paramInt = 0;
        while (paramInt < 4)
        {
          this.mInputTextView[paramInt].setVisibility(4);
          this.mBaseTextView[paramInt].setText(localRandom.nextInt(10));
          paramInt += 1;
        }
      }
    }
  }
  
  private void onCloseParentalGate()
  {
    this.mGateFrameLayout.setVisibility(8);
    this.mGateFrameLayout.removeAllViews();
    this.mConfirmLayout.setVisibility(0);
  }
  
  public static void onSnsBtn(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    Object localObject;
    if (paramInt == 0)
    {
      localObject = instance.getPackageManager().getInstalledApplications(0).iterator();
      if (!((Iterator)localObject).hasNext())
      {
        label30:
        break label229;
        label31:
        localObject = new Intent("android.intent.action.SEND");
        if (paramString1.length() <= 0) {
          break label361;
        }
        ((Intent)localObject).putExtra("android.intent.extra.TEXT", paramString2 + "\n" + paramString1 + "\n" + paramString3);
      }
    }
    for (;;)
    {
      ((Intent)localObject).setType("text/plain");
      instance.startActivity(Intent.createChooser((Intent)localObject, "SHARE"));
      return;
      if (!"com.facebook.katana".equals(((ApplicationInfo)((Iterator)localObject).next()).packageName)) {
        break;
      }
      paramString2 = new Intent();
      paramString2.setAction("android.intent.action.SEND");
      paramString2.setPackage("com.facebook.katana");
      paramString2.setType("text/plain");
      if (paramString1.length() > 0) {
        paramString2.putExtra("android.intent.extra.TEXT", paramString1);
      }
      for (;;)
      {
        instance.startActivity(paramString2);
        return;
        paramString2.putExtra("android.intent.extra.TEXT", paramString3);
      }
      if (paramInt != 1) {
        break label31;
      }
      localObject = instance.getPackageManager().getInstalledApplications(0).iterator();
      label229:
      if (!((Iterator)localObject).hasNext()) {
        break label31;
      }
      if (!"com.twitter.android".equals(((ApplicationInfo)((Iterator)localObject).next()).packageName)) {
        break label30;
      }
      paramString2 = new Intent();
      paramString2.setAction("android.intent.action.SEND");
      paramString2.setPackage("com.twitter.android");
      paramString2.setType("text/plain");
      if (paramString1.length() > 0) {
        paramString2.putExtra("android.intent.extra.TEXT", "Moving Books! jajajajan\n" + paramString1);
      }
      for (;;)
      {
        instance.startActivity(paramString2);
        return;
        paramString2.putExtra("android.intent.extra.TEXT", "Moving Books! jajajajan\n" + paramString3);
      }
      label361:
      ((Intent)localObject).putExtra("android.intent.extra.TEXT", paramString2 + "\n" + paramString3);
    }
  }
  
  public static void openBrowser(String paramString)
  {
    try
    {
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(URLDecoder.decode(paramString, "utf-8")));
      instance.startActivity(paramString);
      return;
    }
    catch (UnsupportedEncodingException paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  private int parseInt(String paramString, int paramInt)
  {
    int i = paramInt;
    if (!TextUtils.isEmpty(paramString)) {}
    try
    {
      i = Integer.parseInt(paramString);
      return i;
    }
    catch (NumberFormatException paramString) {}
    return paramInt;
  }
  
  private static native void playError();
  
  public static void playOpeningVideo(String paramString, long paramLong)
  {
    if (instance != null)
    {
      instance.mCallbackAddress = paramLong;
      instance.runOnUiThread(new Runnable()
      {
        public void run()
        {
          ForiiEhon.instance.startPlayVideo(ForiiEhon.this, false);
        }
      });
    }
  }
  
  private void playSound(int paramInt)
  {
    this.mSoundPool.play(paramInt, 1.0F, 1.0F, 1, 0, 1.0F);
  }
  
  public static void playVideo(String paramString1, String paramString2, long paramLong, String paramString3)
  {
    mShareUrl = paramString3;
    System.out.println(String.format("filepath:%s", new Object[] { paramString1 }));
    instance.mCurrentBookId = paramString2;
    if (instance != null)
    {
      instance.mCallbackAddress = paramLong;
      instance.runOnUiThread(new Runnable()
      {
        public void run()
        {
          ForiiEhon.instance.startPlayVideo(ForiiEhon.this, true);
        }
      });
    }
  }
  
  public static void playYouTube(String paramString)
  {
    instance.runOnUiThread(new Runnable()
    {
      public void run()
      {
        ForiiEhon.instance.youtube(ForiiEhon.this);
      }
    });
  }
  
  public static void rakutenAction(String paramString) {}
  
  private Bitmap readImage(String paramString)
  {
    String str2 = null;
    String str1 = null;
    try
    {
      paramString = BitmapFactory.decodeFile(paramString);
      str1 = paramString;
      if (paramString != null)
      {
        str1 = paramString;
        str2 = paramString;
        Matrix localMatrix = new Matrix();
        str1 = paramString;
        str2 = paramString;
        localMatrix.postScale(this.mScaleX * 0.6F, this.mScaleY * 0.6F);
        str1 = paramString;
        str2 = paramString;
        paramString = Bitmap.createBitmap(paramString, 0, 0, paramString.getWidth(), paramString.getHeight(), localMatrix, true);
        str1 = paramString;
      }
      return str1;
    }
    catch (OutOfMemoryError paramString)
    {
      System.out.println(paramString.getMessage());
      return str1;
    }
    catch (Exception paramString)
    {
      System.out.println(paramString.getMessage());
    }
    return str2;
  }
  
  private BitmapDrawable readImageInAssets(String paramString, int paramInt)
  {
    localObject2 = null;
    localObject3 = null;
    Object localObject4 = null;
    if (paramInt == 0) {}
    for (;;)
    {
      try
      {
        localObject1 = String.format("%s%s", new Object[] { getImageFolder(), paramString });
        localObject1 = BitmapFactory.decodeStream(getAssets().open((String)localObject1));
        paramString = localObject4;
        if (localObject1 != null)
        {
          paramString = new Matrix();
          paramString.postScale(this.mScaleX, this.mScaleY);
          paramString = new BitmapDrawable(Bitmap.createBitmap((Bitmap)localObject1, 0, 0, ((Bitmap)localObject1).getWidth(), ((Bitmap)localObject1).getHeight(), paramString, true));
        }
      }
      catch (OutOfMemoryError localOutOfMemoryError1)
      {
        Object localObject1;
        paramString = localObject2;
        System.out.println(localOutOfMemoryError1.getMessage());
        return paramString;
      }
      catch (Exception localException1)
      {
        paramString = localObject3;
        System.out.println(localException1.getMessage());
        return paramString;
      }
      try
      {
        paramString.setTargetDensity(getResources().getDisplayMetrics());
        return paramString;
      }
      catch (Exception localException2)
      {
        continue;
      }
      catch (OutOfMemoryError localOutOfMemoryError2)
      {
        continue;
      }
      if (paramInt == 1)
      {
        localObject1 = String.format("%s%s", new Object[] { getLoadingImageFolder(), paramString });
      }
      else
      {
        localObject1 = paramString;
        if (paramInt == 2) {
          localObject1 = String.format("%s%s", new Object[] { getCheckImageFolder(), paramString });
        }
      }
    }
  }
  
  public static void reboot()
  {
    Context localContext = instance.getApplicationContext();
    PendingIntent localPendingIntent = PendingIntent.getActivity(localContext, 0, new Intent(localContext, ForiiEhon.class), 268435456);
    ((AlarmManager)localContext.getSystemService("alarm")).set(0, System.currentTimeMillis() + 100L, localPendingIntent);
    System.exit(0);
  }
  
  public static void releaseVideoView()
  {
    if (instance != null)
    {
      instance.mCallbackAddress = 0L;
      instance.runOnUiThread(new Runnable()
      {
        public void run()
        {
          ForiiEhon.instance.releaseView();
        }
      });
    }
  }
  
  private void releaseView()
  {
    ((ViewGroup)getWindow().getDecorView()).removeView(this.mParentLayout);
    this.mIsStoped = false;
    if (this.mHandler != null)
    {
      this.mHandler.removeCallbacks(this.mProgressRunnable);
      this.mHandler.removeCallbacks(this.mAutoHideProgressRunnable);
      this.mHandler = null;
    }
    this.mProgressRunnable = null;
    this.mAutoHideProgressRunnable = null;
    if (this.mHeaderLayout != null) {
      this.mHeaderLayout.removeAllViews();
    }
    if (this.mBottomLayout != null) {
      this.mBottomLayout.removeAllViews();
    }
    dismissConfirmView();
    dismissNextBookInfoView();
    if (this.mConfirmLayout != null) {
      this.mConfirmLayout.removeAllViews();
    }
    if (this.mGateTmpLayout != null) {
      this.mGateTmpLayout.removeAllViews();
    }
    if (this.mParentLayout != null)
    {
      this.mParentLayout.setVisibility(8);
      this.mParentLayout.removeAllViews();
    }
    mPlayDrawable = null;
    mPauseDrawable = null;
    this.mConfirmLayout = null;
    this.mGateTmpLayout = null;
    this.mHeaderLayout = null;
    this.mBottomLayout = null;
    this.mParentLayout = null;
    this.mProgressSekBar = null;
    this.mTimeTxt = null;
    if (this.mVideoView != null) {
      this.mVideoView.release();
    }
    this.mVideoView = null;
  }
  
  public static void removeLoading()
  {
    instance.runOnUiThread(new Runnable()
    {
      public void run()
      {
        ForiiEhon.instance.mLoadingDialog.dissmis();
        ForiiEhon.instance.removeViewGroup();
      }
    });
  }
  
  public static void removeStartLoadingAnimation()
  {
    instance.runOnUiThread(new Runnable()
    {
      public void run()
      {
        ForiiEhon.instance.mAnimationDrawable.stop();
        ForiiEhon.instance.mAnimationDrawable = null;
        ForiiEhon.instance.mLoadingParentLayout.removeAllViews();
        ForiiEhon.instance.mAnimationLayout.removeAllViews();
        ForiiEhon.instance.mAnimationLayout = null;
        ForiiEhon.instance.removeViewGroup();
      }
    });
  }
  
  private void removeViewGroup()
  {
    ((ViewGroup)getWindow().getDecorView()).removeView(this.mLoadingParentLayout);
    this.mLoadingParentLayout = null;
  }
  
  public static void sendAnalyticsEvent(String paramString)
  {
    System.out.println(paramString);
    if ((paramString != null) && (paramString.length() > 0)) {
      instance.runOnUiThread(new Runnable()
      {
        public void run()
        {
          ForiiEhon.instance.tracker.setScreenName(ForiiEhon.this);
        }
      });
    }
  }
  
  public static void sendAnalyticsEvent(String paramString1, String paramString2, final String paramString3, final long paramLong)
  {
    String str = String.format("category:%s,action:%s,lable:%s,value:%d", new Object[] { paramString1, paramString2, paramString3, Long.valueOf(paramLong) });
    System.out.println(str);
    if ((paramString1 != null) || (paramString2 != null)) {
      instance.runOnUiThread(new Runnable()
      {
        public void run()
        {
          ForiiEhon.instance.tracker.send(new HitBuilders.EventBuilder().setCategory(ForiiEhon.this).setAction(ForiiEhon.this).setLabel(paramString3).setValue(paramLong).build());
          GrowthAnalytics.getInstance().track(this.val$action, paramString3, null, GrowthAnalytics.TrackOption.COUNTER);
        }
      });
    }
  }
  
  public static void showAlert(String paramString1, final String paramString2, final String paramString3, final String paramString4, final int paramInt)
  {
    instance.runOnUiThread(new Runnable()
    {
      public void run()
      {
        ForiiEhon.instance.setAlert(ForiiEhon.this, paramString2, paramString3, paramString4, paramInt);
      }
    });
  }
  
  private void showConfirmView(boolean paramBoolean)
  {
    this.mHeaderLayout.setVisibility(8);
    this.mBottomLayout.setVisibility(8);
    float f = getRatio();
    this.mConfirmLayout = new LinearLayout(this);
    this.mConfirmLayout.setOrientation(1);
    this.mConfirmLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
    Object localObject1;
    Object localObject3;
    int k;
    int i;
    label218:
    label278:
    Object localObject2;
    label287:
    int m;
    label395:
    label433:
    label442:
    label585:
    label722:
    label733:
    double d;
    if (paramBoolean)
    {
      localObject1 = "back_confirm.png";
      this.mConfirmLayout.setBackgroundDrawable(readImageInAssets((String)localObject1, 0));
      this.mConfirmLayout.setGravity(17);
      this.mParentLayout.addView(this.mConfirmLayout);
      localObject3 = new LinearLayout(this);
      ((LinearLayout)localObject3).setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
      this.mConfirmLayout.addView((View)localObject3);
      Object localObject4 = new Button(this);
      ((Button)localObject4).setOnClickListener(this);
      k = (int)(140.0F * this.mScaleY);
      j = (int)(50.0F * this.mScaleY);
      if (!isPad())
      {
        k = (int)(100.0F * this.mScaleY);
        j = (int)(15.0F * this.mScaleY);
      }
      if (!isPad()) {
        break label985;
      }
      i = 70;
      localObject1 = new LinearLayout.LayoutParams(-2, -2);
      ((LinearLayout.LayoutParams)localObject1).setMargins(20, k, 0, j);
      ((Button)localObject4).setLayoutParams((ViewGroup.LayoutParams)localObject1);
      ((Button)localObject4).setTag(Integer.valueOf(7));
      StateListDrawable localStateListDrawable = new StateListDrawable();
      if (!paramBoolean) {
        break label992;
      }
      localObject1 = "play_popup_bt1.png";
      if (!paramBoolean) {
        break label1000;
      }
      localObject2 = "play_popup_bt1_on.png";
      localObject2 = readImageInAssets((String)localObject2, 0);
      localStateListDrawable.addState(new int[] { 16842919 }, (Drawable)localObject2);
      localObject1 = readImageInAssets((String)localObject1, 0);
      localStateListDrawable.addState(new int[0], (Drawable)localObject1);
      ((Button)localObject4).setBackgroundDrawable(localStateListDrawable);
      ((LinearLayout)localObject3).addView((View)localObject4);
      localObject4 = new Button(this);
      ((Button)localObject4).setOnClickListener(this);
      ((Button)localObject4).setTag(Integer.valueOf(2));
      localObject1 = new LinearLayout.LayoutParams(-2, -2);
      if (!isPad()) {
        break label1008;
      }
      m = 200;
      ((LinearLayout.LayoutParams)localObject1).setMargins(30, k, m, j);
      ((Button)localObject4).setLayoutParams((ViewGroup.LayoutParams)localObject1);
      localStateListDrawable = new StateListDrawable();
      if (!paramBoolean) {
        break label1015;
      }
      localObject1 = "play_popup_bt2.png";
      if (!paramBoolean) {
        break label1023;
      }
      localObject2 = "play_popup_bt2_on.png";
      localObject2 = readImageInAssets((String)localObject2, 0);
      localStateListDrawable.addState(new int[] { 16842919 }, (Drawable)localObject2);
      localObject1 = readImageInAssets((String)localObject1, 0);
      localStateListDrawable.addState(new int[0], (Drawable)localObject1);
      ((Button)localObject4).setBackgroundDrawable(localStateListDrawable);
      ((LinearLayout)localObject3).addView((View)localObject4);
      localObject1 = new LinearLayout(this);
      ((LinearLayout)localObject1).setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
      this.mConfirmLayout.addView((View)localObject1);
      localObject2 = new Button(this);
      ((Button)localObject2).setOnClickListener(this);
      ((Button)localObject2).setTag(Integer.valueOf(9));
      localObject3 = new LinearLayout.LayoutParams(-2, -2);
      if (!isPad()) {
        break label1031;
      }
      j = 20;
      ((LinearLayout.LayoutParams)localObject3).setMargins(20, j, 0, 0);
      ((Button)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject3);
      localObject3 = new StateListDrawable();
      localObject4 = readImageInAssets("share_FB.png", 0);
      ((StateListDrawable)localObject3).addState(new int[] { 16842919 }, (Drawable)localObject4);
      localObject4 = readImageInAssets("share_FB.png", 0);
      ((StateListDrawable)localObject3).addState(new int[0], (Drawable)localObject4);
      ((Button)localObject2).setBackgroundDrawable((Drawable)localObject3);
      ((LinearLayout)localObject1).addView((View)localObject2);
      localObject2 = new Button(this);
      ((Button)localObject2).setOnClickListener(this);
      ((Button)localObject2).setTag(Integer.valueOf(10));
      localObject3 = new LinearLayout.LayoutParams(-2, -2);
      if (!isPad()) {
        break label1038;
      }
      j = 30;
      if (!isPad()) {
        break label1045;
      }
      k = 20;
      ((LinearLayout.LayoutParams)localObject3).setMargins(j, k, i, 0);
      ((Button)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject3);
      localObject3 = new StateListDrawable();
      localObject4 = readImageInAssets("share_TW.png", 0);
      ((StateListDrawable)localObject3).addState(new int[] { 16842919 }, (Drawable)localObject4);
      localObject4 = readImageInAssets("share_TW.png", 0);
      ((StateListDrawable)localObject3).addState(new int[0], (Drawable)localObject4);
      ((Button)localObject2).setBackgroundDrawable((Drawable)localObject3);
      ((LinearLayout)localObject1).addView((View)localObject2);
      d = Math.sqrt((Math.pow(this.mScaleX, 2.0D) + Math.pow(this.mScaleY, 2.0D)) / 2.0D);
      localObject2 = new TextView(this);
      ((TextView)localObject2).setText("Share the video\nto your friends!");
      ((TextView)localObject2).setTypeface(Typeface.createFromAsset(getAssets(), "fonts/NIS_R10.ttf"));
      ((TextView)localObject2).setTextColor(-16777216);
      if (!isPad()) {
        break label1052;
      }
      d = 28.0D * d * f;
      label910:
      ((TextView)localObject2).setTextSize((float)d);
      localObject3 = new LinearLayout.LayoutParams(-2, -2);
      if (!isPad()) {
        break label1065;
      }
      i = 0;
      label940:
      if (!isPad()) {
        break label1072;
      }
    }
    label985:
    label992:
    label1000:
    label1008:
    label1015:
    label1023:
    label1031:
    label1038:
    label1045:
    label1052:
    label1065:
    label1072:
    for (int j = 30;; j = 10)
    {
      ((LinearLayout.LayoutParams)localObject3).setMargins(0, 0, i, j);
      ((TextView)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject3);
      ((LinearLayout)localObject1).addView((View)localObject2);
      return;
      localObject1 = "play_popup2_back.png";
      break;
      i = 35;
      break label218;
      localObject1 = "play_btn_popup2_end.png";
      break label278;
      localObject2 = "play_btn_popup2_end_sel.png";
      break label287;
      m = 100;
      break label395;
      localObject1 = "play_btn_popup2_continue.png";
      break label433;
      localObject2 = "play_btn_popup2_continue_sel.png";
      break label442;
      j = 12;
      break label585;
      j = 12;
      break label722;
      k = 10;
      break label733;
      d = 17.0D * d * f;
      break label910;
      i = 10;
      break label940;
    }
  }
  
  private void showLoad()
  {
    this.mLoadingDialog = new CustomProgressDialog(instance);
    this.mLoadingDialog.show();
  }
  
  public static void showLoading()
  {
    instance.runOnUiThread(new Runnable()
    {
      public void run()
      {
        ForiiEhon.instance.showLoad();
      }
    });
  }
  
  private void showNextBookInfo(String paramString)
  {
    if (this.mNextInfoLayout != null) {
      return;
    }
    this.mHandler.removeCallbacks(this.mProgressRunnable);
    this.mNextInfoLayout = new LinearLayout(this);
    this.mNextInfoLayout.setOnClickListener(this);
    this.mNextInfoLayout.setTag(Integer.valueOf(4));
    Object localObject1 = new RelativeLayout.LayoutParams(-1, -1);
    ((RelativeLayout.LayoutParams)localObject1).addRule(13);
    this.mNextInfoLayout.setLayoutParams((ViewGroup.LayoutParams)localObject1);
    this.mNextInfoLayout.setGravity(17);
    this.mNextInfoLayout.setOrientation(1);
    this.mParentLayout.addView(this.mNextInfoLayout);
    localObject1 = new LinearLayout(this);
    ((LinearLayout)localObject1).setGravity(17);
    ((LinearLayout)localObject1).setOrientation(1);
    ((LinearLayout)localObject1).setBackgroundDrawable(readImageInAssets("play_popup_back.png", 0));
    ((LinearLayout)localObject1).setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
    this.mNextInfoLayout.addView((View)localObject1);
    Object localObject2 = new ImageView(this);
    Object localObject3 = new LinearLayout.LayoutParams(-2, -2);
    ((LinearLayout.LayoutParams)localObject3).setMargins(0, 0, 0, 10);
    ((ImageView)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject3);
    ((ImageView)localObject2).setImageBitmap(readImage(paramString));
    ((LinearLayout)localObject1).addView((View)localObject2);
    paramString = new ImageView(this);
    localObject2 = new LinearLayout.LayoutParams(-2, -2);
    ((LinearLayout.LayoutParams)localObject2).setMargins(0, 5, 0, 5);
    paramString.setLayoutParams((ViewGroup.LayoutParams)localObject2);
    paramString.setImageDrawable(readImageInAssets("play_next_text.png", 0));
    ((LinearLayout)localObject1).addView(paramString);
    paramString = new Button(this);
    paramString.setOnClickListener(this);
    paramString.setTag(Integer.valueOf(2));
    localObject2 = new LinearLayout.LayoutParams(-2, -2);
    ((LinearLayout.LayoutParams)localObject2).setMargins(0, 15, 0, 0);
    paramString.setLayoutParams((ViewGroup.LayoutParams)localObject2);
    localObject2 = new StateListDrawable();
    localObject3 = readImageInAssets("video_btn_close_sel.png", 0);
    ((StateListDrawable)localObject2).addState(new int[] { 16842919 }, (Drawable)localObject3);
    localObject3 = readImageInAssets("video_btn_close.png", 0);
    ((StateListDrawable)localObject2).addState(new int[0], (Drawable)localObject3);
    paramString.setBackgroundDrawable((Drawable)localObject2);
    ((LinearLayout)localObject1).addView(paramString);
  }
  
  private void showParentalGate()
  {
    this.mConfirmLayout.setVisibility(8);
    float f2 = getRatio();
    this.mGateFrameLayout = new LinearLayout(this);
    this.mGateFrameLayout.setOrientation(1);
    this.mGateFrameLayout.setClipChildren(false);
    this.mGateFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
    this.mGateFrameLayout.setBackgroundDrawable(readImageInAssets("prntgate_bg.png", 2));
    this.mGateFrameLayout.setGravity(17);
    this.mGateTmpLayout.addView(this.mGateFrameLayout);
    Object localObject1 = new Button(this);
    ((Button)localObject1).setOnClickListener(this);
    Object localObject2 = new LinearLayout.LayoutParams(-2, -2);
    float f1;
    int i;
    label157:
    double d2;
    double d1;
    label294:
    label331:
    label353:
    Object localObject3;
    if (isPad())
    {
      f1 = 650.0F * this.mScaleX;
      j = (int)f1;
      if (!isPad()) {
        break label629;
      }
      i = 0;
      ((LinearLayout.LayoutParams)localObject2).setMargins(j, i, 0, 0);
      ((Button)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
      ((Button)localObject1).setTag(Integer.valueOf(11));
      ((Button)localObject1).setBackgroundDrawable(readImageInAssets("prntgate_closebtn.png", 2));
      this.mGateFrameLayout.addView((View)localObject1);
      d2 = Math.sqrt((Math.pow(this.mScaleX, 2.0D) + Math.pow(this.mScaleY, 2.0D)) / 2.0D);
      localObject1 = new TextView(this);
      ((TextView)localObject1).setText("Please enter this number.");
      ((TextView)localObject1).setTypeface(Typeface.createFromAsset(getAssets(), "fonts/NIS_R10.ttf"));
      ((TextView)localObject1).setTextColor(-1);
      if (!isPad()) {
        break label636;
      }
      d1 = 38.0D * d2 * f2;
      ((TextView)localObject1).setTextSize((float)d1);
      localObject2 = new LinearLayout.LayoutParams(-2, -2);
      if (!isPad()) {
        break label649;
      }
      f1 = -40.0F * this.mScaleY;
      i = (int)f1;
      if (!isPad()) {
        break label662;
      }
      f1 = 10.0F * this.mScaleY;
      ((LinearLayout.LayoutParams)localObject2).setMargins(0, i, 0, (int)f1);
      ((TextView)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
      this.mGateFrameLayout.addView((View)localObject1);
      localObject2 = new LinearLayout(this);
      ((LinearLayout)localObject2).setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
      ((LinearLayout)localObject2).setGravity(17);
      this.mGateFrameLayout.addView((View)localObject2);
      localObject1 = new Random();
      i = 0;
      if (i < 4) {
        break label675;
      }
      localObject2 = new LinearLayout(this);
      localObject3 = new LinearLayout.LayoutParams(-2, -2);
      if (!isPad()) {
        break label906;
      }
      f1 = 25.0F * this.mScaleY;
      label481:
      i = (int)f1;
      if (!isPad()) {
        break label919;
      }
      f1 = 25.0F * this.mScaleY;
      label503:
      ((LinearLayout.LayoutParams)localObject3).setMargins(0, i, 0, (int)f1);
      ((LinearLayout)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject3);
      ((LinearLayout)localObject2).setGravity(17);
      ((LinearLayout)localObject2).setBackgroundDrawable(readImageInAssets("prntgate_input.png", 2));
      this.mGateFrameLayout.addView((View)localObject2);
      i = 0;
      if (i < 4) {
        break label932;
      }
      localObject1 = new LinearLayout.LayoutParams(-2, -2);
      if (!isPad()) {
        break label1180;
      }
      i = 20;
      label584:
      if (!isPad()) {
        break label1187;
      }
    }
    label629:
    label636:
    label649:
    label662:
    label675:
    label709:
    label893:
    label906:
    label919:
    label932:
    label966:
    label1167:
    label1180:
    label1187:
    for (int j = 20;; j = 10)
    {
      ((LinearLayout.LayoutParams)localObject1).setMargins(i, 0, j, 0);
      i = 0;
      if (i < 2) {
        break label1194;
      }
      return;
      f1 = 320.0F * this.mScaleX;
      break;
      i = -40;
      break label157;
      d1 = 20.0D * d2 * f2;
      break label294;
      f1 = 10.0F * this.mScaleY;
      break label331;
      f1 = 10.0F * this.mScaleY;
      break label353;
      localObject3 = new LinearLayout(this);
      ((LinearLayout)localObject3).setGravity(17);
      Object localObject4;
      if (isPad())
      {
        f1 = 150.0F * this.mScaleX;
        localObject4 = new LinearLayout.LayoutParams((int)f1, -2);
        ((LinearLayout.LayoutParams)localObject4).setMargins(10, 0, 10, 0);
        ((LinearLayout)localObject3).setLayoutParams((ViewGroup.LayoutParams)localObject4);
        ((LinearLayout)localObject2).addView((View)localObject3);
        this.mBaseTextView[i] = new TextView(this);
        this.mBaseTextView[i].setText(((Random)localObject1).nextInt(10));
        this.mBaseTextView[i].setTypeface(Typeface.createFromAsset(getAssets(), "fonts/NIS_R10.ttf"));
        this.mBaseTextView[i].setTextColor(65280);
        localObject4 = this.mBaseTextView[i];
        if (!isPad()) {
          break label893;
        }
      }
      for (d1 = 60.0D * d2 * f2;; d1 = 30.0D * d2 * f2)
      {
        ((TextView)localObject4).setTextSize((float)d1);
        ((LinearLayout)localObject3).addView(this.mBaseTextView[i]);
        i += 1;
        break;
        f1 = 57.0F * this.mScaleX;
        break label709;
      }
      f1 = 14.0F * this.mScaleY;
      break label481;
      f1 = 18.0F * this.mScaleY;
      break label503;
      localObject3 = new LinearLayout(this);
      ((LinearLayout)localObject3).setGravity(17);
      if (isPad())
      {
        f1 = 150.0F * this.mScaleX;
        localObject4 = new LinearLayout.LayoutParams((int)f1, -2);
        ((LinearLayout.LayoutParams)localObject4).setMargins(10, 0, 10, 0);
        ((LinearLayout)localObject3).setLayoutParams((ViewGroup.LayoutParams)localObject4);
        ((LinearLayout)localObject2).addView((View)localObject3);
        this.mInputTextView[i] = new TextView(this);
        this.mInputTextView[i].setText(((Random)localObject1).nextInt(10));
        this.mInputTextView[i].setTypeface(Typeface.createFromAsset(getAssets(), "fonts/NIS_R10.ttf"));
        this.mInputTextView[i].setTextColor(Color.rgb(0, 144, 81));
        localObject4 = this.mInputTextView[i];
        if (!isPad()) {
          break label1167;
        }
      }
      for (d1 = 60.0D * d2 * f2;; d1 = 30.0D * d2 * f2)
      {
        ((TextView)localObject4).setTextSize((float)d1);
        ((LinearLayout)localObject3).addView(this.mInputTextView[i]);
        this.mInputTextView[i].setVisibility(4);
        i += 1;
        break;
        f1 = 57.0F * this.mScaleX;
        break label966;
      }
      i = 10;
      break label584;
    }
    label1194:
    localObject2 = new LinearLayout(this);
    ((LinearLayout)localObject2).setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
    ((LinearLayout)localObject2).setGravity(17);
    this.mGateFrameLayout.addView((View)localObject2);
    j = 0;
    for (;;)
    {
      if (j >= 5)
      {
        i += 1;
        break;
      }
      int k = j + i * 5;
      localObject3 = new Button(this);
      ((Button)localObject3).setOnClickListener(this);
      ((Button)localObject3).setLayoutParams((ViewGroup.LayoutParams)localObject1);
      ((Button)localObject3).setTag(Integer.valueOf(k + 1000));
      ((Button)localObject3).setBackgroundDrawable(readImageInAssets(String.format("prntgate_key%02d.png", new Object[] { Integer.valueOf(k) }), 2));
      ((LinearLayout)localObject2).addView((View)localObject3);
      j += 1;
    }
  }
  
  private void showStartAnimation()
  {
    this.mAnimationDrawable = new AnimationDrawable();
    Object localObject1 = getWindowManager().getDefaultDisplay();
    Object localObject2 = new Point();
    ((Display)localObject1).getSize((Point)localObject2);
    int j = Math.min(((Point)localObject2).x, ((Point)localObject2).y);
    int k = Math.max(((Point)localObject2).x, ((Point)localObject2).y);
    int i = 0;
    for (;;)
    {
      if (i >= 17)
      {
        this.mImageFolder = null;
        this.mLoadingParentLayout = new RelativeLayout(this);
        this.mLoadingParentLayout.setBackgroundColor(-1);
        this.mLoadingParentLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        ((ViewGroup)getWindow().getDecorView()).addView(this.mLoadingParentLayout);
        localObject1 = new LinearLayout(this);
        localObject2 = new RelativeLayout.LayoutParams(-1, -1);
        ((RelativeLayout.LayoutParams)localObject2).addRule(15);
        ((LinearLayout)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
        this.mLoadingParentLayout.addView((View)localObject1);
        this.mAnimationLayout = new LinearLayout(this);
        localObject2 = new LinearLayout.LayoutParams(j / 3, j / 3);
        ((LinearLayout.LayoutParams)localObject2).setMargins(k / 2 - j / 6, j / 3, k / 2 - j / 6, j / 3);
        this.mAnimationLayout.setLayoutParams((ViewGroup.LayoutParams)localObject2);
        ((LinearLayout)localObject1).addView(this.mAnimationLayout);
        this.mAnimationLayout.setBackgroundDrawable(this.mAnimationDrawable);
        this.mAnimationLayout.post(new Runnable()
        {
          public void run()
          {
            ForiiEhon.this.mAnimationDrawable.setOneShot(false);
            ForiiEhon.this.mAnimationDrawable.start();
          }
        });
        return;
      }
      this.mAnimationDrawable.addFrame(readImageInAssets(String.format("loading_clock%02d.png", new Object[] { Integer.valueOf(i + 1) }), 1), 100);
      i += 1;
    }
  }
  
  public static void showStartLoadingAnimation()
  {
    instance.runOnUiThread(new Runnable()
    {
      public void run()
      {
        ForiiEhon.instance.showStartAnimation();
      }
    });
  }
  
  private void startPlayVideo(String paramString, boolean paramBoolean)
  {
    releaseView();
    initView(paramBoolean);
    loadSounds();
    if (paramBoolean) {
      try
      {
        if (paramString.contains("data/data/"))
        {
          new FileInputStream(paramString);
          this.mVideoView.startPlay(paramString);
          return;
        }
        this.mVideoView.startPlay(paramString);
        return;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return;
      }
    }
    String str = getCacheFileRoot();
    File localFile = new File(str + File.separator + ".nomedia");
    if (!localFile.exists()) {
      localFile.createNewFile();
    }
    if (new File(str).exists())
    {
      moveOpenVideo();
      this.mVideoView.startPlay(String.format("%s/%s", new Object[] { getCacheFileRoot(), this.mStartVideoName }));
      return;
    }
    this.mVideoView.startPlay(getAssets().openFd(paramString));
  }
  
  public boolean isSelectStopPlay()
  {
    return this.mIsStoped;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (!GoogleBilling.mHelper.handleActivityResult(paramInt1, paramInt2, paramIntent)) {
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
    }
    for (;;)
    {
      if ((paramInt1 == 1) && (paramInt2 != -1))
      {
        paramIntent = YouTubeStandalonePlayer.getReturnedInitializationResult(paramIntent);
        if (!paramIntent.isUserRecoverableError()) {
          break;
        }
        paramIntent.getErrorDialog(this, 0).show();
      }
      return;
      Log.d("GoogleBilling", "onActivityResult handled by IABUtil.");
    }
    Toast.makeText(this, "読み込みエラーが発生しました。", 1).show();
  }
  
  public void onClick(View paramView)
  {
    int i = ((Integer)paramView.getTag()).intValue();
    if (i >= 1000) {
      onClickParentalgatelNum(i - 1000);
    }
    label171:
    do
    {
      return;
      switch (i)
      {
      default: 
        return;
      case 1: 
        if (this.mIsStoped)
        {
          this.mPlayBtn.setBackgroundDrawable(mPauseDrawable);
          this.mVideoView.resumePlay();
          this.mHandler.post(this.mProgressRunnable);
          if (!this.mIsStoped) {
            break label171;
          }
        }
        for (boolean bool = false;; bool = true)
        {
          this.mIsStoped = bool;
          return;
          this.mPlayBtn.setBackgroundDrawable(mPlayDrawable);
          this.mVideoView.pausePlay();
          this.mHandler.removeCallbacks(this.mProgressRunnable);
          break;
        }
      case 3: 
        this.mVideoView.pausePlay();
        this.mIsStoped = true;
        showConfirmView(true);
        this.mHandler.removeCallbacks(this.mProgressRunnable);
        return;
      case 7: 
        playSound(this.mCloseSoundID);
        sendAnalyticsEvent("videoPlayer", "interrupted", this.mCurrentBookId, this.mVideoView.getPercentage());
        backPrevPage();
        return;
      case 2: 
        playSound(this.mButtonSoundID);
        this.mVideoView.resumePlay();
        this.mIsStoped = false;
        this.mPlayBtn.setBackgroundDrawable(mPauseDrawable);
        dismissConfirmView();
        dismissNextBookInfoView();
        this.mHandler.post(this.mProgressRunnable);
        return;
      }
    } while ((this.mHeaderLayout == null) || (this.mConfirmLayout != null));
    if (this.mHeaderLayout.getVisibility() == 8)
    {
      this.mHeaderLayout.setVisibility(0);
      this.mBottomLayout.setVisibility(0);
      return;
    }
    this.mHeaderLayout.setVisibility(8);
    this.mBottomLayout.setVisibility(8);
    return;
    checkNextBookInfo(-1);
    return;
    checkNextBookInfo(1);
    return;
    changeRepeatType();
    return;
    mShareType = 2;
    showParentalGate();
    return;
    mShareType = 1;
    showParentalGate();
    return;
    onCloseParentalGate();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (Build.VERSION.SDK_INT >= 9) {
      setRequestedOrientation(6);
    }
    me = this;
    this.mBaseTextView = new TextView[4];
    this.mInputTextView = new TextView[4];
    getWindow().setFlags(128, 128);
    GoogleBilling.setup(this, mGLView);
    instance = this;
    analytics = GoogleAnalytics.getInstance(this);
    analytics.setLocalDispatchPeriod(1800);
    this.tracker = analytics.newTracker("UA-41455020-4");
    this.tracker.enableExceptionReporting(true);
    this.tracker.enableAdvertisingIdCollection(true);
    this.tracker.enableAutoActivityTracking(true);
    this.tracker.enableAdvertisingIdCollection(true);
    GrowthbeatJNI.setContext(this);
    GrowthLinkJNI.setContext(this);
    GrowthLinkJNI.handleOpenUrl(getIntent().getData());
    FacebookSdk.sdkInitialize(getApplicationContext());
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    instance = null;
  }
  
  public void onDestroyed(VideoView paramVideoView)
  {
    System.out.println("動画ビューを解放");
    if (this.mHandler != null) {
      this.mHandler.removeCallbacks(this.mProgressRunnable);
    }
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    setIntent(paramIntent);
    setIntent(new Intent());
  }
  
  protected void onPause()
  {
    super.onPause();
    GoogleAnalytics.getInstance(this).reportActivityStop(this);
  }
  
  public void onPausePlay(VideoView paramVideoView)
  {
    System.out.println("動画再生が一時停止");
  }
  
  public void onPlayComplete(VideoView paramVideoView)
  {
    System.out.println("onVideoFinish");
    if (this.mHeaderLayout != null) {
      sendAnalyticsEvent("videoPlayer", "finished", this.mCurrentBookId, 0L);
    }
    if ((this.mHeaderLayout == null) || (this.mVideoView == null))
    {
      backPrevPage();
      return;
    }
    switch (this.mRepeatType)
    {
    default: 
      return;
    case ALL: 
      backPrevPage();
      return;
    case OFF: 
      this.mVideoView.startPlay();
      return;
    }
    checkNextBookInfo(1);
  }
  
  public void onPlayError(VideoView paramVideoView)
  {
    System.out.println("動画が再生できない");
  }
  
  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean) {}
  
  protected void onResume()
  {
    super.onResume();
    getWindow().setSoftInputMode(3);
    try
    {
      AppEventsLogger.activateApp(this, "629292810451188");
      if ((this.mVideoView != null) && (!this.mIsStoped) && (this.mConfirmLayout == null) && (this.mNextInfoLayout == null))
      {
        this.mVideoView.resumePlay();
        System.out.println(String.format("onResume position:%d", new Object[] { Integer.valueOf(this.mVideoView.getCurrentPosition()) }));
      }
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public void onResumePlay(VideoView paramVideoView)
  {
    if (this.mHandler != null) {
      this.mHandler.post(this.mProgressRunnable);
    }
    System.out.println("動画再生が継続された");
  }
  
  protected void onStart()
  {
    super.onStart();
    GoogleAnalytics.getInstance(this).reportActivityStart(this);
  }
  
  public void onStartPlay(VideoView paramVideoView)
  {
    if (this.mHeaderLayout != null)
    {
      sendAnalyticsEvent("videoPlayer", "started", this.mCurrentBookId, 0L);
      this.mPlayBtn.setBackgroundDrawable(mPauseDrawable);
      int i = this.mVideoView.getDuration();
      paramVideoView = getVideoDurationText(i);
      this.mProgressSekBar.setMax(i);
      this.mTimeTxt.setText(paramVideoView);
      if (this.mHandler == null) {
        this.mHandler = new Handler();
      }
      if (this.mProgressRunnable == null) {
        this.mProgressRunnable = new UpdateVideoProgressRunnable(null);
      }
      this.mHandler.post(this.mProgressRunnable);
      if (this.mAutoHideProgressRunnable == null) {
        this.mAutoHideProgressRunnable = new AutoHideProgressRunnable(null);
      }
      this.mHandler.postDelayed(this.mAutoHideProgressRunnable, 3000L);
    }
    System.out.println("動画再生開始");
  }
  
  public void onStartTrackingTouch(SeekBar paramSeekBar)
  {
    System.out.println("start");
    if (!this.mIsStoped)
    {
      this.mVideoView.pausePlay();
      if (this.mHandler != null) {
        this.mHandler.removeCallbacks(this.mProgressRunnable);
      }
    }
  }
  
  protected void onStop()
  {
    super.onStop();
    GoogleAnalytics.getInstance(this).reportActivityStop(this);
  }
  
  public void onStopTrackingTouch(SeekBar paramSeekBar)
  {
    int i = paramSeekBar.getProgress();
    if (this.mVideoView.getDuration() == i) {
      onPlayComplete(null);
    }
    do
    {
      do
      {
        return;
        System.out.println(String.format("stop Progress:%d", new Object[] { Integer.valueOf(i) }));
        this.mTimeTxt.setText(getVideoDurationText(i));
        this.mProgressSekBar.setProgress(i);
        this.mVideoView.setCurrentPosition(i);
      } while (this.mIsStoped);
      this.mVideoView.resumePlay();
    } while (this.mHandler == null);
    this.mHandler.post(this.mProgressRunnable);
  }
  
  public void setAlert(String paramString1, String paramString2, String paramString3, String paramString4, final int paramInt)
  {
    if (paramString4.length() == 0)
    {
      new AlertDialog.Builder(instance).setTitle(paramString1).setMessage(paramString2).setPositiveButton(paramString3, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          ForiiEhon.alertCallback(0, paramInt);
        }
      }).show();
      return;
    }
    new AlertDialog.Builder(instance).setTitle(paramString1).setMessage(paramString2).setPositiveButton(paramString3, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        ForiiEhon.alertCallback(0, paramInt);
      }
    }).setNegativeButton(paramString4, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        ForiiEhon.alertCallback(1, paramInt);
      }
    }).show();
  }
  
  public void youtube(String paramString)
  {
    paramString = YouTubeStandalonePlayer.createVideoIntent(this, "AIzaSyC8-WmFaUVy9Bezgw07ik17u_USwm0QUyg", paramString, 0, true, true);
    if (paramString != null)
    {
      if (canResolveIntent(paramString)) {
        startActivityForResult(paramString, 1);
      }
    }
    else {
      return;
    }
    YouTubeInitializationResult.SERVICE_MISSING.getErrorDialog(this, 2).show();
  }
  
  private class AutoHideProgressRunnable
    implements Runnable
  {
    private AutoHideProgressRunnable() {}
    
    public void run()
    {
      System.out.println("Auto hide");
      ForiiEhon.this.mHeaderLayout.setVisibility(8);
      ForiiEhon.this.mBottomLayout.setVisibility(8);
      ForiiEhon.this.mHandler.removeCallbacks(this);
    }
  }
  
  public class CustomProgressDialog
    extends Dialog
  {
    public CustomProgressDialog(Context paramContext)
    {
      super(2131099676);
      setContentView(2130903043);
    }
    
    public void dissmis()
    {
      super.dismiss();
    }
  }
  
  private static final class PLAY_INDEX
  {
    static final int NEXT = 1;
    static final int UP = -1;
    
    private PLAY_INDEX() {}
  }
  
  private static enum REPEAT_TYPE
  {
    OFF,  ONE,  ALL;
  }
  
  private static class SHARE_TYPE
  {
    static final int FACEBOOK = 2;
    static final int NONE = 0;
    static final int TWITTER = 1;
    
    private SHARE_TYPE() {}
  }
  
  private class UpdateVideoProgressRunnable
    implements Runnable
  {
    private UpdateVideoProgressRunnable() {}
    
    public void run()
    {
      int i = ForiiEhon.this.mVideoView.getCurrentPosition();
      System.out.println(String.format("CurrentPosition:%d", new Object[] { Integer.valueOf(i) }));
      ForiiEhon.this.mTimeTxt.setText(ForiiEhon.this.getVideoDurationText(i));
      ForiiEhon.this.mProgressSekBar.setProgress(i);
      ForiiEhon.this.mHandler.postDelayed(this, 1000L);
    }
  }
  
  private static final class VIEW_TYPE
  {
    static final int BACK = 7;
    static final int BOOK = 3;
    static final int CLOSE = 11;
    static final int FACEBOOK = 9;
    static final int NEXT = 6;
    static final int PLAY = 1;
    static final int RESUME = 2;
    static final int TWITTER = 10;
    static final int TYPE = 8;
    static final int UP = 5;
    static final int VIDEO = 4;
    
    private VIEW_TYPE() {}
  }
}
