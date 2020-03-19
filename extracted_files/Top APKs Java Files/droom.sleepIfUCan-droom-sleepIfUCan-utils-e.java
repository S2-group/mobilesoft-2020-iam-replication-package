package droom.sleepIfUCan.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.hardware.display.DisplayManager;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.LocaleList;
import android.os.PowerManager;
import android.os.Process;
import android.provider.MediaStore.Audio.Media;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;
import com.aerserv.sdk.AerServSdk;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.crashlytics.android.Crashlytics;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.d;
import com.mobvista.msdk.MobVistaSDK;
import com.mobvista.msdk.out.MobVistaSDKFactory;
import com.mobvista.msdk.system.MobVistaSDKImpl;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.ae;
import droom.sleepIfUCan.db.b;
import droom.sleepIfUCan.db.model.Alarm;
import droom.sleepIfUCan.db.model.Alarm.a;
import droom.sleepIfUCan.db.model.Alarm.b;
import droom.sleepIfUCan.db.model.HelloBotHoroscope;
import droom.sleepIfUCan.db.model.Horoscope;
import droom.sleepIfUCan.db.model.c;
import droom.sleepIfUCan.db.model.k;
import droom.sleepIfUCan.internal.AlarmReceiver;
import droom.sleepIfUCan.view.a.aq;
import droom.sleepIfUCan.view.fragment.cv;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;

public class e
{
  public static aq a;
  private static int b = -1;
  private static CountDownTimer c;
  private static Locale d;
  
