package com.tapjoy.internal;

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

class ft
{
  private static volatile ft c;
  private static final String d;
  private static final Lock e;
  a a = new a();
  private String[] f = null;
  private long g = 0L;
  
  static
  {
    if (!ft.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      b = bool;
      d = gf.a(ft.class);
      e = new ReentrantLock();
      return;
    }
  }
  
  private ft() {}
  
  public static ft a()
  {
    if (c == null) {}
    try
    {
      e.lock();
      if (c == null) {
        c = new ft();
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
    }
    catch (Throwable paramContext)
    {
      Log.e(d, "Native code:", paramContext);
      i = j;
    }
    finally
    {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
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
    catch (Throwable paramString1)
    {
      Log.e(d, "Native code:", paramString1);
    }
    finally
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
    catch (Throwable paramString)
    {
      Log.e(d, "Native code:", paramString);
    }
    finally
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
    catch (Throwable paramArrayOfString)
    {
      Log.e(d, "Native code:", paramArrayOfString);
    }
    finally
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
    catch (Throwable localThrowable)
    {
      Log.e(d, "Native code:", localThrowable);
    }
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
    catch (Throwable paramString)
    {
      Log.e(d, "Native code:", paramString);
    }
    finally
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
    catch (Throwable paramString1)
    {
      Log.e(d, "Native code:", paramString1);
    }
    finally
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
    catch (Throwable localThrowable)
    {
      Log.e(d, "Native code:", localThrowable);
    }
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
    }
    catch (Throwable paramString)
    {
      Log.e(d, "Native code:", paramString);
    }
    finally
    {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
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
    }
    catch (Throwable localThrowable)
    {
      Log.e(d, "Native code:", localThrowable);
    }
    finally
    {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
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
    catch (Throwable paramString)
    {
      Log.e(d, "Native code:", paramString);
    }
    finally
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
    catch (Throwable paramString)
    {
      Log.e(d, "Native code:", paramString);
    }
    finally
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
    }
    catch (Throwable localThrowable)
    {
      Log.e(d, "Native code:", localThrowable);
    }
    finally
    {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
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
    catch (Throwable paramString)
    {
      Log.e(d, "Native code:", paramString);
    }
    finally
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
    }
    catch (Throwable localThrowable)
    {
      Log.e(d, "Native code:", localThrowable);
    }
    finally
    {
      if (Thread.interrupted()) {
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
      if (this.a.a)
      {
        String[] arrayOfString1 = this.a.c();
        if (!Thread.interrupted()) {
          return ???;
        }
        throw new InterruptedException();
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(d, "Native code:", localThrowable);
    }
    finally
    {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
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
    catch (Throwable localThrowable)
    {
      Log.e(d, "Native code:", localThrowable);
    }
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
    catch (Throwable localThrowable)
    {
      Log.e(d, "Native code:", localThrowable);
    }
    return null;
  }
  
  class a
  {
    boolean a = false;
    int b = 0;
    String[] c = { "/system/app", "/system/priv-app" };
    private final String f = gf.a(a.class);
    private final Lock g = new ReentrantLock();
    
    static
    {
      if (!ft.class.desiredAssertionStatus()) {}
      for (boolean bool = true;; bool = false)
      {
        d = bool;
        return;
      }
    }
    
    a() {}
    
    private native boolean a(int paramInt, String paramString);
    
    private native void h();
    
    final native int a(int paramInt1, int paramInt2, String[] paramArrayOfString, int paramInt3);
    
    final native String a(int paramInt);
    
    final native String a(String paramString);
    
    final native String a(String paramString1, String paramString2);
    
    /* Error */
    final boolean a(Context paramContext)
    {
      // Byte code:
      //   0: getstatic 29	com/tapjoy/internal/ft$a:d	Z
      //   3: ifne +15 -> 18
      //   6: aload_1
      //   7: ifnonnull +11 -> 18
      //   10: new 73	java/lang/AssertionError
      //   13: dup
      //   14: invokespecial 74	java/lang/AssertionError:<init>	()V
      //   17: athrow
      //   18: aload_0
      //   19: getfield 45	com/tapjoy/internal/ft$a:a	Z
      //   22: ifeq +8 -> 30
      //   25: aload_0
      //   26: getfield 45	com/tapjoy/internal/ft$a:a	Z
      //   29: ireturn
      //   30: aload_0
      //   31: getfield 60	com/tapjoy/internal/ft$a:g	Ljava/util/concurrent/locks/Lock;
      //   34: invokeinterface 79 1 0
      //   39: aload_0
      //   40: getfield 45	com/tapjoy/internal/ft$a:a	Z
      //   43: ifeq +19 -> 62
      //   46: aload_0
      //   47: getfield 45	com/tapjoy/internal/ft$a:a	Z
      //   50: istore_2
      //   51: aload_0
      //   52: getfield 60	com/tapjoy/internal/ft$a:g	Ljava/util/concurrent/locks/Lock;
      //   55: invokeinterface 82 1 0
      //   60: iload_2
      //   61: ireturn
      //   62: ldc 84
      //   64: invokestatic 90	java/lang/System:loadLibrary	(Ljava/lang/String;)V
      //   67: aload_1
      //   68: invokevirtual 96	android/content/Context:getFilesDir	()Ljava/io/File;
      //   71: invokevirtual 102	java/io/File:getAbsolutePath	()Ljava/lang/String;
      //   74: astore_1
      //   75: aload_0
      //   76: aload_0
      //   77: getstatic 107	com/tapjoy/internal/gq:a	Ljava/lang/Integer;
      //   80: invokevirtual 113	java/lang/Integer:intValue	()I
      //   83: aload_1
      //   84: invokespecial 115	com/tapjoy/internal/ft$a:a	(ILjava/lang/String;)Z
      //   87: putfield 45	com/tapjoy/internal/ft$a:a	Z
      //   90: new 117	java/lang/StringBuilder
      //   93: dup
      //   94: ldc 119
      //   96: invokespecial 121	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   99: aload_0
      //   100: getfield 47	com/tapjoy/internal/ft$a:b	I
      //   103: invokevirtual 125	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   106: pop
      //   107: aload_0
      //   108: getfield 60	com/tapjoy/internal/ft$a:g	Ljava/util/concurrent/locks/Lock;
      //   111: invokeinterface 82 1 0
      //   116: aload_0
      //   117: getfield 45	com/tapjoy/internal/ft$a:a	Z
      //   120: ireturn
      //   121: astore_1
      //   122: aload_0
      //   123: iconst_0
      //   124: putfield 45	com/tapjoy/internal/ft$a:a	Z
      //   127: goto -37 -> 90
      //   130: astore_1
      //   131: aload_0
      //   132: getfield 60	com/tapjoy/internal/ft$a:g	Ljava/util/concurrent/locks/Lock;
      //   135: invokeinterface 82 1 0
      //   140: aload_1
      //   141: athrow
      //   142: astore_1
      //   143: aload_0
      //   144: getfield 43	com/tapjoy/internal/ft$a:f	Ljava/lang/String;
      //   147: ldc 127
      //   149: aload_1
      //   150: invokestatic 132	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   153: pop
      //   154: goto -64 -> 90
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	157	0	this	a
      //   0	157	1	paramContext	Context
      //   50	11	2	bool	boolean
      // Exception table:
      //   from	to	target	type
      //   62	90	121	java/lang/UnsatisfiedLinkError
      //   30	51	130	finally
      //   62	90	130	finally
      //   90	107	130	finally
      //   122	127	130	finally
      //   143	154	130	finally
      //   62	90	142	java/lang/Throwable
    }
    
    final native String[] a();
    
    final native String[] a(String[] paramArrayOfString);
    
    final native int b(String paramString1, String paramString2);
    
    final native String[] b();
    
    final native String[] b(String paramString);
    
    final native String c(String paramString);
    
    final native String[] c();
    
    final native int d();
    
    final native String d(String paramString);
    
    final native int e();
    
    final native String e(String paramString);
    
    final native String f();
    
    final native String f(String paramString);
    
    protected void finalize()
    {
      super.finalize();
      h();
    }
    
    final native String[] g();
  }
}
