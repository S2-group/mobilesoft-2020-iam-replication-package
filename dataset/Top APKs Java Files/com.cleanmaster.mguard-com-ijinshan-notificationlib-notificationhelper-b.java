package com.ijinshan.notificationlib.notificationhelper;

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
import com.ijinshan.notificationlib.notificationhelper.ui.SocialMaskGuideActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class b
{
  public static String a = "";
  
  public static Intent a()
  {
    if (Build.VERSION.SDK_INT >= 18) {}
    for (Intent localIntent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");; localIntent = new Intent("android.settings.ACCESSIBILITY_SETTINGS"))
    {
      localIntent.addFlags(32768);
      return localIntent;
    }
  }
  
  public static void a(Activity paramActivity, int paramInt, boolean paramBoolean, c paramC)
  {
    android.support.v4.a.d.a(paramActivity, a(), paramInt);
    SocialMaskGuideActivity.a(paramActivity, paramBoolean);
    new d(paramC).a();
  }
  
  public static boolean a(Context paramContext)
  {
    int i;
    if (Build.VERSION.SDK_INT >= 14)
    {
      i = 1;
      if (i != 0) {
        break label21;
      }
    }
    for (;;)
    {
      return false;
      i = 0;
      break;
      label21:
      if (Build.VERSION.SDK_INT < 18) {
        break label95;
      }
      Object localObject = Settings.Secure.getString(paramContext.getContentResolver(), "enabled_notification_listeners");
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        localObject = ((String)localObject).split(":");
        i = 0;
        while (i < localObject.length)
        {
          ComponentName localComponentName = ComponentName.unflattenFromString(localObject[i]);
          if ((localComponentName != null) && (localComponentName.getPackageName().equals(paramContext.getPackageName()))) {
            return true;
          }
          i += 1;
        }
      }
    }
    label95:
    return c(paramContext);
  }
  
  public static boolean a(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    String[] arrayOfString;
    int i;
    if (!TextUtils.isEmpty(paramString))
    {
      arrayOfString = a.a;
      i = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < 222)
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
  
  public static List<String> b(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledPackages(0);
    paramContext = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = ((PackageInfo)((Iterator)localObject).next()).packageName;
      if (a(str)) {
        paramContext.add(str);
      }
    }
    return paramContext;
  }
  
  private static boolean c(Context paramContext)
  {
    String str;
    if (TextUtils.isEmpty(a)) {
      str = paramContext.getPackageName() + "/com.cleanmaster.boost.acc.service.AccessibilityKillService";
    }
    for (;;)
    {
      try
      {
        i = Settings.Secure.getInt(paramContext.getApplicationContext().getContentResolver(), "accessibility_enabled");
      }
      catch (Settings.SettingNotFoundException localSettingNotFoundException1)
      {
        TextUtils.SimpleStringSplitter localSimpleStringSplitter;
        int i = 0;
      }
      try
      {
        Log.v("NotificationServiceUtil", "accessibilityEnabled = " + i);
        localSimpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
        if (i != 1) {
          break;
        }
        Log.v("NotificationServiceUtil", "***ACCESSIBILIY IS ENABLED*** -----------------");
        paramContext = Settings.Secure.getString(paramContext.getApplicationContext().getContentResolver(), "enabled_accessibility_services");
        if (paramContext == null) {
          break label202;
        }
        localSimpleStringSplitter.setString(paramContext);
        if (!localSimpleStringSplitter.hasNext()) {
          break label202;
        }
        paramContext = localSimpleStringSplitter.next();
        Log.v("NotificationServiceUtil", "-------------- > accessabilityService :: " + paramContext);
        if (!paramContext.equalsIgnoreCase(str)) {
          continue;
        }
        Log.v("NotificationServiceUtil", "We've found the correct setting - accessibility is switched on!");
        return true;
      }
      catch (Settings.SettingNotFoundException localSettingNotFoundException2)
      {
        for (;;) {}
      }
      str = a;
      continue;
      new StringBuilder("Error finding setting, default accessibility to not found: ").append(localSettingNotFoundException1.getMessage());
    }
    Log.v("NotificationServiceUtil", "***ACCESSIBILIY IS DISABLED***");
    label202:
    return false;
  }
}
