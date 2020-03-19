package com.netherrealm.mkx;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.LocaleList;
import android.os.Messenger;
import android.os.PowerManager.WakeLock;
import android.os.Process;
import android.os.StatFs;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.Display;
import android.view.InputDevice;
import android.view.InputDevice.MotionRange;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.games.AchievementsClient;
import com.google.android.gms.games.AnnotatedData;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.achievement.Achievement;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.vending.expansion.downloader.DownloadProgressInfo;
import com.google.android.vending.expansion.downloader.DownloaderClientMarshaller;
import com.google.android.vending.expansion.downloader.DownloaderServiceMarshaller;
import com.google.android.vending.expansion.downloader.Helpers;
import com.google.android.vending.expansion.downloader.IDownloaderClient;
import com.google.android.vending.expansion.downloader.IDownloaderService;
import com.google.android.vending.expansion.downloader.IStub;
import com.google.android.vending.expansion.downloader.impl.DownloadInfo;
import com.google.android.vending.expansion.downloader.impl.DownloadsDB;
import com.google.example.games.basegameutils.BaseGameActivity;
import com.netherrealm.util.CustomKeyboard;
import com.netherrealm.util.CustomKeyboard.ActionCallback;
import com.netherrealm.util.KeyboardOption;
import com.netherrealm.util.PlatformInterfaceDelegateResult;
import com.netherrealm.util.PlatformInterfaceDelegateResult.EKeyboardDialogDelegate;
import com.netherrealm.util.PlatformInterfaceDelegateResult.PlatformInterfaceType;
import com.netherrealm.util.UIUtil;
import com.wb.goog.mkx.R.drawable;
import com.wb.goog.mkx.R.id;
import com.wb.goog.mkx.R.layout;
import com.wb.goog.mkx.R.string;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGL11;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

