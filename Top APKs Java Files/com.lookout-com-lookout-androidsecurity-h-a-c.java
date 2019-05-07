package com.lookout.androidsecurity.h.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import java.util.List;

public class c
  extends a
{
  private static final c a = new c();
  private volatile d b = d.a;
  private volatile long c = 0L;
  private volatile int d = 0;
  private volatile int e = com.lookout.androidsecurity.a.a().b().getPackageManager().getInstalledPackages(0).size();
  private volatile boolean f;
  
  protected c() {}
  
  public static c i()
  {
    return a;
  }
  
  public void a(int paramInt)
  {
    this.d = paramInt;
  }
  
  public void a(long paramLong)
  {
    this.c = paramLong;
    com.lookout.androidsecurity.a.a().b().getSharedPreferences("timestamps", 0).edit().putLong("last_scan", paramLong).commit();
  }
  
  public void a(d paramD)
  {
    this.b = paramD;
  }
  
  public void a(boolean paramBoolean)
  {
    this.f = paramBoolean;
  }
  
  public void b(int paramInt)
  {
    this.e = paramInt;
  }
  
  public void c()
  {
    super.c();
    this.b = d.a;
  }
  
  public int j()
  {
    return this.d;
  }
  
  public int k()
  {
    return this.e;
  }
  
  public long l()
  {
    if (this.c == 0L) {
      a(com.lookout.androidsecurity.a.a().b().getSharedPreferences("timestamps", 0).getLong("last_scan", 0L));
    }
    return this.c;
  }
  
  public d m()
  {
    return this.b;
  }
}
