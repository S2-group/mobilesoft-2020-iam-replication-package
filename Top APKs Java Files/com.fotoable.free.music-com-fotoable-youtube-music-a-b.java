package com.fotoable.youtube.music.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.fotoable.youtube.music.MusicApplication;
import com.fotoable.youtube.music.util.t;
import java.util.List;
import java.util.Random;

public class b
{
  private static volatile b a;
  private Context b;
  private d c;
  private a d;
  
  private b(Context paramContext)
  {
    try
    {
      this.b = paramContext;
      this.c = d.a(this.b);
      this.d = a.a(this.b);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static b a(Context paramContext)
  {
    if (a == null) {}
    try
    {
      if (a == null) {
        a = new b(paramContext.getApplicationContext());
      }
      return a;
    }
    finally {}
  }
  
  private boolean f()
  {
    try
    {
      List localList = this.b.getPackageManager().getInstalledPackages(0);
      if (localList != null)
      {
        int i = 0;
        while (i < localList.size())
        {
          boolean bool = ((PackageInfo)localList.get(i)).packageName.equals("com.facebook.katana");
          if (bool) {
            return true;
          }
          i += 1;
        }
      }
      return false;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public d a()
  {
    return this.c;
  }
  
  public a b()
  {
    return this.d;
  }
  
  public void c()
  {
    if (!MusicApplication.c().a())
    {
      if ((this.c == null) || (!this.c.c())) {
        break label35;
      }
      this.c.b();
    }
    label35:
    while ((this.d == null) || (!this.d.c())) {
      return;
    }
    this.d.b();
  }
  
  public boolean d()
  {
    if (MusicApplication.c().a()) {}
    while (((this.c == null) || (!this.c.c())) && ((this.d == null) || (!this.d.c()))) {
      return false;
    }
    return true;
  }
  
  public void e()
  {
    int i;
    int j;
    try
    {
      if (MusicApplication.c().a()) {
        return;
      }
      long l = t.a(this.b, com.fotoable.youtube.music.b.m, 0L);
      if (System.currentTimeMillis() - l > 86400000L)
      {
        t.b(this.b, com.fotoable.youtube.music.b.k, 1);
        t.b(this.b, com.fotoable.youtube.music.b.l, 1);
        t.b(this.b, com.fotoable.youtube.music.b.m, System.currentTimeMillis());
      }
      i = t.a(this.b, com.fotoable.youtube.music.b.k, 1);
      j = t.a(this.b, com.fotoable.youtube.music.b.l, 1);
      if (!f()) {
        break label213;
      }
      if (i <= 3)
      {
        com.fotoable.youtube.music.util.b.b("广告 请求Facebook广告");
        this.c.a();
        t.b(this.b, com.fotoable.youtube.music.b.k, i + 1);
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    if ((i > 3) && (j <= 3))
    {
      com.fotoable.youtube.music.util.b.b("广告 请求Admob广告");
      this.d.a();
      t.b(this.b, com.fotoable.youtube.music.b.l, i);
      return;
    }
    if (new Random().nextInt(2) == 0)
    {
      com.fotoable.youtube.music.util.b.b("广告 请求Facebook广告");
      this.c.a();
      return;
    }
    com.fotoable.youtube.music.util.b.b("广告 请求Admob广告");
    this.d.a();
    return;
    label213:
    com.fotoable.youtube.music.util.b.b("广告 请求Admob广告 没有安装facebook");
    this.d.a();
  }
}
