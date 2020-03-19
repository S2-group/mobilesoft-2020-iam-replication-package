package zok.android.shapes;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Toast;
import com.flurry.android.FlurryAgent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import zok.android.shapes.notification.NotificationManager;
import zok.android.shapes.store.Store;

public class HomeActivity
  extends BaseActivity
{
  private static final int PROMO_ACTIVITY_REQUEST = 0;
  private static boolean sFullVersionWarninsShown;
  private List<String> mInstalledPackages;
  private SharedPreferences mPreferences;
  
  public HomeActivity() {}
  
  private boolean checkForRatingRequest()
  {
    if (this.mPreferences.getBoolean("ratingRequestShown", false)) {}
    while ((getDaysCountSinceFirstRun() <= 3L) || (getRunCount() <= 5)) {
      return false;
    }
    SharedPreferences.Editor localEditor = this.mPreferences.edit();
    localEditor.putBoolean("ratingRequestShown", true);
    localEditor.commit();
    showRatingRequestDialog();
    return true;
  }
  
  private Date createDate(int paramInt1, int paramInt2, int paramInt3)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.set(1, paramInt1);
    localCalendar.set(2, paramInt2);
    localCalendar.set(5, paramInt3);
    return localCalendar.getTime();
  }
  
  private int getRunCount()
  {
    return this.mPreferences.getInt("run_count", 0);
  }
  
  private void increaseRunCount()
  {
    SharedPreferences.Editor localEditor = this.mPreferences.edit();
    localEditor.putInt("run_count", getRunCount() + 1);
    localEditor.commit();
  }
  
  private boolean isInstalled(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    Iterator localIterator;
    if (this.mInstalledPackages == null)
    {
      this.mInstalledPackages = new ArrayList();
      localIterator = getPackageManager().getInstalledApplications(128).iterator();
    }
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return this.mInstalledPackages.contains(paramString);
      }
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      this.mInstalledPackages.add(localApplicationInfo.packageName);
    }
  }
  
  private boolean isScreenSmall()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return Math.max(localDisplayMetrics.widthPixels, localDisplayMetrics.heightPixels) < 440;
  }
  
  private void registerListener(int paramInt, final Class<? extends Activity> paramClass)
  {
    findViewById(paramInt).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        HomeActivity.this.startActivity(new Intent(HomeActivity.this, paramClass));
      }
    });
  }
  
  private void registerListeners()
  {
    registerListener(2131296264, MainActivity.class);
    registerListener(2131296265, PreferencesActivity.class);
    if (getString(2131165241).equals("ru")) {
      findViewById(2131296266).setOnClickListener(new ExternalLinkOnClickListener(new Runnable()
      {
        public void run()
        {
          FlurryAgent.onEvent("Buy button pressed RU");
          HomeActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.intellijoy.android.shapes.ru")));
        }
      }));
    }
    for (;;)
    {
      findViewById(2131296267).setOnClickListener(new ExternalLinkOnClickListener(new Runnable()
      {
        public void run()
        {
          if (HomeActivity.this.isScreenSmall())
          {
            FlurryAgent.onEvent("More Games Opened on Small Screen");
            HomeActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pub:Intellijoy")));
            return;
          }
          FlurryAgent.onEvent("More Games Opened");
          HomeActivity.this.startActivity(new Intent(HomeActivity.this, MoreGamesActivity.class));
        }
      }));
      findViewById(2131296268).setOnClickListener(new ExternalLinkOnClickListener(new Runnable()
      {
        public void run()
        {
          FlurryAgent.onEvent("Facebook button was pressed");
          HomeActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.facebook.com/intellijoy")));
        }
      }));
      findViewById(2131296269).setOnClickListener(new ExternalLinkOnClickListener(new Runnable()
      {
        public void run()
        {
          FlurryAgent.onEvent("Twitter button was pressed");
          HomeActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://twitter.com/#!/Intellijoy")));
        }
      }));
      findViewById(2131296271).setOnClickListener(new ExternalLinkOnClickListener(new Runnable()
      {
        public void run()
        {
          FlurryAgent.onEvent("Gamepop button was pressed");
          HomeActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.intellijoy.com/gamepop/")));
        }
      }));
      findViewById(2131296270).setOnClickListener(new ExternalLinkOnClickListener(new Runnable()
      {
        public void run()
        {
          FlurryAgent.onEvent("Share button was pressed");
          Intent localIntent = new Intent("android.intent.action.SEND");
          localIntent.setType("text/plain");
          localIntent.putExtra("android.intent.extra.SUBJECT", HomeActivity.this.getString(2131165243));
          localIntent.putExtra("android.intent.extra.TEXT", HomeActivity.this.getString(2131165244));
          HomeActivity.this.startActivity(Intent.createChooser(localIntent, HomeActivity.this.getString(2131165245)));
        }
      }));
      return;
      findViewById(2131296266).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          HomeActivity.this.showMorePuzzlesProposal();
        }
      });
    }
  }
  
  private void showFullVersionWarning()
  {
    sFullVersionWarninsShown = true;
    FlurryAgent.onEvent("Full Version Waring was shown");
    DialogInterface.OnClickListener local8 = new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        HomeActivity.this.startActivity(HomeActivity.this.getPackageManager().getLaunchIntentForPackage(Config.STORE.getFullVersionPackageName()));
        HomeActivity.this.finish();
      }
    };
    new AlertDialog.Builder(this).setTitle(2131165248).setMessage(getString(2131165249)).setPositiveButton(getString(2131165250), null).setNegativeButton(2131165251, local8).show();
  }
  
  private void showMorePuzzlesProposal()
  {
    Intent localIntent = new Intent(this, GetFullVersionActivity.class);
    localIntent.putExtra("event", "FullVersionOpenedFromHomePage");
    startActivity(localIntent);
  }
  
  private boolean showPromo(PromoActivity paramPromoActivity)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramPromoActivity.isShowable(this.mPreferences))
    {
      bool1 = bool2;
      if (!isInstalled(paramPromoActivity.getFreeAppPackageName()))
      {
        bool1 = bool2;
        if (!isInstalled(paramPromoActivity.getPaidAppPackageName()))
        {
          startActivityForResult(new Intent(this, paramPromoActivity.getClass()), 0);
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  private void showRatingRequestDialog()
  {
    FlurryAgent.onEvent("Request for rating");
    DialogInterface.OnClickListener local9 = new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        FlurryAgent.onEvent("Rate button pressed");
        Config.STORE.openAppPage(HomeActivity.this, HomeActivity.this.getPackageName());
      }
    };
    new AlertDialog.Builder(this).setTitle(2131165187).setMessage(getString(2131165188)).setPositiveButton(getString(2131165190), local9).setNegativeButton(2131165189, null).show();
  }
  
  public long getDaysCountSinceFirstRun()
  {
    long l = this.mPreferences.getLong("firstRunTimestamp", 0L);
    if (l == 0L)
    {
      SharedPreferences.Editor localEditor = this.mPreferences.edit();
      localEditor.putLong("firstRunTimestamp", new Date().getTime());
      localEditor.commit();
      return 0L;
    }
    return (new Date().getTime() - l) / 86400000L;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 0) {
      finish();
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903042);
    findViewById(2131296263).setBackgroundResource(DrawableUtils.getDrawableResourceByResolution(getResources(), "home_bg"));
    this.mPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    registerListeners();
    increaseRunCount();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (!isScreenSmall()))
    {
      boolean bool = new Date().before(createDate(2013, 3, 12));
      if (((bool) && (showPromo(new TrainsPromoActivity()))) || (showPromo(new DotsPromoActivity())) || ((!bool) && (showPromo(new TrainsPromoActivity()))) || (showPromo(new RescueRobyPromoActivity())) || (showPromo(new ProfessionsPromoActivity())) || (showPromo(new LettersPromoActivity())) || (showPromo(new PhonicsPromoActivity())) || (showPromo(new ReadingPromoActivity())) || (showPromo(new AnimalsPromoActivity())) || (showPromo(new MathPromoActivity())) || (showPromo(new LearnShapesPromoActivity()))) {
        return true;
      }
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onResume()
  {
    super.onResume();
    if ((!sFullVersionWarninsShown) && (isInstalled(Config.STORE.getFullVersionPackageName()))) {
      showFullVersionWarning();
    }
    while ((checkForRatingRequest()) || (getRunCount() <= 1)) {
      return;
    }
    NotificationManager.getInstance().showNotifacation(this);
  }
  
  private class ExternalLinkOnClickListener
    implements View.OnClickListener
  {
    private static final long HINT_INTERVAL_MILLIS = 3000L;
    private Runnable mAction;
    private int mClickCounter;
    private long mLastHintTimestamp;
    private Toast mToast;
    
    public ExternalLinkOnClickListener(Runnable paramRunnable)
    {
      this.mAction = paramRunnable;
    }
    
    private void hideHint()
    {
      if (this.mToast != null) {
        this.mToast.cancel();
      }
    }
    
    private void showHint()
    {
      hideHint();
      View localView = HomeActivity.this.getLayoutInflater().inflate(2130903040, null);
      Toast localToast = new Toast(HomeActivity.this.getApplicationContext());
      localToast.setGravity(17, 0, 0);
      localToast.setDuration(1);
      localToast.setView(localView);
      localToast.show();
      this.mToast = localToast;
    }
    
    public void onClick(View paramView)
    {
      long l = System.currentTimeMillis();
      if (l - this.mLastHintTimestamp > 3000L)
      {
        showHint();
        this.mLastHintTimestamp = l;
      }
      for (this.mClickCounter = 0;; this.mClickCounter += 1)
      {
        if (this.mClickCounter >= 2)
        {
          hideHint();
          this.mAction.run();
        }
        return;
      }
    }
  }
}
