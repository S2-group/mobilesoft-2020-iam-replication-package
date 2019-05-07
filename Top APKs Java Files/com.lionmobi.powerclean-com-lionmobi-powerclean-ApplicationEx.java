package com.lionmobi.powerclean;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Process;
import com.a.b.d;
import com.facebook.ads.i;
import com.flurry.android.FlurryAgent;
import com.github.anrwatchdog.ANRWatchDog;
import com.lionmobi.powerclean.model.b.ay;
import com.lionmobi.powerclean.model.b.az;
import com.lionmobi.powerclean.model.bean.a;
import com.lionmobi.powerclean.model.bean.ac;
import com.lionmobi.powerclean.quietnotifications.r;
import com.lionmobi.util.DB_PW_Assistant;
import com.lionmobi.util.ae;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ApplicationEx
  extends Application
{
  public static int b = 0;
  public static boolean c = false;
  public static boolean d = false;
  public static String e = "uninstall_residual_Cache";
  public static boolean f = false;
  public static boolean g = false;
  public static boolean h = false;
  public static int i = 0;
  public static int j = 0;
  public static int k = 0;
  public static String l = "";
  public static String m = "";
  public static long o = -1L;
  public static boolean p = false;
  private static ApplicationEx r;
  private HashSet A = null;
  private Set B = null;
  private int C = 0;
  private int D = 0;
  private int E = 0;
  private long F = 0L;
  private boolean G = false;
  private boolean H = false;
  private boolean I = false;
  private a J = null;
  private List K = null;
  private ac L = null;
  private boolean M = false;
  private List N = null;
  private com.lionmobi.powerclean.model.bean.c O = null;
  private long P = 0L;
  private Map Q = null;
  private boolean R = true;
  private List S = null;
  private long T = 0L;
  private int U = 0;
  private int V = 0;
  private i W;
  private List X;
  DB_PW_Assistant a = DB_PW_Assistant.getInstance();
  Activity n = null;
  private boolean q = false;
  private List s;
  private List t = new ArrayList();
  private List u;
  private boolean v = false;
  private List w = null;
  private long x = 0L;
  private long y = 300000L;
  private List z = null;
  
  public ApplicationEx() {}
  
  public static boolean canBoost()
  {
    return (!p) && (System.currentTimeMillis() - o > 120000L);
  }
  
  public static void closeFloatWindow()
  {
    ay localAy = new ay();
    de.greenrobot.event.c.getDefault().post(localAy);
  }
  
  public static ApplicationEx getInstance()
  {
    return r;
  }
  
  public static void showInstalledAppDetails(Context paramContext, String paramString, int paramInt)
  {
    Intent localIntent = new Intent();
    localIntent.setFlags(268435456);
    int i1 = Build.VERSION.SDK_INT;
    if (i1 >= 9)
    {
      localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
      localIntent.setData(Uri.fromParts("package", paramString, null));
      if ((paramContext != null) && (!((Activity)paramContext).isFinishing()))
      {
        paramContext.startActivity(localIntent);
        if (paramInt > 0) {
          startFloatWindow(paramInt);
        }
      }
      return;
    }
    if (i1 == 8) {}
    for (String str = "pkg";; str = "com.android.settings.ApplicationPkgName")
    {
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
      localIntent.putExtra(str, paramString);
      break;
    }
  }
  
  public static void startFloatWindow(int paramInt)
  {
    az localAz = new az();
    localAz.setFlag(paramInt);
    de.greenrobot.event.c.getDefault().post(localAz);
  }
  
  public List getAlbumList()
  {
    return this.N;
  }
  
  public com.lionmobi.powerclean.model.bean.c getAppCacheItem()
  {
    return this.O;
  }
  
  public long getAppInstallTime()
  {
    try
    {
      long l1 = getPackageManager().getPackageInfo(getPackageName(), 0).firstInstallTime;
      return l1;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return 0L;
  }
  
  public List getAppLovinNativeAds()
  {
    return this.X;
  }
  
  public SharedPreferences getGlobalSettingPreference()
  {
    return getSharedPreferences("com.lionmobi.powerclean_preferences", 0);
  }
  
  public Map getInstallAppMap()
  {
    return this.Q;
  }
  
  public long getJunkClearInterval()
  {
    return this.y;
  }
  
  public List getJunks()
  {
    return this.z;
  }
  
  public long getLastCleanSystemCacheTime()
  {
    return this.F;
  }
  
  public long getLastScanTime()
  {
    return this.x;
  }
  
  public Set getLeftScanPathList()
  {
    return this.B;
  }
  
  public List getMovedAppList()
  {
    return this.u;
  }
  
  public HashSet getObserverApkList()
  {
    if (this.A == null) {
      this.A = new HashSet();
    }
    return this.A;
  }
  
  public List getPreinstallList()
  {
    return this.s;
  }
  
  public SharedPreferences getQNSharePreference()
  {
    return r.getPreference(getApplicationContext());
  }
  
  public i getResultAd()
  {
    return this.W;
  }
  
  public Activity getScreenSaverActivity()
  {
    return this.n;
  }
  
  public List getSystemCacheList()
  {
    return this.t;
  }
  
  public boolean is12HourClock()
  {
    boolean bool = false;
    if (getGlobalSettingPreference().getBoolean("time_format", false)) {
      bool = true;
    }
    return bool;
  }
  
  public boolean isBasePostSuccess()
  {
    SharedPreferences localSharedPreferences = getGlobalSettingPreference();
    if (localSharedPreferences.contains("base_post_success")) {
      return localSharedPreferences.getBoolean("base_post_success", false);
    }
    return true;
  }
  
  public boolean isBasePostingData()
  {
    return this.H;
  }
  
  public boolean isCelsius()
  {
    return getGlobalSettingPreference().getString("temp_type", "0").equals("0");
  }
  
  public boolean isHeartBeatPostingData()
  {
    return this.I;
  }
  
  public boolean isMainPostSuccess()
  {
    SharedPreferences localSharedPreferences = getGlobalSettingPreference();
    if (localSharedPreferences.contains("main_post_success")) {
      return localSharedPreferences.getBoolean("main_post_success", false);
    }
    return true;
  }
  
  public boolean isPostingData()
  {
    return this.G;
  }
  
  public boolean isServiceRunning()
  {
    return this.q;
  }
  
  public void onCreate()
  {
    super.onCreate();
    r = this;
    this.q = false;
    this.M = false;
    FlurryAgent.setLogEnabled(ae.isLogEnabled());
    FlurryAgent.init(this, "SCZJYFTRTJ5VZTMM39S8");
    FlurryAgent.setLogEvents(true);
    com.lionmobi.powerclean.b.d.a = this.a.mgetdbkey();
    com.lionmobi.powerclean.b.d.b = this.a.mgetdbvi();
    com.lionmobi.util.fontIcon.b.init(getAssets(), "lionmobi_fonticon.ttf");
    i = Integer.parseInt(getGlobalSettingPreference().getString("temp_value", "60"));
    try
    {
      localObject1 = getPackageManager().getInstalledPackages(64).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = ((PackageInfo)((Iterator)localObject1).next()).applicationInfo.sourceDir;
        if ((!((String)localObject2).startsWith("/data/app")) && (!((String)localObject2).startsWith("/system/app")) && (!((String)localObject2).startsWith("/system/framework")) && (!((String)localObject2).startsWith("/system/priv-app"))) {
          getGlobalSettingPreference().edit().putBoolean("support_app2sd", true).commit();
        }
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        try
        {
          Object localObject1;
          Object localObject2;
          int i1;
          Class.forName("com.github.anrwatchdog.ANRWatchDog");
          new ANRWatchDog().start();
          return;
        }
        catch (ClassNotFoundException localClassNotFoundException) {}
        localException = localException;
        localException.printStackTrace();
        continue;
        String str = "";
      }
    }
    com.lionmobi.powerclean.quietnotifications.a.b.init(getApplicationContext());
    i1 = getGlobalSettingPreference().getInt("numUsePowerClean", 0);
    if (i1 < 3) {
      getGlobalSettingPreference().edit().putInt("numUsePowerClean", i1 + 1).commit();
    }
    i1 = Process.myPid();
    localObject1 = ((ActivityManager)getSystemService("activity")).getRunningAppProcesses().iterator();
    for (;;)
    {
      if (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject1).next();
        if (((ActivityManager.RunningAppProcessInfo)localObject2).pid == i1)
        {
          localObject1 = ((ActivityManager.RunningAppProcessInfo)localObject2).processName;
          if ("com.lionmobi.powerclean".equals(localObject1))
          {
            localObject1 = getGlobalSettingPreference();
            if (!((SharedPreferences)localObject1).contains("first_launch_time"))
            {
              l1 = System.currentTimeMillis();
              localObject2 = ((SharedPreferences)localObject1).edit();
              ((SharedPreferences.Editor)localObject2).putLong("first_launch_time", l1);
              ((SharedPreferences.Editor)localObject2).commit();
            }
            long l1 = ((SharedPreferences)localObject1).getLong("first_launch_time", 0L);
            com.lionmobi.c.a.b.getInstance(this).setFirstLaunch(l1);
            com.lionmobi.c.a.b.getInstance(this).getAdPriorityData();
          }
          if (getGlobalSettingPreference().getBoolean("isNeedCleanDot", true))
          {
            getGlobalSettingPreference().edit().putBoolean("isNeedCleanDot", false).commit();
            com.lionmobi.util.b.c.removeCount(this);
          }
        }
      }
    }
  }
  
  public void onLowMemory()
  {
    super.onLowMemory();
    d.clearCache();
  }
  
  public void onTerminate()
  {
    super.onTerminate();
  }
  
  public void setAppLovinNativeAds(List paramList)
  {
    this.X = paramList;
  }
  
  public void setBasePostStatus(boolean paramBoolean)
  {
    SharedPreferences localSharedPreferences = getGlobalSettingPreference();
    if (paramBoolean)
    {
      localSharedPreferences.edit().remove("base_post_success").commit();
      return;
    }
    localSharedPreferences.edit().putBoolean("base_post_success", paramBoolean).commit();
  }
  
  public void setBasePostingData(boolean paramBoolean)
  {
    this.H = paramBoolean;
  }
  
  public void setBrowserAlbum(a paramA)
  {
    this.J = paramA;
  }
  
  public void setDBLoadingFinished(boolean paramBoolean)
  {
    this.M = paramBoolean;
  }
  
  public void setHeartBeatPostingData(boolean paramBoolean)
  {
    this.I = paramBoolean;
  }
  
  public void setInstallAppMap(Map paramMap)
  {
    this.Q = paramMap;
  }
  
  public void setJunks(List paramList)
  {
    if ((paramList == null) && (this.z != null)) {
      this.z = null;
    }
    while (this.z == paramList) {
      return;
    }
    this.z = paramList;
  }
  
  public void setLastCleanSystemCacheTime(long paramLong)
  {
    this.F = paramLong;
  }
  
  public void setLastScanTime(long paramLong)
  {
    this.x = paramLong;
  }
  
  public void setLeftScanPathList(Set paramSet)
  {
    this.B = paramSet;
  }
  
  public void setMainPostStatus(boolean paramBoolean)
  {
    SharedPreferences localSharedPreferences = getGlobalSettingPreference();
    if (paramBoolean)
    {
      localSharedPreferences.edit().remove("main_post_success").commit();
      return;
    }
    localSharedPreferences.edit().putBoolean("main_post_success", paramBoolean).commit();
  }
  
  public void setMovedAppList(List paramList)
  {
    this.u = paramList;
  }
  
  public void setPostingData(boolean paramBoolean)
  {
    this.G = paramBoolean;
  }
  
  public void setPreinstallList(List paramList)
  {
    this.s = paramList;
  }
  
  public void setResultAd(i paramI)
  {
    this.W = paramI;
  }
  
  public void setScreenSaverActivity(Activity paramActivity)
  {
    this.n = paramActivity;
  }
  
  public void setServiceRunning(boolean paramBoolean)
  {
    this.q = paramBoolean;
  }
  
  public void setSystemCacheList(List paramList)
  {
    this.t.clear();
    this.t.addAll(paramList);
  }
}
