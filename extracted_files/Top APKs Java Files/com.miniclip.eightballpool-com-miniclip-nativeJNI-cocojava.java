package com.miniclip.nativeJNI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.opengl.GLES10;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StatFs;
import android.os.Vibrator;
import android.text.ClipboardManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Request;
import com.facebook.Request.Callback;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Session.NewPermissionsRequest;
import com.facebook.Session.OpenRequest;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionDefaultAudience;
import com.facebook.SessionLoginBehavior;
import com.facebook.SessionState;
import com.facebook.Settings;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.facebook.model.GraphObject;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.Builder;
import com.facebook.widget.WebDialog.OnCompleteListener;
import com.flurry.android.FlurryAgent;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;
import com.miniclip.NTP.NtpHandler;
import com.miniclip.Ping.PingHandler;
import com.miniclip.newsfeed.Newsfeed;
import com.miniclip.newsfeed.NewsfeedDialog;
import com.mopub.mobileads.MoPubConversionTracker;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubInterstitial.MoPubInterstitialListener;
import com.mopub.mobileads.MoPubView;
import com.mopub.mobileads.MoPubView.OnAdClickedListener;
import com.mopub.mobileads.MoPubView.OnAdFailedListener;
import com.mopub.mobileads.MoPubView.OnAdLoadedListener;
import com.tapjoy.TapjoyConnect;
import com.tapjoy.TapjoyFeaturedAppNotifier;
import com.tapjoy.TapjoyFeaturedAppObject;
import com.tapjoy.easyAppConnectOffers.EasyAppConnectOffers;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.json.JSONException;
import org.json.JSONObject;

