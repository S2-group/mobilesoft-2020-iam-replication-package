package com.amber.lib.common_library;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import com.amber.lib.appuser.AppUseInfo;
import com.amber.lib.basewidget.AbsMainActivity.FromPop;
import com.amber.lib.basewidget.BaseWidgetManager;
import com.amber.lib.basewidget.NewsActivity;
import com.amber.lib.basewidget.WeatherBaseApplication;
import com.amber.lib.basewidget.otheractivity.WeatherWarnActivity;
import com.amber.lib.basewidget.pop.popinterface.IClickPopInterface;
import com.amber.lib.common_library.daily.DailyActivity;
import com.amber.lib.common_library.home.HomeActivity;
import com.amber.lib.common_library.hourly.HourlyActivity;
import com.amber.lib.common_library.utils.CommonUtils;
import com.amber.lib.common_library.utils.WeatherIconAdapter;
import com.amber.lib.device.DeviceId;
import com.amber.lib.net.NetUtil;
import com.amber.lib.report.ReportManger;
import com.amber.lib.statistical.AbsEventAble;
import com.amber.lib.statistical.StatisticalManager;
import com.amber.lib.statistical.amazon.AmazonEvent;
import com.amber.lib.statistical.firebase.FirebaseEvent;
import com.amber.lib.statistical.umeng.UmengEvent;
import com.amber.lib.store.activity.WidgetStoreActivity;
import com.amber.lib.store.adapter.LockerSetting;
import com.amber.lib.tools.AppUtil;
import com.amber.lib.tools.ToolUtils;
import com.amber.lib.weatherdata.core.SDKContext;
import com.amber.lib.weatherdata.core.config.SDKConfigManager;
import com.amber.lib.weatherdata.core.module.weather.IUnitDeafult;
import com.amber.lib.weatherdata.core.module.weather.WeatherData;
import com.amber.lib.widget.WidgetUtils;
import com.amber.lib.widget.status.WidgetStatusManager.MainWidgetController;
import com.amberweather.sdk.amberadsdk.AmberAdSdk;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
    Object localObject2;
    Object localObject1;
    boolean bool;
    if (this.isMainProcess)
    {
      localObject2 = UmengEvent.getInstance();
      if (!this.isWeatherApp) {
        break label538;
      }
      localObject1 = "5a211351f43e487c2500028b";
      ((UmengEvent)localObject2).init(this.mAppContext, this.mAppContext.getPackageName(), (String)localObject1);
      StatisticalManager.getInstance().addEventAble((AbsEventAble)localObject2);
      ((UmengEvent)localObject2).sendEvent(this.mAppContext, "first_event");
      localObject2 = FirebaseEvent.getInstance(this.mAppContext);
      HashMap localHashMap = new HashMap();
      localHashMap.put("using_locker", String.valueOf(LockerSetting.isMyLockerOpened(this.mAppContext)));
      localHashMap.put("first_open_time", String.valueOf(AppUseInfo.getInstance(this.mAppContext).getInstallTime(this.mAppContext)));
      if (WidgetUtils.getWidgetCount(this.mAppContext) <= 0) {
        break label545;
      }
      bool = true;
      label127:
      localHashMap.put("using_widget", String.valueOf(bool));
      if (!this.isWeatherApp) {
        break label550;
      }
      localObject1 = "weather";
      label149:
      localHashMap.put("app_status", localObject1);
      if (AppUseInfo.getInstance(this.mAppContext).getOpenCount(this.mAppContext) != 1) {
        break label557;
      }
      localObject1 = "true";
      label179:
      localHashMap.put("newUser", String.valueOf(localObject1));
      localHashMap.put("has_update", String.valueOf(CommonUtils.hasUpdateApp(this.mAppContext)));
      localHashMap.put("is_open_morning_report", String.valueOf(ReportManger.isMorningReportOpen(this.mAppContext)));
      localHashMap.put("is_open_night_report", String.valueOf(ReportManger.isEveningReportOpen(this.mAppContext)));
      ((FirebaseEvent)localObject2).updateUserPropertys(this.mAppContext, localHashMap);
      StatisticalManager.getInstance().addEventAble((AbsEventAble)localObject2);
      localObject1 = AmazonEvent.getInstance(this.mAppContext);
      localObject2 = new HashMap();
      ((HashMap)localObject2).put("device_id", DeviceId.getDeviceId(this.mAppContext));
      ((HashMap)localObject2).put("app_type", "mul_widget");
      ((HashMap)localObject2).put("first_open_time", String.valueOf(AppUseInfo.getInstance(this.mAppContext).getInstallTime(this.mAppContext)));
      ((HashMap)localObject2).put("type", "widget");
      ((HashMap)localObject2).put("app_pkg", this.mAppContext.getPackageName());
      ((HashMap)localObject2).put("language", Locale.getDefault().getLanguage());
      ((HashMap)localObject2).put("android_id", DeviceId.getDeviceAndroidID(this.mAppContext));
      ((HashMap)localObject2).put("app_ver", String.valueOf(AppUtil.getVersionCode(this.mAppContext)));
      ((HashMap)localObject2).put("brand_name", Build.BRAND);
      ((HashMap)localObject2).put("model_name", Build.MODEL);
      ((HashMap)localObject2).put("net_state", NetUtil.getNetTypeName(this.mAppContext));
      ((HashMap)localObject2).put("pid", String.valueOf(Process.myPid()));
      ((HashMap)localObject2).put("sdk_ver", String.valueOf(Build.VERSION.SDK_INT));
      if (!this.isWeatherApp) {
        break label564;
      }
      ((HashMap)localObject2).put("app_status", "true");
    }
    for (;;)
    {
      ((HashMap)localObject2).put("screen_w", String.valueOf(ToolUtils.getDisplayWidthPixels(this.mAppContext)));
      ((HashMap)localObject2).put("screen_h", String.valueOf(ToolUtils.getDisplayHeightPixels(this.mAppContext)));
      ((AmazonEvent)localObject1).addBaseMap((Map)localObject2);
      ((AmazonEvent)localObject1).init(this.mAppContext, "2a87d07bd91f48989b270c757a1b60b0", "us-east-1:830e7cdc-3e7a-41c6-814e-151328110358");
      StatisticalManager.getInstance().addEventAble((AbsEventAble)localObject1);
      return;
      label538:
      localObject1 = "586f943b6e27a4280e00191e";
      break;
      label545:
      bool = false;
      break label127;
      label550:
      localObject1 = "lwp";
      break label149;
      label557:
      localObject1 = "false";
      break label179;
      label564:
      ((HashMap)localObject2).put("app_status", "false");
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
    Intent localIntent1 = new Intent(paramContext, HomeActivity.class);
    localIntent1.setFlags(268435456);
    Intent localIntent2 = new Intent(paramContext, NewsActivity.class);
    localIntent2.putExtra("news_push_form_extra", 1);
    localIntent2.putExtra("extra_news_title", paramString2);
    localIntent2.putExtra("extra_news_url", paramString1);
    AbsMainActivity.FromPop.addFromWhere(localIntent1, "extra_from_warning_pop");
    paramContext.startActivities(new Intent[] { localIntent1, localIntent2 });
  }
  
  public void goMainWeatherActivityFromWarningPop(Context paramContext, String paramString, boolean paramBoolean)
  {
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
  
  public void onCreate()
  {
    super.onCreate();
    BaseWidgetManager.setMainActivityClass(HomeActivity.class);
    WeatherData.setWeatherIconAdapter(new WeatherIconAdapter());
    initStatisticalSDK();
    AmberAdSdk.getInstance().initSDK(this, getString(R.string.amber_ad_app_id), AppUseInfo.getInstance(this.mAppContext).getInstallTime(), false);
    if (this.isWeatherApp)
    {
      BaseWidgetManager.init(this.mAppContext, HomeActivity.class, WidgetStoreActivity.class, "a1b0891ffa", "", this);
      return;
    }
    BaseLwpManger.init(this);
  }
}
