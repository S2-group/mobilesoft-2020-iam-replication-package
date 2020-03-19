package com.threatmetrix.TrustDefenderMobile;

import ahs;
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

public class NativeGatherer
{
  static final int a = 1;
  static final int b = 2;
  static final int c = 3;
  static final int d = 4;
  static final int e = 8;
  static final int f = 7;
  private static volatile NativeGatherer h;
  private static final String i;
  private static final Lock j;
  private NativeGathererHelper k = new NativeGathererHelper();
  private String[] l = null;
  private long m = 0L;
  
  static
  {
    if (!NativeGatherer.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      g = bool;
      i = ahs.a(NativeGatherer.class);
      j = new ReentrantLock();
      return;
    }
  }
  
  private NativeGatherer() {}
  
  public static NativeGatherer a()
  {
    if (h == null) {}
    try
    {
      j.lock();
      if (h == null) {
        h = new NativeGatherer();
      }
      return h;
    }
    finally
    {
      j.unlock();
    }
  }
  
  private String[] b(Context paramContext)
  {
    if ((this.l != null) && (TimeUnit.SECONDS.convert(System.nanoTime() - this.m, TimeUnit.NANOSECONDS) < 60L)) {
      return this.l;
    }
    Log.d(i, "Starting path find for apk");
    this.m = System.nanoTime();
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
    Log.d(i, "findAPKPaths found : " + paramContext.size());
    this.l = ((String[])paramContext.toArray(new String[paramContext.size()]));
    return this.l;
  }
  