public class cocojava
  extends EasyAppConnectOffers
  implements MoPubInterstitial.MoPubInterstitialListener, DialogInterface.OnClickListener, View.OnClickListener, MoPubView.OnAdLoadedListener, MoPubView.OnAdFailedListener, MoPubView.OnAdClickedListener
{
  public static int ALIGN_LEFT = 0;
  public static int ALIGN_RIGHT = 0;
  public static String FILENAME;
  private static int GAME_MENU_STATE = 0;
  private static final int HANDLER_CLOSE_DIALOG = 3;
  private static final int HANDLER_EXIT_APP = 5;
  private static final int HANDLER_HIDE_DIALOG = 2;
  private static final int HANDLER_OPEN_URL = 4;
  private static final int HANDLER_SHOW_DIALOG = 1;
  protected static cocoaccel accelerometer;
  private static boolean accelerometerEnabled;
  public static RelativeLayout adLayout;
  public static RelativeLayout adLayoutH;
  private static MoPubView adViewGame;
  private static MoPubView adViewMenu;
  private static cocomusic backgroundMusicPlayer;
  private static MoPubView bigAd;
  private static boolean inAppsRemoveAds;
  private static MoPubInterstitial interstitial;
  public static RelativeLayout layout;
  protected static boolean mADS_BLOCKED_NOW;
  private static Handler mAdDelayHandler;
  private static RelativeLayout mBigAdView;
  public static boolean mBlockNewsButton;
  private static RelativeLayout mCenterdAd;
  protected static boolean mConstantAd;
  protected static Context mContext;
  protected static Newsfeed mCurrentNewsfeed;
  protected static NewsfeedDialog mCurrentNewsfeedDialog;
  protected static boolean mCustomInApp;
  protected static boolean mCustomLeaderboard;
  public static float mDensity;
  public static String mDeviceID;
  public static EditText mEditText;
  private static boolean mFB_AuthenticationRequested;
  public static Session mFB_Session;
  public static boolean mFORCE_FB_WEB;
  public static String mFacebookAPP_ID;
  private static Handler mFacebookHandler;
  protected static TapjoyFeaturedAppObject mFeaturedApObject;
  protected static interstitialMopubView mFullscreenInterstitial;
  public static ClearGLSurfaceView mGLView;
  private static int mGameState;
  protected static boolean mHAS_RETINA;
  private static Handler mHandler;
  protected static boolean mHasSecondIntro;
  private static boolean mHasWindowFocus;
  public static int mHeight;
  private static Runnable mHideAdsRun;
  private static horizontalBanner mHorizontalBanner;
  public static boolean mHorizontalBannerDisplayed;
  protected static float mINGAME_HEIGHT;
  public static boolean mINGAME_LANDSCAPE;
  protected static boolean mINGAME_SCALE;
  protected static float mINGAME_WIDTH;
  public static int mInAppCallback;
  public static int mInAppResponce;
  public static int mInAppSelf;
  private static infoTransmitter mInfoTransmitter;
  private static RelativeLayout mInitView;
  private static interstitialBanner mInterstitialBanner;
  public static boolean mKEYBOARD_CUSTOM_BACKGROUND;
  public static boolean mKEYBOARD_FULLSCREEN;
  public static boolean mKEYBOARD_INPUT_HIDE;
  public static boolean mKEYBOARD_INPUT_SINGLE_LINE;
  public static boolean mKEYBOARD_OVERRIDE_VISIBILITY;
  private static Handler mKeyboardHandler;
  protected static int mLastBigAdType;
  protected static boolean mMinimumResolutionSD;
  private static MopubInterstitial mMopubInterstitial;
  private static MopubView mMopubView;
  private static NtpHandler mNtpHandler;
  protected static int mNumCrashes;
  protected static int mNumUpSellsThisSession;
  private static PingHandler mPingHandler;
  public static SharedPreferences mPrefs;
  public static String mProductId;
  private static Button mRemove1;
  public static ImageView mRemoveAdsButton;
  public static boolean mRemoveAdsButtonHidden;
  public static RelativeLayout mRemoveAdsButtonLayout;
  public static ClearRenderer mRenderer;
  private static boolean mResumeOnFocus;
  private static rotatedBannerImg mRotatedBanner;
  public static int mRotatedBannerDisplayed;
  protected static boolean mSERIOUS_ERROR_HAPPENED;
  public static boolean mSHOW_KEYBOARD_INPUT;
  public static boolean mSPINNING_ANIMATION;
  protected static boolean mSTORE_PENDING_PURCHASES;
  protected static boolean mSTORE_PENDING_PURCHASES_SIGNATURE;
  private static Button mSkip1;
  public static boolean mTEST_INAPPS;
  protected static boolean mUSE_ADS;
  protected static boolean mUSE_ADS_BIG;
  protected static boolean mUSE_ADS_HORIZONTAL;
  protected static float mUSE_ADS_HORIZONTAL_BANNER_OFFSET;
  protected static boolean mUSE_ADS_INTERSTITIAL_BANNER;
  protected static boolean mUSE_ADS_INTERSTITIAL_FULLSCREEN;
  protected static boolean mUSE_ADS_VERTICAL;
  protected static float mUSE_ADS_VERTICAL_BANNER_OFFSET;
  public static boolean mUSE_C2DM;
  protected static boolean mUSE_DEVICEID;
  public static boolean mUSE_FACEBOOK;
  protected static boolean mUSE_NEWSFEED;
  protected static boolean mUSE_PRESERVE_CONTEXT;
  public static boolean mUSE_REMOVE_ADS;
  protected static Runnable mUpdateRunable;
  protected static boolean mUpdateRunableCall;
  public static int mWidth;
  public static RelativeLayout sideBar1;
  public static RelativeLayout sideBar2;
  protected static cocosound soundPlayer;
  private static cocotexture texture;
  protected GoogleAnalyticsTracker googleTracker;
  private boolean isFirstRun = true;
  private HashMap<Integer, Dialog> mDialogs;
  private Handler mScreenFixHandler = new Handler();
  
  static
  {
    if (!cocojava.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      GAME_MENU_STATE = 0;
      ALIGN_LEFT = 0;
      ALIGN_RIGHT = 1;
      mRenderer = null;
      mUSE_PRESERVE_CONTEXT = true;
      mUSE_ADS = true;
      mINGAME_WIDTH = 480.0F;
      mINGAME_HEIGHT = 320.0F;
      mINGAME_SCALE = false;
      mMinimumResolutionSD = false;
      mINGAME_LANDSCAPE = true;
      mMopubInterstitial = null;
      mRemoveAdsButtonHidden = true;
      mUSE_REMOVE_ADS = false;
      mBlockNewsButton = false;
      mTEST_INAPPS = false;
      mSPINNING_ANIMATION = false;
      mFORCE_FB_WEB = false;
      mSHOW_KEYBOARD_INPUT = false;
      mKEYBOARD_INPUT_SINGLE_LINE = false;
      mKEYBOARD_INPUT_HIDE = true;
      mKEYBOARD_OVERRIDE_VISIBILITY = false;
      mKEYBOARD_FULLSCREEN = true;
      mKEYBOARD_CUSTOM_BACKGROUND = false;
      mMopubView = null;
      mHandler = null;
      mAdDelayHandler = new Handler();
      mKeyboardHandler = new Handler();
      mFacebookHandler = new Handler();
      inAppsRemoveAds = false;
      mLastBigAdType = 0;
      mConstantAd = true;
      mRotatedBannerDisplayed = 0;
      mNumUpSellsThisSession = 0;
      mDeviceID = "";
      mUSE_ADS_BIG = true;
      mUSE_ADS_VERTICAL = false;
      mUSE_ADS_INTERSTITIAL_BANNER = false;
      mUSE_ADS_INTERSTITIAL_FULLSCREEN = false;
      mADS_BLOCKED_NOW = false;
      mFullscreenInterstitial = null;
      mUSE_ADS_VERTICAL_BANNER_OFFSET = 0.0F;
      mUSE_ADS_HORIZONTAL = false;
      mUSE_ADS_HORIZONTAL_BANNER_OFFSET = 0.0F;
      mHorizontalBanner = null;
      mHorizontalBannerDisplayed = false;
      mEditText = null;
      FILENAME = "AndroidSSO_data";
      mFB_AuthenticationRequested = false;
      mResumeOnFocus = false;
      mHAS_RETINA = false;
      mCustomInApp = false;
      mUSE_NEWSFEED = true;
      mUSE_DEVICEID = true;
      mUSE_C2DM = false;
      mSTORE_PENDING_PURCHASES = false;
      mSTORE_PENDING_PURCHASES_SIGNATURE = false;
      mCurrentNewsfeedDialog = null;
      mCurrentNewsfeed = null;
      mHasSecondIntro = false;
      mSERIOUS_ERROR_HAPPENED = false;
      mCustomLeaderboard = false;
      mNumCrashes = 0;
      mUSE_FACEBOOK = false;
      mNtpHandler = new NtpHandler();
      mPingHandler = new PingHandler();
      mUpdateRunableCall = false;
      mHideAdsRun = new Runnable()
      {
        public void run() {}
      };
      accelerometerEnabled = false;
      return;
    }
  }
  
  public cocojava() {}
  
  public static int DeviceSupportsMultitouch()
  {
    if (mContext.getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch")) {
      return 1;
    }
    return 0;
  }
  
  public static void NF_dismissBoard()
  {
    if ((mUSE_NEWSFEED) && (mCurrentNewsfeedDialog != null))
    {
      ((Activity)mContext).runOnUiThread(new Runnable()
      {
        public void run()
        {
          cocojava.mCurrentNewsfeed.mIsVisible = false;
          cocojava.layout.removeView(cocojava.mCurrentNewsfeedDialog);
          cocojava.mCurrentNewsfeedDialog = null;
        }
      });
      mGLView.queueEvent(new Runnable()
      {
        public void run()
        {
          CocoJNI.NFcallBoardWillDisappear();
          CocoJNI.NFcallBoardDidDisappear();
          CocoJNI.MsetGamePause(0);
        }
      });
    }
  }
  
  public static void NF_setSandBox(int paramInt)
  {
    if ((mUSE_NEWSFEED) && (mCurrentNewsfeed != null)) {
      ((Activity)mContext).runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (this.val$sandBoxMode != 0)
          {
            cocojava.mCurrentNewsfeed.setSandBoxMode(true);
            return;
          }
          cocojava.mCurrentNewsfeed.setSandBoxMode(false);
        }
      });
    }
  }
  
  public static void NF_setShowBadge(int paramInt)
  {
    Log.i("cocojava", "NF_setShowBadge: deprecated method");
  }
  
  public static int NF_showBoard()
  {
    if ((!mUSE_NEWSFEED) || (mCurrentNewsfeed.messagesNum < 1)) {
      return 0;
    }
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        Log.i("cocojava", "Show NewsfeedDialog Non-urgent");
        cocojava.mCurrentNewsfeed.mIsVisible = true;
        if (cocojava.layout != null) {
          cocojava.layout.removeView(cocojava.mCurrentNewsfeedDialog);
        }
        cocojava.mCurrentNewsfeedDialog = new NewsfeedDialog(cocojava.mContext, cocojava.mCurrentNewsfeed, false);
        cocojava.layout.addView(cocojava.mCurrentNewsfeedDialog);
      }
    });
    mGLView.queueEvent(new Runnable()
    {
      public void run()
      {
        CocoJNI.NFcallBoardWillAppear();
        CocoJNI.NFcallBoardDidAppear();
        CocoJNI.MsetGamePause(1);
      }
    });
    return 1;
  }
  
  public static int NF_showUrgentBoard()
  {
    if ((!mUSE_NEWSFEED) || (mCurrentNewsfeed.messagesNumUrgent < 1)) {
      return 0;
    }
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (cocojava.mCurrentNewsfeed.messagesNumUrgent < 1) {
          cocojava.mCurrentNewsfeed.removeUrgentTimer();
        }
        Log.i("cocojava", "Show NewsfeedDialog Urgent");
        cocojava.mCurrentNewsfeed.mIsVisible = true;
        cocojava.mCurrentNewsfeed.removeUrgentTimer();
        if (cocojava.layout != null) {
          cocojava.layout.removeView(cocojava.mCurrentNewsfeedDialog);
        }
        cocojava.mCurrentNewsfeedDialog = new NewsfeedDialog(cocojava.mContext, cocojava.mCurrentNewsfeed, true);
        cocojava.layout.addView(cocojava.mCurrentNewsfeedDialog);
      }
    });
    mGLView.queueEvent(new Runnable()
    {
      public void run()
      {
        CocoJNI.NFcallBoardWillAppear();
        CocoJNI.NFcallBoardDidAppear();
        CocoJNI.MsetGamePause(1);
      }
    });
    return 1;
  }
  
  public static double NTP_JAVA_getOffsetFromServer(String paramString)
  {
    try
    {
      double d = mNtpHandler.getOffsetFromServer(paramString);
      return d;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return 0.0D;
  }
  
  public static void NTP_JAVA_getOffsetFromServerListAsync(String paramString, int paramInt1, int paramInt2)
  {
    mNtpHandler.getOffsetFromServerListAsync(paramString, paramInt1, paramInt2);
  }
  
  public static int Newsfeed_getNumber()
  {
    return 0;
  }
  
  public static void Newsfeed_showDialog() {}
  
  public static void PING_JAVA_simplePingAsync(String paramString, int paramInt1, int paramInt2)
  {
    mPingHandler.simplePingAsync(paramString, paramInt1, paramInt2);
  }
  
  public static int SharedPreferences_getInt(String paramString)
  {
    return ((Activity)mContext).getSharedPreferences("GAME_INFO", 0).getInt(paramString, 0);
  }
  
  public static String SharedPreferences_getString(String paramString)
  {
    return ((Activity)mContext).getSharedPreferences("GAME_INFO", 0).getString(paramString, "");
  }
  
  public static void SharedPreferences_setInt(String paramString, int paramInt)
  {
    SharedPreferences.Editor localEditor = ((Activity)mContext).getSharedPreferences("GAME_INFO", 0).edit();
    localEditor.putInt(paramString, paramInt);
    localEditor.commit();
  }
  
  public static void SharedPreferences_setString(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = ((Activity)mContext).getSharedPreferences("GAME_INFO", 0).edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }
  
  public static float availableMemoryOnDevice()
  {
    StatFs localStatFs = new StatFs(mContext.getFilesDir().getAbsolutePath());
    long l1 = localStatFs.getBlockSize();
    long l2 = localStatFs.getBlockCount();
    l2 = localStatFs.getAvailableBlocks();
    return (float)(localStatFs.getFreeBlocks() * l1 / 1048576L);
  }
  
  public static float availableMemoryOnSDcard()
  {
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
    long l1 = localStatFs.getBlockSize();
    long l2 = localStatFs.getBlockCount();
    l2 = localStatFs.getAvailableBlocks();
    return (float)(localStatFs.getFreeBlocks() * l1 / 1048576L);
  }
  
  public static void callInAppGetJar(String paramString1, final int paramInt1, final String paramString2, final int paramInt2, final int paramInt3, final int paramInt4)
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        ((cocojava)cocojava.mContext).callGetJar(this.val$itemID, paramInt1, paramString2, paramInt2, paramInt3, paramInt4);
      }
    });
  }
  
  public static int callInAppPurchase(String paramString, int paramInt1, int paramInt2)
  {
    return callInAppPurchase(paramString, paramInt1, paramInt2, false);
  }
  
  public static int callInAppPurchase(String paramString, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    Log.i("callInAppPurchase", paramString);
    inAppsRemoveAds = paramBoolean;
    mInAppCallback = paramInt1;
    mInAppResponce = -1;
    mInAppSelf = paramInt2;
    mProductId = paramString;
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (cocojava.mCustomInApp)
        {
          ((cocojava)cocojava.mContext).callInAppPurchaseCustom(this.val$itemID);
          return;
        }
        if (cocojava.mTEST_INAPPS)
        {
          ((InAppActivity)cocojava.mContext).requestPurchaseAct("android.test.purchased");
          return;
        }
        ((InAppActivity)cocojava.mContext).requestPurchaseAct(this.val$itemID);
      }
    });
    return 0;
  }
  
  public static int callInAppPurchaseManaged(String paramString, int paramInt1, int paramInt2)
  {
    return callInAppPurchaseManaged(paramString, paramInt1, paramInt2, false);
  }
  
  public static int callInAppPurchaseManaged(String paramString, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    Log.i("callInAppPurchaseManaged", paramString);
    inAppsRemoveAds = paramBoolean;
    mInAppCallback = paramInt1;
    mInAppResponce = -1;
    mInAppSelf = paramInt2;
    mProductId = paramString;
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (cocojava.mCustomInApp)
        {
          ((cocojava)cocojava.mContext).callInAppPurchaseManagedCustom(this.val$itemID);
          return;
        }
        if (cocojava.mTEST_INAPPS)
        {
          ((InAppActivity)cocojava.mContext).requestPurchaseActManaged("android.test.purchased");
          return;
        }
        ((InAppActivity)cocojava.mContext).requestPurchaseActManaged(this.val$itemID);
      }
    });
    return 0;
  }
  
  public static int callInAppPurchaseRemoveAds(String paramString, int paramInt1, int paramInt2)
  {
    return callInAppPurchase(paramString, paramInt1, paramInt2, true);
  }
  
  public static int callInAppPurchaseRemoveAdsManaged(String paramString, int paramInt1, int paramInt2)
  {
    return callInAppPurchaseManaged(paramString, paramInt1, paramInt2, true);
  }
  
  public static int callLastPendingPurchaseSignature(int paramInt)
  {
    Object localObject = mContext.getSharedPreferences("INAPP_PURCHASED_TEMP_SIGNATURE", 0);
    int i = ((SharedPreferences)localObject).getInt("amount", 0) - 1;
    if (i < 0) {
      return 0;
    }
    if (!((SharedPreferences)localObject).contains("pid" + String.valueOf(i))) {
      return 0;
    }
    final String str1 = ((SharedPreferences)localObject).getString("pid" + String.valueOf(i), "");
    final String str2 = ((SharedPreferences)localObject).getString("data" + String.valueOf(i), "");
    localObject = ((SharedPreferences)localObject).getString("sig" + String.valueOf(i), "");
    mGLView.queueEvent(new Runnable()
    {
      public void run()
      {
        CocoJNI.MsetInAppResponce(1, this.val$function, 0, str1, str2, this.val$sig);
      }
    });
    Log.i("cocojava", String.format("callLastPendingPurchaseSignature: %d pid: %s data: %s sig: %s", new Object[] { Integer.valueOf(i), str1, str2, localObject }));
    return i;
  }
  
  public static void closeMessageBox(int paramInt)
  {
    Message localMessage = new Message();
    localMessage.what = 3;
    localMessage.obj = new DialogMessage(paramInt);
    mHandler.sendMessage(localMessage);
  }
  
  private static Map<String, String> convertToMap(String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    HashMap localHashMap2 = null;
    HashMap localHashMap1 = localHashMap2;
    if (paramArrayOfString1 != null)
    {
      if (paramArrayOfString2 != null) {
        break label22;
      }
      localHashMap1 = localHashMap2;
    }
    label22:
    int j;
    do
    {
      return localHashMap1;
      j = paramArrayOfString1.length;
      localHashMap1 = localHashMap2;
    } while (j != paramArrayOfString2.length);
    localHashMap2 = new HashMap();
    int i = 0;
    for (;;)
    {
      localHashMap1 = localHashMap2;
      if (i >= j) {
        break;
      }
      localHashMap2.put(paramArrayOfString1[i], paramArrayOfString2[i]);
      i += 1;
    }
  }
  
  /* Error */
  public static void copyAsset(String paramString)
  {
    // Byte code:
    //   0: getstatic 609	com/miniclip/nativeJNI/cocojava:mContext	Landroid/content/Context;
    //   3: checkcast 626	android/app/Activity
    //   6: invokevirtual 942	android/app/Activity:getAssets	()Landroid/content/res/AssetManager;
    //   9: astore_1
    //   10: aload_1
    //   11: aload_0
    //   12: invokevirtual 948	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   15: astore_1
    //   16: new 950	java/io/FileOutputStream
    //   19: dup
    //   20: new 867	java/lang/StringBuilder
    //   23: dup
    //   24: invokespecial 868	java/lang/StringBuilder:<init>	()V
    //   27: ldc_w 952
    //   30: invokevirtual 874	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: aload_0
    //   34: invokevirtual 874	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: invokevirtual 883	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   40: invokespecial 953	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   43: astore_0
    //   44: aload_1
    //   45: aload_0
    //   46: invokestatic 957	com/miniclip/nativeJNI/cocojava:copyFile	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   49: aload_1
    //   50: invokevirtual 962	java/io/InputStream:close	()V
    //   53: aload_0
    //   54: invokevirtual 967	java/io/OutputStream:flush	()V
    //   57: aload_0
    //   58: invokevirtual 968	java/io/OutputStream:close	()V
    //   61: return
    //   62: astore_0
    //   63: ldc_w 970
    //   66: aload_0
    //   67: invokevirtual 973	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   70: invokestatic 976	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   73: pop
    //   74: return
    //   75: astore_0
    //   76: goto -13 -> 63
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	79	0	paramString	String
    //   9	41	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   10	44	62	java/lang/Exception
    //   44	61	75	java/lang/Exception
  }
  
  public static void copyFile(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        break;
      }
      paramOutputStream.write(arrayOfByte, 0, i);
    }
  }
  
  public static void custom_logEvent(String paramString1, final String paramString2)
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        ((cocojava)cocojava.mContext).logCustomEvent(this.val$eventId, paramString2);
      }
    });
  }
  
  public static void custom_showAchievements()
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (cocojava.mCustomLeaderboard) {
          ((cocojava)cocojava.mContext).showAchievementsCustom();
        }
      }
    });
  }
  
  public static void custom_showLeaderboard(String paramString)
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (cocojava.mCustomLeaderboard) {
          ((cocojava)cocojava.mContext).showLeaderboardCustom(this.val$leaderboardId);
        }
      }
    });
  }
  
  public static void custom_showLeaderboards()
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (cocojava.mCustomLeaderboard) {
          ((cocojava)cocojava.mContext).showLeaderboardsCustom();
        }
      }
    });
  }
  
  public static void custom_updateAchievement(String paramString, float paramFloat)
  {
    custom_updateAchievement(paramString, paramFloat, null);
  }
  
  public static void custom_updateAchievement(String paramString, final float paramFloat, final Object paramObject)
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (cocojava.mCustomLeaderboard) {
          ((cocojava)cocojava.mContext).updateAchievementCustom(this.val$achievementId, paramFloat, paramObject);
        }
      }
    });
  }
  
  public static void custom_updateScore(String paramString, int paramInt)
  {
    custom_updateScore(paramString, paramInt, null);
  }
  
  public static void custom_updateScore(String paramString, final int paramInt, final Object paramObject)
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (cocojava.mCustomLeaderboard) {
          ((cocojava)cocojava.mContext).updateScoreCustom(this.val$leaderboardId, paramInt, paramObject);
        }
      }
    });
  }
  
  public static void disableAccelerometer()
  {
    accelerometerEnabled = false;
    accelerometer.disable();
  }
  
  public static void disableAds() {}
  
  public static void disableNewsfeed() {}
  
  public static void displayIntro(String paramString)
  {
    mInitView.removeAllViews();
    ImageView localImageView = new ImageView(mContext);
    int i = mContext.getResources().getIdentifier(paramString, "drawable", mContext.getPackageName());
    localImageView.setImageDrawable(mContext.getResources().getDrawable(i));
    localImageView.setLayoutParams(new Gallery.LayoutParams(-1, -1));
    localImageView.setScaleType(ImageView.ScaleType.FIT_XY);
    mInitView.addView(localImageView);
  }
  
  public static void enableAccelerometer()
  {
    accelerometerEnabled = true;
    accelerometer.enable();
  }
  
  public static void enableAds() {}
  
  public static void enableAdsWithPosition(final float paramFloat1, final float paramFloat2, final float paramFloat3, final float paramFloat4, float paramFloat5, final String paramString)
  {
    if (!mUSE_ADS) {
      return;
    }
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        if ((cocojava.adLayout != null) && (cocojava.adLayout.getParent() != cocojava.adLayoutH)) {
          cocojava.adLayoutH.addView(cocojava.adLayout);
        }
        Object localObject1 = new RelativeLayout.LayoutParams((int)(320.0D * cocojava.mDensity), (int)(50.0D * cocojava.mDensity));
        float f3 = cocojava.mINGAME_WIDTH;
        float f1 = cocojava.mINGAME_HEIGHT;
        float f2;
        float f4;
        if (this.val$currentWidth == 0.0F) {
          if ((cocojava.mINGAME_LANDSCAPE) && (!cocojava.mINGAME_SCALE))
          {
            f2 = cocojava.mINGAME_HEIGHT / cocojava.mHeight * cocojava.mWidth;
            if (cocojava.mWidth <= 799)
            {
              f3 = f1;
              f4 = f2;
              if (cocojava.mHeight <= 799) {}
            }
            else
            {
              f3 = f1;
              f4 = f2;
              if (cocojava.mHAS_RETINA)
              {
                f4 = f2 * 1.0F;
                f3 = f1 * 1.0F;
              }
            }
            label140:
            i = (int)(cocojava.mUSE_ADS_VERTICAL_BANNER_OFFSET * cocojava.mDensity);
            int j = (int)(cocojava.mUSE_ADS_HORIZONTAL_BANNER_OFFSET * cocojava.mDensity);
            ((RelativeLayout.LayoutParams)localObject1).leftMargin = ((int)(paramFloat1 * (cocojava.mWidth / f4) - paramFloat3 * (320.0F * cocojava.mDensity)) + i);
            ((RelativeLayout.LayoutParams)localObject1).topMargin = ((int)(paramFloat2 * (cocojava.mHeight / f3) - (1.0F - paramFloat4) * (50.0F * cocojava.mDensity)) + j);
            if (paramString.compareTo(((cocojava)cocojava.mContext).getMoPubGameplayBannerId()) != 0) {
              break label434;
            }
            localMoPubView = cocojava.adViewGame;
            cocojava.hideAd(cocojava.adViewMenu);
          }
        }
        for (int i = 1;; i = 2)
        {
          if ((localMoPubView == null) || (localMoPubView.getParent() != cocojava.adLayout) || (!localMoPubView.isShown()) || (!localMoPubView.AdLoaded()) || (paramString.compareTo(localMoPubView.getAdUnitId()) != 0)) {
            break label451;
          }
          Object localObject2 = (RelativeLayout.LayoutParams)localMoPubView.getLayoutParams();
          if ((((RelativeLayout.LayoutParams)localObject2).leftMargin != ((RelativeLayout.LayoutParams)localObject1).leftMargin) || (((RelativeLayout.LayoutParams)localObject2).topMargin != ((RelativeLayout.LayoutParams)localObject1).topMargin))
          {
            localObject2 = new ScaleAnimation(1.0F, 0.0F, 1.0F, 1.0F, 160.0F * cocojava.mDensity, 0.0F);
            ((Animation)localObject2).setDuration(500L);
            ((Animation)localObject2).setAnimationListener(new Animation.AnimationListener()
            {
              public void onAnimationEnd(Animation paramAnonymous2Animation)
              {
                paramAnonymous2Animation = new ScaleAnimation(0.0F, 1.0F, 1.0F, 1.0F, 160.0F * cocojava.mDensity, 0.0F);
                paramAnonymous2Animation.setDuration(500L);
                localMoPubView.setLayoutParams(this.val$paramsFinal);
                localMoPubView.startAnimation(paramAnonymous2Animation);
              }
              
              public void onAnimationRepeat(Animation paramAnonymous2Animation) {}
              
              public void onAnimationStart(Animation paramAnonymous2Animation) {}
            });
            localMoPubView.startAnimation((Animation)localObject2);
          }
          return;
          f2 = f3;
          if (cocojava.mINGAME_SCALE) {
            break;
          }
          f1 = cocojava.mINGAME_WIDTH / cocojava.mWidth * cocojava.mHeight;
          f2 = f3;
          break;
          f4 = this.val$currentWidth;
          f3 = f1;
          break label140;
          label434:
          localMoPubView = cocojava.adViewMenu;
          cocojava.hideAd(cocojava.adViewGame);
        }
        label451:
        if (cocojava.mConstantAd)
        {
          if (localMoPubView == null) {
            if (i == 1)
            {
              cocojava.access$702(new MoPubView(cocojava.mContext));
              localMoPubView = cocojava.adViewGame;
              label487:
              localMoPubView.setOnAdLoadedListener((cocojava)cocojava.mContext);
              localMoPubView.setOnAdFailedListener((cocojava)cocojava.mContext);
              localMoPubView.setOnAdClickedListener((cocojava)cocojava.mContext);
              localMoPubView.setAdUnitId(paramString);
              localMoPubView.setVisibility(0);
              localMoPubView.loadAd();
            }
          }
          for (;;)
          {
            cocojava.adLayout.addView(localMoPubView, (ViewGroup.LayoutParams)localObject1);
            if (!localMoPubView.AdLoaded()) {
              break;
            }
            localObject1 = new ScaleAnimation(0.0F, 1.0F, 1.0F, 1.0F, 160.0F * cocojava.mDensity, 0.0F);
            ((Animation)localObject1).setDuration(500L);
            localMoPubView.setVisibility(1);
            localMoPubView.startAnimation((Animation)localObject1);
            return;
            cocojava.access$802(new MoPubView(cocojava.mContext));
            localMoPubView = cocojava.adViewMenu;
            break label487;
            cocojava.adLayout.removeView(localMoPubView);
          }
        }
        if (localMoPubView != null)
        {
          cocojava.adLayout.removeView(localMoPubView);
          localMoPubView.destroy();
        }
        if (i == 1) {
          cocojava.access$702(new MoPubView(cocojava.mContext));
        }
        for (final MoPubView localMoPubView = cocojava.adViewGame;; localMoPubView = cocojava.adViewMenu)
        {
          localMoPubView.setOnAdLoadedListener((cocojava)cocojava.mContext);
          localMoPubView.setOnAdFailedListener((cocojava)cocojava.mContext);
          localMoPubView.setOnAdClickedListener((cocojava)cocojava.mContext);
          localMoPubView.setAdUnitId(paramString);
          localMoPubView.setVisibility(0);
          localMoPubView.loadAd();
          break;
          cocojava.access$802(new MoPubView(cocojava.mContext));
        }
      }
    });
  }
  
  public static void enableAdsWithPositionForGameplay(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    enableAdsWithPosition(paramFloat1, paramFloat2, paramFloat3, paramFloat4, 0.0F, ((cocojava)mContext).getMoPubGameplayBannerId());
  }
  
  public static void enableAdsWithPositionForGameplayGivenWidth(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5)
  {
    enableAdsWithPosition(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, ((cocojava)mContext).getMoPubGameplayBannerId());
  }
  
  public static void enableAdsWithPositionForMenu(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    enableAdsWithPosition(paramFloat1, paramFloat2, paramFloat3, paramFloat4, 0.0F, ((cocojava)mContext).getMoPubMenuBannerId());
  }
  
  public static void enableAdsWithPositionForMenuGivenWidth(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5)
  {
    enableAdsWithPosition(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, ((cocojava)mContext).getMoPubMenuBannerId());
  }
  
  public static void enableNewsfeed() {}
  
  public static void end()
  {
    backgroundMusicPlayer.end();
    soundPlayer.end();
  }
  
  public static void endFlurryTimedEvent(String paramString)
  {
    FlurryAgent.endTimedEvent(paramString);
  }
  
  public static void exitApplication()
  {
    Message localMessage = new Message();
    localMessage.what = 5;
    mHandler.sendMessage(localMessage);
  }
  
  public static void faceBook_authorize(String paramString)
  {
    faceBook_authorizeAndRun(paramString, new Runnable()
    {
      public void run() {}
    });
  }
  
  public static void faceBook_authorizeAndRun(final String paramString, final Runnable paramRunnable)
  {
    if (!mUSE_FACEBOOK) {
      return;
    }
    mFB_AuthenticationRequested = true;
    mFB_Session = new Session(mContext);
    mPrefs = ((Activity)mContext).getPreferences(0);
    String str = mPrefs.getString("access_token", null);
    if (str == null)
    {
      paramString = paramString.split(",");
      ((Activity)mContext).runOnUiThread(new Runnable()
      {
        public void run()
        {
          Session.OpenRequest localOpenRequest;
          if ((cocojava.mFB_Session != null) && (!cocojava.mFB_Session.isOpened()))
          {
            localOpenRequest = new Session.OpenRequest((Activity)cocojava.mContext).setCallback(new Session.StatusCallback()
            {
              public void call(Session paramAnonymous2Session, SessionState paramAnonymous2SessionState, Exception paramAnonymous2Exception)
              {
                if ((cocojava.mFB_AuthenticationRequested) && (paramAnonymous2SessionState.isOpened()))
                {
                  cocojava.mGLView.queueEvent(new Runnable()
                  {
                    public void run()
                    {
                      cocojava.access$1302(false);
                      CocoJNI.MfacebookLoginComplete();
                    }
                  });
                  cocojava.mFacebookHandler.postDelayed(cocojava.57.this.val$runAfterLogin, 500L);
                }
              }
            });
            if (localOpenRequest != null)
            {
              localOpenRequest.setDefaultAudience(SessionDefaultAudience.FRIENDS);
              localOpenRequest.setPermissions(Arrays.asList(paramString));
              if (!cocojava.mFORCE_FB_WEB) {
                break label92;
              }
              localOpenRequest.setLoginBehavior(SessionLoginBehavior.SUPPRESS_SSO);
            }
          }
          for (;;)
          {
            cocojava.mFB_Session.openForRead(localOpenRequest);
            Session.setActiveSession(cocojava.mFB_Session);
            return;
            label92:
            localOpenRequest.setLoginBehavior(SessionLoginBehavior.SSO_WITH_FALLBACK);
          }
        }
      });
      return;
    }
    paramString = mPrefs.edit();
    paramString.putString("access_token", null);
    paramString.commit();
    paramString = AccessToken.createFromExistingAccessToken(str, null, null, null, null);
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        cocojava.mFB_Session.open(this.val$accessToken, new Session.StatusCallback()
        {
          public void call(Session paramAnonymous2Session, SessionState paramAnonymous2SessionState, Exception paramAnonymous2Exception) {}
        });
        Session.setActiveSession(cocojava.mFB_Session);
        cocojava.mFacebookHandler.postDelayed(paramRunnable, 500L);
      }
    });
  }
  
  public static void faceBook_dialog(String paramString1, String paramString2)
  {
    if (!mUSE_FACEBOOK) {
      return;
    }
    Log.i("FACEBOOK", "faceBook_dialog");
    final Bundle localBundle = new Bundle();
    paramString2 = paramString2.split("#");
    int i = 0;
    while (i < paramString2.length / 2)
    {
      localBundle.putString(paramString2[(i * 2)], paramString2[(i * 2 + 1)]);
      i += 1;
    }
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        ((WebDialog.Builder)new WebDialog.Builder((Activity)cocojava.mContext, cocojava.mFB_Session, this.val$action, localBundle).setOnCompleteListener(new WebDialog.OnCompleteListener()
        {
          public void onComplete(Bundle paramAnonymous2Bundle, FacebookException paramAnonymous2FacebookException)
          {
            int i;
            if (paramAnonymous2FacebookException == null) {
              if ((paramAnonymous2Bundle.getString("request") != null) || (paramAnonymous2Bundle.getString("post_id") != null)) {
                i = 1;
              }
            }
            while (i != 0)
            {
              cocojava.mGLView.queueEvent(new Runnable()
              {
                public void run() {}
              });
              return;
              i = 0;
              continue;
              if ((paramAnonymous2FacebookException instanceof FacebookOperationCanceledException)) {
                i = 0;
              } else {
                i = 0;
              }
            }
            cocojava.mGLView.queueEvent(new Runnable()
            {
              public void run() {}
            });
          }
        })).build().show();
      }
    });
  }
  
  public static void faceBook_dialogWithLogin(String paramString1, final String paramString2)
  {
    faceBook_authorizeAndRun("email,user_birthday", new Runnable()
    {
      public void run()
      {
        cocojava.faceBook_dialog(this.val$action, paramString2);
      }
    });
  }
  
  public static String faceBook_getAccessToken()
  {
    if (!mUSE_FACEBOOK) {
      return "";
    }
    return mFB_Session.getAccessToken();
  }
  
  public static int faceBook_hasPermission(String paramString)
  {
    if (!mUSE_FACEBOOK) {}
    while (!mFB_Session.getPermissions().contains(paramString)) {
      return 0;
    }
    return 1;
  }
  
  public static int faceBook_isSessionValid()
  {
    if (!mUSE_FACEBOOK) {}
    while (!mFB_Session.isOpened()) {
      return 0;
    }
    return 1;
  }
  
  public static void faceBook_logout()
  {
    if (!mUSE_FACEBOOK) {
      return;
    }
    mFB_Session.closeAndClearTokenInformation();
    Session.setActiveSession(null);
  }
  
  public static void faceBook_reauthorizeWithPublishPermissions(String paramString)
  {
    if (!mUSE_FACEBOOK) {
      return;
    }
    paramString = paramString.split(",");
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        if ((cocojava.mFB_Session != null) && (cocojava.mFB_Session.getState().isOpened()))
        {
          Session.NewPermissionsRequest localNewPermissionsRequest = new Session.NewPermissionsRequest((Activity)cocojava.mContext, Arrays.asList(this.val$permissionsArray));
          cocojava.mFB_Session.requestNewPublishPermissions(localNewPermissionsRequest);
        }
      }
    });
  }
  
  public static void faceBook_request(String paramString, final int paramInt)
  {
    if (!mUSE_FACEBOOK) {
      return;
    }
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        Request.executeGraphPathRequestAsync(cocojava.mFB_Session, this.val$graphPath, new Request.Callback()
        {
          public void onCompleted(final Response paramAnonymous2Response)
          {
            paramAnonymous2Response = paramAnonymous2Response.getGraphObject().getInnerJSONObject().toString();
            cocojava.mGLView.queueEvent(new Runnable()
            {
              public void run()
              {
                CocoJNI.MfacebookRequestComplete(cocojava.60.this.val$object, paramAnonymous2Response.getBytes().length, paramAnonymous2Response.getBytes());
              }
            });
          }
        });
      }
    });
  }
  
  public static void flashScreen(int paramInt) {}
  
  protected static int getAdsDisabled()
  {
    return getAdsDisabled(mContext);
  }
  
  protected static int getAdsDisabled(Context paramContext)
  {
    int i = 0;
    if (paramContext.getSharedPreferences("ADS_DISABLED", 0).getString("disabled", "false").equals("true")) {
      i = 1;
    }
    return i;
  }
  
  public static int getAndroidVersion()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static int getAppVersionCode()
  {
    int i = 0;
    try
    {
      PackageInfo localPackageInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
      if (localPackageInfo != null) {
        i = localPackageInfo.versionCode;
      }
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return 0;
  }
  
  public static String getAppVersionNumber()
  {
    String str2 = "";
    String str1 = str2;
    try
    {
      PackageInfo localPackageInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
      str1 = str2;
      if (localPackageInfo != null)
      {
        str1 = str2;
        str2 = localPackageInfo.versionName;
        str1 = str2;
        int i = localPackageInfo.versionCode;
        str1 = str2;
      }
      return str1;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return str1;
  }
  
  public static long getAvailableMemory(boolean paramBoolean)
  {
    long l;
    StringBuilder localStringBuilder;
    if (paramBoolean)
    {
      localObject = Environment.getExternalStorageDirectory();
      localObject = new StatFs(((File)localObject).getPath());
      l = ((StatFs)localObject).getAvailableBlocks() * ((StatFs)localObject).getBlockSize();
      localStringBuilder = new StringBuilder().append("Available ");
      if (!paramBoolean) {
        break label98;
      }
    }
    label98:
    for (Object localObject = "sdcard";; localObject = "internal")
    {
      Log.i("cocojava", (String)localObject + " memory: " + l / 1048576L);
      return l / 1048576L;
      localObject = Environment.getDataDirectory();
      break;
    }
  }
  
  public static float getBackgroundMusicVolume()
  {
    return backgroundMusicPlayer.getBackgroundVolume();
  }
  
  public static int getCaretPosition()
  {
    int i = mEditText.getSelectionStart();
    Log.i("KeyboardInput", String.format("getCaretPosition position: %d", new Object[] { Integer.valueOf(i) }));
    return i;
  }
  
  public static String getDeviceId()
  {
    String str1 = SharedPreferences_getString("Device Id");
    Object localObject4 = str1;
    Object localObject5;
    if (str1 == "")
    {
      Log.i("JAVAINFO", "Trying to get miniclipId from sdcard");
      localObject1 = "";
      localObject4 = localObject1;
      localObject5 = localObject1;
    }
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/sdcard/miniclipId.txt"))));
      for (;;)
      {
        localObject4 = localObject1;
        localObject5 = localObject1;
        String str2 = localBufferedReader.readLine();
        if (str2 == null) {
          break;
        }
        localObject4 = localObject1;
        localObject5 = localObject1;
        localObject1 = (String)localObject1 + str2;
      }
      localObject4 = localObject1;
      localObject5 = localObject1;
      localBufferedReader.close();
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      for (;;)
      {
        int i;
        localFileNotFoundException.printStackTrace();
        localObject2 = localObject4;
      }
    }
    catch (IOException localIOException1)
    {
      long l;
      for (;;)
      {
        localIOException1.printStackTrace();
        localObject3 = localObject5;
      }
      localObject3 = String.valueOf(l) + "-" + new String((char[])localObject4);
      SharedPreferences_setString("Device Id", (String)localObject3);
      localObject4 = new File("/sdcard/miniclipId.txt");
      if ((localObject3 == "") || (((File)localObject4).exists())) {
        break label434;
      }
      Log.i("JAVAINFO", "Saving miniclipId to sdcard");
    }
    localObject4 = str1;
    if (localObject1 != "")
    {
      SharedPreferences_setString("Device Id", (String)localObject1);
      Log.i("JAVAINFO", "DeviceID from SD Card = " + (String)localObject1);
      localObject4 = localObject1;
    }
    Object localObject1 = localObject4;
    if (localObject4 == "")
    {
      Log.i("JAVAINFO", "Generating miniclipId");
      l = new Date().getTime();
      localObject1 = new Random();
      localObject4 = new char[10];
      i = 0;
      while (i < 10)
      {
        localObject4[i] = "abcdefghijmnopqrstuvxz0123456789".charAt(((Random)localObject1).nextInt("abcdefghijmnopqrstuvxz0123456789".length()));
        i += 1;
      }
    }
    try
    {
      Object localObject2;
      Object localObject3;
      ((File)localObject4).createNewFile();
      localObject4 = new FileOutputStream((File)localObject4);
      localObject5 = new OutputStreamWriter((OutputStream)localObject4);
      ((OutputStreamWriter)localObject5).append((CharSequence)localObject3);
      ((OutputStreamWriter)localObject5).close();
      ((FileOutputStream)localObject4).close();
      Log.i("JAVAINFO", "DeviceID stored to sdcard = " + (String)localObject3);
      label434:
      Log.i("JAVAINFO", "DeviceID = " + (String)localObject3);
      return localObject3;
    }
    catch (IOException localIOException2)
    {
      for (;;)
      {
        localIOException2.printStackTrace();
      }
    }
  }
  
  public static String getDeviceInfo(String paramString)
  {
    if (paramString.contentEquals("device")) {
      return Build.DEVICE;
    }
    if (paramString.contentEquals("version")) {
      return Build.VERSION.RELEASE;
    }
    if (paramString.contentEquals("brand")) {
      return Build.BRAND;
    }
    return "";
  }
  
  public static int getDurationForSound(String paramString)
  {
    return backgroundMusicPlayer.getDurationForSound(paramString);
  }
  
  public static float getEffectsVolume()
  {
    return soundPlayer.getEffectsVolume();
  }
  
  public static String getExternalStorageDirectory()
  {
    String str = mContext.getPackageName();
    Object localObject = Environment.getExternalStorageDirectory().getAbsolutePath();
    str = (String)localObject + "/Android/data/" + str;
    str = str + "/Contents/Resources";
    localObject = new File(str);
    boolean bool = false;
    if (!((File)localObject).exists()) {
      bool = ((File)localObject).mkdirs();
    }
    if (!bool) {}
    return str;
  }
  
  static int getNotificationStatus()
  {
    return mContext.getSharedPreferences("NewsfeedPrefsC2DM", 0).getInt("C2DM_ENABLE", 0);
  }
  
  public static int getNumCores()
  {
    try
    {
      int i = new File("/sys/devices/system/cpu/").listFiles(new FileFilter()
      {
        public boolean accept(File paramAnonymousFile)
        {
          return Pattern.matches("cpu[0-9]", paramAnonymousFile.getName());
        }
      }).length;
      return i;
    }
    catch (Exception localException) {}
    return 1;
  }
  
  public static int getPendingPurchaseAmount(String paramString)
  {
    int i = mContext.getSharedPreferences("INAPP_PURCHASED_TEMP", 0).getInt(paramString, 0);
    Log.i("cocojava", String.format("getPendingPurchaseAmount: %s, %d", new Object[] { paramString, Integer.valueOf(i) }));
    return i;
  }
  
  public static int getPendingPurchaseSignatureAmount()
  {
    SharedPreferences localSharedPreferences = mContext.getSharedPreferences("INAPP_PURCHASED_TEMP_SIGNATURE", 0);
    int j = localSharedPreferences.getInt("amount", 0);
    Log.i("cocojava", String.format("getPendingPurchaseSignatureAmount: %d", new Object[] { Integer.valueOf(j) }));
    int i;
    if (j < 0) {
      i = 0;
    }
    do
    {
      return i;
      i = j;
    } while (localSharedPreferences.contains("pid" + String.valueOf(j - 1)));
    return 0;
  }
  
  public static String getProductPrice(String paramString)
  {
    return ((InAppActivity)mContext).getPurchasePrice(paramString);
  }
  
  public static String[] getPurchasedOwnedItems()
  {
    return ((InAppActivity)mContext).getOwnedItems();
  }
  
  public static int getRecommendedPriceGetJar(int paramInt)
  {
    return ((cocojava)mContext).getJarRecommendedPrice(paramInt);
  }
  
  public static void hideAd(MoPubView paramMoPubView)
  {
    if ((paramMoPubView == null) || (paramMoPubView.getParent() != adLayout)) {}
    while (paramMoPubView.getAnimation() != null) {
      return;
    }
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        ScaleAnimation localScaleAnimation = new ScaleAnimation(1.0F, 0.0F, 1.0F, 1.0F, 160.0F * cocojava.mDensity, 0.0F);
        localScaleAnimation.setDuration(500L);
        localScaleAnimation.setAnimationListener(new Animation.AnimationListener()
        {
          public void onAnimationEnd(Animation paramAnonymous2Animation)
          {
            if (cocojava.26.this.val$adViewFinal != null) {
              cocojava.adLayout.removeView(cocojava.26.this.val$adViewFinal);
            }
          }
          
          public void onAnimationRepeat(Animation paramAnonymous2Animation) {}
          
          public void onAnimationStart(Animation paramAnonymous2Animation) {}
        });
        cocojava.adLayout.removeView(this.val$adViewFinal);
      }
    });
  }
  
  public static void hideAds()
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        if ((cocojava.adLayout != null) && (cocojava.adLayout.getParent() == cocojava.adLayoutH)) {
          cocojava.adLayoutH.removeView(cocojava.adLayout);
        }
        cocojava.hideAd(cocojava.adViewGame);
        cocojava.hideAd(cocojava.adViewMenu);
      }
    });
  }
  
  public static void hideAdsInSeconds(int paramInt)
  {
    mAdDelayHandler.removeCallbacks(mHideAdsRun);
    mAdDelayHandler.postDelayed(mHideAdsRun, paramInt * 1000);
  }
  
  public static void hideHorizontalBanner()
  {
    if (!mHorizontalBannerDisplayed) {
      return;
    }
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        Log.i("horizontalBanner", "hideHorizontalBanner");
        RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(cocojava.mWidth, cocojava.mHeight);
        localLayoutParams.addRule(10);
        cocojava.mGLView.setLayoutParams(localLayoutParams);
        if (cocojava.mHorizontalBanner != null)
        {
          cocojava.mHorizontalBanner.setBlockAutoRefresh(false);
          cocojava.mHorizontalBanner.setAutorefreshEnabled(false);
          cocojava.mHorizontalBanner.setBlockAutoRefresh(true);
        }
        cocojava.mHorizontalBannerDisplayed = false;
      }
    });
  }
  
  public static void hideMessageBox(int paramInt)
  {
    Message localMessage = new Message();
    localMessage.what = 2;
    localMessage.obj = new DialogMessage(paramInt);
    mHandler.sendMessage(localMessage);
  }
  
  public static void hideRemoveAds()
  {
    if (mRemoveAdsButtonHidden) {}
    do
    {
      return;
      mRemoveAdsButtonHidden = true;
    } while (!mUSE_ADS);
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        cocojava.mRemoveAdsButtonLayout.removeView(cocojava.mRemoveAdsButton);
      }
    });
  }
  
  public static void hideRotatedBanner()
  {
    if (mRotatedBannerDisplayed == 0) {
      return;
    }
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        Log.i("rotatedBanner", "hideRotatedBanner");
        RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(cocojava.mWidth, cocojava.mHeight);
        localLayoutParams.addRule(11);
        cocojava.mGLView.setLayoutParams(localLayoutParams);
        if (cocojava.mRotatedBanner != null)
        {
          cocojava.mRotatedBanner.setBlockAutoRefresh(false);
          cocojava.mRotatedBanner.setAutorefreshEnabled(false);
          cocojava.mRotatedBanner.setBlockAutoRefresh(true);
        }
        cocojava.mRotatedBannerDisplayed = 0;
      }
    });
  }
  
  public static int isAppInstalled(String paramString)
  {
    Iterator localIterator = mContext.getPackageManager().getInstalledApplications(8192).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.compareTo(paramString) == 0) {
        return 1;
      }
    }
    return 0;
  }
  
  public static boolean isBackgroundMusicPlaying()
  {
    return backgroundMusicPlayer.isBackgroundMusicPlaying();
  }
  
  public static int isFullVersion()
  {
    return SharedPreferences_getInt("FULL_VERSION");
  }
  
  public static boolean isInstalledOnSdCard()
  {
    try
    {
      String str = mContext.getFilesDir().getAbsolutePath();
      if (str.startsWith("/data/")) {
        return false;
      }
      if (!str.contains("/mnt/"))
      {
        boolean bool = str.contains("/sdcard/");
        if (!bool) {}
      }
      else
      {
        return true;
      }
    }
    catch (Throwable localThrowable) {}
    return false;
  }
  
  public static int isPurchaseOwned(String paramString)
  {
    return ((InAppActivity)mContext).isPurchaseReallyOwned(paramString);
  }
  
  public static boolean isSDcardPresent()
  {
    return "mounted".equals("mounted");
  }
  
  public static int isSignedIn_GooglePlayServices()
  {
    return ((cocojava)mContext).googlePlayServicesIsSignedInCustom();
  }
  
  public static void keyboardInput_Hide()
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        ((InputMethodManager)cocojava.mContext.getSystemService("input_method")).hideSoftInputFromWindow(cocojava.mEditText.getWindowToken(), 0);
      }
    });
  }
  
  public static void keyboardInput_Show(String paramString)
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        cocojava.mEditText.setText(this.val$text);
        cocojava.mEditText.setVisibility(0);
        ((InputMethodManager)cocojava.mContext.getSystemService("input_method")).toggleSoftInput(2, 2);
        cocojava.mEditText.setSelection(cocojava.mEditText.getText().length());
      }
    });
  }
  
  public static void keyboardInput_Show_withMaxLengthLocked(String paramString, final int paramInt)
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        cocojava.mEditText.setText(this.val$text);
        cocojava.mEditText.setVisibility(0);
        InputFilter.LengthFilter localLengthFilter = new InputFilter.LengthFilter(paramInt);
        cocojava.mEditText.setFilters(new InputFilter[] { localLengthFilter });
        ((InputMethodManager)cocojava.mContext.getSystemService("input_method")).toggleSoftInput(2, 2);
        cocojava.mEditText.setSelection(cocojava.mEditText.getText().length());
      }
    });
  }
  
  public static void loadFullScreenAd()
  {
    if (!mUSE_ADS) {
      return;
    }
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        if ((cocojava.mMopubInterstitial == null) || (cocojava.mMopubInterstitial.hasFinished().booleanValue())) {
          cocojava.access$602(new MopubInterstitial(cocojava.mContext));
        }
        cocojava.mMopubInterstitial.loadInterstitialAd();
      }
    });
  }
  
  public static void loadTexture(String paramString)
  {
    texture.readBitmap(paramString);
  }
  
  public static void logFlurryEvent(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2, boolean paramBoolean)
  {
    Log.i("flurry", "logFlurryEvent: " + paramString + " timed: " + paramBoolean);
    Map localMap = convertToMap(paramArrayOfString1, paramArrayOfString2);
    FlurryAgent.logEvent(paramString, localMap, paramBoolean);
    if ((!paramBoolean) && (localMap != null))
    {
      paramString = "Flurry/" + paramString + "/";
      if (localMap != null) {
        break label93;
      }
    }
    for (;;)
    {
      return;
      label93:
      int i = 0;
      while (i < localMap.size())
      {
        String str = paramString + paramArrayOfString1[i] + "->" + paramArrayOfString2[i];
        Log.i("sendGoogleEvent", "logGoogleEventFromFlurry: " + str);
        sendGoogleEvent(str, 1);
        i += 1;
      }
    }
  }
  
  public static void login_GooglePlayServices()
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (cocojava.mCustomLeaderboard) {
          ((cocojava)cocojava.mContext).googlePlayServicesLoginCustom();
        }
      }
    });
  }
  
  public static void logout_GooglePlayServices()
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (cocojava.mCustomLeaderboard) {
          ((cocojava)cocojava.mContext).googlePlayServicesLogoutCustom();
        }
      }
    });
  }
  
  private String md5(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      paramString = ((MessageDigest)localObject).digest();
      localObject = new StringBuffer();
      int i = 0;
      while (i < paramString.length)
      {
        ((StringBuffer)localObject).append(Integer.toHexString(paramString[i] & 0xFF));
        i += 1;
      }
      paramString = ((StringBuffer)localObject).toString();
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static void mopub_showAdFailed()
  {
    Log.i("mopub", "showAdFailed");
    showMiniclipView();
  }
  
  public static void openLink(String paramString)
  {
    Message localMessage = new Message();
    localMessage.what = 4;
    localMessage.obj = paramString;
    mHandler.sendMessage(localMessage);
  }
  
  private void openURL(String paramString)
  {
    if (!isOnline())
    {
      showNoInternetDialog();
      return;
    }
    try
    {
      this.googleTracker.trackEvent("OpenURL", "Open", paramString, 1);
      this.googleTracker.dispatch();
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setData(Uri.parse(paramString));
      startActivity(localIntent);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static void pauseAllSounds()
  {
    soundPlayer.pauseAllSounds();
  }
  
  public static void pauseBackgroundMusic()
  {
    backgroundMusicPlayer.pauseBackgroundMusic();
  }
  
  public static void pauseEffect(int paramInt)
  {
    soundPlayer.pauseEffect(paramInt);
  }
  
  public static String percentEncodeUTF8(String paramString)
  {
    try
    {
      paramString = URLEncoder.encode(paramString, "UTF-8");
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  public static void permanentlyRemoveAds()
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        cocojava.setAdsDisabled(1);
        cocojava.disableAds();
        cocojava.hideRemoveAds();
        cocojava.hideRotatedBanner();
        cocojava.hideHorizontalBanner();
        cocojava.mUSE_ADS = false;
        cocojava.mUSE_ADS_VERTICAL = false;
        cocojava.mUSE_ADS_HORIZONTAL = false;
      }
    });
  }
  
  public static void playBackgroundMusic(String paramString, boolean paramBoolean)
  {
    backgroundMusicPlayer.playBackgroundMusic(paramString, paramBoolean);
  }
  
  public static void playEffect(int paramInt)
  {
    soundPlayer.playEffect(paramInt);
  }
  
  public static void playEffect(int paramInt1, float paramFloat1, float paramFloat2, int paramInt2, float paramFloat3)
  {
    cocosound localCocosound = soundPlayer;
    soundPlayer.getClass();
    localCocosound.playEffect(paramInt1, paramFloat1, paramFloat2, 1, paramInt2, paramFloat3);
  }
  
  public static void playEffect(int paramInt1, float paramFloat1, float paramFloat2, int paramInt2, int paramInt3, float paramFloat3)
  {
    soundPlayer.playEffect(paramInt1, paramFloat1, paramFloat2, paramInt2, paramInt3, paramFloat3);
  }
  
  public static void playEffect(int paramInt1, int paramInt2, float paramFloat)
  {
    cocosound localCocosound = soundPlayer;
    float f1 = soundPlayer.mLeftVolume;
    float f2 = soundPlayer.mRightVolume;
    soundPlayer.getClass();
    localCocosound.playEffect(paramInt1, f1, f2, 1, paramInt2, paramFloat);
  }
  
  public static void playEffect(int paramInt1, int paramInt2, int paramInt3, float paramFloat)
  {
    soundPlayer.playEffect(paramInt1, soundPlayer.mLeftVolume, soundPlayer.mRightVolume, paramInt2, paramInt3, paramFloat);
  }
  
  public static int preloadEffect(String paramString)
  {
    return soundPlayer.preloadEffect(paramString);
  }
  
  public static void pushInterstitial()
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        ((cocojava)cocojava.mContext).showInterstitial();
      }
    });
  }
  
  public static int removeLastPendingPurchaseSignature()
  {
    Object localObject = mContext.getSharedPreferences("INAPP_PURCHASED_TEMP_SIGNATURE", 0);
    int i = ((SharedPreferences)localObject).getInt("amount", 0) - 1;
    if (i < 0) {}
    while (!((SharedPreferences)localObject).contains("pid" + String.valueOf(i))) {
      return 0;
    }
    localObject = ((SharedPreferences)localObject).edit();
    ((SharedPreferences.Editor)localObject).remove("pid" + String.valueOf(i));
    ((SharedPreferences.Editor)localObject).remove("data" + String.valueOf(i));
    ((SharedPreferences.Editor)localObject).remove("sig" + String.valueOf(i));
    ((SharedPreferences.Editor)localObject).putInt("amount", i);
    ((SharedPreferences.Editor)localObject).commit();
    Log.i("cocojava", String.format("removePendingPurchaseSignature: %d", new Object[] { Integer.valueOf(i) }));
    return 1;
  }
  
  public static void resumeAllSounds()
  {
    soundPlayer.resumeAllSounds();
  }
  
  public static void resumeBackgroundMusic()
  {
    backgroundMusicPlayer.resumeBackgroundMusic();
  }
  
  public static void rewindBackgroundMusic()
  {
    backgroundMusicPlayer.rewindBackgroundMusic();
  }
  
  public static void sendEmail(String paramString1, final String paramString2, final String paramString3)
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        Intent localIntent = new Intent("android.intent.action.SEND");
        localIntent.setType("text/plain");
        if (this.val$dest != null) {
          localIntent.putExtra("android.intent.extra.EMAIL", new String[] { this.val$dest });
        }
        if (paramString2 != null) {
          localIntent.putExtra("android.intent.extra.SUBJECT", paramString2);
        }
        if (paramString3 != null) {
          localIntent.putExtra("android.intent.extra.TEXT", paramString3);
        }
        cocojava.mContext.startActivity(Intent.createChooser(localIntent, "Send email..."));
      }
    });
  }
  
  public static void sendGoogleEvent(String paramString, final int paramInt)
  {
    paramString = paramString.split("/");
    if (paramString.length < 3) {
      return;
    }
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        ((cocojava)cocojava.mContext).googleTracker.trackEvent(this.val$event[0], this.val$event[1], this.val$event[2], paramInt);
        ((cocojava)cocojava.mContext).googleTracker.dispatch();
      }
    });
  }
  
  public static void sendSMS(String paramString)
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        Intent localIntent = new Intent("android.intent.action.VIEW");
        if (this.val$txt != null) {
          localIntent.putExtra("sms_body", this.val$txt);
        }
        localIntent.setType("vnd.android-dir/mms-sms");
        cocojava.mContext.startActivity(localIntent);
      }
    });
  }
  
  protected static void setAdsDisabled(int paramInt)
  {
    SharedPreferences.Editor localEditor = mContext.getSharedPreferences("ADS_DISABLED", 0).edit();
    if (paramInt == 1) {
      localEditor.putString("disabled", "true");
    }
    for (;;)
    {
      localEditor.commit();
      return;
      localEditor.putString("disabled", "false");
    }
  }
  
  public static void setBackgroundMusicVolume(float paramFloat)
  {
    backgroundMusicPlayer.setBackgroundVolume(paramFloat);
  }
  
  public static void setCaretPosition(int paramInt)
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        cocojava.mEditText.setSelection(this.val$position);
        Log.i("KeyboardInput", String.format("setCaretPosition position: %d", new Object[] { Integer.valueOf(this.val$position) }));
      }
    });
  }
  
  public static void setClipboardText(String paramString)
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        ((ClipboardManager)cocojava.mContext.getSystemService("clipboard")).setText(this.val$text);
      }
    });
  }
  
  public static void setContentToGl()
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        cocojava.layout.removeView(cocojava.mInitView);
      }
    });
  }
  
  public static void setEffectGain(int paramInt, float paramFloat)
  {
    soundPlayer.setEffectGain(paramInt, paramFloat);
  }
  
  public static void setEffectLooping(int paramInt, boolean paramBoolean)
  {
    soundPlayer.setEffectLooping(paramInt, paramBoolean);
  }
  
  public static void setEffectPitch(int paramInt, float paramFloat)
  {
    soundPlayer.setEffectPitch(paramInt, paramFloat);
  }
  
  public static void setEffectsVolume(float paramFloat)
  {
    soundPlayer.setEffectsVolume(paramFloat);
  }
  
  public static void setFPS(float paramFloat)
  {
    ClearRenderer.animationInterval = (1.0D / paramFloat * 1000000000L);
  }
  
  public static void setFullVersion()
  {
    SharedPreferences_setInt("FULL_VERSION", 1);
  }
  
  public static void setKeyboardInputMaxLength(int paramInt)
  {
    Log.i("cocojava", String.format("setKeyboardInputMaxLength: %d", new Object[] { Integer.valueOf(paramInt) }));
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        InputFilter.LengthFilter localLengthFilter = new InputFilter.LengthFilter(this.val$maxLength);
        cocojava.mEditText.setFilters(new InputFilter[] { localLengthFilter });
      }
    });
  }
  
  public static void setKeyboardInputPosition(final float paramFloat1, final float paramFloat2, final float paramFloat3, float paramFloat4, final float paramFloat5, final float paramFloat6)
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        float f = this.val$height;
        if (this.val$height == 0.0F) {
          f = cocojava.mEditText.getHeight();
        }
        RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams((int)paramFloat3, (int)f);
        localLayoutParams.leftMargin = ((int)(paramFloat1 - paramFloat3 * paramFloat5));
        localLayoutParams.topMargin = ((int)(paramFloat2 - paramFloat6 * f));
        Log.i("setKeyboardInputPosition", String.format("newHeight: %f, anchorY: %f", new Object[] { Float.valueOf(f), Float.valueOf(paramFloat6) }));
      }
    });
  }
  
  public static void setKeyboardInputSingleLine(int paramInt)
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        int i = 1;
        EditText localEditText = cocojava.mEditText;
        if (this.val$singleLine == 1) {}
        for (;;)
        {
          localEditText.setMaxLines(i);
          return;
          i = 100;
        }
      }
    });
  }
  
  public static void setKeyboardInputStyle(int paramInt1, final int paramInt2, final float paramFloat)
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        cocojava.mEditText.setBackgroundColor(this.val$background);
        cocojava.mEditText.setTextColor(paramInt2);
        cocojava.mEditText.setTextSize(paramFloat);
      }
    });
  }
  
  public static void setKeyboardInputText(String paramString)
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        cocojava.mEditText.setText(this.val$text);
      }
    });
  }
  
  public static void setKeyboardInputTypePassword(int paramInt)
  {
    Log.i("cocojava", String.format("setKeyboardInputTypePassword: %d", new Object[] { Integer.valueOf(paramInt) }));
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (this.val$password == 0)
        {
          cocojava.mEditText.setInputType(145);
          return;
        }
        cocojava.mEditText.setInputType(129);
      }
    });
  }
  
  public static void setKeyboardInputVisible(int paramInt)
  {
    Log.i("setKeyboardInputVisible", String.format("visible: %d", new Object[] { Integer.valueOf(paramInt) }));
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (this.val$visible == 0)
        {
          cocojava.mEditText.setVisibility(4);
          return;
        }
        cocojava.mEditText.setVisibility(0);
      }
    });
  }
  
  public static void setKeyboardInputVisibleDelayed(int paramInt1, int paramInt2)
  {
    mKeyboardHandler.postDelayed(new Runnable()
    {
      public void run()
      {
        cocojava.setKeyboardInputVisible(this.val$visible);
      }
    }, paramInt2);
  }
  
  public static void setPendingPurchaseAmount(String paramString, int paramInt)
  {
    SharedPreferences.Editor localEditor = mContext.getSharedPreferences("INAPP_PURCHASED_TEMP", 0).edit();
    localEditor.putInt(paramString, paramInt);
    localEditor.commit();
  }
  
  public static void showAd(MoPubView paramMoPubView)
  {
    if ((!mUSE_ADS) || (paramMoPubView == null)) {
      return;
    }
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (this.val$adView != null)
        {
          cocojava.adLayout.removeView(this.val$adView);
          cocojava.adLayout.addView(this.val$adView);
        }
      }
    });
  }
  
  public static void showAds()
  {
    Log.i("showAds", "deprecated use enableAdsWithPosition");
  }
  
  public static void showBigAd()
  {
    if (!mUSE_ADS_BIG) {}
    while (!mUSE_ADS) {
      return;
    }
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        MoPubView localMoPubView = new MoPubView(cocojava.mContext);
        localMoPubView.setOnAdLoadedListener((cocojava)cocojava.mContext);
        localMoPubView.setAdUnitId(((cocojava)cocojava.mContext).getMoPubInterstitialId());
        localMoPubView.loadAd();
        if (cocojava.mMopubView != null) {
          cocojava.layout.removeView(cocojava.mMopubView);
        }
        cocojava.access$502(new MopubView(cocojava.mContext, localMoPubView));
        cocojava.layout.addView(cocojava.mMopubView);
      }
    });
  }
  
  public static void showDatePickerDialog_JNI(int paramInt1, final int paramInt2, final int paramInt3)
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        ((cocojava)cocojava.mContext).showDatePickerDialog(this.val$year, paramInt2, paramInt3);
      }
    });
  }
  
  public static void showDatePickerDialog_JNI(int paramInt1, final int paramInt2, final int paramInt3, final int paramInt4)
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        ((cocojava)cocojava.mContext).showDatePickerDialog(this.val$year, paramInt2, paramInt3, paramInt4);
      }
    });
  }
  
  public static void showFullScreenAd()
  {
    Log.i("MopubFULLSCREEN", "showfullscreenAd");
    if (!mUSE_ADS) {
      return;
    }
    Log.i("MopubFULLSCREEN", "Going to show ad!");
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        if ((cocojava.mMopubInterstitial == null) || (cocojava.mMopubInterstitial.hasFinished().booleanValue())) {
          cocojava.access$602(new MopubInterstitial(cocojava.mContext));
        }
        cocojava.mMopubInterstitial.showInterstitialAd();
      }
    });
  }
  
  public static void showHorizontalBanner()
  {
    if ((mHorizontalBannerDisplayed) || (!mUSE_ADS_HORIZONTAL)) {
      return;
    }
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        Log.i("horizontalBanner", "showHorizontalBanner");
        RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(cocojava.mWidth, cocojava.mHeight - (int)(50.0F * cocojava.mDensity));
        localLayoutParams.addRule(12);
        cocojava.mGLView.setLayoutParams(localLayoutParams);
        if (cocojava.mHorizontalBanner == null)
        {
          cocojava.access$1102(new horizontalBanner(cocojava.mContext));
          cocojava.layout.addView(cocojava.mHorizontalBanner);
        }
        cocojava.mHorizontalBanner.setBlockAutoRefresh(false);
        cocojava.mHorizontalBanner.resetRefreshTime();
        cocojava.mHorizontalBanner.setAutorefreshEnabled(true);
        cocojava.mHorizontalBanner.setBlockAutoRefresh(true);
        cocojava.mHorizontalBannerDisplayed = true;
      }
    });
  }
  
  public static void showHtmlDialog(String paramString, final int paramInt)
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        new HtmlDialog(cocojava.mContext, this.val$htmlContent, paramInt, new Facebook.DialogListener()
        {
          public void onCancel() {}
          
          public void onComplete(Bundle paramAnonymous2Bundle) {}
          
          public void onError(DialogError paramAnonymous2DialogError) {}
          
          public void onFacebookError(FacebookError paramAnonymous2FacebookError) {}
        }).show();
      }
    });
  }
  
  public static void showInterstitialBanner()
  {
    if (!mUSE_ADS_INTERSTITIAL_BANNER) {
      return;
    }
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        cocojava.mInterstitialBanner.showAd();
      }
    });
  }
  
  public static void showInterstitialMopubView()
  {
    if (!mUSE_ADS_INTERSTITIAL_FULLSCREEN) {}
    while (!mUSE_ADS) {
      return;
    }
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        cocojava.mGLView.queueEvent(new Runnable()
        {
          public void run()
          {
            CocoJNI.MsetGamePause(1);
          }
        });
        if (cocojava.adLayout.getParent() == cocojava.adLayoutH) {
          cocojava.adLayoutH.removeView(cocojava.adLayout);
        }
        cocojava.mFullscreenInterstitial.showAd();
      }
    });
  }
  
  public static void showMessageBox(int paramInt, String paramString1, String paramString2, String[] paramArrayOfString)
  {
    DialogMessage localDialogMessage = new DialogMessage(paramInt);
    localDialogMessage.title = paramString1;
    localDialogMessage.message = paramString2;
    localDialogMessage.buttonTitles = paramArrayOfString;
    paramString1 = new Message();
    paramString1.what = 1;
    paramString1.obj = localDialogMessage;
    mHandler.sendMessage(paramString1);
  }
  
  public static void showMiniclipView()
  {
    if (!mUSE_ADS) {
      return;
    }
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        ((cocojava)cocojava.mContext).showMiniclipViewInternal();
      }
    });
  }
  
  public static void showMopubView()
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (cocojava.mBigAdView != null) {
          cocojava.mBigAdView.removeAllViews();
        }
        for (;;)
        {
          cocojava.access$202(new RelativeLayout(cocojava.mContext));
          Object localObject = new RelativeLayout.LayoutParams((int)(300.0D * cocojava.mDensity) + 40, -1);
          cocojava.mCenterdAd.setLayoutParams((ViewGroup.LayoutParams)localObject);
          cocojava.mCenterdAd.setOnClickListener((View.OnClickListener)cocojava.mContext);
          cocojava.mBigAdView.addView(cocojava.mCenterdAd);
          localObject = new ImageView(cocojava.mContext);
          int i = cocojava.mContext.getResources().getIdentifier("full_dialog1", "drawable", cocojava.mContext.getPackageName());
          ((ImageView)localObject).setImageDrawable(cocojava.mContext.getResources().getDrawable(i));
          ((ImageView)localObject).setScaleType(ImageView.ScaleType.FIT_XY);
          ((ImageView)localObject).setLayoutParams(new Gallery.LayoutParams(-1, -1));
          cocojava.mCenterdAd.addView((View)localObject);
          localObject = new RelativeLayout(cocojava.mContext);
          RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
          ((RelativeLayout)localObject).setVerticalGravity(80);
          localLayoutParams.addRule(12);
          ((RelativeLayout)localObject).setLayoutParams(localLayoutParams);
          ((RelativeLayout)localObject).setPadding(20, 0, 20, 5);
          cocojava.mCenterdAd.addView((View)localObject);
          cocojava.access$302(new Button(cocojava.mContext));
          new RelativeLayout.LayoutParams((int)(300.0D * cocojava.mDensity * 0.5D) - 40, -2);
          cocojava.mSkip1.setText("Skip");
          ((RelativeLayout)localObject).addView(cocojava.mSkip1);
          cocojava.mSkip1.setOnClickListener((View.OnClickListener)cocojava.mContext);
          cocojava.access$402(new Button(cocojava.mContext));
          localLayoutParams = new RelativeLayout.LayoutParams((int)(300.0D * cocojava.mDensity * 0.5D) - 40, -2);
          localLayoutParams.addRule(11);
          cocojava.mRemove1.setLayoutParams(localLayoutParams);
          cocojava.mRemove1.setText("Remove Ads");
          ((RelativeLayout)localObject).addView(cocojava.mRemove1);
          cocojava.mRemove1.setOnClickListener((View.OnClickListener)cocojava.mContext);
          cocojava.mBigAdView.setOnClickListener((View.OnClickListener)cocojava.mContext);
          cocojava.mBigAdView.setClickable(true);
          cocojava.mGLView.queueEvent(new Runnable()
          {
            public void run()
            {
              CocoJNI.MsetGamePause(1);
            }
          });
          return;
          cocojava.access$102(new RelativeLayout(cocojava.mContext));
          localObject = new RelativeLayout.LayoutParams(-1, -1);
          cocojava.mBigAdView.setHorizontalGravity(17);
          cocojava.mBigAdView.setVerticalGravity(80);
          cocojava.mBigAdView.setLayoutParams((ViewGroup.LayoutParams)localObject);
          cocojava.layout.addView(cocojava.mBigAdView);
        }
      }
    });
  }
  
  private void showNoInternetDialog()
  {
    newDialog.Builder localBuilder = new newDialog.Builder(this);
    localBuilder.setMessage("Please enable internet").setCancelable(false).setPositiveButton("Back", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    localBuilder.create().show();
  }
  
  public static void showRemoveAds()
  {
    if (!mRemoveAdsButtonHidden) {}
    do
    {
      return;
      mRemoveAdsButtonHidden = false;
    } while (!mUSE_ADS);
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        cocojava.mRemoveAdsButtonLayout.removeView(cocojava.mRemoveAdsButton);
        cocojava.mRemoveAdsButtonLayout.addView(cocojava.mRemoveAdsButton);
      }
    });
  }
  
  public static void showRotatedBanner()
  {
    showRotatedBanner(ALIGN_LEFT);
  }
  
  public static void showRotatedBanner(int paramInt)
  {
    if (mRotatedBannerDisplayed == 1) {}
    while (!mUSE_ADS_VERTICAL) {
      return;
    }
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        Log.i("rotatedBanner", "showRotatedBanner");
        if (this.val$alignment == cocojava.ALIGN_RIGHT)
        {
          localLayoutParams = new RelativeLayout.LayoutParams(cocojava.sideBar1.getLayoutParams());
          localLayoutParams.addRule(11);
          cocojava.sideBar1.setLayoutParams(localLayoutParams);
          localLayoutParams = new RelativeLayout.LayoutParams(cocojava.sideBar2.getLayoutParams());
          localLayoutParams.addRule(11);
          localLayoutParams.addRule(12);
          cocojava.sideBar2.setLayoutParams(localLayoutParams);
        }
        RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(cocojava.mWidth - (int)(50.0F * cocojava.mDensity), cocojava.mHeight);
        if (this.val$alignment == cocojava.ALIGN_LEFT) {
          localLayoutParams.addRule(11);
        }
        cocojava.mGLView.setLayoutParams(localLayoutParams);
        if (cocojava.mRotatedBanner == null)
        {
          cocojava.access$1002(new rotatedBannerImg(cocojava.mContext, this.val$alignment));
          cocojava.layout.addView(cocojava.mRotatedBanner);
        }
        for (;;)
        {
          cocojava.mRotatedBanner.setBlockAutoRefresh(false);
          cocojava.mRotatedBanner.resetRefreshTime();
          cocojava.mRotatedBanner.setAutorefreshEnabled(true);
          cocojava.mRotatedBanner.setBlockAutoRefresh(true);
          if (this.val$alignment == cocojava.ALIGN_RIGHT)
          {
            localLayoutParams = new RelativeLayout.LayoutParams(cocojava.mRotatedBanner.getLayoutParams());
            localLayoutParams.width = ((int)(cocojava.mDensity * 320.0F));
            localLayoutParams.height = ((int)(cocojava.mDensity * 320.0F));
            localLayoutParams.addRule(11);
            localLayoutParams.addRule(15);
            cocojava.mRotatedBanner.setLayoutParams(localLayoutParams);
          }
          cocojava.mRotatedBannerDisplayed = 1;
          return;
          cocojava.mRotatedBanner.setAlignment(this.val$alignment);
        }
      }
    });
  }
  
  public static void showSimpleTapjoyFeatureAd()
  {
    if (mUSE_TAPJOY) {
      ((Activity)mContext).runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (cocojava.mFeaturedApObject != null)
          {
            cocojava.showTJOfferDialog();
            return;
          }
          cocojava.showTapFeatureAd();
        }
      });
    }
  }
  
  public static void showTJOfferDialog()
  {
    if (!mUSE_ADS) {}
    while ((mFeaturedApObject == null) || (!mUSE_TAPJOY)) {
      return;
    }
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        cocojava.mGLView.queueEvent(new Runnable()
        {
          public void run()
          {
            CocoJNI.MsetGamePause(1);
          }
        });
        newDialog.Builder localBuilder = new newDialog.Builder(cocojava.mContext);
        localBuilder.setTitle(((cocojava)cocojava.mContext).getTapjoyOfferDialogTitle(cocojava.mFeaturedApObject)).setMessage(((cocojava)cocojava.mContext).getTapjoyOfferDialogMessage(cocojava.mFeaturedApObject)).setCancelable(false).setPositiveButton("Later", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface.cancel();
            cocojava.showTapFeatureAd();
            cocojava.mGLView.queueEvent(new Runnable()
            {
              public void run()
              {
                CocoJNI.MinterstitialClosed(0);
                CocoJNI.MsetGamePause(0);
              }
            });
            ((cocojava)cocojava.mContext).googleTracker.trackEvent("ClickEvents", "Clicked", "TapJoy", 0);
            ((cocojava)cocojava.mContext).googleTracker.dispatch();
          }
        }).setNegativeButton("Download now", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface.cancel();
            cocojava.showTapFeatureAd();
            cocojava.openLink(cocojava.mFeaturedApObject.redirectURL);
            cocojava.mGLView.queueEvent(new Runnable()
            {
              public void run()
              {
                CocoJNI.MinterstitialClosed(1);
                CocoJNI.MsetGamePause(0);
              }
            });
            ((cocojava)cocojava.mContext).googleTracker.trackEvent("ClickEvents", "Clicked", "TapJoy", 1);
            ((cocojava)cocojava.mContext).googleTracker.dispatch();
          }
        });
        localBuilder.create().show();
      }
    });
  }
  
  public static void showTapFeatureAd()
  {
    if (mUSE_TAPJOY) {
      ((Activity)mContext).runOnUiThread(new Runnable()
      {
        public void run()
        {
          TapjoyConnect.getTapjoyConnectInstance().getFeaturedApp((TapjoyFeaturedAppNotifier)cocojava.mContext);
        }
      });
    }
  }
  
  public static void showTapJoyOffers()
  {
    if (mUSE_TAPJOY) {
      ((Activity)mContext).runOnUiThread(new Runnable()
      {
        public void run()
        {
          TapjoyConnect.getTapjoyConnectInstance().showOffers();
        }
      });
    }
  }
  
  public static void showTapJoyView(TapjoyFeaturedAppObject paramTapjoyFeaturedAppObject)
  {
    mFeaturedApObject = paramTapjoyFeaturedAppObject;
  }
  
  protected static void showUpSellDialog()
  {
    ((cocojava)mContext).showUpSellDialogInternal();
  }
  
  public static void spendTapPoints(int paramInt)
  {
    if (mUSE_TAPJOY) {
      ((Activity)mContext).runOnUiThread(new Runnable()
      {
        public void run()
        {
          TapjoyConnect.getTapjoyConnectInstance().spendTapPoints(this.val$points, null);
        }
      });
    }
  }
  
  public static void startFlurrySession(String paramString)
  {
    Log.i("flurry", String.format("startFlurrySession %s", new Object[] { paramString }));
    FlurryAgent.onStartSession(mContext, paramString);
  }
  
  public static void stopBackgroundMusic()
  {
    backgroundMusicPlayer.stopBackgroundMusic();
  }
  
  public static void stopEffect(int paramInt)
  {
    soundPlayer.stopEffect(paramInt);
  }
  
  protected static JSONObject stringToJSON(String paramString)
  {
    try
    {
      paramString = new JSONObject(paramString);
      return paramString;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static void tapjoy_showTapFeatureAdFailed()
  {
    mFeaturedApObject = null;
  }
  
  public static void unloadEffect(String paramString)
  {
    soundPlayer.unloadEffect(paramString);
  }
  
  static void updateNotificationStatus(int paramInt)
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        SharedPreferences.Editor localEditor = cocojava.mContext.getSharedPreferences("NewsfeedPrefsC2DM", 0).edit();
        localEditor.putInt("C2DM_ENABLE", this.val$enabled);
        localEditor.commit();
      }
    });
  }
  
  public static void vibrateForMS(int paramInt)
  {
    ((Vibrator)mContext.getSystemService("vibrator")).vibrate(paramInt);
  }
  
  public void OnAdClicked(MoPubView paramMoPubView)
  {
    if (!mUSE_ADS) {
      return;
    }
    Log.i("OnAdClicked", "Ad Clicked");
    mGLView.queueEvent(new Runnable()
    {
      public void run() {}
    });
  }
  
  public void OnAdFailed(MoPubView paramMoPubView)
  {
    if (!mUSE_ADS) {
      return;
    }
    Log.i("OnAdFailed", "Failed to load the ad");
    mGLView.queueEvent(new Runnable()
    {
      public void run()
      {
        CocoJNI.MsetAdLoadFailed(1);
      }
    });
  }
  
  public void OnAdLoaded(final MoPubView paramMoPubView)
  {
    if (!mUSE_ADS) {
      return;
    }
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      @SuppressLint({"NewApi"})
      public void run()
      {
        MoPubView localMoPubView = cocojava.adViewMenu;
        if (paramMoPubView.getAdUnitId().compareTo(((cocojava)cocojava.mContext).getMoPubGameplayBannerId()) == 0) {
          localMoPubView = cocojava.adViewGame;
        }
        if (Build.VERSION.SDK_INT > 10) {
          localMoPubView.setLayerType(1, null);
        }
        if ((localMoPubView != null) && (paramMoPubView == localMoPubView) && (localMoPubView.getVisibility() == 0))
        {
          ScaleAnimation localScaleAnimation = new ScaleAnimation(0.0F, 1.0F, 1.0F, 1.0F, 160.0F * cocojava.mDensity, 0.0F);
          localScaleAnimation.setDuration(500L);
          localMoPubView.setVisibility(1);
          localMoPubView.startAnimation(localScaleAnimation);
        }
      }
    });
    mGLView.queueEvent(new Runnable()
    {
      public void run()
      {
        CocoJNI.MsetAdLoadFailed(0);
      }
    });
  }
  
  public void OnInterstitialFailed()
  {
    Toast.makeText(this, "No ad available", 0).show();
  }
  
  public void OnInterstitialLoaded() {}
  
  int buttonValue2Int(int paramInt)
  {
    int j = 0;
    int i = j;
    switch (paramInt)
    {
    default: 
      i = j;
      if (!$assertionsDisabled) {
        throw new AssertionError();
      }
    case -2: 
      i = 1;
    case -1: 
      return i;
    }
    return 2;
  }
  
  protected void callGetJar(String paramString1, int paramInt1, String paramString2, int paramInt2, int paramInt3, int paramInt4)
  {
    Log.i("cocojava", "ERROR! OVERRIDE ME!");
  }
  
  public int callInAppPurchaseCustom(String paramString)
  {
    Log.i("callInAppPurchaseCustom", "ERROR! OVERRIDE ME!");
    return 0;
  }
  
  public int callInAppPurchaseManagedCustom(String paramString)
  {
    Log.i("callInAppPurchaseManagedCustom", "ERROR! OVERRIDE ME!");
    return 0;
  }
  
  protected void callRemoveAds() {}
  
  void closeDialog(int paramInt)
  {
    if (this.mDialogs.containsKey(Integer.valueOf(paramInt)))
    {
      Dialog localDialog = (Dialog)this.mDialogs.get(Integer.valueOf(paramInt));
      if (localDialog != null) {
        localDialog.dismiss();
      }
      this.mDialogs.remove(Integer.valueOf(paramInt));
    }
  }
  
  void createResources()
  {
    Log.i("JAVAINFO", getFilesDir().getAbsolutePath());
    File localFile = new File(getFilesDir(), "Contents/Resources");
    Log.i("JAVAINFO", localFile.getAbsolutePath());
    localFile.mkdirs();
    if (mUpdateRunableCall) {
      mUpdateRunable.run();
    }
    try
    {
      Object localObject1 = getPackageManager().getApplicationInfo(getPackageName(), 0);
      CocoJNI.MsetAPKPath(((ApplicationInfo)localObject1).sourceDir);
      Log.i("JAVAINFO", ((ApplicationInfo)localObject1).sourceDir);
      Log.i("JAVAINFO", ((ApplicationInfo)localObject1).dataDir);
      localObject1 = new ZipFile(((ApplicationInfo)localObject1).sourceDir);
      Enumeration localEnumeration = ((ZipFile)localObject1).entries();
      while (localEnumeration.hasMoreElements())
      {
        localObject3 = (ZipEntry)localEnumeration.nextElement();
        if ((((ZipEntry)localObject3).getName().length() >= 15) && ("assets/unpack/".equals(((ZipEntry)localObject3).getName().substring(0, 14))))
        {
          localObject2 = new File(localFile, ((ZipEntry)localObject3).getName().substring(14));
          localObject4 = ((File)localObject2).getParentFile();
          if (localObject4 != null) {
            ((File)localObject4).mkdirs();
          }
          if (!((File)localObject2).exists()) {
            localObject2 = ((ZipFile)localObject1).getInputStream((ZipEntry)localObject3);
          }
        }
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        try
        {
          Object localObject2;
          localObject3 = new File(localFile, ((ZipEntry)localObject3).getName().substring(14));
          ((File)localObject3).delete();
          localObject3 = new FileOutputStream((File)localObject3);
          Object localObject4 = new byte['က'];
          int i = ((InputStream)localObject2).read((byte[])localObject4);
          if (i >= 0) {
            ((FileOutputStream)localObject3).write((byte[])localObject4, 0, i);
          }
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          Object localObject3;
          localFileNotFoundException.printStackTrace();
          continue;
          localNameNotFoundException = localNameNotFoundException;
          localNameNotFoundException.printStackTrace();
          return;
          ((FileOutputStream)localObject3).close();
        }
        catch (IOException localIOException2)
        {
          localIOException2.printStackTrace();
        }
      }
    }
    catch (IOException localIOException1)
    {
      localIOException1.printStackTrace();
    }
  }
  
  public void displayErrorMessage(String paramString1, String paramString2)
  {
    AlertDialog localAlertDialog = new AlertDialog.Builder(mContext).create();
    localAlertDialog.setTitle(paramString1);
    localAlertDialog.setMessage(paramString2);
    localAlertDialog.setButton(-1, "Ok", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
        System.exit(0);
      }
    });
    localAlertDialog.show();
  }
  
  public void firstRun()
  {
    if (!this.isFirstRun) {
      return;
    }
    this.isFirstRun = false;
    Log.i("JAVAINFO", "firstRun");
    if (mUSE_DEVICEID) {
      mDeviceID = getDeviceId();
    }
    for (;;)
    {
      int i;
      int j;
      int k;
      Object localObject2;
      try
      {
        CocoJNI.MsetMainActivity(mContext);
        CocoJNI.MsetCountryCode(Locale.getDefault().toString());
        mFeaturedApObject = null;
        if (mUSE_ADS)
        {
          new MoPubConversionTracker().reportAppOpen(this);
          adViewGame = null;
          adViewMenu = null;
          mBigAdView = null;
          mInterstitialBanner = null;
          mFullscreenInterstitial = null;
          mRotatedBanner = null;
          mHorizontalBanner = null;
        }
        CocoJNI.MsetAppVersionNumber(getAppVersionNumber());
        if (getFilesDir() == null) {
          new File("/data/data/" + getPackageName() + "/files/").mkdirs();
        }
        CocoJNI.MsetFilesPath(getFilesDir().getAbsolutePath(), getExternalStorageDirectory());
        Log.i("Files Path", getFilesDir().getAbsolutePath());
        mWidth = getResources().getDisplayMetrics().widthPixels;
        mHeight = getResources().getDisplayMetrics().heightPixels;
        if (mMinimumResolutionSD)
        {
          if (!mINGAME_LANDSCAPE)
          {
            mWidth = 320;
            mHeight = 480;
          }
        }
        else
        {
          mDensity = getResources().getDisplayMetrics().density;
          Log.i("Dim", String.format("width: %d, height: %d, density: %f", new Object[] { Integer.valueOf(mWidth), Integer.valueOf(mHeight), Float.valueOf(mDensity) }));
          CocoJNI.MsetDensity(mDensity);
          CocoJNI.MdisplayInfo(mWidth, mHeight);
          backgroundMusicPlayer = new cocomusic(this);
          soundPlayer = new cocosound(this);
          accelerometer = new cocoaccel(this);
          enableAccelerometer();
          texture = new cocotexture(this);
          mGLView = new ClearGLSurfaceView(this);
          mRenderer = new ClearRenderer();
          mRenderer.setContext(mContext);
          mGLView.setRenderer(mRenderer);
          adLayout = new RelativeLayout(this);
          adLayout.setMinimumWidth(mWidth);
          adLayout.setMinimumHeight(mHeight);
          adLayoutH = new RelativeLayout(this);
          Object localObject1 = new RelativeLayout.LayoutParams(-1, -1);
          adLayoutH.setLayoutParams((ViewGroup.LayoutParams)localObject1);
          adLayoutH.addView(adLayout);
          layout = new RelativeLayout(this);
          layout.setMinimumWidth(mWidth);
          layout.setMinimumHeight(mHeight);
          if (!mUSE_ADS_VERTICAL) {
            break label1785;
          }
          i = mContext.getResources().getIdentifier("side1", "drawable", mContext.getPackageName());
          j = mContext.getResources().getIdentifier("side2", "drawable", mContext.getPackageName());
          k = mContext.getResources().getIdentifier("side3", "drawable", mContext.getPackageName());
          mContext.getResources().getIdentifier("a100x640", "drawable", mContext.getPackageName());
          sideBar1 = new RelativeLayout(this);
          localObject1 = new RelativeLayout.LayoutParams((int)(50.0F * mDensity), (int)((mHeight - 320.0F * mDensity) * 0.5F));
          sideBar1.setLayoutParams((ViewGroup.LayoutParams)localObject1);
          localObject1 = new ImageView(this);
          ((ImageView)localObject1).setImageDrawable(getResources().getDrawable(j));
          ((ImageView)localObject1).setLayoutParams(new Gallery.LayoutParams(-1, -1));
          ((ImageView)localObject1).setScaleType(ImageView.ScaleType.FIT_XY);
          sideBar1.addView((View)localObject1);
          localObject1 = new ImageView(this);
          ((ImageView)localObject1).setImageDrawable(getResources().getDrawable(i));
          ((ImageView)localObject1).setLayoutParams(new Gallery.LayoutParams(-1, (int)(20.0F * mDensity)));
          ((ImageView)localObject1).setScaleType(ImageView.ScaleType.FIT_XY);
          sideBar1.addView((View)localObject1);
          localObject1 = new RelativeLayout(this);
          localObject2 = new RelativeLayout.LayoutParams(-1, (int)(20.0F * mDensity));
          ((RelativeLayout.LayoutParams)localObject2).addRule(12);
          ((RelativeLayout)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
          localObject2 = new ImageView(this);
          ((ImageView)localObject2).setImageDrawable(getResources().getDrawable(k));
          ((ImageView)localObject2).setLayoutParams(new Gallery.LayoutParams(-1, (int)(20.0F * mDensity)));
          ((ImageView)localObject2).setScaleType(ImageView.ScaleType.FIT_XY);
          ((RelativeLayout)localObject1).addView((View)localObject2);
          sideBar1.addView((View)localObject1);
          sideBar2 = new RelativeLayout(this);
          localObject1 = new RelativeLayout.LayoutParams((int)(50.0D * mDensity), (int)((mHeight - 320.0F * mDensity) * 0.5F));
          ((RelativeLayout.LayoutParams)localObject1).addRule(12);
          sideBar2.setLayoutParams((ViewGroup.LayoutParams)localObject1);
          localObject1 = new ImageView(this);
          ((ImageView)localObject1).setImageDrawable(getResources().getDrawable(j));
          ((ImageView)localObject1).setLayoutParams(new Gallery.LayoutParams(-1, -1));
          ((ImageView)localObject1).setScaleType(ImageView.ScaleType.FIT_XY);
          sideBar2.addView((View)localObject1);
          localObject1 = new RelativeLayout(this);
          localObject2 = new RelativeLayout.LayoutParams(-1, (int)(20.0F * mDensity));
          ((RelativeLayout.LayoutParams)localObject2).addRule(12);
          ((RelativeLayout)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
          localObject2 = new ImageView(this);
          ((ImageView)localObject2).setImageDrawable(getResources().getDrawable(k));
          ((ImageView)localObject2).setLayoutParams(new Gallery.LayoutParams(-1, (int)(20.0F * mDensity)));
          ((ImageView)localObject2).setScaleType(ImageView.ScaleType.FIT_XY);
          ((RelativeLayout)localObject1).addView((View)localObject2);
          sideBar2.addView((View)localObject1);
          localObject1 = new ImageView(this);
          ((ImageView)localObject1).setImageDrawable(getResources().getDrawable(i));
          ((ImageView)localObject1).setLayoutParams(new Gallery.LayoutParams(-1, (int)(20.0F * mDensity)));
          ((ImageView)localObject1).setScaleType(ImageView.ScaleType.FIT_XY);
          sideBar2.addView((View)localObject1);
          mRotatedBanner = new rotatedBannerImg(this, ALIGN_LEFT);
          layout.addView(mRotatedBanner);
          layout.addView(sideBar1);
          layout.addView(sideBar2);
          mRotatedBanner.setBlockAutoRefresh(false);
          mRotatedBanner.setAutorefreshEnabled(false);
          mRotatedBanner.setBlockAutoRefresh(true);
          layout.addView(mGLView);
          layout.addView(adLayoutH);
          layout.addView(mInitView);
          setContentView(layout);
          showTapFeatureAd();
          if ((!mUSE_ADS_BIG) || ((!mUSE_ADS) || (mUSE_ADS_INTERSTITIAL_FULLSCREEN)))
          {
            mFullscreenInterstitial = new interstitialMopubView(mContext);
            mFullscreenInterstitial.loadAd();
            layout.addView(mFullscreenInterstitial);
          }
          if (mUSE_ADS_INTERSTITIAL_BANNER)
          {
            mInterstitialBanner = new interstitialBanner(this);
            mInterstitialBanner.loadAd();
            layout.addView(mInterstitialBanner);
          }
          mHandler = new Handler()
          {
            public void handleMessage(Message paramAnonymousMessage)
            {
              switch (paramAnonymousMessage.what)
              {
              default: 
                return;
              case 1: 
                paramAnonymousMessage = (DialogMessage)paramAnonymousMessage.obj;
                cocojava.this.showDialog(paramAnonymousMessage.msgId, paramAnonymousMessage.title, paramAnonymousMessage.message, paramAnonymousMessage.buttonTitles);
                return;
              case 2: 
                paramAnonymousMessage = (DialogMessage)paramAnonymousMessage.obj;
                cocojava.this.hideDialog(paramAnonymousMessage.msgId);
                return;
              case 3: 
                paramAnonymousMessage = (DialogMessage)paramAnonymousMessage.obj;
                cocojava.this.closeDialog(paramAnonymousMessage.msgId);
                return;
              case 4: 
                cocojava.this.openURL((String)paramAnonymousMessage.obj);
                return;
              }
              cocojava.this.finish();
            }
          };
          if (mEditText == null)
          {
            mEditText = new EditText(mContext)
            {
              public boolean onKeyPreIme(int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
              {
                if (paramAnonymousInt == 4)
                {
                  ((InputMethodManager)cocojava.mContext.getSystemService("input_method")).hideSoftInputFromWindow(getWindowToken(), 0);
                  if (!cocojava.mKEYBOARD_OVERRIDE_VISIBILITY) {
                    cocojava.mEditText.setVisibility(4);
                  }
                }
                return true;
              }
            };
            if (mKEYBOARD_INPUT_HIDE)
            {
              localObject1 = new RelativeLayout.LayoutParams(1, 1);
              mEditText.setLayoutParams((ViewGroup.LayoutParams)localObject1);
            }
            mEditText.setImeOptions(6);
            mEditText.addTextChangedListener(new TextWatcher()
            {
              public void afterTextChanged(Editable paramAnonymousEditable) {}
              
              public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
              
              public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
              {
                cocojava.mGLView.queueEvent(new Runnable()
                {
                  public void run()
                  {
                    CocoJNI.MkeyboardInputResponse(cocojava.mEditText.getText().toString());
                  }
                });
              }
            });
            mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener()
            {
              public boolean onEditorAction(TextView paramAnonymousTextView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
              {
                if ((paramAnonymousInt == 6) || (paramAnonymousKeyEvent.getKeyCode() == 66) || (paramAnonymousInt == 5)) {
                  ((Activity)cocojava.mContext).runOnUiThread(new Runnable()
                  {
                    public void run()
                    {
                      if (!cocojava.mKEYBOARD_OVERRIDE_VISIBILITY) {
                        cocojava.mEditText.setVisibility(4);
                      }
                      ((InputMethodManager)cocojava.mContext.getSystemService("input_method")).hideSoftInputFromWindow(cocojava.mEditText.getWindowToken(), 0);
                    }
                  });
                }
                cocojava.mGLView.queueEvent(new Runnable()
                {
                  public void run()
                  {
                    CocoJNI.MkeyboardInputClosed(cocojava.mEditText.getText().toString());
                  }
                });
                return false;
              }
            });
            mEditText.setWidth(2000);
            if (!mKEYBOARD_OVERRIDE_VISIBILITY) {
              mEditText.setVisibility(4);
            }
            if (!mSHOW_KEYBOARD_INPUT) {
              break label2465;
            }
            layout.addView(mEditText);
            if (!mKEYBOARD_FULLSCREEN) {
              mEditText.setImeOptions(268435462);
            }
            mEditText.setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymousView) {}
            });
            if (mKEYBOARD_CUSTOM_BACKGROUND)
            {
              i = mContext.getResources().getIdentifier("roundsquare", "drawable", mContext.getPackageName());
              mEditText.setBackgroundDrawable(getResources().getDrawable(i));
            }
          }
          this.mDialogs = new HashMap();
          isSDcardPresent();
          availableMemoryOnDevice();
          availableMemoryOnSDcard();
          if (mUSE_REMOVE_ADS)
          {
            float f = getResources().getDisplayMetrics().heightPixels / 480.0F;
            localObject1 = new RelativeLayout.LayoutParams((int)(100.0F * f), (int)(59.0F * f));
            ((RelativeLayout.LayoutParams)localObject1).addRule(12);
            ((RelativeLayout.LayoutParams)localObject1).leftMargin = ((int)(5.0F * f));
            ((RelativeLayout.LayoutParams)localObject1).bottomMargin = ((int)(200.0F * f));
            mRemoveAdsButton = new ImageView(this);
            i = getResources().getIdentifier("remove_ads", "drawable", getPackageName());
            mRemoveAdsButton.setImageDrawable(getResources().getDrawable(i));
            mRemoveAdsButton.setOnClickListener(this);
            mRemoveAdsButton.setLayoutParams((ViewGroup.LayoutParams)localObject1);
            mRemoveAdsButtonLayout = new RelativeLayout(this);
            layout.addView(mRemoveAdsButtonLayout);
          }
          if (mUSE_NEWSFEED)
          {
            mCurrentNewsfeed = new Newsfeed(mContext, mDeviceID);
            new Handler().postDelayed(new Runnable()
            {
              public void run()
              {
                cocojava.mCurrentNewsfeed.update();
              }
            }, 2000L);
          }
          setEffectsVolume(1.0F);
          return;
        }
      }
      catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
      {
        Log.i("JAVAINFO", "Library Load Failed");
        localUnsatisfiedLinkError.printStackTrace();
        displayErrorMessage("Unexpected Error", "Please reinstall the game.\nIf the problem persists, please contact us at support@miniclip.com");
        mSERIOUS_ERROR_HAPPENED = true;
        return;
      }
      mWidth = 480;
      mHeight = 320;
      continue;
      label1785:
      if (mUSE_ADS_HORIZONTAL)
      {
        i = mContext.getResources().getIdentifier("side1", "drawable", mContext.getPackageName());
        j = mContext.getResources().getIdentifier("side2", "drawable", mContext.getPackageName());
        k = mContext.getResources().getIdentifier("side3", "drawable", mContext.getPackageName());
        RelativeLayout localRelativeLayout = new RelativeLayout(this);
        localRelativeLayout.setLayoutParams(new RelativeLayout.LayoutParams((int)((mWidth - 320.0F * mDensity) * 0.5F), (int)(50.0D * mDensity)));
        localObject2 = new ImageView(this);
        ((ImageView)localObject2).setImageDrawable(getResources().getDrawable(j));
        ((ImageView)localObject2).setLayoutParams(new Gallery.LayoutParams(-1, -1));
        ((ImageView)localObject2).setScaleType(ImageView.ScaleType.FIT_XY);
        localRelativeLayout.addView((View)localObject2);
        localObject2 = new ImageView(this);
        ((ImageView)localObject2).setImageDrawable(getResources().getDrawable(i));
        ((ImageView)localObject2).setLayoutParams(new Gallery.LayoutParams((int)(20.0F * mDensity), -1));
        ((ImageView)localObject2).setScaleType(ImageView.ScaleType.FIT_XY);
        localRelativeLayout.addView((View)localObject2);
        localObject2 = new RelativeLayout(this);
        Object localObject3 = new RelativeLayout.LayoutParams((int)(20.0F * mDensity), -1);
        ((RelativeLayout.LayoutParams)localObject3).addRule(11);
        ((RelativeLayout)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject3);
        localObject3 = new ImageView(this);
        ((ImageView)localObject3).setImageDrawable(getResources().getDrawable(k));
        ((ImageView)localObject3).setLayoutParams(new Gallery.LayoutParams((int)(20.0F * mDensity), -1));
        ((ImageView)localObject3).setScaleType(ImageView.ScaleType.FIT_XY);
        ((RelativeLayout)localObject2).addView((View)localObject3);
        localRelativeLayout.addView((View)localObject2);
        localObject2 = new RelativeLayout(this);
        localObject3 = new RelativeLayout.LayoutParams((int)((mWidth - 320.0F * mDensity) * 0.5F), (int)(50.0D * mDensity));
        ((RelativeLayout.LayoutParams)localObject3).addRule(11);
        ((RelativeLayout)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject3);
        localObject3 = new ImageView(this);
        ((ImageView)localObject3).setImageDrawable(getResources().getDrawable(j));
        ((ImageView)localObject3).setLayoutParams(new Gallery.LayoutParams(-1, -1));
        ((ImageView)localObject3).setScaleType(ImageView.ScaleType.FIT_XY);
        ((RelativeLayout)localObject2).addView((View)localObject3);
        localObject3 = new RelativeLayout(this);
        Object localObject4 = new RelativeLayout.LayoutParams((int)(20.0F * mDensity), -1);
        ((RelativeLayout.LayoutParams)localObject4).addRule(11);
        ((RelativeLayout)localObject3).setLayoutParams((ViewGroup.LayoutParams)localObject4);
        localObject4 = new ImageView(this);
        ((ImageView)localObject4).setImageDrawable(getResources().getDrawable(k));
        ((ImageView)localObject4).setLayoutParams(new Gallery.LayoutParams((int)(20.0F * mDensity), -1));
        ((ImageView)localObject4).setScaleType(ImageView.ScaleType.FIT_XY);
        ((RelativeLayout)localObject3).addView((View)localObject4);
        ((RelativeLayout)localObject2).addView((View)localObject3);
        localObject3 = new ImageView(this);
        ((ImageView)localObject3).setImageDrawable(getResources().getDrawable(i));
        ((ImageView)localObject3).setLayoutParams(new Gallery.LayoutParams((int)(20.0F * mDensity), -1));
        ((ImageView)localObject3).setScaleType(ImageView.ScaleType.FIT_XY);
        ((RelativeLayout)localObject2).addView((View)localObject3);
        mHorizontalBanner = new horizontalBanner(this);
        layout.addView(localRelativeLayout);
        layout.addView((View)localObject2);
        layout.addView(mHorizontalBanner);
        mHorizontalBanner.setBlockAutoRefresh(false);
        mHorizontalBanner.setAutorefreshEnabled(false);
        mHorizontalBanner.setBlockAutoRefresh(true);
        continue;
        label2465:
        if (mKEYBOARD_INPUT_SINGLE_LINE) {
          mEditText.setMaxLines(1);
        }
        localRelativeLayout = new RelativeLayout(mContext);
        localRelativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        localRelativeLayout.addView(mEditText);
        i = Build.VERSION.SDK_INT;
        layout.addView(localRelativeLayout);
      }
    }
  }
  
  public void fixScreen()
  {
    Runnable local55 = new Runnable()
    {
      public void run()
      {
        Log.i("cocojava", "fixScreen");
        cocojava.layout.requestLayout();
      }
    };
    this.mScreenFixHandler.removeCallbacks(local55);
    this.mScreenFixHandler.postDelayed(local55, 1000L);
  }
  
  protected String getAnalyticsID()
  {
    return "ERROR! OVERRIDE ME!";
  }
  
  protected String getFullAppURI()
  {
    return "ERROR! OVERRIDE ME!";
  }
  
  protected String getFullVersionGameImageId()
  {
    return "ERROR! OVERRIDE ME!";
  }
  
  protected String[] getInAppSkus()
  {
    Log.i("cocojava", "getInAppSkus: Override to get price info.");
    return new String[0];
  }
  
  protected int getJarRecommendedPrice(int paramInt)
  {
    Log.i("cocojava", "ERROR! OVERRIDE ME!");
    return 0;
  }
  
  protected String getMoPubBannerId()
  {
    return "ERROR! OVERRIDE ME!";
  }
  
  protected String getMoPubFullScreenInterstitialId()
  {
    return "ERROR! OVERRIDE ME!";
  }
  
  protected String getMoPubGameplayBannerId()
  {
    return "ERROR! OVERRIDE ME!";
  }
  
  protected String getMoPubInterstitialBannerId()
  {
    return "ERROR! OVERRIDE ME!";
  }
  
  protected String getMoPubInterstitialId()
  {
    return "ERROR! OVERRIDE ME!";
  }
  
  protected String getMoPubMenuBannerId()
  {
    return "ERROR! OVERRIDE ME!";
  }
  
  protected String getTapJoyHtmlOffer(TapjoyFeaturedAppObject paramTapjoyFeaturedAppObject)
  {
    return "ERROR! OVERRIDE ME!";
  }
  
  protected String getTapjoyOfferDialogMessage(TapjoyFeaturedAppObject paramTapjoyFeaturedAppObject)
  {
    return String.format("Download and run this app for free stuff:\n%s", new Object[] { paramTapjoyFeaturedAppObject.name });
  }
  
  protected String getTapjoyOfferDialogTitle(TapjoyFeaturedAppObject paramTapjoyFeaturedAppObject)
  {
    return "Do you want to get free stuff?";
  }
  
  protected void getUpSellDialogAction()
  {
    openLink("https://market.android.com/developer?pub=Miniclip.com");
  }
  
  protected String getUpSellDialogMessage()
  {
    return "Do you want to get the premium version?";
  }
  
  protected String getUpSellDialogTitle()
  {
    return "Upgrade to premium?";
  }
  
  public int googlePlayServicesIsSignedInCustom()
  {
    Log.i("cocojava", "OVERRIDE googlePlayServicesIsSignedInCustom");
    return 0;
  }
  
  public void googlePlayServicesLoginCustom()
  {
    Log.i("cocojava", "OVERRIDE googlePlayServicesLoginCustom");
  }
  
  public void googlePlayServicesLogoutCustom()
  {
    Log.i("cocojava", "OVERRIDE googlePlayServicesLoginCustom");
  }
  
  void hideDialog(int paramInt)
  {
    if (this.mDialogs.containsKey(Integer.valueOf(paramInt)))
    {
      Dialog localDialog = (Dialog)this.mDialogs.get(Integer.valueOf(paramInt));
      if (localDialog != null) {
        localDialog.hide();
      }
    }
  }
  
  public void inAppResponce(int paramInt, String paramString)
  {
    Log.i("inAppResponce", String.format("id: %s responce: %d", new Object[] { paramString, Integer.valueOf(paramInt) }));
    if (paramInt == 1)
    {
      FlurryAgent.logEvent(String.format("InAppSuccess: %s", new Object[] { paramString }));
      if ((inAppsRemoveAds) && (getAdsDisabled() == 0))
      {
        Log.i("callInAppPurchase", "Removing ads due to IAP!");
        permanentlyRemoveAds();
        restoreOriginalAspectRatio();
      }
      return;
    }
    FlurryAgent.logEvent(String.format("InAppFailed: %s", new Object[] { paramString }));
  }
  
  int int2ButtonValue(int paramInt)
  {
    int j = -2;
    int i = j;
    switch (paramInt)
    {
    default: 
      i = j;
      if (!$assertionsDisabled) {
        throw new AssertionError();
      }
    case 0: 
      i = -1;
    case 1: 
      return i;
    }
    return -3;
  }
  
  public boolean isOnline()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
    if (localNetworkInfo == null) {
      return false;
    }
    return localNetworkInfo.isConnectedOrConnecting();
  }
  
  public void logCustomEvent(String paramString1, String paramString2)
  {
    Log.i("cocojava", "OVERRIDE logCustomEvent");
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (mUSE_FACEBOOK) {
      mFB_Session.onActivityResult(this, paramInt1, paramInt2, paramIntent);
    }
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    Iterator localIterator = this.mDialogs.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (localEntry.getValue() == paramDialogInterface) {
        CocoJNI.MonMessageBoxButtonPressed(((Integer)localEntry.getKey()).intValue(), buttonValue2Int(paramInt));
      }
    }
  }
  
  public void onClick(View paramView)
  {
    if (paramView == mRemoveAdsButton) {
      callInAppPurchaseManaged("remove_ads", 0, 0);
    }
    if (paramView == mSkip1)
    {
      mBigAdView.removeAllViews();
      mBigAdView.setOnClickListener(null);
      mBigAdView.setClickable(false);
      mGLView.queueEvent(new Runnable()
      {
        public void run()
        {
          CocoJNI.MsetGamePause(0);
        }
      });
    }
    do
    {
      return;
      if (paramView == mRemove1)
      {
        mBigAdView.removeAllViews();
        mBigAdView.setOnClickListener(null);
        mBigAdView.setClickable(false);
        paramView = new Intent("android.intent.action.VIEW");
        paramView.setData(Uri.parse(getFullAppURI()));
        mContext.startActivity(paramView);
        mGLView.queueEvent(new Runnable()
        {
          public void run()
          {
            CocoJNI.MsetGamePause(0);
          }
        });
        return;
      }
    } while (paramView != mCenterdAd);
    mBigAdView.removeAllViews();
    mBigAdView.setOnClickListener(null);
    mBigAdView.setClickable(false);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    mContext = this;
    if (mUSE_FACEBOOK)
    {
      mFB_Session = Session.openActiveSessionFromCache(mContext);
      if (mFB_Session == null)
      {
        Log.i("cocojava", "FB: Cannot open session from cache");
        mFB_Session = new Session(mContext);
      }
    }
    this.googleTracker = GoogleAnalyticsTracker.getInstance();
    this.googleTracker.startNewSession(getAnalyticsID(), this);
    this.googleTracker.trackPageView("/app_started-" + getPackageName());
    this.googleTracker.dispatch();
    setVolumeControlStream(3);
    mInitView = new RelativeLayout(this);
    paramBundle = new RelativeLayout.LayoutParams(-1, -1);
    mInitView.setLayoutParams(paramBundle);
    mInitView.setPadding(0, 0, 0, 0);
    if (mContext.getResources().getIdentifier("default2", "drawable", mContext.getPackageName()) != 0) {
      mHasSecondIntro = true;
    }
    paramBundle = new ImageView(this);
    int i = mContext.getResources().getIdentifier("default1", "drawable", mContext.getPackageName());
    paramBundle.setImageDrawable(getResources().getDrawable(i));
    paramBundle.setLayoutParams(new Gallery.LayoutParams(-1, -1));
    paramBundle.setScaleType(ImageView.ScaleType.FIT_XY);
    mInitView.addView(paramBundle);
    float f = getResources().getDisplayMetrics().density;
    f = getResources().getDisplayMetrics().widthPixels;
    f = getResources().getDisplayMetrics().heightPixels;
    paramBundle = new RelativeLayout(this);
    Object localObject = new RelativeLayout.LayoutParams((int)(f / 2.0F), (int)(f / 2.0F));
    ((RelativeLayout.LayoutParams)localObject).addRule(14);
    ((RelativeLayout.LayoutParams)localObject).addRule(12);
    paramBundle.setLayoutParams((ViewGroup.LayoutParams)localObject);
    mInitView.addView(paramBundle);
    if (mSPINNING_ANIMATION)
    {
      localObject = new ImageView(this);
      i = mContext.getResources().getIdentifier("spinning_ball", "drawable", mContext.getPackageName());
      ((ImageView)localObject).setImageDrawable(getResources().getDrawable(i));
      RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams((int)(f / 10.0F), (int)(f / 10.0F));
      localLayoutParams.addRule(13);
      ((ImageView)localObject).setLayoutParams(localLayoutParams);
      ((ImageView)localObject).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
      paramBundle.addView((View)localObject);
      localObject = new ImageView(this);
      i = mContext.getResources().getIdentifier("spinning_ball_effect", "drawable", mContext.getPackageName());
      ((ImageView)localObject).setImageDrawable(getResources().getDrawable(i));
      localLayoutParams = new RelativeLayout.LayoutParams((int)(f / 8.0F), (int)(f / 8.0F));
      localLayoutParams.addRule(13);
      ((ImageView)localObject).setLayoutParams(localLayoutParams);
      ((ImageView)localObject).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
      paramBundle.addView((View)localObject);
      paramBundle = new RotateAnimation(0.0F, 360.0F, (int)(f / 8.0F) / 2, (int)(f / 8.0F) / 2);
      paramBundle.setDuration(1000L);
      paramBundle.setRepeatCount(-1);
      paramBundle.setInterpolator(new LinearInterpolator());
      paramBundle.setFillAfter(true);
      ((ImageView)localObject).startAnimation(paramBundle);
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.googleTracker.stopSession();
    System.runFinalizersOnExit(true);
    System.exit(0);
  }
  
  public void onInterstitialMopubViewExit()
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (cocojava.adLayout.getParent() != cocojava.adLayoutH) {
          cocojava.adLayoutH.addView(cocojava.adLayout);
        }
        cocojava.mGLView.queueEvent(new Runnable()
        {
          public void run()
          {
            CocoJNI.MsetGamePause(0);
          }
        });
      }
    });
  }
  
  public boolean onKeyDown(final int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    default: 
      return super.onKeyDown(paramInt, paramKeyEvent);
    case 4: 
      if (paramKeyEvent.isAltPressed())
      {
        mGLView.queueEvent(new Runnable()
        {
          public void run()
          {
            CocoJNI.MpressedKey(paramInt, 1);
          }
        });
        return true;
      }
      mGLView.queueEvent(new Runnable()
      {
        public void run() {}
      });
      return true;
    }
    mGLView.queueEvent(new Runnable()
    {
      public void run()
      {
        CocoJNI.MpressedKey(paramInt, 1);
      }
    });
    return true;
  }
  
  public boolean onKeyUp(final int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    default: 
      return super.onKeyUp(paramInt, paramKeyEvent);
    }
    mGLView.queueEvent(new Runnable()
    {
      public void run()
      {
        CocoJNI.MpressedKey(paramInt, 0);
      }
    });
    return true;
  }
  
  public void onLowMemory()
  {
    Log.i("MEMORY WARNING", "LOW MEMORY");
    mNumCrashes += 1;
    if (mNumCrashes >= 3)
    {
      mNumCrashes = 0;
      mGLView.queueEvent(new Runnable()
      {
        public void run() {}
      });
    }
  }
  
  protected void onPause()
  {
    super.onPause();
    accelerometer.disable();
    pauseBackgroundMusic();
    soundPlayer.pauseAllSounds();
    mGLView.onPause();
    if ((mRotatedBanner != null) && (mUSE_ADS_VERTICAL) && (mRotatedBannerDisplayed == 1))
    {
      mRotatedBanner.setBlockAutoRefresh(false);
      mRotatedBanner.setAutorefreshEnabled(false);
      mRotatedBanner.setBlockAutoRefresh(true);
    }
    if ((mHorizontalBanner != null) && (mUSE_ADS_HORIZONTAL) && (mHorizontalBannerDisplayed))
    {
      mHorizontalBanner.setBlockAutoRefresh(false);
      mHorizontalBanner.setAutorefreshEnabled(false);
      mHorizontalBanner.setBlockAutoRefresh(true);
    }
  }
  
  protected void onRealResume()
  {
    if (mUSE_TAPJOY) {
      TapjoyConnect.getTapjoyConnectInstance().getTapPoints(this);
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    if (mUSE_FACEBOOK)
    {
      Settings.publishInstallAsync(this, mFacebookAPP_ID);
      Log.i("Facebook", "Tracked installation.");
    }
    if (mHasWindowFocus)
    {
      mResumeOnFocus = false;
      accelerometer.enable();
      resumeBackgroundMusic();
      soundPlayer.resumeAllSounds();
      mGLView.onResume();
    }
    for (;;)
    {
      if ((mRenderer != null) && (mRenderer.hasStarted)) {
        onRealResume();
      }
      mNumUpSellsThisSession = 0;
      if ((mRotatedBanner != null) && (mUSE_ADS_VERTICAL) && (mRotatedBannerDisplayed == 1))
      {
        mRotatedBanner.setBlockAutoRefresh(false);
        mRotatedBanner.resetRefreshTime();
        mRotatedBanner.setAutorefreshEnabled(true);
        mRotatedBanner.setBlockAutoRefresh(true);
      }
      if ((mHorizontalBanner != null) && (mUSE_ADS_HORIZONTAL) && (mHorizontalBannerDisplayed))
      {
        mHorizontalBanner.setBlockAutoRefresh(false);
        mHorizontalBanner.setAutorefreshEnabled(false);
        mHorizontalBanner.setBlockAutoRefresh(true);
      }
      return;
      mResumeOnFocus = true;
    }
  }
  
  protected void onStart()
  {
    super.onStart();
    firstRun();
  }
  
  protected void onStop()
  {
    super.onStop();
    FlurryAgent.onEndSession(this);
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    mHasWindowFocus = paramBoolean;
    if ((mHasWindowFocus) && (mResumeOnFocus))
    {
      mResumeOnFocus = false;
      accelerometer.enable();
      resumeBackgroundMusic();
      soundPlayer.resumeAllSounds();
      mGLView.onResume();
    }
    if (mHasWindowFocus) {
      fixScreen();
    }
  }
  
  public void restoreOriginalAspectRatio()
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(cocojava.mWidth, cocojava.mHeight);
        localLayoutParams.addRule(11);
        cocojava.mGLView.setLayoutParams(localLayoutParams);
      }
    });
    mGLView.queueEvent(new Runnable()
    {
      public void run()
      {
        Log.i("INFO", String.format("RES FIX width:%d height:%d", new Object[] { Integer.valueOf(cocojava.mWidth), Integer.valueOf(cocojava.mHeight) }));
        GLES10.glViewport(0, 0, cocojava.mWidth, cocojava.mHeight);
        cocojava.mADS_BLOCKED_NOW = true;
        CocoJNI.MdisplayInfo(cocojava.mWidth, cocojava.mHeight);
        CocoJNI.MnotifyAspectRatioChange();
      }
    });
  }
  
  public void showAchievementsCustom()
  {
    Log.i("cocojava", "OVERRIDE showAchievementsCustom");
  }
  
  public void showDatePickerDialog(int paramInt1, int paramInt2, int paramInt3)
  {
    showDatePickerDialog(paramInt1, paramInt2, paramInt3, 0);
  }
  
  public void showDatePickerDialog(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = paramInt4;
    if (paramInt4 == 0) {
      i = mContext.getResources().getIdentifier("DatePicker", "style", mContext.getPackageName());
    }
    DatePickerDialog localDatePickerDialog = new DatePickerDialog(mContext, i, new DatePickerDialog.OnDateSetListener()
    {
      public void onDateSet(DatePicker paramAnonymousDatePicker, final int paramAnonymousInt1, final int paramAnonymousInt2, final int paramAnonymousInt3)
      {
        cocojava.mGLView.queueEvent(new Runnable()
        {
          public void run()
          {
            CocoJNI.MdatePickerResponce(paramAnonymousInt1, paramAnonymousInt2 + 1, paramAnonymousInt3);
            CocoJNI.MdatePickerClosed();
          }
        });
      }
    }, paramInt1, paramInt2 - 1, paramInt3);
    localDatePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        cocojava.mGLView.queueEvent(new Runnable()
        {
          public void run() {}
        });
      }
    });
    localDatePickerDialog.show();
  }
  
  void showDialog(int paramInt, String paramString1, String paramString2, String[] paramArrayOfString)
  {
    int i;
    if (!this.mDialogs.containsKey(Integer.valueOf(paramInt))) {
      if (paramArrayOfString.length < 4)
      {
        paramString1 = new newDialog.Builder(this).setTitle(paramString1).setMessage(paramString2);
        i = 0;
        if (i < paramArrayOfString.length)
        {
          if (int2ButtonValue(i) == -1) {
            paramString1.setPositiveButton(paramArrayOfString[i], this);
          }
          for (;;)
          {
            i += 1;
            break;
            if (int2ButtonValue(i) == -2) {
              paramString1.setNegativeButton(paramArrayOfString[i], this);
            } else if (int2ButtonValue(i) == -3) {
              paramString1.setNeutralButton(paramArrayOfString[i], this);
            }
          }
        }
        paramString1 = paramString1.create();
        this.mDialogs.put(Integer.valueOf(paramInt), paramString1);
      }
    }
    for (;;)
    {
      paramString1.show();
      return;
      AlertDialog localAlertDialog = new AlertDialog.Builder(this).create();
      localAlertDialog.setTitle(paramString1);
      ((AlertDialog)localAlertDialog).setMessage(paramString2);
      i = 0;
      for (;;)
      {
        paramString1 = localAlertDialog;
        if (i >= paramArrayOfString.length) {
          break;
        }
        ((AlertDialog)localAlertDialog).setButton(int2ButtonValue(i), paramArrayOfString[i], this);
        i += 1;
      }
      paramString1 = (Dialog)this.mDialogs.get(Integer.valueOf(paramInt));
    }
  }
  
  protected void showInterstitial() {}
  
  public void showInterstitialAd()
  {
    interstitial.setListener(this);
    interstitial.showAd();
  }
  
  public void showLeaderboardCustom(String paramString)
  {
    Log.i("cocojava", "OVERRIDE showLeaderboardCustom");
  }
  
  public void showLeaderboardsCustom()
  {
    Log.i("cocojava", "OVERRIDE showLeaderboardsCustom");
  }
  
  protected void showMiniclipViewInternal()
  {
    MiniclipUpsellView localMiniclipUpsellView = new MiniclipUpsellView(mContext);
    layout.removeView(localMiniclipUpsellView);
    layout.addView(localMiniclipUpsellView);
  }
  
  protected void showUpSellDialogInternal()
  {
    ((Activity)mContext).runOnUiThread(new Runnable()
    {
      public void run()
      {
        cocojava.mGLView.queueEvent(new Runnable()
        {
          public void run()
          {
            CocoJNI.MsetGamePause(1);
          }
        });
        newDialog.Builder localBuilder = new newDialog.Builder(cocojava.mContext);
        localBuilder.setTitle(((cocojava)cocojava.mContext).getUpSellDialogTitle()).setMessage(((cocojava)cocojava.mContext).getUpSellDialogMessage()).setCancelable(false).setPositiveButton("Later", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface.cancel();
            cocojava.mGLView.queueEvent(new Runnable()
            {
              public void run()
              {
                CocoJNI.MinterstitialClosed(0);
                CocoJNI.MsetGamePause(0);
              }
            });
          }
        }).setNegativeButton("Get it now", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface.cancel();
            ((cocojava)cocojava.mContext).getUpSellDialogAction();
            cocojava.mGLView.queueEvent(new Runnable()
            {
              public void run()
              {
                CocoJNI.MinterstitialClosed(1);
                CocoJNI.MsetGamePause(0);
              }
            });
          }
        });
        localBuilder.create().show();
      }
    });
  }
  
  public void updateAchievementCustom(String paramString, float paramFloat, Object paramObject)
  {
    Log.i("cocojava", "OVERRIDE updateAchievementCustom");
  }
  
  public void updateScoreCustom(String paramString, long paramLong, Object paramObject)
  {
    Log.i("cocojava", "OVERRIDE updateScoreCustom");
  }
}
