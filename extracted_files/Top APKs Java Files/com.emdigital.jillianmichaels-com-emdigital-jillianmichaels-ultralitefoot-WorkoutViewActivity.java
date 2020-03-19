package com.emdigital.jillianmichaels.ultralitefoot;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.location.Location;
import android.media.AudioAttributes;
import android.media.AudioAttributes.Builder;
import android.media.SoundPool;
import android.media.SoundPool.Builder;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.emdigital.jillianmichaels.engine.EventProcessing.EventProcessingQueue;
import com.emdigital.jillianmichaels.engine.EventProcessing.EventProcessingQueue.EventItem;
import com.emdigital.jillianmichaels.engine.EventProcessing.EventProcessingQueue.UIEventType;
import com.emdigital.jillianmichaels.engine.audio.AudioEngineService;
import com.emdigital.jillianmichaels.engine.audio.AudioQueue;
import com.emdigital.jillianmichaels.engine.utils.UserPreferences;
import com.emdigital.jillianmichaels.fragments.FilmStripFragment;
import com.emdigital.jillianmichaels.fragments.VisualWorkoutContainerFragment;
import com.emdigital.jillianmichaels.fragments.WorkoutProgressFragment;
import com.emdigital.jillianmichaels.fragments.WorkoutStartControllerFragment;
import com.emdigital.jillianmichaels.fragments.WorkoutTransportCallbacks;
import com.emdigital.jillianmichaels.model.Exercise;
import com.emdigital.jillianmichaels.model.Media;
import com.emdigital.jillianmichaels.model.Meme;
import com.emdigital.jillianmichaels.model.MemeType.MemeTypeKind;
import com.emdigital.jillianmichaels.model.StyleSheetObject;
import com.emdigital.jillianmichaels.model.WorkoutActivity;
import com.emdigital.jillianmichaels.model.WorkoutTemplate;
import com.emdigital.jillianmichaels.model.WorkoutTemplateDisplayActivity;
import com.emdigital.jillianmichaels.model.history.CompletedActivity;
import com.emdigital.jillianmichaels.model.history.CompletedSet;
import com.emdigital.jillianmichaels.model.history.CompletedWorkout;
import com.emdigital.jillianmichaels.recievers.MediaItemDownloadReceiver;
import com.emdigital.jillianmichaels.services.LoloDownloadManagerService;
import com.emdigital.jillianmichaels.services.LoloLocationService;
import com.emdigital.jillianmichaels.services.LoloLocationService.LoloBinder;
import com.emdigital.jillianmichaels.services.MissingMediaCalculatorService;
import com.emdigital.jillianmichaels.utills.BackgroundWorkerThread;
import com.emdigital.jillianmichaels.webapis.UltralitefootAPIService;
import com.emdigital.jillianmichaels.webapis.UltralitefootAPIService.WEB_SERVICE_TYPES;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WorkoutViewActivity
  extends BaseActivity
  implements WorkoutTemplateDisplayActivity
{
  public static final String EXTRA_DATA_EXERCISE_LIST = "extra_data_exercise_list";
  public static final String EXTRA_DATA_TEMPLATE_ID = "extra_data_template_id";
  private static final int REQUEST_CHECK_SETTINGS = 1;
  private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
  private static final String TAG = "WorkoutViewActivity";
  private CoachEncMediaCalculateReciever coachEncMediaCalculateReciever;
  private CoachEncMediaDownloadReceiver coachEncMediaDownloadReceiver;
  private WorkoutTemplate currentWorkout;
  private Observable<Double> debugAccuracySubject;
  private Observable<Double> debugDistanceSubject;
  private Observable<Double> debugSpeedSubject;
  private DrawerLayout drawerLayout;
  private AudioEngineService engineService;
  private ImageView filmStripOpeningBtn;
  private FragmentManager fragmentManager;
  private Timer gpsNotificationAutoCloseTimer;
  private boolean listeningToLocationUpdate;
  private RelativeLayout logDataDialog;
  private boolean mBound = false;
  private ServiceConnection mConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      paramAnonymousComponentName = (LoloLocationService.LoloBinder)paramAnonymousIBinder;
      WorkoutViewActivity.access$502(WorkoutViewActivity.this, paramAnonymousComponentName.getService());
      WorkoutViewActivity.access$1002(WorkoutViewActivity.this, true);
      WorkoutViewActivity.this.startListeningToLocationUpdates();
      paramAnonymousComponentName.instantiatePublishers();
      if (WorkoutViewActivity.this.currentWorkout != null) {
        WorkoutViewActivity.this.currentWorkout.setLocationObservable(paramAnonymousComponentName.getLocationObservable());
      }
      WorkoutViewActivity.access$1202(WorkoutViewActivity.this, paramAnonymousComponentName.getSpeedObservable());
    }
    
    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      WorkoutViewActivity.access$1002(WorkoutViewActivity.this, false);
      if (WorkoutViewActivity.this.currentWorkout != null) {
        WorkoutViewActivity.this.currentWorkout.setLocationObservable(null);
      }
    }
  };
  private LoloLocationService mService;
  private ImageView musicBtn;
  private OnFailureListener onFailureListener = new OnFailureListener()
  {
    public void onFailure(@NonNull Exception paramAnonymousException)
    {
      int i = ((ApiException)paramAnonymousException).getStatusCode();
      if (i != 6)
      {
        if (i != 8502) {
          return;
        }
        Log.e(WorkoutViewActivity.TAG, "Location services not available. Shutting down GPS updates");
        Toast.makeText(WorkoutViewActivity.this, "Location services not available. Shutting down GPS updates", 1).show();
        WorkoutViewActivity.this.shutDownGPS();
        return;
      }
      Log.i(WorkoutViewActivity.TAG, "Location settings are not satisfied. Attempting to upgrade location settings ");
      try
      {
        ((ResolvableApiException)paramAnonymousException).startResolutionForResult(WorkoutViewActivity.this, 1);
        return;
      }
      catch (IntentSender.SendIntentException paramAnonymousException)
      {
        for (;;) {}
      }
      Log.i(WorkoutViewActivity.TAG, "PendingIntent unable to execute request.");
    }
  };
  private ProgressBar progressBar;
  private TextView repsAdjustedNumberTxtView;
  private ImageView restIntervalSetting;
  private LinearLayout selectActivityOptionDialog;
  private TextView setTitleTextView;
  private boolean shouldWorkoutAutoResume;
  private int[] soundIds;
  private SoundPool soundPool;
  private Observable<Location> speedSubject;
  private int templateId;
  private boolean unreliableGPSDialogThrown = false;
  private Dialog unreliableGPSNotifierDialog;
  private BroadcastReceiver unreliableLocUpdateReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (paramAnonymousIntent != null)
      {
        paramAnonymousContext = paramAnonymousIntent.getAction();
        if ((!TextUtils.isEmpty(paramAnonymousContext)) && (paramAnonymousContext.equalsIgnoreCase("com.emdigital.jillianmichaels_no_reliable_loc_update_available")) && ((WorkoutViewActivity.this.unreliableGPSNotifierDialog == null) || (!WorkoutViewActivity.this.unreliableGPSNotifierDialog.isShowing())))
        {
          WorkoutViewActivity.this.playSound(WorkoutViewActivity.LoloSoundType.workout_error);
          WorkoutViewActivity.this.unreliableGPSNotifier();
          WorkoutViewActivity.access$402(WorkoutViewActivity.this, new Timer());
          WorkoutViewActivity.this.gpsNotificationAutoCloseTimer.schedule(new TimerTask()
          {
            public void run()
            {
              if ((WorkoutViewActivity.this.unreliableGPSNotifierDialog != null) && (WorkoutViewActivity.this.unreliableGPSNotifierDialog.isShowing()))
              {
                WorkoutViewActivity.this.unreliableGPSNotifierDialog.dismiss();
                WorkoutViewActivity.access$202(WorkoutViewActivity.this, null);
                if (WorkoutViewActivity.this.mService != null) {
                  WorkoutViewActivity.this.mService.setUserWantContinueWithBadGPS(true);
                }
              }
            }
          }, 20000L);
        }
      }
    }
  };
  private List<WorkoutTransportCallbacks> workoutFrags;
  private Disposable workoutPlaybackStateSubscription;
  
  public WorkoutViewActivity() {}
  
  private void bindLocationService()
  {
    if (checkPermissions())
    {
      Log.i(TAG, "Starting Location Service");
      bindService(new Intent(this, LoloLocationService.class), this.mConnection, 1);
      return;
    }
    requestPermissions();
  }
  
  private boolean checkPermissions()
  {
    return ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0;
  }
  
  private void engineStateChanged(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      showSystemUIButtons();
      stopLocationUpdates();
      return;
    }
    hideSystemUIButtons();
    startLocationUpdates();
  }
  
  private int getSoftButtonsBarHeight()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    int i = localDisplayMetrics.widthPixels;
    getWindowManager().getDefaultDisplay().getRealMetrics(localDisplayMetrics);
    int j = localDisplayMetrics.widthPixels;
    if (j > i) {
      return j - i;
    }
    return 0;
  }
  
  private Media getVideoMediaForWorkoutActivity(@NonNull WorkoutActivity paramWorkoutActivity)
  {
    return (Media)paramWorkoutActivity.chosenExercise().getFirstAssignedMemeOfType(MemeType.MemeTypeKind.Exercise_Execution).getAvailableVideoMedia().get(0);
  }
  
  private void hideSystemUIButtons()
  {
    getWindow().getDecorView().setSystemUiVisibility(3846);
  }
  
  private void initLogDataDialog(CompletedActivity paramCompletedActivity, List<CompletedSet> paramList)
  {
    if ((paramList != null) && (paramList.size() > 0))
    {
      this.logDataDialog.setVisibility(0);
      this.logDataDialog.setTag(paramList);
      findViewById(2131296831).setEnabled(true);
      this.setTitleTextView.setText(paramCompletedActivity.exerciseName);
      this.repsAdjustedNumberTxtView.setText(String.valueOf(((CompletedSet)paramList.get(0)).getReps()));
      if (((CompletedSet)paramList.get(0)).getReps() > 0L)
      {
        findViewById(2131296830).setEnabled(true);
        return;
      }
      findViewById(2131296830).setEnabled(false);
    }
  }
  
  private void initSoundPool()
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      AudioAttributes localAudioAttributes = new AudioAttributes.Builder().setUsage(14).setContentType(4).build();
      this.soundPool = new SoundPool.Builder().setAudioAttributes(localAudioAttributes).build();
      return;
    }
    this.soundPool = new SoundPool(5, 3, 0);
  }
  
  private void initSounds()
  {
    initSoundPool();
    loadSounds();
  }
  
  private void loadSounds()
  {
    if (this.soundPool != null)
    {
      this.soundIds = new int[LoloSoundType.values().length];
      LoloSoundType[] arrayOfLoloSoundType = LoloSoundType.values();
      int k = arrayOfLoloSoundType.length;
      int j = 0;
      int i = 0;
      while (j < k)
      {
        LoloSoundType localLoloSoundType = arrayOfLoloSoundType[j];
        int m = getResources().getIdentifier(localLoloSoundType.toString(), "raw", getPackageName());
        this.soundIds[i] = this.soundPool.load(getApplicationContext(), m, 1);
        j += 1;
        i += 1;
      }
    }
  }
  
  private boolean packageExists(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  private void releaseSoundPool()
  {
    if (this.soundPool != null)
    {
      LoloSoundType[] arrayOfLoloSoundType = LoloSoundType.values();
      int j = arrayOfLoloSoundType.length;
      int i = 0;
      while (i < j)
      {
        LoloSoundType localLoloSoundType = arrayOfLoloSoundType[i];
        this.soundPool.unload(localLoloSoundType.ordinal());
        i += 1;
      }
      this.soundPool.release();
    }
  }
  
  private void requestPermissions()
  {
    if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.ACCESS_FINE_LOCATION"))
    {
      Snackbar.make(findViewById(2131297059), 2131755335, 0).setAction(2131755055, new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ActivityCompat.requestPermissions(WorkoutViewActivity.this, new String[] { "android.permission.ACCESS_FINE_LOCATION" }, 34);
        }
      }).show();
      shutDownGPS();
      return;
    }
    Log.i(TAG, "Requesting permission");
    ActivityCompat.requestPermissions(this, new String[] { "android.permission.ACCESS_FINE_LOCATION" }, 34);
  }
  
  private void returnToMainActivity()
  {
    if (this.currentWorkout != null)
    {
      this.currentWorkout.finished();
      CompletedWorkout localCompletedWorkout = this.currentWorkout.getCompletedWorkout();
      if ((localCompletedWorkout != null) && (!localCompletedWorkout.isFinished()))
      {
        Log.e(TAG, "completed workout not finished??");
        localCompletedWorkout.finished();
      }
      this.currentWorkout = null;
    }
    startActivity(new Intent(this, MainActivity.class));
    finish();
  }
  
  private void sendIntensityEvent(Integer paramInteger)
  {
    UserPreferences.setUserIntensityOffset(UserPreferences.UserIntensityOffset() + paramInteger.intValue());
    sendUIEvent(EventProcessingQueue.UIEventType.CHANGE_INSTENSITY, this.currentWorkout, paramInteger);
  }
  
  private void sendUIEvent(EventProcessingQueue.UIEventType paramUIEventType, StyleSheetObject paramStyleSheetObject, Object paramObject)
  {
    paramUIEventType = new EventProcessingQueue.EventItem(paramUIEventType, paramStyleSheetObject, paramObject);
    paramStyleSheetObject = TAG;
    paramObject = new StringBuilder();
    paramObject.append("SUB");
    paramObject.append(paramUIEventType.toString());
    Log.d(paramStyleSheetObject, paramObject.toString());
    EventProcessingQueue.sharedEventQueue().getEventSubject().onNext(paramUIEventType);
  }
  
  private boolean shouldCheckReview()
  {
    boolean bool = false;
    try
    {
      if (UserPreferences.getLastReviewedVersion() != null)
      {
        String str1 = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        if (str1.equals(str1)) {
          return false;
        }
      }
      long l = CompletedWorkout.getStaticDao(CompletedWorkout.class).countOf();
      int i = UserPreferences.showReviewOnNumberWorkouts();
      if (l >= i) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      String str2 = TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Error figuring out package name?");
      localStringBuilder.append(localNameNotFoundException.getMessage());
      Log.e(str2, localStringBuilder.toString());
    }
    return false;
  }
  
  private void showPermissionRequiredSnackBar()
  {
    Snackbar.make(findViewById(2131297059), 2131755335, 0).setAction(2131755055, new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ActivityCompat.requestPermissions(WorkoutViewActivity.this, new String[] { "android.permission.ACCESS_FINE_LOCATION" }, 34);
      }
    }).show();
  }
  
  private void showSystemUIButtons()
  {
    getWindow().clearFlags(3846);
    getWindow().getDecorView().setSystemUiVisibility(1792);
  }
  
  private void shutDownGPS()
  {
    stopLocationUpdates();
    unbindLocationService();
    if (this.currentWorkout != null) {
      this.currentWorkout.setLocationObservable(null);
    }
  }
  
  private void startListeningToLocationUpdates()
  {
    this.listeningToLocationUpdate = true;
    Log.i(TAG, "Starting Location updates.");
    this.mService.startLocationUpdates(this.onFailureListener);
  }
  
  private void stopLocationUpdates()
  {
    if (this.mService != null)
    {
      Log.i(TAG, "Stopping Location updates.");
      this.mService.stopLocationUpdates();
    }
    this.listeningToLocationUpdate = false;
  }
  
  private void translateMusicBtnIn()
  {
    Object localObject;
    StringBuilder localStringBuilder;
    if (this.musicBtn.getLeft() > 0)
    {
      localObject = TAG;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Translating in from : ");
      localStringBuilder.append(this.musicBtn.getLeft());
      Log.i((String)localObject, localStringBuilder.toString());
      localObject = TAG;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Translating in to : ");
      localStringBuilder.append(this.musicBtn.getLeft() - getSoftButtonsBarHeight());
      Log.i((String)localObject, localStringBuilder.toString());
      localObject = ObjectAnimator.ofFloat(this.musicBtn, "x", new float[] { this.musicBtn.getLeft(), this.musicBtn.getLeft() - getSoftButtonsBarHeight() });
      ((ObjectAnimator)localObject).setDuration(250L);
      ((ObjectAnimator)localObject).start();
    }
    if (this.restIntervalSetting.getLeft() > 0)
    {
      localObject = TAG;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Translating in from : ");
      localStringBuilder.append(this.restIntervalSetting.getLeft());
      Log.i((String)localObject, localStringBuilder.toString());
      localObject = TAG;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Translating in to : ");
      localStringBuilder.append(this.restIntervalSetting.getLeft() - getSoftButtonsBarHeight());
      Log.i((String)localObject, localStringBuilder.toString());
      localObject = ObjectAnimator.ofFloat(this.restIntervalSetting, "x", new float[] { this.restIntervalSetting.getLeft(), this.restIntervalSetting.getLeft() - getSoftButtonsBarHeight() });
      ((ObjectAnimator)localObject).setDuration(250L);
      ((ObjectAnimator)localObject).start();
    }
  }
  
  private void translateMusicBtnOut()
  {
    Object localObject;
    StringBuilder localStringBuilder;
    if (this.musicBtn.getLeft() > 0)
    {
      localObject = TAG;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Translating out from : ");
      localStringBuilder.append(this.musicBtn.getLeft());
      Log.i((String)localObject, localStringBuilder.toString());
      localObject = TAG;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Translating out to : ");
      localStringBuilder.append(this.musicBtn.getLeft() + getSoftButtonsBarHeight());
      Log.i((String)localObject, localStringBuilder.toString());
      localObject = ObjectAnimator.ofFloat(this.musicBtn, "x", new float[] { this.musicBtn.getLeft() - getSoftButtonsBarHeight(), this.musicBtn.getLeft() });
      ((ObjectAnimator)localObject).setDuration(500L);
      ((ObjectAnimator)localObject).start();
    }
    if (this.restIntervalSetting.getLeft() > 0)
    {
      localObject = TAG;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Translating out from : ");
      localStringBuilder.append(this.restIntervalSetting.getLeft());
      Log.i((String)localObject, localStringBuilder.toString());
      localObject = TAG;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Translating out to : ");
      localStringBuilder.append(this.restIntervalSetting.getLeft() + getSoftButtonsBarHeight());
      Log.i((String)localObject, localStringBuilder.toString());
      localObject = ObjectAnimator.ofFloat(this.restIntervalSetting, "x", new float[] { this.restIntervalSetting.getLeft() - getSoftButtonsBarHeight(), this.restIntervalSetting.getLeft() });
      ((ObjectAnimator)localObject).setDuration(500L);
      ((ObjectAnimator)localObject).start();
    }
  }
  
  private void unbindLocationService()
  {
    if (this.mBound)
    {
      unbindService(this.mConnection);
      this.mBound = false;
      this.mService = null;
      this.speedSubject = null;
      this.debugAccuracySubject = null;
      this.debugDistanceSubject = null;
      this.debugSpeedSubject = null;
    }
  }
  
  private void unreliableGPSNotifier()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setCancelable(false);
    localBuilder.setMessage(2131755433);
    localBuilder.setPositiveButton(2131755155, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        if (WorkoutViewActivity.this.mService != null) {
          WorkoutViewActivity.this.mService.setUserWantContinueWithBadGPS(true);
        }
      }
    });
    localBuilder.setNegativeButton(2131755378, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        if (WorkoutViewActivity.this.mService != null) {
          WorkoutViewActivity.this.mService.setUserWantContinueWithBadGPS(false);
        }
        WorkoutViewActivity.this.shutDownGPS();
      }
    });
    localBuilder.setOnDismissListener(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        if (WorkoutViewActivity.this.gpsNotificationAutoCloseTimer != null)
        {
          WorkoutViewActivity.this.gpsNotificationAutoCloseTimer.cancel();
          WorkoutViewActivity.access$402(WorkoutViewActivity.this, null);
        }
        WorkoutViewActivity.this.engineStateChanged(false);
        if (WorkoutViewActivity.this.currentWorkout != null) {
          WorkoutViewActivity.this.sendUIEvent(EventProcessingQueue.UIEventType.PLAY, WorkoutViewActivity.this.currentWorkout, null);
        }
      }
    });
    this.unreliableGPSNotifierDialog = localBuilder.show();
  }
  
  public void checkReviewStatus()
  {
    final long l;
    Intent localIntent;
    if (shouldCheckReview())
    {
      l = CompletedWorkout.getStaticDao(CompletedWorkout.class).countOf();
      localIntent = new Intent("android.intent.action.SYNC", null, getApplicationContext(), UltralitefootAPIService.class);
      localIntent.putExtra("uid", UserPreferences.getDeviceId(this));
      localIntent.putExtra("app_bundle_id", getPackageName());
    }
    try
    {
      localIntent.putExtra("app_version", getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
      localIntent.putExtra("status_reciever", new ResultReceiver(new Handler())
      {
        /* Error */
        protected void onReceiveResult(int paramAnonymousInt, Bundle paramAnonymousBundle)
        {
          // Byte code:
          //   0: iload_1
          //   1: iconst_3
          //   2: if_icmpne +168 -> 170
          //   5: aload_2
          //   6: ldc 31
          //   8: invokevirtual 37	android/os/Bundle:getParcelable	(Ljava/lang/String;)Landroid/os/Parcelable;
          //   11: checkcast 39	com/emdigital/jillianmichaels/webapis/WebApiResponse
          //   14: astore_2
          //   15: aload_2
          //   16: invokevirtual 43	com/emdigital/jillianmichaels/webapis/WebApiResponse:getException	()Ljava/lang/Exception;
          //   19: ifnonnull +151 -> 170
          //   22: aload_2
          //   23: invokevirtual 47	com/emdigital/jillianmichaels/webapis/WebApiResponse:getHttpStatusCode	()I
          //   26: sipush 200
          //   29: if_icmpne +141 -> 170
          //   32: aload_2
          //   33: invokevirtual 51	com/emdigital/jillianmichaels/webapis/WebApiResponse:getJsonResponseString	()Ljava/lang/String;
          //   36: astore_2
          //   37: aload_2
          //   38: invokestatic 57	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
          //   41: ifne +129 -> 170
          //   44: new 59	org/json/JSONObject
          //   47: dup
          //   48: aload_2
          //   49: invokespecial 62	org/json/JSONObject:<init>	(Ljava/lang/String;)V
          //   52: astore_2
          //   53: aload_2
          //   54: ifnull +116 -> 170
          //   57: aload_2
          //   58: ldc 64
          //   60: invokevirtual 68	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
          //   63: getstatic 74	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
          //   66: invokevirtual 78	java/lang/Boolean:booleanValue	()Z
          //   69: if_icmpne +101 -> 170
          //   72: aload_0
          //   73: getfield 17	com/emdigital/jillianmichaels/ultralitefoot/WorkoutViewActivity$1:this$0	Lcom/emdigital/jillianmichaels/ultralitefoot/WorkoutViewActivity;
          //   76: invokevirtual 81	com/emdigital/jillianmichaels/ultralitefoot/WorkoutViewActivity:displayReviewDialog	()V
          //   79: aload_0
          //   80: getfield 17	com/emdigital/jillianmichaels/ultralitefoot/WorkoutViewActivity$1:this$0	Lcom/emdigital/jillianmichaels/ultralitefoot/WorkoutViewActivity;
          //   83: invokevirtual 85	com/emdigital/jillianmichaels/ultralitefoot/WorkoutViewActivity:getPackageManager	()Landroid/content/pm/PackageManager;
          //   86: aload_0
          //   87: getfield 17	com/emdigital/jillianmichaels/ultralitefoot/WorkoutViewActivity$1:this$0	Lcom/emdigital/jillianmichaels/ultralitefoot/WorkoutViewActivity;
          //   90: invokevirtual 88	com/emdigital/jillianmichaels/ultralitefoot/WorkoutViewActivity:getPackageName	()Ljava/lang/String;
          //   93: iconst_0
          //   94: invokevirtual 94	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
          //   97: getfield 100	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
          //   100: invokestatic 105	com/emdigital/jillianmichaels/engine/utils/UserPreferences:setLastReviewedVersion	(Ljava/lang/String;)V
          //   103: aload_2
          //   104: ldc 107
          //   106: invokevirtual 111	org/json/JSONObject:optInt	(Ljava/lang/String;)I
          //   109: ifeq +61 -> 170
          //   112: aload_0
          //   113: getfield 19	com/emdigital/jillianmichaels/ultralitefoot/WorkoutViewActivity$1:val$numCompleted	J
          //   116: l2i
          //   117: aload_2
          //   118: ldc 107
          //   120: invokevirtual 111	org/json/JSONObject:optInt	(Ljava/lang/String;)I
          //   123: iadd
          //   124: invokestatic 115	com/emdigital/jillianmichaels/engine/utils/UserPreferences:setShowReviewOnNumberWorkouts	(I)V
          //   127: return
          //   128: astore_2
          //   129: invokestatic 118	com/emdigital/jillianmichaels/ultralitefoot/WorkoutViewActivity:access$100	()Ljava/lang/String;
          //   132: astore_3
          //   133: new 120	java/lang/StringBuilder
          //   136: dup
          //   137: invokespecial 122	java/lang/StringBuilder:<init>	()V
          //   140: astore 4
          //   142: aload 4
          //   144: ldc 124
          //   146: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   149: pop
          //   150: aload 4
          //   152: aload_2
          //   153: invokevirtual 131	org/json/JSONException:getMessage	()Ljava/lang/String;
          //   156: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   159: pop
          //   160: aload_3
          //   161: aload 4
          //   163: invokevirtual 134	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   166: invokestatic 140	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
          //   169: pop
          //   170: return
          //   171: astore_2
          //   172: return
          // Local variable table:
          //   start	length	slot	name	signature
          //   0	173	0	this	1
          //   0	173	1	paramAnonymousInt	int
          //   0	173	2	paramAnonymousBundle	Bundle
          //   132	29	3	str	String
          //   140	22	4	localStringBuilder	StringBuilder
          // Exception table:
          //   from	to	target	type
          //   5	53	128	org/json/JSONException
          //   57	79	128	org/json/JSONException
          //   79	103	128	org/json/JSONException
          //   103	127	128	org/json/JSONException
          //   79	103	171	android/content/pm/PackageManager$NameNotFoundException
        }
      });
      localIntent.putExtra("completed_workouts", l);
      localIntent.putExtra("WS_Type", UltralitefootAPIService.WEB_SERVICE_TYPES.WS_REVIEW_CHECK.ordinal());
      startService(localIntent);
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return;
  }
  
  public void decreaseIntensity()
  {
    sendIntensityEvent(Integer.valueOf(-1));
  }
  
  public void displayReviewDialog()
  {
    if ((this.isRunning) && (!this.unreliableGPSDialogThrown))
    {
      this.unreliableGPSDialogThrown = true;
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
      localBuilder.setTitle("Loving the app?  Leave a 5 star review!");
      localBuilder.setPositiveButton("Doing awesome!", new WorkoutViewActivity..Lambda.9(this));
      localBuilder.setNegativeButton("Having trouble.", new WorkoutViewActivity..Lambda.10(this));
      localBuilder.create().show();
    }
  }
  
  public AudioQueue getAudioQueue()
  {
    return ((UltraliteFootApplication)getApplication()).getAudioQueue();
  }
  
  public WorkoutTemplate getCurrentWorkoutTemplate()
  {
    return this.currentWorkout;
  }
  
  public Observable<Double> getDebugAccuracySubject()
  {
    return this.debugAccuracySubject;
  }
  
  public Observable<Double> getDebugDistanceSubject()
  {
    return this.debugDistanceSubject;
  }
  
  public Observable<Double> getDebugSpeedSubject()
  {
    return this.debugSpeedSubject;
  }
  
  public Observable<Location> getSpeedSubject()
  {
    return this.speedSubject;
  }
  
  public void hideMusicButton()
  {
    if (this.musicBtn != null) {
      this.musicBtn.setVisibility(8);
    }
    if (this.restIntervalSetting != null) {
      this.restIntervalSetting.setVisibility(8);
    }
  }
  
  public void increaseIntensity()
  {
    sendIntensityEvent(Integer.valueOf(1));
  }
  
  public void lockViewFor5K()
  {
    this.filmStripOpeningBtn.setVisibility(8);
    this.drawerLayout.closeDrawers();
    findViewById(2131296545).setVisibility(8);
    if (this.fragmentManager == null) {
      this.fragmentManager = getFragmentManager();
    }
    Object localObject = (WorkoutStartControllerFragment)this.fragmentManager.findFragmentByTag(WorkoutStartControllerFragment.class.getSimpleName());
    if (localObject != null)
    {
      ((WorkoutStartControllerFragment)localObject).lockViewFor5K();
      ((WorkoutStartControllerFragment)localObject).hideProgress();
    }
    localObject = findViewById(2131297071);
    RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams)((View)localObject).getLayoutParams();
    localLayoutParams.removeRule(20);
    localLayoutParams.removeRule(9);
    localLayoutParams.addRule(12);
    localLayoutParams.addRule(14);
    ((View)localObject).setLayoutParams(localLayoutParams);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 != 1) {
      return;
    }
    switch (paramInt2)
    {
    default: 
      return;
    case 0: 
      Log.i(TAG, "User chose not to make required location settings changes.");
      shutDownGPS();
      return;
    }
    Log.i(TAG, "User agreed to make required location settings changes.");
    startLocationUpdates();
  }
  
  public void onAttachFragment(Fragment paramFragment)
  {
    try
    {
      WorkoutTransportCallbacks localWorkoutTransportCallbacks = (WorkoutTransportCallbacks)paramFragment;
      if (this.workoutFrags != null) {
        this.workoutFrags.add(localWorkoutTransportCallbacks);
      } else {
        Log.e(TAG, "Uh-oh, restore is coming in WAAAAAAAY out of order");
      }
      localWorkoutTransportCallbacks.initWorkout(this.currentWorkout);
    }
    catch (ClassCastException localClassCastException)
    {
      for (;;) {}
    }
    Log.w(TAG, "Nah, I guess.");
    super.onAttachFragment(paramFragment);
  }
  
  public void onBackPressed()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle("Alert!");
    localBuilder.setMessage("Are you sure, you want to end and exit the Workout?");
    getAudioQueue().pause();
    localBuilder.setPositiveButton("Yes", new WorkoutViewActivity..Lambda.7(this));
    localBuilder.setNegativeButton("No", new WorkoutViewActivity..Lambda.8(this));
    localBuilder.create().show();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    this.workoutFrags = new ArrayList();
    getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new WorkoutViewActivity..Lambda.0(this));
    getWindow().addFlags(128);
    setContentView(2131492906);
    this.progressBar = ((ProgressBar)findViewById(2131296802));
    this.drawerLayout = ((DrawerLayout)findViewById(2131296511));
    this.filmStripOpeningBtn = ((ImageView)findViewById(2131296546));
    this.musicBtn = ((ImageView)findViewById(2131296733));
    this.restIntervalSetting = ((ImageView)findViewById(2131296834));
    this.drawerLayout.setDrawerLockMode(1);
    this.filmStripOpeningBtn.setOnClickListener(new WorkoutViewActivity..Lambda.1(this));
    this.musicBtn.setOnClickListener(new WorkoutViewActivity..Lambda.2(this));
    this.restIntervalSetting.setOnClickListener(new WorkoutViewActivity..Lambda.3(this));
    this.fragmentManager = getFragmentManager();
    Object localObject1 = (UltraliteFootApplication)getApplication();
    this.currentWorkout = ((UltraliteFootApplication)localObject1).getCurrentWorkout();
    this.progressBar.setVisibility(8);
    Object localObject2;
    if (this.currentWorkout != null)
    {
      localObject2 = new Intent(this, MissingMediaCalculatorService.class);
      ((Intent)localObject2).setAction("coaching_and_encouragement_calc");
      ((Intent)localObject2).putExtra("workout_exercise_ids", this.currentWorkout.getChosenExerciseList());
      startService((Intent)localObject2);
      localObject2 = new IntentFilter();
      ((IntentFilter)localObject2).addAction("started_calc");
      ((IntentFilter)localObject2).addAction("finished_calc");
      this.coachEncMediaCalculateReciever = new CoachEncMediaCalculateReciever(null);
      registerReceiver(this.coachEncMediaCalculateReciever, (IntentFilter)localObject2);
      if (this.currentWorkout.is_yoga()) {
        this.restIntervalSetting.setVisibility(8);
      }
      if (this.currentWorkout.hasGPSEnabledExercise()) {
        bindLocationService();
      }
      this.currentWorkout.setAudioQueue(((UltraliteFootApplication)localObject1).getAudioQueue());
      if (!this.currentWorkout.isStarted()) {
        this.currentWorkout.setupWithParent(null);
      }
      if (this.fragmentManager == null)
      {
        this.fragmentManager = getFragmentManager();
        Log.i(TAG, "Created another fragment manager instance");
      }
      if (!this.currentWorkout.isStarted()) {
        this.fragmentManager.beginTransaction().add(2131296805, WorkoutProgressFragment.newInstance(), WorkoutProgressFragment.class.getSimpleName()).add(2131296545, FilmStripFragment.instance(), FilmStripFragment.class.getSimpleName()).add(2131296695, VisualWorkoutContainerFragment.newInstance(), VisualWorkoutContainerFragment.class.getSimpleName()).add(2131297071, new WorkoutStartControllerFragment(), WorkoutStartControllerFragment.class.getSimpleName()).commit();
      }
    }
    else
    {
      localObject1 = TAG;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Unable to fetch template for id : ");
      ((StringBuilder)localObject2).append(this.templateId);
      ((StringBuilder)localObject2).append(", So Template is null\n Therefore finishing it by just starting the static downloads.");
      Log.i((String)localObject1, ((StringBuilder)localObject2).toString());
      startStaticDownload(this);
      startActivity(new Intent(this, MainActivity.class));
      finish();
    }
    initSounds();
    super.onCreate(paramBundle);
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    ((UltraliteFootApplication)getApplication()).getAudioQueue().suspend();
    BackgroundWorkerThread.releaseBackgroundWorker();
    shutDownGPS();
    releaseSoundPool();
  }
  
  public void onLogDataDialogButtonsClicked(View paramView)
  {
    List localList = (List)((View)paramView.getParent()).getTag();
    if (paramView.getId() == 2131296509)
    {
      int i = 0;
      while (i < localList.size())
      {
        ((CompletedSet)localList.get(i)).updateManuallyLoggedReps();
        i += 1;
      }
      this.logDataDialog.setVisibility(8);
    }
  }
  
  public void onLogDataRepsButtonClciked(View paramView)
  {
    List localList = (List)((View)paramView.getParent()).getTag();
    int k = paramView.getId();
    int j = Integer.parseInt(this.repsAdjustedNumberTxtView.getText().toString());
    int i;
    if (k == 2131296830)
    {
      i = j - 1;
    }
    else
    {
      i = j;
      if (k == 2131296831) {
        i = j + 1;
      }
    }
    this.repsAdjustedNumberTxtView.setText(String.valueOf(i));
    paramView = findViewById(2131296830);
    boolean bool;
    if (i > 0) {
      bool = true;
    } else {
      bool = false;
    }
    paramView.setEnabled(bool);
    ((CompletedSet)localList.get(0)).logRepsManually(i);
  }
  
  public void onLogDataSetButtonsClicked(View paramView)
  {
    List localList = (List)((View)paramView.getParent()).getTag();
    paramView.getId();
    paramView = (CompletedSet)localList.get(0);
    if (paramView != null)
    {
      CompletedActivity localCompletedActivity = paramView.getCompletedActivity();
      if (localCompletedActivity != null) {
        this.setTitleTextView.setText(localCompletedActivity.exerciseName);
      }
      paramView.logRepsManually(Integer.parseInt(this.repsAdjustedNumberTxtView.getText().toString()));
      long l = ((CompletedSet)localList.get(0)).getReps();
      this.repsAdjustedNumberTxtView.setText(String.valueOf(l));
    }
  }
  
  protected void onPause()
  {
    super.onPause();
    unregisterReceiver(this.unreliableLocUpdateReceiver);
    stopLocationUpdates();
    this.shouldWorkoutAutoResume = true;
    if ((this.currentWorkout != null) && (this.currentWorkout.isPlaying())) {
      sendUIEvent(EventProcessingQueue.UIEventType.PAUSE, this.currentWorkout, null);
    }
  }
  
  public void onPostCreate(Bundle paramBundle, PersistableBundle paramPersistableBundle)
  {
    super.onPostCreate(paramBundle, paramPersistableBundle);
  }
  
  public void onRequestPermissionsResult(int paramInt, @NonNull String[] paramArrayOfString, @NonNull int[] paramArrayOfInt)
  {
    Log.i(TAG, "onRequestPermissionResult");
    if (paramInt == 34)
    {
      if (paramArrayOfInt.length <= 0)
      {
        Log.i(TAG, "User interaction was cancelled.");
        return;
      }
      if (paramArrayOfInt[0] == 0)
      {
        bindLocationService();
        return;
      }
      Snackbar.make(findViewById(2131297059), 2131755335, 0).setAction(2131755376, new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = new Intent();
          paramAnonymousView.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
          paramAnonymousView.setData(Uri.fromParts("package", "com.emdigital.jillianmichaels", null));
          paramAnonymousView.setFlags(268435456);
          WorkoutViewActivity.this.startActivity(paramAnonymousView);
        }
      }).show();
      shutDownGPS();
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    if (Build.VERSION.SDK_INT >= 27) {
      setRequestedOrientation(6);
    }
    IntentFilter localIntentFilter = new IntentFilter("com.emdigital.jillianmichaels_no_reliable_loc_update_available");
    registerReceiver(this.unreliableLocUpdateReceiver, localIntentFilter);
    engineStateChanged(false);
    if (this.currentWorkout == null) {
      returnToMainActivity();
    } else if (this.shouldWorkoutAutoResume) {
      sendUIEvent(EventProcessingQueue.UIEventType.PLAY, this.currentWorkout, null);
    }
    this.shouldWorkoutAutoResume = false;
    if ((this.workoutPlaybackStateSubscription != null) && (!this.workoutPlaybackStateSubscription.isDisposed())) {
      this.workoutPlaybackStateSubscription.dispose();
    }
    this.workoutPlaybackStateSubscription = getAudioQueue().playbackStatePublishSubject.observeOn(AndroidSchedulers.from(getMainLooper())).subscribe(new WorkoutViewActivity..Lambda.4(this), new WorkoutViewActivity..Lambda.5(this), new WorkoutViewActivity..Lambda.6(this));
  }
  
  public void onSelectedActivityMenuItemClick(View paramView)
  {
    WorkoutActivity localWorkoutActivity = (WorkoutActivity)((View)paramView.getParent()).getTag();
    int i = paramView.getId();
    if (i == 2131296901)
    {
      Log.w(TAG, "initiate skip event");
      skipToActivity(localWorkoutActivity);
      this.drawerLayout.closeDrawer(8388611);
      this.selectActivityOptionDialog.setVisibility(8);
      return;
    }
    if (i == 2131296666)
    {
      paramView = localWorkoutActivity.getCompletedActivity();
      initLogDataDialog(paramView, paramView.orderedCompletedSets());
      return;
    }
    if (i == 2131296428) {
      this.selectActivityOptionDialog.setVisibility(8);
    }
  }
  
  protected void onStop()
  {
    super.onStop();
    this.shouldWorkoutAutoResume = false;
    if ((this.unreliableGPSNotifierDialog != null) && (this.unreliableGPSNotifierDialog.isShowing()))
    {
      this.unreliableGPSNotifierDialog.dismiss();
      this.unreliableGPSNotifierDialog = null;
    }
    if (this.gpsNotificationAutoCloseTimer != null)
    {
      this.gpsNotificationAutoCloseTimer.cancel();
      this.gpsNotificationAutoCloseTimer = null;
    }
    if (this.coachEncMediaCalculateReciever != null)
    {
      unregisterReceiver(this.coachEncMediaCalculateReciever);
      this.coachEncMediaCalculateReciever = null;
    }
    if (this.coachEncMediaDownloadReceiver != null)
    {
      this.coachEncMediaDownloadReceiver.unregisterToCallbacks(this);
      this.coachEncMediaDownloadReceiver = null;
    }
  }
  
  public void playSound(LoloSoundType paramLoloSoundType)
  {
    if (this.soundPool != null) {
      this.soundPool.play(this.soundIds[paramLoloSoundType.ordinal()], 1.0F, 1.0F, 0, 0, 2.0F);
    }
  }
  
  public void showSelectActivityOptionDialog(WorkoutActivity paramWorkoutActivity)
  {
    this.selectActivityOptionDialog = ((LinearLayout)findViewById(2131296881));
    this.logDataDialog = ((RelativeLayout)findViewById(2131296665));
    this.setTitleTextView = ((TextView)findViewById(2131296667));
    this.repsAdjustedNumberTxtView = ((TextView)findViewById(2131296829));
    this.setTitleTextView.setText(paramWorkoutActivity.exerciseTitle());
    this.selectActivityOptionDialog.setTag(paramWorkoutActivity);
    this.selectActivityOptionDialog.setVisibility(0);
    this.selectActivityOptionDialog.bringToFront();
    if ((paramWorkoutActivity.getCompletedActivity() != null) && (paramWorkoutActivity.getCompletedActivity().isFinished()) && (!paramWorkoutActivity.isYoga()))
    {
      findViewById(2131296666).setEnabled(true);
      return;
    }
    findViewById(2131296666).setEnabled(false);
  }
  
  public void skipToActivity(WorkoutActivity paramWorkoutActivity)
  {
    sendUIEvent(EventProcessingQueue.UIEventType.SWITCH_ACTIVITY, paramWorkoutActivity, null);
    engineStateChanged(false);
  }
  
  public void startLocationUpdates()
  {
    Log.i(TAG, "Initialising location update listener.");
    if ((this.currentWorkout != null) && (!this.listeningToLocationUpdate))
    {
      Object localObject = this.currentWorkout.currentActivity();
      if (localObject != null)
      {
        localObject = ((WorkoutActivity)localObject).chosenExercise();
        if ((localObject != null) && (this.mService != null) && (((Exercise)localObject).isGPSEnabledExercise())) {
          startListeningToLocationUpdates();
        }
      }
    }
    if (!this.listeningToLocationUpdate) {
      stopLocationUpdates();
    }
  }
  
  public void unlockViewFrom5K()
  {
    this.filmStripOpeningBtn.setVisibility(0);
    findViewById(2131296545).setVisibility(0);
    if (this.fragmentManager == null) {
      this.fragmentManager = getFragmentManager();
    }
    Object localObject = (WorkoutStartControllerFragment)this.fragmentManager.findFragmentByTag(WorkoutStartControllerFragment.class.getSimpleName());
    if (localObject != null)
    {
      ((WorkoutStartControllerFragment)localObject).unlockViewFrom5K();
      ((WorkoutStartControllerFragment)localObject).showProgress();
    }
    localObject = findViewById(2131297071);
    RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams)((View)localObject).getLayoutParams();
    localLayoutParams.removeRule(14);
    localLayoutParams.addRule(12);
    localLayoutParams.addRule(20);
    localLayoutParams.addRule(9);
    ((View)localObject).setLayoutParams(localLayoutParams);
  }
  
  public void workoutPlayButtonClicked()
  {
    if (this.currentWorkout != null)
    {
      Object localObject = this.workoutFrags.iterator();
      while (((Iterator)localObject).hasNext())
      {
        WorkoutTransportCallbacks localWorkoutTransportCallbacks = (WorkoutTransportCallbacks)((Iterator)localObject).next();
        if (!localWorkoutTransportCallbacks.isSubscribed()) {
          localWorkoutTransportCallbacks.workoutPlaying(this.currentWorkout);
        }
      }
      if (!this.currentWorkout.isStarted())
      {
        this.currentWorkout.start();
        return;
      }
      if (this.currentWorkout.isPlaying())
      {
        localObject = EventProcessingQueue.UIEventType.PAUSE;
        engineStateChanged(true);
      }
      else
      {
        if (!this.currentWorkout.isPaused()) {
          break label129;
        }
        localObject = EventProcessingQueue.UIEventType.PLAY;
        engineStateChanged(false);
      }
      sendUIEvent((EventProcessingQueue.UIEventType)localObject, this.currentWorkout, null);
      return;
      label129:
      return;
    }
  }
  
  private class CoachEncMediaCalculateReciever
    extends BroadcastReceiver
  {
    private int jobId = -1;
    
    private CoachEncMediaCalculateReciever() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent != null)
      {
        String str = paramIntent.getAction();
        if (str != null)
        {
          if ((this.jobId < 0) && (str.equalsIgnoreCase("started_calc")))
          {
            this.jobId = paramIntent.getIntExtra("current_calculate_job", -1);
            return;
          }
          if ((str.equalsIgnoreCase("finished_calc")) && (this.jobId >= 0) && (paramIntent.getIntExtra("current_calculate_job", -1) == this.jobId))
          {
            paramIntent = paramIntent.getIntegerArrayListExtra("missing_media_list");
            this.jobId = -1;
            if ((paramIntent != null) && (paramIntent.size() > 0))
            {
              if (WorkoutViewActivity.this.coachEncMediaDownloadReceiver != null)
              {
                WorkoutViewActivity.this.coachEncMediaDownloadReceiver.unregisterToCallbacks(WorkoutViewActivity.this);
                WorkoutViewActivity.access$1302(WorkoutViewActivity.this, null);
              }
              paramContext = LoloDownloadManagerService.downloadMediaAction(paramIntent, WorkoutViewActivity.this);
              WorkoutViewActivity.access$1302(WorkoutViewActivity.this, new WorkoutViewActivity.CoachEncMediaDownloadReceiver(WorkoutViewActivity.this, paramContext, null));
              WorkoutViewActivity.this.coachEncMediaDownloadReceiver.registerForCallBacks(WorkoutViewActivity.this);
              return;
            }
            WorkoutViewActivity.this.startStaticDownload(paramContext);
            return;
          }
          if ((str.equalsIgnoreCase("cancel_calc")) && (paramIntent.getIntExtra("current_calculate_job", -1) == this.jobId))
          {
            this.jobId = -1;
            WorkoutViewActivity.this.unregisterReceiver(this);
          }
        }
      }
    }
  }
  
  private class CoachEncMediaDownloadReceiver
    extends MediaItemDownloadReceiver
  {
    private CoachEncMediaDownloadReceiver(String paramString)
    {
      super();
    }
    
    private void cleanUpRegister()
    {
      if (WorkoutViewActivity.this.coachEncMediaDownloadReceiver != null)
      {
        WorkoutViewActivity.this.coachEncMediaDownloadReceiver.unregisterToCallbacks(WorkoutViewActivity.this);
        WorkoutViewActivity.access$1302(WorkoutViewActivity.this, null);
      }
    }
    
    public void onCancel()
    {
      cleanUpRegister();
    }
    
    public void onComplete(int paramInt)
    {
      cleanUpRegister();
      WorkoutViewActivity.this.startStaticDownload(WorkoutViewActivity.this);
    }
    
    public void onError(String paramString, int paramInt1, int paramInt2)
    {
      cleanUpRegister();
      Toast.makeText(WorkoutViewActivity.this.getApplicationContext(), paramString, 1).show();
      WorkoutViewActivity.this.startStaticDownload(WorkoutViewActivity.this);
    }
    
    public void onProgress(int paramInt1, int paramInt2, int paramInt3) {}
  }
  
  private static enum LoloSoundType
  {
    workout_error,  workout_paused,  workout_resume;
    
    private LoloSoundType() {}
  }
}
