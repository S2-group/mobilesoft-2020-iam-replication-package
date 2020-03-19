package com.neat.app.service;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.IBinder;
import com.garbage.b.f;
import com.neat.app.g.c;
import com.neat.app.g.e;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainService
  extends Service
  implements f
{
  public com.neat.app.f.a a;
  private Set<String> b;
  private Map<String, String> c;
  private List<PackageInfo> d;
  
  public MainService() {}
  
  private String a(String paramString, PackageManager paramPackageManager)
  {
    try
    {
      paramString = paramPackageManager.getApplicationLabel(paramPackageManager.getApplicationInfo(paramString, 128)).toString();
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;) {}
    }
    return "";
  }
  
  public Bitmap a(String paramString)
  {
    return null;
  }
  
  public Map<String, String> a()
  {
    return this.c;
  }
  
  public void a(long paramLong, final Runnable paramRunnable)
  {
    new Handler(getMainLooper()).postDelayed(new Runnable()
    {
      public void run()
      {
        new Thread(paramRunnable).start();
      }
    }, paramLong);
  }
  
  public void a(Runnable paramRunnable)
  {
    new Thread(paramRunnable).start();
  }
  
  public List<String> b()
  {
    return new ArrayList();
  }
  
  public void b(Runnable paramRunnable)
  {
    new Thread(paramRunnable).start();
  }
  
  public void c()
  {
    this.b = new HashSet();
    this.c = new HashMap();
    this.d = new ArrayList();
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          PackageManager localPackageManager = MainService.this.getPackageManager();
          int i = 0;
          List localList = localPackageManager.getInstalledPackages(0);
          while (i < localList.size())
          {
            PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
            MainService.a(MainService.this).add(localPackageInfo.packageName);
            MainService.b(MainService.this).add(localPackageInfo);
            MainService.c(MainService.this).put(localPackageInfo.packageName, MainService.a(MainService.this, localPackageInfo.packageName, localPackageManager));
            i += 1;
          }
          return;
        }
        catch (Exception localException) {}
      }
    }).start();
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    e.a().a(this);
    c.a().a(this);
    com.garbage.b.a.a(this).a(true);
    com.garbage.b.a.a(this).a(this);
    this.a = com.neat.app.f.a.a(this);
    com.neat.app.f.b.a(this);
    c();
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    try
    {
      c.a().b(this);
      com.neat.app.f.b.a(this).a();
      com.neat.app.f.a.a(this).a();
      return;
    }
    catch (Exception localException) {}
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    
    if ((paramIntent != null) && (com.neat.app.g.b.b.equalsIgnoreCase(paramIntent.getAction()))) {
      c.a().a(paramIntent);
    }
    return super.onStartCommand(paramIntent, paramInt1, paramInt2);
  }
}
