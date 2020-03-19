package com.tencent.tp;

import android.content.Context;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.List;

public class h
  implements d
{
  private static volatile h b;
  private d a;
  
  private h()
  {
    try
    {
      Class localClass1 = c.a("com.tencent.up_tp.SMI");
      if (localClass1 != null)
      {
        this.a = ((d)localClass1.newInstance());
        return;
      }
      throw new Exception("com.tencent.up_tp.SMI NOT found");
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        Class localClass2 = c.a("com.tencent.tp.SMI");
        if (localClass2 != null)
        {
          this.a = ((d)localClass2.newInstance());
          return;
        }
      }
      catch (Throwable localThrowable2)
      {
        this.a = null;
        return;
      }
      throw new Exception("com.tencent.tp.SMI NOT found");
    }
  }
  
  public static h a()
  {
    if (b == null) {}
    try
    {
      if (b == null) {
        b = new h();
      }
      return b;
    }
    finally {}
  }
  
  public String a(Context paramContext)
  {
    if (this.a != null) {
      return this.a.a(paramContext);
    }
    return "NotImp";
  }
  
  public String b(Context paramContext)
  {
    if (this.a != null) {
      return this.a.b(paramContext);
    }
    return "NotImp";
  }
  
  public String c(Context paramContext)
  {
    if (this.a != null) {
      return this.a.c(paramContext);
    }
    return "NotImp";
  }
  
  public String d(Context paramContext)
  {
    if (this.a != null) {
      return this.a.d(paramContext);
    }
    return "NotImp";
  }
  
  public String e(Context paramContext)
  {
    if (this.a != null) {
      return this.a.e(paramContext);
    }
    return "NotImp";
  }
  
  public String f(Context paramContext)
  {
    if (this.a != null) {
      return this.a.f(paramContext);
    }
    return "NotImp";
  }
  
  public String g(Context paramContext)
  {
    if (this.a != null) {
      return this.a.g(paramContext);
    }
    return "NotImp";
  }
  
  public String h(Context paramContext)
  {
    if (this.a != null) {
      return this.a.h(paramContext);
    }
    return "NotImp";
  }
  
  public String i(Context paramContext)
  {
    if (this.a != null) {
      return this.a.i(paramContext);
    }
    return "NotImp";
  }
  
  public String j(Context paramContext)
  {
    if (this.a != null) {
      return this.a.j(paramContext);
    }
    return "NotImp";
  }
  
  public String k(Context paramContext)
  {
    if (this.a != null) {
      return this.a.k(paramContext);
    }
    return "NotImp";
  }
  
  public String l(Context paramContext)
  {
    if (this.a != null) {
      return this.a.l(paramContext);
    }
    return "NotImp";
  }
  
  public List m(Context paramContext)
  {
    if (this.a != null) {
      return this.a.m(paramContext);
    }
    String str = TssSdk.a("IsEnabled_0:apk");
    if ((str != null) && (str.compareTo("1") == 0))
    {
      paramContext = paramContext.getPackageManager();
      if (paramContext == null) {
        return new ArrayList();
      }
      return paramContext.getInstalledPackages(0);
    }
    return new ArrayList();
  }
  
  public List n(Context paramContext)
  {
    if (this.a != null) {
      return this.a.n(paramContext);
    }
    return new ArrayList();
  }
}
