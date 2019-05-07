package com.jiubang.golauncher.notificationtool;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings.System;
import com.jiubang.golauncher.AppInvoker;
import com.jiubang.golauncher.common.ui.j;
import com.jiubang.golauncher.j.c;
import com.jiubang.golauncher.permission.h;
import com.jiubang.golauncher.setting.activity.DeskSettingExtendActivity;
import com.jiubang.golauncher.t.b;
import com.jiubang.golauncher.thread.GoLauncherThreadExecutorProxy;
import com.jiubang.golauncher.utils.Logcat;
import com.jiubang.golauncher.utils.Machine;
import java.util.Iterator;
import java.util.List;

public class NotificationBroad
  extends BroadcastReceiver
{
  public static boolean a;
  public static int b = -1;
  static final String[] c = { "com.android.gallery3d", "com.google.android.gallery3d" };
  static final String[] d = { "com.android.camera.CameraLauncher", "com.android.camera.CameraEntry", "com.android.hwcamera" };
  
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
    while (i < c.length)
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
    j.a(2131296398, 0);
  }
  
  private boolean b(Context paramContext, int paramInt)
  {
    if (paramInt == d.length) {}
    for (;;)
    {
      return false;
      String str = c[paramInt];
      String[] arrayOfString = d;
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
    if (com.jiubang.ggheart.innerwidgets.goswitchwidget.handler.f.b().a(false))
    {
      paramContext.sendBroadcast(new Intent("com.gau.go.launcherex.s.flashlight.off"));
      Intent localIntent = new Intent("flash_light_change");
      localIntent.putExtra("STATUS", 0);
      paramContext.sendBroadcast(localIntent);
      return;
    }
    try
    {
      b.a();
      j.a(2131296697, 0);
      return;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  private void d(Context paramContext)
  {
    if (com.jiubang.ggheart.innerwidgets.goswitchwidget.handler.f.b().a(true))
    {
      paramContext.sendBroadcast(new Intent("com.gau.go.launcherex.s.flashlight.on"));
      Intent localIntent = new Intent("flash_light_change");
      localIntent.putExtra("STATUS", 1);
      paramContext.sendBroadcast(localIntent);
      return;
    }
    try
    {
      b.a();
      j.a(2131296697, 0);
      return;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public void a(Context paramContext, int paramInt)
  {
    paramContext = new Intent("com.gau.go.launcherex.s.intent.action.DISPATCH");
    paramContext.addCategory("android.intent.category.DEFAULT");
    b = paramInt;
    com.jiubang.golauncher.g.f().invokeApp(paramContext);
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    int i = 2;
    boolean bool1 = false;
    int j = 1;
    boolean bool2 = true;
    String str = paramIntent.getAction();
    if (str.equals("com.gau.go.launcherex.s.notification.wifi"))
    {
      bool1 = Machine.isWifiEnable(paramContext);
      if (bool1)
      {
        paramIntent = com.jiubang.golauncher.advert.competitor.a.j();
        if (bool1) {
          i = 1;
        }
        paramIntent.a(3, i, 1);
      }
    }
    label222:
    do
    {
      try
      {
        com.jiubang.golauncher.common.e.b.f.a(paramContext, "nb_tb_wifi");
        b.a();
        if (!bool1) {}
        for (bool1 = bool2;; bool1 = false)
        {
          Machine.setWifi(paramContext, bool1);
          return;
          GoLauncherThreadExecutorProxy.runOnMainThread(new NotificationBroad.1(this, bool1), 3500L);
          break;
        }
        if (!str.equals("com.gau.go.launcherex.s.notification.data")) {
          break label222;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
      for (;;)
      {
        try
        {
          if (a(paramContext)) {
            break;
          }
          com.jiubang.golauncher.common.e.b.f.a(paramContext, "nb_tb_data");
          b.a();
          bool2 = Machine.isGprsEnable(paramContext);
          if (!bool2) {
            bool1 = true;
          }
          Machine.setGprsEnable(paramContext, bool1);
          if (Machine.IS_SDK_ABOVE_LOLIP)
          {
            paramIntent = Intent.parseUri("#Intent;action=android.intent.action.MAIN;category=com.android.settings.SHORTCUT;launchFlags=0x200000;component=com.android.settings/.Settings%24DataUsageSummaryActivity;end", 0);
            paramIntent.setFlags(268435456);
            paramContext.startActivity(paramIntent);
          }
          paramContext = com.jiubang.golauncher.advert.competitor.a.j();
          if (bool2)
          {
            i = j;
            paramContext.a(3, i, 2);
            return;
          }
        }
        catch (Exception paramContext)
        {
          Logcat.e("zhiping", paramContext.toString());
          return;
        }
        i = 2;
      }
      if (str.equals("com.gau.go.launcherex.s.notification.camera")) {
        try
        {
          c(paramContext);
          com.jiubang.golauncher.common.e.b.f.a(paramContext, "nb_tb_cm");
          b.a();
          b(paramContext);
          return;
        }
        catch (Exception paramContext)
        {
          return;
        }
      }
      if (str.equals("com.gau.go.launcherex.s.notification.netspeedtest")) {
        try
        {
          b.a();
          com.jiubang.golauncher.g.f().invokeApp(new Intent("com.gau.go.launcherex.s.intent.action.ACTION_SHOW_NET_SPEED_TEST"), null, null, 14, new Object[0]);
          return;
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
          return;
        }
      }
      if (str.equals("com.gau.go.launcherex.s.notification.light")) {
        try
        {
          com.jiubang.golauncher.common.e.b.f.a(paramContext, "nb_tb_fl");
          h.a(com.jiubang.golauncher.g.a(), "android.permission.CAMERA", new NotificationBroad.2(this, paramContext));
          return;
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
          return;
        }
      }
      if (str.equals("com.gau.go.launcherex.s.notification.clean")) {
        try
        {
          com.jiubang.golauncher.common.e.b.f.a(paramContext, "nb_tb_bo");
          b.a();
          com.jiubang.golauncher.running.g.a().a(paramContext);
          com.jiubang.golauncher.advert.competitor.a.j().a(3, 3, 4);
          return;
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
          return;
        }
      }
      if (str.equals("com.gau.go.launcherex.s.notification.more")) {
        try
        {
          a(paramContext, 1);
          com.jiubang.golauncher.common.e.b.f.a(paramContext, "nb_tb_mo");
          b.a();
          return;
        }
        catch (Exception paramContext)
        {
          return;
        }
      }
      if (str.equals("com.gau.go.launcherex.s.notification.weather")) {
        try
        {
          a(paramContext, 3);
          b.a();
          com.jiubang.golauncher.common.e.b.f.a(paramContext, "nb_tb_wea");
          return;
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
          return;
        }
      }
      if (str.equals("com.gau.go.launcherex.s.notification.search.button")) {
        try
        {
          a(paramContext, 4);
          b.a();
          com.jiubang.golauncher.common.e.a.a(com.jiubang.golauncher.g.a(), "", "so_wid_mg_cli", 1, "7", "", "", "", "");
          return;
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
          return;
        }
      }
      if (str.equals("com.gau.go.launcherex.s.notification.search.setting")) {
        try
        {
          b.a();
          paramContext = new Intent(com.jiubang.golauncher.g.c(), DeskSettingExtendActivity.class);
          paramContext.addFlags(536870912);
          com.jiubang.golauncher.g.f().invokeApp(paramContext);
          com.jiubang.golauncher.common.e.a.a(com.jiubang.golauncher.g.a(), "", "nb_tb_bar_set", 1, "", "", "", "", "");
          return;
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
          return;
        }
      }
    } while (!str.equals("com.gau.go.launcherex.gowidget.weatherwidget.ACTION_TEMPERATURE_UNIT"));
    try
    {
      i = paramIntent.getIntExtra("temperature_unit", 2);
      paramContext = c.a(paramContext.getApplicationContext());
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
  }
}
