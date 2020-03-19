package com.tapjoy.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class fs
{
  private static volatile fs c;
  private static final String d;
  private static final Lock e;
  fs.a a = new fs.a(this);
  private String[] f = null;
  private long g = 0L;
  
  static
  {
    if (!fs.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      b = bool;
      d = ge.a(fs.class);
      e = new ReentrantLock();
      return;
    }
  }
  
  private fs() {}
  
  public static fs a()
  {
    if (c == null) {}
    try
    {
      e.lock();
      if (c == null) {
        c = new fs();
      }
      return c;
    }
    finally
    {
      e.unlock();
    }
  }
  
  private String[] a(Context paramContext)
  {
    if ((this.f != null) && (TimeUnit.SECONDS.convert(System.nanoTime() - this.g, TimeUnit.NANOSECONDS) < 60L)) {
      return this.f;
    }
    this.g = System.nanoTime();
    Object localObject = paramContext.getPackageManager().getInstalledApplications(0);
    paramContext = new ArrayList(((List)localObject).size());
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      if ((!localApplicationInfo.sourceDir.startsWith("/system/app")) && (!localApplicationInfo.sourceDir.startsWith("/system/priv-app"))) {
        paramContext.add(localApplicationInfo.sourceDir);
      }
    }
    paramContext.add("/system/app");
    paramContext.add("/system/priv-app");
    new StringBuilder("findAPKPaths found : ").append(paramContext.size());
    this.f = ((String[])paramContext.toArray(new String[paramContext.size()]));
    return this.f;
  }
  
  public final int a(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((!b) && (paramContext == null)) {
      throw new AssertionError();
    }
    int j = 0;
    int i = 0;
    try
    {
      if (this.a.a)
      {
        this.a.c = a(paramContext);
        i = this.a.a(paramInt2, paramInt3, this.a.c, paramInt1);
      }
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
    }
    catch (Throwable paramContext)
    {
      paramContext = paramContext;
      i = j;
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
    }
    finally
    {
      paramContext = finally;
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
      throw paramContext;
    }
    return i;
  }
  
  public final int a(String paramString1, String paramString2)
  {
    try
    {
      if ((this.a.a) && (paramString2 != null))
      {
        i = this.a.b(paramString1, paramString2);
        if (!Thread.interrupted()) {
          return i;
        }
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString1) {}finally
    {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
    }
    int i = -1;
    return i;
  }
  
  public final String a(String paramString)
  {
    try
    {
      if ((this.a.a) && (paramString != null))
      {
        paramString = this.a.a(paramString);
        if (!Thread.interrupted()) {
          return paramString;
        }
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString) {}finally
    {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public final String[] a(String[] paramArrayOfString)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      if (this.a.a) {}
      for (String str = " available ";; str = "not available ")
      {
        localStringBuilder.append(str).append(" Found ").append(this.a.b);
        if ((!this.a.a) || (paramArrayOfString == null)) {
          break;
        }
        paramArrayOfString = this.a.a(paramArrayOfString);
        if (!Thread.interrupted()) {
          return paramArrayOfString;
        }
        throw new InterruptedException();
      }
    }
    catch (Throwable paramArrayOfString) {}finally
    {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
    }
    paramArrayOfString = null;
    return paramArrayOfString;
  }
  
  public final int b()
  {
    try
    {
      if (this.a.a)
      {
        int i = this.a.d();
        return i;
      }
    }
    catch (Throwable localThrowable) {}
    return -1;
  }
  
  public final String b(String paramString)
  {
    try
    {
      if ((this.a.a) && (paramString != null))
      {
        paramString = this.a.c(paramString);
        if (!Thread.interrupted()) {
          return paramString;
        }
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString) {}finally
    {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public final String b(String paramString1, String paramString2)
  {
    try
    {
      if ((this.a.a) && (paramString2 != null) && (paramString1 != null) && (paramString2.length() > 0) && (!paramString1.isEmpty()))
      {
        paramString1 = this.a.a(paramString1, paramString2);
        if (!Thread.interrupted()) {
          return paramString1;
        }
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString1) {}finally
    {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
    }
    paramString1 = null;
    return paramString1;
  }
  
  public final int c()
  {
    try
    {
      if (this.a.a)
      {
        int i = this.a.e();
        return i;
      }
    }
    catch (Throwable localThrowable) {}
    return -1;
  }
  
  public final String c(String paramString)
  {
    try
    {
      if (this.a.a)
      {
        paramString = this.a.f(paramString);
        if (!Thread.interrupted()) {
          return paramString;
        }
        throw new InterruptedException();
      }
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString)
    {
      paramString = paramString;
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
    }
    finally
    {
      paramString = finally;
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
      throw paramString;
    }
    paramString = null;
    return paramString;
  }
  
  public final String d()
  {
    try
    {
      if (this.a.a)
      {
        String str1 = this.a.a(32);
        if (!Thread.interrupted()) {
          return ???;
        }
        throw new InterruptedException();
      }
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable = localThrowable;
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
    }
    finally
    {
      localObject = finally;
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
      throw localObject;
    }
    String str2 = null;
    return str2;
  }
  
  public final String d(String paramString)
  {
    try
    {
      if ((this.a.a) && (paramString != null))
      {
        paramString = this.a.d(paramString);
        if (!Thread.interrupted()) {
          return paramString;
        }
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString) {}finally
    {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public final String e(String paramString)
  {
    try
    {
      if ((this.a.a) && (paramString != null))
      {
        paramString = this.a.e(paramString);
        if (!Thread.interrupted()) {
          return paramString;
        }
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString) {}finally
    {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public final String[] e()
  {
    try
    {
      if (this.a.a)
      {
        String[] arrayOfString1 = this.a.a();
        if (!Thread.interrupted()) {
          return ???;
        }
        throw new InterruptedException();
      }
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable = localThrowable;
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
    }
    finally
    {
      localObject = finally;
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
      throw localObject;
    }
    String[] arrayOfString2 = null;
    return arrayOfString2;
  }
  
  public final List f(String paramString)
  {
    try
    {
      if (this.a.a)
      {
        paramString = this.a.b(paramString);
        if (paramString != null)
        {
          paramString = Arrays.asList(paramString);
          if (!Thread.interrupted()) {
            return paramString;
          }
          throw new InterruptedException();
        }
        paramString = new ArrayList();
        if (!Thread.interrupted()) {
          return paramString;
        }
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString) {}finally
    {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public final String[] f()
  {
    try
    {
      if (this.a.a)
      {
        String[] arrayOfString1 = this.a.b();
        if (!Thread.interrupted()) {
          return ???;
        }
        throw new InterruptedException();
      }
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable = localThrowable;
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
    }
    finally
    {
      localObject = finally;
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
      throw localObject;
    }
    String[] arrayOfString2 = null;
    return arrayOfString2;
  }
  
  public final String[] g()
  {
    try
    {
      if (this.a.a)
      {
        String[] arrayOfString1 = this.a.c();
        if (!Thread.interrupted()) {
          return ???;
        }
        throw new InterruptedException();
      }
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable = localThrowable;
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
    }
    finally
    {
      localObject = finally;
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
      throw localObject;
    }
    String[] arrayOfString2 = null;
    return arrayOfString2;
  }
  
  public final String h()
  {
    try
    {
      if (this.a.a)
      {
        String str = this.a.f();
        return str;
      }
    }
    catch (Throwable localThrowable) {}
    return null;
  }
  
  public final String[] i()
  {
    try
    {
      if (this.a.a)
      {
        String[] arrayOfString = this.a.g();
        return arrayOfString;
      }
    }
    catch (Throwable localThrowable) {}
    return null;
  }
}
