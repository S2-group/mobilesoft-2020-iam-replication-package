package com.threatmetrix.TrustDefenderMobile;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class NativeGatherer
{
  private static volatile NativeGatherer b;
  private static final String c;
  private static final Lock d;
  private NativeGatherer.NativeGathererHelper e = new NativeGatherer.NativeGathererHelper(this);
  private String[] f = null;
  private long g = 0L;
  
  static
  {
    if (!NativeGatherer.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      a = bool;
      c = ac.a(NativeGatherer.class);
      d = new ReentrantLock();
      return;
    }
  }
  
  private NativeGatherer() {}
  
  public static NativeGatherer a()
  {
    if (b == null) {}
    try
    {
      d.lock();
      if (b == null) {
        b = new NativeGatherer();
      }
      return b;
    }
    finally
    {
      d.unlock();
    }
  }
  
  private String[] b(Context paramContext)
  {
    if ((this.f != null) && (TimeUnit.SECONDS.convert(System.nanoTime() - this.g, TimeUnit.NANOSECONDS) < 60L)) {
      return this.f;
    }
    Log.d(c, "Starting path find for apk");
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
    Log.d(c, "findAPKPaths found : " + paramContext.size());
    this.f = ((String[])paramContext.toArray(new String[paramContext.size()]));
    return this.f;
  }
  
  public final int a(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((!a) && (paramContext == null)) {
      throw new AssertionError();
    }
    int j = 0;
    int i = 0;
    try
    {
      if (this.e.a)
      {
        this.e.c = b(paramContext);
        i = this.e.findPackages(paramInt2, paramInt3, this.e.c, paramInt1);
      }
    }
    catch (Throwable paramContext)
    {
      Log.e(c, "Native code:", paramContext);
      i = j;
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(c, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    return i;
  }
  
  public final String a(String paramString)
  {
    try
    {
      if ((this.e.a) && (paramString != null))
      {
        paramString = this.e.hashFile(paramString);
        if (!Thread.interrupted()) {
          return paramString;
        }
        Log.d(c, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString)
    {
      Log.e(c, "Native code:", paramString);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(c, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public final String a(String paramString1, String paramString2)
  {
    try
    {
      if ((this.e.a) && (paramString2 != null) && (paramString1 != null) && (paramString2.length() > 0) && (!paramString1.isEmpty()))
      {
        paramString1 = this.e.xor(paramString1, paramString2);
        if (!Thread.interrupted()) {
          return paramString1;
        }
        Log.d(c, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString1)
    {
      Log.e(c, "Native code:", paramString1);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(c, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    paramString1 = null;
    return paramString1;
  }
  
  public final boolean a(Context paramContext)
  {
    return this.e.a(paramContext);
  }
  
  public final String[] a(String[] paramArrayOfString)
  {
    try
    {
      String str2 = c;
      StringBuilder localStringBuilder = new StringBuilder();
      if (this.e.a) {}
      for (String str1 = " available ";; str1 = "not available ")
      {
        Log.d(str2, str1 + " Found " + this.e.b);
        if ((!this.e.a) || (paramArrayOfString == null)) {
          break;
        }
        paramArrayOfString = this.e.checkURLs(paramArrayOfString);
        if (!Thread.interrupted()) {
          return paramArrayOfString;
        }
        Log.d(c, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramArrayOfString)
    {
      Log.e(c, "Native code:", paramArrayOfString);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(c, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    paramArrayOfString = null;
    return paramArrayOfString;
  }
  
  public final String b(String paramString)
  {
    try
    {
      if ((this.e.a) && (paramString != null))
      {
        paramString = this.e.md5(paramString);
        if (!Thread.interrupted()) {
          return paramString;
        }
        Log.d(c, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString)
    {
      Log.e(c, "Native code:", paramString);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(c, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public final boolean b()
  {
    return this.e.a;
  }
  
  public final int c()
  {
    try
    {
      if (this.e.a)
      {
        int i = this.e.cancel();
        return i;
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(c, "Native code:", localThrowable);
    }
    return -1;
  }
  
  public final String c(String paramString)
  {
    try
    {
      if ((this.e.a) && (paramString != null))
      {
        paramString = this.e.sha1(paramString);
        if (!Thread.interrupted()) {
          return paramString;
        }
        Log.d(c, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString)
    {
      Log.e(c, "Native code:", paramString);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(c, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public final int d()
  {
    try
    {
      if (this.e.a)
      {
        int i = this.e.waitUntilCancelled();
        return i;
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(c, "Native code:", localThrowable);
    }
    return -1;
  }
  
  public final String d(String paramString)
  {
    try
    {
      if ((this.e.a) && (paramString != null))
      {
        paramString = this.e.urlEncode(paramString);
        if (!Thread.interrupted()) {
          return paramString;
        }
        Log.d(c, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString)
    {
      Log.e(c, "Native code:", paramString);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(c, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public final String e()
  {
    try
    {
      if (this.e.a)
      {
        String str1 = this.e.getRandomString(32);
        if (!Thread.interrupted()) {
          return ???;
        }
        Log.d(c, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(c, "Native code:", localThrowable);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(c, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    String str2 = null;
    return str2;
  }
  
  public final List<String> e(String paramString)
  {
    try
    {
      if ((this.e.a) && (paramString != null))
      {
        paramString = this.e.getFontList(paramString);
        if (paramString != null)
        {
          paramString = Arrays.asList(paramString);
          if (!Thread.interrupted()) {
            return paramString;
          }
          Log.d(c, "Thread interrupt detected, throwing");
          throw new InterruptedException();
        }
        paramString = new ArrayList();
        if (!Thread.interrupted()) {
          return paramString;
        }
        Log.d(c, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString)
    {
      Log.e(c, "Native code:", paramString);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(c, "Thread interrupt detected, throwing");
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
      if (this.e.a)
      {
        String[] arrayOfString1 = this.e.findRunningProcs();
        if (!Thread.interrupted()) {
          return ???;
        }
        Log.d(c, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(c, "Native code:", localThrowable);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(c, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    String[] arrayOfString2 = null;
    return arrayOfString2;
  }
  
  public final String[] g()
  {
    try
    {
      if (this.e.a)
      {
        String[] arrayOfString1 = this.e.findInstalledProcs();
        if (!Thread.interrupted()) {
          return ???;
        }
        Log.d(c, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(c, "Native code:", localThrowable);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(c, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    String[] arrayOfString2 = null;
    return arrayOfString2;
  }
  
  public final String[] h()
  {
    try
    {
      if (this.e.a)
      {
        String[] arrayOfString1 = this.e.findAllProcs();
        if (!Thread.interrupted()) {
          return ???;
        }
        Log.d(c, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(c, "Native code:", localThrowable);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(c, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    String[] arrayOfString2 = null;
    return arrayOfString2;
  }
}
