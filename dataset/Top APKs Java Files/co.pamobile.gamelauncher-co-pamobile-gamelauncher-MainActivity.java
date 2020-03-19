package co.pamobile.gamelauncher;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.provider.Settings.System;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import co.pamobile.gamelauncher.adapter.ItemAdapter;
import co.pamobile.gamelauncher.adapter.RecyclerViewMoreAppAdapter;
import co.pamobile.gamelauncher.entities.App;
import co.pamobile.gamelauncher.model.SharedPreference;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.MaterialDialog.Builder;
import com.afollestad.materialdialogs.MaterialDialog.ButtonCallback;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings.Builder;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MainActivity
  extends AppCompatActivity
{
  public static String MORE_APPS = "more_apps";
  public static String PACKAGE_NAME;
  public static String POPULAR_PUBLISHERS = "popular_publishers";
  public static List<PackageInfo> appList;
  public static List<App> listMoreApp = new ArrayList();
  public static InterstitialAd mInterstitialAd;
  public static String moreApps;
  public static String popular_publishers;
  private ItemAdapter adapter;
  private GridView gvApp;
  private ImageView imgBoost;
  private ImageView imgLoading;
  boolean isTablet;
  private ArrayList<PackageInfo> listAppNotInstall = new ArrayList();
  String[] listPubPopular;
  private FirebaseRemoteConfig mFirebaseRemoteConfig;
  private List<PackageInfo> packageInfoList;
  RecyclerView rvMoreApp;
  private SharedPreference sharedPreference = new SharedPreference();
  private TextView titMoreApp;
  private TextView txtStart;
  private TextView txtTask1;
  private TextView txtTask2;
  private TextView txtTask3;
  private TextView txtTask4;
  
  static
  {
    appList = new ArrayList();
  }
  
  public MainActivity() {}
  
  private void addApp()
  {
    final AlertDialog localAlertDialog = new android.support.v7.app.AlertDialog.Builder(this).create();
    Object localObject = getLayoutInflater().inflate(2130968605, null);
    localAlertDialog.setView((View)localObject);
    localObject = (GridView)((View)localObject).findViewById(2131558516);
    ((GridView)localObject).setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = ((PackageInfo)MainActivity.this.listAppNotInstall.get(paramAnonymousInt)).packageName;
        MainActivity.this.sharedPreference.addApp(MainActivity.this.getApplicationContext(), paramAnonymousAdapterView);
        MainActivity.this.getInstalledApps();
        localAlertDialog.dismiss();
      }
    });
    ((GridView)localObject).setAdapter(new ItemAdapter(this, this.listAppNotInstall));
    localAlertDialog.show();
  }
  
  private void checkFirstRun()
  {
    if (!this.sharedPreference.getFirst(this))
    {
      this.sharedPreference.saveFirst(this);
      savePopularAppToPref();
    }
  }
  
  private void deleteAppIcon(final PackageInfo paramPackageInfo)
  {
    new MaterialDialog.Builder(this).title(2131165256).content(2131165254).positiveText(2131165255).negativeText(2131165253).callback(new MaterialDialog.ButtonCallback()
    {
      public void onNegative(MaterialDialog paramAnonymousMaterialDialog)
      {
        super.onNegative(paramAnonymousMaterialDialog);
      }
      
      public void onPositive(MaterialDialog paramAnonymousMaterialDialog)
      {
        MainActivity.appList.remove(paramPackageInfo);
        MainActivity.this.sharedPreference.removeApp(MainActivity.this.getApplicationContext(), paramPackageInfo.packageName);
        MainActivity.this.listAppNotInstall.add(paramPackageInfo);
        MainActivity.this.adapter.notifyDataSetChanged();
        super.onPositive(paramAnonymousMaterialDialog);
      }
    }).show();
  }
  
  private void doBoost()
  {
    this.txtStart.setVisibility(8);
    Animation localAnimation = AnimationUtils.loadAnimation(this, 2131034128);
    this.imgBoost.startAnimation(localAnimation);
    this.imgBoost.setClickable(false);
    localAnimation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        MainActivity.this.imgLoading.setVisibility(0);
        paramAnonymousAnimation = AnimationUtils.loadAnimation(MainActivity.this, 2131034126);
        MainActivity.this.imgLoading.startAnimation(paramAnonymousAnimation);
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            Animation localAnimation = AnimationUtils.loadAnimation(MainActivity.this, 2131034123);
            MainActivity.this.txtTask1.setVisibility(0);
            MainActivity.this.txtTask2.setVisibility(4);
            MainActivity.this.txtTask3.setVisibility(4);
            MainActivity.this.txtTask4.setVisibility(4);
            MainActivity.this.txtTask1.startAnimation(localAnimation);
          }
        }, 1000L);
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            Animation localAnimation = AnimationUtils.loadAnimation(MainActivity.this, 2131034123);
            MainActivity.this.txtTask1.setVisibility(4);
            MainActivity.this.txtTask2.setVisibility(0);
            MainActivity.this.txtTask3.setVisibility(4);
            MainActivity.this.txtTask4.setVisibility(4);
            MainActivity.this.txtTask2.startAnimation(localAnimation);
          }
        }, 2000L);
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            Animation localAnimation = AnimationUtils.loadAnimation(MainActivity.this, 2131034123);
            MainActivity.this.txtTask1.setVisibility(4);
            MainActivity.this.txtTask2.setVisibility(4);
            MainActivity.this.txtTask3.setVisibility(0);
            MainActivity.this.txtTask4.setVisibility(4);
            MainActivity.this.txtTask3.startAnimation(localAnimation);
          }
        }, 3000L);
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            Animation localAnimation = AnimationUtils.loadAnimation(MainActivity.this, 2131034123);
            MainActivity.this.txtTask1.setVisibility(4);
            MainActivity.this.txtTask2.setVisibility(4);
            MainActivity.this.txtTask3.setVisibility(4);
            MainActivity.this.txtTask4.setVisibility(0);
            ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
            ((ActivityManager)MainActivity.this.getSystemService("activity")).getMemoryInfo(localMemoryInfo);
            long l1 = localMemoryInfo.availMem / 1048576L;
            long l2 = localMemoryInfo.totalMem / 1048576L;
            MainActivity.this.txtTask4.setText(MainActivity.this.getString(2131165252) + String.valueOf(l2 - l1) + MainActivity.this.getString(2131165261));
            MainActivity.this.txtTask4.startAnimation(localAnimation);
          }
        }, 4000L);
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            Animation localAnimation = AnimationUtils.loadAnimation(MainActivity.this, 2131034129);
            MainActivity.this.imgLoading.startAnimation(localAnimation);
            MainActivity.this.imgBoost.setClickable(true);
          }
        }, 4500L);
      }
      
      public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
      
      public void onAnimationStart(Animation paramAnonymousAnimation) {}
    });
    killRunningApps();
  }
  
  private void fetchParam()
  {
    this.mFirebaseRemoteConfig.setDefaults(2131099648);
    FirebaseRemoteConfigSettings localFirebaseRemoteConfigSettings = new FirebaseRemoteConfigSettings.Builder().setDeveloperModeEnabled(false).build();
    this.mFirebaseRemoteConfig.setConfigSettings(localFirebaseRemoteConfigSettings);
    long l = 3600L;
    if (this.mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
      l = 0L;
    }
    this.mFirebaseRemoteConfig.fetch(l).addOnCompleteListener(new OnCompleteListener()
    {
      public void onComplete(Task<Void> paramAnonymousTask)
      {
        if (paramAnonymousTask.isSuccessful())
        {
          MainActivity.this.mFirebaseRemoteConfig.activateFetched();
          MainActivity.moreApps = MainActivity.this.mFirebaseRemoteConfig.getString(MainActivity.MORE_APPS);
          MainActivity.popular_publishers = MainActivity.this.mFirebaseRemoteConfig.getString(MainActivity.POPULAR_PUBLISHERS);
        }
        for (;;)
        {
          MainActivity.this.getMoreApp();
          MainActivity.this.getAllPublisher();
          MainActivity.this.getInstalledApps();
          return;
          Toast.makeText(MainActivity.this, "Fetch Failed", 0).show();
        }
      }
    });
  }
  
  private void getInstalledApps()
  {
    appList.clear();
    this.listAppNotInstall.clear();
    this.packageInfoList = getPackageManager().getInstalledPackages(0);
    Iterator localIterator = this.packageInfoList.iterator();
    label114:
    label224:
    label236:
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((!isSystemPackage(localPackageInfo)) && (!localPackageInfo.packageName.equals(PACKAGE_NAME)))
      {
        this.listAppNotInstall.add(localPackageInfo);
        int k = 0;
        ArrayList localArrayList = this.sharedPreference.getApp(this);
        int j = k;
        int i;
        if (localArrayList != null)
        {
          i = 0;
          j = k;
          if (i < localArrayList.size())
          {
            if (!localPackageInfo.packageName.equals(localArrayList.get(i))) {
              break label224;
            }
            appList.add(localPackageInfo);
            j = 1;
            this.listAppNotInstall.remove(localPackageInfo);
          }
        }
        if (j == 0)
        {
          i = 0;
          for (;;)
          {
            if (i >= this.listPubPopular.length) {
              break label236;
            }
            if (localPackageInfo.packageName.contains(this.listPubPopular[i].toString()))
            {
              appList.add(localPackageInfo);
              this.listAppNotInstall.remove(localPackageInfo);
              break;
              i += 1;
              break label114;
            }
            i += 1;
          }
        }
      }
    }
    appList.add(new PackageInfo());
    this.adapter = new ItemAdapter(this, appList);
    if (this.isTablet) {
      this.gvApp.setNumColumns(6);
    }
    for (;;)
    {
      this.gvApp.setAdapter(this.adapter);
      this.gvApp.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
      {
        public boolean onItemLongClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          paramAnonymousAdapterView = (PackageInfo)MainActivity.appList.get(paramAnonymousInt);
          MainActivity.this.deleteAppIcon(paramAnonymousAdapterView);
          return true;
        }
      });
      this.gvApp.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          MainActivity.this.onClickGridview(paramAnonymousInt);
        }
      });
      return;
      this.gvApp.setNumColumns(4);
    }
  }
  
  private boolean isSystemPackage(PackageInfo paramPackageInfo)
  {
    return (paramPackageInfo.applicationInfo.flags & 0x1) != 0;
  }
  
  private void killRunningApps()
  {
    PackageManager localPackageManager = getPackageManager();
    Iterator localIterator = ((ActivityManager)getSystemService("activity")).getRunningAppProcesses().iterator();
    while (localIterator.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
      if (localRunningAppProcessInfo.pkgList.length != 0) {
        try
        {
          PackageInfo localPackageInfo = localPackageManager.getPackageInfo(localRunningAppProcessInfo.pkgList[0], 1);
          if (!localRunningAppProcessInfo.processName.equals(PACKAGE_NAME)) {
            Process.killProcess(localRunningAppProcessInfo.pid);
          }
          ((ActivityManager)getSystemService("activity")).killBackgroundProcesses(localPackageInfo.packageName);
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }
  }
  
  private void loadAds()
  {
    ((AdView)findViewById(2131558513)).loadAd(new AdRequest.Builder().addTestDevice("0A1F7EAC9FE7943C3D7F5C67BAD73A3F").build());
  }
  
  private void loadControls()
  {
    this.gvApp = ((GridView)findViewById(2131558512));
    this.imgBoost = ((ImageView)findViewById(2131558507));
    this.imgLoading = ((ImageView)findViewById(2131558501));
    this.txtStart = ((TextView)findViewById(2131558502));
    this.txtTask1 = ((TextView)findViewById(2131558503));
    this.txtTask2 = ((TextView)findViewById(2131558504));
    this.txtTask3 = ((TextView)findViewById(2131558505));
    this.txtTask4 = ((TextView)findViewById(2131558506));
    this.titMoreApp = ((TextView)findViewById(2131558509));
    this.rvMoreApp = ((RecyclerView)findViewById(2131558510));
  }
  
  private void onClickGridview(int paramInt)
  {
    try
    {
      PackageInfo localPackageInfo = (PackageInfo)this.adapter.getItem(paramInt);
      if (localPackageInfo.applicationInfo != null)
      {
        startApp(localPackageInfo);
        return;
      }
      addApp();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void reduceBrightness()
  {
    Settings.System.putInt(getContentResolver(), "screen_brightness", 50);
    WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
    localLayoutParams.screenBrightness = 0.5F;
    getWindow().setAttributes(localLayoutParams);
  }
  
  private void requestNewInterstitial()
  {
    AdRequest localAdRequest = new AdRequest.Builder().addTestDevice("0A1F7EAC9FE7943C3D7F5C67BAD73A3F").build();
    mInterstitialAd.loadAd(localAdRequest);
  }
  
  private void savePopularAppToPref()
  {
    String[] arrayOfString = getResources().getStringArray(2131427328);
    int i = 0;
    while (i < arrayOfString.length)
    {
      this.sharedPreference.addApp(this, arrayOfString[i]);
      i += 1;
    }
  }
  
  private void startApp(final PackageInfo paramPackageInfo)
  {
    Intent localIntent = new Intent(this, LoadingActivity.class);
    localIntent.putExtra("img", ((BitmapDrawable)paramPackageInfo.applicationInfo.loadIcon(getPackageManager())).getBitmap());
    startActivity(localIntent);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        Intent localIntent = MainActivity.this.getPackageManager().getLaunchIntentForPackage(paramPackageInfo.packageName);
        MainActivity.this.startActivityForResult(localIntent, 1);
      }
    }, 1500L);
  }
  
  public void confirmExit()
  {
    new android.app.AlertDialog.Builder(this).setTitle(getString(2131165260)).setMessage(getString(2131165257)).setCancelable(true).setNegativeButton(getString(2131165258), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + MainActivity.PACKAGE_NAME)));
      }
    }).setNeutralButton(getString(2131165259), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MainActivity.this.finish();
      }
    }).show();
  }
  
  public void getAllPublisher()
  {
    this.listPubPopular = ((String[])new Gson().fromJson(popular_publishers, [Ljava.lang.String.class));
  }
  
  protected void getMemAfter()
  {
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    ((ActivityManager)getSystemService("activity")).getMemoryInfo(localMemoryInfo);
    long l = localMemoryInfo.availMem / 1048576L;
    l = localMemoryInfo.totalMem / 1048576L;
  }
  
  protected void getMemBefore()
  {
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    ((ActivityManager)getSystemService("activity")).getMemoryInfo(localMemoryInfo);
    long l = localMemoryInfo.availMem / 1048576L;
    l = localMemoryInfo.totalMem / 1048576L;
  }
  
  public void getMoreApp()
  {
    listMoreApp = new ArrayList();
    Object localObject1 = (App[])new Gson().fromJson(moreApps, [Lco.pamobile.gamelauncher.entities.App.class);
    label82:
    Object localObject2;
    if (localObject1 != null)
    {
      listMoreApp = Arrays.asList((Object[])localObject1);
      listMoreApp = new ArrayList(listMoreApp);
      localObject1 = new LinearLayoutManager(this, 0, false);
      if (!this.isTablet) {
        break label179;
      }
      this.titMoreApp.setTextSize(14.0F);
      localObject2 = new ArrayList();
      if (listMoreApp.size() > 7) {
        break label192;
      }
      ((List)localObject2).addAll(listMoreApp);
    }
    for (;;)
    {
      App localApp = new App();
      localApp.setName("more_app");
      ((List)localObject2).add(localApp);
      localObject2 = new RecyclerViewMoreAppAdapter(this, (List)localObject2);
      this.rvMoreApp.setLayoutManager((RecyclerView.LayoutManager)localObject1);
      this.rvMoreApp.setAdapter((RecyclerView.Adapter)localObject2);
      return;
      listMoreApp = new ArrayList();
      break;
      label179:
      this.titMoreApp.setTextSize(10.0F);
      break label82;
      label192:
      int i = 0;
      while (i < 7)
      {
        ((List)localObject2).add(listMoreApp.get(i));
        i += 1;
      }
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == 1) && (mInterstitialAd.isLoaded())) {
      mInterstitialAd.show();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968603);
    this.isTablet = getResources().getBoolean(2131361795);
    paramBundle = new DisplayImageOptions.Builder().cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).imageScaleType(ImageScaleType.IN_SAMPLE_INT).build();
    paramBundle = new ImageLoaderConfiguration.Builder(getApplicationContext()).defaultDisplayImageOptions(paramBundle).threadPoolSize(5).build();
    ImageLoader.getInstance().init(paramBundle);
    this.mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
    PACKAGE_NAME = getApplicationContext().getPackageName();
    fetchParam();
    loadControls();
    checkFirstRun();
    loadAds();
    mInterstitialAd = new InterstitialAd(this);
    mInterstitialAd.setAdUnitId(getResources().getString(2131165240));
    requestNewInterstitial();
    mInterstitialAd.setAdListener(new AdListener()
    {
      public void onAdClosed()
      {
        MainActivity.this.requestNewInterstitial();
      }
    });
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        MainActivity.this.doBoost();
      }
    }, 1000L);
    this.imgBoost.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MainActivity.this.doBoost();
      }
    });
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt != 4) || ((getSupportFragmentManager().findFragmentById(2131558499) instanceof MoreAppFragment))) {
      return super.onKeyDown(paramInt, paramKeyEvent);
    }
    try
    {
      confirmExit();
      return true;
    }
    catch (Exception paramKeyEvent)
    {
      for (;;)
      {
        paramKeyEvent.printStackTrace();
      }
    }
  }
  
  protected void turnoffServices() {}
}
