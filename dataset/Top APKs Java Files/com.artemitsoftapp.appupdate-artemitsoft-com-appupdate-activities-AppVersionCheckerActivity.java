package artemitsoft.com.appupdate.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import artemitsoft.com.appupdate.AppController;
import artemitsoft.com.appupdate.models.AppListModel;
import artemitsoft.com.appupdate.models.Enums.Error;
import artemitsoft.com.appupdate.models.Enums.GoActivity;
import artemitsoft.com.appupdate.services.Controller;
import artemitsoft.com.appupdate.services.VersionChecker;
import com.skyfishjy.library.RippleBackground;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AppVersionCheckerActivity
  extends BaseActivity
{
  public String TAG = AppVersionCheckerActivity.class.getSimpleName();
  public AppController appController;
  public int appCount = 0;
  public Drawable appIcon;
  public Animation blink;
  public Animation botUp;
  public Animation bounce;
  public Controller controller;
  public Animation fadeIn;
  public Animation fadeOut;
  public boolean flag = true;
  @SuppressLint({"HandlerLeak"})
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      Object localObject = AppVersionCheckerActivity.this;
      ((AppVersionCheckerActivity)localObject).appCount += 1;
      localObject = paramAnonymousMessage.getData();
      paramAnonymousMessage = ((Bundle)localObject).getString("onlineVersion");
      localObject = ((Bundle)localObject).getString("packageName");
      localObject = AppVersionCheckerActivity.this.controller.getAppDetailByPackageName((String)localObject);
      AppVersionCheckerActivity.this.tvAppName.setText(((AppListModel)localObject).getAppName());
      AppVersionCheckerActivity.this.ivAppIcon.setImageDrawable(((AppListModel)localObject).getAppIcon());
      AppVersionCheckerActivity.this.ivAppIcon.startAnimation(AppVersionCheckerActivity.this.zoomIn);
      AppVersionCheckerActivity.this.tvAppName.startAnimation(AppVersionCheckerActivity.this.fadeIn);
      AppVersionCheckerActivity.this.horizontalProgressBar.setProgress(AppVersionCheckerActivity.this.appCount);
      if ((paramAnonymousMessage != null) && (!paramAnonymousMessage.isEmpty()))
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramAnonymousMessage);
        localStringBuilder.append(" currentVersion : ");
        localStringBuilder.append(((AppListModel)localObject).getAppVersion());
        Log.d("onlineVersion : ", localStringBuilder.toString());
        if (paramAnonymousMessage.equals(Enums.Error.NotFoundApp.toString()))
        {
          Log.d("Not Found : ", paramAnonymousMessage);
        }
        else if (!((AppListModel)localObject).getAppVersion().equals(paramAnonymousMessage))
        {
          if (!paramAnonymousMessage.equals(AppVersionCheckerActivity.this.getString(2131558532)))
          {
            AppVersionCheckerActivity.this.newVersionList.add(localObject);
            AppVersionCheckerActivity.this.newAppVersionPackList.add(((AppListModel)localObject).getAppPackageName());
          }
          AppVersionCheckerActivity.this.tvUpdatedCount.setText(AppVersionCheckerActivity.this.getString(2131558531, new Object[] { String.valueOf(AppVersionCheckerActivity.this.newVersionList.size()) }));
          AppVersionCheckerActivity.this.tvUpdatedCount.startAnimation(AppVersionCheckerActivity.this.blink);
        }
      }
      paramAnonymousMessage = AppVersionCheckerActivity.this.tvPercentage;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(AppVersionCheckerActivity.this.appCount);
      localStringBuilder.append("/");
      localStringBuilder.append(AppVersionCheckerActivity.this.lstAppList.size());
      paramAnonymousMessage.setText(String.valueOf(localStringBuilder.toString()));
      Log.d("onlineVersion", String.valueOf(AppVersionCheckerActivity.this.appCount));
      Log.d("onlineVersion", String.valueOf(AppVersionCheckerActivity.this.appCount));
      Log.d("onlineVersion", String.valueOf(((AppListModel)localObject).getAppName()));
      if (AppVersionCheckerActivity.this.appCount == AppVersionCheckerActivity.this.lstAppList.size())
      {
        AppVersionCheckerActivity.this.StopScan();
        AppVersionCheckerActivity.this.ClearBadge();
        AppVersionCheckerActivity.this.SetBadge(AppVersionCheckerActivity.this.lstAppList.size());
      }
    }
  };
  public ProgressBar horizontalProgressBar;
  public ImageView iv1;
  public ImageView iv2;
  public ImageView ivAppIcon;
  public LinearLayout llBody;
  public RelativeLayout llLoading;
  public List<AppListModel> lstAppList = new ArrayList();
  public List<String> newAppVersionPackList = new ArrayList();
  public List<AppListModel> newVersionList = new ArrayList();
  public PackageManager packageManager;
  public RippleBackground rippleBackground;
  private Runnable runnable = new Runnable()
  {
    public void run()
    {
      AppVersionCheckerActivity.this.updateUI();
    }
  };
  private ApiTaskFOrSystemApp taskFOrSystemApp;
  public int timeScan = 500;
  public Animation topDown;
  public TextView tvAppName;
  public TextView tvPercentage;
  public TextView tvUpdatedCount;
  public Animation zoomIn;
  
  public AppVersionCheckerActivity() {}
  
  private List<AppListModel> getAllApp()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = new ArrayList();
    new Intent("android.intent.action.MAIN", null).addCategory("android.intent.category.LAUNCHER");
    Object localObject2 = this.packageManager.getInstalledApplications(128);
    int i = 0;
    while (i < ((List)localObject2).size())
    {
      if (this.packageManager.getLaunchIntentForPackage(((ApplicationInfo)((List)localObject2).get(i)).packageName) != null) {
        ((List)localObject1).add((ApplicationInfo)((List)localObject2).get(i));
      }
      i += 1;
    }
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject3 = (ApplicationInfo)((Iterator)localObject1).next();
      try
      {
        localObject2 = this.packageManager.getPackageInfo(((ApplicationInfo)localObject3).packageName, 0);
        String str1 = ((ApplicationInfo)localObject3).loadLabel(this.packageManager).toString();
        this.appIcon = ((ApplicationInfo)localObject3).loadIcon(this.packageManager);
        String str2 = ((ApplicationInfo)localObject3).packageName;
        localObject3 = ((ApplicationInfo)localObject3).publicSourceDir;
        String str3 = ((PackageInfo)localObject2).versionName;
        long l1 = ((PackageInfo)localObject2).firstInstallTime;
        long l2 = ((PackageInfo)localObject2).lastUpdateTime;
        localArrayList.add(new AppListModel(str1, this.appIcon, str2, (String)localObject3, str3, l1, l2));
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
      }
    }
    Collections.sort(localArrayList, AppListModel.sortByAppName);
    return localArrayList;
  }
  
  public void StartAnimation()
  {
    this.rippleBackground.startRippleAnimation();
    this.iv1.startAnimation(this.botUp);
    this.topDown.setAnimationListener(new v1());
    this.botUp.setAnimationListener(new v2());
    this.iv1.setVisibility(0);
    this.iv2.setVisibility(0);
    StartScan();
  }
  
  public void StartScan()
  {
    new Thread(this.runnable).start();
  }
  
  public void StopScan()
  {
    this.rippleBackground.stopRippleAnimation();
    try
    {
      Thread.sleep(5000L);
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException.printStackTrace();
    }
    this.appController.setSelectedActivity(Enums.GoActivity.UpdateAppList.getValue());
    Intent localIntent = new Intent(this, AppUpdateListActivity.class);
    this.appController.setNewVersionAppList(this.newAppVersionPackList);
    startActivity(localIntent);
    finish();
  }
  
  public void StopScanTask()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(getString(2131558433));
    localBuilder.setMessage(getString(2131558432));
    localBuilder.setCancelable(false);
    localBuilder.setPositiveButton(getString(2131558444), new -..Lambda.AppVersionCheckerActivity.DOxO_Ws8F8R2AlO_rOjE9SG8Kas(this)).setNegativeButton(getString(2131558439), -..Lambda.AppVersionCheckerActivity.QrUbbvDCCuJgf-QJoOJloZZeb00.INSTANCE).show();
  }
  
  @SuppressLint({"ClickableViewAccessibility"})
  public void init()
  {
    initAnimation();
    this.tvUpdatedCount.setText(getString(2131558531, new Object[] { String.valueOf(0) }));
    this.taskFOrSystemApp.execute(new Void[0]);
  }
  
  public void initAnimation()
  {
    this.zoomIn = AnimationUtils.loadAnimation(getApplicationContext(), 2130772002);
    this.blink = AnimationUtils.loadAnimation(getApplicationContext(), 2130771980);
    this.bounce = AnimationUtils.loadAnimation(getApplicationContext(), 2130771982);
    this.botUp = AnimationUtils.loadAnimation(this, 2130771981);
    this.topDown = AnimationUtils.loadAnimation(this, 2130772001);
    this.fadeIn = AnimationUtils.loadAnimation(this, 2130771988);
    this.fadeOut = AnimationUtils.loadAnimation(this, 2130771989);
    this.rippleBackground = ((RippleBackground)findViewById(2131230776));
    this.iv1 = ((ImageView)findViewById(2131230837));
    this.iv2 = ((ImageView)findViewById(2131230838));
    this.iv1.setVisibility(8);
    this.iv2.setVisibility(8);
  }
  
  protected void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131361827);
    this.controller = new Controller(this);
    this.appController = AppController.getInstance();
    this.packageManager = getPackageManager();
    this.ivAppIcon = ((ImageView)findViewById(2131230840));
    this.tvAppName = ((TextView)findViewById(2131230990));
    this.tvUpdatedCount = ((TextView)findViewById(2131231010));
    this.llBody = ((LinearLayout)findViewById(2131230863));
    this.llLoading = ((RelativeLayout)findViewById(2131230867));
    this.tvPercentage = ((TextView)findViewById(2131231000));
    this.horizontalProgressBar = ((ProgressBar)findViewById(2131230827));
    this.llBody.setVisibility(8);
    this.llLoading.setVisibility(0);
    this.taskFOrSystemApp = new ApiTaskFOrSystemApp();
    init();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt != 4) {
      return false;
    }
    StopScanTask();
    return true;
  }
  
  public void onResume()
  {
    ShowAds(this, (LinearLayout)findViewById(2131230748));
    super.onResume();
  }
  
  public void updateUI()
  {
    Iterator localIterator = this.lstAppList.iterator();
    while (localIterator.hasNext()) {
      try
      {
        versionCall((AppListModel)localIterator.next());
        Thread.sleep(this.timeScan);
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException.printStackTrace();
      }
    }
  }
  
  public void versionCall(AppListModel paramAppListModel)
  {
    try
    {
      String str = (String)new VersionChecker().execute(new String[] { paramAppListModel.getAppPackageName() }).get();
      Message localMessage = new Message();
      Bundle localBundle = new Bundle();
      localBundle.putString("onlineVersion", str);
      localBundle.putString("packageName", paramAppListModel.getAppPackageName());
      localMessage.setData(localBundle);
      this.handler.sendMessage(localMessage);
      return;
    }
    catch (ExecutionException paramAppListModel)
    {
      paramAppListModel.printStackTrace();
      return;
    }
    catch (InterruptedException paramAppListModel)
    {
      paramAppListModel.printStackTrace();
    }
  }
  
  @SuppressLint({"StaticFieldLeak"})
  public class ApiTaskFOrSystemApp
    extends AsyncTask<Void, Void, Void>
  {
    public ApiTaskFOrSystemApp() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      AppVersionCheckerActivity.this.lstAppList = AppVersionCheckerActivity.this.getAllApp();
      paramVarArgs = AppVersionCheckerActivity.this.tvPercentage;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("1/");
      localStringBuilder.append(AppVersionCheckerActivity.this.lstAppList.size());
      paramVarArgs.setText(String.valueOf(localStringBuilder.toString()));
      AppVersionCheckerActivity.this.horizontalProgressBar.setMax(AppVersionCheckerActivity.this.lstAppList.size());
      return null;
    }
    
    protected void onCancelled()
    {
      super.onCancelled();
      AppVersionCheckerActivity.this.taskFOrSystemApp.cancel(true);
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      AppVersionCheckerActivity.this.llBody.setVisibility(0);
      AppVersionCheckerActivity.this.llLoading.setVisibility(8);
      AppVersionCheckerActivity.this.StartAnimation();
    }
    
    protected void onPreExecute()
    {
      AppVersionCheckerActivity.this.llBody.setVisibility(8);
      AppVersionCheckerActivity.this.llLoading.setVisibility(0);
      super.onPreExecute();
    }
  }
  
  class v1
    implements Animation.AnimationListener
  {
    v1() {}
    
    public void onAnimationEnd(Animation paramAnimation)
    {
      if (AppVersionCheckerActivity.this.flag) {
        AppVersionCheckerActivity.this.iv1.startAnimation(AppVersionCheckerActivity.this.botUp);
      }
    }
    
    public void onAnimationRepeat(Animation paramAnimation) {}
    
    public void onAnimationStart(Animation paramAnimation)
    {
      AppVersionCheckerActivity.this.iv2.setVisibility(0);
      AppVersionCheckerActivity.this.iv1.setVisibility(8);
    }
  }
  
  class v2
    implements Animation.AnimationListener
  {
    v2() {}
    
    public void onAnimationEnd(Animation paramAnimation)
    {
      if (AppVersionCheckerActivity.this.flag) {
        AppVersionCheckerActivity.this.iv2.startAnimation(AppVersionCheckerActivity.this.topDown);
      }
    }
    
    public void onAnimationRepeat(Animation paramAnimation) {}
    
    public void onAnimationStart(Animation paramAnimation)
    {
      AppVersionCheckerActivity.this.iv1.setVisibility(0);
      AppVersionCheckerActivity.this.iv2.setVisibility(8);
    }
  }
}
