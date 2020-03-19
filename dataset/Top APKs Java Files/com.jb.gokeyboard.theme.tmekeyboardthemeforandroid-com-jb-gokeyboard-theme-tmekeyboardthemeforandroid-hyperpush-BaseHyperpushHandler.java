package com.jb.gokeyboard.theme.tmekeyboardthemeforandroid.hyperpush;

import a.a.a.a.a.a.a;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.cc;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.RemoteViews;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jb.gokeyboard.theme.tmekeyboardthemeforandroid.InstallActivity;
import com.jb.gokeyboard.theme.tmekeyboardthemeforandroid.MainActivity;
import com.jb.gokeyboard.theme.tmekeyboardthemeforandroid.api.Api;
import com.jb.gokeyboard.theme.tmekeyboardthemeforandroid.api.NetService;
import com.jb.gokeyboard.theme.tmekeyboardthemeforandroid.api.TypeOfSettings;
import com.jb.gokeyboard.theme.tmekeyboardthemeforandroid.api.request.SettingsRequest;
import com.jb.gokeyboard.theme.tmekeyboardthemeforandroid.api.response.HyperpushResponse;
import com.jb.gokeyboard.theme.tmekeyboardthemeforandroid.themes.ThemesActivity;
import com.jb.gokeyboard.theme.tmekeyboardthemeforandroid.util.Constants;
import com.jb.gokeyboard.theme.tmekeyboardthemeforandroid.util.Global;
import com.jb.gokeyboard.theme.tmekeyboardthemeforandroid.util.PackageNameComparator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import retrofit.Callback;
import retrofit.RetrofitError;

public class BaseHyperpushHandler
{
  private static final int SECONDS_IN_AN_HOUR = 3600;
  private static final int SECONDS_IN_A_DAY = 86400;
  protected static InputMethodManager imeManager;
  private static int isZombieEffect = 0;
  private Context ctx;
  Callback<HyperpushResponse> mCallback = new BaseHyperpushHandler.3(this);
  private int mLastNotifId;
  
  protected BaseHyperpushHandler(Context paramContext)
  {
    this.ctx = paramContext;
    Context localContext1 = this.ctx;
    Context localContext2 = this.ctx;
    imeManager = (InputMethodManager)localContext1.getSystemService("input_method");
    isZombieEffect = PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("zombieeffect", 0);
  }
  
