package com.samsung.android.hostmanager.notification;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings.System;
import com.samsung.android.app.watchmanager.plugin.libfactory.floatingfeature.CscFeatureFactory;
import com.samsung.android.app.watchmanager.plugin.libfactory.floatingfeature.FloatingFeatureFactory;
import com.samsung.android.app.watchmanager.plugin.libinterface.floatingfeature.CscFeatureInterface;
import com.samsung.android.app.watchmanager.plugin.libinterface.floatingfeature.FloatingFeatureInterface;
import com.samsung.android.hostmanager.aidl.NotificationApp;
import com.samsung.android.hostmanager.utils.CommonUtils;
import com.samsung.android.hostmanager.utils.Log;
import com.samsung.android.reflectwrapper.ReflectUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Utils
{
  private static final String ACTION_NOTIFICATION_APP_NAME_UPDATE = "android.intent.hostmanager.action.NOTIFICATION_APP_NAME_UPDATE";
  private static String[] ATT_TMO_DefaultOnAppArray;
  private static String[] DefaultOffSalesCodeArray = { "SPR", "VMU", "BST", "XAS", "VZW", "ATT", "TMB", "SKC", "SKO", "SKT" };
  public static String[] IncomingCallPackages;
  private static Integer[] KNOX_USERID = { Integer.valueOf(10), Integer.valueOf(11), Integer.valueOf(95), Integer.valueOf(96), Integer.valueOf(97), Integer.valueOf(98), Integer.valueOf(99), Integer.valueOf(100), Integer.valueOf(101), Integer.valueOf(102), Integer.valueOf(103), Integer.valueOf(150), Integer.valueOf(151), Integer.valueOf(152), Integer.valueOf(153), Integer.valueOf(154), Integer.valueOf(155), Integer.valueOf(156), Integer.valueOf(157), Integer.valueOf(158), Integer.valueOf(159), Integer.valueOf(160) };
  private static final Map<String, Integer> LABEL_MAP;
  public static final String MANUFACT_VIVO = "vivo";
  private static final String META_MASTER_APP_PACKAGENAME = "master_app_packagename";
  private static final String META_NOTIFICATION_MAX_BYTE = "app_notification_maxbyte";
  public static final String MGR_NOTI_CALL_STATUS = "mgr_noti_call_status";
  public static final String MGR_NOTI_MESSAGE_STATUS = "mgr_noti_message_status";
  public static final String MGR_NOTI_VOICEMAIL_STATUS = "mgr_noti_voicemail_status";
  public static final String NOTIBYPASS_WAKEUP_PREVENTION_PACKAGES = "wakeup_prevention_packages";
  public static String[] NumberSyncNormalPackages;
  public static final String PACKAGENAME_REMINDER_APP = "com.samsung.android.app.reminder";
  public static final String PACKAGENAME_SERVER_TELECOM = "com.android.server.telecom";
  public static final String PACKAGENAME_S_REMINDER = "com.samsung.android.application.smartassistant";
  public static final String PACKAGENAME_VERIZON_MESSAGE_APP = "com.verizon.messaging.vzmsgs";
  public static final String PACKAGENAME_VIVO_MMS_APP = "com.android.mms.service";
  private static final Map<String, String> PACKAGE_MAP;
  private static final Map<String, Integer> PREDEFINED_APP_ID_MAP;
  public static final String PREF_NOTIFICATION_INFO_MISC = "pref_notification_info_misc";
  public static final String PREF_NOTI_SETTINGS = "pref_noti_settings";
  public static final String REAL_PACKAGENAME_ALARM = ;
  public static String REAL_PACKAGENAME_CALENDAR;
  public static final String REAL_PACKAGENAME_CALL = "com.android.phone";
  public static final String REAL_PACKAGENAME_EMAIL = "com.android.email";
  public static final String REAL_PACKAGENAME_EMAIL_NEW = "com.samsung.android.email.ui";
  public static String REAL_PACKAGENAME_MESSAGES;
  public static final String REAL_PACKAGENAME_MISSEDCALL = "com.android.phone";
  public static final String REAL_PACKAGENAME_VOICEMAIL = "com.android.phone";
  public static final String REAL_PACKAGENAME_WEATHERWIDGET = "com.sec.android.widgetapp.ap.hero.accuweather";
  private static String[] SKT_DefaultOnAppArray;
  private static final String TAG = Utils.class.getSimpleName();
  public static final String VIRTUAL_PACKAGENAME_ALARM = "alarm";
  public static final String VIRTUAL_PACKAGENAME_CALENDAR = "calendar";
  public static final String VIRTUAL_PACKAGENAME_CALL = "call";
  public static final String VIRTUAL_PACKAGENAME_EMAIL = "email";
  public static final String VIRTUAL_PACKAGENAME_MESSAGES = "messages";
  public static final String VIRTUAL_PACKAGENAME_MISSEDCALL = "missedcall";
  public static final String VIRTUAL_PACKAGENAME_VOICEMAIL = "voicemail";
  private static String[] VMSupportSalesCodeArray;
  private static String[] VZW_Sprint_DefaultOnAppArray;
  private static String[] defaultOnAppArray;
  private static String[] excludeAppArray;
  private static String[] excludeNormalAppArray;
  private static String[] excludeWhenScsConnectionAppArray;
  private static String[] knoxExcludeAppArray;
  private static String[] nonSamsungDefaultOnAppArray;
  private static String[] samsungDeviceExcludeAppArray;
  private static String[] samsungDeviceLollipopExcludeAppArray;
  private static String[] samsungMassDeviceExcludeAppArray;
  public static String[] thirdMmsAppPackages;
  private static String[] weatherWidgetPackages;
  
  static
  {
    PACKAGE_MAP = new Utils.1();
    LABEL_MAP = new Utils.2();
    PREDEFINED_APP_ID_MAP = new Utils.3();
    thirdMmsAppPackages = new String[] { "com.android.mms", "com.samsung.android.messaging", "com.htc.sense.mms", "com.pantech.app.mms", "com.lge.acms", "com.sonyericsson.conversations", "com.google.android.apps.messaging" };
    REAL_PACKAGENAME_CALENDAR = "com.android.calendar";
    REAL_PACKAGENAME_MESSAGES = "com.android.mms";
    NumberSyncNormalPackages = new String[] { "missedcall", "messages", "voicemail" };
    IncomingCallPackages = new String[] { "call", "com.android.phone", "com.android.dialer" };
    defaultOnAppArray = new String[] { "com.kakao.talk", "com.whatsapp", "com.google.android.gm", "com.nhn.android.band", "com.google.android.googlequicksearchbox", "com.facebook.katana", "com.sec.android.app.shealth", "com.facebook.orca", "org.telegram.messenger", "com.tencent.mm", "jp.naver.line.android", "com.instagram.android", "com.yahoo.mobile.client.android.mail", "com.viber.voip", "com.tencent.mobileqq", "com.snapchat.android", "com.twitter.android", "com.google.android.talk", "Uxpp.UC", "com.android.phone", "com.google.android.dialer", "com.android.server.telecom", "com.google.android.deskclock", "com.android.mms", "com.samsung.android.messaging", "com.android.calendar", "com.samsung.android.calendar", "com.samsung.android.app.reminder", "com.sds.mobiledesk", "com.sds.mms.ui", "com.sds.mysinglesquare", "com.android.email", "com.samsung.android.email.ui", "com.samsung.android.email.provider", "com.samsung.android.spay", "com.samsung.android.samsungpay.gear" };
    SKT_DefaultOnAppArray = new String[] { "com.sec.android.app.clockpackage", "com.android.email", "com.android.phone", "com.google.android.dialer", "com.android.calendar", "com.android.mms", "com.skt.prod.dialer", "com.skt.prod.tmessage", "com.kakao.talk", "Uxpp.UC", "com.samsung.android.app.reminder", "com.samsung.android.spay", "com.samsung.android.samsungpay.gear", "com.samsung.android.email.ui", "com.samsung.android.email.provider" };
    VZW_Sprint_DefaultOnAppArray = new String[] { "com.sec.android.app.clockpackage", "com.android.email", "com.android.phone", "com.google.android.dialer", "com.android.mms", "com.samsung.android.email.ui", "com.samsung.android.email.provider" };
    ATT_TMO_DefaultOnAppArray = new String[] { "com.sec.android.app.clockpackage", "com.android.email", "com.android.phone", "com.google.android.dialer", "com.android.calendar", "com.android.mms", "com.samsung.android.email.ui", "com.samsung.android.email.provider" };
    nonSamsungDefaultOnAppArray = new String[] { "com.android.phone", "com.google.android.dialer", "com.android.server.telecom", "com.google.android.deskclock", "com.android.mms", "com.android.calendar", "com.google.android.calendar", "com.google.android.apps.messaging" };
    excludeAppArray = new String[] { "com.samsung.android.app.watchmanager", "com.samsung.android.hostmanager", "com.samsung.android.app.watchmanager", "com.samsung.android.app.watchmanagerstub", "com.sec.keystringscreen", "com.android.development", "com.sec.android.quickmemo", "com.sec.android.app.popupcalculator", "com.sec.android.app.camera", "com.sec.android.gallery3d", "com.google.android.apps.books", "com.google.android.play.games", "com.google.android.videos", "com.google.android.music", "com.google.android.gms", "com.samsung.groupcast", "com.google.android.apps.magazines", "com.android.vending", "com.google.android.apps.docs", "com.dropbox.android", "com.sec.kidsplat.installer", "com.sec.android.app.kidshome", "com.google.android.apps.maps", "com.sec.android.app.music", "com.samsung.android.app.music.chn", "com.sec.android.app.myfiles", "com.sec.penup", "com.vlingo.midas", "com.sec.android.app.samsungapps", "com.samsung.everglades.video", "com.samsung.android.video", "com.samsung.android.videolist", "com.samsung.android.app.pinboard", "com.android.settings", "com.sec.android.app.voicenote", "com.sec.android.app.voicerecorder", "co.kr.pluu.appinfo", "com.sec.android.app.dmb", "com.diotek.diodict4.EDictionary", "com.sec.android.wallet", "com.sec.android.widgetapp.ap.hero.accuweather", "com.sec.android.widgetapp.ap.hero.accuweather", "com.samsung.android.weather", "com.sec.android.daemonapp", "com.sec.android.GeoLookout", "com.samsung.android.gearoplugin", "com.samsung.android.app.memo", "com.kddi.disasterapp", "com.skt.skaf.l001f00006", "com.kddi.market", "com.sec.android.voltesettings", "com.pinsightmedia.vpl.Amazon", "com.sec.knox.containeragent2", "com.sec.knox.shortcutsms", "com.sec.knox.switcher", "com.sec.knox.switchknoxI", "com.sec.knox.switchknoxII", "com.android.deskclock" };
    samsungDeviceLollipopExcludeAppArray = new String[] { "com.sec.knox.bridge" };
    samsungDeviceExcludeAppArray = new String[] { "com.android.email", "com.samsung.android.email.ui", "com.samsung.android.email.sync", "com.samsung.android.email.provider", "com.sec.android.app.clockpackage", "com.sec.android.app.clockpackagechina", "com.android.calendar", "com.samsung.android.calendar", "com.android.mms", "com.samsung.android.messaging", "com.android.phone", "com.android.contacts", "com.samsung.android.contacts", "jp.co.nttdocomo.carriermail" };
    samsungMassDeviceExcludeAppArray = new String[] { "com.sec.android.app.clockpackage", "com.sec.android.app.clockpackagechina", "com.android.calendar", "com.samsung.android.calendar", "com.android.mms", "com.samsung.android.messaging", "com.android.phone", "com.android.contacts", "com.samsung.android.contacts", "jp.co.nttdocomo.carriermail" };
    knoxExcludeAppArray = new String[] { "com.sec.android.app.clockpackage", "com.sec.android.app.clockpackagechina", "com.android.mms", "com.samsung.android.messaging", "com.android.phone", "com.android.contacts", "com.samsung.android.contacts", "com.samsung.contacts" };
    excludeNormalAppArray = new String[] { "com.sec.android.app.clockpackage", "com.sec.android.app.clockpackagechina", "com.android.phone", "com.android.email", "com.samsung.android.email.ui", "com.samsung.android.email.sync", "com.samsung.android.email.provider", "com.android.mms", "com.samsung.android.messaging", "sec_container_1.com.android.email", "com.samsung.android.gear1plugin" };
    excludeWhenScsConnectionAppArray = new String[] { "call", "alarm", "calendar" };
    weatherWidgetPackages = new String[] { "com.sec.android.widgetapp.at.hero.accuweather", "com.sec.android.widgetapp.ap.hero.accuweather", "com.sec.android.widgetapp.at.hero.kweather", "com.sec.android.widgetapp.ap.hero.kweather", "com.samsung.android.weather", "com.sec.android.widgetapp.at.hero.cmaweather", "com.sec.android.widgetapp.ap.hero.cmaweather", "com.sec.android.widgetapp.ap.hero.weathernewsjp" };
    VMSupportSalesCodeArray = new String[] { "ATT", "TMB", "BMC", "BWA", "ESK", "GLW", "KDO", "MCT", "MTA", "RWC", "SPC", "TLS", "VMC", "VTR", "XAC" };
  }
  
  public Utils() {}
  
  public static String getCalendarPackageName(Context paramContext)
  {
    if (CommonUtils.isSamsungDevice()) {
      try
      {
        Log.d(TAG, "getCalendarPackageName");
        String str = FloatingFeatureFactory.get().getString("SEC_FLOATING_FEATURE_CALENDAR_CONFIG_PACKAGE_NAME", "com.android.calendar");
        if ("com.android.calendar".equals(str))
        {
          Log.d(TAG, "getCalendarPackageName 'M OS ");
          return "com.android.calendar";
        }
        try
        {
          Log.d(TAG, "getCalendarPackageName N OS ");
          paramContext.getPackageManager().getPackageInfo(str, 0);
          paramContext = str;
        }
        catch (PackageManager.NameNotFoundException paramContext)
        {
          for (;;)
          {
            paramContext = "com.android.calendar";
          }
        }
        return paramContext;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return "com.android.calendar";
  }
  
  public static String getClockPackageName()
  {
    if (CommonUtils.isSamsungDevice()) {
      try
      {
        String str1 = FloatingFeatureFactory.get().getString("SEC_FLOATING_FEATURE_CLOCK_CONFIG_PACKAGE_NAME", "com.sec.android.app.clockpackage");
        String str2 = CscFeatureFactory.get().getString("CscFeature_Clock_ConfigReplacePackage", "");
        boolean bool = "".equals(str2);
        if (!bool) {
          return str2;
        }
        return str1;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return "com.sec.android.app.clockpackage";
  }
  
  public static Integer[] getKnoxUserId()
  {
    return KNOX_USERID;
  }
  
  public static Integer[] getKnoxUserId(Context paramContext)
  {
    paramContext = ((UserManager)paramContext.getSystemService("user")).getUserProfiles();
    int j = 0;
    if (paramContext != null)
    {
      Integer[] arrayOfInteger = new Integer[paramContext.size() - 1];
      int i = 0;
      Iterator localIterator = paramContext.iterator();
      for (;;)
      {
        paramContext = arrayOfInteger;
        if (!localIterator.hasNext()) {
          return paramContext;
        }
        paramContext = ReflectUtil.callMethod((UserHandle)localIterator.next(), "getIdentifier", new Object[0]);
        int k = j;
        if (paramContext != null) {
          k = ((Integer)paramContext).intValue();
        }
        j = k;
        if (k != 0) {
          try
          {
            arrayOfInteger[i] = Integer.valueOf(k);
            i += 1;
            j = k;
          }
          catch (ArrayIndexOutOfBoundsException paramContext)
          {
            for (;;)
            {
              paramContext.printStackTrace();
            }
          }
        }
      }
    }
    paramContext = new Integer[] { Integer.valueOf(0) };
    return paramContext;
  }
  
  public static List<ResolveInfo> getListOfApplicableApps(Context paramContext)
  {
    Object localObject1 = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject1).addCategory("android.intent.category.LAUNCHER");
    if (paramContext == null)
    {
      paramContext = null;
      return paramContext;
    }
    localObject1 = paramContext.getPackageManager().queryIntentActivities((Intent)localObject1, 0);
    Object localObject2 = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject2).addCategory("android.intent.category.INFO");
    localObject2 = paramContext.getPackageManager().queryIntentActivities((Intent)localObject2, 0).iterator();
    while (((Iterator)localObject2).hasNext()) {
      ((List)localObject1).add((ResolveInfo)((Iterator)localObject2).next());
    }
    localObject2 = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject2).addCategory("android.intent.category.INFO");
    localObject2 = paramContext.getPackageManager().queryIntentServices((Intent)localObject2, 0).iterator();
    for (;;)
    {
      paramContext = (Context)localObject1;
      if (!((Iterator)localObject2).hasNext()) {
        break;
      }
      ((List)localObject1).add((ResolveInfo)((Iterator)localObject2).next());
    }
  }
  
  public static List<ResolveInfo> getListOfApplicableApps(Context paramContext, int paramInt)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN", null);
    localIntent.addCategory("android.intent.category.LAUNCHER");
    return (List)ReflectUtil.callMethod(paramContext.getPackageManager(), "queryIntentActivitiesAsUser", new Object[] { localIntent, Integer.valueOf(0), Integer.valueOf(paramInt) });
  }
  
  public static List<PackageInfo> getListOfApplicableAppsForNormalNotifications(Context paramContext)
  {
    return paramContext.getPackageManager().getInstalledPackages(0);
  }
  
  public static String getLocaleApplicationLabel(Context paramContext, String paramString)
  {
    Object localObject = null;
    Integer localInteger = (Integer)LABEL_MAP.get(paramString);
    paramString = localObject;
    if (localInteger != null) {
      paramString = paramContext.getResources().getString(localInteger.intValue());
    }
    return paramString;
  }
  
  public static String getMessagePackageName(Context paramContext)
  {
    if (CommonUtils.isSamsungDevice()) {
      try
      {
        Log.d(TAG, "getMessagePackageName");
        String str = FloatingFeatureFactory.get().getString("SEC_FLOATING_FEATURE_MESSAGE_CONFIG_PACKAGE_NAME", "com.android.mms");
        if ("com.android.mms".equals(str))
        {
          Log.d(TAG, "getMessagePackageName M OS ");
          return "com.android.mms";
        }
        try
        {
          Log.d(TAG, "getMessagePackageName N OS ");
          paramContext.getPackageManager().getPackageInfo(str, 0);
          paramContext = str;
        }
        catch (PackageManager.NameNotFoundException paramContext)
        {
          for (;;)
          {
            paramContext = "com.android.mms";
          }
        }
        return paramContext;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return "com.android.mms";
  }
  
  public static Integer getPredefinedAppId(String paramString)
  {
    if (paramString.equals("com.sds.mms.ui")) {
      return Integer.valueOf(211);
    }
    return (Integer)PREDEFINED_APP_ID_MAP.get(paramString);
  }
  
  public static String getRealPackageName(Context paramContext, String paramString)
  {
    if ("email".equalsIgnoreCase(paramString))
    {
      if (CommonUtils.isEmailPackageNameChanged(paramContext))
      {
        if (Build.VERSION.SDK_INT >= 23) {
          return "com.samsung.android.email.provider";
        }
        return "com.samsung.android.email.ui";
      }
      return "com.android.email";
    }
    if ("messages".equalsIgnoreCase(paramString))
    {
      paramContext = getMessagePackageName(paramContext);
      REAL_PACKAGENAME_MESSAGES = paramContext;
      return paramContext;
    }
    if ("calendar".equalsIgnoreCase(paramString))
    {
      paramContext = getCalendarPackageName(paramContext);
      REAL_PACKAGENAME_CALENDAR = paramContext;
      return paramContext;
    }
    String str = (String)PACKAGE_MAP.get(paramString);
    paramContext = str;
    if (str == null) {
      paramContext = paramString;
    }
    return paramContext;
  }
  
  public static boolean hasLauncherIntent(Context paramContext, String paramString, int paramInt)
  {
    if (paramInt == 0) {
      try
      {
        if (paramContext.getPackageManager().getLaunchIntentForPackage(paramString) == null) {
          throw new PackageManager.NameNotFoundException();
        }
      }
      catch (PackageManager.NameNotFoundException paramContext) {}
    }
    do
    {
      return false;
      return true;
      Intent localIntent = new Intent("android.intent.action.MAIN", null);
      localIntent.addCategory("android.intent.category.LAUNCHER");
      localIntent.setPackage(paramString);
      paramContext = (List)ReflectUtil.callMethod(paramContext.getPackageManager(), "queryIntentActivitiesAsUser", new Object[] { localIntent, Integer.valueOf(0), Integer.valueOf(paramInt) });
    } while ((paramContext == null) || (paramContext.isEmpty()));
    return true;
  }
  
  public static String hasMasterAppInManifest(Context paramContext, String paramString)
  {
    Object localObject1 = null;
    Object localObject2 = null;
    try
    {
      paramString = paramContext.getPackageManager().getApplicationInfo(paramString, 128);
      paramContext = localObject1;
      if (paramString != null)
      {
        paramString = paramString.metaData;
        paramContext = localObject1;
        if (paramString != null)
        {
          paramContext = localObject1;
          if (paramString.getString("master_app_packagename") != null) {
            paramContext = paramString.getString("master_app_packagename");
          }
        }
      }
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramString = localObject2;
      }
    }
  }
  
  public static int hasNotificationMaxByteInManifest(Context paramContext, String paramString)
  {
    int j = -1;
    Object localObject = null;
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramString, 128);
      int i = j;
      if (paramContext != null)
      {
        paramContext = paramContext.metaData;
        i = j;
        if (paramContext != null)
        {
          i = j;
          if (paramContext.getInt("app_notification_maxbyte") > 0) {
            i = paramContext.getInt("app_notification_maxbyte");
          }
        }
      }
      return i;
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
  
  public static boolean isDefaultOff(String paramString)
  {
    boolean bool2 = false;
    String[] arrayOfString = DefaultOffSalesCodeArray;
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        if (arrayOfString[i].equals(paramString)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static boolean isDefaultOnApp(NotificationApp paramNotificationApp)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    String[] arrayOfString;
    int j;
    int i;
    if (paramNotificationApp != null)
    {
      arrayOfString = defaultOnAppArray;
      j = arrayOfString.length;
      i = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < j)
      {
        if (arrayOfString[i].equalsIgnoreCase(paramNotificationApp.getPackageName())) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static boolean isDefaultOnAppForATT(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    String[] arrayOfString;
    int j;
    int i;
    if (paramString != null)
    {
      if ((paramString.equals(REAL_PACKAGENAME_MESSAGES)) || (paramString.equals(REAL_PACKAGENAME_CALENDAR)) || (paramString.equals(REAL_PACKAGENAME_ALARM))) {
        return true;
      }
      arrayOfString = ATT_TMO_DefaultOnAppArray;
      j = arrayOfString.length;
      i = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < j)
      {
        if (arrayOfString[i].equalsIgnoreCase(paramString)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static boolean isDefaultOnAppForSKT(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    String[] arrayOfString;
    int j;
    int i;
    if (paramString != null)
    {
      if ((paramString.equals(REAL_PACKAGENAME_MESSAGES)) || (paramString.equals(REAL_PACKAGENAME_CALENDAR)) || (paramString.equals(REAL_PACKAGENAME_ALARM))) {
        return true;
      }
      arrayOfString = SKT_DefaultOnAppArray;
      j = arrayOfString.length;
      i = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < j)
      {
        if (arrayOfString[i].equalsIgnoreCase(paramString)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static boolean isDefaultOnAppForVZW(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    String[] arrayOfString;
    int j;
    int i;
    if (paramString != null)
    {
      if ((paramString.equals(REAL_PACKAGENAME_MESSAGES)) || (paramString.equals(REAL_PACKAGENAME_ALARM))) {
        return true;
      }
      arrayOfString = VZW_Sprint_DefaultOnAppArray;
      j = arrayOfString.length;
      i = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < j)
      {
        if (arrayOfString[i].equalsIgnoreCase(paramString)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static boolean isDefaultOnAppFromPkg(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    String[] arrayOfString;
    int j;
    int i;
    if (paramString != null)
    {
      if ((paramString.equals(REAL_PACKAGENAME_MESSAGES)) || (paramString.equals(REAL_PACKAGENAME_CALENDAR)) || (paramString.equals(REAL_PACKAGENAME_ALARM))) {
        return true;
      }
      arrayOfString = defaultOnAppArray;
      j = arrayOfString.length;
      i = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < j)
      {
        if (arrayOfString[i].equalsIgnoreCase(paramString)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static boolean isEligibleForNormalNotification(Context paramContext, String paramString)
  {
    int i = 0;
    boolean bool4 = false;
    boolean bool5 = false;
    boolean bool2 = false;
    if (isPreloadNormalNotificationPackage(paramString)) {
      return false;
    }
    if (paramString.equalsIgnoreCase("com.samsung.android.app.reminder"))
    {
      Log.d(TAG, "Samsung Reminder is not a normal app");
      return false;
    }
    boolean bool1;
    if (isExcludeNormalApp(new NotificationApp(paramString, null, 0, false))) {
      bool1 = false;
    }
    for (;;)
    {
      return bool1;
      PackageManager localPackageManager = paramContext.getPackageManager();
      boolean bool3 = bool5;
      try
      {
        paramContext = localPackageManager.getPackageInfo(getRealPackageName(paramContext, paramString), 4096);
        bool1 = bool2;
        if (paramContext == null) {
          continue;
        }
        bool1 = bool2;
        bool3 = bool5;
        if (paramContext.requestedPermissions == null) {
          continue;
        }
        bool1 = bool2;
        bool3 = bool5;
        if (paramContext.requestedPermissions.length <= 0) {
          continue;
        }
        bool3 = bool5;
        paramContext = paramContext.requestedPermissions;
        bool3 = bool5;
        int j = paramContext.length;
        bool2 = bool4;
        for (;;)
        {
          bool1 = bool2;
          if (i >= j) {
            break;
          }
          bool3 = bool2;
          bool1 = paramContext[i].equalsIgnoreCase("com.samsung.wmanager.ENABLE_NOTIFICATION");
          if (bool1) {
            bool2 = true;
          }
          i += 1;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext.printStackTrace();
        bool1 = bool3;
      }
    }
  }
  
  public static boolean isExcludeApp(NotificationApp paramNotificationApp)
  {
    return isExcludeApp(paramNotificationApp, false);
  }
  
  public static boolean isExcludeApp(NotificationApp paramNotificationApp, boolean paramBoolean)
  {
    int j = 0;
    boolean bool = false;
    String[] arrayOfString = excludeAppArray;
    int k = arrayOfString.length;
    int i = 0;
    String str;
    while (i < k)
    {
      str = arrayOfString[i];
      if (paramNotificationApp.getPackageName().equalsIgnoreCase(str)) {
        return true;
      }
      i += 1;
    }
    if (Build.VERSION.SDK_INT >= 21)
    {
      arrayOfString = samsungDeviceLollipopExcludeAppArray;
      k = arrayOfString.length;
      i = j;
      for (;;)
      {
        if (i >= k) {
          break label101;
        }
        str = arrayOfString[i];
        if (paramNotificationApp.getPackageName().equalsIgnoreCase(str)) {
          break;
        }
        i += 1;
      }
    }
    label101:
    if (isSamsungDevice()) {
      if (!paramBoolean) {
        break label125;
      }
    }
    label125:
    for (paramBoolean = isMassModelEmail();; paramBoolean = CommonUtils.isMassModelforPlugin())
    {
      bool = treatEmail(paramBoolean, paramNotificationApp);
      return bool;
    }
  }
  
  public static boolean isExcludeNormalApp(NotificationApp paramNotificationApp)
  {
    boolean bool2 = false;
    String[] arrayOfString = excludeNormalAppArray;
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        String str = arrayOfString[i];
        if (paramNotificationApp.getPackageName().equalsIgnoreCase(str)) {
          bool1 = true;
        }
      }
      else
      {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static NotificationApp isExistApp(ArrayList<NotificationApp> paramArrayList, String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    int i;
    if (paramString != null)
    {
      localObject1 = localObject2;
      if (paramArrayList != null)
      {
        localObject1 = localObject2;
        if (paramArrayList.size() > 0)
        {
          paramArrayList = (NotificationApp[])paramArrayList.toArray(new NotificationApp[paramArrayList.size()]);
          i = 0;
        }
      }
    }
    for (;;)
    {
      localObject1 = localObject2;
      if (i < paramArrayList.length)
      {
        localObject1 = paramArrayList[i];
        if (!localObject1.getPackageName().equalsIgnoreCase(paramString)) {}
      }
      else
      {
        return localObject1;
      }
      i += 1;
    }
  }
  
  public static NotificationApp isExistApp(ArrayList<NotificationApp> paramArrayList, String paramString, int paramInt)
  {
    Object localObject2 = null;
    int i = paramInt;
    Object localObject1;
    if (!isSamsungDevice())
    {
      if (paramInt > 0) {
        i = paramInt;
      }
    }
    else
    {
      localObject1 = localObject2;
      if (paramString != null)
      {
        localObject1 = localObject2;
        if (paramArrayList != null)
        {
          localObject1 = localObject2;
          if (paramArrayList.size() > 0)
          {
            paramArrayList = (NotificationApp[])paramArrayList.toArray(new NotificationApp[paramArrayList.size()]);
            paramInt = 0;
          }
        }
      }
    }
    for (;;)
    {
      localObject1 = localObject2;
      if (paramInt < paramArrayList.length)
      {
        localObject1 = paramArrayList[paramInt];
        if ((!localObject1.getPackageName().equalsIgnoreCase(paramString)) || (localObject1.getUserId() != i)) {}
      }
      else
      {
        return localObject1;
        i = 0;
        break;
      }
      paramInt += 1;
    }
  }
  
  public static boolean isExistAppId(ArrayList<NotificationApp> paramArrayList, int paramInt)
  {
    if ((paramArrayList != null) && (paramArrayList.size() > 0))
    {
      paramArrayList = (NotificationApp[])paramArrayList.toArray(new NotificationApp[paramArrayList.size()]);
      int i = 0;
      while (i < paramArrayList.length)
      {
        if (paramArrayList[i].getAppId() == paramInt) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public static boolean isIncomingCallApp(NotificationApp paramNotificationApp)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    String[] arrayOfString;
    int j;
    int i;
    if (paramNotificationApp != null)
    {
      arrayOfString = IncomingCallPackages;
      j = arrayOfString.length;
      i = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < j)
      {
        if (arrayOfString[i].equalsIgnoreCase(paramNotificationApp.getPackageName())) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static boolean isInstalledDefaultApp(Context paramContext, String paramString, int paramInt)
  {
    boolean bool2 = true;
    String str = getRealPackageName(paramContext, paramString);
    bool1 = bool2;
    if ((str == null) || (paramInt == 0)) {}
    for (;;)
    {
      try
      {
        paramContext.getPackageManager().getApplicationInfo(str, 128);
        bool1 = bool2;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        Object localObject;
        ResolveInfo localResolveInfo;
        bool1 = false;
        continue;
      }
      bool2 = bool1;
      if (bool1)
      {
        bool2 = bool1;
        if (paramString.equals("voicemail"))
        {
          bool2 = bool1;
          if (!isVMSupported(paramContext)) {
            bool2 = false;
          }
        }
      }
      return bool2;
      localObject = getListOfApplicableApps(paramContext, paramInt);
      if (localObject == null)
      {
        Log.d("Utils", "pkgAppsListforMoreNotification is null. so return. ");
        return false;
      }
      bool2 = false;
      localObject = ((List)localObject).iterator();
      bool1 = bool2;
      if (((Iterator)localObject).hasNext())
      {
        localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
        if (localResolveInfo != null)
        {
          bool1 = localResolveInfo.activityInfo.applicationInfo.packageName.equals(str);
          if (bool1) {
            bool1 = true;
          }
        }
      }
    }
  }
  
  public static boolean isKnoxExcludeApp(NotificationApp paramNotificationApp)
  {
    int j = 0;
    boolean bool2 = false;
    String[] arrayOfString = excludeAppArray;
    int k = arrayOfString.length;
    int i = 0;
    boolean bool1 = bool2;
    String str;
    if (i < k)
    {
      str = arrayOfString[i];
      if (paramNotificationApp.getPackageName().equalsIgnoreCase(str)) {
        bool1 = true;
      }
    }
    else
    {
      bool2 = bool1;
      if (!bool1)
      {
        arrayOfString = knoxExcludeAppArray;
        k = arrayOfString.length;
        i = j;
      }
    }
    for (;;)
    {
      bool2 = bool1;
      if (i < k)
      {
        str = arrayOfString[i];
        if (paramNotificationApp.getPackageName().equalsIgnoreCase(str)) {
          bool2 = true;
        }
      }
      else
      {
        return bool2;
        i += 1;
        break;
      }
      i += 1;
    }
  }
  
  public static boolean isMassModelEmail()
  {
    return (new File("/system/app/SecEmail_ESS").exists()) || (new File("/system/priv-app/SecEmailProvider-mass").exists());
  }
  
  public static boolean isNonSamsungDefaultOnApp(NotificationApp paramNotificationApp)
  {
    int j = 0;
    boolean bool2;
    if ((Build.MANUFACTURER.equalsIgnoreCase("vivo")) && ("com.android.mms.service".equalsIgnoreCase(paramNotificationApp.getPackageName()))) {
      bool2 = true;
    }
    boolean bool1;
    label53:
    do
    {
      return bool2;
      bool2 = false;
      bool1 = bool2;
      if (paramNotificationApp != null)
      {
        arrayOfString = nonSamsungDefaultOnAppArray;
        k = arrayOfString.length;
        i = 0;
        bool1 = bool2;
        if (i < k)
        {
          if (!arrayOfString[i].equalsIgnoreCase(paramNotificationApp.getPackageName())) {
            break;
          }
          bool1 = true;
        }
      }
      bool2 = bool1;
    } while (bool1);
    String[] arrayOfString = defaultOnAppArray;
    int k = arrayOfString.length;
    int i = j;
    for (;;)
    {
      bool2 = bool1;
      if (i >= k) {
        break;
      }
      if (arrayOfString[i].equalsIgnoreCase(paramNotificationApp.getPackageName()))
      {
        return true;
        i += 1;
        break label53;
      }
      i += 1;
    }
  }
  
  public static boolean isPreloadNormalNotificationPackage(String paramString)
  {
    boolean bool2 = false;
    String[] arrayOfString = weatherWidgetPackages;
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        if (arrayOfString[i].equalsIgnoreCase(paramString)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static boolean isSCSExcludeApp(NotificationApp paramNotificationApp)
  {
    boolean bool2 = false;
    String[] arrayOfString = excludeWhenScsConnectionAppArray;
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        String str = arrayOfString[i];
        if (paramNotificationApp.getPackageName().equalsIgnoreCase(str)) {
          bool1 = true;
        }
      }
      else
      {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static boolean isSamsungDevice()
  {
    return CommonUtils.isSamsungDevice();
  }
  
  public static boolean isThirdMmsApp(NotificationApp paramNotificationApp)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    String[] arrayOfString;
    int j;
    int i;
    if (paramNotificationApp != null)
    {
      arrayOfString = thirdMmsAppPackages;
      j = arrayOfString.length;
      i = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < j)
      {
        if (arrayOfString[i].equalsIgnoreCase(paramNotificationApp.getPackageName())) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  private static boolean isVMSupported(Context paramContext)
  {
    int i;
    String[] arrayOfString;
    int j;
    if (Settings.System.getInt(paramContext.getContentResolver(), "GEAR_VOICEMAIL_ENABLED", 0) == 1)
    {
      i = 1;
      paramContext = CommonUtils.getSalesCode();
      if (i != 0)
      {
        arrayOfString = VMSupportSalesCodeArray;
        j = arrayOfString.length;
        i = 0;
      }
    }
    else
    {
      for (;;)
      {
        if (i >= j) {
          break label63;
        }
        if (arrayOfString[i].equals(paramContext))
        {
          return true;
          i = 0;
          break;
        }
        i += 1;
      }
    }
    label63:
    return false;
  }
  
  public static void notifyAppNameUpdated(Context paramContext)
  {
    paramContext.sendBroadcast(new Intent("android.intent.hostmanager.action.NOTIFICATION_APP_NAME_UPDATE"));
  }
  
  public static void notifyGearAppListUpdated(Context paramContext, String paramString1, String paramString2)
  {
    Log.d("Utils", "[wapps] notifyListUpdated called : " + paramString1 + " : " + paramString2);
    Intent localIntent = new Intent("android.intent.hostmanager.action.NOTIFICATION_LIST_UPDATE");
    localIntent.putExtra("package", paramString1);
    localIntent.putExtra("iconImage", paramString2);
    paramContext.sendBroadcast(localIntent);
  }
  
  public static void notifyGearAppMarkStateUpdated(Context paramContext, String paramString, boolean paramBoolean)
  {
    Log.d("Utils", "[wapps] notifyGearAppMarkStateUpdated called : " + paramString + " -> " + paramBoolean);
    if (paramBoolean) {}
    for (Object localObject = "android.intent.action.GEAR_APP_UNBLOCKED_FROM_GEAR";; localObject = "android.intent.action.GEAR_APP_BLOCKED_FROM_GEAR")
    {
      localObject = new Intent((String)localObject);
      ((Intent)localObject).putExtra("package", paramString);
      ((Intent)localObject).putExtra("package_name", paramString);
      paramContext.sendBroadcast((Intent)localObject);
      return;
    }
  }
  
  public static void notifyGearWearOnOffChanged(Context paramContext, int paramInt)
  {
    Intent localIntent = new Intent("android.intent.hostmanager.action.GEAR_WEAR_ONOFF_SETTING_UPDATE");
    localIntent.putExtra("value", paramInt);
    paramContext.sendBroadcast(localIntent);
  }
  
  public static void notifyListItemUpdated(Context paramContext, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (!paramBoolean2)
    {
      Intent localIntent = new Intent("android.intent.hostmanager.action.NOTIFICATION_LIST_UPDATE");
      localIntent.putExtra("APPID", paramInt);
      localIntent.putExtra("ISMARKED", paramBoolean1);
      paramContext.sendBroadcast(localIntent);
    }
  }
  
  public static void notifyListUpdated(Context paramContext)
  {
    paramContext.sendBroadcast(new Intent("android.intent.hostmanager.action.NOTIFICATION_LIST_UPDATE"));
  }
  
  public static void notifySettingChanged(Context paramContext)
  {
    paramContext.sendBroadcast(new Intent("android.intent.hostmanager.action.NOTIFICATION_SETTTING_UPDATE"));
  }
  
  public static int[] parseComponents(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
    {
      paramString = null;
      return paramString;
    }
    Object localObject2 = new ArrayList();
    Object localObject1 = new StringBuffer();
    int i = 0;
    int j = paramString.length();
    if (i < j)
    {
      char c = paramString.charAt(i);
      if (c == '/') {
        if (((StringBuffer)localObject1).length() > 0)
        {
          ((List)localObject2).add(Integer.valueOf(((StringBuffer)localObject1).toString()));
          ((StringBuffer)localObject1).setLength(0);
        }
      }
      for (;;)
      {
        i += 1;
        break;
        ((StringBuffer)localObject1).append(c);
      }
    }
    if (((StringBuffer)localObject1).length() > 0) {
      ((List)localObject2).add(Integer.valueOf(((StringBuffer)localObject1).toString()));
    }
    i = 0;
    localObject1 = new int[((List)localObject2).size()];
    localObject2 = ((List)localObject2).iterator();
    for (;;)
    {
      paramString = (String)localObject1;
      if (!((Iterator)localObject2).hasNext()) {
        break;
      }
      localObject1[i] = ((Integer)((Iterator)localObject2).next()).intValue();
      i += 1;
    }
  }
  
  public static boolean treatEmail(boolean paramBoolean, NotificationApp paramNotificationApp)
  {
    String str;
    if (paramBoolean)
    {
      arrayOfString = samsungMassDeviceExcludeAppArray;
      j = arrayOfString.length;
      i = 0;
      while (i < j)
      {
        str = arrayOfString[i];
        if (paramNotificationApp.getPackageName().equalsIgnoreCase(str)) {
          return true;
        }
        i += 1;
      }
    }
    String[] arrayOfString = samsungDeviceExcludeAppArray;
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label88;
      }
      str = arrayOfString[i];
      if (paramNotificationApp.getPackageName().equalsIgnoreCase(str)) {
        break;
      }
      i += 1;
    }
    label88:
    return false;
  }
}
