package com.cleanmaster.ncmanager.dep;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.text.TextUtils;
import android.text.TextUtils.SimpleStringSplitter;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NotificationServiceUtil
{
  private static final String TAG = "NotificationServiceUtil";
  public static String sACCESSIBILITYCOMPONENT = "";
  
  public NotificationServiceUtil() {}
  
  private static boolean CheckNotifiServiceValid(Context paramContext)
  {
    boolean bool2 = false;
    Object localObject = Settings.Secure.getString(paramContext.getContentResolver(), "enabled_notification_listeners");
    boolean bool1 = bool2;
    int i;
    if (!TextUtils.isEmpty((CharSequence)localObject))
    {
      localObject = ((String)localObject).split(":");
      i = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < localObject.length)
      {
        ComponentName localComponentName = ComponentName.unflattenFromString(localObject[i]);
        if ((localComponentName != null) && (localComponentName.getPackageName().equals(paramContext.getPackageName()))) {
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
  
  public static boolean IsNotificationServiceEnable(Context paramContext)
  {
    if (!IsSystemSupportNotifyMsg()) {
      return false;
    }
    if (Build.VERSION.SDK_INT >= 18) {
      return CheckNotifiServiceValid(paramContext);
    }
    return isAccessibilitySettingsOn(paramContext);
  }
  
  public static boolean IsSystemSupportNotifyMsg()
  {
    return Build.VERSION.SDK_INT >= 14;
  }
  
  public static List<String> getDefaultMsgAlertPkg(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledPackages(0);
    paramContext = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = ((PackageInfo)((Iterator)localObject).next()).packageName;
      if (isDefaultSelectedApp(str)) {
        paramContext.add(str);
      }
    }
    return paramContext;
  }
  
  public static Intent getNotificationServiceSettingIntent()
  {
    if (Build.VERSION.SDK_INT >= 18) {}
    for (Intent localIntent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");; localIntent = new Intent("android.settings.ACCESSIBILITY_SETTINGS"))
    {
      localIntent.addFlags(32768);
      return localIntent;
    }
  }
  
  public static boolean isAccessibilitySettingsOn(Context paramContext)
  {
    String str;
    if (TextUtils.isEmpty(sACCESSIBILITYCOMPONENT)) {
      str = paramContext.getPackageName() + "/com.cleanmaster.boost.acc.service.AccessibilityKillService";
    }
    try
    {
      i = Settings.Secure.getInt(paramContext.getApplicationContext().getContentResolver(), "accessibility_enabled");
    }
    catch (Settings.SettingNotFoundException localSettingNotFoundException1)
    {
      try
      {
        int i;
        Log.v("NotificationServiceUtil", "accessibilityEnabled = " + i);
        for (;;)
        {
          TextUtils.SimpleStringSplitter localSimpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
          if (i == 1)
          {
            Log.v("NotificationServiceUtil", "***ACCESSIBILIY IS ENABLED*** -----------------");
            paramContext = Settings.Secure.getString(paramContext.getApplicationContext().getContentResolver(), "enabled_accessibility_services");
            if (paramContext == null) {
              break label185;
            }
            localSimpleStringSplitter.setString(paramContext);
            do
            {
              if (!localSimpleStringSplitter.hasNext()) {
                break;
              }
              paramContext = localSimpleStringSplitter.next();
              Log.v("NotificationServiceUtil", "-------------- > accessabilityService :: " + paramContext);
            } while (!paramContext.equalsIgnoreCase(str));
            Log.v("NotificationServiceUtil", "We've found the correct setting - accessibility is switched on!");
            return true;
            str = sACCESSIBILITYCOMPONENT;
            break;
          }
          Log.v("NotificationServiceUtil", "***ACCESSIBILIY IS DISABLED***");
          label185:
          return false;
          localSettingNotFoundException1 = localSettingNotFoundException1;
          i = 0;
        }
      }
      catch (Settings.SettingNotFoundException localSettingNotFoundException2)
      {
        for (;;) {}
      }
    }
  }
  
  public static boolean isDefaultSelectedApp(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    String[] arrayOfString;
    int j;
    int i;
    if (!TextUtils.isEmpty(paramString))
    {
      arrayOfString = DefaultSelectApp.DEFAULT_SELECTED_APPS;
      j = arrayOfString.length;
      i = 0;
    }
    for (;;)
    {
      bool1 = bool2;
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
  
  public static void requestNotificationAuthNew(Activity paramActivity, int paramInt, boolean paramBoolean, NotifySettingCallback paramNotifySettingCallback)
  {
    Commons.startActivityForResult(paramActivity, getNotificationServiceSettingIntent(), paramInt);
    if (paramNotifySettingCallback != null) {}
    for (;;)
    {
      new TimerWorkMonitor(paramNotifySettingCallback, 60000, 1000).start();
      return;
      paramNotifySettingCallback = new NotifySettingCallback(paramActivity);
    }
  }
}
