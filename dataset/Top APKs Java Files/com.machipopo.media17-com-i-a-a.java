package com.i.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.Iterator;
import java.util.List;

public class a
{
  private Context a;
  private a b;
  private ComponentName c;
  private boolean d;
  private String e;
  private String f;
  private int g;
  private com.c.a.a h;
  private com.d.a.a i;
  private d.a.a.a j;
  private com.h.a.a k;
  
  public a(Context paramContext)
  {
    this.a = paramContext;
    b();
  }
  
  private String a(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
    while (paramContext.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
      if (localApplicationInfo.packageName.contains("com.freemycard.softworld"))
      {
        this.g = 1;
        return "com.freemycard.softworld";
      }
      if (localApplicationInfo.packageName.contains("com.freewallpaper.softworld"))
      {
        this.g = 2;
        return "com.freewallpaper.softworld";
      }
      if (localApplicationInfo.packageName.contains("tw.com.gamecenter"))
      {
        this.g = 3;
        return "tw.com.gamecenter";
      }
      if (localApplicationInfo.packageName.contains("com.mygame.softworld"))
      {
        this.g = 4;
        return "com.mygame.softworld";
      }
    }
    return "";
  }
  
  private boolean a(Context paramContext, String paramString)
  {
    return this.c.getClassName().equals(paramString);
  }
  
  private void b()
  {
    try
    {
      String str = a(this.a);
      Log.d("FreeMyCardConnect", "Running on " + str);
      Intent localIntent = new Intent();
      localIntent.setPackage(str);
      localIntent.setAction("freemycard.cpimission.register");
      this.b = new a(null);
      this.a.bindService(localIntent, this.b, 1);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void a()
  {
    try
    {
      this.a.unbindService(this.b);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void a(String paramString1, String paramString2)
    throws RemoteException
  {
    Log.d("FreeMyCardConnect", "submitMission");
    try
    {
      if (!a(this.a, "tw.com.MyCard.CustomSDK.Cpi.CpiService"))
      {
        this.d = true;
        this.e = paramString1;
        this.f = paramString2;
        b();
        return;
      }
      switch (this.g)
      {
      case 1: 
        this.h.a(paramString1, paramString2);
        return;
      }
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
      return;
    }
    this.i.a(paramString1, paramString2);
    return;
    this.j.a(paramString1, paramString2);
    return;
    this.k.a(paramString1, paramString2);
    return;
  }
  
  private class a
    implements ServiceConnection
  {
    private a() {}
    
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      Log.d("FreeMyCardConnect", "onServiceConnected");
      a.a(a.this, paramComponentName);
      for (;;)
      {
        try
        {
          switch (a.a(a.this))
          {
          case 1: 
            if (!a.b(a.this)) {
              return;
            }
            a.a(a.this, false);
            a.this.a(a.c(a.this), a.d(a.this));
            return;
          }
        }
        catch (Exception paramComponentName)
        {
          paramComponentName.printStackTrace();
          return;
        }
        a.a(a.this, com.c.a.a.a.a(paramIBinder));
        continue;
        a.a(a.this, com.d.a.a.a.a(paramIBinder));
        continue;
        a.a(a.this, d.a.a.a.a.a(paramIBinder));
        continue;
        a.a(a.this, com.h.a.a.a.a(paramIBinder));
      }
    }
    
    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      a.a(a.this, null);
    }
  }
}
