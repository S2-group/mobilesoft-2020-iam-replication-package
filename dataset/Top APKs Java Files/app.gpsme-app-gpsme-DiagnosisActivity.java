package app.gpsme;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import app.gpsme.kcgcm.RegistrationIntentService;
import app.gpsme.net.KCHttpClient;
import app.gpsme.prefs.Constants;
import app.gpsme.tools.BatteryOpt;
import app.gpsme.tools.NotifyTools;
import app.gpsme.ui.MDToast;
import com.google.android.gms.common.GoogleApiAvailability;
import com.loopj.android.http.AsyncHttpResponseHandler;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.http.Header;

public class DiagnosisActivity
  extends LockActivity
{
  private ImageView batOptSuccessIcon;
  private ImageView inetSuccessIcon;
  private ImageView kcsSuccessIcon;
  private ImageView lctnSuccessIcon;
  private Timer myTimer;
  private View rootLay;
  private Toolbar toolbar;
  private ImageView wifiSuccessIcon;
  
  public DiagnosisActivity() {}
  
  private boolean checkPlayServices()
  {
    GoogleApiAvailability localGoogleApiAvailability = GoogleApiAvailability.getInstance();
    int i = localGoogleApiAvailability.isGooglePlayServicesAvailable(this);
    if (i != 0)
    {
      if (localGoogleApiAvailability.isUserResolvableError(i)) {
        localGoogleApiAvailability.getErrorDialog(this, i, Constants.PLAY_SERVICES_RESOLUTION_REQUEST.intValue()).show();
      }
      return false;
    }
    return true;
  }
  
  private void isKcServerAvailable()
  {
    KCHttpClient.get(this, "/check_health", null, new AsyncHttpResponseHandler()
    {
      public void onFailure(int paramAnonymousInt, Header[] paramAnonymousArrayOfHeader, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
      {
        DiagnosisActivity.this.kcsSuccessIcon.setImageResource(2131558436);
      }
      
      public void onSuccess(int paramAnonymousInt, Header[] paramAnonymousArrayOfHeader, byte[] paramAnonymousArrayOfByte)
      {
        paramAnonymousArrayOfHeader = DiagnosisActivity.this.kcsSuccessIcon;
        if (paramAnonymousInt == 200) {
          paramAnonymousInt = 2131558461;
        } else {
          paramAnonymousInt = 2131558436;
        }
        paramAnonymousArrayOfHeader.setImageResource(paramAnonymousInt);
      }
    });
  }
  
  private boolean isLocationAvailable()
  {
    return ((LocationManager)getSystemService("location")).isProviderEnabled("gps");
  }
  
  private boolean isNetworkAvailable()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isAvailable()) && (localNetworkInfo.isConnected());
  }
  
  private boolean isWiFiAvailable()
  {
    return ((WifiManager)getApplicationContext().getSystemService("wifi")).isWifiEnabled();
  }
  
  public void OnClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131296446: 
      startActivity(new Intent("android.settings.WIFI_SETTINGS"));
      return;
    case 2131296445: 
      paramView = new AlertDialog.Builder(this, 2131755183);
      paramView.setTitle("Device Maintenance");
      paramView.setMessage("Please add KidControl to Unmonitored apps list. This is necessary for successful work in background");
      paramView.setPositiveButton(2131689681, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          MDToast.makeText(DiagnosisActivity.this, "Now select Battery -> Unmonitored apps", MDToast.LENGTH_LONG, 0);
          DiagnosisActivity.this.startActivity(new Intent("android.intent.action.VIEW").setPackage("com.samsung.android.lool"));
        }
      });
      paramView.show();
      return;
    case 2131296444: 
      startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
      return;
    case 2131296442: 
      if (getPackageManager().hasSystemFeature("android.hardware.telephony")) {
        paramView = new Intent("android.settings.WIRELESS_SETTINGS");
      }
      break;
    }
    try
    {
      paramView.setClassName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity");
      paramView.setFlags(268435456);
      startActivity(paramView);
      return;
      startActivity(new Intent("android.settings.WIFI_SETTINGS"));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      for (;;) {}
    }
  }
  
  public boolean isPackageExisted(String paramString)
  {
    Iterator localIterator = getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public void onBackPressed()
  {
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427359);
    this.toolbar = ((Toolbar)findViewById(2131296767));
    setSupportActionBar(this.toolbar);
    if (getSupportActionBar() != null)
    {
      getSupportActionBar().setTitle(2131689528);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setHomeButtonEnabled(true);
    }
    this.rootLay = findViewById(2131296650);
    this.inetSuccessIcon = ((ImageView)findViewById(2131296539));
    this.kcsSuccessIcon = ((ImageView)findViewById(2131296546));
    this.lctnSuccessIcon = ((ImageView)findViewById(2131296555));
    this.wifiSuccessIcon = ((ImageView)findViewById(2131296818));
    this.batOptSuccessIcon = ((ImageView)findViewById(2131296377));
    if (!isLocationAvailable()) {
      ((NotificationManager)getSystemService("notification")).cancel(NotifyTools.ntfctnIdLctnOff);
    }
    if (checkPlayServices()) {
      startService(new Intent(this, RegistrationIntentService.class));
    }
    if (Build.VERSION.SDK_INT >= 23)
    {
      if (BatteryOpt.isEnabled(this))
      {
        paramBundle = Snackbar.make(this.rootLay, getString(2131689535), -2);
        paramBundle.getView().setBackgroundColor(ContextCompat.getColor(getApplicationContext(), 2131099784));
        paramBundle.setAction(getString(2131689714), new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            BatteryOpt.setDontOptimize(DiagnosisActivity.this);
          }
        });
        paramBundle.setActionTextColor(ContextCompat.getColor(getApplicationContext(), 17170443));
        TextView localTextView = (TextView)paramBundle.getView().findViewById(2131296697);
        localTextView.setMaxLines(5);
        localTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), 17170443));
        paramBundle.show();
        findViewById(2131296441).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            BatteryOpt.setDontOptimize(DiagnosisActivity.this);
          }
        });
      }
    }
    else {
      findViewById(2131296441).setVisibility(8);
    }
    if ((Build.MANUFACTURER.equalsIgnoreCase("samsung")) && (isPackageExisted("com.samsung.android.lool"))) {
      findViewById(2131296445).setVisibility(0);
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() != 16908332) {
      return super.onOptionsItemSelected(paramMenuItem);
    }
    finish();
    return true;
  }
  
  public void onPause()
  {
    super.onPause();
    if (this.myTimer != null) {
      this.myTimer.cancel();
    }
  }
  
  public void onResume()
  {
    super.onResume();
    ImageView localImageView = this.inetSuccessIcon;
    boolean bool = isNetworkAvailable();
    int j = 2131558436;
    int i;
    if (bool) {
      i = 2131558461;
    } else {
      i = 2131558436;
    }
    localImageView.setImageResource(i);
    isKcServerAvailable();
    localImageView = this.lctnSuccessIcon;
    if (isLocationAvailable()) {
      i = 2131558461;
    } else {
      i = 2131558436;
    }
    localImageView.setImageResource(i);
    localImageView = this.wifiSuccessIcon;
    if (isWiFiAvailable()) {
      i = 2131558461;
    } else {
      i = 2131558436;
    }
    localImageView.setImageResource(i);
    if (this.myTimer != null) {
      this.myTimer.cancel();
    }
    this.myTimer = new Timer();
    this.myTimer.schedule(new TimerTask()
    {
      public void run()
      {
        DiagnosisActivity.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            ImageView localImageView = DiagnosisActivity.this.inetSuccessIcon;
            boolean bool = DiagnosisActivity.this.isNetworkAvailable();
            int j = 2131558436;
            if (bool) {
              i = 2131558461;
            } else {
              i = 2131558436;
            }
            localImageView.setImageResource(i);
            DiagnosisActivity.this.isKcServerAvailable();
            localImageView = DiagnosisActivity.this.lctnSuccessIcon;
            if (DiagnosisActivity.this.isLocationAvailable()) {
              i = 2131558461;
            } else {
              i = 2131558436;
            }
            localImageView.setImageResource(i);
            localImageView = DiagnosisActivity.this.wifiSuccessIcon;
            int i = j;
            if (DiagnosisActivity.this.isWiFiAvailable()) {
              i = 2131558461;
            }
            localImageView.setImageResource(i);
          }
        });
      }
    }, 2500L, 3000L);
    if (Build.VERSION.SDK_INT >= 23)
    {
      bool = BatteryOpt.isEnabled(this);
      localImageView = this.batOptSuccessIcon;
      i = j;
      if (!bool) {
        i = 2131558461;
      }
      localImageView.setImageResource(i);
      return;
    }
    findViewById(2131296441).setVisibility(8);
  }
}