  public int a(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((!g) && (paramContext == null)) {
      throw new AssertionError();
    }
    int i1 = 0;
    int n = 0;
    try
    {
      if (this.k.a)
      {
        this.k.c = b(paramContext);
        n = this.k.findPackages(paramInt2, paramInt3, this.k.c, paramInt1);
      }
    }
    catch (Throwable paramContext)
    {
      Log.e(i, "Native code:", paramContext);
      n = i1;
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    return n;
  }
  
  public int a(String paramString1, String paramString2)
  {
    try
    {
      if ((this.k.a) && (paramString1 != null) && (paramString2 != null))
      {
        n = this.k.setConfig(paramString1, paramString2);
        if (!Thread.interrupted()) {
          return n;
        }
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString1)
    {
      Log.e(i, "Native code:", paramString1);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    int n = -1;
    return n;
  }
  
  public String a(int paramInt)
  {
    try
    {
      if ((this.k.a) && (paramInt > 0))
      {
        String str1 = this.k.getRandomString(paramInt);
        if (!Thread.interrupted()) {
          return ???;
        }
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(i, "Native code:", localThrowable);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    String str2 = null;
    return str2;
  }
  
  public String a(String paramString)
  {
    try
    {
      if ((this.k.a) && (paramString != null))
      {
        paramString = this.k.hashFile(paramString);
        if (!Thread.interrupted()) {
          return paramString;
        }
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString)
    {
      Log.e(i, "Native code:", paramString);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public boolean a(Context paramContext)
  {
    return this.k.a(paramContext);
  }
  
  public String[] a(String[] paramArrayOfString)
  {
    try
    {
      String str2 = i;
      StringBuilder localStringBuilder = new StringBuilder();
      if (this.k.a) {}
      for (String str1 = " available ";; str1 = "not available ")
      {
        Log.d(str2, str1 + " Found " + this.k.b);
        if ((!this.k.a) || (paramArrayOfString == null)) {
          break;
        }
        paramArrayOfString = this.k.checkURLs(paramArrayOfString);
        if (!Thread.interrupted()) {
          return paramArrayOfString;
        }
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramArrayOfString)
    {
      Log.e(i, "Native code:", paramArrayOfString);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    paramArrayOfString = null;
    return paramArrayOfString;
  }
  
  public String b(String paramString)
  {
    try
    {
      if ((this.k.a) && (paramString != null))
      {
        paramString = this.k.md5(paramString);
        if (!Thread.interrupted()) {
          return paramString;
        }
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString)
    {
      Log.e(i, "Native code:", paramString);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public String b(String paramString1, String paramString2)
  {
    try
    {
      if ((this.k.a) && (paramString2 != null) && (paramString1 != null) && (paramString2.length() > 0) && (!paramString1.isEmpty()))
      {
        paramString1 = this.k.xor(paramString1, paramString2);
        if (!Thread.interrupted()) {
          return paramString1;
        }
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString1)
    {
      Log.e(i, "Native code:", paramString1);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    paramString1 = null;
    return paramString1;
  }
  
  void b()
  {
    if (this.k.a) {
      this.k.finit();
    }
    this.k.a = false;
  }
  
  public String c(String paramString)
  {
    try
    {
      if ((this.k.a) && (paramString != null))
      {
        paramString = this.k.getConfig(paramString);
        if (!Thread.interrupted()) {
          return paramString;
        }
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString)
    {
      Log.e(i, "Native code:", paramString);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public boolean c()
  {
    return this.k.a;
  }
  
  public int d()
  {
    try
    {
      if (this.k.a)
      {
        int n = this.k.cancel();
        return n;
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(i, "Native code:", localThrowable);
    }
    return -1;
  }
  
  public String d(String paramString)
  {
    try
    {
      if ((this.k.a) && (paramString != null))
      {
        paramString = this.k.sha1(paramString);
        if (!Thread.interrupted()) {
          return paramString;
        }
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString)
    {
      Log.e(i, "Native code:", paramString);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public int e()
  {
    try
    {
      if (this.k.a)
      {
        int n = this.k.waitUntilCancelled();
        return n;
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(i, "Native code:", localThrowable);
    }
    return -1;
  }
  
  public String e(String paramString)
  {
    try
    {
      if ((this.k.a) && (paramString != null))
      {
        paramString = this.k.urlEncode(paramString);
        if (!Thread.interrupted()) {
          return paramString;
        }
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString)
    {
      Log.e(i, "Native code:", paramString);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public List<String> f(String paramString)
  {
    try
    {
      if ((this.k.a) && (paramString != null))
      {
        paramString = this.k.getFontList(paramString);
        if (paramString != null)
        {
          paramString = Arrays.asList(paramString);
          if (!Thread.interrupted()) {
            return paramString;
          }
          Log.d(i, "Thread interrupt detected, throwing");
          throw new InterruptedException();
        }
        paramString = new ArrayList();
        if (!Thread.interrupted()) {
          return paramString;
        }
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString)
    {
      Log.e(i, "Native code:", paramString);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public String[] f()
  {
    try
    {
      if (this.k.a)
      {
        String[] arrayOfString1 = this.k.findRunningProcs();
        if (!Thread.interrupted()) {
          return ???;
        }
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(i, "Native code:", localThrowable);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    String[] arrayOfString2 = null;
    return arrayOfString2;
  }
  
  public String[] g()
  {
    try
    {
      if (this.k.a)
      {
        String[] arrayOfString1 = this.k.findInstalledProcs();
        if (!Thread.interrupted()) {
          return ???;
        }
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(i, "Native code:", localThrowable);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    String[] arrayOfString2 = null;
    return arrayOfString2;
  }
  
  public String[] h()
  {
    try
    {
      if (this.k.a)
      {
        String[] arrayOfString1 = this.k.findAllProcs();
        if (!Thread.interrupted()) {
          return ???;
        }
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(i, "Native code:", localThrowable);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(i, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    String[] arrayOfString2 = null;
    return arrayOfString2;
  }
  
  public String i()
  {
    try
    {
      if (this.k.a)
      {
        String str = this.k.getBinaryArch();
        return str;
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(i, "Native code:", localThrowable);
    }
    return null;
  }
  
  public String[] j()
  {
    try
    {
      if (this.k.a)
      {
        String[] arrayOfString = this.k.getNetworkInfo();
        return arrayOfString;
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(i, "Native code:", localThrowable);
    }
    return null;
  }
  
  class NativeGathererHelper
  {
    boolean a = false;
    int b = 0;
    String[] c = { "/system/app", "/system/priv-app" };
    private final String f = ahs.a(NativeGathererHelper.class);
    private final Lock g = new ReentrantLock();
    
    static
    {
      if (!NativeGatherer.class.desiredAssertionStatus()) {}
      for (boolean bool = true;; bool = false)
      {
        d = bool;
        return;
      }
    }
    
    NativeGathererHelper() {}
    
    /* Error */
    boolean a(Context paramContext)
    {
      // Byte code:
      //   0: getstatic 30	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:d	Z
      //   3: ifne +15 -> 18
      //   6: aload_1
      //   7: ifnonnull +11 -> 18
      //   10: new 68	java/lang/AssertionError
      //   13: dup
      //   14: invokespecial 69	java/lang/AssertionError:<init>	()V
      //   17: athrow
      //   18: aload_0
      //   19: getfield 46	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:a	Z
      //   22: ifeq +8 -> 30
      //   25: aload_0
      //   26: getfield 46	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:a	Z
      //   29: ireturn
      //   30: aload_0
      //   31: getfield 61	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:g	Ljava/util/concurrent/locks/Lock;
      //   34: invokeinterface 74 1 0
      //   39: aload_0
      //   40: getfield 46	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:a	Z
      //   43: ifeq +19 -> 62
      //   46: aload_0
      //   47: getfield 46	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:a	Z
      //   50: istore_2
      //   51: aload_0
      //   52: getfield 61	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:g	Ljava/util/concurrent/locks/Lock;
      //   55: invokeinterface 77 1 0
      //   60: iload_2
      //   61: ireturn
      //   62: ldc 79
      //   64: invokestatic 85	java/lang/System:loadLibrary	(Ljava/lang/String;)V
      //   67: aload_1
      //   68: invokevirtual 91	android/content/Context:getFilesDir	()Ljava/io/File;
      //   71: invokevirtual 97	java/io/File:getAbsolutePath	()Ljava/lang/String;
      //   74: astore_1
      //   75: aload_0
      //   76: aload_0
      //   77: getstatic 102	aie:b	Ljava/lang/Integer;
      //   80: invokevirtual 108	java/lang/Integer:intValue	()I
      //   83: aload_1
      //   84: invokevirtual 112	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:init	(ILjava/lang/String;)Z
      //   87: putfield 46	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:a	Z
      //   90: aload_0
      //   91: getfield 44	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:f	Ljava/lang/String;
      //   94: new 114	java/lang/StringBuilder
      //   97: dup
      //   98: invokespecial 115	java/lang/StringBuilder:<init>	()V
      //   101: ldc 117
      //   103: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   106: aload_0
      //   107: getfield 48	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:b	I
      //   110: invokevirtual 124	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   113: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   116: invokestatic 132	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
      //   119: pop
      //   120: aload_0
      //   121: getfield 61	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:g	Ljava/util/concurrent/locks/Lock;
      //   124: invokeinterface 77 1 0
      //   129: aload_0
      //   130: getfield 46	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:a	Z
      //   133: ireturn
      //   134: astore_1
      //   135: aload_0
      //   136: iconst_0
      //   137: putfield 46	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:a	Z
      //   140: goto -50 -> 90
      //   143: astore_1
      //   144: aload_0
      //   145: getfield 61	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:g	Ljava/util/concurrent/locks/Lock;
      //   148: invokeinterface 77 1 0
      //   153: aload_1
      //   154: athrow
      //   155: astore_1
      //   156: aload_0
      //   157: getfield 44	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:f	Ljava/lang/String;
      //   160: ldc -122
      //   162: aload_1
      //   163: invokestatic 137	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   166: pop
      //   167: goto -77 -> 90
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	170	0	this	NativeGathererHelper
      //   0	170	1	paramContext	Context
      //   50	11	2	bool	boolean
      // Exception table:
      //   from	to	target	type
      //   62	90	134	java/lang/UnsatisfiedLinkError
      //   30	51	143	finally
      //   62	90	143	finally
      //   90	120	143	finally
      //   135	140	143	finally
      //   156	167	143	finally
      //   62	90	155	java/lang/Throwable
    }
    
    native int cancel();
    
    native String[] checkURLs(String[] paramArrayOfString);
    
    protected void finalize()
    {
      super.finalize();
      finit();
    }
    
    native String[] findAllProcs();
    
    native String[] findInstalledProcs();
    
    native int findPackages(int paramInt1, int paramInt2, String[] paramArrayOfString, int paramInt3);
    
    native String[] findRunningProcs();
    
    native void finit();
    
    native String getBinaryArch();
    
    native String getConfig(String paramString);
    
    native String[] getFontList(String paramString);
    
    native String[] getNetworkInfo();
    
    native String getRandomString(int paramInt);
    
    native String hashFile(String paramString);
    
    native boolean init(int paramInt, String paramString);
    
    native String md5(String paramString);
    
    native int setConfig(String paramString1, String paramString2);
    
    native String sha1(String paramString);
    
    native String urlEncode(String paramString);
    
    native int waitUntilCancelled();
    
    native String xor(String paramString1, String paramString2);
  }
}
