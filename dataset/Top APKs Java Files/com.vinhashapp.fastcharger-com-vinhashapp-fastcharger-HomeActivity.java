package com.vinhashapp.fastcharger;

import android.app.ActivityManager;
import android.app.AlertDialog.Builder;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class HomeActivity
  extends Fragment
{
  public static boolean isCharging = false;
  AdRequest adRequest;
  public BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      int i = paramAnonymousIntent.getIntExtra("level", 0);
      int j = paramAnonymousIntent.getIntExtra("plugged", 0);
      HomeActivity.this.batteryLevel = i;
      HomeActivity.this.batteryLevelSet();
      if ((j == 2) || (j == 1)) {
        HomeActivity.this.textView2.setImageResource(2131165337);
      }
      if ((j != 1) && (j != 2))
      {
        HomeActivity.this.textView2.setImageResource(2131165351);
        if (HomeActivity.this.turnOnConnectionCheck) {
          HomeActivity.this.stopCharger();
        }
        HomeActivity.this.turnOnConnectionCheck = false;
      }
      paramAnonymousContext = HomeActivity.this.tvChargerLevel;
      paramAnonymousIntent = new StringBuilder();
      paramAnonymousIntent.append(i);
      paramAnonymousIntent.append("%");
      paramAnonymousContext.setText(paramAnonymousIntent.toString());
    }
  };
  int batteryLevel;
  BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
  boolean btCheck;
  AlertDialog.Builder builder;
  int buttonCounter = 1;
  int bv = Build.VERSION.SDK_INT;
  int countClick;
  Button fast;
  RelativeLayout home_view;
  ImageView imageView;
  private View.OnClickListener image_click = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (HomeActivity.isCharging)
      {
        paramAnonymousView = HomeActivity.this;
        paramAnonymousView.countClick += 1;
        if (HomeActivity.this.countClick % 2 == 0)
        {
          HomeActivity.this.mInterstitialAd.show();
          HomeActivity.this.requestNewInterstitial();
          return;
        }
        Toast.makeText(HomeActivity.this.getActivity(), HomeActivity.this.getResources().getString(2131689588), 1).show();
        return;
      }
      HomeActivity.this.countClick = 0;
      paramAnonymousView = new Intent(HomeActivity.this.getActivity(), SettingActivity.class);
      HomeActivity.this.startActivity(paramAnonymousView);
    }
  };
  boolean isCheckFull = false;
  AdView mAdView;
  InterstitialAd mInterstitialAd;
  boolean mobDataCheck;
  private MediaPlayer mp;
  Stack<Integer> stack;
  int stackSize;
  boolean superkey;
  ImageView textView2;
  thread thread;
  boolean turnOnConnectionCheck = false;
  TextView tvChargerLevel;
  Vibrator v;
  boolean wifiCheck;
  
  public HomeActivity() {}
  
  private void requestNewInterstitial()
  {
    this.adRequest = new AdRequest.Builder().build();
    this.mInterstitialAd.loadAd(this.adRequest);
  }
  
  private void stopPlaying()
  {
    if (this.mp != null)
    {
      this.mp.stop();
      this.mp.release();
      this.mp = null;
    }
  }
  
  public void batteryLevelSet()
  {
    if (this.batteryLevel <= 10)
    {
      this.isCheckFull = false;
      this.imageView.setBackgroundResource(2131165294);
      return;
    }
    if (this.batteryLevel <= 20)
    {
      this.isCheckFull = false;
      this.imageView.setBackgroundResource(2131165295);
      return;
    }
    if (this.batteryLevel <= 30)
    {
      this.isCheckFull = false;
      this.imageView.setBackgroundResource(2131165296);
      return;
    }
    if (this.batteryLevel <= 40)
    {
      this.isCheckFull = false;
      this.imageView.setBackgroundResource(2131165297);
      return;
    }
    if (this.batteryLevel <= 50)
    {
      this.isCheckFull = false;
      this.imageView.setBackgroundResource(2131165298);
      return;
    }
    if (this.batteryLevel <= 60)
    {
      this.isCheckFull = false;
      this.imageView.setBackgroundResource(2131165299);
      return;
    }
    if (this.batteryLevel <= 70)
    {
      this.isCheckFull = false;
      this.imageView.setBackgroundResource(2131165300);
      return;
    }
    if (this.batteryLevel <= 85)
    {
      this.isCheckFull = false;
      this.imageView.setBackgroundResource(2131165301);
      return;
    }
    if (this.batteryLevel <= 95)
    {
      this.isCheckFull = false;
      this.imageView.setBackgroundResource(2131165301);
      return;
    }
    if (this.batteryLevel == 100)
    {
      this.isCheckFull = true;
      this.imageView.setBackgroundResource(2131165302);
    }
  }
  
  public void btDisable()
  {
    if (this.bluetoothAdapter.isEnabled())
    {
      this.bluetoothAdapter.disable();
      return;
    }
    this.bluetoothAdapter.disable();
  }
  
  public void btEnable()
  {
    if (this.bluetoothAdapter.isEnabled())
    {
      this.bluetoothAdapter.enable();
      return;
    }
    this.bluetoothAdapter.enable();
  }
  
  public void btn_charger()
  {
    if (this.isCheckFull)
    {
      Toast.makeText(getActivity(), getResources().getString(2131689519), 1).show();
      return;
    }
    this.imageView.setEnabled(true);
    this.buttonCounter += 1;
    if (this.buttonCounter % 2 == 0)
    {
      this.thread = new thread();
      checkBatteryState();
      return;
    }
    stopCharger();
  }
  
  public void checkAirplane()
  {
    if (StoreInformation.newInstance(getActivity()).getAirplane()) {}
    try
    {
      Intent localIntent = new Intent("android.intent.action.AIRPLANE_MODE");
      localIntent.putExtra("state", false);
      getActivity().sendBroadcast(localIntent);
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    Toast.makeText(getActivity(), getResources().getString(2131689521), 1).show();
  }
  
  public void checkBatteryState()
  {
    this.turnOnConnectionCheck = true;
    try
    {
      i = getActivity().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("status", -1);
    }
    catch (NullPointerException localNullPointerException)
    {
      int i;
      for (;;) {}
    }
    i = 1;
    switch (i)
    {
    default: 
      this.buttonCounter -= 1;
      return;
    case 5: 
      checkNetworkStatus();
      this.fast.setText(2131689578);
      Toast.makeText(getActivity(), getResources().getString(2131689582), 1).show();
      this.thread.run();
      this.superkey = true;
      wifi();
      btDisable();
      killing();
      turnOnDataConnection(false, getActivity());
      checkAirplane();
      isCharging = true;
      return;
    case 4: 
      checkNetworkStatus();
      this.fast.setText(2131689578);
      Toast.makeText(getActivity(), getResources().getString(2131689582), 1).show();
      this.thread.run();
      this.superkey = true;
      wifi();
      btDisable();
      killing();
      turnOnDataConnection(false, getActivity());
      checkAirplane();
      isCharging = true;
      return;
    case 3: 
      this.buttonCounter -= 1;
      Toast.makeText(getActivity(), getResources().getString(2131689562), 1).show();
      isCharging = false;
      return;
    }
    checkNetworkStatus();
    this.fast.setText(2131689578);
    Toast.makeText(getActivity(), getResources().getString(2131689582), 1).show();
    this.thread.run();
    this.superkey = true;
    wifi();
    btDisable();
    killing();
    checkAirplane();
    turnOnDataConnection(false, getActivity());
    isCharging = true;
  }
  
  public void checkNetworkStatus()
  {
    try
    {
      ConnectivityManager localConnectivityManager = (ConnectivityManager)getActivity().getSystemService("connectivity");
      if (localConnectivityManager.getActiveNetworkInfo().isConnected()) {
        this.wifiCheck = true;
      }
      wifi();
      Thread.sleep(1000L);
      if (localConnectivityManager.getActiveNetworkInfo().isConnectedOrConnecting()) {
        this.mobDataCheck = true;
      }
      if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
        this.btCheck = true;
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  public void killing()
  {
    ActivityManager localActivityManager = (ActivityManager)getActivity().getSystemService("activity");
    Iterator localIterator = getActivity().getPackageManager().getInstalledApplications(0).iterator();
    for (;;)
    {
      ApplicationInfo localApplicationInfo;
      if (localIterator.hasNext())
      {
        localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (((localApplicationInfo.flags & 0x1) == 1) || (localApplicationInfo.packageName.equals("mypackage"))) {}
      }
      else
      {
        try
        {
          localActivityManager.killBackgroundProcesses(localApplicationInfo.packageName);
        }
        catch (NullPointerException localNullPointerException) {}
        return;
      }
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427376, paramViewGroup, false);
    this.textView2 = ((ImageView)paramLayoutInflater.findViewById(2131230955));
    this.tvChargerLevel = ((TextView)paramLayoutInflater.findViewById(2131230774));
    this.fast = ((Button)paramLayoutInflater.findViewById(2131230808));
    this.fast.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        HomeActivity.this.btn_charger();
      }
    });
    this.home_view = ((RelativeLayout)paramLayoutInflater.findViewById(2131230982));
    this.imageView = ((ImageView)paramLayoutInflater.findViewById(2131230829));
    this.imageView.setOnClickListener(this.image_click);
    this.stack = new Stack();
    this.stack.add(Integer.valueOf(2131165294));
    this.stack.add(Integer.valueOf(2131165295));
    this.stack.add(Integer.valueOf(2131165296));
    this.stack.add(Integer.valueOf(2131165297));
    this.stack.add(Integer.valueOf(2131165298));
    this.stack.add(Integer.valueOf(2131165299));
    this.stack.add(Integer.valueOf(2131165300));
    this.stack.add(Integer.valueOf(2131165301));
    this.stack.add(Integer.valueOf(2131165302));
    this.stackSize = this.stack.size();
    getActivity().registerReceiver(this.batteryInfoReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    this.mAdView = ((AdView)paramLayoutInflater.findViewById(2131230746));
    paramViewGroup = new AdRequest.Builder().build();
    this.mAdView.loadAd(paramViewGroup);
    isCharging = false;
    this.mInterstitialAd = new InterstitialAd(getActivity());
    this.mInterstitialAd.setAdUnitId("ca-app-pub-6684374725807060/1581486631");
    requestNewInterstitial();
    return paramLayoutInflater;
  }
  
  public void onDestroy()
  {
    getActivity().unregisterReceiver(this.batteryInfoReceiver);
    if (this.mAdView != null) {
      this.mAdView.destroy();
    }
    super.onDestroy();
  }
  
  public void onResume()
  {
    Log.i("DEBUG ", "resume");
    if (this.mAdView != null) {
      this.mAdView.resume();
    }
    super.onResume();
  }
  
  public void onStart()
  {
    super.onStart();
  }
  
  public void playMedia()
  {
    this.mp = MediaPlayer.create(getActivity(), 2131623936);
    float f = (float)(Math.log(100 - StoreInformation.newInstance(getActivity()).getVolume()) / Math.log(100.0D));
    MediaPlayer localMediaPlayer = this.mp;
    f = 1.0F - f;
    localMediaPlayer.setVolume(f, f);
    this.mp.start();
  }
  
  public void showDialog()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      this.builder = new AlertDialog.Builder(getContext(), 16974374);
    } else {
      this.builder = new AlertDialog.Builder(getContext());
    }
    this.builder.setMessage(2131689519).setPositiveButton(17039379, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        HomeActivity.this.stopPlaying();
      }
    }).setCancelable(false).show();
  }
  
  public void stopCharger()
  {
    isCharging = false;
    Toast.makeText(getActivity(), getResources().getString(2131689566), 1).show();
    this.superkey = false;
    this.fast.setText(2131689576);
    this.thread.interrupt();
    this.thread.killThread();
    turnOnAllFunc();
    batteryLevelSet();
  }
  
  public void stopVibrate()
  {
    if (this.v != null) {
      this.v.cancel();
    }
  }
  
  public void turnOnAllFunc()
  {
    if (this.wifiCheck) {
      wifiEnable();
    }
    if (this.btCheck) {
      btEnable();
    }
    if (this.mobDataCheck) {
      turnOnDataConnection(true, getActivity());
    }
  }
  
  boolean turnOnDataConnection(boolean paramBoolean, Context paramContext)
  {
    try
    {
      if (this.bv == 8)
      {
        paramContext = (TelephonyManager)getActivity().getSystemService("phone");
        localObject = Class.forName(paramContext.getClass().getName()).getDeclaredMethod("getITelephony", new Class[0]);
        ((Method)localObject).setAccessible(true);
        localObject = ((Method)localObject).invoke(paramContext, new Object[0]);
        paramContext = Class.forName(localObject.getClass().getName());
        if (paramBoolean) {
          paramContext = paramContext.getDeclaredMethod("enableDataConnectivity", new Class[0]);
        } else {
          paramContext = paramContext.getDeclaredMethod("disableDataConnectivity", new Class[0]);
        }
        paramContext.setAccessible(true);
        paramContext.invoke(localObject, new Object[0]);
        return true;
      }
      paramContext = (ConnectivityManager)getActivity().getSystemService("connectivity");
      Object localObject = Class.forName(paramContext.getClass().getName()).getDeclaredField("mService");
      ((Field)localObject).setAccessible(true);
      paramContext = ((Field)localObject).get(paramContext);
      localObject = Class.forName(paramContext.getClass().getName()).getDeclaredMethod("setMobileDataEnabled", new Class[] { Boolean.TYPE });
      ((Method)localObject).setAccessible(true);
      ((Method)localObject).invoke(paramContext, new Object[] { Boolean.valueOf(paramBoolean) });
      return true;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public void wifi()
  {
    ((WifiManager)getContext().getApplicationContext().getSystemService("wifi")).setWifiEnabled(false);
  }
  
  public void wifiEnable()
  {
    ((WifiManager)getActivity().getApplicationContext().getSystemService("wifi")).setWifiEnabled(true);
  }
  
  public class thread
    extends Thread
  {
    int currentIndex = 0;
    boolean running = true;
    int updateInterval = 250;
    
    public thread() {}
    
    public void killThread()
    {
      this.running = false;
    }
    
    public void run()
    {
      if (this.running)
      {
        this.currentIndex += 1;
        if (this.currentIndex == HomeActivity.this.stackSize) {
          this.currentIndex = 0;
        }
        HomeActivity.this.imageView.setBackgroundResource(((Integer)HomeActivity.this.stack.get(this.currentIndex)).intValue());
        HomeActivity.this.imageView.postDelayed(this, this.updateInterval);
        if (HomeActivity.this.batteryLevel == 100)
        {
          if (StoreInformation.newInstance(HomeActivity.this.getActivity()).getAlert().equals("on")) {
            HomeActivity.this.playMedia();
          }
          if (StoreInformation.newInstance(HomeActivity.this.getActivity()).getVibrate())
          {
            HomeActivity.this.v = ((Vibrator)HomeActivity.this.getActivity().getSystemService("vibrator"));
            HomeActivity.this.v.vibrate(new long[] { 0L, 100L, 200L, 300L, 400L }, 0);
          }
          HomeActivity.this.showDialog();
          HomeActivity.this.stopCharger();
          HomeActivity.this.stopVibrate();
        }
      }
    }
  }
}
