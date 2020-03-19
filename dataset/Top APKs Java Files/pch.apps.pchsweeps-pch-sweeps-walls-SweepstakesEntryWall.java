package pch.sweeps.walls;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.droidparts.net.http.HTTPException;
import org.droidparts.net.http.HTTPResponse;
import org.droidparts.net.http.RESTClient2;
import org.json.JSONException;
import org.json.JSONObject;
import pch.apps.pchsweeps.hub.Hub;
import pch.apps.util.AppConfig;
import pch.apps.util.AppMonitorMessenger;
import pch.apps.util.Log;
import pch.apps.util.SweepsProgressBar;
import pch.apps.util.analytics.Analytics;
import pch.apps.util.analytics.AnalyticsFactory;
import pch.apps.util.preferences.PCHPreferences;
import pch.apps.util.preferences.PCHPreferencesFactory;

@SuppressLint({"NewApi"})
@TargetApi(11)
public class SweepstakesEntryWall
  extends Activity
{
  protected static SweepstakesEntryWall Instance;
  protected static ArrayList<AppModel> Items;
  private static SweepstakesWallAdapter adapter;
  protected static AppModel currentItem = null;
  private static ListView listView;
  private static Context mContext;
  final int PICK1 = 2;
  final int PICK2 = 3;
  private String SweepsWallAPIUrl;
  private Analytics analytics;
  private boolean appBonus = false;
  private String appURL;
  private String apppkg;
  private Button backButton;
  private ImageView balloonGroupLeft;
  private ImageView balloonGroupRight;
  private Dialog complete;
  private final Context ctx = this;
  private Dialog d;
  private Dialog dialog;
  private TextView facts;
  private Button history;
  private ImageView icon;
  private ImageButton informationButton;
  private int listItemAtPosition;
  private boolean mAdvertisingEnabled;
  private String mAdvertisingId;
  private PCHPreferences mPrefs;
  private ProgressDialog progressDialog;
  private ArrayList<AppModel> result;
  private TextView rules;
  private ImageView starburst;
  private Dialog welcome;
  
  public SweepstakesEntryWall() {}
  
  public static void addContests(AppModel paramAppModel)
  {
    Items.add(paramAppModel);
    adapter.notifyDataSetChanged();
    ListInflator.getListViewSize(listView);
  }
  
  private void analyticsCleanup()
  {
    String str = this.mPrefs.get("adId", "organic");
    this.analytics.tag("DOTW: " + Calendar.getInstance().get(7));
    int i = Calendar.getInstance().get(11);
    if (i <= 6) {
      this.analytics.tag("User Access Timenight");
    }
    for (;;)
    {
      this.analytics.tag("Source adId: " + str);
      return;
      if (i < 12) {
        this.analytics.tag("User Access Timemorning");
      } else if (i < 18) {
        this.analytics.tag("User Access Timeafternoon");
      } else {
        this.analytics.tag("User Access Timeevening");
      }
    }
  }
  
  private void animateContestBalloons()
  {
    if (Build.VERSION.SDK_INT >= 14)
    {
      float f1 = -(10.0F + (float)Math.random() * 10.0F);
      float f2 = (float)Math.random();
      float f3 = -(10.0F + (float)Math.random() * 10.0F);
      float f4 = -(10.0F + (float)Math.random() * 10.0F);
      AnimationSet localAnimationSet1 = new AnimationSet(true);
      AnimationSet localAnimationSet2 = new AnimationSet(true);
      localAnimationSet1.setInterpolator(new LinearInterpolator());
      localAnimationSet2.setInterpolator(new LinearInterpolator());
      RotateAnimation localRotateAnimation1 = new RotateAnimation(0.0F, 5.0F, 1, 0.5F, 1, 0.5F);
      localRotateAnimation1.setStartOffset(500L);
      localRotateAnimation1.setDuration(1500L);
      localRotateAnimation1.setFillEnabled(true);
      localRotateAnimation1.setFillAfter(true);
      localRotateAnimation1.setInterpolator(new LinearInterpolator());
      localRotateAnimation1.setRepeatCount(-1);
      localRotateAnimation1.setRepeatMode(2);
      RotateAnimation localRotateAnimation2 = new RotateAnimation(0.0F, 5.0F, 1, 0.5F, 1, 0.5F);
      localRotateAnimation2.setStartOffset(500L);
      localRotateAnimation2.setDuration(1000L);
      localRotateAnimation2.setFillEnabled(true);
      localRotateAnimation2.setFillAfter(true);
      localRotateAnimation2.setInterpolator(new LinearInterpolator());
      localRotateAnimation2.setRepeatCount(-1);
      localRotateAnimation2.setRepeatMode(2);
      TranslateAnimation localTranslateAnimation1 = new TranslateAnimation(0.0F, f1, 0.0F, f3);
      localTranslateAnimation1.setDuration(2000L);
      localTranslateAnimation1.setRepeatCount(-1);
      localTranslateAnimation1.setRepeatMode(2);
      localTranslateAnimation1.setFillEnabled(true);
      localTranslateAnimation1.setFillAfter(true);
      TranslateAnimation localTranslateAnimation2 = new TranslateAnimation(0.0F, 10.0F + f2 * 10.0F, 0.0F, f4);
      localTranslateAnimation2.setDuration(2000L);
      localTranslateAnimation2.setRepeatCount(-1);
      localTranslateAnimation2.setRepeatMode(2);
      localTranslateAnimation2.setFillEnabled(true);
      localTranslateAnimation2.setFillAfter(true);
      localAnimationSet1.addAnimation(localTranslateAnimation1);
      localAnimationSet1.addAnimation(localRotateAnimation2);
      localAnimationSet2.addAnimation(localTranslateAnimation2);
      localAnimationSet2.addAnimation(localRotateAnimation1);
      this.balloonGroupLeft.startAnimation(localAnimationSet1);
      this.balloonGroupRight.startAnimation(localAnimationSet2);
    }
  }
  
  @SuppressLint({"NewApi"})
  private void animateContestEnter(String paramString)
  {
    if (Build.VERSION.SDK_INT >= 14)
    {
      if (!paramString.equals("all")) {
        break label91;
      }
      this.balloonGroupLeft.animate().translationY(-10000.0F).translationX(-10000.0F).setDuration(200000L).setInterpolator(new LinearInterpolator());
      this.balloonGroupRight.animate().translationY(-10000.0F).translationX(10000.0F).setDuration(200000L).setInterpolator(new LinearInterpolator());
    }
    label91:
    do
    {
      return;
      if (paramString.equals("left"))
      {
        this.balloonGroupLeft.animate().translationY(-10000.0F).translationX(-10000.0F).setDuration(200000L).setInterpolator(new LinearInterpolator());
        return;
      }
    } while (!paramString.equals("right"));
    this.balloonGroupRight.animate().translationY(-10000.0F).translationX(10000.0F).setDuration(200000L).setInterpolator(new LinearInterpolator());
  }
  
  private void appDownloadCompleteDialog()
  {
    if (this.mPrefs.get("show_app_complete_dialog", true))
    {
      this.complete = new Dialog(this, 16973840);
      this.complete.setContentView(2130903075);
      this.complete.getWindow().addFlags(2);
      this.complete.setCancelable(true);
      ((ImageButton)this.complete.findViewById(2131034273)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if ((SweepstakesEntryWall.this.complete != null) && (SweepstakesEntryWall.this.complete.isShowing())) {
            SweepstakesEntryWall.this.complete.dismiss();
          }
        }
      });
      this.mPrefs.store("show_app_complete_dialog", false);
      this.complete.show();
    }
  }
  
  private void appDownloadWeclomeDialog()
  {
    this.welcome = new Dialog(Instance, 16973840);
    this.welcome.setContentView(2130903078);
    this.welcome.getWindow().addFlags(2);
    this.welcome.setCancelable(true);
    ((ImageButton)this.welcome.findViewById(2131034273)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SweepstakesEntryWall.this.analytics.tag("Offer Click: ");
        if ((SweepstakesEntryWall.Instance != null) && (SweepstakesEntryWall.this.welcome != null) && (SweepstakesEntryWall.this.welcome.isShowing())) {
          SweepstakesEntryWall.this.welcome.dismiss();
        }
      }
    });
    this.welcome.show();
  }
  
  private void appDownloaderDialog(final String paramString1, String paramString2, final String paramString3)
  {
    this.d = new Dialog(this, 16973840);
    this.d.setContentView(2130903074);
    this.d.getWindow().addFlags(2);
    this.d.setCancelable(false);
    ((TextView)this.d.findViewById(2131034269)).setText(paramString1);
    ImageView localImageView = (ImageView)this.d.findViewById(2131034197);
    Picasso.with(this).load(paramString2).placeholder(2130837756).error(2130837756).into(localImageView);
    ((ImageButton)this.d.findViewById(2131034271)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SweepstakesEntryWall.this.analytics.tag("APP Dialog Retry ");
        SweepstakesEntryWall.this.startInstallProcess(paramString3, paramString1, SweepstakesEntryWall.this.appURL);
        SweepstakesEntryWall.this.incentTheApp(SweepstakesEntryWall.this.appURL);
        SweepstakesEntryWall.this.makeStatusToast("Bonus Entry Status", "First step is to CLICK INSTALL!");
      }
    });
    ((ImageButton)this.d.findViewById(2131034270)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SweepstakesEntryWall.this.analytics.tag("APP Dialog Cancel");
        SweepstakesEntryWall.this.End();
        if ((SweepstakesEntryWall.this.d != null) && (SweepstakesEntryWall.this.d.isShowing())) {
          SweepstakesEntryWall.this.d.dismiss();
        }
        new SweepstakesEntryWall.AsyncListViewLoader(SweepstakesEntryWall.this, null).execute(new String[] { SweepstakesEntryWall.this.SweepsWallAPIUrl });
      }
    });
    this.d.show();
  }
  
  public static void endNotifications()
  {
    Object localObject = new Intent(mContext, AppMonitorMessenger.class);
    localObject = PendingIntent.getBroadcast(mContext, 11643, (Intent)localObject, 268435456);
    ((AlarmManager)mContext.getSystemService("alarm")).cancel((PendingIntent)localObject);
  }
  
  public static SweepstakesEntryWall getInstance()
  {
    if (Instance == null) {
      throw new IllegalStateException("Application not created yet!");
    }
    return Instance;
  }
  
  public static PendingIntent getSyncPendingIntent(Context paramContext)
  {
    return PendingIntent.getBroadcast(paramContext, 0, new Intent(paramContext, SweepstakesEntryWall.class), 0);
  }
  
  public static boolean isContestsItemActivityCreated()
  {
    return Instance != null;
  }
  
  private void rotateStarburst()
  {
    if (Build.VERSION.SDK_INT >= 14)
    {
      RotateAnimation localRotateAnimation = new RotateAnimation(0.0F, 360.0F, 1, 0.5F, 1, 0.5F);
      localRotateAnimation.setRepeatCount(-1);
      localRotateAnimation.setRepeatMode(2);
      localRotateAnimation.setDuration(40000L);
      localRotateAnimation.setInterpolator(new LinearInterpolator());
      localRotateAnimation.setFillEnabled(true);
      localRotateAnimation.setFillAfter(true);
      this.starburst.startAnimation(localRotateAnimation);
    }
  }
  
  private static void setInstance(SweepstakesEntryWall paramSweepstakesEntryWall)
  {
    Instance = paramSweepstakesEntryWall;
  }
  
  public static void setListViewHeightBasedOnChildren(ListView paramListView)
  {
    ListAdapter localListAdapter = paramListView.getAdapter();
    if (localListAdapter == null) {
      return;
    }
    int j = 0;
    int i = 0;
    for (;;)
    {
      if (i >= localListAdapter.getCount())
      {
        localObject = paramListView.getLayoutParams();
        ((ViewGroup.LayoutParams)localObject).height = (paramListView.getDividerHeight() * (localListAdapter.getCount() - 1) + j);
        paramListView.setLayoutParams((ViewGroup.LayoutParams)localObject);
        return;
      }
      Object localObject = localListAdapter.getView(i, null, paramListView);
      ((View)localObject).measure(0, 0);
      j += ((View)localObject).getMeasuredHeight();
      i += 1;
    }
  }
  
  private void stopStarburst()
  {
    if (Build.VERSION.SDK_INT >= 14) {
      this.starburst.clearAnimation();
    }
  }
  
  public void End()
  {
    this.mPrefs.store("app_downloading", false);
    this.mPrefs.store("app_installed", false);
    this.mPrefs.store("app_running", false);
    this.mPrefs.store("app_looper_end", true);
    this.mPrefs.store("push_app_download_complete", false);
    this.mPrefs.store("push_app_download_installed", false);
    this.mPrefs.store("push_app_download_installing", false);
    PendingIntent localPendingIntent = PendingIntent.getBroadcast(this, 11643, new Intent(this, AppMonitorMessenger.class), 268435456);
    ((AlarmManager)getSystemService("alarm")).cancel(localPendingIntent);
    ((NotificationManager)mContext.getSystemService("notification")).cancel(1337);
  }
  
  public void appLoadingDialog()
  {
    this.dialog = new Dialog(Instance, 16973840);
    this.dialog.setContentView(2130903076);
    this.dialog.getWindow().addFlags(2);
    this.dialog.setOnKeyListener(new DialogInterface.OnKeyListener()
    {
      public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        if (paramAnonymousInt == 4)
        {
          SweepstakesEntryWall.this.exitToHub();
          return true;
        }
        return false;
      }
    });
    this.dialog.show();
  }
  
  public void checkHistory()
  {
    stopStarburst();
    Intent localIntent = new Intent(this, SweepsEntryHistoryWall.class);
    localIntent.setFlags(268435456);
    startActivity(localIntent);
    if ((this.d != null) && (this.d.isShowing())) {
      this.d.dismiss();
    }
    if ((this.dialog != null) && (this.dialog.isShowing())) {
      this.dialog.dismiss();
    }
    finish();
  }
  
  public void closeAllDialogs()
  {
    if ((this.progressDialog != null) && (this.progressDialog.isShowing())) {
      this.progressDialog.dismiss();
    }
    if ((this.d != null) && (this.d.isShowing())) {
      this.d.dismiss();
    }
    if ((this.welcome != null) && (this.welcome.isShowing())) {
      this.welcome.dismiss();
    }
    if ((this.dialog != null) && (this.dialog.isShowing())) {
      this.dialog.dismiss();
    }
  }
  
  public void configProgressBar()
  {
    if (Build.VERSION.SDK_INT <= 11) {
      this.progressDialog = new SweepsProgressBar(this, 4, 4);
    }
    for (;;)
    {
      this.progressDialog.setProgressStyle(1);
      this.progressDialog.setCancelable(true);
      this.progressDialog.setIcon(2130837765);
      this.progressDialog.setTitle("PCH Sweeps");
      this.progressDialog.setMessage("loading....");
      return;
      this.progressDialog = new ProgressDialog(this);
      this.progressDialog.setProgressNumberFormat(null);
      this.progressDialog.setProgressPercentFormat(null);
    }
  }
  
  public void exitToHub()
  {
    stopStarburst();
    Intent localIntent = new Intent(this, Hub.class);
    localIntent.setFlags(268435456);
    startActivity(localIntent);
    if ((this.d != null) && (this.d.isShowing())) {
      this.d.dismiss();
    }
    finish();
  }
  
  public List<String> getAppPackages()
  {
    localPackageManager = getPackageManager();
    localObject = new ArrayList();
    localArrayList1 = new ArrayList();
    localArrayList2 = new ArrayList();
    localApplicationInfo2 = new ApplicationInfo();
    localApplicationInfo2.packageName = "null.package.exception";
    try
    {
      List localList = localPackageManager.getInstalledApplications(0);
      localObject = localList;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        ((List)localObject).add(localApplicationInfo2);
        continue;
        ApplicationInfo localApplicationInfo1 = (ApplicationInfo)((Iterator)localObject).next();
        if ((localApplicationInfo1.flags & 0x80) == 1)
        {
          localArrayList2.add(localApplicationInfo1.packageName);
          this.analytics.tag("Installed App: " + localApplicationInfo1.loadLabel(localPackageManager));
          localArrayList1.add(localApplicationInfo1.loadLabel(localPackageManager).toString());
        }
        else if ((localApplicationInfo1.flags & 0x1) != 1)
        {
          localArrayList2.add(localApplicationInfo1.packageName);
          localApplicationInfo1.loadLabel(localPackageManager).toString();
          localArrayList1.add(localApplicationInfo1.loadLabel(localPackageManager).toString());
        }
      }
    }
    localObject = ((List)localObject).iterator();
    if (!((Iterator)localObject).hasNext())
    {
      if (localArrayList2.size() == 0) {
        localArrayList2.add("null.app.package");
      }
      return localArrayList2;
    }
  }
  
  public void getImagePicasso(Context paramContext)
  {
    try
    {
      switch (getResources().getDisplayMetrics().densityDpi)
      {
      case 120: 
        Picasso.with(paramContext).load(AppConfig.SweepsWallIconUrlLDPI).placeholder(2130837756).error(2130837756).into(this.icon);
        return;
      }
    }
    catch (Exception paramContext)
    {
      this.icon.setBackgroundResource(2130837756);
      return;
    }
    Picasso.with(paramContext).load(AppConfig.SweepsWallIconUrlMDPI).placeholder(2130837756).error(2130837756).into(this.icon);
    return;
    Picasso.with(paramContext).load(AppConfig.SweepsWallIconUrlHDPI).placeholder(2130837756).error(2130837756).into(this.icon);
    return;
    Picasso.with(paramContext).load(AppConfig.SweepsWallIconUrlXHDPI).placeholder(2130837756).error(2130837756).into(this.icon);
    return;
    Picasso.with(paramContext).load(AppConfig.SweepsWallIconUrlXXHDPI).placeholder(2130837756).error(2130837756).into(this.icon);
    return;
  }
  
  public JSONObject getInstalledApps()
    throws JSONException
  {
    localPackageManager = getPackageManager();
    localObject = new ArrayList();
    localArrayList1 = new ArrayList();
    localArrayList2 = new ArrayList();
    localJSONObject = new JSONObject();
    localApplicationInfo2 = new ApplicationInfo();
    localApplicationInfo2.packageName = "null.package.exception";
    try
    {
      List localList = localPackageManager.getInstalledApplications(0);
      localObject = localList;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        ((List)localObject).add(localApplicationInfo2);
        continue;
        ApplicationInfo localApplicationInfo1 = (ApplicationInfo)((Iterator)localObject).next();
        if ((localApplicationInfo1.flags & 0x80) == 1)
        {
          localArrayList2.add(localApplicationInfo1.packageName);
          localArrayList1.add(localApplicationInfo1.loadLabel(localPackageManager).toString());
          if (localApplicationInfo1.loadLabel(localPackageManager).toString() != null)
          {
            localJSONObject.put(localApplicationInfo1.loadLabel(localPackageManager).toString(), localApplicationInfo1.packageName);
            Log.d("App added to request package", localJSONObject.toString());
          }
        }
        else if ((localApplicationInfo1.flags & 0x1) != 1)
        {
          Log.d("add app", localApplicationInfo1.packageName);
          if (localApplicationInfo1.loadLabel(localPackageManager).toString() != null)
          {
            localJSONObject.put(localApplicationInfo1.loadLabel(localPackageManager).toString(), localApplicationInfo1.packageName);
            Log.d("App added to request package", localJSONObject.toString());
          }
        }
      }
    }
    localObject = ((List)localObject).iterator();
    if (!((Iterator)localObject).hasNext())
    {
      if (localArrayList2.size() == 0) {
        localArrayList2.add("null.app.package");
      }
      Log.d("App Packages", localArrayList2.toString());
      return localJSONObject;
    }
  }
  
  public SharedPreferences getSharedPreferences(Context paramContext)
  {
    return paramContext.getSharedPreferences("MySPrefs", 0);
  }
  
  public boolean getUserBoolPref(String paramString, boolean paramBoolean, Context paramContext)
  {
    return getSharedPreferences(paramContext).getBoolean(paramString, paramBoolean);
  }
  
  public String getUserStringPref(String paramString1, String paramString2, Context paramContext)
  {
    return getSharedPreferences(paramContext).getString(paramString1, paramString2);
  }
  
  public void incentTheApp(String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    paramString.addFlags(268435456);
    startActivity(paramString);
  }
  
  @SuppressLint({"InflateParams"})
  protected void initUI()
  {
    setInstance(this);
    configProgressBar();
    View localView1 = getLayoutInflater().inflate(2130903096, null, false);
    View localView2 = getLayoutInflater().inflate(2130903095, null, false);
    listView = (ListView)findViewById(2131034333);
    listView.addHeaderView(localView1);
    listView.addFooterView(localView2);
    this.balloonGroupLeft = ((ImageView)localView1.findViewById(2131034315));
    this.balloonGroupRight = ((ImageView)localView1.findViewById(2131034313));
    this.starburst = ((ImageView)localView1.findViewById(2131034312));
    this.icon = ((ImageView)localView1.findViewById(2131034316));
    this.rules = ((TextView)localView1.findViewById(2131034232));
    this.rules.setPaintFlags(this.rules.getPaintFlags() | 0x8);
    this.rules.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SweepstakesEntryWall.this.analytics.tag("Rules Link Click");
        paramAnonymousView = new Intent("android.intent.action.VIEW", Uri.parse(AppConfig.RulesSweepsEntryWallUrl));
        paramAnonymousView.addCategory("android.intent.category.BROWSABLE");
        paramAnonymousView.addFlags(268435456);
        try
        {
          SweepstakesEntryWall.this.makeStatusToast("PCH Sweeps", "Loading Official Rules");
          SweepstakesEntryWall.this.startActivity(paramAnonymousView);
          return;
        }
        catch (Exception paramAnonymousView) {}
      }
    });
    this.facts = ((TextView)localView1.findViewById(2131034231));
    this.facts.setPaintFlags(this.facts.getPaintFlags() | 0x8);
    this.facts.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SweepstakesEntryWall.this.analytics.tag("Facts Link Click");
        paramAnonymousView = new Intent("android.intent.action.VIEW", Uri.parse(AppConfig.FactsSweepsEntryWallUrl));
        paramAnonymousView.addCategory("android.intent.category.BROWSABLE");
        paramAnonymousView.addFlags(268435456);
        try
        {
          SweepstakesEntryWall.this.makeStatusToast("PCH Sweeps", "Loading Sweepstakes Facts");
          SweepstakesEntryWall.this.startActivity(paramAnonymousView);
          return;
        }
        catch (Exception paramAnonymousView) {}
      }
    });
    this.backButton = ((Button)localView2.findViewById(2131034310));
    this.backButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SweepstakesEntryWall.this.exitToHub();
      }
    });
    this.history = ((Button)localView2.findViewById(2131034311));
    this.history.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SweepstakesEntryWall.this.analytics.tag("History Click: ");
        SweepstakesEntryWall.this.checkHistory();
      }
    });
    adapter = new SweepstakesWallAdapter(this.ctx, new ArrayList());
    listView.setAdapter(adapter);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        SweepstakesEntryWall.this.listItemAtPosition = (paramAnonymousInt - 1);
        SweepstakesEntryWall.currentItem = SweepstakesEntryWall.adapter.getItem(SweepstakesEntryWall.this.listItemAtPosition);
        if ((SweepstakesEntryWall.adapter.getItem(SweepstakesEntryWall.this.listItemAtPosition).getStatus().equals("icon_entry")) || (SweepstakesEntryWall.adapter.getItem(SweepstakesEntryWall.this.listItemAtPosition).getStatus().equals("icon_pending")))
        {
          SweepstakesEntryWall.currentItem = SweepstakesEntryWall.adapter.getItem(SweepstakesEntryWall.this.listItemAtPosition);
          SweepstakesEntryWall.this.appURL = SweepstakesEntryWall.currentItem.getURL();
          SweepstakesEntryWall.this.apppkg = SweepstakesEntryWall.currentItem.getPKG();
          paramAnonymousAdapterView = SweepstakesEntryWall.currentItem.getName();
          if (!SweepstakesEntryWall.currentItem.getStatus().equals("icon_pending")) {
            break label276;
          }
          if (!SweepstakesEntryWall.this.getAppPackages().contains(SweepstakesEntryWall.currentItem.getPKG()))
          {
            SweepstakesEntryWall.this.analytics.tag("Pending Offer Click: " + SweepstakesEntryWall.currentItem.getName());
            SweepstakesEntryWall.this.appDownloaderDialog(SweepstakesEntryWall.currentItem.getName(), SweepstakesEntryWall.currentItem.getIMG(), SweepstakesEntryWall.currentItem.getPKG());
            SweepstakesEntryWall.this.startInstallProcess(SweepstakesEntryWall.this.apppkg, paramAnonymousAdapterView, SweepstakesEntryWall.this.appURL);
            SweepstakesEntryWall.this.incentTheApp(SweepstakesEntryWall.this.appURL);
            SweepstakesEntryWall.this.makeStatusToast("Bonus Entry Status", "First step is to CLICK INSTALL!");
          }
        }
        else
        {
          return;
        }
        SweepstakesEntryWall.this.makeStatusToast("Entry is Processing", "Check back later!");
        return;
        label276:
        SweepstakesEntryWall.this.analytics.tag("Offer Click: " + SweepstakesEntryWall.currentItem.getName());
        SweepstakesEntryWall.this.analytics.tag("Offer Click");
        SweepstakesEntryWall.this.appDownloaderDialog(SweepstakesEntryWall.currentItem.getName(), SweepstakesEntryWall.currentItem.getIMG(), SweepstakesEntryWall.currentItem.getPKG());
        SweepstakesEntryWall.this.startInstallProcess(SweepstakesEntryWall.this.apppkg, paramAnonymousAdapterView, SweepstakesEntryWall.this.appURL);
        SweepstakesEntryWall.this.incentTheApp(SweepstakesEntryWall.this.appURL);
        SweepstakesEntryWall.this.makeStatusToast("Bonus Entry Status", "First step is to CLICK INSTALL!");
      }
    });
    this.informationButton = ((ImageButton)findViewById(2131034218));
    this.informationButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SweepstakesEntryWall.this.openOptionsMenu();
      }
    });
    new AsyncListViewLoader(null).execute(new String[] { this.SweepsWallAPIUrl });
  }
  
  public boolean isAppInstalled()
  {
    return this.mPrefs.get("app_installed", false);
  }
  
  public boolean isAppInstalled(Context paramContext)
  {
    return getUserBoolPref("app_installed", false, paramContext);
  }
  
  public boolean isOnline()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting());
  }
  
  public void makeStatusToast(String paramString1, String paramString2)
  {
    View localView = getLayoutInflater().inflate(2130903077, (ViewGroup)findViewById(2131034268));
    ((TextView)localView.findViewById(2131034238)).setText(paramString1);
    ((TextView)localView.findViewById(2131034232)).setText(paramString2);
    paramString1 = new Toast(this);
    paramString1.setView(localView);
    paramString1.setDuration(1);
    paramString1.show();
  }
  
  public void onBackPressed()
  {
    Intent localIntent = new Intent(this, Hub.class);
    localIntent.setFlags(268435456);
    startActivity(localIntent);
    finish();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903108);
    Instance = this;
    this.mPrefs = PCHPreferencesFactory.createPreferences(this.ctx);
    this.SweepsWallAPIUrl = AppConfig.SweepsOfferApiUrl;
    new AsyncAdvertisingChecker(null).execute(new String[0]);
    mContext = this;
    initUI();
    this.analytics = AnalyticsFactory.getInstance(this);
    this.analytics.tag("Wall View");
    int i = this.mPrefs.get("viewcount", 1);
    this.analytics.tag("Wall View Count: " + i);
    analyticsCleanup();
    if (this.mPrefs.get("sweeps_first_pass", true))
    {
      this.analytics.tag("Sweeps Wall New User");
      this.mPrefs.store("sweeps_first_pass", false);
      return;
    }
    this.analytics.tag("Sweeps Wall Returning User");
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    paramMenu.add(0, 2, 0, "Home").setIcon(2130837766);
    paramMenu.add(0, 3, 0, "Directions").setIcon(17301576);
    return true;
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    closeAllDialogs();
    EasyTracker.getInstance().activityStop(this);
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return false;
    case 2: 
      this.analytics.tag("User has used home button in flow.");
      exitToHub();
      return false;
    }
    this.analytics.tag("Setting Options");
    appDownloadWeclomeDialog();
    return false;
  }
  
  public void onPause()
  {
    super.onPause();
    this.analytics.close();
    this.analytics.upload();
  }
  
  public void onResume()
  {
    super.onResume();
    EasyTracker.getInstance().activityStart(this);
  }
  
  public void onStart()
  {
    super.onStart();
    EasyTracker.getInstance().activityStart(this);
    if (this.d == null) {
      End();
    }
    this.appBonus = this.mPrefs.get("app_bonus_complete", false);
    if (this.appBonus)
    {
      Object localObject = this.ctx.getSharedPreferences("MySPrefs", 0).edit();
      ((SharedPreferences.Editor)localObject).putBoolean("app_bonus_complete", false);
      ((SharedPreferences.Editor)localObject).commit();
      appDownloadCompleteDialog();
      if ((this.d != null) && (this.d.isShowing())) {
        this.d.dismiss();
      }
      localObject = PendingIntent.getBroadcast(this, 11643, new Intent(this, AppMonitorMessenger.class), 268435456);
      ((AlarmManager)getSystemService("alarm")).cancel((PendingIntent)localObject);
    }
    rotateStarburst();
  }
  
  public void onStop()
  {
    super.onStop();
    stopStarburst();
    if ((this.progressDialog != null) && (this.progressDialog.isShowing())) {
      this.progressDialog.dismiss();
    }
    if ((this.dialog != null) && (this.dialog.isShowing())) {
      this.dialog.dismiss();
    }
    EasyTracker.getInstance().activityStop(this);
  }
  
  public void startApp(final String paramString)
  {
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        new Intent();
        Intent localIntent = SweepstakesEntryWall.this.ctx.getPackageManager().getLaunchIntentForPackage(paramString);
        localIntent.addCategory("android.intent.category.LAUNCHER");
        SweepstakesEntryWall.this.ctx.startActivity(localIntent);
      }
    }, 1000L);
  }
  
  public void startInstallProcess(String paramString1, String paramString2, String paramString3)
  {
    this.mPrefs.store("app_downloading", true);
    this.mPrefs.store("app_we_download", paramString1);
    this.mPrefs.store("app_we_download_name", paramString2);
    this.mPrefs.store("app_bonus_complete", false);
    this.mPrefs.store("app_looper_end", false);
    Calendar localCalendar = Calendar.getInstance();
    AlarmManager localAlarmManager = (AlarmManager)getSystemService("alarm");
    Intent localIntent = new Intent(this, AppMonitorMessenger.class);
    Bundle localBundle = new Bundle();
    localBundle.putString("app_pkg", paramString1);
    localBundle.putString("app_name", paramString2);
    localBundle.putString("app_url", paramString3);
    localIntent.putExtras(localBundle);
    paramString1 = PendingIntent.getBroadcast(this, 11643, localIntent, 268435456);
    localAlarmManager.setRepeating(0, localCalendar.getTimeInMillis(), 10000L, paramString1);
  }
  
  public void stopInstallProcess(String paramString)
  {
    this.mPrefs.store("app_downloading", false);
    paramString = new Intent(this.ctx, SweepstakesEntryWall.class);
    paramString = PendingIntent.getBroadcast(this.ctx, 11643, paramString, 0);
    ((AlarmManager)this.ctx.getSystemService("alarm")).cancel(paramString);
  }
  
  private class AsyncAdvertisingChecker
    extends AsyncTask<String, Void, AdvertisingIdClient.Info>
  {
    private AsyncAdvertisingChecker() {}
    
    protected AdvertisingIdClient.Info doInBackground(String... paramVarArgs)
    {
      try
      {
        paramVarArgs = AdvertisingIdClient.getAdvertisingIdInfo(SweepstakesEntryWall.this.ctx);
        return paramVarArgs;
      }
      catch (GooglePlayServicesRepairableException paramVarArgs)
      {
        GooglePlayServicesUtil.getErrorDialog(paramVarArgs.getConnectionStatusCode(), SweepstakesEntryWall.Instance, 57);
        return null;
      }
      catch (IOException paramVarArgs)
      {
        paramVarArgs.printStackTrace();
        return null;
      }
      catch (GooglePlayServicesNotAvailableException paramVarArgs)
      {
        paramVarArgs.printStackTrace();
      }
      return null;
    }
    
    protected void onPostExecute(AdvertisingIdClient.Info paramInfo)
    {
      SweepstakesEntryWall.this.mAdvertisingId = paramInfo.getId();
      SweepstakesEntryWall localSweepstakesEntryWall = SweepstakesEntryWall.this;
      if (paramInfo.isLimitAdTrackingEnabled()) {}
      for (boolean bool = false;; bool = true)
      {
        localSweepstakesEntryWall.mAdvertisingEnabled = bool;
        return;
      }
    }
  }
  
  private class AsyncListViewLoader
    extends AsyncTask<String, Void, ArrayList<AppModel>>
  {
    private AsyncListViewLoader() {}
    
    /* Error */
    public String _getResponseBody(HttpEntity paramHttpEntity)
      throws IOException, ParseException
    {
      // Byte code:
      //   0: aload_1
      //   1: ifnonnull +13 -> 14
      //   4: new 33	java/lang/IllegalArgumentException
      //   7: dup
      //   8: ldc 35
      //   10: invokespecial 38	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
      //   13: athrow
      //   14: aload_1
      //   15: invokeinterface 44 1 0
      //   20: astore 4
      //   22: aload 4
      //   24: ifnonnull +6 -> 30
      //   27: ldc 46
      //   29: areturn
      //   30: aload_1
      //   31: invokeinterface 50 1 0
      //   36: ldc2_w 51
      //   39: lcmp
      //   40: ifle +13 -> 53
      //   43: new 33	java/lang/IllegalArgumentException
      //   46: dup
      //   47: ldc 54
      //   49: invokespecial 38	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
      //   52: athrow
      //   53: aload_0
      //   54: aload_1
      //   55: invokevirtual 57	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:getContentCharSet	(Lorg/apache/http/HttpEntity;)Ljava/lang/String;
      //   58: astore_3
      //   59: aload_3
      //   60: astore_1
      //   61: aload_3
      //   62: ifnonnull +6 -> 68
      //   65: ldc 59
      //   67: astore_1
      //   68: new 61	java/io/InputStreamReader
      //   71: dup
      //   72: aload 4
      //   74: aload_1
      //   75: invokespecial 64	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
      //   78: astore_1
      //   79: new 66	java/lang/StringBuilder
      //   82: dup
      //   83: invokespecial 67	java/lang/StringBuilder:<init>	()V
      //   86: astore_3
      //   87: sipush 1024
      //   90: newarray char
      //   92: astore 4
      //   94: aload_1
      //   95: aload 4
      //   97: invokevirtual 73	java/io/Reader:read	([C)I
      //   100: istore_2
      //   101: iload_2
      //   102: iconst_m1
      //   103: if_icmpne +12 -> 115
      //   106: aload_1
      //   107: invokevirtual 76	java/io/Reader:close	()V
      //   110: aload_3
      //   111: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   114: areturn
      //   115: aload_3
      //   116: aload 4
      //   118: iconst_0
      //   119: iload_2
      //   120: invokevirtual 84	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
      //   123: pop
      //   124: goto -30 -> 94
      //   127: astore_3
      //   128: aload_1
      //   129: invokevirtual 76	java/io/Reader:close	()V
      //   132: aload_3
      //   133: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	134	0	this	AsyncListViewLoader
      //   0	134	1	paramHttpEntity	HttpEntity
      //   100	20	2	i	int
      //   58	58	3	localObject1	Object
      //   127	6	3	localObject2	Object
      //   20	97	4	localObject3	Object
      // Exception table:
      //   from	to	target	type
      //   87	94	127	finally
      //   94	101	127	finally
      //   115	124	127	finally
    }
    
    /* Error */
    protected ArrayList<AppModel> doInBackground(String... paramVarArgs)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 16	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:this$0	Lpch/sweeps/walls/SweepstakesEntryWall;
      //   4: new 100	java/util/ArrayList
      //   7: dup
      //   8: invokespecial 101	java/util/ArrayList:<init>	()V
      //   11: invokestatic 105	pch/sweeps/walls/SweepstakesEntryWall:access$11	(Lpch/sweeps/walls/SweepstakesEntryWall;Ljava/util/ArrayList;)V
      //   14: new 100	java/util/ArrayList
      //   17: dup
      //   18: invokespecial 101	java/util/ArrayList:<init>	()V
      //   21: astore 6
      //   23: aload_0
      //   24: new 107	java/net/URL
      //   27: dup
      //   28: aload_1
      //   29: iconst_0
      //   30: aaload
      //   31: invokespecial 108	java/net/URL:<init>	(Ljava/lang/String;)V
      //   34: invokevirtual 112	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:execute	(Ljava/net/URL;)Lorg/droidparts/net/http/HTTPResponse;
      //   37: astore_1
      //   38: ldc 114
      //   40: aload_1
      //   41: getfield 120	org/droidparts/net/http/HTTPResponse:body	Ljava/lang/String;
      //   44: invokestatic 126	pch/apps/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)V
      //   47: aload_1
      //   48: getfield 120	org/droidparts/net/http/HTTPResponse:body	Ljava/lang/String;
      //   51: astore 5
      //   53: ldc -128
      //   55: aload 5
      //   57: invokestatic 126	pch/apps/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)V
      //   60: aload 5
      //   62: ldc -126
      //   64: invokevirtual 136	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
      //   67: istore 4
      //   69: iload 4
      //   71: ifne +718 -> 789
      //   74: aconst_null
      //   75: astore_1
      //   76: new 138	org/json/JSONObject
      //   79: dup
      //   80: aload 5
      //   82: invokespecial 139	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   85: astore 5
      //   87: aload 5
      //   89: astore_1
      //   90: aload_1
      //   91: ldc -115
      //   93: invokevirtual 145	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   96: astore 7
      //   98: aload_1
      //   99: ldc -109
      //   101: invokevirtual 151	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   104: astore 5
      //   106: aload_0
      //   107: getfield 16	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:this$0	Lpch/sweeps/walls/SweepstakesEntryWall;
      //   110: invokestatic 155	pch/sweeps/walls/SweepstakesEntryWall:access$12	(Lpch/sweeps/walls/SweepstakesEntryWall;)Lpch/apps/util/analytics/Analytics;
      //   113: new 66	java/lang/StringBuilder
      //   116: dup
      //   117: ldc -99
      //   119: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   122: aload 5
      //   124: invokevirtual 164	org/json/JSONArray:length	()I
      //   127: invokevirtual 167	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   130: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   133: invokeinterface 172 2 0
      //   138: aload_1
      //   139: ldc -82
      //   141: invokevirtual 151	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   144: astore 8
      //   146: aload_0
      //   147: getfield 16	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:this$0	Lpch/sweeps/walls/SweepstakesEntryWall;
      //   150: invokestatic 155	pch/sweeps/walls/SweepstakesEntryWall:access$12	(Lpch/sweeps/walls/SweepstakesEntryWall;)Lpch/apps/util/analytics/Analytics;
      //   153: new 66	java/lang/StringBuilder
      //   156: dup
      //   157: ldc -80
      //   159: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   162: aload 8
      //   164: invokevirtual 164	org/json/JSONArray:length	()I
      //   167: invokevirtual 167	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   170: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   173: invokeinterface 172 2 0
      //   178: aload_1
      //   179: ldc -78
      //   181: invokevirtual 145	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   184: astore 8
      //   186: aload_0
      //   187: getfield 16	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:this$0	Lpch/sweeps/walls/SweepstakesEntryWall;
      //   190: invokestatic 155	pch/sweeps/walls/SweepstakesEntryWall:access$12	(Lpch/sweeps/walls/SweepstakesEntryWall;)Lpch/apps/util/analytics/Analytics;
      //   193: new 66	java/lang/StringBuilder
      //   196: dup
      //   197: ldc -76
      //   199: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   202: aload 8
      //   204: invokevirtual 181	java/lang/String:toString	()Ljava/lang/String;
      //   207: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   210: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   213: invokeinterface 172 2 0
      //   218: aload_1
      //   219: ldc -70
      //   221: invokevirtual 190	org/json/JSONObject:getInt	(Ljava/lang/String;)I
      //   224: istore_2
      //   225: aload_0
      //   226: getfield 16	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:this$0	Lpch/sweeps/walls/SweepstakesEntryWall;
      //   229: invokestatic 155	pch/sweeps/walls/SweepstakesEntryWall:access$12	(Lpch/sweeps/walls/SweepstakesEntryWall;)Lpch/apps/util/analytics/Analytics;
      //   232: new 66	java/lang/StringBuilder
      //   235: dup
      //   236: ldc -64
      //   238: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   241: iload_2
      //   242: invokevirtual 167	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   245: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   248: invokeinterface 172 2 0
      //   253: aload_0
      //   254: getfield 16	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:this$0	Lpch/sweeps/walls/SweepstakesEntryWall;
      //   257: invokestatic 196	pch/sweeps/walls/SweepstakesEntryWall:access$9	(Lpch/sweeps/walls/SweepstakesEntryWall;)Lpch/apps/util/preferences/PCHPreferences;
      //   260: ldc -115
      //   262: aload 7
      //   264: invokeinterface 201 3 0
      //   269: iconst_0
      //   270: istore_2
      //   271: aload 5
      //   273: invokevirtual 164	org/json/JSONArray:length	()I
      //   276: istore_3
      //   277: iload_2
      //   278: iload_3
      //   279: if_icmplt +229 -> 508
      //   282: aload_1
      //   283: ldc -53
      //   285: invokevirtual 151	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   288: astore_1
      //   289: aload_0
      //   290: getfield 16	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:this$0	Lpch/sweeps/walls/SweepstakesEntryWall;
      //   293: invokestatic 155	pch/sweeps/walls/SweepstakesEntryWall:access$12	(Lpch/sweeps/walls/SweepstakesEntryWall;)Lpch/apps/util/analytics/Analytics;
      //   296: ldc -64
      //   298: invokeinterface 172 2 0
      //   303: iconst_0
      //   304: istore_2
      //   305: iload_2
      //   306: aload_1
      //   307: invokevirtual 164	org/json/JSONArray:length	()I
      //   310: if_icmplt +306 -> 616
      //   313: goto +476 -> 789
      //   316: iload_2
      //   317: aload_0
      //   318: getfield 16	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:this$0	Lpch/sweeps/walls/SweepstakesEntryWall;
      //   321: invokestatic 207	pch/sweeps/walls/SweepstakesEntryWall:access$13	(Lpch/sweeps/walls/SweepstakesEntryWall;)Ljava/util/ArrayList;
      //   324: invokevirtual 210	java/util/ArrayList:size	()I
      //   327: if_icmplt +408 -> 735
      //   330: ldc -44
      //   332: new 66	java/lang/StringBuilder
      //   335: dup
      //   336: invokespecial 67	java/lang/StringBuilder:<init>	()V
      //   339: aload_0
      //   340: getfield 16	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:this$0	Lpch/sweeps/walls/SweepstakesEntryWall;
      //   343: invokestatic 207	pch/sweeps/walls/SweepstakesEntryWall:access$13	(Lpch/sweeps/walls/SweepstakesEntryWall;)Ljava/util/ArrayList;
      //   346: invokevirtual 210	java/util/ArrayList:size	()I
      //   349: invokevirtual 167	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   352: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   355: invokestatic 126	pch/apps/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)V
      //   358: aload_0
      //   359: getfield 16	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:this$0	Lpch/sweeps/walls/SweepstakesEntryWall;
      //   362: invokestatic 207	pch/sweeps/walls/SweepstakesEntryWall:access$13	(Lpch/sweeps/walls/SweepstakesEntryWall;)Ljava/util/ArrayList;
      //   365: invokevirtual 210	java/util/ArrayList:size	()I
      //   368: ifne +47 -> 415
      //   371: aload_0
      //   372: getfield 16	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:this$0	Lpch/sweeps/walls/SweepstakesEntryWall;
      //   375: invokestatic 155	pch/sweeps/walls/SweepstakesEntryWall:access$12	(Lpch/sweeps/walls/SweepstakesEntryWall;)Lpch/apps/util/analytics/Analytics;
      //   378: ldc -42
      //   380: invokeinterface 172 2 0
      //   385: aload_0
      //   386: getfield 16	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:this$0	Lpch/sweeps/walls/SweepstakesEntryWall;
      //   389: invokestatic 207	pch/sweeps/walls/SweepstakesEntryWall:access$13	(Lpch/sweeps/walls/SweepstakesEntryWall;)Ljava/util/ArrayList;
      //   392: new 216	pch/sweeps/walls/AppModel
      //   395: dup
      //   396: ldc -38
      //   398: ldc -36
      //   400: ldc -34
      //   402: ldc 46
      //   404: ldc 46
      //   406: ldc -32
      //   408: invokespecial 227	pch/sweeps/walls/AppModel:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   411: invokevirtual 231	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   414: pop
      //   415: aload_0
      //   416: getfield 16	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:this$0	Lpch/sweeps/walls/SweepstakesEntryWall;
      //   419: invokestatic 207	pch/sweeps/walls/SweepstakesEntryWall:access$13	(Lpch/sweeps/walls/SweepstakesEntryWall;)Ljava/util/ArrayList;
      //   422: areturn
      //   423: astore 5
      //   425: aload_0
      //   426: getfield 16	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:this$0	Lpch/sweeps/walls/SweepstakesEntryWall;
      //   429: invokestatic 235	pch/sweeps/walls/SweepstakesEntryWall:access$8	(Lpch/sweeps/walls/SweepstakesEntryWall;)Landroid/app/Dialog;
      //   432: ifnull +26 -> 458
      //   435: aload_0
      //   436: getfield 16	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:this$0	Lpch/sweeps/walls/SweepstakesEntryWall;
      //   439: invokestatic 235	pch/sweeps/walls/SweepstakesEntryWall:access$8	(Lpch/sweeps/walls/SweepstakesEntryWall;)Landroid/app/Dialog;
      //   442: invokevirtual 241	android/app/Dialog:isShowing	()Z
      //   445: ifeq +13 -> 458
      //   448: aload_0
      //   449: getfield 16	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:this$0	Lpch/sweeps/walls/SweepstakesEntryWall;
      //   452: invokestatic 235	pch/sweeps/walls/SweepstakesEntryWall:access$8	(Lpch/sweeps/walls/SweepstakesEntryWall;)Landroid/app/Dialog;
      //   455: invokevirtual 244	android/app/Dialog:dismiss	()V
      //   458: aload_0
      //   459: getfield 16	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:this$0	Lpch/sweeps/walls/SweepstakesEntryWall;
      //   462: invokevirtual 247	pch/sweeps/walls/SweepstakesEntryWall:exitToHub	()V
      //   465: goto -375 -> 90
      //   468: astore_1
      //   469: aload_0
      //   470: getfield 16	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:this$0	Lpch/sweeps/walls/SweepstakesEntryWall;
      //   473: invokestatic 235	pch/sweeps/walls/SweepstakesEntryWall:access$8	(Lpch/sweeps/walls/SweepstakesEntryWall;)Landroid/app/Dialog;
      //   476: ifnull +26 -> 502
      //   479: aload_0
      //   480: getfield 16	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:this$0	Lpch/sweeps/walls/SweepstakesEntryWall;
      //   483: invokestatic 235	pch/sweeps/walls/SweepstakesEntryWall:access$8	(Lpch/sweeps/walls/SweepstakesEntryWall;)Landroid/app/Dialog;
      //   486: invokevirtual 241	android/app/Dialog:isShowing	()Z
      //   489: ifeq +13 -> 502
      //   492: aload_0
      //   493: getfield 16	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:this$0	Lpch/sweeps/walls/SweepstakesEntryWall;
      //   496: invokestatic 235	pch/sweeps/walls/SweepstakesEntryWall:access$8	(Lpch/sweeps/walls/SweepstakesEntryWall;)Landroid/app/Dialog;
      //   499: invokevirtual 244	android/app/Dialog:dismiss	()V
      //   502: aload_1
      //   503: invokevirtual 250	java/lang/Throwable:printStackTrace	()V
      //   506: aconst_null
      //   507: areturn
      //   508: aload 5
      //   510: iload_2
      //   511: invokevirtual 254	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
      //   514: astore 11
      //   516: aload 11
      //   518: ldc_w 256
      //   521: invokevirtual 145	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   524: astore 7
      //   526: aload 11
      //   528: ldc_w 258
      //   531: invokevirtual 145	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   534: astore 8
      //   536: aload 11
      //   538: ldc_w 260
      //   541: invokevirtual 145	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   544: astore 9
      //   546: aload 11
      //   548: ldc_w 262
      //   551: invokevirtual 145	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   554: astore 10
      //   556: aload 11
      //   558: ldc_w 264
      //   561: invokevirtual 145	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   564: astore 11
      //   566: aload_0
      //   567: getfield 16	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:this$0	Lpch/sweeps/walls/SweepstakesEntryWall;
      //   570: invokestatic 207	pch/sweeps/walls/SweepstakesEntryWall:access$13	(Lpch/sweeps/walls/SweepstakesEntryWall;)Ljava/util/ArrayList;
      //   573: new 216	pch/sweeps/walls/AppModel
      //   576: dup
      //   577: aload 9
      //   579: aload 7
      //   581: aload 8
      //   583: aload 11
      //   585: aload 10
      //   587: ldc_w 266
      //   590: invokespecial 227	pch/sweeps/walls/AppModel:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   593: invokevirtual 231	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   596: pop
      //   597: iload_2
      //   598: iconst_1
      //   599: iadd
      //   600: istore_2
      //   601: goto -330 -> 271
      //   604: astore_1
      //   605: new 160	org/json/JSONArray
      //   608: dup
      //   609: invokespecial 267	org/json/JSONArray:<init>	()V
      //   612: astore_1
      //   613: goto -310 -> 303
      //   616: aload_1
      //   617: iload_2
      //   618: invokevirtual 254	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
      //   621: astore 10
      //   623: aload 10
      //   625: ldc_w 256
      //   628: invokevirtual 145	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   631: astore 5
      //   633: aload 10
      //   635: ldc_w 258
      //   638: invokevirtual 145	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   641: astore 7
      //   643: aload 10
      //   645: ldc_w 260
      //   648: invokevirtual 145	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   651: astore 8
      //   653: aload 10
      //   655: ldc_w 262
      //   658: invokevirtual 145	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   661: astore 9
      //   663: aload 10
      //   665: ldc_w 264
      //   668: invokevirtual 145	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   671: astore 10
      //   673: aload 6
      //   675: aload 9
      //   677: invokevirtual 231	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   680: pop
      //   681: aload_0
      //   682: getfield 16	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:this$0	Lpch/sweeps/walls/SweepstakesEntryWall;
      //   685: invokestatic 207	pch/sweeps/walls/SweepstakesEntryWall:access$13	(Lpch/sweeps/walls/SweepstakesEntryWall;)Ljava/util/ArrayList;
      //   688: new 216	pch/sweeps/walls/AppModel
      //   691: dup
      //   692: aload 8
      //   694: aload 5
      //   696: aload 7
      //   698: aload 10
      //   700: aload 9
      //   702: ldc_w 269
      //   705: invokespecial 227	pch/sweeps/walls/AppModel:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   708: invokevirtual 231	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   711: pop
      //   712: iload_2
      //   713: iconst_1
      //   714: iadd
      //   715: istore_2
      //   716: goto -411 -> 305
      //   719: astore 5
      //   721: ldc_w 271
      //   724: aload 5
      //   726: invokevirtual 274	java/lang/Exception:getMessage	()Ljava/lang/String;
      //   729: invokestatic 126	pch/apps/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)V
      //   732: goto -20 -> 712
      //   735: new 66	java/lang/StringBuilder
      //   738: dup
      //   739: ldc_w 276
      //   742: invokespecial 158	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   745: iload_2
      //   746: invokevirtual 167	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   749: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   752: aload_0
      //   753: getfield 16	pch/sweeps/walls/SweepstakesEntryWall$AsyncListViewLoader:this$0	Lpch/sweeps/walls/SweepstakesEntryWall;
      //   756: invokestatic 207	pch/sweeps/walls/SweepstakesEntryWall:access$13	(Lpch/sweeps/walls/SweepstakesEntryWall;)Ljava/util/ArrayList;
      //   759: iload_2
      //   760: invokevirtual 280	java/util/ArrayList:get	(I)Ljava/lang/Object;
      //   763: checkcast 216	pch/sweeps/walls/AppModel
      //   766: invokevirtual 283	pch/sweeps/walls/AppModel:getName	()Ljava/lang/String;
      //   769: invokestatic 126	pch/apps/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)V
      //   772: iload_2
      //   773: iconst_1
      //   774: iadd
      //   775: istore_2
      //   776: goto -460 -> 316
      //   779: astore 5
      //   781: goto -279 -> 502
      //   784: astore 7
      //   786: goto -189 -> 597
      //   789: iconst_0
      //   790: istore_2
      //   791: goto -475 -> 316
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	794	0	this	AsyncListViewLoader
      //   0	794	1	paramVarArgs	String[]
      //   224	567	2	i	int
      //   276	4	3	j	int
      //   67	3	4	bool	boolean
      //   51	221	5	localObject1	Object
      //   423	86	5	localJSONException	JSONException
      //   631	64	5	str1	String
      //   719	6	5	localException1	Exception
      //   779	1	5	localException2	Exception
      //   21	653	6	localArrayList	ArrayList
      //   96	601	7	str2	String
      //   784	1	7	localException3	Exception
      //   144	549	8	localObject2	Object
      //   544	157	9	str3	String
      //   554	145	10	localObject3	Object
      //   514	70	11	localObject4	Object
      // Exception table:
      //   from	to	target	type
      //   76	87	423	org/json/JSONException
      //   23	69	468	java/lang/Throwable
      //   76	87	468	java/lang/Throwable
      //   90	269	468	java/lang/Throwable
      //   271	277	468	java/lang/Throwable
      //   282	303	468	java/lang/Throwable
      //   305	313	468	java/lang/Throwable
      //   316	415	468	java/lang/Throwable
      //   415	423	468	java/lang/Throwable
      //   425	458	468	java/lang/Throwable
      //   458	465	468	java/lang/Throwable
      //   508	597	468	java/lang/Throwable
      //   605	613	468	java/lang/Throwable
      //   616	712	468	java/lang/Throwable
      //   721	732	468	java/lang/Throwable
      //   735	772	468	java/lang/Throwable
      //   282	303	604	java/lang/Exception
      //   616	712	719	java/lang/Exception
      //   469	502	779	java/lang/Exception
      //   508	597	784	java/lang/Exception
    }
    
    public HTTPResponse execute(URL paramURL)
      throws JSONException
    {
      Object localObject3 = SweepstakesEntryWall.this.getSharedPreferences("JsonPrefs", 0);
      Object localObject2 = ((SharedPreferences)localObject3).getString("apiKey", "no-key-set");
      Log.d("API URL", paramURL.toString());
      Object localObject1 = localObject2;
      if (((String)localObject2).equals("no-key-set")) {
        localObject1 = "187634e51b59a63ebbf6020b";
      }
      if (((SharedPreferences)localObject3).getString("apiVersion", "no-version-set").equals("no-version-set")) {}
      localObject3 = getLocalIpAddress();
      localObject2 = localObject3;
      if (localObject3 == null) {
        localObject2 = "no-IP-located";
      }
      localObject3 = SweepstakesEntryWall.this.mPrefs.get("sid", "no-sid-stored");
      JSONObject localJSONObject1 = SweepstakesEntryWall.this.getInstalledApps();
      JSONObject localJSONObject2 = new JSONObject();
      localJSONObject2.put("apikey", sha1Hash((String)localObject1).toLowerCase());
      localJSONObject2.put("apiversion", "1.0");
      localJSONObject2.put("advertisingid", SweepstakesEntryWall.this.mAdvertisingId);
      localJSONObject2.put("advertisingenabled", SweepstakesEntryWall.this.mAdvertisingEnabled);
      localJSONObject2.put("ip", localObject2);
      localJSONObject2.put("sid", localObject3);
      localJSONObject2.put("appcount", 15);
      localJSONObject2.put("installedapps", localJSONObject1);
      Log.d("JSON Request Data: ", localJSONObject2.toString());
      return makeRequest(paramURL.toString(), localJSONObject2);
    }
    
    public String getContentCharSet(HttpEntity paramHttpEntity)
      throws ParseException
    {
      if (paramHttpEntity == null) {
        throw new IllegalArgumentException("HTTP entity may not be null");
      }
      Object localObject2 = null;
      Object localObject1 = localObject2;
      if (paramHttpEntity.getContentType() != null)
      {
        paramHttpEntity = paramHttpEntity.getContentType().getElements();
        localObject1 = localObject2;
        if (paramHttpEntity.length > 0)
        {
          paramHttpEntity = paramHttpEntity[0].getParameterByName("charset");
          localObject1 = localObject2;
          if (paramHttpEntity != null) {
            localObject1 = paramHttpEntity.getValue();
          }
        }
      }
      return localObject1;
    }
    
    public String getLocalIpAddress()
    {
      try
      {
        InetAddress localInetAddress;
        do
        {
          localObject = NetworkInterface.getNetworkInterfaces();
          Enumeration localEnumeration;
          while (!localEnumeration.hasMoreElements())
          {
            if (!((Enumeration)localObject).hasMoreElements()) {
              break;
            }
            localEnumeration = ((NetworkInterface)((Enumeration)localObject).nextElement()).getInetAddresses();
          }
          localInetAddress = (InetAddress)localEnumeration.nextElement();
        } while (localInetAddress.isLoopbackAddress());
        Object localObject = localInetAddress.getHostAddress().toString();
        return localObject;
      }
      catch (SocketException localSocketException) {}
      return null;
    }
    
    public String getResponseBody(HttpResponse paramHttpResponse)
    {
      Object localObject = null;
      HttpResponse localHttpResponse = null;
      try
      {
        paramHttpResponse = paramHttpResponse.getEntity();
        localHttpResponse = paramHttpResponse;
        paramHttpResponse = _getResponseBody(paramHttpResponse);
      }
      catch (ParseException paramHttpResponse)
      {
        paramHttpResponse.printStackTrace();
        return null;
      }
      catch (IOException paramHttpResponse)
      {
        do
        {
          paramHttpResponse = localObject;
        } while (localHttpResponse == null);
        try
        {
          localHttpResponse.consumeContent();
          return null;
        }
        catch (IOException paramHttpResponse) {}
      }
      return paramHttpResponse;
      return null;
    }
    
    public HTTPResponse makeRequest(String paramString, JSONObject paramJSONObject)
    {
      try
      {
        paramString = new RESTClient2(SweepstakesEntryWall.mContext).post(paramString, paramJSONObject);
        return paramString;
      }
      catch (HTTPException paramString)
      {
        paramString.printStackTrace();
      }
      return null;
    }
    
    protected void onPostExecute(ArrayList<AppModel> paramArrayList)
    {
      super.onPostExecute(paramArrayList);
      if ((SweepstakesEntryWall.this.d != null) && (SweepstakesEntryWall.this.d.isShowing())) {
        SweepstakesEntryWall.this.d.dismiss();
      }
      if (paramArrayList != null)
      {
        SweepstakesEntryWall.adapter.setItemList(paramArrayList);
        SweepstakesEntryWall.adapter.notifyDataSetChanged();
        SweepstakesEntryWall.this.getImagePicasso(SweepstakesEntryWall.mContext);
        SweepstakesEntryWall.this.rotateStarburst();
        SweepstakesEntryWall.this.animateContestBalloons();
        if ((SweepstakesEntryWall.this.dialog != null) && (SweepstakesEntryWall.this.dialog.isShowing())) {
          SweepstakesEntryWall.this.dialog.dismiss();
        }
        if (!SweepstakesEntryWall.this.mPrefs.get("app_bonus_welcome", false)) {
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              try
              {
                SweepstakesEntryWall.this.appDownloadWeclomeDialog();
                SweepstakesEntryWall.this.mPrefs.store("app_bonus_welcome", true);
                return;
              }
              catch (Exception localException)
              {
                for (;;) {}
              }
            }
          }, 1000L);
        }
        return;
      }
      SweepstakesEntryWall.this.makeStatusToast("PCH Sweeps", "Check back again later for more ways to win!");
      SweepstakesEntryWall.this.exitToHub();
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      if ((SweepstakesEntryWall.this.d != null) && (SweepstakesEntryWall.this.dialog.isShowing())) {
        SweepstakesEntryWall.this.d.dismiss();
      }
      SweepstakesEntryWall.endNotifications();
      SweepstakesEntryWall.this.appLoadingDialog();
    }
    
    public String sha1Hash(String paramString)
    {
      int i = 0;
      try
      {
        Object localObject = MessageDigest.getInstance("SHA-1");
        paramString = paramString.getBytes("UTF-8");
        ((MessageDigest)localObject).update(paramString, 0, paramString.length);
        paramString = ((MessageDigest)localObject).digest();
        localObject = new StringBuilder();
        int j = paramString.length;
        for (;;)
        {
          if (i >= j) {
            return ((StringBuilder)localObject).toString();
          }
          ((StringBuilder)localObject).append(String.format("%02X", new Object[] { Byte.valueOf(paramString[i]) }));
          i += 1;
        }
        return null;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
  }
}
