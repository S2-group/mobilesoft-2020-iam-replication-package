package com.lookout.af.b;

import android.content.Context;
import android.content.pm.PackageManager;
import com.lookout.LookoutApplication;
import com.lookout.af.a.i;
import com.lookout.af.l;
import java.util.List;

public class c
{
  private static c a;
  private static final org.a.b b = org.a.c.a(c.class);
  
  protected c() {}
  
  public static c a()
  {
    try
    {
      if (a == null)
      {
        a = new c();
        a().e();
      }
      c localC = a;
      return localC;
    }
    finally {}
  }
  
  public com.lookout.core.b.d a(String paramString)
  {
    long l = System.currentTimeMillis();
    paramString = new com.lookout.core.b.b(paramString);
    com.lookout.core.b.d localD = new com.lookout.core.b.d();
    localD.a(paramString);
    localD.a(l);
    return localD;
  }
  
  public void a(String paramString1, String paramString2)
  {
    g().a(paramString1, paramString2).a();
  }
  
  public void b()
  {
    b.c("Asynchronously initializing device in Spengler");
    i.a().a(new com.lookout.af.b.b.a());
  }
  
  public void c()
  {
    new com.lookout.af.b.a.b.c().a();
    b.c("Asynchronously removing device from Spengler");
    i.a().a(new com.lookout.af.b.b.b());
  }
  
  public void d()
  {
    i().a();
  }
  
  public void e()
  {
    if (a.c().a("~~RESET~~"))
    {
      b.d("In progress RESET found in database, performing reset.");
      new com.lookout.af.b.a.b.a().a();
    }
    if (a.c().a("~~REMOVE~~"))
    {
      b.d("In progress DEVICE REMOVE found in database, performing removal.");
      new com.lookout.af.b.a.b.b().a();
    }
  }
  
  public void f()
  {
    l.a().c(h());
  }
  
  protected com.lookout.af.b.a.c.d g()
  {
    return new com.lookout.af.b.a.c.d();
  }
  
  protected int h()
  {
    return LookoutApplication.getContext().getPackageManager().getInstalledPackages(0).size();
  }
  
  protected com.lookout.af.b.a.c.b i()
  {
    return new com.lookout.af.b.a.c.b();
  }
}