  private Intent buildMarketIntent(HyperpushItem paramHyperpushItem)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramHyperpushItem.package_name));
      return localIntent;
    }
    catch (ActivityNotFoundException localActivityNotFoundException) {}
    return new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + paramHyperpushItem.package_name));
  }
  
  private List<ApplicationInfo> checkForLaunchIntent(PackageManager paramPackageManager, List<ApplicationInfo> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramList.next();
      try
      {
        if (paramPackageManager.getLaunchIntentForPackage(localApplicationInfo.packageName) != null) {
          localArrayList.add(localApplicationInfo);
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return localArrayList;
  }
  
  public static HyperpushItem getHyperItem(int paramInt, Context paramContext)
  {
    return (HyperpushItem)a.a(paramContext, "hyperpush_seen", 0).a("key_notification_index_" + paramInt, HyperpushItem.class);
  }
  
  private void handleFailure(RetrofitError paramRetrofitError)
  {
    setAlarmAfter(3600L);
  }
  
  private boolean isOkToShow()
  {
    if (isZombieEffect == 1) {
      return true;
    }
    PackageManager localPackageManager = this.ctx.getPackageManager();
    ArrayList localArrayList = new ArrayList();
    int j = Integer.parseInt("4.172.105.88".split("\\.")[0]);
    Iterator localIterator = checkForLaunchIntent(localPackageManager, localPackageManager.getInstalledApplications(128)).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      String[] arrayOfString = Constants.PACKAGE_PREFIX;
      int k = arrayOfString.length;
      int i = 0;
      while (i < k)
      {
        Object localObject = arrayOfString[i];
        if (localApplicationInfo.packageName.startsWith((String)localObject)) {}
        try
        {
          localObject = localPackageManager.getPackageInfo(localApplicationInfo.packageName, 0).versionName;
          Log.d("package name", "" + localApplicationInfo.packageName);
          Log.d("package version name", "" + (String)localObject);
          localObject = ((String)localObject).split("\\.");
          if (localObject.length == 4)
          {
            int m = Integer.parseInt(localObject[0]);
            int n = Integer.parseInt(localObject[1]);
            if ((m <= 6) && (m >= j) && (n >= 70)) {
              localArrayList.add(localApplicationInfo.packageName);
            }
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            localNameNotFoundException.printStackTrace();
          }
        }
        i += 1;
      }
    }
    Collections.sort(localArrayList, new PackageNameComparator(Arrays.asList(new String[] { "tw", "tk", "tim", "tme", "gotme" })));
    if ((localArrayList.size() > 0) && (localArrayList.get(0) != null) && (((String)localArrayList.get(0)).equals(this.ctx.getPackageName()))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private void sendAdBehaviour(Intent paramIntent, HyperpushItem paramHyperpushItem)
  {
    if (paramHyperpushItem.interstitials_on != null) {
      paramIntent.putExtra("ad_behaviour", new GsonBuilder().create().toJson(paramHyperpushItem));
    }
  }
  
  private List<String> storeToSeenItems(String paramString)
  {
    ArrayList localArrayList = getStoreSeenItems();
    localArrayList.add(paramString);
    paramString = this.ctx.getSharedPreferences("hyperpush_seen", 0).edit();
    paramString.putString("key_seen", new Gson().toJson(localArrayList));
    paramString.commit();
    return localArrayList;
  }
  
  protected boolean arePackagesInstalled(HyperpushItem paramHyperpushItem, Context paramContext)
  {
    if (paramHyperpushItem.check_packages == null) {
      paramHyperpushItem.check_packages = new ArrayList();
    }
    paramHyperpushItem.check_packages.add(paramHyperpushItem.package_name);
    paramHyperpushItem = paramHyperpushItem.check_packages.iterator();
    while (paramHyperpushItem.hasNext()) {
      if (isPackageInstalled((String)paramHyperpushItem.next(), paramContext)) {
        return true;
      }
    }
    return false;
  }
  
  public Context getContext()
  {
    return this.ctx;
  }
  
  protected ArrayList<String> getStoreSeenItems()
  {
    Object localObject = this.ctx.getSharedPreferences("hyperpush_seen", 0).getString("key_seen", "");
    localObject = (String[])new Gson().fromJson((String)localObject, [Ljava.lang.String.class);
    if (localObject != null) {
      return new ArrayList(Arrays.asList((Object[])localObject));
    }
    return new ArrayList();
  }
  
  public void handleAlarmTriggered()
  {
    if (isOkToShow())
    {
      SettingsRequest localSettingsRequest = Global.buildSettingsRequest(this.ctx);
      NetService.getRestApi("cdn", this.ctx).getHyperpush(localSettingsRequest.tv, localSettingsRequest.t, localSettingsRequest.l, localSettingsRequest.c, localSettingsRequest.cr, localSettingsRequest.e, localSettingsRequest.ds, localSettingsRequest.dst, localSettingsRequest.s, isZombieEffect, this.mCallback);
    }
  }
  
  protected void handleSuccess(HyperpushResponse paramHyperpushResponse)
  {
    int i = PreferenceManager.getDefaultSharedPreferences(this.ctx).getInt("zombieeffect", 0);
    ArrayList localArrayList = getStoreSeenItems();
    if ((i == 1) && (paramHyperpushResponse.list.size() - 1 == localArrayList.size())) {
      PreferenceManager.getDefaultSharedPreferences(this.ctx).edit().putInt("zombieeffect", 0).commit();
    }
    setAlarmAfter(paramHyperpushResponse.frequency * 86400);
    paramHyperpushResponse = HyperpushHandler.getInst(this.ctx).getBestItem(paramHyperpushResponse);
    if (paramHyperpushResponse != null) {
      showHyperpushNotifcation(paramHyperpushResponse);
    }
  }
  
  protected boolean isCustomizationPush(HyperpushItem paramHyperpushItem)
  {
    return (paramHyperpushItem.type.equals("gobackground")) || (paramHyperpushItem.type.equals("gofont")) || (paramHyperpushItem.type.equals("gosound"));
  }
  
  protected boolean isPackageInstalled(String paramString, Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      Log.d("TAG", paramString + " exists");
      paramContext.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Log.d("TAG", paramString + " does not exists");
    }
    return false;
  }
  
  public void restartCurrentAlarm()
  {
    String str = this.ctx.getSharedPreferences("hyperpush_seen", 0).getString("key_alarm_time", null);
    if (str == null)
    {
      setAlarm();
      return;
    }
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try
    {
      setAlarm(localSimpleDateFormat.parse(str));
      return;
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
      setAlarm();
    }
  }
  
  protected int restoreLastNotifId()
  {
    return this.ctx.getSharedPreferences("hyperpush_seen", 0).getInt("key_last_notif_id", 0);
  }
  
  public void setAlarm()
  {
    setAlarm(null);
  }
  
  public void setAlarm(Date paramDate)
  {
    long l = 86400L;
    if (paramDate != null) {
      l = DateUtils.diffInSeconds(new Date(), paramDate);
    }
    setAlarmAfter(l);
  }
  
  public void setAlarmAfter(long paramLong)
  {
    Calendar localCalendar = DateUtils.setDelayNotification(null, paramLong, this.ctx);
    SharedPreferences.Editor localEditor = this.ctx.getSharedPreferences("hyperpush_seen", 0).edit();
    localEditor.putString("key_alarm_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(localCalendar.getTime()));
    localEditor.commit();
  }
  
  protected void showHyperpushNotifcation(HyperpushItem paramHyperpushItem)
  {
    if (this.ctx == null) {}
    setAlarmAfter(paramHyperpushItem.delay);
    Object localObject = RingtoneManager.getDefaultUri(2);
    cc localCc = new cc(this.ctx).b(this.ctx.getResources().getColor(2131689580)).a(2130837712).a(paramHyperpushItem.title).b(paramHyperpushItem.text).c(paramHyperpushItem.title).a(true);
    if (paramHyperpushItem.sound) {
      localCc.a((Uri)localObject);
    }
    if (paramHyperpushItem.vibrate) {
      localCc.a(new long[] { 1000L, 1000L, 1000L });
    }
    localObject = paramHyperpushItem.type;
    int i = -1;
    switch (((String)localObject).hashCode())
    {
    default: 
      switch (i)
      {
      default: 
        localObject = new Intent(this.ctx, MainActivity.class);
        label239:
        ((Intent)localObject).putExtra("hyper_type", paramHyperpushItem.type);
        sendAdBehaviour((Intent)localObject, paramHyperpushItem);
        this.mLastNotifId = restoreLastNotifId();
        ((Intent)localObject).putExtra("delay", paramHyperpushItem.delay);
        ((Intent)localObject).putExtra("key_notification_id", this.mLastNotifId);
        ((Intent)localObject).putExtra("key_hyperpush_id", paramHyperpushItem.id);
        ((Intent)localObject).putExtra("key_hyperpush_package_name", paramHyperpushItem.package_name);
        ((Intent)localObject).putExtra("SERVER_DATA_TYPE_KEY", TypeOfSettings.settings.getValue());
        storeHyperItemData(this.mLastNotifId, paramHyperpushItem);
        ((Intent)localObject).setFlags(536870912);
        localCc.a(PendingIntent.getActivity(this.ctx, 0, (Intent)localObject, 134217728));
        localObject = (NotificationManager)this.ctx.getSystemService("notification");
        if ((paramHyperpushItem.banner == null) || (paramHyperpushItem.banner.equals("")))
        {
          storeToSeenItems(paramHyperpushItem.id);
          i = this.mLastNotifId;
          this.mLastNotifId = (i + 1);
          ((NotificationManager)localObject).notify(i, localCc.a());
        }
        break;
      }
      break;
    }
    for (;;)
    {
      storeLastNotifId(this.mLastNotifId);
      return;
      if (!((String)localObject).equals("install")) {
        break;
      }
      i = 0;
      break;
      if (!((String)localObject).equals("themes")) {
        break;
      }
      i = 1;
      break;
      if (!((String)localObject).equals("wallpapers")) {
        break;
      }
      i = 2;
      break;
      if (!((String)localObject).equals("gofont")) {
        break;
      }
      i = 3;
      break;
      if (!((String)localObject).equals("gosound")) {
        break;
      }
      i = 4;
      break;
      if (!((String)localObject).equals("gobackground")) {
        break;
      }
      i = 5;
      break;
      localObject = new Intent(this.ctx, InstallActivity.class);
      break label239;
      localObject = new Intent(this.ctx, ThemesActivity.class);
      break label239;
      if ((paramHyperpushItem.big_banner != null) && (!paramHyperpushItem.big_banner.equals(""))) {
        new BaseHyperpushHandler.1(this, localCc, (NotificationManager)localObject).execute(new String[] { paramHyperpushItem.big_banner, paramHyperpushItem.id });
      } else {
        new BaseHyperpushHandler.2(this, new RemoteViews(this.ctx.getPackageName(), 2130968618), localCc, (NotificationManager)localObject).execute(new String[] { paramHyperpushItem.banner, paramHyperpushItem.id });
      }
    }
  }
  
  public void storeHyperItemData(int paramInt, HyperpushItem paramHyperpushItem)
  {
    a localA = a.a(this.ctx, "hyperpush_seen", 0);
    localA.a("key_notification_index_" + paramInt, paramHyperpushItem);
    localA.a();
  }
  
  protected void storeLastNotifId(int paramInt)
  {
    SharedPreferences.Editor localEditor = this.ctx.getSharedPreferences("hyperpush_seen", 0).edit();
    localEditor.putInt("key_last_notif_id", paramInt);
    localEditor.commit();
  }
}
