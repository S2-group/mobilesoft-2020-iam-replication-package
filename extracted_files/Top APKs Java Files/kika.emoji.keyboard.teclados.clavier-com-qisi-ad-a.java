package com.qisi.ad;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.qisi.utils.a.s;
import java.util.List;

public class a
{
  private static a c;
  final String a = "key_to_launcher_day_number";
  final int b = 5;
  private boolean d = false;
  private long e = 0L;
  private final long f = 86400000L;
  
  private a() {}
  
  public static a a()
  {
    try
    {
      if (c == null) {
        c = new a();
      }
      a localA = c;
      return localA;
    }
    finally {}
  }
  
  private void a(Context paramContext, int paramInt)
  {
    if (!b())
    {
      a(paramContext, "c1:return not in launcher");
      return;
    }
    if (a(paramContext))
    {
      a(paramContext, "c2:return has app");
      return;
    }
    if (!b(paramContext))
    {
      a(paramContext, "c3:return in day");
      return;
    }
    a(paramContext, "c4:show");
  }
  
  private void a(Context paramContext, String paramString)
  {
    com.qisi.inputmethod.b.a.c(paramContext, "launcher_ad_test", paramString, "pv", com.qisi.e.a.a.b());
  }
  
  private boolean a(Context paramContext)
  {
    String str = com.kikatech.b.a.a().b("key_to_launcher_packagename", "com.call.flash.theme.emoji.wallpaper.lock.screen.security.smooth.efficiency.color.phone.launcher");
    if (TextUtils.isEmpty(str)) {}
    for (;;)
    {
      return false;
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      int i = 0;
      while (i < paramContext.size())
      {
        if (((PackageInfo)paramContext.get(i)).packageName.equalsIgnoreCase(str)) {
          return true;
        }
        i += 1;
      }
    }
  }
  
  private boolean b()
  {
    return this.d;
  }
  
  private boolean b(Context paramContext)
  {
    int i = 5;
    String str = com.kikatech.b.a.a().b("key_to_launcher_day_number", String.valueOf(5));
    try
    {
      int j = Integer.parseInt(str);
      i = j;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      long l;
      for (;;) {}
    }
    l = s.b(paramContext, "pref_launcher_notification_last_day", 0L);
    return i * 86400000L + l <= System.currentTimeMillis();
  }
  
  public void a(long paramLong, Context paramContext)
  {
    a(paramContext, "a1:low memory");
    this.e = paramLong;
    a(paramContext, false);
  }
  
  public void a(Context paramContext, boolean paramBoolean)
  {
    if (!this.d) {
      a(paramContext, "b1:return not in launcher");
    }
    for (;;)
    {
      return;
      if (("2".equals(com.kikatech.b.a.a().b("key_to_launcher", "0"))) || (paramBoolean))
      {
        if (this.e > System.currentTimeMillis() - 60000L)
        {
          a(paramContext, "b3:launcher is 1 m start");
          a(paramContext, 2);
          return;
        }
        a(paramContext, "b4:launcher time out");
        try
        {
          ActivityManager localActivityManager = (ActivityManager)com.qisi.application.a.a().getSystemService("activity");
          ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
          localActivityManager.getMemoryInfo(localMemoryInfo);
          if (localMemoryInfo.lowMemory)
          {
            a(paramContext, "b4:launcher is low memory start");
            a(paramContext, 2);
            return;
          }
        }
        catch (Exception paramContext)
        {
          return;
        }
      }
    }
    a(paramContext, "b2:application is low memory start");
    a(paramContext, 2);
  }
  
  public void a(Intent paramIntent, Context paramContext)
  {
    if (((!paramIntent.getAction().equals("android.intent.action.PACKAGE_ADDED")) && (!paramIntent.getAction().equals("android.intent.action.PACKAGE_REMOVED"))) || (!"1".equals(com.kikatech.b.a.a().b("key_to_launcher", "0")))) {
      return;
    }
    a(paramContext, 1);
  }
  
  public void a(boolean paramBoolean, Context paramContext)
  {
    this.d = paramBoolean;
    if (paramBoolean)
    {
      a(paramContext, "a2:in launcher");
      a(paramContext, true);
    }
  }
}