public class UE3JavaApp
  extends BaseGameActivity
  implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, IDownloaderClient, ActivityCompat.OnRequestPermissionsResultCallback
{
  public static WeakReference<Context> AppContext;
  private static String ContentPath = "UnrealEngine3";
  private static final int EGL_CONTEXT_CLIENT_VERSION = 12440;
  private static final int EGL_DEPTH_ENCODING_NONE_NV = 0;
  private static final int EGL_DEPTH_ENCODING_NONLINEAR_NV = 12515;
  private static final int EGL_DEPTH_ENCODING_NV = 12514;
  private static final int EGL_OPENGL_ES2_BIT = 4;
  private static final int EGL_RENDERABLE_TYPE = 12352;
  public static int MaxPerformanceLevel = 0;
  private static final int RC_ACHIEVEMENT_UI = 9003;
  protected static final int REQUEST_ACCOUNT_PERMISSIONS = 18021;
  protected static final int REQUEST_MULTIPLE_PERMISSIONS = 18020;
  protected static final int REQUEST_PHONE_STATE_PERMISSIONS = 18023;
  protected static final int REQUEST_STORAGE_PERMISSIONS = 18022;
  private static long StartupFreeMem = 0L;
  private static long StartupUsedMem = 0L;
  public static final String currentPerformanceKey = "cur_performance";
  public static final String currentResScaleKey = "cur_resolution_scale";
  private static final String lastWorkingPerformanceKey = "lw_performance";
  private static final String lastWorkingResScaleKey = "lw_resolution_scale";
  private static SharedPreferences mPrefs;
  private static int numDownloadsRunning = 0;
  private String APKFilePath = "";
  private AssetManager AssetManagerReference;
  private String ContentExternalStoragePath = "";
  private PowerManager.WakeLock CurrentLock = null;
  private int DepthSize = 24;
  private final int EGLMinAlphaBits = 0;
  private final int EGLMinBlueBits = 5;
  private final int EGLMinDepthBits = 16;
  private final int EGLMinGreenBits = 6;
  private final int EGLMinRedBits = 5;
  private final int EGLMinSampleBuffers = 0;
  private final int EGLMinSampleSamples = 0;
  private final int EGLMinStencilBits = 0;
  private String ExternalStoragePath = "";
  private float GScreenScalePercent = 1.0F;
  private SoundPool GSoundPool = null;
  private AdjustInterface IAdjust = null;
  private ProgressBar IndefiniteLoadingBar = null;
  private RelativeLayout IndefiniteReloadingBar = null;
  private ImageView InstallSplashScreen = null;
  private EditText KeyboardText = null;
  private LinearLayout KeyboardTextLayout = null;
  private Thread LoadingMoviePrepareThread = null;
  private String MainExpansionFilePath = "";
  private Thread MoviePrepareThread = null;
  private UE3JavaBuildSettings.PackageType Packaging = UE3JavaBuildSettings.PACKAGING;
  private String PatchExpansionFilePath = "";
  private SurfaceView PrimaryGPUView = null;
  private TextView Progress = null;
  private String RootExternalPath = "";
  private int ScreenSizeX = -1;
  private int ScreenSizeY = -1;
  private Bitmap SplashImage = null;
  private RelativeLayout SplashLayout = null;
  private ImageView SplashScreen = null;
  private SurfaceView StartupView = null;
  private int SwapBufferFailureCount = 0;
  private Runnable UpdateNetworkTask = null;
  private String appCommandLine = "";
  private HashMap<String, String> appLocalValues;
  private TextView averageSpeed;
  private boolean bAchievementShow = false;
  public boolean bAppActive = true;
  private boolean bCanInterruptApp = false;
  private boolean bFirstSurfaceCreated = false;
  private boolean bFullOpenGLReset = true;
  private boolean bIsDestroying = false;
  private boolean bIsExpansionInAPK = false;
  public boolean bIsFullyConnected = false;
  private AtomicBoolean bIsLoadingMoviePlaying = new AtomicBoolean(false);
  private AtomicBoolean bIsMoviePlaying = new AtomicBoolean(false);
  private boolean bIsMovieSkippable = false;
  public boolean bIsOnWifi = false;
  public boolean bIsOnline = false;
  private boolean bIsTipDisplayed = false;
  private boolean bPoorDeviceInfoShowed = false;
  private boolean bRanInit = false;
  private boolean bSplashScreenIsHidden = false;
  private boolean bSurfaceCreatedThisPass = false;
  public boolean bWindowHasFocus = true;
  private View cellMessage;
  private String currentSongName = null;
  private float currentSongVolume = 1.0F;
  private View dashboard;
  private IStub downloaderClientStub;
  private EGL10 egl = null;
  private EGLConfigParms eglAttemptedParams = null;
  private EGLConfig eglConfig = null;
  private EGLContext eglContext = null;
  private EGLDisplay eglDisplay = null;
  private EGLSurface eglSurface = null;
  private Button exitButton;
  private View exitLayout;
  private TextView exitMessage;
  private GL11 gl = null;
  protected Handler handler = null;
  private KontAgent iKontAgent = null;
  private UE3JavaSupersonic iSupersonic = null;
  private UE3JavaUpsight iUpsight = null;
  private Runnable initRunnable = null;
  private boolean isDownloadPaused = false;
  private boolean isStopped = false;
  private ImageView loadingBackgroundView = null;
  private Bitmap loadingImage = null;
  private AssetFileDescriptor loadingMovieFD = null;
  private RelativeLayout loadingVideoLayout = null;
  private MediaPlayer loadingVideoPlayer = null;
  private SurfaceView loadingVideoView = null;
  private SparseArray<InputDeviceState> mInputDeviceStates;
  private CustomKeyboard mKeyboard;
  private View.OnClickListener mKeyboardAccepted = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      UE3JavaApp.this.JavaCallback_HideKeyBoard(false);
    }
  };
  private View.OnClickListener mKeyboardCancel = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      UE3JavaApp.this.JavaCallback_HideKeyBoard(true);
    }
  };
  private long mPreBackEventTime;
  private long mPreTouchEventTime;
  private MediaPlayer mediaPlayer = null;
  private String metaDataTextureFormat = "";
  private String movieOverlayMessage = "";
  private boolean nativeEGL = false;
  private String pathToMainExpansionFile;
  private String pathToPatchExpansionFile;
  private Button pauseButton;
  private boolean paused = false;
  private String pendingSongName = null;
  private MediaPlayer pendingSongPlayer = null;
  private ProgressBar progressBar;
  private TextView progressFraction;
  private TextView progressPercent;
  private IDownloaderService remoteService;
  private float settingVolume = 0.0F;
  private boolean skipDownloader = false;
  private MediaPlayer songPlayer = null;
  private TextView statusText;
  private int surfaceHeight = 0;
  private int surfaceWidth = 0;
  private TextView timeRemaining;
  private ImageView videoBackgroundView = null;
  private RelativeLayout videoLayout = null;
  private SurfaceView videoView = null;
  private MediaPlayer voiceOverlayPlayer = null;
  private Button wifiSettingsButton;
  private XAPKFile[] xAPKS;
  
  static
  {
    mPrefs = null;
    MaxPerformanceLevel = 2;
    System.loadLibrary("UnrealEngine3");
  }
  
  public UE3JavaApp()
  {
    if (!NativeCallback_IsShippingBuild()) {
      enableDebugLog(true);
    }
    this.mKeyboard = new CustomKeyboard(this, this.handler);
    this.mKeyboard.addCallback(new CustomKeyboard.ActionCallback()
    {
      public void onAction(PlatformInterfaceDelegateResult.EKeyboardDialogDelegate paramAnonymousEKeyboardDialogDelegate, PlatformInterfaceDelegateResult paramAnonymousPlatformInterfaceDelegateResult)
      {
        UE3JavaApp.this.callNativeDelegate(PlatformInterfaceDelegateResult.PlatformInterfaceType.E_KeyboardDialogBase, paramAnonymousEKeyboardDialogDelegate, paramAnonymousPlatformInterfaceDelegateResult);
      }
    });
  }
  
  private void BuildExpansionFilePaths(int paramInt1, int paramInt2)
  {
    Object localObject;
    if ((paramInt1 > 0) && (this.MainExpansionFilePath == ""))
    {
      String str2 = getApplicationContext().getPackageName();
      Environment.getExternalStorageDirectory().getAbsolutePath();
      if (!this.bIsExpansionInAPK) {
        break label282;
      }
      str1 = "";
      paramInt2 = paramInt1;
      this.MainExpansionFilePath = (str1 + "main." + paramInt1 + "." + str2 + ".obb");
      StringBuilder localStringBuilder = new StringBuilder().append(this.MainExpansionFilePath);
      if (!this.bIsExpansionInAPK) {
        break label315;
      }
      localObject = ".png";
      label116:
      this.MainExpansionFilePath = ((String)localObject);
      if ((paramInt2 > 0) && (this.PatchExpansionFilePath == ""))
      {
        this.PatchExpansionFilePath = (str1 + "patch." + paramInt2 + "." + str2 + ".obb");
        localObject = new StringBuilder().append(this.PatchExpansionFilePath);
        if (!this.bIsExpansionInAPK) {
          break label323;
        }
      }
    }
    label282:
    label315:
    label323:
    for (String str1 = ".png";; str1 = "")
    {
      this.PatchExpansionFilePath = str1;
      Logger.LogOut("Main Expansion Path set to: " + this.MainExpansionFilePath);
      Logger.LogOut("Patch Expansion Path set to: " + this.PatchExpansionFilePath);
      return;
      str1 = getApplicationContext().getObbDir().getAbsolutePath() + "/";
      break;
      localObject = "";
      break label116;
    }
  }
  
  private boolean CheckForExistingExpansionFiles()
  {
    getApplicationContext().getPackageName();
    Object localObject = getApplicationContext().getObbDir().getAbsolutePath();
    boolean bool2 = false;
    boolean bool1 = false;
    localObject = new File((String)localObject);
    if (((File)localObject).exists())
    {
      localObject = ((File)localObject).listFiles();
      int i = 0;
      bool2 = bool1;
      if (i < localObject.length)
      {
        String str = localObject[i].getName();
        if (str.startsWith("main."))
        {
          this.MainExpansionFilePath = localObject[i].getPath();
          bool1 = true;
        }
        for (;;)
        {
          i += 1;
          break;
          if (str.startsWith("patch."))
          {
            this.PatchExpansionFilePath = localObject[i].getPath();
            bool1 = true;
          }
        }
      }
    }
    return bool2;
  }
  
  private void CheckForUnsupportedDevices()
  {
    try
    {
      if ((Build.MODEL.toLowerCase().equals("k00c")) && (Integer.parseInt(Build.VERSION.SDK) <= 17)) {
        ShowUpgradeMessage();
      }
      return;
    }
    catch (Exception localException)
    {
      Logger.LogOut("Exception:" + localException.getMessage());
    }
  }
  
  private void ContinueOnCreate(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      AppInitialStartup();
      UpdateTimeOffset();
    }
    try
    {
      TimeControl localTimeControl = new TimeControl();
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
      registerReceiver(localTimeControl, localIntentFilter);
      if (UE3JavaNetwork.NetworkManager != null) {
        registerReceiver(UE3JavaNetwork.NetworkManager, localIntentFilter);
      }
      UE3JavaSwrve.SetApplicationContext(this);
      Logger.LogOut("Leaving OnCreate");
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Logger.LogOut("Exception while registering connectivity receiver: " + localException.toString());
      }
    }
  }
  
  private static void DecrementDownloadsCounter()
  {
    try
    {
      numDownloadsRunning -= 1;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  private void DeleteAllExpansionFiles()
  {
    int i = 0;
    try
    {
      File[] arrayOfFile = new File(Helpers.generateSaveFileName(this, Helpers.getExpansionAPKFileName(this, true, getPackageManager().getPackageInfo(getPackageName(), 0).versionCode))).getParentFile().listFiles();
      int j = arrayOfFile.length;
      while (i < j)
      {
        arrayOfFile[i].delete();
        i += 1;
      }
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Logger.ReportException("", localNameNotFoundException);
    }
  }
  
  private void DeleteOldExpansionFiles()
  {
    int i = 0;
    for (;;)
    {
      try
      {
        int j = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        String str1 = Helpers.getExpansionAPKFileName(this, true, j);
        String str2 = Helpers.getExpansionAPKFileName(this, false, j);
        Object localObject1 = new File(Helpers.generateSaveFileName(this, str1)).getParentFile();
        if (localObject1 != null)
        {
          localObject1 = ((File)localObject1).listFiles();
          if (localObject1 != null)
          {
            j = localObject1.length;
            if (i < j)
            {
              Object localObject2 = localObject1[i];
              String str3 = localObject2.getName();
              if ((str3.startsWith(str1)) || (str3.startsWith(str2))) {
                break label129;
              }
              localObject2.delete();
            }
          }
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        Logger.ReportException("", localNameNotFoundException);
      }
      return;
      label129:
      i += 1;
    }
  }
  
  private void DumpMotionEvent(MotionEvent paramMotionEvent)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int k = paramMotionEvent.getAction();
    int j = k & 0xFF;
    int i;
    if (j == 5) {
      i = 0;
    }
    for (;;)
    {
      localStringBuilder.append("event ACTION_").append(new String[] { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE", "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" }[i]);
      if ((i == 0) || (i == 1))
      {
        localStringBuilder.append("(pid ").append(paramMotionEvent.getPointerId(k >> 8));
        localStringBuilder.append(")");
        Logger.LogOut(localStringBuilder.toString());
      }
      return;
      i = j;
      if (j == 6) {
        i = 1;
      }
    }
  }
  
  private boolean EssentialPermissionsChecked()
  {
    return (!NoObbReadAcess()) || (!ShowPermissionRequest("android.permission.READ_EXTERNAL_STORAGE", 18022));
  }
  
  private boolean ExpansionFilesDelivered()
  {
    if (this.xAPKS == null) {}
    for (;;)
    {
      return true;
      XAPKFile[] arrayOfXAPKFile = this.xAPKS;
      int j = arrayOfXAPKFile.length;
      int i = 0;
      while (i < j)
      {
        XAPKFile localXAPKFile = arrayOfXAPKFile[i];
        if (!Helpers.doesFileExist(this, Helpers.getExpansionAPKFileName(this, localXAPKFile.IsMain, localXAPKFile.FileVersion), localXAPKFile.FileSize, false)) {
          return false;
        }
        i += 1;
      }
    }
  }
  
  private int GetPerformanceLevel()
  {
    if (mPrefs == null) {
      mPrefs = getApplicationContext().getSharedPreferences(getPackageName(), 0);
    }
    return mPrefs.getInt("lw_performance", -1);
  }
  
  private float GetResolutionScale()
  {
    if (mPrefs == null) {
      mPrefs = getApplicationContext().getSharedPreferences(getPackageName(), 0);
    }
    return mPrefs.getFloat("lw_resolution_scale", -1.0F);
  }
  
  public static long GetStartupFreeMem()
  {
    return StartupFreeMem;
  }
  
  public static long GetStartupUsedMem()
  {
    return StartupUsedMem;
  }
  
  private boolean GoogleReadAchievements()
  {
    if (this.Packaging == UE3JavaBuildSettings.PackageType.AMAZON)
    {
      Logger.LogOut("Google Read Achievements - not a google build, exiting");
      return false;
    }
    Logger.LogOut("UE3JavaApp - Google_achievement GoogleReadAchievements is call");
    if (isSignedIn())
    {
      Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(getApplicationContext())).load(false).addOnCompleteListener(new OnCompleteListener()
      {
        public void onComplete(Task<AnnotatedData<AchievementBuffer>> paramAnonymousTask)
        {
          if (paramAnonymousTask.isSuccessful())
          {
            Logger.LogOut("UE3JavaApp - Google loadAchievement - SUCCESS");
            paramAnonymousTask = (AchievementBuffer)((AnnotatedData)paramAnonymousTask.getResult()).get();
            if (paramAnonymousTask.getCount() > 0)
            {
              int n = paramAnonymousTask.getCount();
              int[] arrayOfInt = new int[n];
              String[] arrayOfString1 = new String[n];
              String[] arrayOfString2 = new String[n];
              boolean[] arrayOfBoolean1 = new boolean[n];
              boolean[] arrayOfBoolean2 = new boolean[n];
              int j = 0;
              if (j < n)
              {
                Achievement localAchievement = paramAnonymousTask.get(j);
                Logger.LogOut("UE3JavaApp - Google loadAchievement - Achievement_" + j + ": " + localAchievement);
                String str = localAchievement.getAchievementId();
                int m = -1;
                int i1 = Integer.parseInt(UE3JavaApp.this.getResources().getString(R.string.achievement_MAX));
                int i = 0;
                label150:
                int k = m;
                if (i < i1)
                {
                  k = R.string.achievement_0;
                  if (UE3JavaApp.this.getString(k + i).equalsIgnoreCase(str)) {
                    k = i;
                  }
                }
                else
                {
                  arrayOfInt[j] = k;
                  arrayOfString1[j] = localAchievement.getName();
                  arrayOfString2[j] = localAchievement.getDescription();
                  arrayOfBoolean1[j] = false;
                  if (localAchievement.getState() != 2) {
                    break label254;
                  }
                }
                label254:
                for (int i2 = 1;; i2 = 0)
                {
                  arrayOfBoolean2[j] = i2;
                  j += 1;
                  break;
                  i += 1;
                  break label150;
                }
              }
              UE3JavaApp.NativeCallback_GPSOnAchievementsRead(arrayOfInt, arrayOfString1, arrayOfString2, arrayOfBoolean1, arrayOfBoolean2);
              return;
            }
            UE3JavaApp.NativeCallback_GPSOnAchievementsRead(null, null, null, null, null);
            return;
          }
          Logger.LogOut("UE3JavaApp - Google loadAchievement - FAILURE");
        }
      });
      return true;
    }
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        UE3JavaApp.NativeCallback_GPSOnAchievementsRead(null, null, null, null, null);
      }
    });
    return false;
  }
  
  private boolean GoogleShowAchievements()
  {
    if (this.Packaging == UE3JavaBuildSettings.PackageType.AMAZON)
    {
      Logger.LogOut("Google Show Achievements - not a google build, exiting");
      return false;
    }
    Logger.LogOut("UE3JavaApp - GoogleShowAchievements bAchievementShow: " + this.bAchievementShow);
    if (UE3JavaNetwork.NetworkManager.isNetworkAvailable())
    {
      if (!this.bAchievementShow)
      {
        if (isSignedIn())
        {
          this.bAchievementShow = true;
          Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this)).getAchievementsIntent().addOnSuccessListener(new OnSuccessListener()
          {
            public void onSuccess(Intent paramAnonymousIntent)
            {
              try
              {
                UE3JavaApp.this.startActivityForResult(paramAnonymousIntent, 9003);
                UE3JavaApp.access$402(UE3JavaApp.this, false);
                return;
              }
              catch (Exception paramAnonymousIntent)
              {
                Logger.LogOut("Exception while showing achievements: " + paramAnonymousIntent.toString());
                if (!UE3JavaApp.this.isSignedIn()) {
                  UE3JavaApp.this.beginUserInitiatedSignIn();
                }
                UE3JavaApp.access$402(UE3JavaApp.this, false);
              }
            }
          });
          return true;
        }
        Logger.LogOut("UE3JavaApp - Google ShowAchievements with network, NOT signed in, starting sign in");
        runOnUiThread(new Runnable()
        {
          public void run()
          {
            UE3JavaApp.this.beginUserInitiatedSignIn();
          }
        });
        return true;
      }
      Logger.LogOut("UE3JavaApp - GoogleShowAchievements already showing achievements");
      return false;
    }
    Logger.LogOut("UE3JavaApp - GoogleShowAchievements not connected to the internet");
    return false;
  }
  
  private boolean GoogleUnlockAchievement(int paramInt, float paramFloat)
  {
    if (this.Packaging == UE3JavaBuildSettings.PackageType.AMAZON)
    {
      Logger.LogOut("Google UNLOCK Achievements - not a google build, exiting");
      return false;
    }
    Logger.LogOut(String.format("UE3JavaApp - Google_achievement unlock achievementID %d java", new Object[] { Integer.valueOf(paramInt) }));
    if (isSignedIn())
    {
      String str = getString(R.string.achievement_0 + paramInt);
      Logger.LogOut(String.format("UE3JavaApp - Google unlock name %s java", new Object[] { str }));
      Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this)).unlock(str);
      return true;
    }
    Logger.LogOut("UE3JavaApp -  Google unlockAchievement: not signed in!");
    NativeCallback_GPSOnUnlockAchievement(false);
    return false;
  }
  
  public static void IncrementDownloadsCounter()
  {
    try
    {
      numDownloadsRunning += 1;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  private void InitializeSettingsPreferences()
  {
    int i;
    float f;
    if ((mPrefs.getInt("cur_performance", -1) < 0) || (mPrefs.getFloat("cur_resolution_scale", -1.0F) < 0.0F))
    {
      i = NativeCallback_GetPerformanceLevel();
      f = NativeCallback_GetResolutionScale();
      if (f >= 0.6F) {
        break label143;
      }
      f = 0.5F;
    }
    for (;;)
    {
      SharedPreferences.Editor localEditor = mPrefs.edit();
      localEditor.putInt("cur_performance", i);
      localEditor.putFloat("cur_resolution_scale", f);
      if ((mPrefs.getInt("lw_performance", -1) < 0) || (mPrefs.getFloat("lw_resolution_scale", -1.0F) < 0.0F))
      {
        localEditor.putInt("lw_performance", i);
        localEditor.putFloat("lw_resolution_scale", f);
      }
      localEditor.commit();
      return;
      label143:
      if (f < 0.8F) {
        f = 0.75F;
      } else {
        f = 1.0F;
      }
    }
  }
  
  public static native void NativeCallback_AGCOnLoginChanged(boolean paramBoolean, String paramString);
  
  public static native void NativeCallback_AGCOnReadAchievements(UE3Achievement[] paramArrayOfUE3Achievement);
  
  public static native void NativeCallback_AGCOnReadLeaderboardScores(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt, long[] paramArrayOfLong);
  
  public static native void NativeCallback_AppOnActive();
  
  public static native void NativeCallback_AppOnPause();
  
  public static native void NativeCallback_FBAuthorizationDone();
  
  public static native void NativeCallback_FBDialogComplete(boolean paramBoolean);
  
  public static native void NativeCallback_FBGraphResponse(String paramString, boolean paramBoolean);
  
  public static native void NativeCallback_FBSaveFriendsList(String[] paramArrayOfString1, String[] paramArrayOfString2);
  
  public static native void NativeCallback_FBSaveUserInfo(String paramString1, String paramString2, String paramString3);
  
  public static native void NativeCallback_GPSOnAchievementsRead(int[] paramArrayOfInt, String[] paramArrayOfString1, String[] paramArrayOfString2, boolean[] paramArrayOfBoolean1, boolean[] paramArrayOfBoolean2);
  
  public static native void NativeCallback_GPSOnCreateFile(int paramInt, String paramString);
  
  public static native void NativeCallback_GPSOnLeaderboardScoresRead(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt, long[] paramArrayOfLong);
  
  public static native void NativeCallback_GPSOnListFiles(String[] paramArrayOfString1, String[] paramArrayOfString2);
  
  public static native void NativeCallback_GPSOnLoginChanged(boolean paramBoolean, String paramString1, String paramString2);
  
  public static native void NativeCallback_GPSOnReadFile(int paramInt, String paramString, byte[] paramArrayOfByte);
  
  public static native void NativeCallback_GPSOnUnlockAchievement(boolean paramBoolean);
  
  public static native void NativeCallback_GPSOnWriteFile(int paramInt, String paramString);
  
  public static native int NativeCallback_GetEngineVersion();
  
  public static native boolean NativeCallback_HasDelegate(int paramInt1, int paramInt2);
  
  public static native boolean NativeCallback_IsPurchaseValidationEnabled();
  
  public static native void NativeCallback_OnGetUserResourcesComplete(String paramString);
  
  public static native void NativeCallback_OnGetUserResourcesDiffComplete(String paramString);
  
  public static native String NativeCallback_PhoneHomeGetURL();
  
  public static native void NativeCallback_ProcessHttpResponse(int paramInt1, String[] paramArrayOfString1, String[] paramArrayOfString2, String paramString, int paramInt2);
  
  public static native void NativeCallback_RemoteNotificationReceived(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2);
  
  public static native void NativeCallback_RemoteNotificationsRegistrationDone(boolean paramBoolean, String paramString);
  
  public static native void NativeCallback_SupersonicRewardOfferwall(int paramInt);
  
  public static native void NativeCallback_SupersonicRewardVideo(String paramString, int paramInt);
  
  public static native void NativeCallback_SwrveGEngineCmd(String paramString);
  
  public static native void NativeCallback_SwrveSetAppVersion(String paramString);
  
  public static native void NativeCallback_TimeSetOffset(int paramInt1, int paramInt2, int paramInt3);
  
  public static native void NativeCallback_TwitterAuthDone(boolean paramBoolean);
  
  public static native void NativeCallback_UpdatePerformanceSettings(int paramInt, float paramFloat);
  
  private boolean NoObbReadAcess()
  {
    PrepareExpansionFilePaths();
    File localFile1 = new File(this.MainExpansionFilePath);
    File localFile2 = new File(this.MainExpansionFilePath);
    try
    {
      new BufferedReader(new FileReader(localFile1)).read();
      new BufferedReader(new FileReader(localFile2)).read();
      return false;
    }
    catch (IOException localIOException) {}
    return true;
  }
  
  private void OpenSettingsMenu()
  {
    InitializeSettingsPreferences();
    startActivity(new Intent(getApplicationContext(), UE3JavaPreferences.class));
  }
  
  private void PlaySongInternal(final boolean paramBoolean, final FileDescriptor paramFileDescriptor, final long paramLong1, long paramLong2, final String paramString)
  {
    Logger.LogOut("PlaySong: " + paramString);
    this.handler.post(new Runnable()
    {
      public void run()
      {
        for (;;)
        {
          try
          {
            if (UE3JavaApp.this.songPlayer == null) {
              break label201;
            }
            if (!UE3JavaApp.this.songPlayer.isPlaying()) {
              break;
            }
            if ((paramString.equals(UE3JavaApp.this.currentSongName)) && (UE3JavaApp.this.pendingSongPlayer == null)) {
              return;
            }
            if (paramString.equals(UE3JavaApp.this.pendingSongName)) {
              return;
            }
            if (UE3JavaApp.this.pendingSongPlayer != null) {
              UE3JavaApp.this.pendingSongPlayer.release();
            }
            UE3JavaApp.access$4702(UE3JavaApp.this, new MediaPlayer());
            if (paramBoolean)
            {
              UE3JavaApp.this.pendingSongPlayer.setDataSource(paramFileDescriptor, paramLong1, this.val$SongLength);
              UE3JavaApp.this.pendingSongPlayer.prepare();
              UE3JavaApp.this.pendingSongPlayer.setLooping(true);
              UE3JavaApp.access$4802(UE3JavaApp.this, paramString);
              return;
            }
          }
          catch (Exception localException)
          {
            Logger.ReportException("Couldn't start song", localException);
            return;
          }
          UE3JavaApp.this.pendingSongPlayer.setDataSource(paramString);
        }
        UE3JavaApp.this.songPlayer.release();
        label201:
        UE3JavaApp.access$4502(UE3JavaApp.this, new MediaPlayer());
        if (paramBoolean) {
          UE3JavaApp.this.songPlayer.setDataSource(paramFileDescriptor, paramLong1, this.val$SongLength);
        }
        for (;;)
        {
          UE3JavaApp.this.songPlayer.prepare();
          UE3JavaApp.this.songPlayer.setLooping(true);
          UE3JavaApp.this.songPlayer.start();
          UE3JavaApp.access$5002(UE3JavaApp.this, UE3JavaApp.this.settingVolume);
          UE3JavaApp.this.songPlayer.setVolume(UE3JavaApp.this.settingVolume, UE3JavaApp.this.settingVolume);
          UE3JavaApp.access$4602(UE3JavaApp.this, paramString);
          return;
          UE3JavaApp.this.songPlayer.setDataSource(paramString);
        }
      }
    });
  }
  
  private void PopulateXAPKs()
  {
    DownloadsDB localDownloadsDB = DownloadsDB.getDB(getApplicationContext());
    if (localDownloadsDB.getLastCheckedVersionCode() != -1)
    {
      DownloadInfo[] arrayOfDownloadInfo = localDownloadsDB.getDownloads();
      if (arrayOfDownloadInfo != null)
      {
        this.xAPKS = new XAPKFile[arrayOfDownloadInfo.length];
        int i = 0;
        if (i < arrayOfDownloadInfo.length)
        {
          XAPKFile localXAPKFile = new XAPKFile();
          localXAPKFile.FileSize = arrayOfDownloadInfo[i].mTotalBytes;
          String str2 = arrayOfDownloadInfo[i].mFileName;
          String str1;
          if (str2.startsWith("main"))
          {
            localXAPKFile.IsMain = true;
            str1 = str2.substring(5);
          }
          for (;;)
          {
            localXAPKFile.FileVersion = Integer.parseInt(str1.substring(0, str1.indexOf('.')));
            this.xAPKS[i] = localXAPKFile;
            Logger.LogOut("newest apk: " + localXAPKFile.IsMain + "." + localXAPKFile.FileVersion);
            i += 1;
            break;
            str1 = str2;
            if (str2.startsWith("patch"))
            {
              localXAPKFile.IsMain = false;
              str1 = str2.substring(6);
            }
          }
        }
      }
      localDownloadsDB.close();
    }
  }
  
  private void PrepareExpansionFilePaths()
  {
    int i;
    if ((NativeCallback_IsShippingBuild()) && (!this.skipDownloader))
    {
      i = 0;
      int j = 0;
      if ((this.xAPKS != null) && (this.xAPKS.length > 0))
      {
        i = this.xAPKS[0].FileVersion;
        if ((this.xAPKS == null) || (this.xAPKS.length <= 1)) {
          break label110;
        }
        j = this.xAPKS[1].FileVersion;
      }
      for (;;)
      {
        BuildExpansionFilePaths(i, j);
        return;
        int k;
        try
        {
          k = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
          i = k;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException1)
        {
          Logger.ReportException("", localNameNotFoundException1);
        }
        break;
        try
        {
          label110:
          k = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
          j = k;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException2)
        {
          Logger.ReportException("", localNameNotFoundException2);
        }
      }
    }
    try
    {
      i = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
      BuildExpansionFilePaths(i, i);
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException3)
    {
      Logger.ReportException("", localNameNotFoundException3);
    }
  }
  
  public static String ReadMemFileToString(File paramFile)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      paramFile = new BufferedReader(new FileReader(paramFile));
      String str;
      do
      {
        str = paramFile.readLine();
        if (str == null) {
          break;
        }
      } while (str.indexOf("MemTotal") == -1);
      localStringBuilder.append(str);
      paramFile.close();
      return localStringBuilder.toString();
      paramFile.close();
    }
    catch (IOException paramFile)
    {
      for (;;)
      {
        paramFile.printStackTrace();
      }
    }
    return localStringBuilder.toString();
  }
  
  private void SetButtonPausedState(boolean paramBoolean)
  {
    this.isDownloadPaused = paramBoolean;
    if (paramBoolean) {}
    for (int i = R.string.Text_Button_Resume;; i = R.string.Text_Button_Pause)
    {
      this.pauseButton.setText(i);
      return;
    }
  }
  
  private void ShowPermissionExitDialog()
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        new AlertDialog.Builder(jdField_this).setCancelable(false).setTitle(R.string.permission_title).setMessage(R.string.Permission_Exit).setPositiveButton(R.string.Retry, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            UE3JavaApp.this.EssentialPermissionsChecked();
          }
        }).setNegativeButton(R.string.Exit, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            UE3JavaApp.this.finish();
          }
        }).show();
      }
    });
  }
  
  private void ShowReloader()
  {
    if (this.bIsLoadingMoviePlaying.get()) {
      return;
    }
    System.gc();
    this.bIsLoadingMoviePlaying.getAndSet(true);
    int i = getResources().getIdentifier("loadscreen_mkxlogo", "drawable", getPackageName());
    if (i != 0)
    {
      if (this.IndefiniteReloadingBar == null)
      {
        this.IndefiniteReloadingBar = ((RelativeLayout)getLayoutInflater().inflate(R.layout.reloader, null));
        addContentView(this.IndefiniteReloadingBar, new ViewGroup.LayoutParams(-1, -1));
      }
      if (this.videoBackgroundView == null)
      {
        Logger.LogOut("ShowReloader videoBackgroundView is null");
        try
        {
          this.loadingImage = UIUtil.decodeSampledBitmapFromResource(this, i);
          this.loadingBackgroundView = new ImageView(this);
          this.loadingBackgroundView.setImageBitmap(this.loadingImage);
          this.loadingBackgroundView.setScaleType(ImageView.ScaleType.FIT_XY);
          this.IndefiniteLoadingBar = new ProgressBar(this);
          this.IndefiniteReloadingBar.addView(this.loadingBackgroundView, new RelativeLayout.LayoutParams(-1, -1));
          this.IndefiniteReloadingBar.addView(this.IndefiniteLoadingBar, new ViewGroup.LayoutParams(50, 50));
          this.IndefiniteLoadingBar.bringToFront();
          return;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            Logger.LogOut("Exception caught in ShowReloader: " + localException.toString());
          }
        }
      }
      Logger.LogOut("ShowReloader: Background image is not null, not adding image");
      return;
    }
    this.IndefiniteReloadingBar = ((RelativeLayout)getLayoutInflater().inflate(R.layout.reloader, null));
    addContentView(this.IndefiniteReloadingBar, new ViewGroup.LayoutParams(-1, -1));
  }
  
  private void ShowUpgradeMessage()
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(jdField_this);
        localBuilder.setMessage(UE3JavaApp.this.getString(R.string.UpgradeSystem));
        localBuilder.setTitle(UE3JavaApp.this.getString(R.string.app_name));
        localBuilder.setPositiveButton(UE3JavaApp.this.getString(R.string.OK), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
        });
        localBuilder.create().show();
      }
    });
  }
  
  private void StopVideo()
  {
    Logger.LogOut("StopVideo called");
    if (!this.bIsMoviePlaying.get()) {
      return;
    }
    this.handler.post(new Runnable()
    {
      public void run()
      {
        Logger.LogOut("StopVideo called async");
        UE3JavaApp.access$2302(UE3JavaApp.this, false);
        UE3JavaApp.this.bIsMoviePlaying.getAndSet(false);
        for (;;)
        {
          int i;
          try
          {
            if (UE3JavaApp.this.mediaPlayer != null)
            {
              Logger.LogOut("Stopping video");
              UE3JavaApp.this.mediaPlayer.stop();
              UE3JavaApp.this.mediaPlayer.release();
              UE3JavaApp.access$2902(UE3JavaApp.this, null);
              if (UE3JavaApp.this.videoView != null)
              {
                ((ViewGroup)UE3JavaApp.this.videoView.getParent()).removeView(UE3JavaApp.this.videoView);
                UE3JavaApp.access$2802(UE3JavaApp.this, null);
                if (UE3JavaApp.this.videoBackgroundView == null) {
                  break label240;
                }
                ((ViewGroup)UE3JavaApp.this.videoBackgroundView.getParent()).removeView(UE3JavaApp.this.videoBackgroundView);
                UE3JavaApp.access$2602(UE3JavaApp.this, null);
                if (UE3JavaApp.this.videoLayout == null) {
                  break label319;
                }
                ViewGroup localViewGroup = (ViewGroup)UE3JavaApp.this.findViewById(16908290);
                i = 0;
                if (i >= localViewGroup.getChildCount()) {
                  break label248;
                }
                if (localViewGroup.getChildAt(i) != UE3JavaApp.this.videoLayout) {
                  break label335;
                }
                localViewGroup.removeViewAt(i);
                break label335;
              }
            }
            else
            {
              Logger.LogOut("mediaPlayer is null @ StopVideo");
              continue;
            }
            Logger.LogOut("videoView is null @ StopVideo");
          }
          catch (Exception localException)
          {
            Logger.ReportException("Exception occurred while stopping video: ", localException);
            return;
          }
          continue;
          label240:
          Logger.LogOut("videoBackgroundView is null @ StopVideo");
          continue;
          label248:
          UE3JavaApp.access$2502(UE3JavaApp.this, null);
          if (UE3JavaApp.this.loadingImage != null)
          {
            UE3JavaApp.this.loadingImage.recycle();
            UE3JavaApp.access$2702(UE3JavaApp.this, null);
            if (UE3JavaApp.this.bIsLoadingMoviePlaying.get()) {
              UE3JavaApp.this.bIsLoadingMoviePlaying.getAndSet(false);
            }
          }
          for (;;)
          {
            UE3JavaApp.this.NativeCallback_MovieFinished();
            return;
            label319:
            Logger.LogOut("videoLayout is null @ StopVideo");
            break;
            Logger.LogOut("loadingImage is null @ StopVideo");
          }
          label335:
          i += 1;
        }
      }
    });
  }
  
  private void UpdateTimeOffset() {}
  
  private void WarnForPoorDevice()
  {
    long l2 = 0L;
    int j = getNumCores();
    long l1 = l2;
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
      l1 = l2;
      String str1 = localBufferedReader.readLine();
      l1 = l2;
      String[] arrayOfString = str1.split("\\s+");
      l1 = l2;
      int k = arrayOfString.length;
      int i = 0;
      while (i < k)
      {
        String str2 = arrayOfString[i];
        l1 = l2;
        Log.i(str1, str2 + "\t");
        i += 1;
      }
      l1 = l2;
      l2 = Integer.valueOf(arrayOfString[1]).intValue() / 1024;
      l1 = l2;
      localBufferedReader.close();
      l1 = l2;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        l1 = -1L;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Logger.LogOut("Caught Exception trying to setup poor device warning: " + localException.toString());
      }
      label260:
      ShowPoorDeviceMessageBox(getString(R.string.WarningTitle), getString(R.string.InsufficientMemoryWarning));
    }
    Logger.LogOut("Total Device Memory:" + l1);
    if (l1 < 1200L)
    {
      if (l1 <= 700L) {
        break label260;
      }
      if (j < 4) {
        ShowPoorDeviceMessageBox(getString(R.string.WarningTitle), getString(R.string.InsufficientCoreWarning));
      }
    }
    return;
  }
  
  private InputDeviceState getInputDeviceState(InputEvent paramInputEvent)
  {
    Object localObject;
    if ((paramInputEvent == null) || (this.mInputDeviceStates == null)) {
      localObject = null;
    }
    int i;
    InputDeviceState localInputDeviceState;
    do
    {
      return localObject;
      i = paramInputEvent.getDeviceId();
      localInputDeviceState = (InputDeviceState)this.mInputDeviceStates.get(i);
      localObject = localInputDeviceState;
    } while (localInputDeviceState != null);
    paramInputEvent = paramInputEvent.getDevice();
    if (paramInputEvent == null) {
      return null;
    }
    paramInputEvent = new InputDeviceState(paramInputEvent);
    this.mInputDeviceStates.put(i, paramInputEvent);
    Logger.LogOut(paramInputEvent.ShowDeviceInfo());
    return paramInputEvent;
  }
  
  private int getNumCores()
  {
    try
    {
      File[] arrayOfFile = new File("/sys/devices/system/cpu/").listFiles(new FileFilter()
      {
        public boolean accept(File paramAnonymousFile)
        {
          return Pattern.matches("cpu[0-9]", paramAnonymousFile.getName());
        }
      });
      Logger.LogOut("CPU Count: " + arrayOfFile.length);
      int i = arrayOfFile.length;
      return i;
    }
    catch (Exception localException)
    {
      Logger.LogOut("CPU Count: Failed.");
      localException.printStackTrace();
    }
    return 1;
  }
  
  private boolean isIPAddress(String paramString)
  {
    return Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}").matcher(paramString).matches();
  }
  
  /* Error */
  public void AppInitialStartup()
  {
    // Byte code:
    //   0: ldc_w 1564
    //   3: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   6: ldc_w 1564
    //   9: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   12: ldc_w 1564
    //   15: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   18: ldc_w 1566
    //   21: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   24: ldc_w 1564
    //   27: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   30: ldc_w 1564
    //   33: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   36: ldc_w 1564
    //   39: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   42: aload_0
    //   43: invokespecial 1568	com/netherrealm/mkx/UE3JavaApp:PopulateXAPKs	()V
    //   46: aload_0
    //   47: invokespecial 1108	com/netherrealm/mkx/UE3JavaApp:PrepareExpansionFilePaths	()V
    //   50: aload_0
    //   51: ldc_w 1570
    //   54: invokevirtual 1574	com/netherrealm/mkx/UE3JavaApp:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   57: checkcast 1576	android/telephony/TelephonyManager
    //   60: invokevirtual 1578	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   63: astore_2
    //   64: new 657	java/lang/StringBuilder
    //   67: dup
    //   68: invokespecial 658	java/lang/StringBuilder:<init>	()V
    //   71: ldc_w 1580
    //   74: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   77: aload_2
    //   78: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: invokevirtual 674	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   84: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   87: new 657	java/lang/StringBuilder
    //   90: dup
    //   91: invokespecial 658	java/lang/StringBuilder:<init>	()V
    //   94: ldc_w 1582
    //   97: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: aload_0
    //   101: invokevirtual 638	com/netherrealm/mkx/UE3JavaApp:getApplicationContext	()Landroid/content/Context;
    //   104: invokevirtual 1586	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   107: ldc_w 1588
    //   110: invokestatic 1593	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   113: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: invokevirtual 674	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   119: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   122: new 657	java/lang/StringBuilder
    //   125: dup
    //   126: invokespecial 658	java/lang/StringBuilder:<init>	()V
    //   129: ldc_w 1595
    //   132: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: getstatic 722	android/os/Build:MODEL	Ljava/lang/String;
    //   138: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   141: invokevirtual 674	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   144: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   147: new 657	java/lang/StringBuilder
    //   150: dup
    //   151: invokespecial 658	java/lang/StringBuilder:<init>	()V
    //   154: ldc_w 1597
    //   157: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: aload_0
    //   161: invokevirtual 798	com/netherrealm/mkx/UE3JavaApp:getPackageName	()Ljava/lang/String;
    //   164: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: invokevirtual 674	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   170: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   173: new 657	java/lang/StringBuilder
    //   176: dup
    //   177: invokespecial 658	java/lang/StringBuilder:<init>	()V
    //   180: ldc_w 1599
    //   183: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: getstatic 736	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   189: invokestatic 742	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   192: invokevirtual 667	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   195: invokevirtual 674	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   198: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   201: aload_0
    //   202: invokevirtual 1602	com/netherrealm/mkx/UE3JavaApp:systemStartupCheck	()Z
    //   205: pop
    //   206: aload_0
    //   207: invokevirtual 638	com/netherrealm/mkx/UE3JavaApp:getApplicationContext	()Landroid/content/Context;
    //   210: aload_0
    //   211: invokevirtual 798	com/netherrealm/mkx/UE3JavaApp:getPackageName	()Ljava/lang/String;
    //   214: iconst_0
    //   215: invokevirtual 899	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   218: putstatic 391	com/netherrealm/mkx/UE3JavaApp:mPrefs	Landroid/content/SharedPreferences;
    //   221: getstatic 391	com/netherrealm/mkx/UE3JavaApp:mPrefs	Landroid/content/SharedPreferences;
    //   224: ifnull +41 -> 265
    //   227: getstatic 391	com/netherrealm/mkx/UE3JavaApp:mPrefs	Landroid/content/SharedPreferences;
    //   230: ldc_w 1604
    //   233: iconst_1
    //   234: invokeinterface 1608 3 0
    //   239: ifne +26 -> 265
    //   242: getstatic 391	com/netherrealm/mkx/UE3JavaApp:mPrefs	Landroid/content/SharedPreferences;
    //   245: invokeinterface 1038 1 0
    //   250: ldc_w 1610
    //   253: lconst_1
    //   254: invokeinterface 1614 4 0
    //   259: invokeinterface 1051 1 0
    //   264: pop
    //   265: aload_0
    //   266: new 1516	android/util/SparseArray
    //   269: dup
    //   270: invokespecial 1615	android/util/SparseArray:<init>	()V
    //   273: putfield 1509	com/netherrealm/mkx/UE3JavaApp:mInputDeviceStates	Landroid/util/SparseArray;
    //   276: aload_0
    //   277: invokestatic 1620	com/netherrealm/mkx/ApplicationInformation:Init	(Landroid/app/Activity;)V
    //   280: aload_0
    //   281: invokestatic 1625	com/netherrealm/mkx/AndroidPhoneHome:SetApplicationActivity	(Landroid/app/Activity;)V
    //   284: invokestatic 1628	com/netherrealm/mkx/AndroidPhoneHome:QueueRequest	()V
    //   287: aload_0
    //   288: invokestatic 1631	com/netherrealm/mkx/Apsalar:SetApplicationContext	(Landroid/app/Activity;)V
    //   291: aload_0
    //   292: getfield 525	com/netherrealm/mkx/UE3JavaApp:Packaging	Lcom/netherrealm/mkx/UE3JavaBuildSettings$PackageType;
    //   295: getstatic 1634	com/netherrealm/mkx/UE3JavaBuildSettings$PackageType:GOOGLE	Lcom/netherrealm/mkx/UE3JavaBuildSettings$PackageType;
    //   298: if_acmpne +20 -> 318
    //   301: new 1636	com/netherrealm/mkx/UE3JavaGoogleLicensing
    //   304: dup
    //   305: invokespecial 1637	com/netherrealm/mkx/UE3JavaGoogleLicensing:<init>	()V
    //   308: putstatic 1641	com/netherrealm/mkx/UE3JavaGoogleLicensing:GoogleLicensing	Lcom/netherrealm/mkx/UE3JavaGoogleLicensing;
    //   311: getstatic 1641	com/netherrealm/mkx/UE3JavaGoogleLicensing:GoogleLicensing	Lcom/netherrealm/mkx/UE3JavaGoogleLicensing;
    //   314: aload_0
    //   315: invokevirtual 1643	com/netherrealm/mkx/UE3JavaGoogleLicensing:Init	(Lcom/netherrealm/mkx/UE3JavaApp;)V
    //   318: new 1645	com/netherrealm/mkx/UE3JavaHttpRequestHandler
    //   321: dup
    //   322: aload_0
    //   323: invokespecial 1646	com/netherrealm/mkx/UE3JavaHttpRequestHandler:<init>	(Landroid/content/Context;)V
    //   326: putstatic 1650	com/netherrealm/mkx/UE3JavaHttpRequestHandler:Instance	Lcom/netherrealm/mkx/UE3JavaHttpRequestHandler;
    //   329: new 1652	com/netherrealm/mkx/UE3JavaTwitter
    //   332: dup
    //   333: invokespecial 1653	com/netherrealm/mkx/UE3JavaTwitter:<init>	()V
    //   336: putstatic 1657	com/netherrealm/mkx/UE3JavaTwitter:TwitterInstance	Lcom/netherrealm/mkx/UE3JavaTwitter;
    //   339: getstatic 1657	com/netherrealm/mkx/UE3JavaTwitter:TwitterInstance	Lcom/netherrealm/mkx/UE3JavaTwitter;
    //   342: aload_0
    //   343: invokevirtual 1658	com/netherrealm/mkx/UE3JavaTwitter:Init	(Lcom/netherrealm/mkx/UE3JavaApp;)V
    //   346: new 1660	com/netherrealm/mkx/UE3JavaFacebook
    //   349: dup
    //   350: invokespecial 1661	com/netherrealm/mkx/UE3JavaFacebook:<init>	()V
    //   353: putstatic 1665	com/netherrealm/mkx/UE3JavaFacebook:FacebookSupport	Lcom/netherrealm/mkx/UE3JavaFacebook;
    //   356: getstatic 1665	com/netherrealm/mkx/UE3JavaFacebook:FacebookSupport	Lcom/netherrealm/mkx/UE3JavaFacebook;
    //   359: aload_0
    //   360: invokevirtual 1666	com/netherrealm/mkx/UE3JavaFacebook:Init	(Lcom/netherrealm/mkx/UE3JavaApp;)V
    //   363: new 1668	com/netherrealm/mkx/UE3JavaInAppMessaging
    //   366: dup
    //   367: invokespecial 1669	com/netherrealm/mkx/UE3JavaInAppMessaging:<init>	()V
    //   370: putstatic 1673	com/netherrealm/mkx/UE3JavaInAppMessaging:InAppMessaging	Lcom/netherrealm/mkx/UE3JavaInAppMessaging;
    //   373: getstatic 1673	com/netherrealm/mkx/UE3JavaInAppMessaging:InAppMessaging	Lcom/netherrealm/mkx/UE3JavaInAppMessaging;
    //   376: aload_0
    //   377: invokevirtual 1674	com/netherrealm/mkx/UE3JavaInAppMessaging:Init	(Lcom/netherrealm/mkx/UE3JavaApp;)V
    //   380: new 1676	com/netherrealm/mkx/UE3JavaAppNotifications
    //   383: dup
    //   384: invokespecial 1677	com/netherrealm/mkx/UE3JavaAppNotifications:<init>	()V
    //   387: putstatic 1681	com/netherrealm/mkx/UE3JavaAppNotifications:AppNotification	Lcom/netherrealm/mkx/UE3JavaAppNotifications;
    //   390: getstatic 1681	com/netherrealm/mkx/UE3JavaAppNotifications:AppNotification	Lcom/netherrealm/mkx/UE3JavaAppNotifications;
    //   393: astore_2
    //   394: aload_0
    //   395: invokevirtual 638	com/netherrealm/mkx/UE3JavaApp:getApplicationContext	()Landroid/content/Context;
    //   398: aload_0
    //   399: aload_0
    //   400: ldc_w 1683
    //   403: invokevirtual 1687	com/netherrealm/mkx/UE3JavaApp:JavaCallback_GetUIDHashValue	(Ljava/lang/String;)Ljava/lang/String;
    //   406: invokestatic 1690	com/netherrealm/mkx/UE3JavaAppNotifications:Init	(Landroid/content/Context;Landroid/content/Context;Ljava/lang/String;)V
    //   409: aload_0
    //   410: getfield 525	com/netherrealm/mkx/UE3JavaApp:Packaging	Lcom/netherrealm/mkx/UE3JavaBuildSettings$PackageType;
    //   413: getstatic 1634	com/netherrealm/mkx/UE3JavaBuildSettings$PackageType:GOOGLE	Lcom/netherrealm/mkx/UE3JavaBuildSettings$PackageType;
    //   416: if_acmpne +223 -> 639
    //   419: new 1692	com/netherrealm/mkx/UE3JavaGoogleStore
    //   422: dup
    //   423: invokespecial 1693	com/netherrealm/mkx/UE3JavaGoogleStore:<init>	()V
    //   426: putstatic 1699	com/netherrealm/mkx/UE3JavaStore:StoreSupport	Lcom/netherrealm/mkx/UE3JavaStore;
    //   429: getstatic 1699	com/netherrealm/mkx/UE3JavaStore:StoreSupport	Lcom/netherrealm/mkx/UE3JavaStore;
    //   432: aload_0
    //   433: invokevirtual 1700	com/netherrealm/mkx/UE3JavaStore:Init	(Lcom/netherrealm/mkx/UE3JavaApp;)V
    //   436: getstatic 778	com/netherrealm/mkx/UE3JavaNetwork:NetworkManager	Lcom/netherrealm/mkx/UE3JavaNetwork;
    //   439: ifnonnull +14 -> 453
    //   442: new 774	com/netherrealm/mkx/UE3JavaNetwork
    //   445: dup
    //   446: aload_0
    //   447: invokespecial 1701	com/netherrealm/mkx/UE3JavaNetwork:<init>	(Lcom/netherrealm/mkx/UE3JavaApp;)V
    //   450: putstatic 778	com/netherrealm/mkx/UE3JavaNetwork:NetworkManager	Lcom/netherrealm/mkx/UE3JavaNetwork;
    //   453: getstatic 778	com/netherrealm/mkx/UE3JavaNetwork:NetworkManager	Lcom/netherrealm/mkx/UE3JavaNetwork;
    //   456: invokevirtual 1704	com/netherrealm/mkx/UE3JavaNetwork:updateNetworkStatus	()V
    //   459: aload_0
    //   460: invokevirtual 1708	com/netherrealm/mkx/UE3JavaApp:getIntent	()Landroid/content/Intent;
    //   463: astore_2
    //   464: aload_2
    //   465: ldc_w 1710
    //   468: invokevirtual 1714	android/content/Intent:getBundleExtra	(Ljava/lang/String;)Landroid/os/Bundle;
    //   471: astore_3
    //   472: aload_3
    //   473: ifnull +221 -> 694
    //   476: ldc_w 1716
    //   479: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   482: aload_3
    //   483: ldc_w 1718
    //   486: invokevirtual 1723	android/os/Bundle:getStringArray	(Ljava/lang/String;)[Ljava/lang/String;
    //   489: astore_2
    //   490: aload_3
    //   491: ldc_w 1725
    //   494: invokevirtual 1727	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   497: astore_3
    //   498: aload_2
    //   499: arraylength
    //   500: iconst_2
    //   501: idiv
    //   502: anewarray 707	java/lang/String
    //   505: astore 4
    //   507: aload_2
    //   508: arraylength
    //   509: iconst_2
    //   510: idiv
    //   511: anewarray 707	java/lang/String
    //   514: astore 5
    //   516: iconst_0
    //   517: istore_1
    //   518: iload_1
    //   519: aload_2
    //   520: arraylength
    //   521: iconst_1
    //   522: isub
    //   523: if_icmpge +146 -> 669
    //   526: aload 4
    //   528: iconst_0
    //   529: aload_2
    //   530: iload_1
    //   531: aaload
    //   532: aastore
    //   533: aload 5
    //   535: iconst_0
    //   536: aload_2
    //   537: iload_1
    //   538: iconst_1
    //   539: iadd
    //   540: aaload
    //   541: aastore
    //   542: iload_1
    //   543: iconst_2
    //   544: iadd
    //   545: istore_1
    //   546: goto -28 -> 518
    //   549: astore_2
    //   550: new 657	java/lang/StringBuilder
    //   553: dup
    //   554: invokespecial 658	java/lang/StringBuilder:<init>	()V
    //   557: ldc_w 1729
    //   560: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   563: aload_2
    //   564: invokevirtual 789	java/lang/Exception:toString	()Ljava/lang/String;
    //   567: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   570: invokevirtual 674	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   573: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   576: goto -375 -> 201
    //   579: astore_2
    //   580: new 657	java/lang/StringBuilder
    //   583: dup
    //   584: invokespecial 658	java/lang/StringBuilder:<init>	()V
    //   587: ldc_w 1731
    //   590: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   593: aload_2
    //   594: invokevirtual 789	java/lang/Exception:toString	()Ljava/lang/String;
    //   597: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   600: invokevirtual 674	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   603: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   606: goto -341 -> 265
    //   609: astore_2
    //   610: new 657	java/lang/StringBuilder
    //   613: dup
    //   614: invokespecial 658	java/lang/StringBuilder:<init>	()V
    //   617: ldc_w 1733
    //   620: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   623: aload_2
    //   624: invokevirtual 789	java/lang/Exception:toString	()Ljava/lang/String;
    //   627: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   630: invokevirtual 674	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   633: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   636: goto -349 -> 287
    //   639: aload_0
    //   640: getfield 525	com/netherrealm/mkx/UE3JavaApp:Packaging	Lcom/netherrealm/mkx/UE3JavaBuildSettings$PackageType;
    //   643: getstatic 925	com/netherrealm/mkx/UE3JavaBuildSettings$PackageType:AMAZON	Lcom/netherrealm/mkx/UE3JavaBuildSettings$PackageType;
    //   646: if_acmpne -210 -> 436
    //   649: new 1735	com/netherrealm/mkx/UE3JavaAmazonStore
    //   652: dup
    //   653: invokespecial 1736	com/netherrealm/mkx/UE3JavaAmazonStore:<init>	()V
    //   656: putstatic 1699	com/netherrealm/mkx/UE3JavaStore:StoreSupport	Lcom/netherrealm/mkx/UE3JavaStore;
    //   659: getstatic 1699	com/netherrealm/mkx/UE3JavaStore:StoreSupport	Lcom/netherrealm/mkx/UE3JavaStore;
    //   662: aload_0
    //   663: invokevirtual 1700	com/netherrealm/mkx/UE3JavaStore:Init	(Lcom/netherrealm/mkx/UE3JavaApp;)V
    //   666: goto -230 -> 436
    //   669: aload_0
    //   670: aload_3
    //   671: aload 4
    //   673: aload 5
    //   675: iconst_1
    //   676: invokevirtual 1740	com/netherrealm/mkx/UE3JavaApp:NativeCallback_NotificationLaunchInfo	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Z)V
    //   679: aload_0
    //   680: invokevirtual 1744	com/netherrealm/mkx/UE3JavaApp:getWindow	()Landroid/view/Window;
    //   683: ldc_w 1745
    //   686: invokevirtual 1750	android/view/Window:addFlags	(I)V
    //   689: aload_0
    //   690: invokevirtual 1753	com/netherrealm/mkx/UE3JavaApp:ensureImmersiveMode	()V
    //   693: return
    //   694: ldc_w 1755
    //   697: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   700: aload_2
    //   701: ldc_w 1757
    //   704: invokevirtual 1714	android/content/Intent:getBundleExtra	(Ljava/lang/String;)Landroid/os/Bundle;
    //   707: astore_3
    //   708: aload_3
    //   709: ifnull +189 -> 898
    //   712: ldc_w 1759
    //   715: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   718: ldc_w 484
    //   721: astore_2
    //   722: aload_3
    //   723: ldc_w 1725
    //   726: invokevirtual 1727	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   729: ifnull +11 -> 740
    //   732: aload_3
    //   733: ldc_w 1725
    //   736: invokevirtual 1727	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   739: astore_2
    //   740: aload_3
    //   741: invokevirtual 1763	android/os/Bundle:keySet	()Ljava/util/Set;
    //   744: invokeinterface 1769 1 0
    //   749: arraylength
    //   750: anewarray 707	java/lang/String
    //   753: astore 4
    //   755: aload_3
    //   756: invokevirtual 1763	android/os/Bundle:keySet	()Ljava/util/Set;
    //   759: aload 4
    //   761: invokeinterface 1772 2 0
    //   766: pop
    //   767: aload 4
    //   769: arraylength
    //   770: anewarray 707	java/lang/String
    //   773: astore 5
    //   775: iconst_0
    //   776: istore_1
    //   777: iload_1
    //   778: aload 4
    //   780: arraylength
    //   781: if_icmpge +74 -> 855
    //   784: aload 5
    //   786: iload_1
    //   787: aload_3
    //   788: aload 4
    //   790: iload_1
    //   791: aaload
    //   792: invokevirtual 1727	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   795: aastore
    //   796: new 657	java/lang/StringBuilder
    //   799: dup
    //   800: invokespecial 658	java/lang/StringBuilder:<init>	()V
    //   803: ldc_w 1774
    //   806: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   809: aload 4
    //   811: iload_1
    //   812: aaload
    //   813: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   816: invokevirtual 674	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   819: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   822: new 657	java/lang/StringBuilder
    //   825: dup
    //   826: invokespecial 658	java/lang/StringBuilder:<init>	()V
    //   829: ldc_w 1776
    //   832: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   835: aload 5
    //   837: iload_1
    //   838: aaload
    //   839: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   842: invokevirtual 674	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   845: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   848: iload_1
    //   849: iconst_1
    //   850: iadd
    //   851: istore_1
    //   852: goto -75 -> 777
    //   855: aload_0
    //   856: aload_2
    //   857: aload 4
    //   859: aload 5
    //   861: iconst_0
    //   862: invokevirtual 1740	com/netherrealm/mkx/UE3JavaApp:NativeCallback_NotificationLaunchInfo	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Z)V
    //   865: goto -186 -> 679
    //   868: astore_2
    //   869: new 657	java/lang/StringBuilder
    //   872: dup
    //   873: invokespecial 658	java/lang/StringBuilder:<init>	()V
    //   876: ldc_w 1778
    //   879: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   882: aload_2
    //   883: invokevirtual 789	java/lang/Exception:toString	()Ljava/lang/String;
    //   886: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   889: invokevirtual 674	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   892: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   895: goto -216 -> 679
    //   898: ldc_w 1780
    //   901: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   904: aload_0
    //   905: invokevirtual 1783	com/netherrealm/mkx/UE3JavaApp:NativeCallback_AppNonNotificationLaunch	()V
    //   908: goto -229 -> 679
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	911	0	this	UE3JavaApp
    //   517	335	1	i	int
    //   63	474	2	localObject1	Object
    //   549	15	2	localException1	Exception
    //   579	15	2	localException2	Exception
    //   609	92	2	localException3	Exception
    //   721	136	2	str	String
    //   868	15	2	localException4	Exception
    //   471	317	3	localObject2	Object
    //   505	353	4	arrayOfString1	String[]
    //   514	346	5	arrayOfString2	String[]
    // Exception table:
    //   from	to	target	type
    //   50	201	549	java/lang/Exception
    //   206	265	579	java/lang/Exception
    //   276	287	609	java/lang/Exception
    //   459	472	868	java/lang/Exception
    //   476	516	868	java/lang/Exception
    //   518	526	868	java/lang/Exception
    //   669	679	868	java/lang/Exception
    //   694	708	868	java/lang/Exception
    //   712	718	868	java/lang/Exception
    //   722	740	868	java/lang/Exception
    //   740	775	868	java/lang/Exception
    //   777	848	868	java/lang/Exception
    //   855	865	868	java/lang/Exception
    //   898	908	868	java/lang/Exception
  }
  
  public void BeginOpenGLStartup()
  {
    this.PrimaryGPUView = new SurfaceView(this);
    SurfaceHolder localSurfaceHolder = this.PrimaryGPUView.getHolder();
    localSurfaceHolder.setType(2);
    if (Build.MODEL.equals("Kindle Fire"))
    {
      Logger.LogOut("Using external Z on " + Build.MODEL + " to avoid known driver issues.");
      NativeCallback_SpecifyExternalZ(getPackageName());
    }
    localSurfaceHolder.addCallback(new SurfaceHolder.Callback()
    {
      public void surfaceChanged(SurfaceHolder paramAnonymousSurfaceHolder, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        paramAnonymousSurfaceHolder = UE3JavaApp.this;
        if (paramAnonymousInt2 > paramAnonymousInt3)
        {
          paramAnonymousInt1 = paramAnonymousInt2;
          UE3JavaApp.access$1302(paramAnonymousSurfaceHolder, paramAnonymousInt1);
          paramAnonymousSurfaceHolder = UE3JavaApp.this;
          if (paramAnonymousInt2 <= paramAnonymousInt3) {
            break label291;
          }
          label30:
          UE3JavaApp.access$1402(paramAnonymousSurfaceHolder, paramAnonymousInt3);
          Logger.LogOut("Surface changed to width: " + UE3JavaApp.this.surfaceWidth + " and height: " + UE3JavaApp.this.surfaceHeight);
          paramAnonymousSurfaceHolder = Build.MODEL;
          Logger.LogOut("device model is " + paramAnonymousSurfaceHolder);
          if ((!paramAnonymousSurfaceHolder.toLowerCase().contains("nexus 6")) && (!paramAnonymousSurfaceHolder.toLowerCase().contains("nexus 5x")) && (!paramAnonymousSurfaceHolder.toLowerCase().contains("lg-h81")) && (!paramAnonymousSurfaceHolder.toLowerCase().contains("lg-f500")) && (!paramAnonymousSurfaceHolder.toLowerCase().contains("lg-xs991")) && (!paramAnonymousSurfaceHolder.toLowerCase().contains("lg-ls991")) && (!paramAnonymousSurfaceHolder.toLowerCase().contains("lg-v32")) && (!paramAnonymousSurfaceHolder.toLowerCase().contains("lg-vs986"))) {
            break label297;
          }
        }
        label291:
        label297:
        for (paramAnonymousInt1 = 1;; paramAnonymousInt1 = 0)
        {
          if ((UE3JavaApp.this.PrimaryGPUView != null) && (paramAnonymousInt1 != 0))
          {
            Logger.LogOut("force set view size for nexus 6 and LG G4");
            UE3JavaApp.this.PrimaryGPUView.getLayoutParams().width = UE3JavaApp.this.surfaceWidth;
            UE3JavaApp.this.PrimaryGPUView.getLayoutParams().height = UE3JavaApp.this.surfaceHeight;
          }
          UE3JavaApp.this.NativeCallback_PostInitUpdate(UE3JavaApp.this.surfaceWidth, UE3JavaApp.this.surfaceHeight);
          return;
          paramAnonymousInt1 = paramAnonymousInt3;
          break;
          paramAnonymousInt3 = paramAnonymousInt2;
          break label30;
        }
      }
      
      public void surfaceCreated(SurfaceHolder paramAnonymousSurfaceHolder)
      {
        Logger.LogOut("in surfaceCreated");
        UE3JavaApp.access$802(UE3JavaApp.this, true);
        paramAnonymousSurfaceHolder.setType(2);
        boolean bool1 = true;
        if (UE3JavaApp.this.eglContext == null)
        {
          Logger.LogOut("calling initEGLCallback");
          bool2 = UE3JavaApp.this.NativeCallback_InitEGLCallback();
          Logger.LogOut("initEGLCallback returned");
          bool1 = bool2;
          if (UE3JavaApp.this.bFirstSurfaceCreated)
          {
            UE3JavaApp.this.NativeCallback_EGLContextRecreated();
            UE3JavaApp.this.ShowReloader();
            bool1 = bool2;
          }
        }
        boolean bool2 = bool1;
        if (!UE3JavaApp.this.nativeEGL)
        {
          bool2 = bool1;
          if (bool1) {
            bool2 = UE3JavaApp.this.createEGLSurface(paramAnonymousSurfaceHolder);
          }
        }
        if (UE3JavaApp.this.bFirstSurfaceCreated)
        {
          UE3JavaApp.this.ShowReloader();
          if (!UE3JavaApp.this.NativeCallback_InterruptionChanged(false)) {
            Process.killProcess(Process.myPid());
          }
        }
        if (!UE3JavaApp.this.bFirstSurfaceCreated)
        {
          UE3JavaApp.access$1002(UE3JavaApp.this, true);
          if (bool2) {
            UE3JavaApp.this.handler.post(new Runnable()
            {
              public void run()
              {
                UE3JavaApp.this.PostStartup();
              }
            });
          }
        }
        else
        {
          return;
        }
        UE3JavaApp.this.handler.post(new Runnable()
        {
          public void run()
          {
            new AlertDialog.Builder(UE3JavaApp.22.this.val$act).setMessage(UE3JavaApp.this.getString(R.string.OpenGL_Failed)).setPositiveButton("Ok", new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
              {
                UE3JavaApp.this.finish();
              }
            }).setCancelable(false).show();
          }
        });
      }
      
      public void surfaceDestroyed(SurfaceHolder paramAnonymousSurfaceHolder)
      {
        Logger.LogOut("Surface surfaceDestroyed");
        if (!UE3JavaApp.this.nativeEGL)
        {
          UE3JavaApp.this.NativeCallback_InterruptionChanged(true);
          UE3JavaApp.this.destroyEGLSurface();
        }
      }
    });
    setContentView(this.PrimaryGPUView);
  }
  
  public boolean CanDownloadExpansionFiles()
  {
    Logger.LogOut("Check for expansion files");
    if (UE3JavaNetwork.NetworkManager == null) {
      UE3JavaNetwork.NetworkManager = new UE3JavaNetwork(this);
    }
    UE3JavaNetwork.NetworkManager.updateNetworkStatus();
    if (!UE3JavaNetwork.NetworkManager.isNetworkAvailable()) {
      if (!CheckForExistingExpansionFiles()) {}
    }
    for (;;)
    {
      return false;
      new AlertDialog.Builder(this).setMessage(getString(R.string.NoConnectionOrExpansions)).setPositiveButton("Ok", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          UE3JavaApp.this.finish();
        }
      }).setCancelable(false).show();
      return true;
      try
      {
        Object localObject = getIntent();
        Intent localIntent = new Intent(this, getClass());
        localIntent.setFlags(335544320);
        localIntent.setAction(((Intent)localObject).getAction());
        if (((Intent)localObject).getCategories() != null)
        {
          localObject = ((Intent)localObject).getCategories().iterator();
          while (((Iterator)localObject).hasNext()) {
            localIntent.addCategory((String)((Iterator)localObject).next());
          }
        }
        if (DownloaderClientMarshaller.startDownloadServiceIfRequired(this, PendingIntent.getActivity(this, 0, localNameNotFoundException, 134217728), UE3JavaFileDownloader.class) == 0) {}
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        Logger.ReportException("Downloader Client Marshaller", localNameNotFoundException);
        return false;
      }
    }
    if (!IsThereSpaceForExpansionFiles()) {
      InitializeForceExitMessage(R.string.Out_Of_Memory);
    } else {
      InitializeDownloadUI();
    }
    return true;
  }
  
  public void CanInterruptApp()
  {
    this.bCanInterruptApp = true;
  }
  
  public void CopytoClipboard(final String paramString)
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        ((ClipboardManager)UE3JavaApp.this.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Copied Text", paramString));
      }
    });
  }
  
  public void GoToOurApp()
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + UE3JavaApp.this.getPackageName()));
        localIntent.addFlags(268435456);
        UE3JavaApp.this.startActivity(localIntent);
      }
    });
  }
  
  public void HaltMedia()
  {
    StopMusic();
    Logger.LogOut("HaltMedia called");
    try
    {
      if ((this.mediaPlayer != null) || (this.loadingVideoPlayer != null)) {
        MovieError();
      }
      return;
    }
    catch (Exception localException)
    {
      Logger.ReportException("Failed to kill Movie in HaltMedia", localException);
    }
  }
  
  public void HideKeyBoard(boolean paramBoolean)
  {
    Logger.LogOut("HideKeyBoard");
    this.handler.post(new Runnable()
    {
      public void run()
      {
        if (UE3JavaApp.this.mKeyboard != null)
        {
          UE3JavaApp.this.mKeyboard.hide();
          return;
        }
        Logger.LogOut("Keyboard is not initialized, can't hide it");
      }
    });
  }
  
  public void HideReloader()
  {
    if (!this.bIsLoadingMoviePlaying.get()) {
      return;
    }
    this.handler.post(new Runnable()
    {
      public void run()
      {
        if ((UE3JavaApp.this.mKeyboard != null) && (UE3JavaApp.this.mKeyboard.shouldActive())) {}
        try
        {
          UE3JavaApp.this.mKeyboard.getInput().requestFocus();
          ((InputMethodManager)UE3JavaApp.this.getSystemService("input_method")).showSoftInput(UE3JavaApp.this.mKeyboard.getInput(), 1);
          if (UE3JavaApp.this.IndefiniteReloadingBar != null)
          {
            ViewGroup localViewGroup = (ViewGroup)UE3JavaApp.this.IndefiniteReloadingBar.getParent();
            if (localViewGroup != null)
            {
              localViewGroup.removeView(UE3JavaApp.this.IndefiniteReloadingBar);
              UE3JavaApp.access$3902(UE3JavaApp.this, null);
            }
          }
          UE3JavaApp.this.bIsLoadingMoviePlaying.getAndSet(false);
          if (UE3JavaApp.this.loadingVideoPlayer != null)
          {
            UE3JavaApp.this.loadingVideoPlayer.stop();
            UE3JavaApp.this.loadingVideoPlayer.release();
            UE3JavaApp.access$4002(UE3JavaApp.this, null);
          }
          if (UE3JavaApp.this.loadingVideoView != null)
          {
            ((ViewGroup)UE3JavaApp.this.loadingVideoView.getParent()).removeView(UE3JavaApp.this.loadingVideoView);
            UE3JavaApp.access$4102(UE3JavaApp.this, null);
          }
          if (UE3JavaApp.this.loadingBackgroundView != null)
          {
            ((ViewGroup)UE3JavaApp.this.loadingBackgroundView.getParent()).removeView(UE3JavaApp.this.loadingBackgroundView);
            UE3JavaApp.access$4202(UE3JavaApp.this, null);
          }
          if (UE3JavaApp.this.loadingVideoLayout != null)
          {
            ((ViewGroup)UE3JavaApp.this.loadingVideoLayout.getParent()).removeView(UE3JavaApp.this.loadingVideoLayout);
            UE3JavaApp.access$4302(UE3JavaApp.this, null);
          }
          if (UE3JavaApp.this.loadingMovieFD == null) {}
        }
        catch (Exception localException)
        {
          try
          {
            UE3JavaApp.this.loadingMovieFD.close();
            UE3JavaApp.access$4402(UE3JavaApp.this, null);
            if ((UE3JavaApp.this.loadingImage != null) && (UE3JavaApp.this.videoBackgroundView == null))
            {
              UE3JavaApp.this.loadingImage.recycle();
              UE3JavaApp.access$2702(UE3JavaApp.this, null);
            }
            return;
            localException = localException;
            Logger.LogOut("Caught exception trying to clean keyboard in HideReloader: " + localException.toString());
          }
          catch (IOException localIOException)
          {
            for (;;) {}
          }
        }
      }
    });
  }
  
  public void HideSplash()
  {
    if (this.bSplashScreenIsHidden) {
      return;
    }
    this.handler.post(new Runnable()
    {
      public void run()
      {
        UE3JavaApp.access$3502(UE3JavaApp.this, true);
        ViewGroup localViewGroup;
        if (UE3JavaApp.this.SplashScreen != null)
        {
          localViewGroup = (ViewGroup)UE3JavaApp.this.SplashScreen.getParent();
          if (localViewGroup != null) {
            localViewGroup.removeView(UE3JavaApp.this.SplashScreen);
          }
          UE3JavaApp.access$3602(UE3JavaApp.this, null);
        }
        if (UE3JavaApp.this.IndefiniteLoadingBar != null)
        {
          localViewGroup = (ViewGroup)UE3JavaApp.this.IndefiniteLoadingBar.getParent();
          if (localViewGroup != null) {
            localViewGroup.removeView(UE3JavaApp.this.IndefiniteLoadingBar);
          }
          UE3JavaApp.access$3102(UE3JavaApp.this, null);
        }
        if (UE3JavaApp.this.SplashLayout != null)
        {
          localViewGroup = (ViewGroup)UE3JavaApp.this.SplashLayout.getParent();
          if (localViewGroup != null) {
            localViewGroup.removeView(UE3JavaApp.this.SplashLayout);
          }
          UE3JavaApp.access$3702(UE3JavaApp.this, null);
        }
        if (UE3JavaApp.this.SplashImage != null)
        {
          UE3JavaApp.this.SplashImage.recycle();
          UE3JavaApp.access$3802(UE3JavaApp.this, null);
        }
      }
    });
  }
  
  public void InitializeDownloadUI()
  {
    Logger.LogOut("Initialize download UI");
    this.downloaderClientStub = DownloaderClientMarshaller.CreateStub(this, UE3JavaFileDownloader.class);
    if (this.downloaderClientStub == null) {
      Logger.LogOut("downloaderClientStub is null after DownloaderClientMarshaller.CreateStub()!");
    }
    setContentView(R.layout.main);
    this.progressBar = ((ProgressBar)findViewById(R.id.progressBar));
    this.statusText = ((TextView)findViewById(R.id.statusText));
    this.progressFraction = ((TextView)findViewById(R.id.progressAsFraction));
    this.progressPercent = ((TextView)findViewById(R.id.progressAsPercentage));
    this.averageSpeed = ((TextView)findViewById(R.id.progressAverageSpeed));
    this.timeRemaining = ((TextView)findViewById(R.id.progressTimeRemaining));
    this.dashboard = findViewById(R.id.downloaderDashboard);
    this.cellMessage = findViewById(R.id.approveCellular);
    this.pauseButton = ((Button)findViewById(R.id.pauseButton));
    this.wifiSettingsButton = ((Button)findViewById(R.id.wifiSettingsButton));
    this.pauseButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (UE3JavaApp.this.isDownloadPaused)
        {
          UE3JavaApp.this.remoteService.requestContinueDownload();
          paramAnonymousView = UE3JavaApp.this;
          if (UE3JavaApp.this.isDownloadPaused) {
            break label60;
          }
        }
        label60:
        for (boolean bool = true;; bool = false)
        {
          paramAnonymousView.SetButtonPausedState(bool);
          return;
          UE3JavaApp.this.remoteService.requestPauseDownload();
          break;
        }
      }
    });
    this.wifiSettingsButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        UE3JavaApp.this.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
      }
    });
    ((Button)findViewById(R.id.resumeOverCellular)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        UE3JavaApp.this.remoteService.setDownloadFlags(1);
        UE3JavaApp.this.remoteService.requestContinueDownload();
        UE3JavaApp.this.cellMessage.setVisibility(8);
      }
    });
  }
  
  public void InitializeForceExitMessage(int paramInt)
  {
    if (this.dashboard != null) {
      this.dashboard.setVisibility(8);
    }
    this.exitLayout = findViewById(R.id.exitLayout);
    this.exitMessage = ((TextView)findViewById(R.id.exitMessage));
    this.exitMessage.setText(paramInt);
    this.exitButton = ((Button)findViewById(R.id.exitButton));
    this.exitLayout.setVisibility(0);
    this.exitButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        UE3JavaApp.this.systemCleanup();
      }
    });
  }
  
  public boolean IsThereSpaceForExpansionFiles()
  {
    if (this.xAPKS == null) {}
    long l;
    do
    {
      return true;
      l = 0L;
      XAPKFile[] arrayOfXAPKFile = this.xAPKS;
      int j = arrayOfXAPKFile.length;
      int i = 0;
      while (i < j)
      {
        l += arrayOfXAPKFile[i].FileSize;
        i += 1;
      }
    } while (l <= Helpers.getAvailableBytes(new File(getApplicationContext().getObbDir().getAbsolutePath())));
    Logger.LogOut("Out of space, user needs to delete something");
    return false;
  }
  
  public boolean JavaCallback_AGCInitialize(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    return UE3JavaGameCircle.GameCircleSupport.Initialize(paramString1, paramString2, paramBoolean1, paramBoolean2, paramBoolean3);
  }
  
  public boolean JavaCallback_AGCReadAchievements()
  {
    return UE3JavaGameCircle.GameCircleSupport.ReadAchievements();
  }
  
  public boolean JavaCallback_AGCReadLeaderboardFriendsScores(int paramInt)
  {
    return UE3JavaGameCircle.GameCircleSupport.ReadLeaderboardFriendsScores(paramInt);
  }
  
  public boolean JavaCallback_AGCReadLeaderboardGlobalScores(int paramInt1, int paramInt2, int paramInt3)
  {
    return UE3JavaGameCircle.GameCircleSupport.ReadLeaderboardGlobalScores(paramInt1, paramInt2, paramInt3);
  }
  
  public boolean JavaCallback_AGCReadLeaderboardLocalScores(int paramInt1, int paramInt2)
  {
    return UE3JavaGameCircle.GameCircleSupport.ReadLeaderboardLocalScores(paramInt1, paramInt2);
  }
  
  public boolean JavaCallback_AGCReadLeaderboardPlayerScore(int paramInt)
  {
    return UE3JavaGameCircle.GameCircleSupport.ReadLeaderboardPlayerScore(paramInt);
  }
  
  public boolean JavaCallback_AGCShowAchievementsUI()
  {
    return UE3JavaGameCircle.GameCircleSupport.ShowAchievementsUI();
  }
  
  public boolean JavaCallback_AGCShowLeaderboardUI(int paramInt)
  {
    return UE3JavaGameCircle.GameCircleSupport.ShowLeaderboardUI(paramInt);
  }
  
  public boolean JavaCallback_AGCSubmitLeaderboardScore(int paramInt, long paramLong)
  {
    return UE3JavaGameCircle.GameCircleSupport.SubmitLeaderboardScore(paramInt, paramLong);
  }
  
  public boolean JavaCallback_AGCUnlockAchievement(int paramInt, float paramFloat)
  {
    return UE3JavaGameCircle.GameCircleSupport.UnlockAchievement(paramInt, paramFloat);
  }
  
  public void JavaCallback_AdjustHandleOpenURL(String paramString)
  {
    if (this.IAdjust != null) {
      this.IAdjust.AdjustHandleOpenURL(paramString);
    }
  }
  
  public void JavaCallback_AdjustInit(boolean paramBoolean1, boolean paramBoolean2, String paramString)
  {
    this.IAdjust = AdjustInterface.GetAdjustInterface();
    if (this.IAdjust != null) {
      this.IAdjust.init(this, paramBoolean1, paramBoolean2, paramString);
    }
  }
  
  public void JavaCallback_AdjustLogEvent(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    if (this.IAdjust != null) {
      this.IAdjust.AdjustLogEvent(paramString, paramArrayOfString1, paramArrayOfString2);
    }
  }
  
  public void JavaCallback_AdjustLogIapEvent(String paramString1, String paramString2, float paramFloat, String paramString3)
  {
    if (this.IAdjust != null) {
      this.IAdjust.AdjustLogIapEvent(paramString1, paramString2, paramFloat, paramString3);
    }
  }
  
  public void JavaCallback_AdjustLogItemPurchaseEvent(String paramString1, String paramString2, int paramInt1, int paramInt2, String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    if (this.IAdjust != null) {
      this.IAdjust.AdjustLogItemPurchaseEvent(paramString1, paramString2, paramInt1, paramInt2, paramArrayOfString1, paramArrayOfString2);
    }
  }
  
  public void JavaCallback_ApsalarEndSession() {}
  
  public void JavaCallback_ApsalarEvent(String paramString)
  {
    Apsalar.Event(paramString);
  }
  
  public void JavaCallback_ApsalarEventParam(String paramString1, String paramString2, String paramString3)
  {
    Apsalar.EventParam(paramString1, paramString2, paramString3);
  }
  
  public void JavaCallback_ApsalarEventParamArray(String paramString, String[] paramArrayOfString)
  {
    Apsalar.EventParamArray(paramString, paramArrayOfString);
  }
  
  public void JavaCallback_ApsalarInit() {}
  
  public void JavaCallback_ApsalarLogEngineData(String paramString, int paramInt)
  {
    Apsalar.LogEventEngineData(paramString, paramInt);
  }
  
  public void JavaCallback_ApsalarStartSession(String paramString1, String paramString2)
  {
    Apsalar.StartSession(paramString1, paramString2);
  }
  
  public boolean JavaCallback_AuthorizeTwitterAccounts(String paramString1, String paramString2)
  {
    return UE3JavaTwitter.TwitterInstance.AuthorizeAccounts(paramString1, paramString2);
  }
  
  public boolean JavaCallback_CanMakePurchases()
  {
    if (UE3JavaStore.StoreSupport != null) {
      return UE3JavaStore.StoreSupport.CanMakePurchases();
    }
    return false;
  }
  
  public void JavaCallback_CancelAllNotifications()
  {
    UE3JavaAppNotifications localUE3JavaAppNotifications = UE3JavaAppNotifications.AppNotification;
    UE3JavaAppNotifications.CancelAllNotifications();
  }
  
  public void JavaCallback_CheckLicense(String paramString)
  {
    if (this.Packaging == UE3JavaBuildSettings.PackageType.GOOGLE) {
      UE3JavaGoogleLicensing.GoogleLicensing.CheckLicense(paramString);
    }
  }
  
  public void JavaCallback_ConfigureSwrve(String paramString, int paramInt)
  {
    UE3JavaSwrve.SetApplicationContext(this);
    UE3JavaSwrve.Configure(paramString, paramInt, getString(R.string.app_id));
  }
  
  public void JavaCallback_CopyToClipboard(String paramString)
  {
    CopytoClipboard(paramString);
  }
  
  public void JavaCallback_CreateStore(String paramString, String[] paramArrayOfString, boolean[] paramArrayOfBoolean)
  {
    if (UE3JavaStore.StoreSupport != null) {
      if (NativeCallback_IsShippingBuild()) {
        break label31;
      }
    }
    label31:
    for (boolean bool = true;; bool = false)
    {
      ReceiptValidateService_.Init(bool);
      UE3JavaStore.StoreSupport.CreateStore(paramString, paramArrayOfString, paramArrayOfBoolean);
      return;
    }
  }
  
  public void JavaCallback_EnableAppInterruptions()
  {
    this.bCanInterruptApp = true;
  }
  
  public void JavaCallback_FBAuthorize(String paramString)
  {
    UE3JavaFacebook.FacebookSupport.Authorize(paramString);
  }
  
  public void JavaCallback_FBDialog(String paramString, String[] paramArrayOfString)
  {
    UE3JavaFacebook.FacebookSupport.OpenDialog(paramString, paramArrayOfString);
  }
  
  public void JavaCallback_FBDisconnect()
  {
    UE3JavaFacebook.FacebookSupport.Disconnect();
  }
  
  public boolean JavaCallback_FBIsAuthorized()
  {
    return UE3JavaFacebook.FacebookSupport.IsAuthorized();
  }
  
  public void JavaCallback_FBRequest(String paramString)
  {
    UE3JavaFacebook.FacebookSupport.SendGraphRequest(paramString);
  }
  
  public boolean JavaCallback_GPSCreateFile(int paramInt, String paramString, byte[] paramArrayOfByte)
  {
    return false;
  }
  
  public boolean JavaCallback_GPSInitialize(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    return UE3JavaGooglePlayServices.PlayServicesSupport.Initialize(paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4);
  }
  
  public boolean JavaCallback_GPSListFiles()
  {
    return false;
  }
  
  public boolean JavaCallback_GPSLogin()
  {
    LogInGooglePlayServices();
    return true;
  }
  
  public boolean JavaCallback_GPSLogout()
  {
    SignOutGooglePlayServices();
    return true;
  }
  
  public boolean JavaCallback_GPSReadAchievements()
  {
    return GoogleReadAchievements();
  }
  
  public boolean JavaCallback_GPSReadFile(int paramInt, String paramString)
  {
    return false;
  }
  
  public boolean JavaCallback_GPSReadLeaderboardFriendsScores(int paramInt)
  {
    return false;
  }
  
  public boolean JavaCallback_GPSReadLeaderboardGlobalScores(int paramInt1, int paramInt2, int paramInt3)
  {
    return false;
  }
  
  public boolean JavaCallback_GPSReadLeaderboardLocalScores(int paramInt1, int paramInt2)
  {
    return false;
  }
  
  public boolean JavaCallback_GPSReadLeaderboardPlayerScore(int paramInt)
  {
    return false;
  }
  
  public boolean JavaCallback_GPSShowAchievements()
  {
    return GoogleShowAchievements();
  }
  
  public boolean JavaCallback_GPSShowLeaderboard(int paramInt)
  {
    return false;
  }
  
  public boolean JavaCallback_GPSShowLeaderboards()
  {
    return false;
  }
  
  public boolean JavaCallback_GPSSubmitLeaderboardScore(int paramInt, long paramLong)
  {
    return false;
  }
  
  public boolean JavaCallback_GPSUnlockAchievement(int paramInt, float paramFloat)
  {
    return GoogleUnlockAchievement(paramInt, paramFloat);
  }
  
  public boolean JavaCallback_GPSWriteFile(int paramInt, String paramString, byte[] paramArrayOfByte)
  {
    return false;
  }
  
  public void JavaCallback_GPS_Login_Toggle()
  {
    if (this.Packaging == UE3JavaBuildSettings.PackageType.GOOGLE)
    {
      if (!isSignedIn()) {
        beginUserInitiatedSignIn();
      }
    }
    else {
      return;
    }
    SignOutGooglePlayServices();
  }
  
  public String JavaCallback_GetAPKFilePath()
  {
    return this.APKFilePath;
  }
  
  public String JavaCallback_GetAppCommandLine()
  {
    return this.appCommandLine;
  }
  
  public AssetManager JavaCallback_GetAssetManager()
  {
    return this.AssetManagerReference;
  }
  
  public int JavaCallback_GetDepthSize()
  {
    return this.DepthSize;
  }
  
  public String JavaCallback_GetDeviceModel()
  {
    return Build.MODEL;
  }
  
  public String JavaCallback_GetMainAPKExpansionName()
  {
    return this.MainExpansionFilePath;
  }
  
  public int JavaCallback_GetNumTwitterAccounts()
  {
    return UE3JavaTwitter.TwitterInstance.GetNumAccounts();
  }
  
  public String JavaCallback_GetPatchAPKExpansionName()
  {
    return this.PatchExpansionFilePath;
  }
  
  public int JavaCallback_GetPerformanceLevel()
  {
    return GetPerformanceLevel();
  }
  
  public String JavaCallback_GetPushNotificationsToken()
  {
    return UE3JavaRemoteNotifications.GetPushNotificationsToken();
  }
  
  public float JavaCallback_GetResolutionScale()
  {
    return GetResolutionScale();
  }
  
  public int JavaCallback_GetSDKVersion()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public long JavaCallback_GetTime()
  {
    return System.currentTimeMillis();
  }
  
  public String JavaCallback_GetTwitterAccountName()
  {
    return UE3JavaTwitter.TwitterInstance.GetAccountName();
  }
  
  public String JavaCallback_GetTwitterId()
  {
    return UE3JavaTwitter.TwitterInstance.GetAccountId();
  }
  
  public String JavaCallback_GetUIDHashValue(String paramString)
  {
    return AndroidPhoneHome.GetUIDHashValue(paramString);
  }
  
  public long JavaCallback_GetUptime()
  {
    return SystemClock.uptimeMillis();
  }
  
  public void JavaCallback_GetUserInput(final String paramString1, final String paramString2, final String paramString3, final String paramString4, String paramString5)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        Logger.LogOut("Title: " + paramString1 + ", Message: " + paramString2);
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(jdField_this);
        localBuilder.setTitle(paramString1);
        final EditText localEditText = new EditText(jdField_this);
        localEditText.setText(paramString2);
        localEditText.setSingleLine();
        localBuilder.setView(localEditText);
        localBuilder.setCancelable(false);
        localBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface = localEditText.getText().toString();
            UE3JavaApp.this.NativeCallback_UserInputDone(paramAnonymous2DialogInterface, UE3JavaApp.8.this.val$ExecFunc);
          }
        });
        localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            UE3JavaApp.this.NativeCallback_UserInputDone("", UE3JavaApp.8.this.val$CancelFunc);
          }
        });
        localBuilder.show();
      }
    });
  }
  
  public void JavaCallback_GetUserInputMulti(final String paramString1, final String paramString2, final String paramString3, final boolean paramBoolean)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        Logger.LogOut("Title: " + paramString1 + ", Message: " + paramString2);
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(jdField_this);
        localBuilder.setTitle(paramString1);
        final EditText localEditText = new EditText(jdField_this);
        localEditText.setText(paramString2);
        localEditText.setLines(3);
        localEditText.setGravity(0);
        localBuilder.setView(localEditText);
        localBuilder.setCancelable(false);
        localBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            String str = localEditText.getText().toString();
            paramAnonymous2DialogInterface = str;
            if (UE3JavaApp.7.this.val$ShouldURLEncode) {
              paramAnonymous2DialogInterface = URLEncoder.encode(str);
            }
            UE3JavaApp.this.NativeCallback_UserInputDone(paramAnonymous2DialogInterface, UE3JavaApp.7.this.val$ExecFunc);
          }
        });
        localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            UE3JavaApp.this.NativeCallback_UserInputDone("", UE3JavaApp.7.this.val$ExecFunc);
          }
        });
        localBuilder.show();
      }
    });
  }
  
  public void JavaCallback_HideKeyBoard(boolean paramBoolean)
  {
    HideKeyBoard(paramBoolean);
  }
  
  public void JavaCallback_HideReloader()
  {
    HideReloader();
  }
  
  public void JavaCallback_HideSplash()
  {
    HideSplash();
  }
  
  public long JavaCallback_IncrementUserSettingLong(String paramString, long paramLong)
  {
    paramLong = mPrefs.getLong(paramString, 0L) + paramLong;
    mPrefs.edit().putLong(paramString, paramLong).commit();
    return paramLong;
  }
  
  public boolean JavaCallback_IsADMAvailable()
  {
    return UE3JavaRemoteNotifications.IsADMAvailable();
  }
  
  public boolean JavaCallback_IsAmazonPackage()
  {
    return this.Packaging == UE3JavaBuildSettings.PackageType.AMAZON;
  }
  
  public boolean JavaCallback_IsExpansionInAPK()
  {
    return this.bIsExpansionInAPK;
  }
  
  public boolean JavaCallback_IsGCMAvailable()
  {
    return UE3JavaRemoteNotifications.IsGCMAvailable();
  }
  
  public boolean JavaCallback_IsGooglePackage()
  {
    return this.Packaging == UE3JavaBuildSettings.PackageType.GOOGLE;
  }
  
  public boolean JavaCallback_IsVideoPlaying()
  {
    return this.bIsMoviePlaying.get();
  }
  
  public void JavaCallback_KontagentEndSession()
  {
    if ((this.iKontAgent != null) && (this.iKontAgent.IsKontAgentInitialized())) {
      this.iKontAgent.EndSession();
    }
  }
  
  public void JavaCallback_KontagentInit(String paramString)
  {
    this.iKontAgent = KontAgent.getKontagentInterface();
    if (this.iKontAgent != null) {
      this.iKontAgent.Init(paramString);
    }
  }
  
  public void JavaCallback_KontagentLogCustomEventParamArray(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3)
  {
    try
    {
      if (this.iKontAgent != null) {
        this.iKontAgent.LogCustomEventParamArray(paramString, paramArrayOfString1, paramArrayOfString2, paramArrayOfString3);
      }
      return;
    }
    catch (Exception paramString)
    {
      Logger.ReportException("catch kontagent internal error", paramString);
    }
  }
  
  public void JavaCallback_KontagentLogItemPurchaseEvent(String paramString1, String paramString2, int paramInt1, int paramInt2, String paramString3, String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    if (this.iKontAgent != null) {
      this.iKontAgent.LogItemPurchaseEvent(paramString1, paramString2, paramInt1, paramInt2, paramString3, paramArrayOfString1, paramArrayOfString2);
    }
  }
  
  public void JavaCallback_KontagentStartSession(boolean paramBoolean)
  {
    if (this.iKontAgent != null)
    {
      paramBoolean = true;
      if (mPrefs != null) {
        paramBoolean = mPrefs.getBoolean("firstStart", true);
      }
      this.iKontAgent.StartSession(paramBoolean);
      if ((paramBoolean) && (mPrefs != null)) {
        mPrefs.edit().putBoolean("firstStart", false).commit();
      }
    }
  }
  
  public int JavaCallback_LoadSoundFile(String paramString)
  {
    return LoadSoundFile(paramString);
  }
  
  public String JavaCallback_LoadUserSetting(String paramString)
  {
    return mPrefs.getString(paramString, "");
  }
  
  public long JavaCallback_LoadUserSettingLong(String paramString)
  {
    return mPrefs.getLong(paramString, 0L);
  }
  
  public void JavaCallback_OpenSettingsMenu()
  {
    OpenSettingsMenu();
  }
  
  public void JavaCallback_PlaySong(FileDescriptor paramFileDescriptor, long paramLong1, long paramLong2, String paramString)
  {
    PlaySong(paramFileDescriptor, paramLong1, paramLong2, paramString);
  }
  
  public void JavaCallback_PlaySong(String paramString)
  {
    PlaySong(paramString);
  }
  
  public int JavaCallback_PlaySound(int paramInt, boolean paramBoolean)
  {
    return PlaySound(paramInt, paramBoolean);
  }
  
  public boolean JavaCallback_ProcessHttpRequest(String paramString1, String paramString2, String paramString3, String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt)
  {
    return UE3JavaHttpRequestHandler.Instance.ProcessRequest(paramString1, paramString2, paramString3, paramArrayOfString1, paramArrayOfString2, paramInt);
  }
  
  public void JavaCallback_RegisterForPushNotifications(String paramString1, String paramString2)
  {
    UE3JavaRemoteNotifications.Instance.RegisterForPushNotifications(paramString1, paramString2);
  }
  
  public void JavaCallback_RequestPurchase(String paramString, boolean paramBoolean)
  {
    if (UE3JavaStore.StoreSupport != null) {
      UE3JavaStore.StoreSupport.RequestPurchase(paramString, paramBoolean);
    }
  }
  
  public void JavaCallback_ResetTimeOffset()
  {
    UpdateTimeOffset();
  }
  
  public void JavaCallback_RestoreMedia()
  {
    RestoreMedia();
  }
  
  public void JavaCallback_RestorePreviousPurchase(String[] paramArrayOfString)
  {
    if (UE3JavaStore.StoreSupport != null) {
      if (JavaCallback_IsGooglePackage())
      {
        if (UE3JavaStore.StoreSupport == null) {
          break label34;
        }
        UE3JavaStore.StoreSupport.RestorePreviousPurchase(paramArrayOfString);
      }
    }
    for (;;)
    {
      if (JavaCallback_IsAmazonPackage()) {}
      return;
      label34:
      Logger.LogOut("Store support is null, can't restore purchases");
    }
  }
  
  public void JavaCallback_SaveUserSetting(String paramString1, String paramString2)
  {
    mPrefs.edit().putString(paramString1, paramString2).commit();
  }
  
  public void JavaCallback_SaveUserSettingLong(String paramString, long paramLong)
  {
    mPrefs.edit().putLong(paramString, paramLong).commit();
  }
  
  public void JavaCallback_ScheduleNotification(int paramInt1, String paramString1, String paramString2, String[] paramArrayOfString, int paramInt2)
  {
    UE3JavaAppNotifications localUE3JavaAppNotifications = UE3JavaAppNotifications.AppNotification;
    UE3JavaAppNotifications.ScheduleNotification(getApplicationContext(), paramInt1, paramString1, paramString2, paramArrayOfString, paramInt2);
  }
  
  public void JavaCallback_SendEmail(String paramString1, String paramString2)
  {
    UE3JavaInAppMessaging.InAppMessaging.ShowEmailUi(paramString1, paramString2);
  }
  
  public void JavaCallback_SendSms(String paramString)
  {
    UE3JavaInAppMessaging.InAppMessaging.ShowSmsUi(paramString);
  }
  
  public void JavaCallback_SetFixedSizeScale(float paramFloat)
  {
    SetFixedSizeScale(paramFloat);
  }
  
  public void JavaCallback_SetMaxPerformanceLevel(int paramInt)
  {
    MaxPerformanceLevel = paramInt;
  }
  
  public void JavaCallback_SetMusicVolume(float paramFloat)
  {
    SetVolume(paramFloat);
  }
  
  public void JavaCallback_SetVolume(int paramInt, float paramFloat)
  {
    SetVolume(paramInt, paramFloat);
  }
  
  public void JavaCallback_ShowAlert(final String paramString1, final String paramString2, final String paramString3, final String paramString4, final String paramString5)
  {
    try
    {
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          AlertDialog localAlertDialog = new AlertDialog.Builder(UE3JavaApp.this).create();
          TextView localTextView = new TextView(UE3JavaApp.this);
          localTextView.setText(paramString1);
          localTextView.setGravity(17);
          localTextView.setPadding(10, 10, 10, 10);
          localTextView.setTextSize(20.0F);
          localAlertDialog.setCustomTitle(localTextView);
          localAlertDialog.setMessage(paramString2);
          localAlertDialog.setButton(-1, paramString3, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              paramAnonymous2DialogInterface.dismiss();
            }
          });
          if (paramString4 != null) {
            localAlertDialog.setButton(-2, paramString4, new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
              {
                paramAnonymous2DialogInterface.dismiss();
              }
            });
          }
          if (paramString5 != null) {
            localAlertDialog.setButton(-3, paramString5, new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
              {
                paramAnonymous2DialogInterface.dismiss();
              }
            });
          }
          localAlertDialog.setCancelable(true);
          localAlertDialog.show();
        }
      });
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public void JavaCallback_ShowBlockingDialog(final String paramString1, final String paramString2, final String paramString3, final String paramString4, final String paramString5)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        AlertDialog localAlertDialog = new AlertDialog.Builder(UE3JavaApp.this).create();
        TextView localTextView = new TextView(UE3JavaApp.this);
        localTextView.setText(paramString1);
        localTextView.setGravity(17);
        localTextView.setPadding(10, 10, 10, 10);
        localTextView.setTextSize(20.0F);
        localTextView.setTextColor(-1);
        localAlertDialog.setCustomTitle(localTextView);
        localAlertDialog.setMessage(paramString2);
        localAlertDialog.setButton(-1, paramString3, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            UE3JavaApp.this.NativeCallback_SetGDialogReturn(0);
          }
        });
        if (paramString4 != null) {
          localAlertDialog.setButton(-2, paramString4, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              UE3JavaApp.this.NativeCallback_SetGDialogReturn(1);
            }
          });
        }
        if (paramString5 != null) {
          localAlertDialog.setButton(-3, paramString5, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              UE3JavaApp.this.NativeCallback_SetGDialogReturn(2);
            }
          });
        }
        localAlertDialog.setCancelable(false);
        localAlertDialog.show();
      }
    });
  }
  
  public void JavaCallback_ShowExitDialog()
  {
    ShowExitDialog();
  }
  
  public void JavaCallback_ShowKeyBoard(String paramString1, String paramString2, boolean paramBoolean1, int paramInt1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, int paramInt2, int paramInt3, int paramInt4)
  {
    ShowKeyBoard(new KeyboardOption(paramString1, paramString2, paramBoolean1, paramInt1, paramBoolean2, paramBoolean3, paramBoolean4, paramInt2, paramInt3, paramInt4));
  }
  
  public void JavaCallback_ShowWebPage(String paramString)
  {
    ShowWebPage(paramString);
  }
  
  public void JavaCallback_ShutDownApp()
  {
    if (!this.bIsDestroying) {
      Process.killProcess(Process.myPid());
    }
  }
  
  public void JavaCallback_StartVideo(FileDescriptor paramFileDescriptor, long paramLong1, long paramLong2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    StartVideo(paramFileDescriptor, paramLong1, paramLong2, paramBoolean1, paramBoolean2, "", paramBoolean3);
  }
  
  public void JavaCallback_StartVideoResource(String paramString, long paramLong1, long paramLong2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    StartVideo(null, paramLong1, paramLong2, paramBoolean1, paramBoolean2, paramString, paramBoolean3);
  }
  
  public void JavaCallback_StopSong()
  {
    StopSong();
  }
  
  public void JavaCallback_StopSound(int paramInt)
  {
    StopSound(paramInt);
  }
  
  public void JavaCallback_StopVideo()
  {
    Logger.LogOut("JavaCallback_StopVideo");
    StopVideo();
  }
  
  public void JavaCallback_SupersonicInit(String paramString)
  {
    Logger.LogOut("JavaCallback_SupersonicInit()");
    this.iSupersonic = UE3JavaSupersonic.Instance();
    this.iSupersonic.Init(this, paramString, JavaCallback_GetUIDHashValue("MD5"));
  }
  
  public void JavaCallback_SupersonicRequestContentForPlacement(String paramString)
  {
    Logger.LogOut("JavaCallback_SupersonicRequestContentForPlacement()");
    if (this.iSupersonic != null) {
      this.iSupersonic.requestContentForPlacement(paramString);
    }
  }
  
  public void JavaCallback_SwrveGetUserResources() {}
  
  public void JavaCallback_SwrveGetUserResourcesDiff() {}
  
  public void JavaCallback_SwrveOnEvent(String paramString)
  {
    UE3JavaSwrve.OnEvent(paramString);
  }
  
  public void JavaCallback_SwrveSendCachedEvents() {}
  
  public void JavaCallback_ThirdPartIAP(String paramString1, String paramString2, float paramFloat, String paramString3, String paramString4, String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    if (this.iKontAgent != null) {
      this.iKontAgent.revenueTracking(paramString1, paramString2, paramFloat, paramString3, paramString4, paramArrayOfString1, paramArrayOfString2);
    }
  }
  
  public void JavaCallback_Tweet(String paramString)
  {
    UE3JavaTwitter.TwitterInstance.Tweet(paramString);
  }
  
  public void JavaCallback_TweetImage(String paramString1, String paramString2)
  {
    UE3JavaTwitter.TwitterInstance.TweetImage(paramString1, paramString2);
  }
  
  public void JavaCallback_UnloadSoundID(int paramInt)
  {
    UnloadSoundID(paramInt);
  }
  
  public void JavaCallback_UpdateSongPlayer(float paramFloat)
  {
    UpdateSongPlayer(paramFloat);
  }
  
  public void JavaCallback_UpsightInit(String paramString1, String paramString2)
  {
    this.iUpsight = UE3JavaUpsight.Instance();
    this.iUpsight.init(getApplicationContext(), paramString1, paramString2);
  }
  
  public void JavaCallback_UpsightLogIapEvent(String paramString1, float paramFloat, String paramString2)
  {
    if (this.iUpsight != null) {
      this.iUpsight.logIapEvent(paramString1, paramFloat, paramString2, JavaCallback_IsAmazonPackage());
    }
  }
  
  public void JavaCallback_UpsightRequestContentForPlacement(String paramString)
  {
    if (this.iUpsight != null) {
      this.iUpsight.requestContentForPlacement(paramString);
    }
  }
  
  public void JavaCallback_UpsightStartSession()
  {
    if (this.iUpsight != null) {
      this.iUpsight.startSession();
    }
  }
  
  public void JavaCallback_VideoAddTextOverlay(String paramString, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, boolean paramBoolean)
  {
    VideoAddTextOverlay(paramString, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramBoolean);
  }
  
  public void JavaCallback_VideoRemoveTextOverlay()
  {
    VideoRemoveTextOverlay();
  }
  
  public String JavaCallback_getAppLocalValue(String paramString)
  {
    return getAppLocalValue(paramString);
  }
  
  public boolean JavaCallback_hasAppLocalValue(String paramString)
  {
    return hasAppLocalValue(paramString);
  }
  
  public boolean JavaCallback_initEGL(EGLConfigParms paramEGLConfigParms)
  {
    return initEGL(paramEGLConfigParms);
  }
  
  public boolean JavaCallback_makeCurrent()
  {
    return makeCurrent();
  }
  
  public void JavaCallback_setAppLocalValue(String paramString1, String paramString2)
  {
    setAppLocalValue(paramString1, paramString2);
  }
  
  public boolean JavaCallback_swapBuffers()
  {
    return swapBuffers();
  }
  
  public boolean JavaCallback_unMakeCurrent()
  {
    return unMakeCurrent();
  }
  
  public int LoadSoundFile(String paramString)
  {
    int i = this.GSoundPool.load(paramString, 0);
    if (i <= 0)
    {
      Logger.LogOut("loadSoundFile(failed): " + paramString);
      return i;
    }
    Logger.LogOut("loadSoundFile(from storage): " + paramString);
    return i;
  }
  
  public void LogInGooglePlayServices()
  {
    if ((this.Packaging == UE3JavaBuildSettings.PackageType.GOOGLE) && (!getApplicationContext().getSharedPreferences(getPackageName(), 0).getBoolean("GoogleLoginDeclined", false)))
    {
      JavaCallback_GPS_Login_Toggle();
      return;
    }
    Logger.LogOut("LogInGooglePlayServices: Not using google packaging");
  }
  
  public void MovieError()
  {
    Logger.LogOut("MovieError called");
    StopVideo();
    HideReloader();
  }
  
  public native void NativeCallback_AppNonNotificationLaunch();
  
  public native String NativeCallback_ApsalarGetKey();
  
  public native String NativeCallback_ApsalarGetSecret();
  
  public native void NativeCallback_CallPlatformDelegate(int paramInt1, int paramInt2, PlatformInterfaceDelegateResult paramPlatformInterfaceDelegateResult);
  
  public native void NativeCallback_Cleanup();
  
  public native void NativeCallback_EGLContextRecreated();
  
  public native String NativeCallback_GetGameName();
  
  public native int NativeCallback_GetPerformanceLevel();
  
  public native float NativeCallback_GetResolutionScale();
  
  public native void NativeCallback_HandleBackButtonPressed();
  
  public native boolean NativeCallback_InitEGLCallback();
  
  public native boolean NativeCallback_Initialize(int paramInt1, int paramInt2, float paramFloat, boolean paramBoolean);
  
  public native boolean NativeCallback_InputEvent(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong, int paramInt5);
  
  public native boolean NativeCallback_InterruptionChanged(boolean paramBoolean);
  
  public native boolean NativeCallback_IsShippingBuild();
  
  public native boolean NativeCallback_JoystickAxisEvent(int paramInt1, int paramInt2, int paramInt3, float paramFloat, long paramLong);
  
  public native boolean NativeCallback_JoystickButtonEvent(int paramInt1, int paramInt2, int paramInt3, long paramLong);
  
  public native void NativeCallback_KeyPadChange(boolean paramBoolean);
  
  public native boolean NativeCallback_KeyboardEvent(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public native void NativeCallback_KeyboardFinished();
  
  public native void NativeCallback_KeyboardTextChanged(String paramString);
  
  public native void NativeCallback_LanguageandCountrySet(String paramString1, String paramString2);
  
  public native void NativeCallback_MovieFinished();
  
  public native void NativeCallback_NetworkUpdate(boolean paramBoolean1, boolean paramBoolean2);
  
  public native void NativeCallback_NotificationLaunchInfo(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2, boolean paramBoolean);
  
  public native void NativeCallback_PlatformDelegate(int paramInt1, int paramInt2, boolean paramBoolean);
  
  public native void NativeCallback_PlatformDelegateString(int paramInt1, int paramInt2, boolean paramBoolean, String paramString);
  
  public native void NativeCallback_PostInitUpdate(int paramInt1, int paramInt2);
  
  public native void NativeCallback_ProcessProductList(int paramInt, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, String[] paramArrayOfString4, String[] paramArrayOfString5, String[] paramArrayOfString6);
  
  public native void NativeCallback_PurchaseComplete(boolean paramBoolean, String paramString1, String paramString2, String paramString3, String paramString4);
  
  public native void NativeCallback_RestoreItems(String[] paramArrayOfString);
  
  public native void NativeCallback_SetGDialogReturn(int paramInt);
  
  public native void NativeCallback_SpecifyExternalZ(String paramString);
  
  public native boolean NativeCallback_SystemStats(long paramLong, float paramFloat);
  
  public native void NativeCallback_UserInputDone(String paramString1, String paramString2);
  
  public void PlaySong(FileDescriptor paramFileDescriptor, long paramLong1, long paramLong2, String paramString)
  {
    PlaySongInternal(true, paramFileDescriptor, paramLong1, paramLong2, paramString + ".mp3");
  }
  
  public void PlaySong(String paramString)
  {
    PlaySongInternal(false, null, -1L, -1L, paramString);
  }
  
  public int PlaySound(int paramInt, boolean paramBoolean)
  {
    SoundPool localSoundPool = this.GSoundPool;
    if (paramBoolean) {}
    for (int i = -1;; i = 0) {
      return localSoundPool.play(paramInt, 0.0F, 0.0F, 0, i, 1.0F);
    }
  }
  
  public void PostStartup()
  {
    if (!Environment.getExternalStorageState().equals("mounted")) {
      this.handler.post(new Runnable()
      {
        public void run()
        {
          new AlertDialog.Builder(jdField_this).setMessage(UE3JavaApp.this.getString(R.string.NoSDCard)).setPositiveButton("Ok", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              UE3JavaApp.this.finish();
            }
          }).setCancelable(false).show();
        }
      });
    }
    float f3;
    do
    {
      return;
      Object localObject = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
      ((StatFs)localObject).restat(Environment.getExternalStorageDirectory().getAbsolutePath());
      int i = (int)(((StatFs)localObject).getBlockSize() * ((StatFs)localObject).getFreeBlocks() / 1048576L);
      Logger.LogOut("Avaliable Space(MBs): " + i);
      String str = getResources().getConfiguration().locale.getLanguage();
      localObject = getResources().getConfiguration().locale.getCountry();
      if (Build.VERSION.SDK_INT >= 24) {
        localObject = getApplicationContext().getResources().getConfiguration().getLocales().get(0).getCountry();
      }
      NativeCallback_LanguageandCountrySet(str, (String)localObject);
      Logger.LogOut("LanguageText: " + str);
      Logger.LogOut(" CountryText: " + (String)localObject);
      this.SplashLayout = new RelativeLayout(this);
      this.SplashScreen = new ImageView(this);
      this.IndefiniteLoadingBar = new ProgressBar(this);
      this.SplashImage = UIUtil.decodeSampledBitmapFromResource(this, R.drawable.splash);
      this.SplashScreen.setImageBitmap(this.SplashImage);
      this.SplashScreen.setAdjustViewBounds(false);
      this.SplashScreen.setScaleType(ImageView.ScaleType.CENTER_CROP);
      this.SplashLayout.addView(this.SplashScreen, new RelativeLayout.LayoutParams(-1, -1));
      this.SplashLayout.addView(this.IndefiniteLoadingBar, new ViewGroup.LayoutParams(50, 50));
      addContentView(this.SplashLayout, new ViewGroup.LayoutParams(-1, -1));
      localObject = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getRealMetrics((DisplayMetrics)localObject);
      float f1 = 1.0F / ((DisplayMetrics)localObject).xdpi * ((DisplayMetrics)localObject).widthPixels;
      float f2 = 1.0F / ((DisplayMetrics)localObject).ydpi * ((DisplayMetrics)localObject).heightPixels;
      f3 = (float)Math.sqrt((float)Math.pow(f1, 2.0D) + (float)Math.pow(f2, 2.0D));
      Logger.LogOut("Screen Size(Inches) X: " + f1 + " Y: " + f2 + " Diag: " + f3);
      getPackageManager();
      Logger.LogOut("FEATURE_TOUCHSCREEN_MULTITOUCH_DISTINCT " + false);
      this.bRanInit = true;
    } while (NativeCallback_Initialize(this.surfaceWidth, this.surfaceHeight, f3, false));
    this.handler.post(new Runnable()
    {
      public void run()
      {
        new AlertDialog.Builder(jdField_this).setMessage(UE3JavaApp.this.getString(R.string.Init_Failed)).setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            UE3JavaApp.this.finish();
          }
        }).setCancelable(false).show();
      }
    });
  }
  
  public void RefreshView()
  {
    setContentView(this.PrimaryGPUView);
  }
  
  public void RestoreMedia()
  {
    try
    {
      if (this.songPlayer != null) {
        this.songPlayer.start();
      }
      if (this.pendingSongPlayer != null) {
        this.pendingSongPlayer.start();
      }
      return;
    }
    catch (Exception localException)
    {
      Logger.ReportException("Failed RestoreMusic ", localException);
    }
  }
  
  public void SetFixedSizeScale(final float paramFloat)
  {
    this.GScreenScalePercent = paramFloat;
    this.handler.post(new Runnable()
    {
      public void run()
      {
        SurfaceHolder localSurfaceHolder = UE3JavaApp.this.PrimaryGPUView.getHolder();
        int j = (int)(UE3JavaApp.this.PrimaryGPUView.getWidth() * paramFloat);
        int i = (int)(UE3JavaApp.this.PrimaryGPUView.getHeight() * paramFloat);
        Logger.LogOut("JavaCallback_SetFixedSizeScale(" + paramFloat + ") " + j + "x" + i);
        j = (j + 8 - 1) / 8 * 8;
        i = (i + 8 - 1) / 8 * 8;
        Logger.LogOut("JavaCallback_SetFixedSizeScale - adjusted width and height: " + j + "x" + i);
        localSurfaceHolder.setFixedSize(j, i);
        ((ViewGroup)UE3JavaApp.this.PrimaryGPUView.getParent()).removeView(UE3JavaApp.this.PrimaryGPUView);
        UE3JavaApp.this.setContentView(UE3JavaApp.this.PrimaryGPUView);
      }
    });
  }
  
  public void SetInterruption(boolean paramBoolean)
  {
    Logger.LogOut("In SetInterruption " + paramBoolean + ":" + this.paused + ":" + this.bWindowHasFocus);
    if ((paramBoolean) && (!this.paused))
    {
      Logger.LogOut("PAUSING");
      this.paused = true;
      StopMusic();
      if (mPrefs != null)
      {
        SharedPreferences.Editor localEditor = mPrefs.edit();
        localEditor.putString("language", getResources().getConfiguration().locale.getLanguage());
        localEditor.commit();
      }
    }
    if ((!paramBoolean) && (this.bWindowHasFocus) && (this.paused))
    {
      Logger.LogOut("SetInterruption: " + paramBoolean);
      RestoreMedia();
      UpdateAppValues();
      this.bAchievementShow = false;
    }
  }
  
  public void SetVolume(final float paramFloat)
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        UE3JavaApp.access$4902(UE3JavaApp.this, paramFloat);
        if (UE3JavaApp.this.songPlayer != null) {}
        try
        {
          UE3JavaApp.this.songPlayer.setVolume(paramFloat, paramFloat);
          return;
        }
        catch (Exception localException)
        {
          Logger.LogOut("EXCEPTION - Unable to set music volumn on the song player: " + localException.getMessage());
        }
      }
    });
  }
  
  public void SetVolume(int paramInt, float paramFloat)
  {
    this.GSoundPool.setVolume(paramInt, paramFloat, paramFloat);
  }
  
  public void ShowExitDialog()
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        new AlertDialog.Builder(jdField_this).setMessage(UE3JavaApp.this.getString(R.string.ConfirmExit)).setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            UE3JavaApp.this.finish();
          }
        }).setNegativeButton("Cancel", null).show();
      }
    });
  }
  
  public void ShowKeyBoard(final KeyboardOption paramKeyboardOption)
  {
    Logger.LogOut("ShowKeyBoard");
    this.handler.post(new Runnable()
    {
      public void run()
      {
        if (UE3JavaApp.this.mKeyboard != null)
        {
          UE3JavaApp.this.mKeyboard.show(paramKeyboardOption);
          return;
        }
        Logger.LogOut("Keyboard is not initialized, can't bring up to show.");
      }
    });
  }
  
  public boolean ShowMultiPermissionRequest(String[] paramArrayOfString)
  {
    boolean bool = false;
    ArrayList localArrayList = new ArrayList();
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = paramArrayOfString[i];
      if (ContextCompat.checkSelfPermission(this, str) != 0) {
        localArrayList.add(str);
      }
      i += 1;
    }
    if (localArrayList.size() > 0)
    {
      ActivityCompat.requestPermissions(this, (String[])localArrayList.toArray(new String[0]), 18020);
      bool = true;
    }
    return bool;
  }
  
  public boolean ShowPermissionRequest(final String paramString, final int paramInt)
  {
    Logger.LogOut("UE3Java::ShowPermissionRequest: " + paramString);
    if (ContextCompat.checkSelfPermission(this, paramString) != 0)
    {
      this.handler.post(new Runnable()
      {
        public void run()
        {
          new AlertDialog.Builder(jdField_this).setTitle(R.string.permission_title).setCancelable(false).setMessage(UE3JavaApp.this.getString(jdField_this.getResources().getIdentifier(paramString, "string", jdField_this.getPackageName()))).setPositiveButton("Ok", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              paramAnonymous2DialogInterface = UE3JavaApp.4.this.val$act;
              String str = UE3JavaApp.4.this.val$permission;
              paramAnonymous2Int = UE3JavaApp.4.this.val$permissionCode;
              ActivityCompat.requestPermissions(paramAnonymous2DialogInterface, new String[] { str }, paramAnonymous2Int);
            }
          }).show();
        }
      });
      return true;
    }
    Logger.LogOut("Permission already given to user");
    return false;
  }
  
  void ShowPoorDeviceMessageBox(final String paramString1, final String paramString2)
  {
    try
    {
      if (!this.bPoorDeviceInfoShowed)
      {
        this.handler.post(new Runnable()
        {
          public void run()
          {
            new AlertDialog.Builder(jdField_this).setTitle(paramString1).setMessage(paramString2).setPositiveButton(UE3JavaApp.this.getString(R.string.OK), null).show();
          }
        });
        this.bPoorDeviceInfoShowed = true;
      }
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public void ShowUnlicensedDialog()
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        new AlertDialog.Builder(jdField_this).setMessage(UE3JavaApp.this.getString(R.string.NotLicensed)).setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            UE3JavaApp.this.finish();
          }
        }).setCancelable(false).show();
      }
    });
  }
  
  public void ShowWebPage(final String paramString)
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        try
        {
          Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
          localIntent.addFlags(268435456);
          UE3JavaApp.this.startActivity(localIntent);
          return;
        }
        catch (Exception localException)
        {
          Logger.LogOut("Invalid URL");
        }
      }
    });
  }
  
  public void SignOutGooglePlayServices()
  {
    if (this.Packaging == UE3JavaBuildSettings.PackageType.GOOGLE)
    {
      signOut();
      getApplicationContext().getSharedPreferences(getPackageName(), 0).edit().putBoolean("GoogleLoginDeclined", true).commit();
      NativeCallback_GPSOnLoginChanged(false, "", "");
      return;
    }
    Logger.LogOut("SignOutGooglePlayServices: Not using google packaging");
  }
  
  public void StartVideo(final FileDescriptor paramFileDescriptor, final long paramLong1, long paramLong2, final boolean paramBoolean1, final boolean paramBoolean2, final String paramString, boolean paramBoolean3)
  {
    StopVideo();
    this.bIsMovieSkippable = paramBoolean3;
    Logger.LogOut("StartVideo Called for " + paramString + " FD: " + paramFileDescriptor);
    if (this.MoviePrepareThread != null) {}
    try
    {
      this.MoviePrepareThread.join();
      this.MoviePrepareThread = null;
      this.handler.post(new Runnable()
      {
        public void run()
        {
          Logger.LogOut("StartVideo Async");
          if (!paramBoolean2)
          {
            UE3JavaApp.this.bIsLoadingMoviePlaying.getAndSet(true);
            UE3JavaApp.access$2302(UE3JavaApp.this, false);
            Logger.LogOut(paramString + " is Not a movie, setting loading image movie");
          }
          UE3JavaApp.this.bIsMoviePlaying.getAndSet(true);
          Object localObject;
          if (UE3JavaApp.this.videoLayout == null)
          {
            localObject = UE3JavaApp.this.getLayoutInflater();
            UE3JavaApp.access$2502(UE3JavaApp.this, (RelativeLayout)((LayoutInflater)localObject).inflate(R.layout.movielayout, null));
            UE3JavaApp.this.addContentView(UE3JavaApp.this.videoLayout, new ViewGroup.LayoutParams(-1, -1));
          }
          if (UE3JavaApp.this.videoBackgroundView == null)
          {
            localObject = UE3JavaApp.this;
            Bitmap.Config localConfig = Bitmap.Config.RGB_565;
            UE3JavaApp.access$2702((UE3JavaApp)localObject, Bitmap.createBitmap(new int[] { 0, 0, 0, 0 }, 2, 2, localConfig));
            UE3JavaApp.access$2602(UE3JavaApp.this, new ImageView(jdField_this));
            if (!paramBoolean2) {
              break label416;
            }
            UE3JavaApp.this.videoBackgroundView.setImageBitmap(UE3JavaApp.this.loadingImage);
          }
          try
          {
            for (;;)
            {
              UE3JavaApp.this.videoBackgroundView.setScaleType(ImageView.ScaleType.FIT_XY);
              UE3JavaApp.this.videoLayout.addView(UE3JavaApp.this.videoBackgroundView, new RelativeLayout.LayoutParams(-1, -1));
              if (!paramBoolean2) {
                break;
              }
              if (UE3JavaApp.this.videoView == null)
              {
                UE3JavaApp.access$2802(UE3JavaApp.this, new SurfaceView(jdField_this));
                UE3JavaApp.this.videoView.setZOrderMediaOverlay(true);
                localObject = new RelativeLayout.LayoutParams(-1, -1);
                ((RelativeLayout.LayoutParams)localObject).addRule(13);
                UE3JavaApp.this.videoLayout.addView(UE3JavaApp.this.videoView, (ViewGroup.LayoutParams)localObject);
              }
              if (paramFileDescriptor != null)
              {
                localObject = UE3JavaApp.this.videoView.getHolder();
                ((SurfaceHolder)localObject).setType(3);
                ((SurfaceHolder)localObject).addCallback(new SurfaceHolder.Callback()
                {
                  public void surfaceChanged(SurfaceHolder paramAnonymous2SurfaceHolder, int paramAnonymous2Int1, int paramAnonymous2Int2, int paramAnonymous2Int3)
                  {
                    Logger.LogOut("StartVideo.surfaceChanged called");
                  }
                  
                  public void surfaceCreated(SurfaceHolder paramAnonymous2SurfaceHolder)
                  {
                    Logger.LogOut("StartVideo.surfaceCreated called");
                    if ((UE3JavaApp.this.videoView != null) && (UE3JavaApp.this.mediaPlayer == null) && (paramAnonymous2SurfaceHolder == UE3JavaApp.this.videoView.getHolder())) {}
                    do
                    {
                      try
                      {
                        UE3JavaApp.access$2902(UE3JavaApp.this, new MediaPlayer());
                        UE3JavaApp.this.mediaPlayer.setAudioStreamType(3);
                        UE3JavaApp.this.mediaPlayer.reset();
                        UE3JavaApp.this.mediaPlayer.setDataSource(UE3JavaApp.26.this.val$MovieFD, UE3JavaApp.26.this.val$MovieOffset, UE3JavaApp.26.this.val$MovieLength);
                        UE3JavaApp.this.mediaPlayer.setDisplay(paramAnonymous2SurfaceHolder);
                        UE3JavaApp.this.mediaPlayer.setOnCompletionListener(UE3JavaApp.26.this.val$activity);
                        UE3JavaApp.this.mediaPlayer.setLooping(UE3JavaApp.26.this.val$bLoop);
                        UE3JavaApp.access$3002(UE3JavaApp.this, new Thread(new UE3JavaApp.MoviePreparationThread(UE3JavaApp.this, UE3JavaApp.this.mediaPlayer, UE3JavaApp.26.this.val$activity, UE3JavaApp.this.videoView, UE3JavaApp.26.this.val$MovieFD, UE3JavaApp.26.this.val$MovieOffset, UE3JavaApp.26.this.val$MovieLength), "MoviePrepareThread"));
                        UE3JavaApp.this.MoviePrepareThread.start();
                        return;
                      }
                      catch (Exception paramAnonymous2SurfaceHolder)
                      {
                        UE3JavaApp.this.MovieError();
                        Logger.LogOut("Couldn't start video");
                        Logger.ReportException("Couldn't start video", paramAnonymous2SurfaceHolder);
                        return;
                      }
                      Logger.LogOut("Movie Could not start media player");
                      if ((UE3JavaApp.this.videoView == null) || (paramAnonymous2SurfaceHolder != UE3JavaApp.this.videoView.getHolder())) {
                        Logger.LogOut("Movie VideoView issues preventing media player start");
                      }
                    } while (UE3JavaApp.this.mediaPlayer == null);
                    Logger.LogOut("Media player isn't null, can't setup.");
                  }
                  
                  public void surfaceDestroyed(SurfaceHolder paramAnonymous2SurfaceHolder)
                  {
                    Logger.LogOut("StartVideo.surfaceDestroyed called");
                  }
                });
              }
              localObject = (TextView)UE3JavaApp.this.findViewById(R.id.overlay_text);
              ((TextView)localObject).setText(UE3JavaApp.this.movieOverlayMessage);
              ((TextView)localObject).bringToFront();
              Logger.LogOut("Video triggered");
              return;
              label416:
              Logger.LogOut("Not a movie, setting loading image movie ASYNC");
              for (;;)
              {
                try
                {
                  if (paramString != null) {
                    break label473;
                  }
                  Logger.LogOut("Movie image name is null, can't display loading image");
                  if (UE3JavaApp.this.videoBackgroundView == null) {
                    break label518;
                  }
                  UE3JavaApp.this.videoBackgroundView.setImageBitmap(UE3JavaApp.this.loadingImage);
                }
                catch (Exception localException1)
                {
                  Logger.ReportException("Exception opening bitmap as movie ", localException1);
                }
                break;
                label473:
                UE3JavaApp.access$2702(UE3JavaApp.this, UIUtil.decodeSampledBitmapFromResource(jdField_this, jdField_this.getResources().getIdentifier(paramString.toLowerCase(), "drawable", jdField_this.getPackageName())));
              }
              label518:
              if (UE3JavaApp.this.loadingImage != null)
              {
                UE3JavaApp.this.loadingImage.recycle();
                UE3JavaApp.access$2702(UE3JavaApp.this, null);
              }
              Logger.LogOut("VideoBackgroundView is null, can't display loading image");
            }
          }
          catch (Exception localException2)
          {
            for (;;)
            {
              Logger.ReportException("trying to add to Videolayout:", localException2);
              continue;
              UE3JavaApp.access$3102(UE3JavaApp.this, new ProgressBar(jdField_this));
              UE3JavaApp.this.videoLayout.addView(UE3JavaApp.this.IndefiniteLoadingBar, new ViewGroup.LayoutParams(50, 50));
              UE3JavaApp.this.IndefiniteLoadingBar.bringToFront();
            }
          }
        }
      });
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Logger.ReportException("Couldnt join movie thread!!! " + paramString, localException);
      }
    }
  }
  
  public void StopMusic()
  {
    try
    {
      if (this.isStopped)
      {
        if (this.songPlayer != null) {
          this.songPlayer.pause();
        }
        if (this.pendingSongPlayer != null) {
          this.pendingSongPlayer.pause();
        }
      }
      return;
    }
    catch (Exception localException)
    {
      Logger.ReportException("Failed StopMusic ", localException);
    }
  }
  
  public void StopSong()
  {
    Logger.LogOut("StopSong");
    this.handler.post(new Runnable()
    {
      public void run()
      {
        if (UE3JavaApp.this.songPlayer != null) {}
        try
        {
          if (UE3JavaApp.this.songPlayer.isPlaying()) {
            UE3JavaApp.this.songPlayer.stop();
          }
          UE3JavaApp.access$4602(UE3JavaApp.this, null);
          UE3JavaApp.this.songPlayer.release();
          UE3JavaApp.access$4502(UE3JavaApp.this, null);
          if (UE3JavaApp.this.pendingSongPlayer == null) {}
        }
        catch (Exception localException1)
        {
          try
          {
            if (UE3JavaApp.this.pendingSongPlayer.isPlaying()) {
              UE3JavaApp.this.pendingSongPlayer.stop();
            }
            UE3JavaApp.access$4802(UE3JavaApp.this, null);
            UE3JavaApp.this.pendingSongPlayer.release();
            UE3JavaApp.access$4702(UE3JavaApp.this, null);
            return;
            localException1 = localException1;
            Logger.LogOut("EXCEPTION - song acting up: " + localException1.getMessage());
          }
          catch (Exception localException2)
          {
            for (;;)
            {
              Logger.LogOut("EXCEPTION - song acting up: " + localException2.getMessage());
            }
          }
        }
      }
    });
  }
  
  public void StopSound(int paramInt)
  {
    this.GSoundPool.stop(paramInt);
  }
  
  public void UnloadSoundID(int paramInt)
  {
    this.GSoundPool.unload(paramInt);
  }
  
  public void UpdateAppValues()
  {
    if (this.appLocalValues == null) {
      this.appLocalValues = new HashMap();
    }
    this.appLocalValues.put("STORAGE_ROOT", this.ContentExternalStoragePath);
    this.appLocalValues.put("BASE_DIR", ContentPath);
    this.appLocalValues.put("EXTERNAL_ROOT", this.RootExternalPath);
    String str = getLocalIpAddress();
    Logger.LogOut("Local Ip: " + str);
    if (str == null)
    {
      Logger.LogOut("Setting ip to empty string");
      this.appLocalValues.put("LOCAL_IP", "");
    }
    for (;;)
    {
      UpdateTimeOffset();
      return;
      this.appLocalValues.put("LOCAL_IP", str);
    }
  }
  
  public void UpdateNetworkStatus()
  {
    if (UE3JavaNetwork.NetworkManager != null) {
      UE3JavaNetwork.NetworkManager.updateNetworkStatus();
    }
  }
  
  public void UpdateSongPlayer(final float paramFloat)
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        if (UE3JavaApp.this.pendingSongPlayer == null) {
          return;
        }
        try
        {
          if (UE3JavaApp.this.currentSongVolume > 0.0F)
          {
            UE3JavaApp.access$5002(UE3JavaApp.this, UE3JavaApp.this.currentSongVolume - paramFloat);
            UE3JavaApp.this.songPlayer.setVolume(UE3JavaApp.this.currentSongVolume, UE3JavaApp.this.currentSongVolume);
            return;
          }
        }
        catch (Exception localException)
        {
          Logger.LogOut("EXCEPTION - song acting up: " + localException.getMessage());
          return;
        }
        if (UE3JavaApp.this.songPlayer.isPlaying()) {
          UE3JavaApp.this.songPlayer.stop();
        }
        UE3JavaApp.this.songPlayer.release();
        UE3JavaApp.access$4502(UE3JavaApp.this, UE3JavaApp.this.pendingSongPlayer);
        UE3JavaApp.access$4602(UE3JavaApp.this, UE3JavaApp.this.pendingSongName);
        UE3JavaApp.access$4702(UE3JavaApp.this, null);
        UE3JavaApp.access$4802(UE3JavaApp.this, null);
        UE3JavaApp.this.songPlayer.start();
        UE3JavaApp.this.songPlayer.setVolume(UE3JavaApp.this.settingVolume, UE3JavaApp.this.settingVolume);
        UE3JavaApp.access$5002(UE3JavaApp.this, UE3JavaApp.this.settingVolume);
      }
    });
  }
  
  public void VideoAddTextOverlay(final String paramString, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, boolean paramBoolean)
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        try
        {
          UE3JavaApp.access$3202(UE3JavaApp.this, paramString);
          if ((UE3JavaApp.this.videoLayout != null) && (!UE3JavaApp.this.bIsTipDisplayed))
          {
            TextView localTextView = (TextView)UE3JavaApp.this.findViewById(R.id.overlay_text);
            if (localTextView != null)
            {
              UE3JavaApp.access$3302(UE3JavaApp.this, true);
              localTextView.setText(UE3JavaApp.this.movieOverlayMessage);
              return;
            }
            Logger.LogOut("overlay text view is null, can't display overlay message");
            return;
          }
        }
        catch (Exception localException)
        {
          Logger.LogOut("Exception caught trying to overlay text on movie, exception: " + localException.toString());
        }
      }
    });
  }
  
  public void VideoRemoveTextOverlay()
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        boolean bool = false;
        if ((UE3JavaApp.this.videoLayout != null) && (UE3JavaApp.this.bIsTipDisplayed) && (!UE3JavaApp.this.bIsMoviePlaying.get()) && (!UE3JavaApp.this.bIsLoadingMoviePlaying.get()))
        {
          UE3JavaApp.access$3302(UE3JavaApp.this, false);
          ((TextView)UE3JavaApp.this.findViewById(R.id.overlay_text)).setText("");
          return;
        }
        StringBuilder localStringBuilder = new StringBuilder().append("VideoRemoveTextOverlay videoLayout is null");
        if (UE3JavaApp.this.videoLayout == null) {
          bool = true;
        }
        Logger.LogOut(bool + UE3JavaApp.this.bIsTipDisplayed + UE3JavaApp.this.bIsMoviePlaying.get() + UE3JavaApp.this.bIsLoadingMoviePlaying.get());
      }
    });
  }
  
  public <E extends Enum> void callNativeDelegate(PlatformInterfaceDelegateResult.PlatformInterfaceType paramPlatformInterfaceType, E paramE, PlatformInterfaceDelegateResult paramPlatformInterfaceDelegateResult)
  {
    NativeCallback_CallPlatformDelegate(PlatformInterfaceDelegateResult.Enum2Int(paramPlatformInterfaceType), PlatformInterfaceDelegateResult.Enum2Int(paramE), paramPlatformInterfaceDelegateResult);
  }
  
  public <E extends Enum> void callNativeDelegate(PlatformInterfaceDelegateResult.PlatformInterfaceType paramPlatformInterfaceType, E paramE, boolean paramBoolean)
  {
    callNativeDelegate(paramPlatformInterfaceType, paramE, new PlatformInterfaceDelegateResult(paramBoolean));
  }
  
  public void cleanupEGL()
  {
    Logger.LogOut("Full OpenGL ShutDown!!!");
    destroyEGLSurface();
    if (this.eglDisplay != null) {
      this.egl.eglMakeCurrent(this.eglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
    }
    if (this.eglContext != null) {
      this.egl.eglDestroyContext(this.eglDisplay, this.eglContext);
    }
    if (this.eglDisplay != null) {
      this.egl.eglTerminate(this.eglDisplay);
    }
    this.eglDisplay = null;
    this.eglContext = null;
    this.eglSurface = null;
  }
  
  public boolean createEGLSurface(SurfaceHolder paramSurfaceHolder)
  {
    boolean bool = false;
    try
    {
      this.eglSurface = this.egl.eglCreateWindowSurface(this.eglDisplay, this.eglConfig, paramSurfaceHolder, null);
      if (this.egl.eglGetError() == 12297)
      {
        Logger.LogOut("eglCreateWindowSurface FAILED, retrying with more restricted context");
        cleanupEGL();
        this.eglAttemptedParams.redSize = 5;
        this.eglAttemptedParams.greenSize = 6;
        this.eglAttemptedParams.blueSize = 5;
        this.eglAttemptedParams.alphaSize = 0;
        initEGL(this.eglAttemptedParams);
        this.eglSurface = this.egl.eglCreateWindowSurface(this.eglDisplay, this.eglConfig, paramSurfaceHolder, null);
      }
      Logger.LogOut("eglSurface: " + this.eglSurface + ", err: " + this.egl.eglGetError());
      if (this.eglSurface != null) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramSurfaceHolder)
    {
      for (;;)
      {
        Logger.LogOut("Failed createEGLSurface " + paramSurfaceHolder.getMessage());
        paramSurfaceHolder.printStackTrace();
        Process.killProcess(Process.myPid());
      }
    }
  }
  
  public void destroyEGLSurface()
  {
    Logger.LogOut("Begin destroyEGLSurface");
    if (this.egl != null)
    {
      if ((this.eglDisplay != null) && (this.eglSurface != null)) {
        this.egl.eglMakeCurrent(this.eglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
      }
      if (this.eglSurface != null) {
        this.egl.eglDestroySurface(this.eglDisplay, this.eglSurface);
      }
    }
    this.eglSurface = null;
    Logger.LogOut("End destroyEGLSurface");
  }
  
  public boolean dispatchGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    if (((paramMotionEvent.getSource() & 0x10) != 0) && (paramMotionEvent.getAction() == 2))
    {
      InputDeviceState localInputDeviceState = getInputDeviceState(paramMotionEvent);
      if ((localInputDeviceState != null) && (localInputDeviceState.onJoystickMotion(paramMotionEvent)))
      {
        int i = 0;
        while (i < localInputDeviceState.getAxisCount())
        {
          if (localInputDeviceState.getAxisUpdated(i)) {
            NativeCallback_JoystickAxisEvent(paramMotionEvent.getDeviceId(), paramMotionEvent.getAction(), localInputDeviceState.getAxis(i), localInputDeviceState.getAxisValue(i), paramMotionEvent.getEventTime());
          }
          i += 1;
        }
        return true;
      }
    }
    return super.dispatchGenericMotionEvent(paramMotionEvent);
  }
  
  public void ensureImmersiveMode()
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      View localView = getWindow().getDecorView();
      localView.setSystemUiVisibility(5894);
      localView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
      {
        public void onSystemUiVisibilityChange(int paramAnonymousInt)
        {
          UE3JavaApp.this.getWindow().getDecorView().setSystemUiVisibility(5894);
        }
      });
    }
  }
  
  public String getAppLocalValue(String paramString)
  {
    return (String)this.appLocalValues.get(paramString);
  }
  
  public String getLocalIpAddress()
  {
    localObject3 = null;
    Object localObject2 = null;
    Logger.LogOut("FINDING VALID IP ADDRESS");
    Object localObject1 = localObject3;
    try
    {
      InetAddress localInetAddress1 = InetAddress.getByAddress(new byte[] { 0, 0, 0, 0 });
      localObject1 = localObject3;
      Enumeration localEnumeration1 = NetworkInterface.getNetworkInterfaces();
      localObject1 = localObject2;
      localObject3 = localObject2;
      if (localEnumeration1.hasMoreElements())
      {
        localObject1 = localObject2;
        localObject3 = (NetworkInterface)localEnumeration1.nextElement();
        localObject1 = localObject2;
        Logger.LogOut("Getting Element");
        localObject1 = localObject2;
        Enumeration localEnumeration2 = ((NetworkInterface)localObject3).getInetAddresses();
        localObject3 = localObject2;
        for (;;)
        {
          localObject2 = localObject3;
          localObject1 = localObject3;
          if (!localEnumeration2.hasMoreElements()) {
            break;
          }
          localObject1 = localObject3;
          InetAddress localInetAddress2 = (InetAddress)localEnumeration2.nextElement();
          localObject1 = localObject3;
          localObject2 = localInetAddress2.getHostAddress().toString();
          localObject1 = localObject3;
          Logger.LogOut("getLocalIpAddress: " + localInetAddress2.getHostAddress().toString());
          localObject1 = localObject3;
          if (!localInetAddress2.isLoopbackAddress())
          {
            localObject1 = localObject3;
            if (!localInetAddress2.equals(localInetAddress1))
            {
              localObject1 = localObject3;
              if (isIPAddress((String)localObject2))
              {
                localObject1 = localObject3;
                Logger.LogOut("FOUND VALID IP: " + localInetAddress2.getHostAddress().toString());
                localObject3 = localObject2;
              }
            }
          }
        }
      }
      return localObject3;
    }
    catch (Exception localException)
    {
      System.out.println(localException.toString());
      localObject3 = localObject1;
    }
  }
  
  public boolean hasAppLocalValue(String paramString)
  {
    return this.appLocalValues.containsKey(paramString);
  }
  
  public boolean initEGL(EGLConfigParms paramEGLConfigParms)
  {
    int i2 = paramEGLConfigParms.redSize;
    int i3 = paramEGLConfigParms.greenSize;
    int i4 = paramEGLConfigParms.blueSize;
    int i5 = paramEGLConfigParms.alphaSize;
    int i6 = paramEGLConfigParms.stencilSize;
    int i7 = paramEGLConfigParms.depthSize;
    int i = paramEGLConfigParms.redSize;
    int j = paramEGLConfigParms.greenSize;
    int k = paramEGLConfigParms.blueSize;
    int m = paramEGLConfigParms.alphaSize;
    int n = paramEGLConfigParms.stencilSize;
    int i1 = paramEGLConfigParms.depthSize;
    this.egl = ((EGL10)EGLContext.getEGL());
    this.egl.eglGetError();
    this.eglDisplay = this.egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
    Logger.LogOut("eglDisplay: " + this.eglDisplay + ", err: " + this.egl.eglGetError());
    Object localObject1 = new int[2];
    boolean bool = this.egl.eglInitialize(this.eglDisplay, (int[])localObject1);
    Logger.LogOut("EglInitialize returned: " + bool);
    if (!bool) {
      return false;
    }
    int i8 = this.egl.eglGetError();
    if (i8 != 12288) {
      return false;
    }
    Logger.LogOut("eglInitialize err: " + i8);
    Logger.LogOut("Config info (" + paramEGLConfigParms.redSize + ", " + paramEGLConfigParms.greenSize + ", " + paramEGLConfigParms.blueSize + ", " + paramEGLConfigParms.alphaSize + "), [" + paramEGLConfigParms.depthSize + ", " + paramEGLConfigParms.stencilSize + "]");
    localObject1 = new EGLConfig[20];
    int[] arrayOfInt = new int[1];
    Object localObject2 = this.egl;
    EGLDisplay localEGLDisplay = this.eglDisplay;
    i8 = localObject1.length;
    bool = ((EGL10)localObject2).eglChooseConfig(localEGLDisplay, new int[] { 12338, 1, 12337, 4, 12324, i2, 12323, i3, 12322, i4, 12321, i5, 12326, i6, 12325, i7, 12325, 16, 12352, 4, 12344 }, (EGLConfig[])localObject1, i8, arrayOfInt);
    if ((!bool) || (arrayOfInt[0] == 0))
    {
      localObject2 = this.egl;
      localEGLDisplay = this.eglDisplay;
      i2 = localObject1.length;
      bool = ((EGL10)localObject2).eglChooseConfig(localEGLDisplay, new int[] { 12324, i, 12323, j, 12322, k, 12321, m, 12326, n, 12325, i1, 12325, 16, 12352, 4, 12344 }, (EGLConfig[])localObject1, i2, arrayOfInt);
    }
    Logger.LogOut("eglChooseConfig ret: " + bool);
    Logger.LogOut("eglChooseConfig err: " + this.egl.eglGetError());
    Logger.LogOut("eglChooseConfig count: " + arrayOfInt[0]);
    if (arrayOfInt[0] == 0) {
      return false;
    }
    i3 = 0;
    m = 1000000;
    n = 1000000;
    i2 = 0;
    i1 = 0;
    localObject2 = new int[1];
    k = 0;
    if (k < arrayOfInt[0])
    {
      this.egl.eglGetConfigAttrib(this.eglDisplay, localObject1[k], 12324, (int[])localObject2);
      int i11 = localObject2[0];
      this.egl.eglGetConfigAttrib(this.eglDisplay, localObject1[k], 12323, (int[])localObject2);
      int i12 = localObject2[0];
      this.egl.eglGetConfigAttrib(this.eglDisplay, localObject1[k], 12322, (int[])localObject2);
      int i13 = localObject2[0];
      this.egl.eglGetConfigAttrib(this.eglDisplay, localObject1[k], 12321, (int[])localObject2);
      int i14 = localObject2[0];
      this.egl.eglGetConfigAttrib(this.eglDisplay, localObject1[k], 12325, (int[])localObject2);
      int i15 = localObject2[0];
      this.egl.eglGetConfigAttrib(this.eglDisplay, localObject1[k], 12326, (int[])localObject2);
      int i16 = localObject2[0];
      this.egl.eglGetConfigAttrib(this.eglDisplay, localObject1[k], 12338, (int[])localObject2);
      int i9 = localObject2[0];
      this.egl.eglGetConfigAttrib(this.eglDisplay, localObject1[k], 12337, (int[])localObject2);
      i8 = localObject2[0];
      Logger.LogOut("MSAA:" + i9 + ":[" + i8 + "]");
      i = 0;
      label1122:
      int i10;
      if (this.egl.eglGetConfigAttrib(this.eglDisplay, localObject1[k], 12514, (int[])localObject2))
      {
        if (localObject2[0] == 12515) {
          i = 1;
        }
      }
      else
      {
        i10 = (Math.abs(i11 - paramEGLConfigParms.redSize) + Math.abs(i12 - paramEGLConfigParms.greenSize) + Math.abs(i13 - paramEGLConfigParms.blueSize) + Math.abs(i14 - paramEGLConfigParms.alphaSize) << 24) + (Math.abs(1 - i) << 23) + (Math.abs(i15 - paramEGLConfigParms.depthSize) << 16);
        i4 = i10 + (Math.abs(i16 - paramEGLConfigParms.stencilSize) << 8);
        if ((i4 >= m) && (i3 != 0)) {
          break label1433;
        }
        Logger.LogOut("--------------------------");
        Logger.LogOut("New config chosen: " + k);
        Logger.LogOut("Config info (" + i11 + ", " + i12 + ", " + i13 + ", " + i14 + "), [" + i15 + "(" + i + "), " + i16 + "],MSAA:" + i9 + ":[" + i8 + "]");
        this.eglConfig = localObject1[k];
        this.DepthSize = i15;
        j = 1;
        i5 = i10;
        i7 = i9;
        i6 = i8;
      }
      for (;;)
      {
        k += 1;
        i3 = j;
        i2 = i7;
        i1 = i6;
        n = i5;
        m = i4;
        break;
        i = 0;
        break label1122;
        label1433:
        j = i3;
        i7 = i2;
        i6 = i1;
        i5 = n;
        i4 = m;
        if (this.metaDataTextureFormat.equals("dxt"))
        {
          j = i3;
          i7 = i2;
          i6 = i1;
          i5 = n;
          i4 = m;
          if (n == i10)
          {
            j = i3;
            i7 = i2;
            i6 = i1;
            i5 = n;
            i4 = m;
            if (i2 == i9)
            {
              j = i3;
              i7 = i2;
              i6 = i1;
              i5 = n;
              i4 = m;
              if (i1 == i8)
              {
                this.eglConfig = localObject1[k];
                this.DepthSize = i15;
                j = 1;
                Logger.LogOut("--------------------------");
                Logger.LogOut("New config chosen: " + k);
                Logger.LogOut("Config info (" + i11 + ", " + i12 + ", " + i13 + ", " + i14 + "), [" + i15 + "(" + i + "), " + i16 + "],MSAA:" + i9 + ":[" + i8 + "]");
                i7 = i2;
                i6 = i1;
                i5 = n;
                i4 = m;
              }
            }
          }
        }
      }
    }
    if (i3 == 0) {
      return false;
    }
    this.eglContext = this.egl.eglCreateContext(this.eglDisplay, this.eglConfig, EGL10.EGL_NO_CONTEXT, new int[] { 12440, 2, 12344 });
    Logger.LogOut("eglCreateContext: " + this.egl.eglGetError());
    this.gl = ((GL11)this.eglContext.getGL());
    return true;
  }
  
  public boolean makeCurrent()
  {
    for (;;)
    {
      try
      {
        if (this.eglContext == null)
        {
          Logger.LogOut("eglContext is NULL");
          return false;
        }
        if (this.eglSurface == null)
        {
          Logger.LogOut("eglSurface is NULL");
          return false;
        }
      }
      catch (Exception localException)
      {
        Logger.LogOut("Failed makeCurrent with exception:" + localException.getMessage());
        localException.printStackTrace();
        Process.killProcess(Process.myPid());
        Logger.LogOut("eglMakeCurrent succeeded");
        return true;
      }
      if (!this.egl.eglMakeCurrent(this.eglDisplay, this.eglSurface, this.eglSurface, this.eglContext))
      {
        int i = this.egl.eglGetError();
        Logger.LogOut("eglMakeCurrent err: " + i);
        if (this.egl.eglGetError() == 12302)
        {
          Logger.LogOut("EGL11.EGL_CONTEXT_LOST err: " + this.egl.eglGetError());
          Process.killProcess(Process.myPid());
          return false;
        }
        if (i != 12290) {
          break;
        }
        if (this.egl.eglGetCurrentContext() == EGL11.EGL_NO_CONTEXT)
        {
          Logger.LogOut("EGL11.current context err: no context");
          if (this.egl.eglGetCurrentDisplay() != EGL11.EGL_NO_DISPLAY) {
            break label302;
          }
          Logger.LogOut("EGL11.current context err: no display");
        }
        for (;;)
        {
          if (this.egl.eglGetCurrentSurface(12377) != EGL11.EGL_NO_SURFACE) {
            break label336;
          }
          Logger.LogOut("EGL11.current context err: no surface");
          return false;
          if (this.egl.eglGetCurrentContext().equals(this.eglContext))
          {
            Logger.LogOut("EGL11.current context err: == eglContext");
            break;
          }
          Logger.LogOut("EGL11.current context err: != eglContext");
          break;
          label302:
          if (this.egl.eglGetCurrentDisplay() == this.eglDisplay) {
            Logger.LogOut("EGL11.current context err: == eglDisplay");
          } else {
            Logger.LogOut("EGL11.current context err: != eglDisplay");
          }
        }
        label336:
        if (this.egl.eglGetCurrentSurface(12377) == this.eglSurface)
        {
          Logger.LogOut("EGL11.current context err: == eglSurface");
          return false;
        }
        Logger.LogOut("EGL11.current context err: != eglSurface");
        return false;
      }
      String str = ((GL10)this.eglContext.getGL()).glGetString(7937);
      if ((str.contains("GC1000")) || (str.toLowerCase().contains("intel"))) {
        ShowPoorDeviceMessageBox(getString(R.string.WarningTitle), getString(R.string.InsufficientCoreWarning));
      }
    }
    return false;
  }
  
  public void notifyEngineOfStatusChange()
  {
    if (this.bCanInterruptApp) {
      NativeCallback_AppOnActive();
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    Logger.LogOut("onActivityResult");
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (UE3JavaFacebook.FacebookSupport != null) {
      UE3JavaFacebook.FacebookSupport.OnAppActivityResult(paramInt1, paramInt2, paramIntent);
    }
    if (UE3JavaStore.StoreSupport != null) {
      UE3JavaStore.StoreSupport.OnAppActivityResult(paramInt1, paramInt2, paramIntent);
    }
    if (UE3JavaGooglePlayServices.PlayServicesSupport != null) {
      UE3JavaGooglePlayServices.PlayServicesSupport.onActivityResult(paramInt1, paramInt2, paramIntent);
    }
    if ((paramInt1 == 9003) && (paramInt2 == 10001)) {
      SignOutGooglePlayServices();
    }
  }
  
  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    Logger.LogOut("onCompletion called");
    StopVideo();
    this.bIsMoviePlaying.getAndSet(false);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("onConfigurationChanged(navigationHidden): ");
    String str;
    if (paramConfiguration.navigationHidden == 2)
    {
      str = "Hidden";
      Logger.LogOut(str);
      if (paramConfiguration.navigationHidden == 2) {
        break label110;
      }
    }
    label110:
    for (boolean bool = true;; bool = false)
    {
      NativeCallback_KeyPadChange(bool);
      super.onConfigurationChanged(paramConfiguration);
      if ((this.PrimaryGPUView != null) && (1 == paramConfiguration.orientation))
      {
        this.PrimaryGPUView.getLayoutParams().width = this.ScreenSizeX;
        this.PrimaryGPUView.getLayoutParams().height = this.ScreenSizeY;
      }
      return;
      str = "Visible";
      break;
    }
  }
  
  /* Error */
  public void onCreate(Bundle paramBundle)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial 3326	com/google/example/games/basegameutils/BaseGameActivity:onCreate	(Landroid/os/Bundle;)V
    //   5: aload_0
    //   6: iconst_0
    //   7: putfield 535	com/netherrealm/mkx/UE3JavaApp:bPoorDeviceInfoShowed	Z
    //   10: aload_0
    //   11: getfield 627	com/netherrealm/mkx/UE3JavaApp:mKeyboard	Lcom/netherrealm/util/CustomKeyboard;
    //   14: invokevirtual 3328	com/netherrealm/util/CustomKeyboard:init	()V
    //   17: new 3330	java/lang/ref/WeakReference
    //   20: dup
    //   21: aload_0
    //   22: invokespecial 3333	java/lang/ref/WeakReference:<init>	(Ljava/lang/Object;)V
    //   25: putstatic 3335	com/netherrealm/mkx/UE3JavaApp:AppContext	Ljava/lang/ref/WeakReference;
    //   28: aload_0
    //   29: invokevirtual 617	com/netherrealm/mkx/UE3JavaApp:NativeCallback_IsShippingBuild	()Z
    //   32: ifeq +6 -> 38
    //   35: invokestatic 3338	com/netherrealm/mkx/Logger:SuppressLogs	()V
    //   38: ldc_w 3339
    //   41: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   44: aload_0
    //   45: invokevirtual 797	com/netherrealm/mkx/UE3JavaApp:getPackageManager	()Landroid/content/pm/PackageManager;
    //   48: aload_0
    //   49: invokevirtual 798	com/netherrealm/mkx/UE3JavaApp:getPackageName	()Ljava/lang/String;
    //   52: sipush 128
    //   55: invokevirtual 3343	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   58: getfield 3349	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   61: ldc_w 3351
    //   64: invokevirtual 1727	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   67: astore_1
    //   68: new 657	java/lang/StringBuilder
    //   71: dup
    //   72: invokespecial 658	java/lang/StringBuilder:<init>	()V
    //   75: ldc_w 3353
    //   78: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: aload_1
    //   82: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: invokevirtual 674	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   88: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   91: aload_1
    //   92: ifnull +8 -> 100
    //   95: aload_0
    //   96: aload_1
    //   97: putfield 516	com/netherrealm/mkx/UE3JavaApp:metaDataTextureFormat	Ljava/lang/String;
    //   100: aload_0
    //   101: getfield 525	com/netherrealm/mkx/UE3JavaApp:Packaging	Lcom/netherrealm/mkx/UE3JavaBuildSettings$PackageType;
    //   104: getstatic 3356	com/netherrealm/mkx/UE3JavaBuildSettings$PackageType:DEVELOPMENT	Lcom/netherrealm/mkx/UE3JavaBuildSettings$PackageType;
    //   107: if_acmpne +587 -> 694
    //   110: iconst_1
    //   111: istore_2
    //   112: aload_0
    //   113: new 1144	android/os/Handler
    //   116: dup
    //   117: invokespecial 3357	android/os/Handler:<init>	()V
    //   120: putfield 407	com/netherrealm/mkx/UE3JavaApp:handler	Landroid/os/Handler;
    //   123: invokestatic 3363	com/netherrealm/util/UIMessageQueue:getInstance	()Lcom/netherrealm/util/UIMessageQueue;
    //   126: aload_0
    //   127: getfield 407	com/netherrealm/mkx/UE3JavaApp:handler	Landroid/os/Handler;
    //   130: invokevirtual 3367	com/netherrealm/util/UIMessageQueue:initWith	(Landroid/os/Handler;)V
    //   133: new 3369	android/app/ActivityManager$MemoryInfo
    //   136: dup
    //   137: invokespecial 3370	android/app/ActivityManager$MemoryInfo:<init>	()V
    //   140: astore_1
    //   141: aload_0
    //   142: ldc_w 3372
    //   145: invokevirtual 1574	com/netherrealm/mkx/UE3JavaApp:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   148: checkcast 3374	android/app/ActivityManager
    //   151: aload_1
    //   152: invokevirtual 3378	android/app/ActivityManager:getMemoryInfo	(Landroid/app/ActivityManager$MemoryInfo;)V
    //   155: aload_1
    //   156: getfield 3381	android/app/ActivityManager$MemoryInfo:availMem	J
    //   159: putstatic 916	com/netherrealm/mkx/UE3JavaApp:StartupFreeMem	J
    //   162: new 3383	android/os/Debug$MemoryInfo
    //   165: dup
    //   166: invokespecial 3384	android/os/Debug$MemoryInfo:<init>	()V
    //   169: astore_1
    //   170: aload_1
    //   171: invokestatic 3389	android/os/Debug:getMemoryInfo	(Landroid/os/Debug$MemoryInfo;)V
    //   174: aload_1
    //   175: invokevirtual 3392	android/os/Debug$MemoryInfo:getTotalPss	()I
    //   178: sipush 1024
    //   181: imul
    //   182: i2l
    //   183: putstatic 919	com/netherrealm/mkx/UE3JavaApp:StartupUsedMem	J
    //   186: aload_0
    //   187: invokevirtual 638	com/netherrealm/mkx/UE3JavaApp:getApplicationContext	()Landroid/content/Context;
    //   190: invokevirtual 644	android/content/Context:getPackageName	()Ljava/lang/String;
    //   193: astore_1
    //   194: aload_0
    //   195: invokevirtual 797	com/netherrealm/mkx/UE3JavaApp:getPackageManager	()Landroid/content/pm/PackageManager;
    //   198: iconst_0
    //   199: invokevirtual 3396	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   202: invokeinterface 3399 1 0
    //   207: astore 7
    //   209: aload 7
    //   211: invokeinterface 1870 1 0
    //   216: ifeq +36 -> 252
    //   219: aload 7
    //   221: invokeinterface 1874 1 0
    //   226: checkcast 3345	android/content/pm/ApplicationInfo
    //   229: astore 8
    //   231: aload_1
    //   232: aload 8
    //   234: getfield 3402	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   237: invokevirtual 731	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   240: ifeq -31 -> 209
    //   243: aload_0
    //   244: aload 8
    //   246: getfield 3405	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   249: putfield 599	com/netherrealm/mkx/UE3JavaApp:APKFilePath	Ljava/lang/String;
    //   252: new 657	java/lang/StringBuilder
    //   255: dup
    //   256: invokespecial 658	java/lang/StringBuilder:<init>	()V
    //   259: ldc_w 664
    //   262: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   265: aload_0
    //   266: invokevirtual 797	com/netherrealm/mkx/UE3JavaApp:getPackageManager	()Landroid/content/pm/PackageManager;
    //   269: aload_0
    //   270: invokevirtual 798	com/netherrealm/mkx/UE3JavaApp:getPackageName	()Ljava/lang/String;
    //   273: iconst_0
    //   274: invokevirtual 804	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   277: getfield 809	android/content/pm/PackageInfo:versionCode	I
    //   280: invokevirtual 667	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   283: ldc_w 669
    //   286: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   289: aload_0
    //   290: invokevirtual 638	com/netherrealm/mkx/UE3JavaApp:getApplicationContext	()Landroid/content/Context;
    //   293: invokevirtual 644	android/content/Context:getPackageName	()Ljava/lang/String;
    //   296: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   299: ldc_w 3407
    //   302: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   305: invokevirtual 674	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   308: astore_1
    //   309: aload_0
    //   310: invokevirtual 3410	com/netherrealm/mkx/UE3JavaApp:getAssets	()Landroid/content/res/AssetManager;
    //   313: ldc_w 484
    //   316: invokevirtual 3415	android/content/res/AssetManager:list	(Ljava/lang/String;)[Ljava/lang/String;
    //   319: astore 7
    //   321: aload 7
    //   323: arraylength
    //   324: istore 4
    //   326: iconst_0
    //   327: istore_3
    //   328: iload_3
    //   329: iload 4
    //   331: if_icmpge +32 -> 363
    //   334: aload 7
    //   336: iload_3
    //   337: aaload
    //   338: aload_1
    //   339: invokevirtual 731	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   342: ifeq +417 -> 759
    //   345: aload_0
    //   346: iconst_1
    //   347: putfield 514	com/netherrealm/mkx/UE3JavaApp:bIsExpansionInAPK	Z
    //   350: aload_0
    //   351: iconst_1
    //   352: putfield 512	com/netherrealm/mkx/UE3JavaApp:skipDownloader	Z
    //   355: aload_0
    //   356: aload_0
    //   357: invokevirtual 3410	com/netherrealm/mkx/UE3JavaApp:getAssets	()Landroid/content/res/AssetManager;
    //   360: putfield 2243	com/netherrealm/mkx/UE3JavaApp:AssetManagerReference	Landroid/content/res/AssetManager;
    //   363: aload_0
    //   364: invokevirtual 3410	com/netherrealm/mkx/UE3JavaApp:getAssets	()Landroid/content/res/AssetManager;
    //   367: ldc_w 3417
    //   370: invokevirtual 3421	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   373: astore_1
    //   374: iload_2
    //   375: ifeq +49 -> 424
    //   378: aload_0
    //   379: invokevirtual 3423	com/netherrealm/mkx/UE3JavaApp:NativeCallback_GetGameName	()Ljava/lang/String;
    //   382: astore_1
    //   383: new 3425	java/io/BufferedInputStream
    //   386: dup
    //   387: new 3427	java/io/FileInputStream
    //   390: dup
    //   391: new 657	java/lang/StringBuilder
    //   394: dup
    //   395: invokespecial 658	java/lang/StringBuilder:<init>	()V
    //   398: ldc_w 3429
    //   401: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   404: aload_1
    //   405: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   408: ldc_w 3431
    //   411: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   414: invokevirtual 674	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   417: invokespecial 3432	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   420: invokespecial 3435	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   423: astore_1
    //   424: aload_1
    //   425: ifnull +63 -> 488
    //   428: new 657	java/lang/StringBuilder
    //   431: dup
    //   432: sipush 512
    //   435: invokespecial 3437	java/lang/StringBuilder:<init>	(I)V
    //   438: astore 7
    //   440: aload_1
    //   441: invokevirtual 3440	java/io/InputStream:read	()I
    //   444: istore_3
    //   445: iload_3
    //   446: iconst_m1
    //   447: if_icmpeq +383 -> 830
    //   450: aload 7
    //   452: iload_3
    //   453: i2c
    //   454: invokevirtual 3443	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   457: pop
    //   458: goto -18 -> 440
    //   461: astore_1
    //   462: new 657	java/lang/StringBuilder
    //   465: dup
    //   466: invokespecial 658	java/lang/StringBuilder:<init>	()V
    //   469: ldc_w 3445
    //   472: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   475: aload_1
    //   476: invokevirtual 3446	java/io/IOException:getMessage	()Ljava/lang/String;
    //   479: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   482: invokevirtual 674	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   485: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   488: aload_0
    //   489: invokespecial 3448	com/netherrealm/mkx/UE3JavaApp:CheckForUnsupportedDevices	()V
    //   492: aload_0
    //   493: getfield 605	com/netherrealm/mkx/UE3JavaApp:appCommandLine	Ljava/lang/String;
    //   496: ifnull +32 -> 528
    //   499: aload_0
    //   500: getfield 512	com/netherrealm/mkx/UE3JavaApp:skipDownloader	Z
    //   503: ifne +16 -> 519
    //   506: aload_0
    //   507: getfield 605	com/netherrealm/mkx/UE3JavaApp:appCommandLine	Ljava/lang/String;
    //   510: ldc_w 3450
    //   513: invokevirtual 3273	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   516: ifeq +330 -> 846
    //   519: iconst_1
    //   520: istore 5
    //   522: aload_0
    //   523: iload 5
    //   525: putfield 512	com/netherrealm/mkx/UE3JavaApp:skipDownloader	Z
    //   528: iload_2
    //   529: ifeq +23 -> 552
    //   532: aload_0
    //   533: getfield 605	com/netherrealm/mkx/UE3JavaApp:appCommandLine	Ljava/lang/String;
    //   536: ldc_w 3452
    //   539: invokevirtual 3273	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   542: ifeq +310 -> 852
    //   545: aload_0
    //   546: getstatic 925	com/netherrealm/mkx/UE3JavaBuildSettings$PackageType:AMAZON	Lcom/netherrealm/mkx/UE3JavaBuildSettings$PackageType;
    //   549: putfield 525	com/netherrealm/mkx/UE3JavaApp:Packaging	Lcom/netherrealm/mkx/UE3JavaBuildSettings$PackageType;
    //   552: aload_0
    //   553: getfield 525	com/netherrealm/mkx/UE3JavaApp:Packaging	Lcom/netherrealm/mkx/UE3JavaBuildSettings$PackageType;
    //   556: getstatic 925	com/netherrealm/mkx/UE3JavaBuildSettings$PackageType:AMAZON	Lcom/netherrealm/mkx/UE3JavaBuildSettings$PackageType;
    //   559: if_acmpne +316 -> 875
    //   562: ldc_w 3454
    //   565: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   568: getstatic 1634	com/netherrealm/mkx/UE3JavaBuildSettings$PackageType:GOOGLE	Lcom/netherrealm/mkx/UE3JavaBuildSettings$PackageType;
    //   571: aload_0
    //   572: getfield 525	com/netherrealm/mkx/UE3JavaApp:Packaging	Lcom/netherrealm/mkx/UE3JavaBuildSettings$PackageType;
    //   575: if_acmpne +20 -> 595
    //   578: new 2202	com/netherrealm/mkx/UE3JavaGooglePlayServices
    //   581: dup
    //   582: aload_0
    //   583: invokespecial 3455	com/netherrealm/mkx/UE3JavaGooglePlayServices:<init>	(Lcom/netherrealm/mkx/UE3JavaApp;)V
    //   586: putstatic 2206	com/netherrealm/mkx/UE3JavaGooglePlayServices:PlayServicesSupport	Lcom/netherrealm/mkx/UE3JavaGooglePlayServices;
    //   589: getstatic 2206	com/netherrealm/mkx/UE3JavaGooglePlayServices:PlayServicesSupport	Lcom/netherrealm/mkx/UE3JavaGooglePlayServices;
    //   592: invokevirtual 3457	com/netherrealm/mkx/UE3JavaGooglePlayServices:onCreate	()V
    //   595: getstatic 925	com/netherrealm/mkx/UE3JavaBuildSettings$PackageType:AMAZON	Lcom/netherrealm/mkx/UE3JavaBuildSettings$PackageType;
    //   598: aload_0
    //   599: getfield 525	com/netherrealm/mkx/UE3JavaApp:Packaging	Lcom/netherrealm/mkx/UE3JavaBuildSettings$PackageType;
    //   602: if_acmpne +14 -> 616
    //   605: new 2034	com/netherrealm/mkx/UE3JavaGameCircle
    //   608: dup
    //   609: aload_0
    //   610: invokespecial 3458	com/netherrealm/mkx/UE3JavaGameCircle:<init>	(Lcom/netherrealm/mkx/UE3JavaApp;)V
    //   613: putstatic 2038	com/netherrealm/mkx/UE3JavaGameCircle:GameCircleSupport	Lcom/netherrealm/mkx/UE3JavaGameCircle;
    //   616: aload_0
    //   617: invokespecial 1568	com/netherrealm/mkx/UE3JavaApp:PopulateXAPKs	()V
    //   620: iconst_1
    //   621: istore 6
    //   623: iload 6
    //   625: istore 5
    //   627: iload_2
    //   628: ifne +35 -> 663
    //   631: iload 6
    //   633: istore 5
    //   635: aload_0
    //   636: invokevirtual 617	com/netherrealm/mkx/UE3JavaApp:NativeCallback_IsShippingBuild	()Z
    //   639: ifeq +24 -> 663
    //   642: iload 6
    //   644: istore 5
    //   646: aload_0
    //   647: getfield 512	com/netherrealm/mkx/UE3JavaApp:skipDownloader	Z
    //   650: ifne +13 -> 663
    //   653: aload_0
    //   654: invokevirtual 3460	com/netherrealm/mkx/UE3JavaApp:CanDownloadExpansionFiles	()Z
    //   657: ifne +246 -> 903
    //   660: iconst_1
    //   661: istore 5
    //   663: aload_0
    //   664: invokespecial 3462	com/netherrealm/mkx/UE3JavaApp:WarnForPoorDevice	()V
    //   667: iload 5
    //   669: ifeq +16 -> 685
    //   672: aload_0
    //   673: invokespecial 1383	com/netherrealm/mkx/UE3JavaApp:EssentialPermissionsChecked	()Z
    //   676: ifeq +9 -> 685
    //   679: aload_0
    //   680: iload 5
    //   682: invokespecial 3464	com/netherrealm/mkx/UE3JavaApp:ContinueOnCreate	(Z)V
    //   685: return
    //   686: astore_1
    //   687: aload_1
    //   688: invokevirtual 1544	java/lang/Exception:printStackTrace	()V
    //   691: goto -591 -> 100
    //   694: iconst_0
    //   695: istore_2
    //   696: goto -584 -> 112
    //   699: astore_1
    //   700: new 657	java/lang/StringBuilder
    //   703: dup
    //   704: invokespecial 658	java/lang/StringBuilder:<init>	()V
    //   707: ldc_w 3466
    //   710: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   713: aload_1
    //   714: invokevirtual 789	java/lang/Exception:toString	()Ljava/lang/String;
    //   717: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   720: invokevirtual 674	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   723: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   726: goto -540 -> 186
    //   729: astore_1
    //   730: new 657	java/lang/StringBuilder
    //   733: dup
    //   734: invokespecial 658	java/lang/StringBuilder:<init>	()V
    //   737: ldc_w 3468
    //   740: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   743: aload_1
    //   744: invokevirtual 789	java/lang/Exception:toString	()Ljava/lang/String;
    //   747: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   750: invokevirtual 674	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   753: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   756: goto -504 -> 252
    //   759: iload_3
    //   760: iconst_1
    //   761: iadd
    //   762: istore_3
    //   763: goto -435 -> 328
    //   766: astore_1
    //   767: ldc_w 3470
    //   770: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   773: goto -410 -> 363
    //   776: astore_1
    //   777: new 657	java/lang/StringBuilder
    //   780: dup
    //   781: invokespecial 658	java/lang/StringBuilder:<init>	()V
    //   784: ldc_w 3472
    //   787: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   790: aload_1
    //   791: invokevirtual 789	java/lang/Exception:toString	()Ljava/lang/String;
    //   794: invokevirtual 662	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   797: invokevirtual 674	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   800: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   803: goto -440 -> 363
    //   806: astore_1
    //   807: aconst_null
    //   808: astore_1
    //   809: ldc_w 3474
    //   812: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   815: goto -441 -> 374
    //   818: astore_1
    //   819: ldc_w 3476
    //   822: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   825: aconst_null
    //   826: astore_1
    //   827: goto -403 -> 424
    //   830: aload_0
    //   831: aload 7
    //   833: invokevirtual 674	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   836: putfield 605	com/netherrealm/mkx/UE3JavaApp:appCommandLine	Ljava/lang/String;
    //   839: aload_1
    //   840: invokevirtual 3477	java/io/InputStream:close	()V
    //   843: goto -355 -> 488
    //   846: iconst_0
    //   847: istore 5
    //   849: goto -327 -> 522
    //   852: aload_0
    //   853: getfield 605	com/netherrealm/mkx/UE3JavaApp:appCommandLine	Ljava/lang/String;
    //   856: ldc_w 3479
    //   859: invokevirtual 3273	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   862: ifeq -310 -> 552
    //   865: aload_0
    //   866: getstatic 1634	com/netherrealm/mkx/UE3JavaBuildSettings$PackageType:GOOGLE	Lcom/netherrealm/mkx/UE3JavaBuildSettings$PackageType;
    //   869: putfield 525	com/netherrealm/mkx/UE3JavaApp:Packaging	Lcom/netherrealm/mkx/UE3JavaBuildSettings$PackageType;
    //   872: goto -320 -> 552
    //   875: aload_0
    //   876: getfield 525	com/netherrealm/mkx/UE3JavaApp:Packaging	Lcom/netherrealm/mkx/UE3JavaBuildSettings$PackageType;
    //   879: getstatic 1634	com/netherrealm/mkx/UE3JavaBuildSettings$PackageType:GOOGLE	Lcom/netherrealm/mkx/UE3JavaBuildSettings$PackageType;
    //   882: if_acmpne +12 -> 894
    //   885: ldc_w 3481
    //   888: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   891: goto -323 -> 568
    //   894: ldc_w 3483
    //   897: invokestatic 685	com/netherrealm/mkx/Logger:LogOut	(Ljava/lang/String;)V
    //   900: goto -332 -> 568
    //   903: iconst_0
    //   904: istore 5
    //   906: goto -243 -> 663
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	909	0	this	UE3JavaApp
    //   0	909	1	paramBundle	Bundle
    //   111	585	2	i	int
    //   327	436	3	j	int
    //   324	8	4	k	int
    //   520	385	5	bool1	boolean
    //   621	22	6	bool2	boolean
    //   207	625	7	localObject	Object
    //   229	16	8	localApplicationInfo	android.content.pm.ApplicationInfo
    // Exception table:
    //   from	to	target	type
    //   428	440	461	java/io/IOException
    //   440	445	461	java/io/IOException
    //   450	458	461	java/io/IOException
    //   830	843	461	java/io/IOException
    //   44	91	686	java/lang/Exception
    //   95	100	686	java/lang/Exception
    //   133	186	699	java/lang/Exception
    //   186	209	729	java/lang/Exception
    //   209	252	729	java/lang/Exception
    //   309	326	766	java/io/IOException
    //   334	363	766	java/io/IOException
    //   252	309	776	java/lang/Exception
    //   309	326	776	java/lang/Exception
    //   334	363	776	java/lang/Exception
    //   767	773	776	java/lang/Exception
    //   363	374	806	java/io/IOException
    //   378	424	818	java/io/FileNotFoundException
  }
  
  public void onDestroy()
  {
    Logger.LogOut("onDestroy");
    this.bIsDestroying = true;
    if (mPrefs != null)
    {
      SharedPreferences.Editor localEditor = mPrefs.edit();
      localEditor.putString("language", "null");
      localEditor.commit();
    }
    if (UE3JavaGameCircle.GameCircleSupport != null) {
      UE3JavaGameCircle.GameCircleSupport.OnDestroy();
    }
    if (UE3JavaStore.StoreSupport != null) {
      UE3JavaStore.StoreSupport.onDestroy();
    }
    if (UE3JavaHttpRequestHandler.Instance != null) {
      UE3JavaHttpRequestHandler.Instance.OnDestroy();
    }
    if ((this.Packaging == UE3JavaBuildSettings.PackageType.GOOGLE) && (UE3JavaGoogleLicensing.GoogleLicensing != null)) {
      UE3JavaGoogleLicensing.GoogleLicensing.onDestroy();
    }
    super.onDestroy();
    systemCleanup();
  }
  
  public void onDownloadProgress(DownloadProgressInfo paramDownloadProgressInfo)
  {
    this.averageSpeed.setText(getString(R.string.kilobytes_per_second, new Object[] { Helpers.getSpeedString(paramDownloadProgressInfo.mCurrentSpeed) }));
    this.timeRemaining.setText(getString(R.string.time_remaining, new Object[] { Helpers.getTimeRemaining(paramDownloadProgressInfo.mTimeRemaining) }));
    paramDownloadProgressInfo.mOverallTotal = paramDownloadProgressInfo.mOverallTotal;
    this.progressBar.setMax((int)(paramDownloadProgressInfo.mOverallTotal >> 8));
    this.progressBar.setProgress((int)(paramDownloadProgressInfo.mOverallProgress >> 8));
    this.progressPercent.setText(Long.toString(paramDownloadProgressInfo.mOverallProgress * 100L / paramDownloadProgressInfo.mOverallTotal) + "%");
    this.progressFraction.setText(Helpers.getDownloadProgressString(paramDownloadProgressInfo.mOverallProgress, paramDownloadProgressInfo.mOverallTotal));
  }
  
  public void onDownloadStateChanged(int paramInt)
  {
    int j = 1;
    int i = 0;
    boolean bool2;
    boolean bool1;
    switch (paramInt)
    {
    case 6: 
    case 13: 
    default: 
      Logger.LogOut("State changed");
      bool2 = true;
      bool1 = true;
      paramInt = 1;
      if (paramInt != 0)
      {
        paramInt = 0;
        label116:
        if (this.dashboard.getVisibility() != paramInt) {
          this.dashboard.setVisibility(paramInt);
        }
        if (i == 0) {
          break label435;
        }
      }
      break;
    }
    label435:
    for (paramInt = 0;; paramInt = 8)
    {
      if (this.cellMessage.getVisibility() != paramInt) {
        this.cellMessage.setVisibility(paramInt);
      }
      this.progressBar.setIndeterminate(bool1);
      SetButtonPausedState(bool2);
      do
      {
        return;
        Logger.LogOut("State idle");
        bool2 = false;
        bool1 = true;
        paramInt = j;
        break;
        Logger.LogOut("State: connecting");
        paramInt = 1;
        bool2 = false;
        bool1 = true;
        break;
        Logger.LogOut("State: downloading");
        bool2 = false;
        paramInt = 1;
        bool1 = false;
        break;
        Logger.LogOut("State: Canceled");
        bool2 = true;
        paramInt = 0;
        bool1 = false;
        break;
        Logger.LogOut("State: General fail");
        Logger.LogOut("State: Failed fetching URL");
        bool2 = true;
        paramInt = 0;
        bool1 = false;
        break;
        Logger.LogOut("State: Failed, unlicensed");
        bool2 = true;
        paramInt = 0;
        bool1 = false;
        break;
        Logger.LogOut("State: need permissions");
        paramInt = 0;
        bool2 = true;
        bool1 = false;
        i = 1;
        break;
        paramInt = 0;
        bool2 = true;
        bool1 = false;
        i = 0;
        InitializeForceExitMessage(R.string.Wifi_Disabled);
        break;
        Logger.LogOut("Need wifi");
        paramInt = 0;
        bool2 = true;
        bool1 = false;
        i = 0;
        InitializeForceExitMessage(R.string.Wifi_Disabled);
        break;
        Logger.LogOut("State: paused");
        bool2 = true;
        bool1 = false;
        paramInt = j;
        break;
        bool2 = true;
        bool1 = false;
        paramInt = j;
        break;
        Logger.LogOut("State completed");
        DeleteOldExpansionFiles();
        DecrementDownloadsCounter();
      } while ((numDownloadsRunning != 0) || (!EssentialPermissionsChecked()));
      ContinueOnCreate(true);
      return;
      paramInt = 0;
      bool2 = true;
      bool1 = false;
      i = 0;
      InitializeForceExitMessage(R.string.Out_Of_Memory);
      break;
      paramInt = 8;
      break label116;
    }
  }
  
  public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    Logger.LogOut("media error: " + paramMediaPlayer.toString() + "code: " + paramInt1 + "ext: " + paramInt2);
    return false;
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    this.mKeyboard.onKeyDown(paramInt, paramKeyEvent);
    int i = 0;
    switch (paramInt)
    {
    }
    while (i != 0)
    {
      return true;
      if (paramKeyEvent.getScanCode() == 0) {}
      for (i = 1;; i = 0) {
        break;
      }
      i = 1;
    }
    InputDeviceState localInputDeviceState = getInputDeviceState(paramKeyEvent);
    if (localInputDeviceState != null)
    {
      boolean bool = localInputDeviceState.onKeyDown(paramKeyEvent);
      i = paramKeyEvent.getKeyCode();
      if (bool)
      {
        NativeCallback_JoystickButtonEvent(paramKeyEvent.getDeviceId(), paramKeyEvent.getAction(), i, paramKeyEvent.getEventTime());
        return true;
      }
      int j = paramKeyEvent.getUnicodeChar(paramKeyEvent.getMetaState());
      NativeCallback_KeyboardEvent(paramKeyEvent.getDeviceId(), paramKeyEvent.getAction(), i, j);
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    this.mKeyboard.onKeyUp(paramInt, paramKeyEvent);
    if ((paramInt == 82) || (paramInt == 84) || (paramInt == 23)) {}
    long l;
    do
    {
      return true;
      if ((this.mKeyboard.shouldActive()) && ((paramInt == 66) || (paramInt == 4)))
      {
        JavaCallback_HideKeyBoard(false);
        return true;
      }
      if (paramInt != 4) {
        break;
      }
      l = paramKeyEvent.getEventTime();
    } while ((!this.bCanInterruptApp) || (this.bIsLoadingMoviePlaying.get()) || (this.bIsMoviePlaying.get()) || ((float)Math.abs(l - this.mPreTouchEventTime) < 500.0F));
    this.mPreBackEventTime = l;
    if (!this.bRanInit)
    {
      ShowExitDialog();
      return true;
    }
    NativeCallback_HandleBackButtonPressed();
    return true;
    return super.onKeyUp(paramInt, paramKeyEvent);
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    Logger.LogOut("onNewIntent() : " + paramIntent.getAction());
    super.onNewIntent(paramIntent);
  }
  
  protected void onPause()
  {
    Logger.LogOut("onPause");
    super.onPause();
    if (UE3JavaGameCircle.GameCircleSupport != null) {
      UE3JavaGameCircle.GameCircleSupport.OnPause();
    }
    if (this.bCanInterruptApp) {
      NativeCallback_AppOnPause();
    }
    StopMusic();
    this.bAppActive = false;
    SetInterruption(true);
    if (this.IAdjust != null) {
      this.IAdjust.onAdjustPause();
    }
    if (this.iSupersonic != null) {
      this.iSupersonic.onPause(this);
    }
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    Logger.LogOut("onRequestPermissionsResult() requestCode: " + paramInt);
    switch (paramInt)
    {
    }
    do
    {
      return;
      Logger.LogOut("Google Requested Account permissions");
      if ((paramArrayOfInt.length > 0) && (paramArrayOfInt[0] == 0))
      {
        Logger.LogOut("Permissions granted by user");
        beginUserInitiatedSignIn();
        return;
      }
      Logger.LogOut("Permissions denied by user");
      getApplicationContext().getSharedPreferences(getPackageName(), 0).edit().putBoolean("GoogleLoginDeclined", true).commit();
      NativeCallback_GPSOnLoginChanged(false, "", "");
      return;
      Logger.LogOut("Google Requested Storage permissions");
      if ((paramArrayOfInt.length <= 0) || (paramArrayOfInt[0] != 0)) {
        break;
      }
      Logger.LogOut("Permissions granted by user");
    } while (!EssentialPermissionsChecked());
    ContinueOnCreate(true);
    return;
    Logger.LogOut("Permissions denied by user");
    ShowPermissionExitDialog();
  }
  
  protected void onResume()
  {
    Logger.LogOut("onResume");
    super.onResume();
    ensureImmersiveMode();
    this.isStopped = false;
    if (this.downloaderClientStub != null) {
      this.downloaderClientStub.connect(this);
    }
    if (UE3JavaGameCircle.GameCircleSupport != null) {
      UE3JavaGameCircle.GameCircleSupport.OnResume();
    }
    if (getResources().getConfiguration().navigationHidden != 2) {}
    for (boolean bool = true;; bool = false)
    {
      NativeCallback_KeyPadChange(bool);
      UpdateNetworkStatus();
      UpdateTimeOffset();
      this.bAppActive = true;
      SetInterruption(false);
      if (this.IAdjust != null) {
        this.IAdjust.onAdjustResume();
      }
      if (this.iSupersonic != null) {
        this.iSupersonic.onResume(this);
      }
      return;
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (UE3JavaFacebook.FacebookSupport != null) {
      UE3JavaFacebook.FacebookSupport.OnAppSaveInstanceState(paramBundle);
    }
  }
  
  public void onServiceConnected(Messenger paramMessenger)
  {
    Logger.LogOut("onServiceConnected");
    this.remoteService = DownloaderServiceMarshaller.CreateProxy(paramMessenger);
    this.remoteService.onClientUpdated(this.downloaderClientStub.getMessenger());
  }
  
  public void onSignInCanceled()
  {
    if (this.Packaging == UE3JavaBuildSettings.PackageType.GOOGLE)
    {
      Logger.LogOut("onSignInCanceled from Google Play Services");
      getApplicationContext().getSharedPreferences(getPackageName(), 0).edit().putBoolean("GoogleLoginDeclined", true).commit();
      NativeCallback_GPSOnLoginChanged(false, "", "");
      return;
    }
    Logger.LogOut("onSignInCanceled: Not using google packaging");
  }
  
  public void onSignInFailed()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          if (UE3JavaApp.this.Packaging == UE3JavaBuildSettings.PackageType.GOOGLE)
          {
            Logger.LogOut("onSignInFailed from Google Play Services");
            UE3JavaApp.NativeCallback_GPSOnLoginChanged(false, "", "");
            return;
          }
          Logger.LogOut("onSignInFailed: Not using google packaging");
          return;
        }
        catch (Exception localException)
        {
          Logger.LogOut("onSignInSucceeded get account info exception: " + localException.toString());
        }
      }
    }).start();
  }
  
  public void onSignInSucceeded()
  {
    Logger.LogOut("onSignInSucceeded from Google Play Services!");
    if (this.Packaging == UE3JavaBuildSettings.PackageType.GOOGLE)
    {
      new Thread(new Runnable()
      {
        public void run()
        {
          try
          {
            String str = UE3JavaApp.this.getSignedInDisplayName();
            UE3JavaApp.NativeCallback_GPSOnLoginChanged(true, UE3JavaApp.this.getSignedInId(), str);
            return;
          }
          catch (Exception localException)
          {
            Logger.LogOut("onSignInSucceeded get account info exception: " + localException.toString());
          }
        }
      }).start();
      return;
    }
    Logger.LogOut("onSignInSucceeded: Not using google packaging");
  }
  
  protected void onStart()
  {
    super.onStart();
    Logger.LogOut("onStart");
    if (this.downloaderClientStub != null) {
      this.downloaderClientStub.connect(this);
    }
    if (UE3JavaFacebook.FacebookSupport != null) {
      UE3JavaFacebook.FacebookSupport.OnAppStart();
    }
    notifyEngineOfStatusChange();
    if (UE3JavaGooglePlayServices.PlayServicesSupport != null) {
      UE3JavaGooglePlayServices.PlayServicesSupport.onStart();
    }
    KontAgent.SetApplicationContext(this);
    this.isStopped = false;
    UpdateTimeOffset();
  }
  
  protected void onStop()
  {
    Logger.LogOut("onStop");
    super.onStop();
    if (this.mKeyboard != null) {
      this.mKeyboard.hide();
    }
    if (this.downloaderClientStub != null) {
      this.downloaderClientStub.disconnect(this);
    }
    if (mPrefs != null)
    {
      SharedPreferences.Editor localEditor = mPrefs.edit();
      localEditor.putInt("lw_performance", mPrefs.getInt("cur_performance", -1));
      localEditor.putFloat("lw_resolution_scale", mPrefs.getFloat("cur_resolution_scale", 1.0F));
      localEditor.commit();
    }
    if (UE3JavaFacebook.FacebookSupport != null) {
      UE3JavaFacebook.FacebookSupport.OnAppStop();
    }
    if (UE3JavaGooglePlayServices.PlayServicesSupport != null) {
      UE3JavaGooglePlayServices.PlayServicesSupport.onStop();
    }
    if (this.iSupersonic != null) {
      this.iSupersonic.onPause(this);
    }
    Logger.LogOut("stopEnd");
    this.isStopped = true;
    HaltMedia();
    HideReloader();
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool2 = super.onTouchEvent(paramMotionEvent);
    long l = paramMotionEvent.getEventTime();
    if ((float)Math.abs(l - this.mPreBackEventTime) < 500.0F) {
      return true;
    }
    this.mPreTouchEventTime = l;
    if ((this.bIsMoviePlaying.get()) && (this.bIsMovieSkippable) && (!this.bIsLoadingMoviePlaying.get()))
    {
      this.bIsMovieSkippable = false;
      Logger.LogOut("Movie skipping on Touch Event! ");
      StopVideo();
    }
    boolean bool1 = bool2;
    int k;
    int j;
    int i;
    if (!bool2)
    {
      k = paramMotionEvent.getAction();
      j = k & 0xFF;
      if (j == 2)
      {
        i = 0;
        for (;;)
        {
          bool1 = bool2;
          if (i >= paramMotionEvent.getPointerCount()) {
            break;
          }
          bool2 |= NativeCallback_InputEvent(2, (int)paramMotionEvent.getX(i), (int)paramMotionEvent.getY(i), paramMotionEvent.getPointerId(i) + 1, paramMotionEvent.getEventTime(), 0);
          i += 1;
        }
      }
      if (j != 5) {
        break label243;
      }
      i = 0;
      if (paramMotionEvent.getPointerCount() != 2) {
        break label256;
      }
    }
    for (bool1 = bool2 | NativeCallback_InputEvent(i, (int)paramMotionEvent.getX(0), (int)paramMotionEvent.getY(0), 1, paramMotionEvent.getEventTime(), paramMotionEvent.getPointerCount()) | NativeCallback_InputEvent(i, (int)paramMotionEvent.getX(1), (int)paramMotionEvent.getY(1), 2, paramMotionEvent.getEventTime() + 3000L, paramMotionEvent.getPointerCount());; bool1 = bool2 | NativeCallback_InputEvent(i, (int)paramMotionEvent.getX(j), (int)paramMotionEvent.getY(j), k + 1, paramMotionEvent.getEventTime(), paramMotionEvent.getPointerCount()))
    {
      return bool1;
      label243:
      i = j;
      if (j != 6) {
        break;
      }
      i = 1;
      break;
      label256:
      j = k >> 8;
      k = paramMotionEvent.getPointerId(j);
    }
  }
  
  public void onTrimMemory(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      Logger.LogOut("UE3JavaApp::onTrimMemory: no memory level given");
      return;
    case 80: 
      Logger.LogOut("UE3JavaApp::onTrimMemory: TRIM_MEMORY_COMPLETE");
      return;
    case 60: 
      Logger.LogOut("UE3JavaApp::onTrimMemory: TRIM_MEMORY_MODERATE");
      return;
    case 40: 
      Logger.LogOut("UE3JavaApp::onTrimMemory: TRIM_MEMORY_BACKGROUND");
      return;
    case 20: 
      Logger.LogOut("UE3JavaApp::onTrimMemory: TRIM_MEMORY_UI_HIDDEN");
      return;
    case 15: 
      Logger.LogOut("UE3JavaApp::onTrimMemory: TRIM_MEMORY_RUNNING_CRITICAL");
      return;
    case 10: 
      Logger.LogOut("UE3JavaApp::onTrimMemory: TRIM_MEMORY_RUNNING_LOW");
      return;
    }
    Logger.LogOut("UE3JavaApp::onTrimMemory: TRIM_MEMORY_RUNNING_MODERATE");
  }
  
  protected void onUserLeaveHint()
  {
    Logger.LogOut("onUserLeaveHint");
    super.onUserLeaveHint();
    SetInterruption(true);
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    Logger.LogOut("onWindowFocusChanged: " + paramBoolean);
    this.bWindowHasFocus = paramBoolean;
    if (!paramBoolean) {}
    for (paramBoolean = true;; paramBoolean = false)
    {
      SetInterruption(paramBoolean);
      ensureImmersiveMode();
      return;
    }
  }
  
  public void setAppLocalValue(String paramString1, String paramString2)
  {
    this.appLocalValues.put(paramString1, paramString2);
  }
  
  public boolean swapBuffers()
  {
    if ((this.eglSurface == null) || (!this.egl.eglSwapBuffers(this.eglDisplay, this.eglSurface)))
    {
      if (this.SwapBufferFailureCount > 10) {
        Process.killProcess(Process.myPid());
      }
      this.SwapBufferFailureCount += 1;
      if (this.eglSurface == null) {
        Logger.LogOut("swapBuffers: eglSurface is NULL");
      }
      do
      {
        return false;
        Logger.LogOut("swapBuffers: eglSwapBuffers err: " + this.egl.eglGetError());
      } while (this.egl.eglGetError() != 12302);
      Logger.LogOut("swapBuffers: EGL11.EGL_CONTEXT_LOST err: " + this.egl.eglGetError());
      Process.killProcess(Process.myPid());
      return false;
    }
    return true;
  }
  
  protected void systemCleanup()
  {
    Logger.LogOut("*=*=*=*= systemCleanup =*=*=*=*");
    if (this.bRanInit) {
      NativeCallback_Cleanup();
    }
    if (!this.nativeEGL) {
      cleanupEGL();
    }
    Process.killProcess(Process.myPid());
  }
  
  public boolean systemInit()
  {
    this.GSoundPool = new SoundPool(6, 3, 0);
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getRealMetrics(localDisplayMetrics);
    this.ScreenSizeX = localDisplayMetrics.widthPixels;
    this.ScreenSizeY = localDisplayMetrics.heightPixels;
    BeginOpenGLStartup();
    this.handler.post(this.UpdateNetworkTask);
    return true;
  }
  
  public boolean systemStartupCheck()
  {
    setVolumeControlStream(3);
    Object localObject2 = (ActivityManager)getSystemService("activity");
    Object localObject1 = new ActivityManager.MemoryInfo();
    ((ActivityManager)localObject2).getMemoryInfo((ActivityManager.MemoryInfo)localObject1);
    Logger.LogOut("Memory:");
    Logger.LogOut(" - minfo.availMem: " + ((ActivityManager.MemoryInfo)localObject1).availMem);
    Logger.LogOut(" - minfo.lowMemory: " + ((ActivityManager.MemoryInfo)localObject1).lowMemory);
    Logger.LogOut(" - minfo.threshold: " + ((ActivityManager.MemoryInfo)localObject1).threshold);
    localObject2 = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject2);
    float f = ((DisplayMetrics)localObject2).xdpi / 163.0F;
    this.bFullOpenGLReset = NativeCallback_SystemStats(((ActivityManager.MemoryInfo)localObject1).availMem, f);
    this.ExternalStoragePath = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/");
    this.RootExternalPath = this.ExternalStoragePath;
    this.ContentExternalStoragePath = (getApplicationContext().getFilesDir().getAbsolutePath() + "/");
    UpdateAppValues();
    Logger.LogOut("Storage Path: " + this.ContentExternalStoragePath);
    boolean bool;
    int i;
    if (getResources().getConfiguration().navigationHidden != 2)
    {
      bool = true;
      NativeCallback_KeyPadChange(bool);
      localObject1 = new File("proc/meminfo");
      i = 512;
      if (!((File)localObject1).exists()) {
        break label407;
      }
      localObject1 = ReadMemFileToString((File)localObject1);
      Logger.LogOut((String)localObject1);
      localObject1 = ((String)localObject1).split("[ ]+");
      if (localObject1.length == 3) {
        i = Integer.parseInt(localObject1[1]) / 1024;
      }
    }
    for (;;)
    {
      Logger.LogOut(" TotalMemory " + i);
      if (i >= 257) {
        break label416;
      }
      this.handler.post(new Runnable()
      {
        public void run()
        {
          new AlertDialog.Builder(jdField_this).setMessage(UE3JavaApp.this.getString(R.string.MemNotEnough)).setPositiveButton("Ok", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              UE3JavaApp.this.systemInit();
            }
          }).setCancelable(false).show();
        }
      });
      return true;
      bool = false;
      break;
      label407:
      Logger.LogOut("no Proc Meminfo");
    }
    label416:
    systemInit();
    return true;
  }
  
  public boolean unMakeCurrent()
  {
    try
    {
      if (!this.egl.eglMakeCurrent(this.eglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT))
      {
        Logger.LogOut("egl(Un)MakeCurrent err: " + this.egl.eglGetError());
        return false;
      }
    }
    catch (Exception localException)
    {
      Logger.LogOut("Failed unMakeCurrent with exception:" + localException.getMessage());
      localException.printStackTrace();
      Process.killProcess(Process.myPid());
      Logger.LogOut("eglMakeCurrent null succeeded");
    }
    return true;
  }
  
  public class EGLConfigParms
  {
    public int alphaSize = 0;
    public int blueSize = 5;
    public int depthSize = 16;
    public int greenSize = 6;
    public int redSize = 5;
    public int sampleBuffers = 0;
    public int sampleSamples = 0;
    public int stencilSize = 0;
    public int validConfig = 0;
    
    public EGLConfigParms() {}
    
    public EGLConfigParms(EGLConfigParms paramEGLConfigParms)
    {
      this.validConfig = paramEGLConfigParms.validConfig;
      this.redSize = paramEGLConfigParms.redSize;
      this.greenSize = paramEGLConfigParms.greenSize;
      this.blueSize = paramEGLConfigParms.blueSize;
      this.alphaSize = paramEGLConfigParms.alphaSize;
      this.depthSize = paramEGLConfigParms.depthSize;
      this.stencilSize = paramEGLConfigParms.stencilSize;
      this.sampleBuffers = paramEGLConfigParms.sampleBuffers;
      this.sampleSamples = paramEGLConfigParms.sampleSamples;
    }
  }
  
  private static class InputDeviceState
  {
    private final int[] mAxes;
    private final boolean[] mAxisUpdated;
    private final float[] mAxisValues;
    private final InputDevice mDevice;
    private final SparseIntArray mKeys;
    
    public InputDeviceState(InputDevice paramInputDevice)
    {
      this.mDevice = paramInputDevice;
      int j = 0;
      int i = 0;
      Object localObject;
      if (Build.VERSION.SDK_INT >= 12)
      {
        localObject = paramInputDevice.getMotionRanges().iterator();
        for (;;)
        {
          j = i;
          if (!((Iterator)localObject).hasNext()) {
            break;
          }
          if ((((InputDevice.MotionRange)((Iterator)localObject).next()).getSource() & 0x10) != 0) {
            i += 1;
          }
        }
      }
      this.mAxes = new int[j];
      this.mAxisValues = new float[j];
      this.mAxisUpdated = new boolean[j];
      i = 0;
      if (Build.VERSION.SDK_INT >= 12)
      {
        paramInputDevice = paramInputDevice.getMotionRanges().iterator();
        while (paramInputDevice.hasNext())
        {
          localObject = (InputDevice.MotionRange)paramInputDevice.next();
          if ((((InputDevice.MotionRange)localObject).getSource() & 0x10) != 0)
          {
            this.mAxes[i] = ((InputDevice.MotionRange)localObject).getAxis();
            i += 1;
          }
        }
      }
      this.mKeys = new SparseIntArray();
    }
    
    private static boolean isGameKey(int paramInt)
    {
      switch (paramInt)
      {
      default: 
        if (Build.VERSION.SDK_INT >= 12) {
          return KeyEvent.isGamepadButton(paramInt);
        }
        break;
      case 19: 
      case 20: 
      case 21: 
      case 22: 
      case 23: 
        return true;
      }
      return false;
    }
    
    public String ShowDeviceInfo()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("name : ").append(this.mDevice.getName()).append("\n");
      localStringBuilder.append("  id : ").append(this.mDevice.getId()).append("\n");
      localStringBuilder.append("axes : ").append(this.mAxes.length).append("\n");
      return localStringBuilder.toString();
    }
    
    public int getAxis(int paramInt)
    {
      return this.mAxes[paramInt];
    }
    
    public int getAxisCount()
    {
      return this.mAxes.length;
    }
    
    public boolean getAxisUpdated(int paramInt)
    {
      return this.mAxisUpdated[paramInt];
    }
    
    public float getAxisValue(int paramInt)
    {
      return this.mAxisValues[paramInt];
    }
    
    public InputDevice getDevice()
    {
      return this.mDevice;
    }
    
    public int getKeyCode(int paramInt)
    {
      return this.mKeys.keyAt(paramInt);
    }
    
    public int getKeyCount()
    {
      return this.mKeys.size();
    }
    
    public boolean isKeyPressed(int paramInt)
    {
      return this.mKeys.valueAt(paramInt) != 0;
    }
    
    public boolean onJoystickMotion(MotionEvent paramMotionEvent)
    {
      paramMotionEvent.getHistorySize();
      int i = 0;
      if (i < this.mAxes.length)
      {
        float f = paramMotionEvent.getAxisValue(this.mAxes[i]);
        if (this.mAxisValues[i] == f) {
          this.mAxisUpdated[i] = false;
        }
        for (;;)
        {
          i += 1;
          break;
          this.mAxisValues[i] = f;
          this.mAxisUpdated[i] = true;
        }
      }
      return true;
    }
    
    public boolean onKeyDown(KeyEvent paramKeyEvent)
    {
      int i = paramKeyEvent.getKeyCode();
      if ((!isGameKey(i)) || (paramKeyEvent.getRepeatCount() == 0)) {}
      try
      {
        KeyEvent.keyCodeToString(i);
        this.mKeys.put(i, 1);
        return true;
        return false;
      }
      catch (Throwable paramKeyEvent)
      {
        for (;;) {}
      }
    }
    
    public boolean onKeyUp(KeyEvent paramKeyEvent)
    {
      boolean bool = false;
      int i = paramKeyEvent.getKeyCode();
      if ((!isGameKey(i)) || (this.mKeys.indexOfKey(i) >= 0)) {}
      try
      {
        KeyEvent.keyCodeToString(i);
        this.mKeys.put(i, 0);
        bool = true;
        return bool;
      }
      catch (Throwable paramKeyEvent)
      {
        for (;;) {}
      }
    }
  }
  
  public class MoviePreparationThread
    implements Runnable
  {
    private UE3JavaApp MessageSystem;
    private FileDescriptor MovieFD;
    private long MovieLength;
    private long MovieOffset;
    private MediaPlayer OurMediaPlayer;
    private SurfaceView VideoView;
    
    public MoviePreparationThread(MediaPlayer paramMediaPlayer, UE3JavaApp paramUE3JavaApp, SurfaceView paramSurfaceView, FileDescriptor paramFileDescriptor, long paramLong1, long paramLong2)
    {
      this.OurMediaPlayer = paramMediaPlayer;
      this.MessageSystem = paramUE3JavaApp;
      this.VideoView = paramSurfaceView;
      this.MovieFD = paramFileDescriptor;
      this.MovieOffset = paramLong1;
      this.MovieLength = paramLong2;
    }
    
    public void run()
    {
      try
      {
        this.OurMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {
          public void onPrepared(MediaPlayer paramAnonymousMediaPlayer)
          {
            int k = UE3JavaApp.MoviePreparationThread.this.OurMediaPlayer.getVideoWidth();
            int m = UE3JavaApp.MoviePreparationThread.this.OurMediaPlayer.getVideoHeight();
            int j;
            if (k != 0)
            {
              i = m;
              j = k;
              if (m != 0) {}
            }
            else
            {
              i = m;
              j = k;
              if (Build.VERSION.SDK_INT >= 10)
              {
                localObject = new MediaMetadataRetriever();
                ((MediaMetadataRetriever)localObject).setDataSource(UE3JavaApp.MoviePreparationThread.this.MovieFD, UE3JavaApp.MoviePreparationThread.this.MovieOffset, UE3JavaApp.MoviePreparationThread.this.MovieLength);
                localObject = ((MediaMetadataRetriever)localObject).getFrameAtTime(0L);
                i = m;
                j = k;
                if (localObject != null)
                {
                  j = ((Bitmap)localObject).getWidth();
                  i = ((Bitmap)localObject).getHeight();
                  ((Bitmap)localObject).recycle();
                }
              }
            }
            Object localObject = new DisplayMetrics();
            UE3JavaApp.this.getWindowManager().getDefaultDisplay().getRealMetrics((DisplayMetrics)localObject);
            if (j != 0)
            {
              k = i;
              if (i != 0) {}
            }
            else
            {
              j = ((DisplayMetrics)localObject).widthPixels;
              k = ((DisplayMetrics)localObject).heightPixels;
            }
            int i = ((DisplayMetrics)localObject).heightPixels;
            m = ((DisplayMetrics)localObject).widthPixels;
            float f1 = j / k;
            float f2 = m / i;
            localObject = UE3JavaApp.MoviePreparationThread.this.VideoView.getLayoutParams();
            if (f2 > f1) {
              ((ViewGroup.LayoutParams)localObject).width = ((int)(j / k * i));
            }
            for (((ViewGroup.LayoutParams)localObject).height = i;; ((ViewGroup.LayoutParams)localObject).height = ((int)(k / j * m)))
            {
              UE3JavaApp.MoviePreparationThread.this.VideoView.setLayoutParams((ViewGroup.LayoutParams)localObject);
              UE3JavaApp.this.HideSplash();
              paramAnonymousMediaPlayer.start();
              return;
              ((ViewGroup.LayoutParams)localObject).width = m;
            }
          }
        });
      }
      catch (Exception localException2)
      {
        try
        {
          this.OurMediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener()
          {
            public void onVideoSizeChanged(MediaPlayer paramAnonymousMediaPlayer, int paramAnonymousInt1, int paramAnonymousInt2)
            {
              Logger.LogOut("MediaPlayer.onVideoSizeChanged called width: " + paramAnonymousInt1 + " height: " + paramAnonymousInt2);
              if ((paramAnonymousInt1 == 0) || (paramAnonymousInt2 == 0)) {
                return;
              }
              paramAnonymousMediaPlayer = new DisplayMetrics();
              UE3JavaApp.this.getWindowManager().getDefaultDisplay().getRealMetrics(paramAnonymousMediaPlayer);
              int i = paramAnonymousMediaPlayer.heightPixels;
              int j = paramAnonymousMediaPlayer.widthPixels;
              float f1 = paramAnonymousInt1 / paramAnonymousInt2;
              float f2 = j / i;
              paramAnonymousMediaPlayer = UE3JavaApp.MoviePreparationThread.this.VideoView.getLayoutParams();
              if (f2 > f1) {
                paramAnonymousMediaPlayer.width = ((int)(paramAnonymousInt1 / paramAnonymousInt2 * i));
              }
              for (paramAnonymousMediaPlayer.height = i;; paramAnonymousMediaPlayer.height = ((int)(paramAnonymousInt2 / paramAnonymousInt1 * j)))
              {
                UE3JavaApp.MoviePreparationThread.this.VideoView.setLayoutParams(paramAnonymousMediaPlayer);
                return;
                paramAnonymousMediaPlayer.width = j;
              }
            }
          });
        }
        catch (Exception localException2)
        {
          try
          {
            for (;;)
            {
              this.OurMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener()
              {
                public boolean onError(MediaPlayer paramAnonymousMediaPlayer, int paramAnonymousInt1, int paramAnonymousInt2)
                {
                  Logger.LogOut("MediaPlayerError: " + String.valueOf(paramAnonymousInt1) + ":" + String.valueOf(paramAnonymousInt2));
                  return false;
                }
              });
              try
              {
                this.OurMediaPlayer.prepare();
                return;
              }
              catch (Exception localException4)
              {
                Log.e("4ASDFGHJKL", localException4.toString());
                Logger.LogOut("Couldn't start video!!!");
                Logger.ReportException("Couldn't start video!!!", localException4);
                this.MessageSystem.MovieError();
              }
              localException1 = localException1;
              Log.e("1ASDFGHJKL", localException1.toString());
              Logger.LogOut("Couldn't start video!!!");
              Logger.ReportException("Couldn't start video!!!", localException1);
              this.MessageSystem.MovieError();
              continue;
              localException2 = localException2;
              Log.e("2ASDFGHJKL", localException2.toString());
              Logger.LogOut("Couldn't start video!!!");
              Logger.ReportException("Couldn't start video!!!", localException2);
              this.MessageSystem.MovieError();
            }
          }
          catch (Exception localException3)
          {
            for (;;)
            {
              Log.e("3ASDFGHJKL", localException3.toString());
              Logger.LogOut("Couldn't start video!!!");
              Logger.ReportException("Couldn't start video!!!", localException3);
              this.MessageSystem.MovieError();
            }
          }
        }
      }
    }
  }
  
  private class XAPKFile
  {
    public long FileSize;
    public int FileVersion;
    public boolean IsMain;
    
    XAPKFile()
    {
      this.IsMain = true;
      this.FileVersion = 1;
      this.FileSize = 0L;
    }
    
    XAPKFile(boolean paramBoolean, int paramInt, long paramLong)
    {
      this.IsMain = paramBoolean;
      this.FileVersion = paramInt;
      this.FileSize = paramLong;
    }
  }
}
