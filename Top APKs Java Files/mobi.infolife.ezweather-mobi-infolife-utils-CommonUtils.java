package mobi.infolife.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.google.analytics.tracking.android.EasyTracker;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import mobi.infolife.ezweather.Preferences;
import mobi.infolife.ezweather.WidgetSettingActivity;
import mobi.infolife.ezweather.datasource.common.CommonPreferences;
import mobi.infolife.ezweather.datasource.common.NetworkManager;
import mobi.infolife.ezweather.datasource.common.Utils;
import mobi.infolife.idmanager.DatabaseAdapter;
import mobi.infolife.store.PluginInfo;
import mobi.infolife.widget.WidgetConfig;
import mobi.infolife.widget.WidgetPreviewInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class CommonUtils
{
  public static String emailaddr = null;
  public static boolean isAutoLogin = false;
  
  public CommonUtils() {}
  
  public static List<Integer> addArrayToList(List<Integer> paramList, int[] paramArrayOfInt)
  {
    int i;
    if ((paramArrayOfInt != null) && (paramList != null)) {
      i = 0;
    }
    for (;;)
    {
      if (i > paramArrayOfInt.length - 1) {
        return paramList;
      }
      paramList.add(Integer.valueOf(paramArrayOfInt[i]));
      i += 1;
    }
  }
  
  public static void changeLocaleSetting(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setClassName("com.android.settings", "com.android.settings.DateTimeSettings");
    paramContext.startActivity(localIntent);
  }
  
  public static void checkStoreVersion(Context paramContext)
  {
    Calendar localCalendar = Calendar.getInstance();
    int i = CommonPreferences.getGPSLocateRandomInteger(paramContext);
    if ((localCalendar.get(11) != 12) || (localCalendar.get(12) != i))
    {
      Utils.logAndTxt(paramContext, false, "check version return----" + localCalendar.get(11) + "," + localCalendar.get(12) + "," + i);
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        String str1 = Utils.getBaseRequestUrl() + "ezweather/get_version.php";
        str1 = new NetworkManager(CommonUtils.this, str1).excute("load_store_version");
        if ("Unknown".equals(str1)) {
          return;
        }
        String str2;
        try
        {
          str1 = new JSONObject(str1).getString("version");
          str2 = Preferences.getStoreVersion(CommonUtils.this);
          if (!str2.equals(str1))
          {
            Utils.logAndTxt(CommonUtils.this, false, "check version change,online=" + str1 + ",local=" + str2);
            Preferences.setStoreVersion(CommonUtils.this, str1);
            Preferences.setStoreVersionUpdate(CommonUtils.this, true);
            Preferences.setStoreIconClicked(CommonUtils.this, false);
            return;
          }
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
          return;
        }
        Utils.logAndTxt(CommonUtils.this, false, "check version not change,online=" + localJSONException + ",local=" + str2);
        Preferences.setStoreVersionUpdate(CommonUtils.this, false);
      }
    }).start();
  }
  
  public static int[] combineArray(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    if ((paramArrayOfInt1 == null) && (paramArrayOfInt2 == null)) {
      arrayOfInt = null;
    }
    do
    {
      return arrayOfInt;
      if (paramArrayOfInt1 != null) {
        break;
      }
      arrayOfInt = paramArrayOfInt2;
    } while (paramArrayOfInt2 != null);
    if ((paramArrayOfInt2 == null) && (paramArrayOfInt1 != null)) {
      return paramArrayOfInt1;
    }
    int j = paramArrayOfInt1.length;
    int k = paramArrayOfInt2.length;
    int[] arrayOfInt = new int[j + k];
    int i = 0;
    if (i > j - 1)
    {
      i = 0;
      label61:
      if (i <= k - 1) {
        break label98;
      }
      i = 0;
    }
    for (;;)
    {
      if (i > j + k - 1)
      {
        return arrayOfInt;
        arrayOfInt[i] = paramArrayOfInt1[i];
        i += 1;
        break;
        label98:
        arrayOfInt[(j + i)] = paramArrayOfInt2[i];
        i += 1;
        break label61;
      }
      l("array" + i + "=" + arrayOfInt[i]);
      i += 1;
    }
  }
  
  /* Error */
  public static void downloadPlugin(Context paramContext, PluginInfo paramPluginInfo)
  {
    // Byte code:
    //   0: new 40	android/content/Intent
    //   3: dup
    //   4: ldc 42
    //   6: new 78	java/lang/StringBuilder
    //   9: dup
    //   10: ldc 127
    //   12: invokespecial 81	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   15: aload_1
    //   16: invokevirtual 132	mobi/infolife/store/PluginInfo:getPkgName	()Ljava/lang/String;
    //   19: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   25: invokestatic 138	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   28: invokespecial 141	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   31: astore_2
    //   32: aload_0
    //   33: aload_2
    //   34: invokevirtual 59	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   37: return
    //   38: astore_2
    //   39: aconst_null
    //   40: astore_2
    //   41: new 40	android/content/Intent
    //   44: dup
    //   45: ldc 42
    //   47: new 78	java/lang/StringBuilder
    //   50: dup
    //   51: ldc -113
    //   53: invokespecial 81	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   56: aload_1
    //   57: invokevirtual 132	mobi/infolife/store/PluginInfo:getPkgName	()Ljava/lang/String;
    //   60: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   66: invokestatic 138	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   69: invokespecial 141	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   72: astore_1
    //   73: aload_0
    //   74: aload_1
    //   75: invokevirtual 59	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   78: return
    //   79: astore_0
    //   80: return
    //   81: astore_0
    //   82: return
    //   83: astore_3
    //   84: goto -43 -> 41
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	87	0	paramContext	Context
    //   0	87	1	paramPluginInfo	PluginInfo
    //   31	3	2	localIntent	Intent
    //   38	1	2	localActivityNotFoundException1	android.content.ActivityNotFoundException
    //   40	1	2	localObject	Object
    //   83	1	3	localActivityNotFoundException2	android.content.ActivityNotFoundException
    // Exception table:
    //   from	to	target	type
    //   0	32	38	android/content/ActivityNotFoundException
    //   73	78	79	java/lang/Exception
    //   41	73	81	java/lang/Exception
    //   32	37	83	android/content/ActivityNotFoundException
  }
  
  public static String exec(String paramString)
  {
    String str2 = "";
    str1 = str2;
    try
    {
      InputStream localInputStream = Runtime.getRuntime().exec(paramString).getInputStream();
      str1 = str2;
      paramString = new byte['Ѐ'];
      str1 = str2;
      StringBuilder localStringBuilder = new StringBuilder();
      for (;;)
      {
        str1 = str2;
        int i = localInputStream.read(paramString, 0, paramString.length);
        if (i == -1)
        {
          str1 = str2;
          paramString = localStringBuilder.toString();
          str1 = paramString;
          localInputStream.close();
          return paramString;
        }
        str1 = str2;
        localStringBuilder.append(new String(paramString, 0, i));
      }
      return str1;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static Animation fadeOutAnimation()
  {
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(1.0F, 0.0F);
    localAlphaAnimation.setDuration(200L);
    return localAlphaAnimation;
  }
  
  public static Intent getCalendarClassName(Context paramContext, Intent paramIntent)
  {
    String str1 = Preferences.getCalendarPackageName(paramContext);
    String str3 = Preferences.getCalendarActivityName(paramContext);
    if (!TextUtils.equals(str1, "Unknown"))
    {
      paramIntent = paramContext.getPackageManager().getLaunchIntentForPackage(str1);
      if (paramIntent != null) {
        return paramIntent;
      }
      return getIntentFromPackageName(paramContext, str1);
    }
    int j = 0;
    for (;;)
    {
      Object localObject1;
      int i;
      try
      {
        localObject1 = paramContext.getPackageManager().getInstalledPackages(0);
        if ((localObject1 == null) || (((List)localObject1).size() <= 0)) {
          break label238;
        }
        i = 0;
        if (i >= ((List)localObject1).size())
        {
          localObject2 = str3;
          localObject1 = str1;
          if (j != 0)
          {
            localObject2 = str3;
            localObject1 = str1;
            if (TextUtils.equals(str1, "Unknown"))
            {
              localObject1 = "com.android.calendar";
              localObject2 = "com.android.calendar.LaunchActivity";
            }
          }
          if (!TextUtils.equals((CharSequence)localObject1, "Unknown")) {
            break label244;
          }
          localObject1 = "com.android.settings";
          localObject2 = "com.android.settings.DateTimeSettings";
          return paramIntent.setClassName((String)localObject1, (String)localObject2);
        }
      }
      catch (Exception localException)
      {
        return getCalendarClassNameOld(paramContext, paramIntent);
      }
      Object localObject2 = (PackageInfo)((List)localObject1).get(i);
      String str2;
      if (((PackageInfo)localObject2).packageName.equals("com.htc.calendar"))
      {
        str2 = "com.htc.calendar";
        str3 = "com.htc.calendar.CalendarActivityMain";
      }
      if (((PackageInfo)localObject2).packageName.equals("com.google.android.calendar"))
      {
        str2 = "com.google.android.calendar";
        str3 = "com.android.calendar.LaunchActivity";
      }
      if (((PackageInfo)localObject2).packageName.equals("com.android.calendar")) {
        j = 1;
      }
      i += 1;
      continue;
      label238:
      return getCalendarClassNameOld(paramContext, paramIntent);
      label244:
      Preferences.setCalendarPackageName(paramContext, (String)localObject1);
      Preferences.setCalendarActivityName(paramContext, (String)localObject2);
    }
  }
  
  public static Intent getCalendarClassNameOld(Context paramContext, Intent paramIntent)
  {
    String str1 = "Unknown";
    String str2 = "Unknown";
    int j = 0;
    Object localObject1 = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    Object localObject2;
    if (i >= ((List)localObject1).size())
    {
      localObject2 = str2;
      localObject1 = str1;
      if (j != 0)
      {
        localObject2 = str2;
        localObject1 = str1;
        if (TextUtils.equals(str1, "Unknown"))
        {
          localObject1 = "com.android.calendar";
          localObject2 = "com.android.calendar.LaunchActivity";
        }
      }
      if (!TextUtils.equals((CharSequence)localObject1, "Unknown")) {
        break label178;
      }
      localObject1 = "com.android.settings";
      localObject2 = "com.android.settings.DateTimeSettings";
    }
    for (;;)
    {
      return paramIntent.setClassName((String)localObject1, (String)localObject2);
      localObject2 = (PackageInfo)((List)localObject1).get(i);
      if (((PackageInfo)localObject2).packageName.equals("com.htc.calendar"))
      {
        str1 = "com.htc.calendar";
        str2 = "com.htc.calendar.CalendarActivityMain";
      }
      if (((PackageInfo)localObject2).packageName.equals("com.google.android.calendar"))
      {
        str1 = "com.google.android.calendar";
        str2 = "com.android.calendar.LaunchActivity";
      }
      if (((PackageInfo)localObject2).packageName.equals("com.android.calendar")) {
        j = 1;
      }
      i += 1;
      break;
      label178:
      Preferences.setCalendarPackageName(paramContext, (String)localObject1);
      Preferences.setCalendarActivityName(paramContext, (String)localObject2);
    }
  }
  
  public static String getClassName(Context paramContext, String paramString)
  {
    Object localObject = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject).addCategory("android.intent.category.LAUNCHER");
    paramContext = paramContext.getPackageManager().queryIntentActivities((Intent)localObject, 0).iterator();
    do
    {
      if (!paramContext.hasNext()) {
        return "";
      }
      localObject = (ResolveInfo)paramContext.next();
    } while (!((ResolveInfo)localObject).activityInfo.packageName.equals(paramString));
    return ((ResolveInfo)localObject).activityInfo.name;
  }
  
  public static Intent getClockClassName(Context paramContext, Intent paramIntent)
  {
    Object localObject1 = Preferences.getClockPackageName(paramContext);
    Object localObject2 = Preferences.getClockActivityName(paramContext);
    if (!TextUtils.equals((CharSequence)localObject1, "Unknown"))
    {
      paramIntent = paramContext.getPackageManager().getLaunchIntentForPackage((String)localObject1);
      if (paramIntent != null) {
        return paramIntent;
      }
      return getIntentFromPackageName(paramContext, (String)localObject1);
    }
    int k = 0;
    int i = 0;
    for (;;)
    {
      List localList;
      int j;
      try
      {
        localList = paramContext.getPackageManager().getInstalledPackages(1);
        if ((localList == null) || (localList.size() <= 0)) {
          break label588;
        }
        j = 0;
        if (j >= localList.size())
        {
          localObject5 = localObject2;
          Object localObject3 = localObject1;
          if (k != 0)
          {
            localObject5 = localObject2;
            localObject3 = localObject1;
            if (TextUtils.equals((CharSequence)localObject1, "Unknown"))
            {
              localObject3 = "com.google.android.deskclock";
              localObject5 = "com.android.deskclock.AlarmClock";
            }
          }
          localObject2 = localObject5;
          localObject1 = localObject3;
          if (i != 0)
          {
            localObject2 = localObject5;
            localObject1 = localObject3;
            if (TextUtils.equals((CharSequence)localObject3, "Unknown"))
            {
              localObject1 = "com.android.deskclock";
              localObject2 = "com.android.deskclock.AlarmClock";
            }
          }
          if (!TextUtils.equals((CharSequence)localObject1, "Unknown")) {
            break label594;
          }
          localObject1 = "com.android.settings";
          localObject2 = "com.android.settings.DateTimeSettings";
          return paramIntent.setClassName((String)localObject1, (String)localObject2);
        }
      }
      catch (Exception localException)
      {
        l("exception : " + (String)localObject1 + "/" + (String)localObject2);
        localException.printStackTrace();
        return getClockClassNameOld(paramContext, paramIntent);
      }
      PackageInfo localPackageInfo = (PackageInfo)localList.get(j);
      if (localPackageInfo.packageName.equals("com.htc.android.worldclock"))
      {
        localObject1 = "com.htc.android.worldclock";
        localObject2 = "com.htc.android.worldclock.WorldClockTabControl";
      }
      if (localPackageInfo.packageName.equals("com.motorola.blur.alarmclock"))
      {
        localObject1 = "com.motorola.blur.alarmclock";
        localObject2 = "com.motorola.blur.alarmclock.AlarmClock";
      }
      if (localPackageInfo.packageName.equals("com.sec.android.app.clockpackage"))
      {
        localObject1 = "com.sec.android.app.clockpackage";
        localObject2 = "com.sec.android.app.clockpackage.ClockPackage";
      }
      Object localObject4 = localObject2;
      localObject2 = localObject1;
      if (localPackageInfo.packageName.equals("com.lge.alarm"))
      {
        localObject2 = "com.lge.alarm";
        localObject4 = "com.lge.alarm.Super_Clock";
      }
      localObject1 = localObject4;
      if (localPackageInfo.packageName.equals("com.sonyericsson.organizer"))
      {
        localObject2 = "com.sonyericsson.organizer";
        localObject1 = "com.sonyericsson.organizer.Organizer";
      }
      Object localObject5 = localObject1;
      int m = i;
      localObject4 = localObject2;
      if (localPackageInfo.packageName.equals("com.android.deskclock")) {
        m = 0;
      }
      for (;;)
      {
        if (m > localPackageInfo.activities.length - 1)
        {
          localObject4 = localObject2;
          m = i;
          localObject5 = localObject1;
          localObject2 = localObject5;
          localObject1 = localObject4;
          if (localPackageInfo.packageName.equals("com.android.alarmclock"))
          {
            localObject1 = "com.android.alarmclock";
            localObject2 = "com.android.alarmclock.AlarmClock";
          }
          if (localPackageInfo.packageName.equals("com.google.android.deskclock")) {
            k = 1;
          }
          j += 1;
          i = m;
          break;
        }
        if (localPackageInfo.activities[m].name.equals("com.android.deskclock.DeskClockTabActivity"))
        {
          localObject2 = "com.android.deskclock";
          localObject1 = "com.android.deskclock.DeskClockTabActivity";
        }
        if (localPackageInfo.activities[m].name.equals("com.android.deskclock.AlarmsMainActivity"))
        {
          localObject2 = "com.android.deskclock";
          localObject1 = "com.android.deskclock.AlarmsMainActivity";
        }
        if (localPackageInfo.activities[m].name.equals("com.android.deskclock.DeskClock")) {
          i = 1;
        }
        m += 1;
      }
      label588:
      return getClockClassNameOld(paramContext, paramIntent);
      label594:
      Preferences.setClockPackageName(paramContext, (String)localObject1);
      Preferences.setClockActivityName(paramContext, (String)localObject2);
    }
  }
  
  public static Intent getClockClassNameOld(Context paramContext, Intent paramIntent)
  {
    Object localObject1 = "Unknown";
    Object localObject2 = "Unknown";
    int j = 0;
    int k = 0;
    for (;;)
    {
      Object localObject3;
      int i;
      try
      {
        localObject3 = paramContext.getPackageManager().getInstalledPackages(0);
        i = 0;
        if (i >= ((List)localObject3).size())
        {
          localObject4 = localObject2;
          localObject3 = localObject1;
          if (j != 0)
          {
            localObject4 = localObject2;
            localObject3 = localObject1;
            if (TextUtils.equals((CharSequence)localObject1, "Unknown"))
            {
              localObject3 = "com.google.android.deskclock";
              localObject4 = "com.android.deskclock.AlarmClock";
            }
          }
          localObject2 = localObject4;
          localObject1 = localObject3;
          if (k != 0)
          {
            localObject2 = localObject4;
            localObject1 = localObject3;
            if (TextUtils.equals((CharSequence)localObject3, "Unknown"))
            {
              localObject1 = "com.android.deskclock";
              localObject2 = "com.android.deskclock.AlarmClock";
            }
          }
          if (!TextUtils.equals((CharSequence)localObject1, "Unknown")) {
            break label387;
          }
          localObject1 = "com.android.settings";
          localObject2 = "com.android.settings.DateTimeSettings";
          return paramIntent.setClassName((String)localObject1, (String)localObject2);
        }
      }
      catch (Exception paramContext)
      {
        l("exception : " + "Unknown" + "/" + "Unknown");
        paramContext.printStackTrace();
        return paramIntent.setClassName("com.android.settings", "com.android.settings.DateTimeSettings");
      }
      Object localObject4 = (PackageInfo)((List)localObject3).get(i);
      if (((PackageInfo)localObject4).packageName.equals("com.htc.android.worldclock"))
      {
        localObject1 = "com.htc.android.worldclock";
        localObject2 = "com.htc.android.worldclock.WorldClockTabControl";
      }
      if (((PackageInfo)localObject4).packageName.equals("com.motorola.blur.alarmclock"))
      {
        localObject1 = "com.motorola.blur.alarmclock";
        localObject2 = "com.motorola.blur.alarmclock.AlarmClock";
      }
      if (((PackageInfo)localObject4).packageName.equals("com.sec.android.app.clockpackage"))
      {
        localObject1 = "com.sec.android.app.clockpackage";
        localObject2 = "com.sec.android.app.clockpackage.ClockPackage";
      }
      if (((PackageInfo)localObject4).packageName.equals("com.lge.alarm"))
      {
        localObject1 = "com.lge.alarm";
        localObject2 = "com.lge.alarm.Super_Clock";
      }
      if (((PackageInfo)localObject4).packageName.equals("com.sonyericsson.organizer"))
      {
        localObject1 = "com.sonyericsson.organizer";
        localObject2 = "com.sonyericsson.organizer.Organizer";
      }
      if (((PackageInfo)localObject4).packageName.equals("com.android.deskclock")) {
        k = 1;
      }
      if (((PackageInfo)localObject4).packageName.equals("com.android.alarmclock"))
      {
        localObject1 = "com.android.alarmclock";
        localObject2 = "com.android.alarmclock.AlarmClock";
      }
      if (((PackageInfo)localObject4).packageName.equals("com.google.android.deskclock")) {
        j = 1;
      }
      i += 1;
      continue;
      label387:
      Preferences.setClockPackageName(paramContext, (String)localObject1);
      Preferences.setClockActivityName(paramContext, (String)localObject2);
    }
  }
  
  public static String getColoredHtmlString(String paramString, int paramInt)
  {
    if (paramString == null) {
      return null;
    }
    return "<font color=\"#" + Integer.toHexString(paramInt) + "\">" + paramString + "</font>";
  }
  
  public static int getCurrentVersionCode(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = paramContext.getPackageName();
    try
    {
      int i = localPackageManager.getPackageInfo(paramContext, 0).versionCode;
      return i;
    }
    catch (Exception paramContext) {}
    return 1;
  }
  
  public static String getDefaultBackupPath()
  {
    return getSDCardDirPath() + "/" + "EzWeather";
  }
  
  public static EasyTracker getEasyTracker(Context paramContext)
  {
    EasyTracker localEasyTracker = EasyTracker.getInstance(paramContext);
    localEasyTracker.set("&tid", paramContext.getString(2131558434));
    return localEasyTracker;
  }
  
  public static String getExternalStorageDirPath()
  {
    return Environment.getExternalStorageDirectory().getAbsolutePath();
  }
  
  public static List<PackageInfo> getInstalledAppList(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager();
    paramContext = null;
    try
    {
      localObject = ((PackageManager)localObject).getInstalledPackages(8192);
      paramContext = (Context)localObject;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    localObject = paramContext;
    if (paramContext == null) {
      localObject = null;
    }
    return localObject;
  }
  
  public static List<PluginInfo> getInstalledDataSourcePlugins(Context paramContext, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = new PluginInfo();
    ((PluginInfo)localObject).setPkgName(paramContext.getPackageName());
    ((PluginInfo)localObject).setTitle(paramContext.getString(2131558611));
    ((PluginInfo)localObject).setFeature(paramContext.getString(2131558629));
    ((PluginInfo)localObject).setRestriction(paramContext.getString(2131558630));
    ((PluginInfo)localObject).setIcon(TaskUtils.drawableToByteArray(TaskUtils.getDefaultIconBitmap(paramContext)));
    localArrayList.add(localObject);
    localObject = paramContext.getPackageManager();
    Iterator localIterator = getInstalledAppList(paramContext).iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localArrayList;
      }
      String str1 = ((PackageInfo)localIterator.next()).packageName;
      PluginInfo localPluginInfo = new PluginInfo();
      try
      {
        ApplicationInfo localApplicationInfo = ((PackageManager)localObject).getApplicationInfo(str1, 128);
        if ((localApplicationInfo != null) && (localApplicationInfo.metaData != null))
        {
          String str2 = localApplicationInfo.metaData.getString("EZWEATHER_PLUGIN");
          if ((str2 != null) && (!"".equals(str2)) && (paramString.equals(str2)))
          {
            localPluginInfo.setPkgName(str1);
            localPluginInfo.setPluginState(1);
            localPluginInfo.setIcon(TaskUtils.drawableToByteArray(TaskUtils.drawableToBitmap(((PackageManager)localObject).getApplicationIcon(localApplicationInfo))));
            localPluginInfo.setTitle(((PackageManager)localObject).getApplicationLabel(localApplicationInfo).toString());
            localArrayList.add(updateDataSourcePluginInfo(paramContext, localPluginInfo));
          }
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
      }
    }
  }
  
  public static List<PluginInfo> getInstalledPluginInfo(Context paramContext, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = new PluginInfo();
    ((PluginInfo)localObject).setPkgName(paramContext.getPackageName());
    localObject = updateDataSourcePluginInfo(paramContext, (PluginInfo)localObject);
    ((PluginInfo)localObject).setTitle(paramContext.getString(2131558611));
    ((PluginInfo)localObject).setFeature(paramContext.getString(2131558629));
    ((PluginInfo)localObject).setRestriction(paramContext.getString(2131558630));
    localArrayList.add(localObject);
    localObject = new DatabaseAdapter(paramContext).selectPluginByType(paramInt);
    if (localObject == null) {}
    for (;;)
    {
      return localArrayList;
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        localArrayList.add(updateDataSourcePluginInfo(paramContext, (PluginInfo)((Iterator)localObject).next()));
      }
    }
  }
  
  public static List<WidgetPreviewInfo> getInstalledWidgetPluginsForSetting(Context paramContext, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    Iterator localIterator = getInstalledAppList(paramContext).iterator();
    if (!localIterator.hasNext()) {
      return localArrayList;
    }
    String str = ((PackageInfo)localIterator.next()).packageName;
    WidgetPreviewInfo localWidgetPreviewInfo = new WidgetPreviewInfo();
    for (;;)
    {
      Object localObject1;
      Object localObject2;
      WidgetConfig localWidgetConfig;
      try
      {
        localObject1 = localPackageManager.getApplicationInfo(str, 128);
        if ((localObject1 == null) || (((ApplicationInfo)localObject1).metaData == null)) {
          break;
        }
        localObject2 = ((ApplicationInfo)localObject1).metaData.getString("EZWEATHER_PLUGIN");
        if ((localObject2 == null) || ("".equals(localObject2)) || (!"mobi.infolife.ezweather.plugin.widgetskin".equals(localObject2))) {
          break;
        }
        localWidgetPreviewInfo.setPluginPkgName(str);
        localWidgetPreviewInfo.setPluginLabel(localPackageManager.getApplicationLabel((ApplicationInfo)localObject1).toString());
        localObject1 = getOtherAppContext(paramContext, str);
        localObject2 = getRClass((Context)localObject1, str);
        localWidgetConfig = new WidgetConfig(paramContext, str);
        switch (paramInt)
        {
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
      }
      try
      {
        if (!((Context)localObject1).getResources().getBoolean(getResourseIdByName((Class)localObject2, "bool", "isFree"))) {
          break label355;
        }
        localWidgetPreviewInfo.setPaid(true);
        CommonPreferences.setSkinPaidStatByPackageName(paramContext, str, true);
        localWidgetPreviewInfo.setPluginName(((Context)localObject1).getString(getResourseIdByName((Class)localObject2, "string", "pluginName")));
      }
      catch (Exception localException)
      {
        continue;
      }
      localArrayList.add(localWidgetPreviewInfo);
      break;
      break;
      if (!localWidgetConfig.isContain4_1()) {
        break;
      }
      localNameNotFoundException.setPreviewID(getResourseIdByName((Class)localObject2, "drawable", "widgetconfig_preview_4x1"));
      continue;
      if (!localWidgetConfig.isContain4_2()) {
        break;
      }
      localNameNotFoundException.setPreviewID(getResourseIdByName((Class)localObject2, "drawable", "widgetconfig_preview_4x2"));
      continue;
      if (!localWidgetConfig.isContain1_1())
      {
        break;
        label355:
        localNameNotFoundException.setPaid(WidgetSettingActivity.isWidgetEnable(paramContext, str));
        localNameNotFoundException.setProductId(((Context)localObject1).getString(getResourseIdByName((Class)localObject2, "string", "productId")));
      }
    }
  }
  
  public static List<PluginInfo> getInstalledWidgetPluginsForStore(Context paramContext, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject1 = getInstalledAppList(paramContext);
    ApplicationInfo localApplicationInfo = null;
    Object localObject2;
    if (paramInt == 1)
    {
      localObject2 = new PluginInfo(paramContext.getPackageName());
      ((PluginInfo)localObject2).setTitle("Default");
      ((PluginInfo)localObject2).setInstalled(true);
      CommonPreferences.setSkinPaidStatByPackageName(paramContext, paramContext.getPackageName(), true);
      localArrayList.add(0, localObject2);
    }
    localObject1 = ((List)localObject1).iterator();
    paramContext = localApplicationInfo;
    for (;;)
    {
      if (!((Iterator)localObject1).hasNext()) {
        return localArrayList;
      }
      localObject2 = ((PackageInfo)((Iterator)localObject1).next()).packageName;
      String str;
      Context localContext;
      try
      {
        localApplicationInfo = localPackageManager.getApplicationInfo((String)localObject2, 128);
        paramContext = localApplicationInfo;
        if (localApplicationInfo == null) {
          continue;
        }
        paramContext = localApplicationInfo;
        if (localApplicationInfo.metaData == null) {
          continue;
        }
        str = localApplicationInfo.metaData.getString("EZWEATHER_PLUGIN");
        paramContext = localApplicationInfo;
        if (str == null) {
          continue;
        }
        paramContext = localApplicationInfo;
        if ("".equals(str)) {
          continue;
        }
        switch (paramInt)
        {
        default: 
          paramContext = localApplicationInfo;
          break;
        case 0: 
          paramContext = localApplicationInfo;
          if (!"mobi.infolife.ezweather.plugin.widgetskin".equals(str)) {
            continue;
          }
          paramContext = new PluginInfo((String)localObject2);
          paramContext.setTitle(localPackageManager.getApplicationLabel(localApplicationInfo).toString());
          paramContext.setInstalled(true);
          localArrayList.add(paramContext);
          paramContext = localApplicationInfo;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;)
        {
          localNameNotFoundException.printStackTrace();
          localContext = paramContext;
        }
        paramContext = localContext;
      }
      if ("mobi.infolife.ezweather.plugin.iconset".equals(str))
      {
        paramContext = new PluginInfo((String)localObject2);
        paramContext.setTitle(localPackageManager.getApplicationLabel(localContext).toString());
        paramContext.setInstalled(true);
        localArrayList.add(paramContext);
        paramContext = localContext;
      }
    }
  }
  
  public static Intent getIntentFromPackageName(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setClassName(paramString, getClassName(paramContext, paramString));
    return localIntent;
  }
  
  public static Context getOtherAppContext(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getApplicationContext().createPackageContext(paramString, 3);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static Class<?> getRClass(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getClassLoader().loadClass(paramString + ".R");
      return paramContext;
    }
    catch (ClassNotFoundException paramContext)
    {
      paramContext.printStackTrace();
      return null;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static int getResourseIdByName(Class paramClass, String paramString1, String paramString2)
  {
    try
    {
      Class[] arrayOfClass = paramClass.getClasses();
      paramClass = null;
      int i = 0;
      for (;;)
      {
        if (i >= arrayOfClass.length) {}
        for (;;)
        {
          if (paramClass == null) {
            break label101;
          }
          return paramClass.getField(paramString2).getInt(paramClass);
          if (!arrayOfClass[i].getName().split("\\$")[1].equals(paramString1)) {
            break;
          }
          paramClass = arrayOfClass[i];
        }
        i += 1;
      }
      return 0;
    }
    catch (IllegalArgumentException paramClass)
    {
      paramClass.printStackTrace();
      return 0;
    }
    catch (SecurityException paramClass)
    {
      paramClass.printStackTrace();
      return 0;
    }
    catch (IllegalAccessException paramClass)
    {
      paramClass.printStackTrace();
      return 0;
    }
    catch (NoSuchFieldException paramClass)
    {
      paramClass.printStackTrace();
      return 0;
    }
    catch (Exception paramClass)
    {
      paramClass.printStackTrace();
    }
  }
  
  public static String getSDCardDirPath()
  {
    if (isSamSungDevice())
    {
      if (isSamSungExternalSDMounted()) {
        return getExternalStorageDirPath() + "/" + "external_sd";
      }
      return getExternalStorageDirPath();
    }
    return getExternalStorageDirPath();
  }
  
  public static int getSDKVersionNumber()
  {
    try
    {
      int i = Integer.valueOf(Build.VERSION.SDK).intValue();
      return i;
    }
    catch (NumberFormatException localNumberFormatException) {}
    return 0;
  }
  
  public static Drawable getUsingDataSourceIcon(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getApplicationInfo(paramString, 0).loadIcon(paramContext);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static Class<?> getUtilsClass(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getClassLoader().loadClass(paramString + ".Utils");
      return paramContext;
    }
    catch (ClassNotFoundException paramContext)
    {
      paramContext.printStackTrace();
      return null;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static void gotoMarket(Context paramContext)
  {
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramContext.getPackageName())));
      return;
    }
    catch (Exception localException)
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + paramContext.getPackageName())));
    }
  }
  
  public static void gotoMarket(Context paramContext, String paramString)
  {
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString)));
      return;
    }
    catch (Exception localException)
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + paramString)));
    }
  }
  
  public static void hidePluginIcon(Context paramContext, String paramString)
  {
    localContext2 = null;
    localContext1 = null;
    localObject = null;
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getApplicationContext().createPackageContext(paramString, 3);
      localContext1 = paramContext;
      localContext2 = paramContext;
      paramString = localPackageManager.getLaunchIntentForPackage(paramString);
      localContext1 = paramContext;
      localContext2 = paramContext;
      paramString = paramContext.getClassLoader().loadClass(paramString.resolveActivity(localPackageManager).getClassName());
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        int i;
        paramContext.printStackTrace();
        paramContext = localContext1;
        paramString = localObject;
      }
    }
    catch (ClassNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = localContext2;
        paramString = localObject;
      }
    }
    paramContext = new ComponentName(paramContext, paramString);
    i = localPackageManager.getComponentEnabledSetting(paramContext);
    if ((i == 0) || (i == 1)) {
      localPackageManager.setComponentEnabledSetting(paramContext, 2, 1);
    }
  }
  
  public static Animation inFromLeftAnimation()
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(2, -1.0F, 2, 0.0F, 2, 0.0F, 2, 0.0F);
    localTranslateAnimation.setDuration(200L);
    localTranslateAnimation.setInterpolator(new AccelerateInterpolator());
    return localTranslateAnimation;
  }
  
  public static Animation inFromRightAndFadeInAnimation()
  {
    AnimationSet localAnimationSet = new AnimationSet(true);
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.5F, 1.0F);
    localAlphaAnimation.setDuration(600L);
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(2, -1.0F, 2, 0.0F, 2, 0.0F, 2, 0.0F);
    localTranslateAnimation.setDuration(200L);
    localAnimationSet.addAnimation(localAlphaAnimation);
    localAnimationSet.addAnimation(localTranslateAnimation);
    return localAnimationSet;
  }
  
  public static Animation inFromRightAnimation()
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(2, 1.0F, 2, 0.0F, 2, 0.0F, 2, 0.0F);
    localTranslateAnimation.setDuration(200L);
    localTranslateAnimation.setInterpolator(new AccelerateInterpolator());
    return localTranslateAnimation;
  }
  
  public static boolean isAirplaneModeOn(Context paramContext)
  {
    return Settings.System.getInt(paramContext.getContentResolver(), "airplane_mode_on", 0) == 1;
  }
  
  public static boolean isGpsOn(Context paramContext)
  {
    return Settings.Secure.isLocationProviderEnabled(paramContext.getContentResolver(), "gps");
  }
  
  public static boolean isSamSungDevice()
  {
    return new File(getExternalStorageDirPath() + "/" + "external_sd").exists();
  }
  
  public static boolean isSamSungExternalSDMounted()
  {
    return exec("mount").indexOf("external_sd") >= 0;
  }
  
  public static boolean isWifiAvailable(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    int i;
    if (paramContext != null)
    {
      paramContext = paramContext.getAllNetworkInfo();
      if (paramContext != null) {
        i = 0;
      }
    }
    for (;;)
    {
      if (i >= paramContext.length) {
        return false;
      }
      if ((paramContext[i].getState() == NetworkInfo.State.CONNECTED) && (paramContext[i].getTypeName().equals("WIFI"))) {
        return true;
      }
      i += 1;
    }
  }
  
  public static void killSelf()
  {
    android.os.Process.killProcess(android.os.Process.myPid());
  }
  
  public static void l(String paramString) {}
  
  public static void l(String[] paramArrayOfString)
  {
    if (paramArrayOfString == null) {}
    while (paramArrayOfString.length != 0) {
      return;
    }
  }
  
  public static String[][] loadAlarmInfo(Context paramContext)
  {
    Object localObject = new Intent("android.intent.action.SET_ALARM");
    ((Intent)localObject).putExtra("android.intent.extra.alarm.MESSAGE", "New Alarm");
    ((Intent)localObject).putExtra("android.intent.extra.alarm.HOUR", 10);
    ((Intent)localObject).putExtra("android.intent.extra.alarm.MINUTES", 30);
    List localList = paramContext.getPackageManager().queryIntentActivities((Intent)localObject, 0);
    int j = localList.size();
    String[][] arrayOfString = (String[][])Array.newInstance(String.class, new int[] { j, 3 });
    localObject = "";
    int i = 0;
    for (;;)
    {
      if (i >= j)
      {
        l("Have " + arrayOfString.length + " Alarm:" + (String)localObject);
        return arrayOfString;
      }
      String str1 = ((ResolveInfo)localList.get(i)).loadLabel(paramContext.getPackageManager()).toString();
      String str2 = ((ResolveInfo)localList.get(i)).activityInfo.packageName;
      String str3 = ((ResolveInfo)localList.get(i)).activityInfo.name;
      arrayOfString[i][0] = str1;
      arrayOfString[i][1] = str2;
      arrayOfString[i][2] = str3;
      localObject = localObject + str1 + "/";
      i += 1;
    }
  }
  
  public static String[][] loadCalendarInfo(Context paramContext)
  {
    Object localObject = new Intent("android.intent.action.EDIT");
    ((Intent)localObject).setType("vnd.android.cursor.item/event");
    ((Intent)localObject).putExtra("title", "Some title");
    ((Intent)localObject).putExtra("description", "Some description");
    String str1 = "";
    localObject = paramContext.getPackageManager().queryIntentActivities((Intent)localObject, 0);
    int j = ((List)localObject).size();
    String[][] arrayOfString = (String[][])Array.newInstance(String.class, new int[] { j, 3 });
    int i = 0;
    for (;;)
    {
      if (i >= j)
      {
        l("Have " + arrayOfString.length + " Calendr:" + str1);
        return arrayOfString;
      }
      String str2 = ((ResolveInfo)((List)localObject).get(i)).activityInfo.packageName;
      String str3 = ((ResolveInfo)((List)localObject).get(i)).loadLabel(paramContext.getPackageManager()).toString();
      String str4 = ((ResolveInfo)((List)localObject).get(i)).activityInfo.name;
      arrayOfString[i][0] = str3;
      arrayOfString[i][1] = str2;
      arrayOfString[i][2] = str4;
      str1 = str1 + str3 + "/";
      i += 1;
    }
  }
  
  public static void log(String paramString) {}
  
  public static void log4qq(String paramString) {}
  
  public static Animation outToLeftAnimation()
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(2, 0.0F, 2, -1.0F, 2, 0.0F, 2, 0.0F);
    localTranslateAnimation.setDuration(200L);
    localTranslateAnimation.setInterpolator(new AccelerateInterpolator());
    return localTranslateAnimation;
  }
  
  public static Animation outToRightAnimation()
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(2, 0.0F, 2, 1.0F, 2, 0.0F, 2, 0.0F);
    localTranslateAnimation.setDuration(200L);
    localTranslateAnimation.setInterpolator(new AccelerateInterpolator());
    return localTranslateAnimation;
  }
  
  public static byte[] readStream(InputStream paramInputStream)
    throws Exception
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1)
      {
        localByteArrayOutputStream.close();
        paramInputStream.close();
        return localByteArrayOutputStream.toByteArray();
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
  }
  
  public static void setListViewHeightBasedOnChildren(ListView paramListView)
  {
    ListAdapter localListAdapter = paramListView.getAdapter();
    if (localListAdapter == null) {
      return;
    }
    int j = 0;
    int i = 0;
    for (;;)
    {
      if (i >= localListAdapter.getCount())
      {
        localObject = paramListView.getLayoutParams();
        ((ViewGroup.LayoutParams)localObject).height = (paramListView.getDividerHeight() * (localListAdapter.getCount() - 1) + j);
        paramListView.setLayoutParams((ViewGroup.LayoutParams)localObject);
        return;
      }
      Object localObject = localListAdapter.getView(i, null, paramListView);
      ((View)localObject).measure(0, 0);
      j += ((View)localObject).getMeasuredHeight();
      i += 1;
    }
  }
  
  public static void showDebugToast(Context paramContext, String paramString) {}
  
  public static void showLongToast(Context paramContext, String paramString)
  {
    Toast.makeText(paramContext, paramString, 1).show();
  }
  
  public static void showShortToast(Context paramContext, String paramString)
  {
    Toast.makeText(paramContext, paramString, 0).show();
  }
  
  public static String stringArrayToString(String[] paramArrayOfString)
  {
    if (paramArrayOfString == null) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder("");
    int j = paramArrayOfString.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return localStringBuilder.toString();
      }
      localStringBuilder.append(paramArrayOfString[i]);
      localStringBuilder.append("#WEATHERSPLITER#");
      i += 1;
    }
  }
  
  public static String[] stringToStringArray(String paramString)
  {
    if (paramString == null) {}
    while (paramString == "Unknown") {
      return null;
    }
    return paramString.split("#WEATHERSPLITER#");
  }
  
  public static void t(String paramString) {}
  
  public static PluginInfo updateDataSourcePluginInfo(Context paramContext, PluginInfo paramPluginInfo)
  {
    paramContext = getOtherAppContext(paramContext, paramPluginInfo.getPkgName());
    Class localClass = getRClass(paramContext, paramPluginInfo.getPkgName());
    if (localClass != null)
    {
      paramPluginInfo.setRestriction(paramContext.getString(getResourseIdByName(localClass, "string", "restriction")));
      paramPluginInfo.setFeature(paramContext.getString(getResourseIdByName(localClass, "string", "feature")));
    }
    return paramPluginInfo;
  }
}
