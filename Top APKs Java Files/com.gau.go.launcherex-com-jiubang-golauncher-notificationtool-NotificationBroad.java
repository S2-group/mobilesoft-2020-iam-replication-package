package com.jiubang.golauncher.notificationtool;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Log;
import com.jiubang.golauncher.AppInvoker;
import com.jiubang.golauncher.ay;
import com.jiubang.golauncher.common.statistics.b.g;
import com.jiubang.golauncher.common.ui.u;
import com.jiubang.golauncher.g.e;
import com.jiubang.golauncher.notificationad.NotificationAdDialog;
import com.jiubang.golauncher.p.b;
import com.jiubang.golauncher.running.j;
import com.jiubang.golauncher.setting.activity.DeskSettingExtendActivity;
import com.jiubang.golauncher.utils.Machine;
import java.util.Iterator;
import java.util.List;

public class NotificationBroad
  extends BroadcastReceiver
{
  public static boolean a;
  static l b;
  public static int c = -1;
  static final String[] d = { "com.android.gallery3d", "com.google.android.gallery3d" };
  static final String[] e = { "com.android.camera.CameraLauncher", "com.android.camera.CameraEntry", "com.android.hwcamera" };
  private static boolean f = false;
  
  public NotificationBroad() {}
  
  public static boolean a(Context paramContext)
  {
    return Settings.System.getInt(paramContext.getContentResolver(), "airplane_mode_on", 0) == 1;
  }
  
  private boolean a(PackageManager paramPackageManager, PackageInfo paramPackageInfo, String paramString, Context paramContext)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramPackageInfo.applicationInfo.flags & 0x1) > 0)
    {
      bool1 = bool2;
      if (paramPackageInfo.packageName.toLowerCase().contains(paramString))
      {
        bool1 = bool2;
        if (paramPackageInfo.packageName.toLowerCase().contains("widget")) {}
      }
    }
    try
    {
      paramPackageManager = paramPackageManager.getLaunchIntentForPackage(paramPackageInfo.packageName);
      paramPackageManager.setFlags(268435456);
      paramContext.startActivity(paramPackageManager);
      bool1 = true;
      return bool1;
    }
    catch (Exception paramPackageManager) {}
    return false;
  }
  
  private void b(Context paramContext)
  {
    int i = 0;
    while (i < d.length)
    {
      if (b(paramContext, i)) {
        return;
      }
      i += 1;
    }
    PackageManager localPackageManager = paramContext.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
    while (localIterator.hasNext()) {
      if (a(localPackageManager, (PackageInfo)localIterator.next(), "camera", paramContext)) {
        return;
      }
    }
    u.a(2131165724, 0);
  }
  
  private boolean b(Context paramContext, int paramInt)
  {
    if (paramInt == e.length) {}
    for (;;)
    {
      return false;
      String str = d[paramInt];
      String[] arrayOfString = e;
      int i = arrayOfString.length;
      paramInt = 0;
      while (paramInt < i)
      {
        ComponentName localComponentName = new ComponentName(str, arrayOfString[paramInt]);
        Intent localIntent = new Intent();
        localIntent.setFlags(268435456);
        localIntent.setComponent(localComponentName);
        try
        {
          paramContext.startActivity(localIntent);
          return true;
        }
        catch (Exception localException)
        {
          paramInt += 1;
        }
      }
    }
  }
  
  private void c(Context paramContext)
  {
    if (f)
    {
      b.d();
      f = false;
      paramContext.sendBroadcast(new Intent("com.gau.go.launcherex.flashlight.off"));
    }
  }
  
  private void d(Context paramContext)
  {
    if (!f)
    {
      paramContext.sendBroadcast(new Intent("com.gau.go.launcherex.flashlight.on"));
      b.c();
      f = true;
    }
  }
  
  public void a(Context paramContext, int paramInt)
  {
    paramContext = new Intent("com.jiubang.intent.action.DISPATCH");
    paramContext.addCategory("android.intent.category.DEFAULT");
    c = paramInt;
    ay.g().invokeApp(paramContext);
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    boolean bool2 = true;
    boolean bool1 = true;
    String str = paramIntent.getAction();
    if (str.equals("com.gau.go.launcherex.notification.wifi")) {
      com.jiubang.golauncher.notificationtoolad.a.a(paramContext).a();
    }
    label158:
    do
    {
      try
      {
        g.a(paramContext, "nb_tb_wifi");
        b.a();
        if (!Machine.isWifiEnable(paramContext)) {}
        for (;;)
        {
          Machine.setWifi(paramContext, bool1);
          return;
          bool1 = false;
        }
        if (!str.equals("com.gau.go.launcherex.notification.data")) {
          break label158;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
      com.jiubang.golauncher.notificationtoolad.a.a(paramContext).a();
      for (;;)
      {
        try
        {
          if (a(paramContext)) {
            break;
          }
          g.a(paramContext, "nb_tb_data");
          b.a();
          if (!Machine.isGprsEnable(paramContext))
          {
            bool1 = bool2;
            Machine.setGprsEnable(paramContext, bool1);
            if (!Machine.IS_SDK_ABOVE_LOLIP) {
              break;
            }
            paramIntent = Intent.parseUri("#Intent;action=android.intent.action.MAIN;category=com.android.settings.SHORTCUT;launchFlags=0x200000;component=com.android.settings/.Settings%24DataUsageSummaryActivity;end", 0);
            paramIntent.setFlags(268435456);
            paramContext.startActivity(paramIntent);
            return;
          }
        }
        catch (Exception paramContext)
        {
          Log.e("zhiping", paramContext.toString());
          return;
        }
        bool1 = false;
      }
      if (str.equals("com.gau.go.launcherex.notification.camera")) {
        try
        {
          c(paramContext);
          g.a(paramContext, "nb_tb_cm");
          b.a();
          b(paramContext);
          return;
        }
        catch (Exception paramContext)
        {
          return;
        }
      }
      if (str.equals("com.gau.go.launcherex.notification.netspeedtest")) {
        try
        {
          b.a();
          ay.g().invokeApp(new Intent("com.jiubang.intent.action.ACTION_SHOW_NET_SPEED_TEST"), null, null, 14, new Object[0]);
          return;
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
          return;
        }
      }
      if (str.equals("com.gau.go.launcherex.notification.light")) {}
      try
      {
        g.a(paramContext, "nb_tb_fl");
        if (b == null) {
          b = new l(paramContext);
        }
        if (!b.a())
        {
          b.a();
          u.a(2131165906, 0);
          return;
        }
        if (f)
        {
          c(paramContext);
          com.jiubang.golauncher.notificationtoolad.a.a(paramContext).a();
          b.a();
          return;
        }
        if (!b.a())
        {
          b.a();
          u.a(2131165906, 0);
          return;
        }
        d(paramContext);
        return;
      }
      catch (Exception paramContext) {}
      if (str.equals("com.gau.go.launcherex.notification.clean")) {
        try
        {
          g.a(paramContext, "nb_tb_bo");
          b.a();
          j.a().a(paramContext);
          return;
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
          return;
        }
      }
      if (str.equals("com.gau.go.launcherex.notification.more")) {
        try
        {
          a(paramContext, 1);
          g.a(paramContext, "nb_tb_mo");
          b.a();
          return;
        }
        catch (Exception paramContext)
        {
          return;
        }
      }
      if (str.equals("com.gau.go.launcherex.notification.weather")) {
        try
        {
          a(paramContext, 3);
          b.a();
          g.a(paramContext, "nb_tb_wea");
          return;
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
          return;
        }
      }
      if (str.equals("com.gau.go.launcherex.notification.search.button")) {
        try
        {
          a(paramContext, 4);
          b.a();
          com.jiubang.golauncher.common.statistics.a.a(ay.a(), "", "so_wid_mg_cli", 1, "7", "", "", "", "");
          return;
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
          return;
        }
      }
      if (str.equals("com.gau.go.launcherex.notification.search.setting")) {
        try
        {
          b.a();
          paramContext = new Intent(ay.c(), DeskSettingExtendActivity.class);
          paramContext.addFlags(536870912);
          ay.g().invokeApp(paramContext);
          com.jiubang.golauncher.common.statistics.a.a(ay.a(), "", "nb_tb_bar_set", 1, "", "", "", "", "");
          return;
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
          return;
        }
      }
      if (str.equals("com.gau.go.launcherex.gowidget.weatherwidget.ACTION_TEMPERATURE_UNIT"))
      {
        try
        {
          i = paramIntent.getIntExtra("temperature_unit", 2);
          paramContext = e.a(paramContext.getApplicationContext());
          if (i == 2)
          {
            paramContext.a(1);
            return;
          }
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
          return;
        }
        paramContext.a(0);
        return;
      }
      if (str.equals("com.jiubang.intent.action.ACTION_NOTIFICATION_AD_NEW_USER_CLICK"))
      {
        i = paramIntent.getIntExtra("moduleId", 0);
        com.jiubang.golauncher.common.statistics.a.a(paramContext, i + "", "noti_cli", 1, "", "", "", "", "");
        new NotificationAdDialog(paramContext, i).a();
        return;
      }
      if (str.equals("com.jiubang.intent.action.ACTION_NOTIFICATION_GP_NEW_USER_CLICK"))
      {
        str = paramIntent.getStringExtra("url");
        i = paramIntent.getIntExtra("moduleId", 0);
        if (!TextUtils.isEmpty(str)) {
          com.jiubang.golauncher.utils.a.a(paramContext, str, str);
        }
        com.jiubang.golauncher.common.statistics.a.a(paramContext, i + "", "noti_cli", 1, "", "", "", "", "");
        return;
      }
    } while (!str.equals("com.jiubang.intent.action.ACTION_NOTIFICATION_HOT_WORD_NEW_USER_CLICK"));
    com.jiubang.golauncher.utils.a.e(paramContext, paramIntent.getStringExtra("url"));
    int i = paramIntent.getIntExtra("moduleId", 0);
    com.jiubang.golauncher.common.statistics.a.a(paramContext, i + "", "noti_cli", 1, "", "", "", "", "");
    return;
  }
}
