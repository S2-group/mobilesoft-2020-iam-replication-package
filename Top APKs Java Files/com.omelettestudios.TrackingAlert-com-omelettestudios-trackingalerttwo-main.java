package com.omelettestudios.trackingalerttwo;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Notification.Builder;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class main
  extends AppCompatActivity
  implements ActivityCompat.OnRequestPermissionsResultCallback
{
  TextView actionText;
  ImageView alertView;
  Animation animation = new AlphaAnimation(1.0F, 0.0F);
  Animation animation2 = new AlphaAnimation(1.0F, 0.0F);
  Animation animationSwipeLeft;
  Animation animationSwipeRight;
  ArrayAdapter<String> appsListAdapter;
  ListView appsListView;
  final ArrayList<String> arrayListApps = new ArrayList();
  ArrayList<Drawable> arrayListDrawable = new ArrayList();
  final ArrayList<String> arrayListPackageName = new ArrayList();
  ViewFlipper bottomFlipper;
  Button clearIgnoreListButton;
  Button clearTrackingListButton;
  TextView culprit;
  custom_list_view customListViewClass;
  Button dismissButton;
  Button forceCloseButton;
  String foregroundPackageName;
  ArrayList<String> ignoreList = new ArrayList();
  Drawable ignoreListBackground;
  ArrayList<Drawable> ignoreListDrawable = new ArrayList();
  ArrayList<String> ignoreListName = new ArrayList();
  TextView ignoreListPointer;
  ListView ignoreListView;
  Drawable[] imageArray;
  ImageView imgListItem;
  LayoutInflater inflater;
  PackageInfo info2;
  Button infoButton;
  TextView infoText;
  boolean isTracking = false;
  long lastFixTimeMillis = 0L;
  View listViewLayout;
  Location location;
  boolean locationFound = false;
  LocationManager locationManager;
  Boolean locationPermissionsButtonClick = Boolean.valueOf(false);
  private AdView mAdView;
  private InterstitialAd mInterstitialAd;
  private View mLayout;
  View mainRelativeView;
  boolean monitorGPS = true;
  int permission = 0;
  boolean permissionError;
  int permissionGranted = 0;
  ListView permissionsList;
  TextView permissionsText;
  TableRow rowView;
  String[] runningAppArray;
  String[] runningAppsList;
  Boolean showNotifications = Boolean.valueOf(true);
  TextView swipeInstructionsLeft;
  TextView swipeInstructionsRight;
  Drawable swipeLeft;
  Drawable swipeRight;
  TextView textView1;
  int timesOpened;
  Boolean trackingActivityButtonClick = Boolean.valueOf(false);
  TextView trackingInfoPointer;
  Boolean trackingInfoVisible = Boolean.valueOf(true);
  int trackingInstances;
  TextView trackingNumber;
  TextView trackingNumberText;
  TextView trackingTime;
  long trackingTimerStart;
  long trackingTimerStop;
  TextView trackingTimerText;
  long trackingTimerTotal;
  TextView txtListItem;
  Boolean wasAddedToIgnoreList = Boolean.valueOf(false);
  
  public main() {}
  
  private void allSystemsGo()
  {
    this.permission = 1;
    SharedPreferences.Editor localEditor = getApplicationContext().getSharedPreferences("MyPref", 0).edit();
    localEditor.putInt("permission", this.permission);
    localEditor.commit();
    recreate();
  }
  
  private boolean checkPackage2(PackageInfo paramPackageInfo)
  {
    return (paramPackageInfo.applicationInfo.flags & 0x1) != 0;
  }
  
  private boolean checkPersistentPackage(PackageInfo paramPackageInfo)
  {
    return (paramPackageInfo.applicationInfo.flags & 0x200000) != 0;
  }
  
  private boolean checkSystemPackage(PackageInfo paramPackageInfo)
  {
    return (paramPackageInfo.applicationInfo.flags & 0x1000000) != 0;
  }
  
  private void permissionDialog()
  {
    ActivityCompat.requestPermissions(this, new String[] { "android.permission.ACCESS_FINE_LOCATION" }, 1);
  }
  
  public void alertDialog(final String paramString1, final String paramString2)
  {
    new AlertDialog.Builder(this).setTitle(paramString1 + " options").setMessage("Ignore tracking alerts when using " + paramString1 + "?").setPositiveButton(17039379, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        main.this.ignoreList.add(paramString2);
        paramAnonymousDialogInterface = new String[main.this.ignoreList.size()];
        main.this.ignoreList.toArray(paramAnonymousDialogInterface);
        Drawable[] arrayOfDrawable = new Drawable[main.this.arrayListDrawable.size()];
        main.this.arrayListDrawable.toArray(arrayOfDrawable);
        try
        {
          paramAnonymousDialogInterface = new custom_list_view(main.this, paramAnonymousDialogInterface, arrayOfDrawable);
          main.this.ignoreListView.setAdapter(paramAnonymousDialogInterface);
          Toast.makeText(main.this.getApplicationContext(), "Tracking will be ignored for " + paramString1, 1).show();
          main.this.wasAddedToIgnoreList = Boolean.valueOf(true);
          return;
        }
        catch (NullPointerException paramAnonymousDialogInterface)
        {
          for (;;) {}
        }
      }
    }).setNegativeButton(17039369, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).setIcon(17301543).show();
  }
  
  public void clearIgnoreListButton(View paramView)
  {
    this.bottomFlipper.setVisibility(8);
    this.clearIgnoreListButton.setVisibility(4);
    this.infoButton.setVisibility(0);
    this.permissionsText.setVisibility(0);
    this.locationPermissionsButtonClick = Boolean.valueOf(true);
    this.mInterstitialAd.loadAd(new AdRequest.Builder().build());
    paramView = getPackageManager();
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    Iterator localIterator = paramView.getInstalledPackages(4096).iterator();
    PackageInfo localPackageInfo;
    String[] arrayOfString;
    int i;
    int j;
    while (localIterator.hasNext())
    {
      localPackageInfo = (PackageInfo)localIterator.next();
      arrayOfString = localPackageInfo.requestedPermissions;
      if (arrayOfString != null)
      {
        i = 0;
        j = arrayOfString.length;
      }
    }
    while (i < j)
    {
      if (arrayOfString[i].equals("android.permission.ACCESS_FINE_LOCATION")) {}
      try
      {
        String str = (String)paramView.getApplicationLabel(paramView.getApplicationInfo(localPackageInfo.packageName, 128));
        Drawable localDrawable = paramView.getApplicationIcon(localPackageInfo.packageName);
        localArrayList1.add(str);
        localArrayList2.add(localDrawable);
        i += 1;
        continue;
        this.permissionsList.setVisibility(0);
        paramView = new CustomAdapter(this, localArrayList1, localArrayList2);
        this.permissionsList.setAdapter(paramView);
        return;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;) {}
      }
    }
  }
  
  public void clearTrackingListButton(View paramView)
  {
    this.trackingActivityButtonClick = Boolean.valueOf(true);
    this.mInterstitialAd.loadAd(new AdRequest.Builder().build());
    startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 0);
  }
  
  public void forceCloseApps(String paramString1, String paramString2)
  {
    ((ActivityManager)getSystemService("activity")).killBackgroundProcesses(paramString1);
    Toast.makeText(getApplicationContext(), paramString2 + " has been closed", 1).show();
  }
  
  public void getForegroundApp()
  {
    this.foregroundPackageName = ((ActivityManager.RunningTaskInfo)((ActivityManager)getSystemService("activity")).getRunningTasks(Integer.MAX_VALUE).get(0)).topActivity.getPackageName();
  }
  
  public boolean isOnIgnoreList(String paramString1, String paramString2)
  {
    if ((!this.ignoreList.isEmpty()) && (this.ignoreList.contains(paramString1))) {}
    while ((!this.ignoreListName.isEmpty()) && (this.ignoreListName.contains(paramString2))) {
      return true;
    }
    return false;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903067);
    this.mLayout = findViewById(2131493011);
    paramBundle = getApplicationContext().getSharedPreferences("MyPref", 0);
    SharedPreferences.Editor localEditor = paramBundle.edit();
    this.trackingInstances = paramBundle.getInt("trackingCount", 0);
    this.trackingTimerTotal = paramBundle.getLong("trackingTime", 0L);
    this.timesOpened = paramBundle.getInt("timesOpened", 0);
    this.permission = paramBundle.getInt("permission", 0);
    this.timesOpened += 1;
    localEditor.putInt("timesOpened", this.timesOpened);
    MobileAds.initialize(this, "ca-app-pub-6814173623679980~8575043841");
    this.mAdView = ((AdView)findViewById(2131493032));
    paramBundle = new AdRequest.Builder().build();
    this.mAdView.loadAd(paramBundle);
    this.mInterstitialAd = new InterstitialAd(this);
    this.mInterstitialAd.setAdUnitId("ca-app-pub-6814173623679980/1674485775");
    this.mInterstitialAd.loadAd(new AdRequest.Builder().build());
    if (this.permission == 0) {
      permissionDialog();
    }
    this.mInterstitialAd.setAdListener(new AdListener()
    {
      public void onAdClosed()
      {
        main.this.mInterstitialAd.loadAd(new AdRequest.Builder().build());
      }
    });
    this.mainRelativeView = findViewById(2131493011);
    this.clearTrackingListButton = ((Button)findViewById(2131493027));
    this.clearIgnoreListButton = ((Button)findViewById(2131493028));
    this.textView1 = ((TextView)findViewById(2131493016));
    this.trackingTimerText = ((TextView)findViewById(2131493021));
    this.culprit = ((TextView)findViewById(2131493014));
    this.forceCloseButton = ((Button)findViewById(2131493028));
    this.dismissButton = ((Button)findViewById(2131493027));
    this.actionText = ((TextView)findViewById(2131493023));
    this.appsListView = ((ListView)findViewById(2131493015));
    this.swipeRight = getResources().getDrawable(2130837631);
    this.swipeLeft = getResources().getDrawable(2130837630);
    this.infoText = ((TextView)findViewById(2131493013));
    this.trackingNumberText = ((TextView)findViewById(2131493019));
    this.trackingNumber = ((TextView)findViewById(2131493020));
    this.trackingTime = ((TextView)findViewById(2131493022));
    this.customListViewClass = new custom_list_view(this, new String[0], new Drawable[0]);
    this.inflater = getLayoutInflater();
    this.listViewLayout = this.inflater.inflate(2130903083, this.appsListView, false);
    this.imgListItem = ((ImageView)this.listViewLayout.findViewById(2131493047));
    this.txtListItem = ((TextView)this.listViewLayout.findViewById(2131493048));
    this.rowView = ((TableRow)this.listViewLayout.findViewById(2131493048).getParent());
    this.rowView.setOnTouchListener(new MyTouchListener(null));
    this.imgListItem.setOnTouchListener(new MyTouchListener(null));
    this.txtListItem.setOnTouchListener(new MyTouchListener(null));
    this.appsListView.setOnTouchListener(new MyTouchListener(null));
    this.ignoreListBackground = getResources().getDrawable(2130837618);
    this.ignoreListView = ((ListView)findViewById(2131493026));
    this.alertView = ((ImageView)findViewById(2131493012));
    this.permissionsList = ((ListView)findViewById(2131493031));
    this.permissionsList.setVisibility(8);
    this.infoButton = ((Button)findViewById(2131493029));
    this.permissionsText = ((TextView)findViewById(2131493030));
    if (this.trackingTimerTotal == 1L) {
      this.trackingTime.setText(Long.toString(this.trackingTimerTotal / 1000L / 60L) + " minute");
    }
    for (;;)
    {
      int i;
      if (this.trackingInstances == 1)
      {
        this.trackingNumber.setText(Integer.toString(this.trackingInstances) + " time");
        this.bottomFlipper = ((ViewFlipper)findViewById(2131493017));
        this.animation.setDuration(1000L);
        this.animation.setInterpolator(new LinearInterpolator());
        this.animation.setRepeatCount(-1);
        this.animation.setRepeatMode(2);
        this.animation2.setDuration(500L);
        this.animation2.setInterpolator(new LinearInterpolator());
        this.animation2.setRepeatCount(-1);
        this.animation2.setRepeatMode(2);
        if (this.ignoreList.size() > 0) {}
        this.animationSwipeRight = AnimationUtils.loadAnimation(getApplicationContext(), 2130968593);
        this.animationSwipeLeft = AnimationUtils.loadAnimation(getApplicationContext(), 2130968592);
        this.trackingTimerText.setVisibility(0);
        if (this.permission == 1)
        {
          i = 0;
          label951:
          if (i >= 5) {}
        }
      }
      else
      {
        try
        {
          this.locationManager = ((LocationManager)getSystemService("location"));
          new Timer().schedule(new TimerTask()
          {
            public void run()
            {
              main.this.permissionError = false;
              main.this.runOnUiThread(new Runnable()
              {
                public void run()
                {
                  Object localObject = LocationServices.getFusedLocationProviderClient(main.this);
                  try
                  {
                    ((FusedLocationProviderClient)localObject).getLastLocation().addOnSuccessListener(main.this, new OnSuccessListener()
                    {
                      public void onSuccess(Location paramAnonymous3Location)
                      {
                        if (paramAnonymous3Location != null) {
                          main.this.lastFixTimeMillis = paramAnonymous3Location.getTime();
                        }
                      }
                    });
                  }
                  catch (SecurityException localSecurityException)
                  {
                    try
                    {
                      for (;;)
                      {
                        main.this.locationFound = true;
                        if ((main.this.locationFound != true) || (System.currentTimeMillis() - main.this.lastFixTimeMillis >= 10000L)) {
                          break label740;
                        }
                        main.this.textView1.setTextSize(28.0F);
                        main.this.textView1.setText("GPS Tracking Active");
                        main.this.infoText.setVisibility(0);
                        main.this.clearIgnoreListButton.setVisibility(0);
                        main.this.clearTrackingListButton.setVisibility(0);
                        main.this.textView1.startAnimation(main.this.animation);
                        if (!main.this.isTracking)
                        {
                          main.this.getForegroundApp();
                          main.this.trackingTimerStart = System.currentTimeMillis();
                          localObject = main.this;
                          ((main)localObject).trackingInstances += 1;
                          main.this.trackingNumberText.setText("Tracking count");
                          if (main.this.trackingInstances != 1) {
                            break;
                          }
                          main.this.trackingNumber.setText(Integer.toString(main.this.trackingInstances) + " time");
                          main.this.trackingTimerText.setText("Total time tracked");
                          if (main.this.trackingTimerTotal != 1L) {
                            break label684;
                          }
                          main.this.trackingTime.setText(Long.toString(main.this.trackingTimerTotal / 1000L / 60L) + " minute");
                          if (!main.this.isOnIgnoreList(main.this.foregroundPackageName, null))
                          {
                            if (main.this.showNotifications.booleanValue() == true) {
                              main.this.triggerNotification("Your location is being tracked");
                            }
                            main.this.getForegroundApp();
                            main.this.tasksRunning("");
                            main.this.actionText.setVisibility(0);
                            main.this.forceCloseButton.setVisibility(0);
                            main.this.dismissButton.setVisibility(0);
                          }
                        }
                        main.this.isTracking = true;
                        localObject = main.this.getApplicationContext().getSharedPreferences("MyPref", 0).edit();
                        ((SharedPreferences.Editor)localObject).putInt("trackingCount", main.this.trackingInstances);
                        ((SharedPreferences.Editor)localObject).putLong("trackingTime", main.this.trackingTimerTotal);
                        ((SharedPreferences.Editor)localObject).apply();
                        return;
                        localSecurityException = localSecurityException;
                        main.this.permissionError = true;
                        main.this.onPermissionsError();
                      }
                    }
                    catch (NullPointerException localNullPointerException)
                    {
                      for (;;)
                      {
                        main.this.textView1.setText("GPS is turned off");
                        main.this.alertView.clearAnimation();
                        continue;
                        main.this.trackingNumber.setText(Integer.toString(main.this.trackingInstances) + " times");
                        continue;
                        label684:
                        main.this.trackingTime.setText(Long.toString(main.this.trackingTimerTotal / 1000L / 60L) + " minutes");
                        continue;
                        label740:
                        if ((main.this.locationFound == true) && (System.currentTimeMillis() - main.this.lastFixTimeMillis > 10000L))
                        {
                          main.this.textView1.setTextSize(22.0F);
                          main.this.textView1.setText("Monitoring tracking activity...");
                          main.this.alertView.clearAnimation();
                          main.this.alertView.setAnimation(null);
                          if (main.this.isTracking == true)
                          {
                            main.this.trackingTimerStop = System.currentTimeMillis();
                            main.this.trackingTimerTotal += main.this.trackingTimerStop - main.this.trackingTimerStart;
                            main.this.trackingTimerStart = 0L;
                            main.this.trackingTimerStop = 0L;
                          }
                          main.this.isTracking = false;
                        }
                      }
                    }
                  }
                }
              });
            }
          }, 0L, 5000L);
          i += 1;
          break label951;
          this.trackingTime.setText(Long.toString(this.trackingTimerTotal / 1000L / 60L) + " minutes");
          continue;
          this.trackingNumber.setText(Integer.toString(this.trackingInstances) + " times");
        }
        catch (Exception paramBundle)
        {
          for (;;)
          {
            onPermissionsError();
          }
        }
      }
    }
    if ((!this.permissionError) && (!new alert_service().isServiceRunning)) {
      startService(new Intent(this, alert_service.class));
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131558400, paramMenu);
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    int i = paramMenuItem.getItemId();
    if (i == 2131493071) {}
    while (i == 2131493070) {
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  public void onPermissionsError()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage("In order to detect recent access to your device's location, location permissions must be enabled in settings.");
    localBuilder.setTitle("Location Permissions");
    localBuilder.setPositiveButton("SETTINGS", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = new Intent();
        paramAnonymousDialogInterface.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        paramAnonymousDialogInterface.setData(Uri.fromParts("package", main.this.getPackageName(), null));
        main.this.startActivity(paramAnonymousDialogInterface);
      }
    });
    localBuilder.setCancelable(true);
    localBuilder.create().show();
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    switch (paramInt)
    {
    default: 
      return;
    }
    if ((paramArrayOfInt.length > 0) && (paramArrayOfInt[0] == 0))
    {
      allSystemsGo();
      return;
    }
    ((TextView)findViewById(2131493016)).setText("Please enable Location permissions for alert functionality");
  }
  
  public void onResume()
  {
    super.onResume();
    if ((this.trackingActivityButtonClick.booleanValue()) || (this.locationPermissionsButtonClick.booleanValue()))
    {
      if (this.mInterstitialAd.isLoaded()) {
        this.mInterstitialAd.show();
      }
      this.trackingActivityButtonClick = Boolean.valueOf(false);
      this.locationPermissionsButtonClick = Boolean.valueOf(false);
    }
  }
  
  public void returnToInfoButton(View paramView)
  {
    this.bottomFlipper.setVisibility(0);
    this.permissionsList.setVisibility(8);
    this.infoButton.setVisibility(8);
    this.permissionsText.setVisibility(4);
  }
  
  /* Error */
  public void tasksRunning(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 151	com/omelettestudios/trackingalerttwo/main:arrayListApps	Ljava/util/ArrayList;
    //   4: invokevirtual 815	java/util/ArrayList:clear	()V
    //   7: aload_0
    //   8: getfield 155	com/omelettestudios/trackingalerttwo/main:arrayListDrawable	Ljava/util/ArrayList;
    //   11: invokevirtual 815	java/util/ArrayList:clear	()V
    //   14: aload_0
    //   15: getfield 153	com/omelettestudios/trackingalerttwo/main:arrayListPackageName	Ljava/util/ArrayList;
    //   18: invokevirtual 815	java/util/ArrayList:clear	()V
    //   21: aconst_null
    //   22: astore 6
    //   24: aload_0
    //   25: ldc_w 419
    //   28: invokevirtual 423	com/omelettestudios/trackingalerttwo/main:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   31: checkcast 425	android/app/ActivityManager
    //   34: ldc_w 440
    //   37: invokevirtual 443	android/app/ActivityManager:getRunningTasks	(I)Ljava/util/List;
    //   40: astore 10
    //   42: aload_0
    //   43: aload 10
    //   45: invokeinterface 816 1 0
    //   50: anewarray 242	java/lang/String
    //   53: putfield 818	com/omelettestudios/trackingalerttwo/main:runningAppArray	[Ljava/lang/String;
    //   56: iconst_0
    //   57: istore_2
    //   58: iload_2
    //   59: aload 10
    //   61: invokeinterface 816 1 0
    //   66: if_icmpge +640 -> 706
    //   69: aload 10
    //   71: iload_2
    //   72: invokeinterface 447 2 0
    //   77: checkcast 449	android/app/ActivityManager$RunningTaskInfo
    //   80: getfield 453	android/app/ActivityManager$RunningTaskInfo:topActivity	Landroid/content/ComponentName;
    //   83: astore 8
    //   85: aload 10
    //   87: iload_2
    //   88: invokeinterface 447 2 0
    //   93: checkcast 449	android/app/ActivityManager$RunningTaskInfo
    //   96: getfield 821	android/app/ActivityManager$RunningTaskInfo:baseActivity	Landroid/content/ComponentName;
    //   99: invokevirtual 824	android/content/ComponentName:toShortString	()Ljava/lang/String;
    //   102: astore 9
    //   104: aload 6
    //   106: astore 7
    //   108: aload_0
    //   109: invokevirtual 345	com/omelettestudios/trackingalerttwo/main:getPackageManager	()Landroid/content/pm/PackageManager;
    //   112: aload 8
    //   114: invokevirtual 458	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   117: sipush 4096
    //   120: invokevirtual 828	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   123: astore 11
    //   125: aload 6
    //   127: astore 7
    //   129: aload 9
    //   131: ldc_w 830
    //   134: invokevirtual 834	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   137: istore_3
    //   138: aload 9
    //   140: astore 8
    //   142: iconst_m1
    //   143: iload_3
    //   144: if_icmpeq +16 -> 160
    //   147: aload 6
    //   149: astore 7
    //   151: aload 9
    //   153: iconst_1
    //   154: iload_3
    //   155: invokevirtual 838	java/lang/String:substring	(II)Ljava/lang/String;
    //   158: astore 8
    //   160: aload 6
    //   162: astore 7
    //   164: aload_0
    //   165: invokevirtual 345	com/omelettestudios/trackingalerttwo/main:getPackageManager	()Landroid/content/pm/PackageManager;
    //   168: astore 9
    //   170: aload 9
    //   172: aload 8
    //   174: iconst_0
    //   175: invokevirtual 380	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   178: astore 7
    //   180: aload 7
    //   182: astore 6
    //   184: aload 6
    //   186: astore 7
    //   188: aload_0
    //   189: aload 11
    //   191: getfield 376	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   194: aconst_null
    //   195: invokevirtual 840	com/omelettestudios/trackingalerttwo/main:isOnIgnoreList	(Ljava/lang/String;Ljava/lang/String;)Z
    //   198: ifeq +146 -> 344
    //   201: aload 6
    //   203: astore 7
    //   205: aload 11
    //   207: getfield 376	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   210: aload_0
    //   211: getfield 460	com/omelettestudios/trackingalerttwo/main:foregroundPackageName	Ljava/lang/String;
    //   214: invokevirtual 373	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   217: istore 5
    //   219: iload 5
    //   221: ifeq +123 -> 344
    //   224: aload 6
    //   226: astore 8
    //   228: aload_0
    //   229: getfield 151	com/omelettestudios/trackingalerttwo/main:arrayListApps	Ljava/util/ArrayList;
    //   232: invokevirtual 700	java/util/ArrayList:size	()I
    //   235: anewarray 242	java/lang/String
    //   238: astore 6
    //   240: aload_0
    //   241: getfield 151	com/omelettestudios/trackingalerttwo/main:arrayListApps	Ljava/util/ArrayList;
    //   244: aload 6
    //   246: invokevirtual 844	java/util/ArrayList:toArray	([Ljava/lang/Object;)[Ljava/lang/Object;
    //   249: pop
    //   250: aload_0
    //   251: getfield 155	com/omelettestudios/trackingalerttwo/main:arrayListDrawable	Ljava/util/ArrayList;
    //   254: invokevirtual 700	java/util/ArrayList:size	()I
    //   257: anewarray 591	android/graphics/drawable/Drawable
    //   260: astore 7
    //   262: aload_0
    //   263: getfield 155	com/omelettestudios/trackingalerttwo/main:arrayListDrawable	Ljava/util/ArrayList;
    //   266: aload 7
    //   268: invokevirtual 844	java/util/ArrayList:toArray	([Ljava/lang/Object;)[Ljava/lang/Object;
    //   271: pop
    //   272: new 589	com/omelettestudios/trackingalerttwo/custom_list_view
    //   275: dup
    //   276: aload_0
    //   277: aload 6
    //   279: aload 7
    //   281: invokespecial 594	com/omelettestudios/trackingalerttwo/custom_list_view:<init>	(Landroid/app/Activity;[Ljava/lang/String;[Landroid/graphics/drawable/Drawable;)V
    //   284: astore 6
    //   286: aload_0
    //   287: getfield 559	com/omelettestudios/trackingalerttwo/main:appsListView	Landroid/widget/ListView;
    //   290: aload 6
    //   292: invokevirtual 405	android/widget/ListView:setAdapter	(Landroid/widget/ListAdapter;)V
    //   295: iload_2
    //   296: iconst_1
    //   297: iadd
    //   298: istore_2
    //   299: aload 8
    //   301: astore 6
    //   303: goto -245 -> 58
    //   306: astore 7
    //   308: aload 6
    //   310: astore 7
    //   312: aload_0
    //   313: getfield 549	com/omelettestudios/trackingalerttwo/main:culprit	Landroid/widget/TextView;
    //   316: ldc_w 846
    //   319: invokevirtual 667	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   322: goto -138 -> 184
    //   325: astore 6
    //   327: aload_0
    //   328: getfield 549	com/omelettestudios/trackingalerttwo/main:culprit	Landroid/widget/TextView;
    //   331: ldc_w 848
    //   334: invokevirtual 667	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   337: aload 7
    //   339: astore 8
    //   341: goto -113 -> 228
    //   344: aload 6
    //   346: astore 8
    //   348: aload 6
    //   350: astore 7
    //   352: aload 11
    //   354: getfield 376	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   357: aload_1
    //   358: invokevirtual 373	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   361: ifne -133 -> 228
    //   364: aload 6
    //   366: astore 8
    //   368: aload 6
    //   370: astore 7
    //   372: aload 11
    //   374: getfield 376	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   377: ldc_w 850
    //   380: invokevirtual 373	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   383: ifne -155 -> 228
    //   386: aload 6
    //   388: astore 8
    //   390: aload 6
    //   392: astore 7
    //   394: aload 10
    //   396: iload_2
    //   397: invokeinterface 447 2 0
    //   402: checkcast 449	android/app/ActivityManager$RunningTaskInfo
    //   405: getfield 853	android/app/ActivityManager$RunningTaskInfo:numRunning	I
    //   408: ifle -180 -> 228
    //   411: aload 6
    //   413: astore 8
    //   415: aload 6
    //   417: astore 7
    //   419: aload_0
    //   420: aload 11
    //   422: invokespecial 855	com/omelettestudios/trackingalerttwo/main:checkSystemPackage	(Landroid/content/pm/PackageInfo;)Z
    //   425: ifne -197 -> 228
    //   428: aload 6
    //   430: astore 8
    //   432: aload 6
    //   434: astore 7
    //   436: aload_0
    //   437: aload 11
    //   439: invokespecial 857	com/omelettestudios/trackingalerttwo/main:checkPersistentPackage	(Landroid/content/pm/PackageInfo;)Z
    //   442: ifne -214 -> 228
    //   445: aload 6
    //   447: astore 8
    //   449: aload 6
    //   451: astore 7
    //   453: aload 11
    //   455: getfield 369	android/content/pm/PackageInfo:requestedPermissions	[Ljava/lang/String;
    //   458: ifnull -230 -> 228
    //   461: aload 6
    //   463: astore 7
    //   465: aload 11
    //   467: getfield 369	android/content/pm/PackageInfo:requestedPermissions	[Ljava/lang/String;
    //   470: astore 12
    //   472: aload 6
    //   474: astore 7
    //   476: aload 12
    //   478: arraylength
    //   479: istore 4
    //   481: iconst_0
    //   482: istore_3
    //   483: aload 6
    //   485: astore 8
    //   487: iload_3
    //   488: iload 4
    //   490: if_icmpge -262 -> 228
    //   493: aload 12
    //   495: iload_3
    //   496: aaload
    //   497: astore 8
    //   499: aload 6
    //   501: astore 7
    //   503: aload 8
    //   505: ldc_w 859
    //   508: invokevirtual 862	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   511: ifeq +6 -> 517
    //   514: goto +198 -> 712
    //   517: aload 6
    //   519: astore 7
    //   521: aload 8
    //   523: ldc_w 864
    //   526: invokevirtual 862	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   529: ifeq +183 -> 712
    //   532: aload 6
    //   534: astore 7
    //   536: aload 11
    //   538: getfield 376	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   541: ldc_w 866
    //   544: invokevirtual 373	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   547: ifne +165 -> 712
    //   550: aload 6
    //   552: astore 7
    //   554: aload 9
    //   556: aload 6
    //   558: invokevirtual 384	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   561: ldc_w 868
    //   564: invokevirtual 871	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   567: ifne +145 -> 712
    //   570: aload 6
    //   572: astore 7
    //   574: aload 9
    //   576: aload 6
    //   578: invokevirtual 384	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   581: ldc_w 873
    //   584: invokevirtual 871	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   587: ifne +125 -> 712
    //   590: aload 6
    //   592: ifnull +106 -> 698
    //   595: aload 6
    //   597: astore 7
    //   599: aload 9
    //   601: aload 6
    //   603: invokevirtual 384	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   606: astore 8
    //   608: aload 6
    //   610: astore 7
    //   612: aload 8
    //   614: checkcast 242	java/lang/String
    //   617: checkcast 242	java/lang/String
    //   620: astore 8
    //   622: aload 6
    //   624: astore 7
    //   626: aload_0
    //   627: getfield 153	com/omelettestudios/trackingalerttwo/main:arrayListPackageName	Ljava/util/ArrayList;
    //   630: aload 11
    //   632: getfield 376	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   635: invokevirtual 391	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   638: pop
    //   639: aload 6
    //   641: astore 7
    //   643: aload_0
    //   644: getfield 151	com/omelettestudios/trackingalerttwo/main:arrayListApps	Ljava/util/ArrayList;
    //   647: aload 8
    //   649: invokevirtual 391	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   652: pop
    //   653: aload 6
    //   655: astore 7
    //   657: aload_0
    //   658: getfield 155	com/omelettestudios/trackingalerttwo/main:arrayListDrawable	Ljava/util/ArrayList;
    //   661: aload 9
    //   663: aload 6
    //   665: invokevirtual 876	android/content/pm/PackageManager:getApplicationIcon	(Landroid/content/pm/ApplicationInfo;)Landroid/graphics/drawable/Drawable;
    //   668: invokevirtual 391	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   671: pop
    //   672: goto +40 -> 712
    //   675: astore 7
    //   677: aload 6
    //   679: astore 7
    //   681: aload_0
    //   682: getfield 549	com/omelettestudios/trackingalerttwo/main:culprit	Landroid/widget/TextView;
    //   685: ldc_w 878
    //   688: invokevirtual 667	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   691: aload 6
    //   693: astore 8
    //   695: goto -467 -> 228
    //   698: ldc_w 880
    //   701: astore 8
    //   703: goto -95 -> 608
    //   706: return
    //   707: astore 6
    //   709: goto -414 -> 295
    //   712: iload_3
    //   713: iconst_1
    //   714: iadd
    //   715: istore_3
    //   716: goto -233 -> 483
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	719	0	this	main
    //   0	719	1	paramString	String
    //   57	340	2	i	int
    //   137	579	3	j	int
    //   479	12	4	k	int
    //   217	3	5	bool	boolean
    //   22	287	6	localObject1	Object
    //   325	367	6	localNameNotFoundException1	PackageManager.NameNotFoundException
    //   707	1	6	localNullPointerException1	NullPointerException
    //   106	174	7	localObject2	Object
    //   306	1	7	localNameNotFoundException2	PackageManager.NameNotFoundException
    //   310	346	7	localObject3	Object
    //   675	1	7	localNullPointerException2	NullPointerException
    //   679	1	7	localNameNotFoundException3	PackageManager.NameNotFoundException
    //   83	619	8	localObject4	Object
    //   102	560	9	localObject5	Object
    //   40	355	10	localList	List
    //   123	508	11	localPackageInfo	PackageInfo
    //   470	24	12	arrayOfString	String[]
    // Exception table:
    //   from	to	target	type
    //   170	180	306	android/content/pm/PackageManager$NameNotFoundException
    //   108	125	325	android/content/pm/PackageManager$NameNotFoundException
    //   129	138	325	android/content/pm/PackageManager$NameNotFoundException
    //   151	160	325	android/content/pm/PackageManager$NameNotFoundException
    //   164	170	325	android/content/pm/PackageManager$NameNotFoundException
    //   188	201	325	android/content/pm/PackageManager$NameNotFoundException
    //   205	219	325	android/content/pm/PackageManager$NameNotFoundException
    //   312	322	325	android/content/pm/PackageManager$NameNotFoundException
    //   352	364	325	android/content/pm/PackageManager$NameNotFoundException
    //   372	386	325	android/content/pm/PackageManager$NameNotFoundException
    //   394	411	325	android/content/pm/PackageManager$NameNotFoundException
    //   419	428	325	android/content/pm/PackageManager$NameNotFoundException
    //   436	445	325	android/content/pm/PackageManager$NameNotFoundException
    //   453	461	325	android/content/pm/PackageManager$NameNotFoundException
    //   465	472	325	android/content/pm/PackageManager$NameNotFoundException
    //   476	481	325	android/content/pm/PackageManager$NameNotFoundException
    //   503	514	325	android/content/pm/PackageManager$NameNotFoundException
    //   521	532	325	android/content/pm/PackageManager$NameNotFoundException
    //   536	550	325	android/content/pm/PackageManager$NameNotFoundException
    //   554	570	325	android/content/pm/PackageManager$NameNotFoundException
    //   574	590	325	android/content/pm/PackageManager$NameNotFoundException
    //   599	608	325	android/content/pm/PackageManager$NameNotFoundException
    //   612	622	325	android/content/pm/PackageManager$NameNotFoundException
    //   626	639	325	android/content/pm/PackageManager$NameNotFoundException
    //   643	653	325	android/content/pm/PackageManager$NameNotFoundException
    //   657	672	325	android/content/pm/PackageManager$NameNotFoundException
    //   681	691	325	android/content/pm/PackageManager$NameNotFoundException
    //   188	201	675	java/lang/NullPointerException
    //   205	219	675	java/lang/NullPointerException
    //   352	364	675	java/lang/NullPointerException
    //   372	386	675	java/lang/NullPointerException
    //   394	411	675	java/lang/NullPointerException
    //   419	428	675	java/lang/NullPointerException
    //   436	445	675	java/lang/NullPointerException
    //   453	461	675	java/lang/NullPointerException
    //   465	472	675	java/lang/NullPointerException
    //   476	481	675	java/lang/NullPointerException
    //   503	514	675	java/lang/NullPointerException
    //   521	532	675	java/lang/NullPointerException
    //   536	550	675	java/lang/NullPointerException
    //   554	570	675	java/lang/NullPointerException
    //   574	590	675	java/lang/NullPointerException
    //   599	608	675	java/lang/NullPointerException
    //   612	622	675	java/lang/NullPointerException
    //   626	639	675	java/lang/NullPointerException
    //   643	653	675	java/lang/NullPointerException
    //   657	672	675	java/lang/NullPointerException
    //   272	295	707	java/lang/NullPointerException
  }
  
  public void toggleNotification(MenuItem paramMenuItem)
  {
    if (this.showNotifications.booleanValue() == true)
    {
      this.showNotifications = Boolean.valueOf(false);
      Toast.makeText(getApplicationContext(), "Notifications disabled", 1).show();
    }
    while (this.showNotifications.booleanValue()) {
      return;
    }
    this.showNotifications = Boolean.valueOf(true);
    Toast.makeText(getApplicationContext(), "Notifications enabled", 1).show();
  }
  
  public void toggleService(MenuItem paramMenuItem)
  {
    paramMenuItem = new alert_service();
    if (!paramMenuItem.isServiceRunning)
    {
      startService(new Intent(this, alert_service.class));
      Toast.makeText(getApplicationContext(), "Alert service started", 1).show();
    }
    while (paramMenuItem.isServiceRunning != true) {
      return;
    }
    stopService(new Intent(this, alert_service.class));
    Toast.makeText(getApplicationContext(), "Alert service stopped", 1).show();
  }
  
  public void triggerNotification(String paramString)
  {
    PendingIntent localPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, main.class), 0);
    NotificationManager localNotificationManager = (NotificationManager)getSystemService("notification");
    if (Build.VERSION.SDK_INT >= 26)
    {
      localNotificationManager.createNotificationChannel(new NotificationChannel("channel_01", "Tracking Alert", 3));
      localNotificationManager.notify(1, new Notification.Builder(this, "channel_01").setContentTitle(paramString).setContentText("Tracking Alert").setSmallIcon(2130837634).setAutoCancel(true).setContentIntent(localPendingIntent).build());
    }
  }
  
  private final class MyTouchListener
    implements View.OnTouchListener
  {
    private MyTouchListener() {}
    
    public boolean onTouch(final View paramView, MotionEvent paramMotionEvent)
    {
      paramView = (RelativeLayout.LayoutParams)main.this.appsListView.getLayoutParams();
      final int i = 0;
      while (i < main.this.arrayListApps.size())
      {
        main.this.animationSwipeRight.setDuration(700L);
        main.this.animationSwipeLeft.setDuration(700L);
        paramView = main.this.appsListView.getChildAt(i);
        main.this.animationSwipeRight.setAnimationListener(new Animation.AnimationListener()
        {
          public void onAnimationEnd(Animation paramAnonymousAnimation) {}
          
          public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
          
          public void onAnimationStart(Animation paramAnonymousAnimation) {}
        });
        main.this.animationSwipeLeft.setAnimationListener(new Animation.AnimationListener()
        {
          public void onAnimationEnd(Animation paramAnonymousAnimation) {}
          
          public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
          
          public void onAnimationStart(Animation paramAnonymousAnimation) {}
        });
        main.this.appsListView.getChildAt(i).setOnTouchListener(new OnSwipeListener()
        {
          TableRow.LayoutParams layoutParamsImage = (TableRow.LayoutParams)main.this.imgListItem.getLayoutParams();
          TableRow.LayoutParams layoutParamsText = (TableRow.LayoutParams)main.this.txtListItem.getLayoutParams();
          
          public void onSwipeBottom()
          {
            Toast.makeText(main.this, "bottom" + (String)main.this.arrayListPackageName.get(i), 0).show();
          }
          
          public void onSwipeLeft()
          {
            paramView.setBackground(main.this.ignoreListBackground);
            if (main.this.isOnIgnoreList((String)main.this.arrayListPackageName.get(i), null)) {
              Toast.makeText(main.this.getApplicationContext(), main.this.appsListView.getItemAtPosition(i).toString() + " is already on the ignore list.", 1).show();
            }
            for (;;)
            {
              Object localObject;
              Drawable[] arrayOfDrawable;
              if (main.this.ignoreList.size() > 0)
              {
                main.this.ignoreListPointer.setText("(" + main.this.ignoreList.size() + ")" + " Ignore List >>");
                return;
                main.this.ignoreList.add(main.this.arrayListPackageName.get(i));
                main.this.ignoreListName.add(main.this.appsListView.getItemAtPosition(i).toString());
                main.this.ignoreListDrawable.add(main.this.arrayListDrawable.get(i));
                localObject = new String[main.this.ignoreList.size()];
                main.this.ignoreListName.toArray((Object[])localObject);
                arrayOfDrawable = new Drawable[main.this.arrayListDrawable.size()];
                main.this.ignoreListDrawable.toArray(arrayOfDrawable);
              }
              try
              {
                localObject = new custom_list_view(main.this, (String[])localObject, arrayOfDrawable);
                main.this.ignoreListView.setAdapter((ListAdapter)localObject);
                Toast.makeText(main.this.getApplicationContext(), "Tracking will be ignored when " + main.this.appsListView.getItemAtPosition(i).toString() + " is in use.", 1).show();
                continue;
                main.this.ignoreListPointer.setText("Ignore List >>");
                return;
              }
              catch (NullPointerException localNullPointerException)
              {
                for (;;) {}
              }
            }
          }
          
          public void onSwipeRight()
          {
            paramView.startAnimation(main.this.animationSwipeRight);
            main.this.forceCloseApps((String)main.this.arrayListPackageName.get(i), main.this.appsListView.getItemAtPosition(i).toString());
            main.this.tasksRunning((String)main.this.arrayListPackageName.get(i));
          }
          
          public void onSwipeTop()
          {
            Toast.makeText(main.this, "top" + (String)main.this.arrayListPackageName.get(i), 0).show();
          }
        });
        i += 1;
      }
      return false;
    }
  }
}
