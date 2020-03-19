package com.lbe.doubleagent;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.os.Process;
import com.lbe.doubleagent.client.b;
import com.lbe.doubleagent.service.ae;
import com.lbe.doubleagent.utility.PackageManagerWrapper;
import java.util.Iterator;
import java.util.List;

public class q
{
  private static final String a = "multidroid";
  private static int b;
  private static b c;
  private static Application d;
  private static ae e;
  private static SharedPreferences f;
  
  private q() {}
  
  public static ae a()
  {
    return e;
  }
  
  public static void a(Application paramApplication)
  {
    d = paramApplication;
    cd.q = e.a();
    cd.k = e.b();
    cd.E = e.c();
    cd.Q = e.h();
    cd.V = e.i();
    cd.W = e.j();
    f = paramApplication.getSharedPreferences("multidroid", 0);
  }
  
  public static void a(Context paramContext)
  {
    int i = e(d);
    b = i;
    if (ch.c(i))
    {
      c = b.a(b);
      return;
    }
    if (ch.d(b))
    {
      c = b.a(b);
      return;
    }
    new PackageManagerWrapper(paramContext, -1).getInstalledPackages(8192);
    ch.a();
  }
  
  public static void a(ae paramAe)
  {
    e = paramAe;
  }
  
  public static SharedPreferences b()
  {
    return f;
  }
  
  public static void b(Context paramContext)
  {
    if (c != null)
    {
      if (ch.d(b)) {
        c.b(paramContext);
      }
    }
    else {
      return;
    }
    c.a(paramContext, -1, null, null);
  }
  
  public static int c()
  {
    return b;
  }
  
  public static void c(Context paramContext)
  {
    if ((c != null) && (!c.a(paramContext, -1, null, null))) {
      System.exit(0);
    }
  }
  
  public static Application d()
  {
    return d;
  }
  
  public static boolean d(Context paramContext)
  {
    if (Build.VERSION.SDK_INT <= 16) {
      return true;
    }
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    ((ActivityManager)paramContext.getSystemService("activity")).getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.availMem / 1024L / 1024L <= 1300L;
  }
  
  private static int e(Context paramContext)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if (localRunningAppProcessInfo.pid == Process.myPid())
      {
        paramContext = localRunningAppProcessInfo.processName.split(":");
        if ((paramContext != null) && (paramContext.length == 2) && (paramContext[1].length() > 1) && (paramContext[1].charAt(0) == 'P')) {
          try
          {
            int i = Integer.parseInt(paramContext[1].substring(1));
            return i;
          }
          catch (Exception paramContext)
          {
            return -1;
          }
        }
        return -1;
      }
    }
    System.exit(0);
    return -1;
  }
  
  public static class a
    implements ae
  {
    public a() {}
    
    public PendingIntent a(int paramInt1, String paramString1, int paramInt2, String paramString2, PendingIntent paramPendingIntent)
    {
      return null;
    }
    
    public String a()
    {
      return null;
    }
    
    public String b()
    {
      return null;
    }
    
    public boolean c()
    {
      return false;
    }
    
    public boolean d()
    {
      return false;
    }
    
    public boolean e()
    {
      return false;
    }
    
    public int f()
    {
      return 0;
    }
    
    public int g()
    {
      return 0;
    }
    
    public String h()
    {
      return null;
    }
    
    public String i()
    {
      return null;
    }
    
    public String j()
    {
      return null;
    }
  }
}
