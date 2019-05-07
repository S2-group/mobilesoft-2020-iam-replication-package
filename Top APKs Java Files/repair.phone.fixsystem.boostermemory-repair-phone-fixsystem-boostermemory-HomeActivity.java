package repair.phone.fixsystem.boostermemory;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDataObserver.Stub;
import android.content.pm.IPackageStatsObserver.Stub;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageStats;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity
  extends AppCompatActivity
{
  private Timer A;
  private int B = 0;
  public BroadcastReceiver BatteryInformation = new BroadcastReceiver()
  {
    @SuppressLint({"SetTextI18n"})
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      paramAnonymousContext = "Non Unplugged";
      int i = paramAnonymousIntent.getIntExtra("level", 0);
      TextView localTextView = HomeActivity.this.TextBatteryPercentage;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(HomeActivity.this.getString(2131492985));
      localStringBuilder.append(" : ");
      localStringBuilder.append(i);
      localStringBuilder.append("%");
      localTextView.setText(localStringBuilder.toString());
      HomeActivity.this.BatteryPercentage.setProgress(i);
      i = paramAnonymousIntent.getIntExtra("plugged", -1);
      if (i == 2) {
        paramAnonymousContext = "USB";
      }
      if (i == 1) {
        paramAnonymousContext = "AC Adapter";
      }
      if (i == 4) {
        paramAnonymousContext = "Wireless";
      }
      HomeActivity.this.Show_Type_Charge.setText(paramAnonymousContext);
    }
  };
  private ProgressBar BatteryPercentage;
  private int C = 1;
  private boolean D = false;
  private Button OptimizePhone;
  private Button PhoneInformation;
  private ImageView PictureBoot;
  private ImageView PictureFiles;
  private ImageView PictureSystem;
  private Button RepairSystem;
  private TextView Show_Type_Charge;
  private TextView TextBatteryPercentage;
  @SuppressLint({"HandlerLeak"})
  private Handler handler = new Handler()
  {
    @SuppressLint({"SetTextI18n"})
    public void handleMessage(Message paramAnonymousMessage)
    {
      String str = paramAnonymousMessage.getData().getString(HomeActivity.this.getString(2131492994));
      TextView localTextView = new TextView(HomeActivity.this);
      localTextView.setTextColor(Color.parseColor("#ffffff"));
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(HomeActivity.this.getString(2131493010));
      localStringBuilder.append(" : ");
      localStringBuilder.append(str);
      localTextView.setText(localStringBuilder.toString());
      HomeActivity.this.scanner_result.addView(localTextView, 0);
      if (paramAnonymousMessage.what != 0) {
        return;
      }
      HomeActivity.this.googleInterstitial();
      HomeActivity.this.JHome();
    }
  };
  private InterstitialAd interstitialAds;
  @SuppressLint({"SimpleDateFormat"})
  private SimpleDateFormat m = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
  private PackageManager packageManager;
  private ProgressBar progress_boot;
  private ProgressBar progress_file;
  private ProgressBar progress_repair_now;
  private ProgressBar progress_system;
  private LinearLayout scanner_result;
  private Random u = new Random();
  private StringBuilder v = new StringBuilder();
  
  public HomeActivity() {}
  
  static void FHome(HomeActivity paramHomeActivity)
  {
    paramHomeActivity.C += 1;
  }
  
  private void bHome(final String paramString)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    Object localObject = getLayoutInflater().inflate(2131361852, null);
    localBuilder.setView((View)localObject);
    Button localButton = (Button)((View)localObject).findViewById(2131230910);
    localObject = (TextView)((View)localObject).findViewById(2131230909);
    ((TextView)localObject).setMovementMethod(new ScrollingMovementMethod());
    ((TextView)localObject).setText(paramString);
    paramString = localBuilder.create();
    paramString.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      @SuppressLint({"WrongConstant"})
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        HomeActivity.this.progress_system.setProgress(0);
        HomeActivity.this.PictureSystem.setVisibility(4);
        HomeActivity.this.progress_boot.setProgress(0);
        HomeActivity.this.PictureBoot.setVisibility(4);
        HomeActivity.this.progress_file.setProgress(0);
        HomeActivity.this.PictureFiles.setVisibility(4);
        HomeActivity.this.scanner_result.removeAllViews();
        HomeActivity.this.progress_repair_now.setProgress(0);
      }
    });
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramString.cancel();
      }
    });
    paramString.show();
  }
  
  private String l()
  {
    String str = getResources().getString(2131492909);
    Iterator localIterator = getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append("● ");
      localStringBuilder.append(localApplicationInfo.loadLabel(getPackageManager()).toString());
      localStringBuilder.append(" ✓ \n");
      str = localStringBuilder.toString();
    }
    return str;
  }
  
  private void scanCache()
  {
    this.packageManager = getPackageManager();
    new Thread()
    {
      public void run()
      {
        try
        {
          new HomeActivity.MyDataObserver(HomeActivity.this, null);
          Thread.sleep(200L);
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }.start();
  }
  
  @SuppressLint({"WrongConstant", "ShowToast"})
  void JHome()
  {
    this.v.setLength(0);
    StringBuilder localStringBuilder1 = this.v;
    localStringBuilder1.append("# ");
    localStringBuilder1.append(getString(2131492998));
    localStringBuilder1 = this.v;
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("\n◷ ");
    localStringBuilder2.append(getString(2131493029));
    localStringBuilder2.append(this.m.format(new Date()));
    localStringBuilder1.append(localStringBuilder2.toString());
    this.PictureSystem.setVisibility(4);
    this.PictureBoot.setVisibility(4);
    this.PictureFiles.setVisibility(4);
    this.progress_system.setProgress(0);
    this.progress_boot.setProgress(0);
    this.progress_file.setProgress(0);
    this.RepairSystem.setText(getString(2131493013));
    this.A = new Timer();
    localStringBuilder1 = this.v;
    localStringBuilder1.append("\n# ");
    localStringBuilder1.append(getString(2131493022));
    this.A.scheduleAtFixedRate(new TimerTask()
    {
      public void run()
      {
        HomeActivity.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            HomeActivity.access$802(HomeActivity.this, HomeActivity.this.B + HomeActivity.this.u.nextInt(4));
            switch (HomeActivity.this.C)
            {
            default: 
              break;
            case 3: 
              HomeActivity.this.progress_file.setProgress(HomeActivity.this.B);
              if (!HomeActivity.this.D) {
                break;
              }
              break;
            case 2: 
              HomeActivity.this.progress_boot.setProgress(HomeActivity.this.B);
              if (!HomeActivity.this.D) {
                break;
              }
              break;
            case 1: 
              HomeActivity.this.progress_system.setProgress(HomeActivity.this.B);
              if (HomeActivity.this.D) {
                break label202;
              }
            }
            HomeActivity.this.kHome();
            label202:
            if (HomeActivity.this.B > 100)
            {
              if (HomeActivity.this.C == 1) {
                HomeActivity.this.PictureSystem.setVisibility(0);
              }
              if (HomeActivity.this.C == 2)
              {
                HomeActivity.this.PictureBoot.setVisibility(0);
                HomeActivity.this.progress_system.setProgress(100);
              }
              if (HomeActivity.this.C == 3)
              {
                HomeActivity.this.progress_boot.setProgress(100);
                HomeActivity.this.PictureFiles.setVisibility(0);
                HomeActivity.this.PictureFiles.setVisibility(0);
                HomeActivity.this.RepairSystem.setEnabled(true);
                HomeActivity.this.OptimizePhone.setEnabled(true);
                HomeActivity.this.PhoneInformation.setEnabled(true);
                HomeActivity.this.RepairSystem.setText(HomeActivity.this.getString(2131493031));
                HomeActivity.access$802(HomeActivity.this, 0);
                HomeActivity.access$1002(HomeActivity.this, 0);
                Object localObject = HomeActivity.this.v;
                ((StringBuilder)localObject).append("\n# ");
                ((StringBuilder)localObject).append(HomeActivity.this.getString(2131493009));
                HomeActivity.this.A.cancel();
                localObject = HomeActivity.this.v;
                ((StringBuilder)localObject).append("\n# ");
                ((StringBuilder)localObject).append(HomeActivity.this.getString(2131493021));
                localObject = HomeActivity.this.v;
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("\n◷ ");
                localStringBuilder.append(HomeActivity.this.getString(2131492966));
                localStringBuilder.append(HomeActivity.this.m.format(new Date()));
                ((StringBuilder)localObject).append(localStringBuilder.toString());
                localObject = HomeActivity.this;
                localStringBuilder = new StringBuilder();
                localStringBuilder.append(HomeActivity.this.v.toString());
                localStringBuilder.append("\n");
                localStringBuilder.append(HomeActivity.this.l());
                ((HomeActivity)localObject).bHome(localStringBuilder.toString());
              }
              HomeActivity.FHome(HomeActivity.this);
              HomeActivity.access$802(HomeActivity.this, 0);
            }
          }
        });
      }
    }, 400L, 400L);
  }
  
  public void MenuSetting(View paramView)
  {
    startActivity(new Intent(this, SettingsActivity.class));
  }
  
  protected void StartRepairSystemAndroid()
  {
    this.packageManager = getPackageManager();
    new Thread()
    {
      public void run()
      {
        List localList = HomeActivity.this.packageManager.getInstalledPackages(0);
        HomeActivity.this.progress_repair_now.setMax(localList.size() - 1);
        Iterator localIterator = localList.iterator();
        int j;
        for (int i = 0; localIterator.hasNext(); i = j)
        {
          Object localObject1 = (PackageInfo)localIterator.next();
          String str = ((PackageInfo)localObject1).applicationInfo.loadLabel(HomeActivity.this.packageManager).toString();
          localObject1 = ((PackageInfo)localObject1).applicationInfo.packageName;
          Object localObject2 = HomeActivity.this.progress_repair_now;
          j = i + 1;
          ((ProgressBar)localObject2).setProgress(i);
          localObject2 = Message.obtain();
          if (j == localList.size()) {
            ((Message)localObject2).what = 0;
          } else {
            ((Message)localObject2).what = 1;
          }
          Bundle localBundle = new Bundle();
          localBundle.putString(HomeActivity.this.getString(2131492994), str);
          localBundle.putString(HomeActivity.this.getString(2131492993), (String)localObject1);
          ((Message)localObject2).setData(localBundle);
          HomeActivity.this.handler.sendMessage((Message)localObject2);
          try
          {
            sleep(300L);
          }
          catch (InterruptedException localInterruptedException)
          {
            localInterruptedException.printStackTrace();
          }
        }
      }
    }.start();
    this.RepairSystem.setEnabled(false);
    this.OptimizePhone.setEnabled(false);
    this.PhoneInformation.setEnabled(false);
  }
  
  public void btnRateUs(View paramView)
  {
    try
    {
      paramView = new StringBuilder();
      paramView.append("market://details?id=");
      paramView.append(getPackageName());
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramView.toString())));
      return;
    }
    catch (ActivityNotFoundException paramView)
    {
      for (;;) {}
    }
    paramView = new StringBuilder();
    paramView.append("https://play.google.com/store/apps/details?id=");
    paramView.append(getPackageName());
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramView.toString())));
  }
  
  public void btnShareApps(View paramView)
  {
    paramView = new Intent("android.intent.action.SEND");
    paramView.setType("text/plain");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getString(2131492987));
    localStringBuilder.append("\thttps://play.google.com/store/apps/details?id=");
    localStringBuilder.append(getPackageName());
    paramView.putExtra("android.intent.extra.TEXT", localStringBuilder.toString());
    startActivity(paramView);
  }
  
  public void clearAllCache()
  {
    try
    {
      Method[] arrayOfMethod = PackageManager.class.getMethods();
      int j = arrayOfMethod.length;
      int i = 0;
      while (i < j)
      {
        Method localMethod = arrayOfMethod[i];
        if ("freeStorageAndNotify".equals(localMethod.getName()))
        {
          localMethod.invoke(this.packageManager, new Object[] { Long.valueOf(Long.MAX_VALUE), new MyPackageDataObserver(null) });
          return;
        }
        i += 1;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void googleInterstitial()
  {
    this.interstitialAds = new InterstitialAd(this);
    this.interstitialAds.setAdUnitId(getString(2131492984));
    this.interstitialAds.loadAd(new AdRequest.Builder().build());
    this.interstitialAds.setAdListener(new ToastAdListener()
    {
      public void onAdFailedToLoad(int paramAnonymousInt)
      {
        super.onAdFailedToLoad(paramAnonymousInt);
      }
      
      public void onAdLoaded()
      {
        super.onAdLoaded();
        if (HomeActivity.this.interstitialAds.isLoaded()) {
          HomeActivity.this.interstitialAds.show();
        }
      }
    });
  }
  
  void kHome()
  {
    this.D = true;
    for (;;)
    {
      try
      {
        localPackageManager = getPackageManager();
        this.v.append("\n# Package Manager Checking");
        Method[] arrayOfMethod = localPackageManager.getClass().getDeclaredMethods();
        this.v.append("\n# Package Manager Listed");
        int j = arrayOfMethod.length;
        i = 0;
        if (i < j) {
          localMethod = arrayOfMethod[i];
        }
      }
      catch (Exception localException1)
      {
        PackageManager localPackageManager;
        int i;
        Method localMethod;
        return;
      }
      try
      {
        if (localMethod.getName().equals("freeStorage"))
        {
          localMethod.invoke(localPackageManager, new Object[] { Long.valueOf(0L), null });
          return;
        }
        i += 1;
      }
      catch (Exception localException2) {}
    }
    return;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131361820);
    MobileAds.initialize(this, getString(2131492911));
    ((AdView)findViewById(2131230792)).loadAd(new AdRequest.Builder().build());
    getFilesDir();
    getCacheDir();
    scanCache();
    this.Show_Type_Charge = ((TextView)findViewById(2131230766));
    this.TextBatteryPercentage = ((TextView)findViewById(2131230768));
    this.BatteryPercentage = ((ProgressBar)findViewById(2131230721));
    paramBundle = getApplicationContext();
    IntentFilter localIntentFilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    paramBundle.registerReceiver(this.BatteryInformation, localIntentFilter);
    this.progress_repair_now = ((ProgressBar)findViewById(2131230906));
    this.scanner_result = ((LinearLayout)findViewById(2131230914));
    this.progress_system = ((ProgressBar)findViewById(2131230907));
    this.progress_boot = ((ProgressBar)findViewById(2131230902));
    this.progress_file = ((ProgressBar)findViewById(2131230904));
    this.PictureSystem = ((ImageView)findViewById(2131230761));
    this.PictureBoot = ((ImageView)findViewById(2131230759));
    this.PictureFiles = ((ImageView)findViewById(2131230760));
    this.OptimizePhone = ((Button)findViewById(2131230757));
    this.OptimizePhone.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent(HomeActivity.this, OptimizePhone.class);
        HomeActivity.this.startActivity(paramAnonymousView);
      }
    });
    this.RepairSystem = ((Button)findViewById(2131230762));
    this.RepairSystem.setOnClickListener(new View.OnClickListener()
    {
      @SuppressLint({"InflateParams"})
      public void onClick(View paramAnonymousView)
      {
        HomeActivity.this.clearAllCache();
        paramAnonymousView = Toast.makeText(HomeActivity.this.getApplicationContext(), 2131493028, 1);
        paramAnonymousView.setGravity(17, 0, 0);
        LinearLayout localLinearLayout = (LinearLayout)paramAnonymousView.getView();
        ImageView localImageView = new ImageView(HomeActivity.this.getApplicationContext());
        localImageView.setImageResource(2131427328);
        localLinearLayout.addView(localImageView, 0);
        paramAnonymousView.show();
        HomeActivity.this.StartRepairSystemAndroid();
      }
    });
    this.PhoneInformation = ((Button)findViewById(2131230758));
    this.PhoneInformation.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent(HomeActivity.this, PhoneInformation.class);
        HomeActivity.this.startActivity(paramAnonymousView);
        HomeActivity.this.googleInterstitial();
      }
    });
  }
  
  private class MyDataObserver
    extends IPackageStatsObserver.Stub
  {
    private MyDataObserver() {}
    
    public void onGetStatsCompleted(final PackageStats paramPackageStats, boolean paramBoolean)
    {
      try
      {
        final long l = paramPackageStats.cacheSize;
        paramPackageStats = paramPackageStats.packageName;
        paramPackageStats = HomeActivity.this.packageManager.getApplicationInfo(paramPackageStats, 0);
        HomeActivity.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            String str = paramPackageStats.loadLabel(HomeActivity.this.packageManager).toString();
            if (l > 0L)
            {
              PrintStream localPrintStream = System.out;
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append(str);
              localStringBuilder.append("The size of the cache : ");
              localStringBuilder.append(Formatter.formatFileSize(HomeActivity.this.getApplicationContext(), l));
              localPrintStream.println(localStringBuilder.toString());
            }
          }
        });
        return;
      }
      catch (PackageManager.NameNotFoundException paramPackageStats)
      {
        paramPackageStats.printStackTrace();
      }
    }
  }
  
  private class MyPackageDataObserver
    extends IPackageDataObserver.Stub
  {
    private MyPackageDataObserver() {}
    
    public void onRemoveCompleted(String paramString, boolean paramBoolean)
    {
      if (paramBoolean)
      {
        localPrintStream = System.out;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramString);
        localStringBuilder.append(" Cache clear successfully");
        localPrintStream.println(localStringBuilder.toString());
        return;
      }
      PrintStream localPrintStream = System.out;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append(" Cache Clear Fails");
      localPrintStream.println(localStringBuilder.toString());
    }
  }
}
