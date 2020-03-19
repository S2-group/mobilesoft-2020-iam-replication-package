package com.amber.lib.common_library;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import com.amber.lib.appuser.AppUseInfo;
import com.amber.lib.basewidget.AbsMainActivity.FromPop;
import com.amber.lib.basewidget.BaseWidgetManager;
import com.amber.lib.basewidget.WeatherBaseApplication;
import com.amber.lib.basewidget.otheractivity.WeatherWarnActivity;
import com.amber.lib.basewidget.pop.popinterface.IClickPopInterface;
import com.amber.lib.common_library.daily.DailyActivity;
import com.amber.lib.common_library.home.HomeActivity;
import com.amber.lib.common_library.hourly.HourlyActivity;
import com.amber.lib.common_library.utils.CommonUtils;
import com.amber.lib.common_library.utils.WeatherIconAdapter;
import com.amber.lib.report.ReportManger;
import com.amber.lib.statistical.AbsEventAble;
import com.amber.lib.statistical.StatisticalManager;
import com.amber.lib.statistical.facebook.FacebookEvent;
import com.amber.lib.statistical.firebase.FirebaseEvent;
import com.amber.lib.statistical.firebase.extra.EventControllerAlways;
import com.amber.lib.statistical.umeng.UmengEvent;
import com.amber.lib.store.adapter.LockerSetting;
import com.amber.lib.weatherdata.core.SDKContext;
import com.amber.lib.weatherdata.core.config.SDKConfigManager;
import com.amber.lib.weatherdata.core.module.weather.IUnitDeafult;
import com.amber.lib.weatherdata.core.module.weather.WeatherData;
import com.amber.lib.widget.WidgetUtils;
import com.amber.lib.widget.status.WidgetStatusManager.MainWidgetController;
import com.amber.lib.widget.store.ui.store.AmberWidgetStoreActivity;
import com.amberweather.sdk.amberadsdk.AmberAdSdk;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class BaseApplication
  extends WeatherBaseApplication
  implements IClickPopInterface
{
  private static final String LWP_PKG_PREFIX = "mobi.infolife.ezweather.livewallpaper";
  private static final String WEATHER_PKG_PREFIX = "mobi.infolife.ezweather.widget";
  protected boolean isWeatherApp = true;
  protected Context mAppContext;
  
  public BaseApplication() {}
  
  private void initAppStatus()
  {
    boolean bool = true;
    if (AppPreference.getInstance(this).getAppStatus(this) == 0)
    {
      Iterator localIterator = getPackageManager().getInstalledPackages(8192).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if ((!localPackageInfo.packageName.equals(getPackageName())) && ((localPackageInfo.packageName.startsWith("mobi.infolife.ezweather.livewallpaper")) || (localPackageInfo.packageName.startsWith("mobi.infolife.ezweather.widget"))))
        {
          AppPreference.getInstance(this).setAppStatus(this, 2);
          this.isWeatherApp = false;
          return;
        }
      }
      AppPreference.getInstance(this).setAppStatus(this, 1);
    }
    if (AppPreference.getInstance(this).getAppStatus(this) == 1) {}
    for (;;)
    {
      this.isWeatherApp = bool;
      return;
      bool = false;
    }
  }
  
  private void initStatisticalSDK()
  {
    Object localObject;
    HashMap localHashMap;
    boolean bool;
    if (this.isMainProcess)
    {
      localObject = UmengEvent.getInstance();
      if (!this.isWeatherApp) {
        break label450;
      }
      str = "5a211351f43e487c2500028b";
      ((UmengEvent)localObject).init(this.mAppContext, this.mAppContext.getPackageName(), str);
      StatisticalManager.getInstance().addEventAble((AbsEventAble)localObject);
      ((UmengEvent)localObject).sendEvent(this.mAppContext, "first_event");
      FacebookEvent.getInstance().init(this, getResources().getString(R.string.facebook_app_id));
      StatisticalManager.getInstance().addEventAble(FacebookEvent.getInstance());
      localObject = FirebaseEvent.getInstance(this.mAppContext);
      localHashMap = new HashMap();
      localHashMap.put("using_locker", String.valueOf(LockerSetting.isMyLockerOpened(this.mAppContext)));
      localHashMap.put("first_open_time", String.valueOf(AppUseInfo.getInstance(this.mAppContext).getInstallTime(this.mAppContext)));
      if (WidgetUtils.getWidgetCount(this.mAppContext) <= 0) {
        break label457;
      }
      bool = true;
      label154:
      localHashMap.put("using_widget", String.valueOf(bool));
      if (!this.isWeatherApp) {
        break label462;
      }
      str = "weather";
      label176:
      localHashMap.put("app_status", str);
      if (AppUseInfo.getInstance(this.mAppContext).getOpenCount(this.mAppContext) != 1) {
        break label469;
      }
    }
    label450:
    label457:
    label462:
    label469:
    for (String str = "true";; str = "false")
    {
      localHashMap.put("newUser", String.valueOf(str));
      localHashMap.put("has_update", String.valueOf(CommonUtils.hasUpdateApp(this.mAppContext)));
      localHashMap.put("is_open_morning_report", String.valueOf(ReportManger.isMorningReportOpen(this.mAppContext)));
      localHashMap.put("is_open_night_report", String.valueOf(ReportManger.isEveningReportOpen(this.mAppContext)));
      ((FirebaseEvent)localObject).updateUserPropertys(this.mAppContext, localHashMap);
      FirebaseEvent.getInstance(this).addEventController(new EventControllerAlways(Arrays.asList(new String[] { "main_first_open", "theme_first_open", "active_24h", "A_open_CurrentDetail", "A_open_HourlyDetail", "A_open_DailyDetail", "news_detail_open", "change_1h_float_show", "push_warning_float_show", "report_morning_show", "report_night_show", "open_AddWidgetActivity", "A_open_setting", "radar_detail_first_load", "store_click", "item_click", "saver_main_show", "dev_zh_location_geo_start", "dev_zh_location_geo_success", "dev_zh_search_geo_start", "dev_zh_search_geo_success", "referrer" })));
      StatisticalManager.getInstance().addEventAble((AbsEventAble)localObject);
      return;
      str = "586f943b6e27a4280e00191e";
      break;
      bool = false;
      break label154;
      str = "lwp";
      break label176;
    }
  }
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(paramContext);
    this.mAppContext = this;
    initAppStatus();
  }
  
  public WidgetStatusManager.MainWidgetController getWidgetController()
  {
    new WidgetStatusManager.MainWidgetController()
    {
      public String getMainWidget()
      {
        return BaseApplication.this.mAppContext.getPackageName();
      }
      
      public boolean isMainWidget()
      {
        return BaseApplication.this.isWeatherApp;
      }
      
      public void setMainWidget(String paramAnonymousString) {}
    };
  }
  
  public void goCurrentDetailActivity(Context paramContext, String paramString)
  {
    paramString = new Intent(paramContext, HomeActivity.class);
    paramString.setFlags(268435456);
    AbsMainActivity.FromPop.addFromWhere(paramString, "extra_from_aqi_pop");
    paramContext.startActivity(paramString);
  }
  
  public void goDailyDetailActivity(Context paramContext, String paramString)
  {
    paramString = new Intent(paramContext, DailyActivity.class);
    Intent localIntent = new Intent(paramContext, HomeActivity.class);
    localIntent.setFlags(268435456);
    AbsMainActivity.FromPop.addFromWhere(localIntent, "extra_from_temp_change");
    paramContext.startActivities(new Intent[] { localIntent, paramString });
  }
  
  public void goHourlyDetailActivity(Context paramContext, String paramString)
  {
    paramString = new Intent(paramContext, HourlyActivity.class);
    Intent localIntent = new Intent(paramContext, HomeActivity.class);
    localIntent.setFlags(268435456);
    AbsMainActivity.FromPop.addFromWhere(localIntent, "extra_from_change_one_hourly");
    paramContext.startActivities(new Intent[] { localIntent, paramString });
  }
  
  public void goMainWeatherActivityFromNewsPop(Context paramContext, int paramInt, String paramString1, String paramString2)
  {
    Intent localIntent = new Intent(paramContext, HomeActivity.class);
    localIntent.addFlags(268435456);
    localIntent.addFlags(67108864);
    localIntent.putExtra("news_push_form_extra", 1);
    localIntent.putExtra("extra_news_title", paramString2);
    localIntent.putExtra("extra_news_url", paramString1);
    AbsMainActivity.FromPop.addFromWhere(localIntent, "extra_from_warning_pop");
    paramContext.startActivity(localIntent);
  }
  
  public void goMainWeatherActivityFromWarningPop(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (paramContext == null) {
      return;
    }
    paramString = new Intent(paramContext, WeatherWarnActivity.class);
    Intent localIntent = new Intent(paramContext, HomeActivity.class);
    localIntent.setFlags(268435456);
    AbsMainActivity.FromPop.addFromWhere(localIntent, "extra_from_warning_pop");
    paramContext.startActivities(new Intent[] { localIntent, paramString });
  }
  
  public void initDefaultUnit()
  {
    if (AppUseInfo.getInstance(this).getOpenCount(this) < 1)
    {
      SDKContext.getInstance().setWeatherUnitDefault(new IUnitDeafult()
      {
        public String getClock24(Context paramAnonymousContext)
        {
          return "跟随系统";
        }
        
        public String getDistance(Context paramAnonymousContext)
        {
          return "Km";
        }
        
        public String getPrec(Context paramAnonymousContext)
        {
          return "mm";
        }
        
        public String getPres(Context paramAnonymousContext)
        {
          return "kPa";
        }
        
        public String getSpeed(Context paramAnonymousContext)
        {
          return "mph";
        }
        
        public String getTemp(Context paramAnonymousContext)
        {
          return "℃";
        }
        
        public int getVersion(Context paramAnonymousContext)
        {
          return 1;
        }
      });
      SDKContext.getInstance().getSDKConfig().setAutoUpdateWeatherConfig(this, 10800000L);
    }
  }
  
  public void onBackListener()
  {
    try
    {
      Intent localIntent = new Intent(this, HomeActivity.class);
      localIntent.addFlags(268435456);
      startActivity(localIntent);
      return;
    }
    catch (Exception localException) {}
  }
  
  public void onCreate()
  {
    super.onCreate();
    BaseWidgetManager.setMainActivityClass(HomeActivity.class);
    WeatherData.setWeatherIconAdapter(new WeatherIconAdapter());
    initStatisticalSDK();
    AmberAdSdk.getInstance().initSDK(this, getString(R.string.amber_ad_app_id), AppUseInfo.getInstance(this.mAppContext).getInstallTime(), false);
    if (this.isWeatherApp)
    {
      BaseWidgetManager.init(this.mAppContext, HomeActivity.class, AmberWidgetStoreActivity.class, "a1b0891ffa", "", this);
      return;
    }
    BaseLwpManger.init(this);
  }
}
