package com.tencent.qqlive.ona.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.tencent.qqlive.ona.appconfig.AppConfig;
import com.tencent.qqlive.ona.logreport.MTAReport;
import com.tencent.qqlive.ona.utils.AppUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class av
  implements aq
{
  private static boolean a = false;
  private static av b;
  
  private av()
  {
    an.a(this);
  }
  
  public static av a()
  {
    try
    {
      if (b == null) {
        b = new av();
      }
      av localAv = b;
      return localAv;
    }
    finally {}
  }
  
  private boolean c()
  {
    if (a) {}
    int i;
    do
    {
      return false;
      i = AppUtils.getAppSharedPreferences().getInt("last_report_time", 0);
    } while ((int)(System.currentTimeMillis() / 1000L - i) < 604800);
    return true;
  }
  
  public void b()
  {
    List localList = Arrays.asList(AppConfig.getConfig("competitive_app_package_name_list", "com.qiyi.video;com.youku.phone;com.sohu.sohuvideo;com.storm.smart;com.pplive.androidphone;com.tudou.android;com.meitu.meipaimv;com.letv.android.client;tv.pps.mobile;com.baidu.video;com.hunantv.imgo.activity;com.duowan.mobile;com.moretv.android;com.togic.livevideo;tv.danmaku.bili;com.elinkway.tvlive2;air.fyzb3;air.tv.douyu.android;com.funshion.video.mobile").split(";"));
    Object localObject = QQLiveApplication.a().getPackageManager().getInstalledPackages(0);
    StringBuilder localStringBuilder = new StringBuilder();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (localList.contains(localPackageInfo.packageName))
      {
        localStringBuilder.append(localPackageInfo.packageName);
        localStringBuilder.append(",");
        localStringBuilder.append(localPackageInfo.versionCode);
        localStringBuilder.append(";");
      }
    }
    MTAReport.reportUserEvent("competitive_app_package_name_list", new String[] { "content", localStringBuilder.toString() });
    AppUtils.setValueToPrefrences("last_report_time", (int)(System.currentTimeMillis() / 1000L));
    a = true;
  }
  
  public void onSwitchBackground(Context paramContext)
  {
    try
    {
      if (c()) {
        b();
      }
      return;
    }
    finally {}
  }
  
  public void onSwitchFront(Context paramContext) {}
}
