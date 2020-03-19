package com.mindefy.phoneaddiction.mobilepe.activity;

import android.app.usage.UsageEvents.Event;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.github.ybq.android.spinkit.SpinKitView;
import com.mindefy.phoneaddiction.mobilepe.Util.UsageStat;
import com.mindefy.phoneaddiction.mobilepe.Util.Util;
import com.mindefy.phoneaddiction.mobilepe.fragment.AddictionCategoryFragment;
import com.mindefy.phoneaddiction.mobilepe.fragment.CentralFragment;
import com.mindefy.phoneaddiction.mobilepe.fragment.ContentFragment;
import com.mindefy.phoneaddiction.mobilepe.fragment.CustomizeTimerFragment;
import com.mindefy.phoneaddiction.mobilepe.fragment.DailyUsageFragment;
import com.mindefy.phoneaddiction.mobilepe.fragment.DailyUsageInstanceFragment;
import com.mindefy.phoneaddiction.mobilepe.fragment.OverlayPermissionDialog;
import com.mindefy.phoneaddiction.mobilepe.fragment.PermissionDialogFragment;
import com.mindefy.phoneaddiction.mobilepe.fragment.SettingsFragment;
import com.mindefy.phoneaddiction.mobilepe.fragment.WatcherAppFragment;
import com.mindefy.phoneaddiction.mobilepe.fragment.WeeklyReportFragment;
import com.mindefy.phoneaddiction.mobilepe.pojo.AllAppInfoPojo;
import com.mindefy.phoneaddiction.mobilepe.pojo.AllEventsPojo;
import com.mindefy.phoneaddiction.mobilepe.pojo.AppInfo;
import com.mindefy.phoneaddiction.mobilepe.pojo.DailyTimelineListPojo;
import com.vorlonsoft.android.rate.AppRate;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainActivity
  extends AppCompatActivity
{
  ViewPagerAdapter adapter;
  CentralFragment centralFragment;
  ContentFragment contentFragment;
  DailyUsageFragment dailyUsageFragment;
  boolean doubleBackToExitPressedOnce = false;
  boolean isDialogShowed = false;
  private boolean isOnDestroyCalled = false;
  SpinKitView progressBar;
  TextView textView;
  ViewPager viewPager;
  WeeklyReportFragment weeklyReportFragment;
  
  public MainActivity() {}
  
  public static boolean isAppInstalled(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getPackageManager().getApplicationInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public ArrayList<ArrayList<AppInfo>> getAppInfo(List<UsageEvents.Event> paramList)
    throws PackageManager.NameNotFoundException
  {
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList1 = new ArrayList();
    Object localObject2 = null;
    int i = 0;
    Object localObject1 = null;
    long l5 = 0L;
    long l1 = 0L;
    long l4 = 0L;
    long l3;
    for (long l2 = 0L;; l2 = l3)
    {
      Object localObject3 = paramList;
      if (i >= paramList.size() - 2) {
        break;
      }
      Object localObject5 = (UsageEvents.Event)((List)localObject3).get(i);
      int j = i + 1;
      UsageEvents.Event localEvent3 = (UsageEvents.Event)((List)localObject3).get(j);
      UsageEvents.Event localEvent1 = (UsageEvents.Event)((List)localObject3).get(j);
      l3 = l5;
      UsageEvents.Event localEvent2 = (UsageEvents.Event)((List)localObject3).get(i + 2);
      Calendar localCalendar = Calendar.getInstance();
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("hh:mm a");
      AppInfo localAppInfo = new AppInfo();
      Object localObject4 = ((UsageEvents.Event)localObject5).getPackageName();
      if (i != 0) {
        if ((!((UsageEvents.Event)((List)localObject3).get(i - 1)).getClassName().equals(((UsageEvents.Event)localObject5).getClassName())) && (!((UsageEvents.Event)localObject5).getClassName().equals(localEvent3.getClassName())))
        {
          l5 = localEvent3.getTimeStamp() - ((UsageEvents.Event)localObject5).getTimeStamp();
          localCalendar.setTimeInMillis(((UsageEvents.Event)localObject5).getTimeStamp());
          localAppInfo.setAppRunTime(localSimpleDateFormat.format(localCalendar.getTime()));
          localAppInfo.setAppname("Happy Time");
          localAppInfo.setIdleTime(true);
          localAppInfo.setIcon(getResources().getDrawable(2131230924));
          localAppInfo.setTotalAppForgroundTime(l5);
          l1 = l5;
          if (l5 > 1000L)
          {
            localObject3 = new ArrayList();
            ((ArrayList)localObject3).add(localAppInfo);
            localArrayList1.add(localObject3);
            l1 = l5;
          }
        }
        else
        {
          l1 = localEvent2.getTimeStamp() - localEvent1.getTimeStamp();
        }
      }
      if ((((UsageEvents.Event)localObject5).getEventType() == 1) && (localEvent3.getEventType() == 2) && (((UsageEvents.Event)localObject5).getClassName().equals(localEvent3.getClassName())) && (isAppInstalled(getApplicationContext(), ((UsageEvents.Event)localObject5).getPackageName())))
      {
        long l6 = localEvent3.getTimeStamp();
        long l7 = ((UsageEvents.Event)localObject5).getTimeStamp();
        if (!((String)localObject4).equalsIgnoreCase((String)localObject2))
        {
          localObject2 = new AppInfo();
          localCalendar.setTimeInMillis(((UsageEvents.Event)localObject5).getTimeStamp());
          ((AppInfo)localObject2).setAppRunTime(localSimpleDateFormat.format(localCalendar.getTime()));
          localObject1 = localObject4;
          l3 = 0L;
          l5 = 0L;
        }
        else
        {
          localObject3 = localObject1;
          l5 = l4;
          localObject1 = localObject2;
          localObject2 = localObject3;
        }
        l4 = l3 + l1;
        l5 += l6 - l7;
        if (localObject2 != null)
        {
          localObject3 = getApplicationContext().getPackageManager().getApplicationIcon(((UsageEvents.Event)localObject5).getPackageName());
          localObject5 = (String)getApplicationContext().getPackageManager().getApplicationLabel(getApplicationContext().getPackageManager().getApplicationInfo(((UsageEvents.Event)localObject5).getPackageName(), 128));
          ((AppInfo)localObject2).setTotalAppForgroundTime(l5);
          ((AppInfo)localObject2).setIcon((Drawable)localObject3);
          ((AppInfo)localObject2).setAppname((String)localObject5);
          localCalendar.setTimeInMillis(localEvent1.getTimeStamp());
          localAppInfo.setAppRunTime(localSimpleDateFormat.format(localCalendar.getTime()));
          localAppInfo.setAppname("Happy Time");
          localAppInfo.setIcon(getResources().getDrawable(2131230924));
          localAppInfo.setIdleTime(true);
          localAppInfo.setTotalAppForgroundTime(l4);
          if (!((String)localObject4).equalsIgnoreCase(localEvent2.getPackageName()))
          {
            l3 = l2 + l5 + l4;
            if (l3 < 600000L)
            {
              if (l5 > 1000L) {
                localArrayList2.add(localObject2);
              }
              if (l4 > 1000L) {
                localArrayList2.add(localAppInfo);
              }
              l2 = l5;
              localObject3 = localObject1;
              localObject1 = localObject2;
              localObject2 = localObject3;
              break label1039;
            }
            if (l5 > 600000L) {
              if (l4 > 600000L)
              {
                localObject3 = localArrayList1;
                ((ArrayList)localObject3).add(localArrayList2);
                localArrayList2 = new ArrayList();
                localObject4 = new ArrayList();
                ((ArrayList)localObject4).add(localObject2);
                ((ArrayList)localObject3).add(localObject4);
                localObject4 = new ArrayList();
                ((ArrayList)localObject4).add(localObject2);
                ((ArrayList)localObject3).add(localObject4);
              }
            }
            for (;;)
            {
              l3 = 0L;
              break;
              localObject3 = localArrayList1;
              localArrayList2.add(localAppInfo);
              ((ArrayList)localObject3).add(localArrayList2);
              localArrayList2 = new ArrayList();
              localObject4 = new ArrayList();
              ((ArrayList)localObject4).add(localObject2);
              ((ArrayList)localObject3).add(localObject4);
              continue;
              localObject3 = localArrayList1;
              if (l4 > 600000L)
              {
                if (l5 > 1000L) {
                  localArrayList2.add(localObject2);
                }
                if (localArrayList2.size() > 0) {
                  ((ArrayList)localObject3).add(localArrayList2);
                }
                localArrayList2 = new ArrayList();
                localObject4 = new ArrayList();
                ((ArrayList)localObject4).add(localAppInfo);
                ((ArrayList)localObject3).add(localObject4);
              }
              else
              {
                if (l5 > 1000L) {
                  localArrayList2.add(localObject2);
                }
                if (l4 > 1000L) {
                  localArrayList2.add(localAppInfo);
                }
                if (localArrayList2.size() > 0) {
                  ((ArrayList)localObject3).add(localArrayList2);
                }
                localArrayList2 = new ArrayList();
              }
            }
          }
        }
        l3 = l2;
        l2 = l5;
        localObject3 = localObject1;
        localObject1 = localObject2;
        localObject2 = localObject3;
      }
      else
      {
        l5 = l3;
        l3 = l2;
        l2 = l4;
        l4 = l5;
      }
      label1039:
      i = j;
      l5 = l4;
      l4 = l2;
    }
    return localArrayList1;
  }
  
  public ArrayList<AppInfo> getDailyReportAppInfo(Context paramContext, List<AppInfo> paramList)
    throws PackageManager.NameNotFoundException
  {
    paramContext = paramContext.getPackageManager();
    Object localObject = paramContext.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    HashMap localHashMap = new HashMap();
    paramList = paramList.iterator();
    AppInfo localAppInfo;
    while (paramList.hasNext())
    {
      localAppInfo = (AppInfo)paramList.next();
      localHashMap.put(localAppInfo.getPname(), localAppInfo);
    }
    paramList = ((List)localObject).iterator();
    while (paramList.hasNext())
    {
      localObject = (PackageInfo)paramList.next();
      localAppInfo = new AppInfo();
      String str = ((PackageInfo)localObject).packageName;
      if ((!str.equalsIgnoreCase("com.android.systemui")) && (!str.equalsIgnoreCase("android")) && (!str.equalsIgnoreCase("com.google.android.packageinstaller")) && (localHashMap.containsKey(str)))
      {
        long l = ((AppInfo)localHashMap.get(str)).getTotalAppForgroundTime();
        int i = ((AppInfo)localHashMap.get(str)).getAppLaunchCount();
        localAppInfo.setAppRunTime(Util.convertMilliToTime(l));
        localAppInfo.setTotalAppForgroundTime(l);
        Drawable localDrawable = ((PackageInfo)localObject).applicationInfo.loadIcon(paramContext);
        localAppInfo.setPname(str);
        localAppInfo.setAppname((String)((PackageInfo)localObject).applicationInfo.loadLabel(paramContext));
        localAppInfo.setIcon(localDrawable);
        localAppInfo.setAppLaunchCount(i);
        localArrayList.add(localAppInfo);
      }
    }
    return localArrayList;
  }
  
  public ViewPager getPager()
  {
    return this.viewPager;
  }
  
  public String getVersionCode()
  {
    Iterator localIterator = null;
    localObject3 = null;
    Object localObject2 = null;
    Object localObject1 = localIterator;
    try
    {
      Object localObject4 = Jsoup.connect("https://play.google.com/store/apps/details?id=com.mindefy.phoneaddiction.mobilepe&hl=en").timeout(30000).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6").referrer("http://www.google.com").get();
      if (localObject4 != null)
      {
        localObject1 = localIterator;
        localIterator = ((Document)localObject4).getElementsContainingOwnText("description").iterator();
        do
        {
          localObject1 = localObject2;
          localObject3 = localObject2;
          if (!localIterator.hasNext()) {
            break;
          }
          localObject1 = localObject2;
          localObject3 = (Element)localIterator.next();
          localObject1 = localObject2;
        } while (((Element)localObject3).siblingElements() == null);
        localObject1 = localObject2;
        localObject4 = ((Element)localObject3).siblingElements().iterator();
        for (localObject3 = localObject2;; localObject3 = ((Element)((Iterator)localObject4).next()).text())
        {
          localObject2 = localObject3;
          localObject1 = localObject3;
          if (!((Iterator)localObject4).hasNext()) {
            break;
          }
          localObject1 = localObject3;
        }
      }
      return localObject3;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      localObject3 = localObject1;
    }
  }
  
  public boolean isOnDestroyCalled()
  {
    return this.isOnDestroyCalled;
  }
  
  public void onBackPressed()
  {
    Fragment localFragment1 = getSupportFragmentManager().findFragmentByTag("customizeTimerFragment");
    Fragment localFragment2 = getSupportFragmentManager().findFragmentByTag("addictionCategoryFragment");
    Fragment localFragment3 = getSupportFragmentManager().findFragmentByTag("settingsFragment");
    Fragment localFragment4 = getSupportFragmentManager().findFragmentByTag("watcherAppFragment");
    Fragment localFragment5 = getSupportFragmentManager().findFragmentByTag("weeklyReportStatAppFragment");
    Fragment localFragment6 = getSupportFragmentManager().findFragmentByTag("overlayPermissionDialog");
    Fragment localFragment7 = getSupportFragmentManager().findFragmentByTag("permissionDialogFragment");
    if ((!(localFragment1 instanceof CustomizeTimerFragment)) && (!(localFragment2 instanceof AddictionCategoryFragment)) && (!(localFragment6 instanceof OverlayPermissionDialog)) && (!(localFragment3 instanceof SettingsFragment)) && (!(localFragment4 instanceof WatcherAppFragment)) && (!(localFragment5 instanceof DailyUsageInstanceFragment)) && (!(localFragment7 instanceof PermissionDialogFragment)))
    {
      if (this.viewPager.getCurrentItem() != 0)
      {
        this.viewPager.setCurrentItem(this.viewPager.getCurrentItem() - 1, true);
        return;
      }
      if (!this.doubleBackToExitPressedOnce)
      {
        this.doubleBackToExitPressedOnce = true;
        if (!this.isDialogShowed)
        {
          if (AppRate.with(this).shouldShowRateDialog())
          {
            this.isDialogShowed = true;
            if (!isOnDestroyCalled()) {
              AppRate.showRateDialogIfMeetsConditions(this);
            }
          }
          else
          {
            Toast.makeText(this, "Please click BACK again to exit.", 0).show();
          }
        }
        else {
          Toast.makeText(this, "Please click BACK again to exit.", 0).show();
        }
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            MainActivity.this.doubleBackToExitPressedOnce = false;
          }
        }, 2000L);
        return;
      }
      super.onBackPressed();
      return;
    }
    if (getFragmentManager().getBackStackEntryCount() > 0)
    {
      getFragmentManager().popBackStack();
      return;
    }
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427358);
    this.progressBar = ((SpinKitView)findViewById(2131296505));
    this.viewPager = ((ViewPager)findViewById(2131296657));
    this.textView = ((TextView)findViewById(2131296515));
    this.centralFragment = new CentralFragment();
    this.dailyUsageFragment = new DailyUsageFragment();
    this.contentFragment = new ContentFragment();
    this.weeklyReportFragment = new WeeklyReportFragment();
    this.adapter = new ViewPagerAdapter(getSupportFragmentManager());
    startDialog();
    this.viewPager.setOffscreenPageLimit(3);
    Typeface localTypeface = Typeface.createFromAsset(getAssets(), "fonts/Xoxoxa.ttf");
    Object localObject = Arrays.asList(getResources().getStringArray(2130903040));
    localObject = (String)((List)localObject).get(new Random().nextInt(((List)localObject).size()));
    this.textView.setTypeface(localTypeface);
    this.textView.setText((CharSequence)localObject);
    this.progressBar.setVisibility(0);
    this.textView.setVisibility(0);
    this.isOnDestroyCalled = false;
    if (paramBundle != null)
    {
      paramBundle = new Intent(getApplicationContext(), SplashActivity.class);
      paramBundle.setFlags(67108864);
      startActivity(paramBundle);
      finish();
      return;
    }
    new BackgroundProcess().execute(new Void[0]);
  }
  
  protected void onPause()
  {
    super.onPause();
    this.isOnDestroyCalled = true;
  }
  
  protected void onResumeFragments()
  {
    super.onResumeFragments();
    this.isOnDestroyCalled = false;
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putBoolean("isHomeButtonPressed", true);
  }
  
  protected void onStart()
  {
    super.onStart();
    this.isOnDestroyCalled = false;
  }
  
  protected void onStop()
  {
    super.onStop();
    this.isOnDestroyCalled = true;
  }
  
  public void startDialog()
  {
    View localView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2131427427, (ViewGroup)findViewById(2131296518));
    AppRate.with(this).setInstallDays((byte)0).setLaunchTimes((byte)1).setRemindInterval((byte)0).setRemindLaunchTimes((byte)2).setTitle("Rate YourHour App").setShowLaterButton(true).setShowNeverButton(false).setMessage(2131493028).setDebug(false).setCancelable(false).setView(localView).monitor();
  }
  
  public class BackgroundProcess
    extends AsyncTask<Void, Void, Void>
  {
    public BackgroundProcess() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      if (Util.isFirstTime(MainActivity.this.getApplicationContext()))
      {
        Util.setFirstTime(MainActivity.this.getApplicationContext());
        paramVarArgs = UsageStat.printYesterdayUsageStatus(MainActivity.this.getApplicationContext(), true);
        Util.setYesterdayUsageTime(MainActivity.this.getApplicationContext(), paramVarArgs);
      }
      paramVarArgs = UsageStat.getUsageStatsList(MainActivity.this.getApplicationContext());
      try
      {
        ArrayList localArrayList1 = Util.getWeekAppInfo(MainActivity.this);
        ArrayList localArrayList2 = MainActivity.this.getAppInfo(paramVarArgs.getAllEventsPojo().getAllEvents());
        ArrayList localArrayList3 = MainActivity.this.getDailyReportAppInfo(MainActivity.this.getApplicationContext(), paramVarArgs.getAppInfoList());
        DailyTimelineListPojo localDailyTimelineListPojo = new DailyTimelineListPojo();
        localDailyTimelineListPojo.setDailyTimelineList(localArrayList2);
        MainActivity.this.centralFragment.getTotalUsageTime(paramVarArgs.getTotalUsageTime());
        MainActivity.this.dailyUsageFragment.getAllEventsList(localDailyTimelineListPojo);
        MainActivity.this.contentFragment.getUsageStatList(localArrayList3);
        MainActivity.this.weeklyReportFragment.getWeekInfoList(localArrayList1);
      }
      catch (PackageManager.NameNotFoundException paramVarArgs)
      {
        paramVarArgs.printStackTrace();
      }
      return null;
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      MainActivity.this.adapter.addFragment(MainActivity.this.centralFragment);
      MainActivity.this.adapter.addFragment(MainActivity.this.dailyUsageFragment);
      MainActivity.this.adapter.addFragment(MainActivity.this.contentFragment);
      MainActivity.this.adapter.addFragment(MainActivity.this.weeklyReportFragment);
      MainActivity.this.viewPager.setAdapter(MainActivity.this.adapter);
      MainActivity.this.progressBar.setVisibility(8);
      MainActivity.this.viewPager.setVisibility(0);
      MainActivity.this.textView.setVisibility(8);
    }
  }
  
  public class ViewPagerAdapter
    extends FragmentStatePagerAdapter
  {
    private final List<Fragment> mFragmentList = new ArrayList();
    
    ViewPagerAdapter(android.support.v4.app.FragmentManager paramFragmentManager)
    {
      super();
    }
    
    void addFragment(Fragment paramFragment)
    {
      this.mFragmentList.add(paramFragment);
    }
    
    public int getCount()
    {
      return this.mFragmentList.size();
    }
    
    public Fragment getItem(int paramInt)
    {
      return (Fragment)this.mFragmentList.get(paramInt);
    }
    
    public int getItemPosition(Object paramObject)
    {
      return -2;
    }
  }
}