  public static String A()
  {
    Object localObject = "B,923f886c657b4d2e9bf58fc3a2900c57";
    try
    {
      String str = com.google.firebase.remoteconfig.a.a().a("an_main_native_config");
      localObject = str;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Crashlytics.logException(localException);
      }
    }
    s.a("CommonUtils", "getMainNativeConfig:" + (String)localObject);
    return localObject;
  }
  
  public static boolean A(Context paramContext)
  {
    return paramContext.getSharedPreferences("tutorial", 0).getBoolean("notification", false);
  }
  
  public static void B(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("tutorial", 0).edit();
    paramContext.putBoolean("notification", true);
    paramContext.apply();
  }
  
  public static boolean B()
  {
    boolean bool1 = false;
    try
    {
      boolean bool2 = com.google.firebase.remoteconfig.a.a().b("an_panel_cleanview_tooltip_enabled");
      bool1 = bool2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Crashlytics.logException(localException);
      }
    }
    s.a("CommonUtils", "panel_cleanview_tooltip_enabled config:" + bool1);
    return bool1;
  }
  
  public static int C(Context paramContext)
  {
    paramContext = paramContext.getResources();
    String str = paramContext.getResourceEntryName(2131558400);
    s.a("CommonUtils", "large notification name: " + str);
    int i = paramContext.getIdentifier(str, "mipmap", "droom.sleepIfUCan");
    if (i != 0) {
      return i;
    }
    return 2131558400;
  }
  
  public static String C()
  {
    Object localObject = "e097a8062c2144d4b8a554ef3832ecbb";
    try
    {
      String str = com.google.firebase.remoteconfig.a.a().a("an_exit_banner_ad_id");
      localObject = str;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Crashlytics.logException(localException);
      }
    }
    s.a("CommonUtils", "getExitBannerIdConfig:" + (String)localObject);
    return localObject;
  }
  
  public static Uri D(Context paramContext)
  {
    paramContext = paramContext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[] { "_id", "_data", "title" }, null, null, "title ASC");
    paramContext.moveToPosition((int)(Math.random() * paramContext.getCount()));
    droom.sleepIfUCan.db.model.o localO = new droom.sleepIfUCan.db.model.o(paramContext.getString(paramContext.getColumnIndex("title")), Uri.withAppendedPath(MediaStore.Audio.Media.getContentUriForPath(paramContext.getString(paramContext.getColumnIndex("_data"))), paramContext.getString(paramContext.getColumnIndex("_id"))));
    if ((paramContext != null) && (!paramContext.isClosed())) {
      paramContext.close();
    }
    return localO.b();
  }
  
  public static boolean D()
  {
    boolean bool1 = false;
    try
    {
      boolean bool2 = com.google.firebase.remoteconfig.a.a().b("an_panel_native_title_enabled");
      bool1 = bool2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Crashlytics.logException(localException);
      }
    }
    s.a("CommonUtils", "panel_native_title_enabled_config:" + bool1);
    return bool1;
  }
  
  public static Uri E(Context paramContext)
  {
    Object localObject = new RingtoneManager(paramContext);
    ((RingtoneManager)localObject).setType(7);
    paramContext = ((RingtoneManager)localObject).getCursor();
    paramContext.moveToPosition((int)(Math.random() * paramContext.getCount()));
    localObject = ((RingtoneManager)localObject).getRingtoneUri(paramContext.getPosition());
    if ((paramContext != null) && (!paramContext.isClosed())) {
      paramContext.close();
    }
    return localObject;
  }
  
  public static boolean E()
  {
    boolean bool1 = false;
    try
    {
      boolean bool2 = com.google.firebase.remoteconfig.a.a().b("an_panel_native_cta_btn_enabled");
      bool1 = bool2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Crashlytics.logException(localException);
      }
    }
    s.a("CommonUtils", "panel_native_cta_btn_enabled config:" + bool1);
    return bool1;
  }
  
  public static int F()
  {
    int i = 0;
    try
    {
      double d1 = com.google.firebase.remoteconfig.a.a().c("an_rating_dialog_policy");
      i = (int)d1;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Crashlytics.logException(localException);
      }
    }
    s.a("CommonUtils", "ratingDialogPolicy:" + i);
    return i;
  }
  
  public static boolean F(Context paramContext)
  {
    boolean bool = false;
    int i = paramContext.getSharedPreferences("pref_last_open_date", 0).getInt("pref_internal_version", 200);
    s.a("migrateTest", "origin version is " + i);
    if (i <= 270) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean G()
  {
    boolean bool1 = true;
    try
    {
      boolean bool2 = com.google.firebase.remoteconfig.a.a().b("an_exit_banner_policy_enabled");
      bool1 = bool2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Crashlytics.logException(localException);
      }
    }
    s.a("CommonUtils", "exit banner re-request policy enabled? :" + bool1);
    return bool1;
  }
  
  public static boolean G(Context paramContext)
  {
    boolean bool2 = false;
    int i = paramContext.getSharedPreferences("dataMigration", 0).getInt("fromVersion", -1);
    s.a("CommonUtils", "wasOldVersionBeta, fromVersion:" + i);
    boolean bool1 = bool2;
    if (i != -1)
    {
      bool1 = bool2;
      if (i < 2911) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static String H()
  {
    Object localObject = "Get more out of mornings";
    try
    {
      String str = com.google.firebase.remoteconfig.a.a().a("an_tutorial_title");
      localObject = str;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Crashlytics.logException(localException);
      }
    }
    s.a("CommonUtils", "tutorialConfig title:" + (String)localObject);
    return localObject;
  }
  
  public static ArrayList<String> H(Context paramContext)
  {
    int i = 0;
    Object localObject = paramContext.getSharedPreferences("barcodeList", 0);
    int j = ((SharedPreferences)localObject).getInt("index", 0);
    paramContext = new ArrayList(j);
    while (i < j)
    {
      paramContext.add(i, ((SharedPreferences)localObject).getString("elements" + i, null));
      i += 1;
    }
    localObject = paramContext.iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      s.a("CommonUtils", "barcode : " + str);
    }
    return paramContext;
  }
  
  public static String I()
  {
    Object localObject = "Users wake up around 1 hour earlier when they use mission alarms. \\nTry it on your next morning alarm!";
    try
    {
      String str = com.google.firebase.remoteconfig.a.a().a("an_tutorial_content");
      localObject = str;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Crashlytics.logException(localException);
      }
    }
    s.a("CommonUtils", "tutorialConfig content:" + (String)localObject);
    return localObject;
  }
  
  public static void I(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
    localIntent.setData(Uri.parse("package:droom.sleepIfUCan"));
    localIntent.setFlags(268435456);
    paramContext.startActivity(localIntent);
  }
  
  public static final void J(Context paramContext)
  {
    a(null);
    if (a != null) {
      a = null;
    }
    a = new aq(paramContext);
    a.show();
  }
  
  public static boolean J()
  {
    return (f()) || (i()) || (w());
  }
  
  public static boolean K()
  {
    Object localObject = "ENABLED";
    try
    {
      String str = com.google.firebase.remoteconfig.a.a().a("main_native_ad");
      localObject = str;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Crashlytics.logException(localException);
      }
    }
    return ((String)localObject).equals("ENABLED");
  }
  
  public static boolean K(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("pref_last_open_date", 0);
    int i = paramContext.getInt("pref_internal_version", -1);
    boolean bool = paramContext.getBoolean("needVolConversion", true);
    return (i < 163) && (bool);
  }
  
  public static int L(Context paramContext)
  {
    return ((AudioManager)paramContext.getSystemService("audio")).getStreamMaxVolume(3);
  }
  
  public static String L()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss", Locale.ENGLISH);
    localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    return localSimpleDateFormat.format(Calendar.getInstance().getTime()) + " GMT";
  }
  
  public static String M(Context paramContext)
  {
    Object localObject = null;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      paramContext = paramContext.versionName;
      return "[Translation_" + paramContext + "]Ln: " + Locale.getDefault().getLanguage();
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = localObject;
      }
    }
  }
  
  public static boolean M()
  {
    if (a()) {
      return TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
    }
    return false;
  }
  
  public static String N(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("First", 0);
    Object localObject = localSharedPreferences.getString("pref_uuid", null);
    paramContext = (Context)localObject;
    if (localObject == null)
    {
      paramContext = UUID.randomUUID().toString();
      localObject = localSharedPreferences.edit();
      ((SharedPreferences.Editor)localObject).putString("pref_uuid", paramContext);
      ((SharedPreferences.Editor)localObject).apply();
    }
    return paramContext;
  }
  
  public static boolean N()
  {
    try
    {
      boolean bool = com.google.firebase.remoteconfig.a.a().b("an_hellobot_enabled");
      return bool;
    }
    catch (Exception localException)
    {
      Crashlytics.logException(localException);
    }
    return true;
  }
  
  public static String O()
  {
    try
    {
      String str = com.google.firebase.remoteconfig.a.a().a("an_hellobot_link");
      return str;
    }
    catch (Exception localException)
    {
      Crashlytics.logException(localException);
    }
    return "https://chat.hellobot.kr:4443/download?utm_source=alarmy&utm_medium=android&utm_campaign=todayfortune";
  }
  
  public static String O(Context paramContext)
  {
    Object localObject1 = null;
    try
    {
      localObject2 = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      localObject1 = localObject2;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        Object localObject2;
        int i;
        long l;
        int j;
        localNameNotFoundException.printStackTrace();
      }
    }
    localObject2 = localObject1.versionName;
    i = localObject1.versionCode;
    l = paramContext.getSharedPreferences("First", 0).getLong("Date", 0L);
    j = (int)((System.currentTimeMillis() - l) / 86400000L);
    return "[ALARMY] is_Pro : " + droom.sleepIfUCan.a.a + ", is_Upgrade : " + droom.sleepIfUCan.a.b + ", version : " + (String)localObject2 + ", versionCode : " + i + ", OS:" + droom.sleepIfUCan.a.f + ", Device:" + Build.MODEL + ", Ln:" + Locale.getDefault().getLanguage() + ", TM:" + 0 + ", IS_CHN_LOCAL:" + droom.sleepIfUCan.a.c + ", UDID:" + N(paramContext) + ", UD:" + j;
  }
  
  public static LogWriter.a P(Context paramContext)
  {
    Object localObject1 = null;
    try
    {
      localObject2 = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      localObject1 = localObject2;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        Object localObject2;
        long l;
        int i;
        localNameNotFoundException.printStackTrace();
      }
    }
    localObject1 = ((PackageInfo)localObject1).versionName;
    l = paramContext.getSharedPreferences("First", 0).getLong("Date", 0L);
    i = (int)((System.currentTimeMillis() - l) / 86400000L);
    localObject2 = new LogWriter.a();
    ((LogWriter.a)localObject2).a("is_Pro", "" + droom.sleepIfUCan.a.a);
    ((LogWriter.a)localObject2).a("is_Upgrade", "" + droom.sleepIfUCan.a.b);
    ((LogWriter.a)localObject2).a("version", "" + (String)localObject1);
    ((LogWriter.a)localObject2).a("OS", "" + droom.sleepIfUCan.a.f);
    ((LogWriter.a)localObject2).a("Device", "" + Build.MODEL);
    ((LogWriter.a)localObject2).a("Ln", "" + Locale.getDefault().getLanguage());
    ((LogWriter.a)localObject2).a("TM", "0");
    ((LogWriter.a)localObject2).a("IS_CHN_LOCAL", "" + droom.sleepIfUCan.a.c);
    ((LogWriter.a)localObject2).a("UDID", "" + N(paramContext));
    ((LogWriter.a)localObject2).a("UD", "" + i);
    return localObject2;
  }
  
  public static String Q(Context paramContext)
  {
    return paramContext.getSharedPreferences("AlarmClock", 0).getString("news_country_name", null);
  }
  
  public static boolean R(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = (String)paramContext.getPackageInfo("com.android.vending", 1).applicationInfo.loadLabel(paramContext);
      if (paramContext != null)
      {
        boolean bool = paramContext.equals("Market");
        if (!bool) {
          return true;
        }
      }
      return false;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static k S(Context paramContext)
  {
    Object localObject = new d();
    String str = android.preference.PreferenceManager.getDefaultSharedPreferences(paramContext).getString(cv.a, null);
    if (str != null) {}
    int i;
    for (localObject = (ArrayList)((d)localObject).a(str, new f().b());; localObject = new ArrayList())
    {
      int j = Integer.parseInt(android.preference.PreferenceManager.getDefaultSharedPreferences(paramContext).getString(cv.b, "-1"));
      i = j;
      if (j <= 0) {
        i = 0;
      }
      if ((localObject != null) && (i > 0)) {
        break;
      }
      return null;
    }
    return (k)((ArrayList)localObject).get(i - 1);
  }
  
  public static double[] T(Context paramContext)
  {
    double[] arrayOfDouble = new double[2];
    Object localObject = S(paramContext);
    if (localObject == null)
    {
      localObject = paramContext.getSharedPreferences("gps", 0);
      paramContext = ((SharedPreferences)localObject).getString("lat", null);
      localObject = ((SharedPreferences)localObject).getString("lon", null);
      if (paramContext == null)
      {
        arrayOfDouble[0] = Double.parseDouble("-987");
        return arrayOfDouble;
      }
      arrayOfDouble[0] = Double.parseDouble(paramContext);
      arrayOfDouble[1] = Double.parseDouble((String)localObject);
      return arrayOfDouble;
    }
    arrayOfDouble[0] = ((k)localObject).b();
    arrayOfDouble[1] = ((k)localObject).c();
    return arrayOfDouble;
  }
  
  public static String U(Context paramContext)
  {
    int i = 0;
    Object localObject = null;
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("weatherInfo", 0);
    if (System.currentTimeMillis() - localSharedPreferences.getLong("weatherRefreshTime", 0L) < 10800000L) {
      i = 1;
    }
    if (i != 0) {
      s.a("CommonUtils", "getWeatherInfo is valid, time passed since cache = " + (System.currentTimeMillis() - localSharedPreferences.getLong("weatherRefreshTime", 0L)));
    }
    for (;;)
    {
      paramContext = localObject;
      if (i != 0) {
        paramContext = localSharedPreferences.getString("jsonString", null);
      }
      return paramContext;
      if (localSharedPreferences.getString("jsonString", null) == null) {
        s.a("CommonUtils", "cache is empty");
      } else {
        s.a("CommonUtils", "getWeatherInfo is invalid, time passed since cache = " + (System.currentTimeMillis() - localSharedPreferences.getLong("weatherRefreshTime", 0L)));
      }
    }
  }
  
  public static void V(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("weatherInfo", 0).edit();
    paramContext.putLong("weatherRefreshTime", 0L);
    paramContext.apply();
  }
  
  public static String W(Context paramContext)
  {
    String str = aC(paramContext).toString().toLowerCase();
    paramContext = str;
    if (str.contains("zh"))
    {
      if ((str.contains("hans")) || (str.contains("cn"))) {
        paramContext = "zh-rCN";
      }
    }
    else {
      return paramContext;
    }
    return "zh-rTW";
  }
  
  public static boolean X(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity"));
    return paramContext.getPackageManager().queryIntentActivities(localIntent, 65536).size() > 0;
  }
  
  public static boolean Y(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("tutorial", 0);
    if (k()) {
      return paramContext.getBoolean("newSecurityForXiaomi2", false);
    }
    return paramContext.getBoolean("newSecurity", false);
  }
  
  public static void Z(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("tutorial", 0).edit();
    if (k()) {
      paramContext.putBoolean("newSecurityForXiaomi2", true);
    }
    for (;;)
    {
      paramContext.apply();
      return;
      paramContext.putBoolean("newSecurity", true);
    }
  }
  
  public static float a(Context paramContext, float paramFloat)
  {
    return TypedValue.applyDimension(1, paramFloat, paramContext.getResources().getDisplayMetrics());
  }
  
  public static int a(Context paramContext)
  {
    int j = 2131099962;
    b = y(paramContext);
    int i;
    if (b == 1) {
      i = 2131099721;
    }
    do
    {
      do
      {
        return i;
        i = j;
      } while (b == 3);
      if (b == 0) {
        return 2131099822;
      }
      if (b == 2) {
        return 2131099846;
      }
      if (b == 4) {
        return 2131099908;
      }
      if (b == 5) {
        return 2131099954;
      }
      if (b == 6) {
        return 2131099998;
      }
      if ((b == 7) || (b == 14) || (b == 13) || (b == 12)) {
        return 2131099711;
      }
      if (b == 8) {
        return 2131099929;
      }
      if (b == 10) {
        return 2131099915;
      }
      if (b == 9) {
        return 2131099922;
      }
      if (b == 15) {
        return 2131099930;
      }
      i = j;
    } while (b != 11);
    return 2131099833;
  }
  
  public static int a(Context paramContext, int paramInt1, int paramInt2)
  {
    Object localObject = (AudioManager)paramContext.getSystemService("audio");
    double d1 = ((AudioManager)localObject).getStreamMaxVolume(4);
    double d2 = ((AudioManager)localObject).getStreamMaxVolume(3);
    if (paramInt1 == 0) {
      return 0;
    }
    int i;
    if ((paramInt2 == 2) && (paramInt1 > d1)) {
      i = (int)d2;
    }
    for (;;)
    {
      localObject = new LogWriter.a();
      ((LogWriter.a)localObject).a("alarmVol", "" + paramInt1);
      ((LogWriter.a)localObject).a("alarmMax", "" + d1);
      ((LogWriter.a)localObject).a("musicMax", "" + d2);
      ((LogWriter.a)localObject).a("convertedVol", "" + i);
      ((LogWriter.a)localObject).a("direction", "" + paramInt2);
      LogWriter.a(paramContext);
      LogWriter.a(paramContext, LogWriter.EventType.a, "CommonUtils", "convertVolumeType", (LogWriter.a)localObject);
      s.a("CommonUtils", "AlarmKlaxon : convertVolumeType, sourceVol:" + paramInt1 + ", alarmMax: " + d1 + ", musicMax:" + d2 + ", convertedVol:" + i + ", direction:" + paramInt2);
      return i;
      if ((paramInt2 == 1) && (paramInt1 > d2))
      {
        i = (int)d1;
      }
      else
      {
        if ((d1 == 7.0D) && (d2 == 15.0D))
        {
          if (paramInt2 == 1)
          {
            if (paramInt1 <= 3)
            {
              i = 1;
              continue;
            }
            if (paramInt1 <= 8)
            {
              i = 2;
              continue;
            }
            if (paramInt1 == 9)
            {
              i = 3;
              continue;
            }
            if (paramInt1 == 10)
            {
              i = 4;
              continue;
            }
            if (paramInt1 == 11)
            {
              i = 5;
              continue;
            }
            if (paramInt1 == 12)
            {
              i = 6;
              continue;
            }
            if (paramInt1 == 13)
            {
              i = 6;
              continue;
            }
            if ((paramInt1 == 14) || (paramInt1 == 15)) {
              i = 7;
            }
          }
          else if (paramInt2 == 2)
          {
            if (paramInt1 == 1)
            {
              i = 8;
              continue;
            }
            if (paramInt1 == 2)
            {
              i = 9;
              continue;
            }
            if (paramInt1 == 3)
            {
              i = 10;
              continue;
            }
            if (paramInt1 == 4)
            {
              i = 11;
              continue;
            }
            if (paramInt1 == 5)
            {
              i = 12;
              continue;
            }
            if (paramInt1 == 6)
            {
              i = 13;
              continue;
            }
            if (paramInt1 == 7) {
              i = 15;
            }
          }
        }
        else if ((d1 == 15.0D) && (d2 == 15.0D))
        {
          if (paramInt2 == 1)
          {
            if (paramInt1 <= 4)
            {
              i = 1;
              continue;
            }
            if ((paramInt1 == 5) || (paramInt1 == 6))
            {
              i = 3;
              continue;
            }
            if (paramInt1 == 7)
            {
              i = 4;
              continue;
            }
            if (paramInt1 == 8)
            {
              i = 6;
              continue;
            }
            if (paramInt1 == 9)
            {
              i = 8;
              continue;
            }
            if (paramInt1 == 10)
            {
              i = 10;
              continue;
            }
            if (paramInt1 == 11)
            {
              i = 12;
              continue;
            }
            if (paramInt1 == 12)
            {
              i = 13;
              continue;
            }
            if (paramInt1 == 13)
            {
              i = 14;
              continue;
            }
            if (paramInt1 == 14)
            {
              i = 15;
              continue;
            }
            if (paramInt1 == 15) {
              i = 15;
            }
          }
          else if (paramInt2 == 2)
          {
            if ((paramInt1 == 1) || (paramInt1 == 2))
            {
              i = 6;
              continue;
            }
            if (paramInt1 == 3)
            {
              i = 7;
              continue;
            }
            if ((paramInt1 == 4) || (paramInt1 == 5))
            {
              i = 8;
              continue;
            }
            if ((paramInt1 == 6) || (paramInt1 == 7))
            {
              i = 9;
              continue;
            }
            if ((paramInt1 == 8) || (paramInt1 == 9))
            {
              i = 10;
              continue;
            }
            if ((paramInt1 == 10) || (paramInt1 == 11))
            {
              i = 11;
              continue;
            }
            if (paramInt1 == 12)
            {
              i = 12;
              continue;
            }
            if (paramInt1 == 13)
            {
              i = 13;
              continue;
            }
            if (paramInt1 == 14)
            {
              i = 14;
              continue;
            }
            if (paramInt1 == 15) {
              i = 15;
            }
          }
        }
        else if ((d1 == 15.0D) && (d2 == 60.0D))
        {
          if (paramInt2 == 1)
          {
            if (paramInt1 <= 20)
            {
              i = 1;
              continue;
            }
            if (paramInt1 <= 25)
            {
              i = 2;
              continue;
            }
            if (paramInt1 <= 35)
            {
              i = 3;
              continue;
            }
            if (paramInt1 <= 45)
            {
              i = 4;
              continue;
            }
            if (paramInt1 <= 50)
            {
              i = 5;
              continue;
            }
            if (paramInt1 <= 53)
            {
              i = 9;
              continue;
            }
            if (paramInt1 == 54)
            {
              i = 10;
              continue;
            }
            if (paramInt1 == 55)
            {
              i = 11;
              continue;
            }
            if (paramInt1 == 56)
            {
              i = 12;
              continue;
            }
            if (paramInt1 == 57)
            {
              i = 13;
              continue;
            }
            if (paramInt1 == 58)
            {
              i = 14;
              continue;
            }
            if (paramInt1 == 59)
            {
              i = 15;
              continue;
            }
            if (paramInt1 == 60) {
              i = 15;
            }
          }
          else if (paramInt2 == 2)
          {
            if (paramInt1 == 1)
            {
              i = 25;
              continue;
            }
            if (paramInt1 == 2)
            {
              i = 35;
              continue;
            }
            if (paramInt1 == 3)
            {
              i = 45;
              continue;
            }
            if (paramInt1 == 4)
            {
              i = 50;
              continue;
            }
            if (paramInt1 == 5)
            {
              i = 55;
              continue;
            }
            if (paramInt1 == 6)
            {
              i = 56;
              continue;
            }
            if (paramInt1 == 7)
            {
              i = 56;
              continue;
            }
            if (paramInt1 == 8)
            {
              i = 56;
              continue;
            }
            if (paramInt1 == 9)
            {
              i = 56;
              continue;
            }
            if (paramInt1 == 10)
            {
              i = 57;
              continue;
            }
            if (paramInt1 == 11)
            {
              i = 57;
              continue;
            }
            if (paramInt1 == 12)
            {
              i = 58;
              continue;
            }
            if (paramInt1 == 13)
            {
              i = 59;
              continue;
            }
            if (paramInt1 == 14)
            {
              i = 59;
              continue;
            }
            if (paramInt1 == 15) {
              i = 60;
            }
          }
        }
        else
        {
          double d3;
          double d4;
          if (paramInt2 == 1)
          {
            d3 = paramInt1;
            d4 = d1 / d2;
          }
          try
          {
            i = (int)Math.ceil(d3 * d4);
          }
          catch (Exception localException)
          {
            Crashlytics.logException(localException);
            s.a("CommonUtils", "convertVolumeType error:" + localException.toString());
            LogWriter.a localA = new LogWriter.a();
            localA.a("alarmVol", "" + paramInt1);
            localA.a("alarmMax", "" + d1);
            localA.a("musicMax", "" + d2);
            localA.a("convertedVol", "" + 1);
            localA.a("direction", "" + paramInt2);
            LogWriter.a(paramContext);
            LogWriter.a(paramContext, LogWriter.EventType.a, "CommonUtils", "convertVolumeType_ERROR", localA);
          }
          if (paramInt2 == 2)
          {
            d3 = Math.ceil(paramInt1 * (d2 / d1));
            i = (int)d3;
            continue;
          }
        }
        i = 1;
      }
    }
  }
  
  public static int a(Context paramContext, c paramC)
  {
    int i = 0;
    switch (paramC.b())
    {
    default: 
      i = 6;
    }
    do
    {
      do
      {
        return i;
      } while (paramC.c() < 20);
      return 1;
      if (paramC.c() < 19) {
        return 1;
      }
      return 2;
      if (paramC.c() < 21) {
        return 2;
      }
      return 3;
      if (paramC.c() < 20) {
        return 3;
      }
      return 4;
      if (paramC.c() < 21) {
        return 4;
      }
      return 5;
      if (paramC.c() < 22) {
        return 5;
      }
      return 6;
      if (paramC.c() < 23) {
        return 6;
      }
      return 7;
      if (paramC.c() < 23) {
        return 7;
      }
      return 8;
      if (paramC.c() < 24) {
        return 8;
      }
      return 9;
      if (paramC.c() < 23) {
        return 9;
      }
      return 10;
      if (paramC.c() < 23) {
        return 10;
      }
      return 11;
    } while (paramC.c() >= 25);
    return 11;
  }
  
  public static int a(Context paramContext, boolean paramBoolean)
  {
    b = y(paramContext);
    if ((b == 7) || (b == 14) || (b == 13) || (b == 12))
    {
      if (paramBoolean) {
        return 2131099710;
      }
      return 2131099708;
    }
    if (paramBoolean) {
      return 2131099985;
    }
    return 2131099872;
  }
  
  public static int a(String paramString)
  {
    if (paramString.equals("android.resource://droom.sleepIfUCan/2131689473")) {
      return 2131689473;
    }
    if (paramString.equals("android.resource://droom.sleepIfUCan/2131689474")) {
      return 2131689474;
    }
    if (paramString.equals("android.resource://droom.sleepIfUCan/2131689475")) {
      return 2131689475;
    }
    if (paramString.equals("android.resource://droom.sleepIfUCan/2131689476")) {
      return 2131689476;
    }
    if (paramString.equals("android.resource://droom.sleepIfUCan/2131689477")) {
      return 2131689477;
    }
    if (paramString.equals("android.resource://droom.sleepIfUCan/2131689478")) {
      return 2131689478;
    }
    if (paramString.equals("android.resource://droom.sleepIfUCan/2131689479")) {
      return 2131689479;
    }
    if (paramString.equals("android.resource://droom.sleepIfUCan/2131689480")) {
      return 2131689480;
    }
    return 2131689472;
  }
  
  public static int a(boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramBoolean2) && (!paramBoolean1))
    {
      if ((h()) || (j()))
      {
        s.a("CommonUtils", "getRightStreamType:STREAM_RING");
        return 2;
      }
      s.a("CommonUtils", "getRightStreamType:STREAM_ALARM");
      return 4;
    }
    s.a("CommonUtils", "getRightStreamType:STREAM_MUSIC");
    return 3;
  }
  
  public static int a(byte[] paramArrayOfByte)
  {
    try
    {
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length, localOptions);
      int i = localOptions.outWidth;
      return i;
    }
    catch (Exception paramArrayOfByte) {}
    return 0;
  }
  
  public static long a(String paramString1, String paramString2)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString2);
    paramString2 = null;
    try
    {
      paramString1 = localSimpleDateFormat.parse(paramString1);
      return paramString1.getTime();
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        Log.e("CommonUtils", "convertTime");
        paramString1 = paramString2;
      }
    }
  }
  
  public static Context a(Context paramContext, Configuration paramConfiguration)
  {
    d = aC(paramContext);
    s.a("CommonUtils", "config set language to : " + d);
    a(d);
    return b(paramContext, paramConfiguration);
  }
  
  public static Uri a(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 0: 
      return Uri.parse("android.resource://droom.sleepIfUCan/2131689473");
    case 1: 
      return Uri.parse("android.resource://droom.sleepIfUCan/2131689474");
    case 2: 
      return Uri.parse("android.resource://droom.sleepIfUCan/2131689475");
    case 3: 
      return Uri.parse("android.resource://droom.sleepIfUCan/2131689476");
    case 4: 
      return Uri.parse("android.resource://droom.sleepIfUCan/2131689477");
    case 5: 
      return Uri.parse("android.resource://droom.sleepIfUCan/2131689478");
    case 6: 
      return Uri.parse("android.resource://droom.sleepIfUCan/2131689479");
    case 7: 
      return Uri.parse("android.resource://droom.sleepIfUCan/2131689480");
    }
    return Uri.parse("uri_random");
  }
  
  public static Uri a(Context paramContext, Uri paramUri)
  {
    if (paramUri == null) {}
    try
    {
      return RingtoneManager.getDefaultUri(4);
    }
    catch (Exception paramUri)
    {
      paramUri = paramUri;
      paramUri = aO(paramContext);
      aa.a(paramContext, 2131755475, 0);
      return paramUri;
    }
    finally {}
    if (paramUri.toString().equals("uri_random")) {
      return a((int)(Math.random() * 8.0D));
    }
    if (paramUri.toString().equals("uri_random_music")) {
      return D(paramContext);
    }
    if (paramUri.toString().equals("uri_random_ringtone"))
    {
      paramUri = E(paramContext);
      return paramUri;
    }
    return paramUri;
  }
  
  public static Uri a(Context paramContext, Uri paramUri, boolean paramBoolean)
  {
    if ((paramUri == null) || (paramUri.getPath() == null))
    {
      s.a("CommonUtils", "null uri");
      return aO(paramContext);
    }
    s.a("CommonUtils", "alert string: " + paramUri.toString() + ", path: " + paramUri.getPath());
    if ((paramUri.toString() != null) && ((paramUri.toString().equals("uri_random")) || (paramUri.toString().equals("uri_random_music")) || (paramUri.toString().equals("uri_random_ringtone")) || (paramUri.toString().contains("android.resource://droom.sleepIfUCan"))))
    {
      s.a("CommonUtils", "original uri is random, or loud");
      return paramUri;
    }
    int i = RingtoneManager.getDefaultType(paramUri);
    if (i != -1) {}
    try
    {
      Uri localUri2 = RingtoneManager.getActualDefaultRingtoneUri(paramContext, i);
      Uri localUri1 = localUri2;
      if (localUri2 != null)
      {
        localUri1 = localUri2;
        if (!localUri2.toString().startsWith("content")) {
          localUri1 = Uri.parse(n(paramContext, localUri2.getPath()));
        }
      }
      if (localUri1 != null) {
        s.a("CommonUtils", "Default alert: " + localUri1.toString() + ", authority: " + localUri1.getAuthority());
      }
      if (localUri1 == null) {
        return aO(paramContext);
      }
      if ((localUri1.toString().contains("external")) && (!aM(paramContext))) {
        if (paramBoolean)
        {
          paramUri = RingtoneManager.getValidRingtoneUri(paramContext);
          return paramUri;
        }
      }
      try
      {
        Toast.makeText(paramContext, 2131755475, 1).show();
        s.a("CommonUtils", "reqPerm");
        ((Activity)paramContext).requestPermissions(new String[] { "android.permission.READ_EXTERNAL_STORAGE" }, 3124);
        s.a("CommonUtils", "fall with uri " + paramUri.toString());
        return paramUri;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          s.a("CommonUtils", "nvm, can't cast to activity");
        }
      }
      try
      {
        Toast.makeText(paramContext, 2131755475, 1).show();
        s.a("CommonUtils", "reqPerm");
        ((Activity)paramContext).requestPermissions(new String[] { "android.permission.READ_EXTERNAL_STORAGE" }, 3124);
        return aO(paramContext);
      }
      catch (Exception paramUri)
      {
        for (;;)
        {
          s.a("CommonUtils", "nvm, can't cast to activity");
        }
      }
    }
    catch (Exception paramUri)
    {
      s.a("CommonUtils", "default requires permission");
      if (!aM(paramContext)) {
        if (paramBoolean) {
          return RingtoneManager.getValidRingtoneUri(paramContext);
        }
      }
    }
  }
  
  public static Horoscope a(Context paramContext, String paramString, Response.Listener<JSONObject> paramListener, Response.ErrorListener paramErrorListener)
  {
    Object localObject = aC(paramContext).getLanguage();
    if ((!((String)localObject).startsWith("en")) && (!((String)localObject).contains("ja")) && (!((String)localObject).contains("zh")) && (!((String)localObject).contains("ko")))
    {
      s.a(paramString, "requestHOroscope not supported. returning null");
      return null;
    }
    try
    {
      localObject = f(paramContext, aV(paramContext));
      if (localObject != null) {
        return localObject;
      }
    }
    catch (ClassCastException localClassCastException)
    {
      for (;;)
      {
        s.a(paramString, "horoscope cast exception. reset cache");
        str = null;
      }
      String str = j(paramContext, a(paramContext, az(paramContext)));
      s.a(paramString, "horoscope url: " + str);
      paramContext = ad.a(paramContext).a();
      paramListener = new h(0, str, paramListener, paramErrorListener);
      paramListener.setRetryPolicy(new DefaultRetryPolicy(5000, 0, 1.0F));
      paramListener.setTag(paramString);
      paramContext.add(paramListener);
    }
    return null;
  }
  
  public static String a(long paramLong)
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(paramLong));
  }
  
  public static String a(Context paramContext, Long paramLong)
  {
    if (paramLong.longValue() < 0L) {
      return "Something wrong";
    }
    long l1 = paramLong.longValue() / 86400000L;
    long l2 = paramLong.longValue() / 3600000L % 24L;
    long l3 = paramLong.longValue() / 60000L % 60L;
    String str1;
    label74:
    String str2;
    label86:
    int j;
    if (l1 == 0L)
    {
      paramLong = "";
      if (l2 != 0L) {
        break label246;
      }
      str1 = "";
      if (l3 != 0L) {
        break label289;
      }
      str2 = "";
      if (l2 <= 0L) {
        break label332;
      }
      j = 1;
      label95:
      if (l3 <= 0L) {
        break label337;
      }
      i = 1;
      label104:
      if (j == 0) {
        break label342;
      }
      j = 1;
      label110:
      if (i == 0) {
        break label347;
      }
    }
    label246:
    label289:
    label332:
    label337:
    label342:
    label347:
    for (int i = 2;; i = 0)
    {
      paramContext = paramContext.getResources().getStringArray(2130903083);
      return paramLong + String.format(paramContext[(i | j)], new Object[] { str1, str2 });
      if (l1 == 1L)
      {
        paramLong = paramContext.getString(2131755205) + " ";
        break;
      }
      paramLong = paramContext.getString(2131755207, new Object[] { Long.toString(l1) }) + " ";
      break;
      if (l2 == 1L)
      {
        str1 = paramContext.getString(2131755307);
        break label74;
      }
      str1 = paramContext.getString(2131755308, new Object[] { Long.toString(l2) });
      break label74;
      if (l3 == 1L)
      {
        str2 = paramContext.getString(2131755357);
        break label86;
      }
      str2 = paramContext.getString(2131755358, new Object[] { Long.toString(l3) });
      break label86;
      j = 0;
      break label95;
      i = 0;
      break label104;
      j = 0;
      break label110;
    }
  }
  
  public static String a(Uri paramUri, ContentResolver paramContentResolver, Context paramContext)
  {
    if (!aM(paramContext)) {
      return null;
    }
    s.a("CommonUtils", "given uri: " + paramUri);
    do
    {
      do
      {
        long l;
        while (!paramContentResolver.moveToNext()) {
          try
          {
            l = ContentUris.parseId(paramUri);
            paramContentResolver = paramContentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[] { "_id", "_data", "title" }, "_id=?", new String[] { String.valueOf(l) }, "title ASC");
            if ((paramContentResolver == null) || (paramContentResolver.getCount() == 0))
            {
              if (paramContentResolver != null) {
                paramContentResolver.close();
              }
              return null;
            }
          }
          catch (Exception paramUri)
          {
            return null;
          }
        }
        paramContext = paramContentResolver.getString(paramContentResolver.getColumnIndex("_data"));
      } while (paramContext == null);
      s.a("CommonUtils", "load cursor: " + paramContentResolver.getString(paramContentResolver.getColumnIndex("title")));
    } while (!paramUri.toString().equals(Uri.withAppendedPath(MediaStore.Audio.Media.getContentUriForPath(paramContext), paramContentResolver.getString(paramContentResolver.getColumnIndex("_id"))).toString()));
    return paramContentResolver.getString(paramContentResolver.getColumnIndex("title"));
  }
  
  public static void a(Activity paramActivity)
  {
    AerServSdk.init(paramActivity, droom.sleepIfUCan.a.n);
  }
  
  public static void a(Context paramContext, double paramDouble1, double paramDouble2)
  {
    paramContext = paramContext.getSharedPreferences("gps", 0).edit();
    paramContext.putString("lat", "" + paramDouble1);
    paramContext.putString("lon", "" + paramDouble2);
    paramContext.apply();
  }
  
  public static void a(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getSharedPreferences("theme", 0).edit();
    paramContext.putInt("color", paramInt);
    paramContext.apply();
    b = paramInt;
  }
  
  public static void a(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    paramContext = paramContext.getSharedPreferences("pref_user_info", 0).edit();
    paramContext.putBoolean("pref_birthday_has_set", true);
    paramContext.putInt("pref_birthday_year", paramInt1);
    paramContext.putInt("pref_birthday_month", paramInt2);
    paramContext.putInt("pref_birthday_day", paramInt3);
    paramContext.apply();
  }
  
  public static void a(Context paramContext, int paramInt, boolean paramBoolean)
  {
    paramContext = paramContext.getSharedPreferences("restartAlarm", 0).edit();
    paramContext.putInt("id", paramInt);
    paramContext.putLong("ringTime", System.currentTimeMillis());
    paramContext.putBoolean("isDismissed", paramBoolean);
    paramContext.apply();
  }
  
  public static void a(Context paramContext, long paramLong)
  {
    paramContext = paramContext.getSharedPreferences("AlarmClock", 0).edit();
    paramContext.putLong("pref_weather_updated_time", paramLong);
    paramContext.apply();
  }
  
  public static void a(Context paramContext, Alarm paramAlarm)
  {
    FirebaseAnalytics localFirebaseAnalytics;
    Bundle localBundle;
    for (;;)
    {
      try
      {
        localFirebaseAnalytics = FirebaseAnalytics.getInstance(paramContext);
        localBundle = new Bundle();
        localBundle.putLong("event_time", System.currentTimeMillis());
        localBundle.putInt("dismiss_method", paramAlarm.k);
        if (paramAlarm.k == 2)
        {
          localBundle.putInt("shake_number", Integer.parseInt(g(paramAlarm.l)));
          localBundle.putInt("math_level", -1);
          localBundle.putInt("math_number", -1);
          Iterator localIterator = localBundle.keySet().iterator();
          paramAlarm = "";
          if (!localIterator.hasNext()) {
            break;
          }
          String str = (String)localIterator.next();
          paramAlarm = paramAlarm + str + ":" + localBundle.get(str) + ",";
          continue;
        }
        if (paramAlarm.k == 3)
        {
          localBundle.putInt("shake_number", -1);
          localBundle.putInt("math_level", Integer.parseInt(paramAlarm.l.split(",")[1]));
          localBundle.putInt("math_number", Integer.parseInt(paramAlarm.l.split(",")[2]));
          continue;
        }
        localBundle.putInt("shake_number", -1);
      }
      catch (Exception paramContext)
      {
        Crashlytics.logException(paramContext);
        return;
      }
      localBundle.putInt("math_level", -1);
      localBundle.putInt("math_number", -1);
    }
    s.a("FB_EVENT", "event_name:fire_preview, " + paramAlarm);
    localFirebaseAnalytics.a("fire_preview", localBundle);
    l.a(paramContext).a("an_fire_preview", localBundle);
  }
  
  public static void a(Context paramContext, Alarm paramAlarm, String paramString)
  {
    for (;;)
    {
      Bundle localBundle;
      int i;
      try
      {
        Object localObject = FirebaseAnalytics.getInstance(paramContext);
        localBundle = new Bundle();
        localBundle.putLong("event_time", System.currentTimeMillis());
        localBundle.putInt("dismiss_method", paramAlarm.k);
        localBundle.putBoolean("vibration_set", paramAlarm.g);
        localBundle.putInt("ringing_time_hour", paramAlarm.c);
        localBundle.putInt("ringing_time_min", paramAlarm.d);
        localBundle.putString("repeat", paramAlarm.e.a(paramContext, true));
        if (paramAlarm.h == null)
        {
          str1 = "null";
          localBundle.putString("label", str1);
          localBundle.putString("alarm_id", N(paramContext) + "_" + paramAlarm.a);
          if (paramAlarm.k != 2) {
            break label407;
          }
          localBundle.putInt("shake_number", Integer.parseInt(g(paramAlarm.l)));
          localBundle.putInt("math_level", -1);
          localBundle.putInt("math_number", -1);
          ((FirebaseAnalytics)localObject).a(paramString, localBundle);
          localObject = localBundle.keySet().iterator();
          paramAlarm = "";
          if (!((Iterator)localObject).hasNext()) {
            break label503;
          }
          String str2 = (String)((Iterator)localObject).next();
          str1 = paramAlarm + str2 + ":" + localBundle.get(str2).toString() + ",";
          paramAlarm = str1;
          if (!(localBundle.get(str2) instanceof Boolean)) {
            continue;
          }
          boolean bool = ((Boolean)localBundle.get(str2)).booleanValue();
          localBundle.remove(str2);
          if (!bool) {
            break label569;
          }
          i = 1;
          localBundle.putInt(str2, i);
          paramAlarm = str1;
          continue;
        }
        String str1 = paramAlarm.h;
      }
      catch (Exception paramContext)
      {
        s.a("FB_EVENT", "ERROR in trycatch, " + paramContext.getCause() + ", " + paramContext.getMessage());
        paramContext.printStackTrace();
        Crashlytics.logException(paramContext);
        return;
      }
      continue;
      label407:
      if (paramAlarm.k == 3)
      {
        localBundle.putInt("shake_number", -1);
        localBundle.putInt("math_level", Integer.parseInt(paramAlarm.l.split(",")[1]));
        localBundle.putInt("math_number", Integer.parseInt(paramAlarm.l.split(",")[2]));
      }
      else
      {
        localBundle.putInt("shake_number", -1);
        localBundle.putInt("math_level", -1);
        localBundle.putInt("math_number", -1);
        continue;
        label503:
        s.a("FB_EVENT", "event_name: " + paramString + ", " + paramAlarm);
        l.a(paramContext).a("an_" + paramString, localBundle);
        return;
        label569:
        i = 0;
      }
    }
  }
  
  public static void a(Context paramContext, Alarm paramAlarm, String paramString, Bundle paramBundle)
  {
    for (;;)
    {
      Bundle localBundle;
      int i;
      try
      {
        Object localObject2 = FirebaseAnalytics.getInstance(paramContext);
        localBundle = new Bundle();
        localBundle.putLong("event_time", System.currentTimeMillis());
        localBundle.putInt("dismiss_method", paramAlarm.k);
        localBundle.putBoolean("vibration_set", paramAlarm.g);
        localBundle.putInt("ringing_time_hour", paramAlarm.c);
        localBundle.putInt("ringing_time_min", paramAlarm.d);
        localBundle.putString("repeat", paramAlarm.e.a(paramContext, true));
        if (paramAlarm.h == null)
        {
          localObject1 = "null";
          localBundle.putString("label", (String)localObject1);
          localBundle.putString("alarm_id", N(paramContext) + "_" + paramAlarm.a);
          if (paramAlarm.k != 2) {
            break label409;
          }
          localBundle.putInt("shake_number", Integer.parseInt(g(paramAlarm.l)));
          localBundle.putInt("math_level", -1);
          localBundle.putInt("math_number", -1);
          localBundle.putAll(paramBundle);
          ((FirebaseAnalytics)localObject2).a(paramString, localBundle);
          localObject1 = localBundle.keySet().iterator();
          paramAlarm = "";
          if (!((Iterator)localObject1).hasNext()) {
            break label505;
          }
          localObject2 = (String)((Iterator)localObject1).next();
          paramBundle = paramAlarm + (String)localObject2 + ":" + localBundle.get((String)localObject2) + ",";
          paramAlarm = paramBundle;
          if (!(localBundle.get((String)localObject2) instanceof Boolean)) {
            continue;
          }
          boolean bool = ((Boolean)localBundle.get((String)localObject2)).booleanValue();
          localBundle.remove((String)localObject2);
          if (!bool) {
            break label571;
          }
          i = 1;
          localBundle.putInt((String)localObject2, i);
          paramAlarm = paramBundle;
          continue;
        }
        Object localObject1 = paramAlarm.h;
      }
      catch (Exception paramContext)
      {
        s.a("FB_EVENT", "ERROR in trycatch, " + paramContext.getCause() + ", " + paramContext.getMessage());
        paramContext.printStackTrace();
        Crashlytics.logException(paramContext);
        return;
      }
      continue;
      label409:
      if (paramAlarm.k == 3)
      {
        localBundle.putInt("shake_number", -1);
        localBundle.putInt("math_level", Integer.parseInt(paramAlarm.l.split(",")[1]));
        localBundle.putInt("math_number", Integer.parseInt(paramAlarm.l.split(",")[2]));
      }
      else
      {
        localBundle.putInt("shake_number", -1);
        localBundle.putInt("math_level", -1);
        localBundle.putInt("math_number", -1);
        continue;
        label505:
        s.a("FB_EVENT", "event_name: " + paramString + ", " + paramAlarm);
        l.a(paramContext).a("an_" + paramString, localBundle);
        return;
        label571:
        i = 0;
      }
    }
  }
  
  public static void a(Context paramContext, Alarm paramAlarm, boolean paramBoolean)
  {
    FirebaseAnalytics localFirebaseAnalytics;
    Bundle localBundle;
    for (;;)
    {
      try
      {
        localFirebaseAnalytics = FirebaseAnalytics.getInstance(paramContext);
        localBundle = new Bundle();
        localBundle.putLong("event_time", System.currentTimeMillis());
        localBundle.putInt("dismiss_method", paramAlarm.k);
        if (paramAlarm.k == 2)
        {
          localBundle.putInt("shake_number", Integer.parseInt(g(paramAlarm.l)));
          localBundle.putInt("math_level", -1);
          localBundle.putInt("math_number", -1);
          localBundle.putBoolean("dismiss_success", paramBoolean);
          Iterator localIterator = localBundle.keySet().iterator();
          paramAlarm = "";
          if (!localIterator.hasNext()) {
            break;
          }
          String str = (String)localIterator.next();
          paramAlarm = paramAlarm + str + ":" + localBundle.get(str) + ",";
          continue;
        }
        if (paramAlarm.k == 3)
        {
          localBundle.putInt("shake_number", -1);
          localBundle.putInt("math_level", Integer.parseInt(paramAlarm.l.split(",")[1]));
          localBundle.putInt("math_number", Integer.parseInt(paramAlarm.l.split(",")[2]));
          continue;
        }
        localBundle.putInt("shake_number", -1);
      }
      catch (Exception paramContext)
      {
        Crashlytics.logException(paramContext);
        return;
      }
      localBundle.putInt("math_level", -1);
      localBundle.putInt("math_number", -1);
    }
    s.a("FB_EVENT", "event_name:dismiss_preview, " + paramAlarm);
    localFirebaseAnalytics.a("dismiss_preview", localBundle);
    localBundle.remove("dismiss_success");
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      localBundle.putInt("dismiss_success", i);
      l.a(paramContext).a("an_dismiss_preview", localBundle);
      return;
    }
  }
  
  public static void a(Context paramContext, Boolean paramBoolean)
  {
    LogWriter.a(paramContext);
    LogWriter.a(paramContext, LogWriter.EventType.g, "CommonUtils", "ringer_mode_set", new LogWriter.a("setRingerModeWhenMute", "" + paramBoolean));
    if (paramBoolean.booleanValue())
    {
      i = Settings.System.getInt(paramContext.getContentResolver(), "mode_ringer_streams_affected", 0);
      Settings.System.putInt(paramContext.getContentResolver(), "mode_ringer_streams_affected", i & 0xFFFFFFEF);
      return;
    }
    int i = Settings.System.getInt(paramContext.getContentResolver(), "mode_ringer_streams_affected", 0);
    Settings.System.putInt(paramContext.getContentResolver(), "mode_ringer_streams_affected", i | 0x10);
  }
  
  public static void a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("barcodeList", 0);
    int i = paramContext.getInt("index", 0);
    paramContext = paramContext.edit();
    paramContext.putString("elements" + i, paramString);
    paramContext.putInt("index", i + 1);
    paramContext.apply();
  }
  
  public static void a(Context paramContext, String paramString, Bundle paramBundle)
  {
    Object localObject;
    for (;;)
    {
      try
      {
        localObject = FirebaseAnalytics.getInstance(paramContext);
        paramBundle.putLong("event_time", System.currentTimeMillis());
        ((FirebaseAnalytics)localObject).a(paramString, paramBundle);
        Iterator localIterator = paramBundle.keySet().iterator();
        localObject = "";
        if (!localIterator.hasNext()) {
          break;
        }
        String str2 = (String)localIterator.next();
        String str1 = (String)localObject + str2 + ":" + paramBundle.get(str2) + ",";
        localObject = str1;
        if ((paramBundle.get(str2) instanceof Boolean))
        {
          boolean bool = ((Boolean)paramBundle.get(str2)).booleanValue();
          paramBundle.remove(str2);
          int i;
          if (bool)
          {
            i = 1;
            paramBundle.putInt(str2, i);
            localObject = str1;
          }
          else
          {
            i = 0;
          }
        }
      }
      catch (Exception paramContext)
      {
        Crashlytics.logException(paramContext);
        return;
      }
    }
    s.a("FB_EVENT", "event_name: " + paramString + ", " + (String)localObject);
    l.a(paramContext).a("an_" + paramString, paramBundle);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = paramContext.getSharedPreferences("gps", 0).edit();
    paramContext.putString("lat", paramString1);
    paramContext.putString("lon", paramString2);
    paramContext.apply();
  }
  
  public static void a(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      s.a("CommonUtils", "set hellobot horoscope cache with data: " + paramString);
      paramContext = paramContext.getSharedPreferences("horoscope", 0).edit();
      paramContext.putString("jsonStringHelloBot", paramString);
      paramContext.apply();
      return;
    }
    s.a("CommonUtils", "set horoscope cache with data: " + paramString);
    paramContext = paramContext.getSharedPreferences("horoscope", 0).edit();
    paramContext.putString("jsonString", paramString);
    paramContext.apply();
  }
  
  public static void a(Context paramContext, ArrayList<String> paramArrayList)
  {
    paramContext = paramContext.getSharedPreferences("barcodeList", 0).edit();
    paramContext.clear();
    int j = paramArrayList.size();
    paramContext.putInt("index", j);
    int i = 0;
    while (i < j)
    {
      paramContext.putString("elements" + i, (String)paramArrayList.get(i));
      i += 1;
    }
    paramContext.apply();
  }
  
  public static void a(View paramView)
  {
    if ((paramView != null) && (paramView.getParent() != null)) {
      ((ViewGroup)paramView.getParent()).removeView(paramView);
    }
  }
  
  public static void a(Window paramWindow)
  {
    paramWindow.addFlags(1024);
    paramWindow.clearFlags(2048);
  }
  
  public static void a(NumberPicker paramNumberPicker, boolean paramBoolean)
  {
    int j = paramNumberPicker.getChildCount();
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        View localView = paramNumberPicker.getChildAt(i);
        if ((localView instanceof EditText)) {
          ((EditText)localView).setFocusable(paramBoolean);
        }
      }
      else
      {
        return;
      }
      i += 1;
    }
  }
  
  /* Error */
  public static final void a(Exception paramException)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 350	droom/sleepIfUCan/utils/e:a	Ldroom/sleepIfUCan/view/a/aq;
    //   6: ifnull +28 -> 34
    //   9: getstatic 350	droom/sleepIfUCan/utils/e:a	Ldroom/sleepIfUCan/view/a/aq;
    //   12: invokevirtual 1421	droom/sleepIfUCan/view/a/aq:isShowing	()Z
    //   15: istore_1
    //   16: iload_1
    //   17: ifeq +17 -> 34
    //   20: getstatic 350	droom/sleepIfUCan/utils/e:a	Ldroom/sleepIfUCan/view/a/aq;
    //   23: invokevirtual 1424	droom/sleepIfUCan/view/a/aq:dismiss	()V
    //   26: aconst_null
    //   27: putstatic 350	droom/sleepIfUCan/utils/e:a	Ldroom/sleepIfUCan/view/a/aq;
    //   30: aload_0
    //   31: ifnull +3 -> 34
    //   34: ldc 2
    //   36: monitorexit
    //   37: return
    //   38: astore_2
    //   39: aload_2
    //   40: invokevirtual 1425	java/lang/IllegalArgumentException:printStackTrace	()V
    //   43: goto -13 -> 30
    //   46: astore_0
    //   47: ldc 2
    //   49: monitorexit
    //   50: aload_0
    //   51: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	52	0	paramException	Exception
    //   15	2	1	bool	boolean
    //   38	2	2	localIllegalArgumentException	IllegalArgumentException
    // Exception table:
    //   from	to	target	type
    //   20	30	38	java/lang/IllegalArgumentException
    //   3	16	46	finally
    //   20	30	46	finally
    //   39	43	46	finally
  }
  
  public static void a(Locale paramLocale)
  {
    d = paramLocale;
    Locale.setDefault(d);
  }
  
  public static boolean a()
  {
    return droom.sleepIfUCan.a.f > 16;
  }
  
  public static boolean a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((paramInt2 == paramInt4) && (paramInt1 == paramInt3)) {}
    while ((paramInt2 == paramInt3) && (paramInt1 == paramInt4)) {
      return true;
    }
    return false;
  }
  
  public static boolean a(Context paramContext, Intent paramIntent)
  {
    s.a("CommonUtils", "isCallable call");
    paramContext = paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536).iterator();
    while (paramContext.hasNext())
    {
      paramIntent = (ResolveInfo)paramContext.next();
      if (paramIntent.activityInfo.exported == true)
      {
        s.a("CommonUtils", "isCallable: " + paramIntent.activityInfo.name);
        return true;
      }
    }
    s.a("CommonUtils", "isCallable: false");
    return false;
  }
  
  public static int aA(Context paramContext)
  {
    return paramContext.getSharedPreferences("pref_user_info", 0).getInt("pref_gender", 0);
  }
  
  public static int aB(Context paramContext)
  {
    int i = 0;
    paramContext = az(paramContext);
    if (paramContext.d())
    {
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.setTimeInMillis(System.currentTimeMillis());
      i = localCalendar.get(1) - paramContext.a();
    }
    return i;
  }
  
  public static Locale aC(Context paramContext)
  {
    Object localObject2 = android.preference.PreferenceManager.getDefaultSharedPreferences(paramContext).getString("language_setting", "error");
    s.a("CommonUtils", "lang in prf: " + (String)localObject2);
    r.a(paramContext);
    HashMap localHashMap = r.b();
    Object localObject1;
    if (!((String)localObject2).equalsIgnoreCase("error"))
    {
      localObject1 = localObject2;
      if (!((String)localObject2).equalsIgnoreCase("default")) {}
    }
    else if (e())
    {
      paramContext = Resources.getSystem().getConfiguration().getLocales().get(0).getLanguage();
      localObject1 = localHashMap.keySet().iterator();
      do
      {
        if (!((Iterator)localObject1).hasNext()) {
          break;
        }
      } while (!((CharSequence)localHashMap.get((CharSequence)((Iterator)localObject1).next())).toString().equalsIgnoreCase(paramContext));
    }
    for (int i = 1;; i = 0)
    {
      s.a("CommonUtils", "system locale: " + paramContext);
      localObject1 = paramContext;
      if (i == 0)
      {
        if (paramContext.equals("zh"))
        {
          if (e())
          {
            return Resources.getSystem().getConfiguration().getLocales().get(0);
            paramContext = Resources.getSystem().getConfiguration().locale.getLanguage();
            break;
          }
          return Resources.getSystem().getConfiguration().locale;
        }
        return new Locale("en");
      }
      s.a("CommonUtils", "locale: " + (String)localObject1);
      paramContext = ((String)localObject1).split("\\+");
      localObject2 = ((String)localObject1).split("\\-");
      if (paramContext.length > 2)
      {
        if (((String)localObject1).equals("b+sr+Latn")) {
          return new Locale("sr", "rs", "latn");
        }
        return new Locale(paramContext[0], paramContext[1], paramContext[2]);
      }
      if (localObject2.length > 1) {
        return new Locale(localObject2[0], localObject2[1]);
      }
      return new Locale((String)localObject1);
    }
  }
  
  public static String aD(Context paramContext)
  {
    return android.preference.PreferenceManager.getDefaultSharedPreferences(paramContext).getString("lang_value", null);
  }
  
  public static void aE(Context paramContext)
  {
    if (!droom.sleepIfUCan.a.a)
    {
      if (ao(paramContext) != droom.sleepIfUCan.a.l) {
        break label29;
      }
      droom.sleepIfUCan.a.b = true;
      s.a("CommonUtils", "iab upgraded");
    }
    label29:
    do
    {
      return;
      if (ao(paramContext) == droom.sleepIfUCan.a.k)
      {
        droom.sleepIfUCan.a.b = true;
        s.a("CommonUtils", "viral upgraded");
        return;
      }
      droom.sleepIfUCan.a.b = false;
    } while ((i()) && (d()) && (!an(paramContext)));
    s.a("CommonUtils", "not upgraded");
  }
  
  public static long aF(Context paramContext)
  {
    return paramContext.getSharedPreferences("AlarmClock", 0).getLong("pref_weather_updated_time", 0L);
  }
  
  public static boolean aG(Context paramContext)
  {
    return paramContext.getSharedPreferences("exception", 0).getBoolean("exception_occurred", false);
  }
  
  public static File aH(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getDatabasePath("alarms.db");
      return paramContext;
    }
    catch (Exception paramContext)
    {
      s.a("CommonUtils", paramContext.toString());
    }
    return null;
  }
  
  public static boolean aI(Context paramContext)
  {
    return paramContext.getSharedPreferences("AlarmClock", 0).getBoolean("beta_upgrade_notice", false);
  }
  
  public static long aJ(Context paramContext)
  {
    return paramContext.getSharedPreferences("pref_last_open_date", 0).getLong("pref_key_next_alert", 0L);
  }
  
  public static long aK(Context paramContext)
  {
    return paramContext.getSharedPreferences("AlarmClock", 0).getLong("pref_key_alarm_init_receive", 0L);
  }
  
  public static boolean aL(Context paramContext)
  {
    if (!d()) {
      return false;
    }
    return paramContext.getSharedPreferences("AlarmClock", 0).getBoolean("timePicker_analog", false);
  }
  
  @TargetApi(23)
  public static boolean aM(Context paramContext)
  {
    boolean bool2 = true;
    boolean bool1 = bool2;
    if (d())
    {
      int i = paramContext.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE");
      paramContext = new ArrayList();
      if (i != 0) {
        paramContext.add("android.permission.READ_EXTERNAL_STORAGE");
      }
      bool1 = bool2;
      if (!paramContext.isEmpty()) {
        bool1 = false;
      }
    }
    return bool1;
  }
  
  public static boolean aN(Context paramContext)
  {
    return paramContext.getSharedPreferences("AlarmClock", 0).getBoolean("remote_config_error", false);
  }
  
  public static Uri aO(Context paramContext)
  {
    return Uri.parse("android.resource://droom.sleepIfUCan/2131689472");
  }
  
  public static boolean aP(Context paramContext)
  {
    int i = 0;
    if (c())
    {
      paramContext = ((DisplayManager)paramContext.getSystemService("display")).getDisplays();
      int j = paramContext.length;
      for (boolean bool1 = false;; bool1 = bool2)
      {
        bool2 = bool1;
        if (i >= j) {
          break;
        }
        Object localObject = paramContext[i];
        s.a("CommonUtils", "isScreenOn for " + localObject.getName() + "? " + localObject.getState());
        bool2 = bool1;
        if (localObject.getState() != 1)
        {
          bool2 = bool1;
          if (localObject.getState() != 3)
          {
            bool2 = bool1;
            if (localObject.getState() != 4) {
              bool2 = true;
            }
          }
        }
        i += 1;
      }
    }
    boolean bool2 = ((PowerManager)paramContext.getSystemService("power")).isScreenOn();
    return bool2;
  }
  
  public static Intent aQ(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.SENDTO");
    localIntent.setType("message/rfc822");
    localIntent.setData(Uri.parse("mailto:" + paramContext.getResources().getString(2131755202)));
    localIntent.putExtra("android.intent.extra.SUBJECT", M(paramContext));
    localIntent.putExtra("android.intent.extra.TEXT", paramContext.getString(2131755610));
    return Intent.createChooser(localIntent, "Send email");
  }
  
  public static void aR(Context paramContext)
  {
    int i = -1;
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("dataMigration", 0);
    if (localSharedPreferences.getInt("fromVersion", -1) > 270)
    {
      s.a("migrateTest", "already migrated. return");
      return;
    }
    s.a("migrateTest", "migrating...");
    int j = paramContext.getSharedPreferences("defaultVolume", 0).getInt("volume", L(paramContext));
    int k = Integer.parseInt(android.preference.PreferenceManager.getDefaultSharedPreferences(paramContext).getString("snooze_setting", "1"));
    if (k == 0) {}
    for (;;)
    {
      k = Integer.parseInt(android.preference.PreferenceManager.getDefaultSharedPreferences(paramContext).getString("shake_sensitivity", "1"));
      Cursor localCursor = b.a(paramContext.getContentResolver());
      localCursor.moveToFirst();
      label128:
      Alarm localAlarm;
      Object localObject2;
      if (!localCursor.isAfterLast())
      {
        localAlarm = new Alarm(localCursor);
        int m = localAlarm.k;
        localObject2 = localAlarm.l;
        if ((m != 2) || (localObject2 == null)) {
          break label469;
        }
        if (k == 0) {
          localObject1 = "hard";
        }
      }
      label183:
      label469:
      for (Object localObject1 = b((String)localObject2, (String)localObject1);; localObject1 = localObject2)
      {
        localObject2 = new ContentValues(15);
        ((ContentValues)localObject2).put("photopath", (String)localObject1);
        ((ContentValues)localObject2).put("latitude", Integer.valueOf(i));
        ((ContentValues)localObject2).put("longitude", Integer.valueOf(j));
        ((ContentValues)localObject2).put("padding", "");
        paramContext.getContentResolver().update(ContentUris.withAppendedId(Alarm.a.a, localAlarm.a), (ContentValues)localObject2, null, null);
        localCursor.moveToNext();
        break label128;
        if (k == 1)
        {
          i = Integer.parseInt(android.preference.PreferenceManager.getDefaultSharedPreferences(paramContext).getString("snooze_duration", "10"));
          break;
        }
        if (k != 2) {
          break label476;
        }
        i = Integer.parseInt(android.preference.PreferenceManager.getDefaultSharedPreferences(paramContext).getString("snooze_duration", "10"));
        break;
        if (k == 2)
        {
          localObject1 = "easy";
          break label183;
        }
        localObject1 = "normal";
        break label183;
        localCursor.close();
        ar(paramContext);
        s.a("migrateTest", "migration done");
        b.a(paramContext, "migration");
        localObject1 = localSharedPreferences.edit();
        ((SharedPreferences.Editor)localObject1).putInt("fromVersion", 30460);
        ((SharedPreferences.Editor)localObject1).apply();
        localObject1 = android.preference.PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
        ((SharedPreferences.Editor)localObject1).putBoolean("alarm_sort_order_cb", false);
        ((SharedPreferences.Editor)localObject1).apply();
        Alarm.a.b = "padding DESC,hour, minutes ASC";
        LogWriter.a(paramContext);
        LogWriter.a(paramContext, LogWriter.EventType.a, "CommonUtils", "migration_complete");
        return;
      }
      label476:
      i = 0;
    }
  }
  
  public static String aS(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (aA(paramContext) == 11)
    {
      localStringBuilder.append("m_gender:m");
      i = 1;
    }
    for (;;)
    {
      paramContext = az(paramContext);
      if (paramContext.d())
      {
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTimeInMillis(System.currentTimeMillis());
        int j = localCalendar.get(1) - paramContext.a();
        if (j > 0)
        {
          if (i != 0) {
            localStringBuilder.append(",");
          }
          localStringBuilder.append("m_age:" + j);
        }
      }
      s.a("CommonUtils", "keyword: " + localStringBuilder.toString());
      return localStringBuilder.toString();
      if (aA(paramContext) == 22)
      {
        localStringBuilder.append("m_gender:f");
        i = 1;
      }
    }
  }
  
  public static int aT(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if (paramContext != null)
    {
      if (paramContext.getType() == 1) {
        return 1;
      }
      if (paramContext.getType() == 0) {
        return 0;
      }
    }
    return -1;
  }
  
  public static String aU(Context paramContext)
  {
    try
    {
      if (aC(paramContext).getLanguage().equals("ko")) {
        return com.google.firebase.remoteconfig.a.a().a("an_hellobot_prompt_ko");
      }
      paramContext = com.google.firebase.remoteconfig.a.a().a("an_hellobot_prompt_en");
      return paramContext;
    }
    catch (Exception paramContext)
    {
      Crashlytics.logException(paramContext);
    }
    return "";
  }
  
  public static boolean aV(Context paramContext)
  {
    paramContext = aC(paramContext).getLanguage();
    return ((paramContext.startsWith("en")) || (paramContext.contains("ko"))) && (N());
  }
  
  public static String aW(Context paramContext)
  {
    return "http://help.alar.my/android/" + W(paramContext);
  }
  
  private static void aX(Context paramContext)
  {
    LogWriter.a localA = new LogWriter.a();
    Object localObject1 = android.preference.PreferenceManager.getDefaultSharedPreferences(paramContext).getAll();
    Object localObject2 = ((Map)localObject1).keySet().iterator();
    while (((Iterator)localObject2).hasNext())
    {
      String str = (String)((Iterator)localObject2).next();
      if (!str.contains("com.facebook.appevents"))
      {
        s.a("CommonUtils", "pref key: " + str + "," + ((Map)localObject1).get(str));
        localA.a(str, "" + ((Map)localObject1).get(str));
      }
    }
    localObject1 = paramContext.getSharedPreferences("default_settings", 0);
    localObject2 = ((SharedPreferences)localObject1).getString("pref_def_ringtone", null);
    int i = ((SharedPreferences)localObject1).getInt("pref_def_volume", -1);
    int j = ((SharedPreferences)localObject1).getInt("pref_def_snooze", -1);
    localA.a("default_alarm_ringtone", (String)localObject2);
    localA.a("default_alarm_volume", "" + i);
    localA.a("default_alarm_snooze_duration", "" + j);
    LogWriter.a(paramContext);
    LogWriter.a(paramContext, LogWriter.EventType.l, localA);
  }
  
  private static void aY(Context paramContext)
  {
    LogWriter.a localA = P(paramContext);
    LogWriter.a(paramContext);
    LogWriter.a(paramContext, LogWriter.EventType.o, localA);
  }
  
  private static void aZ(Context paramContext)
  {
    Cursor localCursor = b.a(paramContext.getContentResolver());
    LogWriter.a localA = new LogWriter.a();
    if (localCursor == null)
    {
      localA.a("msg", "Alarm cursor null");
      LogWriter.a(paramContext);
      LogWriter.a(paramContext, LogWriter.EventType.m, localA);
      return;
    }
    if (localCursor.moveToFirst()) {
      do
      {
        Alarm localAlarm = new Alarm(localCursor);
        String str2 = "" + localAlarm.c;
        String str1 = "" + localAlarm.d;
        if (localAlarm.d < 10) {
          str1 = "0" + localAlarm.d;
        }
        if (localAlarm.c < 10) {
          str2 = "0" + localAlarm.c;
        }
        new StringBuilder().append("").append(localAlarm.k).toString();
        String str3 = "" + localAlarm.l;
        String str4 = "" + localAlarm.i;
        String str5 = localAlarm.e.b(paramContext, true);
        String str6 = "" + localAlarm.b;
        String str7 = "" + localAlarm.g;
        String str8 = "" + localAlarm.h;
        String str9 = "" + localAlarm.q;
        String str10 = "" + localAlarm.p;
        localA.a("id", "" + localAlarm.a);
        localA.a("time", str2 + ":" + str1);
        localA.a("turnoff", e(localAlarm.k));
        localA.a("param", "" + str3);
        localA.a("alert", "" + str4);
        localA.a("repeat", "" + str5);
        localA.a("enabled", "" + str6);
        localA.a("vibrate", "" + str7);
        localA.a("label", "" + str8);
        localA.a("snooze", "" + str9);
        localA.a("volume", "" + str10);
        LogWriter.a(paramContext);
        LogWriter.a(paramContext, LogWriter.EventType.m, localA);
      } while (localCursor.moveToNext());
    }
    localCursor.close();
  }
  
  public static String aa(Context paramContext)
  {
    return Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
  }
  
  public static void ab(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("rate", 0);
    if (paramContext.getInt("count", 0) < 3)
    {
      SharedPreferences.Editor localEditor = paramContext.edit();
      localEditor.putInt("count", paramContext.getInt("count", 0) + 1);
      localEditor.apply();
    }
  }
  
  public static int ac(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("AlarmClock", 0);
    s.a("mediaVol", "volume to restore: " + paramContext.getInt("curVol", 1));
    return paramContext.getInt("curVol", -1);
  }
  
  public static boolean ad(Context paramContext)
  {
    try
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
      int i = 0;
      while (i < paramContext.size())
      {
        if (((ActivityManager.RunningAppProcessInfo)paramContext.get(i)).processName.equals("droom.sleepIfUCan"))
        {
          int j = ((ActivityManager.RunningAppProcessInfo)paramContext.get(i)).importance;
          if (j == 100) {
            return true;
          }
        }
        i += 1;
      }
      return false;
    }
    catch (Exception paramContext)
    {
      Crashlytics.logException(paramContext);
      s.a("CommonUtils", paramContext.toString());
    }
  }
  
  public static int ae(Context paramContext)
  {
    int j = droom.sleepIfUCan.internal.r.a().f();
    int i = j;
    if (j == -1) {
      i = paramContext.getSharedPreferences("AlarmClock", 0).getInt("streamType", 3);
    }
    return i;
  }
  
  public static int af(Context paramContext)
  {
    return paramContext.getSharedPreferences("AlarmClock", 0).getInt("backupCode", 0);
  }
  
  public static boolean ag(Context paramContext)
  {
    return (!d()) || (paramContext.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0);
  }
  
  public static void ah(Context paramContext)
  {
    do
    {
      do
      {
        do
        {
          try
          {
            List localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(2);
            localObject = localList.iterator();
            while (((Iterator)localObject).hasNext())
            {
              ActivityManager.RunningTaskInfo localRunningTaskInfo = (ActivityManager.RunningTaskInfo)((Iterator)localObject).next();
              Log.e("CommonUtils", "topActivity: " + localRunningTaskInfo.topActivity);
            }
            if (!c()) {
              break;
            }
          }
          catch (Exception localException)
          {
            Log.e("CommonUtils", localException.toString());
            Crashlytics.logException(localException);
            localException.printStackTrace();
            LogWriter.a(paramContext);
            LogWriter.a(paramContext, LogWriter.EventType.a, "CommonUtils", "app_ended_error", new LogWriter.a("msg", "checkAndFinishActivities"));
            return;
          }
          localComponentName = ((ActivityManager.RunningTaskInfo)localException.get(0)).topActivity;
          Log.e("CommonUtils", "topActivity:" + localComponentName.getPackageName());
          LogWriter.a(paramContext);
          LogWriter.a(paramContext, LogWriter.EventType.a, "CommonUtils", "app_end_logic", new LogWriter.a("topActivity", localComponentName.getPackageName()));
          if (!localComponentName.getPackageName().equals(paramContext.getPackageName()))
          {
            Log.e("CommonUtils", "Send to background");
            e(paramContext, "killed_from_background");
            h(paramContext, ", send to background");
            return;
          }
          if (!aP(paramContext))
          {
            Log.e("CommonUtils", "screen off");
            e(paramContext, "killed_from_fg_screenoff");
            h(paramContext, "foreground, screen status off");
          }
        } while (ad(paramContext));
        Log.e("CommonUtils", "app in bg");
        h(paramContext, "top activity, but in bg");
        return;
      } while (localComponentName.size() <= 1);
      Object localObject = ((ActivityManager.RunningTaskInfo)localComponentName.get(0)).topActivity;
      ComponentName localComponentName = ((ActivityManager.RunningTaskInfo)localComponentName.get(1)).topActivity;
      Log.e("CommonUtils", "topActivity:" + localObject);
      Log.e("CommonUtils", "topActivity2:" + localComponentName);
      if (((((ComponentName)localObject).getPackageName().toLowerCase().contains("launcher")) || (((ComponentName)localObject).getPackageName().toLowerCase().contains("home"))) && (localComponentName.getPackageName().equals(paramContext.getPackageName())))
      {
        Log.e("CommonUtils", "Send to background");
        e(paramContext, "killed_from_background");
        h(paramContext, ", send to background");
        return;
      }
      if (!aP(paramContext))
      {
        Log.e("CommonUtils", "screen off");
        e(paramContext, "killed_from_fg_screenoff");
        h(paramContext, "foreground, screen status off");
      }
    } while (ad(paramContext));
    Log.e("CommonUtils", "app in bg");
    h(paramContext, "top activity, but in bg");
  }
  
  public static void ai(Context paramContext)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.MAIN");
      localIntent.addCategory("android.intent.category.HOME");
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      Crashlytics.logException(paramContext);
    }
  }
  
  public static String aj(Context paramContext)
  {
    Object localObject = "https://i.imgur.com/n41vuCb.png";
    try
    {
      String str = com.google.firebase.remoteconfig.a.a().a("an_tutorial_image");
      localObject = str;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Crashlytics.logException(localException);
      }
    }
    s.a("CommonUtils", "tutorialConfig image url:" + (String)localObject);
    Picasso.a(paramContext).a((String)localObject).b();
    return localObject;
  }
  
  public static boolean ak(Context paramContext)
  {
    return paramContext.getSharedPreferences("AlarmClock", 0).getBoolean("pref_key_mission_tutorial_finished", false);
  }
  
  public static void al(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("AlarmClock", 0).edit();
    paramContext.putBoolean("pref_key_mission_tutorial_finished", true);
    paramContext.apply();
  }
  
  public static void am(Context paramContext)
  {
    MobVistaSDKImpl localMobVistaSDKImpl = MobVistaSDKFactory.getMobVistaSDK();
    localMobVistaSDKImpl.init(localMobVistaSDKImpl.getMVConfigurationMap("31075", "149f414b7b53bc056a7accd9c1f221bd"), paramContext);
  }
  
  public static boolean an(Context paramContext)
  {
    return paramContext.getSharedPreferences("AlarmClock", 0).getBoolean("pref_installed_app_guide_displayed", false);
  }
  
  public static int ao(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("Promotion", 0);
    if (paramContext.getBoolean("isRedeemed", false))
    {
      if (paramContext.getBoolean("pref_viral_upgraded", false)) {
        return droom.sleepIfUCan.a.k;
      }
      return droom.sleepIfUCan.a.l;
    }
    return droom.sleepIfUCan.a.j;
  }
  
  public static int ap(Context paramContext)
  {
    long l = paramContext.getSharedPreferences("First", 0).getLong("Date", 0L);
    return (int)((System.currentTimeMillis() - l) / 3600000L);
  }
  
  public static int aq(Context paramContext)
  {
    int i = 0;
    paramContext = paramContext.getSharedPreferences("AlarmClock", 0);
    if (!paramContext.getBoolean("pref_key_tutorial_power_off", true)) {
      i = 11;
    }
    do
    {
      return i;
      if (!paramContext.getBoolean("pref_key_tutorial_external_perm", true)) {
        return 13;
      }
      if (!paramContext.getBoolean("pref_key_tutorial_photo_sensitivity", true)) {
        return 14;
      }
      if (!paramContext.getBoolean("pref_key_tutorial_sony_stamina", true)) {
        return 17;
      }
      if (!paramContext.getBoolean("pref_key_tutorial_smart_manager", true)) {
        return 18;
      }
      if (!paramContext.getBoolean("pref_key_tutorial_photo_perm", true)) {
        return 19;
      }
    } while (paramContext.getBoolean("pref_key_tutorial_barcode_perm", true));
    return 20;
  }
  
  public static void ar(Context paramContext)
  {
    paramContext = android.preference.PreferenceManager.getDefaultSharedPreferences(paramContext);
    SharedPreferences.Editor localEditor;
    try
    {
      paramContext.getString("new_gradually_increase", "0");
      return;
    }
    catch (Exception localException)
    {
      localEditor = paramContext.edit();
      if (!paramContext.getBoolean("new_gradually_increase", false)) {
        break label60;
      }
    }
    localEditor.putString("new_gradually_increase", "1");
    for (;;)
    {
      localEditor.apply();
      return;
      label60:
      localEditor.putString("new_gradually_increase", "0");
    }
  }
  
  public static boolean as(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("com.samsung.android.sm.ACTION_BATTERY");
    return a(paramContext, localIntent);
  }
  
  @TargetApi(23)
  public static boolean at(Context paramContext)
  {
    return ((NotificationManager)paramContext.getSystemService("notification")).isNotificationPolicyAccessGranted();
  }
  
  public static boolean au(Context paramContext)
  {
    return paramContext.getSharedPreferences("AlarmClock", 0).getBoolean("pref_should_restore_dnd_mode", false);
  }
  
  @TargetApi(24)
  public static void av(Context paramContext)
  {
    try
    {
      ((NotificationManager)paramContext.getSystemService("notification")).setInterruptionFilter(4);
      return;
    }
    catch (Exception paramContext)
    {
      s.a("CommonUtils", paramContext.toString());
    }
  }
  
  @TargetApi(24)
  public static void aw(Context paramContext)
  {
    try
    {
      ((NotificationManager)paramContext.getSystemService("notification")).setInterruptionFilter(3);
      return;
    }
    catch (Exception paramContext)
    {
      s.a("CommonUtils", paramContext.toString());
    }
  }
  
  @TargetApi(17)
  public static int ax(Context paramContext)
  {
    try
    {
      int i = Settings.Global.getInt(paramContext.getContentResolver(), "zen_mode");
      return i;
    }
    catch (Exception paramContext) {}
    return -99;
  }
  
  public static boolean ay(Context paramContext)
  {
    return paramContext.getSharedPreferences("AlarmClock", 0).getBoolean("pref_should_display_dnd_tutorial", false);
  }
  
  public static c az(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("pref_user_info", 0);
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(System.currentTimeMillis());
    return new c(paramContext.getBoolean("pref_birthday_has_set", false), paramContext.getInt("pref_birthday_year", localCalendar.get(1)), paramContext.getInt("pref_birthday_month", localCalendar.get(2)), paramContext.getInt("pref_birthday_day", localCalendar.get(5)));
  }
  
  public static int b(int paramInt)
  {
    int i;
    if (paramInt == 1032) {
      i = paramInt * 4;
    }
    do
    {
      return i;
      if (paramInt == 1632) {
        return paramInt * 2;
      }
      if (paramInt == 1024) {
        return paramInt * 2;
      }
      if (paramInt == 1280) {
        return paramInt * 2;
      }
      if (paramInt == 1600) {
        return paramInt * 2;
      }
      if (paramInt == 1296) {
        return paramInt * 2;
      }
      if (paramInt == 1380) {
        return paramInt * 4;
      }
      i = paramInt;
    } while (paramInt != 1496);
    return paramInt * 4;
  }
  
  public static int b(Context paramContext)
  {
    int j = 2131099956;
    b = y(paramContext);
    int i;
    if (b == 1) {
      i = 2131099715;
    }
    do
    {
      do
      {
        return i;
        i = j;
      } while (b == 3);
      if (b == 0) {
        return 2131099816;
      }
      if (b == 2) {
        return 2131099840;
      }
      if (b == 4) {
        return 2131099902;
      }
      if (b == 5) {
        return 2131099948;
      }
      if (b == 6) {
        return 2131099992;
      }
      if (b == 7) {
        return 2131099692;
      }
      if (b == 14) {
        return 2131099687;
      }
      if (b == 12) {
        return 2131099697;
      }
      if (b == 13) {
        return 2131099702;
      }
      if (b == 8) {
        return 2131099923;
      }
      if (b == 10) {
        return 2131099909;
      }
      if (b == 9) {
        return 2131099916;
      }
      if (b == 15) {
        return 2131099930;
      }
      i = j;
    } while (b != 11);
    return 2131099827;
  }
  
  public static int b(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getResources();
    String str = paramContext.getResourceEntryName(paramInt);
    s.a("CommonUtils", "drawable name: " + str);
    int i = paramContext.getIdentifier(str, "drawable", "droom.sleepIfUCan");
    if (i != 0) {
      paramInt = i;
    }
    return paramInt;
  }
  
  public static Context b(Context paramContext, Configuration paramConfiguration)
  {
    Context localContext = paramContext;
    Resources localResources;
    if (d != null)
    {
      localContext = paramContext;
      if (Build.VERSION.SDK_INT >= 17)
      {
        localResources = paramContext.getResources();
        if (paramConfiguration == null) {
          break label66;
        }
      }
    }
    for (;;)
    {
      paramConfiguration.locale = d;
      localContext = paramContext;
      if (e())
      {
        paramConfiguration.setLocale(d);
        localContext = paramContext.createConfigurationContext(paramConfiguration);
      }
      localResources.updateConfiguration(paramConfiguration, localResources.getDisplayMetrics());
      return localContext;
      label66:
      if (localResources.getConfiguration() != null) {
        paramConfiguration = localResources.getConfiguration();
      } else {
        paramConfiguration = new Configuration();
      }
    }
  }
  
  public static String b(Context paramContext, double paramDouble1, double paramDouble2)
  {
    return "https://api.alar.my/richweather?v=1.01&latitude=" + paramDouble1 + "&longitude=" + paramDouble2 + "&language=" + Locale.getDefault().getLanguage();
  }
  
  /* Error */
  public static String b(Uri paramUri, ContentResolver paramContentResolver, Context paramContext)
  {
    // Byte code:
    //   0: ldc_w 2128
    //   3: aload_0
    //   4: invokevirtual 2131	android/net/Uri:getScheme	()Ljava/lang/String;
    //   7: invokevirtual 1481	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   10: ifeq +29 -> 39
    //   13: new 2133	java/io/File
    //   16: dup
    //   17: aload_0
    //   18: invokevirtual 956	android/net/Uri:getPath	()Ljava/lang/String;
    //   21: invokespecial 2134	java/io/File:<init>	(Ljava/lang/String;)V
    //   24: invokevirtual 2137	java/io/File:exists	()Z
    //   27: ifeq +10 -> 37
    //   30: aload_0
    //   31: invokevirtual 956	android/net/Uri:getPath	()Ljava/lang/String;
    //   34: astore_0
    //   35: aload_0
    //   36: areturn
    //   37: aconst_null
    //   38: areturn
    //   39: iconst_1
    //   40: anewarray 146	java/lang/String
    //   43: astore 7
    //   45: aload 7
    //   47: iconst_0
    //   48: ldc -106
    //   50: aastore
    //   51: aload_1
    //   52: aload_0
    //   53: aload 7
    //   55: aconst_null
    //   56: aconst_null
    //   57: aconst_null
    //   58: invokevirtual 160	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   61: astore 6
    //   63: aload 6
    //   65: astore 5
    //   67: aload 6
    //   69: invokeinterface 1684 1 0
    //   74: pop
    //   75: aload 6
    //   77: astore 5
    //   79: aload 6
    //   81: aload 6
    //   83: aload 7
    //   85: iconst_0
    //   86: aaload
    //   87: invokeinterface 182 2 0
    //   92: invokeinterface 185 2 0
    //   97: astore 7
    //   99: aload 6
    //   101: astore 5
    //   103: aload 6
    //   105: invokeinterface 204 1 0
    //   110: aload 6
    //   112: astore 5
    //   114: ldc 35
    //   116: new 37	java/lang/StringBuilder
    //   119: dup
    //   120: invokespecial 40	java/lang/StringBuilder:<init>	()V
    //   123: ldc_w 2139
    //   126: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: aload_0
    //   130: invokevirtual 919	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   133: ldc_w 2141
    //   136: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: aload 7
    //   141: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: invokevirtual 49	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   147: invokestatic 54	droom/sleepIfUCan/utils/s:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   150: aload 7
    //   152: astore_0
    //   153: aload 6
    //   155: ifnull -120 -> 35
    //   158: aload 6
    //   160: invokeinterface 204 1 0
    //   165: aload 7
    //   167: areturn
    //   168: astore 5
    //   170: aconst_null
    //   171: astore 5
    //   173: aload_2
    //   174: invokestatic 995	droom/sleepIfUCan/utils/e:aM	(Landroid/content/Context;)Z
    //   177: istore 4
    //   179: iload 4
    //   181: ifne +17 -> 198
    //   184: aload 5
    //   186: ifnull +10 -> 196
    //   189: aload 5
    //   191: invokeinterface 204 1 0
    //   196: aconst_null
    //   197: areturn
    //   198: aload_1
    //   199: getstatic 144	android/provider/MediaStore$Audio$Media:EXTERNAL_CONTENT_URI	Landroid/net/Uri;
    //   202: iconst_3
    //   203: anewarray 146	java/lang/String
    //   206: dup
    //   207: iconst_0
    //   208: ldc -108
    //   210: aastore
    //   211: dup
    //   212: iconst_1
    //   213: ldc -106
    //   215: aastore
    //   216: dup
    //   217: iconst_2
    //   218: ldc -104
    //   220: aastore
    //   221: aconst_null
    //   222: aconst_null
    //   223: ldc -102
    //   225: invokevirtual 160	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   228: astore_1
    //   229: aload_1
    //   230: ifnull +17 -> 247
    //   233: aload_1
    //   234: astore 5
    //   236: aload_1
    //   237: invokeinterface 172 1 0
    //   242: istore_3
    //   243: iload_3
    //   244: ifne +15 -> 259
    //   247: aload_1
    //   248: ifnull +9 -> 257
    //   251: aload_1
    //   252: invokeinterface 204 1 0
    //   257: aconst_null
    //   258: areturn
    //   259: aload_1
    //   260: astore 5
    //   262: aload_1
    //   263: invokeinterface 1149 1 0
    //   268: ifeq -21 -> 247
    //   271: aload_1
    //   272: astore 5
    //   274: aload_1
    //   275: aload_1
    //   276: ldc -106
    //   278: invokeinterface 182 2 0
    //   283: invokeinterface 185 2 0
    //   288: astore_2
    //   289: aload_2
    //   290: ifnull -31 -> 259
    //   293: aload_1
    //   294: astore 5
    //   296: ldc 35
    //   298: new 37	java/lang/StringBuilder
    //   301: dup
    //   302: invokespecial 40	java/lang/StringBuilder:<init>	()V
    //   305: ldc_w 2143
    //   308: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   311: aload_2
    //   312: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   315: ldc_w 2145
    //   318: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   321: aload_1
    //   322: aload_1
    //   323: ldc -104
    //   325: invokeinterface 182 2 0
    //   330: invokeinterface 185 2 0
    //   335: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   338: invokevirtual 49	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   341: invokestatic 54	droom/sleepIfUCan/utils/s:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   344: aload_1
    //   345: astore 5
    //   347: aload_0
    //   348: invokevirtual 931	android/net/Uri:toString	()Ljava/lang/String;
    //   351: aload_2
    //   352: invokestatic 189	android/provider/MediaStore$Audio$Media:getContentUriForPath	(Ljava/lang/String;)Landroid/net/Uri;
    //   355: aload_1
    //   356: aload_1
    //   357: ldc -108
    //   359: invokeinterface 182 2 0
    //   364: invokeinterface 185 2 0
    //   369: invokestatic 195	android/net/Uri:withAppendedPath	(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    //   372: invokevirtual 931	android/net/Uri:toString	()Ljava/lang/String;
    //   375: invokevirtual 374	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   378: istore 4
    //   380: iload 4
    //   382: ifeq -123 -> 259
    //   385: aload_2
    //   386: astore_0
    //   387: aload_1
    //   388: ifnull -353 -> 35
    //   391: aload_1
    //   392: invokeinterface 204 1 0
    //   397: aload_2
    //   398: areturn
    //   399: astore_0
    //   400: aconst_null
    //   401: astore 5
    //   403: aload 5
    //   405: ifnull +10 -> 415
    //   408: aload 5
    //   410: invokeinterface 204 1 0
    //   415: aload_0
    //   416: athrow
    //   417: astore_0
    //   418: goto -15 -> 403
    //   421: astore_0
    //   422: goto -19 -> 403
    //   425: astore 5
    //   427: aload 6
    //   429: astore 5
    //   431: goto -258 -> 173
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	434	0	paramUri	Uri
    //   0	434	1	paramContentResolver	ContentResolver
    //   0	434	2	paramContext	Context
    //   242	2	3	i	int
    //   177	204	4	bool	boolean
    //   65	48	5	localCursor1	Cursor
    //   168	1	5	localException1	Exception
    //   171	238	5	localContentResolver	ContentResolver
    //   425	1	5	localException2	Exception
    //   429	1	5	localCursor2	Cursor
    //   61	367	6	localCursor3	Cursor
    //   43	123	7	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   39	45	168	java/lang/Exception
    //   51	63	168	java/lang/Exception
    //   39	45	399	finally
    //   51	63	399	finally
    //   67	75	417	finally
    //   79	99	417	finally
    //   103	110	417	finally
    //   114	150	417	finally
    //   236	243	417	finally
    //   262	271	417	finally
    //   274	289	417	finally
    //   296	344	417	finally
    //   347	380	417	finally
    //   173	179	421	finally
    //   198	229	421	finally
    //   67	75	425	java/lang/Exception
    //   79	99	425	java/lang/Exception
    //   103	110	425	java/lang/Exception
    //   114	150	425	java/lang/Exception
  }
  
  public static String b(String paramString)
  {
    if (paramString != null)
    {
      if (paramString.contains("QQQ92fasd1dQQQ")) {
        return paramString.substring(paramString.indexOf("QQQ92fasd1dQQQ") + "QQQ92fasd1dQQQ".length());
      }
      if (paramString.contains("QR-CODE581274")) {
        return paramString.substring("QR-CODE581274".length());
      }
      if (paramString.contains("BARCODE581274")) {
        return paramString.substring("BARCODE581274".length());
      }
    }
    return null;
  }
  
  public static String b(String paramString1, String paramString2)
  {
    String str;
    if (paramString1 == null) {
      str = null;
    }
    do
    {
      return str;
      str = paramString1;
    } while (paramString1.split("/").length > 1);
    if (paramString1.contains("/")) {
      return g(paramString1) + "/" + paramString2;
    }
    if (paramString2 == null) {
      return null;
    }
    if (paramString2.contains("/")) {
      return paramString1 + "/" + f(paramString2);
    }
    return paramString1 + "/" + paramString2;
  }
  
  public static void b(Context paramContext, int paramInt, boolean paramBoolean)
  {
    paramContext = paramContext.getSharedPreferences("AlarmClock", 0);
    SharedPreferences.Editor localEditor = paramContext.edit();
    if (paramInt == 11) {
      if (paramBoolean) {
        if (!paramContext.getBoolean("pref_key_tutorial_power_off_real", false)) {
          localEditor.putBoolean("pref_key_tutorial_power_off", false);
        }
      }
    }
    for (;;)
    {
      localEditor.apply();
      return;
      localEditor.putBoolean("pref_key_tutorial_power_off", true);
      continue;
      if (paramInt == 14)
      {
        if (paramBoolean)
        {
          if (!paramContext.getBoolean("pref_key_tutorial_photo_sensitivity_real", false)) {
            localEditor.putBoolean("pref_key_tutorial_photo_sensitivity", false);
          }
        }
        else {
          localEditor.putBoolean("pref_key_tutorial_photo_sensitivity", true);
        }
      }
      else if (paramInt == 17)
      {
        if (paramBoolean)
        {
          if (!paramContext.getBoolean("pref_key_tutorial_sony_stamina_real", false)) {
            localEditor.putBoolean("pref_key_tutorial_sony_stamina", false);
          }
        }
        else {
          localEditor.putBoolean("pref_key_tutorial_sony_stamina", true);
        }
      }
      else if (paramInt == 18)
      {
        if (paramBoolean)
        {
          if (!paramContext.getBoolean("pref_key_tutorial_smart_manager_real", false)) {
            localEditor.putBoolean("pref_key_tutorial_smart_manager", false);
          }
        }
        else {
          localEditor.putBoolean("pref_key_tutorial_smart_manager", true);
        }
      }
      else if (paramInt == 13)
      {
        if (paramBoolean) {
          localEditor.putBoolean("pref_key_tutorial_external_perm", false);
        } else {
          localEditor.putBoolean("pref_key_tutorial_external_perm", true);
        }
      }
      else if (paramInt == 19)
      {
        if (paramBoolean) {
          localEditor.putBoolean("pref_key_tutorial_photo_perm", false);
        } else {
          localEditor.putBoolean("pref_key_tutorial_photo_perm", true);
        }
      }
      else if (paramInt == 20) {
        if (paramBoolean) {
          localEditor.putBoolean("pref_key_tutorial_barcode_perm", false);
        } else {
          localEditor.putBoolean("pref_key_tutorial_barcode_perm", true);
        }
      }
    }
  }
  
  public static void b(Context paramContext, long paramLong)
  {
    if (paramLong == 0L) {
      s.a("CommonUtils", "setNextAlertTime: 0");
    }
    for (;;)
    {
      paramContext = paramContext.getSharedPreferences("pref_last_open_date", 0).edit();
      paramContext.putLong("pref_key_next_alert", paramLong);
      paramContext.apply();
      return;
      s.a("CommonUtils", "setNextAlertTime:" + a(paramLong));
    }
  }
  
  public static void b(Context paramContext, String paramString1, String paramString2)
  {
    s.a("CommonUtils", "setting user property : " + paramString1 + " to " + paramString2);
    int i = 0;
    for (;;)
    {
      if (i < droom.sleepIfUCan.a.i.length)
      {
        if (droom.sleepIfUCan.a.i[i].equals(paramString1))
        {
          s.a("CommonUtils", "valid firebase prop. logging user property " + paramString1);
          FirebaseAnalytics.getInstance(paramContext).a(paramString1, paramString2);
        }
      }
      else
      {
        paramContext = d(paramString1);
        s.a("CommonUtils", "firebase : " + paramString1 + " maps to " + paramContext);
        paramString1 = new Bundle();
        paramString1.putString(paramContext, paramString2);
        l.a(paramString1);
        return;
      }
      if (i == droom.sleepIfUCan.a.i.length - 1) {
        s.a("CommonUtils", "invalid firebase prop. not logging user property " + paramString1);
      }
      i += 1;
    }
  }
  
  public static void b(Context paramContext, boolean paramBoolean)
  {
    paramContext = paramContext.getSharedPreferences("notification", 0).edit();
    paramContext.putBoolean("icon", paramBoolean);
    paramContext.apply();
  }
  
  public static void b(Window paramWindow)
  {
    paramWindow.addFlags(2048);
    paramWindow.clearFlags(1024);
  }
  
  public static boolean b()
  {
    return droom.sleepIfUCan.a.f > 18;
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    if (c())
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
      while (paramContext.hasNext()) {
        if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
          return true;
        }
      }
      return false;
    }
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  @TargetApi(23)
  private static void ba(Context paramContext)
  {
    int i = 0;
    if ((!d()) || (paramContext == null)) {
      return;
    }
    String[] arrayOfString = new String[18];
    arrayOfString[0] = "android.permission.CAMERA";
    arrayOfString[1] = "android.permission.RECEIVE_BOOT_COMPLETED";
    arrayOfString[2] = "android.permission.WAKE_LOCK";
    arrayOfString[3] = "android.permission.VIBRATE";
    arrayOfString[4] = "android.permission.DISABLE_KEYGUARD";
    arrayOfString[5] = "android.permission.INTERNET";
    arrayOfString[6] = "android.permission.READ_EXTERNAL_STORAGE";
    arrayOfString[7] = "android.permission.WRITE_EXTERNAL_STORAGE";
    arrayOfString[8] = "android.permission.WRITE_SETTINGS";
    arrayOfString[9] = "android.permission.CHANGE_CONFIGURATION";
    arrayOfString[10] = "android.permission.ACCESS_COARSE_LOCATION";
    arrayOfString[11] = "android.permission.MODIFY_AUDIO_SETTINGS";
    arrayOfString[12] = "android.permission.KILL_BACKGROUND_PROCESSES";
    arrayOfString[13] = "android.permission.READ_PHONE_STATE";
    arrayOfString[14] = "android.permission.ACCESS_WIFI_STATE";
    arrayOfString[15] = "android.permission.ACCESS_NETWORK_STATE";
    arrayOfString[16] = "android.permission.ACCESS_NOTIFICATION_POLICY";
    arrayOfString[17] = "android.permission.GET_TASKS";
    LogWriter.a localA = new LogWriter.a();
    if (i < arrayOfString.length)
    {
      String str2 = arrayOfString[i];
      if (paramContext.checkSelfPermission(arrayOfString[i]) == 0) {}
      for (str1 = "Y";; str1 = "N")
      {
        localA.a(str2, str1);
        i += 1;
        break;
      }
    }
    if (e()) {
      if (!at(paramContext)) {
        break label254;
      }
    }
    label254:
    for (String str1 = "Y";; str1 = " N")
    {
      localA.a("dnd_access_granted", str1);
      LogWriter.a(paramContext);
      LogWriter.a(paramContext, LogWriter.EventType.n, localA);
      return;
    }
  }
  
  public static int c(int paramInt)
  {
    if (paramInt > 8000) {
      return 16;
    }
    if (paramInt > 6000) {
      return 8;
    }
    if (paramInt > 4000) {
      return 4;
    }
    if (paramInt > 2000) {
      return 2;
    }
    return 1;
  }
  
  public static int c(Context paramContext)
  {
    int j = 2131231276;
    b = y(paramContext);
    int i;
    if (b == 1) {
      i = 2131231266;
    }
    do
    {
      do
      {
        return i;
        i = j;
      } while (b == 3);
      if (b == 0) {
        return 2131231267;
      }
      if (b == 2) {
        return 2131231269;
      }
      if (b == 4) {
        return 2131231270;
      }
      if (b == 5) {
        return 2131231275;
      }
      if (b == 6) {
        return 2131231277;
      }
      if (b == 7) {
        return 2131231265;
      }
      if (b == 14) {
        return 2131231265;
      }
      if (b == 12) {
        return 2131231265;
      }
      if (b == 13) {
        return 2131231265;
      }
      if (b == 8) {
        return 2131231273;
      }
      if (b == 10) {
        return 2131231271;
      }
      if (b == 9) {
        return 2131231272;
      }
      if (b == 15) {
        return 2131231274;
      }
      i = j;
    } while (b != 11);
    return 2131231268;
  }
  
  public static int c(Context paramContext, int paramInt)
  {
    return Color.parseColor(paramContext.getResources().getString(paramInt));
  }
  
  public static String c(String paramString)
  {
    if (paramString != null)
    {
      if (paramString.contains("QQQ92fasd1dQQQ"))
      {
        int i = paramString.indexOf("QQQ92fasd1dQQQ");
        return paramString.substring("QR-CODE581274".length(), i);
      }
      if (paramString.contains("QR-CODE581274")) {
        return paramString.substring("QR-CODE581274".length());
      }
      if (paramString.contains("BARCODE581274")) {
        return paramString.substring("BARCODE581274".length());
      }
    }
    return null;
  }
  
  public static String c(String paramString1, String paramString2)
  {
    if (paramString2.length() == 0) {
      return null;
    }
    if (paramString1.equals("QR_CODE")) {}
    for (paramString1 = "QR-CODE581274" + paramString2;; paramString1 = "BARCODE581274" + paramString2)
    {
      s.a("CommonUtils", "scannedCode: " + paramString1);
      return paramString1;
    }
  }
  
  public static void c(Context paramContext, int paramInt, boolean paramBoolean)
  {
    paramContext = paramContext.getSharedPreferences("AlarmClock", 0).edit();
    if (paramInt == 18) {
      paramContext.putBoolean("pref_key_tutorial_smart_manager_real", paramBoolean);
    }
    for (;;)
    {
      paramContext.apply();
      return;
      if (paramInt == 11) {
        paramContext.putBoolean("pref_key_tutorial_power_off_real", paramBoolean);
      } else if (paramInt == 14) {
        paramContext.putBoolean("pref_key_tutorial_photo_sensitivity_real", paramBoolean);
      } else if (paramInt == 17) {
        paramContext.putBoolean("pref_key_tutorial_sony_stamina_real", paramBoolean);
      }
    }
  }
  
  public static void c(Context paramContext, long paramLong)
  {
    paramContext = paramContext.getSharedPreferences("AlarmClock", 0).edit();
    paramContext.putLong("pref_key_alarm_init_receive", paramLong);
    paramContext.apply();
  }
  
  public static void c(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("AlarmClock", 0).edit();
    s.a("CommonUtils", "set news country name: " + paramString);
    paramContext.putString("news_country_name", paramString);
    paramContext.apply();
  }
  
  public static void c(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = paramContext.getSharedPreferences("pref_log", 0).edit();
    paramContext.putString("pref_user_prop_country_code", paramString1);
    paramContext.putString("pref_user_prop_administrative_area", paramString2);
    paramContext.apply();
  }
  
  public static void c(Context paramContext, boolean paramBoolean)
  {
    paramContext = paramContext.getSharedPreferences("Promotion", 0).edit();
    paramContext.putBoolean("isRedeemed", paramBoolean);
    paramContext.apply();
  }
  
  public static boolean c()
  {
    return droom.sleepIfUCan.a.f > 20;
  }
  
  public static int d(Context paramContext)
  {
    int j = 2131231259;
    b = y(paramContext);
    int i;
    if (b == 1) {
      i = 2131231249;
    }
    do
    {
      do
      {
        return i;
        i = j;
      } while (b == 3);
      if (b == 0) {
        return 2131231250;
      }
      if (b == 2) {
        return 2131231252;
      }
      if (b == 4) {
        return 2131231253;
      }
      if (b == 5) {
        return 2131231258;
      }
      if (b == 6) {
        return 2131231260;
      }
      if (b == 7) {
        return 2131231246;
      }
      if (b == 14) {
        return 2131231245;
      }
      if (b == 12) {
        return 2131231247;
      }
      if (b == 13) {
        return 2131231248;
      }
      if (b == 8) {
        return 2131231256;
      }
      if (b == 10) {
        return 2131231254;
      }
      if (b == 9) {
        return 2131231255;
      }
      if (b == 15) {
        return 2131231257;
      }
      i = j;
    } while (b != 11);
    return 2131231251;
  }
  
  public static String d(int paramInt)
  {
    if (droom.sleepIfUCan.a.e)
    {
      if (7 == paramInt) {
        return "3fa384dfc3db4548ac1971907b5728cb";
      }
      if (9 == paramInt) {
        return "57aeb829dd614af68bcf054312f842d0";
      }
      if (6 == paramInt) {
        return "e6fc90490b4844c8b0539f90307952d4";
      }
      if (10 == paramInt) {
        return "76a3fefaced247959582d2d2df6f4757";
      }
      return "cf4fce1839314cff9882f99dd891c0b4";
    }
    if (4 == paramInt) {
      return "a2df70be08474ac69e62176f3dae36e1";
    }
    if (5 == paramInt) {
      return "64b18816119947cdbcd51442f4d3c2db";
    }
    if (6 == paramInt) {
      return C();
    }
    String[] arrayOfString;
    if (7 == paramInt)
    {
      arrayOfString = A().split(",");
      if (arrayOfString[0].equals("A"))
      {
        s.a("CommonUtils", "Segment A: " + arrayOfString[1]);
        return arrayOfString[1];
      }
      if (arrayOfString[0].equals("B"))
      {
        s.a("CommonUtils", "Segment B: " + arrayOfString[1]);
        return arrayOfString[1];
      }
      s.a("CommonUtils", "using default cache config " + arrayOfString[0]);
      return "923f886c657b4d2e9bf58fc3a2900c57";
    }
    if (8 == paramInt) {
      return "3ee3a9b95c9a4605bb950ff6984633b8";
    }
    if (9 == paramInt)
    {
      arrayOfString = z().split(",");
      if (arrayOfString[0].equals("A"))
      {
        s.a("CommonUtils", "Segment A: " + arrayOfString[1]);
        return arrayOfString[1];
      }
      if (arrayOfString[0].equals("B"))
      {
        s.a("CommonUtils", "Segment B: " + arrayOfString[1]);
        return arrayOfString[1];
      }
      s.a("CommonUtils", "using default cache config " + arrayOfString[0]);
      return "05d3e04f7ae74ebda61a5133ece23bbd";
    }
    if (10 == paramInt) {
      return "7d9a7b368bca4af0b18b25b0b7e2888b";
    }
    s.a("CommonUtils", "getAdUnitIt error!! add unit id is invalid");
    return null;
  }
  
  public static String d(String paramString)
  {
    int i = -1;
    switch (paramString.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return "an_" + paramString;
        if (paramString.equals("unique_id"))
        {
          i = 0;
          continue;
          if (paramString.equals("theme_index"))
          {
            i = 1;
            continue;
            if (paramString.equals("pause_duration"))
            {
              i = 2;
              continue;
              if (paramString.equals("new_gradually_increase"))
              {
                i = 3;
                continue;
                if (paramString.equals("new_output_source"))
                {
                  i = 4;
                  continue;
                  if (paramString.equals("snooze_limit"))
                  {
                    i = 5;
                    continue;
                    if (paramString.equals("dismiss_sensitivity"))
                    {
                      i = 6;
                      continue;
                      if (paramString.equals("panel_setting"))
                      {
                        i = 7;
                        continue;
                        if (paramString.equals("setting_battery_save"))
                        {
                          i = 8;
                          continue;
                          if (paramString.equals("setting_celsius"))
                          {
                            i = 9;
                            continue;
                            if (paramString.equals("setting_location_name")) {
                              i = 10;
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
        break;
      }
    }
    return "unique_id";
    return "setting_theme";
    return "setting_mute_in_dismiss_mode";
    return "setting_volume_crescendo";
    return "setting_use_built_in_speaker";
    return "setting_num_of_snooze";
    return "setting_accuracy";
    return "setting_showtoday_panel";
    return "setting_battery_save";
    return "setting_celsius";
    return "setting_location_name";
  }
  
  public static void d(Context paramContext, int paramInt)
  {
    FirebaseAnalytics.getInstance(paramContext);
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("pref_last_open_date", 0);
    SharedPreferences.Editor localEditor = localSharedPreferences.edit();
    if (paramInt == 0)
    {
      paramInt = localSharedPreferences.getInt("pref_num_add_default", 0) + 1;
      localEditor.putInt("pref_num_add_default", paramInt);
      b(paramContext, "add_default", "" + paramInt);
    }
    for (;;)
    {
      localEditor.apply();
      return;
      if (paramInt == 1)
      {
        paramInt = localSharedPreferences.getInt("pref_num_add_picture", 0) + 1;
        localEditor.putInt("pref_num_add_picture", paramInt);
        b(paramContext, "add_picture", "" + paramInt);
      }
      else if (paramInt == 2)
      {
        paramInt = localSharedPreferences.getInt("pref_num_add_shake", 0) + 1;
        localEditor.putInt("pref_num_add_shake", paramInt);
        b(paramContext, "add_shake", "" + paramInt);
      }
      else if (paramInt == 3)
      {
        paramInt = localSharedPreferences.getInt("pref_num_add_math", 0) + 1;
        localEditor.putInt("pref_num_add_math", paramInt);
        b(paramContext, "add_math", "" + paramInt);
      }
      else if (paramInt == 4)
      {
        paramInt = localSharedPreferences.getInt("pref_num_add_barcode", 0) + 1;
        localEditor.putInt("pref_num_add_barcode", paramInt);
        b(paramContext, "add_barcode", "" + paramInt);
      }
    }
  }
  
  public static void d(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("weatherInfo", 0).edit();
    paramContext.putString("jsonString", paramString);
    paramContext.putLong("weatherRefreshTime", System.currentTimeMillis());
    paramContext.apply();
  }
  
  public static void d(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = new FileInputStream(new File(paramString1));
    paramString1 = new File(paramString2);
    if (!paramString1.exists()) {
      paramString1.createNewFile();
    }
    paramString1 = new FileOutputStream(paramString2);
    paramString2 = new byte['Ѐ'];
    for (;;)
    {
      int i = paramContext.read(paramString2);
      if (i <= 0) {
        break;
      }
      paramString1.write(paramString2, 0, i);
    }
    paramString1.flush();
    paramString1.close();
    paramContext.close();
  }
  
  public static void d(Context paramContext, boolean paramBoolean)
  {
    paramContext = paramContext.getSharedPreferences("AlarmClock", 0).edit();
    paramContext.putBoolean("pref_should_restore_dnd_mode", paramBoolean);
    paramContext.apply();
  }
  
  public static boolean d()
  {
    return droom.sleepIfUCan.a.f > 22;
  }
  
  public static int e(Context paramContext)
  {
    int j = 2131231292;
    b = y(paramContext);
    int i;
    if (b == 1) {
      i = 2131231282;
    }
    do
    {
      do
      {
        return i;
        i = j;
      } while (b == 3);
      if (b == 0) {
        return 2131231283;
      }
      if (b == 2) {
        return 2131231285;
      }
      if (b == 4) {
        return 2131231286;
      }
      if (b == 5) {
        return 2131231291;
      }
      if (b == 6) {
        return 2131231293;
      }
      if (b == 7) {
        return 2131231279;
      }
      if (b == 14) {
        return 2131231278;
      }
      if (b == 13) {
        return 2131231281;
      }
      if (b == 12) {
        return 2131231280;
      }
      if (b == 8) {
        return 2131231289;
      }
      if (b == 10) {
        return 2131231287;
      }
      if (b == 9) {
        return 2131231288;
      }
      if (b == 15) {
        return 2131231290;
      }
      i = j;
    } while (b != 11);
    return 2131231284;
  }
  
  public static int e(String paramString)
  {
    int j = 1;
    int i;
    if (paramString.equals("easy")) {
      i = 0;
    }
    do
    {
      do
      {
        return i;
        i = j;
      } while (paramString.equals("normal"));
      i = j;
    } while (!paramString.equals("hard"));
    return 2;
  }
  
  public static String e(int paramInt)
  {
    if (paramInt == 101) {
      return "error_setting";
    }
    if (paramInt == 99) {
      return "error";
    }
    if (paramInt == 0) {
      return "default";
    }
    if (paramInt == 77) {
      return "timer";
    }
    if (paramInt == 4) {
      return "barcode";
    }
    if (paramInt == 3) {
      return "math";
    }
    if (paramInt == 1) {
      return "photo";
    }
    if (paramInt == 2) {
      return "shake";
    }
    return "none";
  }
  
  public static void e(Context paramContext, int paramInt)
  {
    FirebaseAnalytics.getInstance(paramContext);
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("pref_last_open_date", 0);
    SharedPreferences.Editor localEditor = localSharedPreferences.edit();
    int i = ap(paramContext);
    if (paramInt == 0)
    {
      paramInt = localSharedPreferences.getInt("pref_num_dismiss_default", 0) + 1;
      localEditor.putInt("pref_num_dismiss_default", paramInt);
      if (i > 72) {
        localEditor.putInt("pref_num_dismiss_default_d3", localSharedPreferences.getInt("pref_num_dismiss_default_d3", 0) + 1);
      }
      b(paramContext, "num_dismiss_default", "" + paramInt);
    }
    for (;;)
    {
      localEditor.apply();
      return;
      if (paramInt == 1)
      {
        paramInt = localSharedPreferences.getInt("pref_num_dismiss_picture", 0) + 1;
        localEditor.putInt("pref_num_dismiss_picture", paramInt);
        if (i > 72) {
          localEditor.putInt("pref_num_dismiss_picture_d3", localSharedPreferences.getInt("pref_num_dismiss_picture_d3", 0) + 1);
        }
        b(paramContext, "num_dismiss_picture", "" + paramInt);
      }
      else if (paramInt == 2)
      {
        paramInt = localSharedPreferences.getInt("pref_num_dismiss_shake", 0) + 1;
        localEditor.putInt("pref_num_dismiss_shake", paramInt);
        if (i > 72) {
          localEditor.putInt("pref_num_dismiss_shake_d3", localSharedPreferences.getInt("pref_num_dismiss_shake_d3", 0) + 1);
        }
        b(paramContext, "num_dismiss_shake", "" + paramInt);
      }
      else if (paramInt == 3)
      {
        paramInt = localSharedPreferences.getInt("pref_num_dismiss_math", 0) + 1;
        localEditor.putInt("pref_num_dismiss_math", paramInt);
        if (i > 72) {
          localEditor.putInt("pref_num_dismiss_math_d3", localSharedPreferences.getInt("pref_num_dismiss_math_d3", 0) + 1);
        }
        b(paramContext, "num_dismiss_math", "" + paramInt);
      }
      else if (paramInt == 4)
      {
        paramInt = localSharedPreferences.getInt("pref_num_dismiss_barcode", 0) + 1;
        localEditor.putInt("pref_num_dismiss_barcode", paramInt);
        if (i > 72) {
          localEditor.putInt("pref_num_dismiss_barcode_d3", localSharedPreferences.getInt("pref_num_dismiss_barcode_d3", 0) + 1);
        }
        b(paramContext, "num_dismiss_barcode", "" + paramInt);
      }
      else if (paramInt == 77)
      {
        paramInt = localSharedPreferences.getInt("pref_num_dismiss_timer", 0) + 1;
        localEditor.putInt("pref_num_dismiss_timer", paramInt);
        if (i > 72) {
          localEditor.putInt("pref_num_dismiss_timer_d3", localSharedPreferences.getInt("pref_num_dismiss_timer_d3", 0) + 1);
        }
        b(paramContext, "num_dismiss_timer", "" + paramInt);
      }
    }
  }
  
  public static void e(Context paramContext, String paramString)
  {
    try
    {
      s.a("FB_EVENT", paramString);
      FirebaseAnalytics localFirebaseAnalytics = FirebaseAnalytics.getInstance(paramContext);
      Bundle localBundle = new Bundle();
      localBundle.putLong("event_time", System.currentTimeMillis());
      localFirebaseAnalytics.a(paramString, localBundle);
      l.a(paramContext).a("an_" + paramString, localBundle);
      return;
    }
    catch (Exception paramContext)
    {
      Crashlytics.logException(paramContext);
    }
  }
  
  public static void e(Context paramContext, boolean paramBoolean)
  {
    paramContext = paramContext.getSharedPreferences("AlarmClock", 0).edit();
    paramContext.putBoolean("pref_should_display_dnd_tutorial", paramBoolean);
    paramContext.apply();
  }
  
  public static boolean e()
  {
    return droom.sleepIfUCan.a.f > 23;
  }
  
  public static int f(Context paramContext)
  {
    int j = 2131231135;
    b = y(paramContext);
    int i;
    if (b == 1) {
      i = 2131231112;
    }
    do
    {
      do
      {
        return i;
        i = j;
      } while (b == 3);
      if (b == 0) {
        return 2131231126;
      }
      if (b == 2) {
        return 2131231128;
      }
      if (b == 4) {
        return 2131231129;
      }
      if (b == 5) {
        return 2131231134;
      }
      if (b == 6) {
        return 2131231136;
      }
      if ((b == 7) || (b == 14) || (b == 13) || (b == 12)) {
        return 2131231111;
      }
      if (b == 8) {
        return 2131231132;
      }
      if (b == 10) {
        return 2131231130;
      }
      if (b == 9) {
        return 2131231131;
      }
      if (b == 15) {
        return 2131231133;
      }
      i = j;
    } while (b != 11);
    return 2131231127;
  }
  
  public static Horoscope f(Context paramContext, boolean paramBoolean)
  {
    Object localObject1 = paramContext.getSharedPreferences("horoscope", 0);
    Object localObject2;
    if (paramBoolean)
    {
      localObject1 = ((SharedPreferences)localObject1).getString("jsonStringHelloBot", null);
      localObject2 = new d();
    }
    int i;
    for (;;)
    {
      try
      {
        com.google.gson.o localO = new com.google.gson.r().a((String)localObject1);
        if (!paramBoolean) {
          break label329;
        }
        localObject1 = HelloBotHoroscope.class;
        localObject1 = (Horoscope)((d)localObject2).a(localO, (Class)localObject1);
        localObject2 = Calendar.getInstance();
        ((Calendar)localObject2).setTimeInMillis(System.currentTimeMillis());
        i = a(paramContext, az(paramContext));
        s.a("CommonUtils", "horoscope info, date: " + ((Horoscope)localObject1).getDate());
        s.a("CommonUtils", "horoscope info, curTime: " + new SimpleDateFormat("yyyy.MM.dd").format(((Calendar)localObject2).getTime()));
        s.a("CommonUtils", "horoscope info, zodiac: " + ((Horoscope)localObject1).getZodiac());
        s.a("CommonUtils", "horoscope info, savedZodiac: " + i);
        if ((!((Horoscope)localObject1).getDate().equals(new SimpleDateFormat("yyyy.MM.dd").format(((Calendar)localObject2).getTime()))) || (((Horoscope)localObject1).getZodiac() != i)) {
          break label336;
        }
        s.a("CommonUtils", "horoscope is cached, date: " + ((Horoscope)localObject1).getDate() + ", zodiac:" + i);
        return localObject1;
      }
      catch (Exception paramContext)
      {
        s.a("CommonUtils", "getCachedHoroscope json parse error: " + (String)localObject1);
        return null;
      }
      localObject1 = ((SharedPreferences)localObject1).getString("jsonString", null);
      break;
      label329:
      localObject1 = Horoscope.class;
    }
    label336:
    if ((((Horoscope)localObject1).getZodiac() == i) && (!((Horoscope)localObject1).getDate().equals(new SimpleDateFormat("yyyy.MM.dd").format(((Calendar)localObject2).getTime())))) {
      return null;
    }
    s.a("CommonUtils", "horoscope smth wrong");
    return null;
  }
  
  public static String f(String paramString)
  {
    try
    {
      String str = paramString.split("/")[1];
      paramString = str;
      if (str.length() == 0) {
        paramString = "normal";
      }
      return paramString;
    }
    catch (Exception paramString) {}
    return "normal";
  }
  
  public static void f(Context paramContext, int paramInt)
  {
    FirebaseAnalytics.getInstance(paramContext);
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("pref_last_open_date", 0);
    SharedPreferences.Editor localEditor = localSharedPreferences.edit();
    if (paramInt == 0)
    {
      paramInt = localSharedPreferences.getInt("pref_num_preview_default", 0) + 1;
      localEditor.putInt("pref_num_preview_default", paramInt);
      b(paramContext, "num_preview_default", "" + paramInt);
    }
    for (;;)
    {
      localEditor.apply();
      return;
      if (paramInt == 1)
      {
        paramInt = localSharedPreferences.getInt("pref_num_preview_picture", 0) + 1;
        localEditor.putInt("pref_num_preview_picture", paramInt);
        b(paramContext, "num_preview_picture", "" + paramInt);
      }
      else if (paramInt == 2)
      {
        paramInt = localSharedPreferences.getInt("pref_num_preview_shake", 0) + 1;
        localEditor.putInt("pref_num_preview_shake", paramInt);
        b(paramContext, "num_preview_shake", "" + paramInt);
      }
      else if (paramInt == 3)
      {
        paramInt = localSharedPreferences.getInt("pref_num_preview_math", 0) + 1;
        localEditor.putInt("pref_num_preview_math", paramInt);
        b(paramContext, "num_preview_math", "" + paramInt);
      }
      else if (paramInt == 4)
      {
        paramInt = localSharedPreferences.getInt("pref_num_preview_barcode", 0) + 1;
        localEditor.putInt("pref_num_preview_barcode", paramInt);
        b(paramContext, "num_preview_barcode", "" + paramInt);
      }
    }
  }
  
  public static void f(Context paramContext, String paramString)
  {
    if (AlarmReceiver.a)
    {
      LogWriter.a(paramContext);
      LogWriter.a(paramContext, LogWriter.EventType.a, "CommonUtils", "app_ended_bailed_alarm_ringing");
      return;
    }
    if ((w()) && (x()))
    {
      LogWriter.a(paramContext);
      LogWriter.a(paramContext, LogWriter.EventType.a, "CommonUtils", "app_ended_bailed_lenovo_vibe");
      return;
    }
    try
    {
      if (g(paramContext, "before_timer"))
      {
        if (c != null)
        {
          LogWriter.a(paramContext);
          LogWriter.a(paramContext, LogWriter.EventType.a, "CommonUtils", "app_end_req_overriden", new LogWriter.a("from", paramString));
          c.cancel();
        }
        c = new g(4000L, 1000L, paramContext, paramString).start();
        return;
      }
    }
    catch (Exception paramString)
    {
      localA = new LogWriter.a();
      localA.a("msg", "killProcessSafely2");
      localA.a("stacktrace", Log.getStackTraceString(paramString));
      LogWriter.a(paramContext);
      LogWriter.a(paramContext, LogWriter.EventType.a, "CommonUtils", "app_ended_error", localA);
      s.a("CommonUtils", paramString.toString());
      Crashlytics.logException(paramString);
      c = null;
      Process.killProcess(Process.myPid());
      return;
    }
    LogWriter.a localA = new LogWriter.a();
    localA.a("from", paramString);
    localA.a("how", "bg_kill_outside_timer");
    LogWriter.a(paramContext);
    LogWriter.a(paramContext, LogWriter.EventType.a, "CommonUtils", "component_end", localA);
    c = null;
    ((ActivityManager)paramContext.getSystemService("activity")).killBackgroundProcesses(paramContext.getPackageName());
  }
  
  public static boolean f()
  {
    return "samsung".equalsIgnoreCase(Build.MANUFACTURER);
  }
  
  public static int g(Context paramContext)
  {
    return 2131231109;
  }
  
  public static String g(String paramString)
  {
    try
    {
      String str = paramString.split("/")[0];
      paramString = str;
      if (str.length() == 0) {
        paramString = Integer.toString(40);
      }
      return paramString;
    }
    catch (Exception paramString) {}
    return Integer.toString(40);
  }
  
  public static void g(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getSharedPreferences("AlarmClock", 0).edit();
    s.a("mediaVol", "volume to save: " + paramInt);
    paramContext.putInt("curVol", paramInt);
    paramContext.apply();
  }
  
  public static void g(Context paramContext, boolean paramBoolean)
  {
    paramContext = paramContext.getSharedPreferences("exception", 0).edit();
    paramContext.putBoolean("exception_occurred", paramBoolean);
    paramContext.apply();
  }
  
  public static boolean g()
  {
    return "sony".equalsIgnoreCase(Build.MANUFACTURER);
  }
  
  public static boolean g(Context paramContext, String paramString)
  {
    try
    {
      List localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
      int i = 0;
      while (i < localList.size())
      {
        if (((ActivityManager.RunningAppProcessInfo)localList.get(i)).processName.equals("droom.sleepIfUCan"))
        {
          s.a("CommonUtils", "importance: " + ((ActivityManager.RunningAppProcessInfo)localList.get(i)).importance);
          if ((((ActivityManager.RunningAppProcessInfo)localList.get(i)).importance != 400) && (((ActivityManager.RunningAppProcessInfo)localList.get(i)).importance != 100))
          {
            localA2 = new LogWriter.a();
            localA2.a("from", paramString);
            localA2.a("proc_importance", "" + ((ActivityManager.RunningAppProcessInfo)localList.get(i)).importance);
            LogWriter.a(paramContext);
            LogWriter.a(paramContext, LogWriter.EventType.a, "CommonUtils", "check_importance", localA2);
            return true;
          }
          LogWriter.a localA2 = new LogWriter.a();
          localA2.a("from", paramString);
          localA2.a("proc_importance", "" + ((ActivityManager.RunningAppProcessInfo)localList.get(i)).importance);
          LogWriter.a(paramContext);
          LogWriter.a(paramContext, LogWriter.EventType.a, "CommonUtils", "check_importance", localA2);
          return false;
        }
        i += 1;
      }
      LogWriter.a localA1;
      return false;
    }
    catch (Exception localException)
    {
      Crashlytics.logException(localException);
      s.a("CommonUtils", localException.toString());
      localA1 = new LogWriter.a();
      localA1.a("proc_importance", "error_failed");
      localA1.a("from", paramString);
      LogWriter.a(paramContext);
      LogWriter.a(paramContext, LogWriter.EventType.a, "CommonUtils", "check_importance", localA1);
    }
  }
  
  public static double h(String paramString)
  {
    double d1 = 1.5D;
    if (paramString.equals("hard")) {
      d1 = 2.1D;
    }
    do
    {
      return d1;
      if (paramString.equals("normal")) {
        return 1.3D;
      }
    } while (!paramString.equals("easy"));
    return 1.0D;
  }
  
  public static int h(Context paramContext)
  {
    b = y(paramContext);
    if (b == 1) {}
    do
    {
      do
      {
        return 2131231307;
      } while ((b == 3) || (b == 0) || (b == 2) || (b == 4) || (b == 5) || (b == 6));
      if (b == 7) {
        return 2131231308;
      }
      if (b == 12) {
        return 2131231308;
      }
      if (b == 13) {
        return 2131231308;
      }
      if (b == 14) {
        return 2131231308;
      }
    } while ((b == 8) || (b == 10) || (b == 9) || (b == 15) || (b != 11));
    return 2131231307;
  }
  
  public static void h(Context paramContext, int paramInt)
  {
    droom.sleepIfUCan.internal.r.a().b(paramInt);
    paramContext = paramContext.getSharedPreferences("AlarmClock", 0).edit();
    paramContext.putInt("streamType", paramInt);
    paramContext.apply();
  }
  
  public static void h(Context paramContext, String paramString)
  {
    if (AlarmReceiver.a)
    {
      LogWriter.a(paramContext);
      LogWriter.a(paramContext, LogWriter.EventType.a, "CommonUtils", "app_ended_bailed_alarm_ringing");
      return;
    }
    LogWriter.a localA = new LogWriter.a("pid", "" + Process.myPid());
    localA.a("from", paramString);
    LogWriter.a(paramContext);
    LogWriter.a(paramContext, LogWriter.EventType.a, "CommonUtils", "app_ended", localA);
    try
    {
      u.a().b(paramContext, paramString);
      return;
    }
    catch (Exception paramString)
    {
      LogWriter.a(paramContext);
      LogWriter.a(paramContext, LogWriter.EventType.a, "CommonUtils", "app_ended_error", new LogWriter.a("msg", "triggerKillProcess"));
      Crashlytics.logException(paramString);
      s.a("CommonUtils", paramString.toString());
      Process.killProcess(Process.myPid());
    }
  }
  
  public static void h(Context paramContext, boolean paramBoolean)
  {
    paramContext = paramContext.getSharedPreferences("AlarmClock", 0).edit();
    paramContext.putBoolean("beta_upgrade_notice", paramBoolean);
    paramContext.apply();
  }
  
  public static boolean h()
  {
    return "asus".equalsIgnoreCase(Build.MANUFACTURER);
  }
  
  public static int i(Context paramContext)
  {
    b = y(paramContext);
    if (b == 1) {}
    do
    {
      do
      {
        return 2131231309;
      } while ((b == 3) || (b == 0) || (b == 2) || (b == 4) || (b == 5) || (b == 6));
      if (b == 7) {
        return 2131231310;
      }
      if (b == 12) {
        return 2131231310;
      }
      if (b == 13) {
        return 2131231310;
      }
      if (b == 14) {
        return 2131231310;
      }
    } while ((b == 8) || (b == 10) || (b == 9) || (b == 15) || (b != 11));
    return 2131231309;
  }
  
  public static String i(String paramString)
  {
    try
    {
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeFile(paramString, localOptions);
      if (localOptions.outMimeType != null) {
        return localOptions.outMimeType;
      }
      return "";
    }
    catch (Exception paramString) {}
    return "";
  }
  
  public static void i(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getSharedPreferences("AlarmClock", 0).edit();
    paramContext.putInt("backupCode", paramInt);
    paramContext.apply();
  }
  
  public static void i(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("Promotion", 0).edit();
    try
    {
      paramString = new Locale("", paramString).getISO3Country();
      if ((paramString == null) || (paramString.isEmpty())) {
        return;
      }
      paramContext.putString("countryCode", paramString);
      paramContext.apply();
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static void i(Context paramContext, boolean paramBoolean)
  {
    paramContext = paramContext.getSharedPreferences("AlarmClock", 0).edit();
    paramContext.putBoolean("timePicker_analog", paramBoolean);
    paramContext.apply();
  }
  
  public static boolean i()
  {
    return "huawei".equalsIgnoreCase(Build.MANUFACTURER);
  }
  
  public static int j(Context paramContext)
  {
    return 2131231110;
  }
  
  public static String j(Context paramContext, int paramInt)
  {
    if (aV(paramContext)) {
      try
      {
        String str1 = "https://api.alar.my/horoscope/hellobot?zodiac=" + paramInt + "&language=" + W(paramContext) + "&timezone=" + URLEncoder.encode(n(), "UTF-8");
        return str1;
      }
      catch (Exception localException1)
      {
        Crashlytics.logException(localException1);
        return "https://api.alar.my/horoscope/hellobot?zodiac=" + paramInt + "&language=" + W(paramContext) + "&timezone=00:00";
      }
    }
    try
    {
      String str2 = "https://api.alar.my/horoscope?zodiac=" + paramInt + "&language=" + W(paramContext) + "&timezone=" + URLEncoder.encode(n(), "UTF-8");
      return str2;
    }
    catch (Exception localException2)
    {
      Crashlytics.logException(localException2);
    }
    return "https://api.alar.my/horoscope?zodiac=" + paramInt + "&language=" + W(paramContext) + "&timezone=00:00";
  }
  
  public static void j(Context paramContext, String paramString)
  {
    if (paramString == null) {
      return;
    }
    s.a("CommonUtils", "purchase orderId:" + paramString);
    paramContext = paramContext.getSharedPreferences("AlarmClock", 0).edit();
    paramContext.putString("purchased_info", paramString);
    paramContext.apply();
  }
  
  public static void j(Context paramContext, boolean paramBoolean)
  {
    paramContext = paramContext.getSharedPreferences("AlarmClock", 0).edit();
    paramContext.putBoolean("remote_config_error", paramBoolean);
    paramContext.apply();
  }
  
  public static boolean j()
  {
    return "meizu".equalsIgnoreCase(Build.MANUFACTURER);
  }
  
  public static int[] j(String paramString)
  {
    int[] arrayOfInt = new int[2];
    int[] tmp5_4 = arrayOfInt;
    tmp5_4[0] = 0;
    int[] tmp9_5 = tmp5_4;
    tmp9_5[1] = 0;
    tmp9_5;
    try
    {
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeFile(paramString, localOptions);
      arrayOfInt[0] = localOptions.outWidth;
      arrayOfInt[1] = localOptions.outHeight;
      return arrayOfInt;
    }
    catch (Exception paramString) {}
    return arrayOfInt;
  }
  
  public static int k(Context paramContext)
  {
    int j = 2131231107;
    b = y(paramContext);
    int i;
    if (b == 1) {
      i = 2131231097;
    }
    do
    {
      do
      {
        return i;
        i = j;
      } while (b == 3);
      if (b == 0) {
        return 2131231098;
      }
      if (b == 2) {
        return 2131231100;
      }
      if (b == 4) {
        return 2131231101;
      }
      if (b == 5) {
        return 2131231106;
      }
      if (b == 6) {
        return 2131231108;
      }
      if (b == 7) {
        return 2131231094;
      }
      if (b == 12) {
        return 2131231095;
      }
      if (b == 13) {
        return 2131231096;
      }
      if (b == 14) {
        return 2131231093;
      }
      if (b == 8) {
        return 2131231104;
      }
      if (b == 10) {
        return 2131231102;
      }
      if (b == 9) {
        return 2131231103;
      }
      if (b == 15) {
        return 2131231105;
      }
      i = j;
    } while (b != 11);
    return 2131231099;
  }
  
  public static int k(Context paramContext, int paramInt)
  {
    if (aV(paramContext))
    {
      switch (paramInt)
      {
      default: 
        return 2131230937;
      case 0: 
        return 2131230919;
      case 1: 
        return 2131230920;
      case 2: 
        return 2131230923;
      case 3: 
        return 2131230924;
      case 4: 
        return 2131230925;
      case 5: 
        return 2131230926;
      case 6: 
        return 2131230927;
      case 7: 
        return 2131230928;
      case 8: 
        return 2131230929;
      case 9: 
        return 2131230930;
      case 10: 
        return 2131230921;
      }
      return 2131230922;
    }
    switch (paramInt)
    {
    default: 
      return 0;
    case 0: 
      return 2131231352;
    case 1: 
      return 2131231353;
    case 2: 
      return 2131231356;
    case 3: 
      return 2131231357;
    case 4: 
      return 2131231358;
    case 5: 
      return 2131231359;
    case 6: 
      return 2131231360;
    case 7: 
      return 2131231361;
    case 8: 
      return 2131231362;
    case 9: 
      return 2131231363;
    case 10: 
      return 2131231354;
    }
    return 2131231355;
  }
  
  public static void k(Context paramContext, String paramString)
  {
    paramContext = android.preference.PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putString("language_setting", paramString);
    paramContext.apply();
  }
  
  public static boolean k()
  {
    return "xiaomi".equalsIgnoreCase(Build.MANUFACTURER);
  }
  
  public static boolean k(String paramString)
  {
    Object localObject = "DISPLAY";
    try
    {
      String str = com.google.firebase.remoteconfig.a.a().a(paramString);
      localObject = str;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Crashlytics.logException(localException);
      }
    }
    s.a("CommonUtils", "shouldNativeAd, ad placement:" + paramString + ",ad type:" + (String)localObject);
    return ((String)localObject).equals("NATIVE");
  }
  
  public static int l(Context paramContext)
  {
    int j = 2131231178;
    b = y(paramContext);
    int i;
    if (b == 1) {
      i = 2131231168;
    }
    do
    {
      do
      {
        return i;
        i = j;
      } while (b == 3);
      if (b == 0) {
        return 2131231169;
      }
      if (b == 2) {
        return 2131231171;
      }
      if (b == 4) {
        return 2131231172;
      }
      if (b == 5) {
        return 2131231177;
      }
      if (b == 6) {
        return 2131231179;
      }
      if (b == 7) {
        return 2131231165;
      }
      if (b == 12) {
        return 2131231166;
      }
      if (b == 14) {
        return 2131231164;
      }
      if (b == 13) {
        return 2131231167;
      }
      if (b == 8) {
        return 2131231175;
      }
      if (b == 10) {
        return 2131231173;
      }
      if (b == 9) {
        return 2131231174;
      }
      if (b == 15) {
        return 2131231176;
      }
      i = j;
    } while (b != 11);
    return 2131231170;
  }
  
  public static String l()
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return Resources.getSystem().getConfiguration().getLocales().get(0).toString();
    }
    return Resources.getSystem().getConfiguration().locale.toString();
  }
  
  public static String l(Context paramContext, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 0: 
      return paramContext.getResources().getString(2131755524);
    case 1: 
      return paramContext.getResources().getString(2131755521);
    case 2: 
      return paramContext.getResources().getString(2131755528);
    case 3: 
      return paramContext.getResources().getString(2131755522);
    case 4: 
      return paramContext.getResources().getString(2131755531);
    case 5: 
      return paramContext.getResources().getString(2131755525);
    case 6: 
      return paramContext.getResources().getString(2131755523);
    case 7: 
      return paramContext.getResources().getString(2131755526);
    case 8: 
      return paramContext.getResources().getString(2131755532);
    case 9: 
      return paramContext.getResources().getString(2131755527);
    case 10: 
      return paramContext.getResources().getString(2131755530);
    }
    return paramContext.getResources().getString(2131755529);
  }
  
  public static String l(String paramString)
  {
    Mac localMac = Mac.getInstance("HmacSHA256");
    localMac.init(new SecretKeySpec(Base64.decode("cfKHT71+PUsHZQPeiBsDsMvv+JHWmdtp7VEQFKJIyuGOqMOitvZpCTObaa/YvissxbZP36Zv5mgvZtLND04Baw==", 0), "HmacSHA256"));
    paramString = Base64.encodeToString(localMac.doFinal(paramString.getBytes("UTF-8")), 0);
    paramString = "SharedKeyLite alarmyblobs:" + paramString;
    s.a("Azure", "auth str: " + paramString);
    return paramString;
  }
  
  public static void l(Context paramContext, String paramString)
  {
    paramContext = android.support.v7.preference.PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putString("lang_value", paramString);
    paramContext.apply();
  }
  
  public static int m(Context paramContext)
  {
    b = y(paramContext);
    if ((b == 7) || (b == 14) || (b == 13) || (b == 12)) {
      return 2131099709;
    }
    return 2131099871;
  }
  
  public static void m(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getSharedPreferences("pref_user_info", 0).edit();
    paramContext.putInt("pref_gender", paramInt);
    paramContext.apply();
  }
  
  /* Error */
  public static void m(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: new 1201	android/os/Bundle
    //   3: dup
    //   4: invokespecial 1202	android/os/Bundle:<init>	()V
    //   7: astore_3
    //   8: aload_3
    //   9: ldc_w 2851
    //   12: aload_1
    //   13: invokevirtual 1292	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   16: aload_0
    //   17: ldc_w 2853
    //   20: aload_3
    //   21: invokestatic 2855	droom/sleepIfUCan/utils/e:a	(Landroid/content/Context;Ljava/lang/String;Landroid/os/Bundle;)V
    //   24: aload_0
    //   25: invokestatic 2857	droom/sleepIfUCan/utils/e:aY	(Landroid/content/Context;)V
    //   28: aload_0
    //   29: invokestatic 2859	droom/sleepIfUCan/utils/e:aX	(Landroid/content/Context;)V
    //   32: aload_0
    //   33: invokestatic 2861	droom/sleepIfUCan/utils/e:ba	(Landroid/content/Context;)V
    //   36: aload_0
    //   37: invokestatic 2863	droom/sleepIfUCan/utils/e:aZ	(Landroid/content/Context;)V
    //   40: aload_0
    //   41: iconst_1
    //   42: invokestatic 2865	droom/sleepIfUCan/utils/e:g	(Landroid/content/Context;Z)V
    //   45: aload_0
    //   46: invokestatic 793	droom/sleepIfUCan/utils/LogWriter:a	(Landroid/content/Context;)Ldroom/sleepIfUCan/utils/LogWriter;
    //   49: pop
    //   50: invokestatic 2868	droom/sleepIfUCan/utils/LogWriter:c	()Ljava/io/File;
    //   53: astore 4
    //   55: ldc 35
    //   57: new 37	java/lang/StringBuilder
    //   60: dup
    //   61: invokespecial 40	java/lang/StringBuilder:<init>	()V
    //   64: ldc_w 2870
    //   67: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: aload 4
    //   72: invokevirtual 2871	java/io/File:getName	()Ljava/lang/String;
    //   75: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: ldc_w 2873
    //   81: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: aload 4
    //   86: invokevirtual 2875	java/io/File:length	()J
    //   89: invokevirtual 677	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   92: invokevirtual 49	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   95: invokestatic 54	droom/sleepIfUCan/utils/s:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   98: aload 4
    //   100: invokevirtual 2875	java/io/File:length	()J
    //   103: l2i
    //   104: istore_2
    //   105: ldc 35
    //   107: new 37	java/lang/StringBuilder
    //   110: dup
    //   111: invokespecial 40	java/lang/StringBuilder:<init>	()V
    //   114: ldc_w 2877
    //   117: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: iload_2
    //   121: invokevirtual 246	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   124: invokevirtual 49	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   127: invokestatic 54	droom/sleepIfUCan/utils/s:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   130: iload_2
    //   131: newarray byte
    //   133: astore_1
    //   134: new 2879	java/io/BufferedInputStream
    //   137: dup
    //   138: new 2402	java/io/FileInputStream
    //   141: dup
    //   142: aload 4
    //   144: invokespecial 2405	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   147: invokespecial 2882	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   150: astore 4
    //   152: aload 4
    //   154: aload_1
    //   155: iconst_0
    //   156: aload_1
    //   157: arraylength
    //   158: invokevirtual 2885	java/io/BufferedInputStream:read	([BII)I
    //   161: pop
    //   162: aload 4
    //   164: invokevirtual 2886	java/io/BufferedInputStream:close	()V
    //   167: aload_0
    //   168: invokestatic 552	droom/sleepIfUCan/utils/e:N	(Landroid/content/Context;)Ljava/lang/String;
    //   171: astore 4
    //   173: invokestatic 2888	droom/sleepIfUCan/utils/e:L	()Ljava/lang/String;
    //   176: astore 5
    //   178: invokestatic 512	java/lang/System:currentTimeMillis	()J
    //   181: invokestatic 1125	java/lang/Long:toString	(J)Ljava/lang/String;
    //   184: astore 6
    //   186: ldc 35
    //   188: new 37	java/lang/StringBuilder
    //   191: dup
    //   192: invokespecial 40	java/lang/StringBuilder:<init>	()V
    //   195: ldc_w 2890
    //   198: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   201: aload 5
    //   203: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   206: invokevirtual 49	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   209: invokestatic 54	droom/sleepIfUCan/utils/s:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   212: ldc 35
    //   214: new 37	java/lang/StringBuilder
    //   217: dup
    //   218: invokespecial 40	java/lang/StringBuilder:<init>	()V
    //   221: ldc_w 2892
    //   224: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   227: aload 4
    //   229: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   232: invokevirtual 49	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   235: invokestatic 54	droom/sleepIfUCan/utils/s:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   238: new 37	java/lang/StringBuilder
    //   241: dup
    //   242: invokespecial 40	java/lang/StringBuilder:<init>	()V
    //   245: ldc_w 2894
    //   248: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   251: aload 4
    //   253: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   256: ldc_w 2896
    //   259: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   262: aload 5
    //   264: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   267: ldc_w 2898
    //   270: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   273: ldc_w 2900
    //   276: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   279: ldc_w 2902
    //   282: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   285: ldc_w 2904
    //   288: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   291: ldc_w 2906
    //   294: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   297: aload_0
    //   298: invokestatic 552	droom/sleepIfUCan/utils/e:N	(Landroid/content/Context;)Ljava/lang/String;
    //   301: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   304: ldc_w 1302
    //   307: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   310: aload 6
    //   312: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   315: ldc_w 2908
    //   318: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   321: invokevirtual 49	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   324: astore 7
    //   326: aload 7
    //   328: invokestatic 2910	droom/sleepIfUCan/utils/e:l	(Ljava/lang/String;)Ljava/lang/String;
    //   331: astore 8
    //   333: aload_0
    //   334: invokestatic 1059	droom/sleepIfUCan/utils/ad:a	(Landroid/content/Context;)Ldroom/sleepIfUCan/utils/ad;
    //   337: invokevirtual 1062	droom/sleepIfUCan/utils/ad:a	()Lcom/android/volley/RequestQueue;
    //   340: astore 7
    //   342: new 2912	droom/sleepIfUCan/utils/k
    //   345: dup
    //   346: iconst_2
    //   347: new 37	java/lang/StringBuilder
    //   350: dup
    //   351: invokespecial 40	java/lang/StringBuilder:<init>	()V
    //   354: ldc_w 2914
    //   357: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   360: aload_0
    //   361: invokestatic 552	droom/sleepIfUCan/utils/e:N	(Landroid/content/Context;)Ljava/lang/String;
    //   364: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   367: ldc_w 1302
    //   370: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   373: aload 6
    //   375: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   378: ldc_w 2908
    //   381: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   384: invokevirtual 49	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   387: new 2916	droom/sleepIfUCan/utils/i
    //   390: dup
    //   391: aload_0
    //   392: aload_3
    //   393: invokespecial 2919	droom/sleepIfUCan/utils/i:<init>	(Landroid/content/Context;Landroid/os/Bundle;)V
    //   396: new 2921	droom/sleepIfUCan/utils/j
    //   399: dup
    //   400: aload_0
    //   401: aload_3
    //   402: invokespecial 2922	droom/sleepIfUCan/utils/j:<init>	(Landroid/content/Context;Landroid/os/Bundle;)V
    //   405: aload 8
    //   407: iload_2
    //   408: aload 4
    //   410: aload 5
    //   412: aload_1
    //   413: invokespecial 2925	droom/sleepIfUCan/utils/k:<init>	(ILjava/lang/String;Lcom/android/volley/Response$Listener;Lcom/android/volley/Response$ErrorListener;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;[B)V
    //   416: astore_0
    //   417: aload_0
    //   418: new 1069	com/android/volley/DefaultRetryPolicy
    //   421: dup
    //   422: ldc_w 2926
    //   425: iconst_2
    //   426: fconst_1
    //   427: invokespecial 1072	com/android/volley/DefaultRetryPolicy:<init>	(IIF)V
    //   430: invokevirtual 2929	droom/sleepIfUCan/utils/ac:setRetryPolicy	(Lcom/android/volley/RetryPolicy;)Lcom/android/volley/Request;
    //   433: pop
    //   434: aload 7
    //   436: aload_0
    //   437: invokevirtual 1087	com/android/volley/RequestQueue:add	(Lcom/android/volley/Request;)Lcom/android/volley/Request;
    //   440: pop
    //   441: return
    //   442: astore 4
    //   444: ldc 35
    //   446: aload 4
    //   448: invokevirtual 2930	java/io/FileNotFoundException:toString	()Ljava/lang/String;
    //   451: invokestatic 54	droom/sleepIfUCan/utils/s:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   454: goto -287 -> 167
    //   457: astore 4
    //   459: ldc 35
    //   461: aload 4
    //   463: invokevirtual 2931	java/io/IOException:toString	()Ljava/lang/String;
    //   466: invokestatic 54	droom/sleepIfUCan/utils/s:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   469: goto -302 -> 167
    //   472: astore_0
    //   473: aload_0
    //   474: invokevirtual 2932	java/security/InvalidKeyException:printStackTrace	()V
    //   477: return
    //   478: astore_0
    //   479: aload_0
    //   480: invokevirtual 2933	java/security/NoSuchAlgorithmException:printStackTrace	()V
    //   483: return
    //   484: astore_0
    //   485: aload_0
    //   486: invokevirtual 2934	java/io/UnsupportedEncodingException:printStackTrace	()V
    //   489: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	490	0	paramContext	Context
    //   0	490	1	paramString	String
    //   104	304	2	i	int
    //   7	395	3	localBundle	Bundle
    //   53	356	4	localObject1	Object
    //   442	5	4	localFileNotFoundException	java.io.FileNotFoundException
    //   457	5	4	localIOException	java.io.IOException
    //   176	235	5	str1	String
    //   184	190	6	str2	String
    //   324	111	7	localObject2	Object
    //   331	75	8	str3	String
    // Exception table:
    //   from	to	target	type
    //   134	167	442	java/io/FileNotFoundException
    //   134	167	457	java/io/IOException
    //   326	441	472	java/security/InvalidKeyException
    //   326	441	478	java/security/NoSuchAlgorithmException
    //   326	441	484	java/io/UnsupportedEncodingException
  }
  
  public static boolean m()
  {
    return c != null;
  }
  
  public static int n(Context paramContext)
  {
    b = y(paramContext);
    if ((b == 7) || (b == 14) || (b == 13) || (b == 12)) {
      return 2131230845;
    }
    return 2131230846;
  }
  
  public static int n(Context paramContext, int paramInt)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().density * paramInt + 0.5F);
  }
  
  public static String n()
  {
    Object localObject = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault());
    localObject = new SimpleDateFormat("Z", Locale.ENGLISH).format(((Calendar)localObject).getTime());
    return ((String)localObject).substring(0, 3) + ":" + ((String)localObject).substring(3, 5);
  }
  
  public static String n(Context paramContext, String paramString)
  {
    Uri localUri = MediaStore.Audio.Media.getContentUriForPath(paramString);
    paramContext = paramContext.getContentResolver().query(localUri, null, "_data='" + paramString + "'", null, null);
    paramContext.moveToFirst();
    long l = paramContext.getLong(paramContext.getColumnIndex("_id"));
    paramContext.close();
    if (!localUri.toString().endsWith(String.valueOf(l))) {
      return localUri + "/" + l;
    }
    return localUri.toString();
  }
  
  public static int o(Context paramContext)
  {
    b = y(paramContext);
    if ((b == 7) || (b == 14) || (b == 13) || (b == 12)) {
      return 2131099794;
    }
    return 2131099795;
  }
  
  public static boolean o()
  {
    return "zte".equalsIgnoreCase(Build.MANUFACTURER);
  }
  
  public static int p(Context paramContext)
  {
    b = y(paramContext);
    if ((b == 7) || (b == 14) || (b == 13) || (b == 12)) {
      return 2131099708;
    }
    return 2131099985;
  }
  
  public static boolean p()
  {
    return "coolPad".equalsIgnoreCase(Build.MANUFACTURER);
  }
  
  public static int q(Context paramContext)
  {
    b = y(paramContext);
    if ((b == 7) || (b == 14) || (b == 13) || (b == 12)) {
      return 2131099809;
    }
    return 2131099815;
  }
  
  public static boolean q()
  {
    return "oneplus".equalsIgnoreCase(Build.MANUFACTURER);
  }
  
  public static int r(Context paramContext)
  {
    b = y(paramContext);
    if ((b == 7) || (b == 14) || (b == 13) || (b == 12)) {
      return 2131099808;
    }
    return 2131099984;
  }
  
  public static boolean r()
  {
    return "vivo".equalsIgnoreCase(Build.MANUFACTURER);
  }
  
  public static int s(Context paramContext)
  {
    b = y(paramContext);
    if ((b == 7) || (b == 14) || (b == 13) || (b == 12)) {
      return 2131099985;
    }
    return 2131099686;
  }
  
  public static boolean s()
  {
    return "zuk".equalsIgnoreCase(Build.MANUFACTURER);
  }
  
  public static int t(Context paramContext)
  {
    b = y(paramContext);
    if ((b == 7) || (b == 14) || (b == 13) || (b == 12)) {
      return 2131099812;
    }
    return 2131099981;
  }
  
  public static boolean t()
  {
    return "gionee".equalsIgnoreCase(Build.MANUFACTURER);
  }
  
  public static int u(Context paramContext)
  {
    b = y(paramContext);
    if ((b == 7) || (b == 14) || (b == 13) || (b == 12)) {
      return 2131099810;
    }
    return 2131099815;
  }
  
  public static boolean u()
  {
    return "lemobile".equalsIgnoreCase(Build.MANUFACTURER);
  }
  
  public static int v(Context paramContext)
  {
    int j = 2131099957;
    b = y(paramContext);
    int i;
    if (b == 1) {
      i = 2131099716;
    }
    do
    {
      do
      {
        return i;
        i = j;
      } while (b == 3);
      if (b == 0) {
        return 2131099817;
      }
      if (b == 2) {
        return 2131099841;
      }
      if (b == 4) {
        return 2131099903;
      }
      if (b == 5) {
        return 2131099949;
      }
      if (b == 6) {
        return 2131099993;
      }
      if (b == 7) {
        return 2131099693;
      }
      if (b == 14) {
        return 2131099688;
      }
      if (b == 12) {
        return 2131099698;
      }
      if (b == 13) {
        return 2131099703;
      }
      if (b == 8) {
        return 2131099924;
      }
      if (b == 10) {
        return 2131099910;
      }
      if (b == 9) {
        return 2131099917;
      }
      if (b == 15) {
        return 2131099931;
      }
      i = j;
    } while (b != 11);
    return 2131099828;
  }
  
  public static boolean v()
  {
    return "oppo".equalsIgnoreCase(Build.MANUFACTURER);
  }
  
  public static int w(Context paramContext)
  {
    int j = 2131820566;
    b = y(paramContext);
    int i;
    if (b == 1) {
      i = 2131820556;
    }
    do
    {
      do
      {
        return i;
        i = j;
      } while (b == 3);
      if (b == 0) {
        return 2131820557;
      }
      if (b == 2) {
        return 2131820559;
      }
      if (b == 4) {
        return 2131820560;
      }
      if (b == 5) {
        return 2131820565;
      }
      if (b == 6) {
        return 2131820567;
      }
      if (b == 7) {
        return 2131820553;
      }
      if (b == 12) {
        return 2131820554;
      }
      if (b == 13) {
        return 2131820555;
      }
      if (b == 14) {
        return 2131820552;
      }
      if (b == 8) {
        return 2131820563;
      }
      if (b == 10) {
        return 2131820561;
      }
      if (b == 9) {
        return 2131820562;
      }
      if (b == 15) {
        return 2131820564;
      }
      i = j;
    } while (b != 11);
    return 2131820558;
  }
  
  public static boolean w()
  {
    return "lenovo".equalsIgnoreCase(Build.MANUFACTURER);
  }
  
  public static int x(Context paramContext)
  {
    int j = 2131820767;
    b = y(paramContext);
    int i;
    if (b == 1) {
      i = 2131820757;
    }
    do
    {
      do
      {
        return i;
        i = j;
      } while (b == 3);
      if (b == 0) {
        return 2131820758;
      }
      if (b == 2) {
        return 2131820760;
      }
      if (b == 4) {
        return 2131820761;
      }
      if (b == 5) {
        return 2131820766;
      }
      if (b == 6) {
        return 2131820768;
      }
      if (b == 11) {
        return 2131820759;
      }
      if (b == 7) {
        return 2131820754;
      }
      if (b == 12) {
        return 2131820755;
      }
      if (b == 14) {
        return 2131820753;
      }
      if (b == 13) {
        return 2131820756;
      }
      if (b == 8) {
        return 2131820764;
      }
      if (b == 10) {
        return 2131820762;
      }
      if (b == 9) {
        return 2131820763;
      }
      i = j;
    } while (b != 15);
    return 2131820765;
  }
  
  public static boolean x()
  {
    return (Build.MODEL.contains("A60")) || (Build.MODEL.contains("A70"));
  }
  
  public static int y(Context paramContext)
  {
    if (b == -1) {}
    try
    {
      b = paramContext.getSharedPreferences("theme", 0).getInt("color", 3);
      return b;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        b = 3;
      }
    }
  }
  
  public static boolean y()
  {
    return droom.sleepIfUCan.a.b;
  }
  
  public static String z()
  {
    try
    {
      String str = com.google.firebase.remoteconfig.a.a().a("an_today_ad_config");
      return str;
    }
    catch (Exception localException)
    {
      Crashlytics.logException(localException);
    }
    return "B,05d3e04f7ae74ebda61a5133ece23bbd";
  }
  
  public static boolean z(Context paramContext)
  {
    return paramContext.getSharedPreferences("notification", 0).getBoolean("icon", true);
  }
}
